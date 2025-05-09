package co.hyperverge.hyperkyc.webCore.ui;

import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import kotlin.Metadata;
import kotlin.Result;
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
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$JSInterface$executeNativeModule$3", f = "HKWebCoreActivity.kt", i = {}, l = {483}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HKWebCoreActivity$JSInterface$executeNativeModule$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WorkflowUIState.FaceCapture $faceUiState;
    int label;
    final /* synthetic */ HKWebCoreActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKWebCoreActivity$JSInterface$executeNativeModule$3(HKWebCoreActivity hKWebCoreActivity, WorkflowUIState.FaceCapture faceCapture, Continuation<? super HKWebCoreActivity$JSInterface$executeNativeModule$3> continuation) {
        super(2, continuation);
        this.this$0 = hKWebCoreActivity;
        this.$faceUiState = faceCapture;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKWebCoreActivity$JSInterface$executeNativeModule$3(this.this$0, this.$faceUiState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKWebCoreActivity$JSInterface$executeNativeModule$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m443startFaceFlowgIAlus;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HKWebCoreActivity hKWebCoreActivity = this.this$0;
            WorkflowUIState.FaceCapture faceUiState = this.$faceUiState;
            Intrinsics.checkNotNullExpressionValue(faceUiState, "faceUiState");
            this.label = 1;
            m443startFaceFlowgIAlus = hKWebCoreActivity.m443startFaceFlowgIAlus(faceUiState, this);
            if (m443startFaceFlowgIAlus == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((Result) obj).getValue();
        }
        return Unit.INSTANCE;
    }
}
