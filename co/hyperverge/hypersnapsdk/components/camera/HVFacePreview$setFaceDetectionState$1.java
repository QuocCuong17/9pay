package co.hyperverge.hypersnapsdk.components.camera;

import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.components.camera.model.FaceStateUIFlow;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.views.CircularProgressBar;
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
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: HVFacePreview.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hypersnapsdk.components.camera.HVFacePreview$setFaceDetectionState$1", f = "HVFacePreview.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HVFacePreview$setFaceDetectionState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FaceConstants.FaceDetectionState $faceDetectionState;
    int label;
    final /* synthetic */ HVFacePreview this$0;

    /* compiled from: HVFacePreview.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FaceConstants.FaceDetectionState.values().length];
            iArr[FaceConstants.FaceDetectionState.FACE_DETECTED.ordinal()] = 1;
            iArr[FaceConstants.FaceDetectionState.FACE_NOT_DETECTED.ordinal()] = 2;
            iArr[FaceConstants.FaceDetectionState.FACE_TOO_CLOSE.ordinal()] = 3;
            iArr[FaceConstants.FaceDetectionState.MULTIPLE_FACES.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVFacePreview$setFaceDetectionState$1(FaceConstants.FaceDetectionState faceDetectionState, HVFacePreview hVFacePreview, Continuation<? super HVFacePreview$setFaceDetectionState$1> continuation) {
        super(2, continuation);
        this.$faceDetectionState = faceDetectionState;
        this.this$0 = hVFacePreview;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HVFacePreview$setFaceDetectionState$1(this.$faceDetectionState, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HVFacePreview$setFaceDetectionState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        CircularProgressBar circularProgressBar;
        MutableStateFlow mutableStateFlow2;
        CircularProgressBar circularProgressBar2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FaceConstants.FaceDetectionState faceDetectionState = this.$faceDetectionState;
        int i = faceDetectionState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[faceDetectionState.ordinal()];
        CircularProgressBar circularProgressBar3 = null;
        if (i == 1) {
            mutableStateFlow = this.this$0.faceUIStateFlow;
            mutableStateFlow.tryEmit(FaceStateUIFlow.Detected.INSTANCE);
            circularProgressBar = this.this$0.progressBar;
            if (circularProgressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                circularProgressBar3 = circularProgressBar;
            }
            circularProgressBar3.setProgressColor(this.this$0.getResources().getColor(R.color.face_capture_circle_success));
        } else if (i == 2 || i == 3 || i == 4) {
            mutableStateFlow2 = this.this$0.faceUIStateFlow;
            mutableStateFlow2.tryEmit(FaceStateUIFlow.NotDetected.INSTANCE);
            circularProgressBar2 = this.this$0.progressBar;
            if (circularProgressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                circularProgressBar3 = circularProgressBar2;
            }
            circularProgressBar3.setProgressColor(this.this$0.getResources().getColor(R.color.face_capture_circle_failure));
        }
        return Unit.INSTANCE;
    }
}
