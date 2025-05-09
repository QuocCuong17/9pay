package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: BaseFragment.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0010\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0016JA\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001cH\u0000¢\u0006\u0002\b\u001dJK\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00142\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010$\u001a\u00020\u00172\b\b\u0002\u0010%\u001a\u00020\u0017¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020\u0010H\u0016J\u0014\u0010(\u001a\u00020\u00102\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0019\u0010+\u001a\u00020\u00102\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0014H\u0000¢\u0006\u0002\b-J\r\u0010.\u001a\u00020\u0010H\u0000¢\u0006\u0002\b/J\u001a\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u0002022\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J=\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u00172\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u00106\u001a\u00020\u00172\u000e\b\u0002\u00107\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cH\u0000¢\u0006\u0004\b8\u00109R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006:"}, d2 = {"Lco/hyperverge/hyperkyc/ui/BaseFragment;", "Landroidx/fragment/app/Fragment;", "layoutResId", "", "(I)V", "lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "getLifecycleScope", "()Landroidx/lifecycle/LifecycleCoroutineScope;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "Lkotlin/Lazy;", "addSessionRecordingVideoUriToResult", "", "uiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "filePath", "", "isCompleted", "deferredInitViews", "", "finishSessionUpload", UploadingFragment.ARG_KEY_STOP_MODULE_ID, "uploadUrl", "finishTransactionCallback", "Lkotlin/Function0;", "finishSessionUpload$hyperkyc_release", "finishWithResult", "status", "data", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "errorCode", "errorMessage", "performReviewFinish", "performStatePush", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;Ljava/lang/Integer;Ljava/lang/String;ZZ)V", "initViews", "initViewsOnlyIfNotRecreated", "savedInstanceState", "Landroid/os/Bundle;", "loadBackground", "url", "loadBackground$hyperkyc_release", "onBackPressed", "onBackPressed$hyperkyc_release", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "updateEndState", "isLoading", "isSuccess", "isAPISuccess", "onFailAction", "updateEndState$hyperkyc_release", "(ZLjava/lang/Boolean;ZLkotlin/jvm/functions/Function0;)V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class BaseFragment extends Fragment {

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;

    public boolean deferredInitViews() {
        return false;
    }

    public void initViews() {
    }

    public BaseFragment(int i) {
        super(i);
        final BaseFragment baseFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(baseFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.BaseFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.BaseFragment$special$$inlined$activityViewModels$default$2
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
    }

    public final LifecycleCoroutineScope getLifecycleScope() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        return LifecycleOwnerKt.getLifecycleScope((AppCompatActivity) requireActivity);
    }

    private final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    public static /* synthetic */ void finishWithResult$default(BaseFragment baseFragment, String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishWithResult");
        }
        baseFragment.finishWithResult(str, (i & 2) != 0 ? null : hyperKycData, (i & 4) != 0 ? null : num, (i & 8) == 0 ? str2 : null, (i & 16) != 0 ? true : z, (i & 32) == 0 ? z2 : true);
    }

    public final void finishWithResult(String status, HyperKycData data, Integer errorCode, String errorMessage, boolean performReviewFinish, boolean performStatePush) {
        Intrinsics.checkNotNullParameter(status, "status");
        if (data != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
            ((HKMainActivity) requireActivity).finishWithResult(status, data, errorCode, errorMessage, performReviewFinish, performStatePush);
        } else {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
            BaseActivity.finishWithResult$default((HKMainActivity) requireActivity2, status, null, errorCode, errorMessage, performReviewFinish, performStatePush, 2, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
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
        if (deferredInitViews()) {
            return;
        }
        initViewsOnlyIfNotRecreated(savedInstanceState);
    }

    public static /* synthetic */ void initViewsOnlyIfNotRecreated$default(BaseFragment baseFragment, Bundle bundle, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initViewsOnlyIfNotRecreated");
        }
        if ((i & 1) != 0) {
            bundle = null;
        }
        baseFragment.initViewsOnlyIfNotRecreated(bundle);
    }

    public void initViewsOnlyIfNotRecreated(Bundle savedInstanceState) {
        if (savedInstanceState == null || !getMainVM().getHsStateHandler().getIsActivityRecreated()) {
            initViews();
        }
    }

    public static /* synthetic */ void loadBackground$hyperkyc_release$default(BaseFragment baseFragment, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadBackground");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        baseFragment.loadBackground$hyperkyc_release(str);
    }

    public final void loadBackground$hyperkyc_release(String url) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (ActivityExtsKt.isHKWebCoreActivity(requireActivity)) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity");
            ((HKWebCoreActivity) requireActivity2).loadBackground$hyperkyc_release(url);
        } else {
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNull(requireActivity3, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
            ((HKMainActivity) requireActivity3).loadBackground$hyperkyc_release(url);
        }
    }

    public final void onBackPressed$hyperkyc_release() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        ((AppCompatActivity) requireActivity).onBackPressed();
    }

    public final void addSessionRecordingVideoUriToResult(WorkflowUIState uiState, String filePath, String isCompleted) {
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(isCompleted, "isCompleted");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
        ((HKMainActivity) requireActivity).addSessionRecordingVideoUriToResult(uiState, filePath, isCompleted);
    }

    public static /* synthetic */ void finishSessionUpload$hyperkyc_release$default(BaseFragment baseFragment, WorkflowUIState workflowUIState, String str, String str2, String str3, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishSessionUpload");
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        baseFragment.finishSessionUpload$hyperkyc_release(workflowUIState, str, str2, str3, function0);
    }

    public final void finishSessionUpload$hyperkyc_release(WorkflowUIState uiState, String stopModuleId, String uploadUrl, String filePath, Function0<Unit> finishTransactionCallback) {
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
        ((HKMainActivity) requireActivity).finishSessionUpload(uiState, stopModuleId, uploadUrl, filePath, finishTransactionCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateEndState$hyperkyc_release$default(BaseFragment baseFragment, boolean z, Boolean bool, boolean z2, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateEndState");
        }
        if ((i & 2) != 0) {
            bool = null;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.BaseFragment$updateEndState$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        baseFragment.updateEndState$hyperkyc_release(z, bool, z2, function0);
    }

    public final void updateEndState$hyperkyc_release(boolean isLoading, Boolean isSuccess, boolean isAPISuccess, Function0<Unit> onFailAction) {
        Intrinsics.checkNotNullParameter(onFailAction, "onFailAction");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.HKMainActivity");
        ((HKMainActivity) requireActivity).updateEndState$hyperkyc_release(isLoading, isSuccess, isAPISuccess, onFailAction);
    }
}
