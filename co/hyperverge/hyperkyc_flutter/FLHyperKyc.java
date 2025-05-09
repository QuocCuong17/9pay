package co.hyperverge.hyperkyc_flutter;

import android.content.Context;
import co.hyperverge.hyperkyc.HyperKyc;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import java.util.Map;

/* loaded from: classes2.dex */
class FLHyperKyc {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String ACCESS_TOKEN = "accessToken";
    static final String APP_ID = "appId";
    static final String APP_KEY = "appKey";
    static final String DEFAULT_LANG_CODE = "defaultLangCode";
    static final String INPUTS = "inputs";
    static final String METADATA = "metadata";
    static final String TAG = "co.hyperverge.hyperkyc_flutter.FLHyperKyc";
    static final String TRANSACTION_ID = "transactionId";
    static final String UNIQUE_ID = "uniqueId";
    static final String USE_LOCATION = "useLocation";
    static final String WORKFLOW_ID = "workflowId";

    public void prefetch(Context context, Map<String, Object> map) {
        String str = map.containsKey("appId") ? (String) map.get("appId") : null;
        String str2 = map.containsKey("accessToken") ? (String) map.get("accessToken") : null;
        String str3 = map.containsKey("workflowId") ? (String) map.get("workflowId") : null;
        if (str2 == null) {
            HyperKyc.prefetch(context, str, str3);
        }
    }

    public String createUniqueId() {
        return HyperKyc.createUniqueId();
    }

    public HyperKycConfig getHyperKycConfigFromMap(Map<String, Object> map) {
        String str = map.containsKey("appId") ? (String) map.get("appId") : null;
        String str2 = map.containsKey("appKey") ? (String) map.get("appKey") : null;
        String str3 = map.containsKey("accessToken") ? (String) map.get("accessToken") : null;
        String str4 = map.containsKey("workflowId") ? (String) map.get("workflowId") : null;
        String str5 = map.containsKey("transactionId") ? (String) map.get("transactionId") : null;
        Map<String, ? extends Object> map2 = map.containsKey("inputs") ? (Map) map.get("inputs") : null;
        String str6 = map.containsKey("defaultLangCode") ? (String) map.get("defaultLangCode") : null;
        Boolean bool = map.containsKey(USE_LOCATION) ? (Boolean) map.get(USE_LOCATION) : null;
        String str7 = map.containsKey("uniqueId") ? (String) map.get("uniqueId") : null;
        Map<String, String> map3 = map.containsKey("metadata") ? (Map) map.get("metadata") : null;
        if (str3 == null) {
            HyperKycConfig hyperKycConfig = new HyperKycConfig(str, str2, str4, str5);
            if (map2 != null) {
                hyperKycConfig.setInputs(map2);
            }
            if (str6 != null) {
                hyperKycConfig.setDefaultLangCode(str6);
            }
            if (bool != null) {
                hyperKycConfig.setUseLocation(bool.booleanValue());
            }
            if (str7 != null) {
                hyperKycConfig.setUniqueId(str7);
            }
            if (map3 != null) {
                hyperKycConfig.addMetadata(map3);
            }
            return hyperKycConfig;
        }
        HyperKycConfig hyperKycConfig2 = new HyperKycConfig(str3, str4, str5);
        if (map2 != null) {
            hyperKycConfig2.setInputs(map2);
        }
        if (str6 != null) {
            hyperKycConfig2.setDefaultLangCode(str6);
        }
        if (bool != null) {
            hyperKycConfig2.setUseLocation(bool.booleanValue());
        }
        if (str7 != null) {
            hyperKycConfig2.setUniqueId(str7);
        }
        if (map3 != null) {
            hyperKycConfig2.addMetadata(map3);
        }
        return hyperKycConfig2;
    }
}
