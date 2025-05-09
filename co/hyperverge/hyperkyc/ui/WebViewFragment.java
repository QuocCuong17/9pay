package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.databinding.HkFragmentWebviewBinding;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperkyc.webCore.models.WebViewNativeResponse;
import co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: WebViewFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 E2\u00020\u0001:\u0004CDEFB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010/\u001a\u00020\u0010H\u0016J\b\u00100\u001a\u000201H\u0002J \u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0006\u0012\u0004\u0018\u000104032\b\u00105\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u00106\u001a\u000201H\u0016J\b\u00107\u001a\u000201H\u0016J\u001a\u00108\u001a\u0002012\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0016\u0010=\u001a\u0002012\f\u0010>\u001a\b\u0012\u0004\u0012\u0002010?H\u0002J\u0015\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020\u0010H\u0000¢\u0006\u0002\bBR\u001b\u0010\u0003\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\t\u001a\u0004\u0018\u00010\n8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001b\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000e\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u000e\u001a\u0004\b \u0010\u001dR\u001b\u0010\"\u001a\u00020\u00178@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u000e\u001a\u0004\b#\u0010\u0019R'\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u000e\u001a\u0004\b'\u0010(R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020\u00178@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u000e\u001a\u0004\b-\u0010\u0019¨\u0006G"}, d2 = {"Lco/hyperverge/hyperkyc/ui/WebViewFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentWebviewBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentWebviewBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "data", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "getData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "data$delegate", "Lkotlin/Lazy;", "isWebViewErrorReceived", "", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM$hyperkyc_release", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "moduleId", "", "getModuleId$hyperkyc_release", "()Ljava/lang/String;", "moduleId$delegate", WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE, "getReloadOnRedirectFailure", "()Z", "reloadOnRedirectFailure$delegate", "showBackButton", "getShowBackButton", "showBackButton$delegate", WebViewFragment.ARG_SUB_TYPE, "getSubType$hyperkyc_release", "subType$delegate", "textConfigs", "", "getTextConfigs", "()Ljava/util/Map;", "textConfigs$delegate", "webView", "Landroid/webkit/WebView;", "webviewUrl", "getWebviewUrl$hyperkyc_release", "webviewUrl$delegate", "deferredInitViews", "destroyWebView", "", "getAnalyticsForWebpage", "", "", "url", "initViews", "onDestroyView", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showDialog", "onPositiveButtonAction", "Lkotlin/Function0;", "showRetry", "show", "showRetry$hyperkyc_release", "BrowserWebChromeClient", "BrowserWebClient", "Companion", "JSInterface", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WebViewFragment extends BaseFragment {
    public static final /* synthetic */ String ARG_DATA = "data";
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_MODULE_ID = "moduleId";
    public static final /* synthetic */ String ARG_RELOAD_ON_REDIRECT_FAILURE = "reloadOnRedirectFailure";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";
    public static final /* synthetic */ String ARG_SUB_TYPE = "subType";
    public static final /* synthetic */ String ARG_URL = "url";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;

    /* renamed from: data$delegate, reason: from kotlin metadata */
    private final Lazy data;
    private boolean isWebViewErrorReceived;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;

    /* renamed from: moduleId$delegate, reason: from kotlin metadata */
    private final Lazy moduleId;

    /* renamed from: reloadOnRedirectFailure$delegate, reason: from kotlin metadata */
    private final Lazy reloadOnRedirectFailure;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;

    /* renamed from: subType$delegate, reason: from kotlin metadata */
    private final Lazy subType;

    /* renamed from: textConfigs$delegate, reason: from kotlin metadata */
    private final Lazy textConfigs;
    private WebView webView;

    /* renamed from: webviewUrl$delegate, reason: from kotlin metadata */
    private final Lazy webviewUrl;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(WebViewFragment.class, "binding", "getBinding$hyperkyc_release()Lco/hyperverge/hyperkyc/databinding/HkFragmentWebviewBinding;", 0))};

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    public boolean deferredInitViews() {
        return true;
    }

    public WebViewFragment() {
        super(R.layout.hk_fragment_webview);
        final WebViewFragment webViewFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(webViewFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$special$$inlined$activityViewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.binding = FragmentViewBindingDelegateKt.viewBinding(webViewFragment, WebViewFragment$binding$2.INSTANCE);
        this.moduleId = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$moduleId$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                String string = arguments != null ? arguments.getString("moduleId") : null;
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        this.subType = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$subType$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                String string = arguments != null ? arguments.getString(WebViewFragment.ARG_SUB_TYPE) : null;
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        this.webviewUrl = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$webviewUrl$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                String string = arguments != null ? arguments.getString("url") : null;
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        this.data = LazyKt.lazy(new Function0<WorkflowModule.Properties.Data>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$data$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WorkflowModule.Properties.Data invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                Serializable serializable = arguments != null ? arguments.getSerializable("data") : null;
                if (serializable instanceof WorkflowModule.Properties.Data) {
                    return (WorkflowModule.Properties.Data) serializable;
                }
                return null;
            }
        });
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("showBackButton")) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
        this.reloadOnRedirectFailure = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$reloadOnRedirectFailure$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean(WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE)) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
        this.textConfigs = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$textConfigs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                Bundle arguments = WebViewFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("textConfigs") : null;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
                return MapsKt.toMap((Map) obj);
            }
        });
    }

    public final MainVM getMainVM$hyperkyc_release() {
        return (MainVM) this.mainVM.getValue();
    }

    public final HkFragmentWebviewBinding getBinding$hyperkyc_release() {
        return (HkFragmentWebviewBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    public final String getModuleId$hyperkyc_release() {
        return (String) this.moduleId.getValue();
    }

    public final String getSubType$hyperkyc_release() {
        return (String) this.subType.getValue();
    }

    public final String getWebviewUrl$hyperkyc_release() {
        return (String) this.webviewUrl.getValue();
    }

    public final WorkflowModule.Properties.Data getData$hyperkyc_release() {
        return (WorkflowModule.Properties.Data) this.data.getValue();
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getReloadOnRedirectFailure() {
        return ((Boolean) this.reloadOnRedirectFailure.getValue()).booleanValue();
    }

    private final Map<String, String> getTextConfigs() {
        return (Map) this.textConfigs.getValue();
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
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
        String str2 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
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
                    String str3 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
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
        initViewsOnlyIfNotRecreated(savedInstanceState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$6$lambda$4(WebViewFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed$hyperkyc_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$6$lambda$5(WebViewFragment this$0, HkFragmentWebviewBinding this_with, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        this$0.showRetry$hyperkyc_release(false);
        ProgressBar pbLoading = this_with.pbLoading;
        Intrinsics.checkNotNullExpressionValue(pbLoading, "pbLoading");
        pbLoading.setVisibility(0);
        this_with.webView.reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDialog(final Function0<Unit> onPositiveButtonAction) {
        if (ActivityExtsKt.isFragmentAdded(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            CharSequence text = getText(R.string.hk_webview_redirection_fail_alert_message);
            Intrinsics.checkNotNullExpressionValue(text, "getText(R.string.hk_webv…ction_fail_alert_message)");
            CharSequence text2 = getText(R.string.hk_webview_redirection_fail_alert_button_text);
            Intrinsics.checkNotNullExpressionValue(text2, "getText(R.string.hk_webv…n_fail_alert_button_text)");
            String str = getTextConfigs().get("redirectFailAlertMessage");
            if (str != null) {
                if (str.length() > 0) {
                    text = UIExtsKt.fromHTML(str);
                }
            }
            String str2 = getTextConfigs().get("redirectFailAlertButtonText");
            if (str2 != null) {
                if (str2.length() > 0) {
                    text2 = UIExtsKt.fromHTML(str2);
                }
            }
            builder.setMessage(text);
            builder.setPositiveButton(text2, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    WebViewFragment.showDialog$lambda$18$lambda$16(Function0.this, dialogInterface, i);
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    WebViewFragment.showDialog$lambda$18$lambda$17(Function0.this, dialogInterface);
                }
            });
            AlertDialog create = builder.create();
            Intrinsics.checkNotNullExpressionValue(create, "create()");
            create.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$18$lambda$16(Function0 onPositiveButtonAction, DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(onPositiveButtonAction, "$onPositiveButtonAction");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        onPositiveButtonAction.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$18$lambda$17(Function0 onPositiveButtonAction, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(onPositiveButtonAction, "$onPositiveButtonAction");
        onPositiveButtonAction.invoke();
    }

    /* compiled from: WebViewFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/WebViewFragment$BrowserWebChromeClient;", "Landroid/webkit/WebChromeClient;", "(Lco/hyperverge/hyperkyc/ui/WebViewFragment;)V", "getDefaultVideoPoster", "Landroid/graphics/Bitmap;", "onPermissionRequest", "", "request", "Landroid/webkit/PermissionRequest;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class BrowserWebChromeClient extends WebChromeClient {
        public BrowserWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public Bitmap getDefaultVideoPoster() {
            return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        }

        @Override // android.webkit.WebChromeClient
        public void onPermissionRequest(PermissionRequest request) {
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
            String str2 = "onPermissionRequest() called with: request = [" + request + ']';
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
                        String str3 = "onPermissionRequest() called with: request = [" + request + ']';
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
            if (request != null) {
                request.grant(request.getResources());
            }
        }
    }

    /* compiled from: WebViewFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\r\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0014"}, d2 = {"Lco/hyperverge/hyperkyc/ui/WebViewFragment$BrowserWebClient;", "Landroid/webkit/WebViewClient;", "(Lco/hyperverge/hyperkyc/ui/WebViewFragment;)V", "onPageFinished", "", "webView", "Landroid/webkit/WebView;", "url", "", "onPageStarted", ViewHierarchyConstants.VIEW_KEY, "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "shouldOverrideUrlLoading", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class BrowserWebClient extends WebViewClient {
        public BrowserWebClient() {
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x0187 A[Catch: all -> 0x01ff, TryCatch #1 {all -> 0x01ff, blocks: (B:3:0x001b, B:6:0x0048, B:8:0x004e, B:11:0x0066, B:14:0x007d, B:15:0x0087, B:18:0x0095, B:21:0x009c, B:22:0x00a6, B:26:0x00ce, B:37:0x011b, B:40:0x0122, B:42:0x012a, B:44:0x013e, B:46:0x0154, B:48:0x015a, B:51:0x0176, B:53:0x0187, B:54:0x018e, B:56:0x0196, B:59:0x019d, B:60:0x01a5, B:63:0x01c9, B:65:0x0166, B:67:0x016c, B:74:0x0111, B:28:0x01dc, B:32:0x01e7, B:76:0x0056, B:78:0x005c, B:36:0x00e8), top: B:2:0x001b, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01c8  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String canonicalName;
            String str;
            Object m1202constructorimpl;
            String str2;
            String canonicalName2;
            Matcher matcher;
            String str3;
            String className;
            String className2;
            super.onPageStarted(view, url, favicon);
            WebViewFragment.this.isWebViewErrorReceived = false;
            WebViewFragment webViewFragment = WebViewFragment.this;
            try {
                Result.Companion companion = Result.INSTANCE;
                BrowserWebClient browserWebClient = this;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = getClass();
                    canonicalName = cls != null ? cls.getCanonicalName() : null;
                    if (canonicalName == null) {
                        canonicalName = "N/A";
                    }
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                String str4 = canonicalName;
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                } else {
                    str = str4;
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(str);
                sb.append(" - ");
                String str5 = "onPageStarted() called with: view = " + view + ", url = " + url + ", favicon = " + favicon;
                String str6 = "null ";
                if (str5 == null) {
                    str5 = "null ";
                }
                sb.append(str5);
                sb.append(' ');
                sb.append("");
                companion2.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        Result.Companion companion4 = Result.INSTANCE;
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
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                str2 = null;
                            } else {
                                str2 = null;
                                canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (canonicalName2 != null) {
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                    if (matcher.find()) {
                                        canonicalName2 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                    }
                                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        canonicalName2 = canonicalName2.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb2 = new StringBuilder();
                                    str3 = "onPageStarted() called with: view = " + view + ", url = " + url + ", favicon = " + favicon;
                                    if (str3 == null) {
                                        str6 = str3;
                                    }
                                    sb2.append(str6);
                                    sb2.append(' ');
                                    sb2.append("");
                                    Log.println(3, canonicalName2, sb2.toString());
                                }
                            }
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str2;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
                            }
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                            if (matcher.find()) {
                            }
                            if (canonicalName2.length() > 23) {
                                canonicalName2 = canonicalName2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb22 = new StringBuilder();
                            str3 = "onPageStarted() called with: view = " + view + ", url = " + url + ", favicon = " + favicon;
                            if (str3 == null) {
                            }
                            sb22.append(str6);
                            sb22.append(' ');
                            sb22.append("");
                            Log.println(3, canonicalName2, sb22.toString());
                        }
                    }
                }
                if (ActivityExtsKt.isFragmentAdded(webViewFragment)) {
                    ProgressBar progressBar = webViewFragment.getBinding$hyperkyc_release().pbLoading;
                    Intrinsics.checkNotNullExpressionValue(progressBar, "binding.pbLoading");
                    progressBar.setVisibility(8);
                    Result.m1202constructorimpl(Unit.INSTANCE);
                }
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x0148, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0158, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x015c, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x016b, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x016d, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x017a, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0180, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0183, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x018a, code lost:
        
            r0 = new java.lang.StringBuilder();
            r1 = "onReceivedError() called with: view = " + r18 + ", request = " + r19 + ", error = " + r20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x01aa, code lost:
        
            if (r1 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x01ac, code lost:
        
            r1 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x01ae, code lost:
        
            r0.append(r1);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x015b, code lost:
        
            r8 = r0;
         */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            String className2;
            super.onReceivedError(view, request, error);
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
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
            String str4 = "onReceivedError() called with: view = " + view + ", request = " + request + ", error = " + error;
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = null;
                        } else {
                            str = null;
                            str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = getClass();
                        str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    }
                }
            }
            WebViewFragment.this.isWebViewErrorReceived = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:114:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x011a  */
        /* JADX WARN: Removed duplicated region for block: B:143:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x01d5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x01d6  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0364  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x039a  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onPageFinished(WebView webView, String url) {
            String canonicalName;
            String str;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str2;
            String canonicalName2;
            String className;
            String canonicalName3;
            Object m1202constructorimpl2;
            String str3;
            String str4;
            Matcher matcher;
            String str5;
            String className2;
            String className3;
            String className4;
            super.onPageFinished(webView, url);
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
            String str6 = "onPageFinished() called with: webView = " + webView + ", url = " + url;
            if (str6 == null) {
                str6 = "null ";
            }
            sb.append(str6);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str = " - ";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = " - ";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (!CoreExtsKt.isDebug()) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = " - ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str2 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
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
                        StringBuilder sb2 = new StringBuilder();
                        String str7 = "onPageFinished() called with: webView = " + webView + ", url = " + url;
                        if (str7 == null) {
                            str7 = "null ";
                        }
                        sb2.append(str7);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    if (ActivityExtsKt.isFragmentAdded(WebViewFragment.this)) {
                        return;
                    }
                    AnalyticsLogger.INSTANCE.logWebViewLoaded$hyperkyc_release(WebViewFragment.this.getAnalyticsForWebpage(url));
                    if (Intrinsics.areEqual(url, WebViewFragment.this.getWebviewUrl$hyperkyc_release()) && WebViewFragment.this.getData$hyperkyc_release() != null) {
                        String json = WebViewFragment.this.getMainVM$hyperkyc_release().getGson().toJson(WebViewFragment.this.getData$hyperkyc_release());
                        Intrinsics.checkNotNullExpressionValue(json, "mainVM.gson.toJson(data)");
                        String replace$default = StringsKt.replace$default(json, "\"", "'", false, 4, (Object) null);
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName3 == null) {
                                canonicalName3 = "N/A";
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
                        sb3.append(canonicalName3);
                        sb3.append(str);
                        String str8 = "onPageFinished() sending data : " + replace$default;
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb3.append(str8);
                        sb3.append(' ');
                        sb3.append("");
                        companion4.log(level2, sb3.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th3) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = "";
                            }
                            String str9 = (String) m1202constructorimpl2;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str9, str2);
                                if (StringsKt.contains$default((CharSequence) str9, charSequence, false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                        str3 = null;
                                    } else {
                                        str3 = null;
                                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        if (substringAfterLast$default != null) {
                                            str4 = substringAfterLast$default;
                                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                            if (matcher.find()) {
                                                str4 = matcher.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                            }
                                            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                str4 = str4.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb4 = new StringBuilder();
                                            str5 = "onPageFinished() sending data : " + replace$default;
                                            if (str5 == null) {
                                                str5 = "null ";
                                            }
                                            sb4.append(str5);
                                            sb4.append(' ');
                                            sb4.append("");
                                            Log.println(3, str4, sb4.toString());
                                        }
                                    }
                                    Class<?> cls4 = getClass();
                                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str3;
                                    str4 = canonicalName4 == null ? "N/A" : canonicalName4;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher.find()) {
                                    }
                                    if (str4.length() > 23) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb42 = new StringBuilder();
                                    str5 = "onPageFinished() sending data : " + replace$default;
                                    if (str5 == null) {
                                    }
                                    sb42.append(str5);
                                    sb42.append(' ');
                                    sb42.append("");
                                    Log.println(3, str4, sb42.toString());
                                }
                            }
                        }
                        if (webView != null) {
                            webView.loadUrl("javascript:initData(" + replace$default + ')');
                        }
                    }
                    WebViewFragment webViewFragment = WebViewFragment.this;
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        BrowserWebClient browserWebClient = this;
                        WebView webView2 = webViewFragment.getBinding$hyperkyc_release().webView;
                        Intrinsics.checkNotNullExpressionValue(webView2, "binding.webView");
                        webView2.setVisibility(0);
                        FragmentActivity requireActivity = webViewFragment.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        if (!ContextExtsKt.isOnline(requireActivity)) {
                            webViewFragment.showRetry$hyperkyc_release(true);
                        }
                        ProgressBar progressBar = webViewFragment.getBinding$hyperkyc_release().pbLoading;
                        Intrinsics.checkNotNullExpressionValue(progressBar, "binding.pbLoading");
                        progressBar.setVisibility(8);
                        Result.m1202constructorimpl(Unit.INSTANCE);
                        return;
                    } catch (Throwable th4) {
                        Result.Companion companion8 = Result.INSTANCE;
                        Result.m1202constructorimpl(ResultKt.createFailure(th4));
                        return;
                    }
                }
                charSequence = "co.hyperverge";
            }
            str2 = "packageName";
            if (ActivityExtsKt.isFragmentAdded(WebViewFragment.this)) {
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:144:0x0112  */
        /* JADX WARN: Removed duplicated region for block: B:147:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:173:0x01c8  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x01d7 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x01d8  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x03b4  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x03f1  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x0401  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String canonicalName;
            String str;
            String str2;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str3;
            String canonicalName2;
            String className;
            boolean z;
            String str4;
            String str5;
            Object m1202constructorimpl2;
            String str6;
            String str7;
            Matcher matcher;
            String str8;
            String localizedMessage;
            String className2;
            String className3;
            Uri url;
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
            String str9 = "";
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
            String str10 = "shouldOverrideUrlLoading() called with: view = " + view + ", request = " + request;
            if (str10 == null) {
                str10 = "null ";
            }
            sb.append(str10);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str = " - ";
                str2 = "N/A";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = " - ";
                    str2 = "N/A";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (!CoreExtsKt.isDebug()) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = " - ";
                    str2 = "N/A";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str2;
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
                        StringBuilder sb2 = new StringBuilder();
                        String str11 = "shouldOverrideUrlLoading() called with: view = " + view + ", request = " + request;
                        if (str11 == null) {
                            str11 = "null ";
                        }
                        sb2.append(str11);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    if (ActivityExtsKt.isFragmentAdded(WebViewFragment.this)) {
                        return true;
                    }
                    String nullIfBlank = CoreExtsKt.nullIfBlank((request == null || (url = request.getUrl()) == null) ? null : url.toString());
                    List listOf = CollectionsKt.listOf((Object[]) new String[]{"http://", "https://", "www"});
                    if (nullIfBlank != null) {
                        List list = listOf;
                        if (!(list instanceof Collection) || !list.isEmpty()) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if (StringsKt.startsWith$default(nullIfBlank, (String) it.next(), false, 2, (Object) null)) {
                                    z = false;
                                    break;
                                }
                            }
                        }
                        z = true;
                        if (z) {
                            try {
                                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(nullIfBlank));
                                intent.addCategory("android.intent.category.BROWSABLE");
                                if (Build.VERSION.SDK_INT >= 30) {
                                    intent.setFlags(268436992);
                                }
                                WebViewFragment.this.startActivity(intent);
                                return true;
                            } catch (ActivityNotFoundException e) {
                                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3 = new StringBuilder();
                                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls3 = getClass();
                                    String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                                    str4 = canonicalName3 == null ? str2 : canonicalName3;
                                }
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                if (matcher4.find()) {
                                    str4 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                }
                                if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str4 = str4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb3.append(str4);
                                sb3.append(str);
                                String str12 = "shouldOverrideUrlLoading: " + e.getLocalizedMessage();
                                if (str12 == null) {
                                    str12 = "null ";
                                }
                                sb3.append(str12);
                                sb3.append(' ');
                                ActivityNotFoundException activityNotFoundException = e;
                                String localizedMessage2 = activityNotFoundException.getLocalizedMessage();
                                if (localizedMessage2 != null) {
                                    str5 = '\n' + localizedMessage2;
                                } else {
                                    str5 = "";
                                }
                                sb3.append(str5);
                                companion4.log(level2, sb3.toString());
                                CoreExtsKt.isRelease();
                                try {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th3) {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                    m1202constructorimpl2 = "";
                                }
                                String str13 = (String) m1202constructorimpl2;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(str13, str3);
                                    if (StringsKt.contains$default((CharSequence) str13, charSequence, false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                            str6 = null;
                                        } else {
                                            str6 = null;
                                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            if (substringAfterLast$default != null) {
                                                str7 = substringAfterLast$default;
                                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                                if (matcher.find()) {
                                                    str7 = matcher.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                                }
                                                if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str7 = str7.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb4 = new StringBuilder();
                                                str8 = "shouldOverrideUrlLoading: " + e.getLocalizedMessage();
                                                if (str8 == null) {
                                                    str8 = "null ";
                                                }
                                                sb4.append(str8);
                                                sb4.append(' ');
                                                localizedMessage = activityNotFoundException.getLocalizedMessage();
                                                if (localizedMessage != null) {
                                                    str9 = '\n' + localizedMessage;
                                                }
                                                sb4.append(str9);
                                                Log.println(6, str7, sb4.toString());
                                            }
                                        }
                                        Class<?> cls4 = getClass();
                                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str6;
                                        str7 = canonicalName4 == null ? str2 : canonicalName4;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                        if (matcher.find()) {
                                        }
                                        if (str7.length() > 23) {
                                            str7 = str7.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb42 = new StringBuilder();
                                        str8 = "shouldOverrideUrlLoading: " + e.getLocalizedMessage();
                                        if (str8 == null) {
                                        }
                                        sb42.append(str8);
                                        sb42.append(' ');
                                        localizedMessage = activityNotFoundException.getLocalizedMessage();
                                        if (localizedMessage != null) {
                                        }
                                        sb42.append(str9);
                                        Log.println(6, str7, sb42.toString());
                                    }
                                }
                                final WebViewFragment webViewFragment = WebViewFragment.this;
                                try {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    webViewFragment.showDialog(new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$BrowserWebClient$shouldOverrideUrlLoading$4$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            boolean reloadOnRedirectFailure;
                                            reloadOnRedirectFailure = WebViewFragment.this.getReloadOnRedirectFailure();
                                            if (reloadOnRedirectFailure) {
                                                WebViewFragment.this.getBinding$hyperkyc_release().webView.reload();
                                            }
                                        }
                                    });
                                    Result.m1202constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th4) {
                                    Result.Companion companion8 = Result.INSTANCE;
                                    Result.m1202constructorimpl(ResultKt.createFailure(th4));
                                }
                                return true;
                            }
                        }
                    }
                    return super.shouldOverrideUrlLoading(view, request);
                }
                charSequence = "co.hyperverge";
            }
            str3 = "packageName";
            if (ActivityExtsKt.isFragmentAdded(WebViewFragment.this)) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, Object> getAnalyticsForWebpage(String url) {
        String substringBefore$default;
        String str = null;
        if (url != null && (substringBefore$default = StringsKt.substringBefore$default(url, "?", (String) null, 2, (Object) null)) != null) {
            str = StringsKt.substringBefore$default(substringBefore$default, "%25", (String) null, 2, (Object) null);
        }
        return MapsKt.mutableMapOf(TuplesKt.to("moduleId", getModuleId$hyperkyc_release()), TuplesKt.to(AnalyticsLogger.Keys.MODULE_TYPE, WorkflowModule.TYPE_WEBVIEW), TuplesKt.to("moduleSubType", getSubType$hyperkyc_release()), TuplesKt.to(AnalyticsLogger.Keys.SOURCE_ID, getModuleId$hyperkyc_release()), TuplesKt.to(AnalyticsLogger.Keys.SOURCE_TYPE, "module"), TuplesKt.to(AnalyticsLogger.Keys.WEB_URL, str));
    }

    /* compiled from: WebViewFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/ui/WebViewFragment$JSInterface;", "", "(Lco/hyperverge/hyperkyc/ui/WebViewFragment;)V", "sendData", "", "data", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class JSInterface {
        public JSInterface() {
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x01c0  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0206  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0370  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x03a4  */
        @JavascriptInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void sendData(String data) {
            String canonicalName;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str;
            String canonicalName2;
            String className;
            FragmentActivity requireActivity;
            String str2;
            Object m1202constructorimpl2;
            String str3;
            String str4;
            Matcher matcher;
            String str5;
            String className2;
            String className3;
            String className4;
            Intrinsics.checkNotNullParameter(data, "data");
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
            String str6 = "sendData() called with: data = " + data;
            if (str6 == null) {
                str6 = "null ";
            }
            sb.append(str6);
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
                        StringBuilder sb2 = new StringBuilder();
                        String str7 = "sendData() called with: data = " + data;
                        if (str7 == null) {
                            str7 = "null ";
                        }
                        sb2.append(str7);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    requireActivity = WebViewFragment.this.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    if (!ActivityExtsKt.isHKWebCoreActivity(requireActivity)) {
                        if (ActivityExtsKt.isFragmentAdded(WebViewFragment.this)) {
                            BuildersKt__Builders_commonKt.launch$default(WebViewFragment.this.getLifecycleScope(), null, null, new WebViewFragment$JSInterface$sendData$2(WebViewFragment.this, null), 3, null);
                            FragmentActivity activity = WebViewFragment.this.getActivity();
                            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity");
                            ((HKWebCoreActivity) activity).sendNativeModuleData$hyperkyc_release(new WebViewNativeResponse(data, null, null, false, 14, null));
                            return;
                        }
                        return;
                    }
                    MainVM.updateWebviewData$hyperkyc_release$default(WebViewFragment.this.getMainVM$hyperkyc_release(), WebViewFragment.this.getModuleId$hyperkyc_release(), new HyperKycData.WebviewData(data), false, 4, null);
                    boolean flowForward = WebViewFragment.this.getMainVM$hyperkyc_release().flowForward();
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        str2 = canonicalName3 == null ? str : canonicalName3;
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher4.find()) {
                        str2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str2);
                    sb3.append(" - ");
                    String str8 = "sendData() flowForward handled: " + flowForward;
                    if (str8 == null) {
                        str8 = "null ";
                    }
                    sb3.append(str8);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
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
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str3 = null;
                            } else {
                                str3 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str4 = substringAfterLast$default;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher.find()) {
                                        str4 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                    }
                                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    str5 = "sendData() flowForward handled: " + flowForward;
                                    if (str5 == null) {
                                        str5 = "null ";
                                    }
                                    sb4.append(str5);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str4, sb4.toString());
                                    return;
                                }
                            }
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str3;
                            str4 = canonicalName4 == null ? str : canonicalName4;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher.find()) {
                            }
                            if (str4.length() > 23) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb42 = new StringBuilder();
                            str5 = "sendData() flowForward handled: " + flowForward;
                            if (str5 == null) {
                            }
                            sb42.append(str5);
                            sb42.append(' ');
                            sb42.append("");
                            Log.println(3, str4, sb42.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            charSequence = "co.hyperverge";
            str = "N/A";
            requireActivity = WebViewFragment.this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            if (!ActivityExtsKt.isHKWebCoreActivity(requireActivity)) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initViews() {
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
        sb.append("initViews() called");
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
                    Log.println(3, str, "initViews() called ");
                }
            }
        }
        final HkFragmentWebviewBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
        WebView webView = binding$hyperkyc_release.webView;
        Intrinsics.checkNotNullExpressionValue(webView, "webView");
        this.webView = webView;
        WebView webView2 = binding$hyperkyc_release.webView;
        webView2.setWebViewClient(new BrowserWebClient());
        webView2.setWebChromeClient(new BrowserWebChromeClient());
        WebSettings settings = webView2.getSettings();
        settings.setJavaScriptEnabled(true);
        webView2.addJavascriptInterface(new JSInterface(), "JSInterface");
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        String userAgentString = settings.getUserAgentString();
        if (!(userAgentString == null || StringsKt.isBlank(userAgentString))) {
            settings.setUserAgentString("Mozilla/5.0 (Linux; Android 11; Mi 11T Pro 5G) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Mobile Safari/537.36");
        }
        webView2.loadUrl(getWebviewUrl$hyperkyc_release());
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding$hyperkyc_release.hkLayoutTopBar.ivBack);
        ImageView imageView = binding$hyperkyc_release.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getShowBackButton() ? 0 : 8);
        binding$hyperkyc_release.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewFragment.initViews$lambda$6$lambda$4(WebViewFragment.this, view);
            }
        });
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding$hyperkyc_release.hkLayoutTopBar.hkClientLogo);
        ConstraintLayout root = binding$hyperkyc_release.hkLayoutTopBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "hkLayoutTopBar.root");
        root.setVisibility(binding$hyperkyc_release.hkLayoutTopBar.hkClientLogo.getDrawable() == null && !getShowBackButton() ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = binding$hyperkyc_release.webView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        ConstraintLayout root2 = binding$hyperkyc_release.hkLayoutTopBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "hkLayoutTopBar.root");
        layoutParams2.topMargin = root2.getVisibility() == 8 ? ViewExtsKt.getDp(56) : ViewExtsKt.getDp(32);
        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(binding$hyperkyc_release.btnRetry);
        binding$hyperkyc_release.btnRetry.setIcon(null);
        binding$hyperkyc_release.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewFragment.initViews$lambda$6$lambda$5(WebViewFragment.this, binding$hyperkyc_release, view);
            }
        });
        BaseFragment.loadBackground$hyperkyc_release$default(this, null, 1, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
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
        sb.append("onDestroyView() called");
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
                    Log.println(3, str, "onDestroyView() called ");
                }
            }
        }
        destroyWebView();
        super.onDestroyView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(15:1|(3:132|(1:134)(1:137)|(1:136))|7|(1:9)|10|(1:14)|15|(6:99|100|101|(1:103)|104|(8:106|(7:108|(3:123|(1:125)(1:128)|(1:127))|114|(1:116)|117|(1:121)|122)|18|19|(2:(1:22)|23)|24|25|(18:27|(1:94)(1:31)|33|(1:35)(1:39)|(1:37)(1:38)|40|(1:42)|43|(1:47)|48|(1:50)|51|52|53|54|(1:56)|57|(2:59|(13:61|(1:88)(1:65)|67|(1:69)(1:73)|(1:71)(1:72)|74|(1:76)|77|(1:81)|82|(1:84)|85|86)(1:89))(1:90))(1:95)))|17|18|19|(0)|24|25|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x01d5, code lost:
    
        if (r8 != null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x02c2, code lost:
    
        if (r0 != null) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0191, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0192, code lost:
    
        r6 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void destroyWebView() {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String className2;
        String className3;
        WebView webView;
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
        sb.append("destroyWebView() called");
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
                        Class<?> cls2 = getClass();
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
                    Log.println(3, canonicalName2, "destroyWebView() called ");
                }
                Result.Companion companion4 = Result.INSTANCE;
                WebViewFragment webViewFragment = this;
                webView = this.webView;
                if (webView != null) {
                    if (webView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("webView");
                        webView = null;
                    }
                    ViewExtsKt.forceDestroy(webView);
                }
                Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
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
                    Class<?> cls3 = getClass();
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
                    String str6 = "destroyWebView(): destroying webView failed " + m1205exceptionOrNullimpl.getLocalizedMessage();
                    if (str6 == null) {
                        str6 = "null ";
                    }
                    sb2.append(str6);
                    sb2.append(' ');
                    sb2.append("");
                    companion5.log(level2, sb2.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion7 = Result.INSTANCE;
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
                            Class<?> cls4 = getClass();
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
                            String str7 = "destroyWebView(): destroying webView failed " + m1205exceptionOrNullimpl.getLocalizedMessage();
                            sb3.append(str7 != null ? str7 : "null ");
                            sb3.append(' ');
                            sb3.append("");
                            Log.println(6, str5, sb3.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
        str = "N/A";
        Result.Companion companion42 = Result.INSTANCE;
        WebViewFragment webViewFragment2 = this;
        webView = this.webView;
        if (webView != null) {
        }
        Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
        if (m1205exceptionOrNullimpl == null) {
        }
    }

    public final void showRetry$hyperkyc_release(final boolean show) {
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
        String str2 = "showRetry() called with: show = " + show;
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
                    String str3 = "showRetry() called with: show = " + show;
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
        HkFragmentWebviewBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
        ProgressBar pbLoading = binding$hyperkyc_release.pbLoading;
        Intrinsics.checkNotNullExpressionValue(pbLoading, "pbLoading");
        pbLoading.setVisibility(8);
        if (show) {
            WebView webView = binding$hyperkyc_release.webView;
            Intrinsics.checkNotNullExpressionValue(webView, "webView");
            webView.setVisibility(8);
        }
        MaterialTextView tvRetryTitle = binding$hyperkyc_release.tvRetryTitle;
        Intrinsics.checkNotNullExpressionValue(tvRetryTitle, "tvRetryTitle");
        MaterialButton btnRetry = binding$hyperkyc_release.btnRetry;
        Intrinsics.checkNotNullExpressionValue(btnRetry, "btnRetry");
        CoreExtsKt.withViews(new View[]{tvRetryTitle, btnRetry}, new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.WebViewFragment$showRetry$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View withViews) {
                Intrinsics.checkNotNullParameter(withViews, "$this$withViews");
                withViews.setVisibility(show ? 0 : 8);
            }
        });
    }
}
