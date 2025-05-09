package co.hyperverge.hyperkyc.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.LifecycleCoroutineScope;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.models.HSDefaultRemoteConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.JSONConfigStore;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.models.webviewform.WebViewFormTransactionData;
import co.hyperverge.hyperkyc.ui.BaseActivity;
import co.hyperverge.hyperkyc.ui.HKMainActivity;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver$backPressedCallback$2;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;

/* compiled from: FormWebViewDriver.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0000\u0018\u0000 I2\u00020\u0001:\u0003HIJB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ,\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001f2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0001\u0018\u00010\"H\u0002J*\u0010#\u001a\u0004\u0018\u00010 2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020 H\u0002J\r\u0010)\u001a\u00020*H\u0000¢\u0006\u0002\b+J<\u0010,\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\"2&\u0010!\u001a\"\u0012\u0004\u0012\u00020 \u0012\u0006\u0012\u0004\u0018\u00010\u00010-j\u0010\u0012\u0004\u0012\u00020 \u0012\u0006\u0012\u0004\u0018\u00010\u0001`.H\u0002J\b\u0010/\u001a\u00020 H\u0002J\"\u00100\u001a\u001c\u0012\u0004\u0012\u00020 \u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0001\u0018\u00010\"0\u001fH\u0002J$\u00101\u001a\u00020*2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u001f2\u0006\u0010$\u001a\u00020%H\u0002J\u001b\u00103\u001a\u00020*2\f\u00104\u001a\b\u0012\u0004\u0012\u00020*05H\u0000¢\u0006\u0002\b6J\b\u00107\u001a\u00020 H\u0002J\u0013\u00108\u001a\u00020\u0017H\u0081@ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u0010\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020\u0017H\u0002J\b\u0010=\u001a\u00020 H\u0002J#\u0010>\u001a\u00020*2\u0006\u0010<\u001a\u00020\u00172\f\u0010?\u001a\b\u0012\u0004\u0012\u00020*05H\u0000¢\u0006\u0002\b@J \u0010A\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020\u0017H\u0002J*\u0010D\u001a\u00020*2\u0006\u0010B\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00172\u000e\b\u0002\u0010E\u001a\b\u0012\u0004\u0012\u00020*05H\u0002J\u0010\u0010F\u001a\u00020*2\u0006\u0010G\u001a\u00020 H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, d2 = {"Lco/hyperverge/hyperkyc/utils/FormWebViewDriver;", "", "activity", "Lco/hyperverge/hyperkyc/ui/HKMainActivity;", "lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "(Lco/hyperverge/hyperkyc/ui/HKMainActivity;Landroidx/lifecycle/LifecycleCoroutineScope;Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;)V", "getActivity", "()Lco/hyperverge/hyperkyc/ui/HKMainActivity;", "backPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "getBackPressedCallback$hyperkyc_release", "()Landroidx/activity/OnBackPressedCallback;", "backPressedCallback$delegate", "Lkotlin/Lazy;", "getLifecycleScope", "()Landroidx/lifecycle/LifecycleCoroutineScope;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "pageLoadContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "webView", "Landroid/webkit/WebView;", "getWebView$hyperkyc_release", "()Landroid/webkit/WebView;", "setWebView$hyperkyc_release", "(Landroid/webkit/WebView;)V", "convertVariablesFromJSONArrayToJsonArray", "", "", "variables", "", "createFile", "filesDir", "Ljava/io/File;", "filename", "fileType", "base64", "destroyWebView", "", "destroyWebView$hyperkyc_release", "getDeFlattenVariables", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getModuleDataJS", "getWebFormModuleDataMap", "handleBase64FormFileUploadValue", "data", "initWebSDK", "onWebSDKFormInit", "Lkotlin/Function0;", "initWebSDK$hyperkyc_release", "initWebSDKFormJS", "initWebView", "initWebView$hyperkyc_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchFormModuleJS", "showBackButton", "loadHtmlFromAssets", "setAndLaunchWebSDKForm", "onLoaded", "setAndLaunchWebSDKForm$hyperkyc_release", "setModuleDataJS", "moduleId", "isChild", "setWebFormData", "onDataSet", "updateFormData", "dataJSON", "BrowserWebClient", "Companion", "JSInterface", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormWebViewDriver {
    public static final String ASSET_URL = "file:///android_asset/web_form";
    public static final String WEB_FORM_VERSION = "8.8.1";
    private final HKMainActivity activity;

    /* renamed from: backPressedCallback$delegate, reason: from kotlin metadata */
    private final Lazy backPressedCallback;
    private final LifecycleCoroutineScope lifecycleScope;
    private final MainVM mainVM;
    private CancellableContinuation<? super Boolean> pageLoadContinuation;
    public WebView webView;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JSONConfigStore jsonConfigStore = new JSONConfigStore(null, null, null, 7, null);

    public FormWebViewDriver(HKMainActivity activity, LifecycleCoroutineScope lifecycleScope, MainVM mainVM) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
        Intrinsics.checkNotNullParameter(mainVM, "mainVM");
        this.activity = activity;
        this.lifecycleScope = lifecycleScope;
        this.mainVM = mainVM;
        this.backPressedCallback = LazyKt.lazy(new Function0<FormWebViewDriver$backPressedCallback$2.AnonymousClass1>() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$backPressedCallback$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [co.hyperverge.hyperkyc.utils.FormWebViewDriver$backPressedCallback$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                final FormWebViewDriver formWebViewDriver = FormWebViewDriver.this;
                return new OnBackPressedCallback() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$backPressedCallback$2.1
                    {
                        super(false);
                    }

                    @Override // androidx.activity.OnBackPressedCallback
                    public void handleOnBackPressed() {
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
                        sb.append("handleOnBackPressed() called");
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
                                    Log.println(3, str, "handleOnBackPressed() called ");
                                }
                            }
                        }
                        new FormWebViewDriver.JSInterface().onSDKBackPress();
                    }
                };
            }
        });
    }

    public final HKMainActivity getActivity() {
        return this.activity;
    }

    public final LifecycleCoroutineScope getLifecycleScope() {
        return this.lifecycleScope;
    }

    public final MainVM getMainVM() {
        return this.mainVM;
    }

    public final WebView getWebView$hyperkyc_release() {
        WebView webView = this.webView;
        if (webView != null) {
            return webView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("webView");
        return null;
    }

    public final void setWebView$hyperkyc_release(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.webView = webView;
    }

    public final OnBackPressedCallback getBackPressedCallback$hyperkyc_release() {
        return (OnBackPressedCallback) this.backPressedCallback.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initWebSDK$lambda$6(FormWebViewDriver this$0, Function0 onWebSDKFormInit, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onWebSDKFormInit, "$onWebSDKFormInit");
        if (this$0.mainVM.getFlowFinished()) {
            return;
        }
        onWebSDKFormInit.invoke();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void setWebFormData$default(FormWebViewDriver formWebViewDriver, String str, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$setWebFormData$1
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
        formWebViewDriver.setWebFormData(str, z, function0);
    }

    /* compiled from: FormWebViewDriver.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0011"}, d2 = {"Lco/hyperverge/hyperkyc/utils/FormWebViewDriver$BrowserWebClient;", "Landroid/webkit/WebViewClient;", "(Lco/hyperverge/hyperkyc/utils/FormWebViewDriver;)V", "onPageFinished", "", "webView", "Landroid/webkit/WebView;", "url", "", "onReceivedError", ViewHierarchyConstants.VIEW_KEY, "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "shouldOverrideUrlLoading", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private final class BrowserWebClient extends WebViewClient {
        public BrowserWebClient() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0146, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0156, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x015a, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0169, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x016b, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0178, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x017e, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0181, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0188, code lost:
        
            r0 = new java.lang.StringBuilder();
            r1 = "onReceivedError() called with: view = " + r18 + ", request = " + r19 + ", error = " + r20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x01a8, code lost:
        
            if (r1 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x01aa, code lost:
        
            r1 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x01ac, code lost:
        
            r0.append(r1);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x01bf, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0159, code lost:
        
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
            if (!CoreExtsKt.isDebug()) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                return;
            }
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

        /* JADX WARN: Can't wrap try/catch for region: R(20:1|(3:151|(1:153)(1:156)|(1:155))|7|(1:9)|10|(1:14)|15|(1:17)|18|(1:20)(9:108|109|110|111|112|113|(1:115)|116|(10:118|(9:120|(3:138|(1:140)(1:143)|(1:142))|126|(1:128)|129|(1:133)|134|(1:136)|137)|22|23|24|(1:105)(1:28)|(1:32)|33|34|(15:36|(3:97|(1:99)(1:102)|(1:101))|42|(1:44)|45|(1:49)|50|(1:52)|53|54|55|56|(1:58)|59|(2:61|(13:63|(1:91)(2:67|(9:69|70|(1:72)|73|(1:77)|78|(1:80)|81|82))|84|(1:86)(1:90)|(1:88)(1:89)|70|(0)|73|(2:75|77)|78|(0)|81|82)(1:92))(1:93))(1:103))(1:144))|21|22|23|24|(1:26)|105|(2:30|32)|33|34|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:106:0x020d, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:107:0x020e, code lost:
        
            r2 = kotlin.Result.INSTANCE;
            r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
         */
        /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x0115  */
        /* JADX WARN: Removed duplicated region for block: B:118:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:144:0x01cb  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x021e  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0360  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x0396  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onPageFinished(WebView webView, String url) {
            String canonicalName;
            String str;
            String str2;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str3;
            String canonicalName2;
            String className;
            FormWebViewDriver formWebViewDriver;
            Throwable m1205exceptionOrNullimpl;
            String canonicalName3;
            Object m1202constructorimpl2;
            String str4;
            String str5;
            Matcher matcher;
            String str6;
            String className2;
            String className3;
            CancellableContinuation cancellableContinuation;
            boolean z;
            CancellableContinuation cancellableContinuation2;
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
            String str7 = "onPageFinished() called with: webView = " + webView + ", url = " + url;
            if (str7 == null) {
                str7 = "null ";
            }
            sb.append(str7);
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
                } catch (Throwable th) {
                    th = th;
                    str = " - ";
                    str2 = "N/A";
                }
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (!CoreExtsKt.isDebug()) {
                    }
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
                        String str8 = "onPageFinished() called with: webView = " + webView + ", url = " + url;
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb2.append(str8);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    formWebViewDriver = FormWebViewDriver.this;
                    Result.Companion companion4 = Result.INSTANCE;
                    BrowserWebClient browserWebClient = this;
                    cancellableContinuation = formWebViewDriver.pageLoadContinuation;
                    z = true;
                    if (cancellableContinuation != null || !cancellableContinuation.isActive()) {
                        z = false;
                    }
                    if (z && (cancellableContinuation2 = formWebViewDriver.pageLoadContinuation) != null) {
                        Result.Companion companion5 = Result.INSTANCE;
                        cancellableContinuation2.resumeWith(Result.m1202constructorimpl(Boolean.valueOf(ContextExtsKt.isOnline(formWebViewDriver.getActivity()))));
                    }
                    Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl == null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName3 == null) {
                                canonicalName3 = str2;
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
                        String str9 = "onPageFinished(): " + m1205exceptionOrNullimpl;
                        if (str9 == null) {
                            str9 = "null ";
                        }
                        sb3.append(str9);
                        sb3.append(' ');
                        sb3.append("");
                        companion6.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String str10 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str10, str3);
                            if (StringsKt.contains$default((CharSequence) str10, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                    str4 = null;
                                } else {
                                    str4 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        str5 = substringAfterLast$default;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                        if (matcher.find()) {
                                            str5 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                        }
                                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str5 = str5.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        str6 = "onPageFinished(): " + m1205exceptionOrNullimpl;
                                        if (str6 == null) {
                                            str6 = "null ";
                                        }
                                        sb4.append(str6);
                                        sb4.append(' ');
                                        sb4.append("");
                                        Log.println(6, str5, sb4.toString());
                                        return;
                                    }
                                }
                                Class<?> cls4 = getClass();
                                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                                str5 = canonicalName4 == null ? str2 : canonicalName4;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher.find()) {
                                }
                                if (str5.length() > 23) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb42 = new StringBuilder();
                                str6 = "onPageFinished(): " + m1205exceptionOrNullimpl;
                                if (str6 == null) {
                                }
                                sb42.append(str6);
                                sb42.append(' ');
                                sb42.append("");
                                Log.println(6, str5, sb42.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                charSequence = "co.hyperverge";
            }
            str3 = "packageName";
            formWebViewDriver = FormWebViewDriver.this;
            Result.Companion companion42 = Result.INSTANCE;
            BrowserWebClient browserWebClient2 = this;
            cancellableContinuation = formWebViewDriver.pageLoadContinuation;
            z = true;
            if (cancellableContinuation != null) {
            }
            z = false;
            if (z) {
                Result.Companion companion52 = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m1202constructorimpl(Boolean.valueOf(ContextExtsKt.isOnline(formWebViewDriver.getActivity()))));
            }
            Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
            if (m1205exceptionOrNullimpl == null) {
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            Uri url;
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
            String str2 = "shouldOverrideUrlLoading() called with: view = " + view + ", request = " + request;
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
                        String str3 = "shouldOverrideUrlLoading() called with: view = " + view + ", request = " + request;
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
            if (view == null || request == null || (url = request.getUrl()) == null) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW", url);
            Context context = view.getContext();
            if (context == null) {
                return true;
            }
            context.startActivity(intent);
            return true;
        }
    }

    /* compiled from: FormWebViewDriver.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0007H\u0007¨\u0006\u000e"}, d2 = {"Lco/hyperverge/hyperkyc/utils/FormWebViewDriver$JSInterface;", "", "(Lco/hyperverge/hyperkyc/utils/FormWebViewDriver;)V", "onSDKBackPress", "", "onSDKError", "errorMessage", "", "onSDKNextStep", "isChild", "", AnalyticsLogger.Keys.NEXTSTEP, "onSDKSendAnalyticsData", "message", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class JSInterface {
        public JSInterface() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
        
            if (r0 == null) goto L52;
         */
        @JavascriptInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void onSDKBackPress() {
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
            sb.append("onSDKBackPress() called");
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
                        Log.println(3, str, "onSDKBackPress() called ");
                    }
                }
            }
            FormWebViewDriver.this.getBackPressedCallback$hyperkyc_release().setEnabled(false);
            BuildersKt__Builders_commonKt.launch$default(FormWebViewDriver.this.getLifecycleScope(), Dispatchers.getMain(), null, new FormWebViewDriver$JSInterface$onSDKBackPress$2(FormWebViewDriver.this, null), 2, null);
        }

        @JavascriptInterface
        public final void onSDKNextStep(boolean isChild, String nextStep) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(nextStep, "nextStep");
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
            String str2 = "onSDKNextStep() called with: nextStep = " + nextStep + ", isChild = " + isChild;
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
                        String str3 = "onSDKNextStep() called with: nextStep = " + nextStep + ", isChild = " + isChild;
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
            BuildersKt__Builders_commonKt.launch$default(FormWebViewDriver.this.getLifecycleScope(), Dispatchers.getMain(), null, new FormWebViewDriver$JSInterface$onSDKNextStep$2(FormWebViewDriver.this, isChild, nextStep, null), 2, null);
        }

        @JavascriptInterface
        public final void onSDKError(String errorMessage) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
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
            String str2 = "onSDKError() called with: errorMessage = " + errorMessage;
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
                        String str3 = "onSDKError() called with: errorMessage = " + errorMessage;
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
            BaseActivity.finishWithResult$default(FormWebViewDriver.this.getActivity(), "error", null, 123, errorMessage, false, false, 50, null);
        }

        @JavascriptInterface
        public final void onSDKSendAnalyticsData(String message) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(message, "message");
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
            String str2 = "onSDKSendAnalyticsData() called with: message = " + message;
            if (str2 == null) {
                str2 = "null ";
            }
            sb.append(str2);
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "onSDKSendAnalyticsData() called with: message = " + message;
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
    }

    /* compiled from: FormWebViewDriver.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lco/hyperverge/hyperkyc/utils/FormWebViewDriver$Companion;", "", "()V", "ASSET_URL", "", "WEB_FORM_VERSION", "jsonConfigStore", "Lco/hyperverge/hyperkyc/data/models/JSONConfigStore;", "getJsonConfigStore$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/JSONConfigStore;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JSONConfigStore getJsonConfigStore$hyperkyc_release() {
            return FormWebViewDriver.jsonConfigStore;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x012a, code lost:
    
        if (r0 != null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x013a, code lost:
    
        if (r0 == null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x013e, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x014d, code lost:
    
        if (r0.find() == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x014f, code lost:
    
        r9 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x015a, code lost:
    
        if (r9.length() <= 23) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x015e, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0161, code lost:
    
        r9 = r9.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0168, code lost:
    
        android.util.Log.println(3, r9, "initWebView() called ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x013d, code lost:
    
        r9 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initWebView$hyperkyc_release(Continuation<? super Boolean> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String str3;
        HSDefaultRemoteConfig.MobileSdkConfig.WebSDKVersion webSDKVersion;
        Map<String, String> androidVersionMap;
        String className2;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str4 = "N/A";
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
        sb.append("initWebView() called");
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
        setWebView$hyperkyc_release(new WebView(getActivity()));
        this.pageLoadContinuation = cancellableContinuationImpl2;
        WebView webView$hyperkyc_release = getWebView$hyperkyc_release();
        WebSettings settings = webView$hyperkyc_release.getSettings();
        webView$hyperkyc_release.setWebViewClient(new BrowserWebClient());
        webView$hyperkyc_release.addJavascriptInterface(new JSInterface(), "JSInterface");
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(-1);
        settings.setUserAgentString("Mozilla/5.0 (Linux; Android 11; Mi 11T Pro 5G) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Mobile Safari/537.36");
        String loadHtmlFromAssets = loadHtmlFromAssets();
        HSDefaultRemoteConfig.MobileSdkConfig mobileSdkConfig = getMainVM().getDefaultRemoteConfig$hyperkyc_release().getMobileSdkConfig();
        if (mobileSdkConfig == null || (webSDKVersion = mobileSdkConfig.getWebSDKVersion()) == null || (androidVersionMap = webSDKVersion.getAndroidVersionMap()) == null || (str3 = androidVersionMap.get(BuildConfig.HYPERKYC_VERSION_NAME)) == null) {
            str3 = WEB_FORM_VERSION;
        }
        getWebView$hyperkyc_release().loadDataWithBaseURL(ASSET_URL, StringsKt.replace$default(loadHtmlFromAssets, "hyperverge-web-sdk@8.7.1", "hyperverge-web-sdk@" + str3, false, 4, (Object) null), "text/html", "UTF-8", null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final void destroyWebView$hyperkyc_release() {
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
                    Log.println(3, str, "destroyWebView() called ");
                }
            }
        }
        getWebView$hyperkyc_release().destroy();
    }

    public final void initWebSDK$hyperkyc_release(final Function0<Unit> onWebSDKFormInit) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(onWebSDKFormInit, "onWebSDKFormInit");
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
        sb.append("initWebSDKForm() called");
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
                    Log.println(3, str, "initWebSDKForm() called ");
                }
            }
        }
        getWebView$hyperkyc_release().evaluateJavascript(initWebSDKFormJS(), new ValueCallback() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                FormWebViewDriver.initWebSDK$lambda$6(FormWebViewDriver.this, onWebSDKFormInit, (String) obj);
            }
        });
    }

    public final void setAndLaunchWebSDKForm$hyperkyc_release(boolean showBackButton, Function0<Unit> onLoaded) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(onLoaded, "onLoaded");
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
        sb.append("setAndLaunchWebSDKForm() called");
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
                    Log.println(3, str, "setAndLaunchWebSDKForm() called ");
                }
            }
        }
        setWebFormData$default(this, this.mainVM.getCurrentModuleId$hyperkyc_release(), false, new FormWebViewDriver$setAndLaunchWebSDKForm$2(this, showBackButton, onLoaded), 2, null);
    }

    private final void setWebFormData(String moduleId, boolean isChild, Function0<Unit> onDataSet) {
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
        String str2 = "setWebFormData() called with: moduleId = " + moduleId + ", isChild = " + isChild;
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
                    String str3 = "setWebFormData() called with: moduleId = " + moduleId + ", isChild = " + isChild;
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
        BuildersKt__Builders_commonKt.launch$default(this.lifecycleScope, Dispatchers.getMain(), null, new FormWebViewDriver$setWebFormData$3(this, moduleId, isChild, onDataSet, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateFormData(String dataJSON) {
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
        String str2 = "updateFormData() called with: dataJSON = " + dataJSON;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "updateFormData() called with: dataJSON = " + dataJSON;
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
        Object fromJson = this.mainVM.getGson().fromJson(dataJSON, new TypeToken<Map<String, ? extends Object>>() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$updateFormData$mapType$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "mainVM.gson.fromJson<Map… Any>>(dataJSON, mapType)");
        Map mutableMap = MapsKt.toMutableMap((Map) fromJson);
        Iterator it = mutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Object value = ((Map.Entry) it.next()).getValue();
            Map<String, Object> map = TypeIntrinsics.isMutableMap(value) ? (Map) value : null;
            if (map != null) {
                File filesDir = this.activity.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "activity.filesDir");
                handleBase64FormFileUploadValue(map, filesDir);
            }
        }
        MainVM mainVM = this.mainVM;
        MainVM.updateFormData$hyperkyc_release$default(mainVM, mainVM.getCurrentModuleId$hyperkyc_release(), JSONExtsKt.flattenMap(mutableMap), false, 4, null);
    }

    private final String initWebSDKFormJS() {
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
        sb.append("initWebSDKFormJS() called");
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
                    Log.println(3, str, "initWebSDKFormJS() called ");
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n        const webSDKFormModule = new HVWebSDKNativeMode();\n        webSDKFormModule.init(\n            {\n                workflow: ");
        JSONConfigStore jSONConfigStore = jsonConfigStore;
        sb2.append(jSONConfigStore.getWorkflowConfig$hyperkyc_release());
        sb2.append(",\n                textConfig: ");
        sb2.append(jSONConfigStore.getTextConfig$hyperkyc_release());
        sb2.append(",\n                uiConfig: ");
        sb2.append(jSONConfigStore.getUiConfig$hyperkyc_release());
        sb2.append(",\n                styleSheetUrl: '");
        sb2.append("https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,200..1000;1,200..1000&display=swap");
        sb2.append("',\n                fontName: '");
        sb2.append("Mulish");
        sb2.append("',\n                languageUsed: '");
        MainVM mainVM = this.mainVM;
        sb2.append(mainVM.getLanguageToBeUsed$hyperkyc_release(mainVM.getHyperKycConfig$hyperkyc_release()));
        sb2.append("',\n                backPressedCallback: () => {\n                    JSInterface.onSDKBackPress();\n                },\n                nextStepCallback: (isChild, nextStep) => {\n                    JSInterface.onSDKNextStep(isChild, nextStep);\n                },\n                errorCallback: (errorMessage) => {\n                     JSInterface.onSDKError(errorMessage)\n                },\n                analyticsCallback: (analyticsMessage) => {\n                     JSInterface.onSDKSendAnalyticsData(analyticsMessage)\n                },\n            }\n        );\n        ");
        return StringsKt.trimIndent(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String launchFormModuleJS(boolean showBackButton) {
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
        String str2 = "launchFormModuleJS() called with: showBackButton = " + showBackButton;
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
                    String str3 = "launchFormModuleJS() called with: showBackButton = " + showBackButton;
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
        return StringsKt.trimIndent("\n            webSDKFormModule.launch(" + showBackButton + ");\n        ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0149, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String setModuleDataJS(MainVM mainVM, String moduleId, boolean isChild) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String json;
        String className2;
        String str5 = moduleId;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
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
        String str6 = "setModuleDataJS() called with: mainVM = " + mainVM + ", moduleId = " + str5 + ", isChild = " + isChild;
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
                    if (str2 == null) {
                        str3 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                            str3 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str4 = "setModuleDataJS() called with: mainVM = " + mainVM + ", moduleId = " + str5 + ", isChild = " + isChild;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                    }
                    str3 = str2;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str4 = "setModuleDataJS() called with: mainVM = " + mainVM + ", moduleId = " + str5 + ", isChild = " + isChild;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        Map<String, Map<String, Object>> webFormModuleDataMap = getWebFormModuleDataMap();
        HashMap inputs = mainVM.getHyperKycConfig$hyperkyc_release().getInputs();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : inputs.entrySet()) {
            if (!CollectionsKt.listOf((Object[]) new String[]{"appId", HyperKycConfig.APP_KEY}).contains((String) entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        if (isChild) {
            Map<String, Object> map = webFormModuleDataMap.get(str5);
            if (map != null) {
                linkedHashMap3.put(str5, convertVariablesFromJSONArrayToJsonArray(map));
            }
        } else {
            for (Map.Entry<String, Map<String, Object>> entry2 : webFormModuleDataMap.entrySet()) {
                linkedHashMap3.put(entry2.getKey(), convertVariablesFromJSONArrayToJsonArray(entry2.getValue()));
            }
        }
        WebViewFormTransactionData webViewFormTransactionData = new WebViewFormTransactionData(linkedHashMap2, MapsKt.toMap(linkedHashMap3));
        StringBuilder sb3 = new StringBuilder();
        sb3.append("\n            webSDKFormModule.setModuleData(\n                {\n                    data: ");
        json = new Gson().toJson(webViewFormTransactionData);
        sb3.append(json);
        sb3.append(",\n                    moduleId: '");
        if (isChild) {
            str5 = "";
        }
        sb3.append(str5);
        sb3.append("',\n                    isChild: ");
        sb3.append(isChild);
        sb3.append(",\n                }\n            );\n        ");
        return StringsKt.trimIndent(sb3.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getModuleDataJS() {
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
        sb.append("getModuleDataJS() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return "webSDKFormModule.getModuleData();";
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
        if (!CoreExtsKt.isDebug()) {
            return "webSDKFormModule.getModuleData();";
        }
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
            return "webSDKFormModule.getModuleData();";
        }
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
        Log.println(3, str, "getModuleDataJS() called ");
        return "webSDKFormModule.getModuleData();";
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0139, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Map<String, Object> convertVariablesFromJSONArrayToJsonArray(Map<String, ? extends Object> variables) {
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
        String str2 = "convertVariablesFromJSONArrayToJsonArray() called with: variables = " + variables;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "convertVariablesFromJSONArrayToJsonArray() called with: variables = " + variables;
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
        if (variables == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ? extends Object> entry : variables.entrySet()) {
            if (entry.getValue() instanceof JSONArray) {
                String key = entry.getKey();
                JsonArray asJsonArray = JsonParser.parseString(entry.getValue().toString()).getAsJsonArray();
                Intrinsics.checkNotNullExpressionValue(asJsonArray, "parseString(it.value.toString()).asJsonArray");
                linkedHashMap.put(key, asJsonArray);
            } else {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    private final void handleBase64FormFileUploadValue(Map<String, Object> data, File filesDir) {
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
        String str2 = "handleBase64FormFileUploadValue() called with: data = " + data;
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
                    String str3 = "handleBase64FormFileUploadValue() called with: data = " + data;
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
        if (data.containsKey("value") && data.containsKey("crossPlatformFiles") && (data.get("crossPlatformFiles") instanceof ArrayList)) {
            Object obj = data.get("crossPlatformFiles");
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.collections.Map<kotlin.String, kotlin.String>>");
            ArrayList arrayList = new ArrayList();
            for (Map map : (List) obj) {
                Object obj2 = map.get("name");
                Intrinsics.checkNotNull(obj2);
                Object obj3 = map.get("type");
                Intrinsics.checkNotNull(obj3);
                Object obj4 = map.get("fileBase64");
                Intrinsics.checkNotNull(obj4);
                String createFile = createFile(filesDir, (String) obj2, (String) obj3, (String) obj4);
                if (createFile != null) {
                    arrayList.add(createFile);
                }
            }
            data.put("value", CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null));
            data.remove("crossPlatformFiles");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(27:1|(3:148|(1:150)(1:153)|(1:152))|7|(1:9)|10|(1:14)|15|(1:17)|18|(1:20)(6:110|111|112|(1:114)|115|(17:117|(9:119|(3:137|(1:139)(1:142)|(1:141))|125|(1:127)|128|(1:132)|133|(1:135)|136)(1:143)|22|(1:24)|25|26|27|28|29|30|31|(15:33|(3:96|(1:98)(1:102)|(1:100)(1:101))|39|(1:41)|42|(1:46)|47|(1:49)|50|51|52|53|(1:55)|56|(2:58|(17:60|(1:92)(1:64)|66|(1:68)(1:90)|(12:70|71|(1:73)|74|(1:78)|79|(1:81)|82|83|(1:85)(1:89)|86|87)|91|71|(0)|74|(2:76|78)|79|(0)|82|83|(0)(0)|86|87)))|103|83|(0)(0)|86|87)(1:144))|21|22|(0)|25|26|27|28|29|30|31|(0)|103|83|(0)(0)|86|87|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0230, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0235, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0232, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0361, code lost:
    
        if (r0 != null) goto L137;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String createFile(File filesDir, String filename, String fileType, String base64) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        CharSequence charSequence;
        String str2;
        String canonicalName2;
        String className;
        File file;
        Throwable m1205exceptionOrNullimpl;
        String str3;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String str6;
        Matcher matcher;
        String str7;
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
        String str8 = "createFiles() called with: filename = " + filename + ", fileType = " + fileType + ", base64 = " + base64;
        if (str8 == null) {
            str8 = "null ";
        }
        sb.append(str8);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            str = base64;
        } else {
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
                str2 = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("createFiles() called with: filename = ");
                    sb3.append(filename);
                    sb3.append(", fileType = ");
                    sb3.append(fileType);
                    sb3.append(", base64 = ");
                    str = base64;
                    sb3.append(str);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                } else {
                    str = base64;
                }
                file = new File(filesDir, "hv/formFiles/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, filename);
                file2.createNewFile();
                String absolutePath = file2.getAbsolutePath();
                Result.Companion companion4 = Result.INSTANCE;
                FormWebViewDriver formWebViewDriver = this;
                BuildersKt__Builders_commonKt.launch$default(this.lifecycleScope, Dispatchers.getIO(), null, new FormWebViewDriver$createFile$2$1(absolutePath, Base64.decode(StringsKt.substringAfter$default(str, "base64,", (String) null, 2, (Object) null), 0), null), 2, null);
                Object m1202constructorimpl3 = Result.m1202constructorimpl(absolutePath);
                Object obj = m1202constructorimpl3;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        str4 = canonicalName3 == null ? "N/A" : canonicalName3;
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
                    sb5.append(str4);
                    sb5.append(" - ");
                    String str9 = "createFiles(): " + m1205exceptionOrNullimpl;
                    if (str9 == null) {
                        str9 = "null ";
                    }
                    sb5.append(str9);
                    sb5.append(' ');
                    sb5.append("");
                    companion5.log(level2, sb5.toString());
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
                    String str10 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str10, str2);
                        if (StringsKt.contains$default((CharSequence) str10, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str3 = null;
                            } else {
                                str3 = null;
                                str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls4 = getClass();
                            str5 = cls4 != null ? cls4.getCanonicalName() : str3;
                            if (str5 == null) {
                                str6 = "N/A";
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                if (matcher.find()) {
                                    str6 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                }
                                if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str6 = str6.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb6 = new StringBuilder();
                                str7 = "createFiles(): " + m1205exceptionOrNullimpl;
                                if (str7 == null) {
                                    str7 = "null ";
                                }
                                sb6.append(str7);
                                sb6.append(' ');
                                sb6.append("");
                                Log.println(6, str6, sb6.toString());
                                return (String) (Result.m1208isFailureimpl(obj) ? str3 : obj);
                            }
                            str6 = str5;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                            if (matcher.find()) {
                            }
                            if (str6.length() > 23) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb62 = new StringBuilder();
                            str7 = "createFiles(): " + m1205exceptionOrNullimpl;
                            if (str7 == null) {
                            }
                            sb62.append(str7);
                            sb62.append(' ');
                            sb62.append("");
                            Log.println(6, str6, sb62.toString());
                            return (String) (Result.m1208isFailureimpl(obj) ? str3 : obj);
                        }
                    }
                }
                str3 = null;
                return (String) (Result.m1208isFailureimpl(obj) ? str3 : obj);
            }
            str = base64;
        }
        charSequence = "co.hyperverge";
        str2 = "packageName";
        file = new File(filesDir, "hv/formFiles/");
        if (!file.exists()) {
        }
        File file22 = new File(file, filename);
        file22.createNewFile();
        String absolutePath2 = file22.getAbsolutePath();
        Result.Companion companion42 = Result.INSTANCE;
        FormWebViewDriver formWebViewDriver2 = this;
        BuildersKt__Builders_commonKt.launch$default(this.lifecycleScope, Dispatchers.getIO(), null, new FormWebViewDriver$createFile$2$1(absolutePath2, Base64.decode(StringsKt.substringAfter$default(str, "base64,", (String) null, 2, (Object) null), 0), null), 2, null);
        Object m1202constructorimpl32 = Result.m1202constructorimpl(absolutePath2);
        Object obj2 = m1202constructorimpl32;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
        if (m1205exceptionOrNullimpl != null) {
        }
        str3 = null;
        return (String) (Result.m1208isFailureimpl(obj2) ? str3 : obj2);
    }

    private final Map<String, Map<String, Object>> getWebFormModuleDataMap() {
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
        sb.append("getModuleDataMap() called");
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
                    Log.println(3, str, "getModuleDataMap() called ");
                }
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (HyperKycData.APIResult aPIResult : this.mainVM.getHyperKycData().getApiResultList()) {
            linkedHashMap.put(aPIResult.getTag$hyperkyc_release(), getDeFlattenVariables(aPIResult.getVariables()));
        }
        for (HyperKycData.BarcodeResult barcodeResult : this.mainVM.getHyperKycData().getBarcodeResultList()) {
            linkedHashMap.put(barcodeResult.getTag$hyperkyc_release(), getDeFlattenVariables(barcodeResult.getVariables()));
        }
        HyperKycData.CountryResult countryResult$hyperkyc_release = this.mainVM.getHyperKycData().getCountryResult$hyperkyc_release();
        if (countryResult$hyperkyc_release != null) {
            linkedHashMap.put(countryResult$hyperkyc_release.getTag$hyperkyc_release(), getDeFlattenVariables(countryResult$hyperkyc_release.getVariables()));
        }
        for (HyperKycData.DocResult docResult : this.mainVM.getHyperKycData().getDocResultList()) {
            linkedHashMap.put(docResult.getTag$hyperkyc_release(), getDeFlattenVariables(docResult.getVariables()));
        }
        for (HyperKycData.FormResult formResult : this.mainVM.getHyperKycData().getFormResultList()) {
            linkedHashMap.put(formResult.getTag$hyperkyc_release(), getDeFlattenVariables(formResult.getVariables()));
        }
        HyperKycData.FaceResult faceResult$hyperkyc_release = this.mainVM.getHyperKycData().getFaceResult$hyperkyc_release();
        if (faceResult$hyperkyc_release != null) {
            linkedHashMap.put(faceResult$hyperkyc_release.getTag$hyperkyc_release(), getDeFlattenVariables(faceResult$hyperkyc_release.getVariables()));
        }
        for (HyperKycData.NFCResult nFCResult : this.mainVM.getHyperKycData().getNfcResultList()) {
            linkedHashMap.put(nFCResult.getTag$hyperkyc_release(), getDeFlattenVariables(nFCResult.getVariables()));
        }
        for (HyperKycData.SessionResult sessionResult : this.mainVM.getHyperKycData().getSessionResultList()) {
            linkedHashMap.put(sessionResult.getTag$hyperkyc_release(), getDeFlattenVariables(sessionResult.getVariables()));
        }
        for (HyperKycData.VideoStatementResult videoStatementResult : this.mainVM.getHyperKycData().getVideoStatementResultList()) {
            linkedHashMap.put(videoStatementResult.getTag$hyperkyc_release(), getDeFlattenVariables(videoStatementResult.getVariables()));
        }
        for (HyperKycData.VideoStatementV2Result videoStatementV2Result : this.mainVM.getHyperKycData().getVideoStatementV2ResultList()) {
            linkedHashMap.put(videoStatementV2Result.getTag$hyperkyc_release(), getDeFlattenVariables(videoStatementV2Result.getVariables()));
        }
        for (HyperKycData.WebviewResult webviewResult : this.mainVM.getHyperKycData().getWebviewResultList()) {
            linkedHashMap.put(webviewResult.getTag$hyperkyc_release(), getDeFlattenVariables(webviewResult.getVariables()));
        }
        return linkedHashMap;
    }

    private final Map<String, Object> getDeFlattenVariables(HashMap<String, Object> variables) {
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
        String str2 = "getDeFlattenVariables() called with: variables = " + variables;
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
                    String str3 = "getDeFlattenVariables() called with: variables = " + variables;
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
        Map nestedMap = JSONExtsKt.toNestedMap(variables);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : nestedMap.entrySet()) {
            if (entry.getValue() != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap2.size()));
        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
            Object key = entry2.getKey();
            Object value = entry2.getValue();
            Intrinsics.checkNotNull(value);
            linkedHashMap3.put(key, value);
        }
        return linkedHashMap3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0122, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String loadHtmlFromAssets() {
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
        sb.append("loadHtmlFromAssets() called");
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
                    Log.println(3, str, "loadHtmlFromAssets() called ");
                }
            }
        }
        InputStream open = this.activity.getAssets().open("web_form/native.html");
        Intrinsics.checkNotNullExpressionValue(open, "assetManager.open(\"web_form/native.html\")");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
        StringBuilder sb2 = new StringBuilder();
        BufferedReader bufferedReader2 = bufferedReader;
        try {
            Iterator<String> it = TextStreamsKt.lineSequence(bufferedReader2).iterator();
            while (it.hasNext()) {
                sb2.append(it.next());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader2, null);
            String sb3 = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "stringBuilder.toString()");
            return sb3;
        } finally {
        }
    }
}
