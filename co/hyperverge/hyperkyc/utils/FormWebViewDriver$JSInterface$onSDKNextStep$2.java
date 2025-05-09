package co.hyperverge.hyperkyc.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* compiled from: FormWebViewDriver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.FormWebViewDriver$JSInterface$onSDKNextStep$2", f = "FormWebViewDriver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormWebViewDriver$JSInterface$onSDKNextStep$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isChild;
    final /* synthetic */ String $nextStep;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FormWebViewDriver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormWebViewDriver$JSInterface$onSDKNextStep$2(FormWebViewDriver formWebViewDriver, boolean z, String str, Continuation<? super FormWebViewDriver$JSInterface$onSDKNextStep$2> continuation) {
        super(2, continuation);
        this.this$0 = formWebViewDriver;
        this.$isChild = z;
        this.$nextStep = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FormWebViewDriver$JSInterface$onSDKNextStep$2 formWebViewDriver$JSInterface$onSDKNextStep$2 = new FormWebViewDriver$JSInterface$onSDKNextStep$2(this.this$0, this.$isChild, this.$nextStep, continuation);
        formWebViewDriver$JSInterface$onSDKNextStep$2.L$0 = obj;
        return formWebViewDriver$JSInterface$onSDKNextStep$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormWebViewDriver$JSInterface$onSDKNextStep$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String moduleDataJS;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        WebView webView$hyperkyc_release = this.this$0.getWebView$hyperkyc_release();
        moduleDataJS = this.this$0.getModuleDataJS();
        final FormWebViewDriver formWebViewDriver = this.this$0;
        final boolean z = this.$isChild;
        final String str = this.$nextStep;
        webView$hyperkyc_release.evaluateJavascript(moduleDataJS, new ValueCallback() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$JSInterface$onSDKNextStep$2$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj2) {
                FormWebViewDriver$JSInterface$onSDKNextStep$2.invokeSuspend$lambda$4(CoroutineScope.this, formWebViewDriver, z, str, (String) obj2);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$4(CoroutineScope coroutineScope, final FormWebViewDriver formWebViewDriver, boolean z, final String str, String dataJSON) {
        Object m1202constructorimpl;
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl2;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        WorkflowModule workflowModule;
        Object obj;
        String str2 = null;
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(dataJSON, "dataJSON");
            formWebViewDriver.updateFormData(dataJSON);
            if (z) {
                formWebViewDriver.getMainVM().getChildModuleDataCallbacks$hyperkyc_release().put(str, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$JSInterface$onSDKNextStep$2$1$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        FormWebViewDriver.setWebFormData$default(FormWebViewDriver.this, str, true, null, 4, null);
                    }
                });
            }
            List<WorkflowModule> modules = formWebViewDriver.getMainVM().getHyperKycConfig$hyperkyc_release().getWorkflowConfig$hyperkyc_release().getModules();
            if (modules != null) {
                Iterator<T> it = modules.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (Intrinsics.areEqual(((WorkflowModule) obj).getId(), formWebViewDriver.getMainVM().getCurrentModuleId$hyperkyc_release())) {
                            break;
                        }
                    }
                }
                workflowModule = (WorkflowModule) obj;
            } else {
                workflowModule = null;
            }
            if (workflowModule != null) {
                workflowModule.setNext(str);
            }
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(formWebViewDriver.getMainVM().flowForward()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? null : cls.getCanonicalName();
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
            String str4 = "onSDKNextStep(): " + m1205exceptionOrNullimpl;
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
            sb.append(' ');
            sb.append("");
            companion3.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        if (coroutineScope != null && (cls2 = coroutineScope.getClass()) != null) {
                            str2 = cls2.getCanonicalName();
                        }
                        if (str2 != null) {
                            str3 = str2;
                        }
                    } else {
                        str3 = substringAfterLast$default;
                    }
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
                    String str5 = "onSDKNextStep(): " + m1205exceptionOrNullimpl;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(6, str3, sb2.toString());
                }
            }
        }
    }
}
