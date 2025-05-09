package io.flutter.plugins.firebase.storage;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageTask;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.storage.GeneratedAndroidFirebaseStorage;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class TaskStateChannelStreamHandler implements EventChannel.StreamHandler {
    private final FirebaseStorage androidStorage;
    private final StorageTask<?> androidTask;
    private final FlutterFirebaseStorageTask flutterTask;
    private final String TASK_STATE_NAME = "taskState";
    private final String TASK_APP_NAME = "appName";
    private final String TASK_SNAPSHOT = "snapshot";

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
    }

    public TaskStateChannelStreamHandler(FlutterFirebaseStorageTask flutterFirebaseStorageTask, FirebaseStorage firebaseStorage, StorageTask storageTask) {
        this.flutterTask = flutterFirebaseStorageTask;
        this.androidStorage = firebaseStorage;
        this.androidTask = storageTask;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.androidTask.addOnProgressListener(new OnProgressListener() { // from class: io.flutter.plugins.firebase.storage.TaskStateChannelStreamHandler$$ExternalSyntheticLambda4
            @Override // com.google.firebase.storage.OnProgressListener
            public final void onProgress(Object obj2) {
                TaskStateChannelStreamHandler.this.m1090x4c3bd9ab(eventSink, (StorageTask.ProvideError) obj2);
            }
        });
        this.androidTask.addOnPausedListener(new OnPausedListener() { // from class: io.flutter.plugins.firebase.storage.TaskStateChannelStreamHandler$$ExternalSyntheticLambda3
            @Override // com.google.firebase.storage.OnPausedListener
            public final void onPaused(Object obj2) {
                TaskStateChannelStreamHandler.this.m1091x27fd556c(eventSink, (StorageTask.ProvideError) obj2);
            }
        });
        this.androidTask.addOnSuccessListener(new OnSuccessListener() { // from class: io.flutter.plugins.firebase.storage.TaskStateChannelStreamHandler$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj2) {
                TaskStateChannelStreamHandler.this.m1092x3bed12d(eventSink, (StorageTask.ProvideError) obj2);
            }
        });
        this.androidTask.addOnCanceledListener(new OnCanceledListener() { // from class: io.flutter.plugins.firebase.storage.TaskStateChannelStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public final void onCanceled() {
                TaskStateChannelStreamHandler.this.m1093xdf804cee(eventSink);
            }
        });
        this.androidTask.addOnFailureListener(new OnFailureListener() { // from class: io.flutter.plugins.firebase.storage.TaskStateChannelStreamHandler$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                TaskStateChannelStreamHandler.this.m1094xbb41c8af(eventSink, exc);
            }
        });
    }

    /* renamed from: lambda$onListen$0$io-flutter-plugins-firebase-storage-TaskStateChannelStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1090x4c3bd9ab(EventChannel.EventSink eventSink, StorageTask.ProvideError provideError) {
        if (this.flutterTask.isDestroyed()) {
            return;
        }
        Map<String, Object> taskEventMap = getTaskEventMap(provideError, null);
        taskEventMap.put("taskState", Integer.valueOf(GeneratedAndroidFirebaseStorage.PigeonStorageTaskState.RUNNING.index));
        eventSink.success(taskEventMap);
        this.flutterTask.notifyResumeObjects();
    }

    /* renamed from: lambda$onListen$1$io-flutter-plugins-firebase-storage-TaskStateChannelStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1091x27fd556c(EventChannel.EventSink eventSink, StorageTask.ProvideError provideError) {
        if (this.flutterTask.isDestroyed()) {
            return;
        }
        Map<String, Object> taskEventMap = getTaskEventMap(provideError, null);
        taskEventMap.put("taskState", Integer.valueOf(GeneratedAndroidFirebaseStorage.PigeonStorageTaskState.PAUSED.index));
        eventSink.success(taskEventMap);
        this.flutterTask.notifyPauseObjects();
    }

    /* renamed from: lambda$onListen$2$io-flutter-plugins-firebase-storage-TaskStateChannelStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1092x3bed12d(EventChannel.EventSink eventSink, StorageTask.ProvideError provideError) {
        if (this.flutterTask.isDestroyed()) {
            return;
        }
        Map<String, Object> taskEventMap = getTaskEventMap(provideError, null);
        taskEventMap.put("taskState", Integer.valueOf(GeneratedAndroidFirebaseStorage.PigeonStorageTaskState.SUCCESS.index));
        eventSink.success(taskEventMap);
        this.flutterTask.destroy();
    }

    /* renamed from: lambda$onListen$3$io-flutter-plugins-firebase-storage-TaskStateChannelStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1093xdf804cee(EventChannel.EventSink eventSink) {
        if (this.flutterTask.isDestroyed()) {
            return;
        }
        Map<String, Object> taskEventMap = getTaskEventMap(null, null);
        taskEventMap.put("taskState", Integer.valueOf(GeneratedAndroidFirebaseStorage.PigeonStorageTaskState.CANCELED.index));
        eventSink.success(taskEventMap);
        this.flutterTask.notifyCancelObjects();
        this.flutterTask.destroy();
    }

    /* renamed from: lambda$onListen$4$io-flutter-plugins-firebase-storage-TaskStateChannelStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1094xbb41c8af(EventChannel.EventSink eventSink, Exception exc) {
        if (this.flutterTask.isDestroyed()) {
            return;
        }
        Map<String, Object> taskEventMap = getTaskEventMap(null, exc);
        taskEventMap.put("taskState", Integer.valueOf(GeneratedAndroidFirebaseStorage.PigeonStorageTaskState.ERROR.index));
        eventSink.error("firebase_storage", exc.getMessage(), taskEventMap);
        this.flutterTask.destroy();
    }

    private Map<String, Object> getTaskEventMap(Object obj, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("appName", this.androidStorage.getApp().getName());
        if (obj != null) {
            hashMap.put("snapshot", FlutterFirebaseStorageTask.parseTaskSnapshot(obj));
        }
        if (exc != null) {
            hashMap.put("error", FlutterFirebaseStoragePlugin.getExceptionDetails(exc));
        }
        return hashMap;
    }
}
