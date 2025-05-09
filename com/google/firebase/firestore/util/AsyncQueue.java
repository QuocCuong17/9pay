package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.AsyncQueue;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;

/* loaded from: classes4.dex */
public class AsyncQueue {
    private final ArrayList<TimerId> timerIdsToSkip = new ArrayList<>();
    private final ArrayList<DelayedTask> delayedTasks = new ArrayList<>();
    private final SynchronizedShutdownAwareExecutor executor = new SynchronizedShutdownAwareExecutor();

    /* loaded from: classes4.dex */
    public enum TimerId {
        ALL,
        LISTEN_STREAM_IDLE,
        LISTEN_STREAM_CONNECTION_BACKOFF,
        WRITE_STREAM_IDLE,
        WRITE_STREAM_CONNECTION_BACKOFF,
        HEALTH_CHECK_TIMEOUT,
        ONLINE_STATE_TIMEOUT,
        GARBAGE_COLLECTION,
        RETRY_TRANSACTION,
        CONNECTIVITY_ATTEMPT_TIMER,
        INDEX_BACKFILL
    }

    /* loaded from: classes4.dex */
    public class DelayedTask {
        private ScheduledFuture scheduledFuture;
        private final long targetTimeMs;
        private final Runnable task;
        private final TimerId timerId;

        private DelayedTask(TimerId timerId, long j, Runnable runnable) {
            this.timerId = timerId;
            this.targetTimeMs = j;
            this.task = runnable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void start(long j) {
            this.scheduledFuture = AsyncQueue.this.executor.schedule(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$DelayedTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AsyncQueue.DelayedTask.this.handleDelayElapsed();
                }
            }, j, TimeUnit.MILLISECONDS);
        }

        void skipDelay() {
            handleDelayElapsed();
        }

        public void cancel() {
            AsyncQueue.this.verifyIsCurrentThread();
            ScheduledFuture scheduledFuture = this.scheduledFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                markDone();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleDelayElapsed() {
            AsyncQueue.this.verifyIsCurrentThread();
            if (this.scheduledFuture != null) {
                markDone();
                this.task.run();
            }
        }

        private void markDone() {
            Assert.hardAssert(this.scheduledFuture != null, "Caller should have verified scheduledFuture is non-null.", new Object[0]);
            this.scheduledFuture = null;
            AsyncQueue.this.removeDelayedTask(this);
        }
    }

