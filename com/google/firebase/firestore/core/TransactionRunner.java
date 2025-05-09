package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Function;

/* loaded from: classes4.dex */
public class TransactionRunner<TResult> {
    private AsyncQueue asyncQueue;
    private int attemptsRemaining;
    private ExponentialBackoff backoff;
    private RemoteStore remoteStore;
    private TaskCompletionSource<TResult> taskSource = new TaskCompletionSource<>();
    private Function<Transaction, Task<TResult>> updateFunction;

    public TransactionRunner(AsyncQueue asyncQueue, RemoteStore remoteStore, TransactionOptions transactionOptions, Function<Transaction, Task<TResult>> function) {
        this.asyncQueue = asyncQueue;
        this.remoteStore = remoteStore;
        this.updateFunction = function;
        this.attemptsRemaining = transactionOptions.getMaxAttempts();
        this.backoff = new ExponentialBackoff(asyncQueue, AsyncQueue.TimerId.RETRY_TRANSACTION);
    }

    public Task<TResult> run() {
        runWithBackoff();
        return this.taskSource.getTask();
    }

    private void runWithBackoff() {
        this.attemptsRemaining--;
        this.backoff.backoffAndRun(new Runnable() { // from class: com.google.firebase.firestore.core.TransactionRunner$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TransactionRunner.this.m790xd5705922();
            }
        });
    }

    /* renamed from: lambda$runWithBackoff$2$com-google-firebase-firestore-core-TransactionRunner, reason: not valid java name */
    public /* synthetic */ void m790xd5705922() {
        final Transaction createTransaction = this.remoteStore.createTransaction();
        this.updateFunction.apply(createTransaction).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.core.TransactionRunner$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TransactionRunner.this.m789xbb54da83(createTransaction, task);
            }
        });
    }

    /* renamed from: lambda$runWithBackoff$1$com-google-firebase-firestore-core-TransactionRunner, reason: not valid java name */
    public /* synthetic */ void m789xbb54da83(Transaction transaction, final Task task) {
        if (!task.isSuccessful()) {
            handleTransactionError(task);
        } else {
            transaction.commit().addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.core.TransactionRunner$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    TransactionRunner.this.m788xa1395be4(task, task2);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$runWithBackoff$0$com-google-firebase-firestore-core-TransactionRunner, reason: not valid java name */
    public /* synthetic */ void m788xa1395be4(Task task, Task task2) {
        if (task2.isSuccessful()) {
            this.taskSource.setResult(task.getResult());
        } else {
            handleTransactionError(task2);
        }
    }

    private void handleTransactionError(Task task) {
        if (this.attemptsRemaining > 0 && isRetryableTransactionError(task.getException())) {
            runWithBackoff();
        } else {
            this.taskSource.setException(task.getException());
        }
    }

    private static boolean isRetryableTransactionError(Exception exc) {
        if (!(exc instanceof FirebaseFirestoreException)) {
            return false;
        }
        FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc;
        FirebaseFirestoreException.Code code = firebaseFirestoreException.getCode();
        return code == FirebaseFirestoreException.Code.ABORTED || code == FirebaseFirestoreException.Code.ALREADY_EXISTS || code == FirebaseFirestoreException.Code.FAILED_PRECONDITION || !Datastore.isPermanentError(firebaseFirestoreException.getCode());
    }
}
