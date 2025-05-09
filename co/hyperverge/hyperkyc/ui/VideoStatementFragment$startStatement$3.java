package co.hyperverge.hyperkyc.ui;

import android.content.Context;
import android.os.CountDownTimer;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.VideoStatementFragment;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM;
import co.hyperverge.hypersnapsdk.components.camera.HVFacePreview;
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
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementFragment$startStatement$3", f = "VideoStatementFragment.kt", i = {}, l = {219, 230}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementFragment$startStatement$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoStatementFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementFragment$startStatement$3(VideoStatementFragment videoStatementFragment, Continuation<? super VideoStatementFragment$startStatement$3> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementFragment$startStatement$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementFragment$startStatement$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VideoStatementVM videoStatementVM;
        VideoStatementVM videoStatementVM2;
        VideoStatementVM videoStatementVM3;
        Object evaluateResults;
        CountDownTimer countDownTimer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoStatementFragment.updateUIState$default(this.this$0, VideoStatementFragment.VideoStatementState.STATEMENT, null, 2, null);
            videoStatementVM = this.this$0.videoStatementVM;
            if (videoStatementVM == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                videoStatementVM = null;
            }
            if (videoStatementVM.getShouldDisplayTimer()) {
                VideoStatementFragment videoStatementFragment = this.this$0;
                videoStatementVM2 = videoStatementFragment.videoStatementVM;
                if (videoStatementVM2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                    videoStatementVM2 = null;
                }
                videoStatementFragment.startTimer(videoStatementVM2.getStatementDuration());
            }
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getDefault(), new AnonymousClass1(this.this$0, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        videoStatementVM3 = this.this$0.videoStatementVM;
        if (videoStatementVM3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
            videoStatementVM3 = null;
        }
        if (videoStatementVM3.getShouldDisplayTimer()) {
            countDownTimer = this.this$0.timer;
            if (countDownTimer == null) {
                Intrinsics.throwUninitializedPropertyAccessException(WorkflowModule.Properties.Section.Component.Type.TIMER);
                countDownTimer = null;
            }
            countDownTimer.cancel();
        }
        VideoStatementFragment.updateUIState$default(this.this$0, VideoStatementFragment.VideoStatementState.VERIFY, null, 2, null);
        this.label = 2;
        evaluateResults = this.this$0.evaluateResults(this);
        if (evaluateResults == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VideoStatementFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementFragment$startStatement$3$1", f = "VideoStatementFragment.kt", i = {}, l = {220}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.VideoStatementFragment$startStatement$3$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ VideoStatementFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(VideoStatementFragment videoStatementFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = videoStatementFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            VideoStatementVM videoStatementVM;
            HVFacePreview hVFacePreview;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                videoStatementVM = this.this$0.videoStatementVM;
                HVFacePreview hVFacePreview2 = null;
                if (videoStatementVM == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                    videoStatementVM = null;
                }
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                hVFacePreview = this.this$0.facePreview;
                if (hVFacePreview == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("facePreview");
                } else {
                    hVFacePreview2 = hVFacePreview;
                }
                this.label = 1;
                if (videoStatementVM.startStatement(requireContext, hVFacePreview2, this) == coroutine_suspended) {
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
}
