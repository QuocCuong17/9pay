package org.tensorflow.lite.support.common.ops;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class CastOp implements TensorOperator {
    private final DataType destinationType;

    public CastOp(DataType destinationType) {
        SupportPreconditions.checkArgument(destinationType == DataType.UINT8 || destinationType == DataType.FLOAT32, "Destination type " + destinationType + " is not supported.");
        this.destinationType = destinationType;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorBuffer apply(TensorBuffer input) {
        DataType dataType = input.getDataType();
        DataType dataType2 = this.destinationType;
        return dataType == dataType2 ? input : TensorBuffer.createFrom(input, dataType2);
    }
}
