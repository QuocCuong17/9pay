package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes6.dex */
class DTLSRecordLayer implements DatagramTransport {
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final int RECORD_HEADER_LENGTH = 13;
    private static final long RETRANSMIT_TIMEOUT = 240000;
    private static final long TCP_MSL = 120000;
    private final TlsContext context;
    private DTLSEpoch currentEpoch;
    private final TlsPeer peer;
    private DTLSEpoch pendingEpoch;
    private volatile int plaintextLimit;
    private DTLSEpoch readEpoch;
    private final DatagramTransport transport;
    private DTLSEpoch writeEpoch;
    private final ByteQueue recordQueue = new ByteQueue();
    private volatile boolean closed = false;
    private volatile boolean failed = false;
    private volatile ProtocolVersion readVersion = null;
    private volatile ProtocolVersion writeVersion = null;
    private DTLSHandshakeRetransmit retransmit = null;
    private DTLSEpoch retransmitEpoch = null;
    private long retransmitExpiry = 0;
    private volatile boolean inHandshake = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSRecordLayer(DatagramTransport datagramTransport, TlsContext tlsContext, TlsPeer tlsPeer, short s) {
        this.transport = datagramTransport;
        this.context = tlsContext;
        this.peer = tlsPeer;
        DTLSEpoch dTLSEpoch = new DTLSEpoch(0, new TlsNullCipher(tlsContext));
        this.currentEpoch = dTLSEpoch;
        this.pendingEpoch = null;
        this.readEpoch = dTLSEpoch;
        this.writeEpoch = dTLSEpoch;
        setPlaintextLimit(16384);
    }

    private void closeTransport() {
        if (this.closed) {
            return;
        }
        try {
            if (!this.failed) {
                warn((short) 0, null);
            }
            this.transport.close();
        } catch (Exception unused) {
        }
        this.closed = true;
    }

    private static long getMacSequenceNumber(int i, long j) {
        return ((i & 4294967295L) << 48) | j;
    }

