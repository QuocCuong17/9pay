package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.remote.WatchChangeAggregator;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class TestingHooks {
    private static final TestingHooks instance = new TestingHooks();
    private final CopyOnWriteArrayList<AtomicReference<ExistenceFilterMismatchListener>> existenceFilterMismatchListeners = new CopyOnWriteArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface ExistenceFilterMismatchListener {
        void onExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo);
    }

    private TestingHooks() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TestingHooks getInstance() {
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyOnExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo) {
        Iterator<AtomicReference<ExistenceFilterMismatchListener>> it = this.existenceFilterMismatchListeners.iterator();
        while (it.hasNext()) {
            ExistenceFilterMismatchListener existenceFilterMismatchListener = it.next().get();
            if (existenceFilterMismatchListener != null) {
                existenceFilterMismatchListener.onExistenceFilterMismatch(existenceFilterMismatchInfo);
            }
        }
    }

    ListenerRegistration addExistenceFilterMismatchListener(ExistenceFilterMismatchListener existenceFilterMismatchListener) {
        Preconditions.checkNotNull(existenceFilterMismatchListener, "a null listener is not allowed");
        final AtomicReference<ExistenceFilterMismatchListener> atomicReference = new AtomicReference<>(existenceFilterMismatchListener);
        this.existenceFilterMismatchListeners.add(atomicReference);
        return new ListenerRegistration() { // from class: com.google.firebase.firestore.remote.TestingHooks$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                TestingHooks.this.m877xc9d8f707(atomicReference);
            }
        };
    }

    /* renamed from: lambda$addExistenceFilterMismatchListener$0$com-google-firebase-firestore-remote-TestingHooks, reason: not valid java name */
    public /* synthetic */ void m877xc9d8f707(AtomicReference atomicReference) {
        atomicReference.set(null);
        this.existenceFilterMismatchListeners.remove(atomicReference);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static abstract class ExistenceFilterMismatchInfo {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract ExistenceFilterBloomFilterInfo bloomFilter();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String databaseId();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int existenceFilterCount();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int localCacheCount();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String projectId();

        static ExistenceFilterMismatchInfo create(int i, int i2, String str, String str2, ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
            return new AutoValue_TestingHooks_ExistenceFilterMismatchInfo(i, i2, str, str2, existenceFilterBloomFilterInfo);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static ExistenceFilterMismatchInfo from(int i, ExistenceFilter existenceFilter, DatabaseId databaseId, BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterApplicationStatus) {
            return create(i, existenceFilter.getCount(), databaseId.getProjectId(), databaseId.getDatabaseId(), ExistenceFilterBloomFilterInfo.from(bloomFilter, bloomFilterApplicationStatus, existenceFilter));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static abstract class ExistenceFilterBloomFilterInfo {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean applied();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int bitmapLength();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract BloomFilter bloomFilter();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int hashCount();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int padding();

        static ExistenceFilterBloomFilterInfo create(BloomFilter bloomFilter, boolean z, int i, int i2, int i3) {
            return new AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(bloomFilter, z, i, i2, i3);
        }

        static ExistenceFilterBloomFilterInfo from(BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterApplicationStatus, ExistenceFilter existenceFilter) {
            com.google.firestore.v1.BloomFilter unchangedNames = existenceFilter.getUnchangedNames();
            if (unchangedNames == null) {
                return null;
            }
            return create(bloomFilter, bloomFilterApplicationStatus == WatchChangeAggregator.BloomFilterApplicationStatus.SUCCESS, unchangedNames.getHashCount(), unchangedNames.getBits().getBitmap().size(), unchangedNames.getBits().getPadding());
        }
    }
}
