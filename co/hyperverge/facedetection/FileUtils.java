package co.hyperverge.facedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FileUtils {
    private static final String LOG_TAG = "co.hyperverge.facedetection.FileUtils";

    public static boolean isRotation90(int i) {
        if (i == 1) {
            return false;
        }
        return i == 5 || i == 6 || i == 7 || i == 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005b A[Catch: IOException -> 0x0057, TRY_LEAVE, TryCatch #1 {IOException -> 0x0057, blocks: (B:37:0x0053, B:30:0x005b), top: B:36:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void copyFileFromRawToOthers(Context context, int i, String str) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception e;
        InputStream openRawResource = context.getResources().openRawResource(i);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(str);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = openRawResource.read(bArr);
                            if (read <= 0) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                        if (openRawResource != null) {
                            openRawResource.close();
                        }
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        e = e2;
                        Log.d(LOG_TAG, e.getMessage());
                        if (openRawResource != null) {
                            openRawResource.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (openRawResource != null) {
                        try {
                            openRawResource.close();
                        } catch (IOException e3) {
                            Log.d(LOG_TAG, e3.getMessage());
                            throw th;
                        }
                    }
                    if (0 != 0) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                fileOutputStream = null;
                e = e4;
            } catch (Throwable th3) {
                th = th3;
                if (openRawResource != null) {
                }
                if (0 != 0) {
                }
                throw th;
            }
        } catch (IOException e5) {
            Log.d(LOG_TAG, e5.getMessage());
        }
    }

    public static Dimensions compressToFile(Context context, String str, String str2, int i, int i2, int i3) throws IOException {
        Dimensions bitmapDimension = getBitmapDimension(str);
        Dimensions scaledDim = getScaledDim(bitmapDimension, i);
        try {
            Bitmap decodeSampledBitmapFromFile = decodeSampledBitmapFromFile(str, bitmapDimension, scaledDim);
            if (decodeSampledBitmapFromFile == null) {
                Log.e(LOG_TAG, "BMP null at: " + str);
                return null;
            }
            try {
                if (scaledDim.width < decodeSampledBitmapFromFile.getWidth()) {
                    decodeSampledBitmapFromFile = Bitmap.createScaledBitmap(decodeSampledBitmapFromFile, scaledDim.width, scaledDim.height, true);
                }
                Bitmap fixRotation = fixRotation(decodeSampledBitmapFromFile, i3);
                scaledDim.width = fixRotation.getWidth();
                scaledDim.height = fixRotation.getHeight();
                FileOutputStream openFileOutput = context.openFileOutput(str2, 0);
                fixRotation.compress(Bitmap.CompressFormat.JPEG, i2, openFileOutput);
                fixRotation.recycle();
                openFileOutput.close();
                openFileOutput.flush();
                return scaledDim;
            } catch (Throwable unused) {
                Log.e(LOG_TAG, "Failed in scaling: " + str);
                return null;
            }
        } catch (Throwable unused2) {
            Log.e(LOG_TAG, "Failed in decode: " + str);
            return null;
        }
    }

    public static Bitmap fixRotation(Bitmap bitmap, int i) {
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
            Log.d(LOG_TAG, e.getMessage());
            return bitmap;
        }
    }

    public static Dimensions getBitmapDimension(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Dimensions(options.outWidth, options.outHeight);
    }

    public static Dimensions getScaledDim(Dimensions dimensions, int i) {
        int i2 = dimensions.width;
        int i3 = dimensions.height;
        if (i2 > i3) {
            if (i2 > i) {
                i3 = (i3 * i) / i2;
                return new Dimensions(i, i3);
            }
        } else if (i3 > i) {
            i2 = (i2 * i) / i3;
            i3 = i;
        }
        i = i2;
        return new Dimensions(i, i3);
    }

    public static int calculateInSampleSize(Dimensions dimensions, Dimensions dimensions2) {
        int i = dimensions.height;
        int i2 = dimensions.width;
        int i3 = 1;
        if (i > dimensions2.height || i2 > dimensions2.width) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 >= dimensions2.height && i5 / i3 >= dimensions2.width) {
                i3 *= 2;
            }
        }
        return i3;
    }

    public static Bitmap decodeSampledBitmapFromFile(String str, Dimensions dimensions, Dimensions dimensions2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = calculateInSampleSize(dimensions, dimensions2);
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap decodeSampledBitmapFromFileForFace(String str, Dimensions dimensions, Dimensions dimensions2) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = calculateInSampleSize(dimensions, dimensions2);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeFile(str, options);
            return fixRotation(bitmap, new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0));
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
            return bitmap;
        }
    }

    public static File[] getFileList(Context context) {
        return context.getFilesDir().listFiles();
    }

    public static void copy(Context context, String str, String str2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        FileOutputStream openFileOutput = context.openFileOutput(str2, 0);
        FileChannel channel = fileInputStream.getChannel();
        channel.transferTo(0L, channel.size(), openFileOutput.getChannel());
        fileInputStream.close();
        openFileOutput.close();
    }

    public static void copy(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        FileChannel channel = fileInputStream.getChannel();
        channel.transferTo(0L, channel.size(), fileOutputStream.getChannel());
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void deleteFile(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static long getFileSize(String str) {
        return new File(str).length();
    }

    public static boolean copyFile(InputStream inputStream, OutputStream outputStream) {
        try {
            try {
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    j += read;
                }
                boolean z = j != 0;
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    Log.d(LOG_TAG, e.getMessage());
                }
                return z;
            } catch (IOException e2) {
                Log.d(LOG_TAG, e2.getMessage());
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e3) {
                    Log.d(LOG_TAG, e3.getMessage());
                }
                return false;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e4) {
                Log.d(LOG_TAG, e4.getMessage());
            }
            throw th;
        }
    }

    /* loaded from: classes2.dex */
    public static class Dimensions {
        int height;
        int width;

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
}
