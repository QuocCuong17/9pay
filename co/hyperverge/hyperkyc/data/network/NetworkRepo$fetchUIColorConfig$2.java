package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$fetchUIColorConfig$2", f = "NetworkRepo.kt", i = {0, 1}, l = {554, 554}, m = "invokeSuspend", n = {"$this$onIO", "$this$onIO"}, s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$fetchUIColorConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSUIConfig>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ File $cacheDir;
    final /* synthetic */ String $uiColorConfigJson;
    final /* synthetic */ String $workflowId;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$fetchUIColorConfig$2(String str, File file, String str2, String str3, Continuation<? super NetworkRepo$fetchUIColorConfig$2> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$cacheDir = file;
        this.$workflowId = str2;
        this.$uiColorConfigJson = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$fetchUIColorConfig$2 networkRepo$fetchUIColorConfig$2 = new NetworkRepo$fetchUIColorConfig$2(this.$appId, this.$cacheDir, this.$workflowId, this.$uiColorConfigJson, continuation);
        networkRepo$fetchUIColorConfig$2.L$0 = obj;
        return networkRepo$fetchUIColorConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSUIConfig> continuation) {
        return ((NetworkRepo$fetchUIColorConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:1|(8:(1:(11:5|6|7|8|9|10|11|12|(19:14|(1:90)(1:18)|(1:26)(1:23)|(1:25)|27|(1:29)(1:89)|30|(1:88)(1:34)|35|(1:37)(1:87)|38|(1:40)(1:86)|41|42|43|44|(1:46)|47|(2:49|(13:51|(1:82)(2:55|(9:57|58|(1:60)|61|(1:74)(1:65)|66|(1:68)(1:73)|(1:70)(1:72)|71))|(1:81)(1:78)|(1:80)|58|(0)|61|(1:63)|74|66|(0)(0)|(0)(0)|71)))|91|92)(2:102|103))(4:104|105|106|107)|100|101|96|12|(0)|91|92)(32:141|142|143|144|145|146|147|148|149|150|151|152|(3:285|286|(3:288|289|290))|154|(3:281|282|(17:284|(1:158)(1:280)|159|160|(1:162)|163|164|(9:169|170|(1:172)|173|(1:175)(12:191|192|193|194|195|196|197|198|199|(1:201)|202|(10:204|205|206|207|208|209|(16:211|212|213|214|(2:216|(3:218|219|220))|248|(1:228)(1:225)|(1:227)|229|(1:231)|232|(4:242|238|(1:240)|241)|237|238|(0)|241)(1:251)|177|178|(3:180|181|(1:183))(13:184|110|111|112|113|114|115|116|117|118|119|120|(1:122)(8:123|9|10|11|12|(0)|91|92)))(1:255))|176|177|178|(0)(0))|275|170|(0)|173|(0)(0)|176|177|178|(0)(0)))|156|(0)(0)|159|160|(0)|163|164|(10:166|169|170|(0)|173|(0)(0)|176|177|178|(0)(0))|275|170|(0)|173|(0)(0)|176|177|178|(0)(0))|108|(6:133|11|12|(0)|91|92)|110|111|112|113|114|115|116|117|118|119|120|(0)(0)|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:141|142|143|144|(10:145|146|147|148|149|150|151|152|(3:285|286|(3:288|289|290))|154)|(3:281|282|(17:284|(1:158)(1:280)|159|160|(1:162)|163|164|(9:169|170|(1:172)|173|(1:175)(12:191|192|193|194|195|196|197|198|199|(1:201)|202|(10:204|205|206|207|208|209|(16:211|212|213|214|(2:216|(3:218|219|220))|248|(1:228)(1:225)|(1:227)|229|(1:231)|232|(4:242|238|(1:240)|241)|237|238|(0)|241)(1:251)|177|178|(3:180|181|(1:183))(13:184|110|111|112|113|114|115|116|117|118|119|120|(1:122)(8:123|9|10|11|12|(0)|91|92)))(1:255))|176|177|178|(0)(0))|275|170|(0)|173|(0)(0)|176|177|178|(0)(0)))|156|(0)(0)|159|160|(0)|163|164|(10:166|169|170|(0)|173|(0)(0)|176|177|178|(0)(0))|275|170|(0)|173|(0)(0)|176|177|178|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x03d6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x03f1, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x03d8, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x03d9, code lost:
    
        r30 = "replaceAll(\"\")";
        r31 = "";
        r32 = "this as java.lang.String…ing(startIndex, endIndex)";
        r15 = null;
        r33 = r24;
        r11 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x03e5, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x03e6, code lost:
    
        r30 = "replaceAll(\"\")";
        r31 = "";
        r32 = "this as java.lang.String…ing(startIndex, endIndex)";
        r33 = r24;
        r11 = r26;
        r15 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x03f4, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x03f5, code lost:
    
        r30 = "replaceAll(\"\")";
        r31 = "";
        r32 = "this as java.lang.String…ing(startIndex, endIndex)";
        r33 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x04a8, code lost:
    
        if (r8 != 0) goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0271, code lost:
    
        if (r3 != 0) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x040d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x040e, code lost:
    
        r30 = "replaceAll(\"\")";
        r31 = "";
        r32 = "this as java.lang.String…ing(startIndex, endIndex)";
        r33 = " - ";
        r12 = "null cannot be cast to non-null type android.app.Application";
        r11 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0427, code lost:
    
        r13 = "getInitialApplication";
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0437, code lost:
    
        r15 = null;
        r25 = "packageName";
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x00f1, code lost:
    
        if (r15 != null) goto L298;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0158 A[Catch: all -> 0x012e, TRY_ENTER, TRY_LEAVE, TryCatch #13 {all -> 0x012e, blocks: (B:282:0x0123, B:284:0x0129, B:162:0x0158, B:166:0x016d, B:169:0x0174), top: B:281:0x0123 }] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x01e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x05f1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0645  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0632  */
    /* JADX WARN: Type inference failed for: r15v13, types: [T] */
    /* JADX WARN: Type inference failed for: r15v22 */
    /* JADX WARN: Type inference failed for: r15v23 */
    /* JADX WARN: Type inference failed for: r15v38 */
    /* JADX WARN: Type inference failed for: r2v12, types: [T] */
    /* JADX WARN: Type inference failed for: r2v22, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v25, types: [T] */
    /* JADX WARN: Type inference failed for: r3v37, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v44, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v54, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v66 */
    /* JADX WARN: Type inference failed for: r8v22, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v29, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v7, types: [T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        File file;
        String str2;
        CharSequence charSequence;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        CoroutineScope coroutineScope2;
        HyperLogger.Level level;
        StringBuilder sb;
        Ref.ObjectRef objectRef;
        String str11;
        HyperLogger hyperLogger;
        String str12;
        String str13;
        Matcher matcher;
        String str14;
        String str15;
        String sb2;
        Object m1202constructorimpl;
        String str16;
        ?? r3;
        String str17;
        String str18;
        Class<?> cls;
        Deferred deferred;
        NetworkRepo$fetchUIColorConfig$2 networkRepo$fetchUIColorConfig$2;
        Object obj2;
        Object await;
        Class<?> cls2;
        String canonicalName;
        Object obj3;
        Object callUIConfigFetchAPI;
        HSUIConfig hSUIConfig;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        ?? r8;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        Object m1202constructorimpl3;
        String str24;
        ?? canonicalName2;
        Class<?> cls3;
        Matcher matcher2;
        String str25;
        String localizedMessage;
        String str26;
        String className;
        Class<?> cls4;
        String className2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        String str27 = "null cannot be cast to non-null type android.app.Application";
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            str = this.$appId;
            file = this.$cacheDir;
            str2 = this.$workflowId;
            charSequence = "co.hyperverge";
            String str28 = this.$uiColorConfigJson;
            try {
                Result.Companion companion = Result.INSTANCE;
                level = HyperLogger.Level.DEBUG;
                HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
                try {
                    sb = new StringBuilder();
                    try {
                        objectRef = new Ref.ObjectRef();
                        try {
                            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                            if (stackTraceElement != null) {
                                try {
                                    String className3 = stackTraceElement.getClassName();
                                    if (className3 != null) {
                                        str11 = "Throwable().stackTrace";
                                        hyperLogger = companion2;
                                        str12 = str28;
                                        try {
                                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            str13 = substringAfterLast$default;
                                        } catch (Throwable th) {
                                            th = th;
                                            coroutineScope2 = coroutineScope;
                                            str4 = "replaceAll(\"\")";
                                            str5 = "";
                                            str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                            str7 = " - ";
                                            str10 = null;
                                            str27 = "null cannot be cast to non-null type android.app.Application";
                                            str3 = str11;
                                            str9 = "getInitialApplication";
                                            str8 = "packageName";
                                            Result.Companion companion3 = Result.INSTANCE;
                                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            CoroutineScope coroutineScope3 = coroutineScope2;
                                            Object obj4 = m1202constructorimpl2;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                            ResultKt.throwOnFailure(obj4);
                                            return obj4;
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    str3 = "Throwable().stackTrace";
                                    coroutineScope2 = coroutineScope;
                                    str4 = "replaceAll(\"\")";
                                    str5 = "";
                                    str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                    str7 = " - ";
                                    str27 = "null cannot be cast to non-null type android.app.Application";
                                    str9 = "getInitialApplication";
                                    str10 = null;
                                    str8 = "packageName";
                                    Result.Companion companion32 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    CoroutineScope coroutineScope32 = coroutineScope2;
                                    Object obj42 = m1202constructorimpl2;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                    ResultKt.throwOnFailure(obj42);
                                    return obj42;
                                }
                            }
                            str11 = "Throwable().stackTrace";
                            hyperLogger = companion2;
                            str12 = str28;
                        } catch (Throwable th3) {
                            th = th3;
                            str3 = "Throwable().stackTrace";
                            str4 = "replaceAll(\"\")";
                            str5 = "";
                            str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                            str7 = " - ";
                            str27 = "null cannot be cast to non-null type android.app.Application";
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        str3 = "Throwable().stackTrace";
                        str4 = "replaceAll(\"\")";
                        str5 = "";
                        str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str7 = " - ";
                        str9 = "getInitialApplication";
                        str27 = "null cannot be cast to non-null type android.app.Application";
                    }
                } catch (Throwable th5) {
                    th = th5;
                    str3 = "Throwable().stackTrace";
                    str4 = "replaceAll(\"\")";
                    str5 = "";
                    str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str7 = " - ";
                    str9 = "getInitialApplication";
                    str8 = "packageName";
                }
            } catch (Throwable th6) {
                th = th6;
                str3 = "Throwable().stackTrace";
                str4 = "replaceAll(\"\")";
                str5 = "";
                str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                str7 = " - ";
                str8 = "packageName";
                str9 = "getInitialApplication";
            }
            if (coroutineScope != null) {
                try {
                    cls2 = coroutineScope.getClass();
                } catch (Throwable th7) {
                    th = th7;
                    coroutineScope2 = coroutineScope;
                    str4 = "replaceAll(\"\")";
                    str5 = "";
                    str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str7 = " - ";
                    str27 = "null cannot be cast to non-null type android.app.Application";
                    str3 = str11;
                    str9 = "getInitialApplication";
                    str10 = null;
                    str8 = "packageName";
                    Result.Companion companion322 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    CoroutineScope coroutineScope322 = coroutineScope2;
                    Object obj422 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    ResultKt.throwOnFailure(obj422);
                    return obj422;
                }
                if (cls2 != null) {
                    canonicalName = cls2.getCanonicalName();
                    str13 = canonicalName != null ? "N/A" : canonicalName;
                    objectRef.element = str13;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str14 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(str14);
                        sb.append(" - ");
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("fetchUIColorConfig() called with appId = ");
                        sb3.append(str);
                        sb3.append(", cacehDir = ");
                        sb3.append(file);
                        sb3.append(", workflowId = ");
                        sb3.append(str2);
                        sb3.append(", uiColorConfigJson = ");
                        str15 = str12;
                        sb3.append(str15);
                        sb2 = sb3.toString();
                        if (sb2 == null) {
                            sb2 = "null ";
                        }
                        sb.append(sb2);
                        sb.append(' ');
                        sb.append("");
                        hyperLogger.log(level, sb.toString());
                        if (CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                str9 = "getInitialApplication";
                                try {
                                    Object invoke = Class.forName("android.app.AppGlobals").getMethod(str9, new Class[0]).invoke(null, new Object[0]);
                                    str27 = "null cannot be cast to non-null type android.app.Application";
                                    try {
                                        Intrinsics.checkNotNull(invoke, str27);
                                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                    } catch (Throwable th8) {
                                        th = th8;
                                        try {
                                            Result.Companion companion5 = Result.INSTANCE;
                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                            }
                                            String packageName = (String) m1202constructorimpl;
                                            if (CoreExtsKt.isDebug()) {
                                            }
                                        } catch (Throwable th9) {
                                            th = th9;
                                            str4 = "replaceAll(\"\")";
                                            str5 = "";
                                            str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                            str7 = " - ";
                                            str8 = "packageName";
                                            str3 = str11;
                                            str10 = null;
                                            coroutineScope2 = coroutineScope;
                                            Result.Companion companion3222 = Result.INSTANCE;
                                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            CoroutineScope coroutineScope3222 = coroutineScope2;
                                            Object obj4222 = m1202constructorimpl2;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                            ResultKt.throwOnFailure(obj4222);
                                            return obj4222;
                                        }
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    str27 = "null cannot be cast to non-null type android.app.Application";
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                str27 = "null cannot be cast to non-null type android.app.Application";
                                str9 = "getInitialApplication";
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                m1202constructorimpl = "";
                            }
                            String packageName2 = (String) m1202constructorimpl;
                            if (CoreExtsKt.isDebug()) {
                                str8 = "packageName";
                            } else {
                                try {
                                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                    str16 = " - ";
                                    str8 = "packageName";
                                    try {
                                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                            String str29 = str11;
                                            try {
                                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str29);
                                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                                if (stackTraceElement2 != null) {
                                                    String className4 = stackTraceElement2.getClassName();
                                                    if (className4 != null) {
                                                        str11 = str29;
                                                        try {
                                                            r3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                        } catch (Throwable th12) {
                                                            th = th12;
                                                            coroutineScope2 = coroutineScope;
                                                            str4 = "replaceAll(\"\")";
                                                            str5 = "";
                                                            str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                            str10 = null;
                                                            str7 = str16;
                                                            str3 = str11;
                                                        }
                                                    }
                                                }
                                                str11 = str29;
                                                r3 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                                                if (r3 == 0) {
                                                    r3 = "N/A";
                                                }
                                                objectRef2.element = r3;
                                                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                if (matcher3.find()) {
                                                    ?? replaceAll2 = matcher3.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                                    objectRef2.element = replaceAll2;
                                                }
                                                if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str17 = ((String) objectRef2.element).substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str17, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    StringBuilder sb4 = new StringBuilder();
                                                    str18 = "fetchUIColorConfig() called with appId = " + str + ", cacehDir = " + file + ", workflowId = " + str2 + ", uiColorConfigJson = " + str15;
                                                    if (str18 == null) {
                                                        str18 = "null ";
                                                    }
                                                    sb4.append(str18);
                                                    sb4.append(' ');
                                                    sb4.append("");
                                                    Log.println(3, str17, sb4.toString());
                                                }
                                                str17 = (String) objectRef2.element;
                                                StringBuilder sb42 = new StringBuilder();
                                                str18 = "fetchUIColorConfig() called with appId = " + str + ", cacehDir = " + file + ", workflowId = " + str2 + ", uiColorConfigJson = " + str15;
                                                if (str18 == null) {
                                                }
                                                sb42.append(str18);
                                                sb42.append(' ');
                                                sb42.append("");
                                                Log.println(3, str17, sb42.toString());
                                            } catch (Throwable th13) {
                                                th = th13;
                                                coroutineScope2 = coroutineScope;
                                                str4 = "replaceAll(\"\")";
                                                str5 = "";
                                                str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                str3 = str29;
                                                str7 = str16;
                                            }
                                        }
                                        deferred = NetworkRepo.prefetchUIConfigDeferred;
                                    } catch (Throwable th14) {
                                        th = th14;
                                        coroutineScope2 = coroutineScope;
                                        str4 = "replaceAll(\"\")";
                                        str5 = "";
                                        str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                        str7 = str16;
                                        str3 = str11;
                                        str10 = null;
                                        Result.Companion companion32222 = Result.INSTANCE;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        CoroutineScope coroutineScope32222 = coroutineScope2;
                                        Object obj42222 = m1202constructorimpl2;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42222);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        ResultKt.throwOnFailure(obj42222);
                                        return obj42222;
                                    }
                                } catch (Throwable th15) {
                                    th = th15;
                                    str8 = "packageName";
                                    coroutineScope2 = coroutineScope;
                                    str4 = "replaceAll(\"\")";
                                    str5 = "";
                                    str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                    str7 = " - ";
                                    str3 = str11;
                                    str10 = null;
                                    Result.Companion companion322222 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    CoroutineScope coroutineScope322222 = coroutineScope2;
                                    Object obj422222 = m1202constructorimpl2;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422222);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                    ResultKt.throwOnFailure(obj422222);
                                    return obj422222;
                                }
                                if (deferred == null) {
                                    networkRepo$fetchUIColorConfig$2 = this;
                                    obj2 = coroutine_suspended;
                                    File file2 = file;
                                    String str30 = str;
                                    String str31 = str15;
                                    CoroutineScope coroutineScope4 = coroutineScope;
                                    String str32 = str2;
                                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                                    networkRepo$fetchUIColorConfig$2.L$0 = coroutineScope4;
                                    networkRepo$fetchUIColorConfig$2.L$1 = null;
                                    networkRepo$fetchUIColorConfig$2.L$2 = null;
                                    networkRepo$fetchUIColorConfig$2.L$3 = null;
                                    networkRepo$fetchUIColorConfig$2.L$4 = null;
                                    networkRepo$fetchUIColorConfig$2.label = 2;
                                    obj3 = obj2;
                                    str3 = str11;
                                    str10 = null;
                                    str4 = "replaceAll(\"\")";
                                    str5 = "";
                                    str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                                    str7 = str16;
                                    callUIConfigFetchAPI = networkRepo.callUIConfigFetchAPI(str30, file2, str32, (r16 & 8) != 0 ? null : null, (r16 & 16) != 0 ? null : str31, this);
                                    if (callUIConfigFetchAPI != obj3) {
                                        return obj3;
                                    }
                                    coroutineScope2 = coroutineScope4;
                                    hSUIConfig = (HSUIConfig) callUIConfigFetchAPI;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(hSUIConfig);
                                    CoroutineScope coroutineScope3222222 = coroutineScope2;
                                    Object obj4222222 = m1202constructorimpl2;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222222);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                    ResultKt.throwOnFailure(obj4222222);
                                    return obj4222222;
                                }
                                networkRepo$fetchUIColorConfig$2 = this;
                                networkRepo$fetchUIColorConfig$2.L$0 = coroutineScope;
                                networkRepo$fetchUIColorConfig$2.L$1 = str;
                                networkRepo$fetchUIColorConfig$2.L$2 = file;
                                networkRepo$fetchUIColorConfig$2.L$3 = str2;
                                networkRepo$fetchUIColorConfig$2.L$4 = str15;
                                networkRepo$fetchUIColorConfig$2.label = 1;
                                await = deferred.await(networkRepo$fetchUIColorConfig$2);
                                obj2 = coroutine_suspended;
                                if (await == obj2) {
                                    return obj2;
                                }
                            }
                        } else {
                            str27 = "null cannot be cast to non-null type android.app.Application";
                            str9 = "getInitialApplication";
                            str8 = "packageName";
                        }
                        str16 = " - ";
                        deferred = NetworkRepo.prefetchUIConfigDeferred;
                        if (deferred == null) {
                        }
                    }
                    str14 = (String) objectRef.element;
                    sb.append(str14);
                    sb.append(" - ");
                    StringBuilder sb32 = new StringBuilder();
                    sb32.append("fetchUIColorConfig() called with appId = ");
                    sb32.append(str);
                    sb32.append(", cacehDir = ");
                    sb32.append(file);
                    sb32.append(", workflowId = ");
                    sb32.append(str2);
                    sb32.append(", uiColorConfigJson = ");
                    str15 = str12;
                    sb32.append(str15);
                    sb2 = sb32.toString();
                    if (sb2 == null) {
                    }
                    sb.append(sb2);
                    sb.append(' ');
                    sb.append("");
                    hyperLogger.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                    }
                    str16 = " - ";
                    deferred = NetworkRepo.prefetchUIConfigDeferred;
                    if (deferred == null) {
                    }
                }
            }
            canonicalName = null;
            if (canonicalName != null) {
            }
            objectRef.element = str13;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
            }
            if (((String) objectRef.element).length() > 23) {
                str14 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(str14);
                sb.append(" - ");
                StringBuilder sb322 = new StringBuilder();
                sb322.append("fetchUIColorConfig() called with appId = ");
                sb322.append(str);
                sb322.append(", cacehDir = ");
                sb322.append(file);
                sb322.append(", workflowId = ");
                sb322.append(str2);
                sb322.append(", uiColorConfigJson = ");
                str15 = str12;
                sb322.append(str15);
                sb2 = sb322.toString();
                if (sb2 == null) {
                }
                sb.append(sb2);
                sb.append(' ');
                sb.append("");
                hyperLogger.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                str16 = " - ";
                deferred = NetworkRepo.prefetchUIConfigDeferred;
                if (deferred == null) {
                }
            }
            str14 = (String) objectRef.element;
            sb.append(str14);
            sb.append(" - ");
            StringBuilder sb3222 = new StringBuilder();
            sb3222.append("fetchUIColorConfig() called with appId = ");
            sb3222.append(str);
            sb3222.append(", cacehDir = ");
            sb3222.append(file);
            sb3222.append(", workflowId = ");
            sb3222.append(str2);
            sb3222.append(", uiColorConfigJson = ");
            str15 = str12;
            sb3222.append(str15);
            sb2 = sb3222.toString();
            if (sb2 == null) {
            }
            sb.append(sb2);
            sb.append(' ');
            sb.append("");
            hyperLogger.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
            }
            str16 = " - ";
            deferred = NetworkRepo.prefetchUIConfigDeferred;
            if (deferred == null) {
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope2 = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    callUIConfigFetchAPI = obj;
                    str3 = "Throwable().stackTrace";
                    str4 = "replaceAll(\"\")";
                    str5 = "";
                    str6 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str7 = " - ";
                    charSequence = "co.hyperverge";
                    str8 = "packageName";
                    str9 = "getInitialApplication";
                    str10 = null;
                    try {
                        hSUIConfig = (HSUIConfig) callUIConfigFetchAPI;
                        m1202constructorimpl2 = Result.m1202constructorimpl(hSUIConfig);
                    } catch (Throwable th16) {
                        th = th16;
                    }
                } catch (Throwable th17) {
                    th = th17;
                    str3 = "Throwable().stackTrace";
                    str4 = "replaceAll(\"\")";
                }
                CoroutineScope coroutineScope32222222 = coroutineScope2;
                Object obj42222222 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42222222);
                if (m1205exceptionOrNullimpl != null) {
                    FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setUiConfig$hyperkyc_release(str10);
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str3);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 != null && (className2 = stackTraceElement3.getClassName()) != null) {
                        r8 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, str10, 2, str10);
                    }
                    r8 = (coroutineScope32222222 == null || (cls4 = coroutineScope32222222.getClass()) == null) ? str10 : cls4.getCanonicalName();
                    if (r8 == 0) {
                        r8 = "N/A";
                    }
                    objectRef3.element = r8;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher4.find()) {
                        str20 = str5;
                        ?? replaceAll3 = matcher4.replaceAll(str20);
                        str19 = str4;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str19);
                        objectRef3.element = replaceAll3;
                    } else {
                        str19 = str4;
                        str20 = str5;
                    }
                    if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str21 = str6;
                        str22 = (String) objectRef3.element;
                    } else {
                        str22 = ((String) objectRef3.element).substring(0, 23);
                        str21 = str6;
                        Intrinsics.checkNotNullExpressionValue(str22, str21);
                    }
                    sb5.append(str22);
                    sb5.append(str7);
                    sb5.append("fetchUIConfig() failed");
                    sb5.append(' ');
                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str10;
                    if (localizedMessage2 != null) {
                        str23 = '\n' + localizedMessage2;
                    } else {
                        str23 = str20;
                    }
                    sb5.append(str23);
                    companion6.log(level2, sb5.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str9, new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, str27);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th18) {
                        Result.Companion companion8 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th18));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = str20;
                    }
                    String str33 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str33, str8);
                        if (StringsKt.contains$default((CharSequence) str33, charSequence, false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                                str24 = null;
                            } else {
                                str24 = null;
                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default2 != null) {
                                    canonicalName2 = substringAfterLast$default2;
                                    objectRef4.element = canonicalName2;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher2.find()) {
                                        ?? replaceAll4 = matcher2.replaceAll(str20);
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, str19);
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                        str25 = (String) objectRef4.element;
                                    } else {
                                        str25 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str25, str21);
                                    }
                                    StringBuilder sb6 = new StringBuilder();
                                    sb6.append("fetchUIConfig() failed");
                                    sb6.append(' ');
                                    localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str24;
                                    if (localizedMessage == null) {
                                        str26 = '\n' + localizedMessage;
                                    } else {
                                        str26 = str20;
                                    }
                                    sb6.append(str26);
                                    Log.println(6, str25, sb6.toString());
                                }
                            }
                            canonicalName2 = (coroutineScope32222222 == null || (cls3 = coroutineScope32222222.getClass()) == null) ? str24 : cls3.getCanonicalName();
                            if (canonicalName2 == 0) {
                                canonicalName2 = "N/A";
                            }
                            objectRef4.element = canonicalName2;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                            if (matcher2.find()) {
                            }
                            if (((String) objectRef4.element).length() > 23) {
                            }
                            str25 = (String) objectRef4.element;
                            StringBuilder sb62 = new StringBuilder();
                            sb62.append("fetchUIConfig() failed");
                            sb62.append(' ');
                            if (m1205exceptionOrNullimpl == null) {
                            }
                            if (localizedMessage == null) {
                            }
                            sb62.append(str26);
                            Log.println(6, str25, sb62.toString());
                        }
                    }
                }
                ResultKt.throwOnFailure(obj42222222);
                return obj42222222;
            }
            String str34 = (String) this.L$4;
            String str35 = (String) this.L$3;
            file = (File) this.L$2;
            String str36 = (String) this.L$1;
            CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                str11 = "Throwable().stackTrace";
                str16 = " - ";
                networkRepo$fetchUIColorConfig$2 = this;
                charSequence = "co.hyperverge";
                str8 = "packageName";
                str9 = "getInitialApplication";
                str15 = str34;
                await = obj;
                str2 = str35;
                obj2 = coroutine_suspended;
                str = str36;
                coroutineScope = coroutineScope5;
            } catch (Throwable th19) {
                th = th19;
                str3 = "Throwable().stackTrace";
                str4 = "replaceAll(\"\")";
                coroutineScope2 = coroutineScope5;
            }
            str5 = "";
            str6 = "this as java.lang.String…ing(startIndex, endIndex)";
            str7 = " - ";
            charSequence = "co.hyperverge";
            str8 = "packageName";
            str9 = "getInitialApplication";
            str10 = null;
            Result.Companion companion3222222 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            CoroutineScope coroutineScope322222222 = coroutineScope2;
            Object obj422222222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422222222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj422222222);
            return obj422222222;
        }
        hSUIConfig = (HSUIConfig) await;
        if (hSUIConfig != null) {
            coroutineScope2 = coroutineScope;
            str4 = "replaceAll(\"\")";
            str5 = "";
            str6 = "this as java.lang.String…ing(startIndex, endIndex)";
            str7 = str16;
            str3 = str11;
            str10 = null;
            m1202constructorimpl2 = Result.m1202constructorimpl(hSUIConfig);
            CoroutineScope coroutineScope3222222222 = coroutineScope2;
            Object obj4222222222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222222222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj4222222222);
            return obj4222222222;
        }
        File file22 = file;
        String str302 = str;
        String str312 = str15;
        CoroutineScope coroutineScope42 = coroutineScope;
        String str322 = str2;
        NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
        networkRepo$fetchUIColorConfig$2.L$0 = coroutineScope42;
        networkRepo$fetchUIColorConfig$2.L$1 = null;
        networkRepo$fetchUIColorConfig$2.L$2 = null;
        networkRepo$fetchUIColorConfig$2.L$3 = null;
        networkRepo$fetchUIColorConfig$2.L$4 = null;
        networkRepo$fetchUIColorConfig$2.label = 2;
        obj3 = obj2;
        str3 = str11;
        str10 = null;
        str4 = "replaceAll(\"\")";
        str5 = "";
        str6 = "this as java.lang.String…ing(startIndex, endIndex)";
        str7 = str16;
        callUIConfigFetchAPI = networkRepo2.callUIConfigFetchAPI(str302, file22, str322, (r16 & 8) != 0 ? null : null, (r16 & 16) != 0 ? null : str312, this);
        if (callUIConfigFetchAPI != obj3) {
        }
    }
}
