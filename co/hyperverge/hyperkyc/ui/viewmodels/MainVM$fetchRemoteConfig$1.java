package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
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
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00050\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "Lco/hyperverge/hyperkyc/ui/custom/RemoteConfigUIState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$fetchRemoteConfig$1", f = "MainVM.kt", i = {0, 1, 2}, l = {668, 671, 678, 683, 686}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MainVM$fetchRemoteConfig$1 extends SuspendLambda implements Function2<FlowCollector<? super NetworkUIState<? extends HSRemoteConfig>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ HyperKycConfig $hyperKycConfig;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$fetchRemoteConfig$1(HyperKycConfig hyperKycConfig, MainVM mainVM, Continuation<? super MainVM$fetchRemoteConfig$1> continuation) {
        super(2, continuation);
        this.$hyperKycConfig = hyperKycConfig;
        this.this$0 = mainVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$fetchRemoteConfig$1 mainVM$fetchRemoteConfig$1 = new MainVM$fetchRemoteConfig$1(this.$hyperKycConfig, this.this$0, continuation);
        mainVM$fetchRemoteConfig$1.L$0 = obj;
        return mainVM$fetchRemoteConfig$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends HSRemoteConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return invoke2((FlowCollector<? super NetworkUIState<HSRemoteConfig>>) flowCollector, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super NetworkUIState<HSRemoteConfig>> flowCollector, Continuation<? super Unit> continuation) {
        return ((MainVM$fetchRemoteConfig$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(1:(2:4|(2:6|(2:8|(3:15|16|17)(2:12|13))(6:18|19|20|(1:97)(2:22|(14:24|(1:93)(1:28)|(1:37)(1:33)|(1:35)(1:36)|38|(1:40)|41|(1:92)(1:45)|46|(1:48)|49|(6:54|55|56|(1:58)|59|(2:61|(9:63|(2:(1:88)(1:85)|(1:87))|69|(1:71)|72|(1:81)(1:76)|77|(1:79)|80)))|51|(1:53))(2:94|(1:96)))|16|17))(7:98|99|100|101|102|103|(23:105|(1:190)(1:109)|(1:118)(1:114)|(1:116)(1:117)|119|(1:121)|122|(1:189)(1:126)|127|(1:129)(1:188)|(1:131)(1:187)|132|133|134|135|136|137|138|(1:140)|141|(2:143|(10:145|(2:(1:178)(1:175)|(1:177))|151|(1:153)|154|(1:171)(1:158)|159|(1:161)(1:170)|(1:163)(1:169)|164)(1:179))(1:180)|165|(1:167)(6:168|19|20|(0)(0)|16|17))(5:191|20|(0)(0)|16|17)))(1:198))(14:204|(1:282)(1:208)|(1:217)(1:213)|(1:215)(1:216)|218|(1:220)|221|(1:281)(1:225)|226|(1:228)|229|(1:231)(9:235|236|237|238|239|240|(1:242)|243|(2:245|(9:247|(2:(1:272)(1:269)|(1:271))|253|(1:255)|256|(1:265)(1:260)|261|(1:263)|264)(1:273))(1:274))|232|(1:234))|199|200|201|(1:203)|102|103|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0325, code lost:
    
        if (r12 != null) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x02ce, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x00e9, code lost:
    
        if (r5 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0561, code lost:
    
        if (r5 != null) goto L225;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0507 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02c6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0519  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:242:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0736  */
    /* JADX WARN: Type inference failed for: r12v13, types: [T] */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r1v101, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v103, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v113, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v124 */
    /* JADX WARN: Type inference failed for: r1v53, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v86 */
    /* JADX WARN: Type inference failed for: r1v87 */
    /* JADX WARN: Type inference failed for: r1v88 */
    /* JADX WARN: Type inference failed for: r1v91, types: [T] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v24, types: [T] */
    /* JADX WARN: Type inference failed for: r3v34, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v18, types: [T] */
    /* JADX WARN: Type inference failed for: r4v28, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v34, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v64 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v5, types: [T] */
    /* JADX WARN: Type inference failed for: r5v52, types: [T] */
    /* JADX WARN: Type inference failed for: r5v56 */
    /* JADX WARN: Type inference failed for: r5v57 */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r5v65 */
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
        String str5;
        String str6;
        String str7;
        Object m1202constructorimpl;
        String str8;
        String str9;
        ?? canonicalName;
        Class<?> cls;
        String str10;
        String className;
        MainVM$fetchRemoteConfig$1 mainVM$fetchRemoteConfig$1;
        Object obj2;
        Class<?> cls2;
        String className2;
        File cacheDir;
        Object fetchRemoteConfig$hyperkyc_release;
        Object m1202constructorimpl2;
        FlowCollector flowCollector2;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        MainVM$fetchRemoteConfig$1 mainVM$fetchRemoteConfig$12;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        Object m1202constructorimpl3;
        NetworkUIState.Failed failed;
        Object obj4;
        ?? canonicalName2;
        Class<?> cls3;
        String str18;
        String str19;
        String className3;
        Class<?> cls4;
        String className4;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        Object m1202constructorimpl4;
        ?? canonicalName3;
        Class<?> cls5;
        String str25;
        String className5;
        Class<?> cls6;
        String className6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            HyperKycConfig hyperKycConfig = this.$hyperKycConfig;
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
                str2 = "null cannot be cast to non-null type android.app.Application";
                str3 = "packageName";
                str4 = "Throwable().stackTrace";
            } else {
                str2 = "null cannot be cast to non-null type android.app.Application";
                str3 = "packageName";
                str4 = "Throwable().stackTrace";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str5 = substringAfterLast$default;
            }
            String canonicalName4 = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? null : cls2.getCanonicalName();
            str5 = canonicalName4 == null ? "N/A" : canonicalName4;
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
            String str26 = "fetchRemoteConfig() called with hyperKycConfig = " + hyperKycConfig;
            if (str26 == null) {
                str26 = str;
            }
            sb.append(str26);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                str8 = str4;
                str9 = str3;
                str7 = str2;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str7 = str2;
                    try {
                        Intrinsics.checkNotNull(invoke, str7);
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String str27 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        mainVM$fetchRemoteConfig$1 = this;
                        mainVM$fetchRemoteConfig$1.L$0 = flowCollector;
                        mainVM$fetchRemoteConfig$1.label = 1;
                        obj2 = coroutine_suspended;
                        if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchRemoteConfig$1) == obj2) {
                        }
                        HyperKycConfig hyperKycConfig2 = mainVM$fetchRemoteConfig$1.$hyperKycConfig;
                        MainVM mainVM = mainVM$fetchRemoteConfig$1.this$0;
                        Result.Companion companion4 = Result.INSTANCE;
                        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                        String appId = hyperKycConfig2.getAppId();
                        Intrinsics.checkNotNull(appId);
                        cacheDir = mainVM.getCacheDir();
                        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
                        mainVM$fetchRemoteConfig$1.L$0 = flowCollector;
                        mainVM$fetchRemoteConfig$1.label = 2;
                        fetchRemoteConfig$hyperkyc_release = networkRepo.fetchRemoteConfig$hyperkyc_release(appId, cacheDir, mainVM$fetchRemoteConfig$1);
                        if (fetchRemoteConfig$hyperkyc_release == obj2) {
                        }
                        m1202constructorimpl2 = Result.m1202constructorimpl((HSRemoteConfig) fetchRemoteConfig$hyperkyc_release);
                        flowCollector2 = flowCollector;
                        Object obj5 = m1202constructorimpl2;
                        MainVM mainVM2 = mainVM$fetchRemoteConfig$1.this$0;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5);
                        if (m1205exceptionOrNullimpl == null) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str7 = str2;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String str272 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str8 = str4;
                    str9 = str3;
                } else {
                    str9 = str3;
                    Intrinsics.checkNotNullExpressionValue(str272, str9);
                    if (StringsKt.contains$default((CharSequence) str272, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str8 = str4;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str8);
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
                            str10 = (String) objectRef2.element;
                        } else {
                            str10 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str28 = "fetchRemoteConfig() called with hyperKycConfig = " + hyperKycConfig;
                        if (str28 == null) {
                            str28 = str;
                        }
                        sb2.append(str28);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(4, str10, sb2.toString());
                    } else {
                        str8 = str4;
                    }
                }
            }
            mainVM$fetchRemoteConfig$1 = this;
            mainVM$fetchRemoteConfig$1.L$0 = flowCollector;
            mainVM$fetchRemoteConfig$1.label = 1;
            obj2 = coroutine_suspended;
            if (flowCollector.emit(NetworkUIState.Loading.INSTANCE, mainVM$fetchRemoteConfig$1) == obj2) {
                return obj2;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4 && i != 5) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    obj4 = this.L$1;
                    FlowCollector flowCollector3 = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    flowCollector2 = flowCollector3;
                    charSequence = "co.hyperverge";
                    str = "null ";
                    mainVM$fetchRemoteConfig$12 = this;
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    str11 = "Throwable().stackTrace";
                    obj2 = coroutine_suspended;
                    str9 = "packageName";
                    obj3 = obj4;
                    MainVM mainVM3 = mainVM$fetchRemoteConfig$12.this$0;
                    if (Result.m1209isSuccessimpl(obj3)) {
                        HSRemoteConfig hSRemoteConfig = (HSRemoteConfig) obj3;
                        if (hSRemoteConfig != null) {
                            mainVM3.remoteConfig = hSRemoteConfig;
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                            Object obj6 = obj2;
                            StringBuilder sb3 = new StringBuilder();
                            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                            Object obj7 = obj3;
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, str11);
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null) {
                                str20 = str7;
                                str21 = str9;
                                str22 = str11;
                            } else {
                                str20 = str7;
                                str21 = str9;
                                str22 = str11;
                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                str23 = substringAfterLast$default2;
                            }
                            String canonicalName5 = (flowCollector2 == null || (cls6 = flowCollector2.getClass()) == null) ? null : cls6.getCanonicalName();
                            str23 = canonicalName5 == null ? "N/A" : canonicalName5;
                            objectRef3.element = str23;
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                            if (matcher3.find()) {
                                ?? replaceAll3 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                                objectRef3.element = replaceAll3;
                            }
                            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str24 = (String) objectRef3.element;
                            } else {
                                str24 = ((String) objectRef3.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str24, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb3.append(str24);
                            sb3.append(" - ");
                            String str29 = "fetchRemoteConfig data: " + hSRemoteConfig;
                            if (str29 == null) {
                                str29 = str;
                            }
                            sb3.append(str29);
                            sb3.append(' ');
                            sb3.append("");
                            companion5.log(level2, sb3.toString());
                            if (!CoreExtsKt.isRelease()) {
                                try {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke2, str20);
                                    m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th3) {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                    m1202constructorimpl4 = "";
                                }
                                String str30 = (String) m1202constructorimpl4;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(str30, str21);
                                    if (StringsKt.contains$default((CharSequence) str30, charSequence, false, 2, (Object) null)) {
                                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str22);
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className5 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                            canonicalName3 = (flowCollector2 == null || (cls5 = flowCollector2.getClass()) == null) ? 0 : cls5.getCanonicalName();
                                            if (canonicalName3 == 0) {
                                                canonicalName3 = "N/A";
                                            }
                                        }
                                        objectRef4.element = canonicalName3;
                                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                        if (matcher4.find()) {
                                            ?? replaceAll4 = matcher4.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                            objectRef4.element = replaceAll4;
                                        }
                                        if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                            str25 = (String) objectRef4.element;
                                        } else {
                                            str25 = ((String) objectRef4.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str25, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        String str31 = "fetchRemoteConfig data: " + hSRemoteConfig;
                                        if (str31 == null) {
                                            str31 = str;
                                        }
                                        sb4.append(str31);
                                        sb4.append(' ');
                                        sb4.append("");
                                        Log.println(3, str25, sb4.toString());
                                    }
                                }
                            }
                            NetworkUIState.Success success = new NetworkUIState.Success(hSRemoteConfig);
                            this.L$0 = obj7;
                            this.L$1 = null;
                            this.label = 4;
                            if (flowCollector2.emit(success, this) == obj6) {
                                return obj6;
                            }
                        } else {
                            MainVM$fetchRemoteConfig$1 mainVM$fetchRemoteConfig$13 = mainVM$fetchRemoteConfig$12;
                            Object obj8 = obj2;
                            mainVM3.remoteConfig = new HSRemoteConfig(false, false, null, 7, null);
                            NetworkUIState.Failed failed2 = new NetworkUIState.Failed("Remote config is corrupt or empty", null, 2, null);
                            mainVM$fetchRemoteConfig$13.L$0 = obj3;
                            mainVM$fetchRemoteConfig$13.L$1 = null;
                            mainVM$fetchRemoteConfig$13.label = 5;
                            if (flowCollector2.emit(failed2, mainVM$fetchRemoteConfig$13) == obj8) {
                                return obj8;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    fetchRemoteConfig$hyperkyc_release = obj;
                    charSequence = "co.hyperverge";
                    str = "null ";
                    str8 = "Throwable().stackTrace";
                    mainVM$fetchRemoteConfig$1 = this;
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    obj2 = coroutine_suspended;
                    str9 = "packageName";
                    m1202constructorimpl2 = Result.m1202constructorimpl((HSRemoteConfig) fetchRemoteConfig$hyperkyc_release);
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    charSequence = "co.hyperverge";
                    str = "null ";
                    str8 = "Throwable().stackTrace";
                    mainVM$fetchRemoteConfig$1 = this;
                    str7 = "null cannot be cast to non-null type android.app.Application";
                    obj2 = coroutine_suspended;
                    str9 = "packageName";
                    Result.Companion companion8 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                    flowCollector2 = flowCollector;
                    Object obj52 = m1202constructorimpl2;
                    MainVM mainVM22 = mainVM$fetchRemoteConfig$1.this$0;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj52);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                }
                flowCollector2 = flowCollector;
                Object obj522 = m1202constructorimpl2;
                MainVM mainVM222 = mainVM$fetchRemoteConfig$1.this$0;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj522);
                if (m1205exceptionOrNullimpl == null) {
                    String str32 = str8;
                    obj3 = obj522;
                    mainVM$fetchRemoteConfig$12 = mainVM$fetchRemoteConfig$1;
                    str11 = str32;
                    MainVM mainVM32 = mainVM$fetchRemoteConfig$12.this$0;
                    if (Result.m1209isSuccessimpl(obj3)) {
                    }
                    return Unit.INSTANCE;
                }
                Object obj9 = obj2;
                HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb5 = new StringBuilder();
                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace5, str8);
                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                    str12 = str7;
                    str13 = str9;
                    str14 = str8;
                } else {
                    str12 = str7;
                    str13 = str9;
                    str14 = str8;
                    String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    str15 = substringAfterLast$default3;
                }
                String canonicalName6 = (flowCollector2 == null || (cls4 = flowCollector2.getClass()) == null) ? null : cls4.getCanonicalName();
                str15 = canonicalName6 == null ? "N/A" : canonicalName6;
                objectRef5.element = str15;
                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                if (matcher5.find()) {
                    ?? replaceAll5 = matcher5.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                    objectRef5.element = replaceAll5;
                }
                if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str16 = (String) objectRef5.element;
                } else {
                    str16 = ((String) objectRef5.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb5.append(str16);
                sb5.append(" - ");
                sb5.append("fetchRemoteConfig() failed");
                sb5.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str17 = '\n' + localizedMessage;
                } else {
                    str17 = "";
                }
                sb5.append(str17);
                companion9.log(level3, sb5.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion10 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str7 = str12;
                    try {
                        Intrinsics.checkNotNull(invoke3, str7);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                    } catch (Throwable th6) {
                        th = th6;
                        Result.Companion companion11 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        }
                        String str33 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                        }
                        mainVM222.remoteConfig = new HSRemoteConfig(false, false, null, 7, null);
                        failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                        mainVM$fetchRemoteConfig$12 = this;
                        mainVM$fetchRemoteConfig$12.L$0 = flowCollector2;
                        mainVM$fetchRemoteConfig$12.L$1 = obj522;
                        mainVM$fetchRemoteConfig$12.label = 3;
                        obj2 = obj9;
                        if (flowCollector2.emit(failed, mainVM$fetchRemoteConfig$12) != obj2) {
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    str7 = str12;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str332 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    str11 = str14;
                    str9 = str13;
                } else {
                    str9 = str13;
                    Intrinsics.checkNotNullExpressionValue(str332, str9);
                    if (StringsKt.contains$default((CharSequence) str332, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                        str11 = str14;
                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str11);
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                        if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName2 = (flowCollector2 == null || (cls3 = flowCollector2.getClass()) == null) ? 0 : cls3.getCanonicalName();
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
                            str18 = (String) objectRef6.element;
                        } else {
                            str18 = ((String) objectRef6.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str18, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("fetchRemoteConfig() failed");
                        sb6.append(' ');
                        String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage2 != null) {
                            str19 = '\n' + localizedMessage2;
                        } else {
                            str19 = "";
                        }
                        sb6.append(str19);
                        Log.println(6, str18, sb6.toString());
                    } else {
                        str11 = str14;
                    }
                }
                mainVM222.remoteConfig = new HSRemoteConfig(false, false, null, 7, null);
                failed = new NetworkUIState.Failed(m1205exceptionOrNullimpl.getMessage(), null, 2, null);
                mainVM$fetchRemoteConfig$12 = this;
                mainVM$fetchRemoteConfig$12.L$0 = flowCollector2;
                mainVM$fetchRemoteConfig$12.L$1 = obj522;
                mainVM$fetchRemoteConfig$12.label = 3;
                obj2 = obj9;
                if (flowCollector2.emit(failed, mainVM$fetchRemoteConfig$12) != obj2) {
                    return obj2;
                }
                obj4 = obj522;
                obj3 = obj4;
                MainVM mainVM322 = mainVM$fetchRemoteConfig$12.this$0;
                if (Result.m1209isSuccessimpl(obj3)) {
                }
                return Unit.INSTANCE;
            }
            FlowCollector flowCollector4 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector4;
            charSequence = "co.hyperverge";
            str = "null ";
            str8 = "Throwable().stackTrace";
            mainVM$fetchRemoteConfig$1 = this;
            str7 = "null cannot be cast to non-null type android.app.Application";
            obj2 = coroutine_suspended;
            str9 = "packageName";
        }
        HyperKycConfig hyperKycConfig22 = mainVM$fetchRemoteConfig$1.$hyperKycConfig;
        MainVM mainVM4 = mainVM$fetchRemoteConfig$1.this$0;
        Result.Companion companion42 = Result.INSTANCE;
        NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
        String appId2 = hyperKycConfig22.getAppId();
        Intrinsics.checkNotNull(appId2);
        cacheDir = mainVM4.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        mainVM$fetchRemoteConfig$1.L$0 = flowCollector;
        mainVM$fetchRemoteConfig$1.label = 2;
        fetchRemoteConfig$hyperkyc_release = networkRepo2.fetchRemoteConfig$hyperkyc_release(appId2, cacheDir, mainVM$fetchRemoteConfig$1);
        if (fetchRemoteConfig$hyperkyc_release == obj2) {
            return obj2;
        }
        m1202constructorimpl2 = Result.m1202constructorimpl((HSRemoteConfig) fetchRemoteConfig$hyperkyc_release);
        flowCollector2 = flowCollector;
        Object obj5222 = m1202constructorimpl2;
        MainVM mainVM2222 = mainVM$fetchRemoteConfig$1.this$0;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5222);
        if (m1205exceptionOrNullimpl == null) {
        }
    }
}
