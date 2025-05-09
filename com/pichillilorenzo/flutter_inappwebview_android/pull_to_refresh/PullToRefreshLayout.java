package com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes2.dex */
public class PullToRefreshLayout extends SwipeRefreshLayout implements Disposable {
    static final String LOG_TAG = "PullToRefreshLayout";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_pull_to_refresh_";
    public PullToRefreshChannelDelegate channelDelegate;
    public PullToRefreshSettings settings;

    public PullToRefreshLayout(Context context, InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, Object obj, PullToRefreshSettings pullToRefreshSettings) {
        super(context);
        this.settings = new PullToRefreshSettings();
        this.settings = pullToRefreshSettings;
        this.channelDelegate = new PullToRefreshChannelDelegate(this, new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME_PREFIX + obj));
    }

    public PullToRefreshLayout(Context context) {
        super(context);
        this.settings = new PullToRefreshSettings();
    }

    public PullToRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.settings = new PullToRefreshSettings();
    }

    public void prepare() {
        setEnabled(this.settings.enabled.booleanValue());
        setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh.PullToRefreshLayout.1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnChildScrollUpCallback
            public boolean canChildScrollUp(SwipeRefreshLayout swipeRefreshLayout, View view) {
                if (!(view instanceof InAppWebView)) {
                    return true;
                }
                InAppWebView inAppWebView = (InAppWebView) view;
                if (!inAppWebView.canScrollVertically() || inAppWebView.getScrollY() <= 0) {
                    return !inAppWebView.canScrollVertically() && inAppWebView.getScrollY() == 0;
                }
                return true;
            }
        });
        setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh.PullToRefreshLayout.2
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                if (PullToRefreshLayout.this.channelDelegate == null) {
                    this.setRefreshing(false);
                } else {
                    PullToRefreshLayout.this.channelDelegate.onRefresh();
                }
            }
        });
        if (this.settings.color != null) {
            setColorSchemeColors(Color.parseColor(this.settings.color));
        }
        if (this.settings.backgroundColor != null) {
            setProgressBackgroundColorSchemeColor(Color.parseColor(this.settings.backgroundColor));
        }
        if (this.settings.distanceToTriggerSync != null) {
            setDistanceToTriggerSync(this.settings.distanceToTriggerSync.intValue());
        }
        if (this.settings.slingshotDistance != null) {
            setSlingshotDistance(this.settings.slingshotDistance.intValue());
        }
        if (this.settings.size != null) {
            setSize(this.settings.size.intValue());
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        PullToRefreshChannelDelegate pullToRefreshChannelDelegate = this.channelDelegate;
        if (pullToRefreshChannelDelegate != null) {
            pullToRefreshChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        removeAllViews();
    }
}
