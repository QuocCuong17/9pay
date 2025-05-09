package com.pichillilorenzo.flutter_inappwebview_android.types;

import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewFeature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class WebMessageCompatExt {
    private Object data;
    private List<WebMessagePortCompatExt> ports;
    private int type;

    public WebMessageCompatExt(Object obj, int i, List<WebMessagePortCompatExt> list) {
        this.data = obj;
        this.type = i;
        this.ports = list;
    }

    public static WebMessageCompatExt fromMapWebMessageCompat(WebMessageCompat webMessageCompat) {
        Object data;
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_ARRAY_BUFFER") && webMessageCompat.getType() == 1) {
            data = webMessageCompat.getArrayBuffer();
        } else {
            data = webMessageCompat.getData();
        }
        return new WebMessageCompatExt(data, webMessageCompat.getType(), null);
    }

    public static WebMessageCompatExt fromMap(Map<String, Object> map) {
        ArrayList arrayList = null;
        if (map == null) {
            return null;
        }
        Object obj = map.get("data");
        Integer num = (Integer) map.get("type");
        List list = (List) map.get("ports");
        if (list != null && !list.isEmpty()) {
            arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(WebMessagePortCompatExt.fromMap((Map) it.next()));
            }
        }
        return new WebMessageCompatExt(obj, num.intValue(), arrayList);
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("data", this.data);
        hashMap.put("type", Integer.valueOf(this.type));
        return hashMap;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public List<WebMessagePortCompatExt> getPorts() {
        return this.ports;
    }

    public void setPorts(List<WebMessagePortCompatExt> list) {
        this.ports = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebMessageCompatExt webMessageCompatExt = (WebMessageCompatExt) obj;
        if (this.type == webMessageCompatExt.type && Objects.equals(this.data, webMessageCompatExt.data)) {
            return Objects.equals(this.ports, webMessageCompatExt.ports);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.data;
        int hashCode = (((obj != null ? obj.hashCode() : 0) * 31) + this.type) * 31;
        List<WebMessagePortCompatExt> list = this.ports;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "WebMessageCompatExt{data=" + this.data + ", type=" + this.type + ", ports=" + this.ports + '}';
    }
}
