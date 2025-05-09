package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.databinding.HkActivityMainBinding;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$loadTextConfigs$2$2$1", f = "HKMainActivity.kt", i = {}, l = {754}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$loadTextConfigs$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Continuation<Map<String, ? extends Map<String, ? extends Object>>> $continuation;
    final /* synthetic */ HkActivityMainBinding $this_with;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HKMainActivity$loadTextConfigs$2$2$1(HKMainActivity hKMainActivity, HkActivityMainBinding hkActivityMainBinding, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation, Continuation<? super HKMainActivity$loadTextConfigs$2$2$1> continuation2) {
        super(2, continuation2);
        this.this$0 = hKMainActivity;
        this.$this_with = hkActivityMainBinding;
        this.$continuation = continuation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKMainActivity$loadTextConfigs$2$2$1(this.this$0, this.$this_with, this.$continuation, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$loadTextConfigs$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<NetworkUIState<Map<String, Map<String, Object>>>> fetchTextConfigs = this.this$0.getMainVM().fetchTextConfigs();
            final HKMainActivity hKMainActivity = this.this$0;
            final HkActivityMainBinding hkActivityMainBinding = this.$this_with;
            final Continuation<Map<String, ? extends Map<String, ? extends Object>>> continuation = this.$continuation;
            this.label = 1;
            if (fetchTextConfigs.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$loadTextConfigs$2$2$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation2) {
                    return emit((NetworkUIState<? extends Map<String, ? extends Map<String, ? extends Object>>>) obj2, (Continuation<? super Unit>) continuation2);
                }

                public final Object emit(NetworkUIState<? extends Map<String, ? extends Map<String, ? extends Object>>> networkUIState, Continuation<? super Unit> continuation2) {
                    if (networkUIState instanceof NetworkUIState.Loading) {
                        HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                        LottieAnimationView lavLoader = hkActivityMainBinding.lavLoader;
                        Intrinsics.checkNotNullExpressionValue(lavLoader, "lavLoader");
                        lavLoader.setVisibility(0);
                    } else {
                        if (networkUIState instanceof NetworkUIState.Failed ? true : networkUIState instanceof NetworkUIState.NetworkFailure) {
                            Continuation<Map<String, ? extends Map<String, ? extends Object>>> continuation3 = continuation;
                            Result.Companion companion = Result.INSTANCE;
                            continuation3.resumeWith(Result.m1202constructorimpl(MapsKt.emptyMap()));
                        } else if (networkUIState instanceof NetworkUIState.Success) {
                            Continuation<Map<String, ? extends Map<String, ? extends Object>>> continuation4 = continuation;
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
}
