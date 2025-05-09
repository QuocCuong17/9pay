package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzfj extends zzlb implements zzmj {
    private static final zzfj zza;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzfj zzfjVar = new zzfj();
        zza = zzfjVar;
        zzlb.zzbO(zzfj.class, zzfjVar);
    }

    private zzfj() {
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzlb
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzbL(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzfj();
        }
        zzez zzezVar = null;
        if (i2 == 4) {
            return new zzfi(zzezVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
