package co.hyperverge.hypersnapsdk.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.HVLottieHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.listeners.FaceCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureContract;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TextureFragment;
import co.hyperverge.hypersnapsdk.liveness.ui.texturetracker.TexturePresenter;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.providers.CallbackProvider;
import co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.PermissionManager;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVFaceActivity extends HVBaseActivity {
    private static final int INSTRUCTIONS_REQUEST_CODE = 1002;
    private static final int LOCATION_SETTINGS_REQUEST_CODE = 1001;
    public static final int PERMISSION_CANCELLED = 1003;
    private static final String TAG = "co.hyperverge.hypersnapsdk.activities.HVFaceActivity";
    HVFaceConfig faceConfig;
    private boolean isCheckingForPerms;
    private LottieAnimationView lavLoader;
    TextureFragment mFragment;
    public final String camViewTag = "faceCaptureCameraPreview";
    PermissionManager permissionManager = new PermissionManager();
    private boolean isFromRetake = false;
    private final TimingUtils permissionTimingUtils = new TimingUtils();
    private final TimingUtils screenLoadSuccessTimingUtils = new TimingUtils();
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

    public static void start(Context context, HVFaceConfig hVFaceConfig, FaceCaptureCompletionHandler faceCaptureCompletionHandler) {
        Intent intent;
        String str = TAG;
        HVLogUtils.d(str, "start() called with: context = [" + context + "], faceConfig = [" + hVFaceConfig + "], handler = [" + faceCaptureCompletionHandler + "]");
        if (faceCaptureCompletionHandler == null) {
            return;
        }
        CallbackProvider.get().setCallback(faceCaptureCompletionHandler);
        String moduleId = hVFaceConfig.getModuleId();
        if (context == null) {
            callCompletionHandler(moduleId, getAppFilesDir(context), faceCaptureCompletionHandler, new HVError(6, "Context object is null"), null);
            return;
        }
        HyperSnapSDK hyperSnapSDK = HyperSnapSDK.getInstance();
        HyperSnapSDKConfig hyperSnapSDKConfig = hyperSnapSDK.getHyperSnapSDKConfig();
        if (!hyperSnapSDK.isHyperSnapSDKInitialised() || ((hyperSnapSDKConfig.getAppId() != null && hyperSnapSDKConfig.getAppId().isEmpty()) || (hyperSnapSDKConfig.getAppKey() != null && hyperSnapSDKConfig.getAppKey().isEmpty()))) {
            HVError hVError = new HVError(11, context.getResources().getString(R.string.initialised_error));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logHyperSnapSDKInitError(hVError.getErrorMessage());
            }
            HVLogUtils.d(str, "start: error = [" + hVError + "]");
            callCompletionHandler(moduleId, getAppFilesDir(context), faceCaptureCompletionHandler, hVError, null);
            return;
        }
        if (hyperSnapSDKConfig.getHyperSnapRegion() == HyperSnapParams.Region.ASIA_PACIFIC && !HyperSnapSDK.isUserSessionActive()) {
            HVError hVError2 = new HVError(11, context.getResources().getString(R.string.user_session_not_created_error));
            HVLogUtils.d(str, "start: error = [" + hVError2 + "]");
            callCompletionHandler(moduleId, getAppFilesDir(context), faceCaptureCompletionHandler, hVError2, null);
            return;
        }
        if (hVFaceConfig == null) {
            HVError hVError3 = new HVError(6, context.getResources().getString(R.string.face_config_error));
            HVLogUtils.d(str, "start: error = [" + hVError3 + "]");
            callCompletionHandler(moduleId, getAppFilesDir(context), faceCaptureCompletionHandler, hVError3, null);
            return;
        }
        if (hVFaceConfig.isShouldShowInstructionPage()) {
            intent = new Intent(context, (Class<?>) HVFaceInstructionActivity.class);
            if (hVFaceConfig.getCustomUIStrings() != null) {
                intent.putExtra("customUIStrings", hVFaceConfig.getCustomUIStrings().toString());
            }
            intent.putExtra("shouldUseBackCam", hVFaceConfig.getShouldUseBackCamera());
        } else {
            intent = new Intent(context, (Class<?>) HVFaceActivity.class);
        }
        intent.putExtra("hvFaceConfig", hVFaceConfig);
        context.startActivity(intent);
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

    public void callCompletionHandler(String str, HVError hVError, HVResponse hVResponse) {
        callCompletionHandler(str, getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), hVError, hVResponse);
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hv_activity_face_capture);
        getWindow().getDecorView().getRootView().setTag("faceCaptureCameraPreview");
        String str = TAG;
        HVLogUtils.d(str, "onCreate() called with: savedInstanceState = [" + bundle + "]");
        if (bundle != null) {
            HVLogUtils.d(str, "onCreate() savedInstance is not null, finishing activity");
            this.faceConfig = (HVFaceConfig) new Gson().fromJson(bundle.getString("faceConfig"), HVFaceConfig.class);
        }
        this.faceConfig = (HVFaceConfig) getIntent().getSerializableExtra("hvFaceConfig");
        HVFaceConfig.setFaceConfigInstance(getFaceConfig());
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieFlowStarted(getFaceConfig());
        }
        this.screenLoadSuccessTimingUtils.init();
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation()) {
            try {
                getLocation();
            } catch (NoClassDefFoundError unused) {
                Log.e(TAG, "gms excluded");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        HVLogUtils.d(TAG, "onStart() called");
        if (!this.isCheckingForPerms && !this.isFromRetake) {
            checkForPermissions();
        } else {
            this.isFromRetake = false;
        }
    }

    private void getLocation() {
        HVLogUtils.d(TAG, "getLocation() called");
        if (LocationServiceImpl.getInstance(this).isGPSSwitchedOn()) {
            LocationServiceImpl.getInstance(this).getLastKnownLocation();
            LocationServiceImpl.getInstance(this).startLocationUpdates();
        } else {
            showLocationSettingsDialog();
        }
    }

    private void showLocationSettingsDialog() {
        HVLogUtils.d(TAG, "showLocationSettingsDialog() called");
        HVJSONObject customUIStrings = getFaceConfig().getCustomUIStrings();
        Spanned text = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOCATION_PERMISSION_TITLE, getString(R.string.hv_gps_switched_off));
        Spanned text2 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOCATION_PERMISSION_DESC, getString(R.string.hv_please_enable_gps_to_continue));
        Spanned text3 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOCATION_PERMISSION_SETTINGS_BUTTON, getString(R.string.hv_open_settings));
        Spanned text4 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOCATION_PERMISSION_CANCEL_BUTTON, getString(R.string.hv_cancel));
        final Spanned text5 = TextConfigUtils.getText(customUIStrings, "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_LOCATION_PERMISSION_ERROR, getString(R.string.hv_gps_access_denied_by_user));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(text);
        builder.setMessage(text2);
        builder.setCancelable(false);
        builder.setPositiveButton(text3, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HVFaceActivity.this.m490x9ad4c246(dialogInterface, i);
            }
        });
        builder.setNegativeButton(text4, new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HVFaceActivity.this.m491xb4f040e5(text5, dialogInterface, i);
            }
        });
        builder.show();
    }

    /* renamed from: lambda$showLocationSettingsDialog$0$co-hyperverge-hypersnapsdk-activities-HVFaceActivity, reason: not valid java name */
    public /* synthetic */ void m490x9ad4c246(DialogInterface dialogInterface, int i) {
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1001);
    }

    /* renamed from: lambda$showLocationSettingsDialog$1$co-hyperverge-hypersnapsdk-activities-HVFaceActivity, reason: not valid java name */
    public /* synthetic */ void m491xb4f040e5(Spanned spanned, DialogInterface dialogInterface, int i) {
        String string = getString(R.string.hv_gps_access_denied_by_user);
        if (spanned != null) {
            string = spanned.toString();
        }
        HVError hVError = new HVError(33, string);
        callCompletionHandler(this.faceConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), hVError, null);
        Intent intent = new Intent();
        intent.putExtra("hvError", hVError);
        setResult(1003, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("faceConfig", new Gson().toJson(this.faceConfig));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        HVLogUtils.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + i + "], permissions = [" + strArr + "], grantResults = [" + iArr + "]");
        final PermissionManager.StatusArray status = this.permissionManager.getStatus(this, new ArrayList(Arrays.asList("android.permission.CAMERA")));
        this.isCheckingForPerms = false;
        if (status.denied.isEmpty()) {
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCameraPermissionsGranted(this.permissionTimingUtils.getTimeDifferenceLong().longValue());
            }
            startFaceCapture();
        } else {
            showCameraPermissionBS(TextConfigUtils.getText(getFaceConfig().getCustomUIStrings(), "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_CAMERA_PERMISSION_TITLE), TextConfigUtils.getText(getFaceConfig().getCustomUIStrings(), "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_CAMERA_PERMISSION_DESC), TextConfigUtils.getText(getFaceConfig().getCustomUIStrings(), "", CustomTextStringConst.FaceCaptureTextConfigs.TEXT_CONFIG_FACE_CAMERA_PERMISSION_BUTTON), new PermDialogCallback() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceActivity.1
                @Override // co.hyperverge.hypersnapsdk.listeners.PermDialogCallback
                public void onActionClick() {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(HVFaceActivity.this, "android.permission.CAMERA")) {
                        HVFaceActivity.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + HVFaceActivity.this.getApplicationContext().getPackageName())));
                        return;
                    }
                    HVFaceActivity.this.checkForPermissions();
                }

                @Override // co.hyperverge.hypersnapsdk.listeners.PermDialogCallback
                public void onCancel() {
                    String join = TextUtils.join(",", status.denied);
                    HVFaceActivity.this.sendInsufficientPermissions("Following Permissions not granted by user: " + join);
                    HVError hVError = new HVError(4, "Following Permissions not granted by user: " + join);
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logCameraPermissionsRejected(hVError, HVFaceActivity.this.permissionTimingUtils.getTimeDifferenceLong().longValue());
                    }
                    Intent intent = new Intent();
                    intent.putExtra("hvError", hVError);
                    HVFaceActivity.this.setResult(1003, intent);
                    HVFaceActivity.this.finish();
                }
            });
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void checkForPermissions() {
        HVLogUtils.d(TAG, "checkForPermissions() called");
        this.isCheckingForPerms = true;
        this.permissionTimingUtils.init();
        ArrayList arrayList = new ArrayList(Arrays.asList("android.permission.CAMERA"));
        this.permissionManager.checkAndRequestPermissions(this, arrayList);
        if (this.permissionManager.getStatus(this, arrayList).denied.isEmpty()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logCameraPermissionsGranted(this.permissionTimingUtils.getTimeDifferenceLong().longValue());
            this.isCheckingForPerms = false;
            startFaceCapture();
        }
    }

    public void startFaceInstruction() {
        HVLogUtils.d(TAG, "startFaceInstruction() called");
        try {
            Intent intent = new Intent(this, (Class<?>) HVFaceInstructionActivity.class);
            if (getFaceConfig().getCustomUIStrings() != null) {
                intent.putExtra("customUIStrings", getFaceConfig().getCustomUIStrings().toString());
            }
            intent.putExtra("shouldUseBackCam", getFaceConfig().getShouldUseBackCamera());
            startActivityForResult(intent, 1002);
        } catch (Exception | NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "startFaceInstruction(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            callCompletionHandler(this.faceConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), new HVError(31, getResources().getString(R.string.instructions_error)), null);
            finish();
        }
    }

    public void startFaceCapture() {
        HVLogUtils.d(TAG, "startFaceCapture() called");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                HVFaceActivity.this.m492x1be2d55a();
            }
        });
        m446x13b071e4();
    }

    /* renamed from: lambda$startFaceCapture$2$co-hyperverge-hypersnapsdk-activities-HVFaceActivity, reason: not valid java name */
    public /* synthetic */ void m492x1be2d55a() {
        this.lavLoader = (LottieAnimationView) findViewById(R.id.lavLoader);
        HyperSnapUIConfigUtil.getInstance().customiseBackgroundImage(findViewById(R.id.texture_container));
        LottieAnimationView lottieAnimationView = this.lavLoader;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(0);
            HVLottieHelper.load(this.lavLoader, "hv_processing.lottie", HVLottieHelper.State.START, null);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    public void onRemoteConfigFetchDone() {
        HVLogUtils.d(TAG, "onRemoteConfigFetchDone() called");
        runOnUiThread(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVFaceActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HVFaceActivity.this.m489xa132a885();
            }
        });
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics()) {
            String sensorDataZipFileName = Utils.getSensorDataZipFileName("face");
            if (SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
                SDKInternalConfig.getInstance().getHvSensorBiometrics().registerSensorBiometrics(sensorDataZipFileName);
                SDKInternalConfig.getInstance().getHvSensorBiometrics().updateSensorDataEvents(System.currentTimeMillis(), "selfieFlowStarted");
            }
            JSONObject headers = getFaceConfig().getHeaders();
            try {
                headers.put(AppConstants.SENSOR_DATA_ZIP_FILE_NAME, sensorDataZipFileName);
                getFaceConfig().setLivenessAPIHeaders(headers);
            } catch (Exception e) {
                String str = TAG;
                HVLogUtils.e(str, "onRemoteConfigFetchDone(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                StringBuilder sb = new StringBuilder();
                sb.append("start() livenessHeaders :- JSON Exception :");
                sb.append(Utils.getErrorMessage(e));
                Log.e(str, sb.toString());
            }
        }
        addTextureFragment();
    }

    /* renamed from: lambda$onRemoteConfigFetchDone$3$co-hyperverge-hypersnapsdk-activities-HVFaceActivity, reason: not valid java name */
    public /* synthetic */ void m489xa132a885() {
        findViewById(R.id.texture_container).setBackground(null);
        LottieAnimationView lottieAnimationView = this.lavLoader;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        TextureFragment textureFragment;
        super.onActivityResult(i, i2, intent);
        HVLogUtils.d(TAG, "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        reinitTimingUtils();
        if (getFaceConfig().isShouldRecordVideo() && (textureFragment = this.mFragment) != null) {
            textureFragment.reinitVideoRecording();
        }
        if (i2 == 18) {
            sendError((HVError) intent.getSerializableExtra("hvError"));
            finish();
        } else if (i2 == 21) {
            this.isFromRetake = true;
        }
        if (i != 1001) {
            return;
        }
        try {
            getLocation();
        } catch (NoClassDefFoundError unused) {
            Log.e(TAG, "gms excluded");
        }
    }

    private void reinitTimingUtils() {
        HVLogUtils.d(TAG, "reinitTimingUtils() called");
        try {
            this.permissionTimingUtils.init();
            this.screenLoadSuccessTimingUtils.init();
            TextureFragment textureFragment = this.mFragment;
            if (textureFragment != null) {
                textureFragment.reinitTimingUtils();
            }
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "reinitTimingUtils(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendInsufficientPermissions(String str) {
        HVLogUtils.d(TAG, "sendInsufficientPermissions() called with: message = [" + str + "]");
        try {
            callCompletionHandler(this.faceConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), new HVError(4, str), null);
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "reinitTimingUtils(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void sendOperationCancelled() {
        HVLogUtils.d(TAG, "sendOperationCancelled() called");
        try {
            callCompletionHandler(this.faceConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), new HVError(3, getResources().getString(R.string.operation_cancelled)), null);
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "reinitTimingUtils(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void sendError(HVError hVError) {
        HVLogUtils.d(TAG, "sendError() called with: hvError = [" + hVError + "]");
        try {
            callCompletionHandler(this.faceConfig.getModuleId(), getAppFilesDir(), CallbackProvider.get().injectFaceCaptureCallback(), hVError, null);
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "reinitTimingUtils(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void addTextureFragment() {
        HVLogUtils.d(TAG, "addTextureFragment() called");
        try {
            if (this.mFragment == null) {
                this.mFragment = new TextureFragment();
            }
            if (this.mFragment.getPresenter() == null) {
                TexturePresenter texturePresenter = new TexturePresenter();
                texturePresenter.setFaceConfig(getFaceConfig());
                texturePresenter.setView(this.mFragment);
                this.mFragment.setPresenter((TextureContract.Presenter) texturePresenter);
                texturePresenter.setMode(getFaceConfig().getMode());
                texturePresenter.setClientId(getFaceConfig().getClientID());
            }
            this.mFragment.setConfig(getFaceConfig());
            getSupportFragmentManager().beginTransaction().replace(R.id.texture_container, this.mFragment).commit();
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                return;
            }
            long longValue = this.screenLoadSuccessTimingUtils.getTimeDifferenceLong().longValue();
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureScreenOpened(getFaceConfig());
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureScreenLoadSuccess(longValue);
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureScreenLaunched();
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "addTextureFragment(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() && SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieCaptureScreenLoadFailure(new HVError(2, Utils.getErrorMessage(e)));
            }
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    HVBaseConfig getBaseConfig() {
        return getFaceConfig();
    }

    public void setShouldAllowActivityClose(boolean z) {
        this.shouldAllowActivityClose = z;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldAllowActivityClose() {
        return this.shouldAllowActivityClose;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    boolean shouldShowCloseAlert() {
        boolean z = !getFaceConfig().isShouldShowInstructionPage() && getFaceConfig().shouldShowCloseAlert();
        HVLogUtils.d(TAG, "shouldShowCloseAlert() returned: " + z);
        return z;
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity
    void onCloseActivity() {
        HVLogUtils.d(TAG, "onCloseActivity() called");
        if (getFaceConfig().shouldShowModuleBackButton() || getFaceConfig().isShouldShowInstructionPage()) {
            try {
                TextureFragment textureFragment = this.mFragment;
                if (textureFragment != null) {
                    textureFragment.onBackPress();
                }
                if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
                    return;
                }
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(getApplicationContext()).logSelfieCaptureScreenBackPressed();
            } catch (Exception e) {
                String str = TAG;
                HVLogUtils.e(str, "onCloseActivity(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(str, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.activities.HVBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        HVLogUtils.d(TAG, "onResume() called");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(TAG, "onConfigurationChanged: newConfig = " + configuration);
    }
}
