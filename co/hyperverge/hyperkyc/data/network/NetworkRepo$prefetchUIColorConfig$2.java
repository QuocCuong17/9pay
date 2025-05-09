package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
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
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchUIColorConfig$2", f = "NetworkRepo.kt", i = {0}, l = {574}, m = "invokeSuspend", n = {"$this$onIO"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$prefetchUIColorConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSUIConfig>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ File $cacheDir;
    final /* synthetic */ String $workflowId;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$prefetchUIColorConfig$2(String str, File file, String str2, Continuation<? super NetworkRepo$prefetchUIColorConfig$2> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$cacheDir = file;
        this.$workflowId = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$prefetchUIColorConfig$2 networkRepo$prefetchUIColorConfig$2 = new NetworkRepo$prefetchUIColorConfig$2(this.$appId, this.$cacheDir, this.$workflowId, continuation);
        networkRepo$prefetchUIColorConfig$2.L$0 = obj;
        return networkRepo$prefetchUIColorConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSUIConfig> continuation) {
        return ((NetworkRepo$prefetchUIColorConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:153:0x01ce, code lost:
    
        if (r4 != 0) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x009a, code lost:
    
        if (r10 != null) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x038f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0480  */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v5, types: [T] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v13, types: [T] */
    /* JADX WARN: Type inference failed for: r2v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v25, types: [T] */
    /* JADX WARN: Type inference failed for: r4v34, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r4v41, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v51, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r6v12, types: [T] */
    /* JADX WARN: Type inference failed for: r6v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Object m1202constructorimpl;
        NetworkRepo$prefetchUIColorConfig$2 networkRepo$prefetchUIColorConfig$2;
        ?? r4;
        String str7;
        String str8;
        Class<?> cls;
        String className;
        String str9;
        File file;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        char c;
        Object callUIConfigFetchAPI;
        Class<?> cls2;
        String className2;
        Object m1202constructorimpl2;
        Object obj2;
        Object obj3;
        ?? canonicalName;
        Class<?> cls3;
        String str15;
        String str16;
        int i;
        String str17;
        Object m1202constructorimpl3;
        ?? canonicalName2;
        Class<?> cls4;
        Matcher matcher;
        String str18;
        String className3;
        String className4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            String str19 = this.$appId;
            File file2 = this.$cacheDir;
            String str20 = this.$workflowId;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str = "Throwable().stackTrace";
                str2 = "getInitialApplication";
                str3 = "android.app.AppGlobals";
            } else {
                str = "Throwable().stackTrace";
                str2 = "getInitialApplication";
                str3 = "android.app.AppGlobals";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str4 = substringAfterLast$default;
            }
            String canonicalName3 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
            str4 = canonicalName3 == null ? "N/A" : canonicalName3;
            objectRef.element = str4;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str5 = (String) objectRef.element;
            } else {
                str5 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str5);
            sb.append(" - ");
            String str21 = "prefetchUIColorConfig() called with appId = " + str19 + ", cacheDir = " + file2 + ", workflowId = " + str20;
            if (str21 == null) {
                str21 = "null ";
            }
            sb.append(str21);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                networkRepo$prefetchUIColorConfig$2 = this;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str6 = str2;
                    try {
                        Object invoke = Class.forName(str3).getMethod(str6, new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        networkRepo$prefetchUIColorConfig$2 = this;
                        str9 = networkRepo$prefetchUIColorConfig$2.$appId;
                        file = networkRepo$prefetchUIColorConfig$2.$cacheDir;
                        str10 = networkRepo$prefetchUIColorConfig$2.$workflowId;
                        Result.Companion companion4 = Result.INSTANCE;
                        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                        Integer boxInt = Boxing.boxInt(3600);
                        networkRepo$prefetchUIColorConfig$2.L$0 = coroutineScope;
                        networkRepo$prefetchUIColorConfig$2.label = 1;
                        str14 = str;
                        c = FilenameUtils.EXTENSION_SEPARATOR;
                        str11 = "replaceAll(\"\")";
                        str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str13 = "";
                        try {
                            callUIConfigFetchAPI = networkRepo.callUIConfigFetchAPI(str9, file, str10, (r16 & 8) != 0 ? null : boxInt, (r16 & 16) != 0 ? null : null, this);
                            if (callUIConfigFetchAPI != coroutine_suspended) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            coroutineScope = coroutineScope;
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            CoroutineScope coroutineScope2 = coroutineScope;
                            obj2 = m1202constructorimpl2;
                            if (Result.m1205exceptionOrNullimpl(obj2) != null) {
                            }
                            obj3 = null;
                            if (!Result.m1208isFailureimpl(obj2)) {
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str6 = str2;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str2 = str6;
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    str2 = str6;
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        String str22 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str22);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = str22;
                        } else {
                            str = str22;
                            r4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r4 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                        if (r4 == 0) {
                            r4 = "N/A";
                        }
                        objectRef2.element = r4;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher3.find()) {
                            ?? replaceAll2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str7 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                            StringBuilder sb2 = new StringBuilder();
                            str8 = "prefetchUIColorConfig() called with appId = " + str19 + ", cacheDir = " + file2 + ", workflowId = " + str20;
                            if (str8 == null) {
                                str8 = "null ";
                            }
                            sb2.append(str8);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str7, sb2.toString());
                            networkRepo$prefetchUIColorConfig$2 = this;
                        }
                        str7 = (String) objectRef2.element;
                        StringBuilder sb22 = new StringBuilder();
                        str8 = "prefetchUIColorConfig() called with appId = " + str19 + ", cacheDir = " + file2 + ", workflowId = " + str20;
                        if (str8 == null) {
                        }
                        sb22.append(str8);
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(3, str7, sb22.toString());
                        networkRepo$prefetchUIColorConfig$2 = this;
                    }
                }
                networkRepo$prefetchUIColorConfig$2 = this;
            }
            str9 = networkRepo$prefetchUIColorConfig$2.$appId;
            file = networkRepo$prefetchUIColorConfig$2.$cacheDir;
            str10 = networkRepo$prefetchUIColorConfig$2.$workflowId;
            try {
                Result.Companion companion42 = Result.INSTANCE;
                NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
                Integer boxInt2 = Boxing.boxInt(3600);
                networkRepo$prefetchUIColorConfig$2.L$0 = coroutineScope;
                networkRepo$prefetchUIColorConfig$2.label = 1;
                str14 = str;
                c = FilenameUtils.EXTENSION_SEPARATOR;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str13 = "";
                callUIConfigFetchAPI = networkRepo2.callUIConfigFetchAPI(str9, file, str10, (r16 & 8) != 0 ? null : boxInt2, (r16 & 16) != 0 ? null : null, this);
                if (callUIConfigFetchAPI != coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope;
            } catch (Throwable th4) {
                th = th4;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str13 = "";
                str14 = str;
                c = FilenameUtils.EXTENSION_SEPARATOR;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                callUIConfigFetchAPI = obj;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str2 = "getInitialApplication";
                str3 = "android.app.AppGlobals";
                c = FilenameUtils.EXTENSION_SEPARATOR;
                str14 = "Throwable().stackTrace";
                str13 = "";
            } catch (Throwable th5) {
                th = th5;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str2 = "getInitialApplication";
                str3 = "android.app.AppGlobals";
                c = FilenameUtils.EXTENSION_SEPARATOR;
                str14 = "Throwable().stackTrace";
                str13 = "";
                Result.Companion companion52 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                CoroutineScope coroutineScope22 = coroutineScope;
                obj2 = m1202constructorimpl2;
                if (Result.m1205exceptionOrNullimpl(obj2) != null) {
                }
                obj3 = null;
                if (!Result.m1208isFailureimpl(obj2)) {
                }
            }
        }
        try {
            m1202constructorimpl2 = Result.m1202constructorimpl((HSUIConfig) callUIConfigFetchAPI);
        } catch (Throwable th6) {
            th = th6;
            Result.Companion companion522 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            CoroutineScope coroutineScope222 = coroutineScope;
            obj2 = m1202constructorimpl2;
            if (Result.m1205exceptionOrNullimpl(obj2) != null) {
            }
            obj3 = null;
            if (!Result.m1208isFailureimpl(obj2)) {
            }
        }
        CoroutineScope coroutineScope2222 = coroutineScope;
        obj2 = m1202constructorimpl2;
        if (Result.m1205exceptionOrNullimpl(obj2) != null) {
            FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setUiConfig$hyperkyc_release(null);
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, str14);
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, c, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (coroutineScope2222 == null || (cls3 = coroutineScope2222.getClass()) == null) ? 0 : cls3.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef3.element = canonicalName;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher4.find()) {
                ?? replaceAll3 = matcher4.replaceAll(str13);
                str15 = str11;
                Intrinsics.checkNotNullExpressionValue(replaceAll3, str15);
                objectRef3.element = replaceAll3;
            } else {
                str15 = str11;
            }
            if (((String) objectRef3.element).length() > 23) {
                i = 26;
                if (Build.VERSION.SDK_INT >= 26) {
                    str16 = str12;
                } else {
                    str17 = ((String) objectRef3.element).substring(0, 23);
                    str16 = str12;
                    Intrinsics.checkNotNullExpressionValue(str17, str16);
                    sb3.append(str17);
                    sb3.append(" - ");
                    sb3.append("prefetchUIColorConfig() failed");
                    sb3.append(' ');
                    sb3.append(str13);
                    companion6.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName(str3).getMethod(str2, new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th7) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = str13;
                        }
                        String packageName3 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str14);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                                    obj3 = null;
                                } else {
                                    obj3 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, c, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        canonicalName2 = substringAfterLast$default2;
                                        objectRef4.element = canonicalName2;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                        if (matcher.find()) {
                                            ?? replaceAll4 = matcher.replaceAll(str13);
                                            Intrinsics.checkNotNullExpressionValue(replaceAll4, str15);
                                            objectRef4.element = replaceAll4;
                                        }
                                        if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= i) {
                                            str18 = (String) objectRef4.element;
                                        } else {
                                            str18 = ((String) objectRef4.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str18, str16);
                                        }
                                        Log.println(3, str18, "prefetchUIColorConfig() failed " + str13);
                                        return !Result.m1208isFailureimpl(obj2) ? obj3 : obj2;
                                    }
                                }
                                canonicalName2 = (coroutineScope2222 == null || (cls4 = coroutineScope2222.getClass()) == null) ? obj3 : cls4.getCanonicalName();
                                if (canonicalName2 == 0) {
                                    canonicalName2 = "N/A";
                                }
                                objectRef4.element = canonicalName2;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef4.element).length() > 23) {
                                }
                                str18 = (String) objectRef4.element;
                                Log.println(3, str18, "prefetchUIColorConfig() failed " + str13);
                                if (!Result.m1208isFailureimpl(obj2)) {
                                }
                            }
                        }
                    }
                }
            } else {
                str16 = str12;
                i = 26;
            }
            str17 = (String) objectRef3.element;
            sb3.append(str17);
            sb3.append(" - ");
            sb3.append("prefetchUIColorConfig() failed");
            sb3.append(' ');
            sb3.append(str13);
            companion6.log(level2, sb3.toString());
            if (!CoreExtsKt.isRelease()) {
            }
        }
        obj3 = null;
        if (!Result.m1208isFailureimpl(obj2)) {
        }
    }
}
