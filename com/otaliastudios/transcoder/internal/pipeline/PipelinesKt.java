package com.otaliastudios.transcoder.internal.pipeline;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.Codecs;
import com.otaliastudios.transcoder.internal.audio.AudioEngine;
import com.otaliastudios.transcoder.internal.codec.Decoder;
import com.otaliastudios.transcoder.internal.codec.DecoderTimer;
import com.otaliastudios.transcoder.internal.codec.Encoder;
import com.otaliastudios.transcoder.internal.data.Bridge;
import com.otaliastudios.transcoder.internal.data.Reader;
import com.otaliastudios.transcoder.internal.data.ReaderTimer;
import com.otaliastudios.transcoder.internal.data.Writer;
import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import com.otaliastudios.transcoder.internal.video.VideoPublisher;
import com.otaliastudios.transcoder.internal.video.VideoRenderer;
import com.otaliastudios.transcoder.resample.AudioResampler;
import com.otaliastudios.transcoder.sink.DataSink;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.stretch.AudioStretcher;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: pipelines.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a\b\u0010\u0010\u001a\u00020\u0001H\u0000\u001a(\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001aP\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a8\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¨\u0006\u0018"}, d2 = {"AudioPipeline", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "source", "Lcom/otaliastudios/transcoder/source/DataSource;", "sink", "Lcom/otaliastudios/transcoder/sink/DataSink;", "interpolator", "Lcom/otaliastudios/transcoder/time/TimeInterpolator;", "format", "Landroid/media/MediaFormat;", "codecs", "Lcom/otaliastudios/transcoder/internal/Codecs;", "audioStretcher", "Lcom/otaliastudios/transcoder/stretch/AudioStretcher;", "audioResampler", "Lcom/otaliastudios/transcoder/resample/AudioResampler;", "EmptyPipeline", "PassThroughPipeline", "track", "Lcom/otaliastudios/transcoder/common/TrackType;", "RegularPipeline", "videoRotation", "", "VideoPipeline", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class PipelinesKt {

    /* compiled from: pipelines.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TrackType.values().length];
            iArr[TrackType.VIDEO.ordinal()] = 1;
            iArr[TrackType.AUDIO.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Pipeline EmptyPipeline() {
        return Pipeline.Companion.build$lib_release$default(Pipeline.INSTANCE, "Empty", null, 2, null);
    }

    public static final Pipeline PassThroughPipeline(final TrackType track, final DataSource source, final DataSink sink, final TimeInterpolator interpolator) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(interpolator, "interpolator");
        return Pipeline.INSTANCE.build$lib_release("PassThrough(" + track + ')', new Function0<Pipeline.Builder<?, Channel>>() { // from class: com.otaliastudios.transcoder.internal.pipeline.PipelinesKt$PassThroughPipeline$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Pipeline.Builder<?, Channel> invoke() {
                Pipeline.Builder plus = PipelineKt.plus(new Reader(DataSource.this, track), new ReaderTimer(track, interpolator));
                MediaFormat trackFormat = DataSource.this.getTrackFormat(track);
                Intrinsics.checkNotNull(trackFormat);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "source.getTrackFormat(track)!!");
                return plus.plus(new Bridge(trackFormat)).plus(new Writer(sink, track));
            }
        });
    }

    public static final Pipeline RegularPipeline(TrackType track, DataSource source, DataSink sink, TimeInterpolator interpolator, MediaFormat format, Codecs codecs, int i, AudioStretcher audioStretcher, AudioResampler audioResampler) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(interpolator, "interpolator");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(codecs, "codecs");
        Intrinsics.checkNotNullParameter(audioStretcher, "audioStretcher");
        Intrinsics.checkNotNullParameter(audioResampler, "audioResampler");
        int i2 = WhenMappings.$EnumSwitchMapping$0[track.ordinal()];
        if (i2 == 1) {
            return VideoPipeline(source, sink, interpolator, format, codecs, i);
        }
        if (i2 == 2) {
            return AudioPipeline(source, sink, interpolator, format, codecs, audioStretcher, audioResampler);
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final Pipeline VideoPipeline(final DataSource dataSource, final DataSink dataSink, final TimeInterpolator timeInterpolator, final MediaFormat mediaFormat, final Codecs codecs, final int i) {
        return Pipeline.INSTANCE.build$lib_release("Video", new Function0<Pipeline.Builder<?, Channel>>() { // from class: com.otaliastudios.transcoder.internal.pipeline.PipelinesKt$VideoPipeline$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Pipeline.Builder<?, Channel> invoke() {
                Reader reader = new Reader(DataSource.this, TrackType.VIDEO);
                MediaFormat trackFormat = DataSource.this.getTrackFormat(TrackType.VIDEO);
                Intrinsics.checkNotNull(trackFormat);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "source.getTrackFormat(TrackType.VIDEO)!!");
                return PipelineKt.plus(reader, new Decoder(trackFormat, true)).plus(new DecoderTimer(TrackType.VIDEO, timeInterpolator)).plus(new VideoRenderer(DataSource.this.getOrientation(), i, mediaFormat, false, 8, null)).plus(new VideoPublisher()).plus(new Encoder(codecs, TrackType.VIDEO)).plus(new Writer(dataSink, TrackType.VIDEO));
            }
        });
    }

    private static final Pipeline AudioPipeline(final DataSource dataSource, final DataSink dataSink, final TimeInterpolator timeInterpolator, final MediaFormat mediaFormat, final Codecs codecs, final AudioStretcher audioStretcher, final AudioResampler audioResampler) {
        return Pipeline.INSTANCE.build$lib_release("Audio", new Function0<Pipeline.Builder<?, Channel>>() { // from class: com.otaliastudios.transcoder.internal.pipeline.PipelinesKt$AudioPipeline$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Pipeline.Builder<?, Channel> invoke() {
                Reader reader = new Reader(DataSource.this, TrackType.AUDIO);
                MediaFormat trackFormat = DataSource.this.getTrackFormat(TrackType.AUDIO);
                Intrinsics.checkNotNull(trackFormat);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "source.getTrackFormat(TrackType.AUDIO)!!");
                return PipelineKt.plus(reader, new Decoder(trackFormat, true)).plus(new DecoderTimer(TrackType.AUDIO, timeInterpolator)).plus(new AudioEngine(audioStretcher, audioResampler, mediaFormat)).plus(new Encoder(codecs, TrackType.AUDIO)).plus(new Writer(dataSink, TrackType.AUDIO));
            }
        });
    }
}
