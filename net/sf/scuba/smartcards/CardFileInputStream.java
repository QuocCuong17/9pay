package net.sf.scuba.smartcards;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public class CardFileInputStream extends InputStream {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");
    private final byte[] buffer;
    private int bufferLength;
    private int fileLength;
    private FileSystemStructured fs;
    private int markedOffset;
    private int offsetBufferInFile;
    private int offsetInBuffer;
    private FileInfo[] path;

    public CardFileInputStream(int i, FileSystemStructured fileSystemStructured) throws CardServiceException {
        this.fs = fileSystemStructured;
        synchronized (fileSystemStructured) {
            FileInfo[] selectedPath = fileSystemStructured.getSelectedPath();
            if (selectedPath == null || selectedPath.length < 1) {
                throw new CardServiceException("No valid file selected, path = " + Arrays.toString(selectedPath));
            }
            FileInfo[] fileInfoArr = new FileInfo[selectedPath.length];
            this.path = fileInfoArr;
            System.arraycopy(selectedPath, 0, fileInfoArr, 0, selectedPath.length);
            this.fileLength = selectedPath[selectedPath.length - 1].getFileLength();
            this.buffer = new byte[i];
            this.bufferLength = 0;
            this.offsetBufferInFile = 0;
            this.offsetInBuffer = 0;
            this.markedOffset = -1;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        synchronized (this.fs) {
            try {
                try {
                    if (!Arrays.equals(this.path, this.fs.getSelectedPath())) {
                        for (FileInfo fileInfo : this.path) {
                            this.fs.selectFile(fileInfo.getFID());
                        }
                    }
                    int i = this.offsetBufferInFile;
                    int i2 = this.offsetInBuffer;
                    int i3 = i + i2;
                    int i4 = this.fileLength;
                    if (i3 >= i4) {
                        return -1;
                    }
                    if (i2 >= this.bufferLength) {
                        int min = Math.min(this.buffer.length, i4 - i3);
                        try {
                            int i5 = this.offsetBufferInFile + this.bufferLength;
                            int fillBufferFromFile = fillBufferFromFile(this.path, i5, min);
                            this.offsetBufferInFile = i5;
                            this.offsetInBuffer = 0;
                            this.bufferLength = fillBufferFromFile;
                        } catch (CardServiceException e) {
                            throw new IOException("Unexpected exception", e);
                        } catch (Exception e2) {
                            throw new IOException("Unexpected exception", e2);
                        }
                    }
                    byte[] bArr = this.buffer;
                    int i6 = this.offsetInBuffer;
                    int i7 = bArr[i6] & 255;
                    this.offsetInBuffer = i6 + 1;
                    return i7;
                } catch (CardServiceException e3) {
                    LOGGER.log(Level.WARNING, "Unexpected exception", (Throwable) e3);
                    throw new IOException("Unexpected exception", e3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        synchronized (this.fs) {
            int i = this.bufferLength;
            int i2 = this.offsetInBuffer;
            if (j < i - i2) {
                this.offsetInBuffer = (int) (i2 + j);
            } else {
                this.offsetBufferInFile = (int) (this.offsetBufferInFile + i2 + j);
                this.offsetInBuffer = 0;
                this.bufferLength = 0;
            }
        }
        return j;
    }

    @Override // java.io.InputStream
    public synchronized int available() {
        return this.bufferLength - this.offsetInBuffer;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        synchronized (this.fs) {
            this.markedOffset = this.offsetBufferInFile + this.offsetInBuffer;
        }
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        synchronized (this.fs) {
            int i = this.markedOffset;
            if (i < 0) {
                throw new IOException("Mark not set");
            }
            this.offsetBufferInFile = i;
            this.offsetInBuffer = 0;
            this.bufferLength = 0;
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        synchronized (this.fs) {
        }
        return true;
    }

    public int getLength() {
        return this.fileLength;
    }

    public int getPostion() {
        return this.offsetBufferInFile + this.offsetInBuffer;
    }

    private int fillBufferFromFile(FileInfo[] fileInfoArr, int i, int i2) throws CardServiceException {
        int length;
        synchronized (this.fs) {
            if (i2 > this.buffer.length) {
                throw new IllegalArgumentException("length too big");
            }
            if (!Arrays.equals(this.fs.getSelectedPath(), fileInfoArr)) {
                for (FileInfo fileInfo : fileInfoArr) {
                    this.fs.selectFile(fileInfo.getFID());
                }
            }
            byte[] readBinary = this.fs.readBinary(i, i2);
            System.arraycopy(readBinary, 0, this.buffer, 0, readBinary.length);
            length = readBinary.length;
        }
        return length;
    }
}
