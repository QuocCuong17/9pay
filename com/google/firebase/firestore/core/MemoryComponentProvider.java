package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.MemoryLruGcSettings;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalSerializer;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.MemoryPersistence;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryEngine;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.AndroidConnectivityMonitor;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.remote.RemoteStore;
import io.grpc.Status;

/* loaded from: classes4.dex */
public class MemoryComponentProvider extends ComponentProvider {
    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected Scheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        return null;
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected IndexBackfiller createIndexBackfiller(ComponentProvider.Configuration configuration) {
        return null;
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected EventManager createEventManager(ComponentProvider.Configuration configuration) {
        return new EventManager(getSyncEngine());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected LocalStore createLocalStore(ComponentProvider.Configuration configuration) {
        return new LocalStore(getPersistence(), new QueryEngine(), configuration.getInitialUser());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.firestore.core.ComponentProvider
    public AndroidConnectivityMonitor createConnectivityMonitor(ComponentProvider.Configuration configuration) {
        return new AndroidConnectivityMonitor(configuration.getContext());
    }

    private boolean isMemoryLruGcEnabled(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        if (firebaseFirestoreSettings.getCacheSettings() == null || !(firebaseFirestoreSettings.getCacheSettings() instanceof MemoryCacheSettings)) {
            return false;
        }
        return ((MemoryCacheSettings) firebaseFirestoreSettings.getCacheSettings()).getGarbageCollectorSettings() instanceof MemoryLruGcSettings;
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected Persistence createPersistence(ComponentProvider.Configuration configuration) {
        if (isMemoryLruGcEnabled(configuration.getSettings())) {
            return MemoryPersistence.createLruGcMemoryPersistence(LruGarbageCollector.Params.WithCacheSizeBytes(configuration.getSettings().getCacheSizeBytes()), new LocalSerializer(new RemoteSerializer(configuration.getDatabaseInfo().getDatabaseId())));
        }
        return MemoryPersistence.createEagerGcMemoryPersistence();
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected RemoteStore createRemoteStore(ComponentProvider.Configuration configuration) {
        return new RemoteStore(new RemoteStoreCallback(), getLocalStore(), configuration.getDatastore(), configuration.getAsyncQueue(), getConnectivityMonitor());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    protected SyncEngine createSyncEngine(ComponentProvider.Configuration configuration) {
        return new SyncEngine(getLocalStore(), getRemoteStore(), configuration.getInitialUser(), configuration.getMaxConcurrentLimboResolutions());
    }

    /* loaded from: classes4.dex */
    private class RemoteStoreCallback implements RemoteStore.RemoteStoreCallback {
        private RemoteStoreCallback() {
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleRemoteEvent(RemoteEvent remoteEvent) {
            MemoryComponentProvider.this.getSyncEngine().handleRemoteEvent(remoteEvent);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleRejectedListen(int i, Status status) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedListen(i, status);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
            MemoryComponentProvider.this.getSyncEngine().handleSuccessfulWrite(mutationBatchResult);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleRejectedWrite(int i, Status status) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedWrite(i, status);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleOnlineStateChange(OnlineState onlineState) {
            MemoryComponentProvider.this.getSyncEngine().handleOnlineStateChange(onlineState);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
            return MemoryComponentProvider.this.getSyncEngine().getRemoteKeysForTarget(i);
        }
    }
}
