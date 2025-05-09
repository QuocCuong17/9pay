package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.ExoPlayer;
import co.hyperverge.hvcamera.HVCamHost;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hvcamera.magicfilter.utils.Exif;
import co.hyperverge.hyperdocdetect.carddetectorservice.HVTfliteHelper;
import co.hyperverge.hyperdocdetect.carddetectorservice.models.HVCardDetectionResult;
import co.hyperverge.hyperdocdetect.carddetectorservice.models.HVCardDetectorInput;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.activities.HVDocsActivity;
import co.hyperverge.hypersnapsdk.data.models.FeatureConfig;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.DocOCRHelper;
import co.hyperverge.hypersnapsdk.helpers.ExifHelper;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.HVCameraHelper;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.helpers.ImageToPDFConverter;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.listeners.DocCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.LocationProviderCallback;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVBaseResponse;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.objects.IPAddress;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService;
import co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoServiceImpl;
import co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl;
import co.hyperverge.hypersnapsdk.service.qr.HVBarcodeDetector;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import co.hyperverge.hypersnapsdk.utils.PDFUtils;
import co.hyperverge.hypersnapsdk.utils.PermissionManager;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.views.CrossHairView;
import co.hyperverge.hypersnapsdk.views.RectPortHoleView;
import co.hyperverge.hypersnapsdk.views.ScanningIndicator;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVDocsActivity extends HVBaseActivity implements View.OnClickListener {
    private static final int LOCATION_SETTINGS_REQUEST_CODE = 1001;
    public static String LOG_TAG = "DocumentActivity";
    private static final int RC_PICK_DOC = 1000;
    private static final String TAG = "co.hyperverge.hypersnapsdk.activities.HVDocsActivity";
    private CountDownTimer autoCaptureTimer;
    View blackOverlay;
    ImageView btCapture;
    private int camViewHeight;
    private int camViewWidth;
    FrameLayout cameraContainer;
    private AtomicBoolean cameraFree;
    HVMagicView cameraView;
    private String capturedHighResolutionFullImagePath;
    private String capturedHighResolutionQRCroppedImagePath;
    private String capturedImagePath;
    File compressedFile;
    private ImageView cross;
    CrossHairView crossHairView;
    private float density;
    TextView descText;
    private HVDocConfig docConfig;
    private ConstraintLayout docInstructionsLayout;
    private ConstraintLayout docLoaderLayout;
    private HVDocConfig.Document document;
    RectPortHoleView documentCaptureView;
    private float dpHeight;
    private float dpWidth;
    File folder;
    private File fullHRImageFile;
    TextView hintText;
    private ImageView hvGreenTickImageView;
    private HVError hvImageCaptureError;
    private Long imageSubmissionTimestamp;
    private ScanningIndicator indicator;
    private TextView instructionsInsideCameraPreviewBox;
    private TextView instructionsOutsideCameraPreviewBox;
    private Button instructionsUploadButton;
    private IPAddress ipAddress;
    private boolean isDocCaptureFlow;
    private boolean isReadBarcodeTimerRunning;
    ImageView ivFlash;
    private float lastTouchX;
    private float lastTouchY;
    private LottieAnimationView lav;
    private LottieAnimationView lavDocInstructions;
    private LottieAnimationView lavDocInstructionsProcessing;
    private LottieAnimationView lavLoader;
    private Location mLocation;
    SensorEventListener mySensorEventListener;
    private ShapeableImageView overlayImageView;
    double padding;
    String pdfPath;
    PermissionManager permissionManager;
    private TextView progressDialogTextView;
    private View progressDialogView;
    private ImageView progressSpinnerImageView;
    private File qrCroppedFile;
    private CountDownTimer readBarcodeTimer;
    String retakeMessage;
    String retryAction;
    SensorManager sensorManager;
    private boolean showInstructionPage;
    TextView tvTitle;
    View vFlash;
    private final String camViewTag = "docCaptureCameraPreview";
    final float[][] gravity = (float[][]) Array.newInstance((Class<?>) float.class, 1, 1);
    final float[][] magnetic = (float[][]) Array.newInstance((Class<?>) float.class, 1, 1);
    final float[][] accels = {new float[3]};
    final float[][] mags = {new float[3]};
    final float[] values = new float[3];
    final float[] azimuth = new float[1];
    final float[] pitch = new float[1];
    final float[] roll = new float[1];
    private final TimingUtils docPickerScreenLoadSuccessTimingUtils = new TimingUtils();
    private final TimingUtils docPickerUploadButtonClickTimingUtils = new TimingUtils();
    private final TimingUtils docPickerCaptureButtonClickTimingUtils = new TimingUtils();
    private final TimingUtils screenLoadSuccessTimingUtils = new TimingUtils();
    private final TimingUtils imageCaptureTimingUtils = new TimingUtils();
    private final TimingUtils permissionTimingUtils = new TimingUtils();
    private final TimingUtils captureClickTimingUtils = new TimingUtils();
    private final HVBarcodeDetector hvBarcodeDetector = new HVBarcodeDetector();
    private final Handler delayHandler = new Handler();
    private final float BOX_PORTHOLE_PADDING_PERCENT = 0.03f;
    private final boolean useFrontFacingCam = false;
    private boolean shouldAllowActivityClose = true;
    private final HVResponse hvResponse = new HVResponse();
    private final ArrayList<HVBaseResponse> hvResponses = new ArrayList<>();
    private final float minimumDocXFramePercentage = 55.0f;
    private final float minimumDocYFramePercentage = 50.0f;
    private final float maximumDocFrameXPercentage = 100.0f;
    private final float maximumDocFrameYPercentage = 90.0f;
    private final Animation.AnimationListener flashAnimationListener = new Animation.AnimationListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.1
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            HVDocsActivity.this.vFlash.setVisibility(0);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            HVDocsActivity.this.vFlash.setVisibility(8);
        }
    };
    boolean isPhoneTilted = false;
    private boolean hasDelayFinished = false;
    private String qrCodeValue = "";
    private String detectedBarcode = "";
    private boolean isBarcodeDetected = false;
    private boolean safeToTakePicture = true;
    private boolean isAutoCapturing = false;
    private boolean isCardDetectionInProgress = false;
    private boolean isAutoCaptureTimerRunning = false;
    private boolean isCardDetectedInAutoCapturedImage = true;
    private HVCardUIState hvCardUIState = HVCardUIState.CARD_NOT_DETECTED;
    private int autoCaptureTimerNumber = 3;
    private boolean isStillAutoCaptureInitialState = true;
    private int numberOfRechecks = 0;
    private boolean isViewsInitialised = false;
    private boolean isCheckingCamera = false;
    private final int CAMERA_CHECK_DELAY = 20;
    private final HVCamHost camHost = new AnonymousClass2();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum HVCardUIState {
        CARD_DETECTED,
        CARD_NOT_DETECTED,
        MOVE_CLOSER,
        MOVE_AWAY_FROM_CAMERA,
        MOVE_AWAY_FROM_EDGE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface ImageSaveListener {
        void onImageSave(String str, Bitmap bitmap);
    }

    private void adjustTopBar() {
    }

    public void adjustTitleText() {
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    /* renamed from: checkAndWaitForRemoteConfig */
    public /* bridge */ /* synthetic */ void m446x13b071e4() {
        super.m446x13b071e4();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public /* bridge */ /* synthetic */ void handleCloseAction() {
        super.handleCloseAction();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.appcompat.app.AppCompatActivity
    public /* bridge */ /* synthetic */ boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public /* bridge */ /* synthetic */ void showCameraPermissionBS(Spanned spanned, Spanned spanned2, Spanned spanned3, PermDialogCallback permDialogCallback) {
        super.showCameraPermissionBS(spanned, spanned2, spanned3, permDialogCallback);
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public /* bridge */ /* synthetic */ Context updateBaseContextLocale(Context context) {
        return super.updateBaseContextLocale(context);
    }

    static /* synthetic */ int access$4410(HVDocsActivity hVDocsActivity) {
        int i = hVDocsActivity.autoCaptureTimerNumber;
        hVDocsActivity.autoCaptureTimerNumber = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends HVCamHost {
        private void showDocBoxView(RectF rectF, boolean z) {
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
            return 2.0f;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public float getPreviewMegapixels() {
            return 2.0f;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public String getVideoFilename() {
            return null;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onCameraFlipCallback() {
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
        public void onFlashNull() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashTorchOn() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onReady() {
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

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void zoomMaxLevel(int i) {
        }

        AnonymousClass2() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureReady(byte[] bArr) {
            Bitmap documentImageBitmap;
            if (InternalToolUtils.isTestMode(HVDocsActivity.this) && (documentImageBitmap = InternalToolUtils.getDocumentImageBitmap(HVDocsActivity.this.getDocConfig().getDocumentSide())) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                documentImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
            }
            onPictureTaken();
            if (HVDocsActivity.this.cameraView != null) {
                HVDocsActivity.this.cameraView.onPause();
            }
            final ExifHelper exifHelper = new ExifHelper();
            exifHelper.readExif(bArr, HVDocsActivity.this.capturedImagePath, HVDocsActivity.this.mLocation);
            try {
                HVDocsActivity.this.showProgressDialog(true, "");
                HVDocsActivity hVDocsActivity = HVDocsActivity.this;
                new SaveBitmapTask(bArr, hVDocsActivity, new ImageSaveListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$2$$ExternalSyntheticLambda0
                    @Override // co.hyperverge.hypersnapsdk.activities.HVDocsActivity.ImageSaveListener
                    public final void onImageSave(String str, Bitmap bitmap) {
                        HVDocsActivity.AnonymousClass2.this.m487x4cfb90b(exifHelper, str, bitmap);
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } catch (Exception e) {
                Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }

        /* renamed from: lambda$onPictureReady$0$co-hyperverge-hypersnapsdk-activities-HVDocsActivity$2, reason: not valid java name */
        public /* synthetic */ void m487x4cfb90b(final ExifHelper exifHelper, final String str, Bitmap bitmap) {
            String transactionID;
            HVLogUtils.d(HVDocsActivity.TAG, "onImageSave() called with: croppedFilePath = [" + str + "], bitmap = [" + bitmap + "]");
            HVDocsActivity.this.imageSubmissionTimestamp = Long.valueOf(System.currentTimeMillis());
            onPictureSaved(new File(str));
            HVDocsActivity.this.cameraFree.set(true);
            if (bitmap != null && new File(str).exists()) {
                if (HVDocsActivity.this.getDocConfig().isShouldAutoCapture()) {
                    try {
                        HVCardDetectionResult detectCard = HVTfliteHelper.getInstance().detectCard(new HVCardDetectorInput(bitmap));
                        boolean z = false;
                        if (detectCard != null && detectCard.getNormalisedTopLeftX() >= 0.0f && detectCard.getNormalisedTopLeftX() <= 1.0f && detectCard.getNormalisedTopLeftY() >= 0.0f && detectCard.getNormalisedTopLeftY() <= 1.0f && detectCard.getNormalisedBottomRightX() >= 0.0f && detectCard.getNormalisedBottomRightX() <= 1.0f && detectCard.getNormalisedBottomRightY() >= 0.0f && detectCard.getNormalisedBottomRightY() <= 1.0f) {
                            float normalisedBottomRightY = detectCard.getNormalisedBottomRightY() - detectCard.getNormalisedTopLeftY();
                            float normalisedBottomRightX = detectCard.getNormalisedBottomRightX() - detectCard.getNormalisedTopLeftX();
                            z = ((normalisedBottomRightY > 0.5f ? 1 : (normalisedBottomRightY == 0.5f ? 0 : -1)) > 0 && (normalisedBottomRightX > 0.55f ? 1 : (normalisedBottomRightX == 0.55f ? 0 : -1)) > 0) && ((normalisedBottomRightY > 0.9f ? 1 : (normalisedBottomRightY == 0.9f ? 0 : -1)) < 0 && (normalisedBottomRightX > 1.0f ? 1 : (normalisedBottomRightX == 1.0f ? 0 : -1)) < 0);
                        }
                        setIsCardDetectedInAutoCapturedImage(z);
                    } catch (Exception e) {
                        Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                        }
                    } catch (NoClassDefFoundError e2) {
                        Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e2));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                        }
                        HVDocsActivity.this.callCompletionHandler(new HVError(36, HVDocsActivity.this.getStringFromResources(R.string.hyper_doc_detect_error)), null);
                        HVDocsActivity.this.finish();
                    }
                }
                final JSONObject jSONObject = new JSONObject();
                try {
                    if (HVDocsActivity.this.getDocConfig().isShouldExportPDF()) {
                        HVDocsActivity.this.pdfPath = ImageToPDFConverter.convertImageToPDF(bitmap, HVDocsActivity.this.folder.getPath() + "/hv_" + System.currentTimeMillis() + ".pdf");
                        jSONObject.put("pdfUri", HVDocsActivity.this.pdfPath);
                    }
                    if (HVDocsActivity.this.getDocConfig().getOcrHeaders() != null && HVDocsActivity.this.getDocConfig().getOcrHeaders().has("transactionId")) {
                        transactionID = HVDocsActivity.this.getDocConfig().getOcrHeaders().getString("transactionId");
                    } else {
                        transactionID = SPHelper.getTransactionID();
                    }
                    final String str2 = transactionID;
                    if (!SDKInternalConfig.getInstance().getRemoteConfig().isUseIpToGeo()) {
                        moveOnFromHVDocView(str, HVDocsActivity.this.capturedHighResolutionQRCroppedImagePath, str2, null, exifHelper, jSONObject);
                        return;
                    } else {
                        new IPToGeoServiceImpl().performIp2GeoAddress(new IPToGeoService.IPToGeoCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.2.1
                            @Override // co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService.IPToGeoCallback
                            public void onSuccess(IPAddress iPAddress) {
                                HVDocsActivity.this.ipAddress = iPAddress;
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                anonymousClass2.moveOnFromHVDocView(str, HVDocsActivity.this.capturedHighResolutionQRCroppedImagePath, str2, iPAddress, exifHelper, jSONObject);
                            }

                            @Override // co.hyperverge.hypersnapsdk.service.iptogeo.IPToGeoService.IPToGeoCallback
                            public void onError() {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                anonymousClass2.moveOnFromHVDocView(str, HVDocsActivity.this.capturedHighResolutionQRCroppedImagePath, str2, null, exifHelper, jSONObject);
                            }
                        });
                        return;
                    }
                } catch (Exception e3) {
                    Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e3));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
                        return;
                    }
                    return;
                }
            }
            HVDocsActivity.this.stopCamera();
            HVDocsActivity.this.finishView(new HVError(2, "Error while capturing the document"), new HVResponse(null, null, null, HVDocsActivity.this.retryAction));
        }

        private void setIsCardDetectedInAutoCapturedImage(boolean z) {
            HVDocsActivity.this.isCardDetectedInAutoCapturedImage = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void moveOnFromHVDocView(String str, String str2, String str3, IPAddress iPAddress, ExifHelper exifHelper, JSONObject jSONObject) {
            Log.d(HVDocsActivity.TAG, "moveOnFromHVDocView() called with: filePath = [" + str + "], capturedHighResolutionQRCroppedImagePath = [" + str2 + "], transactionID = [" + str3 + "], ipAddress = [" + iPAddress + "], helper = [" + exifHelper + "], result = [" + jSONObject + "]");
            if (HVDocsActivity.this.getDocConfig().isShouldReadNIDQR()) {
                Bitmap decodeFile = BitmapFactory.decodeFile(str);
                try {
                    HVDocsActivity hVDocsActivity = HVDocsActivity.this;
                    hVDocsActivity.qrCodeValue = hVDocsActivity.hvBarcodeDetector.detect(decodeFile);
                } catch (NoClassDefFoundError unused) {
                    Log.e(HVDocsActivity.TAG, "gms vision excluded");
                }
                JSONObject ocrParams = HVDocsActivity.this.getDocConfig().getOcrParams();
                try {
                    ocrParams.put(AppConstants.DOCUMENT_QR_CODE, HVDocsActivity.this.qrCodeValue);
                    HVDocsActivity.this.getDocConfig().ocrParams = ocrParams.toString();
                } catch (Exception e) {
                    Log.e(HVDocsActivity.TAG, "onPictureReady:- JSON Exception :" + Utils.getErrorMessage(e));
                }
                decodeFile.recycle();
            }
            if (HVDocsActivity.this.getDocConfig().isShouldReadBarcode()) {
                JSONObject ocrParams2 = HVDocsActivity.this.getDocConfig().getOcrParams();
                try {
                    ocrParams2.put(AppConstants.DOCUMENT_BAR_CODE, HVDocsActivity.this.detectedBarcode);
                    HVDocsActivity.this.getDocConfig().ocrParams = ocrParams2.toString();
                } catch (Exception e2) {
                    Log.e(HVDocsActivity.TAG, "onPictureReady:- JSON Exception :" + Utils.getErrorMessage(e2));
                }
            }
            exifHelper.writeExifData(str, str3, iPAddress);
            HVDocsActivity.this.cross.setEnabled(true);
            HVDocsActivity.this.showProgressDialog(false, null);
            if (HVDocsActivity.this.getDocConfig().isShouldReadBarcode()) {
                HVDocsActivity.this.resetReadBarcodeVariables();
            }
            HVDocsActivity.this.resetAutoCaptureVariables();
            HVDocsActivity.this.safeToTakePicture = true;
            if (!HVDocsActivity.this.getDocConfig().isShouldAutoCapture() || HVDocsActivity.this.isCardDetectedInAutoCapturedImage) {
                if (HVDocsActivity.this.getDocConfig().shouldShowReviewScreen()) {
                    HVDocsActivity.this.enableCaptureButton();
                    HVDocsActivity.this.startReviewScreen(str, str2);
                    return;
                } else {
                    if (!HVDocsActivity.this.getDocConfig().isShouldDoOCR()) {
                        HVDocsActivity.this.enableCaptureButton();
                        HVDocsActivity.this.stopCamera();
                        HVDocsActivity.this.finishView(null, new HVResponse(jSONObject, new JSONObject(), str, HVDocsActivity.this.retryAction));
                        return;
                    }
                    HVDocsActivity.this.makeOCRAPICall(str, str2);
                    return;
                }
            }
            HVDocsActivity.this.enableCaptureButton();
            HVDocsActivity hVDocsActivity2 = HVDocsActivity.this;
            hVDocsActivity2.startRetakeScreen(str, TextConfigUtils.getText(hVDocsActivity2.getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_AUTO_CAPTURE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_AUTO_CAPTURE_ERROR, HVDocsActivity.this.getStringFromResources(R.string.docCaptureAutoCaptureError)).toString());
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureFailed() {
            Log.d(HVDocsActivity.TAG, "onPictureFailed() called");
            HVDocsActivity.this.cameraFree.set(true);
            HVDocsActivity.this.hvImageCaptureError = new HVError(2, "failure logged in document onPictureFailed()");
            long longValue = HVDocsActivity.this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureFailed(HVDocsActivity.this.hvImageCaptureError, HVDocsActivity.this.getDocConfig(), longValue);
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void showCrossHair(final float f, final float f2, final boolean z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.2.2
                @Override // java.lang.Runnable
                public void run() {
                    if (f > 0.0f || f2 > 0.0f) {
                        HVDocsActivity.this.crossHairView.showCrosshair(f * HVDocsActivity.this.camViewWidth, f2 * HVDocsActivity.this.camViewHeight, z);
                    } else {
                        HVDocsActivity.this.crossHairView.showCrosshair(HVDocsActivity.this.camViewWidth / 2, HVDocsActivity.this.camViewHeight / 2, z);
                    }
                }
            });
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onNewPreviewFrame(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2) {
            Bitmap documentImageBitmap;
            if (HVDocsActivity.this.safeToTakePicture) {
                if (HVDocsActivity.this.getDocConfig().isShouldReadBarcode()) {
                    String detect = HVDocsActivity.this.hvBarcodeDetector.detect(bArr, i, i2);
                    if (!detect.isEmpty()) {
                        HVDocsActivity.this.detectedBarcode = detect;
                        if (!HVDocsActivity.this.isBarcodeDetected) {
                            HVDocsActivity.this.isBarcodeDetected = true;
                            if (HVDocsActivity.this.getDocConfig().isBarcodeSkipDisabled()) {
                                HVDocsActivity.this.showBarcodeScannedSuccessfulOverlay(true);
                                new Handler().postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$2$$ExternalSyntheticLambda1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        HVDocsActivity.AnonymousClass2.this.m484x3db24562();
                                    }
                                }, 1000L);
                            }
                            if (!HVDocsActivity.this.getDocConfig().isBarcodeSkipDisabled() && HVDocsActivity.this.isReadBarcodeTimerRunning) {
                                HVDocsActivity.this.readBarcodeTimer.cancel();
                                HVDocsActivity.this.isReadBarcodeTimerRunning = false;
                                HVDocsActivity.this.showBarcodeScannedSuccessfulOverlay(true);
                                new Handler().postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$2$$ExternalSyntheticLambda2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        HVDocsActivity.AnonymousClass2.this.m485x3ee89841();
                                    }
                                }, 1000L);
                            }
                        }
                        if (!HVDocsActivity.this.getDocConfig().isShouldAutoCapture() && (!HVDocsActivity.this.btCapture.isEnabled() || !HVDocsActivity.this.btCapture.isClickable())) {
                            HVDocsActivity.this.runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$2$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    HVDocsActivity.AnonymousClass2.this.m486x401eeb20();
                                }
                            });
                        }
                    }
                }
                if (!HVDocsActivity.this.getDocConfig().isShouldAutoCapture() || HVDocsActivity.this.isCardDetectionInProgress) {
                    return;
                }
                HVDocsActivity.this.isCardDetectionInProgress = true;
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
                if (InternalToolUtils.isTestMode(HVDocsActivity.this) && (documentImageBitmap = InternalToolUtils.getDocumentImageBitmap(HVDocsActivity.this.getDocConfig().getDocumentSide())) != null) {
                    decodeByteArray = documentImageBitmap;
                }
                float width = decodeByteArray.getWidth();
                float height = decodeByteArray.getHeight();
                float width2 = HVDocsActivity.this.cameraContainer.getWidth();
                float height2 = HVDocsActivity.this.cameraContainer.getHeight();
                try {
                    try {
                        HVCardDetectionResult detectCard = HVTfliteHelper.getInstance().detectCard(new HVCardDetectorInput(decodeByteArray));
                        if (detectCard != null) {
                            RectF boxRect = HVDocsActivity.this.getBoxRect();
                            RectF rectF = new RectF(((int) boxRect.left) + ((int) (boxRect.width() * 0.02f)), ((int) boxRect.top) + ((int) (boxRect.height() * 0.02f)), ((int) boxRect.right) - ((int) (boxRect.width() * 0.02f)), ((int) boxRect.bottom) - ((int) (((int) boxRect.height()) * 0.02f)));
                            float f = rectF.bottom - rectF.top;
                            float f2 = rectF.right - rectF.left;
                            int i5 = (int) ((0.0f * f2) / 200.0f);
                            int i6 = (int) ((10.0f * f) / 200.0f);
                            RectF rectF2 = new RectF(((int) rectF.left) + i5, ((int) rectF.top) + i6, ((int) rectF.right) - i5, ((int) rectF.bottom) - i6);
                            int i7 = (int) ((45.0f * f2) / 200.0f);
                            int i8 = (int) ((f * 50.0f) / 200.0f);
                            RectF rectF3 = new RectF(((int) rectF.left) + i7, ((int) rectF.top) + i8, ((int) rectF.right) - i7, ((int) rectF.bottom) - i8);
                            float tfliteInputShapeHeight = detectCard.getTfliteInputShapeHeight() * ((height / width) - 1.0f);
                            float normalisedTopLeftX = detectCard.getNormalisedTopLeftX() * width2;
                            float normalisedTopLeftY = (detectCard.getNormalisedTopLeftY() * height2) + tfliteInputShapeHeight;
                            float normalisedBottomRightX = detectCard.getNormalisedBottomRightX() * width2;
                            float normalisedBottomRightY = (detectCard.getNormalisedBottomRightY() * height2) + tfliteInputShapeHeight;
                            detectCard.getNormalisedWidth();
                            detectCard.getNormalisedHeight();
                            RectF rectF4 = new RectF(normalisedTopLeftX, normalisedTopLeftY, normalisedBottomRightX, normalisedBottomRightY);
                            float f3 = rectF4.bottom - rectF4.top;
                            float f4 = rectF4.right - rectF4.left;
                            float min = Math.min(f2, f);
                            boolean z = f3 < (f * 90.0f) / 100.0f && f4 < (f2 * 100.0f) / 100.0f;
                            boolean z2 = f3 > (min * 50.0f) / 100.0f && f4 > (min * 55.0f) / 100.0f;
                            try {
                                if (rectF3.contains(rectF4)) {
                                    HVDocsActivity.this.cancelAutoCaptureTimer();
                                    HVDocsActivity.this.setHVCardUIState(HVCardUIState.MOVE_CLOSER);
                                } else if (rectF2.contains(rectF4)) {
                                    if (!z2) {
                                        HVDocsActivity.this.cancelAutoCaptureTimer();
                                        HVDocsActivity.this.setHVCardUIState(HVCardUIState.MOVE_CLOSER);
                                    } else {
                                        if (HVDocsActivity.this.hvCardUIState != HVCardUIState.CARD_DETECTED) {
                                            HVDocsActivity.this.setHVCardUIState(HVCardUIState.CARD_DETECTED);
                                        }
                                        if (!HVDocsActivity.this.isAutoCapturing) {
                                            HVDocsActivity.this.isAutoCapturing = true;
                                            if (HVDocsActivity.this.autoCaptureTimer == null || HVDocsActivity.this.isAutoCaptureTimerRunning) {
                                                Log.e(HVDocsActivity.TAG, "onNewPreviewFrame: autoCaptureTimer is null or is already running");
                                            } else {
                                                HVDocsActivity.this.autoCaptureTimer.start();
                                                HVDocsActivity.this.isAutoCaptureTimerRunning = true;
                                            }
                                        }
                                    }
                                } else if (!rectF.contains(rectF4)) {
                                    HVDocsActivity.this.cancelAutoCaptureTimer();
                                    HVDocsActivity.this.setHVCardUIState(HVCardUIState.CARD_NOT_DETECTED);
                                } else if (!z2) {
                                    HVDocsActivity.this.cancelAutoCaptureTimer();
                                    HVDocsActivity.this.setHVCardUIState(HVCardUIState.MOVE_CLOSER);
                                } else if (!z) {
                                    HVDocsActivity.this.cancelAutoCaptureTimer();
                                    HVDocsActivity.this.setHVCardUIState(HVCardUIState.MOVE_AWAY_FROM_CAMERA);
                                } else {
                                    HVDocsActivity.this.cancelAutoCaptureTimer();
                                    HVDocsActivity.this.setHVCardUIState(HVCardUIState.MOVE_AWAY_FROM_EDGE);
                                }
                            } catch (Exception e) {
                                Log.e(HVDocsActivity.TAG, "onNewPreviewFrame: Exception : " + e.getMessage());
                                HVDocsActivity.this.cancelAutoCaptureTimer();
                            }
                        } else {
                            HVDocsActivity.this.cancelAutoCaptureTimer();
                            HVDocsActivity.this.setHVCardUIState(HVCardUIState.CARD_NOT_DETECTED);
                        }
                    } catch (Exception e2) {
                        Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e2));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                        }
                    }
                } catch (NoClassDefFoundError e3) {
                    Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e3));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
                    }
                    HVDocsActivity.this.callCompletionHandler(new HVError(36, HVDocsActivity.this.getStringFromResources(R.string.hyper_doc_detect_error)), null);
                    HVDocsActivity.this.finish();
                }
                HVDocsActivity.this.isCardDetectionInProgress = false;
            }
        }

        /* renamed from: lambda$onNewPreviewFrame$1$co-hyperverge-hypersnapsdk-activities-HVDocsActivity$2, reason: not valid java name */
        public /* synthetic */ void m484x3db24562() {
            HVDocsActivity.this.showBarcodeScannedSuccessfulOverlay(false);
        }

        /* renamed from: lambda$onNewPreviewFrame$2$co-hyperverge-hypersnapsdk-activities-HVDocsActivity$2, reason: not valid java name */
        public /* synthetic */ void m485x3ee89841() {
            HVDocsActivity.this.showBarcodeScannedSuccessfulOverlay(false);
        }

        /* renamed from: lambda$onNewPreviewFrame$3$co-hyperverge-hypersnapsdk-activities-HVDocsActivity$2, reason: not valid java name */
        public /* synthetic */ void m486x401eeb20() {
            HVDocsActivity.this.enableCaptureButton();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onViewDimensionChange(int i, int i2) {
            HVDocsActivity.this.camViewHeight = i2;
            HVDocsActivity.this.camViewWidth = i;
            HVDocsActivity.this.adjustCrossHairView();
            HVDocsActivity.this.adjustDocumentCaptureView();
            HVDocsActivity.this.adjustHintText();
            HVDocsActivity.this.adjustTitleText();
            HVDocsActivity.this.adjustScanningIndicator();
            HVDocsActivity.this.adjustOverlayImageView();
            HVDocsActivity.this.adjustBlackOverlayView();
            HVDocsActivity.this.adjustProgressDialogView();
            HVDocsActivity.this.adjustStatusText();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureSizeSet(int i, int i2) {
            HVDocsActivity.this.enableCaptureButton();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public boolean isShouldCaptureHighResolutionImage() {
            return HVDocsActivity.this.getDocConfig().isShouldReadNIDQR();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public File getPhotoDirectory() {
            return new File(HVDocsActivity.this.capturedImagePath).getParentFile();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public String getPhotoFilename() {
            return "IS_" + System.currentTimeMillis() + ".jpg";
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureTaken() {
            Log.d(HVDocsActivity.TAG, "onPictureTaken() called");
            long longValue = HVDocsActivity.this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureSuccessful(HVDocsActivity.this.getDocConfig(), longValue);
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureSaved(File file) {
            Log.d(HVDocsActivity.TAG, "onPictureSaved() called with: file = [" + file + "]");
            long longValue = HVDocsActivity.this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureSaved(HVDocsActivity.this.getDocConfig(), file.getAbsolutePath(), longValue);
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashOff() {
            try {
                if (HVDocsActivity.this.getDocConfig().isShouldShowFlashIcon()) {
                    HVDocsActivity.this.ivFlash.setImageResource(R.drawable.ic_baseline_flash_off_18);
                }
            } catch (Exception e) {
                Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashOn() {
            try {
                if (HVDocsActivity.this.getDocConfig().isShouldShowFlashIcon()) {
                    HVDocsActivity.this.ivFlash.setImageResource(R.drawable.ic_baseline_flash_on_18);
                    if (CameraEngine.isCamera2(HVDocsActivity.this) || HVDocsActivity.this.cameraView == null) {
                        return;
                    }
                    HVDocsActivity.this.cameraView.nextFlashMode();
                }
            } catch (Exception e) {
                Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashAuto() {
            HVDocsActivity.this.getDocConfig().isShouldShowFlashIcon();
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onLayoutChange() {
            HVDocsActivity.this.adjustView(true);
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void flashScreen() {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.2.3
                @Override // java.lang.Runnable
                public void run() {
                    HVDocsActivity.this.flashScreen();
                }
            });
        }
    }

    public static void start(Context context, HVDocConfig hVDocConfig, DocCaptureCompletionHandler docCaptureCompletionHandler) {
        String str = TAG;
        Log.d(str, "start() called with: context = [" + context + "], hvDocConfig = [" + hVDocConfig + "], listener = [" + docCaptureCompletionHandler + "]");
        if (docCaptureCompletionHandler == null) {
            return;
        }
        CallbackProvider.get().setCallback(docCaptureCompletionHandler);
        Log.d(str, "start: documentImageListener: " + docCaptureCompletionHandler);
        String moduleId = hVDocConfig.getModuleId();
        if (context == null) {
            callCompletionHandler(moduleId, getAppFilesDir(context), CallbackProvider.get().injectDocCaptureCallback(), new HVError(6, "Context object is null"), null);
            return;
        }
        HyperSnapSDK hyperSnapSDK = HyperSnapSDK.getInstance();
        HyperSnapSDKConfig hyperSnapSDKConfig = hyperSnapSDK.getHyperSnapSDKConfig();
        if (!hyperSnapSDK.isHyperSnapSDKInitialised() || ((hyperSnapSDKConfig.getAppId() != null && hyperSnapSDKConfig.getAppId().isEmpty()) || (hyperSnapSDKConfig.getAppKey() != null && hyperSnapSDKConfig.getAppKey().isEmpty()))) {
            HVError hVError = new HVError(11, context.getResources().getString(R.string.initialised_error));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logHyperSnapSDKInitError(hVError.getErrorMessage());
            }
            callCompletionHandler(moduleId, getAppFilesDir(context), CallbackProvider.get().injectDocCaptureCallback(), hVError, null);
            return;
        }
        if (hyperSnapSDKConfig.getHyperSnapRegion() == HyperSnapParams.Region.ASIA_PACIFIC && !HyperSnapSDK.isUserSessionActive()) {
            callCompletionHandler(moduleId, getAppFilesDir(context), CallbackProvider.get().injectDocCaptureCallback(), new HVError(11, context.getResources().getString(R.string.user_session_not_created_error)), null);
            return;
        }
        if (hVDocConfig == null) {
            callCompletionHandler(moduleId, getAppFilesDir(context), CallbackProvider.get().injectDocCaptureCallback(), new HVError(6, context.getResources().getString(R.string.document_config_error)), null);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) HVDocsActivity.class);
        if (hVDocConfig.isShouldReadNIDQR() && SDKInternalConfig.getInstance().getFeatureConfigMap().get(AppConstants.READ_DOC_QR_FEATURE) != null) {
            hVDocConfig.setShouldReadNIDQR(SDKInternalConfig.getInstance().getFeatureConfigMap().get(AppConstants.READ_DOC_QR_FEATURE).isShouldEnable());
        }
        if (hVDocConfig.isShouldAutoCapture()) {
            if (Build.VERSION.SDK_INT >= 26) {
                Map<String, FeatureConfig> featureConfigMap = SDKInternalConfig.getInstance().getFeatureConfigMap();
                if (featureConfigMap != null && featureConfigMap.containsKey(AppConstants.DOC_AUTO_CAPTURE)) {
                    hVDocConfig.setShouldAutoCapture(featureConfigMap.get(AppConstants.DOC_AUTO_CAPTURE).isShouldEnable());
                }
            } else {
                hVDocConfig.setShouldAutoCapture(false);
            }
        }
        intent.putExtra(HVDocConfig.KEY, hVDocConfig);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callCompletionHandler(HVError hVError, HVResponse hVResponse) {
        callCompletionHandler(this.docConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectDocCaptureCallback(), hVError, hVResponse);
    }

    private static void relayoutChildren(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAutoCaptureTimer() {
        this.autoCaptureTimerNumber = 3;
        this.isAutoCapturing = false;
        CountDownTimer countDownTimer = this.autoCaptureTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.isAutoCaptureTimerRunning = false;
    }

    private String getTransactionId() throws JSONException {
        if (getDocConfig().getOcrHeaders() != null && getDocConfig().getOcrHeaders().has("transactionId")) {
            return getDocConfig().getOcrHeaders().getString("transactionId");
        }
        return SPHelper.getTransactionID();
    }

    private void getLocation(final Context context) {
        if (LocationServiceImpl.getInstance(this).isGPSSwitchedOn()) {
            LocationServiceImpl.getInstance(context).startLocationUpdates();
            LocationServiceImpl.getInstance(context).addLocationCallback(new LocationProviderCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.3
                @Override // co.hyperverge.hypersnapsdk.listeners.LocationProviderCallback
                public void onResult(Location location) {
                    if (location != null) {
                        HVDocsActivity.this.mLocation = location;
                        HVDocsActivity.this.enableCaptureButton();
                    } else {
                        HVDocsActivity.this.mLocation = LocationServiceImpl.getInstance(context).getLastKnownLocation();
                        if (HVDocsActivity.this.mLocation != null) {
                            HVDocsActivity.this.enableCaptureButton();
                        }
                    }
                    Log.d(HVDocsActivity.TAG, "onResult: mLocation: " + location);
                }
            });
        } else {
            showLocationSettingsDialog();
        }
    }

    private void showLocationSettingsDialog() {
        HVJSONObject customUIStrings = getDocConfig().getCustomUIStrings();
        Spanned text = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOCATION_PERMISSION_TITLE, getString(R.string.hv_gps_switched_off));
        Spanned text2 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOCATION_PERMISSION_DESC, getString(R.string.hv_please_enable_gps_to_continue));
        Spanned text3 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOCATION_PERMISSION_SETTINGS_BUTTON, getString(R.string.hv_open_settings));
        Spanned text4 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOCATION_PERMISSION_CANCEL_BUTTON, getString(R.string.hv_cancel));
        final Spanned text5 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOCATION_PERMISSION_ERROR, getString(R.string.hv_gps_access_denied_by_user));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(text);
        builder.setMessage(text2);
        builder.setCancelable(false);
        builder.setPositiveButton(text3, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HVDocsActivity.this.m482xcbb15024(dialogInterface, i);
            }
        });
        builder.setNegativeButton(text4, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HVDocsActivity.this.m483xe5cccec3(text5, dialogInterface, i);
            }
        });
        builder.show();
    }

    /* renamed from: lambda$showLocationSettingsDialog$0$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m482xcbb15024(DialogInterface dialogInterface, int i) {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1001);
    }

    /* renamed from: lambda$showLocationSettingsDialog$1$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m483xe5cccec3(Spanned spanned, DialogInterface dialogInterface, int i) {
        String string = getString(R.string.hv_gps_access_denied_by_user);
        if (spanned != null) {
            string = spanned.toString();
        }
        callCompletionHandler(new HVError(33, string), null);
        finish();
    }

    private void initializeTiltSensor() {
        try {
            this.sensorManager = (SensorManager) getSystemService("sensor");
            this.mySensorEventListener = new SensorEventListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.4
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    try {
                        int type = sensorEvent.sensor.getType();
                        if (type == 1) {
                            HVDocsActivity.this.accels[0] = (float[]) sensorEvent.values.clone();
                        } else if (type == 2) {
                            HVDocsActivity.this.mags[0] = (float[]) sensorEvent.values.clone();
                        }
                        if (HVDocsActivity.this.mags[0] == null || HVDocsActivity.this.accels[0] == null) {
                            return;
                        }
                        HVDocsActivity.this.gravity[0] = new float[9];
                        HVDocsActivity.this.magnetic[0] = new float[9];
                        SensorManager.getRotationMatrix(HVDocsActivity.this.gravity[0], HVDocsActivity.this.magnetic[0], HVDocsActivity.this.accels[0], HVDocsActivity.this.mags[0]);
                        SensorManager.getOrientation(HVDocsActivity.this.gravity[0], HVDocsActivity.this.values);
                        HVDocsActivity.this.azimuth[0] = HVDocsActivity.this.values[0] * 57.29578f;
                        HVDocsActivity.this.pitch[0] = HVDocsActivity.this.values[1] * 57.29578f;
                        HVDocsActivity.this.roll[0] = HVDocsActivity.this.values[2] * 57.29578f;
                        HVDocsActivity.this.mags[0] = null;
                        HVDocsActivity.this.accels[0] = null;
                        if (HVDocsActivity.this.pitch[0] >= HVDocsActivity.this.getDocConfig().getAllowedTiltPitch() || HVDocsActivity.this.pitch[0] <= HVDocsActivity.this.getDocConfig().getAllowedTiltPitch() * (-1) || HVDocsActivity.this.roll[0] >= HVDocsActivity.this.getDocConfig().getAllowedTiltRoll() || HVDocsActivity.this.roll[0] <= HVDocsActivity.this.getDocConfig().getAllowedTiltRoll() * (-1)) {
                            HVDocsActivity.this.setCaptureButtonState(true, false);
                            HVDocsActivity.this.isPhoneTilted = true;
                        } else {
                            HVDocsActivity.this.enableCaptureButton();
                            HVDocsActivity.this.isPhoneTilted = false;
                        }
                        HVDocsActivity.this.setDescText();
                    } catch (Exception e) {
                        Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                        }
                    }
                }
            };
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            setContentView(R.layout.hv_activity_doc_capture);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            sendResponse(new HVError(2, getStringFromResources(R.string.internal_error)));
        }
        this.docConfig = (HVDocConfig) getIntent().getSerializableExtra(HVDocConfig.KEY);
        if (bundle != null) {
            this.docConfig = (HVDocConfig) new Gson().fromJson(bundle.getString("docConfig"), HVDocConfig.class);
        }
        getWindow().getDecorView().getRootView().setTag("docCaptureCameraPreview");
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentDocFlowStarted(getDocConfig());
        }
        if (this.docConfig == null) {
            sendResponse(new HVError(2, getStringFromResources(R.string.document_config_error)));
            return;
        }
        if (getDocConfig().isShouldReadBarcode() || getDocConfig().isShouldReadNIDQR()) {
            initializeHVBarcodeDetector();
        }
        if (getDocConfig().isShouldAutoCapture()) {
            getDocConfig().setShouldAutoCapture(initDocumentDetector(this));
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation()) {
            try {
                getLocation(this);
            } catch (NoClassDefFoundError unused) {
                Log.e(TAG, "gms excluded");
            }
        }
        this.document = getDocConfig().getDocument();
        View findViewById = findViewById(R.id.v_flash);
        this.vFlash = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        File file = new File(getFilesDir(), "hv");
        this.folder = file;
        if (!file.exists()) {
            this.folder.mkdirs();
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.capturedImagePath = this.folder.getPath() + RemoteSettings.FORWARD_SLASH_STRING + currentTimeMillis + ".jpg";
        this.capturedHighResolutionQRCroppedImagePath = this.folder.getPath() + "/HIGH_RES_CROPPED_" + currentTimeMillis + ".jpg";
        this.capturedHighResolutionFullImagePath = this.folder.getPath() + "/HIGH_RES_FULL_" + currentTimeMillis + ".jpg";
        this.docInstructionsLayout = (ConstraintLayout) findViewById(R.id.layoutDocInstructions);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.layoutDocLoader);
        this.docLoaderLayout = constraintLayout;
        this.lav = (LottieAnimationView) constraintLayout.findViewById(R.id.lavDocLoader);
        Log.d(TAG, "onCreate: documentImageListener: " + CallbackProvider.get().injectDocCaptureCallback());
    }

    private boolean initDocumentDetector(Context context) {
        try {
            return HVTfliteHelper.getInstance().initialiseDetector(context);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return false;
        } catch (NoClassDefFoundError e2) {
            Log.e(TAG, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
            callCompletionHandler(new HVError(36, getStringFromResources(R.string.hyper_doc_detect_error)), null);
            finish();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HVDocConfig getDocConfig() {
        HVDocConfig hVDocConfig = this.docConfig;
        if (hVDocConfig != null) {
            return hVDocConfig;
        }
        finishView(new HVError(2, getResources().getString(R.string.document_config_error)), null);
        return new HVDocConfig();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        String str = TAG;
        Log.d(str, "onStart() called");
        Log.d(str, "onStart: docConfig.isShouldShowInstructionPage(): " + getDocConfig().isShouldShowInstructionPage() + "\ndocConfig.isDocumentUploadEnabled(): " + getDocConfig().isDocumentUploadEnabled() + "\nisDocCaptureFlow: " + this.isDocCaptureFlow + "\nisFromRetake: false");
        decideWhatToShow(false);
    }

    private void showDocInstructions() {
        Log.d(TAG, "showDocInstructions() called");
        this.docPickerScreenLoadSuccessTimingUtils.init();
        this.docInstructionsLayout.setVisibility(0);
        HVJSONObject customUIStrings = getDocConfig().getCustomUIStrings();
        Spanned text = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_INSTRUCTIONS_TITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_INSTRUCTIONS_TITLE);
        if (text != null) {
            ((TextView) this.docInstructionsLayout.findViewById(R.id.tvTitle)).setText(text);
        }
        Spanned text2 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_INSTRUCTIONS_SUBTITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_INSTRUCTIONS_SUBTITLE);
        if (text2 != null) {
            ((TextView) this.docInstructionsLayout.findViewById(R.id.tvSubtitle)).setText(text2);
        }
        Spanned text3 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_INSTRUCTIONS_BUTTON, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_INSTRUCTIONS_BUTTON);
        if (text3 != null) {
            ((Button) this.docInstructionsLayout.findViewById(R.id.btnCaptureDoc)).setText(text3);
        }
        Spanned text4 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_INSTRUCTIONS_UPLOAD_BUTTON, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_INSTRUCTIONS_UPLOAD_BUTTON);
        if (text4 != null) {
            ((Button) this.docInstructionsLayout.findViewById(R.id.btnUploadDoc)).setText(text4);
        }
        setupBranding(this.docInstructionsLayout);
        TextView textView = (TextView) this.docInstructionsLayout.findViewById(R.id.tvTitle);
        TextView textView2 = (TextView) this.docInstructionsLayout.findViewById(R.id.tvSubtitle);
        Button button = (Button) this.docInstructionsLayout.findViewById(R.id.btnCaptureDoc);
        button.setEnabled(true);
        Button button2 = (Button) this.docInstructionsLayout.findViewById(R.id.btnUploadDoc);
        this.instructionsUploadButton = button2;
        button2.setEnabled(true);
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView2);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(button);
        HyperSnapUIConfigUtil.getInstance().customiseSecondaryButton(this.instructionsUploadButton);
        HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(this.docInstructionsLayout);
        HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) this.docInstructionsLayout.findViewById(R.id.ivBack));
        HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) this.docInstructionsLayout.findViewById(R.id.clientLogo));
        if (!getDocConfig().shouldShowModuleBackButton()) {
            this.docInstructionsLayout.findViewById(R.id.ivBack).setVisibility(4);
        }
        this.docInstructionsLayout.findViewById(R.id.ivBack).setEnabled(true);
        this.docInstructionsLayout.findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocsActivity.this.m479x83b63e19(view);
            }
        });
        this.instructionsUploadButton.setVisibility(getDocConfig().isDocumentUploadEnabled() ? 0 : 8);
        this.instructionsUploadButton.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocsActivity.this.m480x9dd1bcb8(view);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocsActivity.this.m481xb7ed3b57(view);
            }
        });
        long longValue = this.docPickerScreenLoadSuccessTimingUtils.getTimeDifferenceLong().longValue();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentPickerScreenLoadSuccess(longValue);
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentPickerScreenLaunched();
        }
        this.docPickerUploadButtonClickTimingUtils.init();
        this.docPickerCaptureButtonClickTimingUtils.init();
        loadAnimation();
    }

    /* renamed from: lambda$showDocInstructions$2$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m479x83b63e19(View view) {
        view.setEnabled(false);
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentPickerScreenCloseClicked();
        }
        onBackPressed();
    }

    /* renamed from: lambda$showDocInstructions$3$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m480x9dd1bcb8(View view) {
        view.setEnabled(false);
        long longValue = this.docPickerUploadButtonClickTimingUtils.getTimeDifferenceLong().longValue();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentPickerScreenDocumentUploadButtonClicked(longValue);
        }
        this.lavDocInstructions.setVisibility(8);
        HVLottieHelper.load(this.lavDocInstructionsProcessing, "hv_processing.lottie", HVLottieHelper.State.START, null);
        this.lavDocInstructionsProcessing.setVisibility(0);
        startDocUpload();
    }

    /* renamed from: lambda$showDocInstructions$4$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m481xb7ed3b57(View view) {
        view.setEnabled(false);
        long longValue = this.docPickerCaptureButtonClickTimingUtils.getTimeDifferenceLong().longValue();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentPickerScreenDocumentCaptureButtonClicked(longValue);
        }
        startDocCapture();
    }

    private void loadAnimation() {
        String str;
        if (getDocConfig().getDocumentSide().equals(HVDocConfig.DocumentSide.FRONT)) {
            str = "hv_doc_instruction_front.lottie";
        } else {
            str = getDocConfig().getDocumentSide().equals(HVDocConfig.DocumentSide.BACK) ? HVLottieHelper.DOC_INSTRUCTION_BACK : "";
        }
        LottieAnimationView lottieAnimationView = (LottieAnimationView) this.docInstructionsLayout.findViewById(R.id.lavDocInstructions);
        this.lavDocInstructions = lottieAnimationView;
        HVLottieHelper.load(lottieAnimationView, str, HVLottieHelper.State.START, null);
        this.lavDocInstructionsProcessing = (LottieAnimationView) this.docInstructionsLayout.findViewById(R.id.lavDocInstructionsProcessing);
    }

    private void startDocCapture() {
        Log.d(TAG, "startDocCapture() called");
        this.isDocCaptureFlow = true;
        this.docInstructionsLayout.setVisibility(8);
        try {
            this.permissionManager = new PermissionManager();
            if (SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentDocFlowStarted(getDocConfig());
            }
            checkForPermissions();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            sendResponse(new HVError(2, getStringFromResources(R.string.internal_error)));
        }
    }

    private void startDocPicker(String str) {
        List<ResolveInfo> queryIntentActivities;
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType(str);
        if (Build.VERSION.SDK_INT >= 33) {
            queryIntentActivities = packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH));
        } else {
            queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        }
        if (!queryIntentActivities.isEmpty()) {
            intent.putExtra("android.intent.extra.MIME_TYPES", getUploadMimeTypes());
            startActivityForResult(intent, 1000);
        } else {
            getDocConfig().setEnableDocumentUpload(false);
            this.lavDocInstructionsProcessing.setVisibility(8);
            this.lavDocInstructions.setVisibility(0);
            startRetakeScreen(null, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_NO_SUPPORTED_APP_TYPE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_NO_SUPPORTED_APP_TYPE_ERROR, getStringFromResources(R.string.docUploadNoSupportedAppTypeError)).toString());
        }
    }

    private void startDocUpload() {
        Log.d(TAG, "startDocUpload() called");
        this.isDocCaptureFlow = false;
        if (InternalToolUtils.isTestMode(this)) {
            String fileUploadIntentType = InternalToolUtils.getFileUploadIntentType();
            if (fileUploadIntentType != "") {
                startDocPicker(fileUploadIntentType);
                return;
            } else {
                onActivityResult(1000, -1, new Intent().setData(Uri.fromFile(InternalToolUtils.getDocumentImageFile(getDocConfig().getDocumentSide()))));
                return;
            }
        }
        startDocPicker("*/*");
    }

    private String[] getUploadMimeTypes() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("jpg", "image/jpg");
        hashMap.put("jpeg", "image/jpeg");
        hashMap.put("png", MimeTypes.IMAGE_PNG);
        hashMap.put("pdf", "application/pdf");
        List<String> uploadFileTypes = getDocConfig().getUploadFileTypes();
        if (uploadFileTypes == null || uploadFileTypes.isEmpty()) {
            uploadFileTypes = Arrays.asList("jpg", "jpeg", "png");
        }
        for (String str : hashMap.keySet()) {
            if (uploadFileTypes.contains(str)) {
                arrayList.add((String) hashMap.get(str));
            }
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("docConfig", new Gson().toJson(this.docConfig));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        final PermissionManager.StatusArray status = this.permissionManager.getStatus(this, new ArrayList(Arrays.asList("android.permission.CAMERA")));
        if (!status.denied.isEmpty()) {
            Log.d(LOG_TAG, "Required permissions not granted");
            showCameraPermissionBS(TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAMERA_PERMISSION_TITLE), TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAMERA_PERMISSION_DESC), TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), "", CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAMERA_PERMISSION_BUTTON), new PermDialogCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.5
                @Override // co.hyperverge.hypersnapsdk.listeners.PermDialogCallback
                public void onActionClick() {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(HVDocsActivity.this, "android.permission.CAMERA")) {
                        HVDocsActivity.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + HVDocsActivity.this.getApplicationContext().getPackageName())));
                        return;
                    }
                    HVDocsActivity.this.checkForPermissions();
                }

                @Override // co.hyperverge.hypersnapsdk.listeners.PermDialogCallback
                public void onCancel() {
                    HVError hVError = new HVError(4, "Following Permissions not granted by user: " + TextUtils.join(",", status.denied));
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCameraPermissionsRejected(hVError, HVDocsActivity.this.permissionTimingUtils.getTimeDifferenceLong().longValue());
                    }
                    HVDocsActivity.this.callCompletionHandler(hVError, null);
                    HVDocsActivity.this.finish();
                }
            });
            return;
        }
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCameraPermissionsGranted(this.permissionTimingUtils.getTimeDifferenceLong().longValue());
        }
        readyToStartDocCapture();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void finishView(HVError hVError, HVResponse hVResponse) {
        Log.d(TAG, "finishView() called with: documentImageListener: " + CallbackProvider.get().injectDocCaptureCallback() + "error = [" + hVError + "], hvResponse = [" + hVResponse + "]");
        if (hVResponse != null) {
            try {
                if (getDocConfig().isShouldReadBarcode()) {
                    hVResponse.setRawBarcode(this.detectedBarcode);
                }
                Location location = this.mLocation;
                if (location != null) {
                    hVResponse.setLatitude(Double.valueOf(location.getLatitude()));
                    hVResponse.setLongitude(Double.valueOf(this.mLocation.getLongitude()));
                }
                Long l = this.imageSubmissionTimestamp;
                if (l != null) {
                    hVResponse.setSubmittedTimestamp(l);
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    return;
                }
                return;
            }
        }
        stopCamera();
        m462x652d2dce(hVError, hVResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkCameraAndHandleCompletion, reason: merged with bridge method [inline-methods] */
    public void m462x652d2dce(final HVError hVError, final HVResponse hVResponse) {
        String str = TAG;
        HVLogUtils.d(str, "checkCameraAndHandleCompletion() called with: error = [" + hVError + "], hvResponse = [" + hVResponse + "]");
        if (this.isCheckingCamera) {
            HVLogUtils.d("checkCameraAndHandleCompletion", "already checking");
            return;
        }
        if (CameraEngine.isCameraReleased()) {
            HVLogUtils.d(str, "checkCameraAndHandleCompletion: camera is released");
            this.isCheckingCamera = true;
            callCompletionHandler(hVError, hVResponse);
            DocOCRHelper.destroy();
            LocationServiceImpl.destroy();
            this.mLocation = null;
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.clearHandlers();
                this.cameraView = null;
            }
            finish();
            return;
        }
        HVLogUtils.d(str, "checkCameraAndHandleCompletion: camera is not released");
        new Handler().postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m462x652d2dce(hVError, hVResponse);
            }
        }, 20L);
    }

    private void readyToStartDocCapture() {
        HVLogUtils.d(TAG, "readyToStartDocCapture() called");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m471xd45d6de9();
            }
        });
    }

    /* renamed from: lambda$readyToStartDocCapture$6$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m471xd45d6de9() {
        this.lavLoader = (LottieAnimationView) findViewById(R.id.lavLoader);
        HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(findViewById(R.id.parent_container));
        findViewById(R.id.mainLayout).setVisibility(8);
        LottieAnimationView lottieAnimationView = this.lavLoader;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(0);
            if (!this.lavLoader.isInLayout()) {
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.parent_container);
                relativeLayout.removeView(this.lavLoader);
                relativeLayout.addView(this.lavLoader);
            }
            HVLottieHelper.load(this.lavLoader, "hv_processing.lottie", HVLottieHelper.State.START, null);
        }
        m446x13b071e4();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public void onRemoteConfigFetchDone() {
        HVLogUtils.d(TAG, "onRemoteConfigFetchDone() called");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m470x3a7d30df();
            }
        });
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics()) {
            String sensorDataZipFileName = Utils.getSensorDataZipFileName("doc");
            if (SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
                SDKInternalConfig.getInstance().getHvSensorBiometrics().registerSensorBiometrics(sensorDataZipFileName);
            }
            JSONObject ocrHeaders = getDocConfig().getOcrHeaders();
            try {
                ocrHeaders.put(AppConstants.SENSOR_DATA_ZIP_FILE_NAME, sensorDataZipFileName);
                getDocConfig().ocrHeaders = ocrHeaders.toString();
            } catch (Exception e) {
                Log.e(TAG, "start() ocrHeaders :- JSON Exception :" + Utils.getErrorMessage(e));
            }
        }
        startDocumentCapture();
    }

    /* renamed from: lambda$onRemoteConfigFetchDone$7$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m470x3a7d30df() {
        findViewById(R.id.parent_container).setBackground(null);
        LottieAnimationView lottieAnimationView = this.lavLoader;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        findViewById(R.id.mainLayout).setVisibility(0);
    }

    private void startDocumentCapture() {
        try {
            if (!this.isViewsInitialised || this.showInstructionPage) {
                initializeViewsById();
            }
            if (getDocConfig().isShouldAutoCapture()) {
                TextView textView = this.instructionsInsideCameraPreviewBox;
                if (textView != null) {
                    textView.setVisibility(0);
                }
                TextView textView2 = this.instructionsOutsideCameraPreviewBox;
                if (textView2 != null) {
                    textView2.setVisibility(0);
                }
                TextView textView3 = this.tvTitle;
                if (textView3 != null) {
                    textView3.setVisibility(4);
                }
                TextView textView4 = this.descText;
                if (textView4 != null) {
                    textView4.setVisibility(4);
                }
                resetAutoCaptureVariables();
            } else if (getDocConfig().isShouldReadBarcode()) {
                TextView textView5 = this.instructionsInsideCameraPreviewBox;
                if (textView5 != null) {
                    textView5.setVisibility(4);
                }
                TextView textView6 = this.instructionsOutsideCameraPreviewBox;
                if (textView6 != null) {
                    textView6.setVisibility(0);
                    this.instructionsOutsideCameraPreviewBox.setText(TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_SCANNING_BARCODE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_SCANNING_BARCODE, getStringFromResources(R.string.docCaptureScanningBarcode)));
                }
                TextView textView7 = this.tvTitle;
                if (textView7 != null) {
                    textView7.setVisibility(4);
                }
                TextView textView8 = this.descText;
                if (textView8 != null) {
                    textView8.setVisibility(4);
                }
                resetReadBarcodeVariables();
            } else {
                TextView textView9 = this.instructionsInsideCameraPreviewBox;
                if (textView9 != null) {
                    textView9.setVisibility(4);
                }
                TextView textView10 = this.instructionsOutsideCameraPreviewBox;
                if (textView10 != null) {
                    textView10.setVisibility(4);
                }
                TextView textView11 = this.tvTitle;
                if (textView11 != null) {
                    textView11.setVisibility(0);
                }
                TextView textView12 = this.descText;
                if (textView12 != null) {
                    textView12.setVisibility(0);
                }
            }
            setupOverlayImageView();
            setupBranding(null);
            this.cameraFree = new AtomicBoolean(true);
            try {
                if (!getDocConfig().isShouldAllowPhoneTilt()) {
                    initializeTiltSensor();
                    SensorManager sensorManager = this.sensorManager;
                    sensorManager.registerListener(this.mySensorEventListener, sensorManager.getDefaultSensor(1), 3);
                    SensorManager sensorManager2 = this.sensorManager;
                    sensorManager2.registerListener(this.mySensorEventListener, sensorManager2.getDefaultSensor(2), 3);
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
                }
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            long longValue = this.screenLoadSuccessTimingUtils.getTimeDifferenceLong().longValue();
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenOpened(getDocConfig());
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLoadSuccess(longValue);
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLaunched();
            this.captureClickTimingUtils.init();
        } catch (Exception e2) {
            Log.e(TAG, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e2)));
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
            sendResponse(new HVError(2, getStringFromResources(R.string.internal_error)));
        }
    }

    private void setupOverlayImageView() {
        ShapeableImageView shapeableImageView;
        TextView textView = this.instructionsInsideCameraPreviewBox;
        if (textView != null) {
            textView.setVisibility(4);
        }
        if (!getDocConfig().isShouldReadBarcode() && getDocConfig().getDocumentCaptureOverlay() != null && (shapeableImageView = this.overlayImageView) != null) {
            shapeableImageView.setImageBitmap(getDocConfig().getDocumentCaptureOverlay());
            adjustOverlayImageView();
        }
        if (getDocConfig().isShouldAutoCapture() || getDocConfig().isShouldReadBarcode()) {
            return;
        }
        showOverlayImageView(true);
        runOverlayTimer();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [co.hyperverge.hypersnapsdk.activities.HVDocsActivity$6] */
    private void runOverlayTimer() {
        if (getDocConfig().getDocumentCaptureOverlayDuration() != Integer.MAX_VALUE) {
            new CountDownTimer(getDocConfig().getDocumentCaptureOverlayDuration(), 1000L) { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.6
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (HVDocsActivity.this.overlayImageView != null) {
                        HVDocsActivity.this.overlayImageView.setVisibility(4);
                    }
                }
            }.start();
        }
    }

    private void initializeHVBarcodeDetector() {
        try {
            this.hvBarcodeDetector.initialiseHVBarcodeDetector(getApplicationContext(), 6416);
        } catch (NoClassDefFoundError unused) {
            Log.e(TAG, "gms vision excluded");
        }
    }

    public void sendResponse(HVError hVError) {
        Log.d(TAG, "sendResponse() called with: documentImageListener: " + CallbackProvider.get().injectDocCaptureCallback() + "error = [" + hVError + "]");
        callCompletionHandler(hVError, null);
        DocOCRHelper.destroy();
        finish();
    }

    public void checkForPermissions() {
        this.permissionTimingUtils.init();
        ArrayList arrayList = new ArrayList(Arrays.asList("android.permission.CAMERA"));
        this.permissionManager.checkAndRequestPermissions(this, arrayList);
        if (this.permissionManager.getStatus(this, arrayList).denied.isEmpty()) {
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCameraPermissionsGranted(this.permissionTimingUtils.getTimeDifferenceLong().longValue());
            }
            readyToStartDocCapture();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView != null) {
            hVMagicView.onPause();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView != null) {
            hVMagicView.onResume();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        try {
            if (!getDocConfig().isShouldAllowPhoneTilt()) {
                this.sensorManager.unregisterListener(this.mySensorEventListener);
                DocOCRHelper.destroy();
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        super.onDestroy();
        LocationServiceImpl.destroy();
    }

    public void stopCamera() {
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView != null) {
            hVMagicView.setSensorCallback(null);
            this.cameraView.onDestroy();
            this.cameraView.onPause();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    HVBaseConfig getBaseConfig() {
        return this.docConfig;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldAllowActivityClose() {
        return this.shouldAllowActivityClose;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldShowCloseAlert() {
        if (getDocConfig().isDocumentUploadEnabled()) {
            return getDocConfig().shouldShowCloseAlert() && this.docInstructionsLayout.getVisibility() == 0;
        }
        return getDocConfig().shouldShowCloseAlert();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    void onCloseActivity() {
        Log.d(TAG, "onCloseActivity() called");
        boolean shouldShowModuleBackButton = getDocConfig().shouldShowModuleBackButton();
        boolean isShouldShowInstructionPage = getDocConfig().isShouldShowInstructionPage();
        boolean isDocumentUploadEnabled = getDocConfig().isDocumentUploadEnabled();
        boolean z = this.docInstructionsLayout.getVisibility() == 0;
        if (shouldShowModuleBackButton || !((isShouldShowInstructionPage || isDocumentUploadEnabled) && z)) {
            if (shouldShowModuleBackButton || isShouldShowInstructionPage || isDocumentUploadEnabled || z) {
                if ((getDocConfig().isShouldShowInstructionPage() || getDocConfig().isDocumentUploadEnabled()) && this.docInstructionsLayout.getVisibility() != 0) {
                    stopCamera();
                    showProgressDialog(false, null);
                    this.safeToTakePicture = true;
                    this.showInstructionPage = true;
                    showDocInstructions();
                } else {
                    userCancelledOperation();
                }
                if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                    return;
                }
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentCaptureScreenBackPressed();
            }
        }
    }

    private void userCancelledOperation() {
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logIdScreenClosedByUser(getDocConfig());
        }
        stopCamera();
        finishView(new HVError(3, getString(R.string.operation_cancelled)), new HVResponse(new JSONObject(), null, null, this.retryAction));
    }

    public void initializeViewsById() {
        this.hvGreenTickImageView = (ImageView) findViewById(R.id.docGreenTickImageView);
        this.overlayImageView = (ShapeableImageView) findViewById(R.id.docOverlayImageView);
        this.instructionsInsideCameraPreviewBox = (TextView) findViewById(R.id.tvStatus);
        this.instructionsOutsideCameraPreviewBox = (TextView) findViewById(R.id.initialStatusTV);
        ImageView imageView = (ImageView) findViewById(R.id.camera_icon);
        this.btCapture = imageView;
        ImageViewCompat.setImageTintList(imageView, null);
        this.btCapture.setImageResource(R.drawable.hv_camera_button_disabled);
        setCaptureButtonState(true, false);
        this.progressDialogView = findViewById(R.id.docProgressDialogView);
        this.progressSpinnerImageView = (ImageView) findViewById(R.id.hv_loading_icon);
        this.progressDialogTextView = (TextView) findViewById(R.id.hv_loading_text);
        if (getDocConfig().isShouldAutoCapture()) {
            ImageView imageView2 = this.btCapture;
            if (imageView2 != null) {
                imageView2.setVisibility(4);
            }
            this.autoCaptureTimer = new AnonymousClass7(getDocConfig().getAutoCaptureDuration(), getDocConfig().getAutoCaptureDuration() / 2);
        }
        if (getDocConfig().isShouldReadBarcode() && !getDocConfig().isBarcodeSkipDisabled()) {
            CountDownTimer countDownTimer = new CountDownTimer(getDocConfig().getReadBarcodeTimeout(), 1000L) { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.8
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    HVDocsActivity.this.isReadBarcodeTimerRunning = false;
                    HVDocsActivity.this.showBarcodeScannedSuccessfulOverlay(false);
                    HVDocsActivity.this.enableCaptureButton();
                }
            };
            this.readBarcodeTimer = countDownTimer;
            countDownTimer.start();
            this.isReadBarcodeTimerRunning = true;
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation() && this.mLocation != null) {
            enableCaptureButton();
        }
        ImageView imageView3 = (ImageView) findViewById(R.id.ivFlashFlip);
        this.ivFlash = imageView3;
        imageView3.setImageResource(R.drawable.ic_baseline_flash_off_18);
        this.ivFlash.setEnabled(true);
        this.blackOverlay = findViewById(R.id.blackOverlayView);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.9
            @Override // java.lang.Runnable
            public void run() {
                if (SDKInternalConfig.getInstance().isRemoteConfigFetchDone()) {
                    HVDocsActivity.this.setupBranding(null);
                }
            }
        }, 100L);
        View view = this.vFlash;
        if (view != null) {
            view.setVisibility(8);
        }
        this.cameraContainer = (FrameLayout) findViewById(R.id.cameraContainer);
        CameraEngine.setPreviewCallback(getDocConfig().isShouldReadBarcode() || getDocConfig().isShouldAutoCapture());
        HVCameraHelper.enableCameraParameters(this, true);
        CameraEngine.setShouldRandomize(false);
        CameraEngine.setShouldUseDefaultZoom(false);
        try {
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                this.cameraContainer.removeView(hVMagicView);
            }
            HVMagicView hVMagicView2 = HVMagicView.getInstance(this, this.camHost, false);
            this.cameraView = hVMagicView2;
            hVMagicView2.disableRotation();
            this.cameraView.setContentDescription("docCaptureCameraPreview");
            this.cameraContainer.addView(this.cameraView, 0);
            HVMagicView hVMagicView3 = this.cameraView;
            if (hVMagicView3 != null) {
                this.cameraView.setSensorCallback(new HVMagicView.SensorCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.10
                    @Override // co.hyperverge.hvcamera.HVMagicView.SensorCallback
                    public void onSensorCallback() {
                        Log.i("CameraActivity", HVDocsActivity.this.cameraContainer.getWidth() + " " + HVDocsActivity.this.cameraContainer.getHeight());
                        HVDocsActivity.this.crossHairView.showCrosshair((float) (HVDocsActivity.this.camViewWidth / 2), (float) (HVDocsActivity.this.camViewHeight / 2), false);
                    }
                });
            }
            addCrossHairView(this.cameraContainer);
            if (getDocConfig().isShouldReadBarcode()) {
                removeScanningIndicator();
                this.indicator = new ScanningIndicator(this, getBottomYOfBox() - getTopYOfBox());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(UIUtils.getScreenWidth(), UIUtils.dpToPx(this, 15.0f));
                layoutParams.gravity = 1;
                this.indicator.setLayoutParams(layoutParams);
                this.indicator.setImageResource(R.drawable.hv_ic_camera_qr_status);
                this.cameraContainer.addView(this.indicator);
            }
            addDocumentCaptureView(this.cameraContainer);
            ImageView imageView4 = (ImageView) findViewById(R.id.ivBack);
            this.cross = imageView4;
            imageView4.setEnabled(true);
            this.cross.setOnClickListener(this);
            this.tvTitle = (TextView) findViewById(R.id.title_text);
            this.hintText = (TextView) findViewById(R.id.tvHint);
            try {
                HVJSONObject customUIStrings = getDocConfig().getCustomUIStrings();
                if (getDocConfig().getHintTypeface() > 0) {
                    this.hintText.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getHintTypeface()));
                }
                String docCaptureSubText = getDocConfig().getDocCaptureSubText();
                if (!TextUtils.isEmpty(docCaptureSubText)) {
                    this.hintText.setText(docCaptureSubText);
                } else {
                    Spanned text = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_SUB_TEXT, getDocConfig().getDocumentSide() == HVDocConfig.DocumentSide.FRONT ? CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_FRONT_SUB_TEXT : CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_BACK_SUB_TEXT);
                    if (text != null) {
                        this.hintText.setText(text);
                    }
                }
                this.descText = (TextView) findViewById(R.id.desc_text);
                if (getDocConfig().getDescTypeface() > 0) {
                    this.descText.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getDescTypeface()));
                }
                if (getDocConfig().getDocCaptureDescription() != null && !getDocConfig().getDocCaptureDescription().isEmpty()) {
                    this.descText.setText(getDocConfig().getDocCaptureDescription());
                } else {
                    Spanned text2 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_DESC, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_DESC);
                    if (text2 != null) {
                        this.descText.setText(text2);
                    }
                }
                String capturePageTitleText = getDocConfig().getCapturePageTitleText();
                if (!TextUtils.isEmpty(capturePageTitleText)) {
                    this.tvTitle.setText(capturePageTitleText);
                } else {
                    Spanned text3 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_TITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_TITLE);
                    if (text3 != null) {
                        this.tvTitle.setText(text3);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
                }
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            CameraEngine.setCaptureMode(true);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getMetrics(new DisplayMetrics());
            CameraEngine.setScreenSize(point);
            this.density = getResources().getDisplayMetrics().density;
            this.dpHeight = r6.heightPixels / this.density;
            this.dpWidth = r6.widthPixels / this.density;
            adjustView(false);
            adjustCrossHairView();
            adjustHintText();
            adjustTitleText();
            adjustDocumentCaptureView();
            adjustScanningIndicator();
            adjustOverlayImageView();
            adjustBlackOverlayView();
            adjustProgressDialogView();
            adjustStatusText();
            if (getDocConfig().getTitleTypeface() > 0) {
                this.tvTitle.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getTitleTypeface()));
            }
            HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(this.tvTitle, true);
            HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(this.descText, true);
            HyperSnapUIConfigUtil.getInstance().customiseStatusTextView(this.instructionsOutsideCameraPreviewBox, true);
            HyperSnapUIConfigUtil.getInstance().customiseDocumentSideTextView(this.hintText);
            HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) findViewById(R.id.ivBack), true);
            HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) findViewById(R.id.clientLogo), true);
            if (!getDocConfig().shouldShowModuleBackButton() && !getDocConfig().isShouldShowInstructionPage() && !getDocConfig().isDocumentUploadEnabled()) {
                findViewById(R.id.ivBack).setVisibility(4);
            }
            this.btCapture.setOnClickListener(this);
            this.ivFlash.setOnClickListener(this);
            this.btCapture.setOnTouchListener(new View.OnTouchListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.11
                boolean inAnimate;

                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    if (!HVDocsActivity.this.getDocConfig().isShouldAllowPhoneTilt() && HVDocsActivity.this.isPhoneTilted) {
                        return false;
                    }
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        this.inAnimate = HVDocsActivity.this.cameraFree.get();
                        if (HVDocsActivity.this.cameraFree.get()) {
                            HVDocsActivity.this.popin();
                        }
                    } else if (action == 1 && this.inAnimate) {
                        HVDocsActivity.this.popout();
                    }
                    return false;
                }
            });
            if (this.cameraView != null) {
                Log.d(LOG_TAG, "Camera resume is called");
                this.cameraView.onResume();
            } else if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLoadFailure(new HVError(2, "CameraView is null"));
            }
            this.isViewsInitialised = true;
        } catch (Exception e2) {
            Log.e(TAG, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
            HVError hVError = new HVError(5, getStringFromResources(R.string.camera_error));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureScreenLoadFailure(hVError);
            }
            sendResponse(hVError);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$7, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass7 extends CountDownTimer {
        AnonymousClass7(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (HVDocsActivity.this.isAutoCaptureTimerRunning) {
                HVDocsActivity.this.instructionsInsideCameraPreviewBox.setText(((Object) TextConfigUtils.getText(HVDocsActivity.this.getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_AUTO_CAPTURE_WAIT, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_AUTO_CAPTURE_WAIT, HVDocsActivity.this.getStringFromResources(R.string.docCaptureAutoCaptureWait))) + " " + HVDocsActivity.access$4410(HVDocsActivity.this));
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (HVDocsActivity.this.isAutoCaptureTimerRunning) {
                HVDocsActivity.this.isAutoCaptureTimerRunning = false;
                HVDocsActivity.this.runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$7$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HVDocsActivity.AnonymousClass7.this.m488xe52fc682();
                    }
                });
            }
        }

        /* renamed from: lambda$onFinish$0$co-hyperverge-hypersnapsdk-activities-HVDocsActivity$7, reason: not valid java name */
        public /* synthetic */ void m488xe52fc682() {
            HVDocsActivity.this.safeTakePicture();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAutoCaptureVariables() {
        this.autoCaptureTimerNumber = 3;
        this.isAutoCapturing = false;
        this.isCardDetectionInProgress = false;
        this.isStillAutoCaptureInitialState = true;
        if (getDocConfig().isShouldAutoCapture() && this.isDocCaptureFlow) {
            setAutoCaptureInitialState();
        }
        CountDownTimer countDownTimer = this.autoCaptureTimer;
        if (countDownTimer == null || !this.isAutoCaptureTimerRunning) {
            return;
        }
        countDownTimer.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetReadBarcodeVariables() {
        this.isBarcodeDetected = false;
        if (getDocConfig().isShouldReadBarcode() && this.isDocCaptureFlow) {
            setReadBarcodeInitialState();
        }
    }

    private void setReadBarcodeInitialState() {
        try {
            TextView textView = this.instructionsOutsideCameraPreviewBox;
            if (textView != null) {
                textView.setVisibility(0);
            }
            if (this.overlayImageView != null) {
                if (getDocConfig().getReadBarcodeOverlay() != null) {
                    this.overlayImageView.setImageBitmap(getDocConfig().getReadBarcodeOverlay());
                } else {
                    this.overlayImageView.setImageResource(R.drawable.hv_read_barcode_overlay);
                }
            }
            showOverlayImageView(true);
            TextView textView2 = this.instructionsInsideCameraPreviewBox;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
            TextView textView3 = this.hintText;
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void setAutoCaptureInitialState() {
        try {
            TextView textView = this.instructionsOutsideCameraPreviewBox;
            if (textView != null) {
                textView.setVisibility(0);
            }
            showOverlayImageView(true);
            TextView textView2 = this.instructionsInsideCameraPreviewBox;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
            TextView textView3 = this.hintText;
            if (textView3 != null) {
                textView3.setVisibility(4);
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$13, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState;

        static {
            int[] iArr = new int[HVCardUIState.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState = iArr;
            try {
                iArr[HVCardUIState.CARD_DETECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState[HVCardUIState.CARD_NOT_DETECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState[HVCardUIState.MOVE_CLOSER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState[HVCardUIState.MOVE_AWAY_FROM_CAMERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState[HVCardUIState.MOVE_AWAY_FROM_EDGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHVCardUIState(HVCardUIState hVCardUIState) {
        this.hvCardUIState = hVCardUIState;
        int i = AnonymousClass13.$SwitchMap$co$hyperverge$hypersnapsdk$activities$HVDocsActivity$HVCardUIState[hVCardUIState.ordinal()];
        if (i == 1) {
            this.isStillAutoCaptureInitialState = false;
            setAutoCaptureUI(true, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_DOC_FOUND, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_DOC_FOUND, getStringFromResources(R.string.docCaptureDocFound)));
            return;
        }
        if (i == 2) {
            if (this.isStillAutoCaptureInitialState) {
                return;
            }
            setAutoCaptureUI(false, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_DOC_NOT_FOUND, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_DOC_NOT_FOUND, getStringFromResources(R.string.docCaptureDocNotFound)));
        } else {
            if (i == 3) {
                this.isStillAutoCaptureInitialState = false;
                setAutoCaptureUI(false, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_MOVE_CLOSER, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_MOVE_CLOSER, getStringFromResources(R.string.docCaptureMoveCloser)));
                return;
            }
            if (i == 4) {
                this.isStillAutoCaptureInitialState = false;
                setAutoCaptureUI(false, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_MOVE_AWAY_FROM_CAMERA, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_MOVE_AWAY_FROM_CAMERA, getStringFromResources(R.string.docCaptureMoveAwayFromCamera)));
            } else if (i != 5) {
                return;
            }
            this.isStillAutoCaptureInitialState = false;
            setAutoCaptureUI(false, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_MOVE_AWAY_FROM_EDGE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_MOVE_AWAY_FROM_EDGE, getStringFromResources(R.string.docCaptureMoveAwayFromEdge)));
        }
    }

    private void setAutoCaptureUI(boolean z, Spanned spanned) {
        if (z) {
            this.documentCaptureView.setBorderColor(getResources().getColor(R.color.doc_capture_circle_success));
        } else {
            this.documentCaptureView.setBorderColor(getResources().getColor(R.color.doc_capture_circle_failure));
        }
        TextView textView = this.instructionsInsideCameraPreviewBox;
        if (textView != null) {
            textView.setText(spanned);
        }
        try {
            TextView textView2 = this.instructionsOutsideCameraPreviewBox;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
        } catch (Exception unused) {
            runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m475xd2795ef0();
                }
            });
        }
        try {
            showOverlayImageView(false);
        } catch (Exception unused2) {
            runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m476xec94dd8f();
                }
            });
        }
        try {
            TextView textView3 = this.instructionsInsideCameraPreviewBox;
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
        } catch (Exception unused3) {
            runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m473xe59c912d();
                }
            });
        }
        try {
            TextView textView4 = this.hintText;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
        } catch (Exception unused4) {
            runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m474xffb80fcc();
                }
            });
        }
    }

    /* renamed from: lambda$setAutoCaptureUI$8$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m475xd2795ef0() {
        TextView textView = this.instructionsOutsideCameraPreviewBox;
        if (textView != null) {
            textView.setVisibility(4);
        }
    }

    /* renamed from: lambda$setAutoCaptureUI$9$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m476xec94dd8f() {
        showOverlayImageView(false);
    }

    /* renamed from: lambda$setAutoCaptureUI$10$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m473xe59c912d() {
        TextView textView = this.instructionsInsideCameraPreviewBox;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    /* renamed from: lambda$setAutoCaptureUI$11$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m474xffb80fcc() {
        TextView textView = this.hintText;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableCaptureButton() {
        if (!getDocConfig().isShouldReadBarcode()) {
            if (getDocConfig().isShouldReadNIDQR() && !this.hasDelayFinished) {
                this.delayHandler.postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda21
                    @Override // java.lang.Runnable
                    public final void run() {
                        HVDocsActivity.this.m464xc25527fd();
                    }
                }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                return;
            } else {
                setCaptureButtonState(true, true);
                return;
            }
        }
        setCaptureButtonState(true, true);
    }

    /* renamed from: lambda$enableCaptureButton$12$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m464xc25527fd() {
        this.hasDelayFinished = true;
        setCaptureButtonState(true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustCrossHairView() {
        if (this.crossHairView.getParent() != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.crossHairView.getLayoutParams();
            layoutParams.height = this.camViewHeight;
            layoutParams.width = this.camViewWidth;
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                this.crossHairView.setX(hVMagicView.getX());
                this.crossHairView.setY(this.cameraView.getY());
            }
            if (!this.crossHairView.isInLayout()) {
                this.crossHairView.requestLayout();
            }
        }
        if (this.cameraContainer.isInLayout()) {
            return;
        }
        this.cameraContainer.requestLayout();
    }

    private void addCrossHairView(FrameLayout frameLayout) {
        CrossHairView crossHairView = new CrossHairView(this);
        this.crossHairView = crossHairView;
        frameLayout.removeView(crossHairView);
        frameLayout.addView(this.crossHairView, -1);
        frameLayout.setOnTouchListener(new View.OnTouchListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HVDocsActivity.this.m461x97992ec7(view, motionEvent);
            }
        });
    }

    /* renamed from: lambda$addCrossHairView$13$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ boolean m461x97992ec7(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.lastTouchX = motionEvent.getX();
            this.lastTouchY = motionEvent.getY();
        } else if (actionMasked == 1 && Math.abs(motionEvent.getX() - this.lastTouchX) < 20.0f && Math.abs(motionEvent.getY() - this.lastTouchY) < 20.0f) {
            this.crossHairView.showCrosshair(motionEvent.getX(), motionEvent.getY(), false);
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.onTouchToFocus(motionEvent.getX() / this.camViewWidth, motionEvent.getY() / this.camViewHeight, null);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustDocumentCaptureView() {
        if (this.documentCaptureView.getParent() != null) {
            RectF boxRect = getBoxRect();
            int width = (int) boxRect.width();
            int height = (int) boxRect.height();
            int topYOfBox = getTopYOfBox();
            this.documentCaptureView.setX(0);
            this.documentCaptureView.setY(topYOfBox);
            this.documentCaptureView.setBoxRect(new RectF((int) boxRect.left, 0.0f, width + ((int) boxRect.left), height), 0.02f);
            if (getDocConfig().isShouldAutoCapture()) {
                this.documentCaptureView.setStrokeWidth(10);
                this.documentCaptureView.setBorderColor(getResources().getColor(R.color.doc_capture_circle_failure));
            }
            if (!this.documentCaptureView.isInLayout()) {
                this.documentCaptureView.requestLayout();
            }
        }
        if (this.cameraContainer.isInLayout()) {
            return;
        }
        this.cameraContainer.requestLayout();
    }

    private void addDocumentCaptureView(FrameLayout frameLayout) {
        RectPortHoleView rectPortHoleView = new RectPortHoleView(this);
        this.documentCaptureView = rectPortHoleView;
        frameLayout.removeView(rectPortHoleView);
        frameLayout.addView(this.documentCaptureView, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popin() {
        ImageView imageView = this.btCapture;
        imageView.clearAnimation();
        imageView.clearAnimation();
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(150L);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillAfter(true);
        imageView.startAnimation(scaleAnimation);
        this.btCapture.startAnimation(scaleAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popout() {
        ImageView imageView = this.btCapture;
        imageView.clearAnimation();
        this.btCapture.clearAnimation();
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(150L);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity.12
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.btCapture.startAnimation(scaleAnimation);
        imageView.startAnimation(scaleAnimation);
    }

    public void setDescText() {
        if (this.isPhoneTilted) {
            this.descText.setText(getStringFromResources(R.string.docCaptureTilt));
            this.descText.setTextColor(getResources().getColor(R.color.text_red));
            return;
        }
        this.descText.setTextColor(getResources().getColor(R.color.content_text_color));
        if (getDocConfig().getDocCaptureDescription() != null && !getDocConfig().getDocCaptureDescription().isEmpty()) {
            this.descText.setText(getDocConfig().getDocCaptureDescription());
        } else {
            this.descText.setText(getStringFromResources(R.string.docCaptureDescription));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.ivBack) {
            view.setEnabled(false);
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCloseClicked();
            }
            onBackPressed();
            return;
        }
        if (id2 == R.id.camera_icon) {
            Log.d(TAG, "onClick() called with: view = [" + view + "]");
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureButtonClicked(getDocConfig(), this.captureClickTimingUtils.getTimeDifferenceLong().longValue());
            }
            if (getDocConfig().isShouldAllowPhoneTilt() || !this.isPhoneTilted) {
                setCaptureButtonState(true, false);
                this.cross.setEnabled(false);
                safeTakePicture();
                return;
            }
            return;
        }
        if (id2 == R.id.ivFlashFlip) {
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureFlashButtonClicked();
            }
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.nextFlashMode();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeTakePicture() {
        String str = TAG;
        Log.d(str, "safeTakePicture() called");
        if (this.cameraFree.get()) {
            this.safeToTakePicture = false;
            this.cameraFree.set(false);
            try {
                this.imageCaptureTimingUtils.init();
                if (this.cameraView != null) {
                    showProgressDialog(true, "");
                    Log.e(str, "safeTakePicture: Taking picture");
                    this.cameraView.takePicture(null);
                } else {
                    this.hvImageCaptureError = new HVError(2, "cameraView is null");
                    long longValue = this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureFailed(this.hvImageCaptureError, getDocConfig(), longValue);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                this.hvImageCaptureError = new HVError(2, Utils.getErrorMessage(e));
                long longValue2 = this.imageCaptureTimingUtils.getTimeDifferenceLong().longValue();
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentCaptureFailed(this.hvImageCaptureError, getDocConfig(), longValue2);
                }
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgressDialog(boolean z, String str) {
        Log.d(TAG, "showProgressDialog() called with: show = [" + z + "], progressLoaderText = [" + str + "]");
        if (this.blackOverlay != null) {
            if (z) {
                displayProgressView(str);
            } else {
                removeProgressView();
            }
        }
    }

    private void displayProgressView(final String str) {
        Log.d(TAG, "displayProgressView() called with: progressLoaderText = [" + str + "]");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m463xb1cee33a(str);
            }
        });
    }

    /* renamed from: lambda$displayProgressView$14$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m463xb1cee33a(String str) {
        View view = this.blackOverlay;
        if (view != null) {
            view.setVisibility(0);
        }
        if (str != null) {
            this.progressDialogTextView.setText(str);
        }
        ImageView imageView = this.progressSpinnerImageView;
        if (imageView != null) {
            imageView.setAnimation(UIUtils.getInfiniteRotationAnimation());
        }
        View view2 = this.progressDialogView;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        showOverlayImageView(false);
        TextView textView = this.instructionsInsideCameraPreviewBox;
        if (textView != null) {
            textView.setVisibility(4);
        }
        TextView textView2 = this.hintText;
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
    }

    private void removeProgressView() {
        Log.d(TAG, "removeProgressView() called");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m472x48030af5();
            }
        });
    }

    /* renamed from: lambda$removeProgressView$15$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m472x48030af5() {
        TextView textView;
        View view = this.blackOverlay;
        if (view != null) {
            view.setVisibility(8);
        }
        ImageView imageView = this.progressSpinnerImageView;
        if (imageView != null) {
            imageView.clearAnimation();
        }
        if (getDocConfig().isShouldAutoCapture() && (textView = this.instructionsOutsideCameraPreviewBox) != null) {
            textView.setText(TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_DOC_NOT_FOUND, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_DOC_NOT_FOUND, getStringFromResources(R.string.docCaptureDocNotFound)));
        }
        View view2 = this.progressDialogView;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        TextView textView2 = this.hintText;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBarcodeScannedSuccessfulOverlay(final boolean z) {
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m478x85d72d4d(z);
            }
        });
    }

    /* renamed from: lambda$showBarcodeScannedSuccessfulOverlay$16$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m478x85d72d4d(boolean z) {
        if (z) {
            if (this.indicator != null) {
                removeScanningIndicator();
            }
            View view = this.blackOverlay;
            if (view != null) {
                view.setVisibility(0);
            }
            ImageView imageView = this.hvGreenTickImageView;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            TextView textView = this.instructionsOutsideCameraPreviewBox;
            if (textView != null) {
                textView.setText(TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_BARCODE_SCANNED, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_BARCODE_SCANNED, getStringFromResources(R.string.docCaptureBarcodeScanned)));
            }
            showOverlayImageView(false);
            TextView textView2 = this.instructionsInsideCameraPreviewBox;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
            TextView textView3 = this.hintText;
            if (textView3 != null) {
                textView3.setVisibility(4);
                return;
            }
            return;
        }
        if (this.indicator != null) {
            removeScanningIndicator();
        }
        View view2 = this.blackOverlay;
        if (view2 != null) {
            view2.setVisibility(4);
        }
        ImageView imageView2 = this.hvGreenTickImageView;
        if (imageView2 != null) {
            imageView2.setVisibility(4);
        }
        TextView textView4 = this.instructionsOutsideCameraPreviewBox;
        if (textView4 != null) {
            textView4.setText(TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_CAPTURE_TITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_TITLE, getStringFromResources(R.string.docCaptureTitle)));
        }
        if (this.overlayImageView != null) {
            if (getDocConfig().getDocumentCaptureOverlay() != null) {
                this.overlayImageView.setImageBitmap(getDocConfig().getDocumentCaptureOverlay());
            } else {
                this.overlayImageView.setImageResource(R.drawable.hv_front_overlay);
            }
        }
        showOverlayImageView(true);
        runOverlayTimer();
        TextView textView5 = this.instructionsInsideCameraPreviewBox;
        if (textView5 != null) {
            textView5.setVisibility(4);
        }
        TextView textView6 = this.hintText;
        if (textView6 != null) {
            textView6.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flashScreen() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 0.0f);
        alphaAnimation.setDuration(300L);
        alphaAnimation.setInterpolator(new DecelerateInterpolator(2.0f));
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(this.flashAnimationListener);
        this.vFlash.startAnimation(alphaAnimation);
    }

    private void showOverlayImageView(boolean z) {
        if (z) {
            if (this.overlayImageView == null || !getDocConfig().isOverlayEnabled()) {
                return;
            }
            this.overlayImageView.setVisibility(0);
            return;
        }
        ShapeableImageView shapeableImageView = this.overlayImageView;
        if (shapeableImageView != null) {
            shapeableImageView.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustView(boolean z) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.overlay1);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.overlay2);
        int topYOfBox = getTopYOfBox();
        getBottomYOfBox();
        if (frameLayout != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.height = topYOfBox;
            frameLayout.setLayoutParams(layoutParams);
        }
        if (frameLayout2 != null) {
            frameLayout2.setVisibility(0);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) frameLayout2.getLayoutParams();
            layoutParams2.setMargins(0, getBottomYOfBox(), 0, 0);
            frameLayout2.setLayoutParams(layoutParams2);
        }
        if (z) {
            relayoutChildren(findViewById(R.id.mainLayout));
        }
    }

    private boolean is4By3Device() {
        return ((double) this.dpHeight) <= (((double) this.dpWidth) * 4.0d) / 3.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTopYOfBox() {
        return (int) getBoxRect().top;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomYOfBox() {
        return (int) getBoxRect().bottom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getBoxRect() {
        int i;
        int i2;
        float aspectRatio = this.document.getAspectRatio();
        int width = this.cameraContainer.getWidth();
        int min = Math.min(this.camViewHeight, this.cameraContainer.getHeight());
        if (aspectRatio <= 1.0f) {
            i2 = (int) (aspectRatio * width);
            i = width;
        } else {
            i = (int) (min / aspectRatio);
            i2 = min;
        }
        return new RectF((width - i) / 2, (min - i2) / 2, (width + i) / 2, (min + i2) / 2);
    }

    public void adjustHintText() {
        TextView textView = this.hintText;
        if (textView != null) {
            textView.setY(getBottomYOfBox() - (this.hintText.getHeight() * 1.75f));
            if (!this.hintText.isInLayout()) {
                this.hintText.requestLayout();
            }
            if (this.cameraContainer.isInLayout()) {
                return;
            }
            this.cameraContainer.requestLayout();
        }
    }

    public void adjustScanningIndicator() {
        if (!getDocConfig().isShouldReadBarcode() || this.indicator == null) {
            return;
        }
        int bottomYOfBox = getBottomYOfBox() - getTopYOfBox();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.indicator.getLayoutParams();
        layoutParams.setMargins(0, (int) (this.camViewWidth * 0.15d), 0, 0);
        this.indicator.setLayoutParams(layoutParams);
        this.indicator.setmHeight(bottomYOfBox);
        if (!this.cameraContainer.isInLayout()) {
            this.cameraContainer.requestLayout();
        }
        this.indicator.startAnimation();
    }

    public void removeScanningIndicator() {
        ScanningIndicator scanningIndicator = this.indicator;
        if (scanningIndicator != null) {
            this.cameraContainer.removeView(scanningIndicator);
            if (this.cameraContainer.isInLayout()) {
                return;
            }
            this.cameraContainer.requestLayout();
        }
    }

    public void adjustOverlayImageView() {
        ShapeableImageView shapeableImageView = this.overlayImageView;
        if (shapeableImageView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) shapeableImageView.getLayoutParams();
            layoutParams.setMargins(((int) getBoxRect().left) + UIUtils.dpToPx(this, 32.0f), UIUtils.dpToPx(this, 32.0f), ((int) getBoxRect().left) + UIUtils.dpToPx(this, 32.0f), UIUtils.dpToPx(this, 32.0f));
            if (!this.overlayImageView.isInLayout()) {
                this.overlayImageView.setLayoutParams(layoutParams);
            }
            if (this.cameraContainer.isInLayout()) {
                return;
            }
            this.cameraContainer.requestLayout();
        }
    }

    public void adjustBlackOverlayView() {
        View view = this.blackOverlay;
        if (view != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            if (!this.blackOverlay.isInLayout()) {
                this.blackOverlay.setLayoutParams(layoutParams);
            }
            if (this.cameraContainer.isInLayout()) {
                return;
            }
            this.cameraContainer.requestLayout();
        }
    }

    public void adjustProgressDialogView() {
        View view = this.progressDialogView;
        if (view != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(((int) getBoxRect().left) + UIUtils.dpToPx(this, 32.0f), 0, ((int) getBoxRect().left) + UIUtils.dpToPx(this, 32.0f), 0);
            if (!this.progressDialogView.isInLayout()) {
                this.progressDialogView.setLayoutParams(layoutParams);
            }
            if (this.cameraContainer.isInLayout()) {
                return;
            }
            this.cameraContainer.requestLayout();
        }
    }

    public void adjustStatusText() {
        TextView textView = this.instructionsInsideCameraPreviewBox;
        if (textView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.setMarginStart(UIUtils.dpToPx(this, 32.0f));
            layoutParams.setMarginEnd(UIUtils.dpToPx(this, 32.0f));
            if (!this.instructionsInsideCameraPreviewBox.isInLayout()) {
                this.instructionsInsideCameraPreviewBox.setLayoutParams(layoutParams);
            }
            if (this.cameraContainer.isInLayout()) {
                return;
            }
            this.cameraContainer.requestLayout();
        }
    }

    public void setCameraButtonTint() {
        if (!getDocConfig().isShouldAllowPhoneTilt() || this.isPhoneTilted) {
            return;
        }
        if (this.mLocation == null && HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation()) {
            return;
        }
        HyperSnapUIConfigUtil.getInstance().customiseCaptureButton(this.btCapture);
    }

    public void adjustTopText() {
        TextView textView = this.descText;
        if (textView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
            if (this.document.getAspectRatio() < 1.0f) {
                layoutParams.setMargins(UIUtils.dpToPx(this, 30.0f), UIUtils.dpToPx(this, 60.0f), UIUtils.dpToPx(this, 30.0f), 0);
            }
            if (this.descText.isInLayout()) {
                return;
            }
            this.descText.requestLayout();
        }
    }

    public void startReviewScreen(String str, String str2) {
        Log.d(TAG, "startReviewScreen() called with: filePath = [" + str + "]");
        try {
            Intent intent = new Intent(this, (Class<?>) HVDocReviewActivity.class);
            intent.putExtra(HVRetakeActivity.IMAGE_URI_TAG, str);
            if (getDocConfig().isShouldReadNIDQR() && !StringUtils.isEmptyOrNull(str2) && new File(str2).exists()) {
                intent.putExtra("qrCodeCroppedImageUri", str2);
            }
            intent.putExtra(HVRetakeActivity.ASPECT_RATIO_TAG, this.document.getAspectRatio());
            intent.putExtra(HVDocConfig.KEY, getDocConfig());
            intent.putExtra(HVRetakeActivity.EXTRA_PADDING_TAG, this.padding);
            RectPortHoleView rectPortHoleView = this.documentCaptureView;
            if (rectPortHoleView != null) {
                intent.putExtra("viewWidth", rectPortHoleView.getWidth());
                intent.putExtra("viewHeight", this.documentCaptureView.getHeight());
            }
            intent.putExtra(CustomTextStringConst.DocCaptureTextConfigs.DOC_LOADER_DESC, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_LOADER_DESC, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOADER_DESC, getStringFromResources(R.string.hv_doc_loader_subtitle)));
            intent.putExtra("isDocCaptureFlow", this.isDocCaptureFlow);
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void startRetakeScreen(String str, String str2) {
        Log.d(TAG, "startErrorReviewScreen() called with: filePath = [" + str + "], message = [" + str2 + "]");
        try {
            Intent intent = new Intent(this, (Class<?>) HVRetakeActivity.class);
            intent.putExtra(HVRetakeActivity.IMAGE_URI_TAG, str);
            intent.putExtra(HVRetakeActivity.ASPECT_RATIO_TAG, this.document.getAspectRatio());
            intent.putExtra(HVRetakeActivity.CONFIG_TAG, getDocConfig());
            intent.putExtra(HVRetakeActivity.SET_PADDING_TAG, getDocConfig().isShouldSetPadding());
            intent.putExtra(HVRetakeActivity.RETRY_MESSAGE_TAG, str2);
            intent.putExtra(HVRetakeActivity.EXTRA_PADDING_TAG, this.padding);
            intent.putExtra(HVRetakeActivity.CALLING_ACTIVITY_TAG, HVRetakeActivity.DOC_CALLING_ACTIVITY_VALUE);
            RectPortHoleView rectPortHoleView = this.documentCaptureView;
            if (rectPortHoleView != null) {
                intent.putExtra("viewWidth", rectPortHoleView.getWidth());
                intent.putExtra("viewHeight", this.documentCaptureView.getHeight());
            }
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void decideWhatToShow(boolean z) {
        boolean z2 = true;
        if (z) {
            this.showInstructionPage = !this.isDocCaptureFlow;
        } else {
            if ((!getDocConfig().isShouldShowInstructionPage() || this.isDocCaptureFlow) && (!getDocConfig().isDocumentUploadEnabled() || this.isDocCaptureFlow)) {
                z2 = false;
            }
            this.showInstructionPage = z2;
        }
        if (this.showInstructionPage) {
            showDocInstructions();
        } else {
            startDocCapture();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        this.safeToTakePicture = true;
        if (getDocConfig().isShouldReadBarcode()) {
            resetReadBarcodeVariables();
        }
        resetAutoCaptureVariables();
        reInitCaptureButton();
        setupOverlayImageView();
        reInitTimingUtils();
        ImageView imageView = this.cross;
        if (imageView != null) {
            imageView.setEnabled(true);
        }
        showProgressDialog(false, null);
        if (i == 1001) {
            try {
                getLocation(this);
            } catch (NoClassDefFoundError unused) {
                HVLogUtils.e(TAG, "gms excluded");
            }
        }
        final boolean isTestMode = InternalToolUtils.isTestMode(this);
        if (i == 1000) {
            this.imageSubmissionTimestamp = Long.valueOf(System.currentTimeMillis());
            if (i2 == -1 && intent != null && intent.getData() != null) {
                final Uri data = intent.getData();
                String fileMimeType = Utils.getFileMimeType(isTestMode, getContentResolver(), data);
                String str = TAG;
                HVLogUtils.d(str, "onActivityResult: uri - " + data);
                HVLogUtils.d(str, "onActivityResult: fileMimeType - " + fileMimeType);
                if (fileMimeType == null) {
                    startRetakeScreen(null, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_READ_FILE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_READ_FILE_ERROR, getStringFromResources(R.string.docUploadReadFileError)).toString());
                } else if (isSelectedFileExtentionInAllowedList(fileMimeType)) {
                    Utils.saveDocToFilesDir(isTestMode, getContentResolver(), data, new File(getFilesDir(), "hv"), new Utils.FileOperationCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda20
                        @Override // co.hyperverge.hypersnapsdk.utils.Utils.FileOperationCallback
                        public final void onSaved(File file) {
                            HVDocsActivity.this.m469x4ffccf28(isTestMode, data, file);
                        }
                    });
                } else {
                    startRetakeScreen(null, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_ALLOWED_FILE_TYPE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_ALLOWED_FILE_TYPE_ERROR, getStringFromResources(R.string.docUploadAllowedFileTypeError)).toString());
                }
            } else {
                this.instructionsUploadButton.setEnabled(true);
                this.lavDocInstructions.setVisibility(0);
                this.lavDocInstructionsProcessing.setVisibility(8);
            }
        }
        if (i2 == 6) {
            this.numberOfRechecks++;
            this.imageSubmissionTimestamp = null;
            decideWhatToShow(false);
            if (intent != null) {
                long longExtra = intent.getLongExtra("timeTakenToClickRetakeButton", 0L);
                if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                    return;
                }
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenRetakeButtonClicked(getDocConfig(), longExtra);
                return;
            }
            return;
        }
        if (i2 == 7) {
            long longExtra2 = intent.getLongExtra("timeTakenToClickConfirmButton", 0L);
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenConfirmButtonClicked(getDocConfig(), this.numberOfRechecks, longExtra2);
            }
            stopCamera();
            finishView((HVError) intent.getSerializableExtra("hvError"), (HVResponse) intent.getSerializableExtra("hvResponse"));
            return;
        }
        if (i2 == 8 || i2 == 18) {
            this.imageSubmissionTimestamp = null;
            stopCamera();
            finishView((HVError) intent.getSerializableExtra("hvError"), null);
        } else {
            if (i2 != 21) {
                return;
            }
            this.numberOfRechecks++;
            this.imageSubmissionTimestamp = null;
            decideWhatToShow(true);
            if (intent != null) {
                long longExtra3 = intent.getLongExtra("timeTakenToClickRetakeButton", 0L);
                if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                    return;
                }
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentRetakeScreenRetakeButtonClicked(getDocConfig(), longExtra3);
            }
        }
    }

    /* renamed from: lambda$onActivityResult$17$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m469x4ffccf28(boolean z, Uri uri, File file) {
        this.lavDocInstructions.setVisibility(0);
        this.lavDocInstructionsProcessing.setVisibility(8);
        if (file != null && file.exists()) {
            String path = file.getPath();
            JSONObject jSONObject = new JSONObject();
            String fileExtension = Utils.getFileExtension(z, getContentResolver(), uri);
            HVLogUtils.d(TAG, "onActivityResult: extension - " + fileExtension);
            if (fileExtension == null) {
                startRetakeScreen(path, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_READ_FILE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_READ_FILE_ERROR, getStringFromResources(R.string.docUploadReadFileError)).toString());
                return;
            }
            if (fileExtension.equals("pdf")) {
                if (Build.VERSION.SDK_INT >= 21) {
                    try {
                        PDFUtils.checkIfPDFIsReadable(uri, getContentResolver());
                    } catch (Exception e) {
                        Log.e(TAG, Utils.getErrorMessage(e));
                        if (e instanceof SecurityException) {
                            startRetakeScreen(path, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_PASSWORD_PROTECTED_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_PASSWORD_PROTECTED_ERROR, getStringFromResources(R.string.docUploadPasswordProtectedError)).toString());
                            return;
                        } else {
                            startRetakeScreen(path, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_READ_FILE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_READ_FILE_ERROR, getStringFromResources(R.string.docUploadReadFileError)).toString());
                            return;
                        }
                    }
                }
            } else {
                try {
                    ExifHelper exifHelper = new ExifHelper();
                    exifHelper.readExifFromFile(file, this.mLocation);
                    if (getDocConfig().isShouldExportPDF()) {
                        String convertImageToPDF = ImageToPDFConverter.convertImageToPDF(BitmapFactory.decodeFile(path), this.folder.getPath() + "/hv_" + System.currentTimeMillis() + ".pdf");
                        this.pdfPath = convertImageToPDF;
                        jSONObject.put("pdfUri", convertImageToPDF);
                    }
                    exifHelper.writeExifData(path, getTransactionId(), this.ipAddress);
                } catch (Exception e2) {
                    Log.e(TAG, Utils.getErrorMessage(e2));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                    }
                }
            }
            startReviewScreen(path, null);
            return;
        }
        startRetakeScreen(null, TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_UPLOAD_READ_FILE_ERROR, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_UPLOAD_READ_FILE_ERROR, getStringFromResources(R.string.docUploadReadFileError)).toString());
    }

    private boolean isSelectedFileExtentionInAllowedList(String str) {
        for (String str2 : getUploadMimeTypes()) {
            if (str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private void reInitCaptureButton() {
        HVLogUtils.d(TAG, "reInitCaptureButton() called");
        setCaptureButtonState(true, false);
        this.hasDelayFinished = false;
    }

    private void reInitTimingUtils() {
        try {
            this.docPickerScreenLoadSuccessTimingUtils.init();
            this.docPickerCaptureButtonClickTimingUtils.init();
            this.docPickerUploadButtonClickTimingUtils.init();
            this.screenLoadSuccessTimingUtils.init();
            this.imageCaptureTimingUtils.init();
            this.permissionTimingUtils.init();
            this.captureClickTimingUtils.init();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
    }

    public void makeOCRAPICall(final String str, String str2) {
        Log.d(TAG, "makeOCRAPICall() called with: filePath = [" + str + "], capturedHighResolutionQRCroppedImagePath = [" + str2 + "]");
        JSONObject ocrHeaders = getDocConfig().getOcrHeaders();
        try {
            ocrHeaders.put(AppConstants.IS_DOCUMENT_UPLOADED, !this.isDocCaptureFlow);
            ocrHeaders.put("sdk-mode", InternalToolUtils.getSdkMode(this));
            getDocConfig().ocrHeaders = ocrHeaders.toString();
        } catch (Exception e) {
            Log.e(TAG, "makeOCRAPICall() ocrHeaders:" + Utils.getErrorMessage(e));
        }
        if (getDocConfig().isShouldAutoCapture()) {
            runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda22
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m465x90799f64();
                }
            });
        }
        Spanned text = TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_LOADER_DESC, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOADER_DESC, getStringFromResources(R.string.hv_doc_loader_subtitle));
        showProgressDialog(true, text != null ? text.toString() : null);
        this.shouldAllowActivityClose = false;
        DocOCRHelper.get().makeOcrAPICall(this, str, str2, getDocConfig(), new DocOCRHelper.DocOCRListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda19
            @Override // co.hyperverge.hypersnapsdk.helpers.DocOCRHelper.DocOCRListener
            public final void onResult(boolean z, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, HVError hVError) {
                HVDocsActivity.this.m468x30d7e4c(str, z, str3, str4, jSONObject, jSONObject2, hVError);
            }
        });
    }

    /* renamed from: lambda$makeOCRAPICall$18$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m465x90799f64() {
        TextView textView = this.instructionsOutsideCameraPreviewBox;
        if (textView != null) {
            textView.setVisibility(0);
            this.instructionsOutsideCameraPreviewBox.setText(TextConfigUtils.getText(getDocConfig().getCustomUIStrings(), CustomTextStringConst.DocCaptureTextConfigs.DOC_LOADER_TITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_LOADER_TITLE, getStringFromResources(R.string.hv_doc_loader_title)));
        }
    }

    /* renamed from: lambda$makeOCRAPICall$21$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m468x30d7e4c(final String str, final boolean z, final String str2, final String str3, final JSONObject jSONObject, final JSONObject jSONObject2, final HVError hVError) {
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                HVDocsActivity.this.m467xe8f1ffad(str3, str2, z, jSONObject2, str, jSONObject, hVError);
            }
        });
    }

    /* renamed from: lambda$makeOCRAPICall$20$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m467xe8f1ffad(String str, String str2, boolean z, JSONObject jSONObject, String str3, JSONObject jSONObject2, HVError hVError) {
        showProgressDialog(false, null);
        this.shouldAllowActivityClose = true;
        this.retryAction = str;
        this.retakeMessage = str2;
        if (z) {
            if (this.hvResponse.getRetakeAttemptResponses() == null) {
                this.hvResponse.setRetakeAttemptResponses(this.hvResponses);
            }
            HVBaseResponse hVBaseResponse = new HVBaseResponse();
            hVBaseResponse.setAction(this.retryAction);
            hVBaseResponse.setApiHeaders(jSONObject);
            hVBaseResponse.setImageURI(str3);
            hVBaseResponse.setApiResult(addResultImageUri(jSONObject2, str3));
            hVBaseResponse.setRetakeMessage(this.retakeMessage);
            hVBaseResponse.setAttemptsCount(SPHelper.getAttemptsCountForImage(getDocConfig().ocrEndpoint, getDocConfig().getSuffixForDocument()));
            this.hvResponses.add(hVBaseResponse);
            startRetakeScreen(str3, str2);
            this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m466xaa951e03();
                }
            });
            return;
        }
        stopCamera();
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        JSONObject addResultImageUri = addResultImageUri(jSONObject2, str3);
        this.hvResponse.setAction(this.retryAction);
        this.hvResponse.setApiHeaders(jSONObject);
        this.hvResponse.setImageURI(str3);
        this.hvResponse.setApiResult(addResultImageUri);
        this.hvResponse.setRetakeMessage(this.retakeMessage);
        this.hvResponse.setAttemptsCount(SPHelper.getAttemptsCountForImage(getDocConfig().ocrEndpoint, getDocConfig().getSuffixForDocument()));
        this.hvResponse.setRetakeAttemptResponses(this.hvResponses);
        finishView(hVError, this.hvResponse);
    }

    /* renamed from: lambda$makeOCRAPICall$19$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m466xaa951e03() {
        this.docLoaderLayout.setVisibility(8);
        this.docInstructionsLayout.setVisibility(8);
        HVLottieHelper.reset(this.lav);
    }

    public JSONObject addResultImageUri(JSONObject jSONObject, String str) {
        String str2;
        try {
            if (getDocConfig().isShouldExportPDF() && (str2 = this.pdfPath) != null) {
                jSONObject.put("pdfUri", str2);
            }
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return jSONObject;
    }

    /* loaded from: classes2.dex */
    private class SaveBitmapTask extends AsyncTask<Void, Void, Void> {
        private final byte[] data;
        ImageSaveListener listener;
        private Bitmap mBitmap;
        Context mContext;

        public SaveBitmapTask(byte[] bArr, Context context, ImageSaveListener imageSaveListener) {
            this.data = bArr;
            this.mContext = context;
            this.listener = imageSaveListener;
        }

        public Bitmap cropBitmap(Bitmap bitmap) {
            try {
                double width = HVDocsActivity.this.getDocConfig().padding * bitmap.getWidth() * HVDocsActivity.this.document.getAspectRatio();
                HVDocsActivity.this.getBottomYOfBox();
                HVDocsActivity.this.getTopYOfBox();
                if (!HVDocsActivity.this.getDocConfig().isShouldSetPadding() || HVDocsActivity.this.document.getAspectRatio() > 1.0f) {
                    width = 0.0d;
                }
                HVDocsActivity.this.padding = width;
                int width2 = (int) (bitmap.getWidth() * (HVDocsActivity.this.getBoxRect().left / HVDocsActivity.this.camViewWidth));
                int height = (int) (((int) (bitmap.getHeight() * (HVDocsActivity.this.getTopYOfBox() / HVDocsActivity.this.camViewHeight))) - width);
                if (height < 0) {
                    height = 0;
                }
                int height2 = (int) ((((int) (bitmap.getHeight() * (HVDocsActivity.this.getBottomYOfBox() / HVDocsActivity.this.camViewHeight))) - height) + width);
                int width3 = ((int) (bitmap.getWidth() * (HVDocsActivity.this.getBoxRect().right / HVDocsActivity.this.camViewWidth))) - width2;
                if (height2 > bitmap.getHeight()) {
                    height2 = bitmap.getHeight();
                }
                if (height + height2 > bitmap.getHeight()) {
                    height = (int) (bitmap.getHeight() * (HVDocsActivity.this.getTopYOfBox() / HVDocsActivity.this.camViewHeight));
                    height2 = ((int) (bitmap.getHeight() * (HVDocsActivity.this.getBottomYOfBox() / HVDocsActivity.this.camViewHeight))) - height;
                    HVDocsActivity.this.getDocConfig().setShouldAddPadding(false);
                }
                return Bitmap.createBitmap(bitmap, width2, height, width3, height2);
            } catch (Exception | OutOfMemoryError e) {
                Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                    return null;
                }
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            Bitmap rotateBitmap;
            int orientation = Exif.getOrientation(this.data);
            try {
                byte[] bArr = this.data;
                rotateBitmap = FileHelper.rotateBitmap(BitmapFactory.decodeByteArray(bArr, 0, bArr.length), Utils.checkForOrientationCorrection(orientation));
            } catch (Exception | OutOfMemoryError e) {
                Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            if (rotateBitmap == null) {
                return null;
            }
            Bitmap cropBitmap = cropBitmap(rotateBitmap);
            HVDocsActivity.this.fullHRImageFile = new File(HVDocsActivity.this.capturedHighResolutionFullImagePath);
            FileOutputStream fileOutputStream = new FileOutputStream(HVDocsActivity.this.fullHRImageFile);
            cropBitmap.compress(Bitmap.CompressFormat.JPEG, Utils.JPEG_COMPRESSION_QUALITY, fileOutputStream);
            fileOutputStream.close();
            if (cropBitmap == null) {
                return null;
            }
            if (HVDocsActivity.this.getDocConfig().isShouldReadNIDQR()) {
                Bitmap cropQR = Utils.cropQR(cropBitmap);
                HVDocsActivity.this.qrCroppedFile = new File(HVDocsActivity.this.capturedHighResolutionQRCroppedImagePath);
                try {
                    if (cropQR == null) {
                        return null;
                    }
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(HVDocsActivity.this.qrCroppedFile);
                        try {
                            cropQR.compress(Bitmap.CompressFormat.JPEG, Utils.JPEG_COMPRESSION_QUALITY, fileOutputStream2);
                            fileOutputStream2.close();
                        } catch (Throwable th) {
                            try {
                                fileOutputStream2.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        Log.e(HVDocsActivity.TAG, Utils.getErrorMessage(e2));
                    }
                } finally {
                    cropQR.recycle();
                }
            }
            this.mBitmap = Utils.resizeBitmap(cropBitmap);
            cropBitmap.recycle();
            if (this.mBitmap == null) {
                return null;
            }
            HVDocsActivity.this.compressedFile = new File(HVDocsActivity.this.capturedImagePath);
            FileOutputStream fileOutputStream3 = new FileOutputStream(HVDocsActivity.this.compressedFile);
            this.mBitmap.compress(Bitmap.CompressFormat.JPEG, Utils.JPEG_COMPRESSION_QUALITY, fileOutputStream3);
            fileOutputStream3.close();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.listener.onImageSave(HVDocsActivity.this.capturedImagePath, this.mBitmap);
            super.onPostExecute((SaveBitmapTask) r4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStringFromResources(int i) {
        return getResources().getString(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCaptureButtonState(final boolean z, final boolean z2) {
        if (this.btCapture == null) {
            HVLogUtils.d(TAG, "setCaptureButtonState() returning since btCapture is null");
        } else {
            runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocsActivity$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocsActivity.this.m477xb7526a84(z, z2);
                }
            });
        }
    }

    /* renamed from: lambda$setCaptureButtonState$22$co-hyperverge-hypersnapsdk-activities-HVDocsActivity, reason: not valid java name */
    public /* synthetic */ void m477xb7526a84(boolean z, boolean z2) {
        if (z) {
            this.btCapture.setVisibility(0);
        } else {
            this.btCapture.setVisibility(4);
        }
        if (z2) {
            this.btCapture.setImageResource(R.drawable.ic_camera_button_svg);
            HyperSnapUIConfigUtil.getInstance().customiseCaptureButton(this.btCapture);
            this.btCapture.setClickable(true);
            this.btCapture.setEnabled(true);
            return;
        }
        this.btCapture.setImageResource(R.drawable.hv_camera_button_disabled);
        ImageViewCompat.setImageTintList(this.btCapture, null);
        this.btCapture.setClickable(false);
        this.btCapture.setEnabled(false);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(TAG, "onConfigurationChanged: newConfig = " + configuration);
    }
}
