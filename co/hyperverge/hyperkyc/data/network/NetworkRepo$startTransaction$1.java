package co.hyperverge.hyperkyc.data.network;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo", f = "NetworkRepo.kt", i = {}, l = {652}, m = "startTransaction-BWLJW6A$hyperkyc_release", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkRepo$startTransaction$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NetworkRepo this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$startTransaction$1(NetworkRepo networkRepo, Continuation<? super NetworkRepo$startTransaction$1> continuation) {
        super(continuation);
        this.this$0 = networkRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object m400startTransactionBWLJW6A$hyperkyc_release = this.this$0.m400startTransactionBWLJW6A$hyperkyc_release(null, null, null, this);
        return m400startTransactionBWLJW6A$hyperkyc_release == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m400startTransactionBWLJW6A$hyperkyc_release : Result.m1201boximpl(m400startTransactionBWLJW6A$hyperkyc_release);
    }
}
