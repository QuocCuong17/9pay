package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycStatus;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementBinding;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM;
import co.hyperverge.hyperkyc.utils.PermDialogCallback;
import co.hyperverge.hyperkyc.utils.PermissionHandler;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.components.camera.HVFacePreview;
import co.hyperverge.hypersnapsdk.components.camera.model.HVCamConfig;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.io.FilenameUtils;

/* compiled from: VideoStatementFragment.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 N2\u00020\u0001:\u0002NOB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010-\u001a\u00020\u0010H\u0016J\u0011\u0010.\u001a\u00020/H\u0082@ø\u0001\u0000¢\u0006\u0002\u00100J\b\u00101\u001a\u00020/H\u0002J\b\u00102\u001a\u00020/H\u0002J\b\u00103\u001a\u00020/H\u0016J\b\u00104\u001a\u00020/H\u0002J\b\u00105\u001a\u00020/H\u0016J\b\u00106\u001a\u00020/H\u0016J\u001a\u00107\u001a\u00020/2\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\r\u0010<\u001a\u00020/H\u0000¢\u0006\u0002\b=J\b\u0010>\u001a\u00020/H\u0002J\u0010\u0010?\u001a\u00020/2\u0006\u0010@\u001a\u00020\u001fH\u0002J\u001c\u0010A\u001a\u00020/2\u0006\u0010B\u001a\u00020C2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010EH\u0002J6\u0010F\u001a\u00020/2\b\u0010G\u001a\u0004\u0018\u00010\f2\b\u0010H\u001a\u0004\u0018\u00010\f2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020J2\b\u0010L\u001a\u0004\u0018\u00010MH\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\f0\f0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0016\u001a\u0004\b \u0010!R)\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020%\u0018\u00010$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u0016\u001a\u0004\b&\u0010'R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006P"}, d2 = {"Lco/hyperverge/hyperkyc/ui/VideoStatementFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "videoStatementUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "currentHelpText", "", "facePreview", "Lco/hyperverge/hypersnapsdk/components/camera/HVFacePreview;", "isModuleStarted", "", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "Lkotlin/Lazy;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "showBackButton", "getShowBackButton", "()Z", "showBackButton$delegate", "startTimestamp", "", "getStartTimestamp", "()J", "startTimestamp$delegate", "textConfigs", "", "", "getTextConfigs", "()Ljava/util/Map;", "textConfigs$delegate", WorkflowModule.Properties.Section.Component.Type.TIMER, "Landroid/os/CountDownTimer;", "videoStatementVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM;", "deferredInitViews", "evaluateResults", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finish", "handlePermissionDialog", "initViews", "initializeAndStartModule", "onDestroy", "onStart", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "requestPermission", "requestPermission$hyperkyc_release", "startStatement", "startTimer", "duration", "updateUIState", "videoStatementState", "Lco/hyperverge/hyperkyc/ui/VideoStatementFragment$VideoStatementState;", "failedCheck", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$Check;", "updateViews", "title", "desc", "titleColor", "", "descColor", "leftIcon", "Landroid/graphics/drawable/Drawable;", "Companion", "VideoStatementState", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoStatementFragment extends BaseFragment {
    public static final /* synthetic */ String ARG_KEY_START_TIMESTAMP = "startTimestamp";
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private String currentHelpText;
    private HVFacePreview facePreview;
    private boolean isModuleStarted;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;
    private final ActivityResultLauncher<String> requestPermissionLauncher;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;

    /* renamed from: startTimestamp$delegate, reason: from kotlin metadata */
    private final Lazy startTimestamp;

    /* renamed from: textConfigs$delegate, reason: from kotlin metadata */
    private final Lazy textConfigs;
    private CountDownTimer timer;
    private final WorkflowUIState.VideoStatement videoStatementUIState;
    private VideoStatementVM videoStatementVM;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(VideoStatementFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementBinding;", 0))};

    /* compiled from: VideoStatementFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/ui/VideoStatementFragment$VideoStatementState;", "", "(Ljava/lang/String;I)V", "STATEMENT", "VERIFY", "ERROR", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum VideoStatementState {
        STATEMENT,
        VERIFY,
        ERROR
    }

    /* compiled from: VideoStatementFragment.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[VideoStatementVM.Check.values().length];
            try {
                iArr[VideoStatementVM.Check.FACE_DETECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VideoStatementVM.Check.LIVENESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VideoStatementVM.Check.FACE_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[VideoStatementVM.Check.SPEECH_TO_TEXT_MATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[VideoStatementState.values().length];
            try {
                iArr2[VideoStatementState.VERIFY.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[VideoStatementState.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    public boolean deferredInitViews() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementFragment(WorkflowUIState.VideoStatement videoStatementUIState) {
        super(R.layout.hk_fragment_video_statement);
        Intrinsics.checkNotNullParameter(videoStatementUIState, "videoStatementUIState");
        this.videoStatementUIState = videoStatementUIState;
        final VideoStatementFragment videoStatementFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(videoStatementFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$special$$inlined$activityViewModels$default$2
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
        this.binding = FragmentViewBindingDelegateKt.viewBinding(videoStatementFragment, VideoStatementFragment$binding$2.INSTANCE);
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VideoStatementFragment.requestPermissionLauncher$lambda$0(VideoStatementFragment.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…nDialog()\n        }\n    }");
        this.requestPermissionLauncher = registerForActivityResult;
        this.startTimestamp = LazyKt.lazy(new Function0<Long>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$startTimestamp$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                Bundle arguments = VideoStatementFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("startTimestamp") : null;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
                return (Long) obj;
            }
        });
        this.textConfigs = LazyKt.lazy(new Function0<Map<String, ? extends Object>>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$textConfigs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends Object> invoke() {
                Bundle arguments = VideoStatementFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("textConfigs") : null;
                Map map = obj instanceof Map ? (Map) obj : null;
                if (map != null) {
                    return MapsKt.toMap(map);
                }
                return null;
            }
        });
        this.currentHelpText = "";
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = VideoStatementFragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("showBackButton")) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HkFragmentVideoStatementBinding getBinding() {
        return (HkFragmentVideoStatementBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$0(VideoStatementFragment this$0, Boolean isGranted) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(isGranted, "isGranted");
        if (isGranted.booleanValue()) {
            this$0.initializeAndStartModule();
        } else {
            this$0.handlePermissionDialog();
        }
    }

    private final long getStartTimestamp() {
        return ((Number) this.startTimestamp.getValue()).longValue();
    }

    private final Map<String, Object> getTextConfigs() {
        return (Map) this.textConfigs.getValue();
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0143, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0153, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0157, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0166, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0168, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0173, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0179, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x017c, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0183, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = "onViewCreated() called with: view = " + r18 + ", savedInstanceState = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x019d, code lost:
    
        if (r2 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x019f, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01a1, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0156, code lost:
    
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
        VideoStatementVM videoStatementVM = new VideoStatementVM(this.videoStatementUIState, getMainVM());
        this.videoStatementVM = videoStatementVM;
        videoStatementVM.setStartTimestamp(getStartTimestamp());
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
        if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.CAMERA") != 0) {
            requestPermission$hyperkyc_release();
        } else {
            initializeAndStartModule();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$7$lambda$6(VideoStatementFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed$hyperkyc_release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void updateUIState$default(VideoStatementFragment videoStatementFragment, VideoStatementState videoStatementState, VideoStatementVM.Check check, int i, Object obj) {
        if ((i & 2) != 0) {
            check = null;
        }
        videoStatementFragment.updateUIState(videoStatementState, check);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Type inference failed for: r11v10, types: [T] */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2, types: [T] */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r5v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v17, types: [T, java.lang.Object, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object evaluateResults(Continuation<? super Unit> continuation) {
        VideoStatementFragment$evaluateResults$1 videoStatementFragment$evaluateResults$1;
        Object obj;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        String className;
        String substringAfterLast$default;
        VideoStatementFragment videoStatementFragment;
        String className2;
        VideoStatementVM videoStatementVM;
        if (continuation instanceof VideoStatementFragment$evaluateResults$1) {
            videoStatementFragment$evaluateResults$1 = (VideoStatementFragment$evaluateResults$1) continuation;
            if ((videoStatementFragment$evaluateResults$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementFragment$evaluateResults$1.label -= Integer.MIN_VALUE;
                obj = videoStatementFragment$evaluateResults$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementFragment$evaluateResults$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r13 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        Class<?> cls = getClass();
                        canonicalName = cls != null ? cls.getCanonicalName() : 0;
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str = (String) objectRef.element;
                    } else {
                        str = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str);
                    sb.append(" - ");
                    sb.append("evaluateResults() called");
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
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls2 = getClass();
                                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                                    if (canonicalName2 != null) {
                                        r13 = canonicalName2;
                                    }
                                } else {
                                    r13 = substringAfterLast$default;
                                }
                                objectRef2.element = r13;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str2 = (String) objectRef2.element;
                                } else {
                                    str2 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str2, "evaluateResults() called ");
                            }
                        }
                    }
                    VideoStatementVM videoStatementVM2 = this.videoStatementVM;
                    if (videoStatementVM2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                        videoStatementVM2 = null;
                    }
                    videoStatementFragment$evaluateResults$1.L$0 = this;
                    videoStatementFragment$evaluateResults$1.label = 1;
                    obj = videoStatementVM2.evaluate(videoStatementFragment$evaluateResults$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    videoStatementFragment = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    videoStatementFragment = (VideoStatementFragment) videoStatementFragment$evaluateResults$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (((Boolean) obj).booleanValue()) {
                    BuildersKt__Builders_commonKt.launch$default(videoStatementFragment.getLifecycleScope(), Dispatchers.getMain(), null, new VideoStatementFragment$evaluateResults$3(videoStatementFragment, null), 2, null);
                } else {
                    VideoStatementVM videoStatementVM3 = videoStatementFragment.videoStatementVM;
                    if (videoStatementVM3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                        videoStatementVM3 = null;
                    }
                    if (videoStatementVM3.checkIsEndOfStatements()) {
                        videoStatementFragment.finish();
                        return Unit.INSTANCE;
                    }
                    VideoStatementVM videoStatementVM4 = videoStatementFragment.videoStatementVM;
                    if (videoStatementVM4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                        videoStatementVM = null;
                    } else {
                        videoStatementVM = videoStatementVM4;
                    }
                    videoStatementVM.moveToNextStatement();
                    videoStatementFragment.startStatement();
                }
                return Unit.INSTANCE;
            }
        }
        videoStatementFragment$evaluateResults$1 = new VideoStatementFragment$evaluateResults$1(this, continuation);
        obj = videoStatementFragment$evaluateResults$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementFragment$evaluateResults$1.label;
        if (i != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finish() {
        BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), null, null, new VideoStatementFragment$finish$1(this, null), 3, null);
    }

    public final void requestPermission$hyperkyc_release() {
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
        this.requestPermissionLauncher.launch("android.permission.CAMERA");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initializeAndStartModule() {
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
        sb.append("initializeAndStartModule() called");
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
                    Log.println(3, str, "initializeAndStartModule() called ");
                }
            }
        }
        this.isModuleStarted = true;
        BaseFragment.initViewsOnlyIfNotRecreated$default(this, null, 1, null);
        startStatement();
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initViews() {
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
                    Log.println(3, str, "initViews() called ");
                }
            }
        }
        HkFragmentVideoStatementBinding binding = getBinding();
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getShowBackButton() ? 0 : 8);
        binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoStatementFragment.initViews$lambda$7$lambda$6(VideoStatementFragment.this, view);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.facePreview = new HVFacePreview(requireContext, new HVCamConfig(false, false, false, 0.0f, 0.0f, false, false, UIUtils.dpToPx(requireContext(), 150.0f), 127, null), getLifecycleScope());
        LinearLayout linearLayout = binding.camView;
        HVFacePreview hVFacePreview = this.facePreview;
        if (hVFacePreview == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facePreview");
            hVFacePreview = null;
        }
        linearLayout.addView(hVFacePreview);
        VideoStatementVM videoStatementVM = this.videoStatementVM;
        if (videoStatementVM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
            videoStatementVM = null;
        }
        HVFacePreview hVFacePreview2 = this.facePreview;
        if (hVFacePreview2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facePreview");
            hVFacePreview2 = null;
        }
        videoStatementVM.observeUIState(hVFacePreview2);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
        LinearLayout linearLayout2 = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "includeBranding.llBrandingRoot");
        linearLayout2.setVisibility(getMainVM().shouldShowBranding() ? 0 : 8);
        HSUIConfig uiConfigData = getMainVM().getUiConfigData();
        loadBackground$hyperkyc_release(uiConfigData != null ? uiConfigData.getBackgroundImageUrl() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startStatement() {
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
        VideoStatementVM videoStatementVM = null;
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
        sb.append("startStatement() called");
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
                    Log.println(3, str, "startStatement() called ");
                }
            }
        }
        VideoStatementVM videoStatementVM2 = this.videoStatementVM;
        if (videoStatementVM2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
            videoStatementVM2 = null;
        }
        if (videoStatementVM2.checkIsEndOfStatements()) {
            BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), Dispatchers.getMain(), null, new VideoStatementFragment$startStatement$2(this, null), 2, null);
            return;
        }
        VideoStatementVM videoStatementVM3 = this.videoStatementVM;
        if (videoStatementVM3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
            videoStatementVM3 = null;
        }
        if (!videoStatementVM3.checkIfStatementEnabled()) {
            VideoStatementVM videoStatementVM4 = this.videoStatementVM;
            if (videoStatementVM4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
            } else {
                videoStatementVM = videoStatementVM4;
            }
            videoStatementVM.moveToNextStatement();
            startStatement();
            return;
        }
        VideoStatementVM videoStatementVM5 = this.videoStatementVM;
        if (videoStatementVM5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
            videoStatementVM5 = null;
        }
        if (videoStatementVM5.getCurrentStatement() != null) {
            BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), Dispatchers.getMain(), null, new VideoStatementFragment$startStatement$3(this, null), 2, null);
        } else {
            BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), null, null, new VideoStatementFragment$startStatement$4(this, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0131, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0141, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0145, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0154, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0156, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0161, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0165, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0168, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x016f, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "startTimer() called with: duration = " + r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0183, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0185, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0187, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0144, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startTimer(final long duration) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
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
        String str4 = "startTimer() called with: duration = " + duration;
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
        CountDownTimer countDownTimer = new CountDownTimer(duration) { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$startTimer$2
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                BuildersKt__Builders_commonKt.launch$default(this.getLifecycleScope(), Dispatchers.getMain(), null, new VideoStatementFragment$startTimer$2$onTick$1(this, millisUntilFinished / 1000, null), 2, null);
            }
        };
        this.timer = countDownTimer;
        countDownTimer.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x013c, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x014c, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0150, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher((java.lang.CharSequence) r8);
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x015f, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0161, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0168, code lost:
    
        r0 = r8.length();
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x016c, code lost:
    
        if (r0 <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x016e, code lost:
    
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0172, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0175, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x017c, code lost:
    
        r0 = new java.lang.StringBuilder();
        r3 = "updateUIState() called with: videoStatementState = " + r18 + ", failedCheck = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0196, code lost:
    
        if (r3 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0198, code lost:
    
        r3 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x019a, code lost:
    
        r0.append(r3);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x014f, code lost:
    
        r8 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0354  */
    /* JADX WARN: Type inference failed for: r0v109 */
    /* JADX WARN: Type inference failed for: r0v110 */
    /* JADX WARN: Type inference failed for: r0v111 */
    /* JADX WARN: Type inference failed for: r0v93 */
    /* JADX WARN: Type inference failed for: r0v94, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v95 */
    /* JADX WARN: Type inference failed for: r0v96, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v97 */
    /* JADX WARN: Type inference failed for: r0v98, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13, types: [co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r2v32, types: [co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM] */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r3v35, types: [co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM] */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateUIState(VideoStatementState videoStatementState, VideoStatementVM.Check failedCheck) {
        String canonicalName;
        Object m1202constructorimpl;
        Drawable drawable;
        Object obj;
        String className;
        Object obj2;
        Object obj3;
        int color;
        int color2;
        int i;
        Drawable drawable2;
        String str;
        int i2;
        String str2;
        Object obj4;
        ?? r0;
        ?? r11;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        Object obj5 = "N/A";
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
        String str3 = "updateUIState() called with: videoStatementState = " + videoStatementState + ", failedCheck = " + failedCheck;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        drawable = null;
                    } else {
                        drawable = null;
                        obj = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    obj = cls2 != null ? cls2.getCanonicalName() : drawable;
                }
            }
        }
        drawable = null;
        int i3 = WhenMappings.$EnumSwitchMapping$1[videoStatementState.ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                int i4 = failedCheck == null ? -1 : WhenMappings.$EnumSwitchMapping$0[failedCheck.ordinal()];
                if (i4 == 1) {
                    Map<String, Object> textConfigs = getTextConfigs();
                    Object obj6 = textConfigs != null ? textConfigs.get("videoStatementFaceDetectionFailureHelpText") : drawable;
                    obj2 = obj6 instanceof String ? (String) obj6 : drawable;
                    Map<String, Object> textConfigs2 = getTextConfigs();
                    Object obj7 = textConfigs2 != null ? textConfigs2.get("videoStatementFaceDetectionFailureText") : drawable;
                    if (obj7 instanceof String) {
                        obj4 = (String) obj7;
                        obj3 = obj4;
                        if (obj2 == null) {
                        }
                        if (obj3 == null) {
                        }
                        color = ContextCompat.getColor(requireContext(), R.color.error_red);
                        color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                        drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                    }
                    obj4 = drawable;
                    obj3 = obj4;
                    if (obj2 == null) {
                    }
                    if (obj3 == null) {
                    }
                    color = ContextCompat.getColor(requireContext(), R.color.error_red);
                    color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                    drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                } else if (i4 == 2) {
                    Map<String, Object> textConfigs3 = getTextConfigs();
                    Object obj8 = textConfigs3 != null ? textConfigs3.get("videoStatementLivenessFailureHelpText") : drawable;
                    obj2 = obj8 instanceof String ? (String) obj8 : drawable;
                    Map<String, Object> textConfigs4 = getTextConfigs();
                    Object obj9 = textConfigs4 != null ? textConfigs4.get("videoStatementLivenessFailureText") : drawable;
                    if (obj9 instanceof String) {
                        obj4 = (String) obj9;
                        obj3 = obj4;
                        if (obj2 == null) {
                        }
                        if (obj3 == null) {
                        }
                        color = ContextCompat.getColor(requireContext(), R.color.error_red);
                        color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                        drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                    }
                    obj4 = drawable;
                    obj3 = obj4;
                    if (obj2 == null) {
                    }
                    if (obj3 == null) {
                    }
                    color = ContextCompat.getColor(requireContext(), R.color.error_red);
                    color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                    drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                } else if (i4 == 3) {
                    Map<String, Object> textConfigs5 = getTextConfigs();
                    Object obj10 = textConfigs5 != null ? textConfigs5.get("videoStatementFaceMatchFailureHelpText") : drawable;
                    obj2 = obj10 instanceof String ? (String) obj10 : drawable;
                    Map<String, Object> textConfigs6 = getTextConfigs();
                    Object obj11 = textConfigs6 != null ? textConfigs6.get("videoStatementFaceMatchFailureText") : drawable;
                    if (obj11 instanceof String) {
                        obj4 = (String) obj11;
                        obj3 = obj4;
                        if (obj2 == null) {
                        }
                        if (obj3 == null) {
                        }
                        color = ContextCompat.getColor(requireContext(), R.color.error_red);
                        color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                        drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                    }
                    obj4 = drawable;
                    obj3 = obj4;
                    if (obj2 == null) {
                    }
                    if (obj3 == null) {
                    }
                    color = ContextCompat.getColor(requireContext(), R.color.error_red);
                    color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                    drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                } else if (i4 == 4) {
                    Map<String, Object> textConfigs7 = getTextConfigs();
                    Object obj12 = textConfigs7 != null ? textConfigs7.get("videoStatementSpeechToTextFailureHelpText") : drawable;
                    obj2 = obj12 instanceof String ? (String) obj12 : drawable;
                    Map<String, Object> textConfigs8 = getTextConfigs();
                    Object obj13 = textConfigs8 != null ? textConfigs8.get("videoStatementSpeechToTextFailureText") : drawable;
                    if (obj13 instanceof String) {
                        obj4 = (String) obj13;
                        obj3 = obj4;
                        if (obj2 == null) {
                        }
                        if (obj3 == null) {
                        }
                        color = ContextCompat.getColor(requireContext(), R.color.error_red);
                        color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                        drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                    }
                    obj4 = drawable;
                    obj3 = obj4;
                    if (obj2 == null) {
                    }
                    if (obj3 == null) {
                    }
                    color = ContextCompat.getColor(requireContext(), R.color.error_red);
                    color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                    drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                } else {
                    obj2 = requireActivity().getString(R.string.hk_vs_error_title);
                    obj3 = requireActivity().getString(R.string.hk_vs_error_desc);
                    if (obj2 == null) {
                        obj2 = requireActivity().getString(R.string.hk_vs_error_title);
                        Intrinsics.checkNotNullExpressionValue(obj2, "requireActivity().getStr…string.hk_vs_error_title)");
                    }
                    if (obj3 == null) {
                        obj3 = requireActivity().getString(R.string.hk_vs_error_desc);
                        Intrinsics.checkNotNullExpressionValue(obj3, "requireActivity().getStr….string.hk_vs_error_desc)");
                    }
                    color = ContextCompat.getColor(requireContext(), R.color.error_red);
                    color2 = ContextCompat.getColor(requireContext(), R.color.error_red);
                    drawable = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_unhappy_face);
                }
                updateViews(str2, str, i2, i, drawable2);
            }
            Map<String, Object> textConfigs9 = getTextConfigs();
            Object obj14 = textConfigs9 != null ? textConfigs9.get("statements") : drawable;
            ?? r02 = obj14 instanceof Map ? (Map) obj14 : drawable;
            if (r02 != 0) {
                VideoStatementVM videoStatementVM = this.videoStatementVM;
                ?? r2 = videoStatementVM;
                if (videoStatementVM == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                    r2 = drawable;
                }
                r0 = (Map) r02.get(r2.getCurrentStatementId());
            } else {
                r0 = drawable;
            }
            if (r0 == 0 || (str2 = (String) r0.get("help")) == null) {
                str2 = requireActivity().getString(R.string.hk_vs_title);
                Intrinsics.checkNotNullExpressionValue(str2, "requireActivity().getString(R.string.hk_vs_title)");
            }
            VideoStatementVM videoStatementVM2 = this.videoStatementVM;
            ?? r3 = videoStatementVM2;
            if (videoStatementVM2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                r3 = drawable;
            }
            String injectValuesFromDataMap = r3.injectValuesFromDataMap(r0 != 0 ? (String) r0.get("stmt") : drawable);
            if (injectValuesFromDataMap == null) {
                injectValuesFromDataMap = requireActivity().getString(R.string.hk_vs_desc);
                Intrinsics.checkNotNullExpressionValue(injectValuesFromDataMap, "requireActivity().getString(R.string.hk_vs_desc)");
            }
            VideoStatementVM videoStatementVM3 = this.videoStatementVM;
            if (videoStatementVM3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                r11 = drawable;
            } else {
                r11 = videoStatementVM3;
            }
            r11.setCurrentAnswerText(injectValuesFromDataMap);
            this.currentHelpText = str2;
            int color3 = ContextCompat.getColor(requireContext(), R.color.hv_primary);
            i = ContextCompat.getColor(requireContext(), R.color.title_text_color);
            drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.hk_ic_speak);
            str = injectValuesFromDataMap;
            i2 = color3;
            updateViews(str2, str, i2, i, drawable2);
        }
        Map<String, Object> textConfigs10 = getTextConfigs();
        Object obj15 = textConfigs10 != null ? textConfigs10.get("videoStatementVerifyingHelpText") : drawable;
        obj2 = obj15 instanceof String ? (String) obj15 : drawable;
        if (obj2 == null) {
            obj2 = getString(R.string.hk_vs_verify_title);
            Intrinsics.checkNotNullExpressionValue(obj2, "getString(R.string.hk_vs_verify_title)");
        }
        Map<String, Object> textConfigs11 = getTextConfigs();
        Object obj16 = textConfigs11 != null ? textConfigs11.get("videoStatementVerifyingFeedback") : drawable;
        obj3 = obj16 instanceof String ? (String) obj16 : drawable;
        if (obj3 == null) {
            obj3 = getString(R.string.hk_vs_verify_desc);
            Intrinsics.checkNotNullExpressionValue(obj3, "getString(R.string.hk_vs_verify_desc)");
        }
        color = ContextCompat.getColor(requireContext(), R.color.hv_primary);
        color2 = ContextCompat.getColor(requireContext(), R.color.title_text_color);
        i = color2;
        drawable2 = drawable;
        str = obj3;
        i2 = color;
        str2 = obj2;
        updateViews(str2, str, i2, i, drawable2);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateViews(String title, String desc, int titleColor, int descColor, Drawable leftIcon) {
        String canonicalName;
        Object m1202constructorimpl;
        int i;
        Drawable drawable;
        String str;
        boolean z;
        String className;
        String str2;
        boolean z2;
        String str3;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String str4 = "updateViews() called with: title = " + title + ", desc = " + desc + ", titleColor = " + titleColor + ", descColor = " + descColor + ", leftIcon = " + leftIcon;
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
                if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    i = titleColor;
                    drawable = leftIcon;
                    z = false;
                    HkFragmentVideoStatementBinding binding = getBinding();
                    binding.tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                    binding.tvTitle.setTextColor(i);
                    binding.tvDesc.setTextColor(descColor);
                    str2 = title;
                    z2 = true;
                    if (!((str2 != null || str2.length() == 0) ? true : z)) {
                    }
                    str3 = desc;
                    if (str3 != null) {
                        z2 = z;
                    }
                    if (z2) {
                    }
                } else {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        z = false;
                    } else {
                        z = false;
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("updateViews() called with: title = ");
                    sb3.append(title);
                    sb3.append(", desc = ");
                    sb3.append(desc);
                    sb3.append(", titleColor = ");
                    i = titleColor;
                    sb3.append(i);
                    sb3.append(", descColor = ");
                    sb3.append(descColor);
                    sb3.append(", leftIcon = ");
                    drawable = leftIcon;
                    sb3.append(drawable);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                    HkFragmentVideoStatementBinding binding2 = getBinding();
                    binding2.tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                    binding2.tvTitle.setTextColor(i);
                    binding2.tvDesc.setTextColor(descColor);
                    str2 = title;
                    z2 = true;
                    if (!((str2 != null || str2.length() == 0) ? true : z)) {
                        binding2.tvTitle.setText(UIExtsKt.fromHTML(title));
                    }
                    str3 = desc;
                    if (str3 != null && str3.length() != 0) {
                        z2 = z;
                    }
                    if (z2) {
                        return;
                    }
                    binding2.tvDesc.setText(UIExtsKt.fromHTML(desc));
                    return;
                }
            }
        }
        i = titleColor;
        drawable = leftIcon;
        z = false;
        HkFragmentVideoStatementBinding binding22 = getBinding();
        binding22.tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        binding22.tvTitle.setTextColor(i);
        binding22.tvDesc.setTextColor(descColor);
        str2 = title;
        z2 = true;
        if (!((str2 != null || str2.length() == 0) ? true : z)) {
        }
        str3 = desc;
        if (str3 != null) {
        }
        if (z2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handlePermissionDialog() {
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
        PermissionHandler permissionHandler = new PermissionHandler();
        Map<String, Object> map = getMainVM().getTextConfigData().get("camera_permission");
        Map<String, Object> map2 = map instanceof Map ? map : null;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        PermissionHandler.showPermissionBS$default(permissionHandler, "android.permission.CAMERA", map2, requireActivity, new PermDialogCallback() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementFragment$handlePermissionDialog$2
            @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
            public void onActionClick() {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(VideoStatementFragment.this.requireActivity(), "android.permission.CAMERA")) {
                    VideoStatementFragment.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + VideoStatementFragment.this.requireActivity().getApplicationContext().getPackageName())));
                    return;
                }
                VideoStatementFragment.this.requestPermission$hyperkyc_release();
            }

            @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
            public void onCancel() {
                FragmentActivity activity = VideoStatementFragment.this.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
                BaseActivity.finishWithResult$default((HKMainActivity) activity, HyperKycStatus.AUTO_DECLINED, null, 106, "Camera permission denied", false, false, 50, null);
            }
        }, getMainVM().shouldShowBranding(), false, 32, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onDestroy() {
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
        sb.append("onDestroy() called");
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
                    Log.println(3, str, "onDestroy() called ");
                }
            }
        }
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            if (countDownTimer == null) {
                Intrinsics.throwUninitializedPropertyAccessException(WorkflowModule.Properties.Section.Component.Type.TIMER);
                countDownTimer = null;
            }
            countDownTimer.cancel();
        }
        if (this.facePreview != null) {
            BuildersKt__BuildersKt.runBlocking$default(null, new VideoStatementFragment$onDestroy$4(this, null), 1, null);
        }
        super.onDestroy();
    }
}
