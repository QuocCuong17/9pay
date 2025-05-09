package co.hyperverge.hyperkyc.data.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSONConfigStore.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b\u0010J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b\u0014J-\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/JSONConfigStore;", "", "workflowConfig", "", "textConfig", "uiConfig", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTextConfig$hyperkyc_release", "()Ljava/lang/String;", "setTextConfig$hyperkyc_release", "(Ljava/lang/String;)V", "getUiConfig$hyperkyc_release", "setUiConfig$hyperkyc_release", "getWorkflowConfig$hyperkyc_release", "setWorkflowConfig$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class JSONConfigStore {
    private String textConfig;
    private String uiConfig;
    private String workflowConfig;

    public JSONConfigStore() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ JSONConfigStore copy$default(JSONConfigStore jSONConfigStore, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jSONConfigStore.workflowConfig;
        }
        if ((i & 2) != 0) {
            str2 = jSONConfigStore.textConfig;
        }
        if ((i & 4) != 0) {
            str3 = jSONConfigStore.uiConfig;
        }
        return jSONConfigStore.copy(str, str2, str3);
    }

    /* renamed from: component1$hyperkyc_release, reason: from getter */
    public final String getWorkflowConfig() {
        return this.workflowConfig;
    }

    /* renamed from: component2$hyperkyc_release, reason: from getter */
    public final String getTextConfig() {
        return this.textConfig;
    }

    /* renamed from: component3$hyperkyc_release, reason: from getter */
    public final String getUiConfig() {
        return this.uiConfig;
    }

    public final JSONConfigStore copy(String workflowConfig, String textConfig, String uiConfig) {
        return new JSONConfigStore(workflowConfig, textConfig, uiConfig);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JSONConfigStore)) {
            return false;
        }
        JSONConfigStore jSONConfigStore = (JSONConfigStore) other;
        return Intrinsics.areEqual(this.workflowConfig, jSONConfigStore.workflowConfig) && Intrinsics.areEqual(this.textConfig, jSONConfigStore.textConfig) && Intrinsics.areEqual(this.uiConfig, jSONConfigStore.uiConfig);
    }

    public int hashCode() {
        String str = this.workflowConfig;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.textConfig;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.uiConfig;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "JSONConfigStore(workflowConfig=" + this.workflowConfig + ", textConfig=" + this.textConfig + ", uiConfig=" + this.uiConfig + ')';
    }

    public JSONConfigStore(String str, String str2, String str3) {
        this.workflowConfig = str;
        this.textConfig = str2;
        this.uiConfig = str3;
    }

    public /* synthetic */ JSONConfigStore(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getWorkflowConfig$hyperkyc_release() {
        return this.workflowConfig;
    }

    public final void setWorkflowConfig$hyperkyc_release(String str) {
        this.workflowConfig = str;
    }

    public final String getTextConfig$hyperkyc_release() {
        return this.textConfig;
    }

    public final void setTextConfig$hyperkyc_release(String str) {
        this.textConfig = str;
    }

    public final String getUiConfig$hyperkyc_release() {
        return this.uiConfig;
    }

    public final void setUiConfig$hyperkyc_release(String str) {
        this.uiConfig = str;
    }
}
