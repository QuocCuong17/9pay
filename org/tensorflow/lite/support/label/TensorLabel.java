package org.tensorflow.lite.support.label;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class TensorLabel {
    private final Map<Integer, List<String>> axisLabels;
    private final int[] shape;
    private final TensorBuffer tensorBuffer;

    public TensorLabel(Map<Integer, List<String>> axisLabels, TensorBuffer tensorBuffer) {
        SupportPreconditions.checkNotNull(axisLabels, "Axis labels cannot be null.");
        SupportPreconditions.checkNotNull(tensorBuffer, "Tensor Buffer cannot be null.");
        this.axisLabels = axisLabels;
        this.tensorBuffer = tensorBuffer;
        this.shape = tensorBuffer.getShape();
        for (Map.Entry<Integer, List<String>> entry : axisLabels.entrySet()) {
            int intValue = entry.getKey().intValue();
            boolean z = true;
            SupportPreconditions.checkArgument(intValue >= 0 && intValue < this.shape.length, "Invalid axis id: " + intValue);
            SupportPreconditions.checkNotNull(entry.getValue(), "Label list is null on axis " + intValue);
            if (this.shape[intValue] != entry.getValue().size()) {
                z = false;
            }
            SupportPreconditions.checkArgument(z, "Label number " + entry.getValue().size() + " mismatch the shape on axis " + intValue);
        }
    }

    public TensorLabel(List<String> axisLabels, TensorBuffer tensorBuffer) {
        this(makeMap(getFirstAxisWithSizeGreaterThanOne(tensorBuffer), axisLabels), tensorBuffer);
    }

    public Map<String, TensorBuffer> getMapWithTensorBuffer() {
        int firstAxisWithSizeGreaterThanOne = getFirstAxisWithSizeGreaterThanOne(this.tensorBuffer);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SupportPreconditions.checkArgument(this.axisLabels.containsKey(Integer.valueOf(firstAxisWithSizeGreaterThanOne)), "get a <String, TensorBuffer> map requires the labels are set on the first non-1 axis.");
        List<String> list = this.axisLabels.get(Integer.valueOf(firstAxisWithSizeGreaterThanOne));
        DataType dataType = this.tensorBuffer.getDataType();
        int typeSize = this.tensorBuffer.getTypeSize();
        int flatSize = this.tensorBuffer.getFlatSize();
        ByteBuffer buffer = this.tensorBuffer.getBuffer();
        buffer.rewind();
        int i = (flatSize / this.shape[firstAxisWithSizeGreaterThanOne]) * typeSize;
        SupportPreconditions.checkNotNull(list, "Label list should never be null");
        int i2 = 0;
        for (String str : list) {
            buffer.position(i2 * i);
            ByteBuffer slice = buffer.slice();
            slice.order(buffer.order()).limit(i);
            TensorBuffer createDynamic = TensorBuffer.createDynamic(dataType);
            int[] iArr = this.shape;
            createDynamic.loadBuffer(slice, Arrays.copyOfRange(iArr, firstAxisWithSizeGreaterThanOne + 1, iArr.length));
            linkedHashMap.put(str, createDynamic);
            i2++;
        }
        return linkedHashMap;
    }

    public Map<String, Float> getMapWithFloatValue() {
        int firstAxisWithSizeGreaterThanOne = getFirstAxisWithSizeGreaterThanOne(this.tensorBuffer);
        int i = 0;
        SupportPreconditions.checkState(firstAxisWithSizeGreaterThanOne == this.shape.length - 1, "get a <String, Scalar> map is only valid when the only labeled axis is the last one.");
        List<String> list = this.axisLabels.get(Integer.valueOf(firstAxisWithSizeGreaterThanOne));
        float[] floatArray = this.tensorBuffer.getFloatArray();
        SupportPreconditions.checkState(list.size() == floatArray.length);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            linkedHashMap.put(it.next(), Float.valueOf(floatArray[i]));
            i++;
        }
        return linkedHashMap;
    }

    public List<Category> getCategoryList() {
        int firstAxisWithSizeGreaterThanOne = getFirstAxisWithSizeGreaterThanOne(this.tensorBuffer);
        int i = 0;
        SupportPreconditions.checkState(firstAxisWithSizeGreaterThanOne == this.shape.length - 1, "get a Category list is only valid when the only labeled axis is the last one.");
        List<String> list = this.axisLabels.get(Integer.valueOf(firstAxisWithSizeGreaterThanOne));
        float[] floatArray = this.tensorBuffer.getFloatArray();
        SupportPreconditions.checkState(list.size() == floatArray.length);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new Category(it.next(), floatArray[i]));
            i++;
        }
        return arrayList;
    }

    private static int getFirstAxisWithSizeGreaterThanOne(TensorBuffer tensorBuffer) {
        int[] shape = tensorBuffer.getShape();
        for (int i = 0; i < shape.length; i++) {
            if (shape[i] > 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Cannot find an axis to label. A valid axis to label should have size larger than 1.");
    }

    private static Map<Integer, List<String>> makeMap(int axis, List<String> labels) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(axis), labels);
        return linkedHashMap;
    }
}
