package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public final class SequentialExecutor implements Executor {
    private static final LazyLogger log = new LazyLogger(SequentialExecutor.class);
    private final Executor executor;
    private final Deque<Runnable> queue = new ArrayDeque();
    private WorkerRunningState workerRunningState = WorkerRunningState.IDLE;
    private long workerRunCount = 0;
    private final QueueWorker worker = new QueueWorker();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    static /* synthetic */ long access$308(SequentialExecutor sequentialExecutor) {
        long j = sequentialExecutor.workerRunCount;
        sequentialExecutor.workerRunCount = 1 + j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SequentialExecutor(Executor executor) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable task) {
        Preconditions.checkNotNull(task);
        synchronized (this.queue) {
            if (this.workerRunningState != WorkerRunningState.RUNNING && this.workerRunningState != WorkerRunningState.QUEUED) {
                long j = this.workerRunCount;
                Runnable runnable = new Runnable(this) { // from class: com.google.common.util.concurrent.SequentialExecutor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        task.run();
                    }

                    public String toString() {
                        return task.toString();
                    }
                };
                this.queue.add(runnable);
                this.workerRunningState = WorkerRunningState.QUEUING;
                try {
                    this.executor.execute(this.worker);
                    if (this.workerRunningState != WorkerRunningState.QUEUING) {
                        return;
                    }
                    synchronized (this.queue) {
                        if (this.workerRunCount == j && this.workerRunningState == WorkerRunningState.QUEUING) {
                            this.workerRunningState = WorkerRunningState.QUEUED;
                        }
                    }
                    return;
                } catch (Throwable th) {
                    synchronized (this.queue) {
                        if ((this.workerRunningState != WorkerRunningState.IDLE && this.workerRunningState != WorkerRunningState.QUEUING) || !this.queue.removeLastOccurrence(runnable)) {
                            r8 = false;
                        }
                        if (!(th instanceof RejectedExecutionException) || r8) {
                            throw th;
                        }
                        return;
                    }
                }
            }
            this.queue.add(task);
        }
    }

    /* loaded from: classes4.dex */
    private final class QueueWorker implements Runnable {

        @CheckForNull
        Runnable task;

        private QueueWorker() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.queue) {
                    SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0054, code lost:
        
            r1 = r1 | java.lang.Thread.interrupted();
            r2 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0056, code lost:
        
            r8.task.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0060, code lost:
        
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0061, code lost:
        
            com.google.common.util.concurrent.SequentialExecutor.log.get().log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + r8.task, (java.lang.Throwable) r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0082, code lost:
        
            r8.task = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0084, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x004e, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void workOnQueue() {
            boolean z = false;
            boolean z2 = false;
            while (true) {
                try {
                    synchronized (SequentialExecutor.this.queue) {
                        if (!z) {
                            if (SequentialExecutor.this.workerRunningState != WorkerRunningState.RUNNING) {
                                SequentialExecutor.access$308(SequentialExecutor.this);
                                SequentialExecutor.this.workerRunningState = WorkerRunningState.RUNNING;
                                z = true;
                            }
                        }
                        Runnable runnable = (Runnable) SequentialExecutor.this.queue.poll();
                        this.task = runnable;
                        if (runnable == null) {
                            SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                        }
                    }
                    if (z2) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                } finally {
                    if (z2) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public String toString() {
            Runnable runnable = this.task;
            if (runnable != null) {
                return "SequentialExecutorWorker{running=" + runnable + "}";
            }
            return "SequentialExecutorWorker{state=" + SequentialExecutor.this.workerRunningState + "}";
        }
    }

    public String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.executor + "}";
    }
}
