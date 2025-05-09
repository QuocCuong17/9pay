package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes5.dex */
public class CharSequenceReader extends Reader implements Serializable {
    private static final long serialVersionUID = 3724187752191401220L;
    private final CharSequence charSequence;
    private final Integer end;
    private int idx;
    private int mark;
    private final int start;

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    public CharSequenceReader(CharSequence charSequence) {
        this(charSequence, 0);
    }

    public CharSequenceReader(CharSequence charSequence, int i) {
        this(charSequence, i, Integer.MAX_VALUE);
    }

    public CharSequenceReader(String str, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Start index is less than zero: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("End index is less than start " + i + ": " + i2);
        }
        this.charSequence = str == null ? "" : str;
        this.start = i;
        this.end = Integer.valueOf(i2);
        this.idx = i;
        this.mark = i;
    }

    private int start() {
        return Math.min(this.charSequence.length(), this.start);
    }

    private int end() {
        int length = this.charSequence.length();
        Integer num = this.end;
        return Math.min(length, num == null ? Integer.MAX_VALUE : num.intValue());
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i = this.start;
        this.idx = i;
        this.mark = i;
    }

    @Override // java.io.Reader
    public boolean ready() {
        return this.idx < end();
    }

    @Override // java.io.Reader
    public void mark(int i) {
        this.mark = this.idx;
    }

    @Override // java.io.Reader
    public int read() {
        if (this.idx >= end()) {
            return -1;
        }
        CharSequence charSequence = this.charSequence;
        int i = this.idx;
        this.idx = i + 1;
        return charSequence.charAt(i);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) {
        if (this.idx >= end()) {
            return -1;
        }
        Objects.requireNonNull(cArr, "array");
        if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
        }
        CharSequence charSequence = this.charSequence;
        if (charSequence instanceof String) {
            int min = Math.min(i2, end() - this.idx);
            String str = (String) this.charSequence;
            int i3 = this.idx;
            str.getChars(i3, i3 + min, cArr, i);
            this.idx += min;
            return min;
        }
        if (charSequence instanceof StringBuilder) {
            int min2 = Math.min(i2, end() - this.idx);
            StringBuilder sb = (StringBuilder) this.charSequence;
            int i4 = this.idx;
            sb.getChars(i4, i4 + min2, cArr, i);
            this.idx += min2;
            return min2;
        }
        if (charSequence instanceof StringBuffer) {
            int min3 = Math.min(i2, end() - this.idx);
            StringBuffer stringBuffer = (StringBuffer) this.charSequence;
            int i5 = this.idx;
            stringBuffer.getChars(i5, i5 + min3, cArr, i);
            this.idx += min3;
            return min3;
        }
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            int read = read();
            if (read == -1) {
                return i6;
            }
            cArr[i + i7] = (char) read;
            i6++;
        }
        return i6;
    }

    @Override // java.io.Reader
    public void reset() {
        this.idx = this.mark;
    }

    @Override // java.io.Reader
    public long skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Number of characters to skip is less than zero: " + j);
        }
        if (this.idx >= end()) {
            return 0L;
        }
        int min = (int) Math.min(end(), this.idx + j);
        int i = min - this.idx;
        this.idx = min;
        return i;
    }

    public String toString() {
        return this.charSequence.subSequence(start(), end()).toString();
    }
}
