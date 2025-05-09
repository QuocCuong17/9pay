package co.hyperverge.hyperkyc.data.models.webviewform;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebViewFormTransactionData.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B?\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003\u0012 \u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u0003¢\u0006\u0002\u0010\u0006J\u0017\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003HÆ\u0003J#\u0010\u000e\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u0003HÆ\u0003JE\u0010\u000f\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00032\"\b\u0002\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R(\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR4\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0016"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/webviewform/WebViewFormTransactionData;", "", WorkflowModule.PREFIX_INPUTS, "", "", "moduleData", "(Ljava/util/Map;Ljava/util/Map;)V", "getInputs", "()Ljava/util/Map;", "setInputs", "(Ljava/util/Map;)V", "getModuleData", "setModuleData", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class WebViewFormTransactionData {
    private Map<String, ? extends Object> inputs;
    private Map<String, ? extends Map<String, ? extends Object>> moduleData;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WebViewFormTransactionData copy$default(WebViewFormTransactionData webViewFormTransactionData, Map map, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            map = webViewFormTransactionData.inputs;
        }
        if ((i & 2) != 0) {
            map2 = webViewFormTransactionData.moduleData;
        }
        return webViewFormTransactionData.copy(map, map2);
    }

    public final Map<String, Object> component1() {
        return this.inputs;
    }

    public final Map<String, Map<String, Object>> component2() {
        return this.moduleData;
    }

    public final WebViewFormTransactionData copy(Map<String, ? extends Object> inputs, Map<String, ? extends Map<String, ? extends Object>> moduleData) {
        Intrinsics.checkNotNullParameter(moduleData, "moduleData");
        return new WebViewFormTransactionData(inputs, moduleData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WebViewFormTransactionData)) {
            return false;
        }
        WebViewFormTransactionData webViewFormTransactionData = (WebViewFormTransactionData) other;
        return Intrinsics.areEqual(this.inputs, webViewFormTransactionData.inputs) && Intrinsics.areEqual(this.moduleData, webViewFormTransactionData.moduleData);
    }

    public int hashCode() {
        Map<String, ? extends Object> map = this.inputs;
        return ((map == null ? 0 : map.hashCode()) * 31) + this.moduleData.hashCode();
    }

    public String toString() {
        return "WebViewFormTransactionData(inputs=" + this.inputs + ", moduleData=" + this.moduleData + ')';
    }

    public WebViewFormTransactionData(Map<String, ? extends Object> map, Map<String, ? extends Map<String, ? extends Object>> moduleData) {
        Intrinsics.checkNotNullParameter(moduleData, "moduleData");
        this.inputs = map;
        this.moduleData = moduleData;
    }

    public /* synthetic */ WebViewFormTransactionData(Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : map, map2);
    }

    public final Map<String, Object> getInputs() {
        return this.inputs;
    }

    public final void setInputs(Map<String, ? extends Object> map) {
        this.inputs = map;
    }

    public final Map<String, Map<String, Object>> getModuleData() {
        return this.moduleData;
    }

    public final void setModuleData(Map<String, ? extends Map<String, ? extends Object>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.moduleData = map;
    }
}
