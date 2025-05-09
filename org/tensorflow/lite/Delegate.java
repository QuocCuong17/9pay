package org.tensorflow.lite;

import java.io.Closeable;

/* loaded from: classes6.dex */
public interface Delegate extends Closeable {

    /* renamed from: org.tensorflow.lite.Delegate$-CC, reason: invalid class name */
    /* loaded from: classes6.dex */
    public final /* synthetic */ class CC {
        public static void $default$close(Delegate _this) {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long getNativeHandle();
}
