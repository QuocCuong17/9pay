package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.model.UIAnimation;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.protocol.Device;
import java.util.Objects;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ViewExts.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0007H\u0000\u001a\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u000f*\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\u0010\u0016\u001a\u001d\u0010\u0017\u001a\u0004\u0018\u00010\u000f*\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0000¢\u0006\u0002\u0010\u001a\u001a4\u0010\u0017\u001a\u00020\u000f*\u00020\u00132\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u0007H\u0000\u001a\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 *\u00020!H\u0000\u001a\f\u0010\"\u001a\u00020\u000f*\u00020#H\u0000\u001a\f\u0010$\u001a\u00020\u000f*\u00020\u0013H\u0000\u001ak\u0010%\u001a\u00020\u000f*\u00020&28\b\u0002\u0010'\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u000f0(2#\b\u0002\u0010-\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u000f0.H\u0000\u001a\f\u00100\u001a\u000201*\u00020\u0013H\u0000\u001a0\u00102\u001a\u00020\u000f*\u0002032\u0006\u00104\u001a\u0002052\n\b\u0002\u00106\u001a\u0004\u0018\u0001072\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u000f09H\u0000\u001a\"\u0010:\u001a\u00020\u000f*\u00020;2\b\b\u0002\u0010<\u001a\u0002012\n\b\u0002\u0010=\u001a\u0004\u0018\u000101H\u0000\u001a\f\u0010>\u001a\u00020\u000f*\u00020\u0013H\u0000\u001a\f\u0010?\u001a\u00020\u000f*\u00020@H\u0000\u001a\f\u0010A\u001a\u00020\u000f*\u00020@H\u0000\u001a\u0014\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010C0 *\u00020DH\u0000\u001a\u0016\u0010E\u001a\u00020\u000f*\u00020;2\b\u0010F\u001a\u0004\u0018\u000101H\u0000\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0018\u0010\n\u001a\u00020\u0007*\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\"\u0018\u0010\f\u001a\u00020\u0007*\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006G"}, d2 = {"rotationAnimation", "Landroid/view/animation/RotateAnimation;", "getRotationAnimation", "()Landroid/view/animation/RotateAnimation;", "rotationAnimation$delegate", "Lkotlin/Lazy;", "dp", "", "getDp", "(I)I", "px", "getPx", "sp", "getSp", "addDivider", "", "Landroidx/recyclerview/widget/RecyclerView;", Device.JsonKeys.ORIENTATION, "addLayoutParams", "Landroid/view/View;", "param", "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)Lkotlin/Unit;", "addMargin", "margin", "Lco/hyperverge/hyperkyc/utils/extensions/Margin;", "(Landroid/view/View;Lco/hyperverge/hyperkyc/utils/extensions/Margin;)Lkotlin/Unit;", "left", ViewHierarchyConstants.DIMENSION_TOP_KEY, TtmlNode.RIGHT, "bottom", "buttonClickFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroid/widget/Button;", "forceDestroy", "Landroid/webkit/WebView;", "hideSoftInput", "onTransition", "Landroidx/constraintlayout/motion/widget/MotionLayout;", "onStarted", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "startTransitionId", "endTransitionId", "onEnd", "Lkotlin/Function1;", "transitionId", "resName", "", "setCheckedSilently", "Landroid/widget/CheckBox;", "checked", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "action", "Lkotlin/Function0;", "setLoadingAnim", "Lcom/airbnb/lottie/LottieAnimationView;", "localAssetName", "animationUrl", "showSoftInput", "startAnimation", "Landroid/widget/ImageView;", "stopAnimation", "textChangesFlow", "", "Landroid/widget/EditText;", "updateColor", "color", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewExtsKt {
    private static final Lazy rotationAnimation$delegate = LazyKt.lazy(new Function0<RotateAnimation>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$rotationAnimation$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RotateAnimation invoke() {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(1000L);
            rotateAnimation.setRepeatCount(-1);
            return rotateAnimation;
        }
    });

    private static final RotateAnimation getRotationAnimation() {
        return (RotateAnimation) rotationAnimation$delegate.getValue();
    }

    public static final /* synthetic */ void startAnimation(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        imageView.startAnimation(getRotationAnimation());
    }

    public static final /* synthetic */ void stopAnimation(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        imageView.clearAnimation();
    }

    public static /* synthetic */ void addDivider$default(RecyclerView recyclerView, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        addDivider(recyclerView, i);
    }

    public static final /* synthetic */ void addDivider(RecyclerView recyclerView, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), i));
    }

    public static final /* synthetic */ void hideSoftInput(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object systemService = view.getContext().getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static final /* synthetic */ String resName(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        String resourceEntryName = view.getContext().getResources().getResourceEntryName(view.getContext().getResources().getIdentifier(String.valueOf(view.getId()), "id", view.getContext().getPackageName()));
        Intrinsics.checkNotNullExpressionValue(resourceEntryName, "context.resources.getResourceEntryName(id)");
        return resourceEntryName;
    }

    public static final /* synthetic */ void forceDestroy(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<this>");
        webView.stopLoading();
        ViewParent parent = webView.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        ((ViewGroup) parent).removeView(webView);
        webView.clearHistory();
        webView.loadUrl("about:blank");
        webView.onPause();
        webView.removeAllViews();
        webView.destroyDrawingCache();
        webView.destroy();
    }

    public static /* synthetic */ void onTransition$default(MotionLayout motionLayout, Function2 function2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = new Function2<Integer, Integer, Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$onTransition$1
                public final void invoke(int i2, int i3) {
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                    invoke(num.intValue(), num2.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$onTransition$2
                public final void invoke(int i2) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        onTransition(motionLayout, function2, function1);
    }

    public static final /* synthetic */ void onTransition(MotionLayout motionLayout, final Function2 onStarted, final Function1 onEnd) {
        Intrinsics.checkNotNullParameter(motionLayout, "<this>");
        Intrinsics.checkNotNullParameter(onStarted, "onStarted");
        Intrinsics.checkNotNullParameter(onEnd, "onEnd");
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$onTransition$3
            @Override // androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
            public void onTransitionChange(MotionLayout motionLayout2, int startId, int endId, float progress) {
            }

            @Override // androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
            public void onTransitionTrigger(MotionLayout motionLayout2, int triggerId, boolean positive, float progress) {
            }

            @Override // androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
            public void onTransitionStarted(MotionLayout motionLayout2, int startId, int endId) {
                onStarted.invoke(Integer.valueOf(startId), Integer.valueOf(endId));
            }

            @Override // androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
            public void onTransitionCompleted(MotionLayout motionLayout2, int currentId) {
                onEnd.invoke(Integer.valueOf(currentId));
            }
        });
    }

    public static final int getPx(int i) {
        return (int) (i / Resources.getSystem().getDisplayMetrics().density);
    }

    public static final int getDp(int i) {
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }

    public static final int getSp(int i) {
        return (int) TypedValue.applyDimension(2, i, Resources.getSystem().getDisplayMetrics());
    }

    public static final /* synthetic */ Unit addMargin(View view, Margin margin) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (margin == null) {
            return null;
        }
        addMargin(view, margin.getLeft(), margin.getTop(), margin.getRight(), margin.getBottom());
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void addMargin$default(View view, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        addMargin(view, i, i2, i3, i4);
    }

    public static final /* synthetic */ Flow textChangesFlow(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        return FlowKt.callbackFlow(new ViewExtsKt$textChangesFlow$1(editText, null));
    }

    public static final /* synthetic */ Flow buttonClickFlow(Button button) {
        Intrinsics.checkNotNullParameter(button, "<this>");
        return FlowKt.callbackFlow(new ViewExtsKt$buttonClickFlow$1(button, null));
    }

    public static final /* synthetic */ void updateColor(LottieAnimationView lottieAnimationView, String str) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(lottieAnimationView, "<this>");
        if (UIExtsKt.isValidHexColor(str)) {
            final int parseColor = Color.parseColor(str);
            lottieAnimationView.addValueCallback(new KeyPath("**", "hvLottiePrimaryColor"), (KeyPath) LottieProperty.COLOR, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                    Integer valueOf;
                    valueOf = Integer.valueOf(parseColor);
                    return valueOf;
                }
            });
            lottieAnimationView.addValueCallback(new KeyPath("**", "hvLottiePrimaryColor"), (KeyPath) LottieProperty.STROKE_COLOR, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$$ExternalSyntheticLambda2
                @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                    Integer valueOf;
                    valueOf = Integer.valueOf(parseColor);
                    return valueOf;
                }
            });
            if (UIExtsKt.hasAlpha(parseColor)) {
                lottieAnimationView.addValueCallback(new KeyPath("**", "hvLottiePrimaryColor"), (KeyPath) LottieProperty.OPACITY, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$$ExternalSyntheticLambda1
                    @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                    public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                        Integer updateColor$lambda$9;
                        updateColor$lambda$9 = ViewExtsKt.updateColor$lambda$9(parseColor, lottieFrameInfo);
                        return updateColor$lambda$9;
                    }
                });
                return;
            }
            return;
        }
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = lottieAnimationView.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str3 = "updateColor() invalid color value for animationPrimaryColor : " + str;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        CoreExtsKt.isRelease();
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = "";
        }
        String packageName = (String) m1202constructorimpl;
        if (CoreExtsKt.isDebug()) {
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls2 = lottieAnimationView.getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    if (canonicalName2 != null) {
                        str2 = canonicalName2;
                    }
                } else {
                    str2 = substringAfterLast$default;
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                if (matcher2.find()) {
                    str2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String str4 = "updateColor() invalid color value for animationPrimaryColor : " + str;
                if (str4 == null) {
                    str4 = "null ";
                }
                sb2.append(str4);
                sb2.append(' ');
                sb2.append("");
                Log.println(6, str2, sb2.toString());
            }
        }
    }

    public static final Integer updateColor$lambda$9(int i, LottieFrameInfo lottieFrameInfo) {
        return Integer.valueOf(UIExtsKt.alpha(i));
    }

    public static /* synthetic */ void setLoadingAnim$default(LottieAnimationView lottieAnimationView, String str, String str2, int i, Object obj) {
        UIAnimation animation;
        if ((i & 1) != 0) {
            str = "hv_processing.lottie";
        }
        if ((i & 2) != 0) {
            UIConfig uiConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getUiConfig();
            str2 = (uiConfig == null || (animation = uiConfig.getAnimation()) == null) ? null : animation.getLoaderLottie();
        }
        setLoadingAnim(lottieAnimationView, str, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x020e, code lost:
    
        if (r12 != null) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0065, code lost:
    
        if (r11 != null) goto L172;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x032e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void setLoadingAnim(LottieAnimationView lottieAnimationView, String localAssetName, String str) {
        String str2;
        String str3;
        Object m1202constructorimpl;
        String str4;
        Matcher matcher;
        String str5;
        String className;
        String className2;
        CharSequence charSequence;
        String str6;
        String str7;
        Object m1202constructorimpl2;
        String str8;
        String className3;
        String substringAfterLast$default;
        String className4;
        Intrinsics.checkNotNullParameter(lottieAnimationView, "<this>");
        Intrinsics.checkNotNullParameter(localAssetName, "localAssetName");
        String str9 = "N/A";
        if (CoreExtsKt.nullIfBlank(str) != null) {
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null) {
                charSequence = "co.hyperverge";
                str6 = "Throwable().stackTrace";
            } else {
                charSequence = "co.hyperverge";
                str6 = "Throwable().stackTrace";
                str7 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls = lottieAnimationView.getClass();
            String canonicalName = cls != null ? cls.getCanonicalName() : null;
            str7 = canonicalName == null ? "N/A" : canonicalName;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
            if (matcher2.find()) {
                str7 = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
            }
            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str7 = str7.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str7);
            sb.append(" - ");
            String str10 = "setLoadingAnim() setting animation from url: " + str;
            if (str10 == null) {
                str10 = "null ";
            }
            sb.append(str10);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String packageName = (String) m1202constructorimpl2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className3 = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = lottieAnimationView.getClass();
                            String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 != null) {
                                str9 = canonicalName2;
                            }
                        } else {
                            str9 = substringAfterLast$default;
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                        if (matcher3.find()) {
                            str8 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str8, "replaceAll(\"\")");
                        } else {
                            str8 = str9;
                        }
                        if (str8.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str8 = str8.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str11 = "setLoadingAnim() setting animation from url: " + str;
                        sb2.append(str11 == null ? "null " : str11);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str8, sb2.toString());
                    }
                }
            }
            lottieAnimationView.setAnimationFromUrl(str);
        } else {
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                str2 = "Throwable().stackTrace";
            } else {
                str2 = "Throwable().stackTrace";
                str3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls3 = lottieAnimationView.getClass();
            String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
            str3 = canonicalName3 == null ? "N/A" : canonicalName3;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher4.find()) {
                str3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str3);
            sb3.append(" - ");
            String str12 = "setLoadingAnim() setting animation from local asset: " + localAssetName;
            if (str12 == null) {
                str12 = "null ";
            }
            sb3.append(str12);
            sb3.append(' ');
            sb3.append("");
            companion4.log(level2, sb3.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion6 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                            str4 = null;
                        } else {
                            str4 = null;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default2 != null) {
                                str9 = substringAfterLast$default2;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                                if (matcher.find()) {
                                    str5 = str9;
                                } else {
                                    str5 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                }
                                if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str13 = "setLoadingAnim() setting animation from local asset: " + localAssetName;
                                sb4.append(str13 != null ? "null " : str13);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(3, str5, sb4.toString());
                            }
                        }
                        Class<?> cls4 = lottieAnimationView.getClass();
                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                        if (canonicalName4 != null) {
                            str9 = canonicalName4;
                        }
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                        if (matcher.find()) {
                        }
                        if (str5.length() > 23) {
                            str5 = str5.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb42 = new StringBuilder();
                        String str132 = "setLoadingAnim() setting animation from local asset: " + localAssetName;
                        sb42.append(str132 != null ? "null " : str132);
                        sb42.append(' ');
                        sb42.append("");
                        Log.println(3, str5, sb42.toString());
                    }
                }
            }
            lottieAnimationView.setAnimation(localAssetName);
        }
        lottieAnimationView.invalidate();
    }

    public static /* synthetic */ void setCheckedSilently$default(CheckBox checkBox, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            onCheckedChangeListener = null;
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$setCheckedSilently$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        setCheckedSilently(checkBox, z, onCheckedChangeListener, function0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x01ca, code lost:
    
        if (r10 != null) goto L222;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x02bd, code lost:
    
        if (r0 != null) goto L263;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0197  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void showSoftInput(View view) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String className2;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(view, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = view.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("showSoftInput() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = view.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher2.find()) {
                        canonicalName2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, canonicalName2, "showSoftInput() called ");
                }
                if (!view.requestFocus()) {
                    Object systemService = view.getContext().getSystemService("input_method");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                    ((InputMethodManager) systemService).showSoftInput(view, 2);
                    return;
                }
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                    str2 = "Throwable().stackTrace";
                } else {
                    str2 = "Throwable().stackTrace";
                    str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls3 = view.getClass();
                String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                str3 = canonicalName3 == null ? str : canonicalName3;
                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                if (matcher3.find()) {
                    str3 = matcher3.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                }
                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str3 = str3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb2.append(str3);
                sb2.append(" - ");
                String str6 = "showSoftInput: view " + view + " not in focus";
                if (str6 == null) {
                    str6 = "null ";
                }
                sb2.append(str6);
                sb2.append(' ');
                sb2.append("");
                companion4.log(level2, sb2.toString());
                if (CoreExtsKt.isRelease()) {
                    return;
                }
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion6 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String packageName2 = (String) m1202constructorimpl2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                            str4 = null;
                        } else {
                            str4 = null;
                            str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls4 = view.getClass();
                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                        str5 = canonicalName4 == null ? str : canonicalName4;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                        if (matcher4.find()) {
                            str5 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                        }
                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str5 = str5.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb3 = new StringBuilder();
                        String str7 = "showSoftInput: view " + view + " not in focus";
                        sb3.append(str7 != null ? str7 : "null ");
                        sb3.append(' ');
                        sb3.append("");
                        Log.println(5, str5, sb3.toString());
                        return;
                    }
                    return;
                }
                return;
            }
        }
        str = "N/A";
        if (!view.requestFocus()) {
        }
    }

    public static final /* synthetic */ Unit addLayoutParams(View view, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (layoutParams == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        Objects.requireNonNull(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        view.setLayoutParams(layoutParams);
        view.setLayoutParams(layoutParams2);
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ void addMargin(View view, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(i, i2, i3, i4);
        view.setLayoutParams(marginLayoutParams);
    }

    public static final /* synthetic */ void setCheckedSilently(CheckBox checkBox, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, Function0 action) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(checkBox, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = checkBox.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "setCheckedSilently() called with: checked = [" + z + "], listener = [" + onCheckedChangeListener + "], action = [" + action + ']';
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = checkBox.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "setCheckedSilently() called with: checked = [" + z + "], listener = [" + onCheckedChangeListener + "], action = [" + action + ']';
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(z);
        action.invoke();
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
