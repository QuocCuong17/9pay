package com.mr.flutter.plugin.filepicker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
public class FilePickerDelegate implements PluginRegistry.ActivityResultListener, PluginRegistry.RequestPermissionsResultListener {
    private static final int REQUEST_CODE = (FilePickerPlugin.class.hashCode() + 43) & 65535;
    private static final int SAVE_FILE_CODE = (FilePickerPlugin.class.hashCode() + 83) & 65535;
    private static final String TAG = "FilePickerDelegate";
    private final Activity activity;
    private String[] allowedExtensions;
    private byte[] bytes;
    private int compressionQuality;
    private EventChannel.EventSink eventSink;
    private boolean isMultipleSelection;
    private boolean loadDataToMemory;
    private MethodChannel.Result pendingResult;
    private final PermissionManager permissionManager;
    private String type;

    /* loaded from: classes4.dex */
    interface PermissionManager {
        void askForPermission(String str, int i);

        boolean isPermissionGranted(String str);
    }

    public FilePickerDelegate(final Activity activity) {
        this(activity, null, new PermissionManager() { // from class: com.mr.flutter.plugin.filepicker.FilePickerDelegate.1
            @Override // com.mr.flutter.plugin.filepicker.FilePickerDelegate.PermissionManager
            public boolean isPermissionGranted(String str) {
                return ActivityCompat.checkSelfPermission(activity, str) == 0;
            }

            @Override // com.mr.flutter.plugin.filepicker.FilePickerDelegate.PermissionManager
            public void askForPermission(String str, int i) {
                ActivityCompat.requestPermissions(activity, new String[]{str}, i);
            }
        });
    }

    public void setEventHandler(EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
    }

