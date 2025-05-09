package com.google.android.gms.internal.mlkit_vision_face;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzoy extends zza implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzoy(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.face.aidls.IFaceDetector");
    }

    public final List zzd(IObjectWrapper iObjectWrapper, zzoq zzoqVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, iObjectWrapper);
        zzc.zza(zza, zzoqVar);
        Parcel zzb = zzb(3, zza);
        ArrayList createTypedArrayList = zzb.createTypedArrayList(zzow.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public final void zze() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzf() throws RemoteException {
        zzc(2, zza());
    }
}
