package androidx.window.reflection;

import android.util.Log;
import androidx.media3.exoplayer.offline.DownloadService;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: ReflectionUtils.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\b\u001a\u00020\u00042\u0010\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\nH\u0000¢\u0006\u0002\b\fJ%\u0010\r\u001a\u00020\u00042\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0000¢\u0006\u0002\b\u0010J'\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0001¢\u0006\u0002\b\u0015J\u001d\u0010\u0016\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0000¢\u0006\u0002\b\u0018J\u001d\u0010\u0016\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0000¢\u0006\u0002\b\u0018R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001a"}, d2 = {"Landroidx/window/reflection/ReflectionUtils;", "", "()V", "isPublic", "", "Ljava/lang/reflect/Method;", "isPublic$window_release", "(Ljava/lang/reflect/Method;)Z", "checkIsPresent", "classLoader", "Lkotlin/Function0;", "Ljava/lang/Class;", "checkIsPresent$window_release", "validateImplementation", "implementation", DownloadService.KEY_REQUIREMENTS, "validateImplementation$window_release", "validateReflection", "errorMessage", "", "block", "validateReflection$window_release", "doesReturn", "clazz", "doesReturn$window_release", "Lkotlin/reflect/KClass;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReflectionUtils {
    public static final ReflectionUtils INSTANCE = new ReflectionUtils();

    private ReflectionUtils() {
    }

    public final boolean checkIsPresent$window_release(Function0<? extends Class<?>> classLoader) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        try {
            classLoader.invoke();
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static /* synthetic */ boolean validateReflection$window_release$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return validateReflection$window_release(str, function0);
    }

    @JvmStatic
    public static final boolean validateReflection$window_release(String errorMessage, Function0<Boolean> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            boolean booleanValue = block.invoke().booleanValue();
            if (!booleanValue && errorMessage != null) {
                Log.e("ReflectionGuard", errorMessage);
            }
            return booleanValue;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("ClassNotFound: ");
            if (errorMessage == null) {
                errorMessage = "";
            }
            sb.append(errorMessage);
            Log.e("ReflectionGuard", sb.toString());
            return false;
        } catch (NoSuchMethodException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("NoSuchMethod: ");
            if (errorMessage == null) {
                errorMessage = "";
            }
            sb2.append(errorMessage);
            Log.e("ReflectionGuard", sb2.toString());
            return false;
        }
    }

    public final boolean isPublic$window_release(Method method) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        return Modifier.isPublic(method.getModifiers());
    }

    public final boolean doesReturn$window_release(Method method, KClass<?> clazz) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return doesReturn$window_release(method, JvmClassMappingKt.getJavaClass((KClass) clazz));
    }

    public final boolean doesReturn$window_release(Method method, Class<?> clazz) {
        Intrinsics.checkNotNullParameter(method, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return method.getReturnType().equals(clazz);
    }

    public final boolean validateImplementation$window_release(final Class<?> implementation, Class<?> requirements) {
        Intrinsics.checkNotNullParameter(implementation, "implementation");
        Intrinsics.checkNotNullParameter(requirements, "requirements");
        Method[] methods = requirements.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "requirements.methods");
        for (final Method method : methods) {
            if (!validateReflection$window_release(implementation.getName() + '#' + method.getName() + " is not valid", new Function0<Boolean>() { // from class: androidx.window.reflection.ReflectionUtils$validateImplementation$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    boolean z;
                    Class<?> cls = implementation;
                    String name = method.getName();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Method implementedMethod = cls.getMethod(name, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
                    ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(implementedMethod, "implementedMethod");
                    if (reflectionUtils.isPublic$window_release(implementedMethod)) {
                        ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
                        Class<?> returnType = method.getReturnType();
                        Intrinsics.checkNotNullExpressionValue(returnType, "it.returnType");
                        if (reflectionUtils2.doesReturn$window_release(implementedMethod, returnType)) {
                            z = true;
                            return Boolean.valueOf(z);
                        }
                    }
                    z = false;
                    return Boolean.valueOf(z);
                }
            })) {
                return false;
            }
        }
        return true;
    }
}
