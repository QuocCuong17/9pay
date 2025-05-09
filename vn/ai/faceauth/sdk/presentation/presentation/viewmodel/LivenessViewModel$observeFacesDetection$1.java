package vn.ai.faceauth.sdk.presentation.presentation.viewmodel;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "vn.ai.faceauth.sdk.presentation.presentation.viewmodel.LivenessViewModel$observeFacesDetection$1", f = "LivenessViewModel.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class LivenessViewModel$observeFacesDetection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<List<FaceResult>> $facesFlowable;
    int label;
    final /* synthetic */ LivenessViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LivenessViewModel$observeFacesDetection$1(Flow<? extends List<FaceResult>> flow, LivenessViewModel livenessViewModel, Continuation<? super LivenessViewModel$observeFacesDetection$1> continuation) {
        super(2, continuation);
        this.$facesFlowable = flow;
        this.this$0 = livenessViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LivenessViewModel$observeFacesDetection$1(this.$facesFlowable, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LivenessViewModel$observeFacesDetection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<List<FaceResult>> flow = this.$facesFlowable;
            final LivenessViewModel livenessViewModel = this.this$0;
            FlowCollector<? super List<FaceResult>> flowCollector = new FlowCollector() { // from class: vn.ai.faceauth.sdk.presentation.presentation.viewmodel.LivenessViewModel$observeFacesDetection$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((List<FaceResult>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(List<FaceResult> list, Continuation<? super Unit> continuation) {
                    if (!LivenessViewModel.this.getIsFinished()) {
                        LivenessViewModel.this.handleFaces(list);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (flow.collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException(tfwhgw.rnigpa(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256));
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
