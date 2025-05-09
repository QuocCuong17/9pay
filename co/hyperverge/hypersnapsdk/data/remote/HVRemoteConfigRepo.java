package co.hyperverge.hypersnapsdk.data.remote;

import android.util.Log;
import co.hyperverge.hypersnapsdk.data.DefaultRemoteConfig;
import co.hyperverge.hypersnapsdk.data.RemoteConfig;
import co.hyperverge.hypersnapsdk.data.models.FeatureConfigResponse;
import co.hyperverge.hypersnapsdk.helpers.HVFeatureConfigHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.Cache;
import org.apache.commons.io.IOUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class HVRemoteConfigRepo {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo";
    private static HVRemoteConfigRepo instance;
    private final Cache cacheDir;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /* loaded from: classes2.dex */
    public interface HVCallback {
        void onError(HVError hVError);

        void onSuccess();
    }

    private HVRemoteConfigRepo(Cache cache) {
        this.cacheDir = cache;
    }

    public static HVRemoteConfigRepo getInstance(Cache cache) {
        if (instance == null) {
            instance = new HVRemoteConfigRepo(cache);
        }
        return instance;
    }

    public void getFeatureConfigs(String str, final HVCallback hVCallback) {
        String str2 = TAG;
        HVLogUtils.d(str2, "getFeatureConfigs() called with: sdkVersion = [" + str + "], callback = [" + hVCallback + "]");
        String s3FeatureConfigBaseUrl = SDKInternalConfig.getInstance().getS3FeatureConfigBaseUrl();
        final String str3 = s3FeatureConfigBaseUrl + "sdkV2.json";
        final String str4 = s3FeatureConfigBaseUrl + String.format("%s.json", str);
        HVLogUtils.d(str2, "getFeatureConfigs(): sdkJsonUrl: " + str3);
        HVLogUtils.d(str2, "getFeatureConfigs(): versionSpecificJsonUrl: " + str4);
        this.executorService.submit(new Runnable() { // from class: co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HVRemoteConfigRepo.this.m514x64d6237a(str3, str4, hVCallback);
            }
        });
    }

    /* renamed from: lambda$getFeatureConfigs$0$co-hyperverge-hypersnapsdk-data-remote-HVRemoteConfigRepo, reason: not valid java name */
    public /* synthetic */ void m514x64d6237a(String str, String str2, HVCallback hVCallback) {
        try {
            RemoteConfigApiInterface remoteConfigService = ApiClient.getRemoteConfigService(this.cacheDir);
            Response<FeatureConfigResponse> execute = remoteConfigService.getFeatureRemoteConfig(str).execute();
            Response<FeatureConfigResponse> execute2 = remoteConfigService.getFeatureRemoteConfig(str2).execute();
            if (execute.isSuccessful()) {
                SDKInternalConfig.getInstance().setFeatureConfigMap(HVFeatureConfigHelper.extractFeatureMap(execute.body().getFeatures(), execute2.isSuccessful() ? execute2.body().getFeatures() : new ArrayList<>()));
                hVCallback.onSuccess();
                return;
            }
            hVCallback.onError(new HVError(12, logServerError(execute) + IOUtils.LINE_SEPARATOR_UNIX + logServerError(execute2)));
        } catch (Exception e) {
            String str3 = TAG;
            HVLogUtils.e(str3, "getFeatureConfigs(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str3, "getFeatureConfigs: ", e);
            hVCallback.onError(new HVError(12, Utils.getErrorMessage(e)));
        }
    }

    private <T> String logServerError(Response<T> response) {
        String string;
        if (response.isSuccessful()) {
            return "";
        }
        if (response.errorBody() != null) {
            try {
                string = response.errorBody().string();
            } catch (IOException e) {
                HVLogUtils.e(TAG, "logServerError(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(getClass().getCanonicalName(), Utils.getErrorMessage(e));
            }
            logAPICallError(false, string, response.code());
            return string;
        }
        string = "Server Error";
        logAPICallError(false, string, response.code());
        return string;
    }

    public void getDefaultRemoteConfig(final HVCallback hVCallback) {
        HVLogUtils.d(TAG, "getDefaultRemoteConfig() called with: callback = [" + hVCallback + "]");
        ApiClient.getRemoteConfigService(this.cacheDir).getDefaultRemoteConfig("https://hv-central-config.s3.ap-south-1.amazonaws.com/sdk-client-config/hypersnapsdk/v1/default.json").enqueue(new Callback<DefaultRemoteConfig>() { // from class: co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.1
            @Override // retrofit2.Callback
            public void onResponse(Call<DefaultRemoteConfig> call, Response<DefaultRemoteConfig> response) {
                HVLogUtils.d(HVRemoteConfigRepo.TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    SDKInternalConfig.getInstance().setDefaultRemoteConfig(response.body());
                    hVCallback.onSuccess();
                } else {
                    SDKInternalConfig.getInstance().setDefaultRemoteConfig(new DefaultRemoteConfig());
                    hVCallback.onError(new HVError(response.code(), "Could not fetch default remote configs"));
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<DefaultRemoteConfig> call, Throwable th) {
                HVLogUtils.d(HVRemoteConfigRepo.TAG, "onFailure() called with: call = [" + call + "], t = [" + th + "]");
                SDKInternalConfig.getInstance().setDefaultRemoteConfig(new DefaultRemoteConfig());
                hVCallback.onError(new HVError(12, "Could not fetch default remote configs"));
            }
        });
    }

    public void getRemoteConfig(String str, final boolean z, final HVCallback hVCallback) {
        HVLogUtils.d(TAG, "getRemoteConfig() called with: appId = [" + str + "], isForCheckBranding = [" + z + "], callback = [" + hVCallback + "]");
        StringBuilder sb = new StringBuilder();
        sb.append("https://s3-ap-south-1.amazonaws.com/hv-central-config/sdk-client-config/hypersnapsdk/v1/");
        sb.append(str);
        sb.append(".json");
        ApiClient.getRemoteConfigService(this.cacheDir).getRemoteConfig(sb.toString()).enqueue(new Callback<RemoteConfig>() { // from class: co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.2
            @Override // retrofit2.Callback
            public void onResponse(Call<RemoteConfig> call, Response<RemoteConfig> response) {
                String string;
                HVLogUtils.d(HVRemoteConfigRepo.TAG, "getRemoteConfig(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    HVRemoteConfigRepo.this.logAPICallSuccess(z);
                    SDKInternalConfig.getInstance().setRemoteConfig(response.body());
                    hVCallback.onSuccess();
                    return;
                }
                if (response.errorBody() != null) {
                    try {
                        string = response.errorBody().string();
                    } catch (IOException e) {
                        Log.e(getClass().getCanonicalName(), Utils.getErrorMessage(e));
                    }
                    HVRemoteConfigRepo.this.logAPICallError(z, string, response.code());
                    SDKInternalConfig.getInstance().createDefaultMixpanelConfigs();
                    hVCallback.onError(new HVError(response.code(), "Could not get remote configs"));
                }
                string = "Server Error";
                HVRemoteConfigRepo.this.logAPICallError(z, string, response.code());
                SDKInternalConfig.getInstance().createDefaultMixpanelConfigs();
                hVCallback.onError(new HVError(response.code(), "Could not get remote configs"));
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<RemoteConfig> call, Throwable th) {
                HVLogUtils.d(HVRemoteConfigRepo.TAG, "getRemoteConfig(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                if (z) {
                    HVRemoteConfigRepo.this.logAPICallError(true, Utils.getErrorMessage(th), 12);
                }
                SDKInternalConfig.getInstance().setRemoteConfig(new RemoteConfig());
                hVCallback.onError(new HVError(12, "Could not get remote configs"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAPICallSuccess(boolean z) {
        HVLogUtils.d(TAG, "logAPICallSuccess() called with: shouldLogForBrandingCheck = [" + z + "]");
        if (z) {
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCheckBrandingAPISuccess();
            return;
        }
        if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
            return;
        }
        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logRemoteConfigAPISuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAPICallError(boolean z, String str, int i) {
        HVLogUtils.d(TAG, "logAPICallError() called with: shouldLogForBrandingCheck = [" + z + "], errorMessage = [" + str + "], errorCode = [" + i + "]");
        if (z) {
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCheckBrandingAPIError(str, i);
            return;
        }
        if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
            return;
        }
        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logRemoteConfigAPIError(str, i);
    }
}
