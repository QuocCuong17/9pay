package co.hyperverge.hyperkyc.ui.nfc;

import co.hyperverge.hyperkyc.R;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WebCoreNFCReaderFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.nfc.WebCoreNFCReaderFragment$cardDisconnected$1", f = "WebCoreNFCReaderFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class WebCoreNFCReaderFragment$cardDisconnected$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WebCoreNFCReaderFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebCoreNFCReaderFragment$cardDisconnected$1(WebCoreNFCReaderFragment webCoreNFCReaderFragment, Continuation<? super WebCoreNFCReaderFragment$cardDisconnected$1> continuation) {
        super(2, continuation);
        this.this$0 = webCoreNFCReaderFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WebCoreNFCReaderFragment$cardDisconnected$1 webCoreNFCReaderFragment$cardDisconnected$1 = new WebCoreNFCReaderFragment$cardDisconnected$1(this.this$0, continuation);
        webCoreNFCReaderFragment$cardDisconnected$1.L$0 = obj;
        return webCoreNFCReaderFragment$cardDisconnected$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WebCoreNFCReaderFragment$cardDisconnected$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        WebCoreNFCReaderFragment webCoreNFCReaderFragment = this.this$0;
        webCoreNFCReaderFragment.errorMessage = webCoreNFCReaderFragment.getString(R.string.hk_error_connection);
        this.this$0.errorCode = 122;
        nFCReaderVM = this.this$0.nfcVM;
        if (nFCReaderVM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
            nFCReaderVM = null;
        }
        nFCReaderVM.onCardDisconnected();
        this.this$0.processErrorState();
        return Unit.INSTANCE;
    }
}
