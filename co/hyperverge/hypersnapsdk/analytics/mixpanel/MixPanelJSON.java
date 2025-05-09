package co.hyperverge.hypersnapsdk.analytics.mixpanel;

import android.os.Build;
import android.util.Log;
import co.hyperverge.hypersnapsdk.BuildConfig;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes2.dex */
class MixPanelJSON extends JSONObject {
    private static final String TAG = "co.hyperverge.hypersnapsdk.analytics.mixpanel.MixPanelJSON";

    MixPanelJSON() {
        String str;
        try {
            put("App ID", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            put("App Package Name", SDKInternalConfig.getInstance().getAppName());
            put("SDK Version Number", BuildConfig.HYPERSNAP_VERSION_NAME);
            if (Build.VERSION.SDK_INT >= 21) {
                str = Build.SUPPORTED_ABIS[0];
            } else {
                str = Build.CPU_ABI;
            }
            put("ABI Architecture", str);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }
}
