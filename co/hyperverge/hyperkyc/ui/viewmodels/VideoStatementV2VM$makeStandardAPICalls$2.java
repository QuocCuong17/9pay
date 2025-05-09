package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2VM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeStandardAPICalls$2", f = "VideoStatementV2VM.kt", i = {0}, l = {410, 452}, m = "invokeSuspend", n = {"$this$onIO"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class VideoStatementV2VM$makeStandardAPICalls$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VideoStatementV2VM$makeStandardAPICalls$2(Function3<? super String, ? super Integer, ? super String, Unit> function3, VideoStatementV2VM videoStatementV2VM, Continuation<? super VideoStatementV2VM$makeStandardAPICalls$2> continuation) {
        super(2, continuation);
        this.$finishWithErrorCallback = function3;
        this.this$0 = videoStatementV2VM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoStatementV2VM$makeStandardAPICalls$2 videoStatementV2VM$makeStandardAPICalls$2 = new VideoStatementV2VM$makeStandardAPICalls$2(this.$finishWithErrorCallback, this.this$0, continuation);
        videoStatementV2VM$makeStandardAPICalls$2.L$0 = obj;
        return videoStatementV2VM$makeStandardAPICalls$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2VM$makeStandardAPICalls$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x018d, code lost:
    
        if (r3 == null) goto L66;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10, types: [T] */
    /* JADX WARN: Type inference failed for: r11v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1, types: [T] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r3v39, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v55, types: [T, java.lang.Object, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? canonicalName;
        Class<?> cls;
        String str;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String str2;
        String className;
        Deferred async$default;
        CoroutineScope coroutineScope;
        String className2;
        Deferred async$default2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            Function3<String, Integer, String, Unit> function3 = this.$finishWithErrorCallback;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r14 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? 0 : cls.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef.element = canonicalName;
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
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
            String str3 = "makeStandardAPICalls() called with: finishWithErrorCallback = " + function3;
            if (str3 == null) {
                str3 = "null ";
            }
            sb.append(str3);
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
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? null : cls2.getCanonicalName();
                        }
                        r14 = canonicalName2;
                        objectRef2.element = r14;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str2 = (String) objectRef2.element;
                        } else {
                            str2 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str4 = "makeStandardAPICalls() called with: finishWithErrorCallback = " + function3;
                        sb2.append(str4 != null ? str4 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str2, sb2.toString());
                    }
                }
            }
            async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope2, null, null, new AnonymousClass2(this.this$0, this.$finishWithErrorCallback, null), 3, null);
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (async$default.await(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
        }
        async$default2 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass3(this.this$0, this.$finishWithErrorCallback, null), 3, null);
        this.L$0 = null;
        this.label = 2;
        if (async$default2.await(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VideoStatementV2VM.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeStandardAPICalls$2$2", f = "VideoStatementV2VM.kt", i = {}, l = {391}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeStandardAPICalls$2$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
        int label;
        final /* synthetic */ VideoStatementV2VM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(VideoStatementV2VM videoStatementV2VM, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = videoStatementV2VM;
            this.$finishWithErrorCallback = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$finishWithErrorCallback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            VideoStatementV2Config videoStatementV2Config;
            String str;
            VideoStatementV2Config videoStatementV2Config2;
            String str2;
            VideoStatementV2Config videoStatementV2Config3;
            Object validateCheck;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                videoStatementV2Config = this.this$0.vsConfigV2;
                if (videoStatementV2Config.getVideoUploadAPI() != null) {
                    str = this.this$0.videoUri;
                    if (str != null) {
                        videoStatementV2Config2 = this.this$0.vsConfigV2;
                        List mutableList = CollectionsKt.toMutableList((Collection) videoStatementV2Config2.getVideoUploadAPI().getParameters());
                        str2 = this.this$0.videoUri;
                        if (str2 == null) {
                            str2 = "";
                        }
                        mutableList.add(new WorkflowModule.Properties.RequestParam("video", str2, "video"));
                        VideoStatementV2VM videoStatementV2VM = this.this$0;
                        videoStatementV2Config3 = videoStatementV2VM.vsConfigV2;
                        this.label = 1;
                        validateCheck = videoStatementV2VM.validateCheck(VideoStatementV2VM.createAPICallState$default(videoStatementV2VM, videoStatementV2Config3.getVideoUploadAPI(), WorkflowRequestType.MULTIPART, null, mutableList, 4, null), VideoStatementV2VM.RequestType.VIDEO_UPLOAD, this.$finishWithErrorCallback, this);
                        if (validateCheck == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.this$0.isGenericFailure = true;
                    }
                } else {
                    this.$finishWithErrorCallback.invoke("error", Boxing.boxInt(104), "API call failed!");
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VideoStatementV2VM.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeStandardAPICalls$2$3", f = "VideoStatementV2VM.kt", i = {}, l = {440}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeStandardAPICalls$2$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
        int label;
        final /* synthetic */ VideoStatementV2VM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(VideoStatementV2VM videoStatementV2VM, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = videoStatementV2VM;
            this.$finishWithErrorCallback = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, this.$finishWithErrorCallback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            VideoStatementV2Config videoStatementV2Config;
            VideoStatementV2Config videoStatementV2Config2;
            HyperKycData.VideoStatementV2Data videoStatementV2Data;
            long j;
            String formatSecondsToHHMMSS;
            long j2;
            String formatSecondsToHHMMSS2;
            VideoStatementV2Config videoStatementV2Config3;
            HashMap hashMap;
            Object validateCheck;
            HashMap hashMap2;
            HashMap hashMap3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                videoStatementV2Config = this.this$0.vsConfigV2;
                if (videoStatementV2Config.getLogVideoStatementV2API() != null) {
                    videoStatementV2Config2 = this.this$0.vsConfigV2;
                    List<WorkflowModule.Properties.RequestParam> parameters = videoStatementV2Config2.getLogVideoStatementV2API().getParameters();
                    VideoStatementV2VM videoStatementV2VM = this.this$0;
                    for (WorkflowModule.Properties.RequestParam requestParam : parameters) {
                        hashMap3 = videoStatementV2VM.finalLogMap;
                        hashMap3.put(requestParam.getName(), requestParam.getValue());
                    }
                    videoStatementV2Data = this.this$0.videoStatementV2Data;
                    String videoUrl$hyperkyc_release = videoStatementV2Data.getVideoUrl$hyperkyc_release();
                    if (videoUrl$hyperkyc_release != null) {
                        hashMap2 = this.this$0.finalLogMap;
                        hashMap2.put("videoRef", videoUrl$hyperkyc_release);
                    }
                    this.this$0.addValueInLogMap("statementId", "id-0+0");
                    VideoStatementV2VM videoStatementV2VM2 = this.this$0;
                    j = videoStatementV2VM2.startTimestamp;
                    formatSecondsToHHMMSS = videoStatementV2VM2.formatSecondsToHHMMSS(j);
                    videoStatementV2VM2.addValueInLogMap("startTimestamp", formatSecondsToHHMMSS);
                    VideoStatementV2VM videoStatementV2VM3 = this.this$0;
                    j2 = videoStatementV2VM3.endTimestamp;
                    formatSecondsToHHMMSS2 = videoStatementV2VM3.formatSecondsToHHMMSS(j2);
                    videoStatementV2VM3.addValueInLogMap("endTimestamp", formatSecondsToHHMMSS2);
                    VideoStatementV2VM videoStatementV2VM4 = this.this$0;
                    videoStatementV2Config3 = videoStatementV2VM4.vsConfigV2;
                    WorkflowModule.Properties.VideoStatementV2API logVideoStatementV2API = videoStatementV2Config3.getLogVideoStatementV2API();
                    hashMap = this.this$0.finalLogMap;
                    this.label = 1;
                    validateCheck = videoStatementV2VM4.validateCheck(VideoStatementV2VM.createAPICallState$default(videoStatementV2VM4, logVideoStatementV2API, WorkflowRequestType.JSON, hashMap, null, 8, null), VideoStatementV2VM.RequestType.LOG_VIDEO_STATEMENT, this.$finishWithErrorCallback, this);
                    if (validateCheck == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    this.$finishWithErrorCallback.invoke("error", Boxing.boxInt(104), "API call failed!");
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
