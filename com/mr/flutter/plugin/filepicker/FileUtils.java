package com.mr.flutter.plugin.filepicker;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.mr.flutter.plugin.filepicker.FileInfo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FileUtils {
    private static final String PRIMARY_VOLUME_NAME = "primary";
    private static final String TAG = "FilePickerUtils";

    public static String[] getMimeTypes(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(arrayList.get(i));
            if (mimeTypeFromExtension == null) {
                Log.w(TAG, "Custom file type " + arrayList.get(i) + " is unsupported and will be ignored.");
            } else {
                arrayList2.add(mimeTypeFromExtension);
            }
        }
        Log.d(TAG, "Allowed file extensions mimes: " + arrayList2);
        return (String[]) arrayList2.toArray(new String[0]);
    }

    public static String getFileName(Uri uri, Context context) {
        String str = null;
        try {
            if (uri.getScheme().equals(FirebaseAnalytics.Param.CONTENT)) {
                Cursor query = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            str = query.getString(query.getColumnIndexOrThrow("_display_name"));
                        }
                    } finally {
                        query.close();
                    }
                }
            }
            if (str != null) {
                return str;
            }
            String path = uri.getPath();
            int lastIndexOf = path.lastIndexOf(47);
            return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
        } catch (Exception e) {
            Log.e(TAG, "Failed to handle file name: " + e.toString());
            return null;
        }
    }

    public static Uri compressImage(Uri uri, int i, Context context) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            try {
                File createImageFile = createImageFile();
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(createImageFile);
                decodeStream.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Uri fromFile = Uri.fromFile(createImageFile);
                if (openInputStream != null) {
                    openInputStream.close();
                }
                return fromFile;
            } finally {
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static File createImageFile() throws IOException {
        return File.createTempFile("JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    public static String getRealPathFromURI(Context context, Uri uri) {
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + RemoteSettings.FORWARD_SLASH_STRING + split[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = split2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
                }
            }
        } else {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static HashMap<String, Object> createFileInfoMap(File file) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("filePath", file.getAbsolutePath());
        hashMap.put("fileName", file.getName());
        return hashMap;
    }

    public static boolean clearCache(Context context) {
        try {
            recursiveDeleteFile(new File(context.getCacheDir() + "/file_picker/"));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "There was an error while clearing cached files: " + e.toString());
            return false;
        }
    }

    public static void loadData(File file, FileInfo.Builder builder) {
        try {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                bufferedInputStream.read(bArr, 0, length);
                bufferedInputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "File not found: " + e.getMessage(), null);
            } catch (IOException e2) {
                Log.e(TAG, "Failed to close file streams: " + e2.getMessage(), null);
            }
            builder.withData(bArr);
        } catch (Exception e3) {
            Log.e(TAG, "Failed to load bytes into memory with error " + e3.toString() + ". Probably the file is too big to fit device memory. Bytes won't be added to the file this time.");
        }
    }

    public static FileInfo openFileStream(Context context, Uri uri, boolean z) {
        FileOutputStream fileOutputStream;
        Log.i(TAG, "Caching from URI: " + uri.toString());
        FileInfo.Builder builder = new FileInfo.Builder();
        String fileName = getFileName(uri, context);
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir().getAbsolutePath());
        sb.append("/file_picker/");
        sb.append(System.currentTimeMillis());
        sb.append(RemoteSettings.FORWARD_SLASH_STRING);
        sb.append(fileName != null ? fileName : "unamed");
        String sb2 = sb.toString();
        File file = new File(sb2);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                fileOutputStream = new FileOutputStream(sb2);
            } catch (Exception e) {
                e = e;
                fileOutputStream = null;
            }
            try {
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read < 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    fileOutputStream.getFD().sync();
                } catch (Throwable th) {
                    fileOutputStream.getFD().sync();
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    fileOutputStream.close();
                    Log.e(TAG, "Failed to retrieve path: " + e.getMessage(), null);
                    return null;
                } catch (IOException | NullPointerException unused) {
                    Log.e(TAG, "Failed to close file streams: " + e.getMessage(), null);
                    return null;
                }
            }
        }
        Log.d(TAG, "File loaded and cached at:" + sb2);
        if (z) {
            loadData(file, builder);
        }
        builder.withPath(sb2).withName(fileName).withUri(uri).withSize(Long.parseLong(String.valueOf(file.length())));
        return builder.build();
    }

    public static String getFullPathFromTreeUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 30 && isDownloadsDocument(uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            if (documentId.equals("downloads")) {
                return path;
            }
            if (documentId.matches("^ms[df]\\:.*")) {
                return path + RemoteSettings.FORWARD_SLASH_STRING + getFileName(uri, context);
            }
            if (documentId.startsWith("raw:")) {
                return documentId.split(":")[1];
            }
            return null;
        }
        String volumePath = getVolumePath(getVolumeIdFromTreeUri(uri), context);
        new FileInfo.Builder();
        if (volumePath == null) {
            return File.separator;
        }
        if (volumePath.endsWith(File.separator)) {
            volumePath = volumePath.substring(0, volumePath.length() - 1);
        }
        String documentPathFromTreeUri = getDocumentPathFromTreeUri(uri);
        if (documentPathFromTreeUri.endsWith(File.separator)) {
            documentPathFromTreeUri = documentPathFromTreeUri.substring(0, documentPathFromTreeUri.length() - 1);
        }
        if (documentPathFromTreeUri.length() <= 0) {
            return volumePath;
        }
        if (documentPathFromTreeUri.startsWith(File.separator)) {
            return volumePath + documentPathFromTreeUri;
        }
        return volumePath + File.separator + documentPathFromTreeUri;
    }

    private static String getDirectoryPath(Class<?> cls, Object obj) {
        if (Build.VERSION.SDK_INT < 30) {
            return (String) cls.getMethod("getPath", new Class[0]).invoke(obj, new Object[0]);
        }
        File file = (File) cls.getMethod("getDirectory", new Class[0]).invoke(obj, new Object[0]);
        if (file != null) {
            return file.getPath();
        }
        return null;
    }

    private static String getVolumePath(String str, Context context) {
        Class<?> cls;
        Method method;
        Method method2;
        Object invoke;
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            cls = Class.forName("android.os.storage.StorageVolume");
            Method method3 = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            method = cls.getMethod("getUuid", new Class[0]);
            method2 = cls.getMethod("isPrimary", new Class[0]);
            invoke = method3.invoke(storageManager, new Object[0]);
        } catch (Exception unused) {
        }
        if (invoke == null) {
            return null;
        }
        int length = Array.getLength(invoke);
        for (int i = 0; i < length; i++) {
            Object obj = Array.get(invoke, i);
            String str2 = (String) method.invoke(obj, new Object[0]);
            if (((Boolean) method2.invoke(obj, new Object[0])) != null && "primary".equals(str)) {
                return getDirectoryPath(cls, obj);
            }
            if (str2 != null && str2.equals(str)) {
                return getDirectoryPath(cls, obj);
            }
        }
        return null;
    }

    private static String getVolumeIdFromTreeUri(Uri uri) {
        String[] split = DocumentsContract.getTreeDocumentId(uri).split(":");
        if (split.length > 0) {
            return split[0];
        }
        return null;
    }

    private static String getDocumentPathFromTreeUri(Uri uri) {
        String[] split = DocumentsContract.getTreeDocumentId(uri).split(":");
        return (split.length < 2 || split[1] == null) ? File.separator : split[1];
    }

    private static void recursiveDeleteFile(File file) throws Exception {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                recursiveDeleteFile(file2);
            }
        }
        file.delete();
    }
}
