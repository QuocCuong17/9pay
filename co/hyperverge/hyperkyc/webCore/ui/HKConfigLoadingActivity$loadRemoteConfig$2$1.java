package co.hyperverge.hyperkyc.webCore.ui;

import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.databinding.HkActivityWorkflowLoadingBinding;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKConfigLoadingActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.HKConfigLoadingActivity$loadRemoteConfig$2$1", f = "HKConfigLoadingActivity.kt", i = {}, l = {CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKConfigLoadingActivity$loadRemoteConfig$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HkActivityWorkflowLoadingBinding $this_with;
    int label;
    final /* synthetic */ HKConfigLoadingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKConfigLoadingActivity$loadRemoteConfig$2$1(HKConfigLoadingActivity hKConfigLoadingActivity, HkActivityWorkflowLoadingBinding hkActivityWorkflowLoadingBinding, Continuation<? super HKConfigLoadingActivity$loadRemoteConfig$2$1> continuation) {
        super(2, continuation);
        this.this$0 = hKConfigLoadingActivity;
        this.$this_with = hkActivityWorkflowLoadingBinding;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKConfigLoadingActivity$loadRemoteConfig$2$1(this.this$0, this.$this_with, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKConfigLoadingActivity$loadRemoteConfig$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MainVM mainVM;
        HyperKycConfig hyperKycConfig;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mainVM = this.this$0.getMainVM();
            hyperKycConfig = this.this$0.getHyperKycConfig();
            Flow<NetworkUIState<HSRemoteConfig>> fetchRemoteConfig = mainVM.fetchRemoteConfig(hyperKycConfig);
            final HKConfigLoadingActivity hKConfigLoadingActivity = this.this$0;
            final HkActivityWorkflowLoadingBinding hkActivityWorkflowLoadingBinding = this.$this_with;
            this.label = 1;
            if (fetchRemoteConfig.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKConfigLoadingActivity$loadRemoteConfig$2$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetworkUIState<HSRemoteConfig>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetworkUIState<HSRemoteConfig> networkUIState, Continuation<? super Unit> continuation) {
                    if (networkUIState instanceof NetworkUIState.Loading) {
                        HKConfigLoadingActivity.showRetry$default(HKConfigLoadingActivity.this, false, null, null, null, 14, null);
                        LottieAnimationView lavWorkflowLoader = hkActivityWorkflowLoadingBinding.lavWorkflowLoader;
                        Intrinsics.checkNotNullExpressionValue(lavWorkflowLoader, "lavWorkflowLoader");
                        lavWorkflowLoader.setVisibility(0);
                        MaterialButton btnCancel = hkActivityWorkflowLoadingBinding.btnCancel;
                        Intrinsics.checkNotNullExpressionValue(btnCancel, "btnCancel");
                        btnCancel.setVisibility(8);
                    } else {
                        if ((networkUIState instanceof NetworkUIState.Failed) || networkUIState == null) {
                            HKConfigLoadingActivity.showRetry$default(HKConfigLoadingActivity.this, false, null, null, null, 14, null);
                            HKConfigLoadingActivity.startFlow$default(HKConfigLoadingActivity.this, null, 1, null);
                        } else if (networkUIState instanceof NetworkUIState.Success) {
                            HKConfigLoadingActivity.showRetry$default(HKConfigLoadingActivity.this, false, null, null, null, 14, null);
                            HKConfigLoadingActivity.this.startFlow((HSRemoteConfig) ((NetworkUIState.Success) networkUIState).getData());
                        } else if (networkUIState instanceof NetworkUIState.NetworkFailure) {
                            LottieAnimationView lavWorkflowLoader2 = hkActivityWorkflowLoadingBinding.lavWorkflowLoader;
                            Intrinsics.checkNotNullExpressionValue(lavWorkflowLoader2, "lavWorkflowLoader");
                            lavWorkflowLoader2.setVisibility(8);
                            HKConfigLoadingActivity.showRetry$default(HKConfigLoadingActivity.this, true, null, Boxing.boxInt(111), HKConfigLoadingActivity.this.getString(R.string.hk_api_error_message), 2, null);
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
}
