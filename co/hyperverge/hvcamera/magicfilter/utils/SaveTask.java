package co.hyperverge.hvcamera.magicfilter.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.magicfilter.display.MagicDisplay;
import com.facebook.internal.ServerProtocol;
import java.io.File;

/* loaded from: classes2.dex */
public class SaveTask extends AsyncTask<Boolean, Integer, String> {
    private static final String TAG = "SaveTask";
    private final byte[] data;
    int exifOrientation;
    boolean isSuccess = false;
    private Bitmap mBitmap;
    MagicDisplay mCameraDisplay;
    private final File mFile;
    boolean xflip;
    boolean yflip;

    public SaveTask(File file, byte[] bArr, MagicDisplay magicDisplay) {
        this.mFile = file;
        this.data = bArr;
        this.mCameraDisplay = magicDisplay;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Boolean... boolArr) {
        HVLog.d(TAG, "doInBackground() called with: params = [" + boolArr + "]");
        if (this.mFile == null) {
            return null;
        }
        int orientation = Exif.getOrientation(this.data);
        try {
            byte[] bArr = this.data;
            this.mBitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } catch (OutOfMemoryError e) {
            HVLog.e(TAG, "doInBackground: " + e.getMessage());
            System.gc();
            this.isSuccess = false;
            try {
                byte[] bArr2 = this.data;
                this.mBitmap = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
                this.isSuccess = true;
            } catch (OutOfMemoryError e2) {
                HVLog.d(TAG, "doInBackground: " + e2.getMessage());
            }
        }
        if (!boolArr[0].booleanValue() || !boolArr[1].booleanValue()) {
            this.xflip = false;
            this.yflip = false;
        } else if (orientation == 0 || orientation == 1 || orientation == 3) {
            this.xflip = true;
            this.yflip = false;
        } else if (orientation == 6 || orientation == 8) {
            this.xflip = false;
            this.yflip = true;
        }
        this.exifOrientation = orientation;
        this.isSuccess = true;
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        HVLog.d(TAG, "onPostExecute() called with: result = [" + str + "]");
        if (this.isSuccess || this.mBitmap == null) {
            this.mCameraDisplay.getBitmapfromgl(this.mBitmap, this.mFile, this.xflip, this.yflip, this.exifOrientation);
        }
    }
}