    private void raiseAlert(short s, short s2, String str, Throwable th) throws IOException {
        this.peer.notifyAlertRaised(s, s2, str, th);
        sendRecord((short) 21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    private int receiveRecord(byte[] bArr, int i, int i2, int i3) throws IOException {
        int readUint16;
        int i4;
        if (this.recordQueue.available() <= 0) {
            int receive = this.transport.receive(bArr, i, i2, i3);
            if (receive < 13 || receive <= (readUint16 = TlsUtils.readUint16(bArr, i + 11) + 13)) {
                return receive;
            }
            this.recordQueue.addData(bArr, i + readUint16, receive - readUint16);
            return readUint16;
        }
        if (this.recordQueue.available() >= 13) {
            byte[] bArr2 = new byte[2];
            this.recordQueue.read(bArr2, 0, 2, 11);
            i4 = TlsUtils.readUint16(bArr2, 0);
        } else {
            i4 = 0;
        }
        int min = Math.min(this.recordQueue.available(), i4 + 13);
        this.recordQueue.removeData(bArr, i, min, 0);
        return min;
    }

    private void sendRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        if (this.writeVersion == null) {
            return;
        }
        if (i2 > this.plaintextLimit) {
            throw new TlsFatalAlert((short) 80);
        }
        if (i2 < 1 && s != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        int epoch = this.writeEpoch.getEpoch();
        long allocateSequenceNumber = this.writeEpoch.allocateSequenceNumber();
        byte[] encodePlaintext = this.writeEpoch.getCipher().encodePlaintext(getMacSequenceNumber(epoch, allocateSequenceNumber), s, bArr, i, i2);
        int length = encodePlaintext.length + 13;
        byte[] bArr2 = new byte[length];
        TlsUtils.writeUint8(s, bArr2, 0);
        TlsUtils.writeVersion(this.writeVersion, bArr2, 1);
        TlsUtils.writeUint16(epoch, bArr2, 3);
        TlsUtils.writeUint48(allocateSequenceNumber, bArr2, 5);
        TlsUtils.writeUint16(encodePlaintext.length, bArr2, 11);
        System.arraycopy(encodePlaintext, 0, bArr2, 13, encodePlaintext.length);
        this.transport.send(bArr2, 0, length);
    }

    @Override // org.bouncycastle.crypto.tls.TlsCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        if (this.inHandshake) {
            warn((short) 90, "User canceled handshake");
        }
        closeTransport();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fail(short s) {
        if (this.closed) {
            return;
        }
        try {
            raiseAlert((short) 2, s, null, null);
        } catch (Exception unused) {
        }
        this.failed = true;
        closeTransport();
    }

    void failed() {
        if (this.closed) {
            return;
        }
        this.failed = true;
        closeTransport();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getReadEpoch() {
        return this.readEpoch.getEpoch();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int getReceiveLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.readEpoch.getCipher().getPlaintextLimit(this.transport.getReceiveLimit() - 13));
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int getSendLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.writeEpoch.getCipher().getPlaintextLimit(this.transport.getSendLimit() - 13));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handshakeSuccessful(DTLSHandshakeRetransmit dTLSHandshakeRetransmit) {
        DTLSEpoch dTLSEpoch = this.readEpoch;
        DTLSEpoch dTLSEpoch2 = this.currentEpoch;
        if (dTLSEpoch == dTLSEpoch2 || this.writeEpoch == dTLSEpoch2) {
            throw new IllegalStateException();
        }
        if (dTLSHandshakeRetransmit != null) {
            this.retransmit = dTLSHandshakeRetransmit;
            this.retransmitEpoch = dTLSEpoch2;
            this.retransmitExpiry = System.currentTimeMillis() + RETRANSMIT_TIMEOUT;
        }
        this.inHandshake = false;
        this.currentEpoch = this.pendingEpoch;
        this.pendingEpoch = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initPendingEpoch(TlsCipher tlsCipher) {
        if (this.pendingEpoch != null) {
            throw new IllegalStateException();
        }
        this.pendingEpoch = new DTLSEpoch(this.writeEpoch.getEpoch() + 1, tlsCipher);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClosed() {
        return this.closed;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0122, code lost:
    
        if (r18.inHandshake != false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0126, code lost:
    
        if (r18.retransmit == null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0128, code lost:
    
        r18.retransmit = null;
        r18.retransmitEpoch = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x012d, code lost:
    
        java.lang.System.arraycopy(r3, 0, r19, r20, r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0137, code lost:
    
        return r3.length;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x0049. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:41:0x00d2. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075 A[Catch: IOException -> 0x0138, TryCatch #0 {IOException -> 0x0138, blocks: (B:7:0x0018, B:9:0x001c, B:11:0x0026, B:12:0x002a, B:15:0x003b, B:17:0x0045, B:18:0x0049, B:19:0x004d, B:21:0x005a, B:24:0x0075, B:26:0x0085, B:28:0x0091, B:30:0x0095, B:33:0x009e, B:38:0x00cc, B:40:0x00d0, B:41:0x00d2, B:46:0x0120, B:48:0x0124, B:50:0x0128, B:51:0x012d, B:42:0x00d6, B:54:0x00db, B:56:0x00df, B:58:0x00e3, B:60:0x00e9, B:62:0x00ed, B:65:0x00fe, B:67:0x0102, B:68:0x010a, B:70:0x010d, B:72:0x0110, B:76:0x011d, B:77:0x0117, B:79:0x011b, B:98:0x0063, B:100:0x0067, B:102:0x006d), top: B:6:0x0018 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [org.bouncycastle.crypto.tls.DTLSEpoch, org.bouncycastle.crypto.tls.DTLSHandshakeRetransmit] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v3 */
    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int receive(byte[] bArr, int i, int i2, int i3) throws IOException {
        DTLSEpoch dTLSEpoch;
        DTLSEpoch dTLSEpoch2;
        DTLSEpoch dTLSEpoch3;
        DTLSEpoch dTLSEpoch4;
        ?? r0 = 0;
        byte[] bArr2 = null;
        while (true) {
            int min = Math.min(i2, getReceiveLimit()) + 13;
            if (bArr2 == null || bArr2.length < min) {
                bArr2 = new byte[min];
            }
            try {
                if (this.retransmit != null && System.currentTimeMillis() > this.retransmitExpiry) {
                    this.retransmit = r0;
                    this.retransmitEpoch = r0;
                }
                int receiveRecord = receiveRecord(bArr2, 0, min, i3);
                if (receiveRecord < 0) {
                    return receiveRecord;
                }
                if (receiveRecord >= 13 && receiveRecord == TlsUtils.readUint16(bArr2, 11) + 13) {
                    short readUint8 = TlsUtils.readUint8(bArr2, 0);
                    switch (readUint8) {
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                            int readUint16 = TlsUtils.readUint16(bArr2, 3);
                            if (readUint16 == this.readEpoch.getEpoch()) {
                                dTLSEpoch3 = this.readEpoch;
                            } else if (readUint8 == 22 && (dTLSEpoch2 = this.retransmitEpoch) != null && readUint16 == dTLSEpoch2.getEpoch()) {
                                dTLSEpoch3 = this.retransmitEpoch;
                            } else {
                                dTLSEpoch = r0;
                                if (dTLSEpoch != null) {
                                    break;
                                } else {
                                    long readUint48 = TlsUtils.readUint48(bArr2, 5);
                                    if (dTLSEpoch.getReplayWindow().shouldDiscard(readUint48)) {
                                        break;
                                    } else {
                                        ProtocolVersion readVersion = TlsUtils.readVersion(bArr2, 1);
                                        if (readVersion.isDTLS() && (this.readVersion == null || this.readVersion.equals(readVersion))) {
                                            byte[] decodeCiphertext = dTLSEpoch.getCipher().decodeCiphertext(getMacSequenceNumber(dTLSEpoch.getEpoch(), readUint48), readUint8, bArr2, 13, receiveRecord - 13);
                                            dTLSEpoch.getReplayWindow().reportAuthenticated(readUint48);
                                            if (decodeCiphertext.length <= this.plaintextLimit) {
                                                if (this.readVersion == null) {
                                                    this.readVersion = readVersion;
                                                }
                                                switch (readUint8) {
                                                    case 20:
                                                        for (int i4 = 0; i4 < decodeCiphertext.length; i4++) {
                                                            if (TlsUtils.readUint8(decodeCiphertext, i4) == 1 && (dTLSEpoch4 = this.pendingEpoch) != null) {
                                                                this.readEpoch = dTLSEpoch4;
                                                            }
                                                        }
                                                        break;
                                                    case 21:
                                                        if (decodeCiphertext.length == 2) {
                                                            short s = decodeCiphertext[0];
                                                            short s2 = decodeCiphertext[1];
                                                            this.peer.notifyAlertReceived(s, s2);
                                                            if (s == 2) {
                                                                failed();
                                                                throw new TlsFatalAlert(s2);
                                                            }
                                                            if (s2 == 0) {
                                                                closeTransport();
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 22:
                                                        if (this.inHandshake) {
                                                            break;
                                                        } else {
                                                            DTLSHandshakeRetransmit dTLSHandshakeRetransmit = this.retransmit;
                                                            if (dTLSHandshakeRetransmit != null) {
                                                                dTLSHandshakeRetransmit.receivedHandshakeRecord(readUint16, decodeCiphertext, 0, decodeCiphertext.length);
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 23:
                                                        if (this.inHandshake) {
                                                            break;
                                                        } else {
                                                            break;
                                                        }
                                                }
                                            }
                                            r0 = 0;
                                        }
                                    }
                                }
                            }
                            dTLSEpoch = dTLSEpoch3;
                            if (dTLSEpoch != null) {
                            }
                            break;
                    }
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetWriteEpoch() {
        DTLSEpoch dTLSEpoch = this.retransmitEpoch;
        if (dTLSEpoch == null) {
            dTLSEpoch = this.currentEpoch;
        }
        this.writeEpoch = dTLSEpoch;
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public void send(byte[] bArr, int i, int i2) throws IOException {
        short s;
        if (this.inHandshake || this.writeEpoch == this.retransmitEpoch) {
            s = 22;
            if (TlsUtils.readUint8(bArr, i) == 20) {
                DTLSEpoch dTLSEpoch = null;
                if (this.inHandshake) {
                    dTLSEpoch = this.pendingEpoch;
                } else if (this.writeEpoch == this.retransmitEpoch) {
                    dTLSEpoch = this.currentEpoch;
                }
                if (dTLSEpoch == null) {
                    throw new IllegalStateException();
                }
                sendRecord((short) 20, new byte[]{1}, 0, 1);
                this.writeEpoch = dTLSEpoch;
            }
        } else {
            s = 23;
        }
        sendRecord(s, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlaintextLimit(int i) {
        this.plaintextLimit = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    void warn(short s, String str) throws IOException {
        raiseAlert((short) 1, s, str, null);
    }
}
