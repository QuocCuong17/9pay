package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public final class Monitor {

    @CheckForNull
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    /* loaded from: classes4.dex */
    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;

        @CheckForNull
        Guard next;
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        /* JADX INFO: Access modifiers changed from: protected */
        public Guard(Monitor monitor) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean fair) {
        this.activeGuards = null;
        this.fair = fair;
        this.lock = new ReentrantLock(fair);
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enter(long time, TimeUnit unit) {
        boolean tryLock;
        long safeNanos = toSafeNanos(time, unit);
        ReentrantLock reentrantLock = this.lock;
        boolean z = true;
        if (!this.fair && reentrantLock.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            long nanoTime = System.nanoTime();
            long j = safeNanos;
            while (true) {
                try {
                    try {
                        tryLock = reentrantLock.tryLock(j, TimeUnit.NANOSECONDS);
                        break;
                    } catch (Throwable th) {
                        th = th;
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    j = remainingNanos(nanoTime, safeNanos);
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (Throwable th2) {
            th = th2;
            z = interrupted;
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lockInterruptibly();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            await(guard, isHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        if (awaitNanos(r11, r0, r3) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhen(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long initNanoTime;
        long safeNanos = toSafeNanos(time, unit);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        boolean z = false;
        try {
            if (!this.fair) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                if (reentrantLock.tryLock()) {
                    initNanoTime = 0;
                    if (!guard.isSatisfied()) {
                        if (initNanoTime != 0) {
                            safeNanos = remainingNanos(initNanoTime, safeNanos);
                        }
                    }
                    z = true;
                    if (!z) {
                    }
                    return z;
                }
            }
            if (!guard.isSatisfied()) {
            }
            z = true;
            if (!z) {
            }
            return z;
        } catch (Throwable th) {
            if (!isHeldByCurrentThread) {
                try {
                    signalNextWaiter();
                } finally {
                    reentrantLock.unlock();
                }
            }
            throw th;
        }
        initNanoTime = initNanoTime(safeNanos);
        if (!reentrantLock.tryLock(time, unit)) {
            return false;
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lock();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            awaitUninterruptibly(guard, isHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004b A[Catch: all -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0073, blocks: (B:5:0x0013, B:7:0x001a, B:22:0x004b, B:32:0x0059, B:33:0x005c, B:34:0x0023, B:37:0x0028, B:13:0x0030, B:17:0x003b, B:18:0x0045, B:27:0x0041), top: B:4:0x0013, inners: #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long initNanoTime;
        long remainingNanos;
        long safeNanos = toSafeNanos(time, unit);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        boolean interrupted = Thread.interrupted();
        boolean z = true;
        try {
            if (!this.fair && reentrantLock.tryLock()) {
                initNanoTime = 0;
                while (!guard.isSatisfied()) {
                    try {
                        if (initNanoTime == 0) {
                            initNanoTime = initNanoTime(safeNanos);
                            remainingNanos = safeNanos;
                        } else {
                            remainingNanos = remainingNanos(initNanoTime, safeNanos);
                        }
                        z = awaitNanos(guard, remainingNanos, isHeldByCurrentThread);
                    } catch (InterruptedException unused) {
                        interrupted = z;
                        isHeldByCurrentThread = false;
                    } catch (Throwable th) {
                        reentrantLock.unlock();
                        throw th;
                    }
                }
                if (!z) {
                    reentrantLock.unlock();
                }
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return z;
            }
            initNanoTime = initNanoTime(safeNanos);
            long j = safeNanos;
            while (true) {
                try {
                    try {
                        break;
                    } catch (Throwable th2) {
                        th = th2;
                        interrupted = true;
                        if (interrupted) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                } catch (InterruptedException unused2) {
                    j = remainingNanos(initNanoTime, safeNanos);
                    interrupted = true;
                }
            }
            if (!reentrantLock.tryLock(j, TimeUnit.NANOSECONDS)) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return false;
            }
            while (!guard.isSatisfied()) {
            }
            if (!z) {
            }
            if (interrupted) {
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIf(Guard guard, long time, TimeUnit unit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        if (!enter(time, unit)) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock(time, unit)) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock()) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        await(guard, true);
    }

    public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long safeNanos = toSafeNanos(time, unit);
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return true;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return awaitNanos(guard, safeNanos, true);
    }

    public void waitForUninterruptibly(Guard guard) {
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        awaitUninterruptibly(guard, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long safeNanos = toSafeNanos(time, unit);
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        boolean z = true;
        if (guard.isSatisfied()) {
            return true;
        }
        long initNanoTime = initNanoTime(safeNanos);
        boolean interrupted = Thread.interrupted();
        long j = safeNanos;
        boolean z2 = true;
        while (true) {
            try {
                try {
                    boolean awaitNanos = awaitNanos(guard, j, z2);
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return awaitNanos;
                } catch (Throwable th) {
                    th = th;
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (InterruptedException unused) {
                if (!guard.isSatisfied()) {
                    z2 = false;
                    j = remainingNanos(initNanoTime, safeNanos);
                    interrupted = true;
                } else {
                    Thread.currentThread().interrupt();
                    return true;
                }
            } catch (Throwable th2) {
                th = th2;
                z = interrupted;
                if (z) {
                }
                throw th;
            }
        }
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            return guard.waiterCount;
        } finally {
            this.lock.unlock();
        }
    }

    private static long toSafeNanos(long time, TimeUnit unit) {
        return Longs.constrainToRange(unit.toNanos(time), 0L, 6917529027641081853L);
    }

    private static long initNanoTime(long timeoutNanos) {
        if (timeoutNanos <= 0) {
            return 0L;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1L;
        }
        return nanoTime;
    }

    private static long remainingNanos(long startTime, long timeoutNanos) {
        if (timeoutNanos <= 0) {
            return 0L;
        }
        return timeoutNanos - (System.nanoTime() - startTime);
    }

    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            throw th;
        }
    }

    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    private void beginWaitingFor(Guard guard) {
        int i = guard.waiterCount;
        guard.waiterCount = i + 1;
        if (i == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    private void endWaitingFor(Guard guard) {
        int i = guard.waiterCount - 1;
        guard.waiterCount = i;
        if (i == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
        boolean z = true;
        while (nanos > 0) {
            if (z) {
                if (signalBeforeWaiting) {
                    try {
                        signalNextWaiter();
                    } finally {
                        if (!z) {
                            endWaitingFor(guard);
                        }
                    }
                }
                beginWaitingFor(guard);
                z = false;
            }
            nanos = guard.condition.awaitNanos(nanos);
            if (guard.isSatisfied()) {
                if (!z) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        return false;
    }
}
