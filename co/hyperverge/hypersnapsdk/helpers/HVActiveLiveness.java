package co.hyperverge.hypersnapsdk.helpers;

import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.helpers.face.MLKitFaceHelper;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract;
import co.hyperverge.hypersnapsdk.model.HVGesturePose;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.MainUIThread;
import com.google.mlkit.vision.face.Face;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* loaded from: classes2.dex */
public class HVActiveLiveness {
    public static int MIN_STABLE_COUNT = 3;
    private static final String TAG = "HVActiveLiveness";
    static HVActiveLiveness instance;
    HVFaceConfig config;
    int currentPosePosition;
    List<HVGesturePose> hvGesturePoses;
    boolean isAuthenticationDone;
    TextureContract.View mView;
    int stableCounter = 0;
    int correctAttempts = 0;
    boolean timeUp = false;
    private final HVGesturePose.FacePose[] _defaultPoses = {HVGesturePose.FacePose.RIGHT_GESTURE, HVGesturePose.FacePose.LEFT_GESTURE, HVGesturePose.FacePose.STRAIGHT_GESTURE};
    boolean isGestureRunning = false;
    boolean isWaiting = false;
    MainUIThread uiThread = MainUIThread.getInstance();
    boolean isFaceDetected = false;
    Map<String, List<String>> savedImagePaths = new HashMap();
    List<String> gestureFilePaths = new ArrayList();
    HVGesturePose.GestureState gestureState = HVGesturePose.GestureState.NEW_GESTURE;

    /* loaded from: classes2.dex */
    public interface FacePoseDetectionListener {
        void onFaceDetected(Face face);
    }

    private HVActiveLiveness() {
        this.currentPosePosition = 0;
        this.isAuthenticationDone = false;
        this.hvGesturePoses = new ArrayList();
        this.isAuthenticationDone = false;
        this.hvGesturePoses = new ArrayList();
        this.currentPosePosition = 0;
        loadNextGesture();
    }

    public static HVActiveLiveness get() {
        if (instance == null) {
            instance = new HVActiveLiveness();
        }
        return instance;
    }

    public void setConfig(TextureContract.View view, HVFaceConfig hVFaceConfig) {
        this.mView = view;
        this.config = hVFaceConfig;
    }

    public void loadNextGesture() {
        HVGesturePose.FacePose facePose = this._defaultPoses[new Random().nextInt(this._defaultPoses.length)];
        if (this.hvGesturePoses.size() > 0) {
            while (this.hvGesturePoses.get(this.currentPosePosition - 1).getFacePose() == facePose) {
                facePose = this._defaultPoses[new Random().nextInt(this._defaultPoses.length)];
            }
        }
        this.hvGesturePoses.add(this.currentPosePosition, new HVGesturePose(facePose));
        this.gestureState = HVGesturePose.GestureState.NEW_GESTURE;
    }

    public boolean isAuthenticationDone() {
        return this.isAuthenticationDone;
    }

    public boolean detectFaceFromImage(Face face) {
        boolean z;
        if (face != null) {
            HVGesturePose.FacePose facePose = this.hvGesturePoses.get(this.currentPosePosition).getFacePose();
            if (this.isAuthenticationDone) {
                facePose = HVGesturePose.FacePose.STRAIGHT_GESTURE;
            }
            z = checkIfPoseMatches(face, facePose);
        } else {
            z = false;
        }
        this.stableCounter = 0;
        if (!z) {
            if (!this.isAuthenticationDone) {
                if (this.timeUp) {
                    this.timeUp = false;
                    resetCounters();
                    this.mView.resetUI();
                } else {
                    this.gestureState = HVGesturePose.GestureState.VALIDATE_POSE;
                }
            } else {
                this.gestureState = HVGesturePose.GestureState.AUTHENTICATED_STATE;
            }
            this.isWaiting = false;
        } else {
            this.mView.resetPoseTimer();
        }
        return z;
    }

    public String getFileNameForPose(String str) {
        if (this.isAuthenticationDone) {
            return str;
        }
        return this.hvGesturePoses.get(this.currentPosePosition).getFacePose().toString() + "_" + this.correctAttempts + "_" + System.currentTimeMillis() + ".jpg";
    }

