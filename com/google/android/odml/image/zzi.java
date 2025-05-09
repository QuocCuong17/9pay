package com.google.android.odml.image;

import android.media.Image;
import android.os.Build;

/* compiled from: com.google.android.odml:image@@1.0.0-beta1 */
/* loaded from: classes3.dex */
final class zzi implements zzg {
    private final Image zza;
    private final ImageProperties zzb;

    public zzi(Image image) {
        int i;
        this.zza = image;
        zzb zzbVar = new zzb();
        zzbVar.zzb(3);
        int format = image.getFormat();
        if (Build.VERSION.SDK_INT >= 23) {
            if (format == 42) {
                i = 1;
            } else if (format == 41) {
                i = 2;
            }
            zzbVar.zza(i);
            this.zzb = zzbVar.zzc();
        }
        i = format != 35 ? format != 256 ? 0 : 9 : 7;
        zzbVar.zza(i);
        this.zzb = zzbVar.zzc();
    }

    public final Image zza() {
        return this.zza;
    }

    @Override // com.google.android.odml.image.zzg
    public final ImageProperties zzb() {
        return this.zzb;
    }

    @Override // com.google.android.odml.image.zzg
    public final void zzc() {
        this.zza.close();
    }
}
