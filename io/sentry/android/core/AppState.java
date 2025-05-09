package io.sentry.android.core;

/* loaded from: classes5.dex */
public final class AppState {
    private static AppState instance = new AppState();
    private Boolean inBackground = null;

    private AppState() {
    }

    public static AppState getInstance() {
        return instance;
    }

    void resetInstance() {
        instance = new AppState();
    }

    public Boolean isInBackground() {
        return this.inBackground;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setInBackground(boolean z) {
        this.inBackground = Boolean.valueOf(z);
    }
}
