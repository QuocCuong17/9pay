package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.internal.Slashes;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class StorageReference implements Comparable<StorageReference> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "StorageReference";
    private final FirebaseStorage mFirebaseStorage;
    private final Uri mStorageUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StorageReference(Uri uri, FirebaseStorage firebaseStorage) {
        Preconditions.checkArgument(uri != null, "storageUri cannot be null");
        Preconditions.checkArgument(firebaseStorage != null, "FirebaseApp cannot be null");
        this.mStorageUri = uri;
        this.mFirebaseStorage = firebaseStorage;
    }

    public StorageReference child(String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "childName cannot be null or empty");
        return new StorageReference(this.mStorageUri.buildUpon().appendEncodedPath(Slashes.preserveSlashEncode(Slashes.normalizeSlashes(str))).build(), this.mFirebaseStorage);
    }

    public StorageReference getParent() {
        String path = this.mStorageUri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        String str = RemoteSettings.FORWARD_SLASH_STRING;
        if (path.equals(RemoteSettings.FORWARD_SLASH_STRING)) {
            return null;
        }
        int lastIndexOf = path.lastIndexOf(47);
        if (lastIndexOf != -1) {
            str = path.substring(0, lastIndexOf);
        }
        return new StorageReference(this.mStorageUri.buildUpon().path(str).build(), this.mFirebaseStorage);
    }

    public StorageReference getRoot() {
        return new StorageReference(this.mStorageUri.buildUpon().path("").build(), this.mFirebaseStorage);
    }

    public String getName() {
        String path = this.mStorageUri.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public String getPath() {
        return this.mStorageUri.getPath();
    }

    public String getBucket() {
        return this.mStorageUri.getAuthority();
    }

    public FirebaseStorage getStorage() {
        return this.mFirebaseStorage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseApp getApp() {
        return getStorage().getApp();
    }

    public UploadTask putBytes(byte[] bArr) {
        Preconditions.checkArgument(bArr != null, "bytes cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, bArr);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putBytes(byte[] bArr, StorageMetadata storageMetadata) {
        Preconditions.checkArgument(bArr != null, "bytes cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, bArr);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        UploadTask uploadTask = new UploadTask(this, null, uri, null);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri, StorageMetadata storageMetadata) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, null);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri, StorageMetadata storageMetadata, Uri uri2) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, uri2);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putStream(InputStream inputStream) {
        Preconditions.checkArgument(inputStream != null, "stream cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, inputStream);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putStream(InputStream inputStream, StorageMetadata storageMetadata) {
        Preconditions.checkArgument(inputStream != null, "stream cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, inputStream);
        uploadTask.queue();
        return uploadTask;
    }

    public List<UploadTask> getActiveUploadTasks() {
        return StorageTaskManager.getInstance().getUploadTasksUnder(this);
    }

    public List<FileDownloadTask> getActiveDownloadTasks() {
        return StorageTaskManager.getInstance().getDownloadTasksUnder(this);
    }

    public Task<StorageMetadata> getMetadata() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new GetMetadataTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<Uri> getDownloadUrl() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new GetDownloadUrlTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<StorageMetadata> updateMetadata(StorageMetadata storageMetadata) {
        Preconditions.checkNotNull(storageMetadata);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new UpdateMetadataTask(this, taskCompletionSource, storageMetadata));
        return taskCompletionSource.getTask();
    }

    public Task<byte[]> getBytes(final long j) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.setStreamProcessor(new StreamDownloadTask.StreamProcessor() { // from class: com.google.firebase.storage.StorageReference.3
            @Override // com.google.firebase.storage.StreamDownloadTask.StreamProcessor
            public void doInBackground(StreamDownloadTask.TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[16384];
                    int i = 0;
                    while (true) {
                        int read = inputStream.read(bArr, 0, 16384);
                        if (read == -1) {
                            byteArrayOutputStream.flush();
                            taskCompletionSource.setResult(byteArrayOutputStream.toByteArray());
                            return;
                        } else {
                            i += read;
                            if (i > j) {
                                Log.e(StorageReference.TAG, "the maximum allowed buffer size was exceeded.");
                                throw new IndexOutOfBoundsException("the maximum allowed buffer size was exceeded.");
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                    }
                } finally {
                    inputStream.close();
                }
            }
        }).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() { // from class: com.google.firebase.storage.StorageReference.2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
                if (taskCompletionSource.getTask().isComplete()) {
                    return;
                }
                Log.e(StorageReference.TAG, "getBytes 'succeeded', but failed to set a Result.");
                taskCompletionSource.setException(StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR));
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.storage.StorageReference.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exc, 0));
            }
        });
        streamDownloadTask.queue();
        return taskCompletionSource.getTask();
    }

    public FileDownloadTask getFile(Uri uri) {
        FileDownloadTask fileDownloadTask = new FileDownloadTask(this, uri);
        fileDownloadTask.queue();
        return fileDownloadTask;
    }

    public FileDownloadTask getFile(File file) {
        return getFile(Uri.fromFile(file));
    }

    public StreamDownloadTask getStream() {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.queue();
        return streamDownloadTask;
    }

    public StreamDownloadTask getStream(StreamDownloadTask.StreamProcessor streamProcessor) {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.setStreamProcessor(streamProcessor);
        streamDownloadTask.queue();
        return streamDownloadTask;
    }

    public Task<Void> delete() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new DeleteStorageTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<ListResult> list(int i) {
        Preconditions.checkArgument(i > 0, "maxResults must be greater than zero");
        Preconditions.checkArgument(i <= 1000, "maxResults must be at most 1000");
        return listHelper(Integer.valueOf(i), null);
    }

    public Task<ListResult> list(int i, String str) {
        Preconditions.checkArgument(i > 0, "maxResults must be greater than zero");
        Preconditions.checkArgument(i <= 1000, "maxResults must be at most 1000");
        Preconditions.checkArgument(str != null, "pageToken must be non-null to resume a previous list() operation");
        return listHelper(Integer.valueOf(i), str);
    }

    public Task<ListResult> listAll() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final Executor commandPoolExecutor = StorageTaskScheduler.getInstance().getCommandPoolExecutor();
        listHelper(null, null).continueWithTask(commandPoolExecutor, new Continuation<ListResult, Task<Void>>() { // from class: com.google.firebase.storage.StorageReference.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.gms.tasks.Continuation
            public Task<Void> then(Task<ListResult> task) {
                if (task.isSuccessful()) {
                    ListResult result = task.getResult();
                    arrayList.addAll(result.getPrefixes());
                    arrayList2.addAll(result.getItems());
                    if (result.getPageToken() != null) {
                        StorageReference.this.listHelper(null, result.getPageToken()).continueWithTask(commandPoolExecutor, this);
                    } else {
                        taskCompletionSource.setResult(new ListResult(arrayList, arrayList2, null));
                    }
                } else {
                    taskCompletionSource.setException(task.getException());
                }
                return Tasks.forResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<ListResult> listHelper(Integer num, String str) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new ListTask(this, num, str, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    Uri getStorageUri() {
        return this.mStorageUri;
    }

    public String toString() {
        return "gs://" + this.mStorageUri.getAuthority() + this.mStorageUri.getEncodedPath();
    }

    public boolean equals(Object obj) {
        if (obj instanceof StorageReference) {
            return ((StorageReference) obj).toString().equals(toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(StorageReference storageReference) {
        return this.mStorageUri.compareTo(storageReference.mStorageUri);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StorageReferenceUri getStorageReferenceUri() {
        return new StorageReferenceUri(this.mStorageUri, this.mFirebaseStorage.getEmulatorSettings());
    }
}
