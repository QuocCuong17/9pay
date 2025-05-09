package org.bouncycastle.crypto.tls;

/* loaded from: classes6.dex */
public interface TlsPSKIdentity {
    byte[] getPSK();

    byte[] getPSKIdentity();

    void notifyIdentityHint(byte[] bArr);

    void skipIdentityHint();
}
