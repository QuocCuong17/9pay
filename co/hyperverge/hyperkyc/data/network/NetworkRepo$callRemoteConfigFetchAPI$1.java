package co.hyperverge.hyperkyc.data.network;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo", f = "NetworkRepo.kt", i = {}, l = {BaselineTIFFTagSet.TAG_Y_RESOLUTION}, m = "callRemoteConfigFetchAPI", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkRepo$callRemoteConfigFetchAPI$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NetworkRepo this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$callRemoteConfigFetchAPI$1(NetworkRepo networkRepo, Continuation<? super NetworkRepo$callRemoteConfigFetchAPI$1> continuation) {
        super(continuation);
        this.this$0 = networkRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object callRemoteConfigFetchAPI;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        callRemoteConfigFetchAPI = this.this$0.callRemoteConfigFetchAPI(null, null, null, this);
        return callRemoteConfigFetchAPI;
    }
}
