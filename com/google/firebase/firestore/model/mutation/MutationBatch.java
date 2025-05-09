package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.local.OverlayedDocument;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class MutationBatch {
    public static final int UNKNOWN = -1;
    private final List<Mutation> baseMutations;
    private final int batchId;
    private final Timestamp localWriteTime;
    private final List<Mutation> mutations;

    public MutationBatch(int i, Timestamp timestamp, List<Mutation> list, List<Mutation> list2) {
        Assert.hardAssert(!list2.isEmpty(), "Cannot create an empty mutation batch", new Object[0]);
        this.batchId = i;
        this.localWriteTime = timestamp;
        this.baseMutations = list;
        this.mutations = list2;
    }

    public void applyToRemoteDocument(MutableDocument mutableDocument, MutationBatchResult mutationBatchResult) {
        int size = this.mutations.size();
        List<MutationResult> mutationResults = mutationBatchResult.getMutationResults();
        Assert.hardAssert(mutationResults.size() == size, "Mismatch between mutations length (%d) and results length (%d)", Integer.valueOf(size), Integer.valueOf(mutationResults.size()));
        for (int i = 0; i < size; i++) {
            Mutation mutation = this.mutations.get(i);
            if (mutation.getKey().equals(mutableDocument.getKey())) {
                mutation.applyToRemoteDocument(mutableDocument, mutationResults.get(i));
            }
        }
    }

    public FieldMask applyToLocalView(MutableDocument mutableDocument, FieldMask fieldMask) {
        for (int i = 0; i < this.baseMutations.size(); i++) {
            Mutation mutation = this.baseMutations.get(i);
            if (mutation.getKey().equals(mutableDocument.getKey())) {
                fieldMask = mutation.applyToLocalView(mutableDocument, fieldMask, this.localWriteTime);
            }
        }
        for (int i2 = 0; i2 < this.mutations.size(); i2++) {
            Mutation mutation2 = this.mutations.get(i2);
            if (mutation2.getKey().equals(mutableDocument.getKey())) {
                fieldMask = mutation2.applyToLocalView(mutableDocument, fieldMask, this.localWriteTime);
            }
        }
        return fieldMask;
    }

    public Map<DocumentKey, Mutation> applyToLocalDocumentSet(Map<DocumentKey, OverlayedDocument> map, Set<DocumentKey> set) {
        HashMap hashMap = new HashMap();
        for (DocumentKey documentKey : getKeys()) {
            MutableDocument mutableDocument = (MutableDocument) map.get(documentKey).getDocument();
            FieldMask applyToLocalView = applyToLocalView(mutableDocument, map.get(documentKey).getMutatedFields());
            if (set.contains(documentKey)) {
                applyToLocalView = null;
            }
            Mutation calculateOverlayMutation = Mutation.calculateOverlayMutation(mutableDocument, applyToLocalView);
            if (calculateOverlayMutation != null) {
                hashMap.put(documentKey, calculateOverlayMutation);
            }
            if (!mutableDocument.isValidDocument()) {
                mutableDocument.convertToNoDocument(SnapshotVersion.NONE);
            }
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MutationBatch mutationBatch = (MutationBatch) obj;
        return this.batchId == mutationBatch.batchId && this.localWriteTime.equals(mutationBatch.localWriteTime) && this.baseMutations.equals(mutationBatch.baseMutations) && this.mutations.equals(mutationBatch.mutations);
    }

    public int hashCode() {
        return (((((this.batchId * 31) + this.localWriteTime.hashCode()) * 31) + this.baseMutations.hashCode()) * 31) + this.mutations.hashCode();
    }

    public String toString() {
        return "MutationBatch(batchId=" + this.batchId + ", localWriteTime=" + this.localWriteTime + ", baseMutations=" + this.baseMutations + ", mutations=" + this.mutations + ')';
    }

    public Set<DocumentKey> getKeys() {
        HashSet hashSet = new HashSet();
        Iterator<Mutation> it = this.mutations.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getKey());
        }
        return hashSet;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public Timestamp getLocalWriteTime() {
        return this.localWriteTime;
    }

    public List<Mutation> getMutations() {
        return this.mutations;
    }

    public List<Mutation> getBaseMutations() {
        return this.baseMutations;
    }
}
