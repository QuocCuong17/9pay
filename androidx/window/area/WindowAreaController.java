package androidx.window.area;

import android.app.Activity;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import androidx.window.area.utils.DeviceUtils;
import androidx.window.core.BuildConfig;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.WindowAreaComponent;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

/* compiled from: WindowAreaController.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J(\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H&R\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Landroidx/window/area/WindowAreaController;", "", "windowAreaInfos", "Lkotlinx/coroutines/flow/Flow;", "", "Landroidx/window/area/WindowAreaInfo;", "getWindowAreaInfos", "()Lkotlinx/coroutines/flow/Flow;", "presentContentOnWindowArea", "", "token", "Landroid/os/Binder;", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "transferActivityToWindowArea", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface WindowAreaController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: WindowAreaController.kt */
    /* renamed from: androidx.window.area.WindowAreaController$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        static {
            Companion companion = WindowAreaController.INSTANCE;
        }

        @JvmStatic
        public static WindowAreaController getOrCreate() {
            return WindowAreaController.INSTANCE.getOrCreate();
        }

        @JvmStatic
        public static void overrideDecorator(WindowAreaControllerDecorator windowAreaControllerDecorator) {
            WindowAreaController.INSTANCE.overrideDecorator(windowAreaControllerDecorator);
        }

        @JvmStatic
        public static void reset() {
            WindowAreaController.INSTANCE.reset();
        }
    }

    Flow<List<WindowAreaInfo>> getWindowAreaInfos();

    void presentContentOnWindowArea(Binder token, Activity activity, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback);

    void transferActivityToWindowArea(Binder token, Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback);

    /* compiled from: WindowAreaController.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J\b\u0010\f\u001a\u00020\nH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/area/WindowAreaController$Companion;", "", "()V", "TAG", "", "decorator", "Landroidx/window/area/WindowAreaControllerDecorator;", "getOrCreate", "Landroidx/window/area/WindowAreaController;", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaController.class).getSimpleName();
        private static WindowAreaControllerDecorator decorator = EmptyDecorator.INSTANCE;

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x004b, code lost:
        
            if (r1.hasDeviceMetrics$window_release(r2, r3) != false) goto L17;
         */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0063  */
        @JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final WindowAreaController getOrCreate() {
            boolean z;
            EmptyWindowAreaControllerImpl emptyWindowAreaControllerImpl;
            WindowAreaComponent windowAreaComponent = null;
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                if (classLoader != null) {
                    windowAreaComponent = new SafeWindowAreaComponentProvider(classLoader).getWindowAreaComponent();
                }
            } catch (Throwable unused) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.d(TAG, "Failed to load WindowExtensions");
                }
            }
            if (Build.VERSION.SDK_INT > 29 && windowAreaComponent != null) {
                if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 3) {
                    DeviceUtils deviceUtils = DeviceUtils.INSTANCE;
                    String MANUFACTURER = Build.MANUFACTURER;
                    Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
                    String MODEL = Build.MODEL;
                    Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
                }
                z = true;
                if (!z) {
                    Intrinsics.checkNotNull(windowAreaComponent);
                    emptyWindowAreaControllerImpl = new WindowAreaControllerImpl(windowAreaComponent, ExtensionsUtil.INSTANCE.getSafeVendorApiLevel());
                } else {
                    emptyWindowAreaControllerImpl = new EmptyWindowAreaControllerImpl();
                }
                return decorator.decorate(emptyWindowAreaControllerImpl);
            }
            z = false;
            if (!z) {
            }
            return decorator.decorate(emptyWindowAreaControllerImpl);
        }

        @JvmStatic
        public final void overrideDecorator(WindowAreaControllerDecorator overridingDecorator) {
            Intrinsics.checkNotNullParameter(overridingDecorator, "overridingDecorator");
            decorator = overridingDecorator;
        }

        @JvmStatic
        public final void reset() {
            decorator = EmptyDecorator.INSTANCE;
        }
    }
}
