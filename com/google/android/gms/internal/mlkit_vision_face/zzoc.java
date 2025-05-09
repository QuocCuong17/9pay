package com.google.android.gms.internal.mlkit_vision_face;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzoc {
    private static zzbn zza;
    private static final zzbp zzb = zzbp.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzob zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzoc(Context context, final SharedPrefManager sharedPrefManager, zzob zzobVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzobVar;
        zzoo.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_face.zznv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzoc.this.zzb();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_face.zznw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzbp zzbpVar = zzb;
        this.zzj = zzbpVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzbpVar.get(str)) : -1;
    }

    static long zza(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    private static synchronized zzbn zzi() {
        synchronized (zzoc.class) {
            zzbn zzbnVar = zza;
            if (zzbnVar != null) {
                return zzbnVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzbk zzbkVar = new zzbk();
            for (int i = 0; i < locales.size(); i++) {
                zzbkVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzbn zzc = zzbkVar.zzc();
            zza = zzc;
            return zzc;
        }
    }

    private final String zzj() {
        if (this.zzg.isSuccessful()) {
            return (String) this.zzg.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    private final boolean zzk(zzkt zzktVar, long j, long j2) {
        return this.zzk.get(zzktVar) == null || j - ((Long) this.zzk.get(zzktVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String zzb() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zznr zznrVar, zzkt zzktVar, String str) {
        String mlSdkInstanceId;
        zznrVar.zzb(zzktVar);
        String zzd = zznrVar.zzd();
        zzmt zzmtVar = new zzmt();
        zzmtVar.zzb(this.zzc);
        zzmtVar.zzc(this.zzd);
        zzmtVar.zzh(zzi());
        zzmtVar.zzg(true);
        zzmtVar.zzl(zzd);
        zzmtVar.zzj(str);
        if (this.zzh.isSuccessful()) {
            mlSdkInstanceId = (String) this.zzh.getResult();
        } else {
            mlSdkInstanceId = this.zzf.getMlSdkInstanceId();
        }
        zzmtVar.zzi(mlSdkInstanceId);
        zzmtVar.zzd(10);
        zzmtVar.zzk(Integer.valueOf(this.zzj));
        zznrVar.zzc(zzmtVar);
        this.zze.zza(zznrVar);
    }

    public final void zzd(zznr zznrVar, zzkt zzktVar) {
        zze(zznrVar, zzktVar, zzj());
    }

    public final void zze(final zznr zznrVar, final zzkt zzktVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_face.zznx
            @Override // java.lang.Runnable
            public final void run() {
                zzoc.this.zzc(zznrVar, zzktVar, str);
            }
        });
    }

    public final void zzf(zzoa zzoaVar, zzkt zzktVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzk(zzktVar, elapsedRealtime, 30L)) {
            this.zzk.put(zzktVar, Long.valueOf(elapsedRealtime));
            zze(zzoaVar.zza(), zzktVar, zzj());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzkt zzktVar, com.google.mlkit.vision.face.internal.zzg zzgVar) {
        zzbs zzbsVar = (zzbs) this.zzl.get(zzktVar);
        if (zzbsVar != null) {
            for (Object obj : zzbsVar.zzq()) {
                ArrayList arrayList = new ArrayList(zzbsVar.zzc(obj));
                Collections.sort(arrayList);
                zzjt zzjtVar = new zzjt();
                Iterator it = arrayList.iterator();
                long j = 0;
                while (it.hasNext()) {
                    j += ((Long) it.next()).longValue();
                }
                zzjtVar.zza(Long.valueOf(j / arrayList.size()));
                zzjtVar.zzc(Long.valueOf(zza(arrayList, 100.0d)));
                zzjtVar.zzf(Long.valueOf(zza(arrayList, 75.0d)));
                zzjtVar.zzd(Long.valueOf(zza(arrayList, 50.0d)));
                zzjtVar.zzb(Long.valueOf(zza(arrayList, 25.0d)));
                zzjtVar.zze(Long.valueOf(zza(arrayList, 0.0d)));
                zze(zzgVar.zza(obj, arrayList.size(), zzjtVar.zzg()), zzktVar, zzj());
            }
            this.zzl.remove(zzktVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(final zzkt zzktVar, Object obj, long j, final com.google.mlkit.vision.face.internal.zzg zzgVar) {
        if (!this.zzl.containsKey(zzktVar)) {
            this.zzl.put(zzktVar, zzas.zzr());
        }
        ((zzbs) this.zzl.get(zzktVar)).zzo(obj, Long.valueOf(j));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzk(zzktVar, elapsedRealtime, 30L)) {
            this.zzk.put(zzktVar, Long.valueOf(elapsedRealtime));
            final byte[] bArr = null;
            MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzktVar, zzgVar, bArr) { // from class: com.google.android.gms.internal.mlkit_vision_face.zznz
                public final /* synthetic */ zzkt zzb;
                public final /* synthetic */ com.google.mlkit.vision.face.internal.zzg zzc;

                @Override // java.lang.Runnable
                public final void run() {
                    zzoc.this.zzg(this.zzb, this.zzc);
                }
            });
        }
    }
}
