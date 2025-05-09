package net.touchcapture.qr.flutterqr;

import android.app.Activity;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import kotlin.Metadata;

/* compiled from: QrShared.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lnet/touchcapture/qr/flutterqr/QrShared;", "", "()V", "CAMERA_REQUEST_ID", "", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "binding", "Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;", "getBinding", "()Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;", "setBinding", "(Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;)V", "qr_code_scanner_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QrShared {
    public static final int CAMERA_REQUEST_ID = 513469796;
    public static final QrShared INSTANCE = new QrShared();
    private static Activity activity;
    private static ActivityPluginBinding binding;

    private QrShared() {
    }

    public final Activity getActivity() {
        return activity;
    }

    public final void setActivity(Activity activity2) {
        activity = activity2;
    }

    public final ActivityPluginBinding getBinding() {
        return binding;
    }

    public final void setBinding(ActivityPluginBinding activityPluginBinding) {
        binding = activityPluginBinding;
    }
}
