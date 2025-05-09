package kotlinx.coroutines.flow;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.OnTimeoutKt;
import kotlinx.coroutines.selects.SelectImplementation;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Delay.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 1, 1, 1}, l = {221, TypedValues.CycleType.TYPE_PATH_ROTATE}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "timeoutMillis", "downstream", "values", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class FlowKt__DelayKt$debounceInternal$1<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T> $this_debounceInternal;
    final /* synthetic */ Function1<T, Long> $timeoutMillisSelector;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__DelayKt$debounceInternal$1(Function1<? super T, Long> function1, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$debounceInternal$1> continuation) {
        super(3, continuation);
        this.$timeoutMillisSelector = function1;
        this.$this_debounceInternal = flow;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation);
        flowKt__DelayKt$debounceInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$debounceInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$debounceInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0077  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x012d -> B:6:0x0132). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$1;
        ReceiveChannel produce$default;
        Ref.ObjectRef objectRef;
        Ref.LongRef longRef;
        Ref.ObjectRef objectRef2;
        FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$12;
        SelectImplementation selectImplementation;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        long j = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            flowCollector = (FlowCollector) this.L$1;
            flowKt__DelayKt$debounceInternal$1 = this;
            produce$default = ProduceKt.produce$default(coroutineScope, null, 0, new FlowKt__DelayKt$debounceInternal$1$values$1(this.$this_debounceInternal, null), 3, null);
            objectRef = new Ref.ObjectRef();
            if (objectRef.element != NullSurrogateKt.DONE) {
            }
        } else if (i == 1) {
            Ref.LongRef longRef2 = (Ref.LongRef) this.L$3;
            objectRef = (Ref.ObjectRef) this.L$2;
            produce$default = (ReceiveChannel) this.L$1;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            longRef = longRef2;
            flowKt__DelayKt$debounceInternal$1 = this;
            objectRef.element = null;
            objectRef2 = objectRef;
            flowKt__DelayKt$debounceInternal$12 = flowKt__DelayKt$debounceInternal$1;
            if (DebugKt.getASSERTIONS_ENABLED()) {
            }
            selectImplementation = new SelectImplementation(flowKt__DelayKt$debounceInternal$12.get$context());
            SelectImplementation selectImplementation2 = selectImplementation;
            if (objectRef2.element != null) {
            }
            selectImplementation2.invoke(produce$default.getOnReceiveCatching(), new FlowKt__DelayKt$debounceInternal$1$3$2(objectRef2, flowCollector, null));
            flowKt__DelayKt$debounceInternal$12.L$0 = flowCollector;
            flowKt__DelayKt$debounceInternal$12.L$1 = produce$default;
            flowKt__DelayKt$debounceInternal$12.L$2 = objectRef2;
            flowKt__DelayKt$debounceInternal$12.L$3 = null;
            flowKt__DelayKt$debounceInternal$12.label = 2;
            if (selectImplementation.doSelect(flowKt__DelayKt$debounceInternal$12) != coroutine_suspended) {
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$2;
            ReceiveChannel receiveChannel = (ReceiveChannel) this.L$1;
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector2;
            produce$default = receiveChannel;
            objectRef = objectRef3;
            flowKt__DelayKt$debounceInternal$1 = this;
            j = 0;
            if (objectRef.element != NullSurrogateKt.DONE) {
                longRef = new Ref.LongRef();
                if (objectRef.element != null) {
                    Function1<T, Long> function1 = flowKt__DelayKt$debounceInternal$1.$timeoutMillisSelector;
                    Symbol symbol = NullSurrogateKt.NULL;
                    T t = objectRef.element;
                    if (t == symbol) {
                        t = null;
                    }
                    longRef.element = function1.invoke(t).longValue();
                    if (!(longRef.element >= j)) {
                        throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
                    }
                    if (longRef.element == j) {
                        Symbol symbol2 = NullSurrogateKt.NULL;
                        T t2 = objectRef.element;
                        if (t2 == symbol2) {
                            t2 = null;
                        }
                        flowKt__DelayKt$debounceInternal$1.L$0 = flowCollector;
                        flowKt__DelayKt$debounceInternal$1.L$1 = produce$default;
                        flowKt__DelayKt$debounceInternal$1.L$2 = objectRef;
                        flowKt__DelayKt$debounceInternal$1.L$3 = longRef;
                        flowKt__DelayKt$debounceInternal$1.label = 1;
                        if (flowCollector.emit(t2, flowKt__DelayKt$debounceInternal$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef.element = null;
                    }
                }
                objectRef2 = objectRef;
                flowKt__DelayKt$debounceInternal$12 = flowKt__DelayKt$debounceInternal$1;
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(objectRef2.element == null || longRef.element > j)) {
                        throw new AssertionError();
                    }
                }
                selectImplementation = new SelectImplementation(flowKt__DelayKt$debounceInternal$12.get$context());
                SelectImplementation selectImplementation22 = selectImplementation;
                if (objectRef2.element != null) {
                    OnTimeoutKt.onTimeout(selectImplementation22, longRef.element, new FlowKt__DelayKt$debounceInternal$1$3$1(flowCollector, objectRef2, null));
                }
                selectImplementation22.invoke(produce$default.getOnReceiveCatching(), new FlowKt__DelayKt$debounceInternal$1$3$2(objectRef2, flowCollector, null));
                flowKt__DelayKt$debounceInternal$12.L$0 = flowCollector;
                flowKt__DelayKt$debounceInternal$12.L$1 = produce$default;
                flowKt__DelayKt$debounceInternal$12.L$2 = objectRef2;
                flowKt__DelayKt$debounceInternal$12.L$3 = null;
                flowKt__DelayKt$debounceInternal$12.label = 2;
                if (selectImplementation.doSelect(flowKt__DelayKt$debounceInternal$12) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                flowKt__DelayKt$debounceInternal$1 = flowKt__DelayKt$debounceInternal$12;
                j = 0;
                if (objectRef.element != NullSurrogateKt.DONE) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
