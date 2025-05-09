package co.hyperverge.hypersnapsdk.service.qr;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class HVBarcodeDetector {
    public static final String TAG = "co.hyperverge.hypersnapsdk.service.qr.HVBarcodeDetector";
    private BarcodeDetector detector;

    public void initialiseHVBarcodeDetector(Context context, int i) throws NoClassDefFoundError {
        if (context != null) {
            this.detector = new BarcodeDetector.Builder(context).setBarcodeFormats(i).build();
        } else {
            Log.e(TAG, "context is null");
        }
    }

    public String detect(Bitmap bitmap) throws NoClassDefFoundError {
        HVLogUtils.d(TAG, "detect() called with: bitmap = [" + bitmap + "]");
        if (this.detector != null) {
            SparseArray<Barcode> detect = this.detector.detect(new Frame.Builder().setBitmap(bitmap).build());
            if (detect.size() != 0) {
                return detect.valueAt(0).rawValue;
            }
        }
        return "";
    }

    public String detect(byte[] bArr, int i, int i2) {
        HVLogUtils.d(TAG, "detect() called with: frames = [" + bArr + "], width = [" + i + "], height = [" + i2 + "]");
        SparseArray<Barcode> detect = this.detector.detect(new Frame.Builder().setImageData(ByteBuffer.wrap(bArr), i, i2, 17).build());
        return detect.size() != 0 ? detect.valueAt(0).rawValue : "";
    }
}
