package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes3.dex */
final class zzn extends zzc {
    private final /* synthetic */ zzm zzbm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzm zzmVar) {
        this.zzbm = zzmVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzc, com.google.android.gms.auth.api.signin.internal.zzs
    public final void zzf(Status status) throws RemoteException {
        this.zzbm.setResult((zzm) status);
    }
}
