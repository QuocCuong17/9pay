package org.bouncycastle.crypto.tls;

/* loaded from: classes6.dex */
public interface TlsPSKIdentityManager {
    byte[] getHint();

    byte[] getPSK(byte[] bArr);
}
