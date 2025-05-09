package com.otaliastudios.opengl.surface;

import android.graphics.Bitmap;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import com.otaliastudios.opengl.core.EglCore;
import com.otaliastudios.opengl.core.Egloo;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglSurface.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\b\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\f¨\u0006\u0015"}, d2 = {"Lcom/otaliastudios/opengl/surface/EglSurface;", "Lcom/otaliastudios/opengl/surface/EglNativeSurface;", "eglCore", "Lcom/otaliastudios/opengl/core/EglCore;", "eglSurface", "Landroid/opengl/EGLSurface;", "(Lcom/otaliastudios/opengl/core/EglCore;Landroid/opengl/EGLSurface;)V", "Lcom/otaliastudios/opengl/internal/EglSurface;", "(Lcom/otaliastudios/opengl/core/EglCore;Lcom/otaliastudios/opengl/internal/EglSurface;)V", "toByteArray", "", "format", "Landroid/graphics/Bitmap$CompressFormat;", "toFile", "", "file", "Ljava/io/File;", "toOutputStream", "stream", "Ljava/io/OutputStream;", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class EglSurface extends EglNativeSurface {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EglSurface(EglCore eglCore, com.otaliastudios.opengl.internal.EglSurface eglSurface) {
        super(eglCore, eglSurface);
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    protected EglSurface(EglCore eglCore, EGLSurface eglSurface) {
        this(eglCore, new com.otaliastudios.opengl.internal.EglSurface(eglSurface));
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
    }

    public static /* synthetic */ void toOutputStream$default(EglSurface eglSurface, OutputStream outputStream, Bitmap.CompressFormat compressFormat, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toOutputStream");
        }
        if ((i & 2) != 0) {
            compressFormat = Bitmap.CompressFormat.PNG;
        }
        eglSurface.toOutputStream(outputStream, compressFormat);
    }

    public final void toOutputStream(OutputStream stream, Bitmap.CompressFormat format) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(format, "format");
        if (!isCurrent()) {
            throw new RuntimeException("Expected EGL context/surface is not current");
        }
        int width = getWidth();
        int height = getHeight();
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(width * height * 4);
        allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer byteBuffer = allocateDirect;
        GLES20.glReadPixels(0, 0, width, height, 6408, 5121, byteBuffer);
        Egloo.checkGlError("glReadPixels");
        allocateDirect.rewind();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(byteBuffer);
        createBitmap.compress(format, 90, stream);
        createBitmap.recycle();
    }

    public static /* synthetic */ void toFile$default(EglSurface eglSurface, File file, Bitmap.CompressFormat compressFormat, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toFile");
        }
        if ((i & 2) != 0) {
            compressFormat = Bitmap.CompressFormat.PNG;
        }
        eglSurface.toFile(file, compressFormat);
    }

    public final void toFile(File file, Bitmap.CompressFormat format) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(format, "format");
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file.toString()));
            try {
                toOutputStream(bufferedOutputStream2, format);
                bufferedOutputStream2.close();
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static /* synthetic */ byte[] toByteArray$default(EglSurface eglSurface, Bitmap.CompressFormat compressFormat, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toByteArray");
        }
        if ((i & 1) != 0) {
            compressFormat = Bitmap.CompressFormat.PNG;
        }
        return eglSurface.toByteArray(compressFormat);
    }

    public final byte[] toByteArray(Bitmap.CompressFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            toOutputStream(byteArrayOutputStream2, format);
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "it.toByteArray()");
            CloseableKt.closeFinally(byteArrayOutputStream, null);
            return byteArray;
        } finally {
        }
    }

    /* compiled from: EglSurface.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/otaliastudios/opengl/surface/EglSurface$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "getTAG", "()Ljava/lang/String;", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        protected static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }

        protected final String getTAG() {
            return EglSurface.TAG;
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("EglSurface", "EglSurface::class.java.simpleName");
        TAG = "EglSurface";
    }
}
