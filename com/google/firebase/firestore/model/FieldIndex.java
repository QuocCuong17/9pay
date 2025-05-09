package com.google.firebase.firestore.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.FieldIndex;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class FieldIndex {
    public static final int INITIAL_LARGEST_BATCH_ID = -1;
    public static final int INITIAL_SEQUENCE_NUMBER = 0;
    public static IndexState INITIAL_STATE = IndexState.create(0, IndexOffset.NONE);
    public static final Comparator<FieldIndex> SEMANTIC_COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.model.FieldIndex$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FieldIndex.lambda$static$0((FieldIndex) obj, (FieldIndex) obj2);
        }
    };
    public static final int UNKNOWN_ID = -1;

    public abstract String getCollectionGroup();

    public abstract int getIndexId();

    public abstract IndexState getIndexState();

    public abstract List<Segment> getSegments();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(FieldIndex fieldIndex, FieldIndex fieldIndex2) {
        int compareTo = fieldIndex.getCollectionGroup().compareTo(fieldIndex2.getCollectionGroup());
        if (compareTo != 0) {
            return compareTo;
        }
        Iterator<Segment> it = fieldIndex.getSegments().iterator();
        Iterator<Segment> it2 = fieldIndex2.getSegments().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compareTo2 = it.next().compareTo(it2.next());
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return Boolean.compare(it.hasNext(), it2.hasNext());
    }

    /* loaded from: classes4.dex */
    public static abstract class Segment implements Comparable<Segment> {

        /* loaded from: classes4.dex */
        public enum Kind {
            ASCENDING,
            DESCENDING,
            CONTAINS
        }

        public abstract FieldPath getFieldPath();

        public abstract Kind getKind();

        public static Segment create(FieldPath fieldPath, Kind kind) {
            return new AutoValue_FieldIndex_Segment(fieldPath, kind);
        }

        @Override // java.lang.Comparable
        public int compareTo(Segment segment) {
            int compareTo = getFieldPath().compareTo(segment.getFieldPath());
            return compareTo != 0 ? compareTo : getKind().compareTo(segment.getKind());
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class IndexState {
        public abstract IndexOffset getOffset();

        public abstract long getSequenceNumber();

        public static IndexState create(long j, IndexOffset indexOffset) {
            return new AutoValue_FieldIndex_IndexState(j, indexOffset);
        }

        public static IndexState create(long j, SnapshotVersion snapshotVersion, DocumentKey documentKey, int i) {
            return create(j, IndexOffset.create(snapshotVersion, documentKey, i));
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class IndexOffset implements Comparable<IndexOffset> {
        public static final IndexOffset NONE = create(SnapshotVersion.NONE, DocumentKey.empty(), -1);
        public static final Comparator<MutableDocument> DOCUMENT_COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.model.FieldIndex$IndexOffset$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = FieldIndex.IndexOffset.fromDocument((MutableDocument) obj).compareTo(FieldIndex.IndexOffset.fromDocument((MutableDocument) obj2));
                return compareTo;
            }
        };

        public abstract DocumentKey getDocumentKey();

        public abstract int getLargestBatchId();

        public abstract SnapshotVersion getReadTime();

        public static IndexOffset create(SnapshotVersion snapshotVersion, DocumentKey documentKey, int i) {
            return new AutoValue_FieldIndex_IndexOffset(snapshotVersion, documentKey, i);
        }

        public static IndexOffset createSuccessor(SnapshotVersion snapshotVersion, int i) {
            Timestamp timestamp;
            long seconds = snapshotVersion.getTimestamp().getSeconds();
            int nanoseconds = snapshotVersion.getTimestamp().getNanoseconds() + 1;
            if (nanoseconds == 1.0E9d) {
                timestamp = new Timestamp(seconds + 1, 0);
            } else {
                timestamp = new Timestamp(seconds, nanoseconds);
            }
            return create(new SnapshotVersion(timestamp), DocumentKey.empty(), i);
        }

        public static IndexOffset fromDocument(Document document) {
            return create(document.getReadTime(), document.getKey(), -1);
        }

        @Override // java.lang.Comparable
        public int compareTo(IndexOffset indexOffset) {
            int compareTo = getReadTime().compareTo(indexOffset.getReadTime());
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = getDocumentKey().compareTo(indexOffset.getDocumentKey());
            return compareTo2 != 0 ? compareTo2 : Integer.compare(getLargestBatchId(), indexOffset.getLargestBatchId());
        }
    }

    public static FieldIndex create(int i, String str, List<Segment> list, IndexState indexState) {
        return new AutoValue_FieldIndex(i, str, list, indexState);
    }

    public List<Segment> getDirectionalSegments() {
        ArrayList arrayList = new ArrayList();
        for (Segment segment : getSegments()) {
            if (!segment.getKind().equals(Segment.Kind.CONTAINS)) {
                arrayList.add(segment);
            }
        }
        return arrayList;
    }

    public Segment getArraySegment() {
        for (Segment segment : getSegments()) {
            if (segment.getKind().equals(Segment.Kind.CONTAINS)) {
                return segment;
            }
        }
        return null;
    }
}
