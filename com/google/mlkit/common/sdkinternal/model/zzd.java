package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zziy;
import com.google.android.gms.internal.mlkit_common.zzje;
import com.google.android.gms.internal.mlkit_common.zzmh;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zzmr;
import com.google.android.gms.internal.mlkit_common.zzms;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
public final class zzd extends BroadcastReceiver {
    final /* synthetic */ RemoteModelDownloadManager zza;
    private final long zzb;
    private final TaskCompletionSource zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzd(RemoteModelDownloadManager remoteModelDownloadManager, long j, TaskCompletionSource taskCompletionSource, zzc zzcVar) {
        this.zza = remoteModelDownloadManager;
        this.zzb = j;
        this.zzc = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        GmsLogger gmsLogger;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        zzmq zzmqVar;
        RemoteModel remoteModel;
        zzmq zzmqVar2;
        RemoteModel remoteModel2;
        RemoteModel remoteModel3;
        zzmq zzmqVar3;
        RemoteModel remoteModel4;
        MlKitException zzl;
        MlKitContext mlKitContext;
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.zzb) {
            return;
        }
        Integer downloadingModelStatusCode = this.zza.getDownloadingModelStatusCode();
        synchronized (this.zza) {
            try {
                mlKitContext = this.zza.zze;
                mlKitContext.getApplicationContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                gmsLogger = RemoteModelDownloadManager.zza;
                gmsLogger.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e);
            }
            longSparseArray = this.zza.zzc;
            longSparseArray.remove(this.zzb);
            longSparseArray2 = this.zza.zzd;
            longSparseArray2.remove(this.zzb);
        }
        if (downloadingModelStatusCode != null) {
            if (downloadingModelStatusCode.intValue() == 16) {
                zzmqVar3 = this.zza.zzi;
                zzmh zzg = zzmt.zzg();
                RemoteModelDownloadManager remoteModelDownloadManager = this.zza;
                remoteModel4 = remoteModelDownloadManager.zzg;
                Long valueOf = Long.valueOf(longExtra);
                zzmqVar3.zze(zzg, remoteModel4, false, remoteModelDownloadManager.getFailureReason(valueOf));
                TaskCompletionSource taskCompletionSource = this.zzc;
                zzl = this.zza.zzl(valueOf);
                taskCompletionSource.setException(zzl);
                return;
            }
            if (downloadingModelStatusCode.intValue() == 8) {
                zzmqVar2 = this.zza.zzi;
                zzmh zzg2 = zzmt.zzg();
                remoteModel2 = this.zza.zzg;
                zzmr zzh = zzms.zzh();
                zzh.zzb(zziy.NO_ERROR);
                zzh.zze(true);
                remoteModel3 = this.zza.zzg;
                zzh.zzd(remoteModel3.getModelType());
                zzh.zza(zzje.SUCCEEDED);
                zzmqVar2.zzg(zzg2, remoteModel2, zzh.zzh());
                this.zzc.setResult(null);
                return;
            }
        }
        zzmqVar = this.zza.zzi;
        zzmh zzg3 = zzmt.zzg();
        remoteModel = this.zza.zzg;
        zzmqVar.zze(zzg3, remoteModel, false, 0);
        this.zzc.setException(new MlKitException("Model downloading failed", 13));
    }
}
