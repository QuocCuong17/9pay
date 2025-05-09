package com.otaliastudios.transcoder.internal.transcode;

import com.otaliastudios.transcoder.TranscoderOptions;
import com.otaliastudios.transcoder.internal.DataSources;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import com.otaliastudios.transcoder.resample.AudioResampler;
import com.otaliastudios.transcoder.sink.DataSink;
import com.otaliastudios.transcoder.stretch.AudioStretcher;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import com.otaliastudios.transcoder.validator.Validator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranscodeEngine.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b \u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u001c\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007H&J\b\u0010\t\u001a\u00020\nH&¨\u0006\f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/transcode/TranscodeEngine;", "", "()V", "cleanup", "", "transcode", "progress", "Lkotlin/Function1;", "", "validate", "", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class TranscodeEngine {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger log = new Logger("TranscodeEngine");

    @JvmStatic
    public static final void transcode(TranscoderOptions transcoderOptions) {
        INSTANCE.transcode(transcoderOptions);
    }

    public abstract void cleanup();

    public abstract void transcode(Function1<? super Double, Unit> progress);

    public abstract boolean validate();

    /* compiled from: TranscodeEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/transcode/TranscodeEngine$Companion;", "", "()V", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "transcode", "", "options", "Lcom/otaliastudios/transcoder/TranscoderOptions;", "isInterrupted", "", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final boolean isInterrupted(Throwable th) {
            Throwable cause;
            if (th instanceof InterruptedException) {
                return true;
            }
            if (Intrinsics.areEqual(th, th.getCause()) || (cause = th.getCause()) == null) {
                return false;
            }
            return isInterrupted(cause);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00bb  */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v3, types: [com.otaliastudios.transcoder.internal.transcode.TranscodeEngine] */
        /* JADX WARN: Type inference failed for: r1v4 */
        @JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void transcode(TranscoderOptions options) {
            Exception e;
            DefaultTranscodeEngine defaultTranscodeEngine;
            Intrinsics.checkNotNullParameter(options, "options");
            TranscodeEngine.log.i("transcode(): called...");
            final TranscodeDispatcher transcodeDispatcher = new TranscodeDispatcher(options);
            ?? r1 = 0;
            try {
                try {
                    DataSources dataSources = new DataSources(options);
                    DataSink dataSink = options.getDataSink();
                    TrackMap trackMapOf = TrackMapKt.trackMapOf(options.getVideoTrackStrategy(), options.getAudioTrackStrategy());
                    Validator validator = options.getValidator();
                    int videoRotation = options.getVideoRotation();
                    TimeInterpolator timeInterpolator = options.getTimeInterpolator();
                    AudioStretcher audioStretcher = options.getAudioStretcher();
                    AudioResampler audioResampler = options.getAudioResampler();
                    Intrinsics.checkNotNullExpressionValue(dataSink, "dataSink");
                    Intrinsics.checkNotNullExpressionValue(validator, "validator");
                    Intrinsics.checkNotNullExpressionValue(audioStretcher, "audioStretcher");
                    Intrinsics.checkNotNullExpressionValue(audioResampler, "audioResampler");
                    Intrinsics.checkNotNullExpressionValue(timeInterpolator, "timeInterpolator");
                    defaultTranscodeEngine = new DefaultTranscodeEngine(dataSources, dataSink, trackMapOf, validator, videoRotation, audioStretcher, audioResampler, timeInterpolator);
                    try {
                        if (!defaultTranscodeEngine.validate()) {
                            transcodeDispatcher.dispatchSuccess(1);
                        } else {
                            defaultTranscodeEngine.transcode(new Function1<Double, Unit>() { // from class: com.otaliastudios.transcoder.internal.transcode.TranscodeEngine$Companion$transcode$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Double d) {
                                    invoke(d.doubleValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(double d) {
                                    TranscodeDispatcher.this.dispatchProgress(d);
                                }
                            });
                            transcodeDispatcher.dispatchSuccess(0);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        if (isInterrupted(e)) {
                            TranscodeEngine.log.i("Transcode canceled.", e);
                            transcodeDispatcher.dispatchCancel();
                            if (defaultTranscodeEngine == null) {
                                return;
                            }
                            defaultTranscodeEngine.cleanup();
                        }
                        TranscodeEngine.log.e("Unexpected error while transcoding.", e);
                        transcodeDispatcher.dispatchFailure(e);
                        throw e;
                    }
                } catch (Throwable th) {
                    th = th;
                    r1 = options;
                    if (r1 != 0) {
                        r1.cleanup();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                defaultTranscodeEngine = null;
            } catch (Throwable th2) {
                th = th2;
                if (r1 != 0) {
                }
                throw th;
            }
            defaultTranscodeEngine.cleanup();
        }
    }
}
