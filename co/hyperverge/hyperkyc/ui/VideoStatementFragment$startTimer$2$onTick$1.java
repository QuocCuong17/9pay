package co.hyperverge.hyperkyc.ui;

import android.widget.TextView;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementBinding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VideoStatementFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementFragment$startTimer$2$onTick$1", f = "VideoStatementFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class VideoStatementFragment$startTimer$2$onTick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $secondsRemaining;
    int label;
    final /* synthetic */ VideoStatementFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementFragment$startTimer$2$onTick$1(VideoStatementFragment videoStatementFragment, long j, Continuation<? super VideoStatementFragment$startTimer$2$onTick$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementFragment;
        this.$secondsRemaining = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementFragment$startTimer$2$onTick$1(this.this$0, this.$secondsRemaining, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementFragment$startTimer$2$onTick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        HkFragmentVideoStatementBinding binding;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            binding = this.this$0.getBinding();
            VideoStatementFragment videoStatementFragment = this.this$0;
            long j = this.$secondsRemaining;
            TextView textView = binding.tvTitle;
            StringBuilder sb = new StringBuilder();
            str = videoStatementFragment.currentHelpText;
            sb.append(str);
            sb.append(" - ");
            sb.append(j);
            sb.append("s remaining");
            textView.setText(sb.toString());
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
