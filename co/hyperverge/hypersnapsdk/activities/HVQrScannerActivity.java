package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.util.Log;
import co.hyperverge.hvqrmodule.objects.HVQRConfig;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.listeners.QRCodeCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.QRCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.QRScannerCompletionHandler;
import co.hyperverge.hypersnapsdk.objects.AadhaarQRResponse;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.service.qr.AadhaarQRParser;
import co.hyperverge.hypersnapsdk.utils.FileExtensionsKt;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.gson.Gson;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVQrScannerActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "co.hyperverge.hypersnapsdk.activities.HVQrScannerActivity";

    protected static File getAppFilesDir(Context context) {
        try {
            return context.getApplicationContext().getFilesDir();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "getAppFilesDir(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            return null;
        }
    }

    public static void start(final Context context, final HVQRConfig hVQRConfig, QRScannerCompletionHandler qRScannerCompletionHandler) {
        String str = TAG;
        HVLogUtils.d(str, "start() called with: context = [" + context + "], hvqrConfig = [" + hVQRConfig + "], handler = [" + qRScannerCompletionHandler + "]");
        if (qRScannerCompletionHandler == null) {
            return;
        }
        CallbackProvider.get().setCallback(qRScannerCompletionHandler);
        HyperSnapSDK hyperSnapSDK = HyperSnapSDK.getInstance();
        HyperSnapSDKConfig hyperSnapSDKConfig = hyperSnapSDK.getHyperSnapSDKConfig();
        if (!hyperSnapSDK.isHyperSnapSDKInitialised() || ((hyperSnapSDKConfig.getAppId() != null && hyperSnapSDKConfig.getAppId().isEmpty()) || (hyperSnapSDKConfig.getAppKey() != null && hyperSnapSDKConfig.getAppKey().isEmpty()))) {
            HVError hVError = new HVError(11, context.getResources().getString(R.string.initialised_error));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logHyperSnapSDKInitError(hVError.getErrorMessage());
            }
            HVLogUtils.d(str, "start: error = [" + hVError + "]");
            callCompletionHandler(hVQRConfig != null ? hVQRConfig.getModuleId() : null, getAppFilesDir(context), CallbackProvider.get().injectQRScannerCallback(), hVError, null);
            return;
        }
        try {
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logQRScannerLaunched();
            }
            HVQRScannerActivityInternal.start(context, hVQRConfig, new QRCodeCompletionHandler() { // from class: co.hyperverge.hypersnapsdk.activities.HVQrScannerActivity.1
                /* JADX WARN: Removed duplicated region for block: B:10:0x0123  */
                @Override // co.hyperverge.hypersnapsdk.listeners.QRCompletionHandler
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void onResult(HVError hVError2, JSONObject jSONObject) {
                    HVError hVError3;
                    JSONObject jSONObject2;
                    AadhaarQRResponse parseAadhaarQR;
                    HVLogUtils.d(HVQrScannerActivity.TAG, "QRCodeCompletionHandler.onResult() called with: hvError = [" + hVError2 + "], result = [" + jSONObject + "]");
                    if (hVError2 != null) {
                        hVError3 = new HVError(hVError2.getErrorCode(), hVError2.getErrorMessage());
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logQRScannerFailed(hVError3);
                        }
                    } else {
                        hVError3 = null;
                    }
                    if (jSONObject != null) {
                        try {
                            jSONObject2 = new JSONObject();
                        } catch (Exception e) {
                            e = e;
                            jSONObject2 = null;
                        }
                        try {
                            String string = jSONObject.getString("qr-code");
                            if (jSONObject.has("status")) {
                                jSONObject2.put("status", jSONObject.getString("status"));
                            }
                            jSONObject2.put("qr", string);
                            if (!string.isEmpty() && (parseAadhaarQR = new AadhaarQRParser().parseAadhaarQR(string)) != null) {
                                String aadhaar = parseAadhaarQR.getAadhaar();
                                HVQRConfig hVQRConfig2 = hVQRConfig;
                                if (hVQRConfig2 != null && hVQRConfig2.shouldMaskAadhaar() && aadhaar.length() <= 12) {
                                    parseAadhaarQR.setAadhaar("XXXXXXXX" + aadhaar.substring(aadhaar.length() - 4));
                                    Log.d(HVQrScannerActivity.TAG, "onResult: aadhaar: " + parseAadhaarQR.getAadhaar());
                                }
                                jSONObject2.put("qr", new JSONObject(new Gson().toJson(parseAadhaarQR)));
                            }
                        } catch (Exception e2) {
                            e = e2;
                            HVLogUtils.e(HVQrScannerActivity.TAG, "QRCodeCompletionHandler.onResult(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                            Log.e(HVQrScannerActivity.TAG, Utils.getErrorMessage(e));
                            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
                            HVQRConfig hVQRConfig3 = hVQRConfig;
                            HVQrScannerActivity.callCompletionHandler(hVQRConfig3 != null ? hVQRConfig3.getModuleId() : null, HVQrScannerActivity.getAppFilesDir(context), CallbackProvider.get().injectQRScannerCallback(), hVError3, jSONObject2);
                        }
                    } else {
                        jSONObject2 = null;
                    }
                    HVQRConfig hVQRConfig32 = hVQRConfig;
                    HVQrScannerActivity.callCompletionHandler(hVQRConfig32 != null ? hVQRConfig32.getModuleId() : null, HVQrScannerActivity.getAppFilesDir(context), CallbackProvider.get().injectQRScannerCallback(), hVError3, jSONObject2);
                }
            });
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "start(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
        } catch (NoClassDefFoundError e2) {
            String str3 = TAG;
            HVLogUtils.e(str3, "start(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            Log.e(str3, Utils.getErrorMessage(e2));
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e2);
            callCompletionHandler(hVQRConfig != null ? hVQRConfig.getModuleId() : null, getAppFilesDir(context), CallbackProvider.get().injectQRScannerCallback(), new HVError(32, "QR Scanner module is not included. Kindly include the module to use it."), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callCompletionHandler(String str, File file, QRCompletionHandler qRCompletionHandler, HVError hVError, JSONObject jSONObject) {
        String str2 = TAG;
        HVLogUtils.d(str2, "callCompletionHandler() called with: handler = [" + qRCompletionHandler + "], hvError = [" + hVError + "], result = [" + jSONObject + "]");
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
            SDKInternalConfig.getInstance().getHvSensorBiometrics().stopSensorBiometrics();
        }
        if (file != null) {
            FileExtensionsKt.saveSDKResultToFile(file, str, hVError, null, jSONObject);
        } else {
            HVLogUtils.e(str2, "callCompletionHandler(), unable to save SDK result to file, context is null");
        }
        qRCompletionHandler.onResult(hVError, jSONObject);
    }
}
