package org.tensorflow.lite.support.common;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes6.dex */
public class FileUtil {
    private FileUtil() {
    }

    public static List<String> loadLabels(Context context, String filePath) throws IOException {
        return loadLabels(context, filePath, Charset.defaultCharset());
    }

    public static List<String> loadLabels(Context context, String filePath, Charset cs) throws IOException {
        SupportPreconditions.checkNotNull(context, "Context cannot be null.");
        SupportPreconditions.checkNotNull(filePath, "File path cannot be null.");
        InputStream open = context.getAssets().open(filePath);
        try {
            List<String> loadLabels = loadLabels(open, cs);
            if (open != null) {
                open.close();
            }
            return loadLabels;
        } catch (Throwable th) {
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static List<String> loadLabels(InputStream inputStream) throws IOException {
        return loadLabels(inputStream, Charset.defaultCharset());
    }

    public static List<String> loadLabels(InputStream inputStream, Charset cs) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, cs));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (readLine.trim().length() > 0) {
                        arrayList.add(readLine);
                    }
                } else {
                    bufferedReader.close();
                    return arrayList;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static List<String> loadSingleColumnTextFile(Context context, String filePath, Charset cs) throws IOException {
        return loadLabels(context, filePath, cs);
    }

    public static List<String> loadSingleColumnTextFile(InputStream inputStream, Charset cs) throws IOException {
        return loadLabels(inputStream, cs);
    }

    public static MappedByteBuffer loadMappedFile(Context context, String filePath) throws IOException {
        SupportPreconditions.checkNotNull(context, "Context should not be null.");
        SupportPreconditions.checkNotNull(filePath, "File path cannot be null.");
        AssetFileDescriptor openFd = context.getAssets().openFd(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(openFd.getFileDescriptor());
            try {
                MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
                fileInputStream.close();
                if (openFd != null) {
                    openFd.close();
                }
                return map;
            } finally {
            }
        } catch (Throwable th) {
            if (openFd != null) {
                try {
                    openFd.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static byte[] loadByteFromFile(Context context, String filePath) throws IOException {
        MappedByteBuffer loadMappedFile = loadMappedFile(context, filePath);
        byte[] bArr = new byte[loadMappedFile.remaining()];
        loadMappedFile.get(bArr);
        return bArr;
    }
}
