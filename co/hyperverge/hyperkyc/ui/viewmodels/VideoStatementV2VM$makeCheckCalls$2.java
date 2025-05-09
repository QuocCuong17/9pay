package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.apache.commons.io.FilenameUtils;

/* compiled from: VideoStatementV2VM.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeCheckCalls$2", f = "VideoStatementV2VM.kt", i = {}, l = {365}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class VideoStatementV2VM$makeCheckCalls$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Unit>>, Object> {
    final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VideoStatementV2VM$makeCheckCalls$2(VideoStatementV2VM videoStatementV2VM, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super VideoStatementV2VM$makeCheckCalls$2> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2VM;
        this.$finishWithErrorCallback = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoStatementV2VM$makeCheckCalls$2 videoStatementV2VM$makeCheckCalls$2 = new VideoStatementV2VM$makeCheckCalls$2(this.this$0, this.$finishWithErrorCallback, continuation);
        videoStatementV2VM$makeCheckCalls$2.L$0 = obj;
        return videoStatementV2VM$makeCheckCalls$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Unit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Unit>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Unit>> continuation) {
        return ((VideoStatementV2VM$makeCheckCalls$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x021a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v18, types: [T] */
    /* JADX WARN: Type inference failed for: r4v26, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r7v10, types: [T] */
    /* JADX WARN: Type inference failed for: r7v27, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v29, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        ?? canonicalName2;
        Class<?> cls2;
        String str2;
        int i;
        String className;
        Deferred async$default;
        Deferred async$default2;
        Deferred async$default3;
        String className2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 != 0) {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
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
        sb.append("makeCheckCalls() called");
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? 0 : cls2.getCanonicalName();
                        if (canonicalName2 == 0) {
                            canonicalName2 = "N/A";
                        }
                    }
                    objectRef2.element = canonicalName2;
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
                    i = 3;
                    Log.println(3, str2, "makeCheckCalls() called ");
                    async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2VM$makeCheckCalls$2$livenessCall$1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
                    async$default2 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2VM$makeCheckCalls$2$faceMatchCall$1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
                    async$default3 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2VM$makeCheckCalls$2$sttCall$1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
                    Deferred[] deferredArr = new Deferred[i];
                    deferredArr[0] = async$default;
                    deferredArr[1] = async$default2;
                    deferredArr[2] = async$default3;
                    this.label = 1;
                    Object awaitAll = AwaitKt.awaitAll(CollectionsKt.listOf((Object[]) deferredArr), this);
                    return awaitAll != coroutine_suspended ? coroutine_suspended : awaitAll;
                }
            }
        }
        i = 3;
        async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2VM$makeCheckCalls$2$livenessCall$1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
        async$default2 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2VM$makeCheckCalls$2$faceMatchCall$1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
        async$default3 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2VM$makeCheckCalls$2$sttCall$1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
        Deferred[] deferredArr2 = new Deferred[i];
        deferredArr2[0] = async$default;
        deferredArr2[1] = async$default2;
        deferredArr2[2] = async$default3;
        this.label = 1;
        Object awaitAll2 = AwaitKt.awaitAll(CollectionsKt.listOf((Object[]) deferredArr2), this);
        if (awaitAll2 != coroutine_suspended) {
        }
    }
}
