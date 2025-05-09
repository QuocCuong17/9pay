package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkHelpers.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkHelpersKt$makeApiCall$3", f = "NetworkHelpers.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkHelpersKt$makeApiCall$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Response>>, Object> {
    final /* synthetic */ String $body;
    final /* synthetic */ File $cacheDir;
    final /* synthetic */ Integer $cacheExpiryInSeconds;
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ String $method;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NetworkHelpersKt$makeApiCall$3(String str, Map<String, String> map, String str2, String str3, File file, Integer num, Function1<? super Throwable, Unit> function1, Continuation<? super NetworkHelpersKt$makeApiCall$3> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$headers = map;
        this.$method = str2;
        this.$body = str3;
        this.$cacheDir = file;
        this.$cacheExpiryInSeconds = num;
        this.$onFailure = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkHelpersKt$makeApiCall$3 networkHelpersKt$makeApiCall$3 = new NetworkHelpersKt$makeApiCall$3(this.$url, this.$headers, this.$method, this.$body, this.$cacheDir, this.$cacheExpiryInSeconds, this.$onFailure, continuation);
        networkHelpersKt$makeApiCall$3.L$0 = obj;
        return networkHelpersKt$makeApiCall$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Response>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Response>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Response>> continuation) {
        return ((NetworkHelpersKt$makeApiCall$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x03f2, code lost:
    
        if (r13 != null) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x04f9, code lost:
    
        if (r2 != null) goto L208;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0568  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x056b  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0239 A[Catch: all -> 0x021d, TryCatch #11 {all -> 0x021d, blocks: (B:161:0x0207, B:164:0x0225, B:166:0x0239, B:167:0x0242, B:169:0x024e, B:172:0x0255, B:173:0x0266, B:174:0x0290, B:176:0x0296, B:191:0x0262, B:193:0x0212, B:195:0x0218), top: B:160:0x0207 }] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x024e A[Catch: all -> 0x021d, TryCatch #11 {all -> 0x021d, blocks: (B:161:0x0207, B:164:0x0225, B:166:0x0239, B:167:0x0242, B:169:0x024e, B:172:0x0255, B:173:0x0266, B:174:0x0290, B:176:0x0296, B:191:0x0262, B:193:0x0212, B:195:0x0218), top: B:160:0x0207 }] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0296 A[Catch: all -> 0x021d, TRY_LEAVE, TryCatch #11 {all -> 0x021d, blocks: (B:161:0x0207, B:164:0x0225, B:166:0x0239, B:167:0x0242, B:169:0x024e, B:172:0x0255, B:173:0x0266, B:174:0x0290, B:176:0x0296, B:191:0x0262, B:193:0x0212, B:195:0x0218), top: B:160:0x0207 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00bf A[Catch: all -> 0x009d, TryCatch #12 {all -> 0x009d, blocks: (B:17:0x0083, B:20:0x00ab, B:22:0x00bf, B:23:0x00c8, B:25:0x00d4, B:28:0x00db, B:29:0x00ec, B:30:0x0117, B:32:0x011d, B:34:0x0135, B:39:0x0143, B:42:0x015a, B:230:0x00e8, B:232:0x0092, B:234:0x0098), top: B:16:0x0083 }] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d4 A[Catch: all -> 0x009d, TryCatch #12 {all -> 0x009d, blocks: (B:17:0x0083, B:20:0x00ab, B:22:0x00bf, B:23:0x00c8, B:25:0x00d4, B:28:0x00db, B:29:0x00ec, B:30:0x0117, B:32:0x011d, B:34:0x0135, B:39:0x0143, B:42:0x015a, B:230:0x00e8, B:232:0x0092, B:234:0x0098), top: B:16:0x0083 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x011d A[Catch: all -> 0x009d, TryCatch #12 {all -> 0x009d, blocks: (B:17:0x0083, B:20:0x00ab, B:22:0x00bf, B:23:0x00c8, B:25:0x00d4, B:28:0x00db, B:29:0x00ec, B:30:0x0117, B:32:0x011d, B:34:0x0135, B:39:0x0143, B:42:0x015a, B:230:0x00e8, B:232:0x0092, B:234:0x0098), top: B:16:0x0083 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x030d A[Catch: all -> 0x0390, TryCatch #5 {all -> 0x0390, blocks: (B:45:0x0302, B:47:0x030d, B:49:0x0323, B:50:0x0338, B:52:0x036b, B:53:0x037b, B:179:0x02a8, B:181:0x02b2, B:186:0x02c2, B:189:0x02d9), top: B:178:0x02a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0323 A[Catch: all -> 0x0390, TryCatch #5 {all -> 0x0390, blocks: (B:45:0x0302, B:47:0x030d, B:49:0x0323, B:50:0x0338, B:52:0x036b, B:53:0x037b, B:179:0x02a8, B:181:0x02b2, B:186:0x02c2, B:189:0x02d9), top: B:178:0x02a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x036b A[Catch: all -> 0x0390, TryCatch #5 {all -> 0x0390, blocks: (B:45:0x0302, B:47:0x030d, B:49:0x0323, B:50:0x0338, B:52:0x036b, B:53:0x037b, B:179:0x02a8, B:181:0x02b2, B:186:0x02c2, B:189:0x02d9), top: B:178:0x02a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0525  */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [T] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8, types: [T] */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v37, types: [T] */
    /* JADX WARN: Type inference failed for: r1v47, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v49, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v75, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v76 */
    /* JADX WARN: Type inference failed for: r2v41, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v56, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [T] */
    /* JADX WARN: Type inference failed for: r7v37 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        String str5;
        Class<?> cls;
        String str6;
        Object m1202constructorimpl2;
        String str7;
        String str8;
        ?? r12;
        Matcher matcher;
        String str9;
        Class<?> cls2;
        String className;
        String className2;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        HyperLogger hyperLogger;
        String str10;
        String str11;
        String str12;
        Class<?> cls3;
        Matcher matcher2;
        String str13;
        Iterator<Map.Entry<String, String>> it;
        String sb2;
        String str14;
        String str15;
        Object m1202constructorimpl3;
        StackTraceElement stackTraceElement2;
        ?? canonicalName;
        Class<?> cls4;
        Matcher matcher3;
        String str16;
        Iterator<Map.Entry<String, String>> it2;
        String sb3;
        String substringAfterLast$default;
        String str17 = " - ";
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        String str18 = this.$url;
        Map<String, String> map = this.$headers;
        String str19 = this.$method;
        String str20 = "packageName";
        String str21 = this.$body;
        String str22 = "null cannot be cast to non-null type android.app.Application";
        File file = this.$cacheDir;
        final Integer num = this.$cacheExpiryInSeconds;
        try {
            Result.Companion companion2 = Result.INSTANCE;
            level = HyperLogger.Level.DEBUG;
            companion = HyperLogger.INSTANCE.getInstance();
            sb = new StringBuilder();
            str = "getInitialApplication";
            try {
                objectRef = new Ref.ObjectRef();
                str2 = "android.app.AppGlobals";
                try {
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                } catch (Throwable th) {
                    th = th;
                    str3 = " - ";
                    str4 = "Throwable().stackTrace";
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj2 = m1202constructorimpl;
                    Function1<Throwable, Unit> function1 = this.$onFailure;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj2);
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = "android.app.AppGlobals";
                str3 = " - ";
                str4 = "Throwable().stackTrace";
                Result.Companion companion32 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                Object obj22 = m1202constructorimpl;
                Function1<Throwable, Unit> function12 = this.$onFailure;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22);
                if (m1205exceptionOrNullimpl != null) {
                }
                return Result.m1201boximpl(obj22);
            }
        } catch (Throwable th3) {
            th = th3;
            str = "getInitialApplication";
        }
        if (stackTraceElement != null) {
            String className3 = stackTraceElement.getClassName();
            if (className3 != null) {
                hyperLogger = companion;
                str10 = str21;
                str11 = "Throwable().stackTrace";
                try {
                    substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                } catch (Throwable th4) {
                    th = th4;
                    str4 = str11;
                    str3 = str17;
                    Result.Companion companion322 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    Object obj222 = m1202constructorimpl;
                    Function1<Throwable, Unit> function122 = this.$onFailure;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj222);
                }
                if (substringAfterLast$default != null) {
                    str12 = substringAfterLast$default;
                    objectRef.element = str12;
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher2.find()) {
                        ?? replaceAll = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str13 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(str13);
                        sb.append(" - ");
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("makeApiCall() called with url:");
                        sb4.append(str18);
                        sb4.append(", method:");
                        sb4.append(str19);
                        sb4.append(", headers: ");
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, String> next = it.next();
                            Iterator<Map.Entry<String, String>> it3 = it;
                            if (!Intrinsics.areEqual(next.getKey(), HyperKycConfig.APP_KEY)) {
                                linkedHashMap.put(next.getKey(), next.getValue());
                            }
                            it = it3;
                        }
                        sb4.append(linkedHashMap);
                        sb4.append(", body: ");
                        String str23 = str10;
                        sb4.append(str23);
                        sb2 = sb4.toString();
                        if (sb2 == null) {
                            sb2 = "null ";
                        }
                        sb.append(sb2);
                        sb.append(' ');
                        sb.append("");
                        hyperLogger.log(level, sb.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                str15 = str;
                                try {
                                    Object invoke = Class.forName(str2).getMethod(str15, new Class[0]).invoke(null, new Object[0]);
                                    str14 = str22;
                                    try {
                                        Intrinsics.checkNotNull(invoke, str14);
                                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                    } catch (Throwable th5) {
                                        th = th5;
                                        try {
                                            Result.Companion companion5 = Result.INSTANCE;
                                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                            }
                                            String str24 = (String) m1202constructorimpl3;
                                            if (CoreExtsKt.isDebug()) {
                                            }
                                            str = str15;
                                            str4 = str11;
                                            str3 = " - ";
                                            OkHttpClient.Builder newBuilder = new OkHttpClient().newBuilder();
                                            if (file != null) {
                                            }
                                            if (num != null) {
                                            }
                                            newBuilder.retryOnConnectionFailure(false);
                                            newBuilder.callTimeout(10L, TimeUnit.SECONDS);
                                            newBuilder.connectTimeout(10L, TimeUnit.SECONDS);
                                            newBuilder.readTimeout(10L, TimeUnit.SECONDS);
                                            newBuilder.writeTimeout(10L, TimeUnit.SECONDS);
                                            m1202constructorimpl = Result.m1202constructorimpl(newBuilder.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                                        } catch (Throwable th6) {
                                            th = th6;
                                            str22 = str14;
                                            str = str15;
                                            str4 = str11;
                                            str3 = str17;
                                            Result.Companion companion3222 = Result.INSTANCE;
                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            Object obj2222 = m1202constructorimpl;
                                            Function1<Throwable, Unit> function1222 = this.$onFailure;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2222);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                            return Result.m1201boximpl(obj2222);
                                        }
                                        Object obj22222 = m1202constructorimpl;
                                        Function1<Throwable, Unit> function12222 = this.$onFailure;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22222);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        return Result.m1201boximpl(obj22222);
                                    }
                                } catch (Throwable th7) {
                                    th = th7;
                                    str14 = str22;
                                }
                            } catch (Throwable th8) {
                                th = th8;
                                str14 = str22;
                                str15 = str;
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String str242 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                                str22 = str14;
                            } else {
                                try {
                                    Intrinsics.checkNotNullExpressionValue(str242, str20);
                                    str22 = str14;
                                    str20 = str20;
                                    try {
                                    } catch (Throwable th9) {
                                        th = th9;
                                        str = str15;
                                        str4 = str11;
                                        str3 = str17;
                                        Result.Companion companion32222 = Result.INSTANCE;
                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        Object obj222222 = m1202constructorimpl;
                                        Function1<Throwable, Unit> function122222 = this.$onFailure;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222222);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        return Result.m1201boximpl(obj222222);
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    str22 = str14;
                                    str20 = str20;
                                }
                                if (StringsKt.contains$default((CharSequence) str242, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                    str4 = str11;
                                    try {
                                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str4);
                                        stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                    } catch (Throwable th11) {
                                        th = th11;
                                        str = str15;
                                    }
                                    if (stackTraceElement2 != null) {
                                        String className4 = stackTraceElement2.getClassName();
                                        if (className4 != null) {
                                            str = str15;
                                            try {
                                                canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            } catch (Throwable th12) {
                                                th = th12;
                                                str3 = str17;
                                                Result.Companion companion322222 = Result.INSTANCE;
                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                Object obj2222222 = m1202constructorimpl;
                                                Function1<Throwable, Unit> function1222222 = this.$onFailure;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2222222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                return Result.m1201boximpl(obj2222222);
                                            }
                                            if (canonicalName != 0) {
                                                objectRef2.element = canonicalName;
                                                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                if (matcher3.find()) {
                                                    ?? replaceAll2 = matcher3.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                                    objectRef2.element = replaceAll2;
                                                }
                                                if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str16 = ((String) objectRef2.element).substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    StringBuilder sb5 = new StringBuilder();
                                                    StringBuilder sb6 = new StringBuilder();
                                                    sb6.append("makeApiCall() called with url:");
                                                    sb6.append(str18);
                                                    sb6.append(", method:");
                                                    sb6.append(str19);
                                                    sb6.append(", headers: ");
                                                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                                                    it2 = map.entrySet().iterator();
                                                    while (it2.hasNext()) {
                                                        Map.Entry<String, String> next2 = it2.next();
                                                        Iterator<Map.Entry<String, String>> it4 = it2;
                                                        str3 = str17;
                                                        try {
                                                            if (!Intrinsics.areEqual(next2.getKey(), HyperKycConfig.APP_KEY)) {
                                                                linkedHashMap2.put(next2.getKey(), next2.getValue());
                                                            }
                                                            str17 = str3;
                                                            it2 = it4;
                                                        } catch (Throwable th13) {
                                                            th = th13;
                                                            Result.Companion companion3222222 = Result.INSTANCE;
                                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                            Object obj22222222 = m1202constructorimpl;
                                                            Function1<Throwable, Unit> function12222222 = this.$onFailure;
                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22222222);
                                                            if (m1205exceptionOrNullimpl != null) {
                                                            }
                                                            return Result.m1201boximpl(obj22222222);
                                                        }
                                                    }
                                                    str3 = str17;
                                                    sb6.append(linkedHashMap2);
                                                    sb6.append(", body: ");
                                                    sb6.append(str23);
                                                    sb3 = sb6.toString();
                                                    if (sb3 == null) {
                                                        sb3 = "null ";
                                                    }
                                                    sb5.append(sb3);
                                                    sb5.append(' ');
                                                    sb5.append("");
                                                    Log.println(3, str16, sb5.toString());
                                                    OkHttpClient.Builder newBuilder2 = new OkHttpClient().newBuilder();
                                                    if (file != null) {
                                                        newBuilder2.cache(new Cache(new File(file, "okhttp-cache"), 10485760L));
                                                    }
                                                    if (num != null) {
                                                        num.intValue();
                                                        newBuilder2.addNetworkInterceptor(new Interceptor() { // from class: co.hyperverge.hyperkyc.data.network.NetworkHelpersKt$makeApiCall$3$invokeSuspend$lambda$7$lambda$5$lambda$4$$inlined$-addNetworkInterceptor$1
                                                            @Override // okhttp3.Interceptor
                                                            public final Response intercept(Interceptor.Chain chain) {
                                                                Intrinsics.checkNotNullParameter(chain, "chain");
                                                                return chain.proceed(chain.request()).newBuilder().header("cache-control", "max-age=" + num).build();
                                                            }
                                                        });
                                                        Unit unit = Unit.INSTANCE;
                                                    }
                                                    newBuilder2.retryOnConnectionFailure(false);
                                                    newBuilder2.callTimeout(10L, TimeUnit.SECONDS);
                                                    newBuilder2.connectTimeout(10L, TimeUnit.SECONDS);
                                                    newBuilder2.readTimeout(10L, TimeUnit.SECONDS);
                                                    newBuilder2.writeTimeout(10L, TimeUnit.SECONDS);
                                                    m1202constructorimpl = Result.m1202constructorimpl(newBuilder2.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                                                    Object obj222222222 = m1202constructorimpl;
                                                    Function1<Throwable, Unit> function122222222 = this.$onFailure;
                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222222222);
                                                    if (m1205exceptionOrNullimpl != null) {
                                                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                                        HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                                                        StringBuilder sb7 = new StringBuilder();
                                                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str4);
                                                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                                        if (stackTraceElement3 != null && (className2 = stackTraceElement3.getClassName()) != null) {
                                                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                            str5 = substringAfterLast$default2;
                                                        }
                                                        String canonicalName2 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? null : cls.getCanonicalName();
                                                        str5 = canonicalName2 == null ? "N/A" : canonicalName2;
                                                        objectRef3.element = str5;
                                                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                        if (matcher4.find()) {
                                                            ?? replaceAll3 = matcher4.replaceAll("");
                                                            Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                                                            objectRef3.element = replaceAll3;
                                                        }
                                                        if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                            str6 = (String) objectRef3.element;
                                                        } else {
                                                            str6 = ((String) objectRef3.element).substring(0, 23);
                                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        }
                                                        sb7.append(str6);
                                                        sb7.append(str3);
                                                        String str25 = "makeApiCall() onFailure() called with " + m1205exceptionOrNullimpl;
                                                        if (str25 == null) {
                                                            str25 = "null ";
                                                        }
                                                        sb7.append(str25);
                                                        sb7.append(' ');
                                                        sb7.append("");
                                                        companion6.log(level2, sb7.toString());
                                                        CoreExtsKt.isRelease();
                                                        try {
                                                            Result.Companion companion7 = Result.INSTANCE;
                                                            Object invoke2 = Class.forName(str2).getMethod(str, new Class[0]).invoke(null, new Object[0]);
                                                            Intrinsics.checkNotNull(invoke2, str22);
                                                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                                        } catch (Throwable th14) {
                                                            Result.Companion companion8 = Result.INSTANCE;
                                                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th14));
                                                        }
                                                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                                            m1202constructorimpl2 = "";
                                                        }
                                                        String str26 = (String) m1202constructorimpl2;
                                                        if (CoreExtsKt.isDebug()) {
                                                            Intrinsics.checkNotNullExpressionValue(str26, str20);
                                                            if (StringsKt.contains$default((CharSequence) str26, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str4);
                                                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                                if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                                                                    str7 = null;
                                                                } else {
                                                                    str7 = null;
                                                                    str8 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                }
                                                                str8 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? str7 : cls2.getCanonicalName();
                                                                if (str8 == null) {
                                                                    r12 = "N/A";
                                                                    objectRef4.element = r12;
                                                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                                                    if (matcher.find()) {
                                                                        ?? replaceAll4 = matcher.replaceAll("");
                                                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                                                        objectRef4.element = replaceAll4;
                                                                    }
                                                                    if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                                                        str9 = (String) objectRef4.element;
                                                                    } else {
                                                                        str9 = ((String) objectRef4.element).substring(0, 23);
                                                                        Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                    }
                                                                    StringBuilder sb8 = new StringBuilder();
                                                                    String str27 = "makeApiCall() onFailure() called with " + m1205exceptionOrNullimpl;
                                                                    sb8.append(str27 != null ? "null " : str27);
                                                                    sb8.append(' ');
                                                                    sb8.append("");
                                                                    Log.println(6, str9, sb8.toString());
                                                                }
                                                                r12 = str8;
                                                                objectRef4.element = r12;
                                                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                                                if (matcher.find()) {
                                                                }
                                                                if (((String) objectRef4.element).length() > 23) {
                                                                }
                                                                str9 = (String) objectRef4.element;
                                                                StringBuilder sb82 = new StringBuilder();
                                                                String str272 = "makeApiCall() onFailure() called with " + m1205exceptionOrNullimpl;
                                                                sb82.append(str272 != null ? "null " : str272);
                                                                sb82.append(' ');
                                                                sb82.append("");
                                                                Log.println(6, str9, sb82.toString());
                                                            }
                                                        }
                                                        function122222222.invoke(m1205exceptionOrNullimpl);
                                                    }
                                                    return Result.m1201boximpl(obj222222222);
                                                }
                                                str16 = (String) objectRef2.element;
                                                StringBuilder sb52 = new StringBuilder();
                                                StringBuilder sb62 = new StringBuilder();
                                                sb62.append("makeApiCall() called with url:");
                                                sb62.append(str18);
                                                sb62.append(", method:");
                                                sb62.append(str19);
                                                sb62.append(", headers: ");
                                                LinkedHashMap linkedHashMap22 = new LinkedHashMap();
                                                it2 = map.entrySet().iterator();
                                                while (it2.hasNext()) {
                                                }
                                                str3 = str17;
                                                sb62.append(linkedHashMap22);
                                                sb62.append(", body: ");
                                                sb62.append(str23);
                                                sb3 = sb62.toString();
                                                if (sb3 == null) {
                                                }
                                                sb52.append(sb3);
                                                sb52.append(' ');
                                                sb52.append("");
                                                Log.println(3, str16, sb52.toString());
                                                OkHttpClient.Builder newBuilder22 = new OkHttpClient().newBuilder();
                                                if (file != null) {
                                                }
                                                if (num != null) {
                                                }
                                                newBuilder22.retryOnConnectionFailure(false);
                                                newBuilder22.callTimeout(10L, TimeUnit.SECONDS);
                                                newBuilder22.connectTimeout(10L, TimeUnit.SECONDS);
                                                newBuilder22.readTimeout(10L, TimeUnit.SECONDS);
                                                newBuilder22.writeTimeout(10L, TimeUnit.SECONDS);
                                                m1202constructorimpl = Result.m1202constructorimpl(newBuilder22.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                                                Object obj2222222222 = m1202constructorimpl;
                                                Function1<Throwable, Unit> function1222222222 = this.$onFailure;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2222222222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                return Result.m1201boximpl(obj2222222222);
                                            }
                                            canonicalName = (coroutineScope != null || (cls4 = coroutineScope.getClass()) == null) ? 0 : cls4.getCanonicalName();
                                            if (canonicalName == 0) {
                                                canonicalName = "N/A";
                                            }
                                            objectRef2.element = canonicalName;
                                            matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                            if (matcher3.find()) {
                                            }
                                            if (((String) objectRef2.element).length() > 23) {
                                                str16 = ((String) objectRef2.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                StringBuilder sb522 = new StringBuilder();
                                                StringBuilder sb622 = new StringBuilder();
                                                sb622.append("makeApiCall() called with url:");
                                                sb622.append(str18);
                                                sb622.append(", method:");
                                                sb622.append(str19);
                                                sb622.append(", headers: ");
                                                LinkedHashMap linkedHashMap222 = new LinkedHashMap();
                                                it2 = map.entrySet().iterator();
                                                while (it2.hasNext()) {
                                                }
                                                str3 = str17;
                                                sb622.append(linkedHashMap222);
                                                sb622.append(", body: ");
                                                sb622.append(str23);
                                                sb3 = sb622.toString();
                                                if (sb3 == null) {
                                                }
                                                sb522.append(sb3);
                                                sb522.append(' ');
                                                sb522.append("");
                                                Log.println(3, str16, sb522.toString());
                                                OkHttpClient.Builder newBuilder222 = new OkHttpClient().newBuilder();
                                                if (file != null) {
                                                }
                                                if (num != null) {
                                                }
                                                newBuilder222.retryOnConnectionFailure(false);
                                                newBuilder222.callTimeout(10L, TimeUnit.SECONDS);
                                                newBuilder222.connectTimeout(10L, TimeUnit.SECONDS);
                                                newBuilder222.readTimeout(10L, TimeUnit.SECONDS);
                                                newBuilder222.writeTimeout(10L, TimeUnit.SECONDS);
                                                m1202constructorimpl = Result.m1202constructorimpl(newBuilder222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                                                Object obj22222222222 = m1202constructorimpl;
                                                Function1<Throwable, Unit> function12222222222 = this.$onFailure;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22222222222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                return Result.m1201boximpl(obj22222222222);
                                            }
                                            str16 = (String) objectRef2.element;
                                            StringBuilder sb5222 = new StringBuilder();
                                            StringBuilder sb6222 = new StringBuilder();
                                            sb6222.append("makeApiCall() called with url:");
                                            sb6222.append(str18);
                                            sb6222.append(", method:");
                                            sb6222.append(str19);
                                            sb6222.append(", headers: ");
                                            LinkedHashMap linkedHashMap2222 = new LinkedHashMap();
                                            it2 = map.entrySet().iterator();
                                            while (it2.hasNext()) {
                                            }
                                            str3 = str17;
                                            sb6222.append(linkedHashMap2222);
                                            sb6222.append(", body: ");
                                            sb6222.append(str23);
                                            sb3 = sb6222.toString();
                                            if (sb3 == null) {
                                            }
                                            sb5222.append(sb3);
                                            sb5222.append(' ');
                                            sb5222.append("");
                                            Log.println(3, str16, sb5222.toString());
                                            OkHttpClient.Builder newBuilder2222 = new OkHttpClient().newBuilder();
                                            if (file != null) {
                                            }
                                            if (num != null) {
                                            }
                                            newBuilder2222.retryOnConnectionFailure(false);
                                            newBuilder2222.callTimeout(10L, TimeUnit.SECONDS);
                                            newBuilder2222.connectTimeout(10L, TimeUnit.SECONDS);
                                            newBuilder2222.readTimeout(10L, TimeUnit.SECONDS);
                                            newBuilder2222.writeTimeout(10L, TimeUnit.SECONDS);
                                            m1202constructorimpl = Result.m1202constructorimpl(newBuilder2222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                                            Object obj222222222222 = m1202constructorimpl;
                                            Function1<Throwable, Unit> function122222222222 = this.$onFailure;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222222222222);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                            return Result.m1201boximpl(obj222222222222);
                                        }
                                    }
                                    str = str15;
                                    if (coroutineScope != null) {
                                    }
                                    if (canonicalName == 0) {
                                    }
                                    objectRef2.element = canonicalName;
                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                    if (matcher3.find()) {
                                    }
                                    if (((String) objectRef2.element).length() > 23) {
                                    }
                                    str16 = (String) objectRef2.element;
                                    StringBuilder sb52222 = new StringBuilder();
                                    StringBuilder sb62222 = new StringBuilder();
                                    sb62222.append("makeApiCall() called with url:");
                                    sb62222.append(str18);
                                    sb62222.append(", method:");
                                    sb62222.append(str19);
                                    sb62222.append(", headers: ");
                                    LinkedHashMap linkedHashMap22222 = new LinkedHashMap();
                                    it2 = map.entrySet().iterator();
                                    while (it2.hasNext()) {
                                    }
                                    str3 = str17;
                                    sb62222.append(linkedHashMap22222);
                                    sb62222.append(", body: ");
                                    sb62222.append(str23);
                                    sb3 = sb62222.toString();
                                    if (sb3 == null) {
                                    }
                                    sb52222.append(sb3);
                                    sb52222.append(' ');
                                    sb52222.append("");
                                    Log.println(3, str16, sb52222.toString());
                                    OkHttpClient.Builder newBuilder22222 = new OkHttpClient().newBuilder();
                                    if (file != null) {
                                    }
                                    if (num != null) {
                                    }
                                    newBuilder22222.retryOnConnectionFailure(false);
                                    newBuilder22222.callTimeout(10L, TimeUnit.SECONDS);
                                    newBuilder22222.connectTimeout(10L, TimeUnit.SECONDS);
                                    newBuilder22222.readTimeout(10L, TimeUnit.SECONDS);
                                    newBuilder22222.writeTimeout(10L, TimeUnit.SECONDS);
                                    m1202constructorimpl = Result.m1202constructorimpl(newBuilder22222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                                    Object obj2222222222222 = m1202constructorimpl;
                                    Function1<Throwable, Unit> function1222222222222 = this.$onFailure;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2222222222222);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                    return Result.m1201boximpl(obj2222222222222);
                                }
                            }
                            str = str15;
                        }
                        str4 = str11;
                        str3 = " - ";
                        OkHttpClient.Builder newBuilder222222 = new OkHttpClient().newBuilder();
                        if (file != null) {
                        }
                        if (num != null) {
                        }
                        newBuilder222222.retryOnConnectionFailure(false);
                        newBuilder222222.callTimeout(10L, TimeUnit.SECONDS);
                        newBuilder222222.connectTimeout(10L, TimeUnit.SECONDS);
                        newBuilder222222.readTimeout(10L, TimeUnit.SECONDS);
                        newBuilder222222.writeTimeout(10L, TimeUnit.SECONDS);
                        m1202constructorimpl = Result.m1202constructorimpl(newBuilder222222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23 != null ? RequestBody.INSTANCE.create(str23, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                        Object obj22222222222222 = m1202constructorimpl;
                        Function1<Throwable, Unit> function12222222222222 = this.$onFailure;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22222222222222);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        return Result.m1201boximpl(obj22222222222222);
                    }
                    str13 = (String) objectRef.element;
                    sb.append(str13);
                    sb.append(" - ");
                    StringBuilder sb42 = new StringBuilder();
                    sb42.append("makeApiCall() called with url:");
                    sb42.append(str18);
                    sb42.append(", method:");
                    sb42.append(str19);
                    sb42.append(", headers: ");
                    LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                    it = map.entrySet().iterator();
                    while (it.hasNext()) {
                    }
                    sb42.append(linkedHashMap3);
                    sb42.append(", body: ");
                    String str232 = str10;
                    sb42.append(str232);
                    sb2 = sb42.toString();
                    if (sb2 == null) {
                    }
                    sb.append(sb2);
                    sb.append(' ');
                    sb.append("");
                    hyperLogger.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    str4 = str11;
                    str3 = " - ";
                    OkHttpClient.Builder newBuilder2222222 = new OkHttpClient().newBuilder();
                    if (file != null) {
                    }
                    if (num != null) {
                    }
                    newBuilder2222222.retryOnConnectionFailure(false);
                    newBuilder2222222.callTimeout(10L, TimeUnit.SECONDS);
                    newBuilder2222222.connectTimeout(10L, TimeUnit.SECONDS);
                    newBuilder2222222.readTimeout(10L, TimeUnit.SECONDS);
                    newBuilder2222222.writeTimeout(10L, TimeUnit.SECONDS);
                    m1202constructorimpl = Result.m1202constructorimpl(newBuilder2222222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str232 != null ? RequestBody.INSTANCE.create(str232, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                    Object obj222222222222222 = m1202constructorimpl;
                    Function1<Throwable, Unit> function122222222222222 = this.$onFailure;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222222222222222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj222222222222222);
                }
                String canonicalName3 = (coroutineScope != null || (cls3 = coroutineScope.getClass()) == null) ? null : cls3.getCanonicalName();
                str12 = canonicalName3 != null ? "N/A" : canonicalName3;
                objectRef.element = str12;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher2.find()) {
                }
                if (((String) objectRef.element).length() > 23) {
                    str13 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                    sb.append(str13);
                    sb.append(" - ");
                    StringBuilder sb422 = new StringBuilder();
                    sb422.append("makeApiCall() called with url:");
                    sb422.append(str18);
                    sb422.append(", method:");
                    sb422.append(str19);
                    sb422.append(", headers: ");
                    LinkedHashMap linkedHashMap32 = new LinkedHashMap();
                    it = map.entrySet().iterator();
                    while (it.hasNext()) {
                    }
                    sb422.append(linkedHashMap32);
                    sb422.append(", body: ");
                    String str2322 = str10;
                    sb422.append(str2322);
                    sb2 = sb422.toString();
                    if (sb2 == null) {
                    }
                    sb.append(sb2);
                    sb.append(' ');
                    sb.append("");
                    hyperLogger.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    str4 = str11;
                    str3 = " - ";
                    OkHttpClient.Builder newBuilder22222222 = new OkHttpClient().newBuilder();
                    if (file != null) {
                    }
                    if (num != null) {
                    }
                    newBuilder22222222.retryOnConnectionFailure(false);
                    newBuilder22222222.callTimeout(10L, TimeUnit.SECONDS);
                    newBuilder22222222.connectTimeout(10L, TimeUnit.SECONDS);
                    newBuilder22222222.readTimeout(10L, TimeUnit.SECONDS);
                    newBuilder22222222.writeTimeout(10L, TimeUnit.SECONDS);
                    m1202constructorimpl = Result.m1202constructorimpl(newBuilder22222222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str2322 != null ? RequestBody.INSTANCE.create(str2322, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                    Object obj2222222222222222 = m1202constructorimpl;
                    Function1<Throwable, Unit> function1222222222222222 = this.$onFailure;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2222222222222222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj2222222222222222);
                }
                str13 = (String) objectRef.element;
                sb.append(str13);
                sb.append(" - ");
                StringBuilder sb4222 = new StringBuilder();
                sb4222.append("makeApiCall() called with url:");
                sb4222.append(str18);
                sb4222.append(", method:");
                sb4222.append(str19);
                sb4222.append(", headers: ");
                LinkedHashMap linkedHashMap322 = new LinkedHashMap();
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                }
                sb4222.append(linkedHashMap322);
                sb4222.append(", body: ");
                String str23222 = str10;
                sb4222.append(str23222);
                sb2 = sb4222.toString();
                if (sb2 == null) {
                }
                sb.append(sb2);
                sb.append(' ');
                sb.append("");
                hyperLogger.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                str4 = str11;
                str3 = " - ";
                OkHttpClient.Builder newBuilder222222222 = new OkHttpClient().newBuilder();
                if (file != null) {
                }
                if (num != null) {
                }
                newBuilder222222222.retryOnConnectionFailure(false);
                newBuilder222222222.callTimeout(10L, TimeUnit.SECONDS);
                newBuilder222222222.connectTimeout(10L, TimeUnit.SECONDS);
                newBuilder222222222.readTimeout(10L, TimeUnit.SECONDS);
                newBuilder222222222.writeTimeout(10L, TimeUnit.SECONDS);
                m1202constructorimpl = Result.m1202constructorimpl(newBuilder222222222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str23222 != null ? RequestBody.INSTANCE.create(str23222, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
                Object obj22222222222222222 = m1202constructorimpl;
                Function1<Throwable, Unit> function12222222222222222 = this.$onFailure;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22222222222222222);
                if (m1205exceptionOrNullimpl != null) {
                }
                return Result.m1201boximpl(obj22222222222222222);
            }
        }
        hyperLogger = companion;
        str10 = str21;
        str11 = "Throwable().stackTrace";
        if (coroutineScope != null) {
        }
        if (canonicalName3 != null) {
        }
        objectRef.element = str12;
        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
        if (matcher2.find()) {
        }
        if (((String) objectRef.element).length() > 23) {
        }
        str13 = (String) objectRef.element;
        sb.append(str13);
        sb.append(" - ");
        StringBuilder sb42222 = new StringBuilder();
        sb42222.append("makeApiCall() called with url:");
        sb42222.append(str18);
        sb42222.append(", method:");
        sb42222.append(str19);
        sb42222.append(", headers: ");
        LinkedHashMap linkedHashMap3222 = new LinkedHashMap();
        it = map.entrySet().iterator();
        while (it.hasNext()) {
        }
        sb42222.append(linkedHashMap3222);
        sb42222.append(", body: ");
        String str232222 = str10;
        sb42222.append(str232222);
        sb2 = sb42222.toString();
        if (sb2 == null) {
        }
        sb.append(sb2);
        sb.append(' ');
        sb.append("");
        hyperLogger.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        str4 = str11;
        str3 = " - ";
        OkHttpClient.Builder newBuilder2222222222 = new OkHttpClient().newBuilder();
        if (file != null) {
        }
        if (num != null) {
        }
        newBuilder2222222222.retryOnConnectionFailure(false);
        newBuilder2222222222.callTimeout(10L, TimeUnit.SECONDS);
        newBuilder2222222222.connectTimeout(10L, TimeUnit.SECONDS);
        newBuilder2222222222.readTimeout(10L, TimeUnit.SECONDS);
        newBuilder2222222222.writeTimeout(10L, TimeUnit.SECONDS);
        m1202constructorimpl = Result.m1202constructorimpl(newBuilder2222222222.build().newCall(new Request.Builder().url(str18).headers(Headers.INSTANCE.of(map)).method(str19, str232222 != null ? RequestBody.INSTANCE.create(str232222, MediaType.INSTANCE.get("application/json")) : null).build()).execute());
        Object obj222222222222222222 = m1202constructorimpl;
        Function1<Throwable, Unit> function122222222222222222 = this.$onFailure;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222222222222222222);
        if (m1205exceptionOrNullimpl != null) {
        }
        return Result.m1201boximpl(obj222222222222222222);
    }
}
