package co.hyperverge.hyperdocdetect.carddetectorservice.models;

/* loaded from: classes2.dex */
public class HVCardPredictionThreshold {
    private float maxPredictionScore;
    private float minPredictionScore;

    public HVCardPredictionThreshold() {
        this.minPredictionScore = 0.8f;
        this.maxPredictionScore = 1.0f;
    }

    public HVCardPredictionThreshold(float f, float f2) {
        this.minPredictionScore = 0.8f;
        this.maxPredictionScore = 1.0f;
        this.minPredictionScore = f;
        this.maxPredictionScore = f2;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof HVCardPredictionThreshold;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVCardPredictionThreshold)) {
            return false;
        }
        HVCardPredictionThreshold hVCardPredictionThreshold = (HVCardPredictionThreshold) obj;
        return hVCardPredictionThreshold.canEqual(this) && Float.compare(getMinPredictionScore(), hVCardPredictionThreshold.getMinPredictionScore()) == 0 && Float.compare(getMaxPredictionScore(), hVCardPredictionThreshold.getMaxPredictionScore()) == 0;
    }

    public int hashCode() {
        return ((Float.floatToIntBits(getMinPredictionScore()) + 59) * 59) + Float.floatToIntBits(getMaxPredictionScore());
    }

    public void setMaxPredictionScore(float f) {
        this.maxPredictionScore = f;
    }

    public void setMinPredictionScore(float f) {
        this.minPredictionScore = f;
    }

    public String toString() {
        return "HVCardPredictionThreshold(minPredictionScore=" + getMinPredictionScore() + ", maxPredictionScore=" + getMaxPredictionScore() + ")";
    }

    public float getMinPredictionScore() {
        return this.minPredictionScore;
    }

    public float getMaxPredictionScore() {
        return this.maxPredictionScore;
    }
}
