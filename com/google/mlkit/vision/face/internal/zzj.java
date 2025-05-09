package com.google.mlkit.vision.face.internal;

import co.hyperverge.hypersnapsdk.utils.AppConstants;
import com.google.android.gms.internal.mlkit_vision_face.zzjx;
import com.google.android.gms.internal.mlkit_vision_face.zzjy;
import com.google.android.gms.internal.mlkit_vision_face.zzjz;
import com.google.android.gms.internal.mlkit_vision_face.zzka;
import com.google.android.gms.internal.mlkit_vision_face.zzkb;
import com.google.android.gms.internal.mlkit_vision_face.zzkd;
import com.google.android.gms.internal.mlkit_vision_face.zzkr;
import com.google.android.gms.internal.mlkit_vision_face.zzks;
import com.google.android.gms.internal.mlkit_vision_face.zzkt;
import com.google.android.gms.internal.mlkit_vision_face.zzku;
import com.google.android.gms.internal.mlkit_vision_face.zzlm;
import com.google.android.gms.internal.mlkit_vision_face.zznr;
import com.google.android.gms.internal.mlkit_vision_face.zzoa;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzof;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public final class zzj {
    static final AtomicReference zza = new AtomicReference();

    public static zzkd zza(FaceDetectorOptions faceDetectorOptions) {
        zzka zzkaVar;
        zzjy zzjyVar;
        zzkb zzkbVar;
        zzjz zzjzVar;
        zzjx zzjxVar = new zzjx();
        int zzd = faceDetectorOptions.zzd();
        if (zzd == 1) {
            zzkaVar = zzka.NO_LANDMARKS;
        } else if (zzd == 2) {
            zzkaVar = zzka.ALL_LANDMARKS;
        } else {
            zzkaVar = zzka.UNKNOWN_LANDMARKS;
        }
        zzjxVar.zzd(zzkaVar);
        int zzb = faceDetectorOptions.zzb();
        if (zzb == 1) {
            zzjyVar = zzjy.NO_CLASSIFICATIONS;
        } else if (zzb == 2) {
            zzjyVar = zzjy.ALL_CLASSIFICATIONS;
        } else {
            zzjyVar = zzjy.UNKNOWN_CLASSIFICATIONS;
        }
        zzjxVar.zza(zzjyVar);
        int zze = faceDetectorOptions.zze();
        if (zze == 1) {
            zzkbVar = zzkb.FAST;
        } else if (zze == 2) {
            zzkbVar = zzkb.ACCURATE;
        } else {
            zzkbVar = zzkb.UNKNOWN_PERFORMANCE;
        }
        zzjxVar.zzf(zzkbVar);
        int zzc = faceDetectorOptions.zzc();
        if (zzc == 1) {
            zzjzVar = zzjz.NO_CONTOURS;
        } else if (zzc == 2) {
            zzjzVar = zzjz.ALL_CONTOURS;
        } else {
            zzjzVar = zzjz.UNKNOWN_CONTOURS;
        }
        zzjxVar.zzb(zzjzVar);
        zzjxVar.zzc(Boolean.valueOf(faceDetectorOptions.zzg()));
        zzjxVar.zze(Float.valueOf(faceDetectorOptions.zza()));
        return zzjxVar.zzk();
    }

    public static String zzb() {
        return true != zzd() ? "play-services-mlkit-face-detection" : AppConstants.FACE_DETECTION_FEATURE;
    }

    public static void zzc(zzoc zzocVar, final boolean z, final zzks zzksVar) {
        zzocVar.zzf(new zzoa() { // from class: com.google.mlkit.vision.face.internal.zzi
            @Override // com.google.android.gms.internal.mlkit_vision_face.zzoa
            public final zznr zza() {
                boolean z2 = z;
                zzks zzksVar2 = zzksVar;
                zzku zzkuVar = new zzku();
                zzkuVar.zze(z2 ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
                zzlm zzlmVar = new zzlm();
                zzlmVar.zzb(zzksVar2);
                zzkuVar.zzh(zzlmVar.zzc());
                return zzof.zzf(zzkuVar);
            }
        }, zzkt.ON_DEVICE_FACE_LOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd() {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean zzc = zza.zzc(MlKitContext.getInstance().getApplicationContext());
        atomicReference.set(Boolean.valueOf(zzc));
        return zzc;
    }
}
