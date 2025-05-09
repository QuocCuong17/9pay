package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.BloomFilter;
import com.google.firebase.firestore.remote.TestingHooks;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class WatchChangeAggregator {
    private static final String LOG_TAG = "WatchChangeAggregator";
    private final TargetMetadataProvider targetMetadataProvider;
    private final Map<Integer, TargetState> targetStates = new HashMap();
    private Map<DocumentKey, MutableDocument> pendingDocumentUpdates = new HashMap();
    private Map<DocumentKey, Set<Integer>> pendingDocumentTargetMapping = new HashMap();
    private Map<Integer, QueryPurpose> pendingTargetResets = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum BloomFilterApplicationStatus {
        SUCCESS,
        SKIPPED,
        FALSE_POSITIVE
    }

    /* loaded from: classes4.dex */
    public interface TargetMetadataProvider {
        DatabaseId getDatabaseId();

        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        TargetData getTargetDataForTarget(int i);
    }

    public WatchChangeAggregator(TargetMetadataProvider targetMetadataProvider) {
        this.targetMetadataProvider = targetMetadataProvider;
    }

    public void handleDocumentChange(WatchChange.DocumentChange documentChange) {
        MutableDocument newDocument = documentChange.getNewDocument();
        DocumentKey documentKey = documentChange.getDocumentKey();
        Iterator<Integer> it = documentChange.getUpdatedTargetIds().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (newDocument != null && newDocument.isFoundDocument()) {
                addDocumentToTarget(intValue, newDocument);
            } else {
                removeDocumentFromTarget(intValue, documentKey, newDocument);
            }
        }
        Iterator<Integer> it2 = documentChange.getRemovedTargetIds().iterator();
        while (it2.hasNext()) {
            removeDocumentFromTarget(it2.next().intValue(), documentKey, documentChange.getNewDocument());
        }
    }

    public void handleTargetChange(WatchChange.WatchTargetChange watchTargetChange) {
        Iterator<Integer> it = getTargetIds(watchTargetChange).iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            TargetState ensureTargetState = ensureTargetState(intValue);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[watchTargetChange.getChangeType().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    ensureTargetState.recordTargetResponse();
                    if (!ensureTargetState.isPending()) {
                        ensureTargetState.clearChanges();
                    }
                    ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                } else if (i == 3) {
                    ensureTargetState.recordTargetResponse();
                    if (!ensureTargetState.isPending()) {
                        removeTarget(intValue);
                    }
                    Assert.hardAssert(watchTargetChange.getCause() == null, "WatchChangeAggregator does not handle errored targets", new Object[0]);
                } else if (i != 4) {
                    if (i != 5) {
                        throw Assert.fail("Unknown target watch change state: %s", watchTargetChange.getChangeType());
                    }
                    if (isActiveTarget(intValue)) {
                        resetTarget(intValue);
                        ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                    }
                } else if (isActiveTarget(intValue)) {
                    ensureTargetState.markCurrent();
                    ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                }
            } else if (isActiveTarget(intValue)) {
                ensureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.remote.WatchChangeAggregator$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType;

        static {
            int[] iArr = new int[WatchChange.WatchTargetChangeType.values().length];
            $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType = iArr;
            try {
                iArr[WatchChange.WatchTargetChangeType.NoChange.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Added.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Removed.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Current.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Reset.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Collection<Integer> getTargetIds(WatchChange.WatchTargetChange watchTargetChange) {
        List<Integer> targetIds = watchTargetChange.getTargetIds();
        if (!targetIds.isEmpty()) {
            return targetIds;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.targetStates.keySet()) {
            if (isActiveTarget(num.intValue())) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    public void handleExistenceFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange) {
        BloomFilterApplicationStatus bloomFilterApplicationStatus;
        QueryPurpose queryPurpose;
        int targetId = existenceFilterWatchChange.getTargetId();
        int count = existenceFilterWatchChange.getExistenceFilter().getCount();
        TargetData queryDataForActiveTarget = queryDataForActiveTarget(targetId);
        if (queryDataForActiveTarget != null) {
            Target target = queryDataForActiveTarget.getTarget();
            if (target.isDocumentQuery()) {
                if (count == 0) {
                    DocumentKey fromPath = DocumentKey.fromPath(target.getPath());
                    removeDocumentFromTarget(targetId, fromPath, MutableDocument.newNoDocument(fromPath, SnapshotVersion.NONE));
                    return;
                } else {
                    Assert.hardAssert(count == 1, "Single document existence filter with count: %d", Integer.valueOf(count));
                    return;
                }
            }
            int currentDocumentCountForTarget = getCurrentDocumentCountForTarget(targetId);
            if (currentDocumentCountForTarget != count) {
                BloomFilter parseBloomFilter = parseBloomFilter(existenceFilterWatchChange);
                if (parseBloomFilter != null) {
                    bloomFilterApplicationStatus = applyBloomFilter(parseBloomFilter, existenceFilterWatchChange, currentDocumentCountForTarget);
                } else {
                    bloomFilterApplicationStatus = BloomFilterApplicationStatus.SKIPPED;
                }
                if (bloomFilterApplicationStatus != BloomFilterApplicationStatus.SUCCESS) {
                    resetTarget(targetId);
                    if (bloomFilterApplicationStatus == BloomFilterApplicationStatus.FALSE_POSITIVE) {
                        queryPurpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH_BLOOM;
                    } else {
                        queryPurpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH;
                    }
                    this.pendingTargetResets.put(Integer.valueOf(targetId), queryPurpose);
                }
                TestingHooks.getInstance().notifyOnExistenceFilterMismatch(TestingHooks.ExistenceFilterMismatchInfo.from(currentDocumentCountForTarget, existenceFilterWatchChange.getExistenceFilter(), this.targetMetadataProvider.getDatabaseId(), parseBloomFilter, bloomFilterApplicationStatus));
            }
        }
    }

    private BloomFilter parseBloomFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange) {
        com.google.firestore.v1.BloomFilter unchangedNames = existenceFilterWatchChange.getExistenceFilter().getUnchangedNames();
        if (unchangedNames != null && unchangedNames.hasBits()) {
            try {
                BloomFilter create = BloomFilter.create(unchangedNames.getBits().getBitmap(), unchangedNames.getBits().getPadding(), unchangedNames.getHashCount());
                if (create.getBitCount() == 0) {
                    return null;
                }
                return create;
            } catch (BloomFilter.BloomFilterCreateException e) {
                Logger.warn(LOG_TAG, "Applying bloom filter failed: (" + e.getMessage() + "); ignoring the bloom filter and falling back to full re-query.", new Object[0]);
            }
        }
        return null;
    }

    private BloomFilterApplicationStatus applyBloomFilter(BloomFilter bloomFilter, WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange, int i) {
        if (existenceFilterWatchChange.getExistenceFilter().getCount() == i - filterRemovedDocuments(bloomFilter, existenceFilterWatchChange.getTargetId())) {
            return BloomFilterApplicationStatus.SUCCESS;
        }
        return BloomFilterApplicationStatus.FALSE_POSITIVE;
    }

    private int filterRemovedDocuments(BloomFilter bloomFilter, int i) {
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            DocumentKey next = it.next();
            DatabaseId databaseId = this.targetMetadataProvider.getDatabaseId();
            if (!bloomFilter.mightContain("projects/" + databaseId.getProjectId() + "/databases/" + databaseId.getDatabaseId() + "/documents/" + next.getPath().canonicalString())) {
                removeDocumentFromTarget(i, next, null);
                i2++;
            }
        }
        return i2;
    }

    public RemoteEvent createRemoteEvent(SnapshotVersion snapshotVersion) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Integer, TargetState> entry : this.targetStates.entrySet()) {
            int intValue = entry.getKey().intValue();
            TargetState value = entry.getValue();
            TargetData queryDataForActiveTarget = queryDataForActiveTarget(intValue);
            if (queryDataForActiveTarget != null) {
                if (value.isCurrent() && queryDataForActiveTarget.getTarget().isDocumentQuery()) {
                    DocumentKey fromPath = DocumentKey.fromPath(queryDataForActiveTarget.getTarget().getPath());
                    if (this.pendingDocumentUpdates.get(fromPath) == null && !targetContainsDocument(intValue, fromPath)) {
                        removeDocumentFromTarget(intValue, fromPath, MutableDocument.newNoDocument(fromPath, snapshotVersion));
                    }
                }
                if (value.hasChanges()) {
                    hashMap.put(Integer.valueOf(intValue), value.toTargetChange());
                    value.clearChanges();
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry<DocumentKey, Set<Integer>> entry2 : this.pendingDocumentTargetMapping.entrySet()) {
            DocumentKey key = entry2.getKey();
            boolean z = true;
            Iterator<Integer> it = entry2.getValue().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TargetData queryDataForActiveTarget2 = queryDataForActiveTarget(it.next().intValue());
                if (queryDataForActiveTarget2 != null && !queryDataForActiveTarget2.getPurpose().equals(QueryPurpose.LIMBO_RESOLUTION)) {
                    z = false;
                    break;
                }
            }
            if (z) {
                hashSet.add(key);
            }
        }
        Iterator<MutableDocument> it2 = this.pendingDocumentUpdates.values().iterator();
        while (it2.hasNext()) {
            it2.next().setReadTime(snapshotVersion);
        }
        RemoteEvent remoteEvent = new RemoteEvent(snapshotVersion, Collections.unmodifiableMap(hashMap), Collections.unmodifiableMap(this.pendingTargetResets), Collections.unmodifiableMap(this.pendingDocumentUpdates), Collections.unmodifiableSet(hashSet));
        this.pendingDocumentUpdates = new HashMap();
        this.pendingDocumentTargetMapping = new HashMap();
        this.pendingTargetResets = new HashMap();
        return remoteEvent;
    }

    private void addDocumentToTarget(int i, MutableDocument mutableDocument) {
        DocumentViewChange.Type type;
        if (isActiveTarget(i)) {
            if (targetContainsDocument(i, mutableDocument.getKey())) {
                type = DocumentViewChange.Type.MODIFIED;
            } else {
                type = DocumentViewChange.Type.ADDED;
            }
            ensureTargetState(i).addDocumentChange(mutableDocument.getKey(), type);
            this.pendingDocumentUpdates.put(mutableDocument.getKey(), mutableDocument);
            ensureDocumentTargetMapping(mutableDocument.getKey()).add(Integer.valueOf(i));
        }
    }

    private void removeDocumentFromTarget(int i, DocumentKey documentKey, MutableDocument mutableDocument) {
        if (isActiveTarget(i)) {
            TargetState ensureTargetState = ensureTargetState(i);
            if (targetContainsDocument(i, documentKey)) {
                ensureTargetState.addDocumentChange(documentKey, DocumentViewChange.Type.REMOVED);
            } else {
                ensureTargetState.removeDocumentChange(documentKey);
            }
            ensureDocumentTargetMapping(documentKey).add(Integer.valueOf(i));
            if (mutableDocument != null) {
                this.pendingDocumentUpdates.put(documentKey, mutableDocument);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeTarget(int i) {
        this.targetStates.remove(Integer.valueOf(i));
    }

    private int getCurrentDocumentCountForTarget(int i) {
        TargetChange targetChange = ensureTargetState(i).toTargetChange();
        return (this.targetMetadataProvider.getRemoteKeysForTarget(i).size() + targetChange.getAddedDocuments().size()) - targetChange.getRemovedDocuments().size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordPendingTargetRequest(int i) {
        ensureTargetState(i).recordPendingTargetRequest();
    }

    private TargetState ensureTargetState(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState != null) {
            return targetState;
        }
        TargetState targetState2 = new TargetState();
        this.targetStates.put(Integer.valueOf(i), targetState2);
        return targetState2;
    }

    private Set<Integer> ensureDocumentTargetMapping(DocumentKey documentKey) {
        Set<Integer> set = this.pendingDocumentTargetMapping.get(documentKey);
        if (set != null) {
            return set;
        }
        HashSet hashSet = new HashSet();
        this.pendingDocumentTargetMapping.put(documentKey, hashSet);
        return hashSet;
    }

    private boolean isActiveTarget(int i) {
        return queryDataForActiveTarget(i) != null;
    }

    private TargetData queryDataForActiveTarget(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState == null || !targetState.isPending()) {
            return this.targetMetadataProvider.getTargetDataForTarget(i);
        }
        return null;
    }

    private void resetTarget(int i) {
        Assert.hardAssert((this.targetStates.get(Integer.valueOf(i)) == null || this.targetStates.get(Integer.valueOf(i)).isPending()) ? false : true, "Should only reset active targets", new Object[0]);
        this.targetStates.put(Integer.valueOf(i), new TargetState());
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        while (it.hasNext()) {
            removeDocumentFromTarget(i, it.next(), null);
        }
    }

    private boolean targetContainsDocument(int i, DocumentKey documentKey) {
        return this.targetMetadataProvider.getRemoteKeysForTarget(i).contains(documentKey);
    }
}
