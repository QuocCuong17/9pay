package org.apache.commons.io.output;

import java.io.IOException;

/* loaded from: classes5.dex */
public class NullAppendable implements Appendable {
    public static final NullAppendable INSTANCE = new NullAppendable();

    @Override // java.lang.Appendable
    public Appendable append(char c) throws IOException {
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence) throws IOException {
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
        return this;
    }

    private NullAppendable() {
    }
}
