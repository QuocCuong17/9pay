package com.google.mlkit.vision.face;

import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.face.internal.zzc;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public class FaceDetection {
    private FaceDetection() {
    }

    public static FaceDetector getClient() {
        return ((zzc) MlKitContext.getInstance().get(zzc.class)).zza();
    }

    public static FaceDetector getClient(FaceDetectorOptions faceDetectorOptions) {
        Preconditions.checkNotNull(faceDetectorOptions, "You must provide a valid FaceDetectorOptions.");
        return ((zzc) MlKitContext.getInstance().get(zzc.class)).zzb(faceDetectorOptions);
    }
}
