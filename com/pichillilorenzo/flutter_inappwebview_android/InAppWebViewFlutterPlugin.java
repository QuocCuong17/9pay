package com.pichillilorenzo.flutter_inappwebview_android;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeSafariBrowserManager;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.NoHistoryCustomTabsActivityCallbacks;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.CredentialDatabaseHandler;
import com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview.HeadlessInAppWebViewManager;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserManager;
import com.pichillilorenzo.flutter_inappwebview_android.print_job.PrintJobManager;
import com.pichillilorenzo.flutter_inappwebview_android.process_global_config.ProcessGlobalConfigManager;
import com.pichillilorenzo.flutter_inappwebview_android.proxy.ProxyManager;
import com.pichillilorenzo.flutter_inappwebview_android.service_worker.ServiceWorkerManager;
import com.pichillilorenzo.flutter_inappwebview_android.tracing.TracingControllerManager;
import com.pichillilorenzo.flutter_inappwebview_android.webview.FlutterWebViewFactory;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewManager;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformViewRegistry;

/* loaded from: classes4.dex */
public class InAppWebViewFlutterPlugin implements FlutterPlugin, ActivityAware {
    protected static final String LOG_TAG = "InAppWebViewFlutterPL";
    public Activity activity;
    public ActivityPluginBinding activityPluginBinding;
    public Context applicationContext;
    public ChromeSafariBrowserManager chromeSafariBrowserManager;
    public CredentialDatabaseHandler credentialDatabaseHandler;
    public FlutterPlugin.FlutterAssets flutterAssets;
    public FlutterView flutterView;
    public FlutterWebViewFactory flutterWebViewFactory;
    public HeadlessInAppWebViewManager headlessInAppWebViewManager;
    public InAppBrowserManager inAppBrowserManager;
    public InAppWebViewManager inAppWebViewManager;
    public BinaryMessenger messenger;
    public MyCookieManager myCookieManager;
    public MyWebStorage myWebStorage;
    public NoHistoryCustomTabsActivityCallbacks noHistoryCustomTabsActivityCallbacks;
    public PlatformUtil platformUtil;
    public PrintJobManager printJobManager;
    public ProcessGlobalConfigManager processGlobalConfigManager;
    public ProxyManager proxyManager;
    public ServiceWorkerManager serviceWorkerManager;
    public TracingControllerManager tracingControllerManager;
    public WebViewFeatureManager webViewFeatureManager;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.flutterAssets = flutterPluginBinding.getFlutterAssets();
        onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger(), this.activity, flutterPluginBinding.getPlatformViewRegistry(), null);
    }

    private void onAttachedToEngine(Context context, BinaryMessenger binaryMessenger, Activity activity, PlatformViewRegistry platformViewRegistry, FlutterView flutterView) {
        this.applicationContext = context;
        this.activity = activity;
        this.messenger = binaryMessenger;
        this.flutterView = flutterView;
        this.inAppBrowserManager = new InAppBrowserManager(this);
        this.headlessInAppWebViewManager = new HeadlessInAppWebViewManager(this);
        this.chromeSafariBrowserManager = new ChromeSafariBrowserManager(this);
        this.noHistoryCustomTabsActivityCallbacks = new NoHistoryCustomTabsActivityCallbacks(this);
        FlutterWebViewFactory flutterWebViewFactory = new FlutterWebViewFactory(this);
        this.flutterWebViewFactory = flutterWebViewFactory;
        platformViewRegistry.registerViewFactory(FlutterWebViewFactory.VIEW_TYPE_ID, flutterWebViewFactory);
        this.platformUtil = new PlatformUtil(this);
        this.inAppWebViewManager = new InAppWebViewManager(this);
        this.myCookieManager = new MyCookieManager(this);
        this.myWebStorage = new MyWebStorage(this);
        if (Build.VERSION.SDK_INT >= 24) {
            this.serviceWorkerManager = new ServiceWorkerManager(this);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.credentialDatabaseHandler = new CredentialDatabaseHandler(this);
        }
        this.webViewFeatureManager = new WebViewFeatureManager(this);
        this.proxyManager = new ProxyManager(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.printJobManager = new PrintJobManager(this);
        }
        this.tracingControllerManager = new TracingControllerManager(this);
        this.processGlobalConfigManager = new ProcessGlobalConfigManager(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        PlatformUtil platformUtil = this.platformUtil;
        if (platformUtil != null) {
            platformUtil.dispose();
            this.platformUtil = null;
        }
        InAppBrowserManager inAppBrowserManager = this.inAppBrowserManager;
        if (inAppBrowserManager != null) {
            inAppBrowserManager.dispose();
            this.inAppBrowserManager = null;
        }
        HeadlessInAppWebViewManager headlessInAppWebViewManager = this.headlessInAppWebViewManager;
        if (headlessInAppWebViewManager != null) {
            headlessInAppWebViewManager.dispose();
            this.headlessInAppWebViewManager = null;
        }
        ChromeSafariBrowserManager chromeSafariBrowserManager = this.chromeSafariBrowserManager;
        if (chromeSafariBrowserManager != null) {
            chromeSafariBrowserManager.dispose();
            this.chromeSafariBrowserManager = null;
        }
        NoHistoryCustomTabsActivityCallbacks noHistoryCustomTabsActivityCallbacks = this.noHistoryCustomTabsActivityCallbacks;
        if (noHistoryCustomTabsActivityCallbacks != null) {
            noHistoryCustomTabsActivityCallbacks.dispose();
            this.noHistoryCustomTabsActivityCallbacks = null;
        }
        MyCookieManager myCookieManager = this.myCookieManager;
        if (myCookieManager != null) {
            myCookieManager.dispose();
            this.myCookieManager = null;
        }
        MyWebStorage myWebStorage = this.myWebStorage;
        if (myWebStorage != null) {
            myWebStorage.dispose();
            this.myWebStorage = null;
        }
        if (this.credentialDatabaseHandler != null && Build.VERSION.SDK_INT >= 26) {
            this.credentialDatabaseHandler.dispose();
            this.credentialDatabaseHandler = null;
        }
        InAppWebViewManager inAppWebViewManager = this.inAppWebViewManager;
        if (inAppWebViewManager != null) {
            inAppWebViewManager.dispose();
            this.inAppWebViewManager = null;
        }
        if (this.serviceWorkerManager != null && Build.VERSION.SDK_INT >= 24) {
            this.serviceWorkerManager.dispose();
            this.serviceWorkerManager = null;
        }
        WebViewFeatureManager webViewFeatureManager = this.webViewFeatureManager;
        if (webViewFeatureManager != null) {
            webViewFeatureManager.dispose();
            this.webViewFeatureManager = null;
        }
        ProxyManager proxyManager = this.proxyManager;
        if (proxyManager != null) {
            proxyManager.dispose();
            this.proxyManager = null;
        }
        if (this.printJobManager != null && Build.VERSION.SDK_INT >= 19) {
            this.printJobManager.dispose();
            this.printJobManager = null;
        }
        TracingControllerManager tracingControllerManager = this.tracingControllerManager;
        if (tracingControllerManager != null) {
            tracingControllerManager.dispose();
            this.tracingControllerManager = null;
        }
        ProcessGlobalConfigManager processGlobalConfigManager = this.processGlobalConfigManager;
        if (processGlobalConfigManager != null) {
            processGlobalConfigManager.dispose();
            this.processGlobalConfigManager = null;
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activityPluginBinding = activityPluginBinding;
        Activity activity = activityPluginBinding.getActivity();
        this.activity = activity;
        if (this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().registerActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        Activity activity = this.activity;
        if (activity != null && this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
        this.activityPluginBinding = null;
        this.activity = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        this.activityPluginBinding = activityPluginBinding;
        Activity activity = activityPluginBinding.getActivity();
        this.activity = activity;
        if (this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().registerActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        Activity activity = this.activity;
        if (activity != null && this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
        this.activityPluginBinding = null;
        this.activity = null;
    }
}
