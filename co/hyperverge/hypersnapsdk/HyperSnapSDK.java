package co.hyperverge.hypersnapsdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.media3.datasource.cache.CacheDataSink;
import co.hyperverge.crashguard.CrashGuard;
import co.hyperverge.crashguard.objects.CrashguardConfig;
import co.hyperverge.facedetection.FaceDetectorApi;
import co.hyperverge.hypersnapsdk.analytics.AnalyticsTracker;
import co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo;
import co.hyperverge.hypersnapsdk.helpers.HVFeatureConfigHelper;
import co.hyperverge.hypersnapsdk.helpers.LanguageHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.helpers.face.MLKitFaceHelper;
import co.hyperverge.hypersnapsdk.listeners.BrandingCompletionCallback;
import co.hyperverge.hypersnapsdk.listeners.InitializerCallback;
import co.hyperverge.hypersnapsdk.listeners.SessionStatusCallback;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.objects.ExternalConfigs;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVExifData;
import co.hyperverge.hypersnapsdk.objects.HVFaceCaptureMetaData;
import co.hyperverge.hypersnapsdk.objects.HVSessionResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.service.HVSignatureService;
import co.hyperverge.hypersnapsdk.service.exif.HVEXIFExtractor;
import co.hyperverge.hypersnapsdk.service.qr.AadhaarQRParser;
import co.hyperverge.hypersnapsdk.service.sensorbiometrics.HVSensorBiometrics;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.getkeepsafe.relinker.MissingLibraryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.Cache;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HyperSnapSDK {
    private static final String APP_ID_NULL_OR_EMPTY_ERROR = "appId is compulsory";
    private static final String APP_KEY_ACCESS_TOKEN_EMPTY = "Either appKey or accessToken are empty/null; Set either of them";
    private static final String APP_KEY_ACCESS_TOKEN_SET = "Set either appKey or accessToken, not both";
    private static final String CONTEXT_NULL_ERROR_MESSAGE = "Context object should not be null";
    private static final String HYPERSNAP_SDK_CONFIG_NULL_ERROR_MESSAGE = "HyperSnapSDKConfig object should not be null";
    private static final String LOCATION_PERMISSION_UNAVAILABLE = "Location permission not available while location config is set to true";
    private static final String TAG = "co.hyperverge.hypersnapsdk.HyperSnapSDK";
    private static Cache cacheDir;
    private static HVFaceCaptureMetaData faceCaptureMetaData;
    private static HyperSnapSDK hyperSnapSDK;
    private static boolean isLegacy;
    private static HyperSnapSDKConfig sHyperSnapSDKConfig = new HyperSnapSDKConfig();
    private JSONObject geoDetails;
    private boolean hyperSnapSDKInitialised = false;
    private boolean isHVFDInitialised = false;
    private InitializerCallback mInitializerCallback;

    private HyperSnapSDK() {
        faceCaptureMetaData = new HVFaceCaptureMetaData();
    }

    public static HyperSnapSDK getInstance() {
        if (hyperSnapSDK == null) {
            hyperSnapSDK = new HyperSnapSDK();
        }
        return hyperSnapSDK;
    }

    public static void init(Context context, String str, String str2, HyperSnapParams.Region region, HyperSnapParams.Product product) {
        HVLogUtils.d(TAG, "init() called with: context = [" + context + "], appId = [" + str + "], appKey = [" + str2 + "], hyperSnapRegion = [" + region + "], hyperSnapProduct = [" + product + "]");
        initForDeprecatedMethods(context, str, str2, region, product, null, null);
    }

    public static void init(Context context, String str, String str2, HyperSnapParams.Region region) {
        HVLogUtils.d(TAG, "init() called with: context = [" + context + "], appId = [" + str + "], appKey = [" + str2 + "], hyperSnapRegion = [" + region + "]");
        initForDeprecatedMethods(context, str, str2, region, HyperSnapParams.Product.FACEID, null, null);
    }

    public static void init(Context context, String str, String str2, HyperSnapParams.Region region, InitializerCallback initializerCallback) {
        HVLogUtils.d(TAG, "init() called with: context = [" + context + "], appId = [" + str + "], appKey = [" + str2 + "], hyperSnapRegion = [" + region + "], initializerCallback = [" + initializerCallback + "]");
        initForDeprecatedMethods(context, str, str2, region, HyperSnapParams.Product.FACEID, null, initializerCallback);
    }

    public static void initWithToken(Context context, String str, String str2, HyperSnapParams.Region region) {
        HVLogUtils.d(TAG, "initWithToken() called with: context = [" + context + "], appId = [" + str + "], accessToken = [" + str2 + "], hyperSnapRegion = [" + region + "]");
        initForDeprecatedMethods(context, str, null, region, HyperSnapParams.Product.FACEID, str2, null);
    }

    public static void initWithToken(Context context, String str, String str2, HyperSnapParams.Region region, InitializerCallback initializerCallback) {
        HVLogUtils.d(TAG, "initWithToken() called with: context = [" + context + "], appId = [" + str + "], accessToken = [" + str2 + "], hyperSnapRegion = [" + region + "], initializerCallback = [" + initializerCallback + "]");
        initForDeprecatedMethods(context, str, null, region, HyperSnapParams.Product.FACEID, str2, initializerCallback);
    }

    public static void setShouldUseSensorBiometrics(boolean z) {
        HVLogUtils.d(TAG, "setShouldUseSensorBiometrics() called with: shouldUse = [" + z + "]");
        sHyperSnapSDKConfig.setShouldUseSensorBiometrics(z);
    }

    public static void setShouldUseSHA256Signature(boolean z) {
        HVLogUtils.d(TAG, "setShouldUseSHA256Signature() called with: shouldUse = [" + z + "]");
        sHyperSnapSDKConfig.setShouldUseSHA256Signature(z);
        if (z) {
            sHyperSnapSDKConfig.setSignatureMethod("SHA-256");
        }
    }

    public static HVFaceCaptureMetaData getFaceCaptureMetaData() {
        HVLogUtils.d(TAG, "getFaceCaptureMetaData() called");
        return faceCaptureMetaData;
    }

    public static void setShouldEnableSSLPinning(boolean z) {
        HVLogUtils.d(TAG, "setShouldEnableSSLPinning() called with: shouldEnable = [" + z + "]");
        sHyperSnapSDKConfig.setShouldEnableSSLPinning(z);
    }

    public static void setShouldUseSignature(boolean z) {
        HVLogUtils.d(TAG, "setShouldUseSignature() called with: useSignature = [" + z + "]");
        sHyperSnapSDKConfig.setShouldUseSignature(z);
    }

    public static boolean isSslPinning() {
        HVLogUtils.d(TAG, "isSslPinning() called");
        return sHyperSnapSDKConfig.isShouldEnableSSLPinning();
    }

    public static void setAccessToken(String str) {
        HVLogUtils.d(TAG, "setAccessToken() called with: token = [" + str + "]");
        sHyperSnapSDKConfig.setAccessToken(str);
    }

    public static void setShouldLogOnlyErrors(Boolean bool) {
        HVLogUtils.d(TAG, "setShouldLogOnlyErrors() called with: shouldLog = [" + bool + "]");
        sHyperSnapSDKConfig.setShouldLogOnlyErrors(bool.booleanValue());
    }

    public static void setHttpTimeoutValues(int i, int i2, int i3) {
        HVLogUtils.d(TAG, "setHttpTimeoutValues() called with: connectTimeout = [" + i + "], readTimeout = [" + i2 + "], writeTimeout = [" + i3 + "]");
        sHyperSnapSDKConfig.setConnectTimeOut(i);
        sHyperSnapSDKConfig.setReadTimeOut(i2);
        sHyperSnapSDKConfig.setWriteTimeOut(i3);
    }

    public static JSONObject parseAadhaarQRData(String str) {
        HVLogUtils.d(TAG, "parseAadhaarQRData() called with: xmlstring = [" + str + "]");
        return new AadhaarQRParser().parseAadhaarQRData(str);
    }

    public static String sortJSONKeysAlphabetically(TreeMap<String, Object> treeMap) {
        HVLogUtils.d(TAG, "sortJSONKeysAlphabetically() called with: map = [" + treeMap + "]");
        return HVSignatureService.sortJSONKeysAlphabetically(treeMap);
    }

    public static TreeMap<String, Object> convertJSONObjToMap(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "convertJSONObjToMap() called with: jObject = [" + jSONObject + "]");
        return HVSignatureService.convertJSONObjToMap(jSONObject);
    }

    public static void setShouldReturnRawResponse(boolean z) {
        HVLogUtils.d(TAG, "setShouldReturnRawResponse() called with: shouldSend = [" + z + "]");
        sHyperSnapSDKConfig.setShouldReturnRawResponse(z);
    }

    public static boolean isInitialised() {
        HVLogUtils.d(TAG, "isInitialised() called");
        return getInstance().isHyperSnapSDKInitialised();
    }

    public static boolean setShouldUseLocation(Context context, boolean z) {
        HVLogUtils.d(TAG, "setShouldUseLocation() called with: context = [" + context + "], shouldUseLocation = [" + z + "]");
        sHyperSnapSDKConfig.setShouldUseLocation(z);
        return true;
    }

    public static boolean isShouldUseLocation() {
        HVLogUtils.d(TAG, "isShouldUseLocation() called");
        return sHyperSnapSDKConfig.isShouldUseLocation();
    }

    public static HVExifData extractExifDataFromImage(String str) {
        HVLogUtils.d(TAG, "extractExifDataFromImage() called with: imageUri = [" + str + "]");
        return new HVEXIFExtractor().extractExifDataFromImage(str);
    }

    @Deprecated
    public static void setBrandingCheck(boolean z, BrandingCompletionCallback brandingCompletionCallback) {
        String str = TAG;
        HVLogUtils.d(str, "setBrandingCheck() called with: check = [" + z + "], completionCallback = [" + brandingCompletionCallback + "]");
        CallbackProvider.get().setCallback(brandingCompletionCallback);
        sHyperSnapSDKConfig.setShouldUseRemoteConfig(z);
        if (!isInitialised()) {
            HVLogUtils.d(str, "setBrandingCheck(): SDK not initialised");
            brandingCompletionCallback.onResult(new HVError(11, "SDK not initialised."), false);
        }
        if (z && isInitialised()) {
            getRemoteConfigForBrandingCheck(sHyperSnapSDKConfig.getAppId(), brandingCompletionCallback);
        }
    }

    private static void getRemoteConfigForBrandingCheck(String str, final BrandingCompletionCallback brandingCompletionCallback) {
        String str2 = TAG;
        HVLogUtils.d(str2, "getRemoteConfigForBrandingCheck() called with: appId = [" + str + "], brandingCompletionCallback = [" + brandingCompletionCallback + "]");
        if (TextUtils.isEmpty(str)) {
            HVLogUtils.d(str2, "getRemoteConfigForBrandingCheck(): appId is empty");
            brandingCompletionCallback.onResult(new HVError(11, APP_ID_NULL_OR_EMPTY_ERROR), false);
        }
        HVRemoteConfigRepo.getInstance(cacheDir).getRemoteConfig(str, true, new HVRemoteConfigRepo.HVCallback() { // from class: co.hyperverge.hypersnapsdk.HyperSnapSDK.1
            @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
            public void onSuccess() {
                HVLogUtils.d(HyperSnapSDK.TAG, "getRemoteConfig(): onSuccess() called");
                BrandingCompletionCallback.this.onResult(null, SDKInternalConfig.getInstance().isShouldUseBranding());
            }

            @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
            public void onError(HVError hVError) {
                Log.d(HyperSnapSDK.TAG, "getRemoteConfig(): onError() called with: hvError = [" + hVError + "]");
                BrandingCompletionCallback.this.onResult(hVError, false);
            }
        });
    }

    @Deprecated
    private static void initForDeprecatedMethods(Context context, String str, String str2, HyperSnapParams.Region region, HyperSnapParams.Product product, String str3, InitializerCallback initializerCallback) {
        HVLogUtils.d(TAG, "initForDeprecatedMethods() called with: context = [" + context + "], appId = [" + str + "], appKey = [" + str2 + "], hyperSnapRegion = [" + region + "], hyperSnapProduct = [" + product + "], token = [" + str3 + "], initializerCallback = [" + initializerCallback + "]");
        sHyperSnapSDKConfig.setAppId(str);
        sHyperSnapSDKConfig.setAppKey(str2);
        sHyperSnapSDKConfig.setHyperSnapRegion(region);
        if (region == HyperSnapParams.Region.India) {
            sHyperSnapSDKConfig.setHyperSnapRegion(HyperSnapParams.Region.INDIA);
        } else if (region == HyperSnapParams.Region.AsiaPacific) {
            sHyperSnapSDKConfig.setHyperSnapRegion(HyperSnapParams.Region.ASIA_PACIFIC);
        }
        sHyperSnapSDKConfig.setHyperSnapProduct(product);
        if (!StringUtils.isEmptyOrNull(str3)) {
            sHyperSnapSDKConfig.setAccessToken(str3);
        }
        isLegacy = true;
        getInstance().init(context, sHyperSnapSDKConfig, initializerCallback);
    }

    @Deprecated
    public static boolean startUserSession(SessionStatusCallback sessionStatusCallback) {
        String str = TAG;
        HVLogUtils.d(str, "startUserSession() called with: callback = [" + sessionStatusCallback + "]");
        if (isInitialised()) {
            if (isUserSessionActive()) {
                return false;
            }
            HVLogUtils.d(str, "startUserSession(): user session is not active, generating random transactionId");
            return SPHelper.generateRandomTransactionId(sessionStatusCallback);
        }
        if (sessionStatusCallback != null) {
            HVLogUtils.d(str, "startUserSession(): SDK is not initialised");
            sessionStatusCallback.onFailure(new HVError(11, "SDK is not initialised"));
        }
        return false;
    }

    @Deprecated
    public static boolean startUserSession(String str, SessionStatusCallback sessionStatusCallback) {
        String str2 = TAG;
        HVLogUtils.d(str2, "startUserSession() called with: userId = [" + str + "], callback = [" + sessionStatusCallback + "]");
        if (!isInitialised()) {
            if (sessionStatusCallback != null) {
                HVLogUtils.d(str2, "startUserSession(): SDK is not initialised");
                sessionStatusCallback.onFailure(new HVError(11, "SDK is not initialised"));
            }
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            if (isUserSessionActive()) {
                return false;
            }
            return SPHelper.setTransactionID(str, sessionStatusCallback);
        }
        if (sessionStatusCallback != null) {
            HVLogUtils.d(str2, "startUserSession(): empty transactionId");
            sessionStatusCallback.onFailure(new HVError(17, AppConstants.EMPTY_TRANSACTION_ERROR));
        }
        return false;
    }

    public static HVSessionResponse startUserSession(String str) {
        HVError generateRandomTransactionId;
        String str2 = TAG;
        HVLogUtils.d(str2, "startUserSession() called with: userId = [" + str + "]");
        HVSessionResponse hVSessionResponse = new HVSessionResponse();
        if (!isInitialised()) {
            HVLogUtils.d(str2, "startUserSession(): SDK is not initialised");
            hVSessionResponse.setHvError(new HVError(11, "SDK is not initialised"));
            return hVSessionResponse;
        }
        if (!StringUtils.isEmptyOrNull(str)) {
            generateRandomTransactionId = SPHelper.setTransactionID(str);
        } else {
            generateRandomTransactionId = SPHelper.generateRandomTransactionId();
        }
        if (generateRandomTransactionId != null) {
            HVLogUtils.d(str2, "startUserSession(): error in creating transactionId, error = [" + generateRandomTransactionId + "]");
            hVSessionResponse.setHvError(generateRandomTransactionId);
        }
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null && hVSessionResponse.isSuccess()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logUserSessionStarted(!StringUtils.isEmptyOrNull(str));
        }
        if (hVSessionResponse.isSuccess()) {
            HVLogUtils.d(str2, "startUserSession(): successful creation of transactionId, response = [" + hVSessionResponse + "]");
            getInstance().getFeatureConfigs();
        }
        return hVSessionResponse;
    }

    public static HVSessionResponse startUserSession() {
        HVLogUtils.d(TAG, "startUserSession() called");
        return startUserSession((String) null);
    }

    public static void endUserSession() {
        String str = TAG;
        HVLogUtils.d(str, "endUserSession() called");
        if (isUserSessionActive()) {
            SPHelper.closeTransactionId();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logUserSessionEnded(null);
            return;
        }
        HVLogUtils.d(str, "endUserSession(): No active sessions");
        Log.d(str, "No active user sessions to end. Please call startUserSession after initialising the SDK");
    }

    public static boolean isUserSessionActive() {
        HVLogUtils.d(TAG, "isUserSessionActive() called");
        if (isInitialised()) {
            return !TextUtils.isEmpty(SPHelper.getTransactionID());
        }
        return false;
    }

    public static void shouldDisableRemoteConfig() {
        HVLogUtils.d(TAG, "shouldDisableRemoteConfig() called");
        sHyperSnapSDKConfig.setShouldUseRemoteConfig(false);
    }

    public JSONObject getGeoDetails() {
        HVLogUtils.d(TAG, "getGeoDetails() called");
        return this.geoDetails;
    }

    public void init(final Context context, final HyperSnapSDKConfig hyperSnapSDKConfig, InitializerCallback initializerCallback) {
        HVLogUtils.d(TAG, "init() called with: context = [" + context + "], hyperSnapSDKConfig = [" + hyperSnapSDKConfig + "], initializerCallback = [" + initializerCallback + "]");
        sHyperSnapSDKConfig = hyperSnapSDKConfig;
        this.mInitializerCallback = initializerCallback;
        if (isValidInputs(context, hyperSnapSDKConfig)) {
            cacheDir = new Cache(context.getApplicationContext().getCacheDir(), CacheDataSink.DEFAULT_FRAGMENT_SIZE);
            initErrorMonitoring(context);
            setInternalConfigs(context, hyperSnapSDKConfig.getHyperSnapRegion());
            SPHelper.init(context);
            if (initializerCallback != null) {
                getDefaultRemoteConfigs(new HVRemoteConfigRepo.HVCallback() { // from class: co.hyperverge.hypersnapsdk.HyperSnapSDK.2
                    @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
                    public void onSuccess() {
                        HVLogUtils.d(HyperSnapSDK.TAG, "getDefaultRemoteConfigs: onSuccess() called");
                        HyperSnapSDK.this.initAnalytics(context);
                        HyperSnapSDK.this.continueInitialisation(hyperSnapSDKConfig, context);
                        SDKInternalConfig.getInstance().setDefaultRemoteConfigFetchDone(true);
                    }

                    @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
                    public void onError(HVError hVError) {
                        HVLogUtils.d(HyperSnapSDK.TAG, "getDefaultRemoteConfigs: onError() called with: hvError = [" + hVError + "]");
                        HyperSnapSDK.this.initAnalytics(context);
                        HyperSnapSDK.this.continueInitialisation(hyperSnapSDKConfig, context);
                        SDKInternalConfig.getInstance().setDefaultRemoteConfigFetchDone(true);
                    }
                });
            } else {
                getDefaultRemoteConfigs(new HVRemoteConfigRepo.HVCallback() { // from class: co.hyperverge.hypersnapsdk.HyperSnapSDK.3
                    @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
                    public void onSuccess() {
                        HVLogUtils.d(HyperSnapSDK.TAG, "getDefaultRemoteConfigs: onSuccess() called");
                        HyperSnapSDK.this.initAnalytics(context);
                        SDKInternalConfig.getInstance().setDefaultRemoteConfigFetchDone(true);
                    }

                    @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
                    public void onError(HVError hVError) {
                        HVLogUtils.d(HyperSnapSDK.TAG, "getDefaultRemoteConfigs: onError() called with: hvError = [" + hVError + "]");
                        HyperSnapSDK.this.initAnalytics(context);
                        SDKInternalConfig.getInstance().setDefaultRemoteConfigFetchDone(true);
                    }
                });
                continueInitialisation(hyperSnapSDKConfig, context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void continueInitialisation(HyperSnapSDKConfig hyperSnapSDKConfig, Context context) {
        if (hyperSnapSDKConfig.isShouldUseRemoteConfig()) {
            getRemoteConfigs(hyperSnapSDKConfig);
        } else {
            SDKInternalConfig.getInstance().createDefaultMixpanelConfigs();
            getFeatureConfigs();
        }
        initialize(context, hyperSnapSDKConfig);
    }

    public HyperSnapSDKConfig getHyperSnapSDKConfig() {
        return sHyperSnapSDKConfig;
    }

    public boolean isHyperSnapSDKInitialised() {
        HVLogUtils.d(TAG, "isHyperSnapSDKInitialised() called");
        return this.hyperSnapSDKInitialised;
    }

    public void logEvent(String str, Map<String, Object> map) {
        HVLogUtils.d(TAG, "logEvent() called with: eventName = [" + str + "], eventData = [" + map + "]");
        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logGenericTrackEvent(str, map);
    }

    public void setUiConfig(Context context, UIConfig uIConfig) {
        String str = TAG;
        HVLogUtils.d(str, "setUiConfig() called with: context = [" + context + "], uiConfig = [" + uIConfig + "]");
        if (getHyperSnapSDKConfig().getUiConfig() == null && uIConfig == null) {
            HVLogUtils.d(str, "setUiConfig() setting default uiconfig");
            getHyperSnapSDKConfig().setUiConfig(new UIConfig());
            HyperSnapUIConfigUtil.getInstance().init(context);
            return;
        }
        if (uIConfig != null) {
            HVLogUtils.d(str, "setUiConfig() setting custom uiconfig");
            getHyperSnapSDKConfig().setUiConfig(uIConfig);
            HyperSnapUIConfigUtil.getInstance().init(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFeatureConfigs() {
        HVLogUtils.d(TAG, "getFeatureConfigs() called");
        HVRemoteConfigRepo.getInstance(cacheDir).getFeatureConfigs(BuildConfig.HYPERSNAP_VERSION_NAME, new HVRemoteConfigRepo.HVCallback() { // from class: co.hyperverge.hypersnapsdk.HyperSnapSDK.4
            @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
            public void onSuccess() {
                HVLogUtils.d(HyperSnapSDK.TAG, "getFeatureConfigs(): onSuccess() called");
                SDKInternalConfig.getInstance().setRemoteConfigFetchDone(true);
            }

            @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
            public void onError(HVError hVError) {
                HVLogUtils.d(HyperSnapSDK.TAG, "getFeatureConfigs(): onError() called with error = [" + hVError + "]");
                Log.e(HyperSnapSDK.TAG, "onError: " + hVError.getErrorMessage());
                SDKInternalConfig.getInstance().setFeatureConfigMap(HVFeatureConfigHelper.getDefaultFeatureMap());
                SDKInternalConfig.getInstance().setRemoteConfigFetchDone(true);
            }
        });
    }

    private void getDefaultRemoteConfigs(HVRemoteConfigRepo.HVCallback hVCallback) {
        HVLogUtils.d(TAG, "getDefaultRemoteConfigs() called");
        HVRemoteConfigRepo.getInstance(cacheDir).getDefaultRemoteConfig(hVCallback);
    }

    private void getRemoteConfigs(HyperSnapSDKConfig hyperSnapSDKConfig) {
        HVLogUtils.d(TAG, "getRemoteConfigs() called with: hyperSnapSDKConfig = [" + hyperSnapSDKConfig + "]");
        HVRemoteConfigRepo.getInstance(cacheDir).getRemoteConfig(hyperSnapSDKConfig.getAppId(), false, new HVRemoteConfigRepo.HVCallback() { // from class: co.hyperverge.hypersnapsdk.HyperSnapSDK.5
            @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
            public void onSuccess() {
                HVLogUtils.d(HyperSnapSDK.TAG, "getRemoteConfigs(): onSuccess called");
                HyperSnapSDK.this.getFeatureConfigs();
            }

            @Override // co.hyperverge.hypersnapsdk.data.remote.HVRemoteConfigRepo.HVCallback
            public void onError(HVError hVError) {
                HVLogUtils.d(HyperSnapSDK.TAG, "getRemoteConfigs(): onError() called with: hvError = [" + hVError + "]");
                HyperSnapSDK.this.getFeatureConfigs();
            }
        });
    }

    private void initialize(Context context, HyperSnapSDKConfig hyperSnapSDKConfig) {
        HVLogUtils.d(TAG, "initialize() called with: context = [" + context + "], hyperSnapSDKConfig = [" + hyperSnapSDKConfig + "]");
        initFaceDetector(context);
        LanguageHelper.init(context);
        initHVSensorBiometrics(context);
        setUiConfig(context, null);
        notifySuccess();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logHyperSnapSDKInitialised();
        }
    }

    private void initHVSensorBiometrics(Context context) {
        HVLogUtils.d(TAG, "initHVSensorBiometrics() called with: context = [" + context + "]");
        SDKInternalConfig.getInstance().setHvSensorBiometrics(new HVSensorBiometrics(context));
    }

    private void initErrorMonitoring(Context context) {
        HashSet<String> sentryFilters;
        HVLogUtils.d(TAG, "initErrorMonitoring() called with: context = [" + context + "]");
        SDKInternalConfig.getInstance().setErrorMonitoringService(SDKInternalConfig.getInstance().getErrorMonitoringService(context));
        HyperSnapSDKConfig hyperSnapSDKConfig = getHyperSnapSDKConfig();
        HashMap hashMap = new HashMap();
        hashMap.put("appId", hyperSnapSDKConfig.getAppId());
        hashMap.put("hypersnap_sdk_version", BuildConfig.HYPERSNAP_VERSION_NAME);
        ExternalConfigs externalConfigs = hyperSnapSDKConfig.getExternalConfigs();
        if (externalConfigs != null) {
            HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
            if (!metadataMap.containsKey("release")) {
                metadataMap.put("release", BuildConfig.HYPERSNAP_VERSION_NAME);
            }
            for (String str : metadataMap.keySet()) {
                hashMap.put(str, metadataMap.get(str));
            }
        }
        try {
            HashSet hashSet = new HashSet();
            hashSet.add("co.hyperverge");
            if (externalConfigs != null && externalConfigs.getSentryConfig() != null && (sentryFilters = externalConfigs.getSentryConfig().getSentryFilters()) != null) {
                hashSet.addAll(sentryFilters);
            }
            CrashGuard.getInstance().init(context.getApplicationContext(), new CrashguardConfig(new ArrayList(hashSet), hashMap));
        } catch (Exception | NoClassDefFoundError e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "initErrorMonitoring(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAnalytics(Context context) {
        HVLogUtils.d(TAG, "initAnalytics() called with: context = [" + context + "]");
        int userRandomNumber = SPHelper.getUserRandomNumber();
        if (userRandomNumber == 1000) {
            userRandomNumber = Utils.generateRandomInteger(100);
            SPHelper.saveUserRandomNumber(userRandomNumber);
        }
        SDKInternalConfig.getInstance().setUserRandomNumber(userRandomNumber);
        try {
            SDKInternalConfig.getInstance().setAnalyticsTrackerService(new AnalyticsTracker(context));
            SDKInternalConfig.getInstance().setShouldUseMixpanel(true);
        } catch (Exception | NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "initAnalytics(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
            SDKInternalConfig.getInstance().setShouldUseMixpanel(false);
        }
    }

    private void initFaceDetector(Context context) {
        HVLogUtils.d(TAG, "initFaceDetector() called with: context = [" + context + "]");
        try {
            if (this.isHVFDInitialised) {
                return;
            }
            FaceDetectorApi.initialize(context.getApplicationContext(), 2);
            SDKInternalConfig.getInstance().setFaceDetectionProcessor(SDKInternalConfig.FaceDetectionProcessor.NPD);
            SDKInternalConfig.getInstance().setFaceDetectionOn(true);
            this.isHVFDInitialised = true;
        } catch (MissingLibraryException | NoClassDefFoundError | UnsatisfiedLinkError e) {
            HVLogUtils.e(TAG, "initFaceDetector(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            MLKitFaceHelper.get();
            SDKInternalConfig.getInstance().setFaceDetectionProcessor(SDKInternalConfig.FaceDetectionProcessor.MLKIT);
            SDKInternalConfig.getInstance().setFaceDetectionOn((SDKInternalConfig.getInstance().isMLKitDetectorMissing() || SDKInternalConfig.getInstance().isMLKitUnavailable()) ? false : true);
        }
    }

    private void setInternalConfigs(Context context, HyperSnapParams.Region region) {
        HVLogUtils.d(TAG, "setInternalConfigs() called with: context = [" + context + "], region = [" + region + "]");
        try {
            SDKInternalConfig.getInstance().setAppVersion(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            String str = TAG;
            HVLogUtils.e(str, "setInternalConfigs(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        SDKInternalConfig.getInstance().setAppName(context.getPackageName());
        if (region == HyperSnapParams.Region.INDIA) {
            SDKInternalConfig.getInstance().setFaceBaseUrl(SDKInternalConfig.getInstance().getIndiaFaceBaseUrl());
            return;
        }
        if (region == HyperSnapParams.Region.ASIA_PACIFIC) {
            SDKInternalConfig.getInstance().setFaceBaseUrl(SDKInternalConfig.getInstance().getApacLivenessFaceBaseUrl());
        } else if (region == HyperSnapParams.Region.AFRICA) {
            SDKInternalConfig.getInstance().setFaceBaseUrl(SDKInternalConfig.getInstance().getAfricaLivenessFaceBaseUrl());
        } else {
            SDKInternalConfig.getInstance().setFaceBaseUrl(SDKInternalConfig.getInstance().getApacLivenessFaceBaseUrl());
        }
    }

    private boolean isValidInputs(Context context, HyperSnapSDKConfig hyperSnapSDKConfig) {
        String str = TAG;
        HVLogUtils.d(str, "isValidInputs() called with: context = [" + context + "], hyperSnapSDKConfig = [" + hyperSnapSDKConfig + "]");
        if (context == null) {
            HVLogUtils.d(str, "isValidInputs(): Context object should not be null");
            notifyError(CONTEXT_NULL_ERROR_MESSAGE, 6);
            return false;
        }
        if (hyperSnapSDKConfig == null) {
            HVLogUtils.d(str, "isValidInputs(): HyperSnapSDKConfig object should not be null");
            notifyError(HYPERSNAP_SDK_CONFIG_NULL_ERROR_MESSAGE, 6);
            return false;
        }
        if (StringUtils.isEmptyOrNull(hyperSnapSDKConfig.getAppId())) {
            HVLogUtils.d(str, "isValidInputs(): appId is compulsory");
            notifyError(APP_ID_NULL_OR_EMPTY_ERROR, 6);
            return false;
        }
        boolean isEmptyOrNull = StringUtils.isEmptyOrNull(hyperSnapSDKConfig.getAppKey());
        boolean isEmptyOrNull2 = StringUtils.isEmptyOrNull(hyperSnapSDKConfig.getAccessToken());
        if (isEmptyOrNull && isEmptyOrNull2) {
            HVLogUtils.d(str, "isValidInputs(): Either appKey or accessToken are empty/null; Set either of them");
            notifyError(APP_KEY_ACCESS_TOKEN_EMPTY, 6);
            return false;
        }
        if (!isEmptyOrNull && !isEmptyOrNull2) {
            HVLogUtils.d(str, "isValidInputs(): Set either appKey or accessToken, not both");
            notifyError(APP_KEY_ACCESS_TOKEN_SET, 6);
            return false;
        }
        if (!hyperSnapSDKConfig.isShouldUseLocation() || isLocationPermissionAvailable(context)) {
            return true;
        }
        HVLogUtils.d(str, "isValidInputs(): Location permission not available while location config is set to true");
        notifyError(LOCATION_PERMISSION_UNAVAILABLE, 8);
        return false;
    }

    private boolean isLocationPermissionAvailable(Context context) {
        HVLogUtils.d(TAG, "isLocationPermissionAvailable() called with: context = [" + context + "]");
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private void notifySuccess() {
        String str = TAG;
        HVLogUtils.d(str, "notifySuccess() called");
        this.hyperSnapSDKInitialised = true;
        if (this.mInitializerCallback != null) {
            HVLogUtils.d(str, "notifySuccess(): initializer.onSuccess called");
            this.mInitializerCallback.onSuccess();
            if (!getInstance().getHyperSnapSDKConfig().isShouldUseRemoteConfig() || CallbackProvider.get().injectBrandingCallback() == null) {
                return;
            }
            CallbackProvider.get().injectBrandingCallback().onResult(null, true);
        }
    }

    private void notifyError(String str, int i) {
        String str2 = TAG;
        HVLogUtils.d(str2, "notifyError() called with: errorMessage = [" + str + "], errorCode = [" + i + "]");
        if (this.mInitializerCallback != null) {
            HVError hVError = new HVError();
            hVError.setErrorCode(i);
            hVError.setErrorMessage(str);
            HVLogUtils.d(str2, "notifySuccess(): initializer.onError called");
            this.mInitializerCallback.onError(hVError);
            if (getInstance().getHyperSnapSDKConfig() == null || !getInstance().getHyperSnapSDKConfig().isShouldUseRemoteConfig() || CallbackProvider.get().injectBrandingCallback() == null) {
                return;
            }
            CallbackProvider.get().injectBrandingCallback().onResult(hVError, false);
        }
    }
}
