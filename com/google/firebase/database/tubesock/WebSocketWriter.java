package com.google.firebase.database.tubesock;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes4.dex */
class WebSocketWriter {
    private WritableByteChannel channel;
    private BlockingQueue<ByteBuffer> pendingBuffers;
    private WebSocket websocket;
    private final Random random = new Random();
    private volatile boolean stop = false;
    private boolean closeSent = false;
    private final Thread innerThread = WebSocket.getThreadFactory().newThread(new Runnable() { // from class: com.google.firebase.database.tubesock.WebSocketWriter.1
        @Override // java.lang.Runnable
        public void run() {
            WebSocketWriter.this.runWriter();
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketWriter(WebSocket webSocket, String str, int i) {
        WebSocket.getIntializer().setName(getInnerThread(), str + "Writer-" + i);
        this.websocket = webSocket;
        this.pendingBuffers = new LinkedBlockingQueue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOutput(OutputStream outputStream) {
        this.channel = Channels.newChannel(outputStream);
    }

    private ByteBuffer frameInBuffer(byte b, boolean z, byte[] bArr) throws IOException {
        int i = z ? 6 : 2;
        int length = bArr.length;
        if (length >= 126) {
            i = length <= 65535 ? i + 2 : i + 8;
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + i);
        allocate.put((byte) (b | Byte.MIN_VALUE));
        if (length < 126) {
            if (z) {
                length |= 128;
            }
            allocate.put((byte) length);
        } else if (length <= 65535) {
            allocate.put((byte) (z ? 254 : 126));
            allocate.putShort((short) length);
        } else {
            allocate.put((byte) (z ? 255 : 127));
            allocate.putInt(0);
            allocate.putInt(length);
        }
        if (z) {
            byte[] generateMask = generateMask();
            allocate.put(generateMask);
            for (int i2 = 0; i2 < bArr.length; i2++) {
                allocate.put((byte) (bArr[i2] ^ generateMask[i2 % 4]));
            }
        }
        allocate.flip();
        return allocate;
    }

    private byte[] generateMask() {
        byte[] bArr = new byte[4];
        this.random.nextBytes(bArr);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
    
        r1.closeSent = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void send(byte b, boolean z, byte[] bArr) throws IOException {
        ByteBuffer frameInBuffer = frameInBuffer(b, z, bArr);
        if (this.stop && (this.closeSent || b != 8)) {
            throw new WebSocketException("Shouldn't be sending");
        }
        this.pendingBuffers.add(frameInBuffer);
    }

    private void writeMessage() throws InterruptedException, IOException {
        this.channel.write(this.pendingBuffers.take());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopIt() {
        this.stop = true;
    }

    private void handleError(WebSocketException webSocketException) {
        this.websocket.handleReceiverError(webSocketException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runWriter() {
        while (!this.stop && !Thread.interrupted()) {
            try {
                writeMessage();
            } catch (IOException e) {
                handleError(new WebSocketException("IO Exception", e));
                return;
            } catch (InterruptedException unused) {
                return;
            }
        }
        for (int i = 0; i < this.pendingBuffers.size(); i++) {
            writeMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Thread getInnerThread() {
        return this.innerThread;
    }
}
