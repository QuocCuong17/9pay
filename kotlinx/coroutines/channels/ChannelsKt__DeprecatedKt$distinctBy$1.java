package kotlinx.coroutines.channels;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [E] */
/* compiled from: Deprecated.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "E", "K", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {387, 388, 390}, m = "invokeSuspend", n = {"$this$produce", UserMetadata.KEYDATA_FILENAME, "$this$produce", UserMetadata.KEYDATA_FILENAME, "e", "$this$produce", UserMetadata.KEYDATA_FILENAME, "k"}, s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
/* loaded from: classes5.dex */
public final class ChannelsKt__DeprecatedKt$distinctBy$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<E, Continuation<? super K>, Object> $selector;
    final /* synthetic */ ReceiveChannel<E> $this_distinctBy;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChannelsKt__DeprecatedKt$distinctBy$1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super ChannelsKt__DeprecatedKt$distinctBy$1> continuation) {
        super(2, continuation);
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$distinctBy$1 channelsKt__DeprecatedKt$distinctBy$1 = new ChannelsKt__DeprecatedKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__DeprecatedKt$distinctBy$1.L$0 = obj;
        return channelsKt__DeprecatedKt$distinctBy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$distinctBy$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0085 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00cb -> B:7:0x00d1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00db -> B:8:0x0071). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        ChannelsKt__DeprecatedKt$distinctBy$1<E> channelsKt__DeprecatedKt$distinctBy$1;
        HashSet hashSet;
        ChannelIterator<E> it;
        ProducerScope producerScope2;
        HashSet hashSet2;
        E e;
        ChannelIterator<E> channelIterator;
        Object obj2;
        ChannelsKt__DeprecatedKt$distinctBy$1<E> channelsKt__DeprecatedKt$distinctBy$12;
        ProducerScope producerScope3;
        HashSet hashSet3;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            HashSet hashSet4 = new HashSet();
            producerScope = producerScope4;
            channelsKt__DeprecatedKt$distinctBy$1 = this;
            hashSet = hashSet4;
            it = this.$this_distinctBy.iterator();
            channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$distinctBy$1.L$1 = hashSet;
            channelsKt__DeprecatedKt$distinctBy$1.L$2 = it;
            channelsKt__DeprecatedKt$distinctBy$1.L$3 = null;
            channelsKt__DeprecatedKt$distinctBy$1.label = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i == 1) {
            ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$2;
            HashSet hashSet5 = (HashSet) this.L$1;
            ProducerScope producerScope5 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope3 = producerScope5;
            hashSet3 = hashSet5;
            channelIterator = channelIterator2;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$distinctBy$12 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i == 2) {
            Object obj3 = this.L$3;
            channelIterator = (ChannelIterator) this.L$2;
            HashSet hashSet6 = (HashSet) this.L$1;
            ProducerScope producerScope6 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope2 = producerScope6;
            hashSet2 = hashSet6;
            e = obj3;
            obj2 = coroutine_suspended;
            channelsKt__DeprecatedKt$distinctBy$12 = this;
            if (hashSet2.contains(obj)) {
            }
        } else {
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj4 = this.L$3;
            channelIterator = (ChannelIterator) this.L$2;
            HashSet hashSet7 = (HashSet) this.L$1;
            ProducerScope producerScope7 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__DeprecatedKt$distinctBy$1 = this;
            hashSet7.add(obj4);
            it = channelIterator;
            hashSet = hashSet7;
            producerScope = producerScope7;
            channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
            channelsKt__DeprecatedKt$distinctBy$1.L$1 = hashSet;
            channelsKt__DeprecatedKt$distinctBy$1.L$2 = it;
            channelsKt__DeprecatedKt$distinctBy$1.L$3 = null;
            channelsKt__DeprecatedKt$distinctBy$1.label = 1;
            hasNext = it.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__DeprecatedKt$distinctBy$12 = channelsKt__DeprecatedKt$distinctBy$1;
            obj = hasNext;
            producerScope3 = producerScope;
            hashSet3 = hashSet;
            channelIterator = it;
            obj2 = obj5;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                Function2<E, Continuation<? super K>, Object> function2 = channelsKt__DeprecatedKt$distinctBy$12.$selector;
                channelsKt__DeprecatedKt$distinctBy$12.L$0 = producerScope3;
                channelsKt__DeprecatedKt$distinctBy$12.L$1 = hashSet3;
                channelsKt__DeprecatedKt$distinctBy$12.L$2 = channelIterator;
                channelsKt__DeprecatedKt$distinctBy$12.L$3 = next;
                channelsKt__DeprecatedKt$distinctBy$12.label = 2;
                Object invoke = function2.invoke(next, channelsKt__DeprecatedKt$distinctBy$12);
                if (invoke == obj2) {
                    return obj2;
                }
                HashSet hashSet8 = hashSet3;
                e = next;
                obj = invoke;
                producerScope2 = producerScope3;
                hashSet2 = hashSet8;
                if (hashSet2.contains(obj)) {
                    channelsKt__DeprecatedKt$distinctBy$12.L$0 = producerScope2;
                    channelsKt__DeprecatedKt$distinctBy$12.L$1 = hashSet2;
                    channelsKt__DeprecatedKt$distinctBy$12.L$2 = channelIterator;
                    channelsKt__DeprecatedKt$distinctBy$12.L$3 = obj;
                    channelsKt__DeprecatedKt$distinctBy$12.label = 3;
                    if (producerScope2.send(e, channelsKt__DeprecatedKt$distinctBy$12) == obj2) {
                        return obj2;
                    }
                    hashSet7 = hashSet2;
                    producerScope7 = producerScope2;
                    Object obj6 = obj2;
                    obj4 = obj;
                    channelsKt__DeprecatedKt$distinctBy$1 = channelsKt__DeprecatedKt$distinctBy$12;
                    coroutine_suspended = obj6;
                    hashSet7.add(obj4);
                    it = channelIterator;
                    hashSet = hashSet7;
                    producerScope = producerScope7;
                    channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
                    channelsKt__DeprecatedKt$distinctBy$1.L$1 = hashSet;
                    channelsKt__DeprecatedKt$distinctBy$1.L$2 = it;
                    channelsKt__DeprecatedKt$distinctBy$1.L$3 = null;
                    channelsKt__DeprecatedKt$distinctBy$1.label = 1;
                    hasNext = it.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
                    if (hasNext != coroutine_suspended) {
                    }
                } else {
                    channelsKt__DeprecatedKt$distinctBy$1 = channelsKt__DeprecatedKt$distinctBy$12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    hashSet = hashSet2;
                    producerScope = producerScope2;
                    channelsKt__DeprecatedKt$distinctBy$1.L$0 = producerScope;
                    channelsKt__DeprecatedKt$distinctBy$1.L$1 = hashSet;
                    channelsKt__DeprecatedKt$distinctBy$1.L$2 = it;
                    channelsKt__DeprecatedKt$distinctBy$1.L$3 = null;
                    channelsKt__DeprecatedKt$distinctBy$1.label = 1;
                    hasNext = it.hasNext(channelsKt__DeprecatedKt$distinctBy$1);
                    if (hasNext != coroutine_suspended) {
                    }
                }
            } else {
                return Unit.INSTANCE;
            }
        }
    }
}
