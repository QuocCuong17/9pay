package co.hyperverge.hyperkyc.hvsessionrecorder;

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
import android.util.Log;
import android.view.Surface;
import co.hyperverge.hyperkyc.utils.SessionRecorderUtilsKt;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HVBitmapToVideoEncoder.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 52\u00020\u0001:\u000256B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J(\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000fH\u0002J \u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\fH\u0002J\u0017\u0010*\u001a\u00020\u001e2\b\u0010+\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b,J\b\u0010-\u001a\u00020\u001eH\u0002J-\u0010.\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0000¢\u0006\u0002\b2J\r\u00103\u001a\u00020\u001eH\u0000¢\u0006\u0002\b4R\u0014\u0010\u0005\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/HVBitmapToVideoEncoder;", "", "mCallback", "Lco/hyperverge/hyperkyc/hvsessionrecorder/HVBitmapToVideoEncoder$IBitmapToVideoEncoderCallback;", "(Lco/hyperverge/hyperkyc/hvsessionrecorder/HVBitmapToVideoEncoder$IBitmapToVideoEncoderCallback;)V", "isEncodingStarted", "", "isEncodingStarted$hyperkyc_release", "()Z", "isStarted", "mEncodeQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Landroid/graphics/Bitmap;", "mFrameSync", "mGenerateIndex", "", "mNewFrameLatch", "Ljava/util/concurrent/CountDownLatch;", "mNoMoreFrames", "mOutputFile", "Ljava/io/File;", "mTrackIndex", "mediaCodec", "Landroid/media/MediaCodec;", "mediaMuxer", "Landroid/media/MediaMuxer;", "computePresentationTime", "", "frameIndex", "encode", "", "encodeYUV420SP", "yuv420sp", "", "argb", "", "width", "height", "getNV21", "inputWidth", "inputHeight", "scaled", "queueFrame", "bitmap", "queueFrame$hyperkyc_release", "release", "startEncoding", "outputFile", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "startEncoding$hyperkyc_release", "stopEncoding", "stopEncoding$hyperkyc_release", "Companion", "IBitmapToVideoEncoderCallback", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HVBitmapToVideoEncoder {
    private static final int BIT_RATE = 1000000;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FRAME_RATE;
    private static final int I_FRAME_INTERVAL = 1;
    private static final String MIME_TYPE = "video/avc";
    private static int mHeight;
    private static int mWidth;
    private boolean isStarted;
    private final IBitmapToVideoEncoderCallback mCallback;
    private ConcurrentLinkedQueue<Bitmap> mEncodeQueue;
    private final Object mFrameSync;
    private int mGenerateIndex;
    private CountDownLatch mNewFrameLatch;
    private boolean mNoMoreFrames;
    private File mOutputFile;
    private int mTrackIndex;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;

    /* compiled from: HVBitmapToVideoEncoder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/HVBitmapToVideoEncoder$IBitmapToVideoEncoderCallback;", "", "getContext", "Landroid/content/Context;", "onEncodingComplete", "", "outputFile", "Ljava/io/File;", "onLowStorage", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface IBitmapToVideoEncoderCallback {
        /* renamed from: getContext */
        Context get$context();

        void onEncodingComplete(File outputFile);

        void onLowStorage();
    }

    public HVBitmapToVideoEncoder(IBitmapToVideoEncoderCallback mCallback) {
        Intrinsics.checkNotNullParameter(mCallback, "mCallback");
        this.mCallback = mCallback;
        this.mEncodeQueue = new ConcurrentLinkedQueue<>();
        this.mFrameSync = new Object();
        this.mNewFrameLatch = new CountDownLatch(0);
    }

    public final boolean isEncodingStarted$hyperkyc_release() {
        return this.isStarted && !this.mNoMoreFrames;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0080, code lost:
    
        if (r15 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x046d, code lost:
    
        if (r15 != null) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0670, code lost:
    
        if (r15 != null) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x082b, code lost:
    
        if (r11 != null) goto L337;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x09e9, code lost:
    
        if (r12 != null) goto L406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0284, code lost:
    
        if (r12 != null) goto L104;
     */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x063b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0794  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x07da  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x07dd  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x07f2  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x0b04  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0b43  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0b46  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startEncoding$hyperkyc_release(int width, int height, File outputFile, CoroutineScope lifecycleScope) {
        String str;
        String str2;
        Object m1202constructorimpl;
        String str3;
        String str4;
        Matcher matcher;
        String className;
        String className2;
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        String str5;
        String str6;
        String str7;
        Object m1202constructorimpl3;
        String str8;
        String className3;
        String substringAfterLast$default;
        String className4;
        Object m1202constructorimpl4;
        Object m1202constructorimpl5;
        Throwable m1205exceptionOrNullimpl2;
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder2;
        Object m1202constructorimpl6;
        Throwable m1205exceptionOrNullimpl3;
        String str9;
        String str10;
        String str11;
        Object m1202constructorimpl7;
        String canonicalName;
        String str12;
        Matcher matcher2;
        String className5;
        String className6;
        String str13;
        String str14;
        String str15;
        Object m1202constructorimpl8;
        String str16;
        String className7;
        String className8;
        String str17;
        String str18;
        Object m1202constructorimpl9;
        String str19;
        String className9;
        String substringAfterLast$default2;
        String className10;
        String str20;
        String str21;
        String str22;
        Object m1202constructorimpl10;
        String str23;
        String className11;
        String substringAfterLast$default3;
        String className12;
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
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
                    hVBitmapToVideoEncoder = this;
                    try {
                        HVBitmapToVideoEncoder hVBitmapToVideoEncoder3 = hVBitmapToVideoEncoder;
                        m1202constructorimpl2 = Result.m1202constructorimpl(MediaCodec.createByCodecName(selectCodec.getName()));
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    hVBitmapToVideoEncoder = this;
                }
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                if (m1205exceptionOrNullimpl != null) {
                    hVBitmapToVideoEncoder.mediaCodec = (MediaCodec) m1202constructorimpl2;
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", mWidth, mHeight);
                    Intrinsics.checkNotNullExpressionValue(createVideoFormat, "createVideoFormat(MIME_TYPE, mWidth, mHeight)");
                    createVideoFormat.setInteger("bitrate", 1000000);
                    createVideoFormat.setInteger("frame-rate", FRAME_RATE);
                    createVideoFormat.setInteger("color-format", 21);
                    createVideoFormat.setInteger("i-frame-interval", 1);
                    createVideoFormat.setLong("max-input-size", Long.MAX_VALUE);
                    try {
                        Result.Companion companion4 = Result.INSTANCE;
                        HVBitmapToVideoEncoder hVBitmapToVideoEncoder4 = hVBitmapToVideoEncoder;
                        MediaCodec mediaCodec = hVBitmapToVideoEncoder.mediaCodec;
                        if (mediaCodec == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                            mediaCodec = null;
                        }
                        mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                        m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th3) {
                        Result.Companion companion5 = Result.INSTANCE;
                        m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    Throwable m1205exceptionOrNullimpl4 = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                    if (m1205exceptionOrNullimpl4 == null) {
                        try {
                            Result.Companion companion6 = Result.INSTANCE;
                            try {
                                HVBitmapToVideoEncoder hVBitmapToVideoEncoder5 = this;
                                MediaCodec mediaCodec2 = this.mediaCodec;
                                if (mediaCodec2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                                    mediaCodec2 = null;
                                }
                                mediaCodec2.start();
                                m1202constructorimpl5 = Result.m1202constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th4) {
                                th = th4;
                                Result.Companion companion7 = Result.INSTANCE;
                                m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                                if (m1205exceptionOrNullimpl2 != null) {
                                }
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                        if (m1205exceptionOrNullimpl2 != null) {
                            try {
                                Result.Companion companion8 = Result.INSTANCE;
                                hVBitmapToVideoEncoder2 = this;
                                try {
                                    HVBitmapToVideoEncoder hVBitmapToVideoEncoder6 = hVBitmapToVideoEncoder2;
                                    m1202constructorimpl6 = Result.m1202constructorimpl(new MediaMuxer(canonicalPath, 0));
                                } catch (Throwable th6) {
                                    th = th6;
                                    Result.Companion companion9 = Result.INSTANCE;
                                    m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(m1202constructorimpl6);
                                    if (m1205exceptionOrNullimpl3 != null) {
                                    }
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                hVBitmapToVideoEncoder2 = this;
                            }
                            m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(m1202constructorimpl6);
                            if (m1205exceptionOrNullimpl3 != null) {
                                hVBitmapToVideoEncoder2.mediaMuxer = (MediaMuxer) m1202constructorimpl6;
                                hVBitmapToVideoEncoder2.isStarted = true;
                                BuildersKt__Builders_commonKt.launch$default(lifecycleScope, Dispatchers.getMain(), null, new HVBitmapToVideoEncoder$startEncoding$10(hVBitmapToVideoEncoder2, null), 2, null);
                                return;
                            }
                            HyperLogger.Level level = HyperLogger.Level.ERROR;
                            HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb = new StringBuilder();
                            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                            if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null) {
                                str9 = "packageName";
                                str10 = "Throwable().stackTrace";
                            } else {
                                str9 = "packageName";
                                str10 = "Throwable().stackTrace";
                                str11 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls = getClass();
                            String canonicalName2 = cls != null ? cls.getCanonicalName() : null;
                            str11 = canonicalName2 == null ? "N/A" : canonicalName2;
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str11);
                            if (matcher3.find()) {
                                str11 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str11, "replaceAll(\"\")");
                            }
                            Unit unit = Unit.INSTANCE;
                            if (str11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str11 = str11.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb.append(str11);
                            sb.append(" - ");
                            String str24 = "startEncoding() MediaMuxer(): " + m1205exceptionOrNullimpl3.getClass() + ": " + m1205exceptionOrNullimpl3.getMessage();
                            if (str24 == null) {
                                str24 = "null ";
                            }
                            sb.append(str24);
                            sb.append(' ');
                            sb.append("");
                            companion10.log(level, sb.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion11 = Result.INSTANCE;
                                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                            } catch (Throwable th8) {
                                Result.Companion companion12 = Result.INSTANCE;
                                m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                m1202constructorimpl7 = "";
                            }
                            String str25 = (String) m1202constructorimpl7;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str25, str9);
                                if (StringsKt.contains$default((CharSequence) str25, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace2, str10);
                                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                    if (stackTraceElement2 == null || (className5 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls2 = getClass();
                                        canonicalName = cls2 != null ? cls2.getCanonicalName() : null;
                                        if (canonicalName == null) {
                                            str12 = "N/A";
                                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                                            if (matcher2.find()) {
                                                str12 = matcher2.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
                                            }
                                            Unit unit2 = Unit.INSTANCE;
                                            if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                str12 = str12.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb2 = new StringBuilder();
                                            String str26 = "startEncoding() MediaMuxer(): " + m1205exceptionOrNullimpl3.getClass() + ": " + m1205exceptionOrNullimpl3.getMessage();
                                            sb2.append(str26 != null ? "null " : str26);
                                            sb2.append(' ');
                                            sb2.append("");
                                            Log.println(6, str12, sb2.toString());
                                            return;
                                        }
                                    }
                                    str12 = canonicalName;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                                    if (matcher2.find()) {
                                    }
                                    Unit unit22 = Unit.INSTANCE;
                                    if (str12.length() > 23) {
                                        str12 = str12.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb22 = new StringBuilder();
                                    String str262 = "startEncoding() MediaMuxer(): " + m1205exceptionOrNullimpl3.getClass() + ": " + m1205exceptionOrNullimpl3.getMessage();
                                    sb22.append(str262 != null ? "null " : str262);
                                    sb22.append(' ');
                                    sb22.append("");
                                    Log.println(6, str12, sb22.toString());
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion13 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className8 = stackTraceElement3.getClassName()) == null) {
                            str13 = "packageName";
                            str14 = "Throwable().stackTrace";
                        } else {
                            str13 = "packageName";
                            str14 = "Throwable().stackTrace";
                            str15 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls3 = getClass();
                        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        str15 = canonicalName3 == null ? "N/A" : canonicalName3;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str15);
                        if (matcher4.find()) {
                            str15 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str15, "replaceAll(\"\")");
                        }
                        Unit unit3 = Unit.INSTANCE;
                        if (str15.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str15 = str15.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str15);
                        sb3.append(" - ");
                        String str27 = "startEncoding() mediaCodec.start(): " + m1205exceptionOrNullimpl2.getClass() + ": " + m1205exceptionOrNullimpl2.getMessage();
                        if (str27 == null) {
                            str27 = "null ";
                        }
                        sb3.append(str27);
                        sb3.append(' ');
                        sb3.append("");
                        companion13.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion14 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl8 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th9) {
                            Result.Companion companion15 = Result.INSTANCE;
                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th9));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                            m1202constructorimpl8 = "";
                        }
                        String str28 = (String) m1202constructorimpl8;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str28, str13);
                            if (StringsKt.contains$default((CharSequence) str28, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str14);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className7 = stackTraceElement4.getClassName()) == null || (str16 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                    str16 = canonicalName4 == null ? "N/A" : canonicalName4;
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str16);
                                if (matcher5.find()) {
                                    str16 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str16, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (str16.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str16 = str16.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str29 = "startEncoding() mediaCodec.start(): " + m1205exceptionOrNullimpl2.getClass() + ": " + m1205exceptionOrNullimpl2.getMessage();
                                sb4.append(str29 == null ? "null " : str29);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(6, str16, sb4.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                    HyperLogger companion16 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement5 == null || (className10 = stackTraceElement5.getClassName()) == null) {
                        str17 = "Throwable().stackTrace";
                    } else {
                        str17 = "Throwable().stackTrace";
                        str18 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls5 = getClass();
                    String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                    str18 = canonicalName5 == null ? "N/A" : canonicalName5;
                    Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str18);
                    if (matcher6.find()) {
                        str18 = matcher6.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str18, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (str18.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str18 = str18.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str18, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb5.append(str18);
                    sb5.append(" - ");
                    String str30 = "startEncoding() mediaCodec.configure: " + m1205exceptionOrNullimpl4.getClass() + ": " + m1205exceptionOrNullimpl4.getMessage();
                    if (str30 == null) {
                        str30 = "null ";
                    }
                    sb5.append(str30);
                    sb5.append(' ');
                    sb5.append("");
                    companion16.log(level3, sb5.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion17 = Result.INSTANCE;
                        Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl9 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                    } catch (Throwable th10) {
                        Result.Companion companion18 = Result.INSTANCE;
                        m1202constructorimpl9 = Result.m1202constructorimpl(ResultKt.createFailure(th10));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl9)) {
                        m1202constructorimpl9 = "";
                    }
                    String packageName = (String) m1202constructorimpl9;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str17);
                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                            if (stackTraceElement6 == null || (className9 = stackTraceElement6.getClassName()) == null || (substringAfterLast$default2 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls6 = getClass();
                                String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : null;
                                str19 = canonicalName6 == null ? "N/A" : canonicalName6;
                            } else {
                                str19 = substringAfterLast$default2;
                            }
                            Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str19);
                            if (matcher7.find()) {
                                str19 = matcher7.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str19, "replaceAll(\"\")");
                            }
                            Unit unit6 = Unit.INSTANCE;
                            if (str19.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str19 = str19.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str19, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb6 = new StringBuilder();
                            String str31 = "startEncoding() mediaCodec.configure: " + m1205exceptionOrNullimpl4.getClass() + ": " + m1205exceptionOrNullimpl4.getMessage();
                            sb6.append(str31 == null ? "null " : str31);
                            sb6.append(' ');
                            sb6.append("");
                            Log.println(6, str19, sb6.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                HyperLogger companion19 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb7 = new StringBuilder();
                StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace7, "Throwable().stackTrace");
                StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                if (stackTraceElement7 == null || (className4 = stackTraceElement7.getClassName()) == null) {
                    str5 = "packageName";
                    str6 = "Throwable().stackTrace";
                } else {
                    str5 = "packageName";
                    str6 = "Throwable().stackTrace";
                    str7 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls7 = getClass();
                String canonicalName7 = cls7 != null ? cls7.getCanonicalName() : null;
                str7 = canonicalName7 == null ? "N/A" : canonicalName7;
                Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                if (matcher8.find()) {
                    str7 = matcher8.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                }
                Unit unit7 = Unit.INSTANCE;
                if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str7 = str7.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb7.append(str7);
                sb7.append(" - ");
                String str32 = "startEncoding() MediaCodec.createByCodecName: " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                if (str32 == null) {
                    str32 = "null ";
                }
                sb7.append(str32);
                sb7.append(' ');
                sb7.append("");
                companion19.log(level4, sb7.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion20 = Result.INSTANCE;
                    Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke4, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                } catch (Throwable th11) {
                    Result.Companion companion21 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th11));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str33 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str33, str5);
                    if (StringsKt.contains$default((CharSequence) str33, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace8, str6);
                        StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                        if (stackTraceElement8 == null || (className3 = stackTraceElement8.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls8 = getClass();
                            String canonicalName8 = cls8 != null ? cls8.getCanonicalName() : null;
                            str8 = canonicalName8 == null ? "N/A" : canonicalName8;
                        } else {
                            str8 = substringAfterLast$default;
                        }
                        Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str8);
                        if (matcher9.find()) {
                            str8 = matcher9.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str8, "replaceAll(\"\")");
                        }
                        Unit unit8 = Unit.INSTANCE;
                        if (str8.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str8 = str8.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb8 = new StringBuilder();
                        String str34 = "startEncoding() MediaCodec.createByCodecName: " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                        sb8.append(str34 == null ? "null " : str34);
                        sb8.append(' ');
                        sb8.append("");
                        Log.println(6, str8, sb8.toString());
                        return;
                    }
                    return;
                }
                return;
            }
            HyperLogger.Level level5 = HyperLogger.Level.ERROR;
            HyperLogger companion22 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb9 = new StringBuilder();
            StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace9, "Throwable().stackTrace");
            StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
            if (stackTraceElement9 == null || (className12 = stackTraceElement9.getClassName()) == null) {
                str20 = "N/A";
                str21 = "Throwable().stackTrace";
            } else {
                str20 = "N/A";
                str21 = "Throwable().stackTrace";
                str22 = StringsKt.substringAfterLast$default(className12, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls9 = getClass();
            String canonicalName9 = cls9 != null ? cls9.getCanonicalName() : null;
            str22 = canonicalName9 == null ? str20 : canonicalName9;
            Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str22);
            if (matcher10.find()) {
                str22 = matcher10.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str22, "replaceAll(\"\")");
            }
            Unit unit9 = Unit.INSTANCE;
            if (str22.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str22 = str22.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str22, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb9.append(str22);
            sb9.append(" - ");
            sb9.append("startEncoding() Unable to find an appropriate codec for video/avc");
            sb9.append(' ');
            sb9.append("");
            companion22.log(level5, sb9.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion23 = Result.INSTANCE;
                Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke5, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl10 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
            } catch (Throwable th12) {
                Result.Companion companion24 = Result.INSTANCE;
                m1202constructorimpl10 = Result.m1202constructorimpl(ResultKt.createFailure(th12));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl10)) {
                m1202constructorimpl10 = "";
            }
            String packageName2 = (String) m1202constructorimpl10;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace10, str21);
                    StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                    if (stackTraceElement10 == null || (className11 = stackTraceElement10.getClassName()) == null || (substringAfterLast$default3 = StringsKt.substringAfterLast$default(className11, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls10 = getClass();
                        String canonicalName10 = cls10 != null ? cls10.getCanonicalName() : null;
                        str23 = canonicalName10 == null ? str20 : canonicalName10;
                    } else {
                        str23 = substringAfterLast$default3;
                    }
                    Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str23);
                    if (matcher11.find()) {
                        str23 = matcher11.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str23, "replaceAll(\"\")");
                    }
                    Unit unit10 = Unit.INSTANCE;
                    if (str23.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str23 = str23.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str23, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(6, str23, "startEncoding() Unable to find an appropriate codec for video/avc ");
                }
            }
        } catch (IOException e) {
            HyperLogger.Level level6 = HyperLogger.Level.ERROR;
            HyperLogger companion25 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb10 = new StringBuilder();
            StackTraceElement[] stackTrace11 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace11, "Throwable().stackTrace");
            StackTraceElement stackTraceElement11 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace11);
            if (stackTraceElement11 == null || (className2 = stackTraceElement11.getClassName()) == null) {
                str = "Throwable().stackTrace";
            } else {
                str = "Throwable().stackTrace";
                str2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls11 = getClass();
            String canonicalName11 = cls11 != null ? cls11.getCanonicalName() : null;
            str2 = canonicalName11 == null ? "N/A" : canonicalName11;
            Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
            if (matcher12.find()) {
                str2 = matcher12.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
            }
            Unit unit11 = Unit.INSTANCE;
            if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str2 = str2.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb10.append(str2);
            sb10.append(" - ");
            String str35 = "startEncoding() " + e.getMessage();
            if (str35 == null) {
                str35 = "null ";
            }
            sb10.append(str35);
            sb10.append(' ');
            sb10.append("");
            companion25.log(level6, sb10.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion26 = Result.INSTANCE;
                Object invoke6 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke6, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
            } catch (Throwable th13) {
                Result.Companion companion27 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th13));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName3 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace12 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace12, str);
                    StackTraceElement stackTraceElement12 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace12);
                    if (stackTraceElement12 == null || (className = stackTraceElement12.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        String substringAfterLast$default4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default4 != null) {
                            str4 = substringAfterLast$default4;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher.find()) {
                                str4 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                            }
                            Unit unit12 = Unit.INSTANCE;
                            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb11 = new StringBuilder();
                            String str36 = "startEncoding() " + e.getMessage();
                            sb11.append(str36 != null ? "null " : str36);
                            sb11.append(' ');
                            sb11.append("");
                            Log.println(6, str4, sb11.toString());
                        }
                    }
                    Class<?> cls12 = getClass();
                    String canonicalName12 = cls12 != null ? cls12.getCanonicalName() : str3;
                    str4 = canonicalName12 == null ? "N/A" : canonicalName12;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                    if (matcher.find()) {
                    }
                    Unit unit122 = Unit.INSTANCE;
                    if (str4.length() > 23) {
                        str4 = str4.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb112 = new StringBuilder();
                    String str362 = "startEncoding() " + e.getMessage();
                    sb112.append(str362 != null ? "null " : str362);
                    sb112.append(' ');
                    sb112.append("");
                    Log.println(6, str4, sb112.toString());
                }
            }
        }
    }

    public final void queueFrame$hyperkyc_release(Bitmap bitmap) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String className;
        String className2;
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
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("queueFrame() Failed to queue frame. Encoding not started");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = "";
        }
        String packageName = (String) m1202constructorimpl;
        if (CoreExtsKt.isDebug()) {
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls2 = getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    str = canonicalName2 == null ? "N/A" : canonicalName2;
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                Log.println(3, str, "queueFrame() Failed to queue frame. Encoding not started ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:243:0x097b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0002 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void encode() {
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        String canonicalName;
        Object m1202constructorimpl2;
        String str;
        String className;
        String className2;
        MediaCodec.BufferInfo bufferInfo;
        int dequeueOutputBuffer;
        String canonicalName2;
        Object m1202constructorimpl3;
        String canonicalName3;
        String className3;
        String className4;
        String canonicalName4;
        Object m1202constructorimpl4;
        String canonicalName5;
        String className5;
        String className6;
        String canonicalName6;
        Object m1202constructorimpl5;
        String canonicalName7;
        String className7;
        String className8;
        String canonicalName8;
        Object m1202constructorimpl6;
        String canonicalName9;
        String className9;
        String className10;
        MediaCodec mediaCodec;
        Object m1202constructorimpl7;
        String canonicalName10;
        Object m1202constructorimpl8;
        String canonicalName11;
        String className11;
        String className12;
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
                        HVBitmapToVideoEncoder hVBitmapToVideoEncoder = this;
                        this.mNewFrameLatch.await();
                        m1202constructorimpl7 = Result.m1202constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl7);
                    if (m1205exceptionOrNullimpl2 != null) {
                        HyperLogger.Level level = HyperLogger.Level.ERROR;
                        HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb = new StringBuilder();
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        if (stackTraceElement == null || (className12 = stackTraceElement.getClassName()) == null || (canonicalName10 = StringsKt.substringAfterLast$default(className12, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls = getClass();
                            canonicalName10 = cls != null ? cls.getCanonicalName() : null;
                            if (canonicalName10 == null) {
                                canonicalName10 = "N/A";
                            }
                        }
                        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName10);
                        if (matcher.find()) {
                            canonicalName10 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName10, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (canonicalName10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName10 = canonicalName10.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName10, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb.append(canonicalName10);
                        sb.append(" - ");
                        String str2 = "encode() " + m1205exceptionOrNullimpl2.getMessage();
                        if (str2 == null) {
                            str2 = "null ";
                        }
                        sb.append(str2);
                        sb.append(' ');
                        sb.append("");
                        companion3.log(level, sb.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl8 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                            m1202constructorimpl8 = "";
                        }
                        String packageName = (String) m1202constructorimpl8;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className11 = stackTraceElement2.getClassName()) == null || (canonicalName11 = StringsKt.substringAfterLast$default(className11, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls2 = getClass();
                                    canonicalName11 = cls2 != null ? cls2.getCanonicalName() : null;
                                    if (canonicalName11 == null) {
                                        canonicalName11 = "N/A";
                                    }
                                }
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName11);
                                if (matcher2.find()) {
                                    canonicalName11 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName11, "replaceAll(\"\")");
                                }
                                Unit unit3 = Unit.INSTANCE;
                                if (canonicalName11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName11 = canonicalName11.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName11, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb2 = new StringBuilder();
                                String str3 = "encode() " + m1205exceptionOrNullimpl2.getMessage();
                                if (str3 == null) {
                                    str3 = "null ";
                                }
                                sb2.append(str3);
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(6, canonicalName11, sb2.toString());
                            }
                        }
                    }
                    poll = this.mEncodeQueue.poll();
                }
                if (poll != null) {
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        HVBitmapToVideoEncoder hVBitmapToVideoEncoder2 = this;
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
                            if (length <= (inputBuffer != null ? inputBuffer.remaining() : 0)) {
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
                                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                if (stackTraceElement3 == null || (className10 = stackTraceElement3.getClassName()) == null || (canonicalName8 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls3 = getClass();
                                    canonicalName8 = cls3 != null ? cls3.getCanonicalName() : null;
                                    if (canonicalName8 == null) {
                                        canonicalName8 = "N/A";
                                    }
                                }
                                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName8);
                                if (matcher3.find()) {
                                    canonicalName8 = matcher3.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName8, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (canonicalName8.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName8 = canonicalName8.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName8, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb3.append(canonicalName8);
                                sb3.append(" - ");
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("encode() Frame size exceeds buffer capacity: ");
                                sb4.append(length);
                                sb4.append(" > ");
                                sb4.append(inputBuffer != null ? Integer.valueOf(inputBuffer.remaining()) : null);
                                String sb5 = sb4.toString();
                                if (sb5 == null) {
                                    sb5 = "null ";
                                }
                                sb3.append(sb5);
                                sb3.append(' ');
                                sb3.append("");
                                companion7.log(level2, sb3.toString());
                                CoreExtsKt.isRelease();
                                try {
                                    Result.Companion companion8 = Result.INSTANCE;
                                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                    m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th3) {
                                    Result.Companion companion9 = Result.INSTANCE;
                                    m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                    m1202constructorimpl6 = "";
                                }
                                String packageName2 = (String) m1202constructorimpl6;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className9 = stackTraceElement4.getClassName()) == null || (canonicalName9 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls4 = getClass();
                                            canonicalName9 = cls4 != null ? cls4.getCanonicalName() : null;
                                            if (canonicalName9 == null) {
                                                canonicalName9 = "N/A";
                                            }
                                        }
                                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName9);
                                        if (matcher4.find()) {
                                            canonicalName9 = matcher4.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName9, "replaceAll(\"\")");
                                        }
                                        Unit unit5 = Unit.INSTANCE;
                                        if (canonicalName9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName9 = canonicalName9.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName9, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb6 = new StringBuilder();
                                        StringBuilder sb7 = new StringBuilder();
                                        sb7.append("encode() Frame size exceeds buffer capacity: ");
                                        sb7.append(length);
                                        sb7.append(" > ");
                                        sb7.append(inputBuffer != null ? Integer.valueOf(inputBuffer.remaining()) : null);
                                        String sb8 = sb7.toString();
                                        if (sb8 == null) {
                                            sb8 = "null ";
                                        }
                                        sb6.append(sb8);
                                        sb6.append(' ');
                                        sb6.append("");
                                        Log.println(6, canonicalName9, sb6.toString());
                                    }
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
                    } catch (Throwable th4) {
                        Result.Companion companion10 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th4));
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
                                    HyperLogger companion11 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb9 = new StringBuilder();
                                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                    if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls5 = getClass();
                                        canonicalName2 = cls5 != null ? cls5.getCanonicalName() : null;
                                        if (canonicalName2 == null) {
                                            canonicalName2 = "N/A";
                                        }
                                    }
                                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                    if (matcher5.find()) {
                                        canonicalName2 = matcher5.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                    }
                                    Unit unit6 = Unit.INSTANCE;
                                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        canonicalName2 = canonicalName2.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb9.append(canonicalName2);
                                    sb9.append(" - ");
                                    String str4 = "encode() encoderOutputBuffer " + dequeueOutputBuffer + " was null";
                                    if (str4 == null) {
                                        str4 = "null ";
                                    }
                                    sb9.append(str4);
                                    sb9.append(' ');
                                    sb9.append("");
                                    companion11.log(level3, sb9.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion12 = Result.INSTANCE;
                                        Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                    } catch (Throwable th5) {
                                        Result.Companion companion13 = Result.INSTANCE;
                                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                        m1202constructorimpl3 = "";
                                    }
                                    String packageName3 = (String) m1202constructorimpl3;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                        if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                            if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                Class<?> cls6 = getClass();
                                                canonicalName3 = cls6 != null ? cls6.getCanonicalName() : null;
                                                if (canonicalName3 == null) {
                                                    canonicalName3 = "N/A";
                                                }
                                            }
                                            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                            if (matcher6.find()) {
                                                canonicalName3 = matcher6.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                            }
                                            Unit unit7 = Unit.INSTANCE;
                                            if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                canonicalName3 = canonicalName3.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb10 = new StringBuilder();
                                            String str5 = "encode() encoderOutputBuffer " + dequeueOutputBuffer + " was null";
                                            if (str5 == null) {
                                                str5 = "null ";
                                            }
                                            sb10.append(str5);
                                            sb10.append(' ');
                                            sb10.append("");
                                            Log.println(6, canonicalName3, sb10.toString());
                                        }
                                    }
                                }
                            }
                        } else {
                            HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                            HyperLogger companion14 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb11 = new StringBuilder();
                            StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace7, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                            if (stackTraceElement7 == null || (className6 = stackTraceElement7.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls7 = getClass();
                                canonicalName4 = cls7 != null ? cls7.getCanonicalName() : null;
                                if (canonicalName4 == null) {
                                    canonicalName4 = "N/A";
                                }
                            }
                            Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                            if (matcher7.find()) {
                                canonicalName4 = matcher7.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                            }
                            Unit unit8 = Unit.INSTANCE;
                            if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName4 = canonicalName4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb11.append(canonicalName4);
                            sb11.append(" - ");
                            String str6 = "encode() Unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer;
                            if (str6 == null) {
                                str6 = "null ";
                            }
                            sb11.append(str6);
                            sb11.append(' ');
                            sb11.append("");
                            companion14.log(level4, sb11.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion15 = Result.INSTANCE;
                                Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke4, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                            } catch (Throwable th6) {
                                Result.Companion companion16 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String packageName4 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName4, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName4, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace8, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                    if (stackTraceElement8 == null || (className5 = stackTraceElement8.getClassName()) == null || (canonicalName5 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls8 = getClass();
                                        canonicalName5 = cls8 != null ? cls8.getCanonicalName() : null;
                                        if (canonicalName5 == null) {
                                            canonicalName5 = "N/A";
                                        }
                                    }
                                    Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName5);
                                    if (matcher8.find()) {
                                        canonicalName5 = matcher8.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "replaceAll(\"\")");
                                    }
                                    Unit unit9 = Unit.INSTANCE;
                                    if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        canonicalName5 = canonicalName5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb12 = new StringBuilder();
                                    String str7 = "encode() Unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer;
                                    if (str7 == null) {
                                        str7 = "null ";
                                    }
                                    sb12.append(str7);
                                    sb12.append(' ');
                                    sb12.append("");
                                    Log.println(6, canonicalName5, sb12.toString());
                                }
                            }
                        }
                        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                        if (m1205exceptionOrNullimpl == null) {
                        }
                    } else {
                        HyperLogger.Level level5 = HyperLogger.Level.ERROR;
                        HyperLogger companion17 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb13 = new StringBuilder();
                        StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace9, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                        if (stackTraceElement9 == null || (className8 = stackTraceElement9.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls9 = getClass();
                            canonicalName6 = cls9 != null ? cls9.getCanonicalName() : null;
                            if (canonicalName6 == null) {
                                canonicalName6 = "N/A";
                            }
                        }
                        Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName6);
                        if (matcher9.find()) {
                            canonicalName6 = matcher9.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName6, "replaceAll(\"\")");
                        }
                        Unit unit10 = Unit.INSTANCE;
                        if (canonicalName6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName6 = canonicalName6.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName6, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb13.append(canonicalName6);
                        sb13.append(" - ");
                        sb13.append("encode() No output from encoder available");
                        sb13.append(' ');
                        sb13.append("");
                        companion17.log(level5, sb13.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion18 = Result.INSTANCE;
                            Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke5, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                        } catch (Throwable th7) {
                            Result.Companion companion19 = Result.INSTANCE;
                            m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                            m1202constructorimpl5 = "";
                        }
                        String packageName5 = (String) m1202constructorimpl5;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName5, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName5, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace10, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                                if (stackTraceElement10 == null || (className7 = stackTraceElement10.getClassName()) == null || (canonicalName7 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls10 = getClass();
                                    canonicalName7 = cls10 != null ? cls10.getCanonicalName() : null;
                                    if (canonicalName7 == null) {
                                        canonicalName7 = "N/A";
                                    }
                                }
                                Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName7);
                                if (matcher10.find()) {
                                    canonicalName7 = matcher10.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName7, "replaceAll(\"\")");
                                }
                                Unit unit11 = Unit.INSTANCE;
                                if (canonicalName7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName7 = canonicalName7.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(6, canonicalName7, "encode() No output from encoder available ");
                            }
                        }
                        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                        if (m1205exceptionOrNullimpl == null) {
                            HyperLogger.Level level6 = HyperLogger.Level.ERROR;
                            HyperLogger companion20 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb14 = new StringBuilder();
                            StackTraceElement[] stackTrace11 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace11, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement11 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace11);
                            if (stackTraceElement11 == null || (className2 = stackTraceElement11.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls11 = getClass();
                                canonicalName = cls11 != null ? cls11.getCanonicalName() : null;
                                if (canonicalName == null) {
                                    canonicalName = "N/A";
                                }
                            }
                            Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                            if (matcher11.find()) {
                                canonicalName = matcher11.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                            }
                            Unit unit12 = Unit.INSTANCE;
                            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName = canonicalName.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb14.append(canonicalName);
                            sb14.append(" - ");
                            String str8 = "encode() " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                            if (str8 == null) {
                                str8 = "null ";
                            }
                            sb14.append(str8);
                            sb14.append(' ');
                            sb14.append("");
                            companion20.log(level6, sb14.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion21 = Result.INSTANCE;
                                Object invoke6 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke6, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                            } catch (Throwable th8) {
                                Result.Companion companion22 = Result.INSTANCE;
                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = "";
                            }
                            String packageName6 = (String) m1202constructorimpl2;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName6, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName6, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace12 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace12, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement12 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace12);
                                    if (stackTraceElement12 == null || (className = stackTraceElement12.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls12 = getClass();
                                        String canonicalName12 = cls12 != null ? cls12.getCanonicalName() : null;
                                        str = canonicalName12 == null ? "N/A" : canonicalName12;
                                    }
                                    Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                                    if (matcher12.find()) {
                                        str = matcher12.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                                    }
                                    Unit unit13 = Unit.INSTANCE;
                                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str = str.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb15 = new StringBuilder();
                                    String str9 = "encode() " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                                    if (str9 == null) {
                                        str9 = "null ";
                                    }
                                    sb15.append(str9);
                                    sb15.append(' ');
                                    sb15.append("");
                                    Log.println(6, str, sb15.toString());
                                }
                            }
                            if ((m1205exceptionOrNullimpl instanceof IllegalStateException) && ContextExtsKt.isLowStorage(this.mCallback.get$context())) {
                                release();
                                throw m1205exceptionOrNullimpl;
                            }
                        } else {
                            continue;
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

    /* JADX WARN: Can't wrap try/catch for region: R(40:1|2|3|(1:5)|6|7|8|9|(3:229|230|(34:232|233|234|(25:236|17|(1:19)|20|(1:25)|26|27|(1:29)(8:180|181|182|(1:184)|185|(3:189|190|(11:192|193|194|195|(3:212|(1:214)(1:217)|(1:216))(1:201)|202|(1:204)|205|(1:210)|211|188))|187|188)|30|31|(1:33)|34|(3:170|(1:172)(1:176)|(1:174)(1:175))(1:40)|41|(1:43)|44|(1:49)|50|(6:135|136|137|(1:139)|140|(2:142|(7:144|(3:161|(1:163)(1:166)|(1:165))(1:150)|151|(1:153)|154|(1:159)|160)))|52|53|54|55|56|(18:58|(1:126)(1:62)|64|(1:66)(1:70)|(1:68)(1:69)|71|(1:73)|74|(1:78)|79|(1:81)|82|83|84|85|(1:87)|88|(2:90|(13:92|(1:120)(2:96|(9:98|99|(1:101)|102|(1:106)|107|(1:109)|110|111))|113|(1:115)(1:119)|(1:117)(1:118)|99|(0)|102|(2:104|106)|107|(0)|110|111)(1:121))(1:122))(1:127))|12|(1:14)(1:228)|(1:16)(1:227)|17|(0)|20|(2:22|25)|26|27|(0)(0)|30|31|(0)|34|(1:36)|170|(0)(0)|(0)(0)|41|(0)|44|(2:46|49)|50|(0)|52|53|54|55|56|(0)(0)))|11|12|(0)(0)|(0)(0)|17|(0)|20|(0)|26|27|(0)(0)|30|31|(0)|34|(0)|170|(0)(0)|(0)(0)|41|(0)|44|(0)|50|(0)|52|53|54|55|56|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0347, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0349, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x039f, code lost:
    
        if (r15 != null) goto L179;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0266 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007c A[Catch: all -> 0x034c, TryCatch #7 {all -> 0x034c, blocks: (B:234:0x0064, B:17:0x0088, B:19:0x0099, B:20:0x00a0, B:22:0x00aa, B:25:0x00b1, B:26:0x00b9, B:182:0x010b, B:185:0x0112, B:224:0x0101, B:12:0x0076, B:14:0x007c, B:181:0x00de), top: B:233:0x0064, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0207 A[Catch: all -> 0x01ff, TryCatch #10 {all -> 0x01ff, blocks: (B:36:0x01ee, B:38:0x01f4, B:41:0x0213, B:43:0x0224, B:44:0x022b, B:46:0x0235, B:49:0x023c, B:50:0x0244, B:137:0x0293, B:140:0x029a, B:142:0x02a2, B:144:0x02b4, B:146:0x02ca, B:148:0x02d0, B:151:0x02eb, B:153:0x02fc, B:154:0x0303, B:156:0x030d, B:159:0x0314, B:160:0x031c, B:161:0x02db, B:163:0x02e1, B:169:0x0289, B:52:0x0336, B:170:0x0201, B:172:0x0207, B:195:0x0137, B:197:0x0144, B:199:0x014a, B:202:0x0165, B:204:0x0176, B:205:0x017d, B:207:0x0187, B:210:0x018e, B:211:0x0196, B:212:0x0155, B:214:0x015b, B:136:0x0266), top: B:194:0x0137, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x00de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0099 A[Catch: all -> 0x034c, TryCatch #7 {all -> 0x034c, blocks: (B:234:0x0064, B:17:0x0088, B:19:0x0099, B:20:0x00a0, B:22:0x00aa, B:25:0x00b1, B:26:0x00b9, B:182:0x010b, B:185:0x0112, B:224:0x0101, B:12:0x0076, B:14:0x007c, B:181:0x00de), top: B:233:0x0064, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa A[Catch: all -> 0x034c, TryCatch #7 {all -> 0x034c, blocks: (B:234:0x0064, B:17:0x0088, B:19:0x0099, B:20:0x00a0, B:22:0x00aa, B:25:0x00b1, B:26:0x00b9, B:182:0x010b, B:185:0x0112, B:224:0x0101, B:12:0x0076, B:14:0x007c, B:181:0x00de), top: B:233:0x0064, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01bd A[Catch: all -> 0x0349, TryCatch #5 {all -> 0x0349, blocks: (B:31:0x01b9, B:33:0x01bd, B:34:0x01c3), top: B:30:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01ee A[Catch: all -> 0x01ff, TRY_ENTER, TryCatch #10 {all -> 0x01ff, blocks: (B:36:0x01ee, B:38:0x01f4, B:41:0x0213, B:43:0x0224, B:44:0x022b, B:46:0x0235, B:49:0x023c, B:50:0x0244, B:137:0x0293, B:140:0x029a, B:142:0x02a2, B:144:0x02b4, B:146:0x02ca, B:148:0x02d0, B:151:0x02eb, B:153:0x02fc, B:154:0x0303, B:156:0x030d, B:159:0x0314, B:160:0x031c, B:161:0x02db, B:163:0x02e1, B:169:0x0289, B:52:0x0336, B:170:0x0201, B:172:0x0207, B:195:0x0137, B:197:0x0144, B:199:0x014a, B:202:0x0165, B:204:0x0176, B:205:0x017d, B:207:0x0187, B:210:0x018e, B:211:0x0196, B:212:0x0155, B:214:0x015b, B:136:0x0266), top: B:194:0x0137, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0224 A[Catch: all -> 0x01ff, TryCatch #10 {all -> 0x01ff, blocks: (B:36:0x01ee, B:38:0x01f4, B:41:0x0213, B:43:0x0224, B:44:0x022b, B:46:0x0235, B:49:0x023c, B:50:0x0244, B:137:0x0293, B:140:0x029a, B:142:0x02a2, B:144:0x02b4, B:146:0x02ca, B:148:0x02d0, B:151:0x02eb, B:153:0x02fc, B:154:0x0303, B:156:0x030d, B:159:0x0314, B:160:0x031c, B:161:0x02db, B:163:0x02e1, B:169:0x0289, B:52:0x0336, B:170:0x0201, B:172:0x0207, B:195:0x0137, B:197:0x0144, B:199:0x014a, B:202:0x0165, B:204:0x0176, B:205:0x017d, B:207:0x0187, B:210:0x018e, B:211:0x0196, B:212:0x0155, B:214:0x015b, B:136:0x0266), top: B:194:0x0137, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0235 A[Catch: all -> 0x01ff, TryCatch #10 {all -> 0x01ff, blocks: (B:36:0x01ee, B:38:0x01f4, B:41:0x0213, B:43:0x0224, B:44:0x022b, B:46:0x0235, B:49:0x023c, B:50:0x0244, B:137:0x0293, B:140:0x029a, B:142:0x02a2, B:144:0x02b4, B:146:0x02ca, B:148:0x02d0, B:151:0x02eb, B:153:0x02fc, B:154:0x0303, B:156:0x030d, B:159:0x0314, B:160:0x031c, B:161:0x02db, B:163:0x02e1, B:169:0x0289, B:52:0x0336, B:170:0x0201, B:172:0x0207, B:195:0x0137, B:197:0x0144, B:199:0x014a, B:202:0x0165, B:204:0x0176, B:205:0x017d, B:207:0x0187, B:210:0x018e, B:211:0x0196, B:212:0x0155, B:214:0x015b, B:136:0x0266), top: B:194:0x0137, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x036c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void release() {
        CharSequence charSequence;
        String str;
        String str2;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        String str3;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String str6;
        Matcher matcher;
        String className;
        String className2;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement;
        String className3;
        String str7;
        String substringAfterLast$default;
        Matcher matcher2;
        Object m1202constructorimpl3;
        String canonicalName;
        String className4;
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder;
        MediaMuxer mediaMuxer;
        StackTraceElement stackTraceElement2;
        String str8;
        Matcher matcher3;
        Object m1202constructorimpl4;
        String canonicalName2;
        String className5;
        String className6;
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVBitmapToVideoEncoder hVBitmapToVideoEncoder2 = this;
            MediaCodec mediaCodec = this.mediaCodec;
            if (mediaCodec == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaCodec");
                mediaCodec = null;
            }
            mediaCodec.stop();
            mediaCodec.release();
            Unit unit = Unit.INSTANCE;
            level = HyperLogger.Level.DEBUG;
            companion = HyperLogger.INSTANCE.getInstance();
            sb = new StringBuilder();
            str = "N/A";
            try {
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            } catch (Throwable th) {
                th = th;
                charSequence = "co.hyperverge";
            }
        } catch (Throwable th2) {
            th = th2;
            charSequence = "co.hyperverge";
            str = "N/A";
        }
        if (stackTraceElement != null) {
            try {
                className3 = stackTraceElement.getClassName();
            } catch (Throwable th3) {
                th = th3;
                charSequence = "co.hyperverge";
                str2 = "Throwable().stackTrace";
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl == null) {
                }
            }
            if (className3 != null) {
                charSequence = "co.hyperverge";
                str7 = "Throwable().stackTrace";
                try {
                    substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                } catch (Throwable th4) {
                    th = th4;
                    str2 = str7;
                    Result.Companion companion32 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                if (substringAfterLast$default != null) {
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                    if (matcher2.find()) {
                        substringAfterLast$default = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default);
                    sb.append(" - ");
                    sb.append("release() RELEASE CODEC");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        hVBitmapToVideoEncoder = this;
                        str2 = str7;
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th5) {
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            try {
                                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                    str2 = str7;
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str2);
                                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls = getClass();
                                            canonicalName = cls != null ? cls.getCanonicalName() : null;
                                            if (canonicalName == null) {
                                                canonicalName = str;
                                            }
                                        }
                                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                        if (matcher4.find()) {
                                            canonicalName = matcher4.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                        }
                                        Unit unit3 = Unit.INSTANCE;
                                        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName = canonicalName.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, canonicalName, "release() RELEASE CODEC ");
                                        hVBitmapToVideoEncoder = this;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        Result.Companion companion322 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                        if (m1205exceptionOrNullimpl == null) {
                                        }
                                    }
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                str2 = str7;
                                Result.Companion companion3222 = Result.INSTANCE;
                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                if (m1205exceptionOrNullimpl == null) {
                                }
                            }
                        }
                        str2 = str7;
                        hVBitmapToVideoEncoder = this;
                    }
                    mediaMuxer = hVBitmapToVideoEncoder.mediaMuxer;
                    if (mediaMuxer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaMuxer");
                        mediaMuxer = null;
                    }
                    mediaMuxer.stop();
                    mediaMuxer.release();
                    Unit unit4 = Unit.INSTANCE;
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str2);
                    stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement2 != null || (className6 = stackTraceElement2.getClassName()) == null || (str8 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName3 = cls2 != null ? cls2.getCanonicalName() : null;
                        str8 = canonicalName3 == null ? str : canonicalName3;
                    }
                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str8);
                    if (matcher3.find()) {
                        str8 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str8, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (str8.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str8 = str8.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(str8);
                    sb2.append(" - ");
                    sb2.append("release() RELEASE MUXER");
                    sb2.append(' ');
                    sb2.append("");
                    companion6.log(level2, sb2.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th8) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                            m1202constructorimpl4 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl4;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className5 = stackTraceElement4.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls3 = getClass();
                                    canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                                    if (canonicalName2 == null) {
                                        canonicalName2 = str;
                                    }
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                if (matcher5.find()) {
                                    canonicalName2 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName2 = canonicalName2.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, canonicalName2, "release() RELEASE MUXER ");
                            }
                        }
                    }
                    this.mNewFrameLatch = new CountDownLatch(0);
                    m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl == null) {
                        HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                        HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, str2);
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 == null || (className2 = stackTraceElement5.getClassName()) == null) {
                            str3 = str2;
                        } else {
                            str3 = str2;
                            str4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls4 = getClass();
                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                        str4 = canonicalName4 == null ? str : canonicalName4;
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher6.find()) {
                            str4 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        Unit unit7 = Unit.INSTANCE;
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str4);
                        sb3.append(" - ");
                        String str9 = "release() " + m1205exceptionOrNullimpl.getMessage();
                        if (str9 == null) {
                            str9 = "null ";
                        }
                        sb3.append(str9);
                        sb3.append(' ');
                        sb3.append("");
                        companion9.log(level3, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion10 = Result.INSTANCE;
                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        } catch (Throwable th9) {
                            Result.Companion companion11 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th9));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName3 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str3);
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className = stackTraceElement6.getClassName()) == null) {
                                    str5 = null;
                                } else {
                                    str5 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        str6 = substringAfterLast$default2;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                        if (matcher.find()) {
                                            str6 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                        }
                                        Unit unit8 = Unit.INSTANCE;
                                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str6 = str6.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        String str10 = "release() " + m1205exceptionOrNullimpl.getMessage();
                                        sb4.append(str10 != null ? str10 : "null ");
                                        sb4.append(' ');
                                        sb4.append("");
                                        Log.println(6, str6, sb4.toString());
                                        return;
                                    }
                                }
                                Class<?> cls5 = getClass();
                                String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : str5;
                                str6 = canonicalName5 == null ? str : canonicalName5;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                if (matcher.find()) {
                                }
                                Unit unit82 = Unit.INSTANCE;
                                if (str6.length() > 23) {
                                    str6 = str6.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb42 = new StringBuilder();
                                String str102 = "release() " + m1205exceptionOrNullimpl.getMessage();
                                sb42.append(str102 != null ? str102 : "null ");
                                sb42.append(' ');
                                sb42.append("");
                                Log.println(6, str6, sb42.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                Class<?> cls6 = getClass();
                String canonicalName6 = cls6 == null ? cls6.getCanonicalName() : null;
                substringAfterLast$default = canonicalName6 != null ? str : canonicalName6;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher2.find()) {
                }
                Unit unit22 = Unit.INSTANCE;
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("release() RELEASE CODEC");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                mediaMuxer = hVBitmapToVideoEncoder.mediaMuxer;
                if (mediaMuxer == null) {
                }
                mediaMuxer.stop();
                mediaMuxer.release();
                Unit unit42 = Unit.INSTANCE;
                HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
                HyperLogger companion62 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb22 = new StringBuilder();
                StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace32, str2);
                stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
                if (stackTraceElement2 != null) {
                }
                Class<?> cls22 = getClass();
                if (cls22 != null) {
                }
                if (canonicalName3 == null) {
                }
                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str8);
                if (matcher3.find()) {
                }
                Unit unit52 = Unit.INSTANCE;
                if (str8.length() > 23) {
                    str8 = str8.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb22.append(str8);
                sb22.append(" - ");
                sb22.append("release() RELEASE MUXER");
                sb22.append(' ');
                sb22.append("");
                companion62.log(level22, sb22.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                this.mNewFrameLatch = new CountDownLatch(0);
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl == null) {
                }
            }
        }
        charSequence = "co.hyperverge";
        str7 = "Throwable().stackTrace";
        Class<?> cls62 = getClass();
        if (cls62 == null) {
        }
        if (canonicalName6 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        Unit unit222 = Unit.INSTANCE;
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("release() RELEASE CODEC");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
        }
        mediaMuxer = hVBitmapToVideoEncoder.mediaMuxer;
        if (mediaMuxer == null) {
        }
        mediaMuxer.stop();
        mediaMuxer.release();
        Unit unit422 = Unit.INSTANCE;
        HyperLogger.Level level222 = HyperLogger.Level.DEBUG;
        HyperLogger companion622 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb222 = new StringBuilder();
        StackTraceElement[] stackTrace322 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace322, str2);
        stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace322);
        if (stackTraceElement2 != null) {
        }
        Class<?> cls222 = getClass();
        if (cls222 != null) {
        }
        if (canonicalName3 == null) {
        }
        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str8);
        if (matcher3.find()) {
        }
        Unit unit522 = Unit.INSTANCE;
        if (str8.length() > 23) {
        }
        sb222.append(str8);
        sb222.append(" - ");
        sb222.append("release() RELEASE MUXER");
        sb222.append(' ');
        sb222.append("");
        companion622.log(level222, sb222.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        this.mNewFrameLatch = new CountDownLatch(0);
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl == null) {
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
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = 0;
            while (i5 < width) {
                int i6 = (argb[i3] & 16711680) >> 16;
                int i7 = (argb[i3] & 65280) >> 8;
                int i8 = 255;
                int i9 = (argb[i3] & 255) >> 0;
                int i10 = (((((i6 * 66) + (i7 * 129)) + (i9 * 25)) + 128) >> 8) + 16;
                int i11 = (((((i6 * (-38)) - (i7 * 74)) + (i9 * 112)) + 128) >> 8) + 128;
                int i12 = (((((i6 * 112) - (i7 * 94)) - (i9 * 18)) + 128) >> 8) + 128;
                int i13 = i2 + 1;
                if (i10 < 0) {
                    i10 = 0;
                } else if (i10 > 255) {
                    i10 = 255;
                }
                yuv420sp[i2] = (byte) i10;
                if (i4 % 2 == 0 && i3 % 2 == 0) {
                    int i14 = i + 1;
                    if (i11 < 0) {
                        i11 = 0;
                    } else if (i11 > 255) {
                        i11 = 255;
                    }
                    yuv420sp[i] = (byte) i11;
                    i = i14 + 1;
                    if (i12 < 0) {
                        i8 = 0;
                    } else if (i12 <= 255) {
                        i8 = i12;
                    }
                    yuv420sp[i14] = (byte) i8;
                }
                i3++;
                i5++;
                i2 = i13;
            }
        }
    }

    private final long computePresentationTime(long frameIndex) {
        return (frameIndex * 1000000) / FRAME_RATE;
    }

    /* compiled from: HVBitmapToVideoEncoder.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/HVBitmapToVideoEncoder$Companion;", "", "()V", "BIT_RATE", "", "FRAME_RATE", "I_FRAME_INTERVAL", "MIME_TYPE", "", "mHeight", "mWidth", "selectCodec", "Landroid/media/MediaCodecInfo;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
            for (int i = 0; i < codecCount; i++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
                if (codecInfoAt.isEncoder()) {
                    for (String str : codecInfoAt.getSupportedTypes()) {
                        if (StringsKt.equals(str, "video/avc", true)) {
                            return codecInfoAt;
                        }
                    }
                }
            }
            return null;
        }
    }

    static {
        FRAME_RATE = SessionRecorderUtilsKt.getIsAndroid8Plus() ? 8 : 5;
    }

    public final void stopEncoding$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String canonicalName3;
        Object m1202constructorimpl2;
        String str;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("stopEncoding() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher2.find()) {
                        canonicalName2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, canonicalName2, "stopEncoding() called ");
                }
            }
        }
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
        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2 = new StringBuilder();
        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls3 = getClass();
            canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
            if (canonicalName3 == null) {
                canonicalName3 = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
        if (matcher3.find()) {
            canonicalName3 = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
        }
        if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName3 = canonicalName3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb2.append(canonicalName3);
        sb2.append(" - ");
        sb2.append("stopEncoding() Failed to stop encoding since it never started");
        sb2.append(' ');
        sb2.append("");
        companion4.log(level2, sb2.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
        } catch (Throwable th2) {
            Result.Companion companion6 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
            m1202constructorimpl2 = "";
        }
        String packageName2 = (String) m1202constructorimpl2;
        if (CoreExtsKt.isDebug()) {
            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls4 = getClass();
                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                    str = canonicalName4 == null ? "N/A" : canonicalName4;
                }
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                if (matcher4.find()) {
                    str = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                Log.println(3, str, "stopEncoding() Failed to stop encoding since it never started ");
            }
        }
    }
}
