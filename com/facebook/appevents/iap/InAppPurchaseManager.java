package com.facebook.appevents.iap;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.appevents.OperationalData;
import com.facebook.appevents.OperationalDataEnum;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: InAppPurchaseManager.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0002JB\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bJ\n\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0007JF\u0010\u001e\u001a\u0004\u0018\u00010\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0 2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u001b2\u001c\u0010#\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\f0 H\u0007J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0004H\u0003J\b\u0010&\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R<\u0010\b\u001a0\u0012\u0004\u0012\u00020\n\u0012&\u0012$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\f0\f0\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R<\u0010\u0010\u001a0\u0012\u0004\u0012\u00020\n\u0012&\u0012$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\f0\f0\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/facebook/appevents/iap/InAppPurchaseManager;", "", "()V", "GOOGLE_BILLINGCLIENT_VERSION", "", "enabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "specificBillingLibraryVersion", "timesOfImplicitPurchases", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/facebook/appevents/iap/InAppPurchase;", "", "Lkotlin/Pair;", "", "Landroid/os/Bundle;", "Lcom/facebook/appevents/OperationalData;", "timesOfManualPurchases", "enableAutoLogging", "", "getBillingClientVersion", "Lcom/facebook/appevents/iap/InAppPurchaseUtils$BillingClientVersion;", "getDedupeParameter", "newPurchaseParameters", "newPurchaseOperationalData", "oldPurchaseParameters", "oldPurchaseOperationalData", "dedupingWithImplicitlyLoggedHistory", "", "withTestDedupeKeys", "getSpecificBillingLibraryVersion", "performDedupe", "purchases", "", "time", "isImplicitlyLogged", "purchaseParameters", "setSpecificBillingLibraryVersion", "version", "startTracking", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InAppPurchaseManager {
    private static final String GOOGLE_BILLINGCLIENT_VERSION = "com.google.android.play.billingclient.version";
    private static String specificBillingLibraryVersion;
    public static final InAppPurchaseManager INSTANCE = new InAppPurchaseManager();
    private static final ConcurrentHashMap<InAppPurchase, List<Pair<Long, Pair<Bundle, OperationalData>>>> timesOfManualPurchases = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<InAppPurchase, List<Pair<Long, Pair<Bundle, OperationalData>>>> timesOfImplicitPurchases = new ConcurrentHashMap<>();
    private static final AtomicBoolean enabled = new AtomicBoolean(false);

    /* compiled from: InAppPurchaseManager.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InAppPurchaseUtils.BillingClientVersion.values().length];
            try {
                iArr[InAppPurchaseUtils.BillingClientVersion.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InAppPurchaseUtils.BillingClientVersion.V1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InAppPurchaseUtils.BillingClientVersion.V2_V4.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[InAppPurchaseUtils.BillingClientVersion.V5_V7.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private InAppPurchaseManager() {
    }

    @JvmStatic
    public static final void enableAutoLogging() {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
            return;
        }
        try {
            if (!AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
                InAppPurchaseLoggerManager.updateLatestPossiblePurchaseTime();
            } else {
                enabled.set(true);
                startTracking();
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
        }
    }

    @JvmStatic
    public static final void startTracking() {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
            return;
        }
        try {
            if (enabled.get()) {
                InAppPurchaseUtils.BillingClientVersion billingClientVersion = INSTANCE.getBillingClientVersion();
                int i = WhenMappings.$EnumSwitchMapping$0[billingClientVersion.ordinal()];
                if (i == 2) {
                    InAppPurchaseActivityLifecycleTracker.startIapLogging(InAppPurchaseUtils.BillingClientVersion.V1);
                    return;
                }
                if (i == 3) {
                    if (FeatureManager.isEnabled(FeatureManager.Feature.IapLoggingLib2)) {
                        InAppPurchaseAutoLogger.startIapLogging(FacebookSdk.getApplicationContext(), billingClientVersion);
                        return;
                    } else {
                        InAppPurchaseActivityLifecycleTracker.startIapLogging(InAppPurchaseUtils.BillingClientVersion.V2_V4);
                        return;
                    }
                }
                if (i == 4 && FeatureManager.isEnabled(FeatureManager.Feature.IapLoggingLib5To7)) {
                    InAppPurchaseAutoLogger.startIapLogging(FacebookSdk.getApplicationContext(), billingClientVersion);
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
        }
    }

    @JvmStatic
    private static final void setSpecificBillingLibraryVersion(String version) {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
            return;
        }
        try {
            specificBillingLibraryVersion = version;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
        }
    }

    @JvmStatic
    public static final String getSpecificBillingLibraryVersion() {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
            return null;
        }
        try {
            return specificBillingLibraryVersion;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
            return null;
        }
    }

    private final InAppPurchaseUtils.BillingClientVersion getBillingClientVersion() {
        try {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return null;
            }
            try {
                Context applicationContext = FacebookSdk.getApplicationContext();
                ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "context.packageManager.g…TA_DATA\n                )");
                String string = applicationInfo.metaData.getString(GOOGLE_BILLINGCLIENT_VERSION);
                if (string == null) {
                    return InAppPurchaseUtils.BillingClientVersion.NONE;
                }
                List split$default = StringsKt.split$default((CharSequence) string, new String[]{"."}, false, 3, 2, (Object) null);
                if (string.length() == 0) {
                    return InAppPurchaseUtils.BillingClientVersion.V5_V7;
                }
                setSpecificBillingLibraryVersion("GPBL." + string);
                Integer intOrNull = StringsKt.toIntOrNull((String) split$default.get(0));
                if (intOrNull == null) {
                    return InAppPurchaseUtils.BillingClientVersion.V5_V7;
                }
                int intValue = intOrNull.intValue();
                if (intValue == 1) {
                    return InAppPurchaseUtils.BillingClientVersion.V1;
                }
                if (intValue < 5) {
                    return InAppPurchaseUtils.BillingClientVersion.V2_V4;
                }
                return InAppPurchaseUtils.BillingClientVersion.V5_V7;
            } catch (Exception unused) {
                return InAppPurchaseUtils.BillingClientVersion.V5_V7;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01f5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4 A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:11:0x0010, B:15:0x0025, B:21:0x0036, B:23:0x0043, B:25:0x0082, B:26:0x0093, B:28:0x0098, B:33:0x00a4, B:34:0x00ad, B:36:0x00b3, B:40:0x00ec, B:43:0x00f4, B:46:0x00fd, B:49:0x0117, B:58:0x012a, B:71:0x0154, B:72:0x0159, B:75:0x0169, B:77:0x0170, B:78:0x017b, B:82:0x0194, B:84:0x019c, B:85:0x01a9, B:87:0x01b1, B:89:0x01f5, B:93:0x01c7, B:95:0x01cf, B:96:0x01dc, B:98:0x01e4, B:105:0x008b, B:107:0x01fa, B:108:0x01fe, B:110:0x0204, B:112:0x020c, B:115:0x0228, B:116:0x022d, B:118:0x0233, B:122:0x0253, B:135:0x025b, B:141:0x0261, B:138:0x026b, B:125:0x0277, B:132:0x027d, B:128:0x0288, B:147:0x0219), top: B:10:0x0010, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01cf A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:11:0x0010, B:15:0x0025, B:21:0x0036, B:23:0x0043, B:25:0x0082, B:26:0x0093, B:28:0x0098, B:33:0x00a4, B:34:0x00ad, B:36:0x00b3, B:40:0x00ec, B:43:0x00f4, B:46:0x00fd, B:49:0x0117, B:58:0x012a, B:71:0x0154, B:72:0x0159, B:75:0x0169, B:77:0x0170, B:78:0x017b, B:82:0x0194, B:84:0x019c, B:85:0x01a9, B:87:0x01b1, B:89:0x01f5, B:93:0x01c7, B:95:0x01cf, B:96:0x01dc, B:98:0x01e4, B:105:0x008b, B:107:0x01fa, B:108:0x01fe, B:110:0x0204, B:112:0x020c, B:115:0x0228, B:116:0x022d, B:118:0x0233, B:122:0x0253, B:135:0x025b, B:141:0x0261, B:138:0x026b, B:125:0x0277, B:132:0x027d, B:128:0x0288, B:147:0x0219), top: B:10:0x0010, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01e4 A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:11:0x0010, B:15:0x0025, B:21:0x0036, B:23:0x0043, B:25:0x0082, B:26:0x0093, B:28:0x0098, B:33:0x00a4, B:34:0x00ad, B:36:0x00b3, B:40:0x00ec, B:43:0x00f4, B:46:0x00fd, B:49:0x0117, B:58:0x012a, B:71:0x0154, B:72:0x0159, B:75:0x0169, B:77:0x0170, B:78:0x017b, B:82:0x0194, B:84:0x019c, B:85:0x01a9, B:87:0x01b1, B:89:0x01f5, B:93:0x01c7, B:95:0x01cf, B:96:0x01dc, B:98:0x01e4, B:105:0x008b, B:107:0x01fa, B:108:0x01fe, B:110:0x0204, B:112:0x020c, B:115:0x0228, B:116:0x022d, B:118:0x0233, B:122:0x0253, B:135:0x025b, B:141:0x0261, B:138:0x026b, B:125:0x0277, B:132:0x027d, B:128:0x0288, B:147:0x0219), top: B:10:0x0010, outer: #1 }] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final synchronized Bundle performDedupe(List<InAppPurchase> purchases, long time, boolean isImplicitlyLogged, List<Pair<Bundle, OperationalData>> purchaseParameters) {
        List<Pair<Long, Pair<Bundle, OperationalData>>> list;
        List<Pair<Long, Pair<Bundle, OperationalData>>> list2;
        boolean z;
        InAppPurchase inAppPurchase;
        String str;
        Long l;
        String str2;
        boolean z2;
        ConcurrentHashMap<InAppPurchase, List<Pair<Long, Pair<Bundle, OperationalData>>>> concurrentHashMap;
        List<Pair<Long, Pair<Bundle, OperationalData>>> list3;
        synchronized (InAppPurchaseManager.class) {
            String str3 = null;
            if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
                return null;
            }
            try {
                Intrinsics.checkNotNullParameter(purchases, "purchases");
                Intrinsics.checkNotNullParameter(purchaseParameters, "purchaseParameters");
                if (purchaseParameters.isEmpty()) {
                    return null;
                }
                if (!(purchases.size() == purchaseParameters.size())) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                int size = purchases.size();
                Bundle bundle = null;
                int i = 0;
                while (i < size) {
                    InAppPurchase inAppPurchase2 = purchases.get(i);
                    Pair<Bundle, OperationalData> pair = purchaseParameters.get(i);
                    Bundle component1 = pair.component1();
                    OperationalData component2 = pair.component2();
                    InAppPurchase inAppPurchase3 = new InAppPurchase(inAppPurchase2.getEventName(), new BigDecimal(String.valueOf(inAppPurchase2.getAmount())).setScale(2, RoundingMode.HALF_UP).doubleValue(), inAppPurchase2.getCurrency());
                    if (isImplicitlyLogged) {
                        list2 = timesOfManualPurchases.get(inAppPurchase3);
                    } else {
                        list2 = timesOfImplicitPurchases.get(inAppPurchase3);
                    }
                    List<Pair<Long, Pair<Bundle, OperationalData>>> list4 = list2;
                    if (list4 != null && !list4.isEmpty()) {
                        z = false;
                        if (z) {
                            str = str3;
                            Long l2 = str;
                            str2 = l2;
                            z2 = false;
                            for (Pair<Long, Pair<Bundle, OperationalData>> pair2 : list2) {
                                long longValue = pair2.getFirst().longValue();
                                Pair<Bundle, OperationalData> second = pair2.getSecond();
                                Bundle component12 = second.component1();
                                OperationalData component22 = second.component2();
                                if (Math.abs(time - longValue) <= InAppPurchaseDedupeConfig.INSTANCE.getDedupeWindow() && (l2 == 0 || longValue < l2.longValue())) {
                                    InAppPurchaseManager inAppPurchaseManager = INSTANCE;
                                    InAppPurchase inAppPurchase4 = inAppPurchase3;
                                    String dedupeParameter$default = getDedupeParameter$default(inAppPurchaseManager, component1, component2, component12, component22, !isImplicitlyLogged, false, 32, null);
                                    String dedupeParameter = inAppPurchaseManager.getDedupeParameter(component1, component2, component12, component22, !isImplicitlyLogged, true);
                                    if (dedupeParameter != null) {
                                        str = dedupeParameter;
                                    }
                                    if (dedupeParameter$default != null) {
                                        l2 = Long.valueOf(longValue);
                                        arrayList.add(new Pair(inAppPurchase4, Long.valueOf(longValue)));
                                        inAppPurchase3 = inAppPurchase4;
                                        str2 = dedupeParameter$default;
                                        z2 = true;
                                    } else {
                                        inAppPurchase3 = inAppPurchase4;
                                        str2 = dedupeParameter$default;
                                    }
                                }
                            }
                            inAppPurchase = inAppPurchase3;
                            l = l2;
                        } else {
                            inAppPurchase = inAppPurchase3;
                            str = null;
                            l = null;
                            str2 = null;
                            z2 = false;
                        }
                        if (str != null) {
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            bundle.putString(Constants.IAP_TEST_DEDUP_RESULT, "1");
                            bundle.putString(Constants.IAP_TEST_DEDUP_KEY_USED, str);
                        }
                        if (z2) {
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            bundle.putString(Constants.IAP_NON_DEDUPED_EVENT_TIME, String.valueOf(l != null ? l.longValue() / 1000 : 0L));
                            bundle.putString(Constants.IAP_ACTUAL_DEDUP_RESULT, "1");
                            bundle.putString(Constants.IAP_ACTUAL_DEDUP_KEY_USED, str2);
                        }
                        if (!isImplicitlyLogged && !z2) {
                            ConcurrentHashMap<InAppPurchase, List<Pair<Long, Pair<Bundle, OperationalData>>>> concurrentHashMap2 = timesOfImplicitPurchases;
                            if (concurrentHashMap2.get(inAppPurchase) == null) {
                                concurrentHashMap2.put(inAppPurchase, new ArrayList());
                            }
                            List<Pair<Long, Pair<Bundle, OperationalData>>> list5 = concurrentHashMap2.get(inAppPurchase);
                            if (list5 != null) {
                                list5.add(new Pair<>(Long.valueOf(time), new Pair(component1, component2)));
                            }
                        } else if (!isImplicitlyLogged && !z2) {
                            concurrentHashMap = timesOfManualPurchases;
                            if (concurrentHashMap.get(inAppPurchase) == null) {
                                concurrentHashMap.put(inAppPurchase, new ArrayList());
                            }
                            list3 = concurrentHashMap.get(inAppPurchase);
                            if (list3 == null) {
                                list3.add(new Pair<>(Long.valueOf(time), new Pair(component1, component2)));
                            }
                        }
                        i++;
                        str3 = null;
                    }
                    z = true;
                    if (z) {
                    }
                    if (str != null) {
                    }
                    if (z2) {
                    }
                    if (!isImplicitlyLogged) {
                    }
                    if (!isImplicitlyLogged) {
                        concurrentHashMap = timesOfManualPurchases;
                        if (concurrentHashMap.get(inAppPurchase) == null) {
                        }
                        list3 = concurrentHashMap.get(inAppPurchase);
                        if (list3 == null) {
                        }
                    }
                    i++;
                    str3 = null;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Pair pair3 = (Pair) it.next();
                    if (isImplicitlyLogged) {
                        list = timesOfManualPurchases.get(pair3.getFirst());
                    } else {
                        list = timesOfImplicitPurchases.get(pair3.getFirst());
                    }
                    if (list != null) {
                        Iterator<Pair<Long, Pair<Bundle, OperationalData>>> it2 = list.iterator();
                        int i2 = 0;
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            int i3 = i2 + 1;
                            if (it2.next().getFirst().longValue() == ((Number) pair3.getSecond()).longValue()) {
                                list.remove(i2);
                                break;
                            }
                            i2 = i3;
                        }
                        if (isImplicitlyLogged) {
                            if (list.isEmpty()) {
                                timesOfManualPurchases.remove(pair3.getFirst());
                            } else {
                                timesOfManualPurchases.put(pair3.getFirst(), list);
                            }
                        } else if (list.isEmpty()) {
                            timesOfImplicitPurchases.remove(pair3.getFirst());
                        } else {
                            timesOfImplicitPurchases.put(pair3.getFirst(), list);
                        }
                    }
                }
                return bundle;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
                return null;
            }
        }
    }

    public static /* synthetic */ String getDedupeParameter$default(InAppPurchaseManager inAppPurchaseManager, Bundle bundle, OperationalData operationalData, Bundle bundle2, OperationalData operationalData2, boolean z, boolean z2, int i, Object obj) {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
            return null;
        }
        try {
            return inAppPurchaseManager.getDedupeParameter(bundle, operationalData, bundle2, operationalData2, z, (i & 32) != 0 ? false : z2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0054 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0094 A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #0 {all -> 0x009d, blocks: (B:7:0x000a, B:10:0x001a, B:11:0x001e, B:13:0x0024, B:15:0x003c, B:16:0x0040, B:18:0x0047, B:24:0x0054, B:25:0x005e, B:27:0x0064, B:29:0x0076, B:30:0x007a, B:32:0x007f, B:38:0x008c, B:42:0x0094, B:62:0x0011), top: B:5:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0053 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getDedupeParameter(Bundle newPurchaseParameters, OperationalData newPurchaseOperationalData, Bundle oldPurchaseParameters, OperationalData oldPurchaseOperationalData, boolean dedupingWithImplicitlyLoggedHistory, boolean withTestDedupeKeys) {
        List<Pair<String, List<String>>> dedupeParameters;
        boolean z;
        boolean z2;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            if (withTestDedupeKeys) {
                dedupeParameters = InAppPurchaseDedupeConfig.INSTANCE.getTestDedupeParameters(dedupingWithImplicitlyLoggedHistory);
            } else {
                dedupeParameters = InAppPurchaseDedupeConfig.INSTANCE.getDedupeParameters(dedupingWithImplicitlyLoggedHistory);
            }
            if (dedupeParameters == null) {
                return null;
            }
            for (Pair<String, List<String>> pair : dedupeParameters) {
                Object parameter = OperationalData.INSTANCE.getParameter(OperationalDataEnum.IAPParameters, pair.getFirst(), newPurchaseParameters, newPurchaseOperationalData);
                String str = parameter instanceof String ? (String) parameter : null;
                String str2 = str;
                if (str2 != null && str2.length() != 0) {
                    z = false;
                    if (z) {
                        for (String str3 : pair.getSecond()) {
                            Object parameter2 = OperationalData.INSTANCE.getParameter(OperationalDataEnum.IAPParameters, str3, oldPurchaseParameters, oldPurchaseOperationalData);
                            String str4 = parameter2 instanceof String ? (String) parameter2 : null;
                            String str5 = str4;
                            if (str5 != null && str5.length() != 0) {
                                z2 = false;
                                if (!z2 && Intrinsics.areEqual(str4, str)) {
                                    return !dedupingWithImplicitlyLoggedHistory ? pair.getFirst() : str3;
                                }
                            }
                            z2 = true;
                            if (!z2) {
                                if (!dedupingWithImplicitlyLoggedHistory) {
                                }
                            }
                        }
                    }
                }
                z = true;
                if (z) {
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
