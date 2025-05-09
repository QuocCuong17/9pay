package com.google.firebase.storage;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.net.HttpHeaders;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public class StreamDownloadTask extends StorageTask<TaskSnapshot> {
    static final long PREFERRED_CHUNK_SIZE = 262144;
    private static final String TAG = "StreamDownloadTask";
    private long bytesDownloaded;
    private long bytesDownloadedSnapped;
    private String eTagVerification;
    private InputStream inputStream;
    private StreamProcessor processor;
    private NetworkRequest request;
    private ExponentialBackoffSender sender;
    private StorageReference storageRef;
    private volatile Exception exception = null;
    private volatile int resultCode = 0;
    private long totalBytes = -1;

    /* loaded from: classes4.dex */
    public interface StreamProcessor {
        void doInBackground(TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException;
    }

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamDownloadTask(StorageReference storageReference) {
        this.storageRef = storageReference;
        FirebaseStorage storage = storageReference.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamDownloadTask setStreamProcessor(StreamProcessor streamProcessor) {
        Preconditions.checkNotNull(streamProcessor);
        Preconditions.checkState(this.processor == null);
        this.processor = streamProcessor;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public StorageReference getStorage() {
        return this.storageRef;
    }

    long getTotalBytes() {
        return this.totalBytes;
    }

    void recordDownloadedBytes(long j) {
        long j2 = this.bytesDownloaded + j;
        this.bytesDownloaded = j2;
        if (this.bytesDownloadedSnapped + 262144 <= j2) {
            if (getInternalState() == 4) {
                tryChangeState(4, false);
            } else {
                this.bytesDownloadedSnapped = this.bytesDownloaded;
            }
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void schedule() {
        StorageTaskScheduler.getInstance().scheduleDownload(getRunnable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream createDownloadStream() throws Exception {
        String str;
        this.sender.reset();
        NetworkRequest networkRequest = this.request;
        if (networkRequest != null) {
            networkRequest.performRequestEnd();
        }
        GetNetworkRequest getNetworkRequest = new GetNetworkRequest(this.storageRef.getStorageReferenceUri(), this.storageRef.getApp(), this.bytesDownloaded);
        this.request = getNetworkRequest;
        boolean z = false;
        this.sender.sendWithExponentialBackoff(getNetworkRequest, false);
        this.resultCode = this.request.getResultCode();
        this.exception = this.request.getException() != null ? this.request.getException() : this.exception;
        if (isValidHttpResponseCode(this.resultCode) && this.exception == null && getInternalState() == 4) {
            z = true;
        }
        if (z) {
            String resultString = this.request.getResultString(HttpHeaders.ETAG);
            if (!TextUtils.isEmpty(resultString) && (str = this.eTagVerification) != null && !str.equals(resultString)) {
                this.resultCode = 409;
                throw new IOException("The ETag on the server changed.");
            }
            this.eTagVerification = resultString;
            this.totalBytes = this.request.getResultingContentLength() + this.bytesDownloaded;
            return this.request.getStream();
        }
        throw new IOException("Could not open resulting stream.");
    }

    @Override // com.google.firebase.storage.StorageTask
    void run() {
        if (this.exception != null) {
            tryChangeState(64, false);
            return;
        }
        if (tryChangeState(4, false)) {
            StreamProgressWrapper streamProgressWrapper = new StreamProgressWrapper(new Callable<InputStream>() { // from class: com.google.firebase.storage.StreamDownloadTask.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public InputStream call() throws Exception {
                    return StreamDownloadTask.this.createDownloadStream();
                }
            }, this);
            this.inputStream = new BufferedInputStream(streamProgressWrapper);
            try {
                streamProgressWrapper.ensureStream();
                StreamProcessor streamProcessor = this.processor;
                if (streamProcessor != null) {
                    try {
                        streamProcessor.doInBackground(snapState(), this.inputStream);
                    } catch (Exception e) {
                        Log.w(TAG, "Exception occurred calling doInBackground.", e);
                        this.exception = e;
                    }
                }
            } catch (IOException e2) {
                Log.d(TAG, "Initial opening of Stream failed", e2);
                this.exception = e2;
            }
            if (this.inputStream == null) {
                this.request.performRequestEnd();
                this.request = null;
            }
            if (this.exception == null && getInternalState() == 4) {
                tryChangeState(4, false);
                tryChangeState(128, false);
                return;
            }
            if (tryChangeState(getInternalState() == 32 ? 256 : 64, false)) {
                return;
            }
            Log.w(TAG, "Unable to change download task to final state from " + getInternalState());
        }
    }

    @Override // com.google.firebase.storage.StorageTask, com.google.firebase.storage.ControllableTask
    public boolean resume() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    @Override // com.google.firebase.storage.StorageTask, com.google.firebase.storage.ControllableTask
    public boolean pause() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.exception, this.resultCode), this.bytesDownloadedSnapped);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.storage.StorageTask
    public void onCanceled() {
        this.sender.cancel();
        this.exception = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void onProgress() {
        this.bytesDownloadedSnapped = this.bytesDownloaded;
    }

    /* loaded from: classes4.dex */
    static class StreamProgressWrapper extends InputStream {
        private long mDownloadedBytes;
        private Callable<InputStream> mInputStreamCallable;
        private long mLastExceptionPosition;
        private StreamDownloadTask mParentTask;
        private boolean mStreamClosed;
        private IOException mTemporaryException;
        private InputStream mWrappedStream;

        @Override // java.io.InputStream
        public void mark(int i) {
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return false;
        }

        StreamProgressWrapper(Callable<InputStream> callable, StreamDownloadTask streamDownloadTask) {
            this.mParentTask = streamDownloadTask;
            this.mInputStreamCallable = callable;
        }

        private void checkCancel() throws IOException {
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null && streamDownloadTask.getInternalState() == 32) {
                throw new CancelException();
            }
        }

        private void recordDownloadedBytes(long j) {
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null) {
                streamDownloadTask.recordDownloadedBytes(j);
            }
            this.mDownloadedBytes += j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean ensureStream() throws IOException {
            checkCancel();
            if (this.mTemporaryException != null) {
                try {
                    InputStream inputStream = this.mWrappedStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException unused) {
                }
                this.mWrappedStream = null;
                if (this.mLastExceptionPosition == this.mDownloadedBytes) {
                    Log.i(StreamDownloadTask.TAG, "Encountered exception during stream operation. Aborting.", this.mTemporaryException);
                    return false;
                }
                Log.i(StreamDownloadTask.TAG, "Encountered exception during stream operation. Retrying at " + this.mDownloadedBytes, this.mTemporaryException);
                this.mLastExceptionPosition = this.mDownloadedBytes;
                this.mTemporaryException = null;
            }
            if (this.mStreamClosed) {
                throw new IOException("Can't perform operation on closed stream");
            }
            if (this.mWrappedStream != null) {
                return true;
            }
            try {
                this.mWrappedStream = this.mInputStreamCallable.call();
                return true;
            } catch (Exception e) {
                if (e instanceof IOException) {
                    throw ((IOException) e);
                }
                throw new IOException("Unable to open stream", e);
            }
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            while (ensureStream()) {
                try {
                    int read = this.mWrappedStream.read();
                    if (read != -1) {
                        recordDownloadedBytes(1L);
                    }
                    return read;
                } catch (IOException e) {
                    this.mTemporaryException = e;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            while (ensureStream()) {
                try {
                    return this.mWrappedStream.available();
                } catch (IOException e) {
                    this.mTemporaryException = e;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            InputStream inputStream = this.mWrappedStream;
            if (inputStream != null) {
                inputStream.close();
            }
            this.mStreamClosed = true;
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null && streamDownloadTask.request != null) {
                this.mParentTask.request.performRequestEnd();
                this.mParentTask.request = null;
            }
            checkCancel();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (ensureStream()) {
                while (i2 > 262144) {
                    try {
                        int read = this.mWrappedStream.read(bArr, i, 262144);
                        if (read == -1) {
                            if (i3 == 0) {
                                return -1;
                            }
                            return i3;
                        }
                        i3 += read;
                        i += read;
                        i2 -= read;
                        recordDownloadedBytes(read);
                        checkCancel();
                    } catch (IOException e) {
                        this.mTemporaryException = e;
                    }
                }
                if (i2 > 0) {
                    int read2 = this.mWrappedStream.read(bArr, i, i2);
                    if (read2 == -1) {
                        if (i3 == 0) {
                            return -1;
                        }
                        return i3;
                    }
                    i += read2;
                    i3 += read2;
                    i2 -= read2;
                    recordDownloadedBytes(read2);
                }
                if (i2 == 0) {
                    return i3;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = 0;
            while (ensureStream()) {
                while (j > 262144) {
                    try {
                        long skip = this.mWrappedStream.skip(262144L);
                        if (skip < 0) {
                            if (j2 == 0) {
                                return -1L;
                            }
                            return j2;
                        }
                        j2 += skip;
                        j -= skip;
                        recordDownloadedBytes(skip);
                        checkCancel();
                    } catch (IOException e) {
                        this.mTemporaryException = e;
                    }
                }
                if (j > 0) {
                    long skip2 = this.mWrappedStream.skip(j);
                    if (skip2 < 0) {
                        if (j2 == 0) {
                            return -1L;
                        }
                        return j2;
                    }
                    j2 += skip2;
                    j -= skip2;
                    recordDownloadedBytes(skip2);
                }
                if (j == 0) {
                    return j2;
                }
            }
            throw this.mTemporaryException;
        }
    }

    /* loaded from: classes4.dex */
    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesDownloaded;

        TaskSnapshot(Exception exc, long j) {
            super(exc);
            this.mBytesDownloaded = j;
        }

        public long getBytesTransferred() {
            return this.mBytesDownloaded;
        }

        public long getTotalByteCount() {
            return StreamDownloadTask.this.getTotalBytes();
        }

        public InputStream getStream() {
            return StreamDownloadTask.this.inputStream;
        }
    }
}
