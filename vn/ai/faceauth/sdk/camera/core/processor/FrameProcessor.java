package vn.ai.faceauth.sdk.camera.core.processor;

import androidx.camera.core.ImageProxy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/processor/FrameProcessor;", "", "onFrameCaptured", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "(Landroidx/camera/core/ImageProxy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface FrameProcessor {
    Object onFrameCaptured(ImageProxy imageProxy, Continuation<? super Unit> continuation);
}
