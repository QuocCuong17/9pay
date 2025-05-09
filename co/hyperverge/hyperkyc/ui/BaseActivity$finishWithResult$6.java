package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.os.BundleKt;
import androidx.lifecycle.LifecycleOwnerKt;
import co.hyperverge.hyperkyc.HyperKyc;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.models.result.HyperKycResult;
import co.hyperverge.hyperkyc.data.models.result.HyperKycStatus;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.ui.models.LoadingUIState;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.github.jaiimageio.plugins.tiff.FaxTIFFTagSet;
import java.io.File;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6", f = "BaseActivity.kt", i = {0}, l = {300, 373}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class BaseActivity$finishWithResult$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HyperKycData $data;
    final /* synthetic */ Integer $errorCode;
    final /* synthetic */ String $errorMessage;
    final /* synthetic */ boolean $performReviewFinish;
    final /* synthetic */ boolean $performStatePush;
    final /* synthetic */ String $status;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BaseActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseActivity$finishWithResult$6(String str, BaseActivity baseActivity, boolean z, Integer num, String str2, boolean z2, HyperKycData hyperKycData, Continuation<? super BaseActivity$finishWithResult$6> continuation) {
        super(2, continuation);
        this.$status = str;
        this.this$0 = baseActivity;
        this.$performStatePush = z;
        this.$errorCode = num;
        this.$errorMessage = str2;
        this.$performReviewFinish = z2;
        this.$data = hyperKycData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BaseActivity$finishWithResult$6 baseActivity$finishWithResult$6 = new BaseActivity$finishWithResult$6(this.$status, this.this$0, this.$performStatePush, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.$data, continuation);
        baseActivity$finishWithResult$6.L$0 = obj;
        return baseActivity$finishWithResult$6;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseActivity$finishWithResult$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0077  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        BaseActivity$finishWithResult$6 baseActivity$finishWithResult$6;
        CoroutineScope coroutineScope;
        Job job;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            AnalyticsLogger.INSTANCE.logUserSessionEndEvent$hyperkyc_release(this.$status);
            if (this.this$0.getHyperKycConfig().isServerSideResumeEnabled$hyperkyc_release() && this.$performStatePush) {
                baseActivity$finishWithResult$6 = this;
                coroutineScope = coroutineScope2;
                while (baseActivity$finishWithResult$6.this$0.getMainVM().getIsPushingState()) {
                }
                baseActivity$finishWithResult$6.this$0.getMainVM().enqueueStatePush$hyperkyc_release(baseActivity$finishWithResult$6.$status);
                job = baseActivity$finishWithResult$6.this$0.pushTransactionCollectJob;
                if (job != null) {
                }
                BaseActivity baseActivity = baseActivity$finishWithResult$6.this$0;
                launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(baseActivity), null, null, new AnonymousClass1(baseActivity$finishWithResult$6.this$0, baseActivity$finishWithResult$6.$status, baseActivity$finishWithResult$6.$errorCode, baseActivity$finishWithResult$6.$errorMessage, baseActivity$finishWithResult$6.$performReviewFinish, baseActivity$finishWithResult$6.$data, coroutineScope, baseActivity$finishWithResult$6.$performStatePush, null), 3, null);
                baseActivity.pushTransactionCollectJob = launch$default;
            } else {
                this.label = 2;
                if (invokeSuspend$performFinishReview(coroutineScope2, this.this$0, this.$data, this.$performStatePush, this.$status, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
            baseActivity$finishWithResult$6 = this;
            while (baseActivity$finishWithResult$6.this$0.getMainVM().getIsPushingState()) {
                baseActivity$finishWithResult$6.L$0 = coroutineScope;
                baseActivity$finishWithResult$6.label = 1;
                if (DelayKt.delay(100L, baseActivity$finishWithResult$6) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            baseActivity$finishWithResult$6.this$0.getMainVM().enqueueStatePush$hyperkyc_release(baseActivity$finishWithResult$6.$status);
            job = baseActivity$finishWithResult$6.this$0.pushTransactionCollectJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            BaseActivity baseActivity2 = baseActivity$finishWithResult$6.this$0;
            launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(baseActivity2), null, null, new AnonymousClass1(baseActivity$finishWithResult$6.this$0, baseActivity$finishWithResult$6.$status, baseActivity$finishWithResult$6.$errorCode, baseActivity$finishWithResult$6.$errorMessage, baseActivity$finishWithResult$6.$performReviewFinish, baseActivity$finishWithResult$6.$data, coroutineScope, baseActivity$finishWithResult$6.$performStatePush, null), 3, null);
            baseActivity2.pushTransactionCollectJob = launch$default;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0289 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Type inference failed for: r2v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10, types: [T] */
    /* JADX WARN: Type inference failed for: r5v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [T] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object invokeSuspend$performFinishReview(CoroutineScope coroutineScope, BaseActivity baseActivity, HyperKycData hyperKycData, boolean z, String str, Integer num, String str2, boolean z2, Continuation<? super Unit> continuation) {
        BaseActivity$finishWithResult$6$performFinishReview$1 baseActivity$finishWithResult$6$performFinishReview$1;
        BaseActivity$finishWithResult$6$performFinishReview$1 baseActivity$finishWithResult$6$performFinishReview$12;
        Object obj;
        Object coroutine_suspended;
        int i;
        ?? canonicalName;
        Class<?> cls;
        String str3;
        Object m1202constructorimpl;
        Class<?> cls2;
        String str4;
        String className;
        String substringAfterLast$default;
        BaseActivity baseActivity2;
        String str5;
        boolean z3;
        CoroutineScope coroutineScope2;
        HyperKycData hyperKycData2;
        Integer num2;
        String className2;
        FlowCollector flowCollector;
        String str6 = str;
        if (continuation instanceof BaseActivity$finishWithResult$6$performFinishReview$1) {
            baseActivity$finishWithResult$6$performFinishReview$1 = (BaseActivity$finishWithResult$6$performFinishReview$1) continuation;
            if ((baseActivity$finishWithResult$6$performFinishReview$1.label & Integer.MIN_VALUE) != 0) {
                baseActivity$finishWithResult$6$performFinishReview$1.label -= Integer.MIN_VALUE;
                baseActivity$finishWithResult$6$performFinishReview$12 = baseActivity$finishWithResult$6$performFinishReview$1;
                obj = baseActivity$finishWithResult$6$performFinishReview$12.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = baseActivity$finishWithResult$6$performFinishReview$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r7 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
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
                        str3 = (String) objectRef.element;
                    } else {
                        str3 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str3);
                    sb.append(" - ");
                    sb.append("performFinishReview() called");
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
                                    String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
                                    if (canonicalName2 != null) {
                                        r7 = canonicalName2;
                                    }
                                } else {
                                    r7 = substringAfterLast$default;
                                }
                                objectRef2.element = r7;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str4 = (String) objectRef2.element;
                                } else {
                                    str4 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str4, "performFinishReview() called ");
                            }
                        }
                    }
                    if (Intrinsics.areEqual(str6, "error")) {
                        baseActivity.getMainVM().pushLogsToRemote$hyperkyc_release();
                    }
                    if (z2 && baseActivity.getMainVM().isJourneyIdSet$hyperkyc_release()) {
                        MainVM mainVM = baseActivity.getMainVM();
                        baseActivity2 = baseActivity;
                        str5 = str2;
                        HyperKycResult invokeSuspend$getHyperKycResult = invokeSuspend$getHyperKycResult(coroutineScope, baseActivity2, hyperKycData, str, num, str5, false);
                        baseActivity$finishWithResult$6$performFinishReview$12.L$0 = coroutineScope;
                        baseActivity$finishWithResult$6$performFinishReview$12.L$1 = baseActivity2;
                        baseActivity$finishWithResult$6$performFinishReview$12.L$2 = hyperKycData;
                        baseActivity$finishWithResult$6$performFinishReview$12.L$3 = str6;
                        baseActivity$finishWithResult$6$performFinishReview$12.L$4 = num;
                        baseActivity$finishWithResult$6$performFinishReview$12.L$5 = str5;
                        baseActivity$finishWithResult$6$performFinishReview$12.Z$0 = z;
                        baseActivity$finishWithResult$6$performFinishReview$12.label = 1;
                        obj = mainVM.performReviewFinish(invokeSuspend$getHyperKycResult, baseActivity$finishWithResult$6$performFinishReview$12);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        z3 = z;
                        coroutineScope2 = coroutineScope;
                        hyperKycData2 = hyperKycData;
                        num2 = num;
                    } else {
                        invokeSuspend$finish(coroutineScope, baseActivity, hyperKycData, str, num, str2);
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    z3 = baseActivity$finishWithResult$6$performFinishReview$12.Z$0;
                    String str7 = (String) baseActivity$finishWithResult$6$performFinishReview$12.L$5;
                    num2 = (Integer) baseActivity$finishWithResult$6$performFinishReview$12.L$4;
                    String str8 = (String) baseActivity$finishWithResult$6$performFinishReview$12.L$3;
                    hyperKycData2 = (HyperKycData) baseActivity$finishWithResult$6$performFinishReview$12.L$2;
                    BaseActivity baseActivity3 = (BaseActivity) baseActivity$finishWithResult$6$performFinishReview$12.L$1;
                    coroutineScope2 = (CoroutineScope) baseActivity$finishWithResult$6$performFinishReview$12.L$0;
                    ResultKt.throwOnFailure(obj);
                    str6 = str8;
                    str5 = str7;
                    baseActivity2 = baseActivity3;
                }
                final BaseActivity baseActivity4 = baseActivity2;
                final String str9 = str6;
                final Integer num3 = num2;
                final String str10 = str5;
                final HyperKycData hyperKycData3 = hyperKycData2;
                final boolean z4 = z3;
                final CoroutineScope coroutineScope3 = coroutineScope2;
                flowCollector = new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6$performFinishReview$3
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation2) {
                        return emit((NetworkUIState<Response>) obj2, (Continuation<? super Unit>) continuation2);
                    }

                    public final Object emit(NetworkUIState<Response> networkUIState, Continuation<? super Unit> continuation2) {
                        Map<String, Object> map = BaseActivity.this.getMainVM().getTextConfigData().get("commons");
                        String nullIfBlank = CoreExtsKt.nullIfBlank(map != null ? CoreExtsKt.getStringValue(map, "finish_transaction_processing_text") : null);
                        if (nullIfBlank == null) {
                            nullIfBlank = BaseActivity.this.getString(R.string.hk_finish_transaction_processing_text);
                            Intrinsics.checkNotNullExpressionValue(nullIfBlank, "getString(R.string.hk_fi…nsaction_processing_text)");
                        }
                        if (networkUIState instanceof NetworkUIState.Loading) {
                            BaseActivity.this.getMainVM().getLoadingUIStateFlow().setValue(LoadingUIState.Processing.INSTANCE);
                            ActivityExtsKt.replaceContent$default(BaseActivity.this, new LoadingFragment(), BundleKt.bundleOf(TuplesKt.to("loading_message", nullIfBlank)), false, null, 0, 28, null);
                        } else if (networkUIState instanceof NetworkUIState.Failed) {
                            BaseActivity$finishWithResult$6.invokeSuspend$finish(coroutineScope3, BaseActivity.this, hyperKycData3, "error", Boxing.boxInt(104), "Workflow completion failed");
                        } else if (networkUIState instanceof NetworkUIState.Success) {
                            BaseActivity$finishWithResult$6.invokeSuspend$finish(coroutineScope3, BaseActivity.this, hyperKycData3, str9, num3, str10);
                        } else if (networkUIState instanceof NetworkUIState.NetworkFailure) {
                            Map<String, Object> map2 = BaseActivity.this.getMainVM().getTextConfigData().get("commons");
                            String nullIfBlank2 = CoreExtsKt.nullIfBlank(map2 != null ? CoreExtsKt.getStringValue(map2, "hk_api_network_error_text") : null);
                            if (nullIfBlank2 == null) {
                                nullIfBlank2 = BaseActivity.this.getString(R.string.hk_api_error_message);
                                Intrinsics.checkNotNullExpressionValue(nullIfBlank2, "getString(R.string.hk_api_error_message)");
                            }
                            BaseActivity.this.getMainVM().setFlowFinished$hyperkyc_release(false);
                            BaseActivity.this.retryFT(str9, hyperKycData3, num3, nullIfBlank2, true, z4);
                        }
                        return Unit.INSTANCE;
                    }
                };
                baseActivity$finishWithResult$6$performFinishReview$12.L$0 = null;
                baseActivity$finishWithResult$6$performFinishReview$12.L$1 = null;
                baseActivity$finishWithResult$6$performFinishReview$12.L$2 = null;
                baseActivity$finishWithResult$6$performFinishReview$12.L$3 = null;
                baseActivity$finishWithResult$6$performFinishReview$12.L$4 = null;
                baseActivity$finishWithResult$6$performFinishReview$12.L$5 = null;
                baseActivity$finishWithResult$6$performFinishReview$12.label = 2;
                if (((Flow) obj).collect(flowCollector, baseActivity$finishWithResult$6$performFinishReview$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        baseActivity$finishWithResult$6$performFinishReview$1 = new BaseActivity$finishWithResult$6$performFinishReview$1(continuation);
        baseActivity$finishWithResult$6$performFinishReview$12 = baseActivity$finishWithResult$6$performFinishReview$1;
        obj = baseActivity$finishWithResult$6$performFinishReview$12.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = baseActivity$finishWithResult$6$performFinishReview$12.label;
        if (i != 0) {
        }
        final BaseActivity baseActivity42 = baseActivity2;
        final String str92 = str6;
        final Integer num32 = num2;
        final String str102 = str5;
        final HyperKycData hyperKycData32 = hyperKycData2;
        final boolean z42 = z3;
        final CoroutineScope coroutineScope32 = coroutineScope2;
        flowCollector = new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6$performFinishReview$3
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation2) {
                return emit((NetworkUIState<Response>) obj2, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetworkUIState<Response> networkUIState, Continuation<? super Unit> continuation2) {
                Map<String, Object> map = BaseActivity.this.getMainVM().getTextConfigData().get("commons");
                String nullIfBlank = CoreExtsKt.nullIfBlank(map != null ? CoreExtsKt.getStringValue(map, "finish_transaction_processing_text") : null);
                if (nullIfBlank == null) {
                    nullIfBlank = BaseActivity.this.getString(R.string.hk_finish_transaction_processing_text);
                    Intrinsics.checkNotNullExpressionValue(nullIfBlank, "getString(R.string.hk_fi…nsaction_processing_text)");
                }
                if (networkUIState instanceof NetworkUIState.Loading) {
                    BaseActivity.this.getMainVM().getLoadingUIStateFlow().setValue(LoadingUIState.Processing.INSTANCE);
                    ActivityExtsKt.replaceContent$default(BaseActivity.this, new LoadingFragment(), BundleKt.bundleOf(TuplesKt.to("loading_message", nullIfBlank)), false, null, 0, 28, null);
                } else if (networkUIState instanceof NetworkUIState.Failed) {
                    BaseActivity$finishWithResult$6.invokeSuspend$finish(coroutineScope32, BaseActivity.this, hyperKycData32, "error", Boxing.boxInt(104), "Workflow completion failed");
                } else if (networkUIState instanceof NetworkUIState.Success) {
                    BaseActivity$finishWithResult$6.invokeSuspend$finish(coroutineScope32, BaseActivity.this, hyperKycData32, str92, num32, str102);
                } else if (networkUIState instanceof NetworkUIState.NetworkFailure) {
                    Map<String, Object> map2 = BaseActivity.this.getMainVM().getTextConfigData().get("commons");
                    String nullIfBlank2 = CoreExtsKt.nullIfBlank(map2 != null ? CoreExtsKt.getStringValue(map2, "hk_api_network_error_text") : null);
                    if (nullIfBlank2 == null) {
                        nullIfBlank2 = BaseActivity.this.getString(R.string.hk_api_error_message);
                        Intrinsics.checkNotNullExpressionValue(nullIfBlank2, "getString(R.string.hk_api_error_message)");
                    }
                    BaseActivity.this.getMainVM().setFlowFinished$hyperkyc_release(false);
                    BaseActivity.this.retryFT(str92, hyperKycData32, num32, nullIfBlank2, true, z42);
                }
                return Unit.INSTANCE;
            }
        };
        baseActivity$finishWithResult$6$performFinishReview$12.L$0 = null;
        baseActivity$finishWithResult$6$performFinishReview$12.L$1 = null;
        baseActivity$finishWithResult$6$performFinishReview$12.L$2 = null;
        baseActivity$finishWithResult$6$performFinishReview$12.L$3 = null;
        baseActivity$finishWithResult$6$performFinishReview$12.L$4 = null;
        baseActivity$finishWithResult$6$performFinishReview$12.L$5 = null;
        baseActivity$finishWithResult$6$performFinishReview$12.label = 2;
        if (((Flow) obj).collect(flowCollector, baseActivity$finishWithResult$6$performFinishReview$12) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BaseActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6$1", f = "BaseActivity.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineScope $$this$launch;
        final /* synthetic */ HyperKycData $data;
        final /* synthetic */ Integer $errorCode;
        final /* synthetic */ String $errorMessage;
        final /* synthetic */ boolean $performReviewFinish;
        final /* synthetic */ boolean $performStatePush;
        final /* synthetic */ String $status;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ BaseActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(BaseActivity baseActivity, String str, Integer num, String str2, boolean z, HyperKycData hyperKycData, CoroutineScope coroutineScope, boolean z2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = baseActivity;
            this.$status = str;
            this.$errorCode = num;
            this.$errorMessage = str2;
            this.$performReviewFinish = z;
            this.$data = hyperKycData;
            this.$$this$launch = coroutineScope;
            this.$performStatePush = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$status, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.$data, this.$$this$launch, this.$performStatePush, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: BaseActivity.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "Lco/hyperverge/hyperkyc/ui/custom/SaveStateUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6$1$1", f = "BaseActivity.kt", i = {}, l = {314, FaxTIFFTagSet.TAG_CONSECUTIVE_BAD_LINES, 362}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C00091 extends SuspendLambda implements Function2<NetworkUIState<? extends TransactionStateResponse>, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$launch;
            final /* synthetic */ CoroutineScope $$this$launch$1;
            final /* synthetic */ HyperKycData $data;
            final /* synthetic */ Integer $errorCode;
            final /* synthetic */ String $errorMessage;
            final /* synthetic */ boolean $performReviewFinish;
            final /* synthetic */ boolean $performStatePush;
            final /* synthetic */ String $status;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ BaseActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00091(CoroutineScope coroutineScope, String str, Integer num, String str2, boolean z, BaseActivity baseActivity, HyperKycData hyperKycData, CoroutineScope coroutineScope2, boolean z2, Continuation<? super C00091> continuation) {
                super(2, continuation);
                this.$$this$launch = coroutineScope;
                this.$status = str;
                this.$errorCode = num;
                this.$errorMessage = str2;
                this.$performReviewFinish = z;
                this.this$0 = baseActivity;
                this.$data = hyperKycData;
                this.$$this$launch$1 = coroutineScope2;
                this.$performStatePush = z2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00091 c00091 = new C00091(this.$$this$launch, this.$status, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.this$0, this.$data, this.$$this$launch$1, this.$performStatePush, continuation);
                c00091.L$0 = obj;
                return c00091;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(NetworkUIState<TransactionStateResponse> networkUIState, Continuation<? super Unit> continuation) {
                return ((C00091) create(networkUIState, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(NetworkUIState<? extends TransactionStateResponse> networkUIState, Continuation<? super Unit> continuation) {
                return invoke2((NetworkUIState<TransactionStateResponse>) networkUIState, continuation);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:77:0x01a1  */
            /* JADX WARN: Removed duplicated region for block: B:85:0x01e5  */
            /* JADX WARN: Type inference failed for: r11v10, types: [T] */
            /* JADX WARN: Type inference failed for: r11v18, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r11v19 */
            /* JADX WARN: Type inference failed for: r11v5 */
            /* JADX WARN: Type inference failed for: r11v6 */
            /* JADX WARN: Type inference failed for: r11v7 */
            /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r13v1 */
            /* JADX WARN: Type inference failed for: r13v2, types: [T] */
            /* JADX WARN: Type inference failed for: r13v3 */
            /* JADX WARN: Type inference failed for: r4v34, types: [T, java.lang.Object, java.lang.String] */
            /* JADX WARN: Type inference failed for: r4v53, types: [T, java.lang.Object, java.lang.String] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                ?? canonicalName;
                Class<?> cls;
                String str;
                Object m1202constructorimpl;
                String str2;
                Class<?> cls2;
                Matcher matcher;
                String str3;
                String className;
                String className2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkUIState networkUIState = (NetworkUIState) this.L$0;
                    CoroutineScope coroutineScope = this.$$this$launch;
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r13 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher2.find()) {
                        ?? replaceAll = matcher2.replaceAll("");
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
                    String str4 = "finishWithResult() saveStateUIStateFlow: " + networkUIState;
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
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                    str2 = null;
                                } else {
                                    str2 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        r13 = substringAfterLast$default;
                                        objectRef2.element = r13;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                        if (matcher.find()) {
                                            ?? replaceAll2 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                            objectRef2.element = replaceAll2;
                                        }
                                        if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                            str3 = (String) objectRef2.element;
                                        } else {
                                            str3 = ((String) objectRef2.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb2 = new StringBuilder();
                                        String str5 = "finishWithResult() saveStateUIStateFlow: " + networkUIState;
                                        sb2.append(str5 != null ? str5 : "null ");
                                        sb2.append(' ');
                                        sb2.append("");
                                        Log.println(3, str3, sb2.toString());
                                    }
                                }
                                String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? str2 : cls2.getCanonicalName();
                                if (canonicalName2 != null) {
                                    r13 = canonicalName2;
                                }
                                objectRef2.element = r13;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef2.element).length() > 23) {
                                }
                                str3 = (String) objectRef2.element;
                                StringBuilder sb22 = new StringBuilder();
                                String str52 = "finishWithResult() saveStateUIStateFlow: " + networkUIState;
                                sb22.append(str52 != null ? str52 : "null ");
                                sb22.append(' ');
                                sb22.append("");
                                Log.println(3, str3, sb22.toString());
                            }
                        }
                    }
                    if (networkUIState instanceof NetworkUIState.Success) {
                        NetworkUIState.Success success = (NetworkUIState.Success) networkUIState;
                        int statusCode = ((TransactionStateResponse) success.getData()).getStatusCode();
                        if (statusCode == 200) {
                            this.label = 1;
                            if (BaseActivity$finishWithResult$6.invokeSuspend$performFinishReview(this.$$this$launch$1, this.this$0, this.$data, this.$performStatePush, this.$status, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (statusCode == 500) {
                            CoroutineScope coroutineScope2 = this.$$this$launch$1;
                            BaseActivity baseActivity = this.this$0;
                            HyperKycData hyperKycData = this.$data;
                            boolean z = this.$performStatePush;
                            Integer boxInt = Boxing.boxInt(104);
                            String errorCode = ((TransactionStateResponse) success.getData()).getErrorCode();
                            String str6 = errorCode == null ? "" : errorCode;
                            this.label = 2;
                            if (BaseActivity$finishWithResult$6.invokeSuspend$performFinishReview(coroutineScope2, baseActivity, hyperKycData, z, "error", boxInt, str6, this.$performReviewFinish, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (networkUIState instanceof NetworkUIState.Loading) {
                        ActivityExtsKt.replaceContent$default(this.this$0, new LoadingFragment(), BundleKt.bundleOf(TuplesKt.to("loading_message", "")), false, null, 0, 28, null);
                    } else if (!(networkUIState instanceof NetworkUIState.Failed)) {
                        if (networkUIState instanceof NetworkUIState.NetworkFailure) {
                            this.this$0.getMainVM().setFlowFinished$hyperkyc_release(false);
                            this.this$0.retryFT(this.$status, this.$data, this.$errorCode, this.$errorMessage, this.$performReviewFinish, true);
                        } else if (networkUIState == null) {
                            this.label = 3;
                            if (BaseActivity$finishWithResult$6.invokeSuspend$performFinishReview(this.$$this$launch$1, this.this$0, this.$data, this.$performStatePush, this.$status, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                } else {
                    if (i != 1 && i != 2 && i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                this.label = 1;
                if (FlowKt.collectLatest(this.this$0.getMainVM().getSaveStateUIStateFlow$hyperkyc_release(), new C00091(coroutineScope, this.$status, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.this$0, this.$data, this.$$this$launch, this.$performStatePush, null), this) == coroutine_suspended) {
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

    /* JADX WARN: Can't wrap try/catch for region: R(22:1|(2:(1:90)(1:87)|(1:89))|7|(1:9)|10|(1:14)|15|(1:17)|18|(9:39|40|41|42|43|44|(1:46)|47|(12:49|(9:51|(2:(1:77)(1:73)|(1:75)(1:76))(1:57)|58|(1:60)|61|(1:65)|66|(1:68)|69)|21|(1:23)(1:38)|24|25|26|27|(1:29)|30|(1:32)|33))|20|21|(0)(0)|24|25|26|27|(0)|30|(0)|33|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0245, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0246, code lost:
    
        r1 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final HyperKycResult invokeSuspend$getHyperKycResult(CoroutineScope coroutineScope, BaseActivity baseActivity, HyperKycData hyperKycData, String str, Integer num, String str2, boolean z) {
        String canonicalName;
        Class<?> cls;
        String str3;
        Object m1202constructorimpl;
        String str4;
        String str5;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        Object obj;
        String packageName;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str6 = "getHyperKycResult() called with: status = " + str + ", errorCode = " + num + ", errorMsg = " + str2 + ", replaceLargeValues = " + z;
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str3 = ", replaceLargeValues = ";
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
                    String packageName2 = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                    }
                    str4 = "packageName";
                    String str7 = str4;
                    HyperKycResult hyperKycResult = new HyperKycResult(str, baseActivity.getHyperKycConfig().getTransactionId$hyperkyc_release(), baseActivity.getMainVM().getResultsDetailMap$hyperkyc_release(), num, CollectionsKt.contains(CollectionsKt.listOf((Object[]) new Integer[]{103, 3}), num) ? "Workflow cancelled by user" : str2, null, null, null, null, null, 992, null);
                    Result.Companion companion4 = Result.INSTANCE;
                    Object obj2 = Result.m1202constructorimpl(baseActivity.getMainVM().getCurrentModuleId$hyperkyc_release());
                    obj = obj2;
                    if (Result.m1208isFailureimpl(obj)) {
                    }
                    hyperKycResult.setLatestModule((String) obj);
                    hyperKycResult.setLatestCondition$hyperkyc_release(baseActivity.getMainVM().getLatestCondition());
                    hyperKycResult.setLatestRuleRaw$hyperkyc_release(baseActivity.getMainVM().getLatestRuleRaw());
                    hyperKycResult.setLatestRule$hyperkyc_release(baseActivity.getMainVM().getLatestRule());
                    packageName = baseActivity.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName, str7);
                    if (StringsKt.startsWith$default(packageName, "co.hyperverge", false, 2, (Object) null)) {
                    }
                    return hyperKycResult;
                }
            } catch (Throwable th2) {
                th = th2;
                str3 = ", replaceLargeValues = ";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName22 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName22, "packageName");
                str4 = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName22, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
                        str5 = canonicalName2 == null ? "N/A" : canonicalName2;
                    } else {
                        str5 = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                    if (matcher2.find()) {
                        str5 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                    }
                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str5 = str5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str8 = "getHyperKycResult() called with: status = " + str + ", errorCode = " + num + ", errorMsg = " + str2 + str3 + z;
                    if (str8 == null) {
                        str8 = "null ";
                    }
                    sb2.append(str8);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str5, sb2.toString());
                }
                String str72 = str4;
                HyperKycResult hyperKycResult2 = new HyperKycResult(str, baseActivity.getHyperKycConfig().getTransactionId$hyperkyc_release(), baseActivity.getMainVM().getResultsDetailMap$hyperkyc_release(), num, CollectionsKt.contains(CollectionsKt.listOf((Object[]) new Integer[]{103, 3}), num) ? "Workflow cancelled by user" : str2, null, null, null, null, null, 992, null);
                Result.Companion companion42 = Result.INSTANCE;
                Object obj22 = Result.m1202constructorimpl(baseActivity.getMainVM().getCurrentModuleId$hyperkyc_release());
                obj = obj22;
                if (Result.m1208isFailureimpl(obj)) {
                    obj = null;
                }
                hyperKycResult2.setLatestModule((String) obj);
                hyperKycResult2.setLatestCondition$hyperkyc_release(baseActivity.getMainVM().getLatestCondition());
                hyperKycResult2.setLatestRuleRaw$hyperkyc_release(baseActivity.getMainVM().getLatestRuleRaw());
                hyperKycResult2.setLatestRule$hyperkyc_release(baseActivity.getMainVM().getLatestRule());
                packageName = baseActivity.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, str72);
                if (StringsKt.startsWith$default(packageName, "co.hyperverge", false, 2, (Object) null)) {
                    hyperKycResult2.setHyperKycData$hyperkyc_release(hyperKycData);
                }
                return hyperKycResult2;
            }
        }
        str4 = "packageName";
        String str722 = str4;
        HyperKycResult hyperKycResult22 = new HyperKycResult(str, baseActivity.getHyperKycConfig().getTransactionId$hyperkyc_release(), baseActivity.getMainVM().getResultsDetailMap$hyperkyc_release(), num, CollectionsKt.contains(CollectionsKt.listOf((Object[]) new Integer[]{103, 3}), num) ? "Workflow cancelled by user" : str2, null, null, null, null, null, 992, null);
        Result.Companion companion422 = Result.INSTANCE;
        Object obj222 = Result.m1202constructorimpl(baseActivity.getMainVM().getCurrentModuleId$hyperkyc_release());
        obj = obj222;
        if (Result.m1208isFailureimpl(obj)) {
        }
        hyperKycResult22.setLatestModule((String) obj);
        hyperKycResult22.setLatestCondition$hyperkyc_release(baseActivity.getMainVM().getLatestCondition());
        hyperKycResult22.setLatestRuleRaw$hyperkyc_release(baseActivity.getMainVM().getLatestRuleRaw());
        hyperKycResult22.setLatestRule$hyperkyc_release(baseActivity.getMainVM().getLatestRule());
        packageName = baseActivity.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, str722);
        if (StringsKt.startsWith$default(packageName, "co.hyperverge", false, 2, (Object) null)) {
        }
        return hyperKycResult22;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x014b, code lost:
    
        if (r0 != null) goto L57;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void invokeSuspend$finish(CoroutineScope coroutineScope, BaseActivity baseActivity, HyperKycData hyperKycData, String str, Integer num, String str2) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str3;
        String str4;
        String str5;
        Matcher matcher;
        String str6;
        Class<?> cls2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str7 = "finish() called with: status = " + str + ", errorCode = " + num + ", errorMsg = " + str2;
        if (str7 == null) {
            str7 = "null ";
        }
        sb.append(str7);
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
                        str3 = null;
                    } else {
                        str3 = null;
                        str4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    str4 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? str3 : cls2.getCanonicalName();
                    if (str4 == null) {
                        str5 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                        if (matcher.find()) {
                            str5 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                        }
                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str5 = str5.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str6 = "finish() called with: status = " + str + ", errorCode = " + num + ", errorMsg = " + str2;
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb2.append(str6);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str5, sb2.toString());
                    }
                    str5 = str4;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                    if (matcher.find()) {
                    }
                    if (str5.length() > 23) {
                        str5 = str5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str6 = "finish() called with: status = " + str + ", errorCode = " + num + ", errorMsg = " + str2;
                    if (str6 == null) {
                    }
                    sb22.append(str6);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str5, sb22.toString());
                }
            }
        }
        Intent intent = new Intent();
        HyperKycResult invokeSuspend$getHyperKycResult = invokeSuspend$getHyperKycResult(coroutineScope, baseActivity, hyperKycData, str, num, str2, true);
        String json = baseActivity.getMainVM().getGson().toJson(invokeSuspend$getHyperKycResult);
        Intrinsics.checkNotNullExpressionValue(json, "mainVM.gson.toJson(hyperKycResult)");
        String saveToInternalStorage = FileExtsKt.saveToInternalStorage(baseActivity, HyperKyc.RESULTS_CACHE_DIR, HyperKycResult.ARG_CACHE_FILE_PATH_KEY, json);
        if (saveToInternalStorage != null) {
            intent.putExtra(HyperKycResult.ARG_CACHE_FILE_PATH_KEY, saveToInternalStorage);
        } else {
            intent.putExtra(HyperKycResult.ARG_KEY, invokeSuspend$getHyperKycResult);
        }
        if (!CollectionsKt.listOf((Object[]) new String[]{"error", HyperKycStatus.USER_CANCELLED}).contains(str)) {
            baseActivity.getMainVM().deleteSavedTransactionStates$hyperkyc_release();
        }
        if (!Intrinsics.areEqual(str, "error")) {
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            File filesDir = baseActivity.getApplicationContext().getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir, "applicationContext.filesDir");
            companion4.deleteLogFolder(filesDir);
        }
        baseActivity.setResult(-1, intent);
        baseActivity.finish();
    }
}
