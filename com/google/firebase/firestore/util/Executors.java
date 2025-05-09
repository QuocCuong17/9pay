package com.google.firebase.firestore.util;

import android.os.AsyncTask;
import androidx.media3.exoplayer.dash.offline.DashDownloader$$ExternalSyntheticLambda0;
import com.google.android.gms.tasks.TaskExecutors;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class Executors {
    private static final int ASYNC_THREAD_POOL_MAXIMUM_CONCURRENCY = 4;
    public static final Executor DEFAULT_CALLBACK_EXECUTOR = TaskExecutors.MAIN_THREAD;
    public static final Executor DIRECT_EXECUTOR = DashDownloader$$ExternalSyntheticLambda0.INSTANCE;
    public static final Executor BACKGROUND_EXECUTOR = new ThrottledForwardingExecutor(4, AsyncTask.THREAD_POOL_EXECUTOR);

    private Executors() {
    }
}
