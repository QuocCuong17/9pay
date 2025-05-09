package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.Date;
import java.util.Map;

/* loaded from: classes4.dex */
public class DocumentSnapshot {
    private final Document doc;
    private final FirebaseFirestore firestore;
    private final DocumentKey key;
    private final SnapshotMetadata metadata;

    /* loaded from: classes4.dex */
    public enum ServerTimestampBehavior {
        NONE,
        ESTIMATE,
        PREVIOUS;

        static final ServerTimestampBehavior DEFAULT = NONE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentSnapshot(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, Document document, boolean z, boolean z2) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
        this.key = (DocumentKey) Preconditions.checkNotNull(documentKey);
        this.doc = document;
        this.metadata = new SnapshotMetadata(z2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DocumentSnapshot fromDocument(FirebaseFirestore firebaseFirestore, Document document, boolean z, boolean z2) {
        return new DocumentSnapshot(firebaseFirestore, document.getKey(), document, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DocumentSnapshot fromNoDocument(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, boolean z) {
        return new DocumentSnapshot(firebaseFirestore, documentKey, null, z, false);
    }

    public String getId() {
        return this.key.getDocumentId();
    }

    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    public boolean exists() {
        return this.doc != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document getDocument() {
        return this.doc;
    }

    public Map<String, Object> getData() {
        return getData(ServerTimestampBehavior.DEFAULT);
    }

    public Map<String, Object> getData(ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        UserDataWriter userDataWriter = new UserDataWriter(this.firestore, serverTimestampBehavior);
        Document document = this.doc;
        if (document == null) {
            return null;
        }
        return userDataWriter.convertObject(document.getData().getFieldsMap());
    }

    public <T> T toObject(Class<T> cls) {
        return (T) toObject(cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T toObject(Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(cls, "Provided POJO type must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = getData(serverTimestampBehavior);
        if (data == null) {
            return null;
        }
        return (T) CustomClassMapper.convertToCustomClass(data, cls, getReference());
    }

    public boolean contains(String str) {
        return contains(FieldPath.fromDotSeparatedPath(str));
    }

    public boolean contains(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Document document = this.doc;
        return (document == null || document.getField(fieldPath.getInternalPath()) == null) ? false : true;
    }

    public Object get(String str) {
        return get(FieldPath.fromDotSeparatedPath(str), ServerTimestampBehavior.DEFAULT);
    }

    public Object get(String str, ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(str), serverTimestampBehavior);
    }

    public Object get(FieldPath fieldPath) {
        return get(fieldPath, ServerTimestampBehavior.DEFAULT);
    }

    public Object get(FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return getInternal(fieldPath.getInternalPath(), serverTimestampBehavior);
    }

    public <T> T get(String str, Class<T> cls) {
        return (T) get(FieldPath.fromDotSeparatedPath(str), cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(String str, Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        return (T) get(FieldPath.fromDotSeparatedPath(str), cls, serverTimestampBehavior);
    }

    public <T> T get(FieldPath fieldPath, Class<T> cls) {
        return (T) get(fieldPath, cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(FieldPath fieldPath, Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        Object obj = get(fieldPath, serverTimestampBehavior);
        if (obj == null) {
            return null;
        }
        return (T) CustomClassMapper.convertToCustomClass(obj, cls, getReference());
    }

    public Boolean getBoolean(String str) {
        return (Boolean) getTypedValue(str, Boolean.class);
    }

    public Double getDouble(String str) {
        Number number = (Number) getTypedValue(str, Number.class);
        if (number != null) {
            return Double.valueOf(number.doubleValue());
        }
        return null;
    }

    public String getString(String str) {
        return (String) getTypedValue(str, String.class);
    }

    public Long getLong(String str) {
        Number number = (Number) getTypedValue(str, Number.class);
        if (number != null) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    public Date getDate(String str) {
        return getDate(str, ServerTimestampBehavior.DEFAULT);
    }

    public Date getDate(String str, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Timestamp timestamp = getTimestamp(str, serverTimestampBehavior);
        if (timestamp != null) {
            return timestamp.toDate();
        }
        return null;
    }

    public Timestamp getTimestamp(String str) {
        return getTimestamp(str, ServerTimestampBehavior.DEFAULT);
    }

    public Timestamp getTimestamp(String str, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(str, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return (Timestamp) castTypedValue(getInternal(FieldPath.fromDotSeparatedPath(str).getInternalPath(), serverTimestampBehavior), str, Timestamp.class);
    }

    public Blob getBlob(String str) {
        return (Blob) getTypedValue(str, Blob.class);
    }

    public GeoPoint getGeoPoint(String str) {
        return (GeoPoint) getTypedValue(str, GeoPoint.class);
    }

    public DocumentReference getDocumentReference(String str) {
        return (DocumentReference) getTypedValue(str, DocumentReference.class);
    }

    public DocumentReference getReference() {
        return new DocumentReference(this.key, this.firestore);
    }

    private <T> T getTypedValue(String str, Class<T> cls) {
        Preconditions.checkNotNull(str, "Provided field must not be null.");
        return (T) castTypedValue(get(str, ServerTimestampBehavior.DEFAULT), str, cls);
    }

    private <T> T castTypedValue(Object obj, String str, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (!cls.isInstance(obj)) {
            throw new RuntimeException("Field '" + str + "' is not a " + cls.getName());
        }
        return cls.cast(obj);
    }

    private Object getInternal(com.google.firebase.firestore.model.FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Value field;
        Document document = this.doc;
        if (document == null || (field = document.getField(fieldPath)) == null) {
            return null;
        }
        return new UserDataWriter(this.firestore, serverTimestampBehavior).convertValue(field);
    }

    public boolean equals(Object obj) {
        Document document;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentSnapshot)) {
            return false;
        }
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) obj;
        return this.firestore.equals(documentSnapshot.firestore) && this.key.equals(documentSnapshot.key) && ((document = this.doc) != null ? document.equals(documentSnapshot.doc) : documentSnapshot.doc == null) && this.metadata.equals(documentSnapshot.metadata);
    }

    public int hashCode() {
        int hashCode = ((this.firestore.hashCode() * 31) + this.key.hashCode()) * 31;
        Document document = this.doc;
        int hashCode2 = (hashCode + (document != null ? document.getKey().hashCode() : 0)) * 31;
        Document document2 = this.doc;
        return ((hashCode2 + (document2 != null ? document2.getData().hashCode() : 0)) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "DocumentSnapshot{key=" + this.key + ", metadata=" + this.metadata + ", doc=" + this.doc + '}';
    }
}
