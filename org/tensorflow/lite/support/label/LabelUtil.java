package org.tensorflow.lite.support.label;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class LabelUtil {
    public static List<String> mapValueToLabels(TensorBuffer tensorBuffer, List<String> labels, int offset) {
        SupportPreconditions.checkNotNull(tensorBuffer, "Given tensor should not be null");
        SupportPreconditions.checkNotNull(labels, "Given labels should not be null");
        int[] intArray = tensorBuffer.getIntArray();
        Log.d("values", Arrays.toString(intArray));
        ArrayList arrayList = new ArrayList();
        for (int i : intArray) {
            int i2 = i + offset;
            if (i2 < 0 || i2 >= labels.size()) {
                arrayList.add("");
            } else {
                arrayList.add(labels.get(i2));
            }
        }
        return arrayList;
    }

    private LabelUtil() {
    }
}
