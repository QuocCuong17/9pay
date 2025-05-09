package vn.ai.faceauth.sdk.camera.core.analyzer;

import androidx.camera.core.ImageAnalysis;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessor;
import vn.ai.faceauth.sdk.camera.di.CameraModule;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/analyzer/AnalyzeProvider;", "", "()V", "Builder", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class AnalyzeProvider {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/analyzer/AnalyzeProvider$Builder;", "", "()V", "cameraExecutors", "Ljava/util/concurrent/ExecutorService;", "getCameraExecutors", "()Ljava/util/concurrent/ExecutorService;", "setCameraExecutors", "(Ljava/util/concurrent/ExecutorService;)V", "faceFrameProcessor", "Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;", "getFaceFrameProcessor", "()Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;", "setFaceFrameProcessor", "(Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;)V", "build", "Landroidx/camera/core/ImageAnalysis;", "getAnalyzerType", "Lvn/ai/faceauth/sdk/camera/core/analyzer/CameraXAnalyzer;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Builder {
        private ExecutorService cameraExecutors;
        private FaceFrameProcessor faceFrameProcessor;

        public Builder() {
            CameraModule cameraModule = CameraModule.INSTANCE;
            this.faceFrameProcessor = cameraModule.getContainer$trueface_release().provideFaceFrameProcessor();
            this.cameraExecutors = cameraModule.getContainer$trueface_release().provideExecutorService();
        }

        private final CameraXAnalyzer getAnalyzerType() {
            CameraXAnalyzer cameraXAnalyzer = new CameraXAnalyzer(LifecycleOwnerKt.getLifecycleScope(CameraModule.INSTANCE.getLifecycleOwner$trueface_release()));
            cameraXAnalyzer.attachProcessor(this.faceFrameProcessor);
            return cameraXAnalyzer;
        }

        public final ImageAnalysis build() {
            ImageAnalysis build = new ImageAnalysis.Builder().setBackpressureStrategy(0).build();
            build.setAnalyzer(this.cameraExecutors, getAnalyzerType());
            return build;
        }

        public final ExecutorService getCameraExecutors() {
            return this.cameraExecutors;
        }

        public final FaceFrameProcessor getFaceFrameProcessor() {
            return this.faceFrameProcessor;
        }

        public final void setCameraExecutors(ExecutorService executorService) {
            this.cameraExecutors = executorService;
        }

        public final void setFaceFrameProcessor(FaceFrameProcessor faceFrameProcessor) {
            this.faceFrameProcessor = faceFrameProcessor;
        }
    }
}
