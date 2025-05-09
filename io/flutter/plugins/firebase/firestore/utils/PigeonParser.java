package io.flutter.plugins.firebase.firestore.utils;

import android.util.Log;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;
import com.google.firebase.firestore.Source;
import io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class PigeonParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static Source parsePigeonSource(GeneratedAndroidFirebaseFirestore.Source source) {
        int i = AnonymousClass1.$SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$Source[source.ordinal()];
        if (i == 1) {
            return Source.CACHE;
        }
        if (i == 2) {
            return Source.DEFAULT;
        }
        if (i == 3) {
            return Source.SERVER;
        }
        throw new IllegalArgumentException("Unknown source: " + source);
    }

    public static DocumentSnapshot.ServerTimestampBehavior parsePigeonServerTimestampBehavior(GeneratedAndroidFirebaseFirestore.ServerTimestampBehavior serverTimestampBehavior) {
        if (serverTimestampBehavior == null) {
            return DocumentSnapshot.ServerTimestampBehavior.NONE;
        }
        int i = AnonymousClass1.$SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$ServerTimestampBehavior[serverTimestampBehavior.ordinal()];
        if (i == 1) {
            return DocumentSnapshot.ServerTimestampBehavior.NONE;
        }
        if (i == 2) {
            return DocumentSnapshot.ServerTimestampBehavior.ESTIMATE;
        }
        if (i == 3) {
            return DocumentSnapshot.ServerTimestampBehavior.PREVIOUS;
        }
        throw new IllegalArgumentException("Unknown server timestamp behavior: " + serverTimestampBehavior);
    }

    public static GeneratedAndroidFirebaseFirestore.PigeonQuerySnapshot toPigeonQuerySnapshot(QuerySnapshot querySnapshot, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        GeneratedAndroidFirebaseFirestore.PigeonQuerySnapshot.Builder builder = new GeneratedAndroidFirebaseFirestore.PigeonQuerySnapshot.Builder();
        builder.setMetadata(toPigeonSnapshotMetadata(querySnapshot.getMetadata()));
        builder.setDocumentChanges(toPigeonDocumentChanges(querySnapshot.getDocumentChanges(), serverTimestampBehavior));
        builder.setDocuments(toPigeonDocumentSnapshots(querySnapshot.getDocuments(), serverTimestampBehavior));
        return builder.build();
    }

    public static GeneratedAndroidFirebaseFirestore.PigeonSnapshotMetadata toPigeonSnapshotMetadata(SnapshotMetadata snapshotMetadata) {
        GeneratedAndroidFirebaseFirestore.PigeonSnapshotMetadata.Builder builder = new GeneratedAndroidFirebaseFirestore.PigeonSnapshotMetadata.Builder();
        builder.setHasPendingWrites(Boolean.valueOf(snapshotMetadata.hasPendingWrites()));
        builder.setIsFromCache(Boolean.valueOf(snapshotMetadata.isFromCache()));
        return builder.build();
    }

    public static List<GeneratedAndroidFirebaseFirestore.PigeonDocumentChange> toPigeonDocumentChanges(List<DocumentChange> list, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<DocumentChange> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(toPigeonDocumentChange(it.next(), serverTimestampBehavior));
        }
        return arrayList;
    }

    public static GeneratedAndroidFirebaseFirestore.PigeonDocumentChange toPigeonDocumentChange(DocumentChange documentChange, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        GeneratedAndroidFirebaseFirestore.PigeonDocumentChange.Builder builder = new GeneratedAndroidFirebaseFirestore.PigeonDocumentChange.Builder();
        builder.setType(toPigeonDocumentChangeType(documentChange.getType()));
        builder.setOldIndex(Long.valueOf(documentChange.getOldIndex()));
        builder.setNewIndex(Long.valueOf(documentChange.getNewIndex()));
        builder.setDocument(toPigeonDocumentSnapshot(documentChange.getDocument(), serverTimestampBehavior));
        return builder.build();
    }

    public static GeneratedAndroidFirebaseFirestore.DocumentChangeType toPigeonDocumentChangeType(DocumentChange.Type type) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$DocumentChange$Type[type.ordinal()];
        if (i == 1) {
            return GeneratedAndroidFirebaseFirestore.DocumentChangeType.ADDED;
        }
        if (i == 2) {
            return GeneratedAndroidFirebaseFirestore.DocumentChangeType.MODIFIED;
        }
        if (i == 3) {
            return GeneratedAndroidFirebaseFirestore.DocumentChangeType.REMOVED;
        }
        throw new IllegalArgumentException("Unknown change type: " + type);
    }

    public static GeneratedAndroidFirebaseFirestore.PigeonDocumentSnapshot toPigeonDocumentSnapshot(DocumentSnapshot documentSnapshot, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        GeneratedAndroidFirebaseFirestore.PigeonDocumentSnapshot.Builder builder = new GeneratedAndroidFirebaseFirestore.PigeonDocumentSnapshot.Builder();
        builder.setMetadata(toPigeonSnapshotMetadata(documentSnapshot.getMetadata()));
        builder.setData(documentSnapshot.getData(serverTimestampBehavior));
        builder.setPath(documentSnapshot.getReference().getPath());
        return builder.build();
    }

    public static List<GeneratedAndroidFirebaseFirestore.PigeonDocumentSnapshot> toPigeonDocumentSnapshots(List<DocumentSnapshot> list, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<DocumentSnapshot> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(toPigeonDocumentSnapshot(it.next(), serverTimestampBehavior));
        }
        return arrayList;
    }

    public static List<FieldPath> parseFieldPath(List<List<String>> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<List<String>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(FieldPath.of((String[]) it.next().toArray(new String[0])));
        }
        return arrayList;
    }

    public static Query parseQuery(FirebaseFirestore firebaseFirestore, String str, boolean z, GeneratedAndroidFirebaseFirestore.PigeonQueryParameters pigeonQueryParameters) {
        Query collection;
        try {
            if (z) {
                collection = firebaseFirestore.collectionGroup(str);
            } else {
                collection = firebaseFirestore.collection(str);
            }
            if (pigeonQueryParameters == null) {
                return collection;
            }
            if (pigeonQueryParameters.getFilters() != null) {
                collection = collection.where(filterFromJson(pigeonQueryParameters.getFilters()));
            }
            List<List<Object>> where = pigeonQueryParameters.getWhere();
            Objects.requireNonNull(where);
            List<List<Object>> list = where;
            for (List<Object> list2 : where) {
                FieldPath fieldPath = (FieldPath) list2.get(0);
                String str2 = (String) list2.get(1);
                Object obj = list2.get(2);
                if ("==".equals(str2)) {
                    collection = collection.whereEqualTo(fieldPath, obj);
                } else if ("!=".equals(str2)) {
                    collection = collection.whereNotEqualTo(fieldPath, obj);
                } else if ("<".equals(str2)) {
                    collection = collection.whereLessThan(fieldPath, obj);
                } else if ("<=".equals(str2)) {
                    collection = collection.whereLessThanOrEqualTo(fieldPath, obj);
                } else if (">".equals(str2)) {
                    collection = collection.whereGreaterThan(fieldPath, obj);
                } else if (">=".equals(str2)) {
                    collection = collection.whereGreaterThanOrEqualTo(fieldPath, obj);
                } else if ("array-contains".equals(str2)) {
                    collection = collection.whereArrayContains(fieldPath, obj);
                } else if ("array-contains-any".equals(str2)) {
                    collection = collection.whereArrayContainsAny(fieldPath, (List<? extends Object>) obj);
                } else if ("in".equals(str2)) {
                    collection = collection.whereIn(fieldPath, (List<? extends Object>) obj);
                } else if ("not-in".equals(str2)) {
                    collection = collection.whereNotIn(fieldPath, (List<? extends Object>) obj);
                } else {
                    Log.w("FLTFirestoreMsgCodec", "An invalid query operator " + str2 + " was received but not handled.");
                }
            }
            Long limit = pigeonQueryParameters.getLimit();
            if (limit != null) {
                collection = collection.limit(limit.longValue());
            }
            Long limitToLast = pigeonQueryParameters.getLimitToLast();
            if (limitToLast != null) {
                collection = collection.limitToLast(limitToLast.longValue());
            }
            List<List<Object>> orderBy = pigeonQueryParameters.getOrderBy();
            if (orderBy == null) {
                return collection;
            }
            for (List<Object> list3 : orderBy) {
                collection = collection.orderBy((FieldPath) list3.get(0), ((Boolean) list3.get(1)).booleanValue() ? Query.Direction.DESCENDING : Query.Direction.ASCENDING);
            }
            List<Object> startAt = pigeonQueryParameters.getStartAt();
            if (startAt != null) {
                Object[] array = startAt.toArray();
                Objects.requireNonNull(array);
                collection = collection.startAt(array);
            }
            List<Object> startAfter = pigeonQueryParameters.getStartAfter();
            if (startAfter != null) {
                Object[] array2 = startAfter.toArray();
                Objects.requireNonNull(array2);
                collection = collection.startAfter(array2);
            }
            List<Object> endAt = pigeonQueryParameters.getEndAt();
            if (endAt != null) {
                Object[] array3 = endAt.toArray();
                Objects.requireNonNull(array3);
                collection = collection.endAt(array3);
            }
            List<Object> endBefore = pigeonQueryParameters.getEndBefore();
            if (endBefore == null) {
                return collection;
            }
            Object[] array4 = endBefore.toArray();
            Objects.requireNonNull(array4);
            return collection.endBefore(array4);
        } catch (Exception e) {
            Log.e("FLTFirestoreMsgCodec", "An error occurred while parsing query arguments, this is most likely an error with this SDK.", e);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009a, code lost:
    
        if (r1.equals("not-in") == false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Filter filterFromJson(Map<String, Object> map) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.firebase.firestore.utils.PigeonParser$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentChange$Type;
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$AggregateSource;
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$ServerTimestampBehavior;
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$Source;

        static {
            int[] iArr = new int[GeneratedAndroidFirebaseFirestore.AggregateSource.values().length];
            $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$AggregateSource = iArr;
            try {
                iArr[GeneratedAndroidFirebaseFirestore.AggregateSource.SERVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[DocumentChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = iArr2;
            try {
                iArr2[DocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[GeneratedAndroidFirebaseFirestore.ServerTimestampBehavior.values().length];
            $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$ServerTimestampBehavior = iArr3;
            try {
                iArr3[GeneratedAndroidFirebaseFirestore.ServerTimestampBehavior.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$ServerTimestampBehavior[GeneratedAndroidFirebaseFirestore.ServerTimestampBehavior.ESTIMATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$ServerTimestampBehavior[GeneratedAndroidFirebaseFirestore.ServerTimestampBehavior.PREVIOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr4 = new int[GeneratedAndroidFirebaseFirestore.Source.values().length];
            $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$Source = iArr4;
            try {
                iArr4[GeneratedAndroidFirebaseFirestore.Source.CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$Source[GeneratedAndroidFirebaseFirestore.Source.SERVER_AND_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$Source[GeneratedAndroidFirebaseFirestore.Source.SERVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static AggregateSource parseAggregateSource(GeneratedAndroidFirebaseFirestore.AggregateSource aggregateSource) {
        if (AnonymousClass1.$SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$AggregateSource[aggregateSource.ordinal()] == 1) {
            return AggregateSource.SERVER;
        }
        throw new IllegalArgumentException("Unknown AggregateSource value: " + aggregateSource);
    }
}
