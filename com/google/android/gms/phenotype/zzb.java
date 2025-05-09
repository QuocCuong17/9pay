package com.google.android.gms.phenotype;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes3.dex */
final class zzb extends ContentObserver {
    private final /* synthetic */ zza zzm;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzb(zza zzaVar, Handler handler) {
        super(null);
        this.zzm = zzaVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zzm.zzb();
    }
}
