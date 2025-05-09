package com.facebook.appevents.iap;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.OperationalData;
import com.facebook.appevents.OperationalDataEnum;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InAppPurchaseDedupeConfig.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u000b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\rJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\fJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J&\u0010\u0015\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00070\u00042\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\tJ(\u0010\u0019\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0007\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u0017J!\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0013\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u001dJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/facebook/appevents/iap/InAppPurchaseDedupeConfig;", "", "()V", "defaultCurrencyParameterEquivalents", "", "", "defaultDedupeParameters", "Lkotlin/Pair;", "defaultDedupeWindow", "", "defaultValueParameterEquivalents", "addDedupeParameters", "Landroid/os/Bundle;", "Lcom/facebook/appevents/OperationalData;", "dedupeParameters", "originalParameters", "originalOperationalData", "getCurrencyOfManualEvent", "Ljava/util/Currency;", "parameters", "getCurrencyParameterEquivalents", "getDedupeParameters", "dedupingWithImplicitlyLoggedHistory", "", "getDedupeWindow", "getTestDedupeParameters", "getValueOfManualEvent", "", "valueToSum", "(Ljava/lang/Double;Landroid/os/Bundle;)Ljava/lang/Double;", "getValueParameterEquivalents", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InAppPurchaseDedupeConfig {
    public static final InAppPurchaseDedupeConfig INSTANCE = new InAppPurchaseDedupeConfig();
    private static final List<String> defaultCurrencyParameterEquivalents = CollectionsKt.listOf(AppEventsConstants.EVENT_PARAM_CURRENCY);
    private static final List<String> defaultValueParameterEquivalents = CollectionsKt.listOf(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM);
    private static final long defaultDedupeWindow = TimeUnit.MINUTES.toMillis(1);
    private static final List<Pair<String, List<String>>> defaultDedupeParameters = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(Constants.IAP_PRODUCT_ID, CollectionsKt.listOf(Constants.IAP_PRODUCT_ID)), TuplesKt.to(Constants.IAP_PRODUCT_DESCRIPTION, CollectionsKt.listOf(Constants.IAP_PRODUCT_DESCRIPTION)), TuplesKt.to(Constants.IAP_PRODUCT_TITLE, CollectionsKt.listOf(Constants.IAP_PRODUCT_TITLE)), TuplesKt.to(Constants.IAP_PURCHASE_TOKEN, CollectionsKt.listOf(Constants.IAP_PURCHASE_TOKEN))});

    private InAppPurchaseDedupeConfig() {
    }

    public final List<Pair<String, List<String>>> getDedupeParameters(boolean dedupingWithImplicitlyLoggedHistory) {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getProdDedupeParameters() : null) == null || appSettingsWithoutQuery.getProdDedupeParameters().isEmpty()) {
            return defaultDedupeParameters;
        }
        if (!dedupingWithImplicitlyLoggedHistory) {
            return appSettingsWithoutQuery.getProdDedupeParameters();
        }
        ArrayList arrayList = new ArrayList();
        for (Pair<String, List<String>> pair : appSettingsWithoutQuery.getProdDedupeParameters()) {
            Iterator<String> it = pair.getSecond().iterator();
            while (it.hasNext()) {
                arrayList.add(new Pair(it.next(), CollectionsKt.listOf(pair.getFirst())));
            }
        }
        return arrayList;
    }

    public final List<Pair<String, List<String>>> getTestDedupeParameters(boolean dedupingWithImplicitlyLoggedHistory) {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (appSettingsWithoutQuery == null) {
            return null;
        }
        List<Pair<String, List<String>>> testDedupeParameters = appSettingsWithoutQuery.getTestDedupeParameters();
        if (testDedupeParameters == null || testDedupeParameters.isEmpty()) {
            return null;
        }
        if (!dedupingWithImplicitlyLoggedHistory) {
            return appSettingsWithoutQuery.getTestDedupeParameters();
        }
        ArrayList arrayList = new ArrayList();
        for (Pair<String, List<String>> pair : appSettingsWithoutQuery.getTestDedupeParameters()) {
            Iterator<String> it = pair.getSecond().iterator();
            while (it.hasNext()) {
                arrayList.add(new Pair(it.next(), CollectionsKt.listOf(pair.getFirst())));
            }
        }
        return arrayList;
    }

    public final List<String> getCurrencyParameterEquivalents() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getCurrencyDedupeParameters() : null) == null || appSettingsWithoutQuery.getCurrencyDedupeParameters().isEmpty()) {
            return defaultCurrencyParameterEquivalents;
        }
        return appSettingsWithoutQuery.getCurrencyDedupeParameters();
    }

    public final List<String> getValueParameterEquivalents() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getPurchaseValueDedupeParameters() : null) == null || appSettingsWithoutQuery.getPurchaseValueDedupeParameters().isEmpty()) {
            return defaultValueParameterEquivalents;
        }
        return appSettingsWithoutQuery.getPurchaseValueDedupeParameters();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Currency getCurrencyOfManualEvent(Bundle parameters) {
        boolean z;
        Iterator<String> it = getCurrencyParameterEquivalents().iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                return null;
            }
            String next = it.next();
            if (parameters != null) {
                try {
                    str = parameters.getString(next);
                } catch (Exception unused) {
                    continue;
                }
            }
            String str2 = str;
            if (str2 != null && str2.length() != 0) {
                z = false;
                if (z) {
                    return Currency.getInstance(str);
                }
            }
            z = true;
            if (z) {
            }
        }
    }

    public final Double getValueOfManualEvent(Double valueToSum, Bundle parameters) {
        if (valueToSum != null) {
            return valueToSum;
        }
        Iterator<String> it = getValueParameterEquivalents().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (parameters != null) {
                try {
                    return Double.valueOf(parameters.getDouble(next));
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        return null;
    }

    public final long getDedupeWindow() {
        Long dedupeWindow;
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getDedupeWindow() : null) == null || ((dedupeWindow = appSettingsWithoutQuery.getDedupeWindow()) != null && dedupeWindow.longValue() == 0)) {
            return defaultDedupeWindow;
        }
        return appSettingsWithoutQuery.getDedupeWindow().longValue();
    }

    public final Pair<Bundle, OperationalData> addDedupeParameters(Bundle dedupeParameters, Bundle originalParameters, OperationalData originalOperationalData) {
        if (dedupeParameters == null) {
            return new Pair<>(originalParameters, originalOperationalData);
        }
        try {
            for (String key : dedupeParameters.keySet()) {
                String string = dedupeParameters.getString(key);
                if (string != null) {
                    OperationalData.Companion companion = OperationalData.INSTANCE;
                    OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    Pair<Bundle, OperationalData> addParameterAndReturn = companion.addParameterAndReturn(operationalDataEnum, key, string, originalParameters, originalOperationalData);
                    Bundle component1 = addParameterAndReturn.component1();
                    originalOperationalData = addParameterAndReturn.component2();
                    originalParameters = component1;
                }
            }
        } catch (Exception unused) {
        }
        return new Pair<>(originalParameters, originalOperationalData);
    }
}
