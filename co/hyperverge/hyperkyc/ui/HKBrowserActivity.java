package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.databinding.HkActivityBrowserBinding;
import co.hyperverge.hyperkyc.utils.HKLottieHelper;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.PicassoExtsKt;
import co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: HKBrowserActivity.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 :2\u00020\u0001:\u0001:B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\bH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u001a\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0016H\u0002J\u0019\u0010'\u001a\u00020\u001f2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0002\b)J\u0012\u0010*\u001a\u00020\u001f2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002J\u0012\u0010-\u001a\u00020\u001f2\b\u0010.\u001a\u0004\u0018\u00010,H\u0014J\u0012\u0010/\u001a\u00020\u001f2\b\u00100\u001a\u0004\u0018\u00010\u0005H\u0014J\b\u00101\u001a\u00020\u001fH\u0014J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020,H\u0014J\u0018\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0016H\u0002J\u000e\u00108\u001a\u0004\u0018\u000109*\u00020\u0016H\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lco/hyperverge/hyperkyc/ui/HKBrowserActivity;", "Lco/hyperverge/hyperkyc/ui/BaseActivity;", "()V", "activityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", HKBrowserActivity.ARG_AUTH_STARTED, "", "binding", "Lco/hyperverge/hyperkyc/databinding/HkActivityBrowserBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkActivityBrowserBinding;", "binding$delegate", "Lkotlin/Lazy;", "data", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "getData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "setData$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;)V", "moduleId", "", "getModuleId$hyperkyc_release", "()Ljava/lang/String;", "setModuleId$hyperkyc_release", "(Ljava/lang/String;)V", TypedValues.AttributesType.S_TARGET, "Lcom/squareup/picasso/Target;", "webviewUrl", "handleAuthCancelled", "", "handleAuthComplete", "isAuthorisedActivity", "launchInPartialCustomTabs", "loadAnimation", "lav", "Lcom/airbnb/lottie/LottieAnimationView;", "customUrl", "loadBackground", "url", "loadBackground$hyperkyc_release", "loadState", "state", "Landroid/os/Bundle;", "onCreate", "savedInstanceState", "onNewIntent", SDKConstants.PARAM_INTENT, "onResume", "onSaveInstanceState", "outState", "setError", "errorCode", "", "errorMessage", "parseQueryParameters", "Lorg/json/JSONObject;", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HKBrowserActivity extends BaseActivity {
    public static final /* synthetic */ String ARG_AUTH_STARTED = "authStarted";
    public static final /* synthetic */ String ARG_DATA = "data";
    public static final /* synthetic */ String ARG_MODULE_ID = "moduleId";
    public static final /* synthetic */ String ARG_URL = "url";
    public static final /* synthetic */ String ERROR = "error";
    public static final /* synthetic */ String RESPONSE = "response";
    public static final /* synthetic */ int RESULT_ERROR = 2;
    private final ActivityResultLauncher<Intent> activityLauncher;
    private boolean authStarted;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<HkActivityBrowserBinding>() { // from class: co.hyperverge.hyperkyc.ui.HKBrowserActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HkActivityBrowserBinding invoke() {
            return HkActivityBrowserBinding.inflate(HKBrowserActivity.this.getLayoutInflater());
        }
    });
    private WorkflowModule.Properties.Data data;
    private String moduleId;
    private Target target;
    private String webviewUrl;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void activityLauncher$lambda$0(ActivityResult activityResult) {
    }

    public HKBrowserActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.ui.HKBrowserActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                HKBrowserActivity.activityLauncher$lambda$0((ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)) { result ->\n\n        }");
        this.activityLauncher = registerForActivityResult;
    }

    /* renamed from: getData$hyperkyc_release, reason: from getter */
    public final WorkflowModule.Properties.Data getData() {
        return this.data;
    }

    public final void setData$hyperkyc_release(WorkflowModule.Properties.Data data) {
        this.data = data;
    }

    /* renamed from: getModuleId$hyperkyc_release, reason: from getter */
    public final String getModuleId() {
        return this.moduleId;
    }

    public final void setModuleId$hyperkyc_release(String str) {
        this.moduleId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HkActivityBrowserBinding getBinding() {
        return (HkActivityBrowserBinding) this.binding.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x02d9, code lost:
    
        if (r0 != null) goto L124;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0267 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c6  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        HkActivityBrowserBinding binding;
        StackTraceElement stackTraceElement;
        String str2;
        Class<?> cls;
        Matcher matcher;
        Object m1202constructorimpl2;
        String str3;
        String str4;
        String str5;
        Matcher matcher2;
        Class<?> cls2;
        String className2;
        HyperSnapSDKConfig hyperSnapSDKConfig;
        HKBrowserActivity hKBrowserActivity;
        String str6;
        UIConfig uiConfig;
        String className3;
        String className4;
        super.onCreate(savedInstanceState);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls3 = getClass();
            canonicalName = cls3 != null ? cls3.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher3.find()) {
            canonicalName = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str7 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
        if (str7 == null) {
            str7 = "null ";
        }
        sb.append(str7);
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
                charSequence = "co.hyperverge";
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls4 = getClass();
                        canonicalName2 = cls4 != null ? cls4.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher4.find()) {
                        canonicalName2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str8 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
                    if (str8 == null) {
                        str8 = "null ";
                    }
                    sb2.append(str8);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                setContentView(getBinding().getRoot());
                if (savedInstanceState != null) {
                    loadState(getIntent().getExtras());
                } else {
                    loadState(savedInstanceState);
                }
                binding = getBinding();
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    String canonicalName3 = (binding != null || (cls = binding.getClass()) == null) ? null : cls.getCanonicalName();
                    str2 = canonicalName3 != null ? str : canonicalName3;
                }
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                if (matcher.find()) {
                    str2 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb3.append(str2);
                sb3.append(" - ");
                sb3.append("Customising UI here");
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
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
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str3 = null;
                            } else {
                                str3 = null;
                                str4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            str4 = (binding == null || (cls2 = binding.getClass()) == null) ? str3 : cls2.getCanonicalName();
                            if (str4 == null) {
                                str5 = str;
                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher2.find()) {
                                    str5 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                }
                                if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str5, "Customising UI here ");
                                HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
                                ImageView imageView = binding.hkLayoutTopBar.ivBack;
                                Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
                                imageView.setVisibility(8);
                                hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
                                if (hyperSnapSDKConfig != null || (uiConfig = hyperSnapSDKConfig.getUiConfig()) == null) {
                                    hKBrowserActivity = this;
                                    str6 = str3;
                                } else {
                                    str6 = uiConfig.getBackgroundImage();
                                    hKBrowserActivity = this;
                                }
                                hKBrowserActivity.loadBackground$hyperkyc_release(str6);
                                LottieAnimationView browserLoader = binding.browserLoader;
                                Intrinsics.checkNotNullExpressionValue(browserLoader, "browserLoader");
                                browserLoader.setVisibility(0);
                                LottieAnimationView browserLoader2 = binding.browserLoader;
                                Intrinsics.checkNotNullExpressionValue(browserLoader2, "browserLoader");
                                hKBrowserActivity.loadAnimation(browserLoader2, HyperSnapBridgeKt.getUiConfigUtil().getEndStateProcessingAnimation());
                            }
                            str5 = str4;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher2.find()) {
                            }
                            if (str5.length() > 23) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str5, "Customising UI here ");
                            HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
                            ImageView imageView2 = binding.hkLayoutTopBar.ivBack;
                            Intrinsics.checkNotNullExpressionValue(imageView2, "hkLayoutTopBar.ivBack");
                            imageView2.setVisibility(8);
                            hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
                            if (hyperSnapSDKConfig != null) {
                            }
                            hKBrowserActivity = this;
                            str6 = str3;
                            hKBrowserActivity.loadBackground$hyperkyc_release(str6);
                            LottieAnimationView browserLoader3 = binding.browserLoader;
                            Intrinsics.checkNotNullExpressionValue(browserLoader3, "browserLoader");
                            browserLoader3.setVisibility(0);
                            LottieAnimationView browserLoader22 = binding.browserLoader;
                            Intrinsics.checkNotNullExpressionValue(browserLoader22, "browserLoader");
                            hKBrowserActivity.loadAnimation(browserLoader22, HyperSnapBridgeKt.getUiConfigUtil().getEndStateProcessingAnimation());
                        }
                    }
                }
                str3 = null;
                HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
                ImageView imageView22 = binding.hkLayoutTopBar.ivBack;
                Intrinsics.checkNotNullExpressionValue(imageView22, "hkLayoutTopBar.ivBack");
                imageView22.setVisibility(8);
                hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
                if (hyperSnapSDKConfig != null) {
                }
                hKBrowserActivity = this;
                str6 = str3;
                hKBrowserActivity.loadBackground$hyperkyc_release(str6);
                LottieAnimationView browserLoader32 = binding.browserLoader;
                Intrinsics.checkNotNullExpressionValue(browserLoader32, "browserLoader");
                browserLoader32.setVisibility(0);
                LottieAnimationView browserLoader222 = binding.browserLoader;
                Intrinsics.checkNotNullExpressionValue(browserLoader222, "browserLoader");
                hKBrowserActivity.loadAnimation(browserLoader222, HyperSnapBridgeKt.getUiConfigUtil().getEndStateProcessingAnimation());
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        setContentView(getBinding().getRoot());
        if (savedInstanceState != null) {
        }
        binding = getBinding();
        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb32 = new StringBuilder();
        StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace32, "Throwable().stackTrace");
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
        if (stackTraceElement != null) {
        }
        if (binding != null) {
        }
        if (canonicalName3 != null) {
        }
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
        if (matcher.find()) {
        }
        if (str2.length() > 23) {
            str2 = str2.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb32.append(str2);
        sb32.append(" - ");
        sb32.append("Customising UI here");
        sb32.append(' ');
        sb32.append("");
        companion42.log(level22, sb32.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        str3 = null;
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
        ImageView imageView222 = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView222, "hkLayoutTopBar.ivBack");
        imageView222.setVisibility(8);
        hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
        if (hyperSnapSDKConfig != null) {
        }
        hKBrowserActivity = this;
        str6 = str3;
        hKBrowserActivity.loadBackground$hyperkyc_release(str6);
        LottieAnimationView browserLoader322 = binding.browserLoader;
        Intrinsics.checkNotNullExpressionValue(browserLoader322, "browserLoader");
        browserLoader322.setVisibility(0);
        LottieAnimationView browserLoader2222 = binding.browserLoader;
        Intrinsics.checkNotNullExpressionValue(browserLoader2222, "browserLoader");
        hKBrowserActivity.loadAnimation(browserLoader2222, HyperSnapBridgeKt.getUiConfigUtil().getEndStateProcessingAnimation());
    }

    public static /* synthetic */ void loadBackground$hyperkyc_release$default(HKBrowserActivity hKBrowserActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        hKBrowserActivity.loadBackground$hyperkyc_release(str);
    }

    public final void loadBackground$hyperkyc_release(String url) {
        this.target = new Target() { // from class: co.hyperverge.hyperkyc.ui.HKBrowserActivity$loadBackground$1
            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                HkActivityBrowserBinding binding;
                String className2;
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                Intrinsics.checkNotNullParameter(from, "from");
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = getClass();
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
                sb.append("Background image loaded");
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
                                Class<?> cls2 = getClass();
                                String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                                if (canonicalName2 != null) {
                                    str = canonicalName2;
                                }
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
                            Log.println(3, str, "Background image loaded ");
                        }
                    }
                }
                binding = HKBrowserActivity.this.getBinding();
                binding.browserContent.setBackground(new BitmapDrawable(HKBrowserActivity.this.getResources(), bitmap));
            }

            /* JADX WARN: Code restructure failed: missing block: B:49:0x0122, code lost:
            
                if (r0 == null) goto L52;
             */
            @Override // com.squareup.picasso.Target
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                String canonicalName;
                Object m1202constructorimpl;
                String canonicalName2;
                String className;
                HkActivityBrowserBinding binding;
                String className2;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = getClass();
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
                sb.append("Background image load failed");
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
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls2 = getClass();
                                canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            }
                            str = canonicalName2;
                            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                            if (matcher2.find()) {
                                str = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                            }
                            if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str = str.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str, "Background image load failed ");
                        }
                    }
                }
                binding = HKBrowserActivity.this.getBinding();
                binding.browserContent.setBackground(null);
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                String className2;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = getClass();
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
                sb.append("Background image loading");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                    return;
                }
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
                            Class<?> cls2 = getClass();
                            String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 != null) {
                                str = canonicalName2;
                            }
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
                        Log.println(3, str, "Background image loading ");
                    }
                }
            }
        };
        String str = url;
        Target target = null;
        if (!(str == null || str.length() == 0)) {
            ConstraintLayout constraintLayout = getBinding().browserContent;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.browserContent");
            Target target2 = this.target;
            if (target2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(TypedValues.AttributesType.S_TARGET);
            } else {
                target = target2;
            }
            PicassoExtsKt.loadBackgroundImage(constraintLayout, url, target);
            return;
        }
        getBinding().browserContent.setBackground(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        super.onResume();
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        sb.append("onResume() called");
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
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
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
                    Log.println(3, str, "onResume() called ");
                }
            }
        }
        try {
            if (!this.authStarted) {
                this.authStarted = true;
                launchInPartialCustomTabs();
            } else {
                handleAuthCancelled();
            }
        } catch (Exception unused) {
            setError(124, "Browser not supported on this device. Fallback to webview module");
        }
    }

    private final boolean isAuthorisedActivity() {
        String className;
        ComponentName callingActivity = getCallingActivity();
        if (callingActivity == null || (className = callingActivity.getClassName()) == null) {
            return false;
        }
        return SetsKt.setOfNotNull((Object[]) new String[]{Reflection.getOrCreateKotlinClass(HKMainActivity.class).getQualifiedName(), Reflection.getOrCreateKotlinClass(HKWebCoreActivity.class).getQualifiedName()}).contains(className);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void loadState(Bundle state) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        String canonicalName3;
        Object m1202constructorimpl2;
        String str2;
        String str3;
        Matcher matcher;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("loadState() called");
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
                charSequence = "co.hyperverge";
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, canonicalName2, "loadState() called ");
                }
                if (state == null) {
                    this.webviewUrl = state.getString("url");
                    Serializable serializable = state.getSerializable("data");
                    this.data = serializable instanceof WorkflowModule.Properties.Data ? (WorkflowModule.Properties.Data) serializable : null;
                    this.moduleId = state.getString("moduleId");
                    this.authStarted = state.getBoolean(ARG_AUTH_STARTED, false);
                    return;
                }
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                    if (canonicalName3 == null) {
                        canonicalName3 = str;
                    }
                }
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                if (matcher4.find()) {
                    canonicalName3 = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                }
                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName3 = canonicalName3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb2.append(canonicalName3);
                sb2.append(" - ");
                sb2.append("No saved state found. Finishing the flow");
                sb2.append(' ');
                sb2.append("");
                companion4.log(level2, sb2.toString());
                if (!CoreExtsKt.isRelease()) {
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
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str2 = null;
                            } else {
                                str2 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str3 = substringAfterLast$default;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                    if (matcher.find()) {
                                        str3 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                    }
                                    if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str3 = str3.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(3, str3, "No saved state found. Finishing the flow ");
                                }
                            }
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str2;
                            str3 = canonicalName4 == null ? str : canonicalName4;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                            if (matcher.find()) {
                            }
                            if (str3.length() > 23) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str3, "No saved state found. Finishing the flow ");
                        }
                    }
                }
                setResult(0);
                finish();
                return;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        if (state == null) {
        }
    }

    private final void loadAnimation(LottieAnimationView lav, String customUrl) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        String str2 = "loadAnimation() called with: lav = " + lav + ", customUrl = " + customUrl;
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
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
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
                    String str3 = "loadAnimation() called with: lav = " + lav + ", customUrl = " + customUrl;
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
        HKLottieHelper.INSTANCE.getShared().load(lav, "hv_processing.lottie", customUrl == null ? "" : customUrl, HKLottieHelper.State.START, null);
    }

    private final void setError(int errorCode, String errorMessage) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        String str2 = "setError() called with errorCode : " + errorCode + ", errorMessage : " + errorMessage;
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
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
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
                    String str3 = "setError() called with errorCode : " + errorCode + ", errorMessage : " + errorMessage;
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
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", "error");
        jSONObject.put("error", errorMessage);
        jSONObject.put("errorCode", errorCode);
        getIntent().putExtra("error", jSONObject.toString());
        if (isAuthorisedActivity()) {
            setResult(2, getIntent());
        }
        finish();
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0124, code lost:
    
        if (r0 == null) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final JSONObject parseQueryParameters(String str) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (str == null || (cls = str.getClass()) == null) ? null : cls.getCanonicalName();
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
        sb.append("parseQueryParameters() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (str == null || (cls2 = str.getClass()) == null) ? null : cls2.getCanonicalName();
                    }
                    str2 = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str2, "parseQueryParameters() called ");
                }
            }
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (scheme == null || scheme.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        Set<String> queryParameterNames = parse.getQueryParameterNames();
        Intrinsics.checkNotNullExpressionValue(queryParameterNames, "uri.queryParameterNames");
        for (String str3 : queryParameterNames) {
            String queryParameter = parse.getQueryParameter(str3);
            if (queryParameter != null) {
                jSONObject.put(str3, queryParameter);
            }
        }
        return jSONObject;
    }

    private final void launchInPartialCustomTabs() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        LinkedHashMap<String, String> queryParams;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        sb.append("launchInPartialCustomTabs() called");
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
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
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
                    Log.println(3, str, "launchInPartialCustomTabs() called ");
                }
            }
        }
        Uri.Builder buildUpon = Uri.parse(this.webviewUrl).buildUpon();
        WorkflowModule.Properties.Data data = this.data;
        if (data != null && (queryParams = data.getQueryParams()) != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        CustomTabsIntent.Builder urlBarHidingEnabled = new CustomTabsIntent.Builder().setShowTitle(true).setInitialActivityHeightPx((int) (getResources().getDisplayMetrics().heightPixels * 0.9d)).setUrlBarHidingEnabled(true);
        Intrinsics.checkNotNullExpressionValue(urlBarHidingEnabled, "Builder()\n            .s…UrlBarHidingEnabled(true)");
        CustomTabsIntent build = urlBarHidingEnabled.build();
        build.intent.setData(buildUpon.build());
        Intrinsics.checkNotNullExpressionValue(build, "customTabsIntentBuilder.…Builder.build()\n        }");
        this.activityLauncher.launch(build.intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(outState, "outState");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        sb.append("onSaveInstanceState() called");
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
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
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
                    Log.println(3, str, "onSaveInstanceState() called ");
                }
            }
        }
        outState.putBoolean(ARG_AUTH_STARTED, this.authStarted);
        outState.putString("moduleId", this.moduleId);
        outState.putString("url", this.webviewUrl);
        outState.putSerializable("data", this.data);
        super.onSaveInstanceState(outState);
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0142, code lost:
    
        if (r0 == null) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleAuthCancelled() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        StringBuilder sb2 = new StringBuilder();
        sb2.append("handleAuthCancelled() called ");
        ComponentName callingActivity = getCallingActivity();
        sb2.append(callingActivity != null ? callingActivity.getPackageName() : null);
        String sb3 = sb2.toString();
        if (sb3 == null) {
            sb3 = "null ";
        }
        sb.append(sb3);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("handleAuthCancelled() called ");
                    ComponentName callingActivity2 = getCallingActivity();
                    sb5.append(callingActivity2 != null ? callingActivity2.getPackageName() : null);
                    String sb6 = sb5.toString();
                    sb4.append(sb6 != null ? sb6 : "null ");
                    sb4.append(' ');
                    sb4.append("");
                    Log.println(3, str, sb4.toString());
                }
            }
        }
        if (isAuthorisedActivity()) {
            setResult(0, getIntent());
        }
        finish();
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleAuthComplete() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String dataString;
        JSONObject parseQueryParameters;
        String jSONObject;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        sb.append("handleAuthComplete() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "handleAuthComplete() called ");
                }
            }
        }
        if (isAuthorisedActivity()) {
            Intent intent = getIntent();
            if (intent == null || (dataString = intent.getDataString()) == null || (parseQueryParameters = parseQueryParameters(dataString)) == null || (jSONObject = parseQueryParameters.toString()) == null) {
                Intent intent2 = getIntent();
                if (intent2 != null) {
                    str2 = intent2.getDataString();
                }
            } else {
                str2 = jSONObject;
            }
            Intent intent3 = getIntent();
            intent3.putExtra("response", str2);
            intent3.putExtra("moduleId", this.moduleId);
            setResult(-1, getIntent());
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onNewIntent(Intent intent) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        Unit unit = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
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
        sb.append("onNewIntent() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "onNewIntent() called ");
                }
            }
        }
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null && intent.getData() != null) {
            handleAuthComplete();
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            handleAuthCancelled();
        }
    }
}
