package net.touchcapture.qr.flutterqr;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QrActivityLifecycleCallbacks.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¨\u0006\u0007"}, d2 = {"registerLifecycleCallbacks", "Lnet/touchcapture/qr/flutterqr/UnRegisterLifecycleCallback;", "Landroid/app/Activity;", "onPause", "Lkotlin/Function0;", "", "onResume", "qr_code_scanner_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QrActivityLifecycleCallbacksKt {
    public static /* synthetic */ UnRegisterLifecycleCallback registerLifecycleCallbacks$default(Activity activity, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        if ((i & 2) != 0) {
            function02 = null;
        }
        return registerLifecycleCallbacks(activity, function0, function02);
    }

    public static final UnRegisterLifecycleCallback registerLifecycleCallbacks(final Activity activity, final Function0<Unit> function0, final Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: net.touchcapture.qr.flutterqr.QrActivityLifecycleCallbacksKt$registerLifecycleCallbacks$callback$1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity p0, Bundle p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity p0, Bundle p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity p0) {
                Function0<Unit> function03;
                Intrinsics.checkNotNullParameter(p0, "p0");
                if (!Intrinsics.areEqual(p0, activity) || (function03 = function0) == null) {
                    return;
                }
                function03.invoke();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity p0) {
                Function0<Unit> function03;
                Intrinsics.checkNotNullParameter(p0, "p0");
                if (!Intrinsics.areEqual(p0, activity) || (function03 = function02) == null) {
                    return;
                }
                function03.invoke();
            }
        };
        activity.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        Application application = activity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "application");
        return new UnRegisterLifecycleCallback(application, activityLifecycleCallbacks);
    }
}
