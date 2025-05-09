package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
abstract class zzju {
    private static final zzju zza;
    private static final zzju zzb;

    private zzju() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzju zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzju zzb() {
        return zzb;
    }

    static {
        zzjx zzjxVar = null;
        zza = new zzjw();
        zzb = new zzjz();
    }
}
