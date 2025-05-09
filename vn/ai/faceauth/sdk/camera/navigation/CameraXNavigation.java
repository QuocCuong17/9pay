package vn.ai.faceauth.sdk.camera.navigation;

import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import vn.ai.faceauth.sdk.camera.CameraX;
import vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.domain.model.CameraSettingsDomain;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lvn/ai/faceauth/sdk/camera/navigation/CameraXNavigation;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "initializeModuleLifecyle", "", "provideCameraXModule", "Lvn/ai/faceauth/sdk/camera/CameraX;", "cameraSettings", "Lvn/ai/faceauth/sdk/domain/model/CameraSettingsDomain;", "cameraXCallback", "Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXNavigation {
    private final LifecycleOwner lifecycleOwner;

    public CameraXNavigation(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        initializeModuleLifecyle(lifecycleOwner);
    }

    private final void initializeModuleLifecyle(LifecycleOwner lifecycleOwner) {
        CameraModule.INSTANCE.setLifecycleOwner$trueface_release(lifecycleOwner);
    }

    public final CameraX provideCameraXModule(CameraSettingsDomain cameraSettings, CameraXCallback cameraXCallback) {
        return CameraModule.INSTANCE.getContainer$trueface_release().provideCameraX(cameraSettings, cameraXCallback, this.lifecycleOwner);
    }
}
