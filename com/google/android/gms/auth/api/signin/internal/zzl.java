package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes3.dex */
final class zzl extends zzc {
    private final /* synthetic */ zzk zzbl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(zzk zzkVar) {
        this.zzbl = zzkVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzc, com.google.android.gms.auth.api.signin.internal.zzs
    public final void zze(Status status) throws RemoteException {
        this.zzbl.setResult((zzk) status);
    }
}
