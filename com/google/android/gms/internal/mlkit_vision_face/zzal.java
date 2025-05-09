package com.google.android.gms.internal.mlkit_vision_face;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
class zzal implements Iterator {
    final Iterator zza;
    final Collection zzb;
    final /* synthetic */ zzam zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzam zzamVar) {
        Iterator it;
        this.zzc = zzamVar;
        this.zzb = zzamVar.zzb;
        Collection collection = zzamVar.zzb;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.zza = it;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzam zzamVar, Iterator it) {
        this.zzc = zzamVar;
        this.zzb = zzamVar.zzb;
        this.zza = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zza();
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        zza();
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zza.remove();
        zzap.zze(this.zzc.zze);
        this.zzc.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        this.zzc.zzb();
        if (this.zzc.zzb != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }
}
