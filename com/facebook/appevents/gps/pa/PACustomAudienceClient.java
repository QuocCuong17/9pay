package com.facebook.appevents.gps.pa;

import android.adservices.common.AdData;
import android.adservices.common.AdSelectionSignals;
import android.adservices.common.AdTechIdentifier;
import android.adservices.customaudience.CustomAudience;
import android.adservices.customaudience.CustomAudienceManager;
import android.adservices.customaudience.JoinCustomAudienceRequest;
import android.adservices.customaudience.TrustedBiddingData;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEvent;
import com.facebook.appevents.gps.GpsDebugLogger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import io.flutter.plugins.firebase.analytics.Constants;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PACustomAudienceClient.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0007J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004J\u001c\u0010\u0019\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0003J\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/facebook/appevents/gps/pa/PACustomAudienceClient;", "", "()V", "BUYER", "", "DELIMITER", "EVENT_NAME_CONFIG_VERSION", "GPS_PREFIX", "REPLACEMENT_STRING", "TAG", "baseUri", "customAudienceManager", "Landroid/adservices/customaudience/CustomAudienceManager;", "enabled", "", "gpsDebugLogger", "Lcom/facebook/appevents/gps/GpsDebugLogger;", "isInitialized", "enable", "", "joinCustomAudience", "appId", NotificationCompat.CATEGORY_EVENT, "Lcom/facebook/appevents/AppEvent;", Constants.EVENT_NAME, "joinCustomAudienceImpl", "validateAndCreateCAName", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PACustomAudienceClient {
    private static final String BUYER = "facebook.com";
    private static final String DELIMITER = "@";
    private static final String EVENT_NAME_CONFIG_VERSION = "1";
    private static final String GPS_PREFIX = "gps";
    private static final String REPLACEMENT_STRING = "_removed_";
    private static String baseUri;
    private static CustomAudienceManager customAudienceManager;
    private static boolean enabled;
    private static GpsDebugLogger gpsDebugLogger;
    private static boolean isInitialized;
    public static final PACustomAudienceClient INSTANCE = new PACustomAudienceClient();
    private static final String TAG = "Fledge: PACustomAudienceClient";

    private PACustomAudienceClient() {
    }

    public static final /* synthetic */ GpsDebugLogger access$getGpsDebugLogger$p() {
        if (CrashShieldHandler.isObjectCrashing(PACustomAudienceClient.class)) {
            return null;
        }
        try {
            return gpsDebugLogger;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, PACustomAudienceClient.class);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$p() {
        if (CrashShieldHandler.isObjectCrashing(PACustomAudienceClient.class)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, PACustomAudienceClient.class);
            return null;
        }
    }

    @JvmStatic
    public static final void enable() {
        String exc;
        if (CrashShieldHandler.isObjectCrashing(PACustomAudienceClient.class)) {
            return;
        }
        try {
            isInitialized = true;
            Context applicationContext = FacebookSdk.getApplicationContext();
            gpsDebugLogger = new GpsDebugLogger(applicationContext);
            baseUri = "https://www." + FacebookSdk.getFacebookDomain() + "/privacy_sandbox/pa/logic";
            GpsDebugLogger gpsDebugLogger2 = null;
            try {
                CustomAudienceManager customAudienceManager2 = CustomAudienceManager.get(applicationContext);
                customAudienceManager = customAudienceManager2;
                if (customAudienceManager2 != null) {
                    enabled = true;
                }
                exc = null;
            } catch (Error e) {
                exc = e.toString();
                Log.w(TAG, "Failed to get CustomAudienceManager: " + e);
            } catch (Exception e2) {
                exc = e2.toString();
                Log.w(TAG, "Failed to get CustomAudienceManager: " + e2);
            }
            if (enabled) {
                return;
            }
            GpsDebugLogger gpsDebugLogger3 = gpsDebugLogger;
            if (gpsDebugLogger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            } else {
                gpsDebugLogger2 = gpsDebugLogger3;
            }
            Bundle bundle = new Bundle();
            bundle.putString(com.facebook.appevents.internal.Constants.GPS_PA_FAILED_REASON, exc);
            Unit unit = Unit.INSTANCE;
            gpsDebugLogger2.log(com.facebook.appevents.internal.Constants.GPS_PA_FAILED, bundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, PACustomAudienceClient.class);
        }
    }

    public final void joinCustomAudience(String appId, String eventName) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (!isInitialized) {
                enable();
            }
            if (enabled) {
                joinCustomAudienceImpl(appId, eventName);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void joinCustomAudience(String appId, AppEvent event) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (!isInitialized) {
                enable();
            }
            if (enabled) {
                String str = null;
                if (event != null) {
                    try {
                        JSONObject jsonObject = event.getJsonObject();
                        if (jsonObject != null) {
                            str = jsonObject.getString(com.facebook.appevents.internal.Constants.EVENT_NAME_EVENT_KEY);
                        }
                    } catch (JSONException unused) {
                        Log.w(TAG, "Failed to get event name from event.");
                    }
                }
                joinCustomAudienceImpl(appId, str);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    private final void joinCustomAudienceImpl(String appId, String eventName) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            String validateAndCreateCAName = validateAndCreateCAName(appId, eventName);
            if (validateAndCreateCAName == null) {
                return;
            }
            GpsDebugLogger gpsDebugLogger2 = null;
            try {
                try {
                    OutcomeReceiver<Object, Exception> outcomeReceiver = new OutcomeReceiver<Object, Exception>() { // from class: com.facebook.appevents.gps.pa.PACustomAudienceClient$joinCustomAudienceImpl$callback$1
                        @Override // android.os.OutcomeReceiver
                        public void onResult(Object result) {
                            Intrinsics.checkNotNullParameter(result, "result");
                            Log.i(PACustomAudienceClient.access$getTAG$p(), "Successfully joined custom audience");
                            GpsDebugLogger access$getGpsDebugLogger$p = PACustomAudienceClient.access$getGpsDebugLogger$p();
                            if (access$getGpsDebugLogger$p == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                                access$getGpsDebugLogger$p = null;
                            }
                            access$getGpsDebugLogger$p.log(com.facebook.appevents.internal.Constants.GPS_PA_SUCCEED, null);
                        }

                        @Override // android.os.OutcomeReceiver
                        public void onError(Exception error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            Log.e(PACustomAudienceClient.access$getTAG$p(), error.toString());
                            GpsDebugLogger access$getGpsDebugLogger$p = PACustomAudienceClient.access$getGpsDebugLogger$p();
                            if (access$getGpsDebugLogger$p == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                                access$getGpsDebugLogger$p = null;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString(com.facebook.appevents.internal.Constants.GPS_PA_FAILED_REASON, error.toString());
                            Unit unit = Unit.INSTANCE;
                            access$getGpsDebugLogger$p.log(com.facebook.appevents.internal.Constants.GPS_PA_FAILED, bundle);
                        }
                    };
                    AdData.Builder builder = new AdData.Builder();
                    StringBuilder sb = new StringBuilder();
                    String str = baseUri;
                    if (str == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str = null;
                    }
                    sb.append(str);
                    sb.append("/ad");
                    Uri parse = Uri.parse(sb.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
                    AdData build = builder.setRenderUri(parse).setMetadata("{'isRealAd': false}").build();
                    Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
                    TrustedBiddingData.Builder builder2 = new TrustedBiddingData.Builder();
                    StringBuilder sb2 = new StringBuilder();
                    String str2 = baseUri;
                    if (str2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str2 = null;
                    }
                    sb2.append(str2);
                    sb2.append("?trusted_bidding");
                    Uri parse2 = Uri.parse(sb2.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse2, "Uri.parse(this)");
                    TrustedBiddingData build2 = builder2.setTrustedBiddingUri(parse2).setTrustedBiddingKeys(CollectionsKt.listOf("")).build();
                    Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n              …\n                .build()");
                    CustomAudience.Builder buyer = new CustomAudience.Builder().setName(validateAndCreateCAName).setBuyer(AdTechIdentifier.fromString("facebook.com"));
                    StringBuilder sb3 = new StringBuilder();
                    String str3 = baseUri;
                    if (str3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str3 = null;
                    }
                    sb3.append(str3);
                    sb3.append("?daily&app_id=");
                    sb3.append(appId);
                    Uri parse3 = Uri.parse(sb3.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse3, "Uri.parse(this)");
                    CustomAudience.Builder dailyUpdateUri = buyer.setDailyUpdateUri(parse3);
                    StringBuilder sb4 = new StringBuilder();
                    String str4 = baseUri;
                    if (str4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str4 = null;
                    }
                    sb4.append(str4);
                    sb4.append("?bidding");
                    Uri parse4 = Uri.parse(sb4.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse4, "Uri.parse(this)");
                    CustomAudience build3 = dailyUpdateUri.setBiddingLogicUri(parse4).setTrustedBiddingData(build2).setUserBiddingSignals(AdSelectionSignals.fromString("{}")).setAds(CollectionsKt.listOf(build)).build();
                    Intrinsics.checkNotNullExpressionValue(build3, "Builder()\n              …(listOf(dummyAd)).build()");
                    JoinCustomAudienceRequest build4 = new JoinCustomAudienceRequest.Builder().setCustomAudience(build3).build();
                    Intrinsics.checkNotNullExpressionValue(build4, "Builder().setCustomAudience(ca).build()");
                    CustomAudienceManager customAudienceManager2 = customAudienceManager;
                    if (customAudienceManager2 != null) {
                        customAudienceManager2.joinCustomAudience(build4, Executors.newSingleThreadExecutor(), outcomeReceiver);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Failed to join Custom Audience: " + e);
                    GpsDebugLogger gpsDebugLogger3 = gpsDebugLogger;
                    if (gpsDebugLogger3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                    } else {
                        gpsDebugLogger2 = gpsDebugLogger3;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString(com.facebook.appevents.internal.Constants.GPS_PA_FAILED_REASON, e.toString());
                    Unit unit = Unit.INSTANCE;
                    gpsDebugLogger2.log(com.facebook.appevents.internal.Constants.GPS_PA_FAILED, bundle);
                }
            } catch (Error e2) {
                Log.w(TAG, "Failed to join Custom Audience: " + e2);
                GpsDebugLogger gpsDebugLogger4 = gpsDebugLogger;
                if (gpsDebugLogger4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                } else {
                    gpsDebugLogger2 = gpsDebugLogger4;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString(com.facebook.appevents.internal.Constants.GPS_PA_FAILED_REASON, e2.toString());
                Unit unit2 = Unit.INSTANCE;
                gpsDebugLogger2.log(com.facebook.appevents.internal.Constants.GPS_PA_FAILED, bundle2);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    private final String validateAndCreateCAName(String appId, String eventName) {
        if (!CrashShieldHandler.isObjectCrashing(this) && appId != null && eventName != null) {
            try {
                if (!Intrinsics.areEqual(eventName, REPLACEMENT_STRING) && !StringsKt.contains$default((CharSequence) eventName, (CharSequence) GPS_PREFIX, false, 2, (Object) null)) {
                    return appId + '@' + eventName + '@' + (System.currentTimeMillis() / 1000) + "@1";
                }
                return null;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
        return null;
    }
}
