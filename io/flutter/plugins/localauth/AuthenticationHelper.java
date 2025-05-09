package io.flutter.plugins.localauth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.flutter.plugins.localauth.Messages;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class AuthenticationHelper extends BiometricPrompt.AuthenticationCallback implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
    private final FragmentActivity activity;
    private BiometricPrompt biometricPrompt;
    private final AuthCompletionHandler completionHandler;
    private final boolean isAuthSticky;
    private final Lifecycle lifecycle;
    private final BiometricPrompt.PromptInfo promptInfo;
    private final Messages.AuthStrings strings;
    private final boolean useErrorDialogs;
    private boolean activityPaused = false;
    private final UiThreadExecutor uiThreadExecutor = new UiThreadExecutor();

    /* loaded from: classes5.dex */
    interface AuthCompletionHandler {
        void complete(Messages.AuthResult authResult);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationFailed() {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onCreate(LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner lifecycleOwner) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticationHelper(Lifecycle lifecycle, FragmentActivity fragmentActivity, Messages.AuthOptions authOptions, Messages.AuthStrings authStrings, AuthCompletionHandler authCompletionHandler, boolean z) {
        int i;
        this.lifecycle = lifecycle;
        this.activity = fragmentActivity;
        this.completionHandler = authCompletionHandler;
        this.strings = authStrings;
        this.isAuthSticky = authOptions.getSticky().booleanValue();
        this.useErrorDialogs = authOptions.getUseErrorDialgs().booleanValue();
        BiometricPrompt.PromptInfo.Builder confirmationRequired = new BiometricPrompt.PromptInfo.Builder().setDescription(authStrings.getReason()).setTitle(authStrings.getSignInTitle()).setSubtitle(authStrings.getBiometricHint()).setConfirmationRequired(authOptions.getSensitiveTransaction().booleanValue());
        if (z) {
            i = 33023;
        } else {
            confirmationRequired.setNegativeButtonText(authStrings.getCancelButton());
            i = 255;
        }
        confirmationRequired.setAllowedAuthenticators(i);
        this.promptInfo = confirmationRequired.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void authenticate() {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        } else {
            this.activity.getApplication().registerActivityLifecycleCallbacks(this);
        }
        BiometricPrompt biometricPrompt = new BiometricPrompt(this.activity, this.uiThreadExecutor, this);
        this.biometricPrompt = biometricPrompt;
        biometricPrompt.authenticate(this.promptInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopAuthentication() {
        BiometricPrompt biometricPrompt = this.biometricPrompt;
        if (biometricPrompt != null) {
            biometricPrompt.cancelAuthentication();
            this.biometricPrompt = null;
        }
    }

    private void stop() {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        } else {
            this.activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (i != 1) {
            if (i == 7) {
                this.completionHandler.complete(Messages.AuthResult.ERROR_LOCKED_OUT_TEMPORARILY);
            } else if (i == 9) {
                this.completionHandler.complete(Messages.AuthResult.ERROR_LOCKED_OUT_PERMANENTLY);
            } else if (i == 14) {
                if (this.useErrorDialogs) {
                    showGoToSettingsDialog(this.strings.getDeviceCredentialsRequiredTitle(), this.strings.getDeviceCredentialsSetupDescription());
                    return;
                }
                this.completionHandler.complete(Messages.AuthResult.ERROR_NOT_AVAILABLE);
            } else {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 11) {
                            if (i != 12) {
                                this.completionHandler.complete(Messages.AuthResult.FAILURE);
                            }
                        }
                    } else if (this.activityPaused && this.isAuthSticky) {
                        return;
                    } else {
                        this.completionHandler.complete(Messages.AuthResult.FAILURE);
                    }
                }
                if (this.useErrorDialogs) {
                    showGoToSettingsDialog(this.strings.getBiometricRequiredTitle(), this.strings.getGoToSettingsDescription());
                    return;
                }
                this.completionHandler.complete(Messages.AuthResult.ERROR_NOT_ENROLLED);
            }
            stop();
        }
        this.completionHandler.complete(Messages.AuthResult.ERROR_NOT_AVAILABLE);
        stop();
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
        this.completionHandler.complete(Messages.AuthResult.SUCCESS);
        stop();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (this.isAuthSticky) {
            this.activityPaused = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (this.isAuthSticky) {
            this.activityPaused = false;
            final BiometricPrompt biometricPrompt = new BiometricPrompt(this.activity, this.uiThreadExecutor, this);
            this.uiThreadExecutor.handler.post(new Runnable() { // from class: io.flutter.plugins.localauth.AuthenticationHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    AuthenticationHelper.this.m1102xbbbb94b9(biometricPrompt);
                }
            });
        }
    }

    /* renamed from: lambda$onActivityResumed$0$io-flutter-plugins-localauth-AuthenticationHelper, reason: not valid java name */
    public /* synthetic */ void m1102xbbbb94b9(BiometricPrompt biometricPrompt) {
        biometricPrompt.authenticate(this.promptInfo);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onPause(LifecycleOwner lifecycleOwner) {
        onActivityPaused(null);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner lifecycleOwner) {
        onActivityResumed(null);
    }

    private void showGoToSettingsDialog(String str, String str2) {
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.go_to_setting, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.fingerprint_required);
        TextView textView2 = (TextView) inflate.findViewById(R.id.go_to_setting_description);
        textView.setText(str);
        textView2.setText(str2);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.activity, R.style.AlertDialogCustom);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: io.flutter.plugins.localauth.AuthenticationHelper$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AuthenticationHelper.this.m1103xd70b7f36(dialogInterface, i);
            }
        };
        new AlertDialog.Builder(contextThemeWrapper).setView(inflate).setPositiveButton(this.strings.getGoToSettingsButton(), onClickListener).setNegativeButton(this.strings.getCancelButton(), new DialogInterface.OnClickListener() { // from class: io.flutter.plugins.localauth.AuthenticationHelper$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AuthenticationHelper.this.m1104x1a969cf7(dialogInterface, i);
            }
        }).setCancelable(false).show();
    }

    /* renamed from: lambda$showGoToSettingsDialog$1$io-flutter-plugins-localauth-AuthenticationHelper, reason: not valid java name */
    public /* synthetic */ void m1103xd70b7f36(DialogInterface dialogInterface, int i) {
        this.completionHandler.complete(Messages.AuthResult.FAILURE);
        stop();
        this.activity.startActivity(new Intent("android.settings.SECURITY_SETTINGS"));
    }

    /* renamed from: lambda$showGoToSettingsDialog$2$io-flutter-plugins-localauth-AuthenticationHelper, reason: not valid java name */
    public /* synthetic */ void m1104x1a969cf7(DialogInterface dialogInterface, int i) {
        this.completionHandler.complete(Messages.AuthResult.FAILURE);
        stop();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class UiThreadExecutor implements Executor {
        final Handler handler = new Handler(Looper.getMainLooper());

        UiThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.handler.post(runnable);
        }
    }
}
