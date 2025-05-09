package com.google.android.gms.internal.vision;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [K] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzds<K> extends zzdw<K> {
    private final /* synthetic */ zzdp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzds(zzdp zzdpVar) {
        super(zzdpVar, null);
        this.zza = zzdpVar;
    }

    @Override // com.google.android.gms.internal.vision.zzdw
    final K zza(int i) {
        return (K) this.zza.zzb[i];
    }
}
