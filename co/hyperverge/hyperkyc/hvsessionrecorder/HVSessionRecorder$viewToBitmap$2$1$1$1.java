package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.graphics.Bitmap;
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

/* compiled from: HVSessionRecorder.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$2$1$1$1", f = "HVSessionRecorder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HVSessionRecorder$viewToBitmap$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $scaledBitmap;
    int label;
    final /* synthetic */ HVSessionRecorder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVSessionRecorder$viewToBitmap$2$1$1$1(HVSessionRecorder hVSessionRecorder, Bitmap bitmap, Continuation<? super HVSessionRecorder$viewToBitmap$2$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = hVSessionRecorder;
        this.$scaledBitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HVSessionRecorder$viewToBitmap$2$1$1$1(this.this$0, this.$scaledBitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HVSessionRecorder$viewToBitmap$2$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HVSessionRecorder hVSessionRecorder = this.this$0;
        Bitmap scaledBitmap = this.$scaledBitmap;
        Intrinsics.checkNotNullExpressionValue(scaledBitmap, "scaledBitmap");
        hVSessionRecorder.queueBitmap(scaledBitmap);
        return Unit.INSTANCE;
    }
}
