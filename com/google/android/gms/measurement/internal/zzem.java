package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzem extends zzf {
    private final zzel zza;
    private boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzem(zzgd zzgdVar) {
        super(zzgdVar);
        Context zzaw = this.zzt.zzaw();
        this.zzt.zzf();
        this.zza = new zzel(this, zzaw, "google_app_measurement_local.db");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0129  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v10, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zzq(int i, byte[] bArr) {
        SQLiteDatabase sQLiteDatabase;
        ?? r10;
        Cursor cursor;
        zzg();
        ?? r2 = 0;
        if (this.zzb) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        this.zzt.zzf();
        int i2 = 0;
        int i3 = 5;
        for (int i4 = 5; i2 < i4; i4 = 5) {
            Cursor cursor2 = null;
            cursor2 = null;
            cursor2 = null;
            r8 = null;
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase = zzh();
                try {
                    if (sQLiteDatabase == null) {
                        this.zzb = true;
                        return r2;
                    }
                    sQLiteDatabase.beginTransaction();
                    r10 = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                    long j = 0;
                    if (r10 != 0) {
                        try {
                            if (r10.moveToFirst()) {
                                j = r10.getLong(r2);
                            }
                        } catch (SQLiteDatabaseLockedException unused) {
                            cursor2 = r10;
                            try {
                                SystemClock.sleep(i3);
                                i3 += 20;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                i2++;
                                r2 = 0;
                            } catch (Throwable th) {
                                th = th;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteFullException e) {
                            e = e;
                            sQLiteDatabase2 = sQLiteDatabase;
                            cursor = r10;
                            this.zzt.zzaA().zzd().zzb("Error writing entry; local database full", e);
                            this.zzb = true;
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase2 == null) {
                                i2++;
                                r2 = 0;
                            }
                            sQLiteDatabase2.close();
                            i2++;
                            r2 = 0;
                        } catch (SQLiteException e2) {
                            e = e2;
                            sQLiteDatabase2 = sQLiteDatabase;
                            r10 = r10;
                            if (sQLiteDatabase2 != null) {
                                try {
                                    if (sQLiteDatabase2.inTransaction()) {
                                        sQLiteDatabase2.endTransaction();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    cursor2 = r10;
                                    if (cursor2 != null) {
                                    }
                                    if (sQLiteDatabase != null) {
                                    }
                                    throw th;
                                }
                            }
                            this.zzt.zzaA().zzd().zzb("Error writing entry to local database", e);
                            this.zzb = true;
                            if (r10 != 0) {
                                r10.close();
                            }
                            if (sQLiteDatabase2 == null) {
                                i2++;
                                r2 = 0;
                            }
                            sQLiteDatabase2.close();
                            i2++;
                            r2 = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = r10;
                            if (cursor2 != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    }
                    if (j >= SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) {
                        this.zzt.zzaA().zzd().zza("Data loss, local db full");
                        String[] strArr = new String[1];
                        long j2 = (SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US - j) + 1;
                        strArr[r2] = Long.toString(j2);
                        long delete = sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", strArr);
                        if (delete != j2) {
                            this.zzt.zzaA().zzd().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(delete), Long.valueOf(j2 - delete));
                        }
                    }
                    sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (r10 != 0) {
                        r10.close();
                    }
                    sQLiteDatabase.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused2) {
                } catch (SQLiteFullException e3) {
                    e = e3;
                    r10 = 0;
                } catch (SQLiteException e4) {
                    e = e4;
                    r10 = 0;
                }
            } catch (SQLiteDatabaseLockedException unused3) {
                sQLiteDatabase = null;
            } catch (SQLiteFullException e5) {
                e = e5;
                cursor = null;
            } catch (SQLiteException e6) {
                e = e6;
                r10 = 0;
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabase = null;
                if (cursor2 != null) {
                }
                if (sQLiteDatabase != null) {
                }
                throw th;
            }
        }
        this.zzt.zzaA().zzj().zza("Failed to write entry to local database");
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    final SQLiteDatabase zzh() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:200:0x01e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0205 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x025f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zzi(int i) {
        int i2;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor2;
        long j;
        long j2;
        String str;
        String[] strArr;
        Parcel obtain;
        zzlk zzlkVar;
        zzac zzacVar;
        zzg();
        Cursor cursor3 = null;
        if (this.zzb) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzl()) {
            return arrayList;
        }
        int i3 = 5;
        for (0; i2 < 5; i2 + 1) {
            try {
                SQLiteDatabase zzh = zzh();
                if (zzh == null) {
                    this.zzb = true;
                    return null;
                }
                try {
                    zzh.beginTransaction();
                    try {
                        try {
                            cursor2 = zzh.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
                            try {
                                j = -1;
                                try {
                                    if (cursor2.moveToFirst()) {
                                        j2 = cursor2.getLong(0);
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                    } else {
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        j2 = -1;
                                    }
                                    if (j2 != -1) {
                                        str = "rowid<?";
                                        strArr = new String[]{String.valueOf(j2)};
                                    } else {
                                        str = null;
                                        strArr = null;
                                    }
                                    cursor = zzh.query("messages", new String[]{"rowid", "type", "entry"}, str, strArr, null, null, "rowid asc", Integer.toString(100));
                                    while (cursor.moveToNext()) {
                                        try {
                                            j = cursor.getLong(0);
                                            int i4 = cursor.getInt(1);
                                            byte[] blob = cursor.getBlob(2);
                                            if (i4 == 0) {
                                                obtain = Parcel.obtain();
                                                try {
                                                    try {
                                                        obtain.unmarshall(blob, 0, blob.length);
                                                        obtain.setDataPosition(0);
                                                        zzau createFromParcel = zzau.CREATOR.createFromParcel(obtain);
                                                        if (createFromParcel != null) {
                                                            arrayList.add(createFromParcel);
                                                        }
                                                    } catch (SafeParcelReader.ParseException unused) {
                                                        this.zzt.zzaA().zzd().zza("Failed to load event from local database");
                                                        obtain.recycle();
                                                    }
                                                } finally {
                                                }
                                            } else if (i4 == 1) {
                                                obtain = Parcel.obtain();
                                                try {
                                                    try {
                                                        obtain.unmarshall(blob, 0, blob.length);
                                                        obtain.setDataPosition(0);
                                                        zzlkVar = zzlk.CREATOR.createFromParcel(obtain);
                                                    } catch (SafeParcelReader.ParseException unused2) {
                                                        this.zzt.zzaA().zzd().zza("Failed to load user property from local database");
                                                        obtain.recycle();
                                                        zzlkVar = null;
                                                    }
                                                    if (zzlkVar != null) {
                                                        arrayList.add(zzlkVar);
                                                    }
                                                } finally {
                                                }
                                            } else if (i4 == 2) {
                                                obtain = Parcel.obtain();
                                                try {
                                                    try {
                                                        obtain.unmarshall(blob, 0, blob.length);
                                                        obtain.setDataPosition(0);
                                                        zzacVar = zzac.CREATOR.createFromParcel(obtain);
                                                    } catch (SafeParcelReader.ParseException unused3) {
                                                        this.zzt.zzaA().zzd().zza("Failed to load conditional user property from local database");
                                                        obtain.recycle();
                                                        zzacVar = null;
                                                    }
                                                    if (zzacVar != null) {
                                                        arrayList.add(zzacVar);
                                                    }
                                                } finally {
                                                }
                                            } else if (i4 == 3) {
                                                this.zzt.zzaA().zzk().zza("Skipping app launch break");
                                            } else {
                                                this.zzt.zzaA().zzd().zza("Unknown record type in local database");
                                            }
                                        } catch (SQLiteDatabaseLockedException unused4) {
                                            sQLiteDatabase2 = zzh;
                                        } catch (SQLiteFullException e) {
                                            e = e;
                                            sQLiteDatabase2 = zzh;
                                        } catch (SQLiteException e2) {
                                            e = e2;
                                            sQLiteDatabase2 = zzh;
                                        } catch (Throwable th) {
                                            th = th;
                                            sQLiteDatabase2 = zzh;
                                        }
                                    }
                                    sQLiteDatabase2 = zzh;
                                } catch (SQLiteDatabaseLockedException unused5) {
                                    sQLiteDatabase2 = zzh;
                                    cursor = null;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    SystemClock.sleep(i3);
                                    i3 += 20;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    i2 = sQLiteDatabase == null ? i2 + 1 : 0;
                                    sQLiteDatabase.close();
                                } catch (SQLiteFullException e3) {
                                    e = e3;
                                    sQLiteDatabase2 = zzh;
                                    cursor = null;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    this.zzt.zzaA().zzd().zzb("Error reading entries from local database", e);
                                    this.zzb = true;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (sQLiteDatabase == null) {
                                    }
                                    sQLiteDatabase.close();
                                } catch (SQLiteException e4) {
                                    e = e4;
                                    sQLiteDatabase2 = zzh;
                                    cursor = null;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    if (sQLiteDatabase != null) {
                                        try {
                                            if (sQLiteDatabase.inTransaction()) {
                                                sQLiteDatabase.endTransaction();
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor3 = cursor;
                                            if (cursor3 != null) {
                                                cursor3.close();
                                            }
                                            if (sQLiteDatabase != null) {
                                                sQLiteDatabase.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    this.zzt.zzaA().zzd().zzb("Error reading entries from local database", e);
                                    this.zzb = true;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (sQLiteDatabase == null) {
                                    }
                                    sQLiteDatabase.close();
                                } catch (Throwable th3) {
                                    th = th3;
                                    sQLiteDatabase2 = zzh;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                sQLiteDatabase2 = zzh;
                                if (cursor2 != null) {
                                    try {
                                        cursor2.close();
                                    } catch (SQLiteDatabaseLockedException unused6) {
                                        cursor = null;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        SystemClock.sleep(i3);
                                        i3 += 20;
                                        if (cursor != null) {
                                        }
                                        if (sQLiteDatabase == null) {
                                        }
                                        sQLiteDatabase.close();
                                    } catch (SQLiteFullException e5) {
                                        e = e5;
                                        cursor = null;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        this.zzt.zzaA().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursor != null) {
                                        }
                                        if (sQLiteDatabase == null) {
                                        }
                                        sQLiteDatabase.close();
                                    } catch (SQLiteException e6) {
                                        e = e6;
                                        cursor = null;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        if (sQLiteDatabase != null) {
                                        }
                                        this.zzt.zzaA().zzd().zzb("Error reading entries from local database", e);
                                        this.zzb = true;
                                        if (cursor != null) {
                                        }
                                        if (sQLiteDatabase == null) {
                                        }
                                        sQLiteDatabase.close();
                                    } catch (Throwable th5) {
                                        th = th5;
                                        sQLiteDatabase = sQLiteDatabase2;
                                        if (cursor3 != null) {
                                        }
                                        if (sQLiteDatabase != null) {
                                        }
                                        throw th;
                                    }
                                }
                                throw th;
                                break;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            sQLiteDatabase2 = zzh;
                            cursor2 = null;
                            if (cursor2 != null) {
                            }
                            throw th;
                            break;
                            break;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        sQLiteDatabase2 = zzh;
                    }
                } catch (SQLiteDatabaseLockedException unused7) {
                    sQLiteDatabase2 = zzh;
                } catch (SQLiteFullException e7) {
                    e = e7;
                    sQLiteDatabase2 = zzh;
                } catch (SQLiteException e8) {
                    e = e8;
                    sQLiteDatabase2 = zzh;
                } catch (Throwable th8) {
                    th = th8;
                    sQLiteDatabase2 = zzh;
                }
                try {
                    if (sQLiteDatabase2.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                        this.zzt.zzaA().zzd().zza("Fewer entries removed from local database than expected");
                    }
                    sQLiteDatabase2.setTransactionSuccessful();
                    sQLiteDatabase2.endTransaction();
                    if (cursor != null) {
                        cursor.close();
                    }
                    sQLiteDatabase2.close();
                    return arrayList;
                } catch (SQLiteDatabaseLockedException unused8) {
                    sQLiteDatabase = sQLiteDatabase2;
                    SystemClock.sleep(i3);
                    i3 += 20;
                    if (cursor != null) {
                    }
                    if (sQLiteDatabase == null) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteFullException e9) {
                    e = e9;
                    sQLiteDatabase = sQLiteDatabase2;
                    this.zzt.zzaA().zzd().zzb("Error reading entries from local database", e);
                    this.zzb = true;
                    if (cursor != null) {
                    }
                    if (sQLiteDatabase == null) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteException e10) {
                    e = e10;
                    sQLiteDatabase = sQLiteDatabase2;
                    if (sQLiteDatabase != null) {
                    }
                    this.zzt.zzaA().zzd().zzb("Error reading entries from local database", e);
                    this.zzb = true;
                    if (cursor != null) {
                    }
                    if (sQLiteDatabase == null) {
                    }
                    sQLiteDatabase.close();
                } catch (Throwable th9) {
                    th = th9;
                    cursor3 = cursor;
                    sQLiteDatabase = sQLiteDatabase2;
                    if (cursor3 != null) {
                    }
                    if (sQLiteDatabase != null) {
                    }
                    throw th;
                }
            } catch (SQLiteDatabaseLockedException unused9) {
                cursor = null;
                sQLiteDatabase = null;
            } catch (SQLiteFullException e11) {
                e = e11;
                cursor = null;
                sQLiteDatabase = null;
            } catch (SQLiteException e12) {
                e = e12;
                cursor = null;
                sQLiteDatabase = null;
            } catch (Throwable th10) {
                th = th10;
                sQLiteDatabase = null;
            }
        }
        this.zzt.zzaA().zzk().zza("Failed to read events from database in reasonable time");
        return null;
    }

    public final void zzj() {
        int delete;
        zzg();
        try {
            SQLiteDatabase zzh = zzh();
            if (zzh == null || (delete = zzh.delete("messages", null, null)) <= 0) {
                return;
            }
            this.zzt.zzaA().zzj().zzb("Reset local analytics data. records", Integer.valueOf(delete));
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzb("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    final boolean zzl() {
        Context zzaw = this.zzt.zzaw();
        this.zzt.zzf();
        return zzaw.getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final boolean zzm() {
        int i;
        zzg();
        if (!this.zzb && zzl()) {
            int i2 = 5;
            for (0; i < 5; i + 1) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    SQLiteDatabase zzh = zzh();
                    if (zzh == null) {
                        this.zzb = true;
                        return false;
                    }
                    zzh.beginTransaction();
                    zzh.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    zzh.setTransactionSuccessful();
                    zzh.endTransaction();
                    zzh.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep(i2);
                    i2 += 20;
                    i = 0 == 0 ? i + 1 : 0;
                    sQLiteDatabase.close();
                } catch (SQLiteFullException e) {
                    this.zzt.zzaA().zzd().zzb("Error deleting app launch break from local database", e);
                    this.zzb = true;
                    if (0 == 0) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteException e2) {
                    if (0 != 0) {
                        try {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                    this.zzt.zzaA().zzd().zzb("Error deleting app launch break from local database", e2);
                    this.zzb = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            }
            this.zzt.zzaA().zzk().zza("Error deleting app launch break from local database in reasonable time");
        }
        return false;
    }

    public final boolean zzn(zzac zzacVar) {
        byte[] zzap = this.zzt.zzv().zzap(zzacVar);
        if (zzap.length > 131072) {
            this.zzt.zzaA().zzh().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zzq(2, zzap);
    }

    public final boolean zzo(zzau zzauVar) {
        Parcel obtain = Parcel.obtain();
        zzav.zza(zzauVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            this.zzt.zzaA().zzh().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zzq(0, marshall);
    }

    public final boolean zzp(zzlk zzlkVar) {
        Parcel obtain = Parcel.obtain();
        zzll.zza(zzlkVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            this.zzt.zzaA().zzh().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return zzq(1, marshall);
    }
}
