package co.hyperverge.hyperkyc.data.network;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo", f = "NetworkRepo.kt", i = {0}, l = {218, 225}, m = "callCountriesFetchAPI", n = {"countriesJson"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$callCountriesFetchAPI$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NetworkRepo this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$callCountriesFetchAPI$1(NetworkRepo networkRepo, Continuation<? super NetworkRepo$callCountriesFetchAPI$1> continuation) {
        super(continuation);
        this.this$0 = networkRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object callCountriesFetchAPI;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        callCountriesFetchAPI = this.this$0.callCountriesFetchAPI(null, null, null, this);
        return callCountriesFetchAPI;
    }
}
