package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
final class zzlw extends zzlu<zzlx, zzlx> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final boolean zza(zzld zzldVar) {
        return false;
    }

    /* renamed from: zza, reason: avoid collision after fix types in other method */
    private static void zza2(Object obj, zzlx zzlxVar) {
        ((zzjb) obj).zzb = zzlxVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final void zzd(Object obj) {
        ((zzjb) obj).zzb.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ int zzf(zzlx zzlxVar) {
        return zzlxVar.zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ int zze(zzlx zzlxVar) {
        return zzlxVar.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zzc(zzlx zzlxVar, zzlx zzlxVar2) {
        zzlx zzlxVar3 = zzlxVar;
        zzlx zzlxVar4 = zzlxVar2;
        return zzlxVar4.equals(zzlx.zza()) ? zzlxVar3 : zzlx.zza(zzlxVar3, zzlxVar4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zzb(zzlx zzlxVar, zzmr zzmrVar) throws IOException {
        zzlxVar.zza(zzmrVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zza(zzlx zzlxVar, zzmr zzmrVar) throws IOException {
        zzlxVar.zzb(zzmrVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zzb(Object obj, zzlx zzlxVar) {
        zza2(obj, zzlxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zzc(Object obj) {
        zzlx zzlxVar = ((zzjb) obj).zzb;
        if (zzlxVar != zzlx.zza()) {
            return zzlxVar;
        }
        zzlx zzb = zzlx.zzb();
        zza2(obj, zzb);
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zzb(Object obj) {
        return ((zzjb) obj).zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* bridge */ /* synthetic */ void zza(Object obj, zzlx zzlxVar) {
        zza2(obj, zzlxVar);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    final /* synthetic */ zzlx zza(zzlx zzlxVar) {
        zzlx zzlxVar2 = zzlxVar;
        zzlxVar2.zzc();
        return zzlxVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zza() {
        return zzlx.zzb();
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    final /* synthetic */ void zza(zzlx zzlxVar, int i, zzlx zzlxVar2) {
        zzlxVar.zza((i << 3) | 3, zzlxVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zza(zzlx zzlxVar, int i, zzht zzhtVar) {
        zzlxVar.zza((i << 3) | 2, zzhtVar);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    final /* synthetic */ void zzb(zzlx zzlxVar, int i, long j) {
        zzlxVar.zza((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    final /* synthetic */ void zza(zzlx zzlxVar, int i, int i2) {
        zzlxVar.zza((i << 3) | 5, Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zza(zzlx zzlxVar, int i, long j) {
        zzlxVar.zza(i << 3, Long.valueOf(j));
    }
}
