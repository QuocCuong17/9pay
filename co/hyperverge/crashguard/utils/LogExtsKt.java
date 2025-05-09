package co.hyperverge.crashguard.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: LogExts.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u001a\"\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\u000f2\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0015H\u0080\bø\u0001\u0000\u001a.\u0010\u0016\u001a\u00020\u0013*\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0015H\u0080\bø\u0001\u0000\u001a\"\u0010\u0019\u001a\u00020\u0013*\u0004\u0018\u00010\u000f2\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0015H\u0080\bø\u0001\u0000\u001a3\u0010\u001a\u001a\u00020\u0013*\u0004\u0018\u00010\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0082\b\u001a\"\u0010\u001c\u001a\u00020\u0013*\u0004\u0018\u00010\u000f2\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0015H\u0080\bø\u0001\u0000\u001a.\u0010\u001d\u001a\u00020\u0013*\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0015H\u0080\bø\u0001\u0000\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0007\u001a\u00020\b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0007\u0010\t\"\u001b\u0010\f\u001a\u00020\b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000b\u001a\u0004\b\f\u0010\t\"\u001b\u0010\u000e\u001a\u00020\u0006*\u0004\u0018\u00010\u000f8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"ANON_CLASS_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MAX_TAG_LENGTH", "", "NULL_VALUE", "", "isDebug", "", "()Z", "isDebug$delegate", "Lkotlin/Lazy;", "isRelease", "isRelease$delegate", "tag", "", "getTag", "(Ljava/lang/Object;)Ljava/lang/String;", "d", "", "message", "Lkotlin/Function0;", "e", "t", "", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "log", "priority", "v", "w", "crashguard_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class LogExtsKt {
    private static final int MAX_TAG_LENGTH = 23;
    private static final String NULL_VALUE = "null ";
    private static final Pattern ANON_CLASS_PATTERN = Pattern.compile("(\\$\\d+)+$");
    private static final Lazy isDebug$delegate = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.crashguard.utils.LogExtsKt$isDebug$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    });
    private static final Lazy isRelease$delegate = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.crashguard.utils.LogExtsKt$isRelease$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(!LogExtsKt.isDebug());
        }
    });

    public static final /* synthetic */ Pattern access$getANON_CLASS_PATTERN$p() {
        return ANON_CLASS_PATTERN;
    }

    public static /* synthetic */ void w$default(Object obj, Throwable th, Function0 message, int i, Object obj2) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
        Throwable th2 = (i & 1) != 0 ? null : th;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String str2 = (String) message.invoke();
        String str3 = NULL_VALUE;
        if (str2 == null) {
            str2 = NULL_VALUE;
        }
        sb.append(str2);
        sb.append(' ');
        String localizedMessage = th2 == null ? null : th2.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        if (isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th3) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    if (substringAfterLast$default2 != null || (obj != null && (cls = obj.getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                        str = substringAfterLast$default2;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = (String) message.invoke();
                    if (str4 != null) {
                        str3 = str4;
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    String localizedMessage2 = th2 != null ? th2.getLocalizedMessage() : null;
                    sb2.append(localizedMessage2 != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage2) : "");
                    Log.println(5, str, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    public static /* synthetic */ void e$default(Object obj, Throwable th, Function0 message, int i, Object obj2) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
        Throwable th2 = (i & 1) != 0 ? null : th;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String str2 = (String) message.invoke();
        String str3 = NULL_VALUE;
        if (str2 == null) {
            str2 = NULL_VALUE;
        }
        sb.append(str2);
        sb.append(' ');
        String localizedMessage = th2 == null ? null : th2.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        isRelease();
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th3) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    if (substringAfterLast$default2 != null || (obj != null && (cls = obj.getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                        str = substringAfterLast$default2;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = (String) message.invoke();
                    if (str4 != null) {
                        str3 = str4;
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    String localizedMessage2 = th2 != null ? th2.getLocalizedMessage() : null;
                    sb2.append(localizedMessage2 != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage2) : "");
                    Log.println(6, str, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    private static final void log(Object obj, int i, Function0<String> function0, Throwable th) {
        HyperLogger.Level level;
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
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
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String invoke2 = function0.invoke();
        String str2 = NULL_VALUE;
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        if (!isRelease() || i >= 6) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th2) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (invoke != null) {
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default2 != null || (obj != null && (cls = obj.getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                            str = substringAfterLast$default2;
                        }
                        Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                        if (matcher2.find()) {
                            str = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                        }
                        if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str = str.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String invoke3 = function0.invoke();
                        if (invoke3 != null) {
                            str2 = invoke3;
                        }
                        sb2.append(str2);
                        sb2.append(' ');
                        String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
                        sb2.append(localizedMessage2 != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage2) : "");
                        Log.println(i, str, sb2.toString());
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
        }
    }

    static /* synthetic */ void log$default(Object obj, int i, Function0 function0, Throwable th, int i2, Object obj2) {
        HyperLogger.Level level;
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
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
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String str2 = (String) function0.invoke();
        String str3 = NULL_VALUE;
        if (str2 == null) {
            str2 = NULL_VALUE;
        }
        sb.append(str2);
        sb.append(' ');
        String localizedMessage = th2 == null ? null : th2.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        if (!isRelease() || i >= 6) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th3) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (invoke != null) {
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default2 != null || (obj != null && (cls = obj.getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                            str = substringAfterLast$default2;
                        }
                        Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                        if (matcher2.find()) {
                            str = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                        }
                        if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str = str.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str4 = (String) function0.invoke();
                        if (str4 != null) {
                            str3 = str4;
                        }
                        sb2.append(str3);
                        sb2.append(' ');
                        String localizedMessage2 = th2 != null ? th2.getLocalizedMessage() : null;
                        sb2.append(localizedMessage2 != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage2) : "");
                        Log.println(i, str, sb2.toString());
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
        }
    }

    private static final String getTag(Object obj) {
        Class<?> cls;
        String className;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = null;
        if (stackTraceElement != null && (className = stackTraceElement.getClassName()) != null) {
            str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        if (str == null && (obj == null || (cls = obj.getClass()) == null || (str = cls.getCanonicalName()) == null)) {
            str = "N/A";
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
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static final /* synthetic */ boolean isDebug() {
        return ((Boolean) isDebug$delegate.getValue()).booleanValue();
    }

    public static final /* synthetic */ boolean isRelease() {
        return ((Boolean) isRelease$delegate.getValue()).booleanValue();
    }

    public static final void v(Object obj, Function0<String> message) {
        String className;
        Object m1202constructorimpl;
        Class<?> cls;
        String canonicalName;
        String className2;
        Object invoke;
        Class<?> cls2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = null;
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str2 = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String invoke2 = message.invoke();
        String str3 = NULL_VALUE;
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 != null && (className2 = stackTraceElement2.getClassName()) != null) {
                        str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    if (str != null) {
                        str2 = str;
                    } else if (obj != null && (cls = obj.getClass()) != null && (canonicalName = cls.getCanonicalName()) != null) {
                        str2 = canonicalName;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String invoke3 = message.invoke();
                    if (invoke3 != null) {
                        str3 = invoke3;
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(2, str2, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    public static final void d(Object obj, Function0<String> message) {
        String className;
        Object m1202constructorimpl;
        Class<?> cls;
        String canonicalName;
        String className2;
        Object invoke;
        Class<?> cls2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = null;
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str2 = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String invoke2 = message.invoke();
        String str3 = NULL_VALUE;
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 != null && (className2 = stackTraceElement2.getClassName()) != null) {
                        str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    if (str != null) {
                        str2 = str;
                    } else if (obj != null && (cls = obj.getClass()) != null && (canonicalName = cls.getCanonicalName()) != null) {
                        str2 = canonicalName;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String invoke3 = message.invoke();
                    if (invoke3 != null) {
                        str3 = invoke3;
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    public static final void i(Object obj, Function0<String> message) {
        String className;
        Object m1202constructorimpl;
        Class<?> cls;
        String canonicalName;
        String className2;
        Object invoke;
        Class<?> cls2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = null;
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str2 = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String invoke2 = message.invoke();
        String str3 = NULL_VALUE;
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 != null && (className2 = stackTraceElement2.getClassName()) != null) {
                        str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    if (str != null) {
                        str2 = str;
                    } else if (obj != null && (cls = obj.getClass()) != null && (canonicalName = cls.getCanonicalName()) != null) {
                        str2 = canonicalName;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String invoke3 = message.invoke();
                    if (invoke3 != null) {
                        str3 = invoke3;
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(4, str2, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    public static final void w(Object obj, Throwable th, Function0<String> message) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String invoke2 = message.invoke();
        String str2 = NULL_VALUE;
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        if (isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    if (substringAfterLast$default2 != null || (obj != null && (cls = obj.getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                        str = substringAfterLast$default2;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String invoke3 = message.invoke();
                    if (invoke3 != null) {
                        str2 = invoke3;
                    }
                    sb2.append(str2);
                    sb2.append(' ');
                    String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
                    sb2.append(localizedMessage2 != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage2) : "");
                    Log.println(5, str, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    public static final void e(Object obj, Throwable th, Function0<String> message) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str = "N/A";
        if (substringAfterLast$default == null && (obj == null || (cls2 = obj.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
            substringAfterLast$default = "N/A";
        }
        Matcher matcher = ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
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
        String invoke2 = message.invoke();
        String str2 = NULL_VALUE;
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        isRelease();
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (invoke != null) {
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    String substringAfterLast$default2 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    if (substringAfterLast$default2 != null || (obj != null && (cls = obj.getClass()) != null && (substringAfterLast$default2 = cls.getCanonicalName()) != null)) {
                        str = substringAfterLast$default2;
                    }
                    Matcher matcher2 = ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String invoke3 = message.invoke();
                    if (invoke3 != null) {
                        str2 = invoke3;
                    }
                    sb2.append(str2);
                    sb2.append(' ');
                    String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
                    sb2.append(localizedMessage2 != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage2) : "");
                    Log.println(6, str, sb2.toString());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }
}
