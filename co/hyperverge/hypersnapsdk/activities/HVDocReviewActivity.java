package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.DocOCRHelper;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVBaseResponse;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.PDFUtils;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVDocReviewActivity extends HVBaseActivity {
    public static final int CLOSE_ACTION = 5;
    public static final int ERROR_ACTION = 8;
    public static final int PROCEED_ACTION = 7;
    public static final int RECHECK_ACTION = 6;
    private static final ArrayList<HVBaseResponse> hvResponses = new ArrayList<>();
    private float aspectRatio;
    private Bitmap capturedBitmap;
    private ContentLoadingProgressBar clProgressBar;
    private HVDocConfig config;
    private Button confirmButton;
    private CardView cvPdfPageSwitcher;
    private TextView descText;
    private Spanned docLoaderDesc;
    private FloatingActionButton fabNext;
    private FloatingActionButton fabPrevious;
    private int height;
    private boolean isDocCaptureFlow;
    private ImageView ivBack;
    private double padding;
    private Bitmap progressBitmap;
    private TextView progressDialogTextView;
    private View progressDialogView;
    private ImageView progressSpinnerImageView;
    private Button retakeButton;
    private String retryMessage;
    private ImageView reviewImage;
    private TextView tvPage;
    private TextView tvSubtitle;
    private TextView tvTitle;
    private int width;
    private final TimingUtils launchReviewScreenTimingUtils = new TimingUtils();
    private final TimingUtils buttonClickTimingUtils = new TimingUtils();
    private final String TAG = getClass().getSimpleName();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private String filePath = "";
    private String qrCodeCroppedImageUri = "";
    private final HVResponse hvResponse = new HVResponse();
    private boolean shouldAllowActivityClose = true;

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
        this.launchReviewScreenTimingUtils.init();
        setContentView(R.layout.hv_activity_doc_review);
        if (bundle != null) {
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                HVError hVError = new HVError(2, "savedInstanceState is not null");
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenLoadFailure(hVError);
                HVLogUtils.d(this.TAG, "onCreate(): savedInstanceState is not null, error = [" + hVError + "]");
            }
            finish();
            return;
        }
        Intent intent = getIntent();
        this.filePath = intent.getStringExtra(HVRetakeActivity.IMAGE_URI_TAG);
        this.padding = intent.getDoubleExtra(HVRetakeActivity.EXTRA_PADDING_TAG, 0.0d);
        this.aspectRatio = intent.getFloatExtra(HVRetakeActivity.ASPECT_RATIO_TAG, 0.0f);
        this.config = (HVDocConfig) intent.getSerializableExtra(HVDocConfig.KEY);
        this.width = intent.getIntExtra("viewWidth", 0);
        this.height = intent.getIntExtra("viewHeight", 0);
        if (getDocConfig().isShouldReadNIDQR()) {
            this.qrCodeCroppedImageUri = intent.getStringExtra("qrCodeCroppedImageUri");
        }
        if (intent.hasExtra(HVRetakeActivity.RETRY_MESSAGE_TAG)) {
            this.retryMessage = intent.getStringExtra(HVRetakeActivity.RETRY_MESSAGE_TAG);
        }
        if (intent.hasExtra(CustomTextStringConst.DocCaptureTextConfigs.DOC_LOADER_DESC)) {
            this.docLoaderDesc = (Spanned) intent.getCharSequenceExtra(CustomTextStringConst.DocCaptureTextConfigs.DOC_LOADER_DESC);
        }
        if (intent.hasExtra("isDocCaptureFlow")) {
            this.isDocCaptureFlow = intent.getBooleanExtra("isDocCaptureFlow", false);
        }
        findViews();
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logIdCaptureReviewScreenLaunched();
        }
        setTypefaces();
        setupBranding(null);
        try {
            HVJSONObject customUIStrings = getDocConfig().getCustomUIStrings();
            Spanned text = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_REVIEW_RETAKE_BUTTON, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_REVIEW_RETAKE_BUTTON);
            if (text != null) {
                this.retakeButton.setText(text);
            }
            Spanned text2 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_REVIEW_CONTINUE_BUTTON, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_REVIEW_CONTINUE_BUTTON);
            if (text2 != null) {
                this.confirmButton.setText(text2);
            }
            String docReviewTitle = getDocConfig().getDocReviewTitle();
            if (!TextUtils.isEmpty(docReviewTitle)) {
                this.tvTitle.setText(docReviewTitle);
            } else {
                Spanned text3 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_REVIEW_TITLE, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_REVIEW_TITLE);
                if (text3 != null) {
                    this.tvTitle.setText(text3);
                }
            }
            String docReviewDescription = getDocConfig().getDocReviewDescription();
            if (!TextUtils.isEmpty(docReviewDescription)) {
                this.descText.setText(docReviewDescription);
            } else {
                Spanned text4 = TextConfigUtils.getText(customUIStrings, CustomTextStringConst.DocCaptureTextConfigs.DOC_REVIEW_DESC, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_REVIEW_DESC);
                if (text4 != null) {
                    this.descText.setText(text4);
                }
            }
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenLoadSuccess(this.launchReviewScreenTimingUtils.getTimeDifferenceLong().longValue());
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenLaunched();
            }
            this.buttonClickTimingUtils.init();
            HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(this.tvTitle);
            HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(this.descText);
            HyperSnapUIConfigUtil.getInstance().customiseDocumentSideTextView(this.tvSubtitle);
            HyperSnapUIConfigUtil.getInstance().customiseDocumentSideTextView(this.tvPage);
            HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(this.confirmButton);
            HyperSnapUIConfigUtil.getInstance().customiseSecondaryButton(this.retakeButton);
            HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) findViewById(R.id.ivBack));
            HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) findViewById(R.id.clientLogo));
            HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(findViewById(R.id.hvBackgroundContainer));
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "onCreate(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        loadReviewImage();
    }

    private void setTypefaces() {
        HVLogUtils.d(this.TAG, "setTypefaces() called");
        try {
            if (getDocConfig().getReviewScreenTitleTypeface() > 0) {
                this.tvTitle.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getReviewScreenTitleTypeface()));
            }
            if (getDocConfig().getReviewScreenDescTypeface() > 0) {
                this.descText.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getReviewScreenDescTypeface()));
            }
            if (getDocConfig().getReviewScreenConfirmButtonTypeface() > 0) {
                this.confirmButton.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getReviewScreenConfirmButtonTypeface()));
            }
            if (getDocConfig().getReviewScreenRetakeButtonTypeface() > 0) {
                this.retakeButton.setTypeface(ResourcesCompat.getFont(getApplicationContext(), getDocConfig().getReviewScreenRetakeButtonTypeface()));
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "setTypefaces(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logDocumentReviewScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private HVDocConfig getDocConfig() {
        HVDocConfig hVDocConfig = this.config;
        if (hVDocConfig != null) {
            return hVDocConfig;
        }
        setResult(21);
        finish();
        return new HVDocConfig();
    }

    private void findViews() {
        HVLogUtils.d(this.TAG, "findViews() called");
        this.reviewImage = (ImageView) findViewById(R.id.review_image);
        ImageView imageView = (ImageView) findViewById(R.id.ivBack);
        this.ivBack = imageView;
        imageView.setEnabled(true);
        this.descText = (TextView) findViewById(R.id.desc_text);
        this.tvTitle = (TextView) findViewById(R.id.title_text);
        this.tvSubtitle = (TextView) findViewById(R.id.tvSubtitle);
        Button button = (Button) findViewById(R.id.btnConfirm);
        this.confirmButton = button;
        button.setEnabled(true);
        Button button2 = (Button) findViewById(R.id.btnRetake);
        this.retakeButton = button2;
        button2.setEnabled(true);
        this.cvPdfPageSwitcher = (CardView) findViewById(R.id.cvPdfPageSwitcher);
        this.fabPrevious = (FloatingActionButton) findViewById(R.id.fabPrevious);
        this.fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        this.tvPage = (TextView) findViewById(R.id.tvPage);
        this.clProgressBar = (ContentLoadingProgressBar) findViewById(R.id.clProgressBar);
        this.confirmButton.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocReviewActivity.this.m452xb94a1e43(view);
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocReviewActivity.this.m453xc9ffeb04(view);
            }
        };
        this.ivBack.setOnClickListener(onClickListener);
        this.retakeButton.setOnClickListener(onClickListener);
        this.progressDialogView = findViewById(R.id.docReviewProgressDialogView);
        this.progressSpinnerImageView = (ImageView) findViewById(R.id.hv_loading_icon);
        this.progressDialogTextView = (TextView) findViewById(R.id.hv_loading_text);
    }

    /* renamed from: lambda$findViews$0$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m452xb94a1e43(View view) {
        view.setEnabled(false);
        confirm();
    }

    /* renamed from: lambda$findViews$1$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m453xc9ffeb04(View view) {
        view.setEnabled(false);
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        HVLogUtils.d(this.TAG, "onSaveInstanceState() called with: outState = [" + bundle + "]");
    }

    public void adjustDescText() {
        HVLogUtils.d(this.TAG, "adjustDescText() called");
        try {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.descText.getLayoutParams();
            int dpToPx = UIUtils.dpToPx(this, 40.0f);
            if (this.aspectRatio < 1.0f) {
                layoutParams.setMargins(dpToPx, UIUtils.dpToPx(this, 60.0f), dpToPx, 0);
            } else {
                layoutParams.setMargins(dpToPx, UIUtils.dpToPx(this, 6.0f), dpToPx, 0);
            }
            this.descText.requestLayout();
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "adjustDescText(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void adjustTitleText() {
        HVLogUtils.d(this.TAG, "adjustTitleText() called");
    }

    public void loadReviewImage() {
        HVLogUtils.d(this.TAG, "loadReviewImage() called");
        final File file = new File(this.filePath);
        if (Objects.equals(Utils.extractFileExtension(file.getPath()), "pdf")) {
            if (Build.VERSION.SDK_INT < 21) {
                return;
            }
            showProgress(true);
            this.executorService.submit(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    HVDocReviewActivity.this.m457x2d88b22e(file);
                }
            });
            return;
        }
        this.cvPdfPageSwitcher.setVisibility(8);
        showProgress(true);
        this.executorService.submit(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                HVDocReviewActivity.this.m459x4ef44bb0();
            }
        });
    }

    /* renamed from: lambda$loadReviewImage$3$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m457x2d88b22e(File file) {
        final List<Bitmap> asBitmaps = PDFUtils.asBitmaps(file);
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HVDocReviewActivity.this.m456x1cd2e56d(asBitmaps);
            }
        });
    }

    /* renamed from: lambda$loadReviewImage$2$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m456x1cd2e56d(List list) {
        showProgress(false);
        loadPdfBitmaps(list);
    }

    /* renamed from: lambda$loadReviewImage$5$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m459x4ef44bb0() {
        try {
            Bitmap processBitmap = FileHelper.processBitmap(this.filePath);
            if (processBitmap != null) {
                Bitmap roundedCornerBitmap = UIUtils.getRoundedCornerBitmap(this, processBitmap, this.padding, this.aspectRatio, UIUtils.dpToPx(this, 5.0f), getDocConfig().isShouldSetPadding());
                this.capturedBitmap = roundedCornerBitmap;
                if (roundedCornerBitmap != null) {
                    this.progressBitmap = roundedCornerBitmap.copy(roundedCornerBitmap.getConfig(), true);
                    addBlackTransparentColorFilter();
                }
                processBitmap.recycle();
                runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        HVDocReviewActivity.this.m458x3e3e7eef();
                    }
                });
            }
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "loadReviewImage(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* renamed from: lambda$loadReviewImage$4$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m458x3e3e7eef() {
        showProgress(false);
        Bitmap bitmap = this.capturedBitmap;
        if (bitmap == null) {
            HVLogUtils.d(this.TAG, "loadReviewImage(): Error while processing the document");
            onError(new HVError(2, "Error while processing the document"));
        } else {
            setReviewImageBitmap(bitmap);
        }
    }

    private void addBlackTransparentColorFilter() {
        HVLogUtils.d(this.TAG, "addBlackTransparentColorFilter() called");
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.black_transparent), PorterDuff.Mode.SRC_IN));
        new Canvas(this.progressBitmap).drawBitmap(this.progressBitmap, 0.0f, 0.0f, paint);
    }

    private void showProgress(boolean z) {
        HVLogUtils.d(this.TAG, "showProgress() called with: show = [" + z + "]");
        this.clProgressBar.setVisibility(8);
        this.ivBack.setVisibility(z ? 4 : 0);
        this.retakeButton.setVisibility(z ? 4 : 0);
        this.confirmButton.setVisibility(z ? 4 : 0);
    }

    private void loadPdfBitmaps(final List<Bitmap> list) {
        HVLogUtils.d(this.TAG, "loadPdfBitmaps() called with: pdfBitmaps = [" + list + "]");
        if (list.size() == 0) {
            return;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (list.size() > 1) {
            this.cvPdfPageSwitcher.setVisibility(0);
        }
        setReviewImageBitmap(list.get(atomicInteger.get()));
        this.tvPage.setText(String.format(getString(R.string.hv_pdf_page_count), Integer.valueOf(atomicInteger.get() + 1), Integer.valueOf(list.size())));
        this.fabPrevious.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocReviewActivity.this.m454x6c285186(atomicInteger, list, view);
            }
        });
        this.fabNext.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVDocReviewActivity.this.m455x7cde1e47(atomicInteger, list, view);
            }
        });
    }

    /* renamed from: lambda$loadPdfBitmaps$6$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m454x6c285186(AtomicInteger atomicInteger, List list, View view) {
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

    /* renamed from: lambda$loadPdfBitmaps$7$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m455x7cde1e47(AtomicInteger atomicInteger, List list, View view) {
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
            return;
        }
        this.reviewImage.setImageBitmap(bitmap);
        this.capturedBitmap = bitmap.copy(bitmap.getConfig(), true);
        this.progressBitmap = bitmap.copy(bitmap.getConfig(), true);
        addBlackTransparentColorFilter();
        adjustTitleText();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        HVLogUtils.d(this.TAG, "onResume() called");
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public void onRemoteConfigFetchDone() {
        HVLogUtils.d(this.TAG, "onRemoteConfigFetchDone() called");
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    HVBaseConfig getBaseConfig() {
        HVLogUtils.d(this.TAG, "getBaseConfig() called");
        return this.config;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldShowCloseAlert() {
        HVLogUtils.d(this.TAG, "shouldShowCloseAlert() called");
        return false;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldAllowActivityClose() {
        HVLogUtils.d(this.TAG, "shouldAllowActivityClose() called");
        return this.shouldAllowActivityClose;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    void onCloseActivity() {
        HVLogUtils.d(this.TAG, "onCloseActivity() called");
        retakePicture();
        if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
            return;
        }
        SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logDocumentReviewScreenBackPressed();
    }

    private void onError(HVError hVError) {
        HVLogUtils.d(this.TAG, "onError() called with: error = [" + hVError + "]");
        Intent intent = new Intent();
        intent.putExtra("hvError", hVError);
        setResult(8, intent);
        finish();
    }

    public void confirm() {
        HVLogUtils.d(this.TAG, "confirm() called");
        final AtomicReference atomicReference = new AtomicReference();
        atomicReference.set("");
        final AtomicReference atomicReference2 = new AtomicReference();
        atomicReference2.set("");
        if (getDocConfig().isShouldDoOCR()) {
            HVLogUtils.d(this.TAG, "confirm(): shouldDoOCR");
            JSONObject ocrHeaders = getDocConfig().getOcrHeaders();
            try {
                ocrHeaders.put(AppConstants.IS_DOCUMENT_UPLOADED, !this.isDocCaptureFlow);
                getDocConfig().ocrHeaders = ocrHeaders.toString();
            } catch (Exception e) {
                Log.e(this.TAG, "makeOCRAPICall() ocrHeaders:" + Utils.getErrorMessage(e));
            }
            displayProgressView(this.docLoaderDesc);
            this.shouldAllowActivityClose = false;
            DocOCRHelper.get().makeOcrAPICall(this, this.filePath, this.qrCodeCroppedImageUri, getDocConfig(), new DocOCRHelper.DocOCRListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda5
                @Override // co.hyperverge.hypersnapsdk.helpers.DocOCRHelper.DocOCRListener
                public final void onResult(boolean z, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, HVError hVError) {
                    HVDocReviewActivity.this.m450x2e2c8d16(atomicReference2, atomicReference, z, str, str2, jSONObject, jSONObject2, hVError);
                }
            });
            return;
        }
        HVLogUtils.d(this.TAG, "confirm(): shouldDoOCR is not enabled");
        Intent intent = new Intent();
        intent.putExtra(HVRetakeActivity.IMAGE_URI_TAG, this.filePath);
        if (getDocConfig().isShouldReadNIDQR()) {
            intent.putExtra("qrCodeCroppedImageUri", this.qrCodeCroppedImageUri);
        }
        long longValue = this.buttonClickTimingUtils.getTimeDifferenceLong().longValue();
        intent.putExtra("hvResponse", new HVResponse(addResultImageUri(new JSONObject(), this.filePath), new JSONObject(), this.filePath, null));
        intent.putExtra("timeTakenToClickConfirmButton", longValue);
        setResult(7, intent);
        finish();
    }

    /* renamed from: lambda$confirm$8$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m450x2e2c8d16(AtomicReference atomicReference, AtomicReference atomicReference2, boolean z, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, HVError hVError) {
        HVLogUtils.d(this.TAG, "confirm(): onResult() called with: shouldRetry = [" + z + "], message = [" + str + "], action = [" + str2 + "], result = [" + jSONObject + "], headers = [" + jSONObject2 + "], error = [" + hVError + "]");
        removeProgressView();
        this.shouldAllowActivityClose = true;
        atomicReference.set(str2);
        atomicReference2.set(str);
        if (z) {
            if (this.hvResponse.getRetakeAttemptResponses() == null) {
                this.hvResponse.setRetakeAttemptResponses(hvResponses);
            }
            HVBaseResponse hVBaseResponse = new HVBaseResponse();
            hVBaseResponse.setAction((String) atomicReference.get());
            hVBaseResponse.setApiHeaders(jSONObject2);
            hVBaseResponse.setImageURI(this.filePath);
            hVBaseResponse.setApiResult(addResultImageUri(jSONObject, this.filePath));
            hVBaseResponse.setRetakeMessage((String) atomicReference2.get());
            hVBaseResponse.setAttemptsCount(SPHelper.getAttemptsCountForImage(getDocConfig().ocrEndpoint, getDocConfig().getSuffixForDocument()));
            hvResponses.add(hVBaseResponse);
            startErrorReviewScreen(this.filePath, str);
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        JSONObject addResultImageUri = addResultImageUri(jSONObject, this.filePath);
        this.hvResponse.setAction((String) atomicReference.get());
        this.hvResponse.setApiHeaders(jSONObject2);
        this.hvResponse.setImageURI(this.filePath);
        this.hvResponse.setApiResult(addResultImageUri);
        this.hvResponse.setRetakeMessage((String) atomicReference2.get());
        this.hvResponse.setAttemptsCount(SPHelper.getAttemptsCountForImage(getDocConfig().ocrEndpoint, getDocConfig().getSuffixForDocument()));
        this.hvResponse.setRetakeAttemptResponses(hvResponses);
        Intent intent = new Intent();
        intent.putExtra("hvError", hVError);
        intent.putExtra("hvResponse", this.hvResponse);
        setResult(7, intent);
        finish();
    }

    public void retakePicture() {
        HVLogUtils.d(this.TAG, "retakePicture() called");
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logIdCaptureReviewScreenRetake();
        }
        Intent intent = new Intent();
        intent.putExtra("timeTakenToClickRetakeButton", this.buttonClickTimingUtils.getTimeDifferenceLong().longValue());
        setResult(6, intent);
        finish();
    }

    public JSONObject addResultImageUri(JSONObject jSONObject, String str) {
        HVLogUtils.d(this.TAG, "addResultImageUri() called with: result = [" + jSONObject + "], filePath = [" + str + "]");
        try {
            if (getDocConfig().isShouldExportPDF() && str != null) {
                jSONObject.put("pdfUri", str);
            }
        } catch (JSONException e) {
            HVLogUtils.e(this.TAG, "addResultImageUri(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return jSONObject;
    }

    public void startErrorReviewScreen(String str, String str2) {
        HVLogUtils.d(this.TAG, "startErrorReviewScreen() called with: filePath = [" + str + "], message = [" + str2 + "]");
        try {
            Intent intent = new Intent(this, (Class<?>) HVRetakeActivity.class);
            intent.putExtra(HVRetakeActivity.IMAGE_URI_TAG, str);
            intent.putExtra(HVRetakeActivity.ASPECT_RATIO_TAG, getDocConfig().getDocument().getAspectRatio());
            intent.putExtra(HVRetakeActivity.CONFIG_TAG, getDocConfig());
            intent.putExtra(HVRetakeActivity.SET_PADDING_TAG, getDocConfig().isShouldSetPadding());
            intent.putExtra(HVRetakeActivity.RETRY_MESSAGE_TAG, str2);
            intent.putExtra(HVRetakeActivity.EXTRA_PADDING_TAG, this.padding);
            intent.putExtra(HVRetakeActivity.CALLING_ACTIVITY_TAG, HVRetakeActivity.DOC_CALLING_ACTIVITY_VALUE);
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            HVLogUtils.e(this.TAG, "startErrorReviewScreen(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(this.TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        HVLogUtils.d(this.TAG, "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        if (i != 1) {
            return;
        }
        setResult(21);
        finish();
    }

    private void displayProgressView(final Spanned spanned) {
        HVLogUtils.d(this.TAG, "displayProgressView() called with: progressLoaderText = [" + ((Object) spanned) + "]");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                HVDocReviewActivity.this.m451x31fc4ceb(spanned);
            }
        });
    }

    /* renamed from: lambda$displayProgressView$9$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m451x31fc4ceb(Spanned spanned) {
        this.reviewImage.setImageBitmap(this.progressBitmap);
        if (spanned != null) {
            this.progressDialogTextView.setText(spanned);
        }
        ImageView imageView = this.progressSpinnerImageView;
        if (imageView != null) {
            imageView.setAnimation(UIUtils.getInfiniteRotationAnimation());
        }
        View view = this.progressDialogView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void removeProgressView() {
        HVLogUtils.d(this.TAG, "removeProgressView() called");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVDocReviewActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                HVDocReviewActivity.this.m460x3c27686f();
            }
        });
    }

    /* renamed from: lambda$removeProgressView$10$co-hyperverge-hypersnapsdk-activities-HVDocReviewActivity, reason: not valid java name */
    public /* synthetic */ void m460x3c27686f() {
        this.reviewImage.setImageBitmap(this.capturedBitmap);
        ImageView imageView = this.progressSpinnerImageView;
        if (imageView != null) {
            imageView.clearAnimation();
        }
        View view = this.progressDialogView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(this.TAG, "onConfigurationChanged: newConfig = " + configuration);
    }
}
