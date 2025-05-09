package co.hyperverge.hypersnapsdk.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class FaceCoordinateObjsManager {
    private static List<FaceCoordinateObject> faceCoordinates = new ArrayList();
    private static int maxCount = 5;

    public FaceCoordinateObjsManager() {
        faceCoordinates = new ArrayList();
    }

    public static synchronized void addNewFaceCoordinateObject(ArrayList<Float> arrayList, long j, long j2) {
        synchronized (FaceCoordinateObjsManager.class) {
            addNewFaceCoordinateObject(new FaceCoordinateObject(arrayList, j, j2));
        }
    }

    public static synchronized void addNewFaceCoordinateObject(FaceCoordinateObject faceCoordinateObject) {
        synchronized (FaceCoordinateObjsManager.class) {
            Iterator<FaceCoordinateObject> it = faceCoordinates.iterator();
            int i = 0;
            while (it.hasNext()) {
                FaceCoordinateObject next = it.next();
                if (next.hasExpired()) {
                    it.remove();
                } else if (next.frameTimeStamp > faceCoordinateObject.getFrameTimeStamp()) {
                    break;
                } else {
                    i++;
                }
            }
            faceCoordinates.add(i, faceCoordinateObject);
            if (faceCoordinates.size() > maxCount) {
                faceCoordinates.remove(0);
            }
        }
    }

    public static synchronized FaceCoordinateObject getLatestFaceCoordinateObject() {
        synchronized (FaceCoordinateObjsManager.class) {
            if (faceCoordinates.size() == 0) {
                return null;
            }
            FaceCoordinateObject faceCoordinateObject = faceCoordinates.get(r1.size() - 1);
            if (faceCoordinateObject.hasExpired()) {
                return null;
            }
            return faceCoordinateObject;
        }
    }

    public static synchronized void clearFaceCoordinateObjects() {
        synchronized (FaceCoordinateObjsManager.class) {
            faceCoordinates.clear();
        }
    }

    /* loaded from: classes2.dex */
    public static class FaceCoordinateObject {
        private List<Float> coordinates;
        private long detectionTimeStamp;
        private long frameTimeStamp;

        public FaceCoordinateObject(ArrayList<Float> arrayList, long j, long j2) {
            this.coordinates = arrayList;
            this.frameTimeStamp = j;
            this.detectionTimeStamp = j2;
        }

        public FaceCoordinateObject(long j) {
            this.frameTimeStamp = j;
        }

        public List<Float> getCoordinates() {
            return this.coordinates;
        }

        public long getFrameTimeStamp() {
            return this.frameTimeStamp;
        }

        public long getDetectionTimeStamp() {
            return this.detectionTimeStamp;
        }

        public void setCoordinates(List<Float> list) {
            this.coordinates = list;
        }

        public void setDetectionTimeStamp(long j) {
            this.detectionTimeStamp = j;
        }

        public float getTopLeftX() {
            return this.coordinates.get(0).floatValue();
        }

        public float getTopLeftY() {
            return this.coordinates.get(1).floatValue();
        }

        public float getBottomRightX() {
            return this.coordinates.get(2).floatValue();
        }

        public float getBottomRightY() {
            return this.coordinates.get(3).floatValue();
        }

        public boolean hasExpired() {
            return this.detectionTimeStamp + 1500 < System.currentTimeMillis() || this.frameTimeStamp + 1700 < System.currentTimeMillis();
        }
    }
}
