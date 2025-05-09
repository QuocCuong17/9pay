package co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.magicfilter.utils.OpenGLUtils;
import co.hyperverge.hvcamera.magicfilter.utils.Rotation;
import co.hyperverge.hvcamera.magicfilter.utils.TextureRotationUtil;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* loaded from: classes2.dex */
public class GPUImageFilter {
    public static final String NO_FILTER_FRAGMENT_SHADER = "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}";
    public static final String NO_FILTER_VERTEX_SHADER = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}";
    private static final String TAG = "GPUImageFilter";
    private String mFragmentShader;
    protected int mGLAttribPosition;
    protected int mGLAttribTextureCoordinate;
    protected FloatBuffer mGLCubeBuffer;
    protected int mGLProgId;
    protected int mGLStrengthLocation;
    protected FloatBuffer mGLTextureBuffer;
    protected int mGLUniformTexture;
    protected boolean mIsInitialized;
    protected int mOutputHeight;
    protected int mOutputWidth;
    private LinkedList<Runnable> mRunOnDraw;
    protected int mSurfaceHeight;
    protected int mSurfaceWidth;
    private String mVertexShader;

    protected void onDrawArraysAfter() {
    }

    protected void onDrawArraysPre() {
    }

    public GPUImageFilter() {
        this(NO_FILTER_VERTEX_SHADER, NO_FILTER_FRAGMENT_SHADER);
    }

    public GPUImageFilter(String str, String str2) {
        this.mRunOnDraw = new LinkedList<>();
        this.mVertexShader = "";
        this.mFragmentShader = "";
        try {
            checkGlError("1");
            this.mRunOnDraw = new LinkedList<>();
            this.mVertexShader = str;
            this.mFragmentShader = str2;
            checkGlError("2");
            this.mGLCubeBuffer = ByteBuffer.allocateDirect(TextureRotationUtil.CUBE.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            checkGlError("3");
            this.mGLCubeBuffer.put(TextureRotationUtil.CUBE).position(0);
            checkGlError("4");
            this.mGLTextureBuffer = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            checkGlError("5");
            this.mGLTextureBuffer.put(TextureRotationUtil.getRotation(Rotation.NORMAL, false, true)).position(0);
            checkGlError("6");
        } catch (Exception e) {
            HVLog.d(TAG, e.getMessage());
        }
    }

    public void init() {
        HVLog.d(TAG, "init() called");
        onInit();
        onInitialized();
        this.mIsInitialized = true;
    }

    public final void destroy() {
        HVLog.d(TAG, "destroy() called");
        this.mIsInitialized = false;
        GLES20.glDeleteProgram(this.mGLProgId);
        onDestroy();
    }

    public void onOutputSizeChanged(int i, int i2) {
        HVLog.d(TAG, "onOutputSizeChanged() called with: width = [" + i + "], height = [" + i2 + "]");
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public int onDrawFrame(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.mGLProgId);
        runPendingOnDrawTasks();
        if (!this.mIsInitialized) {
            return -1;
        }
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
        if (i != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glUniform1i(this.mGLUniformTexture, 0);
        }
        onDrawArraysPre();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
        GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
        onDrawArraysAfter();
        GLES20.glBindTexture(3553, 0);
        return 1;
    }

    public int onDrawFrame(int i, boolean z, boolean z2) {
        GLES20.glUseProgram(this.mGLProgId);
        runPendingOnDrawTasks();
        if (!this.mIsInitialized) {
            return -1;
        }
        this.mGLCubeBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, 5126, false, 0, (Buffer) this.mGLCubeBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
        if (z || z2) {
            float[] fArr = new float[8];
            if (z) {
                fArr = TextureRotationUtil.getRotation(Rotation.ROTATION_180, false, false);
            } else if (z2) {
                fArr = TextureRotationUtil.getRotation(Rotation.NORMAL, false, false);
            }
            this.mGLTextureBuffer.clear();
            this.mGLTextureBuffer.put(fArr).position(0);
        }
        this.mGLTextureBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, 5126, false, 0, (Buffer) this.mGLTextureBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
        if (i != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
            GLES20.glUniform1i(this.mGLUniformTexture, 0);
        }
        onDrawArraysPre();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
        GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
        onDrawArraysAfter();
        GLES20.glBindTexture(3553, 0);
        return 1;
    }

    public void clearPendingOnDrawTasks() {
        this.mRunOnDraw.clear();
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public int getOutputWidth() {
        HVLog.d(TAG, "getOutputWidth() called");
        return this.mOutputWidth;
    }

    public int getOutputHeight() {
        HVLog.d(TAG, "getOutputHeight() called");
        return this.mOutputHeight;
    }

    public int getProgram() {
        HVLog.d(TAG, "getProgram() called");
        return this.mGLProgId;
    }

    public int getAttribPosition() {
        HVLog.d(TAG, "getAttribPosition() called");
        return this.mGLAttribPosition;
    }

    public int getAttribTextureCoordinate() {
        HVLog.d(TAG, "getAttribTextureCoordinate() called");
        return this.mGLAttribTextureCoordinate;
    }

    public int getUniformTexture() {
        HVLog.d(TAG, "getUniformTexture() called");
        return this.mGLUniformTexture;
    }

    public void onDisplaySizeChanged(int i, int i2) {
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
    }

    private void checkGlError(String str) {
        HVLog.d(TAG, "checkGlError() called with: op = [" + str + "]");
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                return;
            }
            HVLog.e(TAG, str + ": glError " + glGetError);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onInit() {
        HVLog.d(TAG, "onInit() called");
        checkGlError("7");
        try {
            int loadProgram = OpenGLUtils.loadProgram(this.mVertexShader, this.mFragmentShader);
            this.mGLProgId = loadProgram;
            this.mGLAttribPosition = GLES20.glGetAttribLocation(loadProgram, "position");
            this.mGLUniformTexture = GLES20.glGetUniformLocation(this.mGLProgId, "inputImageTexture");
            this.mGLAttribTextureCoordinate = GLES20.glGetAttribLocation(this.mGLProgId, "inputTextureCoordinate");
            this.mIsInitialized = true;
        } catch (Exception e) {
            HVLog.e(TAG, "onInit: " + e.getMessage());
        }
    }

    protected void onInitialized() {
        HVLog.d(TAG, "onInitialized() called");
    }

    protected void onDestroy() {
        HVLog.d(TAG, "onDestroy() called");
    }

    protected void runPendingOnDrawTasks() {
        while (!this.mRunOnDraw.isEmpty()) {
            this.mRunOnDraw.removeFirst().run();
        }
    }

    protected void setInteger(final int i, final int i2) {
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.1
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1i(i, i2);
            }
        });
    }

    protected void setFloat(final int i, final float f) {
        HVLog.d(TAG, "setFloat() called with: location = [" + i + "], floatValue = [" + f + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.2
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(i, f);
            }
        });
    }

    protected void setFloatVec2(final int i, final float[] fArr) {
        HVLog.d(TAG, "setFloatVec2() called with: location = [" + i + "], arrayValue = [" + fArr + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.3
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform2fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void setFloatVec3(final int i, final float[] fArr) {
        HVLog.d(TAG, "setFloatVec3() called with: location = [" + i + "], arrayValue = [" + fArr + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.4
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform3fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void setFloatVec4(final int i, final float[] fArr) {
        HVLog.d(TAG, "setFloatVec4() called with: location = [" + i + "], arrayValue = [" + fArr + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.5
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform4fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void setFloatArray(final int i, final float[] fArr) {
        HVLog.d(TAG, "setFloatArray() called with: location = [" + i + "], arrayValue = [" + fArr + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.6
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                float[] fArr2 = fArr;
                GLES20.glUniform1fv(i2, fArr2.length, FloatBuffer.wrap(fArr2));
            }
        });
    }

    protected void setPoint(final int i, final PointF pointF) {
        HVLog.d(TAG, "setPoint() called with: location = [" + i + "], point = [" + pointF + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.7
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform2fv(i, 1, new float[]{pointF.x, pointF.y}, 0);
            }
        });
    }

    protected void setUniformMatrix3f(final int i, final float[] fArr) {
        HVLog.d(TAG, "setUniformMatrix3f() called with: location = [" + i + "], matrix = [" + fArr + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.8
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniformMatrix3fv(i, 1, false, fArr, 0);
            }
        });
    }

    protected void setUniformMatrix4f(final int i, final float[] fArr) {
        HVLog.d(TAG, "setUniformMatrix4f() called with: location = [" + i + "], matrix = [" + fArr + "]");
        runOnDraw(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter.9
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniformMatrix4fv(i, 1, false, fArr, 0);
            }
        });
    }

    protected void runOnDraw(Runnable runnable) {
        synchronized (this.mRunOnDraw) {
            this.mRunOnDraw.addLast(runnable);
        }
    }
}
