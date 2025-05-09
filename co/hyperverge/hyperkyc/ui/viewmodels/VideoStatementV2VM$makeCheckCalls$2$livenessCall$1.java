package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2VM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeCheckCalls$2$livenessCall$1", f = "VideoStatementV2VM.kt", i = {}, l = {201}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementV2VM$makeCheckCalls$2$livenessCall$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
    int label;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VideoStatementV2VM$makeCheckCalls$2$livenessCall$1(VideoStatementV2VM videoStatementV2VM, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super VideoStatementV2VM$makeCheckCalls$2$livenessCall$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2VM;
        this.$finishWithErrorCallback = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementV2VM$makeCheckCalls$2$livenessCall$1(this.this$0, this.$finishWithErrorCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2VM$makeCheckCalls$2$livenessCall$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VideoStatementV2Config videoStatementV2Config;
        boolean asBoolean;
        VideoStatementV2Config videoStatementV2Config2;
        String str;
        VideoStatementV2Config videoStatementV2Config3;
        String str2;
        VideoStatementV2Config videoStatementV2Config4;
        Object validateCheck;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 liveness;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoStatementV2VM videoStatementV2VM = this.this$0;
            videoStatementV2Config = videoStatementV2VM.vsConfigV2;
            WorkflowModule.Properties.StatementV2 statement = videoStatementV2Config.getStatement();
            asBoolean = videoStatementV2VM.asBoolean((statement == null || (checks = statement.getChecks()) == null || (liveness = checks.getLiveness()) == null) ? null : liveness.getEnable(), false);
            if (asBoolean) {
                videoStatementV2Config2 = this.this$0.vsConfigV2;
                if (videoStatementV2Config2.getLivenessAPI() != null) {
                    str = this.this$0.imageUri;
                    if (str != null) {
                        videoStatementV2Config3 = this.this$0.vsConfigV2;
                        List mutableList = CollectionsKt.toMutableList((Collection) videoStatementV2Config3.getLivenessAPI().getParameters());
                        str2 = this.this$0.imageUri;
                        Intrinsics.checkNotNull(str2);
                        mutableList.add(new WorkflowModule.Properties.RequestParam("image", str2, "image"));
                        VideoStatementV2VM videoStatementV2VM2 = this.this$0;
                        videoStatementV2Config4 = videoStatementV2VM2.vsConfigV2;
                        this.label = 1;
                        validateCheck = videoStatementV2VM2.validateCheck(VideoStatementV2VM.createAPICallState$default(videoStatementV2VM2, videoStatementV2Config4.getLivenessAPI(), WorkflowRequestType.MULTIPART, null, mutableList, 4, null), VideoStatementV2VM.RequestType.LIVENESS, this.$finishWithErrorCallback, this);
                        if (validateCheck == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.this$0.isLivenessCheckFailed = true;
                    }
                } else {
                    this.$finishWithErrorCallback.invoke("error", Boxing.boxInt(104), "API call failed!");
                }
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
