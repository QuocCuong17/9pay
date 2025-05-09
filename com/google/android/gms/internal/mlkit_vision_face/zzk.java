package com.google.android.gms.internal.mlkit_vision_face;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzk extends zza implements zzm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzm
    public final zzj zzd(IObjectWrapper iObjectWrapper, zzh zzhVar) throws RemoteException {
        zzj zzjVar;
        Parcel zza = zza();
        zzc.zzb(zza, iObjectWrapper);
        zzc.zza(zza, zzhVar);
        Parcel zzb = zzb(1, zza);
        IBinder readStrongBinder = zzb.readStrongBinder();
        if (readStrongBinder == null) {
            zzjVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            zzjVar = queryLocalInterface instanceof zzj ? (zzj) queryLocalInterface : new zzj(readStrongBinder);
        }
        zzb.recycle();
        return zzjVar;
    }
}
