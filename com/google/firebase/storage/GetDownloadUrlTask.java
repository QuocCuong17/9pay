package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class GetDownloadUrlTask implements Runnable {
    private static final String DOWNLOAD_TOKENS_KEY = "downloadTokens";
    private static final String TAG = "GetMetadataTask";
    private TaskCompletionSource<Uri> pendingResult;
    private ExponentialBackoffSender sender;
    private StorageReference storageRef;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetDownloadUrlTask(StorageReference storageReference, TaskCompletionSource<Uri> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.storageRef = storageReference;
        this.pendingResult = taskCompletionSource;
        if (storageReference.getRoot().getName().equals(storageReference.getName())) {
            throw new IllegalArgumentException("getDownloadUrl() is not supported at the root of the bucket.");
        }
        FirebaseStorage storage = this.storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxOperationRetryTimeMillis());
    }

    private Uri extractDownloadUrl(JSONObject jSONObject) {
        String optString = jSONObject.optString(DOWNLOAD_TOKENS_KEY);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        String str = optString.split(",", -1)[0];
        Uri.Builder buildUpon = this.storageRef.getStorageReferenceUri().getHttpUri().buildUpon();
        buildUpon.appendQueryParameter("alt", ShareConstants.WEB_DIALOG_PARAM_MEDIA);
        buildUpon.appendQueryParameter("token", str);
        return buildUpon.build();
    }

    @Override // java.lang.Runnable
    public void run() {
        GetMetadataNetworkRequest getMetadataNetworkRequest = new GetMetadataNetworkRequest(this.storageRef.getStorageReferenceUri(), this.storageRef.getApp());
        this.sender.sendWithExponentialBackoff(getMetadataNetworkRequest);
        Uri extractDownloadUrl = getMetadataNetworkRequest.isResultSuccess() ? extractDownloadUrl(getMetadataNetworkRequest.getResultBody()) : null;
        TaskCompletionSource<Uri> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            getMetadataNetworkRequest.completeTask(taskCompletionSource, extractDownloadUrl);
        }
    }
}
