package net.sf.scuba.tlv;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public class TLVInputStream extends InputStream {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");
    private static final int MAX_BUFFER_LENGTH = 65535;
    private int bufferSize;
    private DataInputStream inputStream;
    private TLVInputState markedState;
    private final InputStream originalInputStream;
    private TLVInputState state;

    public TLVInputStream(InputStream inputStream) {
        this.bufferSize = 0;
        try {
            if ((inputStream instanceof BufferedInputStream) || (inputStream instanceof ByteArrayInputStream)) {
                this.bufferSize = inputStream.available();
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception reading from stream", (Throwable) e);
        }
        this.originalInputStream = inputStream;
        this.inputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        this.state = new TLVInputState();
        this.markedState = null;
    }

    public int readTag() throws IOException {
        if (!this.state.isAtStartOfTag() && !this.state.isProcessingValue()) {
            throw new IllegalStateException("Not at start of tag");
        }
        try {
            int readUnsignedByte = this.inputStream.readUnsignedByte();
            int i = 1;
            while (true) {
                if (readUnsignedByte != 0 && readUnsignedByte != 255) {
                    break;
                }
                readUnsignedByte = this.inputStream.readUnsignedByte();
                i++;
            }
            if ((readUnsignedByte & 31) == 31) {
                int readUnsignedByte2 = this.inputStream.readUnsignedByte();
                while (true) {
                    i++;
                    if ((readUnsignedByte2 & 128) != 128) {
                        break;
                    }
                    readUnsignedByte = (readUnsignedByte << 8) | (readUnsignedByte2 & 127);
                    readUnsignedByte2 = this.inputStream.readUnsignedByte();
                }
                readUnsignedByte = (readUnsignedByte << 8) | (readUnsignedByte2 & 127);
            }
            this.state.setTagProcessed(readUnsignedByte, i);
            return readUnsignedByte;
        } catch (IOException e) {
            throw e;
        }
    }

    public int readLength() throws IOException {
        try {
            if (!this.state.isAtStartOfLength()) {
                throw new IllegalStateException("Not at start of length");
            }
            int readUnsignedByte = this.inputStream.readUnsignedByte();
            int i = 1;
            if ((readUnsignedByte & 128) != 0) {
                int i2 = readUnsignedByte & 127;
                int i3 = 0;
                int i4 = 1;
                for (int i5 = 0; i5 < i2; i5++) {
                    i4++;
                    i3 = (i3 << 8) | this.inputStream.readUnsignedByte();
                }
                readUnsignedByte = i3;
                i = i4;
            }
            this.state.setLengthProcessed(readUnsignedByte, i);
            return readUnsignedByte;
        } catch (IOException e) {
            throw e;
        }
    }

    public byte[] readValue() throws IOException {
        try {
            if (!this.state.isProcessingValue()) {
                throw new IllegalStateException("Not yet processing value!");
            }
            int length = this.state.getLength();
            byte[] bArr = new byte[length];
            this.inputStream.readFully(bArr);
            this.state.updateValueBytesProcessed(length);
            return bArr;
        } catch (IOException e) {
            throw e;
        }
    }

    private long skipValue() throws IOException {
        if (this.state.isAtStartOfTag() || this.state.isAtStartOfLength()) {
            return 0L;
        }
        return skip(this.state.getValueBytesLeft());
    }

    public void skipToTag(int i) throws IOException {
        while (true) {
            if (!this.state.isAtStartOfTag()) {
                if (this.state.isAtStartOfLength()) {
                    readLength();
                    if (TLVUtil.isPrimitive(this.state.getTag())) {
                        skipValue();
                    }
                } else if (TLVUtil.isPrimitive(this.state.getTag())) {
                    skipValue();
                }
            }
            int readTag = readTag();
            if (readTag == i) {
                return;
            }
            if (TLVUtil.isPrimitive(readTag)) {
                if (((int) skipValue()) < readLength()) {
                    return;
                }
            }
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.inputStream.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.inputStream.read();
        if (read < 0) {
            return -1;
        }
        this.state.updateValueBytesProcessed(1);
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        long skip = this.inputStream.skip(j);
        this.state.updateValueBytesProcessed((int) skip);
        return skip;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.inputStream.mark(i);
        this.markedState = new TLVInputState(this.state);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.inputStream.markSupported();
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (!markSupported()) {
            throw new IOException("mark/reset not supported");
        }
        this.inputStream.reset();
        this.state = this.markedState;
        this.markedState = null;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    public String toString() {
        return this.state.toString();
    }
}
