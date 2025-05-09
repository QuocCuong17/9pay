package vn.ai.faceauth.sdk.presentation.presentation.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.FacebookRequestErrorClassification;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.core.extensions.ImageExtensionsKt;
import vn.ai.faceauth.sdk.core.extensions.PrimitiveExtensionsKt;
import vn.ai.faceauth.sdk.databinding.FacesdkFragmentPermissionBinding;
import vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;
import vn.ai.faceauth.sdk.presentation.navigation.SDKCallback;
import vn.ai.faceauth.sdk.presentation.presentation.utils.EnumPosition;
import vn.ai.faceauth.sdk.presentation.presentation.utils.UtilsKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 (2\u00020\u0001:\u0002()B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001a\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u000e\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u000eJ\u0006\u0010&\u001a\u00020\u0010J\b\u0010'\u001a\u00020\u0010H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\t0\t0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/PermissionFragment;", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/BaseFragment;", "()V", "_binding", "Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentPermissionBinding;", "binding", "getBinding", "()Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentPermissionBinding;", "cameraManifest", "", "cameraPermission", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "iCallbackCheckPermission", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/PermissionFragment$ICallbackCheckPermission;", "forceCloseSDK", "", "handleCameraPermission", "granted", "", "parentView", "Landroid/view/View;", "initView", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "setCallBackPermission", "iCallback", "setupActionFaceSDK", "setupConfig", "Companion", "ICallbackCheckPermission", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PermissionFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FacesdkFragmentPermissionBinding _binding;
    private final String cameraManifest;
    private final ActivityResultLauncher<String> cameraPermission;
    private ICallbackCheckPermission iCallbackCheckPermission;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/PermissionFragment$Companion;", "", "()V", "newInstance", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/PermissionFragment;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final PermissionFragment newInstance() {
            PermissionFragment permissionFragment = new PermissionFragment();
            permissionFragment.setArguments(new Bundle());
            return permissionFragment;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/PermissionFragment$ICallbackCheckPermission;", "", "onCallbackPermission", "", "isGranted", "", "(Ljava/lang/Boolean;)V", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public interface ICallbackCheckPermission {
        void onCallbackPermission(Boolean isGranted);
    }

    public PermissionFragment() {
        super(R.layout.facesdk_fragment_permission);
        this.cameraManifest = tfwhgw.rnigpa(456);
        this.cameraPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.PermissionFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PermissionFragment.m2984cameraPermission$lambda2(PermissionFragment.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cameraPermission$lambda-2, reason: not valid java name */
    public static final void m2984cameraPermission$lambda2(PermissionFragment permissionFragment, Boolean bool) {
        permissionFragment.handleCameraPermission(PrimitiveExtensionsKt.orFalse(bool), permissionFragment.getView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forceCloseSDK() {
        Log.e(tfwhgw.rnigpa(457), tfwhgw.rnigpa(FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED));
        requireActivity().finish();
    }

    /* renamed from: getBinding, reason: from getter */
    private final FacesdkFragmentPermissionBinding get_binding() {
        return this._binding;
    }

    private final void handleCameraPermission(boolean granted, View parentView) {
        if (granted) {
            ICallbackCheckPermission iCallbackCheckPermission = this.iCallbackCheckPermission;
            if (iCallbackCheckPermission != null) {
                iCallbackCheckPermission.onCallbackPermission(Boolean.TRUE);
                return;
            }
            return;
        }
        ICallbackCheckPermission iCallbackCheckPermission2 = this.iCallbackCheckPermission;
        if (iCallbackCheckPermission2 != null) {
            iCallbackCheckPermission2.onCallbackPermission(Boolean.FALSE);
        }
        requireActivity().finish();
    }

    private final void initView() {
        TextView textView = get_binding().txtRequestAccess;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.facesdk_access_camera_request);
        Context context = getContext();
        textView.setText(String.format(string, Arrays.copyOf(new Object[]{context != null ? PermissionFragmentKt.getAppName(context) : null}, 1)));
        get_binding().btnBack.setOnClickListener(new View.OnClickListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.PermissionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PermissionFragment.m2985initView$lambda0(PermissionFragment.this, view);
            }
        });
        get_binding().btnAccept.setOnClickListener(new View.OnClickListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.PermissionFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PermissionFragment.m2986initView$lambda1(PermissionFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0, reason: not valid java name */
    public static final void m2985initView$lambda0(PermissionFragment permissionFragment, View view) {
        SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
        if (callbackResult$trueface_release != null) {
            SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 0, tfwhgw.rnigpa(459), null, null, null, null, 60, null);
        }
        permissionFragment.requireActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1, reason: not valid java name */
    public static final void m2986initView$lambda1(PermissionFragment permissionFragment, View view) {
        permissionFragment.cameraPermission.launch(permissionFragment.cameraManifest);
    }

    @JvmStatic
    public static final PermissionFragment newInstance() {
        return INSTANCE.newInstance();
    }

    private final void setupConfig() {
        SDKConfig sdkConfig$trueface_release = AuthenticationID.INSTANCE.getSdkConfig$trueface_release();
        String closeColor = sdkConfig$trueface_release.getCloseColor();
        get_binding().btnBack.setVisibility(sdkConfig$trueface_release.isCancelable() ? 0 : 4);
        get_binding().btnBack.setColorFilter(Color.parseColor(closeColor), PorterDuff.Mode.SRC_IN);
        String textColor = sdkConfig$trueface_release.getTextColor();
        get_binding().toolbarText.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().toolbarText, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSizeTitle());
        get_binding().txtSub.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().txtSub, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        get_binding().txtRequestAccess.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().txtRequestAccess, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        String obj = EnumPosition.LEFT.toString();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        if (Intrinsics.areEqual(obj, EnumPosition.RIGHT.toString())) {
            layoutParams.addRule(21);
            get_binding().btnBack.setLayoutParams(layoutParams);
        }
        ImageExtensionsKt.setColorFilter(Color.parseColor(sdkConfig$trueface_release.getPrimaryColor()), get_binding().btnAccept);
        String backgroundColor = sdkConfig$trueface_release.getBackgroundColor();
        get_binding().btnAccept.setTextColor(Color.parseColor(sdkConfig$trueface_release.getTextButtonColor()));
        UtilsKt.updateFont(get_binding().btnAccept, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSizeButton());
        get_binding().bgLinearLayout.setBackgroundColor(Color.parseColor(backgroundColor));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.iCallbackCheckPermission = (ICallbackCheckPermission) context;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this._binding = FacesdkFragmentPermissionBinding.inflate(inflater, container, false);
        return get_binding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setupConfig();
        setupActionFaceSDK();
    }

    public final void setCallBackPermission(ICallbackCheckPermission iCallback) {
        this.iCallbackCheckPermission = iCallback;
    }

    public final void setupActionFaceSDK() {
        AuthenticationID.INSTANCE.setCloseSDK$trueface_release(new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.PermissionFragment$setupActionFaceSDK$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PermissionFragment.this.forceCloseSDK();
            }
        });
    }
}
