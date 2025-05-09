package com.google.android.gms.internal.measurement;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
final class zzip implements zzim {
    private static final zzim zza = new zzim() { // from class: com.google.android.gms.internal.measurement.zzio
        @Override // com.google.android.gms.internal.measurement.zzim
        public final Object zza() {
            throw new IllegalStateException();
        }
    };
    private volatile zzim zzb;

    @CheckForNull
    private Object zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzip(zzim zzimVar) {
        Objects.requireNonNull(zzimVar);
        this.zzb = zzimVar;
    }

    public final String toString() {
        Object obj = this.zzb;
        if (obj == zza) {
            obj = "<supplier that returned " + String.valueOf(this.zzc) + ">";
        }
        return "Suppliers.memoize(" + String.valueOf(obj) + ")";
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final Object zza() {
        zzim zzimVar = this.zzb;
        zzim zzimVar2 = zza;
        if (zzimVar != zzimVar2) {
            synchronized (this) {
                if (this.zzb != zzimVar2) {
                    Object zza2 = this.zzb.zza();
                    this.zzc = zza2;
                    this.zzb = zzimVar2;
                    return zza2;
                }
            }
        }
        return this.zzc;
    }
}
