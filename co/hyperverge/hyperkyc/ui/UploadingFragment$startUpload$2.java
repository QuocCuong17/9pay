package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.data.network.progress.ProgressFriendlySocketFactory;
import co.hyperverge.hyperkyc.data.network.progress.ProgressRequestBody;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadingFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.UploadingFragment$startUpload$2", f = "UploadingFragment.kt", i = {0}, l = {94}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class UploadingFragment$startUpload$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UploadingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadingFragment$startUpload$2(UploadingFragment uploadingFragment, Continuation<? super UploadingFragment$startUpload$2> continuation) {
        super(2, continuation);
        this.this$0 = uploadingFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        UploadingFragment$startUpload$2 uploadingFragment$startUpload$2 = new UploadingFragment$startUpload$2(this.this$0, continuation);
        uploadingFragment$startUpload$2.L$0 = obj;
        return uploadingFragment$startUpload$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UploadingFragment$startUpload$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x043a, code lost:
    
        if (r1 != 0) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00e2, code lost:
    
        if (r1 != 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0310, code lost:
    
        if (r15 != null) goto L108;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:139:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x04f8  */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v7, types: [T] */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r1v100 */
    /* JADX WARN: Type inference failed for: r1v101 */
    /* JADX WARN: Type inference failed for: r1v102 */
    /* JADX WARN: Type inference failed for: r1v105, types: [T] */
    /* JADX WARN: Type inference failed for: r1v115, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v117, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v126, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v127 */
    /* JADX WARN: Type inference failed for: r1v128 */
    /* JADX WARN: Type inference failed for: r1v129 */
    /* JADX WARN: Type inference failed for: r1v133, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v134 */
    /* JADX WARN: Type inference failed for: r1v135 */
    /* JADX WARN: Type inference failed for: r1v136 */
    /* JADX WARN: Type inference failed for: r1v33, types: [T] */
    /* JADX WARN: Type inference failed for: r1v43, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v50, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v61, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v75, types: [T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String uploadUrl;
        Map headers;
        ProgressRequestBody progressRequestBody;
        Object m396customApiCalleH_QyT8$hyperkyc_release$default;
        Object obj2;
        String str;
        String str2;
        UploadingFragment uploadingFragment;
        String str3;
        String str4;
        String str5;
        String str6;
        Object m1202constructorimpl;
        String str7;
        WorkflowUIState workflowUIState;
        String filePath;
        Function0 function0;
        Object obj3;
        ?? r1;
        String str8;
        Class<?> cls;
        String className;
        Class<?> cls2;
        String className2;
        String filePath2;
        UploadingFragment uploadingFragment2;
        HyperKycData.SessionData sessionData;
        MainVM mainVM;
        WorkflowUIState workflowUIState2;
        Function0 function02;
        MainVM mainVM2;
        String str9;
        ?? r12;
        String str10;
        Object m1202constructorimpl2;
        ?? canonicalName;
        Class<?> cls3;
        String str11;
        String className3;
        Class<?> cls4;
        String className4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            NetworkRepo networkRepo = NetworkRepo.INSTANCE;
            uploadUrl = this.this$0.getUploadUrl();
            headers = this.this$0.getHeaders();
            progressRequestBody = this.this$0.getProgressRequestBody();
            this.L$0 = coroutineScope;
            this.label = 1;
            m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo, uploadUrl, "post", headers, progressRequestBody, 900L, new ProgressFriendlySocketFactory(0, 1, null), 0, this, 64, null);
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
        UploadingFragment uploadingFragment3 = this.this$0;
        String str12 = "Throwable().stackTrace";
        String str13 = "";
        if (Result.m1209isSuccessimpl(m396customApiCalleH_QyT8$hyperkyc_release$default)) {
            Response response = (Response) m396customApiCalleH_QyT8$hyperkyc_release$default;
            HyperKycData.SessionData from$hyperkyc_release = HyperKycData.SessionData.INSTANCE.from$hyperkyc_release(response);
            filePath2 = uploadingFragment3.getFilePath();
            from$hyperkyc_release.setVideoPath$hyperkyc_release(filePath2);
            from$hyperkyc_release.setCompleted$hyperkyc_release("yes");
            if (response.isSuccessful()) {
                obj2 = m396customApiCalleH_QyT8$hyperkyc_release$default;
                uploadingFragment2 = uploadingFragment3;
                str = "no";
                str2 = "null ";
                sessionData = from$hyperkyc_release;
            } else {
                from$hyperkyc_release.setCompleted$hyperkyc_release("no");
                HyperLogger.Level level = HyperLogger.Level.ERROR;
                str2 = "null ";
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                str = "no";
                StringBuilder sb = new StringBuilder();
                obj2 = m396customApiCalleH_QyT8$hyperkyc_release$default;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null) {
                    str9 = "Throwable().stackTrace";
                    uploadingFragment2 = uploadingFragment3;
                    sessionData = from$hyperkyc_release;
                } else {
                    str9 = "Throwable().stackTrace";
                    uploadingFragment2 = uploadingFragment3;
                    sessionData = from$hyperkyc_release;
                    r12 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                r12 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? 0 : cls4.getCanonicalName();
                if (r12 == 0) {
                    r12 = "N/A";
                }
                objectRef.element = r12;
                Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher.find()) {
                    ?? replaceAll = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                    objectRef.element = replaceAll;
                }
                if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str10 = (String) objectRef.element;
                } else {
                    str10 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(str10);
                sb.append(" - ");
                String str14 = "session recording: error making api call : " + response.code();
                if (str14 == null) {
                    str14 = str2;
                }
                sb.append(str14);
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                CoreExtsKt.isRelease();
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
                        str12 = str9;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str12);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className3 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? 0 : cls3.getCanonicalName();
                            if (canonicalName == 0) {
                                canonicalName = "N/A";
                            }
                        }
                        objectRef2.element = canonicalName;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str11 = (String) objectRef2.element;
                        } else {
                            str11 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str15 = "session recording: error making api call : " + response.code();
                        if (str15 == null) {
                            str15 = str2;
                        }
                        sb2.append(str15);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(6, str11, sb2.toString());
                    }
                }
                str12 = str9;
            }
            sessionData.setVideoUrl$hyperkyc_release(new JSONArray(JSONExtsKt.extractJsonValue(sessionData.getResponseBodyRaw$hyperkyc_release(), "result.details.uploadUrls")).get(0).toString());
            mainVM = uploadingFragment2.getMainVM();
            workflowUIState2 = uploadingFragment2.uiState;
            MainVM.updateSessionData$hyperkyc_release$default(mainVM, workflowUIState2, sessionData, false, 4, null);
            function02 = uploadingFragment2.finishTransactionCallback;
            if (function02 != null) {
                function02.invoke();
            } else {
                mainVM2 = uploadingFragment2.getMainVM();
                Boxing.boxBoolean(mainVM2.flowForward());
            }
        } else {
            obj2 = m396customApiCalleH_QyT8$hyperkyc_release$default;
            str = "no";
            str2 = "null ";
        }
        UploadingFragment uploadingFragment4 = this.this$0;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, str12);
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null) {
                uploadingFragment = uploadingFragment4;
                str3 = str12;
            } else {
                uploadingFragment = uploadingFragment4;
                str3 = str12;
                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str4 = substringAfterLast$default;
            }
            String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
            str4 = canonicalName2 == null ? "N/A" : canonicalName2;
            objectRef3.element = str4;
            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
            if (matcher3.find()) {
                ?? replaceAll3 = matcher3.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                objectRef3.element = replaceAll3;
            }
            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str5 = (String) objectRef3.element;
            } else {
                str5 = ((String) objectRef3.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str5);
            sb3.append(" - ");
            String str16 = "session recording: upload file failed " + m1205exceptionOrNullimpl.getMessage();
            if (str16 == null) {
                str16 = str2;
            }
            sb3.append(str16);
            sb3.append(' ');
            String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage != null) {
                str6 = '\n' + localizedMessage;
            } else {
                str6 = "";
            }
            sb3.append(str6);
            companion4.log(level2, sb3.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion6 = Result.INSTANCE;
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
                        str7 = null;
                    } else {
                        str7 = null;
                        r1 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r1 = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? str7 : cls.getCanonicalName();
                    if (r1 == 0) {
                        r1 = "N/A";
                    }
                    objectRef4.element = r1;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                    if (matcher4.find()) {
                        ?? replaceAll4 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                        objectRef4.element = replaceAll4;
                    }
                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str8 = (String) objectRef4.element;
                    } else {
                        str8 = ((String) objectRef4.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    String str17 = "session recording: upload file failed " + m1205exceptionOrNullimpl.getMessage();
                    sb4.append(str17 == null ? str2 : str17);
                    sb4.append(' ');
                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str7;
                    if (localizedMessage2 != null) {
                        str13 = '\n' + localizedMessage2;
                    }
                    sb4.append(str13);
                    Log.println(6, str8, sb4.toString());
                    workflowUIState = uploadingFragment.uiState;
                    filePath = uploadingFragment.getFilePath();
                    UploadingFragment uploadingFragment5 = uploadingFragment;
                    uploadingFragment5.addSessionRecordingVideoUriToResult(workflowUIState, filePath, str);
                    function0 = uploadingFragment5.finishTransactionCallback;
                    if (function0 == null) {
                        function0.invoke();
                        obj3 = Unit.INSTANCE;
                    } else {
                        obj3 = str7;
                    }
                    if (obj3 == null) {
                        BaseFragment.finishWithResult$default(uploadingFragment5, "error", null, Boxing.boxInt(111), "Session Recording upload API call failed! - " + m1205exceptionOrNullimpl.getMessage(), false, false, 50, null);
                    }
                }
            }
            str7 = null;
            workflowUIState = uploadingFragment.uiState;
            filePath = uploadingFragment.getFilePath();
            UploadingFragment uploadingFragment52 = uploadingFragment;
            uploadingFragment52.addSessionRecordingVideoUriToResult(workflowUIState, filePath, str);
            function0 = uploadingFragment52.finishTransactionCallback;
            if (function0 == null) {
            }
            if (obj3 == null) {
            }
        }
        return Unit.INSTANCE;
    }
}
