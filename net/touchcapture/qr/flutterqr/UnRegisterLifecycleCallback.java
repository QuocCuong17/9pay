package net.touchcapture.qr.flutterqr;

import android.app.Application;
import androidx.media3.common.MimeTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QrActivityLifecycleCallbacks.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lnet/touchcapture/qr/flutterqr/UnRegisterLifecycleCallback;", "", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "callback", "Landroid/app/Application$ActivityLifecycleCallbacks;", "(Landroid/app/Application;Landroid/app/Application$ActivityLifecycleCallbacks;)V", "invoke", "", "qr_code_scanner_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UnRegisterLifecycleCallback {
    private final Application application;
    private final Application.ActivityLifecycleCallbacks callback;

    public UnRegisterLifecycleCallback(Application application, Application.ActivityLifecycleCallbacks callback) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.application = application;
        this.callback = callback;
    }

    public final void invoke() {
        this.application.unregisterActivityLifecycleCallbacks(this.callback);
    }
}
