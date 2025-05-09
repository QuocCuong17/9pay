package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import co.hyperverge.facedetection.FaceDetectorApi;
import co.hyperverge.hypersnapsdk.model.HVFaceObj;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FileHelper {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.FileHelper";

    public static Dimensions compressToFile(Context context, String str, String str2, int i, int i2, int i3) throws IOException {
        Dimensions bitmapDimension = getBitmapDimension(str);
        Dimensions scaledDim = getScaledDim(bitmapDimension, i);
        try {
            Bitmap decodeSampledBitmapFromFile = decodeSampledBitmapFromFile(str, bitmapDimension, scaledDim);
            if (decodeSampledBitmapFromFile == null) {
                return null;
            }
            try {
                if (scaledDim.width < decodeSampledBitmapFromFile.getWidth()) {
                    decodeSampledBitmapFromFile = Bitmap.createScaledBitmap(decodeSampledBitmapFromFile, scaledDim.width, scaledDim.height, true);
                }
                Bitmap fixRotation = fixRotation(decodeSampledBitmapFromFile, i3);
                if (fixRotation == null) {
                    return null;
                }
                scaledDim.width = fixRotation.getWidth();
                scaledDim.height = fixRotation.getHeight();
                FileOutputStream openFileOutput = context.openFileOutput(str2, 0);
                fixRotation.compress(Bitmap.CompressFormat.JPEG, i2, openFileOutput);
                fixRotation.recycle();
                openFileOutput.close();
                openFileOutput.flush();
                return scaledDim;
            } catch (Throwable th) {
                Log.e(TAG, Utils.getErrorMessage(th));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(th);
                }
                return null;
            }
        } catch (Throwable th2) {
            Log.e(TAG, Utils.getErrorMessage(th2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(th2);
            }
            return null;
        }
    }

    public static HVFaceObj getFaceFromBitmapNPD(Bitmap bitmap) {
        HVLogUtils.d(TAG, "getFaceFromBitmapNPD() called with: source = [" + bitmap + "]");
        Dimensions dimAfterScaling = getDimAfterScaling(new Dimensions(bitmap.getWidth(), bitmap.getHeight()), 512);
        try {
            Bitmap copy = Bitmap.createScaledBitmap(bitmap, dimAfterScaling.width, dimAfterScaling.height, false).copy(Bitmap.Config.ARGB_8888, false);
            ArrayList<ArrayList<Float>> detectFacesFromBitmap = FaceDetectorApi.detectFacesFromBitmap(copy);
            HVFaceObj hVFaceObj = new HVFaceObj();
            if (detectFacesFromBitmap.size() > 0) {
                ArrayList<Float> faceCoordinates = Utils.getFaceCoordinates(detectFacesFromBitmap);
                hVFaceObj.setFaceLocation(faceCoordinates.get(0).floatValue(), faceCoordinates.get(1).floatValue(), faceCoordinates.get(2).floatValue(), faceCoordinates.get(3).floatValue());
                copy.recycle();
            }
            return hVFaceObj;
        } catch (Exception | NoClassDefFoundError e) {
            String str = TAG;
            HVLogUtils.e(str, "getFaceFromBitmapNPD(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static Bitmap getCroppedFaceFromBitmap(Bitmap bitmap, List<Integer> list, HVFaceConfig hVFaceConfig) {
        HVLogUtils.d(TAG, "getCroppedFaceFromBitmap() called with: bitmap = [" + bitmap + "], coordinates = [" + list + "], faceConfig = [" + hVFaceConfig + "]");
        if (bitmap != null && !list.isEmpty()) {
            int i = 0;
            try {
                int intValue = list.get(0).intValue();
                int intValue2 = list.get(1).intValue();
                int intValue3 = list.get(2).intValue();
                int intValue4 = list.get(3).intValue();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int i2 = intValue3 - intValue;
                int i3 = intValue4 - intValue2;
                float topPadding = hVFaceConfig.getTopPadding() > 0.0f ? i3 * hVFaceConfig.getTopPadding() : 0.0f;
                float bottomPadding = hVFaceConfig.getBottomPadding() > 0.0f ? i3 * hVFaceConfig.getBottomPadding() : 0.0f;
                int leftPadding = (int) (intValue - (hVFaceConfig.getLeftPadding() > 0.0f ? i2 * hVFaceConfig.getLeftPadding() : 0.0f));
                int rightPadding = (int) (intValue3 + (hVFaceConfig.getRightPadding() > 0.0f ? i2 * hVFaceConfig.getRightPadding() : 0.0f));
                int i4 = (int) (intValue2 - topPadding);
                int i5 = (int) (intValue4 + bottomPadding);
                if (leftPadding < 0) {
                    leftPadding = 0;
                }
                if (i4 < 0) {
                    i4 = 0;
                }
                if (rightPadding > width) {
                    rightPadding = width;
                }
                if (i5 > height) {
                    i5 = height;
                }
                int i6 = rightPadding - leftPadding;
                int i7 = i5 - i4;
                if (leftPadding + i6 > width) {
                    leftPadding = 0;
                } else {
                    width = i6;
                }
                if (i4 < 0) {
                    i4 = 0;
                }
                if (i4 + i7 <= height) {
                    height = i7;
                    i = i4;
                }
                return Bitmap.createBitmap(bitmap, leftPadding, i, width, height);
            } catch (Exception | OutOfMemoryError e) {
                String str = TAG;
                HVLogUtils.e(str, "getCroppedFaceFromBitmap(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(str, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        return null;
    }

    public static List<Integer> getCoordinatesFromFaceObj(HVFaceObj hVFaceObj, Bitmap bitmap) {
        HVLogUtils.d(TAG, "getCoordinatesFromFaceObj() called with: face = [" + hVFaceObj + "], bitmap = [" + bitmap + "]");
        ArrayList arrayList = new ArrayList();
        if (hVFaceObj != null && hVFaceObj.getFaceLocation() != null) {
            try {
                JSONObject jSONObject = new JSONObject(hVFaceObj.getFaceLocation());
                int actualLeftTopX = hVFaceObj.getActualLeftTopX(jSONObject.getInt("ltx"), bitmap.getWidth());
                int actualLeftTopY = hVFaceObj.getActualLeftTopY(jSONObject.getInt("lty"), bitmap.getHeight());
                int actualLeftTopX2 = hVFaceObj.getActualLeftTopX(jSONObject.getInt("rbx"), bitmap.getWidth());
                int actualLeftTopY2 = hVFaceObj.getActualLeftTopY(jSONObject.getInt("rby"), bitmap.getHeight());
                arrayList.add(Integer.valueOf(actualLeftTopX));
                arrayList.add(Integer.valueOf(actualLeftTopY));
                arrayList.add(Integer.valueOf(actualLeftTopX2));
                arrayList.add(Integer.valueOf(actualLeftTopY2));
            } catch (JSONException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        return arrayList;
    }

    public static String saveCroppedBitmapToPhone(Context context, String str, Bitmap bitmap, int i, int i2) {
        new TimingUtils();
        String absolutePath = new File(AppConstants.PIC_DIR).getAbsolutePath();
        try {
            File file = new File(absolutePath, str);
            if (file.exists()) {
                file.delete();
            }
            Dimensions dimensions = new Dimensions(bitmap.getWidth(), bitmap.getHeight());
            Dimensions scaledDim = getScaledDim(dimensions, i);
            new BitmapFactory.Options().inSampleSize = calculateInSampleSize(dimensions, scaledDim);
            try {
                bitmap = Bitmap.createScaledBitmap(bitmap, scaledDim.width, scaledDim.height, true);
            } catch (Throwable th) {
                Log.e(TAG, Utils.getErrorMessage(th));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(th);
                }
            }
            scaledDim.width = bitmap.getWidth();
            scaledDim.height = bitmap.getHeight();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return absolutePath + RemoteSettings.FORWARD_SLASH_STRING + str;
    }

    public static Bitmap getCroppedBitmap(Bitmap bitmap, float f, float f2) {
        try {
            float max = Math.max((bitmap.getHeight() * 1.0f) / f, (bitmap.getHeight() * 1.0f) / f2);
            return max > 1.0f ? Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() / max), (int) (bitmap.getHeight() / max), true) : bitmap;
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return bitmap;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return bitmap;
        }
    }

    public static Bitmap processBitmap(String str) {
        HVLogUtils.d(TAG, "processBitmap() called with: filepath = [" + str + "]");
        try {
            new BitmapFactory.Options().inPreferredConfig = Bitmap.Config.ARGB_8888;
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "getFaceFromBitmapNPD(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        } catch (OutOfMemoryError e2) {
            String str3 = TAG;
            HVLogUtils.e(str3, "getFaceFromBitmapNPD(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            Log.e(str3, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            return null;
        }
    }

    public static Dimensions compressFile(Context context, String str, String str2, int i, int i2, int i3) throws IOException {
        Dimensions bitmapDimension = getBitmapDimension(str);
        Dimensions scaledDim = getScaledDim(bitmapDimension, i);
        try {
            Bitmap decodeSampledBitmapFromFile = decodeSampledBitmapFromFile(str, bitmapDimension, scaledDim);
            if (decodeSampledBitmapFromFile == null) {
                return null;
            }
            try {
                if (scaledDim.width < decodeSampledBitmapFromFile.getWidth()) {
                    decodeSampledBitmapFromFile = Bitmap.createScaledBitmap(decodeSampledBitmapFromFile, scaledDim.width, scaledDim.height, true);
                }
                ExifInterface exifInterface = new ExifInterface(str);
                Bitmap fixRotation = fixRotation(decodeSampledBitmapFromFile, exifInterface.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0));
                if (fixRotation == null) {
                    return null;
                }
                scaledDim.width = fixRotation.getWidth();
                scaledDim.height = fixRotation.getHeight();
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                fixRotation.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream);
                fixRotation.recycle();
                fileOutputStream.close();
                fileOutputStream.flush();
                ExifInterface exifInterface2 = new ExifInterface(str2);
                copyExifTag(exifInterface, exifInterface2, androidx.exifinterface.media.ExifInterface.TAG_DATETIME);
                copyExifTag(exifInterface, exifInterface2, androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE);
                copyExifTag(exifInterface, exifInterface2, androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE_REF);
                copyExifTag(exifInterface, exifInterface2, androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE);
                copyExifTag(exifInterface, exifInterface2, androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE_REF);
                exifInterface2.saveAttributes();
                return scaledDim;
            } catch (Throwable th) {
                Log.e(TAG, Utils.getErrorMessage(th));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(th);
                }
                return null;
            }
        } catch (Throwable th2) {
            Log.e(TAG, Utils.getErrorMessage(th2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(th2);
            }
            return null;
        }
    }

    public static void copyExifTag(ExifInterface exifInterface, ExifInterface exifInterface2, String str) {
        HVLogUtils.d(TAG, "copyExifTag() called with: from = [" + exifInterface + "], to = [" + exifInterface2 + "], tag = [" + str + "]");
        if (exifInterface.getAttribute(str) != null) {
            exifInterface2.setAttribute(str, exifInterface.getAttribute(str));
        }
    }

    public static Bitmap fixRotation(Bitmap bitmap, int i) {
        HVLogUtils.d(TAG, "fixRotation() called with: bitmap = [" + bitmap + "], orientation = [" + i + "]");
        if (i == 1) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        switch (i) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static boolean isRotation90(int i) {
        HVLogUtils.d(TAG, "isRotation90() called with: orientation = [" + i + "]");
        if (i == 1) {
            return false;
        }
        return i == 5 || i == 6 || i == 7 || i == 8;
    }

    public static Dimensions getBitmapDimension(String str) {
        HVLogUtils.d(TAG, "getBitmapDimension() called with: filePath = [" + str + "]");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Dimensions(options.outWidth, options.outHeight);
    }

    public static Dimensions getScaledDim(Dimensions dimensions, int i) {
        HVLogUtils.d(TAG, "getScaledDim() called with: originalDim = [" + dimensions + "], maxDim = [" + i + "]");
        int i2 = dimensions.width;
        int i3 = dimensions.height;
        if (i2 > i) {
            i3 = (i3 * i) / i2;
        } else {
            i = i2;
        }
        return new Dimensions(i, i3);
    }

    public static Dimensions getDimAfterScaling(Dimensions dimensions, int i) {
        HVLogUtils.d(TAG, "getDimAfterScaling() called with: originalDim = [" + dimensions + "], maxDim = [" + i + "]");
        int i2 = dimensions.width;
        int i3 = dimensions.height;
        if (i2 > i3) {
            if (i2 > i) {
                i3 = (i3 * i) / i2;
                int calculateInSampleSize = calculateInSampleSize(dimensions, new Dimensions(i, i3));
                return new Dimensions(dimensions.getWidth() / calculateInSampleSize, dimensions.getHeight() / calculateInSampleSize);
            }
        } else if (i3 > i) {
            i2 = (i2 * i) / i3;
            i3 = i;
        }
        i = i2;
        int calculateInSampleSize2 = calculateInSampleSize(dimensions, new Dimensions(i, i3));
        return new Dimensions(dimensions.getWidth() / calculateInSampleSize2, dimensions.getHeight() / calculateInSampleSize2);
    }

    public static int calculateInSampleSize(Dimensions dimensions, Dimensions dimensions2) {
        HVLogUtils.d(TAG, "calculateInSampleSize() called with: original = [" + dimensions + "], scaled = [" + dimensions2 + "]");
        int i = dimensions.height;
        int i2 = dimensions.width;
        int i3 = 1;
        if (i > dimensions2.height || i2 > dimensions2.width) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 > dimensions2.height && i5 / i3 > dimensions2.width) {
                i3 *= 2;
            }
        }
        return i3;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        HVLogUtils.d(TAG, "rotateBitmap() called with: bitmap = [" + bitmap + "], orientation = [" + i + "]");
        Matrix matrix = new Matrix();
        switch (i) {
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            String str = TAG;
            HVLogUtils.e(str, "getFaceFromBitmapNPD(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String str, Dimensions dimensions, Dimensions dimensions2) {
        HVLogUtils.d(TAG, "decodeSampledBitmapFromFile() called with: filePath = [" + str + "], original = [" + dimensions + "], scaled = [" + dimensions2 + "]");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = calculateInSampleSize(dimensions, dimensions2);
        return BitmapFactory.decodeFile(str, options);
    }

    /* loaded from: classes2.dex */
    public static class Dimensions {
        public int height;
        public int width;

        public Dimensions(int i, int i2) {
            this.width = 0;
            this.height = 0;
            this.width = i;
            this.height = i2;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public String toString() {
            return this.width + " X " + this.height;
        }

        public JSONObject getJSON() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.width);
            jSONObject.put("height", this.height);
            return jSONObject;
        }

        public JSONObject getInvertedJSON() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.height);
            jSONObject.put("height", this.width);
            return jSONObject;
        }
    }

    public static boolean delete(File file) {
        File[] listFiles;
        HVLogUtils.d(TAG, "delete() called with: file = [" + file + "]");
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                delete(file2);
            }
        }
        return file.delete();
    }

    public static String getNameWithoutExtension(File file) {
        HVLogUtils.d(TAG, "getNameWithoutExtension() called with: file = [" + file + "]");
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        return lastIndexOf >= 0 ? name.substring(0, lastIndexOf) : name;
    }
}
