package co.hyperverge.hyperdocdetect.carddetectorservice;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* compiled from: HVTfliteHelper.java */
/* loaded from: classes2.dex */
class HVTfLiteInput {
    private TensorBuffer fixedSize;
    private ImageProcessor imageProcessor;
    private DataType outputDataType;
    private Interpreter tflite;

    public HVTfLiteInput(DataType dataType, Interpreter interpreter, TensorBuffer tensorBuffer, ImageProcessor imageProcessor) {
        this.outputDataType = dataType;
        this.tflite = interpreter;
        this.fixedSize = tensorBuffer;
        this.imageProcessor = imageProcessor;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof HVTfLiteInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVTfLiteInput)) {
            return false;
        }
        HVTfLiteInput hVTfLiteInput = (HVTfLiteInput) obj;
        if (!hVTfLiteInput.canEqual(this)) {
            return false;
        }
        DataType outputDataType = getOutputDataType();
        DataType outputDataType2 = hVTfLiteInput.getOutputDataType();
        if (outputDataType != null ? !outputDataType.equals(outputDataType2) : outputDataType2 != null) {
            return false;
        }
        Interpreter tflite = getTflite();
        Interpreter tflite2 = hVTfLiteInput.getTflite();
        if (tflite != null ? !tflite.equals(tflite2) : tflite2 != null) {
            return false;
        }
        TensorBuffer fixedSize = getFixedSize();
        TensorBuffer fixedSize2 = hVTfLiteInput.getFixedSize();
        if (fixedSize != null ? !fixedSize.equals(fixedSize2) : fixedSize2 != null) {
            return false;
        }
        ImageProcessor imageProcessor = getImageProcessor();
        ImageProcessor imageProcessor2 = hVTfLiteInput.getImageProcessor();
        return imageProcessor != null ? imageProcessor.equals(imageProcessor2) : imageProcessor2 == null;
    }

    public int hashCode() {
        DataType outputDataType = getOutputDataType();
        int hashCode = outputDataType == null ? 43 : outputDataType.hashCode();
        Interpreter tflite = getTflite();
        int hashCode2 = ((hashCode + 59) * 59) + (tflite == null ? 43 : tflite.hashCode());
        TensorBuffer fixedSize = getFixedSize();
        int hashCode3 = (hashCode2 * 59) + (fixedSize == null ? 43 : fixedSize.hashCode());
        ImageProcessor imageProcessor = getImageProcessor();
        return (hashCode3 * 59) + (imageProcessor != null ? imageProcessor.hashCode() : 43);
    }

    public void setFixedSize(TensorBuffer tensorBuffer) {
        this.fixedSize = tensorBuffer;
    }

    public void setImageProcessor(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    public void setOutputDataType(DataType dataType) {
        this.outputDataType = dataType;
    }

    public void setTflite(Interpreter interpreter) {
        this.tflite = interpreter;
    }

    public String toString() {
        return "HVTfLiteInput(outputDataType=" + getOutputDataType() + ", tflite=" + getTflite() + ", fixedSize=" + getFixedSize() + ", imageProcessor=" + getImageProcessor() + ")";
    }

    public DataType getOutputDataType() {
        return this.outputDataType;
    }

    public Interpreter getTflite() {
        return this.tflite;
    }

    public TensorBuffer getFixedSize() {
        return this.fixedSize;
    }

    public ImageProcessor getImageProcessor() {
        return this.imageProcessor;
    }
}
