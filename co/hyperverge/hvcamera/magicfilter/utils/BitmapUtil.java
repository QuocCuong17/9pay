package co.hyperverge.hvcamera.magicfilter.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import co.hyperverge.hvcamera.HVLog;
import java.io.ByteArrayOutputStream;

/* loaded from: classes2.dex */
public class BitmapUtil {
    private static final String TAG = "BitmapUtil";

    public static byte[] createByteArrayForFrame(byte[] bArr, int i, int i2, int i3) {
        HVLog.d(TAG, "createByteArrayForFrame() called with: data = [" + bArr + "], finalHeight = [" + i + "], finalWidth = [" + i2 + "], mRotation = [" + i3 + "]");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i3 != 90 && i3 != 270) {
            i2 = i;
            i = i2;
        }
        int i4 = i;
        int i5 = i2;
        new YuvImage(bArr, 17, i4, i5, null).compressToJpeg(new Rect(0, 0, i, i2), 90, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        Matrix matrix = new Matrix();
        matrix.postRotate(i3);
        Bitmap.createBitmap(decodeByteArray, 0, 0, i4, i5, matrix, false).compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream2);
        return byteArrayOutputStream2.toByteArray();
    }
}
