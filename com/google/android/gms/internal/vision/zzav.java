package com.google.android.gms.internal.vision;

import android.content.Context;
import java.util.Objects;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
final class zzav extends zzbr {
    private final Context zza;
    private final zzdf<zzcy<zzbe>> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(Context context, @Nullable zzdf<zzcy<zzbe>> zzdfVar) {
        Objects.requireNonNull(context, "Null context");
        this.zza = context;
        this.zzb = zzdfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzbr
    public final Context zza() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzbr
    @Nullable
    public final zzdf<zzcy<zzbe>> zzb() {
        return this.zzb;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46 + String.valueOf(valueOf2).length());
        sb.append("FlagsContext{context=");
        sb.append(valueOf);
        sb.append(", hermeticFileOverrides=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        zzdf<zzcy<zzbe>> zzdfVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbr) {
            zzbr zzbrVar = (zzbr) obj;
            if (this.zza.equals(zzbrVar.zza()) && ((zzdfVar = this.zzb) != null ? zzdfVar.equals(zzbrVar.zzb()) : zzbrVar.zzb() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        zzdf<zzcy<zzbe>> zzdfVar = this.zzb;
        return hashCode ^ (zzdfVar == null ? 0 : zzdfVar.hashCode());
    }
}
