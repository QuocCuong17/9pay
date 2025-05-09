package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class WaterMarkHelper {
    static WaterMarkHelper waterMarkHelper;
    public final String TAG = getClass().getSimpleName();
    HVFaceConfig faceConfig;

    public static WaterMarkHelper get() {
        if (waterMarkHelper == null) {
            waterMarkHelper = new WaterMarkHelper();
        }
        return waterMarkHelper;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:2|3|(1:5)|(1:7)(1:28)|8|(2:9|10)|(2:12|13)|14|15|16|17) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00c6, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c7, code lost:
    
        android.util.Log.e(r7.TAG, co.hyperverge.hypersnapsdk.utils.Utils.getErrorMessage(r9));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String addWaterMarkOnFullImage(Context context, HVFaceConfig hVFaceConfig, String str, String str2, boolean z) {
        FileOutputStream fileOutputStream;
        HVLogUtils.d(this.TAG, "addWaterMarkOnFullImage() called with: context = [" + context + "], faceConfig = [" + hVFaceConfig + "], latLongString = [" + str + "], imageUri = [" + str2 + "], isCrop = [" + z + "]");
        try {
            String timeStamp = getTimeStamp();
            this.faceConfig = hVFaceConfig;
            new BitmapFactory.Options().inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap decodeFile = BitmapFactory.decodeFile(str2);
            Bitmap createBitmap = Bitmap.createBitmap(decodeFile.getWidth(), decodeFile.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(decodeFile, 0.0f, 0.0f, (Paint) null);
            drawText(z, canvas, timeStamp, str, decodeFile);
            File file = new File(context.getFilesDir(), "hv");
            if (!file.exists()) {
                file.mkdirs();
            }
            String str3 = z ? "FD_watermark_crop_image_" : "FD_watermark_full_image_";
            File file2 = new File(file.getAbsolutePath(), str3 + System.currentTimeMillis() + ".jpg");
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception e) {
                e = e;
                fileOutputStream = null;
            }
            try {
                createBitmap.compress(Bitmap.CompressFormat.JPEG, Utils.JPEG_COMPRESSION_QUALITY, fileOutputStream);
            } catch (Exception e2) {
                e = e2;
                Log.e(this.TAG, Utils.getErrorMessage(e));
                fileOutputStream.close();
                return file2.getAbsolutePath();
            }
            fileOutputStream.close();
            return file2.getAbsolutePath();
        } catch (OutOfMemoryError e3) {
            Log.e(this.TAG, Utils.getErrorMessage(e3));
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e3);
            return null;
        }
    }

    private void drawText(boolean z, Canvas canvas, String str, String str2, Bitmap bitmap) {
        getTextOnBitmapPaint(z).getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, 0.0f, (bitmap.getHeight() - r0.height()) - 20.0f, getTextOnBitmapPaint(z));
        canvas.drawText(str2, 0.0f, bitmap.getHeight() - 10, getTextOnBitmapPaint(z));
    }

    private String getTimeStamp() {
        HVLogUtils.d(this.TAG, "getTimeStamp() called");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    private Paint getTextOnBitmapPaint(boolean z) {
        HVLogUtils.d(this.TAG, "getTextOnBitmapPaint() called with: isCrop = [" + z + "]");
        Paint paint = new Paint();
        if (z) {
            paint.setTextSize(this.faceConfig.getCropImageWaterMarkTextSizePx());
        } else {
            paint.setTextSize(this.faceConfig.getFullImageWaterMarkTextSizePx());
        }
        paint.setColor(this.faceConfig.getWaterMarkColor());
        paint.setFakeBoldText(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        return paint;
    }
}
