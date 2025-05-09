package org.tensorflow.lite.support.common.ops;

import org.tensorflow.lite.support.common.TensorOperator;

/* loaded from: classes6.dex */
public class QuantizeOp extends NormalizeOp implements TensorOperator {
    public QuantizeOp(float zeroPoint, float scale) {
        super((-zeroPoint) * scale, scale);
    }
}
