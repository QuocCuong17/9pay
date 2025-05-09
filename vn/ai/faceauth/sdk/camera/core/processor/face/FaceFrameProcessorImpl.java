package vn.ai.faceauth.sdk.camera.core.processor.face;

import android.media.Image;
import android.util.Log;
import androidx.camera.core.ImageProxy;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.mlkit.vision.face.Face;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0012H\u0016J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J'\u0010\u0018\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0083@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessorImpl;", "Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "detector", "Lvn/ai/faceauth/sdk/camera/core/detector/VisionFaceDetector;", "(Lkotlinx/coroutines/CoroutineScope;Lvn/ai/faceauth/sdk/camera/core/detector/VisionFaceDetector;)V", "facesBroadcastChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "addLuminosity", "faceResult", "image", "Landroid/media/Image;", "luminosity", "", "observeFaceList", "Lkotlinx/coroutines/flow/Flow;", "onFrameCaptured", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "(Landroidx/camera/core/ImageProxy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareResultWithLuminosity", "listFace", "Lcom/google/mlkit/vision/face/Face;", "(Ljava/util/List;Landroidx/camera/core/ImageProxy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FaceFrameProcessorImpl implements FaceFrameProcessor {
    private final CoroutineScope coroutineScope;
    private final VisionFaceDetector detector;
    private final MutableSharedFlow<List<FaceResult>> facesBroadcastChannel = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);

    public FaceFrameProcessorImpl(CoroutineScope coroutineScope, VisionFaceDetector visionFaceDetector) {
        this.coroutineScope = coroutineScope;
        this.detector = visionFaceDetector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0002, code lost:
    
        r0 = r16.copy((r26 & 1) != 0 ? r16.trackingId : null, (r26 & 2) != 0 ? r16.bounds : null, (r26 & 4) != 0 ? r16.headEulerAngleX : 0.0f, (r26 & 8) != 0 ? r16.headEulerAngleY : 0.0f, (r26 & 16) != 0 ? r16.headEulerAngleZ : 0.0f, (r26 & 32) != 0 ? r16.smilingProbability : null, (r26 & 64) != 0 ? r16.rightEyeOpenProbability : null, (r26 & 128) != 0 ? r16.leftEyeOpenProbability : null, (r26 & 256) != 0 ? r16.luminosity : java.lang.Float.valueOf(r18), (r26 & 512) != 0 ? r16.imageRect : r17.getCropRect(), (r26 & 1024) != 0 ? r16.landmarkLeftEye : null, (r26 & 2048) != 0 ? r16.landmarkRightEye : null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final FaceResult addLuminosity(FaceResult faceResult, Image image, float luminosity) {
        FaceResult copy;
        return (image == null || copy == null) ? faceResult : copy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object prepareResultWithLuminosity(List<? extends Face> list, ImageProxy imageProxy, Continuation<? super Unit> continuation) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new FaceFrameProcessorImpl$prepareResultWithLuminosity$2(imageProxy, list, this, null), 3, null);
        return launch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? launch$default : Unit.INSTANCE;
    }

    @Override // vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessor
    public Flow<List<FaceResult>> observeFaceList() {
        return FlowKt.asSharedFlow(this.facesBroadcastChannel);
    }

    @Override // vn.ai.faceauth.sdk.camera.core.processor.FrameProcessor
    public Object onFrameCaptured(final ImageProxy imageProxy, Continuation<? super Unit> continuation) {
        this.detector.detect(imageProxy, new Function1<List<? extends Face>, Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessorImpl$onFrameCaptured$2

            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
            @DebugMetadata(c = "vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessorImpl$onFrameCaptured$2$1", f = "FaceFrameProcessorImpl.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessorImpl$onFrameCaptured$2$1, reason: invalid class name */
            /* loaded from: classes6.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ ImageProxy $imageProxy;
                final /* synthetic */ List<Face> $it;
                int label;
                final /* synthetic */ FaceFrameProcessorImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass1(FaceFrameProcessorImpl faceFrameProcessorImpl, List<? extends Face> list, ImageProxy imageProxy, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = faceFrameProcessorImpl;
                    this.$it = list;
                    this.$imageProxy = imageProxy;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$it, this.$imageProxy, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object prepareResultWithLuminosity;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FaceFrameProcessorImpl faceFrameProcessorImpl = this.this$0;
                        List<Face> list = this.$it;
                        ImageProxy imageProxy = this.$imageProxy;
                        this.label = 1;
                        prepareResultWithLuminosity = faceFrameProcessorImpl.prepareResultWithLuminosity(list, imageProxy, this);
                        if (prepareResultWithLuminosity == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException(tfwhgw.rnigpa(367));
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Face> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<? extends Face> list) {
                CoroutineScope coroutineScope;
                Log.d(tfwhgw.rnigpa(241), tfwhgw.rnigpa(PsExtractor.VIDEO_STREAM_MASK) + list.size());
                coroutineScope = FaceFrameProcessorImpl.this.coroutineScope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(FaceFrameProcessorImpl.this, list, imageProxy, null), 3, null);
            }
        });
        return Unit.INSTANCE;
    }
}
