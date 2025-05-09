package org.tensorflow.lite.support.image;

import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.tensorflow.lite.support.common.Operator;
import org.tensorflow.lite.support.common.SequentialProcessor;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ops.Rot90Op;
import org.tensorflow.lite.support.image.ops.TensorOperatorWrapper;

/* loaded from: classes6.dex */
public class ImageProcessor extends SequentialProcessor<TensorImage> {
    private ImageProcessor(Builder builder) {
        super(builder);
    }

    public PointF inverseTransform(PointF point, int inputImageHeight, int inputImageWidth) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.operatorList.iterator();
        while (it.hasNext()) {
            Operator operator = (Operator) it.next();
            arrayList.add(Integer.valueOf(inputImageWidth));
            arrayList2.add(Integer.valueOf(inputImageHeight));
            ImageOperator imageOperator = (ImageOperator) operator;
            int outputImageHeight = imageOperator.getOutputImageHeight(inputImageHeight, inputImageWidth);
            inputImageWidth = imageOperator.getOutputImageWidth(inputImageHeight, inputImageWidth);
            inputImageHeight = outputImageHeight;
        }
        ListIterator listIterator = this.operatorList.listIterator(this.operatorList.size());
        ListIterator listIterator2 = arrayList.listIterator(arrayList.size());
        ListIterator listIterator3 = arrayList2.listIterator(arrayList2.size());
        while (listIterator.hasPrevious()) {
            point = ((ImageOperator) listIterator.previous()).inverseTransform(point, ((Integer) listIterator3.previous()).intValue(), ((Integer) listIterator2.previous()).intValue());
        }
        return point;
    }

    public RectF inverseTransform(RectF rect, int inputImageHeight, int inputImageWidth) {
        PointF inverseTransform = inverseTransform(new PointF(rect.left, rect.top), inputImageHeight, inputImageWidth);
        PointF inverseTransform2 = inverseTransform(new PointF(rect.right, rect.bottom), inputImageHeight, inputImageWidth);
        return new RectF(Math.min(inverseTransform.x, inverseTransform2.x), Math.min(inverseTransform.y, inverseTransform2.y), Math.max(inverseTransform.x, inverseTransform2.x), Math.max(inverseTransform.y, inverseTransform2.y));
    }

    @Override // org.tensorflow.lite.support.common.SequentialProcessor, org.tensorflow.lite.support.common.Processor
    public TensorImage process(TensorImage image) {
        return (TensorImage) super.process((ImageProcessor) image);
    }

    /* loaded from: classes6.dex */
    public static class Builder extends SequentialProcessor.Builder<TensorImage> {
        @Override // org.tensorflow.lite.support.common.SequentialProcessor.Builder
        public /* bridge */ /* synthetic */ SequentialProcessor.Builder<TensorImage> add(Operator<TensorImage> op) {
            return super.add(op);
        }

        public Builder add(ImageOperator op) {
            super.add((Operator) op);
            return this;
        }

        public Builder add(TensorOperator op) {
            return add((ImageOperator) new TensorOperatorWrapper(op));
        }

        @Override // org.tensorflow.lite.support.common.SequentialProcessor.Builder
        public SequentialProcessor<TensorImage> build() {
            return new ImageProcessor(this);
        }
    }

    public void updateNumberOfRotations(int k) {
        updateNumberOfRotations(k, 0);
    }

    public synchronized void updateNumberOfRotations(int k, int occurrence) {
        SupportPreconditions.checkState(this.operatorIndex.containsKey(Rot90Op.class.getName()), "The Rot90Op has not been added to the ImageProcessor.");
        List<Integer> list = this.operatorIndex.get(Rot90Op.class.getName());
        SupportPreconditions.checkElementIndex(occurrence, list.size(), "occurrence");
        this.operatorList.set(list.get(occurrence).intValue(), new Rot90Op(k));
    }
}
