package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.lang.Appendable;
import java.util.Objects;

/* loaded from: classes5.dex */
public class AppendableWriter<T extends Appendable> extends Writer {
    private final T appendable;

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
    }

    public AppendableWriter(T t) {
        this.appendable = t;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        this.appendable.append(c);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) throws IOException {
        this.appendable.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) throws IOException {
        this.appendable.append(charSequence, i, i2);
        return this;
    }

    public T getAppendable() {
        return this.appendable;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        Objects.requireNonNull(cArr, "Character array is missing");
        if (i2 >= 0 && i + i2 <= cArr.length) {
            for (int i3 = 0; i3 < i2; i3++) {
                this.appendable.append(cArr[i + i3]);
            }
            return;
        }
        throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        this.appendable.append((char) i);
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        Objects.requireNonNull(str, "String is missing");
        this.appendable.append(str, i, i2 + i);
    }
}
