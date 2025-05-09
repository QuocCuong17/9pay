package co.hyperverge.facedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import co.hyperverge.facedetection.FileUtils;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FaceCropper {
    private static final String LOG_TAG = "co.hyperverge.facedetection.FaceCropper";
    private static final int MAX_FACE_DIM = 140;
    private static final boolean copyToExternal = false;

    public static String cropFaceAndStore(Context context, HVFace hVFace) {
        try {
            Bitmap croppedFaceFromBitmap = getCroppedFaceFromBitmap(processBitmapFromPath(hVFace.getFilePath()), hVFace);
            String saveCroppedBitmapToPhone = saveCroppedBitmapToPhone(context, hVFace.getFaceLabel() + ".jpg", croppedFaceFromBitmap);
            croppedFaceFromBitmap.recycle();
            return saveCroppedBitmapToPhone;
        } catch (NullPointerException e) {
            Log.d(LOG_TAG, e.getMessage());
            return null;
        }
    }

    public static String cropFaceAndStore(Context context, HVFace hVFace, Bitmap bitmap) {
        try {
            Bitmap croppedFaceFromBitmap = getCroppedFaceFromBitmap(bitmap, hVFace);
            String saveCroppedBitmapToPhone = saveCroppedBitmapToPhone(context, hVFace.getFaceLabel() + ".jpg", croppedFaceFromBitmap);
            croppedFaceFromBitmap.recycle();
            return saveCroppedBitmapToPhone;
        } catch (NullPointerException e) {
            Log.d(LOG_TAG, e.getMessage());
            return null;
        }
    }

    private static String saveCroppedBitmapToPhone(Context context, String str, Bitmap bitmap) {
        int width;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = context.openFileOutput(str, 0);
                    if (bitmap.getWidth() > bitmap.getHeight()) {
                        width = bitmap.getHeight();
                    } else {
                        width = bitmap.getWidth();
                    }
                    float f = (width * 1.0f) / 140.0f;
                    if (f > 1.0f) {
                        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() / f), (int) (bitmap.getHeight() / f), true);
                    }
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fileOutputStream);
                } catch (Throwable th) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            Log.d(LOG_TAG, e.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                Log.d(LOG_TAG, e2.getMessage());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e3) {
            Log.d(LOG_TAG, e3.getMessage());
        }
        return context.getFilesDir() + RemoteSettings.FORWARD_SLASH_STRING + str;
    }

    private static Bitmap grayscaleBitmap(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int alpha = Color.alpha(bitmap.getPixel(i, i2));
                int red = (int) ((Color.red(r6) * 0.299d) + (Color.green(r6) * 0.587d) + (Color.blue(r6) * 0.114d));
                createBitmap.setPixel(i, i2, Color.argb(alpha, red, red, red));
            }
        }
        return createBitmap;
    }

    private static Bitmap getCroppedFaceFromBitmap(Bitmap bitmap, HVFace hVFace) {
        try {
            JSONObject jSONObject = new JSONObject(hVFace.getFaceLocation());
            return Bitmap.createBitmap(bitmap, hVFace.getActualLeftTopX(jSONObject.getInt("ltx"), bitmap.getWidth()), hVFace.getActualLeftTopY(jSONObject.getInt("lty"), bitmap.getHeight()), hVFace.getActualWidth(jSONObject.getInt("width"), bitmap.getWidth()), hVFace.getActualHeight(jSONObject.getInt("height"), bitmap.getHeight()));
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
            return bitmap;
        }
    }

    private static Bitmap processBitmapFromPath(String str) {
        FileUtils.Dimensions bitmapDimension = FileUtils.getBitmapDimension(str);
        try {
            return FileUtils.decodeSampledBitmapFromFileForFace(str, bitmapDimension, FileUtils.getScaledDim(bitmapDimension, 512));
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
            return null;
        }
    }
}
