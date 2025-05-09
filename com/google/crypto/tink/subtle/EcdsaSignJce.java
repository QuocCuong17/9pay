package com.google.crypto.tink.subtle;

import com.google.android.gms.security.ProviderInstaller;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;

@Immutable
/* loaded from: classes4.dex */
public final class EcdsaSignJce implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final EllipticCurves.EcdsaEncoding encoding;
    private final ECPrivateKey privateKey;
    private final String signatureAlgorithm;

    public EcdsaSignJce(final ECPrivateKey priv, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        }
        this.privateKey = priv;
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hash);
        this.encoding = encoding;
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(final byte[] data) throws GeneralSecurityException {
        Signature engineFactory = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm, EngineFactory.toProviderList(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL", "Conscrypt"));
        engineFactory.initSign(this.privateKey);
        engineFactory.update(data);
        byte[] sign = engineFactory.sign();
        return this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363 ? EllipticCurves.ecdsaDer2Ieee(sign, EllipticCurves.fieldSizeInBytes(this.privateKey.getParams().getCurve()) * 2) : sign;
    }
}
