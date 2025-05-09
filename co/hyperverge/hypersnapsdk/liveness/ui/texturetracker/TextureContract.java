package co.hyperverge.hypersnapsdk.liveness.ui.texturetracker;

import android.content.Context;
import android.text.Spanned;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.utils.BasePresenter;
import co.hyperverge.hypersnapsdk.utils.BaseView;
import java.util.List;

/* loaded from: classes2.dex */
public interface TextureContract {

    /* loaded from: classes2.dex */
    public interface Presenter extends BasePresenter {
        Boolean canResumeCamera();

        void closeOnLowStorage();

        void faceCaptureError(HVError hVError);

        void faceCaptureTimeout();

        FaceConstants.FaceDetectionState getFaceDetectionState();

        boolean isFaceDetectedOnce();

        boolean isFacePresent();

        void operationCancelled();

        void resetCounters();

        void resetTimer();

        void resume();

        void savePicture(byte[] bArr, byte[] bArr2, String str, String str2, String str3);

        void setFaceDetectionStateFromView(FaceConstants.FaceDetectionState faceDetectionState);

        void setFaceDetectorTimedOut();

        void showDialog(boolean z, String str);

        void submitFrameForProcessing(CameraProperties cameraProperties);

        void waitForUI(boolean z);
    }

    /* loaded from: classes2.dex */
    public interface View extends BaseView<Presenter> {
        void authenticationDone();

        void callCompletionHandler(HVError hVError, HVResponse hVResponse);

        void disableCaptureButton();

        void enableCaptureButton();

        void facePresentForActiveLiveness();

        void finishTextureView(int i);

        String getRetakeMessageForFaceNotPresentInCapturedImage();

        String getStringForID(int i);

        long getVideoRecordedTime();

        FaceViewListener getViewListener();

        void hideFaceBox();

        Context injectContext();

        boolean isFragmentAdded();

        boolean isPhoneHeldStraight();

        void onNewPose(Spanned spanned);

        void onPictureSaved(String str);

        void pauseCamera();

        void poseDoesNotMatch(boolean z);

        void poseMatches(int i);

        void resetLoader();

        void resetPoseTimer();

        void resetUI();

        void resumeCamera();

        void setFaceDetectionState(FaceConstants.FaceDetectionState faceDetectionState);

        void setShouldAllowActivityClose(boolean z);

        void shouldShowProgress(boolean z, String str);

        void showFaceBox(List<Integer> list);

        void showLoader(boolean z, String str, String str2, HVLottieHelper.State state, HVLottieHelper.Listener listener);

        void showMessage(String str);

        void stablePose();

        void stopCamera();
    }
}
