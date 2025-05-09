package com.otaliastudios.transcoder.internal.transcode;

import android.media.MediaFormat;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.Codecs;
import com.otaliastudios.transcoder.internal.DataSources;
import com.otaliastudios.transcoder.internal.Segment;
import com.otaliastudios.transcoder.internal.Segments;
import com.otaliastudios.transcoder.internal.Timer;
import com.otaliastudios.transcoder.internal.Tracks;
import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import com.otaliastudios.transcoder.internal.pipeline.PipelinesKt;
import com.otaliastudios.transcoder.internal.utils.EosKt;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.resample.AudioResampler;
import com.otaliastudios.transcoder.sink.DataSink;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.strategy.TrackStrategy;
import com.otaliastudios.transcoder.stretch.AudioStretcher;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import com.otaliastudios.transcoder.validator.Validator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: DefaultTranscodeEngine.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 /2\u00020\u0001:\u0001/BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\b\u0010\u001e\u001a\u00020\u001fH\u0016J(\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u001c\u0010)\u001a\u00020\u001f2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u001f0+H\u0016J\b\u0010-\u001a\u00020.H\u0016R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/otaliastudios/transcoder/internal/transcode/DefaultTranscodeEngine;", "Lcom/otaliastudios/transcoder/internal/transcode/TranscodeEngine;", "dataSources", "Lcom/otaliastudios/transcoder/internal/DataSources;", "dataSink", "Lcom/otaliastudios/transcoder/sink/DataSink;", "strategies", "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "Lcom/otaliastudios/transcoder/strategy/TrackStrategy;", "validator", "Lcom/otaliastudios/transcoder/validator/Validator;", "videoRotation", "", "audioStretcher", "Lcom/otaliastudios/transcoder/stretch/AudioStretcher;", "audioResampler", "Lcom/otaliastudios/transcoder/resample/AudioResampler;", "interpolator", "Lcom/otaliastudios/transcoder/time/TimeInterpolator;", "(Lcom/otaliastudios/transcoder/internal/DataSources;Lcom/otaliastudios/transcoder/sink/DataSink;Lcom/otaliastudios/transcoder/internal/utils/TrackMap;Lcom/otaliastudios/transcoder/validator/Validator;ILcom/otaliastudios/transcoder/stretch/AudioStretcher;Lcom/otaliastudios/transcoder/resample/AudioResampler;Lcom/otaliastudios/transcoder/time/TimeInterpolator;)V", "codecs", "Lcom/otaliastudios/transcoder/internal/Codecs;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "segments", "Lcom/otaliastudios/transcoder/internal/Segments;", WorkflowModule.Properties.Section.Component.Type.TIMER, "Lcom/otaliastudios/transcoder/internal/Timer;", "tracks", "Lcom/otaliastudios/transcoder/internal/Tracks;", "cleanup", "", "createPipeline", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", FirebaseAnalytics.Param.INDEX, "status", "Lcom/otaliastudios/transcoder/common/TrackStatus;", "outputFormat", "Landroid/media/MediaFormat;", "transcode", "progress", "Lkotlin/Function1;", "", "validate", "", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DefaultTranscodeEngine extends TranscodeEngine {
    private final AudioResampler audioResampler;
    private final AudioStretcher audioStretcher;
    private final Codecs codecs;
    private final DataSink dataSink;
    private final DataSources dataSources;
    private final Logger log;
    private final Segments segments;
    private final Timer timer;
    private final Tracks tracks;
    private final Validator validator;
    private final int videoRotation;
    private static final long WAIT_MS = 10;
    private static final long PROGRESS_LOOPS = 10;

    /* compiled from: DefaultTranscodeEngine.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TrackStatus.values().length];
            iArr[TrackStatus.ABSENT.ordinal()] = 1;
            iArr[TrackStatus.REMOVING.ordinal()] = 2;
            iArr[TrackStatus.PASS_THROUGH.ordinal()] = 3;
            iArr[TrackStatus.COMPRESSING.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DefaultTranscodeEngine(DataSources dataSources, DataSink dataSink, TrackMap<TrackStrategy> strategies, Validator validator, int i, AudioStretcher audioStretcher, AudioResampler audioResampler, TimeInterpolator interpolator) {
        Intrinsics.checkNotNullParameter(dataSources, "dataSources");
        Intrinsics.checkNotNullParameter(dataSink, "dataSink");
        Intrinsics.checkNotNullParameter(strategies, "strategies");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Intrinsics.checkNotNullParameter(audioStretcher, "audioStretcher");
        Intrinsics.checkNotNullParameter(audioResampler, "audioResampler");
        Intrinsics.checkNotNullParameter(interpolator, "interpolator");
        this.dataSources = dataSources;
        this.dataSink = dataSink;
        this.validator = validator;
        this.videoRotation = i;
        this.audioStretcher = audioStretcher;
        this.audioResampler = audioResampler;
        Logger logger = new Logger("TranscodeEngine");
        this.log = logger;
        Tracks tracks = new Tracks(strategies, dataSources, i, false);
        this.tracks = tracks;
        Segments segments = new Segments(dataSources, tracks, new DefaultTranscodeEngine$segments$1(this));
        this.segments = segments;
        this.timer = new Timer(interpolator, dataSources, tracks, segments.getCurrentIndex());
        this.codecs = new Codecs(dataSources, tracks, segments.getCurrentIndex());
        logger.i("Created Tracks, Segments, Timer...");
        dataSink.setOrientation(0);
        double[] dArr = (double[]) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(dataSources.all()), new Function1<DataSource, double[]>() { // from class: com.otaliastudios.transcoder.internal.transcode.DefaultTranscodeEngine$location$1
            @Override // kotlin.jvm.functions.Function1
            public final double[] invoke(DataSource it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getLocation();
            }
        }));
        if (dArr != null) {
            dataSink.setLocation(dArr[0], dArr[1]);
        }
        dataSink.setTrackStatus(TrackType.VIDEO, tracks.getAll().getVideo());
        dataSink.setTrackStatus(TrackType.AUDIO, tracks.getAll().getAudio());
        logger.i("Set up the DataSink...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pipeline createPipeline(final TrackType type, final int index, TrackStatus status, MediaFormat outputFormat) {
        this.log.w("createPipeline(" + type + ", " + index + ", " + status + "), format=" + outputFormat);
        TimeInterpolator interpolator = this.timer.interpolator(type, index);
        final List<? extends DataSource> list = this.dataSources.get(type);
        DataSource forcingEos = EosKt.forcingEos(list.get(index), new Function0<Boolean>() { // from class: com.otaliastudios.transcoder.internal.transcode.DefaultTranscodeEngine$createPipeline$source$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Timer timer;
                Timer timer2;
                timer = DefaultTranscodeEngine.this.timer;
                long longValue = timer.getPositionUs().get(type).longValue();
                timer2 = DefaultTranscodeEngine.this.timer;
                return Boolean.valueOf(longValue > timer2.getTotalDurationUs() + 100);
            }
        });
        DataSink ignoringEos = EosKt.ignoringEos(this.dataSink, new Function0<Boolean>() { // from class: com.otaliastudios.transcoder.internal.transcode.DefaultTranscodeEngine$createPipeline$sink$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(index < CollectionsKt.getLastIndex(list));
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i == 1) {
            return PipelinesKt.EmptyPipeline();
        }
        if (i == 2) {
            return PipelinesKt.EmptyPipeline();
        }
        if (i == 3) {
            return PipelinesKt.PassThroughPipeline(type, forcingEos, ignoringEos, interpolator);
        }
        if (i == 4) {
            return PipelinesKt.RegularPipeline(type, forcingEos, ignoringEos, interpolator, outputFormat, this.codecs, this.videoRotation, this.audioStretcher, this.audioResampler);
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.otaliastudios.transcoder.internal.transcode.TranscodeEngine
    public boolean validate() {
        if (this.validator.validate(this.tracks.getAll().getVideo(), this.tracks.getAll().getAudio())) {
            return true;
        }
        this.log.i("Validator has decided that the input is fine and transcoding is not necessary.");
        return false;
    }

    @Override // com.otaliastudios.transcoder.internal.transcode.TranscodeEngine
    public void transcode(Function1<? super Double, Unit> progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
        this.log.i("transcode(): about to start, durationUs=" + this.timer.getTotalDurationUs() + ", audioUs=" + this.timer.getDurationUs().audioOrNull() + ", videoUs=" + this.timer.getDurationUs().videoOrNull());
        long j = 0L;
        while (true) {
            Segment next = this.segments.next(TrackType.AUDIO);
            Segment next2 = this.segments.next(TrackType.VIDEO);
            boolean z = false;
            boolean advance = (next == null ? false : next.advance()) | (next2 == null ? false : next2.advance());
            if (!advance && !this.segments.hasNext()) {
                z = true;
            }
            this.log.v("transcode(): executed step=" + j + " advanced=" + advance + " completed=" + z);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            if (z) {
                progress.invoke(Double.valueOf(1.0d));
                this.dataSink.stop();
                return;
            }
            if (!advance) {
                Thread.sleep(WAIT_MS);
            }
            j++;
            if (j % PROGRESS_LOOPS == 0) {
                double doubleValue = this.timer.getProgress().getAudio().doubleValue();
                double doubleValue2 = this.timer.getProgress().getVideo().doubleValue();
                this.log.v("transcode(): got progress, video=" + doubleValue2 + " audio=" + doubleValue);
                progress.invoke(Double.valueOf((doubleValue2 + doubleValue) / ((double) this.tracks.getActive().getSize())));
            }
        }
    }

    @Override // com.otaliastudios.transcoder.internal.transcode.TranscodeEngine
    public void cleanup() {
        try {
            Result.Companion companion = Result.INSTANCE;
            DefaultTranscodeEngine defaultTranscodeEngine = this;
            this.segments.release();
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            DefaultTranscodeEngine defaultTranscodeEngine2 = this;
            this.dataSink.release();
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            DefaultTranscodeEngine defaultTranscodeEngine3 = this;
            this.dataSources.release();
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th3));
        }
        try {
            Result.Companion companion7 = Result.INSTANCE;
            DefaultTranscodeEngine defaultTranscodeEngine4 = this;
            this.codecs.release();
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th4) {
            Result.Companion companion8 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th4));
        }
    }
}
