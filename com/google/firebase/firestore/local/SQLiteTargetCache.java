package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class SQLiteTargetCache implements TargetCache {
    private final SQLitePersistence db;
    private int highestTargetId;
    private long lastListenSequenceNumber;
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private final LocalSerializer localSerializer;
    private long targetCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteTargetCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.localSerializer = localSerializer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start() {
        Assert.hardAssert(this.db.query("SELECT highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos, target_count FROM target_globals LIMIT 1").first(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.this.m852x4f660810((Cursor) obj);
            }
        }) == 1, "Missing target_globals entry", new Object[0]);
    }

    /* renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    public /* synthetic */ void m852x4f660810(Cursor cursor) {
        this.highestTargetId = cursor.getInt(0);
        this.lastListenSequenceNumber = cursor.getInt(1);
        this.lastRemoteSnapshotVersion = new SnapshotVersion(new Timestamp(cursor.getLong(2), cursor.getInt(3)));
        this.targetCount = cursor.getLong(4);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getHighestListenSequenceNumber() {
        return this.lastListenSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getTargetCount() {
        return this.targetCount;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void forEachTarget(final Consumer<TargetData> consumer) {
        this.db.query("SELECT target_proto FROM targets").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.this.m849x1515438a(consumer, (Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$forEachTarget$1$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    public /* synthetic */ void m849x1515438a(Consumer consumer, Cursor cursor) {
        consumer.accept(decodeTargetData(cursor.getBlob(0)));
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
        writeMetadata();
    }

    private void saveTargetData(TargetData targetData) {
        int targetId = targetData.getTargetId();
        String canonicalId = targetData.getTarget().getCanonicalId();
        Timestamp timestamp = targetData.getSnapshotVersion().getTimestamp();
        this.db.execute("INSERT OR REPLACE INTO targets (target_id, canonical_id, snapshot_version_seconds, snapshot_version_nanos, resume_token, last_listen_sequence_number, target_proto) VALUES (?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(targetId), canonicalId, Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), targetData.getResumeToken().toByteArray(), Long.valueOf(targetData.getSequenceNumber()), this.localSerializer.encodeTargetData(targetData).toByteArray());
    }

    private boolean updateMetadata(TargetData targetData) {
        boolean z;
        if (targetData.getTargetId() > this.highestTargetId) {
            this.highestTargetId = targetData.getTargetId();
            z = true;
        } else {
            z = false;
        }
        if (targetData.getSequenceNumber() <= this.lastListenSequenceNumber) {
            return z;
        }
        this.lastListenSequenceNumber = targetData.getSequenceNumber();
        return true;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addTargetData(TargetData targetData) {
        saveTargetData(targetData);
        updateMetadata(targetData);
        this.targetCount++;
        writeMetadata();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void updateTargetData(TargetData targetData) {
        saveTargetData(targetData);
        if (updateMetadata(targetData)) {
            writeMetadata();
        }
    }

    private void writeMetadata() {
        this.db.execute("UPDATE target_globals SET highest_target_id = ?, highest_listen_sequence_number = ?, last_remote_snapshot_version_seconds = ?, last_remote_snapshot_version_nanos = ?, target_count = ?", Integer.valueOf(this.highestTargetId), Long.valueOf(this.lastListenSequenceNumber), Long.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getSeconds()), Integer.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getNanoseconds()), Long.valueOf(this.targetCount));
    }

    private void removeTarget(int i) {
        removeMatchingKeysForTargetId(i);
        this.db.execute("DELETE FROM targets WHERE target_id = ?", Integer.valueOf(i));
        this.targetCount--;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeTargetData(TargetData targetData) {
        removeTarget(targetData.getTargetId());
        writeMetadata();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int removeQueries(long j, final SparseArray<?> sparseArray) {
        final int[] iArr = new int[1];
        this.db.query("SELECT target_id FROM targets WHERE last_listen_sequence_number <= ?").binding(Long.valueOf(j)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.this.m851x1041d572(sparseArray, iArr, (Cursor) obj);
            }
        });
        writeMetadata();
        return iArr[0];
    }

    /* renamed from: lambda$removeQueries$2$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    public /* synthetic */ void m851x1041d572(SparseArray sparseArray, int[] iArr, Cursor cursor) {
        int i = cursor.getInt(0);
        if (sparseArray.get(i) == null) {
            removeTarget(i);
            iArr[0] = iArr[0] + 1;
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public TargetData getTargetData(final Target target) {
        String canonicalId = target.getCanonicalId();
        final TargetDataHolder targetDataHolder = new TargetDataHolder();
        this.db.query("SELECT target_proto FROM targets WHERE canonical_id = ?").binding(canonicalId).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.this.m850x4f05f442(target, targetDataHolder, (Cursor) obj);
            }
        });
        return targetDataHolder.targetData;
    }

    /* renamed from: lambda$getTargetData$3$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    public /* synthetic */ void m850x4f05f442(Target target, TargetDataHolder targetDataHolder, Cursor cursor) {
        TargetData decodeTargetData = decodeTargetData(cursor.getBlob(0));
        if (target.equals(decodeTargetData.getTarget())) {
            targetDataHolder.targetData = decodeTargetData;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TargetDataHolder {
        TargetData targetData;

        private TargetDataHolder() {
        }
    }

    private TargetData decodeTargetData(byte[] bArr) {
        try {
            return this.localSerializer.decodeTargetData(com.google.firebase.firestore.proto.Target.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("TargetData failed to parse: %s", e);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        SQLiteStatement prepare = this.db.prepare("INSERT OR IGNORE INTO target_documents (target_id, path) VALUES (?, ?)");
        SQLiteLruReferenceDelegate referenceDelegate = this.db.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            DocumentKey next = it.next();
            this.db.execute(prepare, Integer.valueOf(i), EncodedPath.encode(next.getPath()));
            referenceDelegate.addReference(next);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        SQLiteStatement prepare = this.db.prepare("DELETE FROM target_documents WHERE target_id = ? AND path = ?");
        SQLiteLruReferenceDelegate referenceDelegate = this.db.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            DocumentKey next = it.next();
            this.db.execute(prepare, Integer.valueOf(i), EncodedPath.encode(next.getPath()));
            referenceDelegate.removeReference(next);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeysForTargetId(int i) {
        this.db.execute("DELETE FROM target_documents WHERE target_id = ?", Integer.valueOf(i));
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int i) {
        final DocumentKeysHolder documentKeysHolder = new DocumentKeysHolder();
        this.db.query("SELECT path FROM target_documents WHERE target_id = ?").binding(Integer.valueOf(i)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.DocumentKeysHolder.this.keys = r0.keys.insert(DocumentKey.fromPath(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0))));
            }
        });
        return documentKeysHolder.keys;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class DocumentKeysHolder {
        ImmutableSortedSet<DocumentKey> keys;

        private DocumentKeysHolder() {
            this.keys = DocumentKey.emptyKeySet();
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public boolean containsKey(DocumentKey documentKey) {
        return !this.db.query("SELECT target_id FROM target_documents WHERE path = ? AND target_id != 0 LIMIT 1").binding(EncodedPath.encode(documentKey.getPath())).isEmpty();
    }
}
