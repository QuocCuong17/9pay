package co.hyperverge.facedetection.Detectors;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Environment;
import android.util.Log;
import co.hyperverge.facedetection.DetectorUtils;
import co.hyperverge.facedetection.FileInterface;
import co.hyperverge.facedetection.HVFace;
import com.getkeepsafe.relinker.ReLinker;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class NDPDetector extends HVFaceDetector {
    private static final String TAG = "co.hyperverge.facedetection.Detectors.NDPDetector";
    private String JsonPath;
    private String LandmarkPath;
    AssetManager mgr;
    long model;

    public native synchronized String detectFaces(byte[] bArr, int i, int i2, int i3, String str);

    public native synchronized String detectFacesBitmap(Bitmap bitmap);

    @Override // co.hyperverge.facedetection.Detectors.HVFaceDetector
    protected List<HVFace> detectFacesFromBitmap(FileInterface fileInterface, Bitmap bitmap) {
        return null;
    }

    public native synchronized int getAverageIntensity(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z);

    public native synchronized void loadModel(AssetManager assetManager, String str, int i, int i2, int i3, int i4);

    public native synchronized void releaseModel(long j);

    @Override // co.hyperverge.facedetection.Detectors.HVFaceDetector
    public boolean initialize(Context context) {
        ReLinker.loadLibrary(context, "ndp-detector");
        AssetManager assets = context.getResources().getAssets();
        this.mgr = assets;
        loadModel(assets, this.LandmarkPath, 640, 480, 20, 11);
        try {
            new File("/sdcard/saved/").mkdirs();
            return true;
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return true;
        }
    }

    @Override // co.hyperverge.facedetection.Detectors.HVFaceDetector
    public List<HVFace> detectFaces(FileInterface fileInterface) {
        Bitmap processBitmapFromPath = DetectorUtils.processBitmapFromPath(fileInterface.getPathOriginal());
        if (processBitmapFromPath == null) {
            return null;
        }
        List<HVFace> detectFacesFromBitmap = detectFacesFromBitmap(fileInterface, processBitmapFromPath);
        processBitmapFromPath.recycle();
        return detectFacesFromBitmap;
    }

    public ArrayList<ArrayList<Float>> detectFacesFromData(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            return null;
        }
        try {
            return getMatricesFromResponse(new JSONArray(detectFaces(bArr, i, i2, i3, this.JsonPath)), i, i2);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
            return null;
        }
    }

    public ArrayList<ArrayList<Float>> getMatricesFromResponse(JSONArray jSONArray, int i, int i2) {
        ArrayList<ArrayList<Float>> arrayList = new ArrayList<>(jSONArray.length());
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i3);
                arrayList.add(new ArrayList<>(jSONArray2.length()));
                ArrayList<Float> arrayList2 = arrayList.get(i3);
                for (int i4 = 0; i4 < jSONArray2.length(); i4 += 2) {
                    arrayList2.add(i4, Float.valueOf(jSONArray2.getInt(i4) / i));
                    arrayList2.add(i4 + 1, Float.valueOf(jSONArray2.getInt(r6) / i2));
                }
            } catch (JSONException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<Float>> detectFacesFromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            return getMatricesFromResponse(new JSONArray(detectFacesBitmap(bitmap)), bitmap.getWidth(), bitmap.getHeight());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getAvgIntensity(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (z) {
            int round = Math.round((i3 / 2.0f) + (i4 / 2.0f));
            int round2 = Math.round((i5 / 2.0f) + (i6 / 2.0f));
            int round3 = Math.round((i4 - i3) / 4.0f);
            int round4 = Math.round((i6 - i5) / 4.0f);
            return getAverageIntensity(bArr, i, i2, round - round3, round + round3, round2 - round4, round2 + round4, z);
        }
        return getAverageIntensity(bArr, i, i2, i3, i4, i5, i6, z);
    }

    public List<RectF> detectFacesFromData2(byte[] bArr, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        if (bArr == null) {
            return null;
        }
        try {
            Log.d("NPD", new JSONArray(detectFaces(bArr, i, i2, i3, this.JsonPath)).toString());
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
        return arrayList;
    }

    private void SaveImage(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/FACE_CROP_256_test");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, "Image-" + System.currentTimeMillis() + ".jpg");
        if (file2.exists()) {
            file2.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override // co.hyperverge.facedetection.Detectors.HVFaceDetector
    public void release() {
        releaseModel(this.model);
    }
}
