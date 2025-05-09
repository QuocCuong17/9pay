package androidx.camera.lifecycle;

import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.lifecycle.LifecycleCameraRepository;
import androidx.lifecycle.LifecycleOwner;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_LifecycleCameraRepository_Key extends LifecycleCameraRepository.Key {
    private final CameraUseCaseAdapter.CameraId cameraId;
    private final LifecycleOwner lifecycleOwner;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_LifecycleCameraRepository_Key(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter.CameraId cameraId) {
        Objects.requireNonNull(lifecycleOwner, "Null lifecycleOwner");
        this.lifecycleOwner = lifecycleOwner;
        Objects.requireNonNull(cameraId, "Null cameraId");
        this.cameraId = cameraId;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraRepository.Key
    public LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraRepository.Key
    public CameraUseCaseAdapter.CameraId getCameraId() {
        return this.cameraId;
    }

    public String toString() {
        return "Key{lifecycleOwner=" + this.lifecycleOwner + ", cameraId=" + this.cameraId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LifecycleCameraRepository.Key)) {
            return false;
        }
        LifecycleCameraRepository.Key key = (LifecycleCameraRepository.Key) obj;
        return this.lifecycleOwner.equals(key.getLifecycleOwner()) && this.cameraId.equals(key.getCameraId());
    }

    public int hashCode() {
        return ((this.lifecycleOwner.hashCode() ^ 1000003) * 1000003) ^ this.cameraId.hashCode();
    }
}
