package com.getkeepsafe.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.getkeepsafe.relinker.ReLinker;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes3.dex */
public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    private String[] sourceDirectories(final Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.zipFile = zipFile;
            this.zipEntry = zipEntry;
        }
    }

    private ZipFileInZipEntry findAPKWithLibrary(final Context context, final String[] abis, final String mappedLibraryName, final ReLinkerInstance instance) {
        String[] sourceDirectories = sourceDirectories(context);
        int length = sourceDirectories.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i2 >= length) {
                return null;
            }
            String str = sourceDirectories[i2];
            int i3 = i;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile != null) {
                int i5 = i;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 < 5) {
                        int length2 = abis.length;
                        int i7 = i;
                        while (i7 < length2) {
                            String str2 = "lib" + File.separatorChar + abis[i7] + File.separatorChar + mappedLibraryName;
                            Object[] objArr = new Object[2];
                            objArr[i] = str2;
                            objArr[1] = str;
                            instance.log("Looking for %s in APK %s...", objArr);
                            ZipEntry entry = zipFile.getEntry(str2);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                            i7++;
                            i = 0;
                        }
                        i5 = i6;
                        i = 0;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i2++;
            i = 0;
        }
    }

    private String[] getSupportedABIs(Context context, String mappedLibraryName) {
        Pattern compile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + mappedLibraryName);
        HashSet hashSet = new HashSet();
        for (String str : sourceDirectories(context)) {
            try {
                Enumeration<? extends ZipEntry> entries = new ZipFile(new File(str), 1).entries();
                while (entries.hasMoreElements()) {
                    Matcher matcher = compile.matcher(entries.nextElement().getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    @Override // com.getkeepsafe.relinker.ReLinker.LibraryInstaller
    public void installLibrary(Context context, String[] strArr, String str, File file, ReLinkerInstance reLinkerInstance) {
        ZipFileInZipEntry findAPKWithLibrary;
        String[] strArr2;
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        long copy;
        ZipFileInZipEntry zipFileInZipEntry = null;
        Closeable closeable = null;
        try {
            findAPKWithLibrary = findAPKWithLibrary(context, strArr, str, reLinkerInstance);
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (findAPKWithLibrary == null) {
                try {
                    strArr2 = getSupportedABIs(context, str);
                } catch (Exception e) {
                    strArr2 = new String[]{e.toString()};
                }
                throw new MissingLibraryException(str, strArr, strArr2);
            }
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i >= 5) {
                    reLinkerInstance.log("FATAL! Couldn't extract the library from the APK!");
                    if (findAPKWithLibrary != null) {
                        try {
                            if (findAPKWithLibrary.zipFile != null) {
                                findAPKWithLibrary.zipFile.close();
                                return;
                            }
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    return;
                }
                reLinkerInstance.log("Found %s! Extracting...", str);
                try {
                    if (file.exists() || file.createNewFile()) {
                        try {
                            inputStream = findAPKWithLibrary.zipFile.getInputStream(findAPKWithLibrary.zipEntry);
                            try {
                                fileOutputStream = new FileOutputStream(file);
                            } catch (FileNotFoundException unused2) {
                                fileOutputStream = null;
                            } catch (IOException unused3) {
                                fileOutputStream = null;
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = null;
                            }
                            try {
                                copy = copy(inputStream, fileOutputStream);
                                fileOutputStream.getFD().sync();
                            } catch (FileNotFoundException unused4) {
                                closeSilently(inputStream);
                                closeSilently(fileOutputStream);
                                i = i2;
                            } catch (IOException unused5) {
                                closeSilently(inputStream);
                                closeSilently(fileOutputStream);
                                i = i2;
                            } catch (Throwable th3) {
                                th = th3;
                                closeable = inputStream;
                                closeSilently(closeable);
                                closeSilently(fileOutputStream);
                                throw th;
                            }
                        } catch (FileNotFoundException unused6) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (IOException unused7) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (Throwable th4) {
                            th = th4;
                            fileOutputStream = null;
                        }
                        if (copy == file.length()) {
                            closeSilently(inputStream);
                            closeSilently(fileOutputStream);
                            file.setReadable(true, false);
                            file.setExecutable(true, false);
                            file.setWritable(true);
                            if (findAPKWithLibrary != null) {
                                try {
                                    if (findAPKWithLibrary.zipFile != null) {
                                        findAPKWithLibrary.zipFile.close();
                                        return;
                                    }
                                    return;
                                } catch (IOException unused8) {
                                    return;
                                }
                            }
                            return;
                        }
                        closeSilently(inputStream);
                        closeSilently(fileOutputStream);
                    }
                } catch (IOException unused9) {
                }
                i = i2;
            }
        } catch (Throwable th5) {
            th = th5;
            zipFileInZipEntry = findAPKWithLibrary;
            if (zipFileInZipEntry != null) {
                try {
                    if (zipFileInZipEntry.zipFile != null) {
                        zipFileInZipEntry.zipFile.close();
                    }
                } catch (IOException unused10) {
                }
            }
            throw th;
        }
    }

    private long copy(InputStream in, OutputStream out) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = in.read(bArr);
            if (read != -1) {
                out.write(bArr, 0, read);
                j += read;
            } else {
                out.flush();
                return j;
            }
        }
    }

    private void closeSilently(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
