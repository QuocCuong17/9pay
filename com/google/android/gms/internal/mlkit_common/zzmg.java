package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
final class zzmg extends zzms {
    private final zziy zza;
    private final String zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final ModelType zze;
    private final zzje zzf;
    private final int zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzmg(zziy zziyVar, String str, boolean z, boolean z2, ModelType modelType, zzje zzjeVar, int i, zzmf zzmfVar) {
        this.zza = zziyVar;
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
        this.zze = modelType;
        this.zzf = zzjeVar;
        this.zzg = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzms) {
            zzms zzmsVar = (zzms) obj;
            if (this.zza.equals(zzmsVar.zzc()) && this.zzb.equals(zzmsVar.zze()) && this.zzc == zzmsVar.zzg() && this.zzd == zzmsVar.zzf() && this.zze.equals(zzmsVar.zzb()) && this.zzf.equals(zzmsVar.zzd()) && this.zzg == zzmsVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ (true != this.zzc ? 1237 : 1231)) * 1000003) ^ (true == this.zzd ? 1231 : 1237)) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003) ^ this.zzg;
    }

    public final String toString() {
        return "RemoteModelLoggingOptions{errorCode=" + this.zza.toString() + ", tfliteSchemaVersion=" + this.zzb + ", shouldLogRoughDownloadTime=" + this.zzc + ", shouldLogExactDownloadTime=" + this.zzd + ", modelType=" + this.zze.toString() + ", downloadStatus=" + this.zzf.toString() + ", failureStatusCode=" + this.zzg + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final int zza() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final ModelType zzb() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final zziy zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final zzje zzd() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final String zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final boolean zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzms
    public final boolean zzg() {
        return this.zzc;
    }
}
