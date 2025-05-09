package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class MemoryEagerReferenceDelegate implements ReferenceDelegate {
    private ReferenceSet inMemoryPins;
    private Set<DocumentKey> orphanedDocuments;
    private final MemoryPersistence persistence;

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public long getCurrentSequenceNumber() {
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MemoryEagerReferenceDelegate(MemoryPersistence memoryPersistence) {
        this.persistence = memoryPersistence;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void setInMemoryPins(ReferenceSet referenceSet) {
        this.inMemoryPins = referenceSet;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void addReference(DocumentKey documentKey) {
        this.orphanedDocuments.remove(documentKey);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeReference(DocumentKey documentKey) {
        this.orphanedDocuments.add(documentKey);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeMutationReference(DocumentKey documentKey) {
        this.orphanedDocuments.add(documentKey);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeTarget(TargetData targetData) {
        MemoryTargetCache targetCache = this.persistence.getTargetCache();
        Iterator<DocumentKey> it = targetCache.getMatchingKeysForTargetId(targetData.getTargetId()).iterator();
        while (it.hasNext()) {
            this.orphanedDocuments.add(it.next());
        }
        targetCache.removeTargetData(targetData);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionStarted() {
        this.orphanedDocuments = new HashSet();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionCommitted() {
        MemoryRemoteDocumentCache remoteDocumentCache = this.persistence.getRemoteDocumentCache();
        ArrayList arrayList = new ArrayList();
        for (DocumentKey documentKey : this.orphanedDocuments) {
            if (!isReferenced(documentKey)) {
                arrayList.add(documentKey);
            }
        }
        remoteDocumentCache.removeAll(arrayList);
        this.orphanedDocuments = null;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void updateLimboDocument(DocumentKey documentKey) {
        if (isReferenced(documentKey)) {
            this.orphanedDocuments.remove(documentKey);
        } else {
            this.orphanedDocuments.add(documentKey);
        }
    }

    private boolean mutationQueuesContainKey(DocumentKey documentKey) {
        Iterator<MemoryMutationQueue> it = this.persistence.getMutationQueues().iterator();
        while (it.hasNext()) {
            if (it.next().containsKey(documentKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean isReferenced(DocumentKey documentKey) {
        if (this.persistence.getTargetCache().containsKey(documentKey) || mutationQueuesContainKey(documentKey)) {
            return true;
        }
        ReferenceSet referenceSet = this.inMemoryPins;
        return referenceSet != null && referenceSet.containsKey(documentKey);
    }
}
