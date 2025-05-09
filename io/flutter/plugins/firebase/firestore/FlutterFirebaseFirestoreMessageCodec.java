package io.flutter.plugins.firebase.firestore;

import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.firebase.database.Constants;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
class FlutterFirebaseFirestoreMessageCodec extends StandardMessageCodec {
    private static final byte DATA_TYPE_ARRAY_REMOVE = -71;
    private static final byte DATA_TYPE_ARRAY_UNION = -72;
    private static final byte DATA_TYPE_BLOB = -73;
    private static final byte DATA_TYPE_DATE_TIME = -76;
    private static final byte DATA_TYPE_DELETE = -70;
    private static final byte DATA_TYPE_DOCUMENT_ID = -65;
    private static final byte DATA_TYPE_DOCUMENT_REFERENCE = -74;
    private static final byte DATA_TYPE_FIELD_PATH = -64;
    private static final byte DATA_TYPE_FIRESTORE_INSTANCE = -60;
    private static final byte DATA_TYPE_FIRESTORE_QUERY = -59;
    private static final byte DATA_TYPE_FIRESTORE_SETTINGS = -58;
    private static final byte DATA_TYPE_GEO_POINT = -75;
    private static final byte DATA_TYPE_INCREMENT_DOUBLE = -67;
    private static final byte DATA_TYPE_INCREMENT_INTEGER = -66;
    private static final byte DATA_TYPE_INFINITY = -62;
    private static final byte DATA_TYPE_NAN = -63;
    private static final byte DATA_TYPE_NEGATIVE_INFINITY = -61;
    private static final byte DATA_TYPE_SERVER_TIMESTAMP = -69;
    private static final byte DATA_TYPE_TIMESTAMP = -68;
    public static final FlutterFirebaseFirestoreMessageCodec INSTANCE = new FlutterFirebaseFirestoreMessageCodec();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.flutter.plugin.common.StandardMessageCodec
    public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
        String databaseURL;
        if (obj instanceof Date) {
            byteArrayOutputStream.write(-76);
            writeLong(byteArrayOutputStream, ((Date) obj).getTime());
            return;
        }
        if (obj instanceof Timestamp) {
            byteArrayOutputStream.write(-68);
            Timestamp timestamp = (Timestamp) obj;
            writeLong(byteArrayOutputStream, timestamp.getSeconds());
            writeInt(byteArrayOutputStream, timestamp.getNanoseconds());
            return;
        }
        if (obj instanceof GeoPoint) {
            byteArrayOutputStream.write(-75);
            writeAlignment(byteArrayOutputStream, 8);
            GeoPoint geoPoint = (GeoPoint) obj;
            writeDouble(byteArrayOutputStream, geoPoint.getLatitude());
            writeDouble(byteArrayOutputStream, geoPoint.getLongitude());
            return;
        }
        if (obj instanceof DocumentReference) {
            byteArrayOutputStream.write(-74);
            DocumentReference documentReference = (DocumentReference) obj;
            FirebaseFirestore firestore = documentReference.getFirestore();
            writeValue(byteArrayOutputStream, firestore.getApp().getName());
            writeValue(byteArrayOutputStream, documentReference.getPath());
            synchronized (FlutterFirebaseFirestorePlugin.firestoreInstanceCache) {
                databaseURL = FlutterFirebaseFirestorePlugin.getCachedFirebaseFirestoreInstanceForKey(firestore).getDatabaseURL();
            }
            writeValue(byteArrayOutputStream, databaseURL);
            return;
        }
        if (obj instanceof DocumentSnapshot) {
            writeDocumentSnapshot(byteArrayOutputStream, (DocumentSnapshot) obj);
            return;
        }
        if (obj instanceof QuerySnapshot) {
            writeQuerySnapshot(byteArrayOutputStream, (QuerySnapshot) obj);
            return;
        }
        if (obj instanceof DocumentChange) {
            writeDocumentChange(byteArrayOutputStream, (DocumentChange) obj);
            return;
        }
        if (obj instanceof LoadBundleTaskProgress) {
            writeLoadBundleTaskProgress(byteArrayOutputStream, (LoadBundleTaskProgress) obj);
            return;
        }
        if (obj instanceof SnapshotMetadata) {
            writeSnapshotMetadata(byteArrayOutputStream, (SnapshotMetadata) obj);
            return;
        }
        if (obj instanceof Blob) {
            byteArrayOutputStream.write(-73);
            writeBytes(byteArrayOutputStream, ((Blob) obj).toBytes());
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (Double.isNaN(d.doubleValue())) {
                byteArrayOutputStream.write(-63);
                return;
            }
            if (d.equals(Double.valueOf(Double.NEGATIVE_INFINITY))) {
                byteArrayOutputStream.write(-61);
                return;
            } else if (d.equals(Double.valueOf(Double.POSITIVE_INFINITY))) {
                byteArrayOutputStream.write(-62);
                return;
            } else {
                super.writeValue(byteArrayOutputStream, obj);
                return;
            }
        }
        super.writeValue(byteArrayOutputStream, obj);
    }

    private void writeSnapshotMetadata(ByteArrayOutputStream byteArrayOutputStream, SnapshotMetadata snapshotMetadata) {
        HashMap hashMap = new HashMap();
        hashMap.put("hasPendingWrites", Boolean.valueOf(snapshotMetadata.hasPendingWrites()));
        hashMap.put("isFromCache", Boolean.valueOf(snapshotMetadata.isFromCache()));
        writeValue(byteArrayOutputStream, hashMap);
    }

    private void writeDocumentChange(ByteArrayOutputStream byteArrayOutputStream, DocumentChange documentChange) {
        HashMap hashMap = new HashMap();
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$DocumentChange$Type[documentChange.getType().ordinal()];
        hashMap.put("type", i != 1 ? i != 2 ? i != 3 ? null : "DocumentChangeType.removed" : "DocumentChangeType.modified" : "DocumentChangeType.added");
        hashMap.put("data", documentChange.getDocument().getData());
        hashMap.put(Constants.PATH, documentChange.getDocument().getReference().getPath());
        hashMap.put("oldIndex", Integer.valueOf(documentChange.getOldIndex()));
        hashMap.put("newIndex", Integer.valueOf(documentChange.getNewIndex()));
        hashMap.put(TtmlNode.TAG_METADATA, documentChange.getDocument().getMetadata());
        writeValue(byteArrayOutputStream, hashMap);
    }

    private void writeQuerySnapshot(ByteArrayOutputStream byteArrayOutputStream, QuerySnapshot querySnapshot) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.get(Integer.valueOf(querySnapshot.hashCode()));
        for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
            arrayList.add(documentSnapshot.getReference().getPath());
            if (serverTimestampBehavior != null) {
                arrayList2.add(documentSnapshot.getData(serverTimestampBehavior));
            } else {
                arrayList2.add(documentSnapshot.getData());
            }
            arrayList3.add(documentSnapshot.getMetadata());
        }
        hashMap.put("paths", arrayList);
        hashMap.put("documents", arrayList2);
        hashMap.put("metadatas", arrayList3);
        hashMap.put("documentChanges", querySnapshot.getDocumentChanges());
        hashMap.put(TtmlNode.TAG_METADATA, querySnapshot.getMetadata());
        FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.remove(Integer.valueOf(querySnapshot.hashCode()));
        writeValue(byteArrayOutputStream, hashMap);
    }

    private void writeLoadBundleTaskProgress(ByteArrayOutputStream byteArrayOutputStream, LoadBundleTaskProgress loadBundleTaskProgress) {
        HashMap hashMap = new HashMap();
        hashMap.put("bytesLoaded", Long.valueOf(loadBundleTaskProgress.getBytesLoaded()));
        hashMap.put("documentsLoaded", Integer.valueOf(loadBundleTaskProgress.getDocumentsLoaded()));
        hashMap.put("totalBytes", Long.valueOf(loadBundleTaskProgress.getTotalBytes()));
        hashMap.put("totalDocuments", Integer.valueOf(loadBundleTaskProgress.getTotalDocuments()));
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[loadBundleTaskProgress.getTaskState().ordinal()];
        String str = WorkflowModule.Properties.Section.Component.State.STATE_RUNNING;
        if (i != 1) {
            if (i == 2) {
                str = "success";
            } else if (i == 3) {
                str = "error";
            }
        }
        hashMap.put("taskState", str);
        writeValue(byteArrayOutputStream, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestoreMessageCodec$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentChange$Type;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState;

        static {
            int[] iArr = new int[LoadBundleTaskProgress.TaskState.values().length];
            $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState = iArr;
            try {
                iArr[LoadBundleTaskProgress.TaskState.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[LoadBundleTaskProgress.TaskState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[LoadBundleTaskProgress.TaskState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[DocumentChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = iArr2;
            try {
                iArr2[DocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void writeDocumentSnapshot(ByteArrayOutputStream byteArrayOutputStream, DocumentSnapshot documentSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PATH, documentSnapshot.getReference().getPath());
        if (documentSnapshot.exists()) {
            DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.get(Integer.valueOf(documentSnapshot.hashCode()));
            if (serverTimestampBehavior != null) {
                hashMap.put("data", documentSnapshot.getData(serverTimestampBehavior));
            } else {
                hashMap.put("data", documentSnapshot.getData());
            }
        } else {
            hashMap.put("data", null);
        }
        hashMap.put(TtmlNode.TAG_METADATA, documentSnapshot.getMetadata());
        FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.remove(Integer.valueOf(documentSnapshot.hashCode()));
        writeValue(byteArrayOutputStream, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.flutter.plugin.common.StandardMessageCodec
    public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
        switch (b) {
            case -76:
                return new Date(byteBuffer.getLong());
            case -75:
                readAlignment(byteBuffer, 8);
                return new GeoPoint(byteBuffer.getDouble(), byteBuffer.getDouble());
            case -74:
                return ((FirebaseFirestore) readValue(byteBuffer)).document((String) readValue(byteBuffer));
            case -73:
                return Blob.fromBytes(readBytes(byteBuffer));
            case -72:
                return FieldValue.arrayUnion(toArray(readValue(byteBuffer)));
            case -71:
                return FieldValue.arrayRemove(toArray(readValue(byteBuffer)));
            case -70:
                return FieldValue.delete();
            case -69:
                return FieldValue.serverTimestamp();
            case -68:
                return new Timestamp(byteBuffer.getLong(), byteBuffer.getInt());
            case -67:
                return FieldValue.increment(((Number) readValue(byteBuffer)).doubleValue());
            case -66:
                return FieldValue.increment(((Number) readValue(byteBuffer)).intValue());
            case -65:
                return FieldPath.documentId();
            case -64:
                int readSize = readSize(byteBuffer);
                ArrayList arrayList = new ArrayList(readSize);
                for (int i = 0; i < readSize; i++) {
                    arrayList.add(readValue(byteBuffer));
                }
                return FieldPath.of((String[]) arrayList.toArray(new String[0]));
            case -63:
                return Double.valueOf(Double.NaN);
            case -62:
                return Double.valueOf(Double.POSITIVE_INFINITY);
            case -61:
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            case -60:
                return readFirestoreInstance(byteBuffer);
            case -59:
                return readFirestoreQuery(byteBuffer);
            case -58:
                return readFirestoreSettings(byteBuffer);
            default:
                return super.readValueOfType(b, byteBuffer);
        }
    }

    private FirebaseFirestore readFirestoreInstance(ByteBuffer byteBuffer) {
        String str = (String) readValue(byteBuffer);
        String str2 = (String) readValue(byteBuffer);
        FirebaseFirestoreSettings firebaseFirestoreSettings = (FirebaseFirestoreSettings) readValue(byteBuffer);
        synchronized (FlutterFirebaseFirestorePlugin.firestoreInstanceCache) {
            if (FlutterFirebaseFirestorePlugin.getFirestoreInstanceByNameAndDatabaseUrl(str, str2) != null) {
                return FlutterFirebaseFirestorePlugin.getFirestoreInstanceByNameAndDatabaseUrl(str, str2);
            }
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance(FirebaseApp.getInstance(str), str2);
            firebaseFirestore.setFirestoreSettings(firebaseFirestoreSettings);
            FlutterFirebaseFirestorePlugin.setCachedFirebaseFirestoreInstanceForKey(firebaseFirestore, str2);
            return firebaseFirestore;
        }
    }

    private FirebaseFirestoreSettings readFirestoreSettings(ByteBuffer byteBuffer) {
        Map map = (Map) readValue(byteBuffer);
        FirebaseFirestoreSettings.Builder builder = new FirebaseFirestoreSettings.Builder();
        if (map.get(Constants.DATABASE_PERSISTENCE_ENABLED) != null) {
            if (Boolean.TRUE.equals((Boolean) map.get(Constants.DATABASE_PERSISTENCE_ENABLED))) {
                PersistentCacheSettings.Builder newBuilder = PersistentCacheSettings.newBuilder();
                if (map.get(Constants.DATABASE_CACHE_SIZE_BYTES) != null) {
                    Long l = 104857600L;
                    Object obj = map.get(Constants.DATABASE_CACHE_SIZE_BYTES);
                    if (obj instanceof Long) {
                        l = (Long) obj;
                    } else if (obj instanceof Integer) {
                        l = Long.valueOf(((Integer) obj).intValue());
                    }
                    if (l.longValue() == -1) {
                        newBuilder.setSizeBytes(-1L);
                    } else {
                        newBuilder.setSizeBytes(l.longValue());
                    }
                }
                builder.setLocalCacheSettings(newBuilder.build());
            } else {
                builder.setLocalCacheSettings(MemoryCacheSettings.newBuilder().build());
            }
        }
        if (map.get(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST) != null) {
            Object obj2 = map.get(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST);
            Objects.requireNonNull(obj2);
            builder.setHost((String) obj2);
            if (map.get("sslEnabled") != null) {
                Object obj3 = map.get("sslEnabled");
                Objects.requireNonNull(obj3);
                builder.setSslEnabled(((Boolean) obj3).booleanValue());
            }
        }
        return builder.build();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009a, code lost:
    
        if (r1.equals("not-in") == false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Filter filterFromJson(Map<String, Object> map) {
        char c = 0;
        if (map.containsKey("fieldPath")) {
            String str = (String) map.get("op");
            FieldPath fieldPath = (FieldPath) map.get("fieldPath");
            Object obj = map.get("value");
            str.hashCode();
            switch (str.hashCode()) {
                case -1039747489:
                    break;
                case 60:
                    if (str.equals("<")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 62:
                    if (str.equals(">")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1084:
                    if (str.equals("!=")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1921:
                    if (str.equals("<=")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1952:
                    if (str.equals("==")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1983:
                    if (str.equals(">=")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 3365:
                    if (str.equals("in")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 135338771:
                    if (str.equals("array-contains")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 355289138:
                    if (str.equals("array-contains-any")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    return Filter.notInArray(fieldPath, (List<? extends Object>) obj);
                case 1:
                    return Filter.lessThan(fieldPath, obj);
                case 2:
                    return Filter.greaterThan(fieldPath, obj);
                case 3:
                    return Filter.notEqualTo(fieldPath, obj);
                case 4:
                    return Filter.lessThanOrEqualTo(fieldPath, obj);
                case 5:
                    return Filter.equalTo(fieldPath, obj);
                case 6:
                    return Filter.greaterThanOrEqualTo(fieldPath, obj);
                case 7:
                    return Filter.inArray(fieldPath, (List<? extends Object>) obj);
                case '\b':
                    return Filter.arrayContains(fieldPath, obj);
                case '\t':
                    return Filter.arrayContainsAny(fieldPath, (List<? extends Object>) obj);
                default:
                    throw new Error("Invalid operator");
            }
        }
        String str2 = (String) map.get("op");
        List list = (List) map.get("queries");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(filterFromJson((Map) it.next()));
        }
        if (str2.equals("OR")) {
            return Filter.or((Filter[]) arrayList.toArray(new Filter[0]));
        }
        if (str2.equals("AND")) {
            return Filter.and((Filter[]) arrayList.toArray(new Filter[0]));
        }
        throw new Error("Invalid operator");
    }

    private Query readFirestoreQuery(ByteBuffer byteBuffer) {
        Query collection;
        try {
            Map map = (Map) readValue(byteBuffer);
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            FirebaseFirestore firebaseFirestore = (FirebaseFirestore) obj;
            Object obj2 = map.get(Constants.PATH);
            Objects.requireNonNull(obj2);
            String str = (String) obj2;
            boolean booleanValue = ((Boolean) map.get("isCollectionGroup")).booleanValue();
            Map map2 = (Map) map.get("parameters");
            if (booleanValue) {
                collection = firebaseFirestore.collectionGroup(str);
            } else {
                collection = firebaseFirestore.collection(str);
            }
            if (map2 == null) {
                return collection;
            }
            if (map2.containsKey("filters")) {
                collection = collection.where(filterFromJson((Map) map2.get("filters")));
            }
            Object obj3 = map2.get("where");
            Objects.requireNonNull(obj3);
            for (List list : (List) obj3) {
                FieldPath fieldPath = (FieldPath) list.get(0);
                String str2 = (String) list.get(1);
                Object obj4 = list.get(2);
                if ("==".equals(str2)) {
                    collection = collection.whereEqualTo(fieldPath, obj4);
                } else if ("!=".equals(str2)) {
                    collection = collection.whereNotEqualTo(fieldPath, obj4);
                } else if ("<".equals(str2)) {
                    collection = collection.whereLessThan(fieldPath, obj4);
                } else if ("<=".equals(str2)) {
                    collection = collection.whereLessThanOrEqualTo(fieldPath, obj4);
                } else if (">".equals(str2)) {
                    collection = collection.whereGreaterThan(fieldPath, obj4);
                } else if (">=".equals(str2)) {
                    collection = collection.whereGreaterThanOrEqualTo(fieldPath, obj4);
                } else if ("array-contains".equals(str2)) {
                    collection = collection.whereArrayContains(fieldPath, obj4);
                } else if ("array-contains-any".equals(str2)) {
                    collection = collection.whereArrayContainsAny(fieldPath, (List<? extends Object>) obj4);
                } else if ("in".equals(str2)) {
                    collection = collection.whereIn(fieldPath, (List<? extends Object>) obj4);
                } else if ("not-in".equals(str2)) {
                    collection = collection.whereNotIn(fieldPath, (List<? extends Object>) obj4);
                } else {
                    Log.w("FLTFirestoreMsgCodec", "An invalid query operator " + str2 + " was received but not handled.");
                }
            }
            Number number = (Number) map2.get(Constants.LIMIT);
            if (number != null) {
                collection = collection.limit(number.longValue());
            }
            Number number2 = (Number) map2.get(Constants.LIMIT_TO_LAST);
            if (number2 != null) {
                collection = collection.limitToLast(number2.longValue());
            }
            List<List> list2 = (List) map2.get(Constants.ORDER_BY);
            if (list2 == null) {
                return collection;
            }
            for (List list3 : list2) {
                collection = collection.orderBy((FieldPath) list3.get(0), ((Boolean) list3.get(1)).booleanValue() ? Query.Direction.DESCENDING : Query.Direction.ASCENDING);
            }
            List list4 = (List) map2.get(Constants.START_AT);
            if (list4 != null) {
                Object[] array = list4.toArray();
                Objects.requireNonNull(array);
                collection = collection.startAt(array);
            }
            List list5 = (List) map2.get(Constants.START_AFTER);
            if (list5 != null) {
                Object[] array2 = list5.toArray();
                Objects.requireNonNull(array2);
                collection = collection.startAfter(array2);
            }
            List list6 = (List) map2.get(Constants.END_AT);
            if (list6 != null) {
                Object[] array3 = list6.toArray();
                Objects.requireNonNull(array3);
                collection = collection.endAt(array3);
            }
            List list7 = (List) map2.get(Constants.END_BEFORE);
            if (list7 == null) {
                return collection;
            }
            Object[] array4 = list7.toArray();
            Objects.requireNonNull(array4);
            return collection.endBefore(array4);
        } catch (Exception e) {
            Log.e("FLTFirestoreMsgCodec", "An error occurred while parsing query arguments, this is most likely an error with this SDK.", e);
            return null;
        }
    }

    private Object[] toArray(Object obj) {
        if (obj instanceof List) {
            return ((List) obj).toArray();
        }
        if (obj == null) {
            return new ArrayList().toArray();
        }
        throw new IllegalArgumentException(String.format("java.util.List was expected, unable to convert '%s' to an object array", obj.getClass().getCanonicalName()));
    }
}
