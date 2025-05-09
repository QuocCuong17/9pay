package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
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
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$makeAPICallToPushLogsToRemote$2", f = "MainVM.kt", i = {0}, l = {3884}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class MainVM$makeAPICallToPushLogsToRemote$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $baseS3Url;
    final /* synthetic */ File $file;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$makeAPICallToPushLogsToRemote$2(String str, MainVM mainVM, File file, Continuation<? super MainVM$makeAPICallToPushLogsToRemote$2> continuation) {
        super(2, continuation);
        this.$baseS3Url = str;
        this.this$0 = mainVM;
        this.$file = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$makeAPICallToPushLogsToRemote$2 mainVM$makeAPICallToPushLogsToRemote$2 = new MainVM$makeAPICallToPushLogsToRemote$2(this.$baseS3Url, this.this$0, this.$file, continuation);
        mainVM$makeAPICallToPushLogsToRemote$2.L$0 = obj;
        return mainVM$makeAPICallToPushLogsToRemote$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainVM$makeAPICallToPushLogsToRemote$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00c5, code lost:
    
        if (r3 != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x02c5, code lost:
    
        if (r1 != 0) goto L104;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:85:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x044b  */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7, types: [T] */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r1v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v32, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v57 */
    /* JADX WARN: Type inference failed for: r1v58 */
    /* JADX WARN: Type inference failed for: r1v59 */
    /* JADX WARN: Type inference failed for: r1v6, types: [T] */
    /* JADX WARN: Type inference failed for: r1v62, types: [T] */
    /* JADX WARN: Type inference failed for: r1v72, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v74, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v85, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v90 */
    /* JADX WARN: Type inference failed for: r1v91 */
    /* JADX WARN: Type inference failed for: r2v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v42, types: [T] */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v58 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Map defaultHeaders;
        Map authHeaders;
        Object m396customApiCalleH_QyT8$hyperkyc_release$default;
        Object obj2;
        String str;
        Object obj3;
        String str2;
        MainVM mainVM;
        String str3;
        ?? r1;
        String str4;
        String str5;
        Object m1202constructorimpl;
        String str6;
        ?? r13;
        Class<?> cls;
        Matcher matcher;
        String str7;
        String localizedMessage;
        String className;
        Context context;
        Class<?> cls2;
        String className2;
        MainVM mainVM2;
        String str8;
        ?? r3;
        String str9;
        Object m1202constructorimpl2;
        ?? canonicalName;
        Class<?> cls3;
        String str10;
        String className3;
        Context context2;
        Class<?> cls4;
        String className4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            NetworkRepo networkRepo = NetworkRepo.INSTANCE;
            String str11 = this.$baseS3Url;
            defaultHeaders = this.this$0.getDefaultHeaders();
            authHeaders = this.this$0.getAuthHeaders();
            this.L$0 = coroutineScope;
            this.label = 1;
            m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo, str11, "put", MapsKt.plus(defaultHeaders, authHeaders), RequestBody.INSTANCE.create(this.$file, MediaType.INSTANCE.get("application/x-gzip")), 0L, null, 0, this, 112, null);
            if (m396customApiCalleH_QyT8$hyperkyc_release$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            m396customApiCalleH_QyT8$hyperkyc_release$default = ((Result) obj).getValue();
            coroutineScope = coroutineScope2;
        }
        MainVM mainVM3 = this.this$0;
        String str12 = "";
        if (Result.m1209isSuccessimpl(m396customApiCalleH_QyT8$hyperkyc_release$default)) {
            Response response = (Response) m396customApiCalleH_QyT8$hyperkyc_release$default;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            obj3 = "N/A";
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            obj2 = m396customApiCalleH_QyT8$hyperkyc_release$default;
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null) {
                mainVM2 = mainVM3;
                str8 = "context.filesDir";
            } else {
                mainVM2 = mainVM3;
                str8 = "context.filesDir";
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                r3 = substringAfterLast$default;
            }
            String canonicalName2 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? null : cls4.getCanonicalName();
            r3 = canonicalName2 == null ? obj3 : canonicalName2;
            objectRef.element = r3;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str9 = (String) objectRef.element;
            } else {
                str9 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str9);
            sb.append(" - ");
            String str13 = "pushLogsToRemote(): onSuccess with responseCode = [" + response.code() + ']';
            if (str13 == null) {
                str13 = "null ";
            }
            sb.append(str13);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
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
                        if (stackTraceElement2 == null || (className3 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? 0 : cls3.getCanonicalName();
                            if (canonicalName == 0) {
                                canonicalName = obj3;
                            }
                        }
                        objectRef2.element = canonicalName;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher3.find()) {
                            ?? replaceAll2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str10 = (String) objectRef2.element;
                        } else {
                            str10 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str14 = "pushLogsToRemote(): onSuccess with responseCode = [" + response.code() + ']';
                        sb2.append(str14 != null ? str14 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str10, sb2.toString());
                    }
                }
            }
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            context2 = mainVM2.getContext();
            File filesDir = context2.getFilesDir();
            str = str8;
            Intrinsics.checkNotNullExpressionValue(filesDir, str);
            companion4.deleteLogFolder(filesDir);
        } else {
            obj2 = m396customApiCalleH_QyT8$hyperkyc_release$default;
            str = "context.filesDir";
            obj3 = "N/A";
        }
        MainVM mainVM4 = this.this$0;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                str2 = str;
                mainVM = mainVM4;
                str3 = "Throwable().stackTrace";
            } else {
                str2 = str;
                mainVM = mainVM4;
                str3 = "Throwable().stackTrace";
                r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            r1 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? 0 : cls2.getCanonicalName();
            if (r1 == 0) {
                r1 = obj3;
            }
            objectRef3.element = r1;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher4.find()) {
                ?? replaceAll3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                objectRef3.element = replaceAll3;
            }
            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str4 = (String) objectRef3.element;
            } else {
                str4 = ((String) objectRef3.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str4);
            sb3.append(" - ");
            sb3.append("pushLogsToRemote(): failed");
            sb3.append(' ');
            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str5 = '\n' + localizedMessage2;
            } else {
                str5 = "";
            }
            sb3.append(str5);
            companion5.log(level2, sb3.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion6 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion7 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str3);
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                            str6 = null;
                        } else {
                            str6 = null;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default2 != null) {
                                r13 = substringAfterLast$default2;
                                objectRef4.element = r13;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher.find()) {
                                    ?? replaceAll4 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                    objectRef4.element = replaceAll4;
                                }
                                if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                    str7 = (String) objectRef4.element;
                                } else {
                                    str7 = ((String) objectRef4.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("pushLogsToRemote(): failed");
                                sb4.append(' ');
                                localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str6;
                                if (localizedMessage != null) {
                                    str12 = '\n' + localizedMessage;
                                }
                                sb4.append(str12);
                                Log.println(5, str7, sb4.toString());
                            }
                        }
                        String canonicalName3 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? str6 : cls.getCanonicalName();
                        r13 = canonicalName3 == null ? obj3 : canonicalName3;
                        objectRef4.element = r13;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                        if (matcher.find()) {
                        }
                        if (((String) objectRef4.element).length() > 23) {
                        }
                        str7 = (String) objectRef4.element;
                        StringBuilder sb42 = new StringBuilder();
                        sb42.append("pushLogsToRemote(): failed");
                        sb42.append(' ');
                        if (m1205exceptionOrNullimpl == null) {
                        }
                        if (localizedMessage != null) {
                        }
                        sb42.append(str12);
                        Log.println(5, str7, sb42.toString());
                    }
                }
            }
            HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
            context = mainVM.getContext();
            File filesDir2 = context.getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir2, str2);
            companion8.deleteLogFolder(filesDir2);
        }
        return Unit.INSTANCE;
    }
}
