package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.app.Application;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.apache.commons.io.FilenameUtils;

/* compiled from: AudioRecorder.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0015\b\u0000\u0018\u0000 (2\u00020\u0001:\u0002()B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\r\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0017J\u001d\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u001bJ%\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001fJ\u0015\u0010 \u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u0015H\u0001¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\u0015H\u0000¢\u0006\u0002\b%J\r\u0010&\u001a\u00020\u0015H\u0000¢\u0006\u0002\b'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/AudioRecorder;", "", "()V", "converted", "Ljava/io/File;", "file", "fileName", "", "folderPath", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "partialFileOutputStream", "Ljava/io/FileOutputStream;", "recorder", "Landroid/media/AudioRecord;", "recordingInProgress", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getBufferReadFailureReason", "errorCode", "", "performAudioWriting", "", "rawToMP4", "rawToMP4$hyperkyc_release", "rawToWav", "inputFile", "outputFile", "rawToWav$hyperkyc_release", "setFileName", "name", Constants.PATH, "setFileName$hyperkyc_release", "startPartialAudioRecording", "startPartialAudioRecording$hyperkyc_release", "startRecording", "startRecording$hyperkyc_release", "stopPartialAudioRecording", "stopPartialAudioRecording$hyperkyc_release", "stopRecording", "stopRecording$hyperkyc_release", "Companion", "RawToWavUtils", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AudioRecorder {

    @Deprecated
    private static final int AUDIO_FORMAT = 2;

    @Deprecated
    private static final int BUFFER_SIZE_FACTOR = 2;

    @Deprecated
    private static final int CHANNEL_CONFIG = 16;

    @Deprecated
    private static final int TRANSFER_BUFFER_SIZE = 10240;
    private File converted;
    private File file;
    private String fileName;
    private File folderPath;
    private CoroutineScope lifecycleScope;
    private FileOutputStream partialFileOutputStream;
    private AudioRecord recorder;
    private final AtomicBoolean recordingInProgress = new AtomicBoolean(false);
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final int SAMPLING_RATE_IN_HZ = 44100;

    @Deprecated
    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLING_RATE_IN_HZ, 16, 2) * 2;

    /* JADX WARN: Can't wrap try/catch for region: R(18:271|(1:339)(2:275|(14:277|278|(1:280)|281|(1:286)|287|(1:289)|290|291|292|293|(1:295)|296|(2:298|(10:300|(3:321|(1:323)(1:326)|(1:325))(1:306)|307|(1:309)|310|(1:315)|316|(1:318)|319|320)(1:327))(1:328)))|332|(1:334)(1:338)|(1:336)(1:337)|278|(0)|281|(2:283|286)|287|(0)|290|291|292|293|(0)|296|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(31:164|(1:268)(1:168)|170|(1:172)(1:176)|(1:174)(1:175)|177|(1:179)|180|181|(3:254|255|(1:257)(24:258|259|260|261|184|185|186|187|188|190|191|192|193|194|195|196|197|198|(1:200)|201|202|(4:204|205|206|(7:208|(3:225|(1:227)(1:230)|(1:229))(1:214)|215|(1:217)|218|(1:223)|224))(1:236)|231|232))|183|184|185|186|187|188|190|191|192|193|194|195|196|197|198|(0)|201|202|(0)(0)|231|232) */
    /* JADX WARN: Can't wrap try/catch for region: R(35:1|(5:2|3|4|5|6)|(3:423|424|(35:426|427|428|(29:430|14|(1:16)|17|(1:22)|23|(6:376|377|378|(1:380)|381|(3:383|384|(31:386|387|388|389|(3:406|(1:408)(1:411)|(1:410))(1:395)|396|(1:398)|399|(1:404)|405|26|27|28|29|(1:31)|32|(1:34)|35|36|37|38|39|40|41|42|43|44|(10:45|(6:49|50|51|(3:57|58|(3:64|65|66)(4:60|61|62|63))(3:53|54|55)|56|46)|144|(3:145|(5:147|148|149|(4:151|(1:159)(1:155)|156|157)(2:160|(3:340|341|342)(2:162|(31:164|(1:268)(1:168)|170|(1:172)(1:176)|(1:174)(1:175)|177|(1:179)|180|181|(3:254|255|(1:257)(24:258|259|260|261|184|185|186|187|188|190|191|192|193|194|195|196|197|198|(1:200)|201|202|(4:204|205|206|(7:208|(3:225|(1:227)(1:230)|(1:229))(1:214)|215|(1:217)|218|(1:223)|224))(1:236)|231|232))|183|184|185|186|187|188|190|191|192|193|194|195|196|197|198|(0)|201|202|(0)(0)|231|232)(4:269|(18:271|(1:339)(2:275|(14:277|278|(1:280)|281|(1:286)|287|(1:289)|290|291|292|293|(1:295)|296|(2:298|(10:300|(3:321|(1:323)(1:326)|(1:325))(1:306)|307|(1:309)|310|(1:315)|316|(1:318)|319|320)(1:327))(1:328)))|332|(1:334)(1:338)|(1:336)(1:337)|278|(0)|281|(2:283|286)|287|(0)|290|291|292|293|(0)|296|(0)(0))|231|232)))|158)|346)|347|348|349|(1:351)|352|(1:355)(1:354))|356|71|(15:73|(3:135|(1:137)(1:141)|(1:139)(1:140))|79|(1:81)|82|(1:86)|87|(1:89)|90|91|92|93|(1:95)|96|(2:98|(13:100|(1:129)(2:104|(9:106|107|(1:109)|110|(1:114)|115|(1:117)(1:121)|118|119))|122|(1:124)(1:128)|(1:126)(1:127)|107|(0)|110|(2:112|114)|115|(0)(0)|118|119)(1:130))(1:131))(1:142))))|25|26|27|28|29|(0)|32|(0)|35|36|37|38|39|40|41|42|43|44|(12:45|(6:49|50|51|(0)(0)|56|46)|359|144|(4:145|(0)|346|342)|347|348|349|(0)|352|(0)(0)|354)|356|71|(0)(0))|9|(1:11)(1:422)|(1:13)(1:421)|14|(0)|17|(2:19|22)|23|(0)|25|26|27|28|29|(0)|32|(0)|35|36|37|38|39|40|41|42|43|44|(12:45|(1:46)|359|144|(4:145|(0)|346|342)|347|348|349|(0)|352|(0)(0)|354)|356|71|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|(0)|25|26|27|28|29|(0)|32|(0)|35|36|37|38|39|40|41|42|43|44|(12:45|(1:46)|359|144|(4:145|(0)|346|342)|347|348|349|(0)|352|(0)(0)|354)|356|71|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x034e, code lost:
    
        if (r11 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x03f5, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0400, code lost:
    
        r6 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x03f7, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x03f8, code lost:
    
        r5 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x03fb, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x03fc, code lost:
    
        r5 = r30;
        r11 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x05c4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x05c5, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x0729, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x072a, code lost:
    
        r11 = "getInitialApplication";
        r32 = "android.app.AppGlobals";
        r6 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x0732, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x0733, code lost:
    
        r11 = "getInitialApplication";
        r32 = "android.app.AppGlobals";
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x073a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x073b, code lost:
    
        r27 = "co.hyperverge";
        r32 = "android.app.AppGlobals";
        r6 = "packageName";
        r11 = "getInitialApplication";
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x08c3  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x08ff  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0072 A[Catch: all -> 0x074b, TryCatch #9 {all -> 0x074b, blocks: (B:428:0x0052, B:14:0x007e, B:16:0x008f, B:17:0x0096, B:19:0x00a0, B:22:0x00a7, B:23:0x00af, B:378:0x0100, B:381:0x0107, B:418:0x00f6, B:9:0x006c, B:11:0x0072, B:377:0x00d3), top: B:427:0x0052, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0902  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008f A[Catch: all -> 0x074b, TryCatch #9 {all -> 0x074b, blocks: (B:428:0x0052, B:14:0x007e, B:16:0x008f, B:17:0x0096, B:19:0x00a0, B:22:0x00a7, B:23:0x00af, B:378:0x0100, B:381:0x0107, B:418:0x00f6, B:9:0x006c, B:11:0x0072, B:377:0x00d3), top: B:427:0x0052, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a0 A[Catch: all -> 0x074b, TryCatch #9 {all -> 0x074b, blocks: (B:428:0x0052, B:14:0x007e, B:16:0x008f, B:17:0x0096, B:19:0x00a0, B:22:0x00a7, B:23:0x00af, B:378:0x0100, B:381:0x0107, B:418:0x00f6, B:9:0x006c, B:11:0x0072, B:377:0x00d3), top: B:427:0x0052, inners: #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0552 A[Catch: all -> 0x0686, TryCatch #4 {all -> 0x0686, blocks: (B:206:0x041b, B:208:0x0433, B:210:0x0449, B:212:0x044f, B:215:0x046a, B:217:0x047b, B:218:0x0482, B:220:0x048c, B:223:0x0493, B:224:0x049b, B:225:0x045a, B:227:0x0460, B:271:0x04f3, B:273:0x0516, B:275:0x051c, B:278:0x0541, B:280:0x0552, B:281:0x0559, B:283:0x0563, B:286:0x056a, B:287:0x0572, B:290:0x058d, B:293:0x05cf, B:296:0x05d6, B:298:0x05de, B:300:0x05f0, B:302:0x0606, B:304:0x060c, B:307:0x0627, B:309:0x0638, B:310:0x063f, B:312:0x0649, B:315:0x0650, B:316:0x0658, B:319:0x0672, B:321:0x0617, B:323:0x061d, B:331:0x05c5, B:332:0x052f, B:334:0x0535, B:292:0x05a2), top: B:205:0x041b, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:289:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x05d5  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x05de A[Catch: all -> 0x0686, TryCatch #4 {all -> 0x0686, blocks: (B:206:0x041b, B:208:0x0433, B:210:0x0449, B:212:0x044f, B:215:0x046a, B:217:0x047b, B:218:0x0482, B:220:0x048c, B:223:0x0493, B:224:0x049b, B:225:0x045a, B:227:0x0460, B:271:0x04f3, B:273:0x0516, B:275:0x051c, B:278:0x0541, B:280:0x0552, B:281:0x0559, B:283:0x0563, B:286:0x056a, B:287:0x0572, B:290:0x058d, B:293:0x05cf, B:296:0x05d6, B:298:0x05de, B:300:0x05f0, B:302:0x0606, B:304:0x060c, B:307:0x0627, B:309:0x0638, B:310:0x063f, B:312:0x0649, B:315:0x0650, B:316:0x0658, B:319:0x0672, B:321:0x0617, B:323:0x061d, B:331:0x05c5, B:332:0x052f, B:334:0x0535, B:292:0x05a2), top: B:205:0x041b, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01b2 A[Catch: all -> 0x073a, TryCatch #19 {all -> 0x073a, blocks: (B:29:0x01ae, B:31:0x01b2, B:32:0x01b8, B:34:0x01c1, B:35:0x01c7), top: B:28:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:328:0x068c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01c1 A[Catch: all -> 0x073a, TryCatch #19 {all -> 0x073a, blocks: (B:29:0x01ae, B:31:0x01b2, B:32:0x01b8, B:34:0x01c1, B:35:0x01c7), top: B:28:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06dc A[Catch: all -> 0x0727, TryCatch #6 {all -> 0x0727, blocks: (B:349:0x06d8, B:351:0x06dc, B:352:0x06e2, B:356:0x06f7), top: B:348:0x06d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0708 A[LOOP:0: B:45:0x0224->B:354:0x0708, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:355:0x06f7 A[EDGE_INSN: B:355:0x06f7->B:356:0x06f7 BREAK  A[LOOP:0: B:45:0x0224->B:354:0x0708], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:376:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:421:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0233 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0292 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x023f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x077e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void rawToMP4$hyperkyc_release() {
        CharSequence charSequence;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        Object obj;
        Throwable m1205exceptionOrNullimpl;
        String str10;
        Object m1202constructorimpl;
        String str11;
        String str12;
        Matcher matcher;
        String className;
        String className2;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement;
        String className3;
        String str13;
        String substringAfterLast$default;
        Matcher matcher2;
        Object m1202constructorimpl2;
        String canonicalName;
        String className4;
        FileInputStream fileInputStream;
        File file;
        MediaMuxer mediaMuxer;
        File file2;
        MediaCodec createEncoderByType;
        ByteBuffer[] inputBuffers;
        ByteBuffer[] outputBuffers;
        MediaCodec.BufferInfo bufferInfo;
        String str14;
        byte[] bArr;
        double d;
        int i;
        int i2;
        double d2;
        int i3;
        int i4;
        int i5;
        int i6;
        FileInputStream fileInputStream2;
        MediaCodec.BufferInfo bufferInfo2;
        MediaMuxer mediaMuxer2;
        File file3;
        byte[] bArr2;
        double d3;
        int dequeueOutputBuffer;
        FileInputStream fileInputStream3;
        int i7;
        byte[] bArr3;
        MediaCodec mediaCodec;
        ByteBuffer[] byteBufferArr;
        MediaMuxer mediaMuxer3;
        int i8;
        MediaCodec.BufferInfo bufferInfo3;
        String str15;
        Matcher matcher3;
        String str16;
        Object m1202constructorimpl3;
        String canonicalName2;
        String className5;
        String className6;
        String str17;
        Object m1202constructorimpl4;
        String canonicalName3;
        String className7;
        String className8;
        String str18 = "null cannot be cast to non-null type android.app.Application";
        String str19 = "getInitialApplication";
        String str20 = "android.app.AppGlobals";
        String str21 = " - ";
        String str22 = "this as java.lang.String…ing(startIndex, endIndex)";
        try {
            Result.Companion companion2 = Result.INSTANCE;
            AudioRecorder audioRecorder = this;
            level = HyperLogger.Level.DEBUG;
            companion = HyperLogger.INSTANCE.getInstance();
            sb = new StringBuilder();
            str2 = "null ";
            try {
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            } catch (Throwable th) {
                th = th;
                charSequence = "co.hyperverge";
                str = "android.app.AppGlobals";
                str3 = "N/A";
                str4 = "packageName";
                str5 = "getInitialApplication";
                str6 = "Throwable().stackTrace";
                str7 = "null cannot be cast to non-null type android.app.Application";
                str8 = " - ";
                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                Result.Companion companion3 = Result.INSTANCE;
                obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl == null) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            charSequence = "co.hyperverge";
            str = "android.app.AppGlobals";
            str2 = "null ";
        }
        if (stackTraceElement != null) {
            try {
                className3 = stackTraceElement.getClassName();
            } catch (Throwable th3) {
                th = th3;
                str3 = "N/A";
                charSequence = "co.hyperverge";
                str5 = "getInitialApplication";
                str = "android.app.AppGlobals";
                str6 = "Throwable().stackTrace";
                str7 = "null cannot be cast to non-null type android.app.Application";
                str4 = "packageName";
                str8 = " - ";
                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                Result.Companion companion32 = Result.INSTANCE;
                obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl == null) {
                }
            }
            if (className3 != null) {
                str3 = "N/A";
                str13 = "Throwable().stackTrace";
                try {
                    substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                } catch (Throwable th4) {
                    th = th4;
                    charSequence = "co.hyperverge";
                    str5 = "getInitialApplication";
                    str = "android.app.AppGlobals";
                    str6 = str13;
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    str4 = "packageName";
                    str8 = " - ";
                    str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                    Result.Companion companion322 = Result.INSTANCE;
                    obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                if (substringAfterLast$default != null) {
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                    if (matcher2.find()) {
                        substringAfterLast$default = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                    }
                    Unit unit = Unit.INSTANCE;
                    if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default);
                    sb.append(" - ");
                    sb.append("rawToMP4() called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th5) {
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            try {
                                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            } catch (Throwable th6) {
                                th = th6;
                                str6 = str13;
                                charSequence = "co.hyperverge";
                                str5 = "getInitialApplication";
                                str = "android.app.AppGlobals";
                                str7 = "null cannot be cast to non-null type android.app.Application";
                                str4 = "packageName";
                                str8 = " - ";
                                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                                Result.Companion companion3222 = Result.INSTANCE;
                                obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                if (m1205exceptionOrNullimpl == null) {
                                }
                            }
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str6 = str13;
                                try {
                                    Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                    if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls = getClass();
                                        canonicalName = cls != null ? cls.getCanonicalName() : null;
                                        if (canonicalName == null) {
                                            canonicalName = str3;
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
                                    Log.println(3, canonicalName, "rawToMP4() called ");
                                    file = this.file;
                                    if (file == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("file");
                                        file = null;
                                    }
                                    fileInputStream = new FileInputStream(file);
                                    file2 = this.converted;
                                    if (file2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("converted");
                                        file2 = null;
                                    }
                                    mediaMuxer = new MediaMuxer(file2.getAbsolutePath(), 0);
                                    MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", SAMPLING_RATE_IN_HZ, 1);
                                    Intrinsics.checkNotNullExpressionValue(createAudioFormat, "createAudioFormat(\n     …TE_IN_HZ, 1\n            )");
                                    createAudioFormat.setInteger("aac-profile", 2);
                                    createAudioFormat.setInteger("bitrate", 65536);
                                    createEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
                                    Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(\"audio/mp4a-latm\")");
                                    createEncoderByType.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
                                    createEncoderByType.start();
                                    inputBuffers = createEncoderByType.getInputBuffers();
                                    Intrinsics.checkNotNullExpressionValue(inputBuffers, "codec.inputBuffers");
                                    outputBuffers = createEncoderByType.getOutputBuffers();
                                    Intrinsics.checkNotNullExpressionValue(outputBuffers, "codec.outputBuffers");
                                    charSequence = "co.hyperverge";
                                    bufferInfo = new MediaCodec.BufferInfo();
                                    str14 = "packageName";
                                    bArr = new byte[BUFFER_SIZE];
                                    d = 0.0d;
                                    i = 0;
                                    boolean z = true;
                                    i2 = 0;
                                    while (true) {
                                        String str23 = str18;
                                        String str24 = str19;
                                        str = str20;
                                        String str25 = str21;
                                        d2 = d;
                                        i3 = i;
                                        i4 = 0;
                                        while (i4 != -1 && z) {
                                            String str26 = str22;
                                            try {
                                                i4 = createEncoderByType.dequeueInputBuffer(500L);
                                                if (i4 < 0) {
                                                    ByteBuffer byteBuffer = inputBuffers[i4];
                                                    byteBuffer.clear();
                                                    ByteBuffer[] byteBufferArr2 = inputBuffers;
                                                    int read = fileInputStream.read(bArr, 0, byteBuffer.limit());
                                                    if (read == -1) {
                                                        createEncoderByType.queueInputBuffer(i4, 0, 0, (long) d2, 4);
                                                        str22 = str26;
                                                        inputBuffers = byteBufferArr2;
                                                        z = false;
                                                    } else {
                                                        i3 += read;
                                                        byteBuffer.put(bArr, 0, read);
                                                        createEncoderByType.queueInputBuffer(i4, 0, read, (long) d2, 0);
                                                        d2 = (1000000 * (i3 / 2.0d)) / SAMPLING_RATE_IN_HZ;
                                                        str22 = str26;
                                                        inputBuffers = byteBufferArr2;
                                                    }
                                                } else {
                                                    str22 = str26;
                                                }
                                            } catch (Throwable th7) {
                                                th = th7;
                                                str4 = str14;
                                                str7 = str23;
                                                str5 = str24;
                                                str8 = str25;
                                                str9 = str26;
                                                Result.Companion companion32222 = Result.INSTANCE;
                                                obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                                if (m1205exceptionOrNullimpl == null) {
                                                }
                                            }
                                        }
                                        String str27 = str22;
                                        ByteBuffer[] byteBufferArr3 = inputBuffers;
                                        int i9 = i2;
                                        i5 = 0;
                                        while (true) {
                                            i6 = -1;
                                            while (i5 != i6) {
                                                bArr2 = bArr;
                                                d3 = d2;
                                                try {
                                                    dequeueOutputBuffer = createEncoderByType.dequeueOutputBuffer(bufferInfo, 500L);
                                                    if (dequeueOutputBuffer >= 0) {
                                                        ByteBuffer byteBuffer2 = outputBuffers[dequeueOutputBuffer];
                                                        byteBuffer2.position(bufferInfo.offset);
                                                        byteBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                                                        if ((bufferInfo.flags & 2) != 0 && bufferInfo.size != 0) {
                                                            createEncoderByType.releaseOutputBuffer(dequeueOutputBuffer, false);
                                                        } else {
                                                            mediaMuxer.writeSampleData(i9, outputBuffers[dequeueOutputBuffer], bufferInfo);
                                                            createEncoderByType.releaseOutputBuffer(dequeueOutputBuffer, false);
                                                        }
                                                        fileInputStream3 = fileInputStream;
                                                        i7 = i9;
                                                        bArr3 = bArr2;
                                                        mediaCodec = createEncoderByType;
                                                        byteBufferArr = outputBuffers;
                                                        str4 = str14;
                                                        str7 = str23;
                                                        str5 = str24;
                                                        str8 = str25;
                                                        str9 = str27;
                                                        i8 = i3;
                                                        bufferInfo3 = bufferInfo;
                                                        mediaMuxer3 = mediaMuxer;
                                                    } else if (dequeueOutputBuffer == -2) {
                                                        MediaFormat outputFormat = createEncoderByType.getOutputFormat();
                                                        Intrinsics.checkNotNullExpressionValue(outputFormat, "codec.outputFormat");
                                                        i9 = mediaMuxer.addTrack(outputFormat);
                                                        mediaMuxer.start();
                                                        i5 = dequeueOutputBuffer;
                                                        bArr = bArr2;
                                                        d2 = d3;
                                                    } else if (dequeueOutputBuffer == -3) {
                                                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                                                        StringBuilder sb2 = new StringBuilder();
                                                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str6);
                                                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                                        if (stackTraceElement3 == null || (className8 = stackTraceElement3.getClassName()) == null) {
                                                            i7 = i9;
                                                            bArr3 = bArr2;
                                                            mediaCodec = createEncoderByType;
                                                        } else {
                                                            i7 = i9;
                                                            bArr3 = bArr2;
                                                            mediaCodec = createEncoderByType;
                                                            str17 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                        }
                                                        Class<?> cls2 = getClass();
                                                        String canonicalName4 = cls2 != null ? cls2.getCanonicalName() : null;
                                                        str17 = canonicalName4 == null ? str3 : canonicalName4;
                                                        Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str17);
                                                        if (matcher5.find()) {
                                                            str17 = matcher5.replaceAll("");
                                                            Intrinsics.checkNotNullExpressionValue(str17, "replaceAll(\"\")");
                                                        }
                                                        Unit unit3 = Unit.INSTANCE;
                                                        try {
                                                            try {
                                                                try {
                                                                    if (str17.length() > 23) {
                                                                        try {
                                                                            if (Build.VERSION.SDK_INT < 26) {
                                                                                str17 = str17.substring(0, 23);
                                                                                str9 = str27;
                                                                                try {
                                                                                    Intrinsics.checkNotNullExpressionValue(str17, str9);
                                                                                    sb2.append(str17);
                                                                                    str8 = str25;
                                                                                    sb2.append(str8);
                                                                                    sb2.append("Output buffers changed during encode!");
                                                                                    sb2.append(' ');
                                                                                    sb2.append("");
                                                                                    companion6.log(level2, sb2.toString());
                                                                                    CoreExtsKt.isRelease();
                                                                                    Result.Companion companion7 = Result.INSTANCE;
                                                                                    str5 = str24;
                                                                                    Object invoke2 = Class.forName(str).getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                                                                                    str7 = str23;
                                                                                    Intrinsics.checkNotNull(invoke2, str7);
                                                                                    m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                                                                        m1202constructorimpl4 = "";
                                                                                    }
                                                                                    String str28 = (String) m1202constructorimpl4;
                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                        str4 = str14;
                                                                                        try {
                                                                                            Intrinsics.checkNotNullExpressionValue(str28, str4);
                                                                                            fileInputStream3 = fileInputStream;
                                                                                            mediaMuxer3 = mediaMuxer;
                                                                                            byteBufferArr = outputBuffers;
                                                                                            if (StringsKt.contains$default((CharSequence) str28, charSequence, false, 2, (Object) null)) {
                                                                                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                                                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str6);
                                                                                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                                                                if (stackTraceElement4 == null || (className7 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                                    Class<?> cls3 = getClass();
                                                                                                    canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                                                                                                    if (canonicalName3 == null) {
                                                                                                        canonicalName3 = str3;
                                                                                                    }
                                                                                                }
                                                                                                Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                                                                                if (matcher6.find()) {
                                                                                                    canonicalName3 = matcher6.replaceAll("");
                                                                                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                                                                                }
                                                                                                Unit unit4 = Unit.INSTANCE;
                                                                                                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                    canonicalName3 = canonicalName3.substring(0, 23);
                                                                                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, str9);
                                                                                                }
                                                                                                Log.println(6, canonicalName3, "Output buffers changed during encode! ");
                                                                                            }
                                                                                        } catch (Throwable th8) {
                                                                                            th = th8;
                                                                                            Result.Companion companion322222 = Result.INSTANCE;
                                                                                            obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        fileInputStream3 = fileInputStream;
                                                                                        byteBufferArr = outputBuffers;
                                                                                        str4 = str14;
                                                                                        mediaMuxer3 = mediaMuxer;
                                                                                    }
                                                                                    i8 = i3;
                                                                                    bufferInfo3 = bufferInfo;
                                                                                } catch (Throwable th9) {
                                                                                    th = th9;
                                                                                    str4 = str14;
                                                                                    str7 = str23;
                                                                                    str5 = str24;
                                                                                    str8 = str25;
                                                                                    Result.Companion companion3222222 = Result.INSTANCE;
                                                                                    obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                    }
                                                                                }
                                                                            }
                                                                        } catch (Throwable th10) {
                                                                            th = th10;
                                                                            str9 = str27;
                                                                        }
                                                                    }
                                                                    String str282 = (String) m1202constructorimpl4;
                                                                    if (CoreExtsKt.isDebug()) {
                                                                    }
                                                                    i8 = i3;
                                                                    bufferInfo3 = bufferInfo;
                                                                } catch (Throwable th11) {
                                                                    th = th11;
                                                                    str4 = str14;
                                                                    Result.Companion companion32222222 = Result.INSTANCE;
                                                                    obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                    }
                                                                }
                                                                sb2.append(str8);
                                                                sb2.append("Output buffers changed during encode!");
                                                                sb2.append(' ');
                                                                sb2.append("");
                                                                companion6.log(level2, sb2.toString());
                                                                CoreExtsKt.isRelease();
                                                                Result.Companion companion72 = Result.INSTANCE;
                                                                str5 = str24;
                                                                Object invoke22 = Class.forName(str).getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                                                                str7 = str23;
                                                                Intrinsics.checkNotNull(invoke22, str7);
                                                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
                                                                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                                                }
                                                            } catch (Throwable th12) {
                                                                th = th12;
                                                                str4 = str14;
                                                                str7 = str23;
                                                                str5 = str24;
                                                            }
                                                            sb2.append(str17);
                                                            str8 = str25;
                                                        } catch (Throwable th13) {
                                                            th = th13;
                                                            str4 = str14;
                                                            str7 = str23;
                                                            str5 = str24;
                                                            str8 = str25;
                                                        }
                                                        str9 = str27;
                                                    } else {
                                                        fileInputStream3 = fileInputStream;
                                                        i7 = i9;
                                                        bArr3 = bArr2;
                                                        mediaCodec = createEncoderByType;
                                                        byteBufferArr = outputBuffers;
                                                        str4 = str14;
                                                        str7 = str23;
                                                        str5 = str24;
                                                        str8 = str25;
                                                        str9 = str27;
                                                        mediaMuxer3 = mediaMuxer;
                                                        if (dequeueOutputBuffer != -1) {
                                                            HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                                                            HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                                                            StringBuilder sb3 = new StringBuilder();
                                                            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                                            Intrinsics.checkNotNullExpressionValue(stackTrace5, str6);
                                                            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                                            if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null) {
                                                                i8 = i3;
                                                                bufferInfo3 = bufferInfo;
                                                            } else {
                                                                i8 = i3;
                                                                bufferInfo3 = bufferInfo;
                                                                str15 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                if (str15 != null) {
                                                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str15);
                                                                    if (matcher3.find()) {
                                                                        str15 = matcher3.replaceAll("");
                                                                        Intrinsics.checkNotNullExpressionValue(str15, "replaceAll(\"\")");
                                                                    }
                                                                    Unit unit5 = Unit.INSTANCE;
                                                                    if (str15.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                        str15 = str15.substring(0, 23);
                                                                        Intrinsics.checkNotNullExpressionValue(str15, str9);
                                                                    }
                                                                    sb3.append(str15);
                                                                    sb3.append(str8);
                                                                    str16 = "Unknown return code from dequeueOutputBuffer - " + dequeueOutputBuffer;
                                                                    if (str16 == null) {
                                                                        str16 = str2;
                                                                    }
                                                                    sb3.append(str16);
                                                                    sb3.append(' ');
                                                                    sb3.append("");
                                                                    companion8.log(level3, sb3.toString());
                                                                    CoreExtsKt.isRelease();
                                                                    Result.Companion companion9 = Result.INSTANCE;
                                                                    Object invoke3 = Class.forName(str).getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                                                                    Intrinsics.checkNotNull(invoke3, str7);
                                                                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                                                        m1202constructorimpl3 = "";
                                                                    }
                                                                    String str29 = (String) m1202constructorimpl3;
                                                                    if (!CoreExtsKt.isDebug()) {
                                                                        Intrinsics.checkNotNullExpressionValue(str29, str4);
                                                                        if (StringsKt.contains$default((CharSequence) str29, charSequence, false, 2, (Object) null)) {
                                                                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str6);
                                                                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                                            if (stackTraceElement6 == null || (className5 = stackTraceElement6.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                Class<?> cls4 = getClass();
                                                                                canonicalName2 = cls4 != null ? cls4.getCanonicalName() : null;
                                                                                if (canonicalName2 == null) {
                                                                                    canonicalName2 = str3;
                                                                                }
                                                                            }
                                                                            Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                                                            if (matcher7.find()) {
                                                                                canonicalName2 = matcher7.replaceAll("");
                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                                                            }
                                                                            Unit unit6 = Unit.INSTANCE;
                                                                            if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                canonicalName2 = canonicalName2.substring(0, 23);
                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName2, str9);
                                                                            }
                                                                            StringBuilder sb4 = new StringBuilder();
                                                                            String str30 = "Unknown return code from dequeueOutputBuffer - " + dequeueOutputBuffer;
                                                                            if (str30 == null) {
                                                                                str30 = str2;
                                                                            }
                                                                            sb4.append(str30);
                                                                            sb4.append(' ');
                                                                            sb4.append("");
                                                                            Log.println(6, canonicalName2, sb4.toString());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            Class<?> cls5 = getClass();
                                                            String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                                                            str15 = canonicalName5 == null ? str3 : canonicalName5;
                                                            matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str15);
                                                            if (matcher3.find()) {
                                                            }
                                                            Unit unit52 = Unit.INSTANCE;
                                                            if (str15.length() > 23) {
                                                                str15 = str15.substring(0, 23);
                                                                Intrinsics.checkNotNullExpressionValue(str15, str9);
                                                            }
                                                            sb3.append(str15);
                                                            sb3.append(str8);
                                                            str16 = "Unknown return code from dequeueOutputBuffer - " + dequeueOutputBuffer;
                                                            if (str16 == null) {
                                                            }
                                                            sb3.append(str16);
                                                            sb3.append(' ');
                                                            sb3.append("");
                                                            companion8.log(level3, sb3.toString());
                                                            CoreExtsKt.isRelease();
                                                            Result.Companion companion92 = Result.INSTANCE;
                                                            Object invoke32 = Class.forName(str).getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                                                            Intrinsics.checkNotNull(invoke32, str7);
                                                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke32).getPackageName());
                                                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                                            }
                                                            String str292 = (String) m1202constructorimpl3;
                                                            if (!CoreExtsKt.isDebug()) {
                                                            }
                                                        }
                                                        i8 = i3;
                                                        bufferInfo3 = bufferInfo;
                                                    }
                                                    i5 = dequeueOutputBuffer;
                                                    str27 = str9;
                                                    str25 = str8;
                                                    i9 = i7;
                                                    bArr = bArr3;
                                                    createEncoderByType = mediaCodec;
                                                    outputBuffers = byteBufferArr;
                                                    mediaMuxer = mediaMuxer3;
                                                    fileInputStream = fileInputStream3;
                                                    bufferInfo = bufferInfo3;
                                                    i3 = i8;
                                                    i6 = -1;
                                                    str23 = str7;
                                                    str14 = str4;
                                                    str24 = str5;
                                                    d2 = d3;
                                                } catch (Throwable th14) {
                                                    th = th14;
                                                    str4 = str14;
                                                    str7 = str23;
                                                    str5 = str24;
                                                    str8 = str25;
                                                    str9 = str27;
                                                }
                                            }
                                            break;
                                        }
                                        byte[] bArr4 = bArr;
                                        double d4 = d2;
                                        fileInputStream2 = fileInputStream;
                                        int i10 = i9;
                                        MediaCodec mediaCodec2 = createEncoderByType;
                                        ByteBuffer[] byteBufferArr4 = outputBuffers;
                                        str4 = str14;
                                        str7 = str23;
                                        str5 = str24;
                                        str8 = str25;
                                        str9 = str27;
                                        bufferInfo2 = bufferInfo;
                                        mediaMuxer2 = mediaMuxer;
                                        float f = i3;
                                        try {
                                            file3 = this.file;
                                            if (file3 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("file");
                                                file3 = null;
                                            }
                                            MathKt.roundToInt((f / ((float) file3.length())) * 100.0d);
                                            if (bufferInfo2.flags != 4) {
                                                break;
                                            }
                                            i = i3;
                                            bufferInfo = bufferInfo2;
                                            str18 = str7;
                                            str19 = str5;
                                            d = d4;
                                            bArr = bArr4;
                                            createEncoderByType = mediaCodec2;
                                            outputBuffers = byteBufferArr4;
                                            mediaMuxer = mediaMuxer2;
                                            fileInputStream = fileInputStream2;
                                            inputBuffers = byteBufferArr3;
                                            str14 = str4;
                                            i2 = i10;
                                            str20 = str;
                                            str22 = str9;
                                            str21 = str8;
                                        } catch (Throwable th15) {
                                            th = th15;
                                            Result.Companion companion322222222 = Result.INSTANCE;
                                            obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                            if (m1205exceptionOrNullimpl == null) {
                                            }
                                        }
                                    }
                                    fileInputStream2.close();
                                    mediaMuxer2.stop();
                                    mediaMuxer2.release();
                                    obj = Result.m1202constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th16) {
                                    th = th16;
                                    charSequence = "co.hyperverge";
                                    str5 = "getInitialApplication";
                                    str = "android.app.AppGlobals";
                                    str7 = "null cannot be cast to non-null type android.app.Application";
                                    str4 = "packageName";
                                    str8 = " - ";
                                    str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                                    Result.Companion companion3222222222 = Result.INSTANCE;
                                    obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                    if (m1205exceptionOrNullimpl == null) {
                                    }
                                }
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                if (m1205exceptionOrNullimpl == null) {
                                    HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                                    HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb5 = new StringBuilder();
                                    StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace7, str6);
                                    StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                                    if (stackTraceElement7 == null || (className2 = stackTraceElement7.getClassName()) == null || (str10 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls6 = getClass();
                                        String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : null;
                                        str10 = canonicalName6 == null ? str3 : canonicalName6;
                                    }
                                    Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                                    if (matcher8.find()) {
                                        str10 = matcher8.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
                                    }
                                    Unit unit7 = Unit.INSTANCE;
                                    if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str10 = str10.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str10, str9);
                                    }
                                    sb5.append(str10);
                                    sb5.append(str8);
                                    String str31 = "rawToMP4() " + m1205exceptionOrNullimpl.getMessage();
                                    if (str31 == null) {
                                        str31 = str2;
                                    }
                                    sb5.append(str31);
                                    sb5.append(' ');
                                    sb5.append("");
                                    companion10.log(level4, sb5.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion11 = Result.INSTANCE;
                                        Object invoke4 = Class.forName(str).getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke4, str7);
                                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                    } catch (Throwable th17) {
                                        Result.Companion companion12 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th17));
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                        m1202constructorimpl = "";
                                    }
                                    String str32 = (String) m1202constructorimpl;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(str32, str4);
                                        if (StringsKt.contains$default((CharSequence) str32, charSequence, false, 2, (Object) null)) {
                                            StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace8, str6);
                                            StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                            if (stackTraceElement8 == null || (className = stackTraceElement8.getClassName()) == null) {
                                                str11 = null;
                                            } else {
                                                str11 = null;
                                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                if (substringAfterLast$default2 != null) {
                                                    str12 = substringAfterLast$default2;
                                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                                                    if (matcher.find()) {
                                                        str12 = matcher.replaceAll("");
                                                        Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
                                                    }
                                                    Unit unit8 = Unit.INSTANCE;
                                                    if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                        str12 = str12.substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str12, str9);
                                                    }
                                                    StringBuilder sb6 = new StringBuilder();
                                                    String str33 = "rawToMP4() " + m1205exceptionOrNullimpl.getMessage();
                                                    sb6.append(str33 != null ? str2 : str33);
                                                    sb6.append(' ');
                                                    sb6.append("");
                                                    Log.println(6, str12, sb6.toString());
                                                    return;
                                                }
                                            }
                                            Class<?> cls7 = getClass();
                                            String canonicalName7 = cls7 != null ? cls7.getCanonicalName() : str11;
                                            str12 = canonicalName7 == null ? str3 : canonicalName7;
                                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                                            if (matcher.find()) {
                                            }
                                            Unit unit82 = Unit.INSTANCE;
                                            if (str12.length() > 23) {
                                                str12 = str12.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str12, str9);
                                            }
                                            StringBuilder sb62 = new StringBuilder();
                                            String str332 = "rawToMP4() " + m1205exceptionOrNullimpl.getMessage();
                                            sb62.append(str332 != null ? str2 : str332);
                                            sb62.append(' ');
                                            sb62.append("");
                                            Log.println(6, str12, sb62.toString());
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                        }
                    }
                    str6 = str13;
                    file = this.file;
                    if (file == null) {
                    }
                    fileInputStream = new FileInputStream(file);
                    file2 = this.converted;
                    if (file2 == null) {
                    }
                    mediaMuxer = new MediaMuxer(file2.getAbsolutePath(), 0);
                    MediaFormat createAudioFormat2 = MediaFormat.createAudioFormat("audio/mp4a-latm", SAMPLING_RATE_IN_HZ, 1);
                    Intrinsics.checkNotNullExpressionValue(createAudioFormat2, "createAudioFormat(\n     …TE_IN_HZ, 1\n            )");
                    createAudioFormat2.setInteger("aac-profile", 2);
                    createAudioFormat2.setInteger("bitrate", 65536);
                    createEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
                    Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(\"audio/mp4a-latm\")");
                    createEncoderByType.configure(createAudioFormat2, (Surface) null, (MediaCrypto) null, 1);
                    createEncoderByType.start();
                    inputBuffers = createEncoderByType.getInputBuffers();
                    Intrinsics.checkNotNullExpressionValue(inputBuffers, "codec.inputBuffers");
                    outputBuffers = createEncoderByType.getOutputBuffers();
                    Intrinsics.checkNotNullExpressionValue(outputBuffers, "codec.outputBuffers");
                    charSequence = "co.hyperverge";
                    bufferInfo = new MediaCodec.BufferInfo();
                    str14 = "packageName";
                    bArr = new byte[BUFFER_SIZE];
                    d = 0.0d;
                    i = 0;
                    boolean z2 = true;
                    i2 = 0;
                    while (true) {
                        String str232 = str18;
                        String str242 = str19;
                        str = str20;
                        String str252 = str21;
                        d2 = d;
                        i3 = i;
                        i4 = 0;
                        while (i4 != -1) {
                            String str262 = str22;
                            i4 = createEncoderByType.dequeueInputBuffer(500L);
                            if (i4 < 0) {
                            }
                        }
                        String str272 = str22;
                        ByteBuffer[] byteBufferArr32 = inputBuffers;
                        int i92 = i2;
                        i5 = 0;
                        while (true) {
                            i6 = -1;
                            while (i5 != i6) {
                            }
                            break;
                            i5 = dequeueOutputBuffer;
                            bArr = bArr2;
                            d2 = d3;
                        }
                        byte[] bArr42 = bArr;
                        double d42 = d2;
                        fileInputStream2 = fileInputStream;
                        int i102 = i92;
                        MediaCodec mediaCodec22 = createEncoderByType;
                        ByteBuffer[] byteBufferArr42 = outputBuffers;
                        str4 = str14;
                        str7 = str232;
                        str5 = str242;
                        str8 = str252;
                        str9 = str272;
                        bufferInfo2 = bufferInfo;
                        mediaMuxer2 = mediaMuxer;
                        float f2 = i3;
                        file3 = this.file;
                        if (file3 == null) {
                        }
                        MathKt.roundToInt((f2 / ((float) file3.length())) * 100.0d);
                        if (bufferInfo2.flags != 4) {
                        }
                        i = i3;
                        bufferInfo = bufferInfo2;
                        str18 = str7;
                        str19 = str5;
                        d = d42;
                        bArr = bArr42;
                        createEncoderByType = mediaCodec22;
                        outputBuffers = byteBufferArr42;
                        mediaMuxer = mediaMuxer2;
                        fileInputStream = fileInputStream2;
                        inputBuffers = byteBufferArr32;
                        str14 = str4;
                        i2 = i102;
                        str20 = str;
                        str22 = str9;
                        str21 = str8;
                    }
                    fileInputStream2.close();
                    mediaMuxer2.stop();
                    mediaMuxer2.release();
                    obj = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                Class<?> cls8 = getClass();
                String canonicalName8 = cls8 == null ? cls8.getCanonicalName() : null;
                substringAfterLast$default = canonicalName8 != null ? str3 : canonicalName8;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher2.find()) {
                }
                Unit unit9 = Unit.INSTANCE;
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("rawToMP4() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                str6 = str13;
                file = this.file;
                if (file == null) {
                }
                fileInputStream = new FileInputStream(file);
                file2 = this.converted;
                if (file2 == null) {
                }
                mediaMuxer = new MediaMuxer(file2.getAbsolutePath(), 0);
                MediaFormat createAudioFormat22 = MediaFormat.createAudioFormat("audio/mp4a-latm", SAMPLING_RATE_IN_HZ, 1);
                Intrinsics.checkNotNullExpressionValue(createAudioFormat22, "createAudioFormat(\n     …TE_IN_HZ, 1\n            )");
                createAudioFormat22.setInteger("aac-profile", 2);
                createAudioFormat22.setInteger("bitrate", 65536);
                createEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
                Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(\"audio/mp4a-latm\")");
                createEncoderByType.configure(createAudioFormat22, (Surface) null, (MediaCrypto) null, 1);
                createEncoderByType.start();
                inputBuffers = createEncoderByType.getInputBuffers();
                Intrinsics.checkNotNullExpressionValue(inputBuffers, "codec.inputBuffers");
                outputBuffers = createEncoderByType.getOutputBuffers();
                Intrinsics.checkNotNullExpressionValue(outputBuffers, "codec.outputBuffers");
                charSequence = "co.hyperverge";
                bufferInfo = new MediaCodec.BufferInfo();
                str14 = "packageName";
                bArr = new byte[BUFFER_SIZE];
                d = 0.0d;
                i = 0;
                boolean z22 = true;
                i2 = 0;
                while (true) {
                    String str2322 = str18;
                    String str2422 = str19;
                    str = str20;
                    String str2522 = str21;
                    d2 = d;
                    i3 = i;
                    i4 = 0;
                    while (i4 != -1) {
                    }
                    String str2722 = str22;
                    ByteBuffer[] byteBufferArr322 = inputBuffers;
                    int i922 = i2;
                    i5 = 0;
                    while (true) {
                        i6 = -1;
                        while (i5 != i6) {
                        }
                        break;
                        i5 = dequeueOutputBuffer;
                        bArr = bArr2;
                        d2 = d3;
                    }
                    byte[] bArr422 = bArr;
                    double d422 = d2;
                    fileInputStream2 = fileInputStream;
                    int i1022 = i922;
                    MediaCodec mediaCodec222 = createEncoderByType;
                    ByteBuffer[] byteBufferArr422 = outputBuffers;
                    str4 = str14;
                    str7 = str2322;
                    str5 = str2422;
                    str8 = str2522;
                    str9 = str2722;
                    bufferInfo2 = bufferInfo;
                    mediaMuxer2 = mediaMuxer;
                    float f22 = i3;
                    file3 = this.file;
                    if (file3 == null) {
                    }
                    MathKt.roundToInt((f22 / ((float) file3.length())) * 100.0d);
                    if (bufferInfo2.flags != 4) {
                    }
                    i = i3;
                    bufferInfo = bufferInfo2;
                    str18 = str7;
                    str19 = str5;
                    d = d422;
                    bArr = bArr422;
                    createEncoderByType = mediaCodec222;
                    outputBuffers = byteBufferArr422;
                    mediaMuxer = mediaMuxer2;
                    fileInputStream = fileInputStream2;
                    inputBuffers = byteBufferArr322;
                    str14 = str4;
                    i2 = i1022;
                    str20 = str;
                    str22 = str9;
                    str21 = str8;
                }
                fileInputStream2.close();
                mediaMuxer2.stop();
                mediaMuxer2.release();
                obj = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl == null) {
                }
            }
        }
        str3 = "N/A";
        str13 = "Throwable().stackTrace";
        Class<?> cls82 = getClass();
        if (cls82 == null) {
        }
        if (canonicalName8 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        Unit unit92 = Unit.INSTANCE;
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("rawToMP4() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        str6 = str13;
        file = this.file;
        if (file == null) {
        }
        fileInputStream = new FileInputStream(file);
        file2 = this.converted;
        if (file2 == null) {
        }
        mediaMuxer = new MediaMuxer(file2.getAbsolutePath(), 0);
        MediaFormat createAudioFormat222 = MediaFormat.createAudioFormat("audio/mp4a-latm", SAMPLING_RATE_IN_HZ, 1);
        Intrinsics.checkNotNullExpressionValue(createAudioFormat222, "createAudioFormat(\n     …TE_IN_HZ, 1\n            )");
        createAudioFormat222.setInteger("aac-profile", 2);
        createAudioFormat222.setInteger("bitrate", 65536);
        createEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
        Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(\"audio/mp4a-latm\")");
        createEncoderByType.configure(createAudioFormat222, (Surface) null, (MediaCrypto) null, 1);
        createEncoderByType.start();
        inputBuffers = createEncoderByType.getInputBuffers();
        Intrinsics.checkNotNullExpressionValue(inputBuffers, "codec.inputBuffers");
        outputBuffers = createEncoderByType.getOutputBuffers();
        Intrinsics.checkNotNullExpressionValue(outputBuffers, "codec.outputBuffers");
        charSequence = "co.hyperverge";
        bufferInfo = new MediaCodec.BufferInfo();
        str14 = "packageName";
        bArr = new byte[BUFFER_SIZE];
        d = 0.0d;
        i = 0;
        boolean z222 = true;
        i2 = 0;
        while (true) {
            String str23222 = str18;
            String str24222 = str19;
            str = str20;
            String str25222 = str21;
            d2 = d;
            i3 = i;
            i4 = 0;
            while (i4 != -1) {
            }
            String str27222 = str22;
            ByteBuffer[] byteBufferArr3222 = inputBuffers;
            int i9222 = i2;
            i5 = 0;
            while (true) {
                i6 = -1;
                while (i5 != i6) {
                }
                break;
                i5 = dequeueOutputBuffer;
                bArr = bArr2;
                d2 = d3;
            }
            byte[] bArr4222 = bArr;
            double d4222 = d2;
            fileInputStream2 = fileInputStream;
            int i10222 = i9222;
            MediaCodec mediaCodec2222 = createEncoderByType;
            ByteBuffer[] byteBufferArr4222 = outputBuffers;
            str4 = str14;
            str7 = str23222;
            str5 = str24222;
            str8 = str25222;
            str9 = str27222;
            bufferInfo2 = bufferInfo;
            mediaMuxer2 = mediaMuxer;
            float f222 = i3;
            file3 = this.file;
            if (file3 == null) {
            }
            MathKt.roundToInt((f222 / ((float) file3.length())) * 100.0d);
            if (bufferInfo2.flags != 4) {
            }
            i = i3;
            bufferInfo = bufferInfo2;
            str18 = str7;
            str19 = str5;
            d = d4222;
            bArr = bArr4222;
            createEncoderByType = mediaCodec2222;
            outputBuffers = byteBufferArr4222;
            mediaMuxer = mediaMuxer2;
            fileInputStream = fileInputStream2;
            inputBuffers = byteBufferArr3222;
            str14 = str4;
            i2 = i10222;
            str20 = str;
            str22 = str9;
            str21 = str8;
        }
        fileInputStream2.close();
        mediaMuxer2.stop();
        mediaMuxer2.release();
        obj = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
        if (m1205exceptionOrNullimpl == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AudioRecorder.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/AudioRecorder$RawToWavUtils;", "", "()V", "copy", "", "source", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "bufferSize", "", "writeToOutput", "", "data", "", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class RawToWavUtils {
        public static final RawToWavUtils INSTANCE = new RawToWavUtils();

        private RawToWavUtils() {
        }

        public final void writeToOutput(OutputStream output, String data) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(data, "data");
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
            String str2 = "writeToOutput() called with: output = " + output + ", data = " + data;
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
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "writeToOutput() called with: output = " + output + ", data = " + data;
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
            int length = data.length();
            for (int i = 0; i < length; i++) {
                output.write(data.charAt(i));
            }
        }

        public final void writeToOutput(OutputStream output, int data) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(output, "output");
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
            String str2 = "writeToOutput() called with: output = " + output + ", data = " + data;
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
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "writeToOutput() called with: output = " + output + ", data = " + data;
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
            output.write(data >> 0);
            output.write(data >> 8);
            output.write(data >> 16);
            output.write(data >> 24);
        }

        public final void writeToOutput(OutputStream output, short data) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(output, "output");
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
            String str2 = "writeToOutput() called with: output = " + output + ", data = " + ((int) data);
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
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "writeToOutput() called with: output = " + output + ", data = " + ((int) data);
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
            output.write(data >> 0);
            output.write(data >> 8);
        }

        public final long copy(InputStream source, OutputStream output) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(output, "output");
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
            String str2 = "copy() called with: source = " + source + ", output = " + output;
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
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "copy() called with: source = " + source + ", output = " + output;
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
            return copy(source, output, AudioRecorder.TRANSFER_BUFFER_SIZE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:41:0x0143, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0153, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0157, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0166, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0168, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0175, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x017b, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x017e, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0185, code lost:
        
            r0 = new java.lang.StringBuilder();
            r4 = "copy() called with: source = " + r18 + ", output = " + r19 + ", bufferSize = " + r20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x01a5, code lost:
        
            if (r4 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x01a7, code lost:
        
            r4 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x01a9, code lost:
        
            r0.append(r4);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0156, code lost:
        
            r8 = r0;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final long copy(InputStream source, OutputStream output, int bufferSize) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            String className2;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
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
            String str4 = "copy() called with: source = " + source + ", output = " + output + ", bufferSize = " + bufferSize;
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = null;
                        } else {
                            str = null;
                            str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = getClass();
                        str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    }
                }
            }
            long j = 0;
            byte[] bArr = new byte[bufferSize];
            while (true) {
                int read = source.read(bArr);
                if (read == -1) {
                    return j;
                }
                output.write(bArr, 0, read);
                j += read;
            }
        }
    }

    /* compiled from: AudioRecorder.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/AudioRecorder$Companion;", "", "()V", "AUDIO_FORMAT", "", "BUFFER_SIZE", "BUFFER_SIZE_FACTOR", "CHANNEL_CONFIG", "SAMPLING_RATE_IN_HZ", "TRANSFER_BUFFER_SIZE", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0158, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setFileName$hyperkyc_release(String name, File path, CoroutineScope lifecycleScope) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
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
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str5 = "setFileName() called with: name = " + name + ", path = " + path + ", lifecycleScope = " + lifecycleScope;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
                        str3 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                            str3 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str4 = "setFileName() called with: name = " + name + ", path = " + path + ", lifecycleScope = " + lifecycleScope;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                    }
                    str3 = str2;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str4 = "setFileName() called with: name = " + name + ", path = " + path + ", lifecycleScope = " + lifecycleScope;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        this.fileName = name;
        this.folderPath = path;
        this.lifecycleScope = lifecycleScope;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(25:1|(3:145|(1:147)(1:150)|(1:149))|7|(1:9)|10|(1:14)|15|(6:112|113|114|(1:116)|117|(18:119|(7:121|(3:136|(1:138)(1:141)|(1:140))|127|(1:129)|130|(1:134)|135)|18|19|20|(1:22)|23|24|(1:26)|27|(1:29)|30|(1:32)|33|(1:35)(1:109)|36|37|(18:39|(1:106)(1:43)|45|(1:47)(1:51)|(1:49)(1:50)|52|(1:54)|55|(1:59)|60|(1:62)|63|64|65|66|(1:68)|69|(2:71|(13:73|(1:100)(1:77)|79|(1:81)(1:85)|(1:83)(1:84)|86|(1:88)|89|(1:93)|94|(1:96)|97|98)(1:101))(1:102))(1:107)))|17|18|19|20|(0)|23|24|(0)|27|(0)|30|(0)|33|(0)(0)|36|37|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0215, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0216, code lost:
    
        r6 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0259, code lost:
    
        if (r8 != null) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0345, code lost:
    
        if (r0 != null) goto L140;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0183 A[Catch: all -> 0x0215, TRY_ENTER, TryCatch #1 {all -> 0x0215, blocks: (B:19:0x0176, B:22:0x0183, B:23:0x0187, B:26:0x0192, B:27:0x0196, B:29:0x01ad, B:30:0x01b1, B:32:0x01ba, B:33:0x01be, B:35:0x01f0, B:36:0x01fa), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0192 A[Catch: all -> 0x0215, TRY_ENTER, TryCatch #1 {all -> 0x0215, blocks: (B:19:0x0176, B:22:0x0183, B:23:0x0187, B:26:0x0192, B:27:0x0196, B:29:0x01ad, B:30:0x01b1, B:32:0x01ba, B:33:0x01be, B:35:0x01f0, B:36:0x01fa), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01ad A[Catch: all -> 0x0215, TryCatch #1 {all -> 0x0215, blocks: (B:19:0x0176, B:22:0x0183, B:23:0x0187, B:26:0x0192, B:27:0x0196, B:29:0x01ad, B:30:0x01b1, B:32:0x01ba, B:33:0x01be, B:35:0x01f0, B:36:0x01fa), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01ba A[Catch: all -> 0x0215, TryCatch #1 {all -> 0x0215, blocks: (B:19:0x0176, B:22:0x0183, B:23:0x0187, B:26:0x0192, B:27:0x0196, B:29:0x01ad, B:30:0x01b1, B:32:0x01ba, B:33:0x01be, B:35:0x01f0, B:36:0x01fa), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01f0 A[Catch: all -> 0x0215, TryCatch #1 {all -> 0x0215, blocks: (B:19:0x0176, B:22:0x0183, B:23:0x0187, B:26:0x0192, B:27:0x0196, B:29:0x01ad, B:30:0x01b1, B:32:0x01ba, B:33:0x01be, B:35:0x01f0, B:36:0x01fa), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0226  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startRecording$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String className2;
        String className3;
        File file;
        String str6;
        File file2;
        String str7;
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        Job launch$default;
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
        sb.append("startRecording() called");
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
                    Log.println(3, canonicalName2, "startRecording() called ");
                }
                Result.Companion companion4 = Result.INSTANCE;
                AudioRecorder audioRecorder = this;
                file = this.folderPath;
                if (file == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                    file = null;
                }
                StringBuilder sb2 = new StringBuilder();
                str6 = this.fileName;
                if (str6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fileName");
                    str6 = null;
                }
                sb2.append(str6);
                sb2.append("-raw.pcm");
                this.file = new File(file, sb2.toString());
                file2 = this.folderPath;
                if (file2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                    file2 = null;
                }
                StringBuilder sb3 = new StringBuilder();
                str7 = this.fileName;
                if (str7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fileName");
                    str7 = null;
                }
                sb3.append(str7);
                sb3.append("-audio.mp4");
                this.converted = new File(file2, sb3.toString());
                AudioRecord audioRecord = new AudioRecord(1, SAMPLING_RATE_IN_HZ, 16, 2, BUFFER_SIZE);
                this.recorder = audioRecord;
                audioRecord.startRecording();
                this.recordingInProgress.set(true);
                coroutineScope = this.lifecycleScope;
                if (coroutineScope != null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lifecycleScope");
                    coroutineScope2 = null;
                } else {
                    coroutineScope2 = coroutineScope;
                }
                launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AudioRecorder$startRecording$2$1(this, null), 3, null);
                Object m1202constructorimpl3 = Result.m1202constructorimpl(launch$default);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb4 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                        str2 = "Throwable().stackTrace";
                    } else {
                        str2 = "Throwable().stackTrace";
                        str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls3 = getClass();
                    String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                    str3 = canonicalName3 == null ? str : canonicalName3;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher3.find()) {
                        str3 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                    }
                    if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb4.append(str3);
                    sb4.append(" - ");
                    String str8 = "startRecording() " + m1205exceptionOrNullimpl.getMessage();
                    if (str8 == null) {
                        str8 = "null ";
                    }
                    sb4.append(str8);
                    sb4.append(' ');
                    sb4.append("");
                    companion5.log(level2, sb4.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion7 = Result.INSTANCE;
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
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str4 = null;
                            } else {
                                str4 = null;
                                str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                            str5 = canonicalName4 == null ? str : canonicalName4;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher4.find()) {
                                str5 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                            }
                            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb5 = new StringBuilder();
                            String str9 = "startRecording() " + m1205exceptionOrNullimpl.getMessage();
                            if (str9 == null) {
                                str9 = "null ";
                            }
                            sb5.append(str9);
                            sb5.append(' ');
                            sb5.append("");
                            Log.println(6, str5, sb5.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
        str = "N/A";
        Result.Companion companion42 = Result.INSTANCE;
        AudioRecorder audioRecorder2 = this;
        file = this.folderPath;
        if (file == null) {
        }
        StringBuilder sb22 = new StringBuilder();
        str6 = this.fileName;
        if (str6 == null) {
        }
        sb22.append(str6);
        sb22.append("-raw.pcm");
        this.file = new File(file, sb22.toString());
        file2 = this.folderPath;
        if (file2 == null) {
        }
        StringBuilder sb32 = new StringBuilder();
        str7 = this.fileName;
        if (str7 == null) {
        }
        sb32.append(str7);
        sb32.append("-audio.mp4");
        this.converted = new File(file2, sb32.toString());
        AudioRecord audioRecord2 = new AudioRecord(1, SAMPLING_RATE_IN_HZ, 16, 2, BUFFER_SIZE);
        this.recorder = audioRecord2;
        audioRecord2.startRecording();
        this.recordingInProgress.set(true);
        coroutineScope = this.lifecycleScope;
        if (coroutineScope != null) {
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AudioRecorder$startRecording$2$1(this, null), 3, null);
        Object m1202constructorimpl32 = Result.m1202constructorimpl(launch$default);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
        if (m1205exceptionOrNullimpl == null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void stopRecording$hyperkyc_release() {
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
        AudioRecord audioRecord = null;
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
        sb.append("stopRecording() called");
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
                    Log.println(3, str, "stopRecording() called ");
                }
            }
        }
        this.recordingInProgress.set(false);
        AudioRecord audioRecord2 = this.recorder;
        if (audioRecord2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recorder");
            audioRecord2 = null;
        }
        audioRecord2.stop();
        AudioRecord audioRecord3 = this.recorder;
        if (audioRecord3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recorder");
        } else {
            audioRecord = audioRecord3;
        }
        audioRecord.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(16:1|(3:267|(1:269)(1:272)|(1:271))|7|(1:9)|10|(1:14)|15|(12:(6:234|235|236|(1:238)|239|(19:241|(7:243|(3:258|(1:260)(1:263)|(1:262))|249|(1:251)|252|(1:256)|257)|18|19|20|(1:22)|23|24|26|27|(10:30|31|(2:33|34)|35|(25:37|38|39|40|(3:126|127|(3:129|130|131))|42|43|44|(1:46)(1:122)|(1:48)(1:121)|49|(1:51)|52|53|(1:58)|59|(1:61)|62|63|64|65|(1:67)|68|(2:70|(12:72|73|74|75|(3:100|(1:102)(1:105)|(1:104))(1:81)|82|(1:84)|85|(1:90)|91|(1:93)|94))|117)(1:144)|95|(1:97)|98|99|28)|148|149|150|151|152|153|154|(15:156|(3:217|(1:219)(1:223)|(1:221)(1:222))|162|(1:164)|165|(1:169)|170|(1:172)|173|174|175|176|(1:178)|179|(2:181|(13:183|(1:211)(2:187|(9:189|190|(1:192)|193|(1:197)|198|(1:200)(1:203)|201|202))|204|(1:206)(1:210)|(1:208)(1:209)|190|(0)|193|(2:195|197)|198|(0)(0)|201|202)(1:212))(1:213))(1:224)))|26|27|(1:28)|148|149|150|151|152|153|154|(0)(0))|17|18|19|20|(0)|23|24|(2:(1:111)|(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(27:1|(3:267|(1:269)(1:272)|(1:271))|7|(1:9)|10|(1:14)|15|(6:234|235|236|(1:238)|239|(19:241|(7:243|(3:258|(1:260)(1:263)|(1:262))|249|(1:251)|252|(1:256)|257)|18|19|20|(1:22)|23|24|26|27|(10:30|31|(2:33|34)|35|(25:37|38|39|40|(3:126|127|(3:129|130|131))|42|43|44|(1:46)(1:122)|(1:48)(1:121)|49|(1:51)|52|53|(1:58)|59|(1:61)|62|63|64|65|(1:67)|68|(2:70|(12:72|73|74|75|(3:100|(1:102)(1:105)|(1:104))(1:81)|82|(1:84)|85|(1:90)|91|(1:93)|94))|117)(1:144)|95|(1:97)|98|99|28)|148|149|150|151|152|153|154|(15:156|(3:217|(1:219)(1:223)|(1:221)(1:222))|162|(1:164)|165|(1:169)|170|(1:172)|173|174|175|176|(1:178)|179|(2:181|(13:183|(1:211)(2:187|(9:189|190|(1:192)|193|(1:197)|198|(1:200)(1:203)|201|202))|204|(1:206)(1:210)|(1:208)(1:209)|190|(0)|193|(2:195|197)|198|(0)(0)|201|202)(1:212))(1:213))(1:224)))|17|18|19|20|(0)|23|24|26|27|(1:28)|148|149|150|151|152|153|154|(0)(0)|(2:(1:111)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01fa, code lost:
    
        if (r10 != null) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x03bf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x03c5, code lost:
    
        r4 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x03c1, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x03c2, code lost:
    
        r23 = "co.hyperverge";
        r2 = "Throwable().stackTrace";
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:224:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0195 A[Catch: all -> 0x03c1, TryCatch #0 {all -> 0x03c1, blocks: (B:20:0x018a, B:22:0x0195, B:24:0x019b), top: B:19:0x018a }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v64 */
    /* JADX WARN: Type inference failed for: r2v65 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void performAudioWriting() {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String canonicalName2;
        String className;
        ?? allocateDirect;
        String str3;
        Throwable m1205exceptionOrNullimpl;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String str6;
        Matcher matcher;
        String className2;
        String className3;
        File file;
        FileOutputStream fileOutputStream;
        String str7;
        FileOutputStream fileOutputStream2;
        Throwable th;
        String str8;
        FileOutputStream fileOutputStream3;
        ByteBuffer byteBuffer;
        String str9;
        FileOutputStream fileOutputStream4;
        String str10;
        String str11;
        String substringAfterLast$default;
        Object m1202constructorimpl3;
        String canonicalName3;
        String className4;
        String className5;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String str12 = "Throwable().stackTrace";
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className5 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("performAudioWriting() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        String str13 = "co.hyperverge";
        try {
            try {
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        m1202constructorimpl = "";
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        str = "null ";
                        str2 = "N/A";
                        if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls2 = getClass();
                                canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                                if (canonicalName2 == null) {
                                    canonicalName2 = str2;
                                }
                            }
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                            if (matcher3.find()) {
                                canonicalName2 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                            }
                            Unit unit2 = Unit.INSTANCE;
                            if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName2 = canonicalName2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, canonicalName2, "performAudioWriting() called ");
                        }
                        allocateDirect = ByteBuffer.allocateDirect(BUFFER_SIZE);
                        Result.Companion companion4 = Result.INSTANCE;
                        AudioRecorder audioRecorder = this;
                        file = this.file;
                        if (file == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("file");
                            file = null;
                        }
                        fileOutputStream = new FileOutputStream(file, true);
                        FileOutputStream fileOutputStream5 = fileOutputStream;
                        ByteBuffer byteBuffer2 = allocateDirect;
                        while (this.recordingInProgress.get()) {
                            try {
                                AudioRecord audioRecord = this.recorder;
                                AudioRecord audioRecord2 = audioRecord;
                                if (audioRecord == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("recorder");
                                    audioRecord2 = null;
                                }
                                int read = audioRecord2.read(byteBuffer2, BUFFER_SIZE);
                                if (read < 0) {
                                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                                    fileOutputStream3 = fileOutputStream;
                                    try {
                                        StringBuilder sb2 = new StringBuilder();
                                        fileOutputStream4 = fileOutputStream5;
                                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str12);
                                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                        if (stackTraceElement3 != null) {
                                            try {
                                                String className6 = stackTraceElement3.getClassName();
                                                if (className6 != null) {
                                                    byteBuffer = byteBuffer2;
                                                    str3 = str13;
                                                    str11 = str12;
                                                    try {
                                                        substringAfterLast$default = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        fileOutputStream2 = fileOutputStream3;
                                                        allocateDirect = str11;
                                                        try {
                                                            throw th;
                                                        } catch (Throwable th4) {
                                                            CloseableKt.closeFinally(fileOutputStream2, th);
                                                            throw th4;
                                                        }
                                                    }
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                str3 = str13;
                                                str8 = str12;
                                                fileOutputStream2 = fileOutputStream3;
                                                str7 = str8;
                                                th = th;
                                                allocateDirect = str7;
                                                throw th;
                                            }
                                        }
                                        byteBuffer = byteBuffer2;
                                        str3 = str13;
                                        str11 = str12;
                                        try {
                                            Class<?> cls3 = getClass();
                                            String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                                            substringAfterLast$default = canonicalName4 == null ? str2 : canonicalName4;
                                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                                            if (matcher4.find()) {
                                                substringAfterLast$default = matcher4.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                                            }
                                            Unit unit3 = Unit.INSTANCE;
                                            if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            sb2.append(substringAfterLast$default);
                                            sb2.append(" - ");
                                            String bufferReadFailureReason = getBufferReadFailureReason(read);
                                            if (bufferReadFailureReason == null) {
                                                bufferReadFailureReason = str;
                                            }
                                            sb2.append(bufferReadFailureReason);
                                            sb2.append(' ');
                                            sb2.append("");
                                            companion5.log(level2, sb2.toString());
                                            CoreExtsKt.isRelease();
                                            try {
                                                Result.Companion companion6 = Result.INSTANCE;
                                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                            } catch (Throwable th6) {
                                                Result.Companion companion7 = Result.INSTANCE;
                                                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                                            }
                                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                                m1202constructorimpl3 = "";
                                            }
                                            String packageName2 = (String) m1202constructorimpl3;
                                            if (CoreExtsKt.isDebug()) {
                                                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) str3, false, 2, (Object) null)) {
                                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                    str10 = str11;
                                                    try {
                                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str10);
                                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                        if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                            Class<?> cls4 = getClass();
                                                            canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                                            if (canonicalName3 == null) {
                                                                canonicalName3 = str2;
                                                            }
                                                        }
                                                        Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                                        if (matcher5.find()) {
                                                            canonicalName3 = matcher5.replaceAll("");
                                                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                                        }
                                                        Unit unit4 = Unit.INSTANCE;
                                                        if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                            canonicalName3 = canonicalName3.substring(0, 23);
                                                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        }
                                                        StringBuilder sb3 = new StringBuilder();
                                                        String bufferReadFailureReason2 = getBufferReadFailureReason(read);
                                                        if (bufferReadFailureReason2 == null) {
                                                            bufferReadFailureReason2 = str;
                                                        }
                                                        sb3.append(bufferReadFailureReason2);
                                                        sb3.append(' ');
                                                        sb3.append("");
                                                        Log.println(6, canonicalName3, sb3.toString());
                                                        str9 = str10;
                                                    } catch (Throwable th7) {
                                                        th = th7;
                                                        th = th;
                                                        fileOutputStream2 = fileOutputStream3;
                                                        allocateDirect = str10;
                                                        throw th;
                                                    }
                                                }
                                            }
                                            str9 = str11;
                                        } catch (Throwable th8) {
                                            th = th8;
                                            str10 = str11;
                                            th = th;
                                            fileOutputStream2 = fileOutputStream3;
                                            allocateDirect = str10;
                                            throw th;
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        str3 = str13;
                                        str10 = str12;
                                    }
                                } else {
                                    byteBuffer = byteBuffer2;
                                    str3 = str13;
                                    str9 = str12;
                                    fileOutputStream3 = fileOutputStream;
                                    fileOutputStream4 = fileOutputStream5;
                                }
                                FileOutputStream fileOutputStream6 = this.partialFileOutputStream;
                                if (fileOutputStream6 != null) {
                                    fileOutputStream6.write(byteBuffer.array(), 0, BUFFER_SIZE);
                                    Unit unit5 = Unit.INSTANCE;
                                }
                                FileOutputStream fileOutputStream7 = fileOutputStream4;
                                fileOutputStream7.write(byteBuffer.array(), 0, BUFFER_SIZE);
                                byteBuffer.clear();
                                str12 = str9;
                                fileOutputStream5 = fileOutputStream7;
                                fileOutputStream = fileOutputStream3;
                                byteBuffer2 = byteBuffer;
                                str13 = str3;
                            } catch (Throwable th10) {
                                str3 = str13;
                                allocateDirect = str12;
                                th = th10;
                                fileOutputStream2 = fileOutputStream;
                            }
                        }
                        str3 = str13;
                        str8 = str12;
                        fileOutputStream3 = fileOutputStream;
                        Unit unit6 = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream3, null);
                        Object m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                        String str14 = str8;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                        if (m1205exceptionOrNullimpl != null) {
                            HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                            HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb4 = new StringBuilder();
                            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace5, str14);
                            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                            if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls5 = getClass();
                                String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                                str4 = canonicalName5 == null ? str2 : canonicalName5;
                            }
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
                            sb4.append(str4);
                            sb4.append(" - ");
                            String str15 = "performAudioWriting() " + m1205exceptionOrNullimpl.getMessage();
                            if (str15 == null) {
                                str15 = str;
                            }
                            sb4.append(str15);
                            sb4.append(' ');
                            sb4.append("");
                            companion8.log(level3, sb4.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion9 = Result.INSTANCE;
                                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                            } catch (Throwable th11) {
                                Result.Companion companion10 = Result.INSTANCE;
                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th11));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = "";
                            }
                            String packageName3 = (String) m1202constructorimpl2;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) str3, false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace6, str14);
                                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                    if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                                        str5 = null;
                                    } else {
                                        str5 = null;
                                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
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
                                            StringBuilder sb5 = new StringBuilder();
                                            String str16 = "performAudioWriting() " + m1205exceptionOrNullimpl.getMessage();
                                            sb5.append(str16 != null ? str : str16);
                                            sb5.append(' ');
                                            sb5.append("");
                                            Log.println(6, str6, sb5.toString());
                                            return;
                                        }
                                    }
                                    Class<?> cls6 = getClass();
                                    String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : str5;
                                    str6 = canonicalName6 == null ? str2 : canonicalName6;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                    if (matcher.find()) {
                                    }
                                    Unit unit82 = Unit.INSTANCE;
                                    if (str6.length() > 23) {
                                        str6 = str6.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb52 = new StringBuilder();
                                    String str162 = "performAudioWriting() " + m1205exceptionOrNullimpl.getMessage();
                                    sb52.append(str162 != null ? str : str162);
                                    sb52.append(' ');
                                    sb52.append("");
                                    Log.println(6, str6, sb52.toString());
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                }
                Unit unit62 = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream3, null);
                Object m1202constructorimpl42 = Result.m1202constructorimpl(Unit.INSTANCE);
                String str142 = str8;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl42);
                if (m1205exceptionOrNullimpl != null) {
                }
            } catch (Throwable th12) {
                th = th12;
                fileOutputStream2 = fileOutputStream3;
                str7 = str8;
                th = th;
                allocateDirect = str7;
                throw th;
            }
            FileOutputStream fileOutputStream52 = fileOutputStream;
            ByteBuffer byteBuffer22 = allocateDirect;
            while (this.recordingInProgress.get()) {
            }
            str3 = str13;
            str8 = str12;
            fileOutputStream3 = fileOutputStream;
        } catch (Throwable th13) {
            th = th13;
            str3 = str13;
            str7 = str12;
            fileOutputStream2 = fileOutputStream;
        }
        str = "null ";
        str2 = "N/A";
        allocateDirect = ByteBuffer.allocateDirect(BUFFER_SIZE);
        Result.Companion companion42 = Result.INSTANCE;
        AudioRecorder audioRecorder2 = this;
        file = this.file;
        if (file == null) {
        }
        fileOutputStream = new FileOutputStream(file, true);
    }

    public final void startPartialAudioRecording$hyperkyc_release(File file) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(file, "file");
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
        String str2 = "startPartialAudioRecording() called with: file = " + file;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "startPartialAudioRecording() called with: file = " + file;
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
        this.partialFileOutputStream = new FileOutputStream(file);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void stopPartialAudioRecording$hyperkyc_release() {
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
        sb.append("stopPartialAudioRecording() called");
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
                    Log.println(3, str, "stopPartialAudioRecording() called ");
                }
            }
        }
        FileOutputStream fileOutputStream = this.partialFileOutputStream;
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        this.partialFileOutputStream = null;
    }

    private final String getBufferReadFailureReason(int errorCode) {
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
        String str2 = "getBufferReadFailureReason() called with: errorCode = " + errorCode;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "getBufferReadFailureReason() called with: errorCode = " + errorCode;
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
        if (errorCode == -6) {
            return "Audio device or system resource unavailable or failed unexpectedly";
        }
        if (errorCode == -3) {
            return "The requested operation is not valid or allowed in the current state of the AudioRecord";
        }
        if (errorCode == -2) {
            return "One or more of the parameters provided to the AudioRecord method are invalid or out of range";
        }
        if (errorCode == -1) {
            return "AudioRecord failed unexpectedly";
        }
        return "Unknown (" + errorCode + ')';
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x014f, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void rawToWav$hyperkyc_release(File inputFile, File outputFile) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(inputFile, "inputFile");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
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
        String str2 = "rawToWav() called with: inputFile = " + inputFile + ", outputFile = " + outputFile;
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
                    String str3 = "rawToWav() called with: inputFile = " + inputFile + ", outputFile = " + outputFile;
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
        int length = (int) inputFile.length();
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        try {
            FileOutputStream fileOutputStream2 = fileOutputStream;
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, "RIFF");
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, length + 36);
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, "WAVE");
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, "fmt ");
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, 16);
            RawToWavUtils.INSTANCE.writeToOutput((OutputStream) fileOutputStream2, (short) 1);
            RawToWavUtils.INSTANCE.writeToOutput((OutputStream) fileOutputStream2, (short) 1);
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, SAMPLING_RATE_IN_HZ);
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, 88200);
            RawToWavUtils.INSTANCE.writeToOutput((OutputStream) fileOutputStream2, (short) 2);
            RawToWavUtils.INSTANCE.writeToOutput((OutputStream) fileOutputStream2, (short) 16);
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, "data");
            RawToWavUtils.INSTANCE.writeToOutput(fileOutputStream2, length);
            RawToWavUtils.INSTANCE.copy(new FileInputStream(inputFile), fileOutputStream2);
            CloseableKt.closeFinally(fileOutputStream, null);
        } finally {
        }
    }
}
