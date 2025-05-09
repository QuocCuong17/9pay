package vn.ai.faceauth.sdk.presentation.presentation;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.math.Primes;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.databinding.FacesdkCameraxActivityBinding;
import vn.ai.faceauth.sdk.presentation.di.LibraryModule;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;
import vn.ai.faceauth.sdk.presentation.navigation.SDKCallback;
import vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment;
import vn.ai.faceauth.sdk.presentation.presentation.fragment.InstructionFragment;
import vn.ai.faceauth.sdk.presentation.presentation.fragment.PermissionFragment;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u0017\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u0012\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0010H\u0014J-\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00062\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0!2\u0006\u0010\"\u001a\u00020#H\u0016¢\u0006\u0002\u0010$J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0016H\u0016J\b\u0010'\u001a\u00020\u0010H\u0002J\b\u0010(\u001a\u00020\u0010H\u0002J\b\u0010)\u001a\u00020\u0010H\u0002J\b\u0010*\u001a\u00020\u0010H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006+"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/LivenessCameraXActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/InstructionFragment$ICallbackAccept;", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/PermissionFragment$ICallbackCheckPermission;", "()V", "CAMERA_PERMISSION_REQUEST_CODE", "", "binding", "Lvn/ai/faceauth/sdk/databinding/FacesdkCameraxActivityBinding;", "mLanguage", "", "getMLanguage", "()Ljava/lang/String;", "mLanguage$delegate", "Lkotlin/Lazy;", "FullScreencall", "", "initLanguage", "initializeModules", "onBackPressed", "onCallback", "isAccept", "", "(Ljava/lang/Boolean;)V", "onCallbackPermission", "isGranted", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onWindowFocusChanged", "hasFocus", "openCameraFragment", "openInstructionFragment", "openPermissionFragment", "viewScreenAfterHasPermission", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessCameraXActivity extends AppCompatActivity implements InstructionFragment.ICallbackAccept, PermissionFragment.ICallbackCheckPermission {
    private FacesdkCameraxActivityBinding binding;
    private final int CAMERA_PERMISSION_REQUEST_CODE = 1001;

    /* renamed from: mLanguage$delegate, reason: from kotlin metadata */
    private final Lazy mLanguage = LazyKt.lazy(new Function0<String>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.LivenessCameraXActivity$mLanguage$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            Bundle extras;
            String string;
            Intent intent = LivenessCameraXActivity.this.getIntent();
            return (intent == null || (extras = intent.getExtras()) == null || (string = extras.getString(tfwhgw.rnigpa(210))) == null) ? tfwhgw.rnigpa(Primes.SMALL_FACTOR_LIMIT) : string;
        }
    });

    private final String getMLanguage() {
        return (String) this.mLanguage.getValue();
    }

    private final void initLanguage() {
        String mLanguage = getMLanguage();
        if (mLanguage != null) {
            if (mLanguage.length() > 0) {
                if (Intrinsics.areEqual(mLanguage, tfwhgw.rnigpa(33)) || Intrinsics.areEqual(mLanguage, tfwhgw.rnigpa(34))) {
                    Resources resources = getApplication().getApplicationContext().getResources();
                    Configuration configuration = new Configuration(resources.getConfiguration());
                    Locale locale = new Locale(mLanguage);
                    configuration.setLocale(locale);
                    resources.updateConfiguration(configuration, resources.getDisplayMetrics());
                    Resources resources2 = getResources();
                    Configuration configuration2 = new Configuration(resources2.getConfiguration());
                    configuration2.setLocale(locale);
                    resources2.updateConfiguration(configuration2, resources2.getDisplayMetrics());
                }
            }
        }
    }

    private final void initializeModules() {
        LibraryModule.INSTANCE.initializeDI(getApplication());
        CameraModule.INSTANCE.initializeDI(getApplication());
    }

    private final void openCameraFragment() {
        Log.d(tfwhgw.rnigpa(35), tfwhgw.rnigpa(36));
        CameraXFragment cameraXFragment = new CameraXFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fragmentContainer, cameraXFragment);
        beginTransaction.commit();
        supportFragmentManager.executePendingTransactions();
    }

    private final void openInstructionFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InstructionFragment()).commit();
    }

    private final void openPermissionFragment() {
        PermissionFragment newInstance = PermissionFragment.INSTANCE.newInstance();
        newInstance.setCallBackPermission(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newInstance).commit();
    }

    private final void viewScreenAfterHasPermission() {
        Log.d(tfwhgw.rnigpa(37), tfwhgw.rnigpa(38));
        if (AuthenticationID.INSTANCE.getConfig$trueface_release().isShowGuideScreen()) {
            openInstructionFragment();
        } else {
            openCameraFragment();
        }
    }

    public final void FullScreencall() {
        View decorView;
        int i;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 11 && i2 < 19) {
            decorView = getWindow().getDecorView();
            i = 8;
        } else {
            if (i2 < 19) {
                return;
            }
            decorView = getWindow().getDecorView();
            i = InputDeviceCompat.SOURCE_TOUCHSCREEN;
        }
        decorView.setSystemUiVisibility(i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.fragment.InstructionFragment.ICallbackAccept
    public void onCallback(Boolean isAccept) {
        openCameraFragment();
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.fragment.PermissionFragment.ICallbackCheckPermission
    public void onCallbackPermission(Boolean isGranted) {
        if (Intrinsics.areEqual(isGranted, Boolean.TRUE)) {
            Log.d(tfwhgw.rnigpa(39), tfwhgw.rnigpa(40));
            viewScreenAfterHasPermission();
        } else {
            SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
            if (callbackResult$trueface_release != null) {
                SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 306, tfwhgw.rnigpa(41), null, null, null, null, 60, null);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        SDKCallback callbackResult$trueface_release;
        super.onCreate(savedInstanceState);
        FullScreencall();
        AuthenticationID authenticationID = AuthenticationID.INSTANCE;
        if (authenticationID.getConfig$trueface_release() == null && (callbackResult$trueface_release = authenticationID.getCallbackResult$trueface_release()) != null) {
            SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 311, tfwhgw.rnigpa(42), "", "", "", null, 32, null);
        }
        FacesdkCameraxActivityBinding inflate = FacesdkCameraxActivityBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(43));
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initializeModules();
        initLanguage();
        if (ContextCompat.checkSelfPermission(this, tfwhgw.rnigpa(44)) != 0) {
            openPermissionFragment();
        } else {
            Log.d(tfwhgw.rnigpa(45), tfwhgw.rnigpa(46));
            viewScreenAfterHasPermission();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        AuthenticationID.INSTANCE.removeAction();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.CAMERA_PERMISSION_REQUEST_CODE) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                Log.d(tfwhgw.rnigpa(47), tfwhgw.rnigpa(48));
                viewScreenAfterHasPermission();
            } else {
                SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
                if (callbackResult$trueface_release != null) {
                    SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 0, tfwhgw.rnigpa(49), null, null, null, null, 60, null);
                }
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            FullScreencall();
        }
    }
}
