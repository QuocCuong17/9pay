package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzlg {
    private static zzlf zza;

    public static synchronized zzkx zza(zzkr zzkrVar) {
        zzkx zzkxVar;
        synchronized (zzlg.class) {
            if (zza == null) {
                zza = new zzlf(null);
            }
            zzkxVar = (zzkx) zza.get(zzkrVar);
        }
        return zzkxVar;
    }

    public static synchronized zzkx zzb(String str) {
        zzkx zza2;
        synchronized (zzlg.class) {
            zza2 = zza(zzkr.zzd("vision-common").zzd());
        }
        return zza2;
    }
}
