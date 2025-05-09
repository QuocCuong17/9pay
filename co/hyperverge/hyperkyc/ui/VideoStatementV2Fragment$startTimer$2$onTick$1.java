package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementV2Binding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VideoStatementV2Fragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startTimer$2$onTick$1", f = "VideoStatementV2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class VideoStatementV2Fragment$startTimer$2$onTick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoStatementV2Fragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2Fragment$startTimer$2$onTick$1(VideoStatementV2Fragment videoStatementV2Fragment, Continuation<? super VideoStatementV2Fragment$startTimer$2$onTick$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2Fragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementV2Fragment$startTimer$2$onTick$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2Fragment$startTimer$2$onTick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final HkFragmentVideoStatementV2Binding binding;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            binding = this.this$0.getBinding();
            final VideoStatementV2Fragment videoStatementV2Fragment = this.this$0;
            binding.btnSecondary.setClickable(true);
            binding.btnSecondary.getBackground().setAlpha(255);
            binding.btnSecondary.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startTimer$2$onTick$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VideoStatementV2Fragment$startTimer$2$onTick$1.invokeSuspend$lambda$1$lambda$0(HkFragmentVideoStatementV2Binding.this, videoStatementV2Fragment, view);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1$lambda$0(HkFragmentVideoStatementV2Binding hkFragmentVideoStatementV2Binding, VideoStatementV2Fragment videoStatementV2Fragment, View view) {
        hkFragmentVideoStatementV2Binding.btnSecondary.setOnClickListener(null);
        videoStatementV2Fragment.stopRecording();
    }
}
