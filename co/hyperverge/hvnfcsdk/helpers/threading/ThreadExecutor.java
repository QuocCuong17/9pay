package co.hyperverge.hvnfcsdk.helpers.threading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ThreadExecutor {
    private static final int CORE_POOL_SIZE = 3;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final int MAX_POOL_SIZE = 5;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue();
    private static volatile ThreadExecutor threadExecutor;
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 120, TIME_UNIT, WORK_QUEUE);

    private ThreadExecutor() {
    }

    public static synchronized ThreadExecutor getInstance() {
        ThreadExecutor threadExecutor2;
        synchronized (ThreadExecutor.class) {
            if (threadExecutor == null) {
                threadExecutor = new ThreadExecutor();
            }
            threadExecutor2 = threadExecutor;
        }
        return threadExecutor2;
    }

    public Future<?> execute(Runnable runnable) {
        return this.threadPoolExecutor.submit(runnable);
    }
}
