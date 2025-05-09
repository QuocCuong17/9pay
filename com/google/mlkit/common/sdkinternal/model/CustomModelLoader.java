package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.internal.mlkit_common.zznb;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
public class CustomModelLoader {
    private static final GmsLogger zza = new GmsLogger("CustomModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final LocalModel zzd;
    private final CustomRemoteModel zze;
    private final RemoteModelDownloadManager zzf;
    private final RemoteModelFileManager zzg;
    private final zzmq zzh;
    private boolean zzi;

    /* compiled from: com.google.mlkit:common@@18.5.0 */
    /* loaded from: classes4.dex */
    public interface CustomModelLoaderHelper {
        void logLoad() throws MlKitException;

        boolean tryLoad(LocalModel localModel) throws MlKitException;
    }

    private CustomModelLoader(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        if (customRemoteModel != null) {
            RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, customRemoteModel, null, new ModelFileHelper(mlKitContext), new com.google.mlkit.common.internal.model.zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.zzg = remoteModelFileManager;
            this.zzf = RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
            this.zzi = true;
        } else {
            this.zzg = null;
            this.zzf = null;
        }
        this.zzc = mlKitContext;
        this.zzd = localModel;
        this.zze = customRemoteModel;
        this.zzh = zznb.zzb("common");
    }

    public static synchronized CustomModelLoader getInstance(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        String uniqueModelNameForPersist;
        CustomModelLoader customModelLoader;
        synchronized (CustomModelLoader.class) {
            if (customRemoteModel == null) {
                uniqueModelNameForPersist = ((LocalModel) Preconditions.checkNotNull(localModel)).toString();
            } else {
                uniqueModelNameForPersist = customRemoteModel.getUniqueModelNameForPersist();
            }
            Map map = zzb;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new CustomModelLoader(mlKitContext, localModel, customRemoteModel));
            }
            customModelLoader = (CustomModelLoader) map.get(uniqueModelNameForPersist);
        }
        return customModelLoader;
    }

    private final File zza() throws MlKitException {
        String zzb2 = ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzb();
        if (zzb2 == null) {
            zza.d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(zzb2);
        File[] listFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(listFiles)).length == 1 ? listFiles[0] : file;
    }

    private final void zzb() throws MlKitException {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).removeOrCancelDownload();
    }

    private static final LocalModel zzc(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
            return builder.build();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.setAbsoluteFilePath(file.getAbsolutePath());
        return builder2.build();
    }

    public synchronized LocalModel createLocalModelByLatestExistingModel() throws MlKitException {
        zza.d("CustomModelLoader", "Try to get the latest existing model file.");
        File zza2 = zza();
        if (zza2 == null) {
            return null;
        }
        return zzc(zza2);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009b A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x009d A[Catch: all -> 0x00a3, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0022, B:9:0x002a, B:15:0x009d, B:19:0x002e, B:21:0x0048, B:24:0x0051, B:25:0x006a, B:27:0x0072, B:28:0x008e), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized LocalModel createLocalModelByNewlyDownloadedModel() throws MlKitException {
        File file;
        GmsLogger gmsLogger = zza;
        gmsLogger.d("CustomModelLoader", "Try to get newly downloaded model file.");
        Long downloadingId = ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).getDownloadingId();
        String downloadingModelHash = this.zzf.getDownloadingModelHash();
        if (downloadingId != null && downloadingModelHash != null) {
            Integer downloadingModelStatusCode = this.zzf.getDownloadingModelStatusCode();
            if (downloadingModelStatusCode == null) {
                zzb();
            } else {
                Objects.toString(downloadingModelStatusCode);
                gmsLogger.d("CustomModelLoader", "Download Status code: ".concat(downloadingModelStatusCode.toString()));
                if (downloadingModelStatusCode.intValue() == 8) {
                    file = this.zzf.zzi(downloadingModelHash);
                    if (file != null) {
                        gmsLogger.d("CustomModelLoader", "Moved the downloaded model to private folder successfully: ".concat(String.valueOf(file.getParent())));
                        this.zzf.updateLatestModelHashAndType(downloadingModelHash);
                        if (file == null) {
                            return null;
                        }
                        return zzc(file);
                    }
                } else if (downloadingModelStatusCode.intValue() == 16) {
                    this.zzh.zze(zzmt.zzg(), (RemoteModel) Preconditions.checkNotNull(this.zze), false, this.zzf.getFailureReason(downloadingId));
                    zzb();
                }
            }
            file = null;
            if (file == null) {
            }
        }
        gmsLogger.d("CustomModelLoader", "No new model is downloading.");
        zzb();
        file = null;
        if (file == null) {
        }
    }

    public void deleteLatestExistingModel() throws MlKitException {
        File zza2 = zza();
        if (zza2 != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzc(zza2);
            SharedPrefManager.getInstance(this.zzc).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.zze));
        }
    }

    public void deleteOldModels(LocalModel localModel) throws MlKitException {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            zza.d("CustomModelLoader", "All old models are deleted.");
            this.zzg.zza(parentFile);
        } else {
            zza.e("CustomModelLoader", "Failed to delete old models");
        }
    }

    public synchronized void load(CustomModelLoaderHelper customModelLoaderHelper) throws MlKitException {
        LocalModel localModel = this.zzd;
        if (localModel == null) {
            localModel = createLocalModelByNewlyDownloadedModel();
        }
        if (localModel == null) {
            localModel = createLocalModelByLatestExistingModel();
        }
        if (localModel == null) {
            throw new MlKitException("Model is not available.", 14);
        }
        while (!customModelLoaderHelper.tryLoad(localModel)) {
            if (this.zze != null) {
                deleteLatestExistingModel();
                localModel = createLocalModelByLatestExistingModel();
            } else {
                localModel = null;
            }
            if (localModel == null) {
                customModelLoaderHelper.logLoad();
                return;
            }
        }
        if (this.zze != null && this.zzi) {
            deleteOldModels((LocalModel) Preconditions.checkNotNull(localModel));
            this.zzi = false;
        }
        customModelLoaderHelper.logLoad();
    }
}
