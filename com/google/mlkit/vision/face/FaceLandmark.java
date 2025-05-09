package com.google.mlkit.vision.face;

import android.graphics.PointF;
import com.google.android.gms.internal.mlkit_vision_face.zzv;
import com.google.android.gms.internal.mlkit_vision_face.zzw;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public class FaceLandmark {
    public static final int LEFT_CHEEK = 1;
    public static final int LEFT_EAR = 3;
    public static final int LEFT_EYE = 4;
    public static final int MOUTH_BOTTOM = 0;
    public static final int MOUTH_LEFT = 5;
    public static final int MOUTH_RIGHT = 11;
    public static final int NOSE_BASE = 6;
    public static final int RIGHT_CHEEK = 7;
    public static final int RIGHT_EAR = 9;
    public static final int RIGHT_EYE = 10;
    private final int zza;
    private final PointF zzb;

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes4.dex */
    public @interface LandmarkType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FaceLandmark(int i, PointF pointF) {
        this.zza = i;
        this.zzb = pointF;
    }

    public int getLandmarkType() {
        return this.zza;
    }

    public PointF getPosition() {
        return this.zzb;
    }

    public String toString() {
        zzv zza = zzw.zza("FaceLandmark");
        zza.zzb("type", this.zza);
        zza.zzc("position", this.zzb);
        return zza.toString();
    }
}
