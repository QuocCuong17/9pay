package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import kotlinx.coroutines.flow.MutableStateFlow;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "", "Lco/hyperverge/hyperkyc/data/models/KycCountry;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$fetchCountries$1", f = "MainVM.kt", i = {0, 1, 2}, l = {446, 449, 456, 460}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$fetchCountries$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends List<? extends KycCountry>>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$fetchCountries$1(MainVM mainVM, Continuation<? super MainVM$fetchCountries$1> continuation) {
        super(2, continuation);
        this.this$0 = mainVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$fetchCountries$1 mainVM$fetchCountries$1 = new MainVM$fetchCountries$1(this.this$0, continuation);
        mainVM$fetchCountries$1.L$0 = obj;
        return mainVM$fetchCountries$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends List<? extends KycCountry>>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<? extends List<KycCountry>>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<? extends List<KycCountry>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$fetchCountries$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(1:(1:(2:5|(2:7|(3:9|10|11)(2:13|14))(6:15|16|17|(19:19|(1:98)(1:23)|(1:32)(1:28)|(1:30)(1:31)|33|(1:35)|36|(1:97)(1:40)|41|(1:43)|44|(6:58|59|60|(1:62)|63|(2:65|(16:67|(1:71)|(1:93)(1:89)|(1:91)(1:92)|73|(1:75)|76|(1:85)(1:80)|81|(1:83)|84|47|(1:57)|(1:52)(1:56)|53|(1:55))))|46|47|(1:49)|57|(0)(0)|53|(0))|10|11))(10:99|100|101|102|103|104|(4:106|(3:215|(3:218|(2:220|221)(1:222)|216)|223)|110|(4:112|(4:115|(3:117|118|119)(1:121)|120|113)|122|123)(1:214))(1:224)|124|125|(23:127|(1:211)(1:131)|(1:139)(1:136)|(1:138)|140|(1:142)|143|(1:210)(1:147)|148|(1:150)(1:209)|(1:152)(1:208)|153|154|155|156|157|158|159|(1:161)|162|(2:164|(10:166|(2:(1:199)(1:196)|(1:198))|172|(1:174)|175|(1:192)(1:179)|180|(1:182)(1:191)|(1:184)(1:190)|185)(1:200))(1:201)|186|(1:188)(6:189|16|17|(0)|10|11))(5:212|17|(0)|10|11)))(1:231))(14:252|(1:321)(1:256)|(1:265)(1:261)|(1:263)(1:264)|266|(1:268)|269|(1:320)(1:273)|274|(6:281|282|283|(1:285)|286|(2:288|(12:290|(1:315)(1:294)|(1:302)(1:299)|(1:301)|303|(1:305)|306|(4:313|312|278|(1:280))|311|312|278|(0))(4:316|277|278|(0))))|276|277|278|(0))|232|233|234|236|237|238|239|240|(1:242)(7:243|103|104|(0)(0)|124|125|(0)(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0379, code lost:
    
        if (r10 != 0) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x030a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x030b, code lost:
    
        r5 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x030e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x030f, code lost:
    
        r11 = "replaceAll(\"\")";
        r8 = "this as java.lang.String…ing(startIndex, endIndex)";
        r12 = r6;
        r26 = r7;
        r17 = "null cannot be cast to non-null type android.app.Application";
        r19 = true;
        r7 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x059e, code lost:
    
        if (r10 != null) goto L244;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x031d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x031e, code lost:
    
        r11 = "replaceAll(\"\")";
        r12 = r6;
        r26 = r7;
        r19 = r8;
        r17 = "null cannot be cast to non-null type android.app.Application";
        r7 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x00d3, code lost:
    
        if (r7 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x01d8, code lost:
    
        if (r3 != 0) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x06af, code lost:
    
        if (r1 != null) goto L286;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02ac A[Catch: all -> 0x0308, TryCatch #3 {all -> 0x0308, blocks: (B:104:0x02a8, B:106:0x02ac, B:108:0x02b3, B:112:0x02d8, B:113:0x02e5, B:115:0x02eb, B:118:0x02f8, B:123:0x02fc, B:124:0x0303, B:215:0x02be, B:216:0x02c2, B:218:0x02c8), top: B:103:0x02a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0544 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0522  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0266 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0772 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0754  */
    /* JADX WARN: Type inference failed for: r10v27, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v30 */
    /* JADX WARN: Type inference failed for: r10v34, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v41, types: [T] */
    /* JADX WARN: Type inference failed for: r10v42 */
    /* JADX WARN: Type inference failed for: r10v43 */
    /* JADX WARN: Type inference failed for: r10v48 */
    /* JADX WARN: Type inference failed for: r10v49 */
    /* JADX WARN: Type inference failed for: r10v8, types: [T] */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v42, types: [T] */
    /* JADX WARN: Type inference failed for: r1v52, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v66 */
    /* JADX WARN: Type inference failed for: r2v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v11, types: [T] */
    /* JADX WARN: Type inference failed for: r3v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v81, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v90 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v30, types: [T] */
    /* JADX WARN: Type inference failed for: r6v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v42, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v55 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v48 */
    /* JADX WARN: Type inference failed for: r7v5, types: [T] */
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
        String str4;
        Object m1202constructorimpl;
        String str5;
        String str6;
        Object obj2;
        ?? r3;
        String str7;
        Class<?> cls;
        String className;
        MainVM$fetchCountries$1 mainVM$fetchCountries$1;
        boolean z;
        Object obj3;
        Class<?> cls2;
        String className2;
        String str8;
        String str9;
        String str10;
        boolean z2;
        String str11;
        Object obj4;
        File cacheDir;
        String str12;
        boolean z3;
        Object fetchCountries$hyperkyc_release$default;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        Object obj5;
        Object obj6;
        String str13;
        String str14;
        String str15;
        MainVM$fetchCountries$1 mainVM$fetchCountries$12;
        Object obj7;
        Object obj8;
        String str16;
        int i;
        ?? r10;
        String str17;
        Object m1202constructorimpl3;
        NetworkUIState.Failed failed;
        Object obj9;
        ?? canonicalName;
        Class<?> cls3;
        String str18;
        String className3;
        Class<?> cls4;
        String className4;
        List list;
        List list2;
        boolean z4;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        Object m1202constructorimpl4;
        String str24;
        Class<?> cls5;
        boolean z5;
        String str25;
        String className5;
        List list3;
        Object obj10;
        Object obj11;
        MutableStateFlow mutableStateFlow;
        Class<?> cls6;
        String className6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
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
                str2 = "packageName";
            } else {
                str = "Throwable().stackTrace";
                charSequence = "co.hyperverge";
                str2 = "packageName";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str3 = substringAfterLast$default;
            }
            String canonicalName2 = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? null : cls2.getCanonicalName();
            str3 = canonicalName2 == null ? "N/A" : canonicalName2;
            objectRef.element = str3;
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
            sb.append("fetchCountries() called");
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
                String str26 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str5 = str2;
                    Intrinsics.checkNotNullExpressionValue(str26, str5);
                    if (!StringsKt.contains$default((CharSequence) str26, charSequence, false, 2, (Object) null)) {
                        str6 = str;
                        mainVM$fetchCountries$1 = this;
                        mainVM$fetchCountries$1.L$0 = flowCollector;
                        z = true;
                        mainVM$fetchCountries$1.label = 1;
                        obj3 = coroutine_suspended;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchCountries$1) == obj3) {
                        }
                    } else {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str6 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            obj2 = null;
                        } else {
                            obj2 = null;
                            r3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r3 = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? obj2 : cls.getCanonicalName();
                        if (r3 == 0) {
                            r3 = "N/A";
                        }
                        objectRef2.element = r3;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str7 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                            Log.println(3, str7, "fetchCountries() called ");
                            mainVM$fetchCountries$1 = this;
                            mainVM$fetchCountries$1.L$0 = flowCollector;
                            z = true;
                            mainVM$fetchCountries$1.label = 1;
                            obj3 = coroutine_suspended;
                            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchCountries$1) == obj3) {
                                return obj3;
                            }
                        }
                        str7 = (String) objectRef2.element;
                        Log.println(3, str7, "fetchCountries() called ");
                        mainVM$fetchCountries$1 = this;
                        mainVM$fetchCountries$1.L$0 = flowCollector;
                        z = true;
                        mainVM$fetchCountries$1.label = 1;
                        obj3 = coroutine_suspended;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchCountries$1) == obj3) {
                        }
                    }
                }
            }
            str6 = str;
            str5 = str2;
            mainVM$fetchCountries$1 = this;
            mainVM$fetchCountries$1.L$0 = flowCollector;
            z = true;
            mainVM$fetchCountries$1.label = 1;
            obj3 = coroutine_suspended;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchCountries$1) == obj3) {
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    obj9 = this.L$1;
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj6 = coroutine_suspended;
                    str14 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    z2 = true;
                    str8 = "replaceAll(\"\")";
                    str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                    mainVM$fetchCountries$12 = this;
                    str15 = "packageName";
                    str13 = "Throwable().stackTrace";
                    obj5 = obj9;
                    MainVM mainVM = mainVM$fetchCountries$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                        List list4 = (List) obj5;
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        Object obj12 = obj6;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        Object obj13 = obj5;
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str13);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null) {
                            str19 = str14;
                            str20 = str15;
                            str21 = str13;
                        } else {
                            str19 = str14;
                            str20 = str15;
                            str21 = str13;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str22 = substringAfterLast$default2;
                        }
                        String canonicalName3 = (flowCollector == null || (cls6 = flowCollector.getClass()) == null) ? null : cls6.getCanonicalName();
                        str22 = canonicalName3 == null ? "N/A" : canonicalName3;
                        objectRef3.element = str22;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                        if (matcher3.find()) {
                            ?? replaceAll3 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll3, str8);
                            objectRef3.element = replaceAll3;
                        }
                        if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str23 = (String) objectRef3.element;
                        } else {
                            str23 = ((String) objectRef3.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str23, str12);
                        }
                        sb2.append(str23);
                        sb2.append(" - ");
                        String str27 = "fetchCountries: countries : " + list4;
                        if (str27 == null) {
                            str27 = "null ";
                        }
                        sb2.append(str27);
                        sb2.append(' ');
                        sb2.append("");
                        companion4.log(level2, sb2.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, str19);
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th2) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String str28 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str28, str20);
                                if (StringsKt.contains$default((CharSequence) str28, charSequence, false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str21);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 != null && (className5 = stackTraceElement4.getClassName()) != null) {
                                        String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        str24 = substringAfterLast$default3;
                                    }
                                    String canonicalName4 = (flowCollector == null || (cls5 = flowCollector.getClass()) == null) ? null : cls5.getCanonicalName();
                                    str24 = canonicalName4 == null ? "N/A" : canonicalName4;
                                    objectRef4.element = str24;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, str8);
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        z5 = false;
                                        str25 = (String) objectRef4.element;
                                    } else {
                                        z5 = false;
                                        str25 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str25, str12);
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    String str29 = "fetchCountries: countries : " + list4;
                                    if (str29 == null) {
                                        str29 = "null ";
                                    }
                                    sb3.append(str29);
                                    sb3.append(' ');
                                    sb3.append("");
                                    Log.println(2, str25, sb3.toString());
                                    list3 = list4;
                                    if (list3 != null || list3.isEmpty()) {
                                        z5 = z2;
                                    }
                                    if (z5) {
                                        mutableStateFlow = mainVM.countriesFlow;
                                        mutableStateFlow.tryEmit(list4);
                                        obj11 = (NetworkUIState) new NetworkUIState.Success(list4);
                                        obj10 = null;
                                    } else {
                                        obj10 = null;
                                        obj11 = (NetworkUIState) new NetworkUIState.Failed("country list is null or empty", null, 2, null);
                                    }
                                    this.L$0 = obj13;
                                    this.L$1 = obj10;
                                    this.label = 4;
                                    if (flowCollector.emit(obj11, this) == obj12) {
                                        return obj12;
                                    }
                                }
                            }
                        }
                        z5 = false;
                        list3 = list4;
                        if (list3 != null) {
                        }
                        z5 = z2;
                        if (z5) {
                        }
                        this.L$0 = obj13;
                        this.L$1 = obj10;
                        this.label = 4;
                        if (flowCollector.emit(obj11, this) == obj12) {
                        }
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    fetchCountries$hyperkyc_release$default = obj;
                    obj4 = coroutine_suspended;
                    str11 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str10 = "packageName";
                    z3 = false;
                    z2 = true;
                    str8 = "replaceAll(\"\")";
                    str9 = "Throwable().stackTrace";
                    str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                    try {
                        list = (List) fetchCountries$hyperkyc_release$default;
                        if (list == null) {
                            List list5 = list;
                            if (!(list5 instanceof Collection) || !list5.isEmpty()) {
                                Iterator it = list5.iterator();
                                while (it.hasNext()) {
                                    if (((KycCountry) it.next()).getEnabled()) {
                                        z4 = z2;
                                        break;
                                    }
                                }
                            }
                            z4 = z3;
                            if (z4) {
                                ArrayList arrayList = new ArrayList();
                                for (Object obj14 : list) {
                                    if (((KycCountry) obj14).getEnabled()) {
                                        arrayList.add(obj14);
                                    }
                                }
                                list2 = arrayList;
                            } else {
                                list2 = list;
                            }
                        } else {
                            list2 = null;
                        }
                        m1202constructorimpl2 = Result.m1202constructorimpl(list2);
                    } catch (Throwable th3) {
                        th = th3;
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        Object obj15 = m1202constructorimpl2;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj15);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    obj4 = coroutine_suspended;
                    str11 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str10 = "packageName";
                    z2 = true;
                    str8 = "replaceAll(\"\")";
                    str9 = "Throwable().stackTrace";
                    str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                    Result.Companion companion72 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj152 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj152);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                Object obj1522 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj1522);
                if (m1205exceptionOrNullimpl != null) {
                    obj5 = obj1522;
                    obj6 = obj4;
                    str13 = str9;
                    str14 = str11;
                    str15 = str10;
                    mainVM$fetchCountries$12 = this;
                    MainVM mainVM2 = mainVM$fetchCountries$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                    }
                    return Unit.INSTANCE;
                }
                HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb4 = new StringBuilder();
                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace5, str9);
                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                    obj7 = obj1522;
                    obj8 = obj4;
                    str16 = str9;
                    i = 2;
                } else {
                    obj7 = obj1522;
                    obj8 = obj4;
                    str16 = str9;
                    i = 2;
                    r10 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                r10 = (flowCollector == null || (cls4 = flowCollector.getClass()) == null) ? 0 : cls4.getCanonicalName();
                if (r10 == 0) {
                    r10 = "N/A";
                }
                objectRef5.element = r10;
                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                if (matcher5.find()) {
                    ?? replaceAll5 = matcher5.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll5, str8);
                    objectRef5.element = replaceAll5;
                }
                if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str17 = (String) objectRef5.element;
                } else {
                    str17 = ((String) objectRef5.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str17, str12);
                }
                sb4.append(str17);
                sb4.append(" - ");
                sb4.append("fetchCountries: failed");
                sb4.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                sb4.append(localizedMessage != null ? '\n' + localizedMessage : "");
                companion8.log(level3, sb4.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion9 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str14 = str11;
                    try {
                        Intrinsics.checkNotNull(invoke3, str14);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                    } catch (Throwable th5) {
                        th = th5;
                        Result.Companion companion10 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        }
                        String str30 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                        }
                        failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                        mainVM$fetchCountries$12 = this;
                        mainVM$fetchCountries$12.L$0 = flowCollector;
                        Object obj16 = obj7;
                        mainVM$fetchCountries$12.L$1 = obj16;
                        mainVM$fetchCountries$12.label = 3;
                        obj6 = obj8;
                        if (flowCollector.emit(failed, mainVM$fetchCountries$12) != obj6) {
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    str14 = str11;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str302 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    str13 = str16;
                    str15 = str10;
                } else {
                    str15 = str10;
                    Intrinsics.checkNotNullExpressionValue(str302, str15);
                    if (StringsKt.contains$default((CharSequence) str302, charSequence, false, i, (Object) null)) {
                        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                        str13 = str16;
                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str13);
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                        if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, i, (Object) null)) == 0) {
                            canonicalName = (flowCollector == null || (cls3 = flowCollector.getClass()) == null) ? 0 : cls3.getCanonicalName();
                            if (canonicalName == 0) {
                                canonicalName = "N/A";
                            }
                        }
                        objectRef6.element = canonicalName;
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                        if (matcher6.find()) {
                            ?? replaceAll6 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll6, str8);
                            objectRef6.element = replaceAll6;
                        }
                        if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str18 = (String) objectRef6.element;
                        } else {
                            str18 = ((String) objectRef6.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str18, str12);
                        }
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("fetchCountries: failed");
                        sb5.append(' ');
                        String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        sb5.append(localizedMessage2 != null ? '\n' + localizedMessage2 : "");
                        Log.println(6, str18, sb5.toString());
                    } else {
                        str13 = str16;
                    }
                }
                failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                mainVM$fetchCountries$12 = this;
                mainVM$fetchCountries$12.L$0 = flowCollector;
                Object obj162 = obj7;
                mainVM$fetchCountries$12.L$1 = obj162;
                mainVM$fetchCountries$12.label = 3;
                obj6 = obj8;
                if (flowCollector.emit(failed, mainVM$fetchCountries$12) != obj6) {
                    return obj6;
                }
                obj9 = obj162;
                obj5 = obj9;
                MainVM mainVM22 = mainVM$fetchCountries$12.this$0;
                if (Result.m1209isSuccessimpl(obj5)) {
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector2;
            str6 = "Throwable().stackTrace";
            mainVM$fetchCountries$1 = this;
            obj3 = coroutine_suspended;
            charSequence = "co.hyperverge";
            str5 = "packageName";
            z = true;
        }
        MainVM mainVM3 = mainVM$fetchCountries$1.this$0;
        Result.Companion companion11 = Result.INSTANCE;
        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
        cacheDir = mainVM3.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        mainVM$fetchCountries$1.L$0 = flowCollector;
        mainVM$fetchCountries$1.label = 2;
        str8 = "replaceAll(\"\")";
        str9 = str6;
        str12 = "this as java.lang.String…ing(startIndex, endIndex)";
        FlowCollector flowCollector3 = flowCollector;
        z2 = true;
        str10 = str5;
        obj4 = obj3;
        str11 = "null cannot be cast to non-null type android.app.Application";
        z3 = false;
        fetchCountries$hyperkyc_release$default = NetworkRepo.fetchCountries$hyperkyc_release$default(networkRepo, cacheDir, null, this, 2, null);
        if (fetchCountries$hyperkyc_release$default == obj4) {
            return obj4;
        }
        flowCollector = flowCollector3;
        list = (List) fetchCountries$hyperkyc_release$default;
        if (list == null) {
        }
        m1202constructorimpl2 = Result.m1202constructorimpl(list2);
        Object obj15222 = m1202constructorimpl2;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj15222);
        if (m1205exceptionOrNullimpl != null) {
        }
    }
}