    public boolean saveImage(String str) {
        HVLogUtils.d(TAG, "saveImage() called with: path = [" + str + "]");
        if (this.gestureFilePaths.size() == this.config.getTotalGestures()) {
            return true;
        }
        HVGesturePose.FacePose facePose = this.hvGesturePoses.get(this.currentPosePosition).getFacePose();
        this.correctAttempts++;
        this.gestureFilePaths.add(str);
        List<String> arrayList = new ArrayList<>();
        if (this.savedImagePaths.containsKey(facePose.toString())) {
            arrayList = this.savedImagePaths.get(facePose.toString());
        }
        arrayList.add(str);
        this.savedImagePaths.put(facePose.toString(), arrayList);
        if (this.correctAttempts == this.config.getTotalGestures()) {
            handleAuthenticatedState();
        } else {
            this.currentPosePosition++;
            loadNextGesture();
            this.isWaiting = false;
        }
        return false;
    }

    public void handleAuthenticatedState() {
        HVLogUtils.d(TAG, "handleAuthenticatedState() called");
        this.isWaiting = true;
        this.isAuthenticationDone = true;
        this.gestureState = HVGesturePose.GestureState.AUTHENTICATED_STATE;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness.1
            @Override // java.lang.Runnable
            public void run() {
                HVActiveLiveness.this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HVActiveLiveness.this.mView.authenticationDone();
                    }
                });
            }
        }, 100L);
    }

    public void validatePose(Face face, HVGesturePose.FacePose facePose) {
        if (MLKitFaceHelper.get().isSignificantPortionOfFaceInsideFrame(face)) {
            if (checkIfPoseMatches(face, facePose)) {
                this.stableCounter++;
                this.mView.stablePose();
                if (this.stableCounter >= MIN_STABLE_COUNT) {
                    this.gestureState = HVGesturePose.GestureState.POSE_MATCHES;
                    return;
                }
                return;
            }
            this.gestureState = HVGesturePose.GestureState.POSE_DOES_NOT_MATCH;
            return;
        }
        this.mView.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
        this.isFaceDetected = false;
    }

    public void waitForUI(boolean z) {
        this.isWaiting = z;
    }

    public Spanned getPoseText(HVGesturePose.FacePose facePose) {
        int i = AnonymousClass5.$SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$FacePose[facePose.ordinal()];
        if (i == 1) {
            return TextConfigUtils.getText(this.config.getCustomUIStrings(), CustomTextStringConst.FaceCaptureTextConfigs.FACE_GESTURE_LOOK_STRAIGHT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_GESTURE_LOOK_STRAIGHT, "Look Straight");
        }
        if (i == 2) {
            return TextConfigUtils.getText(this.config.getCustomUIStrings(), CustomTextStringConst.FaceCaptureTextConfigs.FACE_GESTURE_LOOK_LEFT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_GESTURE_LOOK_LEFT, "Look Left");
        }
        if (i != 3) {
            return null;
        }
        return TextConfigUtils.getText(this.config.getCustomUIStrings(), CustomTextStringConst.FaceCaptureTextConfigs.FACE_GESTURE_LOOK_RIGHT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_GESTURE_LOOK_RIGHT, "Look Right");
    }

    public void changePose(final HVGesturePose.FacePose facePose) {
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness.2
            @Override // java.lang.Runnable
            public void run() {
                HVActiveLiveness.this.mView.onNewPose(HVActiveLiveness.this.getPoseText(facePose));
            }
        });
    }

    public void resetTimer() {
        HVLogUtils.d(TAG, "resetTimer() called");
        this.timeUp = true;
        if (this.gestureState != HVGesturePose.GestureState.CAPTURE_IN_PROGRESS) {
            this.gestureState = HVGesturePose.GestureState.TIME_UP;
        }
    }

    public void resetCounters() {
        HVLogUtils.d(TAG, "resetCounters() called");
        if (!this.isAuthenticationDone) {
            this.hvGesturePoses.clear();
            this.currentPosePosition = 0;
            this.correctAttempts = 0;
            this.timeUp = false;
            this.savedImagePaths.clear();
            this.gestureFilePaths.clear();
            loadNextGesture();
            this.stableCounter = 0;
            this.isGestureRunning = false;
        }
        this.isWaiting = false;
    }

    public void destroy() {
        HVLogUtils.d(TAG, "destroy() called");
        try {
            instance = null;
        } catch (Exception | NoClassDefFoundError e) {
            HVLogUtils.e(TAG, "destroy(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public boolean isFaceDetected() {
        return this.isFaceDetected;
    }

    public void poseMatches() {
        this.gestureState = HVGesturePose.GestureState.CAPTURE_IN_PROGRESS;
        this.isWaiting = true;
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness.3
            @Override // java.lang.Runnable
            public void run() {
                HVActiveLiveness.this.mView.poseMatches(HVActiveLiveness.this.correctAttempts);
            }
        });
    }

    public void poseDoesNotMatch(final boolean z) {
        this.stableCounter = 0;
        this.isWaiting = true;
        this.gestureState = HVGesturePose.GestureState.VALIDATE_POSE;
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness.4
            @Override // java.lang.Runnable
            public void run() {
                HVActiveLiveness.this.mView.poseDoesNotMatch(z);
            }
        });
    }

    public boolean checkIfPoseMatches(Face face, HVGesturePose.FacePose facePose) {
        HVLogUtils.d(TAG, "checkIfPoseMatches() called with: face = [" + face + "], facePose = [" + facePose + "]");
        int i = AnonymousClass5.$SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$FacePose[facePose.ordinal()];
        return i != 1 ? i != 2 ? i == 3 && face.getHeadEulerAngleY() < -5.0f : face.getHeadEulerAngleY() > 5.0f : Math.abs(face.getHeadEulerAngleY()) <= 15.0f && Math.abs(face.getHeadEulerAngleX()) <= 15.0f && Math.abs(face.getHeadEulerAngleZ()) <= 15.0f;
    }

    public Map<String, List<String>> getSavedImagePaths() {
        HVLogUtils.d(TAG, "getSavedImagePaths() called");
        return this.savedImagePaths;
    }

    public void onActiveLivenessFaceDetection(ArrayList<Face> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (this.isWaiting) {
                return;
            }
            if (!this.isFaceDetected) {
                this.mView.facePresentForActiveLiveness();
            }
            this.isFaceDetected = true;
            int i = AnonymousClass5.$SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[this.gestureState.ordinal()];
            if (i == 1) {
                changePose(this.hvGesturePoses.get(this.currentPosePosition).getFacePose());
                this.gestureState = HVGesturePose.GestureState.VALIDATE_POSE;
                return;
            }
            if (i == 2) {
                validatePose(arrayList.get(0), this.hvGesturePoses.get(this.currentPosePosition).getFacePose());
                return;
            }
            if (i == 3) {
                poseMatches();
                return;
            }
            if (i == 4) {
                this.timeUp = true;
                poseMatches();
                return;
            } else if (i == 5) {
                poseDoesNotMatch(false);
                return;
            } else {
                if (i != 7) {
                    return;
                }
                handleAuthenticatedState();
                return;
            }
        }
        if (this.isFaceDetected) {
            return;
        }
        this.mView.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
        resetCounters();
        this.isFaceDetected = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness$5, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$FacePose;
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState;

        static {
            int[] iArr = new int[HVGesturePose.GestureState.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState = iArr;
            try {
                iArr[HVGesturePose.GestureState.NEW_GESTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[HVGesturePose.GestureState.VALIDATE_POSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[HVGesturePose.GestureState.POSE_MATCHES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[HVGesturePose.GestureState.TIME_UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[HVGesturePose.GestureState.POSE_DOES_NOT_MATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[HVGesturePose.GestureState.CAPTURE_IN_PROGRESS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$GestureState[HVGesturePose.GestureState.AUTHENTICATED_STATE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[HVGesturePose.FacePose.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$FacePose = iArr2;
            try {
                iArr2[HVGesturePose.FacePose.STRAIGHT_GESTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$FacePose[HVGesturePose.FacePose.LEFT_GESTURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$model$HVGesturePose$FacePose[HVGesturePose.FacePose.RIGHT_GESTURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }
}