    FilePickerDelegate(Activity activity, MethodChannel.Result result, PermissionManager permissionManager) {
        this.isMultipleSelection = false;
        this.loadDataToMemory = false;
        this.compressionQuality = 20;
        this.activity = activity;
        this.pendingResult = result;
        this.permissionManager = permissionManager;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int i, int i2, final Intent intent) {
        if (i != SAVE_FILE_CODE) {
            if (this.type == null) {
                return false;
            }
            int i3 = REQUEST_CODE;
            if (i == i3 && i2 == -1) {
                dispatchEventStatus(true);
                new Thread(new Runnable() { // from class: com.mr.flutter.plugin.filepicker.FilePickerDelegate.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Uri uri;
                        FileInfo openFileStream;
                        if (intent == null) {
                            FilePickerDelegate.this.finishWithError("unknown_activity", "Unknown activity error, please fill an issue.");
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        int i4 = 0;
                        if (intent.getClipData() != null) {
                            int itemCount = intent.getClipData().getItemCount();
                            while (i4 < itemCount) {
                                Uri uri2 = intent.getClipData().getItemAt(i4).getUri();
                                if (Objects.equals(FilePickerDelegate.this.type, "image/*") && FilePickerDelegate.this.compressionQuality > 0) {
                                    uri2 = FileUtils.compressImage(uri2, FilePickerDelegate.this.compressionQuality, FilePickerDelegate.this.activity.getApplicationContext());
                                }
                                FileInfo openFileStream2 = FileUtils.openFileStream(FilePickerDelegate.this.activity, uri2, FilePickerDelegate.this.loadDataToMemory);
                                if (openFileStream2 != null) {
                                    arrayList.add(openFileStream2);
                                    Log.d(FilePickerDelegate.TAG, "[MultiFilePick] File #" + i4 + " - URI: " + uri2.getPath());
                                }
                                i4++;
                            }
                            FilePickerDelegate.this.finishWithSuccess(arrayList);
                            return;
                        }
                        if (intent.getData() != null) {
                            Uri data = intent.getData();
                            if (Objects.equals(FilePickerDelegate.this.type, "image/*") && FilePickerDelegate.this.compressionQuality > 0) {
                                data = FileUtils.compressImage(data, FilePickerDelegate.this.compressionQuality, FilePickerDelegate.this.activity.getApplicationContext());
                            }
                            if (!FilePickerDelegate.this.type.equals("dir") || Build.VERSION.SDK_INT < 21) {
                                FileInfo openFileStream3 = FileUtils.openFileStream(FilePickerDelegate.this.activity, data, FilePickerDelegate.this.loadDataToMemory);
                                if (openFileStream3 != null) {
                                    arrayList.add(openFileStream3);
                                }
                                if (arrayList.isEmpty()) {
                                    FilePickerDelegate.this.finishWithError("unknown_path", "Failed to retrieve path.");
                                    return;
                                }
                                Log.d(FilePickerDelegate.TAG, "File path:" + arrayList.toString());
                                FilePickerDelegate.this.finishWithSuccess(arrayList);
                                return;
                            }
                            Uri buildDocumentUriUsingTree = DocumentsContract.buildDocumentUriUsingTree(data, DocumentsContract.getTreeDocumentId(data));
                            Log.d(FilePickerDelegate.TAG, "[SingleFilePick] File URI:" + buildDocumentUriUsingTree.toString());
                            String fullPathFromTreeUri = FileUtils.getFullPathFromTreeUri(buildDocumentUriUsingTree, FilePickerDelegate.this.activity);
                            if (fullPathFromTreeUri != null) {
                                FilePickerDelegate.this.finishWithSuccess(fullPathFromTreeUri);
                                return;
                            } else {
                                FilePickerDelegate.this.finishWithError("unknown_path", "Failed to retrieve directory path.");
                                return;
                            }
                        }
                        if (intent.getExtras() == null) {
                            FilePickerDelegate.this.finishWithError("unknown_activity", "Unknown activity error, please fill an issue.");
                            return;
                        }
                        Bundle extras = intent.getExtras();
                        if (extras.keySet().contains("selectedItems")) {
                            ArrayList selectedItems = FilePickerDelegate.this.getSelectedItems(extras);
                            if (selectedItems != null) {
                                Iterator it = selectedItems.iterator();
                                while (it.hasNext()) {
                                    Parcelable parcelable = (Parcelable) it.next();
                                    if ((parcelable instanceof Uri) && (openFileStream = FileUtils.openFileStream(FilePickerDelegate.this.activity, (uri = (Uri) parcelable), FilePickerDelegate.this.loadDataToMemory)) != null) {
                                        arrayList.add(openFileStream);
                                        Log.d(FilePickerDelegate.TAG, "[MultiFilePick] File #" + i4 + " - URI: " + uri.getPath());
                                    }
                                    i4++;
                                }
                            }
                            FilePickerDelegate.this.finishWithSuccess(arrayList);
                            return;
                        }
                        FilePickerDelegate.this.finishWithError("unknown_path", "Failed to retrieve path from bundle.");
                    }
                }).start();
                return true;
            }
            if (i == i3 && i2 == 0) {
                Log.i(TAG, "User cancelled the picker request");
                finishWithSuccess(null);
                return true;
            }
            if (i == i3) {
                finishWithError("unknown_activity", "Unknown activity error, please fill an issue.");
            }
            return false;
        }
        if (i2 == -1) {
            if (intent == null) {
                return false;
            }
            dispatchEventStatus(true);
            Uri data = intent.getData();
            if (data != null) {
                String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + FileUtils.getFileName(data, this.activity);
                try {
                    OutputStream openOutputStream = this.activity.getContentResolver().openOutputStream(data);
                    if (openOutputStream != null) {
                        openOutputStream.write(this.bytes);
                        openOutputStream.flush();
                        openOutputStream.close();
                    }
                    finishWithSuccess(str);
                    return true;
                } catch (IOException e) {
                    Log.i(TAG, "Error while saving file", e);
                    finishWithError("Error while saving file", e.getMessage());
                }
            }
        }
        if (i2 == 0) {
            Log.i(TAG, "User cancelled the save request");
            finishWithSuccess(null);
        }
        return false;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean z = false;
        if (REQUEST_CODE != i) {
            return false;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            z = true;
        }
        if (z) {
            startFileExplorer();
        } else {
            finishWithError("read_external_storage_denied", "User did not allow reading external storage");
        }
        return true;
    }

    private boolean setPendingMethodCallAndResult(MethodChannel.Result result) {
        if (this.pendingResult != null) {
            return false;
        }
        this.pendingResult = result;
        return true;
    }

