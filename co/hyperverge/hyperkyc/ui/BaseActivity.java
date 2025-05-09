package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BundleKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import co.hyperverge.crashguard.CrashGuard;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowAPIHeaders;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder;
import co.hyperverge.hyperkyc.ui.models.FinishWithResultEvent;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
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
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.apache.commons.io.FilenameUtils;

/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eJ:\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010#J\u000e\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&JK\u0010$\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u000e2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020-¢\u0006\u0002\u0010/J\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e01J\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e01JM\u00103\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u000e2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020-H\u0014¢\u0006\u0002\u0010/R\u001b\u0010\u0003\u001a\u00020\u00048DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\b\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lco/hyperverge/hyperkyc/ui/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", HyperKycConfig.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "getHyperKycConfig", "()Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "hyperKycConfig$delegate", "Lkotlin/Lazy;", HyperKycData.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "getHyperKycData", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "journeyId", "", "getJourneyId", "()Ljava/lang/String;", "journeyId$delegate", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "pushTransactionCollectJob", "Lkotlinx/coroutines/Job;", "addSessionRecordingVideoUriToResult", "", "uiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "filePath", "isCompleted", "finishSessionUpload", UploadingFragment.ARG_KEY_STOP_MODULE_ID, "uploadUrl", "finishTransactionCallback", "Lkotlin/Function0;", "finishWithResult", "finishWithResultEvent", "Lco/hyperverge/hyperkyc/ui/models/FinishWithResultEvent;", "status", "data", "errorCode", "", "errorMessage", "performReviewFinish", "", "performStatePush", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;Ljava/lang/Integer;Ljava/lang/String;ZZ)V", "getAuthHeaders", "", "getDefaultHeaders", "retryFT", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class BaseActivity extends AppCompatActivity {

    /* renamed from: hyperKycConfig$delegate, reason: from kotlin metadata */
    private final Lazy co.hyperverge.hyperkyc.data.models.HyperKycConfig.ARG_KEY java.lang.String = LazyKt.lazy(new Function0<HyperKycConfig>() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$hyperKycConfig$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x01df, code lost:
        
            if (r0 != null) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x01ef, code lost:
        
            if (r0 == null) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01f3, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0202, code lost:
        
            if (r0.find() == false) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0204, code lost:
        
            r11 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0211, code lost:
        
            if (r11.length() <= 23) goto L89;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0217, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x021a, code lost:
        
            r11 = r11.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0222, code lost:
        
            r0 = new java.lang.StringBuilder();
            r0.append("getSerializable: failed for key hyperKycConfig");
            r0.append(' ');
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x022f, code lost:
        
            if (r5 == null) goto L92;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0231, code lost:
        
            r2 = r5.getLocalizedMessage();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0237, code lost:
        
            if (r2 == null) goto L95;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0239, code lost:
        
            r12 = '\n' + r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0248, code lost:
        
            r0.append(r12);
            android.util.Log.println(6, r11, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0236, code lost:
        
            r2 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x01f2, code lost:
        
            r11 = r0;
         */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final HyperKycConfig invoke() {
            Object m1202constructorimpl;
            String str;
            String canonicalName;
            String str2;
            Object m1202constructorimpl2;
            String str3;
            String className;
            String className2;
            HyperKycConfig hyperKycConfig;
            if (BaseActivity.this.getIntent().hasExtra(HyperKycConfig.ARG_CACHE_FILE_PATH_KEY)) {
                String stringExtra = BaseActivity.this.getIntent().getStringExtra(HyperKycConfig.ARG_CACHE_FILE_PATH_KEY);
                Intrinsics.checkNotNull(stringExtra);
                if (!new File(stringExtra).exists()) {
                    throw new IllegalStateException(("File not found at path: " + stringExtra + ", HyperKycConfig cannot be extracted").toString());
                }
                return (HyperKycConfig) BaseActivity.this.getMainVM().getGson().fromJson(FilesKt.readText$default(new File(stringExtra), null, 1, null), HyperKycConfig.class);
            }
            Intent intent = BaseActivity.this.getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            try {
                Result.Companion companion = Result.INSTANCE;
                if (Build.VERSION.SDK_INT >= 33) {
                    hyperKycConfig = intent.getSerializableExtra(HyperKycConfig.ARG_KEY, HyperKycConfig.class);
                } else {
                    Serializable serializableExtra = intent.getSerializableExtra(HyperKycConfig.ARG_KEY);
                    if (serializableExtra == null) {
                        throw new NullPointerException("null cannot be cast to non-null type co.hyperverge.hyperkyc.data.models.HyperKycConfig");
                    }
                    hyperKycConfig = (HyperKycConfig) serializableExtra;
                }
                m1202constructorimpl = Result.m1202constructorimpl(hyperKycConfig);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = m1202constructorimpl;
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
            if (m1205exceptionOrNullimpl != null) {
                HyperLogger.Level level = HyperLogger.Level.ERROR;
                HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str4 = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = intent.getClass();
                    canonicalName = cls != null ? cls.getCanonicalName() : null;
                    if (canonicalName == null) {
                        canonicalName = "N/A";
                    }
                }
                Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                String str5 = "";
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
                sb.append("getSerializable: failed for key hyperKycConfig");
                sb.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str2 = '\n' + localizedMessage;
                } else {
                    str2 = "";
                }
                sb.append(str2);
                companion3.log(level, sb.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion5 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String packageName = (String) m1202constructorimpl2;
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
                            str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = intent.getClass();
                        str3 = cls2 != null ? cls2.getCanonicalName() : str;
                    }
                }
                str = null;
            } else {
                str = null;
            }
            HyperKycConfig hyperKycConfig2 = (HyperKycConfig) ((Serializable) (Result.m1208isFailureimpl(obj) ? str : obj));
            if (hyperKycConfig2 != null) {
                return hyperKycConfig2;
            }
            throw new IllegalStateException("HyperKycConfig cannot be null".toString());
        }
    });

    /* renamed from: journeyId$delegate, reason: from kotlin metadata */
    private final Lazy journeyId = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$journeyId$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            String stringExtra = BaseActivity.this.getIntent().getStringExtra("journeyId");
            if (stringExtra != null) {
                return stringExtra;
            }
            throw new IllegalStateException("journeyId cannot be null".toString());
        }
    });

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;
    private Job pushTransactionCollectJob;

    public void retryFT(String status, HyperKycData data, Integer errorCode, String errorMessage, boolean performReviewFinish, boolean performStatePush) {
        Intrinsics.checkNotNullParameter(status, "status");
    }

    public BaseActivity() {
        final BaseActivity baseActivity = this;
        final Function0 function0 = null;
        this.mainVM = new ViewModelLazy(Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0<CreationExtras>() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = baseActivity.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
    }

    public final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    public final HyperKycData getHyperKycData() {
        return getMainVM().getHyperKycData();
    }

    public final HyperKycConfig getHyperKycConfig() {
        Object value = this.co.hyperverge.hyperkyc.data.models.HyperKycConfig.ARG_KEY java.lang.String.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-hyperKycConfig>(...)");
        return (HyperKycConfig) value;
    }

    public final String getJourneyId() {
        return (String) this.journeyId.getValue();
    }

    public static /* synthetic */ void retryFT$default(BaseActivity baseActivity, String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, int i, Object obj) {
        Object m1202constructorimpl;
        HyperKycData hyperKycData2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: retryFT");
        }
        if ((i & 2) != 0) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(baseActivity.getHyperKycData());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = null;
            }
            hyperKycData2 = (HyperKycData) m1202constructorimpl;
        } else {
            hyperKycData2 = hyperKycData;
        }
        baseActivity.retryFT(str, hyperKycData2, (i & 4) != 0 ? null : num, (i & 8) == 0 ? str2 : null, (i & 16) != 0 ? true : z, (i & 32) == 0 ? z2 : true);
    }

    public final void finishWithResult(FinishWithResultEvent finishWithResultEvent) {
        Intrinsics.checkNotNullParameter(finishWithResultEvent, "finishWithResultEvent");
        finishWithResult(finishWithResultEvent.getStatus(), finishWithResultEvent.getData(), finishWithResultEvent.getErrorCode(), finishWithResultEvent.getErrorMessage(), finishWithResultEvent.getPerformReviewFinish(), finishWithResultEvent.getPerformStatePush());
    }

    public static /* synthetic */ void finishWithResult$default(BaseActivity baseActivity, String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, int i, Object obj) {
        Object m1202constructorimpl;
        HyperKycData hyperKycData2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishWithResult");
        }
        if ((i & 2) != 0) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(baseActivity.getHyperKycData());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = null;
            }
            hyperKycData2 = (HyperKycData) m1202constructorimpl;
        } else {
            hyperKycData2 = hyperKycData;
        }
        baseActivity.finishWithResult(str, hyperKycData2, (i & 4) != 0 ? null : num, (i & 8) == 0 ? str2 : null, (i & 16) != 0 ? true : z, (i & 32) == 0 ? z2 : true);
    }

    public final Map<String, String> getAuthHeaders() {
        String appId = getHyperKycConfig().getAppId();
        Intrinsics.checkNotNull(appId);
        Map<String, String> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("appId", appId), TuplesKt.to("Content-Type", "application/json"));
        String nullIfBlank = CoreExtsKt.nullIfBlank(getHyperKycConfig().getAppKey());
        if (nullIfBlank != null) {
            mutableMapOf.put(HyperKycConfig.APP_KEY, nullIfBlank);
        }
        String nullIfBlank2 = CoreExtsKt.nullIfBlank(getHyperKycConfig().getAccessToken());
        if (nullIfBlank2 != null) {
            mutableMapOf.put("Authorization", nullIfBlank2);
        }
        return mutableMapOf;
    }

    public final Map<String, String> getDefaultHeaders() {
        Context appContext = getApplicationContext();
        Application application = getApplication();
        String str = application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName;
        Object obj = getHyperKycConfig().getMetadataMap().get(WorkflowAPIHeaders.SDK_TYPE);
        if (obj == null) {
            obj = "android";
        }
        String str2 = (String) obj;
        Object obj2 = getHyperKycConfig().getMetadataMap().get(WorkflowAPIHeaders.SDK_VERSION);
        if (obj2 == null) {
            obj2 = BuildConfig.HYPERKYC_VERSION_NAME;
        }
        String sdkMode = InternalToolUtils.getSdkMode(appContext);
        Pair[] pairArr = new Pair[17];
        pairArr[0] = TuplesKt.to("workflowId", getHyperKycConfig().getWorkflowId$hyperkyc_release());
        pairArr[1] = TuplesKt.to("transactionId", getHyperKycConfig().getTransactionId$hyperkyc_release());
        pairArr[2] = TuplesKt.to("uniqueId", getHyperKycConfig().getUniqueId());
        pairArr[3] = TuplesKt.to("device", Build.MODEL);
        pairArr[4] = TuplesKt.to(WorkflowAPIHeaders.DEVICE_MAKE, Build.BRAND);
        pairArr[5] = TuplesKt.to("platform", "android");
        pairArr[6] = TuplesKt.to("os", "android");
        pairArr[7] = TuplesKt.to(WorkflowAPIHeaders.SDK_VERSION, (String) obj2);
        pairArr[8] = TuplesKt.to(WorkflowAPIHeaders.SDK_TYPE, str2);
        pairArr[9] = TuplesKt.to("sdk-mode", sdkMode);
        pairArr[10] = TuplesKt.to(WorkflowAPIHeaders.APP_VERSION, str);
        pairArr[11] = TuplesKt.to(WorkflowAPIHeaders.PLATFORM_VERSION, Build.VERSION.RELEASE);
        pairArr[12] = TuplesKt.to(WorkflowAPIHeaders.WEB_CORE_MODE, "no");
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        String webViewServicesVersion = ContextExtsKt.getWebViewServicesVersion(appContext);
        String str3 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        if (webViewServicesVersion == null) {
            webViewServicesVersion = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        pairArr[13] = TuplesKt.to(WorkflowAPIHeaders.WEBVIEW_VERSION, webViewServicesVersion);
        pairArr[14] = TuplesKt.to(WorkflowAPIHeaders.WEBVIEW_ENABLED, String.valueOf(ContextExtsKt.isWebViewEnabled(appContext)));
        pairArr[15] = TuplesKt.to(WorkflowAPIHeaders.PLAY_SERVICES_VERSION, String.valueOf(ContextExtsKt.getGooglePlayServicesVersion(appContext)));
        String playStoreVersion = ContextExtsKt.getPlayStoreVersion(appContext);
        if (playStoreVersion != null) {
            str3 = playStoreVersion;
        }
        pairArr[16] = TuplesKt.to(WorkflowAPIHeaders.PLAY_STORE_VERSION, str3);
        Map mapOf = MapsKt.mapOf(pairArr);
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapOf.size()));
        for (Map.Entry entry : mapOf.entrySet()) {
            Object key = entry.getKey();
            String str4 = (String) entry.getValue();
            if (str4 == null) {
                str4 = "";
            }
            linkedHashMap.put(key, str4);
        }
        return linkedHashMap;
    }

    public static /* synthetic */ void finishSessionUpload$default(BaseActivity baseActivity, WorkflowUIState workflowUIState, String str, String str2, String str3, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishSessionUpload");
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        baseActivity.finishSessionUpload(workflowUIState, str, str2, str3, function0);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0399  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void finishWithResult(final String status, final HyperKycData data, final Integer errorCode, final String errorMessage, boolean performReviewFinish, boolean performStatePush) {
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        String canonicalName2;
        String str3;
        String className;
        String canonicalName3;
        Object m1202constructorimpl2;
        String canonicalName4;
        String str4;
        Matcher matcher;
        String className2;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(status, "status");
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
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str5 = "finishWithResult() called with: \nstatus = " + status + ", \ndata = " + data + ", \nerrorCode = " + errorCode + ", \nerrorMessage = " + errorMessage + ", \nperformReviewFinish = " + performReviewFinish + ", \nperformStatePush: " + performStatePush;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str = ", \nperformStatePush: ";
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                    }
                    str2 = "android.app.AppGlobals";
                    str3 = "replaceAll(\"\")";
                    if (getMainVM().getFlowFinished()) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                str = ", \nperformStatePush: ";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                str2 = "android.app.AppGlobals";
                if (!StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    str3 = "replaceAll(\"\")";
                    if (getMainVM().getFlowFinished()) {
                    }
                } else {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        str3 = "replaceAll(\"\")";
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, str3);
                    } else {
                        str3 = "replaceAll(\"\")";
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str6 = "finishWithResult() called with: \nstatus = " + status + ", \ndata = " + data + ", \nerrorCode = " + errorCode + ", \nerrorMessage = " + errorMessage + ", \nperformReviewFinish = " + performReviewFinish + str + performStatePush;
                    if (str6 == null) {
                        str6 = "null ";
                    }
                    sb2.append(str6);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                    if (getMainVM().getFlowFinished()) {
                        ActivityExtsKt.unregisterActivityCallbacks(this);
                        getMainVM().setFlowFinished$hyperkyc_release(true);
                        HVSessionRecorder companion4 = HVSessionRecorder.INSTANCE.getInstance();
                        if (companion4.getIsRecordingStarted()) {
                            if (companion4.getShouldUpload()) {
                                finishSessionUpload(new WorkflowUIState.StopSessionRecording(companion4.getStopModuleId(), false, null, false, 14, null), companion4.getStopModuleId(), companion4.getUploadUrl(), companion4.getFilePath(), new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$5$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                        BaseActivity.finishWithResult$default(BaseActivity.this, status, data, errorCode, errorMessage, false, false, 48, null);
                                    }
                                });
                                return;
                            }
                            HVSessionRecorder.stop$hyperkyc_release$default(companion4, null, 1, null);
                        }
                        HyperSnapSDK.endUserSession();
                        CrashGuard.INSTANCE.getInstance().endSession();
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new BaseActivity$finishWithResult$6(status, this, performStatePush, errorCode, errorMessage, performReviewFinish, data, null), 3, null);
                        return;
                    }
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        if (canonicalName3 == null) {
                            canonicalName3 = "N/A";
                        }
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher4.find()) {
                        canonicalName3 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, str3);
                    }
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(canonicalName3);
                    sb3.append(" - ");
                    sb3.append("finishWithResult: skipping as flow has already been terminated");
                    sb3.append(' ');
                    sb3.append("");
                    companion5.log(level2, sb3.toString());
                    if (CoreExtsKt.isRelease()) {
                        return;
                    }
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName(str2).getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName3 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName4 == null) {
                                    str4 = "N/A";
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher.find()) {
                                        str4 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str4, str3);
                                    }
                                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(5, str4, "finishWithResult: skipping as flow has already been terminated ");
                                    return;
                                }
                            }
                            str4 = canonicalName4;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher.find()) {
                            }
                            if (str4.length() > 23) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(5, str4, "finishWithResult: skipping as flow has already been terminated ");
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }
        str2 = "android.app.AppGlobals";
        str3 = "replaceAll(\"\")";
        if (getMainVM().getFlowFinished()) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0052, code lost:
    
        if (r11 != null) goto L168;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x03be  */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void finishSessionUpload(WorkflowUIState uiState, String r25, String uploadUrl, String filePath, Function0<Unit> finishTransactionCallback) {
        String str;
        String str2;
        String str3;
        Object m1202constructorimpl;
        String str4;
        Function0<Unit> function0;
        String canonicalName;
        boolean z;
        String className;
        int i;
        String str5;
        String canonicalName2;
        Object m1202constructorimpl2;
        Unit unit;
        String canonicalName3;
        String str6;
        Matcher matcher;
        String className2;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(r25, "stopModuleId");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null) {
            str = "N/A";
        } else {
            str = "N/A";
            str2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        Class<?> cls = getClass();
        str2 = cls != null ? cls.getCanonicalName() : null;
        if (str2 == null) {
            str2 = str;
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
        String str7 = str2;
        if (matcher2.find()) {
            str3 = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
        } else {
            str3 = str7;
        }
        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str3 = str3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(str3);
        sb.append(" - ");
        String str8 = "finishSessionUpload() called with: uiState = " + uiState + ", stopModuleId = " + r25 + ", uploadUrl = " + uploadUrl + ", filePath = " + filePath + ", finishTransactionCallback = " + finishTransactionCallback;
        if (str8 == null) {
            str8 = "null ";
        }
        sb.append(str8);
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
                    str4 = uploadUrl;
                    function0 = finishTransactionCallback;
                    i = 0;
                    str5 = str4;
                    if (((str5 != null || str5.length() == 0) ? true : i) != false) {
                    }
                } else {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName == null) {
                            canonicalName = str;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                    if (matcher3.find()) {
                        canonicalName = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                    }
                    if (canonicalName.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        z = false;
                    } else {
                        z = false;
                        canonicalName = canonicalName.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("finishSessionUpload() called with: uiState = ");
                    sb3.append(uiState);
                    sb3.append(", stopModuleId = ");
                    sb3.append(r25);
                    sb3.append(", uploadUrl = ");
                    str4 = uploadUrl;
                    sb3.append(str4);
                    sb3.append(", filePath = ");
                    sb3.append(filePath);
                    sb3.append(", finishTransactionCallback = ");
                    function0 = finishTransactionCallback;
                    sb3.append(function0);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName, sb2.toString());
                    i = z;
                    str5 = str4;
                    if (((str5 != null || str5.length() == 0) ? true : i) != false) {
                        addSessionRecordingVideoUriToResult(uiState, filePath, "no");
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb5 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher4.find()) {
                            canonicalName2 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(i, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb5.append(canonicalName2);
                        sb5.append(" - ");
                        sb5.append("Session Recording upload API call failed! - Invalid URL");
                        sb5.append(' ');
                        sb5.append("");
                        companion4.log(level2, sb5.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[i]).invoke(null, new Object[i]);
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
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", (boolean) i, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                    if (canonicalName3 == null) {
                                        str6 = str;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                        if (matcher.find()) {
                                            str6 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                        }
                                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str6 = str6.substring(i, 23);
                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(6, str6, "Session Recording upload API call failed! - Invalid URL ");
                                    }
                                }
                                str6 = canonicalName3;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                if (matcher.find()) {
                                }
                                if (str6.length() > 23) {
                                    str6 = str6.substring(i, 23);
                                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(6, str6, "Session Recording upload API call failed! - Invalid URL ");
                            }
                        }
                        HVSessionRecorder.stop$hyperkyc_release$default(HVSessionRecorder.INSTANCE.getInstance(), null, 1, null);
                        if (function0 != null) {
                            finishTransactionCallback.invoke();
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            finishWithResult$default(this, "error", null, 104, "Session Recording upload API call failed! - Invalid URL", false, false, 50, null);
                            return;
                        }
                        return;
                    }
                    UploadingFragment uploadingFragment = new UploadingFragment(uiState, function0);
                    Pair[] pairArr = new Pair[3];
                    pairArr[i] = TuplesKt.to(UploadingFragment.ARG_KEY_STOP_MODULE_ID, r25);
                    pairArr[1] = TuplesKt.to("uploadUrl", str4);
                    pairArr[2] = TuplesKt.to("filePath", filePath);
                    ActivityExtsKt.replaceContent$default(this, uploadingFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
                    return;
                }
            }
        }
        str4 = uploadUrl;
        function0 = finishTransactionCallback;
        i = 0;
        str5 = str4;
        if (((str5 != null || str5.length() == 0) ? true : i) != false) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0152, code lost:
    
        if (r0 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0162, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0166, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0175, code lost:
    
        if (r0.find() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0177, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0184, code lost:
    
        if (r8.length() <= 23) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x018a, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x018d, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0194, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addSessionRecordingVideoUriToResult() called with: uiState = " + r20 + ", filePath = " + r21 + ", isCompleted = " + r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01b4, code lost:
    
        if (r4 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01b6, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01b8, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0165, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void addSessionRecordingVideoUriToResult(WorkflowUIState uiState, String filePath, String isCompleted) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(isCompleted, "isCompleted");
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
        String str4 = "addSessionRecordingVideoUriToResult() called with: uiState = " + uiState + ", filePath = " + filePath + ", isCompleted = " + isCompleted;
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
        HyperKycData.SessionData sessionData = new HyperKycData.SessionData(null, null, null, null, null, null, 63, null);
        sessionData.setVideoPath$hyperkyc_release(filePath);
        sessionData.setCompleted$hyperkyc_release(isCompleted);
        MainVM.updateSessionData$hyperkyc_release$default(getMainVM(), uiState, sessionData, false, 4, null);
    }
}
