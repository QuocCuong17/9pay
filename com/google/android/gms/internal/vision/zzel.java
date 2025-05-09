package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzel<K, V> extends zzek<K, V> {
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
    
        if (r9.zzf() == false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzem<K, V> zza() {
        zzej zzejVar;
        Set<Map.Entry<K, Collection<V>>> entrySet = this.zza.entrySet();
        if (entrySet.isEmpty()) {
            return zzdz.zza;
        }
        zzei zzeiVar = new zzei(entrySet.size());
        Iterator<Map.Entry<K, Collection<V>>> it = entrySet.iterator();
        int i = 0;
        while (true) {
            int i2 = 1;
            if (it.hasNext()) {
                Map.Entry<K, Collection<V>> next = it.next();
                K key = next.getKey();
                Collection<V> value = next.getValue();
                if ((value instanceof zzej) && !(value instanceof SortedSet)) {
                    zzejVar = (zzej) value;
                }
                Object[] array = value.toArray();
                int length = array.length;
                while (true) {
                    if (length == 0) {
                        zzejVar = zzev.zza;
                        break;
                    }
                    if (length == i2) {
                        zzejVar = new zzex(array[0]);
                        break;
                    }
                    int zza = zzej.zza(length);
                    Object[] objArr = new Object[zza];
                    int i3 = zza - 1;
                    int i4 = 0;
                    int i5 = 0;
                    for (int i6 = 0; i6 < length; i6++) {
                        Object zza2 = zzeq.zza(array[i6], i6);
                        int hashCode = zza2.hashCode();
                        int zza3 = zzec.zza(hashCode);
                        while (true) {
                            int i7 = zza3 & i3;
                            Object obj = objArr[i7];
                            if (obj == null) {
                                array[i4] = zza2;
                                objArr[i7] = zza2;
                                i5 += hashCode;
                                i4++;
                                break;
                            }
                            if (!obj.equals(zza2)) {
                                zza3++;
                            }
                        }
                    }
                    Arrays.fill(array, i4, length, (Object) null);
                    if (i4 == 1) {
                        zzejVar = new zzex(array[0], i5);
                        break;
                    }
                    if (zzej.zza(i4) < zza / 2) {
                        length = i4;
                        i2 = 1;
                    } else {
                        int length2 = array.length;
                        if (i4 < (length2 >> 1) + (length2 >> 2)) {
                            array = Arrays.copyOf(array, i4);
                        }
                        zzejVar = new zzev(array, i5, objArr, i3, i4);
                    }
                }
                if (!zzejVar.isEmpty()) {
                    int i8 = (zzeiVar.zzb + 1) << 1;
                    if (i8 > zzeiVar.zza.length) {
                        Object[] objArr2 = zzeiVar.zza;
                        int length3 = zzeiVar.zza.length;
                        if (i8 < 0) {
                            throw new AssertionError("cannot store more than MAX_VALUE elements");
                        }
                        int i9 = length3 + (length3 >> 1) + 1;
                        if (i9 < i8) {
                            i9 = Integer.highestOneBit(i8 - 1) << 1;
                        }
                        if (i9 < 0) {
                            i9 = Integer.MAX_VALUE;
                        }
                        zzeiVar.zza = Arrays.copyOf(objArr2, i9);
                        zzeiVar.zzc = false;
                    }
                    zzdq.zza(key, zzejVar);
                    zzeiVar.zza[zzeiVar.zzb * 2] = key;
                    zzeiVar.zza[(zzeiVar.zzb * 2) + 1] = zzejVar;
                    zzeiVar.zzb++;
                    i += zzejVar.size();
                }
            } else {
                zzeiVar.zzc = true;
                return new zzem<>(zzes.zza(zzeiVar.zzb, zzeiVar.zza), i, null);
            }
        }
    }
}
