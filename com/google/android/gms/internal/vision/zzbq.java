package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzbq implements zzay {
    private static final Map<String, zzbq> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc;
    private final Object zzd;
    private volatile Map<String, ?> zze;
    private final List<zzaz> zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbq zza(Context context, String str) {
        zzbq zzbqVar;
        if (!((!zzas.zza() || str.startsWith("direct_boot:")) ? true : zzas.zza(context))) {
            return null;
        }
        synchronized (zzbq.class) {
            Map<String, zzbq> map = zza;
            zzbqVar = map.get(str);
            if (zzbqVar == null) {
                zzbqVar = new zzbq(zzb(context, str));
                map.put(str, zzbqVar);
            }
        }
        return zzbqVar;
    }

    private static SharedPreferences zzb(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzas.zza()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return context.getSharedPreferences(str.substring(12), 0);
            }
            return context.getSharedPreferences(str, 0);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private zzbq(SharedPreferences sharedPreferences) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener(this) { // from class: com.google.android.gms.internal.vision.zzbt
            private final zzbq zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                this.zza.zza(sharedPreferences2, str);
            }
        };
        this.zzc = onSharedPreferenceChangeListener;
        this.zzd = new Object();
        this.zzf = new ArrayList();
        this.zzb = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // com.google.android.gms.internal.vision.zzay
    public final Object zza(String str) {
        Map<String, ?> map = this.zze;
        if (map == null) {
            synchronized (this.zzd) {
                map = this.zze;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzb.getAll();
                        this.zze = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zza() {
        synchronized (zzbq.class) {
            for (zzbq zzbqVar : zza.values()) {
                zzbqVar.zzb.unregisterOnSharedPreferenceChangeListener(zzbqVar.zzc);
            }
            zza.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzd) {
            this.zze = null;
            zzbi.zza();
        }
        synchronized (this) {
            Iterator<zzaz> it = this.zzf.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }
}
