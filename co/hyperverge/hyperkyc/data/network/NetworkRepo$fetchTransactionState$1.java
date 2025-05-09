package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateRequest;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
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
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u0016\u0012\u0012\u0012\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$fetchTransactionState$1", f = "NetworkRepo.kt", i = {0, 1, 1, 2}, l = {735, 738, 751, 755}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$invokeSuspend_u24lambda_u242", "$this$flow"}, s = {"L$0", "L$0", "L$1", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$fetchTransactionState$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ TransactionStateRequest $tStateRequest;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$fetchTransactionState$1(Map<String, String> map, TransactionStateRequest transactionStateRequest, Continuation<? super NetworkRepo$fetchTransactionState$1> continuation) {
        super(2, continuation);
        this.$headers = map;
        this.$tStateRequest = transactionStateRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$fetchTransactionState$1 networkRepo$fetchTransactionState$1 = new NetworkRepo$fetchTransactionState$1(this.$headers, this.$tStateRequest, continuation);
        networkRepo$fetchTransactionState$1.L$0 = obj;
        return networkRepo$fetchTransactionState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<TransactionStateResponse>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<TransactionStateResponse>> flowCollector, Continuation<? super Unit> continuation) {
        return ((NetworkRepo$fetchTransactionState$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:1|(1:(2:145|(1:(1:(3:149|122|123)(2:150|151))(9:152|153|154|40|41|42|(21:44|(1:121)(1:48)|(1:56)(1:53)|(1:55)|57|(1:59)(1:120)|60|(2:62|(13:64|65|(1:67)(1:117)|(1:69)(1:116)|70|71|72|73|(1:75)|76|(2:78|(12:80|(1:84)|(1:109)(1:105)|(1:107)(1:108)|86|(1:88)|89|(1:101)(1:93)|94|(1:96)(1:100)|(1:98)|99))|110|(1:112)))(1:119)|118|65|(0)(0)|(0)(0)|70|71|72|73|(0)|76|(0)|110|(0))|122|123))(12:155|156|157|15|16|17|18|(2:20|21)(1:133)|22|(2:24|25)|32|(4:34|35|36|(1:38)(7:39|40|41|42|(0)|122|123))(2:128|129)))(1:4))(15:160|(1:233)(1:164)|(1:173)(1:169)|(1:171)(1:172)|174|(1:176)|177|(1:232)(1:181)|182|(1:184)|185|(6:191|192|193|(1:195)|196|(2:198|(14:200|(1:228)(1:204)|(1:213)(1:209)|(1:211)(1:212)|214|(1:216)|217|(6:221|222|(1:224)|225|188|(1:190))|226|222|(0)|225|188|(0))))|187|188|(0))|5|6|7|8|9|10|(1:12)(10:14|15|16|17|18|(0)(0)|22|(0)|32|(0)(0))|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(21:44|(1:121)(1:48)|(1:56)(1:53)|(1:55)|57|(1:59)(1:120)|60|(2:62|(13:64|65|(1:67)(1:117)|(1:69)(1:116)|70|71|72|73|(1:75)|76|(2:78|(12:80|(1:84)|(1:109)(1:105)|(1:107)(1:108)|86|(1:88)|89|(1:101)(1:93)|94|(1:96)(1:100)|(1:98)|99))|110|(1:112)))(1:119)|118|65|(0)(0)|(0)(0)|70|71|72|73|(0)|76|(0)|110|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x045d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x045e, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x033f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0340, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x034b, code lost:
    
        r1 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0342, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0343, code lost:
    
        r23 = "replaceAll(\"\")";
        r19 = r13;
        r9 = r22;
        r4 = null;
        r13 = "this as java.lang.String…ing(startIndex, endIndex)";
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x00c4, code lost:
    
        if (r7 != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x01e8, code lost:
    
        if (r2 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0398, code lost:
    
        if (r5 != 0) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x04b4, code lost:
    
        if (r5 != null) goto L210;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0558 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0291 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x02de A[Catch: all -> 0x02e8, TRY_LEAVE, TryCatch #4 {all -> 0x02e8, blocks: (B:18:0x02d5, B:20:0x02de), top: B:17:0x02d5 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x02ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02fd A[Catch: all -> 0x033b, TRY_LEAVE, TryCatch #6 {all -> 0x033b, blocks: (B:16:0x02cd, B:31:0x02f8, B:34:0x02fd, B:25:0x02ee), top: B:15:0x02cd, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0477  */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v14, types: [T] */
    /* JADX WARN: Type inference failed for: r2v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r4v30, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v36, types: [T] */
    /* JADX WARN: Type inference failed for: r5v44, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v56, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v57 */
    /* JADX WARN: Type inference failed for: r5v58 */
    /* JADX WARN: Type inference failed for: r5v59 */
    /* JADX WARN: Type inference failed for: r5v63, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v67 */
    /* JADX WARN: Type inference failed for: r5v68 */
    /* JADX WARN: Type inference failed for: r5v7, types: [T] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v4, types: [T] */
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
        NetworkRepo$fetchTransactionState$1 networkRepo$fetchTransactionState$1;
        Object obj3;
        Class<?> cls2;
        String className2;
        String str7;
        String str8;
        String str9;
        String str10;
        Throwable th;
        Object m1202constructorimpl2;
        FlowCollector flowCollector2;
        Throwable m1205exceptionOrNullimpl;
        Object obj4;
        Object obj5;
        ?? r5;
        String str11;
        int i;
        String str12;
        String localizedMessage;
        String str13;
        Object m1202constructorimpl3;
        NetworkUIState.Failed failed;
        Object obj6;
        String str14;
        Class<?> cls3;
        String str15;
        String className3;
        Class<?> cls4;
        String className4;
        FlowCollector flowCollector3;
        FlowCollector flowCollector4;
        Object obj7;
        Response response;
        Throwable th2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        ?? r1 = 1;
        String str16 = "";
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            Map<String, String> map = this.$headers;
            TransactionStateRequest transactionStateRequest = this.$tStateRequest;
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
            String canonicalName = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? null : cls2.getCanonicalName();
            str2 = canonicalName == null ? "N/A" : canonicalName;
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
            String str17 = "fetchTransactionState() called with: headers = [" + map + "], transactionStateRequest = [" + transactionStateRequest + ']';
            if (str17 == null) {
                str17 = "null ";
            }
            sb.append(str17);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th3) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                        String str18 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str18);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = str18;
                            str4 = null;
                        } else {
                            str = str18;
                            str4 = null;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str5 = substringAfterLast$default2;
                        }
                        String canonicalName2 = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? str4 : cls.getCanonicalName();
                        str5 = canonicalName2 == null ? "N/A" : canonicalName2;
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
                            String str19 = "fetchTransactionState() called with: headers = [" + map + "], transactionStateRequest = [" + transactionStateRequest + ']';
                            sb2.append(str19 != null ? str19 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str6, sb2.toString());
                            networkRepo$fetchTransactionState$1 = this;
                            networkRepo$fetchTransactionState$1.L$0 = flowCollector;
                            networkRepo$fetchTransactionState$1.label = 1;
                            obj3 = obj2;
                            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, networkRepo$fetchTransactionState$1) == obj3) {
                                return obj3;
                            }
                        }
                        str6 = (String) objectRef2.element;
                        StringBuilder sb22 = new StringBuilder();
                        String str192 = "fetchTransactionState() called with: headers = [" + map + "], transactionStateRequest = [" + transactionStateRequest + ']';
                        sb22.append(str192 != null ? str192 : "null ");
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(3, str6, sb22.toString());
                        networkRepo$fetchTransactionState$1 = this;
                        networkRepo$fetchTransactionState$1.L$0 = flowCollector;
                        networkRepo$fetchTransactionState$1.label = 1;
                        obj3 = obj2;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, networkRepo$fetchTransactionState$1) == obj3) {
                        }
                    }
                }
            }
            networkRepo$fetchTransactionState$1 = this;
            networkRepo$fetchTransactionState$1.L$0 = flowCollector;
            networkRepo$fetchTransactionState$1.label = 1;
            obj3 = obj2;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, networkRepo$fetchTransactionState$1) == obj3) {
            }
        } else {
            if (i2 != 1) {
                try {
                } catch (Throwable th4) {
                    th = th4;
                    str7 = "replaceAll(\"\")";
                    str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj3 = coroutine_suspended;
                    networkRepo$fetchTransactionState$1 = this;
                    str9 = "Throwable().stackTrace";
                    str10 = null;
                    Result.Companion companion4 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    flowCollector2 = r1;
                    FlowCollector flowCollector5 = flowCollector2;
                    Object obj8 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj8);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (i2 == 2) {
                    FlowCollector flowCollector6 = (FlowCollector) this.L$1;
                    FlowCollector flowCollector7 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowCollector3 = flowCollector7;
                    str7 = "replaceAll(\"\")";
                    str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj3 = coroutine_suspended;
                    flowCollector4 = flowCollector6;
                    networkRepo$fetchTransactionState$1 = this;
                    obj7 = ((Result) obj).getValue();
                    str9 = "Throwable().stackTrace";
                    try {
                        ResultKt.throwOnFailure(obj7);
                        response = (Response) obj7;
                        try {
                            ResponseBody body = response.body();
                            str10 = body == null ? body.string() : null;
                            th2 = null;
                        } catch (Throwable th5) {
                            th2 = th5;
                            str10 = null;
                        }
                        if (response != null) {
                            try {
                                response.close();
                            } catch (Throwable th6) {
                                if (th2 == null) {
                                    th2 = th6;
                                } else {
                                    ExceptionsKt.addSuppressed(th2, th6);
                                }
                            }
                        }
                        try {
                        } catch (Throwable th7) {
                            th = th7;
                            r1 = flowCollector3;
                            Result.Companion companion42 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            flowCollector2 = r1;
                            FlowCollector flowCollector52 = flowCollector2;
                            Object obj82 = m1202constructorimpl2;
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj82);
                            if (m1205exceptionOrNullimpl != null) {
                            }
                            return Unit.INSTANCE;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        str10 = null;
                        r1 = flowCollector3;
                        Result.Companion companion422 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        flowCollector2 = r1;
                        FlowCollector flowCollector522 = flowCollector2;
                        Object obj822 = m1202constructorimpl2;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj822);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (th2 == null) {
                        throw th2;
                    }
                    Intrinsics.checkNotNull(str10);
                    NetworkUIState.Success success = new NetworkUIState.Success((TransactionStateResponse) new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create().fromJson(str10, TransactionStateResponse.class));
                    networkRepo$fetchTransactionState$1.L$0 = flowCollector3;
                    str10 = null;
                    networkRepo$fetchTransactionState$1.L$1 = null;
                    networkRepo$fetchTransactionState$1.label = 3;
                    if (flowCollector4.emit(success, networkRepo$fetchTransactionState$1) == obj3) {
                        return obj3;
                    }
                    r1 = flowCollector3;
                    m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                    flowCollector2 = r1;
                    FlowCollector flowCollector5222 = flowCollector2;
                    Object obj8222 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj8222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (i2 != 3) {
                    if (i2 != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector8 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                str7 = "replaceAll(\"\")";
                str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                obj3 = coroutine_suspended;
                networkRepo$fetchTransactionState$1 = this;
                str9 = "Throwable().stackTrace";
                str10 = null;
                r1 = flowCollector8;
                try {
                    m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                    flowCollector2 = r1;
                } catch (Throwable th9) {
                    th = th9;
                    Result.Companion companion4222 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    flowCollector2 = r1;
                    FlowCollector flowCollector52222 = flowCollector2;
                    Object obj82222 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj82222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                FlowCollector flowCollector522222 = flowCollector2;
                Object obj822222 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj822222);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str9);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null) {
                        obj4 = obj822222;
                        obj5 = obj3;
                    } else {
                        obj4 = obj822222;
                        obj5 = obj3;
                        r5 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, str10, 2, str10);
                    }
                    r5 = (flowCollector522222 == null || (cls4 = flowCollector522222.getClass()) == null) ? str10 : cls4.getCanonicalName();
                    if (r5 == 0) {
                        r5 = "N/A";
                    }
                    objectRef3.element = r5;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher3.find()) {
                        ?? replaceAll3 = matcher3.replaceAll("");
                        str11 = str7;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str11);
                        objectRef3.element = replaceAll3;
                    } else {
                        str11 = str7;
                    }
                    if (((String) objectRef3.element).length() > 23) {
                        i = 26;
                        if (Build.VERSION.SDK_INT < 26) {
                            str12 = ((String) objectRef3.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str12, str8);
                            sb3.append(str12);
                            sb3.append(" - ");
                            sb3.append("fetchTransactionState: failed");
                            sb3.append(' ');
                            localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage == null) {
                                str13 = '\n' + localizedMessage;
                            } else {
                                str13 = "";
                            }
                            sb3.append(str13);
                            companion5.log(level2, sb3.toString());
                            CoreExtsKt.isRelease();
                            Result.Companion companion6 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String packageName2 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str9);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 != null && (className3 = stackTraceElement4.getClassName()) != null) {
                                        String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        str14 = substringAfterLast$default3;
                                    }
                                    String canonicalName3 = (flowCollector522222 == null || (cls3 = flowCollector522222.getClass()) == null) ? null : cls3.getCanonicalName();
                                    str14 = canonicalName3 == null ? "N/A" : canonicalName3;
                                    objectRef4.element = str14;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, str11);
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= i) {
                                        str15 = (String) objectRef4.element;
                                    } else {
                                        str15 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str15, str8);
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("fetchTransactionState: failed");
                                    sb4.append(' ');
                                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                    if (localizedMessage2 != null) {
                                        str16 = '\n' + localizedMessage2;
                                    }
                                    sb4.append(str16);
                                    Log.println(6, str15, sb4.toString());
                                }
                            }
                            failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), m1205exceptionOrNullimpl);
                            networkRepo$fetchTransactionState$1.L$0 = obj4;
                            networkRepo$fetchTransactionState$1.L$1 = null;
                            networkRepo$fetchTransactionState$1.label = 4;
                            obj6 = obj5;
                            if (flowCollector522222.emit(failed, networkRepo$fetchTransactionState$1) == obj6) {
                                return obj6;
                            }
                        }
                    } else {
                        i = 26;
                    }
                    str12 = (String) objectRef3.element;
                    sb3.append(str12);
                    sb3.append(" - ");
                    sb3.append("fetchTransactionState: failed");
                    sb3.append(' ');
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    if (localizedMessage == null) {
                    }
                    sb3.append(str13);
                    companion5.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    Result.Companion companion62 = Result.INSTANCE;
                    Object invoke22 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke22, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    }
                    String packageName22 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                    }
                    failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), m1205exceptionOrNullimpl);
                    networkRepo$fetchTransactionState$1.L$0 = obj4;
                    networkRepo$fetchTransactionState$1.L$1 = null;
                    networkRepo$fetchTransactionState$1.label = 4;
                    obj6 = obj5;
                    if (flowCollector522222.emit(failed, networkRepo$fetchTransactionState$1) == obj6) {
                    }
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector9 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector9;
            str = "Throwable().stackTrace";
            obj3 = coroutine_suspended;
            networkRepo$fetchTransactionState$1 = this;
        }
        Map<String, String> map2 = networkRepo$fetchTransactionState$1.$headers;
        TransactionStateRequest transactionStateRequest2 = networkRepo$fetchTransactionState$1.$tStateRequest;
        Result.Companion companion7 = Result.INSTANCE;
        String json = new Gson().toJson(transactionStateRequest2);
        NetworkRepo$fetchTransactionState$1$2$response$1 networkRepo$fetchTransactionState$1$2$response$1 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$fetchTransactionState$1$2$response$1
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
        networkRepo$fetchTransactionState$1.L$0 = flowCollector;
        networkRepo$fetchTransactionState$1.L$1 = flowCollector;
        networkRepo$fetchTransactionState$1.label = 2;
        str9 = str;
        str7 = "replaceAll(\"\")";
        FlowCollector flowCollector10 = flowCollector;
        str8 = "this as java.lang.String…ing(startIndex, endIndex)";
        obj7 = NetworkHelpersKt.apiPost$default("https://ind-state.idv.hyperverge.co/get-transaction-state", map2, json, null, networkRepo$fetchTransactionState$1$2$response$1, this, 8, null);
        if (obj7 == obj3) {
            return obj3;
        }
        flowCollector4 = flowCollector10;
        flowCollector3 = flowCollector4;
        ResultKt.throwOnFailure(obj7);
        response = (Response) obj7;
        ResponseBody body2 = response.body();
        str10 = body2 == null ? body2.string() : null;
        th2 = null;
        if (response != null) {
        }
        if (th2 == null) {
        }
    }
}
