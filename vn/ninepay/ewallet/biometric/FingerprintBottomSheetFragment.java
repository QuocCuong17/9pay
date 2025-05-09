package vn.ninepay.ewallet.biometric;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import vn.ninepay.ewallet.biometric.FingerprintUiHelper;
import vn.ninepay.ewallet.stg.R;

/* loaded from: classes6.dex */
public class FingerprintBottomSheetFragment extends BottomSheetDialogFragment implements FingerprintUiHelper.Callback {
    private BiometricAuthCallback biometricAuthCallback;
    private ImageView ivBiometric;
    private FingerprintUiHelper mFingerprintUiHelper;
    private int screen = 0;
    private TextView tvMessageLogin;
    private TextView tvPass;

    /* loaded from: classes6.dex */
    public interface BiometricAuthCallback {
        void onAuthError();

        void onAuthenticated();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onError$3(Animator animator) {
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void setCallback(BiometricAuthCallback biometricAuthCallback) {
        this.biometricAuthCallback = biometricAuthCallback;
    }

    public void setScreen(int i) {
        this.screen = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_bottom_biometric, viewGroup, false);
        inflate.findViewById(R.id.tv_used_password).setOnClickListener(new View.OnClickListener() { // from class: vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FingerprintBottomSheetFragment.this.m3006x6cb2ee95(view);
            }
        });
        this.tvPass = (TextView) inflate.findViewById(R.id.tv_used_password);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_biometric);
        this.ivBiometric = imageView;
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_biometric_finger));
        TextView textView = (TextView) inflate.findViewById(R.id.tv_message);
        this.tvMessageLogin = textView;
        textView.setText(getString(R.string.message_biometric));
        if (Build.VERSION.SDK_INT >= 23) {
            this.mFingerprintUiHelper = new FingerprintUiHelper((FingerprintManager) getActivity().getSystemService(FingerprintManager.class), this);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            this.mFingerprintUiHelper.startListening();
        }
        return inflate;
    }

    /* renamed from: lambda$onCreateView$0$vn-ninepay-ewallet-biometric-FingerprintBottomSheetFragment, reason: not valid java name */
    public /* synthetic */ void m3006x6cb2ee95(View view) {
        dismiss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (Build.VERSION.SDK_INT >= 23) {
            this.mFingerprintUiHelper.stopListening();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // vn.ninepay.ewallet.biometric.FingerprintUiHelper.Callback
    public void onAuthenticated() {
        this.tvMessageLogin.setText(getString(R.string.message_biometric_3));
        this.tvMessageLogin.setTextColor(-16777216);
        this.ivBiometric.setVisibility(0);
        this.tvPass.setText("");
        this.tvPass.setBackgroundColor(0);
        this.tvPass.setClickable(false);
        this.ivBiometric.setImageDrawable(getResources().getDrawable(R.mipmap.success_biometric));
        YoYo.with(Techniques.Bounce).duration(1000L).onEnd(new YoYo.AnimatorCallback() { // from class: vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment$$ExternalSyntheticLambda1
            @Override // com.daimajia.androidanimations.library.YoYo.AnimatorCallback
            public final void call(Animator animator) {
                FingerprintBottomSheetFragment.this.m3005x294d740d(animator);
            }
        }).playOn(this.ivBiometric);
    }

    /* renamed from: lambda$onAuthenticated$2$vn-ninepay-ewallet-biometric-FingerprintBottomSheetFragment, reason: not valid java name */
    public /* synthetic */ void m3005x294d740d(Animator animator) {
        new Handler().postDelayed(new Runnable() { // from class: vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FingerprintBottomSheetFragment.this.m3004x6ed7d38c();
            }
        }, 1000L);
    }

    /* renamed from: lambda$onAuthenticated$1$vn-ninepay-ewallet-biometric-FingerprintBottomSheetFragment, reason: not valid java name */
    public /* synthetic */ void m3004x6ed7d38c() {
        BiometricAuthCallback biometricAuthCallback = this.biometricAuthCallback;
        if (biometricAuthCallback != null) {
            biometricAuthCallback.onAuthenticated();
        }
        try {
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // vn.ninepay.ewallet.biometric.FingerprintUiHelper.Callback
    public void onError() {
        this.ivBiometric.setVisibility(0);
        this.ivBiometric.setImageDrawable(getResources().getDrawable(R.mipmap.ic_wrong_biometric));
        this.tvMessageLogin.setText(getString(R.string.retry_biometric2));
        this.tvMessageLogin.setTextColor(SupportMenu.CATEGORY_MASK);
        YoYo.with(Techniques.Shake).duration(500L).onEnd(new YoYo.AnimatorCallback() { // from class: vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment$$ExternalSyntheticLambda2
            @Override // com.daimajia.androidanimations.library.YoYo.AnimatorCallback
            public final void call(Animator animator) {
                FingerprintBottomSheetFragment.lambda$onError$3(animator);
            }
        }).playOn(this.tvMessageLogin);
    }

    @Override // vn.ninepay.ewallet.biometric.FingerprintUiHelper.Callback
    public void onLimited() {
        try {
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BiometricAuthCallback biometricAuthCallback = this.biometricAuthCallback;
        if (biometricAuthCallback != null) {
            biometricAuthCallback.onAuthError();
        }
    }

    @Override // vn.ninepay.ewallet.biometric.FingerprintUiHelper.Callback
    public void onCancel() {
        try {
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
