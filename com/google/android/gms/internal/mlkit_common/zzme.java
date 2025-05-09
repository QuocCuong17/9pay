package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;
import java.util.Objects;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
final class zzme extends zzmr {
    private zziy zza;
    private String zzb;
    private boolean zzc;
    private boolean zzd;
    private ModelType zze;
    private zzje zzf;
    private int zzg;
    private byte zzh;

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzmr zza(zzje zzjeVar) {
        Objects.requireNonNull(zzjeVar, "Null downloadStatus");
        this.zzf = zzjeVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzmr zzb(zziy zziyVar) {
        Objects.requireNonNull(zziyVar, "Null errorCode");
        this.zza = zziyVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzmr zzc(int i) {
        this.zzg = i;
        this.zzh = (byte) (this.zzh | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzmr zzd(ModelType modelType) {
        Objects.requireNonNull(modelType, "Null modelType");
        this.zze = modelType;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzmr zze(boolean z) {
        this.zzd = z;
        this.zzh = (byte) (this.zzh | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzmr zzf(boolean z) {
        this.zzc = z;
        this.zzh = (byte) (this.zzh | 1);
        return this;
    }

    public final zzmr zzg(String str) {
        this.zzb = "NA";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmr
    public final zzms zzh() {
        zziy zziyVar;
        String str;
        ModelType modelType;
        zzje zzjeVar;
        if (this.zzh != 7 || (zziyVar = this.zza) == null || (str = this.zzb) == null || (modelType = this.zze) == null || (zzjeVar = this.zzf) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" errorCode");
            }
            if (this.zzb == null) {
                sb.append(" tfliteSchemaVersion");
            }
            if ((this.zzh & 1) == 0) {
                sb.append(" shouldLogRoughDownloadTime");
            }
            if ((this.zzh & 2) == 0) {
                sb.append(" shouldLogExactDownloadTime");
            }
            if (this.zze == null) {
                sb.append(" modelType");
            }
            if (this.zzf == null) {
                sb.append(" downloadStatus");
            }
            if ((this.zzh & 4) == 0) {
                sb.append(" failureStatusCode");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzmg(zziyVar, str, this.zzc, this.zzd, modelType, zzjeVar, this.zzg, null);
    }
}
