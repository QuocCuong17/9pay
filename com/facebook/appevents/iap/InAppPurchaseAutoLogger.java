package com.facebook.appevents.iap;

import android.content.Context;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.appevents.integrity.ProtectedModeManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: InAppPurchaseAutoLogger.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/facebook/appevents/iap/InAppPurchaseAutoLogger;", "", "()V", "failedToCreateWrapper", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getFailedToCreateWrapper", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "logPurchase", "", "billingClientVersion", "Lcom/facebook/appevents/iap/InAppPurchaseUtils$BillingClientVersion;", "packageName", "", "startIapLogging", "context", "Landroid/content/Context;", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InAppPurchaseAutoLogger {
    public static final InAppPurchaseAutoLogger INSTANCE = new InAppPurchaseAutoLogger();
    private static final AtomicBoolean failedToCreateWrapper = new AtomicBoolean(false);

    private InAppPurchaseAutoLogger() {
    }

    public final AtomicBoolean getFailedToCreateWrapper() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return failedToCreateWrapper;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [T, com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV5V7] */
    /* JADX WARN: Type inference failed for: r3v8, types: [com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV2V4, T] */
    @JvmStatic
    public static final synchronized void startIapLogging(final Context context, final InAppPurchaseUtils.BillingClientVersion billingClientVersion) {
        synchronized (InAppPurchaseAutoLogger.class) {
            if (CrashShieldHandler.isObjectCrashing(InAppPurchaseAutoLogger.class)) {
                return;
            }
            try {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(billingClientVersion, "billingClientVersion");
                AtomicBoolean atomicBoolean = failedToCreateWrapper;
                if (atomicBoolean.get()) {
                    return;
                }
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (billingClientVersion == InAppPurchaseUtils.BillingClientVersion.V2_V4) {
                    objectRef.element = InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getOrCreateInstance(context);
                } else if (billingClientVersion == InAppPurchaseUtils.BillingClientVersion.V5_V7) {
                    objectRef.element = InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getOrCreateInstance(context);
                }
                if (objectRef.element == 0) {
                    atomicBoolean.set(true);
                    return;
                }
                if (FeatureManager.isEnabled(FeatureManager.Feature.AndroidIAPSubscriptionAutoLogging) && (!ProtectedModeManager.isEnabled() || billingClientVersion == InAppPurchaseUtils.BillingClientVersion.V2_V4)) {
                    ((InAppPurchaseBillingClientWrapper) objectRef.element).queryPurchaseHistory(InAppPurchaseUtils.IAPProductType.INAPP, new Runnable() { // from class: com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            InAppPurchaseAutoLogger.startIapLogging$lambda$1(Ref.ObjectRef.this, billingClientVersion, context);
                        }
                    });
                } else {
                    ((InAppPurchaseBillingClientWrapper) objectRef.element).queryPurchaseHistory(InAppPurchaseUtils.IAPProductType.INAPP, new Runnable() { // from class: com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            InAppPurchaseAutoLogger.startIapLogging$lambda$2(InAppPurchaseUtils.BillingClientVersion.this, context);
                        }
                    });
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, InAppPurchaseAutoLogger.class);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startIapLogging$lambda$1(Ref.ObjectRef billingClientWrapper, final InAppPurchaseUtils.BillingClientVersion billingClientVersion, final Context context) {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseAutoLogger.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(billingClientWrapper, "$billingClientWrapper");
            Intrinsics.checkNotNullParameter(billingClientVersion, "$billingClientVersion");
            Intrinsics.checkNotNullParameter(context, "$context");
            ((InAppPurchaseBillingClientWrapper) billingClientWrapper.element).queryPurchaseHistory(InAppPurchaseUtils.IAPProductType.SUBS, new Runnable() { // from class: com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    InAppPurchaseAutoLogger.startIapLogging$lambda$1$lambda$0(InAppPurchaseUtils.BillingClientVersion.this, context);
                }
            });
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseAutoLogger.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startIapLogging$lambda$1$lambda$0(InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseAutoLogger.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(billingClientVersion, "$billingClientVersion");
            Intrinsics.checkNotNullParameter(context, "$context");
            InAppPurchaseAutoLogger inAppPurchaseAutoLogger = INSTANCE;
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
            inAppPurchaseAutoLogger.logPurchase(billingClientVersion, packageName);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseAutoLogger.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startIapLogging$lambda$2(InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseAutoLogger.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(billingClientVersion, "$billingClientVersion");
            Intrinsics.checkNotNullParameter(context, "$context");
            InAppPurchaseAutoLogger inAppPurchaseAutoLogger = INSTANCE;
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
            inAppPurchaseAutoLogger.logPurchase(billingClientVersion, packageName);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseAutoLogger.class);
        }
    }

    private final void logPurchase(InAppPurchaseUtils.BillingClientVersion billingClientVersion, String packageName) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            boolean isFirstAppLaunchWithNewIAP = InAppPurchaseLoggerManager.getIsFirstAppLaunchWithNewIAP();
            if (isFirstAppLaunchWithNewIAP) {
                InAppPurchaseLoggerManager.migrateOldCacheHistory();
            }
            if (billingClientVersion == InAppPurchaseUtils.BillingClientVersion.V2_V4) {
                InAppPurchaseLoggerManager.filterPurchaseLogging(InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getIapPurchaseDetailsMap(), InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getSkuDetailsMap(), false, packageName, billingClientVersion, isFirstAppLaunchWithNewIAP);
                InAppPurchaseLoggerManager.filterPurchaseLogging(InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getSubsPurchaseDetailsMap(), InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getSkuDetailsMap(), true, packageName, billingClientVersion, isFirstAppLaunchWithNewIAP);
                InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getIapPurchaseDetailsMap().clear();
                InAppPurchaseBillingClientWrapperV2V4.INSTANCE.getSubsPurchaseDetailsMap().clear();
            } else {
                InAppPurchaseLoggerManager.filterPurchaseLogging(InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getIapPurchaseDetailsMap(), InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getProductDetailsMap(), false, packageName, billingClientVersion, isFirstAppLaunchWithNewIAP);
                InAppPurchaseLoggerManager.filterPurchaseLogging(InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getSubsPurchaseDetailsMap(), InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getProductDetailsMap(), true, packageName, billingClientVersion, isFirstAppLaunchWithNewIAP);
                InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getIapPurchaseDetailsMap().clear();
                InAppPurchaseBillingClientWrapperV5V7.INSTANCE.getSubsPurchaseDetailsMap().clear();
            }
            if (isFirstAppLaunchWithNewIAP) {
                InAppPurchaseLoggerManager.setAppHasBeenLaunchedWithNewIAP();
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }
}
