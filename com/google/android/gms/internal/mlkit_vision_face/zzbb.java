package com.google.android.gms.internal.mlkit_vision_face;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzbb extends zzaq {
    final /* synthetic */ zzbd zza;
    private final Object zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbb(zzbd zzbdVar, int i) {
        this.zza = zzbdVar;
        this.zzb = zzbd.zzg(zzbdVar, i);
        this.zzc = i;
    }

    private final void zza() {
        int zzv;
        int i = this.zzc;
        if (i == -1 || i >= this.zza.size() || !zzx.zza(this.zzb, zzbd.zzg(this.zza, this.zzc))) {
            zzv = this.zza.zzv(this.zzb);
            this.zzc = zzv;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzaq, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzaq, java.util.Map.Entry
    public final Object getValue() {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.get(this.zzb);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            return null;
        }
        return zzbd.zzj(this.zza, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzaq, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.put(this.zzb, obj);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            this.zza.put(this.zzb, obj);
            return null;
        }
        Object zzj = zzbd.zzj(this.zza, i);
        zzbd.zzm(this.zza, this.zzc, obj);
        return zzj;
    }
}
