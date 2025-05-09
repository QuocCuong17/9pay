package com.google.firebase.storage;

import com.google.firebase.concurrent.FirebaseExecutors;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class StorageTaskScheduler {
    private static Executor CALLBACK_QUEUE_EXECUTOR = null;
    private static Executor COMMAND_POOL_EXECUTOR = null;
    private static final int COMMAND_POOL_SIZE = 5;
    private static final int DOWNLOAD_POOL_SIZE = 3;
    private static Executor DOWNLOAD_QUEUE_EXECUTOR = null;
    private static Executor MAIN_THREAD_EXECUTOR = null;
    private static final int UPLOAD_POOL_SIZE = 2;
    private static Executor UPLOAD_QUEUE_EXECUTOR;
    public static StorageTaskScheduler sInstance = new StorageTaskScheduler();

    public static void initializeExecutors(Executor executor, Executor executor2) {
        COMMAND_POOL_EXECUTOR = FirebaseExecutors.newLimitedConcurrencyExecutor(executor, 5);
        DOWNLOAD_QUEUE_EXECUTOR = FirebaseExecutors.newLimitedConcurrencyExecutor(executor, 3);
        UPLOAD_QUEUE_EXECUTOR = FirebaseExecutors.newLimitedConcurrencyExecutor(executor, 2);
        CALLBACK_QUEUE_EXECUTOR = FirebaseExecutors.newSequentialExecutor(executor);
        MAIN_THREAD_EXECUTOR = executor2;
    }

    public static StorageTaskScheduler getInstance() {
        return sInstance;
    }

    public void scheduleCommand(Runnable runnable) {
        COMMAND_POOL_EXECUTOR.execute(runnable);
    }

    public void scheduleUpload(Runnable runnable) {
        UPLOAD_QUEUE_EXECUTOR.execute(runnable);
    }

    public Executor getMainThreadExecutor() {
        return MAIN_THREAD_EXECUTOR;
    }

    public void scheduleDownload(Runnable runnable) {
        DOWNLOAD_QUEUE_EXECUTOR.execute(runnable);
    }

    public void scheduleCallback(Runnable runnable) {
        CALLBACK_QUEUE_EXECUTOR.execute(runnable);
    }

    public Executor getCommandPoolExecutor() {
        return COMMAND_POOL_EXECUTOR;
    }
}
