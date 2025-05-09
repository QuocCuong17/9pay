package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
/* loaded from: classes4.dex */
public final class HpkeDecrypt implements HybridDecrypt {
    private static final byte[] EMPTY_ASSOCIATED_DATA = new byte[0];
    private final HpkeAead aead;
    private final int encapsulatedKeyLength;
    private final HpkeKdf kdf;
    private final HpkeKem kem;
    private final HpkeKemPrivateKey recipientPrivateKey;

    private HpkeDecrypt(HpkeKemPrivateKey recipientPrivateKey, HpkeKem kem, HpkeKdf kdf, HpkeAead aead, int encapsulatedKeyLength) {
        this.recipientPrivateKey = recipientPrivateKey;
        this.kem = kem;
        this.kdf = kdf;
        this.aead = aead;
        this.encapsulatedKeyLength = encapsulatedKeyLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.hybrid.internal.HpkeDecrypt$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] iArr = new int[com.google.crypto.tink.proto.HpkeKem.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HpkeKem = iArr;
            try {
                iArr[com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int encodingSizeInBytes(com.google.crypto.tink.proto.HpkeKem kemProtoEnum) {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[kemProtoEnum.ordinal()];
        if (i == 1) {
            return 32;
        }
        if (i == 2) {
            return 65;
        }
        if (i == 3) {
            return 97;
        }
        if (i == 4) {
            return 133;
        }
        throw new IllegalArgumentException("Unable to determine KEM-encoding length for " + kemProtoEnum.name());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HpkeDecrypt createHpkeDecrypt(HpkePrivateKey recipientPrivateKey) throws GeneralSecurityException {
        if (!recipientPrivateKey.hasPublicKey()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if (!recipientPrivateKey.getPublicKey().hasParams()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if (recipientPrivateKey.getPrivateKey().isEmpty()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        HpkeParams params = recipientPrivateKey.getPublicKey().getParams();
        return new HpkeDecrypt(HpkeKemKeyFactory.createPrivate(recipientPrivateKey), HpkePrimitiveFactory.createKem(params), HpkePrimitiveFactory.createKdf(params), HpkePrimitiveFactory.createAead(params), encodingSizeInBytes(params.getKem()));
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
        int length = ciphertext.length;
        int i = this.encapsulatedKeyLength;
        if (length < i) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        }
        if (contextInfo == null) {
            contextInfo = new byte[0];
        }
        byte[] bArr = contextInfo;
        byte[] copyOf = Arrays.copyOf(ciphertext, i);
        return HpkeContext.createRecipientContext(copyOf, this.recipientPrivateKey, this.kem, this.kdf, this.aead, bArr).open(Arrays.copyOfRange(ciphertext, this.encapsulatedKeyLength, ciphertext.length), EMPTY_ASSOCIATED_DATA);
    }
}
