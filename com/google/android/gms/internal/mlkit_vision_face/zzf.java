package com.google.android.gms.internal.mlkit_vision_face;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    public final int zza;
    public final int zzb;
    public final float zzc;
    public final float zzd;
    public final float zze;
    public final float zzf;
    public final float zzg;
    public final float zzh;
    public final float zzi;
    public final zzn[] zzj;
    public final float zzk;
    public final float zzl;
    public final float zzm;
    public final zzd[] zzn;
    public final float zzo;

    public zzf(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, zzn[] zznVarArr, float f8, float f9, float f10, zzd[] zzdVarArr, float f11) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = f;
        this.zzd = f2;
        this.zze = f3;
        this.zzf = f4;
        this.zzg = f5;
        this.zzh = f6;
        this.zzi = f7;
        this.zzj = zznVarArr;
        this.zzk = f8;
        this.zzl = f9;
        this.zzm = f10;
        this.zzn = zzdVarArr;
        this.zzo = f11;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzc);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzd);
        SafeParcelWriter.writeFloat(parcel, 5, this.zze);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzg);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzh);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzj, i, false);
        SafeParcelWriter.writeFloat(parcel, 10, this.zzk);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzl);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzm);
        SafeParcelWriter.writeTypedArray(parcel, 13, this.zzn, i, false);
        SafeParcelWriter.writeFloat(parcel, 14, this.zzi);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
