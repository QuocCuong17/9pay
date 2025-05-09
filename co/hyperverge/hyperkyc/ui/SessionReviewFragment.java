package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.databinding.HkFragmentSessionReviewBinding;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.io.FilenameUtils;

/* compiled from: SessionReviewFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010$\u001a\u00020!H\u0016J\u001a\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020!H\u0002J\b\u0010+\u001a\u00020!H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019R'\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lco/hyperverge/hyperkyc/ui/SessionReviewFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "uiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentSessionReviewBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentSessionReviewBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "filePath", "", "getFilePath", "()Ljava/lang/String;", "filePath$delegate", "Lkotlin/Lazy;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "showBackButton", "", "getShowBackButton", "()Z", "showBackButton$delegate", "textConfigs", "", "getTextConfigs", "()Ljava/util/Map;", "textConfigs$delegate", "initVideoPlayer", "", "videoView", "Landroid/widget/VideoView;", "initViews", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "proceedToUpload", "retry", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SessionReviewFragment extends BaseFragment {
    public static final /* synthetic */ String ARG_KEY_FILE_PATH = "filePath";
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_KEY_UPLOAD_URL = "uploadUrl";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;

    /* renamed from: filePath$delegate, reason: from kotlin metadata */
    private final Lazy filePath;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;

    /* renamed from: textConfigs$delegate, reason: from kotlin metadata */
    private final Lazy textConfigs;
    private final WorkflowUIState uiState;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SessionReviewFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentSessionReviewBinding;", 0))};

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionReviewFragment(WorkflowUIState uiState) {
        super(R.layout.hk_fragment_session_review);
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        this.uiState = uiState;
        final SessionReviewFragment sessionReviewFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(sessionReviewFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$special$$inlined$activityViewModels$default$2
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
        this.binding = FragmentViewBindingDelegateKt.viewBinding(sessionReviewFragment, SessionReviewFragment$binding$2.INSTANCE);
        this.filePath = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$filePath$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = SessionReviewFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("filePath") : null;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        });
        this.textConfigs = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$textConfigs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                Bundle arguments = SessionReviewFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("textConfigs") : null;
                Map map = obj instanceof Map ? (Map) obj : null;
                Map<String, ? extends String> map2 = map != null ? MapsKt.toMap(map) : null;
                Intrinsics.checkNotNull(map2);
                return map2;
            }
        });
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = SessionReviewFragment.this.getArguments();
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

    private final HkFragmentSessionReviewBinding getBinding() {
        return (HkFragmentSessionReviewBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFilePath() {
        return (String) this.filePath.getValue();
    }

    private final Map<String, String> getTextConfigs() {
        return (Map) this.textConfigs.getValue();
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
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
        String str2 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
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
                String str3 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
                if (str3 == null) {
                    str3 = "null ";
                }
                sb2.append(str3);
                sb2.append(' ');
                sb2.append("");
                Log.println(2, str, sb2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$9$lambda$2(SessionReviewFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.retry();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$9$lambda$3(SessionReviewFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed$hyperkyc_release();
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x024b, code lost:
    
        if (r8 != null) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0322, code lost:
    
        if (r0 != null) goto L125;
     */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0218  */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initViews() {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        int i;
        String str4;
        String str5;
        Class<?> cls;
        String className2;
        Class<?> cls2;
        String className3;
        String nullIfBlank;
        String nullIfBlank2;
        String nullIfBlank3;
        String nullIfBlank4;
        HSUIConfig uiConfigData;
        SessionReviewFragment sessionReviewFragment;
        String str6;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls3 = getClass();
            canonicalName = cls3 != null ? cls3.getCanonicalName() : null;
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
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls4 = getClass();
                        canonicalName2 = cls4 != null ? cls4.getCanonicalName() : null;
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
                    Log.println(3, canonicalName2, "initViews() called ");
                }
                HkFragmentSessionReviewBinding binding = getBinding();
                ImageView progressSpinnerImageView = binding.progressSpinnerImageView;
                Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView, "progressSpinnerImageView");
                ViewExtsKt.startAnimation(progressSpinnerImageView);
                if (getMainVM().getSessionRecordingReAttemptsLeft() > 0) {
                    getMainVM().setSessionRecordingReAttemptsLeft$hyperkyc_release(-1);
                    ViewGroup.LayoutParams layoutParams = binding.btnRetry.getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
                    binding.root.removeView(binding.btnRetry);
                    binding.btnConfirm.setLayoutParams((ConstraintLayout.LayoutParams) layoutParams);
                } else {
                    binding.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            SessionReviewFragment.initViews$lambda$9$lambda$2(SessionReviewFragment.this, view);
                        }
                    });
                }
                ImageView imageView = binding.hkLayoutTopBar.ivBack;
                Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
                imageView.setVisibility(!getShowBackButton() ? 0 : 8);
                binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SessionReviewFragment.initViews$lambda$9$lambda$3(SessionReviewFragment.this, view);
                    }
                });
                if (!HVSessionRecorder.INSTANCE.getInstance().getIsRecordingStarted()) {
                    BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), Dispatchers.getIO(), null, new SessionReviewFragment$initViews$2$3(this, binding, HVSessionRecorder.INSTANCE.getInstance().getShouldUpload(), null), 2, null);
                    i = 0;
                } else {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
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
                    String canonicalName3 = (binding == null || (cls2 = binding.getClass()) == null) ? null : cls2.getCanonicalName();
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
                    sb2.append("Session Recording not started");
                    sb2.append(' ');
                    sb2.append("");
                    companion4.log(level2, sb2.toString());
                    CoreExtsKt.isRelease();
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
                            str5 = (binding == null || (cls = binding.getClass()) == null) ? str4 : cls.getCanonicalName();
                            if (str5 == null) {
                                str5 = str;
                            }
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher4.find()) {
                                str5 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                            }
                            if (str5.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                i = 0;
                            } else {
                                i = 0;
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(6, str5, "Session Recording not started ");
                            nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_title"));
                            if (nullIfBlank != null) {
                                binding.tvTitle.setText(UIExtsKt.fromHTML(nullIfBlank));
                            }
                            nullIfBlank2 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_desc"));
                            if (nullIfBlank2 != null) {
                                binding.tvDesc.setText(UIExtsKt.fromHTML(nullIfBlank2));
                            }
                            nullIfBlank3 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_button"));
                            if (nullIfBlank3 != null) {
                                binding.btnConfirm.setText(UIExtsKt.fromHTML(nullIfBlank3));
                            }
                            nullIfBlank4 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_retry_button"));
                            if (nullIfBlank4 != null) {
                                binding.btnConfirm.setText(UIExtsKt.fromHTML(nullIfBlank4));
                            }
                            HyperSnapUIConfigUtil.getInstance().customiseSecondaryButton((Button) binding.btnRetry);
                            HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(binding.tvTitle);
                            HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(binding.tvDesc);
                            HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(binding.btnConfirm);
                            HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
                            HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
                            LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
                            Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
                            linearLayout.setVisibility(getMainVM().shouldShowBranding() ? i : 8);
                            uiConfigData = getMainVM().getUiConfigData();
                            if (uiConfigData != null) {
                                str6 = uiConfigData.getBackgroundImageUrl();
                                sessionReviewFragment = this;
                            } else {
                                sessionReviewFragment = this;
                                str6 = str4;
                            }
                            sessionReviewFragment.loadBackground$hyperkyc_release(str6);
                        }
                    }
                    i = 0;
                }
                str4 = null;
                nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_title"));
                if (nullIfBlank != null) {
                }
                nullIfBlank2 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_desc"));
                if (nullIfBlank2 != null) {
                }
                nullIfBlank3 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_button"));
                if (nullIfBlank3 != null) {
                }
                nullIfBlank4 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_retry_button"));
                if (nullIfBlank4 != null) {
                }
                HyperSnapUIConfigUtil.getInstance().customiseSecondaryButton((Button) binding.btnRetry);
                HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(binding.tvTitle);
                HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(binding.tvDesc);
                HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(binding.btnConfirm);
                HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
                HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
                LinearLayout linearLayout2 = binding.includeBranding.llBrandingRoot;
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "includeBranding.llBrandingRoot");
                linearLayout2.setVisibility(getMainVM().shouldShowBranding() ? i : 8);
                uiConfigData = getMainVM().getUiConfigData();
                if (uiConfigData != null) {
                }
                sessionReviewFragment.loadBackground$hyperkyc_release(str6);
            }
        }
        str = "N/A";
        HkFragmentSessionReviewBinding binding2 = getBinding();
        ImageView progressSpinnerImageView2 = binding2.progressSpinnerImageView;
        Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView2, "progressSpinnerImageView");
        ViewExtsKt.startAnimation(progressSpinnerImageView2);
        if (getMainVM().getSessionRecordingReAttemptsLeft() > 0) {
        }
        ImageView imageView2 = binding2.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView2, "hkLayoutTopBar.ivBack");
        imageView2.setVisibility(!getShowBackButton() ? 0 : 8);
        binding2.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SessionReviewFragment.initViews$lambda$9$lambda$3(SessionReviewFragment.this, view);
            }
        });
        if (!HVSessionRecorder.INSTANCE.getInstance().getIsRecordingStarted()) {
        }
        str4 = null;
        nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_title"));
        if (nullIfBlank != null) {
        }
        nullIfBlank2 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_desc"));
        if (nullIfBlank2 != null) {
        }
        nullIfBlank3 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_button"));
        if (nullIfBlank3 != null) {
        }
        nullIfBlank4 = CoreExtsKt.nullIfBlank(getTextConfigs().get("session_review_retry_button"));
        if (nullIfBlank4 != null) {
        }
        HyperSnapUIConfigUtil.getInstance().customiseSecondaryButton((Button) binding2.btnRetry);
        HyperSnapUIConfigUtil.getInstance().customiseTitleTextView(binding2.tvTitle);
        HyperSnapUIConfigUtil.getInstance().customiseDescriptionTextView(binding2.tvDesc);
        HyperSnapUIConfigUtil.getInstance().customisePrimaryButton(binding2.btnConfirm);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding2.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding2.hkLayoutTopBar.hkClientLogo);
        LinearLayout linearLayout22 = binding2.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout22, "includeBranding.llBrandingRoot");
        linearLayout22.setVisibility(getMainVM().shouldShowBranding() ? i : 8);
        uiConfigData = getMainVM().getUiConfigData();
        if (uiConfigData != null) {
        }
        sessionReviewFragment.loadBackground$hyperkyc_release(str6);
    }

    private final void retry() {
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
        sb.append("retry() called");
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
                    Log.println(3, str, "retry() called ");
                }
            }
        }
        getMainVM().setSessionRecordingReAttemptsLeft$hyperkyc_release(r0.getSessionRecordingReAttemptsLeft() - 1);
        getMainVM().flowBackToNearestStartSessionModule();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void proceedToUpload() {
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
        sb.append("proceedToUpload() called");
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
                    Log.println(3, str, "proceedToUpload() called ");
                }
            }
        }
        BaseFragment.finishSessionUpload$hyperkyc_release$default(this, this.uiState, getMainVM().getCurrentModuleId$hyperkyc_release(), HVSessionRecorder.INSTANCE.getInstance().getUploadUrl(), HVSessionRecorder.INSTANCE.getInstance().getFilePath(), null, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x01f8, code lost:
    
        if (r3 == null) goto L71;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void initVideoPlayer(VideoView videoView, String filePath) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String str;
        String className2;
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
        String str3 = "initVideoPlayer() called with: videoView = " + videoView + ", filePath = " + filePath;
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
                    String str4 = "initVideoPlayer() called with: videoView = " + videoView + ", filePath = " + filePath;
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
        HkFragmentSessionReviewBinding binding = getBinding();
        ImageView progressSpinnerImageView = binding.progressSpinnerImageView;
        Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView, "progressSpinnerImageView");
        ViewExtsKt.stopAnimation(progressSpinnerImageView);
        binding.progressSpinnerImageView.setVisibility(4);
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        View view = getView();
        if (view != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(view.getWidth());
            sb3.append(':');
            sb3.append(view.getHeight());
            str = sb3.toString();
        }
        str = "16:9";
        layoutParams2.dimensionRatio = str;
        videoView.setVideoURI(Uri.parse(filePath));
        videoView.setBackgroundColor(0);
        videoView.start();
        videoView.requestFocus();
    }
}
