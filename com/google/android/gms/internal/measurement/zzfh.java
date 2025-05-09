package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzfh extends zzlb implements zzmj {
    private static final zzfh zza;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzfh zzfhVar = new zzfh();
        zza = zzfhVar;
        zzlb.zzbO(zzfh.class, zzfhVar);
    }

    private zzfh() {
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
            return new zzfh();
        }
        zzez zzezVar = null;
        if (i2 == 4) {
            return new zzfg(zzezVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
