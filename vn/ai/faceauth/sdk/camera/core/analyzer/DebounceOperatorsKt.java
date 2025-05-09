package vn.ai.faceauth.sdk.camera.core.analyzer;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"debounce", "Lkotlin/Function0;", "", "waitMs", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(JLkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function0;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DebounceOperatorsKt {
    public static final Function0<Unit> debounce(final long j, final CoroutineScope coroutineScope, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        return new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.analyzer.DebounceOperatorsKt$debounce$1

            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
            @DebugMetadata(c = "vn.ai.faceauth.sdk.camera.core.analyzer.DebounceOperatorsKt$debounce$1$1", f = "DebounceOperators.kt", i = {}, l = {18, 19}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: vn.ai.faceauth.sdk.camera.core.analyzer.DebounceOperatorsKt$debounce$1$1, reason: invalid class name */
            /* loaded from: classes6.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
                final /* synthetic */ long $waitMs;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass1(long j, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$waitMs = j;
                    this.$block = function1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$waitMs, this.$block, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        long j = this.$waitMs;
                        this.label = 1;
                        if (DelayKt.delay(j, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            if (i != 2) {
                                throw new IllegalStateException(tfwhgw.rnigpa(212));
                            }
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    Function1<Continuation<? super Unit>, Object> function1 = this.$block;
                    this.label = 2;
                    if (function1.invoke(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [T, kotlinx.coroutines.Job] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Job job = objectRef.element;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                objectRef.element = BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(j, function1, null), 3, null);
            }
        };
    }

    public static /* synthetic */ Function0 debounce$default(long j, CoroutineScope coroutineScope, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 300;
        }
        return debounce(j, coroutineScope, function1);
    }
}
