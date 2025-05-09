package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzoy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzaa extends zzku {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzlh zzlhVar) {
        super(zzlhVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzu.zzb(zzuVar).get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(17:(6:19|20|21|22|23|(22:(7:25|26|27|28|(1:30)(3:500|(1:502)(1:504)|503)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:458|(6:459|460|461|462|463|(1:466)(1:465))|(1:468)|469)|48|(1:50)(6:289|(9:291|292|293|294|295|296|297|298|(1:(4:300|(1:302)|303|(1:306)(1:305)))(1:443))(1:457)|352|(10:355|(3:359|(4:362|(5:364|365|(1:367)(1:371)|368|369)(1:372)|370|360)|373)|374|(3:378|(4:381|(3:386|387|388)|389|379)|392)|393|(3:395|(6:398|(2:400|(3:402|403|404))(1:407)|405|406|404|396)|408)|409|(3:418|(8:421|(1:423)|424|(1:426)|427|(3:429|430|431)(1:433)|432|419)|434)|435|353)|441|442)|51|52|(3:184|(4:187|(10:189|190|(1:192)(1:286)|193|(12:195|196|197|198|199|200|201|202|203|204|(4:206|(11:207|208|209|210|211|212|213|(3:215|216|217)(1:260)|218|219|(1:222)(1:221))|(1:224)|225)(2:266|267)|226)(1:285)|227|(4:230|(3:248|249|250)(4:232|233|(2:234|(2:236|(1:238)(2:239|240))(1:247))|(3:242|243|244)(1:246))|245|228)|251|252|253)(1:287)|254|185)|288)|54|55|(3:82|(6:85|(15:87|88|89|90|91|92|93|94|95|96|(1:162)|(8:98|99|100|101|102|(1:104)(1:158)|105|106)|(1:110)|111|112)(1:182)|113|(2:114|(2:116|(3:152|153|154)(6:118|(2:119|(4:121|(3:123|(1:125)(1:148)|126)(1:149)|127|(1:1)(2:131|(1:133)(2:134|135)))(2:150|151))|(2:140|139)|137|138|139))(0))|155|83)|183)|57|58|(9:61|62|63|64|65|66|(2:68|69)(1:71)|70|59)|79|80)(1:508))|41|42|(0)(0)|48|(0)(0)|51|52|(0)|54|55|(0)|57|58|(1:59)|79|80) */
    /* JADX WARN: Can't wrap try/catch for region: R(27:1|(2:2|(2:4|(2:6|7)(1:524))(2:525|526))|8|(3:10|11|12)|16|(6:19|20|21|22|23|(22:(7:25|26|27|28|(1:30)(3:500|(1:502)(1:504)|503)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:458|(6:459|460|461|462|463|(1:466)(1:465))|(1:468)|469)|48|(1:50)(6:289|(9:291|292|293|294|295|296|297|298|(1:(4:300|(1:302)|303|(1:306)(1:305)))(1:443))(1:457)|352|(10:355|(3:359|(4:362|(5:364|365|(1:367)(1:371)|368|369)(1:372)|370|360)|373)|374|(3:378|(4:381|(3:386|387|388)|389|379)|392)|393|(3:395|(6:398|(2:400|(3:402|403|404))(1:407)|405|406|404|396)|408)|409|(3:418|(8:421|(1:423)|424|(1:426)|427|(3:429|430|431)(1:433)|432|419)|434)|435|353)|441|442)|51|52|(3:184|(4:187|(10:189|190|(1:192)(1:286)|193|(12:195|196|197|198|199|200|201|202|203|204|(4:206|(11:207|208|209|210|211|212|213|(3:215|216|217)(1:260)|218|219|(1:222)(1:221))|(1:224)|225)(2:266|267)|226)(1:285)|227|(4:230|(3:248|249|250)(4:232|233|(2:234|(2:236|(1:238)(2:239|240))(1:247))|(3:242|243|244)(1:246))|245|228)|251|252|253)(1:287)|254|185)|288)|54|55|(3:82|(6:85|(15:87|88|89|90|91|92|93|94|95|96|(1:162)|(8:98|99|100|101|102|(1:104)(1:158)|105|106)|(1:110)|111|112)(1:182)|113|(2:114|(2:116|(3:152|153|154)(6:118|(2:119|(4:121|(3:123|(1:125)(1:148)|126)(1:149)|127|(1:1)(2:131|(1:133)(2:134|135)))(2:150|151))|(2:140|139)|137|138|139))(0))|155|83)|183)|57|58|(9:61|62|63|64|65|66|(2:68|69)(1:71)|70|59)|79|80)(1:508))|523|38|39|40|41|42|(0)(0)|48|(0)(0)|51|52|(0)|54|55|(0)|57|58|(1:59)|79|80|(5:(0)|(0)|(0)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0a1f, code lost:
    
        r0 = r64.zzt.zzaA().zzk();
        r6 = com.google.android.gms.measurement.internal.zzet.zzn(r64.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0a33, code lost:
    
        if (r7.zzj() == false) goto L408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0a35, code lost:
    
        r7 = java.lang.Integer.valueOf(r7.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0a3f, code lost:
    
        r0.zzc("Invalid property filter ID. appId, id", r6, java.lang.String.valueOf(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0a3e, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x08f1, code lost:
    
        if (r3 != null) goto L357;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x08f3, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x091f, code lost:
    
        if (r3 != null) goto L357;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0778, code lost:
    
        if (r5 != null) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x0740, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x073e, code lost:
    
        if (r5 != null) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x02cc, code lost:
    
        if (r5 != null) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x02ce, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x0304, code lost:
    
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);
        r1 = new androidx.collection.ArrayMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x0313, code lost:
    
        if (r13.isEmpty() == false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x0317, code lost:
    
        r3 = r13.keySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0323, code lost:
    
        if (r3.hasNext() == false) goto L517;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x0325, code lost:
    
        r4 = ((java.lang.Integer) r3.next()).intValue();
        r5 = java.lang.Integer.valueOf(r4);
        r6 = (com.google.android.gms.internal.measurement.zzgi) r13.get(r5);
        r7 = (java.util.List) r0.get(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x033f, code lost:
    
        if (r7 == null) goto L520;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0345, code lost:
    
        if (r7.isEmpty() == false) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0349, code lost:
    
        r5 = r64.zzf.zzu().zzr(r6.zzi(), r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x035b, code lost:
    
        if (r5.isEmpty() != false) goto L522;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x035d, code lost:
    
        r8 = (com.google.android.gms.internal.measurement.zzgh) r6.zzbB();
        r8.zzf();
        r8.zzb(r5);
        r20 = r0;
        r0 = r64.zzf.zzu().zzr(r6.zzk(), r7);
        r8.zzh();
        r8.zzd(r0);
        r0 = new java.util.ArrayList();
        r5 = r6.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0390, code lost:
    
        if (r5.hasNext() == false) goto L524;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0392, code lost:
    
        r22 = r3;
        r3 = (com.google.android.gms.internal.measurement.zzfr) r5.next();
        r23 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x03aa, code lost:
    
        if (r7.contains(java.lang.Integer.valueOf(r3.zza())) != false) goto L526;
     */
    /* JADX WARN: Code restructure failed: missing block: B:326:0x03ac, code lost:
    
        r0.add(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x03af, code lost:
    
        r3 = r22;
        r5 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x03b4, code lost:
    
        r22 = r3;
        r8.zze();
        r8.zza(r0);
        r0 = new java.util.ArrayList();
        r3 = r6.zzj().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x03cd, code lost:
    
        if (r3.hasNext() == false) goto L528;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x03cf, code lost:
    
        r5 = (com.google.android.gms.internal.measurement.zzgk) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x03e1, code lost:
    
        if (r7.contains(java.lang.Integer.valueOf(r5.zzb())) != false) goto L530;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x03e3, code lost:
    
        r0.add(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x03e7, code lost:
    
        r8.zzg();
        r8.zzc(r0);
        r1.put(java.lang.Integer.valueOf(r4), (com.google.android.gms.internal.measurement.zzgi) r8.zzaD());
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x0402, code lost:
    
        r0 = r20;
        r3 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x03fb, code lost:
    
        r20 = r0;
        r22 = r3;
        r1.put(r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0408, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x02d8, code lost:
    
        if (r5 != null) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x0301, code lost:
    
        if (r5 == null) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:488:0x0228, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:489:0x0229, code lost:
    
        r18 = "audience_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x0232, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0233, code lost:
    
        r18 = "audience_id";
        r19 = "data";
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x022e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x022f, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x0151, code lost:
    
        if (r5 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:510:0x0153, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:513:0x0175, code lost:
    
        if (r5 == null) goto L58;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:184:0x05c1  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01b4 A[Catch: SQLiteException -> 0x0228, all -> 0x0b0c, TRY_LEAVE, TryCatch #10 {SQLiteException -> 0x0228, blocks: (B:42:0x01ae, B:44:0x01b4, B:458:0x01c4, B:459:0x01c9, B:461:0x01d3, B:462:0x01e3, B:478:0x01f2), top: B:41:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:458:0x01c4 A[Catch: SQLiteException -> 0x0228, all -> 0x0b0c, TRY_ENTER, TryCatch #10 {SQLiteException -> 0x0228, blocks: (B:42:0x01ae, B:44:0x01b4, B:458:0x01c4, B:459:0x01c9, B:461:0x01d3, B:462:0x01e3, B:478:0x01f2), top: B:41:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0a7c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x082e  */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v68, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v69 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v70, types: [java.lang.String] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List zza(String str, List list, List list2, Long l, Long l2) {
        int i;
        int i2;
        boolean z;
        Cursor cursor;
        Map map;
        String str2;
        String str3;
        Cursor cursor2;
        Map map2;
        String str4;
        Map map3;
        String str5;
        String str6;
        List<com.google.android.gms.internal.measurement.zzek> list3;
        String str7;
        Cursor cursor3;
        zzaq zzaqVar;
        zzw zzwVar;
        Iterator it;
        zzaq zzaqVar2;
        String str8;
        Cursor cursor4;
        List list4;
        Iterator it2;
        String str9;
        String str10;
        Map map4;
        Cursor cursor5;
        Cursor cursor6;
        List list5;
        Iterator it3;
        ArrayMap arrayMap;
        Cursor cursor7;
        List list6;
        String str11 = "current_results";
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l;
        this.zze = l2;
        Iterator it4 = list.iterator();
        while (true) {
            i = 0;
            i2 = 1;
            if (!it4.hasNext()) {
                z = false;
                break;
            }
            if ("_s".equals(((com.google.android.gms.internal.measurement.zzft) it4.next()).zzh())) {
                z = true;
                break;
            }
        }
        zzoy.zzc();
        boolean zzs = this.zzt.zzf().zzs(this.zza, zzeg.zzY);
        zzoy.zzc();
        boolean zzs2 = this.zzt.zzf().zzs(this.zza, zzeg.zzX);
        if (z) {
            zzak zzh = this.zzf.zzh();
            String str12 = this.zza;
            zzh.zzW();
            zzh.zzg();
            Preconditions.checkNotEmpty(str12);
            ContentValues contentValues = new ContentValues();
            ?? r5 = "current_session_count";
            contentValues.put("current_session_count", (Integer) 0);
            try {
                r5 = "events";
                zzh.zzh().update("events", contentValues, "app_id = ?", new String[]{str12});
                cursor = "events";
            } catch (SQLiteException e) {
                zzh.zzt.zzaA().zzd().zzc("Error resetting session-scoped event counts. appId", zzet.zzn(str12), e);
                cursor = r5;
            }
        }
        Map emptyMap = Collections.emptyMap();
        String str13 = "Failed to merge filter. appId";
        String str14 = "data";
        String str15 = "audience_id";
        try {
            if (zzs2 && zzs) {
                zzak zzh2 = this.zzf.zzh();
                String str16 = this.zza;
                Preconditions.checkNotEmpty(str16);
                ArrayMap arrayMap2 = new ArrayMap();
                try {
                    try {
                        cursor7 = zzh2.zzh().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str16}, null, null, null);
                        try {
                        } catch (SQLiteException e2) {
                            e = e2;
                            zzh2.zzt.zzaA().zzd().zzc("Database error querying filters. appId", zzet.zzn(str16), e);
                            emptyMap = Collections.emptyMap();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (SQLiteException e3) {
                    e = e3;
                    cursor7 = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    if (cursor != null) {
                    }
                    throw th;
                }
                if (cursor7.moveToFirst()) {
                    while (true) {
                        try {
                            com.google.android.gms.internal.measurement.zzek zzekVar = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursor7.getBlob(i2))).zzaD();
                            if (zzekVar.zzo()) {
                                Integer valueOf = Integer.valueOf(cursor7.getInt(i));
                                List list7 = (List) arrayMap2.get(valueOf);
                                if (list7 == null) {
                                    list6 = new ArrayList();
                                    arrayMap2.put(valueOf, list6);
                                } else {
                                    list6 = list7;
                                }
                                list6.add(zzekVar);
                            }
                        } catch (IOException e4) {
                            zzh2.zzt.zzaA().zzd().zzc("Failed to merge filter. appId", zzet.zzn(str16), e4);
                        }
                        if (!cursor7.moveToNext()) {
                            break;
                        }
                        i = 0;
                        i2 = 1;
                    }
                    if (cursor7 != null) {
                        cursor7.close();
                    }
                    map = arrayMap2;
                    zzak zzh3 = this.zzf.zzh();
                    String str17 = this.zza;
                    zzh3.zzW();
                    zzh3.zzg();
                    Preconditions.checkNotEmpty(str17);
                    cursor2 = zzh3.zzh().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str17}, null, null, null);
                    if (cursor2.moveToFirst()) {
                        Map emptyMap2 = Collections.emptyMap();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        map2 = emptyMap2;
                        str2 = "audience_id";
                        str3 = "data";
                    } else {
                        ArrayMap arrayMap3 = new ArrayMap();
                        while (true) {
                            int i3 = cursor2.getInt(0);
                            try {
                                arrayMap3.put(Integer.valueOf(i3), (com.google.android.gms.internal.measurement.zzgi) ((com.google.android.gms.internal.measurement.zzgh) zzlj.zzm(com.google.android.gms.internal.measurement.zzgi.zze(), cursor2.getBlob(1))).zzaD());
                                arrayMap = arrayMap3;
                                str2 = str15;
                                str3 = str14;
                            } catch (IOException e5) {
                                arrayMap = arrayMap3;
                                str2 = str15;
                                try {
                                    str3 = str14;
                                    try {
                                        zzh3.zzt.zzaA().zzd().zzd("Failed to merge filter results. appId, audienceId, error", zzet.zzn(str17), Integer.valueOf(i3), e5);
                                    } catch (SQLiteException e6) {
                                        e = e6;
                                        zzh3.zzt.zzaA().zzd().zzc("Database error querying filter results. appId", zzet.zzn(str17), e);
                                        Map emptyMap3 = Collections.emptyMap();
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        map2 = emptyMap3;
                                        if (map2.isEmpty()) {
                                        }
                                        String str18 = str2;
                                        String str19 = str3;
                                        if (!list.isEmpty()) {
                                        }
                                        String str20 = str11;
                                        if (!list2.isEmpty()) {
                                        }
                                        String str21 = str18;
                                        ArrayList arrayList = new ArrayList();
                                        Set keySet = this.zzc.keySet();
                                        keySet.removeAll(this.zzb);
                                        it3 = keySet.iterator();
                                        while (it3.hasNext()) {
                                        }
                                        return arrayList;
                                    }
                                } catch (SQLiteException e7) {
                                    e = e7;
                                    str3 = str14;
                                    zzh3.zzt.zzaA().zzd().zzc("Database error querying filter results. appId", zzet.zzn(str17), e);
                                    Map emptyMap32 = Collections.emptyMap();
                                    if (cursor2 != null) {
                                    }
                                    map2 = emptyMap32;
                                    if (map2.isEmpty()) {
                                    }
                                    String str182 = str2;
                                    String str192 = str3;
                                    if (!list.isEmpty()) {
                                    }
                                    String str202 = str11;
                                    if (!list2.isEmpty()) {
                                    }
                                    String str212 = str182;
                                    ArrayList arrayList2 = new ArrayList();
                                    Set keySet2 = this.zzc.keySet();
                                    keySet2.removeAll(this.zzb);
                                    it3 = keySet2.iterator();
                                    while (it3.hasNext()) {
                                    }
                                    return arrayList2;
                                }
                            }
                            if (!cursor2.moveToNext()) {
                                break;
                            }
                            arrayMap3 = arrayMap;
                            str15 = str2;
                            str14 = str3;
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        map2 = arrayMap;
                    }
                    if (map2.isEmpty()) {
                        HashSet hashSet = new HashSet(map2.keySet());
                        if (z) {
                            String str22 = this.zza;
                            zzak zzh4 = this.zzf.zzh();
                            String str23 = this.zza;
                            zzh4.zzW();
                            zzh4.zzg();
                            Preconditions.checkNotEmpty(str23);
                            Map arrayMap4 = new ArrayMap();
                            ?? zzh5 = zzh4.zzh();
                            try {
                                str4 = "Database error querying filters. appId";
                            } catch (Throwable th3) {
                                th = th3;
                            }
                            try {
                                int i4 = 0;
                                cursor3 = zzh5.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str23, str23});
                                try {
                                    if (cursor3.moveToFirst()) {
                                        while (true) {
                                            Integer valueOf2 = Integer.valueOf(cursor3.getInt(i4));
                                            List list8 = (List) arrayMap4.get(valueOf2);
                                            if (list8 == null) {
                                                list8 = new ArrayList();
                                                arrayMap4.put(valueOf2, list8);
                                            }
                                            list8.add(Integer.valueOf(cursor3.getInt(1)));
                                            if (!cursor3.moveToNext()) {
                                                break;
                                            }
                                            i4 = 0;
                                        }
                                    } else {
                                        arrayMap4 = Collections.emptyMap();
                                    }
                                } catch (SQLiteException e8) {
                                    e = e8;
                                    zzh4.zzt.zzaA().zzd().zzc("Database error querying scoped filters. appId", zzet.zzn(str23), e);
                                    arrayMap4 = Collections.emptyMap();
                                }
                            } catch (SQLiteException e9) {
                                e = e9;
                                cursor3 = null;
                            } catch (Throwable th4) {
                                th = th4;
                                zzh5 = 0;
                                if (zzh5 != 0) {
                                    zzh5.close();
                                }
                                throw th;
                            }
                        } else {
                            str4 = "Database error querying filters. appId";
                            map3 = map2;
                        }
                        Iterator it5 = hashSet.iterator();
                        while (it5.hasNext()) {
                            int intValue = ((Integer) it5.next()).intValue();
                            com.google.android.gms.internal.measurement.zzgi zzgiVar = (com.google.android.gms.internal.measurement.zzgi) map3.get(Integer.valueOf(intValue));
                            BitSet bitSet = new BitSet();
                            BitSet bitSet2 = new BitSet();
                            ArrayMap arrayMap5 = new ArrayMap();
                            if (zzgiVar != null && zzgiVar.zza() != 0) {
                                for (com.google.android.gms.internal.measurement.zzfr zzfrVar : zzgiVar.zzh()) {
                                    if (zzfrVar.zzh()) {
                                        arrayMap5.put(Integer.valueOf(zzfrVar.zza()), zzfrVar.zzg() ? Long.valueOf(zzfrVar.zzb()) : null);
                                    }
                                }
                            }
                            ArrayMap arrayMap6 = new ArrayMap();
                            if (zzgiVar != null && zzgiVar.zzc() != 0) {
                                for (com.google.android.gms.internal.measurement.zzgk zzgkVar : zzgiVar.zzj()) {
                                    if (zzgkVar.zzi() && zzgkVar.zza() > 0) {
                                        arrayMap6.put(Integer.valueOf(zzgkVar.zzb()), Long.valueOf(zzgkVar.zzc(zzgkVar.zza() - 1)));
                                        map3 = map3;
                                    }
                                }
                            }
                            Map map5 = map3;
                            if (zzgiVar != null) {
                                int i5 = 0;
                                while (i5 < zzgiVar.zzd() * 64) {
                                    if (zzlj.zzw(zzgiVar.zzk(), i5)) {
                                        str7 = str13;
                                        this.zzt.zzaA().zzj().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i5));
                                        bitSet2.set(i5);
                                        if (zzlj.zzw(zzgiVar.zzi(), i5)) {
                                            bitSet.set(i5);
                                            i5++;
                                            str13 = str7;
                                        }
                                    } else {
                                        str7 = str13;
                                    }
                                    arrayMap5.remove(Integer.valueOf(i5));
                                    i5++;
                                    str13 = str7;
                                }
                            }
                            String str24 = str13;
                            Integer valueOf3 = Integer.valueOf(intValue);
                            com.google.android.gms.internal.measurement.zzgi zzgiVar2 = (com.google.android.gms.internal.measurement.zzgi) map2.get(valueOf3);
                            if (zzs2 && zzs && (list3 = (List) map.get(valueOf3)) != null && this.zze != null && this.zzd != null) {
                                for (com.google.android.gms.internal.measurement.zzek zzekVar2 : list3) {
                                    int zzb = zzekVar2.zzb();
                                    long longValue = this.zze.longValue() / 1000;
                                    if (zzekVar2.zzm()) {
                                        longValue = this.zzd.longValue() / 1000;
                                    }
                                    Integer valueOf4 = Integer.valueOf(zzb);
                                    if (arrayMap5.containsKey(valueOf4)) {
                                        arrayMap5.put(valueOf4, Long.valueOf(longValue));
                                    }
                                    if (arrayMap6.containsKey(valueOf4)) {
                                        arrayMap6.put(valueOf4, Long.valueOf(longValue));
                                    }
                                }
                            }
                            this.zzc.put(Integer.valueOf(intValue), new zzu(this, this.zza, zzgiVar2, bitSet, bitSet2, arrayMap5, arrayMap6, null));
                            str13 = str24;
                            map = map;
                            map3 = map5;
                            map2 = map2;
                        }
                        str5 = str13;
                        str6 = str4;
                    } else {
                        str6 = "Database error querying filters. appId";
                        str5 = "Failed to merge filter. appId";
                    }
                    String str1822 = str2;
                    String str1922 = str3;
                    if (!list.isEmpty()) {
                        zzw zzwVar2 = new zzw(this, null);
                        ArrayMap arrayMap7 = new ArrayMap();
                        Iterator it6 = list.iterator();
                        while (it6.hasNext()) {
                            com.google.android.gms.internal.measurement.zzft zzftVar = (com.google.android.gms.internal.measurement.zzft) it6.next();
                            com.google.android.gms.internal.measurement.zzft zza = zzwVar2.zza(this.zza, zzftVar);
                            if (zza != null) {
                                zzak zzh6 = this.zzf.zzh();
                                String str25 = this.zza;
                                String zzh7 = zza.zzh();
                                zzaq zzn = zzh6.zzn(str25, zzftVar.zzh());
                                if (zzn == null) {
                                    zzh6.zzt.zzaA().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzet.zzn(str25), zzh6.zzt.zzj().zzd(zzh7));
                                    zzaqVar = new zzaq(str25, zzftVar.zzh(), 1L, 1L, 1L, zzftVar.zzd(), 0L, null, null, null, null);
                                } else {
                                    zzaqVar = new zzaq(zzn.zza, zzn.zzb, zzn.zzc + 1, zzn.zzd + 1, zzn.zze + 1, zzn.zzf, zzn.zzg, zzn.zzh, zzn.zzi, zzn.zzj, zzn.zzk);
                                }
                                this.zzf.zzh().zzE(zzaqVar);
                                long j = zzaqVar.zzc;
                                String zzh8 = zza.zzh();
                                Map map6 = (Map) arrayMap7.get(zzh8);
                                if (map6 == null) {
                                    zzak zzh9 = this.zzf.zzh();
                                    String str26 = this.zza;
                                    zzh9.zzW();
                                    zzh9.zzg();
                                    Preconditions.checkNotEmpty(str26);
                                    Preconditions.checkNotEmpty(zzh8);
                                    zzwVar = zzwVar2;
                                    ArrayMap arrayMap8 = new ArrayMap();
                                    it = it6;
                                    str8 = str11;
                                    String str27 = str1822;
                                    String str28 = str1922;
                                    try {
                                        try {
                                            str1922 = str28;
                                        } catch (Throwable th5) {
                                            th = th5;
                                            cursor4 = null;
                                        }
                                    } catch (SQLiteException e10) {
                                        e = e10;
                                        str1922 = str28;
                                    }
                                    try {
                                        cursor4 = zzh9.zzh().query("event_filters", new String[]{str27, str28}, "app_id=? AND event_name=?", new String[]{str26, zzh8}, null, null, null);
                                        try {
                                            try {
                                                if (cursor4.moveToFirst()) {
                                                    str1822 = str27;
                                                    while (true) {
                                                        try {
                                                            try {
                                                                com.google.android.gms.internal.measurement.zzek zzekVar3 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursor4.getBlob(1))).zzaD();
                                                                Integer valueOf5 = Integer.valueOf(cursor4.getInt(0));
                                                                List list9 = (List) arrayMap8.get(valueOf5);
                                                                if (list9 == null) {
                                                                    zzaqVar2 = zzaqVar;
                                                                    try {
                                                                        list4 = new ArrayList();
                                                                        arrayMap8.put(valueOf5, list4);
                                                                    } catch (SQLiteException e11) {
                                                                        e = e11;
                                                                        zzh9.zzt.zzaA().zzd().zzc(str6, zzet.zzn(str26), e);
                                                                        map6 = Collections.emptyMap();
                                                                    }
                                                                } else {
                                                                    zzaqVar2 = zzaqVar;
                                                                    list4 = list9;
                                                                }
                                                                list4.add(zzekVar3);
                                                            } catch (IOException e12) {
                                                                zzaqVar2 = zzaqVar;
                                                                zzh9.zzt.zzaA().zzd().zzc(str5, zzet.zzn(str26), e12);
                                                            }
                                                            if (!cursor4.moveToNext()) {
                                                                break;
                                                            }
                                                            zzaqVar = zzaqVar2;
                                                        } catch (SQLiteException e13) {
                                                            e = e13;
                                                            zzaqVar2 = zzaqVar;
                                                        }
                                                    }
                                                    if (cursor4 != null) {
                                                        cursor4.close();
                                                    }
                                                    map6 = arrayMap8;
                                                } else {
                                                    zzaqVar2 = zzaqVar;
                                                    str1822 = str27;
                                                    map6 = Collections.emptyMap();
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                if (cursor4 != null) {
                                                    cursor4.close();
                                                }
                                                throw th;
                                            }
                                        } catch (SQLiteException e14) {
                                            e = e14;
                                            zzaqVar2 = zzaqVar;
                                            str1822 = str27;
                                        }
                                    } catch (SQLiteException e15) {
                                        e = e15;
                                        zzaqVar2 = zzaqVar;
                                        str1822 = str27;
                                        cursor4 = null;
                                        zzh9.zzt.zzaA().zzd().zzc(str6, zzet.zzn(str26), e);
                                        map6 = Collections.emptyMap();
                                    }
                                    arrayMap7.put(zzh8, map6);
                                } else {
                                    zzwVar = zzwVar2;
                                    it = it6;
                                    zzaqVar2 = zzaqVar;
                                    str8 = str11;
                                }
                                Iterator it7 = map6.keySet().iterator();
                                while (it7.hasNext()) {
                                    int intValue2 = ((Integer) it7.next()).intValue();
                                    Set set = this.zzb;
                                    Integer valueOf6 = Integer.valueOf(intValue2);
                                    if (set.contains(valueOf6)) {
                                        this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", valueOf6);
                                    } else {
                                        Iterator it8 = ((List) map6.get(valueOf6)).iterator();
                                        boolean z2 = true;
                                        while (true) {
                                            if (!it8.hasNext()) {
                                                break;
                                            }
                                            com.google.android.gms.internal.measurement.zzek zzekVar4 = (com.google.android.gms.internal.measurement.zzek) it8.next();
                                            zzx zzxVar = new zzx(this, this.zza, intValue2, zzekVar4);
                                            z2 = zzxVar.zzd(this.zzd, this.zze, zza, j, zzaqVar2, zzf(intValue2, zzekVar4.zzb()));
                                            if (!z2) {
                                                this.zzb.add(Integer.valueOf(intValue2));
                                                break;
                                            }
                                            zzd(Integer.valueOf(intValue2)).zzc(zzxVar);
                                        }
                                        if (!z2) {
                                            this.zzb.add(Integer.valueOf(intValue2));
                                        }
                                    }
                                }
                                zzwVar2 = zzwVar;
                                it6 = it;
                                str11 = str8;
                            }
                        }
                    }
                    String str2022 = str11;
                    if (!list2.isEmpty()) {
                        ArrayMap arrayMap9 = new ArrayMap();
                        Iterator it9 = list2.iterator();
                        while (it9.hasNext()) {
                            com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) it9.next();
                            String zzf = zzgmVar.zzf();
                            Map map7 = (Map) arrayMap9.get(zzf);
                            if (map7 == null) {
                                zzak zzh10 = this.zzf.zzh();
                                String str29 = this.zza;
                                zzh10.zzW();
                                zzh10.zzg();
                                Preconditions.checkNotEmpty(str29);
                                Preconditions.checkNotEmpty(zzf);
                                ArrayMap arrayMap10 = new ArrayMap();
                                str9 = str1822;
                                str10 = str1922;
                                try {
                                    try {
                                        it2 = it9;
                                        try {
                                            cursor6 = zzh10.zzh().query("property_filters", new String[]{str9, str10}, "app_id=? AND property_name=?", new String[]{str29, zzf}, null, null, null);
                                            try {
                                                try {
                                                    if (!cursor6.moveToFirst()) {
                                                        map7 = Collections.emptyMap();
                                                    }
                                                    do {
                                                        try {
                                                            com.google.android.gms.internal.measurement.zzet zzetVar = (com.google.android.gms.internal.measurement.zzet) ((com.google.android.gms.internal.measurement.zzes) zzlj.zzm(com.google.android.gms.internal.measurement.zzet.zzc(), cursor6.getBlob(1))).zzaD();
                                                            Integer valueOf7 = Integer.valueOf(cursor6.getInt(0));
                                                            List list10 = (List) arrayMap10.get(valueOf7);
                                                            if (list10 == null) {
                                                                list5 = new ArrayList();
                                                                arrayMap10.put(valueOf7, list5);
                                                            } else {
                                                                list5 = list10;
                                                            }
                                                            list5.add(zzetVar);
                                                        } catch (IOException e16) {
                                                            zzh10.zzt.zzaA().zzd().zzc("Failed to merge filter", zzet.zzn(str29), e16);
                                                        }
                                                    } while (cursor6.moveToNext());
                                                    if (cursor6 != null) {
                                                        cursor6.close();
                                                    }
                                                    map7 = arrayMap10;
                                                } catch (Throwable th7) {
                                                    th = th7;
                                                    cursor5 = cursor6;
                                                    if (cursor5 != null) {
                                                        cursor5.close();
                                                    }
                                                    throw th;
                                                }
                                            } catch (SQLiteException e17) {
                                                e = e17;
                                                zzh10.zzt.zzaA().zzd().zzc(str6, zzet.zzn(str29), e);
                                                map7 = Collections.emptyMap();
                                            }
                                        } catch (SQLiteException e18) {
                                            e = e18;
                                            cursor6 = null;
                                            zzh10.zzt.zzaA().zzd().zzc(str6, zzet.zzn(str29), e);
                                            map7 = Collections.emptyMap();
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        cursor5 = null;
                                    }
                                } catch (SQLiteException e19) {
                                    e = e19;
                                    it2 = it9;
                                }
                                arrayMap9.put(zzf, map7);
                            } else {
                                it2 = it9;
                                str9 = str1822;
                                str10 = str1922;
                            }
                            Iterator it10 = map7.keySet().iterator();
                            while (true) {
                                if (it10.hasNext()) {
                                    int intValue3 = ((Integer) it10.next()).intValue();
                                    Set set2 = this.zzb;
                                    Integer valueOf8 = Integer.valueOf(intValue3);
                                    if (set2.contains(valueOf8)) {
                                        this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", valueOf8);
                                        break;
                                    }
                                    Iterator it11 = ((List) map7.get(valueOf8)).iterator();
                                    boolean z3 = true;
                                    while (true) {
                                        if (!it11.hasNext()) {
                                            map4 = map7;
                                            break;
                                        }
                                        com.google.android.gms.internal.measurement.zzet zzetVar2 = (com.google.android.gms.internal.measurement.zzet) it11.next();
                                        if (Log.isLoggable(this.zzt.zzaA().zzr(), 2)) {
                                            map4 = map7;
                                            this.zzt.zzaA().zzj().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue3), zzetVar2.zzj() ? Integer.valueOf(zzetVar2.zza()) : null, this.zzt.zzj().zzf(zzetVar2.zze()));
                                            this.zzt.zzaA().zzj().zzb("Filter definition", this.zzf.zzu().zzq(zzetVar2));
                                        } else {
                                            map4 = map7;
                                        }
                                        if (!zzetVar2.zzj() || zzetVar2.zza() > 256) {
                                            break;
                                        }
                                        zzz zzzVar = new zzz(this, this.zza, intValue3, zzetVar2);
                                        z3 = zzzVar.zzd(this.zzd, this.zze, zzgmVar, zzf(intValue3, zzetVar2.zza()));
                                        if (!z3) {
                                            this.zzb.add(Integer.valueOf(intValue3));
                                            break;
                                        }
                                        zzd(Integer.valueOf(intValue3)).zzc(zzzVar);
                                        map7 = map4;
                                    }
                                    if (z3) {
                                        map7 = map4;
                                    }
                                    this.zzb.add(Integer.valueOf(intValue3));
                                    map7 = map4;
                                }
                            }
                            it9 = it2;
                            str1922 = str10;
                            str1822 = str9;
                        }
                    }
                    String str2122 = str1822;
                    ArrayList arrayList22 = new ArrayList();
                    Set keySet22 = this.zzc.keySet();
                    keySet22.removeAll(this.zzb);
                    it3 = keySet22.iterator();
                    while (it3.hasNext()) {
                        int intValue4 = ((Integer) it3.next()).intValue();
                        Map map8 = this.zzc;
                        Integer valueOf9 = Integer.valueOf(intValue4);
                        zzu zzuVar = (zzu) map8.get(valueOf9);
                        Preconditions.checkNotNull(zzuVar);
                        com.google.android.gms.internal.measurement.zzfp zza2 = zzuVar.zza(intValue4);
                        arrayList22.add(zza2);
                        zzak zzh11 = this.zzf.zzh();
                        String str30 = this.zza;
                        com.google.android.gms.internal.measurement.zzgi zzd = zza2.zzd();
                        zzh11.zzW();
                        zzh11.zzg();
                        Preconditions.checkNotEmpty(str30);
                        Preconditions.checkNotNull(zzd);
                        byte[] zzbx = zzd.zzbx();
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("app_id", str30);
                        contentValues2.put(str2122, valueOf9);
                        String str31 = str2022;
                        contentValues2.put(str31, zzbx);
                        try {
                            try {
                                if (zzh11.zzh().insertWithOnConflict("audience_filter_values", null, contentValues2, 5) == -1) {
                                    zzh11.zzt.zzaA().zzd().zzb("Failed to insert filter results (got -1). appId", zzet.zzn(str30));
                                }
                            } catch (SQLiteException e20) {
                                e = e20;
                                zzh11.zzt.zzaA().zzd().zzc("Error storing filter results. appId", zzet.zzn(str30), e);
                                str2022 = str31;
                            }
                        } catch (SQLiteException e21) {
                            e = e21;
                        }
                        str2022 = str31;
                    }
                    return arrayList22;
                }
                emptyMap = Collections.emptyMap();
            }
            if (cursor2.moveToFirst()) {
            }
            if (map2.isEmpty()) {
            }
            String str18222 = str2;
            String str19222 = str3;
            if (!list.isEmpty()) {
            }
            String str20222 = str11;
            if (!list2.isEmpty()) {
            }
            String str21222 = str18222;
            ArrayList arrayList222 = new ArrayList();
            Set keySet222 = this.zzc.keySet();
            keySet222.removeAll(this.zzb);
            it3 = keySet222.iterator();
            while (it3.hasNext()) {
            }
            return arrayList222;
        } catch (Throwable th9) {
            th = th9;
            Cursor cursor8 = cursor2;
            if (cursor8 != null) {
                cursor8.close();
            }
            throw th;
        }
        map = emptyMap;
        zzak zzh32 = this.zzf.zzh();
        String str172 = this.zza;
        zzh32.zzW();
        zzh32.zzg();
        Preconditions.checkNotEmpty(str172);
        cursor2 = zzh32.zzh().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str172}, null, null, null);
    }

    @Override // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }
}
