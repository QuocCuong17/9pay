package org.tensorflow.lite;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.sentry.profilemeasurements.ProfileMeasurement;

/* loaded from: classes6.dex */
class DataTypeUtils {
    private DataTypeUtils() {
    }

    /* renamed from: org.tensorflow.lite.DataTypeUtils$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$org$tensorflow$lite$DataType = iArr;
            try {
                iArr[DataType.FLOAT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.INT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.INT16.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.INT8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.UINT8.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.INT64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toStringName(DataType dataType) {
        switch (AnonymousClass1.$SwitchMap$org$tensorflow$lite$DataType[dataType.ordinal()]) {
            case 1:
                return TypedValues.Custom.S_FLOAT;
            case 2:
                return "int";
            case 3:
                return "short";
            case 4:
            case 5:
                return ProfileMeasurement.UNIT_BYTES;
            case 6:
                return "long";
            case 7:
                return "bool";
            case 8:
                return "string";
            default:
                throw new IllegalArgumentException("DataType error: DataType " + dataType + " is not supported yet");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DataType fromC(int c) {
        switch (c) {
            case 1:
                return DataType.FLOAT32;
            case 2:
                return DataType.INT32;
            case 3:
                return DataType.UINT8;
            case 4:
                return DataType.INT64;
            case 5:
                return DataType.STRING;
            case 6:
                return DataType.BOOL;
            case 7:
                return DataType.INT16;
            case 8:
            default:
                throw new IllegalArgumentException("DataType error: DataType " + c + " is not recognized in Java.");
            case 9:
                return DataType.INT8;
        }
    }
}
