package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
abstract class zzlu<T, B> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zza();

    abstract T zza(B b);

    abstract void zza(B b, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzht zzhtVar);

    abstract void zza(B b, int i, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(T t, zzmr zzmrVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzld zzldVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzb(Object obj);

    abstract void zzb(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(T t, zzmr zzmrVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzc(T t, T t2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zze(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzf(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(B b, zzld zzldVar) throws IOException {
        int zzb = zzldVar.zzb();
        int i = zzb >>> 3;
        int i2 = zzb & 7;
        if (i2 == 0) {
            zza((zzlu<T, B>) b, i, zzldVar.zzg());
            return true;
        }
        if (i2 == 1) {
            zzb(b, i, zzldVar.zzi());
            return true;
        }
        if (i2 == 2) {
            zza((zzlu<T, B>) b, i, zzldVar.zzn());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zza((zzlu<T, B>) b, i, zzldVar.zzj());
                return true;
            }
            throw zzjk.zzf();
        }
        B zza = zza();
        int i3 = 4 | (i << 3);
        while (zzldVar.zza() != Integer.MAX_VALUE && zza((zzlu<T, B>) zza, zzldVar)) {
        }
        if (i3 != zzldVar.zzb()) {
            throw zzjk.zze();
        }
        zza((zzlu<T, B>) b, i, (int) zza((zzlu<T, B>) zza));
        return true;
    }
}
