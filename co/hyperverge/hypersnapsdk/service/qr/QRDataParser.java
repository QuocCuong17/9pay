package co.hyperverge.hypersnapsdk.service.qr;

import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Geo;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class QRDataParser {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.qr.QRDataParser";
    JSONObject qrJSONObject;

    public QRDataParser(JSONObject jSONObject) {
        this.qrJSONObject = jSONObject;
    }

    public QRDataParser() {
    }

    public JSONObject getFixedKeyMap() {
        HVLogUtils.d(TAG, "getFixedKeyMap() called");
        JSONObject jSONObject = new JSONObject();
        LinkedHashMap<String, String> keyMap = getKeyMap();
        if (keyMap == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(MASTER_SET_OCR_KEYS());
        for (String str : keyMap.keySet()) {
            if (this.qrJSONObject.has(str)) {
                try {
                    jSONObject.put(keyMap.get(str), this.qrJSONObject.getString(str));
                } catch (Exception e) {
                    Log.e(TAG, Utils.getErrorMessage(e));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                }
            } else {
                try {
                    jSONObject.put(keyMap.get(str), "");
                } catch (Exception e2) {
                    Log.e(TAG, Utils.getErrorMessage(e2));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                    }
                }
            }
            arrayList.remove(keyMap.get(str));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                jSONObject.put((String) it.next(), "");
            } catch (Exception e3) {
                Log.e(TAG, Utils.getErrorMessage(e3));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
                }
            }
        }
        return jSONObject;
    }

    public List<String> MASTER_SET_OCR_KEYS() {
        HVLogUtils.d(TAG, "MASTER_SET_OCR_KEYS() called");
        ArrayList arrayList = new ArrayList();
        arrayList.add("name");
        arrayList.add("signature");
        arrayList.add("full_address");
        arrayList.add("gender");
        arrayList.add("dob");
        arrayList.add("aadhaar");
        arrayList.add("district");
        arrayList.add("care_of");
        arrayList.add("house_number");
        arrayList.add("landmark");
        arrayList.add("locality");
        arrayList.add("pin");
        arrayList.add("po");
        arrayList.add("state");
        arrayList.add("street");
        arrayList.add("subdist");
        arrayList.add(Geo.JsonKeys.CITY);
        arrayList.add("yob");
        arrayList.add("gname");
        return arrayList;
    }

    public LinkedHashMap<String, String> QDB_FIELD_KEY_TO_NAME_MAP() {
        HVLogUtils.d(TAG, "QDB_FIELD_KEY_TO_NAME_MAP() called");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("n", "name");
        linkedHashMap.put("u", "aadhaar");
        linkedHashMap.put("g", "gender");
        linkedHashMap.put("d", "dob");
        linkedHashMap.put(CmcdData.Factory.STREAMING_FORMAT_SS, "signature");
        linkedHashMap.put("a", "full_address");
        linkedHashMap.put(ViewHierarchyNode.JsonKeys.X, "unknown");
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> PRINTBARCODE_FIELD_KEY_TO_NAME_MAP() {
        HVLogUtils.d(TAG, "PRINTBARCODE_FIELD_KEY_TO_NAME_MAP() called");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name", "name");
        linkedHashMap.put("gender", "gender");
        linkedHashMap.put("dob", "dob");
        linkedHashMap.put("uid", "aadhaar");
        linkedHashMap.put(SentryBaseEvent.JsonKeys.DIST, "district");
        linkedHashMap.put("co", "care_of");
        linkedHashMap.put("house", "house_number");
        linkedHashMap.put("lm", "landmark");
        linkedHashMap.put("loc", "locality");
        linkedHashMap.put("pc", "pin");
        linkedHashMap.put("po", "po");
        linkedHashMap.put("state", "state");
        linkedHashMap.put("street", "street");
        linkedHashMap.put("subdist", "subdist");
        linkedHashMap.put("vtc", Geo.JsonKeys.CITY);
        linkedHashMap.put("yob", "yob");
        linkedHashMap.put("gname", "gname");
        return linkedHashMap;
    }

    public LinkedHashMap<String, String> getKeyMap() {
        HVLogUtils.d(TAG, "getKeyMap() called");
        if (this.qrJSONObject.has("QDB")) {
            try {
                this.qrJSONObject = this.qrJSONObject.getJSONObject("QDB");
            } catch (JSONException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            return QDB_FIELD_KEY_TO_NAME_MAP();
        }
        if (this.qrJSONObject.has("PrintLetterBarcodeData")) {
            try {
                this.qrJSONObject = this.qrJSONObject.getJSONObject("PrintLetterBarcodeData");
            } catch (JSONException e2) {
                String str = TAG;
                HVLogUtils.e(str, "getKeyMap(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
                Log.e(str, Utils.getErrorMessage(e2));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                }
            }
            return PRINTBARCODE_FIELD_KEY_TO_NAME_MAP();
        }
        if (!this.qrJSONObject.has("QDA")) {
            return null;
        }
        try {
            this.qrJSONObject = this.qrJSONObject.getJSONObject("QDA");
        } catch (JSONException e3) {
            Log.e(TAG, Utils.getErrorMessage(e3));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
            }
        }
        return QDB_FIELD_KEY_TO_NAME_MAP();
    }
}
