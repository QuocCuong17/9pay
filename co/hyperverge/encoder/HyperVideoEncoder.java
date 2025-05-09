package co.hyperverge.encoder;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.view.Surface;
import co.hyperverge.encoder.utils.extensions.DeviceExtsKt;
import co.hyperverge.encoder.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HyperVideoEncoder.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0012\b\u0000\u0018\u0000 32\u00020\u0001:\u000234B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J(\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000fH\u0002J \u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\fH\u0002J\u0017\u0010*\u001a\u00020\u001e2\b\u0010+\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b,J\b\u0010-\u001a\u00020\u001eH\u0002J%\u0010.\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u0014H\u0000¢\u0006\u0002\b0J\r\u00101\u001a\u00020\u001eH\u0000¢\u0006\u0002\b2R\u0014\u0010\u0005\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lco/hyperverge/encoder/HyperVideoEncoder;", "", "mCallback", "Lco/hyperverge/encoder/HyperVideoEncoder$HyperVideoEncoderCallback;", "(Lco/hyperverge/encoder/HyperVideoEncoder$HyperVideoEncoderCallback;)V", "isEncodingStarted", "", "isEncodingStarted$hv_bitmaps_to_video_release", "()Z", "isStarted", "mEncodeQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Landroid/graphics/Bitmap;", "mFrameSync", "mGenerateIndex", "", "mNewFrameLatch", "Ljava/util/concurrent/CountDownLatch;", "mNoMoreFrames", "mOutputFile", "Ljava/io/File;", "mTrackIndex", "mediaCodec", "Landroid/media/MediaCodec;", "mediaMuxer", "Landroid/media/MediaMuxer;", "computePresentationTime", "", "frameIndex", "encode", "", "encodeYUV420SP", "yuv420sp", "", "argb", "", "width", "height", "getNV21", "inputWidth", "inputHeight", "scaled", "queueFrame", "bitmap", "queueFrame$hv_bitmaps_to_video_release", "release", "startEncoding", "outputFile", "startEncoding$hv_bitmaps_to_video_release", "stopEncoding", "stopEncoding$hv_bitmaps_to_video_release", "Companion", "HyperVideoEncoderCallback", "hv-bitmaps-to-video_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperVideoEncoder {
    private static final int BIT_RATE = 1000000;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FRAME_RATE = 8;
    private static final int I_FRAME_INTERVAL = 1;
    private static final String MIME_TYPE = "video/avc";
    private static int mHeight;
    private static int mWidth;
    private boolean isStarted;
    private final HyperVideoEncoderCallback mCallback;
    private ConcurrentLinkedQueue<Bitmap> mEncodeQueue;
    private final Object mFrameSync;
    private int mGenerateIndex;
    private CountDownLatch mNewFrameLatch;
    private boolean mNoMoreFrames;
    private File mOutputFile;
    private int mTrackIndex;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;

    /* compiled from: HyperVideoEncoder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lco/hyperverge/encoder/HyperVideoEncoder$HyperVideoEncoderCallback;", "", "getContext", "Landroid/content/Context;", "onEncodingComplete", "", "outputFile", "Ljava/io/File;", "onLowStorage", "hv-bitmaps-to-video_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public interface HyperVideoEncoderCallback {
        /* renamed from: getContext */
        Context get$context();

        void onEncodingComplete(File outputFile);

        void onLowStorage();
    }

    public HyperVideoEncoder(HyperVideoEncoderCallback mCallback) {
        Intrinsics.checkNotNullParameter(mCallback, "mCallback");
        this.mCallback = mCallback;
        this.mEncodeQueue = new ConcurrentLinkedQueue<>();
        this.mFrameSync = new Object();
        this.mNewFrameLatch = new CountDownLatch(0);
    }

    public final boolean isEncodingStarted$hv_bitmaps_to_video_release() {
        return this.isStarted && !this.mNoMoreFrames;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x040a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startEncoding$hv_bitmaps_to_video_release(int width, int height, File outputFile) {
        String className;
        Object obj;
        Object invoke;
        Class<?> cls;
        Object m1202constructorimpl;
        String className2;
        Object obj2;
        Object invoke2;
        Class<?> cls2;
        Object m1202constructorimpl2;
        Object m1202constructorimpl3;
        Throwable m1205exceptionOrNullimpl;
        HyperVideoEncoder hyperVideoEncoder;
        Object m1202constructorimpl4;
        Throwable m1205exceptionOrNullimpl2;
        String className3;
        String str;
        Object obj3;
        Object invoke3;
        String canonicalName;
        String className4;
        String str2;
        Object obj4;
        Object invoke4;
        String canonicalName2;
        String className5;
        String str3;
        String substringAfterLast$default;
        String str4;
        Object obj5;
        Object invoke5;
        String canonicalName3;
        String className6;
        Object obj6;
        Object invoke6;
        Class<?> cls3;
        String str5 = "N/A";
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        Companion companion = INSTANCE;
        mWidth = (width / 2) * 2;
        mHeight = (height / 2) * 2;
        this.mOutputFile = outputFile;
        try {
            String canonicalPath = outputFile.getCanonicalPath();
            Intrinsics.checkNotNullExpressionValue(canonicalPath, "{\n            outputFile.canonicalPath\n        }");
            MediaCodecInfo selectCodec = companion.selectCodec();
            if (selectCodec != null) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    HyperVideoEncoder hyperVideoEncoder2 = this;
                    m1202constructorimpl = Result.m1202constructorimpl(MediaCodec.createByCodecName(selectCodec.getName()));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                Throwable m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl3 == null) {
                    this.mediaCodec = (MediaCodec) m1202constructorimpl;
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", mWidth, mHeight);
                    Intrinsics.checkNotNullExpressionValue(createVideoFormat, "createVideoFormat(MIME_TYPE, mWidth, mHeight)");
                    createVideoFormat.setInteger("bitrate", 1000000);
                    createVideoFormat.setInteger("frame-rate", 8);
                    createVideoFormat.setInteger("color-format", 21);
                    createVideoFormat.setInteger("i-frame-interval", 1);
                    createVideoFormat.setLong("max-input-size", Long.MAX_VALUE);
                    try {
                        Result.Companion companion4 = Result.INSTANCE;
                        HyperVideoEncoder hyperVideoEncoder3 = this;
                        MediaCodec mediaCodec = this.mediaCodec;
                        if (mediaCodec == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                            mediaCodec = null;
                        }
                        mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                        m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        Result.Companion companion5 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    Throwable m1205exceptionOrNullimpl4 = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                    if (m1205exceptionOrNullimpl4 == null) {
                        String str6 = "N/A";
                        try {
                            Result.Companion companion6 = Result.INSTANCE;
                            try {
                                HyperVideoEncoder hyperVideoEncoder4 = this;
                                MediaCodec mediaCodec2 = this.mediaCodec;
                                if (mediaCodec2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                    mediaCodec2 = null;
                                }
                                mediaCodec2.start();
                                m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th3) {
                                th = th3;
                                Result.Companion companion7 = Result.INSTANCE;
                                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                                if (m1205exceptionOrNullimpl != null) {
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                        if (m1205exceptionOrNullimpl != null) {
                            try {
                                Result.Companion companion8 = Result.INSTANCE;
                                hyperVideoEncoder = this;
                                try {
                                    HyperVideoEncoder hyperVideoEncoder5 = hyperVideoEncoder;
                                    m1202constructorimpl4 = Result.m1202constructorimpl(new MediaMuxer(canonicalPath, 0));
                                } catch (Throwable th5) {
                                    th = th5;
                                    Result.Companion companion9 = Result.INSTANCE;
                                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                    if (m1205exceptionOrNullimpl2 != null) {
                                    }
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                hyperVideoEncoder = this;
                            }
                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                            if (m1205exceptionOrNullimpl2 != null) {
                                hyperVideoEncoder.mediaMuxer = (MediaMuxer) m1202constructorimpl4;
                                hyperVideoEncoder.isStarted = true;
                                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), Dispatchers.getMain(), null, new HyperVideoEncoder$startEncoding$10(hyperVideoEncoder, null), 2, null);
                                return;
                            }
                            HyperLogger.Level level = HyperLogger.Level.ERROR;
                            HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb = new StringBuilder();
                            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                            String substringAfterLast$default2 = (stackTraceElement == null || (className3 = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default2 == null) {
                                Class<?> cls4 = getClass();
                                if (cls4 != null && (canonicalName = cls4.getCanonicalName()) != null) {
                                    str6 = canonicalName;
                                }
                            } else {
                                str6 = substringAfterLast$default2;
                            }
                            Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str6);
                            if (matcher.find()) {
                                str = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                            } else {
                                str = str6;
                            }
                            Unit unit = Unit.INSTANCE;
                            if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str = str.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            }
                            sb.append(str);
                            sb.append(" - ");
                            String str7 = "startEncoding() MediaMuxer(): " + m1205exceptionOrNullimpl2.getClass() + ": " + ((Object) m1205exceptionOrNullimpl2.getMessage());
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb.append(str7);
                            sb.append(' ');
                            sb.append("");
                            companion10.log(level, sb.toString());
                            try {
                                Result.Companion companion11 = Result.INSTANCE;
                                invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            } catch (Throwable th7) {
                                Result.Companion companion12 = Result.INSTANCE;
                                obj3 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                            }
                            if (invoke3 != null) {
                                obj3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                return;
                            }
                            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                        }
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion13 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        String substringAfterLast$default3 = (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default3 == null) {
                            Class<?> cls5 = getClass();
                            if (cls5 != null && (canonicalName2 = cls5.getCanonicalName()) != null) {
                                str6 = canonicalName2;
                            }
                        } else {
                            str6 = substringAfterLast$default3;
                        }
                        Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str6);
                        if (matcher2.find()) {
                            str2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                        } else {
                            str2 = str6;
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        sb2.append(str2);
                        sb2.append(" - ");
                        String str8 = "startEncoding() mediaCodec.start(): " + m1205exceptionOrNullimpl.getClass() + ": " + ((Object) m1205exceptionOrNullimpl.getMessage());
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb2.append(str8);
                        sb2.append(' ');
                        sb2.append("");
                        companion13.log(level2, sb2.toString());
                        try {
                            Result.Companion companion14 = Result.INSTANCE;
                            invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        } catch (Throwable th8) {
                            Result.Companion companion15 = Result.INSTANCE;
                            obj4 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                        }
                        if (invoke4 != null) {
                            obj4 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                            return;
                        }
                        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                    }
                    HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                    HyperLogger companion16 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                        str3 = "N/A";
                        substringAfterLast$default = null;
                    } else {
                        str3 = "N/A";
                        substringAfterLast$default = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    if (substringAfterLast$default == null) {
                        Class<?> cls6 = getClass();
                        str4 = (cls6 == null || (canonicalName3 = cls6.getCanonicalName()) == null) ? str3 : canonicalName3;
                    } else {
                        str4 = substringAfterLast$default;
                    }
                    Matcher matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str4);
                    if (matcher3.find()) {
                        str4 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str4 = str4.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    sb3.append(str4);
                    sb3.append(" - ");
                    String str9 = "startEncoding() mediaCodec.configure: " + m1205exceptionOrNullimpl4.getClass() + ": " + ((Object) m1205exceptionOrNullimpl4.getMessage());
                    if (str9 == null) {
                        str9 = "null ";
                    }
                    sb3.append(str9);
                    sb3.append(' ');
                    sb3.append("");
                    companion16.log(level3, sb3.toString());
                    try {
                        Result.Companion companion17 = Result.INSTANCE;
                        invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    } catch (Throwable th9) {
                        Result.Companion companion18 = Result.INSTANCE;
                        obj5 = Result.m1202constructorimpl(ResultKt.createFailure(th9));
                    }
                    if (invoke5 != null) {
                        obj5 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                }
                HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                HyperLogger companion19 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb4 = new StringBuilder();
                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                String substringAfterLast$default4 = (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                String str10 = (substringAfterLast$default4 == null && ((cls2 = getClass()) == null || (substringAfterLast$default4 = cls2.getCanonicalName()) == null)) ? "N/A" : substringAfterLast$default4;
                Matcher matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str10);
                if (matcher4.find()) {
                    str10 = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
                }
                Unit unit4 = Unit.INSTANCE;
                if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str10 = str10.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str10, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                sb4.append(str10);
                sb4.append(" - ");
                String str11 = "startEncoding() MediaCodec.createByCodecName: " + m1205exceptionOrNullimpl3.getClass() + ": " + ((Object) m1205exceptionOrNullimpl3.getMessage());
                if (str11 == null) {
                    str11 = "null ";
                }
                sb4.append(str11);
                sb4.append(' ');
                sb4.append("");
                companion19.log(level4, sb4.toString());
                try {
                    Result.Companion companion20 = Result.INSTANCE;
                    invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                } catch (Throwable th10) {
                    Result.Companion companion21 = Result.INSTANCE;
                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th10));
                }
                if (invoke2 != null) {
                    obj2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
            HyperLogger.Level level5 = HyperLogger.Level.ERROR;
            HyperLogger companion22 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb5 = new StringBuilder();
            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
            String substringAfterLast$default5 = (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            if (substringAfterLast$default5 != null || ((cls3 = getClass()) != null && (substringAfterLast$default5 = cls3.getCanonicalName()) != null)) {
                str5 = substringAfterLast$default5;
            }
            Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str5);
            if (matcher5.find()) {
                str5 = matcher5.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
            }
            Unit unit5 = Unit.INSTANCE;
            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str5 = str5.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            sb5.append(str5);
            sb5.append(" - ");
            sb5.append("startEncoding() Unable to find an appropriate codec for video/avc");
            sb5.append(' ');
            sb5.append("");
            companion22.log(level5, sb5.toString());
            try {
                Result.Companion companion23 = Result.INSTANCE;
                invoke6 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th11) {
                Result.Companion companion24 = Result.INSTANCE;
                obj6 = Result.m1202constructorimpl(ResultKt.createFailure(th11));
            }
            if (invoke6 != null) {
                obj6 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
        } catch (IOException e) {
            HyperLogger.Level level6 = HyperLogger.Level.ERROR;
            HyperLogger companion25 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb6 = new StringBuilder();
            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
            String substringAfterLast$default6 = (stackTraceElement6 == null || (className = stackTraceElement6.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            String str12 = (substringAfterLast$default6 == null && ((cls = getClass()) == null || (substringAfterLast$default6 = cls.getCanonicalName()) == null)) ? "N/A" : substringAfterLast$default6;
            Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str12);
            if (matcher6.find()) {
                str12 = matcher6.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
            }
            Unit unit6 = Unit.INSTANCE;
            if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str12 = str12.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str12, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            sb6.append(str12);
            sb6.append(" - ");
            String stringPlus = Intrinsics.stringPlus("startEncoding() ", e.getMessage());
            if (stringPlus == null) {
                stringPlus = "null ";
            }
            sb6.append(stringPlus);
            sb6.append(' ');
            sb6.append("");
            companion25.log(level6, sb6.toString());
            try {
                Result.Companion companion26 = Result.INSTANCE;
                invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th12) {
                Result.Companion companion27 = Result.INSTANCE;
                obj = Result.m1202constructorimpl(ResultKt.createFailure(th12));
            }
            if (invoke != null) {
                obj = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
        }
    }

    public final void queueFrame$hv_bitmaps_to_video_release(Bitmap bitmap) {
        String className;
        Class<?> cls;
        if (this.isStarted) {
            this.mEncodeQueue.add(bitmap);
            synchronized (this.mFrameSync) {
                if (this.mNewFrameLatch.getCount() > 0) {
                    this.mNewFrameLatch.countDown();
                }
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && ((cls = getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default);
        if (matcher.find()) {
            substringAfterLast$default = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
        }
        if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
            substringAfterLast$default = substringAfterLast$default.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("queueFrame() Failed to queue frame. Encoding not started");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:142|(10:231|(3:146|(2:149|(1:151))|148)|152|(1:154)|155|(1:160)|161|162|163|(7:165|166|(1:168)|169|170|171|(14:173|174|(1:220)(1:176)|(1:180)|183|(1:185)|186|(1:190)|191|(1:193)|194|195|196|(5:198|199|(1:201)|202|(2:209|210)(3:206|207|208))(3:212|213|215))(1:221))(3:222|223|225))|144|(0)|152|(0)|155|(2:157|160)|161|162|163|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(14:245|(12:285|(3:249|(2:252|(1:254))|251)|255|(1:257)|258|(1:263)|264|(1:266)|267|268|269|(4:271|272|(1:274)|275)(3:276|277|279))|247|(0)|255|(0)|258|(2:260|263)|264|(0)|267|268|269|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(14:293|(12:333|(3:297|(2:300|(1:302))|299)|303|(1:305)|306|(1:311)|312|(1:314)|315|316|317|(4:319|320|(1:322)|323)(3:324|325|327))|295|(0)|303|(0)|306|(2:308|311)|312|(0)|315|316|317|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:90|(14:134|(3:94|(2:97|(1:99))|96)|100|(1:102)|103|(1:108)|109|(1:111)(1:130)|112|(1:114)|115|116|117|(4:119|120|(1:122)|123)(3:124|125|127))|92|(0)|100|(0)|103|(2:105|108)|109|(0)(0)|112|(0)|115|116|117|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02b0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x02b1, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x039f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x03a0, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x04ba, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x04bb, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x05be, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x05bf, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x021e A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x029d A[Catch: all -> 0x02b0, TryCatch #0 {all -> 0x02b0, blocks: (B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af), top: B:116:0x0285, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02a8 A[Catch: all -> 0x02b0, TryCatch #0 {all -> 0x02b0, blocks: (B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af), top: B:116:0x0285, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x025d A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0313 A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0334 A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x038c A[Catch: all -> 0x039f, TryCatch #8 {all -> 0x039f, blocks: (B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:162:0x0374, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0616 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0397 A[Catch: all -> 0x039f, TryCatch #8 {all -> 0x039f, blocks: (B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:162:0x0374, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0422 A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0443 A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04a7 A[Catch: all -> 0x04ba, TryCatch #5 {all -> 0x04ba, blocks: (B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9), top: B:268:0x048f, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04b2 A[Catch: all -> 0x04ba, TryCatch #5 {all -> 0x04ba, blocks: (B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9), top: B:268:0x048f, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x051a A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:305:0x053b A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x05ab A[Catch: all -> 0x05be, TryCatch #7 {all -> 0x05be, blocks: (B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd), top: B:316:0x0593, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x05b6 A[Catch: all -> 0x05be, TryCatch #7 {all -> 0x05be, blocks: (B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd), top: B:316:0x0593, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01fd A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:68:0x014e, B:70:0x0175, B:71:0x017b, B:73:0x0188, B:75:0x018c, B:76:0x0192, B:84:0x01ac, B:86:0x01b0, B:87:0x01b8, B:89:0x01a9, B:90:0x01c9, B:94:0x01fd, B:97:0x0206, B:100:0x020d, B:102:0x021e, B:103:0x0229, B:105:0x0231, B:108:0x0236, B:109:0x023f, B:112:0x0265, B:115:0x0270, B:120:0x02bb, B:123:0x02c4, B:129:0x02b1, B:130:0x025d, B:131:0x01f0, B:134:0x01f7, B:135:0x01a0, B:136:0x0199, B:137:0x02c6, B:139:0x02cf, B:140:0x02d5, B:142:0x02df, B:146:0x0313, B:149:0x031c, B:152:0x0323, B:154:0x0334, B:155:0x033f, B:157:0x0347, B:160:0x034c, B:161:0x0355, B:166:0x03aa, B:169:0x03b3, B:170:0x05fe, B:227:0x03a0, B:228:0x0306, B:231:0x030d, B:234:0x03ba, B:236:0x03be, B:237:0x03c4, B:239:0x03d1, B:240:0x03d7, B:242:0x03e1, B:243:0x03e7, B:245:0x03ee, B:249:0x0422, B:252:0x042b, B:255:0x0432, B:257:0x0443, B:258:0x044e, B:260:0x0456, B:263:0x045b, B:264:0x0464, B:267:0x047a, B:272:0x04c5, B:275:0x04ce, B:281:0x04bb, B:282:0x0415, B:285:0x041c, B:286:0x04d2, B:288:0x04d6, B:290:0x04da, B:291:0x04e0, B:293:0x04e6, B:297:0x051a, B:300:0x0523, B:303:0x052a, B:305:0x053b, B:306:0x0546, B:308:0x054e, B:311:0x0553, B:312:0x055c, B:315:0x057e, B:320:0x05c9, B:323:0x05d2, B:329:0x05bf, B:330:0x050d, B:333:0x0514, B:334:0x05d5, B:336:0x05e6, B:337:0x05ec, B:339:0x05f5, B:340:0x05fb, B:117:0x0285, B:119:0x029d, B:124:0x02a8, B:125:0x02af, B:269:0x048f, B:271:0x04a7, B:276:0x04b2, B:277:0x04b9, B:317:0x0593, B:319:0x05ab, B:324:0x05b6, B:325:0x05bd, B:163:0x0374, B:165:0x038c, B:222:0x0397, B:223:0x039e), top: B:67:0x014e, inners: #0, #5, #7, #8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void encode() {
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        String className;
        Object m1202constructorimpl2;
        Object invoke;
        Class<?> cls;
        MediaCodec.BufferInfo bufferInfo;
        int dequeueOutputBuffer;
        String className2;
        String substringAfterLast$default;
        Matcher matcher;
        String str;
        Object invoke2;
        String className3;
        String substringAfterLast$default2;
        Matcher matcher2;
        String stringPlus;
        Object invoke3;
        String className4;
        String substringAfterLast$default3;
        Matcher matcher3;
        Object invoke4;
        String className5;
        String substringAfterLast$default4;
        Matcher matcher4;
        String sb;
        Object invoke5;
        MediaCodec mediaCodec;
        Object m1202constructorimpl3;
        String className6;
        Object m1202constructorimpl4;
        Object invoke6;
        Class<?> cls2;
        while (true) {
            if (!this.mNoMoreFrames || this.mEncodeQueue.size() != 0) {
                Bitmap poll = this.mEncodeQueue.poll();
                if (poll == null) {
                    synchronized (this.mFrameSync) {
                        this.mNewFrameLatch = new CountDownLatch(1);
                        Unit unit = Unit.INSTANCE;
                    }
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        HyperVideoEncoder hyperVideoEncoder = this;
                        this.mNewFrameLatch.await();
                        m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl2 != null) {
                        HyperLogger.Level level = HyperLogger.Level.ERROR;
                        HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        String substringAfterLast$default5 = (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default5 == null && ((cls2 = getClass()) == null || (substringAfterLast$default5 = cls2.getCanonicalName()) == null)) {
                            substringAfterLast$default5 = "N/A";
                        }
                        Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default5);
                        if (matcher5.find()) {
                            substringAfterLast$default5 = matcher5.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default5, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (substringAfterLast$default5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            substringAfterLast$default5 = substringAfterLast$default5.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        sb2.append(substringAfterLast$default5);
                        sb2.append(" - ");
                        String stringPlus2 = Intrinsics.stringPlus("encode() ", m1205exceptionOrNullimpl2.getMessage());
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        companion3.log(level, sb2.toString());
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            invoke6 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (invoke6 != null) {
                            m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            Unit unit3 = Unit.INSTANCE;
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                            break;
                        }
                    }
                    poll = this.mEncodeQueue.poll();
                }
                if (poll != null) {
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        HyperVideoEncoder hyperVideoEncoder2 = this;
                        byte[] nv21 = getNV21(poll.getWidth(), poll.getHeight(), poll);
                        poll.recycle();
                        int length = nv21.length;
                        MediaCodec mediaCodec2 = this.mediaCodec;
                        if (mediaCodec2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                            mediaCodec2 = null;
                        }
                        int dequeueInputBuffer = mediaCodec2.dequeueInputBuffer(500000L);
                        long computePresentationTime = computePresentationTime(this.mGenerateIndex);
                        if (dequeueInputBuffer >= 0) {
                            MediaCodec mediaCodec3 = this.mediaCodec;
                            if (mediaCodec3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                mediaCodec3 = null;
                            }
                            ByteBuffer inputBuffer = mediaCodec3.getInputBuffer(dequeueInputBuffer);
                            if (inputBuffer != null) {
                                inputBuffer.clear();
                            }
                            if (length <= (inputBuffer == null ? 0 : inputBuffer.remaining())) {
                                if (inputBuffer != null) {
                                    inputBuffer.put(nv21);
                                }
                                MediaCodec mediaCodec4 = this.mediaCodec;
                                if (mediaCodec4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                    mediaCodec = null;
                                } else {
                                    mediaCodec = mediaCodec4;
                                }
                                mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, length, computePresentationTime, 0);
                                this.mGenerateIndex++;
                            } else {
                                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3 = new StringBuilder();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 != null && (className5 = stackTraceElement2.getClassName()) != null) {
                                    substringAfterLast$default4 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default4 == null) {
                                        Class<?> cls3 = getClass();
                                        if (cls3 == null) {
                                            substringAfterLast$default4 = cls3.getCanonicalName();
                                            if (substringAfterLast$default4 == null) {
                                            }
                                        }
                                        substringAfterLast$default4 = "N/A";
                                    }
                                    matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default4);
                                    if (matcher4.find()) {
                                        substringAfterLast$default4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default4, "replaceAll(\"\")");
                                    }
                                    Unit unit4 = Unit.INSTANCE;
                                    if (substringAfterLast$default4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        substringAfterLast$default4 = substringAfterLast$default4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    }
                                    sb3.append(substringAfterLast$default4);
                                    sb3.append(" - ");
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("encode() Frame size exceeds buffer capacity: ");
                                    sb4.append(length);
                                    sb4.append(" > ");
                                    sb4.append(inputBuffer != null ? null : Integer.valueOf(inputBuffer.remaining()));
                                    sb = sb4.toString();
                                    if (sb == null) {
                                        sb = "null ";
                                    }
                                    sb3.append(sb);
                                    sb3.append(' ');
                                    sb3.append("");
                                    companion7.log(level2, sb3.toString());
                                    Result.Companion companion8 = Result.INSTANCE;
                                    invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    if (invoke5 == null) {
                                        Object m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                                        if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                            m1202constructorimpl5 = "";
                                        }
                                    } else {
                                        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                                        break;
                                    }
                                }
                                substringAfterLast$default4 = null;
                                if (substringAfterLast$default4 == null) {
                                }
                                matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default4);
                                if (matcher4.find()) {
                                }
                                Unit unit42 = Unit.INSTANCE;
                                if (substringAfterLast$default4.length() > 23) {
                                    substringAfterLast$default4 = substringAfterLast$default4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                sb3.append(substringAfterLast$default4);
                                sb3.append(" - ");
                                StringBuilder sb42 = new StringBuilder();
                                sb42.append("encode() Frame size exceeds buffer capacity: ");
                                sb42.append(length);
                                sb42.append(" > ");
                                sb42.append(inputBuffer != null ? null : Integer.valueOf(inputBuffer.remaining()));
                                sb = sb42.toString();
                                if (sb == null) {
                                }
                                sb3.append(sb);
                                sb3.append(' ');
                                sb3.append("");
                                companion7.log(level2, sb3.toString());
                                Result.Companion companion82 = Result.INSTANCE;
                                invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                if (invoke5 == null) {
                                }
                            }
                        }
                        bufferInfo = new MediaCodec.BufferInfo();
                        MediaCodec mediaCodec5 = this.mediaCodec;
                        if (mediaCodec5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                            mediaCodec5 = null;
                        }
                        dequeueOutputBuffer = mediaCodec5.dequeueOutputBuffer(bufferInfo, 500000L);
                    } catch (Throwable th3) {
                        Result.Companion companion9 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (dequeueOutputBuffer != -1) {
                        if (dequeueOutputBuffer == -2) {
                            MediaCodec mediaCodec6 = this.mediaCodec;
                            if (mediaCodec6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                mediaCodec6 = null;
                            }
                            MediaFormat outputFormat = mediaCodec6.getOutputFormat();
                            Intrinsics.checkNotNullExpressionValue(outputFormat, "mediaCodec.outputFormat");
                            MediaMuxer mediaMuxer = this.mediaMuxer;
                            if (mediaMuxer == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                                mediaMuxer = null;
                            }
                            this.mTrackIndex = mediaMuxer.addTrack(outputFormat);
                            MediaMuxer mediaMuxer2 = this.mediaMuxer;
                            if (mediaMuxer2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                                mediaMuxer2 = null;
                            }
                            mediaMuxer2.start();
                        } else if (dequeueOutputBuffer >= 0) {
                            if (bufferInfo.size != 0) {
                                MediaCodec mediaCodec7 = this.mediaCodec;
                                if (mediaCodec7 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                    mediaCodec7 = null;
                                }
                                ByteBuffer outputBuffer = mediaCodec7.getOutputBuffer(dequeueOutputBuffer);
                                if (outputBuffer != null) {
                                    outputBuffer.position(bufferInfo.offset);
                                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                                    MediaMuxer mediaMuxer3 = this.mediaMuxer;
                                    if (mediaMuxer3 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                                        mediaMuxer3 = null;
                                    }
                                    mediaMuxer3.writeSampleData(this.mTrackIndex, outputBuffer, bufferInfo);
                                    MediaCodec mediaCodec8 = this.mediaCodec;
                                    if (mediaCodec8 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                        mediaCodec8 = null;
                                    }
                                    mediaCodec8.releaseOutputBuffer(dequeueOutputBuffer, false);
                                } else {
                                    HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                                    HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb5 = new StringBuilder();
                                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                    if (stackTraceElement3 != null && (className2 = stackTraceElement3.getClassName()) != null) {
                                        substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        if (substringAfterLast$default == null) {
                                            Class<?> cls4 = getClass();
                                            if (cls4 == null) {
                                                substringAfterLast$default = cls4.getCanonicalName();
                                                if (substringAfterLast$default == null) {
                                                }
                                            }
                                            substringAfterLast$default = "N/A";
                                        }
                                        matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default);
                                        if (matcher.find()) {
                                            substringAfterLast$default = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                                        }
                                        Unit unit5 = Unit.INSTANCE;
                                        if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                        }
                                        sb5.append(substringAfterLast$default);
                                        sb5.append(" - ");
                                        str = "encode() encoderOutputBuffer " + dequeueOutputBuffer + " was null";
                                        if (str == null) {
                                            str = "null ";
                                        }
                                        sb5.append(str);
                                        sb5.append(' ');
                                        sb5.append("");
                                        companion10.log(level3, sb5.toString());
                                        Result.Companion companion11 = Result.INSTANCE;
                                        invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                        if (invoke2 == null) {
                                            Object m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                            if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                                m1202constructorimpl6 = "";
                                            }
                                        } else {
                                            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                                            break;
                                        }
                                    }
                                    substringAfterLast$default = null;
                                    if (substringAfterLast$default == null) {
                                    }
                                    matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default);
                                    if (matcher.find()) {
                                    }
                                    Unit unit52 = Unit.INSTANCE;
                                    if (substringAfterLast$default.length() > 23) {
                                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    }
                                    sb5.append(substringAfterLast$default);
                                    sb5.append(" - ");
                                    str = "encode() encoderOutputBuffer " + dequeueOutputBuffer + " was null";
                                    if (str == null) {
                                    }
                                    sb5.append(str);
                                    sb5.append(' ');
                                    sb5.append("");
                                    companion10.log(level3, sb5.toString());
                                    Result.Companion companion112 = Result.INSTANCE;
                                    invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    if (invoke2 == null) {
                                    }
                                }
                            }
                        } else {
                            HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                            HyperLogger companion12 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb6 = new StringBuilder();
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 != null && (className3 = stackTraceElement4.getClassName()) != null) {
                                substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default2 == null) {
                                    Class<?> cls5 = getClass();
                                    if (cls5 == null) {
                                        substringAfterLast$default2 = cls5.getCanonicalName();
                                        if (substringAfterLast$default2 == null) {
                                        }
                                    }
                                    substringAfterLast$default2 = "N/A";
                                }
                                matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default2);
                                if (matcher2.find()) {
                                    substringAfterLast$default2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                sb6.append(substringAfterLast$default2);
                                sb6.append(" - ");
                                stringPlus = Intrinsics.stringPlus("encode() unexpected result from encoder.dequeueOutputBuffer: ", Integer.valueOf(dequeueOutputBuffer));
                                if (stringPlus == null) {
                                    stringPlus = "null ";
                                }
                                sb6.append(stringPlus);
                                sb6.append(' ');
                                sb6.append("");
                                companion12.log(level4, sb6.toString());
                                Result.Companion companion13 = Result.INSTANCE;
                                invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                if (invoke3 == null) {
                                    Object m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                    if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                        m1202constructorimpl7 = "";
                                    }
                                } else {
                                    throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                                    break;
                                }
                            }
                            substringAfterLast$default2 = null;
                            if (substringAfterLast$default2 == null) {
                            }
                            matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default2);
                            if (matcher2.find()) {
                            }
                            Unit unit62 = Unit.INSTANCE;
                            if (substringAfterLast$default2.length() > 23) {
                                substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            }
                            sb6.append(substringAfterLast$default2);
                            sb6.append(" - ");
                            stringPlus = Intrinsics.stringPlus("encode() unexpected result from encoder.dequeueOutputBuffer: ", Integer.valueOf(dequeueOutputBuffer));
                            if (stringPlus == null) {
                            }
                            sb6.append(stringPlus);
                            sb6.append(' ');
                            sb6.append("");
                            companion12.log(level4, sb6.toString());
                            Result.Companion companion132 = Result.INSTANCE;
                            invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            if (invoke3 == null) {
                            }
                        }
                        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                        if (m1205exceptionOrNullimpl == null) {
                        }
                    } else {
                        HyperLogger.Level level5 = HyperLogger.Level.ERROR;
                        HyperLogger companion14 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb7 = new StringBuilder();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 != null && (className4 = stackTraceElement5.getClassName()) != null) {
                            substringAfterLast$default3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default3 == null) {
                                Class<?> cls6 = getClass();
                                if (cls6 == null) {
                                    substringAfterLast$default3 = cls6.getCanonicalName();
                                    if (substringAfterLast$default3 == null) {
                                    }
                                }
                                substringAfterLast$default3 = "N/A";
                            }
                            matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default3);
                            if (matcher3.find()) {
                                substringAfterLast$default3 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "replaceAll(\"\")");
                            }
                            Unit unit7 = Unit.INSTANCE;
                            if (substringAfterLast$default3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                substringAfterLast$default3 = substringAfterLast$default3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            }
                            sb7.append(substringAfterLast$default3);
                            sb7.append(" - ");
                            sb7.append("encode() No output from encoder available");
                            sb7.append(' ');
                            sb7.append("");
                            companion14.log(level5, sb7.toString());
                            Result.Companion companion15 = Result.INSTANCE;
                            invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            if (invoke4 == null) {
                                Object m1202constructorimpl8 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                                    m1202constructorimpl8 = "";
                                }
                                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                if (m1205exceptionOrNullimpl == null) {
                                    HyperLogger.Level level6 = HyperLogger.Level.ERROR;
                                    HyperLogger companion16 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb8 = new StringBuilder();
                                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                    String substringAfterLast$default6 = (stackTraceElement6 == null || (className = stackTraceElement6.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default6 == null && ((cls = getClass()) == null || (substringAfterLast$default6 = cls.getCanonicalName()) == null)) {
                                        substringAfterLast$default6 = "N/A";
                                    }
                                    Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default6);
                                    if (matcher6.find()) {
                                        substringAfterLast$default6 = matcher6.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default6, "replaceAll(\"\")");
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    if (substringAfterLast$default6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        substringAfterLast$default6 = substringAfterLast$default6.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    }
                                    sb8.append(substringAfterLast$default6);
                                    sb8.append(" - ");
                                    String str2 = "encode() " + m1205exceptionOrNullimpl.getClass() + ": " + ((Object) m1205exceptionOrNullimpl.getMessage());
                                    if (str2 == null) {
                                        str2 = "null ";
                                    }
                                    sb8.append(str2);
                                    sb8.append(' ');
                                    sb8.append("");
                                    companion16.log(level6, sb8.toString());
                                    try {
                                        Result.Companion companion17 = Result.INSTANCE;
                                        invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    } catch (Throwable th4) {
                                        Result.Companion companion18 = Result.INSTANCE;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                                    }
                                    if (invoke != null) {
                                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                            m1202constructorimpl2 = "";
                                        }
                                        if ((m1205exceptionOrNullimpl instanceof IllegalStateException) && DeviceExtsKt.isLowStorage(this.mCallback.get$context())) {
                                            release();
                                            throw m1205exceptionOrNullimpl;
                                        }
                                        Unit unit9 = Unit.INSTANCE;
                                    } else {
                                        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                                break;
                            }
                        }
                        substringAfterLast$default3 = null;
                        if (substringAfterLast$default3 == null) {
                        }
                        matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default3);
                        if (matcher3.find()) {
                        }
                        Unit unit72 = Unit.INSTANCE;
                        if (substringAfterLast$default3.length() > 23) {
                            substringAfterLast$default3 = substringAfterLast$default3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        sb7.append(substringAfterLast$default3);
                        sb7.append(" - ");
                        sb7.append("encode() No output from encoder available");
                        sb7.append(' ');
                        sb7.append("");
                        companion14.log(level5, sb7.toString());
                        Result.Companion companion152 = Result.INSTANCE;
                        invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        if (invoke4 == null) {
                        }
                    }
                }
            } else {
                release();
                this.mCallback.onEncodingComplete(this.mOutputFile);
                return;
            }
        }
    }

    private final void release() {
        Object m1202constructorimpl;
        String className;
        Object obj;
        Object invoke;
        Class<?> cls;
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperVideoEncoder hyperVideoEncoder = this;
            MediaCodec mediaCodec = this.mediaCodec;
            if (mediaCodec == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                mediaCodec = null;
            }
            mediaCodec.stop();
            mediaCodec.release();
            MediaMuxer mediaMuxer = this.mediaMuxer;
            if (mediaMuxer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                mediaMuxer = null;
            }
            mediaMuxer.stop();
            mediaMuxer.release();
            this.mNewFrameLatch = new CountDownLatch(0);
            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            if (substringAfterLast$default == null && ((cls = getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
                substringAfterLast$default = "N/A";
            }
            Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default);
            if (matcher.find()) {
                substringAfterLast$default = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
            }
            if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            sb.append(substringAfterLast$default);
            sb.append(" - ");
            String stringPlus = Intrinsics.stringPlus("release(): ", m1205exceptionOrNullimpl.getMessage());
            if (stringPlus == null) {
                stringPlus = "null ";
            }
            sb.append(stringPlus);
            sb.append(' ');
            sb.append("");
            companion3.log(level, sb.toString());
            try {
                Result.Companion companion4 = Result.INSTANCE;
                invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                obj = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (invoke != null) {
                obj = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
        }
    }

    private final byte[] getNV21(int inputWidth, int inputHeight, Bitmap scaled) {
        int i = inputWidth * inputHeight;
        int[] iArr = new int[i];
        scaled.getPixels(iArr, 0, inputWidth, 0, 0, inputWidth, inputHeight);
        byte[] bArr = new byte[i + (((int) Math.ceil(inputHeight / 2.0d)) * 2 * ((int) Math.ceil(inputWidth / 2.0d)))];
        encodeYUV420SP(bArr, iArr, inputWidth, inputHeight);
        scaled.recycle();
        return bArr;
    }

    private final void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        int i = width * height;
        if (height <= 0) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i2 + 1;
            if (width > 0) {
                int i6 = 0;
                do {
                    i6++;
                    int i7 = (argb[i4] & 16711680) >> 16;
                    int i8 = (argb[i4] & 65280) >> 8;
                    int i9 = 255;
                    int i10 = (argb[i4] & 255) >> 0;
                    int i11 = (((((i7 * 66) + (i8 * 129)) + (i10 * 25)) + 128) >> 8) + 16;
                    int i12 = (((((i7 * (-38)) - (i8 * 74)) + (i10 * 112)) + 128) >> 8) + 128;
                    int i13 = (((((i7 * 112) - (i8 * 94)) - (i10 * 18)) + 128) >> 8) + 128;
                    int i14 = i3 + 1;
                    if (i11 < 0) {
                        i11 = 0;
                    } else if (i11 > 255) {
                        i11 = 255;
                    }
                    yuv420sp[i3] = (byte) i11;
                    if (i2 % 2 == 0 && i4 % 2 == 0) {
                        int i15 = i + 1;
                        if (i12 < 0) {
                            i12 = 0;
                        } else if (i12 > 255) {
                            i12 = 255;
                        }
                        yuv420sp[i] = (byte) i12;
                        i = i15 + 1;
                        if (i13 < 0) {
                            i9 = 0;
                        } else if (i13 <= 255) {
                            i9 = i13;
                        }
                        yuv420sp[i15] = (byte) i9;
                    }
                    i4++;
                    i3 = i14;
                } while (i6 < width);
            }
            if (i5 >= height) {
                return;
            } else {
                i2 = i5;
            }
        }
    }

    private final long computePresentationTime(long frameIndex) {
        return (frameIndex * 1000000) / 8;
    }

    /* compiled from: HyperVideoEncoder.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lco/hyperverge/encoder/HyperVideoEncoder$Companion;", "", "()V", "BIT_RATE", "", "FRAME_RATE", "I_FRAME_INTERVAL", "MIME_TYPE", "", "mHeight", "mWidth", "selectCodec", "Landroid/media/MediaCodecInfo;", "hv-bitmaps-to-video_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MediaCodecInfo selectCodec() {
            int codecCount = MediaCodecList.getCodecCount();
            if (codecCount <= 0) {
                return null;
            }
            int i = 0;
            while (true) {
                int i2 = i + 1;
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
                if (codecInfoAt.isEncoder()) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    int length = supportedTypes.length - 1;
                    if (length >= 0) {
                        int i3 = 0;
                        while (true) {
                            int i4 = i3 + 1;
                            if (StringsKt.equals(supportedTypes[i3], "video/avc", true)) {
                                return codecInfoAt;
                            }
                            if (i4 > length) {
                                break;
                            }
                            i3 = i4;
                        }
                    }
                }
                if (i2 >= codecCount) {
                    return null;
                }
                i = i2;
            }
        }
    }

    public final void stopEncoding$hv_bitmaps_to_video_release() {
        String className;
        Class<?> cls;
        String className2;
        Class<?> cls2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = null;
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && ((cls2 = getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default);
        if (matcher.find()) {
            substringAfterLast$default = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
        }
        if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
            substringAfterLast$default = substringAfterLast$default.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("stopEncoding() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (this.isStarted) {
            this.mNoMoreFrames = true;
            synchronized (this.mFrameSync) {
                if (this.mNewFrameLatch.getCount() > 0) {
                    this.mNewFrameLatch.countDown();
                }
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
        HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2 = new StringBuilder();
        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
        if (stackTraceElement2 != null && (className2 = stackTraceElement2.getClassName()) != null) {
            str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        if (str == null && ((cls = getClass()) == null || (str = cls.getCanonicalName()) == null)) {
            str = "N/A";
        }
        Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str);
        if (matcher2.find()) {
            str = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
        }
        if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str = str.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        sb2.append(str);
        sb2.append(" - ");
        sb2.append("stopEncoding() Failed to stop encoding since it never started");
        sb2.append(' ');
        sb2.append("");
        companion2.log(level2, sb2.toString());
    }
}
