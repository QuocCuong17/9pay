package co.hyperverge.crashguard;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import co.hyperverge.crashguard.objects.CrashguardConfig;
import co.hyperverge.crashguard.utils.ExtensionsKt;
import co.hyperverge.crashguard.utils.HVSentryHub;
import co.hyperverge.crashguard.utils.LogExtsKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperlogger.HyperLogger;
import com.tekartik.sqflite.Constant;
import io.sentry.Sentry;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.android.core.SentryAndroid;
import io.sentry.android.core.SentryAndroidOptions;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: CrashGuard.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007J\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lco/hyperverge/crashguard/CrashGuard;", "", "()V", "crashguardConfig", "Lco/hyperverge/crashguard/objects/CrashguardConfig;", "isInitialized", "", "endSession", "", Session.JsonKeys.INIT, "context", "Landroid/content/Context;", WorkflowModule.TYPE_START_SESSION_RECORDING, "Companion", "crashguard_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CrashGuard {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static CrashGuard INSTANCE;
    private CrashguardConfig crashguardConfig;
    private boolean isInitialized;

    public /* synthetic */ CrashGuard(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final CrashGuard getInstance() {
        return INSTANCE.getInstance();
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        init$default(this, context, null, 2, null);
    }

    private CrashGuard() {
    }

    public static /* synthetic */ void init$default(CrashGuard crashGuard, Context context, CrashguardConfig crashguardConfig, int i, Object obj) {
        if ((i & 2) != 0) {
            crashguardConfig = new CrashguardConfig(null, null, null, 7, null);
        }
        crashGuard.init(context, crashguardConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-5, reason: not valid java name */
    public static final void m390init$lambda5(String env, CrashguardConfig crashguardConfig, SentryAndroidOptions it) {
        Intrinsics.checkNotNullParameter(env, "$env");
        Intrinsics.checkNotNullParameter(crashguardConfig, "$crashguardConfig");
        Intrinsics.checkNotNullParameter(it, "it");
        ExtensionsKt.setConfig(it, env, crashguardConfig);
    }

    /* compiled from: CrashGuard.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lco/hyperverge/crashguard/CrashGuard$Companion;", "", "()V", "INSTANCE", "Lco/hyperverge/crashguard/CrashGuard;", "getInstance", "crashguard_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CrashGuard getInstance() {
            CrashGuard crashGuard = CrashGuard.INSTANCE;
            if (crashGuard != null) {
                return crashGuard;
            }
            CrashGuard crashGuard2 = new CrashGuard(null);
            Companion companion = CrashGuard.INSTANCE;
            CrashGuard.INSTANCE = crashGuard2;
            return crashGuard2;
        }
    }

    public final void startSession() {
        String className;
        Object m1202constructorimpl;
        String canonicalName;
        String className2;
        Object invoke;
        Class<?> cls;
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
        sb.append("startSession() called");
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
                        Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str2);
                        if (matcher2.find()) {
                            str2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                        }
                        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str2, "startSession() called ");
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        if (this.isInitialized) {
            Sentry.getCurrentHub().startSession();
        }
    }

    public final void endSession() {
        String className;
        Object m1202constructorimpl;
        String canonicalName;
        String className2;
        Object invoke;
        Class<?> cls;
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
        sb.append("endSession() called");
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
                        Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str2);
                        if (matcher2.find()) {
                            str2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                        }
                        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str2, "endSession() called ");
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        if (this.isInitialized) {
            Sentry.getCurrentHub().endSession();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x032b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void init(Context context, final CrashguardConfig crashguardConfig) {
        String className;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String className2;
        Class<?> cls;
        Object invoke;
        String className3;
        String str2;
        String substringAfterLast$default;
        Object m1202constructorimpl2;
        String className4;
        String str3;
        String canonicalName;
        Object invoke2;
        Class<?> cls2;
        String className5;
        Object m1202constructorimpl3;
        String className6;
        String str4;
        String canonicalName2;
        Object invoke3;
        Class<?> cls3;
        Class<?> cls4;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(crashguardConfig, "crashguardConfig");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String substringAfterLast$default2 = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        if (substringAfterLast$default2 == null && ((cls4 = getClass()) == null || (substringAfterLast$default2 = cls4.getCanonicalName()) == null)) {
            substringAfterLast$default2 = "N/A";
        }
        Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default2);
        if (matcher.find()) {
            substringAfterLast$default2 = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
            substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        sb.append(substringAfterLast$default2);
        sb.append(" - ");
        String stringPlus = Intrinsics.stringPlus("init() called with: crashguardConfig = ", crashguardConfig);
        sb.append(stringPlus == null ? "null " : stringPlus);
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
                    charSequence = "co.hyperverge";
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        String substringAfterLast$default3 = (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default3 == null && ((cls = getClass()) == null || (substringAfterLast$default3 = cls.getCanonicalName()) == null)) {
                            substringAfterLast$default3 = str;
                        }
                        Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default3);
                        if (matcher2.find()) {
                            substringAfterLast$default3 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (substringAfterLast$default3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            substringAfterLast$default3 = substringAfterLast$default3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String stringPlus2 = Intrinsics.stringPlus("init() called with: crashguardConfig = ", crashguardConfig);
                        if (stringPlus2 == null) {
                            stringPlus2 = "null ";
                        }
                        sb2.append(stringPlus2);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, substringAfterLast$default3, sb2.toString());
                    }
                    if (this.isInitialized) {
                        this.crashguardConfig = crashguardConfig;
                        if (crashguardConfig.getShouldReportCrashes()) {
                            Context applicationContext = context.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                            String str5 = ExtensionsKt.isReleaseBuild(applicationContext) ? "release" : Constant.METHOD_DEBUG;
                            if (Sentry.isEnabled()) {
                                HVSentryHub.INSTANCE.init(str5, crashguardConfig);
                            } else {
                                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3 = new StringBuilder();
                                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                                    str2 = str5;
                                    substringAfterLast$default = null;
                                } else {
                                    str2 = str5;
                                    substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                if (substringAfterLast$default == null && ((cls2 = getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
                                    substringAfterLast$default = str;
                                }
                                Matcher matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default);
                                if (matcher3.find()) {
                                    substringAfterLast$default = matcher3.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                                }
                                Unit unit3 = Unit.INSTANCE;
                                if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                sb3.append(substringAfterLast$default);
                                sb3.append(" - ");
                                String str6 = "SentryAndroid init() called with: config = [" + crashguardConfig + ']';
                                if (str6 == null) {
                                    str6 = "null ";
                                }
                                sb3.append(str6);
                                sb3.append(' ');
                                sb3.append("");
                                companion4.log(level2, sb3.toString());
                                if (!LogExtsKt.isRelease()) {
                                    try {
                                        Result.Companion companion5 = Result.INSTANCE;
                                        invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    } catch (Throwable th2) {
                                        Result.Companion companion6 = Result.INSTANCE;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                    }
                                    if (invoke2 != null) {
                                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                            m1202constructorimpl2 = "";
                                        }
                                        String packageName2 = (String) m1202constructorimpl2;
                                        if (LogExtsKt.isDebug()) {
                                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                String substringAfterLast$default4 = (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                if (substringAfterLast$default4 == null) {
                                                    Class<?> cls5 = getClass();
                                                    str3 = (cls5 == null || (canonicalName = cls5.getCanonicalName()) == null) ? str : canonicalName;
                                                } else {
                                                    str3 = substringAfterLast$default4;
                                                }
                                                Matcher matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str3);
                                                if (matcher4.find()) {
                                                    str3 = matcher4.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                                }
                                                Unit unit4 = Unit.INSTANCE;
                                                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str3 = str3.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb4 = new StringBuilder();
                                                String str7 = "SentryAndroid init() called with: config = [" + crashguardConfig + ']';
                                                if (str7 == null) {
                                                    str7 = "null ";
                                                }
                                                sb4.append(str7);
                                                sb4.append(' ');
                                                sb4.append("");
                                                Log.println(3, str3, sb4.toString());
                                            }
                                        }
                                    } else {
                                        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                                    }
                                }
                                final String str8 = str2;
                                SentryAndroid.init(context.getApplicationContext(), (Sentry.OptionsConfiguration<SentryAndroidOptions>) new Sentry.OptionsConfiguration() { // from class: co.hyperverge.crashguard.CrashGuard$$ExternalSyntheticLambda0
                                    @Override // io.sentry.Sentry.OptionsConfiguration
                                    public final void configure(SentryOptions sentryOptions) {
                                        CrashGuard.m390init$lambda5(str8, crashguardConfig, (SentryAndroidOptions) sentryOptions);
                                    }
                                });
                            }
                        }
                        this.isInitialized = true;
                        return;
                    }
                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    String substringAfterLast$default5 = (stackTraceElement5 == null || (className5 = stackTraceElement5.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    if (substringAfterLast$default5 == null && ((cls3 = getClass()) == null || (substringAfterLast$default5 = cls3.getCanonicalName()) == null)) {
                        substringAfterLast$default5 = str;
                    }
                    Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(substringAfterLast$default5);
                    if (matcher5.find()) {
                        substringAfterLast$default5 = matcher5.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default5, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (substringAfterLast$default5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        substringAfterLast$default5 = substringAfterLast$default5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    sb5.append(substringAfterLast$default5);
                    sb5.append(" - ");
                    sb5.append("Already initialized.");
                    sb5.append(' ');
                    sb5.append("");
                    companion7.log(level3, sb5.toString());
                    if (LogExtsKt.isRelease()) {
                        return;
                    }
                    try {
                        Result.Companion companion8 = Result.INSTANCE;
                        invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    } catch (Throwable th3) {
                        Result.Companion companion9 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (invoke3 != null) {
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName3 = (String) m1202constructorimpl3;
                        if (LogExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                String substringAfterLast$default6 = (stackTraceElement6 == null || (className6 = stackTraceElement6.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default6 == null) {
                                    Class<?> cls6 = getClass();
                                    str4 = (cls6 == null || (canonicalName2 = cls6.getCanonicalName()) == null) ? str : canonicalName2;
                                } else {
                                    str4 = substringAfterLast$default6;
                                }
                                Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str4);
                                if (matcher6.find()) {
                                    str4 = matcher6.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str4 = str4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str4, "Already initialized. ");
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        if (this.isInitialized) {
        }
    }
}
