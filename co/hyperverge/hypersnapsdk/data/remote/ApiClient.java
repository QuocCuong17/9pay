package co.hyperverge.hypersnapsdk.data.remote;

import android.os.Build;
import android.util.Log;
import androidx.media3.datasource.cache.CacheDataSink;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.network.MixPanelApiInterface;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes2.dex */
public class ApiClient {
    private static final String IND_DOCS_PATTERN = "ind.docs.hyperverge.co";
    private static final String IND_FACE_ID_PATTERN = "ind.faceid.hyperverge.co";
    private static final String SHA_1 = "sha256/hHWXbXBGT2chaVwYyxEyGqtPJX9H/dh5HbOAz5CmclM=";
    private static final String SHA_2 = "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=";
    private static final String SHA_3 = "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=";
    private static final String SHA_4 = "sha256/C3U1B8/WXNaje+K8wU4TRgV0htiLVH9gikN4+kwR+P4=";
    private static final String STAGING_API_PATTERN = "staging.api.hyperverge.co";
    private static final String TAG = "co.hyperverge.hypersnapsdk.data.remote.ApiClient";
    private static final String WILD_CARD_PATTERN = "*.hyperverge.co";
    private static AnalyticsApiInterface analyticsApiInterface;
    private static ApiInterface apiService;
    private static DocsApiInterface docsService;
    private static MixPanelApiInterface mixPanelApiService;
    private static RemoteConfigApiInterface remoteConfigApiInterfaceService;
    private static SensorDataInterface sensorDataInterface;
    private final long CACHE_SIZE = CacheDataSink.DEFAULT_FRAGMENT_SIZE;

    private ApiClient() {
    }

    public static ApiInterface getService() {
        HVLogUtils.d(TAG, "getService() called");
        if (apiService == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            configureTimeouts(builder);
            if (Build.VERSION.SDK_INT < 21) {
                try {
                    builder.sslSocketFactory(new TLSSocketFactory());
                } catch (Exception e) {
                    String str = TAG;
                    HVLogUtils.e(str, "getService(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                    Log.e(str, Utils.getErrorMessage(e));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                }
            }
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldEnableSSLPinning()) {
                builder.certificatePinner(new CertificatePinner.Builder().add(IND_FACE_ID_PATTERN, SHA_1).add(IND_FACE_ID_PATTERN, SHA_2).add(IND_FACE_ID_PATTERN, SHA_3).add(IND_DOCS_PATTERN, SHA_1).add(IND_DOCS_PATTERN, SHA_2).add(IND_DOCS_PATTERN, SHA_3).add(STAGING_API_PATTERN, SHA_1).add(STAGING_API_PATTERN, SHA_2).add(STAGING_API_PATTERN, SHA_3).add(WILD_CARD_PATTERN, SHA_4).add(WILD_CARD_PATTERN, SHA_2).add(WILD_CARD_PATTERN, SHA_3).build());
            }
            apiService = (ApiInterface) new Retrofit.Builder().baseUrl(SDKInternalConfig.getInstance().getFaceBaseUrl()).client(builder.build()).addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface.class);
        }
        return apiService;
    }

    public static DocsApiInterface getDocsService() {
        HVLogUtils.d(TAG, "getDocsService() called");
        if (docsService == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            configureTimeouts(builder);
            docsService = (DocsApiInterface) new Retrofit.Builder().baseUrl(SDKInternalConfig.getInstance().getDocsBaseUrl()).client(builder.build()).addConverterFactory(GsonConverterFactory.create()).build().create(DocsApiInterface.class);
        }
        return docsService;
    }

    public static RemoteConfigApiInterface getRemoteConfigService(Cache cache) {
        HVLogUtils.d(TAG, "getRemoteConfigService() called with: cacheDir = [" + cache + "]");
        if (remoteConfigApiInterfaceService == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            configureTimeouts(builder);
            remoteConfigApiInterfaceService = (RemoteConfigApiInterface) new Retrofit.Builder().baseUrl(SDKInternalConfig.getInstance().getS3RemoteConfigBaseUrl()).client(builder.cache(cache).build()).addConverterFactory(GsonConverterFactory.create()).build().create(RemoteConfigApiInterface.class);
        }
        return remoteConfigApiInterfaceService;
    }

    public static SensorDataInterface getSensorDataInterface() {
        if (sensorDataInterface == null) {
            configureTimeouts(new OkHttpClient.Builder());
            sensorDataInterface = (SensorDataInterface) new Retrofit.Builder().baseUrl("https://armxjib6ub.execute-api.ap-southeast-1.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build().create(SensorDataInterface.class);
        }
        return sensorDataInterface;
    }

    public static MixPanelApiInterface getMixPanelService() {
        HVLogUtils.d(TAG, "getMixPanelService() called");
        if (mixPanelApiService == null) {
            HyperSnapSDKConfig hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(hyperSnapSDKConfig.getConnectTimeOut(), TimeUnit.SECONDS);
            builder.writeTimeout(hyperSnapSDKConfig.getWriteTimeOut(), TimeUnit.SECONDS);
            builder.readTimeout(hyperSnapSDKConfig.getReadTimeOut(), TimeUnit.SECONDS);
            mixPanelApiService = (MixPanelApiInterface) new Retrofit.Builder().baseUrl(SDKInternalConfig.getInstance().getMixPanelBaseUrl()).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build().create(MixPanelApiInterface.class);
        }
        return mixPanelApiService;
    }

    public static AnalyticsApiInterface getAnalyticsApiInterface() {
        HVLogUtils.d(TAG, "getAnalyticsApiInterface() called");
        if (analyticsApiInterface == null) {
            configureTimeouts(new OkHttpClient.Builder());
            analyticsApiInterface = (AnalyticsApiInterface) new Retrofit.Builder().baseUrl(SDKInternalConfig.getInstance().getFaceBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build().create(AnalyticsApiInterface.class);
        }
        return analyticsApiInterface;
    }

    private static void configureTimeouts(OkHttpClient.Builder builder) {
        HVLogUtils.d(TAG, "configureTimeouts() called with: client = [" + builder + "]");
        builder.readTimeout((long) HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut(), TimeUnit.SECONDS);
        builder.writeTimeout((long) HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut(), TimeUnit.SECONDS);
        builder.connectTimeout((long) HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut(), TimeUnit.SECONDS);
    }
}
