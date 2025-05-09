package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.graphics.Bitmap;
import androidx.camera.core.ImageProxy;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public interface VisionImageProcessor {
    void addProcessSuccees(FaceImageProcessor faceImageProcessor);

    void processBitmap(Bitmap bitmap, GraphicOverlay graphicOverlay);

    void processByteBuffer(ByteBuffer byteBuffer, FrameMetadata frameMetadata, GraphicOverlay graphicOverlay);

    void processImageProxy(ImageProxy imageProxy, GraphicOverlay graphicOverlay);

    void stop();
}
