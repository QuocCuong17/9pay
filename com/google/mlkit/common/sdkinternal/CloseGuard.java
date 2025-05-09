package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.internal.mlkit_common.zziu;
import com.google.android.gms.internal.mlkit_common.zziv;
import com.google.android.gms.internal.mlkit_common.zziz;
import com.google.android.gms.internal.mlkit_common.zzja;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.internal.mlkit_common.zznb;
import com.google.mlkit.common.sdkinternal.Cleaner;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
public class CloseGuard implements Closeable {
    public static final int API_TRANSLATE = 1;
    private final AtomicBoolean zza = new AtomicBoolean();
    private final String zzb;
    private final Cleaner.Cleanable zzc;

    /* compiled from: com.google.mlkit:common@@18.5.0 */
    /* loaded from: classes4.dex */
    public static class Factory {
        private final Cleaner zza;

        public Factory(Cleaner cleaner) {
            this.zza = cleaner;
        }

        public CloseGuard create(Object obj, int i, Runnable runnable) {
            return new CloseGuard(obj, i, this.zza, runnable, zznb.zzb("common"));
        }
    }

    CloseGuard(Object obj, final int i, Cleaner cleaner, final Runnable runnable, final zzmq zzmqVar) {
        this.zzb = obj.toString();
        this.zzc = cleaner.register(obj, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zze
            @Override // java.lang.Runnable
            public final void run() {
                CloseGuard.this.zza(i, zzmqVar, runnable);
            }
        });
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.zza.set(true);
        this.zzc.clean();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzmq zzmqVar, Runnable runnable) {
        if (!this.zza.get()) {
            Log.e("MlKitCloseGuard", String.format(Locale.ENGLISH, "%s has not been closed", this.zzb));
            zzja zzjaVar = new zzja();
            zziv zzivVar = new zziv();
            zzivVar.zzb(zziu.zzb(i));
            zzjaVar.zzh(zzivVar.zzc());
            zzmqVar.zzd(zzmt.zzf(zzjaVar), zziz.HANDLE_LEAKED);
        }
        runnable.run();
    }
}
