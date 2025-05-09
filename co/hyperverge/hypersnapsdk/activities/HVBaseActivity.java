package co.hyperverge.hypersnapsdk.activities;

import android.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.helpers.LanguageHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.listeners.CaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.service.location.LocationServiceImpl;
import co.hyperverge.hypersnapsdk.utils.FileExtensionsKt;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.MainUIThread;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class HVBaseActivity extends AppCompatActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "co.hyperverge.hypersnapsdk.activities.HVBaseActivity";
    protected BottomSheetDialog bsd;
    public AlertDialog closeAlertDialog;
    ProgressDialog loaderDialog = null;
    MainUIThread uiThread;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showCloseAlert$3(DialogInterface dialogInterface, int i) {
    }

    abstract HVBaseConfig getBaseConfig();

    abstract void onCloseActivity();

    public abstract void onRemoteConfigFetchDone();

    abstract boolean shouldAllowActivityClose();

    abstract boolean shouldShowCloseAlert();

    /* JADX INFO: Access modifiers changed from: protected */
    public File getAppFilesDir() {
        try {
            return getApplicationContext().getFilesDir();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "getAppFilesDir(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static File getAppFilesDir(Context context) {
        try {
            return context.getApplicationContext().getFilesDir();
        } catch (Exception e) {
            HVLogUtils.e(TAG, "getAppFilesDir(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        HVLogUtils.d(TAG, "onCreate() called with: savedInstanceState = [" + bundle + "]");
        LanguageHelper.refresh(this);
        this.uiThread = MainUIThread.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void callCompletionHandler(String str, File file, CaptureCompletionHandler captureCompletionHandler, HVError hVError, HVResponse hVResponse) {
        String str2 = TAG;
        HVLogUtils.d(str2, "callCompletionHandler() called with: handler = [" + captureCompletionHandler + "], hvError = [" + hVError + "], hvResponse = [" + hVResponse + "]");
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
            SDKInternalConfig.getInstance().getHvSensorBiometrics().stopSensorBiometrics();
        }
        SPHelper.clearAttemptsCountForFaceNotPresent();
        if (file != null) {
            FileExtensionsKt.saveSDKResultToFile(file, str, hVError, hVResponse, null);
        } else {
            HVLogUtils.e(str2, "callCompletionHandler(), unable to save SDK result to file, context is null");
        }
        captureCompletionHandler.onResult(hVError, hVResponse);
    }

    /* renamed from: checkAndWaitForRemoteConfig, reason: merged with bridge method [inline-methods] */
    public void m446x13b071e4() {
        HVLogUtils.d(TAG, "checkAndWaitForRemoteConfig() called");
        this.uiThread.post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVBaseActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                HVBaseActivity.this.m447x2dcbf083();
            }
        });
    }

    /* renamed from: lambda$checkAndWaitForRemoteConfig$1$co-hyperverge-hypersnapsdk-activities-HVBaseActivity, reason: not valid java name */
    public /* synthetic */ void m447x2dcbf083() {
        if (SDKInternalConfig.getInstance() != null && SDKInternalConfig.getInstance().isRemoteConfigFetchDone() && SDKInternalConfig.getInstance().isDefaultRemoteConfigFetchDone()) {
            onRemoteConfigFetchDone();
        } else {
            new Handler().postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.activities.HVBaseActivity$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    HVBaseActivity.this.m446x13b071e4();
                }
            }, 100L);
        }
    }

    Locale getCurrentLocale(Context context) {
        HVLogUtils.d(TAG, "getCurrentLocale() called with: context = [" + context + "]");
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getResources().getConfiguration().getLocales().get(0);
        }
        return context.getResources().getConfiguration().locale;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        HVLogUtils.d(TAG, "onResume() called");
        updateBaseContextLocale(this);
        LanguageHelper.refresh(this);
        if (SDKInternalConfig.getInstance().isRemoteConfigFetchDone() && HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
            SDKInternalConfig.getInstance().getHvSensorBiometrics().resumeSensorBiometrics();
        }
    }

    public Context updateBaseContextLocale(Context context) {
        HVLogUtils.d(TAG, "updateBaseContextLocale() called with: context = [" + context + "]");
        Locale currentLocale = getCurrentLocale(context);
        if (Build.VERSION.SDK_INT >= 24) {
            return updateResourcesLocale(context, currentLocale);
        }
        return updateResourcesLocaleLegacy(context, currentLocale);
    }

    private Context updateResourcesLocale(Context context, Locale locale) {
        HVLogUtils.d(TAG, "updateResourcesLocale() called with: context = [" + context + "], locale = [" + locale + "]");
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        HVLogUtils.d(TAG, "updateResourcesLocaleLegacy() called with: context = [" + context + "], locale = [" + locale + "]");
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setupBranding(View view) {
        HVLogUtils.d(TAG, "setupBranding() called with: rootView = [" + view + "]");
        if (view == null) {
            view = findViewById(R.id.content).getRootView();
        }
        ((TextView) view.findViewById(co.hyperverge.hypersnapsdk.R.id.tvBranding)).setVisibility(SDKInternalConfig.getInstance() != null && SDKInternalConfig.getInstance().isShouldUseBranding() ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        BottomSheetDialog bottomSheetDialog = this.bsd;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }
        super.onDestroy();
        HVLogUtils.d(TAG, "onDestroy() called");
        stopLocationUpdates();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        AlertDialog alertDialog;
        super.onStop();
        HVLogUtils.d(TAG, "onStop() called");
        if (isFinishing() && (alertDialog = this.closeAlertDialog) != null && alertDialog.isShowing()) {
            this.closeAlertDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        HVLogUtils.d(TAG, "onPause() called");
        stopLocationUpdates();
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() || SDKInternalConfig.getInstance().getHvSensorBiometrics() == null) {
            return;
        }
        SDKInternalConfig.getInstance().getHvSensorBiometrics().pauseSensorBiometrics();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        HVLogUtils.d(TAG, "onSupportNavigateUp() called");
        onBackPressed();
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        HVLogUtils.d(TAG, "onBackPressed() called");
        handleCloseAction();
    }

    public void handleCloseAction() {
        HVLogUtils.d(TAG, "handleCloseAction() called");
        if (shouldAllowActivityClose()) {
            if (shouldShowCloseAlert() && Utils.isActivityActive(this)) {
                showCloseAlert();
            } else {
                onCloseActivity();
            }
        }
    }

    private void showCloseAlert() {
        String str;
        HVLogUtils.d(TAG, "showCloseAlert() called");
        HVBaseConfig baseConfig = getBaseConfig();
        String str2 = null;
        if (baseConfig != null) {
            str2 = baseConfig.getCloseAlertDialogTitle();
            str = baseConfig.getCloseAlertDialogDesc();
        } else {
            str = null;
        }
        if (str2 == null) {
            str2 = getString(co.hyperverge.hypersnapsdk.R.string.hv_close_alert_title);
        }
        if (str == null) {
            str = getString(co.hyperverge.hypersnapsdk.R.string.hv_close_alert_desc);
        }
        this.closeAlertDialog = new AlertDialog.Builder(this).setTitle(str2).setMessage(str).setCancelable(false).setPositiveButton(getString(co.hyperverge.hypersnapsdk.R.string.hv_close_alert_positive_action), new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVBaseActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HVBaseActivity.this.m448x8e80e894(dialogInterface, i);
            }
        }).setNegativeButton(getString(co.hyperverge.hypersnapsdk.R.string.hv_close_alert_negative_action), new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVBaseActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HVBaseActivity.lambda$showCloseAlert$3(dialogInterface, i);
            }
        }).show();
    }

    /* renamed from: lambda$showCloseAlert$2$co-hyperverge-hypersnapsdk-activities-HVBaseActivity, reason: not valid java name */
    public /* synthetic */ void m448x8e80e894(DialogInterface dialogInterface, int i) {
        onCloseActivity();
    }

    private void stopLocationUpdates() {
        HVLogUtils.d(TAG, "stopLocationUpdates() called");
        try {
            LocationServiceImpl.getInstance(this).stopLocationUpdates();
            LocationServiceImpl.destroy();
        } catch (NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "stopLocationUpdates(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, "gms excluded");
        }
    }

    public void showCameraPermissionBS(Spanned spanned, Spanned spanned2, Spanned spanned3, PermDialogCallback permDialogCallback) {
        HVLogUtils.d(TAG, "showCameraPermissionBS() called with: titleTextSpanned = [" + ((Object) spanned) + "], descTextSpanned = [" + ((Object) spanned2) + "], buttonTextSpanned = [" + ((Object) spanned3) + "], callback = [" + permDialogCallback + "]");
        showPermissionBS(spanned, spanned2, spanned3, permDialogCallback);
    }

    private void showPermissionBS(Spanned spanned, Spanned spanned2, Spanned spanned3, final PermDialogCallback permDialogCallback) {
        HVLogUtils.d(TAG, "showPermissionBS() called with: titleSpanned = [" + ((Object) spanned) + "], descSpanned = [" + ((Object) spanned2) + "], buttonSpanned = [" + ((Object) spanned3) + "], callback = [" + permDialogCallback + "]");
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        this.bsd = bottomSheetDialog;
        bottomSheetDialog.setDismissWithAnimation(true);
        View inflate = getLayoutInflater().inflate(co.hyperverge.hypersnapsdk.R.layout.hv_dialog_perm_prompt_layout, (ViewGroup) null, false);
        setupBranding(inflate);
        TextView textView = (TextView) inflate.findViewById(co.hyperverge.hypersnapsdk.R.id.tvTitle);
        TextView textView2 = (TextView) inflate.findViewById(co.hyperverge.hypersnapsdk.R.id.tvSubtitle);
        Button button = (Button) inflate.findViewById(co.hyperverge.hypersnapsdk.R.id.btnAllow);
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView2);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(button);
        textView.setText(getString(co.hyperverge.hypersnapsdk.R.string.hv_camera_perm_title));
        textView2.setText(getString(co.hyperverge.hypersnapsdk.R.string.hv_camera_perm_subtitle));
        button.setText(getString(co.hyperverge.hypersnapsdk.R.string.hv_camera_perm_button));
        if (spanned != null) {
            textView.setText(spanned);
        }
        if (spanned2 != null) {
            textView2.setText(spanned2);
        }
        if (spanned3 != null) {
            button.setText(spanned3);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVBaseActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVBaseActivity.this.m449x569054b6(permDialogCallback, view);
            }
        });
        this.bsd.setContentView(inflate);
        this.bsd.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVBaseActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                PermDialogCallback.this.onCancel();
            }
        });
        this.bsd.show();
    }

    /* renamed from: lambda$showPermissionBS$4$co-hyperverge-hypersnapsdk-activities-HVBaseActivity, reason: not valid java name */
    public /* synthetic */ void m449x569054b6(PermDialogCallback permDialogCallback, View view) {
        this.bsd.dismiss();
        permDialogCallback.onActionClick();
    }
}
