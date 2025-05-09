package co.hyperverge.hyperkyc.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
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
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HSStateHandler.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.HSStateHandler$storeUIConfig$2", f = "HSStateHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HSStateHandler$storeUIConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ HSUIConfig $data;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HSStateHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HSStateHandler$storeUIConfig$2(HSStateHandler hSStateHandler, HSUIConfig hSUIConfig, Continuation<? super HSStateHandler$storeUIConfig$2> continuation) {
        super(2, continuation);
        this.this$0 = hSStateHandler;
        this.$data = hSUIConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HSStateHandler$storeUIConfig$2 hSStateHandler$storeUIConfig$2 = new HSStateHandler$storeUIConfig$2(this.this$0, this.$data, continuation);
        hSStateHandler$storeUIConfig$2.L$0 = obj;
        return hSStateHandler$storeUIConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Unit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Unit>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((HSStateHandler$storeUIConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:3|(2:(1:158)(1:155)|(1:157))|9|(1:11)|12|(1:151)(1:16)|17|(1:19)(9:109|110|111|112|113|114|(1:116)|117|(9:119|(7:121|(2:(1:143)(1:140)|(1:142))|127|(1:129)|130|(1:136)(1:134)|135)|21|22|23|24|(19:26|(1:104)(1:30)|(1:39)(1:35)|(1:37)(1:38)|40|(1:42)|43|(1:103)(1:47)|48|(1:50)(1:102)|51|(1:53)(1:101)|54|55|56|57|(1:59)|60|(2:62|(13:64|(1:94)(2:68|(9:70|71|(1:73)|74|(1:85)(1:78)|79|(1:81)|(1:83)|84))|(1:93)(1:89)|(1:91)(1:92)|71|(0)|74|(1:76)|85|79|(0)|(0)|84)))(1:105)|95|96)(1:144))|20|21|22|23|24|(0)(0)|95|96) */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0201, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0202, code lost:
    
        r3 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x024d, code lost:
    
        if (r8 != null) goto L97;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03cd  */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17, types: [T] */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r1v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v14, types: [T] */
    /* JADX WARN: Type inference failed for: r6v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r8v10, types: [T] */
    /* JADX WARN: Type inference failed for: r8v23, types: [T] */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v36, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v38, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
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
        CharSequence charSequence;
        String str2;
        ?? canonicalName2;
        Class<?> cls2;
        String str3;
        String className;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        String str4;
        ?? r8;
        String str5;
        String str6;
        Object m1202constructorimpl2;
        String str7;
        ?? r10;
        Class<?> cls3;
        Matcher matcher;
        String str8;
        String className2;
        Class<?> cls4;
        String className3;
        File uiConfigFile;
        String className4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        String jsonData = this.this$0.getGson().toJson(this.$data, HSUIConfig.class);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
            canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
            if (canonicalName == 0) {
                canonicalName = "N/A";
            }
        }
        objectRef.element = canonicalName;
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
        String str9 = "";
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
        sb.append("storeUIConfig() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            charSequence = "co.hyperverge";
            obj2 = "N/A";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                obj2 = "N/A";
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (!CoreExtsKt.isDebug()) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                obj2 = "N/A";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (!CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                charSequence = "co.hyperverge";
                str2 = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? 0 : cls2.getCanonicalName();
                        if (canonicalName2 == 0) {
                            canonicalName2 = obj2;
                        }
                    }
                    objectRef2.element = canonicalName2;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher3.find()) {
                        ?? replaceAll2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                        objectRef2.element = replaceAll2;
                    }
                    if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str3 = (String) objectRef2.element;
                    } else {
                        str3 = ((String) objectRef2.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str3, "storeUIConfig() called ");
                }
                HSStateHandler hSStateHandler = this.this$0;
                Result.Companion companion4 = Result.INSTANCE;
                hSStateHandler.deleteStateDir();
                uiConfigFile = hSStateHandler.getUiConfigFile();
                Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                byte[] bytes = jsonData.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                FilesKt.writeBytes(uiConfigFile, bytes);
                Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                Object obj4 = m1202constructorimpl3;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                        obj3 = obj4;
                        str4 = "Throwable().stackTrace";
                    } else {
                        obj3 = obj4;
                        str4 = "Throwable().stackTrace";
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        r8 = substringAfterLast$default;
                    }
                    String canonicalName3 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? null : cls4.getCanonicalName();
                    r8 = canonicalName3 == null ? obj2 : canonicalName3;
                    objectRef3.element = r8;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                    if (matcher4.find()) {
                        ?? replaceAll3 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                        objectRef3.element = replaceAll3;
                    }
                    if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str5 = (String) objectRef3.element;
                    } else {
                        str5 = ((String) objectRef3.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(str5);
                    sb2.append(" - ");
                    sb2.append("failed saving UI config to file");
                    sb2.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str6 = '\n' + localizedMessage;
                    } else {
                        str6 = "";
                    }
                    sb2.append(str6);
                    companion5.log(level2, sb2.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String str10 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str10, str2);
                        if (StringsKt.contains$default((CharSequence) str10, charSequence, false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str4);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str7 = null;
                            } else {
                                str7 = null;
                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default2 != null) {
                                    r10 = substringAfterLast$default2;
                                    objectRef4.element = r10;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher.find()) {
                                        ?? replaceAll4 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                        str8 = (String) objectRef4.element;
                                    } else {
                                        str8 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("failed saving UI config to file");
                                    sb3.append(' ');
                                    if (m1205exceptionOrNullimpl != null) {
                                        str7 = m1205exceptionOrNullimpl.getLocalizedMessage();
                                    }
                                    if (str7 != null) {
                                        str9 = '\n' + str7;
                                    }
                                    sb3.append(str9);
                                    Log.println(6, str8, sb3.toString());
                                }
                            }
                            String canonicalName4 = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? str7 : cls3.getCanonicalName();
                            r10 = canonicalName4 == null ? obj2 : canonicalName4;
                            objectRef4.element = r10;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                            if (matcher.find()) {
                            }
                            if (((String) objectRef4.element).length() > 23) {
                            }
                            str8 = (String) objectRef4.element;
                            StringBuilder sb32 = new StringBuilder();
                            sb32.append("failed saving UI config to file");
                            sb32.append(' ');
                            if (m1205exceptionOrNullimpl != null) {
                            }
                            if (str7 != null) {
                            }
                            sb32.append(str9);
                            Log.println(6, str8, sb32.toString());
                        }
                    }
                } else {
                    obj3 = obj4;
                }
                return Result.m1201boximpl(obj3);
            }
            charSequence = "co.hyperverge";
        }
        str2 = "packageName";
        HSStateHandler hSStateHandler2 = this.this$0;
        Result.Companion companion42 = Result.INSTANCE;
        hSStateHandler2.deleteStateDir();
        uiConfigFile = hSStateHandler2.getUiConfigFile();
        Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
        byte[] bytes2 = jsonData.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes(uiConfigFile, bytes2);
        Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
        Object obj42 = m1202constructorimpl32;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj42);
        if (m1205exceptionOrNullimpl == null) {
        }
        return Result.m1201boximpl(obj3);
    }
}
