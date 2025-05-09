package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class DocumentSet implements Iterable<Document> {
    private final ImmutableSortedMap<DocumentKey, Document> keyIndex;
    private final ImmutableSortedSet<Document> sortedSet;

    public static DocumentSet emptySet(final Comparator<Document> comparator) {
        return new DocumentSet(DocumentCollections.emptyDocumentMap(), new ImmutableSortedSet(Collections.emptyList(), new Comparator() { // from class: com.google.firebase.firestore.model.DocumentSet$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DocumentSet.lambda$emptySet$0(comparator, (Document) obj, (Document) obj2);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$emptySet$0(Comparator comparator, Document document, Document document2) {
        int compare = comparator.compare(document, document2);
        return compare == 0 ? Document.KEY_COMPARATOR.compare(document, document2) : compare;
    }

    private DocumentSet(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, ImmutableSortedSet<Document> immutableSortedSet) {
        this.keyIndex = immutableSortedMap;
        this.sortedSet = immutableSortedSet;
    }

    public int size() {
        return this.keyIndex.size();
    }

    public boolean isEmpty() {
        return this.keyIndex.isEmpty();
    }

    public boolean contains(DocumentKey documentKey) {
        return this.keyIndex.containsKey(documentKey);
    }

    public Document getDocument(DocumentKey documentKey) {
        return this.keyIndex.get(documentKey);
    }

    public Document getFirstDocument() {
        return this.sortedSet.getMinEntry();
    }

    public Document getLastDocument() {
        return this.sortedSet.getMaxEntry();
    }

    public Document getPredecessor(DocumentKey documentKey) {
        Document document = this.keyIndex.get(documentKey);
        if (document == null) {
            throw new IllegalArgumentException("Key not contained in DocumentSet: " + documentKey);
        }
        return this.sortedSet.getPredecessorEntry(document);
    }

    public int indexOf(DocumentKey documentKey) {
        Document document = this.keyIndex.get(documentKey);
        if (document == null) {
            return -1;
        }
        return this.sortedSet.indexOf(document);
    }

    public DocumentSet add(Document document) {
        DocumentSet remove = remove(document.getKey());
        return new DocumentSet(remove.keyIndex.insert(document.getKey(), document), remove.sortedSet.insert(document));
    }

    public DocumentSet remove(DocumentKey documentKey) {
        Document document = this.keyIndex.get(documentKey);
        return document == null ? this : new DocumentSet(this.keyIndex.remove(documentKey), this.sortedSet.remove(document));
    }

    public List<Document> toList() {
        ArrayList arrayList = new ArrayList(size());
        Iterator<Document> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    @Override // java.lang.Iterable
    public Iterator<Document> iterator() {
        return this.sortedSet.iterator();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DocumentSet documentSet = (DocumentSet) obj;
        if (size() != documentSet.size()) {
            return false;
        }
        Iterator<Document> it = iterator();
        Iterator<Document> it2 = documentSet.iterator();
        while (it.hasNext()) {
            if (!it.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        Iterator<Document> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Document next = it.next();
            i = (((i * 31) + next.getKey().hashCode()) * 31) + next.getData().hashCode();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<Document> it = iterator();
        boolean z = true;
        while (it.hasNext()) {
            Document next = it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(next);
        }
        sb.append("]");
        return sb.toString();
    }
}
