package co.hyperverge.hvcamera.magicfilter.filter.base;

import android.opengl.GLES20;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* loaded from: classes2.dex */
public class MagicCameraInputFilter extends GPUImageFilter {
    private static final String CAMERA_INPUT_FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nvarying highp vec2 textureCoordinate;\n\nuniform samplerExternalOES inputImageTexture;\n\nvoid main()\n{\n\tgl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}";
    private static final String CAMERA_INPUT_VERTEX_SHADER = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform mat4 textureTransform;\nvarying vec2 textureCoordinate;\n\nvoid main()\n{\n\ttextureCoordinate = (textureTransform * inputTextureCoordinate).xy;\n\tgl_Position = position;\n}";
    private static final String TAG = "MagicCameraInputFilter";
    protected int[] mFrameBufferTextures;
    protected int[] mFrameBuffers;
    private int mFrameHeight;
    private int mFrameWidth;
    private float[] mTextureTransformMatrix;
    private int mTextureTransformMatrixLocation;

    public MagicCameraInputFilter() {
        super(CAMERA_INPUT_VERTEX_SHADER, CAMERA_INPUT_FRAGMENT_SHADER);
        this.mFrameBuffers = null;
        this.mFrameBufferTextures = null;
        this.mFrameWidth = -1;
        this.mFrameHeight = -1;
    }

    public void setTextureTransformMatrix(float[] fArr) {
        HVLog.d(TAG, "setTextureTransformMatrix() called with: mtx = [" + fArr + "]");
        this.mTextureTransformMatrix = fArr;
    }

    @Override // co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter
    public int onDrawFrame(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        HVLog.d(TAG, "onDrawFrame() called with: textureId = [" + i + "], vertexBuffer = [" + floatBuffer + "], textureBuffer = [" + floatBuffer2 + "]");
        GLES20.glUseProgram(this.mGLProgId);
        if (!isInitialized() || floatBuffer == null || floatBuffer2 == null || this.mTextureTransformMatrix == null) {
            return -1;
        }
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glUniformMatrix4fv(this.mTextureTransformMatrixLocation, 1, false, this.mTextureTransformMatrix, 0);
        if (i != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i);
            GLES20.glUniform1i(this.mGLUniformTexture, 0);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
        GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glBindTexture(36197, 0);
        return 1;
    }

    @Override // co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter
    public int onDrawFrame(int i, boolean z, boolean z2) {
        HVLog.d(TAG, "onDrawFrame() called with: textureId = [" + i + "], xflip = [" + z + "], yflip = [" + z2 + "]");
        GLES20.glUseProgram(this.mGLProgId);
        if (!isInitialized()) {
            return -1;
        }
        this.mGLCubeBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, 5126, false, 0, (Buffer) this.mGLCubeBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
        this.mGLTextureBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, 5126, false, 0, (Buffer) this.mGLTextureBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glUniformMatrix4fv(this.mTextureTransformMatrixLocation, 1, false, this.mTextureTransformMatrix, 0);
        if (i != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i);
            GLES20.glUniform1i(this.mGLUniformTexture, 0);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
        GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glBindTexture(36197, 0);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hvcamera.magicfilter.filter.base.gpuimage.GPUImageFilter
    public void onInit() {
        super.onInit();
        HVLog.d(TAG, "onInit() called");
        this.mTextureTransformMatrixLocation = GLES20.glGetUniformLocation(this.mGLProgId, "textureTransform");
    }

    public int onDrawToTexture(int i) {
        HVLog.d(TAG, "onDrawToTexture() called with: textureId = [" + i + "]");
        if (this.mFrameBuffers == null || this.mFrameBufferTextures == null) {
            initCameraFrameBuffer(this.mFrameWidth, this.mFrameHeight);
            return -1;
        }
        GLES20.glViewport(0, 0, this.mOutputWidth, this.mOutputHeight);
        GLES20.glBindFramebuffer(36160, this.mFrameBuffers[0]);
        GLES20.glUseProgram(this.mGLProgId);
        if (!isInitialized() || this.mTextureTransformMatrix == null) {
            return -1;
        }
        this.mGLCubeBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, 5126, false, 0, (Buffer) this.mGLCubeBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
        this.mGLTextureBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, 5126, false, 0, (Buffer) this.mGLTextureBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glUniformMatrix4fv(this.mTextureTransformMatrixLocation, 1, false, this.mTextureTransformMatrix, 0);
        if (i != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i);
            GLES20.glUniform1i(this.mGLUniformTexture, 0);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
        GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glBindTexture(36197, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(0, 0, this.mSurfaceWidth, this.mSurfaceHeight);
        return this.mFrameBufferTextures[0];
    }

    public void initCameraFrameBuffer(int i, int i2) {
        HVLog.d(TAG, "initCameraFrameBuffer() called with: width = [" + i + "], height = [" + i2 + "]");
        if (this.mFrameBuffers != null && (this.mFrameWidth != i || this.mFrameHeight != i2)) {
            destroyFramebuffers();
        }
        if (this.mFrameBuffers == null) {
            this.mFrameWidth = i;
            this.mFrameHeight = i2;
            int[] iArr = new int[1];
            this.mFrameBuffers = iArr;
            this.mFrameBufferTextures = new int[1];
            GLES20.glGenFramebuffers(1, iArr, 0);
            GLES20.glGenTextures(1, this.mFrameBufferTextures, 0);
            GLES20.glBindTexture(3553, this.mFrameBufferTextures[0]);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.mFrameBuffers[0]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.mFrameBufferTextures[0], 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
        }
    }

    public void destroyFramebuffers() {
        HVLog.d(TAG, "destroyFramebuffers() called");
        int[] iArr = this.mFrameBufferTextures;
        if (iArr != null) {
            GLES20.glDeleteTextures(1, iArr, 0);
            this.mFrameBufferTextures = null;
        }
        int[] iArr2 = this.mFrameBuffers;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(1, iArr2, 0);
            this.mFrameBuffers = null;
        }
        this.mFrameWidth = -1;
        this.mFrameHeight = -1;
    }
}
