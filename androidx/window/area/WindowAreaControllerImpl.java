package androidx.window.area;

import android.app.Activity;
import android.os.Binder;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.window.area.WindowAreaCapability;
import androidx.window.area.WindowAreaControllerImpl;
import androidx.window.area.WindowAreaInfo;
import androidx.window.area.utils.DeviceUtils;
import androidx.window.core.BuildConfig;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import androidx.window.extensions.area.ExtensionWindowAreaStatus;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import io.sentry.cache.EnvelopeCache;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: WindowAreaControllerImpl.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 22\u00020\u0001:\u0003234B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\rH\u0002J \u0010#\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020%H\u0002J \u0010&\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J(\u0010'\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,H\u0002J \u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020/2\u0006\u0010)\u001a\u00020\b2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u00065"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl;", "Landroidx/window/area/WindowAreaController;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "vendorApiLevel", "", "(Landroidx/window/extensions/area/WindowAreaComponent;I)V", "currentRearDisplayModeStatus", "Landroidx/window/area/WindowAreaCapability$Status;", "currentRearDisplayPresentationStatus", "currentWindowAreaInfoMap", "Ljava/util/HashMap;", "", "Landroidx/window/area/WindowAreaInfo;", "Lkotlin/collections/HashMap;", "rearDisplaySessionConsumer", "Landroidx/window/extensions/core/util/function/Consumer;", "windowAreaInfos", "Lkotlinx/coroutines/flow/Flow;", "", "getWindowAreaInfos", "()Lkotlinx/coroutines/flow/Flow;", "presentContentOnWindowArea", "", "token", "Landroid/os/Binder;", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "shouldRemoveWindowAreaInfo", "", "windowAreaInfo", "startRearDisplayMode", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "startRearDisplayPresentationMode", "transferActivityToWindowArea", "updateRearDisplayAvailability", "status", "updateRearDisplayPresentationAvailability", "extensionWindowAreaStatus", "Landroidx/window/extensions/area/ExtensionWindowAreaStatus;", "updateRearDisplayWindowArea", "operation", "Landroidx/window/area/WindowAreaCapability$Operation;", "metrics", "Landroidx/window/layout/WindowMetrics;", "Companion", "RearDisplayPresentationSessionConsumer", "RearDisplaySessionConsumer", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WindowAreaControllerImpl implements WindowAreaController {
    private static final String REAR_DISPLAY_BINDER_DESCRIPTOR = "WINDOW_AREA_REAR_DISPLAY";
    private WindowAreaCapability.Status currentRearDisplayModeStatus;
    private WindowAreaCapability.Status currentRearDisplayPresentationStatus;
    private final HashMap<String, WindowAreaInfo> currentWindowAreaInfoMap;
    private Consumer<Integer> rearDisplaySessionConsumer;
    private final int vendorApiLevel;
    private final WindowAreaComponent windowAreaComponent;
    private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaControllerImpl.class).getSimpleName();

    public WindowAreaControllerImpl(WindowAreaComponent windowAreaComponent, int i) {
        Intrinsics.checkNotNullParameter(windowAreaComponent, "windowAreaComponent");
        this.windowAreaComponent = windowAreaComponent;
        this.vendorApiLevel = i;
        this.currentRearDisplayModeStatus = WindowAreaCapability.Status.INSTANCE.getWINDOW_AREA_STATUS_UNKNOWN$window_release();
        this.currentRearDisplayPresentationStatus = WindowAreaCapability.Status.INSTANCE.getWINDOW_AREA_STATUS_UNKNOWN$window_release();
        this.currentWindowAreaInfoMap = new HashMap<>();
    }

    @Override // androidx.window.area.WindowAreaController
    public Flow<List<WindowAreaInfo>> getWindowAreaInfos() {
        return FlowKt.callbackFlow(new WindowAreaControllerImpl$windowAreaInfos$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRearDisplayAvailability(int status) {
        WindowMetrics fromDisplayMetrics$window_release;
        if (this.vendorApiLevel >= 3) {
            WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.INSTANCE;
            DisplayMetrics rearDisplayMetrics = this.windowAreaComponent.getRearDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(rearDisplayMetrics, "windowAreaComponent.rearDisplayMetrics");
            fromDisplayMetrics$window_release = companion.fromDisplayMetrics$window_release(rearDisplayMetrics);
        } else {
            DeviceUtils deviceUtils = DeviceUtils.INSTANCE;
            String MANUFACTURER = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
            String MODEL = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
            DisplayMetrics rearDisplayMetrics$window_release = deviceUtils.getRearDisplayMetrics$window_release(MANUFACTURER, MODEL);
            if (rearDisplayMetrics$window_release != null) {
                fromDisplayMetrics$window_release = WindowMetricsCalculator.INSTANCE.fromDisplayMetrics$window_release(rearDisplayMetrics$window_release);
            } else {
                throw new IllegalArgumentException("DeviceUtils rear display metrics entry should not be null");
            }
        }
        this.currentRearDisplayModeStatus = WindowAreaAdapter.INSTANCE.translate$window_release(status);
        updateRearDisplayWindowArea(WindowAreaCapability.Operation.OPERATION_TRANSFER_ACTIVITY_TO_AREA, this.currentRearDisplayModeStatus, fromDisplayMetrics$window_release);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRearDisplayPresentationAvailability(ExtensionWindowAreaStatus extensionWindowAreaStatus) {
        this.currentRearDisplayPresentationStatus = WindowAreaAdapter.INSTANCE.translate$window_release(extensionWindowAreaStatus.getWindowAreaStatus());
        WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.INSTANCE;
        DisplayMetrics windowAreaDisplayMetrics = extensionWindowAreaStatus.getWindowAreaDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(windowAreaDisplayMetrics, "extensionWindowAreaStatus.windowAreaDisplayMetrics");
        updateRearDisplayWindowArea(WindowAreaCapability.Operation.OPERATION_PRESENT_ON_AREA, this.currentRearDisplayPresentationStatus, companion.fromDisplayMetrics$window_release(windowAreaDisplayMetrics));
    }

    private final void updateRearDisplayWindowArea(WindowAreaCapability.Operation operation, WindowAreaCapability.Status status, WindowMetrics metrics) {
        WindowAreaInfo windowAreaInfo = this.currentWindowAreaInfoMap.get(REAR_DISPLAY_BINDER_DESCRIPTOR);
        if (!Intrinsics.areEqual(status, WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED)) {
            if (windowAreaInfo == null) {
                windowAreaInfo = new WindowAreaInfo(metrics, WindowAreaInfo.Type.TYPE_REAR_FACING, new Binder(REAR_DISPLAY_BINDER_DESCRIPTOR), this.windowAreaComponent);
            }
            windowAreaInfo.getCapabilityMap$window_release().put(operation, new WindowAreaCapability(operation, status));
            windowAreaInfo.setMetrics(metrics);
            this.currentWindowAreaInfoMap.put(REAR_DISPLAY_BINDER_DESCRIPTOR, windowAreaInfo);
            return;
        }
        if (windowAreaInfo != null) {
            if (shouldRemoveWindowAreaInfo(windowAreaInfo)) {
                this.currentWindowAreaInfoMap.remove(REAR_DISPLAY_BINDER_DESCRIPTOR);
            } else {
                windowAreaInfo.getCapabilityMap$window_release().put(operation, new WindowAreaCapability(operation, status));
            }
        }
    }

    private final boolean shouldRemoveWindowAreaInfo(WindowAreaInfo windowAreaInfo) {
        for (WindowAreaCapability windowAreaCapability : windowAreaInfo.getCapabilityMap$window_release().values()) {
            Intrinsics.checkNotNullExpressionValue(windowAreaCapability, "windowAreaInfo.capabilityMap.values");
            if (!Intrinsics.areEqual(windowAreaCapability.getStatus(), WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.window.area.WindowAreaController
    public void transferActivityToWindowArea(Binder token, Activity activity, Executor executor, final WindowAreaSessionCallback windowAreaSessionCallback) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "windowAreaSessionCallback");
        if (!Intrinsics.areEqual(token.getInterfaceDescriptor(), REAR_DISPLAY_BINDER_DESCRIPTOR)) {
            executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.transferActivityToWindowArea$lambda$1(WindowAreaSessionCallback.this);
                }
            });
        } else if (Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.INSTANCE.getWINDOW_AREA_STATUS_UNKNOWN$window_release())) {
            Log.d(TAG, "Force updating currentRearDisplayModeStatus");
            BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executor)), null, null, new WindowAreaControllerImpl$transferActivityToWindowArea$2(this, activity, executor, windowAreaSessionCallback, null), 3, null);
        } else {
            startRearDisplayMode(activity, executor, windowAreaSessionCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void transferActivityToWindowArea$lambda$1(WindowAreaSessionCallback windowAreaSessionCallback) {
        Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "$windowAreaSessionCallback");
        windowAreaSessionCallback.onSessionEnded(new IllegalArgumentException("Invalid WindowAreaInfo token"));
    }

    @Override // androidx.window.area.WindowAreaController
    public void presentContentOnWindowArea(Binder token, Activity activity, Executor executor, final WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaPresentationSessionCallback, "windowAreaPresentationSessionCallback");
        if (!Intrinsics.areEqual(token.getInterfaceDescriptor(), REAR_DISPLAY_BINDER_DESCRIPTOR)) {
            executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.presentContentOnWindowArea$lambda$2(WindowAreaPresentationSessionCallback.this);
                }
            });
        } else if (Intrinsics.areEqual(this.currentRearDisplayPresentationStatus, WindowAreaCapability.Status.INSTANCE.getWINDOW_AREA_STATUS_UNKNOWN$window_release())) {
            Log.d(TAG, "Force updating currentRearDisplayPresentationStatus");
            BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executor)), null, null, new WindowAreaControllerImpl$presentContentOnWindowArea$2(this, activity, executor, windowAreaPresentationSessionCallback, null), 3, null);
        } else {
            startRearDisplayPresentationMode(activity, executor, windowAreaPresentationSessionCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void presentContentOnWindowArea$lambda$2(WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        Intrinsics.checkNotNullParameter(windowAreaPresentationSessionCallback, "$windowAreaPresentationSessionCallback");
        windowAreaPresentationSessionCallback.onSessionEnded(new IllegalArgumentException("Invalid WindowAreaInfo token"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback) {
        if (Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_ACTIVE)) {
            windowAreaSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently active, WindowAreaInfo#getActiveSessioncan be used to get an instance of the current active session"));
        } else {
            if (!Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_AVAILABLE)) {
                windowAreaSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently not available to be entered"));
                return;
            }
            RearDisplaySessionConsumer rearDisplaySessionConsumer = new RearDisplaySessionConsumer(executor, windowAreaSessionCallback, this.windowAreaComponent);
            this.rearDisplaySessionConsumer = rearDisplaySessionConsumer;
            this.windowAreaComponent.startRearDisplaySession(activity, rearDisplaySessionConsumer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRearDisplayPresentationMode(Activity activity, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        if (!Intrinsics.areEqual(this.currentRearDisplayPresentationStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_AVAILABLE)) {
            windowAreaPresentationSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently not available to be entered"));
        } else {
            WindowAreaComponent windowAreaComponent = this.windowAreaComponent;
            windowAreaComponent.startRearDisplayPresentationSession(activity, new RearDisplayPresentationSessionConsumer(executor, windowAreaPresentationSessionCallback, windowAreaComponent));
        }
    }

    /* compiled from: WindowAreaControllerImpl.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplaySessionConsumer;", "Landroidx/window/extensions/core/util/function/Consumer;", "", "executor", "Ljava/util/concurrent/Executor;", "appCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "extensionsComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "(Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Landroidx/window/area/WindowAreaSession;", "accept", "", "t", "onSessionFinished", "onSessionStarted", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class RearDisplaySessionConsumer implements Consumer<Integer> {
        private final WindowAreaSessionCallback appCallback;
        private final Executor executor;
        private final WindowAreaComponent extensionsComponent;
        private WindowAreaSession session;

        public RearDisplaySessionConsumer(Executor executor, WindowAreaSessionCallback appCallback, WindowAreaComponent extensionsComponent) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(appCallback, "appCallback");
            Intrinsics.checkNotNullParameter(extensionsComponent, "extensionsComponent");
            this.executor = executor;
            this.appCallback = appCallback;
            this.extensionsComponent = extensionsComponent;
        }

        @Override // androidx.window.extensions.core.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }

        public void accept(int t) {
            if (t == 0) {
                onSessionFinished();
                return;
            }
            if (t == 1) {
                onSessionStarted();
                return;
            }
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                Log.d(WindowAreaControllerImpl.TAG, "Received an unknown session status value: " + t);
            }
            onSessionFinished();
        }

        private final void onSessionStarted() {
            final RearDisplaySessionImpl rearDisplaySessionImpl = new RearDisplaySessionImpl(this.extensionsComponent);
            this.session = rearDisplaySessionImpl;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionStarted$lambda$1$lambda$0(WindowAreaControllerImpl.RearDisplaySessionConsumer.this, rearDisplaySessionImpl);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSessionStarted$lambda$1$lambda$0(RearDisplaySessionConsumer this$0, WindowAreaSession it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "$it");
            this$0.appCallback.onSessionStarted(it);
        }

        private final void onSessionFinished() {
            this.session = null;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionFinished$lambda$2(WindowAreaControllerImpl.RearDisplaySessionConsumer.this);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSessionFinished$lambda$2(RearDisplaySessionConsumer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.appCallback.onSessionEnded(null);
        }
    }

    /* compiled from: WindowAreaControllerImpl.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplayPresentationSessionConsumer;", "Landroidx/window/extensions/core/util/function/Consumer;", "", "executor", "Ljava/util/concurrent/Executor;", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "(Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaPresentationSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", "lastReportedSessionStatus", "accept", "", "t", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class RearDisplayPresentationSessionConsumer implements Consumer<Integer> {
        private final Executor executor;
        private int lastReportedSessionStatus;
        private final WindowAreaComponent windowAreaComponent;
        private final WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback;

        public RearDisplayPresentationSessionConsumer(Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback, WindowAreaComponent windowAreaComponent) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(windowAreaPresentationSessionCallback, "windowAreaPresentationSessionCallback");
            Intrinsics.checkNotNullParameter(windowAreaComponent, "windowAreaComponent");
            this.executor = executor;
            this.windowAreaPresentationSessionCallback = windowAreaPresentationSessionCallback;
            this.windowAreaComponent = windowAreaComponent;
        }

        @Override // androidx.window.extensions.core.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }

        public void accept(final int t) {
            final int i = this.lastReportedSessionStatus;
            this.lastReportedSessionStatus = t;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplayPresentationSessionConsumer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplayPresentationSessionConsumer.accept$lambda$0(t, i, this);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void accept$lambda$0(int i, int i2, RearDisplayPresentationSessionConsumer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (i == 0) {
                this$0.windowAreaPresentationSessionCallback.onSessionEnded(null);
                return;
            }
            if (i != 1) {
                if (i != 2) {
                    Log.e(WindowAreaControllerImpl.TAG, "Invalid session state value received: " + i);
                    return;
                }
                this$0.windowAreaPresentationSessionCallback.onContainerVisibilityChanged(true);
                return;
            }
            if (i2 == 2) {
                this$0.windowAreaPresentationSessionCallback.onContainerVisibilityChanged(false);
                return;
            }
            WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback = this$0.windowAreaPresentationSessionCallback;
            WindowAreaComponent windowAreaComponent = this$0.windowAreaComponent;
            ExtensionWindowAreaPresentation rearDisplayPresentation = windowAreaComponent.getRearDisplayPresentation();
            Intrinsics.checkNotNull(rearDisplayPresentation);
            windowAreaPresentationSessionCallback.onSessionStarted(new RearDisplayPresentationSessionPresenterImpl(windowAreaComponent, rearDisplayPresentation));
        }
    }
}
