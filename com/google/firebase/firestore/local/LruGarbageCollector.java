package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class LruGarbageCollector {
    private static final long INITIAL_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long REGULAR_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(5);
    private final LruDelegate delegate;
    private final Params params;

    /* loaded from: classes4.dex */
    public static class Params {
        private static final long COLLECTION_DISABLED = -1;
        private static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
        private static final int DEFAULT_COLLECTION_PERCENTILE = 10;
        private static final int DEFAULT_MAX_SEQUENCE_NUMBERS_TO_COLLECT = 1000;
        final int maximumSequenceNumbersToCollect;
        long minBytesThreshold;
        int percentileToCollect;

        public static Params Default() {
            return new Params(DEFAULT_CACHE_SIZE_BYTES, 10, 1000);
        }

        public static Params Disabled() {
            return new Params(-1L, 0, 0);
        }

        public static Params WithCacheSizeBytes(long j) {
            return new Params(j, 10, 1000);
        }

        Params(long j, int i, int i2) {
            this.minBytesThreshold = j;
            this.percentileToCollect = i;
            this.maximumSequenceNumbersToCollect = i2;
        }
    }

    /* loaded from: classes4.dex */
    public static class Results {
        private final int documentsRemoved;
        private final boolean hasRun;
        private final int sequenceNumbersCollected;
        private final int targetsRemoved;

        static Results DidNotRun() {
            return new Results(false, 0, 0, 0);
        }

        Results(boolean z, int i, int i2, int i3) {
            this.hasRun = z;
            this.sequenceNumbersCollected = i;
            this.targetsRemoved = i2;
            this.documentsRemoved = i3;
        }

        public boolean hasRun() {
            return this.hasRun;
        }

        public int getSequenceNumbersCollected() {
            return this.sequenceNumbersCollected;
        }

        public int getTargetsRemoved() {
            return this.targetsRemoved;
        }

        public int getDocumentsRemoved() {
            return this.documentsRemoved;
        }
    }

    /* loaded from: classes4.dex */
    public class GCScheduler implements Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask gcTask;
        private boolean hasRun = false;
        private final LocalStore localStore;

        public GCScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
            this.asyncQueue = asyncQueue;
            this.localStore = localStore;
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            if (LruGarbageCollector.this.params.minBytesThreshold != -1) {
                scheduleGC();
            }
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.gcTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }

        private void scheduleGC() {
            this.gcTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.GARBAGE_COLLECTION, this.hasRun ? LruGarbageCollector.REGULAR_GC_DELAY_MS : LruGarbageCollector.INITIAL_GC_DELAY_MS, new Runnable() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$GCScheduler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LruGarbageCollector.GCScheduler.this.m813xb32188f8();
                }
            });
        }

        /* renamed from: lambda$scheduleGC$0$com-google-firebase-firestore-local-LruGarbageCollector$GCScheduler, reason: not valid java name */
        public /* synthetic */ void m813xb32188f8() {
            this.localStore.collectGarbage(LruGarbageCollector.this);
            this.hasRun = true;
            scheduleGC();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LruGarbageCollector(LruDelegate lruDelegate, Params params) {
        this.delegate = lruDelegate;
        this.params = params;
    }

    public GCScheduler newScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
        return new GCScheduler(asyncQueue, localStore);
    }

    public LruGarbageCollector withNewThreshold(long j) {
        this.params.minBytesThreshold = j;
        this.params.percentileToCollect = 100;
        return this;
    }

    int calculateQueryCount(int i) {
        return (int) ((i / 100.0f) * ((float) this.delegate.getSequenceNumberCount()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class RollingSequenceNumberBuffer {
        private static final Comparator<Long> COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$RollingSequenceNumberBuffer$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((Long) obj2).compareTo((Long) obj);
                return compareTo;
            }
        };
        private final int maxElements;
        private final PriorityQueue<Long> queue;

        RollingSequenceNumberBuffer(int i) {
            this.maxElements = i;
            this.queue = new PriorityQueue<>(i, COMPARATOR);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addElement(Long l) {
            if (this.queue.size() < this.maxElements) {
                this.queue.add(l);
                return;
            }
            if (l.longValue() < this.queue.peek().longValue()) {
                this.queue.poll();
                this.queue.add(l);
            }
        }

        long getMaxValue() {
            return this.queue.peek().longValue();
        }
    }

    long getNthSequenceNumber(int i) {
        if (i == 0) {
            return -1L;
        }
        final RollingSequenceNumberBuffer rollingSequenceNumberBuffer = new RollingSequenceNumberBuffer(i);
        this.delegate.forEachTarget(new Consumer() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                LruGarbageCollector.RollingSequenceNumberBuffer.this.addElement(Long.valueOf(((TargetData) obj).getSequenceNumber()));
            }
        });
        this.delegate.forEachOrphanedDocumentSequenceNumber(new Consumer() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                LruGarbageCollector.RollingSequenceNumberBuffer.this.addElement((Long) obj);
            }
        });
        return rollingSequenceNumberBuffer.getMaxValue();
    }

    int removeTargets(long j, SparseArray<?> sparseArray) {
        return this.delegate.removeTargets(j, sparseArray);
    }

    int removeOrphanedDocuments(long j) {
        return this.delegate.removeOrphanedDocuments(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Results collect(SparseArray<?> sparseArray) {
        if (this.params.minBytesThreshold == -1) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; disabled", new Object[0]);
            return Results.DidNotRun();
        }
        long byteSize = getByteSize();
        if (byteSize < this.params.minBytesThreshold) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; Cache size " + byteSize + " is lower than threshold " + this.params.minBytesThreshold, new Object[0]);
            return Results.DidNotRun();
        }
        return runGarbageCollection(sparseArray);
    }

    private Results runGarbageCollection(SparseArray<?> sparseArray) {
        long currentTimeMillis = System.currentTimeMillis();
        int calculateQueryCount = calculateQueryCount(this.params.percentileToCollect);
        if (calculateQueryCount > this.params.maximumSequenceNumbersToCollect) {
            Logger.debug("LruGarbageCollector", "Capping sequence numbers to collect down to the maximum of " + this.params.maximumSequenceNumbersToCollect + " from " + calculateQueryCount, new Object[0]);
            calculateQueryCount = this.params.maximumSequenceNumbersToCollect;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        long nthSequenceNumber = getNthSequenceNumber(calculateQueryCount);
        long currentTimeMillis3 = System.currentTimeMillis();
        int removeTargets = removeTargets(nthSequenceNumber, sparseArray);
        long currentTimeMillis4 = System.currentTimeMillis();
        int removeOrphanedDocuments = removeOrphanedDocuments(nthSequenceNumber);
        long currentTimeMillis5 = System.currentTimeMillis();
        if (Logger.isDebugEnabled()) {
            Logger.debug("LruGarbageCollector", (((("LRU Garbage Collection:\n\tCounted targets in " + (currentTimeMillis2 - currentTimeMillis) + "ms\n") + String.format(Locale.ROOT, "\tDetermined least recently used %d sequence numbers in %dms\n", Integer.valueOf(calculateQueryCount), Long.valueOf(currentTimeMillis3 - currentTimeMillis2))) + String.format(Locale.ROOT, "\tRemoved %d targets in %dms\n", Integer.valueOf(removeTargets), Long.valueOf(currentTimeMillis4 - currentTimeMillis3))) + String.format(Locale.ROOT, "\tRemoved %d documents in %dms\n", Integer.valueOf(removeOrphanedDocuments), Long.valueOf(currentTimeMillis5 - currentTimeMillis4))) + String.format(Locale.ROOT, "Total Duration: %dms", Long.valueOf(currentTimeMillis5 - currentTimeMillis)), new Object[0]);
        }
        return new Results(true, calculateQueryCount, removeTargets, removeOrphanedDocuments);
    }

    long getByteSize() {
        return this.delegate.getByteSize();
    }
}
