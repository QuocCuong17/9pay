package co.hyperverge.hyperkyc.ui;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VideoStatementV2Fragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$Check;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1", f = "VideoStatementV2Fragment.kt", i = {}, l = {TypedValues.TransitionType.TYPE_TRANSITION_FLAGS}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super VideoStatementV2VM.Check>, Object> {
    final /* synthetic */ Ref.BooleanRef $isError;
    int label;
    final /* synthetic */ VideoStatementV2Fragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1(VideoStatementV2Fragment videoStatementV2Fragment, Ref.BooleanRef booleanRef, Continuation<? super VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2Fragment;
        this.$isError = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1(this.this$0, this.$isError, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super VideoStatementV2VM.Check> continuation) {
        return ((VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        VideoStatementV2VM videoStatementV2VM;
        String str;
        String str2;
        String str3;
        String str4;
        long j;
        long j2;
        boolean z;
        boolean z2;
        boolean z3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        videoStatementV2VM = this.this$0.vsV2VM;
        if (videoStatementV2VM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vsV2VM");
            videoStatementV2VM = null;
        }
        VideoStatementV2VM videoStatementV2VM2 = videoStatementV2VM;
        str = this.this$0.imageUri;
        str2 = this.this$0.videoUri;
        str3 = this.this$0.audioUri;
        str4 = this.this$0.statementText;
        j = this.this$0.startTimestamp;
        j2 = this.this$0.endTimestamp;
        z = this.this$0.isFaceDetectionCheckFailed;
        if (!z) {
            z3 = this.this$0.isSelfieCaptured;
            if (z3) {
                z2 = false;
                final VideoStatementV2Fragment videoStatementV2Fragment = this.this$0;
                Function0<Unit> function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (ActivityExtsKt.isFragmentAliveAndAttached(VideoStatementV2Fragment.this)) {
                            VideoStatementV2Fragment.this.updateVideoUploadText();
                        }
                    }
                };
                final Ref.BooleanRef booleanRef = this.$isError;
                final VideoStatementV2Fragment videoStatementV2Fragment2 = this.this$0;
                this.label = 1;
                Object startProcessing$hyperkyc_release = videoStatementV2VM2.startProcessing$hyperkyc_release(str, str2, str3, str4, j, j2, z2, function0, new Function3<String, Integer, String, Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(String str5, Integer num, String str6) {
                        invoke(str5, num.intValue(), str6);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(String error, int i2, String errorMessage) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                        Ref.BooleanRef.this.element = true;
                        if (ActivityExtsKt.isFragmentAliveAndAttached(videoStatementV2Fragment2)) {
                            BaseFragment.finishWithResult$default(videoStatementV2Fragment2, error, null, Integer.valueOf(i2), errorMessage, false, false, 50, null);
                        }
                    }
                }, this);
                return startProcessing$hyperkyc_release != coroutine_suspended ? coroutine_suspended : startProcessing$hyperkyc_release;
            }
        }
        z2 = true;
        final VideoStatementV2Fragment videoStatementV2Fragment3 = this.this$0;
        Function0<Unit> function02 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (ActivityExtsKt.isFragmentAliveAndAttached(VideoStatementV2Fragment.this)) {
                    VideoStatementV2Fragment.this.updateVideoUploadText();
                }
            }
        };
        final Ref.BooleanRef booleanRef2 = this.$isError;
        final VideoStatementV2Fragment videoStatementV2Fragment22 = this.this$0;
        this.label = 1;
        Object startProcessing$hyperkyc_release2 = videoStatementV2VM2.startProcessing$hyperkyc_release(str, str2, str3, str4, j, j2, z2, function02, new Function3<String, Integer, String, Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str5, Integer num, String str6) {
                invoke(str5, num.intValue(), str6);
                return Unit.INSTANCE;
            }

            public final void invoke(String error, int i2, String errorMessage) {
                Intrinsics.checkNotNullParameter(error, "error");
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Ref.BooleanRef.this.element = true;
                if (ActivityExtsKt.isFragmentAliveAndAttached(videoStatementV2Fragment22)) {
                    BaseFragment.finishWithResult$default(videoStatementV2Fragment22, error, null, Integer.valueOf(i2), errorMessage, false, false, 50, null);
                }
            }
        }, this);
        if (startProcessing$hyperkyc_release2 != coroutine_suspended) {
        }
    }
}
