package com.pichillilorenzo.flutter_inappwebview_android.in_app_browser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
public class InAppBrowserManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "InAppBrowserManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappbrowser";
    public static final Map<String, InAppBrowserManager> shared = new HashMap();

    /* renamed from: id, reason: collision with root package name */
    public String f99id;
    public InAppWebViewFlutterPlugin plugin;

    public InAppBrowserManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        String uuid = UUID.randomUUID().toString();
        this.f99id = uuid;
        this.plugin = inAppWebViewFlutterPlugin;
        shared.put(uuid, this);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        if (str.equals("openWithSystemBrowser")) {
            InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
            if (inAppWebViewFlutterPlugin != null && inAppWebViewFlutterPlugin.activity != null) {
                openWithSystemBrowser(this.plugin.activity, (String) methodCall.argument("url"), result);
                return;
            } else {
                result.success(false);
                return;
            }
        }
        if (str.equals("open")) {
            InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin2 = this.plugin;
            if (inAppWebViewFlutterPlugin2 != null && inAppWebViewFlutterPlugin2.activity != null) {
                open(this.plugin.activity, (Map) methodCall.arguments());
                result.success(true);
                return;
            } else {
                result.success(false);
                return;
            }
        }
        result.notImplemented();
    }

    public static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    public void openWithSystemBrowser(Activity activity, String str, MethodChannel.Result result) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri parse = Uri.parse(str);
            if ("file".equals(parse.getScheme())) {
                intent.setDataAndType(parse, getMimeType(str));
            } else {
                intent.setData(parse);
            }
            intent.putExtra("com.android.browser.application_id", activity.getPackageName());
            openExternalExcludeCurrentApp(activity, intent);
            result.success(true);
        } catch (RuntimeException e) {
            Log.d(LOG_TAG, str + " cannot be opened: " + e.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" cannot be opened!");
            result.error(LOG_TAG, sb.toString(), null);
        }
    }

    public void openExternalExcludeCurrentApp(Activity activity, Intent intent) {
        String packageName = activity.getPackageName();
        List<ResolveInfo> queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (packageName.equals(resolveInfo.activityInfo.packageName)) {
                z = true;
            } else {
                Intent intent2 = (Intent) intent.clone();
                intent2.setPackage(resolveInfo.activityInfo.packageName);
                arrayList.add(intent2);
            }
        }
        if (!z || arrayList.size() == 0) {
            activity.startActivity(intent);
            return;
        }
        if (arrayList.size() == 1) {
            activity.startActivity((Intent) arrayList.get(0));
        } else if (arrayList.size() > 0) {
            Intent createChooser = Intent.createChooser((Intent) arrayList.remove(arrayList.size() - 1), null);
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            activity.startActivity(createChooser);
        }
    }

    public void open(Activity activity, Map<String, Object> map) {
        String str = (String) map.get("id");
        Map map2 = (Map) map.get("urlRequest");
        String str2 = (String) map.get("assetFilePath");
        String str3 = (String) map.get("data");
        String str4 = (String) map.get("mimeType");
        String str5 = (String) map.get("encoding");
        String str6 = (String) map.get("baseUrl");
        String str7 = (String) map.get("historyUrl");
        Map map3 = (Map) map.get("settings");
        Map map4 = (Map) map.get("contextMenu");
        Integer num = (Integer) map.get("windowId");
        List list = (List) map.get("initialUserScripts");
        Map map5 = (Map) map.get("pullToRefreshSettings");
        List list2 = (List) map.get("menuItems");
        Bundle bundle = new Bundle();
        bundle.putString("fromActivity", activity.getClass().getName());
        bundle.putSerializable("initialUrlRequest", (Serializable) map2);
        bundle.putString("initialFile", str2);
        bundle.putString("initialData", str3);
        bundle.putString("initialMimeType", str4);
        bundle.putString("initialEncoding", str5);
        bundle.putString("initialBaseUrl", str6);
        bundle.putString("initialHistoryUrl", str7);
        bundle.putString("id", str);
        bundle.putString("managerId", this.f99id);
        bundle.putSerializable("settings", (Serializable) map3);
        bundle.putSerializable("contextMenu", (Serializable) map4);
        bundle.putInt("windowId", num != null ? num.intValue() : -1);
        bundle.putSerializable("initialUserScripts", (Serializable) list);
        bundle.putSerializable("pullToRefreshInitialSettings", (Serializable) map5);
        bundle.putSerializable("menuItems", (Serializable) list2);
        startInAppBrowserActivity(activity, bundle);
    }

    public void startInAppBrowserActivity(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, (Class<?>) InAppBrowserActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        shared.remove(this.f99id);
        this.plugin = null;
    }
}