    public static <TResult> Task<TResult> callTask(final Executor executor, final Callable<Task<TResult>> callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.lambda$callTask$1(callable, executor, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$callTask$1(Callable callable, Executor executor, final TaskCompletionSource taskCompletionSource) {
        try {
            ((Task) callable.call()).continueWith(executor, new Continuation() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return AsyncQueue.lambda$callTask$0(TaskCompletionSource.this, task);
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        } catch (Throwable th) {
            taskCompletionSource.setException(new IllegalStateException("Unhandled throwable in callTask.", th));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$callTask$0(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(task.getResult());
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class SynchronizedShutdownAwareExecutor implements Executor {
        private final ScheduledThreadPoolExecutor internalExecutor;
        private boolean isShuttingDown;
        private final Thread thread;

        /* loaded from: classes4.dex */
        private class DelayedStartFactory implements Runnable, ThreadFactory {
            private Runnable delegate;
            private final CountDownLatch latch;

            private DelayedStartFactory() {
                this.latch = new CountDownLatch(1);
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.latch.await();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                this.delegate.run();
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Assert.hardAssert(this.delegate == null, "Only one thread may be created in an AsyncQueue.", new Object[0]);
                this.delegate = runnable;
                this.latch.countDown();
                return SynchronizedShutdownAwareExecutor.this.thread;
            }
        }

        SynchronizedShutdownAwareExecutor() {
            DelayedStartFactory delayedStartFactory = new DelayedStartFactory();
            Thread newThread = java.util.concurrent.Executors.defaultThreadFactory().newThread(delayedStartFactory);
            this.thread = newThread;
            newThread.setName("FirestoreWorker");
            newThread.setDaemon(true);
            newThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    AsyncQueue.SynchronizedShutdownAwareExecutor.this.m879x23fada6e(thread, th);
                }
            });
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, delayedStartFactory) { // from class: com.google.firebase.firestore.util.AsyncQueue.SynchronizedShutdownAwareExecutor.1
                @Override // java.util.concurrent.ThreadPoolExecutor
                protected void afterExecute(Runnable runnable, Throwable th) {
                    super.afterExecute(runnable, th);
                    if (th == null && (runnable instanceof Future)) {
                        Future future = (Future) runnable;
                        try {
                            if (future.isDone()) {
                                future.get();
                            }
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        } catch (CancellationException unused2) {
                        } catch (ExecutionException e) {
                            th = e.getCause();
                        }
                    }
                    if (th != null) {
                        AsyncQueue.this.panic(th);
                    }
                }
            };
            this.internalExecutor = scheduledThreadPoolExecutor;
            scheduledThreadPoolExecutor.setKeepAliveTime(3L, TimeUnit.SECONDS);
            this.isShuttingDown = false;
        }

        /* renamed from: lambda$new$0$com-google-firebase-firestore-util-AsyncQueue$SynchronizedShutdownAwareExecutor, reason: not valid java name */
        public /* synthetic */ void m879x23fada6e(Thread thread, Throwable th) {
            AsyncQueue.this.panic(th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean isShuttingDown() {
            return this.isShuttingDown;
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(Runnable runnable) {
            if (!this.isShuttingDown) {
                this.internalExecutor.execute(runnable);
            }
        }

        public void executeEvenAfterShutdown(Runnable runnable) {
            try {
                this.internalExecutor.execute(runnable);
            } catch (RejectedExecutionException unused) {
                Logger.warn("AsyncQueue", "Refused to enqueue task after panic", new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> Task<T> executeAndReportResult(final Callable<T> callable) {
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            try {
                execute(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AsyncQueue.SynchronizedShutdownAwareExecutor.lambda$executeAndReportResult$1(TaskCompletionSource.this, callable);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Logger.warn("AsyncQueue", "Refused to enqueue task after panic", new Object[0]);
            }
            return taskCompletionSource.getTask();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$executeAndReportResult$1(TaskCompletionSource taskCompletionSource, Callable callable) {
            try {
                taskCompletionSource.setResult(callable.call());
            } catch (Exception e) {
                taskCompletionSource.setException(e);
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized Task<Void> executeAndInitiateShutdown(final Runnable runnable) {
            if (isShuttingDown()) {
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.setResult(null);
                return taskCompletionSource.getTask();
            }
            Task<Void> executeAndReportResult = executeAndReportResult(new Callable() { // from class: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return AsyncQueue.SynchronizedShutdownAwareExecutor.lambda$executeAndInitiateShutdown$2(runnable);
                }
            });
            this.isShuttingDown = true;
            return executeAndReportResult;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Void lambda$executeAndInitiateShutdown$2(Runnable runnable) throws Exception {
            runnable.run();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.isShuttingDown) {
                return null;
            }
            return this.internalExecutor.schedule(runnable, j, timeUnit);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void shutdownNow() {
            this.internalExecutor.shutdownNow();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCorePoolSize(int i) {
            this.internalExecutor.setCorePoolSize(i);
        }
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void verifyIsCurrentThread() {
        Thread currentThread = Thread.currentThread();
        if (this.executor.thread != currentThread) {
            throw Assert.fail("We are running on the wrong thread. Expected to be on the AsyncQueue thread %s/%d but was %s/%d", this.executor.thread.getName(), Long.valueOf(this.executor.thread.getId()), currentThread.getName(), Long.valueOf(currentThread.getId()));
        }
    }

    @CheckReturnValue
    public <T> Task<T> enqueue(Callable<T> callable) {
        return this.executor.executeAndReportResult(callable);
    }

    @CheckReturnValue
    public Task<Void> enqueue(final Runnable runnable) {
        return enqueue(new Callable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AsyncQueue.lambda$enqueue$2(runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$enqueue$2(Runnable runnable) throws Exception {
        runnable.run();
        return null;
    }

    public Task<Void> enqueueAndInitiateShutdown(Runnable runnable) {
        return this.executor.executeAndInitiateShutdown(runnable);
    }

    public void enqueueAndForgetEvenAfterShutdown(Runnable runnable) {
        this.executor.executeEvenAfterShutdown(runnable);
    }

    public boolean isShuttingDown() {
        return this.executor.isShuttingDown();
    }

    public void enqueueAndForget(Runnable runnable) {
        enqueue(runnable);
    }

    public DelayedTask enqueueAfterDelay(TimerId timerId, long j, Runnable runnable) {
        if (this.timerIdsToSkip.contains(timerId)) {
            j = 0;
        }
        DelayedTask createAndScheduleDelayedTask = createAndScheduleDelayedTask(timerId, j, runnable);
        this.delayedTasks.add(createAndScheduleDelayedTask);
        return createAndScheduleDelayedTask;
    }

    public void skipDelaysForTimerId(TimerId timerId) {
        this.timerIdsToSkip.add(timerId);
    }

    public void panic(final Throwable th) {
        this.executor.shutdownNow();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.lambda$panic$3(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$panic$3(Throwable th) {
        if (th instanceof OutOfMemoryError) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Firestore (24.8.1) ran out of memory. Check your queries to make sure they are not loading an excessive amount of data.");
            outOfMemoryError.initCause(th);
            throw outOfMemoryError;
        }
        throw new RuntimeException("Internal error in Cloud Firestore (24.8.1).", th);
    }

    public void runSync(final Runnable runnable) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(0);
        final Throwable[] thArr = new Throwable[1];
        enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.lambda$runSync$4(runnable, thArr, semaphore);
            }
        });
        semaphore.acquire(1);
        if (thArr[0] != null) {
            throw new RuntimeException("Synchronous task failed", thArr[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$runSync$4(Runnable runnable, Throwable[] thArr, Semaphore semaphore) {
        try {
            runnable.run();
        } catch (Throwable th) {
            thArr[0] = th;
        }
        semaphore.release();
    }

    public boolean containsDelayedTask(TimerId timerId) {
        Iterator<DelayedTask> it = this.delayedTasks.iterator();
        while (it.hasNext()) {
            if (it.next().timerId == timerId) {
                return true;
            }
        }
        return false;
    }

    public void runDelayedTasksUntil(final TimerId timerId) throws InterruptedException {
        runSync(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.this.m878xc3727432(timerId);
            }
        });
    }

    /* renamed from: lambda$runDelayedTasksUntil$6$com-google-firebase-firestore-util-AsyncQueue, reason: not valid java name */
    public /* synthetic */ void m878xc3727432(TimerId timerId) {
        Assert.hardAssert(timerId == TimerId.ALL || containsDelayedTask(timerId), "Attempted to run tasks until missing TimerId: %s", timerId);
        Collections.sort(this.delayedTasks, new Comparator() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda5
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((AsyncQueue.DelayedTask) obj).targetTimeMs, ((AsyncQueue.DelayedTask) obj2).targetTimeMs);
                return compare;
            }
        });
        Iterator it = new ArrayList(this.delayedTasks).iterator();
        while (it.hasNext()) {
            DelayedTask delayedTask = (DelayedTask) it.next();
            delayedTask.skipDelay();
            if (timerId != TimerId.ALL && delayedTask.timerId == timerId) {
                return;
            }
        }
    }

    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    private DelayedTask createAndScheduleDelayedTask(TimerId timerId, long j, Runnable runnable) {
        DelayedTask delayedTask = new DelayedTask(timerId, System.currentTimeMillis() + j, runnable);
        delayedTask.start(j);
        return delayedTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDelayedTask(DelayedTask delayedTask) {
        Assert.hardAssert(this.delayedTasks.remove(delayedTask), "Delayed task not found.", new Object[0]);
    }
}
