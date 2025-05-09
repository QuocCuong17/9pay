package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: LogExts.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u001a\"\u0010\u000b\u001a\u00020\f*\u0004\u0018\u00010\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a.\u0010\u000f\u001a\u00020\f*\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a\"\u0010\u0012\u001a\u00020\f*\u0004\u0018\u00010\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a3\u0010\u0013\u001a\u00020\f*\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\u00042\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0082\b\u001a\"\u0010\u0015\u001a\u00020\f*\u0004\u0018\u00010\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a.\u0010\u0016\u001a\u00020\f*\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0007\u001a\u00020\u0006*\u0004\u0018\u00010\b8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0017"}, d2 = {"ANON_CLASS_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MAX_TAG_LENGTH", "", "NULL_VALUE", "", "tag", "", "getTag", "(Ljava/lang/Object;)Ljava/lang/String;", "d", "", "message", "Lkotlin/Function0;", "e", "t", "", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "log", "priority", "v", "w", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LogExtsKt {
    private static final Pattern ANON_CLASS_PATTERN = Pattern.compile("(\\$\\d+)+$");
    private static final int MAX_TAG_LENGTH = 23;
    private static final String NULL_VALUE = "null ";

    public static final /* synthetic */ Pattern access$getANON_CLASS_PATTERN$p() {
        return ANON_CLASS_PATTERN;
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x015f, code lost:
    
        if (r0 == null) goto L163;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void w$default(Object obj, Throwable th, Function0 message, int i, Object obj2) {
        String canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Throwable th2 = (i & 1) != 0 ? null : th;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
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
        String str4 = (String) message.invoke();
        String str5 = NULL_VALUE;
        if (str4 == null) {
            str4 = NULL_VALUE;
        }
        sb.append(str4);
        sb.append(' ');
        String localizedMessage = th2 != null ? th2.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str = '\n' + localizedMessage;
        } else {
            str = "";
        }
        sb.append(str);
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
        } catch (Throwable th3) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                    canonicalName2 = (obj == null || (cls2 = obj.getClass()) == null) ? null : cls2.getCanonicalName();
                }
                str2 = canonicalName2;
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                if (matcher2.find()) {
                    str2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String str6 = (String) message.invoke();
                if (str6 != null) {
                    str5 = str6;
                }
                sb2.append(str5);
                sb2.append(' ');
                String localizedMessage2 = th2 != null ? th2.getLocalizedMessage() : null;
                if (localizedMessage2 != null) {
                    str3 = '\n' + localizedMessage2;
                }
                sb2.append(str3);
                Log.println(5, str2, sb2.toString());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x015a, code lost:
    
        if (r0 == null) goto L158;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void e$default(Object obj, Throwable th, Function0 message, int i, Object obj2) {
        String canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Throwable th2 = (i & 1) != 0 ? null : th;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
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
        String str4 = (String) message.invoke();
        String str5 = NULL_VALUE;
        if (str4 == null) {
            str4 = NULL_VALUE;
        }
        sb.append(str4);
        sb.append(' ');
        String localizedMessage = th2 != null ? th2.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str = '\n' + localizedMessage;
        } else {
            str = "";
        }
        sb.append(str);
        companion.log(level, sb.toString());
        CoreExtsKt.isRelease();
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
        } catch (Throwable th3) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                    canonicalName2 = (obj == null || (cls2 = obj.getClass()) == null) ? null : cls2.getCanonicalName();
                }
                str2 = canonicalName2;
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                if (matcher2.find()) {
                    str2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String str6 = (String) message.invoke();
                if (str6 != null) {
                    str5 = str6;
                }
                sb2.append(str5);
                sb2.append(' ');
                String localizedMessage2 = th2 != null ? th2.getLocalizedMessage() : null;
                if (localizedMessage2 != null) {
                    str3 = '\n' + localizedMessage2;
                }
                sb2.append(str3);
                Log.println(6, str2, sb2.toString());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0160, code lost:
    
        if (r0 == null) goto L178;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void log(Object obj, int i, Function0<String> function0, Throwable th) {
        HyperLogger.Level level;
        String canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            level = HyperLogger.Level.DEBUG;
        } else {
            level = HyperLogger.Level.ERROR;
        }
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
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
        String invoke = function0.invoke();
        String str4 = NULL_VALUE;
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        String localizedMessage = th != null ? th.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str = '\n' + localizedMessage;
        } else {
            str = "";
        }
        sb.append(str);
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease() || i >= 6) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
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
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (obj == null || (cls2 = obj.getClass()) == null) ? null : cls2.getCanonicalName();
                    }
                    str2 = canonicalName2;
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String invoke3 = function0.invoke();
                    if (invoke3 != null) {
                        str4 = invoke3;
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
                    if (localizedMessage2 != null) {
                        str3 = '\n' + localizedMessage2;
                    }
                    sb2.append(str3);
                    Log.println(i, str2, sb2.toString());
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x016a, code lost:
    
        if (r0 == null) goto L184;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ void log$default(Object obj, int i, Function0 function0, Throwable th, int i2, Object obj2) {
        HyperLogger.Level level;
        String canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Throwable th2 = (i2 & 4) != 0 ? null : th;
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            level = HyperLogger.Level.DEBUG;
        } else {
            level = HyperLogger.Level.ERROR;
        }
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
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
        String str4 = (String) function0.invoke();
        String str5 = NULL_VALUE;
        if (str4 == null) {
            str4 = NULL_VALUE;
        }
        sb.append(str4);
        sb.append(' ');
        String localizedMessage = th2 != null ? th2.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str = '\n' + localizedMessage;
        } else {
            str = "";
        }
        sb.append(str);
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease() || i >= 6) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                        canonicalName2 = (obj == null || (cls2 = obj.getClass()) == null) ? null : cls2.getCanonicalName();
                    }
                    str2 = canonicalName2;
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str6 = (String) function0.invoke();
                    if (str6 != null) {
                        str5 = str6;
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    String localizedMessage2 = th2 != null ? th2.getLocalizedMessage() : null;
                    if (localizedMessage2 != null) {
                        str3 = '\n' + localizedMessage2;
                    }
                    sb2.append(str3);
                    Log.println(i, str2, sb2.toString());
                }
            }
        }
    }

    private static final String getTag(Object obj) {
        String str;
        Class<?> cls;
        String className;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = null;
        if (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            if (obj != null && (cls = obj.getClass()) != null) {
                str2 = cls.getCanonicalName();
            }
            str = str2 == null ? "N/A" : str2;
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(str);
        if (matcher.find()) {
            str = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
        }
        if (str.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
            return str;
        }
        String substring = str.substring(0, 23);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static final void v(Object obj, Function0<String> message) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
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
        String invoke = message.invoke();
        String str3 = NULL_VALUE;
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
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
                    if (obj != null && (cls2 = obj.getClass()) != null) {
                        str2 = cls2.getCanonicalName();
                    }
                    if (str2 != null) {
                        str = str2;
                    }
                } else {
                    str = substringAfterLast$default;
                }
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String invoke3 = message.invoke();
                if (invoke3 != null) {
                    str3 = invoke3;
                }
                sb2.append(str3);
                sb2.append(' ');
                sb2.append("");
                Log.println(2, str, sb2.toString());
            }
        }
    }

    public static final void d(Object obj, Function0<String> message) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
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
        String invoke = message.invoke();
        String str3 = NULL_VALUE;
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
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
                    if (obj != null && (cls2 = obj.getClass()) != null) {
                        str2 = cls2.getCanonicalName();
                    }
                    if (str2 != null) {
                        str = str2;
                    }
                } else {
                    str = substringAfterLast$default;
                }
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String invoke3 = message.invoke();
                if (invoke3 != null) {
                    str3 = invoke3;
                }
                sb2.append(str3);
                sb2.append(' ');
                sb2.append("");
                Log.println(3, str, sb2.toString());
            }
        }
    }

    public static final void i(Object obj, Function0<String> message) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
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
        String invoke = message.invoke();
        String str3 = NULL_VALUE;
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
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
                    if (obj != null && (cls2 = obj.getClass()) != null) {
                        str2 = cls2.getCanonicalName();
                    }
                    if (str2 != null) {
                        str = str2;
                    }
                } else {
                    str = substringAfterLast$default;
                }
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String invoke3 = message.invoke();
                if (invoke3 != null) {
                    str3 = invoke3;
                }
                sb2.append(str3);
                sb2.append(' ');
                sb2.append("");
                Log.println(4, str, sb2.toString());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0155, code lost:
    
        if (r0 == null) goto L155;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void w(Object obj, Throwable th, Function0<String> message) {
        String canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
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
        String invoke = message.invoke();
        String str4 = NULL_VALUE;
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        String localizedMessage = th != null ? th.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str = '\n' + localizedMessage;
        } else {
            str = "";
        }
        sb.append(str);
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
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
            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName2 = (obj == null || (cls2 = obj.getClass()) == null) ? null : cls2.getCanonicalName();
                }
                str2 = canonicalName2;
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                if (matcher2.find()) {
                    str2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String invoke3 = message.invoke();
                if (invoke3 != null) {
                    str4 = invoke3;
                }
                sb2.append(str4);
                sb2.append(' ');
                String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
                if (localizedMessage2 != null) {
                    str3 = '\n' + localizedMessage2;
                }
                sb2.append(str3);
                Log.println(5, str2, sb2.toString());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0150, code lost:
    
        if (r0 == null) goto L150;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void e(Object obj, Throwable th, Function0<String> message) {
        String canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
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
        String invoke = message.invoke();
        String str4 = NULL_VALUE;
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        String localizedMessage = th != null ? th.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str = '\n' + localizedMessage;
        } else {
            str = "";
        }
        sb.append(str);
        companion.log(level, sb.toString());
        CoreExtsKt.isRelease();
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
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
            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName2 = (obj == null || (cls2 = obj.getClass()) == null) ? null : cls2.getCanonicalName();
                }
                str2 = canonicalName2;
                Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                if (matcher2.find()) {
                    str2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String invoke3 = message.invoke();
                if (invoke3 != null) {
                    str4 = invoke3;
                }
                sb2.append(str4);
                sb2.append(' ');
                String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
                if (localizedMessage2 != null) {
                    str3 = '\n' + localizedMessage2;
                }
                sb2.append(str3);
                Log.println(6, str2, sb2.toString());
            }
        }
    }
}
