package com.otaliastudios.transcoder.internal.audio;

import android.media.MediaFormat;
import android.view.Surface;
import com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer;
import com.otaliastudios.transcoder.internal.codec.DecoderChannel;
import com.otaliastudios.transcoder.internal.codec.DecoderData;
import com.otaliastudios.transcoder.internal.codec.DecoderTimerData;
import com.otaliastudios.transcoder.internal.codec.EncoderChannel;
import com.otaliastudios.transcoder.internal.codec.EncoderData;
import com.otaliastudios.transcoder.internal.pipeline.QueuedStep;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.resample.AudioResampler;
import com.otaliastudios.transcoder.stretch.AudioStretcher;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioEngine.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 )2\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0003:\u0001)B\u001d\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040 H\u0014J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0002H\u0014J\u0010\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0002H\u0014J\u0010\u0010%\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u000bH\u0016J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u000bH\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0019\u001a\u00020\u001a*\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u00020\u001a*\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001c¨\u0006*"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/AudioEngine;", "Lcom/otaliastudios/transcoder/internal/pipeline/QueuedStep;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderData;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderChannel;", "Lcom/otaliastudios/transcoder/internal/codec/EncoderData;", "Lcom/otaliastudios/transcoder/internal/codec/EncoderChannel;", "stretcher", "Lcom/otaliastudios/transcoder/stretch/AudioStretcher;", "resampler", "Lcom/otaliastudios/transcoder/resample/AudioResampler;", "targetFormat", "Landroid/media/MediaFormat;", "(Lcom/otaliastudios/transcoder/stretch/AudioStretcher;Lcom/otaliastudios/transcoder/resample/AudioResampler;Landroid/media/MediaFormat;)V", "buffers", "Lcom/otaliastudios/transcoder/internal/audio/ShortBuffers;", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/audio/AudioEngine;", "chunks", "Lcom/otaliastudios/transcoder/internal/audio/ChunkQueue;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "rawFormat", "remixer", "Lcom/otaliastudios/transcoder/internal/audio/remix/AudioRemixer;", "channels", "", "getChannels", "(Landroid/media/MediaFormat;)I", "sampleRate", "getSampleRate", "drain", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "enqueue", "", "data", "enqueueEos", "handleRawFormat", "handleSourceFormat", "Landroid/view/Surface;", "sourceFormat", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class AudioEngine extends QueuedStep<DecoderData, DecoderChannel, EncoderData, EncoderChannel> implements DecoderChannel {
    private static final AtomicInteger ID = new AtomicInteger(0);
    private final ShortBuffers buffers;
    private final AudioEngine channel;
    private ChunkQueue chunks;
    private final Logger log;
    private MediaFormat rawFormat;
    private AudioRemixer remixer;
    private final AudioResampler resampler;
    private final AudioStretcher stretcher;
    private final MediaFormat targetFormat;

    @Override // com.otaliastudios.transcoder.internal.codec.DecoderChannel
    public Surface handleSourceFormat(MediaFormat sourceFormat) {
        Intrinsics.checkNotNullParameter(sourceFormat, "sourceFormat");
        return null;
    }

    public AudioEngine(AudioStretcher stretcher, AudioResampler resampler, MediaFormat targetFormat) {
        Intrinsics.checkNotNullParameter(stretcher, "stretcher");
        Intrinsics.checkNotNullParameter(resampler, "resampler");
        Intrinsics.checkNotNullParameter(targetFormat, "targetFormat");
        this.stretcher = stretcher;
        this.resampler = resampler;
        this.targetFormat = targetFormat;
        this.log = new Logger("AudioEngine(" + ID.getAndIncrement() + ')');
        this.channel = this;
        this.buffers = new ShortBuffers();
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public AudioEngine getChannel() {
        return this.channel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSampleRate(MediaFormat mediaFormat) {
        return mediaFormat.getInteger("sample-rate");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getChannels(MediaFormat mediaFormat) {
        return mediaFormat.getInteger("channel-count");
    }

    @Override // com.otaliastudios.transcoder.internal.codec.DecoderChannel
    public void handleRawFormat(MediaFormat rawFormat) {
        Intrinsics.checkNotNullParameter(rawFormat, "rawFormat");
        this.log.i("handleRawFormat(" + rawFormat + ')');
        this.rawFormat = rawFormat;
        this.remixer = AudioRemixer.INSTANCE.get$lib_release(getChannels(rawFormat), getChannels(this.targetFormat));
        this.chunks = new ChunkQueue(getSampleRate(rawFormat), getChannels(rawFormat));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    public void enqueueEos(DecoderData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.log.i("enqueueEos()");
        data.getRelease().invoke(false);
        ChunkQueue chunkQueue = this.chunks;
        if (chunkQueue == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chunks");
            chunkQueue = null;
        }
        chunkQueue.enqueueEos();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    public void enqueue(final DecoderData data) {
        ChunkQueue chunkQueue;
        Intrinsics.checkNotNullParameter(data, "data");
        DecoderTimerData decoderTimerData = data instanceof DecoderTimerData ? (DecoderTimerData) data : null;
        double timeStretch = decoderTimerData == null ? 1.0d : decoderTimerData.getTimeStretch();
        ChunkQueue chunkQueue2 = this.chunks;
        if (chunkQueue2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chunks");
            chunkQueue = null;
        } else {
            chunkQueue = chunkQueue2;
        }
        ShortBuffer asShortBuffer = data.getBuffer().asShortBuffer();
        Intrinsics.checkNotNullExpressionValue(asShortBuffer, "data.buffer.asShortBuffer()");
        chunkQueue.enqueue(asShortBuffer, data.getTimeUs(), timeStretch, new Function0<Unit>() { // from class: com.otaliastudios.transcoder.internal.audio.AudioEngine$enqueue$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                DecoderData.this.getRelease().invoke(false);
            }
        });
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.QueuedStep
    protected State<EncoderData> drain() {
        ChunkQueue chunkQueue = this.chunks;
        ChunkQueue chunkQueue2 = null;
        if (chunkQueue == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chunks");
            chunkQueue = null;
        }
        if (chunkQueue.isEmpty()) {
            this.log.i("drain(): no chunks, waiting...");
            return State.Wait.INSTANCE;
        }
        Pair<ByteBuffer, Integer> buffer = ((EncoderChannel) getNext()).buffer();
        if (buffer == null) {
            this.log.i("drain(): no next buffer, waiting...");
            return State.Wait.INSTANCE;
        }
        final ByteBuffer component1 = buffer.component1();
        final int intValue = buffer.component2().intValue();
        final ShortBuffer asShortBuffer = component1.asShortBuffer();
        ChunkQueue chunkQueue3 = this.chunks;
        if (chunkQueue3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chunks");
        } else {
            chunkQueue2 = chunkQueue3;
        }
        return (State) chunkQueue2.drain(new State.Eos(new EncoderData(component1, intValue, 0L)), new Function3<ShortBuffer, Long, Double, State.Ok<EncoderData>>() { // from class: com.otaliastudios.transcoder.internal.audio.AudioEngine$drain$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ State.Ok<EncoderData> invoke(ShortBuffer shortBuffer, Long l, Double d) {
                return invoke(shortBuffer, l.longValue(), d.doubleValue());
            }

            public final State.Ok<EncoderData> invoke(ShortBuffer inBuffer, long j, double d) {
                AudioRemixer audioRemixer;
                MediaFormat mediaFormat;
                int sampleRate;
                MediaFormat mediaFormat2;
                int sampleRate2;
                ShortBuffers shortBuffers;
                AudioStretcher audioStretcher;
                MediaFormat mediaFormat3;
                int channels;
                AudioRemixer audioRemixer2;
                ShortBuffers shortBuffers2;
                AudioRemixer audioRemixer3;
                AudioResampler audioResampler;
                MediaFormat mediaFormat4;
                int sampleRate3;
                MediaFormat mediaFormat5;
                int sampleRate4;
                MediaFormat mediaFormat6;
                int channels2;
                Intrinsics.checkNotNullParameter(inBuffer, "inBuffer");
                int remaining = asShortBuffer.remaining();
                int remaining2 = inBuffer.remaining();
                double d2 = remaining2;
                double ceil = Math.ceil(d2 * d);
                audioRemixer = this.remixer;
                MediaFormat mediaFormat7 = null;
                if (audioRemixer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("remixer");
                    audioRemixer = null;
                }
                double remixedSize = audioRemixer.getRemixedSize((int) ceil);
                AudioEngine audioEngine = this;
                mediaFormat = audioEngine.targetFormat;
                sampleRate = audioEngine.getSampleRate(mediaFormat);
                double d3 = remixedSize * sampleRate;
                AudioEngine audioEngine2 = this;
                mediaFormat2 = audioEngine2.rawFormat;
                if (mediaFormat2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rawFormat");
                    mediaFormat2 = null;
                }
                sampleRate2 = audioEngine2.getSampleRate(mediaFormat2);
                double ceil2 = Math.ceil(d3 / sampleRate2);
                double d4 = remaining;
                if (ceil2 > d4) {
                    remaining2 = (int) Math.floor(d4 / (ceil2 / d2));
                }
                inBuffer.limit(inBuffer.position() + remaining2);
                double ceil3 = Math.ceil(remaining2 * d);
                shortBuffers = this.buffers;
                int i = (int) ceil3;
                ShortBuffer acquire = shortBuffers.acquire("stretch", i);
                audioStretcher = this.stretcher;
                AudioEngine audioEngine3 = this;
                mediaFormat3 = audioEngine3.rawFormat;
                if (mediaFormat3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rawFormat");
                    mediaFormat3 = null;
                }
                channels = audioEngine3.getChannels(mediaFormat3);
                audioStretcher.stretch(inBuffer, acquire, channels);
                acquire.flip();
                audioRemixer2 = this.remixer;
                if (audioRemixer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("remixer");
                    audioRemixer2 = null;
                }
                int remixedSize2 = audioRemixer2.getRemixedSize(i);
                shortBuffers2 = this.buffers;
                ShortBuffer acquire2 = shortBuffers2.acquire("remix", remixedSize2);
                audioRemixer3 = this.remixer;
                if (audioRemixer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("remixer");
                    audioRemixer3 = null;
                }
                audioRemixer3.remix(acquire, acquire2);
                acquire2.flip();
                audioResampler = this.resampler;
                AudioEngine audioEngine4 = this;
                mediaFormat4 = audioEngine4.rawFormat;
                if (mediaFormat4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rawFormat");
                } else {
                    mediaFormat7 = mediaFormat4;
                }
                sampleRate3 = audioEngine4.getSampleRate(mediaFormat7);
                ShortBuffer shortBuffer = asShortBuffer;
                AudioEngine audioEngine5 = this;
                mediaFormat5 = audioEngine5.targetFormat;
                sampleRate4 = audioEngine5.getSampleRate(mediaFormat5);
                AudioEngine audioEngine6 = this;
                mediaFormat6 = audioEngine6.targetFormat;
                channels2 = audioEngine6.getChannels(mediaFormat6);
                audioResampler.resample(acquire2, sampleRate3, shortBuffer, sampleRate4, channels2);
                asShortBuffer.flip();
                component1.clear();
                component1.limit(asShortBuffer.limit() * 2);
                component1.position(asShortBuffer.position() * 2);
                return new State.Ok<>(new EncoderData(component1, intValue, j));
            }
        });
    }
}
