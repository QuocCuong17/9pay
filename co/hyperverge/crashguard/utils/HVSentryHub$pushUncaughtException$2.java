package co.hyperverge.crashguard.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2;
import co.hyperverge.hyperlogger.HyperLogger;
import io.sentry.Attachment;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.Hub;
import io.sentry.IHub;
import io.sentry.Scope;
import io.sentry.ScopeCallback;
import io.sentry.protocol.SentryId;
import java.io.File;
import java.util.Queue;
import java.util.regex.Matcher;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HVSentryHub.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2", f = "HVSentryHub.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HVSentryHub$pushUncaughtException$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
    final /* synthetic */ Throwable $e;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVSentryHub$pushUncaughtException$2(Throwable th, Continuation<? super HVSentryHub$pushUncaughtException$2> continuation) {
        super(2, continuation);
        this.$e = th;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HVSentryHub$pushUncaughtException$2 hVSentryHub$pushUncaughtException$2 = new HVSentryHub$pushUncaughtException$2(this.$e, continuation);
        hVSentryHub$pushUncaughtException$2.L$0 = obj;
        return hVSentryHub$pushUncaughtException$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
        return ((HVSentryHub$pushUncaughtException$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HVSentryHub.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lio/sentry/protocol/SentryId;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2$1", f = "HVSentryHub.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SentryId>, Object> {
        final /* synthetic */ Throwable $e;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Throwable th, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$e = th;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$e, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SentryId> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:112:0x044d  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x0172  */
        /* JADX WARN: Removed duplicated region for block: B:127:0x017b  */
        /* JADX WARN: Removed duplicated region for block: B:197:0x0406  */
        /* JADX WARN: Removed duplicated region for block: B:205:0x034e  */
        /* JADX WARN: Removed duplicated region for block: B:208:0x0357  */
        /* JADX WARN: Removed duplicated region for block: B:236:0x03fc  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0238  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0462 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0486  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0495  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x04b2  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x05c5  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x05cb  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x04d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            IHub iHub;
            CharSequence charSequence;
            String str;
            String className;
            String str2;
            String str3;
            String substringAfterLast$default;
            String str4;
            Object m1202constructorimpl;
            String str5;
            String str6;
            String className2;
            Class<?> cls;
            Object invoke;
            Hub hub;
            AnonymousClass1 anonymousClass1;
            Class<?> cls2;
            boolean z;
            StackTraceElement stackTraceElement;
            String className3;
            String str7;
            String str8;
            String substringAfterLast$default2;
            Matcher matcher;
            String stringPlus;
            Object m1202constructorimpl2;
            String className4;
            IHub iHub2;
            String substringAfterLast$default3;
            String str9;
            Class<?> cls3;
            String canonicalName;
            Object invoke2;
            IHub iHub3;
            AnonymousClass1 anonymousClass12;
            Class<?> cls4;
            String className5;
            String str10;
            String str11;
            String str12;
            String substringAfterLast$default4;
            Object m1202constructorimpl3;
            String className6;
            Class<?> cls5;
            Object invoke3;
            Hub hub2;
            Class<?> cls6;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            iHub = HVSentryHub.clientHub;
            if (iHub == null) {
                Intrinsics.throwUninitializedPropertyAccessException("clientHub");
                iHub = null;
            }
            iHub.withScope(new ScopeCallback() { // from class: co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2$1$$ExternalSyntheticLambda0
                @Override // io.sentry.ScopeCallback
                public final void run(Scope scope) {
                    HVSentryHub$pushUncaughtException$2.AnonymousClass1.m394invokeSuspend$lambda1(scope);
                }
            });
            HVSentryHub.INSTANCE.enableScreenshotAttachment(true);
            File currentLogZipFile = HyperLogger.INSTANCE.getInstance().getCurrentLogZipFile();
            String path = currentLogZipFile == null ? null : currentLogZipFile.getPath();
            String str13 = "N/A";
            if (path != null) {
                Throwable th = this.$e;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                charSequence = "co.hyperverge";
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                if (stackTraceElement2 == null || (className5 = stackTraceElement2.getClassName()) == null) {
                    str10 = "Throwable().stackTrace";
                    str11 = "null cannot be cast to non-null type android.app.Application";
                    str12 = "getInitialApplication";
                    substringAfterLast$default4 = null;
                } else {
                    str10 = "Throwable().stackTrace";
                    str11 = "null cannot be cast to non-null type android.app.Application";
                    str12 = "getInitialApplication";
                    substringAfterLast$default4 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                if (substringAfterLast$default4 == null && (coroutineScope == null || (cls6 = coroutineScope.getClass()) == null || (substringAfterLast$default4 = cls6.getCanonicalName()) == null)) {
                    substringAfterLast$default4 = "N/A";
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default4);
                if (matcher2.find()) {
                    substringAfterLast$default4 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default4, "replaceAll(\"\")");
                }
                if (substringAfterLast$default4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    substringAfterLast$default4 = substringAfterLast$default4.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default4);
                sb.append(" - ");
                String str14 = "pushUncaughtException: capturing exception: " + th + " and log path: " + ((Object) path);
                if (str14 == null) {
                    str14 = "null ";
                }
                sb.append(str14);
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (LogExtsKt.isRelease()) {
                    str5 = str10;
                    str4 = str11;
                    str = str12;
                } else {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        str = str12;
                        try {
                            invoke3 = Class.forName("android.app.AppGlobals").getMethod(str, new Class[0]).invoke(null, new Object[0]);
                        } catch (Throwable th2) {
                            th = th2;
                            str4 = str11;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        str4 = str11;
                        str = str12;
                    }
                    if (invoke3 != null) {
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        str4 = str11;
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName = (String) m1202constructorimpl3;
                        if (LogExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str5 = str10;
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str5);
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                String substringAfterLast$default5 = (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default5 == null && (coroutineScope == null || (cls5 = coroutineScope.getClass()) == null || (substringAfterLast$default5 = cls5.getCanonicalName()) == null)) {
                                    substringAfterLast$default5 = "N/A";
                                }
                                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default5);
                                if (matcher3.find()) {
                                    substringAfterLast$default5 = matcher3.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default5, "replaceAll(\"\")");
                                }
                                if (substringAfterLast$default5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    substringAfterLast$default5 = substringAfterLast$default5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb2 = new StringBuilder();
                                String str15 = "pushUncaughtException: capturing exception: " + th + " and log path: " + ((Object) path);
                                if (str15 == null) {
                                    str15 = "null ";
                                }
                                sb2.append(str15);
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(3, substringAfterLast$default5, sb2.toString());
                            }
                        }
                        str5 = str10;
                    } else {
                        str4 = str11;
                        try {
                            throw new NullPointerException(str4);
                        } catch (Throwable th4) {
                            th = th4;
                            Result.Companion companion3 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            }
                            String packageName2 = (String) m1202constructorimpl3;
                            if (LogExtsKt.isDebug()) {
                            }
                            str5 = str10;
                            hub2 = HVSentryHub.hub;
                            if (hub2 == null) {
                            }
                            hub2.captureException(this.$e, Hint.withAttachment(new Attachment(path)));
                            anonymousClass1 = this;
                            str6 = "packageName";
                            HVSentryHub hVSentryHub = HVSentryHub.INSTANCE;
                            z = HVSentryHub.clientHubScreenshotConfig;
                            hVSentryHub.enableScreenshotAttachment(z);
                            HVSentryHub.INSTANCE.enableMainHubDeduplication(false);
                            Throwable th5 = anonymousClass1.$e;
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb3 = new StringBuilder();
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, str5);
                            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            if (stackTraceElement == null) {
                                str7 = str6;
                                str8 = str5;
                                substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default2 == null) {
                                    substringAfterLast$default2 = "N/A";
                                }
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                                if (matcher.find()) {
                                }
                                if (substringAfterLast$default2.length() > 23) {
                                    substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                sb3.append(substringAfterLast$default2);
                                sb3.append(" - ");
                                stringPlus = Intrinsics.stringPlus("pushUncaughtException: mainHub capturing exception: ", th5);
                                if (stringPlus == null) {
                                }
                                sb3.append(stringPlus);
                                sb3.append(' ');
                                sb3.append("");
                                companion4.log(level2, sb3.toString());
                                if (!LogExtsKt.isRelease()) {
                                }
                                iHub2 = null;
                                iHub3 = HVSentryHub.clientHub;
                                if (iHub3 == null) {
                                }
                                return iHub2.captureException(anonymousClass12.$e);
                            }
                            str7 = str6;
                            str8 = str5;
                            substringAfterLast$default2 = null;
                            if (substringAfterLast$default2 == null) {
                            }
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                            if (matcher.find()) {
                            }
                            if (substringAfterLast$default2.length() > 23) {
                            }
                            sb3.append(substringAfterLast$default2);
                            sb3.append(" - ");
                            stringPlus = Intrinsics.stringPlus("pushUncaughtException: mainHub capturing exception: ", th5);
                            if (stringPlus == null) {
                            }
                            sb3.append(stringPlus);
                            sb3.append(' ');
                            sb3.append("");
                            companion4.log(level2, sb3.toString());
                            if (!LogExtsKt.isRelease()) {
                            }
                            iHub2 = null;
                            iHub3 = HVSentryHub.clientHub;
                            if (iHub3 == null) {
                            }
                            return iHub2.captureException(anonymousClass12.$e);
                        }
                    }
                }
                hub2 = HVSentryHub.hub;
                if (hub2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("hub");
                    hub2 = null;
                }
                hub2.captureException(this.$e, Hint.withAttachment(new Attachment(path)));
                anonymousClass1 = this;
                str6 = "packageName";
            } else {
                charSequence = "co.hyperverge";
                str = "getInitialApplication";
                Throwable th6 = this.$e;
                HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb4 = new StringBuilder();
                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                    str2 = "null cannot be cast to non-null type android.app.Application";
                    str3 = "Throwable().stackTrace";
                    substringAfterLast$default = null;
                } else {
                    str2 = "null cannot be cast to non-null type android.app.Application";
                    str3 = "Throwable().stackTrace";
                    substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                if (substringAfterLast$default == null && (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
                    substringAfterLast$default = "N/A";
                }
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher4.find()) {
                    substringAfterLast$default = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                }
                if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                sb4.append(substringAfterLast$default);
                sb4.append(" - ");
                String stringPlus2 = Intrinsics.stringPlus("pushUncaughtException: capturing exception: ", th6);
                if (stringPlus2 == null) {
                    stringPlus2 = "null ";
                }
                sb4.append(stringPlus2);
                sb4.append(' ');
                sb4.append("");
                companion5.log(level3, sb4.toString());
                if (LogExtsKt.isRelease()) {
                    str5 = str3;
                    str6 = "packageName";
                    str4 = str2;
                } else {
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        invoke = Class.forName("android.app.AppGlobals").getMethod(str, new Class[0]).invoke(null, new Object[0]);
                    } catch (Throwable th7) {
                        th = th7;
                        str4 = str2;
                    }
                    if (invoke != null) {
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        str4 = str2;
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            m1202constructorimpl = "";
                        }
                        String str16 = (String) m1202constructorimpl;
                        if (LogExtsKt.isDebug()) {
                            str5 = str3;
                            str6 = "packageName";
                        } else {
                            str6 = "packageName";
                            Intrinsics.checkNotNullExpressionValue(str16, str6);
                            if (StringsKt.contains$default((CharSequence) str16, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                str5 = str3;
                                Intrinsics.checkNotNullExpressionValue(stackTrace5, str5);
                                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                String substringAfterLast$default6 = (stackTraceElement5 == null || (className2 = stackTraceElement5.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default6 == null && (coroutineScope == null || (cls = coroutineScope.getClass()) == null || (substringAfterLast$default6 = cls.getCanonicalName()) == null)) {
                                    substringAfterLast$default6 = "N/A";
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default6);
                                if (matcher5.find()) {
                                    substringAfterLast$default6 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default6, "replaceAll(\"\")");
                                }
                                if (substringAfterLast$default6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    substringAfterLast$default6 = substringAfterLast$default6.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb5 = new StringBuilder();
                                String stringPlus3 = Intrinsics.stringPlus("pushUncaughtException: capturing exception: ", th6);
                                if (stringPlus3 == null) {
                                    stringPlus3 = "null ";
                                }
                                sb5.append(stringPlus3);
                                sb5.append(' ');
                                sb5.append("");
                                Log.println(3, substringAfterLast$default6, sb5.toString());
                            } else {
                                str5 = str3;
                            }
                        }
                    } else {
                        str4 = str2;
                        try {
                            throw new NullPointerException(str4);
                        } catch (Throwable th8) {
                            th = th8;
                            Result.Companion companion7 = Result.INSTANCE;
                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            }
                            String str162 = (String) m1202constructorimpl;
                            if (LogExtsKt.isDebug()) {
                            }
                            hub = HVSentryHub.hub;
                            if (hub == null) {
                            }
                            anonymousClass1 = this;
                            hub.captureException(anonymousClass1.$e);
                            HVSentryHub hVSentryHub2 = HVSentryHub.INSTANCE;
                            z = HVSentryHub.clientHubScreenshotConfig;
                            hVSentryHub2.enableScreenshotAttachment(z);
                            HVSentryHub.INSTANCE.enableMainHubDeduplication(false);
                            Throwable th52 = anonymousClass1.$e;
                            HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
                            HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb32 = new StringBuilder();
                            StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace32, str5);
                            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
                            if (stackTraceElement == null) {
                            }
                            str7 = str6;
                            str8 = str5;
                            substringAfterLast$default2 = null;
                            if (substringAfterLast$default2 == null) {
                            }
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                            if (matcher.find()) {
                            }
                            if (substringAfterLast$default2.length() > 23) {
                            }
                            sb32.append(substringAfterLast$default2);
                            sb32.append(" - ");
                            stringPlus = Intrinsics.stringPlus("pushUncaughtException: mainHub capturing exception: ", th52);
                            if (stringPlus == null) {
                            }
                            sb32.append(stringPlus);
                            sb32.append(' ');
                            sb32.append("");
                            companion42.log(level22, sb32.toString());
                            if (!LogExtsKt.isRelease()) {
                            }
                            iHub2 = null;
                            iHub3 = HVSentryHub.clientHub;
                            if (iHub3 == null) {
                            }
                            return iHub2.captureException(anonymousClass12.$e);
                        }
                    }
                }
                hub = HVSentryHub.hub;
                if (hub == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("hub");
                    hub = null;
                }
                anonymousClass1 = this;
                hub.captureException(anonymousClass1.$e);
            }
            HVSentryHub hVSentryHub22 = HVSentryHub.INSTANCE;
            z = HVSentryHub.clientHubScreenshotConfig;
            hVSentryHub22.enableScreenshotAttachment(z);
            HVSentryHub.INSTANCE.enableMainHubDeduplication(false);
            Throwable th522 = anonymousClass1.$e;
            HyperLogger.Level level222 = HyperLogger.Level.DEBUG;
            HyperLogger companion422 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb322 = new StringBuilder();
            StackTraceElement[] stackTrace322 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace322, str5);
            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace322);
            if (stackTraceElement == null || (className3 = stackTraceElement.getClassName()) == null) {
                str7 = str6;
                str8 = str5;
                substringAfterLast$default2 = null;
            } else {
                str7 = str6;
                str8 = str5;
                substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            if (substringAfterLast$default2 == null && (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null || (substringAfterLast$default2 = cls4.getCanonicalName()) == null)) {
                substringAfterLast$default2 = "N/A";
            }
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
            if (matcher.find()) {
                substringAfterLast$default2 = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
            }
            if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            sb322.append(substringAfterLast$default2);
            sb322.append(" - ");
            stringPlus = Intrinsics.stringPlus("pushUncaughtException: mainHub capturing exception: ", th522);
            if (stringPlus == null) {
                stringPlus = "null ";
            }
            sb322.append(stringPlus);
            sb322.append(' ');
            sb322.append("");
            companion422.log(level222, sb322.toString());
            if (!LogExtsKt.isRelease()) {
                try {
                    Result.Companion companion8 = Result.INSTANCE;
                    invoke2 = Class.forName("android.app.AppGlobals").getMethod(str, new Class[0]).invoke(null, new Object[0]);
                } catch (Throwable th9) {
                    Result.Companion companion9 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th9));
                }
                if (invoke2 != null) {
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String str17 = (String) m1202constructorimpl2;
                    if (LogExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str17, str7);
                        if (StringsKt.contains$default((CharSequence) str17, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str8);
                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                            if (stackTraceElement6 == null || (className4 = stackTraceElement6.getClassName()) == null) {
                                iHub2 = null;
                                substringAfterLast$default3 = null;
                            } else {
                                iHub2 = null;
                                substringAfterLast$default3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            if (substringAfterLast$default3 != null) {
                                str13 = substringAfterLast$default3;
                            } else if (coroutineScope != null && (cls3 = coroutineScope.getClass()) != null && (canonicalName = cls3.getCanonicalName()) != null) {
                                str13 = canonicalName;
                            }
                            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str13);
                            if (matcher6.find()) {
                                str9 = matcher6.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                            } else {
                                str9 = str13;
                            }
                            if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str9 = str9.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str9, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb6 = new StringBuilder();
                            String stringPlus4 = Intrinsics.stringPlus("pushUncaughtException: mainHub capturing exception: ", th522);
                            if (stringPlus4 == null) {
                                stringPlus4 = "null ";
                            }
                            sb6.append(stringPlus4);
                            sb6.append(' ');
                            sb6.append("");
                            Log.println(3, str9, sb6.toString());
                            iHub3 = HVSentryHub.clientHub;
                            if (iHub3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("clientHub");
                                anonymousClass12 = this;
                            } else {
                                anonymousClass12 = this;
                                iHub2 = iHub3;
                            }
                            return iHub2.captureException(anonymousClass12.$e);
                        }
                    }
                } else {
                    throw new NullPointerException(str4);
                }
            }
            iHub2 = null;
            iHub3 = HVSentryHub.clientHub;
            if (iHub3 == null) {
            }
            return iHub2.captureException(anonymousClass12.$e);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-1, reason: not valid java name */
        public static final void m394invokeSuspend$lambda1(Scope scope) {
            Hub hub;
            Queue<Breadcrumb> breadcrumbs = scope.getBreadcrumbs();
            Intrinsics.checkNotNullExpressionValue(breadcrumbs, "it.breadcrumbs");
            for (Breadcrumb breadcrumb : breadcrumbs) {
                hub = HVSentryHub.hub;
                if (hub == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("hub");
                    hub = null;
                }
                hub.addBreadcrumb(breadcrumb);
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Deferred async$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass1(this.$e, null), 3, null);
            async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass2(null), 3, null);
            this.label = 1;
            if (async$default.await(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HVSentryHub.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2$2", f = "HVSentryHub.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.crashguard.utils.HVSentryHub$pushUncaughtException$2$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z;
            String className;
            Object m1202constructorimpl;
            Class<?> cls;
            String canonicalName;
            String className2;
            Object invoke;
            Class<?> cls2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            HVSentryHub hVSentryHub = HVSentryHub.INSTANCE;
            z = HVSentryHub.clientHubDeduplicationConfig;
            hVSentryHub.enableMainHubDeduplication(z);
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str = null;
            String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            String str2 = "N/A";
            if (substringAfterLast$default == null && (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null || (substringAfterLast$default = cls2.getCanonicalName()) == null)) {
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
            sb.append("pushUncaughtException: exiting process");
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
                            if (str != null) {
                                str2 = str;
                            } else if (coroutineScope != null && (cls = coroutineScope.getClass()) != null && (canonicalName = cls.getCanonicalName()) != null) {
                                str2 = canonicalName;
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
                            Log.println(3, str2, "pushUncaughtException: exiting process ");
                        }
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                }
            }
            System.exit(2);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
    }
}
