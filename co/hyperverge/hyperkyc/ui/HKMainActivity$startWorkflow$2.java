package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.LifecycleOwnerKt;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.data.models.state.TransactionState;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.databinding.HkActivityMainBinding;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.airbnb.lottie.LottieAnimationView;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2", f = "HKMainActivity.kt", i = {0, 2, 3}, l = {430, 454, 466, BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING, 647}, m = "invokeSuspend", n = {"$this$coroutineScope", "responses", "responses"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class HKMainActivity$startWorkflow$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startWorkflow$2(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$startWorkflow$2> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$startWorkflow$2 hKMainActivity$startWorkflow$2 = new HKMainActivity$startWorkflow$2(this.this$0, continuation);
        hKMainActivity$startWorkflow$2.L$0 = obj;
        return hKMainActivity$startWorkflow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$startWorkflow$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0434, code lost:
    
        if (r14.equals(co.hyperverge.hyperkyc.data.models.result.HyperKycStatus.USER_CANCELLED) == false) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x043e, code lost:
    
        if (r14.equals(co.hyperverge.hyperkyc.data.models.result.HyperKycStatus.AUTO_DECLINED) == false) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0448, code lost:
    
        if (r14.equals(co.hyperverge.hyperkyc.data.models.WorkflowModule.END_STATE_DECLINE) == false) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0452, code lost:
    
        if (r14.equals("error") == false) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x045c, code lost:
    
        if (r14.equals(co.hyperverge.hyperkyc.data.models.WorkflowModule.END_STATE_APPROVE) == false) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0468, code lost:
    
        r0 = r3.getModuleExecutionOrder();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x046c, code lost:
    
        if (r0 == null) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x046e, code lost:
    
        r0 = kotlin.collections.CollectionsKt.toMutableList((java.util.Collection) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0474, code lost:
    
        if (r0 == null) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0476, code lost:
    
        r29.this$0.getMainVM().setModuleExecutionOrder$hyperkyc_release(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x047f, code lost:
    
        r0 = r3.getWorkflowExecutionOrder();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0483, code lost:
    
        if (r0 == null) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0485, code lost:
    
        r0 = kotlin.collections.CollectionsKt.toMutableList((java.util.Collection) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x048b, code lost:
    
        if (r0 == null) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x048d, code lost:
    
        r29.this$0.getMainVM().setWorkflowExecutionOrder$hyperkyc_release(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0496, code lost:
    
        r29.this$0.getMainVM().setHyperKycData$hyperkyc_release(r29.this$0.getMainVM().createHyperKycData$hyperkyc_release(r3));
        co.hyperverge.hyperkyc.ui.BaseActivity.finishWithResult$default(r29.this$0, r14, null, null, null, false, false, 14, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x04c4, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0466, code lost:
    
        if (r14.equals(co.hyperverge.hyperkyc.data.models.result.HyperKycStatus.AUTO_APPROVED) != false) goto L160;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x0429. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x05ac  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02ec  */
    /* JADX WARN: Type inference failed for: r4v111 */
    /* JADX WARN: Type inference failed for: r4v112 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v27, types: [T] */
    /* JADX WARN: Type inference failed for: r4v36, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v38, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v42, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v44, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v9, types: [T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        ?? canonicalName2;
        Class<?> cls2;
        String str2;
        String className;
        Deferred async$default;
        CoroutineScope coroutineScope;
        String className2;
        Deferred async$default2;
        Deferred async$default3;
        Deferred async$default4;
        Deferred async$default5;
        Deferred async$default6;
        Object awaitAll;
        List list;
        int i;
        boolean validateSdkVersion;
        boolean validateInputs;
        Object loadTransactionState;
        String networkErrorMessage;
        boolean z;
        boolean z2;
        Object startFlow;
        TransactionStateResponse transactionStateResponse;
        Integer boxInt;
        String status;
        TransactionState.TransactionMetadata transactionMetadata;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? 0 : cls.getCanonicalName();
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
            sb.append("startWorkflow() called");
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? 0 : cls2.getCanonicalName();
                            if (canonicalName2 == 0) {
                                canonicalName2 = "N/A";
                            }
                        }
                        objectRef2.element = canonicalName2;
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
                        Log.println(3, str2, "startWorkflow() called ");
                    }
                }
            }
            async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope2, null, null, new HKMainActivity$startWorkflow$2$defaultRemoteConfig$1(this.this$0, null), 3, null);
            Deferred[] deferredArr = {async$default};
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (AwaitKt.awaitAll(deferredArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else if (i2 == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    list = (List) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    loadTransactionState = obj;
                    transactionStateResponse = (TransactionStateResponse) loadTransactionState;
                    boxInt = transactionStateResponse == null ? Boxing.boxInt(transactionStateResponse.getStatusCode()) : null;
                    if (boxInt != null && boxInt.intValue() == 200) {
                        TransactionState result = transactionStateResponse.getResult();
                        status = (result != null || (transactionMetadata = result.getTransactionMetadata()) == null) ? null : transactionMetadata.getStatus();
                        if (status != null) {
                            switch (status.hashCode()) {
                                case -1822838969:
                                    break;
                                case -793050291:
                                    break;
                                case 96784904:
                                    break;
                                case 1542349558:
                                    break;
                                case 1855079614:
                                    break;
                                case 2043678173:
                                    break;
                            }
                        }
                        this.this$0.getMainVM().saveStateLocally$hyperkyc_release(true, result, false);
                    } else if (boxInt != null || boxInt.intValue() != 201) {
                        if (boxInt != null && boxInt.intValue() == 400) {
                            BaseActivity.finishWithResult$default(this.this$0, "error", null, Boxing.boxInt(101), transactionStateResponse.getErrorCode(), false, false, 34, null);
                            return Unit.INSTANCE;
                        }
                        if (boxInt != null && boxInt.intValue() == 409) {
                            BaseActivity.finishWithResult$default(this.this$0, "error", null, Boxing.boxInt(101), this.this$0.getString(R.string.hk_invalid_uniqueId_error), false, false, 34, null);
                            return Unit.INSTANCE;
                        }
                        if (boxInt != null) {
                            boxInt.intValue();
                        }
                    }
                    HkActivityMainBinding binding$hyperkyc_release = this.this$0.getBinding$hyperkyc_release();
                    HKMainActivity hKMainActivity = this.this$0;
                    LottieAnimationView lavLoader = binding$hyperkyc_release.lavLoader;
                    Intrinsics.checkNotNullExpressionValue(lavLoader, "lavLoader");
                    LottieAnimationView lottieAnimationView = lavLoader;
                    z = hKMainActivity.useWebForm;
                    lottieAnimationView.setVisibility(z ? 0 : 8);
                    z2 = this.this$0.useWebForm;
                    if (z2) {
                        this.L$0 = null;
                        this.label = 5;
                        startFlow = this.this$0.startFlow(this);
                        if (startFlow == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        Object obj2 = list.get(4);
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                        if (((Boolean) obj2).booleanValue()) {
                            FormWebViewDriver formWebViewDriver$hyperkyc_release = this.this$0.getFormWebViewDriver$hyperkyc_release();
                            final HKMainActivity hKMainActivity2 = this.this$0;
                            formWebViewDriver$hyperkyc_release.initWebSDK$hyperkyc_release(new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2.8
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
                                    LottieAnimationView lottieAnimationView2 = HKMainActivity.this.getBinding$hyperkyc_release().lavLoader;
                                    Intrinsics.checkNotNullExpressionValue(lottieAnimationView2, "binding.lavLoader");
                                    lottieAnimationView2.setVisibility(8);
                                    HKMainActivity.this.observeUiState();
                                    HKMainActivity.this.observeFinishWithResultState();
                                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(HKMainActivity.this, null), 3, null);
                                }

                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* compiled from: HKMainActivity.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$8$1", f = "HKMainActivity.kt", i = {}, l = {633}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$8$1, reason: invalid class name */
                                /* loaded from: classes2.dex */
                                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    int label;
                                    final /* synthetic */ HKMainActivity this$0;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(HKMainActivity hKMainActivity, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.this$0 = hKMainActivity;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.this$0, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object startFlow;
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            startFlow = this.this$0.startFlow(this);
                                            if (startFlow == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            });
                        } else {
                            HKMainActivity.showRetry$default(this.this$0, true, Boxing.boxInt(111), null, this.this$0.getString(R.string.hk_form_v2_load_error), null, 20, null);
                            LottieAnimationView lavLoader2 = this.this$0.getBinding$hyperkyc_release().lavLoader;
                            Intrinsics.checkNotNullExpressionValue(lavLoader2, "lavLoader");
                            lavLoader2.setVisibility(8);
                        }
                    }
                    return Unit.INSTANCE;
                }
                list = (List) this.L$0;
                ResultKt.throwOnFailure(obj);
                i = 3;
                Object obj3 = list.get(i);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.collections.List<co.hyperverge.hyperkyc.data.models.KycCountry>");
                List list2 = (List) obj3;
                if (this.this$0.getHyperKycConfig().isServerSideResumeEnabled$hyperkyc_release()) {
                    String uniqueId = this.this$0.getHyperKycConfig().getUniqueId();
                    if (!(!(uniqueId == null || StringsKt.isBlank(uniqueId)))) {
                        BaseActivity.finishWithResult$default(this.this$0, "error", null, Boxing.boxInt(101), this.this$0.getString(R.string.hk_invalid_uniqueId_error), false, false, 2, null);
                        return Unit.INSTANCE;
                    }
                }
                if (!this.this$0.getHyperKycConfig().exitIfRooted$hyperkyc_release() && ContextExtsKt.isDeviceRooted(this.this$0)) {
                    BaseActivity.finishWithResult$default(this.this$0, "error", null, Boxing.boxInt(114), this.this$0.getString(R.string.hk_device_rooted_error), false, false, 34, null);
                    return Unit.INSTANCE;
                }
                HKMainActivity hKMainActivity3 = this.this$0;
                validateSdkVersion = hKMainActivity3.validateSdkVersion(hKMainActivity3.getHyperKycConfig().getWorkflowConfig$hyperkyc_release());
                if (!validateSdkVersion) {
                    return Unit.INSTANCE;
                }
                HKMainActivity hKMainActivity4 = this.this$0;
                validateInputs = hKMainActivity4.validateInputs(hKMainActivity4.getHyperKycConfig().getWorkflowConfig$hyperkyc_release());
                if (!validateInputs) {
                    return Unit.INSTANCE;
                }
                if (list2.isEmpty()) {
                    HKMainActivity hKMainActivity5 = this.this$0;
                    Integer boxInt2 = Boxing.boxInt(111);
                    networkErrorMessage = this.this$0.getNetworkErrorMessage();
                    HKMainActivity.showRetry$default(hKMainActivity5, true, boxInt2, null, networkErrorMessage, null, 20, null);
                    LottieAnimationView lavLoader3 = this.this$0.getBinding$hyperkyc_release().lavLoader;
                    Intrinsics.checkNotNullExpressionValue(lavLoader3, "lavLoader");
                    lavLoader3.setVisibility(8);
                    return Unit.INSTANCE;
                }
                HKMainActivity.showRetry$default(this.this$0, false, null, null, null, null, 30, null);
                if (this.this$0.getHyperKycConfig().isServerSideResumeEnabled$hyperkyc_release()) {
                    this.this$0.getMainVM().deleteSavedTransactionStates$hyperkyc_release();
                    this.L$0 = list;
                    this.label = 4;
                    loadTransactionState = this.this$0.loadTransactionState(this);
                    if (loadTransactionState == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    transactionStateResponse = (TransactionStateResponse) loadTransactionState;
                    if (transactionStateResponse == null) {
                    }
                    if (boxInt != null) {
                        TransactionState result2 = transactionStateResponse.getResult();
                        if (result2 != null) {
                        }
                        if (status != null) {
                        }
                        this.this$0.getMainVM().saveStateLocally$hyperkyc_release(true, result2, false);
                    }
                    if (boxInt != null) {
                    }
                    if (boxInt != null) {
                        BaseActivity.finishWithResult$default(this.this$0, "error", null, Boxing.boxInt(101), transactionStateResponse.getErrorCode(), false, false, 34, null);
                        return Unit.INSTANCE;
                    }
                    if (boxInt != null) {
                        BaseActivity.finishWithResult$default(this.this$0, "error", null, Boxing.boxInt(101), this.this$0.getString(R.string.hk_invalid_uniqueId_error), false, false, 34, null);
                        return Unit.INSTANCE;
                    }
                    if (boxInt != null) {
                    }
                }
                HkActivityMainBinding binding$hyperkyc_release2 = this.this$0.getBinding$hyperkyc_release();
                HKMainActivity hKMainActivity6 = this.this$0;
                LottieAnimationView lavLoader4 = binding$hyperkyc_release2.lavLoader;
                Intrinsics.checkNotNullExpressionValue(lavLoader4, "lavLoader");
                LottieAnimationView lottieAnimationView2 = lavLoader4;
                z = hKMainActivity6.useWebForm;
                lottieAnimationView2.setVisibility(z ? 0 : 8);
                z2 = this.this$0.useWebForm;
                if (z2) {
                }
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            awaitAll = obj;
            list = (List) awaitAll;
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), null, null, new AnonymousClass2(this.this$0, null), 3, null);
            this.L$0 = list;
            i = 3;
            this.label = 3;
            if (CoroutineExtsKt.onUI$default(null, new AnonymousClass3(this.this$0, null), this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj32 = list.get(i);
            Intrinsics.checkNotNull(obj32, "null cannot be cast to non-null type kotlin.collections.List<co.hyperverge.hyperkyc.data.models.KycCountry>");
            List list22 = (List) obj32;
            if (this.this$0.getHyperKycConfig().isServerSideResumeEnabled$hyperkyc_release()) {
            }
            if (!this.this$0.getHyperKycConfig().exitIfRooted$hyperkyc_release()) {
            }
            HKMainActivity hKMainActivity32 = this.this$0;
            validateSdkVersion = hKMainActivity32.validateSdkVersion(hKMainActivity32.getHyperKycConfig().getWorkflowConfig$hyperkyc_release());
            if (!validateSdkVersion) {
            }
        }
        CoroutineScope coroutineScope3 = coroutineScope;
        async$default2 = BuildersKt__Builders_commonKt.async$default(coroutineScope3, null, null, new HKMainActivity$startWorkflow$2$textConfigAsync$1(this.this$0, null), 3, null);
        async$default3 = BuildersKt__Builders_commonKt.async$default(coroutineScope3, null, null, new HKMainActivity$startWorkflow$2$uiConfigAsync$1(this.this$0, null), 3, null);
        async$default4 = BuildersKt__Builders_commonKt.async$default(coroutineScope3, null, null, new HKMainActivity$startWorkflow$2$remoteConfigAsync$1(this.this$0, null), 3, null);
        async$default5 = BuildersKt__Builders_commonKt.async$default(coroutineScope3, null, null, new HKMainActivity$startWorkflow$2$countryAsync$1(this.this$0, null), 3, null);
        async$default6 = BuildersKt__Builders_commonKt.async$default(coroutineScope3, null, null, new HKMainActivity$startWorkflow$2$formWebViewAsync$1(this.this$0, null), 3, null);
        this.L$0 = null;
        this.label = 2;
        awaitAll = AwaitKt.awaitAll(new Deferred[]{async$default2, async$default3, async$default4, async$default5, async$default6}, this);
        if (awaitAll == coroutine_suspended) {
            return coroutine_suspended;
        }
        list = (List) awaitAll;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), null, null, new AnonymousClass2(this.this$0, null), 3, null);
        this.L$0 = list;
        i = 3;
        this.label = 3;
        if (CoroutineExtsKt.onUI$default(null, new AnonymousClass3(this.this$0, null), this, 1, null) == coroutine_suspended) {
        }
        Object obj322 = list.get(i);
        Intrinsics.checkNotNull(obj322, "null cannot be cast to non-null type kotlin.collections.List<co.hyperverge.hyperkyc.data.models.KycCountry>");
        List list222 = (List) obj322;
        if (this.this$0.getHyperKycConfig().isServerSideResumeEnabled$hyperkyc_release()) {
        }
        if (!this.this$0.getHyperKycConfig().exitIfRooted$hyperkyc_release()) {
        }
        HKMainActivity hKMainActivity322 = this.this$0;
        validateSdkVersion = hKMainActivity322.validateSdkVersion(hKMainActivity322.getHyperKycConfig().getWorkflowConfig$hyperkyc_release());
        if (!validateSdkVersion) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HKMainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$2", f = "HKMainActivity.kt", i = {}, l = {463}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ HKMainActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(HKMainActivity hKMainActivity, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = hKMainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object storeUIConfig;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                storeUIConfig = this.this$0.storeUIConfig(this);
                if (storeUIConfig == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HKMainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$3", f = "HKMainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ HKMainActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(HKMainActivity hKMainActivity, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = hKMainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.customiseRetryButton();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
