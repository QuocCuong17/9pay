package com.google.firebase.storage;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.TaskListenerImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class StorageTask<ResultT extends ProvideError> extends ControllableTask<ResultT> {
    static final int INTERNAL_STATE_CANCELED = 256;
    static final int INTERNAL_STATE_CANCELING = 32;
    static final int INTERNAL_STATE_FAILURE = 64;
    static final int INTERNAL_STATE_IN_PROGRESS = 4;
    static final int INTERNAL_STATE_NOT_STARTED = 1;
    static final int INTERNAL_STATE_PAUSED = 16;
    static final int INTERNAL_STATE_PAUSING = 8;
    static final int INTERNAL_STATE_QUEUED = 2;
    static final int INTERNAL_STATE_SUCCESS = 128;
    static final int STATES_CANCELED = 256;
    static final int STATES_COMPLETE = 448;
    static final int STATES_FAILURE = 64;
    static final int STATES_INPROGRESS = -465;
    static final int STATES_PAUSED = 16;
    static final int STATES_SUCCESS = 128;
    private static final String TAG = "StorageTask";
    private static final HashMap<Integer, HashSet<Integer>> ValidTaskInitiatedStateChanges;
    private static final HashMap<Integer, HashSet<Integer>> ValidUserInitiatedStateChanges;
    private ResultT finalResult;
    protected final Object syncObject = new Object();
    final TaskListenerImpl<OnSuccessListener<? super ResultT>, ResultT> successManager = new TaskListenerImpl<>(this, 128, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda12
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.m916lambda$new$0$comgooglefirebasestorageStorageTask((OnSuccessListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnFailureListener, ResultT> failureManager = new TaskListenerImpl<>(this, 64, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda11
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.m917lambda$new$1$comgooglefirebasestorageStorageTask((OnFailureListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnCompleteListener<ResultT>, ResultT> completeListener = new TaskListenerImpl<>(this, STATES_COMPLETE, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda10
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.m918lambda$new$2$comgooglefirebasestorageStorageTask((OnCompleteListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnCanceledListener, ResultT> cancelManager = new TaskListenerImpl<>(this, 256, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda9
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.m919lambda$new$3$comgooglefirebasestorageStorageTask((OnCanceledListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnProgressListener<? super ResultT>, ResultT> progressManager = new TaskListenerImpl<>(this, STATES_INPROGRESS, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda2
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            ((OnProgressListener) obj).onProgress((StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnPausedListener<? super ResultT>, ResultT> pausedManager = new TaskListenerImpl<>(this, 16, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda1
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            ((OnPausedListener) obj).onPaused((StorageTask.ProvideError) obj2);
        }
    });
    private volatile int currentState = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public interface ProvideError {
        Exception getError();
    }

    private String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? i != 128 ? i != 256 ? "Unknown Internal State!" : "INTERNAL_STATE_CANCELED" : "INTERNAL_STATE_SUCCESS" : "INTERNAL_STATE_FAILURE" : "INTERNAL_STATE_CANCELING" : "INTERNAL_STATE_PAUSED" : "INTERNAL_STATE_PAUSING" : "INTERNAL_STATE_IN_PROGRESS" : "INTERNAL_STATE_QUEUED" : "INTERNAL_STATE_NOT_STARTED";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract StorageReference getStorage();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCanceled() {
    }

    protected void onFailure() {
    }

    protected void onPaused() {
    }

    protected void onProgress() {
    }

    protected void onQueued() {
    }

    protected void onSuccess() {
    }

    void resetState() {
    }

    abstract void run();

    abstract void schedule();

    abstract ResultT snapStateImpl();

    static {
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
        ValidUserInitiatedStateChanges = hashMap;
        HashMap<Integer, HashSet<Integer>> hashMap2 = new HashMap<>();
        ValidTaskInitiatedStateChanges = hashMap2;
        hashMap.put(1, new HashSet<>(Arrays.asList(16, 256)));
        hashMap.put(2, new HashSet<>(Arrays.asList(8, 32)));
        hashMap.put(4, new HashSet<>(Arrays.asList(8, 32)));
        hashMap.put(16, new HashSet<>(Arrays.asList(2, 256)));
        hashMap.put(64, new HashSet<>(Arrays.asList(2, 256)));
        hashMap2.put(1, new HashSet<>(Arrays.asList(2, 64)));
        hashMap2.put(2, new HashSet<>(Arrays.asList(4, 64, 128)));
        hashMap2.put(4, new HashSet<>(Arrays.asList(4, 64, 128)));
        hashMap2.put(8, new HashSet<>(Arrays.asList(16, 64, 128)));
        hashMap2.put(32, new HashSet<>(Arrays.asList(256, 64, 128)));
    }

    /* renamed from: lambda$new$0$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m916lambda$new$0$comgooglefirebasestorageStorageTask(OnSuccessListener onSuccessListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onSuccessListener.onSuccess(provideError);
    }

    /* renamed from: lambda$new$1$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m917lambda$new$1$comgooglefirebasestorageStorageTask(OnFailureListener onFailureListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onFailureListener.onFailure(provideError.getError());
    }

    /* renamed from: lambda$new$2$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m918lambda$new$2$comgooglefirebasestorageStorageTask(OnCompleteListener onCompleteListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onCompleteListener.onComplete(this);
    }

    /* renamed from: lambda$new$3$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m919lambda$new$3$comgooglefirebasestorageStorageTask(OnCanceledListener onCanceledListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onCanceledListener.onCanceled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean queue() {
        if (!tryChangeState(2, false)) {
            return false;
        }
        schedule();
        return true;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean resume() {
        if (!tryChangeState(2, true)) {
            return false;
        }
        resetState();
        schedule();
        return true;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean pause() {
        return tryChangeState(new int[]{16, 8}, true);
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean cancel() {
        return tryChangeState(new int[]{256, 32}, true);
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isComplete() {
        return (getInternalState() & STATES_COMPLETE) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isSuccessful() {
        return (getInternalState() & 128) != 0;
    }

    @Override // com.google.firebase.storage.CancellableTask, com.google.android.gms.tasks.Task
    public boolean isCanceled() {
        return getInternalState() == 256;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean isInProgress() {
        return (getInternalState() & STATES_INPROGRESS) != 0;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean isPaused() {
        return (getInternalState() & 16) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public ResultT getResult() {
        if (getFinalResult() == null) {
            throw new IllegalStateException();
        }
        Exception error = getFinalResult().getError();
        if (error != null) {
            throw new RuntimeExecutionException(error);
        }
        return getFinalResult();
    }

    @Override // com.google.android.gms.tasks.Task
    public <X extends Throwable> ResultT getResult(Class<X> cls) throws Throwable {
        if (getFinalResult() == null) {
            throw new IllegalStateException();
        }
        if (cls.isInstance(getFinalResult().getError())) {
            throw cls.cast(getFinalResult().getError());
        }
        Exception error = getFinalResult().getError();
        if (error != null) {
            throw new RuntimeExecutionException(error);
        }
        return getFinalResult();
    }

    @Override // com.google.android.gms.tasks.Task
    public Exception getException() {
        if (getFinalResult() == null) {
            return null;
        }
        return getFinalResult().getError();
    }

    public ResultT getSnapshot() {
        return snapState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getInternalState() {
        return this.currentState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getSyncObject() {
        return this.syncObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultT snapState() {
        ResultT snapStateImpl;
        synchronized (this.syncObject) {
            snapStateImpl = snapStateImpl();
        }
        return snapStateImpl;
    }

    boolean tryChangeState(int[] iArr, boolean z) {
        HashMap<Integer, HashSet<Integer>> hashMap = z ? ValidUserInitiatedStateChanges : ValidTaskInitiatedStateChanges;
        synchronized (this.syncObject) {
            for (int i : iArr) {
                HashSet<Integer> hashSet = hashMap.get(Integer.valueOf(getInternalState()));
                if (hashSet != null && hashSet.contains(Integer.valueOf(i))) {
                    this.currentState = i;
                    int i2 = this.currentState;
                    if (i2 == 2) {
                        StorageTaskManager.getInstance().ensureRegistered(this);
                        onQueued();
                    } else if (i2 == 4) {
                        onProgress();
                    } else if (i2 == 16) {
                        onPaused();
                    } else if (i2 == 64) {
                        onFailure();
                    } else if (i2 == 128) {
                        onSuccess();
                    } else if (i2 == 256) {
                        onCanceled();
                    }
                    this.successManager.onInternalStateChanged();
                    this.failureManager.onInternalStateChanged();
                    this.cancelManager.onInternalStateChanged();
                    this.completeListener.onInternalStateChanged();
                    this.pausedManager.onInternalStateChanged();
                    this.progressManager.onInternalStateChanged();
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "changed internal state to: " + getStateString(i) + " isUser: " + z + " from state:" + getStateString(this.currentState));
                    }
                    return true;
                }
            }
            Log.w(TAG, "unable to change internal state to: " + getStateString(iArr) + " isUser: " + z + " from state:" + getStateString(this.currentState));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean tryChangeState(int i, boolean z) {
        return tryChangeState(new int[]{i}, z);
    }

    private ResultT getFinalResult() {
        ResultT resultt = this.finalResult;
        if (resultt != null) {
            return resultt;
        }
        if (!isComplete()) {
            return null;
        }
        if (this.finalResult == null) {
            this.finalResult = snapState();
        }
        return this.finalResult;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.addListener(null, null, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(Executor executor, OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(executor);
        this.pausedManager.addListener(null, executor, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(Activity activity, OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(activity);
        this.pausedManager.addListener(activity, null, onPausedListener);
        return this;
    }

    public StorageTask<ResultT> removeOnPausedListener(OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.m920x5521489(onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.addListener(null, null, onProgressListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(Executor executor, OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(executor);
        this.progressManager.addListener(null, executor, onProgressListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(Activity activity, OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(activity);
        this.progressManager.addListener(activity, null, onProgressListener);
        return this;
    }

    public StorageTask<ResultT> removeOnProgressListener(OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.m920x5521489(onProgressListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(null, null, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(null, executor, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(Activity activity, OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(activity, null, onSuccessListener);
        return this;
    }

    public StorageTask<ResultT> removeOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.m920x5521489(onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.addListener(null, null, onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(executor);
        this.failureManager.addListener(null, executor, onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(activity);
        this.failureManager.addListener(activity, null, onFailureListener);
        return this;
    }

    public StorageTask<ResultT> removeOnFailureListener(OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.m920x5521489(onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.addListener(null, null, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(executor);
        this.completeListener.addListener(null, executor, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(Activity activity, OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(activity);
        this.completeListener.addListener(activity, null, onCompleteListener);
        return this;
    }

    public StorageTask<ResultT> removeOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.m920x5521489(onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.addListener(null, null, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(executor);
        this.cancelManager.addListener(null, executor, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(activity);
        this.cancelManager.addListener(activity, null, onCanceledListener);
        return this;
    }

    public StorageTask<ResultT> removeOnCanceledListener(OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.m920x5521489(onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Executor executor, Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(executor, continuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithImpl(Executor executor, final Continuation<ResultT, ContinuationResultT> continuation) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.completeListener.addListener(null, executor, new OnCompleteListener() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                StorageTask.this.m913xcb6caa6(continuation, taskCompletionSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$continueWithImpl$4$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m913xcb6caa6(Continuation continuation, TaskCompletionSource taskCompletionSource, Task task) {
        try {
            Object then = continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            taskCompletionSource.setResult(then);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Executor executor, Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(null, successContinuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(Executor executor, SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(executor, successContinuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithTaskImpl(Executor executor, final Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.completeListener.addListener(null, executor, new OnCompleteListener() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                StorageTask.this.m914x3ff9518c(continuation, taskCompletionSource, cancellationTokenSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$continueWithTaskImpl$5$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m914x3ff9518c(Continuation continuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, Task task) {
        try {
            Task task2 = (Task) continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            if (task2 == null) {
                taskCompletionSource.setException(new NullPointerException("Continuation returned null"));
                return;
            }
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnSuccessListener(new StorageTask$$ExternalSyntheticLambda8(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnFailureListener(new StorageTask$$ExternalSyntheticLambda6(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            task2.addOnCanceledListener(new StorageTask$$ExternalSyntheticLambda0(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    private <ContinuationResultT> Task<ContinuationResultT> successTaskImpl(Executor executor, final SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.successManager.addListener(null, executor, new OnSuccessListener() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda7
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                StorageTask.lambda$successTaskImpl$6(SuccessContinuation.this, taskCompletionSource, cancellationTokenSource, (StorageTask.ProvideError) obj);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$successTaskImpl$6(SuccessContinuation successContinuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, ProvideError provideError) {
        try {
            Task then = successContinuation.then(provideError);
            Objects.requireNonNull(taskCompletionSource);
            then.addOnSuccessListener(new StorageTask$$ExternalSyntheticLambda8(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            then.addOnFailureListener(new StorageTask$$ExternalSyntheticLambda6(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            then.addOnCanceledListener(new StorageTask$$ExternalSyntheticLambda0(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new Runnable() { // from class: com.google.firebase.storage.StorageTask$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                StorageTask.this.m915lambda$getRunnable$7$comgooglefirebasestorageStorageTask();
            }
        };
    }

    /* renamed from: lambda$getRunnable$7$com-google-firebase-storage-StorageTask, reason: not valid java name */
    public /* synthetic */ void m915lambda$getRunnable$7$comgooglefirebasestorageStorageTask() {
        try {
            run();
        } finally {
            ensureFinalState();
        }
    }

    private void ensureFinalState() {
        if (isComplete() || isPaused() || getInternalState() == 2 || tryChangeState(256, false)) {
            return;
        }
        tryChangeState(64, false);
    }

    private String getStateString(int[] iArr) {
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(getStateString(i));
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    /* loaded from: classes4.dex */
    public class SnapshotBase implements ProvideError {
        private final Exception error;

        public SnapshotBase(Exception exc) {
            if (exc == null) {
                if (StorageTask.this.isCanceled()) {
                    this.error = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
                    return;
                } else if (StorageTask.this.getInternalState() == 64) {
                    this.error = StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR);
                    return;
                } else {
                    this.error = null;
                    return;
                }
            }
            this.error = exc;
        }

        public StorageTask<ResultT> getTask() {
            return StorageTask.this;
        }

        public StorageReference getStorage() {
            return getTask().getStorage();
        }

        @Override // com.google.firebase.storage.StorageTask.ProvideError
        public Exception getError() {
            return this.error;
        }
    }
}
