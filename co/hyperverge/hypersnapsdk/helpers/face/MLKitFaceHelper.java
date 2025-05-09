package co.hyperverge.hypersnapsdk.helpers.face;

import android.graphics.Bitmap;
import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes2.dex */
public class MLKitFaceHelper {
    private static final String TAG = "MLKitHelper";
    private static MLKitFaceHelper instance;
    private CameraProperties cameraProperties;
    private HVFaceConfig faceConfig;
    private FaceDetectionListener faceDetectionListener;
    private boolean isProcessing;
    private FaceViewListener mView;
    private FaceDetector mlKitdetector;
    private int stableFrameCounter = 0;

    private MLKitFaceHelper() {
        this.isProcessing = false;
        HVLogUtils.d(TAG, "MLKitFaceHelper() called");
        try {
            this.mlKitdetector = FaceDetection.getClient();
            this.isProcessing = false;
        } catch (Exception e) {
            HVLogUtils.e(TAG, "MLKitFaceHelper(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            setMlKitdetectorUnavailable(Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        } catch (NoClassDefFoundError e2) {
            HVLogUtils.e(TAG, "MLKitFaceHelper(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            setMlKitdetectorMissing(Utils.getErrorMessage(e2));
        }
    }

    public static MLKitFaceHelper get() {
        if (instance == null) {
            instance = new MLKitFaceHelper();
        }
        return instance;
    }

    private Boolean isConfigNull() {
        return Boolean.valueOf(this.faceConfig == null || this.mView == null || this.faceDetectionListener == null);
    }

    public void setConfig(HVFaceConfig hVFaceConfig, FaceViewListener faceViewListener, FaceDetectionListener faceDetectionListener) {
        HVLogUtils.d(TAG, "setConfig() called with: faceConfig = [" + hVFaceConfig + "], faceViewListener = [" + faceViewListener + "], faceDetectionListener = [" + faceDetectionListener + "]");
        try {
            this.faceConfig = hVFaceConfig;
            this.mView = faceViewListener;
            this.faceDetectionListener = faceDetectionListener;
            FaceDetectorOptions fastFaceDetectorOptions = getFastFaceDetectorOptions();
            if (hVFaceConfig.shouldCheckForFaceTilt() || hVFaceConfig.isShouldCheckActiveLiveness()) {
                fastFaceDetectorOptions = getAccurateFaceDetectorOptions();
            }
            this.mlKitdetector = FaceDetection.getClient(fastFaceDetectorOptions);
        } catch (Exception e) {
            HVLogUtils.e(TAG, "setConfig(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            setMlKitdetectorUnavailable(Utils.getErrorMessage(e));
        } catch (NoClassDefFoundError e2) {
            HVLogUtils.e(TAG, "setConfig(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            setMlKitdetectorMissing(Utils.getErrorMessage(e2));
        }
    }

    public void setMlKitdetectorMissing(String str) {
        HVLogUtils.d(TAG, "setMlKitdetectorMissing() called with: message = [" + str + "]");
        AppConstants.mlkitMissing = str;
        SDKInternalConfig.getInstance().setMLKitDetectorMissing(true);
    }

    public void setMlKitdetectorUnavailable(String str) {
        HVLogUtils.d(TAG, "setMlKitdetectorUnavailable() called with: message = [" + str + "]");
        AppConstants.mlkitUnavailableError = str;
        SDKInternalConfig.getInstance().setMLKitUnavailable(true);
    }

    private FaceDetectorOptions getFastFaceDetectorOptions() {
        try {
            return new FaceDetectorOptions.Builder().setPerformanceMode(1).setLandmarkMode(1).setClassificationMode(1).setMinFaceSize(0.2f).build();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "getFastFaceDetectorOptions(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            setMlKitdetectorUnavailable(Utils.getErrorMessage(e));
            return null;
        } catch (NoClassDefFoundError e2) {
            HVLogUtils.e(TAG, "getFastFaceDetectorOptions(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            setMlKitdetectorMissing(Utils.getErrorMessage(e2));
            return null;
        }
    }

    private FaceDetectorOptions getAccurateFaceDetectorOptions() {
        HVLogUtils.d(TAG, "getAccurateFaceDetectorOptions() called");
        try {
            return new FaceDetectorOptions.Builder().setPerformanceMode(2).setLandmarkMode(2).setClassificationMode(1).setMinFaceSize(0.2f).build();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "getAccurateFaceDetectorOptions(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            setMlKitdetectorUnavailable(Utils.getErrorMessage(e));
            return null;
        } catch (NoClassDefFoundError e2) {
            HVLogUtils.e(TAG, "getAccurateFaceDetectorOptions(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            setMlKitdetectorMissing(Utils.getErrorMessage(e2));
            return null;
        }
    }

    public void destroy() {
        HVLogUtils.d(TAG, "destroy() called");
        try {
            FaceDetector faceDetector = this.mlKitdetector;
            if (faceDetector != null) {
                faceDetector.close();
                this.mlKitdetector = null;
                instance = null;
            }
        } catch (Exception | NoClassDefFoundError e) {
            HVLogUtils.e(TAG, "destroy(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public Face processImage(Bitmap bitmap) {
        try {
            ArrayList arrayList = new ArrayList((Collection) Tasks.await(this.mlKitdetector.process(InputImage.fromBitmap(bitmap, 0))));
            if (arrayList.isEmpty()) {
                return null;
            }
            return (Face) arrayList.get(0);
        } catch (Exception e) {
            HVLogUtils.e(TAG, "processImage(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            AppConstants.mlkitUnavailableError = Utils.getErrorMessage(e);
            SDKInternalConfig.getInstance().setMLKitUnavailable(true);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public void processFrame(CameraProperties cameraProperties) {
        if (this.isProcessing || isConfigNull().booleanValue()) {
            return;
        }
        this.isProcessing = true;
        this.cameraProperties = cameraProperties;
        try {
            ArrayList<Face> arrayList = new ArrayList<>((Collection<? extends Face>) Tasks.await(this.mlKitdetector.process(InputImage.fromByteArray(cameraProperties.getData(), 480, 360, 0, 17))));
            if (this.faceConfig.isShouldCheckActiveLiveness()) {
                HVActiveLiveness.get().onActiveLivenessFaceDetection(arrayList);
                this.isProcessing = false;
                return;
            }
            if (arrayList.isEmpty()) {
                this.isProcessing = false;
                this.stableFrameCounter = 0;
                this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
            } else {
                if (areMultipleFacesPresent(arrayList)) {
                    this.isProcessing = false;
                    this.stableFrameCounter = 0;
                    this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.MULTIPLE_FACES);
                    return;
                }
                checkFaceConditions(arrayList.get(0));
            }
        } catch (Exception | NoClassDefFoundError e) {
            HVLogUtils.e(TAG, "processFrame(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private boolean areMultipleFacesPresent(ArrayList<Face> arrayList) {
        HVLogUtils.d(TAG, "areMultipleFacesPresent() called with: faces = [" + arrayList + "]");
        return arrayList.size() > 1;
    }

    private boolean isFaceOccupyingSignificantPortionOfFrame(Face face) {
        return getFaceWidth(face) > (this.faceConfig.getShouldUseBackCamera() ? 0.35f : 0.3f) * ((float) UIUtils.getScreenWidth()) && getFaceWidth(face) < ((float) UIUtils.getScreenWidth()) * 0.6f;
    }

    private void checkFaceConditions(Face face) {
        if (getFaceWidth(face) > this.cameraProperties.getViewWidth() * 0.6f) {
            this.isProcessing = false;
            this.stableFrameCounter = 0;
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_TOO_CLOSE);
            return;
        }
        if (!isSignificantPortionOfFaceInsideFrame(face)) {
            this.isProcessing = false;
            this.stableFrameCounter = 0;
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
            return;
        }
        this.stableFrameCounter++;
        if (this.faceConfig.shouldCheckForFaceTilt()) {
            if (!isFaceStraight(face)) {
                this.isProcessing = false;
                this.stableFrameCounter = 0;
                this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_STRAIGHT);
                return;
            } else if (this.stableFrameCounter <= 5.0f) {
                this.isProcessing = false;
                this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_STAY_STILL);
                return;
            }
        }
        if (this.stableFrameCounter <= 5.0f) {
            this.isProcessing = false;
        } else {
            this.isProcessing = false;
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_DETECTED);
        }
    }

    public boolean isSignificantPortionOfFaceInsideFrame(Face face) {
        int viewWidth = this.cameraProperties.getViewWidth();
        int viewHeight = this.cameraProperties.getViewHeight();
        float faceWidth = getFaceWidth(face);
        float f = this.faceConfig.getShouldUseBackCamera() ? 0.35f : 0.3f;
        float translateY = translateY(face.getBoundingBox().exactCenterY(), viewHeight);
        float scaleY = scaleY(face.getBoundingBox().height() / 2, viewHeight);
        float f2 = translateY - scaleY;
        float f3 = viewWidth;
        boolean z = faceWidth > f * f3 && faceWidth < f3 * 0.6f && ((((double) Math.abs(this.mView.getViewYCenter() - (this.mView.getViewY() + (((translateY + scaleY) + f2) / 2.0f)))) > (((double) this.mView.getViewYCenter()) * 0.3d) ? 1 : (((double) Math.abs(this.mView.getViewYCenter() - (this.mView.getViewY() + (((translateY + scaleY) + f2) / 2.0f)))) == (((double) this.mView.getViewYCenter()) * 0.3d) ? 0 : -1)) < 0 && (f2 > 10.0f ? 1 : (f2 == 10.0f ? 0 : -1)) > 0);
        HVLogUtils.d(TAG, "isSignificantPortionOfFaceInsideFrame() returned: " + z);
        return z;
    }

    private boolean isFaceStraight(Face face) {
        HVLogUtils.d(TAG, "isFaceStraight() called with: face = [" + face + "]");
        float faceTiltAngle = (float) this.faceConfig.getFaceTiltAngle();
        boolean z = Math.abs(face.getHeadEulerAngleY()) <= faceTiltAngle && Math.abs(face.getHeadEulerAngleX()) <= faceTiltAngle && Math.abs(face.getHeadEulerAngleZ()) <= faceTiltAngle;
        HVLogUtils.d(TAG, "isFaceStraight() returned: " + z);
        return z;
    }

    private float getFaceWidth(Face face) {
        return (face.getBoundingBox().width() * this.cameraProperties.getViewWidth()) / 480;
    }

    private float scaleX(float f, int i) {
        float viewRadius = f * ((this.mView.getViewRadius() * 2) / i);
        HVLogUtils.d(TAG, "scaleX() returned: " + viewRadius);
        return viewRadius;
    }

    private float scaleY(float f, int i) {
        return f * ((this.mView.getViewRadius() * 2) / i);
    }

    private float translateX(float f, int i) {
        if (this.faceConfig.getShouldUseBackCamera()) {
            return scaleX(f, i);
        }
        return this.mView.getViewRadius() - scaleX(f, i);
    }

    private float translateY(float f, int i) {
        return scaleY(f, i);
    }
}
