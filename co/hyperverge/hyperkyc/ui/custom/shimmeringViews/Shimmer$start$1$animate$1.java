package co.hyperverge.hyperkyc.ui.custom.shimmeringViews;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Incorrect field signature: TV; */
/* compiled from: Shimmer.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\f\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "V", "Landroid/view/View;", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewBase;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer$start$1$animate$1", f = "Shimmer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class Shimmer$start$1$animate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $shimmerView;
    int label;
    final /* synthetic */ Shimmer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Incorrect types in method signature: (TV;Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/Shimmer;Lkotlin/coroutines/Continuation<-Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/Shimmer$start$1$animate$1;>;)V */
    public Shimmer$start$1$animate$1(View view, Shimmer shimmer, Continuation continuation) {
        super(2, continuation);
        this.$shimmerView = view;
        this.this$0 = shimmer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Shimmer$start$1$animate$1(this.$shimmerView, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Shimmer$start$1$animate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0076, code lost:
    
        r7 = r6.this$0.animator;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        ObjectAnimator objectAnimator3;
        ObjectAnimator objectAnimator4;
        Animator.AnimatorListener animatorListener;
        ObjectAnimator objectAnimator5;
        ObjectAnimator objectAnimator6;
        Animator.AnimatorListener animatorListener2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ((ShimmerViewBase) this.$shimmerView).setShimmering(true);
        this.this$0.animator = ObjectAnimator.ofFloat(this.$shimmerView, "gradientX", 0.0f, this.$shimmerView.getWidth());
        objectAnimator = this.this$0.animator;
        if (objectAnimator != null) {
            objectAnimator.setRepeatCount(-1);
        }
        objectAnimator2 = this.this$0.animator;
        if (objectAnimator2 != null) {
            objectAnimator2.setDuration(1500L);
        }
        objectAnimator3 = this.this$0.animator;
        if (objectAnimator3 != null) {
            objectAnimator3.setStartDelay(0L);
        }
        objectAnimator4 = this.this$0.animator;
        if (objectAnimator4 != null) {
            final View view = this.$shimmerView;
            final Shimmer shimmer = this.this$0;
            objectAnimator4.addListener(new Animator.AnimatorListener() { // from class: co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer$start$1$animate$1.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    ((ShimmerViewBase) view).setShimmering(false);
                    view.postInvalidateOnAnimation();
                    shimmer.animator = null;
                }
            });
        }
        animatorListener = this.this$0.animatorListener;
        if (animatorListener != null && objectAnimator6 != null) {
            animatorListener2 = this.this$0.animatorListener;
            objectAnimator6.addListener(animatorListener2);
        }
        objectAnimator5 = this.this$0.animator;
        if (objectAnimator5 == null) {
            return null;
        }
        objectAnimator5.start();
        return Unit.INSTANCE;
    }
}
