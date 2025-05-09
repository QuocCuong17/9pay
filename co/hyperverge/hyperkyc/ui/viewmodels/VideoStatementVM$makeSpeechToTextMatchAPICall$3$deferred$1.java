package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hyperkyc.data.models.VideoStatementConfig;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.RequestBody;

/* compiled from: VideoStatementVM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1", f = "VideoStatementVM.kt", i = {}, l = {819}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ RequestBody $requestBody;
    int label;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1(VideoStatementVM videoStatementVM, RequestBody requestBody, Continuation<? super VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementVM;
        this.$requestBody = requestBody;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1(this.this$0, this.$requestBody, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VideoStatementConfig videoStatementConfig;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoStatementVM videoStatementVM = this.this$0;
            videoStatementConfig = videoStatementVM.videoStatementConfig;
            this.label = 1;
            obj = videoStatementVM.sendRequest(videoStatementConfig.getSpeechToTextMatchUrl(), this.$requestBody, VideoStatementVM.RequestType.SPEECH_TO_TEXT_MATCH, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
