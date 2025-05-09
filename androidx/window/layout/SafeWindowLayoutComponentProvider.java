package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: SafeWindowLayoutComponentProvider.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\r\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\r\u0010\u001c\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u001dJ\b\u0010\u001e\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\n¨\u0006\u001f"}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", WorkflowModule.Properties.Section.Component.Type.LOADER, "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;)V", "foldingFeatureClass", "Ljava/lang/Class;", "getFoldingFeatureClass", "()Ljava/lang/Class;", "safeWindowExtensionsProvider", "Landroidx/window/SafeWindowExtensionsProvider;", "windowLayoutComponent", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "windowLayoutComponentClass", "getWindowLayoutComponentClass", "canUseWindowLayoutComponent", "", "hasValidVendorApiLevel1", "hasValidVendorApiLevel1$window_release", "hasValidVendorApiLevel2", "hasValidVendorApiLevel2$window_release", "isFoldingFeatureValid", "isMethodWindowLayoutInfoListenerJavaConsumerValid", "isMethodWindowLayoutInfoListenerWindowConsumerValid", "isWindowLayoutComponentAccessible", "isWindowLayoutComponentAccessible$window_release", "isWindowLayoutProviderValid", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SafeWindowLayoutComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final SafeWindowExtensionsProvider safeWindowExtensionsProvider;

    public SafeWindowLayoutComponentProvider(ClassLoader loader, ConsumerAdapter consumerAdapter) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        this.loader = loader;
        this.consumerAdapter = consumerAdapter;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(loader);
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        if (!canUseWindowLayoutComponent()) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    private final boolean canUseWindowLayoutComponent() {
        if (!isWindowLayoutComponentAccessible$window_release()) {
            return false;
        }
        int safeVendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();
        if (safeVendorApiLevel == 1) {
            return hasValidVendorApiLevel1$window_release();
        }
        if (2 <= safeVendorApiLevel && safeVendorApiLevel <= Integer.MAX_VALUE) {
            return hasValidVendorApiLevel2$window_release();
        }
        return false;
    }

    public final boolean isWindowLayoutComponentAccessible$window_release() {
        return this.safeWindowExtensionsProvider.isWindowExtensionsValid$window_release() && isWindowLayoutProviderValid() && isFoldingFeatureValid();
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return isMethodWindowLayoutInfoListenerJavaConsumerValid();
    }

    public final boolean hasValidVendorApiLevel2$window_release() {
        return hasValidVendorApiLevel1$window_release() && isMethodWindowLayoutInfoListenerWindowConsumerValid();
    }

    private final boolean isWindowLayoutProviderValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowLayoutComponent is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                SafeWindowExtensionsProvider safeWindowExtensionsProvider;
                Class<?> windowLayoutComponentClass;
                safeWindowExtensionsProvider = SafeWindowLayoutComponentProvider.this.safeWindowExtensionsProvider;
                boolean z = false;
                Method getWindowLayoutComponentMethod = safeWindowExtensionsProvider.getWindowExtensionsClass$window_release().getMethod("getWindowLayoutComponent", new Class[0]);
                windowLayoutComponentClass = SafeWindowLayoutComponentProvider.this.getWindowLayoutComponentClass();
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getWindowLayoutComponentMethod, "getWindowLayoutComponentMethod");
                if (reflectionUtils.isPublic$window_release(getWindowLayoutComponentMethod) && ReflectionUtils.INSTANCE.doesReturn$window_release(getWindowLayoutComponentMethod, windowLayoutComponentClass)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isFoldingFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("FoldingFeature class is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Class foldingFeatureClass;
                foldingFeatureClass = SafeWindowLayoutComponentProvider.this.getFoldingFeatureClass();
                boolean z = false;
                Method getBoundsMethod = foldingFeatureClass.getMethod("getBounds", new Class[0]);
                Method getTypeMethod = foldingFeatureClass.getMethod("getType", new Class[0]);
                Method getStateMethod = foldingFeatureClass.getMethod("getState", new Class[0]);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(getBoundsMethod, "getBoundsMethod");
                if (reflectionUtils.doesReturn$window_release(getBoundsMethod, Reflection.getOrCreateKotlinClass(Rect.class)) && ReflectionUtils.INSTANCE.isPublic$window_release(getBoundsMethod)) {
                    ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(getTypeMethod, "getTypeMethod");
                    if (reflectionUtils2.doesReturn$window_release(getTypeMethod, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(getTypeMethod)) {
                        ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(getStateMethod, "getStateMethod");
                        if (reflectionUtils3.doesReturn$window_release(getStateMethod, Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(getStateMethod)) {
                            z = true;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isMethodWindowLayoutInfoListenerJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerJavaConsumerValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                ConsumerAdapter consumerAdapter;
                Class windowLayoutComponentClass;
                consumerAdapter = SafeWindowLayoutComponentProvider.this.consumerAdapter;
                Class<?> consumerClassOrNull$window_release = consumerAdapter.consumerClassOrNull$window_release();
                boolean z = false;
                if (consumerClassOrNull$window_release == null) {
                    return false;
                }
                windowLayoutComponentClass = SafeWindowLayoutComponentProvider.this.getWindowLayoutComponentClass();
                Method addListenerMethod = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Activity.class, consumerClassOrNull$window_release);
                Method removeListenerMethod = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", consumerClassOrNull$window_release);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(addListenerMethod, "addListenerMethod");
                if (reflectionUtils.isPublic$window_release(addListenerMethod)) {
                    ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(removeListenerMethod, "removeListenerMethod");
                    if (reflectionUtils2.isPublic$window_release(removeListenerMethod)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new Function0<Boolean>() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerWindowConsumerValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Class windowLayoutComponentClass;
                windowLayoutComponentClass = SafeWindowLayoutComponentProvider.this.getWindowLayoutComponentClass();
                boolean z = false;
                Method addListenerMethod = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Context.class, Consumer.class);
                Method removeListenerMethod = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", Consumer.class);
                ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(addListenerMethod, "addListenerMethod");
                if (reflectionUtils.isPublic$window_release(addListenerMethod)) {
                    ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(removeListenerMethod, "removeListenerMethod");
                    if (reflectionUtils2.isPublic$window_release(removeListenerMethod)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getFoldingFeatureClass() {
        Class<?> loadClass = this.loader.loadClass(WindowExtensionsConstants.FOLDING_FEATURE_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(FOLDING_FEATURE_CLASS)");
        return loadClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getWindowLayoutComponentClass() {
        Class<?> loadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_LAYOUT_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
        return loadClass;
    }
}
