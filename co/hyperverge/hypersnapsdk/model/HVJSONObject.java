package co.hyperverge.hypersnapsdk.model;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVJSONObject extends JSONObject implements Serializable {
    private static final String TAG = "co.hyperverge.hypersnapsdk.model.HVJSONObject";

    public HVJSONObject() {
    }

    public HVJSONObject(String str) throws JSONException {
        super(str);
    }

    public boolean hasAndNotEmpty(String str) {
        try {
            if (!has(str)) {
                return false;
            }
            if (get(str) instanceof String) {
                return !((String) get(str)).trim().isEmpty();
            }
            return true;
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return false;
        }
    }
}
