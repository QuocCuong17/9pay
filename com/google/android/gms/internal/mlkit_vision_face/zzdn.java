package com.google.android.gms.internal.mlkit_vision_face;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzdn {
    private final zzks zza;
    private final Boolean zzb;
    private final zzkh zzc = null;
    private final zzkd zzd;
    private final Integer zze;
    private final Integer zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdn(zzdl zzdlVar, zzdm zzdmVar) {
        this.zza = zzdl.zzh(zzdlVar);
        this.zzb = zzdl.zzi(zzdlVar);
        this.zzd = zzdl.zzg(zzdlVar);
        this.zze = zzdl.zzj(zzdlVar);
        this.zzf = zzdl.zzk(zzdlVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdn)) {
            return false;
        }
        zzdn zzdnVar = (zzdn) obj;
        if (Objects.equal(this.zza, zzdnVar.zza) && Objects.equal(this.zzb, zzdnVar.zzb)) {
            zzkh zzkhVar = zzdnVar.zzc;
            if (Objects.equal(null, null) && Objects.equal(this.zzd, zzdnVar.zzd) && Objects.equal(this.zze, zzdnVar.zze) && Objects.equal(this.zzf, zzdnVar.zzf)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, null, this.zzd, this.zze, this.zzf);
    }

    public final zzkd zza() {
        return this.zzd;
    }

    public final zzks zzb() {
        return this.zza;
    }

    public final Boolean zzc() {
        return this.zzb;
    }

    public final Integer zzd() {
        return this.zze;
    }

    public final Integer zze() {
        return this.zzf;
    }
}
