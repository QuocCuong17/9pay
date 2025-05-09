package co.hyperverge.hypersnapsdk.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import co.hyperverge.hvcamera.magicfilter.utils.Exif;
import co.hyperverge.hypersnapsdk.BuildConfig;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.face.MLKitFaceHelper;
import co.hyperverge.hypersnapsdk.model.HVFaceObj;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.mlkit.vision.face.Face;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class SaveBitmapAsync implements Runnable {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.SaveBitmapAsync";
    private Bitmap bitmap;
    private final SaveCallBack callBack;
    private final byte[] data;
    private final HVFaceConfig faceConfig;
    HVFaceObj faceObj;
    private final String filePath;
    private final String filename;
    private final String mTransactionId;
    private final byte[] previewFrameData;
    private List<Integer> faceCoords = new ArrayList();
    boolean isFaceDetectorExcluded = false;

    /* loaded from: classes2.dex */
    public interface SaveCallBack {
        void onImageSaved(String str, List<Integer> list, boolean z);
    }

    public SaveBitmapAsync(byte[] bArr, byte[] bArr2, String str, String str2, HVFaceConfig hVFaceConfig, String str3, SaveCallBack saveCallBack) {
        this.data = bArr;
        this.filePath = str;
        this.filename = str2;
        this.callBack = saveCallBack;
        this.faceConfig = hVFaceConfig;
        this.previewFrameData = bArr2;
        this.mTransactionId = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        HVLogUtils.d(TAG, "run() called");
        int orientation = Exif.getOrientation(this.data);
        if (this.faceConfig.getShouldUseBackCamera()) {
            orientation = Utils.checkForOrientationCorrection(orientation);
        }
        Bitmap bitmapFromBufferData = getBitmapFromBufferData(orientation);
        this.bitmap = bitmapFromBufferData;
        if (bitmapFromBufferData == null) {
            this.callBack.onImageSaved(null, this.faceCoords, this.isFaceDetectorExcluded);
            return;
        }
        try {
            if (SDKInternalConfig.getInstance().isMLKitDetectorMissing()) {
                fallbackToNPD(this.bitmap);
                return;
            }
            Face processImage = MLKitFaceHelper.get().processImage(this.bitmap);
            if (processImage == null) {
                fallbackToNPD(this.bitmap);
                return;
            }
            this.faceCoords = Utils.getHVFaceObj(processImage, this.bitmap);
            if (!this.faceConfig.shouldCheckForFaceTilt() && !this.faceConfig.isShouldCheckActiveLiveness()) {
                proceedToSaveBitmap(this.bitmap);
                return;
            }
            checkForPose(processImage);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            SDKInternalConfig.getInstance().setMLKitUnavailable(true);
            AppConstants.mlkitUnavailableError = Utils.getErrorMessage(e);
            fallbackToNPD(this.bitmap);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void fallbackToNPD(Bitmap bitmap) {
        HVLogUtils.d(TAG, "fallbackToNPD() called with: bitmap = [" + bitmap + "]");
        HVFaceObj faceFromBitmapNPD = FileHelper.getFaceFromBitmapNPD(bitmap);
        this.isFaceDetectorExcluded = faceFromBitmapNPD == null && (SDKInternalConfig.getInstance().isMLKitDetectorMissing() || SDKInternalConfig.getInstance().isMLKitUnavailable());
        this.faceCoords = FileHelper.getCoordinatesFromFaceObj(faceFromBitmapNPD, bitmap);
        proceedToSaveBitmap(bitmap);
    }

    public void checkForPose(Face face) {
        boolean z;
        HVLogUtils.d(TAG, "checkForPose() called with: mlkitFace = [" + face + "]");
        if (this.faceConfig.isShouldCheckActiveLiveness()) {
            z = HVActiveLiveness.get().detectFaceFromImage(face);
        } else {
            float headEulerAngleY = face.getHeadEulerAngleY();
            float headEulerAngleX = face.getHeadEulerAngleX();
            float headEulerAngleZ = face.getHeadEulerAngleZ();
            float faceTiltAngle = this.faceConfig.getFaceTiltAngle();
            z = Math.abs(headEulerAngleY) <= faceTiltAngle && Math.abs(headEulerAngleX) <= faceTiltAngle && Math.abs(headEulerAngleZ) <= faceTiltAngle;
        }
        if (z) {
            proceedToSaveBitmap(this.bitmap);
        } else {
            this.callBack.onImageSaved(null, this.faceCoords, this.isFaceDetectorExcluded);
        }
    }

    public void proceedToSaveBitmap(Bitmap bitmap) {
        HVLogUtils.d(TAG, "proceedToSaveBitmap() called with: bitmap = [" + bitmap + "]");
        if (SDKInternalConfig.getInstance() != null && SDKInternalConfig.getInstance().isShouldDoImageInjectionChecks()) {
            ImageComparisonHelper.get().setImageSize(bitmap.getHeight(), bitmap.getWidth());
            byte[] bArr = this.previewFrameData;
            if (bArr != null) {
                performImageInjectionChecks(bArr, bitmap);
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(this.filePath, this.filename));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
            Bitmap croppedBitmap = getCroppedBitmap(bitmap);
            if (croppedBitmap != null) {
                File file = new File(this.filePath, "FD_crop_" + System.currentTimeMillis() + ".jpg");
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                FileHelper.Dimensions dimensions = new FileHelper.Dimensions(croppedBitmap.getWidth(), croppedBitmap.getHeight());
                FileHelper.Dimensions scaledDim = FileHelper.getScaledDim(dimensions, 300);
                new BitmapFactory.Options().inSampleSize = FileHelper.calculateInSampleSize(dimensions, scaledDim);
                try {
                    if (scaledDim.width < croppedBitmap.getWidth()) {
                        croppedBitmap = Bitmap.createScaledBitmap(croppedBitmap, scaledDim.width, scaledDim.height, true);
                    }
                } catch (Exception e) {
                    this.callBack.onImageSaved(null, this.faceCoords, this.isFaceDetectorExcluded);
                    Log.e(TAG, Utils.getErrorMessage(e));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                }
                croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream2);
                fileOutputStream2.close();
                this.callBack.onImageSaved(file.getAbsolutePath(), this.faceCoords, this.isFaceDetectorExcluded);
                return;
            }
            this.callBack.onImageSaved(null, this.faceCoords, this.isFaceDetectorExcluded);
        } catch (Exception e2) {
            String str = TAG;
            HVLogUtils.e(str, "proceedToSaveBitmap(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            this.callBack.onImageSaved(null, this.faceCoords, this.isFaceDetectorExcluded);
            Log.e(str, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
        }
    }

    public Bitmap getBitmapFromBufferData(int i) {
        HVLogUtils.d(TAG, "getBitmapFromBufferData() called with: exifOrientation = [" + i + "]");
        byte[] bArr = this.data;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        this.bitmap = decodeByteArray;
        Bitmap rotateBitmap = UIUtils.rotateBitmap(decodeByteArray, i);
        this.bitmap = rotateBitmap;
        return rotateBitmap;
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        HVLogUtils.d(TAG, "getCroppedBitmap() called with: bitmap = [" + bitmap + "]");
        try {
            return FileHelper.getCroppedFaceFromBitmap(bitmap, this.faceCoords, this.faceConfig);
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "getCroppedBitmap(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public void performImageInjectionChecks(byte[] bArr, Bitmap bitmap) {
        Bitmap createBitmap;
        HVLogUtils.d(TAG, "performImageInjectionChecks() called with: previousFrame = [" + bArr + "], fullImageBitmap = [" + bitmap + "]");
        try {
            setColorToRandomPixel(bitmap);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (!decodeByteArray.sameAs(bitmap) && (bitmap.getWidth() != decodeByteArray.getWidth() || bitmap.getHeight() != decodeByteArray.getHeight())) {
                createBitmap = Bitmap.createScaledBitmap(bitmap, decodeByteArray.getWidth(), decodeByteArray.getHeight(), false);
                ImageComparisonHelper.get().runComparisonMethods(decodeByteArray, createBitmap);
            }
            createBitmap = Bitmap.createBitmap(bitmap);
            ImageComparisonHelper.get().runComparisonMethods(decodeByteArray, createBitmap);
        } catch (Exception e) {
            String str = TAG;
            HVLogUtils.e(str, "performImageInjectionChecks(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private void setColorToRandomPixel(Bitmap bitmap) {
        int i;
        HVLogUtils.d(TAG, "setColorToRandomPixel() called with: bitmap = [" + bitmap + "]");
        try {
            if (StringUtils.isEmptyOrNull(this.mTransactionId)) {
                i = 0;
            } else {
                byte[] bytes = this.mTransactionId.getBytes(StandardCharsets.US_ASCII);
                Arrays.toString(bytes);
                i = 0;
                for (byte b : bytes) {
                    i += b;
                }
            }
            int i2 = 0;
            for (String str : BuildConfig.HYPERSNAP_VERSION_NAME.split("\\.")) {
                i2 += Integer.parseInt(str);
            }
            int i3 = i + i2;
            if (i3 >= bitmap.getHeight() || i3 >= bitmap.getWidth()) {
                i3 = SaveBitmapAsync$$ExternalSyntheticBackport0.m(i3, bitmap.getWidth());
            }
            int i4 = i3 + 1;
            bitmap.setPixel(i3, i3, -16777216);
            bitmap.setPixel(i4, i4, -1);
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "setColorToRandomPixel(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }
}
