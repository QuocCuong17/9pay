package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
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
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "Lco/hyperverge/hyperkyc/ui/custom/UIColorConfigUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$fetchUIColorConfigs$1", f = "MainVM.kt", i = {0, 1, 2}, l = {647, 650, 658, 661}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$fetchUIColorConfigs$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends HSUIConfig>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ HyperKycConfig $hyperKycConfig;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$fetchUIColorConfigs$1(HyperKycConfig hyperKycConfig, MainVM mainVM, Continuation<? super MainVM$fetchUIColorConfigs$1> continuation) {
        super(2, continuation);
        this.$hyperKycConfig = hyperKycConfig;
        this.this$0 = mainVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$fetchUIColorConfigs$1 mainVM$fetchUIColorConfigs$1 = new MainVM$fetchUIColorConfigs$1(this.$hyperKycConfig, this.this$0, continuation);
        mainVM$fetchUIColorConfigs$1.L$0 = obj;
        return mainVM$fetchUIColorConfigs$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends HSUIConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<HSUIConfig>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<HSUIConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$fetchUIColorConfigs$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(1:(1:(1:(1:(3:7|8|9)(2:11|12))(6:13|14|15|(2:17|(1:19))|8|9))(8:20|21|22|23|24|25|26|(21:28|(1:106)(1:32)|(1:40)(1:37)|(1:39)|41|(1:43)(1:105)|44|(1:104)(1:48)|49|(1:51)(1:103)|52|(1:54)(1:102)|55|56|57|58|(1:60)|61|(2:63|(10:65|(2:(1:94)(1:91)|(1:93))|71|(1:73)|74|(1:87)(1:78)|79|(1:81)(1:86)|(1:83)(1:85)|84))|95|(1:97)(6:98|14|15|(0)|8|9))(5:107|15|(0)|8|9)))(1:114))(15:136|(1:209)(1:140)|(1:149)(1:145)|(1:147)(1:148)|150|(1:152)|153|(1:208)(1:157)|158|(1:160)|161|(6:167|168|169|(1:171)|172|(2:174|(14:176|(1:204)(1:180)|(1:188)(1:185)|(1:187)|189|(1:191)|192|(6:202|198|(1:200)|201|164|(1:166))|197|198|(0)|201|164|(0))))|163|164|(0))|115|116|117|118|119|120|121|122|123|(1:125)(5:126|24|25|26|(0)(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02c0, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x02db, code lost:
    
        r1 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02c2, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x02c3, code lost:
    
        r15 = r4;
        r25 = "replaceAll(\"\")";
        r27 = "";
        r26 = "this as java.lang.String…ing(startIndex, endIndex)";
        r18 = r14;
        r14 = 2;
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02cf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02d0, code lost:
    
        r10 = r3;
        r15 = r4;
        r25 = "replaceAll(\"\")";
        r27 = "";
        r26 = "this as java.lang.String…ing(startIndex, endIndex)";
        r18 = r14;
        r14 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x00b5, code lost:
    
        if (r8 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x01cc, code lost:
    
        if (r2 != 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0326, code lost:
    
        if (r8 != 0) goto L133;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0268 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x02ef  */
    /* JADX WARN: Type inference failed for: r0v43, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v67, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v32, types: [T] */
    /* JADX WARN: Type inference failed for: r1v42, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v44, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v60 */
    /* JADX WARN: Type inference failed for: r2v13, types: [T] */
    /* JADX WARN: Type inference failed for: r2v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v32, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r3v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v28, types: [T] */
    /* JADX WARN: Type inference failed for: r8v39, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v46, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v47 */
    /* JADX WARN: Type inference failed for: r8v48 */
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
        Object obj3;
        ?? r2;
        char c;
        String str4;
        Class<?> cls;
        String className;
        Object obj4;
        Class<?> cls2;
        String className2;
        String str5;
        Object obj5;
        String str6;
        String str7;
        String str8;
        int i;
        FlowCollector flowCollector2;
        File cacheDir;
        Object fetchUIColorConfig$hyperkyc_release$default;
        Object m1202constructorimpl2;
        FlowCollector flowCollector3;
        Object obj6;
        Throwable m1205exceptionOrNullimpl;
        Object obj7;
        Object obj8;
        ?? r8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        Object obj9;
        FlowCollector flowCollector4;
        ?? canonicalName;
        Class<?> cls3;
        String str15;
        String str16;
        String className3;
        Class<?> cls4;
        String className4;
        MainVM$fetchUIColorConfigs$1 mainVM$fetchUIColorConfigs$1 = this;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = mainVM$fetchUIColorConfigs$1.label;
        String str17 = "Throwable().stackTrace";
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) mainVM$fetchUIColorConfigs$1.L$0;
            HyperKycConfig hyperKycConfig = mainVM$fetchUIColorConfigs$1.$hyperKycConfig;
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
            String str18 = "fetchUIColorConfigs() called with hyperKycConfig = " + hyperKycConfig;
            if (str18 == null) {
                str18 = "null ";
            }
            sb.append(str18);
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
                        str17 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str17);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            obj3 = null;
                        } else {
                            obj3 = null;
                            r2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r2 = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? obj3 : cls.getCanonicalName();
                        if (r2 == 0) {
                            r2 = "N/A";
                        }
                        objectRef2.element = r2;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        c = 23;
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            StringBuilder sb2 = new StringBuilder();
                            String str19 = "fetchUIColorConfigs() called with hyperKycConfig = " + hyperKycConfig;
                            sb2.append(str19 != null ? str19 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(4, str4, sb2.toString());
                            mainVM$fetchUIColorConfigs$1 = this;
                            mainVM$fetchUIColorConfigs$1.L$0 = flowCollector;
                            mainVM$fetchUIColorConfigs$1.label = 1;
                            obj4 = obj2;
                            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchUIColorConfigs$1) == obj4) {
                                return obj4;
                            }
                        }
                        str4 = (String) objectRef2.element;
                        StringBuilder sb22 = new StringBuilder();
                        String str192 = "fetchUIColorConfigs() called with hyperKycConfig = " + hyperKycConfig;
                        sb22.append(str192 != null ? str192 : "null ");
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(4, str4, sb22.toString());
                        mainVM$fetchUIColorConfigs$1 = this;
                        mainVM$fetchUIColorConfigs$1.L$0 = flowCollector;
                        mainVM$fetchUIColorConfigs$1.label = 1;
                        obj4 = obj2;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchUIColorConfigs$1) == obj4) {
                        }
                    }
                }
            }
            str17 = str;
            c = 23;
            mainVM$fetchUIColorConfigs$1 = this;
            mainVM$fetchUIColorConfigs$1.L$0 = flowCollector;
            mainVM$fetchUIColorConfigs$1.label = 1;
            obj4 = obj2;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchUIColorConfigs$1) == obj4) {
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
                    obj9 = mainVM$fetchUIColorConfigs$1.L$1;
                    flowCollector4 = (FlowCollector) mainVM$fetchUIColorConfigs$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj7 = coroutine_suspended;
                    flowCollector3 = flowCollector4;
                    obj6 = obj9;
                    MainVM mainVM = mainVM$fetchUIColorConfigs$1.this$0;
                    if (Result.m1209isSuccessimpl(obj6)) {
                        HSUIConfig hSUIConfig = (HSUIConfig) obj6;
                        mainVM.setUiConfigData(hSUIConfig);
                        NetworkUIState.Success success = new NetworkUIState.Success(hSUIConfig);
                        mainVM$fetchUIColorConfigs$1.L$0 = obj6;
                        mainVM$fetchUIColorConfigs$1.L$1 = null;
                        mainVM$fetchUIColorConfigs$1.label = 4;
                        if (flowCollector3.emit(success, mainVM$fetchUIColorConfigs$1) == obj7) {
                            return obj7;
                        }
                    }
                    return Unit.INSTANCE;
                }
                flowCollector2 = (FlowCollector) mainVM$fetchUIColorConfigs$1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    fetchUIColorConfig$hyperkyc_release$default = obj;
                    str6 = "replaceAll(\"\")";
                    str7 = "";
                    str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                    i = 2;
                    obj5 = coroutine_suspended;
                    str5 = "Throwable().stackTrace";
                } catch (Throwable th2) {
                    th = th2;
                    str6 = "replaceAll(\"\")";
                    str7 = "";
                    str8 = "this as java.lang.String…ing(startIndex, endIndex)";
                    i = 2;
                    obj5 = coroutine_suspended;
                    str5 = "Throwable().stackTrace";
                    Result.Companion companion4 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    flowCollector3 = flowCollector2;
                    obj6 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj6);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                try {
                    m1202constructorimpl2 = Result.m1202constructorimpl((HSUIConfig) fetchUIColorConfig$hyperkyc_release$default);
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion42 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    flowCollector3 = flowCollector2;
                    obj6 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj6);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                flowCollector3 = flowCollector2;
                obj6 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj6);
                if (m1205exceptionOrNullimpl == null) {
                    obj7 = obj5;
                    MainVM mainVM2 = mainVM$fetchUIColorConfigs$1.this$0;
                    if (Result.m1209isSuccessimpl(obj6)) {
                    }
                    return Unit.INSTANCE;
                }
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, str5);
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null) {
                    obj8 = obj5;
                } else {
                    obj8 = obj5;
                    r8 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, i, (Object) null);
                }
                r8 = (flowCollector3 == null || (cls4 = flowCollector3.getClass()) == null) ? 0 : cls4.getCanonicalName();
                if (r8 == 0) {
                    r8 = "N/A";
                }
                objectRef3.element = r8;
                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                if (matcher3.find()) {
                    str10 = str7;
                    ?? replaceAll3 = matcher3.replaceAll(str10);
                    str9 = str6;
                    Intrinsics.checkNotNullExpressionValue(replaceAll3, str9);
                    objectRef3.element = replaceAll3;
                } else {
                    str9 = str6;
                    str10 = str7;
                }
                if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str11 = str8;
                    str12 = (String) objectRef3.element;
                } else {
                    str12 = ((String) objectRef3.element).substring(0, 23);
                    str11 = str8;
                    Intrinsics.checkNotNullExpressionValue(str12, str11);
                }
                sb3.append(str12);
                sb3.append(" - ");
                sb3.append("fetchUIColorConfigs() failed");
                sb3.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str13 = '\n' + localizedMessage;
                } else {
                    str13 = str10;
                }
                sb3.append(str13);
                companion5.log(level2, sb3.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion6 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    str14 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th4) {
                    Result.Companion companion7 = Result.INSTANCE;
                    str14 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                }
                String str20 = str14;
                if (Result.m1208isFailureimpl(str20)) {
                    str20 = str10;
                }
                String packageName2 = str20;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str5);
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
                            ?? replaceAll4 = matcher4.replaceAll(str10);
                            Intrinsics.checkNotNullExpressionValue(replaceAll4, str9);
                            objectRef4.element = replaceAll4;
                        }
                        if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str15 = (String) objectRef4.element;
                        } else {
                            str15 = ((String) objectRef4.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str15, str11);
                        }
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("fetchUIColorConfigs() failed");
                        sb4.append(' ');
                        String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage2 != null) {
                            str16 = '\n' + localizedMessage2;
                        } else {
                            str16 = str10;
                        }
                        sb4.append(str16);
                        Log.println(6, str15, sb4.toString());
                    }
                }
                NetworkUIState.Failed failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                mainVM$fetchUIColorConfigs$1.L$0 = flowCollector3;
                mainVM$fetchUIColorConfigs$1.L$1 = obj6;
                mainVM$fetchUIColorConfigs$1.label = 3;
                obj7 = obj8;
                if (flowCollector3.emit(failed, mainVM$fetchUIColorConfigs$1) == obj7) {
                    return obj7;
                }
                obj9 = obj6;
                flowCollector4 = flowCollector3;
                flowCollector3 = flowCollector4;
                obj6 = obj9;
                MainVM mainVM22 = mainVM$fetchUIColorConfigs$1.this$0;
                if (Result.m1209isSuccessimpl(obj6)) {
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector5 = (FlowCollector) mainVM$fetchUIColorConfigs$1.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector5;
            obj4 = coroutine_suspended;
            c = 23;
        }
        HyperKycConfig hyperKycConfig2 = mainVM$fetchUIColorConfigs$1.$hyperKycConfig;
        MainVM mainVM3 = mainVM$fetchUIColorConfigs$1.this$0;
        Result.Companion companion8 = Result.INSTANCE;
        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
        String appId = hyperKycConfig2.getAppId();
        Intrinsics.checkNotNull(appId);
        cacheDir = mainVM3.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        String workflowId$hyperkyc_release = hyperKycConfig2.getWorkflowId$hyperkyc_release();
        mainVM$fetchUIColorConfigs$1.L$0 = flowCollector;
        mainVM$fetchUIColorConfigs$1.label = 2;
        str5 = str17;
        obj5 = obj4;
        str6 = "replaceAll(\"\")";
        str8 = "this as java.lang.String…ing(startIndex, endIndex)";
        str7 = "";
        FlowCollector flowCollector6 = flowCollector;
        i = 2;
        fetchUIColorConfig$hyperkyc_release$default = NetworkRepo.fetchUIColorConfig$hyperkyc_release$default(networkRepo, appId, cacheDir, workflowId$hyperkyc_release, null, this, 8, null);
        if (fetchUIColorConfig$hyperkyc_release$default == obj5) {
            return obj5;
        }
        flowCollector2 = flowCollector6;
        m1202constructorimpl2 = Result.m1202constructorimpl((HSUIConfig) fetchUIColorConfig$hyperkyc_release$default);
        flowCollector3 = flowCollector2;
        obj6 = m1202constructorimpl2;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj6);
        if (m1205exceptionOrNullimpl == null) {
        }
    }
}
