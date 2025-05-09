package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.3 */
/* loaded from: classes3.dex */
public final class zzan extends zzt<zzad> {
    private final zzam zza;

    public zzan(Context context, zzam zzamVar) {
        super(context, "TextNativeHandle", OptionalModuleUtils.OCR);
        this.zza = zzamVar;
        zzd();
    }

    public final zzah[] zza(Bitmap bitmap, zzs zzsVar, zzaj zzajVar) {
        if (!zzb()) {
            return new zzah[0];
        }
        try {
            return ((zzad) Preconditions.checkNotNull(zzd())).zza(ObjectWrapper.wrap(bitmap), zzsVar, zzajVar);
        } catch (RemoteException e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzah[0];
        }
    }

    @Override // com.google.android.gms.internal.vision.zzt
    protected final void zza() throws RemoteException {
        ((zzad) Preconditions.checkNotNull(zzd())).zzb();
    }

    @Override // com.google.android.gms.internal.vision.zzt
    protected final /* synthetic */ zzad zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzaf zzaeVar;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            zzaeVar = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            if (queryLocalInterface instanceof zzaf) {
                zzaeVar = (zzaf) queryLocalInterface;
            } else {
                zzaeVar = new zzae(instantiate);
            }
        }
        if (zzaeVar == null) {
            return null;
        }
        return zzaeVar.zza(ObjectWrapper.wrap(context), (zzam) Preconditions.checkNotNull(this.zza));
    }
}
