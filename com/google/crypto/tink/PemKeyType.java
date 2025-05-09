package com.google.crypto.tink;

import androidx.media3.exoplayer.RendererCapabilities;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public enum PemKeyType {
    RSA_PSS_2048_SHA256("RSA", "RSASSA-PSS", 2048, Enums.HashType.SHA256),
    RSA_PSS_3072_SHA256("RSA", "RSASSA-PSS", 3072, Enums.HashType.SHA256),
    RSA_PSS_4096_SHA256("RSA", "RSASSA-PSS", 4096, Enums.HashType.SHA256),
    RSA_PSS_4096_SHA512("RSA", "RSASSA-PSS", 4096, Enums.HashType.SHA512),
    RSA_SIGN_PKCS1_2048_SHA256("RSA", "RSASSA-PKCS1-v1_5", 2048, Enums.HashType.SHA256),
    RSA_SIGN_PKCS1_3072_SHA256("RSA", "RSASSA-PKCS1-v1_5", 3072, Enums.HashType.SHA256),
    RSA_SIGN_PKCS1_4096_SHA256("RSA", "RSASSA-PKCS1-v1_5", 4096, Enums.HashType.SHA256),
    RSA_SIGN_PKCS1_4096_SHA512("RSA", "RSASSA-PKCS1-v1_5", 4096, Enums.HashType.SHA512),
    ECDSA_P256_SHA256("EC", "ECDSA", 256, Enums.HashType.SHA256),
    ECDSA_P384_SHA384("EC", "ECDSA", RendererCapabilities.DECODER_SUPPORT_MASK, Enums.HashType.SHA384),
    ECDSA_P521_SHA512("EC", "ECDSA", BaselineTIFFTagSet.TAG_JPEG_AC_TABLES, Enums.HashType.SHA512);

    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";
    private static final String MARKER = "-----";
    private static final String PRIVATE_KEY = "PRIVATE KEY";
    private static final String PUBLIC_KEY = "PUBLIC KEY";
    public final String algorithm;
    public final Enums.HashType hash;
    public final int keySizeInBits;
    public final String keyType;

    PemKeyType(String keyType, String algorithm, int keySizeInBits, Enums.HashType hash) {
        this.keyType = keyType;
        this.algorithm = algorithm;
        this.keySizeInBits = keySizeInBits;
        this.hash = hash;
    }

    @Nullable
    public java.security.Key readKey(BufferedReader reader) throws IOException {
        String substring;
        int indexOf;
        byte[] decode;
        String readLine = reader.readLine();
        while (readLine != null && !readLine.startsWith(BEGIN)) {
            readLine = reader.readLine();
        }
        if (readLine == null || (indexOf = (substring = readLine.trim().substring(11)).indexOf(MARKER)) < 0) {
            return null;
        }
        String substring2 = substring.substring(0, indexOf);
        String str = END + substring2 + MARKER;
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine2 = reader.readLine();
            if (readLine2 != null) {
                if (readLine2.indexOf(":") <= 0) {
                    if (!readLine2.contains(str)) {
                        sb.append(readLine2);
                    }
                }
            }
            try {
                decode = Base64.decode(sb.toString(), 0);
            } catch (IllegalArgumentException | GeneralSecurityException unused) {
            }
            if (substring2.contains(PUBLIC_KEY)) {
                return getPublicKey(decode);
            }
            if (substring2.contains(PRIVATE_KEY)) {
                return getPrivateKey(decode);
            }
            return null;
        }
    }

    private java.security.Key getPublicKey(final byte[] key) throws GeneralSecurityException {
        return validate(EngineFactory.KEY_FACTORY.getInstance(this.keyType).generatePublic(new X509EncodedKeySpec(key)));
    }

    private java.security.Key getPrivateKey(final byte[] key) throws GeneralSecurityException {
        return validate(EngineFactory.KEY_FACTORY.getInstance(this.keyType).generatePrivate(new PKCS8EncodedKeySpec(key)));
    }

    private java.security.Key validate(java.security.Key key) throws GeneralSecurityException {
        if (this.keyType.equals("RSA")) {
            int bitLength = ((RSAKey) key).getModulus().bitLength();
            if (bitLength != this.keySizeInBits) {
                throw new GeneralSecurityException(String.format("invalid RSA key size, want %d got %d", Integer.valueOf(this.keySizeInBits), Integer.valueOf(bitLength)));
            }
        } else {
            ECParameterSpec params = ((ECKey) key).getParams();
            if (!EllipticCurves.isNistEcParameterSpec(params)) {
                throw new GeneralSecurityException("unsupport EC spec: " + params.toString());
            }
            int fieldSizeInBits = EllipticCurves.fieldSizeInBits(params.getCurve());
            if (fieldSizeInBits != this.keySizeInBits) {
                throw new GeneralSecurityException(String.format("invalid EC key size, want %d got %d", Integer.valueOf(this.keySizeInBits), Integer.valueOf(fieldSizeInBits)));
            }
        }
        return key;
    }
}
