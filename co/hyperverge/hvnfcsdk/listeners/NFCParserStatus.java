package co.hyperverge.hvnfcsdk.listeners;

import co.hyperverge.hvnfcsdk.model.HVNFCError;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface NFCParserStatus {
    void onCardDisconnection();

    void onIDDetection();

    void onScanResult(JSONObject jSONObject, String str, HVNFCError hVNFCError);
}
