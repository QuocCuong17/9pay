package co.hyperverge.facedetection;

import android.os.Environment;
import java.io.File;

/* loaded from: classes2.dex */
public final class Constants {
    private Constants() {
    }

    public static String getFaceShapeModelPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "shape_predictor_68_face_landmarks.dat";
    }

    public static String getJSONPropPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "thuglife.json";
    }
}
