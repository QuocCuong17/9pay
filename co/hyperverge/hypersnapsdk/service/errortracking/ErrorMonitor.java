package co.hyperverge.hypersnapsdk.service.errortracking;

import android.content.Context;
import android.util.Log;
import co.hyperverge.hypersnapsdk.BuildConfig;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.service.errortracking.sentry.SentryService;
import co.hyperverge.hypersnapsdk.utils.Utils;

/* loaded from: classes2.dex */
public class ErrorMonitor implements ErrorMonitoringService {
    private final Context mContext;
    private final SentryService sentryService;

    public ErrorMonitor(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.sentryService = SentryService.getInstance(context, getErrorTrackingData(applicationContext));
    }

    private ErrorTrackingData getErrorTrackingData(Context context) {
        ErrorTrackingData errorTrackingData = new ErrorTrackingData();
        errorTrackingData.setAbi(Utils.getDeviceAbi());
        errorTrackingData.setAppId(HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
        errorTrackingData.setPackageName(context.getPackageName());
        errorTrackingData.setPackageName(BuildConfig.HYPERSNAP_VERSION_NAME);
        return errorTrackingData;
    }

    @Override // co.hyperverge.hypersnapsdk.service.errortracking.ErrorMonitoringService
    public void sendErrorMessage(Throwable th) {
        SentryService sentryService;
        try {
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
                SDKInternalConfig.getInstance().getHvSensorBiometrics().exitSensorBiometrics();
            }
        } catch (Exception e) {
            Log.e("HVErrorMonitor", e.getLocalizedMessage());
        }
        if (!isAppRelease(this.mContext) || (sentryService = this.sentryService) == null) {
            return;
        }
        sentryService.sendErrorMessage(th);
    }

    private boolean isAppRelease(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }
}
