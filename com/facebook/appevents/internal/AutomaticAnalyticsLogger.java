package com.facebook.appevents.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.appevents.OperationalData;
import com.facebook.appevents.OperationalDataEnum;
import com.facebook.appevents.iap.InAppPurchase;
import com.facebook.appevents.iap.InAppPurchaseDedupeConfig;
import com.facebook.appevents.iap.InAppPurchaseEventManager;
import com.facebook.appevents.iap.InAppPurchaseManager;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AutomaticAnalyticsLogger.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001+B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J*\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J>\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J0\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J0\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J \u0010\u001e\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001f\u001a\u00020\u0004H\u0007J\b\u0010 \u001a\u00020!H\u0007J\b\u0010\"\u001a\u00020#H\u0007J\u001a\u0010$\u001a\u00020#2\b\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010&\u001a\u00020'H\u0007J4\u0010(\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010)\u001a\u00020!2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010*\u001a\u00020!H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/facebook/appevents/internal/AutomaticAnalyticsLogger;", "", "()V", "APP_EVENTS_IF_AUTO_LOG_SUBS", "", "TAG", "kotlin.jvm.PlatformType", "internalAppEventsLogger", "Lcom/facebook/appevents/InternalAppEventsLogger;", "getPurchaseDedupeParameters", "Landroid/os/Bundle;", "purchaseLoggingParametersList", "", "Lcom/facebook/appevents/internal/AutomaticAnalyticsLogger$PurchaseLoggingParameters;", "getPurchaseLoggingParameters", FirebaseAnalytics.Event.PURCHASE, "skuDetails", "billingClientVersion", "Lcom/facebook/appevents/iap/InAppPurchaseUtils$BillingClientVersion;", "extraParameter", "", "getPurchaseParametersGPBLV2V4", "type", "params", "operationalData", "Lcom/facebook/appevents/OperationalData;", "purchaseJSON", "Lorg/json/JSONObject;", "skuDetailsJSON", "getPurchaseParametersGPBLV5V7", "getSubscriptionDedupeParameters", io.flutter.plugins.firebase.analytics.Constants.EVENT_NAME, "isImplicitPurchaseLoggingEnabled", "", "logActivateAppEvent", "", "logActivityTimeSpentEvent", "activityName", "timeSpentInSeconds", "", "logPurchase", "isSubscription", "isFirstAppLaunch", "PurchaseLoggingParameters", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AutomaticAnalyticsLogger {
    private static final String APP_EVENTS_IF_AUTO_LOG_SUBS = "app_events_if_auto_log_subs";
    public static final AutomaticAnalyticsLogger INSTANCE = new AutomaticAnalyticsLogger();
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    private static final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());

    private AutomaticAnalyticsLogger() {
    }

    @JvmStatic
    public static final void logActivateAppEvent() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            if (applicationContext instanceof Application) {
                AppEventsLogger.INSTANCE.activateApp((Application) applicationContext, applicationId);
            } else {
                Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
            }
        }
    }

    @JvmStatic
    public static final void logActivityTimeSpentEvent(String activityName, long timeSpentInSeconds) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
        if (queryAppSettings == null || !queryAppSettings.getAutomaticLoggingEnabled() || timeSpentInSeconds <= 0) {
            return;
        }
        InternalAppEventsLogger internalAppEventsLogger2 = new InternalAppEventsLogger(applicationContext);
        Bundle bundle = new Bundle(1);
        bundle.putCharSequence(Constants.AA_TIME_SPENT_SCREEN_PARAMETER_NAME, activityName);
        internalAppEventsLogger2.logEvent(Constants.AA_TIME_SPENT_EVENT_NAME, timeSpentInSeconds, bundle);
    }

    public static /* synthetic */ void logPurchase$default(String str, String str2, boolean z, InAppPurchaseUtils.BillingClientVersion billingClientVersion, boolean z2, int i, Object obj) {
        if ((i & 16) != 0) {
            z2 = false;
        }
        logPurchase(str, str2, z, billingClientVersion, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void logPurchase(String purchase, String skuDetails, boolean isSubscription, InAppPurchaseUtils.BillingClientVersion billingClientVersion, boolean isFirstAppLaunch) {
        List<PurchaseLoggingParameters> purchaseLoggingParameters;
        String str;
        String str2;
        Bundle purchaseDedupeParameters;
        Intrinsics.checkNotNullParameter(purchase, "purchase");
        Intrinsics.checkNotNullParameter(skuDetails, "skuDetails");
        AutomaticAnalyticsLogger automaticAnalyticsLogger = INSTANCE;
        if (!isImplicitPurchaseLoggingEnabled() || (purchaseLoggingParameters = automaticAnalyticsLogger.getPurchaseLoggingParameters(purchase, skuDetails, billingClientVersion)) == null || purchaseLoggingParameters.isEmpty()) {
            return;
        }
        if (isSubscription && FetchedAppGateKeepersManager.getGateKeeperForKey(APP_EVENTS_IF_AUTO_LOG_SUBS, FacebookSdk.getApplicationId(), false)) {
            if (isFirstAppLaunch) {
                str2 = Constants.EVENT_NAME_SUBSCRIPTION_RESTORED;
            } else {
                str2 = InAppPurchaseEventManager.INSTANCE.hasFreeTrialPeirod(skuDetails) ? AppEventsConstants.EVENT_NAME_START_TRIAL : AppEventsConstants.EVENT_NAME_SUBSCRIBE;
            }
        } else {
            if (!isFirstAppLaunch) {
                str = AppEventsConstants.EVENT_NAME_PURCHASED;
                if (!isSubscription && FeatureManager.isEnabled(FeatureManager.Feature.AndroidManualImplicitSubsDedupe)) {
                    purchaseDedupeParameters = getSubscriptionDedupeParameters(purchaseLoggingParameters, str);
                } else {
                    purchaseDedupeParameters = (isSubscription && FeatureManager.isEnabled(FeatureManager.Feature.AndroidManualImplicitPurchaseDedupe)) ? getPurchaseDedupeParameters(purchaseLoggingParameters) : null;
                }
                InAppPurchaseDedupeConfig.INSTANCE.addDedupeParameters(purchaseDedupeParameters, purchaseLoggingParameters.get(0).getParam(), purchaseLoggingParameters.get(0).getOperationalData());
                if (Intrinsics.areEqual(str, AppEventsConstants.EVENT_NAME_PURCHASED)) {
                    internalAppEventsLogger.logEventImplicitly(str, purchaseLoggingParameters.get(0).getPurchaseAmount(), purchaseLoggingParameters.get(0).getCurrency(), purchaseLoggingParameters.get(0).getParam(), purchaseLoggingParameters.get(0).getOperationalData());
                    return;
                } else {
                    internalAppEventsLogger.logPurchaseImplicitly(purchaseLoggingParameters.get(0).getPurchaseAmount(), purchaseLoggingParameters.get(0).getCurrency(), purchaseLoggingParameters.get(0).getParam(), purchaseLoggingParameters.get(0).getOperationalData());
                    return;
                }
            }
            str2 = Constants.EVENT_NAME_PURCHASE_RESTORED;
        }
        str = str2;
        if (!isSubscription) {
        }
        if (isSubscription) {
        }
        InAppPurchaseDedupeConfig.INSTANCE.addDedupeParameters(purchaseDedupeParameters, purchaseLoggingParameters.get(0).getParam(), purchaseLoggingParameters.get(0).getOperationalData());
        if (Intrinsics.areEqual(str, AppEventsConstants.EVENT_NAME_PURCHASED)) {
        }
    }

    @JvmStatic
    public static final synchronized Bundle getPurchaseDedupeParameters(List<PurchaseLoggingParameters> purchaseLoggingParametersList) {
        Bundle performDedupe;
        synchronized (AutomaticAnalyticsLogger.class) {
            Intrinsics.checkNotNullParameter(purchaseLoggingParametersList, "purchaseLoggingParametersList");
            PurchaseLoggingParameters purchaseLoggingParameters = purchaseLoggingParametersList.get(0);
            performDedupe = InAppPurchaseManager.performDedupe(CollectionsKt.listOf(new InAppPurchase(AppEventsConstants.EVENT_NAME_PURCHASED, purchaseLoggingParameters.getPurchaseAmount().doubleValue(), purchaseLoggingParameters.getCurrency())), System.currentTimeMillis(), true, CollectionsKt.listOf(new Pair(purchaseLoggingParameters.getParam(), purchaseLoggingParameters.getOperationalData())));
        }
        return performDedupe;
    }

    @JvmStatic
    public static final synchronized Bundle getSubscriptionDedupeParameters(List<PurchaseLoggingParameters> purchaseLoggingParametersList, String eventName) {
        Bundle performDedupe;
        synchronized (AutomaticAnalyticsLogger.class) {
            Intrinsics.checkNotNullParameter(purchaseLoggingParametersList, "purchaseLoggingParametersList");
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            ArrayList arrayList = new ArrayList();
            for (PurchaseLoggingParameters purchaseLoggingParameters : purchaseLoggingParametersList) {
                arrayList.add(new InAppPurchase(eventName, purchaseLoggingParameters.getPurchaseAmount().doubleValue(), purchaseLoggingParameters.getCurrency()));
            }
            ArrayList arrayList2 = arrayList;
            long currentTimeMillis = System.currentTimeMillis();
            List<PurchaseLoggingParameters> list = purchaseLoggingParametersList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (PurchaseLoggingParameters purchaseLoggingParameters2 : list) {
                arrayList3.add(new Pair(purchaseLoggingParameters2.getParam(), purchaseLoggingParameters2.getOperationalData()));
            }
            performDedupe = InAppPurchaseManager.performDedupe(arrayList2, currentTimeMillis, true, arrayList3);
        }
        return performDedupe;
    }

    @JvmStatic
    public static final boolean isImplicitPurchaseLoggingEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery != null && FacebookSdk.getAutoLogAppEventsEnabled() && appSettingsWithoutQuery.getIAPAutomaticLoggingEnabled();
    }

    private final List<PurchaseLoggingParameters> getPurchaseLoggingParameters(String purchase, String skuDetails, InAppPurchaseUtils.BillingClientVersion billingClientVersion) {
        return getPurchaseLoggingParameters(purchase, skuDetails, new HashMap(), billingClientVersion);
    }

    private final PurchaseLoggingParameters getPurchaseParametersGPBLV2V4(String type, Bundle params, OperationalData operationalData, JSONObject purchaseJSON, JSONObject skuDetailsJSON) {
        if (Intrinsics.areEqual(type, InAppPurchaseUtils.IAPProductType.SUBS.getType())) {
            OperationalData.Companion companion = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
            String bool = Boolean.toString(purchaseJSON.optBoolean(Constants.GP_IAP_AUTORENEWING, false));
            Intrinsics.checkNotNullExpressionValue(bool, "toString(\n              …      )\n                )");
            companion.addParameter(operationalDataEnum, Constants.IAP_SUBSCRIPTION_AUTORENEWING, bool, params, operationalData);
            OperationalData.Companion companion2 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum2 = OperationalDataEnum.IAPParameters;
            String optString = skuDetailsJSON.optString(Constants.GP_IAP_SUBSCRIPTION_PERIOD);
            Intrinsics.checkNotNullExpressionValue(optString, "skuDetailsJSON.optString…_IAP_SUBSCRIPTION_PERIOD)");
            companion2.addParameter(operationalDataEnum2, Constants.IAP_SUBSCRIPTION_PERIOD, optString, params, operationalData);
            OperationalData.Companion companion3 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum3 = OperationalDataEnum.IAPParameters;
            String optString2 = skuDetailsJSON.optString(Constants.GP_IAP_FREE_TRIAL_PERIOD);
            Intrinsics.checkNotNullExpressionValue(optString2, "skuDetailsJSON.optString…GP_IAP_FREE_TRIAL_PERIOD)");
            companion3.addParameter(operationalDataEnum3, Constants.IAP_FREE_TRIAL_PERIOD, optString2, params, operationalData);
            String introductoryPriceCycles = skuDetailsJSON.optString(Constants.GP_IAP_INTRODUCTORY_PRICE_CYCLES);
            Intrinsics.checkNotNullExpressionValue(introductoryPriceCycles, "introductoryPriceCycles");
            if (introductoryPriceCycles.length() > 0) {
                OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_INTRO_PRICE_CYCLES, introductoryPriceCycles, params, operationalData);
            }
            String introductoryPricePeriod = skuDetailsJSON.optString(Constants.GP_IAP_INTRODUCTORY_PRICE_PERIOD);
            Intrinsics.checkNotNullExpressionValue(introductoryPricePeriod, "introductoryPricePeriod");
            if (introductoryPricePeriod.length() > 0) {
                OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_INTRO_PERIOD, introductoryPricePeriod, params, operationalData);
            }
            String introductoryPriceAmountMicros = skuDetailsJSON.optString(Constants.GP_IAP_INTRODUCTORY_PRICE_AMOUNT_MICROS);
            Intrinsics.checkNotNullExpressionValue(introductoryPriceAmountMicros, "introductoryPriceAmountMicros");
            if (introductoryPriceAmountMicros.length() > 0) {
                OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_INTRO_PRICE_AMOUNT_MICROS, introductoryPriceAmountMicros, params, operationalData);
            }
        }
        BigDecimal bigDecimal = new BigDecimal(skuDetailsJSON.getLong(Constants.GP_IAP_PRICE_AMOUNT_MICROS_V2V4) / 1000000.0d);
        Currency currency = Currency.getInstance(skuDetailsJSON.getString(Constants.GP_IAP_PRICE_CURRENCY_CODE_V2V4));
        Intrinsics.checkNotNullExpressionValue(currency, "getInstance(skuDetailsJS…RICE_CURRENCY_CODE_V2V4))");
        return new PurchaseLoggingParameters(bigDecimal, currency, params, operationalData);
    }

    private final List<PurchaseLoggingParameters> getPurchaseParametersGPBLV5V7(String type, Bundle params, OperationalData operationalData, JSONObject skuDetailsJSON) {
        if (Intrinsics.areEqual(type, InAppPurchaseUtils.IAPProductType.SUBS.getType())) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = skuDetailsJSON.getJSONArray(Constants.GP_IAP_SUBSCRIPTION_OFFER_DETAILS);
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = skuDetailsJSON.getJSONArray(Constants.GP_IAP_SUBSCRIPTION_OFFER_DETAILS).getJSONObject(i);
                if (jSONObject == null) {
                    return null;
                }
                Bundle bundle = new Bundle(params);
                OperationalData copy = operationalData.copy();
                String basePlanId = jSONObject.getString(Constants.GP_IAP_BASE_PLAN_ID);
                OperationalData.Companion companion = OperationalData.INSTANCE;
                OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
                Intrinsics.checkNotNullExpressionValue(basePlanId, "basePlanId");
                companion.addParameter(operationalDataEnum, Constants.IAP_BASE_PLAN, basePlanId, bundle, copy);
                JSONArray jSONArray2 = jSONObject.getJSONArray(Constants.GP_IAP_SUBSCRIPTION_PRICING_PHASES);
                JSONObject jSONObject2 = jSONArray2.getJSONObject(jSONArray2.length() - 1);
                if (jSONObject2 == null) {
                    return null;
                }
                OperationalData.Companion companion2 = OperationalData.INSTANCE;
                OperationalDataEnum operationalDataEnum2 = OperationalDataEnum.IAPParameters;
                String optString = jSONObject2.optString(Constants.GP_IAP_BILLING_PERIOD);
                Intrinsics.checkNotNullExpressionValue(optString, "subscriptionJSON.optStri…IOD\n                    )");
                companion2.addParameter(operationalDataEnum2, Constants.IAP_SUBSCRIPTION_PERIOD, optString, bundle, copy);
                if (jSONObject2.has(Constants.GP_IAP_RECURRENCE_MODE) && jSONObject2.getInt(Constants.GP_IAP_RECURRENCE_MODE) != 3) {
                    OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_SUBSCRIPTION_AUTORENEWING, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, bundle, copy);
                } else {
                    OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_SUBSCRIPTION_AUTORENEWING, "false", bundle, copy);
                }
                BigDecimal bigDecimal = new BigDecimal(jSONObject2.getLong(Constants.GP_IAP_PRICE_AMOUNT_MICROS_V5V7) / 1000000.0d);
                Currency currency = Currency.getInstance(jSONObject2.getString(Constants.GP_IAP_PRICE_CURRENCY_CODE_V5V7));
                Intrinsics.checkNotNullExpressionValue(currency, "getInstance(subscription…RICE_CURRENCY_CODE_V5V7))");
                arrayList.add(new PurchaseLoggingParameters(bigDecimal, currency, bundle, copy));
            }
            return arrayList;
        }
        JSONObject jSONObject3 = skuDetailsJSON.getJSONObject(Constants.GP_IAP_ONE_TIME_PURCHASE_OFFER_DETAILS);
        if (jSONObject3 == null) {
            return null;
        }
        BigDecimal bigDecimal2 = new BigDecimal(jSONObject3.getLong(Constants.GP_IAP_PRICE_AMOUNT_MICROS_V5V7) / 1000000.0d);
        Currency currency2 = Currency.getInstance(jSONObject3.getString(Constants.GP_IAP_PRICE_CURRENCY_CODE_V5V7));
        Intrinsics.checkNotNullExpressionValue(currency2, "getInstance(oneTimePurch…RICE_CURRENCY_CODE_V5V7))");
        return CollectionsKt.mutableListOf(new PurchaseLoggingParameters(bigDecimal2, currency2, params, operationalData));
    }

    private final List<PurchaseLoggingParameters> getPurchaseLoggingParameters(String purchase, String skuDetails, Map<String, String> extraParameter, InAppPurchaseUtils.BillingClientVersion billingClientVersion) {
        List<PurchaseLoggingParameters> list = null;
        try {
            JSONObject jSONObject = new JSONObject(purchase);
            JSONObject jSONObject2 = new JSONObject(skuDetails);
            Bundle bundle = new Bundle(1);
            OperationalData operationalData = new OperationalData();
            if (billingClientVersion != null) {
                OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_AUTOLOG_IMPLEMENTATION, billingClientVersion.getType(), bundle, operationalData);
            }
            OperationalData.Companion companion = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
            String string = jSONObject.getString("productId");
            Intrinsics.checkNotNullExpressionValue(string, "purchaseJSON.getString(C…stants.GP_IAP_PRODUCT_ID)");
            companion.addParameter(operationalDataEnum, Constants.IAP_PRODUCT_ID, string, bundle, operationalData);
            OperationalData.Companion companion2 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum2 = OperationalDataEnum.IAPParameters;
            String string2 = jSONObject.getString("productId");
            Intrinsics.checkNotNullExpressionValue(string2, "purchaseJSON.getString(C…stants.GP_IAP_PRODUCT_ID)");
            companion2.addParameter(operationalDataEnum2, AppEventsConstants.EVENT_PARAM_CONTENT_ID, string2, bundle, operationalData);
            OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.ANDROID_DYNAMIC_ADS_CONTENT_ID, "client_implicit", bundle, operationalData);
            OperationalData.Companion companion3 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum3 = OperationalDataEnum.IAPParameters;
            String string3 = jSONObject.getString(Constants.GP_IAP_PURCHASE_TIME);
            Intrinsics.checkNotNullExpressionValue(string3, "purchaseJSON.getString(C…nts.GP_IAP_PURCHASE_TIME)");
            companion3.addParameter(operationalDataEnum3, Constants.IAP_PURCHASE_TIME, string3, bundle, operationalData);
            OperationalData.Companion companion4 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum4 = OperationalDataEnum.IAPParameters;
            String string4 = jSONObject.getString("purchaseToken");
            Intrinsics.checkNotNullExpressionValue(string4, "purchaseJSON.getString(C…ts.GP_IAP_PURCHASE_TOKEN)");
            companion4.addParameter(operationalDataEnum4, Constants.IAP_PURCHASE_TOKEN, string4, bundle, operationalData);
            OperationalData.Companion companion5 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum5 = OperationalDataEnum.IAPParameters;
            String optString = jSONObject.optString("packageName");
            Intrinsics.checkNotNullExpressionValue(optString, "purchaseJSON.optString(C…ants.GP_IAP_PACKAGE_NAME)");
            companion5.addParameter(operationalDataEnum5, Constants.IAP_PACKAGE_NAME, optString, bundle, operationalData);
            OperationalData.Companion companion6 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum6 = OperationalDataEnum.IAPParameters;
            String optString2 = jSONObject2.optString("title");
            Intrinsics.checkNotNullExpressionValue(optString2, "skuDetailsJSON.optString(Constants.GP_IAP_TITLE)");
            companion6.addParameter(operationalDataEnum6, Constants.IAP_PRODUCT_TITLE, optString2, bundle, operationalData);
            OperationalData.Companion companion7 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum7 = OperationalDataEnum.IAPParameters;
            String optString3 = jSONObject2.optString("description");
            Intrinsics.checkNotNullExpressionValue(optString3, "skuDetailsJSON.optString…tants.GP_IAP_DESCRIPTION)");
            companion7.addParameter(operationalDataEnum7, Constants.IAP_PRODUCT_DESCRIPTION, optString3, bundle, operationalData);
            String type = jSONObject2.optString("type");
            OperationalData.Companion companion8 = OperationalData.INSTANCE;
            OperationalDataEnum operationalDataEnum8 = OperationalDataEnum.IAPParameters;
            Intrinsics.checkNotNullExpressionValue(type, "type");
            companion8.addParameter(operationalDataEnum8, Constants.IAP_PRODUCT_TYPE, type, bundle, operationalData);
            String specificBillingLibraryVersion = InAppPurchaseManager.getSpecificBillingLibraryVersion();
            if (specificBillingLibraryVersion != null) {
                OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, Constants.IAP_BILLING_LIBRARY_VERSION, specificBillingLibraryVersion, bundle, operationalData);
            }
            for (Map.Entry<String, String> entry : extraParameter.entrySet()) {
                OperationalData.INSTANCE.addParameter(OperationalDataEnum.IAPParameters, entry.getKey(), entry.getValue(), bundle, operationalData);
            }
            if (jSONObject2.has(Constants.GP_IAP_PRICE_AMOUNT_MICROS_V2V4)) {
                list = CollectionsKt.mutableListOf(getPurchaseParametersGPBLV2V4(type, bundle, operationalData, jSONObject, jSONObject2));
            } else if (jSONObject2.has(Constants.GP_IAP_SUBSCRIPTION_OFFER_DETAILS) || jSONObject2.has(Constants.GP_IAP_ONE_TIME_PURCHASE_OFFER_DETAILS)) {
                try {
                    return getPurchaseParametersGPBLV5V7(type, bundle, operationalData, jSONObject2);
                } catch (JSONException e) {
                    e = e;
                    Log.e(TAG, "Error parsing in-app purchase/subscription data.", e);
                    return null;
                } catch (Exception e2) {
                    e = e2;
                    Log.e(TAG, "Failed to get purchase logging parameters,", e);
                    return null;
                }
            }
            return list;
        } catch (JSONException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    /* compiled from: AutomaticAnalyticsLogger.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/facebook/appevents/internal/AutomaticAnalyticsLogger$PurchaseLoggingParameters;", "", "purchaseAmount", "Ljava/math/BigDecimal;", FirebaseAnalytics.Param.CURRENCY, "Ljava/util/Currency;", "param", "Landroid/os/Bundle;", "operationalData", "Lcom/facebook/appevents/OperationalData;", "(Ljava/math/BigDecimal;Ljava/util/Currency;Landroid/os/Bundle;Lcom/facebook/appevents/OperationalData;)V", "getCurrency", "()Ljava/util/Currency;", "setCurrency", "(Ljava/util/Currency;)V", "getOperationalData", "()Lcom/facebook/appevents/OperationalData;", "setOperationalData", "(Lcom/facebook/appevents/OperationalData;)V", "getParam", "()Landroid/os/Bundle;", "setParam", "(Landroid/os/Bundle;)V", "getPurchaseAmount", "()Ljava/math/BigDecimal;", "setPurchaseAmount", "(Ljava/math/BigDecimal;)V", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class PurchaseLoggingParameters {
        private Currency currency;
        private OperationalData operationalData;
        private Bundle param;
        private BigDecimal purchaseAmount;

        public PurchaseLoggingParameters(BigDecimal purchaseAmount, Currency currency, Bundle param, OperationalData operationalData) {
            Intrinsics.checkNotNullParameter(purchaseAmount, "purchaseAmount");
            Intrinsics.checkNotNullParameter(currency, "currency");
            Intrinsics.checkNotNullParameter(param, "param");
            Intrinsics.checkNotNullParameter(operationalData, "operationalData");
            this.purchaseAmount = purchaseAmount;
            this.currency = currency;
            this.param = param;
            this.operationalData = operationalData;
        }

        public final BigDecimal getPurchaseAmount() {
            return this.purchaseAmount;
        }

        public final void setPurchaseAmount(BigDecimal bigDecimal) {
            Intrinsics.checkNotNullParameter(bigDecimal, "<set-?>");
            this.purchaseAmount = bigDecimal;
        }

        public final Currency getCurrency() {
            return this.currency;
        }

        public final void setCurrency(Currency currency) {
            Intrinsics.checkNotNullParameter(currency, "<set-?>");
            this.currency = currency;
        }

        public final Bundle getParam() {
            return this.param;
        }

        public final void setParam(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "<set-?>");
            this.param = bundle;
        }

        public final OperationalData getOperationalData() {
            return this.operationalData;
        }

        public final void setOperationalData(OperationalData operationalData) {
            Intrinsics.checkNotNullParameter(operationalData, "<set-?>");
            this.operationalData = operationalData;
        }
    }
}
