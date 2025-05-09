package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okhttp3.RequestBody;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementVM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM$makeSpeechToTextMatchAPICall$3", f = "VideoStatementVM.kt", i = {}, l = {826}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementVM$makeSpeechToTextMatchAPICall$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RequestBody $requestBody;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$makeSpeechToTextMatchAPICall$3(VideoStatementVM videoStatementVM, RequestBody requestBody, Continuation<? super VideoStatementVM$makeSpeechToTextMatchAPICall$3> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementVM;
        this.$requestBody = requestBody;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoStatementVM$makeSpeechToTextMatchAPICall$3 videoStatementVM$makeSpeechToTextMatchAPICall$3 = new VideoStatementVM$makeSpeechToTextMatchAPICall$3(this.this$0, this.$requestBody, continuation);
        videoStatementVM$makeSpeechToTextMatchAPICall$3.L$0 = obj;
        return videoStatementVM$makeSpeechToTextMatchAPICall$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementVM$makeSpeechToTextMatchAPICall$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Deferred async$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            async$default = BuildersKt__Builders_commonKt.async$default((CoroutineScope) this.L$0, null, null, new VideoStatementVM$makeSpeechToTextMatchAPICall$3$deferred$1(this.this$0, this.$requestBody, null), 3, null);
            this.this$0.addDeferred(async$default, VideoStatementVM.RequestType.SPEECH_TO_TEXT_MATCH);
            this.label = 1;
            if (async$default.await(this) == coroutine_suspended) {
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
