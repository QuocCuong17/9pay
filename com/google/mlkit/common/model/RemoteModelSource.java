package com.google.mlkit.common.model;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_common.zzw;
import com.google.android.gms.internal.mlkit_common.zzx;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
public abstract class RemoteModelSource {
    private final String zza;

    protected RemoteModelSource(String str) {
        this.zza = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        return Objects.equal(this.zza, ((RemoteModelSource) obj).zza);
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public String toString() {
        zzw zzb = zzx.zzb("RemoteModelSource");
        zzb.zza("firebaseModelName", this.zza);
        return zzb.toString();
    }

    public final String zza() {
        return this.zza;
    }
}
