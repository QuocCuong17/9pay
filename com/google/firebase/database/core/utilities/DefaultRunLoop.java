package com.google.firebase.database.core.utilities;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.RunLoop;
import com.google.firebase.database.core.ThreadInitializer;
import java.lang.Thread;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public abstract class DefaultRunLoop implements RunLoop {
    private ScheduledThreadPoolExecutor executor;

    public abstract void handleException(Throwable th);

    /* loaded from: classes4.dex */
    private class FirebaseThreadFactory implements ThreadFactory {
        private FirebaseThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = DefaultRunLoop.this.getThreadFactory().newThread(runnable);
            ThreadInitializer threadInitializer = DefaultRunLoop.this.getThreadInitializer();
            threadInitializer.setName(newThread, "FirebaseDatabaseWorker");
            threadInitializer.setDaemon(newThread, true);
            threadInitializer.setUncaughtExceptionHandler(newThread, new Thread.UncaughtExceptionHandler() { // from class: com.google.firebase.database.core.utilities.DefaultRunLoop.FirebaseThreadFactory.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    DefaultRunLoop.this.handleException(th);
                }
            });
            return newThread;
        }
    }

    protected ThreadFactory getThreadFactory() {
        return Executors.defaultThreadFactory();
    }

    protected ThreadInitializer getThreadInitializer() {
        return ThreadInitializer.defaultInstance;
    }

    public DefaultRunLoop() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new FirebaseThreadFactory()) { // from class: com.google.firebase.database.core.utilities.DefaultRunLoop.1
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
                    DefaultRunLoop.this.handleException(th);
                }
            }
        };
        this.executor = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setKeepAliveTime(3L, TimeUnit.SECONDS);
    }

    public ScheduledExecutorService getExecutorService() {
        return this.executor;
    }

    @Override // com.google.firebase.database.core.RunLoop
    public void scheduleNow(Runnable runnable) {
        this.executor.execute(runnable);
    }

    @Override // com.google.firebase.database.core.RunLoop
    public ScheduledFuture schedule(Runnable runnable, long j) {
        return this.executor.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.google.firebase.database.core.RunLoop
    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    @Override // com.google.firebase.database.core.RunLoop
    public void restart() {
        this.executor.setCorePoolSize(1);
    }

    public static String messageForException(Throwable th) {
        if (th instanceof OutOfMemoryError) {
            return "Firebase Database encountered an OutOfMemoryError. You may need to reduce the amount of data you are syncing to the client (e.g. by using queries or syncing a deeper path). See https://firebase.google.com/docs/database/ios/structure-data#best_practices_for_data_structure and https://firebase.google.com/docs/database/android/retrieve-data#filtering_data";
        }
        if (th instanceof NoClassDefFoundError) {
            return "A symbol that the Firebase Database SDK depends on failed to load. This usually indicates that your project includes an incompatible version of another Firebase dependency. If updating your dependencies to the latest version does not resolve this issue, please file a report at https://github.com/firebase/firebase-android-sdk";
        }
        if (th instanceof DatabaseException) {
            return "";
        }
        return "Uncaught exception in Firebase Database runloop (" + FirebaseDatabase.getSdkVersion() + "). If you are not already on the latest version of the Firebase SDKs, try updating your dependencies. Should this problem persist, please file a report at https://github.com/firebase/firebase-android-sdk";
    }
}
