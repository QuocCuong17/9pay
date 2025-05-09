package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class EciesHkdfSenderKem {
    private final ECPublicKey recipientPublicKey;

    /* loaded from: classes4.dex */
    public static final class KemKey {
        private final com.google.crypto.tink.util.Bytes kemBytes;
        private final com.google.crypto.tink.util.Bytes symmetricKey;

        public KemKey(final byte[] kemBytes, final byte[] symmetricKey) {
            Objects.requireNonNull(kemBytes, "KemBytes must be non-null");
            Objects.requireNonNull(symmetricKey, "symmetricKey must be non-null");
            this.kemBytes = com.google.crypto.tink.util.Bytes.copyFrom(kemBytes);
            this.symmetricKey = com.google.crypto.tink.util.Bytes.copyFrom(symmetricKey);
        }

        public byte[] getKemBytes() {
            return this.kemBytes.toByteArray();
        }

        public byte[] getSymmetricKey() {
            return this.symmetricKey.toByteArray();
        }
    }

    public EciesHkdfSenderKem(final ECPublicKey recipientPublicKey) {
        this.recipientPublicKey = recipientPublicKey;
    }

    public KemKey generateKey(String hmacAlgo, final byte[] hkdfSalt, final byte[] hkdfInfo, int keySizeInBytes, EllipticCurves.PointFormatType pointFormat) throws GeneralSecurityException {
        KeyPair generateKeyPair = EllipticCurves.generateKeyPair(this.recipientPublicKey.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) generateKeyPair.getPublic();
        byte[] computeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) generateKeyPair.getPrivate(), this.recipientPublicKey);
        byte[] pointEncode = EllipticCurves.pointEncode(eCPublicKey.getParams().getCurve(), pointFormat, eCPublicKey.getW());
        return new KemKey(pointEncode, Hkdf.computeEciesHkdfSymmetricKey(pointEncode, computeSharedSecret, hmacAlgo, hkdfSalt, hkdfInfo, keySizeInBytes));
    }
}
