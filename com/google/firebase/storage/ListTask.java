package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.ListNetworkRequest;
import org.json.JSONException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ListTask implements Runnable {
    private static final String TAG = "ListTask";
    private final Integer maxResults;
    private final String pageToken;
    private final TaskCompletionSource<ListResult> pendingResult;
    private final ExponentialBackoffSender sender;
    private final StorageReference storageRef;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListTask(StorageReference storageReference, Integer num, String str, TaskCompletionSource<ListResult> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.storageRef = storageReference;
        this.maxResults = num;
        this.pageToken = str;
        this.pendingResult = taskCompletionSource;
        FirebaseStorage storage = storageReference.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        ListResult fromJSON;
        ListNetworkRequest listNetworkRequest = new ListNetworkRequest(this.storageRef.getStorageReferenceUri(), this.storageRef.getApp(), this.maxResults, this.pageToken);
        this.sender.sendWithExponentialBackoff(listNetworkRequest);
        if (listNetworkRequest.isResultSuccess()) {
            try {
                fromJSON = ListResult.fromJSON(this.storageRef.getStorage(), listNetworkRequest.getResultBody());
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse response body. " + listNetworkRequest.getRawResult(), e);
                this.pendingResult.setException(StorageException.fromException(e));
                return;
            }
        } else {
            fromJSON = null;
        }
        TaskCompletionSource<ListResult> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            listNetworkRequest.completeTask(taskCompletionSource, fromJSON);
        }
    }
}
