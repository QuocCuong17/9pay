package com.otaliastudios.transcoder.internal.codec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackTypeKt;
import com.otaliastudios.transcoder.internal.data.ReaderChannel;
import com.otaliastudios.transcoder.internal.data.ReaderData;
import com.otaliastudios.transcoder.internal.media.MediaCodecBuffers;
import com.otaliastudios.transcoder.internal.pipeline.QueuedStep;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import com.otaliastudios.transcoder.source.DataSource;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: Decoder.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u0000 62\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0003:\u00016B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010)\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u0017\u0018\u00010*H\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0014J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0002H\u0014J\u0010\u00101\u001a\u00020/2\u0006\u00100\u001a\u00020\u0002H\u0014J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\u0005H\u0016J\b\u00104\u001a\u00020/H\u0002J\b\u00105\u001a\u00020/H\u0016R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR+\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\u001e\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/Decoder;", "Lcom/otaliastudios/transcoder/internal/pipeline/QueuedStep;", "Lcom/otaliastudios/transcoder/internal/data/ReaderData;", "Lcom/otaliastudios/transcoder/internal/data/ReaderChannel;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderData;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderChannel;", "format", "Landroid/media/MediaFormat;", "continuous", "", "(Landroid/media/MediaFormat;Z)V", "buffers", "Lcom/otaliastudios/transcoder/internal/media/MediaCodecBuffers;", "getBuffers", "()Lcom/otaliastudios/transcoder/internal/media/MediaCodecBuffers;", "buffers$delegate", "Lkotlin/Lazy;", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/codec/Decoder;", "codec", "Landroid/media/MediaCodec;", "<set-?>", "", "dequeuedInputs", "getDequeuedInputs", "()I", "setDequeuedInputs", "(I)V", "dequeuedInputs$delegate", "Lkotlin/properties/ReadWriteProperty;", "dequeuedOutputs", "getDequeuedOutputs", "setDequeuedOutputs", "dequeuedOutputs$delegate", "dropper", "Lcom/otaliastudios/transcoder/internal/codec/DecoderDropper;", "info", "Landroid/media/MediaCodec$BufferInfo;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "buffer", "Lkotlin/Pair;", "Ljava/nio/ByteBuffer;", "drain", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "enqueue", "", "data", "enqueueEos", "initialize", "next", "printDequeued", "release", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Decoder extends QueuedStep<ReaderData, ReaderChannel, DecoderData, DecoderChannel> implements ReaderChannel {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Decoder.class, "dequeuedInputs", "getDequeuedInputs()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Decoder.class, "dequeuedOutputs", "getDequeuedOutputs()I", 0))};
    private static final TrackMap<AtomicInteger> ID = TrackMapKt.trackMapOf(new AtomicInteger(0), new AtomicInteger(0));

    /* renamed from: buffers$delegate, reason: from kotlin metadata */
    private final Lazy buffers;
    private final Decoder channel;
    private final MediaCodec codec;

    /* renamed from: dequeuedInputs$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dequeuedInputs;

    /* renamed from: dequeuedOutputs$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dequeuedOutputs;
    private final DecoderDropper dropper;
    private final MediaFormat format;
    private MediaCodec.BufferInfo info;
    private final Logger log;

    /* JADX INFO: Access modifiers changed from: private */
    public final void printDequeued() {
    }

    public Decoder(MediaFormat format, boolean z) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.format = format;
        this.log = new Logger("Decoder(" + TrackTypeKt.getTrackType(format) + ',' + ID.get(TrackTypeKt.getTrackType(format)).getAndIncrement() + ')');
        this.channel = this;
        String string = format.getString("mime");
        Intrinsics.checkNotNull(string);
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(string);
        Intrinsics.checkNotNullExpressionValue(createDecoderByType, "createDecoderByType(form…(MediaFormat.KEY_MIME)!!)");
        this.codec = createDecoderByType;
        this.buffers = LazyKt.lazy(new Function0<MediaCodecBuffers>() { // from class: com.otaliastudios.transcoder.internal.codec.Decoder$buffers$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MediaCodecBuffers invoke() {
                MediaCodec mediaCodec;
                mediaCodec = Decoder.this.codec;
                return new MediaCodecBuffers(mediaCodec);
            }
        });
        this.info = new MediaCodec.BufferInfo();
        this.dropper = new DecoderDropper(z);
        Delegates delegates = Delegates.INSTANCE;
        final int i = 0;
        this.dequeuedInputs = new ObservableProperty<Integer>(i) { // from class: com.otaliastudios.transcoder.internal.codec.Decoder$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                newValue.intValue();
                oldValue.intValue();
                this.printDequeued();
            }
        };
        Delegates delegates2 = Delegates.INSTANCE;
        this.dequeuedOutputs = new ObservableProperty<Integer>(i) { // from class: com.otaliastudios.transcoder.internal.codec.Decoder$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                newValue.intValue();
                oldValue.intValue();
                this.printDequeued();
            }
        };
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public Decoder getChannel() {
        return this.channel;
    }

    private final MediaCodecBuffers getBuffers() {
        return (MediaCodecBuffers) this.buffers.getValue();
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

    @Override // com.otaliastudios.transcoder.internal.pipeline.BaseStep, com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(DecoderChannel next) {
        Intrinsics.checkNotNullParameter(next, "next");
        super.initialize((Decoder) next);
        this.log.i("initialize()");
        this.codec.configure(this.format, next.handleSourceFormat(this.format), (MediaCrypto) null, 0);
        this.codec.start();
    }

    @Override // com.otaliastudios.transcoder.internal.data.ReaderChannel
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
    public void enqueueEos(ReaderData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.log.i("enqueueEos()!");
        setDequeuedInputs(getDequeuedInputs() - 1);
        this.codec.queueInputBuffer(data.getId(), 0, 0, 0L, 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    public void enqueue(ReaderData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        setDequeuedInputs(getDequeuedInputs() - 1);
        DataSource.Chunk chunk = data.getChunk();
        this.codec.queueInputBuffer(data.getId(), chunk.buffer.position(), chunk.buffer.remaining(), chunk.timeUs, chunk.keyframe ? 1 : 0);
        this.dropper.input(chunk.timeUs, chunk.render);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    protected State<DecoderData> drain() {
        State.Wait wait;
        final int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(this.info, 0L);
        if (dequeueOutputBuffer == -3) {
            this.log.i("drain(): got INFO_OUTPUT_BUFFERS_CHANGED, retrying.");
            getBuffers().onOutputBuffersChanged();
            return State.Retry.INSTANCE;
        }
        if (dequeueOutputBuffer == -2) {
            this.log.i(Intrinsics.stringPlus("drain(): got INFO_OUTPUT_FORMAT_CHANGED, handling format and retrying. format=", this.codec.getOutputFormat()));
            DecoderChannel decoderChannel = (DecoderChannel) getNext();
            MediaFormat outputFormat = this.codec.getOutputFormat();
            Intrinsics.checkNotNullExpressionValue(outputFormat, "codec.outputFormat");
            decoderChannel.handleRawFormat(outputFormat);
            return State.Retry.INSTANCE;
        }
        if (dequeueOutputBuffer == -1) {
            this.log.i("drain(): got INFO_TRY_AGAIN_LATER, waiting.");
            return State.Wait.INSTANCE;
        }
        boolean z = (this.info.flags & 4) != 0;
        Long output = z ? 0L : this.dropper.output(this.info.presentationTimeUs);
        if (output != null) {
            setDequeuedOutputs(getDequeuedOutputs() + 1);
            ByteBuffer outputBuffer = getBuffers().getOutputBuffer(dequeueOutputBuffer);
            Intrinsics.checkNotNullExpressionValue(outputBuffer, "buffers.getOutputBuffer(result)");
            DecoderData decoderData = new DecoderData(outputBuffer, output.longValue(), new Function1<Boolean, Unit>() { // from class: com.otaliastudios.transcoder.internal.codec.Decoder$drain$data$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2) {
                    MediaCodec mediaCodec;
                    int dequeuedOutputs;
                    mediaCodec = Decoder.this.codec;
                    mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, z2);
                    Decoder decoder = Decoder.this;
                    dequeuedOutputs = decoder.getDequeuedOutputs();
                    decoder.setDequeuedOutputs(dequeuedOutputs - 1);
                }
            });
            wait = z ? new State.Eos(decoderData) : new State.Ok(decoderData);
        } else {
            this.codec.releaseOutputBuffer(dequeueOutputBuffer, false);
            wait = State.Wait.INSTANCE;
        }
        this.log.v(Intrinsics.stringPlus("drain(): returning ", wait));
        return wait;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.BaseStep, com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        this.log.i("release(): releasing codec. dequeuedInputs=" + getDequeuedInputs() + " dequeuedOutputs=" + getDequeuedOutputs());
        this.codec.stop();
        this.codec.release();
    }
}
