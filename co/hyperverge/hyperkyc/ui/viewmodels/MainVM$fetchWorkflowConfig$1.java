package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowConfig;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$fetchWorkflowConfig$1", f = "MainVM.kt", i = {0, 1, 2, 3}, l = {522, 526, 534, 536, 542}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$fetchWorkflowConfig$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends WorkflowConfig>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ String $workflowId;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$fetchWorkflowConfig$1(String str, String str2, MainVM mainVM, Continuation<? super MainVM$fetchWorkflowConfig$1> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$workflowId = str2;
        this.this$0 = mainVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$fetchWorkflowConfig$1 mainVM$fetchWorkflowConfig$1 = new MainVM$fetchWorkflowConfig$1(this.$appId, this.$workflowId, this.this$0, continuation);
        mainVM$fetchWorkflowConfig$1.L$0 = obj;
        return mainVM$fetchWorkflowConfig$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends WorkflowConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<WorkflowConfig>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<WorkflowConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$fetchWorkflowConfig$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(1:(1:(7:(2:6|(2:8|(3:10|11|12)(2:14|15))(1:16))(1:93)|17|18|19|(15:21|(1:92)(1:25)|(1:33)(1:30)|(1:32)|34|(1:36)|37|(1:91)(1:41)|42|(1:44)|45|(6:53|54|55|(1:57)|58|(2:60|(9:62|(2:(1:87)(1:84)|(1:86))|68|(1:70)|71|(1:80)(1:75)|76|(1:78)|79)))|(1:48)(1:52)|49|(1:51))|11|12)(8:94|95|96|97|98|99|100|(30:102|(1:192)(1:106)|(1:114)(1:111)|(1:113)|115|(1:117)(1:191)|118|(22:122|123|(1:125)|126|(1:128)(1:188)|(1:130)(1:187)|131|132|133|134|(1:136)|137|(2:139|(20:141|(2:(1:182)(1:179)|(1:181))|147|(1:149)|150|(1:175)(1:154)|155|(1:157)|158|(1:160)(1:174)|(1:162)(1:173)|163|164|(2:166|(1:168))(2:170|(1:172))|169|18|19|(0)|11|12))|183|164|(0)(0)|169|18|19|(0)|11|12)|189|123|(0)|126|(0)(0)|(0)(0)|131|132|133|134|(0)|137|(0)|183|164|(0)(0)|169|18|19|(0)|11|12)(5:193|19|(0)|11|12)))(1:200))(13:216|(1:284)(1:220)|(1:229)(1:225)|(1:227)(1:228)|230|(1:232)|233|(1:283)(1:237)|238|(6:245|246|247|(1:249)|250|(2:252|(12:254|(1:279)(1:258)|(1:266)(1:263)|(1:265)|267|(1:269)|270|(4:277|276|241|(1:243)(1:244))|275|276|241|(0)(0))))|240|241|(0)(0))|201|202|203|204|205|206|207|(1:209)|98|99|100|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(30:102|(1:192)(1:106)|(1:114)(1:111)|(1:113)|115|(1:117)(1:191)|118|(22:122|123|(1:125)|126|(1:128)(1:188)|(1:130)(1:187)|131|132|133|134|(1:136)|137|(2:139|(20:141|(2:(1:182)(1:179)|(1:181))|147|(1:149)|150|(1:175)(1:154)|155|(1:157)|158|(1:160)(1:174)|(1:162)(1:173)|163|164|(2:166|(1:168))(2:170|(1:172))|169|18|19|(0)|11|12))|183|164|(0)(0)|169|18|19|(0)|11|12)|189|123|(0)|126|(0)(0)|(0)(0)|131|132|133|134|(0)|137|(0)|183|164|(0)(0)|169|18|19|(0)|11|12) */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0318, code lost:
    
        if (r5 != 0) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x03f6, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x03f7, code lost:
    
        r1 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x02b9, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x02bb, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x02bc, code lost:
    
        r1 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x02bf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x02c0, code lost:
    
        r13 = "";
        r25 = "replaceAll(\"\")";
        r19 = r4;
        r9 = r5;
        r10 = "this as java.lang.String…ing(startIndex, endIndex)";
        r1 = r7;
        r11 = " - ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x00e6, code lost:
    
        if (r10 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x01e7, code lost:
    
        if (r4 != 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0583, code lost:
    
        if (r1 != 0) goto L225;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0271 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0272  */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v66, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v76, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v96, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v100 */
    /* JADX WARN: Type inference failed for: r1v101 */
    /* JADX WARN: Type inference failed for: r1v105, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v117 */
    /* JADX WARN: Type inference failed for: r1v118 */
    /* JADX WARN: Type inference failed for: r1v119 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v28, types: [T] */
    /* JADX WARN: Type inference failed for: r1v37, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v39, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v49, types: [T] */
    /* JADX WARN: Type inference failed for: r1v71 */
    /* JADX WARN: Type inference failed for: r1v72 */
    /* JADX WARN: Type inference failed for: r1v73 */
    /* JADX WARN: Type inference failed for: r1v76, types: [T] */
    /* JADX WARN: Type inference failed for: r1v86, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v88, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v98, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v99 */
    /* JADX WARN: Type inference failed for: r4v12, types: [T] */
    /* JADX WARN: Type inference failed for: r4v22, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v29, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v48 */
    /* JADX WARN: Type inference failed for: r5v21, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v33, types: [T] */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v40, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v46 */
    /* JADX WARN: Type inference failed for: r5v47, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v50 */
    /* JADX WARN: Type inference failed for: r5v54, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v57 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        CharSequence charSequence;
        String str2;
        String str3;
        Object m1202constructorimpl;
        ?? r4;
        String str4;
        Class<?> cls;
        String className;
        MainVM$fetchWorkflowConfig$1 mainVM$fetchWorkflowConfig$1;
        Object obj2;
        FlowCollector flowCollector;
        Class<?> cls2;
        String className2;
        String str5;
        String str6;
        FlowCollector flowCollector2;
        String str7;
        String str8;
        Object obj3;
        String str9;
        File cacheDir;
        Object fetchWorkflowConfig$hyperkyc_release$default;
        Object m1202constructorimpl2;
        FlowCollector flowCollector3;
        Throwable m1205exceptionOrNullimpl;
        Object obj4;
        Object obj5;
        String str10;
        String str11;
        String str12;
        Object obj6;
        Object obj7;
        String str13;
        ?? r5;
        int i;
        String str14;
        String str15;
        String localizedMessage;
        String str16;
        String str17;
        Object obj8;
        Object obj9;
        ?? canonicalName;
        Class<?> cls3;
        String str18;
        String str19;
        String className3;
        Class<?> cls4;
        String className4;
        String str20;
        Object obj10;
        String str21;
        ?? r1;
        String str22;
        String str23;
        ?? canonicalName2;
        Class<?> cls5;
        String str24;
        String className5;
        Object obj11;
        Object obj12;
        Class<?> cls6;
        String className6;
        FlowCollector flowCollector4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        String str25 = "Throwable().stackTrace";
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector5 = (FlowCollector) this.L$0;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str = "Throwable().stackTrace";
                charSequence = "co.hyperverge";
            } else {
                str = "Throwable().stackTrace";
                charSequence = "co.hyperverge";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str2 = substringAfterLast$default;
            }
            String canonicalName3 = (flowCollector5 == null || (cls2 = flowCollector5.getClass()) == null) ? null : cls2.getCanonicalName();
            str2 = canonicalName3 == null ? "N/A" : canonicalName3;
            objectRef.element = str2;
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
            sb.append("fetchWorkflowConfig() called");
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
                    if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str25 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str25);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                            r4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r4 = (flowCollector5 == null || (cls = flowCollector5.getClass()) == null) ? 0 : cls.getCanonicalName();
                        if (r4 == 0) {
                            r4 = "N/A";
                        }
                        objectRef2.element = r4;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            Log.println(3, str4, "fetchWorkflowConfig() called ");
                            mainVM$fetchWorkflowConfig$1 = this;
                            mainVM$fetchWorkflowConfig$1.L$0 = flowCollector5;
                            mainVM$fetchWorkflowConfig$1.label = 1;
                            obj2 = coroutine_suspended;
                            if (flowCollector5.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchWorkflowConfig$1) == obj2) {
                                return obj2;
                            }
                            flowCollector = flowCollector5;
                        }
                        str4 = (String) objectRef2.element;
                        Log.println(3, str4, "fetchWorkflowConfig() called ");
                        mainVM$fetchWorkflowConfig$1 = this;
                        mainVM$fetchWorkflowConfig$1.L$0 = flowCollector5;
                        mainVM$fetchWorkflowConfig$1.label = 1;
                        obj2 = coroutine_suspended;
                        if (flowCollector5.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchWorkflowConfig$1) == obj2) {
                        }
                    }
                }
            }
            str25 = str;
            mainVM$fetchWorkflowConfig$1 = this;
            mainVM$fetchWorkflowConfig$1.L$0 = flowCollector5;
            mainVM$fetchWorkflowConfig$1.label = 1;
            obj2 = coroutine_suspended;
            if (flowCollector5.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchWorkflowConfig$1) == obj2) {
            }
        } else {
            if (i2 != 1) {
                if (i2 == 2) {
                    FlowCollector flowCollector6 = (FlowCollector) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        fetchWorkflowConfig$hyperkyc_release$default = obj;
                        flowCollector2 = flowCollector6;
                        str6 = "replaceAll(\"\")";
                        str7 = "Throwable().stackTrace";
                        str9 = " - ";
                        obj3 = coroutine_suspended;
                        charSequence = "co.hyperverge";
                        str5 = "";
                        str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                        m1202constructorimpl2 = Result.m1202constructorimpl((WorkflowConfig) fetchWorkflowConfig$hyperkyc_release$default);
                    } catch (Throwable th2) {
                        th = th2;
                        flowCollector2 = flowCollector6;
                        str6 = "replaceAll(\"\")";
                        str7 = "Throwable().stackTrace";
                        str9 = " - ";
                        obj3 = coroutine_suspended;
                        charSequence = "co.hyperverge";
                        str5 = "";
                        str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                        Result.Companion companion4 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        Object obj13 = m1202constructorimpl2;
                        flowCollector3 = flowCollector2;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj13);
                        if (m1205exceptionOrNullimpl == null) {
                        }
                    }
                    Object obj132 = m1202constructorimpl2;
                    flowCollector3 = flowCollector2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj132);
                    if (m1205exceptionOrNullimpl == null) {
                        obj4 = obj3;
                        obj5 = obj132;
                        str10 = str7;
                        str11 = str9;
                        str12 = str6;
                        if (Result.m1209isSuccessimpl(obj5)) {
                        }
                        return Unit.INSTANCE;
                    }
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str7);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null) {
                        obj6 = obj3;
                        obj7 = obj132;
                        str13 = str7;
                    } else {
                        obj6 = obj3;
                        obj7 = obj132;
                        str13 = str7;
                        r5 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r5 = (flowCollector3 == null || (cls4 = flowCollector3.getClass()) == null) ? 0 : cls4.getCanonicalName();
                    if (r5 == 0) {
                        r5 = "N/A";
                    }
                    objectRef3.element = r5;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher3.find()) {
                        ?? replaceAll3 = matcher3.replaceAll(str5);
                        str12 = str6;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str12);
                        objectRef3.element = replaceAll3;
                    } else {
                        str12 = str6;
                    }
                    if (((String) objectRef3.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                        String substring = ((String) objectRef3.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substring, str8);
                        str14 = substring;
                        i = 0;
                        sb2.append(str14);
                        sb2.append(str9);
                        str15 = "fetchWorkflowConfig: failed " + m1205exceptionOrNullimpl.getMessage();
                        if (str15 == null) {
                            str15 = "null ";
                        }
                        sb2.append(str15);
                        sb2.append(' ');
                        localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage == null) {
                            str16 = '\n' + localizedMessage;
                        } else {
                            str16 = str5;
                        }
                        sb2.append(str16);
                        companion5.log(level2, sb2.toString());
                        CoreExtsKt.isRelease();
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[i]).invoke(null, new Object[i]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        String str26 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        str17 = str26;
                        if (Result.m1208isFailureimpl(str17)) {
                            str17 = str5;
                        }
                        String packageName2 = str17;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default(packageName2, charSequence, (boolean) i, 2, (Object) null)) {
                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                str10 = str13;
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str10);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                    canonicalName = (flowCollector3 == null || (cls3 = flowCollector3.getClass()) == null) ? 0 : cls3.getCanonicalName();
                                    if (canonicalName == 0) {
                                        canonicalName = "N/A";
                                    }
                                }
                                objectRef4.element = canonicalName;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher4.find()) {
                                    ?? replaceAll4 = matcher4.replaceAll(str5);
                                    Intrinsics.checkNotNullExpressionValue(replaceAll4, str12);
                                    objectRef4.element = replaceAll4;
                                }
                                if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str18 = (String) objectRef4.element;
                                } else {
                                    str18 = ((String) objectRef4.element).substring(i, 23);
                                    Intrinsics.checkNotNullExpressionValue(str18, str8);
                                }
                                StringBuilder sb3 = new StringBuilder();
                                String str27 = "fetchWorkflowConfig: failed " + m1205exceptionOrNullimpl.getMessage();
                                if (str27 == null) {
                                    str27 = "null ";
                                }
                                sb3.append(str27);
                                sb3.append(' ');
                                String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                if (localizedMessage2 != null) {
                                    str19 = '\n' + localizedMessage2;
                                } else {
                                    str19 = str5;
                                }
                                sb3.append(str19);
                                Log.println(6, str18, sb3.toString());
                                if (CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl)) {
                                    NetworkUIState.NetworkFailure networkFailure = NetworkUIState.NetworkFailure.INSTANCE;
                                    str11 = str9;
                                    this.L$0 = flowCollector3;
                                    obj8 = obj7;
                                    this.L$1 = obj8;
                                    this.label = 3;
                                    obj4 = obj6;
                                    if (flowCollector3.emit(networkFailure, this) == obj4) {
                                        return obj4;
                                    }
                                } else {
                                    str11 = str9;
                                    obj8 = obj7;
                                    obj4 = obj6;
                                    NetworkUIState.Failed failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                                    this.L$0 = flowCollector3;
                                    this.L$1 = obj8;
                                    this.label = 4;
                                    if (flowCollector3.emit(failed, this) == obj4) {
                                        return obj4;
                                    }
                                }
                                obj9 = obj8;
                                obj5 = obj9;
                                if (Result.m1209isSuccessimpl(obj5)) {
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        str10 = str13;
                        if (CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl)) {
                        }
                        obj9 = obj8;
                        obj5 = obj9;
                        if (Result.m1209isSuccessimpl(obj5)) {
                        }
                        return Unit.INSTANCE;
                    }
                    i = 0;
                    str14 = (String) objectRef3.element;
                    sb2.append(str14);
                    sb2.append(str9);
                    str15 = "fetchWorkflowConfig: failed " + m1205exceptionOrNullimpl.getMessage();
                    if (str15 == null) {
                    }
                    sb2.append(str15);
                    sb2.append(' ');
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    if (localizedMessage == null) {
                    }
                    sb2.append(str16);
                    companion5.log(level2, sb2.toString());
                    CoreExtsKt.isRelease();
                    Result.Companion companion62 = Result.INSTANCE;
                    Object invoke22 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[i]).invoke(null, new Object[i]);
                    Intrinsics.checkNotNull(invoke22, "null cannot be cast to non-null type android.app.Application");
                    String str262 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
                    str17 = str262;
                    if (Result.m1208isFailureimpl(str17)) {
                    }
                    String packageName22 = str17;
                    if (CoreExtsKt.isDebug()) {
                    }
                    str10 = str13;
                    if (CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl)) {
                    }
                    obj9 = obj8;
                    obj5 = obj9;
                    if (Result.m1209isSuccessimpl(obj5)) {
                    }
                    return Unit.INSTANCE;
                }
                if (i2 == 3) {
                    obj9 = this.L$1;
                    flowCollector4 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    charSequence = "co.hyperverge";
                    str5 = "";
                    str10 = "Throwable().stackTrace";
                } else {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    obj9 = this.L$1;
                    flowCollector4 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    charSequence = "co.hyperverge";
                    str5 = "";
                    str10 = "Throwable().stackTrace";
                }
                flowCollector3 = flowCollector4;
                str12 = "replaceAll(\"\")";
                str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                str11 = " - ";
                obj4 = coroutine_suspended;
                obj5 = obj9;
                if (Result.m1209isSuccessimpl(obj5)) {
                    WorkflowConfig workflowConfig = (WorkflowConfig) obj5;
                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb4 = new StringBuilder();
                    Object obj14 = obj4;
                    Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str10);
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null) {
                        str20 = str10;
                        obj10 = obj5;
                        str21 = "packageName";
                    } else {
                        str20 = str10;
                        obj10 = obj5;
                        str21 = "packageName";
                        r1 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r1 = (flowCollector3 == null || (cls6 = flowCollector3.getClass()) == null) ? 0 : cls6.getCanonicalName();
                    if (r1 == 0) {
                        r1 = "N/A";
                    }
                    objectRef5.element = r1;
                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                    if (matcher5.find()) {
                        ?? replaceAll5 = matcher5.replaceAll(str5);
                        Intrinsics.checkNotNullExpressionValue(replaceAll5, str12);
                        objectRef5.element = replaceAll5;
                    }
                    if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str22 = (String) objectRef5.element;
                    } else {
                        str22 = ((String) objectRef5.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str22, str8);
                    }
                    sb4.append(str22);
                    sb4.append(str11);
                    String str28 = "fetchWorkflowConfig: workflowConfig : " + workflowConfig;
                    if (str28 == null) {
                        str28 = "null ";
                    }
                    sb4.append(str28);
                    sb4.append(' ');
                    sb4.append(str5);
                    companion7.log(level3, sb4.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                            str23 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion9 = Result.INSTANCE;
                            str23 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        String str29 = str23;
                        if (Result.m1208isFailureimpl(str29)) {
                            str29 = str5;
                        }
                        String str30 = str29;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str30, str21);
                            if (StringsKt.contains$default((CharSequence) str30, charSequence, false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str20);
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className5 = stackTraceElement6.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                    canonicalName2 = (flowCollector3 == null || (cls5 = flowCollector3.getClass()) == null) ? 0 : cls5.getCanonicalName();
                                    if (canonicalName2 == 0) {
                                        canonicalName2 = "N/A";
                                    }
                                }
                                objectRef6.element = canonicalName2;
                                Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                if (matcher6.find()) {
                                    ?? replaceAll6 = matcher6.replaceAll(str5);
                                    Intrinsics.checkNotNullExpressionValue(replaceAll6, str12);
                                    objectRef6.element = replaceAll6;
                                }
                                if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str24 = (String) objectRef6.element;
                                } else {
                                    str24 = ((String) objectRef6.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str24, str8);
                                }
                                StringBuilder sb5 = new StringBuilder();
                                String str31 = "fetchWorkflowConfig: workflowConfig : " + workflowConfig;
                                if (str31 == null) {
                                    str31 = "null ";
                                }
                                sb5.append(str31);
                                sb5.append(' ');
                                sb5.append(str5);
                                Log.println(2, str24, sb5.toString());
                            }
                        }
                    }
                    if (workflowConfig != null) {
                        obj12 = (NetworkUIState) new NetworkUIState.Success(workflowConfig);
                        obj11 = null;
                    } else {
                        obj11 = null;
                        obj12 = (NetworkUIState) new NetworkUIState.Failed("Workflow config is corrupt or empty", null, 2, null);
                    }
                    this.L$0 = obj10;
                    this.L$1 = obj11;
                    this.label = 5;
                    if (flowCollector3.emit(obj12, this) == obj14) {
                        return obj14;
                    }
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector7 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector7;
            mainVM$fetchWorkflowConfig$1 = this;
            obj2 = coroutine_suspended;
            charSequence = "co.hyperverge";
        }
        String str32 = mainVM$fetchWorkflowConfig$1.$appId;
        String str33 = mainVM$fetchWorkflowConfig$1.$workflowId;
        MainVM mainVM = mainVM$fetchWorkflowConfig$1.this$0;
        Result.Companion companion10 = Result.INSTANCE;
        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
        cacheDir = mainVM.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        mainVM$fetchWorkflowConfig$1.L$0 = flowCollector;
        mainVM$fetchWorkflowConfig$1.label = 2;
        str5 = "";
        str6 = "replaceAll(\"\")";
        flowCollector2 = flowCollector;
        str7 = str25;
        str8 = "this as java.lang.String…ing(startIndex, endIndex)";
        Object obj15 = obj2;
        str9 = " - ";
        fetchWorkflowConfig$hyperkyc_release$default = NetworkRepo.fetchWorkflowConfig$hyperkyc_release$default(networkRepo, str32, str33, cacheDir, null, this, 8, null);
        obj3 = obj15;
        if (fetchWorkflowConfig$hyperkyc_release$default == obj3) {
            return obj3;
        }
        m1202constructorimpl2 = Result.m1202constructorimpl((WorkflowConfig) fetchWorkflowConfig$hyperkyc_release$default);
        Object obj1322 = m1202constructorimpl2;
        flowCollector3 = flowCollector2;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj1322);
        if (m1205exceptionOrNullimpl == null) {
        }
    }
}
