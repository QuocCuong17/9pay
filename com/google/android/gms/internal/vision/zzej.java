package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public abstract class zzej<E> extends zzeb<E> implements Set<E> {

    @NullableDecl
    private transient zzee<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (highestOneBit * 0.7d < max) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        zzde.zza(max < 1073741824, "collection too large");
        return 1073741824;
    }

    boolean zzg() {
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzej) && zzg() && ((zzej) obj).zzg() && hashCode() != obj.hashCode()) {
            return false;
        }
        return zzey.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzey.zza(this);
    }

    @Override // com.google.android.gms.internal.vision.zzeb
    public zzee<E> zze() {
        zzee<E> zzeeVar = this.zza;
        if (zzeeVar != null) {
            return zzeeVar;
        }
        zzee<E> zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    zzee<E> zzh() {
        return zzee.zza(toArray());
    }

    @Override // com.google.android.gms.internal.vision.zzeb, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
