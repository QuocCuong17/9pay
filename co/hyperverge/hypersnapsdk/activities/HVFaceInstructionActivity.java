package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class HVFaceInstructionActivity extends HVBaseActivity {
    public static final int CANCEL_SELFIE = 3;
    public static final int PROCEED_SELFIE = 2;
    private static final String TAG = "co.hyperverge.hypersnapsdk.activities.HVFaceInstructionActivity";
    private MaterialButton btnProceed;
    private HVFaceConfig faceConfig;
    private TextView tvSubtitle;
    private TextView tvTitle;
    private final TimingUtils screenLoadSuccessTimingUtils = new TimingUtils();
    private final TimingUtils proceedButtonClickTimingUtils = new TimingUtils();

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldAllowActivityClose() {
        return true;
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

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldShowCloseAlert() {
        return getFaceConfig().shouldShowCloseAlert();
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    void onCloseActivity() {
        HVLogUtils.d(TAG, "onCloseActivity() called");
        if (getFaceConfig().shouldShowModuleBackButton()) {
            sendOperationCancelled();
            finish();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    HVBaseConfig getBaseConfig() {
        return getFaceConfig();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        HVLogUtils.d(TAG, "onCreate() called with: savedInstanceState = [" + bundle + "]");
        this.screenLoadSuccessTimingUtils.init();
        setContentView(R.layout.hv_activity_face_instruction);
        if (bundle != null) {
            this.faceConfig = (HVFaceConfig) new Gson().fromJson(bundle.getString("faceConfig"), HVFaceConfig.class);
        }
        findViews();
        boolean booleanExtra = getIntent().getBooleanExtra("shouldUseBackCam", false);
        this.faceConfig = (HVFaceConfig) getIntent().getSerializableExtra("hvFaceConfig");
        HVJSONObject hVJSONObject = null;
        setupBranding(null);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                hVJSONObject = new HVJSONObject(extras.getString("customUIStrings"));
            }
        } catch (JSONException e) {
            String str = TAG;
            HVLogUtils.e(str, "onCreate(): customUIStrings exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieInstructionsScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
            }
        }
        if (hVJSONObject != null) {
            try {
                if (hVJSONObject.has("faceInstructionsTitleTypeFace") && hVJSONObject.getInt("faceInstructionsTitleTypeFace") > 0) {
                    this.tvTitle.setTypeface(ResourcesCompat.getFont(getApplicationContext(), hVJSONObject.getInt("faceInstructionsTitleTypeFace")));
                }
                if (hVJSONObject.hasAndNotEmpty("faceInstructionsProceedBackCamTypeFace") && hVJSONObject.getInt("faceInstructionsProceedBackCamTypeFace") > 0) {
                    this.btnProceed.setTypeface(ResourcesCompat.getFont(getApplicationContext(), hVJSONObject.getInt("faceInstructionsProceedBackCamTypeFace")));
                }
                Spanned text = TextConfigUtils.getText(hVJSONObject, CustomTextStringConst.FaceCaptureTextConfigs.FACE_INSTRUCTIONS_TITLE, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_INSTRUCTIONS_TITLE);
                if (text != null) {
                    this.tvTitle.setText(text);
                }
                Spanned text2 = TextConfigUtils.getText(hVJSONObject, CustomTextStringConst.FaceCaptureTextConfigs.FACE_INSTRUCTIONS_DESC, CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_INSTRUCTIONS_DESC);
                if (text2 != null) {
                    this.tvSubtitle.setText(text2);
                }
                if (booleanExtra) {
                    Spanned text3 = TextConfigUtils.getText(hVJSONObject, "faceInstructionsProceedBackCam", "faceInstructionsProceedBackCam", getResources().getString(R.string.faceInstructionsProceedBackCam));
                    if (text3 != null) {
                        this.btnProceed.setText(text3);
                    }
                } else {
                    Spanned text4 = TextConfigUtils.getText(hVJSONObject, "faceInstructionsProceed", "faceInstructions_button", getResources().getString(R.string.faceInstructionsProceed));
                    if (text4 != null) {
                        this.btnProceed.setText(text4);
                    }
                }
            } catch (JSONException e2) {
                String str2 = TAG;
                HVLogUtils.e(str2, "onCreate(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
                Log.e(str2, Utils.getErrorMessage(e2));
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieInstructionsScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e2)));
                }
            }
        }
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieInstructionsScreenLoadSuccess(this.screenLoadSuccessTimingUtils.getTimeDifferenceLong().longValue());
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieInstructionsScreenLaunched();
            this.proceedButtonClickTimingUtils.init();
        }
        loadAnimation();
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(this.tvTitle);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(this.tvSubtitle);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(this.btnProceed);
        HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) findViewById(R.id.ivBack));
        HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) findViewById(R.id.clientLogo));
        HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(findViewById(R.id.hvBackgroundContainer));
        if (getFaceConfig().shouldShowModuleBackButton()) {
            return;
        }
        findViewById(R.id.ivBack).setVisibility(4);
    }

    private HVFaceConfig getFaceConfig() {
        HVFaceConfig hVFaceConfig = this.faceConfig;
        if (hVFaceConfig != null) {
            return hVFaceConfig;
        }
        callCompletionHandler(null, getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), new HVError(2, getResources().getString(R.string.face_config_error)), null);
        finish();
        return new HVFaceConfig();
    }

    private void loadAnimation() {
        HVLogUtils.d(TAG, "loadAnimation() called");
        HVLottieHelper.load((LottieAnimationView) findViewById(R.id.lavFaceInstructions), HVLottieHelper.FACE_INSTRUCTION, HVLottieHelper.State.START, null);
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public void onRemoteConfigFetchDone() {
        HVLogUtils.d(TAG, "onRemoteConfigFetchDone() called");
    }

    private void findViews() {
        HVLogUtils.d(TAG, "findViews() called");
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.proceed_button);
        this.btnProceed = materialButton;
        materialButton.setEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.ivBack);
        imageView.setEnabled(true);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tvSubtitle = (TextView) findViewById(R.id.tvSubtitle);
        this.btnProceed.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceInstructionActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVFaceInstructionActivity.this.m493xf773e584(view);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceInstructionActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVFaceInstructionActivity.this.m494x45335d85(view);
            }
        });
    }

    /* renamed from: lambda$findViews$0$co-hyperverge-hypersnapsdk-activities-HVFaceInstructionActivity, reason: not valid java name */
    public /* synthetic */ void m493xf773e584(View view) {
        view.setEnabled(false);
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieInstructionsScreenProceedButtonClicked(this.proceedButtonClickTimingUtils.getTimeDifferenceLong().longValue());
        }
        proceedToSelfie();
    }

    /* renamed from: lambda$findViews$1$co-hyperverge-hypersnapsdk-activities-HVFaceInstructionActivity, reason: not valid java name */
    public /* synthetic */ void m494x45335d85(View view) {
        view.setEnabled(false);
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("faceConfig", new Gson().toJson(this.faceConfig));
        HVLogUtils.d(TAG, "onSaveInstanceState() called with: outState = [" + bundle + "]");
    }

    public void proceedToSelfie() {
        HVLogUtils.d(TAG, "proceedToSelfie() called");
        Intent intent = new Intent(this, (Class<?>) HVFaceActivity.class);
        intent.putExtra("hvFaceConfig", (HVFaceConfig) getIntent().getSerializableExtra("hvFaceConfig"));
        this.btnProceed.setEnabled(true);
        startActivityForResult(intent, 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        HVLogUtils.d(TAG, "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        if (i != 2) {
            return;
        }
        if (i2 == -1) {
            finish();
        }
        if (i2 == 1003) {
            finish();
        }
    }

    private void sendOperationCancelled() {
        HVLogUtils.d(TAG, "sendOperationCancelled() called");
        try {
            HVFaceActivity.callCompletionHandler(this.faceConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), new HVError(3, getResources().getString(R.string.operation_cancelled)), null);
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "sendOperationCancelled(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(TAG, "onConfigurationChanged: newConfig = " + configuration);
    }
}
