package com.google.crypto.tink.hybrid.internal;

/* loaded from: classes4.dex */
final class HpkeKemEncapOutput {
    private final byte[] encapsulatedKey;
    private final byte[] sharedSecret;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HpkeKemEncapOutput(byte[] sharedSecret, byte[] encapsulatedKey) {
        this.sharedSecret = sharedSecret;
        this.encapsulatedKey = encapsulatedKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getSharedSecret() {
        return this.sharedSecret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getEncapsulatedKey() {
        return this.encapsulatedKey;
    }
}
