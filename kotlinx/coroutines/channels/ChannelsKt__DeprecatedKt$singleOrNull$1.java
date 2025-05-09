package kotlinx.coroutines.channels;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Deprecated.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA}, m = "singleOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", WorkflowModule.Properties.Section.Component.SelectionMode.SINGLE}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class ChannelsKt__DeprecatedKt$singleOrNull$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$singleOrNull$1(Continuation<? super ChannelsKt__DeprecatedKt$singleOrNull$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object singleOrNull;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        singleOrNull = ChannelsKt__DeprecatedKt.singleOrNull(null, this);
        return singleOrNull;
    }
}
