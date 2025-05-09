package co.hyperverge.hypersnapsdk.helpers;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import co.hyperverge.facedetection.FaceDetectorApi;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hypersnapsdk.helpers.FaceCoordinateObjsManager;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.model.FaceDetectorObj;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class CamPreviewFaceDetectionHandler extends HandlerThread {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.CamPreviewFaceDetectionHandler";
    private static int count;
    static CamPreviewFaceDetectionHandler detectionHandler;
    private final Handler backHandler;
    boolean isFrontfacingCamera;
    NPDFaceListener npdFaceListener;
    private final float[] tapCoordinates;
    private final Handler uiHandler;
    private int viewHeight;
    private int viewWidth;

    /* loaded from: classes2.dex */
    public interface NPDFaceListener {
        void onFaceDetected(FaceDetectorObj faceDetectorObj);
    }

    public CamPreviewFaceDetectionHandler() {
        super("FaceHandler");
        this.tapCoordinates = r0;
        start();
        this.backHandler = new Handler(getLooper());
        this.uiHandler = new Handler(Looper.getMainLooper());
        float[] fArr = {-1.0f, -1.0f};
    }

    public static CamPreviewFaceDetectionHandler get() {
        if (detectionHandler == null) {
            detectionHandler = new CamPreviewFaceDetectionHandler();
        }
        return detectionHandler;
    }

    public void setListener(NPDFaceListener nPDFaceListener) {
        this.npdFaceListener = nPDFaceListener;
    }

    /* loaded from: classes2.dex */
    public class VertexRunnable implements Runnable {
        FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject;
        boolean isCapturedFramePreviewed;
        boolean isFrontFacingCam;
        boolean isMultipleFaces;
        ArrayList<ArrayList<Float>> matrices;
        private ArrayList<Float> matrix;
        int orientation;

        public VertexRunnable(ArrayList<Float> arrayList, int i, FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject, boolean z, boolean z2, ArrayList<ArrayList<Float>> arrayList2) {
            this.matrix = new ArrayList<>();
            this.matrices = new ArrayList<>();
            this.matrix = arrayList;
            this.orientation = i;
            this.faceCoordinateObject = faceCoordinateObject;
            this.isFrontFacingCam = z;
            this.isCapturedFramePreviewed = z2;
            this.matrices = arrayList2;
        }

        public ArrayList<Integer> getDiagonalEdgesOfBox(int i, ArrayList<Float> arrayList) {
            int floatValue;
            int floatValue2;
            int floatValue3;
            int floatValue4;
            double d;
            int i2;
            double d2;
            int i3;
            int i4;
            double d3;
            if (i == 0) {
                int floatValue5 = (int) (arrayList.get(0).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                int floatValue6 = (int) (arrayList.get(1).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight);
                int floatValue7 = (int) (arrayList.get(4).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                double d4 = (floatValue7 - floatValue5) * 0.25d;
                i2 = (int) (floatValue5 - d4);
                double d5 = (r2 - floatValue6) * 0.35d;
                i3 = (int) (floatValue6 - d5);
                i4 = (int) (floatValue7 + d4);
                d3 = ((int) (arrayList.get(5).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight)) + d5;
            } else {
                if (i == 90) {
                    floatValue = (int) (arrayList.get(7).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                    floatValue2 = (int) ((1.0f - arrayList.get(6).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                    floatValue3 = (int) (arrayList.get(3).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                    floatValue4 = (int) ((1.0f - arrayList.get(2).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                } else if (i == 180) {
                    int floatValue8 = (int) ((1.0f - arrayList.get(4).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                    int floatValue9 = (int) ((1.0f - arrayList.get(5).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                    floatValue3 = (int) ((1.0f - arrayList.get(0).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                    floatValue4 = (int) ((1.0f - arrayList.get(1).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                    d = (floatValue3 - floatValue8) * 0.25d;
                    i2 = (int) (floatValue8 - d);
                    d2 = (floatValue4 - floatValue9) * 0.35d;
                    i3 = (int) (floatValue9 - d2);
                    i4 = (int) (floatValue3 + d);
                    d3 = floatValue4 + d2;
                } else {
                    floatValue = (int) ((1.0f - arrayList.get(3).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                    floatValue2 = (int) (arrayList.get(2).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight);
                    floatValue3 = (int) ((1.0f - arrayList.get(7).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                    floatValue4 = (int) (arrayList.get(6).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight);
                }
                d = (floatValue3 - floatValue) * 0.35d;
                i2 = (int) (floatValue - d);
                d2 = (floatValue4 - floatValue2) * 0.25d;
                i3 = (int) (floatValue2 - d2);
                i4 = (int) (floatValue3 + d);
                d3 = floatValue4 + d2;
            }
            int i5 = (int) d3;
            if (i2 <= 0) {
                i2 = 0;
            }
            if (i3 <= 0) {
                i3 = 0;
            }
            if (i5 > CamPreviewFaceDetectionHandler.this.viewHeight - 3) {
                i5 = CamPreviewFaceDetectionHandler.this.viewHeight - 3;
            }
            if (i4 > CamPreviewFaceDetectionHandler.this.viewWidth - 3) {
                i4 = CamPreviewFaceDetectionHandler.this.viewWidth - 3;
            }
            return new ArrayList<>(Arrays.asList(Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
        }

        private ArrayList<Integer> getDiagonalEdgesOfBoxMirrored(int i, ArrayList<Float> arrayList) {
            int floatValue;
            int floatValue2;
            float f;
            double d;
            int i2;
            double d2;
            double d3;
            int i3;
            int i4;
            int i5;
            if (i == 0) {
                int floatValue3 = (int) ((1.0f - arrayList.get(6).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                int floatValue4 = (int) (arrayList.get(7).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight);
                int floatValue5 = (int) ((1.0f - arrayList.get(2).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                double d4 = (floatValue5 - floatValue3) * 0.25d;
                i2 = (int) (floatValue3 - d4);
                double d5 = (r2 - floatValue4) * 0.35d;
                i3 = (int) (floatValue4 - d5);
                i4 = (int) (floatValue5 + d4);
                i5 = (int) (((int) (arrayList.get(3).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight)) + d5);
            } else {
                if (i == 90) {
                    int floatValue6 = (int) (arrayList.get(1).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                    int floatValue7 = (int) (arrayList.get(0).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight);
                    floatValue = (int) (arrayList.get(5).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                    floatValue2 = (int) (arrayList.get(4).floatValue() * CamPreviewFaceDetectionHandler.this.viewHeight);
                    d = (floatValue - floatValue6) * 0.25d;
                    i2 = (int) (floatValue6 - d);
                    d3 = (floatValue2 - floatValue7) * 0.35d;
                    i3 = (int) (floatValue7 - d3);
                } else {
                    if (i == 180) {
                        int floatValue8 = (int) (arrayList.get(2).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                        int floatValue9 = (int) ((1.0f - arrayList.get(3).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                        floatValue = (int) (arrayList.get(6).floatValue() * CamPreviewFaceDetectionHandler.this.viewWidth);
                        floatValue2 = (int) ((1.0f - arrayList.get(7).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                        f = floatValue2 - floatValue9;
                        d = (floatValue - floatValue8) * 0.35d;
                        i2 = (int) (floatValue8 - d);
                        d2 = floatValue9;
                    } else {
                        int floatValue10 = (int) ((1.0f - arrayList.get(5).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                        int floatValue11 = (int) ((1.0f - arrayList.get(4).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                        floatValue = (int) ((1.0f - arrayList.get(1).floatValue()) * CamPreviewFaceDetectionHandler.this.viewWidth);
                        floatValue2 = (int) ((1.0f - arrayList.get(0).floatValue()) * CamPreviewFaceDetectionHandler.this.viewHeight);
                        f = floatValue2 - floatValue11;
                        d = (floatValue - floatValue10) * 0.35d;
                        i2 = (int) (floatValue10 - d);
                        d2 = floatValue11;
                    }
                    d3 = f * 0.25d;
                    i3 = (int) (d2 - d3);
                }
                i4 = (int) (floatValue + d);
                i5 = (int) (floatValue2 + d3);
            }
            if (i2 <= 0) {
                i2 = 0;
            }
            if (i3 <= 0) {
                i3 = 0;
            }
            if (i5 > CamPreviewFaceDetectionHandler.this.viewHeight - 3) {
                i5 = CamPreviewFaceDetectionHandler.this.viewHeight - 3;
            }
            if (i4 > CamPreviewFaceDetectionHandler.this.viewWidth - 3) {
                i4 = CamPreviewFaceDetectionHandler.this.viewWidth - 3;
            }
            return new ArrayList<>(Arrays.asList(Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.matrix == null) {
                if (CamPreviewFaceDetectionHandler.this.npdFaceListener != null) {
                    CamPreviewFaceDetectionHandler.this.npdFaceListener.onFaceDetected(new FaceDetectorObj(new ArrayList(), null, 0, 0, null));
                }
            } else if (this.matrices != null) {
                final ArrayList arrayList = new ArrayList();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.CamPreviewFaceDetectionHandler.VertexRunnable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator<ArrayList<Float>> it = VertexRunnable.this.matrices.iterator();
                        while (it.hasNext()) {
                            ArrayList<Float> next = it.next();
                            VertexRunnable vertexRunnable = VertexRunnable.this;
                            arrayList.add(vertexRunnable.getDiagonalEdgesOfBox(vertexRunnable.orientation, next));
                        }
                        if (CamPreviewFaceDetectionHandler.this.npdFaceListener != null) {
                            CamPreviewFaceDetectionHandler.this.npdFaceListener.onFaceDetected(new FaceDetectorObj(new ArrayList(), VertexRunnable.this.faceCoordinateObject, CamPreviewFaceDetectionHandler.this.viewWidth, CamPreviewFaceDetectionHandler.this.viewHeight, arrayList));
                        }
                    }
                });
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.CamPreviewFaceDetectionHandler.VertexRunnable.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ArrayList arrayList2 = new ArrayList();
                        float floatValue = ((Float) VertexRunnable.this.matrix.get(4)).floatValue() - ((Float) VertexRunnable.this.matrix.get(0)).floatValue();
                        float floatValue2 = ((Float) VertexRunnable.this.matrix.get(5)).floatValue() - ((Float) VertexRunnable.this.matrix.get(1)).floatValue();
                        float f = floatValue * 0.35f;
                        float floatValue3 = (((Float) VertexRunnable.this.matrix.get(0)).floatValue() - f) * 100.0f < 0.0f ? 0.0f : (((Float) VertexRunnable.this.matrix.get(0)).floatValue() - f) * 100.0f;
                        float f2 = floatValue2 * 0.45f;
                        float floatValue4 = (((Float) VertexRunnable.this.matrix.get(1)).floatValue() - f2) * 100.0f >= 0.0f ? (((Float) VertexRunnable.this.matrix.get(1)).floatValue() - f2) * 100.0f : 0.0f;
                        float floatValue5 = (((Float) VertexRunnable.this.matrix.get(4)).floatValue() + f) * 100.0f > 100.0f ? 100.0f : (((Float) VertexRunnable.this.matrix.get(4)).floatValue() + f) * 100.0f;
                        float floatValue6 = (((Float) VertexRunnable.this.matrix.get(5)).floatValue() + f2) * 100.0f <= 100.0f ? 100.0f * (((Float) VertexRunnable.this.matrix.get(5)).floatValue() + f2) : 100.0f;
                        arrayList2.add(Float.valueOf(floatValue3));
                        arrayList2.add(Float.valueOf(floatValue4));
                        arrayList2.add(Float.valueOf(floatValue5));
                        arrayList2.add(Float.valueOf(floatValue6));
                        VertexRunnable.this.faceCoordinateObject.setCoordinates(arrayList2);
                        FaceCoordinateObjsManager.addNewFaceCoordinateObject(VertexRunnable.this.faceCoordinateObject);
                        VertexRunnable vertexRunnable = VertexRunnable.this;
                        ArrayList<Integer> diagonalEdgesOfBox = vertexRunnable.getDiagonalEdgesOfBox(vertexRunnable.orientation, VertexRunnable.this.matrix);
                        if (CamPreviewFaceDetectionHandler.this.npdFaceListener != null) {
                            CamPreviewFaceDetectionHandler.this.npdFaceListener.onFaceDetected(new FaceDetectorObj(diagonalEdgesOfBox, VertexRunnable.this.faceCoordinateObject, CamPreviewFaceDetectionHandler.this.viewWidth, CamPreviewFaceDetectionHandler.this.viewHeight, null));
                        }
                    }
                });
            }
        }
    }

    /* loaded from: classes2.dex */
    public class DetectionRunnable implements Runnable {
        public byte[] data;
        public int height;
        private final boolean isCapturedFramePreviewed;
        private final boolean isFrontFacingCam;
        public int mRotation;
        public int orientation;
        public int width;
        public ArrayList<ArrayList<Float>> matrices = new ArrayList<>();
        public FaceCoordinateObjsManager.FaceCoordinateObject faceCoordinateObject = new FaceCoordinateObjsManager.FaceCoordinateObject(System.currentTimeMillis());

        public DetectionRunnable(byte[] bArr, int i, int i2, int i3, int i4, boolean z, boolean z2) {
            this.data = bArr;
            this.width = i;
            this.height = i2;
            this.orientation = i3;
            this.mRotation = i4;
            this.isFrontFacingCam = z;
            this.isCapturedFramePreviewed = z2;
        }

        public ArrayList<Float> getLargestMatrix(ArrayList<ArrayList<Float>> arrayList) {
            ArrayList<Float> arrayList2 = arrayList.get(0);
            float floatValue = (arrayList2.get(4).floatValue() - arrayList2.get(0).floatValue()) * (arrayList2.get(3).floatValue() - arrayList2.get(1).floatValue());
            Iterator<ArrayList<Float>> it = arrayList.iterator();
            while (it.hasNext()) {
                ArrayList<Float> next = it.next();
                if ((next.get(4).floatValue() - next.get(0).floatValue()) * (next.get(3).floatValue() - next.get(1).floatValue()) > floatValue) {
                    floatValue = (next.get(4).floatValue() - next.get(0).floatValue()) * (next.get(3).floatValue() - next.get(1).floatValue());
                    arrayList2 = next;
                }
            }
            return arrayList2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                FaceCoordinateObjsManager.FaceCoordinateObject latestFaceCoordinateObject = FaceCoordinateObjsManager.getLatestFaceCoordinateObject();
                int i = -1;
                if (CamPreviewFaceDetectionHandler.this.tapCoordinates[0] <= 0.0f) {
                    if (latestFaceCoordinateObject != null && CamPreviewFaceDetectionHandler.count % 10 == 0) {
                        byte[] bArr = this.data;
                        int i2 = this.width;
                        i = FaceDetectorApi.getAvgIntensity(bArr, i2, this.height, Math.round((i2 / 100.0f) * latestFaceCoordinateObject.getTopLeftX()), Math.round((this.width / 100.0f) * latestFaceCoordinateObject.getBottomRightX()), Math.round((this.height / 100.0f) * latestFaceCoordinateObject.getTopLeftY()), Math.round((this.height / 100.0f) * latestFaceCoordinateObject.getBottomRightY()), true);
                    }
                } else {
                    rotateCoordinates();
                    i = FaceDetectorApi.getAvgIntensity(this.data, this.width, this.height, Math.max(0, Math.round(CamPreviewFaceDetectionHandler.this.tapCoordinates[0] * this.width) - 20), Math.min(this.width, Math.round(CamPreviewFaceDetectionHandler.this.tapCoordinates[0] * this.width) + 20), Math.max(0, Math.round(CamPreviewFaceDetectionHandler.this.tapCoordinates[1] * this.height) - 20), Math.min(this.height, Math.round(CamPreviewFaceDetectionHandler.this.tapCoordinates[1] * this.height) + 20), false);
                    Log.i("avgtouch", i + "");
                    CamPreviewFaceDetectionHandler.this.setExposure(Math.log(89.0d) - Math.log((double) i));
                    int unused = CamPreviewFaceDetectionHandler.count = 0;
                    CamPreviewFaceDetectionHandler.this.tapCoordinates[0] = -1.0f;
                    CamPreviewFaceDetectionHandler.this.tapCoordinates[1] = -1.0f;
                }
                CamPreviewFaceDetectionHandler.count++;
                CamPreviewFaceDetectionHandler.count %= 10;
                this.matrices = FaceDetectorApi.detectFacesFromByteArray(this.data, this.width, this.height, this.mRotation % 180 == 0 ? 0 : 1);
                this.faceCoordinateObject.setDetectionTimeStamp(System.currentTimeMillis());
                CamPreviewFaceDetectionHandler.this.uiHandler.removeCallbacksAndMessages(null);
                ArrayList<ArrayList<Float>> arrayList = this.matrices;
                if (arrayList != null && arrayList.size() != 0) {
                    ArrayList<Float> largestMatrix = getLargestMatrix(this.matrices);
                    if (i > 0 && latestFaceCoordinateObject != null && (StrictMath.abs(((largestMatrix.get(0).floatValue() * 100.0f) + (largestMatrix.get(4).floatValue() * 100.0f)) - (latestFaceCoordinateObject.getTopLeftX() + latestFaceCoordinateObject.getBottomRightX())) * this.width) / 200.0f < 15.0f && (StrictMath.abs(((largestMatrix.get(1).floatValue() * 100.0f) + (largestMatrix.get(3).floatValue() * 100.0f)) - (latestFaceCoordinateObject.getTopLeftY() + latestFaceCoordinateObject.getBottomRightY())) * this.height) / 200.0f < 15.0f) {
                        Log.i("avg", i + "");
                        CamPreviewFaceDetectionHandler.this.setExposure(Math.log(89.0d) - Math.log((double) i));
                        int unused2 = CamPreviewFaceDetectionHandler.count = 1;
                    }
                    ArrayList<ArrayList<Float>> arrayList2 = this.matrices;
                    if (arrayList2 == null || arrayList2.size() <= 1) {
                        CamPreviewFaceDetectionHandler.this.uiHandler.post(new VertexRunnable(largestMatrix, this.orientation, this.faceCoordinateObject, this.isFrontFacingCam, this.isCapturedFramePreviewed, null));
                        return;
                    } else {
                        CamPreviewFaceDetectionHandler.this.uiHandler.post(new VertexRunnable(largestMatrix, this.orientation, this.faceCoordinateObject, this.isFrontFacingCam, this.isCapturedFramePreviewed, this.matrices));
                        return;
                    }
                }
                CamPreviewFaceDetectionHandler.this.uiHandler.post(new VertexRunnable(null, this.orientation, this.faceCoordinateObject, this.isFrontFacingCam, this.isCapturedFramePreviewed, null));
            } catch (IllegalArgumentException e) {
                Log.e(CamPreviewFaceDetectionHandler.TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }

        public void rotateCoordinates() {
            float f;
            float f2;
            float f3;
            float f4;
            int i = this.mRotation;
            if (i != 0) {
                if (i == 90) {
                    f3 = CamPreviewFaceDetectionHandler.this.tapCoordinates[0];
                    f4 = CamPreviewFaceDetectionHandler.this.tapCoordinates[1];
                } else if (i == 180) {
                    f3 = 1.0f - CamPreviewFaceDetectionHandler.this.tapCoordinates[1];
                    f4 = CamPreviewFaceDetectionHandler.this.tapCoordinates[0];
                } else if (i != 270) {
                    f2 = 1.0f - CamPreviewFaceDetectionHandler.this.tapCoordinates[0];
                    f = CamPreviewFaceDetectionHandler.this.tapCoordinates[1];
                } else {
                    f2 = 1.0f - CamPreviewFaceDetectionHandler.this.tapCoordinates[0];
                    f = CamPreviewFaceDetectionHandler.this.tapCoordinates[1];
                }
                f2 = f3;
                f = 1.0f - f4;
            } else {
                float f5 = CamPreviewFaceDetectionHandler.this.tapCoordinates[0];
                f = 1.0f - CamPreviewFaceDetectionHandler.this.tapCoordinates[0];
                f2 = CamPreviewFaceDetectionHandler.this.tapCoordinates[1];
            }
            CamPreviewFaceDetectionHandler.this.tapCoordinates[0] = f2;
            CamPreviewFaceDetectionHandler.this.tapCoordinates[1] = f;
        }
    }

    public void setExposure(double d) {
        if (this.isFrontfacingCamera) {
            CameraEngine.setExposure(d);
        }
    }

    public void submit(CameraProperties cameraProperties) {
        this.viewWidth = cameraProperties.getViewWidth();
        this.viewHeight = cameraProperties.getViewHeight();
        this.isFrontfacingCamera = cameraProperties.isFrontFacingCam();
        this.backHandler.removeCallbacksAndMessages(null);
        this.backHandler.post(new DetectionRunnable(cameraProperties.getData(), cameraProperties.getWidth(), cameraProperties.getHeight(), cameraProperties.getOrientation(), cameraProperties.getRotation(), cameraProperties.isFrontFacingCam(), cameraProperties.isCapturedFramePreviewed()));
    }

    public void onTapToFocus(float f, float f2) {
        float[] fArr = this.tapCoordinates;
        fArr[0] = f;
        fArr[1] = f2;
    }

    public void reset() {
        this.npdFaceListener = null;
        detectionHandler = null;
    }
}
