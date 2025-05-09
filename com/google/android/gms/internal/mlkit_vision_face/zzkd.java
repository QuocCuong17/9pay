package com.google.android.gms.internal.mlkit_vision_face;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzkd {
    private final zzka zza;
    private final zzjy zzb;
    private final zzkb zzc;
    private final zzjz zzd;
    private final Boolean zze;
    private final Float zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkd(zzjx zzjxVar, zzkc zzkcVar) {
        zzka zzkaVar;
        zzjy zzjyVar;
        zzkb zzkbVar;
        zzjz zzjzVar;
        Boolean bool;
        Float f;
        zzkaVar = zzjxVar.zza;
        this.zza = zzkaVar;
        zzjyVar = zzjxVar.zzb;
        this.zzb = zzjyVar;
        zzkbVar = zzjxVar.zzc;
        this.zzc = zzkbVar;
        zzjzVar = zzjxVar.zzd;
        this.zzd = zzjzVar;
        bool = zzjxVar.zze;
        this.zze = bool;
        f = zzjxVar.zzf;
        this.zzf = f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkd)) {
            return false;
        }
        zzkd zzkdVar = (zzkd) obj;
        return Objects.equal(this.zza, zzkdVar.zza) && Objects.equal(this.zzb, zzkdVar.zzb) && Objects.equal(this.zzc, zzkdVar.zzc) && Objects.equal(this.zzd, zzkdVar.zzd) && Objects.equal(this.zze, zzkdVar.zze) && Objects.equal(this.zzf, zzkdVar.zzf);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }

    public final zzjy zza() {
        return this.zzb;
    }

    public final zzjz zzb() {
        return this.zzd;
    }

    public final zzka zzc() {
        return this.zza;
    }

    public final zzkb zzd() {
        return this.zzc;
    }

    public final Boolean zze() {
        return this.zze;
    }

    public final Float zzf() {
        return this.zzf;
    }
}
