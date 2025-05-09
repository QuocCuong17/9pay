package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.KycDocument;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.state.TransactionState;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$startWorkFlow$2", f = "MainVM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MainVM$startWorkFlow$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Boolean, ? extends String>>, Object> {
    final /* synthetic */ HyperKycConfig $config;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$startWorkFlow$2(MainVM mainVM, HyperKycConfig hyperKycConfig, Continuation<? super MainVM$startWorkFlow$2> continuation) {
        super(2, continuation);
        this.this$0 = mainVM;
        this.$config = hyperKycConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$startWorkFlow$2 mainVM$startWorkFlow$2 = new MainVM$startWorkFlow$2(this.this$0, this.$config, continuation);
        mainVM$startWorkFlow$2.L$0 = obj;
        return mainVM$startWorkFlow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Boolean, ? extends String>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Pair<Boolean, String>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Boolean, String>> continuation) {
        return ((MainVM$startWorkFlow$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:150:0x0636, code lost:
    
        if (r8 != null) goto L270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0817, code lost:
    
        if (r12 != null) goto L355;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0a29, code lost:
    
        if (r10 != null) goto L432;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x0c10, code lost:
    
        if (r12 != null) goto L509;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x02db, code lost:
    
        if (r5 != null) goto L134;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0d92  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x11c9  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x11cf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x0fbc  */
    /* JADX WARN: Removed duplicated region for block: B:572:0x100a  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x1026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:594:0x10ce  */
    /* JADX WARN: Removed duplicated region for block: B:602:0x111b  */
    /* JADX WARN: Removed duplicated region for block: B:604:0x111e  */
    /* JADX WARN: Removed duplicated region for block: B:624:0x0fa6  */
    /* JADX WARN: Removed duplicated region for block: B:628:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:638:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:641:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x01fe  */
    /* JADX WARN: Type inference failed for: r0v124, types: [T, co.hyperverge.hyperkyc.ui.models.WorkflowUIState] */
    /* JADX WARN: Type inference failed for: r10v25, types: [T] */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v41 */
    /* JADX WARN: Type inference failed for: r12v24, types: [T] */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v35, types: [T] */
    /* JADX WARN: Type inference failed for: r12v36 */
    /* JADX WARN: Type inference failed for: r12v37 */
    /* JADX WARN: Type inference failed for: r12v46 */
    /* JADX WARN: Type inference failed for: r12v47 */
    /* JADX WARN: Type inference failed for: r1v54, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v51, types: [T] */
    /* JADX WARN: Type inference failed for: r2v61, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v63, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v72, types: [T, co.hyperverge.hyperkyc.ui.models.WorkflowUIState$DocCapture] */
    /* JADX WARN: Type inference failed for: r2v82, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v89 */
    /* JADX WARN: Type inference failed for: r3v118, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v147 */
    /* JADX WARN: Type inference failed for: r3v148 */
    /* JADX WARN: Type inference failed for: r3v149 */
    /* JADX WARN: Type inference failed for: r3v152, types: [T] */
    /* JADX WARN: Type inference failed for: r3v162, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v164, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v192 */
    /* JADX WARN: Type inference failed for: r3v193 */
    /* JADX WARN: Type inference failed for: r3v194 */
    /* JADX WARN: Type inference failed for: r3v197, types: [T] */
    /* JADX WARN: Type inference failed for: r3v207, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v209, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v220, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v231, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v236 */
    /* JADX WARN: Type inference failed for: r3v237 */
    /* JADX WARN: Type inference failed for: r3v238 */
    /* JADX WARN: Type inference failed for: r3v77 */
    /* JADX WARN: Type inference failed for: r3v78 */
    /* JADX WARN: Type inference failed for: r3v79 */
    /* JADX WARN: Type inference failed for: r3v82, types: [T] */
    /* JADX WARN: Type inference failed for: r3v92, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v94, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v14, types: [T] */
    /* JADX WARN: Type inference failed for: r5v158 */
    /* JADX WARN: Type inference failed for: r5v159 */
    /* JADX WARN: Type inference failed for: r5v163 */
    /* JADX WARN: Type inference failed for: r5v164 */
    /* JADX WARN: Type inference failed for: r5v165 */
    /* JADX WARN: Type inference failed for: r5v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v50, types: [T] */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r5v65 */
    /* JADX WARN: Type inference failed for: r5v66 */
    /* JADX WARN: Type inference failed for: r5v69, types: [T] */
    /* JADX WARN: Type inference failed for: r5v79, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v81, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v10, types: [T] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v93, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v95, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v96 */
    /* JADX WARN: Type inference failed for: r8v11, types: [T] */
    /* JADX WARN: Type inference failed for: r8v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v21, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v59, types: [T] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v75 */
    /* JADX WARN: Type inference failed for: r8v76 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v90 */
    /* JADX WARN: Type inference failed for: r8v91 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21, types: [T] */
    /* JADX WARN: Type inference failed for: r9v22 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object obj2;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str2;
        ?? canonicalName2;
        Class<?> cls2;
        String str3;
        String className;
        String accessToken;
        String appId;
        String appKey;
        String defaultLangCode;
        TransactionState retrieveStateFromLocal;
        boolean isLocalWorkflowResumeEnabled;
        boolean invokeSuspend$shouldResumeWorkflow;
        boolean z;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        ?? canonicalName3;
        Class<?> cls3;
        Matcher matcher;
        String str4;
        String str5;
        Object m1202constructorimpl2;
        String str6;
        ?? r9;
        Class<?> cls4;
        Matcher matcher2;
        String str7;
        String className2;
        String className3;
        List<String> moduleExecutionOrder;
        String str8;
        String str9;
        ?? r5;
        String str10;
        Object m1202constructorimpl3;
        String str11;
        String str12;
        MainVM$startWorkFlow$2 mainVM$startWorkFlow$2;
        ?? canonicalName4;
        Class<?> cls5;
        String str13;
        String className4;
        List list;
        ?? createWorkflowUIState;
        String str14;
        CoroutineScope coroutineScope;
        String str15;
        String str16;
        MainVM mainVM;
        List list2;
        Ref.ObjectRef objectRef2;
        String str17;
        MainVM mainVM2;
        ?? r10;
        String str18;
        Object m1202constructorimpl4;
        String str19;
        Ref.ObjectRef objectRef3;
        ?? canonicalName5;
        Class<?> cls6;
        String str20;
        String className5;
        Ref.ObjectRef objectRef4;
        String str21;
        ?? r12;
        String str22;
        Object m1202constructorimpl5;
        ?? canonicalName6;
        String str23;
        String className6;
        Iterator it;
        MainVM mainVM3;
        Iterator it2;
        int i;
        Map map;
        List list3;
        String className7;
        List list4;
        Class<?> cls7;
        String className8;
        MainVM mainVM4;
        String str24;
        Ref.ObjectRef objectRef5;
        ?? r8;
        String str25;
        Object m1202constructorimpl6;
        String str26;
        Ref.ObjectRef objectRef6;
        ?? canonicalName7;
        Class<?> cls8;
        String str27;
        String className9;
        MainVM mainVM5;
        List list5;
        Ref.ObjectRef objectRef7;
        String str28;
        List<KycCountry> list6;
        ?? r122;
        String str29;
        Object m1202constructorimpl7;
        ?? canonicalName8;
        Class<?> cls9;
        String str30;
        String className10;
        Class<?> cls10;
        String className11;
        Class<?> cls11;
        String className12;
        LinkedHashMap linkedHashMap;
        TransactionState.ModuleData moduleData;
        Class<?> cls12;
        String className13;
        String className14;
        MainVM$startWorkFlow$2 mainVM$startWorkFlow$22 = this;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (mainVM$startWorkFlow$22.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope2 = (CoroutineScope) mainVM$startWorkFlow$22.L$0;
        HyperKycConfig hyperKycConfig = mainVM$startWorkFlow$22.$config;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        Ref.ObjectRef objectRef8 = new Ref.ObjectRef();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className14 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className14, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
            canonicalName = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? 0 : cls.getCanonicalName();
            if (canonicalName == 0) {
                canonicalName = "N/A";
            }
        }
        objectRef8.element = canonicalName;
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
        if (matcher3.find()) {
            ?? replaceAll = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
            objectRef8.element = replaceAll;
        }
        if (((String) objectRef8.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
            str = (String) objectRef8.element;
        } else {
            str = ((String) objectRef8.element).substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(str);
        sb.append(" - ");
        String str31 = "startWorkFlow() called with: hyperKycConfig = [" + hyperKycConfig + ']';
        if (str31 == null) {
            str31 = "null ";
        }
        sb.append(str31);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        String str32 = "null cannot be cast to non-null type android.app.Application";
        if (CoreExtsKt.isRelease()) {
            charSequence = "co.hyperverge";
            obj2 = "N/A";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                obj2 = "N/A";
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
                    if (!CoreExtsKt.isDebug()) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                obj2 = "N/A";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (!CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                charSequence = "co.hyperverge";
                str2 = "null ";
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    Ref.ObjectRef objectRef9 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? 0 : cls2.getCanonicalName();
                        if (canonicalName2 == 0) {
                            canonicalName2 = obj2;
                        }
                    }
                    objectRef9.element = canonicalName2;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef9.element);
                    if (matcher4.find()) {
                        ?? replaceAll2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                        objectRef9.element = replaceAll2;
                    }
                    if (((String) objectRef9.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str3 = (String) objectRef9.element;
                    } else {
                        str3 = ((String) objectRef9.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str33 = "startWorkFlow() called with: hyperKycConfig = [" + hyperKycConfig + ']';
                    if (str33 == null) {
                        str33 = str2;
                    }
                    sb2.append(str33);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str3, sb2.toString());
                }
                HashMap inputs = mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getInputs();
                HyperKycConfig hyperKycConfig2 = mainVM$startWorkFlow$22.$config;
                inputs.put("transactionId", hyperKycConfig2.getTransactionId$hyperkyc_release());
                accessToken = hyperKycConfig2.getAccessToken();
                if (accessToken != null) {
                    inputs.put("accessToken", accessToken);
                }
                appId = hyperKycConfig2.getAppId();
                if (appId != null) {
                    inputs.put("appId", appId);
                }
                appKey = hyperKycConfig2.getAppKey();
                if (appKey != null) {
                    inputs.put(HyperKycConfig.APP_KEY, appKey);
                }
                defaultLangCode = hyperKycConfig2.getDefaultLangCode();
                if (defaultLangCode != null) {
                    inputs.put(HyperKycConfig.DEFAULT_LANG_CODE, defaultLangCode);
                }
                inputs.put("workflowId", hyperKycConfig2.getWorkflowId$hyperkyc_release());
                retrieveStateFromLocal = mainVM$startWorkFlow$22.this$0.retrieveStateFromLocal();
                if (mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().isServerSideResumeEnabled$hyperkyc_release()) {
                    isLocalWorkflowResumeEnabled = mainVM$startWorkFlow$22.this$0.isLocalWorkflowResumeEnabled();
                    if (isLocalWorkflowResumeEnabled) {
                        invokeSuspend$shouldResumeWorkflow = invokeSuspend$shouldResumeWorkflow(mainVM$startWorkFlow$22.this$0, retrieveStateFromLocal);
                    }
                    invokeSuspend$shouldResumeWorkflow = false;
                } else {
                    if (retrieveStateFromLocal != null) {
                        List<String> moduleExecutionOrder2 = retrieveStateFromLocal.getModuleExecutionOrder();
                        if (!(moduleExecutionOrder2 == null || moduleExecutionOrder2.isEmpty())) {
                            invokeSuspend$shouldResumeWorkflow = true;
                        }
                    }
                    invokeSuspend$shouldResumeWorkflow = false;
                }
                if (invokeSuspend$shouldResumeWorkflow && retrieveStateFromLocal != null) {
                    moduleExecutionOrder = retrieveStateFromLocal.getModuleExecutionOrder();
                    if (!(moduleExecutionOrder != null || moduleExecutionOrder.isEmpty())) {
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        Ref.ObjectRef objectRef10 = new Ref.ObjectRef();
                        z = invokeSuspend$shouldResumeWorkflow;
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement4 == null || (className13 = stackTraceElement4.getClassName()) == null) {
                            str8 = "packageName";
                            str9 = "Throwable().stackTrace";
                        } else {
                            str8 = "packageName";
                            str9 = "Throwable().stackTrace";
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className13, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            r5 = substringAfterLast$default;
                        }
                        String canonicalName9 = (coroutineScope2 == null || (cls12 = coroutineScope2.getClass()) == null) ? null : cls12.getCanonicalName();
                        r5 = canonicalName9 == null ? obj2 : canonicalName9;
                        objectRef10.element = r5;
                        Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef10.element);
                        if (matcher5.find()) {
                            ?? replaceAll3 = matcher5.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                            objectRef10.element = replaceAll3;
                        }
                        if (((String) objectRef10.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str10 = (String) objectRef10.element;
                        } else {
                            str10 = ((String) objectRef10.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str10);
                        sb3.append(" - ");
                        String str34 = "startWorkFlow() resuming with state: " + retrieveStateFromLocal;
                        if (str34 == null) {
                            str34 = str2;
                        }
                        sb3.append(str34);
                        sb3.append(' ');
                        sb3.append("");
                        companion4.log(level2, sb3.toString());
                        if (CoreExtsKt.isRelease()) {
                            mainVM$startWorkFlow$2 = this;
                            str11 = str9;
                            str12 = str8;
                        } else {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th3) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String str35 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                                str12 = str8;
                                Intrinsics.checkNotNullExpressionValue(str35, str12);
                                if (StringsKt.contains$default((CharSequence) str35, charSequence, false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef11 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    str11 = str9;
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str11);
                                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                        canonicalName4 = (coroutineScope2 == null || (cls5 = coroutineScope2.getClass()) == null) ? 0 : cls5.getCanonicalName();
                                        if (canonicalName4 == 0) {
                                            canonicalName4 = obj2;
                                        }
                                    }
                                    objectRef11.element = canonicalName4;
                                    Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef11.element);
                                    if (matcher6.find()) {
                                        ?? replaceAll4 = matcher6.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                        objectRef11.element = replaceAll4;
                                    }
                                    if (((String) objectRef11.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str13 = (String) objectRef11.element;
                                    } else {
                                        str13 = ((String) objectRef11.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    String str36 = "startWorkFlow() resuming with state: " + retrieveStateFromLocal;
                                    sb4.append(str36 == null ? str2 : str36);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str13, sb4.toString());
                                } else {
                                    str11 = str9;
                                }
                            } else {
                                str11 = str9;
                                str12 = str8;
                            }
                            mainVM$startWorkFlow$2 = this;
                        }
                        mainVM$startWorkFlow$2.this$0.setModuleExecutionOrder$hyperkyc_release(CollectionsKt.toMutableList((Collection) retrieveStateFromLocal.getModuleExecutionOrder()));
                        List<String> workflowExecutionOrder = retrieveStateFromLocal.getWorkflowExecutionOrder();
                        if (!(workflowExecutionOrder == null || workflowExecutionOrder.isEmpty())) {
                            mainVM$startWorkFlow$2.this$0.setWorkflowExecutionOrder$hyperkyc_release(CollectionsKt.toMutableList((Collection) retrieveStateFromLocal.getWorkflowExecutionOrder()));
                        }
                        if (!mainVM$startWorkFlow$2.this$0.getHyperKycConfig$hyperkyc_release().isServerSideResumeEnabled$hyperkyc_release()) {
                            Iterator<String> it3 = retrieveStateFromLocal.getModuleExecutionOrder().iterator();
                            int i2 = 0;
                            while (true) {
                                if (!it3.hasNext()) {
                                    i2 = -1;
                                    break;
                                }
                                String next = it3.next();
                                Map<String, TransactionState.ModuleData> moduleData2 = retrieveStateFromLocal.getModuleData();
                                Long boxLong = (moduleData2 == null || (moduleData = moduleData2.get(next)) == null) ? null : Boxing.boxLong(moduleData.getExpireAt());
                                if (boxLong != null && boxLong.longValue() < System.currentTimeMillis()) {
                                    break;
                                }
                                i2++;
                            }
                            if (i2 > -1) {
                                mainVM$startWorkFlow$2.this$0.setModuleExecutionOrder$hyperkyc_release(CollectionsKt.toMutableList((Collection) retrieveStateFromLocal.getModuleExecutionOrder()).subList(0, i2 + 1));
                                int size = mainVM$startWorkFlow$2.this$0.getWorkflowExecutionOrder$hyperkyc_release().size() - i2;
                                MainVM mainVM6 = mainVM$startWorkFlow$2.this$0;
                                for (int i3 = 0; i3 < size; i3++) {
                                    CollectionsKt.removeLastOrNull(mainVM6.getWorkflowExecutionOrder$hyperkyc_release());
                                }
                                Map<String, TransactionState.ModuleData> moduleData3 = retrieveStateFromLocal.getModuleData();
                                if (moduleData3 != null) {
                                    MainVM mainVM7 = mainVM$startWorkFlow$2.this$0;
                                    linkedHashMap = new LinkedHashMap();
                                    Iterator<Map.Entry<String, TransactionState.ModuleData>> it4 = moduleData3.entrySet().iterator();
                                    while (it4.hasNext()) {
                                        Map.Entry<String, TransactionState.ModuleData> next2 = it4.next();
                                        Iterator<Map.Entry<String, TransactionState.ModuleData>> it5 = it4;
                                        if (mainVM7.getModuleExecutionOrder$hyperkyc_release().contains(next2.getKey()) || CollectionsKt.contains(mainVM7.getModuleExecutionOrder$hyperkyc_release(), next2.getValue().getParentModuleId())) {
                                            linkedHashMap.put(next2.getKey(), next2.getValue());
                                        }
                                        it4 = it5;
                                    }
                                } else {
                                    linkedHashMap = null;
                                }
                                retrieveStateFromLocal.setModuleData(linkedHashMap);
                            }
                            Map<String, TransactionState.ModuleData> moduleData4 = retrieveStateFromLocal.getModuleData();
                            if (moduleData4 != null) {
                                mainVM$startWorkFlow$2.this$0.moduleDataMap = new HashMap(moduleData4);
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                        MainVM mainVM8 = mainVM$startWorkFlow$2.this$0;
                        mainVM8.setHyperKycData$hyperkyc_release(mainVM8.createHyperKycData$hyperkyc_release(retrieveStateFromLocal));
                        mainVM$startWorkFlow$2.this$0.workflowUIStateList = new ArrayList();
                        List<String> moduleExecutionOrder$hyperkyc_release = mainVM$startWorkFlow$2.this$0.getModuleExecutionOrder$hyperkyc_release();
                        MainVM mainVM9 = mainVM$startWorkFlow$2.this$0;
                        Iterator it6 = moduleExecutionOrder$hyperkyc_release.iterator();
                        while (it6.hasNext()) {
                            String str37 = (String) it6.next();
                            Ref.ObjectRef objectRef12 = new Ref.ObjectRef();
                            createWorkflowUIState = mainVM9.createWorkflowUIState(str37);
                            objectRef12.element = createWorkflowUIState;
                            Iterator it7 = it6;
                            WorkflowModule moduleForId$hyperkyc_release = mainVM9.getModuleForId$hyperkyc_release(str37);
                            if (moduleForId$hyperkyc_release == null) {
                                throw new IllegalStateException(("Module not found for id: " + str37).toString());
                            }
                            String type = moduleForId$hyperkyc_release.getType();
                            if (!Intrinsics.areEqual(type, "countries")) {
                                String str38 = str11;
                                WorkflowModule workflowModule = moduleForId$hyperkyc_release;
                                MainVM mainVM10 = mainVM9;
                                if (!Intrinsics.areEqual(type, WorkflowModule.TYPE_DOCUMENT)) {
                                    str14 = str12;
                                    coroutineScope = coroutineScope2;
                                    str15 = str32;
                                    str16 = str38;
                                    mainVM = mainVM10;
                                    WorkflowUIState workflowUIState = (WorkflowUIState) objectRef12.element;
                                    if (workflowUIState != null) {
                                        list2 = mainVM.workflowUIStateList;
                                        if (list2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("workflowUIStateList");
                                            list2 = null;
                                        }
                                        Boxing.boxBoolean(list2.add(workflowUIState));
                                    }
                                } else {
                                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb5 = new StringBuilder();
                                    Ref.ObjectRef objectRef13 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str38);
                                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                    if (stackTraceElement6 == null || (className8 = stackTraceElement6.getClassName()) == null) {
                                        objectRef2 = objectRef12;
                                        str17 = str38;
                                        mainVM2 = mainVM10;
                                    } else {
                                        objectRef2 = objectRef12;
                                        str17 = str38;
                                        mainVM2 = mainVM10;
                                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        r10 = substringAfterLast$default2;
                                    }
                                    String canonicalName10 = (coroutineScope2 == null || (cls7 = coroutineScope2.getClass()) == null) ? null : cls7.getCanonicalName();
                                    r10 = canonicalName10 == null ? obj2 : canonicalName10;
                                    objectRef13.element = r10;
                                    Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef13.element);
                                    if (matcher7.find()) {
                                        ?? replaceAll5 = matcher7.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                                        objectRef13.element = replaceAll5;
                                    }
                                    if (((String) objectRef13.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str18 = (String) objectRef13.element;
                                    } else {
                                        str18 = ((String) objectRef13.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str18, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb5.append(str18);
                                    sb5.append(" - ");
                                    sb5.append("startWorkFlow() resuming with document module");
                                    sb5.append(' ');
                                    sb5.append("");
                                    companion7.log(level3, sb5.toString());
                                    if (CoreExtsKt.isRelease()) {
                                        objectRef3 = objectRef2;
                                        str19 = str17;
                                    } else {
                                        try {
                                            Result.Companion companion8 = Result.INSTANCE;
                                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                            Intrinsics.checkNotNull(invoke3, str32);
                                            m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                        } catch (Throwable th4) {
                                            Result.Companion companion9 = Result.INSTANCE;
                                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                                        }
                                        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                            m1202constructorimpl4 = "";
                                        }
                                        String str39 = (String) m1202constructorimpl4;
                                        if (CoreExtsKt.isDebug()) {
                                            Intrinsics.checkNotNullExpressionValue(str39, str12);
                                            if (StringsKt.contains$default((CharSequence) str39, charSequence, false, 2, (Object) null)) {
                                                Ref.ObjectRef objectRef14 = new Ref.ObjectRef();
                                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                str19 = str17;
                                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str19);
                                                StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                if (stackTraceElement7 == null || (className5 = stackTraceElement7.getClassName()) == null || (canonicalName5 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                    canonicalName5 = (coroutineScope2 == null || (cls6 = coroutineScope2.getClass()) == null) ? 0 : cls6.getCanonicalName();
                                                    if (canonicalName5 == 0) {
                                                        canonicalName5 = obj2;
                                                    }
                                                }
                                                objectRef14.element = canonicalName5;
                                                Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef14.element);
                                                if (matcher8.find()) {
                                                    ?? replaceAll6 = matcher8.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(replaceAll6, "replaceAll(\"\")");
                                                    objectRef14.element = replaceAll6;
                                                }
                                                if (((String) objectRef14.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                    str20 = (String) objectRef14.element;
                                                } else {
                                                    str20 = ((String) objectRef14.element).substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str20, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                Log.println(3, str20, "startWorkFlow() resuming with document module ");
                                                objectRef3 = objectRef2;
                                            }
                                        }
                                        str19 = str17;
                                        objectRef3 = objectRef2;
                                    }
                                    Object obj3 = objectRef3.element;
                                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.models.WorkflowUIState.PickDocument");
                                    WorkflowUIState.PickDocument pickDocument = (WorkflowUIState.PickDocument) obj3;
                                    int size2 = pickDocument.getDocuments().size();
                                    if (size2 == 0) {
                                        throw new IllegalStateException("documents cannot be empty".toString());
                                    }
                                    if (size2 != 1) {
                                        WorkflowUIState workflowUIState2 = (WorkflowUIState) objectRef3.element;
                                        if (workflowUIState2 != null) {
                                            list4 = mainVM2.workflowUIStateList;
                                            if (list4 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("workflowUIStateList");
                                                list4 = null;
                                            }
                                            Boxing.boxBoolean(list4.add(workflowUIState2));
                                        }
                                        str14 = str12;
                                        coroutineScope = coroutineScope2;
                                        str15 = str32;
                                        str16 = str19;
                                        mainVM = mainVM2;
                                    } else {
                                        HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
                                        HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                                        StringBuilder sb6 = new StringBuilder();
                                        Ref.ObjectRef objectRef15 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace7, str19);
                                        StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                                        if (stackTraceElement8 == null || (className7 = stackTraceElement8.getClassName()) == null) {
                                            coroutineScope = coroutineScope2;
                                            objectRef4 = objectRef3;
                                            str21 = str19;
                                        } else {
                                            coroutineScope = coroutineScope2;
                                            objectRef4 = objectRef3;
                                            str21 = str19;
                                            String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            r12 = substringAfterLast$default3;
                                        }
                                        Class<?> cls13 = pickDocument.getClass();
                                        String canonicalName11 = cls13 != null ? cls13.getCanonicalName() : null;
                                        r12 = canonicalName11 == null ? obj2 : canonicalName11;
                                        objectRef15.element = r12;
                                        Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef15.element);
                                        if (matcher9.find()) {
                                            ?? replaceAll7 = matcher9.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll7, "replaceAll(\"\")");
                                            objectRef15.element = replaceAll7;
                                        }
                                        if (((String) objectRef15.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                            str22 = (String) objectRef15.element;
                                        } else {
                                            str22 = ((String) objectRef15.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str22, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        sb6.append(str22);
                                        sb6.append(" - ");
                                        sb6.append("startWorkFlow() resuming with single document");
                                        sb6.append(' ');
                                        sb6.append("");
                                        companion10.log(level4, sb6.toString());
                                        if (!CoreExtsKt.isRelease()) {
                                            try {
                                                Result.Companion companion11 = Result.INSTANCE;
                                                Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                Intrinsics.checkNotNull(invoke4, str32);
                                                m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                            } catch (Throwable th5) {
                                                Result.Companion companion12 = Result.INSTANCE;
                                                m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                                            }
                                            if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                m1202constructorimpl5 = "";
                                            }
                                            String str40 = (String) m1202constructorimpl5;
                                            if (CoreExtsKt.isDebug()) {
                                                Intrinsics.checkNotNullExpressionValue(str40, str12);
                                                if (StringsKt.contains$default((CharSequence) str40, charSequence, false, 2, (Object) null)) {
                                                    Ref.ObjectRef objectRef16 = new Ref.ObjectRef();
                                                    StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                                    str16 = str21;
                                                    Intrinsics.checkNotNullExpressionValue(stackTrace8, str16);
                                                    StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                                    if (stackTraceElement9 == null || (className6 = stackTraceElement9.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                        Class<?> cls14 = pickDocument.getClass();
                                                        canonicalName6 = cls14 != null ? cls14.getCanonicalName() : 0;
                                                        if (canonicalName6 == 0) {
                                                            canonicalName6 = obj2;
                                                        }
                                                    }
                                                    objectRef16.element = canonicalName6;
                                                    Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef16.element);
                                                    if (matcher10.find()) {
                                                        ?? replaceAll8 = matcher10.replaceAll("");
                                                        Intrinsics.checkNotNullExpressionValue(replaceAll8, "replaceAll(\"\")");
                                                        objectRef16.element = replaceAll8;
                                                    }
                                                    if (((String) objectRef16.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                        str23 = (String) objectRef16.element;
                                                    } else {
                                                        str23 = ((String) objectRef16.element).substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str23, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    }
                                                    Log.println(3, str23, "startWorkFlow() resuming with single document ");
                                                    KycDocument kycDocument = (KycDocument) CollectionsKt.first((List) pickDocument.getDocuments());
                                                    it = kycDocument.getSides().iterator();
                                                    int i4 = 0;
                                                    while (it.hasNext()) {
                                                        Object next3 = it.next();
                                                        int i5 = i4 + 1;
                                                        if (i4 < 0) {
                                                            CollectionsKt.throwIndexOverflow();
                                                        }
                                                        String str41 = (String) next3;
                                                        HVDocConfig sideToDocConfig = HyperSnapBridgeKt.sideToDocConfig(str41, pickDocument.getAllowUpload(), pickDocument.getSupportedUploadFileTypes(), pickDocument.getShowInstruction(), pickDocument.getShowReview(), kycDocument.readBarcode(str41), pickDocument.getDisableBarcodeSkip(), pickDocument.getBarcodeSkipDelay(), pickDocument.getShouldAutoCapture(), pickDocument.getAutoCaptureDuration());
                                                        String str42 = str12;
                                                        String str43 = str32;
                                                        WorkflowModule workflowModule2 = workflowModule;
                                                        if (i4 == 0) {
                                                            mainVM3 = mainVM2;
                                                            it2 = it;
                                                            i = i5;
                                                            sideToDocConfig.setShowModuleBackButton(MainVM.shouldShowBackButton$default(mainVM3, workflowModule2, false, 1, null));
                                                        } else {
                                                            mainVM3 = mainVM2;
                                                            it2 = it;
                                                            i = i5;
                                                        }
                                                        Map mutableMap = MapsKt.toMutableMap(pickDocument.getOcrParams());
                                                        Map mutableMap2 = MapsKt.toMutableMap(pickDocument.getOcrHeaders());
                                                        mutableMap.put("expectedDocumentSide", str41);
                                                        mutableMap2.put("expectedDocumentSide", str41);
                                                        boolean disableOCR = kycDocument.disableOCR(str41);
                                                        String subType = workflowModule2.getSubType();
                                                        String countryId = pickDocument.getCountryId();
                                                        String url = pickDocument.getUrl();
                                                        List<Integer> allowedStatusCodes = pickDocument.getAllowedStatusCodes();
                                                        boolean z2 = pickDocument.getDisableOCR() || disableOCR;
                                                        map = mainVM3.textConfigData;
                                                        ?? docCapture = new WorkflowUIState.DocCapture(str37, subType, kycDocument, sideToDocConfig, str41, countryId, url, allowedStatusCodes, z2, (Map) map.get(workflowModule2.getSubType()), pickDocument.getOcrParams(), pickDocument.getOcrHeaders(), pickDocument.getEnableOverlay(), pickDocument.getValidateSignature(), pickDocument.getShowEndState(), pickDocument.isSuccess());
                                                        Ref.ObjectRef objectRef17 = objectRef4;
                                                        objectRef17.element = docCapture;
                                                        WorkflowUIState workflowUIState3 = (WorkflowUIState) objectRef17.element;
                                                        if (workflowUIState3 != null) {
                                                            list3 = mainVM3.workflowUIStateList;
                                                            if (list3 == null) {
                                                                Intrinsics.throwUninitializedPropertyAccessException("workflowUIStateList");
                                                                list3 = null;
                                                            }
                                                            list3.add(workflowUIState3);
                                                            Unit unit2 = Unit.INSTANCE;
                                                        }
                                                        objectRef4 = objectRef17;
                                                        it = it2;
                                                        str12 = str42;
                                                        str32 = str43;
                                                        workflowModule = workflowModule2;
                                                        int i6 = i;
                                                        mainVM2 = mainVM3;
                                                        i4 = i6;
                                                    }
                                                    str14 = str12;
                                                    str15 = str32;
                                                    mainVM = mainVM2;
                                                    Unit unit3 = Unit.INSTANCE;
                                                }
                                            }
                                        }
                                        str16 = str21;
                                        KycDocument kycDocument2 = (KycDocument) CollectionsKt.first((List) pickDocument.getDocuments());
                                        it = kycDocument2.getSides().iterator();
                                        int i42 = 0;
                                        while (it.hasNext()) {
                                        }
                                        str14 = str12;
                                        str15 = str32;
                                        mainVM = mainVM2;
                                        Unit unit32 = Unit.INSTANCE;
                                    }
                                }
                            } else {
                                HyperLogger.Level level5 = HyperLogger.Level.DEBUG;
                                HyperLogger companion13 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb7 = new StringBuilder();
                                Ref.ObjectRef objectRef18 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace9, str11);
                                StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                                if (stackTraceElement10 == null || (className12 = stackTraceElement10.getClassName()) == null) {
                                    mainVM4 = mainVM9;
                                    str24 = str11;
                                    objectRef5 = objectRef12;
                                } else {
                                    mainVM4 = mainVM9;
                                    str24 = str11;
                                    objectRef5 = objectRef12;
                                    String substringAfterLast$default4 = StringsKt.substringAfterLast$default(className12, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    r8 = substringAfterLast$default4;
                                }
                                String canonicalName12 = (coroutineScope2 == null || (cls11 = coroutineScope2.getClass()) == null) ? null : cls11.getCanonicalName();
                                r8 = canonicalName12 == null ? obj2 : canonicalName12;
                                objectRef18.element = r8;
                                Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef18.element);
                                if (matcher11.find()) {
                                    ?? replaceAll9 = matcher11.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll9, "replaceAll(\"\")");
                                    objectRef18.element = replaceAll9;
                                }
                                if (((String) objectRef18.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str25 = (String) objectRef18.element;
                                } else {
                                    str25 = ((String) objectRef18.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str25, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb7.append(str25);
                                sb7.append(" - ");
                                sb7.append("startWorkFlow() resuming with country module");
                                sb7.append(' ');
                                sb7.append("");
                                companion13.log(level5, sb7.toString());
                                if (CoreExtsKt.isRelease()) {
                                    objectRef6 = objectRef5;
                                    str26 = str24;
                                } else {
                                    try {
                                        Result.Companion companion14 = Result.INSTANCE;
                                        Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke5, str32);
                                        m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                                    } catch (Throwable th6) {
                                        Result.Companion companion15 = Result.INSTANCE;
                                        m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                        m1202constructorimpl6 = "";
                                    }
                                    String str44 = (String) m1202constructorimpl6;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(str44, str12);
                                        if (StringsKt.contains$default((CharSequence) str44, charSequence, false, 2, (Object) null)) {
                                            Ref.ObjectRef objectRef19 = new Ref.ObjectRef();
                                            StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                                            str26 = str24;
                                            Intrinsics.checkNotNullExpressionValue(stackTrace10, str26);
                                            StackTraceElement stackTraceElement11 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                                            if (stackTraceElement11 == null || (className9 = stackTraceElement11.getClassName()) == null || (canonicalName7 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                canonicalName7 = (coroutineScope2 == null || (cls8 = coroutineScope2.getClass()) == null) ? 0 : cls8.getCanonicalName();
                                                if (canonicalName7 == 0) {
                                                    canonicalName7 = obj2;
                                                }
                                            }
                                            objectRef19.element = canonicalName7;
                                            Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef19.element);
                                            if (matcher12.find()) {
                                                ?? replaceAll10 = matcher12.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(replaceAll10, "replaceAll(\"\")");
                                                objectRef19.element = replaceAll10;
                                            }
                                            if (((String) objectRef19.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                str27 = (String) objectRef19.element;
                                            } else {
                                                str27 = ((String) objectRef19.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str27, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            Log.println(3, str27, "startWorkFlow() resuming with country module ");
                                            objectRef6 = objectRef5;
                                        }
                                    }
                                    str26 = str24;
                                    objectRef6 = objectRef5;
                                }
                                Object obj4 = objectRef6.element;
                                WorkflowUIState.PickCountry pickCountry = obj4 instanceof WorkflowUIState.PickCountry ? (WorkflowUIState.PickCountry) obj4 : null;
                                List<KycCountry> countries = pickCountry != null ? pickCountry.getCountries() : null;
                                Integer boxInt = countries != null ? Boxing.boxInt(countries.size()) : null;
                                if (boxInt != null && boxInt.intValue() == 0) {
                                    throw new IllegalStateException("countries cannot be empty".toString());
                                }
                                if (boxInt == null || boxInt.intValue() != 1) {
                                    mainVM5 = mainVM4;
                                    WorkflowUIState workflowUIState4 = (WorkflowUIState) objectRef6.element;
                                    if (workflowUIState4 != null) {
                                        list5 = mainVM5.workflowUIStateList;
                                        if (list5 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("workflowUIStateList");
                                            list5 = null;
                                        }
                                        Boxing.boxBoolean(list5.add(workflowUIState4));
                                    }
                                } else {
                                    HyperLogger.Level level6 = HyperLogger.Level.DEBUG;
                                    HyperLogger companion16 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb8 = new StringBuilder();
                                    Ref.ObjectRef objectRef20 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace11 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace11, str26);
                                    StackTraceElement stackTraceElement12 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace11);
                                    if (stackTraceElement12 == null || (className11 = stackTraceElement12.getClassName()) == null) {
                                        objectRef7 = objectRef6;
                                        str28 = str26;
                                        list6 = countries;
                                    } else {
                                        objectRef7 = objectRef6;
                                        str28 = str26;
                                        list6 = countries;
                                        String substringAfterLast$default5 = StringsKt.substringAfterLast$default(className11, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        r122 = substringAfterLast$default5;
                                    }
                                    String canonicalName13 = (coroutineScope2 == null || (cls10 = coroutineScope2.getClass()) == null) ? null : cls10.getCanonicalName();
                                    r122 = canonicalName13 == null ? obj2 : canonicalName13;
                                    objectRef20.element = r122;
                                    Matcher matcher13 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef20.element);
                                    if (matcher13.find()) {
                                        ?? replaceAll11 = matcher13.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll11, "replaceAll(\"\")");
                                        objectRef20.element = replaceAll11;
                                    }
                                    if (((String) objectRef20.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str29 = (String) objectRef20.element;
                                    } else {
                                        str29 = ((String) objectRef20.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str29, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb8.append(str29);
                                    sb8.append(" - ");
                                    sb8.append("startWorkFlow() resuming with single country");
                                    sb8.append(' ');
                                    sb8.append("");
                                    companion16.log(level6, sb8.toString());
                                    if (!CoreExtsKt.isRelease()) {
                                        try {
                                            Result.Companion companion17 = Result.INSTANCE;
                                            Object invoke6 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                            Intrinsics.checkNotNull(invoke6, str32);
                                            m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                                        } catch (Throwable th7) {
                                            Result.Companion companion18 = Result.INSTANCE;
                                            m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                                        }
                                        if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                            m1202constructorimpl7 = "";
                                        }
                                        String str45 = (String) m1202constructorimpl7;
                                        if (CoreExtsKt.isDebug()) {
                                            Intrinsics.checkNotNullExpressionValue(str45, str12);
                                            if (StringsKt.contains$default((CharSequence) str45, charSequence, false, 2, (Object) null)) {
                                                Ref.ObjectRef objectRef21 = new Ref.ObjectRef();
                                                StackTraceElement[] stackTrace12 = new Throwable().getStackTrace();
                                                str26 = str28;
                                                Intrinsics.checkNotNullExpressionValue(stackTrace12, str26);
                                                StackTraceElement stackTraceElement13 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace12);
                                                if (stackTraceElement13 == null || (className10 = stackTraceElement13.getClassName()) == null || (canonicalName8 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                    canonicalName8 = (coroutineScope2 == null || (cls9 = coroutineScope2.getClass()) == null) ? 0 : cls9.getCanonicalName();
                                                    if (canonicalName8 == 0) {
                                                        canonicalName8 = obj2;
                                                    }
                                                }
                                                objectRef21.element = canonicalName8;
                                                Matcher matcher14 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef21.element);
                                                if (matcher14.find()) {
                                                    ?? replaceAll12 = matcher14.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(replaceAll12, "replaceAll(\"\")");
                                                    objectRef21.element = replaceAll12;
                                                }
                                                if (((String) objectRef21.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                    str30 = (String) objectRef21.element;
                                                } else {
                                                    str30 = ((String) objectRef21.element).substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str30, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                Log.println(3, str30, "startWorkFlow() resuming with single country ");
                                                mainVM4.getWorkflowExecutionOrder$hyperkyc_release().add(moduleForId$hyperkyc_release.getId());
                                                AnalyticsLogger analyticsLogger = AnalyticsLogger.INSTANCE;
                                                String id2 = moduleForId$hyperkyc_release.getId();
                                                mainVM5 = mainVM4;
                                                analyticsLogger.logModuleStartedEvent$hyperkyc_release(mainVM5.getAnalyticsForModule$hyperkyc_release(id2, AnalyticsLogger.Events.MODULE_STARTED));
                                                mainVM5.setSelectedCountry((KycCountry) CollectionsKt.first((List) list6));
                                                objectRef7.element = null;
                                            }
                                        }
                                    }
                                    str26 = str28;
                                    mainVM4.getWorkflowExecutionOrder$hyperkyc_release().add(moduleForId$hyperkyc_release.getId());
                                    AnalyticsLogger analyticsLogger2 = AnalyticsLogger.INSTANCE;
                                    String id22 = moduleForId$hyperkyc_release.getId();
                                    mainVM5 = mainVM4;
                                    analyticsLogger2.logModuleStartedEvent$hyperkyc_release(mainVM5.getAnalyticsForModule$hyperkyc_release(id22, AnalyticsLogger.Events.MODULE_STARTED));
                                    mainVM5.setSelectedCountry((KycCountry) CollectionsKt.first((List) list6));
                                    objectRef7.element = null;
                                }
                                str14 = str12;
                                coroutineScope = coroutineScope2;
                                str15 = str32;
                                str16 = str26;
                                mainVM = mainVM5;
                            }
                            str11 = str16;
                            mainVM9 = mainVM;
                            it6 = it7;
                            coroutineScope2 = coroutineScope;
                            str12 = str14;
                            str32 = str15;
                            mainVM$startWorkFlow$2 = this;
                        }
                        mainVM$startWorkFlow$22 = mainVM$startWorkFlow$2;
                        MainVM mainVM11 = mainVM$startWorkFlow$22.this$0;
                        list = mainVM11.workflowUIStateList;
                        if (list == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("workflowUIStateList");
                            list = null;
                        }
                        MainVM mainVM12 = mainVM$startWorkFlow$22.this$0;
                        Iterator it8 = list.iterator();
                        int i7 = 0;
                        while (true) {
                            if (!it8.hasNext()) {
                                i7 = -1;
                                break;
                            }
                            if (Intrinsics.areEqual(((WorkflowUIState) it8.next()).getTag(), CollectionsKt.last((List) mainVM12.getModuleExecutionOrder$hyperkyc_release()))) {
                                break;
                            }
                            i7++;
                        }
                        Integer boxInt2 = Boxing.boxInt(i7);
                        Integer num = boxInt2.intValue() > -1 ? boxInt2 : null;
                        mainVM11.currentFlowPos = num != null ? num.intValue() : 0;
                        MainVM mainVM13 = mainVM$startWorkFlow$22.this$0;
                        mainVM13.currentFlowId = mainVM13.getCurrentModuleId$hyperkyc_release();
                        mainVM$startWorkFlow$22.this$0.initJourneyIdIfNotSet();
                        mainVM$startWorkFlow$22.this$0.deleteSavedTransactionStates$hyperkyc_release();
                        HyperSnapBridgeKt.endUserSession();
                        HyperSnapBridgeKt.startUserSession(mainVM$startWorkFlow$22.$config.getTransactionId$hyperkyc_release());
                        if (z) {
                            mainVM$startWorkFlow$22.this$0.updateUiState();
                        } else {
                            mainVM$startWorkFlow$22.this$0.flowForward();
                        }
                        return TuplesKt.to(Boxing.boxBoolean(true), "");
                    }
                }
                z = invokeSuspend$shouldResumeWorkflow;
                MainVM mainVM14 = mainVM$startWorkFlow$22.this$0;
                HyperLogger.Level level7 = HyperLogger.Level.DEBUG;
                HyperLogger companion19 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb9 = new StringBuilder();
                objectRef = new Ref.ObjectRef();
                StackTraceElement[] stackTrace13 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace13, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace13);
                if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                    canonicalName3 = (coroutineScope2 != null || (cls3 = coroutineScope2.getClass()) == null) ? 0 : cls3.getCanonicalName();
                    if (canonicalName3 == 0) {
                        canonicalName3 = obj2;
                    }
                }
                objectRef.element = canonicalName3;
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher.find()) {
                    ?? replaceAll13 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll13, "replaceAll(\"\")");
                    objectRef.element = replaceAll13;
                }
                if (((String) objectRef.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                    str4 = (String) objectRef.element;
                } else {
                    str4 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb9.append(str4);
                sb9.append(" - ");
                str5 = "startWorkFlow() starting fresh for transactionId : " + mainVM14.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release();
                if (str5 == null) {
                    str5 = str2;
                }
                sb9.append(str5);
                sb9.append(' ');
                sb9.append("");
                companion19.log(level7, sb9.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion20 = Result.INSTANCE;
                        Object invoke7 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke7, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke7).getPackageName());
                    } catch (Throwable th8) {
                        Result.Companion companion21 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName3 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef22 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace14 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace14, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement14 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace14);
                            if (stackTraceElement14 == null || (className2 = stackTraceElement14.getClassName()) == null) {
                                str6 = null;
                            } else {
                                str6 = null;
                                String substringAfterLast$default6 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default6 != null) {
                                    r9 = substringAfterLast$default6;
                                    objectRef22.element = r9;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef22.element);
                                    if (matcher2.find()) {
                                        ?? replaceAll14 = matcher2.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll14, "replaceAll(\"\")");
                                        objectRef22.element = replaceAll14;
                                    }
                                    if (((String) objectRef22.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                        str7 = (String) objectRef22.element;
                                    } else {
                                        str7 = ((String) objectRef22.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb10 = new StringBuilder();
                                    String str46 = "startWorkFlow() starting fresh for transactionId : " + mainVM14.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release();
                                    sb10.append(str46 != null ? str2 : str46);
                                    sb10.append(' ');
                                    sb10.append("");
                                    Log.println(3, str7, sb10.toString());
                                }
                            }
                            String canonicalName14 = (coroutineScope2 == null || (cls4 = coroutineScope2.getClass()) == null) ? str6 : cls4.getCanonicalName();
                            r9 = canonicalName14 == null ? obj2 : canonicalName14;
                            objectRef22.element = r9;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef22.element);
                            if (matcher2.find()) {
                            }
                            if (((String) objectRef22.element).length() > 23) {
                            }
                            str7 = (String) objectRef22.element;
                            StringBuilder sb102 = new StringBuilder();
                            String str462 = "startWorkFlow() starting fresh for transactionId : " + mainVM14.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release();
                            sb102.append(str462 != null ? str2 : str462);
                            sb102.append(' ');
                            sb102.append("");
                            Log.println(3, str7, sb102.toString());
                        }
                    }
                }
                mainVM$startWorkFlow$22.this$0.initJourneyIdIfNotSet();
                mainVM$startWorkFlow$22.this$0.workflowUIStateList = new ArrayList();
                AnalyticsLogger.INSTANCE.setCommonProperties$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("appId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getAppId()), TuplesKt.to("transactionId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release()), TuplesKt.to("workflowId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release()), TuplesKt.to("journeyId", mainVM$startWorkFlow$22.this$0.getJourneyId$hyperkyc_release()), TuplesKt.to("uniqueId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getUniqueId())));
                AnalyticsLogger.INSTANCE.logStartWorkflowEvent$hyperkyc_release(new LinkedHashMap());
                mainVM$startWorkFlow$22.this$0.deleteSavedTransactionStates$hyperkyc_release();
                HyperSnapBridgeKt.endUserSession();
                HyperSnapBridgeKt.startUserSession(mainVM$startWorkFlow$22.$config.getTransactionId$hyperkyc_release());
                if (z) {
                }
                return TuplesKt.to(Boxing.boxBoolean(true), "");
            }
            charSequence = "co.hyperverge";
        }
        str2 = "null ";
        HashMap inputs2 = mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getInputs();
        HyperKycConfig hyperKycConfig22 = mainVM$startWorkFlow$22.$config;
        inputs2.put("transactionId", hyperKycConfig22.getTransactionId$hyperkyc_release());
        accessToken = hyperKycConfig22.getAccessToken();
        if (accessToken != null) {
        }
        appId = hyperKycConfig22.getAppId();
        if (appId != null) {
        }
        appKey = hyperKycConfig22.getAppKey();
        if (appKey != null) {
        }
        defaultLangCode = hyperKycConfig22.getDefaultLangCode();
        if (defaultLangCode != null) {
        }
        inputs2.put("workflowId", hyperKycConfig22.getWorkflowId$hyperkyc_release());
        retrieveStateFromLocal = mainVM$startWorkFlow$22.this$0.retrieveStateFromLocal();
        if (mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().isServerSideResumeEnabled$hyperkyc_release()) {
        }
        if (invokeSuspend$shouldResumeWorkflow) {
            moduleExecutionOrder = retrieveStateFromLocal.getModuleExecutionOrder();
            if (!(moduleExecutionOrder != null || moduleExecutionOrder.isEmpty())) {
            }
        }
        z = invokeSuspend$shouldResumeWorkflow;
        MainVM mainVM142 = mainVM$startWorkFlow$22.this$0;
        HyperLogger.Level level72 = HyperLogger.Level.DEBUG;
        HyperLogger companion192 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb92 = new StringBuilder();
        objectRef = new Ref.ObjectRef();
        StackTraceElement[] stackTrace132 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace132, "Throwable().stackTrace");
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace132);
        if (stackTraceElement != null) {
        }
        if (coroutineScope2 != null) {
        }
        if (canonicalName3 == 0) {
        }
        objectRef.element = canonicalName3;
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
        if (matcher.find()) {
        }
        if (((String) objectRef.element).length() > 23) {
        }
        str4 = (String) objectRef.element;
        sb92.append(str4);
        sb92.append(" - ");
        str5 = "startWorkFlow() starting fresh for transactionId : " + mainVM142.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release();
        if (str5 == null) {
        }
        sb92.append(str5);
        sb92.append(' ');
        sb92.append("");
        companion192.log(level72, sb92.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        mainVM$startWorkFlow$22.this$0.initJourneyIdIfNotSet();
        mainVM$startWorkFlow$22.this$0.workflowUIStateList = new ArrayList();
        AnalyticsLogger.INSTANCE.setCommonProperties$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("appId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getAppId()), TuplesKt.to("transactionId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release()), TuplesKt.to("workflowId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release()), TuplesKt.to("journeyId", mainVM$startWorkFlow$22.this$0.getJourneyId$hyperkyc_release()), TuplesKt.to("uniqueId", mainVM$startWorkFlow$22.this$0.getHyperKycConfig$hyperkyc_release().getUniqueId())));
        AnalyticsLogger.INSTANCE.logStartWorkflowEvent$hyperkyc_release(new LinkedHashMap());
        mainVM$startWorkFlow$22.this$0.deleteSavedTransactionStates$hyperkyc_release();
        HyperSnapBridgeKt.endUserSession();
        HyperSnapBridgeKt.startUserSession(mainVM$startWorkFlow$22.$config.getTransactionId$hyperkyc_release());
        if (z) {
        }
        return TuplesKt.to(Boxing.boxBoolean(true), "");
    }

    private static final boolean invokeSuspend$shouldResumeWorkflow(MainVM mainVM, TransactionState transactionState) {
        boolean hasValidSavedWorkflowState;
        hasValidSavedWorkflowState = mainVM.hasValidSavedWorkflowState();
        if (hasValidSavedWorkflowState && transactionState != null) {
            TransactionState.Metadata metadata = transactionState.getMetadata();
            if (Intrinsics.areEqual(metadata != null ? metadata.getAppId() : null, mainVM.getHyperKycConfig$hyperkyc_release().getAppId())) {
                TransactionState.Metadata metadata2 = transactionState.getMetadata();
                if (Intrinsics.areEqual(metadata2 != null ? metadata2.getWorkflowId() : null, mainVM.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release()) && Intrinsics.areEqual(transactionState.getMetadata().getTransactionId(), mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release()) && Intrinsics.areEqual(transactionState.getMetadata().getWorkflowHash(), mainVM.getHyperKycConfig$hyperkyc_release().getWorkflowConfig$hyperkyc_release().getHash()) && Intrinsics.areEqual(transactionState.getMetadata().getInputs(), mainVM.getHyperKycConfig$hyperkyc_release().getInputs())) {
                    return true;
                }
            }
        }
        return false;
    }
}
