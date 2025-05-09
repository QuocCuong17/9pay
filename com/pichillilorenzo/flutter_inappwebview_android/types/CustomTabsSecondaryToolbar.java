package com.pichillilorenzo.flutter_inappwebview_android.types;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class CustomTabsSecondaryToolbar {
    private List<AndroidResource> clickableIDs;
    private AndroidResource layout;

    public CustomTabsSecondaryToolbar(AndroidResource androidResource, List<AndroidResource> list) {
        this.clickableIDs = new ArrayList();
        this.layout = androidResource;
        this.clickableIDs = list;
    }

    public static CustomTabsSecondaryToolbar fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        AndroidResource fromMap = AndroidResource.fromMap((Map) map.get(TtmlNode.TAG_LAYOUT));
        ArrayList arrayList = new ArrayList();
        List list = (List) map.get("clickableIDs");
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AndroidResource fromMap2 = AndroidResource.fromMap((Map) ((Map) it.next()).get("id"));
                if (fromMap2 != null) {
                    arrayList.add(fromMap2);
                }
            }
        }
        return new CustomTabsSecondaryToolbar(fromMap, arrayList);
    }

    public AndroidResource getLayout() {
        return this.layout;
    }

    public void setLayout(AndroidResource androidResource) {
        this.layout = androidResource;
    }

    public List<AndroidResource> getClickableIDs() {
        return this.clickableIDs;
    }

    public void setClickableIDs(List<AndroidResource> list) {
        this.clickableIDs = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomTabsSecondaryToolbar customTabsSecondaryToolbar = (CustomTabsSecondaryToolbar) obj;
        if (this.layout.equals(customTabsSecondaryToolbar.layout)) {
            return this.clickableIDs.equals(customTabsSecondaryToolbar.clickableIDs);
        }
        return false;
    }

    public int hashCode() {
        return (this.layout.hashCode() * 31) + this.clickableIDs.hashCode();
    }

    public String toString() {
        return "CustomTabsSecondaryToolbar{layout=" + this.layout + ", clickableIDs=" + this.clickableIDs + '}';
    }
}
