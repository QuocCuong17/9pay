package com.google.mlkit.vision.face;

import android.graphics.PointF;
import com.google.android.gms.internal.mlkit_vision_face.zzv;
import com.google.android.gms.internal.mlkit_vision_face.zzw;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public class FaceContour {
    public static final int FACE = 1;
    public static final int LEFT_CHEEK = 14;
    public static final int LEFT_EYE = 6;
    public static final int LEFT_EYEBROW_BOTTOM = 3;
    public static final int LEFT_EYEBROW_TOP = 2;
    public static final int LOWER_LIP_BOTTOM = 11;
    public static final int LOWER_LIP_TOP = 10;
    public static final int NOSE_BOTTOM = 13;
    public static final int NOSE_BRIDGE = 12;
    public static final int RIGHT_CHEEK = 15;
    public static final int RIGHT_EYE = 7;
    public static final int RIGHT_EYEBROW_BOTTOM = 5;
    public static final int RIGHT_EYEBROW_TOP = 4;
    public static final int UPPER_LIP_BOTTOM = 9;
    public static final int UPPER_LIP_TOP = 8;
    private final int zza;
    private final List zzb;

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes4.dex */
    public @interface ContourType {
    }

    public FaceContour(int i, List list) {
        this.zza = i;
        this.zzb = list;
    }

    public int getFaceContourType() {
        return this.zza;
    }

    public List<PointF> getPoints() {
        return this.zzb;
    }

    public String toString() {
        zzv zza = zzw.zza("FaceContour");
        zza.zzb("type", this.zza);
        zza.zzc("points", this.zzb.toArray());
        return zza.toString();
    }
}
