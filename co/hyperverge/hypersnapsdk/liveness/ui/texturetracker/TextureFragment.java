package co.hyperverge.hypersnapsdk.liveness.ui.texturetracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.media3.exoplayer.ExoPlayer;
import co.hyperverge.encoder.HyperVideoListener;
import co.hyperverge.encoder.HyperVideoLowStorageExceptionListener;
import co.hyperverge.encoder.HyperVideoRecorder;
import co.hyperverge.hvcamera.HVCamHost;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.activities.HVFaceActivity;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.HVCameraHelper;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.helpers.ImageComparisonHelper;
import co.hyperverge.hypersnapsdk.helpers.LanguageHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.service.sensorbiometrics.PhoneTiltDetectorService;
import co.hyperverge.hypersnapsdk.service.sensorbiometrics.PhoneTiltListener;
import co.hyperverge.hypersnapsdk.service.sensorbiometrics.SensorData;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.MainUIThread;
import co.hyperverge.hypersnapsdk.views.CircularProgressBar;
import co.hyperverge.hypersnapsdk.views.CrossHairView;
import co.hyperverge.hypersnapsdk.views.FaceBoxView;
import co.hyperverge.hypersnapsdk.views.TextureBlackOverlay;
import co.hyperverge.hypersnapsdk.views.TextureCamera;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.imageview.ShapeableImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class TextureFragment extends Fragment implements TextureContract.View, FaceViewListener {
    private static final String TAG = "TextureFragment";
    private TextView alertTextView;
    private CountDownTimer autoCaptureTimer;
    private TextureBlackOverlay blackOverlay;
    private int camViewHeight;
    private int camViewWidth;
    ImageView cameraIcon;
    HVMagicView cameraView;
    private AlertDialog captureAlertDialog;
    private CountDownTimer captureTimer;
    HVFaceConfig config;
    CrossHairView crossHairView;
    HVJSONObject customStrings;
    Handler delayhandler;
    ProgressDialog dialog;
    FaceBoxView faceBoxView;
    private CountDownTimer faceDetectorTimer;
    private ConstraintLayout faceLoaderLayout;
    ImageView flip;
    private CountDownTimer gesturePoseTimer;
    private HVError hvImageCaptureError;
    private HVError hvVideoRecordError;
    private HyperVideoRecorder hyperVideoRecorder;
    private boolean isPhoneHeldStraight;
    ImageView ivBack;
    private float lastTouchX;
    private float lastTouchY;
    private LottieAnimationView lav;
    TextureContract.Presenter mPresenter;
    ShapeableImageView overlayImageView;
    FrameLayout parentContainer;
    private PhoneTiltDetectorService phoneTiltDetectorService;
    TextureCamera previewContainer;
    private byte[] previousFrame;
    int previousValue;
    CircularProgressBar progressBar;
    private TextView progressDialogTextView;
    private View progressDialogView;
    private ImageView progressSpinnerImageView;
    ProgressDialog rotateDialog;
    TextView statusString;
    private long timeTakenToVideoRecord;
    TextView tvTitle;
    private String videoUri;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final TimingUtils imageCaptureTimingUtils = new TimingUtils();
    private final TimingUtils captureClickTimingUtils = new TimingUtils();
    private final int n = 50;
    private final int m = 50;
    private final String MOVE_CLOSER_TAG = "moveCloser";
    private final String FACE_CAPTURE_MULTIPLE_FACES_TAG = CustomTextStringConst.FaceCaptureTextConfigs.FACE_MULTIPLE_FACES;
    private final String FACE_CAPTURE_ACTIVITY_TAG = "faceCaptureActivity";
    private final String FACE_CAPTURE_FACE_FOUND_TAG = CustomTextStringConst.FaceCaptureTextConfigs.FACE_FOUND;
    private final String FACE_CAPTURE_FACE_NOT_FOUND_TAG = CustomTextStringConst.FaceCaptureTextConfigs.FACE_NOT_FOUND;
    private final String FACE_CAPTURE_MOVE_AWAY_TAG = CustomTextStringConst.FaceCaptureTextConfigs.FACE_MOVE_AWAY;
    private final String FACE_CAPTURE_LOOK_STRAIGHT_TAG = CustomTextStringConst.FaceCaptureTextConfigs.FACE_LOOK_STRAIGHT;
    private final String FACE_CAPTURE_AUTO_CAPTURE_WAIT = CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_AUTO_CAPTURE_WAIT;
    private final String FACE_CAPTURE_AUTO_CAPTURE_ACTION = CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_AUTO_CAPTURE_ACTION;
    private final String FACE_CAPTURE_PHONE_STRAIGHT_TAG = "faceCapturePhoneStraightTag";
    private final String FACE_CAPTURE_STAY_STILL_TAG = "faceCaptureStayStill";
    private final String FACE_CAPTURE_FACE_NOT_PRESENT_IN_CAPTURED_IMAGE = CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_FACE_NOT_PRESENT_IN_CAPTURED_IMAGE;
    boolean safeToTakePicture = true;
    private boolean isTakePictureInProgress = false;
    private boolean isPaused = false;
    private final TimingUtils videoRecordTimingUtils = new TimingUtils();
    private boolean isVideoRecordingProcessComplete = false;
    private boolean isVideoRecorded = false;
    private boolean isFrontCam = true;
    private boolean isProgressBarAnimating = false;
    private boolean isCameraOpened = false;
    private boolean isCaptureTimerFinished = false;
    private long timeUntilFinished = 0;
    private boolean isCameraInitialized = false;
    private boolean isCheckingCamera = false;
    View.OnTouchListener touchCameraIconListener = new View.OnTouchListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!TextureFragment.this.safeToTakePicture) {
                return false;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                popin();
                return false;
            }
            if (action != 1) {
                return false;
            }
            popout();
            return false;
        }

        private void popin() {
            ImageView imageView = TextureFragment.this.cameraIcon;
            TextureFragment.this.cameraIcon.clearAnimation();
            imageView.clearAnimation();
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(150L);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            scaleAnimation.setFillAfter(true);
            imageView.startAnimation(scaleAnimation);
            TextureFragment.this.cameraIcon.startAnimation(scaleAnimation);
        }

        private void popout() {
            ImageView imageView = TextureFragment.this.cameraIcon;
            imageView.clearAnimation();
            TextureFragment.this.cameraIcon.clearAnimation();
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(150L);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.1.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    TextureFragment.this.capturePicture();
                }
            });
            TextureFragment.this.cameraIcon.startAnimation(scaleAnimation);
            imageView.startAnimation(scaleAnimation);
        }
    };
    private final HVCamHost camHost = new AnonymousClass2();
    Runnable poseRunnable = new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.11
        @Override // java.lang.Runnable
        public void run() {
            TextureFragment.this.capturePicture();
        }
    };

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public FaceViewListener getViewListener() {
        return this;
    }

    public static TextureFragment getInstance() {
        return new TextureFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HVFaceConfig getFaceConfig() {
        HVFaceConfig hVFaceConfig = this.config;
        if (hVFaceConfig != null) {
            return hVFaceConfig;
        }
        sendResponse(new HVError(2, getResources().getString(R.string.face_config_error)));
        return new HVFaceConfig();
    }

    public void setConfig(HVFaceConfig hVFaceConfig) {
        this.config = hVFaceConfig;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        HVLogUtils.d(TAG, "onStart() called");
        if (getFaceConfig().isShouldRecordVideo()) {
            File createMediaVideoFile = createMediaVideoFile();
            if (createMediaVideoFile == null) {
                HVError hVError = new HVError(2, "Could not create media video file");
                this.hvVideoRecordError = hVError;
                Log.d(TAG, hVError.getErrorMessage());
                long timeDifferenceAndUpdateLong = this.videoRecordTimingUtils.getTimeDifferenceAndUpdateLong();
                if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                    return;
                }
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieVideoRecordFailed(this.hvVideoRecordError, timeDifferenceAndUpdateLong);
                return;
            }
            HyperVideoRecorder companion = HyperVideoRecorder.INSTANCE.getInstance();
            this.hyperVideoRecorder = companion;
            companion.start(requireContext(), requireActivity().getWindow(), createMediaVideoFile, new HyperVideoLowStorageExceptionListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda9
                @Override // co.hyperverge.encoder.HyperVideoLowStorageExceptionListener
                public final void invoke() {
                    TextureFragment.this.m524x17383baa();
                }
            });
        }
    }

    /* renamed from: lambda$onStart$0$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m524x17383baa() {
        this.mPresenter.closeOnLowStorage();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        HVLogUtils.d(TAG, "onCreateView() called with: inflater = [" + layoutInflater + "], container = [" + viewGroup + "], savedInstanceState = [" + bundle + "]");
        this.captureClickTimingUtils.init();
        View inflate = layoutInflater.inflate(R.layout.hv_fragment_texture_view, viewGroup, false);
        this.delayhandler = new Handler();
        initView(inflate);
        try {
            if (SDKInternalConfig.getInstance().isFaceDetectionOn()) {
                this.cameraIcon.setImageResource(R.drawable.hv_camera_button_disabled);
            } else {
                this.cameraIcon.setImageResource(R.drawable.ic_camera_button_svg);
            }
            setupBranding(inflate);
            this.safeToTakePicture = true;
            TextureContract.Presenter presenter = this.mPresenter;
            if (presenter != null) {
                presenter.start();
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return inflate;
    }

    private void obtainVideo() {
        HVLogUtils.d(TAG, "obtainVideo() called");
        HyperVideoRecorder hyperVideoRecorder = this.hyperVideoRecorder;
        if (hyperVideoRecorder != null) {
            hyperVideoRecorder.stop(new HyperVideoListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda7
                @Override // co.hyperverge.encoder.HyperVideoListener
                public final void invoke(File file) {
                    TextureFragment.this.m522x6c04d37e(file);
                }
            });
        }
    }

    /* renamed from: lambda$obtainVideo$1$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m522x6c04d37e(File file) {
        this.isVideoRecordingProcessComplete = true;
        if (file == null) {
            this.isVideoRecorded = false;
            HVError hVError = new HVError(2, "Video encoding unsuccessful - file is null");
            this.hvVideoRecordError = hVError;
            Log.d(TAG, hVError.getErrorMessage());
            long timeDifferenceAndUpdateLong = this.videoRecordTimingUtils.getTimeDifferenceAndUpdateLong();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieVideoRecordFailed(this.hvVideoRecordError, timeDifferenceAndUpdateLong);
            return;
        }
        this.isVideoRecorded = true;
        this.videoUri = Uri.parse(file.toString()).toString();
    }

    public void sendResponse(HVError hVError) {
        HVLogUtils.d(TAG, "sendResponse() called with: error = [" + hVError + "]");
        if (CallbackProvider.get().injectFaceCaptureCallback() != null) {
            if (this.isCheckingCamera) {
                HVLogUtils.d(TAG, "checkCameraAndHandleCompletion: already checking");
                return;
            }
            if (CameraEngine.isCameraReleased()) {
                this.isCheckingCamera = true;
                try {
                    try {
                        stopCamera();
                    } catch (Exception e) {
                        Log.e(TAG, Utils.getErrorMessage(e));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                        }
                    }
                    callCompletionHandler(hVError, null);
                    finishTextureView(-1);
                } catch (Exception e2) {
                    Log.e(TAG, Utils.getErrorMessage(e2));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends HVCamHost {
        @Override // co.hyperverge.hvcamera.HVCamHost
        public void flashScreen() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public int getAspectRatio() {
            return 1;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void getCurrentVideoLength(long j) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public float getPictureMegapixels() {
            return 1.3f;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public float getPreviewMegapixels() {
            return 0.3f;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public boolean isShouldCaptureHighResolutionImage() {
            return false;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onCamerasFound(int i) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFaceDetection(Camera.Face[] faceArr) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFilterMode(int i, String str) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashAuto() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashNull() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashOn() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashTorchOn() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onLayoutChange() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onVideoSaved(File file) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void setScreenFlashOff() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void setScreenFlashOn() {
        }

        AnonymousClass2() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureReady(byte[] bArr) {
            Bitmap selfieImageBitmap;
            HVLogUtils.d(TextureFragment.TAG, "onPictureReady() called with: bytes = [" + bArr + "]");
            if (InternalToolUtils.isTestMode(TextureFragment.this.getContext()) && (selfieImageBitmap = InternalToolUtils.getSelfieImageBitmap()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                selfieImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
            }
            final byte[] bArr2 = bArr;
            onPictureTaken();
            TextureFragment.this.pauseCamera();
            TextureFragment.this.mPresenter.setFaceDetectionStateFromView(FaceConstants.FaceDetectionState.SAVE_UPLOAD);
            if (TextureFragment.this.getFaceConfig().isShouldRecordVideo()) {
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!TextureFragment.this.isVideoRecordingProcessComplete) {
                            handler.postDelayed(this, 100L);
                            Log.d(TextureFragment.TAG, "waiting for videoUri");
                            return;
                        }
                        if (TextureFragment.this.mPresenter != null) {
                            TextureFragment.this.mPresenter.showDialog(false, null);
                        }
                        if (TextureFragment.this.isVideoRecorded) {
                            TextureFragment.this.timeTakenToVideoRecord = TextureFragment.this.videoRecordTimingUtils.getTimeDifferenceAndUpdateLong();
                        }
                        File photoDirectory = AnonymousClass2.this.getPhotoDirectory();
                        if (TextureFragment.this.mPresenter != null && photoDirectory != null) {
                            TextureFragment.this.mPresenter.savePicture(bArr2, TextureFragment.this.previousFrame, photoDirectory.getAbsolutePath(), AnonymousClass2.this.getPhotoFilename(), TextureFragment.this.videoUri);
                        }
                        TextureFragment.this.safeToTakePicture = true;
                        handler.removeCallbacks(this);
                    }
                }, 100L);
                return;
            }
            File photoDirectory = getPhotoDirectory();
            if (TextureFragment.this.mPresenter != null && photoDirectory != null) {
                TextureFragment.this.mPresenter.savePicture(bArr2, TextureFragment.this.previousFrame, photoDirectory.getAbsolutePath(), getPhotoFilename(), emptyVideoUri());
            }
            TextureFragment.this.safeToTakePicture = true;
        }

        private String emptyVideoUri() {
            HVLogUtils.d(TextureFragment.TAG, "emptyVideoUri() called");
            File file = new File(TextureFragment.this.camHost.getPhotoDirectory() + File.separator + "hv_dummy_video.mp4");
            try {
                file.createNewFile();
            } catch (IOException e) {
                HVLogUtils.e(TextureFragment.TAG, "emptyVideoUri(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TextureFragment.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            return file.getAbsolutePath();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureFailed() {
            HVLogUtils.d(TextureFragment.TAG, "onPictureFailed() called");
            TextureFragment.this.safeToTakePicture = true;
            TextureFragment.this.hvImageCaptureError = new HVError(2, "failure logged in selfie onPictureFailed()");
            long longValue = TextureFragment.this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureFailed(TextureFragment.this.hvImageCaptureError, TextureFragment.this.getFaceConfig(), longValue);
            }
            TextureFragment.this.mPresenter.setFaceDetectionStateFromView(SDKInternalConfig.getInstance().isFaceDetectionOn() ? FaceConstants.FaceDetectionState.FACE_NOT_DETECTED : FaceConstants.FaceDetectionState.FACE_DETECTED);
            TextureFragment.this.isTakePictureInProgress = false;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public File getPhotoDirectory() {
            File file;
            Exception e;
            HVLogUtils.d(TextureFragment.TAG, "getPhotoDirectory() called");
            try {
                file = new File(TextureFragment.this.requireActivity().getFilesDir(), "hv");
            } catch (Exception e2) {
                file = null;
                e = e2;
            }
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e3) {
                e = e3;
                HVLogUtils.e(TextureFragment.TAG, "getPhotoDirectory(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                StringBuilder sb = new StringBuilder();
                sb.append("getPhotoDirectory exception: ");
                sb.append(Utils.getErrorMessage(e));
                Log.e(TextureFragment.TAG, sb.toString());
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
                return file;
            }
            return file;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public String getPhotoFilename() {
            return "FD_" + System.currentTimeMillis() + ".jpg";
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public String getVideoFilename() {
            return "FD_" + System.currentTimeMillis() + ".mp4";
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureTaken() {
            HVLogUtils.d(TextureFragment.TAG, "onPictureTaken() called");
            long longValue = TextureFragment.this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureSuccessful(TextureFragment.this.getFaceConfig(), longValue);
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureSaved(File file) {
            HVLogUtils.d(TextureFragment.TAG, "onPictureSaved() called with: file = [" + file + "]");
            long longValue = TextureFragment.this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureSaved(TextureFragment.this.getFaceConfig(), file.getAbsolutePath(), longValue);
            }
            TextureFragment.this.isTakePictureInProgress = false;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashOff() {
            if (!TextureFragment.this.getFaceConfig().isUseFlash() || TextureFragment.this.cameraView == null) {
                return;
            }
            TextureFragment.this.cameraView.nextFlashMode();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onReady() {
            if (!TextureFragment.this.isCameraInitialized) {
                MainUIThread.getInstance().post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextureFragment.AnonymousClass2.this.m530x10fdadb9();
                    }
                });
            }
            try {
                if (TextureFragment.this.cameraView != null) {
                    TextureFragment.this.cameraView.startAccelerometer();
                }
            } catch (Exception e) {
                HVLogUtils.e(TextureFragment.TAG, "onReady(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TextureFragment.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }

        /* renamed from: lambda$onReady$0$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment$2, reason: not valid java name */
        public /* synthetic */ void m530x10fdadb9() {
            TextureFragment.this.shouldShowProgress(false, null);
            TextureFragment.this.progressDialogTextView.setVisibility(0);
            TextureFragment.this.isCameraInitialized = true;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void showCrossHair(final float f, final float f2, final boolean z) {
            if (TextureFragment.this.crossHairView != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$2$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextureFragment.AnonymousClass2.this.m531x7e9387fb(f, f2, z);
                    }
                });
            }
        }

        /* renamed from: lambda$showCrossHair$1$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment$2, reason: not valid java name */
        public /* synthetic */ void m531x7e9387fb(float f, float f2, boolean z) {
            if (f > 0.0f || f2 > 0.0f) {
                TextureFragment.this.crossHairView.showCrosshair(f * TextureFragment.this.camViewWidth, f2 * TextureFragment.this.camViewHeight, z);
            } else {
                TextureFragment.this.crossHairView.showCrosshair(TextureFragment.this.camViewWidth / 2, TextureFragment.this.camViewHeight / 2, z);
            }
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onNewPreviewFrame(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2) {
            if ((TextureFragment.this.isTakePictureInProgress && TextureFragment.this.getFaceConfig().isShouldUseDefaultZoom()) || TextureFragment.this.isPaused) {
                return;
            }
            CameraProperties cameraProperties = new CameraProperties(i, i2, UIUtils.getScreenWidth(), TextureFragment.this.previewContainer.getDiameter() + UIUtils.dpToPx(TextureFragment.this.requireActivity(), 80.0f), bArr2.length, i3, i4, bArr, !TextureFragment.this.getFaceConfig().getShouldUseBackCamera(), false);
            if (TextureFragment.this.mPresenter != null) {
                TextureFragment.this.mPresenter.submitFrameForProcessing(cameraProperties);
            }
            TextureFragment.this.previousFrame = bArr2;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onViewDimensionChange(int i, int i2) {
            HVLogUtils.d(TextureFragment.TAG, "onViewDimensionChange() called with: width = [" + i + "], height = [" + i2 + "]");
            TextureFragment.this.camViewHeight = i2;
            TextureFragment.this.camViewWidth = i;
            TextureFragment.this.adjustCrossHairView();
            TextureFragment.this.adjustOverlayImageView();
            TextureFragment.this.adjustBlackOverlayView();
            TextureFragment.this.adjustProgressDialogView();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onCameraFlipCallback() {
            HVLogUtils.d(TextureFragment.TAG, "onCameraFlipCallback() called");
            if (TextureFragment.this.rotateDialog != null && TextureFragment.this.rotateDialog.isShowing() && Utils.canDismissLoader(TextureFragment.this.getActivity())) {
                TextureFragment.this.rotateDialog.dismiss();
                TextureFragment.this.rotateDialog = null;
            }
            TextureFragment.this.isFrontCam = !r0.isFrontCam;
            MainUIThread.getInstance().post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TextureFragment.AnonymousClass2.this.m529x4c80ce27();
                }
            });
        }

        /* renamed from: lambda$onCameraFlipCallback$2$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment$2, reason: not valid java name */
        public /* synthetic */ void m529x4c80ce27() {
            if (TextureFragment.this.crossHairView != null) {
                TextureFragment.this.crossHairView.setVisibility(TextureFragment.this.getFaceConfig().getShouldUseBackCamera() ? 0 : 8);
                TextureFragment.this.getFaceConfig().isShouldUseZoom();
            }
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void zoomMaxLevel(int i) {
            HVLogUtils.d(TextureFragment.TAG, "zoomMaxLevel() called with: maxZoom = [" + i + "]");
            if (TextureFragment.this.getFaceConfig().isShouldUseDefaultZoom()) {
                if (TextureFragment.this.getFaceConfig().getShouldUseBackCamera()) {
                    CameraEngine.setDefaultZoom(AppConstants.ZOOM_FACTOR_BACK_CAM);
                    return;
                } else {
                    CameraEngine.setDefaultZoom(AppConstants.ZOOM_FACTOR_FRONT_CAM);
                    return;
                }
            }
            CameraEngine.setShouldUseDefaultZoom(false);
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureSizeSet(int i, int i2) {
            HVLogUtils.d(TextureFragment.TAG, "onPictureSizeSet() called with: width = [" + i + "], height = [" + i2 + "]");
            ImageComparisonHelper.get().setPictureSize(i, i2);
            SPHelper.setLastUsedResolution(i, i2);
            if (TextureFragment.this.isCameraOpened) {
                return;
            }
            TextureFragment.this.isCameraOpened = true;
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCameraOpen();
        }
    }

    private File createMediaVideoFile() {
        HVLogUtils.d(TAG, "createMediaVideoFile() called");
        File photoDirectory = this.camHost.getPhotoDirectory();
        if (photoDirectory == null) {
            return null;
        }
        return new File(photoDirectory.getPath() + File.separator + this.camHost.getVideoFilename());
    }

    public void initView(final View view) {
        HVLogUtils.d(TAG, "initView() called with: root = [" + view + "]");
        this.parentContainer = (FrameLayout) view.findViewById(R.id.parent_container);
        this.statusString = (TextView) view.findViewById(R.id.tvStatus);
        this.overlayImageView = (ShapeableImageView) view.findViewById(R.id.faceOverlayImageView);
        this.cameraIcon = (ImageView) view.findViewById(R.id.camera_icon);
        this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivFlashFlip);
        this.flip = imageView;
        imageView.setImageResource(R.drawable.ic_flip_camera_24);
        this.ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.layoutFaceLoader);
        this.faceLoaderLayout = constraintLayout;
        this.lav = (LottieAnimationView) constraintLayout.findViewById(R.id.lavFaceLoader);
        TextView textView = (TextView) view.findViewById(R.id.hv_face_capture_alert_text_view);
        this.alertTextView = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.progressDialogView = view.findViewById(R.id.faceProgressDialogView);
        this.progressSpinnerImageView = (ImageView) view.findViewById(R.id.hv_loading_icon);
        this.progressDialogTextView = (TextView) view.findViewById(R.id.hv_loading_text);
        HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon(this.ivBack, true);
        HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) view.findViewById(R.id.clientLogo), true);
        if (!getFaceConfig().shouldShowModuleBackButton() && !getFaceConfig().isShouldShowInstructionPage()) {
            this.ivBack.setVisibility(4);
        }
        if (injectContext() != null && getFaceConfig().isShowAlertTextBox(injectContext().getResources().getDisplayMetrics())) {
            this.alertTextView.setVisibility(0);
            HyperSnapUIConfigUtil.getInstance().customiseAlertBoxTextView(this.alertTextView);
        }
        setupTextViewTypefaces();
        setUpTextsForViews();
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(this.tvTitle, true);
        this.tvTitle.setVisibility(4);
        HyperSnapUIConfigUtil.getInstance().customiseStatusTextView(this.statusString, true);
        setUpTextsForViews();
        if (getFaceConfig().isShouldAutoCapture() || getFaceConfig().isShouldCheckActiveLiveness()) {
            this.cameraIcon.setVisibility(4);
            this.autoCaptureTimer = new CountDownTimer(getFaceConfig().getAutoCaptureDuration(), 1000L) { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.3
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    TextureFragment.this.setStatusString(CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_AUTO_CAPTURE_ACTION, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_AUTO_CAPTURE_ACTION, LanguageHelper.AUTO_CAPTURE_ACTION);
                    TextureFragment.this.capturePicture();
                }
            };
        } else {
            this.cameraIcon.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TextureFragment.this.m518x92dd7458(view2);
                }
            });
        }
        this.flip.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TextureFragment.this.m519x76092799(view2);
            }
        });
        this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TextureFragment.this.m520x5934dada(view2);
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.4
            @Override // java.lang.Runnable
            public void run() {
                if (SDKInternalConfig.getInstance().isRemoteConfigFetchDone() && Utils.canDismissLoader(TextureFragment.this.getActivity())) {
                    TextureFragment.this.setupBranding(view);
                }
            }
        }, 100L);
        if (!getFaceConfig().isShouldAllowPhoneTilt()) {
            PhoneTiltDetectorService phoneTiltDetectorService = new PhoneTiltDetectorService(injectContext(), new PhoneTiltListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda11
                @Override // co.hyperverge.hypersnapsdk.service.sensorbiometrics.PhoneTiltListener
                public final void onSensorDataChanged(SensorData sensorData) {
                    TextureFragment.this.m521x3c608e1b(sensorData);
                }
            });
            this.phoneTiltDetectorService = phoneTiltDetectorService;
            phoneTiltDetectorService.registerSensors();
            return;
        }
        this.isPhoneHeldStraight = true;
    }

    /* renamed from: lambda$initView$2$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m518x92dd7458(View view) {
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureButtonClicked(this.captureClickTimingUtils.getTimeDifferenceLong().longValue());
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
            SDKInternalConfig.getInstance().getHvSensorBiometrics().updateSensorDataEvents(System.currentTimeMillis(), "selfieCaptureClicked");
        }
        capturePicture();
    }

    /* renamed from: lambda$initView$3$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m519x76092799(View view) {
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureFlipCameraButtonClicked();
        }
        swapCamera();
    }

    /* renamed from: lambda$initView$4$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m520x5934dada(View view) {
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCloseClicked();
        }
        requireActivity().onBackPressed();
    }

    /* renamed from: lambda$initView$5$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m521x3c608e1b(SensorData sensorData) {
        this.isPhoneHeldStraight = sensorData.getPitch() < ((float) getFaceConfig().getPhoneTiltThreshold());
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public boolean isPhoneHeldStraight() {
        return this.isPhoneHeldStraight;
    }

    private void setUpTextsForViews() {
        HVLogUtils.d(TAG, "setUpTextsForViews() called");
        HVJSONObject customUIStrings = getFaceConfig().getCustomUIStrings();
        Spanned text = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_TITLE, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_CAPTURE_TITLE);
        if (text != null) {
            this.tvTitle.setText(text);
        }
        Spanned text2 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_ALERT_TEXT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_ALERT_BOX_TEXT_MESSAGE);
        if (text2 != null) {
            this.alertTextView.setText(text2);
        }
    }

    private void setupTextViewTypefaces() {
        HVLogUtils.d(TAG, "setupTextViewTypefaces() called");
        try {
            getFaceConfig().getTitleTypeface();
            if (getFaceConfig().getTitleTypeface() > 0) {
                this.statusString.setTypeface(ResourcesCompat.getFont(requireActivity().getApplicationContext(), getFaceConfig().getTitleTypeface()));
            }
        } catch (Exception e) {
            HVLogUtils.e(TAG, "setupTextViewTypefaces(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupBranding(View view) {
        HVLogUtils.d(TAG, "setupBranding() called with: rootView = [" + view + "]");
        ((TextView) view.findViewById(R.id.includeFaceBranding).findViewById(R.id.tvBranding)).setVisibility(SDKInternalConfig.getInstance() != null && SDKInternalConfig.getInstance().isShouldUseBranding() ? 0 : 8);
    }

    private void addCrossHairView(FrameLayout frameLayout) {
        HVLogUtils.d(TAG, "addCrossHairView() called with: cameraContainer = [" + frameLayout + "]");
        CrossHairView crossHairView = new CrossHairView(getActivity());
        this.crossHairView = crossHairView;
        frameLayout.removeView(crossHairView);
        frameLayout.addView(this.crossHairView);
        frameLayout.setOnTouchListener(new View.OnTouchListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return TextureFragment.this.m516x653bee6d(view, motionEvent);
            }
        });
    }

    /* renamed from: lambda$addCrossHairView$6$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ boolean m516x653bee6d(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.lastTouchX = motionEvent.getX();
            this.lastTouchY = motionEvent.getY();
        } else if (actionMasked != 1) {
            Log.d(TAG, "cameraContainer : neither action up nor action down");
        } else if (Math.abs(motionEvent.getX() - this.lastTouchX) < 20.0f && Math.abs(motionEvent.getY() - this.lastTouchY) < 20.0f) {
            this.crossHairView.showCrosshair(motionEvent.getX(), motionEvent.getY(), false);
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.onTouchToFocus(motionEvent.getX() / this.camViewWidth, motionEvent.getY() / this.camViewHeight, null);
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        HVLogUtils.d(TAG, "onPause() called");
        this.isPaused = true;
        try {
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.onPause();
            }
            this.safeToTakePicture = false;
        } catch (Exception e) {
            HVLogUtils.e(TAG, "onPause(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        ProgressDialog progressDialog = this.dialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.dialog.cancel();
        }
        AlertDialog alertDialog = this.captureAlertDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.captureAlertDialog.dismiss();
        }
        CountDownTimer countDownTimer = this.captureTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        TextureContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.resetCounters();
        }
        PhoneTiltDetectorService phoneTiltDetectorService = this.phoneTiltDetectorService;
        if (phoneTiltDetectorService != null) {
            phoneTiltDetectorService.pauseSensors();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        HVLogUtils.d(TAG, "onResume() called");
        TextureContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.resume();
        }
        this.isPaused = false;
        addChildViews();
        initializeProgressBar();
        UIUtils.setScreenBrightness(getActivity(), 255);
        setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
        if (isAdded()) {
            try {
                if (this.captureTimer != null) {
                    long j = this.timeUntilFinished;
                    if (j > 0) {
                        startCaptureTimer(j);
                    }
                }
                if (this.cameraView != null && this.mPresenter.canResumeCamera().booleanValue()) {
                    this.cameraView.onResume();
                    this.safeToTakePicture = true;
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        PhoneTiltDetectorService phoneTiltDetectorService = this.phoneTiltDetectorService;
        if (phoneTiltDetectorService != null) {
            phoneTiltDetectorService.resumeSensors();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        HVLogUtils.d(TAG, "onDestroyView() called");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        HVLogUtils.d(TAG, "onActivityCreated() called with: savedInstanceState = [" + bundle + "]");
        try {
            TextureContract.Presenter presenter = this.mPresenter;
            if (presenter != null) {
                presenter.start();
            }
            enableTimers();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "onActivityCreated() with: savedInstanceState = [" + bundle + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        HVLogUtils.d(TAG, "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        reinitTimingUtils();
        setupOverlayImageView();
        showProgressDialog(false, null);
        if (getFaceConfig() != null && getFaceConfig().isShouldRecordVideo()) {
            reinitVideoRecording();
        }
        if (i2 == 18) {
            HVError hVError = (HVError) intent.getSerializableExtra("hvError");
            TextureContract.Presenter presenter = this.mPresenter;
            if (presenter != null) {
                presenter.faceCaptureError(hVError);
                return;
            }
            return;
        }
        if (i2 == 20) {
            TextureContract.Presenter presenter2 = this.mPresenter;
            if (presenter2 != null) {
                presenter2.operationCancelled();
                return;
            }
            return;
        }
        Log.d(TAG, "onActivityResult : default case has been triggered");
    }

    public void reinitTimingUtils() {
        HVLogUtils.d(TAG, "reinitTimingUtils() called");
        this.captureClickTimingUtils.init();
        this.imageCaptureTimingUtils.init();
    }

    public void reinitVideoRecording() {
        HVLogUtils.d(TAG, "reinitVideoRecording() called");
        this.videoUri = "";
        this.isVideoRecorded = false;
        this.isVideoRecordingProcessComplete = false;
    }

    private void createTextureCamera() {
        HVLogUtils.d(TAG, "createTextureCamera() called");
        try {
            HVCameraHelper.enableCameraParameters(getActivity(), getFaceConfig().getShouldUseBackCamera());
            this.previewContainer = new TextureCamera(requireActivity());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.previewContainer.getDiameter(), this.previewContainer.getDiameter());
            layoutParams.gravity = 49;
            this.previewContainer.setLayoutParams(layoutParams);
            this.parentContainer.removeView(this.previewContainer);
            this.parentContainer.addView(this.previewContainer);
            this.faceBoxView = new FaceBoxView(getActivity());
            this.camViewWidth = this.previewContainer.getDiameter();
            this.camViewHeight = this.previewContainer.getDiameter();
            int i = 0;
            this.isFrontCam = !getFaceConfig().getShouldUseBackCamera();
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                this.previewContainer.removeView(hVMagicView);
                this.cameraView.onDestroy();
                this.cameraView.clearHandlers();
                this.cameraView = null;
            }
            HVMagicView hVMagicView2 = HVMagicView.getInstance(requireActivity(), this.camHost, !getFaceConfig().getShouldUseBackCamera());
            this.cameraView = hVMagicView2;
            hVMagicView2.disableRotation();
            HVMagicView hVMagicView3 = this.cameraView;
            Objects.requireNonNull((HVFaceActivity) requireActivity());
            hVMagicView3.setContentDescription("faceCaptureCameraPreview");
            Display defaultDisplay = requireActivity().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            CameraEngine.setScreenSize(point);
            CameraEngine.setPreviewCallback(SDKInternalConfig.getInstance().isFaceDetectionOn());
            CameraEngine.setCaptureMode(true);
            Size lastUsedCameraResolution = SPHelper.getLastUsedCameraResolution();
            CameraEngine.setLastUsedResolution(lastUsedCameraResolution.getWidth(), lastUsedCameraResolution.getHeight());
            if (SDKInternalConfig.getInstance() != null) {
                CameraEngine.setShouldRandomize(SDKInternalConfig.getInstance().shouldRandomizeResolution());
            }
            if (getFaceConfig().isShouldUseEnhancedCameraFeatures()) {
                CameraEngine.setUseEnhancedCameraFeatures(true);
            }
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.previewContainer.getDiameter(), this.previewContainer.getDiameter());
            HVMagicView hVMagicView4 = this.cameraView;
            if (hVMagicView4 != null) {
                hVMagicView4.setLayoutParams(layoutParams2);
                this.cameraView.setSensorCallback(new HVMagicView.SensorCallback() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda10
                    @Override // co.hyperverge.hvcamera.HVMagicView.SensorCallback
                    public final void onSensorCallback() {
                        TextureFragment.this.m517x15821fb2();
                    }
                });
            }
            this.previewContainer.addView(this.cameraView);
            addCrossHairView(this.previewContainer);
            adjustCrossHairView();
            adjustOverlayImageView();
            adjustBlackOverlayView();
            adjustProgressDialogView();
            if (this.crossHairView != null) {
                if (!getFaceConfig().getShouldUseBackCamera()) {
                    i = 8;
                }
                this.crossHairView.setVisibility(i);
            }
            this.previewContainer.removeView(this.faceBoxView);
            this.previewContainer.addView(this.faceBoxView);
        } catch (Exception e) {
            HVLogUtils.e(TAG, "createTextureCamera(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* renamed from: lambda$createTextureCamera$7$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m517x15821fb2() {
        Log.i("CameraActivity", this.previewContainer.getWidth() + " " + this.previewContainer.getHeight());
        CrossHairView crossHairView = this.crossHairView;
        if (crossHairView != null) {
            crossHairView.showCrosshair(this.camViewWidth / 2, this.camViewHeight / 2, false);
        }
    }

    private void addProgressbar() {
        HVLogUtils.d(TAG, "addProgressbar() called");
        try {
            CircularProgressBar circularProgressBar = new CircularProgressBar(getActivity());
            this.progressBar = circularProgressBar;
            circularProgressBar.setId(R.id.hv_circular_bar);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.progressBar.getDiameter(), this.progressBar.getDiameter());
            layoutParams.gravity = 49;
            this.progressBar.setLayoutParams(layoutParams);
            this.parentContainer.removeView(this.progressBar);
            this.parentContainer.addView(this.progressBar);
        } catch (Exception e) {
            HVLogUtils.e(TAG, "addProgressbar(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void enableTimers() {
        HVLogUtils.d(TAG, "enableTimers() called");
        if (getFaceConfig().getFaceDetectorTimeout() > 0) {
            CountDownTimer countDownTimer = new CountDownTimer(getFaceConfig().getFaceDetectorTimeout(), 1000L) { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.5
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    TextureFragment.this.checkIfFaceIsDetected();
                }
            };
            this.faceDetectorTimer = countDownTimer;
            countDownTimer.start();
        }
        startCaptureTimer(getFaceConfig().getCaptureTimeout());
    }

    public void startCaptureTimer(long j) {
        HVLogUtils.d(TAG, "startCaptureTimer() called with: timeRemaining = [" + j + "]");
        if (getFaceConfig().getCaptureTimeout() > 0) {
            CountDownTimer countDownTimer = new CountDownTimer(j, 500L) { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.6
                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                    TextureFragment.this.timeUntilFinished = j2;
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    TextureFragment.this.isCaptureTimerFinished = true;
                    TextureFragment.this.disableCaptureButton();
                    TextureFragment.this.pauseCamera();
                    TextureFragment.this.timeoutForCapture();
                }
            };
            this.captureTimer = countDownTimer;
            countDownTimer.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIfFaceIsDetected() {
        HVLogUtils.d(TAG, "checkIfFaceIsDetected() called" + this.mPresenter.isFaceDetectedOnce());
        if (this.mPresenter.isFaceDetectedOnce()) {
            return;
        }
        this.mPresenter.setFaceDetectorTimedOut();
        getFaceConfig().setDisableFaceDetection(true);
        enableCaptureButton();
        setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_DETECTED);
        this.faceDetectorTimer.cancel();
        if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
            return;
        }
        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logFaceDetectorTimedOut();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void timeoutForCapture() {
        HVLogUtils.d(TAG, "timeoutForCapture() called");
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logFaceCaptureTimedOut();
        }
        showCaptureTimedOutAlert();
    }

    private void showCaptureTimedOutAlert() {
        HVLogUtils.d(TAG, "showCaptureTimedOutAlert() called");
        try {
            String captureTimeoutDialogTitle = getFaceConfig().getCaptureTimeoutDialogTitle();
            String captureTimeoutDialogDesc = getFaceConfig().getCaptureTimeoutDialogDesc();
            if (captureTimeoutDialogTitle == null) {
                captureTimeoutDialogTitle = TextConfigUtils.getText(this.customStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_TIMEOUT_TITLE, getString(R.string.hv_capture_timeout_title)).toString();
            }
            if (captureTimeoutDialogDesc == null) {
                captureTimeoutDialogDesc = TextConfigUtils.getText(this.customStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_TIMEOUT_DESC, getString(R.string.hv_capture_timeout_desc)).toString();
            }
            String obj = TextConfigUtils.getText(this.customStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_TIMEOUT_BUTTON, getString(R.string.hv_capture_timeout_positive_action)).toString();
            if (canShowDialog()) {
                this.captureAlertDialog = new AlertDialog.Builder(requireActivity()).setTitle(captureTimeoutDialogTitle).setMessage(captureTimeoutDialogDesc).setCancelable(false).setPositiveButton(obj, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        TextureFragment.this.m525xceaedb05(dialogInterface, i);
                    }
                }).show();
            }
        } catch (Exception e) {
            HVLogUtils.e(TAG, "showCaptureTimedOutAlert(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* renamed from: lambda$showCaptureTimedOutAlert$8$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m525xceaedb05(DialogInterface dialogInterface, int i) {
        this.mPresenter.faceCaptureTimeout();
    }

    private void addBlackOverlayView() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "addBlackOverlayView() called");
            try {
                this.blackOverlay = new TextureBlackOverlay(requireActivity());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.blackOverlay.getDiameter(), this.blackOverlay.getDiameter());
                layoutParams.gravity = 49;
                this.blackOverlay.setLayoutParams(layoutParams);
                if (getFaceConfig().isShouldUseDefaultZoom()) {
                    this.blackOverlay.setOverlayBackground(getResources().getColor(R.color.black_opaque));
                } else {
                    this.blackOverlay.setOverlayBackground(getResources().getColor(R.color.black_transparent));
                }
                this.parentContainer.removeView(this.blackOverlay);
                this.parentContainer.addView(this.blackOverlay);
                this.blackOverlay.setVisibility(8);
            } catch (Exception e) {
                HVLogUtils.e(TAG, "addBlackOverlayView(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    private void addProgressDialogView() {
        HVLogUtils.d(TAG, "addProgressDialogView() called");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.previewContainer.getDiameter(), this.previewContainer.getDiameter());
        layoutParams.gravity = 49;
        this.progressDialogView.setLayoutParams(layoutParams);
        this.parentContainer.removeView(this.progressDialogView);
        this.parentContainer.addView(this.progressDialogView);
    }

    private void initializeProgressBar() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "initializeProgressBar() called");
            try {
                this.progressBar.setBackgroundColor(getResources().getColor(R.color.progress_grey));
                this.progressBar.setProgressColor(getResources().getColor(R.color.face_capture_circle_failure));
                this.progressBar.setMaxProgress(100);
                this.progressBar.setProgress(100);
                if (SDKInternalConfig.getInstance().isFaceDetectionOn()) {
                    return;
                }
                this.progressBar.setmStrokeWidth(10);
            } catch (Exception e) {
                HVLogUtils.e(TAG, "initializeProgressBar(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    private void addChildViews() {
        HVLogUtils.d(TAG, "addChildViews() called");
        try {
            createTextureCamera();
            addProgressbar();
            addBlackOverlayView();
            addProgressDialogView();
            setupOverlayImageView();
            removeProgressView();
            if (!this.isCameraInitialized) {
                shouldShowProgress(true, null);
                this.progressDialogTextView.setVisibility(8);
            }
            this.flip.setVisibility(getFaceConfig().isShouldUseFlip() ? 0 : 4);
            this.customStrings = getFaceConfig().getCustomUIStrings();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "addChildViews(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$7] */
    private void setupOverlayImageView() {
        HVLogUtils.d(TAG, "setupOverlayImageView() called");
        if (getFaceConfig().getFaceCaptureOverlay() != null) {
            this.overlayImageView.setImageBitmap(getFaceConfig().getFaceCaptureOverlay());
        }
        this.parentContainer.removeView(this.overlayImageView);
        this.parentContainer.addView(this.overlayImageView);
        showOverlayImageView(this.overlayImageView);
        adjustOverlayImageView();
        if (getFaceConfig().getFaceCaptureOverlayDuration() != Integer.MAX_VALUE) {
            new CountDownTimer(getFaceConfig().getFaceCaptureOverlayDuration(), 1000L) { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.7
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    TextureFragment.this.overlayImageView.setVisibility(4);
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustCrossHairView() {
        HVLogUtils.d(TAG, "adjustCrossHairView() called");
        if (getFaceConfig().getShouldUseBackCamera()) {
            if (this.crossHairView.getParent() != null) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.crossHairView.getLayoutParams();
                layoutParams.height = this.camViewHeight;
                layoutParams.width = this.camViewWidth;
                HVMagicView hVMagicView = this.cameraView;
                if (hVMagicView != null) {
                    this.crossHairView.setX(hVMagicView.getX());
                    this.crossHairView.setY(this.cameraView.getY());
                }
                this.crossHairView.requestLayout();
            }
            this.previewContainer.requestLayout();
        }
    }

    public void adjustOverlayImageView() {
        HVLogUtils.d(TAG, "adjustOverlayImageView() called");
        ShapeableImageView shapeableImageView = this.overlayImageView;
        if (shapeableImageView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) shapeableImageView.getLayoutParams();
            if (getContext() != null) {
                layoutParams.setMargins(UIUtils.dpToPx(getContext(), 32.0f), UIUtils.dpToPx(getContext(), 32.0f), UIUtils.dpToPx(getContext(), 32.0f), UIUtils.dpToPx(getContext(), 32.0f));
            }
            if (!this.overlayImageView.isInLayout()) {
                this.overlayImageView.setLayoutParams(layoutParams);
            }
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView == null || hVMagicView.isInLayout()) {
                return;
            }
            this.cameraView.requestLayout();
        }
    }

    public void adjustBlackOverlayView() {
        HVLogUtils.d(TAG, "adjustBlackOverlayView() called");
        TextureBlackOverlay textureBlackOverlay = this.blackOverlay;
        if (textureBlackOverlay != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textureBlackOverlay.getLayoutParams();
            if (!this.blackOverlay.isInLayout()) {
                this.blackOverlay.setLayoutParams(layoutParams);
            }
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView == null || hVMagicView.isInLayout()) {
                return;
            }
            this.cameraView.requestLayout();
        }
    }

    public void adjustProgressDialogView() {
        HVLogUtils.d(TAG, "adjustProgressDialogView() called");
        View view = this.progressDialogView;
        if (view != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (getContext() != null) {
                layoutParams.setMargins(UIUtils.dpToPx(getContext(), 32.0f), 0, UIUtils.dpToPx(getContext(), 32.0f), 0);
            }
            if (!this.progressDialogView.isInLayout()) {
                this.progressDialogView.setLayoutParams(layoutParams);
            }
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView == null || hVMagicView.isInLayout()) {
                return;
            }
            this.cameraView.requestLayout();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // co.hyperverge.hypersnapsdk.utils.BaseView
    public TextureContract.Presenter getPresenter() {
        return this.mPresenter;
    }

    @Override // co.hyperverge.hypersnapsdk.utils.BaseView
    public void setPresenter(TextureContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // co.hyperverge.hypersnapsdk.utils.BaseView
    public void onBackPress() {
        HyperVideoRecorder hyperVideoRecorder;
        HVLogUtils.d(TAG, "onBackPress() called");
        if (this.mPresenter != null) {
            if (getFaceConfig().isShouldRecordVideo() && (hyperVideoRecorder = this.hyperVideoRecorder) != null) {
                hyperVideoRecorder.stop(new HyperVideoListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda8
                    @Override // co.hyperverge.encoder.HyperVideoListener
                    public final void invoke(File file) {
                        TextureFragment.this.m523x780c602d(file);
                    }
                });
            } else {
                this.mPresenter.operationCancelled();
            }
        }
    }

    /* renamed from: lambda$onBackPress$9$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m523x780c602d(File file) {
        reinitVideoRecording();
        this.mPresenter.operationCancelled();
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void resetPoseTimer() {
        HVLogUtils.d(TAG, "resetPoseTimer() called");
        CountDownTimer countDownTimer = this.gesturePoseTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.gesturePoseTimer = null;
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void facePresentForActiveLiveness() {
        if (isFragmentAdded()) {
            try {
                HVLogUtils.d(TAG, "facePresentForActiveLiveness() called");
                this.progressBar.setProgressColor(getResources().getColor(R.color.face_capture_circle_success));
            } catch (Exception e) {
                HVLogUtils.e(TAG, "facePresentForActiveLiveness(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void showMessage(String str) {
        if (isFragmentAdded()) {
            try {
                HVLogUtils.d(TAG, "showMessage() called with: message = [" + str + "]");
                this.statusString.setText(str);
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void disableCaptureButton() {
        HVLogUtils.d(TAG, "disableCaptureButton() called");
        this.cameraIcon.setOnTouchListener(null);
        this.cameraIcon.setClickable(false);
        this.cameraIcon.setImageResource(R.drawable.hv_camera_button_disabled);
        ImageViewCompat.setImageTintList(this.cameraIcon, null);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void enableCaptureButton() {
        HVLogUtils.d(TAG, "enableCaptureButton() called");
        this.cameraIcon.setOnTouchListener(this.touchCameraIconListener);
        this.cameraIcon.setClickable(true);
        this.flip.setClickable(true);
        this.cameraIcon.setImageResource(R.drawable.ic_camera_button_svg);
        HyperSnapUIConfigUtil.getInstance().customiseCaptureButton(this.cameraIcon);
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public float getViewY() {
        TextureCamera textureCamera;
        if (!isFragmentAdded() || (textureCamera = this.previewContainer) == null) {
            return 0.0f;
        }
        textureCamera.getLocationOnScreen(new int[2]);
        return r1[1];
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public float getViewX() {
        TextureCamera textureCamera;
        if (!isFragmentAdded() || (textureCamera = this.previewContainer) == null) {
            return 0.0f;
        }
        textureCamera.getLocationOnScreen(new int[2]);
        return r1[0];
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public float getViewYCenter() {
        if (isFragmentAdded()) {
            return getViewY() + (this.previewContainer.getHeight() / 2);
        }
        return 0.0f;
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public int getViewRadius() {
        if (!isFragmentAdded()) {
            return 0;
        }
        HVLogUtils.d(TAG, "getViewRadius() called");
        return this.previewContainer.getDiameter() / 2;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void showFaceBox(List<Integer> list) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "showFaceBox() called with: rectPoints = [" + list + "]");
            this.faceBoxView.setPoints(list.get(0).intValue(), list.get(1).intValue(), list.get(2).intValue(), list.get(3).intValue());
            this.faceBoxView.showHideBox(true);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void hideFaceBox() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "hideFaceBox() called");
            this.faceBoxView.showHideBox(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatusString(String str, String str2, String str3) {
        Spanned text;
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "setStatusString() called with: tag = [" + str + "], tcKey = [" + str2 + "], defaultValue = [" + str3 + "]");
            if (this.statusString == null || !isAdded() || (text = TextConfigUtils.getText(this.customStrings, str, str2, str3)) == null) {
                return;
            }
            this.statusString.setText(text);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void authenticationDone() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "authenticationDone() called");
            try {
                CountDownTimer countDownTimer = this.gesturePoseTimer;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    this.gesturePoseTimer = null;
                }
                CountDownTimer countDownTimer2 = this.autoCaptureTimer;
                if (countDownTimer2 != null) {
                    countDownTimer2.cancel();
                }
                this.autoCaptureTimer = new CountDownTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, 1000L) { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.8
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        TextureFragment.this.setStatusString(CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_AUTO_CAPTURE_ACTION, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_AUTO_CAPTURE_ACTION, LanguageHelper.AUTO_CAPTURE_ACTION);
                        TextureFragment.this.capturePicture();
                    }
                };
                this.progressBar.setProgressColor(getResources().getColor(R.color.face_capture_circle_success));
                this.statusString.setText(TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_GESTURE_LOOK_STRAIGHT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_GESTURE_LOOK_STRAIGHT, "Look Straight"));
                this.autoCaptureTimer.start();
            } catch (Exception e) {
                HVLogUtils.e(TAG, "authenticationDone(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void poseMatches(int i) {
        HVLogUtils.d(TAG, "poseMatches() called with: correctAttempts = [" + i + "]");
        try {
            this.delayhandler.removeCallbacks(this.poseRunnable);
            this.delayhandler.post(this.poseRunnable);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void stablePose() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "stablePose() called");
            this.progressBar.setProgressColor(getResources().getColor(R.color.progress_green));
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void poseDoesNotMatch(boolean z) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "poseDoesNotMatch() called with: shouldStopCapture = [" + z + "]");
            try {
                this.progressBar.setProgressColor(getResources().getColor(R.color.progress_red));
                if (z) {
                    this.delayhandler.removeCallbacks(this.poseRunnable);
                }
                this.delayhandler.postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment.9
                    @Override // java.lang.Runnable
                    public void run() {
                        TextureFragment.this.mPresenter.waitForUI(false);
                    }
                }, 50L);
            } catch (Exception e) {
                HVLogUtils.e(TAG, "poseDoesNotMatch() with: shouldStopCapture = [" + z + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void resetUI() {
        try {
            this.mPresenter.waitForUI(true);
            CountDownTimer countDownTimer = this.gesturePoseTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.gesturePoseTimer = null;
            }
            CountDownTimer countDownTimer2 = this.autoCaptureTimer;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
                this.autoCaptureTimer = null;
            }
        } catch (Exception e) {
            HVLogUtils.e(TAG, "resetUI(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void onNewPose(Spanned spanned) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "onNewPose() called with: facePose = [" + ((Object) spanned) + "]");
            try {
                CountDownTimer countDownTimer = this.gesturePoseTimer;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    this.gesturePoseTimer = null;
                }
                startTimer();
                this.statusString.setText(spanned);
            } catch (Exception e) {
                HVLogUtils.e(TAG, "onNewPose() with: facePose = [" + ((Object) spanned) + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    public void startTimer() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "startTimer() called");
            try {
                this.progressBar.setProgressColor(getResources().getColor(R.color.progress_green));
                this.progressBar.setVisibility(0);
                AnonymousClass10 anonymousClass10 = new AnonymousClass10(5000L, 100L);
                this.gesturePoseTimer = anonymousClass10;
                anonymousClass10.start();
            } catch (Exception e) {
                HVLogUtils.e(TAG, "startTimer(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$10, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass10 extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        AnonymousClass10(long j, long j2) {
            super(j, j2);
        }

        /* renamed from: lambda$onFinish$0$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment$10, reason: not valid java name */
        public /* synthetic */ void m528x2ea41810() {
            TextureFragment.this.mPresenter.resetTimer();
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            TextureFragment.this.delayhandler.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$10$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TextureFragment.AnonymousClass10.this.m528x2ea41810();
                }
            });
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void finishTextureView(int i) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "finishTextureView() called with: resultCode = [" + i + "]");
            if (getActivity() != null) {
                HVMagicView hVMagicView = this.cameraView;
                if (hVMagicView != null) {
                    hVMagicView.clearHandlers();
                    this.cameraView = null;
                }
                getActivity().setResult(i);
                getActivity().finish();
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void callCompletionHandler(HVError hVError, HVResponse hVResponse) {
        HVFaceActivity hVFaceActivity = (HVFaceActivity) getActivity();
        if (hVFaceActivity != null) {
            hVFaceActivity.callCompletionHandler(this.config.getModuleId(), hVError, hVResponse);
        } else {
            HVLogUtils.e(TAG, "callCompletionHandler error, activity is null");
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public Context injectContext() {
        return getActivity();
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void stopCamera() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "stopCamera() called");
            try {
                HVMagicView hVMagicView = this.cameraView;
                if (hVMagicView != null) {
                    hVMagicView.setSensorCallback(null);
                    this.cameraView.onDestroy();
                    this.cameraView.onPause();
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void resumeCamera() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "resumeCamera() called");
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.onResume();
            }
            this.safeToTakePicture = true;
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void pauseCamera() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "pauseCamera() called");
            try {
                HVMagicView hVMagicView = this.cameraView;
                if (hVMagicView != null) {
                    hVMagicView.onPause();
                }
                if (getFaceConfig().isShouldCheckActiveLiveness()) {
                    resetUI();
                }
            } catch (Exception e) {
                HVLogUtils.e(TAG, "pauseCamera(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void shouldShowProgress(boolean z, String str) {
        HVLogUtils.d(TAG, "shouldShowProgress() called with: shouldShow = [" + z + "], progressLoaderText = [" + str + "]");
        try {
            showProgressDialog(z, str);
        } catch (Exception e) {
            HVLogUtils.e(TAG, "shouldShowProgress() with: shouldShow = [" + z + "], progressLoaderText = [" + str + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void showProgressDialog(boolean z, String str) {
        HVLogUtils.d(TAG, "showProgressDialog() called with: show = [" + z + "], progressLoaderText = [" + str + "]");
        if (this.blackOverlay != null) {
            if (z) {
                displayProgressView(str);
            } else {
                removeProgressView();
            }
        }
    }

    private void displayProgressView(String str) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "displayProgressView() called with: progressLoaderText = [" + str + "]");
            TextureBlackOverlay textureBlackOverlay = this.blackOverlay;
            if (textureBlackOverlay != null) {
                textureBlackOverlay.setVisibility(0);
            }
            ImageView imageView = this.progressSpinnerImageView;
            if (imageView != null) {
                imageView.startAnimation(UIUtils.getInfiniteRotationAnimation());
            }
            if (str != null) {
                this.progressDialogTextView.setText(str);
            }
            View view = this.progressDialogView;
            if (view != null) {
                view.setVisibility(0);
            }
            ShapeableImageView shapeableImageView = this.overlayImageView;
            if (shapeableImageView != null) {
                shapeableImageView.setVisibility(4);
            }
        }
    }

    private void removeProgressView() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "removeProgressView() called");
            TextureBlackOverlay textureBlackOverlay = this.blackOverlay;
            if (textureBlackOverlay != null) {
                textureBlackOverlay.setVisibility(8);
            }
            ImageView imageView = this.progressSpinnerImageView;
            if (imageView != null) {
                imageView.clearAnimation();
            }
            View view = this.progressDialogView;
            if (view != null) {
                view.setVisibility(8);
            }
        }
    }

    private void showOverlayImageView(ShapeableImageView shapeableImageView) {
        HVLogUtils.d(TAG, "showOverlayImageView() called with: overlayImageView = [" + shapeableImageView + "]");
        if (this.isPaused || shapeableImageView == null || !getFaceConfig().isOverlayEnabled()) {
            return;
        }
        shapeableImageView.setVisibility(0);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void showLoader(final boolean z, String str, String str2, HVLottieHelper.State state, final HVLottieHelper.Listener listener) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "showLoader() called with: show = [" + z + "], progressLoaderText = [" + str + "], anim = [" + str2 + "], state = [" + state + "], animListener = [" + listener + "]");
            MainUIThread.getInstance().post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    TextureFragment.this.m526xd001a76e(z, listener);
                }
            });
        }
    }

    /* renamed from: lambda$showLoader$10$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TextureFragment, reason: not valid java name */
    public /* synthetic */ void m526xd001a76e(boolean z, HVLottieHelper.Listener listener) {
        if (isFragmentAdded()) {
            if (getFaceConfig().getCustomLoaderClass() == null) {
                this.safeToTakePicture = !z;
                Spanned text = TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_LOADER_DESC, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOADER_DESC, getStringForID(R.string.hv_face_loader_subtitle));
                showProgressDialog(z, text != null ? text.toString() : null);
                if (listener != null) {
                    listener.onEnd();
                    return;
                }
                return;
            }
            if (z) {
                try {
                    requireActivity().startActivityForResult(new Intent(requireContext(), Class.forName(getFaceConfig().getCustomLoaderClass())), 87);
                    return;
                } catch (ClassNotFoundException e) {
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                        return;
                    }
                    return;
                }
            }
            requireActivity().finishActivity(87);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void resetLoader() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "resetLoader() called");
            this.faceLoaderLayout.setVisibility(8);
            HVLottieHelper.reset(this.lav);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public String getStringForID(int i) {
        if (!isFragmentAdded()) {
            return "";
        }
        HVLogUtils.d(TAG, "getStringForID() called with: resourceID = [" + i + "]");
        return getResources().getString(i);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public String getRetakeMessageForFaceNotPresentInCapturedImage() {
        Log.d(TAG, "getRetakeMessageForFaceNotPresentInCapturedImage() called");
        Spanned text = TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_FACE_NOT_PRESENT_IN_CAPTURED_IMAGE, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_NOT_PRESENT_IN_CAPTURE_IMAGE, LanguageHelper.FACE_NOT_PRESENT_IN_CAPTURED_IMAGE);
        return text != null ? text.toString() : LanguageHelper.FACE_NOT_PRESENT_IN_CAPTURED_IMAGE;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void onPictureSaved(String str) {
        HVLogUtils.d(TAG, "onPictureSaved() called with: imageFilePath = [" + str + "]");
        if (str != null) {
            this.camHost.onPictureSaved(new File(str));
        }
    }

    private void progressBarStopAnimation() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "progressBarStopAnimation() called");
            this.isProgressBarAnimating = false;
            CountDownTimer countDownTimer = this.autoCaptureTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }
    }

    public void capturePicture() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "capturePicture() called");
            try {
                disableCaptureButton();
                this.flip.setClickable(false);
                TextureContract.Presenter presenter = this.mPresenter;
                if (presenter != null && presenter.isFacePresent() && this.safeToTakePicture) {
                    this.imageCaptureTimingUtils.init();
                    CountDownTimer countDownTimer = this.captureTimer;
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                        this.timeUntilFinished = 0L;
                    }
                    this.safeToTakePicture = false;
                    if (this.cameraView != null) {
                        this.isTakePictureInProgress = true;
                        if (getFaceConfig().isShouldRecordVideo()) {
                            this.videoRecordTimingUtils.init();
                            obtainVideo();
                        }
                        this.mPresenter.showDialog(true, "");
                        if (getFaceConfig().isShouldUseDefaultZoom()) {
                            CameraEngine.resetZoom();
                        }
                        HVMagicView hVMagicView = this.cameraView;
                        if (hVMagicView != null) {
                            hVMagicView.takePicture(null);
                            return;
                        }
                        return;
                    }
                    this.hvImageCaptureError = new HVError(2, "camerView is null");
                    long longValue = this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
                    if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                        return;
                    }
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureFailed(this.hvImageCaptureError, getFaceConfig(), longValue);
                }
            } catch (Exception e) {
                HVLogUtils.e(TAG, "capturePicture(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                this.hvImageCaptureError = new HVError(2, Utils.getErrorMessage(e));
                long longValue2 = this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureFailed(this.hvImageCaptureError, getFaceConfig(), longValue2);
                }
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    public void swapCamera() {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "swapCamera() called");
            try {
                ProgressDialog progressDialog = this.rotateDialog;
                if (progressDialog == null || !progressDialog.isShowing()) {
                    if (this.rotateDialog == null) {
                        this.rotateDialog = new ProgressDialog(getActivity());
                    }
                    getFaceConfig().setShouldUseBackCamera(!getFaceConfig().getShouldUseBackCamera());
                    this.rotateDialog.setCancelable(false);
                    this.rotateDialog.setMessage(requireActivity().getString(R.string.hv_please_wait));
                    if (canShowDialog()) {
                        this.rotateDialog.show();
                    }
                    HVMagicView hVMagicView = this.cameraView;
                    if (hVMagicView != null) {
                        hVMagicView.rotateCamera();
                    }
                }
            } catch (Exception e) {
                HVLogUtils.e(TAG, "swapCamera(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    public void closeTexture() {
        this.mPresenter.operationCancelled();
    }

    private boolean canShowDialog() {
        return isFragmentAdded() && !requireActivity().isFinishing();
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public long getVideoRecordedTime() {
        return this.timeTakenToVideoRecord;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public boolean isFragmentAdded() {
        return isAdded() && getActivity() != null;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void setFaceDetectionState(FaceConstants.FaceDetectionState faceDetectionState) {
        if (isFragmentAdded() && !this.isPaused) {
            HVLogUtils.d(TAG, "setFaceDetectionState() called with: faceDetectionState = [" + faceDetectionState + "]");
            switch (AnonymousClass12.$SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[faceDetectionState.ordinal()]) {
                case 1:
                    updateUI(true, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_FOUND, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_FOUND, LanguageHelper.CAPTURE_PIC));
                    return;
                case 2:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_NOT_FOUND, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_NOT_FOUND, LanguageHelper.PLACE_FACE));
                    return;
                case 3:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_MOVE_AWAY, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_MOVE_AWAY, LanguageHelper.MOVE_AWAY));
                    return;
                case 4:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_NOT_FOUND, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_NOT_FOUND, LanguageHelper.PLACE_FACE));
                    return;
                case 5:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_MULTIPLE_FACES, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_MULTIPLE_FACES, LanguageHelper.MULTIPLE_FACES));
                    return;
                case 6:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_LOOK_STRAIGHT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOOK_STRAIGHT, LanguageHelper.LOOK_STRAIGHT));
                    return;
                case 7:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, "faceCaptureStayStill", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_STAY_STILL, LanguageHelper.STAY_STILL));
                    return;
                case 8:
                    updateUI(false, TextConfigUtils.getText(this.customStrings, "faceCapturePhoneStraightTag", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_DEVICE_WRONG_ORIENTATION, LanguageHelper.PHONE_STRAIGHT));
                    return;
                case 9:
                    showLoader(true, "", HVLottieHelper.FACE_PROCESSING, HVLottieHelper.State.START, null);
                    updateUI(false, TextConfigUtils.getText(this.customStrings, CustomTextStringConst.FaceCaptureTextConfigs.FACE_LOADER_TITLE, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOADER_TITLE, getStringForID(R.string.hv_face_loader_title)));
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$12, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState;

        static {
            int[] iArr = new int[FaceConstants.FaceDetectionState.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState = iArr;
            try {
                iArr[FaceConstants.FaceDetectionState.FACE_DETECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.FACE_NOT_DETECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.FACE_TOO_CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.FACE_TOO_FAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.MULTIPLE_FACES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.FACE_NOT_STRAIGHT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.FACE_STAY_STILL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.PHONE_NOT_STRAIGHT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$face$FaceConstants$FaceDetectionState[FaceConstants.FaceDetectionState.SAVE_UPLOAD.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private void updateUI(final boolean z, final Spanned spanned) {
        HVLogUtils.d(TAG, "updateUI() called with: canCaptureSelfie = [" + z + "], statusText = [" + ((Object) spanned) + "]");
        MainUIThread.getInstance().post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TextureFragment.this.m527x52b9f262(z, spanned);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setUI, reason: merged with bridge method [inline-methods] */
    public void m527x52b9f262(boolean z, Spanned spanned) {
        int i;
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "setUI() called with: canCaptureSelfie = [" + z + "], statusText = [" + ((Object) spanned) + "]");
            if (z) {
                if (SDKInternalConfig.getInstance().isFaceDetectionOn()) {
                    i = R.color.face_capture_circle_success;
                } else {
                    i = R.color.hv_face_detection_turned_off;
                }
                CircularProgressBar circularProgressBar = this.progressBar;
                if (circularProgressBar != null) {
                    circularProgressBar.setProgressColor(getResources().getColor(i));
                }
                if (getFaceConfig().isShouldAutoCapture()) {
                    if (!this.isProgressBarAnimating) {
                        this.isProgressBarAnimating = true;
                        this.autoCaptureTimer.start();
                    }
                    if (this.safeToTakePicture && this.isPhoneHeldStraight) {
                        setStatusString(CustomTextStringConst.FaceCaptureTextConfigs.FACE_CAPTURE_AUTO_CAPTURE_WAIT, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_AUTO_CAPTURE_WAIT, LanguageHelper.AUTO_CAPTURE_WAIT);
                    }
                }
                enableCaptureButton();
            } else {
                CircularProgressBar circularProgressBar2 = this.progressBar;
                if (circularProgressBar2 != null) {
                    circularProgressBar2.setProgressColor(getResources().getColor(R.color.face_capture_circle_failure));
                }
                if (getFaceConfig().isShouldAutoCapture()) {
                    progressBarStopAnimation();
                }
                disableCaptureButton();
            }
            TextView textView = this.statusString;
            if (textView == null || spanned == null) {
                return;
            }
            textView.setText(spanned);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.View
    public void setShouldAllowActivityClose(boolean z) {
        if (isFragmentAdded()) {
            HVLogUtils.d(TAG, "setShouldAllowActivityClose() called with: shouldAllowActivityClose = [" + z + "]");
            ((HVFaceActivity) requireActivity()).setShouldAllowActivityClose(z);
        }
    }
}
