package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* loaded from: classes4.dex */
public class ResumableUploadByteRequest extends ResumableNetworkRequest {
    private final int bytesToWrite;
    private final byte[] chunk;
    private final boolean isFinal;
    private final long offset;
    private final Uri uploadURL;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "POST";
    }

    public ResumableUploadByteRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, Uri uri, byte[] bArr, long j, int i, boolean z) {
        super(storageReferenceUri, firebaseApp);
        if (bArr == null && i != -1) {
            this.mException = new IllegalArgumentException("contentType is null or empty");
        }
        if (j < 0) {
            this.mException = new IllegalArgumentException("offset cannot be negative");
        }
        this.bytesToWrite = i;
        this.uploadURL = uri;
        this.chunk = i <= 0 ? null : bArr;
        this.offset = j;
        this.isFinal = z;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        if (z && i > 0) {
            super.setCustomHeader("X-Goog-Upload-Command", "upload, finalize");
        } else if (z) {
            super.setCustomHeader("X-Goog-Upload-Command", "finalize");
        } else {
            super.setCustomHeader("X-Goog-Upload-Command", "upload");
        }
        super.setCustomHeader("X-Goog-Upload-Offset", Long.toString(j));
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        return this.uploadURL;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected byte[] getOutputRaw() {
        return this.chunk;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected int getOutputRawSize() {
        int i = this.bytesToWrite;
        if (i > 0) {
            return i;
        }
        return 0;
    }
}
