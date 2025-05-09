package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import java.util.Arrays;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'RGB' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes6.dex */
public abstract class ColorSpaceType {
    private static final /* synthetic */ ColorSpaceType[] $VALUES;
    private static final int BATCH_DIM = 0;
    private static final int BATCH_VALUE = 1;
    private static final int CHANNEL_DIM = 3;
    public static final ColorSpaceType GRAYSCALE;
    private static final int HEIGHT_DIM = 1;
    public static final ColorSpaceType NV12;
    public static final ColorSpaceType NV21;
    public static final ColorSpaceType RGB;
    private static final int WIDTH_DIM = 2;
    public static final ColorSpaceType YUV_420_888;
    public static final ColorSpaceType YV12;
    public static final ColorSpaceType YV21;
    private final int value;

    abstract int getNumElements(int height, int width);

    public static ColorSpaceType valueOf(String name) {
        return (ColorSpaceType) Enum.valueOf(ColorSpaceType.class, name);
    }

    public static ColorSpaceType[] values() {
        return (ColorSpaceType[]) $VALUES.clone();
    }

    static {
        int i = 0;
        ColorSpaceType colorSpaceType = new ColorSpaceType("RGB", i, i) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.1
            private static final int CHANNEL_VALUE = 3;

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getChannelValue() {
                return 3;
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return height * width * 3;
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            String getShapeInfoMessage() {
                return "The shape of a RGB image should be (h, w, c) or (1, h, w, c), and channels representing R, G, B in order. ";
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            Bitmap convertTensorBufferToBitmap(TensorBuffer buffer) {
                return ImageConversions.convertRgbTensorBufferToBitmap(buffer);
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int[] getNormalizedShape(int[] shape) {
                int length = shape.length;
                if (length == 3) {
                    return ColorSpaceType.insertValue(shape, 0, 1);
                }
                if (length == 4) {
                    return shape;
                }
                throw new IllegalArgumentException(getShapeInfoMessage() + "The provided image shape is " + Arrays.toString(shape));
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            Bitmap.Config toBitmapConfig() {
                return Bitmap.Config.ARGB_8888;
            }
        };
        RGB = colorSpaceType;
        int i2 = 1;
        ColorSpaceType colorSpaceType2 = new ColorSpaceType("GRAYSCALE", i2, i2) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.2
            private static final int CHANNEL_VALUE = 1;

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getChannelValue() {
                return 1;
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return height * width;
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            String getShapeInfoMessage() {
                return "The shape of a grayscale image should be (h, w) or (1, h, w, 1). ";
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            Bitmap convertTensorBufferToBitmap(TensorBuffer buffer) {
                return ImageConversions.convertGrayscaleTensorBufferToBitmap(buffer);
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int[] getNormalizedShape(int[] shape) {
                int length = shape.length;
                if (length == 2) {
                    return ColorSpaceType.insertValue(ColorSpaceType.insertValue(shape, 0, 1), 3, 1);
                }
                if (length == 4) {
                    return shape;
                }
                throw new IllegalArgumentException(getShapeInfoMessage() + "The provided image shape is " + Arrays.toString(shape));
            }

            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            Bitmap.Config toBitmapConfig() {
                return Bitmap.Config.ALPHA_8;
            }
        };
        GRAYSCALE = colorSpaceType2;
        int i3 = 2;
        ColorSpaceType colorSpaceType3 = new ColorSpaceType("NV12", i3, i3) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.3
            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return ColorSpaceType.getYuv420NumElements(height, width);
            }
        };
        NV12 = colorSpaceType3;
        int i4 = 3;
        ColorSpaceType colorSpaceType4 = new ColorSpaceType("NV21", i4, i4) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.4
            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return ColorSpaceType.getYuv420NumElements(height, width);
            }
        };
        NV21 = colorSpaceType4;
        int i5 = 4;
        ColorSpaceType colorSpaceType5 = new ColorSpaceType("YV12", i5, i5) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.5
            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return ColorSpaceType.getYuv420NumElements(height, width);
            }
        };
        YV12 = colorSpaceType5;
        int i6 = 5;
        ColorSpaceType colorSpaceType6 = new ColorSpaceType("YV21", i6, i6) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.6
            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return ColorSpaceType.getYuv420NumElements(height, width);
            }
        };
        YV21 = colorSpaceType6;
        int i7 = 6;
        ColorSpaceType colorSpaceType7 = new ColorSpaceType("YUV_420_888", i7, i7) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.7
            @Override // org.tensorflow.lite.support.image.ColorSpaceType
            int getNumElements(int height, int width) {
                return ColorSpaceType.getYuv420NumElements(height, width);
            }
        };
        YUV_420_888 = colorSpaceType7;
        $VALUES = new ColorSpaceType[]{colorSpaceType, colorSpaceType2, colorSpaceType3, colorSpaceType4, colorSpaceType5, colorSpaceType6, colorSpaceType7};
    }

    private ColorSpaceType(String $enum$name, int $enum$ordinal, int value) {
        this.value = value;
    }

    /* renamed from: org.tensorflow.lite.support.image.ColorSpaceType$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ColorSpaceType fromBitmapConfig(Bitmap.Config config) {
        int i = AnonymousClass8.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
        if (i == 1) {
            return RGB;
        }
        if (i == 2) {
            return GRAYSCALE;
        }
        throw new IllegalArgumentException("Bitmap configuration: " + config + ", is not supported yet.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ColorSpaceType fromImageFormat(int imageFormat) {
        if (imageFormat == 17) {
            return NV21;
        }
        if (imageFormat == 35) {
            return YUV_420_888;
        }
        if (imageFormat == 842094169) {
            return YV12;
        }
        throw new IllegalArgumentException("ImageFormat: " + imageFormat + ", is not supported yet.");
    }

    public int getValue() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assertShape(int[] shape) {
        assertRgbOrGrayScale("assertShape()");
        SupportPreconditions.checkArgument(isValidNormalizedShape(getNormalizedShape(shape)), getShapeInfoMessage() + "The provided image shape is " + Arrays.toString(shape));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assertNumElements(int numElements, int height, int width) {
        SupportPreconditions.checkArgument(numElements >= getNumElements(height, width), String.format("The given number of elements (%d) does not match the image (%s) in %d x %d. The expected number of elements should be at least %d.", Integer.valueOf(numElements), name(), Integer.valueOf(height), Integer.valueOf(width), Integer.valueOf(getNumElements(height, width))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap convertTensorBufferToBitmap(TensorBuffer buffer) {
        throw new UnsupportedOperationException("convertTensorBufferToBitmap() is unsupported for the color space type " + name());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWidth(int[] shape) {
        assertRgbOrGrayScale("getWidth()");
        assertShape(shape);
        return getNormalizedShape(shape)[2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHeight(int[] shape) {
        assertRgbOrGrayScale("getHeight()");
        assertShape(shape);
        return getNormalizedShape(shape)[1];
    }

    int getChannelValue() {
        throw new UnsupportedOperationException("getChannelValue() is unsupported for the color space type " + name());
    }

    int[] getNormalizedShape(int[] shape) {
        throw new UnsupportedOperationException("getNormalizedShape() is unsupported for the color space type " + name());
    }

    String getShapeInfoMessage() {
        throw new UnsupportedOperationException("getShapeInfoMessage() is unsupported for the color space type " + name());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap.Config toBitmapConfig() {
        throw new UnsupportedOperationException("toBitmapConfig() is unsupported for the color space type " + name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getYuv420NumElements(int height, int width) {
        return (height * width) + (((height + 1) / 2) * ((width + 1) / 2) * 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] insertValue(int[] array, int pos, int value) {
        int length = array.length + 1;
        int[] iArr = new int[length];
        for (int i = 0; i < pos; i++) {
            iArr[i] = array[i];
        }
        iArr[pos] = value;
        while (true) {
            pos++;
            if (pos >= length) {
                return iArr;
            }
            iArr[pos] = array[pos - 1];
        }
    }

    protected boolean isValidNormalizedShape(int[] shape) {
        return shape[0] == 1 && shape[1] > 0 && shape[2] > 0 && shape[3] == getChannelValue();
    }

    private void assertRgbOrGrayScale(String unsupportedMethodName) {
        if (this == RGB || this == GRAYSCALE) {
            return;
        }
        throw new UnsupportedOperationException(unsupportedMethodName + " only supports RGB and GRAYSCALE formats, but not " + name());
    }
}
