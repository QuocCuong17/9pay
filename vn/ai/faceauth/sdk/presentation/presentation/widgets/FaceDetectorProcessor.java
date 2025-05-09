package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.presentation.opencv.Point;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0014\u0010\u0014\u001a\u00020\u000e2\n\u0010\u0015\u001a\u00060\u0016j\u0002`\u0017H\u0014J\u001e\u0010\u0018\u001a\u00020\u000e2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/widgets/FaceDetectorProcessor;", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/VisionProcessorBase;", "", "Lcom/google/mlkit/vision/face/Face;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "detector", "Lcom/google/mlkit/vision/face/FaceDetector;", "faceImageProcessor", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/FaceImageProcessor;", "landMarkTypesAlign", "", "addProcessSuccees", "", "_faceImageProcessor", "detectInImage", "Lcom/google/android/gms/tasks/Task;", "image", "Lcom/google/mlkit/vision/common/InputImage;", "onFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "faces", "graphicOverlay", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/GraphicOverlay;", "stop", "Companion", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FaceDetectorProcessor extends VisionProcessorBase<List<? extends Face>> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static String TAG;
    private final FaceDetector detector;
    private FaceImageProcessor faceImageProcessor;
    private final int[] landMarkTypesAlign;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/widgets/FaceDetectorProcessor$Companion;", "", "()V", "TAG", "", "logExtrasForTesting", "", "face", "Lcom/google/mlkit/vision/face/Face;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void logExtrasForTesting(Face face) {
        }
    }

    static {
        tfwhgw.rnl(FaceDetectorProcessor.class, BaselineTIFFTagSet.TAG_JPEG_RESTART_INTERVAL, BaselineTIFFTagSet.TAG_JPEG_RESTART_INTERVAL);
        INSTANCE = new Companion(null);
    }

    public FaceDetectorProcessor(Context context) {
        super(context);
        FaceDetectorOptions build = new FaceDetectorOptions.Builder().setClassificationMode(2).setLandmarkMode(2).setPerformanceMode(1).build();
        this.detector = FaceDetection.getClient(build);
        Log.v(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_MIN_SAMPLE_VALUE), tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS) + build);
        this.landMarkTypesAlign = new int[]{4, 10, 6, 5, 11};
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor
    public void addProcessSuccees(FaceImageProcessor _faceImageProcessor) {
        this.faceImageProcessor = _faceImageProcessor;
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase
    public Task<List<? extends Face>> detectInImage(InputImage image) {
        return this.detector.process(image);
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase
    public void onFailure(Exception e) {
        Log.e(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_X_RESOLUTION), tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_MAX_SAMPLE_VALUE) + e);
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase
    public void onSuccess(List<? extends Face> faces, GraphicOverlay graphicOverlay) {
        for (Face face : faces) {
            graphicOverlay.add(new FaceGraphic(graphicOverlay, face));
            INSTANCE.logExtrasForTesting(face);
        }
        boolean isEmpty = faces.isEmpty();
        String rnigpa = tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
        FaceImageProcessor faceImageProcessor = null;
        if (!(!isEmpty)) {
            ArrayList<Point> arrayList = new ArrayList<>();
            FaceImageProcessor faceImageProcessor2 = this.faceImageProcessor;
            if (faceImageProcessor2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
                faceImageProcessor2 = null;
            }
            faceImageProcessor2.processBitmap(null, arrayList);
            return;
        }
        Face face2 = (Face) CollectionsKt.first((List) faces);
        for (Face face3 : faces) {
            if (face3.getBoundingBox().height() * face3.getBoundingBox().width() > face2.getBoundingBox().height() * face2.getBoundingBox().width()) {
                face2 = face3;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.clear();
        int length = this.landMarkTypesAlign.length;
        for (int i = 0; i < length; i++) {
            FaceLandmark landmark = face2.getLandmark(this.landMarkTypesAlign[i]);
            if (landmark != null) {
                PointF position = landmark.getPosition();
                arrayList2.add(new Pair(Float.valueOf(position.x), Float.valueOf(position.y)));
            }
        }
        ArrayList<Point> arrayList3 = new ArrayList<>();
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            Pair pair = (Pair) arrayList2.get(i2);
            arrayList3.add(new Point(((Number) pair.getFirst()).floatValue(), ((Number) pair.getSecond()).floatValue()));
        }
        if (arrayList3.size() > 0) {
            FaceImageProcessor faceImageProcessor3 = this.faceImageProcessor;
            if (faceImageProcessor3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
            } else {
                faceImageProcessor = faceImageProcessor3;
            }
            faceImageProcessor.processBitmap(face2, arrayList3);
        }
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase, vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor
    public void stop() {
        super.stop();
        this.detector.close();
    }
}
