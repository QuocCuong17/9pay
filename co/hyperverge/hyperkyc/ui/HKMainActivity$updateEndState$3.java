package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import co.hyperverge.hyperkyc.ui.models.LoadingUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$updateEndState$3", f = "HKMainActivity.kt", i = {}, l = {1988}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$updateEndState$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isAPISuccess;
    final /* synthetic */ boolean $isLoading;
    final /* synthetic */ Boolean $isSuccess;
    final /* synthetic */ Function0<Unit> $onFailAction;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$updateEndState$3(boolean z, HKMainActivity hKMainActivity, Boolean bool, boolean z2, Function0<Unit> function0, Continuation<? super HKMainActivity$updateEndState$3> continuation) {
        super(2, continuation);
        this.$isLoading = z;
        this.this$0 = hKMainActivity;
        this.$isSuccess = bool;
        this.$isAPISuccess = z2;
        this.$onFailAction = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$updateEndState$3 hKMainActivity$updateEndState$3 = new HKMainActivity$updateEndState$3(this.$isLoading, this.this$0, this.$isSuccess, this.$isAPISuccess, this.$onFailAction, continuation);
        hKMainActivity$updateEndState$3.L$0 = obj;
        return hKMainActivity$updateEndState$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$updateEndState$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (this.$isLoading) {
                this.this$0.getMainVM().getLoadingUIStateFlow().setValue(LoadingUIState.Processing.INSTANCE);
            } else {
                Boolean bool = this.$isSuccess;
                if (bool != null ? bool.booleanValue() : this.$isAPISuccess) {
                    this.this$0.getMainVM().getLoadingUIStateFlow().setValue(LoadingUIState.Success.INSTANCE);
                } else {
                    this.this$0.getMainVM().getLoadingUIStateFlow().setValue(LoadingUIState.Failure.INSTANCE);
                }
            }
            StateFlow asStateFlow = FlowKt.asStateFlow(this.this$0.getMainVM().getLoadingUIStateFlow());
            final boolean z = this.$isAPISuccess;
            final HKMainActivity hKMainActivity = this.this$0;
            final Function0<Unit> function0 = this.$onFailAction;
            this.label = 1;
            if (asStateFlow.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$updateEndState$3.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((LoadingUIState) obj2, (Continuation<? super Unit>) continuation);
                }

                /* JADX WARN: Code restructure failed: missing block: B:43:0x0145, code lost:
                
                    if (r0 != null) goto L57;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:48:0x0157, code lost:
                
                    if (r0 == null) goto L58;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:49:0x015b, code lost:
                
                    r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:50:0x016a, code lost:
                
                    if (r0.find() == false) goto L61;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:51:0x016c, code lost:
                
                    r8 = r0.replaceAll("");
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:53:0x0177, code lost:
                
                    if (r8.length() <= 23) goto L67;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:55:0x017d, code lost:
                
                    if (android.os.Build.VERSION.SDK_INT < 26) goto L66;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:56:0x0180, code lost:
                
                    r8 = r8.substring(0, 23);
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:57:0x0187, code lost:
                
                    r0 = new java.lang.StringBuilder();
                    r2 = "collect loadingUIStateFlow value : " + r18.getClass().getSimpleName() + ", isApiSuccess : " + r3;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:58:0x01a9, code lost:
                
                    if (r2 != null) goto L70;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:59:0x01ab, code lost:
                
                    r2 = "null ";
                 */
                /* JADX WARN: Code restructure failed: missing block: B:60:0x01ad, code lost:
                
                    r0.append(r2);
                    r0.append(' ');
                    r0.append("");
                    android.util.Log.println(3, r8, r0.toString());
                 */
                /* JADX WARN: Code restructure failed: missing block: B:62:0x015a, code lost:
                
                    r8 = r0;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object emit(LoadingUIState loadingUIState, Continuation<? super Unit> continuation) {
                    String canonicalName;
                    Class<?> cls;
                    Object m1202constructorimpl;
                    String str;
                    String str2;
                    Class<?> cls2;
                    String className;
                    String className2;
                    CoroutineScope coroutineScope2 = CoroutineScope.this;
                    boolean z2 = z;
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    String str3 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? null : cls.getCanonicalName();
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
                    String str4 = "collect loadingUIStateFlow value : " + loadingUIState.getClass().getSimpleName() + ", isApiSuccess : " + z2;
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
                                str2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? str : cls2.getCanonicalName();
                            }
                        }
                    }
                    if (Intrinsics.areEqual(loadingUIState, LoadingUIState.Idle.INSTANCE)) {
                        if (z) {
                            hKMainActivity.flowForwardOrFinish();
                        } else {
                            function0.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
