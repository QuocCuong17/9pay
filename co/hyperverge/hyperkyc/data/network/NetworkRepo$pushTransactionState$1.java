package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.state.TransactionState;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.firebase.database.core.ValidationPath;
import com.google.gson.Gson;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "Lco/hyperverge/hyperkyc/ui/custom/SaveStateUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$pushTransactionState$1", f = "NetworkRepo.kt", i = {0, 1, 1, 2}, l = {ValidationPath.MAX_PATH_LENGTH_BYTES, 770, 780, 784, 786}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$invokeSuspend_u24lambda_u242", "$this$flow"}, s = {"L$0", "L$0", "L$1", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$pushTransactionState$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ TransactionState $transactionState;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$pushTransactionState$1(TransactionState transactionState, Map<String, String> map, Continuation<? super NetworkRepo$pushTransactionState$1> continuation) {
        super(2, continuation);
        this.$transactionState = transactionState;
        this.$headers = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$pushTransactionState$1 networkRepo$pushTransactionState$1 = new NetworkRepo$pushTransactionState$1(this.$transactionState, this.$headers, continuation);
        networkRepo$pushTransactionState$1.L$0 = obj;
        return networkRepo$pushTransactionState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<TransactionStateResponse>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<TransactionStateResponse>> flowCollector, Continuation<? super Unit> continuation) {
        return ((NetworkRepo$pushTransactionState$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:1|(1:(2:136|(1:(1:(3:143|112|113)(2:141|142))(8:144|145|146|27|28|(20:30|(1:111)(1:34)|(1:42)(1:39)|(1:41)|43|(1:45)(1:110)|46|(1:109)(1:50)|51|(1:53)(1:108)|(1:55)(1:107)|56|57|58|59|(1:61)|62|(2:64|(10:66|(2:(1:95)(1:92)|(1:94))|72|(1:74)|75|(1:88)(1:79)|80|(1:82)(1:87)|(1:84)(1:86)|85))|96|(2:98|(1:100))(2:101|(1:103)))|112|113))(11:147|148|149|15|16|17|18|(1:20)(1:124)|21|(2:115|116)|(7:24|(1:26)|27|28|(0)|112|113)(1:114)))(1:4))(15:152|(1:226)(1:156)|(1:165)(1:161)|(1:163)(1:164)|166|(1:168)|169|(1:225)(1:173)|174|(1:176)|177|(6:183|184|185|(1:187)|188|(2:190|(14:192|(1:221)(1:196)|(1:205)(1:201)|(1:203)(1:204)|206|(1:208)|209|(6:219|215|(1:217)|218|180|(1:182))|214|215|(0)|218|180|(0))))|179|180|(0))|5|6|7|8|9|10|(1:12)(9:14|15|16|17|18|(0)(0)|21|(0)|(0)(0))|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x033e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x034a, code lost:
    
        r1 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0340, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0341, code lost:
    
        r24 = "replaceAll(\"\")";
        r25 = "";
        r26 = "this as java.lang.String…ing(startIndex, endIndex)";
        r15 = r23;
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x00d3, code lost:
    
        if (r8 != null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x01f9, code lost:
    
        if (r2 != null) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0392, code lost:
    
        if (r8 != 0) goto L156;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x033d A[Catch: all -> 0x033b, TRY_LEAVE, TryCatch #3 {all -> 0x033b, blocks: (B:27:0x0334, B:16:0x02e2, B:122:0x030c, B:24:0x0311, B:114:0x033d, B:116:0x0302), top: B:15:0x02e2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0302 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02a4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x02f3 A[Catch: all -> 0x02fc, TRY_LEAVE, TryCatch #4 {all -> 0x02fc, blocks: (B:18:0x02ea, B:20:0x02f3), top: B:17:0x02ea }] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0311 A[Catch: all -> 0x033b, TryCatch #3 {all -> 0x033b, blocks: (B:27:0x0334, B:16:0x02e2, B:122:0x030c, B:24:0x0311, B:114:0x033d, B:116:0x0302), top: B:15:0x02e2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x035d  */
    /* JADX WARN: Type inference failed for: r0v37, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v63, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v13, types: [T] */
    /* JADX WARN: Type inference failed for: r2v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r3v31, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v16, types: [T] */
    /* JADX WARN: Type inference failed for: r5v26, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v22, types: [T] */
    /* JADX WARN: Type inference failed for: r8v33, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v36 */
    /* JADX WARN: Type inference failed for: r8v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v5, types: [T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        String str;
        Object obj2;
        String str2;
        String str3;
        Object m1202constructorimpl;
        String str4;
        String str5;
        String str6;
        Class<?> cls;
        String className;
        NetworkRepo$pushTransactionState$1 networkRepo$pushTransactionState$1;
        Object obj3;
        Class<?> cls2;
        String className2;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        Throwable th;
        Object m1202constructorimpl2;
        FlowCollector flowCollector2;
        Throwable m1205exceptionOrNullimpl;
        ?? r8;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        ?? canonicalName;
        Class<?> cls3;
        String str18;
        String str19;
        String className3;
        Class<?> cls4;
        String className4;
        FlowCollector flowCollector3;
        FlowCollector flowCollector4;
        Object obj4;
        Response response;
        ?? r3;
        String str20;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        ?? r1 = 1;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            TransactionState transactionState = this.$transactionState;
            Map<String, String> map = this.$headers;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str = "Throwable().stackTrace";
                obj2 = coroutine_suspended;
            } else {
                str = "Throwable().stackTrace";
                obj2 = coroutine_suspended;
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str2 = substringAfterLast$default;
            }
            String canonicalName2 = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? null : cls2.getCanonicalName();
            str2 = canonicalName2 == null ? "N/A" : canonicalName2;
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
            String str21 = "pushTransactionState() called with: transactionState = [" + transactionState + "], headers = [" + map + ']';
            if (str21 == null) {
                str21 = "null ";
            }
            sb.append(str21);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
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
                        String str22 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str22);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = str22;
                            str4 = null;
                        } else {
                            str = str22;
                            str4 = null;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str5 = substringAfterLast$default2;
                        }
                        String canonicalName3 = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? str4 : cls.getCanonicalName();
                        str5 = canonicalName3 == null ? "N/A" : canonicalName3;
                        objectRef2.element = str5;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str6 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            StringBuilder sb2 = new StringBuilder();
                            String str23 = "pushTransactionState() called with: transactionState = [" + transactionState + "], headers = [" + map + ']';
                            sb2.append(str23 != null ? str23 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str6, sb2.toString());
                            networkRepo$pushTransactionState$1 = this;
                            networkRepo$pushTransactionState$1.L$0 = flowCollector;
                            networkRepo$pushTransactionState$1.label = 1;
                            obj3 = obj2;
                            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, networkRepo$pushTransactionState$1) == obj3) {
                                return obj3;
                            }
                        }
                        str6 = (String) objectRef2.element;
                        StringBuilder sb22 = new StringBuilder();
                        String str232 = "pushTransactionState() called with: transactionState = [" + transactionState + "], headers = [" + map + ']';
                        sb22.append(str232 != null ? str232 : "null ");
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(3, str6, sb22.toString());
                        networkRepo$pushTransactionState$1 = this;
                        networkRepo$pushTransactionState$1.L$0 = flowCollector;
                        networkRepo$pushTransactionState$1.label = 1;
                        obj3 = obj2;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, networkRepo$pushTransactionState$1) == obj3) {
                        }
                    }
                }
            }
            networkRepo$pushTransactionState$1 = this;
            networkRepo$pushTransactionState$1.L$0 = flowCollector;
            networkRepo$pushTransactionState$1.label = 1;
            obj3 = obj2;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, networkRepo$pushTransactionState$1) == obj3) {
            }
        } else {
            if (i != 1) {
                try {
                } catch (Throwable th3) {
                    th = th3;
                    str10 = "Throwable().stackTrace";
                    str9 = "replaceAll(\"\")";
                    str8 = "";
                    str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj3 = coroutine_suspended;
                    networkRepo$pushTransactionState$1 = this;
                    str11 = null;
                    Result.Companion companion4 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    flowCollector2 = r1;
                    FlowCollector flowCollector5 = flowCollector2;
                    Object obj5 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (i == 2) {
                    FlowCollector flowCollector6 = (FlowCollector) this.L$1;
                    FlowCollector flowCollector7 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowCollector = flowCollector6;
                    str10 = "Throwable().stackTrace";
                    str9 = "replaceAll(\"\")";
                    str8 = "";
                    str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj3 = coroutine_suspended;
                    obj4 = ((Result) obj).getValue();
                    networkRepo$pushTransactionState$1 = this;
                    str11 = null;
                    flowCollector4 = flowCollector7;
                    try {
                        ResultKt.throwOnFailure(obj4);
                        response = (Response) obj4;
                        try {
                            ResponseBody body = response.body();
                            str20 = body == null ? body.string() : str11;
                            r3 = str11;
                        } catch (Throwable th4) {
                            r3 = th4;
                            str20 = str11;
                        }
                        if (response != null) {
                            try {
                                response.close();
                            } catch (Throwable th5) {
                                if (r3 == 0) {
                                    r3 = th5;
                                } else {
                                    ExceptionsKt.addSuppressed(r3, th5);
                                }
                            }
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        r1 = flowCollector4;
                        Result.Companion companion42 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        flowCollector2 = r1;
                        FlowCollector flowCollector52 = flowCollector2;
                        Object obj52 = m1202constructorimpl2;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj52);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (r3 == 0) {
                        throw r3;
                    }
                    Intrinsics.checkNotNull(str20);
                    NetworkUIState.Success success = new NetworkUIState.Success((TransactionStateResponse) new Gson().fromJson(str20, TransactionStateResponse.class));
                    networkRepo$pushTransactionState$1.L$0 = flowCollector4;
                    networkRepo$pushTransactionState$1.L$1 = str11;
                    networkRepo$pushTransactionState$1.label = 3;
                    flowCollector3 = flowCollector4;
                    if (flowCollector.emit(success, networkRepo$pushTransactionState$1) == obj3) {
                        return obj3;
                    }
                    m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                    flowCollector2 = flowCollector3;
                    FlowCollector flowCollector522 = flowCollector2;
                    Object obj522 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj522);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    if (i != 4 && i != 5) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector8 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                str10 = "Throwable().stackTrace";
                str9 = "replaceAll(\"\")";
                str8 = "";
                str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                obj3 = coroutine_suspended;
                networkRepo$pushTransactionState$1 = this;
                str11 = null;
                flowCollector3 = flowCollector8;
                m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                flowCollector2 = flowCollector3;
                FlowCollector flowCollector5222 = flowCollector2;
                Object obj5222 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5222);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str10);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 != null && (className4 = stackTraceElement3.getClassName()) != null) {
                        r8 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, str11, 2, str11);
                    }
                    r8 = (flowCollector5222 == null || (cls4 = flowCollector5222.getClass()) == null) ? str11 : cls4.getCanonicalName();
                    if (r8 == 0) {
                        r8 = "N/A";
                    }
                    objectRef3.element = r8;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher3.find()) {
                        str13 = str8;
                        ?? replaceAll3 = matcher3.replaceAll(str13);
                        str12 = str9;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str12);
                        objectRef3.element = replaceAll3;
                    } else {
                        str12 = str9;
                        str13 = str8;
                    }
                    if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str14 = str7;
                        str15 = (String) objectRef3.element;
                    } else {
                        str15 = ((String) objectRef3.element).substring(0, 23);
                        str14 = str7;
                        Intrinsics.checkNotNullExpressionValue(str15, str14);
                    }
                    sb3.append(str15);
                    sb3.append(" - ");
                    sb3.append("pushTransactionState: failed");
                    sb3.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str16 = '\n' + localizedMessage;
                    } else {
                        str16 = str13;
                    }
                    sb3.append(str16);
                    companion5.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        str17 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th7) {
                        Result.Companion companion7 = Result.INSTANCE;
                        str17 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                    }
                    String str24 = str17;
                    if (Result.m1208isFailureimpl(str24)) {
                        str24 = str13;
                    }
                    String packageName2 = str24;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str10);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                canonicalName = (flowCollector5222 == null || (cls3 = flowCollector5222.getClass()) == null) ? 0 : cls3.getCanonicalName();
                                if (canonicalName == 0) {
                                    canonicalName = "N/A";
                                }
                            }
                            objectRef4.element = canonicalName;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                            if (matcher4.find()) {
                                ?? replaceAll4 = matcher4.replaceAll(str13);
                                Intrinsics.checkNotNullExpressionValue(replaceAll4, str12);
                                objectRef4.element = replaceAll4;
                            }
                            if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str18 = (String) objectRef4.element;
                            } else {
                                str18 = ((String) objectRef4.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str18, str14);
                            }
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("pushTransactionState: failed");
                            sb4.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str19 = '\n' + localizedMessage2;
                            } else {
                                str19 = str13;
                            }
                            sb4.append(str19);
                            Log.println(6, str18, sb4.toString());
                        }
                    }
                    if (CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl)) {
                        NetworkUIState.NetworkFailure networkFailure = NetworkUIState.NetworkFailure.INSTANCE;
                        networkRepo$pushTransactionState$1.L$0 = obj5222;
                        networkRepo$pushTransactionState$1.L$1 = null;
                        networkRepo$pushTransactionState$1.label = 4;
                        if (flowCollector5222.emit(networkFailure, networkRepo$pushTransactionState$1) == obj3) {
                            return obj3;
                        }
                    } else {
                        NetworkUIState.Failed failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), m1205exceptionOrNullimpl);
                        networkRepo$pushTransactionState$1.L$0 = obj5222;
                        networkRepo$pushTransactionState$1.L$1 = null;
                        networkRepo$pushTransactionState$1.label = 5;
                        if (flowCollector5222.emit(failed, networkRepo$pushTransactionState$1) == obj3) {
                            return obj3;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector9 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector9;
            str = "Throwable().stackTrace";
            obj3 = coroutine_suspended;
            networkRepo$pushTransactionState$1 = this;
        }
        Map<String, String> map2 = networkRepo$pushTransactionState$1.$headers;
        TransactionState transactionState2 = networkRepo$pushTransactionState$1.$transactionState;
        Result.Companion companion8 = Result.INSTANCE;
        String json = new Gson().toJson(transactionState2);
        NetworkRepo$pushTransactionState$1$2$response$1 networkRepo$pushTransactionState$1$2$response$1 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$pushTransactionState$1$2$response$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th8) {
                invoke2(th8);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                throw it;
            }
        };
        networkRepo$pushTransactionState$1.L$0 = flowCollector;
        networkRepo$pushTransactionState$1.L$1 = flowCollector;
        networkRepo$pushTransactionState$1.label = 2;
        str11 = null;
        str10 = str;
        str9 = "replaceAll(\"\")";
        str8 = "";
        str7 = "this as java.lang.String…ing(startIndex, endIndex)";
        obj4 = NetworkHelpersKt.apiPost$default("https://ind-state.idv.hyperverge.co/put-transaction-state", map2, json, null, networkRepo$pushTransactionState$1$2$response$1, this, 8, null);
        if (obj4 == obj3) {
            return obj3;
        }
        flowCollector4 = flowCollector;
        ResultKt.throwOnFailure(obj4);
        response = (Response) obj4;
        ResponseBody body2 = response.body();
        str20 = body2 == null ? body2.string() : str11;
        r3 = str11;
        if (response != null) {
        }
        if (r3 == 0) {
        }
    }
}
