package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class FirestoreIndexValueWriter {
    public static final int DOCUMENT_NAME_OFFSET = 5;
    public static final int INDEX_TYPE_ARRAY = 50;
    public static final int INDEX_TYPE_BLOB = 30;
    public static final int INDEX_TYPE_BOOLEAN = 10;
    public static final int INDEX_TYPE_GEOPOINT = 45;
    public static final int INDEX_TYPE_MAP = 55;
    public static final int INDEX_TYPE_NAN = 13;
    public static final int INDEX_TYPE_NULL = 5;
    public static final int INDEX_TYPE_NUMBER = 15;
    public static final int INDEX_TYPE_REFERENCE = 37;
    public static final int INDEX_TYPE_REFERENCE_SEGMENT = 60;
    public static final int INDEX_TYPE_STRING = 25;
    public static final int INDEX_TYPE_TIMESTAMP = 20;
    public static final FirestoreIndexValueWriter INSTANCE = new FirestoreIndexValueWriter();
    public static final int NOT_TRUNCATED = 2;

    private FirestoreIndexValueWriter() {
    }

    public void writeIndexValue(Value value, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeIndexValueAux(value, directionalIndexByteEncoder);
        directionalIndexByteEncoder.writeInfinity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.index.FirestoreIndexValueWriter$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase;

        static {
            int[] iArr = new int[Value.ValueTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase = iArr;
            try {
                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BOOLEAN_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.TIMESTAMP_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BYTES_VALUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.REFERENCE_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.GEO_POINT_VALUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private void writeIndexValueAux(Value value, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                writeValueTypeLabel(directionalIndexByteEncoder, 5);
                return;
            case 2:
                writeValueTypeLabel(directionalIndexByteEncoder, 10);
                directionalIndexByteEncoder.writeLong(value.getBooleanValue() ? 1L : 0L);
                return;
            case 3:
                double doubleValue = value.getDoubleValue();
                if (Double.isNaN(doubleValue)) {
                    writeValueTypeLabel(directionalIndexByteEncoder, 13);
                    return;
                }
                writeValueTypeLabel(directionalIndexByteEncoder, 15);
                if (doubleValue == -0.0d) {
                    directionalIndexByteEncoder.writeDouble(0.0d);
                    return;
                } else {
                    directionalIndexByteEncoder.writeDouble(doubleValue);
                    return;
                }
            case 4:
                writeValueTypeLabel(directionalIndexByteEncoder, 15);
                directionalIndexByteEncoder.writeDouble(value.getIntegerValue());
                return;
            case 5:
                Timestamp timestampValue = value.getTimestampValue();
                writeValueTypeLabel(directionalIndexByteEncoder, 20);
                directionalIndexByteEncoder.writeLong(timestampValue.getSeconds());
                directionalIndexByteEncoder.writeLong(timestampValue.getNanos());
                return;
            case 6:
                writeIndexString(value.getStringValue(), directionalIndexByteEncoder);
                writeTruncationMarker(directionalIndexByteEncoder);
                return;
            case 7:
                writeValueTypeLabel(directionalIndexByteEncoder, 30);
                directionalIndexByteEncoder.writeBytes(value.getBytesValue());
                writeTruncationMarker(directionalIndexByteEncoder);
                return;
            case 8:
                writeIndexEntityRef(value.getReferenceValue(), directionalIndexByteEncoder);
                return;
            case 9:
                LatLng geoPointValue = value.getGeoPointValue();
                writeValueTypeLabel(directionalIndexByteEncoder, 45);
                directionalIndexByteEncoder.writeDouble(geoPointValue.getLatitude());
                directionalIndexByteEncoder.writeDouble(geoPointValue.getLongitude());
                return;
            case 10:
                if (Values.isMaxValue(value)) {
                    writeValueTypeLabel(directionalIndexByteEncoder, Integer.MAX_VALUE);
                    return;
                } else {
                    writeIndexMap(value.getMapValue(), directionalIndexByteEncoder);
                    writeTruncationMarker(directionalIndexByteEncoder);
                    return;
                }
            case 11:
                writeIndexArray(value.getArrayValue(), directionalIndexByteEncoder);
                writeTruncationMarker(directionalIndexByteEncoder);
                return;
            default:
                throw new IllegalArgumentException("unknown index value type " + value.getValueTypeCase());
        }
    }

    private void writeIndexString(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 25);
        writeUnlabeledIndexString(str, directionalIndexByteEncoder);
    }

    private void writeUnlabeledIndexString(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeString(str);
    }

    private void writeIndexMap(MapValue mapValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 55);
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            String key = entry.getKey();
            Value value = entry.getValue();
            writeIndexString(key, directionalIndexByteEncoder);
            writeIndexValueAux(value, directionalIndexByteEncoder);
        }
    }

    private void writeIndexArray(ArrayValue arrayValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 50);
        Iterator<Value> it = arrayValue.getValuesList().iterator();
        while (it.hasNext()) {
            writeIndexValueAux(it.next(), directionalIndexByteEncoder);
        }
    }

    private void writeIndexEntityRef(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 37);
        ResourcePath fromString = ResourcePath.fromString(str);
        int length = fromString.length();
        for (int i = 5; i < length; i++) {
            String segment = fromString.getSegment(i);
            writeValueTypeLabel(directionalIndexByteEncoder, 60);
            writeUnlabeledIndexString(segment, directionalIndexByteEncoder);
        }
    }

    private void writeValueTypeLabel(DirectionalIndexByteEncoder directionalIndexByteEncoder, int i) {
        directionalIndexByteEncoder.writeLong(i);
    }

    private void writeTruncationMarker(DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(2L);
    }
}
