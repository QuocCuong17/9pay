package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
final class zzdt<K, V> extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzdp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdt(zzdp zzdpVar) {
        this.zza = zzdpVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return this.zza.zzf();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@NullableDecl Object obj) {
        int zza;
        Map<K, V> zzb = this.zza.zzb();
        if (zzb != null) {
            return zzb.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zza = this.zza.zza(entry.getKey());
            if (zza != -1 && zzcz.zza(this.zza.zzc[zza], entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@NullableDecl Object obj) {
        int zzi;
        Object obj2;
        Map<K, V> zzb = this.zza.zzb();
        if (zzb != null) {
            return zzb.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (this.zza.zza()) {
            return false;
        }
        zzi = this.zza.zzi();
        Object key = entry.getKey();
        Object value = entry.getValue();
        obj2 = this.zza.zze;
        int zza = zzea.zza(key, value, zzi, obj2, this.zza.zza, this.zza.zzb, this.zza.zzc);
        if (zza == -1) {
            return false;
        }
        this.zza.zza(zza, zzi);
        zzdp.zzd(this.zza);
        this.zza.zzc();
        return true;
    }
}
