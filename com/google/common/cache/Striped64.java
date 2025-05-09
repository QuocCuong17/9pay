package com.google.common.cache;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
abstract class Striped64 extends Number {
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    volatile transient long base;
    volatile transient int busy;

    @CheckForNull
    volatile transient Cell[] cells;
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    static final Random rng = new Random();
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    abstract long fn(long currentValue, long newValue);

    static /* synthetic */ Unsafe access$000() {
        return getUnsafe();
    }

    /* loaded from: classes3.dex */
    static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;
        volatile long p0;
        volatile long p1;
        volatile long p2;
        volatile long p3;
        volatile long p4;
        volatile long p5;
        volatile long p6;
        volatile long q0;
        volatile long q1;
        volatile long q2;
        volatile long q3;
        volatile long q4;
        volatile long q5;
        volatile long q6;
        volatile long value;

        Cell(long x) {
            this.value = x;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }

        static {
            try {
                Unsafe access$000 = Striped64.access$000();
                UNSAFE = access$000;
                valueOffset = access$000.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static {
        try {
            Unsafe unsafe = getUnsafe();
            UNSAFE = unsafe;
            baseOffset = unsafe.objectFieldOffset(Striped64.class.getDeclaredField(TtmlNode.RUBY_BASE));
            busyOffset = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, cmp, val);
    }

    final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0023 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void retryUpdate(long x, @CheckForNull int[] hc, boolean wasUncontended) {
        int i;
        int[] iArr;
        boolean z;
        int length;
        boolean z2;
        int length2;
        if (hc == null) {
            iArr = new int[1];
            threadHashCode.set(iArr);
            i = rng.nextInt();
            if (i == 0) {
                i = 1;
            }
            iArr[0] = i;
        } else {
            i = hc[0];
            iArr = hc;
        }
        boolean z3 = false;
        int i2 = i;
        boolean z4 = wasUncontended;
        while (true) {
            Cell[] cellArr = this.cells;
            if (cellArr != null && (length = cellArr.length) > 0) {
                Cell cell = cellArr[(length - 1) & i2];
                if (cell == null) {
                    if (this.busy == 0) {
                        Cell cell2 = new Cell(x);
                        if (this.busy == 0 && casBusy()) {
                            try {
                                Cell[] cellArr2 = this.cells;
                                if (cellArr2 != null && (length2 = cellArr2.length) > 0) {
                                    int i3 = (length2 - 1) & i2;
                                    if (cellArr2[i3] == null) {
                                        cellArr2[i3] = cell2;
                                        z2 = true;
                                        if (!z2) {
                                            return;
                                        }
                                    }
                                }
                                z2 = false;
                                if (!z2) {
                                }
                            } finally {
                            }
                        }
                    }
                    z3 = false;
                } else if (z4) {
                    long j = cell.value;
                    if (cell.cas(j, fn(j, x))) {
                        return;
                    }
                    if (length < NCPU && this.cells == cellArr) {
                        if (!z3) {
                            z3 = true;
                        } else if (this.busy == 0 && casBusy()) {
                            try {
                                if (this.cells == cellArr) {
                                    Cell[] cellArr3 = new Cell[length << 1];
                                    for (int i4 = 0; i4 < length; i4++) {
                                        cellArr3[i4] = cellArr[i4];
                                    }
                                    this.cells = cellArr3;
                                }
                                this.busy = 0;
                                z3 = false;
                            } finally {
                            }
                        }
                    }
                    z3 = false;
                } else {
                    z4 = true;
                }
                int i5 = i2 ^ (i2 << 13);
                int i6 = i5 ^ (i5 >>> 17);
                i2 = i6 ^ (i6 << 5);
                iArr[0] = i2;
            } else if (this.busy == 0 && this.cells == cellArr && casBusy()) {
                try {
                    if (this.cells == cellArr) {
                        Cell[] cellArr4 = new Cell[2];
                        cellArr4[i2 & 1] = new Cell(x);
                        this.cells = cellArr4;
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                } finally {
                }
            } else {
                long j2 = this.base;
                if (casBase(j2, fn(j2, x))) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void internalReset(long initialValue) {
        Cell[] cellArr = this.cells;
        this.base = initialValue;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = initialValue;
                }
            }
        }
    }

    private static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (PrivilegedActionException e) {
                throw new RuntimeException("Could not initialize intrinsics", e.getCause());
            }
        } catch (SecurityException unused) {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.cache.Striped64.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws Exception {
                    for (Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    throw new NoSuchFieldError("the Unsafe");
                }
            });
        }
    }
}
