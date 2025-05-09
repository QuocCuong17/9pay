package co.hyperverge.facedetection.Detectors;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import co.hyperverge.facedetection.DetectorUtils;
import co.hyperverge.facedetection.FileInterface;
import co.hyperverge.facedetection.HVFace;
import java.util.List;

/* loaded from: classes2.dex */
public class HVFaceDetector {
    protected List<HVFace> detectFacesFromBitmap(FileInterface fileInterface, Bitmap bitmap) {
        return null;
    }

    public boolean initialize(Context context) {
        return true;
    }

    public void release() {
    }

    public List<HVFace> detectFaces(FileInterface fileInterface) {
        Log.i("ABCDE", "Detecting faces");
        Bitmap processBitmapFromPath = DetectorUtils.processBitmapFromPath(fileInterface.getPathOriginal());
        if (processBitmapFromPath == null) {
            Log.i("ABCDE", "Bitmap is null");
            return null;
        }
        List<HVFace> detectFacesFromBitmap = detectFacesFromBitmap(fileInterface, processBitmapFromPath);
        processBitmapFromPath.recycle();
        return detectFacesFromBitmap;
    }
}
