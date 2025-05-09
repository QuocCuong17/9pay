package co.hyperverge.hyperdocdetect.carddetectorservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import co.hyperverge.hyperdocdetect.carddetectorservice.models.HVCardDetectionResult;
import co.hyperverge.hyperdocdetect.carddetectorservice.models.HVCardDetectorInput;
import co.hyperverge.hyperdocdetect.carddetectorservice.models.HVCardPredictionThreshold;
import co.hyperverge.hyperdocdetect.utils.PartitionList;
import java.util.ArrayList;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes2.dex */
public class HVTfliteHelper {
    private static final String MODEL_PATH = "yolov5n_224x224_Lite.tflite";
    private static final String TAG = "co.hyperverge.hyperdocdetect.carddetectorservice.HVTfliteHelper";
    private static HVTfliteHelper hvTfliteHelper;
    private HVCardDetectorInput hvCardDetectorInput;
    private HVTfLiteInput hvTfLiteInput;
    private long startTime = 0;
    private long endTime = 0;
    private int targetWidth = 224;
    private int targetHeight = 224;
    private boolean isModelLoaded = false;
    private HVCardPredictionThreshold hvCardPredictionThreshold = new HVCardPredictionThreshold();

    private HVTfliteHelper() {
    }

    public static HVTfliteHelper getInstance() {
        if (hvTfliteHelper == null) {
            hvTfliteHelper = new HVTfliteHelper();
        }
        return hvTfliteHelper;
    }

    public void setHVCardPredictionThreshold(HVCardPredictionThreshold hVCardPredictionThreshold) {
        this.hvCardPredictionThreshold = hVCardPredictionThreshold;
    }

    /* JADX WARN: Type inference failed for: r10v12, types: [org.tensorflow.lite.support.image.ImageProcessor] */
    public boolean initialiseDetector(Context context) {
        if (this.isModelLoaded) {
            return true;
        }
        try {
            Interpreter interpreter = new Interpreter(FileUtil.loadMappedFile(context, MODEL_PATH), new Interpreter.Options().setNumThreads(4));
            int[] shape = interpreter.getInputTensor(0).shape();
            int[] shape2 = interpreter.getOutputTensor(0).shape();
            DataType dataType = interpreter.getOutputTensor(0).dataType();
            TensorBuffer createFixedSize = TensorBuffer.createFixedSize(shape2, dataType);
            this.targetHeight = shape[1];
            this.targetWidth = shape[2];
            this.hvTfLiteInput = new HVTfLiteInput(dataType, interpreter, createFixedSize, new ImageProcessor.Builder().add((ImageOperator) new ResizeOp(this.targetHeight, this.targetWidth, ResizeOp.ResizeMethod.BILINEAR)).add((TensorOperator) new NormalizeOp(0.0f, 255.0f)).build());
            this.isModelLoaded = true;
        } catch (Exception e) {
            Log.e(TAG, "initialiseDetector: " + e.getLocalizedMessage());
            this.isModelLoaded = false;
        }
        return this.isModelLoaded;
    }

    public HVCardDetectionResult detectCard(HVCardDetectorInput hVCardDetectorInput) {
        this.startTime = System.currentTimeMillis();
        this.hvCardDetectorInput = hVCardDetectorInput;
        this.hvTfLiteInput.getTflite().run(prepareTensorImage(hVCardDetectorInput.getBitmap()).getBuffer(), this.hvTfLiteInput.getFixedSize().getBuffer());
        return obtainCardDetectionResult();
    }

    private TensorImage prepareTensorImage(Bitmap bitmap) {
        TensorImage tensorImage = new TensorImage(this.hvTfLiteInput.getOutputDataType());
        tensorImage.load(bitmap);
        return this.hvTfLiteInput.getImageProcessor().process(tensorImage);
    }

    private PartitionList<Float> getPredictionList() {
        float[] floatArray = this.hvTfLiteInput.getFixedSize().getFloatArray();
        ArrayList arrayList = new ArrayList(floatArray.length);
        for (float f : floatArray) {
            arrayList.add(Float.valueOf(f));
        }
        return PartitionList.ofSize(arrayList, 6);
    }

    private HVCardDetectionResult obtainCardDetectionResult() {
        PartitionList<Float> predictionList = getPredictionList();
        float f = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < predictionList.size(); i2++) {
            float floatValue = predictionList.get(i2).get(4).floatValue();
            if (floatValue > f) {
                i = i2;
                f = floatValue;
            }
        }
        if (f <= this.hvCardPredictionThreshold.getMinPredictionScore() || f >= this.hvCardPredictionThreshold.getMaxPredictionScore()) {
            return null;
        }
        this.endTime = System.currentTimeMillis();
        return new HVCardDetectionResult(predictionList.get(i), this.targetHeight, this.endTime - this.startTime);
    }
}
