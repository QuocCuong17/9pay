package androidx.webkit;

import androidx.webkit.internal.ServiceWorkerControllerImpl;

/* loaded from: classes2.dex */
public abstract class ServiceWorkerControllerCompat {
    public abstract ServiceWorkerWebSettingsCompat getServiceWorkerWebSettings();

    public abstract void setServiceWorkerClient(ServiceWorkerClientCompat serviceWorkerClientCompat);

    public static ServiceWorkerControllerCompat getInstance() {
        return LAZY_HOLDER.INSTANCE;
    }

    /* loaded from: classes2.dex */
    private static class LAZY_HOLDER {
        static final ServiceWorkerControllerCompat INSTANCE = new ServiceWorkerControllerImpl();

        private LAZY_HOLDER() {
        }
    }
}
