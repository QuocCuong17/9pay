package com.google.android.gms.internal.mlkit_vision_face;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzay extends AbstractSet {
    final /* synthetic */ zzbd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(zzbd zzbdVar) {
        this.zza = zzbdVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        int zzv;
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzv = this.zza.zzv(entry.getKey());
            if (zzv != -1 && zzx.zza(zzbd.zzj(this.zza, zzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbd zzbdVar = this.zza;
        Map zzl = zzbdVar.zzl();
        if (zzl != null) {
            return zzl.entrySet().iterator();
        }
        return new zzaw(zzbdVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        int zzu;
        int[] zzz;
        Object[] zzA;
        Object[] zzB;
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        zzbd zzbdVar = this.zza;
        if (zzbdVar.zzq()) {
            return false;
        }
        zzu = zzbdVar.zzu();
        Object key = entry.getKey();
        Object value = entry.getValue();
        Object zzk = zzbd.zzk(this.zza);
        zzz = this.zza.zzz();
        zzA = this.zza.zzA();
        zzB = this.zza.zzB();
        int zzb = zzbe.zzb(key, value, zzu, zzk, zzz, zzA, zzB);
        if (zzb == -1) {
            return false;
        }
        this.zza.zzp(zzb, zzu);
        zzbd.zzb(this.zza);
        this.zza.zzn();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
