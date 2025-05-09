package org.tensorflow.lite.support.image;

import android.graphics.RectF;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public final class BoundingBoxUtil {

    /* loaded from: classes6.dex */
    public enum CoordinateType {
        RATIO,
        PIXEL
    }

    /* loaded from: classes6.dex */
    public enum Type {
        BOUNDARIES,
        UPPER_LEFT,
        CENTER
    }

    public static List<RectF> convert(TensorBuffer tensor, int[] valueIndex, int boundingBoxAxis, Type type, CoordinateType coordinateType, int height, int width) {
        int i = boundingBoxAxis;
        int[] shape = tensor.getShape();
        SupportPreconditions.checkArgument(i >= (-shape.length) && i < shape.length, String.format("Axis %d is not in range (-(D+1), D), where D is the number of dimensions of input tensor (shape=%s)", Integer.valueOf(boundingBoxAxis), Arrays.toString(shape)));
        if (i < 0) {
            i += shape.length;
        }
        SupportPreconditions.checkArgument(shape[i] == 4, String.format("Size of bounding box dimension %d is not 4. Got %d in shape %s", Integer.valueOf(i), Integer.valueOf(shape[i]), Arrays.toString(shape)));
        SupportPreconditions.checkArgument(valueIndex.length == 4, String.format("Bounding box index array length %d is not 4. Got index array %s", Integer.valueOf(valueIndex.length), Arrays.toString(valueIndex)));
        SupportPreconditions.checkArgument(tensor.getDataType() == DataType.FLOAT32, "Bounding Boxes only create from FLOAT32 buffers. Got: " + tensor.getDataType().name());
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            i2 *= shape[i3];
        }
        int i4 = 1;
        for (int i5 = i + 1; i5 < shape.length; i5++) {
            i4 *= shape[i5];
        }
        float[] fArr = new float[4];
        ByteBuffer buffer = tensor.getBuffer();
        buffer.rewind();
        FloatBuffer asFloatBuffer = buffer.asFloatBuffer();
        for (int i6 = 0; i6 < i2; i6++) {
            for (int i7 = 0; i7 < i4; i7++) {
                for (int i8 = 0; i8 < 4; i8++) {
                    fArr[i8] = asFloatBuffer.get((((i6 * 4) + i8) * i4) + i7);
                }
                arrayList.add(convertOneBoundingBox(fArr, valueIndex, type, coordinateType, height, width));
            }
        }
        buffer.rewind();
        return arrayList;
    }

    private static RectF convertOneBoundingBox(float[] values, int[] valueIndex, Type type, CoordinateType coordinateType, int height, int width) {
        float[] fArr = new float[4];
        for (int i = 0; i < 4; i++) {
            fArr[i] = values[valueIndex[i]];
        }
        return convertOneBoundingBox(fArr, type, coordinateType, height, width);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.tensorflow.lite.support.image.BoundingBoxUtil$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$support$image$BoundingBoxUtil$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$org$tensorflow$lite$support$image$BoundingBoxUtil$Type = iArr;
            try {
                iArr[Type.BOUNDARIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$image$BoundingBoxUtil$Type[Type.UPPER_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$image$BoundingBoxUtil$Type[Type.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static RectF convertOneBoundingBox(float[] values, Type type, CoordinateType coordinateType, int height, int width) {
        int i = AnonymousClass1.$SwitchMap$org$tensorflow$lite$support$image$BoundingBoxUtil$Type[type.ordinal()];
        if (i == 1) {
            return convertFromBoundaries(values, coordinateType, height, width);
        }
        if (i == 2) {
            return convertFromUpperLeft(values, coordinateType, height, width);
        }
        if (i == 3) {
            return convertFromCenter(values, coordinateType, height, width);
        }
        throw new IllegalArgumentException("Cannot recognize BoundingBox.Type " + type);
    }

    private static RectF convertFromBoundaries(float[] values, CoordinateType coordinateType, int imageHeight, int imageWidth) {
        return getRectF(values[0], values[1], values[2], values[3], imageHeight, imageWidth, coordinateType);
    }

    private static RectF convertFromUpperLeft(float[] values, CoordinateType coordinateType, int imageHeight, int imageWidth) {
        return getRectF(values[0], values[1], values[0] + values[2], values[1] + values[3], imageHeight, imageWidth, coordinateType);
    }

    private static RectF convertFromCenter(float[] values, CoordinateType coordinateType, int imageHeight, int imageWidth) {
        float f = values[0];
        float f2 = values[1];
        float f3 = values[2] / 2.0f;
        float f4 = values[3] / 2.0f;
        return getRectF(f - f3, f2 - f4, f + f3, f2 + f4, imageHeight, imageWidth, coordinateType);
    }

    private static RectF getRectF(float left, float top, float right, float bottom, int imageHeight, int imageWidth, CoordinateType coordinateType) {
        if (coordinateType == CoordinateType.PIXEL) {
            return new RectF(left, top, right, bottom);
        }
        if (coordinateType == CoordinateType.RATIO) {
            float f = imageWidth;
            float f2 = imageHeight;
            return new RectF(left * f, top * f2, right * f, bottom * f2);
        }
        throw new IllegalArgumentException("Cannot convert coordinate type " + coordinateType);
    }

    private BoundingBoxUtil() {
    }
}
