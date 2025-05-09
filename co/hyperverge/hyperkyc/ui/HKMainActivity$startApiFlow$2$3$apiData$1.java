package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
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

/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startApiFlow$2$3$apiData$1", f = "HKMainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HKMainActivity$startApiFlow$2$3$apiData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends HyperKycData.APIData>>, Object> {
    final /* synthetic */ Ref.BooleanRef $isNetworkError;
    final /* synthetic */ Response $response;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startApiFlow$2$3$apiData$1(Response response, Ref.BooleanRef booleanRef, Continuation<? super HKMainActivity$startApiFlow$2$3$apiData$1> continuation) {
        super(2, continuation);
        this.$response = response;
        this.$isNetworkError = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$startApiFlow$2$3$apiData$1 hKMainActivity$startApiFlow$2$3$apiData$1 = new HKMainActivity$startApiFlow$2$3$apiData$1(this.$response, this.$isNetworkError, continuation);
        hKMainActivity$startApiFlow$2$3$apiData$1.L$0 = obj;
        return hKMainActivity$startApiFlow$2$3$apiData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends HyperKycData.APIData>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<HyperKycData.APIData>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<HyperKycData.APIData>> continuation) {
        return ((HKMainActivity$startApiFlow$2$3$apiData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01fc  */
    /* JADX WARN: Type inference failed for: r10v10, types: [T] */
    /* JADX WARN: Type inference failed for: r10v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [T] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v10, types: [T, java.lang.Object, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m1202constructorimpl;
        ?? canonicalName;
        Class<?> cls;
        String str;
        String str2;
        Object m1202constructorimpl2;
        String str3;
        Class<?> cls2;
        Matcher matcher;
        String str4;
        String localizedMessage;
        String className;
        String className2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Response response = this.$response;
        try {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(HyperKycData.APIData.INSTANCE.from$hyperkyc_release(response));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Object obj2 = m1202constructorimpl;
        Ref.BooleanRef booleanRef = this.$isNetworkError;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r12 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef.element = canonicalName;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            String str5 = "";
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
            booleanRef.element = CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl);
            sb.append("Unable to create APIData from response");
            sb.append(' ');
            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str2 = '\n' + localizedMessage2;
            } else {
                str2 = "";
            }
            sb.append(str2);
            companion3.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            r12 = substringAfterLast$default;
                            objectRef2.element = r12;
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
                            StringBuilder sb2 = new StringBuilder();
                            booleanRef.element = CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl);
                            sb2.append("Unable to create APIData from response");
                            sb2.append(' ');
                            localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str3;
                            if (localizedMessage != null) {
                                str5 = '\n' + localizedMessage;
                            }
                            sb2.append(str5);
                            Log.println(6, str4, sb2.toString());
                        }
                    }
                    String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? str3 : cls2.getCanonicalName();
                    if (canonicalName2 != null) {
                        r12 = canonicalName2;
                    }
                    objectRef2.element = r12;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef2.element).length() > 23) {
                    }
                    str4 = (String) objectRef2.element;
                    StringBuilder sb22 = new StringBuilder();
                    booleanRef.element = CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl);
                    sb22.append("Unable to create APIData from response");
                    sb22.append(' ');
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    if (localizedMessage != null) {
                    }
                    sb22.append(str5);
                    Log.println(6, str4, sb22.toString());
                }
            }
        }
        return Result.m1201boximpl(obj2);
    }
}
