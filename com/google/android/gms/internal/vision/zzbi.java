package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public abstract class zzbi<T> {

    @Nullable
    private static volatile zzbr zzb = null;
    private static volatile boolean zzc = false;
    private final zzbo zzf;
    private final String zzg;
    private final T zzh;
    private volatile int zzj;
    private volatile T zzk;
    private final boolean zzl;
    private static final Object zza = new Object();
    private static final AtomicReference<Collection<zzbi<?>>> zzd = new AtomicReference<>();
    private static zzbs zze = new zzbs(zzbk.zza);
    private static final AtomicInteger zzi = new AtomicInteger();

    @Deprecated
    public static void zza(final Context context) {
        synchronized (zza) {
            zzbr zzbrVar = zzb;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzbrVar == null || zzbrVar.zza() != context) {
                zzau.zzb();
                zzbq.zza();
                zzbd.zza();
                zzb = new zzav(context, zzdi.zza(new zzdf(context) { // from class: com.google.android.gms.internal.vision.zzbl
                    private final Context zza;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zza = context;
                    }

                    @Override // com.google.android.gms.internal.vision.zzdf
                    public final Object zza() {
                        return zzbi.zzc(this.zza);
                    }
                }));
                zzi.incrementAndGet();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ boolean zzd() {
        return true;
    }

    abstract T zza(Object obj);

    public static void zzb(Context context) {
        if (zzb != null) {
            return;
        }
        synchronized (zza) {
            if (zzb == null) {
                zza(context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza() {
        zzi.incrementAndGet();
    }

    private zzbi(zzbo zzboVar, String str, T t, boolean z) {
        this.zzj = -1;
        if (zzboVar.zza == null && zzboVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (zzboVar.zza != null && zzboVar.zzb != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.zzf = zzboVar;
        this.zzg = str;
        this.zzh = t;
        this.zzl = z;
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.zzg);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    public final T zzc() {
        T zza2;
        if (!this.zzl) {
            zzde.zzb(zze.zza(this.zzg), "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error.");
        }
        int i = zzi.get();
        if (this.zzj < i) {
            synchronized (this) {
                if (this.zzj < i) {
                    zzbr zzbrVar = zzb;
                    zzde.zzb(zzbrVar != null, "Must call PhenotypeFlag.init() first");
                    if (!this.zzf.zzf ? (zza2 = zza(zzbrVar)) == null && (zza2 = zzb(zzbrVar)) == null : (zza2 = zzb(zzbrVar)) == null && (zza2 = zza(zzbrVar)) == null) {
                        zza2 = this.zzh;
                    }
                    zzcy<zzbe> zza3 = zzbrVar.zzb().zza();
                    if (zza3.zza()) {
                        String zza4 = zza3.zzb().zza(this.zzf.zzb, this.zzf.zza, this.zzf.zzd, this.zzg);
                        zza2 = zza4 == null ? this.zzh : zza((Object) zza4);
                    }
                    this.zzk = zza2;
                    this.zzj = i;
                }
            }
        }
        return this.zzk;
    }

    @Nullable
    private final T zza(zzbr zzbrVar) {
        zzay zza2;
        Object zza3;
        boolean z = false;
        if (!this.zzf.zzg) {
            String str = (String) zzbd.zza(zzbrVar.zza()).zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
            if (str != null && zzaq.zzb.matcher(str).matches()) {
                z = true;
            }
        }
        if (!z) {
            if (this.zzf.zzb != null) {
                if (!zzbg.zza(zzbrVar.zza(), this.zzf.zzb)) {
                    zza2 = null;
                } else if (this.zzf.zzh) {
                    ContentResolver contentResolver = zzbrVar.zza().getContentResolver();
                    String lastPathSegment = this.zzf.zzb.getLastPathSegment();
                    String packageName = zzbrVar.zza().getPackageName();
                    StringBuilder sb = new StringBuilder(String.valueOf(lastPathSegment).length() + 1 + String.valueOf(packageName).length());
                    sb.append(lastPathSegment);
                    sb.append("#");
                    sb.append(packageName);
                    zza2 = zzau.zza(contentResolver, zzbj.zza(sb.toString()));
                } else {
                    zza2 = zzau.zza(zzbrVar.zza().getContentResolver(), this.zzf.zzb);
                }
            } else {
                zza2 = zzbq.zza(zzbrVar.zza(), this.zzf.zza);
            }
            if (zza2 != null && (zza3 = zza2.zza(zzb())) != null) {
                return zza(zza3);
            }
        } else if (Log.isLoggable("PhenotypeFlag", 3)) {
            String valueOf = String.valueOf(zzb());
            Log.d("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        }
        return null;
    }

    @Nullable
    private final T zzb(zzbr zzbrVar) {
        if (!this.zzf.zze && (this.zzf.zzi == null || this.zzf.zzi.zza(zzbrVar.zza()).booleanValue())) {
            Object zza2 = zzbd.zza(zzbrVar.zza()).zza(this.zzf.zze ? null : zza(this.zzf.zzc));
            if (zza2 != null) {
                return zza(zza2);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> zzbi<T> zzb(zzbo zzboVar, String str, T t, zzbp<T> zzbpVar, boolean z) {
        return new zzbm(zzboVar, str, t, true, zzbpVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ zzcy zzc(Context context) {
        new zzbh();
        return zzbh.zza(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbi(zzbo zzboVar, String str, Object obj, boolean z, zzbn zzbnVar) {
        this(zzboVar, str, obj, z);
    }
}
