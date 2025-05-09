package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.data.models.FailureReason;
import co.hyperverge.hyperkyc.data.models.FinishReview;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.result.HyperKycResult;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\u0002`\u00050\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lokhttp3/Response;", "Lco/hyperverge/hyperkyc/ui/custom/FinishReviewUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$performReviewFinish$2", f = "MainVM.kt", i = {0, 0, 1, 2, 3}, l = {3452, 3493, 3503, 3505, 3509}, m = "invokeSuspend", n = {"$this$flow", "requestIds", "$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$1", "L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$performReviewFinish$2 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends Response>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ HyperKycResult $hyperKycResult;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$performReviewFinish$2(MainVM mainVM, HyperKycResult hyperKycResult, Continuation<? super MainVM$performReviewFinish$2> continuation) {
        super(2, continuation);
        this.this$0 = mainVM;
        this.$hyperKycResult = hyperKycResult;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$performReviewFinish$2 mainVM$performReviewFinish$2 = new MainVM$performReviewFinish$2(this.this$0, this.$hyperKycResult, continuation);
        mainVM$performReviewFinish$2.L$0 = obj;
        return mainVM$performReviewFinish$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends Response>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<Response>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<Response>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$performReviewFinish$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(53:1|(1:(2:4|(2:6|(2:8|(2:10|(3:12|13|14)(2:16|17))(7:18|19|20|21|(16:23|(1:98)(1:27)|(1:36)(1:32)|(1:34)(1:35)|37|(1:39)|40|(1:97)(1:44)|45|(1:47)|48|(6:57|58|59|(1:61)|62|(2:64|(11:66|(1:70)|(1:93)(1:89)|(1:91)(1:92)|72|(1:74)|75|(1:85)(1:79)|80|(1:82)(1:84)|83)))|50|(1:52)(1:56)|53|(1:55))(1:99)|13|14))(7:100|101|20|21|(0)(0)|13|14))(8:102|103|104|105|106|107|108|(20:110|(1:196)(1:114)|(1:123)(1:119)|(1:121)(1:122)|124|(1:126)(1:195)|127|(1:194)(1:131)|132|(1:134)(1:193)|(1:136)(1:192)|137|138|139|140|(1:142)|143|(2:145|(10:147|(2:(1:186)(1:183)|(1:185))|153|(1:155)|156|(1:179)(1:160)|161|(1:163)(1:178)|(1:165)(1:177)|166)(1:187))(1:188)|167|(2:169|(1:171)(7:172|101|20|21|(0)(0)|13|14))(2:173|(1:175)(7:176|19|20|21|(0)(0)|13|14)))(5:197|21|(0)(0)|13|14)))(1:204))(14:392|(1:472)(1:396)|(1:405)(1:401)|(1:403)(1:404)|406|(1:408)|409|(1:471)(1:413)|414|(1:416)|417|(1:419)(10:424|425|426|427|428|429|(1:431)|432|(2:434|(9:436|(2:(1:462)(1:459)|(1:461))|442|(1:444)|445|(1:455)(1:449)|450|(1:452)|453)(1:463))(1:464)|454)|420|(1:422)(1:423))|205|(11:207|(1:390)(1:211)|(1:219)(1:216)|(1:218)|220|(1:222)|223|(1:389)(1:227)|228|(6:350|351|352|(1:354)|355|(2:357|(58:359|(1:384)(1:363)|(1:372)(1:368)|(1:370)(1:371)|373|(1:375)|376|(50:380|381|232|(9:235|236|237|238|(1:240)|241|(2:243|244)(1:246)|245|233)|250|251|(1:253)(1:349)|254|(4:257|(2:259|260)(1:262)|261|255)|263|264|265|266|267|(1:269)|270|271|272|273|(1:275)|276|(3:278|(1:280)(1:341)|(28:282|283|284|285|286|(1:288)|289|290|291|292|(1:294)|295|296|297|(2:327|328)|299|300|301|302|303|304|305|306|307|308|309|310|(1:312)(5:313|106|107|108|(0)(0))))|342|283|284|285|286|(0)|289|290|291|292|(0)|295|296|297|(0)|299|300|301|302|303|304|305|306|307|308|309|310|(0)(0))|382|381|232|(1:233)|250|251|(0)(0)|254|(1:255)|263|264|265|266|267|(0)|270|271|272|273|(0)|276|(0)|342|283|284|285|286|(0)|289|290|291|292|(0)|295|296|297|(0)|299|300|301|302|303|304|305|306|307|308|309|310|(0)(0))(1:385)))|230)(1:391)|231|232|(1:233)|250|251|(0)(0)|254|(1:255)|263|264|265|266|267|(0)|270|271|272|273|(0)|276|(0)|342|283|284|285|286|(0)|289|290|291|292|(0)|295|296|297|(0)|299|300|301|302|303|304|305|306|307|308|309|310|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x07a2, code lost:
    
        if (r11 != null) goto L302;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0318, code lost:
    
        if (r1 != 0) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x09ed, code lost:
    
        if (r5 != null) goto L398;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x0729, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x072a, code lost:
    
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0752, code lost:
    
        r2 = r42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x072d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x072e, code lost:
    
        r10 = r2;
        r9 = "this as java.lang.String…ing(startIndex, endIndex)";
        r39 = "";
        r24 = r8;
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x0751, code lost:
    
        r14 = "replaceAll(\"\")";
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0738, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x0739, code lost:
    
        r10 = r2;
        r9 = "this as java.lang.String…ing(startIndex, endIndex)";
        r39 = "";
        r24 = r8;
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x06bf, code lost:
    
        if (r9 == null) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x0744, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x0745, code lost:
    
        r10 = r2;
        r9 = "this as java.lang.String…ing(startIndex, endIndex)";
        r39 = "";
        r24 = r8;
        r42 = r14;
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x0680, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x0681, code lost:
    
        r9 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x0659, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x065a, code lost:
    
        r9 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x061c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x061d, code lost:
    
        r9 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x05e8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x05e9, code lost:
    
        r9 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x040f, code lost:
    
        if (r1 != null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x010e, code lost:
    
        if (r10 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0b02, code lost:
    
        if (r4 != null) goto L440;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x099b  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x04d1  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x09a8  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x071b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:313:0x071c  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x06bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0572  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x02d1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:423:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0bb0  */
    /* JADX WARN: Type inference failed for: r0v144, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v169, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v182, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v200, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v5, types: [T] */
    /* JADX WARN: Type inference failed for: r10v74 */
    /* JADX WARN: Type inference failed for: r11v36, types: [T] */
    /* JADX WARN: Type inference failed for: r11v43 */
    /* JADX WARN: Type inference failed for: r11v44 */
    /* JADX WARN: Type inference failed for: r11v57 */
    /* JADX WARN: Type inference failed for: r1v104, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v131 */
    /* JADX WARN: Type inference failed for: r1v132 */
    /* JADX WARN: Type inference failed for: r1v21, types: [T] */
    /* JADX WARN: Type inference failed for: r1v42, types: [T] */
    /* JADX WARN: Type inference failed for: r1v52, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v53 */
    /* JADX WARN: Type inference failed for: r1v54 */
    /* JADX WARN: Type inference failed for: r1v64, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v65 */
    /* JADX WARN: Type inference failed for: r1v66 */
    /* JADX WARN: Type inference failed for: r1v67 */
    /* JADX WARN: Type inference failed for: r1v71, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v28, types: [T] */
    /* JADX WARN: Type inference failed for: r2v38, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v50, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v96 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v19, types: [T] */
    /* JADX WARN: Type inference failed for: r4v29, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v20, types: [T] */
    /* JADX WARN: Type inference failed for: r5v30, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v32, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v42, types: [T] */
    /* JADX WARN: Type inference failed for: r5v77, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v78 */
    /* JADX WARN: Type inference failed for: r5v79 */
    /* JADX WARN: Type inference failed for: r5v87 */
    /* JADX WARN: Type inference failed for: r5v88 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        CharSequence charSequence;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Object m1202constructorimpl;
        String str8;
        String str9;
        MainVM$performReviewFinish$2 mainVM$performReviewFinish$2;
        ?? canonicalName;
        Class<?> cls;
        String str10;
        String className;
        ArrayList requestIds;
        Object obj2;
        FlowCollector flowCollector;
        ArrayList arrayList;
        Class<?> cls2;
        String className2;
        ArrayList arrayList2;
        String str11;
        Object obj3;
        long j;
        Object m1202constructorimpl2;
        Object m1202constructorimpl3;
        FailureReason failureReason;
        FailureReason failureReason2;
        Object m1202constructorimpl4;
        Object m1202constructorimpl5;
        String str12;
        String str13;
        String str14;
        String str15;
        Object obj4;
        String str16;
        FlowCollector flowCollector2;
        KycCountry selectedCountry;
        String baseUrl;
        Map defaultHeaders;
        Map authHeaders;
        Object m399performReviewFinishyxL6bBk$hyperkyc_release;
        Object m1202constructorimpl6;
        String str17;
        String str18;
        ?? r1;
        String str19;
        Object m1202constructorimpl7;
        String str20;
        String str21;
        Class<?> cls3;
        String className3;
        Class<?> cls4;
        String className4;
        Object m1202constructorimpl8;
        FlowCollector flowCollector3;
        Throwable m1205exceptionOrNullimpl;
        MainVM$performReviewFinish$2 mainVM$performReviewFinish$22;
        Object obj5;
        String str22;
        Object obj6;
        String str23;
        Object obj7;
        Object obj8;
        String str24;
        String str25;
        String str26;
        String str27;
        String str28;
        FlowCollector flowCollector4;
        Object obj9;
        Object obj10;
        ?? canonicalName2;
        Class<?> cls5;
        String str29;
        String className5;
        Class<?> cls6;
        String className6;
        String str30;
        String str31;
        Object obj11;
        String str32;
        String str33;
        String str34;
        String str35;
        Class<?> cls7;
        String str36;
        String className7;
        Class<?> cls8;
        String className8;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector5 = (FlowCollector) this.L$0;
            HyperKycResult hyperKycResult = this.$hyperKycResult;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            str = "null ";
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            charSequence = "co.hyperverge";
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str2 = "Throwable().stackTrace";
                str3 = "null cannot be cast to non-null type android.app.Application";
                str4 = "packageName";
            } else {
                str2 = "Throwable().stackTrace";
                str3 = "null cannot be cast to non-null type android.app.Application";
                str4 = "packageName";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str5 = substringAfterLast$default;
            }
            String canonicalName3 = (flowCollector5 == null || (cls2 = flowCollector5.getClass()) == null) ? null : cls2.getCanonicalName();
            str5 = canonicalName3 == null ? "N/A" : canonicalName3;
            objectRef.element = str5;
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str6 = (String) objectRef.element;
            } else {
                str6 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str6);
            sb.append(" - ");
            String str37 = "performReviewFinish() called with hyperKycResult = " + hyperKycResult;
            if (str37 == null) {
                str37 = str;
            }
            sb.append(str37);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                mainVM$performReviewFinish$2 = this;
                str8 = str2;
                str9 = str4;
                str7 = str3;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str7 = str3;
                    try {
                        Intrinsics.checkNotNull(invoke, str7);
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String str38 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        mainVM$performReviewFinish$2 = this;
                        requestIds = mainVM$performReviewFinish$2.this$0.getRequestIds();
                        mainVM$performReviewFinish$2.L$0 = flowCollector5;
                        mainVM$performReviewFinish$2.L$1 = requestIds;
                        mainVM$performReviewFinish$2.label = 1;
                        obj2 = coroutine_suspended;
                        if (flowCollector5.emit(NetworkUIState.Loading.INSTANCE, mainVM$performReviewFinish$2) != obj2) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str7 = str3;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String str382 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str8 = str2;
                    str9 = str4;
                } else {
                    str9 = str4;
                    Intrinsics.checkNotNullExpressionValue(str382, str9);
                    if (StringsKt.contains$default((CharSequence) str382, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str8 = str2;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str8);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName = (flowCollector5 == null || (cls = flowCollector5.getClass()) == null) ? 0 : cls.getCanonicalName();
                            if (canonicalName == 0) {
                                canonicalName = "N/A";
                            }
                        }
                        objectRef2.element = canonicalName;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str10 = (String) objectRef2.element;
                        } else {
                            str10 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str39 = "performReviewFinish() called with hyperKycResult = " + hyperKycResult;
                        if (str39 == null) {
                            str39 = str;
                        }
                        sb2.append(str39);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str10, sb2.toString());
                    } else {
                        str8 = str2;
                    }
                }
                mainVM$performReviewFinish$2 = this;
            }
            requestIds = mainVM$performReviewFinish$2.this$0.getRequestIds();
            mainVM$performReviewFinish$2.L$0 = flowCollector5;
            mainVM$performReviewFinish$2.L$1 = requestIds;
            mainVM$performReviewFinish$2.label = 1;
            obj2 = coroutine_suspended;
            if (flowCollector5.emit(NetworkUIState.Loading.INSTANCE, mainVM$performReviewFinish$2) != obj2) {
                return obj2;
            }
            flowCollector = flowCollector5;
            arrayList = requestIds;
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        obj9 = this.L$1;
                        FlowCollector flowCollector6 = (FlowCollector) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        str22 = "Throwable().stackTrace";
                        str23 = "";
                        mainVM$performReviewFinish$22 = this;
                        str7 = "null cannot be cast to non-null type android.app.Application";
                        charSequence = "co.hyperverge";
                        str = "null ";
                        str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str16 = "replaceAll(\"\")";
                        obj10 = coroutine_suspended;
                        flowCollector3 = flowCollector6;
                        str28 = "packageName";
                        str15 = str28;
                        obj5 = obj10;
                        obj6 = obj9;
                        if (Result.m1209isSuccessimpl(obj6)) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    obj9 = this.L$1;
                    flowCollector4 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    str22 = "Throwable().stackTrace";
                    str23 = "";
                    mainVM$performReviewFinish$22 = this;
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str15 = "packageName";
                    str = "null ";
                    str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str16 = "replaceAll(\"\")";
                    obj5 = coroutine_suspended;
                    flowCollector3 = flowCollector4;
                    obj6 = obj9;
                    if (Result.m1209isSuccessimpl(obj6)) {
                        Object value = ((Result) obj6).getValue();
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        Object obj12 = obj5;
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str22);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className8 = stackTraceElement3.getClassName()) == null) {
                            str30 = str22;
                            str31 = str7;
                            obj11 = obj6;
                        } else {
                            str30 = str22;
                            str31 = str7;
                            obj11 = obj6;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str32 = substringAfterLast$default2;
                        }
                        String canonicalName4 = (flowCollector3 == null || (cls8 = flowCollector3.getClass()) == null) ? null : cls8.getCanonicalName();
                        str32 = canonicalName4 == null ? "N/A" : canonicalName4;
                        objectRef3.element = str32;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                        if (matcher3.find()) {
                            ?? replaceAll3 = matcher3.replaceAll(str23);
                            Intrinsics.checkNotNullExpressionValue(replaceAll3, str16);
                            objectRef3.element = replaceAll3;
                        }
                        if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str33 = (String) objectRef3.element;
                        } else {
                            str33 = ((String) objectRef3.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str33, str13);
                        }
                        sb3.append(str33);
                        sb3.append(" - ");
                        String str40 = "performReviewFinish() called with response: " + ((Object) Result.m1210toStringimpl(value));
                        if (str40 == null) {
                            str40 = str;
                        }
                        sb3.append(str40);
                        sb3.append(' ');
                        sb3.append(str23);
                        companion4.log(level2, sb3.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, str31);
                                str34 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th3) {
                                Result.Companion companion6 = Result.INSTANCE;
                                str34 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                            }
                            String str41 = str34;
                            if (Result.m1208isFailureimpl(str41)) {
                                str41 = str23;
                            }
                            String str42 = str41;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str42, str15);
                                if (StringsKt.contains$default((CharSequence) str42, charSequence, false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str30);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 != null && (className7 = stackTraceElement4.getClassName()) != null) {
                                        String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        str35 = substringAfterLast$default3;
                                    }
                                    String canonicalName5 = (flowCollector3 == null || (cls7 = flowCollector3.getClass()) == null) ? null : cls7.getCanonicalName();
                                    str35 = canonicalName5 == null ? "N/A" : canonicalName5;
                                    objectRef4.element = str35;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll4 = matcher4.replaceAll(str23);
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, str16);
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str36 = (String) objectRef4.element;
                                    } else {
                                        str36 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str36, str13);
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    String str43 = "performReviewFinish() called with response: " + ((Object) Result.m1210toStringimpl(value));
                                    sb4.append(str43 == null ? str : str43);
                                    sb4.append(' ');
                                    sb4.append(str23);
                                    Log.println(3, str36, sb4.toString());
                                }
                            }
                        }
                        NetworkUIState.Success success = new NetworkUIState.Success(Result.m1208isFailureimpl(value) ? null : value);
                        this.L$0 = obj11;
                        this.L$1 = null;
                        this.label = 5;
                        if (flowCollector3.emit(success, this) == obj12) {
                            return obj12;
                        }
                    }
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector7 = (FlowCollector) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    m399performReviewFinishyxL6bBk$hyperkyc_release = ((Result) obj).getValue();
                    str14 = "";
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str15 = "packageName";
                    str = "null ";
                    str12 = "Throwable().stackTrace";
                    str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str16 = "replaceAll(\"\")";
                    flowCollector2 = flowCollector7;
                    obj4 = coroutine_suspended;
                    try {
                        m1202constructorimpl8 = Result.m1202constructorimpl(Result.m1201boximpl(m399performReviewFinishyxL6bBk$hyperkyc_release));
                    } catch (Throwable th4) {
                        th = th4;
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        flowCollector3 = flowCollector2;
                        Object obj13 = m1202constructorimpl8;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj13);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    str14 = "";
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str15 = "packageName";
                    str = "null ";
                    str12 = "Throwable().stackTrace";
                    str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str16 = "replaceAll(\"\")";
                    flowCollector2 = flowCollector7;
                    obj4 = coroutine_suspended;
                    Result.Companion companion72 = Result.INSTANCE;
                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    flowCollector3 = flowCollector2;
                    Object obj132 = m1202constructorimpl8;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj132);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                flowCollector3 = flowCollector2;
                Object obj1322 = m1202constructorimpl8;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj1322);
                if (m1205exceptionOrNullimpl != null) {
                    mainVM$performReviewFinish$22 = this;
                    obj5 = obj4;
                    str22 = str12;
                    obj6 = obj1322;
                    str23 = str14;
                    if (Result.m1209isSuccessimpl(obj6)) {
                    }
                    return Unit.INSTANCE;
                }
                HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb5 = new StringBuilder();
                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace5, str12);
                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null) {
                    obj7 = obj4;
                    obj8 = obj1322;
                    str24 = str12;
                } else {
                    obj7 = obj4;
                    obj8 = obj1322;
                    str24 = str12;
                    String substringAfterLast$default4 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    str25 = substringAfterLast$default4;
                }
                String canonicalName6 = (flowCollector3 == null || (cls6 = flowCollector3.getClass()) == null) ? null : cls6.getCanonicalName();
                str25 = canonicalName6 == null ? "N/A" : canonicalName6;
                objectRef5.element = str25;
                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                if (matcher5.find()) {
                    str23 = str14;
                    ?? replaceAll5 = matcher5.replaceAll(str23);
                    Intrinsics.checkNotNullExpressionValue(replaceAll5, str16);
                    objectRef5.element = replaceAll5;
                } else {
                    str23 = str14;
                }
                if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str26 = (String) objectRef5.element;
                } else {
                    str26 = ((String) objectRef5.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str26, str13);
                }
                sb5.append(str26);
                sb5.append(" - ");
                sb5.append("performReviewFinish() failed");
                sb5.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                sb5.append(localizedMessage != null ? '\n' + localizedMessage : str23);
                companion8.log(level3, sb5.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion9 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke3, str7);
                    str27 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                } catch (Throwable th6) {
                    Result.Companion companion10 = Result.INSTANCE;
                    str27 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                }
                String str44 = str27;
                if (Result.m1208isFailureimpl(str44)) {
                    str44 = str23;
                }
                String str45 = str44;
                if (CoreExtsKt.isDebug()) {
                    str28 = str15;
                    Intrinsics.checkNotNullExpressionValue(str45, str28);
                    if (StringsKt.contains$default((CharSequence) str45, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                        str22 = str24;
                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str22);
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
                            ?? replaceAll6 = matcher6.replaceAll(str23);
                            Intrinsics.checkNotNullExpressionValue(replaceAll6, str16);
                            objectRef6.element = replaceAll6;
                        }
                        if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str29 = (String) objectRef6.element;
                        } else {
                            str29 = ((String) objectRef6.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str29, str13);
                        }
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("performReviewFinish() failed");
                        sb6.append(' ');
                        String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        sb6.append(localizedMessage2 != null ? '\n' + localizedMessage2 : str23);
                        Log.println(6, str29, sb6.toString());
                    } else {
                        str22 = str24;
                    }
                } else {
                    str22 = str24;
                    str28 = str15;
                }
                if (CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl)) {
                    NetworkUIState.NetworkFailure networkFailure = NetworkUIState.NetworkFailure.INSTANCE;
                    mainVM$performReviewFinish$22 = this;
                    mainVM$performReviewFinish$22.L$0 = flowCollector3;
                    Object obj14 = obj8;
                    mainVM$performReviewFinish$22.L$1 = obj14;
                    mainVM$performReviewFinish$22.label = 3;
                    obj10 = obj7;
                    if (flowCollector3.emit(networkFailure, mainVM$performReviewFinish$22) == obj10) {
                        return obj10;
                    }
                    obj9 = obj14;
                    str15 = str28;
                    obj5 = obj10;
                    obj6 = obj9;
                    if (Result.m1209isSuccessimpl(obj6)) {
                    }
                    return Unit.INSTANCE;
                }
                mainVM$performReviewFinish$22 = this;
                Object obj15 = obj8;
                obj5 = obj7;
                str15 = str28;
                NetworkUIState.Failed failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                mainVM$performReviewFinish$22.L$0 = flowCollector3;
                mainVM$performReviewFinish$22.L$1 = obj15;
                mainVM$performReviewFinish$22.label = 4;
                if (flowCollector3.emit(failed, mainVM$performReviewFinish$22) == obj5) {
                    return obj5;
                }
                flowCollector4 = flowCollector3;
                obj9 = obj15;
                flowCollector3 = flowCollector4;
                obj6 = obj9;
                if (Result.m1209isSuccessimpl(obj6)) {
                }
                return Unit.INSTANCE;
            }
            ArrayList arrayList3 = (ArrayList) this.L$1;
            FlowCollector flowCollector8 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            charSequence = "co.hyperverge";
            str = "null ";
            flowCollector = flowCollector8;
            str7 = "null cannot be cast to non-null type android.app.Application";
            arrayList = arrayList3;
            str8 = "Throwable().stackTrace";
            obj2 = coroutine_suspended;
            str9 = "packageName";
        }
        if (arrayList.isEmpty()) {
            HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
            HyperLogger companion11 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb7 = new StringBuilder();
            obj3 = obj2;
            Ref.ObjectRef objectRef7 = new Ref.ObjectRef();
            arrayList2 = arrayList;
            StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace7, str8);
            StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
            if (stackTraceElement7 == null || (className4 = stackTraceElement7.getClassName()) == null) {
                str17 = str9;
                str18 = str8;
            } else {
                str17 = str9;
                str18 = str8;
                r1 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            r1 = (flowCollector == null || (cls4 = flowCollector.getClass()) == null) ? 0 : cls4.getCanonicalName();
            if (r1 == 0) {
                r1 = "N/A";
            }
            objectRef7.element = r1;
            Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef7.element);
            if (matcher7.find()) {
                ?? replaceAll7 = matcher7.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll7, "replaceAll(\"\")");
                objectRef7.element = replaceAll7;
            }
            if (((String) objectRef7.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str19 = (String) objectRef7.element;
            } else {
                str19 = ((String) objectRef7.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str19, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb7.append(str19);
            sb7.append(" - ");
            sb7.append("performReviewFinish: requestIds is empty");
            sb7.append(' ');
            sb7.append("");
            companion11.log(level4, sb7.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion12 = Result.INSTANCE;
                    Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke4, str7);
                    m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                } catch (Throwable th7) {
                    Result.Companion companion13 = Result.INSTANCE;
                    m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                    m1202constructorimpl7 = "";
                }
                String str46 = (String) m1202constructorimpl7;
                if (CoreExtsKt.isDebug()) {
                    str9 = str17;
                    Intrinsics.checkNotNullExpressionValue(str46, str9);
                    if (StringsKt.contains$default((CharSequence) str46, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef8 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                        str11 = str18;
                        Intrinsics.checkNotNullExpressionValue(stackTrace8, str11);
                        StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                        if (stackTraceElement8 != null && (className3 = stackTraceElement8.getClassName()) != null) {
                            String substringAfterLast$default5 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str20 = substringAfterLast$default5;
                        }
                        String canonicalName7 = (flowCollector == null || (cls3 = flowCollector.getClass()) == null) ? null : cls3.getCanonicalName();
                        str20 = canonicalName7 == null ? "N/A" : canonicalName7;
                        objectRef8.element = str20;
                        Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
                        if (matcher8.find()) {
                            ?? replaceAll8 = matcher8.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll8, "replaceAll(\"\")");
                            objectRef8.element = replaceAll8;
                        }
                        if (((String) objectRef8.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str21 = ((String) objectRef8.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str21, "this as java.lang.String…ing(startIndex, endIndex)");
                            Log.println(5, str21, "performReviewFinish: requestIds is empty ");
                            AnalyticsLogger.INSTANCE.logEndWorkflowEvent$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("status", this.$hyperKycResult.getStatus())));
                            Map<String, String> details = this.$hyperKycResult.getDetails();
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            for (Map.Entry<String, String> entry : details.entrySet()) {
                                try {
                                    Result.Companion companion14 = Result.INSTANCE;
                                    m1202constructorimpl6 = Result.m1202constructorimpl(Boxing.boxBoolean(new File(entry.getValue()).isFile()));
                                } catch (Throwable th8) {
                                    Result.Companion companion15 = Result.INSTANCE;
                                    m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                                }
                                if (Result.m1205exceptionOrNullimpl(m1202constructorimpl6) != null) {
                                    m1202constructorimpl6 = Boxing.boxBoolean(false);
                                }
                                if (!((Boolean) m1202constructorimpl6).booleanValue()) {
                                    linkedHashMap.put(entry.getKey(), entry.getValue());
                                }
                            }
                            Map map = MapsKt.toMap(linkedHashMap);
                            Pair[] pairArr = new Pair[7];
                            pairArr[0] = TuplesKt.to("latestModule", this.$hyperKycResult.getLatestModule());
                            pairArr[1] = TuplesKt.to("latestCondition", this.$hyperKycResult.getLatestCondition$hyperkyc_release());
                            pairArr[2] = TuplesKt.to("latestRule", this.$hyperKycResult.getLatestRule$hyperkyc_release());
                            pairArr[3] = TuplesKt.to("latestRuleRaw", this.$hyperKycResult.getLatestRuleRaw$hyperkyc_release());
                            Integer errorCode = this.$hyperKycResult.getErrorCode();
                            pairArr[4] = TuplesKt.to("errorCode", errorCode != null ? errorCode.toString() : null);
                            pairArr[5] = TuplesKt.to("errorMessage", this.$hyperKycResult.getErrorMessage());
                            j = this.this$0.totalTimeSpent;
                            pairArr[6] = TuplesKt.to("totalTimeSpent", String.valueOf(j));
                            Map mapOf = MapsKt.mapOf(pairArr);
                            LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(mapOf.size()));
                            for (Map.Entry entry2 : mapOf.entrySet()) {
                                Object key = entry2.getKey();
                                String str47 = (String) entry2.getValue();
                                if (str47 == null) {
                                    str47 = "";
                                }
                                linkedHashMap2.put(key, str47);
                            }
                            MainVM mainVM = this.this$0;
                            Result.Companion companion16 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = "";
                            }
                            String str48 = (String) m1202constructorimpl2;
                            ArrayList arrayList4 = arrayList2;
                            String status = this.$hyperKycResult.getStatus();
                            MainVM mainVM2 = this.this$0;
                            Result.Companion companion17 = Result.INSTANCE;
                            String appId = mainVM2.getHyperKycConfig$hyperkyc_release().getAppId();
                            Intrinsics.checkNotNull(appId);
                            m1202constructorimpl3 = Result.m1202constructorimpl(appId);
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String str49 = (String) m1202constructorimpl3;
                            failureReason = this.this$0.failureReason;
                            if (failureReason != null) {
                                if (failureReason.getText() != null) {
                                    failureReason2 = failureReason;
                                    MainVM mainVM3 = this.this$0;
                                    Result.Companion companion18 = Result.INSTANCE;
                                    m1202constructorimpl4 = Result.m1202constructorimpl(mainVM3.getJourneyId$hyperkyc_release());
                                    if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                        m1202constructorimpl4 = "";
                                    }
                                    String str50 = (String) m1202constructorimpl4;
                                    MainVM mainVM4 = this.this$0;
                                    Result.Companion companion19 = Result.INSTANCE;
                                    m1202constructorimpl5 = Result.m1202constructorimpl(mainVM4.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release());
                                    if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                        m1202constructorimpl5 = "";
                                    }
                                    FinishReview finishReview = new FinishReview(str48, arrayList4, status, str49, map, linkedHashMap2, failureReason2, str50, (String) m1202constructorimpl5, this.this$0.getHyperKycData().getApiFlags$hyperkyc_release(), null, null, 3072, null);
                                    MainVM mainVM5 = this.this$0;
                                    Result.Companion companion20 = Result.INSTANCE;
                                    selectedCountry = mainVM5.getSelectedCountry();
                                    if (selectedCountry != null) {
                                        try {
                                            baseUrl = selectedCountry.getBaseUrl();
                                        } catch (Throwable th9) {
                                            th = th9;
                                            str12 = str11;
                                            str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                                            str14 = "";
                                            str15 = str9;
                                            flowCollector2 = flowCollector;
                                            obj4 = obj3;
                                            str16 = "replaceAll(\"\")";
                                            Result.Companion companion722 = Result.INSTANCE;
                                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            flowCollector3 = flowCollector2;
                                            Object obj13222 = m1202constructorimpl8;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj13222);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                        }
                                    }
                                    baseUrl = "https://ind.idv.hyperverge.co";
                                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                                    String str51 = baseUrl + "/v2/finishTransaction";
                                    defaultHeaders = mainVM5.getDefaultHeaders();
                                    authHeaders = mainVM5.getAuthHeaders();
                                    Map<String, String> plus = MapsKt.plus(defaultHeaders, authHeaders);
                                    MainVM$performReviewFinish$2$3$1 mainVM$performReviewFinish$2$3$1 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$performReviewFinish$2$3$1
                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th10) {
                                            invoke2(th10);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Throwable it) {
                                            Intrinsics.checkNotNullParameter(it, "it");
                                            throw it;
                                        }
                                    };
                                    this.L$0 = flowCollector;
                                    FlowCollector flowCollector9 = flowCollector;
                                    this.L$1 = null;
                                    this.label = 2;
                                    str12 = str11;
                                    str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                                    str16 = "replaceAll(\"\")";
                                    str15 = str9;
                                    str14 = "";
                                    m399performReviewFinishyxL6bBk$hyperkyc_release = networkRepo.m399performReviewFinishyxL6bBk$hyperkyc_release(str51, finishReview, plus, mainVM$performReviewFinish$2$3$1, this);
                                    obj4 = obj3;
                                    if (m399performReviewFinishyxL6bBk$hyperkyc_release != obj4) {
                                        return obj4;
                                    }
                                    flowCollector2 = flowCollector9;
                                    m1202constructorimpl8 = Result.m1202constructorimpl(Result.m1201boximpl(m399performReviewFinishyxL6bBk$hyperkyc_release));
                                    flowCollector3 = flowCollector2;
                                    Object obj132222 = m1202constructorimpl8;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj132222);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                }
                            }
                            failureReason2 = null;
                            MainVM mainVM32 = this.this$0;
                            Result.Companion companion182 = Result.INSTANCE;
                            m1202constructorimpl4 = Result.m1202constructorimpl(mainVM32.getJourneyId$hyperkyc_release());
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                            }
                            String str502 = (String) m1202constructorimpl4;
                            MainVM mainVM42 = this.this$0;
                            Result.Companion companion192 = Result.INSTANCE;
                            m1202constructorimpl5 = Result.m1202constructorimpl(mainVM42.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release());
                            if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                            }
                            FinishReview finishReview2 = new FinishReview(str48, arrayList4, status, str49, map, linkedHashMap2, failureReason2, str502, (String) m1202constructorimpl5, this.this$0.getHyperKycData().getApiFlags$hyperkyc_release(), null, null, 3072, null);
                            MainVM mainVM52 = this.this$0;
                            Result.Companion companion202 = Result.INSTANCE;
                            selectedCountry = mainVM52.getSelectedCountry();
                            if (selectedCountry != null) {
                            }
                            baseUrl = "https://ind.idv.hyperverge.co";
                            NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
                            String str512 = baseUrl + "/v2/finishTransaction";
                            defaultHeaders = mainVM52.getDefaultHeaders();
                            authHeaders = mainVM52.getAuthHeaders();
                            Map<String, String> plus2 = MapsKt.plus(defaultHeaders, authHeaders);
                            MainVM$performReviewFinish$2$3$1 mainVM$performReviewFinish$2$3$12 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$performReviewFinish$2$3$1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th10) {
                                    invoke2(th10);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable it) {
                                    Intrinsics.checkNotNullParameter(it, "it");
                                    throw it;
                                }
                            };
                            this.L$0 = flowCollector;
                            FlowCollector flowCollector92 = flowCollector;
                            this.L$1 = null;
                            this.label = 2;
                            str12 = str11;
                            str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                            str16 = "replaceAll(\"\")";
                            str15 = str9;
                            str14 = "";
                            m399performReviewFinishyxL6bBk$hyperkyc_release = networkRepo2.m399performReviewFinishyxL6bBk$hyperkyc_release(str512, finishReview2, plus2, mainVM$performReviewFinish$2$3$12, this);
                            obj4 = obj3;
                            if (m399performReviewFinishyxL6bBk$hyperkyc_release != obj4) {
                            }
                        }
                        str21 = (String) objectRef8.element;
                        Log.println(5, str21, "performReviewFinish: requestIds is empty ");
                        AnalyticsLogger.INSTANCE.logEndWorkflowEvent$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("status", this.$hyperKycResult.getStatus())));
                        Map<String, String> details2 = this.$hyperKycResult.getDetails();
                        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                        while (r9.hasNext()) {
                        }
                        Map map2 = MapsKt.toMap(linkedHashMap3);
                        Pair[] pairArr2 = new Pair[7];
                        pairArr2[0] = TuplesKt.to("latestModule", this.$hyperKycResult.getLatestModule());
                        pairArr2[1] = TuplesKt.to("latestCondition", this.$hyperKycResult.getLatestCondition$hyperkyc_release());
                        pairArr2[2] = TuplesKt.to("latestRule", this.$hyperKycResult.getLatestRule$hyperkyc_release());
                        pairArr2[3] = TuplesKt.to("latestRuleRaw", this.$hyperKycResult.getLatestRuleRaw$hyperkyc_release());
                        Integer errorCode2 = this.$hyperKycResult.getErrorCode();
                        pairArr2[4] = TuplesKt.to("errorCode", errorCode2 != null ? errorCode2.toString() : null);
                        pairArr2[5] = TuplesKt.to("errorMessage", this.$hyperKycResult.getErrorMessage());
                        j = this.this$0.totalTimeSpent;
                        pairArr2[6] = TuplesKt.to("totalTimeSpent", String.valueOf(j));
                        Map mapOf2 = MapsKt.mapOf(pairArr2);
                        LinkedHashMap linkedHashMap22 = new LinkedHashMap(MapsKt.mapCapacity(mapOf2.size()));
                        while (r0.hasNext()) {
                        }
                        MainVM mainVM6 = this.this$0;
                        Result.Companion companion162 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(mainVM6.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        }
                        String str482 = (String) m1202constructorimpl2;
                        ArrayList arrayList42 = arrayList2;
                        String status2 = this.$hyperKycResult.getStatus();
                        MainVM mainVM22 = this.this$0;
                        Result.Companion companion172 = Result.INSTANCE;
                        String appId2 = mainVM22.getHyperKycConfig$hyperkyc_release().getAppId();
                        Intrinsics.checkNotNull(appId2);
                        m1202constructorimpl3 = Result.m1202constructorimpl(appId2);
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        }
                        String str492 = (String) m1202constructorimpl3;
                        failureReason = this.this$0.failureReason;
                        if (failureReason != null) {
                        }
                        failureReason2 = null;
                        MainVM mainVM322 = this.this$0;
                        Result.Companion companion1822 = Result.INSTANCE;
                        m1202constructorimpl4 = Result.m1202constructorimpl(mainVM322.getJourneyId$hyperkyc_release());
                        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                        }
                        String str5022 = (String) m1202constructorimpl4;
                        MainVM mainVM422 = this.this$0;
                        Result.Companion companion1922 = Result.INSTANCE;
                        m1202constructorimpl5 = Result.m1202constructorimpl(mainVM422.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release());
                        if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                        }
                        FinishReview finishReview22 = new FinishReview(str482, arrayList42, status2, str492, map2, linkedHashMap22, failureReason2, str5022, (String) m1202constructorimpl5, this.this$0.getHyperKycData().getApiFlags$hyperkyc_release(), null, null, 3072, null);
                        MainVM mainVM522 = this.this$0;
                        Result.Companion companion2022 = Result.INSTANCE;
                        selectedCountry = mainVM522.getSelectedCountry();
                        if (selectedCountry != null) {
                        }
                        baseUrl = "https://ind.idv.hyperverge.co";
                        NetworkRepo networkRepo22 = NetworkRepo.INSTANCE;
                        String str5122 = baseUrl + "/v2/finishTransaction";
                        defaultHeaders = mainVM522.getDefaultHeaders();
                        authHeaders = mainVM522.getAuthHeaders();
                        Map<String, String> plus22 = MapsKt.plus(defaultHeaders, authHeaders);
                        MainVM$performReviewFinish$2$3$1 mainVM$performReviewFinish$2$3$122 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$performReviewFinish$2$3$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th10) {
                                invoke2(th10);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                throw it;
                            }
                        };
                        this.L$0 = flowCollector;
                        FlowCollector flowCollector922 = flowCollector;
                        this.L$1 = null;
                        this.label = 2;
                        str12 = str11;
                        str13 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str16 = "replaceAll(\"\")";
                        str15 = str9;
                        str14 = "";
                        m399performReviewFinishyxL6bBk$hyperkyc_release = networkRepo22.m399performReviewFinishyxL6bBk$hyperkyc_release(str5122, finishReview22, plus22, mainVM$performReviewFinish$2$3$122, this);
                        obj4 = obj3;
                        if (m399performReviewFinishyxL6bBk$hyperkyc_release != obj4) {
                        }
                    } else {
                        str11 = str18;
                    }
                }
            }
            str11 = str18;
            str9 = str17;
        } else {
            arrayList2 = arrayList;
            str11 = str8;
            obj3 = obj2;
        }
        AnalyticsLogger.INSTANCE.logEndWorkflowEvent$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("status", this.$hyperKycResult.getStatus())));
        Map<String, String> details22 = this.$hyperKycResult.getDetails();
        LinkedHashMap linkedHashMap32 = new LinkedHashMap();
        while (r9.hasNext()) {
        }
        Map map22 = MapsKt.toMap(linkedHashMap32);
        Pair[] pairArr22 = new Pair[7];
        pairArr22[0] = TuplesKt.to("latestModule", this.$hyperKycResult.getLatestModule());
        pairArr22[1] = TuplesKt.to("latestCondition", this.$hyperKycResult.getLatestCondition$hyperkyc_release());
        pairArr22[2] = TuplesKt.to("latestRule", this.$hyperKycResult.getLatestRule$hyperkyc_release());
        pairArr22[3] = TuplesKt.to("latestRuleRaw", this.$hyperKycResult.getLatestRuleRaw$hyperkyc_release());
        Integer errorCode22 = this.$hyperKycResult.getErrorCode();
        pairArr22[4] = TuplesKt.to("errorCode", errorCode22 != null ? errorCode22.toString() : null);
        pairArr22[5] = TuplesKt.to("errorMessage", this.$hyperKycResult.getErrorMessage());
        j = this.this$0.totalTimeSpent;
        pairArr22[6] = TuplesKt.to("totalTimeSpent", String.valueOf(j));
        Map mapOf22 = MapsKt.mapOf(pairArr22);
        LinkedHashMap linkedHashMap222 = new LinkedHashMap(MapsKt.mapCapacity(mapOf22.size()));
        while (r0.hasNext()) {
        }
        MainVM mainVM62 = this.this$0;
        Result.Companion companion1622 = Result.INSTANCE;
        m1202constructorimpl2 = Result.m1202constructorimpl(mainVM62.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
        }
        String str4822 = (String) m1202constructorimpl2;
        ArrayList arrayList422 = arrayList2;
        String status22 = this.$hyperKycResult.getStatus();
        MainVM mainVM222 = this.this$0;
        Result.Companion companion1722 = Result.INSTANCE;
        String appId22 = mainVM222.getHyperKycConfig$hyperkyc_release().getAppId();
        Intrinsics.checkNotNull(appId22);
        m1202constructorimpl3 = Result.m1202constructorimpl(appId22);
        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
        }
        String str4922 = (String) m1202constructorimpl3;
        failureReason = this.this$0.failureReason;
        if (failureReason != null) {
        }
        failureReason2 = null;
        MainVM mainVM3222 = this.this$0;
        Result.Companion companion18222 = Result.INSTANCE;
        m1202constructorimpl4 = Result.m1202constructorimpl(mainVM3222.getJourneyId$hyperkyc_release());
        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
        }
        String str50222 = (String) m1202constructorimpl4;
        MainVM mainVM4222 = this.this$0;
        Result.Companion companion19222 = Result.INSTANCE;
        m1202constructorimpl5 = Result.m1202constructorimpl(mainVM4222.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release());
        if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
        }
        FinishReview finishReview222 = new FinishReview(str4822, arrayList422, status22, str4922, map22, linkedHashMap222, failureReason2, str50222, (String) m1202constructorimpl5, this.this$0.getHyperKycData().getApiFlags$hyperkyc_release(), null, null, 3072, null);
        MainVM mainVM5222 = this.this$0;
        Result.Companion companion20222 = Result.INSTANCE;
        selectedCountry = mainVM5222.getSelectedCountry();
        if (selectedCountry != null) {
        }
        baseUrl = "https://ind.idv.hyperverge.co";
        NetworkRepo networkRepo222 = NetworkRepo.INSTANCE;
        String str51222 = baseUrl + "/v2/finishTransaction";
        defaultHeaders = mainVM5222.getDefaultHeaders();
        authHeaders = mainVM5222.getAuthHeaders();
        Map<String, String> plus222 = MapsKt.plus(defaultHeaders, authHeaders);
        MainVM$performReviewFinish$2$3$1 mainVM$performReviewFinish$2$3$1222 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$performReviewFinish$2$3$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th10) {
                invoke2(th10);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                throw it;
            }
        };
        this.L$0 = flowCollector;
        FlowCollector flowCollector9222 = flowCollector;
        this.L$1 = null;
        this.label = 2;
        str12 = str11;
        str13 = "this as java.lang.String…ing(startIndex, endIndex)";
        str16 = "replaceAll(\"\")";
        str15 = str9;
        str14 = "";
        m399performReviewFinishyxL6bBk$hyperkyc_release = networkRepo222.m399performReviewFinishyxL6bBk$hyperkyc_release(str51222, finishReview222, plus222, mainVM$performReviewFinish$2$3$1222, this);
        obj4 = obj3;
        if (m399performReviewFinishyxL6bBk$hyperkyc_release != obj4) {
        }
    }
}
