package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
final class zzko extends zzkr {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzko(String str, boolean z, int i, zzkn zzknVar) {
        this.zza = str;
        this.zzb = z;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzkr) {
            zzkr zzkrVar = (zzkr) obj;
            if (this.zza.equals(zzkrVar.zzb()) && this.zzb == zzkrVar.zzc() && this.zzc == zzkrVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.zza + ", enableFirelog=" + this.zzb + ", firelogEventType=" + this.zzc + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkr
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkr
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkr
    public final boolean zzc() {
        return this.zzb;
    }
}
