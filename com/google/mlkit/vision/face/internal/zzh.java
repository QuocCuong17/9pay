package com.google.mlkit.vision.face.internal;

import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_face.zzdk;
import com.google.android.gms.internal.mlkit_vision_face.zzdl;
import com.google.android.gms.internal.mlkit_vision_face.zzdn;
import com.google.android.gms.internal.mlkit_vision_face.zzjv;
import com.google.android.gms.internal.mlkit_vision_face.zzke;
import com.google.android.gms.internal.mlkit_vision_face.zzkf;
import com.google.android.gms.internal.mlkit_vision_face.zzkj;
import com.google.android.gms.internal.mlkit_vision_face.zzkr;
import com.google.android.gms.internal.mlkit_vision_face.zzks;
import com.google.android.gms.internal.mlkit_vision_face.zzkt;
import com.google.android.gms.internal.mlkit_vision_face.zzku;
import com.google.android.gms.internal.mlkit_vision_face.zzlj;
import com.google.android.gms.internal.mlkit_vision_face.zzll;
import com.google.android.gms.internal.mlkit_vision_face.zznr;
import com.google.android.gms.internal.mlkit_vision_face.zzoa;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzoe;
import com.google.android.gms.internal.mlkit_vision_face.zzof;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public final class zzh extends MLTask {
    static final AtomicBoolean zza = new AtomicBoolean(true);
    private static final ImageUtils zzb = ImageUtils.getInstance();
    private final FaceDetectorOptions zzc;
    private final zzoc zzd;
    private final zzoe zze;
    private final zzb zzf;
    private boolean zzg;
    private final BitmapInStreamingChecker zzh = new BitmapInStreamingChecker();

    public zzh(zzoc zzocVar, FaceDetectorOptions faceDetectorOptions, zzb zzbVar) {
        Preconditions.checkNotNull(faceDetectorOptions, "FaceDetectorOptions can not be null");
        this.zzc = faceDetectorOptions;
        this.zzd = zzocVar;
        this.zzf = zzbVar;
        this.zze = zzoe.zza(MlKitContext.getInstance().getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzf(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Face) it.next()).zzc(-1);
        }
    }

    private final synchronized void zzg(final zzks zzksVar, long j, final InputImage inputImage, final int i, final int i2) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.zzd.zzf(new zzoa() { // from class: com.google.mlkit.vision.face.internal.zzf
            @Override // com.google.android.gms.internal.mlkit_vision_face.zzoa
            public final zznr zza() {
                return zzh.this.zzc(elapsedRealtime, zzksVar, i, i2, inputImage);
            }
        }, zzkt.ON_DEVICE_FACE_DETECT);
        zzdl zzdlVar = new zzdl();
        zzdlVar.zzc(zzksVar);
        zzdlVar.zzd(Boolean.valueOf(zza.get()));
        zzdlVar.zza(Integer.valueOf(i));
        zzdlVar.zze(Integer.valueOf(i2));
        zzdlVar.zzb(zzj.zza(this.zzc));
        final zzdn zzf = zzdlVar.zzf();
        final zzg zzgVar = new zzg(this);
        final zzoc zzocVar = this.zzd;
        final zzkt zzktVar = zzkt.AGGREGATED_ON_DEVICE_FACE_DETECTION;
        final byte[] bArr = null;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzktVar, zzf, elapsedRealtime, zzgVar, bArr) { // from class: com.google.android.gms.internal.mlkit_vision_face.zzny
            public final /* synthetic */ zzkt zzb;
            public final /* synthetic */ Object zzc;
            public final /* synthetic */ long zzd;
            public final /* synthetic */ com.google.mlkit.vision.face.internal.zzg zze;

            @Override // java.lang.Runnable
            public final void run() {
                zzoc.this.zzh(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        this.zze.zzc(true != this.zzg ? 24303 : 24304, zzksVar.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() throws MlKitException {
        this.zzg = this.zzf.zzd();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void release() {
        this.zzf.zzb();
        zza.set(true);
        zzoc zzocVar = this.zzd;
        zzku zzkuVar = new zzku();
        zzkuVar.zze(this.zzg ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzocVar.zzd(zzof.zzf(zzkuVar), zzkt.ON_DEVICE_FACE_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zznr zzc(long j, zzks zzksVar, int i, int i2, InputImage inputImage) {
        zzkf zzkfVar;
        zzlj zzljVar = new zzlj();
        zzkj zzkjVar = new zzkj();
        zzkjVar.zzc(Long.valueOf(j));
        zzkjVar.zzd(zzksVar);
        zzkjVar.zze(Boolean.valueOf(zza.get()));
        zzkjVar.zza(true);
        zzkjVar.zzb(true);
        zzljVar.zzg(zzkjVar.zzf());
        zzljVar.zze(zzj.zza(this.zzc));
        zzljVar.zzd(Integer.valueOf(i));
        zzljVar.zzh(Integer.valueOf(i2));
        ImageUtils imageUtils = zzb;
        int mobileVisionImageFormat = imageUtils.getMobileVisionImageFormat(inputImage);
        int mobileVisionImageSize = imageUtils.getMobileVisionImageSize(inputImage);
        zzke zzkeVar = new zzke();
        if (mobileVisionImageFormat == -1) {
            zzkfVar = zzkf.BITMAP;
        } else if (mobileVisionImageFormat == 35) {
            zzkfVar = zzkf.YUV_420_888;
        } else if (mobileVisionImageFormat == 842094169) {
            zzkfVar = zzkf.YV12;
        } else if (mobileVisionImageFormat == 16) {
            zzkfVar = zzkf.NV16;
        } else if (mobileVisionImageFormat == 17) {
            zzkfVar = zzkf.NV21;
        } else {
            zzkfVar = zzkf.UNKNOWN_FORMAT;
        }
        zzkeVar.zza(zzkfVar);
        zzkeVar.zzb(Integer.valueOf(mobileVisionImageSize));
        zzljVar.zzf(zzkeVar.zzd());
        zzll zzi = zzljVar.zzi();
        zzku zzkuVar = new zzku();
        zzkuVar.zze(this.zzg ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzkuVar.zzg(zzi);
        return zzof.zzf(zzkuVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zznr zzd(zzdn zzdnVar, int i, zzjv zzjvVar) {
        zzku zzkuVar = new zzku();
        zzkuVar.zze(this.zzg ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzdk zzdkVar = new zzdk();
        zzdkVar.zza(Integer.valueOf(i));
        zzdkVar.zzc(zzdnVar);
        zzdkVar.zzb(zzjvVar);
        zzkuVar.zzd(zzdkVar.zze());
        return zzof.zzf(zzkuVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        r2 = (java.util.List) com.google.android.gms.common.internal.Preconditions.checkNotNull(r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0113 A[Catch: MlKitException -> 0x0129, all -> 0x0148, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x000e, B:10:0x0021, B:11:0x002a, B:14:0x002d, B:16:0x0104, B:21:0x0118, B:32:0x0113, B:33:0x010a, B:35:0x003a, B:36:0x0041, B:37:0x004a, B:39:0x0050, B:40:0x005b, B:42:0x0061, B:44:0x006d, B:46:0x0073, B:48:0x0081, B:51:0x00ab, B:53:0x00cf, B:55:0x00de, B:26:0x012e, B:28:0x0136, B:29:0x013b, B:30:0x0147, B:31:0x0139, B:62:0x00f1, B:67:0x00fc), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010a A[Catch: MlKitException -> 0x0129, all -> 0x0148, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x000e, B:10:0x0021, B:11:0x002a, B:14:0x002d, B:16:0x0104, B:21:0x0118, B:32:0x0113, B:33:0x010a, B:35:0x003a, B:36:0x0041, B:37:0x004a, B:39:0x0050, B:40:0x005b, B:42:0x0061, B:44:0x006d, B:46:0x0073, B:48:0x0081, B:51:0x00ab, B:53:0x00cf, B:55:0x00de, B:26:0x012e, B:28:0x0136, B:29:0x013b, B:30:0x0147, B:31:0x0139, B:62:0x00f1, B:67:0x00fc), top: B:3:0x0005 }] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.util.List] */
    @Override // com.google.mlkit.common.sdkinternal.MLTask
    /* renamed from: zze, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized List run(InputImage inputImage) throws MlKitException {
        long j;
        zzks zzksVar;
        ArrayList arrayList;
        Iterator it;
        Iterator it2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.zzh.check(inputImage);
        try {
            Pair zza2 = this.zzf.zza(inputImage);
            List list = (List) zza2.first;
            List list2 = (List) zza2.second;
            if (list == null && list2 == null) {
                throw new MlKitException("No detector is enabled", 13);
            }
            if (list2 == null) {
                ArrayList arrayList2 = (List) Preconditions.checkNotNull(list);
                arrayList = arrayList2;
                j = elapsedRealtime;
                zzg(zzks.NO_ERROR, j, inputImage, list2 != null ? 0 : list2.size(), list != null ? 0 : list.size());
                zza.set(false);
            } else {
                HashSet hashSet = new HashSet();
                Iterator it3 = list2.iterator();
                while (it3.hasNext()) {
                    Face face = (Face) it3.next();
                    Iterator it4 = list.iterator();
                    boolean z = false;
                    while (it4.hasNext()) {
                        Face face2 = (Face) it4.next();
                        if (face.getBoundingBox() != null && face2.getBoundingBox() != null) {
                            Rect boundingBox = face.getBoundingBox();
                            Rect boundingBox2 = face2.getBoundingBox();
                            if (boundingBox.intersect(boundingBox2)) {
                                it = it3;
                                it2 = it4;
                                j = elapsedRealtime;
                                double min = (Math.min(boundingBox.right, boundingBox2.right) - Math.max(boundingBox.left, boundingBox2.left)) * (Math.min(boundingBox.bottom, boundingBox2.bottom) - Math.max(boundingBox.top, boundingBox2.top));
                                try {
                                    if (min / ((((boundingBox.right - boundingBox.left) * (boundingBox.bottom - boundingBox.top)) + ((boundingBox2.right - boundingBox2.left) * (boundingBox2.bottom - boundingBox2.top))) - min) > 0.6d) {
                                        face2.zzb(face.zza());
                                        z = true;
                                    }
                                    hashSet.add(face2);
                                    it3 = it;
                                    it4 = it2;
                                    elapsedRealtime = j;
                                } catch (MlKitException e) {
                                    e = e;
                                    if (e.getErrorCode() == 14) {
                                        zzksVar = zzks.MODEL_NOT_DOWNLOADED;
                                    } else {
                                        zzksVar = zzks.UNKNOWN_ERROR;
                                    }
                                    zzg(zzksVar, j, inputImage, 0, 0);
                                    throw e;
                                }
                            }
                        }
                        it = it3;
                        it2 = it4;
                        j = elapsedRealtime;
                        hashSet.add(face2);
                        it3 = it;
                        it4 = it2;
                        elapsedRealtime = j;
                    }
                    Iterator it5 = it3;
                    long j2 = elapsedRealtime;
                    if (!z) {
                        hashSet.add(face);
                    }
                    it3 = it5;
                    elapsedRealtime = j2;
                }
                j = elapsedRealtime;
                arrayList = new ArrayList(hashSet);
                zzg(zzks.NO_ERROR, j, inputImage, list2 != null ? 0 : list2.size(), list != null ? 0 : list.size());
                zza.set(false);
            }
        } catch (MlKitException e2) {
            e = e2;
            j = elapsedRealtime;
        }
        return arrayList;
    }
}
