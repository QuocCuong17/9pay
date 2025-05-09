package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.model.UIColors;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import com.airbnb.lottie.LottieAnimationView;
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
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$initViews$2$1$1", f = "HKMainActivity.kt", i = {}, l = {389, 391}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$initViews$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LottieAnimationView $this_with;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$initViews$2$1$1(HKMainActivity hKMainActivity, LottieAnimationView lottieAnimationView, Continuation<? super HKMainActivity$initViews$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
        this.$this_with = lottieAnimationView;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKMainActivity$initViews$2$1$1(this.this$0, this.$this_with, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$initViews$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object storeUIConfig;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            storeUIConfig = this.this$0.storeUIConfig(this);
            if (storeUIConfig == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.label = 2;
        if (CoroutineExtsKt.onUI$default(null, new AnonymousClass1(this.$this_with, this.this$0, null), this, 1, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HKMainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$initViews$2$1$1$1", f = "HKMainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$initViews$2$1$1$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LottieAnimationView $this_with;
        int label;
        final /* synthetic */ HKMainActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LottieAnimationView lottieAnimationView, HKMainActivity hKMainActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_with = lottieAnimationView;
            this.this$0 = hKMainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_with, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            UIColors colors;
            String animationPrimaryColor;
            String nullIfBlank;
            UIConfig uiConfig;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LottieAnimationView invokeSuspend = this.$this_with;
            Intrinsics.checkNotNullExpressionValue(invokeSuspend, "invokeSuspend");
            String str = null;
            ViewExtsKt.setLoadingAnim$default(invokeSuspend, null, null, 3, null);
            HKMainActivity hKMainActivity = this.this$0;
            HyperSnapSDKConfig hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
            if (hyperSnapSDKConfig != null && (uiConfig = hyperSnapSDKConfig.getUiConfig()) != null) {
                str = uiConfig.getBackgroundImage();
            }
            hKMainActivity.loadBackground$hyperkyc_release(str);
            UIConfig uiConfig2 = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getUiConfig();
            if (uiConfig2 != null && (colors = uiConfig2.getColors()) != null && (animationPrimaryColor = colors.getAnimationPrimaryColor()) != null && (nullIfBlank = CoreExtsKt.nullIfBlank(animationPrimaryColor)) != null) {
                LottieAnimationView invokeSuspend$lambda$0 = this.$this_with;
                Intrinsics.checkNotNullExpressionValue(invokeSuspend$lambda$0, "invokeSuspend$lambda$0");
                ViewExtsKt.updateColor(invokeSuspend$lambda$0, nullIfBlank);
            }
            return Unit.INSTANCE;
        }
    }
}
