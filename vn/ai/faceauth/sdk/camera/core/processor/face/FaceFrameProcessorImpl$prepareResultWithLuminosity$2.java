package vn.ai.faceauth.sdk.camera.core.processor.face;

import androidx.camera.core.ImageProxy;
import com.google.mlkit.vision.face.Face;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.domain.mapper.FaceMapperKt;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.core.extensions.ImageExtensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessorImpl$prepareResultWithLuminosity$2", f = "FaceFrameProcessorImpl.kt", i = {}, l = {47}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class FaceFrameProcessorImpl$prepareResultWithLuminosity$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ImageProxy $imageProxy;
    final /* synthetic */ List<Face> $listFace;
    int label;
    final /* synthetic */ FaceFrameProcessorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FaceFrameProcessorImpl$prepareResultWithLuminosity$2(ImageProxy imageProxy, List<? extends Face> list, FaceFrameProcessorImpl faceFrameProcessorImpl, Continuation<? super FaceFrameProcessorImpl$prepareResultWithLuminosity$2> continuation) {
        super(2, continuation);
        this.$imageProxy = imageProxy;
        this.$listFace = list;
        this.this$0 = faceFrameProcessorImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FaceFrameProcessorImpl$prepareResultWithLuminosity$2(this.$imageProxy, this.$listFace, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FaceFrameProcessorImpl$prepareResultWithLuminosity$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableSharedFlow mutableSharedFlow;
        FaceResult addLuminosity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            float luminosity = ImageExtensionsKt.getLuminosity(this.$imageProxy.getImage());
            List<Face> list = this.$listFace;
            FaceFrameProcessorImpl faceFrameProcessorImpl = this.this$0;
            ImageProxy imageProxy = this.$imageProxy;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                addLuminosity = faceFrameProcessorImpl.addLuminosity(FaceMapperKt.toFaceResult((Face) it.next(), imageProxy.getImage()), imageProxy.getImage(), luminosity);
                arrayList.add(addLuminosity);
            }
            this.$imageProxy.close();
            mutableSharedFlow = this.this$0.facesBroadcastChannel;
            this.label = 1;
            if (mutableSharedFlow.emit(arrayList, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException(tfwhgw.rnigpa(352));
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
