package com.google.mlkit.vision.face.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_face.zzkr;
import com.google.android.gms.internal.mlkit_vision_face.zzkt;
import com.google.android.gms.internal.mlkit_vision_face.zzku;
import com.google.android.gms.internal.mlkit_vision_face.zzlj;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzof;
import com.google.android.gms.internal.mlkit_vision_face.zzon;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public class FaceDetectorImpl extends MobileVisionBase<List<Face>> implements FaceDetector {
    static final FaceDetectorOptions zzb = new FaceDetectorOptions.Builder().build();
    private final boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ FaceDetectorImpl(zzh zzhVar, ExecutorSelector executorSelector, FaceDetectorOptions faceDetectorOptions, zzd zzdVar) {
        super(zzhVar, r2);
        Executor executorToUse = executorSelector.getExecutorToUse(faceDetectorOptions.zzf());
        zzoc zzb2 = zzon.zzb(zzj.zzb());
        boolean zzd = zzj.zzd();
        this.zzc = zzd;
        zzku zzkuVar = new zzku();
        zzkuVar.zze(zzd ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzlj zzljVar = new zzlj();
        zzljVar.zze(zzj.zza(faceDetectorOptions));
        zzkuVar.zzg(zzljVar.zzi());
        zzb2.zzd(zzof.zzg(zzkuVar, 1), zzkt.ON_DEVICE_FACE_CREATE);
    }

    @Override // com.google.mlkit.vision.interfaces.Detector
    public final int getDetectorType() {
        return 2;
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        return this.zzc ? OptionalModuleUtils.EMPTY_FEATURES : new Feature[]{OptionalModuleUtils.FEATURE_FACE};
    }

    @Override // com.google.mlkit.vision.face.FaceDetector
    public final Task<List<Face>> process(MlImage mlImage) {
        return super.processBase(mlImage);
    }

    @Override // com.google.mlkit.vision.face.FaceDetector
    public final Task<List<Face>> process(InputImage inputImage) {
        return super.processBase(inputImage);
    }
}
