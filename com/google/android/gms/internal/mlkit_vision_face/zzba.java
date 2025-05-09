package com.google.android.gms.internal.mlkit_vision_face;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzba extends AbstractSet {
    final /* synthetic */ zzbd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzbd zzbdVar) {
        this.zza = zzbdVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbd zzbdVar = this.zza;
        Map zzl = zzbdVar.zzl();
        if (zzl != null) {
            return zzl.keySet().iterator();
        }
        return new zzav(zzbdVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        Object zzx;
        Object obj2;
        Map zzl = this.zza.zzl();
        if (zzl == null) {
            zzx = this.zza.zzx(obj);
            obj2 = zzbd.zzd;
            return zzx != obj2;
        }
        return zzl.keySet().remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
