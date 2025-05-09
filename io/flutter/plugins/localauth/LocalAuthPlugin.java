package io.flutter.plugins.localauth;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.biometric.BiometricManager;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.localauth.AuthenticationHelper;
import io.flutter.plugins.localauth.Messages;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class LocalAuthPlugin implements FlutterPlugin, ActivityAware, Messages.LocalAuthApi {
    private static final int LOCK_REQUEST_CODE = 221;
    private Activity activity;
    private AuthenticationHelper authHelper;
    private BiometricManager biometricManager;
    private KeyguardManager keyguardManager;
    private Lifecycle lifecycle;
    Messages.Result<Messages.AuthResult> lockRequestResult;
    final AtomicBoolean authInProgress = new AtomicBoolean(false);
    private final PluginRegistry.ActivityResultListener resultListener = new PluginRegistry.ActivityResultListener() { // from class: io.flutter.plugins.localauth.LocalAuthPlugin.1
        @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
        public boolean onActivityResult(int i, int i2, Intent intent) {
            if (i != LocalAuthPlugin.LOCK_REQUEST_CODE) {
                return false;
            }
            if (i2 == -1 && LocalAuthPlugin.this.lockRequestResult != null) {
                LocalAuthPlugin localAuthPlugin = LocalAuthPlugin.this;
                localAuthPlugin.m1105x1012d692(localAuthPlugin.lockRequestResult, Messages.AuthResult.SUCCESS);
            } else {
                LocalAuthPlugin localAuthPlugin2 = LocalAuthPlugin.this;
                localAuthPlugin2.m1105x1012d692(localAuthPlugin2.lockRequestResult, Messages.AuthResult.FAILURE);
            }
            LocalAuthPlugin.this.lockRequestResult = null;
            return false;
        }
    };

    @Override // io.flutter.plugins.localauth.Messages.LocalAuthApi
    public Boolean isDeviceSupported() {
        return Boolean.valueOf(isDeviceSecure() || canAuthenticateWithBiometrics());
    }

    @Override // io.flutter.plugins.localauth.Messages.LocalAuthApi
    public Boolean deviceCanSupportBiometrics() {
        return Boolean.valueOf(hasBiometricHardware());
    }

    @Override // io.flutter.plugins.localauth.Messages.LocalAuthApi
    public List<Messages.AuthClassification> getEnrolledBiometrics() {
        ArrayList arrayList = new ArrayList();
        if (this.biometricManager.canAuthenticate(255) == 0) {
            arrayList.add(Messages.AuthClassification.WEAK);
        }
        if (this.biometricManager.canAuthenticate(15) == 0) {
            arrayList.add(Messages.AuthClassification.STRONG);
        }
        return arrayList;
    }

    @Override // io.flutter.plugins.localauth.Messages.LocalAuthApi
    public Boolean stopAuthentication() {
        try {
            if (this.authHelper != null && this.authInProgress.get()) {
                this.authHelper.stopAuthentication();
                this.authHelper = null;
            }
            this.authInProgress.set(false);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // io.flutter.plugins.localauth.Messages.LocalAuthApi
    public void authenticate(Messages.AuthOptions authOptions, Messages.AuthStrings authStrings, Messages.Result<Messages.AuthResult> result) {
        if (this.authInProgress.get()) {
            result.success(Messages.AuthResult.ERROR_ALREADY_IN_PROGRESS);
            return;
        }
        Activity activity = this.activity;
        if (activity == null || activity.isFinishing()) {
            result.success(Messages.AuthResult.ERROR_NO_ACTIVITY);
            return;
        }
        if (!(this.activity instanceof FragmentActivity)) {
            result.success(Messages.AuthResult.ERROR_NOT_FRAGMENT_ACTIVITY);
        } else {
            if (!isDeviceSupported().booleanValue()) {
                result.success(Messages.AuthResult.ERROR_NOT_AVAILABLE);
                return;
            }
            this.authInProgress.set(true);
            sendAuthenticationRequest(authOptions, authStrings, !authOptions.getBiometricOnly().booleanValue() && canAuthenticateWithDeviceCredential(), createAuthCompletionHandler(result));
        }
    }

    public AuthenticationHelper.AuthCompletionHandler createAuthCompletionHandler(final Messages.Result<Messages.AuthResult> result) {
        return new AuthenticationHelper.AuthCompletionHandler() { // from class: io.flutter.plugins.localauth.LocalAuthPlugin$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.localauth.AuthenticationHelper.AuthCompletionHandler
            public final void complete(Messages.AuthResult authResult) {
                LocalAuthPlugin.this.m1105x1012d692(result, authResult);
            }
        };
    }

    public void sendAuthenticationRequest(Messages.AuthOptions authOptions, Messages.AuthStrings authStrings, boolean z, AuthenticationHelper.AuthCompletionHandler authCompletionHandler) {
        AuthenticationHelper authenticationHelper = new AuthenticationHelper(this.lifecycle, (FragmentActivity) this.activity, authOptions, authStrings, authCompletionHandler, z);
        this.authHelper = authenticationHelper;
        authenticationHelper.authenticate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onAuthenticationCompleted, reason: merged with bridge method [inline-methods] */
    public void m1105x1012d692(Messages.Result<Messages.AuthResult> result, Messages.AuthResult authResult) {
        if (this.authInProgress.compareAndSet(true, false)) {
            result.success(authResult);
        }
    }

    public boolean isDeviceSecure() {
        return this.keyguardManager != null && Build.VERSION.SDK_INT >= 23 && this.keyguardManager.isDeviceSecure();
    }

    private boolean canAuthenticateWithBiometrics() {
        BiometricManager biometricManager = this.biometricManager;
        return biometricManager != null && biometricManager.canAuthenticate(255) == 0;
    }

    private boolean hasBiometricHardware() {
        BiometricManager biometricManager = this.biometricManager;
        return (biometricManager == null || biometricManager.canAuthenticate(255) == 12) ? false : true;
    }

    public boolean canAuthenticateWithDeviceCredential() {
        if (Build.VERSION.SDK_INT < 30) {
            return isDeviceSecure();
        }
        BiometricManager biometricManager = this.biometricManager;
        return biometricManager != null && biometricManager.canAuthenticate(32768) == 0;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Messages.LocalAuthApi.CC.setUp(flutterPluginBinding.getBinaryMessenger(), this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Messages.LocalAuthApi.CC.setUp(flutterPluginBinding.getBinaryMessenger(), null);
    }

    private void setServicesFromActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        this.activity = activity;
        Context baseContext = activity.getBaseContext();
        this.biometricManager = BiometricManager.from(activity);
        this.keyguardManager = (KeyguardManager) baseContext.getSystemService("keyguard");
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addActivityResultListener(this.resultListener);
        setServicesFromActivity(activityPluginBinding.getActivity());
        this.lifecycle = FlutterLifecycleAdapter.getActivityLifecycle(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        this.lifecycle = null;
        this.activity = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addActivityResultListener(this.resultListener);
        setServicesFromActivity(activityPluginBinding.getActivity());
        this.lifecycle = FlutterLifecycleAdapter.getActivityLifecycle(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.lifecycle = null;
        this.activity = null;
    }

    final Activity getActivity() {
        return this.activity;
    }

    void setBiometricManager(BiometricManager biometricManager) {
        this.biometricManager = biometricManager;
    }

    void setKeyguardManager(KeyguardManager keyguardManager) {
        this.keyguardManager = keyguardManager;
    }
}
