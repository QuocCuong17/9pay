package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Write;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

/* loaded from: classes4.dex */
public class SQLiteDocumentOverlayCache implements DocumentOverlayCache {
    private final SQLitePersistence db;
    private final LocalSerializer serializer;
    private final String uid;

    public SQLiteDocumentOverlayCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Overlay getOverlay(DocumentKey documentKey) {
        return (Overlay) this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id = ?").binding(this.uid, EncodedPath.encode(documentKey.getPath().popLast()), documentKey.getPath().getLastSegment()).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return SQLiteDocumentOverlayCache.this.m815xe0a2a77c((Cursor) obj);
            }
        });
    }

    /* renamed from: lambda$getOverlay$0$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache, reason: not valid java name */
    public /* synthetic */ Overlay m815xe0a2a77c(Cursor cursor) {
        return decodeOverlay(cursor.getBlob(0), cursor.getInt(1));
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> sortedSet) {
        Assert.hardAssert(sortedSet.comparator() == null, "getOverlays() requires natural order", new Object[0]);
        HashMap hashMap = new HashMap();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        ResourcePath resourcePath = ResourcePath.EMPTY;
        ArrayList arrayList = new ArrayList();
        for (DocumentKey documentKey : sortedSet) {
            if (!resourcePath.equals(documentKey.getCollectionPath())) {
                processSingleCollection(hashMap, backgroundQueue, resourcePath, arrayList);
                resourcePath = documentKey.getCollectionPath();
                arrayList.clear();
            }
            arrayList.add(documentKey.getDocumentId());
        }
        processSingleCollection(hashMap, backgroundQueue, resourcePath, arrayList);
        backgroundQueue.drain();
        return hashMap;
    }

    private void processSingleCollection(final Map<DocumentKey, Overlay> map, final BackgroundQueue backgroundQueue, ResourcePath resourcePath, List<Object> list) {
        if (list.isEmpty()) {
            return;
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id IN (", Arrays.asList(this.uid, EncodedPath.encode(resourcePath)), list, ")");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda2
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    SQLiteDocumentOverlayCache.this.m820x634144da(backgroundQueue, map, (Cursor) obj);
                }
            });
        }
    }

    private void saveOverlay(int i, DocumentKey documentKey, Mutation mutation) {
        this.db.execute("INSERT OR REPLACE INTO document_overlays (uid, collection_group, collection_path, document_id, largest_batch_id, overlay_mutation) VALUES (?, ?, ?, ?, ?, ?)", this.uid, documentKey.getCollectionGroup(), EncodedPath.encode(documentKey.getPath().popLast()), documentKey.getPath().getLastSegment(), Integer.valueOf(i), this.serializer.encodeMutation(mutation).toByteArray());
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void saveOverlays(int i, Map<DocumentKey, Mutation> map) {
        for (Map.Entry<DocumentKey, Mutation> entry : map.entrySet()) {
            DocumentKey key = entry.getKey();
            saveOverlay(i, key, (Mutation) Preconditions.checkNotNull(entry.getValue(), "null value for key: %s", key));
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void removeOverlaysForBatchId(int i) {
        this.db.execute("DELETE FROM document_overlays WHERE uid = ? AND largest_batch_id = ?", this.uid, Integer.valueOf(i));
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(ResourcePath resourcePath, int i) {
        final HashMap hashMap = new HashMap();
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND largest_batch_id > ?").binding(this.uid, EncodedPath.encode(resourcePath), Integer.valueOf(i)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteDocumentOverlayCache.this.m816x2236d2b5(backgroundQueue, hashMap, (Cursor) obj);
            }
        });
        backgroundQueue.drain();
        return hashMap;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(String str, int i, int i2) {
        final HashMap hashMap = new HashMap();
        final String[] strArr = new String[1];
        final String[] strArr2 = new String[1];
        final int[] iArr = new int[1];
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.db.query("SELECT overlay_mutation, largest_batch_id, collection_path, document_id  FROM document_overlays WHERE uid = ? AND collection_group = ? AND largest_batch_id > ? ORDER BY largest_batch_id, collection_path, document_id LIMIT ?").binding(this.uid, str, Integer.valueOf(i), Integer.valueOf(i2)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteDocumentOverlayCache.this.m817xa097d694(iArr, strArr, strArr2, backgroundQueue, hashMap, (Cursor) obj);
            }
        });
        if (strArr[0] == null) {
            return hashMap;
        }
        this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_group = ? AND (collection_path > ? OR (collection_path = ? AND document_id > ?)) AND largest_batch_id = ?").binding(this.uid, str, strArr[0], strArr[0], strArr2[0], Integer.valueOf(iArr[0])).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteDocumentOverlayCache.this.m818x1ef8da73(backgroundQueue, hashMap, (Cursor) obj);
            }
        });
        backgroundQueue.drain();
        return hashMap;
    }

    /* renamed from: lambda$getOverlays$3$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache, reason: not valid java name */
    public /* synthetic */ void m817xa097d694(int[] iArr, String[] strArr, String[] strArr2, BackgroundQueue backgroundQueue, Map map, Cursor cursor) {
        iArr[0] = cursor.getInt(1);
        strArr[0] = cursor.getString(2);
        strArr2[0] = cursor.getString(3);
        m820x634144da(backgroundQueue, map, cursor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.concurrent.Executor] */
    /* renamed from: processOverlaysInBackground, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m820x634144da(BackgroundQueue backgroundQueue, final Map<DocumentKey, Overlay> map, Cursor cursor) {
        final byte[] blob = cursor.getBlob(0);
        final int i = cursor.getInt(1);
        BackgroundQueue backgroundQueue2 = backgroundQueue;
        if (cursor.isLast()) {
            backgroundQueue2 = Executors.DIRECT_EXECUTOR;
        }
        backgroundQueue2.execute(new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                SQLiteDocumentOverlayCache.this.m819x1d10fe66(blob, i, map);
            }
        });
    }

    /* renamed from: lambda$processOverlaysInBackground$5$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache, reason: not valid java name */
    public /* synthetic */ void m819x1d10fe66(byte[] bArr, int i, Map map) {
        Overlay decodeOverlay = decodeOverlay(bArr, i);
        synchronized (map) {
            map.put(decodeOverlay.getKey(), decodeOverlay);
        }
    }

    private Overlay decodeOverlay(byte[] bArr, int i) {
        try {
            return Overlay.create(i, this.serializer.decodeMutation(Write.parseFrom(bArr)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Overlay failed to parse: %s", e);
        }
    }
}
