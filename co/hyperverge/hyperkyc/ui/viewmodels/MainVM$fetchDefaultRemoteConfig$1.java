package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import co.hyperverge.hyperkyc.core.hv.models.HSDefaultRemoteConfig;
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
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00050\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig;", "Lco/hyperverge/hyperkyc/ui/custom/DefaultRemoteConfigUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$fetchDefaultRemoteConfig$1", f = "MainVM.kt", i = {0, 1, 2}, l = {693, 695, 703, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$fetchDefaultRemoteConfig$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends HSDefaultRemoteConfig>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$fetchDefaultRemoteConfig$1(MainVM mainVM, Continuation<? super MainVM$fetchDefaultRemoteConfig$1> continuation) {
        super(2, continuation);
        this.this$0 = mainVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$fetchDefaultRemoteConfig$1 mainVM$fetchDefaultRemoteConfig$1 = new MainVM$fetchDefaultRemoteConfig$1(this.this$0, continuation);
        mainVM$fetchDefaultRemoteConfig$1.L$0 = obj;
        return mainVM$fetchDefaultRemoteConfig$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends HSDefaultRemoteConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<HSDefaultRemoteConfig>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<HSDefaultRemoteConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$fetchDefaultRemoteConfig$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(1:(1:(2:5|(2:7|(3:9|10|11)(2:13|14))(6:15|16|17|(14:19|(1:89)(1:23)|(1:32)(1:28)|(1:30)(1:31)|33|(1:35)|36|(1:88)(1:40)|41|(1:43)|44|(6:49|50|51|(1:53)|54|(2:56|(11:58|(1:62)|(1:84)(1:80)|(1:82)(1:83)|64|(1:66)|67|(1:76)(1:71)|72|(1:74)|75)))|46|(1:48))(1:90)|10|11))(8:91|92|93|94|95|96|97|(23:99|(1:183)(1:103)|(1:111)(1:108)|(1:110)|112|(1:114)|115|(1:182)(1:119)|120|(1:122)(1:181)|(1:124)(1:180)|125|126|127|128|129|130|131|(1:133)|134|(2:136|(10:138|(2:(1:171)(1:168)|(1:170))|144|(1:146)|147|(1:164)(1:151)|152|(1:154)(1:163)|(1:156)(1:162)|157)(1:172))(1:173)|158|(1:160)(6:161|16|17|(0)(0)|10|11))(5:184|17|(0)(0)|10|11)))(1:191))(14:207|(1:275)(1:211)|(1:220)(1:216)|(1:218)(1:219)|221|(1:223)|224|(1:274)(1:228)|229|(6:236|237|238|(1:240)|241|(2:243|(12:245|(1:269)(1:249)|(1:257)(1:254)|(1:256)|258|(1:260)|261|(4:265|266|233|(1:235))|267|266|233|(0))(4:270|232|233|(0))))|231|232|233|(0))|192|193|194|195|196|197|(1:199)(5:200|95|96|97|(0)(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02fd, code lost:
    
        if (r2 != 0) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x029b, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x029c, code lost:
    
        r5 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x029f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x02a0, code lost:
    
        r10 = "replaceAll(\"\")";
        r8 = "this as java.lang.String…ing(startIndex, endIndex)";
        r11 = r6;
        r25 = r7;
        r7 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x00c8, code lost:
    
        if (r7 != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x052d, code lost:
    
        if (r4 != null) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x01cc, code lost:
    
        if (r3 != 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x063d, code lost:
    
        if (r1 != null) goto L259;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x04d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x04e7  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0258 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x06dc  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02bd  */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r1v54, types: [T] */
    /* JADX WARN: Type inference failed for: r1v64, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v76, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v81 */
    /* JADX WARN: Type inference failed for: r2v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v33, types: [T] */
    /* JADX WARN: Type inference failed for: r2v52, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v59, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v88 */
    /* JADX WARN: Type inference failed for: r3v11, types: [T] */
    /* JADX WARN: Type inference failed for: r3v21, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v53, types: [T] */
    /* JADX WARN: Type inference failed for: r3v63, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v65, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v79 */
    /* JADX WARN: Type inference failed for: r3v80 */
    /* JADX WARN: Type inference failed for: r4v11, types: [T] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v21 */
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
        MainVM$fetchDefaultRemoteConfig$1 mainVM$fetchDefaultRemoteConfig$1;
        Object obj3;
        Class<?> cls2;
        String className2;
        String str8;
        String str9;
        String str10;
        String str11;
        Object obj4;
        File cacheDir;
        Object fetchDefaultRemoteConfig$hyperkyc_release$default;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        Object obj5;
        MainVM$fetchDefaultRemoteConfig$1 mainVM$fetchDefaultRemoteConfig$12;
        String str12;
        String str13;
        String str14;
        Object obj6;
        MainVM mainVM;
        String str15;
        String str16;
        int i;
        ?? r2;
        String str17;
        String str18;
        Object m1202constructorimpl3;
        NetworkUIState.Failed failed;
        Object obj7;
        ?? canonicalName;
        Class<?> cls3;
        String str19;
        String str20;
        String className3;
        Class<?> cls4;
        String className4;
        String str21;
        String str22;
        String str23;
        String str24;
        String str25;
        Object m1202constructorimpl4;
        String str26;
        Class<?> cls5;
        String str27;
        String className5;
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
            sb.append("fetchDefaultRemoteConfig() called");
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
                String str28 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str5 = str2;
                    Intrinsics.checkNotNullExpressionValue(str28, str5);
                    if (!StringsKt.contains$default((CharSequence) str28, charSequence, false, 2, (Object) null)) {
                        str6 = str;
                        mainVM$fetchDefaultRemoteConfig$1 = this;
                        mainVM$fetchDefaultRemoteConfig$1.L$0 = flowCollector;
                        mainVM$fetchDefaultRemoteConfig$1.label = 1;
                        obj3 = coroutine_suspended;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchDefaultRemoteConfig$1) == obj3) {
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
                            Log.println(4, str7, "fetchDefaultRemoteConfig() called ");
                            mainVM$fetchDefaultRemoteConfig$1 = this;
                            mainVM$fetchDefaultRemoteConfig$1.L$0 = flowCollector;
                            mainVM$fetchDefaultRemoteConfig$1.label = 1;
                            obj3 = coroutine_suspended;
                            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchDefaultRemoteConfig$1) == obj3) {
                                return obj3;
                            }
                        }
                        str7 = (String) objectRef2.element;
                        Log.println(4, str7, "fetchDefaultRemoteConfig() called ");
                        mainVM$fetchDefaultRemoteConfig$1 = this;
                        mainVM$fetchDefaultRemoteConfig$1.L$0 = flowCollector;
                        mainVM$fetchDefaultRemoteConfig$1.label = 1;
                        obj3 = coroutine_suspended;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchDefaultRemoteConfig$1) == obj3) {
                        }
                    }
                }
            }
            str6 = str;
            str5 = str2;
            mainVM$fetchDefaultRemoteConfig$1 = this;
            mainVM$fetchDefaultRemoteConfig$1.L$0 = flowCollector;
            mainVM$fetchDefaultRemoteConfig$1.label = 1;
            obj3 = coroutine_suspended;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchDefaultRemoteConfig$1) == obj3) {
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
                    obj7 = this.L$1;
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    str13 = "Throwable().stackTrace";
                    mainVM$fetchDefaultRemoteConfig$12 = this;
                    charSequence = "co.hyperverge";
                    str14 = "packageName";
                    str8 = "replaceAll(\"\")";
                    str12 = "null cannot be cast to non-null type android.app.Application";
                    obj6 = coroutine_suspended;
                    str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                    obj5 = obj7;
                    MainVM mainVM2 = mainVM$fetchDefaultRemoteConfig$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                        HSDefaultRemoteConfig hSDefaultRemoteConfig = (HSDefaultRemoteConfig) obj5;
                        mainVM2.setDefaultRemoteConfig$hyperkyc_release(hSDefaultRemoteConfig);
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        Object obj8 = obj6;
                        StringBuilder sb2 = new StringBuilder();
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        Object obj9 = obj5;
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str13);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null) {
                            str21 = str12;
                            str22 = str14;
                            str23 = str13;
                        } else {
                            str21 = str12;
                            str22 = str14;
                            str23 = str13;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str24 = substringAfterLast$default2;
                        }
                        String canonicalName3 = (flowCollector == null || (cls6 = flowCollector.getClass()) == null) ? null : cls6.getCanonicalName();
                        str24 = canonicalName3 == null ? "N/A" : canonicalName3;
                        objectRef3.element = str24;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                        if (matcher3.find()) {
                            ?? replaceAll3 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll3, str8);
                            objectRef3.element = replaceAll3;
                        }
                        if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str25 = (String) objectRef3.element;
                        } else {
                            str25 = ((String) objectRef3.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str25, str9);
                        }
                        sb2.append(str25);
                        sb2.append(" - ");
                        String str29 = "fetchDefaultRemoteConfig data: " + hSDefaultRemoteConfig;
                        if (str29 == null) {
                            str29 = "null ";
                        }
                        sb2.append(str29);
                        sb2.append(' ');
                        sb2.append("");
                        companion4.log(level2, sb2.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, str21);
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th2) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String str30 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str30, str22);
                                if (StringsKt.contains$default((CharSequence) str30, charSequence, false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str23);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 != null && (className5 = stackTraceElement4.getClassName()) != null) {
                                        String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        str26 = substringAfterLast$default3;
                                    }
                                    String canonicalName4 = (flowCollector == null || (cls5 = flowCollector.getClass()) == null) ? null : cls5.getCanonicalName();
                                    str26 = canonicalName4 == null ? "N/A" : canonicalName4;
                                    objectRef4.element = str26;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll4 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, str8);
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str27 = (String) objectRef4.element;
                                    } else {
                                        str27 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str27, str9);
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    String str31 = "fetchDefaultRemoteConfig data: " + hSDefaultRemoteConfig;
                                    if (str31 == null) {
                                        str31 = "null ";
                                    }
                                    sb3.append(str31);
                                    sb3.append(' ');
                                    sb3.append("");
                                    Log.println(3, str27, sb3.toString());
                                }
                            }
                        }
                        NetworkUIState.Success success = new NetworkUIState.Success(hSDefaultRemoteConfig);
                        this.L$0 = obj9;
                        this.L$1 = null;
                        this.label = 4;
                        if (flowCollector.emit(success, this) == obj8) {
                            return obj8;
                        }
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    fetchDefaultRemoteConfig$hyperkyc_release$default = obj;
                    obj4 = coroutine_suspended;
                    charSequence = "co.hyperverge";
                    str11 = "packageName";
                    str8 = "replaceAll(\"\")";
                    str10 = "Throwable().stackTrace";
                    str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                } catch (Throwable th3) {
                    th = th3;
                    obj4 = coroutine_suspended;
                    charSequence = "co.hyperverge";
                    str11 = "packageName";
                    str8 = "replaceAll(\"\")";
                    str10 = "Throwable().stackTrace";
                    str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                    Result.Companion companion7 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj10 = m1202constructorimpl2;
                    MainVM mainVM3 = this.this$0;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj10);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                try {
                    m1202constructorimpl2 = Result.m1202constructorimpl((HSDefaultRemoteConfig) fetchDefaultRemoteConfig$hyperkyc_release$default);
                } catch (Throwable th4) {
                    th = th4;
                    Result.Companion companion72 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj102 = m1202constructorimpl2;
                    MainVM mainVM32 = this.this$0;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj102);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                Object obj1022 = m1202constructorimpl2;
                MainVM mainVM322 = this.this$0;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj1022);
                if (m1205exceptionOrNullimpl == null) {
                    obj5 = obj1022;
                    mainVM$fetchDefaultRemoteConfig$12 = this;
                    str12 = "null cannot be cast to non-null type android.app.Application";
                    str13 = str10;
                    str14 = str11;
                    obj6 = obj4;
                    MainVM mainVM22 = mainVM$fetchDefaultRemoteConfig$12.this$0;
                    if (Result.m1209isSuccessimpl(obj5)) {
                    }
                    return Unit.INSTANCE;
                }
                HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                Object obj11 = obj4;
                StringBuilder sb4 = new StringBuilder();
                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace5, str10);
                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                    mainVM = mainVM322;
                    str15 = "null cannot be cast to non-null type android.app.Application";
                    str16 = str10;
                    i = 2;
                } else {
                    mainVM = mainVM322;
                    str15 = "null cannot be cast to non-null type android.app.Application";
                    str16 = str10;
                    i = 2;
                    r2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                r2 = (flowCollector == null || (cls4 = flowCollector.getClass()) == null) ? 0 : cls4.getCanonicalName();
                if (r2 == 0) {
                    r2 = "N/A";
                }
                objectRef5.element = r2;
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
                    Intrinsics.checkNotNullExpressionValue(str17, str9);
                }
                sb4.append(str17);
                sb4.append(" - ");
                sb4.append("fetchDefaultRemoteConfig() failed");
                sb4.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str18 = '\n' + localizedMessage;
                } else {
                    str18 = "";
                }
                sb4.append(str18);
                companion8.log(level3, sb4.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion9 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str12 = str15;
                    try {
                        Intrinsics.checkNotNull(invoke3, str12);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                    } catch (Throwable th5) {
                        th = th5;
                        Result.Companion companion10 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        }
                        String str32 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                        }
                        mainVM.setDefaultRemoteConfig$hyperkyc_release(new HSDefaultRemoteConfig(null, 1, null));
                        failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                        mainVM$fetchDefaultRemoteConfig$12 = this;
                        mainVM$fetchDefaultRemoteConfig$12.L$0 = flowCollector;
                        mainVM$fetchDefaultRemoteConfig$12.L$1 = obj1022;
                        mainVM$fetchDefaultRemoteConfig$12.label = 3;
                        obj6 = obj11;
                        if (flowCollector.emit(failed, mainVM$fetchDefaultRemoteConfig$12) != obj6) {
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    str12 = str15;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str322 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    str13 = str16;
                    str14 = str11;
                } else {
                    str14 = str11;
                    Intrinsics.checkNotNullExpressionValue(str322, str14);
                    if (StringsKt.contains$default((CharSequence) str322, charSequence, false, i, (Object) null)) {
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
                            str19 = (String) objectRef6.element;
                        } else {
                            str19 = ((String) objectRef6.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str19, str9);
                        }
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("fetchDefaultRemoteConfig() failed");
                        sb5.append(' ');
                        String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage2 != null) {
                            str20 = '\n' + localizedMessage2;
                        } else {
                            str20 = "";
                        }
                        sb5.append(str20);
                        Log.println(6, str19, sb5.toString());
                    } else {
                        str13 = str16;
                    }
                }
                mainVM.setDefaultRemoteConfig$hyperkyc_release(new HSDefaultRemoteConfig(null, 1, null));
                failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                mainVM$fetchDefaultRemoteConfig$12 = this;
                mainVM$fetchDefaultRemoteConfig$12.L$0 = flowCollector;
                mainVM$fetchDefaultRemoteConfig$12.L$1 = obj1022;
                mainVM$fetchDefaultRemoteConfig$12.label = 3;
                obj6 = obj11;
                if (flowCollector.emit(failed, mainVM$fetchDefaultRemoteConfig$12) != obj6) {
                    return obj6;
                }
                obj7 = obj1022;
                obj5 = obj7;
                MainVM mainVM222 = mainVM$fetchDefaultRemoteConfig$12.this$0;
                if (Result.m1209isSuccessimpl(obj5)) {
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector2;
            str6 = "Throwable().stackTrace";
            mainVM$fetchDefaultRemoteConfig$1 = this;
            obj3 = coroutine_suspended;
            charSequence = "co.hyperverge";
            str5 = "packageName";
        }
        MainVM mainVM4 = mainVM$fetchDefaultRemoteConfig$1.this$0;
        Result.Companion companion11 = Result.INSTANCE;
        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
        cacheDir = mainVM4.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        mainVM$fetchDefaultRemoteConfig$1.L$0 = flowCollector;
        mainVM$fetchDefaultRemoteConfig$1.label = 2;
        str8 = "replaceAll(\"\")";
        str10 = str6;
        str9 = "this as java.lang.String…ing(startIndex, endIndex)";
        FlowCollector flowCollector3 = flowCollector;
        str11 = str5;
        obj4 = obj3;
        fetchDefaultRemoteConfig$hyperkyc_release$default = NetworkRepo.fetchDefaultRemoteConfig$hyperkyc_release$default(networkRepo, cacheDir, null, this, 2, null);
        if (fetchDefaultRemoteConfig$hyperkyc_release$default == obj4) {
            return obj4;
        }
        flowCollector = flowCollector3;
        m1202constructorimpl2 = Result.m1202constructorimpl((HSDefaultRemoteConfig) fetchDefaultRemoteConfig$hyperkyc_release$default);
        Object obj10222 = m1202constructorimpl2;
        MainVM mainVM3222 = this.this$0;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj10222);
        if (m1205exceptionOrNullimpl == null) {
        }
    }
}
