package io.flutter.plugins.webviewflutter;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;

/* loaded from: classes5.dex */
public class JavaScriptChannel implements Releasable {
    private JavaScriptChannelFlutterApiImpl flutterApi;
    final String javaScriptChannelName;
    private final Handler platformThreadHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$postMessage$0(Void r0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$release$2(Void r0) {
    }

    public JavaScriptChannel(JavaScriptChannelFlutterApiImpl javaScriptChannelFlutterApiImpl, String str, Handler handler) {
        this.flutterApi = javaScriptChannelFlutterApiImpl;
        this.javaScriptChannelName = str;
        this.platformThreadHandler = handler;
    }

    @JavascriptInterface
    public void postMessage(final String str) {
        Runnable runnable = new Runnable() { // from class: io.flutter.plugins.webviewflutter.JavaScriptChannel$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                JavaScriptChannel.this.m1106x6cafa204(str);
            }
        };
        if (this.platformThreadHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            this.platformThreadHandler.post(runnable);
        }
    }

    /* renamed from: lambda$postMessage$1$io-flutter-plugins-webviewflutter-JavaScriptChannel, reason: not valid java name */
    public /* synthetic */ void m1106x6cafa204(String str) {
        JavaScriptChannelFlutterApiImpl javaScriptChannelFlutterApiImpl = this.flutterApi;
        if (javaScriptChannelFlutterApiImpl != null) {
            javaScriptChannelFlutterApiImpl.postMessage(this, str, new GeneratedAndroidWebView.JavaScriptChannelFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.JavaScriptChannel$$ExternalSyntheticLambda0
                @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.JavaScriptChannelFlutterApi.Reply
                public final void reply(Object obj) {
                    JavaScriptChannel.lambda$postMessage$0((Void) obj);
                }
            });
        }
    }

    @Override // io.flutter.plugins.webviewflutter.Releasable
    public void release() {
        JavaScriptChannelFlutterApiImpl javaScriptChannelFlutterApiImpl = this.flutterApi;
        if (javaScriptChannelFlutterApiImpl != null) {
            javaScriptChannelFlutterApiImpl.dispose(this, new GeneratedAndroidWebView.JavaScriptChannelFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.JavaScriptChannel$$ExternalSyntheticLambda1
                @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.JavaScriptChannelFlutterApi.Reply
                public final void reply(Object obj) {
                    JavaScriptChannel.lambda$release$2((Void) obj);
                }
            });
        }
        this.flutterApi = null;
    }
}
