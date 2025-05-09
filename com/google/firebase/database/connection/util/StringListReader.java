package com.google.firebase.database.connection.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class StringListReader extends Reader {
    private List<String> strings;
    private boolean closed = false;
    private int charPos;
    private int markedCharPos = this.charPos;
    private int stringListPos;
    private int markedStringListPos = this.stringListPos;
    private boolean frozen = false;

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    public StringListReader() {
        this.strings = null;
        this.strings = new ArrayList();
    }

    public void addString(String str) {
        if (this.frozen) {
            throw new IllegalStateException("Trying to add string after reading");
        }
        if (str.length() > 0) {
            this.strings.add(str);
        }
    }

    public void freeze() {
        if (this.frozen) {
            throw new IllegalStateException("Trying to freeze frozen StringListReader");
        }
        this.frozen = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.strings.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.charPos = this.markedCharPos;
        this.stringListPos = this.markedStringListPos;
    }

    private String currentString() {
        if (this.stringListPos < this.strings.size()) {
            return this.strings.get(this.stringListPos);
        }
        return null;
    }

    private int currentStringRemainingChars() {
        String currentString = currentString();
        if (currentString == null) {
            return 0;
        }
        return currentString.length() - this.charPos;
    }

    private void checkState() throws IOException {
        if (this.closed) {
            throw new IOException("Stream already closed");
        }
        if (!this.frozen) {
            throw new IOException("Reader needs to be frozen before read operations can be called");
        }
    }

    private long advance(long j) {
        long j2 = 0;
        while (this.stringListPos < this.strings.size() && j2 < j) {
            long j3 = j - j2;
            long currentStringRemainingChars = currentStringRemainingChars();
            if (j3 < currentStringRemainingChars) {
                this.charPos = (int) (this.charPos + j3);
                j2 += j3;
            } else {
                j2 += currentStringRemainingChars;
                this.charPos = 0;
                this.stringListPos++;
            }
        }
        return j2;
    }

    @Override // java.io.Reader, java.lang.Readable
    public int read(CharBuffer charBuffer) throws IOException {
        checkState();
        int remaining = charBuffer.remaining();
        String currentString = currentString();
        int i = 0;
        while (remaining > 0 && currentString != null) {
            int min = Math.min(currentString.length() - this.charPos, remaining);
            String str = this.strings.get(this.stringListPos);
            int i2 = this.charPos;
            charBuffer.put(str, i2, i2 + min);
            remaining -= min;
            i += min;
            advance(min);
            currentString = currentString();
        }
        if (i > 0 || currentString != null) {
            return i;
        }
        return -1;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        checkState();
        String currentString = currentString();
        if (currentString == null) {
            return -1;
        }
        char charAt = currentString.charAt(this.charPos);
        advance(1L);
        return charAt;
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        checkState();
        return advance(j);
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        checkState();
        return true;
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        checkState();
        this.markedCharPos = this.charPos;
        this.markedStringListPos = this.stringListPos;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        checkState();
        String currentString = currentString();
        int i3 = 0;
        while (currentString != null && i3 < i2) {
            int min = Math.min(currentStringRemainingChars(), i2 - i3);
            int i4 = this.charPos;
            currentString.getChars(i4, i4 + min, cArr, i + i3);
            i3 += min;
            advance(min);
            currentString = currentString();
        }
        if (i3 > 0 || currentString != null) {
            return i3;
        }
        return -1;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        checkState();
        this.closed = true;
    }
}
