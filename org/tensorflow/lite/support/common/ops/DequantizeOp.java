package org.tensorflow.lite.support.common.ops;

import org.tensorflow.lite.support.common.TensorOperator;

/* loaded from: classes6.dex */
public class DequantizeOp extends NormalizeOp implements TensorOperator {
    public DequantizeOp(float zeroPoint, float scale) {
        super(zeroPoint, 1.0f / scale);
    }
}
