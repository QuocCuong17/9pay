package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.bouncycastle.math.Primes;

/* JADX INFO: Add missing generic type declarations: [E] */
/* compiled from: Deprecated.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {Primes.SMALL_FACTOR_LIMIT, 212, 212}, m = "invokeSuspend", n = {"$this$produce", FirebaseAnalytics.Param.INDEX, "$this$produce", "e", FirebaseAnalytics.Param.INDEX, "$this$produce", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "I$0", "L$0", "L$2", "I$0", "L$0", "I$0"})
/* loaded from: classes5.dex */
final class ChannelsKt__DeprecatedKt$filterIndexed$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<Integer, E, Continuation<? super Boolean>, Object> $predicate;
    final /* synthetic */ ReceiveChannel<E> $this_filterIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChannelsKt__DeprecatedKt$filterIndexed$1(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3, Continuation<? super ChannelsKt__DeprecatedKt$filterIndexed$1> continuation) {
        super(2, continuation);
        this.$this_filterIndexed = receiveChannel;
        this.$predicate = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$filterIndexed$1 channelsKt__DeprecatedKt$filterIndexed$1 = new ChannelsKt__DeprecatedKt$filterIndexed$1(this.$this_filterIndexed, this.$predicate, continuation);
        channelsKt__DeprecatedKt$filterIndexed$1.L$0 = obj;
        return channelsKt__DeprecatedKt$filterIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$filterIndexed$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        r13 = r0;
        r0 = r1;
        r7 = r8;
        r1 = r10;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        ChannelIterator<E> it;
        ProducerScope producerScope;
        ProducerScope producerScope2;
        Object obj2;
        ChannelsKt__DeprecatedKt$filterIndexed$1<E> channelsKt__DeprecatedKt$filterIndexed$1;
        ChannelsKt__DeprecatedKt$filterIndexed$1<E> channelsKt__DeprecatedKt$filterIndexed$12;
        ChannelIterator<E> channelIterator;
        int i2;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            i = 0;
            it = this.$this_filterIndexed.iterator();
            producerScope = producerScope3;
        } else if (i3 == 1) {
            int i4 = this.I$0;
            ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$1;
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope2 = producerScope4;
            channelIterator = channelIterator2;
            i2 = i4;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$filterIndexed$1 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i3 == 2) {
            int i5 = this.I$0;
            Object obj3 = this.L$2;
            ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$1;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            int i6 = i5;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$filterIndexed$1 = this;
            ChannelIterator<E> channelIterator4 = channelIterator3;
            E e = obj3;
            it = channelIterator4;
            if (((Boolean) obj).booleanValue()) {
                channelsKt__DeprecatedKt$filterIndexed$1.L$0 = producerScope2;
                channelsKt__DeprecatedKt$filterIndexed$1.L$1 = it;
                channelsKt__DeprecatedKt$filterIndexed$1.L$2 = null;
                channelsKt__DeprecatedKt$filterIndexed$1.I$0 = i6;
                channelsKt__DeprecatedKt$filterIndexed$1.label = 3;
                if (producerScope2.send(e, channelsKt__DeprecatedKt$filterIndexed$1) == obj2) {
                    return obj2;
                }
            }
            channelsKt__DeprecatedKt$filterIndexed$12 = channelsKt__DeprecatedKt$filterIndexed$1;
            coroutine_suspended = obj2;
            producerScope = producerScope2;
            i = i6;
            channelsKt__DeprecatedKt$filterIndexed$12.L$0 = producerScope;
            channelsKt__DeprecatedKt$filterIndexed$12.L$1 = it;
            channelsKt__DeprecatedKt$filterIndexed$12.L$2 = null;
            channelsKt__DeprecatedKt$filterIndexed$12.I$0 = i;
            channelsKt__DeprecatedKt$filterIndexed$12.label = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$filterIndexed$12);
            if (hasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj4 = coroutine_suspended;
            channelsKt__DeprecatedKt$filterIndexed$1 = channelsKt__DeprecatedKt$filterIndexed$12;
            obj = hasNext;
            producerScope2 = producerScope;
            channelIterator = it;
            i2 = i;
            obj2 = obj4;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                Function3<Integer, E, Continuation<? super Boolean>, Object> function3 = channelsKt__DeprecatedKt$filterIndexed$1.$predicate;
                i6 = i2 + 1;
                Integer boxInt = Boxing.boxInt(i2);
                channelsKt__DeprecatedKt$filterIndexed$1.L$0 = producerScope2;
                channelsKt__DeprecatedKt$filterIndexed$1.L$1 = channelIterator;
                channelsKt__DeprecatedKt$filterIndexed$1.L$2 = next;
                channelsKt__DeprecatedKt$filterIndexed$1.I$0 = i6;
                channelsKt__DeprecatedKt$filterIndexed$1.label = 2;
                Object invoke = function3.invoke(boxInt, next, channelsKt__DeprecatedKt$filterIndexed$1);
                if (invoke == obj2) {
                    return obj2;
                }
                channelIterator4 = channelIterator;
                e = next;
                obj = invoke;
                it = channelIterator4;
                if (((Boolean) obj).booleanValue()) {
                }
                channelsKt__DeprecatedKt$filterIndexed$12 = channelsKt__DeprecatedKt$filterIndexed$1;
                coroutine_suspended = obj2;
                producerScope = producerScope2;
                i = i6;
                channelsKt__DeprecatedKt$filterIndexed$12.L$0 = producerScope;
                channelsKt__DeprecatedKt$filterIndexed$12.L$1 = it;
                channelsKt__DeprecatedKt$filterIndexed$12.L$2 = null;
                channelsKt__DeprecatedKt$filterIndexed$12.I$0 = i;
                channelsKt__DeprecatedKt$filterIndexed$12.label = 1;
                hasNext = it.hasNext(channelsKt__DeprecatedKt$filterIndexed$12);
                if (hasNext == coroutine_suspended) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            it = (ChannelIterator) this.L$1;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        channelsKt__DeprecatedKt$filterIndexed$12 = this;
        channelsKt__DeprecatedKt$filterIndexed$12.L$0 = producerScope;
        channelsKt__DeprecatedKt$filterIndexed$12.L$1 = it;
        channelsKt__DeprecatedKt$filterIndexed$12.L$2 = null;
        channelsKt__DeprecatedKt$filterIndexed$12.I$0 = i;
        channelsKt__DeprecatedKt$filterIndexed$12.label = 1;
        hasNext = it.hasNext(channelsKt__DeprecatedKt$filterIndexed$12);
        if (hasNext == coroutine_suspended) {
        }
    }
}
