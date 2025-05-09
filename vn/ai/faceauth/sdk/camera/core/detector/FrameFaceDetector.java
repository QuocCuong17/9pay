package vn.ai.faceauth.sdk.camera.core.detector;

import android.graphics.Bitmap;
import androidx.camera.core.ImageProxy;
import com.google.mlkit.vision.face.Face;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\u00030\u0007H&J$\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0007H&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000f"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/detector/FrameFaceDetector;", "", "detect", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "processImage", "Lkotlin/Function1;", "", "Lcom/google/mlkit/vision/face/Face;", "detectBlurry", "bitmapSource", "Landroid/graphics/Bitmap;", "", "handleNoFace", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface FrameFaceDetector {
    void detect(ImageProxy imageProxy, Function1<? super List<? extends Face>, Unit> processImage);

    void detectBlurry(Bitmap bitmapSource, Function1<? super Float, Unit> processImage);

    void handleNoFace();
}
