package co.hyperverge.hypersnapsdk.helpers.face;

/* loaded from: classes2.dex */
public class FaceConstants {
    public static int MIN_STABLE_COUNT = 5;
    public static final float MLKIT_MIN_FACE_SIZE = 0.2f;
    public static final float PERCENT_WIDTH_MAX_FOR_CAPTURE = 0.6f;
    public static final float PERCENT_WIDTH_MIN_CAPTURE_BACK_CAM = 0.35f;
    public static final float PERCENT_WIDTH_MIN_CAPTURE_FRONT_CAM = 0.3f;
    public static final float STABLE_FRAME_COUNTER = 5.0f;

    /* loaded from: classes2.dex */
    public enum FaceDetectionState {
        FACE_DETECTED,
        FACE_NOT_DETECTED,
        FACE_TOO_CLOSE,
        FACE_TOO_FAR,
        MULTIPLE_FACES,
        FACE_NOT_STRAIGHT,
        FACE_STAY_STILL,
        SAVE_UPLOAD,
        PHONE_NOT_STRAIGHT
    }
}
