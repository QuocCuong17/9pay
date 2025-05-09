package co.hyperverge.hyperdocdetect.carddetectorservice.models;

import android.graphics.Bitmap;

/* loaded from: classes2.dex */
public class HVCardDetectorInput {
    private Bitmap bitmap;

    protected boolean canEqual(Object obj) {
        return obj instanceof HVCardDetectorInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVCardDetectorInput)) {
            return false;
        }
        HVCardDetectorInput hVCardDetectorInput = (HVCardDetectorInput) obj;
        if (!hVCardDetectorInput.canEqual(this)) {
            return false;
        }
        Bitmap bitmap = getBitmap();
        Bitmap bitmap2 = hVCardDetectorInput.getBitmap();
        return bitmap != null ? bitmap.equals(bitmap2) : bitmap2 == null;
    }

    public int hashCode() {
        Bitmap bitmap = getBitmap();
        return 59 + (bitmap == null ? 43 : bitmap.hashCode());
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String toString() {
        return "HVCardDetectorInput(bitmap=" + getBitmap() + ")";
    }

    public HVCardDetectorInput(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
