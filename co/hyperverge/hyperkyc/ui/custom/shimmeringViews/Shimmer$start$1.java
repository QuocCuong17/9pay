package co.hyperverge.hyperkyc.ui.custom.shimmeringViews;

import android.view.View;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Incorrect field signature: TV; */
/* compiled from: Shimmer.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\f\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "V", "Landroid/view/View;", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewBase;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer$start$1", f = "Shimmer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class Shimmer$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineScope $lifecycleScope;
    final /* synthetic */ View $shimmerView;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Shimmer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Incorrect types in method signature: (TV;Lkotlinx/coroutines/CoroutineScope;Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/Shimmer;Lkotlin/coroutines/Continuation<-Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/Shimmer$start$1;>;)V */
    public Shimmer$start$1(View view, CoroutineScope coroutineScope, Shimmer shimmer, Continuation continuation) {
        super(2, continuation);
        this.$shimmerView = view;
        this.$lifecycleScope = coroutineScope;
        this.this$0 = shimmer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Shimmer$start$1 shimmer$start$1 = new Shimmer$start$1(this.$shimmerView, this.$lifecycleScope, this.this$0, continuation);
        shimmer$start$1.L$0 = obj;
        return shimmer$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Shimmer$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final Deferred async$default;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            async$default = BuildersKt__Builders_commonKt.async$default((CoroutineScope) this.L$0, null, null, new Shimmer$start$1$animate$1(this.$shimmerView, this.this$0, null), 3, null);
            if (((ShimmerViewBase) this.$shimmerView).isSetUp()) {
                BuildersKt__Builders_commonKt.launch$default(this.$lifecycleScope, Dispatchers.getMain(), null, new AnonymousClass2(async$default, null), 2, null);
            } else {
                ShimmerViewBase shimmerViewBase = (ShimmerViewBase) this.$shimmerView;
                final CoroutineScope coroutineScope = this.$lifecycleScope;
                shimmerViewBase.setAnimationSetupCallback(new ShimmerViewHelper.AnimationSetupCallback() { // from class: co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer$start$1.1
                    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewHelper.AnimationSetupCallback
                    public void onSetupAnimation(View target) {
                        BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, Dispatchers.getMain(), null, new Shimmer$start$1$1$onSetupAnimation$1(async$default, null), 2, null);
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Shimmer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\f\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "V", "Landroid/view/View;", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewBase;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer$start$1$2", f = "Shimmer.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer$start$1$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Deferred<Unit> $animate;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Deferred<Unit> deferred, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$animate = deferred;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$animate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$animate.await(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
