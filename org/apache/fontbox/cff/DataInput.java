package org.apache.fontbox.cff;

import java.io.EOFException;
import java.io.IOException;

/* loaded from: classes5.dex */
public class DataInput {
    private int bufferPosition = 0;
    private byte[] inputBuffer;

    public DataInput(byte[] bArr) {
        this.inputBuffer = null;
        this.inputBuffer = bArr;
    }

    public boolean hasRemaining() {
        return this.bufferPosition < this.inputBuffer.length;
    }

    public int getPosition() {
        return this.bufferPosition;
    }

    public void setPosition(int i) {
        this.bufferPosition = i;
    }

    public String getString() throws IOException {
        return new String(this.inputBuffer, "ISO-8859-1");
    }

    public byte readByte() throws IOException {
        return (byte) readUnsignedByte();
    }

    public int readUnsignedByte() throws IOException {
        int read = read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    public int peekUnsignedByte(int i) throws IOException {
        int peek = peek(i);
        if (peek >= 0) {
            return peek;
        }
        throw new EOFException();
    }

    public short readShort() throws IOException {
        return (short) readUnsignedShort();
    }

    public int readUnsignedShort() throws IOException {
        int read = read();
        int read2 = read();
        if ((read | read2) >= 0) {
            return (read << 8) | read2;
        }
        throw new EOFException();
    }

    public int readInt() throws IOException {
        int read = read();
        int read2 = read();
        int read3 = read();
        int read4 = read();
        if ((read | read2 | read3 | read4) >= 0) {
            return (read << 24) | (read2 << 16) | (read3 << 8) | read4;
        }
        throw new EOFException();
    }

    public byte[] readBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = readByte();
        }
        return bArr;
    }

    private int read() {
        try {
            byte[] bArr = this.inputBuffer;
            int i = this.bufferPosition;
            int i2 = bArr[i] & 255;
            this.bufferPosition = i + 1;
            return i2;
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    private int peek(int i) {
        try {
            return this.inputBuffer[this.bufferPosition + i] & 255;
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public int length() {
        return this.inputBuffer.length;
    }
}
