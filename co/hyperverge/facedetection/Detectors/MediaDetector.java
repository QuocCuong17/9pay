package co.hyperverge.facedetection.Detectors;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.util.Log;
import co.hyperverge.facedetection.DetectorUtils;
import co.hyperverge.facedetection.FileInterface;
import co.hyperverge.facedetection.HVFace;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MediaDetector extends HVFaceDetector {
    private static final int MAX_FACES = 10;
    private static final String TAG = "co.hyperverge.facedetection.Detectors.MediaDetector";

    @Override // co.hyperverge.facedetection.Detectors.HVFaceDetector
    protected List<HVFace> detectFacesFromBitmap(FileInterface fileInterface, Bitmap bitmap) {
        ArrayList arrayList = new ArrayList();
        if (bitmap == null) {
            return arrayList;
        }
        if (1 == bitmap.getWidth() % 2) {
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() + 1, bitmap.getHeight(), false);
        }
        FaceDetector.Face[] faceArr = new FaceDetector.Face[10];
        int findFaces = new FaceDetector(bitmap.getWidth(), bitmap.getHeight(), 10).findFaces(bitmap, faceArr);
        if (findFaces > 0) {
            for (int i = 0; i < findFaces; i++) {
                FaceDetector.Face face = faceArr[i];
                PointF pointF = new PointF();
                face.getMidPoint(pointF);
                float eyesDistance = face.eyesDistance() * 2.0f;
                float f = pointF.x - eyesDistance;
                float f2 = pointF.x + eyesDistance;
                float f3 = pointF.y - eyesDistance;
                float f4 = f2 - f;
                float f5 = (pointF.y + eyesDistance) - f3;
                try {
                    HVFace hVFace = new HVFace(fileInterface.getLabel() + "_" + i, fileInterface.getPathOriginal());
                    hVFace.setFileLabel(fileInterface.getLabel());
                    hVFace.setFaceLocation(DetectorUtils.performFaceCalculations(f, f3, f4, f5, (float) bitmap.getWidth(), (float) bitmap.getHeight()));
                    arrayList.add(hVFace);
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        }
        return arrayList;
    }
}
