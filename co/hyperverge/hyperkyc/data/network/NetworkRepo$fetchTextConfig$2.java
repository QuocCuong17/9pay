package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00010\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$fetchTextConfig$2", f = "NetworkRepo.kt", i = {0, 1}, l = {467, 467}, m = "invokeSuspend", n = {"$this$onIO", "$this$onIO"}, s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$fetchTextConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ File $cacheDir;
    final /* synthetic */ String $languageCode;
    final /* synthetic */ String $source;
    final /* synthetic */ String $textConfigJson;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$fetchTextConfig$2(String str, String str2, String str3, File file, String str4, Continuation<? super NetworkRepo$fetchTextConfig$2> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$languageCode = str2;
        this.$source = str3;
        this.$cacheDir = file;
        this.$textConfigJson = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$fetchTextConfig$2 networkRepo$fetchTextConfig$2 = new NetworkRepo$fetchTextConfig$2(this.$appId, this.$languageCode, this.$source, this.$cacheDir, this.$textConfigJson, continuation);
        networkRepo$fetchTextConfig$2.L$0 = obj;
        return networkRepo$fetchTextConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
        return ((NetworkRepo$fetchTextConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:133|(11:134|135|136|137|138|139|140|141|142|143|144)|(3:274|275|(21:277|278|279|(16:281|153|154|(1:156)|157|158|(9:163|164|(1:166)|167|(1:169)(12:188|189|190|191|192|193|194|195|196|(1:198)|199|(10:201|202|203|204|205|206|(17:208|209|210|211|(2:213|(13:215|216|(9:218|219|(1:221)|222|(4:232|228|(1:230)|231)|227|228|(0)|231)|(1:240)(1:237)|(1:239)|219|(0)|222|(1:233)(5:224|232|228|(0)|231)|227|228|(0)|231))|242|(1:235)|240|(0)|219|(0)|222|(0)(0)|227|228|(0)|231)(1:245)|171|172|(4:174|175|176|(1:178)(1:179))(7:183|111|112|113|114|115|(1:117)(8:118|9|10|11|12|(0)|90|91)))(1:249))|170|171|172|(0)(0))|268|164|(0)|167|(0)(0)|170|171|172|(0)(0))|(1:273)(1:150)|(1:152)(1:272)|153|154|(0)|157|158|(10:160|163|164|(0)|167|(0)(0)|170|171|172|(0)(0))|268|164|(0)|167|(0)(0)|170|171|172|(0)(0)))|146|(1:148)|273|(0)(0)|153|154|(0)|157|158|(0)|268|164|(0)|167|(0)(0)|170|171|172|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(8:(1:(11:5|6|7|8|9|10|11|12|(19:14|(1:89)(1:18)|(1:26)(1:23)|(1:25)|27|(1:29)(1:88)|30|(1:87)(1:34)|35|(1:37)(1:86)|38|(1:40)(1:85)|41|42|43|44|(1:46)|47|(2:49|(12:51|(1:81)(1:55)|(1:63)(1:60)|(1:62)|64|(1:66)|67|(1:80)(1:71)|72|(1:74)(1:79)|(1:76)(1:78)|77)))|90|91)(2:101|102))(4:103|104|105|106)|99|100|95|12|(0)|90|91)(32:133|134|135|136|137|138|139|140|141|142|143|144|(3:274|275|(21:277|278|279|(16:281|153|154|(1:156)|157|158|(9:163|164|(1:166)|167|(1:169)(12:188|189|190|191|192|193|194|195|196|(1:198)|199|(10:201|202|203|204|205|206|(17:208|209|210|211|(2:213|(13:215|216|(9:218|219|(1:221)|222|(4:232|228|(1:230)|231)|227|228|(0)|231)|(1:240)(1:237)|(1:239)|219|(0)|222|(1:233)(5:224|232|228|(0)|231)|227|228|(0)|231))|242|(1:235)|240|(0)|219|(0)|222|(0)(0)|227|228|(0)|231)(1:245)|171|172|(4:174|175|176|(1:178)(1:179))(7:183|111|112|113|114|115|(1:117)(8:118|9|10|11|12|(0)|90|91)))(1:249))|170|171|172|(0)(0))|268|164|(0)|167|(0)(0)|170|171|172|(0)(0))|(1:273)(1:150)|(1:152)(1:272)|153|154|(0)|157|158|(10:160|163|164|(0)|167|(0)(0)|170|171|172|(0)(0))|268|164|(0)|167|(0)(0)|170|171|172|(0)(0)))|146|(1:148)|273|(0)(0)|153|154|(0)|157|158|(0)|268|164|(0)|167|(0)(0)|170|171|172|(0)(0))|107|108|(7:110|111|112|113|114|115|(0)(0))(6:125|11|12|(0)|90|91)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x036c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x03eb, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x03ec, code lost:
    
        r31 = "replaceAll(\"\")";
        r32 = "this as java.lang.String…ing(startIndex, endIndex)";
        r33 = r25;
        r13 = r27;
        r12 = r28;
        r25 = r14;
        r14 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x049a, code lost:
    
        if (r8 != 0) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0407, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x0408, code lost:
    
        r31 = "replaceAll(\"\")";
        r14 = "";
        r32 = "this as java.lang.String…ing(startIndex, endIndex)";
        r33 = " - ";
        r11 = "null cannot be cast to non-null type android.app.Application";
        r13 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x041f, code lost:
    
        r12 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x05b4, code lost:
    
        if (r4 != 0) goto L247;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x014d A[Catch: all -> 0x0124, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x0124, blocks: (B:279:0x00fa, B:156:0x014d, B:160:0x0162, B:163:0x0169, B:148:0x0119, B:150:0x011f), top: B:278:0x00fa }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0162 A[Catch: all -> 0x0124, TRY_ENTER, TryCatch #4 {all -> 0x0124, blocks: (B:279:0x00fa, B:156:0x014d, B:160:0x0162, B:163:0x0169, B:148:0x0119, B:150:0x011f), top: B:278:0x00fa }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x01d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0291 A[Catch: all -> 0x037b, TryCatch #19 {all -> 0x037b, blocks: (B:175:0x0335, B:206:0x0231, B:208:0x0237, B:216:0x0260, B:219:0x027d, B:221:0x0291, B:222:0x029a, B:224:0x02a6, B:227:0x02be, B:228:0x02c2, B:231:0x02f2, B:232:0x02ae, B:235:0x026d, B:237:0x0273), top: B:205:0x0231 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02a6 A[Catch: all -> 0x037b, TryCatch #19 {all -> 0x037b, blocks: (B:175:0x0335, B:206:0x0231, B:208:0x0237, B:216:0x0260, B:219:0x027d, B:221:0x0291, B:222:0x029a, B:224:0x02a6, B:227:0x02be, B:228:0x02c2, B:231:0x02f2, B:232:0x02ae, B:235:0x026d, B:237:0x0273), top: B:205:0x0231 }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02bb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0138  */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v7, types: [T] */
    /* JADX WARN: Type inference failed for: r2v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v30, types: [T] */
    /* JADX WARN: Type inference failed for: r3v42, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v44, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v54, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v66 */
    /* JADX WARN: Type inference failed for: r4v26, types: [T] */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r4v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v47 */
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
        File file;
        CharSequence charSequence;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        CoroutineScope coroutineScope;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        Ref.ObjectRef objectRef;
        String str9;
        StackTraceElement stackTraceElement;
        String className;
        String str10;
        HyperLogger hyperLogger;
        String str11;
        String substringAfterLast$default;
        String str12;
        Matcher matcher;
        String str13;
        String sb2;
        String str14;
        Object m1202constructorimpl;
        String str15;
        String str16;
        ?? canonicalName;
        Class<?> cls;
        Matcher matcher2;
        String str17;
        String str18;
        Deferred deferred;
        NetworkRepo$fetchTextConfig$2 networkRepo$fetchTextConfig$2;
        Object obj2;
        String str19;
        String str20;
        CoroutineScope coroutineScope2;
        String str21;
        String str22;
        Object await;
        String str23;
        String str24;
        Class<?> cls2;
        CoroutineScope coroutineScope3;
        Object obj3;
        Object callTextConfigFetchAPI;
        Map map;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        ?? r8;
        String str25;
        String str26;
        String str27;
        String str28;
        Object m1202constructorimpl3;
        String str29;
        ?? r4;
        String str30;
        String str31;
        Class<?> cls3;
        String className2;
        Class<?> cls4;
        String className3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            String str32 = this.$appId;
            String str33 = this.$languageCode;
            String str34 = this.$source;
            file = this.$cacheDir;
            charSequence = "co.hyperverge";
            String str35 = this.$textConfigJson;
            try {
                Result.Companion companion2 = Result.INSTANCE;
                level = HyperLogger.Level.DEBUG;
                companion = HyperLogger.INSTANCE.getInstance();
                str7 = "packageName";
                try {
                    sb = new StringBuilder();
                    try {
                        objectRef = new Ref.ObjectRef();
                        str9 = "getInitialApplication";
                        try {
                            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        } catch (Throwable th) {
                            th = th;
                            str8 = "Throwable().stackTrace";
                            str = "replaceAll(\"\")";
                            str2 = "";
                            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                            str4 = " - ";
                            str6 = "null cannot be cast to non-null type android.app.Application";
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        str8 = "Throwable().stackTrace";
                        str = "replaceAll(\"\")";
                        str2 = "";
                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str4 = " - ";
                        str5 = "getInitialApplication";
                        str6 = "null cannot be cast to non-null type android.app.Application";
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str = "replaceAll(\"\")";
                    str2 = "";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str4 = " - ";
                    str5 = "getInitialApplication";
                    str6 = "null cannot be cast to non-null type android.app.Application";
                    str8 = "Throwable().stackTrace";
                    coroutineScope = coroutineScope4;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    CoroutineScope coroutineScope5 = coroutineScope;
                    Object obj4 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    ResultKt.throwOnFailure(obj4);
                    return obj4;
                }
            } catch (Throwable th4) {
                th = th4;
                str = "replaceAll(\"\")";
                str2 = "";
                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                str4 = " - ";
                str5 = "getInitialApplication";
                str6 = "null cannot be cast to non-null type android.app.Application";
                str7 = "packageName";
            }
            if (stackTraceElement != null) {
                try {
                    className = stackTraceElement.getClassName();
                } catch (Throwable th5) {
                    th = th5;
                    str8 = "Throwable().stackTrace";
                    coroutineScope = coroutineScope4;
                    str = "replaceAll(\"\")";
                    str2 = "";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str4 = " - ";
                    str6 = "null cannot be cast to non-null type android.app.Application";
                    str5 = str9;
                    Result.Companion companion32 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    CoroutineScope coroutineScope52 = coroutineScope;
                    Object obj42 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    ResultKt.throwOnFailure(obj42);
                    return obj42;
                }
                if (className != null) {
                    str10 = "Throwable().stackTrace";
                    hyperLogger = companion;
                    str11 = str35;
                    try {
                        substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    } catch (Throwable th6) {
                        th = th6;
                        coroutineScope = coroutineScope4;
                        str = "replaceAll(\"\")";
                        str2 = "";
                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str4 = " - ";
                        str6 = "null cannot be cast to non-null type android.app.Application";
                        str8 = str10;
                        str5 = str9;
                        Result.Companion companion322 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        CoroutineScope coroutineScope522 = coroutineScope;
                        Object obj422 = m1202constructorimpl2;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        ResultKt.throwOnFailure(obj422);
                        return obj422;
                    }
                    if (substringAfterLast$default != null) {
                        str12 = substringAfterLast$default;
                        objectRef.element = str12;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                        if (matcher.find()) {
                            ?? replaceAll = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                            objectRef.element = replaceAll;
                        }
                        if (((String) objectRef.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str13 = ((String) objectRef.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                            sb.append(str13);
                            sb.append(" - ");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("fetchTextConfig() called with: languageCode = ");
                            sb3.append(str33);
                            sb3.append(", source = ");
                            sb3.append(str34);
                            sb3.append(", cacheDir = ");
                            sb3.append(file);
                            sb3.append(", textConfigJson = ");
                            String str36 = str11;
                            sb3.append(str36);
                            sb2 = sb3.toString();
                            if (sb2 == null) {
                                sb2 = "null ";
                            }
                            sb.append(sb2);
                            sb.append(' ');
                            sb.append("");
                            hyperLogger.log(level, sb.toString());
                            if (CoreExtsKt.isRelease()) {
                                str15 = str7;
                                str6 = "null cannot be cast to non-null type android.app.Application";
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    str14 = str9;
                                    try {
                                        Object invoke = Class.forName("android.app.AppGlobals").getMethod(str14, new Class[0]).invoke(null, new Object[0]);
                                        str6 = "null cannot be cast to non-null type android.app.Application";
                                        try {
                                            Intrinsics.checkNotNull(invoke, str6);
                                            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                        } catch (Throwable th7) {
                                            th = th7;
                                            try {
                                                Result.Companion companion5 = Result.INSTANCE;
                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                                }
                                                String str37 = (String) m1202constructorimpl;
                                                if (CoreExtsKt.isDebug()) {
                                                }
                                            } catch (Throwable th8) {
                                                th = th8;
                                                str = "replaceAll(\"\")";
                                                str2 = "";
                                                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                str4 = " - ";
                                                str5 = str14;
                                                str8 = str10;
                                                coroutineScope = coroutineScope4;
                                                Result.Companion companion3222 = Result.INSTANCE;
                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                CoroutineScope coroutineScope5222 = coroutineScope;
                                                Object obj4222 = m1202constructorimpl2;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                ResultKt.throwOnFailure(obj4222);
                                                return obj4222;
                                            }
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        str6 = "null cannot be cast to non-null type android.app.Application";
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    str6 = "null cannot be cast to non-null type android.app.Application";
                                    str14 = str9;
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                    m1202constructorimpl = "";
                                }
                                String str372 = (String) m1202constructorimpl;
                                if (CoreExtsKt.isDebug()) {
                                    str9 = str14;
                                    str15 = str7;
                                } else {
                                    str15 = str7;
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(str372, str15);
                                        str16 = " - ";
                                        str9 = str14;
                                    } catch (Throwable th11) {
                                        th = th11;
                                        coroutineScope = coroutineScope4;
                                        str = "replaceAll(\"\")";
                                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                        str4 = " - ";
                                        str5 = str14;
                                        str7 = str15;
                                        str8 = str10;
                                    }
                                    try {
                                        if (StringsKt.contains$default((CharSequence) str372, charSequence, false, 2, (Object) null)) {
                                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                            String str38 = str10;
                                            try {
                                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str38);
                                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                                if (stackTraceElement2 != null) {
                                                    String className4 = stackTraceElement2.getClassName();
                                                    if (className4 != null) {
                                                        str10 = str38;
                                                        canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                        if (canonicalName != 0) {
                                                            objectRef2.element = canonicalName;
                                                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                            if (matcher2.find()) {
                                                                ?? replaceAll2 = matcher2.replaceAll("");
                                                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                                                objectRef2.element = replaceAll2;
                                                            }
                                                            if (((String) objectRef2.element).length() <= 23 && Build.VERSION.SDK_INT < 26) {
                                                                str17 = ((String) objectRef2.element).substring(0, 23);
                                                                Intrinsics.checkNotNullExpressionValue(str17, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                StringBuilder sb4 = new StringBuilder();
                                                                str18 = "fetchTextConfig() called with: languageCode = " + str33 + ", source = " + str34 + ", cacheDir = " + file + ", textConfigJson = " + str36;
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
                                                            str18 = "fetchTextConfig() called with: languageCode = " + str33 + ", source = " + str34 + ", cacheDir = " + file + ", textConfigJson = " + str36;
                                                            if (str18 == null) {
                                                            }
                                                            sb42.append(str18);
                                                            sb42.append(' ');
                                                            sb42.append("");
                                                            Log.println(3, str17, sb42.toString());
                                                        }
                                                        canonicalName = (coroutineScope4 != null || (cls = coroutineScope4.getClass()) == null) ? 0 : cls.getCanonicalName();
                                                        if (canonicalName == 0) {
                                                            canonicalName = "N/A";
                                                        }
                                                        objectRef2.element = canonicalName;
                                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                        if (matcher2.find()) {
                                                        }
                                                        if (((String) objectRef2.element).length() <= 23) {
                                                            str17 = ((String) objectRef2.element).substring(0, 23);
                                                            Intrinsics.checkNotNullExpressionValue(str17, "this as java.lang.String…ing(startIndex, endIndex)");
                                                            StringBuilder sb422 = new StringBuilder();
                                                            str18 = "fetchTextConfig() called with: languageCode = " + str33 + ", source = " + str34 + ", cacheDir = " + file + ", textConfigJson = " + str36;
                                                            if (str18 == null) {
                                                            }
                                                            sb422.append(str18);
                                                            sb422.append(' ');
                                                            sb422.append("");
                                                            Log.println(3, str17, sb422.toString());
                                                        }
                                                        str17 = (String) objectRef2.element;
                                                        StringBuilder sb4222 = new StringBuilder();
                                                        str18 = "fetchTextConfig() called with: languageCode = " + str33 + ", source = " + str34 + ", cacheDir = " + file + ", textConfigJson = " + str36;
                                                        if (str18 == null) {
                                                        }
                                                        sb4222.append(str18);
                                                        sb4222.append(' ');
                                                        sb4222.append("");
                                                        Log.println(3, str17, sb4222.toString());
                                                    }
                                                }
                                                str10 = str38;
                                                if (coroutineScope4 != null) {
                                                }
                                                if (canonicalName == 0) {
                                                }
                                                objectRef2.element = canonicalName;
                                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                if (matcher2.find()) {
                                                }
                                                if (((String) objectRef2.element).length() <= 23) {
                                                }
                                                str17 = (String) objectRef2.element;
                                                StringBuilder sb42222 = new StringBuilder();
                                                str18 = "fetchTextConfig() called with: languageCode = " + str33 + ", source = " + str34 + ", cacheDir = " + file + ", textConfigJson = " + str36;
                                                if (str18 == null) {
                                                }
                                                sb42222.append(str18);
                                                sb42222.append(' ');
                                                sb42222.append("");
                                                Log.println(3, str17, sb42222.toString());
                                            } catch (Throwable th12) {
                                                th = th12;
                                                coroutineScope = coroutineScope4;
                                                str = "replaceAll(\"\")";
                                                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                str8 = str38;
                                                str4 = str16;
                                                str5 = str9;
                                                str7 = str15;
                                                str2 = "";
                                                Result.Companion companion32222 = Result.INSTANCE;
                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                CoroutineScope coroutineScope52222 = coroutineScope;
                                                Object obj42222 = m1202constructorimpl2;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                ResultKt.throwOnFailure(obj42222);
                                                return obj42222;
                                            }
                                        }
                                        deferred = NetworkRepo.prefetchTextConfigDeferred;
                                    } catch (Throwable th13) {
                                        th = th13;
                                        coroutineScope = coroutineScope4;
                                        str = "replaceAll(\"\")";
                                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                        str4 = str16;
                                        str8 = str10;
                                        str5 = str9;
                                        str7 = str15;
                                        str2 = "";
                                        Result.Companion companion322222 = Result.INSTANCE;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        CoroutineScope coroutineScope522222 = coroutineScope;
                                        Object obj422222 = m1202constructorimpl2;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422222);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        ResultKt.throwOnFailure(obj422222);
                                        return obj422222;
                                    }
                                    if (deferred != null) {
                                        networkRepo$fetchTextConfig$2 = this;
                                        obj2 = coroutine_suspended;
                                        str19 = str36;
                                        str20 = str34;
                                        coroutineScope2 = coroutineScope4;
                                        str21 = str33;
                                        str22 = str32;
                                        try {
                                            NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                                            networkRepo$fetchTextConfig$2.L$0 = coroutineScope2;
                                            networkRepo$fetchTextConfig$2.L$1 = null;
                                            networkRepo$fetchTextConfig$2.L$2 = null;
                                            networkRepo$fetchTextConfig$2.L$3 = null;
                                            networkRepo$fetchTextConfig$2.L$4 = null;
                                            networkRepo$fetchTextConfig$2.L$5 = null;
                                            networkRepo$fetchTextConfig$2.label = 2;
                                            str8 = str10;
                                            str = "replaceAll(\"\")";
                                            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                            obj3 = obj2;
                                            str4 = str16;
                                            coroutineScope3 = coroutineScope2;
                                            str7 = str15;
                                            str5 = str9;
                                            str2 = "";
                                            try {
                                                callTextConfigFetchAPI = networkRepo.callTextConfigFetchAPI(str22, str21, str20, file, (r18 & 16) != 0 ? null : null, (r18 & 32) != 0 ? null : str19, this);
                                            } catch (Throwable th14) {
                                                th = th14;
                                                coroutineScope = coroutineScope3;
                                                Result.Companion companion3222222 = Result.INSTANCE;
                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                CoroutineScope coroutineScope5222222 = coroutineScope;
                                                Object obj4222222 = m1202constructorimpl2;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                ResultKt.throwOnFailure(obj4222222);
                                                return obj4222222;
                                            }
                                        } catch (Throwable th15) {
                                            th = th15;
                                            str = "replaceAll(\"\")";
                                            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                            coroutineScope3 = coroutineScope2;
                                            str4 = str16;
                                            str8 = str10;
                                            str5 = str9;
                                            str7 = str15;
                                            str2 = "";
                                        }
                                        if (callTextConfigFetchAPI != obj3) {
                                            return obj3;
                                        }
                                        coroutineScope = coroutineScope3;
                                        map = (Map) callTextConfigFetchAPI;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(map);
                                        CoroutineScope coroutineScope52222222 = coroutineScope;
                                        Object obj42222222 = m1202constructorimpl2;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42222222);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        ResultKt.throwOnFailure(obj42222222);
                                        return obj42222222;
                                    }
                                    networkRepo$fetchTextConfig$2 = this;
                                    networkRepo$fetchTextConfig$2.L$0 = coroutineScope4;
                                    networkRepo$fetchTextConfig$2.L$1 = str32;
                                    networkRepo$fetchTextConfig$2.L$2 = str33;
                                    networkRepo$fetchTextConfig$2.L$3 = str34;
                                    networkRepo$fetchTextConfig$2.L$4 = file;
                                    networkRepo$fetchTextConfig$2.L$5 = str36;
                                    networkRepo$fetchTextConfig$2.label = 1;
                                    await = deferred.await(networkRepo$fetchTextConfig$2);
                                    obj2 = coroutine_suspended;
                                    if (await == obj2) {
                                        return obj2;
                                    }
                                    str19 = str36;
                                    str23 = str32;
                                    coroutineScope = coroutineScope4;
                                    str21 = str33;
                                    str24 = str34;
                                }
                            }
                            str16 = " - ";
                            deferred = NetworkRepo.prefetchTextConfigDeferred;
                            if (deferred != null) {
                            }
                        }
                        str13 = (String) objectRef.element;
                        sb.append(str13);
                        sb.append(" - ");
                        StringBuilder sb32 = new StringBuilder();
                        sb32.append("fetchTextConfig() called with: languageCode = ");
                        sb32.append(str33);
                        sb32.append(", source = ");
                        sb32.append(str34);
                        sb32.append(", cacheDir = ");
                        sb32.append(file);
                        sb32.append(", textConfigJson = ");
                        String str362 = str11;
                        sb32.append(str362);
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
                        deferred = NetworkRepo.prefetchTextConfigDeferred;
                        if (deferred != null) {
                        }
                    }
                    String canonicalName2 = (coroutineScope4 != null || (cls2 = coroutineScope4.getClass()) == null) ? null : cls2.getCanonicalName();
                    str12 = canonicalName2 != null ? "N/A" : canonicalName2;
                    objectRef.element = str12;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef.element).length() > 23) {
                        str13 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(str13);
                        sb.append(" - ");
                        StringBuilder sb322 = new StringBuilder();
                        sb322.append("fetchTextConfig() called with: languageCode = ");
                        sb322.append(str33);
                        sb322.append(", source = ");
                        sb322.append(str34);
                        sb322.append(", cacheDir = ");
                        sb322.append(file);
                        sb322.append(", textConfigJson = ");
                        String str3622 = str11;
                        sb322.append(str3622);
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
                        deferred = NetworkRepo.prefetchTextConfigDeferred;
                        if (deferred != null) {
                        }
                    }
                    str13 = (String) objectRef.element;
                    sb.append(str13);
                    sb.append(" - ");
                    StringBuilder sb3222 = new StringBuilder();
                    sb3222.append("fetchTextConfig() called with: languageCode = ");
                    sb3222.append(str33);
                    sb3222.append(", source = ");
                    sb3222.append(str34);
                    sb3222.append(", cacheDir = ");
                    sb3222.append(file);
                    sb3222.append(", textConfigJson = ");
                    String str36222 = str11;
                    sb3222.append(str36222);
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
                    deferred = NetworkRepo.prefetchTextConfigDeferred;
                    if (deferred != null) {
                    }
                }
            }
            str10 = "Throwable().stackTrace";
            hyperLogger = companion;
            str11 = str35;
            if (coroutineScope4 != null) {
            }
            if (canonicalName2 != null) {
            }
            objectRef.element = str12;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
            }
            if (((String) objectRef.element).length() > 23) {
            }
            str13 = (String) objectRef.element;
            sb.append(str13);
            sb.append(" - ");
            StringBuilder sb32222 = new StringBuilder();
            sb32222.append("fetchTextConfig() called with: languageCode = ");
            sb32222.append(str33);
            sb32222.append(", source = ");
            sb32222.append(str34);
            sb32222.append(", cacheDir = ");
            sb32222.append(file);
            sb32222.append(", textConfigJson = ");
            String str362222 = str11;
            sb32222.append(str362222);
            sb2 = sb32222.toString();
            if (sb2 == null) {
            }
            sb.append(sb2);
            sb.append(' ');
            sb.append("");
            hyperLogger.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
            }
            str16 = " - ";
            deferred = NetworkRepo.prefetchTextConfigDeferred;
            if (deferred != null) {
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    callTextConfigFetchAPI = obj;
                    str = "replaceAll(\"\")";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str4 = " - ";
                    str5 = "getInitialApplication";
                    str6 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str7 = "packageName";
                    str8 = "Throwable().stackTrace";
                    str2 = "";
                    try {
                        map = (Map) callTextConfigFetchAPI;
                        m1202constructorimpl2 = Result.m1202constructorimpl(map);
                    } catch (Throwable th16) {
                        th = th16;
                    }
                } catch (Throwable th17) {
                    th = th17;
                    str = "replaceAll(\"\")";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str4 = " - ";
                    str5 = "getInitialApplication";
                    str6 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str7 = "packageName";
                }
                CoroutineScope coroutineScope522222222 = coroutineScope;
                Object obj422222222 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj422222222);
                if (m1205exceptionOrNullimpl != null) {
                    FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setTextConfig$hyperkyc_release(null);
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str8);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 != null && (className3 = stackTraceElement3.getClassName()) != null) {
                        r8 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r8 = (coroutineScope522222222 == null || (cls4 = coroutineScope522222222.getClass()) == null) ? 0 : cls4.getCanonicalName();
                    if (r8 == 0) {
                        r8 = "N/A";
                    }
                    objectRef3.element = r8;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher3.find()) {
                        ?? replaceAll3 = matcher3.replaceAll(str2);
                        str25 = str;
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str25);
                        objectRef3.element = replaceAll3;
                    } else {
                        str25 = str;
                    }
                    if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str26 = str3;
                        str27 = (String) objectRef3.element;
                    } else {
                        str27 = ((String) objectRef3.element).substring(0, 23);
                        str26 = str3;
                        Intrinsics.checkNotNullExpressionValue(str27, str26);
                    }
                    sb5.append(str27);
                    sb5.append(str4);
                    sb5.append("fetchTextConfig() failed");
                    sb5.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str28 = '\n' + localizedMessage;
                    } else {
                        str28 = str2;
                    }
                    sb5.append(str28);
                    companion6.log(level2, sb5.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, str6);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th18) {
                        Result.Companion companion8 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th18));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = str2;
                    }
                    String str39 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str39, str7);
                        if (StringsKt.contains$default((CharSequence) str39, charSequence, false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str8);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str29 = null;
                            } else {
                                str29 = null;
                                r4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            r4 = (coroutineScope522222222 == null || (cls3 = coroutineScope522222222.getClass()) == null) ? str29 : cls3.getCanonicalName();
                            if (r4 == 0) {
                                r4 = "N/A";
                            }
                            objectRef4.element = r4;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                            if (matcher4.find()) {
                                ?? replaceAll4 = matcher4.replaceAll(str2);
                                Intrinsics.checkNotNullExpressionValue(replaceAll4, str25);
                                objectRef4.element = replaceAll4;
                            }
                            if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str30 = (String) objectRef4.element;
                            } else {
                                str30 = ((String) objectRef4.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str30, str26);
                            }
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append("fetchTextConfig() failed");
                            sb6.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str29;
                            if (localizedMessage2 != null) {
                                str31 = '\n' + localizedMessage2;
                            } else {
                                str31 = str2;
                            }
                            sb6.append(str31);
                            Log.println(6, str30, sb6.toString());
                        }
                    }
                }
                ResultKt.throwOnFailure(obj422222222);
                return obj422222222;
            }
            String str40 = (String) this.L$5;
            File file2 = (File) this.L$4;
            str24 = (String) this.L$3;
            str21 = (String) this.L$2;
            String str41 = (String) this.L$1;
            str19 = str40;
            CoroutineScope coroutineScope6 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                await = obj;
                str10 = "Throwable().stackTrace";
                str16 = " - ";
                str9 = "getInitialApplication";
                networkRepo$fetchTextConfig$2 = this;
                obj2 = coroutine_suspended;
                str6 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str15 = "packageName";
                str23 = str41;
                file = file2;
                coroutineScope = coroutineScope6;
            } catch (Throwable th19) {
                th = th19;
                str = "replaceAll(\"\")";
                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                str4 = " - ";
                str5 = "getInitialApplication";
                str6 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str7 = "packageName";
                coroutineScope = coroutineScope6;
            }
            str8 = "Throwable().stackTrace";
            str2 = "";
            Result.Companion companion32222222 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            CoroutineScope coroutineScope5222222222 = coroutineScope;
            Object obj4222222222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4222222222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj4222222222);
            return obj4222222222;
        }
        map = (Map) await;
        if (map != null) {
            str = "replaceAll(\"\")";
            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
            str4 = str16;
            str8 = str10;
            str5 = str9;
            str7 = str15;
            str2 = "";
            m1202constructorimpl2 = Result.m1202constructorimpl(map);
            CoroutineScope coroutineScope52222222222 = coroutineScope;
            Object obj42222222222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42222222222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj42222222222);
            return obj42222222222;
        }
        coroutineScope2 = coroutineScope;
        str20 = str24;
        str22 = str23;
        NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
        networkRepo$fetchTextConfig$2.L$0 = coroutineScope2;
        networkRepo$fetchTextConfig$2.L$1 = null;
        networkRepo$fetchTextConfig$2.L$2 = null;
        networkRepo$fetchTextConfig$2.L$3 = null;
        networkRepo$fetchTextConfig$2.L$4 = null;
        networkRepo$fetchTextConfig$2.L$5 = null;
        networkRepo$fetchTextConfig$2.label = 2;
        str8 = str10;
        str = "replaceAll(\"\")";
        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
        obj3 = obj2;
        str4 = str16;
        coroutineScope3 = coroutineScope2;
        str7 = str15;
        str5 = str9;
        str2 = "";
        callTextConfigFetchAPI = networkRepo2.callTextConfigFetchAPI(str22, str21, str20, file, (r18 & 16) != 0 ? null : null, (r18 & 32) != 0 ? null : str19, this);
        if (callTextConfigFetchAPI != obj3) {
        }
    }
}
