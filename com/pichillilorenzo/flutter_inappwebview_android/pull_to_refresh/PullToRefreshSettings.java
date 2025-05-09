package com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class PullToRefreshSettings implements ISettings<PullToRefreshLayout> {
    public static final String LOG_TAG = "PullToRefreshSettings";
    public String backgroundColor;
    public String color;
    public Integer distanceToTriggerSync;
    public Boolean enabled = true;
    public Integer size;
    public Integer slingshotDistance;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<PullToRefreshLayout> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0029. Please report as an issue. */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse, reason: avoid collision after fix types in other method */
    public ISettings<PullToRefreshLayout> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c = 65535;
                switch (key.hashCode()) {
                    case -1878912765:
                        if (key.equals("distanceToTriggerSync")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1609594047:
                        if (key.equals("enabled")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 3530753:
                        if (key.equals("size")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 94842723:
                        if (key.equals("color")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1287124693:
                        if (key.equals(TtmlNode.ATTR_TTS_BACKGROUND_COLOR)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1719097976:
                        if (key.equals("slingshotDistance")) {
                            c = 5;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.distanceToTriggerSync = (Integer) value;
                        break;
                    case 1:
                        this.enabled = (Boolean) value;
                        break;
                    case 2:
                        this.size = (Integer) value;
                        break;
                    case 3:
                        this.color = (String) value;
                        break;
                    case 4:
                        this.backgroundColor = (String) value;
                        break;
                    case 5:
                        this.slingshotDistance = (Integer) value;
                        break;
                }
            }
        }
        return this;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("enabled", this.enabled);
        hashMap.put("color", this.color);
        hashMap.put(TtmlNode.ATTR_TTS_BACKGROUND_COLOR, this.backgroundColor);
        hashMap.put("distanceToTriggerSync", this.distanceToTriggerSync);
        hashMap.put("slingshotDistance", this.slingshotDistance);
        hashMap.put("size", this.size);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(PullToRefreshLayout pullToRefreshLayout) {
        return toMap();
    }
}
