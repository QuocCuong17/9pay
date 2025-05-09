package com.google.android.gms.internal.mlkit_vision_face;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzv {
    private final String zza;
    private final zzu zzb;
    private zzu zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzv(String str, zzs zzsVar) {
        zzu zzuVar = new zzu(null);
        this.zzb = zzuVar;
        this.zzc = zzuVar;
        this.zza = str;
    }

    private final zzv zze(String str, Object obj) {
        zzt zztVar = new zzt(null);
        this.zzc.zzc = zztVar;
        this.zzc = zztVar;
        zztVar.zzb = obj;
        zztVar.zza = str;
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzu zzuVar = this.zzb.zzc;
        String str = "";
        while (zzuVar != null) {
            Object obj = zzuVar.zzb;
            sb.append(str);
            String str2 = zzuVar.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r3.length() - 1);
            }
            zzuVar = zzuVar.zzc;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzv zza(String str, float f) {
        zze(str, String.valueOf(f));
        return this;
    }

    public final zzv zzb(String str, int i) {
        zze(str, String.valueOf(i));
        return this;
    }

    public final zzv zzc(String str, @CheckForNull Object obj) {
        zzu zzuVar = new zzu(null);
        this.zzc.zzc = zzuVar;
        this.zzc = zzuVar;
        zzuVar.zzb = obj;
        zzuVar.zza = str;
        return this;
    }

    public final zzv zzd(String str, boolean z) {
        zze("trackingEnabled", String.valueOf(z));
        return this;
    }
}
