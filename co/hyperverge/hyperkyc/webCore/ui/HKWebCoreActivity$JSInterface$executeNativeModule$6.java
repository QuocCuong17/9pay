package co.hyperverge.hyperkyc.webCore.ui;

import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: HKWebCoreActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$JSInterface$executeNativeModule$6", f = "HKWebCoreActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HKWebCoreActivity$JSInterface$executeNativeModule$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WorkflowUIState.NFCReader $nfcUiState;
    int label;
    final /* synthetic */ HKWebCoreActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKWebCoreActivity$JSInterface$executeNativeModule$6(HKWebCoreActivity hKWebCoreActivity, WorkflowUIState.NFCReader nFCReader, Continuation<? super HKWebCoreActivity$JSInterface$executeNativeModule$6> continuation) {
        super(2, continuation);
        this.this$0 = hKWebCoreActivity;
        this.$nfcUiState = nFCReader;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKWebCoreActivity$JSInterface$executeNativeModule$6(this.this$0, this.$nfcUiState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKWebCoreActivity$JSInterface$executeNativeModule$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HKWebCoreActivity hKWebCoreActivity = this.this$0;
        WorkflowUIState.NFCReader nfcUiState = this.$nfcUiState;
        Intrinsics.checkNotNullExpressionValue(nfcUiState, "nfcUiState");
        hKWebCoreActivity.startNFCFlow(nfcUiState);
        return Unit.INSTANCE;
    }
}
