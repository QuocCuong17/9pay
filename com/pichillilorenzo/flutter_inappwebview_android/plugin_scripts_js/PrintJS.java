package com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js;

import com.pichillilorenzo.flutter_inappwebview_android.types.PluginScript;
import com.pichillilorenzo.flutter_inappwebview_android.types.UserScriptInjectionTime;

/* loaded from: classes4.dex */
public class PrintJS {
    public static final String PRINT_JS_PLUGIN_SCRIPT_GROUP_NAME = "IN_APP_WEBVIEW_PRINT_JS_PLUGIN_SCRIPT";
    public static final String PRINT_JS_SOURCE = "window.print = function() {  if (window.top == null || window.top === window) {     window.flutter_inappwebview.callHandler('onPrintRequest', window.location.href);  } else {     window.top.print();  }};";
    public static final PluginScript PRINT_JS_PLUGIN_SCRIPT = new PluginScript(PRINT_JS_PLUGIN_SCRIPT_GROUP_NAME, PRINT_JS_SOURCE, UserScriptInjectionTime.AT_DOCUMENT_START, null, false, null);
}
