package co.hyperverge.hypersnapsdk.service.qr;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.objects.AadhaarQRResponse;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.gson.Gson;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AadhaarQRParser {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.qr.AadhaarQRParser";

    public AadhaarQRResponse parseAadhaarQR(String str) throws JSONException, IOException {
        HVLogUtils.d(TAG, "parseAadhaarQR() called with: rawString = [" + str + "]");
        Gson gson = new Gson();
        if (str.matches("\\d*")) {
            return (AadhaarQRResponse) gson.fromJson(Utils.secureQRDataToJSON(str), AadhaarQRResponse.class);
        }
        return ((AadhaarQrData) gson.fromJson(Utils.XMLToJSON(str), AadhaarQrData.class)).aadhaarQRResponse;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JSONObject parseAadhaarQRData(String str) {
        JSONObject jSONObject;
        Exception e;
        HVLogUtils.d(TAG, "parseAadhaarQRData() called with: xmlString = [" + str + "]");
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2 = new QRDataParser(new JSONObject(Utils.XMLToJSON(str))).getFixedKeyMap();
        } catch (Exception e2) {
            jSONObject = jSONObject2;
            e = e2;
        }
        if (jSONObject2 == null) {
            jSONObject = new JSONObject();
            try {
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logQRParseFailed();
                }
            } catch (Exception e3) {
                e = e3;
                String str2 = TAG;
                HVLogUtils.e(str2, "parseAadhaarQRData(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(str2, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
                jSONObject2 = jSONObject;
                if (jSONObject2 != null) {
                }
                return jSONObject2;
            }
            jSONObject2 = jSONObject;
        }
        if (jSONObject2 != null) {
            try {
                jSONObject2.put("value", str);
            } catch (JSONException e4) {
                String str3 = TAG;
                HVLogUtils.e(str3, "parseAadhaarQRData(): exception = [" + Utils.getErrorMessage(e4) + "]", e4);
                Log.e(str3, Utils.getErrorMessage(e4));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e4);
                }
            }
        }
        return jSONObject2;
    }
}
