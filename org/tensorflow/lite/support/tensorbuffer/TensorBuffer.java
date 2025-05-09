package org.tensorflow.lite.support.tensorbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes6.dex */
public abstract class TensorBuffer {
    protected ByteBuffer buffer;
    protected int flatSize = -1;
    protected final boolean isDynamic = true;
    protected int[] shape;

    public abstract DataType getDataType();

    public abstract float[] getFloatArray();

    public abstract float getFloatValue(int absIndex);

    public abstract int[] getIntArray();

    public abstract int getIntValue(int absIndex);

    public abstract int getTypeSize();

    public abstract void loadArray(float[] src, int[] shape);

    public abstract void loadArray(int[] src, int[] shape);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.tensorflow.lite.support.tensorbuffer.TensorBuffer$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$org$tensorflow$lite$DataType = iArr;
            try {
                iArr[DataType.FLOAT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.UINT8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static TensorBuffer createFixedSize(int[] shape, DataType dataType) {
        int i = AnonymousClass1.$SwitchMap$org$tensorflow$lite$DataType[dataType.ordinal()];
        if (i == 1) {
            return new TensorBufferFloat(shape);
        }
        if (i == 2) {
            return new TensorBufferUint8(shape);
        }
        throw new AssertionError("TensorBuffer does not support data type: " + dataType);
    }

    public static TensorBuffer createDynamic(DataType dataType) {
        int i = AnonymousClass1.$SwitchMap$org$tensorflow$lite$DataType[dataType.ordinal()];
        if (i == 1) {
            return new TensorBufferFloat();
        }
        if (i == 2) {
            return new TensorBufferUint8();
        }
        throw new AssertionError("TensorBuffer does not support data type: " + dataType);
    }

    public static TensorBuffer createFrom(TensorBuffer buffer, DataType dataType) {
        TensorBuffer createFixedSize;
        SupportPreconditions.checkNotNull(buffer, "Cannot create a buffer from null");
        if (buffer.isDynamic()) {
            createFixedSize = createDynamic(dataType);
        } else {
            createFixedSize = createFixedSize(buffer.shape, dataType);
        }
        if (buffer.getDataType() == DataType.FLOAT32 && dataType == DataType.FLOAT32) {
            createFixedSize.loadArray(buffer.getFloatArray(), buffer.shape);
        } else {
            createFixedSize.loadArray(buffer.getIntArray(), buffer.shape);
        }
        return createFixedSize;
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public int getFlatSize() {
        assertShapeIsCorrect();
        return this.flatSize;
    }

    public int[] getShape() {
        assertShapeIsCorrect();
        int[] iArr = this.shape;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public boolean isDynamic() {
        return this.isDynamic;
    }

    public void loadArray(int[] src) {
        loadArray(src, this.shape);
    }

    public void loadArray(float[] src) {
        loadArray(src, this.shape);
    }

    public void loadBuffer(ByteBuffer buffer, int[] shape) {
        SupportPreconditions.checkNotNull(buffer, "Byte buffer cannot be null.");
        SupportPreconditions.checkArgument(isShapeValid(shape), "Values in TensorBuffer shape should be non-negative.");
        int computeFlatSize = computeFlatSize(shape);
        SupportPreconditions.checkArgument(buffer.limit() == getTypeSize() * computeFlatSize, "The size of byte buffer and the shape do not match. Expected: " + (getTypeSize() * computeFlatSize) + " Actual: " + buffer.limit());
        if (!this.isDynamic) {
            SupportPreconditions.checkArgument(Arrays.equals(shape, this.shape));
        }
        this.shape = (int[]) shape.clone();
        this.flatSize = computeFlatSize;
        buffer.rewind();
        this.buffer = buffer;
    }

    public void loadBuffer(ByteBuffer buffer) {
        loadBuffer(buffer, this.shape);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TensorBuffer(int[] shape) {
        allocateMemory(shape);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TensorBuffer() {
        allocateMemory(new int[]{0});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int computeFlatSize(int[] shape) {
        SupportPreconditions.checkNotNull(shape, "Shape cannot be null.");
        int i = 1;
        for (int i2 : shape) {
            i *= i2;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resize(int[] shape) {
        if (this.isDynamic) {
            allocateMemory(shape);
        } else {
            SupportPreconditions.checkArgument(Arrays.equals(shape, this.shape));
            this.shape = (int[]) shape.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void copyByteBufferIfReadOnly() {
        if (this.buffer.isReadOnly()) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.buffer.capacity());
            allocateDirect.order(this.buffer.order());
            allocateDirect.put(this.buffer);
            allocateDirect.rewind();
            this.buffer = allocateDirect;
        }
    }

    private void allocateMemory(int[] shape) {
        SupportPreconditions.checkNotNull(shape, "TensorBuffer shape cannot be null.");
        SupportPreconditions.checkArgument(isShapeValid(shape), "Values in TensorBuffer shape should be non-negative.");
        int computeFlatSize = computeFlatSize(shape);
        this.shape = (int[]) shape.clone();
        if (this.flatSize == computeFlatSize) {
            return;
        }
        this.flatSize = computeFlatSize;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(computeFlatSize * getTypeSize());
        this.buffer = allocateDirect;
        allocateDirect.order(ByteOrder.nativeOrder());
    }

    private void assertShapeIsCorrect() {
        SupportPreconditions.checkState(this.buffer.limit() == getTypeSize() * computeFlatSize(this.shape), String.format("The size of underlying ByteBuffer (%d) and the shape (%s) do not match. The ByteBuffer may have been changed.", Integer.valueOf(this.buffer.limit()), Arrays.toString(this.shape)));
    }

    private static boolean isShapeValid(int[] shape) {
        if (shape.length == 0) {
            return true;
        }
        for (int i : shape) {
            if (i < 0) {
                return false;
            }
        }
        return true;
    }
}
