package com.otaliastudios.transcoder.internal.video;

import android.media.MediaFormat;
import android.view.Surface;
import com.otaliastudios.transcoder.internal.codec.DecoderChannel;
import com.otaliastudios.transcoder.internal.codec.DecoderData;
import com.otaliastudios.transcoder.internal.media.MediaFormatConstants;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.pipeline.Step;
import com.otaliastudios.transcoder.internal.utils.Logger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoRenderer.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0003B'\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\nH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\nH\u0016J\b\u0010!\u001a\u00020\u001cH\u0016J$\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020%2\u0006\u0010&\u001a\u00020\fH\u0016R\u0014\u0010\u000e\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/otaliastudios/transcoder/internal/video/VideoRenderer;", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderData;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderChannel;", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "sourceRotation", "", "extraRotation", "targetFormat", "Landroid/media/MediaFormat;", "flipY", "", "(IILandroid/media/MediaFormat;Z)V", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/video/VideoRenderer;", "frameDrawer", "Lcom/otaliastudios/transcoder/internal/video/FrameDrawer;", "getFrameDrawer", "()Lcom/otaliastudios/transcoder/internal/video/FrameDrawer;", "frameDrawer$delegate", "Lkotlin/Lazy;", "frameDropper", "Lcom/otaliastudios/transcoder/internal/video/FrameDropper;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "handleRawFormat", "", "rawFormat", "handleSourceFormat", "Landroid/view/Surface;", "sourceFormat", "release", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class VideoRenderer implements Step<DecoderData, DecoderChannel, Long, Channel>, DecoderChannel {
    private final VideoRenderer channel;
    private final int extraRotation;

    /* renamed from: frameDrawer$delegate, reason: from kotlin metadata */
    private final Lazy frameDrawer;
    private FrameDropper frameDropper;
    private final Logger log;
    private final int sourceRotation;
    private final MediaFormat targetFormat;

    @Override // com.otaliastudios.transcoder.internal.codec.DecoderChannel
    public void handleRawFormat(MediaFormat rawFormat) {
        Intrinsics.checkNotNullParameter(rawFormat, "rawFormat");
    }

    public VideoRenderer(int i, int i2, MediaFormat targetFormat, final boolean z) {
        Intrinsics.checkNotNullParameter(targetFormat, "targetFormat");
        this.sourceRotation = i;
        this.extraRotation = i2;
        this.targetFormat = targetFormat;
        Logger logger = new Logger("VideoRenderer");
        this.log = logger;
        this.channel = this;
        this.frameDrawer = LazyKt.lazy(new Function0<FrameDrawer>() { // from class: com.otaliastudios.transcoder.internal.video.VideoRenderer$frameDrawer$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameDrawer invoke() {
                FrameDrawer frameDrawer = new FrameDrawer();
                frameDrawer.setFlipY(z);
                return frameDrawer;
            }
        });
        int integer = targetFormat.getInteger("width");
        int integer2 = targetFormat.getInteger("height");
        boolean z2 = i2 % 180 != 0;
        logger.e("FrameDrawerEncoder: size=" + integer + '-' + integer2 + ", flipping=" + z2);
        targetFormat.setInteger("width", z2 ? integer2 : integer);
        targetFormat.setInteger("height", z2 ? integer : integer2);
    }

    public /* synthetic */ VideoRenderer(int i, int i2, MediaFormat mediaFormat, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, mediaFormat, (i3 & 8) != 0 ? false : z);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(Channel channel) {
        Step.DefaultImpls.initialize(this, channel);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public DecoderChannel getChannel() {
        return this.channel;
    }

    private final FrameDrawer getFrameDrawer() {
        return (FrameDrawer) this.frameDrawer.getValue();
    }

    @Override // com.otaliastudios.transcoder.internal.codec.DecoderChannel
    public Surface handleSourceFormat(MediaFormat sourceFormat) {
        Object m1202constructorimpl;
        float f;
        Intrinsics.checkNotNullParameter(sourceFormat, "sourceFormat");
        this.log.i("handleSourceFormat(" + sourceFormat + ')');
        try {
            Result.Companion companion = Result.INSTANCE;
            VideoRenderer videoRenderer = this;
            m1202constructorimpl = Result.m1202constructorimpl(Integer.valueOf(sourceFormat.getInteger(MediaFormatConstants.KEY_ROTATION_DEGREES)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1205exceptionOrNullimpl(m1202constructorimpl) != null) {
            m1202constructorimpl = 0;
        }
        int intValue = ((Number) m1202constructorimpl).intValue();
        if (intValue != this.sourceRotation) {
            throw new IllegalStateException(("Unexpected difference in rotation. DataSource=" + this.sourceRotation + ", MediaFormat=" + intValue).toString());
        }
        sourceFormat.setInteger(MediaFormatConstants.KEY_ROTATION_DEGREES, 0);
        int i = (intValue + this.extraRotation) % 360;
        getFrameDrawer().setRotation(i);
        boolean z = i % 180 != 0;
        float integer = sourceFormat.getInteger("width") / sourceFormat.getInteger("height");
        MediaFormat mediaFormat = this.targetFormat;
        float integer2 = (z ? mediaFormat.getInteger("height") : mediaFormat.getInteger("width")) / (z ? this.targetFormat.getInteger("width") : this.targetFormat.getInteger("height"));
        float f2 = 1.0f;
        if (integer > integer2) {
            float f3 = integer / integer2;
            f = 1.0f;
            f2 = f3;
        } else {
            f = integer < integer2 ? integer2 / integer : 1.0f;
        }
        getFrameDrawer().setScale(f2, f);
        this.frameDropper = FrameDropperKt.FrameDropper(sourceFormat.getInteger("frame-rate"), this.targetFormat.getInteger("frame-rate"));
        Surface surface = getFrameDrawer().getSurface();
        Intrinsics.checkNotNullExpressionValue(surface, "frameDrawer.surface");
        return surface;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<Long> step(State.Ok<DecoderData> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (state instanceof State.Eos) {
            state.getValue().getRelease().invoke(false);
            return new State.Eos(0L);
        }
        FrameDropper frameDropper = this.frameDropper;
        if (frameDropper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameDropper");
            frameDropper = null;
        }
        if (!frameDropper.shouldRender(state.getValue().getTimeUs())) {
            state.getValue().getRelease().invoke(false);
            return State.Wait.INSTANCE;
        }
        state.getValue().getRelease().invoke(true);
        getFrameDrawer().drawFrame();
        return new State.Ok(Long.valueOf(state.getValue().getTimeUs()));
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        getFrameDrawer().release();
    }
}
