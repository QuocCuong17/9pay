package vn.ai.faceauth.sdk.presentation.presentation.fragment;

import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.CameraX;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$forceToRetryCapture$1$1", f = "CameraXFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class CameraXFragment$forceToRetryCapture$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CameraXFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraXFragment$forceToRetryCapture$1$1(CameraXFragment cameraXFragment, Continuation<? super CameraXFragment$forceToRetryCapture$1$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraXFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraXFragment$forceToRetryCapture$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraXFragment$forceToRetryCapture$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CameraX cameraX;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException(tfwhgw.rnigpa(307));
        }
        ResultKt.throwOnFailure(obj);
        cameraX = this.this$0.getCameraX();
        final CameraXFragment cameraXFragment = this.this$0;
        cameraX.takePicture(new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$forceToRetryCapture$1$1.1
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
                String str;
                str = CameraXFragment.this.TAG;
                Log.e(str, tfwhgw.rnigpa(82));
                CameraXFragment.this.handleFinished();
            }
        });
        return Unit.INSTANCE;
    }
}
