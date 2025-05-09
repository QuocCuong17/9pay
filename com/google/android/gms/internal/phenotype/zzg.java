package com.google.android.gms.internal.phenotype;

import android.database.ContentObserver;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class zzg extends ContentObserver {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        AtomicBoolean atomicBoolean;
        atomicBoolean = zzf.zzbh;
        atomicBoolean.set(true);
    }
}
