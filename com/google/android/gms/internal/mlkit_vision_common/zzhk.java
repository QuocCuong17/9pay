package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzhk {
    private Long zza;
    private zzhl zzb;
    private zzhg zzc;
    private Integer zzd;
    private Integer zze;
    private Integer zzf;
    private Integer zzg;

    public final zzhk zzb(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzhk zzc(Integer num) {
        this.zzd = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzhk zzd(zzhg zzhgVar) {
        this.zzc = zzhgVar;
        return this;
    }

    public final zzhk zze(Integer num) {
        this.zzf = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzhk zzf(zzhl zzhlVar) {
        this.zzb = zzhlVar;
        return this;
    }

    public final zzhk zzg(Integer num) {
        this.zze = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzhk zzh(Integer num) {
        this.zzg = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzhn zzj() {
        return new zzhn(this, null);
    }
}
