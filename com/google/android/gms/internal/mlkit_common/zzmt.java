package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzmt implements zzmh {
    private final zzja zza;
    private zzle zzb = new zzle();

    private zzmt(zzja zzjaVar, int i) {
        this.zza = zzjaVar;
        zzne.zza();
    }

    public static zzmh zzf(zzja zzjaVar) {
        return new zzmt(zzjaVar, 0);
    }

    public static zzmh zzg() {
        return new zzmt(new zzja(), 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmh
    public final zzmh zza(zziz zzizVar) {
        this.zza.zzf(zzizVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmh
    public final zzmh zzb(zzjg zzjgVar) {
        this.zza.zzi(zzjgVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmh
    public final zzmh zzc(zzle zzleVar) {
        this.zzb = zzleVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmh
    public final String zzd() {
        zzlg zzf = this.zza.zzk().zzf();
        return (zzf == null || zzad.zzc(zzf.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzf.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmh
    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzne.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzhk.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzjc zzk = this.zza.zzk();
            zzbp zzbpVar = new zzbp();
            zzhk.zza.configure(zzbpVar);
            return zzbpVar.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
