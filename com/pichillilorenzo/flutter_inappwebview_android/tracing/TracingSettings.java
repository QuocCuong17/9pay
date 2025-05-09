package com.pichillilorenzo.flutter_inappwebview_android.tracing;

import androidx.webkit.TracingController;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class TracingSettings implements ISettings<TracingController> {
    public static final String LOG_TAG = "TracingSettings";
    public List<Object> categories = new ArrayList();
    public Integer tracingMode;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<TracingController> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse, reason: avoid collision after fix types in other method */
    public ISettings<TracingController> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                if (key.equals("tracingMode")) {
                    this.tracingMode = (Integer) value;
                } else if (key.equals("categories")) {
                    this.categories = (List) value;
                }
            }
        }
        return this;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("categories", this.categories);
        hashMap.put("tracingMode", this.tracingMode);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(TracingController tracingController) {
        return toMap();
    }
}
