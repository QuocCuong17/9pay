package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomTabsSecondaryToolbar;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ChromeCustomTabsChannelDelegate extends ChannelDelegateImpl {
    private ChromeCustomTabsActivity chromeCustomTabsActivity;

    public ChromeCustomTabsChannelDelegate(ChromeCustomTabsActivity chromeCustomTabsActivity, MethodChannel methodChannel) {
        super(methodChannel);
        this.chromeCustomTabsActivity = chromeCustomTabsActivity;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x006e, code lost:
    
        if (r0.equals("launchUrl") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 1;
        switch (str.hashCode()) {
            case -1526944655:
                if (str.equals("isEngagementSignalsApiAvailable")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -675108676:
                break;
            case -334843312:
                if (str.equals("updateSecondaryToolbar")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 50870385:
                if (str.equals("updateActionButton")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 94756344:
                if (str.equals("close")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1256059502:
                if (str.equals("validateRelationship")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1392239787:
                if (str.equals("requestPostMessageChannel")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1490029383:
                if (str.equals("postMessage")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2000053463:
                if (str.equals("mayLaunchUrl")) {
                    c = '\b';
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
                ChromeCustomTabsActivity chromeCustomTabsActivity = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity != null && chromeCustomTabsActivity.customTabsSession != null) {
                    try {
                        result.success(Boolean.valueOf(this.chromeCustomTabsActivity.customTabsSession.isEngagementSignalsApiAvailable(new Bundle())));
                        return;
                    } catch (Throwable unused) {
                        result.success(false);
                        return;
                    }
                }
                result.success(false);
                return;
            case 1:
                if (this.chromeCustomTabsActivity != null) {
                    String str2 = (String) methodCall.argument("url");
                    if (str2 != null) {
                        this.chromeCustomTabsActivity.launchUrl(str2, (Map) methodCall.argument("headers"), (String) methodCall.argument("referrer"), (List) methodCall.argument("otherLikelyURLs"));
                        result.success(true);
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                }
                result.success(false);
                return;
            case 2:
                if (this.chromeCustomTabsActivity != null) {
                    this.chromeCustomTabsActivity.updateSecondaryToolbar(CustomTabsSecondaryToolbar.fromMap((Map) methodCall.argument("secondaryToolbar")));
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 3:
                if (this.chromeCustomTabsActivity != null) {
                    this.chromeCustomTabsActivity.updateActionButton((byte[]) methodCall.argument("icon"), (String) methodCall.argument("description"));
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 4:
                ChromeCustomTabsActivity chromeCustomTabsActivity2 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity2 != null) {
                    chromeCustomTabsActivity2.onStop();
                    this.chromeCustomTabsActivity.onDestroy();
                    this.chromeCustomTabsActivity.close();
                    if (this.chromeCustomTabsActivity.manager != null && this.chromeCustomTabsActivity.manager.plugin != null && this.chromeCustomTabsActivity.manager.plugin.activity != null) {
                        Activity activity = this.chromeCustomTabsActivity.manager.plugin.activity;
                        Intent intent = new Intent(activity, activity.getClass());
                        intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                        intent.addFlags(536870912);
                        activity.startActivity(intent);
                    }
                    this.chromeCustomTabsActivity.dispose();
                    result.success(true);
                    return;
                }
                result.success(false);
                return;
            case 5:
                ChromeCustomTabsActivity chromeCustomTabsActivity3 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity3 != null && chromeCustomTabsActivity3.customTabsSession != null) {
                    result.success(Boolean.valueOf(this.chromeCustomTabsActivity.customTabsSession.validateRelationship(((Integer) methodCall.argument("relation")).intValue(), Uri.parse((String) methodCall.argument("origin")), null)));
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 6:
                ChromeCustomTabsActivity chromeCustomTabsActivity4 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity4 != null && chromeCustomTabsActivity4.customTabsSession != null) {
                    String str3 = (String) methodCall.argument("sourceOrigin");
                    String str4 = (String) methodCall.argument("targetOrigin");
                    result.success(Boolean.valueOf(this.chromeCustomTabsActivity.customTabsSession.requestPostMessageChannel(Uri.parse(str3), str4 != null ? Uri.parse(str4) : null, new Bundle())));
                    return;
                }
                result.success(false);
                return;
            case 7:
                ChromeCustomTabsActivity chromeCustomTabsActivity5 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity5 != null && chromeCustomTabsActivity5.customTabsSession != null) {
                    result.success(Integer.valueOf(this.chromeCustomTabsActivity.customTabsSession.postMessage((String) methodCall.argument("message"), new Bundle())));
                    return;
                } else {
                    result.success(-3);
                    return;
                }
            case '\b':
                if (this.chromeCustomTabsActivity != null) {
                    result.success(Boolean.valueOf(this.chromeCustomTabsActivity.mayLaunchUrl((String) methodCall.argument("url"), (List) methodCall.argument("otherLikelyURLs"))));
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

    public void onServiceConnected() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onServiceConnected", new HashMap());
    }

    public void onOpened() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onOpened", new HashMap());
    }

    public void onCompletedInitialLoad() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onCompletedInitialLoad", new HashMap());
    }

    public void onNavigationEvent(int i) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("navigationEvent", Integer.valueOf(i));
        channel.invokeMethod("onNavigationEvent", hashMap);
    }

    public void onClosed() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onClosed", new HashMap());
    }

    public void onItemActionPerform(int i, String str, String str2) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.valueOf(i));
        hashMap.put("url", str);
        hashMap.put("title", str2);
        channel.invokeMethod("onItemActionPerform", hashMap);
    }

    public void onSecondaryItemActionPerform(String str, String str2) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("url", str2);
        channel.invokeMethod("onSecondaryItemActionPerform", hashMap);
    }

    public void onRelationshipValidationResult(int i, Uri uri, boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("relation", Integer.valueOf(i));
        hashMap.put("requestedOrigin", uri.toString());
        hashMap.put(Constant.PARAM_RESULT, Boolean.valueOf(z));
        channel.invokeMethod("onRelationshipValidationResult", hashMap);
    }

    public void onMessageChannelReady() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onMessageChannelReady", new HashMap());
    }

    public void onPostMessage(String str) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", str);
        channel.invokeMethod("onPostMessage", hashMap);
    }

    public void onVerticalScrollEvent(boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("isDirectionUp", Boolean.valueOf(z));
        channel.invokeMethod("onVerticalScrollEvent", hashMap);
    }

    public void onGreatestScrollPercentageIncreased(int i) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("scrollPercentage", Integer.valueOf(i));
        channel.invokeMethod("onGreatestScrollPercentageIncreased", hashMap);
    }

    public void onSessionEnded(boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("didUserInteract", Boolean.valueOf(z));
        channel.invokeMethod("onSessionEnded", hashMap);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.chromeCustomTabsActivity = null;
    }
}
