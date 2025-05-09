package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class View {
    private boolean current;
    private DocumentSet documentSet;
    private final Query query;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> limboDocuments = DocumentKey.emptyKeySet();
    private ImmutableSortedSet<DocumentKey> mutatedKeys = DocumentKey.emptyKeySet();

    /* loaded from: classes4.dex */
    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        private final boolean needsRefill;

        /* synthetic */ DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet immutableSortedSet, boolean z, AnonymousClass1 anonymousClass1) {
            this(documentSet, documentViewChangeSet, immutableSortedSet, z);
        }

        private DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z) {
            this.documentSet = documentSet;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    public View(Query query, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.query = query;
        this.documentSet = DocumentSet.emptySet(query.comparator());
        this.syncedDocuments = immutableSortedSet;
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        return computeDocChanges(immutableSortedMap, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00f4, code lost:
    
        if (r18.query.comparator().compare(r6, r4) > 0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0102, code lost:
    
        if (r18.query.comparator().compare(r6, r7) < 0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0131, code lost:
    
        if (r7 == null) goto L66;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, DocumentChanges documentChanges) {
        int i;
        boolean z;
        Document firstDocument;
        DocumentSet documentSet;
        boolean z2;
        ImmutableSortedSet<DocumentKey> remove;
        DocumentViewChangeSet documentViewChangeSet = documentChanges != null ? documentChanges.changeSet : new DocumentViewChangeSet();
        DocumentSet documentSet2 = documentChanges != null ? documentChanges.documentSet : this.documentSet;
        ImmutableSortedSet<DocumentKey> immutableSortedSet = documentChanges != null ? documentChanges.mutatedKeys : this.mutatedKeys;
        Document lastDocument = (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST) && ((long) documentSet2.size()) == this.query.getLimit()) ? documentSet2.getLastDocument() : null;
        Document firstDocument2 = (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_LAST) && ((long) documentSet2.size()) == this.query.getLimit()) ? documentSet2.getFirstDocument() : null;
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        DocumentSet documentSet3 = documentSet2;
        boolean z3 = false;
        while (it.hasNext()) {
            Map.Entry<DocumentKey, Document> next = it.next();
            DocumentKey key = next.getKey();
            Document document = documentSet2.getDocument(key);
            Document value = this.query.matches(next.getValue()) ? next.getValue() : null;
            boolean z4 = document != null && this.mutatedKeys.contains(document.getKey());
            boolean z5 = value != null && (value.hasLocalMutations() || (this.mutatedKeys.contains(value.getKey()) && value.hasCommittedMutations()));
            if (document == null || value == null) {
                documentSet = documentSet2;
                if (document != null || value == null) {
                    if (document != null && value == null) {
                        documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, document));
                        if (lastDocument == null) {
                        }
                        z3 = true;
                    }
                    z2 = false;
                } else {
                    documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.ADDED, value));
                }
                z2 = true;
            } else {
                documentSet = documentSet2;
                if (document.getData().equals(value.getData())) {
                    if (z4 != z5) {
                        documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.METADATA, value));
                        z2 = true;
                    }
                    z2 = false;
                } else {
                    if (!shouldWaitForSyncedDocument(document, value)) {
                        documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, value));
                        if (lastDocument != null) {
                        }
                        if (firstDocument2 != null) {
                        }
                        z2 = true;
                    }
                    z2 = false;
                }
            }
            if (z2) {
                if (value != null) {
                    documentSet3 = documentSet3.add(value);
                    if (value.hasLocalMutations()) {
                        remove = immutableSortedSet.insert(value.getKey());
                    } else {
                        remove = immutableSortedSet.remove(value.getKey());
                    }
                } else {
                    documentSet3 = documentSet3.remove(key);
                    remove = immutableSortedSet.remove(key);
                }
                immutableSortedSet = remove;
            }
            documentSet2 = documentSet;
        }
        if (this.query.hasLimit()) {
            long size = documentSet3.size();
            long limit = this.query.getLimit();
            while (true) {
                size -= limit;
                if (size <= 0) {
                    break;
                }
                if (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST)) {
                    firstDocument = documentSet3.getLastDocument();
                } else {
                    firstDocument = documentSet3.getFirstDocument();
                }
                documentSet3 = documentSet3.remove(firstDocument.getKey());
                immutableSortedSet = immutableSortedSet.remove(firstDocument.getKey());
                documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, firstDocument));
                limit = 1;
            }
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet2 = immutableSortedSet;
        DocumentSet documentSet4 = documentSet3;
        if (!z3 || documentChanges == null) {
            i = 0;
            z = true;
        } else {
            i = 0;
            z = false;
        }
        Assert.hardAssert(z, "View was refilled using docs that themselves needed refilling.", new Object[i]);
        return new DocumentChanges(documentSet4, documentViewChangeSet, immutableSortedSet2, z3, null);
    }

    private boolean shouldWaitForSyncedDocument(Document document, Document document2) {
        return document.hasLocalMutations() && document2.hasCommittedMutations() && !document2.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges documentChanges) {
        return applyChanges(documentChanges, null);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange) {
        Assert.hardAssert(!documentChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet documentSet = this.documentSet;
        this.documentSet = documentChanges.documentSet;
        this.mutatedKeys = documentChanges.mutatedKeys;
        List<DocumentViewChange> changes = documentChanges.changeSet.getChanges();
        Collections.sort(changes, new Comparator() { // from class: com.google.firebase.firestore.core.View$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return View.this.m791lambda$applyChanges$0$comgooglefirebasefirestorecoreView((DocumentViewChange) obj, (DocumentViewChange) obj2);
            }
        });
        applyTargetChange(targetChange);
        List<LimboDocumentChange> updateLimboDocuments = updateLimboDocuments();
        ViewSnapshot.SyncState syncState = this.limboDocuments.size() == 0 && this.current ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean z = syncState != this.syncState;
        this.syncState = syncState;
        ViewSnapshot viewSnapshot = null;
        if (changes.size() != 0 || z) {
            viewSnapshot = new ViewSnapshot(this.query, documentChanges.documentSet, documentSet, changes, syncState == ViewSnapshot.SyncState.LOCAL, documentChanges.mutatedKeys, z, false, (targetChange == null || targetChange.getResumeToken().isEmpty()) ? false : true);
        }
        return new ViewChange(viewSnapshot, updateLimboDocuments);
    }

    /* renamed from: lambda$applyChanges$0$com-google-firebase-firestore-core-View, reason: not valid java name */
    public /* synthetic */ int m791lambda$applyChanges$0$comgooglefirebasefirestorecoreView(DocumentViewChange documentViewChange, DocumentViewChange documentViewChange2) {
        int compareIntegers = Util.compareIntegers(changeTypeOrder(documentViewChange), changeTypeOrder(documentViewChange2));
        return compareIntegers != 0 ? compareIntegers : this.query.comparator().compare(documentViewChange.getDocument(), documentViewChange2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (this.current && onlineState == OnlineState.OFFLINE) {
            this.current = false;
            return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, null));
        }
        return new ViewChange(null, Collections.emptyList());
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            Iterator<DocumentKey> it2 = targetChange.getModifiedDocuments().iterator();
            while (it2.hasNext()) {
                DocumentKey next = it2.next();
                Assert.hardAssert(this.syncedDocuments.contains(next), "Modified document %s not found in view.", next);
            }
            Iterator<DocumentKey> it3 = targetChange.getRemovedDocuments().iterator();
            while (it3.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it3.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        Iterator<Document> it = this.documentSet.iterator();
        while (it.hasNext()) {
            Document next = it.next();
            if (shouldBeLimboDoc(next.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(next.getKey());
            }
        }
        ArrayList arrayList = new ArrayList(immutableSortedSet.size() + this.limboDocuments.size());
        Iterator<DocumentKey> it2 = immutableSortedSet.iterator();
        while (it2.hasNext()) {
            DocumentKey next2 = it2.next();
            if (!this.limboDocuments.contains(next2)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, next2));
            }
        }
        Iterator<DocumentKey> it3 = this.limboDocuments.iterator();
        while (it3.hasNext()) {
            DocumentKey next3 = it3.next();
            if (!immutableSortedSet.contains(next3)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, next3));
            }
        }
        return arrayList;
    }

    private boolean shouldBeLimboDoc(DocumentKey documentKey) {
        Document document;
        return (this.syncedDocuments.contains(documentKey) || (document = this.documentSet.getDocument(documentKey)) == null || document.hasLocalMutations()) ? false : true;
    }

    ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.core.View$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.METADATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int changeTypeOrder(DocumentViewChange documentViewChange) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[documentViewChange.getType().ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2 && i != 3) {
                if (i == 4) {
                    return 0;
                }
                throw new IllegalArgumentException("Unknown change type: " + documentViewChange.getType());
            }
        }
        return i2;
    }
}
