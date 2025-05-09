package com.facebook.appevents.iap;

import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InAppPurchaseSkuDetailsWrapper.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B=\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\"\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0011R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"}, d2 = {"Lcom/facebook/appevents/iap/InAppPurchaseSkuDetailsWrapper;", "", "skuDetailsParamsClazz", "Ljava/lang/Class;", "builderClazz", "newBuilderMethod", "Ljava/lang/reflect/Method;", "setTypeMethod", "setSkusListMethod", "buildMethod", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getSkuDetailsParamsClazz", "()Ljava/lang/Class;", "getSkuDetailsParams", "productType", "Lcom/facebook/appevents/iap/InAppPurchaseUtils$IAPProductType;", "skuIDs", "", "", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InAppPurchaseSkuDetailsWrapper {
    private static final String CLASSNAME_SKU_DETAILS_PARAMS = "com.android.billingclient.api.SkuDetailsParams";
    private static final String CLASSNAME_SKU_DETAILS_PARAMS_BUILDER = "com.android.billingclient.api.SkuDetailsParams$Builder";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String METHOD_BUILD = "build";
    private static final String METHOD_NEW_BUILDER = "newBuilder";
    private static final String METHOD_SET_SKU_LIST = "setSkusList";
    private static final String METHOD_SET_TYPE = "setType";
    private static InAppPurchaseSkuDetailsWrapper instance;
    private final Method buildMethod;
    private final Class<?> builderClazz;
    private final Method newBuilderMethod;
    private final Method setSkusListMethod;
    private final Method setTypeMethod;
    private final Class<?> skuDetailsParamsClazz;

    @JvmStatic
    public static final synchronized InAppPurchaseSkuDetailsWrapper getOrCreateInstance() {
        synchronized (InAppPurchaseSkuDetailsWrapper.class) {
            if (CrashShieldHandler.isObjectCrashing(InAppPurchaseSkuDetailsWrapper.class)) {
                return null;
            }
            try {
                return INSTANCE.getOrCreateInstance();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, InAppPurchaseSkuDetailsWrapper.class);
                return null;
            }
        }
    }

    public InAppPurchaseSkuDetailsWrapper(Class<?> skuDetailsParamsClazz, Class<?> builderClazz, Method newBuilderMethod, Method setTypeMethod, Method setSkusListMethod, Method buildMethod) {
        Intrinsics.checkNotNullParameter(skuDetailsParamsClazz, "skuDetailsParamsClazz");
        Intrinsics.checkNotNullParameter(builderClazz, "builderClazz");
        Intrinsics.checkNotNullParameter(newBuilderMethod, "newBuilderMethod");
        Intrinsics.checkNotNullParameter(setTypeMethod, "setTypeMethod");
        Intrinsics.checkNotNullParameter(setSkusListMethod, "setSkusListMethod");
        Intrinsics.checkNotNullParameter(buildMethod, "buildMethod");
        this.skuDetailsParamsClazz = skuDetailsParamsClazz;
        this.builderClazz = builderClazz;
        this.newBuilderMethod = newBuilderMethod;
        this.setTypeMethod = setTypeMethod;
        this.setSkusListMethod = setSkusListMethod;
        this.buildMethod = buildMethod;
    }

    public static final /* synthetic */ InAppPurchaseSkuDetailsWrapper access$getInstance$cp() {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseSkuDetailsWrapper.class)) {
            return null;
        }
        try {
            return instance;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseSkuDetailsWrapper.class);
            return null;
        }
    }

    public static final /* synthetic */ void access$setInstance$cp(InAppPurchaseSkuDetailsWrapper inAppPurchaseSkuDetailsWrapper) {
        if (CrashShieldHandler.isObjectCrashing(InAppPurchaseSkuDetailsWrapper.class)) {
            return;
        }
        try {
            instance = inAppPurchaseSkuDetailsWrapper;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, InAppPurchaseSkuDetailsWrapper.class);
        }
    }

    public final Class<?> getSkuDetailsParamsClazz() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.skuDetailsParamsClazz;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final Object getSkuDetailsParams(InAppPurchaseUtils.IAPProductType productType, List<String> skuIDs) {
        Object invokeMethod;
        Object invokeMethod2;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(productType, "productType");
            Object invokeMethod3 = InAppPurchaseUtils.invokeMethod(this.skuDetailsParamsClazz, this.newBuilderMethod, null, new Object[0]);
            if (invokeMethod3 != null && (invokeMethod = InAppPurchaseUtils.invokeMethod(this.builderClazz, this.setTypeMethod, invokeMethod3, productType.getType())) != null && (invokeMethod2 = InAppPurchaseUtils.invokeMethod(this.builderClazz, this.setSkusListMethod, invokeMethod, skuIDs)) != null) {
                return InAppPurchaseUtils.invokeMethod(this.builderClazz, this.buildMethod, invokeMethod2, new Object[0]);
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* compiled from: InAppPurchaseSkuDetailsWrapper.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\n\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/appevents/iap/InAppPurchaseSkuDetailsWrapper$Companion;", "", "()V", "CLASSNAME_SKU_DETAILS_PARAMS", "", "CLASSNAME_SKU_DETAILS_PARAMS_BUILDER", "METHOD_BUILD", "METHOD_NEW_BUILDER", "METHOD_SET_SKU_LIST", "METHOD_SET_TYPE", "instance", "Lcom/facebook/appevents/iap/InAppPurchaseSkuDetailsWrapper;", "createInstance", "getOrCreateInstance", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final synchronized InAppPurchaseSkuDetailsWrapper getOrCreateInstance() {
            InAppPurchaseSkuDetailsWrapper access$getInstance$cp;
            access$getInstance$cp = InAppPurchaseSkuDetailsWrapper.access$getInstance$cp();
            if (access$getInstance$cp == null) {
                access$getInstance$cp = createInstance();
            }
            return access$getInstance$cp;
        }

        private final InAppPurchaseSkuDetailsWrapper createInstance() {
            Class<?> cls = InAppPurchaseUtils.getClass(InAppPurchaseSkuDetailsWrapper.CLASSNAME_SKU_DETAILS_PARAMS);
            Class<?> cls2 = InAppPurchaseUtils.getClass(InAppPurchaseSkuDetailsWrapper.CLASSNAME_SKU_DETAILS_PARAMS_BUILDER);
            if (cls == null || cls2 == null) {
                return null;
            }
            Method method = InAppPurchaseUtils.getMethod(cls, "newBuilder", new Class[0]);
            Method method2 = InAppPurchaseUtils.getMethod(cls2, InAppPurchaseSkuDetailsWrapper.METHOD_SET_TYPE, String.class);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, InAppPurchaseSkuDetailsWrapper.METHOD_SET_SKU_LIST, List.class);
            Method method4 = InAppPurchaseUtils.getMethod(cls2, "build", new Class[0]);
            if (method == null || method2 == null || method3 == null || method4 == null) {
                return null;
            }
            InAppPurchaseSkuDetailsWrapper.access$setInstance$cp(new InAppPurchaseSkuDetailsWrapper(cls, cls2, method, method2, method3, method4));
            return InAppPurchaseSkuDetailsWrapper.access$getInstance$cp();
        }
    }
}
