package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.data.models.StartTransaction;
import co.hyperverge.hyperkyc.databinding.HkActivityWorkflowLoadingBinding;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.Response;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WorkflowLoadingActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.WorkflowLoadingActivity$performStartTransaction$2$1", f = "WorkflowLoadingActivity.kt", i = {}, l = {CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class WorkflowLoadingActivity$performStartTransaction$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ StartTransaction $startTransactionData;
    final /* synthetic */ HkActivityWorkflowLoadingBinding $this_with;
    int label;
    final /* synthetic */ WorkflowLoadingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkflowLoadingActivity$performStartTransaction$2$1(WorkflowLoadingActivity workflowLoadingActivity, StartTransaction startTransaction, HkActivityWorkflowLoadingBinding hkActivityWorkflowLoadingBinding, Continuation<? super WorkflowLoadingActivity$performStartTransaction$2$1> continuation) {
        super(2, continuation);
        this.this$0 = workflowLoadingActivity;
        this.$startTransactionData = startTransaction;
        this.$this_with = hkActivityWorkflowLoadingBinding;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WorkflowLoadingActivity$performStartTransaction$2$1(this.this$0, this.$startTransactionData, this.$this_with, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WorkflowLoadingActivity$performStartTransaction$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Map defaultHeaders;
        Map authHeaders;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainVM mainVM = this.this$0.getMainVM();
            StartTransaction startTransaction = this.$startTransactionData;
            defaultHeaders = super/*co.hyperverge.hyperkyc.ui.BaseActivity*/.getDefaultHeaders();
            authHeaders = super/*co.hyperverge.hyperkyc.ui.BaseActivity*/.getAuthHeaders();
            Flow<NetworkUIState<Result<Response>>> performStartTransactionApiCall = mainVM.performStartTransactionApiCall(startTransaction, MapsKt.plus(defaultHeaders, authHeaders));
            final HkActivityWorkflowLoadingBinding hkActivityWorkflowLoadingBinding = this.$this_with;
            this.label = 1;
            if (performStartTransactionApiCall.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.WorkflowLoadingActivity$performStartTransaction$2$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetworkUIState<Result<Response>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetworkUIState<Result<Response>> networkUIState, Continuation<? super Unit> continuation) {
                    if (networkUIState instanceof NetworkUIState.Loading) {
                        LottieAnimationView lavWorkflowLoader = HkActivityWorkflowLoadingBinding.this.lavWorkflowLoader;
                        Intrinsics.checkNotNullExpressionValue(lavWorkflowLoader, "lavWorkflowLoader");
                        lavWorkflowLoader.setVisibility(0);
                    } else if (networkUIState instanceof NetworkUIState.Failed) {
                        LottieAnimationView lavWorkflowLoader2 = HkActivityWorkflowLoadingBinding.this.lavWorkflowLoader;
                        Intrinsics.checkNotNullExpressionValue(lavWorkflowLoader2, "lavWorkflowLoader");
                        lavWorkflowLoader2.setVisibility(8);
                    } else if (!(networkUIState instanceof NetworkUIState.Success)) {
                        Intrinsics.areEqual(networkUIState, NetworkUIState.NetworkFailure.INSTANCE);
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
}
