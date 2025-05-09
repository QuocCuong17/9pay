package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.databinding.HkFragmentLoadingBinding;
import co.hyperverge.hyperkyc.ui.models.LoadingUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoadingFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.LoadingFragment$initViews$2$1$1", f = "LoadingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class LoadingFragment$initViews$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HkFragmentLoadingBinding $this_with;
    int label;
    final /* synthetic */ LoadingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingFragment$initViews$2$1$1(HkFragmentLoadingBinding hkFragmentLoadingBinding, LoadingFragment loadingFragment, Continuation<? super LoadingFragment$initViews$2$1$1> continuation) {
        super(2, continuation);
        this.$this_with = hkFragmentLoadingBinding;
        this.this$0 = loadingFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LoadingFragment$initViews$2$1$1(this.$this_with, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoadingFragment$initViews$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MainVM mainVM;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$this_with.lottieAnimView.removeAllUpdateListeners();
        mainVM = this.this$0.getMainVM();
        mainVM.getLoadingUIStateFlow().setValue(LoadingUIState.Idle.INSTANCE);
        return Unit.INSTANCE;
    }
}
