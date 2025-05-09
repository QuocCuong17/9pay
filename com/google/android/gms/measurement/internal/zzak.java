package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrd;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.sentry.protocol.App;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzak extends zzku {
    private static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzc = {App.JsonKeys.APP_VERSION, "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;"};
    private static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzaj zzj;
    private final zzkq zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(zzlh zzlhVar) {
        super(zzlhVar);
        this.zzk = new zzkq(this.zzt.zzax());
        this.zzt.zzf();
        this.zzj = new zzaj(this, this.zzt.zzaw(), "google_app_measurement.db");
    }

    static final void zzV(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            if (obj instanceof Double) {
                contentValues.put("value", (Double) obj);
                return;
            }
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final long zzZ(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = zzh().rawQuery(str, strArr);
                if (rawQuery.moveToFirst()) {
                    long j = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzc("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zzaa(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            try {
                cursor = zzh().rawQuery(str, strArr);
                if (cursor.moveToFirst()) {
                    return cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzc("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final void zzA(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzd("Error deleting user property. appId", zzet.zzn(str), this.zzt.zzj().zzf(str2), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0237, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01e1, code lost:
    
        r0 = r23.zzt.zzaA().zzk();
        r9 = com.google.android.gms.measurement.internal.zzet.zzn(r24);
        r11 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01f9, code lost:
    
        if (r12.zzp() == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01fb, code lost:
    
        r20 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0208, code lost:
    
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r9, r11, java.lang.String.valueOf(r20));
        r21 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0206, code lost:
    
        r20 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0299, code lost:
    
        r21 = r7;
        r0 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02a7, code lost:
    
        if (r0.hasNext() == false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02a9, code lost:
    
        r3 = (com.google.android.gms.internal.measurement.zzet) r0.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02c3, code lost:
    
        if (r3.zze().isEmpty() == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x02f2, code lost:
    
        r7 = r3.zzbx();
        r11 = new android.content.ContentValues();
        r11.put("app_id", r24);
        r11.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0309, code lost:
    
        if (r3.zzj() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x030b, code lost:
    
        r12 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0315, code lost:
    
        r11.put("filter_id", r12);
        r22 = r0;
        r11.put("property_name", r3.zze());
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0327, code lost:
    
        if (r3.zzk() == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0329, code lost:
    
        r0 = java.lang.Boolean.valueOf(r3.zzi());
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0333, code lost:
    
        r11.put("session_scoped", r0);
        r11.put("data", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0347, code lost:
    
        if (zzh().insertWithOnConflict("property_filters", null, r11, 5) != (-1)) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x035d, code lost:
    
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0349, code lost:
    
        r23.zzt.zzaA().zzd().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzet.zzn(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0361, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0362, code lost:
    
        r23.zzt.zzaA().zzd().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzet.zzn(r24), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0332, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0314, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x02c5, code lost:
    
        r0 = r23.zzt.zzaA().zzk();
        r8 = com.google.android.gms.measurement.internal.zzet.zzn(r24);
        r9 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x02dd, code lost:
    
        if (r3.zzj() == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02df, code lost:
    
        r3 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02e9, code lost:
    
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r8, r9, java.lang.String.valueOf(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02e8, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x03ac, code lost:
    
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x017a, code lost:
    
        r11 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0186, code lost:
    
        if (r11.hasNext() == false) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0192, code lost:
    
        if (((com.google.android.gms.internal.measurement.zzet) r11.next()).zzj() != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0194, code lost:
    
        r23.zzt.zzaA().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzet.zzn(r24), java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01ad, code lost:
    
        r11 = r0.zzg().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01c3, code lost:
    
        if (r11.hasNext() == false) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01c5, code lost:
    
        r12 = (com.google.android.gms.internal.measurement.zzek) r11.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01df, code lost:
    
        if (r12.zzg().isEmpty() == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0213, code lost:
    
        r3 = r12.zzbx();
        r21 = r7;
        r7 = new android.content.ContentValues();
        r7.put("app_id", r24);
        r7.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x022c, code lost:
    
        if (r12.zzp() == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x022e, code lost:
    
        r9 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0238, code lost:
    
        r7.put("filter_id", r9);
        r7.put("event_name", r12.zzg());
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0248, code lost:
    
        if (r12.zzq() == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x024a, code lost:
    
        r9 = java.lang.Boolean.valueOf(r12.zzn());
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0254, code lost:
    
        r7.put("session_scoped", r9);
        r7.put("data", r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0268, code lost:
    
        if (zzh().insertWithOnConflict("event_filters", null, r7, 5) != (-1)) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x026a, code lost:
    
        r23.zzt.zzaA().zzd().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzet.zzn(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x027d, code lost:
    
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0283, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0284, code lost:
    
        r23.zzt.zzaA().zzd().zzc("Error storing event filter. appId", com.google.android.gms.measurement.internal.zzet.zzn(r24), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0375, code lost:
    
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        r0 = zzh();
        r3 = r17;
        r0.delete("property_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r0.delete("event_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r17 = r3;
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0253, code lost:
    
        r9 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzB(String str, List list) {
        boolean z;
        String str2 = "app_id=? and audience_id=?";
        Preconditions.checkNotNull(list);
        int i = 0;
        while (i < list.size()) {
            com.google.android.gms.internal.measurement.zzeh zzehVar = (com.google.android.gms.internal.measurement.zzeh) ((com.google.android.gms.internal.measurement.zzei) list.get(i)).zzbB();
            if (zzehVar.zza() != 0) {
                int i2 = 0;
                while (i2 < zzehVar.zza()) {
                    com.google.android.gms.internal.measurement.zzej zzejVar = (com.google.android.gms.internal.measurement.zzej) zzehVar.zze(i2).zzbB();
                    com.google.android.gms.internal.measurement.zzej zzejVar2 = (com.google.android.gms.internal.measurement.zzej) zzejVar.clone();
                    String zzb2 = zzhc.zzb(zzejVar.zze());
                    if (zzb2 != null) {
                        zzejVar2.zzb(zzb2);
                        z = true;
                    } else {
                        z = false;
                    }
                    int i3 = 0;
                    while (i3 < zzejVar.zza()) {
                        com.google.android.gms.internal.measurement.zzem zzd2 = zzejVar.zzd(i3);
                        com.google.android.gms.internal.measurement.zzej zzejVar3 = zzejVar;
                        String str3 = str2;
                        String zzb3 = zziq.zzb(zzd2.zze(), zzhd.zza, zzhd.zzb);
                        if (zzb3 != null) {
                            com.google.android.gms.internal.measurement.zzel zzelVar = (com.google.android.gms.internal.measurement.zzel) zzd2.zzbB();
                            zzelVar.zza(zzb3);
                            zzejVar2.zzc(i3, (com.google.android.gms.internal.measurement.zzem) zzelVar.zzaD());
                            z = true;
                        }
                        i3++;
                        zzejVar = zzejVar3;
                        str2 = str3;
                    }
                    String str4 = str2;
                    if (z) {
                        zzehVar.zzc(i2, zzejVar2);
                        list.set(i, (com.google.android.gms.internal.measurement.zzei) zzehVar.zzaD());
                    }
                    i2++;
                    str2 = str4;
                }
            }
            String str5 = str2;
            if (zzehVar.zzb() != 0) {
                for (int i4 = 0; i4 < zzehVar.zzb(); i4++) {
                    com.google.android.gms.internal.measurement.zzet zzf = zzehVar.zzf(i4);
                    String zzb4 = zziq.zzb(zzf.zze(), zzhe.zza, zzhe.zzb);
                    if (zzb4 != null) {
                        com.google.android.gms.internal.measurement.zzes zzesVar = (com.google.android.gms.internal.measurement.zzes) zzf.zzbB();
                        zzesVar.zza(zzb4);
                        zzehVar.zzd(i4, zzesVar);
                        list.set(i, (com.google.android.gms.internal.measurement.zzei) zzehVar.zzaD());
                    }
                }
            }
            i++;
            str2 = str5;
        }
        String str6 = str2;
        zzW();
        zzg();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase zzh2 = zzh();
        zzh2.beginTransaction();
        try {
            zzW();
            zzg();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase zzh3 = zzh();
            zzh3.delete("property_filters", "app_id=?", new String[]{str});
            zzh3.delete("event_filters", "app_id=?", new String[]{str});
            Iterator it = list.iterator();
            while (it.hasNext()) {
                com.google.android.gms.internal.measurement.zzei zzeiVar = (com.google.android.gms.internal.measurement.zzei) it.next();
                zzW();
                zzg();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzeiVar);
                if (!zzeiVar.zzk()) {
                    this.zzt.zzaA().zzk().zzb("Audience with no ID. appId", zzet.zzn(str));
                } else {
                    int zza2 = zzeiVar.zza();
                    Iterator it2 = zzeiVar.zzg().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (!((com.google.android.gms.internal.measurement.zzek) it2.next()).zzp()) {
                                this.zzt.zzaA().zzk().zzc("Event filter with no ID. Audience definition ignored. appId, audienceId", zzet.zzn(str), Integer.valueOf(zza2));
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                com.google.android.gms.internal.measurement.zzei zzeiVar2 = (com.google.android.gms.internal.measurement.zzei) it3.next();
                arrayList.add(zzeiVar2.zzk() ? Integer.valueOf(zzeiVar2.zza()) : null);
            }
            Preconditions.checkNotEmpty(str);
            zzW();
            zzg();
            SQLiteDatabase zzh4 = zzh();
            try {
                long zzZ = zzZ("select count(1) from audience_filter_values where app_id=?", new String[]{str});
                int max = Math.max(0, Math.min(2000, this.zzt.zzf().zze(str, zzeg.zzF)));
                if (zzZ > max) {
                    ArrayList arrayList2 = new ArrayList();
                    int i5 = 0;
                    while (true) {
                        if (i5 < arrayList.size()) {
                            Integer num = (Integer) arrayList.get(i5);
                            if (num == null) {
                                break;
                            }
                            arrayList2.add(Integer.toString(num.intValue()));
                            i5++;
                        } else {
                            zzh4.delete("audience_filter_values", "audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in " + ("(" + TextUtils.join(",", arrayList2) + ")") + " order by rowid desc limit -1 offset ?)", new String[]{str, Integer.toString(max)});
                            break;
                        }
                    }
                }
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzc("Database error querying filters. appId", zzet.zzn(str), e);
            }
            zzh2.setTransactionSuccessful();
        } finally {
            zzh2.endTransaction();
        }
    }

    public final void zzC() {
        zzW();
        zzh().setTransactionSuccessful();
    }

    public final void zzD(zzh zzhVar) {
        Preconditions.checkNotNull(zzhVar);
        zzg();
        zzW();
        String zzv = zzhVar.zzv();
        Preconditions.checkNotNull(zzv);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzv);
        contentValues.put("app_instance_id", zzhVar.zzw());
        contentValues.put("gmp_app_id", zzhVar.zzA());
        contentValues.put("resettable_device_id_hash", zzhVar.zzC());
        contentValues.put("last_bundle_index", Long.valueOf(zzhVar.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzhVar.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzhVar.zzn()));
        contentValues.put(App.JsonKeys.APP_VERSION, zzhVar.zzy());
        contentValues.put("app_store", zzhVar.zzx());
        contentValues.put("gmp_version", Long.valueOf(zzhVar.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzhVar.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzhVar.zzan()));
        contentValues.put("day", Long.valueOf(zzhVar.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzhVar.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzhVar.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzhVar.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzhVar.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzhVar.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzhVar.zzb()));
        contentValues.put("firebase_instance_id", zzhVar.zzz());
        contentValues.put("daily_error_events_count", Long.valueOf(zzhVar.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzhVar.zzh()));
        contentValues.put("health_monitor_sample", zzhVar.zzB());
        zzhVar.zza();
        contentValues.put("android_id", (Long) 0L);
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzhVar.zzam()));
        contentValues.put("admob_app_id", zzhVar.zzt());
        contentValues.put("dynamite_version", Long.valueOf(zzhVar.zzk()));
        contentValues.put("session_stitching_token", zzhVar.zzD());
        contentValues.put("sgtm_upload_enabled", Boolean.valueOf(zzhVar.zzap()));
        contentValues.put("target_os_version", Long.valueOf(zzhVar.zzr()));
        contentValues.put("session_stitching_token_hash", Long.valueOf(zzhVar.zzq()));
        List zzE = zzhVar.zzE();
        if (zzE != null) {
            if (zzE.isEmpty()) {
                this.zzt.zzaA().zzk().zzb("Safelisted events should not be an empty list. appId", zzv);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzE));
            }
        }
        zzop.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzak) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        try {
            SQLiteDatabase zzh2 = zzh();
            if (zzh2.update("apps", contentValues, "app_id = ?", new String[]{zzv}) == 0 && zzh2.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update app (got -1). appId", zzet.zzn(zzv));
            }
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzc("Error storing app. appId", zzet.zzn(zzv), e);
        }
    }

    public final void zzE(zzaq zzaqVar) {
        Preconditions.checkNotNull(zzaqVar);
        zzg();
        zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaqVar.zza);
        contentValues.put("name", zzaqVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzaqVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzaqVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzaqVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzaqVar.zzg));
        contentValues.put("last_bundled_day", zzaqVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzaqVar.zzi);
        contentValues.put("last_sampling_rate", zzaqVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzaqVar.zze));
        Boolean bool = zzaqVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zzh().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzet.zzn(zzaqVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzc("Error storing event aggregates. appId", zzet.zzn(zzaqVar.zza), e);
        }
    }

    public final boolean zzF() {
        return zzZ("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzG() {
        return zzZ("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean zzH() {
        return zzZ("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    protected final boolean zzI() {
        Context zzaw = this.zzt.zzaw();
        this.zzt.zzf();
        return zzaw.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzJ(String str, Long l, long j, com.google.android.gms.internal.measurement.zzft zzftVar) {
        zzg();
        zzW();
        Preconditions.checkNotNull(zzftVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbx = zzftVar.zzbx();
        this.zzt.zzaA().zzj().zzc("Saving complex main event, appId, data size", this.zzt.zzj().zzd(str), Integer.valueOf(zzbx.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbx);
        try {
            if (zzh().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            this.zzt.zzaA().zzd().zzb("Failed to insert complex main event (got -1). appId", zzet.zzn(str));
            return false;
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzc("Error storing complex main event. appId", zzet.zzn(str), e);
            return false;
        }
    }

    public final boolean zzK(zzac zzacVar) {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        zzW();
        String str = zzacVar.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzacVar.zzc.zzb) == null) {
            long zzZ = zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzt.zzf();
            if (zzZ >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzacVar.zzb);
        contentValues.put("name", zzacVar.zzc.zzb);
        zzV(contentValues, "value", Preconditions.checkNotNull(zzacVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzacVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzacVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzacVar.zzh));
        contentValues.put("timed_out_event", this.zzt.zzv().zzap(zzacVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzacVar.zzd));
        contentValues.put("triggered_event", this.zzt.zzv().zzap(zzacVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzacVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzacVar.zzj));
        contentValues.put("expired_event", this.zzt.zzv().zzap(zzacVar.zzk));
        try {
            if (zzh().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzet.zzn(str));
            }
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzc("Error storing conditional user property", zzet.zzn(str), e);
        }
        return true;
    }

    public final boolean zzL(zzlm zzlmVar) {
        Preconditions.checkNotNull(zzlmVar);
        zzg();
        zzW();
        if (zzp(zzlmVar.zza, zzlmVar.zzc) == null) {
            if (zzlp.zzak(zzlmVar.zzc)) {
                if (zzZ("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzlmVar.zza}) >= this.zzt.zzf().zzf(zzlmVar.zza, zzeg.zzG, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzlmVar.zzc)) {
                long zzZ = zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzlmVar.zza, zzlmVar.zzb});
                this.zzt.zzf();
                if (zzZ >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzlmVar.zza);
        contentValues.put("origin", zzlmVar.zzb);
        contentValues.put("name", zzlmVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzlmVar.zzd));
        zzV(contentValues, "value", zzlmVar.zze);
        try {
            if (zzh().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update user property (got -1). appId", zzet.zzn(zzlmVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzc("Error storing user property. appId", zzet.zzn(zzlmVar.zza), e);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0235: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:121:0x0235 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v9 */
    public final void zzU(String str, long j, long j2, zzle zzleVar) {
        ?? r4;
        Cursor cursor;
        Cursor rawQuery;
        String string;
        int i;
        String str2;
        String[] strArr;
        Preconditions.checkNotNull(zzleVar);
        zzg();
        zzW();
        Cursor cursor2 = null;
        r3 = null;
        r3 = null;
        String str3 = null;
        try {
            try {
                SQLiteDatabase zzh2 = zzh();
                r4 = TextUtils.isEmpty(null);
                try {
                    if (r4 != 0) {
                        rawQuery = zzh2.rawQuery("select app_id, metadata_fingerprint from raw_events where " + (j2 != -1 ? "rowid <= ? and " : "") + "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;", j2 != -1 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)});
                        if (!rawQuery.moveToFirst()) {
                            if (rawQuery != null) {
                                rawQuery.close();
                                return;
                            }
                            return;
                        } else {
                            str3 = rawQuery.getString(0);
                            string = rawQuery.getString(1);
                            rawQuery.close();
                        }
                    } else {
                        rawQuery = zzh2.rawQuery("select metadata_fingerprint from raw_events where app_id = ?" + (j2 != -1 ? " and rowid <= ?" : "") + " order by rowid limit 1;", j2 != -1 ? new String[]{null, String.valueOf(j2)} : new String[]{null});
                        if (!rawQuery.moveToFirst()) {
                            if (rawQuery != null) {
                                rawQuery.close();
                                return;
                            }
                            return;
                        }
                        string = rawQuery.getString(0);
                        rawQuery.close();
                    }
                    Cursor cursor3 = rawQuery;
                    String str4 = string;
                    try {
                        Cursor query = zzh2.query("raw_events_metadata", new String[]{TtmlNode.TAG_METADATA}, "app_id = ? and metadata_fingerprint = ?", new String[]{str3, str4}, null, null, "rowid", "2");
                        try {
                            if (!query.moveToFirst()) {
                                this.zzt.zzaA().zzd().zzb("Raw event metadata record is missing. appId", zzet.zzn(str3));
                                if (query != null) {
                                    query.close();
                                    return;
                                }
                                return;
                            }
                            try {
                                try {
                                    com.google.android.gms.internal.measurement.zzgd zzgdVar = (com.google.android.gms.internal.measurement.zzgd) ((com.google.android.gms.internal.measurement.zzgc) zzlj.zzm(com.google.android.gms.internal.measurement.zzgd.zzu(), query.getBlob(0))).zzaD();
                                    if (query.moveToNext()) {
                                        this.zzt.zzaA().zzk().zzb("Get multiple raw event metadata records, expected one. appId", zzet.zzn(str3));
                                    }
                                    query.close();
                                    Preconditions.checkNotNull(zzgdVar);
                                    zzleVar.zza = zzgdVar;
                                    if (j2 != -1) {
                                        i = 1;
                                        str2 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                                        strArr = new String[]{str3, str4, String.valueOf(j2)};
                                    } else {
                                        i = 1;
                                        str2 = "app_id = ? and metadata_fingerprint = ?";
                                        strArr = new String[]{str3, str4};
                                    }
                                    Cursor query2 = zzh2.query("raw_events", new String[]{"rowid", "name", "timestamp", "data"}, str2, strArr, null, null, "rowid", null);
                                    if (!query2.moveToFirst()) {
                                        this.zzt.zzaA().zzk().zzb("Raw event data disappeared while in transaction. appId", zzet.zzn(str3));
                                        if (query2 != null) {
                                            query2.close();
                                            return;
                                        }
                                        return;
                                    }
                                    do {
                                        long j3 = query2.getLong(0);
                                        try {
                                            com.google.android.gms.internal.measurement.zzfs zzfsVar = (com.google.android.gms.internal.measurement.zzfs) zzlj.zzm(com.google.android.gms.internal.measurement.zzft.zze(), query2.getBlob(3));
                                            zzfsVar.zzi(query2.getString(i));
                                            zzfsVar.zzm(query2.getLong(2));
                                            if (!zzleVar.zza(j3, (com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD())) {
                                                if (query2 != null) {
                                                    query2.close();
                                                    return;
                                                }
                                                return;
                                            }
                                        } catch (IOException e) {
                                            this.zzt.zzaA().zzd().zzc("Data loss. Failed to merge raw event. appId", zzet.zzn(str3), e);
                                        }
                                    } while (query2.moveToNext());
                                    if (query2 != null) {
                                        query2.close();
                                    }
                                } catch (IOException e2) {
                                    this.zzt.zzaA().zzd().zzc("Data loss. Failed to merge raw event metadata. appId", zzet.zzn(str3), e2);
                                    if (query != null) {
                                        query.close();
                                    }
                                }
                            } catch (SQLiteException e3) {
                                e = e3;
                                r4 = str4;
                                this.zzt.zzaA().zzd().zzc("Data loss. Error selecting raw event. appId", zzet.zzn(str3), e);
                                if (r4 != 0) {
                                    r4.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor2 = str4;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteException e4) {
                            e = e4;
                            str4 = query;
                        } catch (Throwable th2) {
                            th = th2;
                            str4 = query;
                        }
                    } catch (SQLiteException e5) {
                        e = e5;
                        r4 = cursor3;
                    } catch (Throwable th3) {
                        th = th3;
                        cursor2 = cursor3;
                    }
                } catch (SQLiteException e6) {
                    e = e6;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor2 = cursor;
            }
        } catch (SQLiteException e7) {
            e = e7;
            r4 = 0;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            return zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzd("Error deleting conditional property", zzet.zzn(str), this.zzt.zzj().zzf(str2), e);
            return 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzc(String str, String str2) {
        long j;
        SQLiteException e;
        ContentValues contentValues;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzW();
        SQLiteDatabase zzh2 = zzh();
        zzh2.beginTransaction();
        try {
            try {
                j = zzaa("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
                if (j == -1) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", (Integer) 0);
                    contentValues2.put("previous_install_count", (Integer) 0);
                    if (zzh2.insertWithOnConflict("app2", null, contentValues2, 5) == -1) {
                        this.zzt.zzaA().zzd().zzc("Failed to insert column (got -1). appId", zzet.zzn(str), "first_open_count");
                        return -1L;
                    }
                    j = 0;
                }
            } catch (SQLiteException e2) {
                j = 0;
                e = e2;
            }
            try {
                contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Long.valueOf(1 + j));
            } catch (SQLiteException e3) {
                e = e3;
                this.zzt.zzaA().zzd().zzd("Error inserting column. appId", zzet.zzn(str), "first_open_count", e);
                return j;
            }
            if (zzh2.update("app2", contentValues, "app_id = ?", new String[]{str}) == 0) {
                this.zzt.zzaA().zzd().zzc("Failed to update column (got 0). appId", zzet.zzn(str), "first_open_count");
                return -1L;
            }
            zzh2.setTransactionSuccessful();
            return j;
        } finally {
            zzh2.endTransaction();
        }
    }

    public final long zzd() {
        return zzaa("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    public final long zze() {
        return zzaa("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaa("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SQLiteDatabase zzh() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzk().zzb("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00dc: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:60:0x00dc */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle zzi(String str) {
        Cursor cursor;
        Cursor cursor2;
        zzg();
        zzW();
        Cursor cursor3 = null;
        try {
            try {
                cursor = zzh().rawQuery("select parameters from default_event_params where app_id=?", new String[]{str});
                try {
                    if (!cursor.moveToFirst()) {
                        this.zzt.zzaA().zzj().zza("Default event parameters not found");
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                    try {
                        com.google.android.gms.internal.measurement.zzft zzftVar = (com.google.android.gms.internal.measurement.zzft) ((com.google.android.gms.internal.measurement.zzfs) zzlj.zzm(com.google.android.gms.internal.measurement.zzft.zze(), cursor.getBlob(0))).zzaD();
                        this.zzf.zzu();
                        List<com.google.android.gms.internal.measurement.zzfx> zzi2 = zzftVar.zzi();
                        Bundle bundle = new Bundle();
                        for (com.google.android.gms.internal.measurement.zzfx zzfxVar : zzi2) {
                            String zzg2 = zzfxVar.zzg();
                            if (zzfxVar.zzu()) {
                                bundle.putDouble(zzg2, zzfxVar.zza());
                            } else if (zzfxVar.zzv()) {
                                bundle.putFloat(zzg2, zzfxVar.zzb());
                            } else if (zzfxVar.zzy()) {
                                bundle.putString(zzg2, zzfxVar.zzh());
                            } else if (zzfxVar.zzw()) {
                                bundle.putLong(zzg2, zzfxVar.zzd());
                            }
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return bundle;
                    } catch (IOException e) {
                        this.zzt.zzaA().zzd().zzc("Failed to retrieve default event parameters. appId", zzet.zzn(str), e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzt.zzaA().zzd().zzb("Error selecting default event parameters", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor3 = cursor2;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0189 A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01ac A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01d4 A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01f8 A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x020f A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0221 A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TRY_LEAVE, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x017a A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0125 A[Catch: SQLiteException -> 0x023a, all -> 0x0259, TryCatch #0 {SQLiteException -> 0x023a, blocks: (B:5:0x0067, B:11:0x0073, B:13:0x00d6, B:17:0x00e0, B:20:0x012a, B:22:0x0159, B:26:0x0163, B:29:0x017e, B:31:0x0189, B:32:0x019b, B:34:0x01ac, B:36:0x01c3, B:38:0x01d4, B:40:0x01dc, B:43:0x01e4, B:45:0x01e7, B:47:0x01f8, B:48:0x0201, B:50:0x020f, B:51:0x0218, B:53:0x0221, B:58:0x01ba, B:59:0x017a, B:61:0x0125), top: B:4:0x0067 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x025d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzh zzj(String str) {
        Cursor cursor;
        boolean z;
        boolean z2;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        Cursor cursor2 = null;
        try {
            boolean z3 = true;
            cursor = zzh().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", App.JsonKeys.APP_VERSION, "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token", "sgtm_upload_enabled", "target_os_version", "session_stitching_token_hash"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (!cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                    zzh zzhVar = new zzh(this.zzf.zzp(), str);
                    zzhVar.zzJ(cursor.getString(0));
                    zzhVar.zzY(cursor.getString(1));
                    zzhVar.zzag(cursor.getString(2));
                    zzhVar.zzac(cursor.getLong(3));
                    zzhVar.zzad(cursor.getLong(4));
                    zzhVar.zzab(cursor.getLong(5));
                    zzhVar.zzL(cursor.getString(6));
                    zzhVar.zzK(cursor.getString(7));
                    zzhVar.zzZ(cursor.getLong(8));
                    zzhVar.zzU(cursor.getLong(9));
                    if (!cursor.isNull(10) && cursor.getInt(10) == 0) {
                        z = false;
                        zzhVar.zzae(z);
                        zzhVar.zzT(cursor.getLong(11));
                        zzhVar.zzR(cursor.getLong(12));
                        zzhVar.zzQ(cursor.getLong(13));
                        zzhVar.zzO(cursor.getLong(14));
                        zzhVar.zzN(cursor.getLong(15));
                        zzhVar.zzW(cursor.getLong(16));
                        zzhVar.zzM(!cursor.isNull(17) ? -2147483648L : cursor.getInt(17));
                        zzhVar.zzX(cursor.getString(18));
                        zzhVar.zzP(cursor.getLong(19));
                        zzhVar.zzS(cursor.getLong(20));
                        zzhVar.zzaa(cursor.getString(21));
                        if (!cursor.isNull(23) && cursor.getInt(23) == 0) {
                            z2 = false;
                            zzhVar.zzI(z2);
                            zzhVar.zzH(cursor.getString(24));
                            zzhVar.zzV(!cursor.isNull(25) ? 0L : cursor.getLong(25));
                            if (!cursor.isNull(26)) {
                                zzhVar.zzah(Arrays.asList(cursor.getString(26).split(",", -1)));
                            }
                            zzqu.zzc();
                            if (!this.zzt.zzf().zzs(str, zzeg.zzao) || this.zzt.zzf().zzs(null, zzeg.zzam)) {
                                zzhVar.zzai(cursor.getString(28));
                            }
                            zzrd.zzc();
                            if (this.zzt.zzf().zzs(null, zzeg.zzaq)) {
                                if (cursor.isNull(29) || cursor.getInt(29) == 0) {
                                    z3 = false;
                                }
                                zzhVar.zzak(z3);
                            }
                            zzpz.zzc();
                            if (this.zzt.zzf().zzs(null, zzeg.zzaE)) {
                                zzhVar.zzal(cursor.getLong(30));
                            }
                            if (this.zzt.zzf().zzs(null, zzeg.zzaH)) {
                                zzhVar.zzaj(cursor.getLong(31));
                            }
                            zzhVar.zzF();
                            if (cursor.moveToNext()) {
                                this.zzt.zzaA().zzd().zzb("Got multiple records for app, expected one. appId", zzet.zzn(str));
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            return zzhVar;
                        }
                        z2 = true;
                        zzhVar.zzI(z2);
                        zzhVar.zzH(cursor.getString(24));
                        zzhVar.zzV(!cursor.isNull(25) ? 0L : cursor.getLong(25));
                        if (!cursor.isNull(26)) {
                        }
                        zzqu.zzc();
                        if (!this.zzt.zzf().zzs(str, zzeg.zzao)) {
                        }
                        zzhVar.zzai(cursor.getString(28));
                        zzrd.zzc();
                        if (this.zzt.zzf().zzs(null, zzeg.zzaq)) {
                        }
                        zzpz.zzc();
                        if (this.zzt.zzf().zzs(null, zzeg.zzaE)) {
                        }
                        if (this.zzt.zzf().zzs(null, zzeg.zzaH)) {
                        }
                        zzhVar.zzF();
                        if (cursor.moveToNext()) {
                        }
                        if (cursor != null) {
                        }
                        return zzhVar;
                    }
                    z = true;
                    zzhVar.zzae(z);
                    zzhVar.zzT(cursor.getLong(11));
                    zzhVar.zzR(cursor.getLong(12));
                    zzhVar.zzQ(cursor.getLong(13));
                    zzhVar.zzO(cursor.getLong(14));
                    zzhVar.zzN(cursor.getLong(15));
                    zzhVar.zzW(cursor.getLong(16));
                    zzhVar.zzM(!cursor.isNull(17) ? -2147483648L : cursor.getInt(17));
                    zzhVar.zzX(cursor.getString(18));
                    zzhVar.zzP(cursor.getLong(19));
                    zzhVar.zzS(cursor.getLong(20));
                    zzhVar.zzaa(cursor.getString(21));
                    if (!cursor.isNull(23)) {
                        z2 = false;
                        zzhVar.zzI(z2);
                        zzhVar.zzH(cursor.getString(24));
                        zzhVar.zzV(!cursor.isNull(25) ? 0L : cursor.getLong(25));
                        if (!cursor.isNull(26)) {
                        }
                        zzqu.zzc();
                        if (!this.zzt.zzf().zzs(str, zzeg.zzao)) {
                        }
                        zzhVar.zzai(cursor.getString(28));
                        zzrd.zzc();
                        if (this.zzt.zzf().zzs(null, zzeg.zzaq)) {
                        }
                        zzpz.zzc();
                        if (this.zzt.zzf().zzs(null, zzeg.zzaE)) {
                        }
                        if (this.zzt.zzf().zzs(null, zzeg.zzaH)) {
                        }
                        zzhVar.zzF();
                        if (cursor.moveToNext()) {
                        }
                        if (cursor != null) {
                        }
                        return zzhVar;
                    }
                    z2 = true;
                    zzhVar.zzI(z2);
                    zzhVar.zzH(cursor.getString(24));
                    zzhVar.zzV(!cursor.isNull(25) ? 0L : cursor.getLong(25));
                    if (!cursor.isNull(26)) {
                    }
                    zzqu.zzc();
                    if (!this.zzt.zzf().zzs(str, zzeg.zzao)) {
                    }
                    zzhVar.zzai(cursor.getString(28));
                    zzrd.zzc();
                    if (this.zzt.zzf().zzs(null, zzeg.zzaq)) {
                    }
                    zzpz.zzc();
                    if (this.zzt.zzf().zzs(null, zzeg.zzaE)) {
                    }
                    if (this.zzt.zzf().zzs(null, zzeg.zzaH)) {
                    }
                    zzhVar.zzF();
                    if (cursor.moveToNext()) {
                    }
                    if (cursor != null) {
                    }
                    return zzhVar;
                } catch (SQLiteException e) {
                    e = e;
                    this.zzt.zzaA().zzd().zzc("Error querying app. appId", zzet.zzn(str), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x012b: MOVE (r9 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:32:0x012b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzac zzk(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        Cursor cursor3 = null;
        try {
            try {
                cursor = zzh().query("conditional_properties", new String[]{"origin", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                    String string = cursor.getString(0);
                    if (string == null) {
                        string = "";
                    }
                    String str3 = string;
                    Object zzq = zzq(cursor, 1);
                    boolean z = cursor.getInt(2) != 0;
                    zzac zzacVar = new zzac(str, str3, new zzlk(str2, cursor.getLong(8), zzq, str3), cursor.getLong(6), z, cursor.getString(3), (zzau) this.zzf.zzu().zzi(cursor.getBlob(5), zzau.CREATOR), cursor.getLong(4), (zzau) this.zzf.zzu().zzi(cursor.getBlob(7), zzau.CREATOR), cursor.getLong(9), (zzau) this.zzf.zzu().zzi(cursor.getBlob(10), zzau.CREATOR));
                    if (cursor.moveToNext()) {
                        this.zzt.zzaA().zzd().zzc("Got multiple records for conditional property, expected one", zzet.zzn(str), this.zzt.zzj().zzf(str2));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzacVar;
                } catch (SQLiteException e) {
                    e = e;
                    this.zzt.zzaA().zzd().zzd("Error querying conditional property", zzet.zzn(str), this.zzt.zzj().zzf(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor3 = cursor2;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    public final zzai zzl(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j, str, 1L, false, false, z3, false, z5);
    }

    public final zzai zzm(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        String[] strArr = {str};
        zzai zzaiVar = new zzai();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase zzh2 = zzh();
                Cursor query = zzh2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!query.moveToFirst()) {
                    this.zzt.zzaA().zzk().zzb("Not updating daily counts, app is not known. appId", zzet.zzn(str));
                    if (query != null) {
                        query.close();
                    }
                    return zzaiVar;
                }
                if (query.getLong(0) == j) {
                    zzaiVar.zzb = query.getLong(1);
                    zzaiVar.zza = query.getLong(2);
                    zzaiVar.zzc = query.getLong(3);
                    zzaiVar.zzd = query.getLong(4);
                    zzaiVar.zze = query.getLong(5);
                }
                if (z) {
                    zzaiVar.zzb += j2;
                }
                if (z2) {
                    zzaiVar.zza += j2;
                }
                if (z3) {
                    zzaiVar.zzc += j2;
                }
                if (z4) {
                    zzaiVar.zzd += j2;
                }
                if (z5) {
                    zzaiVar.zze += j2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zzaiVar.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzaiVar.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzaiVar.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzaiVar.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzaiVar.zze));
                zzh2.update("apps", contentValues, "app_id=?", strArr);
                if (query != null) {
                    query.close();
                }
                return zzaiVar;
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzc("Error updating daily counts. appId", zzet.zzn(str), e);
                if (0 != 0) {
                    cursor.close();
                }
                return zzaiVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzaq zzn(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Boolean bool;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        Cursor cursor3 = null;
        try {
            Cursor query = zzh().query("events", (String[]) new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new String[0]), "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                long j = query.getLong(0);
                long j2 = query.getLong(1);
                long j3 = query.getLong(2);
                long j4 = query.isNull(3) ? 0L : query.getLong(3);
                Long valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                Long valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                Long valueOf3 = query.isNull(6) ? null : Long.valueOf(query.getLong(6));
                if (query.isNull(7)) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(query.getLong(7) == 1);
                }
                cursor2 = query;
                try {
                    zzaq zzaqVar = new zzaq(str, str2, j, j2, query.isNull(8) ? 0L : query.getLong(8), j3, j4, valueOf, valueOf2, valueOf3, bool);
                    if (cursor2.moveToNext()) {
                        this.zzt.zzaA().zzd().zzb("Got multiple records for event aggregates, expected one. appId", zzet.zzn(str));
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return zzaqVar;
                } catch (SQLiteException e) {
                    e = e;
                    cursor = cursor2;
                    try {
                        this.zzt.zzaA().zzd().zzd("Error querying events. appId", zzet.zzn(str), this.zzt.zzj().zzd(str2), e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        cursor3 = cursor;
                        if (cursor3 != null) {
                            cursor3.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor3 = cursor2;
                    if (cursor3 != null) {
                    }
                    throw th;
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursor2 = query;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00a9: MOVE (r10 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:30:0x00a9 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzlm zzp(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        Cursor cursor3 = null;
        try {
            try {
                cursor = zzh().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                    long j = cursor.getLong(0);
                    Object zzq = zzq(cursor, 1);
                    if (zzq == null) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                    zzlm zzlmVar = new zzlm(str, cursor.getString(2), str2, j, zzq);
                    if (cursor.moveToNext()) {
                        this.zzt.zzaA().zzd().zzb("Got multiple records for user property, expected one. appId", zzet.zzn(str));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzlmVar;
                } catch (SQLiteException e) {
                    e = e;
                    this.zzt.zzaA().zzd().zzd("Error querying user property. appId", zzet.zzn(str), this.zzt.zzj().zzf(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor3 = cursor2;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
    }

    final Object zzq(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            this.zzt.zzaA().zzd().zza("Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type != 4) {
            this.zzt.zzaA().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
            return null;
        }
        this.zzt.zzaA().zzd().zza("Loaded invalid blob type value, ignoring it");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String zzr() {
        SQLiteException e;
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = zzh().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                try {
                    if (!cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                    String string = cursor.getString(0);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return string;
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzt.zzaA().zzd().zzb("Database error getting next bundle app id", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                cursor2 = cursor;
                th = th;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    public final List zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat(ProxyConfig.MATCH_ALL_SCHEMES));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
    
        r2 = r27.zzt.zzaA().zzd();
        r27.zzt.zzf();
        r2.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzt(String str, String[] strArr) {
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase zzh2 = zzh();
                String[] strArr2 = {"app_id", "origin", "name", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"};
                this.zzt.zzf();
                cursor = zzh2.query("conditional_properties", strArr2, str, strArr, null, null, "rowid", "1001");
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
                while (true) {
                    int size = arrayList.size();
                    this.zzt.zzf();
                    if (size >= 1000) {
                        break;
                    }
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zzq = zzq(cursor, 3);
                    boolean z = cursor.getInt(4) != 0;
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    zzau zzauVar = (zzau) this.zzf.zzu().zzi(cursor.getBlob(7), zzau.CREATOR);
                    arrayList.add(new zzac(string, string2, new zzlk(string3, cursor.getLong(10), zzq, string2), cursor.getLong(8), z, string4, zzauVar, j, (zzau) this.zzf.zzu().zzi(cursor.getBlob(9), zzau.CREATOR), cursor.getLong(11), (zzau) this.zzf.zzu().zzi(cursor.getBlob(12), zzau.CREATOR)));
                    if (!cursor.moveToNext()) {
                        break;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzb("Error querying conditional user property value", e);
                List emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List zzu(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                this.zzt.zzf();
                cursor = zzh().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
                do {
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str2 = string2;
                    long j = cursor.getLong(2);
                    Object zzq = zzq(cursor, 3);
                    if (zzq == null) {
                        this.zzt.zzaA().zzd().zzb("Read invalid user property value, ignoring it. appId", zzet.zzn(str));
                    } else {
                        arrayList.add(new zzlm(str, str2, string, j, zzq));
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzc("Error querying user properties. appId", zzet.zzn(str), e);
                List emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a9, code lost:
    
        r0 = r17.zzt.zzaA().zzd();
        r17.zzt.zzf();
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzv(String str, String str2, String str3) {
        String str4;
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                ArrayList arrayList2 = new ArrayList(3);
                try {
                    arrayList2.add(str);
                    StringBuilder sb = new StringBuilder("app_id=?");
                    if (TextUtils.isEmpty(str2)) {
                        str4 = str2;
                    } else {
                        str4 = str2;
                        try {
                            arrayList2.add(str4);
                            sb.append(" and origin=?");
                        } catch (SQLiteException e) {
                            e = e;
                            this.zzt.zzaA().zzd().zzd("(2)Error querying user properties", zzet.zzn(str), str4, e);
                            List emptyList = Collections.emptyList();
                            if (cursor != null) {
                            }
                            return emptyList;
                        }
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        arrayList2.add(str3 + ProxyConfig.MATCH_ALL_SCHEMES);
                        sb.append(" and name glob ?");
                    }
                    String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                    String sb2 = sb.toString();
                    this.zzt.zzf();
                    cursor = zzh().query("user_attributes", new String[]{"name", "set_timestamp", "value", "origin"}, sb2, strArr, null, null, "rowid", "1001");
                    if (!cursor.moveToFirst()) {
                        return arrayList;
                    }
                    while (true) {
                        int size = arrayList.size();
                        this.zzt.zzf();
                        if (size >= 1000) {
                            break;
                        }
                        String string = cursor.getString(0);
                        long j = cursor.getLong(1);
                        Object zzq = zzq(cursor, 2);
                        str4 = cursor.getString(3);
                        if (zzq == null) {
                            this.zzt.zzaA().zzd().zzd("(2)Read invalid user property value, ignoring it", zzet.zzn(str), str4, str3);
                        } else {
                            arrayList.add(new zzlm(str, str4, string, j, zzq));
                        }
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (SQLiteException e2) {
                    e = e2;
                    str4 = str2;
                    this.zzt.zzaA().zzd().zzd("(2)Error querying user properties", zzet.zzn(str), str4, e);
                    List emptyList2 = Collections.emptyList();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return emptyList2;
                }
            } catch (SQLiteException e3) {
                e = e3;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final void zzw() {
        zzW();
        zzh().beginTransaction();
    }

    public final void zzx() {
        zzW();
        zzh().endTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzy(List list) {
        zzg();
        zzW();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzI()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzZ("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", null) > 0) {
                this.zzt.zzaA().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                zzh().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                this.zzt.zzaA().zzd().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzz() {
        zzg();
        zzW();
        if (zzI()) {
            long zza2 = this.zzf.zzs().zza.zza();
            long elapsedRealtime = this.zzt.zzax().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            this.zzt.zzf();
            if (abs > ((Long) zzeg.zzy.zza(null)).longValue()) {
                this.zzf.zzs().zza.zzb(elapsedRealtime);
                zzg();
                zzW();
                if (zzI()) {
                    SQLiteDatabase zzh2 = zzh();
                    this.zzt.zzf();
                    int delete = zzh2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.zzt.zzax().currentTimeMillis()), String.valueOf(zzag.zzA())});
                    if (delete > 0) {
                        this.zzt.zzaA().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }
}
