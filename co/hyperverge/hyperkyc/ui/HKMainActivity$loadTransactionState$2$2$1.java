package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.databinding.HkActivityMainBinding;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.airbnb.lottie.LottieAnimationView;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$loadTransactionState$2$2$1", f = "HKMainActivity.kt", i = {}, l = {885}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$loadTransactionState$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Continuation<TransactionStateResponse> $continuation;
    final /* synthetic */ HkActivityMainBinding $this_with;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HKMainActivity$loadTransactionState$2$2$1(HKMainActivity hKMainActivity, HkActivityMainBinding hkActivityMainBinding, Continuation<? super TransactionStateResponse> continuation, Continuation<? super HKMainActivity$loadTransactionState$2$2$1> continuation2) {
        super(2, continuation2);
        this.this$0 = hKMainActivity;
        this.$this_with = hkActivityMainBinding;
        this.$continuation = continuation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKMainActivity$loadTransactionState$2$2$1(this.this$0, this.$this_with, this.$continuation, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$loadTransactionState$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow m2735catch = FlowKt.m2735catch(this.this$0.getMainVM().fetchTransactionState$hyperkyc_release(this.this$0.getHyperKycConfig().getInputs(), this.this$0.getHyperKycConfig().getWorkflowConfig$hyperkyc_release().getHash()), new AnonymousClass1(null));
            final HKMainActivity hKMainActivity = this.this$0;
            final HkActivityMainBinding hkActivityMainBinding = this.$this_with;
            final Continuation<TransactionStateResponse> continuation = this.$continuation;
            this.label = 1;
            if (m2735catch.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$loadTransactionState$2$2$1.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation2) {
                    return emit((NetworkUIState<TransactionStateResponse>) obj2, (Continuation<? super Unit>) continuation2);
                }

                public final Object emit(NetworkUIState<TransactionStateResponse> networkUIState, Continuation<? super Unit> continuation2) {
                    if (networkUIState instanceof NetworkUIState.Loading) {
                        HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                        LottieAnimationView lavLoader = hkActivityMainBinding.lavLoader;
                        Intrinsics.checkNotNullExpressionValue(lavLoader, "lavLoader");
                        lavLoader.setVisibility(0);
                    } else {
                        if (networkUIState instanceof NetworkUIState.Failed ? true : networkUIState instanceof NetworkUIState.NetworkFailure) {
                            Continuation<TransactionStateResponse> continuation3 = continuation;
                            Result.Companion companion = Result.INSTANCE;
                            continuation3.resumeWith(Result.m1202constructorimpl(null));
                        } else if (networkUIState instanceof NetworkUIState.Success) {
                            Continuation<TransactionStateResponse> continuation4 = continuation;
                            Result.Companion companion2 = Result.INSTANCE;
                            continuation4.resumeWith(Result.m1202constructorimpl(((NetworkUIState.Success) networkUIState).getData()));
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
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HKMainActivity.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "Lco/hyperverge/hyperkyc/ui/custom/FetchStateUIState;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$loadTransactionState$2$2$1$1", f = "HKMainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$loadTransactionState$2$2$1$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetworkUIState<TransactionStateResponse>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetworkUIState<TransactionStateResponse>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = flowCollector;
            anonymousClass1.L$1 = th;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0191  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x01cc  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01d1  */
        /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r10v1 */
        /* JADX WARN: Type inference failed for: r10v2, types: [T] */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r8v10, types: [T] */
        /* JADX WARN: Type inference failed for: r8v27, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r8v29, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r8v30 */
        /* JADX WARN: Type inference failed for: r8v5 */
        /* JADX WARN: Type inference failed for: r8v6 */
        /* JADX WARN: Type inference failed for: r8v7 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ?? canonicalName;
            Class<?> cls;
            String str;
            String str2;
            Object m1202constructorimpl;
            String str3;
            Class<?> cls2;
            Matcher matcher;
            String str4;
            String localizedMessage;
            String className;
            String className2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Throwable th = (Throwable) this.L$1;
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r10 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? 0 : cls.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef.element = canonicalName;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            String str5 = "";
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str = (String) objectRef.element;
            } else {
                str = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str);
            sb.append(" - ");
            sb.append("loadTransactionState: ");
            sb.append(' ');
            String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str2 = '\n' + localizedMessage2;
            } else {
                str2 = "";
            }
            sb.append(str2);
            companion.log(level, sb.toString());
            CoreExtsKt.isRelease();
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
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            r10 = substringAfterLast$default;
                            objectRef2.element = r10;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                                ?? replaceAll2 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                objectRef2.element = replaceAll2;
                            }
                            if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                str4 = (String) objectRef2.element;
                            } else {
                                str4 = ((String) objectRef2.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("loadTransactionState: ");
                            sb2.append(' ');
                            localizedMessage = th == null ? th.getLocalizedMessage() : str3;
                            if (localizedMessage != null) {
                                str5 = '\n' + localizedMessage;
                            }
                            sb2.append(str5);
                            Log.println(6, str4, sb2.toString());
                        }
                    }
                    String canonicalName2 = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? str3 : cls2.getCanonicalName();
                    if (canonicalName2 != null) {
                        r10 = canonicalName2;
                    }
                    objectRef2.element = r10;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef2.element).length() > 23) {
                    }
                    str4 = (String) objectRef2.element;
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append("loadTransactionState: ");
                    sb22.append(' ');
                    if (th == null) {
                    }
                    if (localizedMessage != null) {
                    }
                    sb22.append(str5);
                    Log.println(6, str4, sb22.toString());
                }
            }
            return Unit.INSTANCE;
        }
    }
}
