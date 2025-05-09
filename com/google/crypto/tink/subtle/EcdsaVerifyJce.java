package com.google.crypto.tink.subtle;

import com.google.android.gms.security.ProviderInstaller;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;

@Immutable
/* loaded from: classes4.dex */
public final class EcdsaVerifyJce implements PublicKeyVerify {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final EllipticCurves.EcdsaEncoding encoding;
    private final ECPublicKey publicKey;
    private final String signatureAlgorithm;

    public EcdsaVerifyJce(final ECPublicKey pubKey, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        }
        EllipticCurves.checkPublicKey(pubKey);
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hash);
        this.publicKey = pubKey;
        this.encoding = encoding;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
        boolean z;
        if (this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
            if (signature.length != EllipticCurves.fieldSizeInBytes(this.publicKey.getParams().getCurve()) * 2) {
                throw new GeneralSecurityException("Invalid signature");
            }
            signature = EllipticCurves.ecdsaIeee2Der(signature);
        }
        if (!EllipticCurves.isValidDerEncoding(signature)) {
            throw new GeneralSecurityException("Invalid signature");
        }
        Signature engineFactory = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm, EngineFactory.toProviderList(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL", "Conscrypt"));
        engineFactory.initVerify(this.publicKey);
        engineFactory.update(data);
        try {
            z = engineFactory.verify(signature);
        } catch (RuntimeException unused) {
            z = false;
        }
        if (!z) {
            throw new GeneralSecurityException("Invalid signature");
        }
    }
}
