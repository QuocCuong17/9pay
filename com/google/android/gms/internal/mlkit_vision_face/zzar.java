package com.google.android.gms.internal.mlkit_vision_face;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public abstract class zzar implements zzca {

    @CheckForNull
    private transient Set zza;

    @CheckForNull
    private transient Map zzb;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzca) {
            return zzp().equals(((zzca) obj).zzp());
        }
        return false;
    }

    public final int hashCode() {
        return zzp().hashCode();
    }

    public final String toString() {
        return ((zzah) zzp()).zza.toString();
    }

    abstract Map zzk();

    abstract Set zzl();

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzca
    public boolean zzo(Object obj, Object obj2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzca
    public final Map zzp() {
        Map map = this.zzb;
        if (map != null) {
            return map;
        }
        Map zzk = zzk();
        this.zzb = zzk;
        return zzk;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzca
    public final Set zzq() {
        Set set = this.zza;
        if (set != null) {
            return set;
        }
        Set zzl = zzl();
        this.zza = zzl;
        return zzl;
    }
}
