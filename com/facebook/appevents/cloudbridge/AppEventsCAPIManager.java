package com.facebook.appevents.cloudbridge;

import android.content.SharedPreferences;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AppEventsCAPIManager.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0007J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR@\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e8A@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsCAPIManager;", "", "()V", "SETTINGS_PATH", "", "TAG", "kotlin.jvm.PlatformType", "isEnabled", "", "isEnabled$facebook_core_release", "()Z", "setEnabled$facebook_core_release", "(Z)V", "valuesToSave", "", "savedCloudBridgeCredentials", "getSavedCloudBridgeCredentials$facebook_core_release", "()Ljava/util/Map;", "setSavedCloudBridgeCredentials$facebook_core_release", "(Ljava/util/Map;)V", "enable", "", "getCAPIGSettingsFromGraphResponse", "response", "Lcom/facebook/GraphResponse;", "getCAPIGSettingsFromGraphResponse$facebook_core_release", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppEventsCAPIManager {
    private static final String SETTINGS_PATH = "/cloudbridge_settings";
    private static boolean isEnabled;
    public static final AppEventsCAPIManager INSTANCE = new AppEventsCAPIManager();
    private static final String TAG = AppEventsCAPIManager.class.getCanonicalName();

    private AppEventsCAPIManager() {
    }

    public final boolean isEnabled$facebook_core_release() {
        return isEnabled;
    }

    public final void setEnabled$facebook_core_release(boolean z) {
        isEnabled = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048 A[Catch: all -> 0x00a8, TryCatch #0 {all -> 0x00a8, blocks: (B:6:0x000a, B:9:0x0018, B:11:0x003c, B:16:0x0048, B:18:0x004d, B:23:0x0059, B:25:0x005e, B:31:0x006b), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0059 A[Catch: all -> 0x00a8, TryCatch #0 {all -> 0x00a8, blocks: (B:6:0x000a, B:9:0x0018, B:11:0x003c, B:16:0x0048, B:18:0x004d, B:23:0x0059, B:25:0x005e, B:31:0x006b), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b A[Catch: all -> 0x00a8, TRY_LEAVE, TryCatch #0 {all -> 0x00a8, blocks: (B:6:0x000a, B:9:0x0018, B:11:0x003c, B:16:0x0048, B:18:0x004d, B:23:0x0059, B:25:0x005e, B:31:0x006b), top: B:5:0x000a }] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Map<String, Object> getSavedCloudBridgeCredentials$facebook_core_release() {
        boolean z;
        boolean z2;
        boolean z3;
        if (CrashShieldHandler.isObjectCrashing(AppEventsCAPIManager.class)) {
            return null;
        }
        try {
            SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(FacebookSdk.CLOUDBRIDGE_SAVED_CREDENTIALS, 0);
            if (sharedPreferences == null) {
                return null;
            }
            String string = sharedPreferences.getString(SettingsAPIFields.DATASETID.getRawValue(), null);
            String string2 = sharedPreferences.getString(SettingsAPIFields.URL.getRawValue(), null);
            String string3 = sharedPreferences.getString(SettingsAPIFields.ACCESSKEY.getRawValue(), null);
            String str = string;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
                if (!z) {
                    String str2 = string2;
                    if (str2 != null && !StringsKt.isBlank(str2)) {
                        z2 = false;
                        if (!z2) {
                            String str3 = string3;
                            if (str3 != null && !StringsKt.isBlank(str3)) {
                                z3 = false;
                                if (z3) {
                                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                                    linkedHashMap.put(SettingsAPIFields.URL.getRawValue(), string2);
                                    linkedHashMap.put(SettingsAPIFields.DATASETID.getRawValue(), string);
                                    linkedHashMap.put(SettingsAPIFields.ACCESSKEY.getRawValue(), string3);
                                    Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG.toString(), " \n\nLoading Cloudbridge settings from saved Prefs: \n================\n DATASETID: %s\n URL: %s \n ACCESSKEY: %s \n\n ", string, string2, string3);
                                    return linkedHashMap;
                                }
                            }
                            z3 = true;
                            if (z3) {
                            }
                        }
                    }
                    z2 = true;
                    if (!z2) {
                    }
                }
                return null;
            }
            z = true;
            if (!z) {
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsCAPIManager.class);
            return null;
        }
    }

    public final void setSavedCloudBridgeCredentials$facebook_core_release(Map<String, ? extends Object> map) {
        SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(FacebookSdk.CLOUDBRIDGE_SAVED_CREDENTIALS, 0);
        if (sharedPreferences == null) {
            return;
        }
        if (map == null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.apply();
            return;
        }
        Object obj = map.get(SettingsAPIFields.DATASETID.getRawValue());
        Object obj2 = map.get(SettingsAPIFields.URL.getRawValue());
        Object obj3 = map.get(SettingsAPIFields.ACCESSKEY.getRawValue());
        if (obj == null || obj2 == null || obj3 == null) {
            return;
        }
        SharedPreferences.Editor edit2 = sharedPreferences.edit();
        edit2.putString(SettingsAPIFields.DATASETID.getRawValue(), obj.toString());
        edit2.putString(SettingsAPIFields.URL.getRawValue(), obj2.toString());
        edit2.putString(SettingsAPIFields.ACCESSKEY.getRawValue(), obj3.toString());
        edit2.apply();
        Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG.toString(), " \n\nSaving Cloudbridge settings from saved Prefs: \n================\n DATASETID: %s\n URL: %s \n ACCESSKEY: %s \n\n ", obj, obj2, obj3);
    }

    @JvmStatic
    public static final void enable() {
        try {
            GraphRequest graphRequest = new GraphRequest(null, FacebookSdk.getApplicationId() + SETTINGS_PATH, null, HttpMethod.GET, new GraphRequest.Callback() { // from class: com.facebook.appevents.cloudbridge.AppEventsCAPIManager$$ExternalSyntheticLambda0
                @Override // com.facebook.GraphRequest.Callback
                public final void onCompleted(GraphResponse graphResponse) {
                    AppEventsCAPIManager.enable$lambda$0(graphResponse);
                }
            }, null, 32, null);
            Logger.Companion companion = Logger.INSTANCE;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            companion.log(loggingBehavior, str, " \n\nCreating Graph Request: \n=============\n%s\n\n ", graphRequest);
            graphRequest.executeAsync();
        } catch (JSONException e) {
            Logger.Companion companion2 = Logger.INSTANCE;
            LoggingBehavior loggingBehavior2 = LoggingBehavior.APP_EVENTS;
            String str2 = TAG;
            Intrinsics.checkNotNull(str2, "null cannot be cast to non-null type kotlin.String");
            companion2.log(loggingBehavior2, str2, " \n\nGraph Request Exception: \n=============\n%s\n\n ", ExceptionsKt.stackTraceToString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enable$lambda$0(GraphResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        INSTANCE.getCAPIGSettingsFromGraphResponse$facebook_core_release(response);
    }

    public final void getCAPIGSettingsFromGraphResponse$facebook_core_release(GraphResponse response) {
        Object obj;
        Intrinsics.checkNotNullParameter(response, "response");
        boolean z = false;
        if (response.getError() != null) {
            Logger.Companion companion = Logger.INSTANCE;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            companion.log(loggingBehavior, str, " \n\nGraph Response Error: \n================\nResponse Error: %s\nResponse Error Exception: %s\n\n ", response.getError().toString(), String.valueOf(response.getError().getException()));
            Map<String, Object> savedCloudBridgeCredentials$facebook_core_release = getSavedCloudBridgeCredentials$facebook_core_release();
            if (savedCloudBridgeCredentials$facebook_core_release != null) {
                URL url = new URL(String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.URL.getRawValue())));
                AppEventsConversionsAPITransformerWebRequests.configure(String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.DATASETID.getRawValue())), url.getProtocol() + "://" + url.getHost(), String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.ACCESSKEY.getRawValue())));
                isEnabled = true;
                return;
            }
            return;
        }
        Logger.Companion companion2 = Logger.INSTANCE;
        LoggingBehavior loggingBehavior2 = LoggingBehavior.APP_EVENTS;
        String TAG2 = TAG;
        Intrinsics.checkNotNull(TAG2, "null cannot be cast to non-null type kotlin.String");
        companion2.log(loggingBehavior2, TAG2, " \n\nGraph Response Received: \n================\n%s\n\n ", response);
        JSONObject graphObject = response.getGraphObject();
        if (graphObject != null) {
            try {
                obj = graphObject.get("data");
            } catch (NullPointerException e) {
                Logger.Companion companion3 = Logger.INSTANCE;
                LoggingBehavior loggingBehavior3 = LoggingBehavior.APP_EVENTS;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                companion3.log(loggingBehavior3, TAG3, "CloudBridge Settings API response is not a valid json: \n%s ", ExceptionsKt.stackTraceToString(e));
                return;
            } catch (JSONException e2) {
                Logger.Companion companion4 = Logger.INSTANCE;
                LoggingBehavior loggingBehavior4 = LoggingBehavior.APP_EVENTS;
                String TAG4 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG4, "TAG");
                companion4.log(loggingBehavior4, TAG4, "CloudBridge Settings API response is not a valid json: \n%s ", ExceptionsKt.stackTraceToString(e2));
                return;
            }
        } else {
            obj = null;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONArray");
        Map<String, ? extends Object> convertJSONObjectToHashMap = Utility.convertJSONObjectToHashMap(new JSONObject((String) CollectionsKt.firstOrNull((List) Utility.convertJSONArrayToList((JSONArray) obj))));
        String str2 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.URL.getRawValue());
        String str3 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.DATASETID.getRawValue());
        String str4 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.ACCESSKEY.getRawValue());
        if (str2 == null || str3 == null || str4 == null) {
            Logger.Companion companion5 = Logger.INSTANCE;
            LoggingBehavior loggingBehavior5 = LoggingBehavior.APP_EVENTS;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            companion5.log(loggingBehavior5, TAG2, "CloudBridge Settings API response doesn't have valid data");
            return;
        }
        try {
            AppEventsConversionsAPITransformerWebRequests.configure(str3, str2, str4);
            setSavedCloudBridgeCredentials$facebook_core_release(convertJSONObjectToHashMap);
            if (convertJSONObjectToHashMap.get(SettingsAPIFields.ENABLED.getRawValue()) != null) {
                Object obj2 = convertJSONObjectToHashMap.get(SettingsAPIFields.ENABLED.getRawValue());
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                z = ((Boolean) obj2).booleanValue();
            }
            isEnabled = z;
        } catch (MalformedURLException e3) {
            Logger.Companion companion6 = Logger.INSTANCE;
            LoggingBehavior loggingBehavior6 = LoggingBehavior.APP_EVENTS;
            String TAG5 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG5, "TAG");
            companion6.log(loggingBehavior6, TAG5, "CloudBridge Settings API response doesn't have valid url\n %s ", ExceptionsKt.stackTraceToString(e3));
        }
    }
}
