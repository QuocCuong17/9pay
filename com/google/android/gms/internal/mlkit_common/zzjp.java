package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzjp {
    private final zzjl zza;
    private final zzjn zzb;
    private final zzjn zzc;
    private final Boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjp(zzjm zzjmVar, zzjo zzjoVar) {
        zzjl zzjlVar;
        zzjlVar = zzjmVar.zza;
        this.zza = zzjlVar;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjp)) {
            return false;
        }
        zzjp zzjpVar = (zzjp) obj;
        if (Objects.equal(this.zza, zzjpVar.zza)) {
            zzjn zzjnVar = zzjpVar.zzb;
            if (Objects.equal(null, null)) {
                zzjn zzjnVar2 = zzjpVar.zzc;
                if (Objects.equal(null, null)) {
                    Boolean bool = zzjpVar.zzd;
                    if (Objects.equal(null, null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    public final zzjl zza() {
        return this.zza;
    }
}
