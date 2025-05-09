package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrj;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzek extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzek(zzgd zzgdVar, long j) {
        super(zzgdVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(1:3)(6:66|67|(1:69)(2:84|(1:86))|70|71|(20:73|(1:75)(1:82)|77|78|5|(1:65)(1:9)|10|11|13|(1:15)|16|17|(1:19)(1:54)|20|(3:22|(1:24)(1:26)|25)|(3:28|(1:30)(1:33)|31)|34|(3:36|(1:38)(3:45|(3:48|(1:50)(1:51)|46)|52)|(2:40|41)(2:43|44))|53|(0)(0)))|4|5|(1:7)|65|10|11|13|(0)|16|17|(0)(0)|20|(0)|(0)|34|(0)|53|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01db, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01dc, code lost:
    
        r11.zzt.zzaA().zzd().zzc("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzet.zzn(r0), r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0194 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:17:0x0172, B:20:0x018c, B:22:0x0194, B:25:0x01b2, B:26:0x01ae, B:28:0x01bc, B:30:0x01d2, B:31:0x01d7, B:33:0x01d5), top: B:16:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01bc A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:17:0x0172, B:20:0x018c, B:22:0x0194, B:25:0x01b2, B:26:0x01ae, B:28:0x01bc, B:30:0x01d2, B:31:0x01d7, B:33:0x01d5), top: B:16:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00b5  */
    @Override // com.google.android.gms.measurement.internal.zzf
    @EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void zzd() {
        String str;
        String str2;
        PackageInfo packageInfo;
        boolean z;
        int zza;
        List zzp;
        String zzc;
        String packageName = this.zzt.zzaw().getPackageName();
        PackageManager packageManager = this.zzt.zzaw().getPackageManager();
        String str3 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int i = Integer.MIN_VALUE;
        String str4 = "";
        String str5 = "unknown";
        if (packageManager == null) {
            this.zzt.zzaA().zzd().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzet.zzn(packageName));
        } else {
            try {
                str5 = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException unused) {
                this.zzt.zzaA().zzd().zzb("Error retrieving app installer package name. appId", zzet.zzn(packageName));
            }
            if (str5 == null) {
                str5 = "manual_install";
            } else if ("com.android.vending".equals(str5)) {
                str5 = "";
            }
            try {
                packageInfo = packageManager.getPackageInfo(this.zzt.zzaw().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            if (packageInfo != null) {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                str2 = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                try {
                    str3 = packageInfo.versionName;
                    i = packageInfo.versionCode;
                } catch (PackageManager.NameNotFoundException unused3) {
                    str = str3;
                    str3 = str2;
                    this.zzt.zzaA().zzd().zzc("Error retrieving package info. appId, appName", zzet.zzn(packageName), str3);
                    str2 = str3;
                    str3 = str;
                    this.zza = packageName;
                    this.zzd = str5;
                    this.zzb = str3;
                    this.zzc = i;
                    this.zze = str2;
                    this.zzf = 0L;
                    if (TextUtils.isEmpty(this.zzt.zzw())) {
                    }
                    zza = this.zzt.zza();
                    switch (zza) {
                    }
                    this.zzk = "";
                    this.zzl = "";
                    this.zzt.zzay();
                    if (z) {
                    }
                    zzc = zziq.zzc(this.zzt.zzaw(), "google_app_id", this.zzt.zzz());
                    if (!TextUtils.isEmpty(zzc)) {
                    }
                    this.zzk = str4;
                    if (!TextUtils.isEmpty(zzc)) {
                    }
                    if (zza == 0) {
                    }
                    this.zzh = null;
                    this.zzt.zzay();
                    zzp = this.zzt.zzf().zzp("analytics.safelisted_events");
                    if (zzp != null) {
                    }
                    this.zzh = zzp;
                    if (packageManager == null) {
                    }
                }
                this.zza = packageName;
                this.zzd = str5;
                this.zzb = str3;
                this.zzc = i;
                this.zze = str2;
                this.zzf = 0L;
                z = TextUtils.isEmpty(this.zzt.zzw()) && "am".equals(this.zzt.zzx());
                zza = this.zzt.zza();
                switch (zza) {
                    case 0:
                        this.zzt.zzaA().zzj().zza("App measurement collection enabled");
                        break;
                    case 1:
                        this.zzt.zzaA().zzi().zza("App measurement deactivated via the manifest");
                        break;
                    case 2:
                        this.zzt.zzaA().zzj().zza("App measurement deactivated via the init parameters");
                        break;
                    case 3:
                        this.zzt.zzaA().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                        break;
                    case 4:
                        this.zzt.zzaA().zzi().zza("App measurement disabled via the manifest");
                        break;
                    case 5:
                        this.zzt.zzaA().zzj().zza("App measurement disabled via the init parameters");
                        break;
                    case 6:
                        this.zzt.zzaA().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                        break;
                    case 7:
                        this.zzt.zzaA().zzi().zza("App measurement disabled via the global data collection setting");
                        break;
                    default:
                        this.zzt.zzaA().zzi().zza("App measurement disabled due to denied storage consent");
                        break;
                }
                this.zzk = "";
                this.zzl = "";
                this.zzt.zzay();
                if (z) {
                    this.zzl = this.zzt.zzw();
                }
                zzc = zziq.zzc(this.zzt.zzaw(), "google_app_id", this.zzt.zzz());
                if (!TextUtils.isEmpty(zzc)) {
                    str4 = zzc;
                }
                this.zzk = str4;
                if (!TextUtils.isEmpty(zzc)) {
                    Context zzaw = this.zzt.zzaw();
                    String zzz = this.zzt.zzz();
                    Preconditions.checkNotNull(zzaw);
                    Resources resources = zzaw.getResources();
                    if (TextUtils.isEmpty(zzz)) {
                        zzz = zzfv.zza(zzaw);
                    }
                    this.zzl = zzfv.zzb("admob_app_id", resources, zzz);
                }
                if (zza == 0) {
                    this.zzt.zzaA().zzj().zzc("App measurement enabled for app package, google app id", this.zza, TextUtils.isEmpty(this.zzk) ? this.zzl : this.zzk);
                }
                this.zzh = null;
                this.zzt.zzay();
                zzp = this.zzt.zzf().zzp("analytics.safelisted_events");
                if (zzp != null) {
                    if (zzp.isEmpty()) {
                        this.zzt.zzaA().zzl().zza("Safelisted event list is empty. Ignoring");
                    } else {
                        Iterator it = zzp.iterator();
                        while (it.hasNext()) {
                            if (!this.zzt.zzv().zzac("safelisted event", (String) it.next())) {
                            }
                        }
                    }
                    if (packageManager == null) {
                        this.zzj = InstantApps.isInstantApp(this.zzt.zzaw()) ? 1 : 0;
                        return;
                    } else {
                        this.zzj = 0;
                        return;
                    }
                }
                this.zzh = zzp;
                if (packageManager == null) {
                }
            }
        }
        str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        this.zza = packageName;
        this.zzd = str5;
        this.zzb = str3;
        this.zzc = i;
        this.zze = str2;
        this.zzf = 0L;
        if (TextUtils.isEmpty(this.zzt.zzw())) {
        }
        zza = this.zzt.zza();
        switch (zza) {
        }
        this.zzk = "";
        this.zzl = "";
        this.zzt.zzay();
        if (z) {
        }
        zzc = zziq.zzc(this.zzt.zzaw(), "google_app_id", this.zzt.zzz());
        if (!TextUtils.isEmpty(zzc)) {
        }
        this.zzk = str4;
        if (!TextUtils.isEmpty(zzc)) {
        }
        if (zza == 0) {
        }
        this.zzh = null;
        this.zzt.zzay();
        zzp = this.zzt.zzf().zzp("analytics.safelisted_events");
        if (zzp != null) {
        }
        this.zzh = zzp;
        if (packageManager == null) {
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzh() {
        zza();
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzi() {
        zza();
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0194  */
    /* JADX WARN: Type inference failed for: r9v48, types: [com.google.android.gms.measurement.internal.zzgw, com.google.android.gms.measurement.internal.zzlp] */
    /* JADX WARN: Type inference failed for: r9v49, types: [com.google.android.gms.measurement.internal.zzgw] */
    /* JADX WARN: Type inference failed for: r9v53 */
    /* JADX WARN: Type inference failed for: r9v58 */
    /* JADX WARN: Type inference failed for: r9v59 */
    /* JADX WARN: Type inference failed for: r9v60 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzq zzj(String str) {
        String str2;
        Class<?> loadClass;
        Object invoke;
        long zza;
        String str3;
        long min;
        String str4;
        long j;
        int i;
        int i2;
        long j2;
        ApplicationInfo applicationInfo;
        zzg();
        String zzl = zzl();
        String zzm = zzm();
        zza();
        String str5 = this.zzb;
        zza();
        long j3 = this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str6 = this.zzd;
        this.zzt.zzf().zzh();
        zza();
        zzg();
        long j4 = this.zzf;
        if (j4 == 0) {
            ?? zzv = this.zzt.zzv();
            Context zzaw = this.zzt.zzaw();
            String packageName = this.zzt.zzaw().getPackageName();
            zzv.zzg();
            Preconditions.checkNotNull(zzaw);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = zzaw.getPackageManager();
            MessageDigest zzF = zzlp.zzF();
            long j5 = -1;
            if (zzF == null) {
                zzv.zzt.zzaA().zzd().zza("Could not get MD5 instance");
            } else {
                if (packageManager != null) {
                    try {
                        if (zzv.zzah(zzaw, packageName)) {
                            j5 = 0;
                            zzv = zzv;
                        } else {
                            PackageInfo packageInfo = Wrappers.packageManager(zzaw).getPackageInfo(zzv.zzt.zzaw().getPackageName(), 64);
                            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                                zzv.zzt.zzaA().zzk().zza("Could not get signatures");
                                zzv = zzv;
                            } else {
                                long zzp = zzlp.zzp(zzF.digest(packageInfo.signatures[0].toByteArray()));
                                j5 = zzp;
                                zzv = zzp;
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        zzv.zzt.zzaA().zzd().zzb("Package name not found", e);
                    }
                }
                j4 = 0;
                this.zzf = j4;
            }
            j4 = j5;
            this.zzf = j4;
        }
        long j6 = j4;
        boolean zzJ = this.zzt.zzJ();
        boolean z = !this.zzt.zzm().zzl;
        zzg();
        if (this.zzt.zzJ()) {
            zzrj.zzc();
            if (!this.zzt.zzf().zzs(null, zzeg.zzac)) {
                try {
                    loadClass = this.zzt.zzaw().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                } catch (ClassNotFoundException unused) {
                }
                if (loadClass != null) {
                    try {
                        invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzt.zzaw());
                    } catch (Exception unused2) {
                        this.zzt.zzaA().zzm().zza("Failed to obtain Firebase Analytics instance");
                    }
                    if (invoke != null) {
                        try {
                            str2 = (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                        } catch (Exception unused3) {
                            this.zzt.zzaA().zzl().zza("Failed to retrieve Firebase Instance Id");
                        }
                        zzgd zzgdVar = this.zzt;
                        zza = zzgdVar.zzm().zzc.zza();
                        if (zza == 0) {
                            str3 = zzl;
                            min = zzgdVar.zzc;
                        } else {
                            str3 = zzl;
                            min = Math.min(zzgdVar.zzc, zza);
                        }
                        zza();
                        int i3 = this.zzj;
                        boolean zzr = this.zzt.zzf().zzr();
                        zzfi zzm2 = this.zzt.zzm();
                        zzm2.zzg();
                        boolean z2 = zzm2.zza().getBoolean("deferred_analytics_collection", false);
                        zza();
                        String str7 = this.zzl;
                        Boolean valueOf = this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null ? null : Boolean.valueOf(!r2.booleanValue());
                        long j7 = this.zzg;
                        List list = this.zzh;
                        String zzi = this.zzt.zzm().zzc().zzi();
                        if (this.zzi == null) {
                            this.zzi = this.zzt.zzv().zzC();
                        }
                        String str8 = this.zzi;
                        zzqu.zzc();
                        if (this.zzt.zzf().zzs(null, zzeg.zzan)) {
                            zzg();
                            if (this.zzn != 0) {
                                long currentTimeMillis = this.zzt.zzax().currentTimeMillis() - this.zzn;
                                if (this.zzm != null && currentTimeMillis > 86400000 && this.zzo == null) {
                                    zzo();
                                }
                            }
                            if (this.zzm == null) {
                                zzo();
                            }
                            str4 = this.zzm;
                        } else {
                            str4 = null;
                        }
                        zzag zzf = this.zzt.zzf();
                        zzgd zzgdVar2 = zzf.zzt;
                        Boolean zzk = zzf.zzk("google_analytics_sgtm_upload_enabled");
                        boolean booleanValue = zzk == null ? false : zzk.booleanValue();
                        zzpz.zzc();
                        if (this.zzt.zzf().zzs(null, zzeg.zzaD)) {
                            zzlp zzv2 = this.zzt.zzv();
                            String zzl2 = zzl();
                            if (zzv2.zzt.zzaw().getPackageManager() == null) {
                                j2 = 0;
                            } else {
                                try {
                                    i = 0;
                                    try {
                                        applicationInfo = Wrappers.packageManager(zzv2.zzt.zzaw()).getApplicationInfo(zzl2, 0);
                                    } catch (PackageManager.NameNotFoundException unused4) {
                                        zzv2.zzt.zzay();
                                        zzv2.zzt.zzaA().zzi().zzb("PackageManager failed to find running app: app_id", zzl2);
                                        i2 = i;
                                        j2 = i2;
                                        j = j2;
                                        return new zzq(str3, zzm, str5, j3, str6, 79000L, j6, str, zzJ, z, str2, 0L, min, i3, zzr, z2, str7, valueOf, j7, list, (String) null, zzi, str8, str4, booleanValue, j);
                                    }
                                } catch (PackageManager.NameNotFoundException unused5) {
                                    i = 0;
                                }
                                if (applicationInfo != null) {
                                    i2 = applicationInfo.targetSdkVersion;
                                    j2 = i2;
                                }
                                i2 = i;
                                j2 = i2;
                            }
                            j = j2;
                        } else {
                            j = 0;
                        }
                        return new zzq(str3, zzm, str5, j3, str6, 79000L, j6, str, zzJ, z, str2, 0L, min, i3, zzr, z2, str7, valueOf, j7, list, (String) null, zzi, str8, str4, booleanValue, j);
                    }
                    str2 = null;
                    zzgd zzgdVar3 = this.zzt;
                    zza = zzgdVar3.zzm().zzc.zza();
                    if (zza == 0) {
                    }
                    zza();
                    int i32 = this.zzj;
                    boolean zzr2 = this.zzt.zzf().zzr();
                    zzfi zzm22 = this.zzt.zzm();
                    zzm22.zzg();
                    boolean z22 = zzm22.zza().getBoolean("deferred_analytics_collection", false);
                    zza();
                    String str72 = this.zzl;
                    if (this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null) {
                    }
                    long j72 = this.zzg;
                    List list2 = this.zzh;
                    String zzi2 = this.zzt.zzm().zzc().zzi();
                    if (this.zzi == null) {
                    }
                    String str82 = this.zzi;
                    zzqu.zzc();
                    if (this.zzt.zzf().zzs(null, zzeg.zzan)) {
                    }
                    zzag zzf2 = this.zzt.zzf();
                    zzgd zzgdVar22 = zzf2.zzt;
                    Boolean zzk2 = zzf2.zzk("google_analytics_sgtm_upload_enabled");
                    if (zzk2 == null) {
                    }
                    zzpz.zzc();
                    if (this.zzt.zzf().zzs(null, zzeg.zzaD)) {
                    }
                    return new zzq(str3, zzm, str5, j3, str6, 79000L, j6, str, zzJ, z, str2, 0L, min, i32, zzr2, z22, str72, valueOf, j72, list2, (String) null, zzi2, str82, str4, booleanValue, j);
                }
            } else {
                this.zzt.zzaA().zzj().zza("Disabled IID for tests.");
            }
        }
        str2 = null;
        zzgd zzgdVar32 = this.zzt;
        zza = zzgdVar32.zzm().zzc.zza();
        if (zza == 0) {
        }
        zza();
        int i322 = this.zzj;
        boolean zzr22 = this.zzt.zzf().zzr();
        zzfi zzm222 = this.zzt.zzm();
        zzm222.zzg();
        boolean z222 = zzm222.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        String str722 = this.zzl;
        if (this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null) {
        }
        long j722 = this.zzg;
        List list22 = this.zzh;
        String zzi22 = this.zzt.zzm().zzc().zzi();
        if (this.zzi == null) {
        }
        String str822 = this.zzi;
        zzqu.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzan)) {
        }
        zzag zzf22 = this.zzt.zzf();
        zzgd zzgdVar222 = zzf22.zzt;
        Boolean zzk22 = zzf22.zzk("google_analytics_sgtm_upload_enabled");
        if (zzk22 == null) {
        }
        zzpz.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzaD)) {
        }
        return new zzq(str3, zzm, str5, j3, str6, 79000L, j6, str, zzJ, z, str2, 0L, min, i322, zzr22, z222, str722, valueOf, j722, list22, (String) null, zzi22, str822, str4, booleanValue, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzk() {
        zza();
        return this.zzl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzm() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List zzn() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo() {
        String format;
        zzg();
        if (!this.zzt.zzm().zzc().zzj(zzha.ANALYTICS_STORAGE)) {
            this.zzt.zzaA().zzc().zza("Analytics Storage consent is not granted");
            format = null;
        } else {
            byte[] bArr = new byte[16];
            this.zzt.zzv().zzG().nextBytes(bArr);
            format = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        }
        zzer zzc = this.zzt.zzaA().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = format == null ? "null" : "not null";
        zzc.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzm = format;
        this.zzn = this.zzt.zzax().currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
