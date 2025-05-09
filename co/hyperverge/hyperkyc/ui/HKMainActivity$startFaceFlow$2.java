package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startFaceFlow$2", f = "HKMainActivity.kt", i = {0}, l = {1599}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class HKMainActivity$startFaceFlow$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ WorkflowUIState.FaceCapture $faceFlowUIState;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startFaceFlow$2(HKMainActivity hKMainActivity, WorkflowUIState.FaceCapture faceCapture, Continuation<? super HKMainActivity$startFaceFlow$2> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
        this.$faceFlowUIState = faceCapture;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$startFaceFlow$2 hKMainActivity$startFaceFlow$2 = new HKMainActivity$startFaceFlow$2(this.this$0, this.$faceFlowUIState, continuation);
        hKMainActivity$startFaceFlow$2.L$0 = obj;
        return hKMainActivity$startFaceFlow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Unit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Unit>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((HKMainActivity$startFaceFlow$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r42) {
        /*
            Method dump skipped, instructions count: 4667
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: co.hyperverge.hyperkyc.ui.HKMainActivity$startFaceFlow$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1$failWithError(HKMainActivity hKMainActivity, HyperKycData.FaceData faceData) {
        HKMainActivity.processHVBridgeError$default(hKMainActivity, "startFaceFlow", new Throwable(HyperKycData.FaceData.errorMessage$default(faceData, null, 1, null)), 104, null, 8, null);
    }
}
