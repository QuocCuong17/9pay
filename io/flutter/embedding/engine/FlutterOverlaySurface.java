package io.flutter.embedding.engine;

import android.view.Surface;

/* loaded from: classes5.dex */
public class FlutterOverlaySurface {

    /* renamed from: id, reason: collision with root package name */
    private final int f115id;
    private final Surface surface;

    public FlutterOverlaySurface(int i, Surface surface) {
        this.f115id = i;
        this.surface = surface;
    }

    public int getId() {
        return this.f115id;
    }

    public Surface getSurface() {
        return this.surface;
    }
}
