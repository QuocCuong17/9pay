package org.ejbca.cvc.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes6.dex */
public final class FileHelper {
    private FileHelper() {
    }

    public static byte[] loadFile(String str) throws IOException {
        return loadFile(new File(str));
    }

    public static byte[] loadFile(File file) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            FileInputStream fileInputStream2 = new FileInputStream(file);
            boolean z = true;
            int i = 0;
            while (z) {
                try {
                    int read = fileInputStream2.read(bArr, i, length - i);
                    i += read;
                    z = read > 0 && i != length;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            System.out.println("loadFile - error when closing: " + e);
                        }
                    }
                    throw th;
                }
            }
            try {
                fileInputStream2.close();
            } catch (IOException e2) {
                System.out.println("loadFile - error when closing: " + e2);
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void writeFile(File file, byte[] bArr) throws IOException {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file), 1000);
            try {
                bufferedOutputStream2.write(bArr);
                bufferedOutputStream2.close();
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
