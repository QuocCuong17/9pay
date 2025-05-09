package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzko<T> implements zzlc<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzma.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzkk zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzks zzo;
    private final zzju zzp;
    private final zzlu<?, ?> zzq;
    private final zziq<?> zzr;
    private final zzkh zzs;

    private zzko(int[] iArr, Object[] objArr, int i, int i2, zzkk zzkkVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzks zzksVar, zzju zzjuVar, zzlu<?, ?> zzluVar, zziq<?> zziqVar, zzkh zzkhVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzkkVar instanceof zzjb;
        this.zzj = z;
        this.zzh = zziqVar != null && zziqVar.zza(zzkkVar);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzksVar;
        this.zzp = zzjuVar;
        this.zzq = zzluVar;
        this.zzr = zziqVar;
        this.zzg = zzkkVar;
        this.zzs = zzkhVar;
    }

    private static boolean zzf(int i) {
        return (i & 536870912) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0397  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T> zzko<T> zza(Class<T> cls, zzki zzkiVar, zzks zzksVar, zzju zzjuVar, zzlu<?, ?> zzluVar, zziq<?> zziqVar, zzkh zzkhVar) {
        int i;
        int charAt;
        int charAt2;
        int charAt3;
        int charAt4;
        int charAt5;
        int i2;
        int[] iArr;
        int i3;
        char charAt6;
        int i4;
        char charAt7;
        int i5;
        char charAt8;
        int i6;
        char charAt9;
        int i7;
        char charAt10;
        int i8;
        char charAt11;
        int i9;
        char charAt12;
        int i10;
        char charAt13;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int objectFieldOffset;
        String str;
        boolean z;
        boolean z2;
        int i16;
        int i17;
        int i18;
        Field zza2;
        int i19;
        char charAt14;
        int i20;
        int i21;
        Field zza3;
        Field zza4;
        int i22;
        char charAt15;
        int i23;
        char charAt16;
        int i24;
        char charAt17;
        int i25;
        char charAt18;
        if (zzkiVar instanceof zzla) {
            zzla zzlaVar = (zzla) zzkiVar;
            int i26 = 0;
            boolean z3 = zzlaVar.zza() == zzkz.zzb;
            String zzd = zzlaVar.zzd();
            int length = zzd.length();
            if (zzd.charAt(0) >= 55296) {
                int i27 = 1;
                while (true) {
                    i = i27 + 1;
                    if (zzd.charAt(i27) < 55296) {
                        break;
                    }
                    i27 = i;
                }
            } else {
                i = 1;
            }
            int i28 = i + 1;
            int charAt19 = zzd.charAt(i);
            if (charAt19 >= 55296) {
                int i29 = charAt19 & 8191;
                int i30 = 13;
                while (true) {
                    i25 = i28 + 1;
                    charAt18 = zzd.charAt(i28);
                    if (charAt18 < 55296) {
                        break;
                    }
                    i29 |= (charAt18 & 8191) << i30;
                    i30 += 13;
                    i28 = i25;
                }
                charAt19 = i29 | (charAt18 << i30);
                i28 = i25;
            }
            if (charAt19 == 0) {
                charAt = 0;
                charAt2 = 0;
                charAt3 = 0;
                charAt4 = 0;
                charAt5 = 0;
                iArr = zza;
                i2 = 0;
            } else {
                int i31 = i28 + 1;
                int charAt20 = zzd.charAt(i28);
                if (charAt20 >= 55296) {
                    int i32 = charAt20 & 8191;
                    int i33 = 13;
                    while (true) {
                        i10 = i31 + 1;
                        charAt13 = zzd.charAt(i31);
                        if (charAt13 < 55296) {
                            break;
                        }
                        i32 |= (charAt13 & 8191) << i33;
                        i33 += 13;
                        i31 = i10;
                    }
                    charAt20 = i32 | (charAt13 << i33);
                    i31 = i10;
                }
                int i34 = i31 + 1;
                int charAt21 = zzd.charAt(i31);
                if (charAt21 >= 55296) {
                    int i35 = charAt21 & 8191;
                    int i36 = 13;
                    while (true) {
                        i9 = i34 + 1;
                        charAt12 = zzd.charAt(i34);
                        if (charAt12 < 55296) {
                            break;
                        }
                        i35 |= (charAt12 & 8191) << i36;
                        i36 += 13;
                        i34 = i9;
                    }
                    charAt21 = i35 | (charAt12 << i36);
                    i34 = i9;
                }
                int i37 = i34 + 1;
                charAt = zzd.charAt(i34);
                if (charAt >= 55296) {
                    int i38 = charAt & 8191;
                    int i39 = 13;
                    while (true) {
                        i8 = i37 + 1;
                        charAt11 = zzd.charAt(i37);
                        if (charAt11 < 55296) {
                            break;
                        }
                        i38 |= (charAt11 & 8191) << i39;
                        i39 += 13;
                        i37 = i8;
                    }
                    charAt = i38 | (charAt11 << i39);
                    i37 = i8;
                }
                int i40 = i37 + 1;
                charAt2 = zzd.charAt(i37);
                if (charAt2 >= 55296) {
                    int i41 = charAt2 & 8191;
                    int i42 = 13;
                    while (true) {
                        i7 = i40 + 1;
                        charAt10 = zzd.charAt(i40);
                        if (charAt10 < 55296) {
                            break;
                        }
                        i41 |= (charAt10 & 8191) << i42;
                        i42 += 13;
                        i40 = i7;
                    }
                    charAt2 = i41 | (charAt10 << i42);
                    i40 = i7;
                }
                int i43 = i40 + 1;
                charAt3 = zzd.charAt(i40);
                if (charAt3 >= 55296) {
                    int i44 = charAt3 & 8191;
                    int i45 = 13;
                    while (true) {
                        i6 = i43 + 1;
                        charAt9 = zzd.charAt(i43);
                        if (charAt9 < 55296) {
                            break;
                        }
                        i44 |= (charAt9 & 8191) << i45;
                        i45 += 13;
                        i43 = i6;
                    }
                    charAt3 = i44 | (charAt9 << i45);
                    i43 = i6;
                }
                int i46 = i43 + 1;
                charAt4 = zzd.charAt(i43);
                if (charAt4 >= 55296) {
                    int i47 = charAt4 & 8191;
                    int i48 = 13;
                    while (true) {
                        i5 = i46 + 1;
                        charAt8 = zzd.charAt(i46);
                        if (charAt8 < 55296) {
                            break;
                        }
                        i47 |= (charAt8 & 8191) << i48;
                        i48 += 13;
                        i46 = i5;
                    }
                    charAt4 = i47 | (charAt8 << i48);
                    i46 = i5;
                }
                int i49 = i46 + 1;
                int charAt22 = zzd.charAt(i46);
                if (charAt22 >= 55296) {
                    int i50 = charAt22 & 8191;
                    int i51 = 13;
                    while (true) {
                        i4 = i49 + 1;
                        charAt7 = zzd.charAt(i49);
                        if (charAt7 < 55296) {
                            break;
                        }
                        i50 |= (charAt7 & 8191) << i51;
                        i51 += 13;
                        i49 = i4;
                    }
                    charAt22 = i50 | (charAt7 << i51);
                    i49 = i4;
                }
                int i52 = i49 + 1;
                charAt5 = zzd.charAt(i49);
                if (charAt5 >= 55296) {
                    int i53 = charAt5 & 8191;
                    int i54 = i52;
                    int i55 = 13;
                    while (true) {
                        i3 = i54 + 1;
                        charAt6 = zzd.charAt(i54);
                        if (charAt6 < 55296) {
                            break;
                        }
                        i53 |= (charAt6 & 8191) << i55;
                        i55 += 13;
                        i54 = i3;
                    }
                    charAt5 = i53 | (charAt6 << i55);
                    i52 = i3;
                }
                i2 = (charAt20 << 1) + charAt21;
                iArr = new int[charAt5 + charAt4 + charAt22];
                i26 = charAt20;
                i28 = i52;
            }
            Unsafe unsafe = zzb;
            Object[] zze = zzlaVar.zze();
            Class<?> cls2 = zzlaVar.zzc().getClass();
            int i56 = i28;
            int[] iArr2 = new int[charAt3 * 3];
            Object[] objArr = new Object[charAt3 << 1];
            int i57 = charAt5 + charAt4;
            int i58 = i2;
            int i59 = charAt5;
            int i60 = i56;
            int i61 = i57;
            int i62 = 0;
            int i63 = 0;
            while (i60 < length) {
                int i64 = i60 + 1;
                int charAt23 = zzd.charAt(i60);
                if (charAt23 >= 55296) {
                    int i65 = charAt23 & 8191;
                    int i66 = i64;
                    int i67 = 13;
                    while (true) {
                        i24 = i66 + 1;
                        charAt17 = zzd.charAt(i66);
                        i11 = length;
                        if (charAt17 < 55296) {
                            break;
                        }
                        i65 |= (charAt17 & 8191) << i67;
                        i67 += 13;
                        i66 = i24;
                        length = i11;
                    }
                    charAt23 = i65 | (charAt17 << i67);
                    i12 = i24;
                } else {
                    i11 = length;
                    i12 = i64;
                }
                int i68 = i12 + 1;
                int charAt24 = zzd.charAt(i12);
                if (charAt24 >= 55296) {
                    int i69 = charAt24 & 8191;
                    int i70 = i68;
                    int i71 = 13;
                    while (true) {
                        i23 = i70 + 1;
                        charAt16 = zzd.charAt(i70);
                        i13 = charAt5;
                        if (charAt16 < 55296) {
                            break;
                        }
                        i69 |= (charAt16 & 8191) << i71;
                        i71 += 13;
                        i70 = i23;
                        charAt5 = i13;
                    }
                    charAt24 = i69 | (charAt16 << i71);
                    i14 = i23;
                } else {
                    i13 = charAt5;
                    i14 = i68;
                }
                int i72 = charAt24 & 255;
                int i73 = charAt2;
                if ((charAt24 & 1024) != 0) {
                    iArr[i62] = i63;
                    i62++;
                }
                int i74 = charAt;
                if (i72 >= 51) {
                    int i75 = i14 + 1;
                    int charAt25 = zzd.charAt(i14);
                    char c = 55296;
                    if (charAt25 >= 55296) {
                        int i76 = charAt25 & 8191;
                        int i77 = 13;
                        while (true) {
                            i22 = i75 + 1;
                            charAt15 = zzd.charAt(i75);
                            if (charAt15 < c) {
                                break;
                            }
                            i76 |= (charAt15 & 8191) << i77;
                            i77 += 13;
                            i75 = i22;
                            c = 55296;
                        }
                        charAt25 = i76 | (charAt15 << i77);
                        i75 = i22;
                    }
                    int i78 = i72 - 51;
                    int i79 = i75;
                    if (i78 == 9 || i78 == 17) {
                        i21 = 1;
                        objArr[((i63 / 3) << 1) + 1] = zze[i58];
                        i58++;
                    } else {
                        if (i78 == 12 && !z3) {
                            objArr[((i63 / 3) << 1) + 1] = zze[i58];
                            i58++;
                        }
                        i21 = 1;
                    }
                    int i80 = charAt25 << i21;
                    Object obj = zze[i80];
                    if (obj instanceof Field) {
                        zza3 = (Field) obj;
                    } else {
                        zza3 = zza(cls2, (String) obj);
                        zze[i80] = zza3;
                    }
                    int objectFieldOffset2 = (int) unsafe.objectFieldOffset(zza3);
                    int i81 = i80 + 1;
                    Object obj2 = zze[i81];
                    if (obj2 instanceof Field) {
                        zza4 = (Field) obj2;
                    } else {
                        zza4 = zza(cls2, (String) obj2);
                        zze[i81] = zza4;
                    }
                    str = zzd;
                    i16 = (int) unsafe.objectFieldOffset(zza4);
                    z = z3;
                    objectFieldOffset = objectFieldOffset2;
                    i17 = i79;
                    i18 = 0;
                    z2 = true;
                } else {
                    int i82 = i58 + 1;
                    Field zza5 = zza(cls2, (String) zze[i58]);
                    if (i72 == 9 || i72 == 17) {
                        objArr[((i63 / 3) << 1) + 1] = zza5.getType();
                    } else {
                        if (i72 == 27 || i72 == 49) {
                            i20 = i82 + 1;
                            objArr[((i63 / 3) << 1) + 1] = zze[i82];
                        } else {
                            if (i72 == 12 || i72 == 30 || i72 == 44) {
                                if (!z3) {
                                    i20 = i82 + 1;
                                    objArr[((i63 / 3) << 1) + 1] = zze[i82];
                                }
                            } else if (i72 == 50) {
                                int i83 = i59 + 1;
                                iArr[i59] = i63;
                                int i84 = (i63 / 3) << 1;
                                i20 = i82 + 1;
                                objArr[i84] = zze[i82];
                                if ((charAt24 & 2048) != 0) {
                                    i82 = i20 + 1;
                                    objArr[i84 + 1] = zze[i20];
                                    i59 = i83;
                                } else {
                                    i59 = i83;
                                }
                            }
                            objectFieldOffset = (int) unsafe.objectFieldOffset(zza5);
                            int i85 = i15;
                            if ((charAt24 & 4096) == 4096 || i72 > 17) {
                                str = zzd;
                                z = z3;
                                z2 = true;
                                i16 = 1048575;
                                i17 = i14;
                                i18 = 0;
                            } else {
                                int i86 = i14 + 1;
                                int charAt26 = zzd.charAt(i14);
                                if (charAt26 >= 55296) {
                                    int i87 = charAt26 & 8191;
                                    int i88 = 13;
                                    while (true) {
                                        i19 = i86 + 1;
                                        charAt14 = zzd.charAt(i86);
                                        if (charAt14 < 55296) {
                                            break;
                                        }
                                        i87 |= (charAt14 & 8191) << i88;
                                        i88 += 13;
                                        i86 = i19;
                                    }
                                    charAt26 = i87 | (charAt14 << i88);
                                    i86 = i19;
                                }
                                z2 = true;
                                int i89 = (i26 << 1) + (charAt26 / 32);
                                Object obj3 = zze[i89];
                                str = zzd;
                                if (obj3 instanceof Field) {
                                    zza2 = (Field) obj3;
                                } else {
                                    zza2 = zza(cls2, (String) obj3);
                                    zze[i89] = zza2;
                                }
                                i17 = i86;
                                z = z3;
                                i16 = (int) unsafe.objectFieldOffset(zza2);
                                i18 = charAt26 % 32;
                            }
                            if (i72 >= 18 && i72 <= 49) {
                                iArr[i61] = objectFieldOffset;
                                i61++;
                            }
                            i58 = i85;
                        }
                        i15 = i20;
                        objectFieldOffset = (int) unsafe.objectFieldOffset(zza5);
                        int i852 = i15;
                        if ((charAt24 & 4096) == 4096) {
                        }
                        str = zzd;
                        z = z3;
                        z2 = true;
                        i16 = 1048575;
                        i17 = i14;
                        i18 = 0;
                        if (i72 >= 18) {
                            iArr[i61] = objectFieldOffset;
                            i61++;
                        }
                        i58 = i852;
                    }
                    i15 = i82;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zza5);
                    int i8522 = i15;
                    if ((charAt24 & 4096) == 4096) {
                    }
                    str = zzd;
                    z = z3;
                    z2 = true;
                    i16 = 1048575;
                    i17 = i14;
                    i18 = 0;
                    if (i72 >= 18) {
                    }
                    i58 = i8522;
                }
                int i90 = i63 + 1;
                iArr2[i63] = charAt23;
                int i91 = i90 + 1;
                int i92 = i26;
                iArr2[i90] = ((charAt24 & 256) != 0 ? 268435456 : 0) | ((charAt24 & 512) != 0 ? 536870912 : 0) | (i72 << 20) | objectFieldOffset;
                int i93 = i91 + 1;
                iArr2[i91] = (i18 << 20) | i16;
                z3 = z;
                i26 = i92;
                charAt2 = i73;
                i60 = i17;
                charAt5 = i13;
                charAt = i74;
                zzd = str;
                i63 = i93;
                length = i11;
            }
            return new zzko<>(iArr2, objArr, charAt, charAt2, zzlaVar.zzc(), z3, false, iArr, charAt5, i57, zzksVar, zzjuVar, zzluVar, zziqVar, zzkhVar);
        }
        ((zzlr) zzkiVar).zza();
        int i94 = zzkz.zzb;
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final T zza() {
        return (T) this.zzo.zza(this.zzg);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        if (com.google.android.gms.internal.vision.zzle.zza(com.google.android.gms.internal.vision.zzma.zzf(r10, r6), com.google.android.gms.internal.vision.zzma.zzf(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zzb(r10, r6) == com.google.android.gms.internal.vision.zzma.zzb(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0090, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zza(r10, r6) == com.google.android.gms.internal.vision.zzma.zza(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a4, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zzb(r10, r6) == com.google.android.gms.internal.vision.zzma.zzb(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b6, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zza(r10, r6) == com.google.android.gms.internal.vision.zzma.zza(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c8, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zza(r10, r6) == com.google.android.gms.internal.vision.zzma.zza(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00da, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zza(r10, r6) == com.google.android.gms.internal.vision.zzma.zza(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f0, code lost:
    
        if (com.google.android.gms.internal.vision.zzle.zza(com.google.android.gms.internal.vision.zzma.zzf(r10, r6), com.google.android.gms.internal.vision.zzma.zzf(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0106, code lost:
    
        if (com.google.android.gms.internal.vision.zzle.zza(com.google.android.gms.internal.vision.zzma.zzf(r10, r6), com.google.android.gms.internal.vision.zzma.zzf(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011c, code lost:
    
        if (com.google.android.gms.internal.vision.zzle.zza(com.google.android.gms.internal.vision.zzma.zzf(r10, r6), com.google.android.gms.internal.vision.zzma.zzf(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x012e, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zzc(r10, r6) == com.google.android.gms.internal.vision.zzma.zzc(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0140, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zza(r10, r6) == com.google.android.gms.internal.vision.zzma.zza(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0154, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zzb(r10, r6) == com.google.android.gms.internal.vision.zzma.zzb(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0165, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zza(r10, r6) == com.google.android.gms.internal.vision.zzma.zza(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0178, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zzb(r10, r6) == com.google.android.gms.internal.vision.zzma.zzb(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x018b, code lost:
    
        if (com.google.android.gms.internal.vision.zzma.zzb(r10, r6) == com.google.android.gms.internal.vision.zzma.zzb(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01a4, code lost:
    
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zzma.zzd(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zzma.zzd(r11, r6))) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01bf, code lost:
    
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zzma.zze(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zzma.zze(r11, r6))) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
    
        if (com.google.android.gms.internal.vision.zzle.zza(com.google.android.gms.internal.vision.zzma.zzf(r10, r6), com.google.android.gms.internal.vision.zzma.zzf(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c5 A[LOOP:0: B:2:0x0005->B:86:0x01c5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c4 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.vision.zzlc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zza(T t, T t2) {
        int length = this.zzc.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzd = zzd(i);
                long j = zzd & 1048575;
                switch ((zzd & 267386880) >>> 20) {
                    case 0:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                            return false;
                        }
                        i += 3;
                    case 1:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 2:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 3:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 4:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 5:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 6:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 7:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 8:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 9:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 10:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 11:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 12:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 13:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 14:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 15:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 16:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    case 17:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
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
                        z = zzle.zza(zzma.zzf(t, j), zzma.zzf(t2, j));
                        if (z) {
                        }
                        break;
                    case 50:
                        z = zzle.zza(zzma.zzf(t, j), zzma.zzf(t2, j));
                        if (z) {
                        }
                        break;
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
                        long zze = zze(i) & 1048575;
                        if (zzma.zza(t, zze) == zzma.zza(t2, zze)) {
                            break;
                        }
                        z = false;
                        if (z) {
                        }
                        break;
                    default:
                        if (z) {
                        }
                        break;
                }
            } else {
                if (!this.zzq.zzb(t).equals(this.zzq.zzb(t2))) {
                    return false;
                }
                if (this.zzh) {
                    return this.zzr.zza(t).equals(this.zzr.zza(t2));
                }
                return true;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001b. Please report as an issue. */
    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zza(T t) {
        int i;
        int zza2;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzd = zzd(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzd;
            int i5 = 37;
            switch ((zzd & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zza2 = zzjf.zza(Double.doubleToLongBits(zzma.zze(t, j)));
                    i2 = i + zza2;
                    break;
                case 1:
                    i = i2 * 53;
                    zza2 = Float.floatToIntBits(zzma.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 2:
                    i = i2 * 53;
                    zza2 = zzjf.zza(zzma.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 3:
                    i = i2 * 53;
                    zza2 = zzjf.zza(zzma.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 4:
                    i = i2 * 53;
                    zza2 = zzma.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 5:
                    i = i2 * 53;
                    zza2 = zzjf.zza(zzma.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 6:
                    i = i2 * 53;
                    zza2 = zzma.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 7:
                    i = i2 * 53;
                    zza2 = zzjf.zza(zzma.zzc(t, j));
                    i2 = i + zza2;
                    break;
                case 8:
                    i = i2 * 53;
                    zza2 = ((String) zzma.zzf(t, j)).hashCode();
                    i2 = i + zza2;
                    break;
                case 9:
                    Object zzf = zzma.zzf(t, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zza2 = zzma.zzf(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 11:
                    i = i2 * 53;
                    zza2 = zzma.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 12:
                    i = i2 * 53;
                    zza2 = zzma.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 13:
                    i = i2 * 53;
                    zza2 = zzma.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 14:
                    i = i2 * 53;
                    zza2 = zzjf.zza(zzma.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 15:
                    i = i2 * 53;
                    zza2 = zzma.zza(t, j);
                    i2 = i + zza2;
                    break;
                case 16:
                    i = i2 * 53;
                    zza2 = zzjf.zza(zzma.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 17:
                    Object zzf2 = zzma.zzf(t, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
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
                    zza2 = zzma.zzf(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 50:
                    i = i2 * 53;
                    zza2 = zzma.zzf(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 51:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(Double.doubleToLongBits(zzb(t, j)));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = Float.floatToIntBits(zzc(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(zzf(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = ((String) zzma.zzf(t, j)).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzma.zzf(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzma.zzf(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzd(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjf.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzko<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzma.zzf(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzq.zzb(t).hashCode();
        return this.zzh ? (hashCode * 53) + this.zzr.zza(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzb(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzd = zzd(i);
            long j = 1048575 & zzd;
            int i2 = this.zzc[i];
            switch ((zzd & 267386880) >>> 20) {
                case 0:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza(t, j, zzma.zze(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zzd(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zzb(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zzb(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zza(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zzb(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zza(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza(t, j, zzma.zzc(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza(t, j, zzma.zzf(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza(t, j, zzma.zzf(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zza(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zza(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zza(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zzb(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zza(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zza((zzko<T>) t2, i)) {
                        zzma.zza((Object) t, j, zzma.zzb(t2, j));
                        zzb((zzko<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zza(t, t2, i);
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
                    this.zzp.zza(t, t2, j);
                    break;
                case 50:
                    zzle.zza(this.zzs, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zza((zzko<T>) t2, i2, i)) {
                        zzma.zza(t, j, zzma.zzf(t2, j));
                        zzb((zzko<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza((zzko<T>) t2, i2, i)) {
                        zzma.zza(t, j, zzma.zzf(t2, j));
                        zzb((zzko<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        zzle.zza(this.zzq, t, t2);
        if (this.zzh) {
            zzle.zza(this.zzr, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzd = zzd(i) & 1048575;
        if (zza((zzko<T>) t2, i)) {
            Object zzf = zzma.zzf(t, zzd);
            Object zzf2 = zzma.zzf(t2, zzd);
            if (zzf != null && zzf2 != null) {
                zzma.zza(t, zzd, zzjf.zza(zzf, zzf2));
                zzb((zzko<T>) t, i);
            } else if (zzf2 != null) {
                zzma.zza(t, zzd, zzf2);
                zzb((zzko<T>) t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzd = zzd(i);
        int i2 = this.zzc[i];
        long j = zzd & 1048575;
        if (zza((zzko<T>) t2, i2, i)) {
            Object zzf = zza((zzko<T>) t, i2, i) ? zzma.zzf(t, j) : null;
            Object zzf2 = zzma.zzf(t2, j);
            if (zzf != null && zzf2 != null) {
                zzma.zza(t, j, zzjf.zza(zzf, zzf2));
                zzb((zzko<T>) t, i2, i);
            } else if (zzf2 != null) {
                zzma.zza(t, j, zzf2);
                zzb((zzko<T>) t, i2, i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x003f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:255:0x04b2. Please report as an issue. */
    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zzb(T t) {
        int i;
        int i2;
        int i3;
        long j;
        int zzd;
        int zzb2;
        int zzj;
        int zzh;
        int zzi;
        int zze;
        int zzg;
        int zzb3;
        int zzi2;
        int zze2;
        int zzg2;
        int i4 = 267386880;
        int i5 = 1048575;
        int i6 = 1;
        int i7 = 0;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i8 = 0;
            int i9 = 0;
            while (i8 < this.zzc.length) {
                int zzd2 = zzd(i8);
                int i10 = (zzd2 & i4) >>> 20;
                int i11 = this.zzc[i8];
                long j2 = zzd2 & 1048575;
                if (i10 >= zziv.DOUBLE_LIST_PACKED.zza() && i10 <= zziv.SINT64_LIST_PACKED.zza()) {
                    int i12 = this.zzc[i8 + 2];
                }
                switch (i10) {
                    case 0:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzb(i11, 0.0d);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzb(i11, 0.0f);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzd(i11, zzma.zzb(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zze(i11, zzma.zzb(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzf(i11, zzma.zza(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzg(i11, 0L);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzi(i11, 0);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzb(i11, true);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzko<T>) t, i8)) {
                            Object zzf = zzma.zzf(t, j2);
                            if (zzf instanceof zzht) {
                                zzb3 = zzii.zzc(i11, (zzht) zzf);
                            } else {
                                zzb3 = zzii.zzb(i11, (String) zzf);
                            }
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzle.zza(i11, zzma.zzf(t, j2), zza(i8));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzc(i11, (zzht) zzma.zzf(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzg(i11, zzma.zza(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzk(i11, zzma.zza(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzj(i11, 0);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzh(i11, 0L);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzh(i11, zzma.zza(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzf(i11, zzma.zzb(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza((zzko<T>) t, i8)) {
                            zzb3 = zzii.zzc(i11, (zzkk) zzma.zzf(t, j2), zza(i8));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzb3 = zzle.zzi(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 19:
                        zzb3 = zzle.zzh(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 20:
                        zzb3 = zzle.zza(i11, (List<Long>) zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 21:
                        zzb3 = zzle.zzb(i11, (List<Long>) zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 22:
                        zzb3 = zzle.zze(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 23:
                        zzb3 = zzle.zzi(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 24:
                        zzb3 = zzle.zzh(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 25:
                        zzb3 = zzle.zzj(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 26:
                        zzb3 = zzle.zza(i11, zza(t, j2));
                        i9 += zzb3;
                        break;
                    case 27:
                        zzb3 = zzle.zza(i11, zza(t, j2), zza(i8));
                        i9 += zzb3;
                        break;
                    case 28:
                        zzb3 = zzle.zzb(i11, zza(t, j2));
                        i9 += zzb3;
                        break;
                    case 29:
                        zzb3 = zzle.zzf(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 30:
                        zzb3 = zzle.zzd(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 31:
                        zzb3 = zzle.zzh(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 32:
                        zzb3 = zzle.zzi(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 33:
                        zzb3 = zzle.zzg(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 34:
                        zzb3 = zzle.zzc(i11, zza(t, j2), false);
                        i9 += zzb3;
                        break;
                    case 35:
                        zzi2 = zzle.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        zzi2 = zzle.zzh((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        zzi2 = zzle.zza((List<Long>) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        zzi2 = zzle.zzb((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        zzi2 = zzle.zze((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        zzi2 = zzle.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        zzi2 = zzle.zzh((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        zzi2 = zzle.zzj((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        zzi2 = zzle.zzf((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        zzi2 = zzle.zzd((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        zzi2 = zzle.zzh((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        zzi2 = zzle.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        zzi2 = zzle.zzg((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        zzi2 = zzle.zzc((List) unsafe.getObject(t, j2));
                        if (zzi2 > 0) {
                            zze2 = zzii.zze(i11);
                            zzg2 = zzii.zzg(zzi2);
                            zzb3 = zze2 + zzg2 + zzi2;
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        zzb3 = zzle.zzb(i11, (List<zzkk>) zza(t, j2), zza(i8));
                        i9 += zzb3;
                        break;
                    case 50:
                        zzb3 = this.zzs.zza(i11, zzma.zzf(t, j2), zzb(i8));
                        i9 += zzb3;
                        break;
                    case 51:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzb(i11, 0.0d);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzb(i11, 0.0f);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzd(i11, zze(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zze(i11, zze(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzf(i11, zzd(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzg(i11, 0L);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzi(i11, 0);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzb(i11, true);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzko<T>) t, i11, i8)) {
                            Object zzf2 = zzma.zzf(t, j2);
                            if (zzf2 instanceof zzht) {
                                zzb3 = zzii.zzc(i11, (zzht) zzf2);
                            } else {
                                zzb3 = zzii.zzb(i11, (String) zzf2);
                            }
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzle.zza(i11, zzma.zzf(t, j2), zza(i8));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzc(i11, (zzht) zzma.zzf(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzg(i11, zzd(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzk(i11, zzd(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzj(i11, 0);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzh(i11, 0L);
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzh(i11, zzd(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzf(i11, zze(t, j2));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzko<T>) t, i11, i8)) {
                            zzb3 = zzii.zzc(i11, (zzkk) zzma.zzf(t, j2), zza(i8));
                            i9 += zzb3;
                            break;
                        } else {
                            break;
                        }
                }
                i8 += 3;
                i4 = 267386880;
            }
            return i9 + zza((zzlu) this.zzq, (Object) t);
        }
        Unsafe unsafe2 = zzb;
        int i13 = 1048575;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < this.zzc.length) {
            int zzd3 = zzd(i14);
            int[] iArr = this.zzc;
            int i17 = iArr[i14];
            int i18 = (zzd3 & 267386880) >>> 20;
            if (i18 <= 17) {
                int i19 = iArr[i14 + 2];
                int i20 = i19 & i5;
                i = i6 << (i19 >>> 20);
                if (i20 != i13) {
                    i16 = unsafe2.getInt(t, i20);
                    i13 = i20;
                }
            } else {
                i = 0;
            }
            long j3 = zzd3 & i5;
            switch (i18) {
                case 0:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzii.zzb(i17, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzii.zzb(i17, 0.0f);
                    }
                    break;
                case 2:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzii.zzd(i17, unsafe2.getLong(t, j3));
                        i15 += zzd;
                    }
                    break;
                case 3:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzii.zze(i17, unsafe2.getLong(t, j3));
                        i15 += zzd;
                    }
                    break;
                case 4:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzii.zzf(i17, unsafe2.getInt(t, j3));
                        i15 += zzd;
                    }
                    break;
                case 5:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        zzd = zzii.zzg(i17, 0L);
                        i15 += zzd;
                    }
                    break;
                case 6:
                    i2 = 1;
                    i3 = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzii.zzi(i17, 0);
                    }
                    j = 0;
                    break;
                case 7:
                    if ((i16 & i) != 0) {
                        i2 = 1;
                        i15 += zzii.zzb(i17, true);
                        i3 = 0;
                        j = 0;
                        break;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                case 8:
                    if ((i16 & i) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzht) {
                            zzb2 = zzii.zzc(i17, (zzht) object);
                        } else {
                            zzb2 = zzii.zzb(i17, (String) object);
                        }
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 9:
                    if ((i16 & i) != 0) {
                        zzb2 = zzle.zza(i17, unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 10:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzc(i17, (zzht) unsafe2.getObject(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 11:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzg(i17, unsafe2.getInt(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 12:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzk(i17, unsafe2.getInt(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 13:
                    if ((i16 & i) != 0) {
                        zzj = zzii.zzj(i17, 0);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 14:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzh(i17, 0L);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 15:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzh(i17, unsafe2.getInt(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 16:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzf(i17, unsafe2.getLong(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 17:
                    if ((i16 & i) != 0) {
                        zzb2 = zzii.zzc(i17, (zzkk) unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 18:
                    zzb2 = zzle.zzi(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 19:
                    i3 = 0;
                    zzh = zzle.zzh(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 20:
                    i3 = 0;
                    zzh = zzle.zza(i17, (List<Long>) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 21:
                    i3 = 0;
                    zzh = zzle.zzb(i17, (List<Long>) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 22:
                    i3 = 0;
                    zzh = zzle.zze(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 23:
                    i3 = 0;
                    zzh = zzle.zzi(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 24:
                    i3 = 0;
                    zzh = zzle.zzh(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 25:
                    i3 = 0;
                    zzh = zzle.zzj(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 26:
                    zzb2 = zzle.zza(i17, (List<?>) unsafe2.getObject(t, j3));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 27:
                    zzb2 = zzle.zza(i17, (List<?>) unsafe2.getObject(t, j3), zza(i14));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 28:
                    zzb2 = zzle.zzb(i17, (List) unsafe2.getObject(t, j3));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 29:
                    zzb2 = zzle.zzf(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 30:
                    i3 = 0;
                    zzh = zzle.zzd(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 31:
                    i3 = 0;
                    zzh = zzle.zzh(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 32:
                    i3 = 0;
                    zzh = zzle.zzi(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 33:
                    i3 = 0;
                    zzh = zzle.zzg(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 34:
                    i3 = 0;
                    zzh = zzle.zzc(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzh;
                    i2 = 1;
                    j = 0;
                    break;
                case 35:
                    zzi = zzle.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 36:
                    zzi = zzle.zzh((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 37:
                    zzi = zzle.zza((List<Long>) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 38:
                    zzi = zzle.zzb((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 39:
                    zzi = zzle.zze((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 40:
                    zzi = zzle.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 41:
                    zzi = zzle.zzh((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 42:
                    zzi = zzle.zzj((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 43:
                    zzi = zzle.zzf((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 44:
                    zzi = zzle.zzd((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 45:
                    zzi = zzle.zzh((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 46:
                    zzi = zzle.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 47:
                    zzi = zzle.zzg((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 48:
                    zzi = zzle.zzc((List) unsafe2.getObject(t, j3));
                    if (zzi > 0) {
                        zze = zzii.zze(i17);
                        zzg = zzii.zzg(zzi);
                        zzj = zze + zzg + zzi;
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 49:
                    zzb2 = zzle.zzb(i17, (List<zzkk>) unsafe2.getObject(t, j3), zza(i14));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 50:
                    zzb2 = this.zzs.zza(i17, unsafe2.getObject(t, j3), zzb(i14));
                    i15 += zzb2;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 51:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzb(i17, 0.0d);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 52:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzj = zzii.zzb(i17, 0.0f);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 53:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzd(i17, zze(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 54:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zze(i17, zze(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 55:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzf(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 56:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzg(i17, 0L);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 57:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzj = zzii.zzi(i17, 0);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 58:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzj = zzii.zzb(i17, true);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 59:
                    if (zza((zzko<T>) t, i17, i14)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzht) {
                            zzb2 = zzii.zzc(i17, (zzht) object2);
                        } else {
                            zzb2 = zzii.zzb(i17, (String) object2);
                        }
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 60:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzle.zza(i17, unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 61:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzc(i17, (zzht) unsafe2.getObject(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 62:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzg(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 63:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzk(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 64:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzj = zzii.zzj(i17, 0);
                        i15 += zzj;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 65:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzh(i17, 0L);
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 66:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzh(i17, zzd(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 67:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzf(i17, zze(t, j3));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                case 68:
                    if (zza((zzko<T>) t, i17, i14)) {
                        zzb2 = zzii.zzc(i17, (zzkk) unsafe2.getObject(t, j3), zza(i14));
                        i15 += zzb2;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
                default:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    break;
            }
            i14 += 3;
            i6 = i2;
            i7 = i3;
            i5 = 1048575;
        }
        int i21 = i7;
        int zza2 = i15 + zza((zzlu) this.zzq, (Object) t);
        if (!this.zzh) {
            return zza2;
        }
        zziu<?> zza3 = this.zzr.zza(t);
        for (int i22 = i21; i22 < zza3.zza.zzc(); i22++) {
            Map.Entry<?, Object> zzb4 = zza3.zza.zzb(i22);
            i21 += zziu.zzc((zziw) zzb4.getKey(), zzb4.getValue());
        }
        for (Map.Entry<?, Object> entry : zza3.zza.zzd()) {
            i21 += zziu.zzc((zziw) entry.getKey(), entry.getValue());
        }
        return zza2 + i21;
    }

    private static <UT, UB> int zza(zzlu<UT, UB> zzluVar, T t) {
        return zzluVar.zzf(zzluVar.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzma.zzf(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0a2a  */
    @Override // com.google.android.gms.internal.vision.zzlc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t, zzmr zzmrVar) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        int i;
        Iterator<Map.Entry<?, Object>> it2;
        Map.Entry<?, ?> entry2;
        int length2;
        if (zzmrVar.zza() == zzmq.zzb) {
            zza(this.zzq, t, zzmrVar);
            if (this.zzh) {
                zziu<?> zza2 = this.zzr.zza(t);
                if (!zza2.zza.isEmpty()) {
                    it2 = zza2.zze();
                    entry2 = (Map.Entry) it2.next();
                    for (length2 = this.zzc.length - 3; length2 >= 0; length2 -= 3) {
                        int zzd = zzd(length2);
                        int i2 = this.zzc[length2];
                        while (entry2 != null && this.zzr.zza(entry2) > i2) {
                            this.zzr.zza(zzmrVar, entry2);
                            entry2 = it2.hasNext() ? (Map.Entry) it2.next() : null;
                        }
                        switch ((zzd & 267386880) >>> 20) {
                            case 0:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, zzma.zze(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, zzma.zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, zzma.zzb(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzc(i2, zzma.zzb(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzc(i2, zzma.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzd(i2, zzma.zzb(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzd(i2, zzma.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, zzma.zzc(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (zza((zzko<T>) t, length2)) {
                                    zza(i2, zzma.zzf(t, zzd & 1048575), zzmrVar);
                                    break;
                                } else {
                                    break;
                                }
                            case 9:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, zzma.zzf(t, zzd & 1048575), zza(length2));
                                    break;
                                } else {
                                    break;
                                }
                            case 10:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, (zzht) zzma.zzf(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 11:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zze(i2, zzma.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzb(i2, zzma.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zza(i2, zzma.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzb(i2, zzma.zzb(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzf(i2, zzma.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zze(i2, zzma.zzb(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (zza((zzko<T>) t, length2)) {
                                    zzmrVar.zzb(i2, zzma.zzf(t, zzd & 1048575), zza(length2));
                                    break;
                                } else {
                                    break;
                                }
                            case 18:
                                zzle.zza(this.zzc[length2], (List<Double>) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 19:
                                zzle.zzb(this.zzc[length2], (List<Float>) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 20:
                                zzle.zzc(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 21:
                                zzle.zzd(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 22:
                                zzle.zzh(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 23:
                                zzle.zzf(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 24:
                                zzle.zzk(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 25:
                                zzle.zzn(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 26:
                                zzle.zza(this.zzc[length2], (List<String>) zzma.zzf(t, zzd & 1048575), zzmrVar);
                                break;
                            case 27:
                                zzle.zza(this.zzc[length2], (List<?>) zzma.zzf(t, zzd & 1048575), zzmrVar, zza(length2));
                                break;
                            case 28:
                                zzle.zzb(this.zzc[length2], (List<zzht>) zzma.zzf(t, zzd & 1048575), zzmrVar);
                                break;
                            case 29:
                                zzle.zzi(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 30:
                                zzle.zzm(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 31:
                                zzle.zzl(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 32:
                                zzle.zzg(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 33:
                                zzle.zzj(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 34:
                                zzle.zze(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, false);
                                break;
                            case 35:
                                zzle.zza(this.zzc[length2], (List<Double>) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 36:
                                zzle.zzb(this.zzc[length2], (List<Float>) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 37:
                                zzle.zzc(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 38:
                                zzle.zzd(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 39:
                                zzle.zzh(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 40:
                                zzle.zzf(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 41:
                                zzle.zzk(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 42:
                                zzle.zzn(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 43:
                                zzle.zzi(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 44:
                                zzle.zzm(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 45:
                                zzle.zzl(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 46:
                                zzle.zzg(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 47:
                                zzle.zzj(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 48:
                                zzle.zze(this.zzc[length2], (List) zzma.zzf(t, zzd & 1048575), zzmrVar, true);
                                break;
                            case 49:
                                zzle.zzb(this.zzc[length2], (List<?>) zzma.zzf(t, zzd & 1048575), zzmrVar, zza(length2));
                                break;
                            case 50:
                                zza(zzmrVar, i2, zzma.zzf(t, zzd & 1048575), length2);
                                break;
                            case 51:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, zzb(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, zzc(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, zze(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzc(i2, zze(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzc(i2, zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzd(i2, zze(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzd(i2, zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, zzf(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zza(i2, zzma.zzf(t, zzd & 1048575), zzmrVar);
                                    break;
                                } else {
                                    break;
                                }
                            case 60:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, zzma.zzf(t, zzd & 1048575), zza(length2));
                                    break;
                                } else {
                                    break;
                                }
                            case 61:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, (zzht) zzma.zzf(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 62:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zze(i2, zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzb(i2, zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zza(i2, zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzb(i2, zze(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzf(i2, zzd(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zze(i2, zze(t, zzd & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (zza((zzko<T>) t, i2, length2)) {
                                    zzmrVar.zzb(i2, zzma.zzf(t, zzd & 1048575), zza(length2));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                    while (entry2 != null) {
                        this.zzr.zza(zzmrVar, entry2);
                        entry2 = it2.hasNext() ? (Map.Entry) it2.next() : null;
                    }
                    return;
                }
            }
            it2 = null;
            entry2 = null;
            while (length2 >= 0) {
            }
            while (entry2 != null) {
            }
            return;
        }
        if (this.zzj) {
            if (this.zzh) {
                zziu<?> zza3 = this.zzr.zza(t);
                if (!zza3.zza.isEmpty()) {
                    it = zza3.zzd();
                    entry = (Map.Entry) it.next();
                    length = this.zzc.length;
                    for (i = 0; i < length; i += 3) {
                        int zzd2 = zzd(i);
                        int i3 = this.zzc[i];
                        while (entry != null && this.zzr.zza(entry) <= i3) {
                            this.zzr.zza(zzmrVar, entry);
                            entry = it.hasNext() ? (Map.Entry) it.next() : null;
                        }
                        switch ((zzd2 & 267386880) >>> 20) {
                            case 0:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, zzma.zze(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, zzma.zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, zzma.zzb(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzc(i3, zzma.zzb(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzc(i3, zzma.zza(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzd(i3, zzma.zzb(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzd(i3, zzma.zza(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, zzma.zzc(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (zza((zzko<T>) t, i)) {
                                    zza(i3, zzma.zzf(t, zzd2 & 1048575), zzmrVar);
                                    break;
                                } else {
                                    break;
                                }
                            case 9:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, zzma.zzf(t, zzd2 & 1048575), zza(i));
                                    break;
                                } else {
                                    break;
                                }
                            case 10:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, (zzht) zzma.zzf(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 11:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zze(i3, zzma.zza(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzb(i3, zzma.zza(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zza(i3, zzma.zza(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzb(i3, zzma.zzb(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzf(i3, zzma.zza(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zze(i3, zzma.zzb(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (zza((zzko<T>) t, i)) {
                                    zzmrVar.zzb(i3, zzma.zzf(t, zzd2 & 1048575), zza(i));
                                    break;
                                } else {
                                    break;
                                }
                            case 18:
                                zzle.zza(this.zzc[i], (List<Double>) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 19:
                                zzle.zzb(this.zzc[i], (List<Float>) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 20:
                                zzle.zzc(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 21:
                                zzle.zzd(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 22:
                                zzle.zzh(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 23:
                                zzle.zzf(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 24:
                                zzle.zzk(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 25:
                                zzle.zzn(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 26:
                                zzle.zza(this.zzc[i], (List<String>) zzma.zzf(t, zzd2 & 1048575), zzmrVar);
                                break;
                            case 27:
                                zzle.zza(this.zzc[i], (List<?>) zzma.zzf(t, zzd2 & 1048575), zzmrVar, zza(i));
                                break;
                            case 28:
                                zzle.zzb(this.zzc[i], (List<zzht>) zzma.zzf(t, zzd2 & 1048575), zzmrVar);
                                break;
                            case 29:
                                zzle.zzi(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 30:
                                zzle.zzm(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 31:
                                zzle.zzl(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 32:
                                zzle.zzg(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 33:
                                zzle.zzj(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 34:
                                zzle.zze(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, false);
                                break;
                            case 35:
                                zzle.zza(this.zzc[i], (List<Double>) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 36:
                                zzle.zzb(this.zzc[i], (List<Float>) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 37:
                                zzle.zzc(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 38:
                                zzle.zzd(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 39:
                                zzle.zzh(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 40:
                                zzle.zzf(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 41:
                                zzle.zzk(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 42:
                                zzle.zzn(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 43:
                                zzle.zzi(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 44:
                                zzle.zzm(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 45:
                                zzle.zzl(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 46:
                                zzle.zzg(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 47:
                                zzle.zzj(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 48:
                                zzle.zze(this.zzc[i], (List) zzma.zzf(t, zzd2 & 1048575), zzmrVar, true);
                                break;
                            case 49:
                                zzle.zzb(this.zzc[i], (List<?>) zzma.zzf(t, zzd2 & 1048575), zzmrVar, zza(i));
                                break;
                            case 50:
                                zza(zzmrVar, i3, zzma.zzf(t, zzd2 & 1048575), i);
                                break;
                            case 51:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, zzb(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, zzc(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, zze(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzc(i3, zze(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzc(i3, zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzd(i3, zze(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzd(i3, zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, zzf(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zza(i3, zzma.zzf(t, zzd2 & 1048575), zzmrVar);
                                    break;
                                } else {
                                    break;
                                }
                            case 60:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, zzma.zzf(t, zzd2 & 1048575), zza(i));
                                    break;
                                } else {
                                    break;
                                }
                            case 61:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, (zzht) zzma.zzf(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 62:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zze(i3, zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzb(i3, zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zza(i3, zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzb(i3, zze(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzf(i3, zzd(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zze(i3, zze(t, zzd2 & 1048575));
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (zza((zzko<T>) t, i3, i)) {
                                    zzmrVar.zzb(i3, zzma.zzf(t, zzd2 & 1048575), zza(i));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                    while (entry != null) {
                        this.zzr.zza(zzmrVar, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    zza(this.zzq, t, zzmrVar);
                    return;
                }
            }
            it = null;
            entry = null;
            length = this.zzc.length;
            while (i < length) {
            }
            while (entry != null) {
            }
            zza(this.zzq, t, zzmrVar);
            return;
        }
        zzb((zzko<T>) t, zzmrVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x007e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:216:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzb(T t, zzmr zzmrVar) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        int i;
        int i2;
        boolean z;
        if (this.zzh) {
            zziu<?> zza2 = this.zzr.zza(t);
            if (!zza2.zza.isEmpty()) {
                it = zza2.zzd();
                entry = (Map.Entry) it.next();
                length = this.zzc.length;
                Unsafe unsafe = zzb;
                int i3 = 1048575;
                int i4 = 0;
                for (i = 0; i < length; i += 3) {
                    int zzd = zzd(i);
                    int[] iArr = this.zzc;
                    int i5 = iArr[i];
                    int i6 = (zzd & 267386880) >>> 20;
                    if (i6 <= 17) {
                        int i7 = iArr[i + 2];
                        int i8 = i7 & 1048575;
                        if (i8 != i3) {
                            i4 = unsafe.getInt(t, i8);
                            i3 = i8;
                        }
                        i2 = 1 << (i7 >>> 20);
                    } else {
                        i2 = 0;
                    }
                    while (entry != null && this.zzr.zza(entry) <= i5) {
                        this.zzr.zza(zzmrVar, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    long j = zzd & 1048575;
                    switch (i6) {
                        case 0:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, zzma.zze(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, zzma.zzd(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzc(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzc(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzd(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzd(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, zzma.zzc(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if ((i2 & i4) != 0) {
                                zza(i5, unsafe.getObject(t, j), zzmrVar);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, unsafe.getObject(t, j), zza(i));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, (zzht) unsafe.getObject(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zze(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzb(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zza(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzb(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzf(i5, unsafe.getInt(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zze(i5, unsafe.getLong(t, j));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if ((i2 & i4) != 0) {
                                zzmrVar.zzb(i5, unsafe.getObject(t, j), zza(i));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            z = false;
                            zzle.zza(this.zzc[i], (List<Double>) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 19:
                            z = false;
                            zzle.zzb(this.zzc[i], (List<Float>) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 20:
                            z = false;
                            zzle.zzc(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 21:
                            z = false;
                            zzle.zzd(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 22:
                            z = false;
                            zzle.zzh(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 23:
                            z = false;
                            zzle.zzf(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 24:
                            z = false;
                            zzle.zzk(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 25:
                            z = false;
                            zzle.zzn(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 26:
                            zzle.zza(this.zzc[i], (List<String>) unsafe.getObject(t, j), zzmrVar);
                            break;
                        case 27:
                            zzle.zza(this.zzc[i], (List<?>) unsafe.getObject(t, j), zzmrVar, zza(i));
                            break;
                        case 28:
                            zzle.zzb(this.zzc[i], (List<zzht>) unsafe.getObject(t, j), zzmrVar);
                            break;
                        case 29:
                            z = false;
                            zzle.zzi(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 30:
                            z = false;
                            zzle.zzm(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 31:
                            z = false;
                            zzle.zzl(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 32:
                            z = false;
                            zzle.zzg(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 33:
                            z = false;
                            zzle.zzj(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 34:
                            z = false;
                            zzle.zze(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, false);
                            break;
                        case 35:
                            zzle.zza(this.zzc[i], (List<Double>) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 36:
                            zzle.zzb(this.zzc[i], (List<Float>) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 37:
                            zzle.zzc(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 38:
                            zzle.zzd(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 39:
                            zzle.zzh(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 40:
                            zzle.zzf(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 41:
                            zzle.zzk(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 42:
                            zzle.zzn(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 43:
                            zzle.zzi(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 44:
                            zzle.zzm(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 45:
                            zzle.zzl(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 46:
                            zzle.zzg(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 47:
                            zzle.zzj(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 48:
                            zzle.zze(this.zzc[i], (List) unsafe.getObject(t, j), zzmrVar, true);
                            break;
                        case 49:
                            zzle.zzb(this.zzc[i], (List<?>) unsafe.getObject(t, j), zzmrVar, zza(i));
                            break;
                        case 50:
                            zza(zzmrVar, i5, unsafe.getObject(t, j), i);
                            break;
                        case 51:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, zzb(t, j));
                            }
                            break;
                        case 52:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, zzc(t, j));
                            }
                            break;
                        case 53:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, zze(t, j));
                            }
                            break;
                        case 54:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzc(i5, zze(t, j));
                            }
                            break;
                        case 55:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzc(i5, zzd(t, j));
                            }
                            break;
                        case 56:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzd(i5, zze(t, j));
                            }
                            break;
                        case 57:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzd(i5, zzd(t, j));
                            }
                            break;
                        case 58:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, zzf(t, j));
                            }
                            break;
                        case 59:
                            if (zza((zzko<T>) t, i5, i)) {
                                zza(i5, unsafe.getObject(t, j), zzmrVar);
                            }
                            break;
                        case 60:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, unsafe.getObject(t, j), zza(i));
                            }
                            break;
                        case 61:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, (zzht) unsafe.getObject(t, j));
                            }
                            break;
                        case 62:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zze(i5, zzd(t, j));
                            }
                            break;
                        case 63:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzb(i5, zzd(t, j));
                            }
                            break;
                        case 64:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zza(i5, zzd(t, j));
                            }
                            break;
                        case 65:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzb(i5, zze(t, j));
                            }
                            break;
                        case 66:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzf(i5, zzd(t, j));
                            }
                            break;
                        case 67:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zze(i5, zze(t, j));
                            }
                            break;
                        case 68:
                            if (zza((zzko<T>) t, i5, i)) {
                                zzmrVar.zzb(i5, unsafe.getObject(t, j), zza(i));
                            }
                            break;
                    }
                }
                while (entry != null) {
                    this.zzr.zza(zzmrVar, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                zza(this.zzq, t, zzmrVar);
            }
        }
        it = null;
        entry = null;
        length = this.zzc.length;
        Unsafe unsafe2 = zzb;
        int i32 = 1048575;
        int i42 = 0;
        while (i < length) {
        }
        while (entry != null) {
        }
        zza(this.zzq, t, zzmrVar);
    }

    private final <K, V> void zza(zzmr zzmrVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzmrVar.zza(i, this.zzs.zzb(zzb(i2)), this.zzs.zzc(obj));
        }
    }

    private static <UT, UB> void zza(zzlu<UT, UB> zzluVar, T t, zzmr zzmrVar) throws IOException {
        zzluVar.zza((zzlu<UT, UB>) zzluVar.zzb(t), zzmrVar);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0084. Please report as an issue. */
    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T t, zzld zzldVar, zzio zzioVar) throws IOException {
        Objects.requireNonNull(zzioVar);
        zzlu zzluVar = this.zzq;
        zziq<?> zziqVar = this.zzr;
        zziu<?> zziuVar = null;
        Object obj = null;
        while (true) {
            try {
                int zza2 = zzldVar.zza();
                int zzg = zzg(zza2);
                if (zzg < 0) {
                    if (zza2 == Integer.MAX_VALUE) {
                        for (int i = this.zzm; i < this.zzn; i++) {
                            obj = zza((Object) t, this.zzl[i], (int) obj, (zzlu<UT, int>) zzluVar);
                        }
                        if (obj != null) {
                            zzluVar.zzb((Object) t, (T) obj);
                            return;
                        }
                        return;
                    }
                    Object zza3 = !this.zzh ? null : zziqVar.zza(zzioVar, this.zzg, zza2);
                    if (zza3 != null) {
                        if (zziuVar == null) {
                            zziuVar = zziqVar.zzb(t);
                        }
                        zziu<?> zziuVar2 = zziuVar;
                        obj = zziqVar.zza(zzldVar, zza3, zzioVar, zziuVar2, obj, zzluVar);
                        zziuVar = zziuVar2;
                    } else {
                        zzluVar.zza(zzldVar);
                        if (obj == null) {
                            obj = zzluVar.zzc(t);
                        }
                        if (!zzluVar.zza((zzlu) obj, zzldVar)) {
                            for (int i2 = this.zzm; i2 < this.zzn; i2++) {
                                obj = zza((Object) t, this.zzl[i2], (int) obj, (zzlu<UT, int>) zzluVar);
                            }
                            if (obj != null) {
                                zzluVar.zzb((Object) t, (T) obj);
                                return;
                            }
                            return;
                        }
                    }
                } else {
                    int zzd = zzd(zzg);
                    switch ((267386880 & zzd) >>> 20) {
                        case 0:
                            zzma.zza(t, zzd & 1048575, zzldVar.zzd());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 1:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zze());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 2:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzg());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 3:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzf());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 4:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzh());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 5:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzi());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 6:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzj());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 7:
                            zzma.zza(t, zzd & 1048575, zzldVar.zzk());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 8:
                            zza(t, zzd, zzldVar);
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 9:
                            if (zza((zzko<T>) t, zzg)) {
                                long j = zzd & 1048575;
                                zzma.zza(t, j, zzjf.zza(zzma.zzf(t, j), zzldVar.zza(zza(zzg), zzioVar)));
                                break;
                            } else {
                                zzma.zza(t, zzd & 1048575, zzldVar.zza(zza(zzg), zzioVar));
                                zzb((zzko<T>) t, zzg);
                                break;
                            }
                        case 10:
                            zzma.zza(t, zzd & 1048575, zzldVar.zzn());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 11:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzo());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 12:
                            int zzp = zzldVar.zzp();
                            zzjg zzc = zzc(zzg);
                            if (zzc != null && !zzc.zza(zzp)) {
                                obj = zzle.zza(zza2, zzp, obj, (zzlu<UT, Object>) zzluVar);
                                break;
                            }
                            zzma.zza((Object) t, zzd & 1048575, zzp);
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 13:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzq());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 14:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzr());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 15:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzs());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 16:
                            zzma.zza((Object) t, zzd & 1048575, zzldVar.zzt());
                            zzb((zzko<T>) t, zzg);
                            break;
                        case 17:
                            if (zza((zzko<T>) t, zzg)) {
                                long j2 = zzd & 1048575;
                                zzma.zza(t, j2, zzjf.zza(zzma.zzf(t, j2), zzldVar.zzb(zza(zzg), zzioVar)));
                                break;
                            } else {
                                zzma.zza(t, zzd & 1048575, zzldVar.zzb(zza(zzg), zzioVar));
                                zzb((zzko<T>) t, zzg);
                                break;
                            }
                        case 18:
                            zzldVar.zza(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 19:
                            zzldVar.zzb(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 20:
                            zzldVar.zzd(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 21:
                            zzldVar.zzc(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 22:
                            zzldVar.zze(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 23:
                            zzldVar.zzf(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 24:
                            zzldVar.zzg(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 25:
                            zzldVar.zzh(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 26:
                            if (zzf(zzd)) {
                                zzldVar.zzj(this.zzp.zza(t, zzd & 1048575));
                                break;
                            } else {
                                zzldVar.zzi(this.zzp.zza(t, zzd & 1048575));
                                break;
                            }
                        case 27:
                            zzldVar.zza(this.zzp.zza(t, zzd & 1048575), zza(zzg), zzioVar);
                            break;
                        case 28:
                            zzldVar.zzk(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 29:
                            zzldVar.zzl(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 30:
                            List<Integer> zza4 = this.zzp.zza(t, zzd & 1048575);
                            zzldVar.zzm(zza4);
                            obj = zzle.zza(zza2, zza4, zzc(zzg), obj, zzluVar);
                            break;
                        case 31:
                            zzldVar.zzn(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 32:
                            zzldVar.zzo(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 33:
                            zzldVar.zzp(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 34:
                            zzldVar.zzq(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 35:
                            zzldVar.zza(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 36:
                            zzldVar.zzb(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 37:
                            zzldVar.zzd(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 38:
                            zzldVar.zzc(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 39:
                            zzldVar.zze(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 40:
                            zzldVar.zzf(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 41:
                            zzldVar.zzg(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 42:
                            zzldVar.zzh(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 43:
                            zzldVar.zzl(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 44:
                            List<Integer> zza5 = this.zzp.zza(t, zzd & 1048575);
                            zzldVar.zzm(zza5);
                            obj = zzle.zza(zza2, zza5, zzc(zzg), obj, zzluVar);
                            break;
                        case 45:
                            zzldVar.zzn(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 46:
                            zzldVar.zzo(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 47:
                            zzldVar.zzp(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 48:
                            zzldVar.zzq(this.zzp.zza(t, zzd & 1048575));
                            break;
                        case 49:
                            zzldVar.zzb(this.zzp.zza(t, zzd & 1048575), zza(zzg), zzioVar);
                            break;
                        case 50:
                            Object zzb2 = zzb(zzg);
                            long zzd2 = zzd(zzg) & 1048575;
                            Object zzf = zzma.zzf(t, zzd2);
                            if (zzf == null) {
                                zzf = this.zzs.zzf(zzb2);
                                zzma.zza(t, zzd2, zzf);
                            } else if (this.zzs.zzd(zzf)) {
                                Object zzf2 = this.zzs.zzf(zzb2);
                                this.zzs.zza(zzf2, zzf);
                                zzma.zza(t, zzd2, zzf2);
                                zzf = zzf2;
                            }
                            zzldVar.zza(this.zzs.zza(zzf), this.zzs.zzb(zzb2), zzioVar);
                            break;
                        case 51:
                            zzma.zza(t, zzd & 1048575, Double.valueOf(zzldVar.zzd()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 52:
                            zzma.zza(t, zzd & 1048575, Float.valueOf(zzldVar.zze()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 53:
                            zzma.zza(t, zzd & 1048575, Long.valueOf(zzldVar.zzg()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 54:
                            zzma.zza(t, zzd & 1048575, Long.valueOf(zzldVar.zzf()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 55:
                            zzma.zza(t, zzd & 1048575, Integer.valueOf(zzldVar.zzh()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 56:
                            zzma.zza(t, zzd & 1048575, Long.valueOf(zzldVar.zzi()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 57:
                            zzma.zza(t, zzd & 1048575, Integer.valueOf(zzldVar.zzj()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 58:
                            zzma.zza(t, zzd & 1048575, Boolean.valueOf(zzldVar.zzk()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 59:
                            zza(t, zzd, zzldVar);
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 60:
                            if (zza((zzko<T>) t, zza2, zzg)) {
                                long j3 = zzd & 1048575;
                                zzma.zza(t, j3, zzjf.zza(zzma.zzf(t, j3), zzldVar.zza(zza(zzg), zzioVar)));
                            } else {
                                zzma.zza(t, zzd & 1048575, zzldVar.zza(zza(zzg), zzioVar));
                                zzb((zzko<T>) t, zzg);
                            }
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 61:
                            zzma.zza(t, zzd & 1048575, zzldVar.zzn());
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 62:
                            zzma.zza(t, zzd & 1048575, Integer.valueOf(zzldVar.zzo()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 63:
                            int zzp2 = zzldVar.zzp();
                            zzjg zzc2 = zzc(zzg);
                            if (zzc2 != null && !zzc2.zza(zzp2)) {
                                obj = zzle.zza(zza2, zzp2, obj, (zzlu<UT, Object>) zzluVar);
                                break;
                            }
                            zzma.zza(t, zzd & 1048575, Integer.valueOf(zzp2));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 64:
                            zzma.zza(t, zzd & 1048575, Integer.valueOf(zzldVar.zzq()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 65:
                            zzma.zza(t, zzd & 1048575, Long.valueOf(zzldVar.zzr()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 66:
                            zzma.zza(t, zzd & 1048575, Integer.valueOf(zzldVar.zzs()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 67:
                            zzma.zza(t, zzd & 1048575, Long.valueOf(zzldVar.zzt()));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        case 68:
                            zzma.zza(t, zzd & 1048575, zzldVar.zzb(zza(zzg), zzioVar));
                            zzb((zzko<T>) t, zza2, zzg);
                            break;
                        default:
                            if (obj == null) {
                                try {
                                    obj = zzluVar.zza();
                                } catch (zzjn unused) {
                                    zzluVar.zza(zzldVar);
                                    if (obj == null) {
                                        obj = zzluVar.zzc(t);
                                    }
                                    if (!zzluVar.zza((zzlu) obj, zzldVar)) {
                                        for (int i3 = this.zzm; i3 < this.zzn; i3++) {
                                            obj = zza((Object) t, this.zzl[i3], (int) obj, (zzlu<UT, int>) zzluVar);
                                        }
                                        if (obj != null) {
                                            zzluVar.zzb((Object) t, (T) obj);
                                            return;
                                        }
                                        return;
                                    }
                                    break;
                                }
                            }
                            if (!zzluVar.zza((zzlu) obj, zzldVar)) {
                                for (int i4 = this.zzm; i4 < this.zzn; i4++) {
                                    obj = zza((Object) t, this.zzl[i4], (int) obj, (zzlu<UT, int>) zzluVar);
                                }
                                if (obj != null) {
                                    zzluVar.zzb((Object) t, (T) obj);
                                    return;
                                }
                                return;
                            }
                            break;
                    }
                }
            } catch (Throwable th) {
                for (int i5 = this.zzm; i5 < this.zzn; i5++) {
                    obj = zza((Object) t, this.zzl[i5], (int) obj, (zzlu<UT, int>) zzluVar);
                }
                if (obj != null) {
                    zzluVar.zzb((Object) t, (T) obj);
                }
                throw th;
            }
        }
    }

    private static zzlx zze(Object obj) {
        zzjb zzjbVar = (zzjb) obj;
        zzlx zzlxVar = zzjbVar.zzb;
        if (zzlxVar != zzlx.zza()) {
            return zzlxVar;
        }
        zzlx zzb2 = zzlx.zzb();
        zzjbVar.zzb = zzb2;
        return zzb2;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    private static int zza(byte[] bArr, int i, int i2, zzml zzmlVar, Class<?> cls, zzhn zzhnVar) throws IOException {
        switch (zzkr.zza[zzmlVar.ordinal()]) {
            case 1:
                int zzb2 = zzhl.zzb(bArr, i, zzhnVar);
                zzhnVar.zzc = Boolean.valueOf(zzhnVar.zzb != 0);
                return zzb2;
            case 2:
                return zzhl.zze(bArr, i, zzhnVar);
            case 3:
                zzhnVar.zzc = Double.valueOf(zzhl.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzhnVar.zzc = Integer.valueOf(zzhl.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzhnVar.zzc = Long.valueOf(zzhl.zzb(bArr, i));
                return i + 8;
            case 8:
                zzhnVar.zzc = Float.valueOf(zzhl.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zza2 = zzhl.zza(bArr, i, zzhnVar);
                zzhnVar.zzc = Integer.valueOf(zzhnVar.zza);
                return zza2;
            case 12:
            case 13:
                int zzb3 = zzhl.zzb(bArr, i, zzhnVar);
                zzhnVar.zzc = Long.valueOf(zzhnVar.zzb);
                return zzb3;
            case 14:
                return zzhl.zza(zzky.zza().zza((Class) cls), bArr, i, i2, zzhnVar);
            case 15:
                int zza3 = zzhl.zza(bArr, i, zzhnVar);
                zzhnVar.zzc = Integer.valueOf(zzif.zze(zzhnVar.zza));
                return zza3;
            case 16:
                int zzb4 = zzhl.zzb(bArr, i, zzhnVar);
                zzhnVar.zzc = Long.valueOf(zzif.zza(zzhnVar.zzb));
                return zzb4;
            case 17:
                return zzhl.zzd(bArr, i, zzhnVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0037. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzhn zzhnVar) throws IOException {
        int zza2;
        int i8 = i;
        Unsafe unsafe = zzb;
        zzjl zzjlVar = (zzjl) unsafe.getObject(t, j2);
        if (!zzjlVar.zza()) {
            int size = zzjlVar.size();
            zzjlVar = zzjlVar.zza(size == 0 ? 10 : size << 1);
            unsafe.putObject(t, j2, zzjlVar);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzin zzinVar = (zzin) zzjlVar;
                    int zza3 = zzhl.zza(bArr, i8, zzhnVar);
                    int i9 = zzhnVar.zza + zza3;
                    while (zza3 < i9) {
                        zzinVar.zza(zzhl.zzc(bArr, zza3));
                        zza3 += 8;
                    }
                    if (zza3 == i9) {
                        return zza3;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 1) {
                    zzin zzinVar2 = (zzin) zzjlVar;
                    zzinVar2.zza(zzhl.zzc(bArr, i));
                    while (true) {
                        int i10 = i8 + 8;
                        if (i10 >= i2) {
                            return i10;
                        }
                        i8 = zzhl.zza(bArr, i10, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return i10;
                        }
                        zzinVar2.zza(zzhl.zzc(bArr, i8));
                    }
                }
                return i8;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzja zzjaVar = (zzja) zzjlVar;
                    int zza4 = zzhl.zza(bArr, i8, zzhnVar);
                    int i11 = zzhnVar.zza + zza4;
                    while (zza4 < i11) {
                        zzjaVar.zza(zzhl.zzd(bArr, zza4));
                        zza4 += 4;
                    }
                    if (zza4 == i11) {
                        return zza4;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 5) {
                    zzja zzjaVar2 = (zzja) zzjlVar;
                    zzjaVar2.zza(zzhl.zzd(bArr, i));
                    while (true) {
                        int i12 = i8 + 4;
                        if (i12 >= i2) {
                            return i12;
                        }
                        i8 = zzhl.zza(bArr, i12, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return i12;
                        }
                        zzjaVar2.zza(zzhl.zzd(bArr, i8));
                    }
                }
                return i8;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzjy zzjyVar = (zzjy) zzjlVar;
                    int zza5 = zzhl.zza(bArr, i8, zzhnVar);
                    int i13 = zzhnVar.zza + zza5;
                    while (zza5 < i13) {
                        zza5 = zzhl.zzb(bArr, zza5, zzhnVar);
                        zzjyVar.zza(zzhnVar.zzb);
                    }
                    if (zza5 == i13) {
                        return zza5;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 0) {
                    zzjy zzjyVar2 = (zzjy) zzjlVar;
                    int zzb2 = zzhl.zzb(bArr, i8, zzhnVar);
                    zzjyVar2.zza(zzhnVar.zzb);
                    while (zzb2 < i2) {
                        int zza6 = zzhl.zza(bArr, zzb2, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return zzb2;
                        }
                        zzb2 = zzhl.zzb(bArr, zza6, zzhnVar);
                        zzjyVar2.zza(zzhnVar.zzb);
                    }
                    return zzb2;
                }
                return i8;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzhl.zza(bArr, i8, (zzjl<?>) zzjlVar, zzhnVar);
                }
                if (i5 == 0) {
                    return zzhl.zza(i3, bArr, i, i2, (zzjl<?>) zzjlVar, zzhnVar);
                }
                return i8;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzjy zzjyVar3 = (zzjy) zzjlVar;
                    int zza7 = zzhl.zza(bArr, i8, zzhnVar);
                    int i14 = zzhnVar.zza + zza7;
                    while (zza7 < i14) {
                        zzjyVar3.zza(zzhl.zzb(bArr, zza7));
                        zza7 += 8;
                    }
                    if (zza7 == i14) {
                        return zza7;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 1) {
                    zzjy zzjyVar4 = (zzjy) zzjlVar;
                    zzjyVar4.zza(zzhl.zzb(bArr, i));
                    while (true) {
                        int i15 = i8 + 8;
                        if (i15 >= i2) {
                            return i15;
                        }
                        i8 = zzhl.zza(bArr, i15, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return i15;
                        }
                        zzjyVar4.zza(zzhl.zzb(bArr, i8));
                    }
                }
                return i8;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzjd zzjdVar = (zzjd) zzjlVar;
                    int zza8 = zzhl.zza(bArr, i8, zzhnVar);
                    int i16 = zzhnVar.zza + zza8;
                    while (zza8 < i16) {
                        zzjdVar.zzc(zzhl.zza(bArr, zza8));
                        zza8 += 4;
                    }
                    if (zza8 == i16) {
                        return zza8;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 5) {
                    zzjd zzjdVar2 = (zzjd) zzjlVar;
                    zzjdVar2.zzc(zzhl.zza(bArr, i));
                    while (true) {
                        int i17 = i8 + 4;
                        if (i17 >= i2) {
                            return i17;
                        }
                        i8 = zzhl.zza(bArr, i17, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return i17;
                        }
                        zzjdVar2.zzc(zzhl.zza(bArr, i8));
                    }
                }
                return i8;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzhr zzhrVar = (zzhr) zzjlVar;
                    zza2 = zzhl.zza(bArr, i8, zzhnVar);
                    int i18 = zzhnVar.zza + zza2;
                    while (zza2 < i18) {
                        zza2 = zzhl.zzb(bArr, zza2, zzhnVar);
                        zzhrVar.zza(zzhnVar.zzb != 0);
                    }
                    if (zza2 != i18) {
                        throw zzjk.zza();
                    }
                    return zza2;
                }
                if (i5 == 0) {
                    zzhr zzhrVar2 = (zzhr) zzjlVar;
                    i8 = zzhl.zzb(bArr, i8, zzhnVar);
                    zzhrVar2.zza(zzhnVar.zzb != 0);
                    while (i8 < i2) {
                        int zza9 = zzhl.zza(bArr, i8, zzhnVar);
                        if (i3 == zzhnVar.zza) {
                            i8 = zzhl.zzb(bArr, zza9, zzhnVar);
                            zzhrVar2.zza(zzhnVar.zzb != 0);
                        }
                    }
                }
                return i8;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int zza10 = zzhl.zza(bArr, i8, zzhnVar);
                        int i19 = zzhnVar.zza;
                        if (i19 < 0) {
                            throw zzjk.zzb();
                        }
                        if (i19 == 0) {
                            zzjlVar.add("");
                        } else {
                            zzjlVar.add(new String(bArr, zza10, i19, zzjf.zza));
                            zza10 += i19;
                        }
                        while (zza10 < i2) {
                            int zza11 = zzhl.zza(bArr, zza10, zzhnVar);
                            if (i3 != zzhnVar.zza) {
                                return zza10;
                            }
                            zza10 = zzhl.zza(bArr, zza11, zzhnVar);
                            int i20 = zzhnVar.zza;
                            if (i20 < 0) {
                                throw zzjk.zzb();
                            }
                            if (i20 == 0) {
                                zzjlVar.add("");
                            } else {
                                zzjlVar.add(new String(bArr, zza10, i20, zzjf.zza));
                                zza10 += i20;
                            }
                        }
                        return zza10;
                    }
                    int zza12 = zzhl.zza(bArr, i8, zzhnVar);
                    int i21 = zzhnVar.zza;
                    if (i21 < 0) {
                        throw zzjk.zzb();
                    }
                    if (i21 == 0) {
                        zzjlVar.add("");
                    } else {
                        int i22 = zza12 + i21;
                        if (!zzmd.zza(bArr, zza12, i22)) {
                            throw zzjk.zzh();
                        }
                        zzjlVar.add(new String(bArr, zza12, i21, zzjf.zza));
                        zza12 = i22;
                    }
                    while (zza12 < i2) {
                        int zza13 = zzhl.zza(bArr, zza12, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return zza12;
                        }
                        zza12 = zzhl.zza(bArr, zza13, zzhnVar);
                        int i23 = zzhnVar.zza;
                        if (i23 < 0) {
                            throw zzjk.zzb();
                        }
                        if (i23 == 0) {
                            zzjlVar.add("");
                        } else {
                            int i24 = zza12 + i23;
                            if (!zzmd.zza(bArr, zza12, i24)) {
                                throw zzjk.zzh();
                            }
                            zzjlVar.add(new String(bArr, zza12, i23, zzjf.zza));
                            zza12 = i24;
                        }
                    }
                    return zza12;
                }
                return i8;
            case 27:
                if (i5 == 2) {
                    return zzhl.zza(zza(i6), i3, bArr, i, i2, zzjlVar, zzhnVar);
                }
                return i8;
            case 28:
                if (i5 == 2) {
                    int zza14 = zzhl.zza(bArr, i8, zzhnVar);
                    int i25 = zzhnVar.zza;
                    if (i25 < 0) {
                        throw zzjk.zzb();
                    }
                    if (i25 > bArr.length - zza14) {
                        throw zzjk.zza();
                    }
                    if (i25 == 0) {
                        zzjlVar.add(zzht.zza);
                    } else {
                        zzjlVar.add(zzht.zza(bArr, zza14, i25));
                        zza14 += i25;
                    }
                    while (zza14 < i2) {
                        int zza15 = zzhl.zza(bArr, zza14, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return zza14;
                        }
                        zza14 = zzhl.zza(bArr, zza15, zzhnVar);
                        int i26 = zzhnVar.zza;
                        if (i26 < 0) {
                            throw zzjk.zzb();
                        }
                        if (i26 > bArr.length - zza14) {
                            throw zzjk.zza();
                        }
                        if (i26 == 0) {
                            zzjlVar.add(zzht.zza);
                        } else {
                            zzjlVar.add(zzht.zza(bArr, zza14, i26));
                            zza14 += i26;
                        }
                    }
                    return zza14;
                }
                return i8;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        zza2 = zzhl.zza(i3, bArr, i, i2, (zzjl<?>) zzjlVar, zzhnVar);
                    }
                    return i8;
                }
                zza2 = zzhl.zza(bArr, i8, (zzjl<?>) zzjlVar, zzhnVar);
                zzjb zzjbVar = (zzjb) t;
                zzlx zzlxVar = zzjbVar.zzb;
                if (zzlxVar == zzlx.zza()) {
                    zzlxVar = null;
                }
                zzlx zzlxVar2 = (zzlx) zzle.zza(i4, zzjlVar, zzc(i6), zzlxVar, this.zzq);
                if (zzlxVar2 != null) {
                    zzjbVar.zzb = zzlxVar2;
                }
                return zza2;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzjd zzjdVar3 = (zzjd) zzjlVar;
                    int zza16 = zzhl.zza(bArr, i8, zzhnVar);
                    int i27 = zzhnVar.zza + zza16;
                    while (zza16 < i27) {
                        zza16 = zzhl.zza(bArr, zza16, zzhnVar);
                        zzjdVar3.zzc(zzif.zze(zzhnVar.zza));
                    }
                    if (zza16 == i27) {
                        return zza16;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 0) {
                    zzjd zzjdVar4 = (zzjd) zzjlVar;
                    int zza17 = zzhl.zza(bArr, i8, zzhnVar);
                    zzjdVar4.zzc(zzif.zze(zzhnVar.zza));
                    while (zza17 < i2) {
                        int zza18 = zzhl.zza(bArr, zza17, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return zza17;
                        }
                        zza17 = zzhl.zza(bArr, zza18, zzhnVar);
                        zzjdVar4.zzc(zzif.zze(zzhnVar.zza));
                    }
                    return zza17;
                }
                return i8;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzjy zzjyVar5 = (zzjy) zzjlVar;
                    int zza19 = zzhl.zza(bArr, i8, zzhnVar);
                    int i28 = zzhnVar.zza + zza19;
                    while (zza19 < i28) {
                        zza19 = zzhl.zzb(bArr, zza19, zzhnVar);
                        zzjyVar5.zza(zzif.zza(zzhnVar.zzb));
                    }
                    if (zza19 == i28) {
                        return zza19;
                    }
                    throw zzjk.zza();
                }
                if (i5 == 0) {
                    zzjy zzjyVar6 = (zzjy) zzjlVar;
                    int zzb3 = zzhl.zzb(bArr, i8, zzhnVar);
                    zzjyVar6.zza(zzif.zza(zzhnVar.zzb));
                    while (zzb3 < i2) {
                        int zza20 = zzhl.zza(bArr, zzb3, zzhnVar);
                        if (i3 != zzhnVar.zza) {
                            return zzb3;
                        }
                        zzb3 = zzhl.zzb(bArr, zza20, zzhnVar);
                        zzjyVar6.zza(zzif.zza(zzhnVar.zzb));
                    }
                    return zzb3;
                }
                return i8;
            case 49:
                if (i5 == 3) {
                    zzlc zza21 = zza(i6);
                    int i29 = (i3 & (-8)) | 4;
                    i8 = zzhl.zza(zza21, bArr, i, i2, i29, zzhnVar);
                    zzjlVar.add(zzhnVar.zzc);
                    while (i8 < i2) {
                        int zza22 = zzhl.zza(bArr, i8, zzhnVar);
                        if (i3 == zzhnVar.zza) {
                            i8 = zzhl.zza(zza21, bArr, zza22, i2, i29, zzhnVar);
                            zzjlVar.add(zzhnVar.zzc);
                        }
                    }
                }
                return i8;
            default:
                return i8;
        }
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzhn zzhnVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzb2 = zzb(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzs.zzd(object)) {
            Object zzf = this.zzs.zzf(zzb2);
            this.zzs.zza(zzf, object);
            unsafe.putObject(t, j, zzf);
            object = zzf;
        }
        zzkf<?, ?> zzb3 = this.zzs.zzb(zzb2);
        Map<?, ?> zza2 = this.zzs.zza(object);
        int zza3 = zzhl.zza(bArr, i, zzhnVar);
        int i4 = zzhnVar.zza;
        if (i4 < 0 || i4 > i2 - zza3) {
            throw zzjk.zza();
        }
        int i5 = i4 + zza3;
        K k = zzb3.zzb;
        V v = zzb3.zzd;
        while (zza3 < i5) {
            int i6 = zza3 + 1;
            int i7 = bArr[zza3];
            if (i7 < 0) {
                i6 = zzhl.zza(i7, bArr, i6, zzhnVar);
                i7 = zzhnVar.zza;
            }
            int i8 = i6;
            int i9 = i7 >>> 3;
            int i10 = i7 & 7;
            if (i9 == 1) {
                if (i10 == zzb3.zza.zzb()) {
                    zza3 = zza(bArr, i8, i2, zzb3.zza, (Class<?>) null, zzhnVar);
                    k = (K) zzhnVar.zzc;
                } else {
                    zza3 = zzhl.zza(i7, bArr, i8, i2, zzhnVar);
                }
            } else {
                if (i9 == 2 && i10 == zzb3.zzc.zzb()) {
                    zza3 = zza(bArr, i8, i2, zzb3.zzc, zzb3.zzd.getClass(), zzhnVar);
                    v = zzhnVar.zzc;
                }
                zza3 = zzhl.zza(i7, bArr, i8, i2, zzhnVar);
            }
        }
        if (zza3 != i5) {
            throw zzjk.zzg();
        }
        zza2.put(k, v);
        return i5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0023. Please report as an issue. */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzhn zzhnVar) throws IOException {
        int zzb2;
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzhl.zzc(bArr, i)));
                    zzb2 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzhl.zzd(bArr, i)));
                    zzb2 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    zzb2 = zzhl.zzb(bArr, i, zzhnVar);
                    unsafe.putObject(t, j, Long.valueOf(zzhnVar.zzb));
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    zzb2 = zzhl.zza(bArr, i, zzhnVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzhnVar.zza));
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzhl.zzb(bArr, i)));
                    zzb2 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzhl.zza(bArr, i)));
                    zzb2 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    zzb2 = zzhl.zzb(bArr, i, zzhnVar);
                    unsafe.putObject(t, j, Boolean.valueOf(zzhnVar.zzb != 0));
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int zza2 = zzhl.zza(bArr, i, zzhnVar);
                    int i9 = zzhnVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(t, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zzmd.zza(bArr, zza2, zza2 + i9)) {
                            throw zzjk.zzh();
                        }
                        unsafe.putObject(t, j, new String(bArr, zza2, i9, zzjf.zza));
                        zza2 += i9;
                    }
                    unsafe.putInt(t, j2, i4);
                    return zza2;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    int zza3 = zzhl.zza(zza(i8), bArr, i, i2, zzhnVar);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, zzhnVar.zzc);
                    } else {
                        unsafe.putObject(t, j, zzjf.zza(object, zzhnVar.zzc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return zza3;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    zzb2 = zzhl.zze(bArr, i, zzhnVar);
                    unsafe.putObject(t, j, zzhnVar.zzc);
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zza4 = zzhl.zza(bArr, i, zzhnVar);
                    int i10 = zzhnVar.zza;
                    zzjg zzc = zzc(i8);
                    if (zzc == null || zzc.zza(i10)) {
                        unsafe.putObject(t, j, Integer.valueOf(i10));
                        zzb2 = zza4;
                        unsafe.putInt(t, j2, i4);
                        return zzb2;
                    }
                    zze(t).zza(i3, Long.valueOf(i10));
                    return zza4;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    zzb2 = zzhl.zza(bArr, i, zzhnVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzif.zze(zzhnVar.zza)));
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    zzb2 = zzhl.zzb(bArr, i, zzhnVar);
                    unsafe.putObject(t, j, Long.valueOf(zzif.zza(zzhnVar.zzb)));
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    zzb2 = zzhl.zza(zza(i8), bArr, i, i2, (i3 & (-8)) | 4, zzhnVar);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, zzhnVar.zzc);
                    } else {
                        unsafe.putObject(t, j, zzjf.zza(object2, zzhnVar.zzc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            default:
                return i;
        }
    }

    private final zzlc zza(int i) {
        int i2 = (i / 3) << 1;
        zzlc zzlcVar = (zzlc) this.zzd[i2];
        if (zzlcVar != null) {
            return zzlcVar;
        }
        zzlc<T> zza2 = zzky.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zza2;
        return zza2;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzjg zzc(int i) {
        return (zzjg) this.zzd[((i / 3) << 1) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0668, code lost:
    
        if (r1 == r2) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x066a, code lost:
    
        r31.putInt(r13, r1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0670, code lost:
    
        r1 = r8.zzm;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0674, code lost:
    
        if (r1 >= r8.zzn) goto L295;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0676, code lost:
    
        r4 = (com.google.android.gms.internal.vision.zzlx) r8.zza((java.lang.Object) r13, r8.zzl[r1], (int) r4, (com.google.android.gms.internal.vision.zzlu<UT, int>) r8.zzq);
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0686, code lost:
    
        if (r4 == null) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0688, code lost:
    
        r8.zzq.zzb((java.lang.Object) r13, (T) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x068d, code lost:
    
        if (r9 != 0) goto L216;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x068f, code lost:
    
        if (r0 != r6) goto L214;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0696, code lost:
    
        throw com.google.android.gms.internal.vision.zzjk.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x069b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0697, code lost:
    
        if (r0 > r6) goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0699, code lost:
    
        if (r3 != r9) goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x06a0, code lost:
    
        throw com.google.android.gms.internal.vision.zzjk.zzg();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:151:0x009e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:86:0x04d8. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:76:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x05e2  */
    /* JADX WARN: Type inference failed for: r13v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zza(T t, byte[] bArr, int i, int i2, int i3, zzhn zzhnVar) throws IOException {
        Unsafe unsafe;
        int i4;
        int i5;
        T t2;
        zzko<T> zzkoVar;
        zzlx zzlxVar;
        int i6;
        int i7;
        int i8;
        int i9;
        int zzg;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z;
        int i15;
        T t3;
        byte[] bArr2;
        zzhn zzhnVar2;
        int i16;
        int i17;
        int i18;
        T t4;
        Object obj;
        Object zza2;
        long j;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int zzb2;
        int i24;
        int i25;
        boolean z2;
        int i26;
        int i27;
        int i28;
        T t5;
        int i29;
        int i30;
        int i31;
        zzko<T> zzkoVar2 = this;
        T t6 = t;
        byte[] bArr3 = bArr;
        int i32 = i2;
        int i33 = i3;
        zzhn zzhnVar3 = zzhnVar;
        Unsafe unsafe2 = zzb;
        int i34 = i;
        int i35 = 0;
        int i36 = 0;
        int i37 = 0;
        int i38 = -1;
        int i39 = 1048575;
        while (true) {
            if (i34 < i32) {
                int i40 = i34 + 1;
                byte b = bArr3[i34];
                if (b < 0) {
                    i9 = zzhl.zza(b, bArr3, i40, zzhnVar3);
                    i8 = zzhnVar3.zza;
                } else {
                    i8 = b;
                    i9 = i40;
                }
                int i41 = i8 >>> 3;
                int i42 = i8 & 7;
                if (i41 > i38) {
                    zzg = zzkoVar2.zza(i41, i35 / 3);
                } else {
                    zzg = zzkoVar2.zzg(i41);
                }
                int i43 = zzg;
                if (i43 == -1) {
                    i10 = i9;
                    i11 = i37;
                    i12 = i41;
                    unsafe = unsafe2;
                    i13 = i33;
                    i14 = 0;
                    z = true;
                    i15 = i8;
                } else {
                    int[] iArr = zzkoVar2.zzc;
                    int i44 = iArr[i43 + 1];
                    int i45 = (i44 & 267386880) >>> 20;
                    int i46 = i9;
                    long j2 = i44 & 1048575;
                    if (i45 <= 17) {
                        int i47 = iArr[i43 + 2];
                        int i48 = 1 << (i47 >>> 20);
                        int i49 = i47 & 1048575;
                        if (i49 != i39) {
                            if (i39 != 1048575) {
                                long j3 = i39;
                                t5 = t;
                                j = j2;
                                unsafe2.putInt(t5, j3, i37);
                            } else {
                                t5 = t;
                                j = j2;
                            }
                            i37 = unsafe2.getInt(t5, i49);
                            i19 = i49;
                            t6 = t5;
                        } else {
                            t6 = t;
                            j = j2;
                            i19 = i39;
                        }
                        int i50 = i37;
                        switch (i45) {
                            case 0:
                                i25 = i8;
                                i21 = i19;
                                i22 = i46;
                                z2 = true;
                                i23 = i43;
                                long j4 = j;
                                if (i42 != 1) {
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    zzma.zza(t6, j4, zzhl.zzc(bArr3, i22));
                                    i26 = i22 + 8;
                                    i37 = i50 | i48;
                                    i33 = i3;
                                    i36 = i25;
                                    i34 = i26;
                                    i38 = i41;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                }
                            case 1:
                                i25 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j5 = j;
                                if (i42 != 5) {
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    zzma.zza((Object) t6, j5, zzhl.zzd(bArr3, i22));
                                    i26 = i22 + 4;
                                    i37 = i50 | i48;
                                    i33 = i3;
                                    i36 = i25;
                                    i34 = i26;
                                    i38 = i41;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                }
                            case 2:
                            case 3:
                                i20 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j6 = j;
                                if (i42 != 0) {
                                    i25 = i20;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    zzb2 = zzhl.zzb(bArr3, i22, zzhnVar3);
                                    i24 = i20;
                                    unsafe2.putLong(t, j6, zzhnVar3.zzb);
                                    i37 = i50 | i48;
                                    i38 = i41;
                                    i34 = zzb2;
                                    i36 = i24;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                    i33 = i3;
                                }
                            case 4:
                            case 11:
                                i20 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j7 = j;
                                if (i42 != 0) {
                                    i25 = i20;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    i34 = zzhl.zza(bArr3, i22, zzhnVar3);
                                    unsafe2.putInt(t6, j7, zzhnVar3.zza);
                                    i37 = i50 | i48;
                                    i33 = i3;
                                    i36 = i20;
                                    i38 = i41;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                }
                            case 5:
                            case 14:
                                int i51 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j8 = j;
                                if (i42 != 1) {
                                    z2 = true;
                                    i25 = i51;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    unsafe2.putLong(t, j8, zzhl.zzb(bArr3, i22));
                                    i34 = i22 + 8;
                                    i37 = i50 | i48;
                                    i33 = i3;
                                    i38 = i41;
                                    i36 = i51;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                }
                            case 6:
                            case 13:
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j9 = j;
                                if (i42 != 5) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    unsafe2.putInt(t6, j9, zzhl.zza(bArr3, i22));
                                    i34 = i22 + 4;
                                    int i52 = i50 | i48;
                                    i33 = i3;
                                    i36 = i27;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                    i37 = i52;
                                    i38 = i41;
                                }
                            case 7:
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j10 = j;
                                if (i42 != 0) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    int zzb3 = zzhl.zzb(bArr3, i22, zzhnVar3);
                                    zzma.zza(t6, j10, zzhnVar3.zzb != 0);
                                    int i53 = i50 | i48;
                                    i33 = i3;
                                    i36 = i27;
                                    i38 = i41;
                                    i35 = i23;
                                    i39 = i21;
                                    i37 = i53;
                                    i32 = i2;
                                    i34 = zzb3;
                                }
                            case 8:
                                i28 = i2;
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j11 = j;
                                if (i42 != 2) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    if ((536870912 & i44) == 0) {
                                        i34 = zzhl.zzc(bArr3, i22, zzhnVar3);
                                    } else {
                                        i34 = zzhl.zzd(bArr3, i22, zzhnVar3);
                                    }
                                    unsafe2.putObject(t6, j11, zzhnVar3.zzc);
                                    int i54 = i50 | i48;
                                    i33 = i3;
                                    i36 = i27;
                                    i35 = i23;
                                    i39 = i21;
                                    i37 = i54;
                                    i32 = i28;
                                    i38 = i41;
                                }
                            case 9:
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j12 = j;
                                if (i42 != 2) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    i28 = i2;
                                    i34 = zzhl.zza(zzkoVar2.zza(i23), bArr3, i22, i28, zzhnVar3);
                                    if ((i50 & i48) == 0) {
                                        unsafe2.putObject(t6, j12, zzhnVar3.zzc);
                                    } else {
                                        unsafe2.putObject(t6, j12, zzjf.zza(unsafe2.getObject(t6, j12), zzhnVar3.zzc));
                                    }
                                    int i542 = i50 | i48;
                                    i33 = i3;
                                    i36 = i27;
                                    i35 = i23;
                                    i39 = i21;
                                    i37 = i542;
                                    i32 = i28;
                                    i38 = i41;
                                }
                            case 10:
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j13 = j;
                                if (i42 != 2) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    i34 = zzhl.zze(bArr3, i22, zzhnVar3);
                                    unsafe2.putObject(t6, j13, zzhnVar3.zzc);
                                    int i522 = i50 | i48;
                                    i33 = i3;
                                    i36 = i27;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                    i37 = i522;
                                    i38 = i41;
                                }
                            case 12:
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j14 = j;
                                if (i42 != 0) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    i34 = zzhl.zza(bArr3, i22, zzhnVar3);
                                    int i55 = zzhnVar3.zza;
                                    zzjg zzc = zzkoVar2.zzc(i23);
                                    if (zzc == null || zzc.zza(i55)) {
                                        unsafe2.putInt(t6, j14, i55);
                                        int i5222 = i50 | i48;
                                        i33 = i3;
                                        i36 = i27;
                                        i35 = i23;
                                        i39 = i21;
                                        i32 = i2;
                                        i37 = i5222;
                                        i38 = i41;
                                    } else {
                                        zze(t).zza(i27, Long.valueOf(i55));
                                        i33 = i3;
                                        i36 = i27;
                                        i37 = i50;
                                        i38 = i41;
                                        i35 = i23;
                                        i39 = i21;
                                        i32 = i2;
                                    }
                                }
                                break;
                            case 15:
                                i27 = i8;
                                i21 = i19;
                                i22 = i46;
                                i23 = i43;
                                long j15 = j;
                                if (i42 != 0) {
                                    i25 = i27;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    i34 = zzhl.zza(bArr3, i22, zzhnVar3);
                                    unsafe2.putInt(t6, j15, zzif.zze(zzhnVar3.zza));
                                    int i52222 = i50 | i48;
                                    i33 = i3;
                                    i36 = i27;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                    i37 = i52222;
                                    i38 = i41;
                                }
                            case 16:
                                int i56 = i8;
                                i22 = i46;
                                long j16 = j;
                                if (i42 != 0) {
                                    i21 = i19;
                                    i23 = i43;
                                    i25 = i56;
                                    z2 = true;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    zzb2 = zzhl.zzb(bArr3, i22, zzhnVar3);
                                    i24 = i56;
                                    i21 = i19;
                                    i23 = i43;
                                    unsafe2.putLong(t, j16, zzif.zza(zzhnVar3.zzb));
                                    i37 = i50 | i48;
                                    i38 = i41;
                                    i34 = zzb2;
                                    i36 = i24;
                                    i35 = i23;
                                    i39 = i21;
                                    i32 = i2;
                                    i33 = i3;
                                }
                            case 17:
                                if (i42 != 3) {
                                    i22 = i46;
                                    i25 = i8;
                                    i21 = i19;
                                    z2 = true;
                                    i23 = i43;
                                    i13 = i3;
                                    i11 = i50;
                                    i12 = i41;
                                    unsafe = unsafe2;
                                    i10 = i22;
                                    i14 = i23;
                                    i39 = i21;
                                    i15 = i25;
                                    z = z2;
                                    break;
                                } else {
                                    int i57 = i8;
                                    i34 = zzhl.zza(zzkoVar2.zza(i43), bArr, i46, i2, (i41 << 3) | 4, zzhnVar);
                                    if ((i50 & i48) == 0) {
                                        unsafe2.putObject(t6, j, zzhnVar3.zzc);
                                    } else {
                                        long j17 = j;
                                        unsafe2.putObject(t6, j17, zzjf.zza(unsafe2.getObject(t6, j17), zzhnVar3.zzc));
                                    }
                                    i37 = i50 | i48;
                                    i38 = i41;
                                    i35 = i43;
                                    i36 = i57;
                                    i39 = i19;
                                    i32 = i2;
                                    i33 = i3;
                                }
                            default:
                                i25 = i8;
                                i21 = i19;
                                i22 = i46;
                                z2 = true;
                                i23 = i43;
                                i13 = i3;
                                i11 = i50;
                                i12 = i41;
                                unsafe = unsafe2;
                                i10 = i22;
                                i14 = i23;
                                i39 = i21;
                                i15 = i25;
                                z = z2;
                                break;
                        }
                    } else {
                        int i58 = i8;
                        t6 = t;
                        if (i45 != 27) {
                            i11 = i37;
                            i29 = i39;
                            if (i45 <= 49) {
                                z = true;
                                i30 = i58;
                                i12 = i41;
                                unsafe = unsafe2;
                                i13 = i3;
                                i14 = i43;
                                i34 = zza((zzko<T>) t, bArr, i46, i2, i58, i41, i42, i43, i44, i45, j2, zzhnVar);
                                if (i34 == i46) {
                                    i15 = i30;
                                    i10 = i34;
                                    i39 = i29;
                                } else {
                                    t6 = t;
                                    bArr3 = bArr;
                                    i36 = i30;
                                    i32 = i2;
                                    zzhnVar3 = zzhnVar;
                                    i33 = i13;
                                    i35 = i14;
                                    i38 = i12;
                                    i37 = i11;
                                    i39 = i29;
                                    unsafe2 = unsafe;
                                    zzkoVar2 = this;
                                }
                            } else {
                                i13 = i3;
                                i30 = i58;
                                i12 = i41;
                                unsafe = unsafe2;
                                i14 = i43;
                                i31 = i46;
                                z = true;
                                if (i45 != 50) {
                                    i34 = zza((zzko<T>) t, bArr, i31, i2, i30, i12, i42, i44, i45, j2, i14, zzhnVar);
                                    if (i34 != i31) {
                                        zzkoVar2 = this;
                                        t6 = t;
                                        bArr3 = bArr;
                                        i32 = i2;
                                        i36 = i30;
                                        i38 = i12;
                                        i35 = i14;
                                        i37 = i11;
                                        i39 = i29;
                                        unsafe2 = unsafe;
                                        i33 = i13;
                                        zzhnVar3 = zzhnVar;
                                    }
                                } else if (i42 == 2) {
                                    i34 = zza((zzko<T>) t, bArr, i31, i2, i14, j2, zzhnVar);
                                    if (i34 != i31) {
                                        t6 = t;
                                        bArr3 = bArr;
                                        i36 = i30;
                                        i32 = i2;
                                        zzhnVar3 = zzhnVar;
                                        i33 = i13;
                                        i35 = i14;
                                        i38 = i12;
                                        i37 = i11;
                                        i39 = i29;
                                        unsafe2 = unsafe;
                                        zzkoVar2 = this;
                                    }
                                }
                                i15 = i30;
                                i10 = i34;
                                i39 = i29;
                            }
                        } else if (i42 == 2) {
                            zzjl zzjlVar = (zzjl) unsafe2.getObject(t6, j2);
                            if (!zzjlVar.zza()) {
                                int size = zzjlVar.size();
                                zzjlVar = zzjlVar.zza(size == 0 ? 10 : size << 1);
                                unsafe2.putObject(t6, j2, zzjlVar);
                            }
                            i34 = zzhl.zza(zzkoVar2.zza(i43), i58, bArr, i46, i2, zzjlVar, zzhnVar);
                            i38 = i41;
                            i36 = i58;
                            i35 = i43;
                            i37 = i37;
                            i39 = i39;
                            i32 = i2;
                            i33 = i3;
                        } else {
                            i11 = i37;
                            i29 = i39;
                            i13 = i3;
                            i30 = i58;
                            i12 = i41;
                            unsafe = unsafe2;
                            i14 = i43;
                            i31 = i46;
                            z = true;
                        }
                        i15 = i30;
                        i10 = i31;
                        i39 = i29;
                    }
                }
                if (i15 != i13 || i13 == 0) {
                    int i59 = i13;
                    if (this.zzh) {
                        zzhnVar2 = zzhnVar;
                        if (zzhnVar2.zzd != zzio.zzb()) {
                            zzkk zzkkVar = this.zzg;
                            zzlu<?, ?> zzluVar = this.zzq;
                            i17 = i12;
                            zzjb.zze zza3 = zzhnVar2.zzd.zza(zzkkVar, i17);
                            if (zza3 == null) {
                                i34 = zzhl.zza(i15, bArr, i10, i2, zze(t), zzhnVar);
                                t4 = t;
                                bArr2 = bArr;
                                i16 = i39;
                                i18 = i2;
                            } else {
                                ?? r13 = t;
                                zzjb.zzc zzcVar = (zzjb.zzc) r13;
                                zzcVar.zza();
                                zziu<zzjb.zzf> zziuVar = zzcVar.zzc;
                                boolean z3 = zza3.zzd.zzd;
                                if (zza3.zzd.zzc == zzml.zzn) {
                                    bArr2 = bArr;
                                    i10 = zzhl.zza(bArr2, i10, zzhnVar2);
                                    zzjh zzjhVar = null;
                                    if (zzjhVar.zza(zzhnVar2.zza) == null) {
                                        zzlx zzlxVar2 = zzcVar.zzb;
                                        if (zzlxVar2 == zzlx.zza()) {
                                            zzlxVar2 = zzlx.zzb();
                                            zzcVar.zzb = zzlxVar2;
                                        }
                                        zzle.zza(i17, zzhnVar2.zza, zzlxVar2, (zzlu<UT, zzlx>) zzluVar);
                                        i16 = i39;
                                        i18 = i2;
                                        i34 = i10;
                                        t4 = r13;
                                    } else {
                                        obj = Integer.valueOf(zzhnVar2.zza);
                                    }
                                } else {
                                    bArr2 = bArr;
                                    obj = null;
                                    switch (zzhk.zza[zza3.zzd.zzc.ordinal()]) {
                                        case 1:
                                            i16 = i39;
                                            i18 = i2;
                                            obj = Double.valueOf(zzhl.zzc(bArr2, i10));
                                            i10 += 8;
                                            if (!zza3.zzd.zzd) {
                                                zziuVar.zzb(zza3.zzd, obj);
                                            } else {
                                                int i60 = zzhk.zza[zza3.zzd.zzc.ordinal()];
                                                if ((i60 == 17 || i60 == 18) && (zza2 = zziuVar.zza((zziu<zzjb.zzf>) zza3.zzd)) != null) {
                                                    obj = zzjf.zza(zza2, obj);
                                                }
                                                zziuVar.zza((zziu<zzjb.zzf>) zza3.zzd, obj);
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 2:
                                            i16 = i39;
                                            i18 = i2;
                                            obj = Float.valueOf(zzhl.zzd(bArr2, i10));
                                            i10 += 4;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 3:
                                        case 4:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zzb(bArr2, i10, zzhnVar2);
                                            obj = Long.valueOf(zzhnVar2.zzb);
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 5:
                                        case 6:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zza(bArr2, i10, zzhnVar2);
                                            obj = Integer.valueOf(zzhnVar2.zza);
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 7:
                                        case 8:
                                            i16 = i39;
                                            i18 = i2;
                                            obj = Long.valueOf(zzhl.zzb(bArr2, i10));
                                            i10 += 8;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 9:
                                        case 10:
                                            i16 = i39;
                                            i18 = i2;
                                            obj = Integer.valueOf(zzhl.zza(bArr2, i10));
                                            i10 += 4;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 11:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zzb(bArr2, i10, zzhnVar2);
                                            obj = Boolean.valueOf(zzhnVar2.zzb != 0 ? z : false);
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 12:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zza(bArr2, i10, zzhnVar2);
                                            obj = Integer.valueOf(zzif.zze(zzhnVar2.zza));
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 13:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zzb(bArr2, i10, zzhnVar2);
                                            obj = Long.valueOf(zzif.zza(zzhnVar2.zzb));
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 14:
                                            throw new IllegalStateException("Shouldn't reach here.");
                                        case 15:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zze(bArr2, i10, zzhnVar2);
                                            obj = zzhnVar2.zzc;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 16:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zzc(bArr2, i10, zzhnVar2);
                                            obj = zzhnVar2.zzc;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 17:
                                            i16 = i39;
                                            i18 = i2;
                                            i10 = zzhl.zza(zzky.zza().zza((Class) zza3.zzc.getClass()), bArr, i10, i2, (i17 << 3) | 4, zzhnVar);
                                            obj = zzhnVar2.zzc;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                        case 18:
                                            i10 = zzhl.zza(zzky.zza().zza((Class) zza3.zzc.getClass()), bArr2, i10, i2, zzhnVar2);
                                            obj = zzhnVar2.zzc;
                                            i16 = i39;
                                            i18 = i2;
                                            if (!zza3.zzd.zzd) {
                                            }
                                            i34 = i10;
                                            t4 = r13;
                                            break;
                                    }
                                }
                                i16 = i39;
                                i18 = i2;
                                if (!zza3.zzd.zzd) {
                                }
                                i34 = i10;
                                t4 = r13;
                            }
                            i36 = i15;
                            i38 = i17;
                            t6 = t4;
                            bArr3 = bArr2;
                            i35 = i14;
                            i37 = i11;
                            i32 = i18;
                            zzkoVar2 = this;
                            i33 = i59;
                            zzhnVar3 = zzhnVar2;
                            unsafe2 = unsafe;
                            i39 = i16;
                        } else {
                            t3 = t;
                            bArr2 = bArr;
                        }
                    } else {
                        t3 = t;
                        bArr2 = bArr;
                        zzhnVar2 = zzhnVar;
                    }
                    i16 = i39;
                    i17 = i12;
                    i18 = i2;
                    i34 = zzhl.zza(i15, bArr, i10, i2, zze(t), zzhnVar);
                    t4 = t3;
                    i36 = i15;
                    i38 = i17;
                    t6 = t4;
                    bArr3 = bArr2;
                    i35 = i14;
                    i37 = i11;
                    i32 = i18;
                    zzkoVar2 = this;
                    i33 = i59;
                    zzhnVar3 = zzhnVar2;
                    unsafe2 = unsafe;
                    i39 = i16;
                } else {
                    zzkoVar = this;
                    t2 = t;
                    i34 = i10;
                    i6 = i39;
                    i36 = i15;
                    i4 = i13;
                    i37 = i11;
                    i7 = 1048575;
                    zzlxVar = null;
                    i5 = i2;
                }
            } else {
                int i61 = i39;
                unsafe = unsafe2;
                i4 = i33;
                i5 = i32;
                t2 = t6;
                zzkoVar = zzkoVar2;
                zzlxVar = null;
                i6 = i61;
                i7 = 1048575;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:134:0x02d1, code lost:
    
        if (r0 == r5) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02d5, code lost:
    
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r18;
        r10 = r20;
        r1 = r25;
        r6 = r27;
        r7 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x033f, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x031a, code lost:
    
        if (r0 == r15) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x033d, code lost:
    
        if (r0 == r15) goto L115;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0094. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [int] */
    @Override // com.google.android.gms.internal.vision.zzlc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t, byte[] bArr, int i, int i2, zzhn zzhnVar) throws IOException {
        byte b;
        int i3;
        int zzg;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        Unsafe unsafe2;
        int i13;
        int i14;
        Unsafe unsafe3;
        Unsafe unsafe4;
        zzko<T> zzkoVar = this;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i15 = i2;
        zzhn zzhnVar2 = zzhnVar;
        if (zzkoVar.zzj) {
            Unsafe unsafe5 = zzb;
            int i16 = -1;
            int i17 = 1048575;
            int i18 = i;
            int i19 = 1048575;
            int i20 = -1;
            int i21 = 0;
            int i22 = 0;
            while (i18 < i15) {
                int i23 = i18 + 1;
                byte b2 = bArr2[i18];
                if (b2 < 0) {
                    i3 = zzhl.zza(b2, bArr2, i23, zzhnVar2);
                    b = zzhnVar2.zza;
                } else {
                    b = b2;
                    i3 = i23;
                }
                int i24 = b >>> 3;
                int i25 = b & 7;
                if (i24 > i20) {
                    zzg = zzkoVar.zza(i24, i21 / 3);
                } else {
                    zzg = zzkoVar.zzg(i24);
                }
                int i26 = zzg;
                if (i26 == i16) {
                    i4 = i3;
                    i5 = i24;
                    unsafe = unsafe5;
                    i6 = i16;
                    i7 = 0;
                } else {
                    int[] iArr = zzkoVar.zzc;
                    int i27 = iArr[i26 + 1];
                    int i28 = (i27 & 267386880) >>> 20;
                    Unsafe unsafe6 = unsafe5;
                    long j = i27 & i17;
                    if (i28 <= 17) {
                        int i29 = iArr[i26 + 2];
                        int i30 = 1 << (i29 >>> 20);
                        int i31 = i29 & 1048575;
                        if (i31 != i19) {
                            if (i19 != 1048575) {
                                long j2 = i19;
                                unsafe4 = unsafe6;
                                unsafe4.putInt(t2, j2, i22);
                            } else {
                                unsafe4 = unsafe6;
                            }
                            if (i31 != 1048575) {
                                i22 = unsafe4.getInt(t2, i31);
                            }
                            unsafe2 = unsafe4;
                            i19 = i31;
                        } else {
                            unsafe2 = unsafe6;
                        }
                        switch (i28) {
                            case 0:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i14 = i3;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 1) {
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    zzma.zza(t2, j, zzhl.zzc(bArr2, i14));
                                    i18 = i14 + 8;
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 1:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i14 = i3;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 5) {
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    zzma.zza((Object) t2, j, zzhl.zzd(bArr2, i14));
                                    i18 = i14 + 4;
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 2:
                            case 3:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i14 = i3;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 0) {
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    int zzb2 = zzhl.zzb(bArr2, i14, zzhnVar2);
                                    unsafe3.putLong(t, j, zzhnVar2.zzb);
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i18 = zzb2;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 4:
                            case 11:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i14 = i3;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 0) {
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    i18 = zzhl.zza(bArr2, i14, zzhnVar2);
                                    unsafe3.putInt(t2, j, zzhnVar2.zza);
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 5:
                            case 14:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 1) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    unsafe3.putLong(t, j, zzhl.zzb(bArr2, i3));
                                    i18 = i3 + 8;
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 6:
                            case 13:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 5) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    unsafe3.putInt(t2, j, zzhl.zza(bArr2, i3));
                                    i18 = i3 + 4;
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 7:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 0) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    i18 = zzhl.zzb(bArr2, i3, zzhnVar2);
                                    zzma.zza(t2, j, zzhnVar2.zzb != 0);
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 8:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 2) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    if ((i27 & 536870912) == 0) {
                                        i18 = zzhl.zzc(bArr2, i3, zzhnVar2);
                                    } else {
                                        i18 = zzhl.zzd(bArr2, i3, zzhnVar2);
                                    }
                                    unsafe3.putObject(t2, j, zzhnVar2.zzc);
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 9:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 2) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    i18 = zzhl.zza(zzkoVar.zza(i13), bArr2, i3, i15, zzhnVar2);
                                    Object object = unsafe3.getObject(t2, j);
                                    if (object == null) {
                                        unsafe3.putObject(t2, j, zzhnVar2.zzc);
                                    } else {
                                        unsafe3.putObject(t2, j, zzjf.zza(object, zzhnVar2.zzc));
                                    }
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 10:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 2) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    i18 = zzhl.zze(bArr2, i3, zzhnVar2);
                                    unsafe3.putObject(t2, j, zzhnVar2.zzc);
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 12:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 0) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    i18 = zzhl.zza(bArr2, i3, zzhnVar2);
                                    unsafe3.putInt(t2, j, zzhnVar2.zza);
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 15:
                                i5 = i24;
                                i9 = 1048575;
                                i13 = i26;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                if (i25 != 0) {
                                    i14 = i3;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    i18 = zzhl.zza(bArr2, i3, zzhnVar2);
                                    unsafe3.putInt(t2, j, zzif.zze(zzhnVar2.zza));
                                    i22 |= i30;
                                    unsafe5 = unsafe3;
                                    i21 = i13;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            case 16:
                                if (i25 != 0) {
                                    i5 = i24;
                                    i8 = i19;
                                    unsafe3 = unsafe2;
                                    i14 = i3;
                                    i13 = i26;
                                    i4 = i14;
                                    unsafe = unsafe3;
                                    i7 = i13;
                                    i19 = i8;
                                    i6 = -1;
                                    break;
                                } else {
                                    int zzb3 = zzhl.zzb(bArr2, i3, zzhnVar2);
                                    i8 = i19;
                                    i5 = i24;
                                    i9 = 1048575;
                                    unsafe2.putLong(t, j, zzif.zza(zzhnVar2.zzb));
                                    i22 |= i30;
                                    unsafe5 = unsafe2;
                                    i21 = i26;
                                    i18 = zzb3;
                                    i19 = i8;
                                    i20 = i5;
                                    i17 = i9;
                                    i16 = -1;
                                    break;
                                }
                            default:
                                i5 = i24;
                                i13 = i26;
                                i14 = i3;
                                i8 = i19;
                                unsafe3 = unsafe2;
                                i4 = i14;
                                unsafe = unsafe3;
                                i7 = i13;
                                i19 = i8;
                                i6 = -1;
                                break;
                        }
                    } else {
                        i5 = i24;
                        int i32 = i3;
                        i8 = i19;
                        i9 = 1048575;
                        if (i28 != 27) {
                            i7 = i26;
                            if (i28 <= 49) {
                                i11 = i22;
                                i12 = i8;
                                unsafe = unsafe6;
                                i6 = -1;
                                i18 = zza((zzko<T>) t, bArr, i32, i2, b, i5, i25, i7, i27, i28, j, zzhnVar);
                            } else {
                                i10 = i32;
                                i11 = i22;
                                unsafe = unsafe6;
                                i12 = i8;
                                i6 = -1;
                                if (i28 != 50) {
                                    i18 = zza((zzko<T>) t, bArr, i10, i2, b, i5, i25, i27, i28, j, i7, zzhnVar);
                                } else if (i25 == 2) {
                                    i18 = zza((zzko<T>) t, bArr, i10, i2, i7, j, zzhnVar);
                                }
                            }
                            unsafe5 = unsafe;
                            i17 = 1048575;
                        } else if (i25 == 2) {
                            zzjl zzjlVar = (zzjl) unsafe6.getObject(t2, j);
                            if (!zzjlVar.zza()) {
                                int size = zzjlVar.size();
                                zzjlVar = zzjlVar.zza(size == 0 ? 10 : size << 1);
                                unsafe6.putObject(t2, j, zzjlVar);
                            }
                            i18 = zzhl.zza(zzkoVar.zza(i26), b, bArr, i32, i2, zzjlVar, zzhnVar);
                            unsafe5 = unsafe6;
                            i22 = i22;
                            i21 = i26;
                            i19 = i8;
                            i20 = i5;
                            i17 = i9;
                            i16 = -1;
                        } else {
                            i7 = i26;
                            i10 = i32;
                            i11 = i22;
                            unsafe = unsafe6;
                            i12 = i8;
                            i6 = -1;
                        }
                        i4 = i10;
                        i22 = i11;
                        i19 = i12;
                    }
                }
                i18 = zzhl.zza(b, bArr, i4, i2, zze(t), zzhnVar);
                zzkoVar = this;
                t2 = t;
                bArr2 = bArr;
                i15 = i2;
                zzhnVar2 = zzhnVar;
                i21 = i7;
                i16 = i6;
                i20 = i5;
                unsafe5 = unsafe;
                i17 = 1048575;
            }
            int i33 = i22;
            Unsafe unsafe7 = unsafe5;
            if (i19 != i17) {
                unsafe7.putInt(t, i19, i33);
            }
            if (i18 != i2) {
                throw zzjk.zzg();
            }
            return;
        }
        zza((zzko<T>) t, bArr, i, i2, 0, zzhnVar);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzc(T t) {
        int i;
        int i2 = this.zzm;
        while (true) {
            i = this.zzn;
            if (i2 >= i) {
                break;
            }
            long zzd = zzd(this.zzl[i2]) & 1048575;
            Object zzf = zzma.zzf(t, zzd);
            if (zzf != null) {
                zzma.zza(t, zzd, this.zzs.zze(zzf));
            }
            i2++;
        }
        int length = this.zzl.length;
        while (i < length) {
            this.zzp.zzb(t, this.zzl[i]);
            i++;
        }
        this.zzq.zzd(t);
        if (this.zzh) {
            this.zzr.zzc(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzlu<UT, UB> zzluVar) {
        zzjg zzc;
        int i2 = this.zzc[i];
        Object zzf = zzma.zzf(obj, zzd(i) & 1048575);
        return (zzf == null || (zzc = zzc(i)) == null) ? ub : (UB) zza(i, i2, this.zzs.zza(zzf), zzc, (zzjg) ub, (zzlu<UT, zzjg>) zzluVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzjg zzjgVar, UB ub, zzlu<UT, UB> zzluVar) {
        zzkf<?, ?> zzb2 = this.zzs.zzb(zzb(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzjgVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzluVar.zza();
                }
                zzib zzc = zzht.zzc(zzkc.zza(zzb2, next.getKey(), next.getValue()));
                try {
                    zzkc.zza(zzc.zzb(), zzb2, next.getKey(), next.getValue());
                    zzluVar.zza((zzlu<UT, UB>) ub, i2, zzc.zza());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.gms.internal.vision.zzlc] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.vision.zzlc] */
    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zzd(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.zzm) {
                return !this.zzh || this.zzr.zza(t).zzf();
            }
            int i6 = this.zzl[i5];
            int i7 = this.zzc[i6];
            int zzd = zzd(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if (((268435456 & zzd) != 0) && !zza((zzko<T>) t, i6, i, i2, i10)) {
                return false;
            }
            int i11 = (267386880 & zzd) >>> 20;
            if (i11 == 9 || i11 == 17) {
                if (zza((zzko<T>) t, i6, i, i2, i10) && !zza(t, zzd, zza(i6))) {
                    return false;
                }
            } else {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (zza((zzko<T>) t, i7, i6) && !zza(t, zzd, zza(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzc = this.zzs.zzc(zzma.zzf(t, zzd & 1048575));
                            if (!zzc.isEmpty()) {
                                if (this.zzs.zzb(zzb(i6)).zzc.zza() == zzmo.MESSAGE) {
                                    ?? r1 = 0;
                                    Iterator<?> it = zzc.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        r1 = r1;
                                        if (r1 == 0) {
                                            r1 = zzky.zza().zza((Class) next.getClass());
                                        }
                                        if (!r1.zzd(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzma.zzf(t, zzd & 1048575);
                if (!list.isEmpty()) {
                    ?? zza2 = zza(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        }
                        if (!zza2.zzd(list.get(i12))) {
                            z = false;
                            break;
                        }
                        i12++;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzlc zzlcVar) {
        return zzlcVar.zzd(zzma.zzf(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzmr zzmrVar) throws IOException {
        if (obj instanceof String) {
            zzmrVar.zza(i, (String) obj);
        } else {
            zzmrVar.zza(i, (zzht) obj);
        }
    }

    private final void zza(Object obj, int i, zzld zzldVar) throws IOException {
        if (zzf(i)) {
            zzma.zza(obj, i & 1048575, zzldVar.zzm());
        } else if (this.zzi) {
            zzma.zza(obj, i & 1048575, zzldVar.zzl());
        } else {
            zzma.zza(obj, i & 1048575, zzldVar.zzn());
        }
    }

    private final int zzd(int i) {
        return this.zzc[i + 1];
    }

    private final int zze(int i) {
        return this.zzc[i + 2];
    }

    private static <T> double zzb(T t, long j) {
        return ((Double) zzma.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzma.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzma.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzma.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzma.zzf(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((zzko<T>) t, i) == zza((zzko<T>) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zza((zzko<T>) t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zza(T t, int i) {
        int zze = zze(i);
        long j = zze & 1048575;
        if (j != 1048575) {
            return (zzma.zza(t, j) & (1 << (zze >>> 20))) != 0;
        }
        int zzd = zzd(i);
        long j2 = zzd & 1048575;
        switch ((zzd & 267386880) >>> 20) {
            case 0:
                return zzma.zze(t, j2) != 0.0d;
            case 1:
                return zzma.zzd(t, j2) != 0.0f;
            case 2:
                return zzma.zzb(t, j2) != 0;
            case 3:
                return zzma.zzb(t, j2) != 0;
            case 4:
                return zzma.zza(t, j2) != 0;
            case 5:
                return zzma.zzb(t, j2) != 0;
            case 6:
                return zzma.zza(t, j2) != 0;
            case 7:
                return zzma.zzc(t, j2);
            case 8:
                Object zzf = zzma.zzf(t, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzht) {
                    return !zzht.zza.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzma.zzf(t, j2) != null;
            case 10:
                return !zzht.zza.equals(zzma.zzf(t, j2));
            case 11:
                return zzma.zza(t, j2) != 0;
            case 12:
                return zzma.zza(t, j2) != 0;
            case 13:
                return zzma.zza(t, j2) != 0;
            case 14:
                return zzma.zzb(t, j2) != 0;
            case 15:
                return zzma.zza(t, j2) != 0;
            case 16:
                return zzma.zzb(t, j2) != 0;
            case 17:
                return zzma.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final void zzb(T t, int i) {
        int zze = zze(i);
        long j = 1048575 & zze;
        if (j == 1048575) {
            return;
        }
        zzma.zza((Object) t, j, (1 << (zze >>> 20)) | zzma.zza(t, j));
    }

    private final boolean zza(T t, int i, int i2) {
        return zzma.zza(t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzma.zza((Object) t, zze(i2) & 1048575, i);
    }

    private final int zzg(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, 0);
    }

    private final int zza(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, i2);
    }

    private final int zzb(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
