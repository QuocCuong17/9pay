package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.text.TextUtils;
import android.webkit.WebView;
import androidx.webkit.ProxyConfig;
import androidx.webkit.ScriptHandler;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js.PluginScriptsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class UserContentController implements Disposable {
    private static final String CONTENT_WORLDS_GENERATOR_JS_SOURCE = "(function() {  var interval = setInterval(function() {    if (document.body == null) {return;}    var contentWorldNames = [$IN_APP_WEBVIEW_CONTENT_WORLD_NAME_ARRAY];    for (var contentWorldName of contentWorldNames) {      var iframeId = 'flutter_inappwebview_' + contentWorldName;      var iframe = document.getElementById(iframeId);      if (iframe == null) {        iframe = document.createElement('iframe');        iframe.id = iframeId;        iframe.style = 'display: none; z-index: 0; position: absolute; width: 0px; height: 0px';        document.body.append(iframe);      }      if (iframe.contentWindow.document.getElementById('flutter_inappwebview_plugin_scripts') == null) {        var script = iframe.contentWindow.document.createElement('script');        script.id = 'flutter_inappwebview_plugin_scripts';        script.innerHTML = $IN_APP_WEBVIEW_JSON_SOURCE_ENCODED;        iframe.contentWindow.document.body.append(script);      }    }    clearInterval(interval);  });})();";
    private static final String CONTENT_WORLD_WRAPPER_JS_SOURCE = "(function() {  var interval = setInterval(function() {    if (document.body == null) {return;}    var iframeId = 'flutter_inappwebview_$IN_APP_WEBVIEW_CONTENT_WORLD_NAME';    var iframe = document.getElementById(iframeId);    if (iframe == null) {      iframe = document.createElement('iframe');      iframe.id = iframeId;      iframe.style = 'display: none; z-index: 0; position: absolute; width: 0px; height: 0px';      document.body.append(iframe);    }    if (iframe.contentWindow.document.querySelector('#flutter_inappwebview_plugin_scripts') == null) {      return;    }    var script = iframe.contentWindow.document.createElement('script');    script.innerHTML = $IN_APP_WEBVIEW_JSON_SOURCE_ENCODED;    iframe.contentWindow.document.body.append(script);    clearInterval(interval);  });})();";
    private static final String DOCUMENT_READY_WRAPPER_JS_SOURCE = "if (document.readyState === 'interactive' || document.readyState === 'complete') {   $IN_APP_WEBVIEW_PLACEHOLDER_VALUE}";
    protected static final String LOG_TAG = "UserContentController";
    private static final String USER_SCRIPTS_AT_DOCUMENT_END_WRAPPER_JS_SOURCE = "if (window.flutter_inappwebview != null && (window.flutter_inappwebview._userScriptsAtDocumentEndLoaded == null || !window.flutter_inappwebview._userScriptsAtDocumentEndLoaded)) {  window.flutter_inappwebview._userScriptsAtDocumentEndLoaded = true;  $IN_APP_WEBVIEW_PLACEHOLDER_VALUE}";
    private static final String USER_SCRIPTS_AT_DOCUMENT_START_WRAPPER_JS_SOURCE = "if (window.flutter_inappwebview != null && (window.flutter_inappwebview._userScriptsAtDocumentStartLoaded == null || !window.flutter_inappwebview._userScriptsAtDocumentStartLoaded)) {  window.flutter_inappwebview._userScriptsAtDocumentStartLoaded = true;  $IN_APP_WEBVIEW_PLACEHOLDER_VALUE}";
    private ScriptHandler contentWorldsCreatorScript;
    public WebView webView;
    private final Set<ContentWorld> contentWorlds = new HashSet<ContentWorld>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.UserContentController.1
        {
            add(ContentWorld.PAGE);
        }
    };
    private final Map<UserScript, ScriptHandler> scriptHandlerMap = new HashMap();
    private final Map<UserScriptInjectionTime, LinkedHashSet<UserScript>> userOnlyScripts = new HashMap<UserScriptInjectionTime, LinkedHashSet<UserScript>>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.UserContentController.2
        {
            put(UserScriptInjectionTime.AT_DOCUMENT_START, new LinkedHashSet());
            put(UserScriptInjectionTime.AT_DOCUMENT_END, new LinkedHashSet());
        }
    };
    private final Map<UserScriptInjectionTime, LinkedHashSet<PluginScript>> pluginScripts = new HashMap<UserScriptInjectionTime, LinkedHashSet<PluginScript>>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.UserContentController.3
        {
            put(UserScriptInjectionTime.AT_DOCUMENT_START, new LinkedHashSet());
            put(UserScriptInjectionTime.AT_DOCUMENT_END, new LinkedHashSet());
        }
    };

    public UserContentController(WebView webView) {
        this.webView = webView;
    }

    public String generateWrappedCodeForDocumentStart() {
        return Util.replaceAll(DOCUMENT_READY_WRAPPER_JS_SOURCE, PluginScriptsUtil.VAR_PLACEHOLDER_VALUE, generateCodeForDocumentStart());
    }

    public String generateWrappedCodeForDocumentEnd() {
        UserScriptInjectionTime userScriptInjectionTime = UserScriptInjectionTime.AT_DOCUMENT_END;
        String str = "";
        if (!WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            str = "" + generateCodeForDocumentStart();
        }
        return USER_SCRIPTS_AT_DOCUMENT_END_WRAPPER_JS_SOURCE.replace(PluginScriptsUtil.VAR_PLACEHOLDER_VALUE, (str + generatePluginScriptsCodeAt(userScriptInjectionTime)) + generateUserOnlyScriptsCodeAt(userScriptInjectionTime));
    }

    public String generateCodeForDocumentStart() {
        UserScriptInjectionTime userScriptInjectionTime = UserScriptInjectionTime.AT_DOCUMENT_START;
        return USER_SCRIPTS_AT_DOCUMENT_START_WRAPPER_JS_SOURCE.replace(PluginScriptsUtil.VAR_PLACEHOLDER_VALUE, (("" + generatePluginScriptsCodeAt(userScriptInjectionTime)) + generateContentWorldsCreatorCode()) + generateUserOnlyScriptsCodeAt(userScriptInjectionTime));
    }

    public String generateContentWorldsCreatorCode() {
        if (this.contentWorlds.size() == 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<PluginScript> it = getPluginScriptsRequiredInAllContentWorlds().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getSource());
        }
        ArrayList arrayList = new ArrayList();
        for (ContentWorld contentWorld : this.contentWorlds) {
            if (!contentWorld.equals(ContentWorld.PAGE)) {
                arrayList.add("'" + escapeContentWorldName(contentWorld.getName()) + "'");
            }
        }
        return CONTENT_WORLDS_GENERATOR_JS_SOURCE.replace(PluginScriptsUtil.VAR_CONTENT_WORLD_NAME_ARRAY, TextUtils.join(", ", arrayList)).replace(PluginScriptsUtil.VAR_JSON_SOURCE_ENCODED, escapeCode(sb.toString()));
    }

    public String generatePluginScriptsCodeAt(UserScriptInjectionTime userScriptInjectionTime) {
        StringBuilder sb = new StringBuilder();
        Iterator<PluginScript> it = getPluginScriptsAt(userScriptInjectionTime).iterator();
        while (it.hasNext()) {
            PluginScript next = it.next();
            sb.append(wrapSourceCodeInContentWorld(next.getContentWorld(), ";" + next.getSource()));
        }
        return sb.toString();
    }

    public String generateUserOnlyScriptsCodeAt(UserScriptInjectionTime userScriptInjectionTime) {
        StringBuilder sb = new StringBuilder();
        Iterator<UserScript> it = getUserOnlyScriptsAt(userScriptInjectionTime).iterator();
        while (it.hasNext()) {
            UserScript next = it.next();
            sb.append(wrapSourceCodeInContentWorld(next.getContentWorld(), ";" + next.getSource()));
        }
        return sb.toString();
    }

    public String generateCodeForScriptEvaluation(String str, ContentWorld contentWorld) {
        if (contentWorld == null || contentWorld.equals(ContentWorld.PAGE)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (!this.contentWorlds.contains(contentWorld)) {
            this.contentWorlds.add(contentWorld);
            StringBuilder sb2 = new StringBuilder();
            Iterator<PluginScript> it = getPluginScriptsRequiredInAllContentWorlds().iterator();
            while (it.hasNext()) {
                sb2.append(it.next().getSource());
            }
            sb.append(CONTENT_WORLDS_GENERATOR_JS_SOURCE.replace(PluginScriptsUtil.VAR_CONTENT_WORLD_NAME_ARRAY, "'" + escapeContentWorldName(contentWorld.getName()) + "'").replace(PluginScriptsUtil.VAR_JSON_SOURCE_ENCODED, escapeCode(sb2.toString())));
            sb.append(";");
        }
        sb.append(wrapSourceCodeInContentWorld(contentWorld, str));
        return sb.toString();
    }

    public String wrapSourceCodeInContentWorld(ContentWorld contentWorld, String str) {
        return (contentWorld == null || contentWorld.equals(ContentWorld.PAGE)) ? str : CONTENT_WORLD_WRAPPER_JS_SOURCE.replace(PluginScriptsUtil.VAR_CONTENT_WORLD_NAME, escapeContentWorldName(contentWorld.getName())).replace(PluginScriptsUtil.VAR_JSON_SOURCE_ENCODED, escapeCode(str));
    }

    public static String escapeCode(String str) {
        return JSONObject.quote(str);
    }

    public static String escapeContentWorldName(String str) {
        return str.replaceAll("'", "\\\\'");
    }

    public LinkedHashSet<UserScript> getUserOnlyScriptsAt(UserScriptInjectionTime userScriptInjectionTime) {
        return new LinkedHashSet<>(this.userOnlyScripts.get(userScriptInjectionTime));
    }

    private void updateContentWorldsCreatorScript() {
        WebView webView;
        String generateContentWorldsCreatorCode = generateContentWorldsCreatorCode();
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            ScriptHandler scriptHandler = this.contentWorldsCreatorScript;
            if (scriptHandler != null) {
                scriptHandler.remove();
            }
            if (generateContentWorldsCreatorCode.isEmpty() || (webView = this.webView) == null) {
                return;
            }
            this.contentWorldsCreatorScript = WebViewCompat.addDocumentStartJavaScript(webView, generateContentWorldsCreatorCode, new HashSet<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.UserContentController.4
                {
                    add(ProxyConfig.MATCH_ALL_SCHEMES);
                }
            });
        }
    }

    public boolean addUserOnlyScript(UserScript userScript) {
        ContentWorld contentWorld = userScript.getContentWorld();
        if (contentWorld != null) {
            this.contentWorlds.add(contentWorld);
        }
        updateContentWorldsCreatorScript();
        if (this.webView != null && userScript.getInjectionTime() == UserScriptInjectionTime.AT_DOCUMENT_START && WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            this.scriptHandlerMap.put(userScript, WebViewCompat.addDocumentStartJavaScript(this.webView, wrapSourceCodeInContentWorld(userScript.getContentWorld(), userScript.getSource()), userScript.getAllowedOriginRules()));
        }
        return this.userOnlyScripts.get(userScript.getInjectionTime()).add(userScript);
    }

    public void addUserOnlyScripts(List<UserScript> list) {
        Iterator<UserScript> it = list.iterator();
        while (it.hasNext()) {
            addUserOnlyScript(it.next());
        }
    }

    public boolean removeUserOnlyScript(UserScript userScript) {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            ScriptHandler scriptHandler = this.scriptHandlerMap.get(userScript);
            if (scriptHandler != null) {
                scriptHandler.remove();
                this.scriptHandlerMap.remove(userScript);
            }
            updateContentWorldsCreatorScript();
        }
        return this.userOnlyScripts.get(userScript.getInjectionTime()).remove(userScript);
    }

    public boolean removeUserOnlyScriptAt(int i, UserScriptInjectionTime userScriptInjectionTime) {
        return removeUserOnlyScript((UserScript) new ArrayList(this.userOnlyScripts.get(userScriptInjectionTime)).get(i));
    }

    public void removeAllUserOnlyScripts() {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            Iterator<UserScript> it = this.userOnlyScripts.get(UserScriptInjectionTime.AT_DOCUMENT_START).iterator();
            while (it.hasNext()) {
                UserScript next = it.next();
                ScriptHandler scriptHandler = this.scriptHandlerMap.get(next);
                if (scriptHandler != null) {
                    scriptHandler.remove();
                    this.scriptHandlerMap.remove(next);
                }
            }
        }
        this.userOnlyScripts.get(UserScriptInjectionTime.AT_DOCUMENT_START).clear();
        this.userOnlyScripts.get(UserScriptInjectionTime.AT_DOCUMENT_END).clear();
    }

    public LinkedHashSet<PluginScript> getPluginScriptsAt(UserScriptInjectionTime userScriptInjectionTime) {
        return new LinkedHashSet<>(this.pluginScripts.get(userScriptInjectionTime));
    }

    public LinkedHashSet<PluginScript> getPluginScriptsRequiredInAllContentWorlds() {
        LinkedHashSet<PluginScript> linkedHashSet = new LinkedHashSet<>();
        Iterator<PluginScript> it = getPluginScriptsAt(UserScriptInjectionTime.AT_DOCUMENT_START).iterator();
        while (it.hasNext()) {
            PluginScript next = it.next();
            if (next.isRequiredInAllContentWorlds()) {
                linkedHashSet.add(next);
            }
        }
        return linkedHashSet;
    }

    public boolean addPluginScript(PluginScript pluginScript) {
        ContentWorld contentWorld = pluginScript.getContentWorld();
        if (contentWorld != null) {
            this.contentWorlds.add(contentWorld);
        }
        updateContentWorldsCreatorScript();
        if (this.webView != null && pluginScript.getInjectionTime() == UserScriptInjectionTime.AT_DOCUMENT_START && WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            this.scriptHandlerMap.put(pluginScript, WebViewCompat.addDocumentStartJavaScript(this.webView, wrapSourceCodeInContentWorld(pluginScript.getContentWorld(), pluginScript.getSource()), pluginScript.getAllowedOriginRules()));
        }
        return this.pluginScripts.get(pluginScript.getInjectionTime()).add(pluginScript);
    }

    public void addPluginScripts(List<PluginScript> list) {
        Iterator<PluginScript> it = list.iterator();
        while (it.hasNext()) {
            addPluginScript(it.next());
        }
    }

    public boolean removePluginScript(PluginScript pluginScript) {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            ScriptHandler scriptHandler = this.scriptHandlerMap.get(pluginScript);
            if (scriptHandler != null) {
                scriptHandler.remove();
                this.scriptHandlerMap.remove(pluginScript);
            }
            updateContentWorldsCreatorScript();
        }
        return this.pluginScripts.get(pluginScript.getInjectionTime()).remove(pluginScript);
    }

    public void removeAllPluginScripts() {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            Iterator<PluginScript> it = this.pluginScripts.get(UserScriptInjectionTime.AT_DOCUMENT_START).iterator();
            while (it.hasNext()) {
                PluginScript next = it.next();
                ScriptHandler scriptHandler = this.scriptHandlerMap.get(next);
                if (scriptHandler != null) {
                    scriptHandler.remove();
                    this.scriptHandlerMap.remove(next);
                }
            }
        }
        this.pluginScripts.get(UserScriptInjectionTime.AT_DOCUMENT_START).clear();
        this.pluginScripts.get(UserScriptInjectionTime.AT_DOCUMENT_END).clear();
    }

    public LinkedHashSet<UserScript> getUserOnlyScriptAsList() {
        LinkedHashSet<UserScript> linkedHashSet = new LinkedHashSet<>();
        Iterator<LinkedHashSet<UserScript>> it = this.userOnlyScripts.values().iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next());
        }
        return linkedHashSet;
    }

    public LinkedHashSet<PluginScript> getPluginScriptAsList() {
        LinkedHashSet<PluginScript> linkedHashSet = new LinkedHashSet<>();
        Iterator<LinkedHashSet<PluginScript>> it = this.pluginScripts.values().iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next());
        }
        return linkedHashSet;
    }

    public void resetContentWorlds() {
        this.contentWorlds.clear();
        this.contentWorlds.add(ContentWorld.PAGE);
        Iterator<PluginScript> it = getPluginScriptAsList().iterator();
        while (it.hasNext()) {
            this.contentWorlds.add(it.next().getContentWorld());
        }
        Iterator<UserScript> it2 = getUserOnlyScriptAsList().iterator();
        while (it2.hasNext()) {
            this.contentWorlds.add(it2.next().getContentWorld());
        }
    }

    public boolean containsPluginScript(PluginScript pluginScript) {
        return getPluginScriptAsList().contains(pluginScript);
    }

    public boolean containsPluginScriptByGroupName(String str) {
        Iterator<PluginScript> it = getPluginScriptAsList().iterator();
        while (it.hasNext()) {
            if (Util.objEquals(str, it.next().getGroupName())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsUserOnlyScript(UserScript userScript) {
        return getUserOnlyScriptAsList().contains(userScript);
    }

    public boolean containsUserOnlyScriptByGroupName(String str) {
        Iterator<UserScript> it = getUserOnlyScriptAsList().iterator();
        while (it.hasNext()) {
            if (Util.objEquals(str, it.next().getGroupName())) {
                return true;
            }
        }
        return false;
    }

    public void removePluginScriptsByGroupName(String str) {
        Iterator<PluginScript> it = getPluginScriptAsList().iterator();
        while (it.hasNext()) {
            PluginScript next = it.next();
            if (Util.objEquals(str, next.getGroupName())) {
                removePluginScript(next);
            }
        }
    }

    public void removeUserOnlyScriptsByGroupName(String str) {
        Iterator<UserScript> it = getUserOnlyScriptAsList().iterator();
        while (it.hasNext()) {
            UserScript next = it.next();
            if (Util.objEquals(str, next.getGroupName())) {
                removeUserOnlyScript(next);
            }
        }
    }

    public LinkedHashSet<ContentWorld> getContentWorlds() {
        return new LinkedHashSet<>(this.contentWorlds);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        ScriptHandler scriptHandler;
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT) && (scriptHandler = this.contentWorldsCreatorScript) != null) {
            scriptHandler.remove();
        }
        removeAllUserOnlyScripts();
        removeAllPluginScripts();
        this.webView = null;
    }
}
