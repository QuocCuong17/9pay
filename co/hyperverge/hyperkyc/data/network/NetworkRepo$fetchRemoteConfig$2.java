package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$fetchRemoteConfig$2", f = "NetworkRepo.kt", i = {0, 1}, l = {251, 251}, m = "invokeSuspend", n = {"$this$onIO", "$this$onIO"}, s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$fetchRemoteConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSRemoteConfig>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ File $cacheDir;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$fetchRemoteConfig$2(String str, File file, Continuation<? super NetworkRepo$fetchRemoteConfig$2> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$cacheDir = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$fetchRemoteConfig$2 networkRepo$fetchRemoteConfig$2 = new NetworkRepo$fetchRemoteConfig$2(this.$appId, this.$cacheDir, continuation);
        networkRepo$fetchRemoteConfig$2.L$0 = obj;
        return networkRepo$fetchRemoteConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSRemoteConfig> continuation) {
        return ((NetworkRepo$fetchRemoteConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(7:(2:4|(11:6|7|8|9|10|11|12|13|(20:15|(1:93)(1:19)|(1:27)(1:24)|(1:26)|28|(1:30)|31|(12:35|36|(1:38)(1:90)|39|(1:41)(1:89)|42|43|44|45|(1:47)|48|(2:50|(12:52|(1:56)|(1:82)(1:78)|(1:80)(1:81)|58|(1:60)|61|(1:74)(1:65)|66|(1:68)(1:73)|(1:70)(1:72)|71)))|91|36|(0)(0)|39|(0)(0)|42|43|44|45|(0)|48|(0))(1:94)|83|84)(2:101|102))(4:103|104|105|106)|100|97|13|(0)(0)|83|84)(32:131|132|133|134|135|136|137|138|139|140|141|142|(3:259|260|(21:262|263|264|(16:266|151|152|(1:154)|155|156|(9:161|162|(1:164)|165|(1:167)(13:182|183|184|185|186|187|188|189|190|(1:192)|193|194|(3:196|197|(20:199|200|201|202|(2:204|(16:206|207|(12:209|210|(1:212)|213|(7:223|219|(1:221)|222|169|170|(3:172|173|(1:175))(8:176|109|110|111|112|113|114|(1:116)(8:117|10|11|12|13|(0)(0)|83|84)))|218|219|(0)|222|169|170|(0)(0))|(1:232)(1:228)|(1:230)(1:231)|210|(0)|213|(1:224)(8:215|223|219|(0)|222|169|170|(0)(0))|218|219|(0)|222|169|170|(0)(0)))|234|(1:226)|232|(0)(0)|210|(0)|213|(0)(0)|218|219|(0)|222|169|170|(0)(0))))|168|169|170|(0)(0))|254|162|(0)|165|(0)(0)|168|169|170|(0)(0))|(1:258)(1:148)|(1:150)(1:257)|151|152|(0)|155|156|(10:158|161|162|(0)|165|(0)(0)|168|169|170|(0)(0))|254|162|(0)|165|(0)(0)|168|169|170|(0)(0)))|144|(1:146)|258|(0)(0)|151|152|(0)|155|156|(0)|254|162|(0)|165|(0)(0)|168|169|170|(0)(0))|107|(6:124|12|13|(0)(0)|83|84)|109|110|111|112|113|114|(0)(0)|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(20:15|(1:93)(1:19)|(1:27)(1:24)|(1:26)|28|(1:30)|31|(12:35|36|(1:38)(1:90)|39|(1:41)(1:89)|42|43|44|45|(1:47)|48|(2:50|(12:52|(1:56)|(1:82)(1:78)|(1:80)(1:81)|58|(1:60)|61|(1:74)(1:65)|66|(1:68)(1:73)|(1:70)(1:72)|71)))|91|36|(0)(0)|39|(0)(0)|42|43|44|45|(0)|48|(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:131|(9:132|133|134|135|136|137|138|139|140)|(2:141|142)|(3:259|260|(21:262|263|264|(16:266|151|152|(1:154)|155|156|(9:161|162|(1:164)|165|(1:167)(13:182|183|184|185|186|187|188|189|190|(1:192)|193|194|(3:196|197|(20:199|200|201|202|(2:204|(16:206|207|(12:209|210|(1:212)|213|(7:223|219|(1:221)|222|169|170|(3:172|173|(1:175))(8:176|109|110|111|112|113|114|(1:116)(8:117|10|11|12|13|(0)(0)|83|84)))|218|219|(0)|222|169|170|(0)(0))|(1:232)(1:228)|(1:230)(1:231)|210|(0)|213|(1:224)(8:215|223|219|(0)|222|169|170|(0)(0))|218|219|(0)|222|169|170|(0)(0)))|234|(1:226)|232|(0)(0)|210|(0)|213|(0)(0)|218|219|(0)|222|169|170|(0)(0))))|168|169|170|(0)(0))|254|162|(0)|165|(0)(0)|168|169|170|(0)(0))|(1:258)(1:148)|(1:150)(1:257)|151|152|(0)|155|156|(10:158|161|162|(0)|165|(0)(0)|168|169|170|(0)(0))|254|162|(0)|165|(0)(0)|168|169|170|(0)(0)))|144|(1:146)|258|(0)(0)|151|152|(0)|155|156|(0)|254|162|(0)|165|(0)(0)|168|169|170|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(32:131|132|133|134|135|136|137|138|139|140|141|142|(3:259|260|(21:262|263|264|(16:266|151|152|(1:154)|155|156|(9:161|162|(1:164)|165|(1:167)(13:182|183|184|185|186|187|188|189|190|(1:192)|193|194|(3:196|197|(20:199|200|201|202|(2:204|(16:206|207|(12:209|210|(1:212)|213|(7:223|219|(1:221)|222|169|170|(3:172|173|(1:175))(8:176|109|110|111|112|113|114|(1:116)(8:117|10|11|12|13|(0)(0)|83|84)))|218|219|(0)|222|169|170|(0)(0))|(1:232)(1:228)|(1:230)(1:231)|210|(0)|213|(1:224)(8:215|223|219|(0)|222|169|170|(0)(0))|218|219|(0)|222|169|170|(0)(0)))|234|(1:226)|232|(0)(0)|210|(0)|213|(0)(0)|218|219|(0)|222|169|170|(0)(0))))|168|169|170|(0)(0))|254|162|(0)|165|(0)(0)|168|169|170|(0)(0))|(1:258)(1:148)|(1:150)(1:257)|151|152|(0)|155|156|(10:158|161|162|(0)|165|(0)(0)|168|169|170|(0)(0))|254|162|(0)|165|(0)(0)|168|169|170|(0)(0)))|144|(1:146)|258|(0)(0)|151|152|(0)|155|156|(0)|254|162|(0)|165|(0)(0)|168|169|170|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x032f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0339, code lost:
    
        r1 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0331, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0332, code lost:
    
        r13 = "";
        r14 = "replaceAll(\"\")";
        r17 = r9;
        r27 = r23;
        r9 = "this as java.lang.String…ing(startIndex, endIndex)";
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x033d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x033e, code lost:
    
        r13 = "";
        r14 = "replaceAll(\"\")";
        r9 = "this as java.lang.String…ing(startIndex, endIndex)";
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x03d4, code lost:
    
        if (r12 != 0) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x034c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x034d, code lost:
    
        r13 = "";
        r14 = "replaceAll(\"\")";
        r9 = "this as java.lang.String…ing(startIndex, endIndex)";
        r11 = "packageName";
        r27 = r23;
        r8 = "null cannot be cast to non-null type android.app.Application";
        r10 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x04e8, code lost:
    
        if (r4 != null) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0491, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0492, code lost:
    
        r4 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0321 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0122 A[Catch: all -> 0x00f8, TRY_ENTER, TRY_LEAVE, TryCatch #15 {all -> 0x00f8, blocks: (B:264:0x00ce, B:154:0x0122, B:158:0x0137, B:161:0x013e, B:146:0x00ed, B:148:0x00f3), top: B:263:0x00ce }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0137 A[Catch: all -> 0x00f8, TRY_ENTER, TryCatch #15 {all -> 0x00f8, blocks: (B:264:0x00ce, B:154:0x0122, B:158:0x0137, B:161:0x013e, B:146:0x00ed, B:148:0x00f3), top: B:263:0x00ce }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0196 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x01e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0250 A[Catch: all -> 0x02e7, TryCatch #4 {all -> 0x02e7, blocks: (B:107:0x02d8, B:173:0x02c6, B:197:0x01e2, B:199:0x01f4, B:207:0x021d, B:210:0x023c, B:212:0x0250, B:213:0x0259, B:215:0x0265, B:218:0x027d, B:219:0x0281, B:222:0x02a1, B:223:0x026d, B:226:0x022a, B:228:0x0230), top: B:196:0x01e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0265 A[Catch: all -> 0x02e7, TryCatch #4 {all -> 0x02e7, blocks: (B:107:0x02d8, B:173:0x02c6, B:197:0x01e2, B:199:0x01f4, B:207:0x021d, B:210:0x023c, B:212:0x0250, B:213:0x0259, B:215:0x0265, B:218:0x027d, B:219:0x0281, B:222:0x02a1, B:223:0x026d, B:226:0x022a, B:228:0x0230), top: B:196:0x01e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x027a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0572  */
    /* JADX WARN: Type inference failed for: r10v14, types: [T] */
    /* JADX WARN: Type inference failed for: r10v30 */
    /* JADX WARN: Type inference failed for: r10v31 */
    /* JADX WARN: Type inference failed for: r10v43 */
    /* JADX WARN: Type inference failed for: r12v21, types: [T] */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v31, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v32 */
    /* JADX WARN: Type inference failed for: r1v42, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21, types: [T] */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v28, types: [T] */
    /* JADX WARN: Type inference failed for: r6v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v52, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v64 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        File file;
        String str2;
        String str3;
        String str4;
        CharSequence charSequence;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        CoroutineScope coroutineScope2;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        String className;
        String str10;
        String str11;
        String substringAfterLast$default;
        String str12;
        Matcher matcher;
        String str13;
        String str14;
        Object m1202constructorimpl;
        StackTraceElement stackTraceElement2;
        char c;
        String str15;
        Class<?> cls;
        Matcher matcher2;
        String str16;
        String str17;
        Deferred deferred;
        NetworkRepo$fetchRemoteConfig$2 networkRepo$fetchRemoteConfig$2;
        Object obj2;
        Object await;
        Class<?> cls2;
        Object obj3;
        Object callRemoteConfigFetchAPI$default;
        HSRemoteConfig hSRemoteConfig;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        Object obj4;
        String str18;
        String str19;
        String str20;
        ?? r12;
        String str21;
        String localizedMessage;
        String str22;
        Object m1202constructorimpl3;
        String str23;
        Class<?> cls3;
        String str24;
        String str25;
        String className2;
        Class<?> cls4;
        String className3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            str = this.$appId;
            file = this.$cacheDir;
            try {
                Result.Companion companion2 = Result.INSTANCE;
                level = HyperLogger.Level.DEBUG;
                companion = HyperLogger.INSTANCE.getInstance();
                charSequence = "co.hyperverge";
                try {
                    sb = new StringBuilder();
                    try {
                        objectRef = new Ref.ObjectRef();
                    } catch (Throwable th) {
                        th = th;
                        str2 = "Throwable().stackTrace";
                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str4 = "null cannot be cast to non-null type android.app.Application";
                        str6 = "getInitialApplication";
                        str7 = "android.app.AppGlobals";
                        str5 = "packageName";
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str2 = "Throwable().stackTrace";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str4 = "null cannot be cast to non-null type android.app.Application";
                    str5 = "packageName";
                    str6 = "getInitialApplication";
                    str7 = "android.app.AppGlobals";
                    str8 = "";
                    str9 = "replaceAll(\"\")";
                    coroutineScope2 = coroutineScope;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    CoroutineScope coroutineScope3 = coroutineScope2;
                    Object obj5 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    ResultKt.throwOnFailure(obj4);
                    return obj4;
                }
            } catch (Throwable th3) {
                th = th3;
                str2 = "Throwable().stackTrace";
                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                str4 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
            }
            try {
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            } catch (Throwable th4) {
                th = th4;
                str2 = "Throwable().stackTrace";
                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                str6 = "getInitialApplication";
                str7 = "android.app.AppGlobals";
                str5 = "packageName";
                str4 = "null cannot be cast to non-null type android.app.Application";
                str8 = "";
                str9 = "replaceAll(\"\")";
                coroutineScope2 = coroutineScope;
                Result.Companion companion32 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                CoroutineScope coroutineScope32 = coroutineScope2;
                Object obj52 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj52);
                if (m1205exceptionOrNullimpl != null) {
                }
                ResultKt.throwOnFailure(obj4);
                return obj4;
            }
            if (stackTraceElement != null) {
                try {
                    className = stackTraceElement.getClassName();
                } catch (Throwable th5) {
                    th = th5;
                    str7 = "android.app.AppGlobals";
                    coroutineScope2 = coroutineScope;
                    str9 = "replaceAll(\"\")";
                    str2 = "Throwable().stackTrace";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str6 = "getInitialApplication";
                    str5 = "packageName";
                    str4 = "null cannot be cast to non-null type android.app.Application";
                    str8 = "";
                }
                if (className != null) {
                    str10 = "Throwable().stackTrace";
                    str11 = "getInitialApplication";
                    str7 = "android.app.AppGlobals";
                    try {
                        substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    } catch (Throwable th6) {
                        th = th6;
                        coroutineScope2 = coroutineScope;
                        str8 = "";
                        str9 = "replaceAll(\"\")";
                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str5 = "packageName";
                        str2 = str10;
                        str4 = "null cannot be cast to non-null type android.app.Application";
                        str6 = str11;
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
                            str14 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                            if (str14 == null) {
                                str14 = "null ";
                            }
                            sb.append(str14);
                            sb.append(' ');
                            sb.append("");
                            companion.log(level, sb.toString());
                            if (CoreExtsKt.isRelease()) {
                                str5 = "packageName";
                                str4 = "null cannot be cast to non-null type android.app.Application";
                                str6 = str11;
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    str6 = str11;
                                    try {
                                        Object invoke = Class.forName(str7).getMethod(str6, new Class[0]).invoke(null, new Object[0]);
                                        str4 = "null cannot be cast to non-null type android.app.Application";
                                        try {
                                            Intrinsics.checkNotNull(invoke, str4);
                                            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                        } catch (Throwable th7) {
                                            th = th7;
                                            try {
                                                Result.Companion companion5 = Result.INSTANCE;
                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                                }
                                                String str26 = (String) m1202constructorimpl;
                                                str5 = "packageName";
                                                if (CoreExtsKt.isDebug()) {
                                                }
                                                c = FilenameUtils.EXTENSION_SEPARATOR;
                                                deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                                                if (deferred == null) {
                                                }
                                            } catch (Throwable th8) {
                                                th = th8;
                                                str8 = "";
                                                str9 = "replaceAll(\"\")";
                                                str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                str5 = "packageName";
                                                str2 = str10;
                                                coroutineScope2 = coroutineScope;
                                                Result.Companion companion322 = Result.INSTANCE;
                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                CoroutineScope coroutineScope322 = coroutineScope2;
                                                Object obj522 = m1202constructorimpl2;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj522);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                ResultKt.throwOnFailure(obj4);
                                                return obj4;
                                            }
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        str4 = "null cannot be cast to non-null type android.app.Application";
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    str4 = "null cannot be cast to non-null type android.app.Application";
                                    str6 = str11;
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                    m1202constructorimpl = "";
                                }
                                String str262 = (String) m1202constructorimpl;
                                str5 = "packageName";
                                if (CoreExtsKt.isDebug()) {
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(str262, str5);
                                    } catch (Throwable th11) {
                                        th = th11;
                                        coroutineScope2 = coroutineScope;
                                        str8 = "";
                                        str9 = "replaceAll(\"\")";
                                        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                        str2 = str10;
                                    }
                                    if (StringsKt.contains$default((CharSequence) str262, charSequence, false, 2, (Object) null)) {
                                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                        String str27 = str10;
                                        try {
                                            Intrinsics.checkNotNullExpressionValue(stackTrace2, str27);
                                            stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                        } catch (Throwable th12) {
                                            th = th12;
                                            coroutineScope2 = coroutineScope;
                                            str8 = "";
                                            str9 = "replaceAll(\"\")";
                                            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                            str2 = str27;
                                        }
                                        if (stackTraceElement2 != null) {
                                            String className4 = stackTraceElement2.getClassName();
                                            if (className4 != null) {
                                                str10 = str27;
                                                c = FilenameUtils.EXTENSION_SEPARATOR;
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
                                                        str17 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                                                        if (str17 == null) {
                                                            str17 = "null ";
                                                        }
                                                        sb2.append(str17);
                                                        sb2.append(' ');
                                                        sb2.append("");
                                                        Log.println(3, str16, sb2.toString());
                                                        deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                                                        if (deferred == null) {
                                                            networkRepo$fetchRemoteConfig$2 = this;
                                                            obj2 = coroutine_suspended;
                                                            File file2 = file;
                                                            CoroutineScope coroutineScope4 = coroutineScope;
                                                            String str28 = str;
                                                            NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                                                            networkRepo$fetchRemoteConfig$2.L$0 = coroutineScope4;
                                                            networkRepo$fetchRemoteConfig$2.L$1 = null;
                                                            networkRepo$fetchRemoteConfig$2.L$2 = null;
                                                            networkRepo$fetchRemoteConfig$2.label = 2;
                                                            obj3 = obj2;
                                                            str8 = "";
                                                            str9 = "replaceAll(\"\")";
                                                            str2 = str10;
                                                            CoroutineScope coroutineScope5 = coroutineScope4;
                                                            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                                                            callRemoteConfigFetchAPI$default = NetworkRepo.callRemoteConfigFetchAPI$default(networkRepo, str28, file2, null, this, 4, null);
                                                            if (callRemoteConfigFetchAPI$default != obj3) {
                                                                return obj3;
                                                            }
                                                            coroutineScope2 = coroutineScope5;
                                                            hSRemoteConfig = (HSRemoteConfig) callRemoteConfigFetchAPI$default;
                                                            m1202constructorimpl2 = Result.m1202constructorimpl(hSRemoteConfig);
                                                            CoroutineScope coroutineScope3222 = coroutineScope2;
                                                            Object obj5222 = m1202constructorimpl2;
                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5222);
                                                            if (m1205exceptionOrNullimpl != null) {
                                                            }
                                                            ResultKt.throwOnFailure(obj4);
                                                            return obj4;
                                                        }
                                                        networkRepo$fetchRemoteConfig$2 = this;
                                                        networkRepo$fetchRemoteConfig$2.L$0 = coroutineScope;
                                                        networkRepo$fetchRemoteConfig$2.L$1 = str;
                                                        networkRepo$fetchRemoteConfig$2.L$2 = file;
                                                        networkRepo$fetchRemoteConfig$2.label = 1;
                                                        await = deferred.await(networkRepo$fetchRemoteConfig$2);
                                                        obj2 = coroutine_suspended;
                                                        if (await == obj2) {
                                                            return obj2;
                                                        }
                                                    }
                                                    str16 = (String) objectRef2.element;
                                                    StringBuilder sb22 = new StringBuilder();
                                                    str17 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                                                    if (str17 == null) {
                                                    }
                                                    sb22.append(str17);
                                                    sb22.append(' ');
                                                    sb22.append("");
                                                    Log.println(3, str16, sb22.toString());
                                                    deferred = NetworkRepo.prefetchRemoteConfigDeferred;
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
                                                    str17 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                                                    if (str17 == null) {
                                                    }
                                                    sb222.append(str17);
                                                    sb222.append(' ');
                                                    sb222.append("");
                                                    Log.println(3, str16, sb222.toString());
                                                    deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                                                    if (deferred == null) {
                                                    }
                                                }
                                                str16 = (String) objectRef2.element;
                                                StringBuilder sb2222 = new StringBuilder();
                                                str17 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                                                if (str17 == null) {
                                                }
                                                sb2222.append(str17);
                                                sb2222.append(' ');
                                                sb2222.append("");
                                                Log.println(3, str16, sb2222.toString());
                                                deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                                                if (deferred == null) {
                                                }
                                            }
                                        }
                                        str10 = str27;
                                        c = FilenameUtils.EXTENSION_SEPARATOR;
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
                                        str17 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                                        if (str17 == null) {
                                        }
                                        sb22222.append(str17);
                                        sb22222.append(' ');
                                        sb22222.append("");
                                        Log.println(3, str16, sb22222.toString());
                                        deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                                        if (deferred == null) {
                                        }
                                    }
                                }
                            }
                            c = FilenameUtils.EXTENSION_SEPARATOR;
                            deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                            if (deferred == null) {
                            }
                        }
                        str13 = (String) objectRef.element;
                        sb.append(str13);
                        sb.append(" - ");
                        str14 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                        if (str14 == null) {
                        }
                        sb.append(str14);
                        sb.append(' ');
                        sb.append("");
                        companion.log(level, sb.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        c = FilenameUtils.EXTENSION_SEPARATOR;
                        deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                        if (deferred == null) {
                        }
                    }
                    String canonicalName2 = (coroutineScope != null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
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
                        str14 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                        if (str14 == null) {
                        }
                        sb.append(str14);
                        sb.append(' ');
                        sb.append("");
                        companion.log(level, sb.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        c = FilenameUtils.EXTENSION_SEPARATOR;
                        deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                        if (deferred == null) {
                        }
                    }
                    str13 = (String) objectRef.element;
                    sb.append(str13);
                    sb.append(" - ");
                    str14 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
                    if (str14 == null) {
                    }
                    sb.append(str14);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                    }
                    c = FilenameUtils.EXTENSION_SEPARATOR;
                    deferred = NetworkRepo.prefetchRemoteConfigDeferred;
                    if (deferred == null) {
                    }
                }
            }
            str10 = "Throwable().stackTrace";
            str11 = "getInitialApplication";
            str7 = "android.app.AppGlobals";
            if (coroutineScope != null) {
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
            str14 = "fetchRemoteConfig() called with appId = " + str + ", cacheDir = " + file;
            if (str14 == null) {
            }
            sb.append(str14);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
            }
            c = FilenameUtils.EXTENSION_SEPARATOR;
            deferred = NetworkRepo.prefetchRemoteConfigDeferred;
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
                    callRemoteConfigFetchAPI$default = obj;
                    str2 = "Throwable().stackTrace";
                    str3 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str4 = "null cannot be cast to non-null type android.app.Application";
                    charSequence = "co.hyperverge";
                    str5 = "packageName";
                    str6 = "getInitialApplication";
                    str7 = "android.app.AppGlobals";
                    str8 = "";
                    str9 = "replaceAll(\"\")";
                    try {
                        hSRemoteConfig = (HSRemoteConfig) callRemoteConfigFetchAPI$default;
                        m1202constructorimpl2 = Result.m1202constructorimpl(hSRemoteConfig);
                    } catch (Throwable th13) {
                        th = th13;
                    }
                } catch (Throwable th14) {
                    th = th14;
                }
                CoroutineScope coroutineScope32222 = coroutineScope2;
                Object obj52222 = m1202constructorimpl2;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj52222);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    String str29 = str2;
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str29);
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                        obj4 = obj52222;
                        str18 = str29;
                        str19 = str5;
                        str20 = null;
                    } else {
                        obj4 = obj52222;
                        str18 = str29;
                        str19 = str5;
                        str20 = null;
                        r12 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r12 = (coroutineScope32222 == null || (cls4 = coroutineScope32222.getClass()) == null) ? str20 : cls4.getCanonicalName();
                    if (r12 == 0) {
                        r12 = "N/A";
                    }
                    objectRef3.element = r12;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher3.find()) {
                        ?? replaceAll3 = matcher3.replaceAll(str8);
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, str9);
                        objectRef3.element = replaceAll3;
                    }
                    if (((String) objectRef3.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str21 = ((String) objectRef3.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str21, str3);
                        sb3.append(str21);
                        sb3.append(" - ");
                        sb3.append("fetchRemoteConfig() failed");
                        sb3.append(' ');
                        localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str20;
                        if (localizedMessage == null) {
                            str22 = '\n' + localizedMessage;
                        } else {
                            str22 = str8;
                        }
                        sb3.append(str22);
                        companion6.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        Result.Companion companion7 = Result.INSTANCE;
                        Object invoke2 = Class.forName(str7).getMethod(str6, new Class[0]).invoke(str20, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, str4);
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = str8;
                        }
                        String str30 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str30, str19);
                            if (StringsKt.contains$default((CharSequence) str30, charSequence, false, 2, (Object) str20)) {
                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str18);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 != null && (className2 = stackTraceElement4.getClassName()) != null) {
                                    String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, str20, 2, str20);
                                    str23 = substringAfterLast$default3;
                                }
                                String canonicalName3 = (coroutineScope32222 == null || (cls3 = coroutineScope32222.getClass()) == null) ? str20 : cls3.getCanonicalName();
                                str23 = canonicalName3 == null ? "N/A" : canonicalName3;
                                objectRef4.element = str23;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher4.find()) {
                                    ?? replaceAll4 = matcher4.replaceAll(str8);
                                    Intrinsics.checkNotNullExpressionValue(replaceAll4, str9);
                                    objectRef4.element = replaceAll4;
                                }
                                if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str24 = (String) objectRef4.element;
                                } else {
                                    str24 = ((String) objectRef4.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str24, str3);
                                }
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("fetchRemoteConfig() failed");
                                sb4.append(' ');
                                String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str20;
                                if (localizedMessage2 != null) {
                                    str25 = '\n' + localizedMessage2;
                                } else {
                                    str25 = str8;
                                }
                                sb4.append(str25);
                                Log.println(6, str24, sb4.toString());
                            }
                        }
                    }
                    str21 = (String) objectRef3.element;
                    sb3.append(str21);
                    sb3.append(" - ");
                    sb3.append("fetchRemoteConfig() failed");
                    sb3.append(' ');
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    if (localizedMessage == null) {
                    }
                    sb3.append(str22);
                    companion6.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    Result.Companion companion72 = Result.INSTANCE;
                    Object invoke22 = Class.forName(str7).getMethod(str6, new Class[0]).invoke(str20, new Object[0]);
                    Intrinsics.checkNotNull(invoke22, str4);
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    }
                    String str302 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                    }
                } else {
                    obj4 = obj52222;
                }
                ResultKt.throwOnFailure(obj4);
                return obj4;
            }
            File file3 = (File) this.L$2;
            String str31 = (String) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                str = str31;
                str10 = "Throwable().stackTrace";
                networkRepo$fetchRemoteConfig$2 = this;
                obj2 = coroutine_suspended;
                str4 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str5 = "packageName";
                str6 = "getInitialApplication";
                str7 = "android.app.AppGlobals";
                c = FilenameUtils.EXTENSION_SEPARATOR;
                file = file3;
                await = obj;
            } catch (Throwable th15) {
                th = th15;
                coroutineScope2 = coroutineScope;
            }
            str2 = "Throwable().stackTrace";
            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
            str4 = "null cannot be cast to non-null type android.app.Application";
            charSequence = "co.hyperverge";
            str5 = "packageName";
            str6 = "getInitialApplication";
            str7 = "android.app.AppGlobals";
            str8 = "";
            str9 = "replaceAll(\"\")";
            Result.Companion companion3222 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            CoroutineScope coroutineScope322222 = coroutineScope2;
            Object obj522222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj522222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj4);
            return obj4;
        }
        hSRemoteConfig = (HSRemoteConfig) await;
        if (hSRemoteConfig != null) {
            coroutineScope2 = coroutineScope;
            str8 = "";
            str9 = "replaceAll(\"\")";
            str3 = "this as java.lang.String…ing(startIndex, endIndex)";
            str2 = str10;
            m1202constructorimpl2 = Result.m1202constructorimpl(hSRemoteConfig);
            CoroutineScope coroutineScope3222222 = coroutineScope2;
            Object obj5222222 = m1202constructorimpl2;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj5222222);
            if (m1205exceptionOrNullimpl != null) {
            }
            ResultKt.throwOnFailure(obj4);
            return obj4;
        }
        File file22 = file;
        CoroutineScope coroutineScope42 = coroutineScope;
        String str282 = str;
        NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
        networkRepo$fetchRemoteConfig$2.L$0 = coroutineScope42;
        networkRepo$fetchRemoteConfig$2.L$1 = null;
        networkRepo$fetchRemoteConfig$2.L$2 = null;
        networkRepo$fetchRemoteConfig$2.label = 2;
        obj3 = obj2;
        str8 = "";
        str9 = "replaceAll(\"\")";
        str2 = str10;
        CoroutineScope coroutineScope52 = coroutineScope42;
        str3 = "this as java.lang.String…ing(startIndex, endIndex)";
        callRemoteConfigFetchAPI$default = NetworkRepo.callRemoteConfigFetchAPI$default(networkRepo2, str282, file22, null, this, 4, null);
        if (callRemoteConfigFetchAPI$default != obj3) {
        }
    }
}
