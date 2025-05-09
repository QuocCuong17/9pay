package co.hyperverge.hypersnapsdk.activities;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.listeners.PermDialogCallback;
import co.hyperverge.hypersnapsdk.listeners.QRCompletionHandler;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.utils.FileExtensionsKt;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.TextConfigUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVQRBaseActivity extends AppCompatActivity {
    private static final String TAG = "HVQRBaseActivity";

    public Context updateBaseContextLocale(Context context) {
        HVLogUtils.d(TAG, "updateBaseContextLocale() called with: context = [" + context + "]");
        Locale currentLocale = getCurrentLocale(context);
        if (Build.VERSION.SDK_INT >= 24) {
            return updateResourcesLocale(context, currentLocale);
        }
        return updateResourcesLocaleLegacy(context, currentLocale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setupBranding(View view) {
        if (view == null) {
            view = findViewById(R.id.content).getRootView();
        }
        ((TextView) view.findViewById(co.hyperverge.hypersnapsdk.R.id.tvBranding)).setVisibility(SDKInternalConfig.getInstance() != null && SDKInternalConfig.getInstance().isShouldUseBranding() ? 0 : 8);
    }

    public void showCameraPermissionAlert(HVJSONObject hVJSONObject, PermDialogCallback permDialogCallback) {
        HVLogUtils.d(TAG, "showCameraPermissionAlert() called with: customUIStrings = [" + hVJSONObject + "], callback = [" + permDialogCallback + "]");
        showPermissionAlert(hVJSONObject, permDialogCallback);
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

    private void showPermissionAlert(HVJSONObject hVJSONObject, final PermDialogCallback permDialogCallback) {
        HVLogUtils.d(TAG, "showPermissionAlert() called with: customUIStrings = [" + hVJSONObject + "], callback = [" + permDialogCallback + "]");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setDismissWithAnimation(true);
        View inflate = getLayoutInflater().inflate(co.hyperverge.hypersnapsdk.R.layout.hv_dialog_perm_prompt_layout, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(co.hyperverge.hypersnapsdk.R.id.tvTitle);
        TextView textView2 = (TextView) inflate.findViewById(co.hyperverge.hypersnapsdk.R.id.tvSubtitle);
        Button button = (Button) inflate.findViewById(co.hyperverge.hypersnapsdk.R.id.btnAllow);
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(textView);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(textView2);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(button);
        HyperSnapUIConfigUtil.getInstance().customiseBackButtonIcon((ImageView) findViewById(co.hyperverge.hypersnapsdk.R.id.ivBack));
        HyperSnapUIConfigUtil.getInstance().customiseClientLogo((ImageView) findViewById(co.hyperverge.hypersnapsdk.R.id.clientLogo));
        setupBranding(inflate);
        textView.setText(getString(co.hyperverge.hypersnapsdk.R.string.hv_qr_camera_perm_title));
        textView2.setText(getString(co.hyperverge.hypersnapsdk.R.string.hv_qr_camera_perm_subtitle));
        button.setText(getString(co.hyperverge.hypersnapsdk.R.string.hv_qr_camera_perm_button));
        if (hVJSONObject != null) {
            Spanned text = TextConfigUtils.getText(hVJSONObject, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAMERA_PERMISSION_TITLE, getString(co.hyperverge.hypersnapsdk.R.string.hv_qr_camera_perm_title));
            if (text != null) {
                textView.setText(text);
            }
            Spanned text2 = TextConfigUtils.getText(hVJSONObject, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAMERA_PERMISSION_DESC, getString(co.hyperverge.hypersnapsdk.R.string.hv_qr_camera_perm_subtitle));
            if (text2 != null) {
                textView2.setText(text2);
            }
            Spanned text3 = TextConfigUtils.getText(hVJSONObject, "", CustomTextStringConst.QRScanTextConfigs.TEXT_CONFIG_QR_CAMERA_PERMISSION_BUTTON, getString(co.hyperverge.hypersnapsdk.R.string.hv_qr_camera_perm_button));
            if (text3 != null) {
                button.setText(text3);
            }
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRBaseActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HVQRBaseActivity.lambda$showPermissionAlert$0(BottomSheetDialog.this, permDialogCallback, view);
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: co.hyperverge.hypersnapsdk.activities.HVQRBaseActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                PermDialogCallback.this.onCancel();
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showPermissionAlert$0(BottomSheetDialog bottomSheetDialog, PermDialogCallback permDialogCallback, View view) {
        bottomSheetDialog.dismiss();
        permDialogCallback.onActionClick();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        HVLogUtils.d(TAG, "onCreate() called with: savedInstanceState = [" + bundle + "]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        HVLogUtils.d(TAG, "onResume() called");
        updateBaseContextLocale(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HVLogUtils.d(TAG, "onDestroy() called");
    }

    Locale getCurrentLocale(Context context) {
        HVLogUtils.d(TAG, "getCurrentLocale() called with: context = [" + context + "]");
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getResources().getConfiguration().getLocales().get(0);
        }
        return context.getResources().getConfiguration().locale;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HVLogUtils.d(TAG, "onConfigurationChanged: newConfig = " + configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void callCompletionHandler(String str, Context context, QRCompletionHandler qRCompletionHandler, HVError hVError, JSONObject jSONObject) {
        HVLogUtils.d(TAG, "callCompletionHandler() called with: handler = [" + qRCompletionHandler + "], hvError = [" + hVError + "], result = [" + jSONObject + "]");
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics() && SDKInternalConfig.getInstance().getHvSensorBiometrics() != null) {
            SDKInternalConfig.getInstance().getHvSensorBiometrics().stopSensorBiometrics();
        }
        if (context != null) {
            FileExtensionsKt.saveSDKResultToFile(context.getApplicationContext().getFilesDir(), str, hVError, null, jSONObject);
        } else {
            HVLogUtils.e(TAG, "callCompletionHandler(), unable to save SDK result to file, context is null");
        }
        qRCompletionHandler.onResult(hVError, jSONObject);
    }
}
