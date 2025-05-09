package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchRemoteConfig$2", f = "NetworkRepo.kt", i = {0}, l = {BaselineTIFFTagSet.TAG_FILL_ORDER}, m = "invokeSuspend", n = {"$this$onIO"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class NetworkRepo$prefetchRemoteConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSRemoteConfig>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ File $cacheDir;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$prefetchRemoteConfig$2(String str, File file, Continuation<? super NetworkRepo$prefetchRemoteConfig$2> continuation) {
        super(2, continuation);
        this.$appId = str;
        this.$cacheDir = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$prefetchRemoteConfig$2 networkRepo$prefetchRemoteConfig$2 = new NetworkRepo$prefetchRemoteConfig$2(this.$appId, this.$cacheDir, continuation);
        networkRepo$prefetchRemoteConfig$2.L$0 = obj;
        return networkRepo$prefetchRemoteConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSRemoteConfig> continuation) {
        return ((NetworkRepo$prefetchRemoteConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:144:0x01b5, code lost:
    
        if (r1 != 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x02b5, code lost:
    
        if (r13 != null) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x03ac, code lost:
    
        if (r4 != null) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0090, code lost:
    
        if (r1 != 0) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0260 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0427  */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19, types: [T] */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v25, types: [T] */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r1v30, types: [T] */
    /* JADX WARN: Type inference failed for: r1v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v47, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v58, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v59 */
    /* JADX WARN: Type inference failed for: r1v60 */
    /* JADX WARN: Type inference failed for: r1v61 */
    /* JADX WARN: Type inference failed for: r1v65, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v85, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v86 */
    /* JADX WARN: Type inference failed for: r1v87 */
    /* JADX WARN: Type inference failed for: r3v20, types: [T, java.lang.Object, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        String str;
        String str2;
        String str3;
        ?? r1;
        String str4;
        String str5;
        Object m1202constructorimpl;
        String str6;
        NetworkRepo$prefetchRemoteConfig$2 networkRepo$prefetchRemoteConfig$2;
        ?? r12;
        String str7;
        Class<?> cls;
        String className;
        CoroutineScope coroutineScope;
        Object callRemoteConfigFetchAPI;
        Class<?> cls2;
        String className2;
        Object m1202constructorimpl2;
        Object obj3;
        String str8;
        ?? r13;
        Class<?> cls3;
        String str9;
        Object m1202constructorimpl3;
        String str10;
        ?? r132;
        Matcher matcher;
        String str11;
        Class<?> cls4;
        String className3;
        String className4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            String str12 = this.$appId;
            File file = this.$cacheDir;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            obj2 = "N/A";
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str = "packageName";
                str2 = "getInitialApplication";
                str3 = "Throwable().stackTrace";
            } else {
                str = "packageName";
                str2 = "getInitialApplication";
                str3 = "Throwable().stackTrace";
                r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            r1 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? 0 : cls2.getCanonicalName();
            if (r1 == 0) {
                r1 = obj2;
            }
            objectRef.element = r1;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
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
            String str13 = "prefetchRemoteConfig() called with: appId = " + str12 + ", cacheDir = " + file;
            if (str13 == null) {
                str13 = "null ";
            }
            sb.append(str13);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                networkRepo$prefetchRemoteConfig$2 = this;
                str6 = str3;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str5 = str2;
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod(str5, new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String str14 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        str2 = str5;
                        str6 = str3;
                        networkRepo$prefetchRemoteConfig$2 = this;
                        String str15 = networkRepo$prefetchRemoteConfig$2.$appId;
                        File file2 = networkRepo$prefetchRemoteConfig$2.$cacheDir;
                        Result.Companion companion4 = Result.INSTANCE;
                        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                        Integer boxInt = Boxing.boxInt(3600);
                        networkRepo$prefetchRemoteConfig$2.L$0 = coroutineScope2;
                        networkRepo$prefetchRemoteConfig$2.label = 1;
                        callRemoteConfigFetchAPI = networkRepo.callRemoteConfigFetchAPI(str15, file2, boxInt, networkRepo$prefetchRemoteConfig$2);
                        if (callRemoteConfigFetchAPI != coroutine_suspended) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str5 = str2;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String str142 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str142, str);
                    if (StringsKt.contains$default((CharSequence) str142, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str6 = str3;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str2 = str5;
                        } else {
                            str2 = str5;
                            r12 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r12 = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? 0 : cls.getCanonicalName();
                        if (r12 == 0) {
                            r12 = obj2;
                        }
                        objectRef2.element = r12;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher3.find()) {
                            ?? replaceAll2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str7 = (String) objectRef2.element;
                        } else {
                            str7 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str16 = "prefetchRemoteConfig() called with: appId = " + str12 + ", cacheDir = " + file;
                        if (str16 == null) {
                            str16 = "null ";
                        }
                        sb2.append(str16);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str7, sb2.toString());
                        networkRepo$prefetchRemoteConfig$2 = this;
                    }
                }
                str2 = str5;
                str6 = str3;
                networkRepo$prefetchRemoteConfig$2 = this;
            }
            String str152 = networkRepo$prefetchRemoteConfig$2.$appId;
            File file22 = networkRepo$prefetchRemoteConfig$2.$cacheDir;
            try {
                Result.Companion companion42 = Result.INSTANCE;
                NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
                Integer boxInt2 = Boxing.boxInt(3600);
                networkRepo$prefetchRemoteConfig$2.L$0 = coroutineScope2;
                networkRepo$prefetchRemoteConfig$2.label = 1;
                callRemoteConfigFetchAPI = networkRepo2.callRemoteConfigFetchAPI(str152, file22, boxInt2, networkRepo$prefetchRemoteConfig$2);
                if (callRemoteConfigFetchAPI != coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope2;
            } catch (Throwable th3) {
                th = th3;
                coroutineScope = coroutineScope2;
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                CoroutineScope coroutineScope3 = coroutineScope;
                obj3 = m1202constructorimpl2;
                if (Result.m1205exceptionOrNullimpl(obj3) != null) {
                }
                str8 = null;
                if (!Result.m1208isFailureimpl(obj3)) {
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                callRemoteConfigFetchAPI = obj;
                str = "packageName";
                str2 = "getInitialApplication";
                obj2 = "N/A";
                str6 = "Throwable().stackTrace";
            } catch (Throwable th4) {
                th = th4;
                str = "packageName";
                str2 = "getInitialApplication";
                obj2 = "N/A";
                str6 = "Throwable().stackTrace";
                Result.Companion companion52 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                CoroutineScope coroutineScope32 = coroutineScope;
                obj3 = m1202constructorimpl2;
                if (Result.m1205exceptionOrNullimpl(obj3) != null) {
                }
                str8 = null;
                if (!Result.m1208isFailureimpl(obj3)) {
                }
            }
        }
        try {
            m1202constructorimpl2 = Result.m1202constructorimpl((HSRemoteConfig) callRemoteConfigFetchAPI);
        } catch (Throwable th5) {
            th = th5;
            Result.Companion companion522 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            CoroutineScope coroutineScope322 = coroutineScope;
            obj3 = m1202constructorimpl2;
            if (Result.m1205exceptionOrNullimpl(obj3) != null) {
            }
            str8 = null;
            if (!Result.m1208isFailureimpl(obj3)) {
            }
        }
        CoroutineScope coroutineScope3222 = coroutineScope;
        obj3 = m1202constructorimpl2;
        if (Result.m1205exceptionOrNullimpl(obj3) != null) {
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, str6);
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 != null && (className4 = stackTraceElement3.getClassName()) != null) {
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                r13 = substringAfterLast$default;
            }
            String canonicalName = (coroutineScope3222 == null || (cls3 = coroutineScope3222.getClass()) == null) ? null : cls3.getCanonicalName();
            r13 = canonicalName == null ? obj2 : canonicalName;
            objectRef3.element = r13;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher4.find()) {
                ?? replaceAll3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                objectRef3.element = replaceAll3;
            }
            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str9 = (String) objectRef3.element;
            } else {
                str9 = ((String) objectRef3.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str9);
            sb3.append(" - ");
            sb3.append("prefetchRemoteConfig() failed");
            sb3.append(' ');
            sb3.append("");
            companion6.log(level2, sb3.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion7 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str2, new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th6) {
                    Result.Companion companion8 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str17 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str17, str);
                    if (StringsKt.contains$default((CharSequence) str17, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str6);
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                            str8 = null;
                        } else {
                            str8 = null;
                            str10 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        str10 = (coroutineScope3222 == null || (cls4 = coroutineScope3222.getClass()) == null) ? str8 : cls4.getCanonicalName();
                        if (str10 == null) {
                            r132 = obj2;
                            objectRef4.element = r132;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                            if (matcher.find()) {
                                ?? replaceAll4 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                objectRef4.element = replaceAll4;
                            }
                            if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                str11 = (String) objectRef4.element;
                            } else {
                                str11 = ((String) objectRef4.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str11, "prefetchRemoteConfig() failed ");
                            return !Result.m1208isFailureimpl(obj3) ? str8 : obj3;
                        }
                        r132 = str10;
                        objectRef4.element = r132;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                        if (matcher.find()) {
                        }
                        if (((String) objectRef4.element).length() > 23) {
                        }
                        str11 = (String) objectRef4.element;
                        Log.println(3, str11, "prefetchRemoteConfig() failed ");
                        if (!Result.m1208isFailureimpl(obj3)) {
                        }
                    }
                }
            }
        }
        str8 = null;
        if (!Result.m1208isFailureimpl(obj3)) {
        }
    }
}
