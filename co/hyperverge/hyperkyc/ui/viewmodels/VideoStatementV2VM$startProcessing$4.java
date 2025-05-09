package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2VM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$startProcessing$4", f = "VideoStatementV2VM.kt", i = {}, l = {CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementV2VM$startProcessing$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
    final /* synthetic */ String $imageUri;
    final /* synthetic */ boolean $isFaceDetectionCheckFailed;
    final /* synthetic */ Function0<Unit> $processingTextChangeCallback;
    final /* synthetic */ String $statementText;
    final /* synthetic */ String $videoUri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VideoStatementV2VM$startProcessing$4(VideoStatementV2VM videoStatementV2VM, boolean z, Function3<? super String, ? super Integer, ? super String, Unit> function3, String str, String str2, String str3, Function0<Unit> function0, Continuation<? super VideoStatementV2VM$startProcessing$4> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2VM;
        this.$isFaceDetectionCheckFailed = z;
        this.$finishWithErrorCallback = function3;
        this.$imageUri = str;
        this.$videoUri = str2;
        this.$statementText = str3;
        this.$processingTextChangeCallback = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoStatementV2VM$startProcessing$4 videoStatementV2VM$startProcessing$4 = new VideoStatementV2VM$startProcessing$4(this.this$0, this.$isFaceDetectionCheckFailed, this.$finishWithErrorCallback, this.$imageUri, this.$videoUri, this.$statementText, this.$processingTextChangeCallback, continuation);
        videoStatementV2VM$startProcessing$4.L$0 = obj;
        return videoStatementV2VM$startProcessing$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2VM$startProcessing$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a1 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        VideoStatementV2Config videoStatementV2Config;
        boolean asBoolean;
        Deferred async$default;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 faceDetection;
        Object makeStandardAPICalls;
        HyperKycData.VideoStatementV2Data videoStatementV2Data;
        HyperKycData.VideoStatementV2Data videoStatementV2Data2;
        HyperKycData.VideoStatementV2Data videoStatementV2Data3;
        HyperKycData.VideoStatementV2Data videoStatementV2Data4;
        MainVM mainVM;
        WorkflowUIState.VideoStatementV2 videoStatementV2;
        HyperKycData.VideoStatementV2Data videoStatementV2Data5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            VideoStatementV2VM videoStatementV2VM = this.this$0;
            videoStatementV2Config = videoStatementV2VM.vsConfigV2;
            WorkflowModule.Properties.StatementV2 statement = videoStatementV2Config.getStatement();
            asBoolean = videoStatementV2VM.asBoolean((statement == null || (checks = statement.getChecks()) == null || (faceDetection = checks.getFaceDetection()) == null) ? null : faceDetection.getEnable(), false);
            if (asBoolean) {
                this.this$0.addFaceDetectionDataToLog();
            }
            if (!this.$isFaceDetectionCheckFailed) {
                async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass1(this.this$0, this.$finishWithErrorCallback, null), 3, null);
                this.label = 1;
                if (async$default.await(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    int attemptsCount$hyperkyc_release = this.this$0.getAttemptsCount$hyperkyc_release() + 1;
                    this.this$0.setAttemptsCount(attemptsCount$hyperkyc_release);
                    videoStatementV2Data = this.this$0.videoStatementV2Data;
                    videoStatementV2Data.setImagePath$hyperkyc_release(this.$imageUri);
                    videoStatementV2Data2 = this.this$0.videoStatementV2Data;
                    videoStatementV2Data2.setVideoPath$hyperkyc_release(this.$videoUri);
                    videoStatementV2Data3 = this.this$0.videoStatementV2Data;
                    videoStatementV2Data3.setStatement$hyperkyc_release(this.$statementText);
                    videoStatementV2Data4 = this.this$0.videoStatementV2Data;
                    videoStatementV2Data4.setAttemptsCount$hyperkyc_release(String.valueOf(attemptsCount$hyperkyc_release));
                    mainVM = this.this$0.mainVM;
                    videoStatementV2 = this.this$0.uiState;
                    videoStatementV2Data5 = this.this$0.videoStatementV2Data;
                    MainVM.updateVideoStatementV2Data$hyperkyc_release$default(mainVM, videoStatementV2, videoStatementV2Data5, false, 4, null);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                this.label = 3;
                makeStandardAPICalls = this.this$0.makeStandardAPICalls(this.$finishWithErrorCallback, this);
                if (makeStandardAPICalls == coroutine_suspended) {
                    return coroutine_suspended;
                }
                int attemptsCount$hyperkyc_release2 = this.this$0.getAttemptsCount$hyperkyc_release() + 1;
                this.this$0.setAttemptsCount(attemptsCount$hyperkyc_release2);
                videoStatementV2Data = this.this$0.videoStatementV2Data;
                videoStatementV2Data.setImagePath$hyperkyc_release(this.$imageUri);
                videoStatementV2Data2 = this.this$0.videoStatementV2Data;
                videoStatementV2Data2.setVideoPath$hyperkyc_release(this.$videoUri);
                videoStatementV2Data3 = this.this$0.videoStatementV2Data;
                videoStatementV2Data3.setStatement$hyperkyc_release(this.$statementText);
                videoStatementV2Data4 = this.this$0.videoStatementV2Data;
                videoStatementV2Data4.setAttemptsCount$hyperkyc_release(String.valueOf(attemptsCount$hyperkyc_release2));
                mainVM = this.this$0.mainVM;
                videoStatementV2 = this.this$0.uiState;
                videoStatementV2Data5 = this.this$0.videoStatementV2Data;
                MainVM.updateVideoStatementV2Data$hyperkyc_release$default(mainVM, videoStatementV2, videoStatementV2Data5, false, 4, null);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.label = 2;
        if (CoroutineExtsKt.onUI$default(null, new AnonymousClass2(this.$processingTextChangeCallback, null), this, 1, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.label = 3;
        makeStandardAPICalls = this.this$0.makeStandardAPICalls(this.$finishWithErrorCallback, this);
        if (makeStandardAPICalls == coroutine_suspended) {
        }
        int attemptsCount$hyperkyc_release22 = this.this$0.getAttemptsCount$hyperkyc_release() + 1;
        this.this$0.setAttemptsCount(attemptsCount$hyperkyc_release22);
        videoStatementV2Data = this.this$0.videoStatementV2Data;
        videoStatementV2Data.setImagePath$hyperkyc_release(this.$imageUri);
        videoStatementV2Data2 = this.this$0.videoStatementV2Data;
        videoStatementV2Data2.setVideoPath$hyperkyc_release(this.$videoUri);
        videoStatementV2Data3 = this.this$0.videoStatementV2Data;
        videoStatementV2Data3.setStatement$hyperkyc_release(this.$statementText);
        videoStatementV2Data4 = this.this$0.videoStatementV2Data;
        videoStatementV2Data4.setAttemptsCount$hyperkyc_release(String.valueOf(attemptsCount$hyperkyc_release22));
        mainVM = this.this$0.mainVM;
        videoStatementV2 = this.this$0.uiState;
        videoStatementV2Data5 = this.this$0.videoStatementV2Data;
        MainVM.updateVideoStatementV2Data$hyperkyc_release$default(mainVM, videoStatementV2, videoStatementV2Data5, false, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VideoStatementV2VM.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$startProcessing$4$1", f = "VideoStatementV2VM.kt", i = {}, l = {CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$startProcessing$4$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
        int label;
        final /* synthetic */ VideoStatementV2VM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(VideoStatementV2VM videoStatementV2VM, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = videoStatementV2VM;
            this.$finishWithErrorCallback = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$finishWithErrorCallback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object makeCheckCalls;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                makeCheckCalls = this.this$0.makeCheckCalls(this.$finishWithErrorCallback, this);
                if (makeCheckCalls == coroutine_suspended) {
                    return coroutine_suspended;
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
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$startProcessing$4$2", f = "VideoStatementV2VM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$startProcessing$4$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $processingTextChangeCallback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function0<Unit> function0, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$processingTextChangeCallback = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$processingTextChangeCallback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$processingTextChangeCallback.invoke();
            return Unit.INSTANCE;
        }
    }
}
