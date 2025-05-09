package co.hyperverge.hyperkyc.ui.nfc;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.databinding.HkFragmentNfcReaderInstructionBinding;
import co.hyperverge.hyperkyc.ui.BaseFragment;
import co.hyperverge.hyperkyc.ui.HKMainActivity;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.HKLottieHelper;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity;
import co.hyperverge.hyperkyc.webCore.ui.WebCoreVM;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: NFCReaderInstructionFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0002J\u001a\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020 H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R'\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000e\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCReaderInstructionFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcReaderInstructionBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcReaderInstructionBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "Lkotlin/Lazy;", NFCReaderInstructionFragment.ARG_KEY_NFC_READER_UI_STATE, "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "getNfcReaderUIState", "()Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "nfcReaderUIState$delegate", "textConfig", "", "", "getTextConfig", "()Ljava/util/Map;", "textConfig$delegate", "webCoreVM", "Lco/hyperverge/hyperkyc/webCore/ui/WebCoreVM;", "getWebCoreVM", "()Lco/hyperverge/hyperkyc/webCore/ui/WebCoreVM;", "webCoreVM$delegate", "initViews", "", "loadAnimation", "lav", "Lcom/airbnb/lottie/LottieAnimationView;", "customUrl", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "proceed", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NFCReaderInstructionFragment extends BaseFragment {
    public static final /* synthetic */ String ARG_KEY_NFC_READER_UI_STATE = "nfcReaderUIState";
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;

    /* renamed from: nfcReaderUIState$delegate, reason: from kotlin metadata */
    private final Lazy nfcReaderUIState;

    /* renamed from: textConfig$delegate, reason: from kotlin metadata */
    private final Lazy textConfig;

    /* renamed from: webCoreVM$delegate, reason: from kotlin metadata */
    private final Lazy webCoreVM;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(NFCReaderInstructionFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcReaderInstructionBinding;", 0))};

    public NFCReaderInstructionFragment() {
        super(R.layout.hk_fragment_nfc_reader_instruction);
        final NFCReaderInstructionFragment nFCReaderInstructionFragment = this;
        this.webCoreVM = FragmentViewModelLazyKt.createViewModelLazy(nFCReaderInstructionFragment, Reflection.getOrCreateKotlinClass(WebCoreVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$special$$inlined$activityViewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(nFCReaderInstructionFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$special$$inlined$activityViewModels$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.binding = FragmentViewBindingDelegateKt.viewBinding(nFCReaderInstructionFragment, NFCReaderInstructionFragment$binding$2.INSTANCE);
        this.nfcReaderUIState = LazyKt.lazy(new Function0<WorkflowUIState.NFCReader>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$nfcReaderUIState$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WorkflowUIState.NFCReader invoke() {
                Bundle arguments = NFCReaderInstructionFragment.this.getArguments();
                WorkflowUIState.NFCReader nFCReader = arguments != null ? (WorkflowUIState.NFCReader) arguments.getParcelable(NFCReaderInstructionFragment.ARG_KEY_NFC_READER_UI_STATE) : null;
                Intrinsics.checkNotNull(nFCReader);
                return nFCReader;
            }
        });
        this.textConfig = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$textConfig$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                WorkflowUIState.NFCReader nfcReaderUIState;
                nfcReaderUIState = NFCReaderInstructionFragment.this.getNfcReaderUIState();
                Map<String, ? extends String> textConfigs = nfcReaderUIState.getTextConfigs();
                if (!(textConfigs instanceof Map)) {
                    textConfigs = null;
                }
                return textConfigs == null ? MapsKt.emptyMap() : textConfigs;
            }
        });
    }

    private final WebCoreVM getWebCoreVM() {
        return (WebCoreVM) this.webCoreVM.getValue();
    }

    private final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    private final HkFragmentNfcReaderInstructionBinding getBinding() {
        return (HkFragmentNfcReaderInstructionBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WorkflowUIState.NFCReader getNfcReaderUIState() {
        return (WorkflowUIState.NFCReader) this.nfcReaderUIState.getValue();
    }

    private final Map<String, String> getTextConfig() {
        return (Map) this.textConfig.getValue();
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "onViewCreated() 1 called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = "";
        }
        String packageName = (String) m1202constructorimpl;
        if (CoreExtsKt.isDebug()) {
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls2 = getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    if (canonicalName2 != null) {
                        str = canonicalName2;
                    }
                } else {
                    str = substringAfterLast$default;
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                String str3 = "onViewCreated() 1 called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
                if (str3 == null) {
                    str3 = "null ";
                }
                sb2.append(str3);
                sb2.append(' ');
                sb2.append("");
                Log.println(3, str, sb2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$7$lambda$2(NFCReaderInstructionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$7$lambda$3(NFCReaderInstructionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.proceed();
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    public void initViews() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        boolean shouldShowBranding;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("initViews() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "initViews() called ");
                }
            }
        }
        HkFragmentNfcReaderInstructionBinding binding = getBinding();
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getNfcReaderUIState().getShowBackButton() ? 0 : 8);
        binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCReaderInstructionFragment.initViews$lambda$7$lambda$2(NFCReaderInstructionFragment.this, view);
            }
        });
        binding.btnProceed.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCReaderInstructionFragment.initViews$lambda$7$lambda$3(NFCReaderInstructionFragment.this, view);
            }
        });
        String str2 = getTextConfig().get("nfcInstructions_title");
        if (str2 != null) {
            if (str2.length() > 0) {
                binding.tvTitle.setText(UIExtsKt.fromHTML(str2));
            }
        }
        String str3 = getTextConfig().get("nfcInstructions_desc");
        if (str3 != null) {
            if (str3.length() > 0) {
                binding.tvSubtitle.setText(UIExtsKt.fromHTML(str3));
            }
        }
        String str4 = getTextConfig().get("nfcInstructions_button");
        if (str4 != null) {
            if (str4.length() > 0) {
                binding.btnProceed.setText(UIExtsKt.fromHTML(str4));
            }
        }
        LottieAnimationView lavNFCInstructions = binding.lavNFCInstructions;
        Intrinsics.checkNotNullExpressionValue(lavNFCInstructions, "lavNFCInstructions");
        loadAnimation(lavNFCInstructions, HyperSnapBridgeKt.getUiConfigUtil().getNfcInstructionAnimation());
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(binding.tvTitle);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(binding.tvSubtitle);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(binding.btnProceed);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
        LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        LinearLayout linearLayout2 = linearLayout;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (ActivityExtsKt.isHKWebCoreActivity(requireActivity)) {
            shouldShowBranding = getWebCoreVM().getRemoteConfig().getUseBranding();
        } else {
            shouldShowBranding = getMainVM().shouldShowBranding();
        }
        linearLayout2.setVisibility(shouldShowBranding ? 0 : 8);
    }

    private final void loadAnimation(LottieAnimationView lav, String customUrl) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "loadAnimation() called with: lav = " + lav + ", customUrl = " + customUrl;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "loadAnimation() called with: lav = " + lav + ", customUrl = " + customUrl;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        HKLottieHelper.INSTANCE.getShared().load(lav, HKLottieHelper.NFC_INSTRUCTION, customUrl == null ? "" : customUrl, HKLottieHelper.State.START, null);
    }

    private final void proceed() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("proceed() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "proceed() called ");
                }
            }
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (ActivityExtsKt.isHKWebCoreActivity(requireActivity)) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity");
            ActivityExtsKt.replaceContent$default((HKWebCoreActivity) requireActivity2, new WebCoreNFCReaderFragment(), BundleKt.bundleOf(TuplesKt.to("nfcUIState", getNfcReaderUIState())), false, "nfcFragment", 0, 20, null);
        } else {
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNull(requireActivity3, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
            ActivityExtsKt.replaceContent$default((HKMainActivity) requireActivity3, new NFCReaderFragment(), BundleKt.bundleOf(TuplesKt.to("nfcUIState", getNfcReaderUIState())), false, "nfcFragment", 0, 20, null);
        }
    }
}
