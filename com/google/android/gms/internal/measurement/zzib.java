package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public abstract class zzib {
    public static final /* synthetic */ int zzc = 0;

    @Nullable
    private static volatile zzhz zze = null;
    private static volatile boolean zzf = false;
    final zzhy zza;
    final String zzb;
    private final Object zzj;
    private volatile int zzk = -1;
    private volatile Object zzl;
    private static final Object zzd = new Object();
    private static final AtomicReference zzg = new AtomicReference();
    private static final zzid zzh = new zzid(new Object() { // from class: com.google.android.gms.internal.measurement.zzht
    });
    private static final AtomicInteger zzi = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzib(zzhy zzhyVar, String str, Object obj, boolean z, zzia zziaVar) {
        if (zzhyVar.zza == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zza = zzhyVar;
        this.zzb = str;
        this.zzj = obj;
    }

    public static void zzc() {
        zzi.incrementAndGet();
    }

    public static void zzd(final Context context) {
        if (zze != null || context == null) {
            return;
        }
        Object obj = zzd;
        synchronized (obj) {
            if (zze == null) {
                synchronized (obj) {
                    zzhz zzhzVar = zze;
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    if (zzhzVar == null || zzhzVar.zza() != context) {
                        zzhf.zze();
                        zzic.zzc();
                        zzhn.zze();
                        zze = new zzhc(context, zzir.zza(new zzim() { // from class: com.google.android.gms.internal.measurement.zzhs
                            @Override // com.google.android.gms.internal.measurement.zzim
                            public final Object zza() {
                                Context context2 = context;
                                int i = zzib.zzc;
                                return zzho.zza(context2);
                            }
                        }));
                        zzi.incrementAndGet();
                    }
                }
            }
        }
    }

    abstract Object zza(Object obj);

    /* JADX WARN: Removed duplicated region for block: B:14:0x0040 A[Catch: all -> 0x00c5, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x0028, B:14:0x0040, B:16:0x0046, B:18:0x0050, B:20:0x0071, B:22:0x0079, B:24:0x0081, B:26:0x0087, B:29:0x0099, B:31:0x009f, B:32:0x0097, B:34:0x00a5, B:36:0x00a9, B:39:0x00b1, B:40:0x00b4, B:41:0x00b8, B:44:0x0065, B:45:0x00bd, B:46:0x00c2, B:49:0x00c3), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bd A[Catch: all -> 0x00c5, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0018, B:11:0x0028, B:14:0x0040, B:16:0x0046, B:18:0x0050, B:20:0x0071, B:22:0x0079, B:24:0x0081, B:26:0x0087, B:29:0x0099, B:31:0x009f, B:32:0x0097, B:34:0x00a5, B:36:0x00a9, B:39:0x00b1, B:40:0x00b4, B:41:0x00b8, B:44:0x0065, B:45:0x00bd, B:46:0x00c2, B:49:0x00c3), top: B:4:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzb() {
        String str;
        zzhk zza;
        Object zzb;
        int i = zzi.get();
        if (this.zzk < i) {
            synchronized (this) {
                if (this.zzk < i) {
                    zzhz zzhzVar = zze;
                    zzii zzc2 = zzii.zzc();
                    Object obj = null;
                    if (zzhzVar != null) {
                        zzc2 = (zzii) zzhzVar.zzb().zza();
                        if (zzc2.zzb()) {
                            zzhh zzhhVar = (zzhh) zzc2.zza();
                            zzhy zzhyVar = this.zza;
                            str = zzhhVar.zza(zzhyVar.zza, null, zzhyVar.zzc, this.zzb);
                            if (zzhzVar == null) {
                                Uri uri = this.zza.zza;
                                if (uri != null) {
                                    zza = zzhp.zza(zzhzVar.zza(), uri) ? zzhf.zza(zzhzVar.zza().getContentResolver(), this.zza.zza, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhr
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            zzib.zzc();
                                        }
                                    }) : null;
                                } else {
                                    zza = zzic.zza(zzhzVar.zza(), null, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhr
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            zzib.zzc();
                                        }
                                    });
                                }
                                Object zza2 = (zza == null || (zzb = zza.zzb(this.zzb)) == null) ? null : zza(zzb);
                                if (zza2 == null) {
                                    if (!this.zza.zzd) {
                                        String zzb2 = zzhn.zza(zzhzVar.zza()).zzb(this.zza.zzd ? null : this.zzb);
                                        if (zzb2 != null) {
                                            obj = zza(zzb2);
                                        }
                                    }
                                    zza2 = obj == null ? this.zzj : obj;
                                }
                                if (zzc2.zzb()) {
                                    zza2 = str == null ? this.zzj : zza(str);
                                }
                                this.zzl = zza2;
                                this.zzk = i;
                            } else {
                                throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                            }
                        }
                    }
                    str = null;
                    if (zzhzVar == null) {
                    }
                }
            }
        }
        return this.zzl;
    }
}
