package co.hyperverge.hypersnapsdk.service.errortracking.sentry;

import android.content.Context;
import co.hyperverge.hypersnapsdk.service.errortracking.ErrorMonitoringService;
import co.hyperverge.hypersnapsdk.service.errortracking.ErrorTrackingData;

/* loaded from: classes2.dex */
public class SentryService implements ErrorMonitoringService {
    private static SentryService sentryService;
    private final ErrorTrackingData mErrorTrackingData;

    private void setAdditionalData() {
    }

    private void setUserDetails(String str) {
    }

    @Override // co.hyperverge.hypersnapsdk.service.errortracking.ErrorMonitoringService
    public void sendErrorMessage(Throwable th) {
    }

    private SentryService(Context context, ErrorTrackingData errorTrackingData) {
        this.mErrorTrackingData = errorTrackingData;
        setUserDetails(errorTrackingData.getAppId());
        setAdditionalData();
    }

    public static SentryService getInstance(Context context, ErrorTrackingData errorTrackingData) {
        if (sentryService == null) {
            sentryService = new SentryService(context, errorTrackingData);
        }
        return sentryService;
    }
}
