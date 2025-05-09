package com.pichillilorenzo.flutter_inappwebview_android;

import android.webkit.ValueCallback;
import android.webkit.WebStorage;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class MyWebStorage extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "MyWebStorage";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_webstoragemanager";
    public static WebStorage webStorageManager;
    public InAppWebViewFlutterPlugin plugin;

    public MyWebStorage(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static void init() {
        if (webStorageManager == null) {
            webStorageManager = WebStorage.getInstance();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0043, code lost:
    
        if (r0.equals("deleteAllData") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        init();
        String str = methodCall.method;
        str.hashCode();
        char c = 1;
        switch (str.hashCode()) {
            case -1308548435:
                if (str.equals("getQuotaForOrigin")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1117417280:
                break;
            case -876677967:
                if (str.equals("deleteOrigin")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -165580329:
                if (str.equals("getOrigins")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 843309476:
                if (str.equals("getUsageForOrigin")) {
                    c = 4;
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
                getQuotaForOrigin((String) methodCall.argument("origin"), result);
                return;
            case 1:
                WebStorage webStorage = webStorageManager;
                if (webStorage != null) {
                    webStorage.deleteAllData();
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 2:
                if (webStorageManager != null) {
                    webStorageManager.deleteOrigin((String) methodCall.argument("origin"));
                    result.success(true);
                    return;
                }
                result.success(false);
                return;
            case 3:
                getOrigins(result);
                return;
            case 4:
                getUsageForOrigin((String) methodCall.argument("origin"), result);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    public void getOrigins(final MethodChannel.Result result) {
        WebStorage webStorage = webStorageManager;
        if (webStorage == null) {
            result.success(new ArrayList());
        } else {
            webStorage.getOrigins(new ValueCallback<Map>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyWebStorage.1
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Map map) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = map.keySet().iterator();
                    while (it.hasNext()) {
                        WebStorage.Origin origin = (WebStorage.Origin) map.get(it.next());
                        HashMap hashMap = new HashMap();
                        hashMap.put("origin", origin.getOrigin());
                        hashMap.put("quota", Long.valueOf(origin.getQuota()));
                        hashMap.put("usage", Long.valueOf(origin.getUsage()));
                        arrayList.add(hashMap);
                    }
                    result.success(arrayList);
                }
            });
        }
    }

    public void getQuotaForOrigin(String str, final MethodChannel.Result result) {
        WebStorage webStorage = webStorageManager;
        if (webStorage == null) {
            result.success(0);
        } else {
            webStorage.getQuotaForOrigin(str, new ValueCallback<Long>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyWebStorage.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Long l) {
                    result.success(l);
                }
            });
        }
    }

    public void getUsageForOrigin(String str, final MethodChannel.Result result) {
        WebStorage webStorage = webStorageManager;
        if (webStorage == null) {
            result.success(0);
        } else {
            webStorage.getUsageForOrigin(str, new ValueCallback<Long>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyWebStorage.3
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Long l) {
                    result.success(l);
                }
            });
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }
}
