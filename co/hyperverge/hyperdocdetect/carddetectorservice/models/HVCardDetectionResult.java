package co.hyperverge.hyperdocdetect.carddetectorservice.models;

import java.util.List;

/* loaded from: classes2.dex */
public class HVCardDetectionResult {
    private final float documentLabel;
    private final float normalisedBottomRightX;
    private final float normalisedBottomRightY;
    private final float normalisedHeight;
    private final float normalisedTopLeftX;
    private final float normalisedTopLeftY;
    private final float normalisedWidth;
    private final float predictionScore;
    private final float tfliteInputShapeHeight;
    private final long timeToDetect;

    public String toString() {
        return "HVCardDetectionResult(normalisedTopLeftX=" + getNormalisedTopLeftX() + ", normalisedTopLeftY=" + getNormalisedTopLeftY() + ", normalisedBottomRightX=" + getNormalisedBottomRightX() + ", normalisedBottomRightY=" + getNormalisedBottomRightY() + ", normalisedWidth=" + getNormalisedWidth() + ", normalisedHeight=" + getNormalisedHeight() + ", predictionScore=" + getPredictionScore() + ", documentLabel=" + getDocumentLabel() + ", tfliteInputShapeHeight=" + getTfliteInputShapeHeight() + ", timeToDetect=" + getTimeToDetect() + ")";
    }

    public float getNormalisedTopLeftX() {
        return this.normalisedTopLeftX;
    }

    public float getNormalisedTopLeftY() {
        return this.normalisedTopLeftY;
    }

    public float getNormalisedBottomRightX() {
        return this.normalisedBottomRightX;
    }

    public float getNormalisedBottomRightY() {
        return this.normalisedBottomRightY;
    }

    public float getNormalisedWidth() {
        return this.normalisedWidth;
    }

    public float getNormalisedHeight() {
        return this.normalisedHeight;
    }

    public float getPredictionScore() {
        return this.predictionScore;
    }

    public float getDocumentLabel() {
        return this.documentLabel;
    }

    public float getTfliteInputShapeHeight() {
        return this.tfliteInputShapeHeight;
    }

    public long getTimeToDetect() {
        return this.timeToDetect;
    }

    public HVCardDetectionResult(List<Float> list, int i, long j) {
        float floatValue = list.get(0).floatValue();
        float floatValue2 = list.get(1).floatValue();
        float floatValue3 = list.get(2).floatValue();
        float floatValue4 = list.get(3).floatValue();
        float floatValue5 = list.get(4).floatValue();
        float floatValue6 = list.get(5).floatValue();
        this.normalisedWidth = floatValue3;
        this.normalisedHeight = floatValue4;
        float f = floatValue3 / 2.0f;
        this.normalisedTopLeftX = floatValue - f;
        float f2 = floatValue4 / 2.0f;
        this.normalisedTopLeftY = floatValue2 - f2;
        this.normalisedBottomRightX = floatValue + f;
        this.normalisedBottomRightY = floatValue2 + f2;
        this.predictionScore = floatValue5;
        this.documentLabel = floatValue6;
        this.tfliteInputShapeHeight = i;
        this.timeToDetect = j;
    }
}
