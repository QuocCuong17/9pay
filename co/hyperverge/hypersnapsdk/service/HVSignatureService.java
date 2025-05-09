package co.hyperverge.hypersnapsdk.service;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVSignatureService {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.HVSignatureService";

    public static String sortJSONKeysAlphabetically(TreeMap<String, Object> treeMap) {
        HVLogUtils.d(TAG, "sortJSONKeysAlphabetically() called with: map = [" + treeMap + "]");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("{");
        String sb2 = sb.toString();
        try {
            for (String str : treeMap.keySet()) {
                if (!sb2.equals("{")) {
                    sb2 = sb2 + ",";
                }
                Object obj = treeMap.get(str);
                if (obj instanceof JSONObject) {
                    obj = sortJSONKeysAlphabetically(convertJSONObjToMap((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    obj = sortJSONArray(obj);
                } else if (obj instanceof String) {
                    obj = "\"" + getParsedString((String) obj) + "\"";
                }
                sb2 = sb2 + "\"" + str + "\":" + obj;
            }
            return sb2 + "}";
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            return null;
        }
    }

    private static Object sortJSONArray(Object obj) {
        HVLogUtils.d(TAG, "sortJSONArray() called with: value = [" + obj + "]");
        try {
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList();
            int i = 0;
            boolean z = false;
            while (i < jSONArray.length()) {
                Object obj2 = jSONArray.get(i);
                if (!(obj2 instanceof JSONObject)) {
                    return obj;
                }
                arrayList.add(sortJSONKeysAlphabetically(convertJSONObjToMap((JSONObject) obj2)));
                i++;
                z = true;
            }
            if (!z) {
                return obj;
            }
            String str = "[";
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                str = str.concat(String.valueOf(arrayList.get(i2)));
                if (i2 < arrayList.size() - 1) {
                    String str2 = str;
                    str = str.concat(",");
                }
            }
            return str.concat("]");
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return obj;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return obj;
        }
    }

    public static TreeMap<String, Object> convertJSONObjToMap(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "convertJSONObjToMap() called with: jObject = [" + jSONObject + "]");
        TreeMap<String, Object> treeMap = new TreeMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = null;
            try {
                obj = jSONObject.get(next);
            } catch (JSONException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            treeMap.put(next, obj);
        }
        return treeMap;
    }

    private static String getParsedString(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"").replace(IOUtils.LINE_SEPARATOR_UNIX, "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\b", "\\b").replace("\f", "\\f");
    }
}
