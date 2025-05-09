package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import co.hyperverge.hvcamera.HVCamHost;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hvqrmodule.objects.HVQRConfig;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.HVCameraHelper;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.listeners.QRCodeCompletionHandler;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import co.hyperverge.hypersnapsdk.utils.PermissionManager;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.views.CrossHairView;
import co.hyperverge.hypersnapsdk.views.RoundedRectangleView;
import co.hyperverge.hypersnapsdk.views.ScanningIndicator;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVQRScannerActivityInternal extends HVQRBaseActivity {
    private static final String ARG_HV_QR_CONFIG = "hv_qr_config";
    private static final String TAG = "co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal";
    private int camViewHeight;
    private int camViewWidth;
    private FrameLayout cameraContainer;
    private HVMagicView cameraView;
    private CrossHairView crossHairView;
    private float density;
    private BarcodeDetector detector;
    private HVQRConfig hvqrConfig;
    private ScanningIndicator indicator;
    private float lastTouchX;
    private float lastTouchY;
    private PermissionManager permissionManager;
    private ConstraintLayout rlInstructions;
    private RoundedRectangleView rrView;
    private MaterialButton skipButton;
    private TextView tvHint;
    private final String camViewTag = "qrCaptureCameraPreview";
    private boolean barcodeAvailable = false;
    private boolean isTestCountdownTimerFinished = false;
    private final HVCamHost camHost = new HVCamHost() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal.1
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
        public File getPhotoDirectory() {
            return null;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public String getPhotoFilename() {
            return null;
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public float getPictureMegapixels() {
            return 1.3f;
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
        public boolean isShouldCaptureHighResolutionImage() {
            return false;
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
        public void onFlashAuto() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashNull() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onFlashOff() {
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
        public void onPictureFailed() {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureReady(byte[] bArr) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureSaved(File file) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureSizeSet(int i, int i2) {
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onPictureTaken() {
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

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void showCrossHair(final float f, final float f2, final boolean z) {
            HVLogUtils.d(HVQRScannerActivityInternal.TAG, "showCrossHair() called with: x = [" + f + "], y = [" + f2 + "], b = [" + z + "]");
            if (HVQRScannerActivityInternal.this.crossHairView != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (f > 0.0f || f2 > 0.0f) {
                            HVQRScannerActivityInternal.this.crossHairView.showCrosshair(f * HVQRScannerActivityInternal.this.camViewWidth, f2 * HVQRScannerActivityInternal.this.camViewHeight, z);
                        } else {
                            HVQRScannerActivityInternal.this.crossHairView.showCrosshair(HVQRScannerActivityInternal.this.camViewWidth / 2, HVQRScannerActivityInternal.this.camViewHeight / 2, z);
                        }
                    }
                });
            }
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onNewPreviewFrame(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2) {
            Bitmap barcodeImageBitmap;
            if (HVQRScannerActivityInternal.this.barcodeAvailable || HVQRScannerActivityInternal.this.detector == null || !HVQRScannerActivityInternal.this.detector.isOperational()) {
                return;
            }
            Frame build = new Frame.Builder().setImageData(ByteBuffer.wrap(bArr), i, i2, 17).build();
            if (InternalToolUtils.isTestMode(HVQRScannerActivityInternal.this) && HVQRScannerActivityInternal.this.isTestCountdownTimerFinished && (barcodeImageBitmap = InternalToolUtils.getBarcodeImageBitmap()) != null) {
                build = new Frame.Builder().setBitmap(barcodeImageBitmap).build();
            }
            SparseArray<Barcode> detect = HVQRScannerActivityInternal.this.detector.detect(build);
            if (detect.size() != 0) {
                Barcode valueAt = detect.valueAt(0);
                String str = valueAt.rawValue;
                HVQRScannerActivityInternal.this.barcodeAvailable = true;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("status", "success");
                    jSONObject.put("qr-code", valueAt.rawValue);
                    HVQRScannerActivityInternal.this.sendResponse(jSONObject, null);
                } catch (JSONException e) {
                    HVLogUtils.e(HVQRScannerActivityInternal.TAG, "initErrorMonitoring(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                    Log.e(HVQRScannerActivityInternal.TAG, Utils.getErrorMessage(e));
                }
            }
        }

        @Override // co.hyperverge.hvcamera.HVCamHost
        public void onViewDimensionChange(int i, int i2) {
            HVLogUtils.d(HVQRScannerActivityInternal.TAG, "onViewDimensionChange() called with: width = [" + i + "], height = [" + i2 + "]");
            HVQRScannerActivityInternal.this.camViewHeight = i2;
            HVQRScannerActivityInternal.this.camViewWidth = i;
            HVQRScannerActivityInternal.this.adjustQRScannerView();
            HVQRScannerActivityInternal.this.adjustCrossHairView();
        }
    };

    public static void start(Context context, HVQRConfig hVQRConfig, QRCodeCompletionHandler qRCodeCompletionHandler) {
        HVLogUtils.d(TAG, "start() called with: context = [" + context + "], config = [" + hVQRConfig + "], handler = [" + qRCodeCompletionHandler + "]");
        Intent intent = new Intent(context, (Class<?>) HVQRScannerActivityInternal.class);
        if (hVQRConfig == null) {
            hVQRConfig = new HVQRConfig();
        }
        intent.putExtra(ARG_HV_QR_CONFIG, hVQRConfig);
        CallbackProvider.get().setCallback(qRCodeCompletionHandler);
        context.startActivity(intent);
    }

    public void checkForPermissions() {
        HVLogUtils.d(TAG, "checkForPermissions() called");
        ArrayList arrayList = new ArrayList(Collections.singletonList("android.permission.CAMERA"));
        this.permissionManager.checkAndRequestPermissions(this, arrayList);
        if (this.permissionManager.getStatus(this, arrayList).denied.isEmpty()) {
            startQRCapture();
        }
    }

    public void startQRCapture() {
        HVLogUtils.d(TAG, "startQRCapture() called");
        this.density = getResources().getDisplayMetrics().density;
        new Handler().postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                HVQRScannerActivityInternal.this.m500xa94164d5();
            }
        }, this.hvqrConfig.getSkipButtonDelay());
        createQRCamera();
    }

    /* renamed from: lambda$startQRCapture$0$co-hyperverge-hypersnapsdk-activities-HVQRScannerActivityInternal, reason: not valid java name */
    public /* synthetic */ void m500xa94164d5() {
        this.skipButton.setVisibility(0);
    }

    public void stopCamera() {
        HVLogUtils.d(TAG, "stopCamera() called");
        if (this.cameraView != null) {
            this.skipButton.setVisibility(8);
            this.cameraView.setSensorCallback(null);
            this.cameraView.onDestroy();
            this.cameraView.onPause();
        }
    }

    public void sendResponse(final JSONObject jSONObject, final HVError hVError) {
        HVLogUtils.d(TAG, "sendResponse() called with: result = [" + jSONObject + "], error = [" + hVError + "]");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                HVQRScannerActivityInternal.this.m499x549260fc(hVError, jSONObject);
            }
        });
    }

    /* renamed from: lambda$sendResponse$1$co-hyperverge-hypersnapsdk-activities-HVQRScannerActivityInternal, reason: not valid java name */
    public /* synthetic */ void m499x549260fc(HVError hVError, JSONObject jSONObject) {
        stopCamera();
        this.isTestCountdownTimerFinished = false;
        QRCodeCompletionHandler injectQRCodeCallback = CallbackProvider.get().injectQRCodeCallback();
        if (injectQRCodeCallback != null) {
            HVQRConfig hVQRConfig = this.hvqrConfig;
            callCompletionHandler(hVQRConfig != null ? hVQRConfig.getModuleId() : null, this, injectQRCodeCallback, hVError, jSONObject);
        }
        finish();
    }

    public void closeScreen() {
        HVLogUtils.d(TAG, "closeScreen() called");
        boolean shouldShowModuleBackButton = this.hvqrConfig.shouldShowModuleBackButton();
        boolean shouldShowInstructions = this.hvqrConfig.shouldShowInstructions();
        boolean z = this.rlInstructions.getVisibility() == 0;
        if (shouldShowModuleBackButton || (shouldShowInstructions && !z)) {
            if (shouldShowInstructions && !z) {
                showInstructions(true);
            } else {
                sendResponse(null, new HVError(3, getResources().getString(R.string.operation_cancelled)));
            }
        }
    }

    private void findViews() {
        HVLogUtils.d(TAG, "findViews() called");
        this.cameraContainer = (FrameLayout) findViewById(R.id.flCameraContainer);
        this.rlInstructions = (ConstraintLayout) findViewById(R.id.layoutQRInstructions);
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.btnSkip);
        this.skipButton = materialButton;
        materialButton.setEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.ivBack);
        ImageView imageView2 = (ImageView) this.rlInstructions.findViewById(R.id.ivBack);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVQRScannerActivityInternal.this.m496xf730e1af(view);
            }
        };
        imageView.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        if (!this.hvqrConfig.shouldShowModuleBackButton()) {
            if (this.hvqrConfig.shouldShowInstructions()) {
                imageView2.setVisibility(4);
            } else {
                imageView.setVisibility(4);
            }
        }
        Button button = (Button) this.rlInstructions.findViewById(R.id.btnProceed);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVQRScannerActivityInternal.this.m497xd2f25d70(view);
            }
        });
        this.skipButton.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVQRScannerActivityInternal.this.m498xaeb3d931(view);
            }
        });
    }

    /* renamed from: lambda$findViews$2$co-hyperverge-hypersnapsdk-activities-HVQRScannerActivityInternal, reason: not valid java name */
    public /* synthetic */ void m496xf730e1af(View view) {
        closeScreen();
    }

    /* renamed from: lambda$findViews$3$co-hyperverge-hypersnapsdk-activities-HVQRScannerActivityInternal, reason: not valid java name */
    public /* synthetic */ void m497xd2f25d70(View view) {
        view.setEnabled(false);
        showInstructions(false);
        checkForPermissions();
    }

    /* renamed from: lambda$findViews$4$co-hyperverge-hypersnapsdk-activities-HVQRScannerActivityInternal, reason: not valid java name */
    public /* synthetic */ void m498xaeb3d931(View view) {
        view.setEnabled(false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", "skipped");
            jSONObject.put("qr-code", "");
            sendResponse(jSONObject, null);
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "findViews(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
        }
    }

    private void showInstructions(boolean z) {
        HVLogUtils.d(TAG, "showInstructions() called with: show = [" + z + "]");
        this.rlInstructions.setVisibility(z ? 0 : 8);
        if (z) {
            TextView textView = (TextView) this.rlInstructions.findViewById(R.id.tvTitle);
            HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView);
            TextView textView2 = (TextView) this.rlInstructions.findViewById(R.id.tvSubtitle);
            HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView2);
            Button button = (Button) this.rlInstructions.findViewById(R.id.btnProceed);
            button.setEnabled(true);
            HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(button);
            HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(this.rlInstructions);
            HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) this.rlInstructions.findViewById(R.id.ivBack));
            HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) this.rlInstructions.findViewById(R.id.clientLogo));
            setupBranding(this.rlInstructions);
            HVJSONObject customUIStrings = this.hvqrConfig.getCustomUIStrings();
            if (customUIStrings != null) {
                Spanned text = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_INSTRUCTIONS_TITLE, getString(R.string.hv_qr_instruction_title));
                if (text != null) {
                    textView.setText(text);
                }
                Spanned text2 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_INSTRUCTIONS_DESC, getString(R.string.hv_qr_instruction_subtitle));
                if (text2 != null) {
                    textView2.setText(text2);
                }
                Spanned text3 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_INSTRUCTIONS_BUTTON, getString(R.string.hv_proceed_to_scan_qr));
                if (text3 != null) {
                    button.setText(text3);
                }
            }
            loadAnimation();
        }
    }

    private void loadAnimation() {
        HVLogUtils.d(TAG, "loadAnimation() called");
        HVLottieHelper.load((LottieAnimationView) findViewById(R.id.lavQRInstructions), HVLottieHelper.QR_INSTRUCTION, HVLottieHelper.State.START, null);
    }

    private void addHintTextView() {
        HVLogUtils.d(TAG, "addHintTextView() called");
        this.tvHint = (TextView) findViewById(R.id.tvHint);
        if (this.hvqrConfig.getCustomUIStrings() != null) {
            this.tvHint.setText(TextConfigUtils.getText(this.hvqrConfig.getCustomUIStrings(), "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAPTURE_SUB_TEXT, getString(R.string.hv_qr_capture_info_label)));
        }
        this.tvHint.setBackgroundResource(R.drawable.hv_rounded_button_white);
        int dpToPx = UIUtils.dpToPx(this, 12.0f);
        int dpToPx2 = UIUtils.dpToPx(this, 4.0f);
        this.tvHint.setPadding(dpToPx, dpToPx2, dpToPx, dpToPx2);
        this.tvHint.setTextColor(ContextCompat.getColor(this, R.color.title_text_color));
        this.tvHint.setTextSize(12.0f);
        this.tvHint.setGravity(49);
        this.tvHint.setVisibility(0);
        this.cameraContainer.removeView(this.tvHint);
        this.cameraContainer.addView(this.tvHint, new FrameLayout.LayoutParams(-2, -2, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustQRScannerView() {
        HVLogUtils.d(TAG, "adjustQRScannerView() called");
        if (this.rrView.getParent() != null) {
            int i = this.camViewWidth;
            int bottomYOfBox = getBottomYOfBox() - getTopYOfBox();
            int i2 = (int) (this.camViewWidth * 0.15d);
            this.rrView.setY(0.0f);
            float f = i2;
            float f2 = bottomYOfBox - i2;
            this.rrView.setBoxRect(new RectF(f, f, i - i2, f2), 0.1f);
            this.rrView.requestLayout();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.indicator.getLayoutParams();
            layoutParams.setMargins(0, i2, 0, 0);
            this.indicator.setLayoutParams(layoutParams);
            this.indicator.setmHeight(bottomYOfBox - (i2 * 2));
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.tvHint.getLayoutParams();
            this.tvHint.setY(f2 - (r1.getMeasuredHeight() * 1.5f));
            this.tvHint.setLayoutParams(layoutParams2);
        }
        this.cameraContainer.requestLayout();
        this.indicator.startAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustCrossHairView() {
        HVLogUtils.d(TAG, "adjustCrossHairView() called");
        if (this.crossHairView.getParent() != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.crossHairView.getLayoutParams();
            layoutParams.height = this.camViewHeight;
            layoutParams.width = this.camViewWidth;
            this.crossHairView.setX(this.cameraView.getX());
            this.crossHairView.setY(this.cameraView.getY());
            this.crossHairView.requestLayout();
        }
        this.cameraContainer.requestLayout();
    }

    private void adjustSkipButton() {
        HVLogUtils.d(TAG, "adjustSkipButton() called");
        this.cameraContainer.removeView(this.skipButton);
        this.cameraContainer.addView(this.skipButton, new FrameLayout.LayoutParams(-2, -2, 1));
    }

    private int getTopYOfBox() {
        int i = (int) getBoxRect().top;
        HVLogUtils.d(TAG, "getTopYOfBox() returned: " + i);
        return i;
    }

    private int getBottomYOfBox() {
        int i = (int) getBoxRect().bottom;
        HVLogUtils.d(TAG, "getBottomYOfBox() returned: " + i);
        return i;
    }

    private RectF getBoxRect() {
        int i;
        int i2;
        String str = TAG;
        HVLogUtils.d(str, "getBoxRect() called");
        float aspectRatio = this.hvqrConfig.getHVBarcodeType().getAspectRatio();
        int width = this.cameraContainer.getWidth();
        int min = Math.min(this.camViewHeight, this.cameraContainer.getHeight());
        if (aspectRatio <= 1.0f) {
            i2 = (int) (aspectRatio * width);
            i = width;
        } else {
            i = (int) (min / aspectRatio);
            i2 = min;
        }
        RectF rectF = new RectF((width - i) / 2, (min - i2) / 2, (width + i) / 2, (min + i2) / 2);
        HVLogUtils.d(str, "getBoxRect() returned: " + rectF);
        return rectF;
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$2] */
    private void createQRCamera() {
        HVLogUtils.d(TAG, "createQRCamera() called");
        HVCameraHelper.enableCameraParameters(this, true);
        CameraEngine.setPreviewCallback(true);
        CameraEngine.setCaptureMode(true);
        this.rrView = new RoundedRectangleView(this);
        this.indicator = new ScanningIndicator(this, getBottomYOfBox() - getTopYOfBox());
        HVMagicView hVMagicView = HVMagicView.getInstance(this, this.camHost, false);
        this.cameraView = hVMagicView;
        hVMagicView.setContentDescription("qrCaptureCameraPreview");
        this.cameraView.disableRotation();
        int i = AnonymousClass4.$SwitchMap$co$hyperverge$hvqrmodule$objects$HVQRConfig$HVBarcodeType[this.hvqrConfig.getHVBarcodeType().ordinal()];
        BarcodeDetector build = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(i != 1 ? i != 2 ? i != 3 ? i != 4 ? 6416 : 2048 : 16 : 4096 : 256).build();
        this.detector = build;
        if (!build.isOperational()) {
            sendResponse(null, new HVError(60, "QR Scanner detector not available. Please try again after sometime "));
            return;
        }
        this.cameraContainer.addView(this.cameraView);
        addCrossHairView(this.cameraContainer);
        adjustCrossHairView();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (UIUtils.getScreenWidth() * 0.7d), UIUtils.dpToPx(this, 15.0f));
        layoutParams.gravity = 1;
        this.indicator.setLayoutParams(layoutParams);
        this.indicator.setImageResource(R.drawable.hv_ic_camera_qr_status);
        this.cameraContainer.addView(this.indicator);
        this.rrView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.cameraContainer.addView(this.rrView);
        addHintTextView();
        adjustQRScannerView();
        HVMagicView hVMagicView2 = this.cameraView;
        if (hVMagicView2 != null) {
            hVMagicView2.onResume();
        }
        HyperSnapUIConfigUtil.getInstance().customiseDocumentSideTextView((TextView) findViewById(R.id.tvHint));
        if (InternalToolUtils.isTestMode(this)) {
            new CountDownTimer(this.hvqrConfig.getSkipButtonDelay() + 2000, 1000L) { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal.2
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    HVQRScannerActivityInternal.this.isTestCountdownTimerFinished = true;
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$4, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hvqrmodule$objects$HVQRConfig$HVBarcodeType;

        static {
            int[] iArr = new int[HVQRConfig.HVBarcodeType.values().length];
            $SwitchMap$co$hyperverge$hvqrmodule$objects$HVQRConfig$HVBarcodeType = iArr;
            try {
                iArr[HVQRConfig.HVBarcodeType.QR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hvqrmodule$objects$HVQRConfig$HVBarcodeType[HVQRConfig.HVBarcodeType.AZTEC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hvqrmodule$objects$HVQRConfig$HVBarcodeType[HVQRConfig.HVBarcodeType.DATA_MATRIX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hvqrmodule$objects$HVQRConfig$HVBarcodeType[HVQRConfig.HVBarcodeType.PDF417.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void addCrossHairView(FrameLayout frameLayout) {
        HVLogUtils.d(TAG, "addCrossHairView() called with: cameraContainer = [" + frameLayout + "]");
        CrossHairView crossHairView = new CrossHairView(this);
        this.crossHairView = crossHairView;
        frameLayout.addView(crossHairView);
        frameLayout.setOnTouchListener(new View.OnTouchListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal$$ExternalSyntheticLambda3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HVQRScannerActivityInternal.this.m495xc240e9fd(view, motionEvent);
            }
        });
    }

    /* renamed from: lambda$addCrossHairView$5$co-hyperverge-hypersnapsdk-activities-HVQRScannerActivityInternal, reason: not valid java name */
    public /* synthetic */ boolean m495xc240e9fd(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.lastTouchX = motionEvent.getX();
            this.lastTouchY = motionEvent.getY();
        } else if (actionMasked == 1 && Math.abs(motionEvent.getX() - this.lastTouchX) < 20.0f && Math.abs(motionEvent.getY() - this.lastTouchY) < 20.0f) {
            this.crossHairView.showCrosshair(motionEvent.getX(), motionEvent.getY(), false);
            this.cameraView.onTouchToFocus(motionEvent.getX() / this.camViewWidth, motionEvent.getY() / this.camViewHeight, null);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        HVLogUtils.d(TAG, "onStart() called");
        if (this.hvqrConfig.shouldShowInstructions() && this.rlInstructions.getVisibility() == 0) {
            return;
        }
        checkForPermissions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        HVLogUtils.d(TAG, "onPause() called");
        try {
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.onPause();
            }
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "onPause(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        HVLogUtils.d(TAG, "onBackPressed() called");
        closeScreen();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        HVLogUtils.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + i + "], permissions = [" + strArr + "], grantResults = [" + iArr + "]");
        final PermissionManager.StatusArray status = this.permissionManager.getStatus(this, new ArrayList(Collections.singletonList("android.permission.CAMERA")));
        if (!status.denied.isEmpty()) {
            showCameraPermissionAlert(this.hvqrConfig.getCustomUIStrings(), new PermDialogCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRScannerActivityInternal.3
                @Override // co.hyperverge.hypersnapsdk.listeners.PermDialogCallback
                public void onActionClick() {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(HVQRScannerActivityInternal.this, "android.permission.CAMERA")) {
                        HVQRScannerActivityInternal.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + HVQRScannerActivityInternal.this.getApplicationContext().getPackageName())));
                        return;
                    }
                    HVQRScannerActivityInternal.this.checkForPermissions();
                }

                @Override // co.hyperverge.hypersnapsdk.listeners.PermDialogCallback
                public void onCancel() {
                    String join = TextUtils.join(",", status.denied);
                    HVQRScannerActivityInternal.this.sendResponse(null, new HVError(4, "Following Permissions not granted by user: " + join));
                    HVQRScannerActivityInternal.this.finish();
                }
            });
        } else {
            startQRCapture();
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("hvqrConfig", new Gson().toJson(this.hvqrConfig));
        HVLogUtils.d(TAG, "onSaveInstanceState() called with: outState = [" + bundle + "]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hypersnapsdk.activities.HVQRBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String str = TAG;
        HVLogUtils.d(str, "onCreate() called with: savedInstanceState = [" + bundle + "]");
        setContentView(R.layout.hv_activity_qrscanner);
        if (bundle != null) {
            HVLogUtils.d(str, "onCreate() savedInstance is not null, finishing activity");
            this.hvqrConfig = (HVQRConfig) new Gson().fromJson(bundle.getString("hvqrConfig"), HVQRConfig.class);
        }
        getWindow().getDecorView().getRootView().setTag("qrCaptureCameraPreview");
        HVQRConfig hVQRConfig = (HVQRConfig) getIntent().getSerializableExtra(ARG_HV_QR_CONFIG);
        this.hvqrConfig = hVQRConfig;
        if (hVQRConfig == null) {
            HVError hVError = new HVError(6, getResources().getString(R.string.qr_config_error));
            HVLogUtils.d(str, "start: error = [" + hVError + "]");
            HVQRConfig hVQRConfig2 = this.hvqrConfig;
            callCompletionHandler(hVQRConfig2 != null ? hVQRConfig2.getModuleId() : null, this, CallbackProvider.get().injectQRCodeCallback(), hVError, null);
            return;
        }
        findViews();
        try {
            if (getIntent() != null && getIntent().getStringExtra("customUIStrings") != null) {
                new JSONObject(getIntent().getStringExtra("customUIStrings"));
            }
        } catch (JSONException e) {
            String str2 = TAG;
            String message = e.getMessage();
            Objects.requireNonNull(message);
            Log.e(str2, message);
        }
        this.permissionManager = new PermissionManager();
        showInstructions(this.hvqrConfig.shouldShowInstructions());
        TextView textView = (TextView) findViewById(R.id.tvTitle);
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView, true);
        TextView textView2 = (TextView) findViewById(R.id.tvSubtitle);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView2, true);
        HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) findViewById(R.id.ivBack), true);
        HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) findViewById(R.id.clientLogo), true);
        if (!this.hvqrConfig.shouldShowInstructions() && !this.hvqrConfig.shouldShowModuleBackButton()) {
            findViewById(R.id.ivBack).setVisibility(4);
        }
        if (this.hvqrConfig.getCustomUIStrings() != null) {
            HVJSONObject customUIStrings = this.hvqrConfig.getCustomUIStrings();
            Spanned text = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAPTURE_TITLE, getString(R.string.hv_qr_capture_title));
            if (text != null) {
                textView.setText(text);
            }
            Spanned text2 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAPTURE_DESC, getString(R.string.hv_qr_capture_subtitle));
            if (text2 != null) {
                textView2.setText(text2);
            }
            Spanned text3 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAPTURE_SKIP_TEXT, getString(R.string.hv_qr_capture_skip));
            if (text3 != null) {
                this.skipButton.setText(text3);
            }
            HyperSnapUIConfigUtil.getInstance().customiseSecondaryButton((Button) this.skipButton);
        }
        setupBranding(null);
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVQRBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        HVLogUtils.d(TAG, "onResume() called");
        try {
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView != null) {
                hVMagicView.onResume();
            }
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "onResume(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVQRBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(TAG, "onConfigurationChanged: newConfig = " + configuration);
    }
}
