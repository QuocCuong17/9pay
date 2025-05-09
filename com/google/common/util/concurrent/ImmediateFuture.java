package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public class ImmediateFuture<V> implements ListenableFuture<V> {
    static final ListenableFuture<?> NULL = new ImmediateFuture(null);
    private static final LazyLogger log = new LazyLogger(ImmediateFuture.class);

    @ParametricNullness
    private final V value;

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmediateFuture(@ParametricNullness V value) {
        this.value = value;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor executor) {
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(listener);
        } catch (Exception e) {
            log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    @ParametricNullness
    public V get() {
        return this.value;
    }

    @Override // java.util.concurrent.Future
    @ParametricNullness
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        Preconditions.checkNotNull(unit);
        return get();
    }

    public String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ImmediateFailedFuture(Throwable thrown) {
            setException(thrown);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {

        @CheckForNull
        static final ImmediateCancelledFuture<Object> INSTANCE;

        static {
            INSTANCE = AbstractFuture.GENERATE_CANCELLATION_CAUSES ? null : new ImmediateCancelledFuture<>();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ImmediateCancelledFuture() {
            cancel(false);
        }
    }
}
