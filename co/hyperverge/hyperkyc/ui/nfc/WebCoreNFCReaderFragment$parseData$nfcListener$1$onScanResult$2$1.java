package co.hyperverge.hyperkyc.ui.nfc;

import co.hyperverge.hyperkyc.ui.nfc.NFCUIFlowModel;
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
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: WebCoreNFCReaderFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.nfc.WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1", f = "WebCoreNFCReaderFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WebCoreNFCReaderFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1(WebCoreNFCReaderFragment webCoreNFCReaderFragment, Continuation<? super WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1> continuation) {
        super(2, continuation);
        this.this$0 = webCoreNFCReaderFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1 webCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1 = new WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1(this.this$0, continuation);
        webCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1.L$0 = obj;
        return webCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WebCoreNFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        NFCReaderVM nFCReaderVM;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScopeKt.ensureActive((CoroutineScope) this.L$0);
        nFCReaderVM = this.this$0.nfcVM;
        if (nFCReaderVM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
            nFCReaderVM = null;
        }
        nFCReaderVM.updateStatus(NFCUIFlowModel.NFCUIState.ConnectChip.INSTANCE, NFCUIFlowModel.NFCUIStatus.Failure);
        WebCoreNFCReaderFragment.sendResult$default(this.this$0, null, 1, null);
        return Unit.INSTANCE;
    }
}
