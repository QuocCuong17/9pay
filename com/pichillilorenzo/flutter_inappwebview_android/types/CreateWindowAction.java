package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class CreateWindowAction extends NavigationAction {
    boolean isDialog;
    int windowId;

    public CreateWindowAction(URLRequest uRLRequest, boolean z, boolean z2, boolean z3, int i, boolean z4) {
        super(uRLRequest, z, z2, z3);
        this.windowId = i;
        this.isDialog = z4;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("windowId", Integer.valueOf(this.windowId));
        map.put("isDialog", Boolean.valueOf(this.isDialog));
        map.put("windowFeatures", null);
        return map;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public void setWindowId(int i) {
        this.windowId = i;
    }

    public boolean isDialog() {
        return this.isDialog;
    }

    public void setDialog(boolean z) {
        this.isDialog = z;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        CreateWindowAction createWindowAction = (CreateWindowAction) obj;
        return this.windowId == createWindowAction.windowId && this.isDialog == createWindowAction.isDialog;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction
    public int hashCode() {
        return (((super.hashCode() * 31) + this.windowId) * 31) + (this.isDialog ? 1 : 0);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction
    public String toString() {
        return "CreateWindowAction{windowId=" + this.windowId + ", isDialog=" + this.isDialog + ", request=" + this.request + ", isForMainFrame=" + this.isForMainFrame + ", hasGesture=" + this.hasGesture + ", isRedirect=" + this.isRedirect + '}';
    }
}
