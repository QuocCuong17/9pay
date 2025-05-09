package org.tensorflow.lite.support.common.ops;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.support.tensorbuffer.TensorBufferFloat;

/* loaded from: classes6.dex */
public class NormalizeOp implements TensorOperator {
    private final boolean isIdentityOp;
    private final float[] mean;
    private final int numChannels;
    private final float[] stddev;

    public NormalizeOp(float mean, float stddev) {
        if (mean == 0.0f && (stddev == 0.0f || Float.isInfinite(stddev))) {
            stddev = 1.0f;
        }
        SupportPreconditions.checkArgument(stddev != 0.0f, "Stddev cannot be zero.");
        this.isIdentityOp = mean == 0.0f && stddev == 1.0f;
        this.mean = new float[]{mean};
        this.stddev = new float[]{stddev};
        this.numChannels = 1;
    }

    public NormalizeOp(float[] mean, float[] stddev) {
        SupportPreconditions.checkNotNull(mean, "Mean cannot be null");
        SupportPreconditions.checkNotNull(stddev, "Stddev cannot be null");
        SupportPreconditions.checkArgument(mean.length == stddev.length, "Per channel normalization requires same number of means and stddevs");
        SupportPreconditions.checkArgument(mean.length > 0, "Means and stddevs are empty.");
        this.mean = (float[]) mean.clone();
        this.stddev = (float[]) stddev.clone();
        this.numChannels = mean.length;
        boolean z = true;
        for (int i = 0; i < this.numChannels; i++) {
            SupportPreconditions.checkArgument(this.stddev[i] != 0.0f, "Stddev cannot be zero.");
            if (this.stddev[i] != 1.0f || this.mean[i] != 0.0f) {
                z = false;
            }
        }
        this.isIdentityOp = z;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorBuffer apply(TensorBuffer input) {
        TensorBuffer createFixedSize;
        if (this.isIdentityOp) {
            return input;
        }
        int[] shape = input.getShape();
        int i = this.numChannels;
        SupportPreconditions.checkArgument(i == 1 || (shape.length != 0 && shape[shape.length - 1] == i), "Number of means (stddevs) is not same with number of channels (size of last axis).");
        float[] floatArray = input.getFloatArray();
        int i2 = 0;
        for (int i3 = 0; i3 < floatArray.length; i3++) {
            floatArray[i3] = (floatArray[i3] - this.mean[i2]) / this.stddev[i2];
            i2 = (i2 + 1) % this.numChannels;
        }
        if (input.isDynamic()) {
            createFixedSize = TensorBufferFloat.createDynamic(DataType.FLOAT32);
        } else {
            createFixedSize = TensorBufferFloat.createFixedSize(shape, DataType.FLOAT32);
        }
        createFixedSize.loadArray(floatArray, shape);
        return createFixedSize;
    }
}
