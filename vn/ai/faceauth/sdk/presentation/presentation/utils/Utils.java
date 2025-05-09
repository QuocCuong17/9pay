package vn.ai.faceauth.sdk.presentation.presentation.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004¨\u0006\u000e"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/utils/Utils;", "", "()V", "getOutputDirectory", "Ljava/io/File;", "context", "Landroid/content/Context;", "initFileImage", Constants.PATH, "", "saveBitmapToFile", "rootBitmap", "Landroid/graphics/Bitmap;", "file", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class Utils {
    public final File getOutputDirectory(Context context) {
        File file;
        Context applicationContext = context.getApplicationContext();
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            file = new File(cacheDir, tfwhgw.rnigpa(375));
            file.mkdirs();
        } else {
            file = null;
        }
        return (file == null || !file.exists()) ? applicationContext.getFilesDir() : file;
    }

    public final File initFileImage(Context context, String path) {
        File file = new File(getOutputDirectory(context), tfwhgw.rnigpa(376) + path + tfwhgw.rnigpa(377));
        StringBuilder sb = new StringBuilder(tfwhgw.rnigpa(378));
        sb.append(file);
        Log.e(tfwhgw.rnigpa(379), sb.toString());
        return file;
    }

    public final File saveBitmapToFile(Bitmap rootBitmap, File file) {
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            rootBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
