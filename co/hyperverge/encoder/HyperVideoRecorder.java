package co.hyperverge.encoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.Window;
import co.hyperverge.encoder.HyperVideoEncoder;
import co.hyperverge.encoder.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.concurrent.TimersKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HyperVideoRecorder.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J&\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 J\u0012\u0010!\u001a\u00020\u00132\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lco/hyperverge/encoder/HyperVideoRecorder;", "", "()V", "bitmapToVideoEncoder", "Lco/hyperverge/encoder/HyperVideoEncoder;", "cameraPreviewTag", "", "currentWindow", "Landroid/view/Window;", "isRecordingStarted", "", "mCallback", "Lco/hyperverge/encoder/HyperVideoListener;", "outputVideoFile", "Ljava/io/File;", WorkflowModule.Properties.Section.Component.Type.TIMER, "Ljava/util/Timer;", "videoPath", "createBitmaps", "", "previewToBitmap", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "queueBitmap", "bitmap", "Landroid/graphics/Bitmap;", "start", "context", "Landroid/content/Context;", "window", "videoFile", "hyperVideoLowStorageExceptionListener", "Lco/hyperverge/encoder/HyperVideoLowStorageExceptionListener;", "stop", "callback", "Companion", "hv-bitmaps-to-video_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperVideoRecorder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static HyperVideoRecorder instance;
    private HyperVideoEncoder bitmapToVideoEncoder;
    private String cameraPreviewTag;
    private Window currentWindow;
    private boolean isRecordingStarted;
    private HyperVideoListener mCallback;
    private File outputVideoFile;
    private Timer timer;
    private String videoPath;

    public /* synthetic */ HyperVideoRecorder(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private HyperVideoRecorder() {
        this.videoPath = "";
    }

    public static /* synthetic */ void stop$default(HyperVideoRecorder hyperVideoRecorder, HyperVideoListener hyperVideoListener, int i, Object obj) {
        if ((i & 1) != 0) {
            hyperVideoListener = null;
        }
        hyperVideoRecorder.stop(hyperVideoListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void previewToBitmap(View view) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new HyperVideoRecorder$previewToBitmap$1(this, view, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void queueBitmap(Bitmap bitmap) {
        HyperVideoEncoder hyperVideoEncoder = this.bitmapToVideoEncoder;
        HyperVideoEncoder hyperVideoEncoder2 = null;
        if (hyperVideoEncoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
            hyperVideoEncoder = null;
        }
        if (!hyperVideoEncoder.isEncodingStarted$hv_bitmaps_to_video_release() && this.isRecordingStarted) {
            File file = this.outputVideoFile;
            if (file == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outputVideoFile");
                file = null;
            }
            HyperVideoEncoder hyperVideoEncoder3 = this.bitmapToVideoEncoder;
            if (hyperVideoEncoder3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
                hyperVideoEncoder3 = null;
            }
            hyperVideoEncoder3.startEncoding$hv_bitmaps_to_video_release(bitmap.getWidth(), bitmap.getHeight(), file);
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
            this.videoPath = absolutePath;
        }
        HyperVideoEncoder hyperVideoEncoder4 = this.bitmapToVideoEncoder;
        if (hyperVideoEncoder4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
        } else {
            hyperVideoEncoder2 = hyperVideoEncoder4;
        }
        hyperVideoEncoder2.queueFrame$hv_bitmaps_to_video_release(bitmap);
    }

    /* compiled from: HyperVideoRecorder.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lco/hyperverge/encoder/HyperVideoRecorder$Companion;", "", "()V", "instance", "Lco/hyperverge/encoder/HyperVideoRecorder;", "getInstance", "hv-bitmaps-to-video_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HyperVideoRecorder getInstance() {
            if (HyperVideoRecorder.instance == null) {
                HyperVideoRecorder.instance = new HyperVideoRecorder(null);
            }
            HyperVideoRecorder hyperVideoRecorder = HyperVideoRecorder.instance;
            Objects.requireNonNull(hyperVideoRecorder, "null cannot be cast to non-null type co.hyperverge.encoder.HyperVideoRecorder");
            return hyperVideoRecorder;
        }
    }

    public final void start(final Context context, Window window, File videoFile, final HyperVideoLowStorageExceptionListener hyperVideoLowStorageExceptionListener) {
        String className;
        String className2;
        String canonicalName;
        Class<?> cls;
        Window window2 = window;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(window2, "window");
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        Intrinsics.checkNotNullParameter(hyperVideoLowStorageExceptionListener, "hyperVideoLowStorageExceptionListener");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && ((cls = getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String str2 = "start() called with: window = " + window2 + ", videoFile = " + videoFile;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (this.isRecordingStarted) {
            return;
        }
        this.currentWindow = window2;
        if (window2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWindow");
            window2 = null;
        }
        Object tag = window2.getDecorView().getRootView().getTag();
        this.cameraPreviewTag = tag == null ? null : tag.toString();
        this.outputVideoFile = videoFile;
        this.bitmapToVideoEncoder = new HyperVideoEncoder(new HyperVideoEncoder.HyperVideoEncoderCallback() { // from class: co.hyperverge.encoder.HyperVideoRecorder$start$2
            @Override // co.hyperverge.encoder.HyperVideoEncoder.HyperVideoEncoderCallback
            /* renamed from: getContext, reason: from getter */
            public Context get$context() {
                return context;
            }

            @Override // co.hyperverge.encoder.HyperVideoEncoder.HyperVideoEncoderCallback
            public void onEncodingComplete(File outputFile) {
                String className3;
                HyperVideoListener hyperVideoListener;
                Class<?> cls2;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                String substringAfterLast$default2 = (stackTraceElement2 == null || (className3 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                if (substringAfterLast$default2 == null && ((cls2 = getClass()) == null || (substringAfterLast$default2 = cls2.getCanonicalName()) == null)) {
                    substringAfterLast$default2 = "N/A";
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                if (matcher2.find()) {
                    substringAfterLast$default2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
                }
                if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                sb2.append(substringAfterLast$default2);
                sb2.append(" - ");
                String stringPlus = Intrinsics.stringPlus("onEncodingComplete() called with: outputFile = ", outputFile);
                if (stringPlus == null) {
                    stringPlus = "null ";
                }
                sb2.append(stringPlus);
                sb2.append(' ');
                sb2.append("");
                companion2.log(level2, sb2.toString());
                hyperVideoListener = HyperVideoRecorder.this.mCallback;
                if (hyperVideoListener == null) {
                    return;
                }
                hyperVideoListener.invoke(outputFile);
            }

            @Override // co.hyperverge.encoder.HyperVideoEncoder.HyperVideoEncoderCallback
            public void onLowStorage() {
                String className3;
                Class<?> cls2;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                String substringAfterLast$default2 = (stackTraceElement2 == null || (className3 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                if (substringAfterLast$default2 == null && ((cls2 = getClass()) == null || (substringAfterLast$default2 = cls2.getCanonicalName()) == null)) {
                    substringAfterLast$default2 = "N/A";
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                if (matcher2.find()) {
                    substringAfterLast$default2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
                }
                if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                sb2.append(substringAfterLast$default2);
                sb2.append(" - ");
                sb2.append("onLowStorage() called");
                sb2.append(' ');
                sb2.append("");
                companion2.log(level2, sb2.toString());
                HyperVideoRecorder.stop$default(HyperVideoRecorder.this, null, 1, null);
                hyperVideoLowStorageExceptionListener.invoke();
            }
        });
        this.isRecordingStarted = true;
        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
        HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2 = new StringBuilder();
        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
        String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default2 == null) {
            Class<?> cls2 = getClass();
            if (cls2 != null && (canonicalName = cls2.getCanonicalName()) != null) {
                str = canonicalName;
            }
        } else {
            str = substringAfterLast$default2;
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
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
        sb2.append("start() HyperVideo started");
        sb2.append(' ');
        sb2.append("");
        companion2.log(level2, sb2.toString());
        createBitmaps();
    }

    public final void stop(HyperVideoListener callback) {
        String className;
        String className2;
        Class<?> cls;
        Class<?> cls2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        HyperVideoEncoder hyperVideoEncoder = null;
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && ((cls2 = getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String stringPlus = Intrinsics.stringPlus("stop() called with: callback = ", callback);
        if (stringPlus == null) {
            stringPlus = "null ";
        }
        sb.append(stringPlus);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (this.isRecordingStarted) {
            this.mCallback = callback;
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb2 = new StringBuilder();
            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
            String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            if (substringAfterLast$default2 != null || ((cls = getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                str = substringAfterLast$default2;
            }
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
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
            sb2.append("stop() Stopping HyperVideo");
            sb2.append(' ');
            sb2.append("");
            companion2.log(level2, sb2.toString());
            this.isRecordingStarted = false;
            Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
            }
            HyperVideoEncoder hyperVideoEncoder2 = this.bitmapToVideoEncoder;
            if (hyperVideoEncoder2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
            } else {
                hyperVideoEncoder = hyperVideoEncoder2;
            }
            hyperVideoEncoder.stopEncoding$hv_bitmaps_to_video_release();
        }
    }

    private final void createBitmaps() {
        String className;
        Class<?> cls;
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
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        sb.append("createBitmaps() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = TimersKt.timer(WorkflowModule.Properties.Section.Component.Type.TIMER, false);
        timer2.scheduleAtFixedRate(new TimerTask() { // from class: co.hyperverge.encoder.HyperVideoRecorder$createBitmaps$$inlined$fixedRateTimer$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Window window;
                window = HyperVideoRecorder.this.currentWindow;
                if (window == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentWindow");
                    window = null;
                }
                View rootView = window.getDecorView().getRootView();
                Intrinsics.checkNotNullExpressionValue(rootView, "currentWindow.decorView.rootView");
                if (rootView.isLaidOut()) {
                    HyperVideoRecorder.this.previewToBitmap(rootView);
                }
            }
        }, 0L, 125L);
        this.timer = timer2;
    }
}
