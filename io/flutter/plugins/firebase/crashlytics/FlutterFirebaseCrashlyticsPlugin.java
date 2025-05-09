package io.flutter.plugins.firebase.crashlytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.FlutterFirebaseCrashlyticsInternal;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.database.FlutterFirebaseDatabaseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class FlutterFirebaseCrashlyticsPlugin implements FlutterFirebasePlugin, FlutterPlugin, MethodChannel.MethodCallHandler {
    public static final String TAG = "FLTFirebaseCrashlytics";
    private MethodChannel channel;

    private void initInstance(BinaryMessenger binaryMessenger) {
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.flutter.io/firebase_crashlytics");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        FlutterFirebasePluginRegistry.registerPlugin("plugins.flutter.io/firebase_crashlytics", this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        MethodChannel methodChannel = this.channel;
        if (methodChannel != null) {
            methodChannel.setMethodCallHandler(null);
            this.channel = null;
        }
    }

    private Task<Map<String, Object>> checkForUnsentReports() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.this.m1041xf43d20c6(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$checkForUnsentReports$0$io-flutter-plugins-firebase-crashlytics-FlutterFirebaseCrashlyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1041xf43d20c6(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(new HashMap<String, Object>(((Boolean) Tasks.await(FirebaseCrashlytics.getInstance().checkForUnsentReports())).booleanValue()) { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin.1
                final /* synthetic */ boolean val$unsentReports;

                {
                    this.val$unsentReports = r2;
                    put(Constants.UNSENT_REPORTS, Boolean.valueOf(r2));
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private void crash() {
        new Handler(Looper.myLooper()).postDelayed(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$crash$1();
            }
        }, 50L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$crash$1() {
        throw new FirebaseCrashlyticsTestCrash();
    }

    private Task<Void> deleteUnsentReports() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$deleteUnsentReports$2(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$deleteUnsentReports$2(TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseCrashlytics.getInstance().deleteUnsentReports();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> didCrashOnPreviousExecution() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.this.m1042xbcc1ffb6(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$didCrashOnPreviousExecution$3$io-flutter-plugins-firebase-crashlytics-FlutterFirebaseCrashlyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1042xbcc1ffb6(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(new HashMap<String, Object>(FirebaseCrashlytics.getInstance().didCrashOnPreviousExecution()) { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin.2
                final /* synthetic */ boolean val$didCrashOnPreviousExecution;

                {
                    this.val$didCrashOnPreviousExecution = r2;
                    put(Constants.DID_CRASH_ON_PREVIOUS_EXECUTION, Boolean.valueOf(r2));
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> recordError(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.this.m1044x582aa4b8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$recordError$4$io-flutter-plugins-firebase-crashlytics-FlutterFirebaseCrashlyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1044x582aa4b8(Map map, TaskCompletionSource taskCompletionSource) {
        FlutterError flutterError;
        try {
            FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
            Object obj = map.get("exception");
            Objects.requireNonNull(obj);
            String str = (String) obj;
            String str2 = (String) map.get("reason");
            Object obj2 = map.get("information");
            Objects.requireNonNull(obj2);
            String str3 = (String) obj2;
            Object obj3 = map.get(Constants.FATAL);
            Objects.requireNonNull(obj3);
            boolean booleanValue = ((Boolean) obj3).booleanValue();
            Object obj4 = map.get(Constants.BUILD_ID);
            Objects.requireNonNull(obj4);
            String str4 = (String) obj4;
            if (str4.length() > 0) {
                FlutterFirebaseCrashlyticsInternal.setFlutterBuildId(str4);
            }
            if (str2 != null) {
                firebaseCrashlytics.setCustomKey(Constants.FLUTTER_ERROR_REASON, "thrown " + str2);
                flutterError = new FlutterError(str + ". Error thrown " + str2 + ".");
            } else {
                flutterError = new FlutterError(str);
            }
            firebaseCrashlytics.setCustomKey(Constants.FLUTTER_ERROR_EXCEPTION, str);
            ArrayList arrayList = new ArrayList();
            Object obj5 = map.get(Constants.STACK_TRACE_ELEMENTS);
            Objects.requireNonNull(obj5);
            Iterator it = ((List) obj5).iterator();
            while (it.hasNext()) {
                StackTraceElement generateStackTraceElement = generateStackTraceElement((Map) it.next());
                if (generateStackTraceElement != null) {
                    arrayList.add(generateStackTraceElement);
                }
            }
            flutterError.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            if (!str3.isEmpty()) {
                firebaseCrashlytics.log(str3);
            }
            if (booleanValue) {
                FlutterFirebaseCrashlyticsInternal.recordFatalException(flutterError);
            } else {
                firebaseCrashlytics.recordException(flutterError);
            }
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> log(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$log$5(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$log$5(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("message");
            Objects.requireNonNull(obj);
            FirebaseCrashlytics.getInstance().log((String) obj);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> sendUnsentReports() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$sendUnsentReports$6(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$sendUnsentReports$6(TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseCrashlytics.getInstance().sendUnsentReports();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> setCrashlyticsCollectionEnabled(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.this.m1045x6c97280(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setCrashlyticsCollectionEnabled$7$io-flutter-plugins-firebase-crashlytics-FlutterFirebaseCrashlyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1045x6c97280(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("enabled");
            Objects.requireNonNull(obj);
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled((Boolean) obj);
            taskCompletionSource.setResult(new HashMap<String, Object>() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin.3
                {
                    put(Constants.IS_CRASHLYTICS_COLLECTION_ENABLED, Boolean.valueOf(FlutterFirebaseCrashlyticsPlugin.this.isCrashlyticsCollectionEnabled(FirebaseApp.getInstance())));
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setUserIdentifier(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$setUserIdentifier$8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setUserIdentifier$8(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("identifier");
            Objects.requireNonNull(obj);
            FirebaseCrashlytics.getInstance().setUserId((String) obj);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setCustomKey(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$setCustomKey$9(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setCustomKey$9(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("key");
            Objects.requireNonNull(obj);
            Object obj2 = map.get("value");
            Objects.requireNonNull(obj2);
            FirebaseCrashlytics.getInstance().setCustomKey((String) obj, (String) obj2);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task didCrashOnPreviousExecution;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2094964816:
                if (str.equals("Crashlytics#didCrashOnPreviousExecution")) {
                    c = 0;
                    break;
                }
                break;
            case -1437158995:
                if (str.equals("Crashlytics#recordError")) {
                    c = 1;
                    break;
                }
                break;
            case -1025128541:
                if (str.equals("Crashlytics#checkForUnsentReports")) {
                    c = 2;
                    break;
                }
                break;
            case -424770276:
                if (str.equals("Crashlytics#sendUnsentReports")) {
                    c = 3;
                    break;
                }
                break;
            case -108157790:
                if (str.equals("Crashlytics#setCrashlyticsCollectionEnabled")) {
                    c = 4;
                    break;
                }
                break;
            case 28093114:
                if (str.equals("Crashlytics#log")) {
                    c = 5;
                    break;
                }
                break;
            case 108415030:
                if (str.equals("Crashlytics#setCustomKey")) {
                    c = 6;
                    break;
                }
                break;
            case 213629529:
                if (str.equals("Crashlytics#deleteUnsentReports")) {
                    c = 7;
                    break;
                }
                break;
            case 679831756:
                if (str.equals("Crashlytics#setUserIdentifier")) {
                    c = '\b';
                    break;
                }
                break;
            case 1219454365:
                if (str.equals("Crashlytics#crash")) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                didCrashOnPreviousExecution = didCrashOnPreviousExecution();
                break;
            case 1:
                didCrashOnPreviousExecution = recordError((Map) methodCall.arguments());
                break;
            case 2:
                didCrashOnPreviousExecution = checkForUnsentReports();
                break;
            case 3:
                didCrashOnPreviousExecution = sendUnsentReports();
                break;
            case 4:
                didCrashOnPreviousExecution = setCrashlyticsCollectionEnabled((Map) methodCall.arguments());
                break;
            case 5:
                didCrashOnPreviousExecution = log((Map) methodCall.arguments());
                break;
            case 6:
                didCrashOnPreviousExecution = setCustomKey((Map) methodCall.arguments());
                break;
            case 7:
                didCrashOnPreviousExecution = deleteUnsentReports();
                break;
            case '\b':
                didCrashOnPreviousExecution = setUserIdentifier((Map) methodCall.arguments());
                break;
            case '\t':
                crash();
                return;
            default:
                result.notImplemented();
                return;
        }
        didCrashOnPreviousExecution.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseCrashlyticsPlugin.lambda$onMethodCall$10(MethodChannel.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$10(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
        } else {
            Exception exception = task.getException();
            result.error("firebase_crashlytics", exception != null ? exception.getMessage() : FlutterFirebaseDatabaseException.UNKNOWN_ERROR_MESSAGE, null);
        }
    }

    private StackTraceElement generateStackTraceElement(Map<String, String> map) {
        try {
            String str = map.get("file");
            String str2 = map.get(Constants.LINE);
            String str3 = map.get(Constants.CLASS);
            String str4 = map.get("method");
            if (str3 == null) {
                str3 = "";
            }
            Objects.requireNonNull(str2);
            String str5 = str2;
            return new StackTraceElement(str3, str4, str, Integer.parseInt(str2));
        } catch (Exception unused) {
            Log.e(TAG, "Unable to generate stack trace element from Dart error.");
            return null;
        }
    }

    private SharedPreferences getCrashlyticsSharedPrefs(Context context) {
        return context.getSharedPreferences("com.google.firebase.crashlytics", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCrashlyticsCollectionEnabled(FirebaseApp firebaseApp) {
        SharedPreferences crashlyticsSharedPrefs = getCrashlyticsSharedPrefs(firebaseApp.getApplicationContext());
        if (crashlyticsSharedPrefs.contains("firebase_crashlytics_collection_enabled")) {
            return crashlyticsSharedPrefs.getBoolean("firebase_crashlytics_collection_enabled", true);
        }
        if (!firebaseApp.isDataCollectionDefaultEnabled()) {
            return false;
        }
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        return true;
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(final FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.this.m1043x55419b2(taskCompletionSource, firebaseApp);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getPluginConstantsForFirebaseApp$11$io-flutter-plugins-firebase-crashlytics-FlutterFirebaseCrashlyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1043x55419b2(TaskCompletionSource taskCompletionSource, FirebaseApp firebaseApp) {
        try {
            taskCompletionSource.setResult(new HashMap<String, Object>(firebaseApp) { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin.4
                final /* synthetic */ FirebaseApp val$firebaseApp;

                {
                    this.val$firebaseApp = firebaseApp;
                    if (firebaseApp.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        put(Constants.IS_CRASHLYTICS_COLLECTION_ENABLED, Boolean.valueOf(FlutterFirebaseCrashlyticsPlugin.this.isCrashlyticsCollectionEnabled(FirebaseApp.getInstance())));
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.crashlytics.FlutterFirebaseCrashlyticsPlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseCrashlyticsPlugin.lambda$didReinitializeFirebaseCore$12(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$didReinitializeFirebaseCore$12(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }
}
