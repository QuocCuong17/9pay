package com.otaliastudios.transcoder.internal.video;

import android.opengl.EGL14;
import android.view.Surface;
import com.otaliastudios.opengl.core.EglCore;
import com.otaliastudios.opengl.surface.EglWindowSurface;
import com.otaliastudios.transcoder.internal.codec.EncoderChannel;
import com.otaliastudios.transcoder.internal.codec.EncoderData;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.pipeline.Step;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPublisher.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/otaliastudios/transcoder/internal/video/VideoPublisher;", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Lcom/otaliastudios/transcoder/internal/codec/EncoderData;", "Lcom/otaliastudios/transcoder/internal/codec/EncoderChannel;", "()V", "channel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "getChannel", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "core", "Lcom/otaliastudios/opengl/core/EglCore;", "surface", "Lcom/otaliastudios/opengl/surface/EglWindowSurface;", "initialize", "", "next", "release", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class VideoPublisher implements Step<Long, Channel, EncoderData, EncoderChannel> {
    private final Channel.Companion channel = Channel.INSTANCE;
    private final EglCore core = new EglCore(EGL14.EGL_NO_CONTEXT, 1);
    private EglWindowSurface surface;

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public Channel getChannel() {
        return this.channel;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(EncoderChannel next) {
        Intrinsics.checkNotNullParameter(next, "next");
        Step.DefaultImpls.initialize(this, next);
        EglCore eglCore = this.core;
        Surface surface = next.getSurface();
        Intrinsics.checkNotNull(surface);
        EglWindowSurface eglWindowSurface = new EglWindowSurface(eglCore, surface, false);
        this.surface = eglWindowSurface;
        eglWindowSurface.makeCurrent();
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<EncoderData> step(State.Ok<Long> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (state instanceof State.Eos) {
            return new State.Eos(EncoderData.INSTANCE.getEmpty());
        }
        EglWindowSurface eglWindowSurface = this.surface;
        EglWindowSurface eglWindowSurface2 = null;
        if (eglWindowSurface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surface");
            eglWindowSurface = null;
        }
        eglWindowSurface.setPresentationTime(state.getValue().longValue() * 1000);
        EglWindowSurface eglWindowSurface3 = this.surface;
        if (eglWindowSurface3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surface");
        } else {
            eglWindowSurface2 = eglWindowSurface3;
        }
        eglWindowSurface2.swapBuffers();
        return new State.Ok(EncoderData.INSTANCE.getEmpty());
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        EglWindowSurface eglWindowSurface = this.surface;
        if (eglWindowSurface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surface");
            eglWindowSurface = null;
        }
        eglWindowSurface.release();
        this.core.release$library_release();
    }
}
