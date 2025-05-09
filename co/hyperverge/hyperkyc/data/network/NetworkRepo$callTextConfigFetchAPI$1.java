package co.hyperverge.hyperkyc.data.network;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo", f = "NetworkRepo.kt", i = {0, 0, 0, 1, 1}, l = {BaselineTIFFTagSet.TAG_JPEG_POINT_TRANSFORMS, BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING}, m = "callTextConfigFetchAPI", n = {"this", "textConfigJson", "textConfigTypeToken", "this", "textConfigTypeToken"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class NetworkRepo$callTextConfigFetchAPI$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NetworkRepo this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$callTextConfigFetchAPI$1(NetworkRepo networkRepo, Continuation<? super NetworkRepo$callTextConfigFetchAPI$1> continuation) {
        super(continuation);
        this.this$0 = networkRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object callTextConfigFetchAPI;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        callTextConfigFetchAPI = this.this$0.callTextConfigFetchAPI(null, null, null, null, null, null, this);
        return callTextConfigFetchAPI;
    }
}
