package com.github.barteksc.pdfviewer.util;

import android.content.Context;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class FileUtils {
    private FileUtils() {
    }

    public static File fileFromAsset(Context context, String str) throws IOException {
        File file = new File(context.getCacheDir(), str + "-pdfview.pdf");
        if (str.contains(RemoteSettings.FORWARD_SLASH_STRING)) {
            file.getParentFile().mkdirs();
        }
        copy(context.getAssets().open(str), file);
        return file;
    }

    public static void copy(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else {
                        fileOutputStream2.write(bArr, 0, read);
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } finally {
                        fileOutputStream2.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } finally {
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
