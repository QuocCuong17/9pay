package co.hyperverge.hypersnapsdk.utils;

import android.text.Spanned;
import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class TextConfigUtils {
    private static final String TAG = "co.hyperverge.hypersnapsdk.utils.TextConfigUtils";

    public static Spanned getText(HVJSONObject hVJSONObject, String str, String str2) {
        return getText(hVJSONObject, str, str2, null);
    }

    public static Spanned getText(HVJSONObject hVJSONObject, String str, String str2, String str3) {
        String string;
        if (hVJSONObject != null) {
            try {
                if (hVJSONObject.hasAndNotEmpty(str)) {
                    string = hVJSONObject.getString(str);
                } else if (hVJSONObject.hasAndNotEmpty(str2)) {
                    string = hVJSONObject.getString(str2);
                }
                str3 = string;
            } catch (JSONException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        if (StringUtils.isEmptyOrNull(str3)) {
            return null;
        }
        return StringUtils.getHTML(str3);
    }
}
