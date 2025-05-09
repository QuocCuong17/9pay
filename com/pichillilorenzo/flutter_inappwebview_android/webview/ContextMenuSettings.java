package com.pichillilorenzo.flutter_inappwebview_android.webview;

import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ContextMenuSettings implements ISettings<Object> {
    public static final String LOG_TAG = "ContextMenuOptions";
    public Boolean hideDefaultSystemContextMenuItems = false;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<Object> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse, reason: avoid collision after fix types in other method */
    public ISettings<Object> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                if (key.equals("hideDefaultSystemContextMenuItems")) {
                    this.hideDefaultSystemContextMenuItems = (Boolean) value;
                }
            }
        }
        return this;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("hideDefaultSystemContextMenuItems", this.hideDefaultSystemContextMenuItems);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(Object obj) {
        return toMap();
    }
}
