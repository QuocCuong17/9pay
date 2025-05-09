package androidx.window.java.core;

import androidx.core.util.Consumer;
import io.sentry.protocol.SentryStackFrame;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;

/* compiled from: CallbackToFlowAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J4\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0010J\u0012\u0010\u0011\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005R\u001e\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/java/core/CallbackToFlowAdapter;", "", "()V", "consumerToJobMap", "", "Landroidx/core/util/Consumer;", "Lkotlinx/coroutines/Job;", SentryStackFrame.JsonKeys.LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "connect", "", "T", "executor", "Ljava/util/concurrent/Executor;", "consumer", "flow", "Lkotlinx/coroutines/flow/Flow;", "disconnect", "window-java_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CallbackToFlowAdapter {
    private final ReentrantLock lock = new ReentrantLock();
    private final Map<Consumer<?>, Job> consumerToJobMap = new LinkedHashMap();

    public final <T> void connect(Executor executor, Consumer<T> consumer, Flow<? extends T> flow) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(flow, "flow");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.consumerToJobMap.get(consumer) == null) {
                this.consumerToJobMap.put(consumer, BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executor)), null, null, new CallbackToFlowAdapter$connect$1$1(flow, consumer, null), 3, null));
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void disconnect(Consumer<?> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Job job = this.consumerToJobMap.get(consumer);
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.consumerToJobMap.remove(consumer);
        } finally {
            reentrantLock.unlock();
        }
    }
}
