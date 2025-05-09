package co.hyperverge.hvcamera.magicfilter.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.TimingUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes2.dex */
public class SaveTask2 extends AsyncTask<Bitmap, Integer, String> {
    private static final String TAG = "SaveTask2";
    private final int exifOrientation;
    private final File mFile;
    private final onPictureSaveListener mListener;

    /* loaded from: classes2.dex */
    public interface onPictureSaveListener {
        void onSaved(String str);
    }

    public SaveTask2(File file, onPictureSaveListener onpicturesavelistener, int i) {
        this.mListener = onpicturesavelistener;
        this.mFile = file;
        this.exifOrientation = i;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        HVLog.d(TAG, "rotateBitmap() called with: bitmap = [" + bitmap + "], orientation = [" + i + "]");
        Matrix matrix = new Matrix();
        switch (i) {
            case 0:
            case 1:
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            HVLog.e(TAG, "rotateBitmap: " + e.getMessage());
            return null;
        }
    }

    private String saveBitmap(Bitmap bitmap) {
        HVLog.d(TAG, "saveBitmap() called with: bitmap = [" + bitmap + "]");
        TimingUtil timingUtil = new TimingUtil();
        timingUtil.pitch("SaveTask", "Copied exif");
        if (this.mFile.exists()) {
            this.mFile.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.mFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            bitmap.recycle();
            ExifInterface exifInterface = new ExifInterface(this.mFile.getAbsolutePath());
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, "" + this.exifOrientation);
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME_ORIGINAL, DateHelper.sFormatter.format(new Date(System.currentTimeMillis())));
            exifInterface.saveAttributes();
            timingUtil.pitch("SaveTask", "Saved exif");
            return this.mFile.toString();
        } catch (FileNotFoundException e) {
            HVLog.e(TAG, "saveBitmap: " + e.getMessage());
            return null;
        } catch (IOException e2) {
            HVLog.e(TAG, "saveBitmap: " + e2.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Bitmap... bitmapArr) {
        HVLog.d(TAG, "doInBackground() called with: params = [" + bitmapArr + "]");
        if (this.mFile == null) {
            return null;
        }
        return saveBitmap(bitmapArr[0]);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        onPictureSaveListener onpicturesavelistener;
        HVLog.d(TAG, "onPostExecute() called with: result = [" + str + "]");
        if (str == null || (onpicturesavelistener = this.mListener) == null) {
            return;
        }
        onpicturesavelistener.onSaved(str);
    }
}
