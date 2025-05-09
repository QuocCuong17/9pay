package io.flutter.plugins.firebase.analytics;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.database.FlutterFirebaseDatabaseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class FlutterFirebaseAnalyticsPlugin implements FlutterFirebasePlugin, MethodChannel.MethodCallHandler, FlutterPlugin {
    private FirebaseAnalytics analytics;
    private MethodChannel channel;

    private void initInstance(BinaryMessenger binaryMessenger, Context context) {
        this.analytics = FirebaseAnalytics.getInstance(context);
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.flutter.io/firebase_analytics");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        FlutterFirebasePluginRegistry.registerPlugin("plugins.flutter.io/firebase_analytics", this);
    }

    private static Bundle createBundleFromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof Integer) {
                bundle.putLong(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                bundle.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Double) {
                bundle.putDouble(key, ((Double) value).doubleValue());
            } else if (value instanceof Boolean) {
                bundle.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value == null) {
                bundle.putString(key, null);
            } else if (value instanceof Iterable) {
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                for (Object obj : (Iterable) value) {
                    if (obj instanceof Map) {
                        arrayList.add(createBundleFromMap((Map) obj));
                    } else {
                        throw new IllegalArgumentException("Unsupported value type: " + obj.getClass().getCanonicalName() + " in list at key " + key);
                    }
                }
                bundle.putParcelableArrayList(key, arrayList);
            } else if (value instanceof Map) {
                bundle.putParcelable(key, createBundleFromMap((Map) value));
            } else {
                throw new IllegalArgumentException("Unsupported value type: " + value.getClass().getCanonicalName());
            }
        }
        return bundle;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        MethodChannel methodChannel = this.channel;
        if (methodChannel != null) {
            methodChannel.setMethodCallHandler(null);
            this.channel = null;
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task handleGetAppInstanceId;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2090892968:
                if (str.equals("Analytics#getAppInstanceId")) {
                    c = 0;
                    break;
                }
                break;
            case -1931910274:
                if (str.equals("Analytics#resetAnalyticsData")) {
                    c = 1;
                    break;
                }
                break;
            case -1572470123:
                if (str.equals("Analytics#setConsent")) {
                    c = 2;
                    break;
                }
                break;
            case -273201790:
                if (str.equals("Analytics#setAnalyticsCollectionEnabled")) {
                    c = 3;
                    break;
                }
                break;
            case -99047480:
                if (str.equals("Analytics#setDefaultEventParameters")) {
                    c = 4;
                    break;
                }
                break;
            case -45011405:
                if (str.equals("Analytics#logEvent")) {
                    c = 5;
                    break;
                }
                break;
            case 179244440:
                if (str.equals("Analytics#getSessionId")) {
                    c = 6;
                    break;
                }
                break;
            case 1083589925:
                if (str.equals("Analytics#setUserProperty")) {
                    c = 7;
                    break;
                }
                break;
            case 1751063748:
                if (str.equals("Analytics#setSessionTimeoutDuration")) {
                    c = '\b';
                    break;
                }
                break;
            case 1992044651:
                if (str.equals("Analytics#setUserId")) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                handleGetAppInstanceId = handleGetAppInstanceId();
                break;
            case 1:
                handleGetAppInstanceId = handleResetAnalyticsData();
                break;
            case 2:
                handleGetAppInstanceId = setConsent((Map) methodCall.arguments());
                break;
            case 3:
                handleGetAppInstanceId = handleSetAnalyticsCollectionEnabled((Map) methodCall.arguments());
                break;
            case 4:
                handleGetAppInstanceId = setDefaultEventParameters((Map) methodCall.arguments());
                break;
            case 5:
                handleGetAppInstanceId = handleLogEvent((Map) methodCall.arguments());
                break;
            case 6:
                handleGetAppInstanceId = handleGetSessionId();
                break;
            case 7:
                handleGetAppInstanceId = handleSetUserProperty((Map) methodCall.arguments());
                break;
            case '\b':
                handleGetAppInstanceId = handleSetSessionTimeoutDuration((Map) methodCall.arguments());
                break;
            case '\t':
                handleGetAppInstanceId = handleSetUserId((Map) methodCall.arguments());
                break;
            default:
                result.notImplemented();
                return;
        }
        handleGetAppInstanceId.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseAnalyticsPlugin.lambda$onMethodCall$0(MethodChannel.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$0(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
        } else {
            Exception exception = task.getException();
            result.error("firebase_analytics", exception != null ? exception.getMessage() : FlutterFirebaseDatabaseException.UNKNOWN_ERROR_MESSAGE, null);
        }
    }

    private Task<Long> handleGetSessionId() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1028x9b953f75(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleGetSessionId$1$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1028x9b953f75(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult((Long) Tasks.await(this.analytics.getSessionId()));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> handleLogEvent(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1029xf94b1f19(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleLogEvent$2$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1029xf94b1f19(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get(Constants.EVENT_NAME);
            Objects.requireNonNull(obj);
            Bundle createBundleFromMap = createBundleFromMap((Map) map.get("parameters"));
            this.analytics.logEvent((String) obj, createBundleFromMap);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> handleSetUserId(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1033xa4516010(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleSetUserId$3$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1033xa4516010(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            this.analytics.setUserId((String) map.get(Constants.USER_ID));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> handleSetAnalyticsCollectionEnabled(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1031x85e657b8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleSetAnalyticsCollectionEnabled$4$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1031x85e657b8(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("enabled");
            Objects.requireNonNull(obj);
            this.analytics.setAnalyticsCollectionEnabled(((Boolean) obj).booleanValue());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> handleSetSessionTimeoutDuration(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1032xa24506d5(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleSetSessionTimeoutDuration$5$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1032xa24506d5(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Objects.requireNonNull(map.get(Constants.MILLISECONDS));
            this.analytics.setSessionTimeoutDuration(((Integer) r4).intValue());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> handleSetUserProperty(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1034x4bbb01f3(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleSetUserProperty$6$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1034x4bbb01f3(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("name");
            Objects.requireNonNull(obj);
            String str = (String) map.get("value");
            this.analytics.setUserProperty((String) obj, str);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> handleResetAnalyticsData() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1030x47c82489(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleResetAnalyticsData$7$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1030x47c82489(TaskCompletionSource taskCompletionSource) {
        try {
            this.analytics.resetAnalyticsData();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setConsent(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1035xcce26db9(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setConsent$8$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1035xcce26db9(Map map, TaskCompletionSource taskCompletionSource) {
        FirebaseAnalytics.ConsentStatus consentStatus;
        FirebaseAnalytics.ConsentStatus consentStatus2;
        try {
            Boolean bool = (Boolean) map.get(Constants.AD_STORAGE_CONSENT_GRANTED);
            Boolean bool2 = (Boolean) map.get(Constants.ANALYTICS_STORAGE_CONSENT_GRANTED);
            HashMap hashMap = new HashMap();
            if (bool != null) {
                FirebaseAnalytics.ConsentType consentType = FirebaseAnalytics.ConsentType.AD_STORAGE;
                if (bool.booleanValue()) {
                    consentStatus2 = FirebaseAnalytics.ConsentStatus.GRANTED;
                } else {
                    consentStatus2 = FirebaseAnalytics.ConsentStatus.DENIED;
                }
                hashMap.put(consentType, consentStatus2);
            }
            if (bool2 != null) {
                FirebaseAnalytics.ConsentType consentType2 = FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE;
                if (bool2.booleanValue()) {
                    consentStatus = FirebaseAnalytics.ConsentStatus.GRANTED;
                } else {
                    consentStatus = FirebaseAnalytics.ConsentStatus.DENIED;
                }
                hashMap.put(consentType2, consentStatus);
            }
            this.analytics.setConsent(hashMap);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setDefaultEventParameters(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1036x96fa8145(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setDefaultEventParameters$9$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1036x96fa8145(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            this.analytics.setDefaultEventParameters(createBundleFromMap(map));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<String> handleGetAppInstanceId() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1027x86b7fff(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$handleGetAppInstanceId$10$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1027x86b7fff(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult((String) Tasks.await(this.analytics.getAppInstanceId()));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.this.m1026x62318912(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getPluginConstantsForFirebaseApp$11$io-flutter-plugins-firebase-analytics-FlutterFirebaseAnalyticsPlugin, reason: not valid java name */
    public /* synthetic */ void m1026x62318912(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(new HashMap<String, Object>() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin.1
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.analytics.FlutterFirebaseAnalyticsPlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAnalyticsPlugin.lambda$didReinitializeFirebaseCore$12(TaskCompletionSource.this);
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
