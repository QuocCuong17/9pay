package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hypersnapsdk.components.camera.HVFacePreview;
import co.hyperverge.hypersnapsdk.components.camera.model.FaceStateUIFlow;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementVM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$observeUIState$2", f = "VideoStatementVM.kt", i = {}, l = {314}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementVM$observeUIState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HVFacePreview $facePreview;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$observeUIState$2(HVFacePreview hVFacePreview, VideoStatementVM videoStatementVM, Continuation<? super VideoStatementVM$observeUIState$2> continuation) {
        super(2, continuation);
        this.$facePreview = hVFacePreview;
        this.this$0 = videoStatementVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoStatementVM$observeUIState$2 videoStatementVM$observeUIState$2 = new VideoStatementVM$observeUIState$2(this.$facePreview, this.this$0, continuation);
        videoStatementVM$observeUIState$2.L$0 = obj;
        return videoStatementVM$observeUIState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementVM$observeUIState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScopeKt.ensureActive((CoroutineScope) this.L$0);
            Flow filterNotNull = FlowKt.filterNotNull(this.$facePreview.getUiStateFlow());
            final VideoStatementVM videoStatementVM = this.this$0;
            final HVFacePreview hVFacePreview = this.$facePreview;
            this.label = 1;
            if (filterNotNull.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$observeUIState$2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((FaceStateUIFlow) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(FaceStateUIFlow faceStateUIFlow, Continuation<? super Unit> continuation) {
                    boolean z;
                    boolean isFaceDetectionEnabled;
                    boolean z2;
                    Timer timer;
                    long faceDetectionTimeout;
                    boolean z3;
                    Timer timer2;
                    boolean z4;
                    boolean z5;
                    if (faceStateUIFlow instanceof FaceStateUIFlow.Detected) {
                        z3 = VideoStatementVM.this.shouldListenForFaceDetection;
                        if (z3) {
                            timer2 = VideoStatementVM.this.faceDetectionTimer;
                            if (timer2 != null) {
                                timer2.cancel();
                            }
                            z4 = VideoStatementVM.this.isFaceDetectionTimedOut;
                            if (!z4) {
                                VideoStatementVM.this.isFaceDetectionPass = true;
                            }
                            z5 = VideoStatementVM.this.isSelfieCaptured;
                            if (!z5) {
                                hVFacePreview.clickPicture();
                            }
                        }
                    } else if (faceStateUIFlow instanceof FaceStateUIFlow.NotDetected) {
                        isFaceDetectionEnabled = VideoStatementVM.this.isFaceDetectionEnabled();
                        if (isFaceDetectionEnabled) {
                            z2 = VideoStatementVM.this.shouldListenForFaceDetection;
                            if (z2) {
                                VideoStatementVM.this.faceDetectionTimer = new Timer();
                                timer = VideoStatementVM.this.faceDetectionTimer;
                                if (timer != null) {
                                    final VideoStatementVM videoStatementVM2 = VideoStatementVM.this;
                                    TimerTask timerTask = new TimerTask() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$observeUIState$2$1$emit$$inlined$timerTask$1
                                        @Override // java.util.TimerTask, java.lang.Runnable
                                        public void run() {
                                            VideoStatementVM.this.isFaceDetectionPass = false;
                                            VideoStatementVM.this.isFaceDetectionTimedOut = true;
                                        }
                                    };
                                    faceDetectionTimeout = VideoStatementVM.this.getFaceDetectionTimeout();
                                    timer.schedule(timerTask, faceDetectionTimeout);
                                }
                            }
                        }
                    } else {
                        if (faceStateUIFlow instanceof FaceStateUIFlow.FaceCapture) {
                            VideoStatementVM.this.isSelfieCaptured = true;
                            z = VideoStatementVM.this.isFirstStatement;
                            if (z) {
                                VideoStatementVM videoStatementVM3 = VideoStatementVM.this;
                                String fullImageUri = ((FaceStateUIFlow.FaceCapture) faceStateUIFlow).getFullImageUri();
                                if (fullImageUri == null) {
                                    fullImageUri = "";
                                }
                                videoStatementVM3.refImageUri = fullImageUri;
                            }
                            String fullImageUri2 = ((FaceStateUIFlow.FaceCapture) faceStateUIFlow).getFullImageUri();
                            if (fullImageUri2 != null) {
                                VideoStatementVM.this.addImage(fullImageUri2);
                            }
                            Object withContext = BuildersKt.withContext(Dispatchers.getDefault(), new AnonymousClass3(VideoStatementVM.this, faceStateUIFlow, null), continuation);
                            return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
                        }
                        Intrinsics.areEqual(faceStateUIFlow, FaceStateUIFlow.CameraClosed.INSTANCE);
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: VideoStatementVM.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$observeUIState$2$1$3", f = "VideoStatementVM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$observeUIState$2$1$3, reason: invalid class name */
                /* loaded from: classes2.dex */
                public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ FaceStateUIFlow $it;
                    int label;
                    final /* synthetic */ VideoStatementVM this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass3(VideoStatementVM videoStatementVM, FaceStateUIFlow faceStateUIFlow, Continuation<? super AnonymousClass3> continuation) {
                        super(2, continuation);
                        this.this$0 = videoStatementVM;
                        this.$it = faceStateUIFlow;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass3(this.this$0, this.$it, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        VideoStatementVM videoStatementVM = this.this$0;
                        String fullImageUri = ((FaceStateUIFlow.FaceCapture) this.$it).getFullImageUri();
                        if (fullImageUri == null) {
                            fullImageUri = "";
                        }
                        videoStatementVM.makeLivenessAPICall(fullImageUri);
                        VideoStatementVM videoStatementVM2 = this.this$0;
                        String fullImageUri2 = ((FaceStateUIFlow.FaceCapture) this.$it).getFullImageUri();
                        videoStatementVM2.makeFaceMatchAPICall(fullImageUri2 != null ? fullImageUri2 : "");
                        return Unit.INSTANCE;
                    }
                }
            }, this) == coroutine_suspended) {
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
