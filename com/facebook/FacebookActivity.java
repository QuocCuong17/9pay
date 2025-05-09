package com.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.internal.logging.dumpsys.EndToEndDumper;
import com.facebook.login.LoginFragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FacebookActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J7\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0004H\u0014J\b\u0010\u0014\u001a\u00020\tH\u0002J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001c"}, d2 = {"Lcom/facebook/FacebookActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "<set-?>", "Landroidx/fragment/app/Fragment;", "currentFragment", "getCurrentFragment", "()Landroidx/fragment/app/Fragment;", "dump", "", "prefix", "", "fd", "Ljava/io/FileDescriptor;", "writer", "Ljava/io/PrintWriter;", "args", "", "(Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V", "getFragment", "handlePassThroughError", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class FacebookActivity extends FragmentActivity {
    private static final String FRAGMENT_TAG = "SingleFragment";
    public static final String PASS_THROUGH_CANCEL_ACTION = "PassThrough";
    private Fragment currentFragment;
    private static final String TAG = FacebookActivity.class.getName();

    public final Fragment getCurrentFragment() {
        return this.currentFragment;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!FacebookSdk.isInitialized()) {
            Utility.logd(TAG, "Facebook SDK not initialized. Make sure you call sdkInitialize inside your Application's onCreate method.");
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            FacebookSdk.sdkInitialize(applicationContext);
        }
        setContentView(com.facebook.common.R.layout.com_facebook_activity_layout);
        if (Intrinsics.areEqual(PASS_THROUGH_CANCEL_ACTION, intent.getAction())) {
            handlePassThroughError();
        } else {
            this.currentFragment = getFragment();
        }
    }

    protected Fragment getFragment() {
        Intent intent = getIntent();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (findFragmentByTag != null) {
            return findFragmentByTag;
        }
        if (Intrinsics.areEqual(FacebookDialogFragment.TAG, intent.getAction())) {
            FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
            facebookDialogFragment.setRetainInstance(true);
            facebookDialogFragment.show(supportFragmentManager, FRAGMENT_TAG);
            return facebookDialogFragment;
        }
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setRetainInstance(true);
        supportFragmentManager.beginTransaction().add(com.facebook.common.R.id.com_facebook_fragment_container, loginFragment, FRAGMENT_TAG).commit();
        return loginFragment;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Fragment fragment = this.currentFragment;
        if (fragment != null) {
            fragment.onConfigurationChanged(newConfig);
        }
    }

    private final void handlePassThroughError() {
        Intent requestIntent = getIntent();
        Intrinsics.checkNotNullExpressionValue(requestIntent, "requestIntent");
        FacebookException exceptionFromErrorData = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(requestIntent));
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        setResult(0, NativeProtocol.createProtocolResultIntent(intent, null, exceptionFromErrorData));
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(writer, "writer");
            EndToEndDumper companion = EndToEndDumper.INSTANCE.getInstance();
            boolean z = true;
            if (companion == null || !companion.maybeDump(prefix, writer, args)) {
                z = false;
            }
            if (z) {
                return;
            }
            super.dump(prefix, fd, writer, args);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }
}
