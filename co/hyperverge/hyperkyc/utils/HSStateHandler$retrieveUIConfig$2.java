package co.hyperverge.hyperkyc.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.gson.Gson;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HSStateHandler.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.HSStateHandler$retrieveUIConfig$2", f = "HSStateHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HSStateHandler$retrieveUIConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSUIConfig>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HSStateHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HSStateHandler$retrieveUIConfig$2(HSStateHandler hSStateHandler, Continuation<? super HSStateHandler$retrieveUIConfig$2> continuation) {
        super(2, continuation);
        this.this$0 = hSStateHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HSStateHandler$retrieveUIConfig$2 hSStateHandler$retrieveUIConfig$2 = new HSStateHandler$retrieveUIConfig$2(this.this$0, continuation);
        hSStateHandler$retrieveUIConfig$2.L$0 = obj;
        return hSStateHandler$retrieveUIConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSUIConfig> continuation) {
        return ((HSStateHandler$retrieveUIConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:5|(2:(1:154)(1:151)|(1:153))|11|(1:13)|14|(1:147)(1:18)|19|(6:112|113|114|(1:116)|117|(7:119|(7:121|(2:(1:143)(1:140)|(1:142))|127|(1:129)|130|(1:136)(1:134)|135)|22|23|24|25|(2:27|28)(21:30|(1:108)(1:34)|(1:43)(1:39)|(1:41)(1:42)|44|(1:46)|47|(1:107)(1:51)|52|(1:54)(1:106)|55|(1:57)(1:105)|58|59|60|61|(1:63)|64|(2:66|(13:68|(1:99)(2:72|(9:74|75|(1:77)|78|(1:90)(1:82)|83|(1:85)(1:89)|(1:87)|88))|(1:98)(1:94)|(1:96)(1:97)|75|(0)|78|(1:80)|90|83|(0)(0)|(0)|88))|100|101)))|21|22|23|24|25|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01ed, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01ee, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x023b, code lost:
    
        if (r7 != null) goto L94;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03ba  */
    /* JADX WARN: Type inference failed for: r1v20, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v14, types: [T] */
    /* JADX WARN: Type inference failed for: r5v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v10, types: [T] */
    /* JADX WARN: Type inference failed for: r7v23, types: [T] */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v38, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v41 */
    /* JADX WARN: Type inference failed for: r7v42 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11, types: [T] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        File uiConfigFile;
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        CharSequence charSequence;
        Object obj2;
        ?? canonicalName2;
        Class<?> cls2;
        String str2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        String str3;
        String str4;
        ?? r7;
        String str5;
        String str6;
        Object m1202constructorimpl2;
        String str7;
        ?? r9;
        Class<?> cls3;
        Matcher matcher;
        String str8;
        String localizedMessage;
        String className2;
        Class<?> cls4;
        String className3;
        File uiConfigFile2;
        String className4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            uiConfigFile = this.this$0.getUiConfigFile();
            if (!uiConfigFile.exists()) {
                throw new Exception("UI config file not found");
            }
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
            sb.append("retrieveUIConfig() called");
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
                    charSequence = "co.hyperverge";
                    obj2 = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                            str2 = (String) objectRef2.element;
                        } else {
                            str2 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str2, "retrieveUIConfig() called ");
                    }
                    HSStateHandler hSStateHandler = this.this$0;
                    Result.Companion companion4 = Result.INSTANCE;
                    Gson gson = hSStateHandler.getGson();
                    uiConfigFile2 = hSStateHandler.getUiConfigFile();
                    HSUIConfig hSUIConfig = (HSUIConfig) gson.fromJson(FilesKt.readText$default(uiConfigFile2, null, 1, null), HSUIConfig.class);
                    hSStateHandler.deleteStateDir();
                    Object m1202constructorimpl3 = Result.m1202constructorimpl(hSUIConfig);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl != null) {
                        return (HSUIConfig) m1202constructorimpl3;
                    }
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                        str3 = "packageName";
                        str4 = "Throwable().stackTrace";
                    } else {
                        str3 = "packageName";
                        str4 = "Throwable().stackTrace";
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        r7 = substringAfterLast$default;
                    }
                    String canonicalName3 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? null : cls4.getCanonicalName();
                    r7 = canonicalName3 == null ? obj2 : canonicalName3;
                    objectRef3.element = r7;
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
                    sb2.append("unable to retrieve UI config from file");
                    sb2.append(' ');
                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage2 != null) {
                        str6 = '\n' + localizedMessage2;
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
                    } catch (Throwable th2) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String str10 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str10, str3);
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
                                    r9 = substringAfterLast$default2;
                                    objectRef4.element = r9;
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
                                    sb3.append("unable to retrieve UI config from file");
                                    sb3.append(' ');
                                    localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str7;
                                    if (localizedMessage != null) {
                                        str9 = '\n' + localizedMessage;
                                    }
                                    sb3.append(str9);
                                    Log.println(6, str8, sb3.toString());
                                }
                            }
                            String canonicalName4 = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? str7 : cls3.getCanonicalName();
                            r9 = canonicalName4 == null ? obj2 : canonicalName4;
                            objectRef4.element = r9;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                            if (matcher.find()) {
                            }
                            if (((String) objectRef4.element).length() > 23) {
                            }
                            str8 = (String) objectRef4.element;
                            StringBuilder sb32 = new StringBuilder();
                            sb32.append("unable to retrieve UI config from file");
                            sb32.append(' ');
                            if (m1205exceptionOrNullimpl == null) {
                            }
                            if (localizedMessage != null) {
                            }
                            sb32.append(str9);
                            Log.println(6, str8, sb32.toString());
                        }
                    }
                    throw new Exception("Unable to retrieve UI config from file");
                }
            }
            charSequence = "co.hyperverge";
            obj2 = "N/A";
            HSStateHandler hSStateHandler2 = this.this$0;
            Result.Companion companion42 = Result.INSTANCE;
            Gson gson2 = hSStateHandler2.getGson();
            uiConfigFile2 = hSStateHandler2.getUiConfigFile();
            HSUIConfig hSUIConfig2 = (HSUIConfig) gson2.fromJson(FilesKt.readText$default(uiConfigFile2, null, 1, null), HSUIConfig.class);
            hSStateHandler2.deleteStateDir();
            Object m1202constructorimpl32 = Result.m1202constructorimpl(hSUIConfig2);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
            if (m1205exceptionOrNullimpl != null) {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
