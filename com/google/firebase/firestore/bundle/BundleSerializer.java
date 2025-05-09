package com.google.firebase.firestore.bundle;

import android.util.Base64;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.GraphRequest;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import io.flutter.plugins.firebase.database.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class BundleSerializer {
    private static final long MILLIS_PER_SECOND = 1000;
    private final RemoteSerializer remoteSerializer;
    private final SimpleDateFormat timestampFormat;

    public BundleSerializer(RemoteSerializer remoteSerializer) {
        this.remoteSerializer = remoteSerializer;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        this.timestampFormat = simpleDateFormat;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        simpleDateFormat.setCalendar(gregorianCalendar);
    }

    public NamedQuery decodeNamedQuery(JSONObject jSONObject) throws JSONException {
        return new NamedQuery(jSONObject.getString("name"), decodeBundledQuery(jSONObject.getJSONObject("bundledQuery")), decodeSnapshotVersion(jSONObject.get("readTime")));
    }

    public BundleMetadata decodeBundleMetadata(JSONObject jSONObject) throws JSONException {
        return new BundleMetadata(jSONObject.getString("id"), jSONObject.getInt("version"), decodeSnapshotVersion(jSONObject.get("createTime")), jSONObject.getInt("totalDocuments"), jSONObject.getLong("totalBytes"));
    }

    public BundledDocumentMetadata decodeBundledDocumentMetadata(JSONObject jSONObject) throws JSONException {
        DocumentKey fromPath = DocumentKey.fromPath(decodeName(jSONObject.getString("name")));
        SnapshotVersion decodeSnapshotVersion = decodeSnapshotVersion(jSONObject.get("readTime"));
        boolean optBoolean = jSONObject.optBoolean("exists", false);
        JSONArray optJSONArray = jSONObject.optJSONArray("queries");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.getString(i));
            }
        }
        return new BundledDocumentMetadata(fromPath, decodeSnapshotVersion, optBoolean, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BundleDocument decodeDocument(JSONObject jSONObject) throws JSONException {
        DocumentKey fromPath = DocumentKey.fromPath(decodeName(jSONObject.getString("name")));
        SnapshotVersion decodeSnapshotVersion = decodeSnapshotVersion(jSONObject.get("updateTime"));
        Value.Builder newBuilder = Value.newBuilder();
        decodeMapValue(newBuilder, jSONObject.getJSONObject(GraphRequest.FIELDS_PARAM));
        return new BundleDocument(MutableDocument.newFoundDocument(fromPath, decodeSnapshotVersion, ObjectValue.fromMap(newBuilder.getMapValue().getFieldsMap())));
    }

    private ResourcePath decodeName(String str) {
        ResourcePath fromString = ResourcePath.fromString(str);
        if (!this.remoteSerializer.isLocalResourceName(fromString)) {
            throw new IllegalArgumentException("Resource name is not valid for current instance: " + str);
        }
        return fromString.popFirst(5);
    }

    private SnapshotVersion decodeSnapshotVersion(Object obj) throws JSONException {
        return new SnapshotVersion(decodeTimestamp(obj));
    }

    private BundledQuery decodeBundledQuery(JSONObject jSONObject) throws JSONException {
        String str;
        JSONObject jSONObject2 = jSONObject.getJSONObject("structuredQuery");
        verifyNoSelect(jSONObject2);
        ResourcePath decodeName = decodeName(jSONObject.getString("parent"));
        JSONArray jSONArray = jSONObject2.getJSONArray("from");
        verifyCollectionSelector(jSONArray);
        JSONObject jSONObject3 = jSONArray.getJSONObject(0);
        if (jSONObject3.optBoolean("allDescendants", false)) {
            str = jSONObject3.getString("collectionId");
        } else {
            decodeName = decodeName.append(jSONObject3.getString("collectionId"));
            str = null;
        }
        List<Filter> decodeWhere = decodeWhere(jSONObject2.optJSONObject("where"));
        List<OrderBy> decodeOrderBy = decodeOrderBy(jSONObject2.optJSONArray(Constants.ORDER_BY));
        Bound decodeStartAtBound = decodeStartAtBound(jSONObject2.optJSONObject(Constants.START_AT));
        Bound decodeEndAtBound = decodeEndAtBound(jSONObject2.optJSONObject(Constants.END_AT));
        verifyNoOffset(jSONObject2);
        return new BundledQuery(new Query(decodeName, str, decodeWhere, decodeOrderBy, decodeLimit(jSONObject2), Query.LimitType.LIMIT_TO_FIRST, decodeStartAtBound, decodeEndAtBound).toTarget(), decodeLimitType(jSONObject));
    }

    private int decodeLimit(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(Constants.LIMIT);
        if (optJSONObject != null) {
            return optJSONObject.optInt("value", -1);
        }
        return jSONObject.optInt(Constants.LIMIT, -1);
    }

    private Bound decodeStartAtBound(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new Bound(decodePosition(jSONObject), jSONObject.optBoolean(TtmlNode.ANNOTATION_POSITION_BEFORE, false));
    }

    private Bound decodeEndAtBound(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new Bound(decodePosition(jSONObject), !jSONObject.optBoolean(TtmlNode.ANNOTATION_POSITION_BEFORE, false));
    }

    private List<Value> decodePosition(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("values");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(decodeValue(optJSONArray.getJSONObject(i)));
            }
        }
        return arrayList;
    }

    private List<OrderBy> decodeOrderBy(JSONArray jSONArray) throws JSONException {
        OrderBy.Direction direction;
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                FieldPath decodeFieldReference = decodeFieldReference(jSONObject.getJSONObject("field"));
                if (jSONObject.optString("direction", "ASCENDING").equals("ASCENDING")) {
                    direction = OrderBy.Direction.ASCENDING;
                } else {
                    direction = OrderBy.Direction.DESCENDING;
                }
                arrayList.add(OrderBy.getInstance(direction, decodeFieldReference));
            }
        }
        return arrayList;
    }

    private List<Filter> decodeWhere(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            decodeFilter(arrayList, jSONObject);
        }
        return arrayList;
    }

    private void decodeFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("compositeFilter")) {
            decodeCompositeFilter(list, jSONObject.getJSONObject("compositeFilter"));
        } else if (jSONObject.has("fieldFilter")) {
            decodeFieldFilter(list, jSONObject.getJSONObject("fieldFilter"));
        } else if (jSONObject.has("unaryFilter")) {
            decodeUnaryFilter(list, jSONObject.getJSONObject("unaryFilter"));
        }
    }

    private void decodeCompositeFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        if (!jSONObject.getString("op").equals("AND")) {
            throw new IllegalArgumentException("The Android SDK only supports composite filters of type 'AND'");
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("filters");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                decodeFilter(list, optJSONArray.getJSONObject(i));
            }
        }
    }

    private void decodeFieldFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        list.add(FieldFilter.create(decodeFieldReference(jSONObject.getJSONObject("field")), decodeFieldFilterOperator(jSONObject.getString("op")), decodeValue(jSONObject.getJSONObject("value"))));
    }

    private Value decodeValue(JSONObject jSONObject) throws JSONException {
        Value.Builder newBuilder = Value.newBuilder();
        if (jSONObject.has("nullValue")) {
            newBuilder.setNullValue(NullValue.NULL_VALUE);
        } else if (jSONObject.has("booleanValue")) {
            newBuilder.setBooleanValue(jSONObject.optBoolean("booleanValue", false));
        } else if (jSONObject.has("integerValue")) {
            newBuilder.setIntegerValue(jSONObject.optLong("integerValue"));
        } else if (jSONObject.has("doubleValue")) {
            newBuilder.setDoubleValue(jSONObject.optDouble("doubleValue"));
        } else if (jSONObject.has("timestampValue")) {
            decodeTimestamp(newBuilder, jSONObject.get("timestampValue"));
        } else if (jSONObject.has("stringValue")) {
            newBuilder.setStringValue(jSONObject.optString("stringValue", ""));
        } else if (jSONObject.has("bytesValue")) {
            newBuilder.setBytesValue(ByteString.copyFrom(Base64.decode(jSONObject.getString("bytesValue"), 0)));
        } else if (jSONObject.has("referenceValue")) {
            newBuilder.setReferenceValue(jSONObject.getString("referenceValue"));
        } else if (jSONObject.has("geoPointValue")) {
            decodeGeoPoint(newBuilder, jSONObject.getJSONObject("geoPointValue"));
        } else if (jSONObject.has("arrayValue")) {
            decodeArrayValue(newBuilder, jSONObject.getJSONObject("arrayValue").optJSONArray("values"));
        } else if (jSONObject.has("mapValue")) {
            decodeMapValue(newBuilder, jSONObject.getJSONObject("mapValue").optJSONObject(GraphRequest.FIELDS_PARAM));
        } else {
            throw new IllegalArgumentException("Unexpected value type: " + jSONObject);
        }
        return newBuilder.build();
    }

    private void decodeArrayValue(Value.Builder builder, JSONArray jSONArray) throws JSONException {
        ArrayValue.Builder newBuilder = ArrayValue.newBuilder();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                newBuilder.addValues(decodeValue(jSONArray.getJSONObject(i)));
            }
        }
        builder.setArrayValue(newBuilder);
    }

    private void decodeMapValue(Value.Builder builder, JSONObject jSONObject) throws JSONException {
        MapValue.Builder newBuilder = MapValue.newBuilder();
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                newBuilder.putFields(next, decodeValue(jSONObject.getJSONObject(next)));
            }
        }
        builder.setMapValue(newBuilder);
    }

    private void decodeGeoPoint(Value.Builder builder, JSONObject jSONObject) {
        builder.setGeoPointValue(LatLng.newBuilder().setLatitude(jSONObject.optDouble("latitude")).setLongitude(jSONObject.optDouble("longitude")));
    }

    private Timestamp decodeTimestamp(JSONObject jSONObject) {
        return new Timestamp(jSONObject.optLong("seconds"), jSONObject.optInt("nanos"));
    }

    private Timestamp decodeTimestamp(String str) {
        try {
            int indexOf = str.indexOf(84);
            if (indexOf == -1) {
                throw new IllegalArgumentException("Invalid timestamp: " + str);
            }
            int indexOf2 = str.indexOf(90, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = str.indexOf(43, indexOf);
            }
            if (indexOf2 == -1) {
                indexOf2 = str.indexOf(45, indexOf);
            }
            if (indexOf2 == -1) {
                throw new IllegalArgumentException("Invalid timestamp: Missing valid timezone offset: " + str);
            }
            int i = 0;
            String substring = str.substring(0, indexOf2);
            String str2 = "";
            int indexOf3 = substring.indexOf(46);
            if (indexOf3 != -1) {
                String substring2 = substring.substring(0, indexOf3);
                str2 = substring.substring(indexOf3 + 1);
                substring = substring2;
            }
            long time = this.timestampFormat.parse(substring).getTime() / 1000;
            if (!str2.isEmpty()) {
                i = parseNanos(str2);
            }
            if (str.charAt(indexOf2) == 'Z') {
                if (str.length() != indexOf2 + 1) {
                    throw new IllegalArgumentException("Invalid timestamp: Invalid trailing data \"" + str.substring(indexOf2) + "\"");
                }
            } else {
                long decodeTimezoneOffset = decodeTimezoneOffset(str.substring(indexOf2 + 1));
                time = str.charAt(indexOf2) == '+' ? time - decodeTimezoneOffset : time + decodeTimezoneOffset;
            }
            return new Timestamp(time, i);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse timestamp", e);
        }
    }

    private Timestamp decodeTimestamp(Object obj) throws JSONException {
        if (obj instanceof String) {
            return decodeTimestamp((String) obj);
        }
        if (!(obj instanceof JSONObject)) {
            throw new IllegalArgumentException("Timestamps must be either ISO 8601-formatted strings or JSON objects");
        }
        return decodeTimestamp((JSONObject) obj);
    }

    private void decodeTimestamp(Value.Builder builder, Object obj) throws JSONException {
        Timestamp decodeTimestamp = decodeTimestamp(obj);
        builder.setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(decodeTimestamp.getSeconds()).setNanos(decodeTimestamp.getNanoseconds()));
    }

    private static int parseNanos(String str) {
        int i = 0;
        for (int i2 = 0; i2 < 9; i2++) {
            i *= 10;
            if (i2 < str.length()) {
                if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                    throw new IllegalArgumentException("Invalid nanoseconds: " + str);
                }
                i += str.charAt(i2) - '0';
            }
        }
        return i;
    }

    private static long decodeTimezoneOffset(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new IllegalArgumentException("Invalid offset value: " + str);
        }
        return ((Long.parseLong(str.substring(0, indexOf)) * 60) + Long.parseLong(str.substring(indexOf + 1))) * 60;
    }

    private FieldFilter.Operator decodeFieldFilterOperator(String str) {
        return FieldFilter.Operator.valueOf(str);
    }

    private void decodeUnaryFilter(List<Filter> list, JSONObject jSONObject) throws JSONException {
        FieldPath decodeFieldReference = decodeFieldReference(jSONObject.getJSONObject("field"));
        String string = jSONObject.getString("op");
        string.hashCode();
        char c = 65535;
        switch (string.hashCode()) {
            case -2125479834:
                if (string.equals("IS_NAN")) {
                    c = 0;
                    break;
                }
                break;
            case -1465346180:
                if (string.equals("IS_NULL")) {
                    c = 1;
                    break;
                }
                break;
            case -244195494:
                if (string.equals("IS_NOT_NAN")) {
                    c = 2;
                    break;
                }
                break;
            case 1019893512:
                if (string.equals("IS_NOT_NULL")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                list.add(FieldFilter.create(decodeFieldReference, FieldFilter.Operator.EQUAL, Values.NAN_VALUE));
                return;
            case 1:
                list.add(FieldFilter.create(decodeFieldReference, FieldFilter.Operator.EQUAL, Values.NULL_VALUE));
                return;
            case 2:
                list.add(FieldFilter.create(decodeFieldReference, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE));
                return;
            case 3:
                list.add(FieldFilter.create(decodeFieldReference, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE));
                return;
            default:
                throw new IllegalArgumentException("Unexpected unary filter: " + string);
        }
    }

    private FieldPath decodeFieldReference(JSONObject jSONObject) throws JSONException {
        return FieldPath.fromServerFormat(jSONObject.getString("fieldPath"));
    }

    private Query.LimitType decodeLimitType(JSONObject jSONObject) {
        String optString = jSONObject.optString("limitType", "FIRST");
        if (optString.equals("FIRST")) {
            return Query.LimitType.LIMIT_TO_FIRST;
        }
        if (optString.equals("LAST")) {
            return Query.LimitType.LIMIT_TO_LAST;
        }
        throw new IllegalArgumentException("Invalid limit type for bundle query: " + optString);
    }

    private void verifyCollectionSelector(JSONArray jSONArray) {
        if (jSONArray.length() != 1) {
            throw new IllegalArgumentException("Only queries with a single 'from' clause are supported by the Android SDK");
        }
    }

    private void verifyNoOffset(JSONObject jSONObject) {
        if (jSONObject.has(TypedValues.CycleType.S_WAVE_OFFSET)) {
            throw new IllegalArgumentException("Queries with offsets are not supported by the Android SDK");
        }
    }

    private void verifyNoSelect(JSONObject jSONObject) {
        if (jSONObject.has("select")) {
            throw new IllegalArgumentException("Queries with 'select' statements are not supported by the Android SDK");
        }
    }
}
