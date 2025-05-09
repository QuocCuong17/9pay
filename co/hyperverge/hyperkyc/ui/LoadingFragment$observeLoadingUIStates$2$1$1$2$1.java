package co.hyperverge.hyperkyc.ui;

import android.os.Bundle;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.databinding.HkFragmentLoadingBinding;
import co.hyperverge.hyperkyc.ui.models.LoadingUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.model.UIAnimation;
import co.hyperverge.hypersnapsdk.model.UIColors;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Locale;
import java.util.Map;
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

/* compiled from: LoadingFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.LoadingFragment$observeLoadingUIStates$2$1$1$2$1", f = "LoadingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class LoadingFragment$observeLoadingUIStates$2$1$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoadingUIState $it;
    final /* synthetic */ HkFragmentLoadingBinding $this_with;
    int label;
    final /* synthetic */ LoadingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingFragment$observeLoadingUIStates$2$1$1$2$1(LoadingUIState loadingUIState, HkFragmentLoadingBinding hkFragmentLoadingBinding, LoadingFragment loadingFragment, Continuation<? super LoadingFragment$observeLoadingUIStates$2$1$1$2$1> continuation) {
        super(2, continuation);
        this.$it = loadingUIState;
        this.$this_with = hkFragmentLoadingBinding;
        this.this$0 = loadingFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LoadingFragment$observeLoadingUIStates$2$1$1$2$1(this.$it, this.$this_with, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoadingFragment$observeLoadingUIStates$2$1$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0080, code lost:
    
        if (r1 == null) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Map map;
        String string;
        Map map2;
        String str;
        MainVM mainVM;
        String str2;
        HyperSnapSDKConfig hyperSnapSDKConfig;
        UIConfig uiConfig;
        UIColors colors;
        UIAnimation animation;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        String str3 = null;
        if (this.$it instanceof LoadingUIState.Idle) {
            this.$this_with.lottieAnimView.setVisibility(8);
            this.$this_with.lottieAnimView.invalidate();
            this.$this_with.lottieAnimView.clearAnimation();
            this.$this_with.lottieAnimView.cancelAnimation();
            this.$this_with.tvTitle.setText((CharSequence) null);
            this.$this_with.tvDesc.setText((CharSequence) null);
        } else {
            this.$this_with.lottieAnimView.setVisibility(0);
            String simpleName = this.$it.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "it.javaClass.simpleName");
            String lowerCase = simpleName.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            map = this.this$0.textConfigs;
            if (map != null) {
                string = (String) map.get(lowerCase + "Title");
            }
            Bundle arguments = this.this$0.getArguments();
            string = arguments != null ? arguments.getString("loading_message") : null;
            map2 = this.this$0.textConfigs;
            if (map2 != null) {
                str = (String) map2.get(lowerCase + "Hint");
            } else {
                str = null;
            }
            this.$this_with.tvTitle.setText(string);
            this.$this_with.tvDesc.setText(str);
            LottieAnimationView invokeSuspend$lambda$2$lambda$1 = this.$this_with.lottieAnimView;
            LoadingUIState loadingUIState = this.$it;
            LoadingFragment loadingFragment = this.this$0;
            invokeSuspend$lambda$2$lambda$1.setRepeatCount(loadingUIState.getLottieRepeatCount());
            mainVM = loadingFragment.getMainVM();
            HSUIConfig uiConfigData = mainVM.getUiConfigData();
            if (uiConfigData != null && (animation = uiConfigData.getAnimation()) != null) {
                Intrinsics.checkNotNullExpressionValue(animation, "animation");
                if (loadingUIState instanceof LoadingUIState.Processing) {
                    str2 = animation.getEndStateProcessing();
                } else if (loadingUIState instanceof LoadingUIState.Success) {
                    str2 = animation.getEndStateSuccess();
                } else if (loadingUIState instanceof LoadingUIState.Failure) {
                    str2 = animation.getEndStateFailure();
                }
                if (CoreExtsKt.nullIfBlank(str2) == null) {
                    invokeSuspend$lambda$2$lambda$1.setAnimationFromUrl(str2);
                } else {
                    invokeSuspend$lambda$2$lambda$1.setAnimation(loadingUIState.getLottieFileName());
                }
                invokeSuspend$lambda$2$lambda$1.invalidate();
                hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
                if (hyperSnapSDKConfig != null && (uiConfig = hyperSnapSDKConfig.getUiConfig()) != null && (colors = uiConfig.getColors()) != null) {
                    Intrinsics.checkNotNullExpressionValue(colors, "colors");
                    if (!(loadingUIState instanceof LoadingUIState.Processing)) {
                        str3 = colors.getAnimationPrimaryColor();
                    } else if (loadingUIState instanceof LoadingUIState.Success) {
                        str3 = colors.getSuccessAnimationPrimaryColor();
                    } else if (loadingUIState instanceof LoadingUIState.Failure) {
                        str3 = colors.getErrorAnimationPrimaryColor();
                    }
                    Intrinsics.checkNotNullExpressionValue(invokeSuspend$lambda$2$lambda$1, "invokeSuspend$lambda$2$lambda$1");
                    ViewExtsKt.updateColor(invokeSuspend$lambda$2$lambda$1, str3);
                }
                invokeSuspend$lambda$2$lambda$1.playAnimation();
            }
            str2 = null;
            if (CoreExtsKt.nullIfBlank(str2) == null) {
            }
            invokeSuspend$lambda$2$lambda$1.invalidate();
            hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
            if (hyperSnapSDKConfig != null) {
                Intrinsics.checkNotNullExpressionValue(colors, "colors");
                if (!(loadingUIState instanceof LoadingUIState.Processing)) {
                }
                Intrinsics.checkNotNullExpressionValue(invokeSuspend$lambda$2$lambda$1, "invokeSuspend$lambda$2$lambda$1");
                ViewExtsKt.updateColor(invokeSuspend$lambda$2$lambda$1, str3);
            }
            invokeSuspend$lambda$2$lambda$1.playAnimation();
        }
        return Unit.INSTANCE;
    }
}
