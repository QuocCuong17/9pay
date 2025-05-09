package co.hyperverge.crashguard.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.crashguard.objects.CrashguardConfig;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import io.sentry.Hub;
import io.sentry.IHub;
import io.sentry.Sentry;
import io.sentry.Session;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.Request;
import java.lang.Thread;
import java.util.regex.Matcher;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HVSentryHub.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lco/hyperverge/crashguard/utils/HVSentryHub;", "", "()V", "clientHub", "Lio/sentry/IHub;", "clientHubDeduplicationConfig", "", "clientHubScreenshotConfig", "clientHubSentryAndroidOptions", "Lio/sentry/android/core/SentryAndroidOptions;", "defaultUEH", "Ljava/lang/Thread$UncaughtExceptionHandler;", "kotlin.jvm.PlatformType", "hub", "Lio/sentry/Hub;", "enableMainHubDeduplication", "", "enable", "enableScreenshotAttachment", Session.JsonKeys.INIT, Request.JsonKeys.ENV, "", HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/crashguard/objects/CrashguardConfig;", "initialiseSentryHub", "pushUncaughtException", "e", "", "crashguard_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HVSentryHub {
    private static IHub clientHub;
    private static boolean clientHubDeduplicationConfig;
    private static boolean clientHubScreenshotConfig;
    private static SentryAndroidOptions clientHubSentryAndroidOptions;
    private static Hub hub;
    public static final HVSentryHub INSTANCE = new HVSentryHub();
    private static final Thread.UncaughtExceptionHandler defaultUEH = Thread.getDefaultUncaughtExceptionHandler();

    private HVSentryHub() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initialiseSentryHub$lambda-2, reason: not valid java name */
    public static final void m393initialiseSentryHub$lambda2(CrashguardConfig config, Thread thread, Throwable e) {
        Intrinsics.checkNotNullParameter(config, "$config");
        Intrinsics.checkNotNullExpressionValue(e, "e");
        if (ExtensionsKt.shouldReportCrash(e, config.getFilters())) {
            INSTANCE.pushUncaughtException(e);
            return;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = defaultUEH;
        if (uncaughtExceptionHandler == null) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, e);
    }

    public final void init(String env, CrashguardConfig config) {
        String className;
        Object m1202constructorimpl;
        String canonicalName;
        String className2;
        Object invoke;
        Class<?> cls;
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(config, "config");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = null;
        String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        String str2 = "N/A";
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
        String stringPlus = Intrinsics.stringPlus("init() called with: config = ", config);
        if (stringPlus == null) {
            stringPlus = "null ";
        }
        sb.append(stringPlus);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!LogExtsKt.isRelease()) {
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
                if (LogExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 != null && (className2 = stackTraceElement2.getClassName()) != null) {
                            str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        if (str == null) {
                            Class<?> cls2 = getClass();
                            if (cls2 != null && (canonicalName = cls2.getCanonicalName()) != null) {
                                str2 = canonicalName;
                            }
                        } else {
                            str2 = str;
                        }
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                        if (matcher2.find()) {
                            str2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                        }
                        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String stringPlus2 = Intrinsics.stringPlus("init() called with: config = ", config);
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str2, sb2.toString());
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        initialiseSentryHub(env, config);
    }

    private final void initialiseSentryHub(String env, final CrashguardConfig config) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        SentryAndroidOptions sentryAndroidOptions = null;
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
        String stringPlus = Intrinsics.stringPlus("initialiseSentryHub() called with: config = ", config);
        if (stringPlus == null) {
            stringPlus = "null ";
        }
        sb.append(stringPlus);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!LogExtsKt.isRelease()) {
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
                if (LogExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                        StringBuilder sb2 = new StringBuilder();
                        String stringPlus2 = Intrinsics.stringPlus("initialiseSentryHub() called with: config = ", config);
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = new SentryAndroidOptions();
        ExtensionsKt.setConfig(sentryAndroidOptions2, env, config);
        hub = new Hub(sentryAndroidOptions2);
        IHub cloneMainHub = Sentry.cloneMainHub();
        Intrinsics.checkNotNullExpressionValue(cloneMainHub, "cloneMainHub()");
        clientHub = cloneMainHub;
        if (cloneMainHub == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clientHub");
            cloneMainHub = null;
        }
        SentryAndroidOptions sentryAndroidOptions3 = (SentryAndroidOptions) cloneMainHub.getOptions();
        clientHubSentryAndroidOptions = sentryAndroidOptions3;
        if (sentryAndroidOptions3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clientHubSentryAndroidOptions");
            sentryAndroidOptions3 = null;
        }
        clientHubScreenshotConfig = sentryAndroidOptions3.isAttachScreenshot();
        SentryAndroidOptions sentryAndroidOptions4 = clientHubSentryAndroidOptions;
        if (sentryAndroidOptions4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clientHubSentryAndroidOptions");
        } else {
            sentryAndroidOptions = sentryAndroidOptions4;
        }
        clientHubDeduplicationConfig = sentryAndroidOptions.isEnableDeduplication();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: co.hyperverge.crashguard.utils.HVSentryHub$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th2) {
                HVSentryHub.m393initialiseSentryHub$lambda2(CrashguardConfig.this, thread, th2);
            }
        });
    }

    private final void pushUncaughtException(Throwable e) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        Class<?> cls2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String stringPlus = Intrinsics.stringPlus("pushUncaughtException() called with: e = ", e);
        if (stringPlus == null) {
            stringPlus = "null ";
        }
        sb.append(stringPlus);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!LogExtsKt.isRelease()) {
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
                if (LogExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                        StringBuilder sb2 = new StringBuilder();
                        String stringPlus2 = Intrinsics.stringPlus("pushUncaughtException() called with: e = ", e);
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new HVSentryHub$pushUncaughtException$2(e, null), 1, null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableScreenshotAttachment(boolean enable) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        boolean z;
        Class<?> cls2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        SentryAndroidOptions sentryAndroidOptions = null;
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
        String stringPlus = Intrinsics.stringPlus("enableScreenshotAttachment() called with: enable = ", Boolean.valueOf(enable));
        if (stringPlus == null) {
            stringPlus = "null ";
        }
        sb.append(stringPlus);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!LogExtsKt.isRelease()) {
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
                if (LogExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                        StringBuilder sb2 = new StringBuilder();
                        String stringPlus2 = Intrinsics.stringPlus("enableScreenshotAttachment() called with: enable = ", Boolean.valueOf(enable));
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = clientHubSentryAndroidOptions;
        if (sentryAndroidOptions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clientHubSentryAndroidOptions");
            z = enable;
        } else {
            z = enable;
            sentryAndroidOptions = sentryAndroidOptions2;
        }
        sentryAndroidOptions.setAttachScreenshot(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableMainHubDeduplication(boolean enable) {
        String className;
        Object m1202constructorimpl;
        String className2;
        Class<?> cls;
        Object invoke;
        boolean z;
        Class<?> cls2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        SentryAndroidOptions sentryAndroidOptions = null;
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
        String stringPlus = Intrinsics.stringPlus("enableDeduplication() called with: enable = ", Boolean.valueOf(enable));
        if (stringPlus == null) {
            stringPlus = "null ";
        }
        sb.append(stringPlus);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!LogExtsKt.isRelease()) {
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
                if (LogExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                        StringBuilder sb2 = new StringBuilder();
                        String stringPlus2 = Intrinsics.stringPlus("enableDeduplication() called with: enable = ", Boolean.valueOf(enable));
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = clientHubSentryAndroidOptions;
        if (sentryAndroidOptions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clientHubSentryAndroidOptions");
            z = enable;
        } else {
            z = enable;
            sentryAndroidOptions = sentryAndroidOptions2;
        }
        sentryAndroidOptions.setEnableDeduplication(z);
    }
}
