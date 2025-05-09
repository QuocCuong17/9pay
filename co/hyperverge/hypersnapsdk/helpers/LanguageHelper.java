package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import co.hyperverge.hypersnapsdk.R;

/* loaded from: classes2.dex */
public class LanguageHelper {
    public static String AUTO_CAPTURE_ACTION;
    public static String AUTO_CAPTURE_WAIT;
    public static String CAPTURE_PIC;
    public static String FACE_NOT_PRESENT_IN_CAPTURED_IMAGE;
    public static String LOOK_STRAIGHT;
    public static String MOVE_AWAY;
    public static String MOVE_CLOSER;
    public static String MULTIPLE_FACES;
    public static String PHONE_STRAIGHT;
    public static String PLACE_FACE;
    public static String PROCESSING;
    public static String STAY_STILL;

    public static void init(Context context) {
        PLACE_FACE = context.getResources().getString(R.string.faceCaptureFaceNotFound);
        CAPTURE_PIC = context.getResources().getString(R.string.faceCaptureFaceFound);
        PROCESSING = context.getResources().getString(R.string.faceCaptureActivity);
        MOVE_CLOSER = context.getResources().getString(R.string.moveCloser);
        MOVE_AWAY = context.getResources().getString(R.string.faceCaptureMoveAway);
        MULTIPLE_FACES = context.getResources().getString(R.string.faceCaptureMultipleFaces);
        LOOK_STRAIGHT = context.getResources().getString(R.string.faceCaptureLookStraight);
        AUTO_CAPTURE_WAIT = context.getResources().getString(R.string.faceCaptureAutoCaptureWait);
        AUTO_CAPTURE_ACTION = context.getResources().getString(R.string.faceCaptureAutoCaptureAction);
        PHONE_STRAIGHT = context.getResources().getString(R.string.faceCapturePhoneStraight);
        STAY_STILL = context.getResources().getString(R.string.faceCaptureStayStill);
        FACE_NOT_PRESENT_IN_CAPTURED_IMAGE = context.getResources().getString(R.string.faceNotPresentInCapturedImage);
    }

    public static void refresh(Context context) {
        init(context);
    }
}
