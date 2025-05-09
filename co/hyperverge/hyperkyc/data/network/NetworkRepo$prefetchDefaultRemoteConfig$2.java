package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.models.HSDefaultRemoteConfig;
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

/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchDefaultRemoteConfig$2", f = "NetworkRepo.kt", i = {0}, l = {BaselineTIFFTagSet.TAG_TILE_WIDTH}, m = "invokeSuspend", n = {"$this$onIO"}, s = {"L$0"})
/* loaded from: classes2.dex */
final class NetworkRepo$prefetchDefaultRemoteConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSDefaultRemoteConfig>, Object> {
    final /* synthetic */ File $cacheDir;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$prefetchDefaultRemoteConfig$2(File file, Continuation<? super NetworkRepo$prefetchDefaultRemoteConfig$2> continuation) {
        super(2, continuation);
        this.$cacheDir = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NetworkRepo$prefetchDefaultRemoteConfig$2 networkRepo$prefetchDefaultRemoteConfig$2 = new NetworkRepo$prefetchDefaultRemoteConfig$2(this.$cacheDir, continuation);
        networkRepo$prefetchDefaultRemoteConfig$2.L$0 = obj;
        return networkRepo$prefetchDefaultRemoteConfig$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSDefaultRemoteConfig> continuation) {
        return ((NetworkRepo$prefetchDefaultRemoteConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x01b1, code lost:
    
        if (r2 != 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x02db, code lost:
    
        if (r5 != null) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0094, code lost:
    
        if (r8 != null) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0361 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0444  */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v14, types: [T] */
    /* JADX WARN: Type inference failed for: r1v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v24, types: [T] */
    /* JADX WARN: Type inference failed for: r2v36, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v43, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v53, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v80 */
    /* JADX WARN: Type inference failed for: r5v14, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v7, types: [T] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v5, types: [T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        CharSequence charSequence;
        String str2;
        String str3;
        String str4;
        Object m1202constructorimpl;
        String str5;
        NetworkRepo$prefetchDefaultRemoteConfig$2 networkRepo$prefetchDefaultRemoteConfig$2;
        Object obj2;
        ?? r2;
        String str6;
        Class<?> cls;
        String className;
        String str7;
        String str8;
        String str9;
        String str10;
        Object callDefaultRemoteConfigFetchAPI$default;
        Class<?> cls2;
        String className2;
        Object m1202constructorimpl2;
        Object obj3;
        Object obj4;
        Object obj5;
        String str11;
        String str12;
        boolean z;
        int i;
        int i2;
        String str13;
        Object m1202constructorimpl3;
        ?? canonicalName;
        Class<?> cls3;
        Matcher matcher;
        String str14;
        String className3;
        Class<?> cls4;
        String className4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            File file = this.$cacheDir;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                str = "Throwable().stackTrace";
                charSequence = "co.hyperverge";
                str2 = "packageName";
            } else {
                str = "Throwable().stackTrace";
                charSequence = "co.hyperverge";
                str2 = "packageName";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str3 = substringAfterLast$default;
            }
            String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
            str3 = canonicalName2 == null ? "N/A" : canonicalName2;
            objectRef.element = str3;
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
            String str15 = "prefetchDefaultRemoteConfig() called with cacheDir = " + file;
            if (str15 == null) {
                str15 = "null ";
            }
            sb.append(str15);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                networkRepo$prefetchDefaultRemoteConfig$2 = this;
                str5 = str2;
            } else {
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
                String str16 = (String) m1202constructorimpl;
                str5 = str2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str16, str5);
                    if (StringsKt.contains$default((CharSequence) str16, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        String str17 = str;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str17);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str = str17;
                            obj2 = null;
                        } else {
                            str = str17;
                            obj2 = null;
                            r2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r2 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? obj2 : cls.getCanonicalName();
                        if (r2 == 0) {
                            r2 = "N/A";
                        }
                        objectRef2.element = r2;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher3.find()) {
                            ?? replaceAll2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str6 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            StringBuilder sb2 = new StringBuilder();
                            String str18 = "prefetchDefaultRemoteConfig() called with cacheDir = " + file;
                            sb2.append(str18 != null ? str18 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str6, sb2.toString());
                            networkRepo$prefetchDefaultRemoteConfig$2 = this;
                        }
                        str6 = (String) objectRef2.element;
                        StringBuilder sb22 = new StringBuilder();
                        String str182 = "prefetchDefaultRemoteConfig() called with cacheDir = " + file;
                        sb22.append(str182 != null ? str182 : "null ");
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(3, str6, sb22.toString());
                        networkRepo$prefetchDefaultRemoteConfig$2 = this;
                    }
                }
                networkRepo$prefetchDefaultRemoteConfig$2 = this;
            }
            File file2 = networkRepo$prefetchDefaultRemoteConfig$2.$cacheDir;
            try {
                Result.Companion companion4 = Result.INSTANCE;
                NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                Integer boxInt = Boxing.boxInt(3600);
                networkRepo$prefetchDefaultRemoteConfig$2.L$0 = coroutineScope;
                networkRepo$prefetchDefaultRemoteConfig$2.label = 1;
                str7 = "replaceAll(\"\")";
                str8 = "";
                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                str10 = str;
                try {
                    callDefaultRemoteConfigFetchAPI$default = NetworkRepo.callDefaultRemoteConfigFetchAPI$default(networkRepo, file2, boxInt, null, this, 4, null);
                    if (callDefaultRemoteConfigFetchAPI$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope = coroutineScope;
                } catch (Throwable th2) {
                    th = th2;
                    coroutineScope = coroutineScope;
                    Result.Companion companion5 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    obj3 = m1202constructorimpl2;
                    if (Result.m1205exceptionOrNullimpl(obj3) == null) {
                    }
                    obj5 = null;
                    if (!Result.m1208isFailureimpl(obj4)) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                str7 = "replaceAll(\"\")";
                str8 = "";
                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                str10 = str;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                callDefaultRemoteConfigFetchAPI$default = obj;
                str10 = "Throwable().stackTrace";
                str8 = "";
                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                charSequence = "co.hyperverge";
                str5 = "packageName";
                str7 = "replaceAll(\"\")";
            } catch (Throwable th4) {
                th = th4;
                str10 = "Throwable().stackTrace";
                str8 = "";
                str9 = "this as java.lang.String…ing(startIndex, endIndex)";
                charSequence = "co.hyperverge";
                str5 = "packageName";
                str7 = "replaceAll(\"\")";
                Result.Companion companion52 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                obj3 = m1202constructorimpl2;
                if (Result.m1205exceptionOrNullimpl(obj3) == null) {
                }
                obj5 = null;
                if (!Result.m1208isFailureimpl(obj4)) {
                }
            }
        }
        try {
            m1202constructorimpl2 = Result.m1202constructorimpl((HSDefaultRemoteConfig) callDefaultRemoteConfigFetchAPI$default);
        } catch (Throwable th5) {
            th = th5;
            Result.Companion companion522 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            obj3 = m1202constructorimpl2;
            if (Result.m1205exceptionOrNullimpl(obj3) == null) {
            }
            obj5 = null;
            if (!Result.m1208isFailureimpl(obj4)) {
            }
        }
        obj3 = m1202constructorimpl2;
        if (Result.m1205exceptionOrNullimpl(obj3) == null) {
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, str10);
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null) {
                obj4 = obj3;
            } else {
                obj4 = obj3;
                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str11 = substringAfterLast$default2;
            }
            String canonicalName3 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? null : cls4.getCanonicalName();
            str11 = canonicalName3 == null ? "N/A" : canonicalName3;
            objectRef3.element = str11;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher4.find()) {
                ?? replaceAll3 = matcher4.replaceAll(str8);
                Intrinsics.checkNotNullExpressionValue(replaceAll3, str7);
                objectRef3.element = replaceAll3;
            }
            if (((String) objectRef3.element).length() > 23) {
                i = 26;
                if (Build.VERSION.SDK_INT >= 26) {
                    str12 = str9;
                    z = false;
                } else {
                    i2 = 0;
                    str13 = ((String) objectRef3.element).substring(0, 23);
                    str12 = str9;
                    Intrinsics.checkNotNullExpressionValue(str13, str12);
                    sb3.append(str13);
                    sb3.append(" - ");
                    sb3.append("prefetchDefaultRemoteConfig() failed");
                    sb3.append(' ');
                    sb3.append(str8);
                    companion6.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[i2]).invoke(null, new Object[i2]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th6) {
                            Result.Companion companion8 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = str8;
                        }
                        String str19 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str19, str5);
                            if (StringsKt.contains$default(str19, charSequence, (boolean) i2, 2, (Object) null)) {
                                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str10);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                                    obj5 = null;
                                } else {
                                    obj5 = null;
                                    String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default3 != null) {
                                        canonicalName = substringAfterLast$default3;
                                        objectRef4.element = canonicalName;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                        if (matcher.find()) {
                                            ?? replaceAll4 = matcher.replaceAll(str8);
                                            Intrinsics.checkNotNullExpressionValue(replaceAll4, str7);
                                            objectRef4.element = replaceAll4;
                                        }
                                        if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= i) {
                                            str14 = (String) objectRef4.element;
                                        } else {
                                            str14 = ((String) objectRef4.element).substring(i2, 23);
                                            Intrinsics.checkNotNullExpressionValue(str14, str12);
                                        }
                                        Log.println(3, str14, "prefetchDefaultRemoteConfig() failed " + str8);
                                        return !Result.m1208isFailureimpl(obj4) ? obj5 : obj4;
                                    }
                                }
                                canonicalName = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? obj5 : cls3.getCanonicalName();
                                if (canonicalName == 0) {
                                    canonicalName = "N/A";
                                }
                                objectRef4.element = canonicalName;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef4.element).length() > 23) {
                                }
                                str14 = (String) objectRef4.element;
                                Log.println(3, str14, "prefetchDefaultRemoteConfig() failed " + str8);
                                if (!Result.m1208isFailureimpl(obj4)) {
                                }
                            }
                        }
                    }
                }
            } else {
                str12 = str9;
                z = false;
                i = 26;
            }
            str13 = (String) objectRef3.element;
            i2 = z;
            sb3.append(str13);
            sb3.append(" - ");
            sb3.append("prefetchDefaultRemoteConfig() failed");
            sb3.append(' ');
            sb3.append(str8);
            companion6.log(level2, sb3.toString());
            if (!CoreExtsKt.isRelease()) {
            }
        } else {
            obj4 = obj3;
        }
        obj5 = null;
        if (!Result.m1208isFailureimpl(obj4)) {
        }
    }
}
