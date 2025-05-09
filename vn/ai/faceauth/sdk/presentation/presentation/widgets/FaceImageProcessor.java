package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import com.google.mlkit.vision.face.Face;
import java.util.ArrayList;
import kotlin.Metadata;
import vn.ai.faceauth.sdk.presentation.presentation.opencv.Point;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH&¨\u0006\n"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/widgets/FaceImageProcessor;", "", "processBitmap", "", "face", "Lcom/google/mlkit/vision/face/Face;", "landmarkPoints", "Ljava/util/ArrayList;", "Lvn/ai/faceauth/sdk/presentation/presentation/opencv/Point;", "Lkotlin/collections/ArrayList;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface FaceImageProcessor {
    void processBitmap(Face face, ArrayList<Point> landmarkPoints);
}
