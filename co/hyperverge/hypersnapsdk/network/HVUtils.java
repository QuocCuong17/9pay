package co.hyperverge.hypersnapsdk.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class HVUtils {
    private static final String TAG = "HVUtils";

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a5, code lost:
    
        r3.write(r4.toByteArray());
        r3.flush();
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00d0, code lost:
    
        r9 = r15.getAbsolutePath();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00d4, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String compressImageToSize(String str, int i) {
        File file = new File(str);
        if (file.length() / 1024 <= i) {
            return str;
        }
        String[] split = file.getName().split("\\.");
        long j = i * 1024;
        long j2 = j - (((int) (j / 100)) * 10);
        int i2 = 90;
        try {
            File file2 = new File(file.getParent(), split[0] + "_compressed.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            int i3 = 5;
            while (true) {
                int i4 = (i3 + i2) / 2;
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.reset();
                } catch (IOException e) {
                    Log.e(TAG, Utils.getErrorMessage(e));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                }
                decodeFile.compress(Bitmap.CompressFormat.JPEG, i4, byteArrayOutputStream);
                File file3 = file2;
                long size = byteArrayOutputStream.size();
                if (size > j) {
                    i2 = i4 - 1;
                } else {
                    i3 = i4 + 1;
                }
                if (i4 < 5 || (size < j && size > j2)) {
                    try {
                        break;
                    } catch (IOException e2) {
                        Log.e(TAG, Utils.getErrorMessage(e2));
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                        }
                    }
                } else {
                    file2 = file3;
                }
            }
        } catch (Exception e3) {
            e = e3;
            String str2 = null;
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return str2;
        }
        return str2;
    }
}
