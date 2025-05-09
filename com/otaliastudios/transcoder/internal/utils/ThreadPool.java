package com.otaliastudios.transcoder.internal.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThreadPool.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/utils/ThreadPool;", "", "()V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "getExecutor$annotations", "getExecutor", "()Ljava/util/concurrent/ThreadPoolExecutor;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ThreadPool {
    public static final ThreadPool INSTANCE = new ThreadPool();
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1, Runtime.getRuntime().availableProcessors() + 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.otaliastudios.transcoder.internal.utils.ThreadPool$executor$1
        private final AtomicInteger count = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            return new Thread(r, Intrinsics.stringPlus("TranscoderThread #", Integer.valueOf(this.count.getAndIncrement())));
        }
    });

    @JvmStatic
    public static /* synthetic */ void getExecutor$annotations() {
    }

    private ThreadPool() {
    }

    public static final ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
