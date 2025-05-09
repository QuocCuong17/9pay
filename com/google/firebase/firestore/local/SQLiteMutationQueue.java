package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class SQLiteMutationQueue implements MutationQueue {
    private static final int BLOB_MAX_INLINE_LENGTH = 1000000;
    private final SQLitePersistence db;
    private final IndexManager indexManager;
    private ByteString lastStreamToken;
    private int nextBatchId;
    private final LocalSerializer serializer;
    private final String uid;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteMutationQueue(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user, IndexManager indexManager) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
        this.lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;
        this.indexManager = indexManager;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void start() {
        loadNextBatchIdAcrossAllUsers();
        if (this.db.query("SELECT last_stream_token FROM mutation_queues WHERE uid = ?").binding(this.uid).first(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteMutationQueue.this.m832xf87fd3c7((Cursor) obj);
            }
        }) == 0) {
            writeMutationQueueMetadata();
        }
    }

    /* renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ void m832xf87fd3c7(Cursor cursor) {
        this.lastStreamToken = ByteString.copyFrom(cursor.getBlob(0));
    }

    private void loadNextBatchIdAcrossAllUsers() {
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT uid FROM mutation_queues").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda8
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                arrayList.add(((Cursor) obj).getString(0));
            }
        });
        this.nextBatchId = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.db.query("SELECT MAX(batch_id) FROM mutations WHERE uid = ?").binding((String) it.next()).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda0
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    SQLiteMutationQueue.this.m830xa3a174c9((Cursor) obj);
                }
            });
        }
        this.nextBatchId++;
    }

    /* renamed from: lambda$loadNextBatchIdAcrossAllUsers$2$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ void m830xa3a174c9(Cursor cursor) {
        this.nextBatchId = Math.max(this.nextBatchId, cursor.getInt(0));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public boolean isEmpty() {
        return this.db.query("SELECT batch_id FROM mutations WHERE uid = ? LIMIT 1").binding(this.uid).isEmpty();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void acknowledgeBatch(MutationBatch mutationBatch, ByteString byteString) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
        writeMutationQueueMetadata();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void setLastStreamToken(ByteString byteString) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
        writeMutationQueueMetadata();
    }

    private void writeMutationQueueMetadata() {
        this.db.execute("INSERT OR REPLACE INTO mutation_queues (uid, last_acknowledged_batch_id, last_stream_token) VALUES (?, ?, ?)", this.uid, -1, this.lastStreamToken.toByteArray());
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch addMutationBatch(Timestamp timestamp, List<Mutation> list, List<Mutation> list2) {
        int i = this.nextBatchId;
        this.nextBatchId = i + 1;
        MutationBatch mutationBatch = new MutationBatch(i, timestamp, list, list2);
        this.db.execute("INSERT INTO mutations (uid, batch_id, mutations) VALUES (?, ?, ?)", this.uid, Integer.valueOf(i), this.serializer.encodeMutationBatch(mutationBatch).toByteArray());
        HashSet hashSet = new HashSet();
        SQLiteStatement prepare = this.db.prepare("INSERT INTO document_mutations (uid, path, batch_id) VALUES (?, ?, ?)");
        Iterator<Mutation> it = list2.iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next().getKey();
            if (hashSet.add(key)) {
                this.db.execute(prepare, this.uid, EncodedPath.encode(key.getPath()), Integer.valueOf(i));
                this.indexManager.addToCollectionParentIndex(key.getCollectionPath());
            }
        }
        return mutationBatch;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch lookupMutationBatch(final int i) {
        return (MutationBatch) this.db.query("SELECT SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(1000000, this.uid, Integer.valueOf(i)).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda11
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return SQLiteMutationQueue.this.m831xb685bf9f(i, (Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$lookupMutationBatch$3$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ MutationBatch m831xb685bf9f(int i, Cursor cursor) {
        return decodeInlineMutationBatch(i, cursor.getBlob(0));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch getNextMutationBatchAfterBatchId(int i) {
        return (MutationBatch) this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id >= ? ORDER BY batch_id ASC LIMIT 1").binding(1000000, this.uid, Integer.valueOf(i + 1)).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda10
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return SQLiteMutationQueue.this.m829x6512fc44((Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$getNextMutationBatchAfterBatchId$4$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ MutationBatch m829x6512fc44(Cursor cursor) {
        return decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public int getHighestUnacknowledgedBatchId() {
        return ((Integer) this.db.query("SELECT IFNULL(MAX(batch_id), ?) FROM mutations WHERE uid = ?").binding(-1, this.uid).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                Integer valueOf;
                valueOf = Integer.valueOf(((Cursor) obj).getInt(0));
                return valueOf;
            }
        })).intValue();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatches() {
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? ORDER BY batch_id ASC").binding(1000000, this.uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteMutationQueue.this.m825x425a097f(arrayList, (Cursor) obj);
            }
        });
        return arrayList;
    }

    /* renamed from: lambda$getAllMutationBatches$6$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ void m825x425a097f(List list, Cursor cursor) {
        list.add(decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1)));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        String encode = EncodedPath.encode(documentKey.getPath());
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT m.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path = ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, encode).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteMutationQueue.this.m826xb3096ab7(arrayList, (Cursor) obj);
            }
        });
        return arrayList;
    }

    /* renamed from: lambda$getAllMutationBatchesAffectingDocumentKey$7$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ void m826xb3096ab7(List list, Cursor cursor) {
        list.add(decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1)));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<DocumentKey> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(EncodedPath.encode(it.next().getPath()));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT DISTINCT dm.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path IN (", Arrays.asList(1000000, this.uid), arrayList, ") AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id");
        final ArrayList arrayList2 = new ArrayList();
        final HashSet hashSet = new HashSet();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda7
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    SQLiteMutationQueue.this.m827x1f85c24f(hashSet, arrayList2, (Cursor) obj);
                }
            });
        }
        if (longQuery.getSubqueriesPerformed() > 1) {
            Collections.sort(arrayList2, new Comparator() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda2
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compareIntegers;
                    compareIntegers = Util.compareIntegers(((MutationBatch) obj).getBatchId(), ((MutationBatch) obj2).getBatchId());
                    return compareIntegers;
                }
            });
        }
        return arrayList2;
    }

    /* renamed from: lambda$getAllMutationBatchesAffectingDocumentKeys$8$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ void m827x1f85c24f(Set set, List list, Cursor cursor) {
        int i = cursor.getInt(0);
        if (set.contains(Integer.valueOf(i))) {
            return;
        }
        set.add(Integer.valueOf(i));
        list.add(decodeInlineMutationBatch(i, cursor.getBlob(1)));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath path = query.getPath();
        final int length = path.length() + 1;
        String encode = EncodedPath.encode(path);
        String prefixSuccessor = EncodedPath.prefixSuccessor(encode);
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT dm.batch_id, dm.path, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path >= ? AND dm.path < ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, encode, prefixSuccessor).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda6
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteMutationQueue.this.m828xb8a43fc7(arrayList, length, (Cursor) obj);
            }
        });
        return arrayList;
    }

    /* renamed from: lambda$getAllMutationBatchesAffectingQuery$10$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    public /* synthetic */ void m828xb8a43fc7(List list, int i, Cursor cursor) {
        int i2 = cursor.getInt(0);
        int size = list.size();
        if ((size <= 0 || i2 != ((MutationBatch) list.get(size - 1)).getBatchId()) && EncodedPath.decodeResourcePath(cursor.getString(1)).length() == i) {
            list.add(decodeInlineMutationBatch(i2, cursor.getBlob(2)));
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void removeMutationBatch(MutationBatch mutationBatch) {
        SQLiteStatement prepare = this.db.prepare("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        SQLiteStatement prepare2 = this.db.prepare("DELETE FROM document_mutations WHERE uid = ? AND path = ? AND batch_id = ?");
        int batchId = mutationBatch.getBatchId();
        Assert.hardAssert(this.db.execute(prepare, this.uid, Integer.valueOf(batchId)) != 0, "Mutation batch (%s, %d) did not exist", this.uid, Integer.valueOf(mutationBatch.getBatchId()));
        Iterator<Mutation> it = mutationBatch.getMutations().iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next().getKey();
            this.db.execute(prepare2, this.uid, EncodedPath.encode(key.getPath()), Integer.valueOf(batchId));
            this.db.getReferenceDelegate().removeMutationReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void performConsistencyCheck() {
        if (isEmpty()) {
            final ArrayList arrayList = new ArrayList();
            this.db.query("SELECT path FROM document_mutations WHERE uid = ?").binding(this.uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda9
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    arrayList.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
                }
            });
            Assert.hardAssert(arrayList.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty. Dangling keys: %s", arrayList);
        }
    }

    private MutationBatch decodeInlineMutationBatch(int i, byte[] bArr) {
        try {
            if (bArr.length < 1000000) {
                return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(bArr));
            }
            BlobAccumulator blobAccumulator = new BlobAccumulator(bArr);
            while (blobAccumulator.more) {
                this.db.query("SELECT SUBSTR(mutations, ?, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(Integer.valueOf((blobAccumulator.numChunks() * 1000000) + 1), 1000000, this.uid, Integer.valueOf(i)).first(blobAccumulator);
            }
            return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(blobAccumulator.result()));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MutationBatch failed to parse: %s", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class BlobAccumulator implements Consumer<Cursor> {
        private final ArrayList<ByteString> chunks = new ArrayList<>();
        private boolean more = true;

        BlobAccumulator(byte[] bArr) {
            addChunk(bArr);
        }

        int numChunks() {
            return this.chunks.size();
        }

        ByteString result() {
            return ByteString.copyFrom(this.chunks);
        }

        @Override // com.google.firebase.firestore.util.Consumer
        public void accept(Cursor cursor) {
            byte[] blob = cursor.getBlob(0);
            addChunk(blob);
            if (blob.length < 1000000) {
                this.more = false;
            }
        }

        private void addChunk(byte[] bArr) {
            this.chunks.add(ByteString.copyFrom(bArr));
        }
    }
}
