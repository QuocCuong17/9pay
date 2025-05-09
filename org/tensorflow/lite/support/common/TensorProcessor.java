package org.tensorflow.lite.support.common;

import org.tensorflow.lite.support.common.SequentialProcessor;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class TensorProcessor extends SequentialProcessor<TensorBuffer> {
    private TensorProcessor(Builder builder) {
        super(builder);
    }

    /* loaded from: classes6.dex */
    public static class Builder extends SequentialProcessor.Builder<TensorBuffer> {
        @Override // org.tensorflow.lite.support.common.SequentialProcessor.Builder
        public /* bridge */ /* synthetic */ SequentialProcessor.Builder<TensorBuffer> add(Operator<TensorBuffer> op) {
            return super.add(op);
        }

        public Builder add(TensorOperator op) {
            super.add((Operator) op);
            return this;
        }

        @Override // org.tensorflow.lite.support.common.SequentialProcessor.Builder
        public SequentialProcessor<TensorBuffer> build() {
            return new TensorProcessor(this);
        }
    }
}
