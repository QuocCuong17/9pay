package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzjo {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private zzp zze;
    private String zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Integer zzj;
    private Integer zzk;

    public final zzjo zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzjo zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzjo zzd(Integer num) {
        this.zzj = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzjo zze(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final zzjo zzf(Boolean bool) {
        this.zzi = bool;
        return this;
    }

    public final zzjo zzg(Boolean bool) {
        this.zzh = bool;
        return this;
    }

    public final zzjo zzh(zzp zzpVar) {
        this.zze = zzpVar;
        return this;
    }

    public final zzjo zzi(String str) {
        this.zzf = str;
        return this;
    }

    public final zzjo zzj(String str) {
        this.zzc = str;
        return this;
    }

    public final zzjo zzk(Integer num) {
        this.zzk = num;
        return this;
    }

    public final zzjo zzl(String str) {
        this.zzd = str;
        return this;
    }

    public final zzjq zzm() {
        return new zzjq(this, null);
    }
}
