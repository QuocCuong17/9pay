package com.google.android.gms.internal.mlkit_vision_face;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzon {
    private static zzom zza;

    public static synchronized zzoc zza(zznt zzntVar) {
        zzoc zzocVar;
        synchronized (zzon.class) {
            if (zza == null) {
                zza = new zzom(null);
            }
            zzocVar = (zzoc) zza.get(zzntVar);
        }
        return zzocVar;
    }

    public static synchronized zzoc zzb(String str) {
        zzoc zza2;
        synchronized (zzon.class) {
            zza2 = zza(zznt.zzd(str).zzd());
        }
        return zza2;
    }
}
