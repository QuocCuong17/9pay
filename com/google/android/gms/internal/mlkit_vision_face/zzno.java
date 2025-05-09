package com.google.android.gms.internal.mlkit_vision_face;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzno extends zzns {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzns
    public final zzns zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzns
    public final zzns zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzns zzc(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzns
    public final zznt zzd() {
        String str;
        if (this.zzd != 3 || (str = this.zza) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" libraryName");
            }
            if ((this.zzd & 1) == 0) {
                sb.append(" enableFirelog");
            }
            if ((this.zzd & 2) == 0) {
                sb.append(" firelogEventType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zznq(str, this.zzb, this.zzc, null);
    }
}
