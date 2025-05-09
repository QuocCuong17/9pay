package androidx.camera.camera2.impl;

import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.MultiValueSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CameraEventCallbacks extends MultiValueSet<CameraEventCallback> {
    public CameraEventCallbacks(CameraEventCallback... cameraEventCallbackArr) {
        addAll(Arrays.asList(cameraEventCallbackArr));
    }

    public ComboCameraEventCallback createComboCallback() {
        return new ComboCameraEventCallback(getAllItems());
    }

    public static CameraEventCallbacks createEmptyCallback() {
        return new CameraEventCallbacks(new CameraEventCallback[0]);
    }

    @Override // androidx.camera.core.impl.MultiValueSet
    /* renamed from: clone */
    public MultiValueSet<CameraEventCallback> mo12clone() {
        CameraEventCallbacks createEmptyCallback = createEmptyCallback();
        createEmptyCallback.addAll(getAllItems());
        return createEmptyCallback;
    }

    /* loaded from: classes.dex */
    public static final class ComboCameraEventCallback {
        private final List<CameraEventCallback> mCallbacks = new ArrayList();

        ComboCameraEventCallback(List<CameraEventCallback> list) {
            Iterator<CameraEventCallback> it = list.iterator();
            while (it.hasNext()) {
                this.mCallbacks.add(it.next());
            }
        }

        public List<CaptureConfig> onPresetSession() {
            ArrayList arrayList = new ArrayList();
            Iterator<CameraEventCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                CaptureConfig onPresetSession = it.next().onPresetSession();
                if (onPresetSession != null) {
                    arrayList.add(onPresetSession);
                }
            }
            return arrayList;
        }

        public List<CaptureConfig> onEnableSession() {
            ArrayList arrayList = new ArrayList();
            Iterator<CameraEventCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                CaptureConfig onEnableSession = it.next().onEnableSession();
                if (onEnableSession != null) {
                    arrayList.add(onEnableSession);
                }
            }
            return arrayList;
        }

        public List<CaptureConfig> onRepeating() {
            ArrayList arrayList = new ArrayList();
            Iterator<CameraEventCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                CaptureConfig onRepeating = it.next().onRepeating();
                if (onRepeating != null) {
                    arrayList.add(onRepeating);
                }
            }
            return arrayList;
        }

        public List<CaptureConfig> onDisableSession() {
            ArrayList arrayList = new ArrayList();
            Iterator<CameraEventCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                CaptureConfig onDisableSession = it.next().onDisableSession();
                if (onDisableSession != null) {
                    arrayList.add(onDisableSession);
                }
            }
            return arrayList;
        }

        public List<CameraEventCallback> getCallbacks() {
            return this.mCallbacks;
        }
    }
}
