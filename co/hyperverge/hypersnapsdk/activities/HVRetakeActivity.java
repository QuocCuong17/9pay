package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.PDFUtils;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class HVRetakeActivity extends HVBaseActivity {
    public static final String ASPECT_RATIO_TAG = "aspectRatio";
    public static final String CALLING_ACTIVITY_TAG = "callingActivity";
    public static final int CLOSE_ACTION = 20;
    public static final String CONFIG_TAG = "config";
    public static final String DOC_CALLING_ACTIVITY_VALUE = "Doc";
    public static final int ERROR_ACTION = 18;
    public static final String EXTRA_PADDING_TAG = "extraPadding";
    public static final String FACE_CALLING_ACTIVITY_VALUE = "Face";
    public static final String FACE_TAG = "face";
    public static final String IMAGE_URI_TAG = "imageUri";
    public static final String RADIUS_TAG = "radius";
    public static final int RECHECK_ACTION = 21;
    public static final String RETRY_MESSAGE_TAG = "retryMessage";
    public static final String SET_PADDING_TAG = "setPadding";
    private float aspectRatio;
    private String bitmapNullErrorString;
    private String callingActivity;
    private ContentLoadingProgressBar clProgressBar;
    private HVBaseConfig config;
    private CardView cvPdfPageSwitcher;
    private ImageView errorPlaceHolderImageView;
    private FloatingActionButton fabNext;
    private FloatingActionButton fabPrevious;
    private int height;
    private ImageView ivBack;
    private double padding;
    private int radius;
    private MaterialButton retakeButton;
    private String retryMessage;
    private ImageView reviewImage;
    private TextView tvError;
    private TextView tvPage;
    private TextView tvTitle;
    private int width;
    private final String TAG = getClass().getSimpleName();
    private final TimingUtils retakeScreenOpenedTimingUtils = new TimingUtils();
    private final TimingUtils retakeButtonClickTimingUtils = new TimingUtils();
    private String filePath = "";
    private boolean isShouldSetPadding = false;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldAllowActivityClose() {
        return true;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldShowCloseAlert() {
        return false;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        HVLogUtils.d(this.TAG, "onCreate() called with: savedInstanceState = [" + bundle + "]");
        this.retakeScreenOpenedTimingUtils.init();
        setContentView(R.layout.hv_activity_retake);
        if (bundle != null) {
            HVLogUtils.d(this.TAG, "onCreate() savedInstance is not null, finishing activity");
            finish();
            return;
        }
        findViews();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logIdCaptureReviewScreenLaunched();
        }
        Intent intent = getIntent();
        this.filePath = intent.getStringExtra(IMAGE_URI_TAG);
        if (intent.hasExtra(EXTRA_PADDING_TAG)) {
            this.padding = intent.getDoubleExtra(EXTRA_PADDING_TAG, 0.0d);
        }
        if (intent.hasExtra(SET_PADDING_TAG)) {
            this.isShouldSetPadding = intent.getBooleanExtra(SET_PADDING_TAG, false);
        }
        if (intent.hasExtra(ASPECT_RATIO_TAG)) {
            this.aspectRatio = intent.getFloatExtra(ASPECT_RATIO_TAG, 0.0f);
        }
        if (intent.hasExtra(FACE_CALLING_ACTIVITY_VALUE)) {
            intent.getBooleanExtra(FACE_CALLING_ACTIVITY_VALUE, false);
        }
        if (intent.hasExtra(RADIUS_TAG)) {
            this.radius = intent.getIntExtra(RADIUS_TAG, 0);
        }
        this.config = (HVBaseConfig) intent.getSerializableExtra(CONFIG_TAG);
        if (intent.hasExtra(RETRY_MESSAGE_TAG)) {
            this.retryMessage = intent.getStringExtra(RETRY_MESSAGE_TAG);
        }
        if (intent.hasExtra(CALLING_ACTIVITY_TAG)) {
            this.callingActivity = intent.getStringExtra(CALLING_ACTIVITY_TAG);
        }
        setTypefaces();
        HVJSONObject hVJSONObject = null;
        setupBranding(null);
        adjustDescText();
        if (getConfig() instanceof HVDocConfig) {
            adjustTitleText();
        }
        try {
            if (getConfig() instanceof HVDocConfig) {
                hVJSONObject = ((HVDocConfig) getConfig()).getCustomUIStrings();
            } else if (getConfig() instanceof HVFaceConfig) {
                hVJSONObject = ((HVFaceConfig) getConfig()).getCustomUIStrings();
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "onCreate(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        long longValue = this.retakeScreenOpenedTimingUtils.getTimeDifferenceLong().longValue();
        if (this.callingActivity.contains(FACE_CALLING_ACTIVITY_VALUE)) {
            this.bitmapNullErrorString = "Error while processing the face image";
            if (getConfig().getErrorReviewTitle() != null && !getConfig().getErrorReviewTitle().isEmpty()) {
                this.tvTitle.setText(getConfig().getErrorReviewTitle());
            } else {
                Spanned text = TextConfigUtils.getText(hVJSONObject, CustomTextStringConst.FaceCaptureTextConfigs.FACE_RETAKE_TITLE, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_RETAKE_TITLE);
                if (text != null) {
                    this.tvTitle.setText(text);
                }
            }
            if (getConfig().getErrorReviewRetakeButton() != null && !getConfig().getErrorReviewRetakeButton().isEmpty()) {
                this.retakeButton.setText(getConfig().getErrorReviewRetakeButton());
            } else {
                Spanned text2 = TextConfigUtils.getText(hVJSONObject, CustomTextStringConst.FaceCaptureTextConfigs.FACE_RETAKE_BUTTON, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_RETAKE_BUTTON);
                if (text2 != null) {
                    this.retakeButton.setText(text2);
                }
            }
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieRetakeScreenLoadSuccess(longValue);
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieRetakeScreenLaunched();
            }
            this.tvTitle.setVisibility(8);
        }
        if (this.callingActivity.contains(DOC_CALLING_ACTIVITY_VALUE)) {
            this.bitmapNullErrorString = "Error while processing the document";
            if (getConfig().getErrorReviewTitle() != null && !getConfig().getErrorReviewTitle().isEmpty()) {
                this.tvTitle.setText(getConfig().getErrorReviewTitle());
            } else {
                Spanned text3 = TextConfigUtils.getText(hVJSONObject, CustomTextStringConst.DocCaptureTextConfigs.DOC_RETAKE_TITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_RETAKE_TITLE);
                if (text3 != null) {
                    this.tvTitle.setText(text3);
                }
            }
            if (getConfig().getErrorReviewRetakeButton() != null && !getConfig().getErrorReviewRetakeButton().isEmpty()) {
                this.retakeButton.setText(getConfig().getErrorReviewRetakeButton());
            } else {
                Spanned text4 = TextConfigUtils.getText(hVJSONObject, CustomTextStringConst.DocCaptureTextConfigs.DOC_RETAKE_BUTTON, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_RETAKE_BUTTON);
                if (text4 != null) {
                    this.retakeButton.setText(text4);
                }
            }
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentRetakeScreenLoadSuccess(longValue);
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentRetakeScreenLaunched();
            }
        }
        this.retakeButtonClickTimingUtils.init();
    }

    private void setTypefaces() {
        HVLogUtils.d(this.TAG, "setTypefaces() called");
        try {
            if (getConfig().getErrorReviewScreenTitleTypeface() > 0) {
                this.tvTitle.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getConfig().getErrorReviewScreenTitleTypeface()));
            }
            getConfig().getErrorReviewScreenDescTypeface();
            if (getConfig().getErroReviewScreenRetakeButtonTypeface() > 0) {
                this.retakeButton.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getConfig().getErroReviewScreenRetakeButtonTypeface()));
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "setTypefaces(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            HVError hVError = new HVError(2, Utils.getErrorMessage(e));
            if (this.callingActivity.contains(FACE_CALLING_ACTIVITY_VALUE) && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieRetakeScreenLoadFailure(hVError);
            }
            if (this.callingActivity.contains(DOC_CALLING_ACTIVITY_VALUE) && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentRetakeScreenLoadFailure(hVError);
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private HVBaseConfig getConfig() {
        HVBaseConfig hVBaseConfig = this.config;
        if (hVBaseConfig != null) {
            return hVBaseConfig;
        }
        setResult(21);
        finish();
        return new HVBaseConfig();
    }

    private void findViews() {
        HVLogUtils.d(this.TAG, "findViews() called");
        this.reviewImage = (ImageView) findViewById(R.id.review_image);
        this.errorPlaceHolderImageView = (ImageView) findViewById(R.id.review_file_upload_error_image_view);
        this.tvTitle = (TextView) findViewById(R.id.title_text);
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.btnRetake);
        this.retakeButton = materialButton;
        materialButton.setEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.ivBack);
        this.ivBack = imageView;
        imageView.setEnabled(true);
        this.tvError = (TextView) findViewById(R.id.tvError);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVRetakeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVRetakeActivity.this.m501x33eb1085(view);
            }
        };
        this.retakeButton.setOnClickListener(onClickListener);
        this.ivBack.setOnClickListener(onClickListener);
        this.cvPdfPageSwitcher = (CardView) findViewById(R.id.cvPdfPageSwitcher);
        this.fabPrevious = (FloatingActionButton) findViewById(R.id.fabPrevious);
        this.fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        this.tvPage = (TextView) findViewById(R.id.tvPage);
        this.clProgressBar = (ContentLoadingProgressBar) findViewById(R.id.clProgressBar);
    }

    /* renamed from: lambda$findViews$0$co-hyperverge-hypersnapsdk-activities-HVRetakeActivity, reason: not valid java name */
    public /* synthetic */ void m501x33eb1085(View view) {
        long longValue = this.retakeButtonClickTimingUtils.getTimeDifferenceLong().longValue();
        if (this.callingActivity.contains(FACE_CALLING_ACTIVITY_VALUE)) {
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieRetakeScreenRetakeButtonClicked((HVFaceConfig) getConfig(), longValue);
            }
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
                SDKInternalConfig.getInstance().getHvSensorBiometrics().updateSensorDataEvents(System.currentTimeMillis(), "selfieRetakeClicked");
            }
        }
        if (this.callingActivity.contains(DOC_CALLING_ACTIVITY_VALUE) && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentRetakeScreenRetakeButtonClicked((HVDocConfig) getConfig(), longValue);
        }
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        HVLogUtils.d(this.TAG, "onSaveInstanceState() called with: outState = [" + bundle + "]");
    }

    public void adjustTitleText() {
        HVLogUtils.d(this.TAG, "adjustTitleText() called");
        boolean z = getConfig() instanceof HVFaceConfig;
    }

    public void adjustDescText() {
        HVLogUtils.d(this.TAG, "adjustDescText() called");
        try {
            HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(this.tvTitle);
            HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(this.retakeButton);
            HyperSnapUIConfigUtil.getInstance().customiseRetakeMessageTextView(this.tvError);
            HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) findViewById(R.id.ivBack));
            HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) findViewById(R.id.clientLogo));
            HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(findViewById(R.id.hvBackgroundContainer));
            String str = this.retryMessage;
            if (str != null) {
                this.tvError.setText(StringUtils.getHTML(capitalize(str)));
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "adjustDescText(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            HVError hVError = new HVError(2, Utils.getErrorMessage(e));
            if (this.callingActivity.contains(FACE_CALLING_ACTIVITY_VALUE) && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieRetakeScreenLoadFailure(hVError);
            }
            if (this.callingActivity.contains(DOC_CALLING_ACTIVITY_VALUE) && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentRetakeScreenLoadFailure(hVError);
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void loadReviewImage() {
        Bitmap roundedCornerBitmap;
        HVLogUtils.d(this.TAG, "loadReviewImage() called");
        try {
            final File file = new File(this.filePath);
            if (Objects.equals(Utils.extractFileExtension(file.getPath()), "pdf")) {
                if (Build.VERSION.SDK_INT < 21) {
                    return;
                }
                showProgress(true);
                this.executorService.submit(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVRetakeActivity$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        HVRetakeActivity.this.m505x2b39a99b(file);
                    }
                });
                return;
            }
            Bitmap processBitmap = FileHelper.processBitmap(this.filePath);
            if (processBitmap != null) {
                if (this.callingActivity.contains(FACE_CALLING_ACTIVITY_VALUE)) {
                    roundedCornerBitmap = UIUtils.getCircularCroppedBitmap(processBitmap, Integer.valueOf(this.radius));
                } else {
                    roundedCornerBitmap = UIUtils.getRoundedCornerBitmap(this, processBitmap, this.padding, this.aspectRatio, UIUtils.dpToPx(this, 5.0f), this.isShouldSetPadding);
                    ((RelativeLayout.LayoutParams) this.reviewImage.getLayoutParams()).addRule(13);
                }
                this.reviewImage.getLayoutParams().height = -2;
                this.reviewImage.setAdjustViewBounds(true);
                this.reviewImage.requestLayout();
                this.reviewImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                if (roundedCornerBitmap == null) {
                    onError(new HVError(2, this.bitmapNullErrorString));
                } else {
                    this.reviewImage.setImageBitmap(roundedCornerBitmap);
                }
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "adjustDescText(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            setReviewImageBitmap(null);
        }
    }

    /* renamed from: lambda$loadReviewImage$2$co-hyperverge-hypersnapsdk-activities-HVRetakeActivity, reason: not valid java name */
    public /* synthetic */ void m505x2b39a99b(File file) {
        final List<Bitmap> asBitmaps = PDFUtils.asBitmaps(file);
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVRetakeActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                HVRetakeActivity.this.m504x2a0356bc(asBitmaps);
            }
        });
    }

    /* renamed from: lambda$loadReviewImage$1$co-hyperverge-hypersnapsdk-activities-HVRetakeActivity, reason: not valid java name */
    public /* synthetic */ void m504x2a0356bc(List list) {
        showProgress(false);
        loadPdfBitmaps(list);
    }

    private void onError(HVError hVError) {
        HVLogUtils.d(this.TAG, "onError() called with: error = [" + hVError + "]");
        Intent intent = new Intent();
        intent.putExtra("hvError", hVError);
        setResult(18, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        HVLogUtils.d(this.TAG, "onResume() called");
        loadReviewImage();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public void onRemoteConfigFetchDone() {
        HVLogUtils.d(this.TAG, "onRemoteConfigFetchDone() called");
    }

    public void retakePicture() {
        HVLogUtils.d(this.TAG, "retakePicture() called");
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logIdCaptureReviewScreenRetake();
        }
        setResult(21);
        finish();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    HVBaseConfig getBaseConfig() {
        return this.config;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    void onCloseActivity() {
        HVLogUtils.d(this.TAG, "onCloseActivity() called");
        retakePicture();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            if (this.callingActivity.contains(FACE_CALLING_ACTIVITY_VALUE)) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieRetakeScreenBackPressed();
            } else if (this.callingActivity.contains(DOC_CALLING_ACTIVITY_VALUE)) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentRetakeScreenBackPressed();
            }
        }
    }

    private String capitalize(String str) {
        HVLogUtils.d(this.TAG, "capitalize() called with: capString = [" + str + "]");
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private void loadPdfBitmaps(final List<Bitmap> list) {
        HVLogUtils.d(this.TAG, "loadPdfBitmaps() called with: pdfBitmaps = [" + list + "]");
        if (list.isEmpty()) {
            setReviewImageBitmap(null);
            return;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (list.size() > 1) {
            this.cvPdfPageSwitcher.setVisibility(0);
        }
        setReviewImageBitmap(list.get(atomicInteger.get()));
        this.tvPage.setText(String.format(getString(R.string.hv_pdf_page_count), Integer.valueOf(atomicInteger.get() + 1), Integer.valueOf(list.size())));
        this.fabPrevious.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVRetakeActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVRetakeActivity.this.m502x88296385(atomicInteger, list, view);
            }
        });
        this.fabNext.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVRetakeActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVRetakeActivity.this.m503x895fb664(atomicInteger, list, view);
            }
        });
    }

    /* renamed from: lambda$loadPdfBitmaps$3$co-hyperverge-hypersnapsdk-activities-HVRetakeActivity, reason: not valid java name */
    public /* synthetic */ void m502x88296385(AtomicInteger atomicInteger, List list, View view) {
        int i = atomicInteger.get();
        boolean z = false;
        if (i > 0) {
            i = atomicInteger.decrementAndGet();
            setReviewImageBitmap((Bitmap) list.get(i));
            this.tvPage.setText(String.format(getString(R.string.hv_pdf_page_count), Integer.valueOf(i + 1), Integer.valueOf(list.size())));
        }
        this.fabPrevious.setEnabled(i != 0 && list.size() > 1);
        FloatingActionButton floatingActionButton = this.fabNext;
        if (i != list.size() - 1 && list.size() > 1) {
            z = true;
        }
        floatingActionButton.setEnabled(z);
    }

    /* renamed from: lambda$loadPdfBitmaps$4$co-hyperverge-hypersnapsdk-activities-HVRetakeActivity, reason: not valid java name */
    public /* synthetic */ void m503x895fb664(AtomicInteger atomicInteger, List list, View view) {
        int i = atomicInteger.get();
        if (i < list.size() - 1) {
            i = atomicInteger.incrementAndGet();
            setReviewImageBitmap((Bitmap) list.get(i));
            this.tvPage.setText(String.format(getString(R.string.hv_pdf_page_count), Integer.valueOf(i + 1), Integer.valueOf(list.size())));
        }
        this.fabPrevious.setEnabled(i != 0 && list.size() > 1);
        this.fabNext.setEnabled(i != list.size() - 1 && list.size() > 1);
    }

    private void setReviewImageBitmap(Bitmap bitmap) {
        HVLogUtils.d(this.TAG, "setReviewImageBitmap() called with: bitmap = [" + bitmap + "]");
        if (bitmap == null) {
            this.reviewImage.setVisibility(4);
            this.errorPlaceHolderImageView.setVisibility(0);
        } else {
            this.reviewImage.setVisibility(0);
            this.errorPlaceHolderImageView.setVisibility(4);
        }
        this.reviewImage.setImageBitmap(bitmap);
        adjustTitleText();
    }

    private void showProgress(boolean z) {
        HVLogUtils.d(this.TAG, "showProgress() called with: show = [" + z + "]");
        this.clProgressBar.setVisibility(8);
        this.ivBack.setVisibility(z ? 4 : 0);
        this.retakeButton.setVisibility(z ? 4 : 0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(this.TAG, "onConfigurationChanged: newConfig = " + configuration);
    }
}
