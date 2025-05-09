package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
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
import kotlinx.coroutines.flow.MutableStateFlow;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001**\u0012&\u0012$\u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u00040\u0003j\u0002`\u00070\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "", "", "", "Lco/hyperverge/hyperkyc/ui/custom/TextConfigUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$fetchTextConfigs$1", f = "MainVM.kt", i = {0, 0, 0, 1, 2}, l = {583, 586, 594, 598}, m = "invokeSuspend", n = {"$this$flow", "languageToBeUsed", "source", "$this$flow", "$this$flow"}, s = {"L$0", "L$1", "L$2", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$fetchTextConfigs$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends Map<String, ? extends Map<String, ? extends Object>>>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$fetchTextConfigs$1(MainVM mainVM, Continuation<? super MainVM$fetchTextConfigs$1> continuation) {
        super(2, continuation);
        this.this$0 = mainVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$fetchTextConfigs$1 mainVM$fetchTextConfigs$1 = new MainVM$fetchTextConfigs$1(this.this$0, continuation);
        mainVM$fetchTextConfigs$1.L$0 = obj;
        return mainVM$fetchTextConfigs$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super NetworkUIState<? extends Map<String, ? extends Map<String, ? extends Object>>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$fetchTextConfigs$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:1|(1:(2:4|(1:(1:(3:8|9|10)(2:12|13))(5:14|15|(2:17|(1:19))|9|10))(13:20|21|22|23|24|25|26|(20:28|(1:105)(1:32)|(1:40)(1:37)|(1:39)|41|(1:43)(1:104)|44|(1:103)(1:48)|49|(1:51)(1:102)|(1:53)(1:101)|54|55|56|57|(1:59)|60|(2:62|(10:64|(2:(1:93)(1:90)|(1:92))|70|(1:72)|73|(1:86)(1:77)|78|(1:80)(1:85)|(1:82)(1:84)|83))|94|(1:96))(1:106)|97|15|(0)|9|10))(1:113))(16:136|(1:208)(1:140)|(1:149)(1:145)|(1:147)(1:148)|150|(1:152)|153|(1:207)(1:157)|158|(6:169|170|171|(1:173)|174|(2:176|(15:178|(1:203)(1:182)|(1:191)(1:187)|(1:189)(1:190)|192|(1:194)|195|(7:199|200|161|162|(1:164)|165|(1:167)(1:168))|201|200|161|162|(0)|165|(0)(0))))|160|161|162|(0)|165|(0)(0))|114|115|116|117|118|119|120|121|122|(1:124)(10:125|24|25|26|(0)(0)|97|15|(0)|9|10)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x02f1, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0309, code lost:
    
        r20 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x02f3, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02f4, code lost:
    
        r12 = "";
        r26 = "replaceAll(\"\")";
        r27 = r8;
        r17 = r11;
        r28 = "this as java.lang.String…ing(startIndex, endIndex)";
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0308, code lost:
    
        r11 = " - ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02fe, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02ff, code lost:
    
        r12 = "";
        r26 = "replaceAll(\"\")";
        r28 = "this as java.lang.String…ing(startIndex, endIndex)";
        r27 = r8;
        r17 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x00cd, code lost:
    
        if (r11 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x01d2, code lost:
    
        if (r1 != null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x035a, code lost:
    
        if (r8 != 0) goto L134;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x028c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x031e  */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v11, types: [T] */
    /* JADX WARN: Type inference failed for: r1v20, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v54 */
    /* JADX WARN: Type inference failed for: r4v40 */
    /* JADX WARN: Type inference failed for: r4v41 */
    /* JADX WARN: Type inference failed for: r4v42 */
    /* JADX WARN: Type inference failed for: r4v45, types: [T] */
    /* JADX WARN: Type inference failed for: r4v59, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v61, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v72, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v77 */
    /* JADX WARN: Type inference failed for: r5v12, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v19, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v15, types: [T] */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v32 */
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
        String str4;
        String str5;
        String str6;
        Class<?> cls;
        String className;
        MainVM$fetchTextConfigs$1 mainVM$fetchTextConfigs$1;
        Ref.ObjectRef objectRef;
        ?? languageSource;
        Object obj2;
        FlowCollector flowCollector;
        String str7;
        Ref.ObjectRef objectRef2;
        Class<?> cls2;
        String className2;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        FlowCollector flowCollector2;
        File cacheDir;
        Object fetchTextConfig$hyperkyc_release$default;
        Object m1202constructorimpl2;
        FlowCollector flowCollector3;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        Object obj4;
        MainVM$fetchTextConfigs$1 mainVM$fetchTextConfigs$12;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        ?? r8;
        String str13;
        String str14;
        String str15;
        String str16;
        Object m1202constructorimpl3;
        ?? canonicalName;
        Class<?> cls3;
        String str17;
        String str18;
        String className3;
        Class<?> cls4;
        String className4;
        MutableStateFlow mutableStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector4 = (FlowCollector) this.L$0;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
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
            String canonicalName2 = (flowCollector4 == null || (cls2 = flowCollector4.getClass()) == null) ? null : cls2.getCanonicalName();
            str2 = canonicalName2 == null ? "N/A" : canonicalName2;
            objectRef3.element = str2;
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef3.element = replaceAll;
            }
            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str3 = (String) objectRef3.element;
            } else {
                str3 = ((String) objectRef3.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str3);
            sb.append(" - ");
            sb.append("fetchTextConfigs() called");
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
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str4 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str4);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str5 = substringAfterLast$default2;
                        }
                        String canonicalName3 = (flowCollector4 == null || (cls = flowCollector4.getClass()) == null) ? null : cls.getCanonicalName();
                        str5 = canonicalName3 == null ? "N/A" : canonicalName3;
                        objectRef4.element = str5;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef4.element = replaceAll2;
                        }
                        if (((String) objectRef4.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str6 = ((String) objectRef4.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            Log.println(3, str6, "fetchTextConfigs() called ");
                            mainVM$fetchTextConfigs$1 = this;
                            MainVM mainVM = mainVM$fetchTextConfigs$1.this$0;
                            String languageToBeUsed$hyperkyc_release = mainVM.getLanguageToBeUsed$hyperkyc_release(mainVM.getHyperKycConfig$hyperkyc_release());
                            objectRef = new Ref.ObjectRef();
                            languageSource = mainVM$fetchTextConfigs$1.this$0.getLanguageSource(languageToBeUsed$hyperkyc_release);
                            objectRef.element = languageSource;
                            if (Intrinsics.areEqual(objectRef.element, "custom")) {
                                objectRef.element = mainVM$fetchTextConfigs$1.this$0.getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release();
                            }
                            mainVM$fetchTextConfigs$1.L$0 = flowCollector4;
                            mainVM$fetchTextConfigs$1.L$1 = languageToBeUsed$hyperkyc_release;
                            mainVM$fetchTextConfigs$1.L$2 = objectRef;
                            mainVM$fetchTextConfigs$1.label = 1;
                            obj2 = coroutine_suspended;
                            if (flowCollector4.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchTextConfigs$1) == obj2) {
                                return obj2;
                            }
                            flowCollector = flowCollector4;
                            str7 = languageToBeUsed$hyperkyc_release;
                            objectRef2 = objectRef;
                        }
                        str6 = (String) objectRef4.element;
                        Log.println(3, str6, "fetchTextConfigs() called ");
                        mainVM$fetchTextConfigs$1 = this;
                        MainVM mainVM2 = mainVM$fetchTextConfigs$1.this$0;
                        String languageToBeUsed$hyperkyc_release2 = mainVM2.getLanguageToBeUsed$hyperkyc_release(mainVM2.getHyperKycConfig$hyperkyc_release());
                        objectRef = new Ref.ObjectRef();
                        languageSource = mainVM$fetchTextConfigs$1.this$0.getLanguageSource(languageToBeUsed$hyperkyc_release2);
                        objectRef.element = languageSource;
                        if (Intrinsics.areEqual(objectRef.element, "custom")) {
                        }
                        mainVM$fetchTextConfigs$1.L$0 = flowCollector4;
                        mainVM$fetchTextConfigs$1.L$1 = languageToBeUsed$hyperkyc_release2;
                        mainVM$fetchTextConfigs$1.L$2 = objectRef;
                        mainVM$fetchTextConfigs$1.label = 1;
                        obj2 = coroutine_suspended;
                        if (flowCollector4.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchTextConfigs$1) == obj2) {
                        }
                    }
                }
            }
            str4 = str;
            mainVM$fetchTextConfigs$1 = this;
            MainVM mainVM22 = mainVM$fetchTextConfigs$1.this$0;
            String languageToBeUsed$hyperkyc_release22 = mainVM22.getLanguageToBeUsed$hyperkyc_release(mainVM22.getHyperKycConfig$hyperkyc_release());
            objectRef = new Ref.ObjectRef();
            languageSource = mainVM$fetchTextConfigs$1.this$0.getLanguageSource(languageToBeUsed$hyperkyc_release22);
            objectRef.element = languageSource;
            if (Intrinsics.areEqual(objectRef.element, "custom")) {
            }
            mainVM$fetchTextConfigs$1.L$0 = flowCollector4;
            mainVM$fetchTextConfigs$1.L$1 = languageToBeUsed$hyperkyc_release22;
            mainVM$fetchTextConfigs$1.L$2 = objectRef;
            mainVM$fetchTextConfigs$1.label = 1;
            obj2 = coroutine_suspended;
            if (flowCollector4.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchTextConfigs$1) == obj2) {
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
                    flowCollector3 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mainVM$fetchTextConfigs$12 = this;
                    obj4 = coroutine_suspended;
                    MainVM mainVM3 = mainVM$fetchTextConfigs$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                        Map map = (Map) obj5;
                        mainVM3.textConfigData = map;
                        mutableStateFlow = mainVM3.textConfigFlow;
                        mutableStateFlow.tryEmit(map);
                        NetworkUIState.Success success = new NetworkUIState.Success(map);
                        mainVM$fetchTextConfigs$12.L$0 = obj5;
                        mainVM$fetchTextConfigs$12.L$1 = null;
                        mainVM$fetchTextConfigs$12.L$2 = null;
                        mainVM$fetchTextConfigs$12.label = 4;
                        if (flowCollector3.emit(success, mainVM$fetchTextConfigs$12) == obj4) {
                            return obj4;
                        }
                    }
                    return Unit.INSTANCE;
                }
                flowCollector2 = (FlowCollector) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    fetchTextConfig$hyperkyc_release$default = obj;
                    str9 = "replaceAll(\"\")";
                    str11 = "Throwable().stackTrace";
                    str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj2 = coroutine_suspended;
                    charSequence = "co.hyperverge";
                    str8 = "";
                    str12 = " - ";
                } catch (Throwable th2) {
                    th = th2;
                    str9 = "replaceAll(\"\")";
                    str11 = "Throwable().stackTrace";
                    str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj2 = coroutine_suspended;
                    charSequence = "co.hyperverge";
                    str8 = "";
                    str12 = " - ";
                    Result.Companion companion4 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj9 = m1202constructorimpl2;
                    flowCollector3 = flowCollector2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj9);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    obj5 = obj3;
                    MainVM mainVM32 = mainVM$fetchTextConfigs$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                    }
                    return Unit.INSTANCE;
                }
                try {
                    m1202constructorimpl2 = Result.m1202constructorimpl((Map) fetchTextConfig$hyperkyc_release$default);
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion42 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj92 = m1202constructorimpl2;
                    flowCollector3 = flowCollector2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj92);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    obj5 = obj3;
                    MainVM mainVM322 = mainVM$fetchTextConfigs$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                    }
                    return Unit.INSTANCE;
                }
                Object obj922 = m1202constructorimpl2;
                flowCollector3 = flowCollector2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj922);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    String str19 = str11;
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str19);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null) {
                        obj6 = obj922;
                        obj7 = obj2;
                        obj8 = null;
                    } else {
                        obj6 = obj922;
                        obj7 = obj2;
                        obj8 = null;
                        r8 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r8 = (flowCollector3 == null || (cls4 = flowCollector3.getClass()) == null) ? obj8 : cls4.getCanonicalName();
                    if (r8 == 0) {
                        r8 = "N/A";
                    }
                    objectRef5.element = r8;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                    if (matcher3.find()) {
                        ?? replaceAll3 = matcher3.replaceAll(str8);
                        str13 = str9;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str13);
                        objectRef5.element = replaceAll3;
                    } else {
                        str13 = str9;
                    }
                    if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str14 = str10;
                        str15 = (String) objectRef5.element;
                    } else {
                        String substring = ((String) objectRef5.element).substring(0, 23);
                        str14 = str10;
                        Intrinsics.checkNotNullExpressionValue(substring, str14);
                        str15 = substring;
                    }
                    sb2.append(str15);
                    sb2.append(str12);
                    sb2.append("fetchTextConfigs() failed");
                    sb2.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str16 = '\n' + localizedMessage;
                    } else {
                        str16 = str8;
                    }
                    sb2.append(str16);
                    companion5.log(level2, sb2.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th4) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = str8;
                    }
                    String packageName2 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str19);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                canonicalName = (flowCollector3 == null || (cls3 = flowCollector3.getClass()) == null) ? 0 : cls3.getCanonicalName();
                                if (canonicalName == 0) {
                                    canonicalName = "N/A";
                                }
                            }
                            objectRef6.element = canonicalName;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                            if (matcher4.find()) {
                                ?? replaceAll4 = matcher4.replaceAll(str8);
                                Intrinsics.checkNotNullExpressionValue(replaceAll4, str13);
                                objectRef6.element = replaceAll4;
                            }
                            if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str17 = (String) objectRef6.element;
                            } else {
                                str17 = ((String) objectRef6.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str17, str14);
                            }
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("fetchTextConfigs() failed");
                            sb3.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str18 = '\n' + localizedMessage2;
                            } else {
                                str18 = str8;
                            }
                            sb3.append(str18);
                            Log.println(6, str17, sb3.toString());
                        }
                    }
                    NetworkUIState.Failed failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                    mainVM$fetchTextConfigs$12 = this;
                    mainVM$fetchTextConfigs$12.L$0 = flowCollector3;
                    obj3 = obj6;
                    mainVM$fetchTextConfigs$12.L$1 = obj3;
                    mainVM$fetchTextConfigs$12.L$2 = null;
                    mainVM$fetchTextConfigs$12.label = 3;
                    obj4 = obj7;
                    if (flowCollector3.emit(failed, mainVM$fetchTextConfigs$12) == obj4) {
                        return obj4;
                    }
                } else {
                    obj3 = obj922;
                    obj4 = obj2;
                    mainVM$fetchTextConfigs$12 = this;
                }
                obj5 = obj3;
                MainVM mainVM3222 = mainVM$fetchTextConfigs$12.this$0;
                if (Result.m1209isSuccessimpl(obj5)) {
                }
                return Unit.INSTANCE;
            }
            objectRef2 = (Ref.ObjectRef) this.L$2;
            String str20 = (String) this.L$1;
            FlowCollector flowCollector5 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            str7 = str20;
            str4 = "Throwable().stackTrace";
            mainVM$fetchTextConfigs$1 = this;
            obj2 = coroutine_suspended;
            charSequence = "co.hyperverge";
            flowCollector = flowCollector5;
        }
        MainVM mainVM4 = mainVM$fetchTextConfigs$1.this$0;
        Result.Companion companion8 = Result.INSTANCE;
        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
        String appId = mainVM4.getHyperKycConfig$hyperkyc_release().getAppId();
        Intrinsics.checkNotNull(appId);
        String str21 = (String) objectRef2.element;
        cacheDir = mainVM4.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        mainVM$fetchTextConfigs$1.L$0 = flowCollector;
        mainVM$fetchTextConfigs$1.L$1 = null;
        mainVM$fetchTextConfigs$1.L$2 = null;
        mainVM$fetchTextConfigs$1.label = 2;
        str9 = "replaceAll(\"\")";
        str11 = str4;
        str10 = "this as java.lang.String…ing(startIndex, endIndex)";
        str8 = "";
        FlowCollector flowCollector6 = flowCollector;
        str12 = " - ";
        fetchTextConfig$hyperkyc_release$default = NetworkRepo.fetchTextConfig$hyperkyc_release$default(networkRepo, appId, str7, str21, cacheDir, null, this, 16, null);
        if (fetchTextConfig$hyperkyc_release$default == obj2) {
            return obj2;
        }
        flowCollector2 = flowCollector6;
        m1202constructorimpl2 = Result.m1202constructorimpl((Map) fetchTextConfig$hyperkyc_release$default);
        Object obj9222 = m1202constructorimpl2;
        flowCollector3 = flowCollector2;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj9222);
        if (m1205exceptionOrNullimpl == null) {
        }
        obj5 = obj3;
        MainVM mainVM32222 = mainVM$fetchTextConfigs$12.this$0;
        if (Result.m1209isSuccessimpl(obj5)) {
        }
        return Unit.INSTANCE;
    }
}
