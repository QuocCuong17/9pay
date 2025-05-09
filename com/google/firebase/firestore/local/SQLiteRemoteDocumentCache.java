package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {
    static final int BINDS_PER_STATEMENT = 9;
    private final SQLitePersistence db;
    private IndexManager indexManager;
    private final LocalSerializer serializer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteRemoteDocumentCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        DocumentKey key = mutableDocument.getKey();
        Timestamp timestamp = snapshotVersion.getTimestamp();
        this.db.execute("INSERT OR REPLACE INTO remote_documents (path, path_length, read_time_seconds, read_time_nanos, contents) VALUES (?, ?, ?, ?, ?)", EncodedPath.encode(key.getPath()), Integer.valueOf(key.getPath().length()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), this.serializer.encodeMaybeDocument(mutableDocument).toByteArray());
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> collection) {
        if (collection.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (DocumentKey documentKey : collection) {
            arrayList.add(EncodedPath.encode(documentKey.getPath()));
            emptyDocumentMap = emptyDocumentMap.insert(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "DELETE FROM remote_documents WHERE path IN (", arrayList, ")");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.executeNextSubquery();
        }
        this.indexManager.updateIndexEntries(emptyDocumentMap);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey documentKey) {
        return getAll(Collections.singletonList(documentKey)).get(documentKey);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        final HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (DocumentKey documentKey : iterable) {
            arrayList.add(EncodedPath.encode(documentKey.getPath()));
            hashMap.put(documentKey, MutableDocument.newInvalidDocument(documentKey));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT contents, read_time_seconds, read_time_nanos FROM remote_documents WHERE path IN (", arrayList, ") ORDER BY path");
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda0
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    SQLiteRemoteDocumentCache.this.m834xca14d1c3(backgroundQueue, hashMap, (Cursor) obj);
                }
            });
        }
        backgroundQueue.drain();
        return hashMap;
    }

    /* renamed from: lambda$getAll$0$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    public /* synthetic */ void m834xca14d1c3(BackgroundQueue backgroundQueue, Map map, Cursor cursor) {
        processRowInBackground(backgroundQueue, map, cursor, null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        List<ResourcePath> collectionParents = this.indexManager.getCollectionParents(str);
        ArrayList arrayList = new ArrayList(collectionParents.size());
        Iterator<ResourcePath> it = collectionParents.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().append(str));
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyMap();
        }
        if (arrayList.size() * 9 < 900) {
            return getAll(arrayList, indexOffset, i, null);
        }
        HashMap hashMap = new HashMap();
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int i3 = i2 + 100;
            hashMap.putAll(getAll(arrayList.subList(i2, Math.min(arrayList.size(), i3)), indexOffset, i, null));
            i2 = i3;
        }
        return Util.firstNEntries(hashMap, i, FieldIndex.IndexOffset.DOCUMENT_COMPARATOR);
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i, @Nullable final Function<MutableDocument, Boolean> function, @Nullable final QueryContext queryContext) {
        Timestamp timestamp = indexOffset.getReadTime().getTimestamp();
        DocumentKey documentKey = indexOffset.getDocumentKey();
        StringBuilder repeatSequence = Util.repeatSequence("SELECT contents, read_time_seconds, read_time_nanos, path FROM remote_documents WHERE path >= ? AND path < ? AND path_length = ? AND (read_time_seconds > ? OR ( read_time_seconds = ? AND read_time_nanos > ?) OR ( read_time_seconds = ? AND read_time_nanos = ? and path > ?)) ", list.size(), " UNION ");
        repeatSequence.append("ORDER BY read_time_seconds, read_time_nanos, path LIMIT ?");
        Object[] objArr = new Object[(list.size() * 9) + 1];
        int i2 = 0;
        for (ResourcePath resourcePath : list) {
            String encode = EncodedPath.encode(resourcePath);
            int i3 = i2 + 1;
            objArr[i2] = encode;
            int i4 = i3 + 1;
            objArr[i3] = EncodedPath.prefixSuccessor(encode);
            int i5 = i4 + 1;
            objArr[i4] = Integer.valueOf(resourcePath.length() + 1);
            int i6 = i5 + 1;
            objArr[i5] = Long.valueOf(timestamp.getSeconds());
            int i7 = i6 + 1;
            objArr[i6] = Long.valueOf(timestamp.getSeconds());
            int i8 = i7 + 1;
            objArr[i7] = Integer.valueOf(timestamp.getNanoseconds());
            int i9 = i8 + 1;
            objArr[i8] = Long.valueOf(timestamp.getSeconds());
            int i10 = i9 + 1;
            objArr[i9] = Integer.valueOf(timestamp.getNanoseconds());
            objArr[i10] = EncodedPath.encode(documentKey.getPath());
            i2 = i10 + 1;
        }
        objArr[i2] = Integer.valueOf(i);
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        final HashMap hashMap = new HashMap();
        this.db.query(repeatSequence.toString()).binding(objArr).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteRemoteDocumentCache.this.m835xbda45604(backgroundQueue, hashMap, function, queryContext, (Cursor) obj);
            }
        });
        backgroundQueue.drain();
        return hashMap;
    }

    /* renamed from: lambda$getAll$1$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    public /* synthetic */ void m835xbda45604(BackgroundQueue backgroundQueue, Map map, Function function, QueryContext queryContext, Cursor cursor) {
        processRowInBackground(backgroundQueue, map, cursor, function);
        if (queryContext != null) {
            queryContext.incrementDocumentReadCount();
        }
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i, @Nullable Function<MutableDocument, Boolean> function) {
        return getAll(list, indexOffset, i, function, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.util.concurrent.Executor] */
    private void processRowInBackground(BackgroundQueue backgroundQueue, final Map<DocumentKey, MutableDocument> map, Cursor cursor, @Nullable final Function<MutableDocument, Boolean> function) {
        final byte[] blob = cursor.getBlob(0);
        final int i = cursor.getInt(1);
        final int i2 = cursor.getInt(2);
        BackgroundQueue backgroundQueue2 = backgroundQueue;
        if (cursor.isLast()) {
            backgroundQueue2 = Executors.DIRECT_EXECUTOR;
        }
        backgroundQueue2.execute(new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                SQLiteRemoteDocumentCache.this.m836x16ef698(blob, i, i2, function, map);
            }
        });
    }

    /* renamed from: lambda$processRowInBackground$2$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    public /* synthetic */ void m836x16ef698(byte[] bArr, int i, int i2, Function function, Map map) {
        MutableDocument decodeMaybeDocument = decodeMaybeDocument(bArr, i, i2);
        if (function == null || ((Boolean) function.apply(decodeMaybeDocument)).booleanValue()) {
            synchronized (map) {
                map.put(decodeMaybeDocument.getKey(), decodeMaybeDocument);
            }
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set) {
        return getDocumentsMatchingQuery(query, indexOffset, set, null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(final Query query, FieldIndex.IndexOffset indexOffset, @Nonnull final Set<DocumentKey> set, @Nullable QueryContext queryContext) {
        return getAll(Collections.singletonList(query.getPath()), indexOffset, Integer.MAX_VALUE, new Function() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                Boolean valueOf;
                Query query2 = Query.this;
                Set set2 = set;
                valueOf = Boolean.valueOf(r0.matches(r2) || r1.contains(r2.getKey()));
                return valueOf;
            }
        }, queryContext);
    }

    private MutableDocument decodeMaybeDocument(byte[] bArr, int i, int i2) {
        try {
            return this.serializer.decodeMaybeDocument(MaybeDocument.parseFrom(bArr)).setReadTime(new SnapshotVersion(new Timestamp(i, i2)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }
}
