package com.google.mlkit.vision.face.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public final class zzc {
    private final zze zza;
    private final ExecutorSelector zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zze zzeVar, ExecutorSelector executorSelector) {
        this.zza = zzeVar;
        this.zzb = executorSelector;
    }

    public final FaceDetectorImpl zza() {
        return zzb(FaceDetectorImpl.zzb);
    }

    public final FaceDetectorImpl zzb(FaceDetectorOptions faceDetectorOptions) {
        Preconditions.checkNotNull(faceDetectorOptions, "You must provide a valid FaceDetectorOptions.");
        return new FaceDetectorImpl((zzh) this.zza.get(faceDetectorOptions), this.zzb, faceDetectorOptions, null);
    }
}
