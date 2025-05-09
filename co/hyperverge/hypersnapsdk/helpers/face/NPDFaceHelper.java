package co.hyperverge.hypersnapsdk.helpers.face;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.CamPreviewFaceDetectionHandler;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.model.FaceDetectorObj;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NPDFaceHelper implements CamPreviewFaceDetectionHandler.NPDFaceListener {
    private static final String TAG = "NPDFaceHelper";
    private static NPDFaceHelper instance;
    private CameraProperties cameraProperties;
    private HVFaceConfig faceConfig;
    private final CamPreviewFaceDetectionHandler faceDetectionHandler;
    private FaceDetectionListener faceDetectionListener;
    private FaceViewListener mView;
    private int stableFrameCounter = 0;

    private NPDFaceHelper() {
        CamPreviewFaceDetectionHandler camPreviewFaceDetectionHandler = CamPreviewFaceDetectionHandler.get();
        this.faceDetectionHandler = camPreviewFaceDetectionHandler;
        camPreviewFaceDetectionHandler.setListener(this);
    }

    public static NPDFaceHelper get() {
        if (instance == null) {
            instance = new NPDFaceHelper();
        }
        return instance;
    }

    public void setConfig(HVFaceConfig hVFaceConfig, FaceViewListener faceViewListener, FaceDetectionListener faceDetectionListener) {
        HVLogUtils.d(TAG, "setConfig() called with: faceConfig = [" + hVFaceConfig + "], faceViewListener = [" + faceViewListener + "], faceDetectionListener = [" + faceDetectionListener + "]");
        this.faceConfig = hVFaceConfig;
        this.mView = faceViewListener;
        this.faceDetectionListener = faceDetectionListener;
    }

    private Boolean isConfigNull() {
        return Boolean.valueOf(this.faceConfig == null || this.mView == null || this.faceDetectionListener == null);
    }

    public void destroy() {
        HVLogUtils.d(TAG, "destroy() called");
        try {
            this.faceDetectionHandler.reset();
            this.faceDetectionListener = null;
            instance = null;
        } catch (Exception e) {
            HVLogUtils.e(TAG, "destroy(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            StringBuilder sb = new StringBuilder();
            sb.append("destroy: exception - ");
            sb.append(Utils.getErrorMessage(e));
            Log.e(TAG, sb.toString());
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void processFrame(CameraProperties cameraProperties) {
        this.cameraProperties = cameraProperties;
        this.faceDetectionHandler.submit(cameraProperties);
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.CamPreviewFaceDetectionHandler.NPDFaceListener
    public void onFaceDetected(FaceDetectorObj faceDetectorObj) {
        if (isConfigNull().booleanValue()) {
            return;
        }
        if (areMultipleFacesPresent(faceDetectorObj.getMultipleFaces())) {
            this.stableFrameCounter = 0;
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.MULTIPLE_FACES);
        } else if (faceDetectorObj.getRectPoints() == null || faceDetectorObj.getRectPoints().isEmpty()) {
            this.stableFrameCounter = 0;
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
        } else {
            checkFaceConditions(faceDetectorObj.getRectPoints());
        }
    }

    private void checkFaceConditions(List<Integer> list) {
        HVLogUtils.d(TAG, "checkFaceConditions() called with: faceCoordinates = [" + list + "]");
        if (list.get(2).intValue() - list.get(0).intValue() > UIUtils.getScreenWidth() * 0.6f) {
            this.stableFrameCounter = 0;
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_TOO_CLOSE);
        } else {
            if (!isSignificantPortionOfFaceInsideFrame(list)) {
                this.stableFrameCounter = 0;
                this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
                return;
            }
            int i = this.stableFrameCounter + 1;
            this.stableFrameCounter = i;
            if (i <= 5.0f) {
                return;
            }
            this.faceDetectionListener.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_DETECTED);
        }
    }

    private boolean isSignificantPortionOfFaceInsideFrame(List<Integer> list) {
        if (isConfigNull().booleanValue()) {
            return false;
        }
        try {
            long intValue = list.get(2).intValue() - list.get(0).intValue();
            long intValue2 = list.get(1).intValue() + ((list.get(3).intValue() - list.get(1).intValue()) / 2);
            float f = this.faceConfig.getShouldUseBackCamera() ? 0.35f : 0.3f;
            boolean z = ((double) Math.abs(this.mView.getViewYCenter() - (((float) intValue2) + this.mView.getViewY()))) < ((double) this.mView.getViewYCenter()) * 0.3d;
            float f2 = (float) intValue;
            if (f2 > f * UIUtils.getScreenWidth()) {
                return f2 < ((float) UIUtils.getScreenWidth()) * 0.6f && z;
            }
            return false;
        } catch (Exception e) {
            HVLogUtils.e(TAG, "isSignificantPortionOfFaceInsideFrame(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return false;
        }
    }

    private boolean isFaceOccupyingSignificantPortionOfFrame(List<Integer> list) {
        boolean z = false;
        if (r0 > (this.faceConfig.getShouldUseBackCamera() ? 0.35f : 0.3f) * UIUtils.getScreenWidth() && r0 < UIUtils.getScreenWidth() * 0.6f) {
            z = true;
        }
        HVLogUtils.d(TAG, "isFaceOccupyingSignificantPortionOfFrame() returned: " + z);
        return z;
    }

    private boolean areMultipleFacesPresent(List<ArrayList<Integer>> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<ArrayList<Integer>> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (isFaceOccupyingSignificantPortionOfFrame(it.next())) {
                i++;
            }
        }
        return i > 0;
    }
}
