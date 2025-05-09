package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
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

    private static boolean isEnforceUtf8(int i) {
        return (i & 536870912) != 0;
    }

    private static boolean isRequired(int i) {
        return (i & 268435456) != 0;
    }

    private static long offset(int i) {
        return i & 1048575;
    }

    private static int type(int i) {
        return (i & FIELD_TYPE_MASK) >>> 20;
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(messageLite);
        this.useCachedSizeField = z2;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
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
    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
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
        boolean z3 = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = rawMessageInfo.getStringInfo();
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
        Object[] objects = rawMessageInfo.getObjects();
        Class<?> cls = rawMessageInfo.getDefaultInstance().getClass();
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
        return new MessageSchema<>(iArr2, objArr, charAt, charAt2, rawMessageInfo.getDefaultInstance(), z3, false, iArr, charAt5, i55, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = cls.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
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
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
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
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), z, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i, Object[] objArr) {
        int objectFieldOffset;
        int id2;
        long objectFieldOffset2;
        int i2;
        int i3;
        OneofInfo oneof = fieldInfo.getOneof();
        if (oneof != null) {
            id2 = fieldInfo.getType().id() + 51;
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            objectFieldOffset2 = UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        } else {
            FieldType type = fieldInfo.getType();
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getField());
            id2 = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fieldInfo.getPresenceField();
                i2 = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                i3 = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
            } else if (fieldInfo.getCachedSizeField() == null) {
                i2 = 0;
                i3 = 0;
            } else {
                objectFieldOffset2 = UnsafeUtil.objectFieldOffset(fieldInfo.getCachedSizeField());
            }
            iArr[i] = fieldInfo.getFieldNumber();
            iArr[i + 1] = (fieldInfo.isRequired() ? 268435456 : 0) | (!fieldInfo.isEnforceUtf8() ? 536870912 : 0) | (id2 << 20) | objectFieldOffset;
            iArr[i + 2] = i2 | (i3 << 20);
            Class<?> messageFieldClass = fieldInfo.getMessageFieldClass();
            if (fieldInfo.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objArr[((i / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fieldInfo.getEnumVerifier() != null) {
                        objArr[((i / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            int i4 = (i / 3) * 2;
            objArr[i4] = fieldInfo.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objArr[i4 + 1] = messageFieldClass;
                return;
            } else {
                if (fieldInfo.getEnumVerifier() != null) {
                    objArr[i4 + 1] = fieldInfo.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        i2 = (int) objectFieldOffset2;
        i3 = 0;
        iArr[i] = fieldInfo.getFieldNumber();
        if (!fieldInfo.isEnforceUtf8()) {
        }
        iArr[i + 1] = (fieldInfo.isRequired() ? 268435456 : 0) | (!fieldInfo.isEnforceUtf8() ? 536870912 : 0) | (id2 << 20) | objectFieldOffset;
        iArr[i + 2] = i2 | (i3 << 20);
        Class<?> messageFieldClass2 = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() != null) {
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T t, T t2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(t, t2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t).equals(this.unknownFieldSchema.getFromMessage(t2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t).equals(this.extensionSchema.getExtensions(t2));
        }
        return true;
    }

    private boolean equals(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(t, t2, i) && Double.doubleToLongBits(UnsafeUtil.getDouble(t, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(t2, offset));
            case 1:
                return arePresentForEquals(t, t2, i) && Float.floatToIntBits(UnsafeUtil.getFloat(t, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(t2, offset));
            case 2:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 3:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 4:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 5:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 6:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 7:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getBoolean(t, offset) == UnsafeUtil.getBoolean(t2, offset);
            case 8:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 9:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 10:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 11:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 12:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 13:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 14:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 15:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 16:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 17:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
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
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
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
                return isOneofCaseEqual(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            default:
                return true;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0019. Please report as an issue. */
    @Override // com.google.protobuf.Schema
    public int hashCode(T t) {
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
                    hashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(t, offset)));
                    i2 = i + hashLong;
                    break;
                case 1:
                    i = i2 * 53;
                    hashLong = Float.floatToIntBits(UnsafeUtil.getFloat(t, offset));
                    i2 = i + hashLong;
                    break;
                case 2:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 3:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 4:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 5:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 6:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 7:
                    i = i2 * 53;
                    hashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(t, offset));
                    i2 = i + hashLong;
                    break;
                case 8:
                    i = i2 * 53;
                    hashLong = ((String) UnsafeUtil.getObject(t, offset)).hashCode();
                    i2 = i + hashLong;
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(t, offset);
                    if (object != null) {
                        i4 = object.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 11:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 12:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 13:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 14:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 15:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 16:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(t, offset);
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
                    hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 50:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 51:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(t, offset)));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Float.floatToIntBits(oneofFloatAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashBoolean(oneofBooleanAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = ((String) UnsafeUtil.getObject(t, offset)).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.unknownFieldSchema.getFromMessage(t).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(t).hashCode() : hashCode;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, T t2) {
        checkMutable(t);
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(t, t2, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t, t2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t, t2);
        }
    }

    private void mergeSingleField(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putDouble(t, offset, UnsafeUtil.getDouble(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putFloat(t, offset, UnsafeUtil.getFloat(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putBoolean(t, offset, UnsafeUtil.getBoolean(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 9:
                mergeMessage(t, t2, i);
                return;
            case 10:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    return;
                }
                return;
            case 17:
                mergeMessage(t, t2, i);
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
                this.listFieldSchema.mergeListsAt(t, t2, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t, t2, offset);
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
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(t, t2, i);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setOneofPresent(t, numberAt, i);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(t, t2, i);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T t, T t2, int i) {
        if (isFieldPresent(t2, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t2, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + t2);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isFieldPresent(t, i)) {
                if (!isMutable(object)) {
                    unsafe.putObject(t, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(t, offset, newInstance);
                }
                setFieldPresent(t, i);
                return;
            }
            Object object2 = unsafe.getObject(t, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(t, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T t, T t2, int i) {
        int numberAt = numberAt(i);
        if (isOneofPresent(t2, numberAt, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t2, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + t2);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isOneofPresent(t, numberAt, i)) {
                if (!isMutable(object)) {
                    unsafe.putObject(t, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(t, offset, newInstance);
                }
                setOneofPresent(t, numberAt, i);
                return;
            }
            Object object2 = unsafe.getObject(t, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(t, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T t) {
        return this.proto3 ? getSerializedSizeProto3(t) : getSerializedSizeProto2(t);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x005d. Please report as an issue. */
    private int getSerializedSizeProto2(T t) {
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
                    i7 = unsafe.getInt(t, i8);
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
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(t, offset));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 3:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(t, offset));
                        i6 += computeDoubleSize;
                        break;
                    }
                case 4:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(t, offset));
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
                        Object object = unsafe.getObject(t, offset);
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
                        computeBoolSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 10:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 11:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 12:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(t, offset));
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
                        computeBoolSize = CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 16:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 17:
                    if ((i7 & i2) != 0) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 18:
                    computeBoolSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeBoolSize;
                    break;
                case 19:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 20:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeInt64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 21:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeUInt64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 22:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeInt32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 23:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 24:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 25:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeBoolList(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 26:
                    computeBoolSize = SchemaUtil.computeSizeStringList(numberAt, (List) unsafe.getObject(t, offset));
                    i6 += computeBoolSize;
                    break;
                case 27:
                    computeBoolSize = SchemaUtil.computeSizeMessageList(numberAt, (List) unsafe.getObject(t, offset), getMessageFieldSchema(i5));
                    i6 += computeBoolSize;
                    break;
                case 28:
                    computeBoolSize = SchemaUtil.computeSizeByteStringList(numberAt, (List) unsafe.getObject(t, offset));
                    i6 += computeBoolSize;
                    break;
                case 29:
                    computeBoolSize = SchemaUtil.computeSizeUInt32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeBoolSize;
                    break;
                case 30:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeEnumList(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 31:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 32:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 33:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeSInt32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 34:
                    z = false;
                    computeSizeFixed32List = SchemaUtil.computeSizeSInt64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i6 += computeSizeFixed32List;
                    break;
                case 35:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 36:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 37:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 38:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 39:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 40:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 41:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 42:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 43:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 44:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 45:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 46:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 47:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 48:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSFixed32Size = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 49:
                    computeBoolSize = SchemaUtil.computeSizeGroupList(numberAt, (List) unsafe.getObject(t, offset), getMessageFieldSchema(i5));
                    i6 += computeBoolSize;
                    break;
                case 50:
                    computeBoolSize = this.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(t, offset), getMapFieldDefaultEntry(i5));
                    i6 += computeBoolSize;
                    break;
                case 51:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i6 += computeBoolSize;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i6 += computeBoolSize;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeSFixed32Size = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i6 += computeBoolSize;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, numberAt, i5)) {
                        Object object2 = unsafe.getObject(t, offset);
                        if (object2 instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        }
                        i6 += computeBoolSize;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeSFixed32Size = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i6 += computeSFixed32Size;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i6 += computeBoolSize;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(t, offset));
                        i6 += computeBoolSize;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, numberAt, i5)) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t, offset), getMessageFieldSchema(i5));
                        i6 += computeBoolSize;
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        int unknownFieldsSerializedSize = i6 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t).getSerializedSize() : unknownFieldsSerializedSize;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x003d. Please report as an issue. */
    private int getSerializedSizeProto3(T t) {
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
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, UnsafeUtil.getLong(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, UnsafeUtil.getLong(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, UnsafeUtil.getInt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(t, i2)) {
                        Object object = UnsafeUtil.getObject(t, offset);
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
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject(t, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, UnsafeUtil.getInt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, UnsafeUtil.getInt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, UnsafeUtil.getInt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, UnsafeUtil.getLong(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(t, i2)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject(t, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 19:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 20:
                    computeDoubleSize = SchemaUtil.computeSizeInt64List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 21:
                    computeDoubleSize = SchemaUtil.computeSizeUInt64List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 22:
                    computeDoubleSize = SchemaUtil.computeSizeInt32List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 23:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 24:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 25:
                    computeDoubleSize = SchemaUtil.computeSizeBoolList(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 26:
                    computeDoubleSize = SchemaUtil.computeSizeStringList(numberAt, listAt(t, offset));
                    i += computeDoubleSize;
                    break;
                case 27:
                    computeDoubleSize = SchemaUtil.computeSizeMessageList(numberAt, listAt(t, offset), getMessageFieldSchema(i2));
                    i += computeDoubleSize;
                    break;
                case 28:
                    computeDoubleSize = SchemaUtil.computeSizeByteStringList(numberAt, listAt(t, offset));
                    i += computeDoubleSize;
                    break;
                case 29:
                    computeDoubleSize = SchemaUtil.computeSizeUInt32List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 30:
                    computeDoubleSize = SchemaUtil.computeSizeEnumList(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 31:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 32:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 33:
                    computeDoubleSize = SchemaUtil.computeSizeSInt32List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 34:
                    computeDoubleSize = SchemaUtil.computeSizeSInt64List(numberAt, listAt(t, offset), false);
                    i += computeDoubleSize;
                    break;
                case 35:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 36:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 37:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 38:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 39:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 40:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 41:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 42:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 43:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 44:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 45:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 46:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 47:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 48:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i3, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeDoubleSize = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i += computeDoubleSize;
                        break;
                    }
                case 49:
                    computeDoubleSize = SchemaUtil.computeSizeGroupList(numberAt, listAt(t, offset), getMessageFieldSchema(i2));
                    i += computeDoubleSize;
                    break;
                case 50:
                    computeDoubleSize = this.mapFieldSchema.getSerializedSize(numberAt, UnsafeUtil.getObject(t, offset), getMapFieldDefaultEntry(i2));
                    i += computeDoubleSize;
                    break;
                case 51:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t, numberAt, i2)) {
                        Object object2 = UnsafeUtil.getObject(t, offset);
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
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject(t, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(t, offset));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t, numberAt, i2)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject(t, offset), getMessageFieldSchema(i2));
                        i += computeDoubleSize;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return i + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t) {
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t));
    }

    private static List<?> listAt(Object obj, long j) {
        return (List) UnsafeUtil.getObject(obj, j);
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T t, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(t, writer);
        } else {
            writeFieldsInAscendingOrderProto2(t, writer);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:228:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto2(T t, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        int i;
        int i2;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(t);
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
                            i5 = unsafe.getInt(t, i7);
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
                                writer.writeDouble(numberAt, doubleAt(t, offset));
                                continue;
                            }
                        case 1:
                            if ((i2 & i5) != 0) {
                                writer.writeFloat(numberAt, floatAt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 2:
                            if ((i2 & i5) != 0) {
                                writer.writeInt64(numberAt, unsafe.getLong(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 3:
                            if ((i2 & i5) != 0) {
                                writer.writeUInt64(numberAt, unsafe.getLong(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 4:
                            if ((i2 & i5) != 0) {
                                writer.writeInt32(numberAt, unsafe.getInt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 5:
                            if ((i2 & i5) != 0) {
                                writer.writeFixed64(numberAt, unsafe.getLong(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 6:
                            if ((i2 & i5) != 0) {
                                writer.writeFixed32(numberAt, unsafe.getInt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 7:
                            if ((i2 & i5) != 0) {
                                writer.writeBool(numberAt, booleanAt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 8:
                            if ((i2 & i5) != 0) {
                                writeString(numberAt, unsafe.getObject(t, offset), writer);
                                break;
                            } else {
                                continue;
                            }
                        case 9:
                            if ((i2 & i5) != 0) {
                                writer.writeMessage(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i));
                                break;
                            } else {
                                continue;
                            }
                        case 10:
                            if ((i2 & i5) != 0) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 11:
                            if ((i2 & i5) != 0) {
                                writer.writeUInt32(numberAt, unsafe.getInt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 12:
                            if ((i2 & i5) != 0) {
                                writer.writeEnum(numberAt, unsafe.getInt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 13:
                            if ((i2 & i5) != 0) {
                                writer.writeSFixed32(numberAt, unsafe.getInt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 14:
                            if ((i2 & i5) != 0) {
                                writer.writeSFixed64(numberAt, unsafe.getLong(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 15:
                            if ((i2 & i5) != 0) {
                                writer.writeSInt32(numberAt, unsafe.getInt(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 16:
                            if ((i2 & i5) != 0) {
                                writer.writeSInt64(numberAt, unsafe.getLong(t, offset));
                                break;
                            } else {
                                continue;
                            }
                        case 17:
                            if ((i2 & i5) != 0) {
                                writer.writeGroup(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i));
                                break;
                            } else {
                                continue;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) unsafe.getObject(t, offset), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) unsafe.getObject(t, offset), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) unsafe.getObject(t, offset), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, false);
                            continue;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) unsafe.getObject(t, offset), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) unsafe.getObject(t, offset), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, unsafe.getObject(t, offset), i);
                            break;
                        case 51:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(t, offset));
                                break;
                            }
                            break;
                        case 52:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeFloat(numberAt, oneofFloatAt(t, offset));
                                break;
                            }
                            break;
                        case 53:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeInt64(numberAt, oneofLongAt(t, offset));
                                break;
                            }
                            break;
                        case 54:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeUInt64(numberAt, oneofLongAt(t, offset));
                                break;
                            }
                            break;
                        case 55:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeInt32(numberAt, oneofIntAt(t, offset));
                                break;
                            }
                            break;
                        case 56:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeFixed64(numberAt, oneofLongAt(t, offset));
                                break;
                            }
                            break;
                        case 57:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeFixed32(numberAt, oneofIntAt(t, offset));
                                break;
                            }
                            break;
                        case 58:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeBool(numberAt, oneofBooleanAt(t, offset));
                                break;
                            }
                            break;
                        case 59:
                            if (isOneofPresent(t, numberAt, i)) {
                                writeString(numberAt, unsafe.getObject(t, offset), writer);
                                break;
                            }
                            break;
                        case 60:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeMessage(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i));
                                break;
                            }
                            break;
                        case 61:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(t, offset));
                                break;
                            }
                            break;
                        case 62:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeUInt32(numberAt, oneofIntAt(t, offset));
                                break;
                            }
                            break;
                        case 63:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeEnum(numberAt, oneofIntAt(t, offset));
                                break;
                            }
                            break;
                        case 64:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(t, offset));
                                break;
                            }
                            break;
                        case 65:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(t, offset));
                                break;
                            }
                            break;
                        case 66:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSInt32(numberAt, oneofIntAt(t, offset));
                                break;
                            }
                            break;
                        case 67:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSInt64(numberAt, oneofLongAt(t, offset));
                                break;
                            }
                            break;
                        case 68:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeGroup(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i));
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
                writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
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
        writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:275:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto3(T t, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        int i;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(t);
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
                            if (isFieldPresent(t, i)) {
                                writer.writeDouble(numberAt, doubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(t, i)) {
                                writer.writeFloat(numberAt, floatAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(t, i)) {
                                writer.writeInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(t, i)) {
                                writer.writeUInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(t, i)) {
                                writer.writeInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(t, i)) {
                                writer.writeFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(t, i)) {
                                writer.writeFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(t, i)) {
                                writer.writeBool(numberAt, booleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(t, i)) {
                                writeString(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(t, i)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(t, i)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(t, i)) {
                                writer.writeUInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(t, i)) {
                                writer.writeEnum(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(t, i)) {
                                writer.writeSFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(t, i)) {
                                writer.writeSFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(t, i)) {
                                writer.writeSInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(t, i)) {
                                writer.writeSInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(t, i)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), i);
                            break;
                        case 51:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeFloat(numberAt, oneofFloatAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeUInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeBool(numberAt, oneofBooleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(t, numberAt, i)) {
                                writeString(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeUInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeEnum(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeSInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(t, numberAt, i)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
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
                writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        while (i < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:275:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInDescendingOrder(T t, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        writeUnknownInMessageTo(this.unknownFieldSchema, t, writer);
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(t);
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
                            if (isFieldPresent(t, length)) {
                                writer.writeDouble(numberAt, doubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(t, length)) {
                                writer.writeFloat(numberAt, floatAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(t, length)) {
                                writer.writeInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(t, length)) {
                                writer.writeUInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(t, length)) {
                                writer.writeInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(t, length)) {
                                writer.writeFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(t, length)) {
                                writer.writeFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(t, length)) {
                                writer.writeBool(numberAt, booleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(t, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(t, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(t, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(t, length)) {
                                writer.writeUInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(t, length)) {
                                writer.writeEnum(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(t, length)) {
                                writer.writeSFixed32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(t, length)) {
                                writer.writeSFixed64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(t, length)) {
                                writer.writeSInt32(numberAt, intAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(t, length)) {
                                writer.writeSInt64(numberAt, longAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(t, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), length);
                            break;
                        case 51:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeFloat(numberAt, oneofFloatAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeUInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeBool(numberAt, oneofBooleanAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(t, numberAt, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeUInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeEnum(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeSInt32(numberAt, oneofIntAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeSInt64(numberAt, oneofLongAt(t, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(t, numberAt, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(t, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
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

    private <K, V> void writeMapHelper(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t, Writer writer) throws IOException {
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Objects.requireNonNull(extensionRegistryLite);
        checkMutable(t);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t, reader, extensionRegistryLite);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x00c3. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0646 A[Catch: all -> 0x069e, TRY_LEAVE, TryCatch #13 {all -> 0x069e, blocks: (B:16:0x0617, B:34:0x0640, B:36:0x0646, B:49:0x066e, B:50:0x0673), top: B:15:0x0617 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x06b0 A[LOOP:4: B:64:0x06ac->B:66:0x06b0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x06c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        UnknownFieldSchema unknownFieldSchema2;
        T t2;
        int i;
        Object obj;
        T t3;
        ExtensionRegistryLite extensionRegistryLite2;
        Object obj2;
        UnknownFieldSchema unknownFieldSchema3 = unknownFieldSchema;
        T t4 = t;
        ExtensionRegistryLite extensionRegistryLite3 = extensionRegistryLite;
        Object obj3 = null;
        FieldSet<ET> fieldSet = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int positionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (positionForFieldNumber >= 0) {
                    t2 = t4;
                    try {
                        int typeAndOffsetAt = typeAndOffsetAt(positionForFieldNumber);
                        switch (type(typeAndOffsetAt)) {
                            case 0:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putDouble(t2, offset(typeAndOffsetAt), reader.readDouble());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 1:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putFloat(t2, offset(typeAndOffsetAt), reader.readFloat());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 2:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t2, offset(typeAndOffsetAt), reader.readInt64());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 3:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t2, offset(typeAndOffsetAt), reader.readUInt64());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 4:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t2, offset(typeAndOffsetAt), reader.readInt32());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 5:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t2, offset(typeAndOffsetAt), reader.readFixed64());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 6:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t2, offset(typeAndOffsetAt), reader.readFixed32());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 7:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putBoolean(t2, offset(typeAndOffsetAt), reader.readBool());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 8:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readString(t2, typeAndOffsetAt, reader);
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 9:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                MessageLite messageLite = (MessageLite) mutableMessageFieldForMerge(t2, positionForFieldNumber);
                                reader.mergeMessageField(messageLite, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite2);
                                storeMessageField(t2, positionForFieldNumber, messageLite);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 10:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), reader.readBytes());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 11:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t2, offset(typeAndOffsetAt), reader.readUInt32());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 12:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                int readEnum = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(readEnum)) {
                                    obj3 = SchemaUtil.storeUnknownEnum(t2, fieldNumber, readEnum, obj2, unknownFieldSchema2);
                                    t4 = t2;
                                    extensionRegistryLite3 = extensionRegistryLite2;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                    break;
                                }
                                UnsafeUtil.putInt(t2, offset(typeAndOffsetAt), readEnum);
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 13:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t2, offset(typeAndOffsetAt), reader.readSFixed32());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 14:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t2, offset(typeAndOffsetAt), reader.readSFixed64());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 15:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putInt(t2, offset(typeAndOffsetAt), reader.readSInt32());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 16:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                UnsafeUtil.putLong(t2, offset(typeAndOffsetAt), reader.readSInt64());
                                setFieldPresent(t2, positionForFieldNumber);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 17:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                MessageLite messageLite2 = (MessageLite) mutableMessageFieldForMerge(t2, positionForFieldNumber);
                                reader.mergeGroupField(messageLite2, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite2);
                                storeMessageField(t2, positionForFieldNumber, messageLite2);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 18:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 19:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 20:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 21:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 22:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 23:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 24:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 25:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 26:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readStringList(t2, typeAndOffsetAt, reader);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 27:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readMessageList(t, typeAndOffsetAt, reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 28:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readBytesList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 29:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 30:
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                List<Integer> mutableListAt = this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt));
                                reader.readEnumList(mutableListAt);
                                obj3 = SchemaUtil.filterUnknownEnumList(t, fieldNumber, mutableListAt, getEnumFieldVerifier(positionForFieldNumber), obj3, unknownFieldSchema);
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 31:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 32:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 33:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 34:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 35:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 36:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 37:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 38:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 39:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 40:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 41:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 42:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 43:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 44:
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                List<Integer> mutableListAt2 = this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt));
                                reader.readEnumList(mutableListAt2);
                                obj3 = SchemaUtil.filterUnknownEnumList(t, fieldNumber, mutableListAt2, getEnumFieldVerifier(positionForFieldNumber), obj3, unknownFieldSchema);
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 45:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 46:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 47:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 48:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t2, offset(typeAndOffsetAt)));
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 49:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                readGroupList(t, offset(typeAndOffsetAt), reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 50:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                try {
                                    mergeMap(t, positionForFieldNumber, getMapFieldDefaultEntry(positionForFieldNumber), extensionRegistryLite, reader);
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                    if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                    }
                                    t4 = t2;
                                    extensionRegistryLite3 = extensionRegistryLite2;
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
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 51:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 52:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 53:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 54:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 55:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 56:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 57:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 58:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 59:
                                readString(t2, typeAndOffsetAt, reader);
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 60:
                                MessageLite messageLite3 = (MessageLite) mutableOneofMessageFieldForMerge(t2, fieldNumber, positionForFieldNumber);
                                reader.mergeMessageField(messageLite3, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite3);
                                storeOneofMessageField(t2, fieldNumber, positionForFieldNumber, messageLite3);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 61:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), reader.readBytes());
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 62:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 63:
                                int readEnum2 = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier2 != null && !enumFieldVerifier2.isInRange(readEnum2)) {
                                    obj3 = SchemaUtil.storeUnknownEnum(t2, fieldNumber, readEnum2, obj3, unknownFieldSchema3);
                                    extensionRegistryLite2 = extensionRegistryLite3;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    t4 = t2;
                                    extensionRegistryLite3 = extensionRegistryLite2;
                                    unknownFieldSchema3 = unknownFieldSchema2;
                                    break;
                                }
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Integer.valueOf(readEnum2));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 64:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 65:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 66:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 67:
                                UnsafeUtil.putObject(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj3 = obj2;
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            case 68:
                                try {
                                    MessageLite messageLite4 = (MessageLite) mutableOneofMessageFieldForMerge(t2, fieldNumber, positionForFieldNumber);
                                    reader.mergeGroupField(messageLite4, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite3);
                                    storeOneofMessageField(t2, fieldNumber, positionForFieldNumber, messageLite4);
                                    obj2 = obj3;
                                    extensionRegistryLite2 = extensionRegistryLite3;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    obj3 = obj2;
                                } catch (InvalidProtocolBufferException.InvalidWireTypeException unused2) {
                                    extensionRegistryLite2 = extensionRegistryLite3;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                    }
                                    t4 = t2;
                                    extensionRegistryLite3 = extensionRegistryLite2;
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
                                t4 = t2;
                                extensionRegistryLite3 = extensionRegistryLite2;
                                unknownFieldSchema3 = unknownFieldSchema2;
                                break;
                            default:
                                obj2 = obj3;
                                extensionRegistryLite2 = extensionRegistryLite3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                if (obj2 == null) {
                                    try {
                                        obj3 = unknownFieldSchema2.getBuilderFromMessage(t2);
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused3) {
                                        obj3 = obj2;
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                        }
                                        t4 = t2;
                                        extensionRegistryLite3 = extensionRegistryLite2;
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
                                                obj4 = filterMapUnknownEnumValues(t, this.intArray[i2], obj4, unknownFieldSchema, t);
                                            }
                                            if (obj4 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t2, obj4);
                                                return;
                                            }
                                            return;
                                        }
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused4) {
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                            if (obj3 == null) {
                                                obj3 = unknownFieldSchema2.getBuilderFromMessage(t2);
                                            }
                                            if (!unknownFieldSchema2.mergeOneFieldFrom(obj3, reader)) {
                                                Object obj5 = obj3;
                                                for (int i3 = this.checkInitializedCount; i3 < this.repeatedFieldOffsetStart; i3++) {
                                                    obj5 = filterMapUnknownEnumValues(t, this.intArray[i3], obj5, unknownFieldSchema, t);
                                                }
                                                if (obj5 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t2, obj5);
                                                    return;
                                                }
                                                return;
                                            }
                                        } else if (!reader.skipField()) {
                                            Object obj6 = obj3;
                                            for (int i4 = this.checkInitializedCount; i4 < this.repeatedFieldOffsetStart; i4++) {
                                                obj6 = filterMapUnknownEnumValues(t, this.intArray[i4], obj6, unknownFieldSchema, t);
                                            }
                                            if (obj6 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t2, obj6);
                                                return;
                                            }
                                            return;
                                        }
                                        t4 = t2;
                                        extensionRegistryLite3 = extensionRegistryLite2;
                                        unknownFieldSchema3 = unknownFieldSchema2;
                                    }
                                    t4 = t2;
                                    extensionRegistryLite3 = extensionRegistryLite2;
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
                            obj7 = filterMapUnknownEnumValues(t, this.intArray[i5], obj7, unknownFieldSchema, t);
                        }
                        if (obj7 != null) {
                            unknownFieldSchema3.setBuilderToMessage(t4, obj7);
                            return;
                        }
                        return;
                    }
                    try {
                        Object findExtensionByNumber = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistryLite3, this.defaultInstance, fieldNumber);
                        if (findExtensionByNumber != null) {
                            if (fieldSet == null) {
                                try {
                                    fieldSet = extensionSchema.getMutableExtensions(t);
                                } catch (Throwable th6) {
                                    th = th6;
                                    unknownFieldSchema2 = unknownFieldSchema3;
                                    t2 = t4;
                                    obj = obj3;
                                    while (i < this.repeatedFieldOffsetStart) {
                                    }
                                    if (obj != null) {
                                    }
                                    throw th;
                                }
                            }
                            FieldSet<ET> fieldSet2 = fieldSet;
                            t3 = t4;
                            try {
                                obj3 = extensionSchema.parseExtension(t, reader, findExtensionByNumber, extensionRegistryLite, fieldSet2, obj3, unknownFieldSchema);
                                fieldSet = fieldSet2;
                            } catch (Throwable th7) {
                                th = th7;
                                t2 = t3;
                                unknownFieldSchema2 = unknownFieldSchema3;
                                obj = obj3;
                                for (i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
                                    obj = filterMapUnknownEnumValues(t, this.intArray[i], obj, unknownFieldSchema, t);
                                }
                                if (obj != null) {
                                    unknownFieldSchema2.setBuilderToMessage(t2, obj);
                                }
                                throw th;
                            }
                        } else {
                            t3 = t4;
                            if (!unknownFieldSchema3.shouldDiscardUnknownFields(reader)) {
                                if (obj3 == null) {
                                    obj3 = unknownFieldSchema3.getBuilderFromMessage(t3);
                                }
                                if (!unknownFieldSchema3.mergeOneFieldFrom(obj3, reader)) {
                                }
                            } else if (!reader.skipField()) {
                            }
                        }
                        t4 = t3;
                    } catch (Throwable th8) {
                        th = th8;
                        t2 = t4;
                    }
                }
            } catch (Throwable th9) {
                th = th9;
            }
        }
        int i6 = this.checkInitializedCount;
        Object obj8 = obj3;
        while (i6 < this.repeatedFieldOffsetStart) {
            obj8 = filterMapUnknownEnumValues(t, this.intArray[i6], obj8, unknownFieldSchema, t);
            i6++;
            t3 = t3;
        }
        T t5 = t3;
        if (obj8 != null) {
            unknownFieldSchema3.setBuilderToMessage(t5, obj8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.MessageSchema$1, reason: invalid class name */
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
    private int decodeMapEntryValue(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(bArr, i, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i));
                return i + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i));
                return i + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i));
                return i + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case 12:
            case 13:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint642;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) cls), bArr, i, i2, registers);
            case 15:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case 16:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return decodeVarint643;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.util.Map, java.util.Map<K, V>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    private <K, V> int decodeMapEntry(byte[] bArr, int i, int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws IOException {
        int i3;
        int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
        int i4 = registers.int1;
        if (i4 < 0 || i4 > i2 - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i5 = decodeVarint32 + i4;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (decodeVarint32 < i5) {
            int i6 = decodeVarint32 + 1;
            byte b = bArr[decodeVarint32];
            if (b < 0) {
                i3 = ArrayDecoders.decodeVarint32(b, bArr, i6, registers);
                b = registers.int1;
            } else {
                i3 = i6;
            }
            int i7 = b >>> 3;
            int i8 = b & 7;
            if (i7 == 1) {
                if (i8 == metadata.keyType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata.keyType, null, registers);
                    obj = registers.object1;
                } else {
                    decodeVarint32 = ArrayDecoders.skipField(b, bArr, i3, i2, registers);
                }
            } else {
                if (i7 == 2 && i8 == metadata.valueType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                }
                decodeVarint32 = ArrayDecoders.skipField(b, bArr, i3, i2, registers);
            }
        }
        if (decodeVarint32 != i5) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return i5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x002f. Please report as an issue. */
    private int parseRepeatedField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ArrayDecoders.Registers registers) throws IOException {
        int decodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(t, j2);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, j2, protobufList);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i, protobufList, registers);
                }
                if (i5 == 1) {
                    return ArrayDecoders.decodeDoubleList(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 19:
            case 36:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i, protobufList, registers);
                }
                if (i5 == 5) {
                    return ArrayDecoders.decodeFloatList(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeVarint64List(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i, protobufList, registers);
                }
                if (i5 == 1) {
                    return ArrayDecoders.decodeFixed64List(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i, protobufList, registers);
                }
                if (i5 == 5) {
                    return ArrayDecoders.decodeFixed32List(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 25:
            case 42:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeBoolList(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(i3, bArr, i, i2, protobufList, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 27:
                if (i5 == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 28:
                if (i5 == 2) {
                    return ArrayDecoders.decodeBytesList(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        decodeVarint32List = ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                    }
                    return i;
                }
                decodeVarint32List = ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers);
                SchemaUtil.filterUnknownEnumList((Object) t, i4, (List<Integer>) protobufList, getEnumFieldVerifier(i6), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return decodeVarint32List;
            case 33:
            case 47:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeSInt32List(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 34:
            case 48:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeSInt64List(i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            case 49:
                if (i5 == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufList, registers);
                }
                return i;
            default:
                return i;
        }
    }

    private <K, V> int parseMapField(T t, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i3);
        Object object = unsafe.getObject(t, j);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(t, j, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(bArr, i, i2, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0023. Please report as an issue. */
    private int parseOneofField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        long j2 = this.buffer[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i)));
                    int i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i)));
                    int i10 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i10;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(t, j, Long.valueOf(registers.long1));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint64;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(t, j, Integer.valueOf(registers.int1));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint32;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i11;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i12;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(t, j, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint642;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i13 = registers.int1;
                    if (i13 == 0) {
                        unsafe.putObject(t, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !Utf8.isValidUtf8(bArr, decodeVarint322, decodeVarint322 + i13)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        unsafe.putObject(t, j, new String(bArr, decodeVarint322, i13, Internal.UTF_8));
                        decodeVarint322 += i13;
                    }
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint322;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    Object mutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(t, i4, i8);
                    int mergeMessageField = ArrayDecoders.mergeMessageField(mutableOneofMessageFieldForMerge, getMessageFieldSchema(i8), bArr, i, i2, registers);
                    storeOneofMessageField(t, i4, i8, mutableOneofMessageFieldForMerge);
                    return mergeMessageField;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(bArr, i, registers);
                    unsafe.putObject(t, j, registers.object1);
                    unsafe.putInt(t, j2, i4);
                    return decodeBytes;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i14 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i8);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i14)) {
                        unsafe.putObject(t, j, Integer.valueOf(i14));
                        unsafe.putInt(t, j2, i4);
                    } else {
                        getMutableUnknownFields(t).storeField(i3, Long.valueOf(i14));
                    }
                    return decodeVarint323;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(t, j, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint324;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(t, j, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint643;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    Object mutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(t, i4, i8);
                    int mergeGroupField = ArrayDecoders.mergeGroupField(mutableOneofMessageFieldForMerge2, getMessageFieldSchema(i8), bArr, i, i2, (i3 & (-8)) | 4, registers);
                    storeOneofMessageField(t, i4, i8, mutableOneofMessageFieldForMerge2);
                    return mergeGroupField;
                }
                return i;
            default:
                return i;
        }
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Schema schema = (Schema) this.objects[i2];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i2 + 1]);
        this.objects[i2] = schemaFor;
        return schemaFor;
    }

    private Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        return (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:98:0x008f. Please report as an issue. */
    public int parseProto2Message(T t, byte[] bArr, int i, int i2, int i3, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe;
        int i4;
        MessageSchema<T> messageSchema;
        int i5;
        int i6;
        int i7;
        int i8;
        T t2;
        int i9;
        int positionForFieldNumber;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        byte[] bArr2;
        int decodeVarint64;
        int i20;
        int i21;
        MessageSchema<T> messageSchema2 = this;
        T t3 = t;
        byte[] bArr3 = bArr;
        int i22 = i2;
        int i23 = i3;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(t);
        Unsafe unsafe2 = UNSAFE;
        int i24 = i;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        int i28 = -1;
        int i29 = 1048575;
        while (true) {
            if (i24 < i22) {
                int i30 = i24 + 1;
                byte b = bArr3[i24];
                if (b < 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(b, bArr3, i30, registers2);
                    i9 = registers2.int1;
                    i30 = decodeVarint32;
                } else {
                    i9 = b;
                }
                int i31 = i9 >>> 3;
                int i32 = i9 & 7;
                if (i31 > i28) {
                    positionForFieldNumber = messageSchema2.positionForFieldNumber(i31, i25 / 3);
                } else {
                    positionForFieldNumber = messageSchema2.positionForFieldNumber(i31);
                }
                int i33 = positionForFieldNumber;
                if (i33 == -1) {
                    i10 = i31;
                    i11 = i30;
                    i6 = i9;
                    i12 = i27;
                    i13 = i29;
                    unsafe = unsafe2;
                    i4 = i23;
                    i14 = 0;
                } else {
                    int i34 = messageSchema2.buffer[i33 + 1];
                    int type = type(i34);
                    long offset = offset(i34);
                    int i35 = i9;
                    if (type <= 17) {
                        int i36 = messageSchema2.buffer[i33 + 2];
                        int i37 = 1 << (i36 >>> 20);
                        int i38 = i36 & 1048575;
                        if (i38 != i29) {
                            if (i29 != 1048575) {
                                unsafe2.putInt(t3, i29, i27);
                            }
                            i16 = i38;
                            i15 = unsafe2.getInt(t3, i38);
                        } else {
                            i15 = i27;
                            i16 = i29;
                        }
                        switch (type) {
                            case 0:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 1) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    UnsafeUtil.putDouble(t3, offset, ArrayDecoders.decodeDouble(bArr2, i30));
                                    i24 = i30 + 8;
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 1:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 5) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    UnsafeUtil.putFloat(t3, offset, ArrayDecoders.decodeFloat(bArr2, i30));
                                    i24 = i30 + 4;
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 2:
                            case 3:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 0) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i30, registers2);
                                    unsafe2.putLong(t, offset, registers2.long1);
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i24 = decodeVarint64;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 4:
                            case 11:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 0) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.decodeVarint32(bArr2, i30, registers2);
                                    unsafe2.putInt(t3, offset, registers2.int1);
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 5:
                            case 14:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 1) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    unsafe2.putLong(t, offset, ArrayDecoders.decodeFixed64(bArr2, i30));
                                    i24 = i30 + 8;
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 6:
                            case 13:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 5) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    unsafe2.putInt(t3, offset, ArrayDecoders.decodeFixed32(bArr2, i30));
                                    i24 = i30 + 4;
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 7:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 0) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.decodeVarint64(bArr2, i30, registers2);
                                    UnsafeUtil.putBoolean(t3, offset, registers2.long1 != 0);
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 8:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 2) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    if ((536870912 & i34) == 0) {
                                        i24 = ArrayDecoders.decodeString(bArr2, i30, registers2);
                                    } else {
                                        i24 = ArrayDecoders.decodeStringRequireUtf8(bArr2, i30, registers2);
                                    }
                                    unsafe2.putObject(t3, offset, registers2.object1);
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 9:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 2) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    Object mutableMessageFieldForMerge = messageSchema2.mutableMessageFieldForMerge(t3, i17);
                                    i24 = ArrayDecoders.mergeMessageField(mutableMessageFieldForMerge, messageSchema2.getMessageFieldSchema(i17), bArr, i30, i2, registers);
                                    messageSchema2.storeMessageField(t3, i17, mutableMessageFieldForMerge);
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 10:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 2) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.decodeBytes(bArr2, i30, registers2);
                                    unsafe2.putObject(t3, offset, registers2.object1);
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 12:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 0) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.decodeVarint32(bArr2, i30, registers2);
                                    int i39 = registers2.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i17);
                                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i39)) {
                                        unsafe2.putInt(t3, offset, i39);
                                        i27 = i15 | i37;
                                        i23 = i3;
                                        i25 = i17;
                                        i26 = i19;
                                        i28 = i10;
                                        bArr3 = bArr2;
                                        i29 = i18;
                                    } else {
                                        getMutableUnknownFields(t).storeField(i19, Long.valueOf(i39));
                                        i25 = i17;
                                        i27 = i15;
                                        i26 = i19;
                                        i28 = i10;
                                        i23 = i3;
                                        bArr3 = bArr2;
                                        i29 = i18;
                                    }
                                }
                                break;
                            case 15:
                                bArr2 = bArr;
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                if (i32 != 0) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.decodeVarint32(bArr2, i30, registers2);
                                    unsafe2.putInt(t3, offset, CodedInputStream.decodeZigZag32(registers2.int1));
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 16:
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                bArr2 = bArr;
                                if (i32 != 0) {
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i30, registers2);
                                    unsafe2.putLong(t, offset, CodedInputStream.decodeZigZag64(registers2.long1));
                                    i27 = i15 | i37;
                                    i23 = i3;
                                    i24 = decodeVarint64;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i10;
                                    bArr3 = bArr2;
                                    i29 = i18;
                                }
                            case 17:
                                if (i32 != 3) {
                                    i10 = i31;
                                    i17 = i33;
                                    i18 = i16;
                                    i19 = i35;
                                    i13 = i18;
                                    i11 = i30;
                                    i14 = i17;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i19;
                                    i4 = i3;
                                    break;
                                } else {
                                    Object mutableMessageFieldForMerge2 = messageSchema2.mutableMessageFieldForMerge(t3, i33);
                                    i24 = ArrayDecoders.mergeGroupField(mutableMessageFieldForMerge2, messageSchema2.getMessageFieldSchema(i33), bArr, i30, i2, (i31 << 3) | 4, registers);
                                    messageSchema2.storeMessageField(t3, i33, mutableMessageFieldForMerge2);
                                    i27 = i15 | i37;
                                    i29 = i16;
                                    i23 = i3;
                                    i25 = i33;
                                    i26 = i35;
                                    i28 = i31;
                                    bArr3 = bArr;
                                }
                            default:
                                i10 = i31;
                                i17 = i33;
                                i18 = i16;
                                i19 = i35;
                                i13 = i18;
                                i11 = i30;
                                i14 = i17;
                                unsafe = unsafe2;
                                i12 = i15;
                                i6 = i19;
                                i4 = i3;
                                break;
                        }
                    } else {
                        i10 = i31;
                        i13 = i29;
                        i12 = i27;
                        if (type == 27) {
                            if (i32 == 2) {
                                Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t3, offset);
                                if (!protobufList.isModifiable()) {
                                    int size = protobufList.size();
                                    protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                    unsafe2.putObject(t3, offset, protobufList);
                                }
                                i24 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(i33), i35, bArr, i30, i2, protobufList, registers);
                                i25 = i33;
                                i26 = i35;
                                i29 = i13;
                                i27 = i12;
                                i28 = i10;
                                bArr3 = bArr;
                                i23 = i3;
                            } else {
                                i20 = i30;
                                unsafe = unsafe2;
                                i14 = i33;
                                i21 = i35;
                                i4 = i3;
                                i11 = i20;
                            }
                        } else if (type <= 49) {
                            int i40 = i30;
                            unsafe = unsafe2;
                            i14 = i33;
                            i21 = i35;
                            i24 = parseRepeatedField(t, bArr, i30, i2, i35, i10, i32, i33, i34, type, offset, registers);
                            if (i24 != i40) {
                                messageSchema2 = this;
                                t3 = t;
                                bArr3 = bArr;
                                i22 = i2;
                                i23 = i3;
                                registers2 = registers;
                                i29 = i13;
                                i27 = i12;
                                i25 = i14;
                                i26 = i21;
                                i28 = i10;
                                unsafe2 = unsafe;
                            } else {
                                i4 = i3;
                                i11 = i24;
                            }
                        } else {
                            i20 = i30;
                            unsafe = unsafe2;
                            i14 = i33;
                            i21 = i35;
                            if (type != 50) {
                                i24 = parseOneofField(t, bArr, i20, i2, i21, i10, i32, i34, type, offset, i14, registers);
                                if (i24 != i20) {
                                    messageSchema2 = this;
                                    t3 = t;
                                    bArr3 = bArr;
                                    i22 = i2;
                                    i23 = i3;
                                    registers2 = registers;
                                    i29 = i13;
                                    i27 = i12;
                                    i25 = i14;
                                    i26 = i21;
                                    i28 = i10;
                                    unsafe2 = unsafe;
                                } else {
                                    i4 = i3;
                                    i11 = i24;
                                }
                            } else if (i32 == 2) {
                                i24 = parseMapField(t, bArr, i20, i2, i14, offset, registers);
                                if (i24 != i20) {
                                    messageSchema2 = this;
                                    t3 = t;
                                    bArr3 = bArr;
                                    i22 = i2;
                                    i23 = i3;
                                    registers2 = registers;
                                    i29 = i13;
                                    i27 = i12;
                                    i25 = i14;
                                    i26 = i21;
                                    i28 = i10;
                                    unsafe2 = unsafe;
                                } else {
                                    i4 = i3;
                                    i11 = i24;
                                }
                            } else {
                                i4 = i3;
                                i11 = i20;
                            }
                        }
                        i6 = i21;
                    }
                }
                if (i6 != i4 || i4 == 0) {
                    if (this.hasExtensions && registers.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                        i24 = ArrayDecoders.decodeExtensionOrUnknownField(i6, bArr, i11, i2, t, this.defaultInstance, this.unknownFieldSchema, registers);
                    } else {
                        i24 = ArrayDecoders.decodeUnknownField(i6, bArr, i11, i2, getMutableUnknownFields(t), registers);
                    }
                    t3 = t;
                    bArr3 = bArr;
                    i22 = i2;
                    i26 = i6;
                    messageSchema2 = this;
                    registers2 = registers;
                    i29 = i13;
                    i27 = i12;
                    i25 = i14;
                    i28 = i10;
                    unsafe2 = unsafe;
                    i23 = i4;
                } else {
                    i8 = 1048575;
                    messageSchema = this;
                    i5 = i11;
                    i7 = i13;
                    i27 = i12;
                }
            } else {
                int i41 = i29;
                unsafe = unsafe2;
                i4 = i23;
                messageSchema = messageSchema2;
                i5 = i24;
                i6 = i26;
                i7 = i41;
                i8 = 1048575;
            }
        }
        if (i7 != i8) {
            t2 = t;
            unsafe.putInt(t2, i7, i27);
        } else {
            t2 = t;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i42 = messageSchema.checkInitializedCount; i42 < messageSchema.repeatedFieldOffsetStart; i42++) {
            unknownFieldSetLite = (UnknownFieldSetLite) filterMapUnknownEnumValues(t, messageSchema.intArray[i42], unknownFieldSetLite, messageSchema.unknownFieldSchema, t);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t2, unknownFieldSetLite);
        }
        if (i4 == 0) {
            if (i5 != i2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i5 > i2 || i6 != i4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T t, int i) {
        Schema messageFieldSchema = getMessageFieldSchema(i);
        long offset = offset(typeAndOffsetAt(i));
        if (!isFieldPresent(t, i)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeMessageField(T t, int i, Object obj) {
        UNSAFE.putObject(t, offset(typeAndOffsetAt(i)), obj);
        setFieldPresent(t, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T t, int i, int i2) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        if (!isOneofPresent(t, i, i2)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t, offset(typeAndOffsetAt(i2)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeOneofMessageField(T t, int i, int i2, Object obj) {
        UNSAFE.putObject(t, offset(typeAndOffsetAt(i2)), obj);
        setOneofPresent(t, i, i2);
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
    private int parseProto3Message(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws IOException {
        byte b;
        int i3;
        int positionForFieldNumber;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        Unsafe unsafe2;
        int i9;
        int i10;
        int i11;
        int decodeVarint64;
        Unsafe unsafe3;
        MessageSchema<T> messageSchema = this;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i12 = i2;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(t);
        Unsafe unsafe4 = UNSAFE;
        int i13 = -1;
        int i14 = i;
        int i15 = -1;
        int i16 = 0;
        int i17 = 0;
        int i18 = 1048575;
        while (i14 < i12) {
            int i19 = i14 + 1;
            byte b2 = bArr2[i14];
            if (b2 < 0) {
                i3 = ArrayDecoders.decodeVarint32(b2, bArr2, i19, registers2);
                b = registers2.int1;
            } else {
                b = b2;
                i3 = i19;
            }
            int i20 = b >>> 3;
            int i21 = b & 7;
            if (i20 > i15) {
                positionForFieldNumber = messageSchema.positionForFieldNumber(i20, i16 / 3);
            } else {
                positionForFieldNumber = messageSchema.positionForFieldNumber(i20);
            }
            int i22 = positionForFieldNumber;
            if (i22 == i13) {
                i4 = i3;
                i5 = i20;
                unsafe = unsafe4;
                i6 = i13;
                i7 = 0;
            } else {
                int i23 = messageSchema.buffer[i22 + 1];
                int type = type(i23);
                Unsafe unsafe5 = unsafe4;
                long offset = offset(i23);
                if (type <= 17) {
                    int i24 = messageSchema.buffer[i22 + 2];
                    int i25 = 1 << (i24 >>> 20);
                    int i26 = i24 & 1048575;
                    if (i26 != i18) {
                        if (i18 != 1048575) {
                            long j = i18;
                            unsafe3 = unsafe5;
                            unsafe3.putInt(t2, j, i17);
                        } else {
                            unsafe3 = unsafe5;
                        }
                        if (i26 != 1048575) {
                            i17 = unsafe3.getInt(t2, i26);
                        }
                        unsafe2 = unsafe3;
                        i18 = i26;
                    } else {
                        unsafe2 = unsafe5;
                    }
                    switch (type) {
                        case 0:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 1) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                UnsafeUtil.putDouble(t2, offset, ArrayDecoders.decodeDouble(bArr2, i3));
                                i14 = i3 + 8;
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 1:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 5) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                UnsafeUtil.putFloat(t2, offset, ArrayDecoders.decodeFloat(bArr2, i3));
                                i14 = i3 + 4;
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 2:
                        case 3:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 0) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i3, registers2);
                                unsafe2.putLong(t, offset, registers2.long1);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i14 = decodeVarint64;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 4:
                        case 11:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 0) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeVarint32(bArr2, i3, registers2);
                                unsafe2.putInt(t2, offset, registers2.int1);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 5:
                        case 14:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 1) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                unsafe2.putLong(t, offset, ArrayDecoders.decodeFixed64(bArr2, i3));
                                i14 = i3 + 8;
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 6:
                        case 13:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 5) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                unsafe2.putInt(t2, offset, ArrayDecoders.decodeFixed32(bArr2, i3));
                                i14 = i3 + 4;
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 7:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 0) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeVarint64(bArr2, i3, registers2);
                                UnsafeUtil.putBoolean(t2, offset, registers2.long1 != 0);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 8:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 2) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                if ((i23 & 536870912) == 0) {
                                    i14 = ArrayDecoders.decodeString(bArr2, i3, registers2);
                                } else {
                                    i14 = ArrayDecoders.decodeStringRequireUtf8(bArr2, i3, registers2);
                                }
                                unsafe2.putObject(t2, offset, registers2.object1);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 9:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 2) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                Object mutableMessageFieldForMerge = messageSchema.mutableMessageFieldForMerge(t2, i8);
                                i14 = ArrayDecoders.mergeMessageField(mutableMessageFieldForMerge, messageSchema.getMessageFieldSchema(i8), bArr, i3, i2, registers);
                                messageSchema.storeMessageField(t2, i8, mutableMessageFieldForMerge);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 10:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 2) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeBytes(bArr2, i3, registers2);
                                unsafe2.putObject(t2, offset, registers2.object1);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 12:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 0) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeVarint32(bArr2, i3, registers2);
                                unsafe2.putInt(t2, offset, registers2.int1);
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 15:
                            i8 = i22;
                            i5 = i20;
                            if (i21 != 0) {
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeVarint32(bArr2, i3, registers2);
                                unsafe2.putInt(t2, offset, CodedInputStream.decodeZigZag32(registers2.int1));
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        case 16:
                            if (i21 != 0) {
                                i8 = i22;
                                i5 = i20;
                                i4 = i3;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                                break;
                            } else {
                                decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i3, registers2);
                                i8 = i22;
                                i5 = i20;
                                unsafe2.putLong(t, offset, CodedInputStream.decodeZigZag64(registers2.long1));
                                i17 |= i25;
                                unsafe4 = unsafe2;
                                i16 = i8;
                                i14 = decodeVarint64;
                                i15 = i5;
                                i13 = -1;
                                i12 = i2;
                                break;
                            }
                        default:
                            i8 = i22;
                            i5 = i20;
                            i4 = i3;
                            unsafe = unsafe2;
                            i7 = i8;
                            i6 = -1;
                            break;
                    }
                } else {
                    i5 = i20;
                    i8 = i22;
                    unsafe2 = unsafe5;
                    if (type != 27) {
                        if (type <= 49) {
                            int i27 = i3;
                            i10 = i17;
                            i11 = i18;
                            unsafe = unsafe2;
                            i6 = -1;
                            i7 = i8;
                            i14 = parseRepeatedField(t, bArr, i3, i2, b, i5, i21, i8, i23, type, offset, registers);
                        } else {
                            i9 = i3;
                            i10 = i17;
                            i11 = i18;
                            unsafe = unsafe2;
                            i7 = i8;
                            i6 = -1;
                            if (type != 50) {
                                i14 = parseOneofField(t, bArr, i9, i2, b, i5, i21, i23, type, offset, i7, registers);
                            } else if (i21 == 2) {
                                i14 = parseMapField(t, bArr, i9, i2, i7, offset, registers);
                            }
                        }
                        unsafe4 = unsafe;
                    } else if (i21 == 2) {
                        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(t2, offset);
                        if (!protobufList.isModifiable()) {
                            int size = protobufList.size();
                            protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                            unsafe2.putObject(t2, offset, protobufList);
                        }
                        i14 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(i8), b, bArr, i3, i2, protobufList, registers);
                        i17 = i17;
                        unsafe4 = unsafe2;
                        i16 = i8;
                        i15 = i5;
                        i13 = -1;
                        i12 = i2;
                    } else {
                        i9 = i3;
                        i10 = i17;
                        i11 = i18;
                        unsafe = unsafe2;
                        i7 = i8;
                        i6 = -1;
                    }
                    i4 = i9;
                    i17 = i10;
                    i18 = i11;
                }
            }
            i14 = ArrayDecoders.decodeUnknownField(b, bArr, i4, i2, getMutableUnknownFields(t), registers);
            messageSchema = this;
            t2 = t;
            bArr2 = bArr;
            i12 = i2;
            registers2 = registers;
            i13 = i6;
            i15 = i5;
            i16 = i7;
            unsafe4 = unsafe;
        }
        int i28 = i17;
        Unsafe unsafe6 = unsafe4;
        if (i18 != 1048575) {
            unsafe6.putInt(t, i18, i28);
        }
        if (i14 == i2) {
            return i14;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(t, bArr, i, i2, registers);
        } else {
            parseProto2Message(t, bArr, i, i2, 0, registers);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Schema
    public void makeImmutable(T t) {
        if (isMutable(t)) {
            if (t instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
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
                            this.listFieldSchema.makeImmutableListAt(t, offset);
                            break;
                        case 50:
                            Unsafe unsafe = UNSAFE;
                            Object object = unsafe.getObject(t, offset);
                            if (object != null) {
                                unsafe.putObject(t, offset, this.mapFieldSchema.toImmutable(object));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                if (isFieldPresent(t, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(t, offset));
                }
            }
            this.unknownFieldSchema.makeImmutable(t);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(t);
            }
        }
    }

    private final <K, V> void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(i));
        Object object = UnsafeUtil.getObject(obj, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(obj, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
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

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T t) {
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
                    i4 = UNSAFE.getInt(t, i8);
                }
                i2 = i4;
                i = i8;
            } else {
                i = i3;
                i2 = i4;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(t, i6, i, i2, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type == 9 || type == 17) {
                if (isFieldPresent(t, i6, i, i2, i9) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                    return false;
                }
            } else {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t, numberAt, i6) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(t, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(t, typeAndOffsetAt, i6)) {
                    return false;
                }
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
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
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.protobuf.Schema] */
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

    private void writeString(int i, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private void readString(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    private <E> void readMessageList(Object obj, int i, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private <E> void readGroupList(Object obj, long j, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object obj) {
        if (isMutable(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + obj);
    }

    private static <T> double doubleAt(T t, long j) {
        return UnsafeUtil.getDouble(t, j);
    }

    private static <T> float floatAt(T t, long j) {
        return UnsafeUtil.getFloat(t, j);
    }

    private static <T> int intAt(T t, long j) {
        return UnsafeUtil.getInt(t, j);
    }

    private static <T> long longAt(T t, long j) {
        return UnsafeUtil.getLong(t, j);
    }

    private static <T> boolean booleanAt(T t, long j) {
        return UnsafeUtil.getBoolean(t, j);
    }

    private static <T> double oneofDoubleAt(T t, long j) {
        return ((Double) UnsafeUtil.getObject(t, j)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t, long j) {
        return ((Float) UnsafeUtil.getObject(t, j)).floatValue();
    }

    private static <T> int oneofIntAt(T t, long j) {
        return ((Integer) UnsafeUtil.getObject(t, j)).intValue();
    }

    private static <T> long oneofLongAt(T t, long j) {
        return ((Long) UnsafeUtil.getObject(t, j)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T t, long j) {
        return ((Boolean) UnsafeUtil.getObject(t, j)).booleanValue();
    }

    private boolean arePresentForEquals(T t, T t2, int i) {
        return isFieldPresent(t, i) == isFieldPresent(t2, i);
    }

    private boolean isFieldPresent(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(t, i);
        }
        return (i3 & i4) != 0;
    }

    private boolean isFieldPresent(T t, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(t, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(t, offset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(t, offset)) != 0;
            case 2:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 3:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 4:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 5:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 6:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(t, offset);
            case 8:
                Object object = UnsafeUtil.getObject(t, offset);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(t, offset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(t, offset));
            case 11:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 12:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 13:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 14:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 15:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 16:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 17:
                return UnsafeUtil.getObject(t, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setFieldPresent(T t, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(t, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(t, j));
    }

    private boolean isOneofPresent(T t, int i, int i2) {
        return UnsafeUtil.getInt(t, (long) (presenceMaskAndOffsetAt(i2) & 1048575)) == i;
    }

    private boolean isOneofCaseEqual(T t, T t2, int i) {
        long presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i) & 1048575;
        return UnsafeUtil.getInt(t, presenceMaskAndOffsetAt) == UnsafeUtil.getInt(t2, presenceMaskAndOffsetAt);
    }

    private void setOneofPresent(T t, int i, int i2) {
        UnsafeUtil.putInt(t, presenceMaskAndOffsetAt(i2) & 1048575, i);
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int positionForFieldNumber(int i, int i2) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, i2);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i == numberAt) {
                return i4;
            }
            if (i < numberAt) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
