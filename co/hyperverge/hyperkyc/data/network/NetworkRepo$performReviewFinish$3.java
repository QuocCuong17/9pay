package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.FinishReview;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.gson.Gson;
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
import kotlin.jvm.functions.Function1;
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
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$performReviewFinish$3", f = "NetworkRepo.kt", i = {}, l = {634}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkRepo$performReviewFinish$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Response>>, Object> {
    final /* synthetic */ FinishReview $finishReview;
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NetworkRepo$performReviewFinish$3(String str, Map<String, String> map, FinishReview finishReview, Function1<? super Throwable, Unit> function1, Continuation<? super NetworkRepo$performReviewFinish$3> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$headers = map;
        this.$finishReview = finishReview;
        this.$onFailure = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$performReviewFinish$3 networkRepo$performReviewFinish$3 = new NetworkRepo$performReviewFinish$3(this.$url, this.$headers, this.$finishReview, this.$onFailure, continuation);
        networkRepo$performReviewFinish$3.L$0 = obj;
        return networkRepo$performReviewFinish$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Response>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Response>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Response>> continuation) {
        return ((NetworkRepo$performReviewFinish$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0185, code lost:
    
        if (r3 != 0) goto L64;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10, types: [T] */
    /* JADX WARN: Type inference failed for: r11v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r3v17, types: [T] */
    /* JADX WARN: Type inference failed for: r3v26, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r7v10, types: [T, java.lang.Object, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        Object obj2;
        ?? r3;
        String str2;
        Class<?> cls2;
        String className;
        Object apiPost$default;
        String className2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            FinishReview finishReview = this.$finishReview;
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
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
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
            String str3 = "performReviewFinish() called with: finishReview = " + finishReview + ", headers = " + map;
            if (str3 == null) {
                str3 = "null ";
            }
            sb.append(str3);
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
                            obj2 = null;
                        } else {
                            obj2 = null;
                            r3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r3 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? obj2 : cls2.getCanonicalName();
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
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str2 = (String) objectRef2.element;
                        } else {
                            str2 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str4 = "performReviewFinish() called with: finishReview = " + finishReview + ", headers = " + map;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str2, sb2.toString());
                    }
                }
            }
            this.label = 1;
            apiPost$default = NetworkHelpersKt.apiPost$default(this.$url, this.$headers, new Gson().toJson(this.$finishReview), null, this.$onFailure, this, 8, null);
            if (apiPost$default == coroutine_suspended) {
                return coroutine_suspended;
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
