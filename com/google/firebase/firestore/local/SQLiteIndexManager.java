package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.index.FirestoreIndexValueWriter;
import com.google.firebase.firestore.index.IndexByteEncoder;
import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.TargetIndexMatcher;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.LogicUtils;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.admin.v1.Index;
import com.google.firestore.v1.Value;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class SQLiteIndexManager implements IndexManager {
    private static final byte[] EMPTY_BYTES_VALUE = new byte[0];
    private static final String TAG = "SQLiteIndexManager";
    private final SQLitePersistence db;
    private final LocalSerializer serializer;
    private final String uid;
    private final Map<Target, List<Target>> targetToDnfSubTargets = new HashMap();
    private final MemoryIndexManager.MemoryCollectionParentIndex collectionParentsCache = new MemoryIndexManager.MemoryCollectionParentIndex();
    private final Map<String, Map<Integer, FieldIndex>> memoizedIndexes = new HashMap();
    private final Queue<FieldIndex> nextIndexToUpdate = new PriorityQueue(10, new Comparator() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda7
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return SQLiteIndexManager.lambda$new$0((FieldIndex) obj, (FieldIndex) obj2);
        }
    });
    private boolean started = false;
    private int memoizedMaxIndexId = -1;
    private long memoizedMaxSequenceNumber = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(FieldIndex fieldIndex, FieldIndex fieldIndex2) {
        int compare = Long.compare(fieldIndex.getIndexState().getSequenceNumber(), fieldIndex2.getIndexState().getSequenceNumber());
        return compare == 0 ? fieldIndex.getCollectionGroup().compareTo(fieldIndex2.getCollectionGroup()) : compare;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteIndexManager(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void start() {
        final HashMap hashMap = new HashMap();
        this.db.query("SELECT index_id, sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id FROM index_state WHERE uid = ?").binding(this.uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                hashMap.put(Integer.valueOf(r2.getInt(0)), FieldIndex.IndexState.create(r2.getLong(1), new SnapshotVersion(new Timestamp(r2.getLong(2), r2.getInt(3))), DocumentKey.fromPath(EncodedPath.decodeResourcePath(r2.getString(4))), ((Cursor) obj).getInt(5)));
            }
        });
        this.db.query("SELECT index_id, collection_group, index_proto FROM index_configuration").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteIndexManager.this.m821x30c0807a(hashMap, (Cursor) obj);
            }
        });
        this.started = true;
    }

    /* renamed from: lambda$start$2$com-google-firebase-firestore-local-SQLiteIndexManager, reason: not valid java name */
    public /* synthetic */ void m821x30c0807a(Map map, Cursor cursor) {
        FieldIndex.IndexState indexState;
        try {
            int i = cursor.getInt(0);
            String string = cursor.getString(1);
            List<FieldIndex.Segment> decodeFieldIndexSegments = this.serializer.decodeFieldIndexSegments(Index.parseFrom(cursor.getBlob(2)));
            if (map.containsKey(Integer.valueOf(i))) {
                indexState = (FieldIndex.IndexState) map.get(Integer.valueOf(i));
            } else {
                indexState = FieldIndex.INITIAL_STATE;
            }
            memoizeIndex(FieldIndex.create(i, string, decodeFieldIndexSegments, indexState));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Failed to decode index: " + e, new Object[0]);
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addToCollectionParentIndex(ResourcePath resourcePath) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Assert.hardAssert(resourcePath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
        if (this.collectionParentsCache.add(resourcePath)) {
            this.db.execute("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)", resourcePath.getLastSegment(), EncodedPath.encode(resourcePath.popLast()));
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public List<ResourcePath> getCollectionParents(String str) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT parent FROM collection_parents WHERE collection_id = ?").binding(str).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                arrayList.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
            }
        });
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addFieldIndex(FieldIndex fieldIndex) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        int i = this.memoizedMaxIndexId + 1;
        FieldIndex create = FieldIndex.create(i, fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), fieldIndex.getIndexState());
        this.db.execute("INSERT INTO index_configuration (index_id, collection_group, index_proto) VALUES(?, ?, ?)", Integer.valueOf(i), create.getCollectionGroup(), encodeSegments(create));
        memoizeIndex(create);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void deleteFieldIndex(FieldIndex fieldIndex) {
        this.db.execute("DELETE FROM index_configuration WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.db.execute("DELETE FROM index_entries WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.db.execute("DELETE FROM index_state WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.nextIndexToUpdate.remove(fieldIndex);
        Map<Integer, FieldIndex> map = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (map != null) {
            map.remove(Integer.valueOf(fieldIndex.getIndexId()));
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void deleteAllFieldIndexes() {
        this.db.execute("DELETE FROM index_configuration", new Object[0]);
        this.db.execute("DELETE FROM index_entries", new Object[0]);
        this.db.execute("DELETE FROM index_state", new Object[0]);
        this.nextIndexToUpdate.clear();
        this.memoizedIndexes.clear();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void createTargetIndexes(Target target) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        for (Target target2 : getSubTargets(target)) {
            IndexManager.IndexType indexType = getIndexType(target2);
            if (indexType == IndexManager.IndexType.NONE || indexType == IndexManager.IndexType.PARTIAL) {
                addFieldIndex(new TargetIndexMatcher(target2).buildTargetIndex());
            }
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public String getNextCollectionGroupToUpdate() {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        FieldIndex peek = this.nextIndexToUpdate.peek();
        if (peek != null) {
            return peek.getCollectionGroup();
        }
        return null;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Map.Entry<DocumentKey, Document> next = it.next();
            for (FieldIndex fieldIndex : getFieldIndexes(next.getKey().getCollectionGroup())) {
                SortedSet<IndexEntry> existingIndexEntries = getExistingIndexEntries(next.getKey(), fieldIndex);
                SortedSet<IndexEntry> computeIndexEntries = computeIndexEntries(next.getValue(), fieldIndex);
                if (!existingIndexEntries.equals(computeIndexEntries)) {
                    updateEntries(next.getValue(), existingIndexEntries, computeIndexEntries);
                }
            }
        }
    }

    private void updateEntries(final Document document, SortedSet<IndexEntry> sortedSet, SortedSet<IndexEntry> sortedSet2) {
        Logger.debug(TAG, "Updating index entries for document '%s'", document.getKey());
        Util.diffCollections(sortedSet, sortedSet2, new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteIndexManager.this.m822x55deb5b3(document, (IndexEntry) obj);
            }
        }, new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteIndexManager.this.m823x57150892(document, (IndexEntry) obj);
            }
        });
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes(String str) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Map<Integer, FieldIndex> map = this.memoizedIndexes.get(str);
        return map == null ? Collections.emptyList() : map.values();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map<Integer, FieldIndex>> it = this.memoizedIndexes.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().values());
        }
        return arrayList;
    }

    private FieldIndex.IndexOffset getMinOffset(Collection<FieldIndex> collection) {
        Assert.hardAssert(!collection.isEmpty(), "Found empty index group when looking for least recent index offset.", new Object[0]);
        Iterator<FieldIndex> it = collection.iterator();
        FieldIndex.IndexOffset offset = it.next().getIndexState().getOffset();
        int largestBatchId = offset.getLargestBatchId();
        while (it.hasNext()) {
            FieldIndex.IndexOffset offset2 = it.next().getIndexState().getOffset();
            if (offset2.compareTo(offset) < 0) {
                offset = offset2;
            }
            largestBatchId = Math.max(offset2.getLargestBatchId(), largestBatchId);
        }
        return FieldIndex.IndexOffset.create(offset.getReadTime(), offset.getDocumentKey(), largestBatchId);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public FieldIndex.IndexOffset getMinOffset(String str) {
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(str);
        Assert.hardAssert(!fieldIndexes.isEmpty(), "minOffset was called for collection without indexes", new Object[0]);
        return getMinOffset(fieldIndexes);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public IndexManager.IndexType getIndexType(Target target) {
        IndexManager.IndexType indexType = IndexManager.IndexType.FULL;
        List<Target> subTargets = getSubTargets(target);
        Iterator<Target> it = subTargets.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Target next = it.next();
            FieldIndex fieldIndex = getFieldIndex(next);
            if (fieldIndex == null) {
                indexType = IndexManager.IndexType.NONE;
                break;
            }
            if (fieldIndex.getSegments().size() < next.getSegmentCount()) {
                indexType = IndexManager.IndexType.PARTIAL;
            }
        }
        return (target.hasLimit() && subTargets.size() > 1 && indexType == IndexManager.IndexType.FULL) ? IndexManager.IndexType.PARTIAL : indexType;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public FieldIndex.IndexOffset getMinOffset(Target target) {
        ArrayList arrayList = new ArrayList();
        Iterator<Target> it = getSubTargets(target).iterator();
        while (it.hasNext()) {
            FieldIndex fieldIndex = getFieldIndex(it.next());
            if (fieldIndex != null) {
                arrayList.add(fieldIndex);
            }
        }
        return getMinOffset(arrayList);
    }

    private List<Target> getSubTargets(Target target) {
        if (this.targetToDnfSubTargets.containsKey(target)) {
            return this.targetToDnfSubTargets.get(target);
        }
        ArrayList arrayList = new ArrayList();
        if (target.getFilters().isEmpty()) {
            arrayList.add(target);
        } else {
            Iterator<Filter> it = LogicUtils.getDnfTerms(new CompositeFilter(target.getFilters(), CompositeFilter.Operator.AND)).iterator();
            while (it.hasNext()) {
                arrayList.add(new Target(target.getPath(), target.getCollectionGroup(), it.next().getFilters(), target.getOrderBy(), target.getLimit(), target.getStartAt(), target.getEndAt()));
            }
        }
        this.targetToDnfSubTargets.put(target, arrayList);
        return arrayList;
    }

    private void memoizeIndex(FieldIndex fieldIndex) {
        Map<Integer, FieldIndex> map = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (map == null) {
            map = new HashMap<>();
            this.memoizedIndexes.put(fieldIndex.getCollectionGroup(), map);
        }
        FieldIndex fieldIndex2 = map.get(Integer.valueOf(fieldIndex.getIndexId()));
        if (fieldIndex2 != null) {
            this.nextIndexToUpdate.remove(fieldIndex2);
        }
        map.put(Integer.valueOf(fieldIndex.getIndexId()), fieldIndex);
        this.nextIndexToUpdate.add(fieldIndex);
        this.memoizedMaxIndexId = Math.max(this.memoizedMaxIndexId, fieldIndex.getIndexId());
        this.memoizedMaxSequenceNumber = Math.max(this.memoizedMaxSequenceNumber, fieldIndex.getIndexState().getSequenceNumber());
    }

    private SortedSet<IndexEntry> computeIndexEntries(Document document, FieldIndex fieldIndex) {
        TreeSet treeSet = new TreeSet();
        byte[] encodeDirectionalElements = encodeDirectionalElements(fieldIndex, document);
        if (encodeDirectionalElements == null) {
            return treeSet;
        }
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment != null) {
            Value field = document.getField(arraySegment.getFieldPath());
            if (Values.isArray(field)) {
                Iterator<Value> it = field.getArrayValue().getValuesList().iterator();
                while (it.hasNext()) {
                    treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), encodeSingleElement(it.next()), encodeDirectionalElements));
                }
            }
        } else {
            treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), new byte[0], encodeDirectionalElements));
        }
        return treeSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addIndexEntry, reason: merged with bridge method [inline-methods] */
    public void m822x55deb5b3(Document document, IndexEntry indexEntry) {
        this.db.execute("INSERT INTO index_entries (index_id, uid, array_value, directional_value, document_key) VALUES(?, ?, ?, ?, ?)", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deleteIndexEntry, reason: merged with bridge method [inline-methods] */
    public void m823x57150892(Document document, IndexEntry indexEntry) {
        this.db.execute("DELETE FROM index_entries WHERE index_id = ? AND uid = ? AND array_value = ? AND directional_value = ? AND document_key = ?", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    private SortedSet<IndexEntry> getExistingIndexEntries(final DocumentKey documentKey, final FieldIndex fieldIndex) {
        final TreeSet treeSet = new TreeSet();
        this.db.query("SELECT array_value, directional_value FROM index_entries WHERE index_id = ? AND document_key = ? AND uid = ?").binding(Integer.valueOf(fieldIndex.getIndexId()), documentKey.toString(), this.uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda6
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), documentKey, r4.getBlob(0), ((Cursor) obj).getBlob(1)));
            }
        });
        return treeSet;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public List<DocumentKey> getDocumentsMatchingTarget(Target target) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<Pair> arrayList3 = new ArrayList();
        for (Target target2 : getSubTargets(target)) {
            FieldIndex fieldIndex = getFieldIndex(target2);
            if (fieldIndex == null) {
                return null;
            }
            arrayList3.add(Pair.create(target2, fieldIndex));
        }
        for (Pair pair : arrayList3) {
            Target target3 = (Target) pair.first;
            FieldIndex fieldIndex2 = (FieldIndex) pair.second;
            List<Value> arrayValues = target3.getArrayValues(fieldIndex2);
            Collection<Value> notInValues = target3.getNotInValues(fieldIndex2);
            Bound lowerBound = target3.getLowerBound(fieldIndex2);
            Bound upperBound = target3.getUpperBound(fieldIndex2);
            if (Logger.isDebugEnabled()) {
                Logger.debug(TAG, "Using index '%s' to execute '%s' (Arrays: %s, Lower bound: %s, Upper bound: %s)", fieldIndex2, target3, arrayValues, lowerBound, upperBound);
            }
            Object[] generateQueryAndBindings = generateQueryAndBindings(target3, fieldIndex2.getIndexId(), arrayValues, encodeBound(fieldIndex2, target3, lowerBound), lowerBound.isInclusive() ? ">=" : ">", encodeBound(fieldIndex2, target3, upperBound), upperBound.isInclusive() ? "<=" : "<", encodeValues(fieldIndex2, target3, notInValues));
            arrayList.add(String.valueOf(generateQueryAndBindings[0]));
            arrayList2.addAll(Arrays.asList(generateQueryAndBindings).subList(1, generateQueryAndBindings.length));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(TextUtils.join(" UNION ", arrayList));
        sb.append("ORDER BY directional_value, document_key ");
        sb.append(target.getKeyOrder().equals(OrderBy.Direction.ASCENDING) ? "asc " : "desc ");
        String str = "SELECT DISTINCT document_key FROM (" + sb.toString() + ")";
        if (target.hasLimit()) {
            str = str + " LIMIT " + target.getLimit();
        }
        Assert.hardAssert(arrayList2.size() < 1000, "Cannot perform query with more than 999 bind elements", new Object[0]);
        SQLitePersistence.Query binding = this.db.query(str).binding(arrayList2.toArray());
        final ArrayList arrayList4 = new ArrayList();
        binding.forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteIndexManager$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                arrayList4.add(DocumentKey.fromPath(ResourcePath.fromString(((Cursor) obj).getString(0))));
            }
        });
        Logger.debug(TAG, "Index scan returned %s documents", Integer.valueOf(arrayList4.size()));
        return arrayList4;
    }

    private Object[] generateQueryAndBindings(Target target, int i, List<Value> list, Object[] objArr, String str, Object[] objArr2, String str2, Object[] objArr3) {
        StringBuilder sb;
        int max = Math.max(objArr.length, objArr2.length) * (list != null ? list.size() : 1);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT document_key, directional_value FROM index_entries ");
        sb2.append("WHERE index_id = ? AND uid = ? ");
        sb2.append("AND array_value = ? ");
        sb2.append("AND directional_value ");
        sb2.append(str);
        sb2.append(" ? ");
        sb2.append("AND directional_value ");
        sb2.append(str2);
        sb2.append(" ? ");
        CharSequence repeatSequence = Util.repeatSequence(sb2, max, " UNION ");
        if (objArr3 != null) {
            StringBuilder sb3 = new StringBuilder("SELECT document_key, directional_value FROM (");
            sb3.append(repeatSequence);
            sb3.append(") WHERE directional_value NOT IN (");
            sb3.append((CharSequence) Util.repeatSequence("?", objArr3.length, ", "));
            sb3.append(")");
            sb = sb3;
        } else {
            sb = repeatSequence;
        }
        Object[] fillBounds = fillBounds(max, i, list, objArr, objArr2, objArr3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(sb.toString());
        arrayList.addAll(Arrays.asList(fillBounds));
        return arrayList.toArray();
    }

    private Object[] fillBounds(int i, int i2, List<Value> list, Object[] objArr, Object[] objArr2, Object[] objArr3) {
        byte[] bArr;
        int size = i / (list != null ? list.size() : 1);
        int i3 = 0;
        Object[] objArr4 = new Object[(i * 5) + (objArr3 != null ? objArr3.length : 0)];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = i5 + 1;
            objArr4[i5] = Integer.valueOf(i2);
            int i7 = i6 + 1;
            objArr4[i6] = this.uid;
            int i8 = i7 + 1;
            if (list != null) {
                bArr = encodeSingleElement(list.get(i4 / size));
            } else {
                bArr = EMPTY_BYTES_VALUE;
            }
            objArr4[i7] = bArr;
            int i9 = i8 + 1;
            int i10 = i4 % size;
            objArr4[i8] = objArr[i10];
            objArr4[i9] = objArr2[i10];
            i4++;
            i5 = i9 + 1;
        }
        if (objArr3 != null) {
            int length = objArr3.length;
            while (i3 < length) {
                objArr4[i5] = objArr3[i3];
                i3++;
                i5++;
            }
        }
        return objArr4;
    }

    private FieldIndex getFieldIndex(Target target) {
        String lastSegment;
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        TargetIndexMatcher targetIndexMatcher = new TargetIndexMatcher(target);
        if (target.getCollectionGroup() != null) {
            lastSegment = target.getCollectionGroup();
        } else {
            lastSegment = target.getPath().getLastSegment();
        }
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(lastSegment);
        FieldIndex fieldIndex = null;
        if (fieldIndexes.isEmpty()) {
            return null;
        }
        for (FieldIndex fieldIndex2 : fieldIndexes) {
            if (targetIndexMatcher.servedByIndex(fieldIndex2) && (fieldIndex == null || fieldIndex2.getSegments().size() > fieldIndex.getSegments().size())) {
                fieldIndex = fieldIndex2;
            }
        }
        return fieldIndex;
    }

    private byte[] encodeDirectionalElements(FieldIndex fieldIndex, Document document) {
        IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value field = document.getField(segment.getFieldPath());
            if (field == null) {
                return null;
            }
            FirestoreIndexValueWriter.INSTANCE.writeIndexValue(field, indexByteEncoder.forKind(segment.getKind()));
        }
        return indexByteEncoder.getEncodedBytes();
    }

    private byte[] encodeSingleElement(Value value) {
        IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
        FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, indexByteEncoder.forKind(FieldIndex.Segment.Kind.ASCENDING));
        return indexByteEncoder.getEncodedBytes();
    }

    private Object[] encodeValues(FieldIndex fieldIndex, Target target, Collection<Value> collection) {
        if (collection == null) {
            return null;
        }
        List<IndexByteEncoder> arrayList = new ArrayList<>();
        arrayList.add(new IndexByteEncoder());
        Iterator<Value> it = collection.iterator();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value next = it.next();
            for (IndexByteEncoder indexByteEncoder : arrayList) {
                if (isInFilter(target, segment.getFieldPath()) && Values.isArray(next)) {
                    arrayList = expandIndexValues(arrayList, segment, next);
                } else {
                    FirestoreIndexValueWriter.INSTANCE.writeIndexValue(next, indexByteEncoder.forKind(segment.getKind()));
                }
            }
        }
        return getEncodedBytes(arrayList);
    }

    private Object[] encodeBound(FieldIndex fieldIndex, Target target, Bound bound) {
        return encodeValues(fieldIndex, target, bound.getPosition());
    }

    private Object[] getEncodedBytes(List<IndexByteEncoder> list) {
        Object[] objArr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objArr[i] = list.get(i).getEncodedBytes();
        }
        return objArr;
    }

    private List<IndexByteEncoder> expandIndexValues(List<IndexByteEncoder> list, FieldIndex.Segment segment, Value value) {
        ArrayList<IndexByteEncoder> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        for (Value value2 : value.getArrayValue().getValuesList()) {
            for (IndexByteEncoder indexByteEncoder : arrayList) {
                IndexByteEncoder indexByteEncoder2 = new IndexByteEncoder();
                indexByteEncoder2.seed(indexByteEncoder.getEncodedBytes());
                FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value2, indexByteEncoder2.forKind(segment.getKind()));
                arrayList2.add(indexByteEncoder2);
            }
        }
        return arrayList2;
    }

    private boolean isInFilter(Target target, FieldPath fieldPath) {
        for (Filter filter : target.getFilters()) {
            if (filter instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) filter;
                if (fieldFilter.getField().equals(fieldPath)) {
                    FieldFilter.Operator operator = fieldFilter.getOperator();
                    if (operator.equals(FieldFilter.Operator.IN) || operator.equals(FieldFilter.Operator.NOT_IN)) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    private byte[] encodeSegments(FieldIndex fieldIndex) {
        return this.serializer.encodeFieldIndexSegments(fieldIndex.getSegments()).toByteArray();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        this.memoizedMaxSequenceNumber++;
        for (FieldIndex fieldIndex : getFieldIndexes(str)) {
            FieldIndex create = FieldIndex.create(fieldIndex.getIndexId(), fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), FieldIndex.IndexState.create(this.memoizedMaxSequenceNumber, indexOffset));
            this.db.execute("REPLACE INTO index_state (index_id, uid,  sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id) VALUES(?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(fieldIndex.getIndexId()), this.uid, Long.valueOf(this.memoizedMaxSequenceNumber), Long.valueOf(indexOffset.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(indexOffset.getReadTime().getTimestamp().getNanoseconds()), EncodedPath.encode(indexOffset.getDocumentKey().getPath()), Integer.valueOf(indexOffset.getLargestBatchId()));
            memoizeIndex(create);
        }
    }
}
