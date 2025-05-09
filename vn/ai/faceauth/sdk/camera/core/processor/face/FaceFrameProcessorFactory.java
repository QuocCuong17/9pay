package vn.ai.faceauth.sdk.camera.core.processor.face;

import kotlin.Metadata;
import vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.core.factory.Factory;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessorFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;", "()V", "detector", "Lvn/ai/faceauth/sdk/camera/core/detector/VisionFaceDetector;", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FaceFrameProcessorFactory implements Factory<FaceFrameProcessor> {
    public static final FaceFrameProcessorFactory INSTANCE = new FaceFrameProcessorFactory();
    private static final VisionFaceDetector detector = CameraModule.INSTANCE.getContainer$trueface_release().provideVisionFaceDetector();

    private FaceFrameProcessorFactory() {
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public FaceFrameProcessor create() {
        return new FaceFrameProcessorImpl(CameraModule.INSTANCE.getContainer$trueface_release().provideCoroutineScope(), detector);
    }
}
