package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzpk;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzpq;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrd;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.mlkit.common.MlKitException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzlh implements zzgy {
    private static volatile zzlh zzb;
    private long zzA;
    private final Map zzB;
    private final Map zzC;
    private zzir zzD;
    private String zzE;
    long zza;
    private final zzfu zzc;
    private final zzez zzd;
    private zzak zze;
    private zzfb zzf;
    private zzks zzg;
    private zzaa zzh;
    private final zzlj zzi;
    private zzip zzj;
    private zzkb zzk;
    private final zzkw zzl;
    private zzfl zzm;
    private final zzgd zzn;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;
    private boolean zzo = false;
    private final zzlo zzF = new zzlc(this);

    zzlh(zzli zzliVar, zzgd zzgdVar) {
        Preconditions.checkNotNull(zzliVar);
        this.zzn = zzgd.zzp(zzliVar.zza, null, null);
        this.zzA = -1L;
        this.zzl = new zzkw(this);
        zzlj zzljVar = new zzlj(this);
        zzljVar.zzX();
        this.zzi = zzljVar;
        zzez zzezVar = new zzez(this);
        zzezVar.zzX();
        this.zzd = zzezVar;
        zzfu zzfuVar = new zzfu(this);
        zzfuVar.zzX();
        this.zzc = zzfuVar;
        this.zzB = new HashMap();
        this.zzC = new HashMap();
        zzaB().zzp(new zzkx(this, zzliVar));
    }

    static final void zzaa(com.google.android.gms.internal.measurement.zzfs zzfsVar, int i, String str) {
        List zzp = zzfsVar.zzp();
        for (int i2 = 0; i2 < zzp.size(); i2++) {
            if ("_err".equals(((com.google.android.gms.internal.measurement.zzfx) zzp.get(i2)).zzg())) {
                return;
            }
        }
        com.google.android.gms.internal.measurement.zzfw zze = com.google.android.gms.internal.measurement.zzfx.zze();
        zze.zzj("_err");
        zze.zzi(Long.valueOf(i).longValue());
        com.google.android.gms.internal.measurement.zzfx zzfxVar = (com.google.android.gms.internal.measurement.zzfx) zze.zzaD();
        com.google.android.gms.internal.measurement.zzfw zze2 = com.google.android.gms.internal.measurement.zzfx.zze();
        zze2.zzj("_ev");
        zze2.zzk(str);
        com.google.android.gms.internal.measurement.zzfx zzfxVar2 = (com.google.android.gms.internal.measurement.zzfx) zze2.zzaD();
        zzfsVar.zzf(zzfxVar);
        zzfsVar.zzf(zzfxVar2);
    }

    static final void zzab(com.google.android.gms.internal.measurement.zzfs zzfsVar, String str) {
        List zzp = zzfsVar.zzp();
        for (int i = 0; i < zzp.size(); i++) {
            if (str.equals(((com.google.android.gms.internal.measurement.zzfx) zzp.get(i)).zzg())) {
                zzfsVar.zzh(i);
                return;
            }
        }
    }

    private final zzq zzac(String str) {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzj = zzakVar.zzj(str);
        if (zzj == null || TextUtils.isEmpty(zzj.zzy())) {
            zzaA().zzc().zzb("No app data available; dropping", str);
            return null;
        }
        Boolean zzad = zzad(zzj);
        if (zzad == null || zzad.booleanValue()) {
            String zzA = zzj.zzA();
            String zzy = zzj.zzy();
            long zzb2 = zzj.zzb();
            String zzx = zzj.zzx();
            long zzm = zzj.zzm();
            long zzj2 = zzj.zzj();
            boolean zzan = zzj.zzan();
            String zzz = zzj.zzz();
            zzj.zza();
            return new zzq(str, zzA, zzy, zzb2, zzx, zzm, zzj2, (String) null, zzan, false, zzz, 0L, 0L, 0, zzj.zzam(), false, zzj.zzt(), zzj.zzs(), zzj.zzk(), zzj.zzE(), (String) null, zzq(str).zzi(), "", (String) null, zzj.zzap(), zzj.zzr());
        }
        zzaA().zzd().zzb("App version does not match; dropping. appId", zzet.zzn(str));
        return null;
    }

    private final Boolean zzad(zzh zzhVar) {
        try {
            if (zzhVar.zzb() == -2147483648L) {
                String str = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzhVar.zzv(), 0).versionName;
                String zzy = zzhVar.zzy();
                if (zzy != null && zzy.equals(str)) {
                    return true;
                }
            } else {
                if (zzhVar.zzb() == Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzhVar.zzv(), 0).versionCode) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final void zzae() {
        zzaB().zzg();
        if (this.zzt || this.zzu || this.zzv) {
            zzaA().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzt), Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv));
            return;
        }
        zzaA().zzj().zza("Stopping uploading service(s)");
        List list = this.zzq;
        if (list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        ((List) Preconditions.checkNotNull(this.zzq)).clear();
    }

    private final void zzaf(com.google.android.gms.internal.measurement.zzgc zzgcVar, long j, boolean z) {
        zzlm zzlmVar;
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        String str = true != z ? "_lte" : "_se";
        zzlm zzp = zzakVar.zzp(zzgcVar.zzaq(), str);
        if (zzp == null || zzp.zze == null) {
            zzlmVar = new zzlm(zzgcVar.zzaq(), "auto", str, zzax().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzlmVar = new zzlm(zzgcVar.zzaq(), "auto", str, zzax().currentTimeMillis(), Long.valueOf(((Long) zzp.zze).longValue() + j));
        }
        com.google.android.gms.internal.measurement.zzgl zzd = com.google.android.gms.internal.measurement.zzgm.zzd();
        zzd.zzf(str);
        zzd.zzg(zzax().currentTimeMillis());
        zzd.zze(((Long) zzlmVar.zze).longValue());
        com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) zzd.zzaD();
        int zza = zzlj.zza(zzgcVar, str);
        if (zza < 0) {
            zzgcVar.zzm(zzgmVar);
        } else {
            zzgcVar.zzan(zza, zzgmVar);
        }
        if (j > 0) {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzakVar2.zzL(zzlmVar);
            zzaA().zzj().zzc("Updated engagement user property. scope, value", true != z ? "lifetime" : "session-scoped", zzlmVar.zze);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0238  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzag() {
        long max;
        long j;
        zzaB().zzg();
        zzB();
        if (this.zza > 0) {
            long abs = 3600000 - Math.abs(zzax().elapsedRealtime() - this.zza);
            if (abs > 0) {
                zzaA().zzj().zzb("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                zzl().zzc();
                zzks zzksVar = this.zzg;
                zzal(zzksVar);
                zzksVar.zza();
                return;
            }
            this.zza = 0L;
        }
        if (!this.zzn.zzM() || !zzai()) {
            zzaA().zzj().zza("Nothing to upload or uploading impossible");
            zzl().zzc();
            zzks zzksVar2 = this.zzg;
            zzal(zzksVar2);
            zzksVar2.zza();
            return;
        }
        long currentTimeMillis = zzax().currentTimeMillis();
        zzg();
        long max2 = Math.max(0L, ((Long) zzeg.zzA.zza(null)).longValue());
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        boolean z = true;
        if (!zzakVar.zzH()) {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            if (!zzakVar2.zzG()) {
                z = false;
            }
        }
        if (z) {
            String zzl = zzg().zzl();
            if (TextUtils.isEmpty(zzl) || ".none.".equals(zzl)) {
                zzg();
                max = Math.max(0L, ((Long) zzeg.zzu.zza(null)).longValue());
            } else {
                zzg();
                max = Math.max(0L, ((Long) zzeg.zzv.zza(null)).longValue());
            }
        } else {
            zzg();
            max = Math.max(0L, ((Long) zzeg.zzt.zza(null)).longValue());
        }
        long zza = this.zzk.zzc.zza();
        long zza2 = this.zzk.zzd.zza();
        zzak zzakVar3 = this.zze;
        zzal(zzakVar3);
        boolean z2 = z;
        long zzd = zzakVar3.zzd();
        zzak zzakVar4 = this.zze;
        zzal(zzakVar4);
        long max3 = Math.max(zzd, zzakVar4.zze());
        if (max3 != 0) {
            long abs2 = currentTimeMillis - Math.abs(max3 - currentTimeMillis);
            long abs3 = currentTimeMillis - Math.abs(zza - currentTimeMillis);
            long abs4 = currentTimeMillis - Math.abs(zza2 - currentTimeMillis);
            j = abs2 + max2;
            long max4 = Math.max(abs3, abs4);
            if (z2 && max4 > 0) {
                j = Math.min(abs2, max4) + max;
            }
            zzlj zzljVar = this.zzi;
            zzal(zzljVar);
            if (!zzljVar.zzx(max4, max)) {
                j = max4 + max;
            }
            if (abs4 != 0 && abs4 >= abs2) {
                int i = 0;
                while (true) {
                    zzg();
                    if (i >= Math.min(20, Math.max(0, ((Integer) zzeg.zzC.zza(null)).intValue()))) {
                        break;
                    }
                    zzg();
                    j += Math.max(0L, ((Long) zzeg.zzB.zza(null)).longValue()) * (1 << i);
                    if (j > abs4) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (j == 0) {
                zzez zzezVar = this.zzd;
                zzal(zzezVar);
                if (zzezVar.zza()) {
                    long zza3 = this.zzk.zzb.zza();
                    zzg();
                    long max5 = Math.max(0L, ((Long) zzeg.zzr.zza(null)).longValue());
                    zzlj zzljVar2 = this.zzi;
                    zzal(zzljVar2);
                    if (!zzljVar2.zzx(zza3, max5)) {
                        j = Math.max(j, zza3 + max5);
                    }
                    zzl().zzc();
                    long currentTimeMillis2 = j - zzax().currentTimeMillis();
                    if (currentTimeMillis2 <= 0) {
                        zzg();
                        currentTimeMillis2 = Math.max(0L, ((Long) zzeg.zzw.zza(null)).longValue());
                        this.zzk.zzc.zzb(zzax().currentTimeMillis());
                    }
                    zzaA().zzj().zzb("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
                    zzks zzksVar3 = this.zzg;
                    zzal(zzksVar3);
                    zzksVar3.zzd(currentTimeMillis2);
                    return;
                }
                zzaA().zzj().zza("No network");
                zzl().zzb();
                zzks zzksVar4 = this.zzg;
                zzal(zzksVar4);
                zzksVar4.zza();
                return;
            }
            zzaA().zzj().zza("Next upload time is 0");
            zzl().zzc();
            zzks zzksVar5 = this.zzg;
            zzal(zzksVar5);
            zzksVar5.zza();
            return;
        }
        j = 0;
        if (j == 0) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:366:0x0b36, code lost:
    
        if (r10 > (com.google.android.gms.measurement.internal.zzag.zzA() + r8)) goto L354;
     */
    /* JADX WARN: Removed duplicated region for block: B:278:0x07d7 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0820 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0843 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x08ba  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x08c4 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x08f0 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:333:0x08bc  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0b26 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0bad A[Catch: all -> 0x0ccf, TRY_LEAVE, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0bc9 A[Catch: SQLiteException -> 0x0be1, all -> 0x0ccf, TRY_LEAVE, TryCatch #1 {SQLiteException -> 0x0be1, blocks: (B:374:0x0bba, B:376:0x0bc9), top: B:373:0x0bba, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0383 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0447 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0489 A[Catch: all -> 0x0ccf, TryCatch #4 {all -> 0x0ccf, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:25:0x04ee, B:26:0x00f7, B:28:0x0105, B:31:0x0125, B:33:0x012b, B:35:0x013d, B:37:0x014b, B:39:0x015b, B:41:0x0168, B:46:0x016d, B:49:0x0186, B:58:0x0383, B:59:0x038f, B:62:0x0399, B:66:0x03bc, B:67:0x03ab, B:76:0x043b, B:78:0x0447, B:81:0x045c, B:83:0x046d, B:85:0x0479, B:87:0x04da, B:91:0x0489, B:93:0x0495, B:96:0x04aa, B:98:0x04bb, B:100:0x04c7, B:102:0x03c4, B:104:0x03d0, B:106:0x03dc, B:110:0x0421, B:111:0x03f9, B:114:0x040b, B:116:0x0411, B:118:0x041b, B:123:0x01c0, B:126:0x01ca, B:128:0x01d8, B:130:0x021f, B:131:0x01f5, B:133:0x0206, B:140:0x022e, B:142:0x025a, B:143:0x0284, B:145:0x02ba, B:146:0x02c0, B:149:0x02cc, B:151:0x0302, B:152:0x031d, B:154:0x0323, B:156:0x0331, B:158:0x0344, B:159:0x0339, B:167:0x034b, B:170:0x0352, B:171:0x036a, B:178:0x0506, B:180:0x0514, B:182:0x051f, B:184:0x0551, B:185:0x0527, B:187:0x0532, B:189:0x0538, B:191:0x0544, B:193:0x054c, B:200:0x0554, B:201:0x0560, B:204:0x0568, B:207:0x057a, B:208:0x0586, B:210:0x058e, B:211:0x05b3, B:213:0x05d8, B:215:0x05e9, B:217:0x05ef, B:219:0x05fb, B:220:0x062c, B:222:0x0632, B:226:0x0640, B:224:0x0644, B:228:0x0647, B:229:0x064a, B:230:0x0658, B:232:0x065e, B:234:0x066e, B:235:0x0675, B:237:0x0681, B:239:0x0688, B:242:0x068b, B:244:0x06cb, B:245:0x06de, B:247:0x06e4, B:250:0x06fe, B:252:0x0719, B:254:0x0732, B:256:0x0737, B:258:0x073b, B:260:0x073f, B:262:0x0749, B:263:0x0753, B:265:0x0757, B:267:0x075d, B:268:0x076b, B:269:0x0774, B:272:0x09c9, B:273:0x0780, B:338:0x0797, B:276:0x07b3, B:278:0x07d7, B:279:0x07df, B:281:0x07e5, B:285:0x07f7, B:290:0x0820, B:291:0x0843, B:293:0x084f, B:295:0x0864, B:296:0x08a5, B:299:0x08bd, B:301:0x08c4, B:303:0x08d3, B:305:0x08d7, B:307:0x08db, B:309:0x08df, B:310:0x08eb, B:311:0x08f0, B:313:0x08f6, B:315:0x0912, B:316:0x0917, B:317:0x09c6, B:319:0x0931, B:321:0x0939, B:324:0x0960, B:326:0x098e, B:327:0x0998, B:329:0x09aa, B:331:0x09b6, B:332:0x0946, B:336:0x080b, B:342:0x079e, B:344:0x09d4, B:346:0x09e1, B:347:0x09e7, B:348:0x09ef, B:350:0x09f5, B:352:0x0a0b, B:354:0x0a1c, B:355:0x0a90, B:357:0x0a96, B:359:0x0aae, B:362:0x0ab5, B:363:0x0ae4, B:365:0x0b26, B:367:0x0b5b, B:369:0x0b5f, B:370:0x0b6a, B:372:0x0bad, B:374:0x0bba, B:376:0x0bc9, B:380:0x0be3, B:383:0x0bfc, B:384:0x0b38, B:385:0x0abd, B:387:0x0ac9, B:388:0x0acd, B:389:0x0c14, B:390:0x0c2c, B:393:0x0c34, B:395:0x0c39, B:398:0x0c49, B:400:0x0c63, B:401:0x0c7e, B:403:0x0c87, B:404:0x0cab, B:411:0x0c98, B:412:0x0a34, B:414:0x0a3a, B:416:0x0a44, B:417:0x0a4b, B:422:0x0a5b, B:423:0x0a62, B:425:0x0a81, B:426:0x0a88, B:427:0x0a85, B:428:0x0a5f, B:430:0x0a48, B:432:0x0593, B:434:0x0599, B:437:0x0cbd), top: B:2:0x000e, inners: #0, #1, #2, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zzah(String str, long j) {
        int i;
        com.google.android.gms.internal.measurement.zzgc zzgcVar;
        zzak zzakVar;
        com.google.android.gms.internal.measurement.zzgd zzgdVar;
        long currentTimeMillis;
        long zzk;
        ContentValues contentValues;
        long parseLong;
        int zzc;
        long j2;
        SecureRandom secureRandom;
        Long l;
        com.google.android.gms.internal.measurement.zzgc zzgcVar2;
        HashMap hashMap;
        ArrayList arrayList;
        long zzr;
        HashMap hashMap2;
        ArrayList arrayList2;
        com.google.android.gms.internal.measurement.zzgc zzgcVar3;
        String str2;
        int i2;
        int i3;
        com.google.android.gms.internal.measurement.zzfs zzfsVar;
        String str3;
        com.google.android.gms.internal.measurement.zzgc zzgcVar4;
        String str4;
        com.google.android.gms.internal.measurement.zzgc zzgcVar5;
        String str5;
        int i4;
        String str6 = "_npa";
        String str7 = "_ai";
        zzak zzakVar2 = this.zze;
        zzal(zzakVar2);
        zzakVar2.zzw();
        try {
            zzle zzleVar = new zzle(this, null);
            zzak zzakVar3 = this.zze;
            zzal(zzakVar3);
            zzakVar3.zzU(null, j, this.zzA, zzleVar);
            List list = zzleVar.zzc;
            if (list != null && !list.isEmpty()) {
                com.google.android.gms.internal.measurement.zzgc zzgcVar6 = (com.google.android.gms.internal.measurement.zzgc) zzleVar.zza.zzbB();
                zzgcVar6.zzr();
                com.google.android.gms.internal.measurement.zzfs zzfsVar2 = null;
                com.google.android.gms.internal.measurement.zzfs zzfsVar3 = null;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = -1;
                int i9 = -1;
                while (true) {
                    String str8 = "_fr";
                    String str9 = "_et";
                    i = i7;
                    int i10 = i8;
                    com.google.android.gms.internal.measurement.zzfs zzfsVar4 = zzfsVar3;
                    if (i5 >= zzleVar.zzc.size()) {
                        break;
                    }
                    com.google.android.gms.internal.measurement.zzfs zzfsVar5 = (com.google.android.gms.internal.measurement.zzfs) ((com.google.android.gms.internal.measurement.zzft) zzleVar.zzc.get(i5)).zzbB();
                    zzfu zzfuVar = this.zzc;
                    zzal(zzfuVar);
                    String str10 = str6;
                    if (zzfuVar.zzr(zzleVar.zza.zzy(), zzfsVar5.zzo())) {
                        zzaA().zzk().zzc("Dropping blocked raw event. appId", zzet.zzn(zzleVar.zza.zzy()), this.zzn.zzj().zzd(zzfsVar5.zzo()));
                        zzfu zzfuVar2 = this.zzc;
                        zzal(zzfuVar2);
                        if (!zzfuVar2.zzp(zzleVar.zza.zzy())) {
                            zzfu zzfuVar3 = this.zzc;
                            zzal(zzfuVar3);
                            if (!zzfuVar3.zzs(zzleVar.zza.zzy()) && !"_err".equals(zzfsVar5.zzo())) {
                                zzv().zzO(this.zzF, zzleVar.zza.zzy(), 11, "_ev", zzfsVar5.zzo(), 0);
                            }
                        }
                        str2 = str7;
                        i4 = i5;
                        zzfsVar = zzfsVar2;
                        i7 = i;
                        i8 = i10;
                        zzfsVar3 = zzfsVar4;
                        zzgcVar4 = zzgcVar6;
                    } else {
                        if (zzfsVar5.zzo().equals(zzhc.zza(str7))) {
                            zzfsVar5.zzi(str7);
                            zzaA().zzj().zza("Renaming ad_impression to _ai");
                            if (Log.isLoggable(zzaA().zzr(), 5)) {
                                int i11 = 0;
                                while (i11 < zzfsVar5.zza()) {
                                    String str11 = str7;
                                    if (FirebaseAnalytics.Param.AD_PLATFORM.equals(zzfsVar5.zzn(i11).zzg()) && !zzfsVar5.zzn(i11).zzh().isEmpty() && "admob".equalsIgnoreCase(zzfsVar5.zzn(i11).zzh())) {
                                        zzaA().zzl().zza("AdMob ad impression logged from app. Potentially duplicative.");
                                    }
                                    i11++;
                                    str7 = str11;
                                }
                            }
                        }
                        str2 = str7;
                        zzfu zzfuVar4 = this.zzc;
                        zzal(zzfuVar4);
                        boolean zzq = zzfuVar4.zzq(zzleVar.zza.zzy(), zzfsVar5.zzo());
                        if (zzq) {
                            i2 = i5;
                            i3 = i6;
                        } else {
                            zzal(this.zzi);
                            String zzo = zzfsVar5.zzo();
                            Preconditions.checkNotEmpty(zzo);
                            i3 = i6;
                            i2 = i5;
                            if (((zzo.hashCode() == 95027 && zzo.equals("_ui")) ? (char) 0 : (char) 65535) != 0) {
                                str4 = "_fr";
                                str3 = "_et";
                                zzfsVar = zzfsVar2;
                                zzq = false;
                                zzgcVar4 = zzgcVar6;
                                if (zzq) {
                                    ArrayList arrayList3 = new ArrayList(zzfsVar5.zzp());
                                    int i12 = -1;
                                    int i13 = -1;
                                    for (int i14 = 0; i14 < arrayList3.size(); i14++) {
                                        if ("value".equals(((com.google.android.gms.internal.measurement.zzfx) arrayList3.get(i14)).zzg())) {
                                            i12 = i14;
                                        } else if (FirebaseAnalytics.Param.CURRENCY.equals(((com.google.android.gms.internal.measurement.zzfx) arrayList3.get(i14)).zzg())) {
                                            i13 = i14;
                                        }
                                    }
                                    if (i12 != -1) {
                                        if (((com.google.android.gms.internal.measurement.zzfx) arrayList3.get(i12)).zzw() || ((com.google.android.gms.internal.measurement.zzfx) arrayList3.get(i12)).zzu()) {
                                            if (i13 != -1) {
                                                String zzh = ((com.google.android.gms.internal.measurement.zzfx) arrayList3.get(i13)).zzh();
                                                if (zzh.length() == 3) {
                                                    int i15 = 0;
                                                    while (i15 < zzh.length()) {
                                                        int codePointAt = zzh.codePointAt(i15);
                                                        if (Character.isLetter(codePointAt)) {
                                                            i15 += Character.charCount(codePointAt);
                                                        }
                                                    }
                                                }
                                            }
                                            zzaA().zzl().zza("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                            zzfsVar5.zzh(i12);
                                            zzab(zzfsVar5, "_c");
                                            zzaa(zzfsVar5, 19, FirebaseAnalytics.Param.CURRENCY);
                                            break;
                                        }
                                        zzaA().zzl().zza("Value must be specified with a numeric type.");
                                        zzfsVar5.zzh(i12);
                                        zzab(zzfsVar5, "_c");
                                        zzaa(zzfsVar5, 18, "value");
                                    }
                                    if ("_e".equals(zzfsVar5.zzo())) {
                                        zzal(this.zzi);
                                        if (zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD(), str4) == null) {
                                            if (zzfsVar4 != null && Math.abs(zzfsVar4.zzc() - zzfsVar5.zzc()) <= 1000) {
                                                com.google.android.gms.internal.measurement.zzfs zzfsVar6 = (com.google.android.gms.internal.measurement.zzfs) zzfsVar4.clone();
                                                if (zzaj(zzfsVar5, zzfsVar6)) {
                                                    zzgcVar4.zzS(i9, zzfsVar6);
                                                    i8 = i10;
                                                    zzfsVar3 = null;
                                                    zzfsVar = null;
                                                    i4 = i2;
                                                    zzleVar.zzc.set(i4, (com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD());
                                                    i6 = i3 + 1;
                                                    zzgcVar4.zzk(zzfsVar5);
                                                    i7 = i;
                                                }
                                            }
                                            zzfsVar = zzfsVar5;
                                            zzfsVar3 = zzfsVar4;
                                            i8 = i3;
                                            i4 = i2;
                                            zzleVar.zzc.set(i4, (com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD());
                                            i6 = i3 + 1;
                                            zzgcVar4.zzk(zzfsVar5);
                                            i7 = i;
                                        }
                                        i8 = i10;
                                        zzfsVar3 = zzfsVar4;
                                        i4 = i2;
                                        zzleVar.zzc.set(i4, (com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD());
                                        i6 = i3 + 1;
                                        zzgcVar4.zzk(zzfsVar5);
                                        i7 = i;
                                    } else {
                                        if ("_vs".equals(zzfsVar5.zzo())) {
                                            zzal(this.zzi);
                                            if (zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD(), str3) == null) {
                                                if (zzfsVar != null && Math.abs(zzfsVar.zzc() - zzfsVar5.zzc()) <= 1000) {
                                                    com.google.android.gms.internal.measurement.zzfs zzfsVar7 = (com.google.android.gms.internal.measurement.zzfs) zzfsVar.clone();
                                                    if (zzaj(zzfsVar7, zzfsVar5)) {
                                                        zzgcVar4.zzS(i10, zzfsVar7);
                                                        i8 = i10;
                                                        zzfsVar3 = null;
                                                        zzfsVar = null;
                                                        i4 = i2;
                                                        zzleVar.zzc.set(i4, (com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD());
                                                        i6 = i3 + 1;
                                                        zzgcVar4.zzk(zzfsVar5);
                                                        i7 = i;
                                                    }
                                                }
                                                i8 = i10;
                                                zzfsVar3 = zzfsVar5;
                                                i9 = i3;
                                                i4 = i2;
                                                zzleVar.zzc.set(i4, (com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD());
                                                i6 = i3 + 1;
                                                zzgcVar4.zzk(zzfsVar5);
                                                i7 = i;
                                            }
                                        }
                                        i8 = i10;
                                        zzfsVar3 = zzfsVar4;
                                        i4 = i2;
                                        zzleVar.zzc.set(i4, (com.google.android.gms.internal.measurement.zzft) zzfsVar5.zzaD());
                                        i6 = i3 + 1;
                                        zzgcVar4.zzk(zzfsVar5);
                                        i7 = i;
                                    }
                                }
                                if ("_e".equals(zzfsVar5.zzo())) {
                                }
                            }
                        }
                        zzfsVar = zzfsVar2;
                        int i16 = 0;
                        boolean z = false;
                        boolean z2 = false;
                        while (true) {
                            str3 = str9;
                            if (i16 >= zzfsVar5.zza()) {
                                break;
                            }
                            if ("_c".equals(zzfsVar5.zzn(i16).zzg())) {
                                com.google.android.gms.internal.measurement.zzfw zzfwVar = (com.google.android.gms.internal.measurement.zzfw) zzfsVar5.zzn(i16).zzbB();
                                zzgcVar5 = zzgcVar6;
                                str5 = str8;
                                zzfwVar.zzi(1L);
                                zzfsVar5.zzk(i16, (com.google.android.gms.internal.measurement.zzfx) zzfwVar.zzaD());
                                z = true;
                            } else {
                                zzgcVar5 = zzgcVar6;
                                str5 = str8;
                                if ("_r".equals(zzfsVar5.zzn(i16).zzg())) {
                                    com.google.android.gms.internal.measurement.zzfw zzfwVar2 = (com.google.android.gms.internal.measurement.zzfw) zzfsVar5.zzn(i16).zzbB();
                                    zzfwVar2.zzi(1L);
                                    zzfsVar5.zzk(i16, (com.google.android.gms.internal.measurement.zzfx) zzfwVar2.zzaD());
                                    z2 = true;
                                }
                            }
                            i16++;
                            zzgcVar6 = zzgcVar5;
                            str9 = str3;
                            str8 = str5;
                        }
                        zzgcVar4 = zzgcVar6;
                        str4 = str8;
                        if (!z && zzq) {
                            zzaA().zzj().zzb("Marking event as conversion", this.zzn.zzj().zzd(zzfsVar5.zzo()));
                            com.google.android.gms.internal.measurement.zzfw zze = com.google.android.gms.internal.measurement.zzfx.zze();
                            zze.zzj("_c");
                            zze.zzi(1L);
                            zzfsVar5.zze(zze);
                        }
                        if (!z2) {
                            zzaA().zzj().zzb("Marking event as real-time", this.zzn.zzj().zzd(zzfsVar5.zzo()));
                            com.google.android.gms.internal.measurement.zzfw zze2 = com.google.android.gms.internal.measurement.zzfx.zze();
                            zze2.zzj("_r");
                            zze2.zzi(1L);
                            zzfsVar5.zze(zze2);
                        }
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        if (zzakVar4.zzl(zza(), zzleVar.zza.zzy(), false, false, false, false, true).zze > zzg().zze(zzleVar.zza.zzy(), zzeg.zzo)) {
                            zzab(zzfsVar5, "_r");
                        } else {
                            i = 1;
                        }
                        if (zzlp.zzak(zzfsVar5.zzo()) && zzq) {
                            zzak zzakVar5 = this.zze;
                            zzal(zzakVar5);
                            if (zzakVar5.zzl(zza(), zzleVar.zza.zzy(), false, false, true, false, false).zzc > zzg().zze(zzleVar.zza.zzy(), zzeg.zzn)) {
                                zzaA().zzk().zzb("Too many conversions. Not logging as conversion. appId", zzet.zzn(zzleVar.zza.zzy()));
                                com.google.android.gms.internal.measurement.zzfw zzfwVar3 = null;
                                boolean z3 = false;
                                int i17 = -1;
                                for (int i18 = 0; i18 < zzfsVar5.zza(); i18++) {
                                    com.google.android.gms.internal.measurement.zzfx zzn = zzfsVar5.zzn(i18);
                                    if ("_c".equals(zzn.zzg())) {
                                        zzfwVar3 = (com.google.android.gms.internal.measurement.zzfw) zzn.zzbB();
                                        i17 = i18;
                                    } else if ("_err".equals(zzn.zzg())) {
                                        z3 = true;
                                    }
                                }
                                if (z3) {
                                    if (zzfwVar3 != null) {
                                        zzfsVar5.zzh(i17);
                                    } else {
                                        zzfwVar3 = null;
                                    }
                                }
                                if (zzfwVar3 != null) {
                                    com.google.android.gms.internal.measurement.zzfw zzfwVar4 = (com.google.android.gms.internal.measurement.zzfw) zzfwVar3.clone();
                                    zzfwVar4.zzj("_err");
                                    zzfwVar4.zzi(10L);
                                    zzfsVar5.zzk(i17, (com.google.android.gms.internal.measurement.zzfx) zzfwVar4.zzaD());
                                } else {
                                    zzaA().zzd().zzb("Did not find conversion parameter. appId", zzet.zzn(zzleVar.zza.zzy()));
                                }
                            }
                        }
                        if (zzq) {
                        }
                        if ("_e".equals(zzfsVar5.zzo())) {
                        }
                    }
                    i5 = i4 + 1;
                    zzgcVar6 = zzgcVar4;
                    str6 = str10;
                    str7 = str2;
                    zzfsVar2 = zzfsVar;
                }
                String str12 = str6;
                com.google.android.gms.internal.measurement.zzgc zzgcVar7 = zzgcVar6;
                long j3 = 0;
                int i19 = 0;
                while (i19 < i6) {
                    com.google.android.gms.internal.measurement.zzft zze3 = zzgcVar7.zze(i19);
                    if ("_e".equals(zze3.zzh())) {
                        zzal(this.zzi);
                        if (zzlj.zzC(zze3, "_fr") != null) {
                            zzgcVar7.zzA(i19);
                            i6--;
                            i19--;
                            i19++;
                        }
                    }
                    zzal(this.zzi);
                    com.google.android.gms.internal.measurement.zzfx zzC = zzlj.zzC(zze3, "_et");
                    if (zzC != null) {
                        Long valueOf = zzC.zzw() ? Long.valueOf(zzC.zzd()) : null;
                        if (valueOf != null && valueOf.longValue() > 0) {
                            j3 += valueOf.longValue();
                        }
                    }
                    i19++;
                }
                zzaf(zzgcVar7, j3, false);
                Iterator it = zzgcVar7.zzat().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if ("_s".equals(((com.google.android.gms.internal.measurement.zzft) it.next()).zzh())) {
                        zzak zzakVar6 = this.zze;
                        zzal(zzakVar6);
                        zzakVar6.zzA(zzgcVar7.zzaq(), "_se");
                        break;
                    }
                }
                if (zzlj.zza(zzgcVar7, "_sid") >= 0) {
                    zzaf(zzgcVar7, j3, true);
                } else {
                    int zza = zzlj.zza(zzgcVar7, "_se");
                    if (zza >= 0) {
                        zzgcVar7.zzB(zza);
                        zzaA().zzd().zzb("Session engagement user property is in the bundle without session ID. appId", zzet.zzn(zzleVar.zza.zzy()));
                    }
                }
                zzlj zzljVar = this.zzi;
                zzal(zzljVar);
                zzljVar.zzt.zzaA().zzj().zza("Checking account type status for ad personalization signals");
                zzfu zzfuVar5 = zzljVar.zzf.zzc;
                zzal(zzfuVar5);
                if (zzfuVar5.zzn(zzgcVar7.zzaq())) {
                    zzak zzakVar7 = zzljVar.zzf.zze;
                    zzal(zzakVar7);
                    zzh zzj = zzakVar7.zzj(zzgcVar7.zzaq());
                    if (zzj != null && zzj.zzam() && zzljVar.zzt.zzg().zze()) {
                        zzljVar.zzt.zzaA().zzc().zza("Turning off ad personalization due to account type");
                        com.google.android.gms.internal.measurement.zzgl zzd = com.google.android.gms.internal.measurement.zzgm.zzd();
                        zzd.zzf(str12);
                        zzd.zzg(zzljVar.zzt.zzg().zza());
                        zzd.zze(1L);
                        com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) zzd.zzaD();
                        int i20 = 0;
                        while (true) {
                            if (i20 >= zzgcVar7.zzb()) {
                                zzgcVar7.zzm(zzgmVar);
                                break;
                            }
                            if (str12.equals(zzgcVar7.zzap(i20).zzf())) {
                                zzgcVar7.zzan(i20, zzgmVar);
                                break;
                            }
                            i20++;
                        }
                    }
                }
                zzgcVar7.zzai(Long.MAX_VALUE);
                zzgcVar7.zzQ(Long.MIN_VALUE);
                for (int i21 = 0; i21 < zzgcVar7.zza(); i21++) {
                    com.google.android.gms.internal.measurement.zzft zze4 = zzgcVar7.zze(i21);
                    if (zze4.zzd() < zzgcVar7.zzd()) {
                        zzgcVar7.zzai(zze4.zzd());
                    }
                    if (zze4.zzd() > zzgcVar7.zzc()) {
                        zzgcVar7.zzQ(zze4.zzd());
                    }
                }
                zzgcVar7.zzz();
                zzgcVar7.zzo();
                zzaa zzaaVar = this.zzh;
                zzal(zzaaVar);
                zzgcVar7.zzf(zzaaVar.zza(zzgcVar7.zzaq(), zzgcVar7.zzat(), zzgcVar7.zzau(), Long.valueOf(zzgcVar7.zzd()), Long.valueOf(zzgcVar7.zzc())));
                if (zzg().zzw(zzleVar.zza.zzy())) {
                    HashMap hashMap3 = new HashMap();
                    ArrayList arrayList4 = new ArrayList();
                    SecureRandom zzG = zzv().zzG();
                    int i22 = 0;
                    while (i22 < zzgcVar7.zza()) {
                        com.google.android.gms.internal.measurement.zzfs zzfsVar8 = (com.google.android.gms.internal.measurement.zzfs) zzgcVar7.zze(i22).zzbB();
                        if (zzfsVar8.zzo().equals("_ep")) {
                            zzal(this.zzi);
                            String str13 = (String) zzlj.zzD((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD(), "_en");
                            zzaq zzaqVar = (zzaq) hashMap3.get(str13);
                            if (zzaqVar == null) {
                                zzak zzakVar8 = this.zze;
                                zzal(zzakVar8);
                                zzaqVar = zzakVar8.zzn(zzleVar.zza.zzy(), (String) Preconditions.checkNotNull(str13));
                                if (zzaqVar != null) {
                                    hashMap3.put(str13, zzaqVar);
                                }
                            }
                            if (zzaqVar != null && zzaqVar.zzi == null) {
                                Long l2 = zzaqVar.zzj;
                                if (l2 != null && l2.longValue() > 1) {
                                    zzal(this.zzi);
                                    zzlj.zzA(zzfsVar8, "_sr", zzaqVar.zzj);
                                }
                                Boolean bool = zzaqVar.zzk;
                                if (bool != null && bool.booleanValue()) {
                                    zzal(this.zzi);
                                    zzlj.zzA(zzfsVar8, "_efs", 1L);
                                }
                                arrayList4.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                            }
                            zzgcVar7.zzS(i22, zzfsVar8);
                        } else {
                            zzfu zzfuVar6 = this.zzc;
                            zzal(zzfuVar6);
                            String zzy = zzleVar.zza.zzy();
                            String zza2 = zzfuVar6.zza(zzy, "measurement.account.time_zone_offset_minutes");
                            if (!TextUtils.isEmpty(zza2)) {
                                try {
                                    parseLong = Long.parseLong(zza2);
                                } catch (NumberFormatException e) {
                                    zzfuVar6.zzt.zzaA().zzk().zzc("Unable to parse timezone offset. appId", zzet.zzn(zzy), e);
                                }
                                long zzr2 = zzv().zzr(zzfsVar8.zzc(), parseLong);
                                com.google.android.gms.internal.measurement.zzft zzftVar = (com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD();
                                Long l3 = 1L;
                                long j4 = parseLong;
                                if (!TextUtils.isEmpty("_dbg")) {
                                    Iterator it2 = zzftVar.zzi().iterator();
                                    while (true) {
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        com.google.android.gms.internal.measurement.zzfx zzfxVar = (com.google.android.gms.internal.measurement.zzfx) it2.next();
                                        Iterator it3 = it2;
                                        if (!"_dbg".equals(zzfxVar.zzg())) {
                                            it2 = it3;
                                        } else if (l3.equals(Long.valueOf(zzfxVar.zzd()))) {
                                            zzc = 1;
                                        }
                                    }
                                }
                                zzfu zzfuVar7 = this.zzc;
                                zzal(zzfuVar7);
                                zzc = zzfuVar7.zzc(zzleVar.zza.zzy(), zzfsVar8.zzo());
                                if (zzc > 0) {
                                    zzaA().zzk().zzc("Sample rate must be positive. event, rate", zzfsVar8.zzo(), Integer.valueOf(zzc));
                                    arrayList4.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                    zzgcVar7.zzS(i22, zzfsVar8);
                                } else {
                                    zzaq zzaqVar2 = (zzaq) hashMap3.get(zzfsVar8.zzo());
                                    if (zzaqVar2 == null) {
                                        zzak zzakVar9 = this.zze;
                                        zzal(zzakVar9);
                                        zzaqVar2 = zzakVar9.zzn(zzleVar.zza.zzy(), zzfsVar8.zzo());
                                        if (zzaqVar2 == null) {
                                            j2 = zzr2;
                                            zzaA().zzk().zzc("Event being bundled has no eventAggregate. appId, eventName", zzleVar.zza.zzy(), zzfsVar8.zzo());
                                            zzaqVar2 = new zzaq(zzleVar.zza.zzy(), zzfsVar8.zzo(), 1L, 1L, 1L, zzfsVar8.zzc(), 0L, null, null, null, null);
                                            zzal(this.zzi);
                                            Long l4 = (Long) zzlj.zzD((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD(), "_eid");
                                            Boolean valueOf2 = Boolean.valueOf(l4 == null);
                                            if (zzc != 1) {
                                                arrayList4.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                                if (valueOf2.booleanValue() && (zzaqVar2.zzi != null || zzaqVar2.zzj != null || zzaqVar2.zzk != null)) {
                                                    hashMap3.put(zzfsVar8.zzo(), zzaqVar2.zza(null, null, null));
                                                }
                                                zzgcVar7.zzS(i22, zzfsVar8);
                                            } else {
                                                if (zzG.nextInt(zzc) == 0) {
                                                    zzal(this.zzi);
                                                    Long valueOf3 = Long.valueOf(zzc);
                                                    zzlj.zzA(zzfsVar8, "_sr", valueOf3);
                                                    arrayList4.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                                    if (valueOf2.booleanValue()) {
                                                        zzaqVar2 = zzaqVar2.zza(null, valueOf3, null);
                                                    }
                                                    hashMap3.put(zzfsVar8.zzo(), zzaqVar2.zzb(zzfsVar8.zzc(), j2));
                                                    arrayList2 = arrayList4;
                                                    secureRandom = zzG;
                                                    zzgcVar3 = zzgcVar7;
                                                    hashMap2 = hashMap3;
                                                } else {
                                                    long j5 = j2;
                                                    secureRandom = zzG;
                                                    Long l5 = zzaqVar2.zzh;
                                                    if (l5 != null) {
                                                        zzr = l5.longValue();
                                                        hashMap = hashMap3;
                                                        arrayList = arrayList4;
                                                        l = l4;
                                                        zzgcVar2 = zzgcVar7;
                                                    } else {
                                                        l = l4;
                                                        zzgcVar2 = zzgcVar7;
                                                        hashMap = hashMap3;
                                                        arrayList = arrayList4;
                                                        zzr = zzv().zzr(zzfsVar8.zzb(), j4);
                                                    }
                                                    if (zzr != j5) {
                                                        zzal(this.zzi);
                                                        zzlj.zzA(zzfsVar8, "_efs", 1L);
                                                        zzal(this.zzi);
                                                        Long valueOf4 = Long.valueOf(zzc);
                                                        zzlj.zzA(zzfsVar8, "_sr", valueOf4);
                                                        arrayList2 = arrayList;
                                                        arrayList2.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                                        if (valueOf2.booleanValue()) {
                                                            zzaqVar2 = zzaqVar2.zza(null, valueOf4, true);
                                                        }
                                                        hashMap2 = hashMap;
                                                        hashMap2.put(zzfsVar8.zzo(), zzaqVar2.zzb(zzfsVar8.zzc(), j5));
                                                    } else {
                                                        hashMap2 = hashMap;
                                                        arrayList2 = arrayList;
                                                        if (valueOf2.booleanValue()) {
                                                            hashMap2.put(zzfsVar8.zzo(), zzaqVar2.zza(l, null, null));
                                                        }
                                                    }
                                                    zzgcVar3 = zzgcVar2;
                                                }
                                                zzgcVar3.zzS(i22, zzfsVar8);
                                                i22++;
                                                zzgcVar7 = zzgcVar3;
                                                hashMap3 = hashMap2;
                                                arrayList4 = arrayList2;
                                                zzG = secureRandom;
                                            }
                                        }
                                    }
                                    j2 = zzr2;
                                    zzal(this.zzi);
                                    Long l42 = (Long) zzlj.zzD((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD(), "_eid");
                                    Boolean valueOf22 = Boolean.valueOf(l42 == null);
                                    if (zzc != 1) {
                                    }
                                }
                            }
                            parseLong = 0;
                            long zzr22 = zzv().zzr(zzfsVar8.zzc(), parseLong);
                            com.google.android.gms.internal.measurement.zzft zzftVar2 = (com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD();
                            Long l32 = 1L;
                            long j42 = parseLong;
                            if (!TextUtils.isEmpty("_dbg")) {
                            }
                            zzfu zzfuVar72 = this.zzc;
                            zzal(zzfuVar72);
                            zzc = zzfuVar72.zzc(zzleVar.zza.zzy(), zzfsVar8.zzo());
                            if (zzc > 0) {
                            }
                        }
                        arrayList2 = arrayList4;
                        secureRandom = zzG;
                        zzgcVar3 = zzgcVar7;
                        hashMap2 = hashMap3;
                        i22++;
                        zzgcVar7 = zzgcVar3;
                        hashMap3 = hashMap2;
                        arrayList4 = arrayList2;
                        zzG = secureRandom;
                    }
                    HashMap hashMap4 = hashMap3;
                    ArrayList arrayList5 = arrayList4;
                    zzgcVar = zzgcVar7;
                    if (arrayList5.size() < zzgcVar.zza()) {
                        zzgcVar.zzr();
                        zzgcVar.zzg(arrayList5);
                    }
                    for (Map.Entry entry : hashMap4.entrySet()) {
                        zzak zzakVar10 = this.zze;
                        zzal(zzakVar10);
                        zzakVar10.zzE((zzaq) entry.getValue());
                    }
                } else {
                    zzgcVar = zzgcVar7;
                }
                String zzy2 = zzleVar.zza.zzy();
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzh zzj2 = zzakVar11.zzj(zzy2);
                if (zzj2 == null) {
                    zzaA().zzd().zzb("Bundling raw events w/o app info. appId", zzet.zzn(zzleVar.zza.zzy()));
                } else if (zzgcVar.zza() > 0) {
                    long zzn2 = zzj2.zzn();
                    if (zzn2 != 0) {
                        zzgcVar.zzab(zzn2);
                    } else {
                        zzgcVar.zzv();
                    }
                    long zzp = zzj2.zzp();
                    if (zzp != 0) {
                        zzn2 = zzp;
                    }
                    if (zzn2 != 0) {
                        zzgcVar.zzac(zzn2);
                    } else {
                        zzgcVar.zzw();
                    }
                    zzj2.zzG();
                    zzgcVar.zzI((int) zzj2.zzo());
                    zzj2.zzad(zzgcVar.zzd());
                    zzj2.zzab(zzgcVar.zzc());
                    String zzu = zzj2.zzu();
                    if (zzu != null) {
                        zzgcVar.zzW(zzu);
                    } else {
                        zzgcVar.zzs();
                    }
                    zzak zzakVar12 = this.zze;
                    zzal(zzakVar12);
                    zzakVar12.zzD(zzj2);
                }
                if (zzgcVar.zza() > 0) {
                    this.zzn.zzay();
                    zzfu zzfuVar8 = this.zzc;
                    zzal(zzfuVar8);
                    com.google.android.gms.internal.measurement.zzff zze5 = zzfuVar8.zze(zzleVar.zza.zzy());
                    try {
                        try {
                            if (zze5 != null && zze5.zzu()) {
                                zzgcVar.zzK(zze5.zzc());
                                zzakVar = this.zze;
                                zzal(zzakVar);
                                zzgdVar = (com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD();
                                zzakVar.zzg();
                                zzakVar.zzW();
                                Preconditions.checkNotNull(zzgdVar);
                                Preconditions.checkNotEmpty(zzgdVar.zzy());
                                Preconditions.checkState(zzgdVar.zzbg());
                                zzakVar.zzz();
                                currentTimeMillis = zzakVar.zzt.zzax().currentTimeMillis();
                                zzk = zzgdVar.zzk();
                                zzakVar.zzt.zzf();
                                if (zzk >= currentTimeMillis - zzag.zzA()) {
                                    long zzk2 = zzgdVar.zzk();
                                    zzakVar.zzt.zzf();
                                }
                                zzakVar.zzt.zzaA().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzet.zzn(zzgdVar.zzy()), Long.valueOf(currentTimeMillis), Long.valueOf(zzgdVar.zzk()));
                                byte[] zzbx = zzgdVar.zzbx();
                                zzlj zzljVar2 = zzakVar.zzf.zzi;
                                zzal(zzljVar2);
                                byte[] zzz = zzljVar2.zzz(zzbx);
                                zzakVar.zzt.zzaA().zzj().zzb("Saving bundle, size", Integer.valueOf(zzz.length));
                                contentValues = new ContentValues();
                                contentValues.put("app_id", zzgdVar.zzy());
                                contentValues.put("bundle_end_timestamp", Long.valueOf(zzgdVar.zzk()));
                                contentValues.put("data", zzz);
                                contentValues.put("has_realtime", Integer.valueOf(i));
                                if (zzgdVar.zzbm()) {
                                    contentValues.put("retry_count", Integer.valueOf(zzgdVar.zze()));
                                }
                                if (zzakVar.zzh().insert("queue", null, contentValues) == -1) {
                                    zzakVar.zzt.zzaA().zzd().zzb("Failed to insert bundle (got -1). appId", zzet.zzn(zzgdVar.zzy()));
                                }
                            }
                            if (zzakVar.zzh().insert("queue", null, contentValues) == -1) {
                            }
                        } catch (SQLiteException e2) {
                            zzakVar.zzt.zzaA().zzd().zzc("Error storing bundle. appId", zzet.zzn(zzgdVar.zzy()), e2);
                        }
                        zzlj zzljVar22 = zzakVar.zzf.zzi;
                        zzal(zzljVar22);
                        byte[] zzz2 = zzljVar22.zzz(zzbx);
                        zzakVar.zzt.zzaA().zzj().zzb("Saving bundle, size", Integer.valueOf(zzz2.length));
                        contentValues = new ContentValues();
                        contentValues.put("app_id", zzgdVar.zzy());
                        contentValues.put("bundle_end_timestamp", Long.valueOf(zzgdVar.zzk()));
                        contentValues.put("data", zzz2);
                        contentValues.put("has_realtime", Integer.valueOf(i));
                        if (zzgdVar.zzbm()) {
                        }
                    } catch (IOException e3) {
                        zzakVar.zzt.zzaA().zzd().zzc("Data loss. Failed to serialize bundle. appId", zzet.zzn(zzgdVar.zzy()), e3);
                    }
                    if (zzleVar.zza.zzG().isEmpty()) {
                        zzgcVar.zzK(-1L);
                    } else {
                        zzaA().zzk().zzb("Did not find measurement config or missing version info. appId", zzet.zzn(zzleVar.zza.zzy()));
                    }
                    zzakVar = this.zze;
                    zzal(zzakVar);
                    zzgdVar = (com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD();
                    zzakVar.zzg();
                    zzakVar.zzW();
                    Preconditions.checkNotNull(zzgdVar);
                    Preconditions.checkNotEmpty(zzgdVar.zzy());
                    Preconditions.checkState(zzgdVar.zzbg());
                    zzakVar.zzz();
                    currentTimeMillis = zzakVar.zzt.zzax().currentTimeMillis();
                    zzk = zzgdVar.zzk();
                    zzakVar.zzt.zzf();
                    if (zzk >= currentTimeMillis - zzag.zzA()) {
                    }
                    zzakVar.zzt.zzaA().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzet.zzn(zzgdVar.zzy()), Long.valueOf(currentTimeMillis), Long.valueOf(zzgdVar.zzk()));
                    byte[] zzbx2 = zzgdVar.zzbx();
                }
                zzak zzakVar13 = this.zze;
                zzal(zzakVar13);
                List list2 = zzleVar.zzb;
                Preconditions.checkNotNull(list2);
                zzakVar13.zzg();
                zzakVar13.zzW();
                StringBuilder sb = new StringBuilder("rowid in (");
                for (int i23 = 0; i23 < list2.size(); i23++) {
                    if (i23 != 0) {
                        sb.append(",");
                    }
                    sb.append(((Long) list2.get(i23)).longValue());
                }
                sb.append(")");
                int delete = zzakVar13.zzh().delete("raw_events", sb.toString(), null);
                if (delete != list2.size()) {
                    zzakVar13.zzt.zzaA().zzd().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list2.size()));
                }
                zzak zzakVar14 = this.zze;
                zzal(zzakVar14);
                try {
                    zzakVar14.zzh().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{zzy2, zzy2});
                } catch (SQLiteException e4) {
                    zzakVar14.zzt.zzaA().zzd().zzc("Failed to remove unused event metadata. appId", zzet.zzn(zzy2), e4);
                }
                zzak zzakVar15 = this.zze;
                zzal(zzakVar15);
                zzakVar15.zzC();
                zzak zzakVar16 = this.zze;
                zzal(zzakVar16);
                zzakVar16.zzx();
                return true;
            }
            zzak zzakVar17 = this.zze;
            zzal(zzakVar17);
            zzakVar17.zzC();
            zzak zzakVar18 = this.zze;
            zzal(zzakVar18);
            zzakVar18.zzx();
            return false;
        } catch (Throwable th) {
            zzak zzakVar19 = this.zze;
            zzal(zzakVar19);
            zzakVar19.zzx();
            throw th;
        }
    }

    private final boolean zzai() {
        zzaB().zzg();
        zzB();
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        if (zzakVar.zzF()) {
            return true;
        }
        zzak zzakVar2 = this.zze;
        zzal(zzakVar2);
        return !TextUtils.isEmpty(zzakVar2.zzr());
    }

    private final boolean zzaj(com.google.android.gms.internal.measurement.zzfs zzfsVar, com.google.android.gms.internal.measurement.zzfs zzfsVar2) {
        Preconditions.checkArgument("_e".equals(zzfsVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD(), "_sc");
        String zzh = zzC == null ? null : zzC.zzh();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC2 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar2.zzaD(), "_pc");
        String zzh2 = zzC2 != null ? zzC2.zzh() : null;
        if (zzh2 == null || !zzh2.equals(zzh)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzfsVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC3 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD(), "_et");
        if (zzC3 == null || !zzC3.zzw() || zzC3.zzd() <= 0) {
            return true;
        }
        long zzd = zzC3.zzd();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzC4 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar2.zzaD(), "_et");
        if (zzC4 != null && zzC4.zzd() > 0) {
            zzd += zzC4.zzd();
        }
        zzal(this.zzi);
        zzlj.zzA(zzfsVar2, "_et", Long.valueOf(zzd));
        zzal(this.zzi);
        zzlj.zzA(zzfsVar, "_fr", 1L);
        return true;
    }

    private static final boolean zzak(zzq zzqVar) {
        return (TextUtils.isEmpty(zzqVar.zzb) && TextUtils.isEmpty(zzqVar.zzq)) ? false : true;
    }

    private static final zzku zzal(zzku zzkuVar) {
        if (zzkuVar != null) {
            if (zzkuVar.zzY()) {
                return zzkuVar;
            }
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(String.valueOf(zzkuVar.getClass()))));
        }
        throw new IllegalStateException("Upload Component not created");
    }

    public static zzlh zzt(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzlh.class) {
                if (zzb == null) {
                    zzb = new zzlh((zzli) Preconditions.checkNotNull(new zzli(context)), null);
                }
            }
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzy(zzlh zzlhVar, zzli zzliVar) {
        zzlhVar.zzaB().zzg();
        zzlhVar.zzm = new zzfl(zzlhVar);
        zzak zzakVar = new zzak(zzlhVar);
        zzakVar.zzX();
        zzlhVar.zze = zzakVar;
        zzlhVar.zzg().zzq((zzaf) Preconditions.checkNotNull(zzlhVar.zzc));
        zzkb zzkbVar = new zzkb(zzlhVar);
        zzkbVar.zzX();
        zzlhVar.zzk = zzkbVar;
        zzaa zzaaVar = new zzaa(zzlhVar);
        zzaaVar.zzX();
        zzlhVar.zzh = zzaaVar;
        zzip zzipVar = new zzip(zzlhVar);
        zzipVar.zzX();
        zzlhVar.zzj = zzipVar;
        zzks zzksVar = new zzks(zzlhVar);
        zzksVar.zzX();
        zzlhVar.zzg = zzksVar;
        zzlhVar.zzf = new zzfb(zzlhVar);
        if (zzlhVar.zzr != zzlhVar.zzs) {
            zzlhVar.zzaA().zzd().zzc("Not all upload components initialized", Integer.valueOf(zzlhVar.zzr), Integer.valueOf(zzlhVar.zzs));
        }
        zzlhVar.zzo = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzA() {
        zzaB().zzg();
        zzB();
        if (this.zzp) {
            return;
        }
        this.zzp = true;
        if (zzZ()) {
            FileChannel fileChannel = this.zzx;
            zzaB().zzg();
            int i = 0;
            if (fileChannel == null || !fileChannel.isOpen()) {
                zzaA().zzd().zza("Bad channel to read from");
            } else {
                ByteBuffer allocate = ByteBuffer.allocate(4);
                try {
                    fileChannel.position(0L);
                    int read = fileChannel.read(allocate);
                    if (read == 4) {
                        allocate.flip();
                        i = allocate.getInt();
                    } else if (read != -1) {
                        zzaA().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(read));
                    }
                } catch (IOException e) {
                    zzaA().zzd().zzb("Failed to read from channel", e);
                }
            }
            int zzi = this.zzn.zzh().zzi();
            zzaB().zzg();
            if (i > zzi) {
                zzaA().zzd().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi));
                return;
            }
            if (i < zzi) {
                FileChannel fileChannel2 = this.zzx;
                zzaB().zzg();
                if (fileChannel2 == null || !fileChannel2.isOpen()) {
                    zzaA().zzd().zza("Bad channel to read from");
                } else {
                    ByteBuffer allocate2 = ByteBuffer.allocate(4);
                    allocate2.putInt(zzi);
                    allocate2.flip();
                    try {
                        fileChannel2.truncate(0L);
                        fileChannel2.write(allocate2);
                        fileChannel2.force(true);
                        if (fileChannel2.size() != 4) {
                            zzaA().zzd().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                        }
                        zzaA().zzj().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi));
                        return;
                    } catch (IOException e2) {
                        zzaA().zzd().zzb("Failed to write to channel", e2);
                    }
                }
                zzaA().zzd().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzB() {
        if (!this.zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzC(String str, com.google.android.gms.internal.measurement.zzgc zzgcVar) {
        int zza;
        int indexOf;
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        Set zzk = zzfuVar.zzk(str);
        if (zzk != null) {
            zzgcVar.zzi(zzk);
        }
        zzfu zzfuVar2 = this.zzc;
        zzal(zzfuVar2);
        if (zzfuVar2.zzv(str)) {
            zzgcVar.zzp();
        }
        zzfu zzfuVar3 = this.zzc;
        zzal(zzfuVar3);
        if (zzfuVar3.zzy(str)) {
            if (zzg().zzs(str, zzeg.zzar)) {
                String zzas = zzgcVar.zzas();
                if (!TextUtils.isEmpty(zzas) && (indexOf = zzas.indexOf(".")) != -1) {
                    zzgcVar.zzY(zzas.substring(0, indexOf));
                }
            } else {
                zzgcVar.zzu();
            }
        }
        zzfu zzfuVar4 = this.zzc;
        zzal(zzfuVar4);
        if (zzfuVar4.zzz(str) && (zza = zzlj.zza(zzgcVar, "_id")) != -1) {
            zzgcVar.zzB(zza);
        }
        zzfu zzfuVar5 = this.zzc;
        zzal(zzfuVar5);
        if (zzfuVar5.zzx(str)) {
            zzgcVar.zzq();
        }
        zzfu zzfuVar6 = this.zzc;
        zzal(zzfuVar6);
        if (zzfuVar6.zzu(str)) {
            zzgcVar.zzn();
            zzlg zzlgVar = (zzlg) this.zzC.get(str);
            if (zzlgVar == null || zzlgVar.zzb + zzg().zzi(str, zzeg.zzT) < zzax().elapsedRealtime()) {
                zzlgVar = new zzlg(this);
                this.zzC.put(str, zzlgVar);
            }
            zzgcVar.zzR(zzlgVar.zza);
        }
        zzfu zzfuVar7 = this.zzc;
        zzal(zzfuVar7);
        if (zzfuVar7.zzw(str)) {
            zzgcVar.zzy();
        }
    }

    final void zzD(zzh zzhVar) {
        zzaB().zzg();
        if (!TextUtils.isEmpty(zzhVar.zzA()) || !TextUtils.isEmpty(zzhVar.zzt())) {
            zzkw zzkwVar = this.zzl;
            Uri.Builder builder = new Uri.Builder();
            String zzA = zzhVar.zzA();
            if (TextUtils.isEmpty(zzA)) {
                zzA = zzhVar.zzt();
            }
            ArrayMap arrayMap = null;
            Uri.Builder appendQueryParameter = builder.scheme((String) zzeg.zze.zza(null)).encodedAuthority((String) zzeg.zzf.zza(null)).path("config/app/".concat(String.valueOf(zzA))).appendQueryParameter("platform", "android");
            zzkwVar.zzt.zzf().zzh();
            appendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(79000L)).appendQueryParameter("runtime_version", "0");
            String uri = builder.build().toString();
            try {
                String str = (String) Preconditions.checkNotNull(zzhVar.zzv());
                URL url = new URL(uri);
                zzaA().zzj().zzb("Fetching remote configuration", str);
                zzfu zzfuVar = this.zzc;
                zzal(zzfuVar);
                com.google.android.gms.internal.measurement.zzff zze = zzfuVar.zze(str);
                zzfu zzfuVar2 = this.zzc;
                zzal(zzfuVar2);
                String zzh = zzfuVar2.zzh(str);
                if (zze != null) {
                    if (!TextUtils.isEmpty(zzh)) {
                        ArrayMap arrayMap2 = new ArrayMap();
                        arrayMap2.put(HttpHeaders.IF_MODIFIED_SINCE, zzh);
                        arrayMap = arrayMap2;
                    }
                    zzfu zzfuVar3 = this.zzc;
                    zzal(zzfuVar3);
                    String zzf = zzfuVar3.zzf(str);
                    if (!TextUtils.isEmpty(zzf)) {
                        if (arrayMap == null) {
                            arrayMap = new ArrayMap();
                        }
                        arrayMap.put(HttpHeaders.IF_NONE_MATCH, zzf);
                    }
                }
                this.zzt = true;
                zzez zzezVar = this.zzd;
                zzal(zzezVar);
                zzkz zzkzVar = new zzkz(this);
                zzezVar.zzg();
                zzezVar.zzW();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzkzVar);
                zzezVar.zzt.zzaB().zzo(new zzey(zzezVar, str, url, null, arrayMap, zzkzVar));
                return;
            } catch (MalformedURLException unused) {
                zzaA().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzet.zzn(zzhVar.zzv()), uri);
                return;
            }
        }
        zzI((String) Preconditions.checkNotNull(zzhVar.zzv()), MlKitException.CODE_SCANNER_TASK_IN_PROGRESS, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzE(zzau zzauVar, zzq zzqVar) {
        zzau zzauVar2;
        List<zzac> zzt;
        List<zzac> zzt2;
        List<zzac> zzt3;
        String str;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzaB().zzg();
        zzB();
        String str2 = zzqVar.zza;
        long j = zzauVar.zzd;
        zzeu zzb2 = zzeu.zzb(zzauVar);
        zzaB().zzg();
        zzir zzirVar = null;
        if (this.zzD != null && (str = this.zzE) != null && str.equals(str2)) {
            zzirVar = this.zzD;
        }
        zzlp.zzK(zzirVar, zzb2.zzd, false);
        zzau zza = zzb2.zza();
        zzal(this.zzi);
        if (zzlj.zzB(zza, zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            List list = zzqVar.zzt;
            if (list == null) {
                zzauVar2 = zza;
            } else if (list.contains(zza.zza)) {
                Bundle zzc = zza.zzb.zzc();
                zzc.putLong("ga_safelisted", 1L);
                zzauVar2 = new zzau(zza.zza, new zzas(zzc), zza.zzc, zza.zzd);
            } else {
                zzaA().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", str2, zza.zza, zza.zzc);
                return;
            }
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                Preconditions.checkNotEmpty(str2);
                zzakVar2.zzg();
                zzakVar2.zzW();
                if (j < 0) {
                    zzakVar2.zzt.zzaA().zzk().zzc("Invalid time querying timed out conditional properties", zzet.zzn(str2), Long.valueOf(j));
                    zzt = Collections.emptyList();
                } else {
                    zzt = zzakVar2.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzac zzacVar : zzt) {
                    if (zzacVar != null) {
                        zzaA().zzj().zzd("User property timed out", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb), zzacVar.zzc.zza());
                        zzau zzauVar3 = zzacVar.zzg;
                        if (zzauVar3 != null) {
                            zzY(new zzau(zzauVar3, j), zzqVar);
                        }
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zza(str2, zzacVar.zzc.zzb);
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                Preconditions.checkNotEmpty(str2);
                zzakVar4.zzg();
                zzakVar4.zzW();
                if (j < 0) {
                    zzakVar4.zzt.zzaA().zzk().zzc("Invalid time querying expired conditional properties", zzet.zzn(str2), Long.valueOf(j));
                    zzt2 = Collections.emptyList();
                } else {
                    zzt2 = zzakVar4.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(zzt2.size());
                for (zzac zzacVar2 : zzt2) {
                    if (zzacVar2 != null) {
                        zzaA().zzj().zzd("User property expired", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzA(str2, zzacVar2.zzc.zzb);
                        zzau zzauVar4 = zzacVar2.zzk;
                        if (zzauVar4 != null) {
                            arrayList.add(zzauVar4);
                        }
                        zzak zzakVar6 = this.zze;
                        zzal(zzakVar6);
                        zzakVar6.zza(str2, zzacVar2.zzc.zzb);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    zzY(new zzau((zzau) it.next(), j), zzqVar);
                }
                zzak zzakVar7 = this.zze;
                zzal(zzakVar7);
                String str3 = zzauVar2.zza;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzakVar7.zzg();
                zzakVar7.zzW();
                if (j < 0) {
                    zzakVar7.zzt.zzaA().zzk().zzd("Invalid time querying triggered conditional properties", zzet.zzn(str2), zzakVar7.zzt.zzj().zzd(str3), Long.valueOf(j));
                    zzt3 = Collections.emptyList();
                } else {
                    zzt3 = zzakVar7.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList2 = new ArrayList(zzt3.size());
                for (zzac zzacVar3 : zzt3) {
                    if (zzacVar3 != null) {
                        zzlk zzlkVar = zzacVar3.zzc;
                        zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzacVar3.zza), zzacVar3.zzb, zzlkVar.zzb, j, Preconditions.checkNotNull(zzlkVar.zza()));
                        zzak zzakVar8 = this.zze;
                        zzal(zzakVar8);
                        if (zzakVar8.zzL(zzlmVar)) {
                            zzaA().zzj().zzd("User property triggered", zzacVar3.zza, this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                        } else {
                            zzaA().zzd().zzd("Too many active user properties, ignoring", zzet.zzn(zzacVar3.zza), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                        }
                        zzau zzauVar5 = zzacVar3.zzi;
                        if (zzauVar5 != null) {
                            arrayList2.add(zzauVar5);
                        }
                        zzacVar3.zzc = new zzlk(zzlmVar);
                        zzacVar3.zze = true;
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        zzakVar9.zzK(zzacVar3);
                    }
                }
                zzY(zzauVar2, zzqVar);
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    zzY(new zzau((zzau) it2.next(), j), zzqVar);
                }
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzC();
            } finally {
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzakVar11.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzF(zzau zzauVar, String str) {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzj = zzakVar.zzj(str);
        if (zzj == null || TextUtils.isEmpty(zzj.zzy())) {
            zzaA().zzc().zzb("No app data available; dropping event", str);
            return;
        }
        Boolean zzad = zzad(zzj);
        if (zzad == null) {
            if (!"_ui".equals(zzauVar.zza)) {
                zzaA().zzk().zzb("Could not find package. appId", zzet.zzn(str));
            }
        } else if (!zzad.booleanValue()) {
            zzaA().zzd().zzb("App version does not match; dropping event. appId", zzet.zzn(str));
            return;
        }
        String zzA = zzj.zzA();
        String zzy = zzj.zzy();
        long zzb2 = zzj.zzb();
        String zzx = zzj.zzx();
        long zzm = zzj.zzm();
        long zzj2 = zzj.zzj();
        boolean zzan = zzj.zzan();
        String zzz = zzj.zzz();
        zzj.zza();
        zzG(zzauVar, new zzq(str, zzA, zzy, zzb2, zzx, zzm, zzj2, (String) null, zzan, false, zzz, 0L, 0L, 0, zzj.zzam(), false, zzj.zzt(), zzj.zzs(), zzj.zzk(), zzj.zzE(), (String) null, zzq(str).zzi(), "", (String) null, zzj.zzap(), zzj.zzr()));
    }

    final void zzG(zzau zzauVar, zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzeu zzb2 = zzeu.zzb(zzauVar);
        zzlp zzv = zzv();
        Bundle bundle = zzb2.zzd;
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzv.zzL(bundle, zzakVar.zzi(zzqVar.zza));
        zzv().zzN(zzb2, zzg().zzd(zzqVar.zza));
        zzau zza = zzb2.zza();
        if ("_cmp".equals(zza.zza) && "referrer API v2".equals(zza.zzb.zzg("_cis"))) {
            String zzg = zza.zzb.zzg("gclid");
            if (!TextUtils.isEmpty(zzg)) {
                zzW(new zzlk("_lgclid", zza.zzd, zzg, "auto"), zzqVar);
            }
        }
        zzE(zza, zzqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzH() {
        this.zzs++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0049 A[Catch: all -> 0x0176, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x002c, B:13:0x0049, B:14:0x0160, B:24:0x0063, B:28:0x00b5, B:29:0x00a6, B:32:0x00bd, B:34:0x00c9, B:36:0x00cf, B:38:0x00d9, B:40:0x00e5, B:42:0x00eb, B:46:0x00f8, B:47:0x0114, B:49:0x0129, B:50:0x0148, B:52:0x0153, B:54:0x0159, B:55:0x015d, B:56:0x0137, B:57:0x0101, B:59:0x010c), top: B:4:0x002c, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0129 A[Catch: all -> 0x0176, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x002c, B:13:0x0049, B:14:0x0160, B:24:0x0063, B:28:0x00b5, B:29:0x00a6, B:32:0x00bd, B:34:0x00c9, B:36:0x00cf, B:38:0x00d9, B:40:0x00e5, B:42:0x00eb, B:46:0x00f8, B:47:0x0114, B:49:0x0129, B:50:0x0148, B:52:0x0153, B:54:0x0159, B:55:0x015d, B:56:0x0137, B:57:0x0101, B:59:0x010c), top: B:4:0x002c, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0137 A[Catch: all -> 0x0176, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x002c, B:13:0x0049, B:14:0x0160, B:24:0x0063, B:28:0x00b5, B:29:0x00a6, B:32:0x00bd, B:34:0x00c9, B:36:0x00cf, B:38:0x00d9, B:40:0x00e5, B:42:0x00eb, B:46:0x00f8, B:47:0x0114, B:49:0x0129, B:50:0x0148, B:52:0x0153, B:54:0x0159, B:55:0x015d, B:56:0x0137, B:57:0x0101, B:59:0x010c), top: B:4:0x002c, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzI(String str, int i, Throwable th, byte[] bArr, Map map) {
        boolean z;
        zzez zzezVar;
        zzaB().zzg();
        zzB();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzt = false;
                zzae();
            }
        }
        zzer zzj = zzaA().zzj();
        Integer valueOf = Integer.valueOf(bArr.length);
        zzj.zzb("onConfigFetched. Response size", valueOf);
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzakVar.zzw();
        try {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzh zzj2 = zzakVar2.zzj(str);
            if (i != 200 && i != 204) {
                if (i == 304) {
                    i = 304;
                }
                z = false;
                if (zzj2 == null) {
                    zzaA().zzk().zzb("App does not exist in onConfigFetched. appId", zzet.zzn(str));
                } else {
                    if (!z && i != 404) {
                        zzj2.zzW(zzax().currentTimeMillis());
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zzD(zzj2);
                        zzaA().zzj().zzc("Fetching config failed. code, error", Integer.valueOf(i), th);
                        zzfu zzfuVar = this.zzc;
                        zzal(zzfuVar);
                        zzfuVar.zzl(str);
                        this.zzk.zzd.zzb(zzax().currentTimeMillis());
                        if (i == 503 || i == 429) {
                            this.zzk.zzb.zzb(zzax().currentTimeMillis());
                        }
                        zzag();
                    }
                    List list = map != null ? (List) map.get(HttpHeaders.LAST_MODIFIED) : null;
                    String str2 = (list == null || list.isEmpty()) ? null : (String) list.get(0);
                    List list2 = map != null ? (List) map.get(HttpHeaders.ETAG) : null;
                    String str3 = (list2 == null || list2.isEmpty()) ? null : (String) list2.get(0);
                    if (i != 404 && i != 304) {
                        zzfu zzfuVar2 = this.zzc;
                        zzal(zzfuVar2);
                        zzfuVar2.zzt(str, bArr, str2, str3);
                        zzj2.zzN(zzax().currentTimeMillis());
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        zzakVar4.zzD(zzj2);
                        if (i != 404) {
                            zzaA().zzl().zzb("Config not found. Using empty config. appId", str);
                        } else {
                            zzaA().zzj().zzc("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), valueOf);
                        }
                        zzezVar = this.zzd;
                        zzal(zzezVar);
                        if (zzezVar.zza() || !zzai()) {
                            zzag();
                        } else {
                            zzX();
                        }
                    }
                    zzfu zzfuVar3 = this.zzc;
                    zzal(zzfuVar3);
                    if (zzfuVar3.zze(str) == null) {
                        zzfu zzfuVar4 = this.zzc;
                        zzal(zzfuVar4);
                        zzfuVar4.zzt(str, null, null, null);
                    }
                    zzj2.zzN(zzax().currentTimeMillis());
                    zzak zzakVar42 = this.zze;
                    zzal(zzakVar42);
                    zzakVar42.zzD(zzj2);
                    if (i != 404) {
                    }
                    zzezVar = this.zzd;
                    zzal(zzezVar);
                    if (zzezVar.zza()) {
                    }
                    zzag();
                }
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzC();
            }
            if (th == null) {
                z = true;
                if (zzj2 == null) {
                }
                zzak zzakVar52 = this.zze;
                zzal(zzakVar52);
                zzakVar52.zzC();
            }
            z = false;
            if (zzj2 == null) {
            }
            zzak zzakVar522 = this.zze;
            zzal(zzakVar522);
            zzakVar522.zzC();
        } finally {
            zzak zzakVar6 = this.zze;
            zzal(zzakVar6);
            zzakVar6.zzx();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzJ(boolean z) {
        zzag();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzK(int i, Throwable th, byte[] bArr, String str) {
        zzak zzakVar;
        long longValue;
        zzaB().zzg();
        zzB();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzu = false;
                zzae();
            }
        }
        List<Long> list = (List) Preconditions.checkNotNull(this.zzy);
        this.zzy = null;
        if (i != 200) {
            if (i == 204) {
                i = 204;
            }
            zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzk.zzd.zzb(zzax().currentTimeMillis());
            if (i != 503 || i == 429) {
                this.zzk.zzb.zzb(zzax().currentTimeMillis());
            }
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzakVar2.zzy(list);
            zzag();
        }
        if (th == null) {
            try {
                this.zzk.zzc.zzb(zzax().currentTimeMillis());
                this.zzk.zzd.zzb(0L);
                zzag();
                zzaA().zzj().zzc("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzak zzakVar3 = this.zze;
                zzal(zzakVar3);
                zzakVar3.zzw();
            } catch (SQLiteException e) {
                zzaA().zzd().zzb("Database error while trying to delete uploaded bundles", e);
                this.zza = zzax().elapsedRealtime();
                zzaA().zzj().zzb("Disable upload, time", Long.valueOf(this.zza));
            }
            try {
                for (Long l : list) {
                    try {
                        zzakVar = this.zze;
                        zzal(zzakVar);
                        longValue = l.longValue();
                        zzakVar.zzg();
                        zzakVar.zzW();
                    } catch (SQLiteException e2) {
                        List list2 = this.zzz;
                        if (list2 == null || !list2.contains(l)) {
                            throw e2;
                        }
                    }
                    try {
                        if (zzakVar.zzh().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                            break;
                        }
                    } catch (SQLiteException e3) {
                        zzakVar.zzt.zzaA().zzd().zzb("Failed to delete a bundle in a queue table", e3);
                        throw e3;
                        break;
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                zzakVar4.zzC();
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzx();
                this.zzz = null;
                zzez zzezVar = this.zzd;
                zzal(zzezVar);
                if (zzezVar.zza() && zzai()) {
                    zzX();
                } else {
                    this.zzA = -1L;
                    zzag();
                }
                this.zza = 0L;
            } catch (Throwable th2) {
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                zzakVar6.zzx();
                throw th2;
            }
        }
        zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
        this.zzk.zzd.zzb(zzax().currentTimeMillis());
        if (i != 503) {
        }
        this.zzk.zzb.zzb(zzax().currentTimeMillis());
        zzak zzakVar22 = this.zze;
        zzal(zzakVar22);
        zzakVar22.zzy(list);
        zzag();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0545 A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x025d A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01f5 A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x024e A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x026d A[Catch: all -> 0x0575, TRY_LEAVE, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03db A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0407 A[Catch: all -> 0x0575, TRY_LEAVE, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x04e0 A[Catch: all -> 0x0575, TryCatch #0 {all -> 0x0575, blocks: (B:24:0x00a6, B:26:0x00b6, B:30:0x00fd, B:32:0x0110, B:34:0x0126, B:36:0x014d, B:39:0x01a7, B:42:0x01ac, B:44:0x01b2, B:46:0x01bb, B:50:0x01f5, B:52:0x0200, B:55:0x020d, B:58:0x021b, B:61:0x0226, B:63:0x0229, B:66:0x0249, B:68:0x024e, B:70:0x026d, B:73:0x0280, B:75:0x02a7, B:78:0x02af, B:80:0x02be, B:81:0x03a7, B:83:0x03db, B:84:0x03de, B:86:0x0407, B:90:0x04e0, B:91:0x04e3, B:92:0x0564, B:97:0x041c, B:99:0x0441, B:101:0x0449, B:103:0x0455, B:107:0x0468, B:109:0x0479, B:112:0x0485, B:114:0x049b, B:124:0x04ac, B:116:0x04c0, B:118:0x04c6, B:119:0x04cd, B:121:0x04d3, B:126:0x0471, B:131:0x042d, B:132:0x02cf, B:134:0x02fa, B:135:0x030b, B:137:0x0312, B:139:0x0318, B:141:0x0322, B:143:0x032c, B:145:0x0332, B:147:0x0338, B:149:0x033d, B:152:0x035f, B:156:0x0364, B:157:0x0378, B:158:0x0388, B:159:0x0398, B:160:0x04fa, B:162:0x052b, B:163:0x052e, B:164:0x0545, B:166:0x0549, B:167:0x025d, B:169:0x01da, B:177:0x00c3, B:179:0x00c7, B:182:0x00d8, B:184:0x00e9, B:186:0x00f3, B:190:0x00fa), top: B:23:0x00a6, inners: #1, #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x041c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzL(zzq zzqVar) {
        zzh zzj;
        String str;
        String str2;
        zzaq zzn;
        boolean z;
        long zzc;
        PackageInfo packageInfo;
        String str3;
        String str4;
        ApplicationInfo applicationInfo;
        ApplicationInfo applicationInfo2;
        boolean z2;
        zzaB().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        if (zzak(zzqVar)) {
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzh zzj2 = zzakVar.zzj(zzqVar.zza);
            if (zzj2 != null && TextUtils.isEmpty(zzj2.zzA()) && !TextUtils.isEmpty(zzqVar.zzb)) {
                zzj2.zzN(0L);
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzakVar2.zzD(zzj2);
                zzfu zzfuVar = this.zzc;
                zzal(zzfuVar);
                zzfuVar.zzm(zzqVar.zza);
            }
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            long j = zzqVar.zzm;
            if (j == 0) {
                j = zzax().currentTimeMillis();
            }
            this.zzn.zzg().zzd();
            int i = zzqVar.zzn;
            if (i != 0 && i != 1) {
                zzaA().zzk().zzc("Incorrect app type, assuming installed app. appId, appType", zzet.zzn(zzqVar.zza), Integer.valueOf(i));
                i = 0;
            }
            zzak zzakVar3 = this.zze;
            zzal(zzakVar3);
            zzakVar3.zzw();
            try {
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                zzlm zzp = zzakVar4.zzp(zzqVar.zza, "_npa");
                if (zzp != null && !"auto".equals(zzp.zzb)) {
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzj = zzakVar5.zzj((String) Preconditions.checkNotNull(zzqVar.zza));
                    if (zzj == null && zzv().zzao(zzqVar.zzb, zzj.zzA(), zzqVar.zzq, zzj.zzt())) {
                        zzaA().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzet.zzn(zzj.zzv()));
                        zzak zzakVar6 = this.zze;
                        zzal(zzakVar6);
                        String zzv = zzj.zzv();
                        zzakVar6.zzW();
                        zzakVar6.zzg();
                        Preconditions.checkNotEmpty(zzv);
                        try {
                            SQLiteDatabase zzh = zzakVar6.zzh();
                            String[] strArr = {zzv};
                            int delete = zzh.delete("events", "app_id=?", strArr) + zzh.delete("user_attributes", "app_id=?", strArr) + zzh.delete("conditional_properties", "app_id=?", strArr) + zzh.delete("apps", "app_id=?", strArr) + zzh.delete("raw_events", "app_id=?", strArr) + zzh.delete("raw_events_metadata", "app_id=?", strArr) + zzh.delete("event_filters", "app_id=?", strArr) + zzh.delete("property_filters", "app_id=?", strArr) + zzh.delete("audience_filter_values", "app_id=?", strArr) + zzh.delete("consent_settings", "app_id=?", strArr);
                            zzpk.zzc();
                            str2 = "_sysu";
                            try {
                                str = "_pfo";
                                try {
                                    if (zzakVar6.zzt.zzf().zzs(null, zzeg.zzat)) {
                                        delete += zzh.delete("default_event_params", "app_id=?", strArr);
                                    }
                                    if (delete > 0) {
                                        zzakVar6.zzt.zzaA().zzj().zzc("Deleted application data. app, records", zzv, Integer.valueOf(delete));
                                    }
                                } catch (SQLiteException e) {
                                    e = e;
                                    zzakVar6.zzt.zzaA().zzd().zzc("Error deleting application data. appId, error", zzet.zzn(zzv), e);
                                    zzj = null;
                                    if (zzj != null) {
                                    }
                                    zzd(zzqVar);
                                    if (i == 0) {
                                    }
                                    if (zzn == null) {
                                    }
                                    zzak zzakVar7 = this.zze;
                                    zzal(zzakVar7);
                                    zzakVar7.zzC();
                                }
                            } catch (SQLiteException e2) {
                                e = e2;
                                str = "_pfo";
                            }
                        } catch (SQLiteException e3) {
                            e = e3;
                            str = "_pfo";
                            str2 = "_sysu";
                        }
                        zzj = null;
                    } else {
                        str = "_pfo";
                        str2 = "_sysu";
                    }
                    if (zzj != null) {
                        boolean z3 = (zzj.zzb() == -2147483648L || zzj.zzb() == zzqVar.zzj) ? false : true;
                        String zzy = zzj.zzy();
                        if (((zzj.zzb() != -2147483648L || zzy == null || zzy.equals(zzqVar.zzc)) ? false : true) | z3) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_pv", zzy);
                            zzE(new zzau("_au", new zzas(bundle), "auto", j), zzqVar);
                        }
                    }
                    zzd(zzqVar);
                    if (i == 0) {
                        zzak zzakVar8 = this.zze;
                        zzal(zzakVar8);
                        zzn = zzakVar8.zzn(zzqVar.zza, "_f");
                        z = false;
                    } else {
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        zzn = zzakVar9.zzn(zzqVar.zza, "_v");
                        z = true;
                    }
                    if (zzn == null) {
                        long j2 = ((j / 3600000) + 1) * 3600000;
                        if (z) {
                            zzW(new zzlk("_fvt", j, Long.valueOf(j2), "auto"), zzqVar);
                            zzaB().zzg();
                            zzB();
                            Bundle bundle2 = new Bundle();
                            bundle2.putLong("_c", 1L);
                            bundle2.putLong("_r", 1L);
                            bundle2.putLong("_et", 1L);
                            if (zzqVar.zzp) {
                                bundle2.putLong("_dac", 1L);
                            }
                            zzG(new zzau("_v", new zzas(bundle2), "auto", j), zzqVar);
                        } else {
                            zzW(new zzlk("_fot", j, Long.valueOf(j2), "auto"), zzqVar);
                            zzaB().zzg();
                            zzfl zzflVar = (zzfl) Preconditions.checkNotNull(this.zzm);
                            String str5 = zzqVar.zza;
                            if (str5 != null && !str5.isEmpty()) {
                                zzflVar.zza.zzaB().zzg();
                                if (!zzflVar.zza()) {
                                    zzflVar.zza.zzaA().zzi().zza("Install Referrer Reporter is not available");
                                } else {
                                    zzfk zzfkVar = new zzfk(zzflVar, str5);
                                    zzflVar.zza.zzaB().zzg();
                                    Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                                    intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                                    PackageManager packageManager = zzflVar.zza.zzaw().getPackageManager();
                                    if (packageManager == null) {
                                        zzflVar.zza.zzaA().zzm().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");
                                    } else {
                                        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
                                        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                                            zzflVar.zza.zzaA().zzi().zza("Play Service for fetching Install Referrer is unavailable on device");
                                        } else {
                                            ResolveInfo resolveInfo = queryIntentServices.get(0);
                                            if (resolveInfo.serviceInfo != null) {
                                                String str6 = resolveInfo.serviceInfo.packageName;
                                                if (resolveInfo.serviceInfo.name == null || !"com.android.vending".equals(str6) || !zzflVar.zza()) {
                                                    zzflVar.zza.zzaA().zzk().zza("Play Store version 8.3.73 or higher required for Install Referrer");
                                                } else {
                                                    try {
                                                        zzflVar.zza.zzaA().zzj().zzb("Install Referrer Service is", ConnectionTracker.getInstance().bindService(zzflVar.zza.zzaw(), new Intent(intent), zzfkVar, 1) ? "available" : "not available");
                                                    } catch (RuntimeException e4) {
                                                        zzflVar.zza.zzaA().zzd().zzb("Exception occurred while binding to Install Referrer Service", e4.getMessage());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                zzaB().zzg();
                                zzB();
                                Bundle bundle3 = new Bundle();
                                bundle3.putLong("_c", 1L);
                                bundle3.putLong("_r", 1L);
                                bundle3.putLong("_uwa", 0L);
                                String str7 = str;
                                bundle3.putLong(str7, 0L);
                                bundle3.putLong("_sys", 0L);
                                String str8 = str2;
                                bundle3.putLong(str8, 0L);
                                bundle3.putLong("_et", 1L);
                                if (zzqVar.zzp) {
                                    bundle3.putLong("_dac", 1L);
                                }
                                String str9 = (String) Preconditions.checkNotNull(zzqVar.zza);
                                zzak zzakVar10 = this.zze;
                                zzal(zzakVar10);
                                Preconditions.checkNotEmpty(str9);
                                zzakVar10.zzg();
                                zzakVar10.zzW();
                                zzc = zzakVar10.zzc(str9, "first_open_count");
                                if (this.zzn.zzaw().getPackageManager() == null) {
                                    try {
                                        packageInfo = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(str9, 0);
                                    } catch (PackageManager.NameNotFoundException e5) {
                                        zzaA().zzd().zzc("Package info is null, first open report might be inaccurate. appId", zzet.zzn(str9), e5);
                                        packageInfo = null;
                                    }
                                    if (packageInfo == null || packageInfo.firstInstallTime == 0) {
                                        str3 = "_sys";
                                        str4 = str8;
                                        applicationInfo = null;
                                    } else {
                                        str3 = "_sys";
                                        str4 = str8;
                                        if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                            applicationInfo = null;
                                            if (!zzg().zzs(null, zzeg.zzad)) {
                                                bundle3.putLong("_uwa", 1L);
                                            } else if (zzc == 0) {
                                                bundle3.putLong("_uwa", 1L);
                                                zzc = 0;
                                            }
                                            z2 = false;
                                        } else {
                                            applicationInfo = null;
                                            z2 = true;
                                        }
                                        zzW(new zzlk("_fi", j, Long.valueOf(true != z2 ? 0L : 1L), "auto"), zzqVar);
                                    }
                                    try {
                                        applicationInfo2 = Wrappers.packageManager(this.zzn.zzaw()).getApplicationInfo(str9, 0);
                                    } catch (PackageManager.NameNotFoundException e6) {
                                        zzaA().zzd().zzc("Application info is null, first open report might be inaccurate. appId", zzet.zzn(str9), e6);
                                        applicationInfo2 = applicationInfo;
                                    }
                                    if (applicationInfo2 != null) {
                                        if ((applicationInfo2.flags & 1) != 0) {
                                            bundle3.putLong(str3, 1L);
                                        }
                                        if ((applicationInfo2.flags & 128) != 0) {
                                            bundle3.putLong(str4, 1L);
                                        }
                                    }
                                } else {
                                    zzaA().zzd().zzb("PackageManager is null, first open report might be inaccurate. appId", zzet.zzn(str9));
                                }
                                if (zzc >= 0) {
                                    bundle3.putLong(str7, zzc);
                                }
                                zzG(new zzau("_f", new zzas(bundle3), "auto", j), zzqVar);
                            }
                            zzflVar.zza.zzaA().zzm().zza("Install Referrer Reporter was called with invalid app package name");
                            zzaB().zzg();
                            zzB();
                            Bundle bundle32 = new Bundle();
                            bundle32.putLong("_c", 1L);
                            bundle32.putLong("_r", 1L);
                            bundle32.putLong("_uwa", 0L);
                            String str72 = str;
                            bundle32.putLong(str72, 0L);
                            bundle32.putLong("_sys", 0L);
                            String str82 = str2;
                            bundle32.putLong(str82, 0L);
                            bundle32.putLong("_et", 1L);
                            if (zzqVar.zzp) {
                            }
                            String str92 = (String) Preconditions.checkNotNull(zzqVar.zza);
                            zzak zzakVar102 = this.zze;
                            zzal(zzakVar102);
                            Preconditions.checkNotEmpty(str92);
                            zzakVar102.zzg();
                            zzakVar102.zzW();
                            zzc = zzakVar102.zzc(str92, "first_open_count");
                            if (this.zzn.zzaw().getPackageManager() == null) {
                            }
                            if (zzc >= 0) {
                            }
                            zzG(new zzau("_f", new zzas(bundle32), "auto", j), zzqVar);
                        }
                    } else if (zzqVar.zzi) {
                        zzG(new zzau("_cd", new zzas(new Bundle()), "auto", j), zzqVar);
                    }
                    zzak zzakVar72 = this.zze;
                    zzal(zzakVar72);
                    zzakVar72.zzC();
                }
                if (zzqVar.zzr != null) {
                    zzlk zzlkVar = new zzlk("_npa", j, Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), "auto");
                    if (zzp == null || !zzp.zze.equals(zzlkVar.zzd)) {
                        zzW(zzlkVar, zzqVar);
                    }
                } else if (zzp != null) {
                    zzP("_npa", zzqVar);
                }
                zzak zzakVar52 = this.zze;
                zzal(zzakVar52);
                zzj = zzakVar52.zzj((String) Preconditions.checkNotNull(zzqVar.zza));
                if (zzj == null) {
                }
                str = "_pfo";
                str2 = "_sysu";
                if (zzj != null) {
                }
                zzd(zzqVar);
                if (i == 0) {
                }
                if (zzn == null) {
                }
                zzak zzakVar722 = this.zze;
                zzal(zzakVar722);
                zzakVar722.zzC();
            } finally {
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzakVar11.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzM() {
        this.zzr++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzN(zzac zzacVar) {
        zzq zzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzac != null) {
            zzO(zzacVar, zzac);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzO(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (zzqVar.zzh) {
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzakVar.zzw();
                try {
                    zzd(zzqVar);
                    String str = (String) Preconditions.checkNotNull(zzacVar.zza);
                    zzak zzakVar2 = this.zze;
                    zzal(zzakVar2);
                    zzac zzk = zzakVar2.zzk(str, zzacVar.zzc.zzb);
                    if (zzk != null) {
                        zzaA().zzc().zzc("Removing conditional user property", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zza(str, zzacVar.zzc.zzb);
                        if (zzk.zze) {
                            zzak zzakVar4 = this.zze;
                            zzal(zzakVar4);
                            zzakVar4.zzA(str, zzacVar.zzc.zzb);
                        }
                        zzau zzauVar = zzacVar.zzk;
                        if (zzauVar != null) {
                            zzas zzasVar = zzauVar.zzb;
                            zzY((zzau) Preconditions.checkNotNull(zzv().zzz(str, ((zzau) Preconditions.checkNotNull(zzacVar.zzk)).zza, zzasVar != null ? zzasVar.zzc() : null, zzk.zzb, zzacVar.zzk.zzd, true, true)), zzqVar);
                        }
                    } else {
                        zzaA().zzk().zzc("Conditional user property doesn't exist", zzet.zzn(zzacVar.zza), this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                    }
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzakVar5.zzC();
                    return;
                } finally {
                    zzak zzakVar6 = this.zze;
                    zzal(zzakVar6);
                    zzakVar6.zzx();
                }
            }
            zzd(zzqVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzP(String str, zzq zzqVar) {
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            if (!"_npa".equals(str) || zzqVar.zzr == null) {
                zzaA().zzc().zzb("Removing user property", this.zzn.zzj().zzf(str));
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzakVar.zzw();
                try {
                    zzd(zzqVar);
                    if ("_id".equals(str)) {
                        zzak zzakVar2 = this.zze;
                        zzal(zzakVar2);
                        zzakVar2.zzA((String) Preconditions.checkNotNull(zzqVar.zza), "_lair");
                    }
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    zzakVar3.zzA((String) Preconditions.checkNotNull(zzqVar.zza), str);
                    zzak zzakVar4 = this.zze;
                    zzal(zzakVar4);
                    zzakVar4.zzC();
                    zzaA().zzc().zzb("User property removed", this.zzn.zzj().zzf(str));
                    return;
                } finally {
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzakVar5.zzx();
                }
            }
            zzaA().zzc().zza("Falling back to manifest metadata value for ad personalization");
            zzW(new zzlk("_npa", zzax().currentTimeMillis(), Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), "auto"), zzqVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzQ(zzq zzqVar) {
        if (this.zzy != null) {
            ArrayList arrayList = new ArrayList();
            this.zzz = arrayList;
            arrayList.addAll(this.zzy);
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        String str = (String) Preconditions.checkNotNull(zzqVar.zza);
        Preconditions.checkNotEmpty(str);
        zzakVar.zzg();
        zzakVar.zzW();
        try {
            SQLiteDatabase zzh = zzakVar.zzh();
            String[] strArr = {str};
            int delete = zzh.delete("apps", "app_id=?", strArr) + zzh.delete("events", "app_id=?", strArr) + zzh.delete("user_attributes", "app_id=?", strArr) + zzh.delete("conditional_properties", "app_id=?", strArr) + zzh.delete("raw_events", "app_id=?", strArr) + zzh.delete("raw_events_metadata", "app_id=?", strArr) + zzh.delete("queue", "app_id=?", strArr) + zzh.delete("audience_filter_values", "app_id=?", strArr) + zzh.delete("main_event_params", "app_id=?", strArr) + zzh.delete("default_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzakVar.zzt.zzaA().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzakVar.zzt.zzaA().zzd().zzc("Error resetting analytics data. appId, error", zzet.zzn(str), e);
        }
        if (zzqVar.zzh) {
            zzL(zzqVar);
        }
    }

    public final void zzR(String str, zzir zzirVar) {
        zzaB().zzg();
        String str2 = this.zzE;
        if (str2 == null || str2.equals(str) || zzirVar != null) {
            this.zzE = str;
            this.zzD = zzirVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzS() {
        zzaB().zzg();
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzakVar.zzz();
        if (this.zzk.zzc.zza() == 0) {
            this.zzk.zzc.zzb(zzax().currentTimeMillis());
        }
        zzag();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzT(zzac zzacVar) {
        zzq zzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzac != null) {
            zzU(zzacVar, zzac);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzU(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzb);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzac zzacVar2 = new zzac(zzacVar);
            boolean z = false;
            zzacVar2.zze = false;
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzac zzk = zzakVar2.zzk((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzc.zzb);
                if (zzk != null && !zzk.zzb.equals(zzacVar2.zzb)) {
                    zzaA().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzb, zzk.zzb);
                }
                if (zzk == null || !zzk.zze) {
                    if (TextUtils.isEmpty(zzacVar2.zzf)) {
                        zzlk zzlkVar = zzacVar2.zzc;
                        zzacVar2.zzc = new zzlk(zzlkVar.zzb, zzacVar2.zzd, zzlkVar.zza(), zzacVar2.zzc.zzf);
                        zzacVar2.zze = true;
                        z = true;
                    }
                } else {
                    zzacVar2.zzb = zzk.zzb;
                    zzacVar2.zzd = zzk.zzd;
                    zzacVar2.zzh = zzk.zzh;
                    zzacVar2.zzf = zzk.zzf;
                    zzacVar2.zzi = zzk.zzi;
                    zzacVar2.zze = true;
                    zzlk zzlkVar2 = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlk(zzlkVar2.zzb, zzk.zzc.zzc, zzlkVar2.zza(), zzk.zzc.zzf);
                }
                if (zzacVar2.zze) {
                    zzlk zzlkVar3 = zzacVar2.zzc;
                    zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzb, zzlkVar3.zzb, zzlkVar3.zzc, Preconditions.checkNotNull(zzlkVar3.zza()));
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    if (zzakVar3.zzL(zzlmVar)) {
                        zzaA().zzc().zzd("User property updated immediately", zzacVar2.zza, this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    } else {
                        zzaA().zzd().zzd("(2)Too many active user properties, ignoring", zzet.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    }
                    if (z && zzacVar2.zzi != null) {
                        zzY(new zzau(zzacVar2.zzi, zzacVar2.zzd), zzqVar);
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                if (zzakVar4.zzK(zzacVar2)) {
                    zzaA().zzc().zzd("Conditional property added", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                } else {
                    zzaA().zzd().zzd("Too many conditional properties, ignoring", zzet.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                }
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzC();
            } finally {
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                zzakVar6.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzV(String str, zzhb zzhbVar) {
        zzaB().zzg();
        zzB();
        this.zzB.put(str, zzhbVar);
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzhbVar);
        zzakVar.zzg();
        zzakVar.zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzhbVar.zzi());
        try {
            if (zzakVar.zzh().insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                zzakVar.zzt.zzaA().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzet.zzn(str));
            }
        } catch (SQLiteException e) {
            zzakVar.zzt.zzaA().zzd().zzc("Error storing consent setting. appId, error", zzet.zzn(str), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzW(zzlk zzlkVar, zzq zzqVar) {
        long j;
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            int zzl = zzv().zzl(zzlkVar.zzb);
            int i = 0;
            if (zzl != 0) {
                zzlp zzv = zzv();
                String str = zzlkVar.zzb;
                zzg();
                String zzD = zzv.zzD(str, 24, true);
                String str2 = zzlkVar.zzb;
                zzv().zzO(this.zzF, zzqVar.zza, zzl, "_ev", zzD, str2 != null ? str2.length() : 0);
                return;
            }
            int zzd = zzv().zzd(zzlkVar.zzb, zzlkVar.zza());
            if (zzd != 0) {
                zzlp zzv2 = zzv();
                String str3 = zzlkVar.zzb;
                zzg();
                String zzD2 = zzv2.zzD(str3, 24, true);
                Object zza = zzlkVar.zza();
                if (zza != null && ((zza instanceof String) || (zza instanceof CharSequence))) {
                    i = zza.toString().length();
                }
                zzv().zzO(this.zzF, zzqVar.zza, zzd, "_ev", zzD2, i);
                return;
            }
            Object zzB = zzv().zzB(zzlkVar.zzb, zzlkVar.zza());
            if (zzB == null) {
                return;
            }
            if ("_sid".equals(zzlkVar.zzb)) {
                long j2 = zzlkVar.zzc;
                String str4 = zzlkVar.zzf;
                String str5 = (String) Preconditions.checkNotNull(zzqVar.zza);
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzlm zzp = zzakVar.zzp(str5, "_sno");
                if (zzp != null) {
                    Object obj = zzp.zze;
                    if (obj instanceof Long) {
                        j = ((Long) obj).longValue();
                        zzW(new zzlk("_sno", j2, Long.valueOf(j + 1), str4), zzqVar);
                    }
                }
                if (zzp != null) {
                    zzaA().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzp.zze);
                }
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzaq zzn = zzakVar2.zzn(str5, "_s");
                if (zzn != null) {
                    j = zzn.zzc;
                    zzaA().zzj().zzb("Backfill the session number. Last used session number", Long.valueOf(j));
                } else {
                    j = 0;
                }
                zzW(new zzlk("_sno", j2, Long.valueOf(j + 1), str4), zzqVar);
            }
            zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzqVar.zza), (String) Preconditions.checkNotNull(zzlkVar.zzf), zzlkVar.zzb, zzlkVar.zzc, zzB);
            zzaA().zzj().zzc("Setting user property", this.zzn.zzj().zzf(zzlmVar.zzc), zzB);
            zzak zzakVar3 = this.zze;
            zzal(zzakVar3);
            zzakVar3.zzw();
            try {
                if ("_id".equals(zzlmVar.zzc)) {
                    zzak zzakVar4 = this.zze;
                    zzal(zzakVar4);
                    zzlm zzp2 = zzakVar4.zzp(zzqVar.zza, "_id");
                    if (zzp2 != null && !zzlmVar.zze.equals(zzp2.zze)) {
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzA(zzqVar.zza, "_lair");
                    }
                }
                zzd(zzqVar);
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                boolean zzL = zzakVar6.zzL(zzlmVar);
                if (zzg().zzs(null, zzeg.zzaH) && "_sid".equals(zzlkVar.zzb)) {
                    zzlj zzljVar = this.zzi;
                    zzal(zzljVar);
                    long zzd2 = zzljVar.zzd(zzqVar.zzx);
                    zzak zzakVar7 = this.zze;
                    zzal(zzakVar7);
                    zzh zzj = zzakVar7.zzj(zzqVar.zza);
                    if (zzj != null) {
                        zzj.zzaj(zzd2);
                        if (zzj.zzao()) {
                            zzak zzakVar8 = this.zze;
                            zzal(zzakVar8);
                            zzakVar8.zzD(zzj);
                        }
                    }
                }
                zzak zzakVar9 = this.zze;
                zzal(zzakVar9);
                zzakVar9.zzC();
                if (!zzL) {
                    zzaA().zzd().zzc("Too many unique user properties are set. Ignoring user property", this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    zzv().zzO(this.zzF, zzqVar.zza, 9, null, null, 0);
                }
            } finally {
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzx();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02fe, code lost:
    
        r0 = r0.subList(0, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0303, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0304, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x04f3, code lost:
    
        if (r3 != null) goto L214;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x04f5, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x051d, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x051a, code lost:
    
        if (r3 == null) goto L230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0108, code lost:
    
        if (r11 != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010a, code lost:
    
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012d, code lost:
    
        r22.zzA = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0112, code lost:
    
        if (r11 != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x012a, code lost:
    
        if (r11 == null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0270 A[Catch: all -> 0x053f, TRY_ENTER, TRY_LEAVE, TryCatch #22 {all -> 0x053f, blocks: (B:81:0x029f, B:83:0x02a5, B:85:0x02b1, B:86:0x02b5, B:88:0x02bb, B:91:0x02cf, B:94:0x02d8, B:96:0x02de, B:101:0x02f3, B:117:0x030a, B:119:0x0325, B:122:0x0334, B:124:0x0358, B:129:0x0392, B:131:0x0397, B:133:0x039f, B:134:0x03a2, B:136:0x03a7, B:137:0x03aa, B:139:0x03b6, B:141:0x03cc, B:147:0x03d8, B:149:0x03e9, B:150:0x03fa, B:152:0x040f, B:154:0x041c, B:155:0x0431, B:159:0x0441, B:160:0x0445, B:162:0x042a, B:163:0x0494, B:188:0x0270, B:210:0x029c, B:230:0x04af, B:231:0x04b2, B:240:0x04b3, B:248:0x04f5, B:250:0x051e, B:252:0x0524, B:254:0x052f, B:258:0x0500, B:268:0x053b, B:269:0x053e, B:158:0x043d), top: B:36:0x00eb, inners: #16 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0134 A[Catch: all -> 0x0542, TryCatch #2 {all -> 0x0542, blocks: (B:3:0x0010, B:5:0x0021, B:10:0x0034, B:12:0x003a, B:14:0x004a, B:16:0x0052, B:18:0x0058, B:20:0x0063, B:22:0x0073, B:24:0x007e, B:26:0x0091, B:28:0x00b0, B:30:0x00b6, B:32:0x00b9, B:34:0x00c5, B:35:0x00dc, B:38:0x00ed, B:40:0x00f3, B:47:0x010a, B:48:0x012d, B:59:0x0134, B:60:0x0137, B:65:0x0138, B:68:0x0160, B:71:0x0168, B:79:0x019e, B:158:0x043d), top: B:2:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02a5 A[Catch: all -> 0x053f, TryCatch #22 {all -> 0x053f, blocks: (B:81:0x029f, B:83:0x02a5, B:85:0x02b1, B:86:0x02b5, B:88:0x02bb, B:91:0x02cf, B:94:0x02d8, B:96:0x02de, B:101:0x02f3, B:117:0x030a, B:119:0x0325, B:122:0x0334, B:124:0x0358, B:129:0x0392, B:131:0x0397, B:133:0x039f, B:134:0x03a2, B:136:0x03a7, B:137:0x03aa, B:139:0x03b6, B:141:0x03cc, B:147:0x03d8, B:149:0x03e9, B:150:0x03fa, B:152:0x040f, B:154:0x041c, B:155:0x0431, B:159:0x0441, B:160:0x0445, B:162:0x042a, B:163:0x0494, B:188:0x0270, B:210:0x029c, B:230:0x04af, B:231:0x04b2, B:240:0x04b3, B:248:0x04f5, B:250:0x051e, B:252:0x0524, B:254:0x052f, B:258:0x0500, B:268:0x053b, B:269:0x053e, B:158:0x043d), top: B:36:0x00eb, inners: #16 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzX() {
        boolean z;
        Cursor cursor;
        zzak zzakVar;
        long zzz;
        Cursor cursor2;
        Cursor cursor3;
        long j;
        Cursor cursor4;
        List emptyList;
        String str;
        String str2;
        byte[] byteArray;
        Cursor cursor5;
        zzaB().zzg();
        zzB();
        int i = 1;
        this.zzv = true;
        int i2 = 0;
        try {
            this.zzn.zzay();
            Boolean zzj = this.zzn.zzt().zzj();
            if (zzj == null) {
                zzaA().zzk().zza("Upload data called on the client side before use of service was decided");
                this.zzv = false;
            } else if (zzj.booleanValue()) {
                zzaA().zzd().zza("Upload called in the client side when service should be used");
                this.zzv = false;
            } else if (this.zza > 0) {
                zzag();
                this.zzv = false;
            } else {
                zzaB().zzg();
                if (this.zzy != null) {
                    zzaA().zzj().zza("Uploading requested multiple times");
                    this.zzv = false;
                } else {
                    zzez zzezVar = this.zzd;
                    zzal(zzezVar);
                    if (zzezVar.zza()) {
                        long currentTimeMillis = zzax().currentTimeMillis();
                        Cursor cursor6 = null;
                        int zze = zzg().zze(null, zzeg.zzR);
                        zzg();
                        long zzz2 = currentTimeMillis - zzag.zzz();
                        for (int i3 = 0; i3 < zze && zzah(null, zzz2); i3++) {
                        }
                        long zza = this.zzk.zzc.zza();
                        if (zza != 0) {
                            zzaA().zzc().zzb("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - zza)));
                        }
                        zzak zzakVar2 = this.zze;
                        zzal(zzakVar2);
                        String zzr = zzakVar2.zzr();
                        long j2 = -1;
                        try {
                            if (TextUtils.isEmpty(zzr)) {
                                try {
                                    this.zzA = -1L;
                                    zzakVar = this.zze;
                                    zzal(zzakVar);
                                    zzg();
                                    zzz = currentTimeMillis - zzag.zzz();
                                    zzakVar.zzg();
                                    zzakVar.zzW();
                                } catch (Throwable th) {
                                    th = th;
                                    cursor = null;
                                }
                                try {
                                    cursor2 = zzakVar.zzh().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(zzz)});
                                    try {
                                    } catch (SQLiteException e) {
                                        e = e;
                                        zzakVar.zzt.zzaA().zzd().zzb("Error selecting expired configs", e);
                                    }
                                } catch (SQLiteException e2) {
                                    e = e2;
                                    cursor2 = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = null;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    throw th;
                                }
                                if (cursor2.moveToFirst()) {
                                    String str3 = cursor2.getString(0);
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (!TextUtils.isEmpty(str3)) {
                                        zzak zzakVar3 = this.zze;
                                        zzal(zzakVar3);
                                        zzh zzj2 = zzakVar3.zzj(str3);
                                        if (zzj2 != null) {
                                            zzD(zzj2);
                                        }
                                    }
                                } else {
                                    zzakVar.zzt.zzaA().zzj().zza("No expired configs for apps with pending events");
                                }
                            } else {
                                if (this.zzA == -1) {
                                    zzak zzakVar4 = this.zze;
                                    zzal(zzakVar4);
                                    try {
                                        cursor5 = zzakVar4.zzh().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                                        try {
                                            try {
                                                if (cursor5.moveToFirst()) {
                                                    j2 = cursor5.getLong(0);
                                                }
                                            } catch (SQLiteException e3) {
                                                e = e3;
                                                zzakVar4.zzt.zzaA().zzd().zzb("Error querying raw events", e);
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            cursor6 = cursor5;
                                            if (cursor6 != null) {
                                                cursor6.close();
                                            }
                                            throw th;
                                        }
                                    } catch (SQLiteException e4) {
                                        e = e4;
                                        cursor5 = null;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        if (cursor6 != null) {
                                        }
                                        throw th;
                                    }
                                }
                                int zze2 = zzg().zze(zzr, zzeg.zzg);
                                int max = Math.max(0, zzg().zze(zzr, zzeg.zzh));
                                zzak zzakVar5 = this.zze;
                                zzal(zzakVar5);
                                zzakVar5.zzg();
                                zzakVar5.zzW();
                                Preconditions.checkArgument(zze2 > 0);
                                Preconditions.checkArgument(max > 0);
                                Preconditions.checkNotEmpty(zzr);
                                try {
                                    cursor4 = zzakVar5.zzh().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{zzr}, null, null, "rowid", String.valueOf(zze2));
                                    try {
                                        try {
                                            if (cursor4.moveToFirst()) {
                                                ArrayList arrayList = new ArrayList();
                                                int i4 = 0;
                                                while (true) {
                                                    long j3 = cursor4.getLong(i2);
                                                    try {
                                                        byte[] blob = cursor4.getBlob(i);
                                                        zzlj zzljVar = zzakVar5.zzf.zzi;
                                                        zzal(zzljVar);
                                                        try {
                                                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(blob);
                                                            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                                            byte[] bArr = new byte[1024];
                                                            j = currentTimeMillis;
                                                            while (true) {
                                                                try {
                                                                    try {
                                                                        int read = gZIPInputStream.read(bArr);
                                                                        if (read <= 0) {
                                                                            break;
                                                                        } else {
                                                                            byteArrayOutputStream.write(bArr, 0, read);
                                                                        }
                                                                    } catch (SQLiteException e5) {
                                                                        e = e5;
                                                                        zzakVar5.zzt.zzaA().zzd().zzc("Error querying bundles. appId", zzet.zzn(zzr), e);
                                                                        emptyList = Collections.emptyList();
                                                                        if (cursor4 != null) {
                                                                            cursor4.close();
                                                                        }
                                                                        if (!emptyList.isEmpty()) {
                                                                        }
                                                                        this.zzv = false;
                                                                        zzae();
                                                                    }
                                                                } catch (IOException e6) {
                                                                    e = e6;
                                                                    try {
                                                                        zzljVar.zzt.zzaA().zzd().zzb("Failed to ungzip content", e);
                                                                        throw e;
                                                                        break;
                                                                    } catch (IOException e7) {
                                                                        e = e7;
                                                                        zzakVar5.zzt.zzaA().zzd().zzc("Failed to unzip queued bundle. appId", zzet.zzn(zzr), e);
                                                                        if (cursor4.moveToNext()) {
                                                                            break;
                                                                        }
                                                                        currentTimeMillis = j;
                                                                        i = 1;
                                                                        i2 = 0;
                                                                        if (cursor4 != null) {
                                                                        }
                                                                        emptyList = arrayList;
                                                                        if (!emptyList.isEmpty()) {
                                                                        }
                                                                        this.zzv = false;
                                                                        zzae();
                                                                    }
                                                                }
                                                            }
                                                            gZIPInputStream.close();
                                                            byteArrayInputStream.close();
                                                            byteArray = byteArrayOutputStream.toByteArray();
                                                        } catch (IOException e8) {
                                                            e = e8;
                                                            j = currentTimeMillis;
                                                        }
                                                    } catch (IOException e9) {
                                                        e = e9;
                                                        j = currentTimeMillis;
                                                    }
                                                    if (!arrayList.isEmpty() && byteArray.length + i4 > max) {
                                                        break;
                                                    }
                                                    try {
                                                        com.google.android.gms.internal.measurement.zzgc zzgcVar = (com.google.android.gms.internal.measurement.zzgc) zzlj.zzm(com.google.android.gms.internal.measurement.zzgd.zzu(), byteArray);
                                                        if (!cursor4.isNull(2)) {
                                                            zzgcVar.zzaf(cursor4.getInt(2));
                                                        }
                                                        i4 += byteArray.length;
                                                        arrayList.add(Pair.create((com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD(), Long.valueOf(j3)));
                                                    } catch (IOException e10) {
                                                        zzakVar5.zzt.zzaA().zzd().zzc("Failed to merge queued bundle. appId", zzet.zzn(zzr), e10);
                                                    }
                                                    if (cursor4.moveToNext() || i4 > max) {
                                                        break;
                                                        break;
                                                    } else {
                                                        currentTimeMillis = j;
                                                        i = 1;
                                                        i2 = 0;
                                                    }
                                                }
                                                if (cursor4 != null) {
                                                    cursor4.close();
                                                }
                                                emptyList = arrayList;
                                            } else {
                                                emptyList = Collections.emptyList();
                                                if (cursor4 != null) {
                                                    cursor4.close();
                                                }
                                                j = currentTimeMillis;
                                            }
                                        } catch (SQLiteException e11) {
                                            e = e11;
                                            j = currentTimeMillis;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        cursor3 = cursor4;
                                        if (cursor3 != null) {
                                            cursor3.close();
                                        }
                                        throw th;
                                    }
                                } catch (SQLiteException e12) {
                                    e = e12;
                                    j = currentTimeMillis;
                                    cursor4 = null;
                                } catch (Throwable th6) {
                                    th = th6;
                                    cursor3 = null;
                                }
                                if (!emptyList.isEmpty()) {
                                    if (zzq(zzr).zzj(zzha.AD_STORAGE)) {
                                        Iterator it = emptyList.iterator();
                                        while (true) {
                                            if (!it.hasNext()) {
                                                str2 = null;
                                                break;
                                            }
                                            com.google.android.gms.internal.measurement.zzgd zzgdVar = (com.google.android.gms.internal.measurement.zzgd) ((Pair) it.next()).first;
                                            if (!zzgdVar.zzK().isEmpty()) {
                                                str2 = zzgdVar.zzK();
                                                break;
                                            }
                                        }
                                        if (str2 != null) {
                                            int i5 = 0;
                                            while (true) {
                                                if (i5 >= emptyList.size()) {
                                                    break;
                                                }
                                                com.google.android.gms.internal.measurement.zzgd zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) ((Pair) emptyList.get(i5)).first;
                                                if (!zzgdVar2.zzK().isEmpty() && !zzgdVar2.zzK().equals(str2)) {
                                                    break;
                                                }
                                                i5++;
                                            }
                                        }
                                    }
                                    com.google.android.gms.internal.measurement.zzga zza2 = com.google.android.gms.internal.measurement.zzgb.zza();
                                    int size = emptyList.size();
                                    ArrayList arrayList2 = new ArrayList(emptyList.size());
                                    boolean z2 = zzg().zzt(zzr) && zzq(zzr).zzj(zzha.AD_STORAGE);
                                    boolean zzj3 = zzq(zzr).zzj(zzha.AD_STORAGE);
                                    boolean zzj4 = zzq(zzr).zzj(zzha.ANALYTICS_STORAGE);
                                    zzqu.zzc();
                                    boolean zzs = zzg().zzs(zzr, zzeg.zzao);
                                    int i6 = 0;
                                    while (i6 < size) {
                                        com.google.android.gms.internal.measurement.zzgc zzgcVar2 = (com.google.android.gms.internal.measurement.zzgc) ((com.google.android.gms.internal.measurement.zzgd) ((Pair) emptyList.get(i6)).first).zzbB();
                                        arrayList2.add((Long) ((Pair) emptyList.get(i6)).second);
                                        zzg().zzh();
                                        zzgcVar2.zzam(79000L);
                                        long j4 = j;
                                        zzgcVar2.zzal(j4);
                                        this.zzn.zzay();
                                        try {
                                            zzgcVar2.zzag(false);
                                            if (!z2) {
                                                zzgcVar2.zzq();
                                            }
                                            if (!zzj3) {
                                                zzgcVar2.zzx();
                                                zzgcVar2.zzt();
                                            }
                                            if (!zzj4) {
                                                zzgcVar2.zzn();
                                            }
                                            zzC(zzr, zzgcVar2);
                                            if (!zzs) {
                                                zzgcVar2.zzy();
                                            }
                                            if (zzg().zzs(zzr, zzeg.zzV)) {
                                                byte[] zzbx = ((com.google.android.gms.internal.measurement.zzgd) zzgcVar2.zzaD()).zzbx();
                                                zzlj zzljVar2 = this.zzi;
                                                zzal(zzljVar2);
                                                zzgcVar2.zzJ(zzljVar2.zzf(zzbx));
                                            }
                                            zza2.zza(zzgcVar2);
                                            i6++;
                                            j = j4;
                                        } catch (Throwable th7) {
                                            th = th7;
                                            z = false;
                                            this.zzv = z;
                                            zzae();
                                            throw th;
                                        }
                                    }
                                    long j5 = j;
                                    if (Log.isLoggable(zzaA().zzr(), 2)) {
                                        zzlj zzljVar3 = this.zzi;
                                        zzal(zzljVar3);
                                        str = zzljVar3.zzo((com.google.android.gms.internal.measurement.zzgb) zza2.zzaD());
                                    } else {
                                        str = null;
                                    }
                                    zzal(this.zzi);
                                    byte[] zzbx2 = ((com.google.android.gms.internal.measurement.zzgb) zza2.zzaD()).zzbx();
                                    zzkv zza3 = this.zzl.zza(zzr);
                                    try {
                                        Preconditions.checkArgument(!arrayList2.isEmpty());
                                        if (this.zzy != null) {
                                            zzaA().zzd().zza("Set uploading progress before finishing the previous upload");
                                        } else {
                                            this.zzy = new ArrayList(arrayList2);
                                        }
                                        this.zzk.zzd.zzb(j5);
                                        zzaA().zzj().zzd("Uploading data. app, uncompressed size, data", size > 0 ? zza2.zzb(0).zzy() : "?", Integer.valueOf(zzbx2.length), str);
                                        this.zzu = true;
                                        zzez zzezVar2 = this.zzd;
                                        zzal(zzezVar2);
                                        URL url = new URL(zza3.zza());
                                        Map zzb2 = zza3.zzb();
                                        zzky zzkyVar = new zzky(this, zzr);
                                        zzezVar2.zzg();
                                        zzezVar2.zzW();
                                        Preconditions.checkNotNull(url);
                                        Preconditions.checkNotNull(zzbx2);
                                        Preconditions.checkNotNull(zzkyVar);
                                        zzezVar2.zzt.zzaB().zzo(new zzey(zzezVar2, zzr, url, zzbx2, zzb2, zzkyVar));
                                    } catch (MalformedURLException unused) {
                                        zzaA().zzd().zzc("Failed to parse upload URL. Not uploading. appId", zzet.zzn(zzr), zza3.zza());
                                    }
                                }
                            }
                            this.zzv = false;
                        } catch (Throwable th8) {
                            th = th8;
                            z = false;
                            this.zzv = z;
                            zzae();
                            throw th;
                        }
                    } else {
                        zzaA().zzj().zza("Network not connected, ignoring upload request");
                        zzag();
                        this.zzv = false;
                    }
                }
            }
            zzae();
        } catch (Throwable th9) {
            th = th9;
            z = false;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:319|(2:321|(1:323)(7:324|325|(1:327)|59|(0)(0)|62|(0)(0)))|328|329|330|331|332|333|334|335|336|337|338|339|325|(0)|59|(0)(0)|62|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x07d5, code lost:
    
        if (r14.isEmpty() == false) goto L235;
     */
    /* JADX WARN: Code restructure failed: missing block: B:341:0x02f6, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x0309, code lost:
    
        r11.zzt.zzaA().zzd().zzc("Error pruning currencies. appId", com.google.android.gms.measurement.internal.zzet.zzn(r10), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x02f8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x02fd, code lost:
    
        r33 = "app_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x0300, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0301, code lost:
    
        r32 = "metadata_fingerprint";
        r33 = "app_id";
        r18 = r18;
     */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0577 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x05b7 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x067b A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0688 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0695 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x06cd A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x06de A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0721 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0748 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x077a A[Catch: all -> 0x0b59, TRY_LEAVE, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x07da A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0820 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0871 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x087e A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0897 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0925 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0945 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0a13 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0ac2 A[Catch: SQLiteException -> 0x0add, all -> 0x0b59, TRY_LEAVE, TryCatch #10 {SQLiteException -> 0x0add, blocks: (B:243:0x0ab2, B:245:0x0ac2), top: B:242:0x0ab2, outer: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0ad8  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0a25 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:279:0x074d A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0630 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0389 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:305:0x01e7 A[Catch: all -> 0x0b59, TRY_ENTER, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x025b A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0343 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x024b A[Catch: all -> 0x0b59, TRY_ENTER, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x03e8 A[Catch: all -> 0x0b59, TryCatch #7 {all -> 0x0b59, blocks: (B:45:0x019f, B:48:0x01ae, B:50:0x01b8, B:54:0x01c4, B:59:0x0373, B:62:0x03a8, B:64:0x03e8, B:66:0x03ee, B:67:0x0405, B:71:0x0418, B:73:0x042f, B:75:0x0435, B:76:0x044c, B:81:0x0476, B:85:0x0497, B:86:0x04ae, B:89:0x04bf, B:92:0x04dc, B:93:0x04f0, B:95:0x04fa, B:97:0x0507, B:99:0x050d, B:100:0x0516, B:102:0x0524, B:105:0x0539, B:108:0x054e, B:112:0x0577, B:113:0x058c, B:115:0x05b7, B:118:0x05cf, B:121:0x0612, B:122:0x063e, B:124:0x067b, B:125:0x0680, B:127:0x0688, B:128:0x068d, B:130:0x0695, B:131:0x069a, B:133:0x06a5, B:135:0x06b1, B:137:0x06bf, B:138:0x06c4, B:140:0x06cd, B:141:0x06d1, B:143:0x06de, B:144:0x06e3, B:146:0x070c, B:148:0x0714, B:149:0x0719, B:151:0x0721, B:152:0x0724, B:154:0x0748, B:156:0x0753, B:159:0x075b, B:160:0x0774, B:162:0x077a, B:165:0x078e, B:168:0x079a, B:171:0x07a7, B:274:0x07c1, B:174:0x07d1, B:177:0x07da, B:178:0x07dd, B:180:0x07fb, B:182:0x07ff, B:184:0x0811, B:186:0x0815, B:188:0x0820, B:189:0x082b, B:191:0x0871, B:192:0x0876, B:194:0x087e, B:196:0x0887, B:197:0x088a, B:199:0x0897, B:201:0x08b9, B:202:0x08c6, B:203:0x08fc, B:205:0x0904, B:207:0x090e, B:208:0x091b, B:210:0x0925, B:211:0x0932, B:212:0x093f, B:214:0x0945, B:216:0x097e, B:218:0x098e, B:220:0x0998, B:222:0x09ab, B:230:0x09b1, B:232:0x09f7, B:233:0x0a01, B:234:0x0a0d, B:236:0x0a13, B:241:0x0a64, B:243:0x0ab2, B:245:0x0ac2, B:246:0x0b26, B:251:0x0ada, B:253:0x0ade, B:255:0x0a25, B:257:0x0a4f, B:263:0x0af7, B:264:0x0b0e, B:268:0x0b11, B:279:0x074d, B:280:0x0630, B:284:0x055f, B:291:0x0389, B:292:0x0390, B:294:0x0396, B:297:0x03a2, B:302:0x01db, B:305:0x01e7, B:307:0x01fe, B:312:0x0217, B:315:0x0255, B:317:0x025b, B:319:0x0269, B:321:0x027e, B:324:0x0285, B:325:0x0338, B:327:0x0343, B:328:0x02b7, B:330:0x02d4, B:335:0x02df, B:338:0x02e8, B:339:0x031c, B:343:0x0309, B:351:0x0225, B:354:0x024b), top: B:44:0x019f, inners: #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0416  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void zzY(zzau zzauVar, zzq zzqVar) {
        boolean z;
        String zzg;
        long longValue;
        String str;
        String str2;
        String str3;
        zzlm zzlmVar;
        zzak zzakVar;
        zzas zzasVar;
        long j;
        long intValue;
        String str4;
        long j2;
        zzap zzapVar;
        String str5;
        int i;
        String str6;
        zzaq zzn;
        zzaq zzc;
        com.google.android.gms.internal.measurement.zzgc zzu;
        long j3;
        long j4;
        Map emptyMap;
        ArrayList arrayList;
        zzhb zzd;
        zzh zzj;
        List zzu2;
        int i2;
        zzak zzakVar2;
        zzak zzakVar3;
        zzar zzarVar;
        ContentValues contentValues;
        String str7;
        Pair zzd2;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        long nanoTime = System.nanoTime();
        zzaB().zzg();
        zzB();
        String str8 = zzqVar.zza;
        zzal(this.zzi);
        if (!zzlj.zzB(zzauVar, zzqVar)) {
            return;
        }
        if (!zzqVar.zzh) {
            zzd(zzqVar);
            return;
        }
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        String str9 = "_err";
        if (zzfuVar.zzr(str8, zzauVar.zza)) {
            zzaA().zzk().zzc("Dropping blocked event. appId", zzet.zzn(str8), this.zzn.zzj().zzd(zzauVar.zza));
            zzfu zzfuVar2 = this.zzc;
            zzal(zzfuVar2);
            if (!zzfuVar2.zzp(str8)) {
                zzfu zzfuVar3 = this.zzc;
                zzal(zzfuVar3);
                if (!zzfuVar3.zzs(str8)) {
                    if ("_err".equals(zzauVar.zza)) {
                        return;
                    }
                    zzv().zzO(this.zzF, str8, 11, "_ev", zzauVar.zza, 0);
                    return;
                }
            }
            zzak zzakVar4 = this.zze;
            zzal(zzakVar4);
            zzh zzj2 = zzakVar4.zzj(str8);
            if (zzj2 != null) {
                long abs = Math.abs(zzax().currentTimeMillis() - Math.max(zzj2.zzl(), zzj2.zzc()));
                zzg();
                if (abs > ((Long) zzeg.zzz.zza(null)).longValue()) {
                    zzaA().zzc().zza("Fetching config for blocked app");
                    zzD(zzj2);
                    return;
                }
                return;
            }
            return;
        }
        zzeu zzb2 = zzeu.zzb(zzauVar);
        zzv().zzN(zzb2, zzg().zzd(str8));
        zzpq.zzc();
        int zzf = zzg().zzs(null, zzeg.zzaA) ? zzg().zzf(str8, zzeg.zzQ, 10, 35) : 0;
        Iterator it = new TreeSet(zzb2.zzd.keySet()).iterator();
        while (it.hasNext()) {
            String str10 = (String) it.next();
            if (FirebaseAnalytics.Param.ITEMS.equals(str10)) {
                zzlp zzv = zzv();
                Iterator it2 = it;
                Parcelable[] parcelableArray = zzb2.zzd.getParcelableArray(str10);
                zzpq.zzc();
                zzv.zzM(parcelableArray, zzf, zzg().zzs(null, zzeg.zzaA));
                it = it2;
                str9 = str9;
                nanoTime = nanoTime;
            }
        }
        long j5 = nanoTime;
        String str11 = str9;
        zzau zza = zzb2.zza();
        if (Log.isLoggable(zzaA().zzr(), 2)) {
            zzaA().zzj().zzb("Logging event", this.zzn.zzj().zzc(zza));
        }
        zzpn.zzc();
        zzg().zzs(null, zzeg.zzax);
        zzak zzakVar5 = this.zze;
        zzal(zzakVar5);
        zzakVar5.zzw();
        try {
            zzd(zzqVar);
            if (!"ecommerce_purchase".equals(zza.zza) && !FirebaseAnalytics.Event.PURCHASE.equals(zza.zza) && !FirebaseAnalytics.Event.REFUND.equals(zza.zza)) {
                z = false;
                if (!"_iap".equals(zza.zza)) {
                    if (z) {
                        z = true;
                    }
                    str = "metadata_fingerprint";
                    str2 = "app_id";
                    str3 = str11;
                    boolean zzak = zzlp.zzak(zza.zza);
                    boolean equals = str3.equals(zza.zza);
                    zzv();
                    zzasVar = zza.zzb;
                    if (zzasVar == null) {
                        j = 0;
                    } else {
                        zzar zzarVar2 = new zzar(zzasVar);
                        j = 0;
                        while (zzarVar2.hasNext()) {
                            if (zzasVar.zzf(zzarVar2.next()) instanceof Parcelable[]) {
                                j += ((Parcelable[]) r14).length;
                            }
                        }
                    }
                    zzak zzakVar6 = this.zze;
                    zzal(zzakVar6);
                    zzai zzm = zzakVar6.zzm(zza(), str8, j + 1, true, zzak, false, equals, false);
                    long j6 = zzm.zzb;
                    zzg();
                    intValue = j6 - ((Integer) zzeg.zzk.zza(null)).intValue();
                    if (intValue > 0) {
                        if (intValue % 1000 == 1) {
                            zzaA().zzd().zzc("Data loss. Too many events logged. appId, count", zzet.zzn(str8), Long.valueOf(zzm.zzb));
                        }
                        zzak zzakVar7 = this.zze;
                        zzal(zzakVar7);
                        zzakVar7.zzC();
                        return;
                    }
                    if (zzak) {
                        long j7 = zzm.zza;
                        zzg();
                        long intValue2 = j7 - ((Integer) zzeg.zzm.zza(null)).intValue();
                        if (intValue2 > 0) {
                            if (intValue2 % 1000 == 1) {
                                zzaA().zzd().zzc("Data loss. Too many public events logged. appId, count", zzet.zzn(str8), Long.valueOf(zzm.zza));
                            }
                            zzv().zzO(this.zzF, str8, 16, "_ev", zza.zza, 0);
                            zzak zzakVar8 = this.zze;
                            zzal(zzakVar8);
                            zzakVar8.zzC();
                            return;
                        }
                    }
                    if (equals) {
                        long max = zzm.zzd - Math.max(0, Math.min(1000000, zzg().zze(zzqVar.zza, zzeg.zzl)));
                        if (max > 0) {
                            if (max == 1) {
                                zzaA().zzd().zzc("Too many error events logged. appId, count", zzet.zzn(str8), Long.valueOf(zzm.zzd));
                            }
                            zzak zzakVar9 = this.zze;
                            zzal(zzakVar9);
                            zzakVar9.zzC();
                            return;
                        }
                    }
                    Bundle zzc2 = zza.zzb.zzc();
                    zzv().zzP(zzc2, "_o", zza.zzc);
                    if (zzv().zzaf(str8)) {
                        zzv().zzP(zzc2, "_dbg", 1L);
                        zzv().zzP(zzc2, "_r", 1L);
                    }
                    if ("_s".equals(zza.zza)) {
                        zzak zzakVar10 = this.zze;
                        zzal(zzakVar10);
                        zzlm zzp = zzakVar10.zzp(zzqVar.zza, "_sno");
                        if (zzp != null && (zzp.zze instanceof Long)) {
                            zzv().zzP(zzc2, "_sno", zzp.zze);
                        }
                    }
                    zzak zzakVar11 = this.zze;
                    zzal(zzakVar11);
                    Preconditions.checkNotEmpty(str8);
                    zzakVar11.zzg();
                    zzakVar11.zzW();
                    try {
                        try {
                            str4 = "raw_events";
                            try {
                                j2 = zzakVar11.zzh().delete(str4, "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str8, String.valueOf(Math.max(0, Math.min(1000000, zzakVar11.zzt.zzf().zze(str8, zzeg.zzp))))});
                            } catch (SQLiteException e) {
                                e = e;
                                zzakVar11.zzt.zzaA().zzd().zzc("Error deleting over the limit events. appId", zzet.zzn(str8), e);
                                j2 = 0;
                                if (j2 > 0) {
                                }
                                zzgd zzgdVar = this.zzn;
                                String str12 = zza.zzc;
                                String str13 = zza.zza;
                                long j8 = zza.zzd;
                                str5 = str4;
                                i = 0;
                                str6 = "_r";
                                zzapVar = new zzap(zzgdVar, str12, str8, str13, j8, 0L, zzc2);
                                zzak zzakVar12 = this.zze;
                                zzal(zzakVar12);
                                zzn = zzakVar12.zzn(str8, zzapVar.zzb);
                                if (zzn != null) {
                                }
                                zzak zzakVar13 = this.zze;
                                zzal(zzakVar13);
                                zzakVar13.zzE(zzc);
                                zzaB().zzg();
                                zzB();
                                Preconditions.checkNotNull(zzapVar);
                                Preconditions.checkNotNull(zzqVar);
                                Preconditions.checkNotEmpty(zzapVar.zza);
                                Preconditions.checkArgument(zzapVar.zza.equals(zzqVar.zza));
                                zzu = com.google.android.gms.internal.measurement.zzgd.zzu();
                                zzu.zzad(1);
                                zzu.zzZ("android");
                                if (!TextUtils.isEmpty(zzqVar.zza)) {
                                }
                                if (!TextUtils.isEmpty(zzqVar.zzd)) {
                                }
                                if (!TextUtils.isEmpty(zzqVar.zzc)) {
                                }
                                zzqu.zzc();
                                if (!TextUtils.isEmpty(zzqVar.zzx)) {
                                    zzu.zzah(zzqVar.zzx);
                                }
                                j3 = zzqVar.zzj;
                                if (j3 != -2147483648L) {
                                }
                                zzu.zzV(zzqVar.zze);
                                if (!TextUtils.isEmpty(zzqVar.zzb)) {
                                }
                                zzu.zzL(zzq((String) Preconditions.checkNotNull(zzqVar.zza)).zzd(zzhb.zzc(zzqVar.zzv, 100)).zzi());
                                if (zzu.zzar().isEmpty()) {
                                    zzu.zzC(zzqVar.zzq);
                                }
                                j4 = zzqVar.zzf;
                                if (j4 != 0) {
                                }
                                zzu.zzP(zzqVar.zzs);
                                zzlj zzljVar = this.zzi;
                                zzal(zzljVar);
                                com.google.android.gms.internal.measurement.zzhf zza2 = com.google.android.gms.internal.measurement.zzhf.zza(zzljVar.zzf.zzn.zzaw().getContentResolver(), com.google.android.gms.internal.measurement.zzhq.zza("com.google.android.gms.measurement"), new Runnable() { // from class: com.google.android.gms.measurement.internal.zzaw
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        com.google.android.gms.internal.measurement.zzib.zzc();
                                    }
                                });
                                if (zza2 != null) {
                                }
                                if (emptyMap != null) {
                                    arrayList = new ArrayList();
                                    int intValue3 = ((Integer) zzeg.zzP.zza(null)).intValue();
                                    while (r8.hasNext()) {
                                    }
                                }
                                arrayList = null;
                                if (arrayList != null) {
                                }
                                zzd = zzq((String) Preconditions.checkNotNull(zzqVar.zza)).zzd(zzhb.zzc(zzqVar.zzv, 100));
                                if (zzd.zzj(zzha.AD_STORAGE)) {
                                    zzd2 = this.zzk.zzd(zzqVar.zza, zzd);
                                    if (!TextUtils.isEmpty((CharSequence) zzd2.first)) {
                                        zzu.zzae((String) zzd2.first);
                                        if (zzd2.second != null) {
                                        }
                                    }
                                }
                                this.zzn.zzg().zzv();
                                zzu.zzN(Build.MODEL);
                                this.zzn.zzg().zzv();
                                zzu.zzY(Build.VERSION.RELEASE);
                                zzu.zzak((int) this.zzn.zzg().zzb());
                                zzu.zzao(this.zzn.zzg().zzc());
                                zzpz.zzc();
                                if (zzg().zzs(null, zzeg.zzaE)) {
                                }
                                if (this.zzn.zzJ()) {
                                }
                                zzak zzakVar14 = this.zze;
                                zzal(zzakVar14);
                                zzj = zzakVar14.zzj(zzqVar.zza);
                                if (zzj == null) {
                                }
                                if (zzd.zzj(zzha.ANALYTICS_STORAGE)) {
                                    zzu.zzE((String) Preconditions.checkNotNull(zzj.zzw()));
                                }
                                if (!TextUtils.isEmpty(zzj.zzz())) {
                                }
                                zzak zzakVar15 = this.zze;
                                zzal(zzakVar15);
                                zzu2 = zzakVar15.zzu(zzqVar.zza);
                                while (i2 < zzu2.size()) {
                                }
                                zzakVar2 = this.zze;
                                zzal(zzakVar2);
                                com.google.android.gms.internal.measurement.zzgd zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) zzu.zzaD();
                                zzakVar2.zzg();
                                zzakVar2.zzW();
                                Preconditions.checkNotNull(zzgdVar2);
                                Preconditions.checkNotEmpty(zzgdVar2.zzy());
                                byte[] zzbx = zzgdVar2.zzbx();
                                zzlj zzljVar2 = zzakVar2.zzf.zzi;
                                zzal(zzljVar2);
                                long zzf2 = zzljVar2.zzf(zzbx);
                                ContentValues contentValues2 = new ContentValues();
                                String str14 = str2;
                                contentValues2.put(str14, zzgdVar2.zzy());
                                String str15 = str;
                                contentValues2.put(str15, Long.valueOf(zzf2));
                                contentValues2.put(TtmlNode.TAG_METADATA, zzbx);
                                try {
                                    zzakVar2.zzh().insertWithOnConflict("raw_events_metadata", null, contentValues2, 4);
                                    zzakVar3 = this.zze;
                                    zzal(zzakVar3);
                                    zzarVar = new zzar(zzapVar.zzf);
                                    while (true) {
                                        if (zzarVar.hasNext()) {
                                        }
                                        str6 = str7;
                                    }
                                    zzakVar3.zzg();
                                    zzakVar3.zzW();
                                    Preconditions.checkNotNull(zzapVar);
                                    Preconditions.checkNotEmpty(zzapVar.zza);
                                    zzlj zzljVar3 = zzakVar3.zzf.zzi;
                                    zzal(zzljVar3);
                                    byte[] zzbx2 = zzljVar3.zzl(zzapVar).zzbx();
                                    contentValues = new ContentValues();
                                    contentValues.put(str14, zzapVar.zza);
                                    contentValues.put("name", zzapVar.zzb);
                                    contentValues.put("timestamp", Long.valueOf(zzapVar.zzd));
                                    contentValues.put(str15, Long.valueOf(zzf2));
                                    contentValues.put("data", zzbx2);
                                    contentValues.put("realtime", Integer.valueOf(i));
                                    if (zzakVar3.zzh().insert(str5, null, contentValues) != -1) {
                                    }
                                    zzak zzakVar16 = this.zze;
                                    zzal(zzakVar16);
                                    zzakVar16.zzC();
                                    zzak zzakVar17 = this.zze;
                                    zzal(zzakVar17);
                                    zzakVar17.zzx();
                                    zzag();
                                    zzaA().zzj().zzb("Background event processing time, ms", Long.valueOf(((System.nanoTime() - j5) + 500000) / 1000000));
                                    return;
                                } catch (SQLiteException e2) {
                                    zzakVar2.zzt.zzaA().zzd().zzc("Error storing raw event metadata. appId", zzet.zzn(zzgdVar2.zzy()), e2);
                                    throw e2;
                                }
                            }
                        } catch (SQLiteException e3) {
                            e = e3;
                            str4 = "raw_events";
                        }
                    } catch (SQLiteException e4) {
                        e = e4;
                        str4 = "raw_events";
                    }
                    if (j2 > 0) {
                        zzaA().zzk().zzc("Data lost. Too many events stored on disk, deleted. appId", zzet.zzn(str8), Long.valueOf(j2));
                    }
                    zzgd zzgdVar3 = this.zzn;
                    String str122 = zza.zzc;
                    String str132 = zza.zza;
                    long j82 = zza.zzd;
                    str5 = str4;
                    i = 0;
                    str6 = "_r";
                    zzapVar = new zzap(zzgdVar3, str122, str8, str132, j82, 0L, zzc2);
                    zzak zzakVar122 = this.zze;
                    zzal(zzakVar122);
                    zzn = zzakVar122.zzn(str8, zzapVar.zzb);
                    if (zzn != null) {
                        zzak zzakVar18 = this.zze;
                        zzal(zzakVar18);
                        if (zzakVar18.zzf(str8) >= zzg().zzb(str8) && zzak) {
                            zzaA().zzd().zzd("Too many event names used, ignoring event. appId, name, supported count", zzet.zzn(str8), this.zzn.zzj().zzd(zzapVar.zzb), Integer.valueOf(zzg().zzb(str8)));
                            zzv().zzO(this.zzF, str8, 8, null, null, 0);
                            return;
                        }
                        zzc = new zzaq(str8, zzapVar.zzb, 0L, 0L, 0L, zzapVar.zzd, 0L, null, null, null, null);
                    } else {
                        zzapVar = zzapVar.zza(this.zzn, zzn.zzf);
                        zzc = zzn.zzc(zzapVar.zzd);
                    }
                    zzak zzakVar132 = this.zze;
                    zzal(zzakVar132);
                    zzakVar132.zzE(zzc);
                    zzaB().zzg();
                    zzB();
                    Preconditions.checkNotNull(zzapVar);
                    Preconditions.checkNotNull(zzqVar);
                    Preconditions.checkNotEmpty(zzapVar.zza);
                    Preconditions.checkArgument(zzapVar.zza.equals(zzqVar.zza));
                    zzu = com.google.android.gms.internal.measurement.zzgd.zzu();
                    zzu.zzad(1);
                    zzu.zzZ("android");
                    if (!TextUtils.isEmpty(zzqVar.zza)) {
                        zzu.zzD(zzqVar.zza);
                    }
                    if (!TextUtils.isEmpty(zzqVar.zzd)) {
                        zzu.zzF(zzqVar.zzd);
                    }
                    if (!TextUtils.isEmpty(zzqVar.zzc)) {
                        zzu.zzG(zzqVar.zzc);
                    }
                    zzqu.zzc();
                    if (!TextUtils.isEmpty(zzqVar.zzx) && (zzg().zzs(null, zzeg.zzam) || zzg().zzs(zzqVar.zza, zzeg.zzao))) {
                        zzu.zzah(zzqVar.zzx);
                    }
                    j3 = zzqVar.zzj;
                    if (j3 != -2147483648L) {
                        zzu.zzH((int) j3);
                    }
                    zzu.zzV(zzqVar.zze);
                    if (!TextUtils.isEmpty(zzqVar.zzb)) {
                        zzu.zzU(zzqVar.zzb);
                    }
                    zzu.zzL(zzq((String) Preconditions.checkNotNull(zzqVar.zza)).zzd(zzhb.zzc(zzqVar.zzv, 100)).zzi());
                    if (zzu.zzar().isEmpty() && !TextUtils.isEmpty(zzqVar.zzq)) {
                        zzu.zzC(zzqVar.zzq);
                    }
                    j4 = zzqVar.zzf;
                    if (j4 != 0) {
                        zzu.zzM(j4);
                    }
                    zzu.zzP(zzqVar.zzs);
                    zzlj zzljVar4 = this.zzi;
                    zzal(zzljVar4);
                    com.google.android.gms.internal.measurement.zzhf zza22 = com.google.android.gms.internal.measurement.zzhf.zza(zzljVar4.zzf.zzn.zzaw().getContentResolver(), com.google.android.gms.internal.measurement.zzhq.zza("com.google.android.gms.measurement"), new Runnable() { // from class: com.google.android.gms.measurement.internal.zzaw
                        @Override // java.lang.Runnable
                        public final void run() {
                            com.google.android.gms.internal.measurement.zzib.zzc();
                        }
                    });
                    emptyMap = zza22 != null ? Collections.emptyMap() : zza22.zzc();
                    if (emptyMap != null && !emptyMap.isEmpty()) {
                        arrayList = new ArrayList();
                        int intValue32 = ((Integer) zzeg.zzP.zza(null)).intValue();
                        for (Map.Entry entry : emptyMap.entrySet()) {
                            if (((String) entry.getKey()).startsWith("measurement.id.")) {
                                try {
                                    int parseInt = Integer.parseInt((String) entry.getValue());
                                    if (parseInt != 0) {
                                        arrayList.add(Integer.valueOf(parseInt));
                                        if (arrayList.size() >= intValue32) {
                                            zzljVar4.zzt.zzaA().zzk().zzb("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                                            break;
                                        }
                                        continue;
                                    } else {
                                        continue;
                                    }
                                } catch (NumberFormatException e5) {
                                    zzljVar4.zzt.zzaA().zzk().zzb("Experiment ID NumberFormatException", e5);
                                }
                            }
                        }
                    }
                    arrayList = null;
                    if (arrayList != null) {
                        zzu.zzh(arrayList);
                    }
                    zzd = zzq((String) Preconditions.checkNotNull(zzqVar.zza)).zzd(zzhb.zzc(zzqVar.zzv, 100));
                    if (zzd.zzj(zzha.AD_STORAGE) && zzqVar.zzo) {
                        zzd2 = this.zzk.zzd(zzqVar.zza, zzd);
                        if (!TextUtils.isEmpty((CharSequence) zzd2.first) && zzqVar.zzo) {
                            zzu.zzae((String) zzd2.first);
                            if (zzd2.second != null) {
                                zzu.zzX(((Boolean) zzd2.second).booleanValue());
                            }
                        }
                    }
                    this.zzn.zzg().zzv();
                    zzu.zzN(Build.MODEL);
                    this.zzn.zzg().zzv();
                    zzu.zzY(Build.VERSION.RELEASE);
                    zzu.zzak((int) this.zzn.zzg().zzb());
                    zzu.zzao(this.zzn.zzg().zzc());
                    zzpz.zzc();
                    if (zzg().zzs(null, zzeg.zzaE)) {
                        zzu.zzaj(zzqVar.zzz);
                    }
                    if (this.zzn.zzJ()) {
                        zzu.zzaq();
                        if (!TextUtils.isEmpty(null)) {
                            zzu.zzO(null);
                        }
                    }
                    zzak zzakVar142 = this.zze;
                    zzal(zzakVar142);
                    zzj = zzakVar142.zzj(zzqVar.zza);
                    if (zzj == null) {
                        zzj = new zzh(this.zzn, zzqVar.zza);
                        zzj.zzJ(zzw(zzd));
                        zzj.zzX(zzqVar.zzk);
                        zzj.zzY(zzqVar.zzb);
                        if (zzd.zzj(zzha.AD_STORAGE)) {
                            zzj.zzag(this.zzk.zzf(zzqVar.zza, zzqVar.zzo));
                        }
                        zzj.zzac(0L);
                        zzj.zzad(0L);
                        zzj.zzab(0L);
                        zzj.zzL(zzqVar.zzc);
                        zzj.zzM(zzqVar.zzj);
                        zzj.zzK(zzqVar.zzd);
                        zzj.zzZ(zzqVar.zze);
                        zzj.zzU(zzqVar.zzf);
                        zzj.zzae(zzqVar.zzh);
                        zzj.zzV(zzqVar.zzs);
                        zzak zzakVar19 = this.zze;
                        zzal(zzakVar19);
                        zzakVar19.zzD(zzj);
                    }
                    if (zzd.zzj(zzha.ANALYTICS_STORAGE) && !TextUtils.isEmpty(zzj.zzw())) {
                        zzu.zzE((String) Preconditions.checkNotNull(zzj.zzw()));
                    }
                    if (!TextUtils.isEmpty(zzj.zzz())) {
                        zzu.zzT((String) Preconditions.checkNotNull(zzj.zzz()));
                    }
                    zzak zzakVar152 = this.zze;
                    zzal(zzakVar152);
                    zzu2 = zzakVar152.zzu(zzqVar.zza);
                    for (i2 = 0; i2 < zzu2.size(); i2++) {
                        com.google.android.gms.internal.measurement.zzgl zzd3 = com.google.android.gms.internal.measurement.zzgm.zzd();
                        zzd3.zzf(((zzlm) zzu2.get(i2)).zzc);
                        zzd3.zzg(((zzlm) zzu2.get(i2)).zzd);
                        zzlj zzljVar5 = this.zzi;
                        zzal(zzljVar5);
                        zzljVar5.zzv(zzd3, ((zzlm) zzu2.get(i2)).zze);
                        zzu.zzl(zzd3);
                        if (zzg().zzs(null, zzeg.zzaH) && "_sid".equals(((zzlm) zzu2.get(i2)).zzc) && zzj.zzq() != 0) {
                            zzlj zzljVar6 = this.zzi;
                            zzal(zzljVar6);
                            if (zzljVar6.zzd(zzqVar.zzx) != zzj.zzq()) {
                                zzu.zzy();
                            }
                        }
                    }
                    try {
                        zzakVar2 = this.zze;
                        zzal(zzakVar2);
                        com.google.android.gms.internal.measurement.zzgd zzgdVar22 = (com.google.android.gms.internal.measurement.zzgd) zzu.zzaD();
                        zzakVar2.zzg();
                        zzakVar2.zzW();
                        Preconditions.checkNotNull(zzgdVar22);
                        Preconditions.checkNotEmpty(zzgdVar22.zzy());
                        byte[] zzbx3 = zzgdVar22.zzbx();
                        zzlj zzljVar22 = zzakVar2.zzf.zzi;
                        zzal(zzljVar22);
                        long zzf22 = zzljVar22.zzf(zzbx3);
                        ContentValues contentValues22 = new ContentValues();
                        String str142 = str2;
                        contentValues22.put(str142, zzgdVar22.zzy());
                        String str152 = str;
                        contentValues22.put(str152, Long.valueOf(zzf22));
                        contentValues22.put(TtmlNode.TAG_METADATA, zzbx3);
                        zzakVar2.zzh().insertWithOnConflict("raw_events_metadata", null, contentValues22, 4);
                        zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzarVar = new zzar(zzapVar.zzf);
                        while (true) {
                            if (zzarVar.hasNext()) {
                                zzfu zzfuVar4 = this.zzc;
                                zzal(zzfuVar4);
                                boolean zzq = zzfuVar4.zzq(zzapVar.zza, zzapVar.zzb);
                                zzak zzakVar20 = this.zze;
                                zzal(zzakVar20);
                                zzai zzl = zzakVar20.zzl(zza(), zzapVar.zza, false, false, false, false, false);
                                if (zzq && zzl.zze < zzg().zze(zzapVar.zza, zzeg.zzo)) {
                                    i = 1;
                                }
                            } else {
                                str7 = str6;
                                if (str7.equals(zzarVar.next())) {
                                    i = 1;
                                    break;
                                }
                                str6 = str7;
                            }
                        }
                        zzakVar3.zzg();
                        zzakVar3.zzW();
                        Preconditions.checkNotNull(zzapVar);
                        Preconditions.checkNotEmpty(zzapVar.zza);
                        zzlj zzljVar32 = zzakVar3.zzf.zzi;
                        zzal(zzljVar32);
                        byte[] zzbx22 = zzljVar32.zzl(zzapVar).zzbx();
                        contentValues = new ContentValues();
                        contentValues.put(str142, zzapVar.zza);
                        contentValues.put("name", zzapVar.zzb);
                        contentValues.put("timestamp", Long.valueOf(zzapVar.zzd));
                        contentValues.put(str152, Long.valueOf(zzf22));
                        contentValues.put("data", zzbx22);
                        contentValues.put("realtime", Integer.valueOf(i));
                        try {
                            if (zzakVar3.zzh().insert(str5, null, contentValues) != -1) {
                                zzakVar3.zzt.zzaA().zzd().zzb("Failed to insert raw event (got -1). appId", zzet.zzn(zzapVar.zza));
                            } else {
                                this.zza = 0L;
                            }
                        } catch (SQLiteException e6) {
                            zzakVar3.zzt.zzaA().zzd().zzc("Error storing raw event. appId", zzet.zzn(zzapVar.zza), e6);
                        }
                    } catch (IOException e7) {
                        zzaA().zzd().zzc("Data loss. Failed to insert raw event metadata. appId", zzet.zzn(zzu.zzaq()), e7);
                    }
                    zzak zzakVar162 = this.zze;
                    zzal(zzakVar162);
                    zzakVar162.zzC();
                    zzak zzakVar172 = this.zze;
                    zzal(zzakVar172);
                    zzakVar172.zzx();
                    zzag();
                    zzaA().zzj().zzb("Background event processing time, ms", Long.valueOf(((System.nanoTime() - j5) + 500000) / 1000000));
                    return;
                }
                zzg = zza.zzb.zzg(FirebaseAnalytics.Param.CURRENCY);
                if (z) {
                    longValue = zza.zzb.zze("value").longValue();
                } else {
                    double doubleValue = zza.zzb.zzd("value").doubleValue() * 1000000.0d;
                    if (doubleValue == 0.0d) {
                        doubleValue = zza.zzb.zze("value").longValue() * 1000000.0d;
                    }
                    if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                        zzaA().zzk().zzc("Data lost. Currency value is too big. appId", zzet.zzn(str8), Double.valueOf(doubleValue));
                        zzak zzakVar21 = this.zze;
                        zzal(zzakVar21);
                        zzakVar21.zzC();
                        return;
                    }
                    longValue = Math.round(doubleValue);
                    if (FirebaseAnalytics.Event.REFUND.equals(zza.zza)) {
                        longValue = -longValue;
                    }
                }
                if (!TextUtils.isEmpty(zzg)) {
                    String upperCase = zzg.toUpperCase(Locale.US);
                    if (upperCase.matches("[A-Z]{3}")) {
                        String concat = "_ltv_".concat(String.valueOf(upperCase));
                        zzak zzakVar22 = this.zze;
                        zzal(zzakVar22);
                        zzlm zzp2 = zzakVar22.zzp(str8, concat);
                        if (zzp2 != null) {
                            Object obj = zzp2.zze;
                            if (obj instanceof Long) {
                                str = "metadata_fingerprint";
                                str2 = "app_id";
                                str3 = str11;
                                zzlmVar = new zzlm(str8, zza.zzc, concat, zzax().currentTimeMillis(), Long.valueOf(((Long) obj).longValue() + longValue));
                                zzakVar = this.zze;
                                zzal(zzakVar);
                                if (!zzakVar.zzL(zzlmVar)) {
                                    zzaA().zzd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzet.zzn(str8), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                                    zzv().zzO(this.zzF, str8, 9, null, null, 0);
                                }
                                boolean zzak2 = zzlp.zzak(zza.zza);
                                boolean equals2 = str3.equals(zza.zza);
                                zzv();
                                zzasVar = zza.zzb;
                                if (zzasVar == null) {
                                }
                                zzak zzakVar62 = this.zze;
                                zzal(zzakVar62);
                                zzai zzm2 = zzakVar62.zzm(zza(), str8, j + 1, true, zzak2, false, equals2, false);
                                long j62 = zzm2.zzb;
                                zzg();
                                intValue = j62 - ((Integer) zzeg.zzk.zza(null)).intValue();
                                if (intValue > 0) {
                                }
                            }
                        }
                        zzak zzakVar23 = this.zze;
                        zzal(zzakVar23);
                        int zze = zzg().zze(str8, zzeg.zzE) - 1;
                        Preconditions.checkNotEmpty(str8);
                        zzakVar23.zzg();
                        zzakVar23.zzW();
                        SQLiteDatabase zzh = zzakVar23.zzh();
                        String str16 = str11;
                        str = "metadata_fingerprint";
                        String[] strArr = new String[3];
                        strArr[0] = str8;
                        str2 = "app_id";
                        strArr[1] = str8;
                        strArr[2] = String.valueOf(zze);
                        zzh.execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", strArr);
                        str3 = str16;
                        zzlmVar = new zzlm(str8, zza.zzc, concat, zzax().currentTimeMillis(), Long.valueOf(longValue));
                        zzakVar = this.zze;
                        zzal(zzakVar);
                        if (!zzakVar.zzL(zzlmVar)) {
                        }
                        boolean zzak22 = zzlp.zzak(zza.zza);
                        boolean equals22 = str3.equals(zza.zza);
                        zzv();
                        zzasVar = zza.zzb;
                        if (zzasVar == null) {
                        }
                        zzak zzakVar622 = this.zze;
                        zzal(zzakVar622);
                        zzai zzm22 = zzakVar622.zzm(zza(), str8, j + 1, true, zzak22, false, equals22, false);
                        long j622 = zzm22.zzb;
                        zzg();
                        intValue = j622 - ((Integer) zzeg.zzk.zza(null)).intValue();
                        if (intValue > 0) {
                        }
                    }
                }
                str = "metadata_fingerprint";
                str2 = "app_id";
                str3 = str11;
                boolean zzak222 = zzlp.zzak(zza.zza);
                boolean equals222 = str3.equals(zza.zza);
                zzv();
                zzasVar = zza.zzb;
                if (zzasVar == null) {
                }
                zzak zzakVar6222 = this.zze;
                zzal(zzakVar6222);
                zzai zzm222 = zzakVar6222.zzm(zza(), str8, j + 1, true, zzak222, false, equals222, false);
                long j6222 = zzm222.zzb;
                zzg();
                intValue = j6222 - ((Integer) zzeg.zzk.zza(null)).intValue();
                if (intValue > 0) {
                }
            }
            z = true;
            if (!"_iap".equals(zza.zza)) {
            }
            zzg = zza.zzb.zzg(FirebaseAnalytics.Param.CURRENCY);
            if (z) {
            }
            if (!TextUtils.isEmpty(zzg)) {
            }
            str = "metadata_fingerprint";
            str2 = "app_id";
            str3 = str11;
            boolean zzak2222 = zzlp.zzak(zza.zza);
            boolean equals2222 = str3.equals(zza.zza);
            zzv();
            zzasVar = zza.zzb;
            if (zzasVar == null) {
            }
            zzak zzakVar62222 = this.zze;
            zzal(zzakVar62222);
            zzai zzm2222 = zzakVar62222.zzm(zza(), str8, j + 1, true, zzak2222, false, equals2222, false);
            long j62222 = zzm2222.zzb;
            zzg();
            intValue = j62222 - ((Integer) zzeg.zzk.zza(null)).intValue();
            if (intValue > 0) {
            }
        } finally {
            zzak zzakVar24 = this.zze;
            zzal(zzakVar24);
            zzakVar24.zzx();
        }
    }

    final boolean zzZ() {
        zzaB().zzg();
        FileLock fileLock = this.zzw;
        if (fileLock == null || !fileLock.isValid()) {
            this.zze.zzt.zzf();
            try {
                FileChannel channel = new RandomAccessFile(new File(this.zzn.zzaw().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zzx = channel;
                FileLock tryLock = channel.tryLock();
                this.zzw = tryLock;
                if (tryLock != null) {
                    zzaA().zzj().zza("Storage concurrent access okay");
                    return true;
                }
                zzaA().zzd().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                zzaA().zzd().zzb("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                zzaA().zzd().zzb("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                zzaA().zzk().zzb("Storage lock already acquired", e3);
                return false;
            }
        }
        zzaA().zzj().zza("Storage concurrent access okay");
        return true;
    }

    final long zza() {
        long currentTimeMillis = zzax().currentTimeMillis();
        zzkb zzkbVar = this.zzk;
        zzkbVar.zzW();
        zzkbVar.zzg();
        long zza = zzkbVar.zze.zza();
        if (zza == 0) {
            zza = zzkbVar.zzt.zzv().zzG().nextInt(86400000) + 1;
            zzkbVar.zze.zzb(zza);
        }
        return ((((currentTimeMillis + zza) / 1000) / 60) / 60) / 24;
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzet zzaA() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzaA();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzga zzaB() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzaB();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final Context zzaw() {
        return this.zzn.zzaw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final Clock zzax() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzax();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzab zzay() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzh zzd(zzq zzqVar) {
        zzaB().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzlf zzlfVar = null;
        if (!zzqVar.zzw.isEmpty()) {
            this.zzC.put(zzqVar.zza, new zzlg(this, zzqVar.zzw));
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzj = zzakVar.zzj(zzqVar.zza);
        zzhb zzd = zzq(zzqVar.zza).zzd(zzhb.zzc(zzqVar.zzv, 100));
        String zzf = zzd.zzj(zzha.AD_STORAGE) ? this.zzk.zzf(zzqVar.zza, zzqVar.zzo) : "";
        if (zzj == null) {
            zzj = new zzh(this.zzn, zzqVar.zza);
            if (zzd.zzj(zzha.ANALYTICS_STORAGE)) {
                zzj.zzJ(zzw(zzd));
            }
            if (zzd.zzj(zzha.AD_STORAGE)) {
                zzj.zzag(zzf);
            }
        } else if (!zzd.zzj(zzha.AD_STORAGE) || zzf == null || zzf.equals(zzj.zzC())) {
            if (TextUtils.isEmpty(zzj.zzw()) && zzd.zzj(zzha.ANALYTICS_STORAGE)) {
                zzj.zzJ(zzw(zzd));
            }
        } else {
            zzj.zzag(zzf);
            if (zzqVar.zzo && !"00000000-0000-0000-0000-000000000000".equals(this.zzk.zzd(zzqVar.zza, zzd).first)) {
                zzj.zzJ(zzw(zzd));
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                if (zzakVar2.zzp(zzqVar.zza, "_id") != null) {
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    if (zzakVar3.zzp(zzqVar.zza, "_lair") == null) {
                        zzlm zzlmVar = new zzlm(zzqVar.zza, "auto", "_lair", zzax().currentTimeMillis(), 1L);
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        zzakVar4.zzL(zzlmVar);
                    }
                }
            }
        }
        zzj.zzY(zzqVar.zzb);
        zzj.zzH(zzqVar.zzq);
        if (!TextUtils.isEmpty(zzqVar.zzk)) {
            zzj.zzX(zzqVar.zzk);
        }
        long j = zzqVar.zze;
        if (j != 0) {
            zzj.zzZ(j);
        }
        if (!TextUtils.isEmpty(zzqVar.zzc)) {
            zzj.zzL(zzqVar.zzc);
        }
        zzj.zzM(zzqVar.zzj);
        String str = zzqVar.zzd;
        if (str != null) {
            zzj.zzK(str);
        }
        zzj.zzU(zzqVar.zzf);
        zzj.zzae(zzqVar.zzh);
        if (!TextUtils.isEmpty(zzqVar.zzg)) {
            zzj.zzaa(zzqVar.zzg);
        }
        zzj.zzI(zzqVar.zzo);
        zzj.zzaf(zzqVar.zzr);
        zzj.zzV(zzqVar.zzs);
        zzqu.zzc();
        if (zzg().zzs(null, zzeg.zzam) || zzg().zzs(zzqVar.zza, zzeg.zzao)) {
            zzj.zzai(zzqVar.zzx);
        }
        zzop.zzc();
        if (!zzg().zzs(null, zzeg.zzal)) {
            zzop.zzc();
            if (zzg().zzs(null, zzeg.zzak)) {
                zzj.zzah(null);
            }
        } else {
            zzj.zzah(zzqVar.zzt);
        }
        zzrd.zzc();
        if (zzg().zzs(null, zzeg.zzaq)) {
            zzj.zzak(zzqVar.zzy);
        }
        zzpz.zzc();
        if (zzg().zzs(null, zzeg.zzaE)) {
            zzj.zzal(zzqVar.zzz);
        }
        if (zzj.zzao()) {
            zzak zzakVar5 = this.zze;
            zzal(zzakVar5);
            zzakVar5.zzD(zzj);
        }
        return zzj;
    }

    public final zzaa zzf() {
        zzaa zzaaVar = this.zzh;
        zzal(zzaaVar);
        return zzaaVar;
    }

    public final zzag zzg() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzf();
    }

    public final zzak zzh() {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        return zzakVar;
    }

    public final zzeo zzi() {
        return this.zzn.zzj();
    }

    public final zzez zzj() {
        zzez zzezVar = this.zzd;
        zzal(zzezVar);
        return zzezVar;
    }

    public final zzfb zzl() {
        zzfb zzfbVar = this.zzf;
        if (zzfbVar != null) {
            return zzfbVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzfu zzm() {
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        return zzfuVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgd zzp() {
        return this.zzn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzhb zzq(String str) {
        String str2;
        zzhb zzhbVar = zzhb.zza;
        zzaB().zzg();
        zzB();
        zzhb zzhbVar2 = (zzhb) this.zzB.get(str);
        if (zzhbVar2 != null) {
            return zzhbVar2;
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        Preconditions.checkNotNull(str);
        zzakVar.zzg();
        zzakVar.zzW();
        Cursor cursor = null;
        try {
            try {
                cursor = zzakVar.zzh().rawQuery("select consent_state from consent_settings where app_id=? limit 1;", new String[]{str});
                if (cursor.moveToFirst()) {
                    str2 = cursor.getString(0);
                } else {
                    if (cursor != null) {
                        cursor.close();
                    }
                    str2 = "G1";
                }
                zzhb zzc = zzhb.zzc(str2, 100);
                zzV(str, zzc);
                return zzc;
            } catch (SQLiteException e) {
                zzakVar.zzt.zzaA().zzd().zzc("Database error", "select consent_state from consent_settings where app_id=? limit 1;", e);
                throw e;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final zzip zzr() {
        zzip zzipVar = this.zzj;
        zzal(zzipVar);
        return zzipVar;
    }

    public final zzkb zzs() {
        return this.zzk;
    }

    public final zzlj zzu() {
        zzlj zzljVar = this.zzi;
        zzal(zzljVar);
        return zzljVar;
    }

    public final zzlp zzv() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzv();
    }

    final String zzw(zzhb zzhbVar) {
        if (!zzhbVar.zzj(zzha.ANALYTICS_STORAGE)) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzv().zzG().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzx(zzq zzqVar) {
        try {
            return (String) zzaB().zzh(new zzla(this, zzqVar)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzaA().zzd().zzc("Failed to get app instance id. appId", zzet.zzn(zzqVar.zza), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzz(Runnable runnable) {
        zzaB().zzg();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }
}
