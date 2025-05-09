package com.otaliastudios.transcoder.internal.video;

import android.graphics.Bitmap;
import android.media.MediaFormat;
import android.opengl.EGL14;
import android.opengl.GLES20;
import com.otaliastudios.opengl.core.EglCore;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.surface.EglOffscreenSurface;
import com.otaliastudios.transcoder.internal.pipeline.BaseStep;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoSnapshots.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\u0010\rJ\b\u0010\u001c\u001a\u00020\fH\u0016J$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\b\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/otaliastudios/transcoder/internal/video/VideoSnapshots;", "Lcom/otaliastudios/transcoder/internal/pipeline/BaseStep;", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "format", "Landroid/media/MediaFormat;", "requests", "", "accuracyUs", "onSnapshot", "Lkotlin/Function2;", "Landroid/graphics/Bitmap;", "", "(Landroid/media/MediaFormat;Ljava/util/List;JLkotlin/jvm/functions/Function2;)V", "channel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "getChannel", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "core", "Lcom/otaliastudios/opengl/core/EglCore;", "height", "", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "", "surface", "Lcom/otaliastudios/opengl/surface/EglOffscreenSurface;", "width", "release", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class VideoSnapshots extends BaseStep<Long, Channel, Long, Channel> {
    private final long accuracyUs;
    private final Channel.Companion channel;
    private final EglCore core;
    private final int height;
    private final Logger log;
    private final Function2<Long, Bitmap, Unit> onSnapshot;
    private final List<Long> requests;
    private final EglOffscreenSurface surface;
    private final int width;

    /* JADX WARN: Multi-variable type inference failed */
    public VideoSnapshots(MediaFormat format, List<Long> requests, long j, Function2<? super Long, ? super Bitmap, Unit> onSnapshot) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(requests, "requests");
        Intrinsics.checkNotNullParameter(onSnapshot, "onSnapshot");
        this.accuracyUs = j;
        this.onSnapshot = onSnapshot;
        this.log = new Logger("VideoSnapshots");
        this.channel = Channel.INSTANCE;
        this.requests = CollectionsKt.toMutableList((Collection) requests);
        int integer = format.getInteger("width");
        this.width = integer;
        int integer2 = format.getInteger("height");
        this.height = integer2;
        EglCore eglCore = new EglCore(EGL14.EGL_NO_CONTEXT, 1);
        this.core = eglCore;
        EglOffscreenSurface eglOffscreenSurface = new EglOffscreenSurface(eglCore, integer, integer2);
        eglOffscreenSurface.makeCurrent();
        Unit unit = Unit.INSTANCE;
        this.surface = eglOffscreenSurface;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public Channel.Companion getChannel() {
        return this.channel;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<Long> step(State.Ok<Long> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (this.requests.isEmpty()) {
            return state;
        }
        long longValue = ((Number) CollectionsKt.first((List) this.requests)).longValue();
        long abs = Math.abs(longValue - state.getValue().longValue());
        if (abs < this.accuracyUs || ((state instanceof State.Eos) && longValue > state.getValue().longValue())) {
            this.log.i("Request MATCHED! expectedUs=" + longValue + " actualUs=" + state.getValue().longValue() + " deltaUs=" + abs);
            CollectionsKt.removeFirst(this.requests);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.width * this.height * 4);
            allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            ByteBuffer byteBuffer = allocateDirect;
            GLES20.glReadPixels(0, 0, this.width, this.height, 6408, 5121, byteBuffer);
            Egloo.checkGlError("glReadPixels");
            allocateDirect.rewind();
            Bitmap bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(byteBuffer);
            Function2<Long, Bitmap, Unit> function2 = this.onSnapshot;
            Long value = state.getValue();
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            function2.invoke(value, bitmap);
        } else {
            this.log.v("Request has high delta. expectedUs=" + longValue + " actualUs=" + state.getValue().longValue() + " deltaUs=" + abs);
        }
        return state;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.BaseStep, com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        this.surface.release();
        this.core.release$library_release();
    }
}
