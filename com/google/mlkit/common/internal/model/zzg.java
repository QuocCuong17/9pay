package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzif;
import com.google.android.gms.internal.mlkit_common.zzip;
import com.google.android.gms.internal.mlkit_common.zziz;
import com.google.android.gms.internal.mlkit_common.zzja;
import com.google.android.gms.internal.mlkit_common.zzji;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.internal.mlkit_common.zznb;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import java.util.Set;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
public final class zzg implements RemoteModelManagerInterface {
    private final MlKitContext zza;
    private final zzmq zzb;

    public zzg(MlKitContext mlKitContext) {
        zzmq zzb = zznb.zzb("common");
        this.zza = mlKitContext;
        this.zzb = zzb;
    }

    private final RemoteModelDownloadManager zze(CustomRemoteModel customRemoteModel) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.zza, customRemoteModel, null, new ModelFileHelper(this.zza), new zza(this.zza, customRemoteModel.getUniqueModelNameForPersist()));
        MlKitContext mlKitContext = this.zza;
        return RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task deleteDownloadedModel(RemoteModel remoteModel) {
        final CustomRemoteModel customRemoteModel = (CustomRemoteModel) remoteModel;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.mlkit.common.internal.model.zzc
            @Override // java.lang.Runnable
            public final void run() {
                zzg.this.zzb(customRemoteModel, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.common.internal.model.zzd
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzg.this.zzc(task);
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task download(RemoteModel remoteModel, DownloadConditions downloadConditions) {
        final RemoteModelDownloadManager zze = zze((CustomRemoteModel) remoteModel);
        zze.setDownloadConditions(downloadConditions);
        return Tasks.forResult(null).onSuccessTask(MLTaskExecutor.workerThreadExecutor(), new SuccessContinuation() { // from class: com.google.mlkit.common.internal.model.zzb
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return RemoteModelDownloadManager.this.ensureModelDownloaded();
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final Task<Set<CustomRemoteModel>> getDownloadedModels() {
        return Tasks.forException(new MlKitException("Custom Remote model does not support listing downloaded models", 12));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task isModelDownloaded(RemoteModel remoteModel) {
        final CustomRemoteModel customRemoteModel = (CustomRemoteModel) remoteModel;
        return MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.mlkit.common.internal.model.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzg.this.zza(customRemoteModel);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.common.internal.model.zzf
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzg.this.zzd(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zza(CustomRemoteModel customRemoteModel) throws Exception {
        return Boolean.valueOf(zze(customRemoteModel).isModelDownloadedAndValid());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        try {
            new ModelFileHelper(this.zza).deleteAllModels(ModelType.CUSTOM, (String) Preconditions.checkNotNull(customRemoteModel.getModelName()));
            taskCompletionSource.setResult(null);
        } catch (RuntimeException e) {
            taskCompletionSource.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Task task) {
        boolean isSuccessful = task.isSuccessful();
        zzmq zzmqVar = this.zzb;
        zzja zzjaVar = new zzja();
        zzif zzifVar = new zzif();
        zzifVar.zzb(zzji.CUSTOM);
        zzifVar.zza(Boolean.valueOf(isSuccessful));
        zzjaVar.zze(zzifVar.zzc());
        zzmqVar.zzd(zzmt.zzf(zzjaVar), zziz.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Task task) {
        boolean booleanValue = ((Boolean) task.getResult()).booleanValue();
        zzmq zzmqVar = this.zzb;
        zzja zzjaVar = new zzja();
        zzip zzipVar = new zzip();
        zzipVar.zzb(zzji.CUSTOM);
        zzipVar.zza(Boolean.valueOf(booleanValue));
        zzjaVar.zzg(zzipVar.zzc());
        zzmqVar.zzd(zzmt.zzf(zzjaVar), zziz.REMOTE_MODEL_IS_DOWNLOADED);
    }
}
