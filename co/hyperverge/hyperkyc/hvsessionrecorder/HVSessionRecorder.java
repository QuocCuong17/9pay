package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVBitmapToVideoEncoder;
import co.hyperverge.hyperkyc.ui.UploadingFragment;
import co.hyperverge.hyperkyc.utils.SessionRecorderUtilsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.tekartik.sqflite.Constant;
import defpackage.WakelockPlusApi$Companion$codec$2;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.concurrent.TimersKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HVSessionRecorder.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 `2\u00020\u0001:\u0001`B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010?\u001a\u00020*H\u0002J\b\u0010@\u001a\u00020*H\u0002J\b\u0010A\u001a\u00020*H\u0002J\r\u0010B\u001a\u00020*H\u0000¢\u0006\u0002\bCJ\u0010\u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u00020IH\u0002J\r\u0010J\u001a\u00020*H\u0000¢\u0006\u0002\bKJS\u0010L\u001a\u00020*2\u0006\u0010H\u001a\u00020I2\u0006\u0010M\u001a\u00020\u00142\u0006\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020\u00122\b\u0010P\u001a\u0004\u0018\u00010\u00062\u0006\u0010Q\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010SH\u0000¢\u0006\u0002\bTJ5\u0010U\u001a\u00020*2\u0006\u0010H\u001a\u00020I2\u0006\u0010M\u001a\u00020\u00142\u0006\u0010N\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'2\u0006\u0010R\u001a\u00020SH\u0000¢\u0006\u0002\bVJ4\u0010W\u001a\u00020*2%\b\u0002\u0010X\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\bY\u0012\b\bZ\u0012\u0004\b\b([\u0012\u0004\u0012\u00020*0)H\u0000¢\u0006\u0002\b\\J\u0010\u0010]\u001a\u00020*2\u0006\u0010^\u001a\u00020_H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\nR\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020*0)X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0004\u0018\u00010\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010#\"\u0004\b3\u0010%R\u001a\u00104\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\b\"\u0004\b6\u0010\nR\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010;\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\b\"\u0004\b=\u0010\nR\u000e\u0010>\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/HVSessionRecorder;", "", "()V", "activityLifecycleListener", "Landroid/app/Application$ActivityLifecycleCallbacks;", "audioPath", "", "getAudioPath", "()Ljava/lang/String;", "setAudioPath", "(Ljava/lang/String;)V", "bitmapHeight", "", "bitmapToVideoEncoder", "Lco/hyperverge/hyperkyc/hvsessionrecorder/HVBitmapToVideoEncoder;", "bitmapWidth", "cameraPreviewTag", "captureAudio", "", "currentWindow", "Landroid/view/Window;", "fileName", "filePath", "getFilePath$hyperkyc_release", "setFilePath$hyperkyc_release", "folderPath", "Ljava/io/File;", "hvAudioRecorder", "Lco/hyperverge/hyperkyc/hvsessionrecorder/HVAudioRecorder;", "getHvAudioRecorder$hyperkyc_release", "()Lco/hyperverge/hyperkyc/hvsessionrecorder/HVAudioRecorder;", "setHvAudioRecorder$hyperkyc_release", "(Lco/hyperverge/hyperkyc/hvsessionrecorder/HVAudioRecorder;)V", "isRecordingStarted", "isRecordingStarted$hyperkyc_release", "()Z", "setRecordingStarted$hyperkyc_release", "(Z)V", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "mCallback", "Lkotlin/Function1;", "", "sessionVideo", "getSessionVideo$hyperkyc_release", "()Ljava/io/File;", "setSessionVideo$hyperkyc_release", "(Ljava/io/File;)V", "shouldRecordPreviewOnly", "shouldUpload", "getShouldUpload$hyperkyc_release", "setShouldUpload$hyperkyc_release", UploadingFragment.ARG_KEY_STOP_MODULE_ID, "getStopModuleId$hyperkyc_release", "setStopModuleId$hyperkyc_release", WorkflowModule.Properties.Section.Component.Type.TIMER, "Ljava/util/Timer;", "timerRate", "", "uploadUrl", "getUploadUrl$hyperkyc_release", "setUploadUrl$hyperkyc_release", "videoPath", "createBitmaps", "deleteFiles", "muxAudioVideo", "pause", "pause$hyperkyc_release", "queueBitmap", "bitmap", "Landroid/graphics/Bitmap;", "registerForScreenRecording", "context", "Landroid/content/Context;", "resume", "resume$hyperkyc_release", "start", "window", "recordAudio", "upload", "url", "stopModule", "lowStorageExceptionListener", "Lco/hyperverge/hyperkyc/hvsessionrecorder/VideoStatementV2LowStorageExceptionListener;", "start$hyperkyc_release", "startRecordingCameraPreview", "startRecordingCameraPreview$hyperkyc_release", "stop", "callback", "Lkotlin/ParameterName;", "name", Constant.PARAM_RESULT, "stop$hyperkyc_release", "viewToBitmap", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HVSessionRecorder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static HVSessionRecorder instance;
    private Application.ActivityLifecycleCallbacks activityLifecycleListener;
    private String audioPath;
    private int bitmapHeight;
    private HVBitmapToVideoEncoder bitmapToVideoEncoder;
    private int bitmapWidth;
    private String cameraPreviewTag;
    private boolean captureAudio;
    private Window currentWindow;
    private String fileName;
    private String filePath;
    private File folderPath;
    private HVAudioRecorder hvAudioRecorder;
    private boolean isRecordingStarted;
    private CoroutineScope lifecycleScope;
    private Function1<? super File, Unit> mCallback;
    private File sessionVideo;
    private boolean shouldRecordPreviewOnly;
    private boolean shouldUpload;
    private String stopModuleId;
    private Timer timer;
    private final long timerRate;
    private String uploadUrl;
    private String videoPath;

    public HVSessionRecorder() {
        this.timerRate = SessionRecorderUtilsKt.getIsAndroid8Plus() ? 125L : 200L;
        this.captureAudio = true;
        this.bitmapWidth = 350;
        this.videoPath = "";
        this.fileName = "";
        this.stopModuleId = "";
        this.filePath = "";
    }

    /* renamed from: getHvAudioRecorder$hyperkyc_release, reason: from getter */
    public final HVAudioRecorder getHvAudioRecorder() {
        return this.hvAudioRecorder;
    }

    public final void setHvAudioRecorder$hyperkyc_release(HVAudioRecorder hVAudioRecorder) {
        this.hvAudioRecorder = hVAudioRecorder;
    }

    public final String getAudioPath() {
        return this.audioPath;
    }

    public final void setAudioPath(String str) {
        this.audioPath = str;
    }

    /* renamed from: isRecordingStarted$hyperkyc_release, reason: from getter */
    public final boolean getIsRecordingStarted() {
        return this.isRecordingStarted;
    }

    public final void setRecordingStarted$hyperkyc_release(boolean z) {
        this.isRecordingStarted = z;
    }

    /* renamed from: getSessionVideo$hyperkyc_release, reason: from getter */
    public final File getSessionVideo() {
        return this.sessionVideo;
    }

    public final void setSessionVideo$hyperkyc_release(File file) {
        this.sessionVideo = file;
    }

    /* renamed from: getShouldUpload$hyperkyc_release, reason: from getter */
    public final boolean getShouldUpload() {
        return this.shouldUpload;
    }

    public final void setShouldUpload$hyperkyc_release(boolean z) {
        this.shouldUpload = z;
    }

    /* renamed from: getUploadUrl$hyperkyc_release, reason: from getter */
    public final String getUploadUrl() {
        return this.uploadUrl;
    }

    public final void setUploadUrl$hyperkyc_release(String str) {
        this.uploadUrl = str;
    }

    /* renamed from: getStopModuleId$hyperkyc_release, reason: from getter */
    public final String getStopModuleId() {
        return this.stopModuleId;
    }

    public final void setStopModuleId$hyperkyc_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stopModuleId = str;
    }

    /* renamed from: getFilePath$hyperkyc_release, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    public final void setFilePath$hyperkyc_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.filePath = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void stop$hyperkyc_release$default(HVSessionRecorder hVSessionRecorder, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<File, Unit>() { // from class: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$stop$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(File file) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(File file) {
                    invoke2(file);
                    return Unit.INSTANCE;
                }
            };
        }
        hVSessionRecorder.stop$hyperkyc_release(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void viewToBitmap$lambda$15$lambda$14(HVSessionRecorder this_runCatching, Bitmap bitmap, int i) {
        Intrinsics.checkNotNullParameter(this_runCatching, "$this_runCatching");
        if (i == 0) {
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            this_runCatching.queueBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(77:1|(5:2|3|4|5|6)|(3:406|407|(77:409|410|411|(71:413|14|(1:16)|17|(1:22)|23|24|(6:363|364|365|(1:367)|368|(2:370|(72:372|373|374|375|(3:392|(1:394)(1:397)|(1:396))(1:381)|382|(1:384)|385|(1:390)|391|27|(2:29|(63:31|32|33|(57:35|36|(1:38)|39|(1:44)|45|(6:307|308|309|(1:311)|312|(3:314|315|(56:317|318|319|320|(3:337|(1:339)(1:342)|(1:341))(1:326)|327|(1:329)|330|(1:335)|336|48|49|50|51|(1:53)|54|(1:56)(1:304)|(1:58)(1:303)|59|(1:61)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(5:77|(4:86|87|88|85)|83|84|85)|90|91|(5:93|(4:102|103|104|101)|99|100|101)|106|107|(3:281|(1:283)(1:286)|(1:285))(1:113)|114|(1:116)|117|(1:122)|123|124|125|126|(1:128)(13:219|220|221|222|223|224|225|226|227|(1:229)|230|(4:232|233|234|(7:236|(3:254|(1:256)(1:259)|(1:258))(1:242)|243|(1:245)|246|(1:251)|252))(1:263)|260)|129|130|(1:132)|133|134|(21:136|(3:138|(1:142)|143)|144|(3:207|(1:209)(1:213)|(1:211)(1:212))|150|(1:152)|153|(1:157)|158|(1:160)|161|162|163|164|(1:166)|167|(2:169|(9:171|(3:190|(1:192)(1:196)|(1:194)(1:195))(1:177)|178|(1:180)|181|(1:185)|186|(1:188)|189))|197|(1:199)(1:203)|200|201)(2:214|215))))|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(1:109)|281|(0)(0)|(0)|114|(0)|117|(2:119|122)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0))|350|(1:352)(1:356)|(1:354)(1:355)|36|(0)|39|(2:41|44)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0)))|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0))))|26|27|(0)|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0))|9|(1:11)(1:405)|(1:13)(1:404)|14|(0)|17|(2:19|22)|23|24|(0)|26|27|(0)|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|24|(0)|26|27|(0)|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(81:1|2|3|4|5|6|(3:406|407|(77:409|410|411|(71:413|14|(1:16)|17|(1:22)|23|24|(6:363|364|365|(1:367)|368|(2:370|(72:372|373|374|375|(3:392|(1:394)(1:397)|(1:396))(1:381)|382|(1:384)|385|(1:390)|391|27|(2:29|(63:31|32|33|(57:35|36|(1:38)|39|(1:44)|45|(6:307|308|309|(1:311)|312|(3:314|315|(56:317|318|319|320|(3:337|(1:339)(1:342)|(1:341))(1:326)|327|(1:329)|330|(1:335)|336|48|49|50|51|(1:53)|54|(1:56)(1:304)|(1:58)(1:303)|59|(1:61)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(5:77|(4:86|87|88|85)|83|84|85)|90|91|(5:93|(4:102|103|104|101)|99|100|101)|106|107|(3:281|(1:283)(1:286)|(1:285))(1:113)|114|(1:116)|117|(1:122)|123|124|125|126|(1:128)(13:219|220|221|222|223|224|225|226|227|(1:229)|230|(4:232|233|234|(7:236|(3:254|(1:256)(1:259)|(1:258))(1:242)|243|(1:245)|246|(1:251)|252))(1:263)|260)|129|130|(1:132)|133|134|(21:136|(3:138|(1:142)|143)|144|(3:207|(1:209)(1:213)|(1:211)(1:212))|150|(1:152)|153|(1:157)|158|(1:160)|161|162|163|164|(1:166)|167|(2:169|(9:171|(3:190|(1:192)(1:196)|(1:194)(1:195))(1:177)|178|(1:180)|181|(1:185)|186|(1:188)|189))|197|(1:199)(1:203)|200|201)(2:214|215))))|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(1:109)|281|(0)(0)|(0)|114|(0)|117|(2:119|122)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0))|350|(1:352)(1:356)|(1:354)(1:355)|36|(0)|39|(2:41|44)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0)))|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0))))|26|27|(0)|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0))|9|(1:11)(1:405)|(1:13)(1:404)|14|(0)|17|(2:19|22)|23|24|(0)|26|27|(0)|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|24|(0)|26|27|(0)|362|350|(0)(0)|(0)(0)|36|(0)|39|(0)|45|(0)|47|48|49|50|51|(0)|54|(0)(0)|(0)(0)|59|(0)|62|63|64|65|66|67|68|69|70|71|72|73|74|75|(0)|90|91|(0)|106|107|(0)|281|(0)(0)|(0)|114|(0)|117|(0)|123|124|125|126|(0)(0)|129|130|(0)|133|134|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x05da, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x05e4, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x05e5, code lost:
    
        r6 = r27;
        r3 = "packageName";
        r2 = "null cannot be cast to non-null type android.app.Application";
        r5 = "getInitialApplication";
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x05ef, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x05f0, code lost:
    
        r6 = r27;
        r3 = "packageName";
        r2 = "null cannot be cast to non-null type android.app.Application";
        r5 = "getInitialApplication";
        r4 = " - ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x05fc, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x05fd, code lost:
    
        r5 = "getInitialApplication";
        r24 = "android.app.AppGlobals";
        r4 = " - ";
        r3 = "packageName";
        r2 = "null cannot be cast to non-null type android.app.Application";
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x0607, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0608, code lost:
    
        r2 = "null cannot be cast to non-null type android.app.Application";
        r5 = "getInitialApplication";
        r24 = "android.app.AppGlobals";
        r4 = " - ";
        r3 = "packageName";
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x0611, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0612, code lost:
    
        r2 = "null cannot be cast to non-null type android.app.Application";
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x0614, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x0615, code lost:
    
        r21 = "co.hyperverge";
        r24 = "android.app.AppGlobals";
        r4 = " - ";
        r6 = r27;
        r2 = "null cannot be cast to non-null type android.app.Application";
        r3 = "packageName";
        r5 = "getInitialApplication";
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x045c A[Catch: all -> 0x05ef, TryCatch #10 {all -> 0x05ef, blocks: (B:75:0x03cc, B:77:0x03da, B:79:0x03e8, B:87:0x03ed, B:83:0x0400, B:93:0x0408, B:95:0x0416, B:103:0x041b, B:99:0x042e, B:107:0x0433, B:109:0x045c, B:111:0x0462, B:114:0x047d, B:116:0x048e, B:117:0x0495, B:119:0x049f, B:122:0x04a6, B:123:0x04ae, B:281:0x046d, B:283:0x0473), top: B:74:0x03cc }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x048e A[Catch: all -> 0x05ef, TryCatch #10 {all -> 0x05ef, blocks: (B:75:0x03cc, B:77:0x03da, B:79:0x03e8, B:87:0x03ed, B:83:0x0400, B:93:0x0408, B:95:0x0416, B:103:0x041b, B:99:0x042e, B:107:0x0433, B:109:0x045c, B:111:0x0462, B:114:0x047d, B:116:0x048e, B:117:0x0495, B:119:0x049f, B:122:0x04a6, B:123:0x04ae, B:281:0x046d, B:283:0x0473), top: B:74:0x03cc }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x049f A[Catch: all -> 0x05ef, TryCatch #10 {all -> 0x05ef, blocks: (B:75:0x03cc, B:77:0x03da, B:79:0x03e8, B:87:0x03ed, B:83:0x0400, B:93:0x0408, B:95:0x0416, B:103:0x041b, B:99:0x042e, B:107:0x0433, B:109:0x045c, B:111:0x0462, B:114:0x047d, B:116:0x048e, B:117:0x0495, B:119:0x049f, B:122:0x04a6, B:123:0x04ae, B:281:0x046d, B:283:0x0473), top: B:74:0x03cc }] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0073 A[Catch: all -> 0x063e, TryCatch #22 {all -> 0x063e, blocks: (B:411:0x0052, B:14:0x007f, B:16:0x0090, B:17:0x0097, B:19:0x00a1, B:22:0x00a8, B:23:0x00b0, B:365:0x0100, B:368:0x0107, B:370:0x010f, B:372:0x0120, B:401:0x00f6, B:9:0x006d, B:11:0x0073, B:364:0x00d3), top: B:410:0x0052, inners: #14 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x05c6 A[Catch: all -> 0x05da, TryCatch #18 {all -> 0x05da, blocks: (B:130:0x05c2, B:132:0x05c6, B:133:0x05ca), top: B:129:0x05c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0090 A[Catch: all -> 0x063e, TryCatch #22 {all -> 0x063e, blocks: (B:411:0x0052, B:14:0x007f, B:16:0x0090, B:17:0x0097, B:19:0x00a1, B:22:0x00a8, B:23:0x00b0, B:365:0x0100, B:368:0x0107, B:370:0x010f, B:372:0x0120, B:401:0x00f6, B:9:0x006d, B:11:0x0073, B:364:0x00d3), top: B:410:0x0052, inners: #14 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a1 A[Catch: all -> 0x063e, TryCatch #22 {all -> 0x063e, blocks: (B:411:0x0052, B:14:0x007f, B:16:0x0090, B:17:0x0097, B:19:0x00a1, B:22:0x00a8, B:23:0x00b0, B:365:0x0100, B:368:0x0107, B:370:0x010f, B:372:0x0120, B:401:0x00f6, B:9:0x006d, B:11:0x0073, B:364:0x00d3), top: B:410:0x0052, inners: #14 }] */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0837  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x04da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0473 A[Catch: all -> 0x05ef, TryCatch #10 {all -> 0x05ef, blocks: (B:75:0x03cc, B:77:0x03da, B:79:0x03e8, B:87:0x03ed, B:83:0x0400, B:93:0x0408, B:95:0x0416, B:103:0x041b, B:99:0x042e, B:107:0x0433, B:109:0x045c, B:111:0x0462, B:114:0x047d, B:116:0x048e, B:117:0x0495, B:119:0x049f, B:122:0x04a6, B:123:0x04ae, B:281:0x046d, B:283:0x0473), top: B:74:0x03cc }] */
    /* JADX WARN: Removed duplicated region for block: B:285:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01c9 A[Catch: all -> 0x0632, TRY_LEAVE, TryCatch #12 {all -> 0x0632, blocks: (B:375:0x012b, B:377:0x0138, B:379:0x013e, B:382:0x0159, B:384:0x016a, B:385:0x0171, B:387:0x017b, B:390:0x0182, B:391:0x018a, B:27:0x01a6, B:29:0x01c9, B:392:0x0149, B:394:0x014f), top: B:374:0x012b }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x035c A[Catch: all -> 0x0614, TryCatch #13 {all -> 0x0614, blocks: (B:51:0x0320, B:53:0x0324, B:54:0x032a, B:56:0x0352, B:59:0x0361, B:62:0x0375, B:303:0x035c), top: B:50:0x0320 }] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0245 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:352:0x01e4 A[Catch: all -> 0x0625, TryCatch #5 {all -> 0x0625, blocks: (B:33:0x01d5, B:36:0x01f0, B:38:0x0201, B:39:0x0208, B:41:0x0212, B:44:0x0219, B:45:0x0221, B:309:0x0272, B:312:0x0279, B:349:0x0268, B:350:0x01de, B:352:0x01e4, B:308:0x0245), top: B:32:0x01d5, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0201 A[Catch: all -> 0x0625, TryCatch #5 {all -> 0x0625, blocks: (B:33:0x01d5, B:36:0x01f0, B:38:0x0201, B:39:0x0208, B:41:0x0212, B:44:0x0219, B:45:0x0221, B:309:0x0272, B:312:0x0279, B:349:0x0268, B:350:0x01de, B:352:0x01e4, B:308:0x0245), top: B:32:0x01d5, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0212 A[Catch: all -> 0x0625, TryCatch #5 {all -> 0x0625, blocks: (B:33:0x01d5, B:36:0x01f0, B:38:0x0201, B:39:0x0208, B:41:0x0212, B:44:0x0219, B:45:0x0221, B:309:0x0272, B:312:0x0279, B:349:0x0268, B:350:0x01de, B:352:0x01e4, B:308:0x0245), top: B:32:0x01d5, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0324 A[Catch: all -> 0x0614, TryCatch #13 {all -> 0x0614, blocks: (B:51:0x0320, B:53:0x0324, B:54:0x032a, B:56:0x0352, B:59:0x0361, B:62:0x0375, B:303:0x035c), top: B:50:0x0320 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0352 A[Catch: all -> 0x0614, TryCatch #13 {all -> 0x0614, blocks: (B:51:0x0320, B:53:0x0324, B:54:0x032a, B:56:0x0352, B:59:0x0361, B:62:0x0375, B:303:0x035c), top: B:50:0x0320 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03da A[Catch: all -> 0x05ef, TryCatch #10 {all -> 0x05ef, blocks: (B:75:0x03cc, B:77:0x03da, B:79:0x03e8, B:87:0x03ed, B:83:0x0400, B:93:0x0408, B:95:0x0416, B:103:0x041b, B:99:0x042e, B:107:0x0433, B:109:0x045c, B:111:0x0462, B:114:0x047d, B:116:0x048e, B:117:0x0495, B:119:0x049f, B:122:0x04a6, B:123:0x04ae, B:281:0x046d, B:283:0x0473), top: B:74:0x03cc }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0408 A[Catch: all -> 0x05ef, TryCatch #10 {all -> 0x05ef, blocks: (B:75:0x03cc, B:77:0x03da, B:79:0x03e8, B:87:0x03ed, B:83:0x0400, B:93:0x0408, B:95:0x0416, B:103:0x041b, B:99:0x042e, B:107:0x0433, B:109:0x045c, B:111:0x0462, B:114:0x047d, B:116:0x048e, B:117:0x0495, B:119:0x049f, B:122:0x04a6, B:123:0x04ae, B:281:0x046d, B:283:0x0473), top: B:74:0x03cc }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void muxAudioVideo() {
        String str;
        String str2;
        CharSequence charSequence;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        HVSessionRecorder hVSessionRecorder;
        String str8;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        String str9;
        Object m1202constructorimpl2;
        WakelockPlusApi$Companion$codec$2 wakelockPlusApi$Companion$codec$2;
        String str10;
        String className;
        String substringAfterLast$default;
        String className2;
        File file;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement;
        String className3;
        String str11;
        String substringAfterLast$default2;
        Matcher matcher;
        Object m1202constructorimpl3;
        String str12;
        String canonicalName;
        String className4;
        StackTraceElement stackTraceElement2;
        String str13;
        String str14;
        Matcher matcher2;
        Object m1202constructorimpl4;
        String canonicalName2;
        String className5;
        File file2;
        String absolutePath;
        String str15;
        boolean z;
        boolean z2;
        StackTraceElement stackTraceElement3;
        String canonicalName3;
        Matcher matcher3;
        Object m1202constructorimpl5;
        String canonicalName4;
        String className6;
        Function1<? super File, Unit> function1;
        String className7;
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVSessionRecorder hVSessionRecorder2 = this;
            level = HyperLogger.Level.DEBUG;
            companion = HyperLogger.INSTANCE.getInstance();
            sb = new StringBuilder();
            str7 = "N/A";
            try {
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            } catch (Throwable th) {
                th = th;
                str = "mCallback";
                str2 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str3 = "packageName";
                str4 = "getInitialApplication";
                str5 = "android.app.AppGlobals";
                str6 = " - ";
                hVSessionRecorder = this;
                str8 = "Throwable().stackTrace";
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            str = "mCallback";
            str2 = "null cannot be cast to non-null type android.app.Application";
            charSequence = "co.hyperverge";
            str3 = "packageName";
            str4 = "getInitialApplication";
            str5 = "android.app.AppGlobals";
            str6 = " - ";
            str7 = "N/A";
        }
        if (stackTraceElement != null) {
            try {
                className3 = stackTraceElement.getClassName();
            } catch (Throwable th3) {
                th = th3;
                str = "mCallback";
                str2 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str3 = "packageName";
                str4 = "getInitialApplication";
                str5 = "android.app.AppGlobals";
                str6 = " - ";
                str8 = "Throwable().stackTrace";
                hVSessionRecorder = this;
                Result.Companion companion32 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
            if (className3 != null) {
                str = "mCallback";
                str11 = "Throwable().stackTrace";
                try {
                    substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                } catch (Throwable th4) {
                    th = th4;
                    str2 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    str4 = "getInitialApplication";
                    str5 = "android.app.AppGlobals";
                    str6 = " - ";
                    str8 = str11;
                    hVSessionRecorder = this;
                    Result.Companion companion322 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                if (substringAfterLast$default2 != null) {
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                    if (matcher.find()) {
                        substringAfterLast$default2 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
                    }
                    Unit unit = Unit.INSTANCE;
                    if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default2);
                    sb.append(" - ");
                    sb.append("muxAudioVideo() called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
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
                            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str12 = str11;
                                try {
                                    Intrinsics.checkNotNullExpressionValue(stackTrace2, str12);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                    if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls = getClass();
                                        canonicalName = cls != null ? cls.getCanonicalName() : null;
                                        if (canonicalName == null) {
                                            canonicalName = str7;
                                        }
                                    }
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                    if (matcher4.find()) {
                                        canonicalName = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                    }
                                    Unit unit2 = Unit.INSTANCE;
                                    if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        canonicalName = canonicalName.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(3, canonicalName, "muxAudioVideo() called ");
                                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb2 = new StringBuilder();
                                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str12);
                                    stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                } catch (Throwable th6) {
                                    th = th6;
                                    str8 = str12;
                                    str2 = "null cannot be cast to non-null type android.app.Application";
                                    charSequence = "co.hyperverge";
                                    str3 = "packageName";
                                    str4 = "getInitialApplication";
                                    str5 = "android.app.AppGlobals";
                                    str6 = " - ";
                                    hVSessionRecorder = this;
                                    Result.Companion companion3222 = Result.INSTANCE;
                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                }
                                if (stackTraceElement2 != null) {
                                    String className8 = stackTraceElement2.getClassName();
                                    if (className8 != null) {
                                        str13 = str12;
                                        try {
                                            str14 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        } catch (Throwable th7) {
                                            th = th7;
                                            str2 = "null cannot be cast to non-null type android.app.Application";
                                            charSequence = "co.hyperverge";
                                            str3 = "packageName";
                                            str4 = "getInitialApplication";
                                            str5 = "android.app.AppGlobals";
                                            str6 = " - ";
                                            str8 = str13;
                                            hVSessionRecorder = this;
                                            Result.Companion companion32222 = Result.INSTANCE;
                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                        }
                                        if (str14 != null) {
                                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                                            if (matcher2.find()) {
                                                str14 = matcher2.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(str14, "replaceAll(\"\")");
                                            }
                                            Unit unit3 = Unit.INSTANCE;
                                            if (str14.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                str14 = str14.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            sb2.append(str14);
                                            sb2.append(" - ");
                                            sb2.append("mux audio video started");
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
                                                    try {
                                                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                                    } catch (Throwable th9) {
                                                        th = th9;
                                                        str8 = str13;
                                                        str2 = "null cannot be cast to non-null type android.app.Application";
                                                        charSequence = "co.hyperverge";
                                                        str3 = "packageName";
                                                        str4 = "getInitialApplication";
                                                        str5 = "android.app.AppGlobals";
                                                        str6 = " - ";
                                                        hVSessionRecorder = this;
                                                        Result.Companion companion322222 = Result.INSTANCE;
                                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                        if (m1205exceptionOrNullimpl != null) {
                                                        }
                                                    }
                                                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                        str8 = str13;
                                                        try {
                                                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str8);
                                                            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                            if (stackTraceElement5 == null || (className5 = stackTraceElement5.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                Class<?> cls2 = getClass();
                                                                canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                                                                if (canonicalName2 == null) {
                                                                    canonicalName2 = str7;
                                                                }
                                                            }
                                                            Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                                            if (matcher5.find()) {
                                                                canonicalName2 = matcher5.replaceAll("");
                                                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                                            }
                                                            Unit unit4 = Unit.INSTANCE;
                                                            if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                canonicalName2 = canonicalName2.substring(0, 23);
                                                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                                                            }
                                                            Log.println(3, canonicalName2, "mux audio video started ");
                                                            file2 = this.folderPath;
                                                            if (file2 == null) {
                                                                Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                                                                file2 = null;
                                                            }
                                                            File file3 = new File(file2, '/' + this.fileName + "-final.mp4");
                                                            this.sessionVideo = file3;
                                                            Boolean.valueOf(file3.createNewFile());
                                                            File file4 = this.sessionVideo;
                                                            absolutePath = file4 != null ? file4.getAbsolutePath() : null;
                                                            if (absolutePath == null) {
                                                                absolutePath = "";
                                                            } else {
                                                                Intrinsics.checkNotNullExpressionValue(absolutePath, "sessionVideo?.absolutePath ?: \"\"");
                                                            }
                                                            MediaExtractor mediaExtractor = new MediaExtractor();
                                                            mediaExtractor.setDataSource(this.videoPath);
                                                            MediaExtractor mediaExtractor2 = new MediaExtractor();
                                                            str15 = this.audioPath;
                                                            if (str15 == null) {
                                                                str15 = "";
                                                            }
                                                            mediaExtractor2.setDataSource(str15);
                                                            MediaMuxer mediaMuxer = new MediaMuxer(absolutePath, 0);
                                                            mediaExtractor.selectTrack(0);
                                                            MediaFormat trackFormat = mediaExtractor.getTrackFormat(0);
                                                            Intrinsics.checkNotNullExpressionValue(trackFormat, "videoExtractor.getTrackFormat(0)");
                                                            int addTrack = mediaMuxer.addTrack(trackFormat);
                                                            mediaExtractor2.selectTrack(0);
                                                            MediaFormat trackFormat2 = mediaExtractor2.getTrackFormat(0);
                                                            Intrinsics.checkNotNullExpressionValue(trackFormat2, "audioExtractor.getTrackFormat(0)");
                                                            int addTrack2 = mediaMuxer.addTrack(trackFormat2);
                                                            ByteBuffer allocate = ByteBuffer.allocate(262144);
                                                            charSequence = "co.hyperverge";
                                                            Intrinsics.checkNotNullExpressionValue(allocate, "allocate(sampleSize)");
                                                            ByteBuffer allocate2 = ByteBuffer.allocate(262144);
                                                            Intrinsics.checkNotNullExpressionValue(allocate2, "allocate(sampleSize)");
                                                            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                                                            MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
                                                            str5 = "android.app.AppGlobals";
                                                            mediaExtractor.seekTo(0L, 2);
                                                            mediaExtractor2.seekTo(0L, 2);
                                                            mediaMuxer.start();
                                                            z = false;
                                                            while (!z) {
                                                                bufferInfo.offset = 100;
                                                                bufferInfo.size = mediaExtractor.readSampleData(allocate, 100);
                                                                if (bufferInfo.size >= 0 && bufferInfo2.size >= 0) {
                                                                    bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
                                                                    bufferInfo.flags = mediaExtractor.getSampleFlags();
                                                                    mediaMuxer.writeSampleData(addTrack, allocate, bufferInfo);
                                                                    mediaExtractor.advance();
                                                                }
                                                                z = true;
                                                                bufferInfo.size = 0;
                                                            }
                                                            z2 = false;
                                                            while (!z2) {
                                                                bufferInfo2.offset = 100;
                                                                bufferInfo2.size = mediaExtractor2.readSampleData(allocate2, 100);
                                                                if (bufferInfo.size >= 0 && bufferInfo2.size >= 0) {
                                                                    bufferInfo2.presentationTimeUs = mediaExtractor2.getSampleTime();
                                                                    bufferInfo2.flags = mediaExtractor2.getSampleFlags();
                                                                    mediaMuxer.writeSampleData(addTrack2, allocate2, bufferInfo2);
                                                                    mediaExtractor2.advance();
                                                                }
                                                                z2 = true;
                                                                bufferInfo2.size = 0;
                                                            }
                                                            mediaMuxer.stop();
                                                            mediaMuxer.release();
                                                            HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                                                            HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                                                            StringBuilder sb3 = new StringBuilder();
                                                            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                                            Intrinsics.checkNotNullExpressionValue(stackTrace5, str8);
                                                            stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                                            if (stackTraceElement3 != null || (className7 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                Class<?> cls3 = getClass();
                                                                canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                                                                if (canonicalName3 == null) {
                                                                    canonicalName3 = str7;
                                                                }
                                                            }
                                                            matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                                            if (matcher3.find()) {
                                                                canonicalName3 = matcher3.replaceAll("");
                                                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                                            }
                                                            Unit unit5 = Unit.INSTANCE;
                                                            if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                canonicalName3 = canonicalName3.substring(0, 23);
                                                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                                            }
                                                            sb3.append(canonicalName3);
                                                            str6 = " - ";
                                                            sb3.append(str6);
                                                            sb3.append("muxAudioVideo() mux audio video completed");
                                                            sb3.append(' ');
                                                            sb3.append("");
                                                            companion9.log(level3, sb3.toString());
                                                            if (CoreExtsKt.isRelease()) {
                                                                hVSessionRecorder = this;
                                                                str3 = "packageName";
                                                                str2 = "null cannot be cast to non-null type android.app.Application";
                                                                str4 = "getInitialApplication";
                                                            } else {
                                                                try {
                                                                    Result.Companion companion10 = Result.INSTANCE;
                                                                    str4 = "getInitialApplication";
                                                                    try {
                                                                        Object invoke3 = Class.forName(str5).getMethod(str4, new Class[0]).invoke(null, new Object[0]);
                                                                        str2 = "null cannot be cast to non-null type android.app.Application";
                                                                        try {
                                                                            Intrinsics.checkNotNull(invoke3, str2);
                                                                            m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                                                        } catch (Throwable th10) {
                                                                            th = th10;
                                                                            try {
                                                                                Result.Companion companion11 = Result.INSTANCE;
                                                                                m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                }
                                                                                String str16 = (String) m1202constructorimpl5;
                                                                                if (CoreExtsKt.isDebug()) {
                                                                                }
                                                                                hVSessionRecorder = this;
                                                                                function1 = hVSessionRecorder.mCallback;
                                                                                if (function1 == null) {
                                                                                }
                                                                                function1.invoke(hVSessionRecorder.sessionVideo);
                                                                                deleteFiles();
                                                                                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                            } catch (Throwable th11) {
                                                                                th = th11;
                                                                                hVSessionRecorder = this;
                                                                                str3 = "packageName";
                                                                                Result.Companion companion3222222 = Result.INSTANCE;
                                                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                if (m1205exceptionOrNullimpl != null) {
                                                                                }
                                                                            }
                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                            if (m1205exceptionOrNullimpl != null) {
                                                                            }
                                                                        }
                                                                    } catch (Throwable th12) {
                                                                        th = th12;
                                                                        str2 = "null cannot be cast to non-null type android.app.Application";
                                                                    }
                                                                } catch (Throwable th13) {
                                                                    th = th13;
                                                                    str2 = "null cannot be cast to non-null type android.app.Application";
                                                                    str4 = "getInitialApplication";
                                                                }
                                                                if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                    m1202constructorimpl5 = "";
                                                                }
                                                                String str162 = (String) m1202constructorimpl5;
                                                                if (CoreExtsKt.isDebug()) {
                                                                    str3 = "packageName";
                                                                } else {
                                                                    str3 = "packageName";
                                                                    try {
                                                                        Intrinsics.checkNotNullExpressionValue(str162, str3);
                                                                        if (StringsKt.contains$default((CharSequence) str162, charSequence, false, 2, (Object) null)) {
                                                                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str8);
                                                                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                                            if (stackTraceElement6 == null || (className6 = stackTraceElement6.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                Class<?> cls4 = getClass();
                                                                                canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                                                                if (canonicalName4 == null) {
                                                                                    canonicalName4 = str7;
                                                                                }
                                                                            }
                                                                            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                                                                            if (matcher6.find()) {
                                                                                canonicalName4 = matcher6.replaceAll("");
                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                                                                            }
                                                                            Unit unit6 = Unit.INSTANCE;
                                                                            if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                canonicalName4 = canonicalName4.substring(0, 23);
                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                            }
                                                                            Log.println(3, canonicalName4, "muxAudioVideo() mux audio video completed ");
                                                                        }
                                                                    } catch (Throwable th14) {
                                                                        th = th14;
                                                                        hVSessionRecorder = this;
                                                                        Result.Companion companion32222222 = Result.INSTANCE;
                                                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                        if (m1205exceptionOrNullimpl != null) {
                                                                        }
                                                                    }
                                                                }
                                                                hVSessionRecorder = this;
                                                            }
                                                            function1 = hVSessionRecorder.mCallback;
                                                            if (function1 == null) {
                                                                Intrinsics.throwUninitializedPropertyAccessException(str);
                                                                function1 = null;
                                                            }
                                                            function1.invoke(hVSessionRecorder.sessionVideo);
                                                            deleteFiles();
                                                            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                                        } catch (Throwable th15) {
                                                            th = th15;
                                                            str2 = "null cannot be cast to non-null type android.app.Application";
                                                            charSequence = "co.hyperverge";
                                                            str3 = "packageName";
                                                            str4 = "getInitialApplication";
                                                            str5 = "android.app.AppGlobals";
                                                            str6 = " - ";
                                                            hVSessionRecorder = this;
                                                            Result.Companion companion322222222 = Result.INSTANCE;
                                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                            if (m1205exceptionOrNullimpl != null) {
                                                            }
                                                        }
                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                        if (m1205exceptionOrNullimpl != null) {
                                                            deleteFiles();
                                                            File file5 = hVSessionRecorder.sessionVideo;
                                                            if (file5 != null) {
                                                                if (file5.exists() && (file = hVSessionRecorder.sessionVideo) != null) {
                                                                    Boolean.valueOf(file.delete());
                                                                }
                                                                Unit unit7 = Unit.INSTANCE;
                                                                Unit unit8 = Unit.INSTANCE;
                                                            }
                                                            HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                                                            HyperLogger companion12 = HyperLogger.INSTANCE.getInstance();
                                                            StringBuilder sb4 = new StringBuilder();
                                                            StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                                                            Intrinsics.checkNotNullExpressionValue(stackTrace7, str8);
                                                            StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                                                            if (stackTraceElement7 == null || (className2 = stackTraceElement7.getClassName()) == null || (str9 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                Class<?> cls5 = getClass();
                                                                String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                                                                str9 = canonicalName5 == null ? str7 : canonicalName5;
                                                            }
                                                            Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                                                            if (matcher7.find()) {
                                                                str9 = matcher7.replaceAll("");
                                                                Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                                                            }
                                                            Unit unit9 = Unit.INSTANCE;
                                                            if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                str9 = str9.substring(0, 23);
                                                                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                                                            }
                                                            sb4.append(str9);
                                                            sb4.append(str6);
                                                            String str17 = "muxAudioVideo() " + m1205exceptionOrNullimpl.getMessage();
                                                            if (str17 == null) {
                                                                str17 = "null ";
                                                            }
                                                            sb4.append(str17);
                                                            sb4.append(' ');
                                                            sb4.append("");
                                                            companion12.log(level4, sb4.toString());
                                                            CoreExtsKt.isRelease();
                                                            try {
                                                                Result.Companion companion13 = Result.INSTANCE;
                                                                Object invoke4 = Class.forName(str5).getMethod(str4, new Class[0]).invoke(null, new Object[0]);
                                                                Intrinsics.checkNotNull(invoke4, str2);
                                                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                                            } catch (Throwable th16) {
                                                                Result.Companion companion14 = Result.INSTANCE;
                                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th16));
                                                            }
                                                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                                                m1202constructorimpl2 = "";
                                                            }
                                                            String str18 = (String) m1202constructorimpl2;
                                                            if (CoreExtsKt.isDebug()) {
                                                                Intrinsics.checkNotNullExpressionValue(str18, str3);
                                                                if (StringsKt.contains$default((CharSequence) str18, charSequence, false, 2, (Object) null)) {
                                                                    StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                                                    Intrinsics.checkNotNullExpressionValue(stackTrace8, str8);
                                                                    StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                                                    if (stackTraceElement8 == null || (className = stackTraceElement8.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                        Class<?> cls6 = getClass();
                                                                        String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : null;
                                                                        str10 = canonicalName6 == null ? str7 : canonicalName6;
                                                                    } else {
                                                                        str10 = substringAfterLast$default;
                                                                    }
                                                                    Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                                                                    if (matcher8.find()) {
                                                                        str10 = matcher8.replaceAll("");
                                                                        Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
                                                                    }
                                                                    Unit unit10 = Unit.INSTANCE;
                                                                    if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                        str10 = str10.substring(0, 23);
                                                                        Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                    }
                                                                    StringBuilder sb5 = new StringBuilder();
                                                                    String str19 = "muxAudioVideo() " + m1205exceptionOrNullimpl.getMessage();
                                                                    sb5.append(str19 != null ? str19 : "null ");
                                                                    sb5.append(' ');
                                                                    sb5.append("");
                                                                    Log.println(6, str10, sb5.toString());
                                                                }
                                                            }
                                                            Function1<? super File, Unit> function12 = this.mCallback;
                                                            if (function12 == null) {
                                                                Intrinsics.throwUninitializedPropertyAccessException(str);
                                                                wakelockPlusApi$Companion$codec$2 = null;
                                                                function12 = null;
                                                            } else {
                                                                wakelockPlusApi$Companion$codec$2 = null;
                                                            }
                                                            function12.invoke(wakelockPlusApi$Companion$codec$2);
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                }
                                            }
                                            str8 = str13;
                                            file2 = this.folderPath;
                                            if (file2 == null) {
                                            }
                                            File file32 = new File(file2, '/' + this.fileName + "-final.mp4");
                                            this.sessionVideo = file32;
                                            Boolean.valueOf(file32.createNewFile());
                                            File file42 = this.sessionVideo;
                                            if (file42 != null) {
                                            }
                                            if (absolutePath == null) {
                                            }
                                            MediaExtractor mediaExtractor3 = new MediaExtractor();
                                            mediaExtractor3.setDataSource(this.videoPath);
                                            MediaExtractor mediaExtractor22 = new MediaExtractor();
                                            str15 = this.audioPath;
                                            if (str15 == null) {
                                            }
                                            mediaExtractor22.setDataSource(str15);
                                            MediaMuxer mediaMuxer2 = new MediaMuxer(absolutePath, 0);
                                            mediaExtractor3.selectTrack(0);
                                            MediaFormat trackFormat3 = mediaExtractor3.getTrackFormat(0);
                                            Intrinsics.checkNotNullExpressionValue(trackFormat3, "videoExtractor.getTrackFormat(0)");
                                            int addTrack3 = mediaMuxer2.addTrack(trackFormat3);
                                            mediaExtractor22.selectTrack(0);
                                            MediaFormat trackFormat22 = mediaExtractor22.getTrackFormat(0);
                                            Intrinsics.checkNotNullExpressionValue(trackFormat22, "audioExtractor.getTrackFormat(0)");
                                            int addTrack22 = mediaMuxer2.addTrack(trackFormat22);
                                            ByteBuffer allocate3 = ByteBuffer.allocate(262144);
                                            charSequence = "co.hyperverge";
                                            Intrinsics.checkNotNullExpressionValue(allocate3, "allocate(sampleSize)");
                                            ByteBuffer allocate22 = ByteBuffer.allocate(262144);
                                            Intrinsics.checkNotNullExpressionValue(allocate22, "allocate(sampleSize)");
                                            MediaCodec.BufferInfo bufferInfo3 = new MediaCodec.BufferInfo();
                                            MediaCodec.BufferInfo bufferInfo22 = new MediaCodec.BufferInfo();
                                            str5 = "android.app.AppGlobals";
                                            mediaExtractor3.seekTo(0L, 2);
                                            mediaExtractor22.seekTo(0L, 2);
                                            mediaMuxer2.start();
                                            z = false;
                                            while (!z) {
                                            }
                                            z2 = false;
                                            while (!z2) {
                                            }
                                            mediaMuxer2.stop();
                                            mediaMuxer2.release();
                                            HyperLogger.Level level32 = HyperLogger.Level.DEBUG;
                                            HyperLogger companion92 = HyperLogger.INSTANCE.getInstance();
                                            StringBuilder sb32 = new StringBuilder();
                                            StackTraceElement[] stackTrace52 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace52, str8);
                                            stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace52);
                                            if (stackTraceElement3 != null) {
                                            }
                                            Class<?> cls32 = getClass();
                                            if (cls32 != null) {
                                            }
                                            if (canonicalName3 == null) {
                                            }
                                            matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                            if (matcher3.find()) {
                                            }
                                            Unit unit52 = Unit.INSTANCE;
                                            if (canonicalName3.length() > 23) {
                                                canonicalName3 = canonicalName3.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            sb32.append(canonicalName3);
                                            str6 = " - ";
                                            sb32.append(str6);
                                            sb32.append("muxAudioVideo() mux audio video completed");
                                            sb32.append(' ');
                                            sb32.append("");
                                            companion92.log(level32, sb32.toString());
                                            if (CoreExtsKt.isRelease()) {
                                            }
                                            function1 = hVSessionRecorder.mCallback;
                                            if (function1 == null) {
                                            }
                                            function1.invoke(hVSessionRecorder.sessionVideo);
                                            deleteFiles();
                                            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                        }
                                        Class<?> cls7 = getClass();
                                        String canonicalName7 = cls7 != null ? cls7.getCanonicalName() : null;
                                        str14 = canonicalName7 == null ? str7 : canonicalName7;
                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                                        if (matcher2.find()) {
                                        }
                                        Unit unit32 = Unit.INSTANCE;
                                        if (str14.length() > 23) {
                                            str14 = str14.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        sb2.append(str14);
                                        sb2.append(" - ");
                                        sb2.append("mux audio video started");
                                        sb2.append(' ');
                                        sb2.append("");
                                        companion6.log(level2, sb2.toString());
                                        if (!CoreExtsKt.isRelease()) {
                                        }
                                        str8 = str13;
                                        file2 = this.folderPath;
                                        if (file2 == null) {
                                        }
                                        File file322 = new File(file2, '/' + this.fileName + "-final.mp4");
                                        this.sessionVideo = file322;
                                        Boolean.valueOf(file322.createNewFile());
                                        File file422 = this.sessionVideo;
                                        if (file422 != null) {
                                        }
                                        if (absolutePath == null) {
                                        }
                                        MediaExtractor mediaExtractor32 = new MediaExtractor();
                                        mediaExtractor32.setDataSource(this.videoPath);
                                        MediaExtractor mediaExtractor222 = new MediaExtractor();
                                        str15 = this.audioPath;
                                        if (str15 == null) {
                                        }
                                        mediaExtractor222.setDataSource(str15);
                                        MediaMuxer mediaMuxer22 = new MediaMuxer(absolutePath, 0);
                                        mediaExtractor32.selectTrack(0);
                                        MediaFormat trackFormat32 = mediaExtractor32.getTrackFormat(0);
                                        Intrinsics.checkNotNullExpressionValue(trackFormat32, "videoExtractor.getTrackFormat(0)");
                                        int addTrack32 = mediaMuxer22.addTrack(trackFormat32);
                                        mediaExtractor222.selectTrack(0);
                                        MediaFormat trackFormat222 = mediaExtractor222.getTrackFormat(0);
                                        Intrinsics.checkNotNullExpressionValue(trackFormat222, "audioExtractor.getTrackFormat(0)");
                                        int addTrack222 = mediaMuxer22.addTrack(trackFormat222);
                                        ByteBuffer allocate32 = ByteBuffer.allocate(262144);
                                        charSequence = "co.hyperverge";
                                        Intrinsics.checkNotNullExpressionValue(allocate32, "allocate(sampleSize)");
                                        ByteBuffer allocate222 = ByteBuffer.allocate(262144);
                                        Intrinsics.checkNotNullExpressionValue(allocate222, "allocate(sampleSize)");
                                        MediaCodec.BufferInfo bufferInfo32 = new MediaCodec.BufferInfo();
                                        MediaCodec.BufferInfo bufferInfo222 = new MediaCodec.BufferInfo();
                                        str5 = "android.app.AppGlobals";
                                        mediaExtractor32.seekTo(0L, 2);
                                        mediaExtractor222.seekTo(0L, 2);
                                        mediaMuxer22.start();
                                        z = false;
                                        while (!z) {
                                        }
                                        z2 = false;
                                        while (!z2) {
                                        }
                                        mediaMuxer22.stop();
                                        mediaMuxer22.release();
                                        HyperLogger.Level level322 = HyperLogger.Level.DEBUG;
                                        HyperLogger companion922 = HyperLogger.INSTANCE.getInstance();
                                        StringBuilder sb322 = new StringBuilder();
                                        StackTraceElement[] stackTrace522 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace522, str8);
                                        stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace522);
                                        if (stackTraceElement3 != null) {
                                        }
                                        Class<?> cls322 = getClass();
                                        if (cls322 != null) {
                                        }
                                        if (canonicalName3 == null) {
                                        }
                                        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                        if (matcher3.find()) {
                                        }
                                        Unit unit522 = Unit.INSTANCE;
                                        if (canonicalName3.length() > 23) {
                                        }
                                        sb322.append(canonicalName3);
                                        str6 = " - ";
                                        sb322.append(str6);
                                        sb322.append("muxAudioVideo() mux audio video completed");
                                        sb322.append(' ');
                                        sb322.append("");
                                        companion922.log(level322, sb322.toString());
                                        if (CoreExtsKt.isRelease()) {
                                        }
                                        function1 = hVSessionRecorder.mCallback;
                                        if (function1 == null) {
                                        }
                                        function1.invoke(hVSessionRecorder.sessionVideo);
                                        deleteFiles();
                                        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                    }
                                }
                                str13 = str12;
                                Class<?> cls72 = getClass();
                                if (cls72 != null) {
                                }
                                if (canonicalName7 == null) {
                                }
                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                                if (matcher2.find()) {
                                }
                                Unit unit322 = Unit.INSTANCE;
                                if (str14.length() > 23) {
                                }
                                sb2.append(str14);
                                sb2.append(" - ");
                                sb2.append("mux audio video started");
                                sb2.append(' ');
                                sb2.append("");
                                companion6.log(level2, sb2.toString());
                                if (!CoreExtsKt.isRelease()) {
                                }
                                str8 = str13;
                                file2 = this.folderPath;
                                if (file2 == null) {
                                }
                                File file3222 = new File(file2, '/' + this.fileName + "-final.mp4");
                                this.sessionVideo = file3222;
                                Boolean.valueOf(file3222.createNewFile());
                                File file4222 = this.sessionVideo;
                                if (file4222 != null) {
                                }
                                if (absolutePath == null) {
                                }
                                MediaExtractor mediaExtractor322 = new MediaExtractor();
                                mediaExtractor322.setDataSource(this.videoPath);
                                MediaExtractor mediaExtractor2222 = new MediaExtractor();
                                str15 = this.audioPath;
                                if (str15 == null) {
                                }
                                mediaExtractor2222.setDataSource(str15);
                                MediaMuxer mediaMuxer222 = new MediaMuxer(absolutePath, 0);
                                mediaExtractor322.selectTrack(0);
                                MediaFormat trackFormat322 = mediaExtractor322.getTrackFormat(0);
                                Intrinsics.checkNotNullExpressionValue(trackFormat322, "videoExtractor.getTrackFormat(0)");
                                int addTrack322 = mediaMuxer222.addTrack(trackFormat322);
                                mediaExtractor2222.selectTrack(0);
                                MediaFormat trackFormat2222 = mediaExtractor2222.getTrackFormat(0);
                                Intrinsics.checkNotNullExpressionValue(trackFormat2222, "audioExtractor.getTrackFormat(0)");
                                int addTrack2222 = mediaMuxer222.addTrack(trackFormat2222);
                                ByteBuffer allocate322 = ByteBuffer.allocate(262144);
                                charSequence = "co.hyperverge";
                                Intrinsics.checkNotNullExpressionValue(allocate322, "allocate(sampleSize)");
                                ByteBuffer allocate2222 = ByteBuffer.allocate(262144);
                                Intrinsics.checkNotNullExpressionValue(allocate2222, "allocate(sampleSize)");
                                MediaCodec.BufferInfo bufferInfo322 = new MediaCodec.BufferInfo();
                                MediaCodec.BufferInfo bufferInfo2222 = new MediaCodec.BufferInfo();
                                str5 = "android.app.AppGlobals";
                                mediaExtractor322.seekTo(0L, 2);
                                mediaExtractor2222.seekTo(0L, 2);
                                mediaMuxer222.start();
                                z = false;
                                while (!z) {
                                }
                                z2 = false;
                                while (!z2) {
                                }
                                mediaMuxer222.stop();
                                mediaMuxer222.release();
                                HyperLogger.Level level3222 = HyperLogger.Level.DEBUG;
                                HyperLogger companion9222 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3222 = new StringBuilder();
                                StackTraceElement[] stackTrace5222 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace5222, str8);
                                stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5222);
                                if (stackTraceElement3 != null) {
                                }
                                Class<?> cls3222 = getClass();
                                if (cls3222 != null) {
                                }
                                if (canonicalName3 == null) {
                                }
                                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                if (matcher3.find()) {
                                }
                                Unit unit5222 = Unit.INSTANCE;
                                if (canonicalName3.length() > 23) {
                                }
                                sb3222.append(canonicalName3);
                                str6 = " - ";
                                sb3222.append(str6);
                                sb3222.append("muxAudioVideo() mux audio video completed");
                                sb3222.append(' ');
                                sb3222.append("");
                                companion9222.log(level3222, sb3222.toString());
                                if (CoreExtsKt.isRelease()) {
                                }
                                function1 = hVSessionRecorder.mCallback;
                                if (function1 == null) {
                                }
                                function1.invoke(hVSessionRecorder.sessionVideo);
                                deleteFiles();
                                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                if (m1205exceptionOrNullimpl != null) {
                                }
                            }
                        }
                    }
                    str12 = str11;
                    HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
                    HyperLogger companion62 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb22 = new StringBuilder();
                    StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace32, str12);
                    stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
                    if (stackTraceElement2 != null) {
                    }
                    str13 = str12;
                    Class<?> cls722 = getClass();
                    if (cls722 != null) {
                    }
                    if (canonicalName7 == null) {
                    }
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                    if (matcher2.find()) {
                    }
                    Unit unit3222 = Unit.INSTANCE;
                    if (str14.length() > 23) {
                    }
                    sb22.append(str14);
                    sb22.append(" - ");
                    sb22.append("mux audio video started");
                    sb22.append(' ');
                    sb22.append("");
                    companion62.log(level22, sb22.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    str8 = str13;
                    file2 = this.folderPath;
                    if (file2 == null) {
                    }
                    File file32222 = new File(file2, '/' + this.fileName + "-final.mp4");
                    this.sessionVideo = file32222;
                    Boolean.valueOf(file32222.createNewFile());
                    File file42222 = this.sessionVideo;
                    if (file42222 != null) {
                    }
                    if (absolutePath == null) {
                    }
                    MediaExtractor mediaExtractor3222 = new MediaExtractor();
                    mediaExtractor3222.setDataSource(this.videoPath);
                    MediaExtractor mediaExtractor22222 = new MediaExtractor();
                    str15 = this.audioPath;
                    if (str15 == null) {
                    }
                    mediaExtractor22222.setDataSource(str15);
                    MediaMuxer mediaMuxer2222 = new MediaMuxer(absolutePath, 0);
                    mediaExtractor3222.selectTrack(0);
                    MediaFormat trackFormat3222 = mediaExtractor3222.getTrackFormat(0);
                    Intrinsics.checkNotNullExpressionValue(trackFormat3222, "videoExtractor.getTrackFormat(0)");
                    int addTrack3222 = mediaMuxer2222.addTrack(trackFormat3222);
                    mediaExtractor22222.selectTrack(0);
                    MediaFormat trackFormat22222 = mediaExtractor22222.getTrackFormat(0);
                    Intrinsics.checkNotNullExpressionValue(trackFormat22222, "audioExtractor.getTrackFormat(0)");
                    int addTrack22222 = mediaMuxer2222.addTrack(trackFormat22222);
                    ByteBuffer allocate3222 = ByteBuffer.allocate(262144);
                    charSequence = "co.hyperverge";
                    Intrinsics.checkNotNullExpressionValue(allocate3222, "allocate(sampleSize)");
                    ByteBuffer allocate22222 = ByteBuffer.allocate(262144);
                    Intrinsics.checkNotNullExpressionValue(allocate22222, "allocate(sampleSize)");
                    MediaCodec.BufferInfo bufferInfo3222 = new MediaCodec.BufferInfo();
                    MediaCodec.BufferInfo bufferInfo22222 = new MediaCodec.BufferInfo();
                    str5 = "android.app.AppGlobals";
                    mediaExtractor3222.seekTo(0L, 2);
                    mediaExtractor22222.seekTo(0L, 2);
                    mediaMuxer2222.start();
                    z = false;
                    while (!z) {
                    }
                    z2 = false;
                    while (!z2) {
                    }
                    mediaMuxer2222.stop();
                    mediaMuxer2222.release();
                    HyperLogger.Level level32222 = HyperLogger.Level.DEBUG;
                    HyperLogger companion92222 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb32222 = new StringBuilder();
                    StackTraceElement[] stackTrace52222 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace52222, str8);
                    stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace52222);
                    if (stackTraceElement3 != null) {
                    }
                    Class<?> cls32222 = getClass();
                    if (cls32222 != null) {
                    }
                    if (canonicalName3 == null) {
                    }
                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher3.find()) {
                    }
                    Unit unit52222 = Unit.INSTANCE;
                    if (canonicalName3.length() > 23) {
                    }
                    sb32222.append(canonicalName3);
                    str6 = " - ";
                    sb32222.append(str6);
                    sb32222.append("muxAudioVideo() mux audio video completed");
                    sb32222.append(' ');
                    sb32222.append("");
                    companion92222.log(level32222, sb32222.toString());
                    if (CoreExtsKt.isRelease()) {
                    }
                    function1 = hVSessionRecorder.mCallback;
                    if (function1 == null) {
                    }
                    function1.invoke(hVSessionRecorder.sessionVideo);
                    deleteFiles();
                    m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                Class<?> cls8 = getClass();
                String canonicalName8 = cls8 == null ? cls8.getCanonicalName() : null;
                substringAfterLast$default2 = canonicalName8 != null ? str7 : canonicalName8;
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                if (matcher.find()) {
                }
                Unit unit11 = Unit.INSTANCE;
                if (substringAfterLast$default2.length() > 23) {
                    substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default2);
                sb.append(" - ");
                sb.append("muxAudioVideo() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                str12 = str11;
                HyperLogger.Level level222 = HyperLogger.Level.DEBUG;
                HyperLogger companion622 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb222 = new StringBuilder();
                StackTraceElement[] stackTrace322 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace322, str12);
                stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace322);
                if (stackTraceElement2 != null) {
                }
                str13 = str12;
                Class<?> cls7222 = getClass();
                if (cls7222 != null) {
                }
                if (canonicalName7 == null) {
                }
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                if (matcher2.find()) {
                }
                Unit unit32222 = Unit.INSTANCE;
                if (str14.length() > 23) {
                }
                sb222.append(str14);
                sb222.append(" - ");
                sb222.append("mux audio video started");
                sb222.append(' ');
                sb222.append("");
                companion622.log(level222, sb222.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                str8 = str13;
                file2 = this.folderPath;
                if (file2 == null) {
                }
                File file322222 = new File(file2, '/' + this.fileName + "-final.mp4");
                this.sessionVideo = file322222;
                Boolean.valueOf(file322222.createNewFile());
                File file422222 = this.sessionVideo;
                if (file422222 != null) {
                }
                if (absolutePath == null) {
                }
                MediaExtractor mediaExtractor32222 = new MediaExtractor();
                mediaExtractor32222.setDataSource(this.videoPath);
                MediaExtractor mediaExtractor222222 = new MediaExtractor();
                str15 = this.audioPath;
                if (str15 == null) {
                }
                mediaExtractor222222.setDataSource(str15);
                MediaMuxer mediaMuxer22222 = new MediaMuxer(absolutePath, 0);
                mediaExtractor32222.selectTrack(0);
                MediaFormat trackFormat32222 = mediaExtractor32222.getTrackFormat(0);
                Intrinsics.checkNotNullExpressionValue(trackFormat32222, "videoExtractor.getTrackFormat(0)");
                int addTrack32222 = mediaMuxer22222.addTrack(trackFormat32222);
                mediaExtractor222222.selectTrack(0);
                MediaFormat trackFormat222222 = mediaExtractor222222.getTrackFormat(0);
                Intrinsics.checkNotNullExpressionValue(trackFormat222222, "audioExtractor.getTrackFormat(0)");
                int addTrack222222 = mediaMuxer22222.addTrack(trackFormat222222);
                ByteBuffer allocate32222 = ByteBuffer.allocate(262144);
                charSequence = "co.hyperverge";
                Intrinsics.checkNotNullExpressionValue(allocate32222, "allocate(sampleSize)");
                ByteBuffer allocate222222 = ByteBuffer.allocate(262144);
                Intrinsics.checkNotNullExpressionValue(allocate222222, "allocate(sampleSize)");
                MediaCodec.BufferInfo bufferInfo32222 = new MediaCodec.BufferInfo();
                MediaCodec.BufferInfo bufferInfo222222 = new MediaCodec.BufferInfo();
                str5 = "android.app.AppGlobals";
                mediaExtractor32222.seekTo(0L, 2);
                mediaExtractor222222.seekTo(0L, 2);
                mediaMuxer22222.start();
                z = false;
                while (!z) {
                }
                z2 = false;
                while (!z2) {
                }
                mediaMuxer22222.stop();
                mediaMuxer22222.release();
                HyperLogger.Level level322222 = HyperLogger.Level.DEBUG;
                HyperLogger companion922222 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb322222 = new StringBuilder();
                StackTraceElement[] stackTrace522222 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace522222, str8);
                stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace522222);
                if (stackTraceElement3 != null) {
                }
                Class<?> cls322222 = getClass();
                if (cls322222 != null) {
                }
                if (canonicalName3 == null) {
                }
                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                if (matcher3.find()) {
                }
                Unit unit522222 = Unit.INSTANCE;
                if (canonicalName3.length() > 23) {
                }
                sb322222.append(canonicalName3);
                str6 = " - ";
                sb322222.append(str6);
                sb322222.append("muxAudioVideo() mux audio video completed");
                sb322222.append(' ');
                sb322222.append("");
                companion922222.log(level322222, sb322222.toString());
                if (CoreExtsKt.isRelease()) {
                }
                function1 = hVSessionRecorder.mCallback;
                if (function1 == null) {
                }
                function1.invoke(hVSessionRecorder.sessionVideo);
                deleteFiles();
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
        }
        str = "mCallback";
        str11 = "Throwable().stackTrace";
        Class<?> cls82 = getClass();
        if (cls82 == null) {
        }
        if (canonicalName8 != null) {
        }
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
        if (matcher.find()) {
        }
        Unit unit112 = Unit.INSTANCE;
        if (substringAfterLast$default2.length() > 23) {
        }
        sb.append(substringAfterLast$default2);
        sb.append(" - ");
        sb.append("muxAudioVideo() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        str12 = str11;
        HyperLogger.Level level2222 = HyperLogger.Level.DEBUG;
        HyperLogger companion6222 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2222 = new StringBuilder();
        StackTraceElement[] stackTrace3222 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace3222, str12);
        stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3222);
        if (stackTraceElement2 != null) {
        }
        str13 = str12;
        Class<?> cls72222 = getClass();
        if (cls72222 != null) {
        }
        if (canonicalName7 == null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
        if (matcher2.find()) {
        }
        Unit unit322222 = Unit.INSTANCE;
        if (str14.length() > 23) {
        }
        sb2222.append(str14);
        sb2222.append(" - ");
        sb2222.append("mux audio video started");
        sb2222.append(' ');
        sb2222.append("");
        companion6222.log(level2222, sb2222.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        str8 = str13;
        file2 = this.folderPath;
        if (file2 == null) {
        }
        File file3222222 = new File(file2, '/' + this.fileName + "-final.mp4");
        this.sessionVideo = file3222222;
        Boolean.valueOf(file3222222.createNewFile());
        File file4222222 = this.sessionVideo;
        if (file4222222 != null) {
        }
        if (absolutePath == null) {
        }
        MediaExtractor mediaExtractor322222 = new MediaExtractor();
        mediaExtractor322222.setDataSource(this.videoPath);
        MediaExtractor mediaExtractor2222222 = new MediaExtractor();
        str15 = this.audioPath;
        if (str15 == null) {
        }
        mediaExtractor2222222.setDataSource(str15);
        MediaMuxer mediaMuxer222222 = new MediaMuxer(absolutePath, 0);
        mediaExtractor322222.selectTrack(0);
        MediaFormat trackFormat322222 = mediaExtractor322222.getTrackFormat(0);
        Intrinsics.checkNotNullExpressionValue(trackFormat322222, "videoExtractor.getTrackFormat(0)");
        int addTrack322222 = mediaMuxer222222.addTrack(trackFormat322222);
        mediaExtractor2222222.selectTrack(0);
        MediaFormat trackFormat2222222 = mediaExtractor2222222.getTrackFormat(0);
        Intrinsics.checkNotNullExpressionValue(trackFormat2222222, "audioExtractor.getTrackFormat(0)");
        int addTrack2222222 = mediaMuxer222222.addTrack(trackFormat2222222);
        ByteBuffer allocate322222 = ByteBuffer.allocate(262144);
        charSequence = "co.hyperverge";
        Intrinsics.checkNotNullExpressionValue(allocate322222, "allocate(sampleSize)");
        ByteBuffer allocate2222222 = ByteBuffer.allocate(262144);
        Intrinsics.checkNotNullExpressionValue(allocate2222222, "allocate(sampleSize)");
        MediaCodec.BufferInfo bufferInfo322222 = new MediaCodec.BufferInfo();
        MediaCodec.BufferInfo bufferInfo2222222 = new MediaCodec.BufferInfo();
        str5 = "android.app.AppGlobals";
        mediaExtractor322222.seekTo(0L, 2);
        mediaExtractor2222222.seekTo(0L, 2);
        mediaMuxer222222.start();
        z = false;
        while (!z) {
        }
        z2 = false;
        while (!z2) {
        }
        mediaMuxer222222.stop();
        mediaMuxer222222.release();
        HyperLogger.Level level3222222 = HyperLogger.Level.DEBUG;
        HyperLogger companion9222222 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb3222222 = new StringBuilder();
        StackTraceElement[] stackTrace5222222 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace5222222, str8);
        stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5222222);
        if (stackTraceElement3 != null) {
        }
        Class<?> cls3222222 = getClass();
        if (cls3222222 != null) {
        }
        if (canonicalName3 == null) {
        }
        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
        if (matcher3.find()) {
        }
        Unit unit5222222 = Unit.INSTANCE;
        if (canonicalName3.length() > 23) {
        }
        sb3222222.append(canonicalName3);
        str6 = " - ";
        sb3222222.append(str6);
        sb3222222.append("muxAudioVideo() mux audio video completed");
        sb3222222.append(' ');
        sb3222222.append("");
        companion9222222.log(level3222222, sb3222222.toString());
        if (CoreExtsKt.isRelease()) {
        }
        function1 = hVSessionRecorder.mCallback;
        if (function1 == null) {
        }
        function1.invoke(hVSessionRecorder.sessionVideo);
        deleteFiles();
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
        }
    }

    /* compiled from: HVSessionRecorder.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/HVSessionRecorder$Companion;", "", "()V", "instance", "Lco/hyperverge/hyperkyc/hvsessionrecorder/HVSessionRecorder;", "deleteInstance", "", "getInstance", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HVSessionRecorder getInstance() {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str = "N/A";
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
            sb.append("getInstance() called");
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 != null) {
                                str = canonicalName2;
                            }
                        } else {
                            str = substringAfterLast$default;
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
                        Log.println(3, str, "getInstance() called ");
                    }
                }
            }
            if (HVSessionRecorder.instance == null) {
                HVSessionRecorder.instance = new HVSessionRecorder();
            }
            HVSessionRecorder hVSessionRecorder = HVSessionRecorder.instance;
            Intrinsics.checkNotNull(hVSessionRecorder, "null cannot be cast to non-null type co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder");
            return hVSessionRecorder;
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
        
            if (r0 == null) goto L52;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void deleteInstance() {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String className2;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str = "N/A";
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
            sb.append("deleteInstance() called");
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
                        }
                        str = canonicalName2;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                        if (matcher2.find()) {
                            str = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                        }
                        if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str = str.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str, "deleteInstance() called ");
                    }
                }
            }
            HVSessionRecorder.instance = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x005b, code lost:
    
        if (r11 != null) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void start$hyperkyc_release(final Context context, Window window, boolean recordAudio, boolean upload, String url, String stopModule, CoroutineScope lifecycleScope, final VideoStatementV2LowStorageExceptionListener lowStorageExceptionListener) {
        String str;
        String str2;
        String str3;
        Object m1202constructorimpl;
        boolean z;
        String str4;
        CoroutineScope coroutineScope;
        String str5;
        String str6;
        String str7;
        String canonicalName;
        String className;
        String canonicalName2;
        Object m1202constructorimpl2;
        String str8;
        String str9;
        Matcher matcher;
        String className2;
        String className3;
        String className4;
        Window window2 = window;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(window2, "window");
        Intrinsics.checkNotNullParameter(stopModule, "stopModule");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null) {
            str = "N/A";
        } else {
            str = "N/A";
            str2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        Class<?> cls = getClass();
        str2 = cls != null ? cls.getCanonicalName() : null;
        if (str2 == null) {
            str2 = str;
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
        String str10 = str2;
        if (matcher2.find()) {
            str3 = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
        } else {
            str3 = str10;
        }
        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str3 = str3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(str3);
        sb.append(" - ");
        String str11 = "start() called with: context = " + context + ", window = " + window2 + ", recordAudio = " + recordAudio + ", upload = " + upload + ", url = " + url + ", stopModule = " + stopModule + ", lifecycleScope = " + lifecycleScope;
        if (str11 == null) {
            str11 = "null ";
        }
        sb.append(str11);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            z = recordAudio;
            str4 = url;
            str7 = "this as java.lang.String…ing(startIndex, endIndex)";
            coroutineScope = lifecycleScope;
            str5 = "replaceAll(\"\")";
            str6 = "Throwable().stackTrace";
        } else {
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
                    str6 = "Throwable().stackTrace";
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName == null) {
                            canonicalName = str;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                    if (matcher3.find()) {
                        canonicalName = matcher3.replaceAll("");
                        str5 = "replaceAll(\"\")";
                        Intrinsics.checkNotNullExpressionValue(canonicalName, str5);
                    } else {
                        str5 = "replaceAll(\"\")";
                    }
                    if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName = canonicalName.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("start() called with: context = ");
                    sb3.append(context);
                    sb3.append(", window = ");
                    sb3.append(window2);
                    sb3.append(", recordAudio = ");
                    z = recordAudio;
                    sb3.append(z);
                    sb3.append(", upload = ");
                    sb3.append(upload);
                    sb3.append(", url = ");
                    str4 = url;
                    sb3.append(str4);
                    sb3.append(", stopModule = ");
                    sb3.append(stopModule);
                    sb3.append(", lifecycleScope = ");
                    coroutineScope = lifecycleScope;
                    sb3.append(coroutineScope);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName, sb2.toString());
                    str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                } else {
                    z = recordAudio;
                    str4 = url;
                    coroutineScope = lifecycleScope;
                }
            } else {
                z = recordAudio;
                str4 = url;
                coroutineScope = lifecycleScope;
            }
            str5 = "replaceAll(\"\")";
            str6 = "Throwable().stackTrace";
            str7 = "this as java.lang.String…ing(startIndex, endIndex)";
        }
        if (this.isRecordingStarted) {
            return;
        }
        this.lifecycleScope = coroutineScope;
        this.stopModuleId = stopModule;
        this.shouldUpload = upload;
        this.uploadUrl = str4;
        this.currentWindow = window2;
        if (window2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWindow");
            window2 = null;
        }
        Object tag = window2.getDecorView().getRootView().getTag();
        this.cameraPreviewTag = tag != null ? tag.toString() : null;
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
        this.folderPath = filesDir;
        this.captureAudio = z;
        this.bitmapToVideoEncoder = new HVBitmapToVideoEncoder(new HVBitmapToVideoEncoder.IBitmapToVideoEncoderCallback() { // from class: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$start$2
            @Override // co.hyperverge.hyperkyc.hvsessionrecorder.HVBitmapToVideoEncoder.IBitmapToVideoEncoderCallback
            public void onLowStorage() {
                HVSessionRecorder.stop$hyperkyc_release$default(HVSessionRecorder.this, null, 1, null);
                VideoStatementV2LowStorageExceptionListener videoStatementV2LowStorageExceptionListener = lowStorageExceptionListener;
                if (videoStatementV2LowStorageExceptionListener != null) {
                    videoStatementV2LowStorageExceptionListener.invoke();
                }
            }

            @Override // co.hyperverge.hyperkyc.hvsessionrecorder.HVBitmapToVideoEncoder.IBitmapToVideoEncoderCallback
            /* renamed from: getContext, reason: from getter */
            public Context get$context() {
                return context;
            }

            /* JADX WARN: Code restructure failed: missing block: B:62:0x0128, code lost:
            
                if (r0 == null) goto L52;
             */
            @Override // co.hyperverge.hyperkyc.hvsessionrecorder.HVBitmapToVideoEncoder.IBitmapToVideoEncoderCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onEncodingComplete(File outputFile) {
                String canonicalName3;
                Object m1202constructorimpl3;
                String canonicalName4;
                String className5;
                Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
                boolean z2;
                Function1 function1;
                Application.ActivityLifecycleCallbacks activityLifecycleCallbacks2;
                String className6;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb5 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str12 = "N/A";
                Function1 function12 = null;
                if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                    if (canonicalName3 == null) {
                        canonicalName3 = "N/A";
                    }
                }
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                if (matcher4.find()) {
                    canonicalName3 = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                }
                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName3 = canonicalName3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb5.append(canonicalName3);
                sb5.append(" - ");
                sb5.append("onEncodingComplete() Session recording completed");
                sb5.append(' ');
                sb5.append("");
                companion4.log(level2, sb5.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className5 = stackTraceElement4.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            }
                            str12 = canonicalName4;
                            Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                            if (matcher5.find()) {
                                str12 = matcher5.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
                            }
                            if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str12 = str12.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str12, "onEncodingComplete() Session recording completed ");
                        }
                    }
                }
                activityLifecycleCallbacks = HVSessionRecorder.this.activityLifecycleListener;
                if (activityLifecycleCallbacks != null) {
                    Context context2 = context;
                    Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type android.app.Application");
                    Application application = (Application) context2;
                    activityLifecycleCallbacks2 = HVSessionRecorder.this.activityLifecycleListener;
                    if (activityLifecycleCallbacks2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activityLifecycleListener");
                        activityLifecycleCallbacks2 = null;
                    }
                    application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks2);
                }
                z2 = HVSessionRecorder.this.captureAudio;
                if (z2) {
                    HVSessionRecorder.this.muxAudioVideo();
                    return;
                }
                HVSessionRecorder.this.setSessionVideo$hyperkyc_release(outputFile);
                function1 = HVSessionRecorder.this.mCallback;
                if (function1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCallback");
                } else {
                    function12 = function1;
                }
                function12.invoke(outputFile);
            }
        });
        if (this.captureAudio) {
            this.hvAudioRecorder = new HVAudioRecorder();
        }
        this.isRecordingStarted = true;
        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb5 = new StringBuilder();
        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace3, str6);
        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls3 = getClass();
            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
            if (canonicalName2 == null) {
                canonicalName2 = str;
            }
        }
        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
        if (matcher4.find()) {
            canonicalName2 = matcher4.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName2, str5);
        }
        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName2 = canonicalName2.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName2, str7);
        }
        sb5.append(canonicalName2);
        sb5.append(" - ");
        sb5.append("start() Session recording started");
        sb5.append(' ');
        sb5.append("");
        companion4.log(level2, sb5.toString());
        if (!CoreExtsKt.isRelease()) {
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
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str6);
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str8 = null;
                    } else {
                        str8 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str9 = substringAfterLast$default;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                            if (matcher.find()) {
                                str9 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str9, str5);
                            }
                            if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str9 = str9.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str9, str7);
                            }
                            Log.println(3, str9, "start() Session recording started ");
                        }
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : str8;
                    str9 = canonicalName3 == null ? str : canonicalName3;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                    if (matcher.find()) {
                    }
                    if (str9.length() > 23) {
                        str9 = str9.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str9, str7);
                    }
                    Log.println(3, str9, "start() Session recording started ");
                }
            }
        }
        registerForScreenRecording(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x016b, code lost:
    
        if (r0 != null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startRecordingCameraPreview$hyperkyc_release(Context context, Window window, boolean recordAudio, CoroutineScope lifecycleScope, VideoStatementV2LowStorageExceptionListener lowStorageExceptionListener) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(window, "window");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
        Intrinsics.checkNotNullParameter(lowStorageExceptionListener, "lowStorageExceptionListener");
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
        String str3 = "startRecordingCameraPreview() called with: context = " + context + ", window = " + window + ", recordAudio = " + recordAudio + ", lifecycleScope = " + lifecycleScope;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (str2 == null) {
                        str2 = "N/A";
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "startRecordingCameraPreview() called with: context = " + context + ", window = " + window + ", recordAudio = " + recordAudio + ", lifecycleScope = " + lifecycleScope;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
            }
        }
        this.shouldRecordPreviewOnly = true;
        this.bitmapHeight = 400;
        this.bitmapWidth = 300;
        start$hyperkyc_release(context, window, recordAudio, false, "", "", lifecycleScope, lowStorageExceptionListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void stop$hyperkyc_release(Function1<? super File, Unit> callback) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        String canonicalName3;
        Object m1202constructorimpl2;
        String str2;
        String className2;
        String substringAfterLast$default;
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(callback, "callback");
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
        String str3 = "stop() called with: callback = " + callback;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                charSequence = "co.hyperverge";
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "stop() called with: callback = " + callback;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                if (this.isRecordingStarted) {
                    return;
                }
                this.mCallback = callback;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                    if (canonicalName3 == null) {
                        canonicalName3 = str;
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                sb3.append("stop() Session recording stopping");
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
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
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                str2 = canonicalName4 == null ? str : canonicalName4;
                            } else {
                                str2 = substringAfterLast$default;
                            }
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                            if (matcher4.find()) {
                                str2 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                            }
                            if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str2 = str2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str2, "stop() Session recording stopping ");
                        }
                    }
                }
                this.isRecordingStarted = false;
                Timer timer = this.timer;
                if (timer != null) {
                    timer.cancel();
                }
                HVAudioRecorder hVAudioRecorder = this.hvAudioRecorder;
                if (hVAudioRecorder != null) {
                    hVAudioRecorder.stopAudioRecord$hyperkyc_release();
                }
                this.hvAudioRecorder = null;
                HVBitmapToVideoEncoder hVBitmapToVideoEncoder2 = this.bitmapToVideoEncoder;
                if (hVBitmapToVideoEncoder2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
                    hVBitmapToVideoEncoder = null;
                } else {
                    hVBitmapToVideoEncoder = hVBitmapToVideoEncoder2;
                }
                hVBitmapToVideoEncoder.stopEncoding$hyperkyc_release();
                return;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        if (this.isRecordingStarted) {
        }
    }

    public final void pause$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
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
        sb.append("pause() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
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
                    Log.println(3, str, "pause() called ");
                }
            }
        }
        if (this.isRecordingStarted) {
            Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
            }
            HVAudioRecorder hVAudioRecorder = this.hvAudioRecorder;
            if (hVAudioRecorder != null) {
                hVAudioRecorder.pauseAudioRecord$hyperkyc_release();
            }
        }
    }

    public final void resume$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
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
        sb.append("resume() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
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
                    Log.println(3, str, "resume() called ");
                }
            }
        }
        if (this.isRecordingStarted) {
            HVAudioRecorder hVAudioRecorder = this.hvAudioRecorder;
            if (hVAudioRecorder != null) {
                hVAudioRecorder.resumeAudioRecord$hyperkyc_release();
            }
            createBitmaps();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void registerForScreenRecording(Context context) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = null;
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
        String str2 = "registerForScreenRecording() called with: context = " + context;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "registerForScreenRecording() called with: context = " + context;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        this.activityLifecycleListener = new Application.ActivityLifecycleCallbacks() { // from class: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$registerForScreenRecording$2
            private final String TAG = "LifecycleCallbacks";

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity p0) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivityPaused at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
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
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName4 != null) {
                                    str4 = canonicalName4;
                                }
                            } else {
                                str4 = substringAfterLast$default;
                            }
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher4.find()) {
                                str4 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                            }
                            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            String str6 = "onActivityPaused at " + p0.getLocalClassName();
                            sb4.append(str6 != null ? str6 : "null ");
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str4, sb4.toString());
                        }
                    }
                }
                HVSessionRecorder.this.pause$hyperkyc_release();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity p0) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivityStarted at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
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
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            if (canonicalName4 != null) {
                                str4 = canonicalName4;
                            }
                        } else {
                            str4 = substringAfterLast$default;
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb4 = new StringBuilder();
                        String str6 = "onActivityStarted at " + p0.getLocalClassName();
                        sb4.append(str6 != null ? str6 : "null ");
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, str4, sb4.toString());
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity p0) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivityDestroyed at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
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
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            if (canonicalName4 != null) {
                                str4 = canonicalName4;
                            }
                        } else {
                            str4 = substringAfterLast$default;
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb4 = new StringBuilder();
                        String str6 = "onActivityDestroyed at " + p0.getLocalClassName();
                        sb4.append(str6 != null ? str6 : "null ");
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, str4, sb4.toString());
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity p0, Bundle p1) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivitySaveInstanceState at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
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
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            if (canonicalName4 != null) {
                                str4 = canonicalName4;
                            }
                        } else {
                            str4 = substringAfterLast$default;
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb4 = new StringBuilder();
                        String str6 = "onActivitySaveInstanceState at " + p0.getLocalClassName();
                        sb4.append(str6 != null ? str6 : "null ");
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, str4, sb4.toString());
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity p0) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivityStopped at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
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
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            if (canonicalName4 != null) {
                                str4 = canonicalName4;
                            }
                        } else {
                            str4 = substringAfterLast$default;
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb4 = new StringBuilder();
                        String str6 = "onActivityStopped at " + p0.getLocalClassName();
                        sb4.append(str6 != null ? str6 : "null ");
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, str4, sb4.toString());
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity p0, Bundle p1) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivityCreated at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
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
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            if (canonicalName4 != null) {
                                str4 = canonicalName4;
                            }
                        } else {
                            str4 = substringAfterLast$default;
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb4 = new StringBuilder();
                        String str6 = "onActivityCreated at " + p0.getLocalClassName();
                        sb4.append(str6 != null ? str6 : "null ");
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, str4, sb4.toString());
                    }
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:63:0x0145, code lost:
            
                if (r0 == null) goto L55;
             */
            @Override // android.app.Application.ActivityLifecycleCallbacks
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onActivityResumed(Activity p0) {
                String canonicalName3;
                Object m1202constructorimpl2;
                String canonicalName4;
                String className3;
                Window window;
                Timer timer;
                String className4;
                Intrinsics.checkNotNullParameter(p0, "p0");
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                sb3.append(canonicalName3);
                sb3.append(" - ");
                String str5 = "onActivityResumed at " + p0.getLocalClassName();
                if (str5 == null) {
                    str5 = "null ";
                }
                sb3.append(str5);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
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
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                            }
                            str4 = canonicalName4;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher4.find()) {
                                str4 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                            }
                            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            String str6 = "onActivityResumed at " + p0.getLocalClassName();
                            sb4.append(str6 != null ? str6 : "null ");
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str4, sb4.toString());
                        }
                    }
                }
                HVSessionRecorder hVSessionRecorder = HVSessionRecorder.this;
                Window window2 = p0.getWindow();
                Intrinsics.checkNotNullExpressionValue(window2, "p0.window");
                hVSessionRecorder.currentWindow = window2;
                HVSessionRecorder hVSessionRecorder2 = HVSessionRecorder.this;
                window = hVSessionRecorder2.currentWindow;
                if (window == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentWindow");
                    window = null;
                }
                Object tag = window.getDecorView().getRootView().getTag();
                hVSessionRecorder2.cameraPreviewTag = tag != null ? tag.toString() : null;
                timer = HVSessionRecorder.this.timer;
                if (timer != null) {
                    timer.cancel();
                }
                HVSessionRecorder.this.resume$hyperkyc_release();
            }
        };
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Application");
        Application application = (Application) context;
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks2 = this.activityLifecycleListener;
        if (activityLifecycleCallbacks2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityLifecycleListener");
        } else {
            activityLifecycleCallbacks = activityLifecycleCallbacks2;
        }
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private final void createBitmaps() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
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
        sb.append("createBitmaps() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
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
                    Log.println(3, str, "createBitmaps() called ");
                }
            }
        }
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
        long j = this.timerRate;
        Timer timer2 = TimersKt.timer(WorkflowModule.Properties.Section.Component.Type.TIMER, false);
        timer2.scheduleAtFixedRate(new TimerTask() { // from class: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$createBitmaps$$inlined$fixedRateTimer$1
            /* JADX WARN: Code restructure failed: missing block: B:48:0x0194, code lost:
            
                if (r0 != null) goto L70;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x01a4, code lost:
            
                if (r0 == null) goto L71;
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x01a8, code lost:
            
                r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r11);
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x01b7, code lost:
            
                if (r0.find() == false) goto L74;
             */
            /* JADX WARN: Code restructure failed: missing block: B:55:0x01b9, code lost:
            
                r11 = r0.replaceAll("");
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "replaceAll(\"\")");
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x01c4, code lost:
            
                if (r11.length() <= 23) goto L80;
             */
            /* JADX WARN: Code restructure failed: missing block: B:59:0x01c8, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 26) goto L79;
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x01cb, code lost:
            
                r11 = r11.substring(0, 23);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "this as java.lang.String…ing(startIndex, endIndex)");
             */
            /* JADX WARN: Code restructure failed: missing block: B:61:0x01d2, code lost:
            
                r0 = new java.lang.StringBuilder();
                r2 = r5.getMessage();
             */
            /* JADX WARN: Code restructure failed: missing block: B:62:0x01db, code lost:
            
                if (r2 != null) goto L83;
             */
            /* JADX WARN: Code restructure failed: missing block: B:63:0x01de, code lost:
            
                r13 = r2;
             */
            /* JADX WARN: Code restructure failed: missing block: B:64:0x01df, code lost:
            
                r0.append(r13);
                r0.append(' ');
                r0.append("");
                android.util.Log.println(6, r11, r0.toString());
             */
            /* JADX WARN: Code restructure failed: missing block: B:65:0x01f2, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:68:0x01a7, code lost:
            
                r11 = r0;
             */
            @Override // java.util.TimerTask, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                Window window;
                Object m1202constructorimpl2;
                String canonicalName3;
                Object m1202constructorimpl3;
                String str2;
                String str3;
                String className3;
                String className4;
                int i;
                int i2;
                int i3;
                int i4;
                HVSessionRecorder$createBitmaps$$inlined$fixedRateTimer$1 hVSessionRecorder$createBitmaps$$inlined$fixedRateTimer$1 = this;
                window = HVSessionRecorder.this.currentWindow;
                if (window == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentWindow");
                    window = null;
                }
                View rootView = window.getDecorView().getRootView();
                Intrinsics.checkNotNullExpressionValue(rootView, "currentWindow.decorView.rootView");
                if (!rootView.isLaidOut()) {
                    return;
                }
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    i = HVSessionRecorder.this.bitmapHeight;
                    if (i == 0) {
                        i2 = HVSessionRecorder.this.bitmapWidth;
                        HVSessionRecorder.this.bitmapHeight = (int) ((rootView.getHeight() / rootView.getWidth()) * i2);
                        i3 = HVSessionRecorder.this.bitmapHeight;
                        if (i3 % 2 != 0) {
                            HVSessionRecorder hVSessionRecorder = HVSessionRecorder.this;
                            i4 = hVSessionRecorder.bitmapHeight;
                            hVSessionRecorder.bitmapHeight = i4 + 1;
                        }
                    }
                    HVSessionRecorder.this.viewToBitmap(rootView);
                    m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    Result.Companion companion5 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                if (m1205exceptionOrNullimpl == null) {
                    return;
                }
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = hVSessionRecorder$createBitmaps$$inlined$fixedRateTimer$1.getClass();
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
                String message = m1205exceptionOrNullimpl.getMessage();
                String str5 = "null ";
                if (message == null) {
                    message = "null ";
                }
                sb2.append(message);
                sb2.append(' ');
                sb2.append("");
                companion6.log(level2, sb2.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion7 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th3) {
                    Result.Companion companion8 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String packageName2 = (String) m1202constructorimpl3;
                if (!CoreExtsKt.isDebug()) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (!StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    return;
                }
                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                    str2 = null;
                } else {
                    str2 = null;
                    str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls4 = hVSessionRecorder$createBitmaps$$inlined$fixedRateTimer$1.getClass();
                str3 = cls4 != null ? cls4.getCanonicalName() : str2;
            }
        }, 0L, j);
        this.timer = timer2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void viewToBitmap(View view) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        Object m1202constructorimpl2;
        String str2;
        Object m1202constructorimpl3;
        String str3;
        String className2;
        String substringAfterLast$default;
        String className3;
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
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
        String str4 = "viewToBitmap() called with: view = " + view;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
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
                charSequence = "co.hyperverge";
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str5 = "viewToBitmap() called with: view = " + view;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                if (!this.shouldRecordPreviewOnly) {
                    if (this.cameraPreviewTag != null) {
                        CoroutineScope coroutineScope3 = this.lifecycleScope;
                        if (coroutineScope3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("lifecycleScope");
                            coroutineScope2 = null;
                        } else {
                            coroutineScope2 = coroutineScope3;
                        }
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new HVSessionRecorder$viewToBitmap$2(view, this, null), 3, null);
                        return;
                    }
                    return;
                }
                if (SessionRecorderUtilsKt.getIsAndroid8Plus()) {
                    try {
                        Result.Companion companion4 = Result.INSTANCE;
                        HVSessionRecorder hVSessionRecorder = this;
                        final Bitmap createBitmap = Bitmap.createBitmap(this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
                        int[] iArr = new int[2];
                        view.getLocationInWindow(iArr);
                        Window window = this.currentWindow;
                        if (window == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("currentWindow");
                            window = null;
                        }
                        PixelCopy.request(window, new Rect(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight()), createBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$$ExternalSyntheticLambda0
                            @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                            public final void onPixelCopyFinished(int i) {
                                HVSessionRecorder.viewToBitmap$lambda$15$lambda$14(HVSessionRecorder.this, createBitmap, i);
                            }
                        }, new Handler(Looper.getMainLooper()));
                        m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        Result.Companion companion5 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                    if (m1205exceptionOrNullimpl != null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                            str2 = canonicalName3 == null ? str : canonicalName3;
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                        if (matcher3.find()) {
                            str2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                        }
                        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str2);
                        sb3.append(" - ");
                        String str6 = "viewToBitmap() error in Android 8+: " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb3.append(str6);
                        sb3.append(' ');
                        sb3.append("");
                        companion6.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                    str3 = canonicalName4 == null ? str : canonicalName4;
                                } else {
                                    str3 = substringAfterLast$default;
                                }
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                if (matcher4.find()) {
                                    str3 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                }
                                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str3 = str3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str7 = "viewToBitmap() error in Android 8+: " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                                if (str7 == null) {
                                    str7 = "null ";
                                }
                                sb4.append(str7);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(6, str3, sb4.toString());
                            }
                        }
                    }
                    return;
                }
                CoroutineScope coroutineScope4 = this.lifecycleScope;
                if (coroutineScope4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lifecycleScope");
                    coroutineScope = null;
                } else {
                    coroutineScope = coroutineScope4;
                }
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new HVSessionRecorder$viewToBitmap$5(view, this, null), 3, null);
                return;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        if (!this.shouldRecordPreviewOnly) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void queueBitmap(Bitmap bitmap) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        String str2;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder = null;
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
        String str4 = "queueBitmap() called with: bitmap = " + bitmap;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
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
                    }
                    str3 = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher2.find()) {
                        str3 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                    }
                    if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str5 = "queueBitmap() called with: bitmap = " + bitmap;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str3, sb2.toString());
                }
            }
        }
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder2 = this.bitmapToVideoEncoder;
        if (hVBitmapToVideoEncoder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
            hVBitmapToVideoEncoder2 = null;
        }
        if (!hVBitmapToVideoEncoder2.isEncodingStarted$hyperkyc_release() && this.isRecordingStarted) {
            UUID randomUUID = UUID.randomUUID();
            File file = this.folderPath;
            if (file == null) {
                Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                file = null;
            }
            File file2 = new File(file, randomUUID + ".mp4");
            HVBitmapToVideoEncoder hVBitmapToVideoEncoder3 = this.bitmapToVideoEncoder;
            if (hVBitmapToVideoEncoder3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
                hVBitmapToVideoEncoder3 = null;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            CoroutineScope coroutineScope = this.lifecycleScope;
            if (coroutineScope == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lifecycleScope");
                coroutineScope = null;
            }
            hVBitmapToVideoEncoder3.startEncoding$hyperkyc_release(width, height, file2, coroutineScope);
            String absolutePath = file2.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
            this.videoPath = absolutePath;
            String uuid = randomUUID.toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "videoName.toString()");
            this.fileName = uuid;
            if (this.captureAudio) {
                File file3 = this.folderPath;
                if (file3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                    file3 = null;
                }
                str = new File(file3, '/' + this.fileName + "-final.mp4").getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(str, "{\n                    Fi…utePath\n                }");
            } else {
                str = this.videoPath;
            }
            this.filePath = str;
            HVAudioRecorder hVAudioRecorder = this.hvAudioRecorder;
            if (hVAudioRecorder != null) {
                String str6 = this.fileName;
                File file4 = this.folderPath;
                if (file4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                    file4 = null;
                }
                CoroutineScope coroutineScope2 = this.lifecycleScope;
                if (coroutineScope2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lifecycleScope");
                    coroutineScope2 = null;
                }
                str2 = hVAudioRecorder.initializeAudioRecorder$hyperkyc_release(str6, file4, coroutineScope2);
            } else {
                str2 = null;
            }
            this.audioPath = str2;
        }
        HVBitmapToVideoEncoder hVBitmapToVideoEncoder4 = this.bitmapToVideoEncoder;
        if (hVBitmapToVideoEncoder4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bitmapToVideoEncoder");
        } else {
            hVBitmapToVideoEncoder = hVBitmapToVideoEncoder4;
        }
        hVBitmapToVideoEncoder.queueFrame$hyperkyc_release(bitmap);
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void deleteFiles() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        File file = null;
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
        sb.append("deleteFiles() called");
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
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "deleteFiles() called ");
                }
            }
        }
        File file2 = new File(this.videoPath);
        File file3 = this.folderPath;
        if (file3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("folderPath");
        } else {
            file = file3;
        }
        File file4 = new File(file, '/' + this.fileName + "-raw.pcm");
        String str2 = this.audioPath;
        new File(str2 != null ? str2 : "");
        if (file2.exists()) {
            file2.delete();
        }
        if (file4.exists()) {
            file4.delete();
        }
    }
}
