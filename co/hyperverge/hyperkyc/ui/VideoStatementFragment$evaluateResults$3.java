package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.ui.VideoStatementFragment;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementFragment$evaluateResults$3", f = "VideoStatementFragment.kt", i = {}, l = {434}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementFragment$evaluateResults$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoStatementFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementFragment$evaluateResults$3(VideoStatementFragment videoStatementFragment, Continuation<? super VideoStatementFragment$evaluateResults$3> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementFragment$evaluateResults$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementFragment$evaluateResults$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VideoStatementVM videoStatementVM;
        VideoStatementVM videoStatementVM2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        VideoStatementVM videoStatementVM3 = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoStatementFragment videoStatementFragment = this.this$0;
            VideoStatementFragment.VideoStatementState videoStatementState = VideoStatementFragment.VideoStatementState.ERROR;
            videoStatementVM = this.this$0.videoStatementVM;
            if (videoStatementVM == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
                videoStatementVM = null;
            }
            videoStatementFragment.updateUIState(videoStatementState, videoStatementVM.getFailedCheck());
            this.label = 1;
            if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        videoStatementVM2 = this.this$0.videoStatementVM;
        if (videoStatementVM2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStatementVM");
        } else {
            videoStatementVM3 = videoStatementVM2;
        }
        if (videoStatementVM3.handleRetry()) {
            this.this$0.startStatement();
        } else {
            this.this$0.finish();
        }
        return Unit.INSTANCE;
    }
}
