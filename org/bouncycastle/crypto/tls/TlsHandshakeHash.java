package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes6.dex */
public interface TlsHandshakeHash extends Digest {
    Digest forkPRFHash();

    byte[] getFinalHash(short s);

    void init(TlsContext tlsContext);

    TlsHandshakeHash notifyPRFDetermined();

    void sealHashAlgorithms();

    TlsHandshakeHash stopTracking();

    void trackHashAlgorithm(short s);
}