    private static void finishWithAlreadyActiveError(MethodChannel.Result result) {
        result.error("already_active", "File picker is already active", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Parcelable> getSelectedItems(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 33) {
            return bundle.getParcelableArrayList("selectedItems", Parcelable.class);
        }
        return bundle.getParcelableArrayList("selectedItems");
    }

    private void startFileExplorer() {
        Intent intent;
        String str = this.type;
        if (str == null) {
            return;
        }
        if (str.equals("dir")) {
            intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        } else {
            if (this.type.equals("image/*")) {
                intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            } else {
                intent = new Intent("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
            }
            Uri parse = Uri.parse(Environment.getExternalStorageDirectory().getPath() + File.separator);
            Log.d(TAG, "Selected type " + this.type);
            intent.setDataAndType(parse, this.type);
            intent.setType(this.type);
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.isMultipleSelection);
            intent.putExtra("multi-pick", this.isMultipleSelection);
            if (this.type.contains(",")) {
                this.allowedExtensions = this.type.split(",");
            }
            String[] strArr = this.allowedExtensions;
            if (strArr != null) {
                intent.putExtra("android.intent.extra.MIME_TYPES", strArr);
            }
        }
        if (intent.resolveActivity(this.activity.getPackageManager()) != null) {
            this.activity.startActivityForResult(Intent.createChooser(intent, null), REQUEST_CODE);
        } else {
            Log.e(TAG, "Can't find a valid activity to handle the request. Make sure you've a file explorer installed.");
            finishWithError("invalid_format_type", "Can't handle the provided file type.");
        }
    }

    public void startFileExplorer(String str, boolean z, boolean z2, String[] strArr, int i, MethodChannel.Result result) {
        if (!setPendingMethodCallAndResult(result)) {
            finishWithAlreadyActiveError(result);
            return;
        }
        this.type = str;
        this.isMultipleSelection = z;
        this.loadDataToMemory = z2;
        this.allowedExtensions = strArr;
        this.compressionQuality = i;
        if (Build.VERSION.SDK_INT < 33 && !this.permissionManager.isPermissionGranted("android.permission.READ_EXTERNAL_STORAGE")) {
            this.permissionManager.askForPermission("android.permission.READ_EXTERNAL_STORAGE", REQUEST_CODE);
        } else {
            startFileExplorer();
        }
    }

    public void saveFile(String str, String str2, String str3, String[] strArr, byte[] bArr, MethodChannel.Result result) {
        if (!setPendingMethodCallAndResult(result)) {
            finishWithAlreadyActiveError(result);
            return;
        }
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (str != null && !str.isEmpty()) {
            intent.putExtra("android.intent.extra.TITLE", str);
        }
        this.bytes = bArr;
        if (str2 != null && !"dir".equals(str2) && str2.split(",").length == 1) {
            intent.setType(str2);
        } else {
            intent.setType("*/*");
        }
        if (str3 != null && !str3.isEmpty() && Build.VERSION.SDK_INT >= 26) {
            intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(str3));
        }
        if (strArr != null && strArr.length > 0) {
            intent.putExtra("android.intent.extra.MIME_TYPES", strArr);
        }
        if (intent.resolveActivity(this.activity.getPackageManager()) != null) {
            this.activity.startActivityForResult(intent, SAVE_FILE_CODE);
        } else {
            Log.e(TAG, "Can't find a valid activity to handle the request. Make sure you've a file explorer installed.");
            finishWithError("invalid_format_type", "Can't handle the provided file type.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithSuccess(Object obj) {
        dispatchEventStatus(false);
        if (this.pendingResult != null) {
            if (obj != null && !(obj instanceof String)) {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((ArrayList) obj).iterator();
                while (it.hasNext()) {
                    arrayList.add(((FileInfo) it.next()).toMap());
                }
                obj = arrayList;
            }
            this.pendingResult.success(obj);
            clearPendingResult();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithError(String str, String str2) {
        if (this.pendingResult == null) {
            return;
        }
        dispatchEventStatus(false);
        this.pendingResult.error(str, str2, null);
        clearPendingResult();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.mr.flutter.plugin.filepicker.FilePickerDelegate$3] */
    private void dispatchEventStatus(final boolean z) {
        if (this.eventSink == null || this.type.equals("dir")) {
            return;
        }
        new Handler(Looper.getMainLooper()) { // from class: com.mr.flutter.plugin.filepicker.FilePickerDelegate.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                FilePickerDelegate.this.eventSink.success(Boolean.valueOf(z));
            }
        }.obtainMessage().sendToTarget();
    }

    private void clearPendingResult() {
        this.pendingResult = null;
    }
}
