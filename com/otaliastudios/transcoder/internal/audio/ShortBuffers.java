package com.otaliastudios.transcoder.internal.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: shorts.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/ShortBuffers;", "", "()V", "map", "", "", "Ljava/nio/ShortBuffer;", "acquire", "name", "size", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ShortBuffers {
    private final Map<String, ShortBuffer> map = new LinkedHashMap();

    public final ShortBuffer acquire(String name, int size) {
        Intrinsics.checkNotNullParameter(name, "name");
        ShortBuffer shortBuffer = this.map.get(name);
        if (shortBuffer == null || shortBuffer.capacity() < size) {
            shortBuffer = ByteBuffer.allocateDirect(size * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        }
        Intrinsics.checkNotNull(shortBuffer);
        ShortBuffer shortBuffer2 = shortBuffer;
        shortBuffer2.clear();
        shortBuffer2.limit(size);
        this.map.put(name, shortBuffer);
        return shortBuffer2;
    }
}
