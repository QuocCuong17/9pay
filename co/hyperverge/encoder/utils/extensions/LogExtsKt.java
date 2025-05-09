package co.hyperverge.encoder.utils.extensions;

import android.app.Application;
import android.os.Build;
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
import org.apache.commons.io.IOUtils;

/* compiled from: LogExts.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u001a\"\u0010\u000b\u001a\u00020\f*\u0004\u0018\u00010\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a.\u0010\u000f\u001a\u00020\f*\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a\"\u0010\u0012\u001a\u00020\f*\u0004\u0018\u00010\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a3\u0010\u0013\u001a\u00020\f*\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\u00042\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0082\b\u001a\"\u0010\u0015\u001a\u00020\f*\u0004\u0018\u00010\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\u001a.\u0010\u0016\u001a\u00020\f*\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0080\bø\u0001\u0000\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0007\u001a\u00020\u0006*\u0004\u0018\u00010\b8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0017"}, d2 = {"ANON_CLASS_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MAX_TAG_LENGTH", "", "NULL_VALUE", "", "tag", "", "getTag", "(Ljava/lang/Object;)Ljava/lang/String;", "d", "", "message", "Lkotlin/Function0;", "e", "t", "", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "log", "priority", "v", "w", "hv-bitmaps-to-video_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class LogExtsKt {
    private static final Pattern ANON_CLASS_PATTERN = Pattern.compile("(\\$\\d+)+$");
    private static final int MAX_TAG_LENGTH = 23;
    private static final String NULL_VALUE = "null ";

    public static /* synthetic */ void w$default(Object obj, Throwable th, Function0 message, int i, Object obj2) {
        String className;
        Class<?> cls;
        if ((i & 1) != 0) {
            th = null;
        }
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String str = (String) message.invoke();
        if (str == null) {
            str = NULL_VALUE;
        }
        sb.append(str);
        sb.append(' ');
        String localizedMessage = th != null ? th.getLocalizedMessage() : null;
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
    }

    public static /* synthetic */ void e$default(Object obj, Throwable th, Function0 message, int i, Object obj2) {
        String className;
        Object obj3;
        Object invoke;
        Class<?> cls;
        if ((i & 1) != 0) {
            th = null;
        }
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String str = (String) message.invoke();
        if (str == null) {
            str = NULL_VALUE;
        }
        sb.append(str);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            obj3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (invoke != null) {
            obj3 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    private static final void log(Object obj, int i, Function0<String> function0, Throwable th) {
        HyperLogger.Level level;
        String className;
        Object obj2;
        Object invoke;
        Class<?> cls;
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
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        if (i < 6) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (invoke != null) {
            obj2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }

    static /* synthetic */ void log$default(Object obj, int i, Function0 function0, Throwable th, int i2, Object obj2) {
        HyperLogger.Level level;
        String className;
        Object obj3;
        Object invoke;
        Class<?> cls;
        if ((i2 & 4) != 0) {
            th = null;
        }
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
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String str = (String) function0.invoke();
        if (str == null) {
            str = NULL_VALUE;
        }
        sb.append(str);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        if (i < 6) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            obj3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (invoke != null) {
            obj3 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
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

    public static final void v(Object obj, Function0<String> message) {
        String className;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String invoke = message.invoke();
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
    }

    public static final void d(Object obj, Function0<String> message) {
        String className;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String invoke = message.invoke();
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
    }

    public static final void i(Object obj, Function0<String> message) {
        String className;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String invoke = message.invoke();
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
    }

    public static final void w(Object obj, Throwable th, Function0<String> message) {
        String className;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        String invoke = message.invoke();
        if (invoke == null) {
            invoke = NULL_VALUE;
        }
        sb.append(invoke);
        sb.append(' ');
        String localizedMessage = th != null ? th.getLocalizedMessage() : null;
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
    }

    public static final void e(Object obj, Throwable th, Function0<String> message) {
        String className;
        Object obj2;
        Object invoke;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(message, "message");
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default == null && (obj == null || (cls = obj.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
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
        if (invoke2 == null) {
            invoke2 = NULL_VALUE;
        }
        sb.append(invoke2);
        sb.append(' ');
        String localizedMessage = th == null ? null : th.getLocalizedMessage();
        sb.append(localizedMessage != null ? Intrinsics.stringPlus(IOUtils.LINE_SEPARATOR_UNIX, localizedMessage) : "");
        companion.log(level, sb.toString());
        try {
            Result.Companion companion2 = Result.INSTANCE;
            invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        if (invoke != null) {
            obj2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }
}
