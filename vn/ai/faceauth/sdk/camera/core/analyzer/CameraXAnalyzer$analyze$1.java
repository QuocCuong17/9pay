package vn.ai.faceauth.sdk.camera.core.analyzer;

import androidx.camera.core.ImageProxy;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.Iterator;
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
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.core.processor.FrameProcessor;

@Metadata(d1 = {"\u0000\u0006\n\u0002\u0010\u0002\n\u0000\u0010\u0001\u001a\u00020\u0000H\u008a@"}, d2 = {"", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "vn.ai.faceauth.sdk.camera.core.analyzer.CameraXAnalyzer$analyze$1", f = "CameraXAnalyzer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class CameraXAnalyzer$analyze$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ ImageProxy $image;
    int label;
    final /* synthetic */ CameraXAnalyzer this$0;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "vn.ai.faceauth.sdk.camera.core.analyzer.CameraXAnalyzer$analyze$1$1", f = "CameraXAnalyzer.kt", i = {}, l = {23}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: vn.ai.faceauth.sdk.camera.core.analyzer.CameraXAnalyzer$analyze$1$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ImageProxy $image;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ CameraXAnalyzer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CameraXAnalyzer cameraXAnalyzer, ImageProxy imageProxy, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = cameraXAnalyzer;
            this.$image = imageProxy;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$image, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List list;
            ImageProxy imageProxy;
            Iterator it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                list = this.this$0.processors;
                imageProxy = this.$image;
                it = list.iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException(tfwhgw.rnigpa(24));
                }
                it = (Iterator) this.L$1;
                imageProxy = (ImageProxy) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (it.hasNext()) {
                FrameProcessor frameProcessor = (FrameProcessor) it.next();
                this.L$0 = imageProxy;
                this.L$1 = it;
                this.label = 1;
                if (frameProcessor.onFrameCaptured(imageProxy, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraXAnalyzer$analyze$1(CameraXAnalyzer cameraXAnalyzer, ImageProxy imageProxy, Continuation<? super CameraXAnalyzer$analyze$1> continuation) {
        super(1, continuation);
        this.this$0 = cameraXAnalyzer;
        this.$image = imageProxy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new CameraXAnalyzer$analyze$1(this.this$0, this.$image, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((CameraXAnalyzer$analyze$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_HALFTONE_HINTS));
        }
        ResultKt.throwOnFailure(obj);
        coroutineScope = this.this$0.coroutineScope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.this$0, this.$image, null), 3, null);
        return Unit.INSTANCE;
    }
}
