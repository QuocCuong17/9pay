package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.codeless.internal.UnityReflection;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import io.sentry.protocol.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FetchedAppSettingsManager.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002efB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u000207H\u0007J\u0010\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u0004H\u0002J\u0014\u0010F\u001a\u0004\u0018\u0001042\b\u0010E\u001a\u0004\u0018\u00010\u0004H\u0007J\u0016\u0010G\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000209\u0018\u00010HH\u0007J\b\u0010I\u001a\u00020AH\u0007J\u001d\u0010J\u001a\u0002042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010K\u001a\u00020DH\u0000¢\u0006\u0002\bLJ\"\u0010M\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010N\u001a\u0004\u0018\u00010D2\u0006\u0010O\u001a\u00020\u0004H\u0002JT\u0010P\u001a:\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00100R\u0018\u00010Qj\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00100R\u0018\u0001`S2\b\u0010N\u001a\u0004\u0018\u00010D2\b\b\u0002\u0010T\u001a\u000209H\u0002J\u0019\u0010U\u001a\u0004\u0018\u00010V2\b\u0010W\u001a\u0004\u0018\u00010DH\u0002¢\u0006\u0002\u0010XJ*\u0010Y\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020Z030H2\b\u0010[\u001a\u0004\u0018\u00010DH\u0002J \u0010\\\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000209\u0018\u00010H2\b\u0010K\u001a\u0004\u0018\u00010DH\u0002J\u001c\u0010]\u001a\u0004\u0018\u00010?2\b\u0010^\u001a\u0004\u0018\u00010D2\u0006\u0010_\u001a\u00020\u0004H\u0002J\b\u0010`\u001a\u00020AH\u0002J\u001a\u0010a\u001a\u0004\u0018\u0001042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010b\u001a\u000209H\u0007J\u0010\u0010c\u001a\u00020A2\u0006\u0010d\u001a\u000209H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n .*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020403X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010:\u001a\u0010\u0012\f\u0012\n .*\u0004\u0018\u00010<0<0;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Lcom/facebook/internal/FetchedAppSettingsManager;", "", "()V", "ANDROID_DEDUPE_KEY", "", "APPLICATION_FIELDS", "APP_SETTINGS_PREFS_KEY_FORMAT", "APP_SETTINGS_PREFS_STORE", "APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES", "APP_SETTING_APP_EVENTS_AAM_RULE", "APP_SETTING_APP_EVENTS_CONFIG", "APP_SETTING_APP_EVENTS_EVENT_BINDINGS", "APP_SETTING_APP_EVENTS_FEATURE_BITMASK", "APP_SETTING_APP_EVENTS_SESSION_TIMEOUT", "APP_SETTING_DIALOG_CONFIGS", "APP_SETTING_FIELDS", "", "APP_SETTING_NUX_CONTENT", "APP_SETTING_NUX_ENABLED", "APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD", "APP_SETTING_SMART_LOGIN_OPTIONS", "APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING", "AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD", "", "AUTO_LOG_APP_EVENTS_DEFAULT_FIELD", "AUTO_LOG_APP_EVENT_ENABLED_FIELD", "BLOCKLIST_EVENTS_KEY", "CODELESS_EVENTS_ENABLED_BITMASK_FIELD", "DEDUPE_KEY", "DEDUPE_WINDOW", "IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD", "KEY", "MACA_RULES_KEY", "MONITOR_ENABLED_BITMASK_FIELD", "PROD_DEDUPE_KEY", "PROTECTED_MODE_RULES", "REDACTED_EVENTS_KEY", "SDK_UPDATE_MESSAGE", "SENSITIVE_PARAMS_KEY", "SMART_LOGIN_BOOKMARK_ICON_URL", "SMART_LOGIN_MENU_ICON_URL", "STANDARD_PARAMS_KEY", "STD_PARAMS_BLOCKED_KEY", "STD_PARAMS_SCHEMA_KEY", "SUGGESTED_EVENTS_SETTING", "TAG", "kotlin.jvm.PlatformType", "TEST_DEDUPE_KEY", "TRACK_UNINSTALL_ENABLED_BITMASK_FIELD", "VALUE", "fetchedAppSettings", "", "Lcom/facebook/internal/FetchedAppSettings;", "fetchedAppSettingsCallbacks", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/facebook/internal/FetchedAppSettingsManager$FetchedAppSettingsCallback;", "isUnityInit", "", "loadingState", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/facebook/internal/FetchedAppSettingsManager$FetchAppSettingState;", "printedSDKUpdatedMessage", "unityEventBindings", "Lorg/json/JSONArray;", "getAppSettingsAsync", "", "callback", "getAppSettingsQueryResponse", "Lorg/json/JSONObject;", "applicationId", "getAppSettingsWithoutQuery", "getCachedMigratedAutoLogValuesInAppSettings", "", "loadAppSettingsAsync", "parseAppSettingsFromJSON", "settingsJSON", "parseAppSettingsFromJSON$facebook_core_release", "parseCurrencyAndValueDedupeParameters", "originalJSON", "key", "parseDedupeParameters", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "Lkotlin/collections/ArrayList;", "getTestValues", "parseDedupeWindow", "", "parameters", "(Lorg/json/JSONObject;)Ljava/lang/Long;", "parseDialogConfigurations", "Lcom/facebook/internal/FetchedAppSettings$DialogFeatureConfig;", "dialogConfigResponse", "parseMigratedAutoLogValues", "parseProtectedModeRules", "protectedModeSettings", "ruleType", "pollCallbacks", "queryAppSettings", "forceRequery", "setIsUnityInit", "flag", "FetchAppSettingState", "FetchedAppSettingsCallback", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FetchedAppSettingsManager {
    private static final String ANDROID_DEDUPE_KEY = "FBAndroidSDK";
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_APP_EVENTS_CONFIG = "app_events_config";
    private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
    private static final String BLOCKLIST_EVENTS_KEY = "blocklist_events";
    private static final int CODELESS_EVENTS_ENABLED_BITMASK_FIELD = 32;
    private static final String DEDUPE_KEY = "iap_manual_and_auto_log_dedup_keys";
    private static final String DEDUPE_WINDOW = "iap_manual_and_auto_log_dedup_window_millis";
    private static final int IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 16;
    private static final String KEY = "key";
    private static final String MACA_RULES_KEY = "maca_rules";
    private static final int MONITOR_ENABLED_BITMASK_FIELD = 16384;
    private static final String PROD_DEDUPE_KEY = "prod_keys";
    private static final String REDACTED_EVENTS_KEY = "redacted_events";
    private static final String SDK_UPDATE_MESSAGE = "sdk_update_message";
    private static final String SENSITIVE_PARAMS_KEY = "sensitive_params";
    private static final String STANDARD_PARAMS_KEY = "standard_params";
    private static final String STD_PARAMS_BLOCKED_KEY = "standard_params_blocked";
    private static final String STD_PARAMS_SCHEMA_KEY = "standard_params_schema";
    private static final String TEST_DEDUPE_KEY = "test_keys";
    private static final int TRACK_UNINSTALL_ENABLED_BITMASK_FIELD = 256;
    private static final String VALUE = "value";
    private static boolean isUnityInit;
    private static boolean printedSDKUpdatedMessage;
    private static JSONArray unityEventBindings;
    public static final FetchedAppSettingsManager INSTANCE = new FetchedAppSettingsManager();
    private static final String TAG = "FetchedAppSettingsManager";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
    private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
    private static final String APP_SETTING_APP_EVENTS_EVENT_BINDINGS = "auto_event_mapping_android";
    private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
    private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
    private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
    private static final String APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD = "restrictive_data_filter_params";
    private static final String APP_SETTING_APP_EVENTS_AAM_RULE = "aam_rules";
    private static final String SUGGESTED_EVENTS_SETTING = "suggested_events_setting";
    private static final String PROTECTED_MODE_RULES = "protected_mode_rules";
    public static final String AUTO_LOG_APP_EVENTS_DEFAULT_FIELD = "auto_log_app_events_default";
    public static final String AUTO_LOG_APP_EVENT_ENABLED_FIELD = "auto_log_app_events_enabled";
    private static final List<String> APP_SETTING_FIELDS = CollectionsKt.listOf((Object[]) new String[]{APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES, APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, APP_SETTING_APP_EVENTS_FEATURE_BITMASK, APP_SETTING_APP_EVENTS_EVENT_BINDINGS, APP_SETTING_SMART_LOGIN_OPTIONS, SMART_LOGIN_BOOKMARK_ICON_URL, SMART_LOGIN_MENU_ICON_URL, APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD, APP_SETTING_APP_EVENTS_AAM_RULE, SUGGESTED_EVENTS_SETTING, PROTECTED_MODE_RULES, AUTO_LOG_APP_EVENTS_DEFAULT_FIELD, AUTO_LOG_APP_EVENT_ENABLED_FIELD, "app_events_config.os_version(" + Build.VERSION.RELEASE + ')'});
    private static final Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    private static final AtomicReference<FetchAppSettingState> loadingState = new AtomicReference<>(FetchAppSettingState.NOT_LOADED);
    private static final ConcurrentLinkedQueue<FetchedAppSettingsCallback> fetchedAppSettingsCallbacks = new ConcurrentLinkedQueue<>();

    /* compiled from: FetchedAppSettingsManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/internal/FetchedAppSettingsManager$FetchAppSettingState;", "", "(Ljava/lang/String;I)V", "NOT_LOADED", "LOADING", "SUCCESS", "ERROR", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum FetchAppSettingState {
        NOT_LOADED,
        LOADING,
        SUCCESS,
        ERROR
    }

    /* compiled from: FetchedAppSettingsManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/facebook/internal/FetchedAppSettingsManager$FetchedAppSettingsCallback;", "", "onError", "", "onSuccess", "fetchedAppSettings", "Lcom/facebook/internal/FetchedAppSettings;", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface FetchedAppSettingsCallback {
        void onError();

        void onSuccess(FetchedAppSettings fetchedAppSettings);
    }

    private FetchedAppSettingsManager() {
    }

    @JvmStatic
    public static final void loadAppSettingsAsync() {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        if (Utility.isNullOrEmpty(applicationId)) {
            loadingState.set(FetchAppSettingState.ERROR);
            INSTANCE.pollCallbacks();
            return;
        }
        if (fetchedAppSettings.containsKey(applicationId)) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            INSTANCE.pollCallbacks();
            return;
        }
        AtomicReference<FetchAppSettingState> atomicReference = loadingState;
        if (!(atomicReference.compareAndSet(FetchAppSettingState.NOT_LOADED, FetchAppSettingState.LOADING) || atomicReference.compareAndSet(FetchAppSettingState.ERROR, FetchAppSettingState.LOADING))) {
            INSTANCE.pollCallbacks();
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        final String format = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, Arrays.copyOf(new Object[]{applicationId}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FetchedAppSettingsManager.loadAppSettingsAsync$lambda$0(applicationContext, format, applicationId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadAppSettingsAsync$lambda$0(Context context, String settingsKey, String applicationId) {
        JSONObject jSONObject;
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(settingsKey, "$settingsKey");
        Intrinsics.checkNotNullParameter(applicationId, "$applicationId");
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SETTINGS_PREFS_STORE, 0);
        FetchedAppSettings fetchedAppSettings2 = null;
        String string = sharedPreferences.getString(settingsKey, null);
        if (!Utility.isNullOrEmpty(string)) {
            if (string == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            try {
                jSONObject = new JSONObject(string);
            } catch (JSONException e) {
                Utility.logd(Utility.LOG_TAG, e);
                jSONObject = null;
            }
            if (jSONObject != null) {
                fetchedAppSettings2 = INSTANCE.parseAppSettingsFromJSON$facebook_core_release(applicationId, jSONObject);
            }
        }
        FetchedAppSettingsManager fetchedAppSettingsManager = INSTANCE;
        JSONObject appSettingsQueryResponse = fetchedAppSettingsManager.getAppSettingsQueryResponse(applicationId);
        if (appSettingsQueryResponse != null) {
            fetchedAppSettingsManager.parseAppSettingsFromJSON$facebook_core_release(applicationId, appSettingsQueryResponse);
            sharedPreferences.edit().putString(settingsKey, appSettingsQueryResponse.toString()).apply();
        }
        if (fetchedAppSettings2 != null) {
            String sdkUpdateMessage = fetchedAppSettings2.getSdkUpdateMessage();
            if (!printedSDKUpdatedMessage && sdkUpdateMessage != null && sdkUpdateMessage.length() > 0) {
                printedSDKUpdatedMessage = true;
                Log.w(TAG, sdkUpdateMessage);
            }
        }
        FetchedAppGateKeepersManager.queryAppGateKeepers(applicationId, true);
        AutomaticAnalyticsLogger.logActivateAppEvent();
        loadingState.set(fetchedAppSettings.containsKey(applicationId) ? FetchAppSettingState.SUCCESS : FetchAppSettingState.ERROR);
        fetchedAppSettingsManager.pollCallbacks();
    }

    @JvmStatic
    public static final FetchedAppSettings getAppSettingsWithoutQuery(String applicationId) {
        if (applicationId != null) {
            return fetchedAppSettings.get(applicationId);
        }
        return null;
    }

    @JvmStatic
    public static final void getAppSettingsAsync(FetchedAppSettingsCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        fetchedAppSettingsCallbacks.add(callback);
        loadAppSettingsAsync();
    }

    private final synchronized void pollCallbacks() {
        FetchAppSettingState fetchAppSettingState = loadingState.get();
        if (FetchAppSettingState.NOT_LOADED != fetchAppSettingState && FetchAppSettingState.LOADING != fetchAppSettingState) {
            final FetchedAppSettings fetchedAppSettings2 = fetchedAppSettings.get(FacebookSdk.getApplicationId());
            Handler handler = new Handler(Looper.getMainLooper());
            if (FetchAppSettingState.ERROR == fetchAppSettingState) {
                while (true) {
                    ConcurrentLinkedQueue<FetchedAppSettingsCallback> concurrentLinkedQueue = fetchedAppSettingsCallbacks;
                    if (concurrentLinkedQueue.isEmpty()) {
                        return;
                    }
                    final FetchedAppSettingsCallback poll = concurrentLinkedQueue.poll();
                    handler.post(new Runnable() { // from class: com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            FetchedAppSettingsManager.FetchedAppSettingsCallback.this.onError();
                        }
                    });
                }
            } else {
                while (true) {
                    ConcurrentLinkedQueue<FetchedAppSettingsCallback> concurrentLinkedQueue2 = fetchedAppSettingsCallbacks;
                    if (concurrentLinkedQueue2.isEmpty()) {
                        return;
                    }
                    final FetchedAppSettingsCallback poll2 = concurrentLinkedQueue2.poll();
                    handler.post(new Runnable() { // from class: com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            FetchedAppSettingsManager.FetchedAppSettingsCallback.this.onSuccess(fetchedAppSettings2);
                        }
                    });
                }
            }
        }
    }

    @JvmStatic
    public static final Map<String, Boolean> getCachedMigratedAutoLogValuesInAppSettings() {
        JSONObject jSONObject;
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, Arrays.copyOf(new Object[]{applicationId}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        String string = applicationContext.getSharedPreferences(APP_SETTINGS_PREFS_STORE, 0).getString(format, null);
        if (!Utility.isNullOrEmpty(string)) {
            if (string == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            try {
                jSONObject = new JSONObject(string);
            } catch (JSONException e) {
                Utility.logd(Utility.LOG_TAG, e);
                jSONObject = null;
            }
            if (jSONObject != null) {
                return INSTANCE.parseMigratedAutoLogValues(jSONObject);
            }
        }
        return null;
    }

    @JvmStatic
    public static final FetchedAppSettings queryAppSettings(String applicationId, boolean forceRequery) {
        Intrinsics.checkNotNullParameter(applicationId, "applicationId");
        if (!forceRequery) {
            Map<String, FetchedAppSettings> map = fetchedAppSettings;
            if (map.containsKey(applicationId)) {
                return map.get(applicationId);
            }
        }
        FetchedAppSettingsManager fetchedAppSettingsManager = INSTANCE;
        FetchedAppSettings parseAppSettingsFromJSON$facebook_core_release = fetchedAppSettingsManager.parseAppSettingsFromJSON$facebook_core_release(applicationId, fetchedAppSettingsManager.getAppSettingsQueryResponse(applicationId));
        if (Intrinsics.areEqual(applicationId, FacebookSdk.getApplicationId())) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            fetchedAppSettingsManager.pollCallbacks();
        }
        return parseAppSettingsFromJSON$facebook_core_release;
    }

    public final FetchedAppSettings parseAppSettingsFromJSON$facebook_core_release(String applicationId, JSONObject settingsJSON) {
        Intrinsics.checkNotNullParameter(applicationId, "applicationId");
        Intrinsics.checkNotNullParameter(settingsJSON, "settingsJSON");
        FacebookRequestErrorClassification createFromJSON = FacebookRequestErrorClassification.INSTANCE.createFromJSON(settingsJSON.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES));
        if (createFromJSON == null) {
            createFromJSON = FacebookRequestErrorClassification.INSTANCE.getDefaultErrorClassification();
        }
        FacebookRequestErrorClassification facebookRequestErrorClassification = createFromJSON;
        int optInt = settingsJSON.optInt(APP_SETTING_APP_EVENTS_FEATURE_BITMASK, 0);
        boolean z = (optInt & 8) != 0;
        boolean z2 = (optInt & 16) != 0;
        boolean z3 = (optInt & 32) != 0;
        boolean z4 = (optInt & 256) != 0;
        boolean z5 = (optInt & 16384) != 0;
        JSONArray optJSONArray = settingsJSON.optJSONArray(APP_SETTING_APP_EVENTS_EVENT_BINDINGS);
        unityEventBindings = optJSONArray;
        if (optJSONArray != null && InternalSettings.isUnityApp()) {
            UnityReflection.sendEventMapping(optJSONArray != null ? optJSONArray.toString() : null);
        }
        JSONObject optJSONObject = settingsJSON.optJSONObject(APP_SETTING_APP_EVENTS_CONFIG);
        boolean optBoolean = settingsJSON.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false);
        String optString = settingsJSON.optString(APP_SETTING_NUX_CONTENT, "");
        Intrinsics.checkNotNullExpressionValue(optString, "settingsJSON.optString(A…_SETTING_NUX_CONTENT, \"\")");
        boolean optBoolean2 = settingsJSON.optBoolean(APP_SETTING_NUX_ENABLED, false);
        int optInt2 = settingsJSON.optInt(APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, Constants.getDefaultAppEventsSessionTimeoutInSeconds());
        EnumSet<SmartLoginOption> parseOptions = SmartLoginOption.INSTANCE.parseOptions(settingsJSON.optLong(APP_SETTING_SMART_LOGIN_OPTIONS));
        Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations = parseDialogConfigurations(settingsJSON.optJSONObject(APP_SETTING_DIALOG_CONFIGS));
        String optString2 = settingsJSON.optString(SMART_LOGIN_BOOKMARK_ICON_URL);
        Intrinsics.checkNotNullExpressionValue(optString2, "settingsJSON.optString(S…_LOGIN_BOOKMARK_ICON_URL)");
        String optString3 = settingsJSON.optString(SMART_LOGIN_MENU_ICON_URL);
        Intrinsics.checkNotNullExpressionValue(optString3, "settingsJSON.optString(SMART_LOGIN_MENU_ICON_URL)");
        String optString4 = settingsJSON.optString(SDK_UPDATE_MESSAGE);
        Intrinsics.checkNotNullExpressionValue(optString4, "settingsJSON.optString(SDK_UPDATE_MESSAGE)");
        FetchedAppSettings fetchedAppSettings2 = new FetchedAppSettings(optBoolean, optString, optBoolean2, optInt2, parseOptions, parseDialogConfigurations, z, facebookRequestErrorClassification, optString2, optString3, z2, z3, optJSONArray, optString4, z4, z5, settingsJSON.optString(APP_SETTING_APP_EVENTS_AAM_RULE), settingsJSON.optString(SUGGESTED_EVENTS_SETTING), settingsJSON.optString(APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), STANDARD_PARAMS_KEY), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), MACA_RULES_KEY), parseMigratedAutoLogValues(settingsJSON), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), BLOCKLIST_EVENTS_KEY), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), REDACTED_EVENTS_KEY), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), SENSITIVE_PARAMS_KEY), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), STD_PARAMS_SCHEMA_KEY), parseProtectedModeRules(settingsJSON.optJSONObject(PROTECTED_MODE_RULES), STD_PARAMS_BLOCKED_KEY), parseCurrencyAndValueDedupeParameters(optJSONObject, AppEventsConstants.EVENT_PARAM_CURRENCY), parseCurrencyAndValueDedupeParameters(optJSONObject, AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM), parseDedupeParameters$default(this, optJSONObject, false, 2, null), parseDedupeParameters(optJSONObject, true), parseDedupeWindow(settingsJSON.optJSONObject(APP_SETTING_APP_EVENTS_CONFIG)));
        fetchedAppSettings.put(applicationId, fetchedAppSettings2);
        return fetchedAppSettings2;
    }

    @JvmStatic
    public static final void setIsUnityInit(boolean flag) {
        isUnityInit = flag;
        JSONArray jSONArray = unityEventBindings;
        if (jSONArray == null || !flag) {
            return;
        }
        UnityReflection.sendEventMapping(String.valueOf(jSONArray));
    }

    private final JSONObject getAppSettingsQueryResponse(String applicationId) {
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(APP_SETTING_FIELDS);
        bundle.putString("fields", TextUtils.join(",", arrayList));
        GraphRequest newGraphPathRequest = GraphRequest.INSTANCE.newGraphPathRequest(null, App.TYPE, null);
        newGraphPathRequest.setForceApplicationRequest(true);
        newGraphPathRequest.setParameters(bundle);
        JSONObject jsonObject = newGraphPathRequest.executeAndWait().getJsonObject();
        return jsonObject == null ? new JSONObject() : jsonObject;
    }

    private final Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations(JSONObject dialogConfigResponse) {
        JSONArray optJSONArray;
        HashMap hashMap = new HashMap();
        if (dialogConfigResponse != null && (optJSONArray = dialogConfigResponse.optJSONArray("data")) != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                FetchedAppSettings.DialogFeatureConfig.Companion companion = FetchedAppSettings.DialogFeatureConfig.INSTANCE;
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(optJSONObject, "dialogConfigData.optJSONObject(i)");
                FetchedAppSettings.DialogFeatureConfig parseDialogConfig = companion.parseDialogConfig(optJSONObject);
                if (parseDialogConfig != null) {
                    String dialogName = parseDialogConfig.getDialogName();
                    HashMap hashMap2 = (Map) hashMap.get(dialogName);
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap();
                        hashMap.put(dialogName, hashMap2);
                    }
                    hashMap2.put(parseDialogConfig.getFeatureName(), parseDialogConfig);
                }
            }
        }
        return hashMap;
    }

    private final Long parseDedupeWindow(JSONObject parameters) {
        if (parameters == null) {
            return null;
        }
        try {
            return Long.valueOf(parameters.optLong(DEDUPE_WINDOW));
        } catch (Exception unused) {
            return null;
        }
    }

    private final JSONArray parseProtectedModeRules(JSONObject protectedModeSettings, String ruleType) {
        if (protectedModeSettings != null) {
            return protectedModeSettings.optJSONArray(ruleType);
        }
        return null;
    }

    private final List<String> parseCurrencyAndValueDedupeParameters(JSONObject originalJSON, String key) {
        JSONArray jSONArray;
        if (originalJSON != null) {
            try {
                jSONArray = originalJSON.getJSONArray(DEDUPE_KEY);
            } catch (Exception unused) {
            }
        } else {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (Intrinsics.areEqual(jSONObject.getString("key"), PROD_DEDUPE_KEY)) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("value");
                int length2 = jSONArray2.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                    if (Intrinsics.areEqual(jSONObject2.getString("key"), key)) {
                        JSONArray jSONArray3 = jSONObject2.getJSONArray("value");
                        ArrayList arrayList = new ArrayList();
                        int length3 = jSONArray3.length();
                        for (int i3 = 0; i3 < length3; i3++) {
                            arrayList.add(jSONArray3.getJSONObject(i3).getString("value"));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.addAll(arrayList);
                        return arrayList2;
                    }
                }
            }
        }
        return null;
    }

    static /* synthetic */ ArrayList parseDedupeParameters$default(FetchedAppSettingsManager fetchedAppSettingsManager, JSONObject jSONObject, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return fetchedAppSettingsManager.parseDedupeParameters(jSONObject, z);
    }

    private final ArrayList<Pair<String, List<String>>> parseDedupeParameters(JSONObject originalJSON, boolean getTestValues) {
        JSONArray jSONArray;
        if (originalJSON != null) {
            try {
                jSONArray = originalJSON.getJSONArray(DEDUPE_KEY);
            } catch (Exception unused) {
                return null;
            }
        } else {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        ArrayList<Pair<String, List<String>>> arrayList = null;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("key");
            if ((!Intrinsics.areEqual(string, PROD_DEDUPE_KEY) || !getTestValues) && (!Intrinsics.areEqual(string, TEST_DEDUPE_KEY) || getTestValues)) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("value");
                int length2 = jSONArray2.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                    String string2 = jSONObject2.getString("key");
                    if (!Intrinsics.areEqual(string2, AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM) && !Intrinsics.areEqual(string2, AppEventsConstants.EVENT_PARAM_CURRENCY)) {
                        JSONArray jSONArray3 = jSONObject2.getJSONArray("value");
                        ArrayList arrayList2 = new ArrayList();
                        int length3 = jSONArray3.length();
                        for (int i3 = 0; i3 < length3; i3++) {
                            arrayList2.add(jSONArray3.getJSONObject(i3).getString("value"));
                        }
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                        }
                        arrayList.add(new Pair<>(string2, arrayList2));
                    }
                }
            }
        }
        return arrayList;
    }

    private final Map<String, Boolean> parseMigratedAutoLogValues(JSONObject settingsJSON) {
        if (settingsJSON == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        if (!settingsJSON.isNull(AUTO_LOG_APP_EVENTS_DEFAULT_FIELD)) {
            try {
                hashMap.put(AUTO_LOG_APP_EVENTS_DEFAULT_FIELD, Boolean.valueOf(settingsJSON.getBoolean(AUTO_LOG_APP_EVENTS_DEFAULT_FIELD)));
            } catch (JSONException e) {
                Utility.logd(Utility.LOG_TAG, e);
            }
        }
        if (!settingsJSON.isNull(AUTO_LOG_APP_EVENT_ENABLED_FIELD)) {
            try {
                hashMap.put(AUTO_LOG_APP_EVENT_ENABLED_FIELD, Boolean.valueOf(settingsJSON.getBoolean(AUTO_LOG_APP_EVENT_ENABLED_FIELD)));
            } catch (JSONException e2) {
                Utility.logd(Utility.LOG_TAG, e2);
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }
}
