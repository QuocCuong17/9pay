package co.hyperverge.hypersnapsdk.model;

import co.hyperverge.hypersnapsdk.helpers.FaceCoordinateObjsManager;
import com.google.mlkit.vision.face.Face;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FaceDetectorObj {
    Face face;
    FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject;
    private boolean isStraight;
    List<ArrayList<Integer>> multipleFaces;
    private ArrayList<Integer> rectPoints;
    int viewHeight;
    int viewWidth;

    protected boolean canEqual(Object obj) {
        return obj instanceof FaceDetectorObj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FaceDetectorObj)) {
            return false;
        }
        FaceDetectorObj faceDetectorObj = (FaceDetectorObj) obj;
        if (!faceDetectorObj.canEqual(this) || getViewWidth() != faceDetectorObj.getViewWidth() || getViewHeight() != faceDetectorObj.getViewHeight() || isStraight() != faceDetectorObj.isStraight()) {
            return false;
        }
        ArrayList<Integer> rectPoints = getRectPoints();
        ArrayList<Integer> rectPoints2 = faceDetectorObj.getRectPoints();
        if (rectPoints != null ? !rectPoints.equals(rectPoints2) : rectPoints2 != null) {
            return false;
        }
        FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject = getFaceCoordinateObject();
        FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject2 = faceDetectorObj.getFaceCoordinateObject();
        if (faceCoordinateObject != null ? !faceCoordinateObject.equals(faceCoordinateObject2) : faceCoordinateObject2 != null) {
            return false;
        }
        List<ArrayList<Integer>> multipleFaces = getMultipleFaces();
        List<ArrayList<Integer>> multipleFaces2 = faceDetectorObj.getMultipleFaces();
        if (multipleFaces != null ? !multipleFaces.equals(multipleFaces2) : multipleFaces2 != null) {
            return false;
        }
        Face face = getFace();
        Face face2 = faceDetectorObj.getFace();
        return face != null ? face.equals(face2) : face2 == null;
    }

    public int hashCode() {
        int viewWidth = ((((getViewWidth() + 59) * 59) + getViewHeight()) * 59) + (isStraight() ? 79 : 97);
        ArrayList<Integer> rectPoints = getRectPoints();
        int hashCode = (viewWidth * 59) + (rectPoints == null ? 43 : rectPoints.hashCode());
        FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject = getFaceCoordinateObject();
        int hashCode2 = (hashCode * 59) + (faceCoordinateObject == null ? 43 : faceCoordinateObject.hashCode());
        List<ArrayList<Integer>> multipleFaces = getMultipleFaces();
        int hashCode3 = (hashCode2 * 59) + (multipleFaces == null ? 43 : multipleFaces.hashCode());
        Face face = getFace();
        return (hashCode3 * 59) + (face != null ? face.hashCode() : 43);
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public void setFaceCoordinateObject(FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject) {
        this.faceCoordinateObject = faceCoordinateObject;
    }

    public void setMultipleFaces(List<ArrayList<Integer>> list) {
        this.multipleFaces = list;
    }

    public void setRectPoints(ArrayList<Integer> arrayList) {
        this.rectPoints = arrayList;
    }

    public void setStraight(boolean z) {
        this.isStraight = z;
    }

    public void setViewHeight(int i) {
        this.viewHeight = i;
    }

    public void setViewWidth(int i) {
        this.viewWidth = i;
    }

    public String toString() {
        return "FaceDetectorObj(rectPoints=" + getRectPoints() + ", faceCoordinateObject=" + getFaceCoordinateObject() + ", viewWidth=" + getViewWidth() + ", viewHeight=" + getViewHeight() + ", multipleFaces=" + getMultipleFaces() + ", isStraight=" + isStraight() + ", face=" + getFace() + ")";
    }

    public ArrayList<Integer> getRectPoints() {
        return this.rectPoints;
    }

    public FaceCoordinateObjsManager.FaceCoordinateObject getFaceCoordinateObject() {
        return this.faceCoordinateObject;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    public List<ArrayList<Integer>> getMultipleFaces() {
        return this.multipleFaces;
    }

    public boolean isStraight() {
        return this.isStraight;
    }

    public Face getFace() {
        return this.face;
    }

    public FaceDetectorObj(ArrayList<Integer> arrayList, FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject, int i, int i2, List<ArrayList<Integer>> list) {
        this.rectPoints = new ArrayList<>();
        this.rectPoints = arrayList;
        this.faceCoordinateObject = faceCoordinateObject;
        this.viewHeight = i2;
        this.viewWidth = i;
        this.multipleFaces = list;
        this.isStraight = true;
    }

    public FaceDetectorObj(ArrayList<Integer> arrayList, FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject, int i, int i2, List<ArrayList<Integer>> list, boolean z) {
        this.rectPoints = new ArrayList<>();
        this.rectPoints = arrayList;
        this.faceCoordinateObject = faceCoordinateObject;
        this.viewHeight = i2;
        this.viewWidth = i;
        this.multipleFaces = list;
        this.isStraight = z;
    }
}
