package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ListPopupWindow;

/* loaded from: classes5.dex */
public class InputAwareWebView extends WebView {
    private static final String LOG_TAG = "InputAwareWebView";
    public View containerView;
    private ThreadedInputConnectionProxyAdapterView proxyAdapterView;
    private View threadedInputConnectionProxyView;
    private boolean useHybridComposition;

    public InputAwareWebView(Context context, View view, Boolean bool) {
        super(context);
        this.useHybridComposition = false;
        this.containerView = view;
        this.useHybridComposition = bool != null ? bool.booleanValue() : false;
    }

    public InputAwareWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.useHybridComposition = false;
        this.containerView = null;
    }

    public InputAwareWebView(Context context) {
        super(context);
        this.useHybridComposition = false;
        this.containerView = null;
    }

    public InputAwareWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.useHybridComposition = false;
        this.containerView = null;
    }

    public void setContainerView(View view) {
        this.containerView = view;
        if (this.proxyAdapterView == null) {
            return;
        }
        Log.w(LOG_TAG, "The containerView has changed while the proxyAdapterView exists.");
        if (view != null) {
            setInputConnectionTarget(this.proxyAdapterView);
        }
    }

    public void lockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView == null) {
            return;
        }
        threadedInputConnectionProxyAdapterView.setLocked(true);
    }

    public void unlockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView == null) {
            return;
        }
        threadedInputConnectionProxyAdapterView.setLocked(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        if (this.useHybridComposition) {
            return;
        }
        resetInputConnection();
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        if (this.useHybridComposition) {
            return super.checkInputConnectionProxy(view);
        }
        View view2 = this.threadedInputConnectionProxyView;
        this.threadedInputConnectionProxyView = view;
        if (view2 == view) {
            return super.checkInputConnectionProxy(view);
        }
        if (this.containerView == null) {
            Log.e(LOG_TAG, "Can't create a proxy view because there's no container view. Text input may not work.");
            return super.checkInputConnectionProxy(view);
        }
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = new ThreadedInputConnectionProxyAdapterView(this.containerView, view, view.getHandler());
        this.proxyAdapterView = threadedInputConnectionProxyAdapterView;
        setInputConnectionTarget(threadedInputConnectionProxyAdapterView);
        return super.checkInputConnectionProxy(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        super.clearFocus();
        if (this.useHybridComposition) {
            return;
        }
        resetInputConnection();
    }

    private void resetInputConnection() {
        if (this.proxyAdapterView == null) {
            return;
        }
        View view = this.containerView;
        if (view == null) {
            Log.e(LOG_TAG, "Can't reset the input connection to the container view because there is none.");
        } else {
            setInputConnectionTarget(view);
        }
    }

    private void setInputConnectionTarget(final View view) {
        if (this.containerView == null) {
            Log.e(LOG_TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
        } else {
            view.requestFocus();
            this.containerView.post(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InputAwareWebView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (InputAwareWebView.this.containerView == null) {
                        Log.e(InputAwareWebView.LOG_TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
                        return;
                    }
                    InputMethodManager inputMethodManager = (InputMethodManager) InputAwareWebView.this.getContext().getSystemService("input_method");
                    view.onWindowFocusChanged(true);
                    if (Build.VERSION.SDK_INT < 29) {
                        inputMethodManager.isActive(InputAwareWebView.this.containerView);
                    }
                }
            });
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (this.useHybridComposition) {
            super.onFocusChanged(z, i, rect);
        } else if (Build.VERSION.SDK_INT >= 28 || !isCalledFromListPopupWindowShow() || z) {
            super.onFocusChanged(z, i, rect);
        }
    }

    private boolean isCalledFromListPopupWindowShow() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().equals(ListPopupWindow.class.getCanonicalName()) && stackTraceElement.getMethodName().equals("show")) {
                return true;
            }
        }
        return false;
    }
}
