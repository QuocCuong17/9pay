package io.flutter.plugins.firebase.storage;

import android.net.Uri;
import android.util.Base64;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.database.Constants;
import io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/* loaded from: classes5.dex */
public class FlutterFirebaseStoragePlugin implements FlutterFirebasePlugin, FlutterPlugin, GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String DEFAULT_ERROR_CODE = "firebase_storage";
    static final String STORAGE_METHOD_CHANNEL_NAME = "plugins.flutter.io/firebase_storage";
    static final String STORAGE_TASK_EVENT_NAME = "taskEvent";
    private MethodChannel channel;
    private BinaryMessenger messenger;
    private final Map<String, EventChannel> eventChannels = new HashMap();
    private final Map<String, EventChannel.StreamHandler> streamHandlers = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> getExceptionDetails(Exception exc) {
        HashMap hashMap = new HashMap();
        GeneratedAndroidFirebaseStorage.FlutterError parserExceptionToFlutter = FlutterFirebaseStorageException.parserExceptionToFlutter(exc);
        if (parserExceptionToFlutter != null) {
            hashMap.put("code", parserExceptionToFlutter.code);
            hashMap.put("message", parserExceptionToFlutter.getMessage());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> parseMetadataToMap(StorageMetadata storageMetadata) {
        if (storageMetadata == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        if (storageMetadata.getName() != null) {
            hashMap.put("name", storageMetadata.getName());
        }
        if (storageMetadata.getBucket() != null) {
            hashMap.put("bucket", storageMetadata.getBucket());
        }
        if (storageMetadata.getGeneration() != null) {
            hashMap.put("generation", storageMetadata.getGeneration());
        }
        if (storageMetadata.getMetadataGeneration() != null) {
            hashMap.put("metadataGeneration", storageMetadata.getMetadataGeneration());
        }
        hashMap.put("fullPath", storageMetadata.getPath());
        hashMap.put("size", Long.valueOf(storageMetadata.getSizeBytes()));
        hashMap.put("creationTimeMillis", Long.valueOf(storageMetadata.getCreationTimeMillis()));
        hashMap.put("updatedTimeMillis", Long.valueOf(storageMetadata.getUpdatedTimeMillis()));
        if (storageMetadata.getMd5Hash() != null) {
            hashMap.put("md5Hash", storageMetadata.getMd5Hash());
        }
        if (storageMetadata.getCacheControl() != null) {
            hashMap.put("cacheControl", storageMetadata.getCacheControl());
        }
        if (storageMetadata.getContentDisposition() != null) {
            hashMap.put("contentDisposition", storageMetadata.getContentDisposition());
        }
        if (storageMetadata.getContentEncoding() != null) {
            hashMap.put("contentEncoding", storageMetadata.getContentEncoding());
        }
        if (storageMetadata.getContentLanguage() != null) {
            hashMap.put("contentLanguage", storageMetadata.getContentLanguage());
        }
        if (storageMetadata.getContentType() != null) {
            hashMap.put("contentType", storageMetadata.getContentType());
        }
        HashMap hashMap2 = new HashMap();
        for (String str : storageMetadata.getCustomMetadataKeys()) {
            if (storageMetadata.getCustomMetadata(str) == null) {
                hashMap2.put(str, "");
            } else {
                String customMetadata = storageMetadata.getCustomMetadata(str);
                Objects.requireNonNull(customMetadata);
                hashMap2.put(str, customMetadata);
            }
        }
        hashMap.put("customMetadata", hashMap2);
        return hashMap;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        FlutterFirebaseStorageTask.cancelInProgressTasks();
        this.channel.setMethodCallHandler(null);
        GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi.CC.setup(this.messenger, null);
        this.channel = null;
        this.messenger = null;
        removeEventListeners();
    }

    private void initInstance(BinaryMessenger binaryMessenger) {
        FlutterFirebasePluginRegistry.registerPlugin(STORAGE_METHOD_CHANNEL_NAME, this);
        this.channel = new MethodChannel(binaryMessenger, STORAGE_METHOD_CHANNEL_NAME);
        GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi.CC.setup(binaryMessenger, this);
        this.messenger = binaryMessenger;
    }

    private String registerEventChannel(String str, EventChannel.StreamHandler streamHandler) {
        return registerEventChannel(str, UUID.randomUUID().toString().toLowerCase(Locale.US), streamHandler);
    }

    private String registerEventChannel(String str, String str2, EventChannel.StreamHandler streamHandler) {
        EventChannel eventChannel = new EventChannel(this.messenger, str + RemoteSettings.FORWARD_SLASH_STRING + str2);
        eventChannel.setStreamHandler(streamHandler);
        this.eventChannels.put(str2, eventChannel);
        this.streamHandlers.put(str2, streamHandler);
        return str2;
    }

    private void removeEventListeners() {
        Iterator<String> it = this.eventChannels.keySet().iterator();
        while (it.hasNext()) {
            this.eventChannels.get(it.next()).setStreamHandler(null);
        }
        this.eventChannels.clear();
        Iterator<String> it2 = this.streamHandlers.keySet().iterator();
        while (it2.hasNext()) {
            this.streamHandlers.get(it2.next()).onCancel(null);
        }
        this.streamHandlers.clear();
    }

    private FirebaseStorage getStorageFromPigeon(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp) {
        return getStorageFromPigeon(pigeonStorageFirebaseApp, null);
    }

    private FirebaseStorage getStorageFromPigeon(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, String str) {
        FirebaseApp firebaseApp = FirebaseApp.getInstance(pigeonStorageFirebaseApp.getAppName());
        if (str == null) {
            return FirebaseStorage.getInstance(firebaseApp);
        }
        return FirebaseStorage.getInstance(firebaseApp, "gs://" + str);
    }

    private FirebaseStorage getStorage(Map<String, Object> map) {
        FirebaseStorage firebaseStorage;
        Object obj = map.get("appName");
        Objects.requireNonNull(obj);
        FirebaseApp firebaseApp = FirebaseApp.getInstance((String) obj);
        String str = (String) map.get("bucket");
        if (str == null) {
            firebaseStorage = FirebaseStorage.getInstance(firebaseApp);
        } else {
            firebaseStorage = FirebaseStorage.getInstance(firebaseApp, "gs://" + str);
        }
        Object obj2 = map.get("maxOperationRetryTime");
        if (obj2 != null) {
            firebaseStorage.setMaxOperationRetryTimeMillis(getLongValue(obj2).longValue());
        }
        Object obj3 = map.get("maxDownloadRetryTime");
        if (obj3 != null) {
            firebaseStorage.setMaxDownloadRetryTimeMillis(getLongValue(obj3).longValue());
        }
        Object obj4 = map.get("maxUploadRetryTime");
        if (obj4 != null) {
            firebaseStorage.setMaxUploadRetryTimeMillis(getLongValue(obj4).longValue());
        }
        return firebaseStorage;
    }

    private StorageReference getReferenceFromPigeon(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference) {
        return getStorageFromPigeon(pigeonStorageFirebaseApp, pigeonStorageReference.getBucket()).getReference(pigeonStorageReference.getFullPath());
    }

    private StorageReference getReference(Map<String, Object> map) {
        Object obj = map.get(Constants.PATH);
        Objects.requireNonNull(obj);
        return getStorage(map).getReference((String) obj);
    }

    private GeneratedAndroidFirebaseStorage.PigeonStorageReference convertToPigeonReference(StorageReference storageReference) {
        return new GeneratedAndroidFirebaseStorage.PigeonStorageReference.Builder().setBucket(storageReference.getBucket()).setFullPath(storageReference.getPath()).setName(storageReference.getName()).build();
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void getReferencebyPath(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, String str, String str2, GeneratedAndroidFirebaseStorage.Result<GeneratedAndroidFirebaseStorage.PigeonStorageReference> result) {
        result.success(convertToPigeonReference(getStorageFromPigeon(pigeonStorageFirebaseApp, str2).getReference(str)));
    }

    private Map<String, Object> parseListResult(ListResult listResult) {
        HashMap hashMap = new HashMap();
        if (listResult.getPageToken() != null) {
            hashMap.put("nextPageToken", listResult.getPageToken());
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<StorageReference> it = listResult.getItems().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPath());
        }
        Iterator<StorageReference> it2 = listResult.getPrefixes().iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getPath());
        }
        hashMap.put(FirebaseAnalytics.Param.ITEMS, arrayList);
        hashMap.put("prefixes", arrayList2);
        return hashMap;
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void useStorageEmulator(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, String str, Long l, GeneratedAndroidFirebaseStorage.Result<Void> result) {
        try {
            getStorageFromPigeon(pigeonStorageFirebaseApp).useEmulator(str, l.intValue());
            result.success(null);
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceDelete(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, final GeneratedAndroidFirebaseStorage.Result<Void> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath()).delete().addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.lambda$referenceDelete$0(GeneratedAndroidFirebaseStorage.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$referenceDelete$0(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(null);
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceGetDownloadURL(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, final GeneratedAndroidFirebaseStorage.Result<String> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.lambda$referenceGetDownloadURL$1(GeneratedAndroidFirebaseStorage.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$referenceGetDownloadURL$1(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(((Uri) task.getResult()).toString());
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceGetData(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, Long l, final GeneratedAndroidFirebaseStorage.Result<byte[]> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath()).getBytes(l.longValue()).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.lambda$referenceGetData$2(GeneratedAndroidFirebaseStorage.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$referenceGetData$2(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success((byte[]) task.getResult());
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    GeneratedAndroidFirebaseStorage.PigeonFullMetaData convertToPigeonMetaData(StorageMetadata storageMetadata) {
        return new GeneratedAndroidFirebaseStorage.PigeonFullMetaData.Builder().setMetadata(parseMetadataToMap(storageMetadata)).build();
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceGetMetaData(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, final GeneratedAndroidFirebaseStorage.Result<GeneratedAndroidFirebaseStorage.PigeonFullMetaData> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath()).getMetadata().addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.this.m1086x7f19e5c(result, task);
            }
        });
    }

    /* renamed from: lambda$referenceGetMetaData$3$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m1086x7f19e5c(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(convertToPigeonMetaData((StorageMetadata) task.getResult()));
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    GeneratedAndroidFirebaseStorage.PigeonListResult convertToPigeonListResult(ListResult listResult) {
        ArrayList arrayList = new ArrayList();
        Iterator<StorageReference> it = listResult.getItems().iterator();
        while (it.hasNext()) {
            arrayList.add(convertToPigeonReference(it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<StorageReference> it2 = listResult.getPrefixes().iterator();
        while (it2.hasNext()) {
            arrayList2.add(convertToPigeonReference(it2.next()));
        }
        return new GeneratedAndroidFirebaseStorage.PigeonListResult.Builder().setItems(arrayList).setPageToken(listResult.getPageToken()).setPrefixs(arrayList2).build();
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceList(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, GeneratedAndroidFirebaseStorage.PigeonListOptions pigeonListOptions, final GeneratedAndroidFirebaseStorage.Result<GeneratedAndroidFirebaseStorage.PigeonListResult> result) {
        Task<ListResult> list;
        StorageReference reference = getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath());
        if (pigeonListOptions.getPageToken() == null) {
            list = reference.list(pigeonListOptions.getMaxResults().intValue());
        } else {
            list = reference.list(pigeonListOptions.getMaxResults().intValue(), pigeonListOptions.getPageToken());
        }
        list.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.this.m1087xaee20726(result, task);
            }
        });
    }

    /* renamed from: lambda$referenceList$4$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m1087xaee20726(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(convertToPigeonListResult((ListResult) task.getResult()));
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceListAll(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, final GeneratedAndroidFirebaseStorage.Result<GeneratedAndroidFirebaseStorage.PigeonListResult> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath()).listAll().addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.this.m1088x9ffe597c(result, task);
            }
        });
    }

    /* renamed from: lambda$referenceListAll$5$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m1088x9ffe597c(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(convertToPigeonListResult((ListResult) task.getResult()));
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    StorageMetadata getMetaDataFromPigeon(GeneratedAndroidFirebaseStorage.PigeonSettableMetadata pigeonSettableMetadata) {
        StorageMetadata.Builder contentType = new StorageMetadata.Builder().setCacheControl(pigeonSettableMetadata.getCacheControl()).setContentDisposition(pigeonSettableMetadata.getContentDisposition()).setContentEncoding(pigeonSettableMetadata.getContentEncoding()).setContentLanguage(pigeonSettableMetadata.getContentLanguage()).setContentType(pigeonSettableMetadata.getContentType());
        Map<String, String> customMetadata = pigeonSettableMetadata.getCustomMetadata();
        if (customMetadata != null) {
            for (Map.Entry<String, String> entry : customMetadata.entrySet()) {
                contentType.setCustomMetadata(entry.getKey(), entry.getValue());
            }
        }
        return contentType.build();
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceUpdateMetadata(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, GeneratedAndroidFirebaseStorage.PigeonSettableMetadata pigeonSettableMetadata, final GeneratedAndroidFirebaseStorage.Result<GeneratedAndroidFirebaseStorage.PigeonFullMetaData> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).getReference(pigeonStorageReference.getFullPath()).updateMetadata(getMetaDataFromPigeon(pigeonSettableMetadata)).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.this.m1089xa7dc682a(result, task);
            }
        });
    }

    /* renamed from: lambda$referenceUpdateMetadata$6$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m1089xa7dc682a(GeneratedAndroidFirebaseStorage.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(convertToPigeonMetaData((StorageMetadata) task.getResult()));
        } else {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(task.getException()));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referencePutData(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, byte[] bArr, GeneratedAndroidFirebaseStorage.PigeonSettableMetadata pigeonSettableMetadata, Long l, GeneratedAndroidFirebaseStorage.Result<String> result) {
        try {
            result.success(registerEventChannel("plugins.flutter.io/firebase_storage/taskEvent", FlutterFirebaseStorageTask.uploadBytes(l.intValue(), getReferenceFromPigeon(pigeonStorageFirebaseApp, pigeonStorageReference), bArr, getMetaDataFromPigeon(pigeonSettableMetadata)).startTaskWithMethodChannel(this.channel)));
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referencePutString(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, String str, Long l, GeneratedAndroidFirebaseStorage.PigeonSettableMetadata pigeonSettableMetadata, Long l2, GeneratedAndroidFirebaseStorage.Result<String> result) {
        try {
            result.success(registerEventChannel("plugins.flutter.io/firebase_storage/taskEvent", FlutterFirebaseStorageTask.uploadBytes(l2.intValue(), getReferenceFromPigeon(pigeonStorageFirebaseApp, pigeonStorageReference), stringToByteData(str, l.intValue()), getMetaDataFromPigeon(pigeonSettableMetadata)).startTaskWithMethodChannel(this.channel)));
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referencePutFile(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, String str, GeneratedAndroidFirebaseStorage.PigeonSettableMetadata pigeonSettableMetadata, Long l, GeneratedAndroidFirebaseStorage.Result<String> result) {
        try {
            result.success(registerEventChannel("plugins.flutter.io/firebase_storage/taskEvent", FlutterFirebaseStorageTask.uploadFile(l.intValue(), getReferenceFromPigeon(pigeonStorageFirebaseApp, pigeonStorageReference), Uri.fromFile(new File(str)), getMetaDataFromPigeon(pigeonSettableMetadata)).startTaskWithMethodChannel(this.channel)));
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void referenceDownloadFile(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, GeneratedAndroidFirebaseStorage.PigeonStorageReference pigeonStorageReference, String str, Long l, GeneratedAndroidFirebaseStorage.Result<String> result) {
        try {
            result.success(registerEventChannel("plugins.flutter.io/firebase_storage/taskEvent", FlutterFirebaseStorageTask.downloadFile(l.intValue(), getReferenceFromPigeon(pigeonStorageFirebaseApp, pigeonStorageReference), new File(str)).startTaskWithMethodChannel(this.channel)));
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void taskPause(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, Long l, GeneratedAndroidFirebaseStorage.Result<Map<String, Object>> result) {
        FlutterFirebaseStorageTask inProgressTaskForHandle = FlutterFirebaseStorageTask.getInProgressTaskForHandle(l.intValue());
        if (inProgressTaskForHandle == null) {
            result.error(new GeneratedAndroidFirebaseStorage.FlutterError("unknown", "Pause operation was called on a task which does not exist.", null));
            return;
        }
        HashMap hashMap = new HashMap();
        try {
            boolean pause = inProgressTaskForHandle.getAndroidTask().pause();
            hashMap.put("status", Boolean.valueOf(pause));
            if (pause) {
                hashMap.put("snapshot", FlutterFirebaseStorageTask.parseTaskSnapshot(inProgressTaskForHandle.getSnapshot()));
            }
            result.success(hashMap);
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void taskResume(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, Long l, GeneratedAndroidFirebaseStorage.Result<Map<String, Object>> result) {
        FlutterFirebaseStorageTask inProgressTaskForHandle = FlutterFirebaseStorageTask.getInProgressTaskForHandle(l.intValue());
        if (inProgressTaskForHandle == null) {
            result.error(new GeneratedAndroidFirebaseStorage.FlutterError("unknown", "Resume operation was called on a task which does not exist.", null));
            return;
        }
        try {
            boolean resume = inProgressTaskForHandle.getAndroidTask().resume();
            HashMap hashMap = new HashMap();
            hashMap.put("status", Boolean.valueOf(resume));
            if (resume) {
                hashMap.put("snapshot", FlutterFirebaseStorageTask.parseTaskSnapshot(inProgressTaskForHandle.getSnapshot()));
            }
            result.success(hashMap);
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void taskCancel(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, Long l, GeneratedAndroidFirebaseStorage.Result<Map<String, Object>> result) {
        FlutterFirebaseStorageTask inProgressTaskForHandle = FlutterFirebaseStorageTask.getInProgressTaskForHandle(l.intValue());
        if (inProgressTaskForHandle == null) {
            result.error(new GeneratedAndroidFirebaseStorage.FlutterError("unknown", "Cancel operation was called on a task which does not exist.", null));
            return;
        }
        try {
            boolean cancel = inProgressTaskForHandle.getAndroidTask().cancel();
            HashMap hashMap = new HashMap();
            hashMap.put("status", Boolean.valueOf(cancel));
            if (cancel) {
                hashMap.put("snapshot", FlutterFirebaseStorageTask.parseTaskSnapshot(inProgressTaskForHandle.getSnapshot()));
            }
            result.success(hashMap);
        } catch (Exception e) {
            result.error(FlutterFirebaseStorageException.parserExceptionToFlutter(e));
        }
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void setMaxOperationRetryTime(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, Long l, GeneratedAndroidFirebaseStorage.Result<Void> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).setMaxOperationRetryTimeMillis(l.longValue());
        result.success(null);
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void setMaxUploadRetryTime(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, Long l, GeneratedAndroidFirebaseStorage.Result<Void> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).setMaxUploadRetryTimeMillis(l.longValue());
        result.success(null);
    }

    @Override // io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage.FirebaseStorageHostApi
    public void setMaxDownloadRetryTime(GeneratedAndroidFirebaseStorage.PigeonStorageFirebaseApp pigeonStorageFirebaseApp, Long l, GeneratedAndroidFirebaseStorage.Result<Void> result) {
        getStorageFromPigeon(pigeonStorageFirebaseApp).setMaxDownloadRetryTimeMillis(l.longValue());
        result.success(null);
    }

    private StorageMetadata parseToStorageMetadata(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        if (map.get("cacheControl") != null) {
            builder.setCacheControl((String) map.get("cacheControl"));
        }
        if (map.get("contentDisposition") != null) {
            builder.setContentDisposition((String) map.get("contentDisposition"));
        }
        if (map.get("contentEncoding") != null) {
            builder.setContentEncoding((String) map.get("contentEncoding"));
        }
        if (map.get("contentLanguage") != null) {
            builder.setContentLanguage((String) map.get("contentLanguage"));
        }
        if (map.get("contentType") != null) {
            builder.setContentType((String) map.get("contentType"));
        }
        if (map.get("customMetadata") != null) {
            Object obj = map.get("customMetadata");
            Objects.requireNonNull(obj);
            Map map2 = (Map) obj;
            for (String str : map2.keySet()) {
                builder.setCustomMetadata(str, (String) map2.get(str));
            }
        }
        return builder.build();
    }

    private byte[] stringToByteData(String str, int i) {
        if (i == 1) {
            return Base64.decode(str, 0);
        }
        if (i != 2) {
            return null;
        }
        return Base64.decode(str, 8);
    }

    private Long getLongValue(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        return 0L;
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                TaskCompletionSource.this.setResult(new HashMap());
            }
        });
        return taskCompletionSource.getTask();
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m1085x55f1ef83(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$didReinitializeFirebaseCore$8$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m1085x55f1ef83(TaskCompletionSource taskCompletionSource) {
        FlutterFirebaseStorageTask.cancelInProgressTasks();
        taskCompletionSource.setResult(null);
        removeEventListeners();
    }
}
