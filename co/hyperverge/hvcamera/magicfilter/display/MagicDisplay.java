package co.hyperverge.hvcamera.magicfilter.display;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.opengl.GLES20;
import co.hyperverge.hvcamera.GLTextureView;
import co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter;
import co.hyperverge.hvcamera.magicfilter.utils.SaveTask;
import co.hyperverge.hvcamera.magicfilter.utils.SaveTask2;
import co.hyperverge.hvcamera.magicfilter.utils.TextureRotationUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* loaded from: classes2.dex */
public abstract class MagicDisplay implements GLTextureView.Renderer {
    public static int currentFiltertype = -1;
    IntBuffer ib;
    protected final FloatBuffer mGLCubeBuffer;
    protected final FloatBuffer mGLTextureBuffer;
    protected final GLTextureView mGLTextureView;
    protected int mImageHeight;
    protected int mImageWidth;
    protected SaveTask mSaveTask;
    protected int mSurfaceHeight;
    protected int mSurfaceWidth;
    protected int mTextureId = -1;
    protected GPUImageFilter mFilters = new GPUImageFilter();

    public void clearHandlers() {
    }

    public void getBitmapfromgl(Bitmap bitmap, File file, boolean z, boolean z2, int i) {
    }

    public void onCameraChanged() {
    }

    public void onCameraFlip() {
    }

    public void onResume() {
    }

    public void onTakePicture(File file, SaveTask2.onPictureSaveListener onpicturesavelistener, Camera.ShutterCallback shutterCallback) {
    }

    public void setTouchEvent(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
    }

    public void startRecording() {
    }

    public void stopRecording() {
    }

    public MagicDisplay(GLTextureView gLTextureView) {
        this.mGLTextureView = gLTextureView;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(TextureRotationUtil.CUBE.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(TextureRotationUtil.CUBE).position(0);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(TextureRotationUtil.TEXTURE_NO_ROTATION).position(0);
        gLTextureView.setPreserveEGLContextOnDestroy(false);
        gLTextureView.setEGLContextClientVersion(2);
        gLTextureView.setRenderer(this);
        gLTextureView.setRenderMode(0);
    }

    public void setFilter(final int i) {
        this.mGLTextureView.queueEvent(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicDisplay.1
            @Override // java.lang.Runnable
            public void run() {
                if (MagicDisplay.this.mFilters != null) {
                    MagicDisplay.this.mFilters.destroy();
                }
                MagicDisplay.this.mFilters = null;
                MagicDisplay.this.mFilters = new GPUImageFilter();
                MagicDisplay.currentFiltertype = i;
                if (MagicDisplay.this.mFilters != null) {
                    MagicDisplay.this.mFilters.init();
                }
                MagicDisplay.this.onFilterChanged();
            }
        });
        this.mGLTextureView.requestRender();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onFilterChanged() {
        GPUImageFilter gPUImageFilter = this.mFilters;
        if (gPUImageFilter == null) {
            return;
        }
        gPUImageFilter.onDisplaySizeChanged(this.mSurfaceWidth, this.mSurfaceHeight);
        this.mFilters.onOutputSizeChanged(this.mImageWidth, this.mImageHeight);
    }

    public void onPause() {
        SaveTask saveTask = this.mSaveTask;
        if (saveTask != null) {
            saveTask.cancel(true);
        }
    }

    public void onDestroy() {
        this.mGLTextureView.queueEvent(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicDisplay.2
            @Override // java.lang.Runnable
            public void run() {
                MagicDisplay.this.mFilters.destroy();
            }
        });
    }

    protected void deleteTextures() {
        if (this.mTextureId != -1) {
            this.mGLTextureView.queueEvent(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicDisplay.3
                @Override // java.lang.Runnable
                public void run() {
                    GLES20.glDeleteTextures(1, new int[]{MagicDisplay.this.mTextureId}, 0);
                    MagicDisplay.this.mTextureId = -1;
                }
            });
        }
    }
}
