package co.hyperverge.facedetection;

import android.graphics.Bitmap;
import android.util.Log;
import co.hyperverge.facedetection.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DetectorUtils {
    private static final String TAG = "co.hyperverge.facedetection.DetectorUtils";

    public static Bitmap processBitmapFromPath(String str) {
        FileUtils.Dimensions bitmapDimension = FileUtils.getBitmapDimension(str);
        try {
            return FileUtils.decodeSampledBitmapFromFileForFace(str, bitmapDimension, FileUtils.getScaledDim(bitmapDimension, 256));
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
    }

    public static JSONObject performFaceCalculations(float f, float f2, float f3, float f4, float f5, float f6) {
        JSONObject jSONObject = new JSONObject();
        float f7 = f / f5;
        float f8 = f2 / f6;
        float f9 = f3 / f5;
        float f10 = f4 / f6;
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        if (f8 < 0.0f) {
            f8 = 0.0f;
        }
        if (f9 + f7 > 1.0f) {
            f9 = 1.0f - f7;
        }
        if (f10 + f8 > 1.0f) {
            f10 = 1.0f - f8;
        }
        try {
            jSONObject.put("ltx", f7);
            jSONObject.put("lty", f8);
            jSONObject.put("rbx", f7 + f9);
            jSONObject.put("rby", f8 + f10);
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
        return jSONObject;
    }
}
