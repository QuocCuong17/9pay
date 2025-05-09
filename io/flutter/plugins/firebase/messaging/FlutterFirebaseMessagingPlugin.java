package io.flutter.plugins.firebase.messaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import io.flutter.embedding.engine.FlutterShellArgs;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.messaging.FlutterFirebasePermissionManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class FlutterFirebaseMessagingPlugin implements FlutterFirebasePlugin, MethodChannel.MethodCallHandler, PluginRegistry.NewIntentListener, FlutterPlugin, ActivityAware {
    private MethodChannel channel;
    private RemoteMessage initialMessage;
    private Map<String, Object> initialMessageNotification;
    private Activity mainActivity;
    FlutterFirebasePermissionManager permissionManager;
    private Observer<RemoteMessage> remoteMessageObserver;
    private Observer<String> tokenObserver;
    private final HashMap<String, Boolean> consumedInitialMessages = new HashMap<>();
    private final LiveData<RemoteMessage> liveDataRemoteMessage = FlutterFirebaseRemoteMessageLiveData.getInstance();
    private final LiveData<String> liveDataToken = FlutterFirebaseTokenLiveData.getInstance();

    private void initInstance(BinaryMessenger binaryMessenger) {
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.flutter.io/firebase_messaging");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        this.permissionManager = new FlutterFirebasePermissionManager();
        this.remoteMessageObserver = new Observer() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FlutterFirebaseMessagingPlugin.this.m1080x60a054b4((RemoteMessage) obj);
            }
        };
        this.tokenObserver = new Observer() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FlutterFirebaseMessagingPlugin.this.m1081x9980b553((String) obj);
            }
        };
        this.liveDataRemoteMessage.observeForever(this.remoteMessageObserver);
        this.liveDataToken.observeForever(this.tokenObserver);
        FlutterFirebasePluginRegistry.registerPlugin("plugins.flutter.io/firebase_messaging", this);
    }

    /* renamed from: lambda$initInstance$0$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1080x60a054b4(RemoteMessage remoteMessage) {
        this.channel.invokeMethod("Messaging#onMessage", FlutterFirebaseMessagingUtils.remoteMessageToMap(remoteMessage));
    }

    /* renamed from: lambda$initInstance$1$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1081x9980b553(String str) {
        this.channel.invokeMethod("Messaging#onTokenRefresh", str);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.liveDataToken.removeObserver(this.tokenObserver);
        this.liveDataRemoteMessage.removeObserver(this.remoteMessageObserver);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addOnNewIntentListener(this);
        activityPluginBinding.addRequestPermissionsResultListener(this.permissionManager);
        Activity activity = activityPluginBinding.getActivity();
        this.mainActivity = activity;
        if (activity.getIntent() == null || this.mainActivity.getIntent().getExtras() == null || (this.mainActivity.getIntent().getFlags() & 1048576) == 1048576) {
            return;
        }
        onNewIntent(this.mainActivity.getIntent());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        this.mainActivity = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addOnNewIntentListener(this);
        this.mainActivity = activityPluginBinding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.mainActivity = null;
    }

    private Task<Void> deleteToken() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.lambda$deleteToken$2(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$deleteToken$2(TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(FirebaseMessaging.getInstance().deleteToken());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> getToken() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.this.m1079x3d7973d3(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getToken$3$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1079x3d7973d3(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(new HashMap<String, Object>((String) Tasks.await(FirebaseMessaging.getInstance().getToken())) { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin.1
                final /* synthetic */ String val$token;

                {
                    this.val$token = r2;
                    put("token", r2);
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> subscribeToTopic(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.lambda$subscribeToTopic$4(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeToTopic$4(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseMessaging firebaseMessagingForArguments = FlutterFirebaseMessagingUtils.getFirebaseMessagingForArguments(map);
            Object obj = map.get("topic");
            Objects.requireNonNull(obj);
            Tasks.await(firebaseMessagingForArguments.subscribeToTopic((String) obj));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> unsubscribeFromTopic(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.lambda$unsubscribeFromTopic$5(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$unsubscribeFromTopic$5(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseMessaging firebaseMessagingForArguments = FlutterFirebaseMessagingUtils.getFirebaseMessagingForArguments(map);
            Object obj = map.get("topic");
            Objects.requireNonNull(obj);
            Tasks.await(firebaseMessagingForArguments.unsubscribeFromTopic((String) obj));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> sendMessage(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.lambda$sendMessage$6(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$sendMessage$6(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FlutterFirebaseMessagingUtils.getFirebaseMessagingForArguments(map).send(FlutterFirebaseMessagingUtils.getRemoteMessageForArguments(map));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> setAutoInitEnabled(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.this.m1084x1419fe12(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setAutoInitEnabled$7$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1084x1419fe12(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseMessaging firebaseMessagingForArguments = FlutterFirebaseMessagingUtils.getFirebaseMessagingForArguments(map);
            Object obj = map.get("enabled");
            Objects.requireNonNull(obj);
            firebaseMessagingForArguments.setAutoInitEnabled(((Boolean) obj).booleanValue());
            taskCompletionSource.setResult(new HashMap<String, Object>(firebaseMessagingForArguments) { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin.2
                final /* synthetic */ FirebaseMessaging val$firebaseMessaging;

                {
                    this.val$firebaseMessaging = firebaseMessagingForArguments;
                    put("isAutoInitEnabled", Boolean.valueOf(firebaseMessagingForArguments.isAutoInitEnabled()));
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setDeliveryMetricsExportToBigQuery(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.lambda$setDeliveryMetricsExportToBigQuery$8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setDeliveryMetricsExportToBigQuery$8(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseMessaging firebaseMessagingForArguments = FlutterFirebaseMessagingUtils.getFirebaseMessagingForArguments(map);
            Object obj = map.get("enabled");
            Objects.requireNonNull(obj);
            firebaseMessagingForArguments.setDeliveryMetricsExportToBigQuery(((Boolean) obj).booleanValue());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> getInitialMessage() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.this.m1077x6a1ec711(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getInitialMessage$9$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1077x6a1ec711(TaskCompletionSource taskCompletionSource) {
        Map map;
        try {
            RemoteMessage remoteMessage = this.initialMessage;
            if (remoteMessage != null) {
                Map<String, Object> remoteMessageToMap = FlutterFirebaseMessagingUtils.remoteMessageToMap(remoteMessage);
                Map<String, Object> map2 = this.initialMessageNotification;
                if (map2 != null) {
                    remoteMessageToMap.put("notification", map2);
                }
                taskCompletionSource.setResult(remoteMessageToMap);
                this.initialMessage = null;
                this.initialMessageNotification = null;
                return;
            }
            Activity activity = this.mainActivity;
            if (activity == null) {
                taskCompletionSource.setResult(null);
                return;
            }
            Intent intent = activity.getIntent();
            if (intent != null && intent.getExtras() != null) {
                String string = intent.getExtras().getString(Constants.MessagePayloadKeys.MSGID);
                if (string == null) {
                    string = intent.getExtras().getString(Constants.MessagePayloadKeys.MSGID_SERVER);
                }
                if (string != null && this.consumedInitialMessages.get(string) == null) {
                    RemoteMessage remoteMessage2 = FlutterFirebaseMessagingReceiver.notifications.get(string);
                    if (remoteMessage2 == null) {
                        Map<String, Object> firebaseMessageMap = FlutterFirebaseMessagingStore.getInstance().getFirebaseMessageMap(string);
                        if (firebaseMessageMap != null) {
                            remoteMessage2 = FlutterFirebaseMessagingUtils.getRemoteMessageForArguments(firebaseMessageMap);
                            if (firebaseMessageMap.get("notification") != null) {
                                map = (Map) firebaseMessageMap.get("notification");
                                FlutterFirebaseMessagingStore.getInstance().removeFirebaseMessage(string);
                            }
                        }
                        map = null;
                        FlutterFirebaseMessagingStore.getInstance().removeFirebaseMessage(string);
                    } else {
                        map = null;
                    }
                    if (remoteMessage2 == null) {
                        taskCompletionSource.setResult(null);
                        return;
                    }
                    this.consumedInitialMessages.put(string, true);
                    Map<String, Object> remoteMessageToMap2 = FlutterFirebaseMessagingUtils.remoteMessageToMap(remoteMessage2);
                    if (remoteMessage2.getNotification() == null && map != null) {
                        remoteMessageToMap2.put("notification", map);
                    }
                    taskCompletionSource.setResult(remoteMessageToMap2);
                    return;
                }
                taskCompletionSource.setResult(null);
                return;
            }
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Integer>> requestPermissions() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.this.m1083x7111fdcf(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$requestPermissions$12$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1083x7111fdcf(final TaskCompletionSource taskCompletionSource) {
        final HashMap hashMap = new HashMap();
        try {
            if (!checkPermissions().booleanValue()) {
                this.permissionManager.requestPermissions(this.mainActivity, new FlutterFirebasePermissionManager.RequestPermissionsSuccessCallback() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda11
                    @Override // io.flutter.plugins.firebase.messaging.FlutterFirebasePermissionManager.RequestPermissionsSuccessCallback
                    public final void onSuccess(int i) {
                        FlutterFirebaseMessagingPlugin.lambda$requestPermissions$10(hashMap, taskCompletionSource, i);
                    }
                }, new ErrorCallback() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda10
                    @Override // io.flutter.plugins.firebase.messaging.ErrorCallback
                    public final void onError(String str) {
                        TaskCompletionSource.this.setException(new Exception(str));
                    }
                });
            } else {
                hashMap.put("authorizationStatus", 1);
                taskCompletionSource.setResult(hashMap);
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$requestPermissions$10(Map map, TaskCompletionSource taskCompletionSource, int i) {
        map.put("authorizationStatus", Integer.valueOf(i));
        taskCompletionSource.setResult(map);
    }

    private Boolean checkPermissions() {
        return Boolean.valueOf(ContextHolder.getApplicationContext().checkSelfPermission("android.permission.POST_NOTIFICATIONS") == 0);
    }

    private Task<Map<String, Integer>> getPermissions() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.this.m1078x6f4ba4c7(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getPermissions$13$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1078x6f4ba4c7(TaskCompletionSource taskCompletionSource) {
        boolean areNotificationsEnabled;
        try {
            HashMap hashMap = new HashMap();
            if (Build.VERSION.SDK_INT >= 33) {
                areNotificationsEnabled = checkPermissions().booleanValue();
            } else {
                areNotificationsEnabled = NotificationManagerCompat.from(this.mainActivity).areNotificationsEnabled();
            }
            hashMap.put("authorizationStatus", Integer.valueOf(areNotificationsEnabled ? 1 : 0));
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task initialMessage;
        long longValue;
        long longValue2;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1704007366:
                if (str.equals("Messaging#getInitialMessage")) {
                    c = 0;
                    break;
                }
                break;
            case -1661255137:
                if (str.equals("Messaging#setAutoInitEnabled")) {
                    c = 1;
                    break;
                }
                break;
            case -657665041:
                if (str.equals("Messaging#deleteToken")) {
                    c = 2;
                    break;
                }
                break;
            case 421314579:
                if (str.equals("Messaging#unsubscribeFromTopic")) {
                    c = 3;
                    break;
                }
                break;
            case 506322569:
                if (str.equals("Messaging#subscribeToTopic")) {
                    c = 4;
                    break;
                }
                break;
            case 607887267:
                if (str.equals("Messaging#setDeliveryMetricsExportToBigQuery")) {
                    c = 5;
                    break;
                }
                break;
            case 928431066:
                if (str.equals("Messaging#startBackgroundIsolate")) {
                    c = 6;
                    break;
                }
                break;
            case 1165917248:
                if (str.equals("Messaging#sendMessage")) {
                    c = 7;
                    break;
                }
                break;
            case 1231338975:
                if (str.equals("Messaging#requestPermission")) {
                    c = '\b';
                    break;
                }
                break;
            case 1243856389:
                if (str.equals("Messaging#getNotificationSettings")) {
                    c = '\t';
                    break;
                }
                break;
            case 1459336962:
                if (str.equals("Messaging#getToken")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                initialMessage = getInitialMessage();
                break;
            case 1:
                initialMessage = setAutoInitEnabled((Map) methodCall.arguments());
                break;
            case 2:
                initialMessage = deleteToken();
                break;
            case 3:
                initialMessage = unsubscribeFromTopic((Map) methodCall.arguments());
                break;
            case 4:
                initialMessage = subscribeToTopic((Map) methodCall.arguments());
                break;
            case 5:
                initialMessage = setDeliveryMetricsExportToBigQuery((Map) methodCall.arguments());
                break;
            case 6:
                Map map = (Map) methodCall.arguments;
                Object obj = map.get("pluginCallbackHandle");
                Object obj2 = map.get("userCallbackHandle");
                if (obj instanceof Long) {
                    longValue = ((Long) obj).longValue();
                } else if (obj instanceof Integer) {
                    longValue = Long.valueOf(((Integer) obj).intValue()).longValue();
                } else {
                    throw new IllegalArgumentException("Expected 'Long' or 'Integer' type for 'pluginCallbackHandle'.");
                }
                if (obj2 instanceof Long) {
                    longValue2 = ((Long) obj2).longValue();
                } else if (obj2 instanceof Integer) {
                    longValue2 = Long.valueOf(((Integer) obj2).intValue()).longValue();
                } else {
                    throw new IllegalArgumentException("Expected 'Long' or 'Integer' type for 'userCallbackHandle'.");
                }
                Activity activity = this.mainActivity;
                FlutterShellArgs fromIntent = activity != null ? FlutterShellArgs.fromIntent(activity.getIntent()) : null;
                FlutterFirebaseMessagingBackgroundService.setCallbackDispatcher(longValue);
                FlutterFirebaseMessagingBackgroundService.setUserCallbackHandle(longValue2);
                FlutterFirebaseMessagingBackgroundService.startBackgroundIsolate(longValue, fromIntent);
                initialMessage = Tasks.forResult(null);
                break;
            case 7:
                initialMessage = sendMessage((Map) methodCall.arguments());
                break;
            case '\b':
                if (Build.VERSION.SDK_INT >= 33) {
                    initialMessage = requestPermissions();
                    break;
                } else {
                    initialMessage = getPermissions();
                    break;
                }
            case '\t':
                initialMessage = getPermissions();
                break;
            case '\n':
                initialMessage = getToken();
                break;
            default:
                result.notImplemented();
                return;
        }
        initialMessage.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseMessagingPlugin.this.m1082xbbae7b76(result, task);
            }
        });
    }

    /* renamed from: lambda$onMethodCall$14$io-flutter-plugins-firebase-messaging-FlutterFirebaseMessagingPlugin, reason: not valid java name */
    public /* synthetic */ void m1082xbbae7b76(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
        } else {
            Exception exception = task.getException();
            result.error("firebase_messaging", exception != null ? exception.getMessage() : null, getExceptionDetails(exception));
        }
    }

    private Map<String, Object> getExceptionDetails(Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("code", "unknown");
        if (exc != null) {
            hashMap.put("message", exc.getMessage());
        } else {
            hashMap.put("message", "An unknown error has occurred.");
        }
        return hashMap;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.NewIntentListener
    public boolean onNewIntent(Intent intent) {
        Map<String, Object> map;
        Map<String, Object> firebaseMessageMap;
        if (intent.getExtras() == null) {
            return false;
        }
        String string = intent.getExtras().getString(Constants.MessagePayloadKeys.MSGID);
        if (string == null) {
            string = intent.getExtras().getString(Constants.MessagePayloadKeys.MSGID_SERVER);
        }
        if (string == null) {
            return false;
        }
        RemoteMessage remoteMessage = FlutterFirebaseMessagingReceiver.notifications.get(string);
        Map<String, Object> map2 = null;
        if (remoteMessage == null && (firebaseMessageMap = FlutterFirebaseMessagingStore.getInstance().getFirebaseMessageMap(string)) != null) {
            remoteMessage = FlutterFirebaseMessagingUtils.getRemoteMessageForArguments(firebaseMessageMap);
            map2 = FlutterFirebaseMessagingUtils.getRemoteMessageNotificationForArguments(firebaseMessageMap);
        }
        if (remoteMessage == null) {
            return false;
        }
        this.initialMessage = remoteMessage;
        this.initialMessageNotification = map2;
        FlutterFirebaseMessagingReceiver.notifications.remove(string);
        Map<String, Object> remoteMessageToMap = FlutterFirebaseMessagingUtils.remoteMessageToMap(remoteMessage);
        if (remoteMessage.getNotification() == null && (map = this.initialMessageNotification) != null) {
            remoteMessageToMap.put("notification", map);
        }
        this.channel.invokeMethod("Messaging#onMessageOpenedApp", remoteMessageToMap);
        this.mainActivity.setIntent(intent);
        return true;
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(final FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseMessagingPlugin.lambda$getPluginConstantsForFirebaseApp$15(FirebaseApp.this, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPluginConstantsForFirebaseApp$15(FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        try {
            HashMap hashMap = new HashMap();
            if (firebaseApp.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                hashMap.put("AUTO_INIT_ENABLED", Boolean.valueOf(FirebaseMessaging.getInstance().isAutoInitEnabled()));
            }
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.messaging.FlutterFirebaseMessagingPlugin$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                TaskCompletionSource.this.setResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }
}
