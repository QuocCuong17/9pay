package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Map;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzes<K, V> extends zzef<K, V> {
    static final zzef<Object, Object> zza = new zzes(null, new Object[0], 0);
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0056, code lost:
    
        r2[r6] = (byte) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0097, code lost:
    
        r2[r6] = (short) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00cc, code lost:
    
        r2[r7] = r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [int[]] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <K, V> zzes<K, V> zza(int i, Object[] objArr) {
        byte[] bArr;
        if (i == 0) {
            return (zzes) zza;
        }
        Object obj = null;
        int i2 = 0;
        if (i == 1) {
            zzdq.zza(objArr[0], objArr[1]);
            return new zzes<>(null, objArr, 1);
        }
        zzde.zzb(i, objArr.length >> 1);
        int zza2 = zzej.zza(i);
        if (i == 1) {
            zzdq.zza(objArr[0], objArr[1]);
        } else {
            int i3 = zza2 - 1;
            if (zza2 <= 128) {
                bArr = new byte[zza2];
                Arrays.fill(bArr, (byte) -1);
                while (i2 < i) {
                    int i4 = i2 * 2;
                    Object obj2 = objArr[i4];
                    Object obj3 = objArr[i4 ^ 1];
                    zzdq.zza(obj2, obj3);
                    int zza3 = zzec.zza(obj2.hashCode());
                    while (true) {
                        int i5 = zza3 & i3;
                        int i6 = bArr[i5] & 255;
                        if (i6 == 255) {
                            break;
                        }
                        if (objArr[i6].equals(obj2)) {
                            throw zza(obj2, obj3, objArr, i6);
                        }
                        zza3 = i5 + 1;
                    }
                }
            } else if (zza2 <= 32768) {
                bArr = new short[zza2];
                Arrays.fill(bArr, (short) -1);
                while (i2 < i) {
                    int i7 = i2 * 2;
                    Object obj4 = objArr[i7];
                    Object obj5 = objArr[i7 ^ 1];
                    zzdq.zza(obj4, obj5);
                    int zza4 = zzec.zza(obj4.hashCode());
                    while (true) {
                        int i8 = zza4 & i3;
                        int i9 = bArr[i8] & UShort.MAX_VALUE;
                        if (i9 == 65535) {
                            break;
                        }
                        if (objArr[i9].equals(obj4)) {
                            throw zza(obj4, obj5, objArr, i9);
                        }
                        zza4 = i8 + 1;
                    }
                }
            } else {
                bArr = new int[zza2];
                Arrays.fill((int[]) bArr, -1);
                while (i2 < i) {
                    int i10 = i2 * 2;
                    Object obj6 = objArr[i10];
                    Object obj7 = objArr[i10 ^ 1];
                    zzdq.zza(obj6, obj7);
                    int zza5 = zzec.zza(obj6.hashCode());
                    while (true) {
                        int i11 = zza5 & i3;
                        ?? r8 = bArr[i11];
                        if (r8 == -1) {
                            break;
                        }
                        if (objArr[r8].equals(obj6)) {
                            throw zza(obj6, obj7, objArr, r8);
                        }
                        zza5 = i11 + 1;
                    }
                }
            }
            obj = bArr;
        }
        return new zzes<>(obj, objArr, i);
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append("=");
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    private zzes(Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.vision.zzef, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzb;
        Object[] objArr = this.zzc;
        int i = this.zzd;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int zza2 = zzec.zza(obj.hashCode());
            while (true) {
                int i2 = zza2 & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return (V) objArr[i3 ^ 1];
                }
                zza2 = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int zza3 = zzec.zza(obj.hashCode());
            while (true) {
                int i4 = zza3 & length2;
                int i5 = sArr[i4] & UShort.MAX_VALUE;
                if (i5 == 65535) {
                    return null;
                }
                if (objArr[i5].equals(obj)) {
                    return (V) objArr[i5 ^ 1];
                }
                zza3 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int zza4 = zzec.zza(obj.hashCode());
            while (true) {
                int i6 = zza4 & length3;
                int i7 = iArr[i6];
                if (i7 == -1) {
                    return null;
                }
                if (objArr[i7].equals(obj)) {
                    return (V) objArr[i7 ^ 1];
                }
                zza4 = i6 + 1;
            }
        }
    }

    @Override // com.google.android.gms.internal.vision.zzef
    final zzej<Map.Entry<K, V>> zza() {
        return new zzer(this, this.zzc, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.vision.zzef
    final zzej<K> zzb() {
        return new zzet(this, new zzew(this.zzc, 0, this.zzd));
    }

    @Override // com.google.android.gms.internal.vision.zzef
    final zzeb<V> zzc() {
        return new zzew(this.zzc, 1, this.zzd);
    }
}
