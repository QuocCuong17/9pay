package com.pichillilorenzo.flutter_inappwebview_android.webview;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js.JavaScriptBridgeJS;
import com.pichillilorenzo.flutter_inappwebview_android.print_job.PrintJobController;
import com.pichillilorenzo.flutter_inappwebview_android.print_job.PrintJobSettings;
import com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JavaScriptBridgeInterface {
    private static final String LOG_TAG = "JSBridgeInterface";
    private InAppWebView inAppWebView;

    public JavaScriptBridgeInterface(InAppWebView inAppWebView) {
        this.inAppWebView = inAppWebView;
    }

    @JavascriptInterface
    public void _hideContextMenu() {
        if (this.inAppWebView == null) {
            return;
        }
        new Handler(this.inAppWebView.getWebViewLooper()).post(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.JavaScriptBridgeInterface.1
            @Override // java.lang.Runnable
            public void run() {
                if (JavaScriptBridgeInterface.this.inAppWebView == null || JavaScriptBridgeInterface.this.inAppWebView.floatingContextMenu == null) {
                    return;
                }
                JavaScriptBridgeInterface.this.inAppWebView.hideContextMenu();
            }
        });
    }

    @JavascriptInterface
    public void _callHandler(final String str, final String str2, final String str3) {
        if (this.inAppWebView == null) {
            return;
        }
        new Handler(this.inAppWebView.getWebViewLooper()).post(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.JavaScriptBridgeInterface.2
            @Override // java.lang.Runnable
            public void run() {
                if (JavaScriptBridgeInterface.this.inAppWebView == null) {
                    return;
                }
                if (str.equals("onPrintRequest") && Build.VERSION.SDK_INT >= 21) {
                    PrintJobSettings printJobSettings = new PrintJobSettings();
                    printJobSettings.handledByClient = true;
                    final String printCurrentPage = JavaScriptBridgeInterface.this.inAppWebView.printCurrentPage(printJobSettings);
                    if (JavaScriptBridgeInterface.this.inAppWebView == null || JavaScriptBridgeInterface.this.inAppWebView.channelDelegate == null) {
                        return;
                    }
                    JavaScriptBridgeInterface.this.inAppWebView.channelDelegate.onPrintRequest(JavaScriptBridgeInterface.this.inAppWebView.getUrl(), printCurrentPage, new WebViewChannelDelegate.PrintRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.JavaScriptBridgeInterface.2.1
                        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                        public boolean nonNullSuccess(Boolean bool) {
                            return !bool.booleanValue();
                        }

                        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                        public void defaultBehaviour(Boolean bool) {
                            PrintJobController printJobController;
                            if (JavaScriptBridgeInterface.this.inAppWebView == null || JavaScriptBridgeInterface.this.inAppWebView.plugin == null || JavaScriptBridgeInterface.this.inAppWebView.plugin.printJobManager == null || (printJobController = JavaScriptBridgeInterface.this.inAppWebView.plugin.printJobManager.jobs.get(printCurrentPage)) == null) {
                                return;
                            }
                            printJobController.disposeNoCancel();
                        }

                        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
                        public void error(String str4, String str5, Object obj) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str4);
                            sb.append(", ");
                            if (str5 == null) {
                                str5 = "";
                            }
                            sb.append(str5);
                            Log.e(JavaScriptBridgeInterface.LOG_TAG, sb.toString());
                            defaultBehaviour((Boolean) null);
                        }
                    });
                    return;
                }
                if (str.equals("callAsyncJavaScript")) {
                    try {
                        JSONObject jSONObject = new JSONArray(str3).getJSONObject(0);
                        String string = jSONObject.getString("resultUuid");
                        ValueCallback<String> valueCallback = JavaScriptBridgeInterface.this.inAppWebView.callAsyncJavaScriptCallbacks.get(string);
                        if (valueCallback != null) {
                            valueCallback.onReceiveValue(jSONObject.toString());
                            JavaScriptBridgeInterface.this.inAppWebView.callAsyncJavaScriptCallbacks.remove(string);
                            return;
                        }
                        return;
                    } catch (JSONException e) {
                        Log.e(JavaScriptBridgeInterface.LOG_TAG, "", e);
                        return;
                    }
                }
                if (!str.equals("evaluateJavaScriptWithContentWorld")) {
                    if (JavaScriptBridgeInterface.this.inAppWebView.channelDelegate != null) {
                        JavaScriptBridgeInterface.this.inAppWebView.channelDelegate.onCallJsHandler(str, str3, new WebViewChannelDelegate.CallJsHandlerCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.JavaScriptBridgeInterface.2.2
                            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                            public void defaultBehaviour(Object obj) {
                                if (JavaScriptBridgeInterface.this.inAppWebView == null) {
                                    return;
                                }
                                String str4 = "if (window.flutter_inappwebview[" + str2 + "] != null) { window." + JavaScriptBridgeJS.JAVASCRIPT_BRIDGE_NAME + "[" + str2 + "].resolve(" + obj + "); delete window." + JavaScriptBridgeJS.JAVASCRIPT_BRIDGE_NAME + "[" + str2 + "]; }";
                                if (Build.VERSION.SDK_INT >= 19) {
                                    JavaScriptBridgeInterface.this.inAppWebView.evaluateJavascript(str4, null);
                                    return;
                                }
                                JavaScriptBridgeInterface.this.inAppWebView.loadUrl("javascript:" + str4);
                            }

                            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
                            public void error(String str4, String str5, Object obj) {
                                String str6;
                                StringBuilder sb = new StringBuilder();
                                sb.append(str4);
                                if (str5 != null) {
                                    str6 = ", " + str5;
                                } else {
                                    str6 = "";
                                }
                                sb.append(str6);
                                String sb2 = sb.toString();
                                Log.e(JavaScriptBridgeInterface.LOG_TAG, sb2);
                                if (JavaScriptBridgeInterface.this.inAppWebView == null) {
                                    return;
                                }
                                String str7 = "if (window.flutter_inappwebview[" + str2 + "] != null) { window." + JavaScriptBridgeJS.JAVASCRIPT_BRIDGE_NAME + "[" + str2 + "].reject(new Error(" + JSONObject.quote(sb2) + ")); delete window." + JavaScriptBridgeJS.JAVASCRIPT_BRIDGE_NAME + "[" + str2 + "]; }";
                                if (Build.VERSION.SDK_INT >= 19) {
                                    JavaScriptBridgeInterface.this.inAppWebView.evaluateJavascript(str7, null);
                                    return;
                                }
                                JavaScriptBridgeInterface.this.inAppWebView.loadUrl("javascript:" + str7);
                            }
                        });
                        return;
                    }
                    return;
                }
                try {
                    JSONObject jSONObject2 = new JSONArray(str3).getJSONObject(0);
                    String string2 = jSONObject2.getString("resultUuid");
                    ValueCallback<String> valueCallback2 = JavaScriptBridgeInterface.this.inAppWebView.evaluateJavaScriptContentWorldCallbacks.get(string2);
                    if (valueCallback2 != null) {
                        valueCallback2.onReceiveValue(jSONObject2.has("value") ? jSONObject2.get("value").toString() : "null");
                        JavaScriptBridgeInterface.this.inAppWebView.evaluateJavaScriptContentWorldCallbacks.remove(string2);
                    }
                } catch (JSONException e2) {
                    Log.e(JavaScriptBridgeInterface.LOG_TAG, "", e2);
                }
            }
        });
    }

    public void dispose() {
        this.inAppWebView = null;
    }
}
