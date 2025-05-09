package vn.ai.faceauth.sdk.camera.core.detector;

import android.graphics.Bitmap;
import android.media.Image;
import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\b0\fH\u0017J$\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\fH\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J4\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\b0\f2\u0018\u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\b0\fH\u0002JX\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u001a\b\u0002\u0010\u0018\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\b0\f2\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001a2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/detector/VisionFaceDetector;", "Lvn/ai/faceauth/sdk/camera/core/detector/FrameFaceDetector;", "()V", "detector", "Lcom/google/mlkit/vision/face/FaceDetector;", "realTimeOpts", "Lcom/google/mlkit/vision/face/FaceDetectorOptions;", "detect", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "processImage", "Lkotlin/Function1;", "", "Lcom/google/mlkit/vision/face/Face;", "detectBlurry", "bitmap", "Landroid/graphics/Bitmap;", "", "handleNoFace", "handleSuccessImage", "Lcom/google/android/gms/tasks/Task;", "image", "Lcom/google/mlkit/vision/common/InputImage;", "onSuccessListener", "onCompleteListener", "Lkotlin/Function0;", "onNoFaceListener", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class VisionFaceDetector implements FrameFaceDetector {
    private final FaceDetector detector;
    private final FaceDetectorOptions realTimeOpts;

    public VisionFaceDetector() {
        FaceDetectorOptions build = new FaceDetectorOptions.Builder().setLandmarkMode(2).setClassificationMode(2).setPerformanceMode(1).build();
        this.realTimeOpts = build;
        this.detector = FaceDetection.getClient(build);
    }

    private final Function1<List<? extends Face>, Unit> handleSuccessImage(final Function1<? super List<? extends Face>, Unit> processImage) {
        return new Function1<List<? extends Face>, Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$handleSuccessImage$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                processImage.invoke(list);
            }
        };
    }

    private final Task<List<Face>> processImage(InputImage image, final Function1<? super List<? extends Face>, Unit> onSuccessListener, final Function0<Unit> onCompleteListener, Function0<Unit> onNoFaceListener) {
        return this.detector.process(image).addOnSuccessListener(new OnSuccessListener() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                Function1.this.invoke((List) obj);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Function0.this.invoke();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Task processImage$default(VisionFaceDetector visionFaceDetector, InputImage inputImage, Function1 function1, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<List<? extends Face>, Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$processImage$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends Face> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<? extends Face> list) {
                }
            };
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$processImage$2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        if ((i & 8) != 0) {
            function02 = new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$processImage$3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        return visionFaceDetector.processImage(inputImage, function1, function0, function02);
    }

    @Override // vn.ai.faceauth.sdk.camera.core.detector.FrameFaceDetector
    public void detect(final ImageProxy imageProxy, Function1<? super List<? extends Face>, Unit> processImage) {
        Image image = imageProxy.getImage();
        if (image != null) {
            processImage(InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees()), handleSuccessImage(processImage), new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$detect$1$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            }, new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector$detect$1$2
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
                    ImageProxy.this.close();
                }
            });
        }
    }

    @Override // vn.ai.faceauth.sdk.camera.core.detector.FrameFaceDetector
    public void detectBlurry(Bitmap bitmap, Function1<? super Float, Unit> processImage) {
    }

    @Override // vn.ai.faceauth.sdk.camera.core.detector.FrameFaceDetector
    public void handleNoFace() {
    }
}
