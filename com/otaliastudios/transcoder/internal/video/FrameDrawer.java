package com.otaliastudios.transcoder.internal.video;

import android.graphics.SurfaceTexture;
import android.opengl.Matrix;
import android.view.Surface;
import com.otaliastudios.opengl.draw.GlRect;
import com.otaliastudios.opengl.program.GlTextureProgram;
import com.otaliastudios.opengl.texture.GlTexture;
import com.otaliastudios.transcoder.internal.utils.Logger;

/* loaded from: classes4.dex */
class FrameDrawer {
    private static final Logger LOG = new Logger("FrameDrawer");
    private static final long NEW_IMAGE_TIMEOUT_MILLIS = 10000;
    private GlRect mDrawable;
    private boolean mFrameAvailable;
    private GlTextureProgram mProgram;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private int mRotation = 0;
    private boolean mFlipY = false;
    private final Object mFrameAvailableLock = new Object();

    public FrameDrawer() {
        GlTexture glTexture = new GlTexture();
        GlTextureProgram glTextureProgram = new GlTextureProgram();
        this.mProgram = glTextureProgram;
        glTextureProgram.setTexture(glTexture);
        this.mDrawable = new GlRect();
        SurfaceTexture surfaceTexture = new SurfaceTexture(glTexture.getId());
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: com.otaliastudios.transcoder.internal.video.FrameDrawer.1
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                FrameDrawer.LOG.v("New frame available");
                synchronized (FrameDrawer.this.mFrameAvailableLock) {
                    if (!FrameDrawer.this.mFrameAvailable) {
                        FrameDrawer.this.mFrameAvailable = true;
                        FrameDrawer.this.mFrameAvailableLock.notifyAll();
                    } else {
                        throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
                    }
                }
            }
        });
        this.mSurface = new Surface(this.mSurfaceTexture);
    }

    public void setScale(float f, float f2) {
        this.mScaleX = f;
        this.mScaleY = f2;
    }

    public void setRotation(int i) {
        this.mRotation = i;
    }

    public void setFlipY(boolean z) {
        this.mFlipY = z;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void release() {
        this.mProgram.release();
        this.mSurface.release();
        this.mSurface = null;
        this.mSurfaceTexture = null;
        this.mDrawable = null;
        this.mProgram = null;
    }

    public void drawFrame() {
        awaitNewFrame();
        drawNewFrame();
    }

    private void awaitNewFrame() {
        synchronized (this.mFrameAvailableLock) {
            do {
                if (!this.mFrameAvailable) {
                    try {
                        this.mFrameAvailableLock.wait(10000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    this.mFrameAvailable = false;
                }
            } while (this.mFrameAvailable);
            throw new RuntimeException("Surface frame wait timed out");
        }
        this.mSurfaceTexture.updateTexImage();
    }

    private void drawNewFrame() {
        this.mSurfaceTexture.getTransformMatrix(this.mProgram.getTextureTransform());
        float f = 1.0f / this.mScaleX;
        float f2 = 1.0f / this.mScaleY;
        Matrix.translateM(this.mProgram.getTextureTransform(), 0, (1.0f - f) / 2.0f, (1.0f - f2) / 2.0f, 0.0f);
        Matrix.scaleM(this.mProgram.getTextureTransform(), 0, f, f2, 1.0f);
        Matrix.translateM(this.mProgram.getTextureTransform(), 0, 0.5f, 0.5f, 0.0f);
        Matrix.rotateM(this.mProgram.getTextureTransform(), 0, this.mRotation, 0.0f, 0.0f, 1.0f);
        if (this.mFlipY) {
            Matrix.scaleM(this.mProgram.getTextureTransform(), 0, 1.0f, -1.0f, 1.0f);
        }
        Matrix.translateM(this.mProgram.getTextureTransform(), 0, -0.5f, -0.5f, 0.0f);
        this.mProgram.draw(this.mDrawable);
    }
}
