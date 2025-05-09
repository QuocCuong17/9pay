package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSDefaultRemoteConfig;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$fetchDefaultRemoteConfig$2", f = "NetworkRepo.kt", i = {0, 1}, l = {308, 308}, m = "invokeSuspend", n = {"$this$onIO", "$this$onIO"}, s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$fetchDefaultRemoteConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSDefaultRemoteConfig>, Object> {
    final /* synthetic */ File $cacheDir;
    final /* synthetic */ String $defaultRemoteConfigJson;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$fetchDefaultRemoteConfig$2(File file, String str, Continuation<? super NetworkRepo$fetchDefaultRemoteConfig$2> continuation) {
        super(2, continuation);
        this.$cacheDir = file;
        this.$defaultRemoteConfigJson = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$fetchDefaultRemoteConfig$2 networkRepo$fetchDefaultRemoteConfig$2 = new NetworkRepo$fetchDefaultRemoteConfig$2(this.$cacheDir, this.$defaultRemoteConfigJson, continuation);
        networkRepo$fetchDefaultRemoteConfig$2.L$0 = obj;
        return networkRepo$fetchDefaultRemoteConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSDefaultRemoteConfig> continuation) {
        return ((NetworkRepo$fetchDefaultRemoteConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(9:(2:4|(11:6|7|8|9|10|11|12|13|(20:15|(1:96)(1:19)|(1:28)(1:24)|(1:26)(1:27)|29|(1:31)(1:95)|32|(2:34|(1:36)(12:93|38|(1:40)(1:92)|41|(1:43)(1:91)|44|45|46|47|(1:49)|50|(2:52|(12:54|(1:84)(1:58)|(1:66)(1:63)|(1:65)|67|(1:69)|70|(1:83)(1:74)|75|(1:77)(1:82)|(1:79)(1:81)|80))))(1:94)|37|38|(0)(0)|41|(0)(0)|44|45|46|47|(0)|50|(0))(1:97)|85|86)(2:106|107))(4:108|109|110|111)|103|104|105|100|13|(0)(0)|85|86)(29:136|137|138|139|140|141|142|143|144|(3:261|262|(21:264|265|266|(16:268|153|154|(1:156)|157|158|(9:163|164|(1:166)|167|(1:169)(13:184|185|186|187|188|189|190|191|192|(1:194)|195|196|(3:198|199|(20:201|202|203|204|(2:206|(16:208|209|(12:211|212|(1:214)|215|(7:225|221|(1:223)|224|171|172|(3:174|175|(1:177))(8:178|114|115|116|117|118|119|(1:121)(8:122|10|11|12|13|(0)(0)|85|86)))|220|221|(0)|224|171|172|(0)(0))|(1:234)(1:230)|(1:232)(1:233)|212|(0)|215|(1:226)(8:217|225|221|(0)|224|171|172|(0)(0))|220|221|(0)|224|171|172|(0)(0)))|236|(1:228)|234|(0)(0)|212|(0)|215|(0)(0)|220|221|(0)|224|171|172|(0)(0))))|170|171|172|(0)(0))|256|164|(0)|167|(0)(0)|170|171|172|(0)(0))|(1:260)(1:150)|(1:152)(1:259)|153|154|(0)|157|158|(10:160|163|164|(0)|167|(0)(0)|170|171|172|(0)(0))|256|164|(0)|167|(0)(0)|170|171|172|(0)(0)))|146|(1:148)|260|(0)(0)|153|154|(0)|157|158|(0)|256|164|(0)|167|(0)(0)|170|171|172|(0)(0))|112|(6:129|12|13|(0)(0)|85|86)|114|115|116|117|118|119|(0)(0)|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(20:15|(1:96)(1:19)|(1:28)(1:24)|(1:26)(1:27)|29|(1:31)(1:95)|32|(2:34|(1:36)(12:93|38|(1:40)(1:92)|41|(1:43)(1:91)|44|45|46|47|(1:49)|50|(2:52|(12:54|(1:84)(1:58)|(1:66)(1:63)|(1:65)|67|(1:69)|70|(1:83)(1:74)|75|(1:77)(1:82)|(1:79)(1:81)|80))))(1:94)|37|38|(0)(0)|41|(0)(0)|44|45|46|47|(0)|50|(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:136|(8:137|138|139|140|141|142|143|144)|(3:261|262|(21:264|265|266|(16:268|153|154|(1:156)|157|158|(9:163|164|(1:166)|167|(1:169)(13:184|185|186|187|188|189|190|191|192|(1:194)|195|196|(3:198|199|(20:201|202|203|204|(2:206|(16:208|209|(12:211|212|(1:214)|215|(7:225|221|(1:223)|224|171|172|(3:174|175|(1:177))(8:178|114|115|116|117|118|119|(1:121)(8:122|10|11|12|13|(0)(0)|85|86)))|220|221|(0)|224|171|172|(0)(0))|(1:234)(1:230)|(1:232)(1:233)|212|(0)|215|(1:226)(8:217|225|221|(0)|224|171|172|(0)(0))|220|221|(0)|224|171|172|(0)(0)))|236|(1:228)|234|(0)(0)|212|(0)|215|(0)(0)|220|221|(0)|224|171|172|(0)(0))))|170|171|172|(0)(0))|256|164|(0)|167|(0)(0)|170|171|172|(0)(0))|(1:260)(1:150)|(1:152)(1:259)|153|154|(0)|157|158|(10:160|163|164|(0)|167|(0)(0)|170|171|172|(0)(0))|256|164|(0)|167|(0)(0)|170|171|172|(0)(0)))|146|(1:148)|260|(0)(0)|153|154|(0)|157|158|(0)|256|164|(0)|167|(0)(0)|170|171|172|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x031f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x032c, code lost:
    
        r1 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0321, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0322, code lost:
    
        r12 = "";
        r27 = "replaceAll(\"\")";
        r18 = r5;
        r24 = r10;
        r13 = r23;
        r10 = "this as java.lang.String…ing(startIndex, endIndex)";
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x032f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0330, code lost:
    
        r12 = "";
        r27 = "replaceAll(\"\")";
        r24 = r10;
        r13 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x03b1, code lost:
    
        if (r11 != null) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0340, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0341, code lost:
    
        r12 = "";
        r27 = "replaceAll(\"\")";
        r10 = "this as java.lang.String…ing(startIndex, endIndex)";
        r13 = r23;
        r8 = r25;
        r9 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x04d0, code lost:
    
        if (r5 != 0) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0478, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0479, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0311 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0113 A[Catch: all -> 0x00ea, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00ea, blocks: (B:266:0x00c8, B:156:0x0113, B:160:0x0128, B:163:0x012f, B:148:0x00df, B:150:0x00e5), top: B:265:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0128 A[Catch: all -> 0x00ea, TRY_ENTER, TryCatch #4 {all -> 0x00ea, blocks: (B:266:0x00c8, B:156:0x0113, B:160:0x0128, B:163:0x012f, B:148:0x00df, B:150:0x00e5), top: B:265:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x017f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x01cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0239 A[Catch: all -> 0x02d1, TryCatch #10 {all -> 0x02d1, blocks: (B:112:0x02bc, B:175:0x02a8, B:199:0x01cb, B:201:0x01dd, B:209:0x0206, B:212:0x0225, B:214:0x0239, B:215:0x0242, B:217:0x024e, B:220:0x0266, B:221:0x026a, B:224:0x0282, B:225:0x0256, B:228:0x0213, B:230:0x0219), top: B:198:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x024e A[Catch: all -> 0x02d1, TryCatch #10 {all -> 0x02d1, blocks: (B:112:0x02bc, B:175:0x02a8, B:199:0x01cb, B:201:0x01dd, B:209:0x0206, B:212:0x0225, B:214:0x0239, B:215:0x0242, B:217:0x024e, B:220:0x0266, B:221:0x026a, B:224:0x0282, B:225:0x0256, B:228:0x0213, B:230:0x0219), top: B:198:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0263 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x055a  */
    /* JADX WARN: Type inference failed for: r11v24, types: [T] */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r11v37 */
    /* JADX WARN: Type inference failed for: r12v14, types: [T] */
    /* JADX WARN: Type inference failed for: r12v24 */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v43 */
    /* JADX WARN: Type inference failed for: r1v30, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v57 */
    /* JADX WARN: Type inference failed for: r2v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v17, types: [T] */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v27, types: [T] */
    /* JADX WARN: Type inference failed for: r6v39, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v51, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v66 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        File file;
        String str;
        String str2;
        CharSequence charSequence;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        CoroutineScope coroutineScope2;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        String className;
        String str8;
        String str9;
        String str10;
        String substringAfterLast$default;
        String str11;
        Matcher matcher;
        String str12;
        String str13;
        Object m1202constructorimpl;
        String str14;
        StackTraceElement stackTraceElement2;
        String str15;
        Class<?> cls;
        Matcher matcher2;
        String str16;
        String str17;
        Deferred deferred;
        NetworkRepo$fetchDefaultRemoteConfig$2 networkRepo$fetchDefaultRemoteConfig$2;
        String str18;
        Object obj2;
        Object await;
        Class<?> cls2;
        Object callDefaultRemoteConfigFetchAPI$default;
        HSDefaultRemoteConfig hSDefaultRemoteConfig;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        String str19;
        String str20;
        String str21;
        boolean z;
        String str22;
        int i;
        String localizedMessage;
        String str23;
        Object m1202constructorimpl3;
        String str24;
        ?? r5;
        String str25;
        String str26;
        Class<?> cls3;
        String className2;
        Class<?> cls4;
        String className3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            file = this.$cacheDir;
            String str27 = this.$defaultRemoteConfigJson;
            try {
                Result.Companion companion2 = Result.INSTANCE;
                level = HyperLogger.Level.DEBUG;
                companion = HyperLogger.INSTANCE.getInstance();
                sb = new StringBuilder();
                charSequence = "co.hyperverge";
                try {
                    objectRef = new Ref.ObjectRef();
                    str3 = "packageName";
                    try {
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    } catch (Throwable th) {
                        th = th;
                        str5 = "";
                        str = "replaceAll(\"\")";
                        str2 = "null cannot be cast to non-null type android.app.Application";
                        str4 = "getInitialApplication";
                        str6 = "Throwable().stackTrace";
                        str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                        coroutineScope2 = coroutineScope;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        CoroutineScope coroutineScope3 = coroutineScope2;
                        Object obj4 = m1202constructorimpl2;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        ResultKt.throwOnFailure(obj3);
                        return obj3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = "replaceAll(\"\")";
                    str2 = "null cannot be cast to non-null type android.app.Application";
                    str3 = "packageName";
                    str4 = "getInitialApplication";
                    str5 = "";
                    str6 = "Throwable().stackTrace";
                    str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                    coroutineScope2 = coroutineScope;
                    Result.Companion companion32 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    CoroutineScope coroutineScope32 = coroutineScope2;
                    Object obj42 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    ResultKt.throwOnFailure(obj3);
                    return obj3;
                }
            } catch (Throwable th3) {
                th = th3;
                str = "replaceAll(\"\")";
                str2 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
            }
            if (stackTraceElement != null) {
                try {
                    className = stackTraceElement.getClassName();
                } catch (Throwable th4) {
                    th = th4;
                    coroutineScope2 = coroutineScope;
                    str5 = "";
                    str = "replaceAll(\"\")";
                    str2 = "null cannot be cast to non-null type android.app.Application";
                    str4 = "getInitialApplication";
                }
                if (className != null) {
                    str8 = "Throwable().stackTrace";
                    str9 = "null cannot be cast to non-null type android.app.Application";
                    str10 = "getInitialApplication";
                    try {
                        substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    } catch (Throwable th5) {
                        th = th5;
                        coroutineScope2 = coroutineScope;
                        str5 = "";
                        str = "replaceAll(\"\")";
                        str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str6 = str8;
                        str2 = str9;
                        str4 = str10;
                    }
                    if (substringAfterLast$default != null) {
                        str11 = substringAfterLast$default;
                        objectRef.element = str11;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                        if (matcher.find()) {
                            ?? replaceAll = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                            objectRef.element = replaceAll;
                        }
                        if (((String) objectRef.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str12 = ((String) objectRef.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                            sb.append(str12);
                            sb.append(" - ");
                            str13 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                            if (str13 == null) {
                                str13 = "null ";
                            }
                            sb.append(str13);
                            sb.append(' ');
                            sb.append("");
                            companion.log(level, sb.toString());
                            if (CoreExtsKt.isRelease()) {
                                str14 = str3;
                                str2 = str9;
                                str4 = str10;
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    str4 = str10;
                                    try {
                                        Object invoke = Class.forName("android.app.AppGlobals").getMethod(str4, new Class[0]).invoke(null, new Object[0]);
                                        str2 = str9;
                                        try {
                                            Intrinsics.checkNotNull(invoke, str2);
                                            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                        } catch (Throwable th6) {
                                            th = th6;
                                            try {
                                                Result.Companion companion5 = Result.INSTANCE;
                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                                }
                                                String str28 = (String) m1202constructorimpl;
                                                str14 = str3;
                                                if (CoreExtsKt.isDebug()) {
                                                }
                                                deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                                                if (deferred == null) {
                                                }
                                            } catch (Throwable th7) {
                                                th = th7;
                                                str5 = "";
                                                str = "replaceAll(\"\")";
                                                str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                str6 = str8;
                                                coroutineScope2 = coroutineScope;
                                                Result.Companion companion322 = Result.INSTANCE;
                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                CoroutineScope coroutineScope322 = coroutineScope2;
                                                Object obj422 = m1202constructorimpl2;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                ResultKt.throwOnFailure(obj3);
                                                return obj3;
                                            }
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        str2 = str9;
                                    }
                                } catch (Throwable th9) {
                                    th = th9;
                                    str2 = str9;
                                    str4 = str10;
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                    m1202constructorimpl = "";
                                }
                                String str282 = (String) m1202constructorimpl;
                                str14 = str3;
                                if (CoreExtsKt.isDebug()) {
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(str282, str14);
                                    } catch (Throwable th10) {
                                        th = th10;
                                        coroutineScope2 = coroutineScope;
                                        str5 = "";
                                        str = "replaceAll(\"\")";
                                        str3 = str14;
                                        str6 = str8;
                                    }
                                    if (StringsKt.contains$default((CharSequence) str282, charSequence, false, 2, (Object) null)) {
                                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                        String str29 = str8;
                                        try {
                                            Intrinsics.checkNotNullExpressionValue(stackTrace2, str29);
                                            stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        } catch (Throwable th11) {
                                            th = th11;
                                            coroutineScope2 = coroutineScope;
                                            str5 = "";
                                            str = "replaceAll(\"\")";
                                            str3 = str14;
                                            str6 = str29;
                                        }
                                        if (stackTraceElement2 != null) {
                                            String className4 = stackTraceElement2.getClassName();
                                            if (className4 != null) {
                                                str8 = str29;
                                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                if (substringAfterLast$default2 != null) {
                                                    str15 = substringAfterLast$default2;
                                                    objectRef2.element = str15;
                                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                    if (matcher2.find()) {
                                                        ?? replaceAll2 = matcher2.replaceAll("");
                                                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                                        objectRef2.element = replaceAll2;
                                                    }
                                                    if (((String) objectRef2.element).length() <= 23 && Build.VERSION.SDK_INT < 26) {
                                                        str16 = ((String) objectRef2.element).substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        StringBuilder sb2 = new StringBuilder();
                                                        str17 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                                                        if (str17 == null) {
                                                            str17 = "null ";
                                                        }
                                                        sb2.append(str17);
                                                        sb2.append(' ');
                                                        sb2.append("");
                                                        Log.println(3, str16, sb2.toString());
                                                        deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                                                        if (deferred == null) {
                                                            networkRepo$fetchDefaultRemoteConfig$2 = this;
                                                            str18 = str27;
                                                            obj2 = coroutine_suspended;
                                                            String str30 = str18;
                                                            File file2 = file;
                                                            CoroutineScope coroutineScope4 = coroutineScope;
                                                            NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                                                            networkRepo$fetchDefaultRemoteConfig$2.L$0 = coroutineScope4;
                                                            networkRepo$fetchDefaultRemoteConfig$2.L$1 = null;
                                                            networkRepo$fetchDefaultRemoteConfig$2.L$2 = null;
                                                            networkRepo$fetchDefaultRemoteConfig$2.label = 2;
                                                            str5 = "";
                                                            str = "replaceAll(\"\")";
                                                            CoroutineScope coroutineScope5 = coroutineScope4;
                                                            str6 = str8;
                                                            str3 = str14;
                                                            str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                            callDefaultRemoteConfigFetchAPI$default = NetworkRepo.callDefaultRemoteConfigFetchAPI$default(networkRepo, file2, null, str30, this, 2, null);
                                                            if (callDefaultRemoteConfigFetchAPI$default != obj2) {
                                                                return obj2;
                                                            }
                                                            coroutineScope2 = coroutineScope5;
                                                            hSDefaultRemoteConfig = (HSDefaultRemoteConfig) callDefaultRemoteConfigFetchAPI$default;
                                                            m1202constructorimpl2 = Result.m1202constructorimpl(hSDefaultRemoteConfig);
                                                            CoroutineScope coroutineScope3222 = coroutineScope2;
                                                            Object obj4222 = m1202constructorimpl2;
                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222);
                                                            if (m1205exceptionOrNullimpl != null) {
                                                            }
                                                            ResultKt.throwOnFailure(obj3);
                                                            return obj3;
                                                        }
                                                        networkRepo$fetchDefaultRemoteConfig$2 = this;
                                                        networkRepo$fetchDefaultRemoteConfig$2.L$0 = coroutineScope;
                                                        networkRepo$fetchDefaultRemoteConfig$2.L$1 = file;
                                                        str18 = str27;
                                                        networkRepo$fetchDefaultRemoteConfig$2.L$2 = str18;
                                                        networkRepo$fetchDefaultRemoteConfig$2.label = 1;
                                                        await = deferred.await(networkRepo$fetchDefaultRemoteConfig$2);
                                                        obj2 = coroutine_suspended;
                                                        if (await == obj2) {
                                                            return obj2;
                                                        }
                                                    }
                                                    str16 = (String) objectRef2.element;
                                                    StringBuilder sb22 = new StringBuilder();
                                                    str17 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                                                    if (str17 == null) {
                                                    }
                                                    sb22.append(str17);
                                                    sb22.append(' ');
                                                    sb22.append("");
                                                    Log.println(3, str16, sb22.toString());
                                                    deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                                                    if (deferred == null) {
                                                    }
                                                }
                                                String canonicalName = (coroutineScope != null || (cls = coroutineScope.getClass()) == null) ? null : cls.getCanonicalName();
                                                str15 = canonicalName != null ? "N/A" : canonicalName;
                                                objectRef2.element = str15;
                                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                if (matcher2.find()) {
                                                }
                                                if (((String) objectRef2.element).length() <= 23) {
                                                    str16 = ((String) objectRef2.element).substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    StringBuilder sb222 = new StringBuilder();
                                                    str17 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                                                    if (str17 == null) {
                                                    }
                                                    sb222.append(str17);
                                                    sb222.append(' ');
                                                    sb222.append("");
                                                    Log.println(3, str16, sb222.toString());
                                                    deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                                                    if (deferred == null) {
                                                    }
                                                }
                                                str16 = (String) objectRef2.element;
                                                StringBuilder sb2222 = new StringBuilder();
                                                str17 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                                                if (str17 == null) {
                                                }
                                                sb2222.append(str17);
                                                sb2222.append(' ');
                                                sb2222.append("");
                                                Log.println(3, str16, sb2222.toString());
                                                deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                                                if (deferred == null) {
                                                }
                                            }
                                        }
                                        str8 = str29;
                                        if (coroutineScope != null) {
                                        }
                                        if (canonicalName != null) {
                                        }
                                        objectRef2.element = str15;
                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                        if (matcher2.find()) {
                                        }
                                        if (((String) objectRef2.element).length() <= 23) {
                                        }
                                        str16 = (String) objectRef2.element;
                                        StringBuilder sb22222 = new StringBuilder();
                                        str17 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                                        if (str17 == null) {
                                        }
                                        sb22222.append(str17);
                                        sb22222.append(' ');
                                        sb22222.append("");
                                        Log.println(3, str16, sb22222.toString());
                                        deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                                        if (deferred == null) {
                                        }
                                    }
                                }
                            }
                            deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                            if (deferred == null) {
                            }
                        }
                        str12 = (String) objectRef.element;
                        sb.append(str12);
                        sb.append(" - ");
                        str13 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                        if (str13 == null) {
                        }
                        sb.append(str13);
                        sb.append(' ');
                        sb.append("");
                        companion.log(level, sb.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                        if (deferred == null) {
                        }
                    }
                    String canonicalName2 = (coroutineScope != null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
                    str11 = canonicalName2 != null ? "N/A" : canonicalName2;
                    objectRef.element = str11;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef.element).length() > 23) {
                        str12 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(str12);
                        sb.append(" - ");
                        str13 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                        if (str13 == null) {
                        }
                        sb.append(str13);
                        sb.append(' ');
                        sb.append("");
                        companion.log(level, sb.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                        if (deferred == null) {
                        }
                    }
                    str12 = (String) objectRef.element;
                    sb.append(str12);
                    sb.append(" - ");
                    str13 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
                    if (str13 == null) {
                    }
                    sb.append(str13);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                    }
                    deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
                    if (deferred == null) {
                    }
                }
            }
            str8 = "Throwable().stackTrace";
            str9 = "null cannot be cast to non-null type android.app.Application";
            str10 = "getInitialApplication";
            if (coroutineScope != null) {
            }
            if (canonicalName2 != null) {
            }
            objectRef.element = str11;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
            }
            if (((String) objectRef.element).length() > 23) {
            }
            str12 = (String) objectRef.element;
            sb.append(str12);
            sb.append(" - ");
            str13 = "fetchDefaultRemoteConfig() called with cacheDir = " + file;
            if (str13 == null) {
            }
            sb.append(str13);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
            }
            deferred = NetworkRepo.prefetchDefaultRemoteConfigDeferred;
            if (deferred == null) {
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope2 = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    callDefaultRemoteConfigFetchAPI$default = obj;
                    str = "replaceAll(\"\")";
                    str2 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    str4 = "getInitialApplication";
                    str5 = "";
                    str6 = "Throwable().stackTrace";
                    str7 = "this as java.lang.String…ing(startIndex, endIndex)";
                    try {
                        hSDefaultRemoteConfig = (HSDefaultRemoteConfig) callDefaultRemoteConfigFetchAPI$default;
                        m1202constructorimpl2 = Result.m1202constructorimpl(hSDefaultRemoteConfig);
                    } catch (Throwable th12) {
                        th = th12;
                    }
                } catch (Throwable th13) {
                    th = th13;
                }
                CoroutineScope coroutineScope32222 = coroutineScope2;
                Object obj42222 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42222);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str6);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                        obj3 = obj42222;
                        str19 = str6;
                    } else {
                        obj3 = obj42222;
                        str19 = str6;
                        String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        str20 = substringAfterLast$default3;
                    }
                    String canonicalName3 = (coroutineScope32222 == null || (cls4 = coroutineScope32222.getClass()) == null) ? null : cls4.getCanonicalName();
                    str20 = canonicalName3 == null ? "N/A" : canonicalName3;
                    objectRef3.element = str20;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher3.find()) {
                        ?? replaceAll3 = matcher3.replaceAll(str5);
                        str21 = str;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str21);
                        objectRef3.element = replaceAll3;
                    } else {
                        str21 = str;
                    }
                    if (((String) objectRef3.element).length() <= 23) {
                        z = false;
                    } else if (Build.VERSION.SDK_INT >= 26) {
                        z = false;
                    } else {
                        String substring = ((String) objectRef3.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(substring, str7);
                        str22 = substring;
                        i = 0;
                        sb3.append(str22);
                        sb3.append(" - ");
                        sb3.append("fetchDefaultRemoteConfig() failed");
                        sb3.append(' ');
                        localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage == null) {
                            str23 = '\n' + localizedMessage;
                        } else {
                            str23 = str5;
                        }
                        sb3.append(str23);
                        companion6.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        Result.Companion companion7 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str4, new Class[i]).invoke(null, new Object[i]);
                        Intrinsics.checkNotNull(invoke2, str2);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = str5;
                        }
                        String str31 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str31, str3);
                            if (StringsKt.contains$default(str31, charSequence, (boolean) i, 2, (Object) null)) {
                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str19);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                    str24 = null;
                                } else {
                                    str24 = null;
                                    r5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                r5 = (coroutineScope32222 == null || (cls3 = coroutineScope32222.getClass()) == null) ? str24 : cls3.getCanonicalName();
                                if (r5 == 0) {
                                    r5 = "N/A";
                                }
                                objectRef4.element = r5;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher4.find()) {
                                    ?? replaceAll4 = matcher4.replaceAll(str5);
                                    Intrinsics.checkNotNullExpressionValue(replaceAll4, str21);
                                    objectRef4.element = replaceAll4;
                                }
                                if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str25 = (String) objectRef4.element;
                                } else {
                                    str25 = ((String) objectRef4.element).substring(i, 23);
                                    Intrinsics.checkNotNullExpressionValue(str25, str7);
                                }
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("fetchDefaultRemoteConfig() failed");
                                sb4.append(' ');
                                String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str24;
                                if (localizedMessage2 != null) {
                                    str26 = '\n' + localizedMessage2;
                                } else {
                                    str26 = str5;
                                }
                                sb4.append(str26);
                                Log.println(6, str25, sb4.toString());
                            }
                        }
                    }
                    str22 = (String) objectRef3.element;
                    i = z;
                    sb3.append(str22);
                    sb3.append(" - ");
                    sb3.append("fetchDefaultRemoteConfig() failed");
                    sb3.append(' ');
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    if (localizedMessage == null) {
                    }
                    sb3.append(str23);
                    companion6.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    Result.Companion companion72 = Result.INSTANCE;
                    Object invoke22 = Class.forName("android.app.AppGlobals").getMethod(str4, new Class[i]).invoke(null, new Object[i]);
                    Intrinsics.checkNotNull(invoke22, str2);
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    }
                    String str312 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                    }
                } else {
                    obj3 = obj42222;
                }
                ResultKt.throwOnFailure(obj3);
                return obj3;
            }
            String str32 = (String) this.L$2;
            File file3 = (File) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                file = file3;
                str8 = "Throwable().stackTrace";
                networkRepo$fetchDefaultRemoteConfig$2 = this;
                str2 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str14 = "packageName";
                str18 = str32;
                obj2 = coroutine_suspended;
                str4 = "getInitialApplication";
                await = obj;
            } catch (Throwable th14) {
                th = th14;
                coroutineScope2 = coroutineScope;
            }
            str = "replaceAll(\"\")";
            str2 = "null cannot be cast to non-null type android.app.Application";
            charSequence = "co.hyperverge";
            str3 = "packageName";
            str4 = "getInitialApplication";
            str5 = "";
            str6 = "Throwable().stackTrace";
            str7 = "this as java.lang.String…ing(startIndex, endIndex)";
            Result.Companion companion3222 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            CoroutineScope coroutineScope322222 = coroutineScope2;
            Object obj422222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj3);
            return obj3;
        }
        hSDefaultRemoteConfig = (HSDefaultRemoteConfig) await;
        if (hSDefaultRemoteConfig != null) {
            coroutineScope2 = coroutineScope;
            str5 = "";
            str = "replaceAll(\"\")";
            str3 = str14;
            str6 = str8;
            str7 = "this as java.lang.String…ing(startIndex, endIndex)";
            m1202constructorimpl2 = Result.m1202constructorimpl(hSDefaultRemoteConfig);
            CoroutineScope coroutineScope3222222 = coroutineScope2;
            Object obj4222222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj3);
            return obj3;
        }
        String str302 = str18;
        File file22 = file;
        CoroutineScope coroutineScope42 = coroutineScope;
        NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
        networkRepo$fetchDefaultRemoteConfig$2.L$0 = coroutineScope42;
        networkRepo$fetchDefaultRemoteConfig$2.L$1 = null;
        networkRepo$fetchDefaultRemoteConfig$2.L$2 = null;
        networkRepo$fetchDefaultRemoteConfig$2.label = 2;
        str5 = "";
        str = "replaceAll(\"\")";
        CoroutineScope coroutineScope52 = coroutineScope42;
        str6 = str8;
        str3 = str14;
        str7 = "this as java.lang.String…ing(startIndex, endIndex)";
        callDefaultRemoteConfigFetchAPI$default = NetworkRepo.callDefaultRemoteConfigFetchAPI$default(networkRepo2, file22, null, str302, this, 2, null);
        if (callDefaultRemoteConfigFetchAPI$default != obj2) {
        }
    }
}
