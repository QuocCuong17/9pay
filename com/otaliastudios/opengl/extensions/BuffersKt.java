package com.otaliastudios.opengl.extensions;

import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Buffers.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\n\u0010\u0003\u001a\u00020\u0004\"\u00020\u0005\u001a\u0014\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0016\u0010\b\u001a\u00060\tj\u0002`\n2\n\u0010\u0003\u001a\u00020\u000b\"\u00020\f\u001a\u0014\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0016\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0003\u001a\u00020\u0010\"\u00020\u0007\u001a\u0016\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\n\u0010\u0003\u001a\u00020\u0014\"\u00020\u0015\u001a\u000e\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00020\u0004\u001a\u000e\u0010\u0016\u001a\u00060\tj\u0002`\n*\u00020\u000b\u001a\u000e\u0010\u0016\u001a\u00060\u000ej\u0002`\u000f*\u00020\u0010\u001a\u000e\u0010\u0016\u001a\u00060\u0012j\u0002`\u0013*\u00020\u0014¨\u0006\u0017"}, d2 = {"byteBufferOf", "Ljava/nio/ByteBuffer;", "Lcom/otaliastudios/opengl/types/ByteBuffer;", "elements", "", "", "size", "", "floatBufferOf", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "", "", "intBufferOf", "Ljava/nio/IntBuffer;", "Lcom/otaliastudios/opengl/types/IntBuffer;", "", "shortBufferOf", "Ljava/nio/ShortBuffer;", "Lcom/otaliastudios/opengl/types/ShortBuffer;", "", "", "toBuffer", "library_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class BuffersKt {
    public static final FloatBuffer toBuffer(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        FloatBuffer floatBuffer = BuffersJvmKt.floatBuffer(fArr.length);
        floatBuffer.put(fArr);
        floatBuffer.flip();
        return floatBuffer;
    }

    public static final ShortBuffer toBuffer(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        ShortBuffer shortBuffer = BuffersJvmKt.shortBuffer(sArr.length);
        shortBuffer.put(sArr);
        shortBuffer.flip();
        return shortBuffer;
    }

    public static final IntBuffer toBuffer(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        IntBuffer intBuffer = BuffersJvmKt.intBuffer(iArr.length);
        intBuffer.put(iArr);
        intBuffer.flip();
        return intBuffer;
    }

    public static final ByteBuffer toBuffer(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ByteBuffer byteBuffer = BuffersJvmKt.byteBuffer(bArr.length);
        byteBuffer.put(bArr);
        byteBuffer.flip();
        return byteBuffer;
    }

    public static final FloatBuffer floatBufferOf(float... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return toBuffer(Arrays.copyOf(elements, elements.length));
    }

    public static final ShortBuffer shortBufferOf(short... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return toBuffer(Arrays.copyOf(elements, elements.length));
    }

    public static final IntBuffer intBufferOf(int... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return toBuffer(Arrays.copyOf(elements, elements.length));
    }

    public static final ByteBuffer byteBufferOf(byte... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return toBuffer(Arrays.copyOf(elements, elements.length));
    }

    @Deprecated(message = "Do not use this.", replaceWith = @ReplaceWith(expression = "FloatBuffer(size)", imports = {}))
    public static final FloatBuffer floatBufferOf(int i) {
        return BuffersJvmKt.floatBuffer(i);
    }

    @Deprecated(message = "Do not use this.", replaceWith = @ReplaceWith(expression = "ByteBuffer(size)", imports = {}))
    public static final ByteBuffer byteBufferOf(int i) {
        return BuffersJvmKt.byteBuffer(i);
    }
}
