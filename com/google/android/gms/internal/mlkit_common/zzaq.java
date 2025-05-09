package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzaq {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzap zzc;

    public final zzaq zza(Object obj, Object obj2) {
        int i = this.zzb + 1;
        int i2 = i + i;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (i2 > length) {
            this.zza = Arrays.copyOf(objArr, zzaj.zzb(length, i2));
        }
        zzaf.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i3 = this.zzb;
        int i4 = i3 + i3;
        objArr2[i4] = obj;
        objArr2[i4 + 1] = obj2;
        this.zzb = i3 + 1;
        return this;
    }

    public final zzar zzb() {
        zzap zzapVar = this.zzc;
        if (zzapVar != null) {
            throw zzapVar.zza();
        }
        zzaz zzg = zzaz.zzg(this.zzb, this.zza, this);
        zzap zzapVar2 = this.zzc;
        if (zzapVar2 == null) {
            return zzg;
        }
        throw zzapVar2.zza();
    }
}
