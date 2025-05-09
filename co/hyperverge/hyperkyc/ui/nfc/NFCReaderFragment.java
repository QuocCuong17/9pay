package co.hyperverge.hyperkyc.ui.nfc;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hvnfcsdk.adapters.HVNFCAdapter;
import co.hyperverge.hvnfcsdk.listeners.NFCParserStatus;
import co.hyperverge.hvnfcsdk.model.HVNFCError;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.databinding.HkFragmentNfcBinding;
import co.hyperverge.hyperkyc.ui.BaseActivity;
import co.hyperverge.hyperkyc.ui.BaseFragment;
import co.hyperverge.hyperkyc.ui.HKMainActivity;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.nfc.NFCUIFlowModel;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.HKLottieHelper;
import co.hyperverge.hyperkyc.utils.PermDialogCallback;
import co.hyperverge.hyperkyc.utils.PermissionHandler;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: NFCReaderFragment.kt */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 g2\u00020\u0001:\u0001gB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020<H\u0002J\b\u0010>\u001a\u00020\u0010H\u0016J\b\u0010?\u001a\u00020<H\u0002J\b\u0010@\u001a\u00020<H\u0002J\b\u0010A\u001a\u00020<H\u0016J\b\u0010B\u001a\u00020<H\u0002J\u001a\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010G\u001a\u00020<H\u0002J\b\u0010H\u001a\u00020<H\u0002J\b\u0010I\u001a\u00020<H\u0002J\b\u0010J\u001a\u00020<H\u0002J\u0010\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020MH\u0016J\b\u0010N\u001a\u00020<H\u0016J\b\u0010O\u001a\u00020<H\u0016J\b\u0010P\u001a\u00020<H\u0016J\u001a\u0010Q\u001a\u00020<2\u0006\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\u0015\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020XH\u0000¢\u0006\u0002\bYJ\b\u0010Z\u001a\u00020<H\u0002J\b\u0010[\u001a\u00020<H\u0002J\b\u0010\\\u001a\u00020<H\u0002J\u0018\u0010]\u001a\u00020<2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010^\u001a\u00020<H\u0002J\u0014\u0010_\u001a\u00020<2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010aH\u0002J\u0010\u0010b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010c\u001a\u00020<2\u0006\u0010d\u001a\u00020\u0010H\u0002J\b\u0010e\u001a\u00020<H\u0002J\u0012\u0010f\u001a\u00020<2\b\u0010`\u001a\u0004\u0018\u00010aH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u001b\u001a\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u0010\u0012\f\u0012\n .*\u0004\u0018\u00010\f0\f0-X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\u001b\u001a\u0004\b1\u00102R\u000e\u00104\u001a\u000205X\u0082.¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020:09X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCReaderFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "errorCode", "", "errorMessage", "", "errorRetryMap", "", "isCompleted", "", "isModuleStarted", "isPermissionDialogShown", "isRegistered", "mReceiver", "Landroid/content/BroadcastReceiver;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "Lkotlin/Lazy;", "nfcAdapter", "Lco/hyperverge/hvnfcsdk/adapters/HVNFCAdapter;", "nfcMgr", "Landroid/nfc/NfcManager;", "nfcStatus", "nfcUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "getNfcUIState", "()Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "nfcUIState$delegate", "nfcVM", "Lco/hyperverge/hyperkyc/ui/nfc/NFCReaderVM;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "permissionHandler", "Lco/hyperverge/hyperkyc/utils/PermissionHandler;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "textConfig", "", "getTextConfig", "()Ljava/util/Map;", "textConfig$delegate", WorkflowModule.Properties.Section.Component.Type.TIMER, "Ljava/util/Timer;", "timerTask", "Ljava/util/TimerTask;", "uiStatesView", "", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIStateView;", "cancelTimer", "", "cardDisconnected", "deferredInitViews", "finishPermissionWithError", "handlePermissionDialog", "initViews", "initialiseNFC", "loadAnimation", "lav", "Lcom/airbnb/lottie/LottieAnimationView;", "customUrl", "loadErrorRetries", "loadListViews", "loadUIConfig", "observeUIState", "onAttach", "context", "Landroid/content/Context;", "onDetach", "onResume", "onStart", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "parseData", SDKConstants.PARAM_INTENT, "Landroid/content/Intent;", "parseData$hyperkyc_release", "processErrorState", "requestPermission", "resetState", "sendErrorEvent", "sendModuleEnded", "sendResult", "data", "Lorg/json/JSONObject;", "shouldRetry", "showButtons", "shouldShow", "startSkipButtonTimer", "verifyForIncompleteScan", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NFCReaderFragment extends BaseFragment {
    public static final String ARG_KEY_NFC_UI_STATE = "nfcUIState";
    public static final String ERROR = "error";
    public static final String NFC_FRAGMENT_ID = "nfcFragment";
    public static final String SKIPPED = "skipped";
    public static final String SUCCESS = "success";
    public static final String TEXT_CONFIG_NFC_DESC = "nfcScreen_desc";
    public static final String TEXT_CONFIG_NFC_RETRY = "nfcScreen_retry";
    public static final String TEXT_CONFIG_NFC_SKIP = "nfcScreen_skip";
    public static final String TEXT_CONFIG_NFC_TITLE = "nfcScreen_title";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private int errorCode;
    private String errorMessage;
    private Map<Integer, Integer> errorRetryMap;
    private boolean isCompleted;
    private boolean isModuleStarted;
    private boolean isPermissionDialogShown;
    private boolean isRegistered;
    private final BroadcastReceiver mReceiver;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;
    private HVNFCAdapter nfcAdapter;
    private NfcManager nfcMgr;
    private String nfcStatus;

    /* renamed from: nfcUIState$delegate, reason: from kotlin metadata */
    private final Lazy nfcUIState;
    private NFCReaderVM nfcVM;
    private OnBackPressedCallback onBackPressedCallback;
    private PermissionHandler permissionHandler;
    private final ActivityResultLauncher<String> requestPermissionLauncher;

    /* renamed from: textConfig$delegate, reason: from kotlin metadata */
    private final Lazy textConfig;
    private Timer timer;
    private TimerTask timerTask;
    private List<NFCUIStateView> uiStatesView;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(NFCReaderFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcBinding;", 0))};

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    public boolean deferredInitViews() {
        return true;
    }

    public NFCReaderFragment() {
        super(R.layout.hk_fragment_nfc);
        final NFCReaderFragment nFCReaderFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(nFCReaderFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$special$$inlined$activityViewModels$default$2
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
        this.binding = FragmentViewBindingDelegateKt.viewBinding(nFCReaderFragment, NFCReaderFragment$binding$2.INSTANCE);
        this.uiStatesView = CollectionsKt.emptyList();
        this.errorCode = -1;
        this.errorRetryMap = new LinkedHashMap();
        this.permissionHandler = new PermissionHandler();
        this.nfcUIState = LazyKt.lazy(new Function0<WorkflowUIState.NFCReader>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$nfcUIState$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WorkflowUIState.NFCReader invoke() {
                Bundle arguments = NFCReaderFragment.this.getArguments();
                WorkflowUIState.NFCReader nFCReader = arguments != null ? (WorkflowUIState.NFCReader) arguments.getParcelable("nfcUIState") : null;
                Intrinsics.checkNotNull(nFCReader);
                return nFCReader;
            }
        });
        this.textConfig = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$textConfig$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                WorkflowUIState.NFCReader nfcUIState;
                nfcUIState = NFCReaderFragment.this.getNfcUIState();
                Map<String, ? extends String> textConfigs = nfcUIState.getTextConfigs();
                if (!(textConfigs instanceof Map)) {
                    textConfigs = null;
                }
                return textConfigs == null ? MapsKt.emptyMap() : textConfigs;
            }
        });
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                NFCReaderFragment.requestPermissionLauncher$lambda$0(NFCReaderFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…nDialog()\n        }\n    }");
        this.requestPermissionLauncher = registerForActivityResult;
        this.mReceiver = new BroadcastReceiver() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$mReceiver$1
            /* JADX WARN: Code restructure failed: missing block: B:58:0x0147, code lost:
            
                if (r0 != null) goto L55;
             */
            /* JADX WARN: Code restructure failed: missing block: B:62:0x0157, code lost:
            
                if (r0 == null) goto L56;
             */
            /* JADX WARN: Code restructure failed: missing block: B:63:0x015b, code lost:
            
                r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
             */
            /* JADX WARN: Code restructure failed: missing block: B:64:0x016a, code lost:
            
                if (r0.find() == false) goto L59;
             */
            /* JADX WARN: Code restructure failed: missing block: B:65:0x016c, code lost:
            
                r8 = r0.replaceAll("");
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
             */
            /* JADX WARN: Code restructure failed: missing block: B:67:0x0177, code lost:
            
                if (r8.length() <= 23) goto L65;
             */
            /* JADX WARN: Code restructure failed: missing block: B:69:0x017d, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
             */
            /* JADX WARN: Code restructure failed: missing block: B:70:0x0180, code lost:
            
                r8 = r8.substring(0, 23);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
             */
            /* JADX WARN: Code restructure failed: missing block: B:71:0x0187, code lost:
            
                r0 = new java.lang.StringBuilder();
                r4 = "onReceive() called with action = [" + r3 + ']';
             */
            /* JADX WARN: Code restructure failed: missing block: B:72:0x019e, code lost:
            
                if (r4 != null) goto L68;
             */
            /* JADX WARN: Code restructure failed: missing block: B:73:0x01a0, code lost:
            
                r4 = "null ";
             */
            /* JADX WARN: Code restructure failed: missing block: B:74:0x01a2, code lost:
            
                r0.append(r4);
                r0.append(' ');
                r0.append("");
                android.util.Log.println(3, r8, r0.toString());
             */
            /* JADX WARN: Code restructure failed: missing block: B:76:0x015a, code lost:
            
                r8 = r0;
             */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onReceive(Context context, Intent intent) {
                String canonicalName;
                Object m1202constructorimpl;
                String str;
                String str2;
                String className;
                NFCReaderVM nFCReaderVM;
                NFCReaderVM nFCReaderVM2;
                NFCReaderVM nFCReaderVM3;
                NFCReaderVM nFCReaderVM4;
                PermissionHandler permissionHandler;
                String className2;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String action = intent.getAction();
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str3 = "N/A";
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
                String str4 = "onReceive() called with action = [" + action + ']';
                if (str4 == null) {
                    str4 = "null ";
                }
                sb.append(str4);
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
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                str = null;
                            } else {
                                str = null;
                                str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls2 = getClass();
                            str2 = cls2 != null ? cls2.getCanonicalName() : str;
                        }
                    }
                }
                str = null;
                if (action == null || !Intrinsics.areEqual(action, "android.nfc.action.ADAPTER_STATE_CHANGED")) {
                    return;
                }
                int intExtra = intent.getIntExtra("android.nfc.extra.ADAPTER_STATE", 1);
                if (intExtra == 1) {
                    nFCReaderVM = NFCReaderFragment.this.nfcVM;
                    if (nFCReaderVM == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
                        nFCReaderVM2 = str;
                    } else {
                        nFCReaderVM2 = nFCReaderVM;
                    }
                    nFCReaderVM2.nfcTurnedOff();
                    NFCReaderFragment.this.handlePermissionDialog();
                    return;
                }
                if (intExtra != 3) {
                    return;
                }
                nFCReaderVM3 = NFCReaderFragment.this.nfcVM;
                if (nFCReaderVM3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
                    nFCReaderVM4 = str;
                } else {
                    nFCReaderVM4 = nFCReaderVM3;
                }
                nFCReaderVM4.updateStatus(NFCUIFlowModel.NFCUIState.TurnOnNFC.INSTANCE, NFCUIFlowModel.NFCUIStatus.Success);
                permissionHandler = NFCReaderFragment.this.permissionHandler;
                permissionHandler.closeBottomSheet();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    private final HkFragmentNfcBinding getBinding() {
        return (HkFragmentNfcBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WorkflowUIState.NFCReader getNfcUIState() {
        return (WorkflowUIState.NFCReader) this.nfcUIState.getValue();
    }

    private final Map<String, String> getTextConfig() {
        return (Map) this.textConfig.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$0(NFCReaderFragment this$0, Boolean isGranted) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(isGranted, "isGranted");
        if (isGranted.booleanValue()) {
            this$0.initialiseNFC();
        } else {
            this$0.handlePermissionDialog();
        }
    }

    private final void loadErrorRetries() {
        this.errorRetryMap = MapsKt.mutableMapOf(TuplesKt.to(121, 0), TuplesKt.to(122, 0), TuplesKt.to(106, 0));
    }

    private final void initialiseNFC() {
        Object m1202constructorimpl;
        this.isModuleStarted = true;
        try {
            Result.Companion companion = Result.INSTANCE;
            NFCReaderFragment nFCReaderFragment = this;
            HVNFCAdapter hVNFCAdapter = new HVNFCAdapter();
            this.nfcAdapter = hVNFCAdapter;
            hVNFCAdapter.register(requireActivity(), getLifecycle());
            Object systemService = requireActivity().getSystemService(WorkflowModule.TYPE_NFC);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.nfc.NfcManager");
            this.nfcMgr = (NfcManager) systemService;
            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null && (m1205exceptionOrNullimpl instanceof NoClassDefFoundError)) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
            BaseActivity.finishWithResult$default((HKMainActivity) requireActivity, "error", null, 119, getString(R.string.hk_error_nfc_not_integrated), false, false, 50, null);
            return;
        }
        BaseFragment.initViewsOnlyIfNotRecreated$default(this, null, 1, null);
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    public void initViews() {
        loadErrorRetries();
        HkFragmentNfcBinding binding = getBinding();
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getNfcUIState().getShowBackButton() ? 0 : 8);
        binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCReaderFragment.initViews$lambda$11$lambda$3(NFCReaderFragment.this, view);
            }
        });
        binding.btnSkip.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCReaderFragment.initViews$lambda$11$lambda$5(NFCReaderFragment.this, view);
            }
        });
        binding.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCReaderFragment.initViews$lambda$11$lambda$6(NFCReaderFragment.this, view);
            }
        });
        LottieAnimationView animationPlaceholder = binding.animationPlaceholder;
        Intrinsics.checkNotNullExpressionValue(animationPlaceholder, "animationPlaceholder");
        loadAnimation(animationPlaceholder, HyperSnapBridgeKt.getUiConfigUtil().getNfcAnimation());
        String str = getTextConfig().get("nfcScreen_title");
        if (str != null) {
            if (str.length() > 0) {
                binding.titleText.setText(UIExtsKt.fromHTML(str));
            }
        }
        String str2 = getTextConfig().get("nfcScreen_desc");
        if (str2 != null) {
            if (str2.length() > 0) {
                binding.descText.setText(UIExtsKt.fromHTML(str2));
            }
        }
        String str3 = getTextConfig().get("nfcScreen_skip");
        if (str3 != null) {
            if (str3.length() > 0) {
                binding.btnSkip.setText(UIExtsKt.fromHTML(str3));
            }
        }
        String str4 = getTextConfig().get("nfcScreen_retry");
        if (str4 != null) {
            if (str4.length() > 0) {
                binding.btnRetry.setText(UIExtsKt.fromHTML(str4));
            }
        }
        loadUIConfig();
        loadListViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$11$lambda$3(NFCReaderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getNfcUIState().getShowInstruction()) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
            ActivityExtsKt.replaceContent$default((HKMainActivity) requireActivity, new NFCReaderInstructionFragment(), BundleKt.bundleOf(TuplesKt.to(NFCReaderInstructionFragment.ARG_KEY_NFC_READER_UI_STATE, this$0.getNfcUIState())), false, null, 0, 28, null);
            return;
        }
        this$0.requireActivity().onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$11$lambda$5(NFCReaderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.nfcStatus;
        if (!Intrinsics.areEqual(str, "error")) {
            str = null;
        }
        if (str == null) {
            str = "skipped";
        }
        this$0.nfcStatus = str;
        this$0.sendResult(new JSONObject());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$11$lambda$6(NFCReaderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showButtons(false);
        NFCReaderVM nFCReaderVM = this$0.nfcVM;
        if (nFCReaderVM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
            nFCReaderVM = null;
        }
        nFCReaderVM.retryFlow();
        this$0.startSkipButtonTimer();
    }

    private final void loadListViews() {
        NFCReaderVM nFCReaderVM = this.nfcVM;
        NFCReaderVM nFCReaderVM2 = null;
        if (nFCReaderVM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
            nFCReaderVM = null;
        }
        for (NFCUIFlowModel nFCUIFlowModel : nFCReaderVM.getUIStates()) {
            HkFragmentNfcBinding binding = getBinding();
            NFCUIStateView nFCUIStateView = new NFCUIStateView(requireActivity(), getTextConfig());
            NFCUIFlowModel.NFCUIState uiState = nFCUIFlowModel.getUiState();
            nFCUIStateView.setTag(uiState != null ? uiState.getNfcStepId() : null);
            binding.uiStatesLayout.addView(nFCUIStateView);
            nFCUIStateView.setUIModel(nFCUIFlowModel);
            this.uiStatesView = CollectionsKt.plus((Collection<? extends NFCUIStateView>) this.uiStatesView, nFCUIStateView);
        }
        NFCReaderVM nFCReaderVM3 = this.nfcVM;
        if (nFCReaderVM3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
        } else {
            nFCReaderVM2 = nFCReaderVM3;
        }
        nFCReaderVM2.updateFlow();
    }

    private final void cancelTimer() {
        TimerTask timerTask = this.timerTask;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.timer;
        if (timer != null) {
            if (timer == null) {
                Intrinsics.throwUninitializedPropertyAccessException(WorkflowModule.Properties.Section.Component.Type.TIMER);
                timer = null;
            }
            timer.purge();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        requireActivity().registerReceiver(this.mReceiver, new IntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED"));
        this.isRegistered = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.isRegistered) {
            requireActivity().unregisterReceiver(this.mReceiver);
            TimerTask timerTask = this.timerTask;
            if (timerTask != null) {
                timerTask.cancel();
            }
            Timer timer = this.timer;
            if (timer != null) {
                if (timer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(WorkflowModule.Properties.Section.Component.Type.TIMER);
                    timer = null;
                }
                timer.purge();
            }
        }
        OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.remove();
        }
        this.onBackPressedCallback = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        super.onStart();
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
        sb.append("onStart() called");
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
                    Log.println(3, str, "onStart() called ");
                }
            }
        }
        if (this.isModuleStarted) {
            return;
        }
        initialiseNFC();
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0127, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onResume() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        super.onResume();
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        NfcManager nfcManager = null;
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
        sb.append("onResume() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "onResume() called ");
                }
            }
        }
        HVNFCAdapter hVNFCAdapter = this.nfcAdapter;
        if (hVNFCAdapter != null) {
            if (hVNFCAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nfcAdapter");
                hVNFCAdapter = null;
            }
            if (hVNFCAdapter.isEnabled() || this.isPermissionDialogShown) {
                return;
            }
            NfcManager nfcManager2 = this.nfcMgr;
            if (nfcManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nfcMgr");
            } else {
                nfcManager = nfcManager2;
            }
            if (nfcManager.getDefaultAdapter() != null) {
                handlePermissionDialog();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0143, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0153, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0157, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0166, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0168, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0173, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0179, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x017c, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0183, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = "onViewCreated() called with: view = " + r18 + ", savedInstanceState = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x019d, code lost:
    
        if (r2 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x019f, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01a1, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0156, code lost:
    
        r8 = r0;
     */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
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
        String str4 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        if (this.onBackPressedCallback == null) {
            this.onBackPressedCallback = new OnBackPressedCallback() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$onViewCreated$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    String canonicalName2;
                    Object m1202constructorimpl2;
                    String className3;
                    String substringAfterLast$default;
                    WorkflowUIState.NFCReader nfcUIState;
                    MainVM mainVM;
                    WorkflowUIState.NFCReader nfcUIState2;
                    String className4;
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    String str5 = "N/A";
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher2.find()) {
                        canonicalName2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(canonicalName2);
                    sb2.append(" - ");
                    sb2.append("handleOnBackPressed called for NFCReaderInstructionFragment");
                    sb2.append(' ');
                    sb2.append("");
                    companion4.log(level2, sb2.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                    if (canonicalName3 != null) {
                                        str5 = canonicalName3;
                                    }
                                } else {
                                    str5 = substringAfterLast$default;
                                }
                                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher3.find()) {
                                    str5 = matcher3.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                }
                                if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str5, "handleOnBackPressed called for NFCReaderInstructionFragment ");
                            }
                        }
                    }
                    nfcUIState = NFCReaderFragment.this.getNfcUIState();
                    if (!nfcUIState.getShowInstruction()) {
                        mainVM = NFCReaderFragment.this.getMainVM();
                        mainVM.flowBack();
                        return;
                    }
                    FragmentActivity requireActivity = NFCReaderFragment.this.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
                    NFCReaderInstructionFragment nFCReaderInstructionFragment = new NFCReaderInstructionFragment();
                    nfcUIState2 = NFCReaderFragment.this.getNfcUIState();
                    ActivityExtsKt.replaceContent$default((HKMainActivity) requireActivity, nFCReaderInstructionFragment, BundleKt.bundleOf(TuplesKt.to(NFCReaderInstructionFragment.ARG_KEY_NFC_READER_UI_STATE, nfcUIState2)), false, null, 0, 28, null);
                }
            };
            OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            OnBackPressedCallback onBackPressedCallback = this.onBackPressedCallback;
            Intrinsics.checkNotNull(onBackPressedCallback);
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback);
        }
        NFCReaderVM nFCReaderVM = new NFCReaderVM();
        this.nfcVM = nFCReaderVM;
        nFCReaderVM.loadUIStates();
        observeUIState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishPermissionWithError() {
        this.nfcStatus = "error";
        this.errorMessage = getString(R.string.hk_error_nfc_permission_denied);
        this.errorCode = 106;
        sendResult$default(this, null, 1, null);
    }

    public final /* synthetic */ void parseData$hyperkyc_release(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        resetState();
        NFCParserStatus nFCParserStatus = new NFCParserStatus() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$parseData$nfcListener$1
            @Override // co.hyperverge.hvnfcsdk.listeners.NFCParserStatus
            public void onCardDisconnection() {
                NFCReaderFragment.this.cardDisconnected();
            }

            /* JADX WARN: Code restructure failed: missing block: B:53:0x0122, code lost:
            
                if (r0 == null) goto L52;
             */
            @Override // co.hyperverge.hvnfcsdk.listeners.NFCParserStatus
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onIDDetection() {
                String canonicalName;
                Object m1202constructorimpl;
                String canonicalName2;
                String className;
                NFCReaderVM nFCReaderVM;
                String className2;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str = "N/A";
                NFCReaderVM nFCReaderVM2 = null;
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
                sb.append("onIDDetection() called");
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
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls2 = getClass();
                                canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            }
                            str = canonicalName2;
                            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                            if (matcher2.find()) {
                                str = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                            }
                            if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str = str.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str, "onIDDetection() called ");
                        }
                    }
                }
                nFCReaderVM = NFCReaderFragment.this.nfcVM;
                if (nFCReaderVM == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
                } else {
                    nFCReaderVM2 = nFCReaderVM;
                }
                nFCReaderVM2.updateStatus(NFCUIFlowModel.NFCUIState.TapChipCard.INSTANCE, NFCUIFlowModel.NFCUIStatus.Success);
            }

            @Override // co.hyperverge.hvnfcsdk.listeners.NFCParserStatus
            public void onScanResult(JSONObject nfcData, String status, HVNFCError error) {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                int i;
                String str;
                String className2;
                Intrinsics.checkNotNullParameter(status, "status");
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str2 = "N/A";
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
                String str3 = "Scan completed with data [" + nfcData + "], status [" + status + ']';
                if (str3 == null) {
                    str3 = "null ";
                }
                sb.append(str3);
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
                                    str2 = canonicalName2;
                                }
                            } else {
                                str2 = substringAfterLast$default;
                            }
                            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                            if (matcher2.find()) {
                                str2 = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                            }
                            if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str2 = str2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String str4 = "Scan completed with data [" + nfcData + "], status [" + status + ']';
                            if (str4 == null) {
                                str4 = "null ";
                            }
                            sb2.append(str4);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str2, sb2.toString());
                        }
                    }
                }
                NFCReaderFragment.this.nfcStatus = status;
                if (error == null) {
                    NFCReaderFragment.this.sendResult(nfcData);
                    return;
                }
                NFCReaderFragment nFCReaderFragment = NFCReaderFragment.this;
                int errorCode = error.getErrorCode();
                if (errorCode != 44) {
                    if (errorCode == 46 || errorCode == 47) {
                        FragmentActivity requireActivity = nFCReaderFragment.requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
                        BaseActivity.finishWithResult$default((HKMainActivity) requireActivity, "error", null, 104, error.getErrorMessage(), false, false, 50, null);
                        return;
                    }
                    return;
                }
                nFCReaderFragment.nfcStatus = "error";
                nFCReaderFragment.errorMessage = nFCReaderFragment.getString(R.string.hk_error_authentication);
                nFCReaderFragment.errorCode = 121;
                i = nFCReaderFragment.errorCode;
                str = nFCReaderFragment.errorMessage;
                nFCReaderFragment.sendErrorEvent(i, str != null ? str : "");
                BuildersKt__Builders_commonKt.launch$default(nFCReaderFragment.getLifecycleScope(), null, null, new NFCReaderFragment$parseData$nfcListener$1$onScanResult$2$1(nFCReaderFragment, null), 3, null);
            }
        };
        HVNFCAdapter hVNFCAdapter = this.nfcAdapter;
        if (hVNFCAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcAdapter");
            hVNFCAdapter = null;
        }
        Map<String, String> authentication = getNfcUIState().getAuthentication();
        Intrinsics.checkNotNull(authentication);
        hVNFCAdapter.parseIntent(intent, authentication, nFCParserStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendErrorEvent(int errorCode, String errorMessage) {
        AnalyticsLogger.INSTANCE.logNFCModuleError$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("errorCode", Integer.valueOf(errorCode)), TuplesKt.to("errorMessage", errorMessage), TuplesKt.to("moduleId", getNfcUIState().getTag()), TuplesKt.to("moduleSubType", getNfcUIState().getSubType())));
    }

    private final void sendModuleEnded() {
        Pair[] pairArr = new Pair[6];
        pairArr[0] = TuplesKt.to("status", this.nfcStatus);
        pairArr[1] = TuplesKt.to("errorCode", Integer.valueOf(this.errorCode));
        pairArr[2] = TuplesKt.to("errorMessage", this.errorMessage);
        pairArr[3] = TuplesKt.to("moduleId", getNfcUIState().getTag());
        pairArr[4] = TuplesKt.to("moduleSubType", getNfcUIState().getSubType());
        NFCReaderVM nFCReaderVM = this.nfcVM;
        if (nFCReaderVM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
            nFCReaderVM = null;
        }
        pairArr[5] = TuplesKt.to("nfcLastStep", nFCReaderVM.currentStep());
        AnalyticsLogger.INSTANCE.logNFCModuleEnded$hyperkyc_release(MapsKt.mutableMapOf(pairArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cardDisconnected() {
        BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), null, null, new NFCReaderFragment$cardDisconnected$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processErrorState() {
        this.nfcStatus = "error";
        int i = this.errorCode;
        String str = this.errorMessage;
        if (str == null) {
            str = "";
        }
        sendErrorEvent(i, str);
        if (shouldRetry(this.errorCode)) {
            showButtons(true);
        } else {
            sendResult$default(this, null, 1, null);
        }
    }

    private final void resetState() {
        this.errorCode = -1;
        this.errorMessage = null;
        this.nfcStatus = null;
        showButtons(false);
        cancelTimer();
    }

    private final void showButtons(boolean shouldShow) {
        boolean z;
        HkFragmentNfcBinding binding = getBinding();
        MaterialButton btnSkip = binding.btnSkip;
        Intrinsics.checkNotNullExpressionValue(btnSkip, "btnSkip");
        MaterialButton materialButton = btnSkip;
        if (this.errorCode == 120) {
            z = shouldShow;
        } else {
            z = shouldShow && getNfcUIState().getShowSkipButton();
        }
        materialButton.setVisibility(z ? 0 : 8);
        MaterialButton btnRetry = binding.btnRetry;
        Intrinsics.checkNotNullExpressionValue(btnRetry, "btnRetry");
        MaterialButton materialButton2 = btnRetry;
        if (this.errorCode == 120) {
            shouldShow = false;
        }
        materialButton2.setVisibility(shouldShow ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void sendResult$default(NFCReaderFragment nFCReaderFragment, JSONObject jSONObject, int i, Object obj) {
        if ((i & 1) != 0) {
            jSONObject = null;
        }
        nFCReaderFragment.sendResult(jSONObject);
    }

    private final void verifyForIncompleteScan(JSONObject data) {
        boolean z;
        if (data != null) {
            Iterator<String> keys = data.keys();
            while (true) {
                z = true;
                boolean z2 = false;
                if (!keys.hasNext()) {
                    z = false;
                    break;
                }
                String value = data.optString(keys.next());
                Intrinsics.checkNotNullExpressionValue(value, "value");
                if (value.length() == 0) {
                    z2 = true;
                }
                if (z2) {
                    break;
                }
            }
            if (z) {
                this.errorCode = 125;
                String string = getString(R.string.hk_error_incomplete_scan);
                this.errorMessage = string;
                this.nfcStatus = "error";
                int i = this.errorCode;
                Intrinsics.checkNotNull(string);
                sendErrorEvent(i, string);
            }
        }
    }

    private final boolean shouldRetry(int errorCode) {
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
        boolean z = false;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "shouldRetry called with errorCode : [ " + errorCode + " ]";
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
                    String str3 = "shouldRetry called with errorCode : [ " + errorCode + " ]";
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
        Integer num = this.errorRetryMap.get(Integer.valueOf(errorCode));
        int intValue = (num != null ? num.intValue() : 0) + 1;
        if (errorCode == 106 ? intValue < 3 : !(errorCode == 122 ? intValue >= getNfcUIState().getRetryOnNFCError() : intValue > 1)) {
            z = true;
        }
        this.errorRetryMap.put(Integer.valueOf(errorCode), Integer.valueOf(intValue));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01f3, code lost:
    
        if (r10 != null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x02e7, code lost:
    
        if (r0 != null) goto L138;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x017a A[Catch: Exception -> 0x01be, TryCatch #0 {Exception -> 0x01be, blocks: (B:19:0x0176, B:21:0x017a, B:22:0x0181, B:24:0x0185, B:25:0x0188, B:28:0x018e, B:29:0x0192, B:31:0x01a2, B:32:0x01a6, B:34:0x01b2, B:35:0x01b9), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0185 A[Catch: Exception -> 0x01be, TryCatch #0 {Exception -> 0x01be, blocks: (B:19:0x0176, B:21:0x017a, B:22:0x0181, B:24:0x0185, B:25:0x0188, B:28:0x018e, B:29:0x0192, B:31:0x01a2, B:32:0x01a6, B:34:0x01b2, B:35:0x01b9), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x018e A[Catch: Exception -> 0x01be, TRY_ENTER, TryCatch #0 {Exception -> 0x01be, blocks: (B:19:0x0176, B:21:0x017a, B:22:0x0181, B:24:0x0185, B:25:0x0188, B:28:0x018e, B:29:0x0192, B:31:0x01a2, B:32:0x01a6, B:34:0x01b2, B:35:0x01b9), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01a2 A[Catch: Exception -> 0x01be, TryCatch #0 {Exception -> 0x01be, blocks: (B:19:0x0176, B:21:0x017a, B:22:0x0181, B:24:0x0185, B:25:0x0188, B:28:0x018e, B:29:0x0192, B:31:0x01a2, B:32:0x01a6, B:34:0x01b2, B:35:0x01b9), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01b2 A[Catch: Exception -> 0x01be, TryCatch #0 {Exception -> 0x01be, blocks: (B:19:0x0176, B:21:0x017a, B:22:0x0181, B:24:0x0185, B:25:0x0188, B:28:0x018e, B:29:0x0192, B:31:0x01a2, B:32:0x01a6, B:34:0x01b2, B:35:0x01b9), top: B:18:0x0176 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startSkipButtonTimer() {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String className2;
        String className3;
        TimerTask timerTask;
        Timer timer;
        Timer timer2;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
        sb.append("startSkipButtonTimer() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        try {
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
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher2.find()) {
                            canonicalName2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, canonicalName2, "startSkipButtonTimer() called ");
                    }
                    if (this.timer == null) {
                        this.timer = new Timer();
                    }
                    timerTask = this.timerTask;
                    if (timerTask != null) {
                        timerTask.cancel();
                    }
                    timer = this.timer;
                    if (timer == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(WorkflowModule.Properties.Section.Component.Type.TIMER);
                        timer = null;
                    }
                    timer.purge();
                    this.timerTask = new TimerTask() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$startSkipButtonTimer$3
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            NFCReaderFragment.this.cardDisconnected();
                        }
                    };
                    timer2 = this.timer;
                    if (timer2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(WorkflowModule.Properties.Section.Component.Type.TIMER);
                        timer2 = null;
                    }
                    TimerTask timerTask2 = this.timerTask;
                    Long nfcSkipDelay = getNfcUIState().getNfcSkipDelay();
                    timer2.schedule(timerTask2, nfcSkipDelay == null ? nfcSkipDelay.longValue() : 5000L);
                    return;
                }
            }
            if (this.timer == null) {
            }
            timerTask = this.timerTask;
            if (timerTask != null) {
            }
            timer = this.timer;
            if (timer == null) {
            }
            timer.purge();
            this.timerTask = new TimerTask() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$startSkipButtonTimer$3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    NFCReaderFragment.this.cardDisconnected();
                }
            };
            timer2 = this.timer;
            if (timer2 == null) {
            }
            TimerTask timerTask22 = this.timerTask;
            Long nfcSkipDelay2 = getNfcUIState().getNfcSkipDelay();
            timer2.schedule(timerTask22, nfcSkipDelay2 == null ? nfcSkipDelay2.longValue() : 5000L);
            return;
        } catch (Exception e) {
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb2 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                str2 = "Throwable().stackTrace";
            } else {
                str2 = "Throwable().stackTrace";
                str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls3 = getClass();
            String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
            str3 = canonicalName3 == null ? str : canonicalName3;
            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher3.find()) {
                str3 = matcher3.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb2.append(str3);
            sb2.append(" - ");
            String str6 = "Exception caught: " + e.getMessage();
            if (str6 == null) {
                str6 = "null ";
            }
            sb2.append(str6);
            sb2.append(' ');
            sb2.append("");
            companion4.log(level2, sb2.toString());
            if (CoreExtsKt.isRelease()) {
                return;
            }
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion6 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName2 = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str4 = null;
                    } else {
                        str4 = null;
                        str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                    str5 = canonicalName4 == null ? str : canonicalName4;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                    if (matcher4.find()) {
                        str5 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                    }
                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str5 = str5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb3 = new StringBuilder();
                    String str7 = "Exception caught: " + e.getMessage();
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb3.append(str7);
                    sb3.append(' ');
                    sb3.append("");
                    Log.println(3, str5, sb3.toString());
                    return;
                }
                return;
            }
            return;
        }
        str = "N/A";
    }

    private final void loadUIConfig() {
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
        sb.append("loadUIConfig() called");
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
                    Log.println(3, str, "loadUIConfig() called ");
                }
            }
        }
        HkFragmentNfcBinding binding = getBinding();
        HyperSnapBridgeKt.getUiConfigUtil().customiseTitleTextView(binding.titleText);
        HyperSnapBridgeKt.getUiConfigUtil().customiseDescriptionTextView(binding.descText);
        HyperSnapBridgeKt.getUiConfigUtil().customiseSecondaryButton((Button) binding.btnSkip);
        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(binding.btnRetry);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPermission() {
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
        sb.append("requestPermission() called");
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
                    Log.println(3, str, "requestPermission() called ");
                }
            }
        }
        this.requestPermissionLauncher.launch("android.permission.NFC");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handlePermissionDialog() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        sb.append("handlePermissionDialog() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "handlePermissionDialog() called ");
                }
            }
        }
        this.isPermissionDialogShown = true;
        if (shouldRetry(106)) {
            String string = getString(R.string.hk_error_nfc_permission_denied);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hk_error_nfc_permission_denied)");
            sendErrorEvent(106, string);
            PermissionHandler permissionHandler = this.permissionHandler;
            Map<String, Object> textConfigs = getNfcUIState().getTextConfigs();
            Map<String, Object> map = textConfigs instanceof Map ? textConfigs : null;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            permissionHandler.showPermissionBS("android.permission.NFC", map, requireActivity, new PermDialogCallback() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$handlePermissionDialog$2
                @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
                public void onActionClick() {
                    NFCReaderFragment.this.isPermissionDialogShown = false;
                    if (ActivityExtsKt.isFragmentAdded(NFCReaderFragment.this)) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(NFCReaderFragment.this.requireActivity(), "android.permission.NFC")) {
                            NFCReaderFragment.this.requestPermission();
                        } else {
                            NFCReaderFragment.this.startActivity(new Intent("android.settings.NFC_SETTINGS"));
                        }
                    }
                }

                @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
                public void onCancel() {
                    NFCReaderFragment.this.isPermissionDialogShown = false;
                    NFCReaderFragment.this.finishPermissionWithError();
                }
            }, getMainVM().shouldShowBranding(), false);
            return;
        }
        finishPermissionWithError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x014c, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void sendResult(JSONObject data) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String jSONObject;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        NFCReaderVM nFCReaderVM = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str2 = "";
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
        String str3 = "sendResult() called with date [" + data + "], status [" + this.nfcStatus + ']';
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
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
                    String str4 = "sendResult() called with date [" + data + "], status [" + this.nfcStatus + ']';
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        if (this.isCompleted) {
            return;
        }
        this.isCompleted = true;
        if (data != null && (jSONObject = data.toString()) != null) {
            str2 = jSONObject;
        }
        if (Intrinsics.areEqual(this.nfcStatus, "success")) {
            verifyForIncompleteScan(data);
        }
        HyperKycData.NFCResult nFCResult = new HyperKycData.NFCResult(getNfcUIState().getTag(), null, null, null, null, null, 0, null, 254, null);
        nFCResult.setNfcStatus$hyperkyc_release(this.nfcStatus);
        nFCResult.setNfcData$hyperkyc_release(str2);
        nFCResult.setNfcErrorMessage$hyperkyc_release(this.errorMessage);
        nFCResult.setNfcErrorCode$hyperkyc_release(Integer.valueOf(this.errorCode));
        NFCReaderVM nFCReaderVM2 = this.nfcVM;
        if (nFCReaderVM2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
        } else {
            nFCReaderVM = nFCReaderVM2;
        }
        nFCResult.setNfcLastStep$hyperkyc_release(nFCReaderVM.currentStep());
        sendModuleEnded();
        resetState();
        MainVM.updateNFCData$hyperkyc_release$default(getMainVM(), getNfcUIState().getTag(), nFCResult, false, 4, null);
        getMainVM().flowForward();
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void observeUIState() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        sb.append(" observeUIState() called ");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, " observeUIState() called  ");
                }
            }
        }
        BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), null, null, new NFCReaderFragment$observeUIState$2(this, null), 3, null);
    }
}
