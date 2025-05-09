package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
final class zzeo<T> extends zzfa<T> {
    private boolean zza;
    private final /* synthetic */ Object zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeo(Object obj) {
        this.zzb = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.zza;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (this.zza) {
            throw new NoSuchElementException();
        }
        this.zza = true;
        return (T) this.zzb;
    }
}
