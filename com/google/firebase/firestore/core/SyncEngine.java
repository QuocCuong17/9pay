package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleElement;
import com.google.firebase.firestore.bundle.BundleLoader;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.View;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.local.LocalDocumentsResult;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LocalViewChanges;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SyncEngine implements RemoteStore.RemoteStoreCallback {
    private static final String TAG = "SyncEngine";
    private User currentUser;
    private final LocalStore localStore;
    private final int maxConcurrentLimboResolutions;
    private final RemoteStore remoteStore;
    private SyncEngineCallback syncEngineListener;
    private final Map<Query, QueryView> queryViewsByQuery = new HashMap();
    private final Map<Integer, List<Query>> queriesByTarget = new HashMap();
    private final LinkedHashSet<DocumentKey> enqueuedLimboResolutions = new LinkedHashSet<>();
    private final Map<DocumentKey, Integer> activeLimboTargetsByKey = new HashMap();
    private final Map<Integer, LimboResolution> activeLimboResolutionsByTarget = new HashMap();
    private final ReferenceSet limboDocumentRefs = new ReferenceSet();
    private final Map<User, Map<Integer, TaskCompletionSource<Void>>> mutationUserCallbacks = new HashMap();
    private final TargetIdGenerator targetIdGenerator = TargetIdGenerator.forSyncEngine();
    private final Map<Integer, List<TaskCompletionSource<Void>>> pendingWritesCallbacks = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface SyncEngineCallback {
        void handleOnlineStateChange(OnlineState onlineState);

        void onError(Query query, Status status);

        void onViewSnapshots(List<ViewSnapshot> list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class LimboResolution {
        private final DocumentKey key;
        private boolean receivedDocument;

        LimboResolution(DocumentKey documentKey) {
            this.key = documentKey;
        }
    }

    public SyncEngine(LocalStore localStore, RemoteStore remoteStore, User user, int i) {
        this.localStore = localStore;
        this.remoteStore = remoteStore;
        this.maxConcurrentLimboResolutions = i;
        this.currentUser = user;
    }

    public void setCallback(SyncEngineCallback syncEngineCallback) {
        this.syncEngineListener = syncEngineCallback;
    }

    private void assertCallback(String str) {
        Assert.hardAssert(this.syncEngineListener != null, "Trying to call %s before setting callback", str);
    }

    public int listen(Query query) {
        assertCallback("listen");
        Assert.hardAssert(!this.queryViewsByQuery.containsKey(query), "We already listen to query: %s", query);
        TargetData allocateTarget = this.localStore.allocateTarget(query.toTarget());
        this.syncEngineListener.onViewSnapshots(Collections.singletonList(initializeViewAndComputeSnapshot(query, allocateTarget.getTargetId(), allocateTarget.getResumeToken())));
        this.remoteStore.listen(allocateTarget);
        return allocateTarget.getTargetId();
    }

    private ViewSnapshot initializeViewAndComputeSnapshot(Query query, int i, ByteString byteString) {
        QueryResult executeQuery = this.localStore.executeQuery(query, true);
        ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
        if (this.queriesByTarget.get(Integer.valueOf(i)) != null) {
            syncState = this.queryViewsByQuery.get(this.queriesByTarget.get(Integer.valueOf(i)).get(0)).getView().getSyncState();
        }
        TargetChange createSynthesizedTargetChangeForCurrentChange = TargetChange.createSynthesizedTargetChangeForCurrentChange(syncState == ViewSnapshot.SyncState.SYNCED, byteString);
        View view = new View(query, executeQuery.getRemoteKeys());
        ViewChange applyChanges = view.applyChanges(view.computeDocChanges(executeQuery.getDocuments()), createSynthesizedTargetChangeForCurrentChange);
        updateTrackedLimboDocuments(applyChanges.getLimboChanges(), i);
        this.queryViewsByQuery.put(query, new QueryView(query, i, view));
        if (!this.queriesByTarget.containsKey(Integer.valueOf(i))) {
            this.queriesByTarget.put(Integer.valueOf(i), new ArrayList(1));
        }
        this.queriesByTarget.get(Integer.valueOf(i)).add(query);
        return applyChanges.getSnapshot();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopListening(Query query) {
        assertCallback("stopListening");
        QueryView queryView = this.queryViewsByQuery.get(query);
        Assert.hardAssert(queryView != null, "Trying to stop listening to a query not found", new Object[0]);
        this.queryViewsByQuery.remove(query);
        int targetId = queryView.getTargetId();
        List<Query> list = this.queriesByTarget.get(Integer.valueOf(targetId));
        list.remove(query);
        if (list.isEmpty()) {
            this.localStore.releaseTarget(targetId);
            this.remoteStore.stopListening(targetId);
            removeAndCleanupTarget(targetId, Status.OK);
        }
    }

    public void writeMutations(List<Mutation> list, TaskCompletionSource<Void> taskCompletionSource) {
        assertCallback("writeMutations");
        LocalDocumentsResult writeLocally = this.localStore.writeLocally(list);
        addUserCallback(writeLocally.getBatchId(), taskCompletionSource);
        emitNewSnapsAndNotifyLocalStore(writeLocally.getDocuments(), null);
        this.remoteStore.fillWritePipeline();
    }

    private void addUserCallback(int i, TaskCompletionSource<Void> taskCompletionSource) {
        Map<Integer, TaskCompletionSource<Void>> map = this.mutationUserCallbacks.get(this.currentUser);
        if (map == null) {
            map = new HashMap<>();
            this.mutationUserCallbacks.put(this.currentUser, map);
        }
        map.put(Integer.valueOf(i), taskCompletionSource);
    }

    public <TResult> Task<TResult> transaction(AsyncQueue asyncQueue, TransactionOptions transactionOptions, Function<Transaction, Task<TResult>> function) {
        return new TransactionRunner(asyncQueue, this.remoteStore, transactionOptions, function).run();
    }

    public Task<Map<String, Value>> runAggregateQuery(Query query, List<AggregateField> list) {
        return this.remoteStore.runAggregateQuery(query, list);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRemoteEvent(RemoteEvent remoteEvent) {
        assertCallback("handleRemoteEvent");
        for (Map.Entry<Integer, TargetChange> entry : remoteEvent.getTargetChanges().entrySet()) {
            Integer key = entry.getKey();
            TargetChange value = entry.getValue();
            LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(key);
            if (limboResolution != null) {
                Assert.hardAssert((value.getAddedDocuments().size() + value.getModifiedDocuments().size()) + value.getRemovedDocuments().size() <= 1, "Limbo resolution for single document contains multiple changes.", new Object[0]);
                if (value.getAddedDocuments().size() > 0) {
                    limboResolution.receivedDocument = true;
                } else if (value.getModifiedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received change for limbo target document without add.", new Object[0]);
                } else if (value.getRemovedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received remove for limbo target document without add.", new Object[0]);
                    limboResolution.receivedDocument = false;
                }
            }
        }
        emitNewSnapsAndNotifyLocalStore(this.localStore.applyRemoteEvent(remoteEvent), remoteEvent);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleOnlineStateChange(OnlineState onlineState) {
        assertCallback("handleOnlineStateChange");
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Query, QueryView>> it = this.queryViewsByQuery.entrySet().iterator();
        while (it.hasNext()) {
            ViewChange applyOnlineStateChange = it.next().getValue().getView().applyOnlineStateChange(onlineState);
            Assert.hardAssert(applyOnlineStateChange.getLimboChanges().isEmpty(), "OnlineState should not affect limbo documents.", new Object[0]);
            if (applyOnlineStateChange.getSnapshot() != null) {
                arrayList.add(applyOnlineStateChange.getSnapshot());
            }
        }
        this.syncEngineListener.onViewSnapshots(arrayList);
        this.syncEngineListener.handleOnlineStateChange(onlineState);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(i));
        if (limboResolution != null && limboResolution.receivedDocument) {
            return DocumentKey.emptyKeySet().insert(limboResolution.key);
        }
        ImmutableSortedSet<DocumentKey> emptyKeySet = DocumentKey.emptyKeySet();
        if (this.queriesByTarget.containsKey(Integer.valueOf(i))) {
            for (Query query : this.queriesByTarget.get(Integer.valueOf(i))) {
                if (this.queryViewsByQuery.containsKey(query)) {
                    emptyKeySet = emptyKeySet.unionWith(this.queryViewsByQuery.get(query).getView().getSyncedDocuments());
                }
            }
        }
        return emptyKeySet;
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRejectedListen(int i, Status status) {
        assertCallback("handleRejectedListen");
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(i));
        DocumentKey documentKey = limboResolution != null ? limboResolution.key : null;
        if (documentKey != null) {
            this.activeLimboTargetsByKey.remove(documentKey);
            this.activeLimboResolutionsByTarget.remove(Integer.valueOf(i));
            pumpEnqueuedLimboResolutions();
            handleRemoteEvent(new RemoteEvent(SnapshotVersion.NONE, Collections.emptyMap(), Collections.emptyMap(), Collections.singletonMap(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE)), Collections.singleton(documentKey)));
            return;
        }
        this.localStore.releaseTarget(i);
        removeAndCleanupTarget(i, status);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
        assertCallback("handleSuccessfulWrite");
        notifyUser(mutationBatchResult.getBatch().getBatchId(), null);
        resolvePendingWriteTasks(mutationBatchResult.getBatch().getBatchId());
        emitNewSnapsAndNotifyLocalStore(this.localStore.acknowledgeBatch(mutationBatchResult), null);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRejectedWrite(int i, Status status) {
        assertCallback("handleRejectedWrite");
        ImmutableSortedMap<DocumentKey, Document> rejectBatch = this.localStore.rejectBatch(i);
        if (!rejectBatch.isEmpty()) {
            logErrorIfInteresting(status, "Write failed at %s", rejectBatch.getMinKey().getPath());
        }
        notifyUser(i, status);
        resolvePendingWriteTasks(i);
        emitNewSnapsAndNotifyLocalStore(rejectBatch, null);
    }

    public void registerPendingWritesTask(TaskCompletionSource<Void> taskCompletionSource) {
        if (!this.remoteStore.canUseNetwork()) {
            Logger.debug(TAG, "The network is disabled. The task returned by 'awaitPendingWrites()' will not complete until the network is enabled.", new Object[0]);
        }
        int highestUnacknowledgedBatchId = this.localStore.getHighestUnacknowledgedBatchId();
        if (highestUnacknowledgedBatchId == -1) {
            taskCompletionSource.setResult(null);
            return;
        }
        if (!this.pendingWritesCallbacks.containsKey(Integer.valueOf(highestUnacknowledgedBatchId))) {
            this.pendingWritesCallbacks.put(Integer.valueOf(highestUnacknowledgedBatchId), new ArrayList());
        }
        this.pendingWritesCallbacks.get(Integer.valueOf(highestUnacknowledgedBatchId)).add(taskCompletionSource);
    }

    private void resolvePendingWriteTasks(int i) {
        if (this.pendingWritesCallbacks.containsKey(Integer.valueOf(i))) {
            Iterator<TaskCompletionSource<Void>> it = this.pendingWritesCallbacks.get(Integer.valueOf(i)).iterator();
            while (it.hasNext()) {
                it.next().setResult(null);
            }
            this.pendingWritesCallbacks.remove(Integer.valueOf(i));
        }
    }

    private void failOutstandingPendingWritesAwaitingTasks() {
        Iterator<Map.Entry<Integer, List<TaskCompletionSource<Void>>>> it = this.pendingWritesCallbacks.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<TaskCompletionSource<Void>> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                it2.next().setException(new FirebaseFirestoreException("'waitForPendingWrites' task is cancelled due to User change.", FirebaseFirestoreException.Code.CANCELLED));
            }
        }
        this.pendingWritesCallbacks.clear();
    }

    public void loadBundle(BundleReader bundleReader, LoadBundleTask loadBundleTask) {
        try {
            try {
                BundleMetadata bundleMetadata = bundleReader.getBundleMetadata();
                if (this.localStore.hasNewerBundle(bundleMetadata)) {
                    loadBundleTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                    try {
                        bundleReader.close();
                        return;
                    } catch (IOException e) {
                        Logger.warn(TAG, "Exception while closing bundle", e);
                        return;
                    }
                }
                loadBundleTask.updateProgress(LoadBundleTaskProgress.forInitial(bundleMetadata));
                BundleLoader bundleLoader = new BundleLoader(this.localStore, bundleMetadata);
                long j = 0;
                while (true) {
                    BundleElement nextElement = bundleReader.getNextElement();
                    if (nextElement == null) {
                        emitNewSnapsAndNotifyLocalStore(bundleLoader.applyChanges(), null);
                        this.localStore.saveBundle(bundleMetadata);
                        loadBundleTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                        try {
                            bundleReader.close();
                            return;
                        } catch (IOException e2) {
                            Logger.warn(TAG, "Exception while closing bundle", e2);
                            return;
                        }
                    }
                    long bytesRead = bundleReader.getBytesRead();
                    LoadBundleTaskProgress addElement = bundleLoader.addElement(nextElement, bytesRead - j);
                    if (addElement != null) {
                        loadBundleTask.updateProgress(addElement);
                    }
                    j = bytesRead;
                }
            } catch (Exception e3) {
                Logger.warn("Firestore", "Loading bundle failed : %s", e3);
                loadBundleTask.setException(new FirebaseFirestoreException("Bundle failed to load", FirebaseFirestoreException.Code.INVALID_ARGUMENT, e3));
                try {
                    bundleReader.close();
                } catch (IOException e4) {
                    Logger.warn(TAG, "Exception while closing bundle", e4);
                }
            }
        } catch (Throwable th) {
            try {
                bundleReader.close();
            } catch (IOException e5) {
                Logger.warn(TAG, "Exception while closing bundle", e5);
            }
            throw th;
        }
    }

    private void notifyUser(int i, Status status) {
        Integer valueOf;
        TaskCompletionSource<Void> taskCompletionSource;
        Map<Integer, TaskCompletionSource<Void>> map = this.mutationUserCallbacks.get(this.currentUser);
        if (map == null || (taskCompletionSource = map.get((valueOf = Integer.valueOf(i)))) == null) {
            return;
        }
        if (status != null) {
            taskCompletionSource.setException(Util.exceptionFromStatus(status));
        } else {
            taskCompletionSource.setResult(null);
        }
        map.remove(valueOf);
    }

    private void removeAndCleanupTarget(int i, Status status) {
        for (Query query : this.queriesByTarget.get(Integer.valueOf(i))) {
            this.queryViewsByQuery.remove(query);
            if (!status.isOk()) {
                this.syncEngineListener.onError(query, status);
                logErrorIfInteresting(status, "Listen for %s failed", query);
            }
        }
        this.queriesByTarget.remove(Integer.valueOf(i));
        ImmutableSortedSet<DocumentKey> referencesForId = this.limboDocumentRefs.referencesForId(i);
        this.limboDocumentRefs.removeReferencesForId(i);
        Iterator<DocumentKey> it = referencesForId.iterator();
        while (it.hasNext()) {
            DocumentKey next = it.next();
            if (!this.limboDocumentRefs.containsKey(next)) {
                removeLimboTarget(next);
            }
        }
    }

    private void removeLimboTarget(DocumentKey documentKey) {
        this.enqueuedLimboResolutions.remove(documentKey);
        Integer num = this.activeLimboTargetsByKey.get(documentKey);
        if (num != null) {
            this.remoteStore.stopListening(num.intValue());
            this.activeLimboTargetsByKey.remove(documentKey);
            this.activeLimboResolutionsByTarget.remove(num);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void emitNewSnapsAndNotifyLocalStore(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, RemoteEvent remoteEvent) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Map.Entry<Query, QueryView>> it = this.queryViewsByQuery.entrySet().iterator();
        while (it.hasNext()) {
            QueryView value = it.next().getValue();
            View view = value.getView();
            View.DocumentChanges computeDocChanges = view.computeDocChanges(immutableSortedMap);
            if (computeDocChanges.needsRefill()) {
                computeDocChanges = view.computeDocChanges(this.localStore.executeQuery(value.getQuery(), false).getDocuments(), computeDocChanges);
            }
            ViewChange applyChanges = value.getView().applyChanges(computeDocChanges, remoteEvent == null ? null : remoteEvent.getTargetChanges().get(Integer.valueOf(value.getTargetId())));
            updateTrackedLimboDocuments(applyChanges.getLimboChanges(), value.getTargetId());
            if (applyChanges.getSnapshot() != null) {
                arrayList.add(applyChanges.getSnapshot());
                arrayList2.add(LocalViewChanges.fromViewSnapshot(value.getTargetId(), applyChanges.getSnapshot()));
            }
        }
        this.syncEngineListener.onViewSnapshots(arrayList);
        this.localStore.notifyLocalViewChanges(arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.core.SyncEngine$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type;

        static {
            int[] iArr = new int[LimboDocumentChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type = iArr;
            try {
                iArr[LimboDocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type[LimboDocumentChange.Type.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void updateTrackedLimboDocuments(List<LimboDocumentChange> list, int i) {
        for (LimboDocumentChange limboDocumentChange : list) {
            int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type[limboDocumentChange.getType().ordinal()];
            if (i2 == 1) {
                this.limboDocumentRefs.addReference(limboDocumentChange.getKey(), i);
                trackLimboChange(limboDocumentChange);
            } else {
                if (i2 != 2) {
                    throw Assert.fail("Unknown limbo change type: %s", limboDocumentChange.getType());
                }
                Logger.debug(TAG, "Document no longer in limbo: %s", limboDocumentChange.getKey());
                DocumentKey key = limboDocumentChange.getKey();
                this.limboDocumentRefs.removeReference(key, i);
                if (!this.limboDocumentRefs.containsKey(key)) {
                    removeLimboTarget(key);
                }
            }
        }
    }

    private void trackLimboChange(LimboDocumentChange limboDocumentChange) {
        DocumentKey key = limboDocumentChange.getKey();
        if (this.activeLimboTargetsByKey.containsKey(key) || this.enqueuedLimboResolutions.contains(key)) {
            return;
        }
        Logger.debug(TAG, "New document in limbo: %s", key);
        this.enqueuedLimboResolutions.add(key);
        pumpEnqueuedLimboResolutions();
    }

    private void pumpEnqueuedLimboResolutions() {
        while (!this.enqueuedLimboResolutions.isEmpty() && this.activeLimboTargetsByKey.size() < this.maxConcurrentLimboResolutions) {
            Iterator<DocumentKey> it = this.enqueuedLimboResolutions.iterator();
            DocumentKey next = it.next();
            it.remove();
            int nextId = this.targetIdGenerator.nextId();
            this.activeLimboResolutionsByTarget.put(Integer.valueOf(nextId), new LimboResolution(next));
            this.activeLimboTargetsByKey.put(next, Integer.valueOf(nextId));
            this.remoteStore.listen(new TargetData(Query.atPath(next.getPath()).toTarget(), nextId, -1L, QueryPurpose.LIMBO_RESOLUTION));
        }
    }

    public Map<DocumentKey, Integer> getActiveLimboDocumentResolutions() {
        return new HashMap(this.activeLimboTargetsByKey);
    }

    public List<DocumentKey> getEnqueuedLimboDocumentResolutions() {
        return new ArrayList(this.enqueuedLimboResolutions);
    }

    public void handleCredentialChange(User user) {
        boolean z = !this.currentUser.equals(user);
        this.currentUser = user;
        if (z) {
            failOutstandingPendingWritesAwaitingTasks();
            emitNewSnapsAndNotifyLocalStore(this.localStore.handleUserChange(user), null);
        }
        this.remoteStore.handleCredentialChange();
    }

    private void logErrorIfInteresting(Status status, String str, Object... objArr) {
        if (errorIsInteresting(status)) {
            Logger.warn("Firestore", "%s: %s", String.format(str, objArr), status);
        }
    }

    private boolean errorIsInteresting(Status status) {
        Status.Code code = status.getCode();
        return (code == Status.Code.FAILED_PRECONDITION && (status.getDescription() != null ? status.getDescription() : "").contains("requires an index")) || code == Status.Code.PERMISSION_DENIED;
    }
}
