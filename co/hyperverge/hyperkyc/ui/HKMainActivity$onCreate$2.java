package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.databinding.HkActivityMainBinding;
import co.hyperverge.hyperkyc.utils.HSStateHandler;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.model.UIConfig;
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

/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$onCreate$2", f = "HKMainActivity.kt", i = {0}, l = {199}, m = "invokeSuspend", n = {"$this$invokeSuspend_u24lambda_u243"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class HKMainActivity$onCreate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$onCreate$2(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$onCreate$2> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKMainActivity$onCreate$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$onCreate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(1:(4:4|5|6|7)(2:87|88))(4:89|(3:91|92|(1:94)(1:95))|78|79)|8|9|10|(15:12|(2:(1:77)(1:74)|(1:76))|18|(1:20)|21|(1:70)(1:25)|26|(1:28)|29|30|31|32|(1:34)|35|(2:37|(12:39|(1:66)(2:43|(8:45|46|(1:48)|49|(1:58)(1:53)|54|(1:56)|57))|(1:65)(1:62)|(1:64)|46|(0)|49|(1:51)|58|54|(0)|57)))|78|79|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0073, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0074, code lost:
    
        r3 = r4;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0242  */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [T] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r4v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v10, types: [T] */
    /* JADX WARN: Type inference failed for: r8v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v20, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        HkActivityMainBinding hkActivityMainBinding;
        Object retrieveUIConfig$hyperkyc_release;
        HkActivityMainBinding hkActivityMainBinding2;
        HyperSnapSDK hyperSnapSDK;
        Context context;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
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
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HkActivityMainBinding binding$hyperkyc_release = this.this$0.getBinding$hyperkyc_release();
            HKMainActivity hKMainActivity = this.this$0;
            if (hKMainActivity.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    HyperSnapSDK hyperSnapSDK2 = HyperSnapSDK.getInstance();
                    Context applicationContext = hKMainActivity.getApplicationContext();
                    HSStateHandler hsStateHandler = hKMainActivity.getMainVM().getHsStateHandler();
                    this.L$0 = binding$hyperkyc_release;
                    this.L$1 = applicationContext;
                    this.L$2 = hyperSnapSDK2;
                    this.label = 1;
                    retrieveUIConfig$hyperkyc_release = hsStateHandler.retrieveUIConfig$hyperkyc_release(this);
                    if (retrieveUIConfig$hyperkyc_release == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hkActivityMainBinding2 = binding$hyperkyc_release;
                    hyperSnapSDK = hyperSnapSDK2;
                    context = applicationContext;
                } catch (Throwable th) {
                    th = th;
                    hkActivityMainBinding = binding$hyperkyc_release;
                    Result.Companion companion2 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    hkActivityMainBinding2 = hkActivityMainBinding;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        hyperSnapSDK = (HyperSnapSDK) this.L$2;
        context = (Context) this.L$1;
        hkActivityMainBinding = (HkActivityMainBinding) this.L$0;
        try {
            ResultKt.throwOnFailure(obj);
            hkActivityMainBinding2 = hkActivityMainBinding;
            retrieveUIConfig$hyperkyc_release = obj;
        } catch (Throwable th2) {
            th = th2;
            Result.Companion companion22 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            hkActivityMainBinding2 = hkActivityMainBinding;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
            if (m1205exceptionOrNullimpl != null) {
            }
            return Unit.INSTANCE;
        }
        hyperSnapSDK.setUiConfig(context, (UIConfig) retrieveUIConfig$hyperkyc_release);
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r10 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (hkActivityMainBinding2 == null || (cls = hkActivityMainBinding2.getClass()) == null) ? 0 : cls.getCanonicalName();
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
            String str4 = "Error retrieving uiConfig state: " + m1205exceptionOrNullimpl.getMessage();
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
            } catch (Throwable th3) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                            r10 = substringAfterLast$default;
                            objectRef2.element = r10;
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
                            String str5 = "Error retrieving uiConfig state: " + m1205exceptionOrNullimpl.getMessage();
                            sb2.append(str5 != null ? str5 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(6, str3, sb2.toString());
                        }
                    }
                    String canonicalName2 = (hkActivityMainBinding2 == null || (cls2 = hkActivityMainBinding2.getClass()) == null) ? str2 : cls2.getCanonicalName();
                    if (canonicalName2 != null) {
                        r10 = canonicalName2;
                    }
                    objectRef2.element = r10;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef2.element).length() > 23) {
                    }
                    str3 = (String) objectRef2.element;
                    StringBuilder sb22 = new StringBuilder();
                    String str52 = "Error retrieving uiConfig state: " + m1205exceptionOrNullimpl.getMessage();
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
