package io.flutter.plugins.firebase.core;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class FlutterFirebaseCorePlugin implements FlutterPlugin, GeneratedAndroidFirebaseCore.FirebaseCoreHostApi, GeneratedAndroidFirebaseCore.FirebaseAppHostApi {
    private Context applicationContext;
    private boolean coreInitialized = false;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), this);
        GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), this);
        this.applicationContext = flutterPluginBinding.getApplicationContext();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.applicationContext = null;
        GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), null);
        GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), null);
    }

    private GeneratedAndroidFirebaseCore.PigeonFirebaseOptions firebaseOptionsToMap(FirebaseOptions firebaseOptions) {
        GeneratedAndroidFirebaseCore.PigeonFirebaseOptions.Builder builder = new GeneratedAndroidFirebaseCore.PigeonFirebaseOptions.Builder();
        builder.setApiKey(firebaseOptions.getApiKey());
        builder.setAppId(firebaseOptions.getApplicationId());
        if (firebaseOptions.getGcmSenderId() != null) {
            builder.setMessagingSenderId(firebaseOptions.getGcmSenderId());
        }
        if (firebaseOptions.getProjectId() != null) {
            builder.setProjectId(firebaseOptions.getProjectId());
        }
        builder.setDatabaseURL(firebaseOptions.getDatabaseUrl());
        builder.setStorageBucket(firebaseOptions.getStorageBucket());
        builder.setTrackingId(firebaseOptions.getGaTrackingId());
        return builder.build();
    }

    private Task<GeneratedAndroidFirebaseCore.PigeonInitializeResponse> firebaseAppToMap(final FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.this.m1037x5526557c(firebaseApp, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$firebaseAppToMap$0$io-flutter-plugins-firebase-core-FlutterFirebaseCorePlugin, reason: not valid java name */
    public /* synthetic */ void m1037x5526557c(FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        try {
            GeneratedAndroidFirebaseCore.PigeonInitializeResponse.Builder builder = new GeneratedAndroidFirebaseCore.PigeonInitializeResponse.Builder();
            builder.setName(firebaseApp.getName());
            builder.setOptions(firebaseOptionsToMap(firebaseApp.getOptions()));
            builder.setIsAutomaticDataCollectionEnabled(Boolean.valueOf(firebaseApp.isDataCollectionDefaultEnabled()));
            builder.setPluginConstants((Map) Tasks.await(FlutterFirebasePluginRegistry.getPluginConstantsForFirebaseApp(firebaseApp)));
            taskCompletionSource.setResult(builder.build());
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private <T> void listenToResponse(TaskCompletionSource<T> taskCompletionSource, final GeneratedAndroidFirebaseCore.Result<T> result) {
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseCorePlugin.lambda$listenToResponse$1(GeneratedAndroidFirebaseCore.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$listenToResponse$1(GeneratedAndroidFirebaseCore.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
        } else {
            result.error(task.getException());
        }
    }

    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi
    public void initializeApp(final String str, final GeneratedAndroidFirebaseCore.PigeonFirebaseOptions pigeonFirebaseOptions, GeneratedAndroidFirebaseCore.Result<GeneratedAndroidFirebaseCore.PigeonInitializeResponse> result) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.this.m1038xc7a7cd8a(pigeonFirebaseOptions, str, taskCompletionSource);
            }
        });
        listenToResponse(taskCompletionSource, result);
    }

    /* renamed from: lambda$initializeApp$2$io-flutter-plugins-firebase-core-FlutterFirebaseCorePlugin, reason: not valid java name */
    public /* synthetic */ void m1038xc7a7cd8a(GeneratedAndroidFirebaseCore.PigeonFirebaseOptions pigeonFirebaseOptions, String str, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseOptions build = new FirebaseOptions.Builder().setApiKey(pigeonFirebaseOptions.getApiKey()).setApplicationId(pigeonFirebaseOptions.getAppId()).setDatabaseUrl(pigeonFirebaseOptions.getDatabaseURL()).setGcmSenderId(pigeonFirebaseOptions.getMessagingSenderId()).setProjectId(pigeonFirebaseOptions.getProjectId()).setStorageBucket(pigeonFirebaseOptions.getStorageBucket()).setGaTrackingId(pigeonFirebaseOptions.getTrackingId()).build();
            try {
                Looper.prepare();
            } catch (Exception unused) {
            }
            taskCompletionSource.setResult((GeneratedAndroidFirebaseCore.PigeonInitializeResponse) Tasks.await(firebaseAppToMap(FirebaseApp.initializeApp(this.applicationContext, build, str))));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi
    public void initializeCore(GeneratedAndroidFirebaseCore.Result<List<GeneratedAndroidFirebaseCore.PigeonInitializeResponse>> result) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.this.m1039x57de4071(taskCompletionSource);
            }
        });
        listenToResponse(taskCompletionSource, result);
    }

    /* renamed from: lambda$initializeCore$3$io-flutter-plugins-firebase-core-FlutterFirebaseCorePlugin, reason: not valid java name */
    public /* synthetic */ void m1039x57de4071(TaskCompletionSource taskCompletionSource) {
        try {
            if (!this.coreInitialized) {
                this.coreInitialized = true;
            } else {
                Tasks.await(FlutterFirebasePluginRegistry.didReinitializeFirebaseCore());
            }
            List<FirebaseApp> apps = FirebaseApp.getApps(this.applicationContext);
            ArrayList arrayList = new ArrayList(apps.size());
            Iterator<FirebaseApp> it = apps.iterator();
            while (it.hasNext()) {
                arrayList.add((GeneratedAndroidFirebaseCore.PigeonInitializeResponse) Tasks.await(firebaseAppToMap(it.next())));
            }
            taskCompletionSource.setResult(arrayList);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi
    public void optionsFromResource(GeneratedAndroidFirebaseCore.Result<GeneratedAndroidFirebaseCore.PigeonFirebaseOptions> result) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.this.m1040x1f536d83(taskCompletionSource);
            }
        });
        listenToResponse(taskCompletionSource, result);
    }

    /* renamed from: lambda$optionsFromResource$4$io-flutter-plugins-firebase-core-FlutterFirebaseCorePlugin, reason: not valid java name */
    public /* synthetic */ void m1040x1f536d83(TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseOptions fromResource = FirebaseOptions.fromResource(this.applicationContext);
            if (fromResource == null) {
                taskCompletionSource.setResult(null);
            } else {
                taskCompletionSource.setResult(firebaseOptionsToMap(fromResource));
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi
    public void setAutomaticDataCollectionEnabled(final String str, final Boolean bool, GeneratedAndroidFirebaseCore.Result<Void> result) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.lambda$setAutomaticDataCollectionEnabled$5(str, bool, taskCompletionSource);
            }
        });
        listenToResponse(taskCompletionSource, result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setAutomaticDataCollectionEnabled$5(String str, Boolean bool, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseApp.getInstance(str).setDataCollectionDefaultEnabled(bool);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi
    public void setAutomaticResourceManagementEnabled(final String str, final Boolean bool, GeneratedAndroidFirebaseCore.Result<Void> result) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.lambda$setAutomaticResourceManagementEnabled$6(str, bool, taskCompletionSource);
            }
        });
        listenToResponse(taskCompletionSource, result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setAutomaticResourceManagementEnabled$6(String str, Boolean bool, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseApp.getInstance(str).setAutomaticResourceManagementEnabled(bool.booleanValue());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi
    public void delete(final String str, GeneratedAndroidFirebaseCore.Result<Void> result) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebaseCorePlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCorePlugin.lambda$delete$7(str, taskCompletionSource);
            }
        });
        listenToResponse(taskCompletionSource, result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$delete$7(String str, TaskCompletionSource taskCompletionSource) {
        try {
            try {
                FirebaseApp.getInstance(str).delete();
            } catch (IllegalStateException unused) {
            }
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }
}
