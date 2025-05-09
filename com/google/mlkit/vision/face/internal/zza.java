package com.google.mlkit.vision.face.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_face.zzks;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzoq;
import com.google.android.gms.internal.mlkit_vision_face.zzou;
import com.google.android.gms.internal.mlkit_vision_face.zzow;
import com.google.android.gms.internal.mlkit_vision_face.zzoy;
import com.google.android.gms.internal.mlkit_vision_face.zzpa;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public final class zza implements zzb {
    private final Context zza;
    private final FaceDetectorOptions zzb;
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private final zzoc zzf;
    private zzoy zzg;
    private zzoy zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(Context context, FaceDetectorOptions faceDetectorOptions, zzoc zzocVar) {
        this.zza = context;
        this.zzb = faceDetectorOptions;
        this.zzf = zzocVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(Context context) {
        return DynamiteModule.getLocalVersion(context, "com.google.mlkit.dynamite.face") > 0;
    }

    private final void zzf() throws DynamiteModule.LoadingException, RemoteException {
        if (this.zzb.zzc() != 2) {
            if (this.zzh == null) {
                this.zzh = zzg(new zzou(this.zzb.zze(), this.zzb.zzd(), this.zzb.zzb(), 1, this.zzb.zzg(), this.zzb.zza()));
                return;
            }
            return;
        }
        if (this.zzg == null) {
            this.zzg = zzg(new zzou(this.zzb.zze(), 1, 1, 2, false, this.zzb.zza()));
        }
        if ((this.zzb.zzd() == 2 || this.zzb.zzb() == 2 || this.zzb.zze() == 2) && this.zzh == null) {
            this.zzh = zzg(new zzou(this.zzb.zze(), this.zzb.zzd(), this.zzb.zzb(), 1, this.zzb.zzg(), this.zzb.zza()));
        }
    }

    private final zzoy zzg(zzou zzouVar) throws DynamiteModule.LoadingException, RemoteException {
        return this.zzd ? zze(DynamiteModule.PREFER_LOCAL, "com.google.mlkit.dynamite.face", "com.google.mlkit.vision.face.bundled.internal.ThickFaceDetectorCreator", zzouVar) : zze(DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.FACE_MODULE_ID, "com.google.android.gms.vision.face.mlkit.FaceDetectorCreator", zzouVar);
    }

    private static List zzh(zzoy zzoyVar, InputImage inputImage) throws MlKitException {
        if (inputImage.getFormat() == -1) {
            inputImage = InputImage.fromByteBuffer(ImageConvertUtils.getInstance().convertToNv21Buffer(inputImage, false), inputImage.getWidth(), inputImage.getHeight(), inputImage.getRotationDegrees(), 17);
        }
        try {
            List zzd = zzoyVar.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zzoq(inputImage.getFormat(), inputImage.getWidth(), inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime()));
            ArrayList arrayList = new ArrayList();
            Iterator it = zzd.iterator();
            while (it.hasNext()) {
                arrayList.add(new Face((zzow) it.next(), inputImage.getCoordinatesMatrix()));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new MlKitException("Failed to run face detector.", 13, e);
        }
    }

    @Override // com.google.mlkit.vision.face.internal.zzb
    public final Pair zza(InputImage inputImage) throws MlKitException {
        List list;
        if (this.zzh == null && this.zzg == null) {
            zzd();
        }
        if (!this.zzc) {
            try {
                zzoy zzoyVar = this.zzh;
                if (zzoyVar != null) {
                    zzoyVar.zze();
                }
                zzoy zzoyVar2 = this.zzg;
                if (zzoyVar2 != null) {
                    zzoyVar2.zze();
                }
                this.zzc = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to init face detector.", 13, e);
            }
        }
        zzoy zzoyVar3 = this.zzh;
        List list2 = null;
        if (zzoyVar3 != null) {
            list = zzh(zzoyVar3, inputImage);
            if (!this.zzb.zzg()) {
                zzh.zzf(list);
            }
        } else {
            list = null;
        }
        zzoy zzoyVar4 = this.zzg;
        if (zzoyVar4 != null) {
            list2 = zzh(zzoyVar4, inputImage);
            zzh.zzf(list2);
        }
        return new Pair(list, list2);
    }

    @Override // com.google.mlkit.vision.face.internal.zzb
    public final void zzb() {
        try {
            zzoy zzoyVar = this.zzh;
            if (zzoyVar != null) {
                zzoyVar.zzf();
                this.zzh = null;
            }
            zzoy zzoyVar2 = this.zzg;
            if (zzoyVar2 != null) {
                zzoyVar2.zzf();
                this.zzg = null;
            }
        } catch (RemoteException e) {
            Log.e("DecoupledFaceDelegate", "Failed to release face detector.", e);
        }
        this.zzc = false;
    }

    @Override // com.google.mlkit.vision.face.internal.zzb
    public final boolean zzd() throws MlKitException {
        if (this.zzh != null || this.zzg != null) {
            return this.zzd;
        }
        if (DynamiteModule.getLocalVersion(this.zza, "com.google.mlkit.dynamite.face") > 0) {
            this.zzd = true;
            try {
                zzf();
            } catch (RemoteException e) {
                throw new MlKitException("Failed to create thick face detector.", 13, e);
            } catch (DynamiteModule.LoadingException e2) {
                throw new MlKitException("Failed to load the bundled face module.", 13, e2);
            }
        } else {
            this.zzd = false;
            try {
                zzf();
            } catch (RemoteException e3) {
                zzj.zzc(this.zzf, this.zzd, zzks.OPTIONAL_MODULE_INIT_ERROR);
                throw new MlKitException("Failed to create thin face detector.", 13, e3);
            } catch (DynamiteModule.LoadingException e4) {
                if (!this.zze) {
                    OptionalModuleUtils.requestDownload(this.zza, "face");
                    this.zze = true;
                }
                zzj.zzc(this.zzf, this.zzd, zzks.OPTIONAL_MODULE_NOT_AVAILABLE);
                throw new MlKitException("Waiting for the face module to be downloaded. Please wait.", 14, e4);
            }
        }
        zzj.zzc(this.zzf, this.zzd, zzks.NO_ERROR);
        return this.zzd;
    }

    final zzoy zze(DynamiteModule.VersionPolicy versionPolicy, String str, String str2, zzou zzouVar) throws DynamiteModule.LoadingException, RemoteException {
        return zzpa.zza(DynamiteModule.load(this.zza, versionPolicy, str).instantiate(str2)).zzd(ObjectWrapper.wrap(this.zza), zzouVar);
    }
}
