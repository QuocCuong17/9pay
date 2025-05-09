package com.otaliastudios.transcoder.internal.codec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.Codecs;
import com.otaliastudios.transcoder.internal.data.WriterChannel;
import com.otaliastudios.transcoder.internal.data.WriterData;
import com.otaliastudios.transcoder.internal.media.MediaCodecBuffers;
import com.otaliastudios.transcoder.internal.pipeline.QueuedStep;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: Encoder.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 ;2\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0003:\u0001;B\u0017\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0012J\u0016\u00100\u001a\u0010\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u001d\u0018\u000101H\u0016J\u000e\u00103\u001a\b\u0012\u0004\u0012\u00020\u000404H\u0014J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0002H\u0014J\u0010\u00108\u001a\u0002062\u0006\u00107\u001a\u00020\u0002H\u0014J\b\u00109\u001a\u000206H\u0002J\b\u0010:\u001a\u000206H\u0016R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010%\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010$\u001a\u0004\b&\u0010 \"\u0004\b'\u0010\"R\u000e\u0010)\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/Encoder;", "Lcom/otaliastudios/transcoder/internal/pipeline/QueuedStep;", "Lcom/otaliastudios/transcoder/internal/codec/EncoderData;", "Lcom/otaliastudios/transcoder/internal/codec/EncoderChannel;", "Lcom/otaliastudios/transcoder/internal/data/WriterData;", "Lcom/otaliastudios/transcoder/internal/data/WriterChannel;", "codecs", "Lcom/otaliastudios/transcoder/internal/Codecs;", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "(Lcom/otaliastudios/transcoder/internal/Codecs;Lcom/otaliastudios/transcoder/common/TrackType;)V", "codec", "Landroid/media/MediaCodec;", "surface", "Landroid/view/Surface;", "ownsCodecStart", "", "ownsCodecStop", "(Landroid/media/MediaCodec;Landroid/view/Surface;ZZ)V", "buffers", "Lcom/otaliastudios/transcoder/internal/media/MediaCodecBuffers;", "getBuffers", "()Lcom/otaliastudios/transcoder/internal/media/MediaCodecBuffers;", "buffers$delegate", "Lkotlin/Lazy;", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/codec/Encoder;", "<set-?>", "", "dequeuedInputs", "getDequeuedInputs", "()I", "setDequeuedInputs", "(I)V", "dequeuedInputs$delegate", "Lkotlin/properties/ReadWriteProperty;", "dequeuedOutputs", "getDequeuedOutputs", "setDequeuedOutputs", "dequeuedOutputs$delegate", "eosReceivedButNotEnqueued", "info", "Landroid/media/MediaCodec$BufferInfo;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "getSurface", "()Landroid/view/Surface;", "buffer", "Lkotlin/Pair;", "Ljava/nio/ByteBuffer;", "drain", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "enqueue", "", "data", "enqueueEos", "printDequeued", "release", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Encoder extends QueuedStep<EncoderData, EncoderChannel, WriterData, WriterChannel> implements EncoderChannel {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Encoder.class, "dequeuedInputs", "getDequeuedInputs()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Encoder.class, "dequeuedOutputs", "getDequeuedOutputs()I", 0))};
    private static final TrackMap<AtomicInteger> ID = TrackMapKt.trackMapOf(new AtomicInteger(0), new AtomicInteger(0));

    /* renamed from: buffers$delegate, reason: from kotlin metadata */
    private final Lazy buffers;
    private final Encoder channel;
    private final MediaCodec codec;

    /* renamed from: dequeuedInputs$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dequeuedInputs;

    /* renamed from: dequeuedOutputs$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dequeuedOutputs;
    private boolean eosReceivedButNotEnqueued;
    private MediaCodec.BufferInfo info;
    private final Logger log;
    private final boolean ownsCodecStop;
    private final Surface surface;
    private final TrackType type;

    @Override // com.otaliastudios.transcoder.internal.codec.EncoderChannel
    public Surface getSurface() {
        return this.surface;
    }

    public Encoder(MediaCodec codec, Surface surface, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(codec, "codec");
        this.codec = codec;
        this.surface = surface;
        this.ownsCodecStop = z2;
        TrackType trackType = getSurface() != null ? TrackType.VIDEO : TrackType.AUDIO;
        this.type = trackType;
        Logger logger = new Logger("Encoder(" + trackType + ',' + ID.get(trackType).getAndIncrement() + ')');
        this.log = logger;
        Delegates delegates = Delegates.INSTANCE;
        final int i = 0;
        this.dequeuedInputs = new ObservableProperty<Integer>(i) { // from class: com.otaliastudios.transcoder.internal.codec.Encoder$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                newValue.intValue();
                oldValue.intValue();
                this.printDequeued();
            }
        };
        Delegates delegates2 = Delegates.INSTANCE;
        final int i2 = 0;
        this.dequeuedOutputs = new ObservableProperty<Integer>(i2) { // from class: com.otaliastudios.transcoder.internal.codec.Encoder$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                newValue.intValue();
                oldValue.intValue();
                this.printDequeued();
            }
        };
        this.channel = this;
        this.buffers = LazyKt.lazy(new Function0<MediaCodecBuffers>() { // from class: com.otaliastudios.transcoder.internal.codec.Encoder$buffers$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MediaCodecBuffers invoke() {
                MediaCodec mediaCodec;
                mediaCodec = Encoder.this.codec;
                return new MediaCodecBuffers(mediaCodec);
            }
        });
        this.info = new MediaCodec.BufferInfo();
        logger.i("Encoder: ownsStart=" + z + " ownsStop=" + z2);
        if (z) {
            codec.start();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Encoder(Codecs codecs, TrackType type) {
        this(codecs.getEncoders().get(type).getFirst(), codecs.getEncoders().get(type).getSecond(), codecs.getOwnsEncoderStart().get(type).booleanValue(), codecs.getOwnsEncoderStop().get(type).booleanValue());
        Intrinsics.checkNotNullParameter(codecs, "codecs");
        Intrinsics.checkNotNullParameter(type, "type");
    }

    private final int getDequeuedInputs() {
        return ((Number) this.dequeuedInputs.getValue(this, $$delegatedProperties[0])).intValue();
    }

    private final void setDequeuedInputs(int i) {
        this.dequeuedInputs.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDequeuedOutputs() {
        return ((Number) this.dequeuedOutputs.getValue(this, $$delegatedProperties[1])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDequeuedOutputs(int i) {
        this.dequeuedOutputs.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void printDequeued() {
        this.log.v("dequeuedInputs=" + getDequeuedInputs() + " dequeuedOutputs=" + getDequeuedOutputs());
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public Encoder getChannel() {
        return this.channel;
    }

    private final MediaCodecBuffers getBuffers() {
        return (MediaCodecBuffers) this.buffers.getValue();
    }

    @Override // com.otaliastudios.transcoder.internal.codec.EncoderChannel
    public Pair<ByteBuffer, Integer> buffer() {
        int dequeueInputBuffer = this.codec.dequeueInputBuffer(0L);
        if (dequeueInputBuffer >= 0) {
            setDequeuedInputs(getDequeuedInputs() + 1);
            return TuplesKt.to(getBuffers().getInputBuffer(dequeueInputBuffer), Integer.valueOf(dequeueInputBuffer));
        }
        this.log.i("buffer() failed. dequeuedInputs=" + getDequeuedInputs() + " dequeuedOutputs=" + getDequeuedOutputs());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    public void enqueueEos(EncoderData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (getSurface() == null) {
            boolean z = this.ownsCodecStop;
            if (!z) {
                this.eosReceivedButNotEnqueued = true;
            }
            this.codec.queueInputBuffer(data.getId(), 0, 0, 0L, !z ? 0 : 4);
            setDequeuedInputs(getDequeuedInputs() - 1);
            return;
        }
        if (this.ownsCodecStop) {
            this.codec.signalEndOfInputStream();
        } else {
            this.eosReceivedButNotEnqueued = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    public void enqueue(EncoderData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (getSurface() != null) {
            return;
        }
        ByteBuffer buffer = data.getBuffer();
        if (buffer == null) {
            throw new IllegalArgumentException("Audio should always pass a buffer to Encoder.".toString());
        }
        this.codec.queueInputBuffer(data.getId(), buffer.position(), buffer.remaining(), data.getTimeUs(), 0);
        setDequeuedInputs(getDequeuedInputs() - 1);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    protected State<WriterData> drain() {
        final int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(this.info, this.eosReceivedButNotEnqueued ? 5000L : 0L);
        if (dequeueOutputBuffer == -3) {
            getBuffers().onOutputBuffersChanged();
            return State.Retry.INSTANCE;
        }
        if (dequeueOutputBuffer == -2) {
            this.log.i(Intrinsics.stringPlus("INFO_OUTPUT_FORMAT_CHANGED! format=", this.codec.getOutputFormat()));
            WriterChannel writerChannel = (WriterChannel) getNext();
            MediaFormat outputFormat = this.codec.getOutputFormat();
            Intrinsics.checkNotNullExpressionValue(outputFormat, "codec.outputFormat");
            writerChannel.handleFormat(outputFormat);
            return State.Retry.INSTANCE;
        }
        if (dequeueOutputBuffer == -1) {
            if (this.eosReceivedButNotEnqueued) {
                this.log.i("Sending fake Eos. dequeuedInputs=" + getDequeuedInputs() + " dequeuedOutputs=" + getDequeuedOutputs());
                ByteBuffer buffer = ByteBuffer.allocateDirect(0);
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                return new State.Eos(new WriterData(buffer, 0L, 0, new Function0<Unit>() { // from class: com.otaliastudios.transcoder.internal.codec.Encoder$drain$1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                }));
            }
            this.log.i("Can't dequeue output buffer: INFO_TRY_AGAIN_LATER");
            return State.Wait.INSTANCE;
        }
        if ((this.info.flags & 2) != 0) {
            this.codec.releaseOutputBuffer(dequeueOutputBuffer, false);
            return State.Retry.INSTANCE;
        }
        setDequeuedOutputs(getDequeuedOutputs() + 1);
        boolean z = (this.info.flags & 4) != 0;
        int i = this.info.flags & (-5);
        ByteBuffer outputBuffer = getBuffers().getOutputBuffer(dequeueOutputBuffer);
        Intrinsics.checkNotNullExpressionValue(outputBuffer, "buffers.getOutputBuffer(result)");
        long j = this.info.presentationTimeUs;
        outputBuffer.clear();
        outputBuffer.limit(this.info.offset + this.info.size);
        outputBuffer.position(this.info.offset);
        WriterData writerData = new WriterData(outputBuffer, j, i, new Function0<Unit>() { // from class: com.otaliastudios.transcoder.internal.codec.Encoder$drain$data$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MediaCodec mediaCodec;
                int dequeuedOutputs;
                mediaCodec = Encoder.this.codec;
                mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                Encoder encoder = Encoder.this;
                dequeuedOutputs = encoder.getDequeuedOutputs();
                encoder.setDequeuedOutputs(dequeuedOutputs - 1);
            }
        });
        return z ? new State.Eos(writerData) : new State.Ok(writerData);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.BaseStep, com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        this.log.i("release(): ownsStop=" + this.ownsCodecStop + " dequeuedInputs=" + getDequeuedInputs() + " dequeuedOutputs=" + getDequeuedOutputs());
        if (this.ownsCodecStop) {
            this.codec.stop();
        }
    }
}
