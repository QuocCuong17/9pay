package com.google.mlkit.common.internal.model;

import com.google.mlkit.common.internal.model.ModelUtils;
import java.util.Objects;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
final class AutoValue_ModelUtils_AutoMLManifest extends ModelUtils.AutoMLManifest {
    private final String zza;
    private final String zzb;
    private final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ModelUtils_AutoMLManifest(String str, String str2, String str3) {
        Objects.requireNonNull(str, "Null modelType");
        this.zza = str;
        Objects.requireNonNull(str2, "Null modelFile");
        this.zzb = str2;
        Objects.requireNonNull(str3, "Null labelsFile");
        this.zzc = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.AutoMLManifest) {
            ModelUtils.AutoMLManifest autoMLManifest = (ModelUtils.AutoMLManifest) obj;
            if (this.zza.equals(autoMLManifest.getModelType()) && this.zzb.equals(autoMLManifest.getModelFile()) && this.zzc.equals(autoMLManifest.getLabelsFile())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    public String getLabelsFile() {
        return this.zzc;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    public String getModelFile() {
        return this.zzb;
    }

    @Override // com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest
    public String getModelType() {
        return this.zza;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode();
    }

    public final String toString() {
        return "AutoMLManifest{modelType=" + this.zza + ", modelFile=" + this.zzb + ", labelsFile=" + this.zzc + "}";
    }
}
