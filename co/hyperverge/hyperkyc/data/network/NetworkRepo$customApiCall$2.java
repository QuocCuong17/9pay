package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import javax.net.SocketFactory;
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
import kotlinx.coroutines.DelayKt;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$customApiCall$2", f = "NetworkRepo.kt", i = {0, 0, 0, 0, 0}, l = {715}, m = "invokeSuspend", n = {"$this$onIO", "$this$invokeSuspend_u24lambda_u244", "client", "request", AppConstants.ATTEMPTS_KEY}, s = {"L$0", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes2.dex */
public final class NetworkRepo$customApiCall$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Response>>, Object> {
    final /* synthetic */ RequestBody $body;
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ int $maxRetries;
    final /* synthetic */ String $method;
    final /* synthetic */ SocketFactory $socketFactory;
    final /* synthetic */ long $timeoutSeconds;
    final /* synthetic */ String $url;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$customApiCall$2(long j, SocketFactory socketFactory, String str, Map<String, String> map, String str2, RequestBody requestBody, int i, Continuation<? super NetworkRepo$customApiCall$2> continuation) {
        super(2, continuation);
        this.$timeoutSeconds = j;
        this.$socketFactory = socketFactory;
        this.$url = str;
        this.$headers = map;
        this.$method = str2;
        this.$body = requestBody;
        this.$maxRetries = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$customApiCall$2 networkRepo$customApiCall$2 = new NetworkRepo$customApiCall$2(this.$timeoutSeconds, this.$socketFactory, this.$url, this.$headers, this.$method, this.$body, this.$maxRetries, continuation);
        networkRepo$customApiCall$2.L$0 = obj;
        return networkRepo$customApiCall$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Response>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Response>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Response>> continuation) {
        return ((NetworkRepo$customApiCall$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x065d, code lost:
    
        if (r12 != null) goto L271;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x078b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x07ce  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x07d1  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x07e6  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x04b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x039f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x03f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0124 A[Catch: all -> 0x0103, TryCatch #13 {all -> 0x0103, blocks: (B:252:0x00e9, B:255:0x0110, B:257:0x0124, B:258:0x012d, B:260:0x0139, B:263:0x0140, B:264:0x0151, B:267:0x01a0, B:351:0x014d, B:353:0x00f8, B:355:0x00fe), top: B:251:0x00e9 }] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0139 A[Catch: all -> 0x0103, TryCatch #13 {all -> 0x0103, blocks: (B:252:0x00e9, B:255:0x0110, B:257:0x0124, B:258:0x012d, B:260:0x0139, B:263:0x0140, B:264:0x0151, B:267:0x01a0, B:351:0x014d, B:353:0x00f8, B:355:0x00fe), top: B:251:0x00e9 }] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x01c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x027e A[Catch: all -> 0x0601, TryCatch #12 {all -> 0x0601, blocks: (B:270:0x0318, B:294:0x0220, B:296:0x0226, B:304:0x024f, B:307:0x026a, B:309:0x027e, B:310:0x0287, B:312:0x0293, B:315:0x029a, B:316:0x02ab, B:319:0x02f5, B:320:0x02a7, B:322:0x025a, B:324:0x0260), top: B:293:0x0220 }] */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0293 A[Catch: all -> 0x0601, TryCatch #12 {all -> 0x0601, blocks: (B:270:0x0318, B:294:0x0220, B:296:0x0226, B:304:0x024f, B:307:0x026a, B:309:0x027e, B:310:0x0287, B:312:0x0293, B:315:0x029a, B:316:0x02ab, B:319:0x02f5, B:320:0x02a7, B:322:0x025a, B:324:0x0260), top: B:293:0x0220 }] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x041b A[Catch: all -> 0x0426, TryCatch #4 {all -> 0x0426, blocks: (B:215:0x0403, B:41:0x0441, B:45:0x0456, B:48:0x045d, B:33:0x041b, B:35:0x0421), top: B:214:0x0403 }] */
    /* JADX WARN: Removed duplicated region for block: B:357:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0441 A[Catch: all -> 0x0426, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x0426, blocks: (B:215:0x0403, B:41:0x0441, B:45:0x0456, B:48:0x045d, B:33:0x041b, B:35:0x0421), top: B:214:0x0403 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0456 A[Catch: all -> 0x0426, TRY_ENTER, TryCatch #4 {all -> 0x0426, blocks: (B:215:0x0403, B:41:0x0441, B:45:0x0456, B:48:0x045d, B:33:0x041b, B:35:0x0421), top: B:214:0x0403 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0625  */
    /* JADX WARN: Type inference failed for: r12v15, types: [T] */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v6, types: [T] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r1v102, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v104 */
    /* JADX WARN: Type inference failed for: r1v11, types: [T] */
    /* JADX WARN: Type inference failed for: r1v33, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v35, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v72, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v82, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20, types: [T] */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r6v25, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v26, types: [T] */
    /* JADX WARN: Type inference failed for: r6v47, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v55 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v19, types: [T] */
    /* JADX WARN: Type inference failed for: r7v29, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v31, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v73 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
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
        SocketFactory socketFactory;
        int i;
        String str11;
        ?? canonicalName;
        Class<?> cls;
        Matcher matcher;
        String str12;
        String sb;
        String str13;
        String str14;
        Object m1202constructorimpl;
        String str15;
        ?? canonicalName2;
        Class<?> cls2;
        Matcher matcher2;
        String str16;
        String str17;
        NetworkRepo$customApiCall$2 networkRepo$customApiCall$2;
        Request build;
        Ref.IntRef intRef;
        Object obj2;
        CoroutineScope coroutineScope2;
        CoroutineScope coroutineScope3;
        int i2;
        OkHttpClient okHttpClient;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        String str18;
        String str19;
        Object m1202constructorimpl2;
        Object obj4;
        ?? canonicalName3;
        Class<?> cls3;
        Matcher matcher3;
        String str20;
        String className;
        Class<?> cls4;
        String className2;
        CoroutineScope coroutineScope4;
        Object m1202constructorimpl3;
        Object obj5;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        ?? substringAfterLast$default;
        Matcher matcher4;
        String str21;
        String str22;
        Object m1202constructorimpl4;
        String str23;
        Class<?> cls5;
        String str24;
        String str25;
        String className3;
        String substringAfterLast$default2;
        Object invoke;
        Class<?> cls6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            long j = this.$timeoutSeconds;
            SocketFactory socketFactory2 = this.$socketFactory;
            str = "null ";
            str2 = this.$url;
            charSequence = "co.hyperverge";
            Map<String, String> map = this.$headers;
            str3 = "packageName";
            String str26 = this.$method;
            str4 = "null cannot be cast to non-null type android.app.Application";
            RequestBody requestBody = this.$body;
            str5 = "getInitialApplication";
            int i4 = this.$maxRetries;
            try {
                Result.Companion companion = Result.INSTANCE;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
                str6 = "android.app.AppGlobals";
                try {
                    StringBuilder sb2 = new StringBuilder();
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement2 != null) {
                        String className4 = stackTraceElement2.getClassName();
                        if (className4 != null) {
                            socketFactory = socketFactory2;
                            i = i4;
                            str11 = "Throwable().stackTrace";
                            try {
                                canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (canonicalName != 0) {
                                    objectRef2.element = canonicalName;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                    if (matcher.find()) {
                                        ?? replaceAll = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                                        objectRef2.element = replaceAll;
                                    }
                                    if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str12 = ((String) objectRef2.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                                        sb2.append(str12);
                                        sb2.append(" - ");
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append("customApiCall() called with: url = ");
                                        sb3.append(str2);
                                        sb3.append(", method = ");
                                        sb3.append(str26);
                                        sb3.append(", headers = ");
                                        sb3.append(map);
                                        sb3.append(", body = ");
                                        sb3.append(requestBody);
                                        sb3.append(", timeoutSeconds = ");
                                        sb3.append(j);
                                        sb3.append(", socketFactory = ");
                                        SocketFactory socketFactory3 = socketFactory;
                                        sb3.append(socketFactory3);
                                        sb3.append(", maxRetries: ");
                                        int i5 = i;
                                        sb3.append(i5);
                                        sb = sb3.toString();
                                        if (sb == null) {
                                            sb = str;
                                        }
                                        sb2.append(sb);
                                        sb2.append(' ');
                                        sb2.append("");
                                        companion2.log(level, sb2.toString());
                                        if (CoreExtsKt.isRelease()) {
                                            str15 = " - ";
                                        } else {
                                            try {
                                                Result.Companion companion3 = Result.INSTANCE;
                                                str14 = str5;
                                                try {
                                                    Object invoke2 = Class.forName(str6).getMethod(str14, new Class[0]).invoke(null, new Object[0]);
                                                    str13 = str4;
                                                    try {
                                                        Intrinsics.checkNotNull(invoke2, str13);
                                                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        try {
                                                            Result.Companion companion4 = Result.INSTANCE;
                                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                                            }
                                                            String str27 = (String) m1202constructorimpl;
                                                            if (CoreExtsKt.isDebug()) {
                                                            }
                                                            OkHttpClient build2 = new OkHttpClient.Builder().callTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).socketFactory(socketFactory3).retryOnConnectionFailure(true).build();
                                                            Request.Builder headers = new Request.Builder().url(str2).headers(Headers.INSTANCE.of(map));
                                                            String upperCase = str26.toUpperCase(Locale.ROOT);
                                                            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                                            networkRepo$customApiCall$2 = this;
                                                            build = headers.method(upperCase, requestBody).build();
                                                            intRef = new Ref.IntRef();
                                                            obj2 = coroutine_suspended;
                                                            coroutineScope2 = coroutineScope;
                                                            coroutineScope3 = coroutineScope2;
                                                            i2 = i5;
                                                            okHttpClient = build2;
                                                            do {
                                                                try {
                                                                    intRef.element++;
                                                                    try {
                                                                        Result.Companion companion5 = Result.INSTANCE;
                                                                        m1202constructorimpl3 = Result.m1202constructorimpl(okHttpClient.newCall(build).execute());
                                                                    } catch (Throwable th2) {
                                                                        Result.Companion companion6 = Result.INSTANCE;
                                                                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                    }
                                                                    obj5 = m1202constructorimpl3;
                                                                    if (Result.m1209isSuccessimpl(obj5)) {
                                                                    }
                                                                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                                                                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                                                                    StringBuilder sb4 = new StringBuilder();
                                                                    objectRef = new Ref.ObjectRef();
                                                                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                                                    str8 = str11;
                                                                    try {
                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str8);
                                                                        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                                                        if (stackTraceElement != null) {
                                                                        }
                                                                        coroutineScope4 = coroutineScope3;
                                                                        if (coroutineScope2 != null) {
                                                                        }
                                                                        if (substringAfterLast$default == 0) {
                                                                        }
                                                                        try {
                                                                            objectRef.element = substringAfterLast$default;
                                                                            matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                                                                            if (matcher4.find()) {
                                                                            }
                                                                            if (((String) objectRef.element).length() > 23) {
                                                                            }
                                                                            str21 = (String) objectRef.element;
                                                                            sb4.append(str21);
                                                                            str7 = str15;
                                                                            try {
                                                                                sb4.append(str7);
                                                                                str22 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                                                                                if (str22 == null) {
                                                                                }
                                                                                sb4.append(str22);
                                                                                sb4.append(' ');
                                                                                sb4.append("");
                                                                                companion7.log(level2, sb4.toString());
                                                                                if (CoreExtsKt.isRelease()) {
                                                                                }
                                                                                return Result.m1201boximpl(obj5);
                                                                            } catch (Throwable th3) {
                                                                                th = th3;
                                                                                str9 = str4;
                                                                                str10 = str5;
                                                                            }
                                                                        } catch (Throwable th4) {
                                                                            th = th4;
                                                                            str9 = str4;
                                                                            str10 = str5;
                                                                            str7 = str15;
                                                                            coroutineScope = coroutineScope4;
                                                                            Result.Companion companion8 = Result.INSTANCE;
                                                                            Object m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                                                                            if (m1205exceptionOrNullimpl != null) {
                                                                            }
                                                                            return Result.m1201boximpl(obj3);
                                                                        }
                                                                    } catch (Throwable th5) {
                                                                        th = th5;
                                                                        coroutineScope4 = coroutineScope3;
                                                                    }
                                                                } catch (Throwable th6) {
                                                                    th = th6;
                                                                    coroutineScope4 = coroutineScope3;
                                                                    str9 = str4;
                                                                    str10 = str5;
                                                                    str7 = str15;
                                                                    str8 = str11;
                                                                }
                                                            } while (DelayKt.delay(1000L, networkRepo$customApiCall$2) != obj2);
                                                            return obj2;
                                                        } catch (Throwable th7) {
                                                            th = th7;
                                                            str9 = str13;
                                                            str7 = " - ";
                                                            str10 = str14;
                                                            str8 = str11;
                                                            Result.Companion companion82 = Result.INSTANCE;
                                                            Object m1202constructorimpl52 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl52);
                                                            if (m1205exceptionOrNullimpl != null) {
                                                            }
                                                            return Result.m1201boximpl(obj3);
                                                        }
                                                    }
                                                } catch (Throwable th8) {
                                                    th = th8;
                                                    str13 = str4;
                                                }
                                            } catch (Throwable th9) {
                                                th = th9;
                                                str13 = str4;
                                                str14 = str5;
                                            }
                                            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                                m1202constructorimpl = "";
                                            }
                                            String str272 = (String) m1202constructorimpl;
                                            if (CoreExtsKt.isDebug()) {
                                                str4 = str13;
                                                str15 = " - ";
                                                str5 = str14;
                                            } else {
                                                try {
                                                    Intrinsics.checkNotNullExpressionValue(str272, str3);
                                                    str3 = str3;
                                                    str4 = str13;
                                                    str15 = " - ";
                                                    str5 = str14;
                                                    try {
                                                        if (StringsKt.contains$default((CharSequence) str272, charSequence, false, 2, (Object) null)) {
                                                            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                                            str8 = str11;
                                                            try {
                                                                Intrinsics.checkNotNullExpressionValue(stackTrace3, str8);
                                                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                                                if (stackTraceElement3 != null) {
                                                                    String className5 = stackTraceElement3.getClassName();
                                                                    if (className5 != null) {
                                                                        str11 = str8;
                                                                        canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                        if (canonicalName2 != 0) {
                                                                            objectRef3.element = canonicalName2;
                                                                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                                            if (matcher2.find()) {
                                                                                ?? replaceAll2 = matcher2.replaceAll("");
                                                                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                                                                objectRef3.element = replaceAll2;
                                                                            }
                                                                            if (((String) objectRef3.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                str16 = ((String) objectRef3.element).substring(0, 23);
                                                                                Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                StringBuilder sb5 = new StringBuilder();
                                                                                str17 = "customApiCall() called with: url = " + str2 + ", method = " + str26 + ", headers = " + map + ", body = " + requestBody + ", timeoutSeconds = " + j + ", socketFactory = " + socketFactory3 + ", maxRetries: " + i5;
                                                                                if (str17 == null) {
                                                                                    str17 = str;
                                                                                }
                                                                                sb5.append(str17);
                                                                                sb5.append(' ');
                                                                                sb5.append("");
                                                                                Log.println(3, str16, sb5.toString());
                                                                            }
                                                                            str16 = (String) objectRef3.element;
                                                                            StringBuilder sb52 = new StringBuilder();
                                                                            str17 = "customApiCall() called with: url = " + str2 + ", method = " + str26 + ", headers = " + map + ", body = " + requestBody + ", timeoutSeconds = " + j + ", socketFactory = " + socketFactory3 + ", maxRetries: " + i5;
                                                                            if (str17 == null) {
                                                                            }
                                                                            sb52.append(str17);
                                                                            sb52.append(' ');
                                                                            sb52.append("");
                                                                            Log.println(3, str16, sb52.toString());
                                                                        }
                                                                        canonicalName2 = (coroutineScope != null || (cls2 = coroutineScope.getClass()) == null) ? 0 : cls2.getCanonicalName();
                                                                        if (canonicalName2 == 0) {
                                                                            canonicalName2 = "N/A";
                                                                        }
                                                                        objectRef3.element = canonicalName2;
                                                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                                        if (matcher2.find()) {
                                                                        }
                                                                        if (((String) objectRef3.element).length() > 23) {
                                                                            str16 = ((String) objectRef3.element).substring(0, 23);
                                                                            Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                            StringBuilder sb522 = new StringBuilder();
                                                                            str17 = "customApiCall() called with: url = " + str2 + ", method = " + str26 + ", headers = " + map + ", body = " + requestBody + ", timeoutSeconds = " + j + ", socketFactory = " + socketFactory3 + ", maxRetries: " + i5;
                                                                            if (str17 == null) {
                                                                            }
                                                                            sb522.append(str17);
                                                                            sb522.append(' ');
                                                                            sb522.append("");
                                                                            Log.println(3, str16, sb522.toString());
                                                                        }
                                                                        str16 = (String) objectRef3.element;
                                                                        StringBuilder sb5222 = new StringBuilder();
                                                                        str17 = "customApiCall() called with: url = " + str2 + ", method = " + str26 + ", headers = " + map + ", body = " + requestBody + ", timeoutSeconds = " + j + ", socketFactory = " + socketFactory3 + ", maxRetries: " + i5;
                                                                        if (str17 == null) {
                                                                        }
                                                                        sb5222.append(str17);
                                                                        sb5222.append(' ');
                                                                        sb5222.append("");
                                                                        Log.println(3, str16, sb5222.toString());
                                                                    }
                                                                }
                                                                str11 = str8;
                                                                if (coroutineScope != null) {
                                                                }
                                                                if (canonicalName2 == 0) {
                                                                }
                                                                objectRef3.element = canonicalName2;
                                                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                                if (matcher2.find()) {
                                                                }
                                                                if (((String) objectRef3.element).length() > 23) {
                                                                }
                                                                str16 = (String) objectRef3.element;
                                                                StringBuilder sb52222 = new StringBuilder();
                                                                str17 = "customApiCall() called with: url = " + str2 + ", method = " + str26 + ", headers = " + map + ", body = " + requestBody + ", timeoutSeconds = " + j + ", socketFactory = " + socketFactory3 + ", maxRetries: " + i5;
                                                                if (str17 == null) {
                                                                }
                                                                sb52222.append(str17);
                                                                sb52222.append(' ');
                                                                sb52222.append("");
                                                                Log.println(3, str16, sb52222.toString());
                                                            } catch (Throwable th10) {
                                                                th = th10;
                                                                str9 = str4;
                                                                str10 = str5;
                                                                str7 = str15;
                                                                Result.Companion companion822 = Result.INSTANCE;
                                                                Object m1202constructorimpl522 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl522);
                                                                if (m1205exceptionOrNullimpl != null) {
                                                                }
                                                                return Result.m1201boximpl(obj3);
                                                            }
                                                        }
                                                    } catch (Throwable th11) {
                                                        th = th11;
                                                        str9 = str4;
                                                        str10 = str5;
                                                        str7 = str15;
                                                        str8 = str11;
                                                        Result.Companion companion8222 = Result.INSTANCE;
                                                        Object m1202constructorimpl5222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl5222);
                                                        if (m1205exceptionOrNullimpl != null) {
                                                        }
                                                        return Result.m1201boximpl(obj3);
                                                    }
                                                } catch (Throwable th12) {
                                                    th = th12;
                                                    str9 = str13;
                                                    str3 = str3;
                                                    str7 = " - ";
                                                    str10 = str14;
                                                    str8 = str11;
                                                    Result.Companion companion82222 = Result.INSTANCE;
                                                    Object m1202constructorimpl52222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl52222);
                                                    if (m1205exceptionOrNullimpl != null) {
                                                    }
                                                    return Result.m1201boximpl(obj3);
                                                }
                                            }
                                        }
                                        OkHttpClient build22 = new OkHttpClient.Builder().callTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).socketFactory(socketFactory3).retryOnConnectionFailure(true).build();
                                        Request.Builder headers2 = new Request.Builder().url(str2).headers(Headers.INSTANCE.of(map));
                                        String upperCase2 = str26.toUpperCase(Locale.ROOT);
                                        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                        networkRepo$customApiCall$2 = this;
                                        build = headers2.method(upperCase2, requestBody).build();
                                        intRef = new Ref.IntRef();
                                        obj2 = coroutine_suspended;
                                        coroutineScope2 = coroutineScope;
                                        coroutineScope3 = coroutineScope2;
                                        i2 = i5;
                                        okHttpClient = build22;
                                    }
                                    str12 = (String) objectRef2.element;
                                    sb2.append(str12);
                                    sb2.append(" - ");
                                    StringBuilder sb32 = new StringBuilder();
                                    sb32.append("customApiCall() called with: url = ");
                                    sb32.append(str2);
                                    sb32.append(", method = ");
                                    sb32.append(str26);
                                    sb32.append(", headers = ");
                                    sb32.append(map);
                                    sb32.append(", body = ");
                                    sb32.append(requestBody);
                                    sb32.append(", timeoutSeconds = ");
                                    sb32.append(j);
                                    sb32.append(", socketFactory = ");
                                    SocketFactory socketFactory32 = socketFactory;
                                    sb32.append(socketFactory32);
                                    sb32.append(", maxRetries: ");
                                    int i52 = i;
                                    sb32.append(i52);
                                    sb = sb32.toString();
                                    if (sb == null) {
                                    }
                                    sb2.append(sb);
                                    sb2.append(' ');
                                    sb2.append("");
                                    companion2.log(level, sb2.toString());
                                    if (CoreExtsKt.isRelease()) {
                                    }
                                    OkHttpClient build222 = new OkHttpClient.Builder().callTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).socketFactory(socketFactory32).retryOnConnectionFailure(true).build();
                                    Request.Builder headers22 = new Request.Builder().url(str2).headers(Headers.INSTANCE.of(map));
                                    String upperCase22 = str26.toUpperCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(upperCase22, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                    networkRepo$customApiCall$2 = this;
                                    build = headers22.method(upperCase22, requestBody).build();
                                    intRef = new Ref.IntRef();
                                    obj2 = coroutine_suspended;
                                    coroutineScope2 = coroutineScope;
                                    coroutineScope3 = coroutineScope2;
                                    i2 = i52;
                                    okHttpClient = build222;
                                }
                                canonicalName = (coroutineScope != null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                                if (canonicalName == 0) {
                                    canonicalName = "N/A";
                                }
                                objectRef2.element = canonicalName;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef2.element).length() > 23) {
                                    str12 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                                    sb2.append(str12);
                                    sb2.append(" - ");
                                    StringBuilder sb322 = new StringBuilder();
                                    sb322.append("customApiCall() called with: url = ");
                                    sb322.append(str2);
                                    sb322.append(", method = ");
                                    sb322.append(str26);
                                    sb322.append(", headers = ");
                                    sb322.append(map);
                                    sb322.append(", body = ");
                                    sb322.append(requestBody);
                                    sb322.append(", timeoutSeconds = ");
                                    sb322.append(j);
                                    sb322.append(", socketFactory = ");
                                    SocketFactory socketFactory322 = socketFactory;
                                    sb322.append(socketFactory322);
                                    sb322.append(", maxRetries: ");
                                    int i522 = i;
                                    sb322.append(i522);
                                    sb = sb322.toString();
                                    if (sb == null) {
                                    }
                                    sb2.append(sb);
                                    sb2.append(' ');
                                    sb2.append("");
                                    companion2.log(level, sb2.toString());
                                    if (CoreExtsKt.isRelease()) {
                                    }
                                    OkHttpClient build2222 = new OkHttpClient.Builder().callTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).socketFactory(socketFactory322).retryOnConnectionFailure(true).build();
                                    Request.Builder headers222 = new Request.Builder().url(str2).headers(Headers.INSTANCE.of(map));
                                    String upperCase222 = str26.toUpperCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(upperCase222, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                    networkRepo$customApiCall$2 = this;
                                    build = headers222.method(upperCase222, requestBody).build();
                                    intRef = new Ref.IntRef();
                                    obj2 = coroutine_suspended;
                                    coroutineScope2 = coroutineScope;
                                    coroutineScope3 = coroutineScope2;
                                    i2 = i522;
                                    okHttpClient = build2222;
                                }
                                str12 = (String) objectRef2.element;
                                sb2.append(str12);
                                sb2.append(" - ");
                                StringBuilder sb3222 = new StringBuilder();
                                sb3222.append("customApiCall() called with: url = ");
                                sb3222.append(str2);
                                sb3222.append(", method = ");
                                sb3222.append(str26);
                                sb3222.append(", headers = ");
                                sb3222.append(map);
                                sb3222.append(", body = ");
                                sb3222.append(requestBody);
                                sb3222.append(", timeoutSeconds = ");
                                sb3222.append(j);
                                sb3222.append(", socketFactory = ");
                                SocketFactory socketFactory3222 = socketFactory;
                                sb3222.append(socketFactory3222);
                                sb3222.append(", maxRetries: ");
                                int i5222 = i;
                                sb3222.append(i5222);
                                sb = sb3222.toString();
                                if (sb == null) {
                                }
                                sb2.append(sb);
                                sb2.append(' ');
                                sb2.append("");
                                companion2.log(level, sb2.toString());
                                if (CoreExtsKt.isRelease()) {
                                }
                                OkHttpClient build22222 = new OkHttpClient.Builder().callTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).socketFactory(socketFactory3222).retryOnConnectionFailure(true).build();
                                Request.Builder headers2222 = new Request.Builder().url(str2).headers(Headers.INSTANCE.of(map));
                                String upperCase2222 = str26.toUpperCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(upperCase2222, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                                networkRepo$customApiCall$2 = this;
                                build = headers2222.method(upperCase2222, requestBody).build();
                                intRef = new Ref.IntRef();
                                obj2 = coroutine_suspended;
                                coroutineScope2 = coroutineScope;
                                coroutineScope3 = coroutineScope2;
                                i2 = i5222;
                                okHttpClient = build22222;
                            } catch (Throwable th13) {
                                th = th13;
                                str7 = " - ";
                                str9 = str4;
                                str10 = str5;
                                str8 = str11;
                                Result.Companion companion822222 = Result.INSTANCE;
                                Object m1202constructorimpl522222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl522222);
                                if (m1205exceptionOrNullimpl != null) {
                                }
                                return Result.m1201boximpl(obj3);
                            }
                        }
                    }
                    socketFactory = socketFactory2;
                    i = i4;
                    str11 = "Throwable().stackTrace";
                    if (coroutineScope != null) {
                    }
                    if (canonicalName == 0) {
                    }
                    objectRef2.element = canonicalName;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef2.element).length() > 23) {
                    }
                    str12 = (String) objectRef2.element;
                    sb2.append(str12);
                    sb2.append(" - ");
                    StringBuilder sb32222 = new StringBuilder();
                    sb32222.append("customApiCall() called with: url = ");
                    sb32222.append(str2);
                    sb32222.append(", method = ");
                    sb32222.append(str26);
                    sb32222.append(", headers = ");
                    sb32222.append(map);
                    sb32222.append(", body = ");
                    sb32222.append(requestBody);
                    sb32222.append(", timeoutSeconds = ");
                    sb32222.append(j);
                    sb32222.append(", socketFactory = ");
                    SocketFactory socketFactory32222 = socketFactory;
                    sb32222.append(socketFactory32222);
                    sb32222.append(", maxRetries: ");
                    int i52222 = i;
                    sb32222.append(i52222);
                    sb = sb32222.toString();
                    if (sb == null) {
                    }
                    sb2.append(sb);
                    sb2.append(' ');
                    sb2.append("");
                    companion2.log(level, sb2.toString());
                    if (CoreExtsKt.isRelease()) {
                    }
                    OkHttpClient build222222 = new OkHttpClient.Builder().callTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS).readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).socketFactory(socketFactory32222).retryOnConnectionFailure(true).build();
                    Request.Builder headers22222 = new Request.Builder().url(str2).headers(Headers.INSTANCE.of(map));
                    String upperCase22222 = str26.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase22222, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    networkRepo$customApiCall$2 = this;
                    build = headers22222.method(upperCase22222, requestBody).build();
                    intRef = new Ref.IntRef();
                    obj2 = coroutine_suspended;
                    coroutineScope2 = coroutineScope;
                    coroutineScope3 = coroutineScope2;
                    i2 = i52222;
                    okHttpClient = build222222;
                } catch (Throwable th14) {
                    th = th14;
                    str7 = " - ";
                    str8 = "Throwable().stackTrace";
                    str9 = str4;
                    str10 = str5;
                    Result.Companion companion8222222 = Result.INSTANCE;
                    Object m1202constructorimpl5222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl5222222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj3);
                }
            } catch (Throwable th15) {
                th = th15;
                str6 = "android.app.AppGlobals";
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i6 = this.I$0;
            intRef = (Ref.IntRef) this.L$5;
            Request request = (Request) this.L$4;
            OkHttpClient okHttpClient2 = (OkHttpClient) this.L$3;
            CoroutineScope coroutineScope5 = (CoroutineScope) this.L$2;
            String str28 = (String) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                str5 = "getInitialApplication";
                str6 = "android.app.AppGlobals";
                str15 = " - ";
                str = "null ";
                str11 = "Throwable().stackTrace";
                coroutineScope3 = coroutineScope;
                networkRepo$customApiCall$2 = this;
                coroutineScope2 = coroutineScope5;
                str3 = "packageName";
                build = request;
                str2 = str28;
                str4 = "null cannot be cast to non-null type android.app.Application";
                i2 = i6;
                obj2 = coroutine_suspended;
                okHttpClient = okHttpClient2;
                charSequence = "co.hyperverge";
            } catch (Throwable th16) {
                th = th16;
                str9 = "null cannot be cast to non-null type android.app.Application";
                charSequence = "co.hyperverge";
                str3 = "packageName";
                str10 = "getInitialApplication";
                str6 = "android.app.AppGlobals";
                str7 = " - ";
                str = "null ";
                str8 = "Throwable().stackTrace";
                Result.Companion companion82222222 = Result.INSTANCE;
                Object m1202constructorimpl52222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl52222222);
                if (m1205exceptionOrNullimpl != null) {
                }
                return Result.m1201boximpl(obj3);
            }
        }
        do {
            intRef.element++;
            Result.Companion companion52 = Result.INSTANCE;
            m1202constructorimpl3 = Result.m1202constructorimpl(okHttpClient.newCall(build).execute());
            obj5 = m1202constructorimpl3;
            if (Result.m1209isSuccessimpl(obj5)) {
                try {
                    if (intRef.element < i2 + 1) {
                        networkRepo$customApiCall$2.L$0 = coroutineScope3;
                        networkRepo$customApiCall$2.L$1 = str2;
                        networkRepo$customApiCall$2.L$2 = coroutineScope2;
                        networkRepo$customApiCall$2.L$3 = okHttpClient;
                        networkRepo$customApiCall$2.L$4 = build;
                        networkRepo$customApiCall$2.L$5 = intRef;
                        networkRepo$customApiCall$2.I$0 = i2;
                        networkRepo$customApiCall$2.label = 1;
                    }
                } catch (Throwable th17) {
                    th = th17;
                    coroutineScope = coroutineScope3;
                    str9 = str4;
                    str10 = str5;
                    str7 = str15;
                    str8 = str11;
                    Result.Companion companion822222222 = Result.INSTANCE;
                    Object m1202constructorimpl522222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl522222222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj3);
                }
            }
            HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
            HyperLogger companion72 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb42 = new StringBuilder();
            objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace22 = new Throwable().getStackTrace();
            str8 = str11;
            Intrinsics.checkNotNullExpressionValue(stackTrace22, str8);
            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace22);
            if (stackTraceElement != null) {
                try {
                    String className6 = stackTraceElement.getClassName();
                    if (className6 != null) {
                        coroutineScope4 = coroutineScope3;
                        try {
                            substringAfterLast$default = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default != 0) {
                                objectRef.element = substringAfterLast$default;
                                matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                                if (matcher4.find()) {
                                    ?? replaceAll3 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                                    objectRef.element = replaceAll3;
                                }
                                if (((String) objectRef.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str21 = ((String) objectRef.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str21, "this as java.lang.String…ing(startIndex, endIndex)");
                                    sb42.append(str21);
                                    str7 = str15;
                                    sb42.append(str7);
                                    str22 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                                    if (str22 == null) {
                                        str22 = str;
                                    }
                                    sb42.append(str22);
                                    sb42.append(' ');
                                    sb42.append("");
                                    companion72.log(level22, sb42.toString());
                                    if (CoreExtsKt.isRelease()) {
                                        try {
                                            Result.Companion companion9 = Result.INSTANCE;
                                            str10 = str5;
                                            try {
                                                invoke = Class.forName(str6).getMethod(str10, new Class[0]).invoke(null, new Object[0]);
                                                str9 = str4;
                                            } catch (Throwable th18) {
                                                th = th18;
                                                str9 = str4;
                                            }
                                        } catch (Throwable th19) {
                                            th = th19;
                                            str9 = str4;
                                            str10 = str5;
                                        }
                                        try {
                                            Intrinsics.checkNotNull(invoke, str9);
                                            m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                        } catch (Throwable th20) {
                                            th = th20;
                                            try {
                                                Result.Companion companion10 = Result.INSTANCE;
                                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                                }
                                                String str29 = (String) m1202constructorimpl4;
                                                if (CoreExtsKt.isDebug()) {
                                                }
                                                return Result.m1201boximpl(obj5);
                                            } catch (Throwable th21) {
                                                th = th21;
                                                coroutineScope = coroutineScope4;
                                                Result.Companion companion8222222222 = Result.INSTANCE;
                                                Object m1202constructorimpl5222222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl5222222222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                return Result.m1201boximpl(obj3);
                                            }
                                        }
                                        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                            m1202constructorimpl4 = "";
                                        }
                                        String str292 = (String) m1202constructorimpl4;
                                        if (CoreExtsKt.isDebug()) {
                                            String str30 = str3;
                                            try {
                                                Intrinsics.checkNotNullExpressionValue(str292, str30);
                                                str3 = str30;
                                                if (StringsKt.contains$default((CharSequence) str292, charSequence, false, 2, (Object) null)) {
                                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str8);
                                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                    if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                        String canonicalName4 = (coroutineScope2 == null || (cls5 = coroutineScope2.getClass()) == null) ? null : cls5.getCanonicalName();
                                                        str23 = canonicalName4 == null ? "N/A" : canonicalName4;
                                                    } else {
                                                        str23 = substringAfterLast$default2;
                                                    }
                                                    objectRef4.element = str23;
                                                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                                    if (matcher5.find()) {
                                                        ?? replaceAll4 = matcher5.replaceAll("");
                                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                                        objectRef4.element = replaceAll4;
                                                    }
                                                    if (((String) objectRef4.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                        str24 = ((String) objectRef4.element).substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str24, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        StringBuilder sb6 = new StringBuilder();
                                                        str25 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                                                        if (str25 == null) {
                                                            str25 = str;
                                                        }
                                                        sb6.append(str25);
                                                        sb6.append(' ');
                                                        sb6.append("");
                                                        Log.println(3, str24, sb6.toString());
                                                    }
                                                    str24 = (String) objectRef4.element;
                                                    StringBuilder sb62 = new StringBuilder();
                                                    str25 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                                                    if (str25 == null) {
                                                    }
                                                    sb62.append(str25);
                                                    sb62.append(' ');
                                                    sb62.append("");
                                                    Log.println(3, str24, sb62.toString());
                                                }
                                            } catch (Throwable th22) {
                                                th = th22;
                                                str3 = str30;
                                                coroutineScope = coroutineScope4;
                                                Result.Companion companion82222222222 = Result.INSTANCE;
                                                Object m1202constructorimpl52222222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl52222222222);
                                                if (m1205exceptionOrNullimpl != null) {
                                                }
                                                return Result.m1201boximpl(obj3);
                                            }
                                        }
                                    } else {
                                        str9 = str4;
                                        str10 = str5;
                                    }
                                    return Result.m1201boximpl(obj5);
                                }
                                str21 = (String) objectRef.element;
                                sb42.append(str21);
                                str7 = str15;
                                sb42.append(str7);
                                str22 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                                if (str22 == null) {
                                }
                                sb42.append(str22);
                                sb42.append(' ');
                                sb42.append("");
                                companion72.log(level22, sb42.toString());
                                if (CoreExtsKt.isRelease()) {
                                }
                                return Result.m1201boximpl(obj5);
                            }
                            substringAfterLast$default = (coroutineScope2 != null || (cls6 = coroutineScope2.getClass()) == null) ? 0 : cls6.getCanonicalName();
                            if (substringAfterLast$default == 0) {
                                substringAfterLast$default = "N/A";
                            }
                            objectRef.element = substringAfterLast$default;
                            matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                            if (matcher4.find()) {
                            }
                            if (((String) objectRef.element).length() > 23) {
                                str21 = ((String) objectRef.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str21, "this as java.lang.String…ing(startIndex, endIndex)");
                                sb42.append(str21);
                                str7 = str15;
                                sb42.append(str7);
                                str22 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                                if (str22 == null) {
                                }
                                sb42.append(str22);
                                sb42.append(' ');
                                sb42.append("");
                                companion72.log(level22, sb42.toString());
                                if (CoreExtsKt.isRelease()) {
                                }
                                return Result.m1201boximpl(obj5);
                            }
                            str21 = (String) objectRef.element;
                            sb42.append(str21);
                            str7 = str15;
                            sb42.append(str7);
                            str22 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
                            if (str22 == null) {
                            }
                            sb42.append(str22);
                            sb42.append(' ');
                            sb42.append("");
                            companion72.log(level22, sb42.toString());
                            if (CoreExtsKt.isRelease()) {
                            }
                            return Result.m1201boximpl(obj5);
                        } catch (Throwable th23) {
                            th = th23;
                            coroutineScope = coroutineScope4;
                            str9 = str4;
                            str10 = str5;
                            str7 = str15;
                            Result.Companion companion822222222222 = Result.INSTANCE;
                            Object m1202constructorimpl522222222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl522222222222);
                            if (m1205exceptionOrNullimpl != null) {
                                HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                                HyperLogger companion11 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb7 = new StringBuilder();
                                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace5, str8);
                                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                if (stackTraceElement5 == null || (className2 = stackTraceElement5.getClassName()) == null) {
                                    obj3 = m1202constructorimpl522222222222;
                                } else {
                                    obj3 = m1202constructorimpl522222222222;
                                    String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    str18 = substringAfterLast$default3;
                                }
                                String canonicalName5 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? null : cls4.getCanonicalName();
                                str18 = canonicalName5 == null ? "N/A" : canonicalName5;
                                objectRef5.element = str18;
                                Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                                if (matcher6.find()) {
                                    ?? replaceAll5 = matcher6.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                                    objectRef5.element = replaceAll5;
                                }
                                if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str19 = (String) objectRef5.element;
                                } else {
                                    str19 = ((String) objectRef5.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str19, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb7.append(str19);
                                sb7.append(str7);
                                String str31 = "customApiCall() onFailure() called with " + m1205exceptionOrNullimpl;
                                if (str31 == null) {
                                    str31 = str;
                                }
                                sb7.append(str31);
                                sb7.append(' ');
                                sb7.append("");
                                companion11.log(level3, sb7.toString());
                                CoreExtsKt.isRelease();
                                try {
                                    Result.Companion companion12 = Result.INSTANCE;
                                    Object invoke3 = Class.forName(str6).getMethod(str10, new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke3, str9);
                                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                } catch (Throwable th24) {
                                    Result.Companion companion13 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th24));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                    m1202constructorimpl2 = "";
                                }
                                String str32 = (String) m1202constructorimpl2;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(str32, str3);
                                    if (StringsKt.contains$default((CharSequence) str32, charSequence, false, 2, (Object) null)) {
                                        Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str8);
                                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                        if (stackTraceElement6 == null || (className = stackTraceElement6.getClassName()) == null) {
                                            obj4 = null;
                                        } else {
                                            obj4 = null;
                                            String substringAfterLast$default4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            if (substringAfterLast$default4 != null) {
                                                canonicalName3 = substringAfterLast$default4;
                                                objectRef6.element = canonicalName3;
                                                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                                if (matcher3.find()) {
                                                    ?? replaceAll6 = matcher3.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(replaceAll6, "replaceAll(\"\")");
                                                    objectRef6.element = replaceAll6;
                                                }
                                                if (((String) objectRef6.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                                    str20 = (String) objectRef6.element;
                                                } else {
                                                    str20 = ((String) objectRef6.element).substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str20, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb8 = new StringBuilder();
                                                String str33 = "customApiCall() onFailure() called with " + m1205exceptionOrNullimpl;
                                                sb8.append(str33 != null ? str : str33);
                                                sb8.append(' ');
                                                sb8.append("");
                                                Log.println(6, str20, sb8.toString());
                                            }
                                        }
                                        canonicalName3 = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? obj4 : cls3.getCanonicalName();
                                        if (canonicalName3 == 0) {
                                            canonicalName3 = "N/A";
                                        }
                                        objectRef6.element = canonicalName3;
                                        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                        if (matcher3.find()) {
                                        }
                                        if (((String) objectRef6.element).length() > 23) {
                                        }
                                        str20 = (String) objectRef6.element;
                                        StringBuilder sb82 = new StringBuilder();
                                        String str332 = "customApiCall() onFailure() called with " + m1205exceptionOrNullimpl;
                                        sb82.append(str332 != null ? str : str332);
                                        sb82.append(' ');
                                        sb82.append("");
                                        Log.println(6, str20, sb82.toString());
                                    }
                                }
                            } else {
                                obj3 = m1202constructorimpl522222222222;
                            }
                            return Result.m1201boximpl(obj3);
                        }
                    }
                } catch (Throwable th25) {
                    th = th25;
                    coroutineScope4 = coroutineScope3;
                    coroutineScope = coroutineScope4;
                    str9 = str4;
                    str10 = str5;
                    str7 = str15;
                    Result.Companion companion8222222222222 = Result.INSTANCE;
                    Object m1202constructorimpl5222222222222 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl5222222222222);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Result.m1201boximpl(obj3);
                }
            }
            coroutineScope4 = coroutineScope3;
            if (coroutineScope2 != null) {
            }
            if (substringAfterLast$default == 0) {
            }
            objectRef.element = substringAfterLast$default;
            matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher4.find()) {
            }
            if (((String) objectRef.element).length() > 23) {
            }
            str21 = (String) objectRef.element;
            sb42.append(str21);
            str7 = str15;
            sb42.append(str7);
            str22 = "customApiCall() finished with success for url = [" + str2 + "] and attemptsCount = [" + intRef.element + ']';
            if (str22 == null) {
            }
            sb42.append(str22);
            sb42.append(' ');
            sb42.append("");
            companion72.log(level22, sb42.toString());
            if (CoreExtsKt.isRelease()) {
            }
            return Result.m1201boximpl(obj5);
        } while (DelayKt.delay(1000L, networkRepo$customApiCall$2) != obj2);
        return obj2;
    }
}
