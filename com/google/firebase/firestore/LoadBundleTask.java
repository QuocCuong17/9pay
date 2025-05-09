package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class LoadBundleTask extends Task<LoadBundleTaskProgress> {
    private final TaskCompletionSource<LoadBundleTaskProgress> completionSource;
    private final Task<LoadBundleTaskProgress> delegate;
    private final Queue<ManagedListener> progressListeners;
    private final Object lock = new Object();
    private LoadBundleTaskProgress snapshot = LoadBundleTaskProgress.INITIAL;

    public LoadBundleTask() {
        TaskCompletionSource<LoadBundleTaskProgress> taskCompletionSource = new TaskCompletionSource<>();
        this.completionSource = taskCompletionSource;
        this.delegate = taskCompletionSource.getTask();
        this.progressListeners = new ArrayDeque();
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isComplete() {
        return this.delegate.isComplete();
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isSuccessful() {
        return this.delegate.isSuccessful();
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isCanceled() {
        return this.delegate.isCanceled();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.tasks.Task
    public LoadBundleTaskProgress getResult() {
        return this.delegate.getResult();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.tasks.Task
    public <X extends Throwable> LoadBundleTaskProgress getResult(Class<X> cls) throws Throwable {
        return this.delegate.getResult(cls);
    }

    @Override // com.google.android.gms.tasks.Task
    public Exception getException() {
        return this.delegate.getException();
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnSuccessListener(OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(onSuccessListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnSuccessListener(Executor executor, OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(executor, onSuccessListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnSuccessListener(Activity activity, OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(activity, onSuccessListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnFailureListener(OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(onFailureListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(executor, onFailureListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(activity, onFailureListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnCompleteListener(OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnCompleteListener(Executor executor, OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(executor, onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnCompleteListener(Activity activity, OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(activity, onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(onCanceledListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(executor, onCanceledListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task<LoadBundleTaskProgress> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(activity, onCanceledListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<LoadBundleTaskProgress, TContinuationResult> continuation) {
        return this.delegate.continueWith(continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<LoadBundleTaskProgress, TContinuationResult> continuation) {
        return this.delegate.continueWith(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<LoadBundleTaskProgress, Task<TContinuationResult>> continuation) {
        return this.delegate.continueWithTask(continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<LoadBundleTaskProgress, Task<TContinuationResult>> continuation) {
        return this.delegate.continueWithTask(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<LoadBundleTaskProgress, TContinuationResult> successContinuation) {
        return this.delegate.onSuccessTask(successContinuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<LoadBundleTaskProgress, TContinuationResult> successContinuation) {
        return this.delegate.onSuccessTask(executor, successContinuation);
    }

    public LoadBundleTask addOnProgressListener(OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        ManagedListener managedListener = new ManagedListener(null, onProgressListener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        return this;
    }

    public LoadBundleTask addOnProgressListener(Executor executor, OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        ManagedListener managedListener = new ManagedListener(executor, onProgressListener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        return this;
    }

    public LoadBundleTask addOnProgressListener(Activity activity, final OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        ManagedListener managedListener = new ManagedListener(null, onProgressListener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        ActivityLifecycleObserver.of(activity).onStopCallOnce(new Runnable() { // from class: com.google.firebase.firestore.LoadBundleTask$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LoadBundleTask.this.m755x9c983ee1(onProgressListener);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: removeOnProgressListener, reason: merged with bridge method [inline-methods] */
    public void m755x9c983ee1(OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
        synchronized (this.lock) {
            this.progressListeners.remove(new ManagedListener(null, onProgressListener));
        }
    }

    public void setResult(LoadBundleTaskProgress loadBundleTaskProgress) {
        Assert.hardAssert(loadBundleTaskProgress.getTaskState().equals(LoadBundleTaskProgress.TaskState.SUCCESS), "Expected success, but was " + loadBundleTaskProgress.getTaskState(), new Object[0]);
        synchronized (this.lock) {
            this.snapshot = loadBundleTaskProgress;
            Iterator<ManagedListener> it = this.progressListeners.iterator();
            while (it.hasNext()) {
                it.next().invokeAsync(this.snapshot);
            }
            this.progressListeners.clear();
        }
        this.completionSource.setResult(loadBundleTaskProgress);
    }

    public void setException(Exception exc) {
        synchronized (this.lock) {
            LoadBundleTaskProgress loadBundleTaskProgress = new LoadBundleTaskProgress(this.snapshot.getDocumentsLoaded(), this.snapshot.getTotalDocuments(), this.snapshot.getBytesLoaded(), this.snapshot.getTotalBytes(), exc, LoadBundleTaskProgress.TaskState.ERROR);
            this.snapshot = loadBundleTaskProgress;
            Iterator<ManagedListener> it = this.progressListeners.iterator();
            while (it.hasNext()) {
                it.next().invokeAsync(loadBundleTaskProgress);
            }
            this.progressListeners.clear();
        }
        this.completionSource.setException(exc);
    }

    public void updateProgress(LoadBundleTaskProgress loadBundleTaskProgress) {
        synchronized (this.lock) {
            this.snapshot = loadBundleTaskProgress;
            Iterator<ManagedListener> it = this.progressListeners.iterator();
            while (it.hasNext()) {
                it.next().invokeAsync(loadBundleTaskProgress);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class ManagedListener {
        Executor executor;
        OnProgressListener<LoadBundleTaskProgress> listener;

        ManagedListener(Executor executor, OnProgressListener<LoadBundleTaskProgress> onProgressListener) {
            this.executor = executor == null ? TaskExecutors.MAIN_THREAD : executor;
            this.listener = onProgressListener;
        }

        public void invokeAsync(final LoadBundleTaskProgress loadBundleTaskProgress) {
            this.executor.execute(new Runnable() { // from class: com.google.firebase.firestore.LoadBundleTask$ManagedListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LoadBundleTask.ManagedListener.this.m756xdd93410d(loadBundleTaskProgress);
                }
            });
        }

        /* renamed from: lambda$invokeAsync$0$com-google-firebase-firestore-LoadBundleTask$ManagedListener, reason: not valid java name */
        public /* synthetic */ void m756xdd93410d(LoadBundleTaskProgress loadBundleTaskProgress) {
            this.listener.onProgress(loadBundleTaskProgress);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.listener.equals(((ManagedListener) obj).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }
    }
}
