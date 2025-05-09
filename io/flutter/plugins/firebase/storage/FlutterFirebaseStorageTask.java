package io.flutter.plugins.firebase.storage;

import android.net.Uri;
import android.util.SparseArray;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class FlutterFirebaseStorageTask {
    static final SparseArray<FlutterFirebaseStorageTask> inProgressTasks = new SparseArray<>();
    private final byte[] bytes;
    private final Uri fileUri;
    private final int handle;
    private final StorageMetadata metadata;
    private final StorageReference reference;
    private StorageTask<?> storageTask;
    private final FlutterFirebaseStorageTaskType type;
    private final Object pauseSyncObject = new Object();
    private final Object resumeSyncObject = new Object();
    private final Object cancelSyncObject = new Object();
    private Boolean destroyed = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum FlutterFirebaseStorageTaskType {
        FILE,
        BYTES,
        DOWNLOAD
    }

    private FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType flutterFirebaseStorageTaskType, int i, StorageReference storageReference, byte[] bArr, Uri uri, StorageMetadata storageMetadata) {
        this.type = flutterFirebaseStorageTaskType;
        this.handle = i;
        this.reference = storageReference;
        this.bytes = bArr;
        this.fileUri = uri;
        this.metadata = storageMetadata;
        SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
        synchronized (sparseArray) {
            sparseArray.put(i, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseStorageTask getInProgressTaskForHandle(int i) {
        FlutterFirebaseStorageTask flutterFirebaseStorageTask;
        SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
        synchronized (sparseArray) {
            flutterFirebaseStorageTask = sparseArray.get(i);
        }
        return flutterFirebaseStorageTask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void cancelInProgressTasks() {
        synchronized (inProgressTasks) {
            int i = 0;
            while (true) {
                SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
                if (i < sparseArray.size()) {
                    FlutterFirebaseStorageTask valueAt = sparseArray.valueAt(i);
                    if (valueAt != null) {
                        valueAt.destroy();
                    }
                    i++;
                } else {
                    sparseArray.clear();
                }
            }
        }
    }

    public static FlutterFirebaseStorageTask uploadBytes(int i, StorageReference storageReference, byte[] bArr, StorageMetadata storageMetadata) {
        return new FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType.BYTES, i, storageReference, bArr, null, storageMetadata);
    }

    public static FlutterFirebaseStorageTask uploadFile(int i, StorageReference storageReference, Uri uri, StorageMetadata storageMetadata) {
        return new FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType.FILE, i, storageReference, null, uri, storageMetadata);
    }

    public static FlutterFirebaseStorageTask downloadFile(int i, StorageReference storageReference, File file) {
        return new FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType.DOWNLOAD, i, storageReference, null, Uri.fromFile(file), null);
    }

    public static Map<String, Object> parseUploadTaskSnapshot(UploadTask.TaskSnapshot taskSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PATH, taskSnapshot.getStorage().getPath());
        hashMap.put("bytesTransferred", Long.valueOf(taskSnapshot.getBytesTransferred()));
        hashMap.put("totalBytes", Long.valueOf(taskSnapshot.getTotalByteCount()));
        if (taskSnapshot.getMetadata() != null) {
            hashMap.put(TtmlNode.TAG_METADATA, FlutterFirebaseStoragePlugin.parseMetadataToMap(taskSnapshot.getMetadata()));
        }
        return hashMap;
    }

    public static Map<String, Object> parseDownloadTaskSnapshot(FileDownloadTask.TaskSnapshot taskSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PATH, taskSnapshot.getStorage().getPath());
        if (taskSnapshot.getTask().isSuccessful()) {
            hashMap.put("bytesTransferred", Long.valueOf(taskSnapshot.getTotalByteCount()));
        } else {
            hashMap.put("bytesTransferred", Long.valueOf(taskSnapshot.getBytesTransferred()));
        }
        hashMap.put("totalBytes", Long.valueOf(taskSnapshot.getTotalByteCount()));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> parseTaskSnapshot(Object obj) {
        if (obj instanceof FileDownloadTask.TaskSnapshot) {
            return parseDownloadTaskSnapshot((FileDownloadTask.TaskSnapshot) obj);
        }
        return parseUploadTaskSnapshot((UploadTask.TaskSnapshot) obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy() {
        if (this.destroyed.booleanValue()) {
            return;
        }
        this.destroyed = true;
        SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
        synchronized (sparseArray) {
            if (this.storageTask.isInProgress() || this.storageTask.isPaused()) {
                this.storageTask.cancel();
            }
            sparseArray.remove(this.handle);
        }
        synchronized (this.cancelSyncObject) {
            this.cancelSyncObject.notifyAll();
        }
        synchronized (this.pauseSyncObject) {
            this.pauseSyncObject.notifyAll();
        }
        synchronized (this.resumeSyncObject) {
            this.resumeSyncObject.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskStateChannelStreamHandler startTaskWithMethodChannel(MethodChannel methodChannel) throws Exception {
        Uri uri;
        Uri uri2;
        byte[] bArr;
        if (this.type == FlutterFirebaseStorageTaskType.BYTES && (bArr = this.bytes) != null) {
            StorageMetadata storageMetadata = this.metadata;
            if (storageMetadata == null) {
                this.storageTask = this.reference.putBytes(bArr);
            } else {
                this.storageTask = this.reference.putBytes(bArr, storageMetadata);
            }
        } else if (this.type == FlutterFirebaseStorageTaskType.FILE && (uri2 = this.fileUri) != null) {
            StorageMetadata storageMetadata2 = this.metadata;
            if (storageMetadata2 == null) {
                this.storageTask = this.reference.putFile(uri2);
            } else {
                this.storageTask = this.reference.putFile(uri2, storageMetadata2);
            }
        } else if (this.type == FlutterFirebaseStorageTaskType.DOWNLOAD && (uri = this.fileUri) != null) {
            this.storageTask = this.reference.getFile(uri);
        } else {
            throw new Exception("Unable to start task. Some arguments have no been initialized.");
        }
        return new TaskStateChannelStreamHandler(this, this.reference.getStorage(), this.storageTask);
    }

    private Map<String, Object> getTaskEventMap(Object obj, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("handle", Integer.valueOf(this.handle));
        hashMap.put("appName", this.reference.getStorage().getApp().getName());
        hashMap.put("bucket", this.reference.getBucket());
        if (obj != null) {
            hashMap.put("snapshot", parseTaskSnapshot(obj));
        }
        if (exc != null) {
            hashMap.put("error", FlutterFirebaseStoragePlugin.getExceptionDetails(exc));
        }
        return hashMap;
    }

    public Object getSnapshot() {
        return this.storageTask.getSnapshot();
    }

    public boolean isDestroyed() {
        return this.destroyed.booleanValue();
    }

    public void notifyResumeObjects() {
        synchronized (this.resumeSyncObject) {
            this.resumeSyncObject.notifyAll();
        }
    }

    public void notifyCancelObjects() {
        synchronized (this.cancelSyncObject) {
            this.cancelSyncObject.notifyAll();
        }
    }

    public void notifyPauseObjects() {
        synchronized (this.pauseSyncObject) {
            this.pauseSyncObject.notifyAll();
        }
    }

    public StorageTask<?> getAndroidTask() {
        return this.storageTask;
    }
}
