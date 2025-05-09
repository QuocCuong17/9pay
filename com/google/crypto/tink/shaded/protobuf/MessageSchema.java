package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
@CheckReturnValue
/* loaded from: classes4.dex */
public final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private static boolean isEnforceUtf8(int value) {
        return (value & 536870912) != 0;
    }

    private static boolean isRequired(int value) {
        return (value & 268435456) != 0;
    }

    private static long offset(int value) {
        return value & 1048575;
    }

    private static int type(int value) {
        return (value & FIELD_TYPE_MASK) >>> 20;
    }

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean proto3, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.proto3 = proto3;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0251  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int i;
        int charAt;
        int charAt2;
        int charAt3;
        int charAt4;
        int charAt5;
        int[] iArr;
        int i2;
        int i3;
        int i4;
        char charAt6;
        int i5;
        char charAt7;
        int i6;
        char charAt8;
        int i7;
        char charAt9;
        int i8;
        char charAt10;
        int i9;
        char charAt11;
        int i10;
        char charAt12;
        int i11;
        char charAt13;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z;
        int i17;
        int objectFieldOffset;
        String str;
        boolean z2;
        int i18;
        int i19;
        int i20;
        java.lang.reflect.Field reflectField;
        char charAt14;
        int i21;
        int i22;
        Object obj;
        java.lang.reflect.Field reflectField2;
        Object obj2;
        java.lang.reflect.Field reflectField3;
        int i23;
        char charAt15;
        int i24;
        char charAt16;
        int i25;
        char charAt17;
        int i26;
        char charAt18;
        boolean z3 = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = messageInfo.getStringInfo();
        int length = stringInfo.length();
        char c = 55296;
        if (stringInfo.charAt(0) >= 55296) {
            int i27 = 1;
            while (true) {
                i = i27 + 1;
                if (stringInfo.charAt(i27) < 55296) {
                    break;
                }
                i27 = i;
            }
        } else {
            i = 1;
        }
        int i28 = i + 1;
        int charAt19 = stringInfo.charAt(i);
        if (charAt19 >= 55296) {
            int i29 = charAt19 & 8191;
            int i30 = 13;
            while (true) {
                i26 = i28 + 1;
                charAt18 = stringInfo.charAt(i28);
                if (charAt18 < 55296) {
                    break;
                }
                i29 |= (charAt18 & 8191) << i30;
                i30 += 13;
                i28 = i26;
            }
            charAt19 = i29 | (charAt18 << i30);
            i28 = i26;
        }
        if (charAt19 == 0) {
            charAt = 0;
            charAt2 = 0;
            charAt3 = 0;
            charAt4 = 0;
            charAt5 = 0;
            i2 = 0;
            iArr = EMPTY_INT_ARRAY;
            i3 = 0;
        } else {
            int i31 = i28 + 1;
            int charAt20 = stringInfo.charAt(i28);
            if (charAt20 >= 55296) {
                int i32 = charAt20 & 8191;
                int i33 = 13;
                while (true) {
                    i11 = i31 + 1;
                    charAt13 = stringInfo.charAt(i31);
                    if (charAt13 < 55296) {
                        break;
                    }
                    i32 |= (charAt13 & 8191) << i33;
                    i33 += 13;
                    i31 = i11;
                }
                charAt20 = i32 | (charAt13 << i33);
                i31 = i11;
            }
            int i34 = i31 + 1;
            int charAt21 = stringInfo.charAt(i31);
            if (charAt21 >= 55296) {
                int i35 = charAt21 & 8191;
                int i36 = 13;
                while (true) {
                    i10 = i34 + 1;
                    charAt12 = stringInfo.charAt(i34);
                    if (charAt12 < 55296) {
                        break;
                    }
                    i35 |= (charAt12 & 8191) << i36;
                    i36 += 13;
                    i34 = i10;
                }
                charAt21 = i35 | (charAt12 << i36);
                i34 = i10;
            }
            int i37 = i34 + 1;
            charAt = stringInfo.charAt(i34);
            if (charAt >= 55296) {
                int i38 = charAt & 8191;
                int i39 = 13;
                while (true) {
                    i9 = i37 + 1;
                    charAt11 = stringInfo.charAt(i37);
                    if (charAt11 < 55296) {
                        break;
                    }
                    i38 |= (charAt11 & 8191) << i39;
                    i39 += 13;
                    i37 = i9;
                }
                charAt = i38 | (charAt11 << i39);
                i37 = i9;
            }
            int i40 = i37 + 1;
            charAt2 = stringInfo.charAt(i37);
            if (charAt2 >= 55296) {
                int i41 = charAt2 & 8191;
                int i42 = 13;
                while (true) {
                    i8 = i40 + 1;
                    charAt10 = stringInfo.charAt(i40);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i41 |= (charAt10 & 8191) << i42;
                    i42 += 13;
                    i40 = i8;
                }
                charAt2 = i41 | (charAt10 << i42);
                i40 = i8;
            }
            int i43 = i40 + 1;
            charAt3 = stringInfo.charAt(i40);
            if (charAt3 >= 55296) {
                int i44 = charAt3 & 8191;
                int i45 = 13;
                while (true) {
                    i7 = i43 + 1;
                    charAt9 = stringInfo.charAt(i43);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i44 |= (charAt9 & 8191) << i45;
                    i45 += 13;
                    i43 = i7;
                }
                charAt3 = i44 | (charAt9 << i45);
                i43 = i7;
            }
            int i46 = i43 + 1;
            charAt4 = stringInfo.charAt(i43);
            if (charAt4 >= 55296) {
                int i47 = charAt4 & 8191;
                int i48 = 13;
                while (true) {
                    i6 = i46 + 1;
                    charAt8 = stringInfo.charAt(i46);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i47 |= (charAt8 & 8191) << i48;
                    i48 += 13;
                    i46 = i6;
                }
                charAt4 = i47 | (charAt8 << i48);
                i46 = i6;
            }
            int i49 = i46 + 1;
            int charAt22 = stringInfo.charAt(i46);
            if (charAt22 >= 55296) {
                int i50 = charAt22 & 8191;
                int i51 = 13;
                while (true) {
                    i5 = i49 + 1;
                    charAt7 = stringInfo.charAt(i49);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i50 |= (charAt7 & 8191) << i51;
                    i51 += 13;
                    i49 = i5;
                }
                charAt22 = i50 | (charAt7 << i51);
                i49 = i5;
            }
            int i52 = i49 + 1;
            charAt5 = stringInfo.charAt(i49);
            if (charAt5 >= 55296) {
                int i53 = charAt5 & 8191;
                int i54 = 13;
                while (true) {
                    i4 = i52 + 1;
                    charAt6 = stringInfo.charAt(i52);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i53 |= (charAt6 & 8191) << i54;
                    i54 += 13;
                    i52 = i4;
                }
                charAt5 = i53 | (charAt6 << i54);
                i52 = i4;
            }
            iArr = new int[charAt5 + charAt4 + charAt22];
            i2 = (charAt20 * 2) + charAt21;
            i3 = charAt20;
            i28 = i52;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = messageInfo.getObjects();
        Class<?> cls = messageInfo.getDefaultInstance().getClass();
        int[] iArr2 = new int[charAt3 * 3];
        Object[] objArr = new Object[charAt3 * 2];
        int i55 = charAt5 + charAt4;
        int i56 = charAt5;
        int i57 = i55;
        int i58 = 0;
        int i59 = 0;
        while (i28 < length) {
            int i60 = i28 + 1;
            int charAt23 = stringInfo.charAt(i28);
            if (charAt23 >= c) {
                int i61 = charAt23 & 8191;
                int i62 = i60;
                int i63 = 13;
                while (true) {
                    i25 = i62 + 1;
                    charAt17 = stringInfo.charAt(i62);
                    if (charAt17 < c) {
                        break;
                    }
                    i61 |= (charAt17 & 8191) << i63;
                    i63 += 13;
                    i62 = i25;
                }
                charAt23 = i61 | (charAt17 << i63);
                i12 = i25;
            } else {
                i12 = i60;
            }
            int i64 = i12 + 1;
            int charAt24 = stringInfo.charAt(i12);
            if (charAt24 >= c) {
                int i65 = charAt24 & 8191;
                int i66 = i64;
                int i67 = 13;
                while (true) {
                    i24 = i66 + 1;
                    charAt16 = stringInfo.charAt(i66);
                    i13 = length;
                    if (charAt16 < 55296) {
                        break;
                    }
                    i65 |= (charAt16 & 8191) << i67;
                    i67 += 13;
                    i66 = i24;
                    length = i13;
                }
                charAt24 = i65 | (charAt16 << i67);
                i14 = i24;
            } else {
                i13 = length;
                i14 = i64;
            }
            int i68 = charAt24 & 255;
            int i69 = charAt5;
            if ((charAt24 & 1024) != 0) {
                iArr[i58] = i59;
                i58++;
            }
            int i70 = i58;
            if (i68 >= 51) {
                int i71 = i14 + 1;
                int charAt25 = stringInfo.charAt(i14);
                char c2 = 55296;
                if (charAt25 >= 55296) {
                    int i72 = charAt25 & 8191;
                    int i73 = 13;
                    while (true) {
                        i23 = i71 + 1;
                        charAt15 = stringInfo.charAt(i71);
                        if (charAt15 < c2) {
                            break;
                        }
                        i72 |= (charAt15 & 8191) << i73;
                        i73 += 13;
                        i71 = i23;
                        c2 = 55296;
                    }
                    charAt25 = i72 | (charAt15 << i73);
                    i71 = i23;
                }
                int i74 = i68 - 51;
                int i75 = i71;
                if (i74 == 9 || i74 == 17) {
                    i22 = i2 + 1;
                    objArr[((i59 / 3) * 2) + 1] = objects[i2];
                } else {
                    if (i74 == 12 && !z3) {
                        i22 = i2 + 1;
                        objArr[((i59 / 3) * 2) + 1] = objects[i2];
                    }
                    int i76 = charAt25 * 2;
                    obj = objects[i76];
                    if (!(obj instanceof java.lang.reflect.Field)) {
                        reflectField2 = (java.lang.reflect.Field) obj;
                    } else {
                        reflectField2 = reflectField(cls, (String) obj);
                        objects[i76] = reflectField2;
                    }
                    i15 = charAt;
                    i16 = charAt2;
                    int objectFieldOffset2 = (int) unsafe.objectFieldOffset(reflectField2);
                    int i77 = i76 + 1;
                    obj2 = objects[i77];
                    if (!(obj2 instanceof java.lang.reflect.Field)) {
                        reflectField3 = (java.lang.reflect.Field) obj2;
                    } else {
                        reflectField3 = reflectField(cls, (String) obj2);
                        objects[i77] = reflectField3;
                    }
                    str = stringInfo;
                    i18 = (int) unsafe.objectFieldOffset(reflectField3);
                    z2 = z3;
                    i19 = i75;
                    objectFieldOffset = objectFieldOffset2;
                    i20 = 0;
                }
                i2 = i22;
                int i762 = charAt25 * 2;
                obj = objects[i762];
                if (!(obj instanceof java.lang.reflect.Field)) {
                }
                i15 = charAt;
                i16 = charAt2;
                int objectFieldOffset22 = (int) unsafe.objectFieldOffset(reflectField2);
                int i772 = i762 + 1;
                obj2 = objects[i772];
                if (!(obj2 instanceof java.lang.reflect.Field)) {
                }
                str = stringInfo;
                i18 = (int) unsafe.objectFieldOffset(reflectField3);
                z2 = z3;
                i19 = i75;
                objectFieldOffset = objectFieldOffset22;
                i20 = 0;
            } else {
                i15 = charAt;
                i16 = charAt2;
                int i78 = i2 + 1;
                java.lang.reflect.Field reflectField4 = reflectField(cls, (String) objects[i2]);
                if (i68 == 9 || i68 == 17) {
                    z = true;
                    objArr[((i59 / 3) * 2) + 1] = reflectField4.getType();
                } else {
                    if (i68 == 27 || i68 == 49) {
                        z = true;
                        i21 = i78 + 1;
                        objArr[((i59 / 3) * 2) + 1] = objects[i78];
                    } else {
                        if (i68 == 12 || i68 == 30 || i68 == 44) {
                            if (!z3) {
                                z = true;
                                i21 = i78 + 1;
                                objArr[((i59 / 3) * 2) + 1] = objects[i78];
                            }
                        } else if (i68 == 50) {
                            int i79 = i56 + 1;
                            iArr[i56] = i59;
                            int i80 = (i59 / 3) * 2;
                            int i81 = i78 + 1;
                            objArr[i80] = objects[i78];
                            if ((charAt24 & 2048) != 0) {
                                i78 = i81 + 1;
                                objArr[i80 + 1] = objects[i81];
                                i56 = i79;
                            } else {
                                i56 = i79;
                                i17 = i81;
                                z = true;
                                objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                                int i82 = i17;
                                if (((charAt24 & 4096) == 4096 ? z : false) || i68 > 17) {
                                    str = stringInfo;
                                    z2 = z3;
                                    i18 = 1048575;
                                    i19 = i14;
                                    i20 = 0;
                                } else {
                                    int i83 = i14 + 1;
                                    int charAt26 = stringInfo.charAt(i14);
                                    if (charAt26 >= 55296) {
                                        int i84 = charAt26 & 8191;
                                        int i85 = 13;
                                        while (true) {
                                            i19 = i83 + 1;
                                            charAt14 = stringInfo.charAt(i83);
                                            if (charAt14 < 55296) {
                                                break;
                                            }
                                            i84 |= (charAt14 & 8191) << i85;
                                            i85 += 13;
                                            i83 = i19;
                                        }
                                        charAt26 = i84 | (charAt14 << i85);
                                    } else {
                                        i19 = i83;
                                    }
                                    int i86 = (i3 * 2) + (charAt26 / 32);
                                    Object obj3 = objects[i86];
                                    str = stringInfo;
                                    if (obj3 instanceof java.lang.reflect.Field) {
                                        reflectField = (java.lang.reflect.Field) obj3;
                                    } else {
                                        reflectField = reflectField(cls, (String) obj3);
                                        objects[i86] = reflectField;
                                    }
                                    z2 = z3;
                                    i18 = (int) unsafe.objectFieldOffset(reflectField);
                                    i20 = charAt26 % 32;
                                }
                                if (i68 >= 18 && i68 <= 49) {
                                    iArr[i57] = objectFieldOffset;
                                    i57++;
                                }
                                i2 = i82;
                            }
                        }
                        z = true;
                    }
                    i17 = i21;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                    int i822 = i17;
                    if ((charAt24 & 4096) == 4096 ? z : false) {
                    }
                    str = stringInfo;
                    z2 = z3;
                    i18 = 1048575;
                    i19 = i14;
                    i20 = 0;
                    if (i68 >= 18) {
                        iArr[i57] = objectFieldOffset;
                        i57++;
                    }
                    i2 = i822;
                }
                i17 = i78;
                objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                int i8222 = i17;
                if ((charAt24 & 4096) == 4096 ? z : false) {
                }
                str = stringInfo;
                z2 = z3;
                i18 = 1048575;
                i19 = i14;
                i20 = 0;
                if (i68 >= 18) {
                }
                i2 = i8222;
            }
            int i87 = i59 + 1;
            iArr2[i59] = charAt23;
            int i88 = i87 + 1;
            iArr2[i87] = ((charAt24 & 256) != 0 ? 268435456 : 0) | ((charAt24 & 512) != 0 ? 536870912 : 0) | (i68 << 20) | objectFieldOffset;
            i59 = i88 + 1;
            iArr2[i88] = (i20 << 20) | i18;
            z3 = z2;
            charAt = i15;
            charAt5 = i69;
            length = i13;
            i28 = i19;
            i58 = i70;
            stringInfo = str;
            charAt2 = i16;
            c = 55296;
        }
        return new MessageSchema<>(iArr2, objArr, charAt, charAt2, messageInfo.getDefaultInstance(), z3, false, iArr, charAt5, i55, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i;
        boolean z = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = messageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i3++;
            }
        }
        int[] iArr2 = i2 > 0 ? new int[i2] : null;
        int[] iArr3 = i3 > 0 ? new int[i3] : null;
        int[] checkInitialized = messageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < fields.length) {
            FieldInfo fieldInfo2 = fields[i4];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i5, objArr);
            if (i6 < checkInitialized.length && checkInitialized[i6] == fieldNumber3) {
                checkInitialized[i6] = i5;
                i6++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
            } else if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                i = i5;
                iArr3[i8] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                i8++;
                i4++;
                i5 = i + 3;
            }
            i = i5;
            i4++;
            i5 = i + 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, messageInfo.getDefaultInstance(), z, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
        int objectFieldOffset;
        int id2;
        long objectFieldOffset2;
        int i;
        int i2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            id2 = fi.getType().id() + 51;
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            objectFieldOffset2 = UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        } else {
            FieldType type = fi.getType();
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            id2 = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fi.getPresenceField();
                i = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                i2 = Integer.numberOfTrailingZeros(fi.getPresenceMask());
            } else if (fi.getCachedSizeField() == null) {
                i = 0;
                i2 = 0;
            } else {
                objectFieldOffset2 = UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
            }
            buffer[bufferIndex] = fi.getFieldNumber();
            buffer[bufferIndex + 1] = (fi.isRequired() ? 268435456 : 0) | (!fi.isEnforceUtf8() ? 536870912 : 0) | (id2 << 20) | objectFieldOffset;
            buffer[bufferIndex + 2] = i | (i2 << 20);
            Class<?> messageFieldClass = fi.getMessageFieldClass();
            if (fi.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fi.getEnumVerifier() != null) {
                        objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            int i3 = (bufferIndex / 3) * 2;
            objects[i3] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[i3 + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[i3 + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        i = (int) objectFieldOffset2;
        i2 = 0;
        buffer[bufferIndex] = fi.getFieldNumber();
        if (!fi.isEnforceUtf8()) {
        }
        buffer[bufferIndex + 1] = (fi.isRequired() ? 268435456 : 0) | (!fi.isEnforceUtf8() ? 536870912 : 0) | (id2 << 20) | objectFieldOffset;
        buffer[bufferIndex + 2] = i | (i2 << 20);
        Class<?> messageFieldClass2 = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T message, T other) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(message, other, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(message).equals(this.unknownFieldSchema.getFromMessage(other))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffsetAt = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset));
            case 1:
                return arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset));
            case 2:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 3:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 4:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 5:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 6:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 7:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset);
            case 8:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 9:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 10:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 11:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 12:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 13:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 14:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 15:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 16:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 17:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            default:
                return true;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0019. Please report as an issue. */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T message) {
        int i;
        int hashLong;
        int length = this.buffer.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i3);
            int numberAt = numberAt(i3);
            long offset = offset(typeAndOffsetAt);
            int i4 = 37;
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    i2 = i + hashLong;
                    break;
                case 1:
                    i = i2 * 53;
                    hashLong = Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    i2 = i + hashLong;
                    break;
                case 2:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 3:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 4:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 5:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 6:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 7:
                    i = i2 * 53;
                    hashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    i2 = i + hashLong;
                    break;
                case 8:
                    i = i2 * 53;
                    hashLong = ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    i2 = i + hashLong;
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(message, offset);
                    if (object != null) {
                        i4 = object.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 11:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 12:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 13:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 14:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 15:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 16:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(message, offset);
                    if (object2 != null) {
                        i4 = object2.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 50:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 51:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Float.floatToIntBits(oneofFloatAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashBoolean(oneofBooleanAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.unknownFieldSchema.getFromMessage(message).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(message).hashCode() : hashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, T other) {
        checkMutable(message);
        Objects.requireNonNull(other);
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffsetAt = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(pos);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 9:
                mergeMessage(message, other, pos);
                return;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 17:
                mergeMessage(message, other, pos);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, numberAt, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, numberAt, pos);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(message, other, pos);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(other, numberAt, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, numberAt, pos);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(message, other, pos);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T targetParent, T sourceParent, int pos) {
        if (isFieldPresent(sourceParent, pos)) {
            long offset = offset(typeAndOffsetAt(pos));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(sourceParent, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
            }
            Schema messageFieldSchema = getMessageFieldSchema(pos);
            if (!isFieldPresent(targetParent, pos)) {
                if (!isMutable(object)) {
                    unsafe.putObject(targetParent, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(targetParent, offset, newInstance);
                }
                setFieldPresent(targetParent, pos);
                return;
            }
            Object object2 = unsafe.getObject(targetParent, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(targetParent, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T targetParent, T sourceParent, int pos) {
        int numberAt = numberAt(pos);
        if (isOneofPresent(sourceParent, numberAt, pos)) {
            long offset = offset(typeAndOffsetAt(pos));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(sourceParent, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
            }
            Schema messageFieldSchema = getMessageFieldSchema(pos);
            if (!isOneofPresent(targetParent, numberAt, pos)) {
                if (!isMutable(object)) {
                    unsafe.putObject(targetParent, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(targetParent, offset, newInstance);
                }
                setOneofPresent(targetParent, numberAt, pos);
                return;
            }
            Object object2 = unsafe.getObject(targetParent, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(targetParent, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T message) {
        return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x005d. Please report as an issue. */
    private int getSerializedSizeProto2(T message) {
        int i;
        int i2;
        int computeDoubleSize;
        int computeBoolSize;
        int computeSFixed32Size;
        boolean z;
        int computeSizeFixed32List;
        int computeSizeFixed64ListNoTag;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        int i3 = 1048575;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.buffer.length) {
            int typeAndOffsetAt = typeAndOffsetAt(i5);
            int numberAt = numberAt(i5);
            int type = type(typeAndOffsetAt);
            if (type <= 17) {
                i = this.buffer[i5 + 2];
                int i8 = i & i3;
                i2 = 1 << (i >>> 20);
                if (i8 != i4) {
                    i7 = unsafe.getInt(message, i8);
                    i4 = i8;
                }
            } else {
                i = (!this.useCachedSizeField || type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i5 + 2] & i3;
                i2 = 0;
            }
            long offset = offset(typeAndOffsetAt);
            switch (type) {
                case 0:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i6 += computeDoubleSize;
                        break;
                    }
                case 1:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i6 += computeDoubleSize;
                        break;
                    }
                case 2:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(message, offset));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 3:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(message, offset));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 4:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(message, offset));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 5:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i6 += computeDoubleSize;
                        break;
                    }
                case 6:
                    if ((i7 & i2) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i6 += computeDoubleSize;
                        break;
                    }
                    break;
                case 7:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i6 += computeBoolSize;
                    }
                    break;
                case 8:
                    if ((i7 & i2) != 0) {
                        Object object = unsafe.getObject(message, offset);
                        if (object instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(numberAt, (String) object);
                        }
                        i6 += computeBoolSize;
                    }
                    break;
                case 9:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(message, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 10:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 11:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 12:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 13:
                    if ((i7 & i2) != 0) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 14:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 15:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 16:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 17:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 18:
                    computeBoolSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeBoolSize;
                    break;
                case 19:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 20:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeInt64List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 21:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeUInt64List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 22:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeInt32List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 23:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 24:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 25:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeBoolList(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 26:
                    computeBoolSize = SchemaUtil.computeSizeStringList(numberAt, (List) unsafe.getObject(message, offset));
                    i6 += computeBoolSize;
                    break;
                case 27:
                    computeBoolSize = SchemaUtil.computeSizeMessageList(numberAt, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i5));
                    i6 += computeBoolSize;
                    break;
                case 28:
                    computeBoolSize = SchemaUtil.computeSizeByteStringList(numberAt, (List) unsafe.getObject(message, offset));
                    i6 += computeBoolSize;
                    break;
                case 29:
                    computeBoolSize = SchemaUtil.computeSizeUInt32List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeBoolSize;
                    break;
                case 30:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeEnumList(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 31:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 32:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 33:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeSInt32List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 34:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeSInt64List(numberAt, (List) unsafe.getObject(message, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 35:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 36:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 37:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 38:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 39:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 40:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 41:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 42:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 43:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 44:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 45:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 46:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 47:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 48:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 49:
                    computeBoolSize = SchemaUtil.computeSizeGroupList(numberAt, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i5));
                    i6 += computeBoolSize;
                    break;
                case 50:
                    computeBoolSize = this.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(message, offset), getMapFieldDefaultEntry(i5));
                    i6 += computeBoolSize;
                    break;
                case 51:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i6 += computeBoolSize;
                    }
                    break;
                case 52:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i6 += computeBoolSize;
                    }
                    break;
                case 53:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 54:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 55:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 57:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeSFixed32Size = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 58:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i6 += computeBoolSize;
                    }
                    break;
                case 59:
                    if (isOneofPresent(message, numberAt, i5)) {
                        Object object2 = unsafe.getObject(message, offset);
                        if (object2 instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        }
                        i6 += computeBoolSize;
                    }
                    break;
                case 60:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(message, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 61:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 62:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 63:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 65:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 66:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 67:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(message, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 68:
                    if (isOneofPresent(message, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        int unknownFieldsSerializedSize = i6 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(message).getSerializedSize() : unknownFieldsSerializedSize;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x003d. Please report as an issue. */
    private int getSerializedSizeProto3(T message) {
        int computeDoubleSize;
        int computeSizeFixed64ListNoTag;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        int i = 0;
        for (int i2 = 0; i2 < this.buffer.length; i2 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i2);
            int type = type(typeAndOffsetAt);
            int numberAt = numberAt(i2);
            long offset = offset(typeAndOffsetAt);
            int i3 = (type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i2 + 2] & 1048575;
            switch (type) {
                case 0:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, UnsafeUtil.getLong(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, UnsafeUtil.getLong(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, UnsafeUtil.getInt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(message, i2)) {
                        Object object = UnsafeUtil.getObject(message, offset);
                        if (object instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(numberAt, (String) object);
                        }
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, UnsafeUtil.getInt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, UnsafeUtil.getInt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, UnsafeUtil.getInt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, UnsafeUtil.getLong(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(message, i2)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 19:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 20:
                    computeDoubleSize = SchemaUtil.computeSizeInt64List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 21:
                    computeDoubleSize = SchemaUtil.computeSizeUInt64List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 22:
                    computeDoubleSize = SchemaUtil.computeSizeInt32List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 23:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 24:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 25:
                    computeDoubleSize = SchemaUtil.computeSizeBoolList(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 26:
                    computeDoubleSize = SchemaUtil.computeSizeStringList(numberAt, listAt(message, offset));
                    i += computeDoubleSize;
                    break;
                case 27:
                    computeDoubleSize = SchemaUtil.computeSizeMessageList(numberAt, listAt(message, offset), getMessageFieldSchema(i2));
                    i += computeDoubleSize;
                    break;
                case 28:
                    computeDoubleSize = SchemaUtil.computeSizeByteStringList(numberAt, listAt(message, offset));
                    i += computeDoubleSize;
                    break;
                case 29:
                    computeDoubleSize = SchemaUtil.computeSizeUInt32List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 30:
                    computeDoubleSize = SchemaUtil.computeSizeEnumList(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 31:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 32:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 33:
                    computeDoubleSize = SchemaUtil.computeSizeSInt32List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 34:
                    computeDoubleSize = SchemaUtil.computeSizeSInt64List(numberAt, listAt(message, offset), false);
                    i += computeDoubleSize;
                    break;
                case 35:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 36:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 37:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 38:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 39:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 40:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 41:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 42:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 43:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 44:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 45:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 46:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 47:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 48:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 49:
                    computeDoubleSize = SchemaUtil.computeSizeGroupList(numberAt, listAt(message, offset), getMessageFieldSchema(i2));
                    i += computeDoubleSize;
                    break;
                case 50:
                    computeDoubleSize = this.mapFieldSchema.getSerializedSize(numberAt, UnsafeUtil.getObject(message, offset), getMapFieldDefaultEntry(i2));
                    i += computeDoubleSize;
                    break;
                case 51:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, numberAt, i2)) {
                        Object object2 = UnsafeUtil.getObject(message, offset);
                        if (object2 instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        }
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(message, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return i + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        return schema.getSerializedSize(schema.getFromMessage(message));
    }

    private static List<?> listAt(Object message, long offset) {
        return (List) UnsafeUtil.getObject(message, offset);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(message, writer);
        } else {
            writeFieldsInAscendingOrderProto2(message, writer);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:228:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        int i;
        int i2;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
                length = this.buffer.length;
                Unsafe unsafe = UNSAFE;
                int i3 = 1048575;
                int i4 = 1048575;
                i = 0;
                int i5 = 0;
                while (i < length) {
                    int typeAndOffsetAt = typeAndOffsetAt(i);
                    int numberAt = numberAt(i);
                    int type = type(typeAndOffsetAt);
                    if (type <= 17) {
                        int i6 = this.buffer[i + 2];
                        int i7 = i6 & i3;
                        if (i7 != i4) {
                            i5 = unsafe.getInt(message, i7);
                            i4 = i7;
                        }
                        i2 = 1 << (i6 >>> 20);
                    } else {
                        i2 = 0;
                    }
                    while (entry != null && this.extensionSchema.extensionNumber(entry) <= numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    long offset = offset(typeAndOffsetAt);
                    switch (type) {
                        case 0:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, doubleAt(message, offset));
                                continue;
                            }
                        case 1:
                            if ((i2 & i5) != 0) {
                                writer.writeFloat(numberAt, floatAt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 2:
                            if ((i2 & i5) != 0) {
                                writer.writeInt64(numberAt, unsafe.getLong(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 3:
                            if ((i2 & i5) != 0) {
                                writer.writeUInt64(numberAt, unsafe.getLong(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 4:
                            if ((i2 & i5) != 0) {
                                writer.writeInt32(numberAt, unsafe.getInt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 5:
                            if ((i2 & i5) != 0) {
                                writer.writeFixed64(numberAt, unsafe.getLong(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 6:
                            if ((i2 & i5) != 0) {
                                writer.writeFixed32(numberAt, unsafe.getInt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 7:
                            if ((i2 & i5) != 0) {
                                writer.writeBool(numberAt, booleanAt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 8:
                            if ((i2 & i5) != 0) {
                                writeString(numberAt, unsafe.getObject(message, offset), writer);
                                break;
                            } else {
                                continue;
                            }
                        case 9:
                            if ((i2 & i5) != 0) {
                                writer.writeMessage(numberAt, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                                break;
                            } else {
                                continue;
                            }
                        case 10:
                            if ((i2 & i5) != 0) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 11:
                            if ((i2 & i5) != 0) {
                                writer.writeUInt32(numberAt, unsafe.getInt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 12:
                            if ((i2 & i5) != 0) {
                                writer.writeEnum(numberAt, unsafe.getInt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 13:
                            if ((i2 & i5) != 0) {
                                writer.writeSFixed32(numberAt, unsafe.getInt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 14:
                            if ((i2 & i5) != 0) {
                                writer.writeSFixed64(numberAt, unsafe.getLong(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 15:
                            if ((i2 & i5) != 0) {
                                writer.writeSInt32(numberAt, unsafe.getInt(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 16:
                            if ((i2 & i5) != 0) {
                                writer.writeSInt64(numberAt, unsafe.getLong(message, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 17:
                            if ((i2 & i5) != 0) {
                                writer.writeGroup(numberAt, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                                break;
                            } else {
                                continue;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) unsafe.getObject(message, offset), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) unsafe.getObject(message, offset), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            continue;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) unsafe.getObject(message, offset), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, unsafe.getObject(message, offset), i);
                            break;
                        case 51:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(message, offset));
                                break;
                            }
                            break;
                        case 52:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeFloat(numberAt, oneofFloatAt(message, offset));
                                break;
                            }
                            break;
                        case 53:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeInt64(numberAt, oneofLongAt(message, offset));
                                break;
                            }
                            break;
                        case 54:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeUInt64(numberAt, oneofLongAt(message, offset));
                                break;
                            }
                            break;
                        case 55:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeInt32(numberAt, oneofIntAt(message, offset));
                                break;
                            }
                            break;
                        case 56:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeFixed64(numberAt, oneofLongAt(message, offset));
                                break;
                            }
                            break;
                        case 57:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeFixed32(numberAt, oneofIntAt(message, offset));
                                break;
                            }
                            break;
                        case 58:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeBool(numberAt, oneofBooleanAt(message, offset));
                                break;
                            }
                            break;
                        case 59:
                            if (isOneofPresent(message, numberAt, i)) {
                                writeString(numberAt, unsafe.getObject(message, offset), writer);
                                break;
                            }
                            break;
                        case 60:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeMessage(numberAt, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                                break;
                            }
                            break;
                        case 61:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(message, offset));
                                break;
                            }
                            break;
                        case 62:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeUInt32(numberAt, oneofIntAt(message, offset));
                                break;
                            }
                            break;
                        case 63:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeEnum(numberAt, oneofIntAt(message, offset));
                                break;
                            }
                            break;
                        case 64:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(message, offset));
                                break;
                            }
                            break;
                        case 65:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(message, offset));
                                break;
                            }
                            break;
                        case 66:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSInt32(numberAt, oneofIntAt(message, offset));
                                break;
                            }
                            break;
                        case 67:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSInt64(numberAt, oneofLongAt(message, offset));
                                break;
                            }
                            break;
                        case 68:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeGroup(numberAt, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                                break;
                            }
                            break;
                    }
                    i += 3;
                    i3 = 1048575;
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        Unsafe unsafe2 = UNSAFE;
        int i32 = 1048575;
        int i42 = 1048575;
        i = 0;
        int i52 = 0;
        while (i < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:275:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        int i;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
                length = this.buffer.length;
                for (i = 0; i < length; i += 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(i);
                    int numberAt = numberAt(i);
                    while (entry != null && this.extensionSchema.extensionNumber(entry) <= numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(message, i)) {
                                writer.writeDouble(numberAt, doubleAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(message, i)) {
                                writer.writeFloat(numberAt, floatAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(message, i)) {
                                writer.writeInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(message, i)) {
                                writer.writeUInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(message, i)) {
                                writer.writeInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(message, i)) {
                                writer.writeFixed64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(message, i)) {
                                writer.writeFixed32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(message, i)) {
                                writer.writeBool(numberAt, booleanAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(message, i)) {
                                writeString(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(message, i)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(message, i)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(message, i)) {
                                writer.writeUInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(message, i)) {
                                writer.writeEnum(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(message, i)) {
                                writer.writeSFixed32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(message, i)) {
                                writer.writeSFixed64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(message, i)) {
                                writer.writeSInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(message, i)) {
                                writer.writeSInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(message, i)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), i);
                            break;
                        case 51:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeFloat(numberAt, oneofFloatAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeUInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeFixed64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeFixed32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeBool(numberAt, oneofBooleanAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(message, numberAt, i)) {
                                writeString(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeUInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeEnum(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeSInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(message, numberAt, i)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        while (i < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:275:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                it = extensions.descendingIterator();
                entry = (Map.Entry) it.next();
                for (length = this.buffer.length - 3; length >= 0; length -= 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(length);
                    int numberAt = numberAt(length);
                    while (entry != null && this.extensionSchema.extensionNumber(entry) > numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(message, length)) {
                                writer.writeDouble(numberAt, doubleAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(message, length)) {
                                writer.writeFloat(numberAt, floatAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(message, length)) {
                                writer.writeInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(message, length)) {
                                writer.writeUInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(message, length)) {
                                writer.writeInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(message, length)) {
                                writer.writeFixed64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(message, length)) {
                                writer.writeFixed32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(message, length)) {
                                writer.writeBool(numberAt, booleanAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(message, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(message, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(message, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(message, length)) {
                                writer.writeUInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(message, length)) {
                                writer.writeEnum(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(message, length)) {
                                writer.writeSFixed32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(message, length)) {
                                writer.writeSFixed64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(message, length)) {
                                writer.writeSInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(message, length)) {
                                writer.writeSInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(message, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), length);
                            break;
                        case 51:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeFloat(numberAt, oneofFloatAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeUInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeFixed64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeFixed32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeBool(numberAt, oneofBooleanAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(message, numberAt, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeUInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeEnum(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
            }
        }
        it = null;
        entry = null;
        while (length >= 0) {
        }
        while (entry != null) {
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        Objects.requireNonNull(extensionRegistry);
        checkMutable(message);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x00c3. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0646 A[Catch: all -> 0x069e, TRY_LEAVE, TryCatch #13 {all -> 0x069e, blocks: (B:16:0x0617, B:34:0x0640, B:36:0x0646, B:49:0x066e, B:50:0x0673), top: B:15:0x0617 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x06b0 A[LOOP:4: B:64:0x06ac->B:66:0x06b0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x06c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        UnknownFieldSchema unknownFieldSchema2;
        T t;
        int i;
        Object obj;
        T t2;
        ExtensionRegistryLite extensionRegistryLite;
        Object obj2;
        UnknownFieldSchema unknownFieldSchema3 = unknownFieldSchema;
        T t3 = message;
        ExtensionRegistryLite extensionRegistryLite2 = extensionRegistry;
        Object obj3 = null;
        FieldSet<ET> fieldSet = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int positionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (positionForFieldNumber >= 0) {
                    t = t3;
                    try {
                        int typeAndOffsetAt = typeAndOffsetAt(positionForFieldNumber);
                        switch (type(typeAndOffsetAt)) {
                            case 0:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putDouble(t, offset(typeAndOffsetAt), reader.readDouble());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 1:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putFloat(t, offset(typeAndOffsetAt), reader.readFloat());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 2:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t, offset(typeAndOffsetAt), reader.readInt64());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 3:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t, offset(typeAndOffsetAt), reader.readUInt64());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 4:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t, offset(typeAndOffsetAt), reader.readInt32());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 5:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t, offset(typeAndOffsetAt), reader.readFixed64());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 6:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t, offset(typeAndOffsetAt), reader.readFixed32());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 7:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putBoolean(t, offset(typeAndOffsetAt), reader.readBool());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 8:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readString(t, typeAndOffsetAt, reader);
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 9:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                MessageLite messageLite = (MessageLite) mutableMessageFieldForMerge(t, positionForFieldNumber);
                                reader.mergeMessageField(messageLite, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                storeMessageField(t, positionForFieldNumber, messageLite);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 10:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), reader.readBytes());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 11:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t, offset(typeAndOffsetAt), reader.readUInt32());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 12:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                int readEnum = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(readEnum)) {
                                    obj3 = SchemaUtil.storeUnknownEnum(t, fieldNumber, readEnum, obj2, unknownFieldSchema2);
                                    t3 = t;
                                    extensionRegistryLite2 = extensionRegistryLite;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                    break;
                                }
                                UnsafeUtil.putInt(t, offset(typeAndOffsetAt), readEnum);
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 13:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t, offset(typeAndOffsetAt), reader.readSFixed32());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 14:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t, offset(typeAndOffsetAt), reader.readSFixed64());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 15:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t, offset(typeAndOffsetAt), reader.readSInt32());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 16:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t, offset(typeAndOffsetAt), reader.readSInt64());
                                setFieldPresent(t, positionForFieldNumber);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 17:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                MessageLite messageLite2 = (MessageLite) mutableMessageFieldForMerge(t, positionForFieldNumber);
                                reader.mergeGroupField(messageLite2, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                storeMessageField(t, positionForFieldNumber, messageLite2);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 18:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 19:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 20:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 21:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 22:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 23:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 24:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 25:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 26:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readStringList(t, typeAndOffsetAt, reader);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 27:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readMessageList(message, typeAndOffsetAt, reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistry);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 28:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readBytesList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 29:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 30:
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                List<Integer> mutableListAt = this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt));
                                reader.readEnumList(mutableListAt);
                                obj3 = SchemaUtil.filterUnknownEnumList(message, fieldNumber, mutableListAt, getEnumFieldVerifier(positionForFieldNumber), obj3, unknownFieldSchema);
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 31:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 32:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 33:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 34:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 35:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 36:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 37:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 38:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 39:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 40:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 41:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 42:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 43:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 44:
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                List<Integer> mutableListAt2 = this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt));
                                reader.readEnumList(mutableListAt2);
                                obj3 = SchemaUtil.filterUnknownEnumList(message, fieldNumber, mutableListAt2, getEnumFieldVerifier(positionForFieldNumber), obj3, unknownFieldSchema);
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 45:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 46:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 47:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 48:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 49:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readGroupList(message, offset(typeAndOffsetAt), reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistry);
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 50:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                try {
                                    mergeMap(message, positionForFieldNumber, getMapFieldDefaultEntry(positionForFieldNumber), extensionRegistry, reader);
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                    if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                    }
                                    t3 = t;
                                    extensionRegistryLite2 = extensionRegistryLite;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                } catch (Throwable th) {
                                    th = th;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                    obj = obj3;
                                    while (i < this.repeatedFieldOffsetStart) {
                                    }
                                    if (obj != null) {
                                    }
                                    throw th;
                                }
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 51:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 52:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 53:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 54:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 55:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 56:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 57:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 58:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 59:
                                readString(t, typeAndOffsetAt, reader);
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 60:
                                MessageLite messageLite3 = (MessageLite) mutableOneofMessageFieldForMerge(t, fieldNumber, positionForFieldNumber);
                                reader.mergeMessageField(messageLite3, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite2);
                                storeOneofMessageField(t, fieldNumber, positionForFieldNumber, messageLite3);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 61:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), reader.readBytes());
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 62:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 63:
                                int readEnum2 = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier2 != null && !enumFieldVerifier2.isInRange(readEnum2)) {
                                    obj3 = SchemaUtil.storeUnknownEnum(t, fieldNumber, readEnum2, obj3, unknownFieldSchema3);
                                    extensionRegistryLite = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    t3 = t;
                                    extensionRegistryLite2 = extensionRegistryLite;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                    break;
                                }
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Integer.valueOf(readEnum2));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 64:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 65:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 66:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 67:
                                UnsafeUtil.putObject(t, offset(typeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                setOneofPresent(t, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 68:
                                try {
                                    MessageLite messageLite4 = (MessageLite) mutableOneofMessageFieldForMerge(t, fieldNumber, positionForFieldNumber);
                                    reader.mergeGroupField(messageLite4, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite2);
                                    storeOneofMessageField(t, fieldNumber, positionForFieldNumber, messageLite4);
                                    obj2 = obj3;
                                    extensionRegistryLite = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                } catch (InvalidProtocolBufferException.InvalidWireTypeException unused2) {
                                    extensionRegistryLite = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                    }
                                    t3 = t;
                                    extensionRegistryLite2 = extensionRegistryLite;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                } catch (Throwable th2) {
                                    th = th2;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj = obj3;
                                    while (i < this.repeatedFieldOffsetStart) {
                                    }
                                    if (obj != null) {
                                    }
                                    throw th;
                                }
                                t3 = t;
                                extensionRegistryLite2 = extensionRegistryLite;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            default:
                                obj2 = obj3;
                                extensionRegistryLite = extensionRegistryLite2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                if (obj2 == null) {
                                    try {
                                        obj3 = unknownFieldSchema2.getBuilderFromMessage(t);
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused3) {
                                        obj3 = obj2;
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                        }
                                        t3 = t;
                                        extensionRegistryLite2 = extensionRegistryLite;
                                        unknownFieldSchema3 = unknownFieldSchema2;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        obj3 = obj2;
                                        obj = obj3;
                                        while (i < this.repeatedFieldOffsetStart) {
                                        }
                                        if (obj != null) {
                                        }
                                        throw th;
                                    }
                                } else {
                                    obj3 = obj2;
                                }
                                try {
                                    try {
                                        if (!unknownFieldSchema2.mergeOneFieldFrom(obj3, reader)) {
                                            Object obj4 = obj3;
                                            for (int i2 = this.checkInitializedCount; i2 < this.repeatedFieldOffsetStart; i2++) {
                                                obj4 = filterMapUnknownEnumValues(message, this.intArray[i2], obj4, unknownFieldSchema, message);
                                            }
                                            if (obj4 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t, obj4);
                                                return;
                                            }
                                            return;
                                        }
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused4) {
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                            if (obj3 == null) {
                                                obj3 = unknownFieldSchema2.getBuilderFromMessage(t);
                                            }
                                            if (!unknownFieldSchema2.mergeOneFieldFrom(obj3, reader)) {
                                                Object obj5 = obj3;
                                                for (int i3 = this.checkInitializedCount; i3 < this.repeatedFieldOffsetStart; i3++) {
                                                    obj5 = filterMapUnknownEnumValues(message, this.intArray[i3], obj5, unknownFieldSchema, message);
                                                }
                                                if (obj5 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t, obj5);
                                                    return;
                                                }
                                                return;
                                            }
                                        } else if (!reader.skipField()) {
                                            Object obj6 = obj3;
                                            for (int i4 = this.checkInitializedCount; i4 < this.repeatedFieldOffsetStart; i4++) {
                                                obj6 = filterMapUnknownEnumValues(message, this.intArray[i4], obj6, unknownFieldSchema, message);
                                            }
                                            if (obj6 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t, obj6);
                                                return;
                                            }
                                            return;
                                        }
                                        t3 = t;
                                        extensionRegistryLite2 = extensionRegistryLite;
                                        unknownFieldSchema3 = unknownFieldSchema2;
                                    }
                                    t3 = t;
                                    extensionRegistryLite2 = extensionRegistryLite;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                } catch (Throwable th4) {
                                    th = th4;
                                    obj = obj3;
                                    while (i < this.repeatedFieldOffsetStart) {
                                    }
                                    if (obj != null) {
                                    }
                                    throw th;
                                }
                                break;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } else {
                    if (fieldNumber == Integer.MAX_VALUE) {
                        Object obj7 = obj3;
                        for (int i5 = this.checkInitializedCount; i5 < this.repeatedFieldOffsetStart; i5++) {
                            obj7 = filterMapUnknownEnumValues(message, this.intArray[i5], obj7, unknownFieldSchema, message);
                        }
                        if (obj7 != null) {
                            unknownFieldSchema3.setBuilderToMessage(t3, obj7);
                            return;
                        }
                        return;
                    }
                    try {
                        Object findExtensionByNumber = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistryLite2, this.defaultInstance, fieldNumber);
                        if (findExtensionByNumber != null) {
                            if (fieldSet == null) {
                                try {
                                    fieldSet = extensionSchema.getMutableExtensions(message);
                                } catch (Throwable th6) {
                                    th = th6;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    t = t3;
                                    obj = obj3;
                                    while (i < this.repeatedFieldOffsetStart) {
                                    }
                                    if (obj != null) {
                                    }
                                    throw th;
                                }
                            }
                            FieldSet<ET> fieldSet2 = fieldSet;
                            t2 = t3;
                            try {
                                obj3 = extensionSchema.parseExtension(message, reader, findExtensionByNumber, extensionRegistry, fieldSet2, obj3, unknownFieldSchema);
                                fieldSet = fieldSet2;
                            } catch (Throwable th7) {
                                th = th7;
                                t = t2;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj = obj3;
                                for (i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
                                    obj = filterMapUnknownEnumValues(message, this.intArray[i], obj, unknownFieldSchema, message);
                                }
                                if (obj != null) {
                                    unknownFieldSchema2.setBuilderToMessage(t, obj);
                                }
                                throw th;
                            }
                        } else {
                            t2 = t3;
                            if (!unknownFieldSchema3.shouldDiscardUnknownFields(reader)) {
                                if (obj3 == null) {
                                    obj3 = unknownFieldSchema3.getBuilderFromMessage(t2);
                                }
                                if (!unknownFieldSchema3.mergeOneFieldFrom(obj3, reader)) {
                                }
                            } else if (!reader.skipField()) {
                            }
                        }
                        t3 = t2;
                    } catch (Throwable th8) {
                        th = th8;
                        t = t3;
                    }
                }
            } catch (Throwable th9) {
                th = th9;
            }
        }
        int i6 = this.checkInitializedCount;
        Object obj8 = obj3;
        while (i6 < this.repeatedFieldOffsetStart) {
            obj8 = filterMapUnknownEnumValues(message, this.intArray[i6], obj8, unknownFieldSchema, message);
            i6++;
            t2 = t2;
        }
        T t4 = t2;
        if (obj8 != null) {
            unknownFieldSchema3.setBuilderToMessage(t4, obj8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.MessageSchema$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case 12:
            case 13:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint642;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case 16:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return decodeVarint643;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.util.Map, java.util.Map<K, V>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
        int i;
        int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
        int i2 = registers.int1;
        if (i2 < 0 || i2 > limit - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i3 = decodeVarint32 + i2;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (decodeVarint32 < i3) {
            int i4 = decodeVarint32 + 1;
            byte b = data[decodeVarint32];
            if (b < 0) {
                i = ArrayDecoders.decodeVarint32(b, data, i4, registers);
                b = registers.int1;
            } else {
                i = i4;
            }
            int i5 = b >>> 3;
            int i6 = b & 7;
            if (i5 == 1) {
                if (i6 == metadata.keyType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(data, i, limit, metadata.keyType, null, registers);
                    obj = registers.object1;
                } else {
                    decodeVarint32 = ArrayDecoders.skipField(b, data, i, limit, registers);
                }
            } else {
                if (i5 == 2 && i6 == metadata.valueType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(data, i, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                }
                decodeVarint32 = ArrayDecoders.skipField(b, data, i, limit, registers);
            }
        }
        if (decodeVarint32 != i3) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        target.put(obj, obj2);
        return i3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x002f. Please report as an issue. */
    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        int decodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(message, fieldOffset);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(message, fieldOffset, protobufList);
        }
        switch (fieldType) {
            case 18:
            case 35:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, protobufList, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 19:
            case 36:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, protobufList, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 20:
            case 21:
            case 37:
            case 38:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 22:
            case 29:
            case 39:
            case 43:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 23:
            case 32:
            case 40:
            case 46:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, protobufList, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 24:
            case 31:
            case 41:
            case 45:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, protobufList, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 25:
            case 42:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 26:
                if (wireType == 2) {
                    if ((typeAndOffset & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, protobufList, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 27:
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 28:
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 30:
            case 44:
                if (wireType != 2) {
                    if (wireType == 0) {
                        decodeVarint32List = ArrayDecoders.decodeVarint32List(tag, data, position, limit, protobufList, registers);
                    }
                    return position;
                }
                decodeVarint32List = ArrayDecoders.decodePackedVarint32List(data, position, protobufList, registers);
                SchemaUtil.filterUnknownEnumList((Object) message, number, (List<Integer>) protobufList, getEnumFieldVerifier(bufferPosition), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return decodeVarint32List;
            case 33:
            case 47:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 34:
            case 48:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 49:
                if (wireType == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, protobufList, registers);
                }
                return position;
            default:
                return position;
        }
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object object = unsafe.getObject(message, fieldOffset);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(message, fieldOffset, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0023. Please report as an issue. */
    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        long j = this.buffer[bufferPosition + 2] & 1048575;
        switch (fieldType) {
            case 51:
                if (wireType == 1) {
                    unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                    int i = position + 8;
                    unsafe.putInt(message, j, number);
                    return i;
                }
                return position;
            case 52:
                if (wireType == 5) {
                    unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                    int i2 = position + 4;
                    unsafe.putInt(message, j, number);
                    return i2;
                }
                return position;
            case 53:
            case 54:
                if (wireType == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                    unsafe.putInt(message, j, number);
                    return decodeVarint64;
                }
                return position;
            case 55:
            case 62:
                if (wireType == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                    unsafe.putInt(message, j, number);
                    return decodeVarint32;
                }
                return position;
            case 56:
            case 65:
                if (wireType == 1) {
                    unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                    int i3 = position + 8;
                    unsafe.putInt(message, j, number);
                    return i3;
                }
                return position;
            case 57:
            case 64:
                if (wireType == 5) {
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                    int i4 = position + 4;
                    unsafe.putInt(message, j, number);
                    return i4;
                }
                return position;
            case 58:
                if (wireType == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(message, j, number);
                    return decodeVarint642;
                }
                return position;
            case 59:
                if (wireType == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(data, position, registers);
                    int i5 = registers.int1;
                    if (i5 == 0) {
                        unsafe.putObject(message, fieldOffset, "");
                    } else {
                        if ((typeAndOffset & 536870912) != 0 && !Utf8.isValidUtf8(data, decodeVarint322, decodeVarint322 + i5)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        unsafe.putObject(message, fieldOffset, new String(data, decodeVarint322, i5, Internal.UTF_8));
                        decodeVarint322 += i5;
                    }
                    unsafe.putInt(message, j, number);
                    return decodeVarint322;
                }
                return position;
            case 60:
                if (wireType == 2) {
                    Object mutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                    int mergeMessageField = ArrayDecoders.mergeMessageField(mutableOneofMessageFieldForMerge, getMessageFieldSchema(bufferPosition), data, position, limit, registers);
                    storeOneofMessageField(message, number, bufferPosition, mutableOneofMessageFieldForMerge);
                    return mergeMessageField;
                }
                return position;
            case 61:
                if (wireType == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(data, position, registers);
                    unsafe.putObject(message, fieldOffset, registers.object1);
                    unsafe.putInt(message, j, number);
                    return decodeBytes;
                }
                return position;
            case 63:
                if (wireType == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(data, position, registers);
                    int i6 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(bufferPosition);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i6)) {
                        unsafe.putObject(message, fieldOffset, Integer.valueOf(i6));
                        unsafe.putInt(message, j, number);
                    } else {
                        getMutableUnknownFields(message).storeField(tag, Long.valueOf(i6));
                    }
                    return decodeVarint323;
                }
                return position;
            case 66:
                if (wireType == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe.putInt(message, j, number);
                    return decodeVarint324;
                }
                return position;
            case 67:
                if (wireType == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe.putInt(message, j, number);
                    return decodeVarint643;
                }
                return position;
            case 68:
                if (wireType == 3) {
                    Object mutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                    int mergeGroupField = ArrayDecoders.mergeGroupField(mutableOneofMessageFieldForMerge2, getMessageFieldSchema(bufferPosition), data, position, limit, (tag & (-8)) | 4, registers);
                    storeOneofMessageField(message, number, bufferPosition, mutableOneofMessageFieldForMerge2);
                    return mergeGroupField;
                }
                return position;
            default:
                return position;
        }
    }

    private Schema getMessageFieldSchema(int pos) {
        int i = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[i];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i + 1]);
        this.objects[i] = schemaFor;
        return schemaFor;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:98:0x008f. Please report as an issue. */
    public int parseProto2Message(T message, byte[] data, int position, int limit, int endGroup, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe;
        int i;
        MessageSchema<T> messageSchema;
        int i2;
        int i3;
        int i4;
        int i5;
        T t;
        int i6;
        int positionForFieldNumber;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        byte[] bArr;
        int decodeVarint64;
        int i17;
        int i18;
        MessageSchema<T> messageSchema2 = this;
        T t2 = message;
        byte[] bArr2 = data;
        int i19 = limit;
        int i20 = endGroup;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(message);
        Unsafe unsafe2 = UNSAFE;
        int i21 = position;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = -1;
        int i26 = 1048575;
        while (true) {
            if (i21 < i19) {
                int i27 = i21 + 1;
                byte b = bArr2[i21];
                if (b < 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(b, bArr2, i27, registers2);
                    i6 = registers2.int1;
                    i27 = decodeVarint32;
                } else {
                    i6 = b;
                }
                int i28 = i6 >>> 3;
                int i29 = i6 & 7;
                if (i28 > i25) {
                    positionForFieldNumber = messageSchema2.positionForFieldNumber(i28, i22 / 3);
                } else {
                    positionForFieldNumber = messageSchema2.positionForFieldNumber(i28);
                }
                int i30 = positionForFieldNumber;
                if (i30 == -1) {
                    i7 = i28;
                    i8 = i27;
                    i3 = i6;
                    i9 = i24;
                    i10 = i26;
                    unsafe = unsafe2;
                    i = i20;
                    i11 = 0;
                } else {
                    int i31 = messageSchema2.buffer[i30 + 1];
                    int type = type(i31);
                    long offset = offset(i31);
                    int i32 = i6;
                    if (type <= 17) {
                        int i33 = messageSchema2.buffer[i30 + 2];
                        int i34 = 1 << (i33 >>> 20);
                        int i35 = i33 & 1048575;
                        if (i35 != i26) {
                            if (i26 != 1048575) {
                                unsafe2.putInt(t2, i26, i24);
                            }
                            i13 = i35;
                            i12 = unsafe2.getInt(t2, i35);
                        } else {
                            i12 = i24;
                            i13 = i26;
                        }
                        switch (type) {
                            case 0:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 1) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    UnsafeUtil.putDouble(t2, offset, ArrayDecoders.decodeDouble(bArr, i27));
                                    i21 = i27 + 8;
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 1:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 5) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    UnsafeUtil.putFloat(t2, offset, ArrayDecoders.decodeFloat(bArr, i27));
                                    i21 = i27 + 4;
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 2:
                            case 3:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 0) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i27, registers2);
                                    unsafe2.putLong(message, offset, registers2.long1);
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i21 = decodeVarint64;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 4:
                            case 11:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 0) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    i21 = ArrayDecoders.decodeVarint32(bArr, i27, registers2);
                                    unsafe2.putInt(t2, offset, registers2.int1);
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 5:
                            case 14:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 1) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    unsafe2.putLong(message, offset, ArrayDecoders.decodeFixed64(bArr, i27));
                                    i21 = i27 + 8;
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 6:
                            case 13:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 5) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    unsafe2.putInt(t2, offset, ArrayDecoders.decodeFixed32(bArr, i27));
                                    i21 = i27 + 4;
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 7:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 0) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    i21 = ArrayDecoders.decodeVarint64(bArr, i27, registers2);
                                    UnsafeUtil.putBoolean(t2, offset, registers2.long1 != 0);
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 8:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 2) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    if ((536870912 & i31) == 0) {
                                        i21 = ArrayDecoders.decodeString(bArr, i27, registers2);
                                    } else {
                                        i21 = ArrayDecoders.decodeStringRequireUtf8(bArr, i27, registers2);
                                    }
                                    unsafe2.putObject(t2, offset, registers2.object1);
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 9:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 2) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    Object mutableMessageFieldForMerge = messageSchema2.mutableMessageFieldForMerge(t2, i14);
                                    i21 = ArrayDecoders.mergeMessageField(mutableMessageFieldForMerge, messageSchema2.getMessageFieldSchema(i14), data, i27, limit, registers);
                                    messageSchema2.storeMessageField(t2, i14, mutableMessageFieldForMerge);
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 10:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 2) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    i21 = ArrayDecoders.decodeBytes(bArr, i27, registers2);
                                    unsafe2.putObject(t2, offset, registers2.object1);
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 12:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 0) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    i21 = ArrayDecoders.decodeVarint32(bArr, i27, registers2);
                                    int i36 = registers2.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i14);
                                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i36)) {
                                        unsafe2.putInt(t2, offset, i36);
                                        i24 = i12 | i34;
                                        i20 = endGroup;
                                        i22 = i14;
                                        i23 = i16;
                                        i25 = i7;
                                        bArr2 = bArr;
                                        i26 = i15;
                                    } else {
                                        getMutableUnknownFields(message).storeField(i16, Long.valueOf(i36));
                                        i22 = i14;
                                        i24 = i12;
                                        i23 = i16;
                                        i25 = i7;
                                        i20 = endGroup;
                                        bArr2 = bArr;
                                        i26 = i15;
                                    }
                                }
                                break;
                            case 15:
                                bArr = data;
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                if (i29 != 0) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    i21 = ArrayDecoders.decodeVarint32(bArr, i27, registers2);
                                    unsafe2.putInt(t2, offset, CodedInputStream.decodeZigZag32(registers2.int1));
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 16:
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                bArr = data;
                                if (i29 != 0) {
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i27, registers2);
                                    unsafe2.putLong(message, offset, CodedInputStream.decodeZigZag64(registers2.long1));
                                    i24 = i12 | i34;
                                    i20 = endGroup;
                                    i21 = decodeVarint64;
                                    i22 = i14;
                                    i23 = i16;
                                    i25 = i7;
                                    bArr2 = bArr;
                                    i26 = i15;
                                }
                            case 17:
                                if (i29 != 3) {
                                    i7 = i28;
                                    i14 = i30;
                                    i15 = i13;
                                    i16 = i32;
                                    i10 = i15;
                                    i8 = i27;
                                    i11 = i14;
                                    unsafe = unsafe2;
                                    i9 = i12;
                                    i3 = i16;
                                    i = endGroup;
                                    break;
                                } else {
                                    Object mutableMessageFieldForMerge2 = messageSchema2.mutableMessageFieldForMerge(t2, i30);
                                    i21 = ArrayDecoders.mergeGroupField(mutableMessageFieldForMerge2, messageSchema2.getMessageFieldSchema(i30), data, i27, limit, (i28 << 3) | 4, registers);
                                    messageSchema2.storeMessageField(t2, i30, mutableMessageFieldForMerge2);
                                    i24 = i12 | i34;
                                    i26 = i13;
                                    i20 = endGroup;
                                    i22 = i30;
                                    i23 = i32;
                                    i25 = i28;
                                    bArr2 = data;
                                }
                            default:
                                i7 = i28;
                                i14 = i30;
                                i15 = i13;
                                i16 = i32;
                                i10 = i15;
                                i8 = i27;
                                i11 = i14;
                                unsafe = unsafe2;
                                i9 = i12;
                                i3 = i16;
                                i = endGroup;
                                break;
                        }
                    } else {
                        i7 = i28;
                        i10 = i26;
                        i9 = i24;
                        if (type == 27) {
                            if (i29 == 2) {
                                Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t2, offset);
                                if (!protobufList.isModifiable()) {
                                    int size = protobufList.size();
                                    protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                    unsafe2.putObject(t2, offset, protobufList);
                                }
                                i21 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(i30), i32, data, i27, limit, protobufList, registers);
                                i22 = i30;
                                i23 = i32;
                                i26 = i10;
                                i24 = i9;
                                i25 = i7;
                                bArr2 = data;
                                i20 = endGroup;
                            } else {
                                i17 = i27;
                                unsafe = unsafe2;
                                i11 = i30;
                                i18 = i32;
                                i = endGroup;
                                i8 = i17;
                            }
                        } else if (type <= 49) {
                            int i37 = i27;
                            unsafe = unsafe2;
                            i11 = i30;
                            i18 = i32;
                            i21 = parseRepeatedField(message, data, i27, limit, i32, i7, i29, i30, i31, type, offset, registers);
                            if (i21 != i37) {
                                messageSchema2 = this;
                                t2 = message;
                                bArr2 = data;
                                i19 = limit;
                                i20 = endGroup;
                                registers2 = registers;
                                i26 = i10;
                                i24 = i9;
                                i22 = i11;
                                i23 = i18;
                                i25 = i7;
                                unsafe2 = unsafe;
                            } else {
                                i = endGroup;
                                i8 = i21;
                            }
                        } else {
                            i17 = i27;
                            unsafe = unsafe2;
                            i11 = i30;
                            i18 = i32;
                            if (type != 50) {
                                i21 = parseOneofField(message, data, i17, limit, i18, i7, i29, i31, type, offset, i11, registers);
                                if (i21 != i17) {
                                    messageSchema2 = this;
                                    t2 = message;
                                    bArr2 = data;
                                    i19 = limit;
                                    i20 = endGroup;
                                    registers2 = registers;
                                    i26 = i10;
                                    i24 = i9;
                                    i22 = i11;
                                    i23 = i18;
                                    i25 = i7;
                                    unsafe2 = unsafe;
                                } else {
                                    i = endGroup;
                                    i8 = i21;
                                }
                            } else if (i29 == 2) {
                                i21 = parseMapField(message, data, i17, limit, i11, offset, registers);
                                if (i21 != i17) {
                                    messageSchema2 = this;
                                    t2 = message;
                                    bArr2 = data;
                                    i19 = limit;
                                    i20 = endGroup;
                                    registers2 = registers;
                                    i26 = i10;
                                    i24 = i9;
                                    i22 = i11;
                                    i23 = i18;
                                    i25 = i7;
                                    unsafe2 = unsafe;
                                } else {
                                    i = endGroup;
                                    i8 = i21;
                                }
                            } else {
                                i = endGroup;
                                i8 = i17;
                            }
                        }
                        i3 = i18;
                    }
                }
                if (i3 != i || i == 0) {
                    if (this.hasExtensions && registers.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                        i21 = ArrayDecoders.decodeExtensionOrUnknownField(i3, data, i8, limit, message, this.defaultInstance, this.unknownFieldSchema, registers);
                    } else {
                        i21 = ArrayDecoders.decodeUnknownField(i3, data, i8, limit, getMutableUnknownFields(message), registers);
                    }
                    t2 = message;
                    bArr2 = data;
                    i19 = limit;
                    i23 = i3;
                    messageSchema2 = this;
                    registers2 = registers;
                    i26 = i10;
                    i24 = i9;
                    i22 = i11;
                    i25 = i7;
                    unsafe2 = unsafe;
                    i20 = i;
                } else {
                    i5 = 1048575;
                    messageSchema = this;
                    i2 = i8;
                    i4 = i10;
                    i24 = i9;
                }
            } else {
                int i38 = i26;
                unsafe = unsafe2;
                i = i20;
                messageSchema = messageSchema2;
                i2 = i21;
                i3 = i23;
                i4 = i38;
                i5 = 1048575;
            }
        }
        if (i4 != i5) {
            t = message;
            unsafe.putInt(t, i4, i24);
        } else {
            t = message;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i39 = messageSchema.checkInitializedCount; i39 < messageSchema.repeatedFieldOffsetStart; i39++) {
            unknownFieldSetLite = (UnknownFieldSetLite) filterMapUnknownEnumValues(message, messageSchema.intArray[i39], unknownFieldSetLite, messageSchema.unknownFieldSchema, message);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t, unknownFieldSetLite);
        }
        if (i == 0) {
            if (i2 != limit) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i2 > limit || i3 != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T message, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        long offset = offset(typeAndOffsetAt(pos));
        if (!isFieldPresent(message, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(message, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeMessageField(T message, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setFieldPresent(message, pos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T message, int fieldNumber, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(message, fieldNumber, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(message, offset(typeAndOffsetAt(pos)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeOneofMessageField(T message, int fieldNumber, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setOneofPresent(message, fieldNumber, pos);
    }

    /* JADX WARN: Code restructure failed: missing block: B:129:0x0252, code lost:
    
        if (r0 != r15) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0254, code lost:
    
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r10 = r18;
        r1 = r19;
        r2 = r20;
        r6 = r24;
        r7 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02c1, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x029b, code lost:
    
        if (r0 != r15) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x02be, code lost:
    
        if (r0 != r15) goto L98;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0096. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int parseProto3Message(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        byte b;
        int i;
        int positionForFieldNumber;
        int i2;
        int i3;
        Unsafe unsafe;
        int i4;
        int i5;
        int i6;
        Unsafe unsafe2;
        int i7;
        int i8;
        int i9;
        int decodeVarint64;
        Unsafe unsafe3;
        MessageSchema<T> messageSchema = this;
        T t = message;
        byte[] bArr = data;
        int i10 = limit;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(message);
        Unsafe unsafe4 = UNSAFE;
        int i11 = -1;
        int i12 = position;
        int i13 = -1;
        int i14 = 0;
        int i15 = 0;
        int i16 = 1048575;
        while (i12 < i10) {
            int i17 = i12 + 1;
            byte b2 = bArr[i12];
            if (b2 < 0) {
                i = ArrayDecoders.decodeVarint32(b2, bArr, i17, registers2);
                b = registers2.int1;
            } else {
                b = b2;
                i = i17;
            }
            int i18 = b >>> 3;
            int i19 = b & 7;
            if (i18 > i13) {
                positionForFieldNumber = messageSchema.positionForFieldNumber(i18, i14 / 3);
            } else {
                positionForFieldNumber = messageSchema.positionForFieldNumber(i18);
            }
            int i20 = positionForFieldNumber;
            if (i20 == i11) {
                i2 = i;
                i3 = i18;
                unsafe = unsafe4;
                i4 = i11;
                i5 = 0;
            } else {
                int i21 = messageSchema.buffer[i20 + 1];
                int type = type(i21);
                Unsafe unsafe5 = unsafe4;
                long offset = offset(i21);
                if (type <= 17) {
                    int i22 = messageSchema.buffer[i20 + 2];
                    int i23 = 1 << (i22 >>> 20);
                    int i24 = i22 & 1048575;
                    if (i24 != i16) {
                        if (i16 != 1048575) {
                            long j = i16;
                            unsafe3 = unsafe5;
                            unsafe3.putInt(t, j, i15);
                        } else {
                            unsafe3 = unsafe5;
                        }
                        if (i24 != 1048575) {
                            i15 = unsafe3.getInt(t, i24);
                        }
                        unsafe2 = unsafe3;
                        i16 = i24;
                    } else {
                        unsafe2 = unsafe5;
                    }
                    switch (type) {
                        case 0:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 1) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                UnsafeUtil.putDouble(t, offset, ArrayDecoders.decodeDouble(bArr, i));
                                i12 = i + 8;
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 1:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 5) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                UnsafeUtil.putFloat(t, offset, ArrayDecoders.decodeFloat(bArr, i));
                                i12 = i + 4;
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 2:
                        case 3:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 0) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers2);
                                unsafe2.putLong(message, offset, registers2.long1);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i12 = decodeVarint64;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 4:
                        case 11:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 0) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                i12 = ArrayDecoders.decodeVarint32(bArr, i, registers2);
                                unsafe2.putInt(t, offset, registers2.int1);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 5:
                        case 14:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 1) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                unsafe2.putLong(message, offset, ArrayDecoders.decodeFixed64(bArr, i));
                                i12 = i + 8;
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 6:
                        case 13:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 5) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                unsafe2.putInt(t, offset, ArrayDecoders.decodeFixed32(bArr, i));
                                i12 = i + 4;
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 7:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 0) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                i12 = ArrayDecoders.decodeVarint64(bArr, i, registers2);
                                UnsafeUtil.putBoolean(t, offset, registers2.long1 != 0);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 8:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 2) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                if ((i21 & 536870912) == 0) {
                                    i12 = ArrayDecoders.decodeString(bArr, i, registers2);
                                } else {
                                    i12 = ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers2);
                                }
                                unsafe2.putObject(t, offset, registers2.object1);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 9:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 2) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                Object mutableMessageFieldForMerge = messageSchema.mutableMessageFieldForMerge(t, i6);
                                i12 = ArrayDecoders.mergeMessageField(mutableMessageFieldForMerge, messageSchema.getMessageFieldSchema(i6), data, i, limit, registers);
                                messageSchema.storeMessageField(t, i6, mutableMessageFieldForMerge);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 10:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 2) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                i12 = ArrayDecoders.decodeBytes(bArr, i, registers2);
                                unsafe2.putObject(t, offset, registers2.object1);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 12:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 0) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                i12 = ArrayDecoders.decodeVarint32(bArr, i, registers2);
                                unsafe2.putInt(t, offset, registers2.int1);
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 15:
                            i6 = i20;
                            i3 = i18;
                            if (i19 != 0) {
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                i12 = ArrayDecoders.decodeVarint32(bArr, i, registers2);
                                unsafe2.putInt(t, offset, CodedInputStream.decodeZigZag32(registers2.int1));
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        case 16:
                            if (i19 != 0) {
                                i6 = i20;
                                i3 = i18;
                                i2 = i;
                                unsafe = unsafe2;
                                i5 = i6;
                                i4 = -1;
                                break;
                            } else {
                                decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers2);
                                i6 = i20;
                                i3 = i18;
                                unsafe2.putLong(message, offset, CodedInputStream.decodeZigZag64(registers2.long1));
                                i15 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i6;
                                i12 = decodeVarint64;
                                i13 = i3;
                                i11 = -1;
                                i10 = limit;
                                break;
                            }
                        default:
                            i6 = i20;
                            i3 = i18;
                            i2 = i;
                            unsafe = unsafe2;
                            i5 = i6;
                            i4 = -1;
                            break;
                    }
                } else {
                    i3 = i18;
                    i6 = i20;
                    unsafe2 = unsafe5;
                    if (type != 27) {
                        if (type <= 49) {
                            int i25 = i;
                            i8 = i15;
                            i9 = i16;
                            unsafe = unsafe2;
                            i4 = -1;
                            i5 = i6;
                            i12 = parseRepeatedField(message, data, i, limit, b, i3, i19, i6, i21, type, offset, registers);
                        } else {
                            i7 = i;
                            i8 = i15;
                            i9 = i16;
                            unsafe = unsafe2;
                            i5 = i6;
                            i4 = -1;
                            if (type != 50) {
                                i12 = parseOneofField(message, data, i7, limit, b, i3, i19, i21, type, offset, i5, registers);
                            } else if (i19 == 2) {
                                i12 = parseMapField(message, data, i7, limit, i5, offset, registers);
                            }
                        }
                        unsafe4 = unsafe;
                    } else if (i19 == 2) {
                        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t, offset);
                        if (!protobufList.isModifiable()) {
                            int size = protobufList.size();
                            protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                            unsafe2.putObject(t, offset, protobufList);
                        }
                        i12 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(i6), b, data, i, limit, protobufList, registers);
                        i15 = i15;
                        unsafe4 = unsafe2;
                        i14 = i6;
                        i13 = i3;
                        i11 = -1;
                        i10 = limit;
                    } else {
                        i7 = i;
                        i8 = i15;
                        i9 = i16;
                        unsafe = unsafe2;
                        i5 = i6;
                        i4 = -1;
                    }
                    i2 = i7;
                    i15 = i8;
                    i16 = i9;
                }
            }
            i12 = ArrayDecoders.decodeUnknownField(b, data, i2, limit, getMutableUnknownFields(message), registers);
            messageSchema = this;
            t = message;
            bArr = data;
            i10 = limit;
            registers2 = registers;
            i11 = i4;
            i13 = i3;
            i14 = i5;
            unsafe4 = unsafe;
        }
        int i26 = i15;
        Unsafe unsafe6 = unsafe4;
        if (i16 != 1048575) {
            unsafe6.putInt(message, i16, i26);
        }
        if (i12 == limit) {
            return i12;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(message, data, position, limit, registers);
        } else {
            parseProto2Message(message, data, position, limit, 0, registers);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T message) {
        if (isMutable(message)) {
            if (message instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long offset = offset(typeAndOffsetAt);
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    switch (type) {
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                            this.listFieldSchema.makeImmutableListAt(message, offset);
                            break;
                        case 50:
                            Unsafe unsafe = UNSAFE;
                            Object object = unsafe.getObject(message, offset);
                            if (object != null) {
                                unsafe.putObject(message, offset, this.mapFieldSchema.toImmutable(object));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                if (isFieldPresent(message, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(message, offset));
                }
            }
            this.unknownFieldSchema.makeImmutable(message);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(message);
            }
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object object = UnsafeUtil.getObject(message, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(message, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        Internal.EnumVerifier enumFieldVerifier;
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        return (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) ? ub : (UB) filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T message) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.checkInitializedCount) {
            int i6 = this.intArray[i5];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i4 = UNSAFE.getInt(message, i8);
                }
                i2 = i4;
                i = i8;
            } else {
                i = i3;
                i2 = i4;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(message, i6, i, i2, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type == 9 || type == 17) {
                if (isFieldPresent(message, i6, i, i2, i9) && !isInitialized(message, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                    return false;
                }
            } else {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(message, numberAt, i6) && !isInitialized(message, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(message, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(message, typeAndOffsetAt, i6)) {
                    return false;
                }
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(message).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(message, offset(typeAndOffset)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            if (!messageFieldSchema.isInitialized(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.crypto.tink.shaded.protobuf.Schema] */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? r5 = 0;
        for (Object obj : forMapData.values()) {
            r5 = r5;
            if (r5 == 0) {
                r5 = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!r5.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static boolean isMutable(Object message) {
        if (message == null) {
            return false;
        }
        if (message instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) message).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object message) {
        if (isMutable(message)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + message);
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(message, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(message, offset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(message, offset)) != 0;
            case 2:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 3:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 4:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 5:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 6:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(message, offset);
            case 8:
                Object object = UnsafeUtil.getObject(message, offset);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(message, offset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
            case 11:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 12:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 13:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 14:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 15:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 16:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 17:
                return UnsafeUtil.getObject(message, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setFieldPresent(T message, int pos) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(message, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(message, j));
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffsetAt(pos) & 1048575)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        long presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos) & 1048575;
        return UnsafeUtil.getInt(message, presenceMaskAndOffsetAt) == UnsafeUtil.getInt(other, presenceMaskAndOffsetAt);
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        UnsafeUtil.putInt(message, presenceMaskAndOffsetAt(pos) & 1048575, fieldNumber);
    }

    private int positionForFieldNumber(final int number) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, 0);
    }

    private int positionForFieldNumber(final int number, final int min) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, min);
    }

    private int slowPositionForFieldNumber(final int number, int min) {
        int length = (this.buffer.length / 3) - 1;
        while (min <= length) {
            int i = (length + min) >>> 1;
            int i2 = i * 3;
            int numberAt = numberAt(i2);
            if (number == numberAt) {
                return i2;
            }
            if (number < numberAt) {
                length = i - 1;
            } else {
                min = i + 1;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
