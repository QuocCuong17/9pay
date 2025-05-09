package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.util.Bytes;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
final class ChunkedHmacVerification implements ChunkedMacVerification {
    private final ChunkedHmacComputation hmacComputation;
    private final Bytes tag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChunkedHmacVerification(HmacKey key, byte[] tag) throws GeneralSecurityException {
        this.hmacComputation = new ChunkedHmacComputation(key);
        this.tag = Bytes.copyFrom(tag);
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacVerification
    public void update(ByteBuffer data) {
        this.hmacComputation.update(data);
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacVerification
    public void verifyMac() throws GeneralSecurityException {
        if (!this.tag.equals(Bytes.copyFrom(this.hmacComputation.computeMac()))) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
