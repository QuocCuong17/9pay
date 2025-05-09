package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes6.dex */
public interface DatagramTransport extends TlsCloseable {
    int getReceiveLimit() throws IOException;

    int getSendLimit() throws IOException;

    int receive(byte[] bArr, int i, int i2, int i3) throws IOException;

    void send(byte[] bArr, int i, int i2) throws IOException;
}
