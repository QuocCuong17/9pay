package org.tensorflow.lite.support.tensorbuffer;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes6.dex */
public final class TensorBufferFloat extends TensorBuffer {
    private static final DataType DATA_TYPE = DataType.FLOAT32;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TensorBufferFloat(int[] shape) {
        super(shape);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TensorBufferFloat() {
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public DataType getDataType() {
        return DATA_TYPE;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public float[] getFloatArray() {
        this.buffer.rewind();
        float[] fArr = new float[this.flatSize];
        this.buffer.asFloatBuffer().get(fArr);
        return fArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public float getFloatValue(int absIndex) {
        return this.buffer.getFloat(absIndex << 2);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int[] getIntArray() {
        this.buffer.rewind();
        float[] fArr = new float[this.flatSize];
        this.buffer.asFloatBuffer().get(fArr);
        int[] iArr = new int[this.flatSize];
        for (int i = 0; i < this.flatSize; i++) {
            iArr[i] = (int) fArr[i];
        }
        return iArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getIntValue(int absIndex) {
        return (int) this.buffer.getFloat(absIndex << 2);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getTypeSize() {
        return DATA_TYPE.byteSize();
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(float[] src, int[] shape) {
        SupportPreconditions.checkNotNull(src, "The array to be loaded cannot be null.");
        SupportPreconditions.checkArgument(src.length == computeFlatSize(shape), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(shape);
        this.buffer.rewind();
        this.buffer.asFloatBuffer().put(src);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(int[] src, int[] shape) {
        SupportPreconditions.checkNotNull(src, "The array to be loaded cannot be null.");
        int i = 0;
        SupportPreconditions.checkArgument(src.length == computeFlatSize(shape), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(shape);
        this.buffer.rewind();
        float[] fArr = new float[src.length];
        int length = src.length;
        int i2 = 0;
        while (i < length) {
            fArr[i2] = src[i];
            i++;
            i2++;
        }
        this.buffer.asFloatBuffer().put(fArr);
    }
}
