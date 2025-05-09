package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.utils.HSStateHandler;
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
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$storeUIConfig$2", f = "HKMainActivity.kt", i = {0}, l = {376}, m = "invokeSuspend", n = {"$this$onIO"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class HKMainActivity$storeUIConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$storeUIConfig$2(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$storeUIConfig$2> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$storeUIConfig$2 hKMainActivity$storeUIConfig$2 = new HKMainActivity$storeUIConfig$2(this.this$0, continuation);
        hKMainActivity$storeUIConfig$2.L$0 = obj;
        return hKMainActivity$storeUIConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$storeUIConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0235  */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [T] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v21, types: [kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r9v10, types: [T] */
    /* JADX WARN: Type inference failed for: r9v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v20, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m1202constructorimpl;
        ?? r2;
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl2;
        String str2;
        Class<?> cls2;
        Matcher matcher;
        String str3;
        String className;
        String className2;
        HSUIConfig hSUIConfig;
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            r2 = i;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ?? r22 = (CoroutineScope) this.L$0;
            if (!this.this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                HKMainActivity hKMainActivity = this.this$0;
                Result.Companion companion2 = Result.INSTANCE;
                HSStateHandler hsStateHandler = hKMainActivity.getMainVM().getHsStateHandler();
                hSUIConfig = hKMainActivity.uiColorConfigData;
                if (hSUIConfig == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("uiColorConfigData");
                    hSUIConfig = null;
                }
                this.L$0 = r22;
                this.label = 1;
                Object m432storeUIConfiggIAlus$hyperkyc_release = hsStateHandler.m432storeUIConfiggIAlus$hyperkyc_release(hSUIConfig, this);
                if (m432storeUIConfiggIAlus$hyperkyc_release == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = m432storeUIConfiggIAlus$hyperkyc_release;
                i = r22;
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ?? r23 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        obj2 = ((Result) obj).getValue();
        i = r23;
        m1202constructorimpl = Result.m1202constructorimpl(Result.m1201boximpl(obj2));
        r2 = i;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r11 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (r2 == 0 || (cls = r2.getClass()) == null) ? 0 : cls.getCanonicalName();
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
            String str4 = "Error storing uiConfig state: " + m1205exceptionOrNullimpl.getMessage();
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
            sb.append(' ');
            sb.append("");
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
                        str2 = null;
                    } else {
                        str2 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            r11 = substringAfterLast$default;
                            objectRef2.element = r11;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                                ?? replaceAll2 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                objectRef2.element = replaceAll2;
                            }
                            if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                str3 = (String) objectRef2.element;
                            } else {
                                str3 = ((String) objectRef2.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String str5 = "Error storing uiConfig state: " + m1205exceptionOrNullimpl.getMessage();
                            sb2.append(str5 != null ? str5 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(6, str3, sb2.toString());
                        }
                    }
                    String canonicalName2 = (r2 == 0 || (cls2 = r2.getClass()) == null) ? str2 : cls2.getCanonicalName();
                    if (canonicalName2 != null) {
                        r11 = canonicalName2;
                    }
                    objectRef2.element = r11;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef2.element).length() > 23) {
                    }
                    str3 = (String) objectRef2.element;
                    StringBuilder sb22 = new StringBuilder();
                    String str52 = "Error storing uiConfig state: " + m1205exceptionOrNullimpl.getMessage();
                    sb22.append(str52 != null ? str52 : "null ");
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(6, str3, sb22.toString());
                }
            }
        }
        return Unit.INSTANCE;
    }
}
