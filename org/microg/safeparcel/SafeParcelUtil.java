package org.microg.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.microg.safeparcel.SafeParcelable;

/* loaded from: classes6.dex */
public final class SafeParcelUtil {
    private static final String TAG = "SafeParcel";

    private SafeParcelUtil() {
    }

    public static <T extends SafeParcelable> T createObject(Class<T> cls, Parcel parcel) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            boolean isAccessible = declaredConstructor.isAccessible();
            declaredConstructor.setAccessible(true);
            T newInstance = declaredConstructor.newInstance(new Object[0]);
            readObject(newInstance, parcel);
            declaredConstructor.setAccessible(isAccessible);
            return newInstance;
        } catch (NoSuchMethodException unused) {
            throw new RuntimeException("createObject() requires a default constructor");
        } catch (Exception e) {
            throw new RuntimeException("Can't construct object", e);
        }
    }

    public static void writeObject(SafeParcelable safeParcelable, Parcel parcel, int i) {
        Objects.requireNonNull(safeParcelable);
        int writeObjectHeader = SafeParcelWriter.writeObjectHeader(parcel);
        for (Class<?> cls = safeParcelable.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (isSafeParceledField(field)) {
                    try {
                        writeField(safeParcelable, parcel, field, i);
                    } catch (Exception e) {
                        Log.w(TAG, "Error writing field: " + e);
                    }
                }
            }
        }
        SafeParcelWriter.finishObjectHeader(parcel, writeObjectHeader);
    }

    public static void readObject(SafeParcelable safeParcelable, Parcel parcel) {
        Objects.requireNonNull(safeParcelable);
        SparseArray sparseArray = new SparseArray();
        for (Class<?> cls = safeParcelable.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (isSafeParceledField(field)) {
                    int fieldId = getFieldId(field);
                    if (sparseArray.get(fieldId) != null) {
                        throw new RuntimeException(String.format("Field number %d is used twice in %s for fields %s and %s", Integer.valueOf(fieldId), cls.getName(), field.getName(), ((Field) sparseArray.get(fieldId)).getName()));
                    }
                    sparseArray.put(fieldId, field);
                }
            }
        }
        Class<?> cls2 = safeParcelable.getClass();
        int readObjectHeader = SafeParcelReader.readObjectHeader(parcel);
        while (parcel.dataPosition() < readObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId2 = SafeParcelReader.getFieldId(readHeader);
            Field field2 = (Field) sparseArray.get(fieldId2);
            if (field2 == null) {
                Log.d(TAG, String.format("Unknown field id %d in %s, skipping.", Integer.valueOf(fieldId2), cls2.getName()));
                SafeParcelReader.skip(parcel, readHeader);
            } else {
                try {
                    readField(safeParcelable, parcel, field2, readHeader);
                } catch (Exception e) {
                    Log.w(TAG, String.format("Error reading field: %d in %s, skipping.", Integer.valueOf(fieldId2), cls2.getName()), e);
                    SafeParcelReader.skip(parcel, readHeader);
                }
            }
        }
        if (parcel.dataPosition() <= readObjectHeader) {
            return;
        }
        throw new RuntimeException("Overread allowed size end=" + readObjectHeader);
    }

    private static Parcelable.Creator<Parcelable> getCreator(Field field) {
        Class<?> type = field.getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        if (type != null && Parcelable.class.isAssignableFrom(type)) {
            return getCreator(type);
        }
        throw new RuntimeException(type + " is not an Parcelable");
    }

    private static Parcelable.Creator<Parcelable> getCreator(Class cls) {
        try {
            Field declaredField = cls.getDeclaredField("CREATOR");
            declaredField.setAccessible(true);
            return (Parcelable.Creator) declaredField.get(null);
        } catch (IllegalAccessException unused) {
            throw new RuntimeException("CREATOR in " + cls + " is not accessible");
        } catch (NoSuchFieldException unused2) {
            throw new RuntimeException(cls + " is an Parcelable without CREATOR");
        }
    }

    private static Class getSubClass(Field field) {
        SafeParceled safeParceled = (SafeParceled) field.getAnnotation(SafeParceled.class);
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (safeParceled != null && safeParceled.subClass() != SafeParceled.class) {
            return safeParceled.subClass();
        }
        if (safeParceled != null && !"undefined".equals(safeParceled.subType())) {
            try {
                return Class.forName(safeParceled.subType());
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        if (field2 == null || field2.subClass() == SafeParcelable.class) {
            return null;
        }
        return field2.subClass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Class getListItemClass(Field field) {
        Class subClass = getSubClass(field);
        if (subClass != null || field.isAnnotationPresent(SafeParceled.class)) {
            return subClass;
        }
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        if (parameterizedType.getActualTypeArguments().length < 1) {
            return null;
        }
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (Class) type;
        }
        return null;
    }

    private static ClassLoader getClassLoader(Class cls) {
        return (cls == null || cls.getClassLoader() == null) ? ClassLoader.getSystemClassLoader() : cls.getClassLoader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean useValueParcel(Field field) {
        SafeParceled safeParceled = (SafeParceled) field.getAnnotation(SafeParceled.class);
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (safeParceled != null) {
            return safeParceled.useClassLoader();
        }
        if (field2 != null) {
            return field2.useValueParcel();
        }
        throw new IllegalStateException();
    }

    private static int getFieldId(Field field) {
        SafeParceled safeParceled = (SafeParceled) field.getAnnotation(SafeParceled.class);
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (safeParceled != null) {
            return safeParceled.value();
        }
        if (field2 != null) {
            return field2.value();
        }
        throw new IllegalStateException();
    }

    private static boolean getMayNull(Field field) {
        SafeParceled safeParceled = (SafeParceled) field.getAnnotation(SafeParceled.class);
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (safeParceled != null) {
            return safeParceled.mayNull();
        }
        if (field2 != null) {
            return field2.mayNull();
        }
        throw new IllegalStateException();
    }

    private static boolean isSafeParceledField(Field field) {
        return field.isAnnotationPresent(SafeParceled.class) || field.isAnnotationPresent(SafeParcelable.Field.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean useDirectList(Field field) {
        SafeParcelable.Field field2 = (SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class);
        if (field2 != null) {
            return field2.useDirectList();
        }
        return false;
    }

    private static void writeField(SafeParcelable safeParcelable, Parcel parcel, Field field, int i) throws IllegalAccessException {
        int fieldId = getFieldId(field);
        boolean mayNull = getMayNull(field);
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        switch (AnonymousClass1.$SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.fromField(field).ordinal()]) {
            case 1:
                SafeParcelWriter.write(parcel, fieldId, (Parcelable) field.get(safeParcelable), i, mayNull);
                break;
            case 2:
                SafeParcelWriter.write(parcel, fieldId, (IBinder) field.get(safeParcelable), mayNull);
                break;
            case 3:
                SafeParcelWriter.write(parcel, fieldId, ((IInterface) field.get(safeParcelable)).asBinder(), mayNull);
                break;
            case 4:
                SafeParcelWriter.writeStringList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 5:
                SafeParcelWriter.writeIntegerList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 6:
                SafeParcelWriter.writeBooleanList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 7:
                SafeParcelWriter.writeLongList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 8:
                SafeParcelWriter.writeFloatList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 9:
                SafeParcelWriter.writeDoubleList(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                break;
            case 10:
                Class listItemClass = getListItemClass(field);
                if (listItemClass == null || !Parcelable.class.isAssignableFrom(listItemClass) || useValueParcel(field)) {
                    SafeParcelWriter.write(parcel, fieldId, (List) field.get(safeParcelable), mayNull);
                    break;
                } else {
                    SafeParcelWriter.write(parcel, fieldId, (List) field.get(safeParcelable), i, mayNull);
                    break;
                }
                break;
            case 11:
                SafeParcelWriter.write(parcel, fieldId, (Map) field.get(safeParcelable), mayNull);
                break;
            case 12:
                SafeParcelWriter.write(parcel, fieldId, (Bundle) field.get(safeParcelable), mayNull);
                break;
            case 13:
                SafeParcelWriter.write(parcel, fieldId, (Parcelable[]) field.get(safeParcelable), i, mayNull);
                break;
            case 14:
                SafeParcelWriter.write(parcel, fieldId, (String[]) field.get(safeParcelable), mayNull);
                break;
            case 15:
                SafeParcelWriter.write(parcel, fieldId, (byte[]) field.get(safeParcelable), mayNull);
                break;
            case 16:
                SafeParcelWriter.write(parcel, fieldId, (int[]) field.get(safeParcelable), mayNull);
                break;
            case 17:
                SafeParcelWriter.write(parcel, fieldId, (Integer) field.get(safeParcelable));
                break;
            case 18:
                SafeParcelWriter.write(parcel, fieldId, (Long) field.get(safeParcelable));
                break;
            case 19:
                SafeParcelWriter.write(parcel, fieldId, (Boolean) field.get(safeParcelable));
                break;
            case 20:
                SafeParcelWriter.write(parcel, fieldId, (Float) field.get(safeParcelable));
                break;
            case 21:
                SafeParcelWriter.write(parcel, fieldId, (Double) field.get(safeParcelable));
                break;
            case 22:
                SafeParcelWriter.write(parcel, fieldId, (String) field.get(safeParcelable), mayNull);
                break;
        }
        field.setAccessible(isAccessible);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.microg.safeparcel.SafeParcelUtil$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType;

        static {
            int[] iArr = new int[SafeParcelType.values().length];
            $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType = iArr;
            try {
                iArr[SafeParcelType.Parcelable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Binder.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Interface.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.StringList.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.IntegerList.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.BooleanList.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.LongList.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.FloatList.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.DoubleList.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.List.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Map.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Bundle.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.ParcelableArray.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.StringArray.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.ByteArray.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.IntArray.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Integer.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Long.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Boolean.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Float.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Double.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.String.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.Byte.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    private static void readField(SafeParcelable safeParcelable, Parcel parcel, Field field, int i) throws IllegalAccessException {
        ArrayList readList;
        Bundle readBundle;
        boolean isAccessible = field.isAccessible();
        boolean z = true;
        field.setAccessible(true);
        long versionCode = field.isAnnotationPresent(SafeParcelable.Field.class) ? ((SafeParcelable.Field) field.getAnnotation(SafeParcelable.Field.class)).versionCode() : -1L;
        switch (AnonymousClass1.$SwitchMap$org$microg$safeparcel$SafeParcelUtil$SafeParcelType[SafeParcelType.fromField(field).ordinal()]) {
            case 1:
                field.set(safeParcelable, SafeParcelReader.readParcelable(parcel, i, getCreator(field)));
                break;
            case 2:
                field.set(safeParcelable, SafeParcelReader.readBinder(parcel, i));
                break;
            case 3:
                Class<?>[] declaredClasses = field.getType().getDeclaredClasses();
                int length = declaredClasses.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        try {
                            field.set(safeParcelable, declaredClasses[i2].getDeclaredMethod("asInterface", IBinder.class).invoke(null, SafeParcelReader.readBinder(parcel, i)));
                        } catch (Exception unused) {
                            i2++;
                        }
                    } else {
                        z = false;
                    }
                }
                if (!z) {
                    throw new RuntimeException("Field has broken interface: " + field);
                }
                break;
            case 4:
                field.set(safeParcelable, SafeParcelReader.readStringList(parcel, i));
                break;
            case 5:
                field.set(safeParcelable, SafeParcelReader.readIntegerList(parcel, i));
                break;
            case 6:
                field.set(safeParcelable, SafeParcelReader.readBooleanList(parcel, i));
                break;
            case 7:
                field.set(safeParcelable, SafeParcelReader.readLongList(parcel, i));
                break;
            case 8:
                field.set(safeParcelable, SafeParcelReader.readFloatList(parcel, i));
                break;
            case 9:
                field.set(safeParcelable, SafeParcelReader.readDoubleList(parcel, i));
                break;
            case 10:
                Class listItemClass = getListItemClass(field);
                if (listItemClass == null || !Parcelable.class.isAssignableFrom(listItemClass) || useValueParcel(field)) {
                    readList = SafeParcelReader.readList(parcel, i, getClassLoader(listItemClass));
                } else {
                    readList = SafeParcelReader.readParcelableList(parcel, i, getCreator(listItemClass));
                }
                field.set(safeParcelable, readList);
                break;
            case 11:
                field.set(safeParcelable, SafeParcelReader.readMap(parcel, i, getClassLoader(getSubClass(field))));
                break;
            case 12:
                Class subClass = getSubClass(field);
                if (subClass == null || !Parcelable.class.isAssignableFrom(subClass) || useValueParcel(field)) {
                    readBundle = SafeParcelReader.readBundle(parcel, i, getClassLoader(field.getDeclaringClass()));
                } else {
                    readBundle = SafeParcelReader.readBundle(parcel, i, getClassLoader(subClass));
                }
                field.set(safeParcelable, readBundle);
                break;
            case 13:
                field.set(safeParcelable, SafeParcelReader.readParcelableArray(parcel, i, getCreator(field)));
                break;
            case 14:
                field.set(safeParcelable, SafeParcelReader.readStringArray(parcel, i));
                break;
            case 15:
                field.set(safeParcelable, SafeParcelReader.readByteArray(parcel, i));
                break;
            case 16:
                field.set(safeParcelable, SafeParcelReader.readIntArray(parcel, i));
                break;
            case 17:
                int readInt = SafeParcelReader.readInt(parcel, i);
                if (versionCode != -1 && readInt > versionCode) {
                    Log.d(TAG, String.format("Version code of %s (%d) is older than object read (%d).", field.getDeclaringClass().getName(), Long.valueOf(versionCode), Integer.valueOf(readInt)));
                }
                field.set(safeParcelable, Integer.valueOf(readInt));
                break;
            case 18:
                long readLong = SafeParcelReader.readLong(parcel, i);
                if (versionCode != -1 && readLong > versionCode) {
                    Log.d(TAG, String.format("Version code of %s (%d) is older than object read (%d).", field.getDeclaringClass().getName(), Long.valueOf(versionCode), Long.valueOf(readLong)));
                }
                field.set(safeParcelable, Long.valueOf(readLong));
                break;
            case 19:
                field.set(safeParcelable, Boolean.valueOf(SafeParcelReader.readBool(parcel, i)));
                break;
            case 20:
                field.set(safeParcelable, Float.valueOf(SafeParcelReader.readFloat(parcel, i)));
                break;
            case 21:
                field.set(safeParcelable, Double.valueOf(SafeParcelReader.readDouble(parcel, i)));
                break;
            case 22:
                field.set(safeParcelable, SafeParcelReader.readString(parcel, i));
                break;
            case 23:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + SafeParcelType.fromField(field));
        }
        field.setAccessible(isAccessible);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum SafeParcelType {
        Parcelable,
        Binder,
        Interface,
        Bundle,
        StringList,
        IntegerList,
        BooleanList,
        LongList,
        FloatList,
        DoubleList,
        List,
        Map,
        ParcelableArray,
        StringArray,
        ByteArray,
        IntArray,
        Integer,
        Long,
        Boolean,
        Float,
        Double,
        String,
        Byte;

        public static SafeParcelType fromField(Field field) {
            Class<?> type = field.getType();
            Class<?> componentType = type.getComponentType();
            if (type.isArray() && componentType != null && Parcelable.class.isAssignableFrom(componentType)) {
                return ParcelableArray;
            }
            if (type.isArray() && componentType != null && String.class.isAssignableFrom(componentType)) {
                return StringArray;
            }
            if (type.isArray() && componentType != null && Byte.TYPE.isAssignableFrom(componentType)) {
                return ByteArray;
            }
            if (type.isArray() && componentType != null && Integer.TYPE.isAssignableFrom(componentType)) {
                return IntArray;
            }
            if (Bundle.class.isAssignableFrom(type)) {
                return Bundle;
            }
            if (Parcelable.class.isAssignableFrom(type)) {
                return Parcelable;
            }
            if (IBinder.class.isAssignableFrom(type)) {
                return Binder;
            }
            if (IInterface.class.isAssignableFrom(type)) {
                return Interface;
            }
            if (type == List.class || type == ArrayList.class) {
                return (SafeParcelUtil.getListItemClass(field) != String.class || SafeParcelUtil.useValueParcel(field)) ? (SafeParcelUtil.getListItemClass(field) == Integer.class && SafeParcelUtil.useDirectList(field)) ? IntegerList : (SafeParcelUtil.getListItemClass(field) == Boolean.class && SafeParcelUtil.useDirectList(field)) ? BooleanList : (SafeParcelUtil.getListItemClass(field) == Long.class && SafeParcelUtil.useDirectList(field)) ? LongList : (SafeParcelUtil.getListItemClass(field) == Float.class && SafeParcelUtil.useDirectList(field)) ? FloatList : (SafeParcelUtil.getListItemClass(field) == Double.class && SafeParcelUtil.useDirectList(field)) ? DoubleList : List : StringList;
            }
            if (type == Map.class || type == HashMap.class) {
                return Map;
            }
            if (type == Integer.TYPE || type == Integer.class) {
                return Integer;
            }
            if (type == Boolean.TYPE || type == Boolean.class) {
                return Boolean;
            }
            if (type == Long.TYPE || type == Long.class) {
                return Long;
            }
            if (type == Float.TYPE || type == Float.class) {
                return Float;
            }
            if (type == Double.TYPE || type == Double.class) {
                return Double;
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return Byte;
            }
            if (type == String.class) {
                return String;
            }
            throw new RuntimeException("Type is not yet usable with SafeParcelUtil: " + type);
        }
    }

    public static <T extends Parcelable> byte[] asByteArray(T t) {
        if (t == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public static <T extends Parcelable> T fromByteArray(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }
}
