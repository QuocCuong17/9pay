package co.hyperverge.hypersnapsdk.liveness.ui.texturetracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hyperkyc.data.network.ApiAction;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.data.DataRepository;
import co.hyperverge.hypersnapsdk.data.DataSource;
import co.hyperverge.hypersnapsdk.helpers.ExifHelper;
import co.hyperverge.hypersnapsdk.helpers.FaceRetryHelper;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.HVActiveLiveness;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.helpers.ImageComparisonHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.helpers.SaveBitmapAsync;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.helpers.WaterMarkHelper;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.helpers.face.FaceDetectionListener;
import co.hyperverge.hypersnapsdk.helpers.face.MLKitFaceHelper;
import co.hyperverge.hypersnapsdk.helpers.face.NPDFaceHelper;
import co.hyperverge.hypersnapsdk.listeners.FaceCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.LocationProviderCallback;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract;
import co.hyperverge.hypersnapsdk.model.BaseResponse;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.model.LivenessResponse;
import co.hyperverge.hypersnapsdk.objects.HVBaseResponse;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.IPAddress;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService;
import co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoServiceImpl;
import co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.MainUIThread;
import co.hyperverge.hypersnapsdk.utils.threading.ThreadExecutor;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.tekartik.sqflite.Constant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TexturePresenter implements TextureContract.Presenter, FaceDetectionListener {
    FaceCaptureCompletionHandler completionHandler;
    public String fullImageUri;
    HVActiveLiveness hvActiveLiveness;
    private Long imageCaptureTimestamp;
    ImageComparisonHelper imageComparisonHelper;
    boolean isFaceDetected;
    boolean isMultipleFaces;
    boolean isSaving;
    private Location mLocation;
    TextureContract.View mView;
    boolean moveAway;
    DataRepository repository;
    long timeTaken;
    private String waterMarkCropImageUrl;
    private String waterMarkFullImageUrl;
    private final String TAG = getClass().getSimpleName();
    private final ArrayList<HVBaseResponse> retakeResponses = new ArrayList<>();
    HVFaceConfig.LivenessMode mode = HVFaceConfig.LivenessMode.TEXTURELIVENESS;
    String clientId = "";
    String imageUri = "";
    String retryAction = null;
    String retakeMessage = null;
    MediaMetadataRetriever metaDataRetriever = new MediaMetadataRetriever();
    private String videoUri = "";
    private String latLongString = "";
    boolean isFaceDetectedAtleastOnce = false;
    private boolean hasFaceDetectorTimedOut = false;
    private boolean isCheckingCamera = false;
    private final int CAMERA_CHECK_DELAY = 20;
    ThreadExecutor mExecutor = ThreadExecutor.getInstance();
    MainUIThread uiThread = MainUIThread.getInstance();
    boolean uiReady = true;
    Handler delayHandler = new Handler();
    private final SDKInternalConfig.FaceDetectionProcessor faceDetectionProcessor = SDKInternalConfig.getInstance().getFaceDetectionProcessor();
    private FaceConstants.FaceDetectionState faceDetectionState = FaceConstants.FaceDetectionState.FACE_TOO_FAR;
    HVFaceConfig faceConfig = new HVFaceConfig();

    public TexturePresenter() {
        MLKitFaceHelper.get();
        NPDFaceHelper.get();
        this.repository = DataRepository.getInstance();
        this.imageComparisonHelper = ImageComparisonHelper.get();
        this.completionHandler = CallbackProvider.get().injectFaceCaptureCallback();
    }

    public void setView(TextureContract.View view) {
        HVLogUtils.d(this.TAG, "setView() called with: view = [" + view + "]");
        this.mView = view;
        MLKitFaceHelper.get().setConfig(getFaceConfig(), view.getViewListener(), this);
        NPDFaceHelper.get().setConfig(getFaceConfig(), view.getViewListener(), this);
        if (getFaceConfig().isShouldCheckActiveLiveness()) {
            try {
                HVActiveLiveness hVActiveLiveness = HVActiveLiveness.get();
                this.hvActiveLiveness = hVActiveLiveness;
                hVActiveLiveness.setConfig(this.mView, getFaceConfig());
            } catch (Exception e) {
                HVLogUtils.e(this.TAG, "setView() with: view = [" + view + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(this.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceDetectionListener
    public void setFaceDetectionState(FaceConstants.FaceDetectionState faceDetectionState) {
        if (this.faceDetectionState == faceDetectionState || !SDKInternalConfig.getInstance().isFaceDetectionOn() || this.faceDetectionState == FaceConstants.FaceDetectionState.SAVE_UPLOAD) {
            return;
        }
        HVLogUtils.d(this.TAG, "setFaceDetectionState() called with: faceDetectionState = [" + faceDetectionState + "]");
        this.faceDetectionState = faceDetectionState;
        if (faceDetectionState == FaceConstants.FaceDetectionState.FACE_DETECTED) {
            this.isFaceDetectedAtleastOnce = true;
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation() && this.mLocation == null) {
                this.mView.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
                return;
            } else if (!this.mView.isPhoneHeldStraight()) {
                this.mView.setFaceDetectionState(FaceConstants.FaceDetectionState.PHONE_NOT_STRAIGHT);
                return;
            } else {
                this.mView.setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_DETECTED);
                return;
            }
        }
        this.mView.setFaceDetectionState(faceDetectionState);
    }

    public void setFaceConfig(HVFaceConfig hVFaceConfig) {
        this.faceConfig = hVFaceConfig;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void resetCounters() {
        HVLogUtils.d(this.TAG, "resetCounters() called");
        if (this.hvActiveLiveness != null) {
            this.mView.resetUI();
            this.hvActiveLiveness.resetCounters();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void resetTimer() {
        HVLogUtils.d(this.TAG, "resetTimer() called");
        HVActiveLiveness hVActiveLiveness = this.hvActiveLiveness;
        if (hVActiveLiveness != null) {
            hVActiveLiveness.resetTimer();
        }
    }

    public void setUiReady(boolean z) {
        this.uiReady = z;
    }

    public void setMode(HVFaceConfig.LivenessMode livenessMode) {
        HVLogUtils.d(this.TAG, "setMode() called with: mode = [" + livenessMode + "]");
        if (livenessMode != null) {
            this.mode = livenessMode;
        }
    }

    public void setClientId(String str) {
        HVLogUtils.d(this.TAG, "setClientId() called with: clientId = [" + str + "]");
        this.clientId = str;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void submitFrameForProcessing(CameraProperties cameraProperties) {
        Bitmap selfieImageBitmap;
        if (InternalToolUtils.isTestMode(this.mView.injectContext()) && (selfieImageBitmap = InternalToolUtils.getSelfieImageBitmap()) != null) {
            if (MLKitFaceHelper.get().processImage(selfieImageBitmap) != null) {
                setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_DETECTED);
                return;
            } else {
                setFaceDetectionState(FaceConstants.FaceDetectionState.FACE_NOT_DETECTED);
                return;
            }
        }
        if (SDKInternalConfig.getInstance().isFaceDetectionOn()) {
            int i = AnonymousClass4.$SwitchMap$co$hyperverge$hypersnapsdk$helpers$SDKInternalConfig$FaceDetectionProcessor[this.faceDetectionProcessor.ordinal()];
            if (i == 1) {
                NPDFaceHelper.get().processFrame(cameraProperties);
            } else if (i == 2) {
                MLKitFaceHelper.get().processFrame(cameraProperties);
            }
        }
        try {
            submitPreviewData(cameraProperties.getData(), cameraProperties.getRgbDataLength());
        } catch (OutOfMemoryError e) {
            HVLogUtils.e(this.TAG, e.getMessage(), e);
        }
    }

    /* renamed from: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$4, reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$helpers$SDKInternalConfig$FaceDetectionProcessor;

        static {
            int[] iArr = new int[SDKInternalConfig.FaceDetectionProcessor.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$helpers$SDKInternalConfig$FaceDetectionProcessor = iArr;
            try {
                iArr[SDKInternalConfig.FaceDetectionProcessor.NPD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$helpers$SDKInternalConfig$FaceDetectionProcessor[SDKInternalConfig.FaceDetectionProcessor.MLKIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public boolean isFacePresent() {
        HVLogUtils.d(this.TAG, "isFacePresent() called");
        if (getFaceConfig().isShouldCheckActiveLiveness()) {
            return this.hvActiveLiveness.isFaceDetected();
        }
        return this.faceDetectionState == FaceConstants.FaceDetectionState.FACE_DETECTED || !SDKInternalConfig.getInstance().isFaceDetectionOn();
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public boolean isFaceDetectedOnce() {
        HVLogUtils.d(this.TAG, "isFaceDetectedOnce() called");
        return this.isFaceDetectedAtleastOnce;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void setFaceDetectorTimedOut() {
        HVLogUtils.d(this.TAG, "setFaceDetectorTimedOut() called");
        this.faceDetectionState = FaceConstants.FaceDetectionState.FACE_DETECTED;
        this.hasFaceDetectorTimedOut = true;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void savePicture(byte[] bArr, byte[] bArr2, final String str, final String str2, String str3) {
        String transactionID;
        HVLogUtils.d(this.TAG, "savePicture() called with: data = [" + bArr + "], previewFrameData = [" + bArr2 + "], filePath = [" + str + "], sourceFileName = [" + str2 + "], videoUri = [" + str3 + "]");
        this.isSaving = true;
        final ExifHelper exifHelper = new ExifHelper();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(RemoteSettings.FORWARD_SLASH_STRING);
        sb.append(str2);
        exifHelper.readExif(bArr, sb.toString(), this.mLocation);
        this.videoUri = str3;
        try {
            File file = new File(str3);
            String nameWithoutExtension = FileHelper.getNameWithoutExtension(file);
            File file2 = new File(file.getParent(), nameWithoutExtension + "_final.mp4");
            Utils.trimVideo(str3, Uri.parse(file2.toString()).toString(), (long) (getFaceConfig().getNumberOfFrames() / getFaceConfig().getFps()));
            if (file.delete()) {
                file2.renameTo(new File(file.getParent(), nameWithoutExtension + ".mp4"));
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "savePicture(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        if (getFaceConfig().getHeaders() != null && getFaceConfig().getHeaders().has("transactionId")) {
            try {
                transactionID = getFaceConfig().getHeaders().getString("transactionId");
            } catch (JSONException e2) {
                Log.e(this.TAG, Utils.getErrorMessage(e2));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                }
                transactionID = "";
            }
        } else {
            transactionID = SPHelper.getTransactionID();
        }
        final String str4 = transactionID;
        this.mExecutor.execute(new SaveBitmapAsync(bArr, bArr2, str, str2, getFaceConfig(), str4, new SaveBitmapAsync.SaveCallBack() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter.1
            @Override // co.hyperverge.hypersnapsdk.helpers.SaveBitmapAsync.SaveCallBack
            public void onImageSaved(final String str5, final List<Integer> list, boolean z) {
                HVLogUtils.d(TexturePresenter.this.TAG, "onImageSaved() called with: croppedFilePath = [" + str5 + "], faceCoords = [" + list + "], isFaceDetectorExcluded = [" + z + "]");
                TexturePresenter.this.imageCaptureTimestamp = Long.valueOf(System.currentTimeMillis());
                TexturePresenter.this.mView.onPictureSaved(str5);
                TexturePresenter.this.imageUri = str5;
                TexturePresenter.this.fullImageUri = str + RemoteSettings.FORWARD_SLASH_STRING + str2;
                if (z) {
                    TexturePresenter texturePresenter = TexturePresenter.this;
                    texturePresenter.imageUri = texturePresenter.fullImageUri;
                }
                if (TexturePresenter.this.imageUri == null && (TexturePresenter.this.getFaceConfig().isShouldCheckActiveLiveness() || TexturePresenter.this.getFaceConfig().shouldCheckForFaceTilt())) {
                    TexturePresenter.this.isSaving = false;
                    TexturePresenter.this.mView.resumeCamera();
                    if (TexturePresenter.this.getFaceConfig().shouldCheckForFaceTilt()) {
                        TexturePresenter texturePresenter2 = TexturePresenter.this;
                        texturePresenter2.onFailureResponse(texturePresenter2.getError(24, "Tilted face captured. Hold phone straight and face straight to the camera"), null);
                        return;
                    } else {
                        if (TexturePresenter.this.hvActiveLiveness != null) {
                            TexturePresenter texturePresenter3 = TexturePresenter.this;
                            texturePresenter3.faceDetectionState = texturePresenter3.hvActiveLiveness.isFaceDetected() ? FaceConstants.FaceDetectionState.FACE_DETECTED : FaceConstants.FaceDetectionState.FACE_NOT_DETECTED;
                            return;
                        }
                        return;
                    }
                }
                TexturePresenter.this.checkForWaterMark();
                if (!SDKInternalConfig.getInstance().getRemoteConfig().isUseIpToGeo()) {
                    TexturePresenter.this.moveOnFromFaceCapture(str, str2, str5, list, null, exifHelper, str4);
                } else {
                    new IPToGeoServiceImpl().performIp2GeoAddress(new IPToGeoService.IPToGeoCallback() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter.1.1
                        @Override // co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService.IPToGeoCallback
                        public void onSuccess(IPAddress iPAddress) {
                            HVLogUtils.d(TexturePresenter.this.TAG, "IPToGeoCallback.onSuccess() called with: ipAddress = [" + iPAddress + "]");
                            TexturePresenter.this.moveOnFromFaceCapture(str, str2, str5, list, iPAddress, exifHelper, str4);
                        }

                        @Override // co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService.IPToGeoCallback
                        public void onError() {
                            HVLogUtils.d(TexturePresenter.this.TAG, "IPToGeoCallback.onError() called");
                            TexturePresenter.this.moveOnFromFaceCapture(str, str2, str5, list, null, exifHelper, str4);
                        }
                    });
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveOnFromFaceCapture(String str, String str2, String str3, List<Integer> list, IPAddress iPAddress, ExifHelper exifHelper, String str4) {
        HVLogUtils.d(this.TAG, "moveOnFromFaceCapture() called with: filePath = [" + str + "], fileName = [" + str2 + "], croppedFilePath = [" + str3 + "], faceCoords = [" + list + "], ipAddress = [" + iPAddress + "], helper = [" + exifHelper + "], transactionID = [" + str4 + "]");
        if (this.mView.isFragmentAdded()) {
            exifHelper.writeExifData(this.imageUri, str4, iPAddress);
            exifHelper.writeExifData(this.fullImageUri, str4, iPAddress);
            if (getFaceConfig().isShouldAddWaterMark()) {
                exifHelper.writeExifData(this.waterMarkFullImageUrl, str4, iPAddress);
                exifHelper.writeExifData(this.waterMarkCropImageUrl, str4, iPAddress);
            }
            if (getFaceConfig().isShouldRecordVideo()) {
                long videoDuration = Utils.getVideoDuration(this.videoUri, this.mView.injectContext(), this.metaDataRetriever);
                if (videoDuration == 0) {
                    this.videoUri = null;
                    HVError hVError = new HVError(2, "videoDuration is 0 ms");
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieVideoRecordFailed(hVError, this.mView.getVideoRecordedTime());
                    }
                } else if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieVideoRecordSuccessful(this.videoUri, videoDuration, this.mView.getVideoRecordedTime());
                }
            }
            if (this.imageUri == null) {
                if (getFaceConfig().shouldRetryIfFaceNotPresent()) {
                    int attemptsCountForFaceNotPresent = SPHelper.getAttemptsCountForFaceNotPresent();
                    if (attemptsCountForFaceNotPresent < 0) {
                        this.delayHandler.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda9
                            @Override // java.lang.Runnable
                            public final void run() {
                                TexturePresenter.this.m537xadbb78a2();
                            }
                        });
                    } else {
                        int i = attemptsCountForFaceNotPresent + 1;
                        SPHelper.setAttemptsCountForFaceNotPresent(i);
                        if (i < getFaceConfig().getMaxAttemptsForFaceNotPresent()) {
                            startRetakeScreen(str + RemoteSettings.FORWARD_SLASH_STRING + str2, this.mView.getRetakeMessageForFaceNotPresentInCapturedImage());
                        } else {
                            this.delayHandler.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda10
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TexturePresenter.this.m538x30062d81();
                                }
                            });
                        }
                    }
                } else {
                    this.delayHandler.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda11
                        @Override // java.lang.Runnable
                        public final void run() {
                            TexturePresenter.this.m539xb250e260();
                        }
                    });
                }
            } else if (this.mode == HVFaceConfig.LivenessMode.NONE) {
                m540x56d9ec2(null, null);
            } else if (getFaceConfig().isShouldCheckActiveLiveness()) {
                HVActiveLiveness hVActiveLiveness = this.hvActiveLiveness;
                if (hVActiveLiveness != null) {
                    if (hVActiveLiveness.saveImage(str + RemoteSettings.FORWARD_SLASH_STRING + str2)) {
                        this.mView.pauseCamera();
                        uploadImage(str + RemoteSettings.FORWARD_SLASH_STRING + str2, str3, list, null);
                    } else {
                        this.faceDetectionState = this.hvActiveLiveness.isFaceDetected() ? FaceConstants.FaceDetectionState.FACE_DETECTED : FaceConstants.FaceDetectionState.FACE_NOT_DETECTED;
                        this.mView.resumeCamera();
                    }
                }
            } else {
                uploadImage(str + RemoteSettings.FORWARD_SLASH_STRING + str2, str3, list, this.videoUri);
            }
            this.isSaving = false;
        }
    }

    /* renamed from: lambda$moveOnFromFaceCapture$0$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m537xadbb78a2() {
        onFailureResponse(getError(2, this.mView.getStringForID(R.string.internal_error)), null);
    }

    /* renamed from: lambda$moveOnFromFaceCapture$1$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m538x30062d81() {
        onFailureResponse(getError(23, this.mView.getStringForID(R.string.blurry_face_detection_error)), null);
    }

    /* renamed from: lambda$moveOnFromFaceCapture$2$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m539xb250e260() {
        onFailureResponse(getError(23, this.mView.getStringForID(R.string.blurry_face_detection_error)), null);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void operationCancelled() {
        HVLogUtils.d(this.TAG, "operationCancelled() called");
        this.mView.stopCamera();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieScreenClosedByUser(getFaceConfig());
        }
        HVError error = getError(3, this.mView.getStringForID(R.string.operation_cancelled));
        if (getFaceConfig().isShouldShowInstructionPage()) {
            m532xcaacb122(error, null);
        } else {
            m540x56d9ec2(null, error);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void closeOnLowStorage() {
        HVLogUtils.d(this.TAG, "closeOnLowStorage() called");
        m540x56d9ec2(null, getError(25, this.mView.getStringForID(R.string.storage_error)));
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void faceCaptureError(HVError hVError) {
        HVLogUtils.d(this.TAG, "faceCaptureError() called with: hvError = [" + hVError + "]");
        m540x56d9ec2(null, hVError);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void faceCaptureTimeout() {
        HVLogUtils.d(this.TAG, "faceCaptureTimeout() called");
        m540x56d9ec2(null, getError(35, this.mView.getStringForID(R.string.face_capture_timeout)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeJSON(JSONObject jSONObject) {
        JSONObject selfieLivenessResult = InternalToolUtils.getSelfieLivenessResult();
        if (jSONObject == null || selfieLivenessResult == null) {
            return;
        }
        Iterator<String> keys = selfieLivenessResult.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                if (jSONObject.has(next)) {
                    jSONObject.remove(next);
                    jSONObject.put(next, selfieLivenessResult.get(next));
                }
            } catch (JSONException e) {
                HVLogUtils.e(this.TAG, "getLivenessTextureScore: onSuccess: could not put 'selfieLivenessResult' object inside 'resultInternal'", e);
            }
        }
    }

    public void uploadImage(final String str, String str2, List<Integer> list, String str3) {
        HVLogUtils.d(this.TAG, "uploadImage() called with: filePath = [" + str + "], croppedFacePath = [" + str2 + "], faceCoords = [" + list + "], videoUri = [" + str3 + "]");
        this.mView.showLoader(true, "", HVLottieHelper.FACE_PROCESSING, HVLottieHelper.State.START, null);
        final TimingUtils timingUtils = new TimingUtils();
        if (!StringUtils.isEmptyOrNull(SPHelper.getTransactionID())) {
            FaceRetryHelper.get().setRetryLivenessParamsAndHeaders(getFaceConfig());
        }
        try {
            if (getFaceConfig().getHeaders() != null) {
                JSONObject headers = getFaceConfig().getHeaders();
                headers.put("sdk-mode", InternalToolUtils.getSdkMode(this.mView.injectContext()));
                getFaceConfig().setLivenessAPIHeaders(headers);
            }
        } catch (JSONException e) {
            HVLogUtils.e(this.TAG, "uploadImage(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        this.mView.setShouldAllowActivityClose(false);
        this.repository.getLivenessTextureScore(this.mView.injectContext(), str, str2, str3, list, getFaceConfig(), new DataSource.NetworkCallback() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter.2
            @Override // co.hyperverge.hypersnapsdk.data.DataSource.NetworkCallback
            public void onSuccess(BaseResponse baseResponse) {
                HVError error;
                HVLogUtils.d(TexturePresenter.this.TAG, "getLivenessTextureScore.onSuccess() called with: response = [" + baseResponse + "]");
                TexturePresenter.this.mView.setShouldAllowActivityClose(true);
                Log.d(TexturePresenter.this.TAG, "onSuccess() called with: response = [" + baseResponse + "]");
                TexturePresenter.this.timeTaken = timingUtils.getTimeDifferenceLong().longValue();
                TexturePresenter.this.faceDetectionState = FaceConstants.FaceDetectionState.FACE_NOT_DETECTED;
                LivenessResponse livenessResponse = (LivenessResponse) baseResponse;
                if (InternalToolUtils.isTestMode(TexturePresenter.this.mView.injectContext())) {
                    TexturePresenter.this.mergeJSON(livenessResponse.getResponse());
                }
                if (livenessResponse.getErrorCode() == 18) {
                    TexturePresenter.this.onFailureResponse(TexturePresenter.this.getError(livenessResponse.getErrorCode(), livenessResponse.getLivenessError()), livenessResponse);
                    return;
                }
                if (baseResponse != null && baseResponse.getHttpStatusCode() != null) {
                    int intValue = baseResponse.getHttpStatusCode().intValue();
                    if (intValue == 200 || intValue == 422) {
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieAPIResponseReceived(livenessResponse, str, timingUtils.getTimeDifferenceLong().longValue());
                        }
                        TexturePresenter.this.processServerResponse(livenessResponse, str);
                        return;
                    }
                    if (livenessResponse.getLivenessError() != null) {
                        error = TexturePresenter.this.getError(livenessResponse.getErrorCode(), livenessResponse.getLivenessError());
                    } else {
                        error = TexturePresenter.this.getError(14, "Internal server error has occurred.");
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieAPICallFailed(null, error);
                    }
                    TexturePresenter.this.onFailureResponse(error, livenessResponse);
                    return;
                }
                HVError error2 = TexturePresenter.this.getError(14, "Internal server error has occurred.");
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieAPICallFailed(null, error2);
                }
                TexturePresenter.this.onFailureResponse(error2, null);
            }

            @Override // co.hyperverge.hypersnapsdk.data.DataSource.NetworkCallback
            public void onFailure(int i, String str4) {
                HVError error;
                HVLogUtils.d(TexturePresenter.this.TAG, "onFailure() called with: errorCode = [" + i + "], message = [" + str4 + "]");
                try {
                    TexturePresenter.this.mView.setShouldAllowActivityClose(true);
                    if (str4 != null && str4.contains("Certificate pinning")) {
                        TexturePresenter texturePresenter = TexturePresenter.this;
                        error = texturePresenter.getError(15, texturePresenter.mView.getStringForID(R.string.ssl_error));
                    } else {
                        error = TexturePresenter.this.getError(i, str4);
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieAPICallFailed(null, error);
                    }
                    TexturePresenter.this.onFailureResponse(error, null);
                } catch (Exception e2) {
                    HVLogUtils.e(TexturePresenter.this.TAG, "getLivenessTextureScore.onFailure(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
                    Log.e(TexturePresenter.this.TAG, Utils.getErrorMessage(e2));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                    }
                }
            }

            @Override // co.hyperverge.hypersnapsdk.data.DataSource.NetworkCallback
            public void onNetworkFailure() {
                HVLogUtils.d(TexturePresenter.this.TAG, "onNetworkFailure() called");
                TexturePresenter.this.mView.setShouldAllowActivityClose(true);
                TexturePresenter texturePresenter = TexturePresenter.this;
                HVError error = texturePresenter.getError(12, texturePresenter.mView.getStringForID(R.string.network_error));
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieAPICallFailed(null, error);
                }
                TexturePresenter.this.onFailureResponse(error, null);
            }
        });
    }

    public void onFailureResponse(final HVError hVError, final LivenessResponse livenessResponse) {
        HVLogUtils.d(this.TAG, "onFailureResponse() called with: error = [" + hVError + "], response = [" + livenessResponse + "]");
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logLivenessAPIFailed(hVError, livenessResponse, getFaceConfig());
        }
        this.mView.showLoader(true, "", null, HVLottieHelper.State.TRANSITION, new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda6
            @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
            public final void onEnd() {
                TexturePresenter.this.m541x87b853a1(livenessResponse, hVError);
            }
        });
    }

    /* renamed from: lambda$onFailureResponse$4$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m541x87b853a1(final LivenessResponse livenessResponse, final HVError hVError) {
        this.mView.showLoader(true, "", HVLottieHelper.FACE_FAILURE, HVLottieHelper.State.END, new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda5
            @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
            public final void onEnd() {
                TexturePresenter.this.m540x56d9ec2(livenessResponse, hVError);
            }
        });
    }

    public void processServerResponse(LivenessResponse livenessResponse, String str) {
        HVLogUtils.d(this.TAG, "processServerResponse() called with: livenessResponse = [" + livenessResponse + "], filePath = [" + str + "]");
        try {
            checkForRetryLogic(livenessResponse, str);
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "processServerResponse() with: livenessResponse = [" + livenessResponse + "], filePath = [" + str + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void showDialog(final boolean z, final String str) {
        if (this.mView.isFragmentAdded()) {
            HVLogUtils.d(this.TAG, "showDialog() called with: shouldShow = [" + z + "], progressLoaderText = [" + str + "]");
            this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    TexturePresenter.this.m543xdd2ce6ad(z, str);
                }
            });
        }
    }

    /* renamed from: lambda$showDialog$5$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m543xdd2ce6ad(boolean z, String str) {
        if (getFaceConfig().getCustomLoaderClass() == null) {
            this.mView.shouldShowProgress(z, str);
            return;
        }
        if (z) {
            try {
                ((Activity) this.mView.injectContext()).startActivityForResult(new Intent(this.mView.injectContext(), Class.forName(getFaceConfig().getCustomLoaderClass())), 87);
                return;
            } catch (ClassNotFoundException e) {
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    return;
                }
                return;
            }
        }
        ((Activity) this.mView.injectContext()).finishActivity(87);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void waitForUI(boolean z) {
        this.hvActiveLiveness.waitForUI(z);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public FaceConstants.FaceDetectionState getFaceDetectionState() {
        HVLogUtils.d(this.TAG, "getFaceDetectionState() called");
        HVLogUtils.d(this.TAG, "getFaceDetectionState: returning " + this.faceDetectionState);
        return this.faceDetectionState;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void setFaceDetectionStateFromView(final FaceConstants.FaceDetectionState faceDetectionState) {
        this.faceDetectionState = faceDetectionState;
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                TexturePresenter.this.m542xe2f90251(faceDetectionState);
            }
        });
    }

    /* renamed from: lambda$setFaceDetectionStateFromView$6$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m542xe2f90251(FaceConstants.FaceDetectionState faceDetectionState) {
        this.mView.setFaceDetectionState(faceDetectionState);
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public Boolean canResumeCamera() {
        return Boolean.valueOf(this.faceDetectionState != FaceConstants.FaceDetectionState.SAVE_UPLOAD);
    }

    private HVBaseResponse createHVResponse(LivenessResponse livenessResponse, boolean z) {
        String str;
        Integer num;
        HVBaseResponse hVResponse;
        HVActiveLiveness hVActiveLiveness;
        HVLogUtils.d(this.TAG, "createHVResponse() called with: livenessResponse = [" + livenessResponse + "], shouldReturnBaseResponse = [" + z + "]");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = null;
        if (livenessResponse != null) {
            if (livenessResponse.getResponse() != null) {
                jSONObject = livenessResponse.getResponse();
                if (this.mode == HVFaceConfig.LivenessMode.TEXTURELIVENESS && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logLivenessAPISuccessful(livenessResponse, getFaceConfig(), this.timeTaken);
                }
            }
            jSONObject2 = livenessResponse.getHeaders();
            num = livenessResponse.getHttpStatusCode();
            str = livenessResponse.getStatusMessage();
        } else {
            str = null;
            num = null;
        }
        if (z) {
            hVResponse = new HVBaseResponse(jSONObject, jSONObject2, this.imageUri, this.retryAction);
        } else {
            hVResponse = new HVResponse(jSONObject, jSONObject2, this.imageUri, this.retryAction);
        }
        Location location = this.mLocation;
        if (location != null) {
            hVResponse.setLatitude(Double.valueOf(location.getLatitude()));
            hVResponse.setLongitude(Double.valueOf(this.mLocation.getLongitude()));
        }
        Long l = this.imageCaptureTimestamp;
        if (l != null) {
            hVResponse.setSubmittedTimestamp(l);
        }
        hVResponse.setRetakeMessage(this.retakeMessage);
        hVResponse.setAttemptsCount(SPHelper.getAttemptsCountForImage(getFaceConfig().getLivenessEndpoint(), ""));
        if (getFaceConfig().isShouldReturnFullImageUrl()) {
            hVResponse.setFullImageURI(this.fullImageUri);
        } else if (!StringUtils.isEmptyOrNull(this.fullImageUri) && !this.fullImageUri.equals(this.imageUri)) {
            new File(this.fullImageUri).delete();
        }
        if (getFaceConfig().isShouldAddWaterMark() && HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation()) {
            hVResponse.setWaterMarkFullImageUri(this.waterMarkFullImageUrl);
            hVResponse.setWaterMarkCroppedImageUri(this.waterMarkCropImageUrl);
        }
        if (getFaceConfig().isShouldCheckActiveLiveness() && (hVActiveLiveness = this.hvActiveLiveness) != null && hVActiveLiveness.getSavedImagePaths() != null) {
            hVResponse.setGestureLivenessImageUrls((HashMap) this.hvActiveLiveness.getSavedImagePaths());
        }
        hVResponse.setVideoUri(this.videoUri);
        hVResponse.setStatusMessage(str);
        hVResponse.setStatusCode(num);
        Log.d("response before sending", hVResponse.toString());
        return hVResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HVFaceConfig getFaceConfig() {
        HVFaceConfig hVFaceConfig = this.faceConfig;
        if (hVFaceConfig != null) {
            return hVFaceConfig;
        }
        m540x56d9ec2(null, getError(2, this.mView.getStringForID(R.string.face_config_error)));
        return new HVFaceConfig();
    }

    /* renamed from: sendResponse, reason: merged with bridge method [inline-methods] */
    public void m540x56d9ec2(LivenessResponse livenessResponse, HVError hVError) {
        HVLogUtils.d(this.TAG, "sendResponse() called with: livenessResponse = [" + livenessResponse + "], error = [" + hVError + "]");
        if (this.completionHandler != null) {
            try {
                try {
                    this.mView.stopCamera();
                } catch (Exception e) {
                    Log.e(this.TAG, Utils.getErrorMessage(e));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                }
                HVResponse hVResponse = (HVResponse) createHVResponse(livenessResponse, false);
                hVResponse.setRetakeAttemptResponses(this.retakeResponses);
                if (!HyperSnapSDK.getFaceCaptureMetaData().isFaceDetected()) {
                    HyperSnapSDK.getFaceCaptureMetaData().setFaceDetected(this.isFaceDetectedAtleastOnce);
                }
                if (this.hasFaceDetectorTimedOut && getFaceConfig().isFaceDetectionDisabled()) {
                    SDKInternalConfig.getInstance().setFaceDetectionOn(true);
                }
                m532xcaacb122(hVError, hVResponse);
            } catch (Exception e2) {
                Log.e(this.TAG, Utils.getErrorMessage(e2));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkCameraAndHandleCompletion, reason: merged with bridge method [inline-methods] */
    public void m532xcaacb122(final HVError hVError, final HVResponse hVResponse) {
        HVLogUtils.d(this.TAG, "checkCameraAndHandleCompletion() called with: error = [" + hVError + "], hvResponse = [" + hVResponse + "]");
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TexturePresenter.this.m533x4cf76601(hVError, hVResponse);
            }
        });
    }

    /* renamed from: lambda$checkCameraAndHandleCompletion$8$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m533x4cf76601(final HVError hVError, final HVResponse hVResponse) {
        if (this.isCheckingCamera) {
            HVLogUtils.d(this.TAG, "checkCameraAndHandleCompletion: already checking");
            return;
        }
        if (CameraEngine.isCameraReleased()) {
            this.isCheckingCamera = true;
            HVLogUtils.d(this.TAG, "checkCameraAndHandleCompletion: camera is released");
            int i = (hVError == null || hVError.getErrorCode() != 3) ? -1 : 0;
            if (i == -1 || !getFaceConfig().isShouldShowInstructionPage()) {
                this.mView.callCompletionHandler(hVError, hVResponse);
            }
            finishView(i);
            return;
        }
        HVLogUtils.d(this.TAG, "checkCameraAndHandleCompletion: camera is not released");
        new Handler().postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TexturePresenter.this.m532xcaacb122(hVError, hVResponse);
            }
        }, 20L);
    }

    public JSONObject getJSONForRetry(LivenessResponse livenessResponse) {
        HVLogUtils.d(this.TAG, "getJSONForRetry() called with: livenessResponse = [" + livenessResponse + "]");
        JSONObject jSONObject = new JSONObject();
        if (livenessResponse.getResponse() != null) {
            return livenessResponse.getResponse();
        }
        try {
            JSONObject headers = livenessResponse.getHeaders();
            if (!headers.has(AppConstants.RAW_RESPONSE)) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject(headers.getString(AppConstants.RAW_RESPONSE));
            return jSONObject2.has(Constant.PARAM_RESULT) ? jSONObject2 : jSONObject;
        } catch (JSONException e) {
            HVLogUtils.e(this.TAG, "getJSONForRetry(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return jSONObject;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return jSONObject;
        }
    }

    public void checkForRetryLogic(final LivenessResponse livenessResponse, final String str) {
        HVLogUtils.d(this.TAG, "checkForRetryLogic() called with: livenessResponse = [" + livenessResponse + "], filePath = [" + str + "]");
        FaceRetryHelper.get().checkForRetakeMessage(getJSONForRetry(livenessResponse), getFaceConfig(), new FaceRetryHelper.FaceRetryListener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda0
            @Override // co.hyperverge.hypersnapsdk.helpers.FaceRetryHelper.FaceRetryListener
            public final void onResult(boolean z, String str2, String str3, HVError hVError) {
                TexturePresenter.this.m535x3cdb2610(livenessResponse, str, z, str2, str3, hVError);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0019, code lost:
    
        if (r20.equals("manualReview") == false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x001b, code lost:
    
        if (r21 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002d, code lost:
    
        if (r16.getHttpStatusCode().intValue() == 200) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002f, code lost:
    
        r0 = true;
     */
    /* renamed from: lambda$checkForRetryLogic$11$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ void m535x3cdb2610(final LivenessResponse livenessResponse, final String str, final boolean z, final String str2, String str3, final HVError hVError) {
        TextureContract.View view;
        boolean z2;
        String str4;
        HVLottieHelper.State state;
        HVLottieHelper.Listener listener;
        this.retryAction = str3;
        this.retakeMessage = str2;
        try {
            if (str3 != null) {
                try {
                    if (!str3.equals(ApiAction.PASS)) {
                    }
                } catch (Exception e) {
                    String str5 = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("checkForRetryLogic() with: livenessResponse = [");
                    try {
                        sb.append(livenessResponse);
                        sb.append("], filePath = [");
                        try {
                            sb.append(str);
                            sb.append("]: exception = [");
                            sb.append(Utils.getErrorMessage(e));
                            sb.append("]");
                            HVLogUtils.e(str5, sb.toString(), e);
                            Log.e(this.TAG, "checkForRetryLogic: " + Utils.getErrorMessage(e));
                            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                            }
                            view = this.mView;
                            z2 = true;
                            str4 = null;
                            state = HVLottieHelper.State.TRANSITION;
                            final String str6 = HVLottieHelper.FACE_FAILURE;
                            listener = new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda7
                                @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
                                public final void onEnd() {
                                    TexturePresenter.this.m534xba907131(str6, z, livenessResponse, str, str2, hVError);
                                }
                            };
                        } catch (Throwable th) {
                            th = th;
                            TextureContract.View view2 = this.mView;
                            HVLottieHelper.State state2 = HVLottieHelper.State.TRANSITION;
                            final String str7 = HVLottieHelper.FACE_FAILURE;
                            view2.showLoader(true, "", null, state2, new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda7
                                @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
                                public final void onEnd() {
                                    TexturePresenter.this.m534xba907131(str7, z, livenessResponse, str, str2, hVError);
                                }
                            });
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        TextureContract.View view22 = this.mView;
                        HVLottieHelper.State state22 = HVLottieHelper.State.TRANSITION;
                        final String str72 = HVLottieHelper.FACE_FAILURE;
                        view22.showLoader(true, "", null, state22, new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda7
                            @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
                            public final void onEnd() {
                                TexturePresenter.this.m534xba907131(str72, z, livenessResponse, str, str2, hVError);
                            }
                        });
                        throw th;
                    }
                }
            }
            if (livenessResponse.getHttpStatusCode() != null) {
            }
            boolean z3 = false;
            final String str8 = z3 ? HVLottieHelper.FACE_SUCCESS : HVLottieHelper.FACE_FAILURE;
            view = this.mView;
            z2 = true;
            str4 = null;
            state = HVLottieHelper.State.TRANSITION;
            listener = new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda7
                @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
                public final void onEnd() {
                    TexturePresenter.this.m534xba907131(str8, z, livenessResponse, str, str2, hVError);
                }
            };
            view.showLoader(z2, "", str4, state, listener);
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: lambda$checkForRetryLogic$10$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m534xba907131(String str, final boolean z, final LivenessResponse livenessResponse, final String str2, final String str3, final HVError hVError) {
        this.mView.showLoader(true, "", str, HVLottieHelper.State.END, new HVLottieHelper.Listener() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda8
            @Override // co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.Listener
            public final void onEnd() {
                TexturePresenter.this.m536xc02eaf7(z, livenessResponse, str2, str3, hVError);
            }
        });
    }

    /* renamed from: lambda$checkForRetryLogic$9$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m536xc02eaf7(boolean z, LivenessResponse livenessResponse, String str, String str2, HVError hVError) {
        if (z) {
            this.retakeResponses.add(createHVResponse(livenessResponse, true));
            startRetakeScreen(str, str2);
            return;
        }
        m540x56d9ec2(livenessResponse, hVError);
    }

    private void startRetakeScreen(String str, String str2) {
        HVLogUtils.d(this.TAG, "startRetakeScreen() called with: filePath = [" + str + "], message = [" + str2 + "]");
        this.faceDetectionState = SDKInternalConfig.getInstance().isFaceDetectionOn() ? FaceConstants.FaceDetectionState.FACE_NOT_DETECTED : FaceConstants.FaceDetectionState.FACE_DETECTED;
        this.imageCaptureTimestamp = null;
        Intent intent = new Intent(this.mView.injectContext(), (Class<?>) HVRetakeActivity.class);
        intent.putExtra(HVRetakeActivity.IMAGE_URI_TAG, str);
        intent.putExtra(HVRetakeActivity.RETRY_MESSAGE_TAG, str2);
        intent.putExtra(HVRetakeActivity.CONFIG_TAG, getFaceConfig());
        intent.putExtra("face", true);
        intent.putExtra(HVRetakeActivity.CALLING_ACTIVITY_TAG, HVRetakeActivity.FACE_CALLING_ACTIVITY_VALUE);
        intent.putExtra(HVRetakeActivity.RADIUS_TAG, this.mView.getViewListener().getViewRadius());
        ((Activity) this.mView.injectContext()).startActivityForResult(intent, 1);
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                TexturePresenter.this.m544x2dabbf46();
            }
        });
    }

    /* renamed from: lambda$startRetakeScreen$12$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m544x2dabbf46() {
        this.mView.resetLoader();
    }

    public void finishView(int i) {
        HVLogUtils.d(this.TAG, "finishView() called with: resultCode = [" + i + "]");
        try {
            this.imageComparisonHelper.destroy();
            FaceRetryHelper.destroy();
            MLKitFaceHelper.get().destroy();
            NPDFaceHelper.get().destroy();
            LocationServiceImpl.destroy();
            HVActiveLiveness hVActiveLiveness = this.hvActiveLiveness;
            if (hVActiveLiveness != null) {
                hVActiveLiveness.destroy();
            }
            this.mView.finishTextureView(i);
            MediaMetadataRetriever mediaMetadataRetriever = this.metaDataRetriever;
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
            this.completionHandler = null;
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "finishView(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public HVError getError(int i, String str) {
        HVLogUtils.d(this.TAG, "getError() called with: errorCode = [" + i + "], errorMsg = [" + str + "]");
        return new HVError(i, str);
    }

    @Override // co.hyperverge.hypersnapsdk.utils.BasePresenter
    public void start() {
        HVLogUtils.d(this.TAG, "start() called");
        getLocation();
        if (SDKInternalConfig.getInstance().isFaceDetectionOn()) {
            return;
        }
        this.faceDetectionState = FaceConstants.FaceDetectionState.FACE_DETECTED;
    }

    @Override // co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract.Presenter
    public void resume() {
        HVLogUtils.d(this.TAG, "resume() called");
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation()) {
            getLocation();
        }
    }

    /* renamed from: lambda$submitPreviewData$13$co-hyperverge-hypersnapsdk-liveness-ui-texturetracker-TexturePresenter, reason: not valid java name */
    public /* synthetic */ void m545xc8310237(byte[] bArr, long j) {
        this.imageComparisonHelper.compareConsecutiveFrames(bArr, j);
    }

    public void submitPreviewData(final byte[] bArr, final long j) {
        this.mExecutor.execute(new Runnable() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                TexturePresenter.this.m545xc8310237(bArr, j);
            }
        });
    }

    private void getLocation() {
        try {
            HVLogUtils.d(this.TAG, "getLocation() called");
            LocationServiceImpl.getInstance(this.mView.injectContext()).startLocationUpdates();
            LocationServiceImpl.getInstance(this.mView.injectContext()).addLocationCallback(new LocationProviderCallback() { // from class: co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter.3
                @Override // co.hyperverge.hypersnapsdk.listeners.LocationProviderCallback
                public void onResult(Location location) {
                    if (location != null) {
                        TexturePresenter.this.mLocation = location;
                        TexturePresenter.this.latLongString = location.getLatitude() + ", " + location.getLongitude();
                        return;
                    }
                    TexturePresenter texturePresenter = TexturePresenter.this;
                    texturePresenter.mLocation = LocationServiceImpl.getInstance(texturePresenter.mView.injectContext()).getLastKnownLocation();
                    TexturePresenter.this.latLongString = TexturePresenter.this.mLocation.getLatitude() + ", " + TexturePresenter.this.mLocation.getLongitude();
                }
            });
        } catch (NoClassDefFoundError e) {
            HVLogUtils.e(this.TAG, "getLocation(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, "gms excluded");
        }
    }

    public void checkForWaterMark() {
        HVLogUtils.d(this.TAG, "checkForWaterMark() called");
        if (getFaceConfig().isShouldAddWaterMark() && HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation()) {
            if (this.fullImageUri != null) {
                this.waterMarkFullImageUrl = WaterMarkHelper.get().addWaterMarkOnFullImage(this.mView.injectContext(), getFaceConfig(), this.latLongString, this.fullImageUri, false);
            }
            if (this.imageUri != null) {
                this.waterMarkCropImageUrl = WaterMarkHelper.get().addWaterMarkOnFullImage(this.mView.injectContext(), getFaceConfig(), this.latLongString, this.imageUri, true);
            }
        }
    }
}
