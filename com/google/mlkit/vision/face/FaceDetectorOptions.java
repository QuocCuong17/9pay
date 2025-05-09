package com.google.mlkit.vision.face;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_vision_face.zzv;
import com.google.android.gms.internal.mlkit_vision_face.zzw;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public class FaceDetectorOptions {
    public static final int CLASSIFICATION_MODE_ALL = 2;
    public static final int CLASSIFICATION_MODE_NONE = 1;
    public static final int CONTOUR_MODE_ALL = 2;
    public static final int CONTOUR_MODE_NONE = 1;
    public static final int LANDMARK_MODE_ALL = 2;
    public static final int LANDMARK_MODE_NONE = 1;
    public static final int PERFORMANCE_MODE_ACCURATE = 2;
    public static final int PERFORMANCE_MODE_FAST = 1;
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final boolean zze;
    private final float zzf;
    private final Executor zzg;

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    /* loaded from: classes4.dex */
    public static class Builder {
        private int zza = 1;
        private int zzb = 1;
        private int zzc = 1;
        private int zzd = 1;
        private boolean zze = false;
        private float zzf = 0.1f;
        private Executor zzg;

        public FaceDetectorOptions build() {
            return new FaceDetectorOptions(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, null);
        }

        public Builder enableTracking() {
            this.zze = true;
            return this;
        }

        public Builder setClassificationMode(int i) {
            this.zzc = i;
            return this;
        }

        public Builder setContourMode(int i) {
            this.zzb = i;
            return this;
        }

        public Builder setExecutor(Executor executor) {
            this.zzg = executor;
            return this;
        }

        public Builder setLandmarkMode(int i) {
            this.zza = i;
            return this;
        }

        public Builder setMinFaceSize(float f) {
            this.zzf = f;
            return this;
        }

        public Builder setPerformanceMode(int i) {
            this.zzd = i;
            return this;
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes4.dex */
    public @interface ClassificationMode {
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes4.dex */
    public @interface ContourMode {
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes4.dex */
    public @interface LandmarkMode {
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes4.dex */
    public @interface PerformanceMode {
    }

    /* synthetic */ FaceDetectorOptions(int i, int i2, int i3, int i4, boolean z, float f, Executor executor, zza zzaVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = z;
        this.zzf = f;
        this.zzg = executor;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FaceDetectorOptions)) {
            return false;
        }
        FaceDetectorOptions faceDetectorOptions = (FaceDetectorOptions) obj;
        return Float.floatToIntBits(this.zzf) == Float.floatToIntBits(faceDetectorOptions.zzf) && Objects.equal(Integer.valueOf(this.zza), Integer.valueOf(faceDetectorOptions.zza)) && Objects.equal(Integer.valueOf(this.zzb), Integer.valueOf(faceDetectorOptions.zzb)) && Objects.equal(Integer.valueOf(this.zzd), Integer.valueOf(faceDetectorOptions.zzd)) && Objects.equal(Boolean.valueOf(this.zze), Boolean.valueOf(faceDetectorOptions.zze)) && Objects.equal(Integer.valueOf(this.zzc), Integer.valueOf(faceDetectorOptions.zzc)) && Objects.equal(this.zzg, faceDetectorOptions.zzg);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Float.floatToIntBits(this.zzf)), Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzd), Boolean.valueOf(this.zze), Integer.valueOf(this.zzc), this.zzg);
    }

    public String toString() {
        zzv zza = zzw.zza("FaceDetectorOptions");
        zza.zzb("landmarkMode", this.zza);
        zza.zzb("contourMode", this.zzb);
        zza.zzb("classificationMode", this.zzc);
        zza.zzb("performanceMode", this.zzd);
        zza.zzd("trackingEnabled", this.zze);
        zza.zza("minFaceSize", this.zzf);
        return zza.toString();
    }

    public final float zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzc;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final int zzd() {
        return this.zza;
    }

    public final int zze() {
        return this.zzd;
    }

    public final Executor zzf() {
        return this.zzg;
    }

    public final boolean zzg() {
        return this.zze;
    }
}
