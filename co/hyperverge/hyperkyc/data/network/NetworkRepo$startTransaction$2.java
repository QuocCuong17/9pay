package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.StartTransaction;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.gson.Gson;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$startTransaction$2", f = "NetworkRepo.kt", i = {}, l = {654}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkRepo$startTransaction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Response>>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ StartTransaction $startTransactionData;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$startTransaction$2(String str, Map<String, String> map, StartTransaction startTransaction, Continuation<? super NetworkRepo$startTransaction$2> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$headers = map;
        this.$startTransactionData = startTransaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$startTransaction$2 networkRepo$startTransaction$2 = new NetworkRepo$startTransaction$2(this.$url, this.$headers, this.$startTransactionData, continuation);
        networkRepo$startTransaction$2.L$0 = obj;
        return networkRepo$startTransaction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Response>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Response>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Response>> continuation) {
        return ((NetworkRepo$startTransaction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x01c7, code lost:
    
        if (r1 != null) goto L71;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0272  */
    /* JADX WARN: Type inference failed for: r11v10, types: [T] */
    /* JADX WARN: Type inference failed for: r11v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v20, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6, types: [T] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r1v20, types: [T, java.lang.Object, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object obj2;
        Object m1202constructorimpl;
        String str2;
        String str3;
        ?? r13;
        Matcher matcher;
        String str4;
        Class<?> cls2;
        String className;
        Object apiPost$default;
        String className2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            StartTransaction startTransaction = this.$startTransactionData;
            Map<String, String> map = this.$headers;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef.element = canonicalName;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str = (String) objectRef.element;
            } else {
                str = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str);
            sb.append(" - ");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("startTransaction() called with: startTransactionData = ");
            sb2.append(startTransaction);
            sb2.append(", headers =  ");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (true) {
                obj2 = coroutine_suspended;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if (!Intrinsics.areEqual(next.getKey(), HyperKycConfig.APP_KEY)) {
                    linkedHashMap.put(next.getKey(), next.getValue());
                }
                coroutine_suspended = obj2;
            }
            sb2.append(linkedHashMap);
            String sb3 = sb2.toString();
            if (sb3 == null) {
                sb3 = "null ";
            }
            sb.append(sb3);
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
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str2 = null;
                        } else {
                            str2 = null;
                            str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        str3 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? str2 : cls2.getCanonicalName();
                        if (str3 == null) {
                            r13 = "N/A";
                            objectRef2.element = r13;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                                ?? replaceAll2 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                objectRef2.element = replaceAll2;
                            }
                            if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                str4 = (String) objectRef2.element;
                            } else {
                                str4 = ((String) objectRef2.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("startTransaction() called with: startTransactionData = ");
                            sb5.append(startTransaction);
                            sb5.append(", headers =  ");
                            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                if (!Intrinsics.areEqual(entry.getKey(), HyperKycConfig.APP_KEY)) {
                                    linkedHashMap2.put(entry.getKey(), entry.getValue());
                                }
                            }
                            sb5.append(linkedHashMap2);
                            String sb6 = sb5.toString();
                            sb4.append(sb6 != null ? sb6 : "null ");
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str4, sb4.toString());
                        }
                        r13 = str3;
                        objectRef2.element = r13;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher.find()) {
                        }
                        if (((String) objectRef2.element).length() > 23) {
                        }
                        str4 = (String) objectRef2.element;
                        StringBuilder sb42 = new StringBuilder();
                        StringBuilder sb52 = new StringBuilder();
                        sb52.append("startTransaction() called with: startTransactionData = ");
                        sb52.append(startTransaction);
                        sb52.append(", headers =  ");
                        LinkedHashMap linkedHashMap22 = new LinkedHashMap();
                        while (r6.hasNext()) {
                        }
                        sb52.append(linkedHashMap22);
                        String sb62 = sb52.toString();
                        sb42.append(sb62 != null ? sb62 : "null ");
                        sb42.append(' ');
                        sb42.append("");
                        Log.println(3, str4, sb42.toString());
                    }
                }
            }
            this.label = 1;
            apiPost$default = NetworkHelpersKt.apiPost$default(this.$url, this.$headers, new Gson().toJson(this.$startTransactionData), null, null, this, 24, null);
            if (apiPost$default == obj2) {
                return obj2;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            apiPost$default = ((Result) obj).getValue();
        }
        return Result.m1201boximpl(apiPost$default);
    }
}
