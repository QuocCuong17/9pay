package kotlinx.coroutines.flow;

import com.tekartik.sqflite.Constant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Reduce.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0, 0}, l = {CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384}, m = "first", n = {"predicate", Constant.PARAM_RESULT, "collector$iv"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class FlowKt__ReduceKt$first$3<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$first$3(Continuation<? super FlowKt__ReduceKt$first$3> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.first(null, null, this);
    }
}
