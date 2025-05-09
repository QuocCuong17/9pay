package com.google.common.util.concurrent;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
abstract class AggregateFutureState<OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final LazyLogger log = new LazyLogger(AggregateFutureState.class);
    private volatile int remaining;

    @CheckForNull
    private volatile Set<Throwable> seenExceptions = null;

    abstract void addInitialException(Set<Throwable> seen);

    static /* synthetic */ int access$306(AggregateFutureState aggregateFutureState) {
        int i = aggregateFutureState.remaining - 1;
        aggregateFutureState.remaining = i;
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        AtomicHelper synchronizedAtomicHelper;
        Throwable th = null;
        Object[] objArr = 0;
        try {
            synchronizedAtomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
        } catch (Throwable th2) {
            synchronizedAtomicHelper = new SynchronizedAtomicHelper();
            th = th2;
        }
        ATOMIC_HELPER = synchronizedAtomicHelper;
        if (th != null) {
            log.get().log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AggregateFutureState(int remainingFutures) {
        this.remaining = remainingFutures;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Throwable> getOrInitSeenExceptions() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set<Throwable> newConcurrentHashSet = Sets.newConcurrentHashSet();
        addInitialException(newConcurrentHashSet);
        ATOMIC_HELPER.compareAndSetSeenExceptions(this, null, newConcurrentHashSet);
        Set<Throwable> set2 = this.seenExceptions;
        Objects.requireNonNull(set2);
        return set2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int decrementRemainingAndGet() {
        return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearSeenExceptions() {
        this.seenExceptions = null;
    }

    /* loaded from: classes4.dex */
    private static abstract class AtomicHelper {
        abstract void compareAndSetSeenExceptions(AggregateFutureState<?> state, @CheckForNull Set<Throwable> expect, Set<Throwable> update);

        abstract int decrementAndGetRemainingCount(AggregateFutureState<?> state);

        private AtomicHelper() {
        }
    }

    /* loaded from: classes4.dex */
    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicIntegerFieldUpdater<AggregateFutureState<?>> remainingCountUpdater;
        final AtomicReferenceFieldUpdater<AggregateFutureState<?>, Set<Throwable>> seenExceptionsUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater seenExceptionsUpdater, AtomicIntegerFieldUpdater remainingCountUpdater) {
            super();
            this.seenExceptionsUpdater = seenExceptionsUpdater;
            this.remainingCountUpdater = remainingCountUpdater;
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        void compareAndSetSeenExceptions(AggregateFutureState<?> state, @CheckForNull Set<Throwable> expect, Set<Throwable> update) {
            this.seenExceptionsUpdater.compareAndSet(state, expect, update);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        int decrementAndGetRemainingCount(AggregateFutureState<?> state) {
            return this.remainingCountUpdater.decrementAndGet(state);
        }
    }

    /* loaded from: classes4.dex */
    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        void compareAndSetSeenExceptions(AggregateFutureState<?> state, @CheckForNull Set<Throwable> expect, Set<Throwable> update) {
            synchronized (state) {
                if (((AggregateFutureState) state).seenExceptions == expect) {
                    ((AggregateFutureState) state).seenExceptions = update;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        int decrementAndGetRemainingCount(AggregateFutureState<?> state) {
            int access$306;
            synchronized (state) {
                access$306 = AggregateFutureState.access$306(state);
            }
            return access$306;
        }
    }
}
