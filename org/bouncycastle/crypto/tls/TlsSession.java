package org.bouncycastle.crypto.tls;

/* loaded from: classes6.dex */
public interface TlsSession {
    SessionParameters exportSessionParameters();

    byte[] getSessionID();

    void invalidate();

    boolean isResumable();
}
