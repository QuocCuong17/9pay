package io.flutter.plugins.webviewflutter;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ListPopupWindow;

/* loaded from: classes5.dex */
class InputAwareWebView extends WebView {
    private static final String TAG = "InputAwareWebView";
    private View containerView;
    private ThreadedInputConnectionProxyAdapterView proxyAdapterView;
    private View threadedInputConnectionProxyView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputAwareWebView(Context context, View view) {
        super(context);
        this.containerView = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContainerView(View view) {
        this.containerView = view;
        if (this.proxyAdapterView == null) {
            return;
        }
        Log.w(TAG, "The containerView has changed while the proxyAdapterView exists.");
        if (view != null) {
            setInputConnectionTarget(this.proxyAdapterView);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void lockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView == null) {
            return;
        }
        threadedInputConnectionProxyAdapterView.setLocked(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unlockInputConnection() {
        ThreadedInputConnectionProxyAdapterView threadedInputConnectionProxyAdapterView = this.proxyAdapterView;
        if (threadedInputConnectionProxyAdapterView == null) {
            return;
        }
        threadedInputConnectionProxyAdapterView.setLocked(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        resetInputConnection();
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        View view2 = this.threadedInputConnectionProxyView;
        this.threadedInputConnectionProxyView = view;
        if (view2 == view) {
            return super.checkInputConnectionProxy(view);
        }
        if (this.containerView == null) {
            Log.e(TAG, "Can't create a proxy view because there's no container view. Text input may not work.");
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
        resetInputConnection();
    }

    private void resetInputConnection() {
        if (this.proxyAdapterView == null) {
            return;
        }
        View view = this.containerView;
        if (view == null) {
            Log.e(TAG, "Can't reset the input connection to the container view because there is none.");
        } else {
            setInputConnectionTarget(view);
        }
    }

    void setInputConnectionTarget(final View view) {
        if (this.containerView == null) {
            Log.e(TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
        } else {
            view.requestFocus();
            this.containerView.post(new Runnable() { // from class: io.flutter.plugins.webviewflutter.InputAwareWebView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (InputAwareWebView.this.containerView == null) {
                        Log.e(InputAwareWebView.TAG, "Can't set the input connection target because there is no containerView to use as a handler.");
                        return;
                    }
                    InputMethodManager inputMethodManager = (InputMethodManager) InputAwareWebView.this.getContext().getSystemService("input_method");
                    view.onWindowFocusChanged(true);
                    inputMethodManager.isActive(InputAwareWebView.this.containerView);
                }
            });
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (Build.VERSION.SDK_INT >= 28 || !isCalledFromListPopupWindowShow() || z) {
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
