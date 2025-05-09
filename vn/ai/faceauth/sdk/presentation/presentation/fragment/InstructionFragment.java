package vn.ai.faceauth.sdk.presentation.presentation.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.core.extensions.ImageExtensionsKt;
import vn.ai.faceauth.sdk.databinding.FacesdkFragmentInstructionBinding;
import vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;
import vn.ai.faceauth.sdk.presentation.presentation.utils.UtilsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0006\u0010\u001a\u001a\u00020\u000bJ\b\u0010\u001b\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/InstructionFragment;", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/BaseFragment;", "()V", "_binding", "Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentInstructionBinding;", "binding", "getBinding", "()Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentInstructionBinding;", "iCallbackAccept", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/InstructionFragment$ICallbackAccept;", "forceCloseSDK", "", "initView", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "setupActionFaceSDK", "setupConfig", "Companion", "ICallbackAccept", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class InstructionFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FacesdkFragmentInstructionBinding _binding;
    private ICallbackAccept iCallbackAccept;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/InstructionFragment$Companion;", "", "()V", "newInstance", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/InstructionFragment;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final InstructionFragment newInstance() {
            InstructionFragment instructionFragment = new InstructionFragment();
            instructionFragment.setArguments(new Bundle());
            return instructionFragment;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/InstructionFragment$ICallbackAccept;", "", "onCallback", "", "isGranted", "", "(Ljava/lang/Boolean;)V", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public interface ICallbackAccept {
        void onCallback(Boolean isGranted);
    }

    public InstructionFragment() {
        super(R.layout.facesdk_fragment_instruction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forceCloseSDK() {
        Log.e(tfwhgw.rnigpa(371), tfwhgw.rnigpa(372));
        requireActivity().finish();
    }

    /* renamed from: getBinding, reason: from getter */
    private final FacesdkFragmentInstructionBinding get_binding() {
        return this._binding;
    }

    private final void initView() {
        get_binding().btnAccept.setOnClickListener(new View.OnClickListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.InstructionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InstructionFragment.m2983initView$lambda0(InstructionFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0, reason: not valid java name */
    public static final void m2983initView$lambda0(InstructionFragment instructionFragment, View view) {
        ICallbackAccept iCallbackAccept = instructionFragment.iCallbackAccept;
        if (iCallbackAccept != null) {
            iCallbackAccept.onCallback(Boolean.TRUE);
        }
    }

    @JvmStatic
    public static final InstructionFragment newInstance() {
        return INSTANCE.newInstance();
    }

    private final void setupConfig() {
        SDKConfig sdkConfig$trueface_release = AuthenticationID.INSTANCE.getSdkConfig$trueface_release();
        String textColor = sdkConfig$trueface_release.getTextColor();
        String primaryColor = sdkConfig$trueface_release.getPrimaryColor();
        get_binding().toolbarText.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().toolbarText, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSizeTitle());
        get_binding().textInstruction.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().textInstruction, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        get_binding().textInstruction1.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().textInstruction1, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        get_binding().textInstruction2.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().textInstruction2, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        get_binding().textInstruction3.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().textInstruction3, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        get_binding().textInstruction4.setTextColor(Color.parseColor(textColor));
        UtilsKt.updateFont(get_binding().textInstruction4, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSize());
        try {
            ImageExtensionsKt.setColorFilter(Color.parseColor(primaryColor), get_binding().btnAccept);
        } catch (Exception e) {
            System.out.println((Object) e.toString());
        }
        ImageExtensionsKt.setColorFilter(Color.parseColor(primaryColor), get_binding().btnAccept);
        String backgroundColor = sdkConfig$trueface_release.getBackgroundColor();
        String textButtonColor = sdkConfig$trueface_release.getTextButtonColor();
        UtilsKt.updateFont(get_binding().btnAccept, sdkConfig$trueface_release.getFontName(), sdkConfig$trueface_release.getTextSizeButton());
        get_binding().btnAccept.setTextColor(Color.parseColor(textButtonColor));
        get_binding().getRoot().setBackgroundColor(Color.parseColor(backgroundColor));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.iCallbackAccept = (ICallbackAccept) context;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this._binding = FacesdkFragmentInstructionBinding.inflate(inflater, container, false);
        return get_binding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setupConfig();
        setupActionFaceSDK();
    }

    public final void setupActionFaceSDK() {
        AuthenticationID.INSTANCE.setCloseSDK$trueface_release(new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.InstructionFragment$setupActionFaceSDK$1
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
                InstructionFragment.this.forceCloseSDK();
            }
        });
    }
}
