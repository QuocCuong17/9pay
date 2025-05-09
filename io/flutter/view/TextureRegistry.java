package io.flutter.view;

import android.graphics.SurfaceTexture;
import android.media.Image;
import android.view.Surface;

/* loaded from: classes5.dex */
public interface TextureRegistry {

    /* renamed from: io.flutter.view.TextureRegistry$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static void $default$onTrimMemory(TextureRegistry _this, int i) {
        }
    }

    /* loaded from: classes5.dex */
    public interface GLTextureConsumer {
        SurfaceTexture getSurfaceTexture();
    }

    /* loaded from: classes5.dex */
    public interface ImageConsumer {
        Image acquireLatestImage();
    }

    /* loaded from: classes5.dex */
    public interface ImageTextureEntry extends TextureEntry {
        void pushImage(Image image);
    }

    /* loaded from: classes5.dex */
    public interface OnFrameConsumedListener {
        void onFrameConsumed();
    }

    /* loaded from: classes5.dex */
    public interface OnTrimMemoryListener {
        void onTrimMemory(int i);
    }

    /* loaded from: classes5.dex */
    public interface SurfaceProducer extends TextureEntry {

        /* loaded from: classes5.dex */
        public interface Callback {
            void onSurfaceCreated();

            void onSurfaceDestroyed();
        }

        int getHeight();

        Surface getSurface();

        int getWidth();

        void scheduleFrame();

        void setCallback(Callback callback);

        void setSize(int i, int i2);
    }

    /* loaded from: classes5.dex */
    public interface SurfaceTextureEntry extends TextureEntry {

        /* renamed from: io.flutter.view.TextureRegistry$SurfaceTextureEntry$-CC, reason: invalid class name */
        /* loaded from: classes5.dex */
        public final /* synthetic */ class CC {
            public static void $default$setOnFrameConsumedListener(SurfaceTextureEntry _this, OnFrameConsumedListener onFrameConsumedListener) {
            }

            public static void $default$setOnTrimMemoryListener(SurfaceTextureEntry _this, OnTrimMemoryListener onTrimMemoryListener) {
            }
        }

        void setOnFrameConsumedListener(OnFrameConsumedListener onFrameConsumedListener);

        void setOnTrimMemoryListener(OnTrimMemoryListener onTrimMemoryListener);

        SurfaceTexture surfaceTexture();
    }

    /* loaded from: classes5.dex */
    public interface TextureEntry {
        long id();

        void release();
    }

    ImageTextureEntry createImageTexture();

    SurfaceProducer createSurfaceProducer();

    SurfaceTextureEntry createSurfaceTexture();

    void onTrimMemory(int i);

    SurfaceTextureEntry registerSurfaceTexture(SurfaceTexture surfaceTexture);
}
