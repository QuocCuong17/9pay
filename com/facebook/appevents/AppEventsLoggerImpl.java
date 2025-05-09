package com.facebook.appevents;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.MimeTypes;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.UserSettingsManager;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsLoggerImpl;
import com.facebook.appevents.gps.ara.GpsAraTriggersManager;
import com.facebook.appevents.gps.pa.PACustomAudienceClient;
import com.facebook.appevents.iap.InAppPurchase;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.appevents.iap.InAppPurchaseDedupeConfig;
import com.facebook.appevents.iap.InAppPurchaseManager;
import com.facebook.appevents.integrity.BannedParamManager;
import com.facebook.appevents.integrity.BlocklistEventsManager;
import com.facebook.appevents.integrity.MACARuleMatchingManager;
import com.facebook.appevents.integrity.ProtectedModeManager;
import com.facebook.appevents.integrity.SensitiveParamsManager;
import com.facebook.appevents.integrity.StdParamsEnforcementManager;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InstallReferrerUtil;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.flutter.plugins.firebase.analytics.Constants;
import io.sentry.protocol.Device;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AppEventsLoggerImpl.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u0000 <2\u00020\u0001:\u0001<B%\b\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB#\b\u0000\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005J\u001c\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0018\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u0019J\"\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017JG\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0005J:\u0010\"\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ)\u0010\"\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010'J\u0088\u0001\u0010(\u001a\u00020\u00112\b\u0010)\u001a\u0004\u0018\u00010\u00052\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010\u00052\b\u0010/\u001a\u0004\u0018\u00010\u00052\b\u00100\u001a\u0004\u0018\u00010\u00052\b\u00101\u001a\u0004\u0018\u00010\u00052\b\u00102\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u00103\u001a\u0004\u0018\u00010\u00052\b\u00104\u001a\u0004\u0018\u00010\u00052\b\u00105\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u001a\u00106\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&J&\u00106\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017J8\u00106\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00132\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ0\u00107\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0018\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00172\b\u0010:\u001a\u0004\u0018\u00010\u0005J'\u0010;\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010'R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/facebook/appevents/AppEventsLoggerImpl;", "", "context", "Landroid/content/Context;", "applicationId", "", "accessToken", "Lcom/facebook/AccessToken;", "(Landroid/content/Context;Ljava/lang/String;Lcom/facebook/AccessToken;)V", "activityName", "(Ljava/lang/String;Ljava/lang/String;Lcom/facebook/AccessToken;)V", "accessTokenAppId", "Lcom/facebook/appevents/AccessTokenAppIdPair;", "getApplicationId", "()Ljava/lang/String;", "contextName", "flush", "", "isValidForAccessToken", "", "logEvent", Constants.EVENT_NAME, "parameters", "Landroid/os/Bundle;", "valueToSum", "", "isImplicitlyLogged", "currentSessionId", "Ljava/util/UUID;", "operationalData", "Lcom/facebook/appevents/OperationalData;", "(Ljava/lang/String;Ljava/lang/Double;Landroid/os/Bundle;ZLjava/util/UUID;Lcom/facebook/appevents/OperationalData;)V", "logEventFromSE", "buttonText", "logEventImplicitly", "purchaseAmount", "Ljava/math/BigDecimal;", FirebaseAnalytics.Param.CURRENCY, "Ljava/util/Currency;", "(Ljava/lang/String;Ljava/lang/Double;Landroid/os/Bundle;)V", "logProductItem", "itemID", "availability", "Lcom/facebook/appevents/AppEventsLogger$ProductAvailability;", AnalyticsLogger.Keys.CONDITION, "Lcom/facebook/appevents/AppEventsLogger$ProductCondition;", "description", "imageLink", "link", "title", "priceAmount", "gtin", "mpn", Device.JsonKeys.BRAND, "logPurchase", "logPurchaseImplicitly", "logPushNotificationOpen", "payload", "action", "logSdkEvent", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppEventsLoggerImpl {
    private static final String ACCOUNT_KIT_EVENT_NAME_PREFIX = "fb_ak";
    public static final String APP_EVENTS_KILLSWITCH = "app_events_killswitch";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    private static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    private static final String TAG;
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static AppEventsLogger.FlushBehavior flushBehaviorField;
    private static boolean isActivateAppEventRequested;
    private static String pushNotificationsRegistrationIdField;
    private static final Object staticLock;
    private AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    @JvmStatic
    public static final void activateApp(Application application, String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.activateApp(application, str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final Pair<Bundle, OperationalData> addImplicitPurchaseParameters(Bundle bundle, OperationalData operationalData, boolean z) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return INSTANCE.addImplicitPurchaseParameters(bundle, operationalData, z);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    @JvmStatic
    public static final void augmentWebView(WebView webView, Context context) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.augmentWebView(webView, context);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final void functionDEPRECATED(String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.functionDEPRECATED(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final Executor getAnalyticsExecutor() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return INSTANCE.getAnalyticsExecutor();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    @JvmStatic
    public static final String getAnonymousAppDeviceGUID(Context context) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return INSTANCE.getAnonymousAppDeviceGUID(context);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    @JvmStatic
    public static final AppEventsLogger.FlushBehavior getFlushBehavior() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return INSTANCE.getFlushBehavior();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    @JvmStatic
    public static final String getInstallReferrer() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return INSTANCE.getInstallReferrer();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    @JvmStatic
    public static final String getPushNotificationsRegistrationId() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return INSTANCE.getPushNotificationsRegistrationId();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    @JvmStatic
    public static final void initializeLib(Context context, String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.initializeLib(context, str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final void onContextStop() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.onContextStop();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final void setFlushBehavior(AppEventsLogger.FlushBehavior flushBehavior) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.setFlushBehavior(flushBehavior);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final void setInstallReferrer(String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.setInstallReferrer(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    @JvmStatic
    public static final void setPushNotificationsRegistrationId(String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            INSTANCE.setPushNotificationsRegistrationId(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public AppEventsLoggerImpl(String activityName, String str, AccessToken accessToken) {
        Intrinsics.checkNotNullParameter(activityName, "activityName");
        Validate.sdkInitialized();
        this.contextName = activityName;
        accessToken = accessToken == null ? AccessToken.INSTANCE.getCurrentAccessToken() : accessToken;
        if (accessToken != null && !accessToken.isExpired() && (str == null || Intrinsics.areEqual(str, accessToken.getApplicationId()))) {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        } else {
            str = str == null ? Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext()) : str;
            if (str != null) {
                this.accessTokenAppId = new AccessTokenAppIdPair(null, str);
            } else {
                throw new IllegalStateException("Required value was null.".toString());
            }
        }
        INSTANCE.initializeTimersIfNeeded();
    }

    public static final /* synthetic */ String access$getAnonymousAppDeviceGUID$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return anonymousAppDeviceGUID;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    public static final /* synthetic */ ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return backgroundExecutor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    public static final /* synthetic */ AppEventsLogger.FlushBehavior access$getFlushBehaviorField$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return flushBehaviorField;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    public static final /* synthetic */ String access$getPushNotificationsRegistrationIdField$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return pushNotificationsRegistrationIdField;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    public static final /* synthetic */ Object access$getStaticLock$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return staticLock;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return null;
        }
    }

    public static final /* synthetic */ boolean access$isActivateAppEventRequested$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return false;
        }
        try {
            return isActivateAppEventRequested;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
            return false;
        }
    }

    public static final /* synthetic */ void access$setActivateAppEventRequested$cp(boolean z) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            isActivateAppEventRequested = z;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public static final /* synthetic */ void access$setAnonymousAppDeviceGUID$cp(String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            anonymousAppDeviceGUID = str;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public static final /* synthetic */ void access$setBackgroundExecutor$cp(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            backgroundExecutor = scheduledThreadPoolExecutor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public static final /* synthetic */ void access$setFlushBehaviorField$cp(AppEventsLogger.FlushBehavior flushBehavior) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            flushBehaviorField = flushBehavior;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public static final /* synthetic */ void access$setPushNotificationsRegistrationIdField$cp(String str) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        try {
            pushNotificationsRegistrationIdField = str;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public AppEventsLoggerImpl(Context context, String str, AccessToken accessToken) {
        this(Utility.getActivityName(context), str, accessToken);
    }

    public final void logEvent(String eventName) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logEvent(eventName, (Bundle) null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public static /* synthetic */ void logEvent$default(AppEventsLoggerImpl appEventsLoggerImpl, String str, Bundle bundle, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        if ((i & 2) != 0) {
            bundle = null;
        }
        try {
            appEventsLoggerImpl.logEvent(str, bundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public final void logEvent(String eventName, Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logEvent$default(this, eventName, null, parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid(), null, 32, null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void logEvent(String eventName, double valueToSum) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logEvent(eventName, valueToSum, null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void logEvent(String eventName, double valueToSum, Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logEvent$default(this, eventName, Double.valueOf(valueToSum), parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid(), null, 32, null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void logEventFromSE(String eventName, String buttonText) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("_is_suggested_event", "1");
            bundle.putString("_button_text", buttonText);
            logEvent(eventName, bundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void logPurchase(BigDecimal purchaseAmount, Currency currency) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logPurchase(purchaseAmount, currency, null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public static /* synthetic */ void logPurchase$default(AppEventsLoggerImpl appEventsLoggerImpl, BigDecimal bigDecimal, Currency currency, Bundle bundle, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        if ((i & 4) != 0) {
            bundle = null;
        }
        try {
            appEventsLoggerImpl.logPurchase(bigDecimal, currency, bundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public final void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logPurchase$default(this, purchaseAmount, currency, parameters, false, null, 16, null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public static /* synthetic */ void logPurchaseImplicitly$default(AppEventsLoggerImpl appEventsLoggerImpl, BigDecimal bigDecimal, Currency currency, Bundle bundle, OperationalData operationalData, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        if ((i & 8) != 0) {
            operationalData = null;
        }
        try {
            appEventsLoggerImpl.logPurchaseImplicitly(bigDecimal, currency, bundle, operationalData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public final void logPurchaseImplicitly(BigDecimal purchaseAmount, Currency currency, Bundle parameters, OperationalData operationalData) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logPurchase(purchaseAmount, currency, parameters, true, operationalData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public static /* synthetic */ void logPurchase$default(AppEventsLoggerImpl appEventsLoggerImpl, BigDecimal bigDecimal, Currency currency, Bundle bundle, boolean z, OperationalData operationalData, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        if ((i & 16) != 0) {
            operationalData = null;
        }
        try {
            appEventsLoggerImpl.logPurchase(bigDecimal, currency, bundle, z, operationalData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public final void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters, boolean isImplicitlyLogged, OperationalData operationalData) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (purchaseAmount == null) {
                INSTANCE.notifyDeveloperError("purchaseAmount cannot be null");
                return;
            }
            if (currency != null) {
                if (parameters == null) {
                    parameters = new Bundle();
                }
                Bundle bundle = parameters;
                bundle.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
                logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, Double.valueOf(purchaseAmount.doubleValue()), bundle, isImplicitlyLogged, ActivityLifecycleTracker.getCurrentSessionGuid(), operationalData);
                INSTANCE.eagerFlush();
                return;
            }
            INSTANCE.notifyDeveloperError("currency cannot be null");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void logPushNotificationOpen(Bundle payload, String action) {
        String string;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(payload, "payload");
            String str = null;
            try {
                string = payload.getString(PUSH_PAYLOAD_KEY);
            } catch (JSONException unused) {
            }
            if (Utility.isNullOrEmpty(string)) {
                return;
            }
            str = new JSONObject(string).getString("campaign");
            if (str == null) {
                Logger.INSTANCE.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, str);
            if (action != null) {
                bundle.putString(APP_EVENT_PUSH_PARAMETER_ACTION, action);
            }
            logEvent(APP_EVENT_NAME_PUSH_OPENED, bundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void logProductItem(String itemID, AppEventsLogger.ProductAvailability availability, AppEventsLogger.ProductCondition condition, String description, String imageLink, String link, String title, BigDecimal priceAmount, Currency currency, String gtin, String mpn, String brand, Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (itemID == null) {
                INSTANCE.notifyDeveloperError("itemID cannot be null");
                return;
            }
            if (availability == null) {
                INSTANCE.notifyDeveloperError("availability cannot be null");
                return;
            }
            if (condition == null) {
                INSTANCE.notifyDeveloperError("condition cannot be null");
                return;
            }
            if (description == null) {
                INSTANCE.notifyDeveloperError("description cannot be null");
                return;
            }
            if (imageLink == null) {
                INSTANCE.notifyDeveloperError("imageLink cannot be null");
                return;
            }
            if (link == null) {
                INSTANCE.notifyDeveloperError("link cannot be null");
                return;
            }
            if (title == null) {
                INSTANCE.notifyDeveloperError("title cannot be null");
                return;
            }
            if (priceAmount == null) {
                INSTANCE.notifyDeveloperError("priceAmount cannot be null");
                return;
            }
            if (currency == null) {
                INSTANCE.notifyDeveloperError("currency cannot be null");
                return;
            }
            if (gtin != null || mpn != null || brand != null) {
                if (parameters == null) {
                    parameters = new Bundle();
                }
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_ITEM_ID, itemID);
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_AVAILABILITY, availability.name());
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_CONDITION, condition.name());
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_DESCRIPTION, description);
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_IMAGE_LINK, imageLink);
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_LINK, link);
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_TITLE, title);
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_PRICE_AMOUNT, priceAmount.setScale(3, 4).toString());
                parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_PRICE_CURRENCY, currency.getCurrencyCode());
                if (gtin != null) {
                    parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_GTIN, gtin);
                }
                if (mpn != null) {
                    parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_MPN, mpn);
                }
                if (brand != null) {
                    parameters.putString(com.facebook.appevents.internal.Constants.EVENT_PARAM_PRODUCT_BRAND, brand);
                }
                logEvent(AppEventsConstants.EVENT_NAME_PRODUCT_CATALOG_UPDATE, parameters);
                INSTANCE.eagerFlush();
                return;
            }
            INSTANCE.notifyDeveloperError("Either gtin, mpn or brand is required");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final void flush() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            AppEventQueue.flush(FlushReason.EXPLICIT);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final boolean isValidForAccessToken(AccessToken accessToken) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            return Intrinsics.areEqual(this.accessTokenAppId, new AccessTokenAppIdPair(accessToken));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    public final void logSdkEvent(String eventName, Double valueToSum, Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            if (!StringsKt.startsWith$default(eventName, ACCOUNT_KIT_EVENT_NAME_PREFIX, false, 2, (Object) null)) {
                Log.e(TAG, "logSdkEvent is deprecated and only supports account kit for legacy, please use logEvent instead");
            } else if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                logEvent$default(this, eventName, valueToSum, parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid(), null, 32, null);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final String getApplicationId() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.accessTokenAppId.getApplicationId();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final void logEventImplicitly(String eventName, Double valueToSum, Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            logEvent$default(this, eventName, valueToSum, parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid(), null, 32, null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public static /* synthetic */ void logEventImplicitly$default(AppEventsLoggerImpl appEventsLoggerImpl, String str, BigDecimal bigDecimal, Currency currency, Bundle bundle, OperationalData operationalData, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        if ((i & 16) != 0) {
            operationalData = null;
        }
        try {
            appEventsLoggerImpl.logEventImplicitly(str, bigDecimal, currency, bundle, operationalData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    public final void logEventImplicitly(String eventName, BigDecimal purchaseAmount, Currency currency, Bundle parameters, OperationalData operationalData) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (purchaseAmount == null || currency == null) {
                Utility.logd(TAG, "purchaseAmount and currency cannot be null");
                return;
            }
            if (parameters == null) {
                parameters = new Bundle();
            }
            Bundle bundle = parameters;
            bundle.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(eventName, Double.valueOf(purchaseAmount.doubleValue()), bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid(), operationalData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public static /* synthetic */ void logEvent$default(AppEventsLoggerImpl appEventsLoggerImpl, String str, Double d, Bundle bundle, boolean z, UUID uuid, OperationalData operationalData, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(AppEventsLoggerImpl.class)) {
            return;
        }
        if ((i & 32) != 0) {
            operationalData = null;
        }
        try {
            appEventsLoggerImpl.logEvent(str, d, bundle, z, uuid, operationalData);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppEventsLoggerImpl.class);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c6 A[Catch: all -> 0x0146, TRY_ENTER, TryCatch #2 {all -> 0x0146, blocks: (B:6:0x0013, B:8:0x001a, B:15:0x0029, B:17:0x002f, B:20:0x0039, B:22:0x003f, B:24:0x0045, B:26:0x0054, B:28:0x006e, B:31:0x0080, B:32:0x00b8, B:35:0x00c6, B:37:0x00d4, B:40:0x00db, B:42:0x00ef, B:44:0x00f7, B:45:0x00fa, B:49:0x0121, B:52:0x0134, B:54:0x005a, B:56:0x0062, B:58:0x0068), top: B:5:0x0013, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d4 A[Catch: all -> 0x0146, TryCatch #2 {all -> 0x0146, blocks: (B:6:0x0013, B:8:0x001a, B:15:0x0029, B:17:0x002f, B:20:0x0039, B:22:0x003f, B:24:0x0045, B:26:0x0054, B:28:0x006e, B:31:0x0080, B:32:0x00b8, B:35:0x00c6, B:37:0x00d4, B:40:0x00db, B:42:0x00ef, B:44:0x00f7, B:45:0x00fa, B:49:0x0121, B:52:0x0134, B:54:0x005a, B:56:0x0062, B:58:0x0068), top: B:5:0x0013, inners: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void logEvent(String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, UUID currentSessionId, OperationalData operationalData) {
        boolean z;
        Bundle bundle = parameters;
        OperationalData operationalData2 = operationalData;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            String str = eventName;
            if (str != null && str.length() != 0) {
                z = false;
                if (!z) {
                    return;
                }
                if (!isImplicitlyLogged && AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled() && (Intrinsics.areEqual(eventName, AppEventsConstants.EVENT_NAME_PURCHASED) || Intrinsics.areEqual(eventName, AppEventsConstants.EVENT_NAME_SUBSCRIBE) || Intrinsics.areEqual(eventName, AppEventsConstants.EVENT_NAME_START_TRIAL))) {
                    Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
                    if ((FeatureManager.isEnabled(FeatureManager.Feature.AndroidManualImplicitPurchaseDedupe) && Intrinsics.areEqual(eventName, AppEventsConstants.EVENT_NAME_PURCHASED)) || (FeatureManager.isEnabled(FeatureManager.Feature.AndroidManualImplicitSubsDedupe) && (Intrinsics.areEqual(eventName, AppEventsConstants.EVENT_NAME_SUBSCRIBE) || Intrinsics.areEqual(eventName, AppEventsConstants.EVENT_NAME_START_TRIAL)))) {
                        Double valueOfManualEvent = InAppPurchaseDedupeConfig.INSTANCE.getValueOfManualEvent(valueToSum, bundle);
                        Currency currencyOfManualEvent = InAppPurchaseDedupeConfig.INSTANCE.getCurrencyOfManualEvent(bundle);
                        if (valueOfManualEvent != null && currencyOfManualEvent != null) {
                            Pair<Bundle, OperationalData> addDedupeParameters = InAppPurchaseDedupeConfig.INSTANCE.addDedupeParameters(InAppPurchaseManager.performDedupe(CollectionsKt.listOf(new InAppPurchase(eventName, valueOfManualEvent.doubleValue(), currencyOfManualEvent)), System.currentTimeMillis(), false, CollectionsKt.listOf(new Pair(bundle, operationalData2))), bundle, operationalData2);
                            Bundle component1 = addDedupeParameters.component1();
                            operationalData2 = addDedupeParameters.component2();
                            bundle = component1;
                        }
                        if (!FetchedAppGateKeepersManager.getGateKeeperForKey(APP_EVENTS_KILLSWITCH, FacebookSdk.getApplicationId(), false)) {
                            Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, "AppEvents", "KillSwitch is enabled and fail to log app event: %s", eventName);
                            return;
                        }
                        if (BlocklistEventsManager.isInBlocklist(eventName)) {
                            return;
                        }
                        Companion companion = INSTANCE;
                        Pair<Bundle, OperationalData> addImplicitPurchaseParameters = companion.addImplicitPurchaseParameters(bundle, operationalData2, isImplicitlyLogged);
                        Bundle component12 = addImplicitPurchaseParameters.component1();
                        OperationalData component2 = addImplicitPurchaseParameters.component2();
                        try {
                            if (!ProtectedModeManager.INSTANCE.protectedModeIsApplied(component12)) {
                                SensitiveParamsManager.processFilterSensitiveParams(component12, eventName);
                            }
                            BannedParamManager.processFilterBannedParams(component12);
                            MACARuleMatchingManager.processParameters(component12, eventName);
                            StdParamsEnforcementManager.processFilterParamSchemaBlocking(component12);
                            ProtectedModeManager.processParametersForProtectedMode(component12);
                            companion.logEvent(new AppEvent(this.contextName, eventName, valueToSum, component12, isImplicitlyLogged, ActivityLifecycleTracker.isInBackground(), currentSessionId, component2), this.accessTokenAppId);
                            return;
                        } catch (FacebookException e) {
                            Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e.toString());
                            return;
                        } catch (JSONException e2) {
                            Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e2.toString());
                            return;
                        }
                    }
                }
                if (!FetchedAppGateKeepersManager.getGateKeeperForKey(APP_EVENTS_KILLSWITCH, FacebookSdk.getApplicationId(), false)) {
                }
            }
            z = true;
            if (!z) {
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    /* compiled from: AppEventsLoggerImpl.kt */
    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0007J4\u0010\u001d\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0006\u0012\u0004\u0018\u00010 0\u001e2\b\u0010!\u001a\u0004\u0018\u00010\u001f2\b\u0010\"\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\u0015H\u0007J\u001a\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0007J\u0006\u0010)\u001a\u00020\u0019J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u0004H\u0007J\b\u0010,\u001a\u00020-H\u0007J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010'\u001a\u00020(H\u0007J\b\u0010/\u001a\u00020\u0013H\u0007J\n\u00100\u001a\u0004\u0018\u00010\u0004H\u0007J\n\u00101\u001a\u0004\u0018\u00010\u0004H\u0007J\u001a\u00102\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0007J\b\u00103\u001a\u00020\u0019H\u0002J\u0018\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0002J\u0010\u00109\u001a\u00020\u00192\u0006\u0010:\u001a\u00020\u0004H\u0002J\b\u0010;\u001a\u00020\u0019H\u0007J\u0010\u0010<\u001a\u00020\u00192\u0006\u0010=\u001a\u00020\u0013H\u0007J\u0012\u0010>\u001a\u00020\u00192\b\u0010?\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010@\u001a\u00020\u00192\b\u0010A\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/facebook/appevents/AppEventsLoggerImpl$Companion;", "", "()V", "ACCOUNT_KIT_EVENT_NAME_PREFIX", "", "APP_EVENTS_KILLSWITCH", "APP_EVENT_NAME_PUSH_OPENED", "APP_EVENT_PREFERENCES", "APP_EVENT_PUSH_PARAMETER_ACTION", "APP_EVENT_PUSH_PARAMETER_CAMPAIGN", "APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS", "", "PUSH_PAYLOAD_CAMPAIGN_KEY", "PUSH_PAYLOAD_KEY", "TAG", "anonymousAppDeviceGUID", "backgroundExecutor", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "flushBehaviorField", "Lcom/facebook/appevents/AppEventsLogger$FlushBehavior;", "isActivateAppEventRequested", "", "pushNotificationsRegistrationIdField", "staticLock", "activateApp", "", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "applicationId", "addImplicitPurchaseParameters", "Lkotlin/Pair;", "Landroid/os/Bundle;", "Lcom/facebook/appevents/OperationalData;", "params", "operationalData", "isImplicitlyLogged", "augmentWebView", "webView", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "eagerFlush", "functionDEPRECATED", "extraMsg", "getAnalyticsExecutor", "Ljava/util/concurrent/Executor;", "getAnonymousAppDeviceGUID", "getFlushBehavior", "getInstallReferrer", "getPushNotificationsRegistrationId", "initializeLib", "initializeTimersIfNeeded", "logEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/facebook/appevents/AppEvent;", "accessTokenAppId", "Lcom/facebook/appevents/AccessTokenAppIdPair;", "notifyDeveloperError", "message", "onContextStop", "setFlushBehavior", "flushBehavior", "setInstallReferrer", "referrer", "setPushNotificationsRegistrationId", "registrationId", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AppEventsLogger.FlushBehavior getFlushBehavior() {
            AppEventsLogger.FlushBehavior access$getFlushBehaviorField$cp;
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                access$getFlushBehaviorField$cp = AppEventsLoggerImpl.access$getFlushBehaviorField$cp();
            }
            return access$getFlushBehaviorField$cp;
        }

        @JvmStatic
        public final void setFlushBehavior(AppEventsLogger.FlushBehavior flushBehavior) {
            Intrinsics.checkNotNullParameter(flushBehavior, "flushBehavior");
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                Companion companion = AppEventsLoggerImpl.INSTANCE;
                AppEventsLoggerImpl.access$setFlushBehaviorField$cp(flushBehavior);
                Unit unit = Unit.INSTANCE;
            }
        }

        @JvmStatic
        public final String getPushNotificationsRegistrationId() {
            String access$getPushNotificationsRegistrationIdField$cp;
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                access$getPushNotificationsRegistrationIdField$cp = AppEventsLoggerImpl.access$getPushNotificationsRegistrationIdField$cp();
            }
            return access$getPushNotificationsRegistrationIdField$cp;
        }

        @JvmStatic
        public final void setPushNotificationsRegistrationId(String registrationId) {
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                if (!Utility.stringsEqualOrEmpty(AppEventsLoggerImpl.access$getPushNotificationsRegistrationIdField$cp(), registrationId)) {
                    Companion companion = AppEventsLoggerImpl.INSTANCE;
                    AppEventsLoggerImpl.access$setPushNotificationsRegistrationIdField$cp(registrationId);
                    AppEventsLoggerImpl appEventsLoggerImpl = new AppEventsLoggerImpl(FacebookSdk.getApplicationContext(), (String) null, (AccessToken) null);
                    appEventsLoggerImpl.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                    if (AppEventsLoggerImpl.INSTANCE.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
                        appEventsLoggerImpl.flush();
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        @JvmStatic
        public final void activateApp(Application application, String applicationId) {
            Intrinsics.checkNotNullParameter(application, "application");
            if (!FacebookSdk.isInitialized()) {
                throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
            }
            AnalyticsUserIDStore.initStore();
            UserDataStore.initStore();
            if (applicationId == null) {
                applicationId = FacebookSdk.getApplicationId();
            }
            FacebookSdk.publishInstallAsync(application, applicationId);
            ActivityLifecycleTracker.startTracking(application, applicationId);
            if (FeatureManager.isEnabled(FeatureManager.Feature.GPSPACAProcessing)) {
                PACustomAudienceClient.INSTANCE.joinCustomAudience(applicationId, "fb_mobile_app_install");
            }
        }

        @JvmStatic
        public final Pair<Bundle, OperationalData> addImplicitPurchaseParameters(Bundle params, OperationalData operationalData, boolean isImplicitlyLogged) {
            Pair<Bundle, OperationalData> addParameterAndReturn = OperationalData.INSTANCE.addParameterAndReturn(OperationalDataEnum.IAPParameters, com.facebook.appevents.internal.Constants.EVENT_PARAM_IS_IMPLICIT_PURCHASE_LOGGING_ENABLED, AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled() ? "1" : "0", params, operationalData);
            Object parameter = OperationalData.INSTANCE.getParameter(OperationalDataEnum.IAPParameters, com.facebook.appevents.internal.Constants.IAP_PRODUCT_ID, params, operationalData);
            String str = parameter instanceof String ? (String) parameter : null;
            if (!isImplicitlyLogged) {
                if ((params != null ? params.getString(AppEventsConstants.EVENT_PARAM_CONTENT_ID) : null) == null && str != null) {
                    Pair<Bundle, OperationalData> addParameterAndReturn2 = OperationalData.INSTANCE.addParameterAndReturn(OperationalDataEnum.IAPParameters, AppEventsConstants.EVENT_PARAM_CONTENT_ID, str, params, operationalData);
                    addParameterAndReturn = OperationalData.INSTANCE.addParameterAndReturn(OperationalDataEnum.IAPParameters, com.facebook.appevents.internal.Constants.ANDROID_DYNAMIC_ADS_CONTENT_ID, "client_manual", addParameterAndReturn2.getFirst(), addParameterAndReturn2.getSecond());
                }
            }
            Pair<Bundle, OperationalData> addParameterAndReturn3 = OperationalData.INSTANCE.addParameterAndReturn(OperationalDataEnum.IAPParameters, com.facebook.appevents.internal.Constants.EVENT_PARAM_IS_AUTOLOG_APP_EVENTS_ENABLED, UserSettingsManager.getAutoLogAppEventsEnabled() ? "1" : "0", addParameterAndReturn.getFirst(), addParameterAndReturn.getSecond());
            return new Pair<>(addParameterAndReturn3.getFirst(), addParameterAndReturn3.getSecond());
        }

        @JvmStatic
        public final void functionDEPRECATED(String extraMsg) {
            Intrinsics.checkNotNullParameter(extraMsg, "extraMsg");
            Log.w(AppEventsLoggerImpl.access$getTAG$cp(), "This function is deprecated. " + extraMsg);
        }

        @JvmStatic
        public final void initializeLib(final Context context, String applicationId) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                final AppEventsLoggerImpl appEventsLoggerImpl = new AppEventsLoggerImpl(context, applicationId, (AccessToken) null);
                ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp = AppEventsLoggerImpl.access$getBackgroundExecutor$cp();
                if (access$getBackgroundExecutor$cp == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                access$getBackgroundExecutor$cp.execute(new Runnable() { // from class: com.facebook.appevents.AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppEventsLoggerImpl.Companion.initializeLib$lambda$4(context, appEventsLoggerImpl);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void initializeLib$lambda$4(Context context, AppEventsLoggerImpl logger) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(logger, "$logger");
            Bundle bundle = new Bundle();
            String[] strArr = {"com.facebook.core.Core", "com.facebook.login.Login", "com.facebook.share.Share", "com.facebook.places.Places", "com.facebook.messenger.Messenger", "com.facebook.applinks.AppLinks", "com.facebook.marketing.Marketing", "com.facebook.gamingservices.GamingServices", "com.facebook.all.All", InAppPurchaseConstants.CLASSNAME_BILLING_CLIENT, "com.android.vending.billing.IInAppBillingService"};
            String[] strArr2 = {"core_lib_included", "login_lib_included", "share_lib_included", "places_lib_included", "messenger_lib_included", "applinks_lib_included", "marketing_lib_included", "gamingservices_lib_included", "all_lib_included", "billing_client_lib_included", "billing_service_lib_included"};
            int i = 0;
            for (int i2 = 0; i2 < 11; i2++) {
                String str = strArr[i2];
                String str2 = strArr2[i2];
                try {
                    Class.forName(str);
                    bundle.putInt(str2, 1);
                    i |= 1 << i2;
                } catch (ClassNotFoundException unused) {
                }
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0);
            if (sharedPreferences.getInt("kitsBitmask", 0) != i) {
                sharedPreferences.edit().putInt("kitsBitmask", i).apply();
                logger.logEventImplicitly(AnalyticsEvents.EVENT_SDK_INITIALIZE, null, bundle);
            }
        }

        @JvmStatic
        public final void onContextStop() {
            AppEventQueue.persistToDisk();
        }

        @JvmStatic
        public final String getInstallReferrer() {
            InstallReferrerUtil.tryUpdateReferrerInfo(new InstallReferrerUtil.Callback() { // from class: com.facebook.appevents.AppEventsLoggerImpl$Companion$getInstallReferrer$1
                @Override // com.facebook.internal.InstallReferrerUtil.Callback
                public void onReceiveReferrerUrl(String s) {
                    AppEventsLoggerImpl.INSTANCE.setInstallReferrer(s);
                }
            });
            return FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("install_referrer", null);
        }

        @JvmStatic
        public final void setInstallReferrer(String referrer) {
            SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.appEventPreferences", 0);
            if (referrer != null) {
                sharedPreferences.edit().putString("install_referrer", referrer).apply();
            }
        }

        @JvmStatic
        public final void augmentWebView(WebView webView, Context context) {
            Intrinsics.checkNotNullParameter(webView, "webView");
            String RELEASE = Build.VERSION.RELEASE;
            Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
            String[] strArr = (String[]) StringsKt.split$default((CharSequence) RELEASE, new String[]{"."}, false, 0, 6, (Object) null).toArray(new String[0]);
            int parseInt = (strArr.length == 0) ^ true ? Integer.parseInt(strArr[0]) : 0;
            int parseInt2 = strArr.length > 1 ? Integer.parseInt(strArr[1]) : 0;
            if (Build.VERSION.SDK_INT < 17 || parseInt < 4 || (parseInt == 4 && parseInt2 <= 1)) {
                Logger.INSTANCE.log(LoggingBehavior.DEVELOPER_ERRORS, AppEventsLoggerImpl.access$getTAG$cp(), "augmentWebView is only available for Android SDK version >= 17 on devices running Android >= 4.2");
                return;
            }
            webView.addJavascriptInterface(new FacebookSDKJSInterface(context), "fbmq_" + FacebookSdk.getApplicationId());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initializeTimersIfNeeded() {
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                if (AppEventsLoggerImpl.access$getBackgroundExecutor$cp() != null) {
                    return;
                }
                Companion companion = AppEventsLoggerImpl.INSTANCE;
                AppEventsLoggerImpl.access$setBackgroundExecutor$cp(new ScheduledThreadPoolExecutor(1));
                Unit unit = Unit.INSTANCE;
                AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda1 appEventsLoggerImpl$Companion$$ExternalSyntheticLambda1 = new Runnable() { // from class: com.facebook.appevents.AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppEventsLoggerImpl.Companion.initializeTimersIfNeeded$lambda$6();
                    }
                };
                ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp = AppEventsLoggerImpl.access$getBackgroundExecutor$cp();
                if (access$getBackgroundExecutor$cp == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                access$getBackgroundExecutor$cp.scheduleAtFixedRate(appEventsLoggerImpl$Companion$$ExternalSyntheticLambda1, 0L, 86400L, TimeUnit.SECONDS);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void initializeTimersIfNeeded$lambda$6() {
            HashSet hashSet = new HashSet();
            Iterator<AccessTokenAppIdPair> it = AppEventQueue.getKeySet().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getApplicationId());
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                FetchedAppSettingsManager.queryAppSettings((String) it2.next(), true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void logEvent(AppEvent event, AccessTokenAppIdPair accessTokenAppId) {
            AppEventQueue.add(accessTokenAppId, event);
            if (FeatureManager.isEnabled(FeatureManager.Feature.OnDevicePostInstallEventProcessing) && OnDeviceProcessingManager.isOnDeviceProcessingEnabled()) {
                OnDeviceProcessingManager.sendCustomEventAsync(accessTokenAppId.getApplicationId(), event);
            }
            if (FeatureManager.isEnabled(FeatureManager.Feature.GPSARATriggers)) {
                GpsAraTriggersManager.INSTANCE.registerTriggerAsync(accessTokenAppId.getApplicationId(), event);
            }
            if (FeatureManager.isEnabled(FeatureManager.Feature.GPSPACAProcessing)) {
                PACustomAudienceClient.INSTANCE.joinCustomAudience(accessTokenAppId.getApplicationId(), event);
            }
            if (event.getIsImplicit() || AppEventsLoggerImpl.access$isActivateAppEventRequested$cp()) {
                return;
            }
            if (Intrinsics.areEqual(event.getName(), AppEventsConstants.EVENT_NAME_ACTIVATED_APP)) {
                AppEventsLoggerImpl.access$setActivateAppEventRequested$cp(true);
            } else {
                Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }

        public final void eagerFlush() {
            if (getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
                AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void notifyDeveloperError(String message) {
            Logger.INSTANCE.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", message);
        }

        @JvmStatic
        public final Executor getAnalyticsExecutor() {
            if (AppEventsLoggerImpl.access$getBackgroundExecutor$cp() == null) {
                initializeTimersIfNeeded();
            }
            ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp = AppEventsLoggerImpl.access$getBackgroundExecutor$cp();
            if (access$getBackgroundExecutor$cp != null) {
                return access$getBackgroundExecutor$cp;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }

        @JvmStatic
        public final String getAnonymousAppDeviceGUID(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp() == null) {
                synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                    if (AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp() == null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0);
                        Companion companion = AppEventsLoggerImpl.INSTANCE;
                        AppEventsLoggerImpl.access$setAnonymousAppDeviceGUID$cp(sharedPreferences.getString("anonymousAppDeviceGUID", null));
                        if (AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp() == null) {
                            Companion companion2 = AppEventsLoggerImpl.INSTANCE;
                            AppEventsLoggerImpl.access$setAnonymousAppDeviceGUID$cp("XZ" + UUID.randomUUID());
                            context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp()).apply();
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            String access$getAnonymousAppDeviceGUID$cp = AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp();
            if (access$getAnonymousAppDeviceGUID$cp != null) {
                return access$getAnonymousAppDeviceGUID$cp;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
    }

    static {
        String canonicalName = AppEventsLoggerImpl.class.getCanonicalName();
        if (canonicalName == null) {
            canonicalName = "com.facebook.appevents.AppEventsLoggerImpl";
        }
        TAG = canonicalName;
        flushBehaviorField = AppEventsLogger.FlushBehavior.AUTO;
        staticLock = new Object();
    }
}
