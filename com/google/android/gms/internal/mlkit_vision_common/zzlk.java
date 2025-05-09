package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzlk {
    private static zzlk zza;

    private zzlk() {
    }

    public static synchronized zzlk zza() {
        zzlk zzlkVar;
        synchronized (zzlk.class) {
            if (zza == null) {
                zza = new zzlk();
            }
            zzlkVar = zza;
        }
        return zzlkVar;
    }

    public static final boolean zzb() {
        return zzlj.zza("mlkit-dev-profiling");
    }
}
