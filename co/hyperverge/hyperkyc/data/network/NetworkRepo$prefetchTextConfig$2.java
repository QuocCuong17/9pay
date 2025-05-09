package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.Map;
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
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u0018\u00010\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchTextConfig$2", f = "NetworkRepo.kt", i = {0}, l = {489}, m = "invokeSuspend", n = {"$this$onIO"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$prefetchTextConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ File $cacheDir;
    final /* synthetic */ String $languageCode;
    final /* synthetic */ String $source;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$prefetchTextConfig$2(String str, String str2, String str3, File file, Continuation<? super NetworkRepo$prefetchTextConfig$2> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$languageCode = str2;
        this.$source = str3;
        this.$cacheDir = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$prefetchTextConfig$2 networkRepo$prefetchTextConfig$2 = new NetworkRepo$prefetchTextConfig$2(this.$appId, this.$languageCode, this.$source, this.$cacheDir, continuation);
        networkRepo$prefetchTextConfig$2.L$0 = obj;
        return networkRepo$prefetchTextConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
        return ((NetworkRepo$prefetchTextConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:150:0x01dc, code lost:
    
        if (r4 != 0) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x009a, code lost:
    
        if (r11 != null) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02be A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x03ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0494  */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v14, types: [T] */
    /* JADX WARN: Type inference failed for: r1v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v18, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v25, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v7, types: [T] */
    /* JADX WARN: Type inference failed for: r5v37, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v50 */
    /* JADX WARN: Type inference failed for: r5v53, types: [T] */
    /* JADX WARN: Type inference failed for: r5v60, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v63 */
    /* JADX WARN: Type inference failed for: r5v65, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v67, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v68 */
    /* JADX WARN: Type inference failed for: r5v69 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        HyperLogger hyperLogger;
        String str2;
        String str3;
        String str4;
        String str5;
        Object m1202constructorimpl;
        char c;
        NetworkRepo$prefetchTextConfig$2 networkRepo$prefetchTextConfig$2;
        Object obj2;
        ?? r4;
        String str6;
        String str7;
        Class<?> cls;
        String className;
        String str8;
        String str9;
        String str10;
        File file;
        String str11;
        String str12;
        String str13;
        String str14;
        Object callTextConfigFetchAPI;
        Class<?> cls2;
        String className2;
        Object m1202constructorimpl2;
        Object obj3;
        ?? canonicalName;
        Class<?> cls3;
        String str15;
        String str16;
        boolean z;
        int i;
        int i2;
        String str17;
        Object m1202constructorimpl3;
        ?? canonicalName2;
        Class<?> cls4;
        String str18;
        String className3;
        String substringAfterLast$default;
        String className4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            String str19 = this.$appId;
            String str20 = this.$languageCode;
            String str21 = this.$source;
            File file2 = this.$cacheDir;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str = "Throwable().stackTrace";
                hyperLogger = companion;
                str2 = "android.app.AppGlobals";
            } else {
                str = "Throwable().stackTrace";
                hyperLogger = companion;
                str2 = "android.app.AppGlobals";
                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str3 = substringAfterLast$default2;
            }
            String canonicalName3 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
            str3 = canonicalName3 == null ? "N/A" : canonicalName3;
            objectRef.element = str3;
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str4 = (String) objectRef.element;
            } else {
                str4 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str4);
            sb.append(" - ");
            String str22 = "prefetchTextConfig() called with: appId = " + str19 + ", languageCode = " + str20 + ", source = " + str21 + ", cacheDir = " + file2;
            if (str22 == null) {
                str22 = "null ";
            }
            sb.append(str22);
            sb.append(' ');
            sb.append("");
            hyperLogger.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                networkRepo$prefetchTextConfig$2 = this;
                str5 = " - ";
                c = FilenameUtils.EXTENSION_SEPARATOR;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str5 = " - ";
                    try {
                        Object invoke = Class.forName(str2).getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
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
                        c = FilenameUtils.EXTENSION_SEPARATOR;
                        networkRepo$prefetchTextConfig$2 = this;
                        str8 = networkRepo$prefetchTextConfig$2.$appId;
                        str9 = networkRepo$prefetchTextConfig$2.$languageCode;
                        str10 = networkRepo$prefetchTextConfig$2.$source;
                        file = networkRepo$prefetchTextConfig$2.$cacheDir;
                        Result.Companion companion4 = Result.INSTANCE;
                        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                        Integer boxInt = Boxing.boxInt(3600);
                        networkRepo$prefetchTextConfig$2.L$0 = coroutineScope;
                        networkRepo$prefetchTextConfig$2.label = 1;
                        str13 = str;
                        c = FilenameUtils.EXTENSION_SEPARATOR;
                        str11 = "replaceAll(\"\")";
                        str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str14 = null;
                        try {
                            callTextConfigFetchAPI = networkRepo.callTextConfigFetchAPI(str8, str9, str10, file, (r18 & 16) != 0 ? null : boxInt, (r18 & 32) != 0 ? null : null, this);
                            if (callTextConfigFetchAPI != coroutine_suspended) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            coroutineScope = coroutineScope;
                            Result.Companion companion5 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            obj3 = m1202constructorimpl2;
                            if (Result.m1205exceptionOrNullimpl(obj3) != null) {
                            }
                            if (!Result.m1208isFailureimpl(obj3)) {
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str5 = " - ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        String str23 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str23);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = str23;
                            obj2 = null;
                            c = FilenameUtils.EXTENSION_SEPARATOR;
                        } else {
                            str = str23;
                            obj2 = null;
                            c = FilenameUtils.EXTENSION_SEPARATOR;
                            r4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r4 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? obj2 : cls.getCanonicalName();
                        if (r4 == 0) {
                            r4 = "N/A";
                        }
                        objectRef2.element = r4;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str6 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            StringBuilder sb2 = new StringBuilder();
                            str7 = "prefetchTextConfig() called with: appId = " + str19 + ", languageCode = " + str20 + ", source = " + str21 + ", cacheDir = " + file2;
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb2.append(str7);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str6, sb2.toString());
                            networkRepo$prefetchTextConfig$2 = this;
                        }
                        str6 = (String) objectRef2.element;
                        StringBuilder sb22 = new StringBuilder();
                        str7 = "prefetchTextConfig() called with: appId = " + str19 + ", languageCode = " + str20 + ", source = " + str21 + ", cacheDir = " + file2;
                        if (str7 == null) {
                        }
                        sb22.append(str7);
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(3, str6, sb22.toString());
                        networkRepo$prefetchTextConfig$2 = this;
                    }
                }
                c = FilenameUtils.EXTENSION_SEPARATOR;
                networkRepo$prefetchTextConfig$2 = this;
            }
            str8 = networkRepo$prefetchTextConfig$2.$appId;
            str9 = networkRepo$prefetchTextConfig$2.$languageCode;
            str10 = networkRepo$prefetchTextConfig$2.$source;
            file = networkRepo$prefetchTextConfig$2.$cacheDir;
            try {
                Result.Companion companion42 = Result.INSTANCE;
                NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
                Integer boxInt2 = Boxing.boxInt(3600);
                networkRepo$prefetchTextConfig$2.L$0 = coroutineScope;
                networkRepo$prefetchTextConfig$2.label = 1;
                str13 = str;
                c = FilenameUtils.EXTENSION_SEPARATOR;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str14 = null;
                callTextConfigFetchAPI = networkRepo2.callTextConfigFetchAPI(str8, str9, str10, file, (r18 & 16) != 0 ? null : boxInt2, (r18 & 32) != 0 ? null : null, this);
                if (callTextConfigFetchAPI != coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope;
            } catch (Throwable th4) {
                th = th4;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str13 = str;
                str14 = null;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                callTextConfigFetchAPI = obj;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str2 = "android.app.AppGlobals";
                str5 = " - ";
                str14 = null;
                c = FilenameUtils.EXTENSION_SEPARATOR;
                str13 = "Throwable().stackTrace";
            } catch (Throwable th5) {
                th = th5;
                str11 = "replaceAll(\"\")";
                str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                str2 = "android.app.AppGlobals";
                str5 = " - ";
                str14 = null;
                c = FilenameUtils.EXTENSION_SEPARATOR;
                str13 = "Throwable().stackTrace";
                Result.Companion companion52 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                obj3 = m1202constructorimpl2;
                if (Result.m1205exceptionOrNullimpl(obj3) != null) {
                }
                if (!Result.m1208isFailureimpl(obj3)) {
                }
            }
        }
        try {
            m1202constructorimpl2 = Result.m1202constructorimpl((Map) callTextConfigFetchAPI);
        } catch (Throwable th6) {
            th = th6;
            Result.Companion companion522 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            obj3 = m1202constructorimpl2;
            if (Result.m1205exceptionOrNullimpl(obj3) != null) {
            }
            if (!Result.m1208isFailureimpl(obj3)) {
            }
        }
        obj3 = m1202constructorimpl2;
        if (Result.m1205exceptionOrNullimpl(obj3) != null) {
            FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setTextConfig$hyperkyc_release(str14);
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, str13);
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, c, str14, 2, str14)) == 0) {
                canonicalName = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? str14 : cls3.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef3.element = canonicalName;
            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher3.find()) {
                ?? replaceAll3 = matcher3.replaceAll("");
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
                    z = false;
                } else {
                    i2 = 0;
                    str17 = ((String) objectRef3.element).substring(0, 23);
                    str16 = str12;
                    Intrinsics.checkNotNullExpressionValue(str17, str16);
                    sb3.append(str17);
                    sb3.append(str5);
                    sb3.append("prefetchTextConfig() failed");
                    sb3.append(' ');
                    sb3.append("");
                    companion6.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName(str2).getMethod("getInitialApplication", new Class[i2]).invoke(str14, new Object[i2]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th7) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName3 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default(packageName3, "co.hyperverge", (boolean) i2, 2, str14)) {
                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str13);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, c, str14, 2, str14)) == null) {
                                    canonicalName2 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? str14 : cls4.getCanonicalName();
                                    if (canonicalName2 == 0) {
                                        canonicalName2 = "N/A";
                                    }
                                } else {
                                    canonicalName2 = substringAfterLast$default;
                                }
                                objectRef4.element = canonicalName2;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher4.find()) {
                                    ?? replaceAll4 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll4, str15);
                                    objectRef4.element = replaceAll4;
                                }
                                if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= i) {
                                    str18 = (String) objectRef4.element;
                                } else {
                                    str18 = ((String) objectRef4.element).substring(i2, 23);
                                    Intrinsics.checkNotNullExpressionValue(str18, str16);
                                }
                                Log.println(3, str18, "prefetchTextConfig() failed ");
                            }
                        }
                    }
                }
            } else {
                str16 = str12;
                z = false;
                i = 26;
            }
            str17 = (String) objectRef3.element;
            i2 = z;
            sb3.append(str17);
            sb3.append(str5);
            sb3.append("prefetchTextConfig() failed");
            sb3.append(' ');
            sb3.append("");
            companion6.log(level2, sb3.toString());
            if (!CoreExtsKt.isRelease()) {
            }
        }
        return !Result.m1208isFailureimpl(obj3) ? str14 : obj3;
    }
}
