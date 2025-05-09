package com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh;

import android.graphics.Color;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class PullToRefreshChannelDelegate extends ChannelDelegateImpl {
    private PullToRefreshLayout pullToRefreshView;

    public PullToRefreshChannelDelegate(PullToRefreshLayout pullToRefreshLayout, MethodChannel methodChannel) {
        super(methodChannel);
        this.pullToRefreshView = pullToRefreshLayout;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        char c;
        String str = methodCall.method;
        str.hashCode();
        switch (str.hashCode()) {
            case -1790841290:
                if (str.equals("setSlingshotDistance")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 154556713:
                if (str.equals("setRefreshing")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1235582893:
                if (str.equals("getDefaultSlingshotDistance")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1364071551:
                if (str.equals("setEnabled")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1389555745:
                if (str.equals("setColor")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1743806995:
                if (str.equals("setBackgroundColor")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1807783361:
                if (str.equals("setDistanceToTriggerSync")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1849446385:
                if (str.equals("isRefreshing")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1984958339:
                if (str.equals("setSize")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 2105594551:
                if (str.equals("isEnabled")) {
                    c = '\t';
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
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setSlingshotDistance(((Integer) methodCall.argument("slingshotDistance")).intValue());
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 1:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setRefreshing(((Boolean) methodCall.argument("refreshing")).booleanValue());
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 2:
                result.success(-1);
                return;
            case 3:
                if (this.pullToRefreshView != null) {
                    Boolean bool = (Boolean) methodCall.argument("enabled");
                    this.pullToRefreshView.settings.enabled = bool;
                    this.pullToRefreshView.setEnabled(bool.booleanValue());
                    result.success(true);
                    return;
                }
                result.success(false);
                return;
            case 4:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setColorSchemeColors(Color.parseColor((String) methodCall.argument("color")));
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 5:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setProgressBackgroundColorSchemeColor(Color.parseColor((String) methodCall.argument("color")));
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 6:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setDistanceToTriggerSync(((Integer) methodCall.argument("distanceToTriggerSync")).intValue());
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 7:
                PullToRefreshLayout pullToRefreshLayout = this.pullToRefreshView;
                result.success(Boolean.valueOf(pullToRefreshLayout != null && pullToRefreshLayout.isRefreshing()));
                return;
            case '\b':
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setSize(((Integer) methodCall.argument("size")).intValue());
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case '\t':
                PullToRefreshLayout pullToRefreshLayout2 = this.pullToRefreshView;
                if (pullToRefreshLayout2 != null) {
                    result.success(Boolean.valueOf(pullToRefreshLayout2.isEnabled()));
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

    public void onRefresh() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onRefresh", new HashMap());
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.pullToRefreshView = null;
    }
}
