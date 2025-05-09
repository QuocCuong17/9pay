package com.google.android.gms.internal.p001authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
/* loaded from: classes3.dex */
final class zzs extends zzg {
    private final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(zzn zznVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p001authapiphone.zzh
    public final void zza(Status status, boolean z) {
        TaskUtil.setResultOrApiException(status, Boolean.valueOf(z), this.zza);
    }
}
