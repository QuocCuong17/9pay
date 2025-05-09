package co.hyperverge.hyperkyc.data.network;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkHelpers.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkHelpersKt", f = "NetworkHelpers.kt", i = {}, l = {39}, m = "apiGet", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkHelpersKt$apiGet$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetworkHelpersKt$apiGet$1(Continuation<? super NetworkHelpersKt$apiGet$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object apiGet = NetworkHelpersKt.apiGet(null, null, null, null, null, null, this);
        return apiGet == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? apiGet : Result.m1201boximpl(apiGet);
    }
}
