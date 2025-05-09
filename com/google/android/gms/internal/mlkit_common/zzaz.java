package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzaz extends zzar {
    static final zzar zza = new zzaz(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzaz(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v2, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object[]] */
    public static zzaz zzg(int i, Object[] objArr, zzaq zzaqVar) {
        short[] sArr;
        char c;
        char c2;
        byte[] bArr;
        int i2 = i;
        Object[] objArr2 = objArr;
        if (i2 == 0) {
            return (zzaz) zza;
        }
        Object obj = null;
        int i3 = 1;
        if (i2 == 1) {
            Object obj2 = objArr2[0];
            obj2.getClass();
            Object obj3 = objArr2[1];
            obj3.getClass();
            zzaf.zza(obj2, obj3);
            return new zzaz(null, objArr2, 1);
        }
        zzac.zzb(i2, objArr2.length >> 1, FirebaseAnalytics.Param.INDEX);
        int max = Math.max(i2, 2);
        int i4 = 1073741824;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            i4 = highestOneBit + highestOneBit;
            while (i4 * 0.7d < max) {
                i4 += i4;
            }
        } else if (max >= 1073741824) {
            throw new IllegalArgumentException("collection too large");
        }
        if (i2 == 1) {
            Object obj4 = objArr2[0];
            obj4.getClass();
            Object obj5 = objArr2[1];
            obj5.getClass();
            zzaf.zza(obj4, obj5);
            c = 1;
            c2 = 2;
        } else {
            int i5 = i4 - 1;
            char c3 = 65535;
            if (i4 <= 128) {
                byte[] bArr2 = new byte[i4];
                Arrays.fill(bArr2, (byte) -1);
                int i6 = 0;
                int i7 = 0;
                while (i6 < i2) {
                    int i8 = i6 + i6;
                    int i9 = i7 + i7;
                    Object obj6 = objArr2[i8];
                    obj6.getClass();
                    Object obj7 = objArr2[i8 ^ i3];
                    obj7.getClass();
                    zzaf.zza(obj6, obj7);
                    int zza2 = zzah.zza(obj6.hashCode());
                    while (true) {
                        int i10 = zza2 & i5;
                        int i11 = bArr2[i10] & 255;
                        if (i11 != 255) {
                            if (obj6.equals(objArr2[i11])) {
                                int i12 = i11 ^ 1;
                                Object obj8 = objArr2[i12];
                                obj8.getClass();
                                zzap zzapVar = new zzap(obj6, obj7, obj8);
                                objArr2[i12] = obj7;
                                obj = zzapVar;
                                break;
                            }
                            zza2 = i10 + 1;
                        } else {
                            bArr2[i10] = (byte) i9;
                            if (i7 < i6) {
                                objArr2[i9] = obj6;
                                objArr2[i9 ^ 1] = obj7;
                            }
                            i7++;
                        }
                    }
                    i6++;
                    i3 = 1;
                }
                if (i7 == i2) {
                    bArr = bArr2;
                } else {
                    bArr = new Object[]{bArr2, Integer.valueOf(i7), obj};
                    c2 = 2;
                    c = 1;
                    obj = bArr;
                }
            } else if (i4 <= 32768) {
                sArr = new short[i4];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = i14 + i14;
                    int i16 = i13 + i13;
                    Object obj9 = objArr2[i15];
                    obj9.getClass();
                    Object obj10 = objArr2[i15 ^ 1];
                    obj10.getClass();
                    zzaf.zza(obj9, obj10);
                    int zza3 = zzah.zza(obj9.hashCode());
                    while (true) {
                        int i17 = zza3 & i5;
                        char c4 = (char) sArr[i17];
                        if (c4 != 65535) {
                            if (obj9.equals(objArr2[c4])) {
                                int i18 = c4 ^ 1;
                                Object obj11 = objArr2[i18];
                                obj11.getClass();
                                zzap zzapVar2 = new zzap(obj9, obj10, obj11);
                                objArr2[i18] = obj10;
                                obj = zzapVar2;
                                break;
                            }
                            zza3 = i17 + 1;
                        } else {
                            sArr[i17] = (short) i16;
                            if (i13 < i14) {
                                objArr2[i16] = obj9;
                                objArr2[i16 ^ 1] = obj10;
                            }
                            i13++;
                        }
                    }
                }
                if (i13 != i2) {
                    c2 = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i13), obj};
                    c = 1;
                }
                bArr = sArr;
            } else {
                int i19 = 1;
                sArr = new int[i4];
                Arrays.fill((int[]) sArr, -1);
                int i20 = 0;
                int i21 = 0;
                while (i20 < i2) {
                    int i22 = i20 + i20;
                    int i23 = i21 + i21;
                    Object obj12 = objArr2[i22];
                    obj12.getClass();
                    Object obj13 = objArr2[i22 ^ i19];
                    obj13.getClass();
                    zzaf.zza(obj12, obj13);
                    int zza4 = zzah.zza(obj12.hashCode());
                    while (true) {
                        int i24 = zza4 & i5;
                        ?? r15 = sArr[i24];
                        if (r15 != c3) {
                            if (obj12.equals(objArr2[r15])) {
                                int i25 = r15 ^ 1;
                                Object obj14 = objArr2[i25];
                                obj14.getClass();
                                zzap zzapVar3 = new zzap(obj12, obj13, obj14);
                                objArr2[i25] = obj13;
                                obj = zzapVar3;
                                break;
                            }
                            zza4 = i24 + 1;
                            c3 = 65535;
                        } else {
                            sArr[i24] = i23;
                            if (i21 < i20) {
                                objArr2[i23] = obj12;
                                objArr2[i23 ^ 1] = obj13;
                            }
                            i21++;
                        }
                    }
                    i20++;
                    i19 = 1;
                    c3 = 65535;
                }
                if (i21 != i2) {
                    c = 1;
                    c2 = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i21), obj};
                }
                bArr = sArr;
            }
            c2 = 2;
            c = 1;
            obj = bArr;
        }
        boolean z = obj instanceof Object[];
        Object obj15 = obj;
        if (z) {
            Object[] objArr3 = (Object[]) obj;
            zzap zzapVar4 = (zzap) objArr3[c2];
            if (zzaqVar != null) {
                zzaqVar.zzc = zzapVar4;
                Object obj16 = objArr3[0];
                int intValue = ((Integer) objArr3[c]).intValue();
                objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
                obj15 = obj16;
                i2 = intValue;
            } else {
                throw zzapVar4.zza();
            }
        }
        return new zzaz(obj15, objArr2, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x009f A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_common.zzar, java.util.Map
    @CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        Object obj3 = this.zzc;
        Object[] objArr = this.zzb;
        int i = this.zzd;
        if (obj != null) {
            if (i == 1) {
                Object obj4 = objArr[0];
                obj4.getClass();
                if (obj4.equals(obj)) {
                    obj2 = objArr[1];
                    obj2.getClass();
                }
            } else if (obj3 != null) {
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    int length = bArr.length - 1;
                    int zza2 = zzah.zza(obj.hashCode());
                    while (true) {
                        int i2 = zza2 & length;
                        int i3 = bArr[i2] & 255;
                        if (i3 == 255) {
                            break;
                        }
                        if (obj.equals(objArr[i3])) {
                            obj2 = objArr[i3 ^ 1];
                            break;
                        }
                        zza2 = i2 + 1;
                    }
                } else if (obj3 instanceof short[]) {
                    short[] sArr = (short[]) obj3;
                    int length2 = sArr.length - 1;
                    int zza3 = zzah.zza(obj.hashCode());
                    while (true) {
                        int i4 = zza3 & length2;
                        char c = (char) sArr[i4];
                        if (c == 65535) {
                            break;
                        }
                        if (obj.equals(objArr[c])) {
                            obj2 = objArr[c ^ 1];
                            break;
                        }
                        zza3 = i4 + 1;
                    }
                } else {
                    int[] iArr = (int[]) obj3;
                    int length3 = iArr.length - 1;
                    int zza4 = zzah.zza(obj.hashCode());
                    while (true) {
                        int i5 = zza4 & length3;
                        int i6 = iArr[i5];
                        if (i6 == -1) {
                            break;
                        }
                        if (obj.equals(objArr[i6])) {
                            obj2 = objArr[i6 ^ 1];
                            break;
                        }
                        zza4 = i5 + 1;
                    }
                }
            }
            if (obj2 != null) {
                return null;
            }
            return obj2;
        }
        obj2 = null;
        if (obj2 != null) {
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzar
    final zzak zza() {
        return new zzay(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzar
    final zzas zzd() {
        return new zzaw(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzar
    final zzas zze() {
        return new zzax(this, new zzay(this.zzb, 0, this.zzd));
    }
}
