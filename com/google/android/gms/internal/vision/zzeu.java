package com.google.android.gms.internal.vision;

import java.util.AbstractMap;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
final class zzeu<K, V> extends zzee<Map.Entry<K, V>> {
    private final /* synthetic */ zzer zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeu(zzer zzerVar) {
        this.zza = zzerVar;
    }

    @Override // com.google.android.gms.internal.vision.zzeb
    public final boolean zzf() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzd;
        return i;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzd;
        zzde.zza(i, i2);
        objArr = this.zza.zzb;
        int i3 = i * 2;
        Object obj = objArr[i3];
        objArr2 = this.zza.zzb;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i3 + 1]);
    }
}
