package com.google.android.gms.internal.mlkit_vision_face;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzj extends zza implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    public final void zzd() throws RemoteException {
        zzc(3, zza());
    }

    public final zzf[] zze(IObjectWrapper iObjectWrapper, zzp zzpVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, iObjectWrapper);
        zzc.zza(zza, zzpVar);
        Parcel zzb = zzb(1, zza);
        zzf[] zzfVarArr = (zzf[]) zzb.createTypedArray(zzf.CREATOR);
        zzb.recycle();
        return zzfVarArr;
    }

    public final zzf[] zzf(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, int i, int i2, int i3, int i4, int i5, int i6, zzp zzpVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, iObjectWrapper);
        zzc.zzb(zza, iObjectWrapper2);
        zzc.zzb(zza, iObjectWrapper3);
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        zza.writeInt(i5);
        zza.writeInt(i6);
        zzc.zza(zza, zzpVar);
        Parcel zzb = zzb(4, zza);
        zzf[] zzfVarArr = (zzf[]) zzb.createTypedArray(zzf.CREATOR);
        zzb.recycle();
        return zzfVarArr;
    }
}
