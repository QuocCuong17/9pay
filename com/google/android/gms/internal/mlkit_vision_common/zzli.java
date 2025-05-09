package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzli {
    public static void zza(zzkx zzkxVar, int i, int i2, long j, int i3, int i4, int i5, int i6) {
        zzkxVar.zzc(zzc(i, i2, j, i3, i4, i5, i6), zzhs.INPUT_IMAGE_CONSTRUCTION);
    }

    public static void zzb(zzkx zzkxVar, int i, int i2, long j, int i3, int i4, int i5, int i6) {
        zzkxVar.zzc(zzc(i, i2, j, i3, i4, i5, i6), zzhs.ODML_IMAGE);
    }

    private static zzlh zzc(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        return new zzlh(i, i2, i5, i3, i4, SystemClock.elapsedRealtime() - j, i6);
    }
}
