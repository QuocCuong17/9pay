package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HVAudioRecorder.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0016J\u001d\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001aR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lco/hyperverge/hyperkyc/hvsessionrecorder/HVAudioRecorder;", "", "()V", "audioRecorder", "Lco/hyperverge/hyperkyc/hvsessionrecorder/AudioRecorder;", "filename", "", "folderPath", "Ljava/io/File;", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "initializeAudioRecorder", "initializeAudioRecorder$hyperkyc_release", "pauseAudioRecord", "", "pauseAudioRecord$hyperkyc_release", "resumeAudioRecord", "resumeAudioRecord$hyperkyc_release", "startPartialAudioRecord", "file", "startPartialAudioRecord$hyperkyc_release", "stopAudioRecord", "stopAudioRecord$hyperkyc_release", "stopPartialAudioRecord", "rawFile", "encodedFile", "stopPartialAudioRecord$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HVAudioRecorder {
    private AudioRecorder audioRecorder;
    private String filename;
    private File folderPath;
    private CoroutineScope lifecycleScope;

    /* JADX WARN: Can't wrap try/catch for region: R(19:1|(5:2|3|4|5|6)|(3:159|160|(19:162|163|164|(13:166|14|(1:16)|17|(1:22)|23|(1:25)(8:107|108|109|(1:111)|112|(3:116|117|(11:119|120|121|122|(3:140|(1:142)(1:145)|(1:144))(1:128)|129|(1:131)|132|(1:137)|138|115))|114|115)|26|27|(1:29)(1:104)|30|31|(18:33|(1:101)(1:37)|39|(1:41)(1:45)|(1:43)(1:44)|46|(1:48)|49|(1:53)|54|(1:56)|57|58|59|60|(1:62)|63|(2:65|(13:67|(1:95)(2:71|(9:73|74|(1:76)|77|(1:81)|82|(1:84)|85|86))|88|(1:90)(1:94)|(1:92)(1:93)|74|(0)|77|(2:79|81)|82|(0)|85|86)(1:96))(1:97))(1:102))|9|(1:11)(1:158)|(1:13)(1:157)|14|(0)|17|(2:19|22)|23|(0)(0)|26|27|(0)(0)|30|31|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|(0)(0)|26|27|(0)(0)|30|31|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01b2, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0209, code lost:
    
        if (r15 != null) goto L112;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x006a A[Catch: all -> 0x01b4, TryCatch #1 {all -> 0x01b4, blocks: (B:164:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0096, B:22:0x009d, B:23:0x00a5, B:109:0x00f6, B:112:0x00fd, B:154:0x00ec, B:9:0x0064, B:11:0x006a, B:108:0x00c9), top: B:163:0x0052, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0087 A[Catch: all -> 0x01b4, TryCatch #1 {all -> 0x01b4, blocks: (B:164:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0096, B:22:0x009d, B:23:0x00a5, B:109:0x00f6, B:112:0x00fd, B:154:0x00ec, B:9:0x0064, B:11:0x006a, B:108:0x00c9), top: B:163:0x0052, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0096 A[Catch: all -> 0x01b4, TryCatch #1 {all -> 0x01b4, blocks: (B:164:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0096, B:22:0x009d, B:23:0x00a5, B:109:0x00f6, B:112:0x00fd, B:154:0x00ec, B:9:0x0064, B:11:0x006a, B:108:0x00c9), top: B:163:0x0052, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01a8 A[Catch: all -> 0x01b2, TryCatch #0 {all -> 0x01b2, blocks: (B:27:0x01a4, B:29:0x01a8, B:30:0x01ad), top: B:26:0x01a4 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0359  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void pauseAudioRecord$hyperkyc_release() {
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
        HVAudioRecorder hVAudioRecorder;
        AudioRecorder audioRecorder;
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVAudioRecorder hVAudioRecorder2 = this;
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
                if (m1205exceptionOrNullimpl != null) {
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
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                if (substringAfterLast$default != null) {
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                    if (matcher2.find()) {
                        substringAfterLast$default = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                    }
                    if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default);
                    sb.append(" - ");
                    sb.append("pauseAudioRecord() called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        hVAudioRecorder = this;
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
                                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls = getClass();
                                            canonicalName = cls != null ? cls.getCanonicalName() : null;
                                            if (canonicalName == null) {
                                                canonicalName = str;
                                            }
                                        }
                                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                        if (matcher3.find()) {
                                            canonicalName = matcher3.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                        }
                                        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName = canonicalName.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, canonicalName, "pauseAudioRecord() called ");
                                        hVAudioRecorder = this;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        Result.Companion companion322 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                    }
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                str2 = str7;
                            }
                        }
                        str2 = str7;
                        hVAudioRecorder = this;
                    }
                    audioRecorder = hVAudioRecorder.audioRecorder;
                    if (audioRecorder != null) {
                        audioRecorder.stopRecording$hyperkyc_release();
                    } else {
                        audioRecorder = null;
                    }
                    m1202constructorimpl = Result.m1202constructorimpl(audioRecorder);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str2);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                            str3 = str2;
                        } else {
                            str3 = str2;
                            str4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str4 = canonicalName2 == null ? str : canonicalName2;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(str4);
                        sb2.append(" - ");
                        String str8 = "pauseAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb2.append(str8);
                        sb2.append(' ');
                        sb2.append("");
                        companion6.log(level2, sb2.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th8) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
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
                                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str6 = str6.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb3 = new StringBuilder();
                                        String str9 = "pauseAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                        sb3.append(str9 != null ? str9 : "null ");
                                        sb3.append(' ');
                                        sb3.append("");
                                        Log.println(6, str6, sb3.toString());
                                        return;
                                    }
                                }
                                Class<?> cls3 = getClass();
                                String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : str5;
                                str6 = canonicalName3 == null ? str : canonicalName3;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                if (matcher.find()) {
                                }
                                if (str6.length() > 23) {
                                    str6 = str6.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb32 = new StringBuilder();
                                String str92 = "pauseAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                sb32.append(str92 != null ? str92 : "null ");
                                sb32.append(' ');
                                sb32.append("");
                                Log.println(6, str6, sb32.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                Class<?> cls4 = getClass();
                String canonicalName4 = cls4 == null ? cls4.getCanonicalName() : null;
                substringAfterLast$default = canonicalName4 != null ? str : canonicalName4;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher2.find()) {
                }
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("pauseAudioRecord() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                audioRecorder = hVAudioRecorder.audioRecorder;
                if (audioRecorder != null) {
                }
                m1202constructorimpl = Result.m1202constructorimpl(audioRecorder);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
        }
        charSequence = "co.hyperverge";
        str7 = "Throwable().stackTrace";
        Class<?> cls42 = getClass();
        if (cls42 == null) {
        }
        if (canonicalName4 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("pauseAudioRecord() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
        }
        audioRecorder = hVAudioRecorder.audioRecorder;
        if (audioRecorder != null) {
        }
        m1202constructorimpl = Result.m1202constructorimpl(audioRecorder);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(26:1|(5:2|3|4|5|6)|(3:164|165|(26:167|168|169|(20:171|14|(1:16)|17|(1:22)|23|(6:117|118|119|(1:121)|122|(3:124|125|(22:127|128|129|130|(3:147|(1:149)(1:152)|(1:151))(1:136)|137|(1:139)|140|(1:145)|146|26|27|28|29|(1:31)|32|(1:34)|35|(1:37)|38|39|(18:41|(1:109)(1:45)|47|(1:49)(1:53)|(1:51)(1:52)|54|(1:56)|57|(1:61)|62|(1:64)|65|66|67|68|(1:70)|71|(2:73|(13:75|(1:103)(2:79|(9:81|82|(1:84)|85|(1:89)|90|(1:92)|93|94))|96|(1:98)(1:102)|(1:100)(1:101)|82|(0)|85|(2:87|89)|90|(0)|93|94)(1:104))(1:105))(1:110))))|25|26|27|28|29|(0)|32|(0)|35|(0)|38|39|(0)(0))|9|(1:11)(1:163)|(1:13)(1:162)|14|(0)|17|(2:19|22)|23|(0)|25|26|27|28|29|(0)|32|(0)|35|(0)|38|39|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|(0)|25|26|27|28|29|(0)|32|(0)|35|(0)|38|39|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01ce, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0229, code lost:
    
        if (r15 != null) goto L115;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x006a A[Catch: all -> 0x01d4, TryCatch #1 {all -> 0x01d4, blocks: (B:169:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0096, B:22:0x009d, B:23:0x00a5, B:119:0x00f4, B:122:0x00fb, B:159:0x00ea, B:9:0x0064, B:11:0x006a, B:118:0x00c7), top: B:168:0x0052, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0087 A[Catch: all -> 0x01d4, TryCatch #1 {all -> 0x01d4, blocks: (B:169:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0096, B:22:0x009d, B:23:0x00a5, B:119:0x00f4, B:122:0x00fb, B:159:0x00ea, B:9:0x0064, B:11:0x006a, B:118:0x00c7), top: B:168:0x0052, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0096 A[Catch: all -> 0x01d4, TryCatch #1 {all -> 0x01d4, blocks: (B:169:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0096, B:22:0x009d, B:23:0x00a5, B:119:0x00f4, B:122:0x00fb, B:159:0x00ea, B:9:0x0064, B:11:0x006a, B:118:0x00c7), top: B:168:0x0052, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01a5 A[Catch: all -> 0x01ce, TryCatch #7 {all -> 0x01ce, blocks: (B:29:0x01a1, B:31:0x01a5, B:32:0x01ab, B:34:0x01af, B:35:0x01b5, B:37:0x01b9, B:38:0x01bf), top: B:28:0x01a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01af A[Catch: all -> 0x01ce, TryCatch #7 {all -> 0x01ce, blocks: (B:29:0x01a1, B:31:0x01a5, B:32:0x01ab, B:34:0x01af, B:35:0x01b5, B:37:0x01b9, B:38:0x01bf), top: B:28:0x01a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01b9 A[Catch: all -> 0x01ce, TryCatch #7 {all -> 0x01ce, blocks: (B:29:0x01a1, B:31:0x01a5, B:32:0x01ab, B:34:0x01af, B:35:0x01b5, B:37:0x01b9, B:38:0x01bf), top: B:28:0x01a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0379  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void resumeAudioRecord$hyperkyc_release() {
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
        String str8;
        File file;
        CoroutineScope coroutineScope;
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVAudioRecorder hVAudioRecorder = this;
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
                if (m1205exceptionOrNullimpl != null) {
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
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                if (substringAfterLast$default != null) {
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                    if (matcher2.find()) {
                        substringAfterLast$default = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                    }
                    if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default);
                    sb.append(" - ");
                    sb.append("resumeAudioRecord() called");
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
                            try {
                                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            } catch (Throwable th6) {
                                th = th6;
                                str2 = str7;
                                Result.Companion companion322 = Result.INSTANCE;
                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                if (m1205exceptionOrNullimpl != null) {
                                }
                            }
                            if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str2 = str7;
                                try {
                                    Intrinsics.checkNotNullExpressionValue(stackTrace2, str2);
                                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                    if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls = getClass();
                                        canonicalName = cls != null ? cls.getCanonicalName() : null;
                                        if (canonicalName == null) {
                                            canonicalName = str;
                                        }
                                    }
                                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                    if (matcher3.find()) {
                                        canonicalName = matcher3.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                    }
                                    if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        canonicalName = canonicalName.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(3, canonicalName, "resumeAudioRecord() called ");
                                    AudioRecorder audioRecorder = new AudioRecorder();
                                    str8 = this.filename;
                                    if (str8 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("filename");
                                        str8 = null;
                                    }
                                    file = this.folderPath;
                                    if (file == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("folderPath");
                                        file = null;
                                    }
                                    coroutineScope = this.lifecycleScope;
                                    if (coroutineScope == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("lifecycleScope");
                                        coroutineScope = null;
                                    }
                                    audioRecorder.setFileName$hyperkyc_release(str8, file, coroutineScope);
                                    audioRecorder.startRecording$hyperkyc_release();
                                    this.audioRecorder = audioRecorder;
                                    m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th7) {
                                    th = th7;
                                    Result.Companion companion3222 = Result.INSTANCE;
                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                }
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                if (m1205exceptionOrNullimpl != null) {
                                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb2 = new StringBuilder();
                                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str2);
                                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                    if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                                        str3 = str2;
                                    } else {
                                        str3 = str2;
                                        str4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    Class<?> cls2 = getClass();
                                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                                    str4 = canonicalName2 == null ? str : canonicalName2;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher4.find()) {
                                        str4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                    }
                                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb2.append(str4);
                                    sb2.append(" - ");
                                    String str9 = "resumeAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                    if (str9 == null) {
                                        str9 = "null ";
                                    }
                                    sb2.append(str9);
                                    sb2.append(' ');
                                    sb2.append("");
                                    companion6.log(level2, sb2.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion7 = Result.INSTANCE;
                                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                    } catch (Throwable th8) {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                        m1202constructorimpl2 = "";
                                    }
                                    String packageName2 = (String) m1202constructorimpl2;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                            if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
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
                                                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                        str6 = str6.substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    }
                                                    StringBuilder sb3 = new StringBuilder();
                                                    String str10 = "resumeAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                                    sb3.append(str10 != null ? str10 : "null ");
                                                    sb3.append(' ');
                                                    sb3.append("");
                                                    Log.println(6, str6, sb3.toString());
                                                    return;
                                                }
                                            }
                                            Class<?> cls3 = getClass();
                                            String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : str5;
                                            str6 = canonicalName3 == null ? str : canonicalName3;
                                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                            if (matcher.find()) {
                                            }
                                            if (str6.length() > 23) {
                                                str6 = str6.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb32 = new StringBuilder();
                                            String str102 = "resumeAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                            sb32.append(str102 != null ? str102 : "null ");
                                            sb32.append(' ');
                                            sb32.append("");
                                            Log.println(6, str6, sb32.toString());
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
                    str2 = str7;
                    AudioRecorder audioRecorder2 = new AudioRecorder();
                    str8 = this.filename;
                    if (str8 == null) {
                    }
                    file = this.folderPath;
                    if (file == null) {
                    }
                    coroutineScope = this.lifecycleScope;
                    if (coroutineScope == null) {
                    }
                    audioRecorder2.setFileName$hyperkyc_release(str8, file, coroutineScope);
                    audioRecorder2.startRecording$hyperkyc_release();
                    this.audioRecorder = audioRecorder2;
                    m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                Class<?> cls4 = getClass();
                String canonicalName4 = cls4 == null ? cls4.getCanonicalName() : null;
                substringAfterLast$default = canonicalName4 != null ? str : canonicalName4;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher2.find()) {
                }
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("resumeAudioRecord() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                str2 = str7;
                AudioRecorder audioRecorder22 = new AudioRecorder();
                str8 = this.filename;
                if (str8 == null) {
                }
                file = this.folderPath;
                if (file == null) {
                }
                coroutineScope = this.lifecycleScope;
                if (coroutineScope == null) {
                }
                audioRecorder22.setFileName$hyperkyc_release(str8, file, coroutineScope);
                audioRecorder22.startRecording$hyperkyc_release();
                this.audioRecorder = audioRecorder22;
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
        }
        charSequence = "co.hyperverge";
        str7 = "Throwable().stackTrace";
        Class<?> cls42 = getClass();
        if (cls42 == null) {
        }
        if (canonicalName4 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("resumeAudioRecord() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        str2 = str7;
        AudioRecorder audioRecorder222 = new AudioRecorder();
        str8 = this.filename;
        if (str8 == null) {
        }
        file = this.folderPath;
        if (file == null) {
        }
        coroutineScope = this.lifecycleScope;
        if (coroutineScope == null) {
        }
        audioRecorder222.setFileName$hyperkyc_release(str8, file, coroutineScope);
        audioRecorder222.startRecording$hyperkyc_release();
        this.audioRecorder = audioRecorder222;
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:1|(5:2|3|4|5|6)|(3:230|231|(21:233|234|235|(15:237|14|(1:16)|17|(1:22)|23|(1:25)(8:178|179|180|(1:182)|183|(3:187|188|(11:190|191|192|193|(3:211|(1:213)(1:216)|(1:215))(1:199)|200|(1:202)|203|(1:208)|209|186))|185|186)|26|27|(1:29)|30|31|(21:33|(1:173)(1:37)|39|(1:41)(1:45)|(1:43)(1:44)|46|(1:48)|49|(1:53)|54|(1:56)|57|58|59|60|(1:62)|63|(2:65|(9:67|(3:162|(1:164)(1:167)|(1:166))|73|(1:75)|76|(1:80)|81|(1:83)|84)(1:168))(1:169)|85|(1:87)|88)(1:174)|89|(18:91|(1:160)(1:95)|97|(1:99)(1:103)|(1:101)(1:102)|104|(1:106)|107|(1:111)|112|(1:114)|115|116|117|118|(1:120)|121|(2:123|(13:125|(1:154)(2:129|(9:131|132|(1:134)|135|(1:139)|140|(1:142)(1:146)|143|144))|147|(1:149)(1:153)|(1:151)(1:152)|132|(0)|135|(2:137|139)|140|(0)(0)|143|144)(1:155))(1:156))(1:161))|9|(1:11)(1:229)|(1:13)(1:228)|14|(0)|17|(2:19|22)|23|(0)(0)|26|27|(0)|30|31|(0)(0)|89|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|(0)(0)|26|27|(0)|30|31|(0)(0)|89|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x01c0, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0222, code lost:
    
        if (r4 != null) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x03e0, code lost:
    
        if (r14 != null) goto L185;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x006a A[Catch: all -> 0x01c2, TryCatch #2 {all -> 0x01c2, blocks: (B:235:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0098, B:22:0x009f, B:23:0x00a7, B:180:0x00f8, B:183:0x00ff, B:225:0x00ee, B:9:0x0064, B:11:0x006a, B:179:0x00cb), top: B:234:0x0052, inners: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:161:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0087 A[Catch: all -> 0x01c2, TryCatch #2 {all -> 0x01c2, blocks: (B:235:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0098, B:22:0x009f, B:23:0x00a7, B:180:0x00f8, B:183:0x00ff, B:225:0x00ee, B:9:0x0064, B:11:0x006a, B:179:0x00cb), top: B:234:0x0052, inners: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x00cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0098 A[Catch: all -> 0x01c2, TryCatch #2 {all -> 0x01c2, blocks: (B:235:0x0052, B:14:0x0076, B:16:0x0087, B:17:0x008e, B:19:0x0098, B:22:0x009f, B:23:0x00a7, B:180:0x00f8, B:183:0x00ff, B:225:0x00ee, B:9:0x0064, B:11:0x006a, B:179:0x00cb), top: B:234:0x0052, inners: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01ac A[Catch: all -> 0x01c0, TryCatch #1 {all -> 0x01c0, blocks: (B:27:0x01a8, B:29:0x01ac, B:30:0x01b6), top: B:26:0x01a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void stopAudioRecord$hyperkyc_release() {
        HVAudioRecorder hVAudioRecorder;
        CharSequence charSequence;
        String str;
        String str2;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        Object obj;
        String str3;
        String str4;
        String str5;
        Throwable m1205exceptionOrNullimpl2;
        String str6;
        String str7;
        String str8;
        Object m1202constructorimpl2;
        String str9;
        String str10;
        Matcher matcher;
        String className;
        String className2;
        String str11;
        String str12;
        String str13;
        Object m1202constructorimpl3;
        String canonicalName;
        String className3;
        String className4;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement;
        String className5;
        String str14;
        String substringAfterLast$default;
        Matcher matcher2;
        Object m1202constructorimpl4;
        String canonicalName2;
        String className6;
        AudioRecorder audioRecorder;
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVAudioRecorder hVAudioRecorder2 = this;
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
                hVAudioRecorder = this;
                charSequence = "co.hyperverge";
            }
        } catch (Throwable th2) {
            th = th2;
            hVAudioRecorder = this;
            charSequence = "co.hyperverge";
            str = "N/A";
        }
        if (stackTraceElement != null) {
            try {
                className5 = stackTraceElement.getClassName();
            } catch (Throwable th3) {
                th = th3;
                charSequence = "co.hyperverge";
                hVAudioRecorder = this;
                str2 = "Throwable().stackTrace";
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                Object obj2 = m1202constructorimpl;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
                if (m1205exceptionOrNullimpl != null) {
                }
                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl2 != null) {
                }
            }
            if (className5 != null) {
                charSequence = "co.hyperverge";
                str14 = "Throwable().stackTrace";
                try {
                    substringAfterLast$default = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                } catch (Throwable th4) {
                    th = th4;
                    hVAudioRecorder = this;
                    str2 = str14;
                    Result.Companion companion32 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj22 = m1202constructorimpl;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(obj);
                    if (m1205exceptionOrNullimpl2 != null) {
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
                    sb.append("stopAudioRecord() called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        hVAudioRecorder = this;
                        str2 = str14;
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th5) {
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                            m1202constructorimpl4 = "";
                        }
                        String packageName = (String) m1202constructorimpl4;
                        if (CoreExtsKt.isDebug()) {
                            try {
                                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                    str2 = str14;
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str2);
                                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        if (stackTraceElement2 == null || (className6 = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls = getClass();
                                            canonicalName2 = cls != null ? cls.getCanonicalName() : null;
                                            if (canonicalName2 == null) {
                                                canonicalName2 = str;
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
                                        Log.println(3, canonicalName2, "stopAudioRecord() called ");
                                        hVAudioRecorder = this;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        hVAudioRecorder = this;
                                        Result.Companion companion322 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        Object obj222 = m1202constructorimpl;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(obj);
                                        if (m1205exceptionOrNullimpl2 != null) {
                                        }
                                    }
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                str2 = str14;
                            }
                        }
                        str2 = str14;
                        hVAudioRecorder = this;
                    }
                    audioRecorder = hVAudioRecorder.audioRecorder;
                    if (audioRecorder != null) {
                        audioRecorder.stopRecording$hyperkyc_release();
                        audioRecorder.rawToMP4$hyperkyc_release();
                        Unit unit3 = Unit.INSTANCE;
                        Unit unit4 = Unit.INSTANCE;
                    }
                    hVAudioRecorder.audioRecorder = null;
                    m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                    Object obj2222 = m1202constructorimpl;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2222);
                    if (m1205exceptionOrNullimpl != null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                        str3 = "null ";
                        StringBuilder sb2 = new StringBuilder();
                        obj = obj2222;
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str2);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null) {
                            str11 = str2;
                            str12 = "packageName";
                        } else {
                            str11 = str2;
                            str12 = "packageName";
                            str13 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = getClass();
                        String canonicalName3 = cls2 != null ? cls2.getCanonicalName() : null;
                        str13 = canonicalName3 == null ? str : canonicalName3;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str13);
                        if (matcher4.find()) {
                            str13 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str13, "replaceAll(\"\")");
                        }
                        Unit unit5 = Unit.INSTANCE;
                        if (str13.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str13 = str13.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(str13);
                        sb2.append(" - ");
                        String str15 = "stopAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                        if (str15 == null) {
                            str15 = str3;
                        }
                        sb2.append(str15);
                        sb2.append(' ');
                        sb2.append("");
                        companion6.log(level2, sb2.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th8) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String str16 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            str5 = str12;
                            Intrinsics.checkNotNullExpressionValue(str16, str5);
                            if (StringsKt.contains$default((CharSequence) str16, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                str4 = str11;
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str4);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls3 = getClass();
                                    canonicalName = cls3 != null ? cls3.getCanonicalName() : null;
                                    if (canonicalName == null) {
                                        canonicalName = str;
                                    }
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                if (matcher5.find()) {
                                    canonicalName = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName = canonicalName.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb3 = new StringBuilder();
                                String str17 = "stopAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                if (str17 == null) {
                                    str17 = str3;
                                }
                                sb3.append(str17);
                                sb3.append(' ');
                                sb3.append("");
                                Log.println(6, canonicalName, sb3.toString());
                            } else {
                                str4 = str11;
                            }
                        } else {
                            str4 = str11;
                            str5 = str12;
                        }
                        AudioRecorder audioRecorder2 = this.audioRecorder;
                        if (audioRecorder2 != null) {
                            audioRecorder2.rawToMP4$hyperkyc_release();
                            Unit unit7 = Unit.INSTANCE;
                        }
                        this.audioRecorder = null;
                    } else {
                        obj = obj2222;
                        str3 = "null ";
                        str4 = str2;
                        str5 = "packageName";
                    }
                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(obj);
                    if (m1205exceptionOrNullimpl2 != null) {
                        HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                        HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb4 = new StringBuilder();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, str4);
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 == null || (className2 = stackTraceElement5.getClassName()) == null) {
                            str6 = str5;
                            str7 = str4;
                        } else {
                            str6 = str5;
                            str7 = str4;
                            str8 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls4 = getClass();
                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                        str8 = canonicalName4 == null ? str : canonicalName4;
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str8);
                        if (matcher6.find()) {
                            str8 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str8, "replaceAll(\"\")");
                        }
                        Unit unit8 = Unit.INSTANCE;
                        if (str8.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str8 = str8.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb4.append(str8);
                        sb4.append(" - ");
                        String str18 = "stopAudioRecord() " + m1205exceptionOrNullimpl2.getMessage();
                        if (str18 == null) {
                            str18 = str3;
                        }
                        sb4.append(str18);
                        sb4.append(' ');
                        sb4.append("");
                        companion9.log(level3, sb4.toString());
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
                        String str19 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str19, str6);
                            if (StringsKt.contains$default((CharSequence) str19, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str7);
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className = stackTraceElement6.getClassName()) == null) {
                                    str9 = null;
                                } else {
                                    str9 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        str10 = substringAfterLast$default2;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                                        if (matcher.find()) {
                                            str10 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
                                        }
                                        Unit unit9 = Unit.INSTANCE;
                                        if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str10 = str10.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb5 = new StringBuilder();
                                        String str20 = "stopAudioRecord() " + m1205exceptionOrNullimpl2.getMessage();
                                        sb5.append(str20 != null ? str3 : str20);
                                        sb5.append(' ');
                                        sb5.append("");
                                        Log.println(6, str10, sb5.toString());
                                        return;
                                    }
                                }
                                Class<?> cls5 = getClass();
                                String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : str9;
                                str10 = canonicalName5 == null ? str : canonicalName5;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                                if (matcher.find()) {
                                }
                                Unit unit92 = Unit.INSTANCE;
                                if (str10.length() > 23) {
                                    str10 = str10.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb52 = new StringBuilder();
                                String str202 = "stopAudioRecord() " + m1205exceptionOrNullimpl2.getMessage();
                                sb52.append(str202 != null ? str3 : str202);
                                sb52.append(' ');
                                sb52.append("");
                                Log.println(6, str10, sb52.toString());
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
                Unit unit10 = Unit.INSTANCE;
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("stopAudioRecord() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                audioRecorder = hVAudioRecorder.audioRecorder;
                if (audioRecorder != null) {
                }
                hVAudioRecorder.audioRecorder = null;
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                Object obj22222 = m1202constructorimpl;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22222);
                if (m1205exceptionOrNullimpl != null) {
                }
                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl2 != null) {
                }
            }
        }
        charSequence = "co.hyperverge";
        str14 = "Throwable().stackTrace";
        Class<?> cls62 = getClass();
        if (cls62 == null) {
        }
        if (canonicalName6 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        Unit unit102 = Unit.INSTANCE;
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("stopAudioRecord() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
        }
        audioRecorder = hVAudioRecorder.audioRecorder;
        if (audioRecorder != null) {
        }
        hVAudioRecorder.audioRecorder = null;
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        Object obj222222 = m1202constructorimpl;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222222);
        if (m1205exceptionOrNullimpl != null) {
        }
        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(obj);
        if (m1205exceptionOrNullimpl2 != null) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:1|2|3|4|5|6|(3:159|160|(19:162|163|164|(13:166|14|(1:16)|17|(1:22)|23|(1:25)(8:107|108|109|(1:111)|112|(3:116|117|(11:119|120|121|122|(3:140|(1:142)(1:145)|(1:144))(1:128)|129|(1:131)|132|(1:137)|138|115))|114|115)|26|27|(1:29)(1:104)|30|31|(18:33|(1:101)(1:37)|39|(1:41)(1:45)|(1:43)(1:44)|46|(1:48)|49|(1:53)|54|(1:56)|57|58|59|60|(1:62)|63|(2:65|(13:67|(1:95)(2:71|(9:73|74|(1:76)|77|(1:81)|82|(1:84)|85|86))|88|(1:90)(1:94)|(1:92)(1:93)|74|(0)|77|(2:79|81)|82|(0)|85|86)(1:96))(1:97))(1:102))|9|(1:11)(1:158)|(1:13)(1:157)|14|(0)|17|(2:19|22)|23|(0)(0)|26|27|(0)(0)|30|31|(0)(0)))|8|9|(0)(0)|(0)(0)|14|(0)|17|(0)|23|(0)(0)|26|27|(0)(0)|30|31|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01bd, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0214, code lost:
    
        if (r15 != null) goto L112;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0071 A[Catch: all -> 0x01bf, TryCatch #8 {all -> 0x01bf, blocks: (B:164:0x0059, B:14:0x007d, B:16:0x008e, B:17:0x0095, B:19:0x009d, B:22:0x00a4, B:23:0x00ac, B:109:0x00fd, B:112:0x0104, B:154:0x00f3, B:9:0x006b, B:11:0x0071, B:108:0x00d0), top: B:163:0x0059, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008e A[Catch: all -> 0x01bf, TryCatch #8 {all -> 0x01bf, blocks: (B:164:0x0059, B:14:0x007d, B:16:0x008e, B:17:0x0095, B:19:0x009d, B:22:0x00a4, B:23:0x00ac, B:109:0x00fd, B:112:0x0104, B:154:0x00f3, B:9:0x006b, B:11:0x0071, B:108:0x00d0), top: B:163:0x0059, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009d A[Catch: all -> 0x01bf, TryCatch #8 {all -> 0x01bf, blocks: (B:164:0x0059, B:14:0x007d, B:16:0x008e, B:17:0x0095, B:19:0x009d, B:22:0x00a4, B:23:0x00ac, B:109:0x00fd, B:112:0x0104, B:154:0x00f3, B:9:0x006b, B:11:0x0071, B:108:0x00d0), top: B:163:0x0059, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01af A[Catch: all -> 0x01bd, TryCatch #6 {all -> 0x01bd, blocks: (B:27:0x01ab, B:29:0x01af, B:30:0x01b8), top: B:26:0x01ab }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0364  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startPartialAudioRecord$hyperkyc_release(File file) {
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
        HVAudioRecorder hVAudioRecorder;
        AudioRecorder audioRecorder;
        Unit unit;
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVAudioRecorder hVAudioRecorder2 = this;
            level = HyperLogger.Level.DEBUG;
            companion = HyperLogger.INSTANCE.getInstance();
            str = "N/A";
            try {
                sb = new StringBuilder();
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
                if (m1205exceptionOrNullimpl != null) {
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
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                if (substringAfterLast$default != null) {
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                    if (matcher2.find()) {
                        substringAfterLast$default = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                    }
                    if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default);
                    sb.append(" - ");
                    sb.append("startPartialAudioRecord() called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        hVAudioRecorder = this;
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
                                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls = getClass();
                                            canonicalName = cls != null ? cls.getCanonicalName() : null;
                                            if (canonicalName == null) {
                                                canonicalName = str;
                                            }
                                        }
                                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                        if (matcher3.find()) {
                                            canonicalName = matcher3.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                        }
                                        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName = canonicalName.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, canonicalName, "startPartialAudioRecord() called ");
                                        hVAudioRecorder = this;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        Result.Companion companion322 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                    }
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                str2 = str7;
                            }
                        }
                        str2 = str7;
                        hVAudioRecorder = this;
                    }
                    audioRecorder = hVAudioRecorder.audioRecorder;
                    if (audioRecorder != null) {
                        audioRecorder.startPartialAudioRecording$hyperkyc_release(file);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    m1202constructorimpl = Result.m1202constructorimpl(unit);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str2);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                            str3 = str2;
                        } else {
                            str3 = str2;
                            str4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str4 = canonicalName2 == null ? str : canonicalName2;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(str4);
                        sb2.append(" - ");
                        String str8 = "startPartialAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb2.append(str8);
                        sb2.append(' ');
                        sb2.append("");
                        companion6.log(level2, sb2.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th8) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
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
                                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str6 = str6.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb3 = new StringBuilder();
                                        String str9 = "startPartialAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                        sb3.append(str9 != null ? str9 : "null ");
                                        sb3.append(' ');
                                        sb3.append("");
                                        Log.println(6, str6, sb3.toString());
                                        return;
                                    }
                                }
                                Class<?> cls3 = getClass();
                                String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : str5;
                                str6 = canonicalName3 == null ? str : canonicalName3;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                if (matcher.find()) {
                                }
                                if (str6.length() > 23) {
                                    str6 = str6.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb32 = new StringBuilder();
                                String str92 = "startPartialAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                sb32.append(str92 != null ? str92 : "null ");
                                sb32.append(' ');
                                sb32.append("");
                                Log.println(6, str6, sb32.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                Class<?> cls4 = getClass();
                String canonicalName4 = cls4 == null ? cls4.getCanonicalName() : null;
                substringAfterLast$default = canonicalName4 != null ? str : canonicalName4;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher2.find()) {
                }
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("startPartialAudioRecord() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                audioRecorder = hVAudioRecorder.audioRecorder;
                if (audioRecorder != null) {
                }
                m1202constructorimpl = Result.m1202constructorimpl(unit);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
        }
        charSequence = "co.hyperverge";
        str7 = "Throwable().stackTrace";
        Class<?> cls42 = getClass();
        if (cls42 == null) {
        }
        if (canonicalName4 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("startPartialAudioRecord() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
        }
        audioRecorder = hVAudioRecorder.audioRecorder;
        if (audioRecorder != null) {
        }
        m1202constructorimpl = Result.m1202constructorimpl(unit);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:1|2|3|(3:158|159|(21:161|162|163|(15:165|11|(1:13)|14|(1:19)|20|(1:22)(8:106|107|108|(1:110)|111|(3:115|116|(11:118|119|120|121|(3:139|(1:141)(1:144)|(1:143))(1:127)|128|(1:130)|131|(1:136)|137|114))|113|114)|23|24|(1:26)|27|(1:29)(1:103)|30|31|(18:33|(1:100)(1:37)|39|(1:41)(1:45)|(1:43)(1:44)|46|(1:48)|49|(1:53)|54|(1:56)|57|58|59|60|(1:62)|63|(2:65|(13:67|(1:94)(2:71|(9:73|74|(1:76)|77|(1:81)|82|(1:84)|85|86))|88|(1:90)(1:93)|(1:92)|74|(0)|77|(2:79|81)|82|(0)|85|86)(1:95))(1:96))(1:101))|6|(1:8)(1:157)|(1:10)(1:156)|11|(0)|14|(2:16|19)|20|(0)(0)|23|24|(0)|27|(0)(0)|30|31|(0)(0)))|5|6|(0)(0)|(0)(0)|11|(0)|14|(0)|20|(0)(0)|23|24|(0)|27|(0)(0)|30|31|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01cd, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x021d, code lost:
    
        if (r14 != null) goto L111;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x00d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0095 A[Catch: all -> 0x01cf, TryCatch #7 {all -> 0x01cf, blocks: (B:163:0x0060, B:11:0x0084, B:13:0x0095, B:14:0x009c, B:16:0x00a4, B:19:0x00ab, B:20:0x00b3, B:108:0x0104, B:111:0x010b, B:153:0x00fa, B:6:0x0072, B:8:0x0078, B:107:0x00d7), top: B:162:0x0060, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a4 A[Catch: all -> 0x01cf, TryCatch #7 {all -> 0x01cf, blocks: (B:163:0x0060, B:11:0x0084, B:13:0x0095, B:14:0x009c, B:16:0x00a4, B:19:0x00ab, B:20:0x00b3, B:108:0x0104, B:111:0x010b, B:153:0x00fa, B:6:0x0072, B:8:0x0078, B:107:0x00d7), top: B:162:0x0060, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01b6 A[Catch: all -> 0x01cd, TryCatch #6 {all -> 0x01cd, blocks: (B:24:0x01b2, B:26:0x01b6, B:27:0x01b9, B:29:0x01bd, B:30:0x01c8), top: B:23:0x01b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01bd A[Catch: all -> 0x01cd, TryCatch #6 {all -> 0x01cd, blocks: (B:24:0x01b2, B:26:0x01b6, B:27:0x01b9, B:29:0x01bd, B:30:0x01c8), top: B:23:0x01b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0078 A[Catch: all -> 0x01cf, TryCatch #7 {all -> 0x01cf, blocks: (B:163:0x0060, B:11:0x0084, B:13:0x0095, B:14:0x009c, B:16:0x00a4, B:19:0x00ab, B:20:0x00b3, B:108:0x0104, B:111:0x010b, B:153:0x00fa, B:6:0x0072, B:8:0x0078, B:107:0x00d7), top: B:162:0x0060, inners: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void stopPartialAudioRecord$hyperkyc_release(File rawFile, File encodedFile) {
        CharSequence charSequence;
        String str;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String canonicalName;
        Matcher matcher;
        String className;
        String className2;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement;
        String className3;
        String str5;
        String substringAfterLast$default;
        Matcher matcher2;
        Object m1202constructorimpl3;
        String canonicalName2;
        String className4;
        HVAudioRecorder hVAudioRecorder;
        AudioRecorder audioRecorder;
        AudioRecorder audioRecorder2;
        Unit unit;
        Intrinsics.checkNotNullParameter(rawFile, "rawFile");
        Intrinsics.checkNotNullParameter(encodedFile, "encodedFile");
        try {
            Result.Companion companion2 = Result.INSTANCE;
            HVAudioRecorder hVAudioRecorder2 = this;
            level = HyperLogger.Level.DEBUG;
            companion = HyperLogger.INSTANCE.getInstance();
            sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        } catch (Throwable th) {
            th = th;
            charSequence = "co.hyperverge";
        }
        if (stackTraceElement != null) {
            try {
                className3 = stackTraceElement.getClassName();
            } catch (Throwable th2) {
                th = th2;
                charSequence = "co.hyperverge";
                str = "Throwable().stackTrace";
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
            if (className3 != null) {
                charSequence = "co.hyperverge";
                str5 = "Throwable().stackTrace";
                try {
                    substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                } catch (Throwable th3) {
                    th = th3;
                    str = str5;
                    Result.Companion companion32 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                if (substringAfterLast$default != null) {
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                    if (matcher2.find()) {
                        substringAfterLast$default = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                    }
                    if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(substringAfterLast$default);
                    sb.append(" - ");
                    sb.append("stopPartialAudioRecord() called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        hVAudioRecorder = this;
                        str = str5;
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th4) {
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
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
                                    str = str5;
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str);
                                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls = getClass();
                                            canonicalName2 = cls != null ? cls.getCanonicalName() : null;
                                            if (canonicalName2 == null) {
                                                canonicalName2 = "N/A";
                                            }
                                        }
                                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                        if (matcher3.find()) {
                                            canonicalName2 = matcher3.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                        }
                                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName2 = canonicalName2.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, canonicalName2, "stopPartialAudioRecord() called ");
                                        hVAudioRecorder = this;
                                    } catch (Throwable th5) {
                                        th = th5;
                                        Result.Companion companion322 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                    }
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                str = str5;
                            }
                        }
                        str = str5;
                        hVAudioRecorder = this;
                    }
                    audioRecorder = hVAudioRecorder.audioRecorder;
                    if (audioRecorder != null) {
                        audioRecorder.stopPartialAudioRecording$hyperkyc_release();
                    }
                    audioRecorder2 = hVAudioRecorder.audioRecorder;
                    if (audioRecorder2 != null) {
                        audioRecorder2.rawToWav$hyperkyc_release(rawFile, encodedFile);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    m1202constructorimpl = Result.m1202constructorimpl(unit);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                            str2 = str;
                        } else {
                            str2 = str;
                            str3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = getClass();
                        String canonicalName3 = cls2 != null ? cls2.getCanonicalName() : null;
                        str3 = canonicalName3 == null ? "N/A" : canonicalName3;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher4.find()) {
                            str3 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(str3);
                        sb2.append(" - ");
                        String str6 = "stopPartialAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb2.append(str6);
                        sb2.append(' ');
                        sb2.append("");
                        companion6.log(level2, sb2.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th7) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                                    str4 = null;
                                } else {
                                    str4 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        canonicalName = substringAfterLast$default2;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                        if (matcher.find()) {
                                            canonicalName = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                        }
                                        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName = canonicalName.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb3 = new StringBuilder();
                                        String str7 = "stopPartialAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                        sb3.append(str7 != null ? str7 : "null ");
                                        sb3.append(' ');
                                        sb3.append("");
                                        Log.println(6, canonicalName, sb3.toString());
                                        return;
                                    }
                                }
                                Class<?> cls3 = getClass();
                                canonicalName = cls3 != null ? cls3.getCanonicalName() : str4;
                                if (canonicalName == null) {
                                    canonicalName = "N/A";
                                }
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                if (matcher.find()) {
                                }
                                if (canonicalName.length() > 23) {
                                    canonicalName = canonicalName.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb32 = new StringBuilder();
                                String str72 = "stopPartialAudioRecord() " + m1205exceptionOrNullimpl.getMessage();
                                sb32.append(str72 != null ? str72 : "null ");
                                sb32.append(' ');
                                sb32.append("");
                                Log.println(6, canonicalName, sb32.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                Class<?> cls4 = getClass();
                String canonicalName4 = cls4 == null ? cls4.getCanonicalName() : null;
                substringAfterLast$default = canonicalName4 != null ? "N/A" : canonicalName4;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher2.find()) {
                }
                if (substringAfterLast$default.length() > 23) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                sb.append("stopPartialAudioRecord() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                audioRecorder = hVAudioRecorder.audioRecorder;
                if (audioRecorder != null) {
                }
                audioRecorder2 = hVAudioRecorder.audioRecorder;
                if (audioRecorder2 != null) {
                }
                m1202constructorimpl = Result.m1202constructorimpl(unit);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                if (m1205exceptionOrNullimpl != null) {
                }
            }
        }
        charSequence = "co.hyperverge";
        str5 = "Throwable().stackTrace";
        Class<?> cls42 = getClass();
        if (cls42 == null) {
        }
        if (canonicalName4 != null) {
        }
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
        if (matcher2.find()) {
        }
        if (substringAfterLast$default.length() > 23) {
        }
        sb.append(substringAfterLast$default);
        sb.append(" - ");
        sb.append("stopPartialAudioRecord() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
        }
        audioRecorder = hVAudioRecorder.audioRecorder;
        if (audioRecorder != null) {
        }
        audioRecorder2 = hVAudioRecorder.audioRecorder;
        if (audioRecorder2 != null) {
        }
        m1202constructorimpl = Result.m1202constructorimpl(unit);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x03bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String initializeAudioRecorder$hyperkyc_release(String filename, File folderPath, CoroutineScope lifecycleScope) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        CoroutineScope coroutineScope;
        String canonicalName2;
        String className;
        String canonicalName3;
        Object m1202constructorimpl2;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className2;
        String className3;
        String className4;
        HVAudioRecorder hVAudioRecorder = this;
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(folderPath, "folderPath");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
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
        String str5 = "initializeAudioRecorder() called with: filename = " + filename + ", folderPath = " + folderPath + ", lifecycleScope = " + lifecycleScope;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            charSequence = "co.hyperverge";
            str = "packageName";
            coroutineScope = lifecycleScope;
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
                charSequence = "co.hyperverge";
                str = "packageName";
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
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("initializeAudioRecorder() called with: filename = ");
                    sb3.append(filename);
                    sb3.append(", folderPath = ");
                    sb3.append(folderPath);
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
                    Log.println(3, canonicalName2, sb2.toString());
                    hVAudioRecorder = this;
                }
            } else {
                charSequence = "co.hyperverge";
                str = "packageName";
            }
            coroutineScope = lifecycleScope;
            hVAudioRecorder = this;
        }
        hVAudioRecorder.filename = filename;
        hVAudioRecorder.folderPath = folderPath;
        hVAudioRecorder.lifecycleScope = coroutineScope;
        try {
            Result.Companion companion4 = Result.INSTANCE;
            HVAudioRecorder hVAudioRecorder2 = hVAudioRecorder;
            File file = new File(folderPath, '/' + filename + "-audio.mp4");
            AudioRecorder audioRecorder = new AudioRecorder();
            audioRecorder.setFileName$hyperkyc_release(filename, folderPath, coroutineScope);
            audioRecorder.startRecording$hyperkyc_release();
            hVAudioRecorder.audioRecorder = audioRecorder;
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
            return absolutePath;
        } catch (Throwable th2) {
            Result.Companion companion5 = Result.INSTANCE;
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(Result.m1202constructorimpl(ResultKt.createFailure(th2)));
            if (m1205exceptionOrNullimpl != null) {
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb5 = new StringBuilder();
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
                String str6 = "initializeAudioRecorder() " + m1205exceptionOrNullimpl.getMessage();
                if (str6 == null) {
                    str6 = "null ";
                }
                sb5.append(str6);
                sb5.append(' ');
                sb5.append("");
                companion6.log(level2, sb5.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion7 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th3) {
                    Result.Companion companion8 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String str7 = (String) m1202constructorimpl2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str7, str);
                    if (StringsKt.contains$default((CharSequence) str7, charSequence, false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                            str2 = null;
                        } else {
                            str2 = null;
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default != null) {
                                str3 = substringAfterLast$default;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                if (matcher.find()) {
                                    str3 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                }
                                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str3 = str3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb6 = new StringBuilder();
                                str4 = "initializeAudioRecorder() " + m1205exceptionOrNullimpl.getMessage();
                                if (str4 == null) {
                                    str4 = "null ";
                                }
                                sb6.append(str4);
                                sb6.append(' ');
                                sb6.append("");
                                Log.println(6, str3, sb6.toString());
                            }
                        }
                        Class<?> cls4 = getClass();
                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str2;
                        str3 = canonicalName4 == null ? "N/A" : canonicalName4;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                        }
                        if (str3.length() > 23) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb62 = new StringBuilder();
                        str4 = "initializeAudioRecorder() " + m1205exceptionOrNullimpl.getMessage();
                        if (str4 == null) {
                        }
                        sb62.append(str4);
                        sb62.append(' ');
                        sb62.append("");
                        Log.println(6, str3, sb62.toString());
                    }
                }
            }
            return "";
        }
    }
}
