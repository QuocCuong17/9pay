package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzjd {
    private zzjp zza;
    private Long zzb;
    private zziy zzc;
    private Long zzd;
    private zzje zze;
    private Long zzf;

    public final zzjd zzb(Long l) {
        this.zzf = l;
        return this;
    }

    public final zzjd zzc(zzje zzjeVar) {
        this.zze = zzjeVar;
        return this;
    }

    public final zzjd zzd(zziy zziyVar) {
        this.zzc = zziyVar;
        return this;
    }

    public final zzjd zze(Long l) {
        this.zzd = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjd zzf(zzjp zzjpVar) {
        this.zza = zzjpVar;
        return this;
    }

    public final zzjd zzg(Long l) {
        this.zzb = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjg zzi() {
        return new zzjg(this, null);
    }
}
