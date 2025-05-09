package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import co.hyperverge.hyperkyc.data.models.StartTransaction;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.Map;
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
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lkotlin/Result;", "Lokhttp3/Response;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$performStartTransactionApiCall$1", f = "MainVM.kt", i = {0, 1, 2}, l = {499, TypedValues.PositionType.TYPE_DRAWPATH, 509, 512}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$performStartTransactionApiCall$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends Result<? extends Response>>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ StartTransaction $startTransaction;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$performStartTransactionApiCall$1(StartTransaction startTransaction, Map<String, String> map, Continuation<? super MainVM$performStartTransactionApiCall$1> continuation) {
        super(2, continuation);
        this.$startTransaction = startTransaction;
        this.$headers = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$performStartTransactionApiCall$1 mainVM$performStartTransactionApiCall$1 = new MainVM$performStartTransactionApiCall$1(this.$startTransaction, this.$headers, continuation);
        mainVM$performStartTransactionApiCall$1.L$0 = obj;
        return mainVM$performStartTransactionApiCall$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends Result<? extends Response>>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<Result<Response>>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<Result<Response>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$performStartTransactionApiCall$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(1:(2:4|(2:6|(2:8|(3:10|11|12)(2:14|15))(6:16|17|18|(14:20|(1:90)(1:24)|(1:33)(1:29)|(1:31)(1:32)|34|(1:36)|37|(1:89)(1:41)|42|(1:44)|45|(6:50|51|52|(1:54)|55|(2:57|(11:59|(1:63)|(1:85)(1:81)|(1:83)(1:84)|65|(1:67)|68|(1:77)(1:72)|73|(1:75)|76)))|47|(1:49))(1:91)|11|12))(7:92|93|94|95|96|97|(25:99|(1:189)(1:103)|(1:111)(1:108)|(1:110)|112|(1:114)|115|(1:188)(1:119)|120|(1:122)|123|(1:125)(1:187)|(1:127)(1:186)|128|129|130|131|132|133|134|(1:136)|137|(2:139|(12:141|(2:(1:177)(1:174)|(1:176))|147|(1:149)|150|(1:170)(1:154)|155|(1:157)|158|(1:160)(1:169)|(1:162)(1:168)|163)(1:178))(1:179)|164|(1:166)(6:167|17|18|(0)(0)|11|12))(5:190|18|(0)(0)|11|12)))(1:197))(13:209|(1:273)(1:213)|(1:221)(1:218)|(1:220)|222|(1:224)|225|(1:272)(1:229)|230|(6:236|237|238|(1:240)|241|(4:243|(7:245|(2:(1:267)(1:264)|(1:266))|251|(1:253)|254|(1:260)(1:258)|259)(1:268)|233|(1:235)))|232|233|(0))|198|199|200|201|202|203|(1:205)|96|97|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02de, code lost:
    
        if (r1 != 0) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x028a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x028c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x028d, code lost:
    
        r23 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x00d3, code lost:
    
        if (r1 != 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x052c, code lost:
    
        if (r6 != null) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0640, code lost:
    
        if (r1 != null) goto L259;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x04d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0257 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02a0  */
    /* JADX WARN: Type inference failed for: r1v111 */
    /* JADX WARN: Type inference failed for: r1v112 */
    /* JADX WARN: Type inference failed for: r1v114, types: [T] */
    /* JADX WARN: Type inference failed for: r1v124, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v137, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v146 */
    /* JADX WARN: Type inference failed for: r1v147 */
    /* JADX WARN: Type inference failed for: r1v148 */
    /* JADX WARN: Type inference failed for: r1v29, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v47, types: [T] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v78, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v79 */
    /* JADX WARN: Type inference failed for: r1v80 */
    /* JADX WARN: Type inference failed for: r1v81 */
    /* JADX WARN: Type inference failed for: r1v85, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v13, types: [T] */
    /* JADX WARN: Type inference failed for: r2v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v25, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v83 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v24, types: [T] */
    /* JADX WARN: Type inference failed for: r3v34, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r6v52, types: [T] */
    /* JADX WARN: Type inference failed for: r6v56 */
    /* JADX WARN: Type inference failed for: r6v57 */
    /* JADX WARN: Type inference failed for: r6v64 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        String str;
        CharSequence charSequence;
        String str2;
        String str3;
        ?? r1;
        String str4;
        Object m1202constructorimpl;
        String str5;
        String str6;
        ?? canonicalName;
        Class<?> cls;
        String str7;
        String className;
        MainVM$performStartTransactionApiCall$1 mainVM$performStartTransactionApiCall$1;
        Object obj2;
        Class<?> cls2;
        String className2;
        String str8;
        Object m400startTransactionBWLJW6A$hyperkyc_release;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        Object obj4;
        String str9;
        String str10;
        MainVM$performStartTransactionApiCall$1 mainVM$performStartTransactionApiCall$12;
        String str11;
        String str12;
        String str13;
        ?? r12;
        String str14;
        String str15;
        Object m1202constructorimpl3;
        NetworkUIState.Failed failed;
        Object obj5;
        ?? canonicalName2;
        Class<?> cls3;
        String str16;
        String str17;
        String className3;
        Class<?> cls4;
        String className4;
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        Object m1202constructorimpl4;
        String str23;
        Class<?> cls5;
        String str24;
        String className5;
        Class<?> cls6;
        String className6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            str = "null ";
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                charSequence = "co.hyperverge";
                str2 = "packageName";
                str3 = "Throwable().stackTrace";
            } else {
                charSequence = "co.hyperverge";
                str2 = "packageName";
                str3 = "Throwable().stackTrace";
                r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            r1 = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? 0 : cls2.getCanonicalName();
            if (r1 == 0) {
                r1 = "N/A";
            }
            objectRef.element = r1;
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str4 = (String) objectRef.element;
            } else {
                str4 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str4);
            sb.append(" - ");
            sb.append("performStartTransactionApiCall() called");
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
                String str25 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str5 = str2;
                    Intrinsics.checkNotNullExpressionValue(str25, str5);
                    if (StringsKt.contains$default((CharSequence) str25, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str6 = str3;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? 0 : cls.getCanonicalName();
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
                            str7 = (String) objectRef2.element;
                        } else {
                            str7 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str7, "performStartTransactionApiCall() called ");
                    } else {
                        str6 = str3;
                    }
                    mainVM$performStartTransactionApiCall$1 = this;
                    mainVM$performStartTransactionApiCall$1.L$0 = flowCollector;
                    mainVM$performStartTransactionApiCall$1.label = 1;
                    obj2 = coroutine_suspended;
                    if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$performStartTransactionApiCall$1) == obj2) {
                        return obj2;
                    }
                }
            }
            str6 = str3;
            str5 = str2;
            mainVM$performStartTransactionApiCall$1 = this;
            mainVM$performStartTransactionApiCall$1.L$0 = flowCollector;
            mainVM$performStartTransactionApiCall$1.label = 1;
            obj2 = coroutine_suspended;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$performStartTransactionApiCall$1) == obj2) {
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    obj5 = this.L$1;
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mainVM$performStartTransactionApiCall$12 = this;
                    str9 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str = "null ";
                    str10 = "Throwable().stackTrace";
                    obj3 = coroutine_suspended;
                    str11 = "packageName";
                    obj4 = obj5;
                    if (Result.m1209isSuccessimpl(obj4)) {
                        Object value = ((Result) obj4).getValue();
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        Object obj6 = obj3;
                        StringBuilder sb2 = new StringBuilder();
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        Object obj7 = obj4;
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str10);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null) {
                            str18 = str9;
                            str19 = str11;
                            str20 = str10;
                        } else {
                            str18 = str9;
                            str19 = str11;
                            str20 = str10;
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str21 = substringAfterLast$default;
                        }
                        String canonicalName3 = (flowCollector == null || (cls6 = flowCollector.getClass()) == null) ? null : cls6.getCanonicalName();
                        str21 = canonicalName3 == null ? "N/A" : canonicalName3;
                        objectRef3.element = str21;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                        if (matcher3.find()) {
                            ?? replaceAll3 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                            objectRef3.element = replaceAll3;
                        }
                        if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str22 = (String) objectRef3.element;
                        } else {
                            str22 = ((String) objectRef3.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str22, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(str22);
                        sb2.append(" - ");
                        String str26 = "performStartTransactionApiCall: result : " + ((Object) Result.m1210toStringimpl(value));
                        if (str26 == null) {
                            str26 = str;
                        }
                        sb2.append(str26);
                        sb2.append(' ');
                        sb2.append("");
                        companion4.log(level2, sb2.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, str18);
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th2) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String str27 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str27, str19);
                                if (StringsKt.contains$default((CharSequence) str27, charSequence, false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str20);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 != null && (className5 = stackTraceElement4.getClassName()) != null) {
                                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        str23 = substringAfterLast$default2;
                                    }
                                    String canonicalName4 = (flowCollector == null || (cls5 = flowCollector.getClass()) == null) ? null : cls5.getCanonicalName();
                                    str23 = canonicalName4 == null ? "N/A" : canonicalName4;
                                    objectRef4.element = str23;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str24 = (String) objectRef4.element;
                                    } else {
                                        str24 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str24, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    String str28 = "performStartTransactionApiCall: result : " + ((Object) Result.m1210toStringimpl(value));
                                    if (str28 == null) {
                                        str28 = str;
                                    }
                                    sb3.append(str28);
                                    sb3.append(' ');
                                    sb3.append("");
                                    Log.println(2, str24, sb3.toString());
                                }
                            }
                        }
                        NetworkUIState.Success success = new NetworkUIState.Success(Result.m1201boximpl(value));
                        this.L$0 = obj7;
                        this.L$1 = null;
                        this.label = 4;
                        if (flowCollector.emit(success, this) == obj6) {
                            return obj6;
                        }
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    m400startTransactionBWLJW6A$hyperkyc_release = ((Result) obj).getValue();
                    charSequence = "co.hyperverge";
                    str8 = "packageName";
                    str = "null ";
                    str6 = "Throwable().stackTrace";
                    obj2 = coroutine_suspended;
                    mainVM$performStartTransactionApiCall$1 = this;
                    m1202constructorimpl2 = Result.m1202constructorimpl(Result.m1201boximpl(m400startTransactionBWLJW6A$hyperkyc_release));
                } catch (Throwable th3) {
                    th = th3;
                    charSequence = "co.hyperverge";
                    str8 = "packageName";
                    str = "null ";
                    str6 = "Throwable().stackTrace";
                    obj2 = coroutine_suspended;
                    mainVM$performStartTransactionApiCall$1 = this;
                    Result.Companion companion7 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj8 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj8);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                Object obj82 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj82);
                if (m1205exceptionOrNullimpl == null) {
                    obj3 = obj2;
                    obj4 = obj82;
                    str9 = "null cannot be cast to non-null type android.app.Application";
                    str10 = str6;
                    mainVM$performStartTransactionApiCall$12 = mainVM$performStartTransactionApiCall$1;
                    str11 = str8;
                    if (Result.m1209isSuccessimpl(obj4)) {
                    }
                    return Unit.INSTANCE;
                }
                HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb4 = new StringBuilder();
                Object obj9 = obj2;
                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace5, str6);
                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                    str12 = str6;
                    str13 = "null cannot be cast to non-null type android.app.Application";
                } else {
                    str12 = str6;
                    str13 = "null cannot be cast to non-null type android.app.Application";
                    r12 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                r12 = (flowCollector == null || (cls4 = flowCollector.getClass()) == null) ? 0 : cls4.getCanonicalName();
                if (r12 == 0) {
                    r12 = "N/A";
                }
                objectRef5.element = r12;
                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                if (matcher5.find()) {
                    ?? replaceAll5 = matcher5.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                    objectRef5.element = replaceAll5;
                }
                if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str14 = (String) objectRef5.element;
                } else {
                    str14 = ((String) objectRef5.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb4.append(str14);
                sb4.append(" - ");
                String str29 = "performStartTransactionApiCall: failed " + m1205exceptionOrNullimpl.getMessage();
                if (str29 == null) {
                    str29 = str;
                }
                sb4.append(str29);
                sb4.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str15 = '\n' + localizedMessage;
                } else {
                    str15 = "";
                }
                sb4.append(str15);
                companion8.log(level3, sb4.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion9 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str9 = str13;
                    try {
                        Intrinsics.checkNotNull(invoke3, str9);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                    } catch (Throwable th4) {
                        th = th4;
                        Result.Companion companion10 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        }
                        String str30 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                        }
                        failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                        mainVM$performStartTransactionApiCall$12 = this;
                        mainVM$performStartTransactionApiCall$12.L$0 = flowCollector;
                        mainVM$performStartTransactionApiCall$12.L$1 = obj82;
                        mainVM$performStartTransactionApiCall$12.label = 3;
                        obj3 = obj9;
                        if (flowCollector.emit(failed, mainVM$performStartTransactionApiCall$12) != obj3) {
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    str9 = str13;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str302 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    str10 = str12;
                    str11 = str8;
                } else {
                    str11 = str8;
                    Intrinsics.checkNotNullExpressionValue(str302, str11);
                    if (StringsKt.contains$default((CharSequence) str302, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                        str10 = str12;
                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str10);
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                        if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName2 = (flowCollector == null || (cls3 = flowCollector.getClass()) == null) ? 0 : cls3.getCanonicalName();
                            if (canonicalName2 == 0) {
                                canonicalName2 = "N/A";
                            }
                        }
                        objectRef6.element = canonicalName2;
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                        if (matcher6.find()) {
                            ?? replaceAll6 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll6, "replaceAll(\"\")");
                            objectRef6.element = replaceAll6;
                        }
                        if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str16 = (String) objectRef6.element;
                        } else {
                            str16 = ((String) objectRef6.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb5 = new StringBuilder();
                        String str31 = "performStartTransactionApiCall: failed " + m1205exceptionOrNullimpl.getMessage();
                        if (str31 == null) {
                            str31 = str;
                        }
                        sb5.append(str31);
                        sb5.append(' ');
                        String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage2 != null) {
                            str17 = '\n' + localizedMessage2;
                        } else {
                            str17 = "";
                        }
                        sb5.append(str17);
                        Log.println(6, str16, sb5.toString());
                    } else {
                        str10 = str12;
                    }
                }
                failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                mainVM$performStartTransactionApiCall$12 = this;
                mainVM$performStartTransactionApiCall$12.L$0 = flowCollector;
                mainVM$performStartTransactionApiCall$12.L$1 = obj82;
                mainVM$performStartTransactionApiCall$12.label = 3;
                obj3 = obj9;
                if (flowCollector.emit(failed, mainVM$performStartTransactionApiCall$12) != obj3) {
                    return obj3;
                }
                obj5 = obj82;
                obj4 = obj5;
                if (Result.m1209isSuccessimpl(obj4)) {
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector2;
            charSequence = "co.hyperverge";
            str = "null ";
            str6 = "Throwable().stackTrace";
            obj2 = coroutine_suspended;
            mainVM$performStartTransactionApiCall$1 = this;
            str5 = "packageName";
        }
        StartTransaction startTransaction = mainVM$performStartTransactionApiCall$1.$startTransaction;
        Map<String, String> map = mainVM$performStartTransactionApiCall$1.$headers;
        Result.Companion companion11 = Result.INSTANCE;
        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
        str8 = str5;
        mainVM$performStartTransactionApiCall$1.L$0 = flowCollector;
        mainVM$performStartTransactionApiCall$1.label = 2;
        m400startTransactionBWLJW6A$hyperkyc_release = networkRepo.m400startTransactionBWLJW6A$hyperkyc_release("https://ind.idv.hyperverge.co/v2/startTransaction", startTransaction, map, mainVM$performStartTransactionApiCall$1);
        if (m400startTransactionBWLJW6A$hyperkyc_release == obj2) {
            return obj2;
        }
        m1202constructorimpl2 = Result.m1202constructorimpl(Result.m1201boximpl(m400startTransactionBWLJW6A$hyperkyc_release));
        Object obj822 = m1202constructorimpl2;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj822);
        if (m1205exceptionOrNullimpl == null) {
        }
    }
}
