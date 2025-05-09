package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.sentry.protocol.SdkVersion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
public class ChromeSafariBrowserManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "ChromeBrowserManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_chromesafaribrowser";
    public static final Map<String, ChromeSafariBrowserManager> shared = new HashMap();
    public final Map<String, ChromeCustomTabsActivity> browsers;

    /* renamed from: id, reason: collision with root package name */
    public String f96id;
    public InAppWebViewFlutterPlugin plugin;

    public ChromeSafariBrowserManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.browsers = new HashMap();
        String uuid = UUID.randomUUID().toString();
        this.f96id = uuid;
        this.plugin = inAppWebViewFlutterPlugin;
        shared.put(uuid, this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0044, code lost:
    
        if (r0.equals("getMaxToolbarItems") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = (String) methodCall.argument("id");
        String str2 = methodCall.method;
        str2.hashCode();
        char c = 0;
        switch (str2.hashCode()) {
            case -1382009261:
                break;
            case 3417674:
                if (str2.equals("open")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 268490427:
                if (str2.equals("getPackageName")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 444517567:
                if (str2.equals("isAvailable")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                result.success(Integer.valueOf(CustomTabsIntent.getMaxToolbarItems()));
                return;
            case 1:
                InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
                if (inAppWebViewFlutterPlugin != null && inAppWebViewFlutterPlugin.activity != null) {
                    open(this.plugin.activity, str, (String) methodCall.argument("url"), (HashMap) methodCall.argument("headers"), (String) methodCall.argument("referrer"), (ArrayList) methodCall.argument("otherLikelyURLs"), (HashMap) methodCall.argument("settings"), (HashMap) methodCall.argument("actionButton"), (HashMap) methodCall.argument("secondaryToolbar"), (List) methodCall.argument("menuItemList"), result);
                    return;
                }
                result.success(false);
                return;
            case 2:
                InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin2 = this.plugin;
                if (inAppWebViewFlutterPlugin2 != null && inAppWebViewFlutterPlugin2.activity != null) {
                    result.success(CustomTabsClient.getPackageName(this.plugin.activity, (ArrayList) methodCall.argument(SdkVersion.JsonKeys.PACKAGES), ((Boolean) methodCall.argument("ignoreDefault")).booleanValue()));
                    return;
                }
                result.success(null);
                return;
            case 3:
                InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin3 = this.plugin;
                if (inAppWebViewFlutterPlugin3 != null && inAppWebViewFlutterPlugin3.activity != null) {
                    result.success(Boolean.valueOf(CustomTabActivityHelper.isAvailable(this.plugin.activity)));
                    return;
                } else {
                    result.success(false);
                    return;
                }
            default:
                result.notImplemented();
                return;
        }
    }

    public void open(Activity activity, String str, String str2, HashMap<String, Object> hashMap, String str3, ArrayList<String> arrayList, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3, HashMap<String, Object> hashMap4, List<HashMap<String, Object>> list, MethodChannel.Result result) {
        Class cls;
        Bundle bundle = new Bundle();
        bundle.putString("url", str2);
        bundle.putString("id", str);
        bundle.putString("managerId", this.f96id);
        bundle.putSerializable("headers", hashMap);
        bundle.putString("referrer", str3);
        bundle.putSerializable("otherLikelyURLs", arrayList);
        bundle.putSerializable("settings", hashMap2);
        bundle.putSerializable("actionButton", hashMap3);
        bundle.putSerializable("secondaryToolbar", hashMap4);
        bundle.putSerializable("menuItemList", (Serializable) list);
        Boolean bool = (Boolean) Util.getOrDefault(hashMap2, "isSingleInstance", false);
        Boolean bool2 = (Boolean) Util.getOrDefault(hashMap2, "isTrustedWebActivity", false);
        if (CustomTabActivityHelper.isAvailable(activity)) {
            if (!bool.booleanValue()) {
                cls = !bool2.booleanValue() ? ChromeCustomTabsActivity.class : TrustedWebActivity.class;
            } else {
                cls = !bool2.booleanValue() ? ChromeCustomTabsActivitySingleInstance.class : TrustedWebActivitySingleInstance.class;
            }
            Intent intent = new Intent(activity, (Class<?>) cls);
            intent.putExtras(bundle);
            if (((Boolean) Util.getOrDefault(hashMap2, "noHistory", false)).booleanValue()) {
                intent.addFlags(1073741824);
            }
            activity.startActivity(intent);
            result.success(true);
            return;
        }
        result.error(LOG_TAG, "ChromeCustomTabs is not available!", null);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        for (ChromeCustomTabsActivity chromeCustomTabsActivity : this.browsers.values()) {
            if (chromeCustomTabsActivity != null) {
                chromeCustomTabsActivity.close();
                chromeCustomTabsActivity.dispose();
            }
        }
        this.browsers.clear();
        shared.remove(this.f96id);
        this.plugin = null;
    }
}
