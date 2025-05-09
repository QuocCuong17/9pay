package vn.ai.faceauth.sdk.camera.domain.mapper;

import android.media.Image;
import com.google.mlkit.vision.face.Face;
import kotlin.Metadata;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000¨\u0006\u0005"}, d2 = {"toFaceResult", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "Lcom/google/mlkit/vision/face/Face;", "image", "Landroid/media/Image;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FaceMapperKt {
    public static final FaceResult toFaceResult(Face face, Image image) {
        return new FaceResult(face.getTrackingId(), face.getBoundingBox(), face.getHeadEulerAngleX(), face.getHeadEulerAngleY(), face.getHeadEulerAngleZ(), face.getSmilingProbability(), face.getRightEyeOpenProbability(), face.getLeftEyeOpenProbability(), null, image != null ? image.getCropRect() : null, face.getLandmark(4), face.getLandmark(10), 256, null);
    }
}
