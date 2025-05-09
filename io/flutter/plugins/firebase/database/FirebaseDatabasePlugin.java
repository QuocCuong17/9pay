package io.flutter.plugins.firebase.database;

import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.OnDisconnect;
import com.google.firebase.database.Query;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class FirebaseDatabasePlugin implements FlutterFirebasePlugin, FlutterPlugin, MethodChannel.MethodCallHandler {
    private static final String METHOD_CHANNEL_NAME = "plugins.flutter.io/firebase_database";
    protected static final HashMap<String, FirebaseDatabase> databaseInstanceCache = new HashMap<>();
    private BinaryMessenger messenger;
    private MethodChannel methodChannel;
    private int listenerCount = 0;
    private final Map<EventChannel, EventChannel.StreamHandler> streamHandlers = new HashMap();

    private static FirebaseDatabase getCachedFirebaseDatabaseInstanceForKey(String str) {
        FirebaseDatabase firebaseDatabase;
        HashMap<String, FirebaseDatabase> hashMap = databaseInstanceCache;
        synchronized (hashMap) {
            firebaseDatabase = hashMap.get(str);
        }
        return firebaseDatabase;
    }

    private static void setCachedFirebaseDatabaseInstanceForKey(FirebaseDatabase firebaseDatabase, String str) {
        HashMap<String, FirebaseDatabase> hashMap = databaseInstanceCache;
        synchronized (hashMap) {
            if (hashMap.get(str) == null) {
                hashMap.put(str, firebaseDatabase);
            }
        }
    }

    private void initPluginInstance(BinaryMessenger binaryMessenger) {
        FlutterFirebasePluginRegistry.registerPlugin(METHOD_CHANNEL_NAME, this);
        this.messenger = binaryMessenger;
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, METHOD_CHANNEL_NAME);
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    FirebaseDatabase getDatabase(Map<String, Object> map) {
        FirebaseDatabase firebaseDatabase;
        String str = (String) map.get("appName");
        if (str == null) {
            str = FirebaseApp.DEFAULT_APP_NAME;
        }
        String str2 = (String) map.get(Constants.DATABASE_URL);
        if (str2 == null) {
            str2 = "";
        }
        String concat = str.concat(str2);
        FirebaseDatabase cachedFirebaseDatabaseInstanceForKey = getCachedFirebaseDatabaseInstanceForKey(concat);
        if (cachedFirebaseDatabaseInstanceForKey != null) {
            return cachedFirebaseDatabaseInstanceForKey;
        }
        FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        if (!str2.isEmpty()) {
            firebaseDatabase = FirebaseDatabase.getInstance(firebaseApp, str2);
        } else {
            firebaseDatabase = FirebaseDatabase.getInstance(firebaseApp);
        }
        Boolean bool = (Boolean) map.get(Constants.DATABASE_LOGGING_ENABLED);
        Boolean bool2 = (Boolean) map.get(Constants.DATABASE_PERSISTENCE_ENABLED);
        String str3 = (String) map.get(Constants.DATABASE_EMULATOR_HOST);
        Integer num = (Integer) map.get(Constants.DATABASE_EMULATOR_PORT);
        Object obj = map.get(Constants.DATABASE_CACHE_SIZE_BYTES);
        if (bool != null) {
            try {
                firebaseDatabase.setLogLevel(bool.booleanValue() ? Logger.Level.DEBUG : Logger.Level.NONE);
            } catch (DatabaseException e) {
                String message = e.getMessage();
                if (message == null) {
                    throw e;
                }
                if (!message.contains("must be made before any other usage of FirebaseDatabase")) {
                    throw e;
                }
            }
        }
        if (str3 != null && num != null) {
            firebaseDatabase.useEmulator(str3, num.intValue());
        }
        if (bool2 != null) {
            firebaseDatabase.setPersistenceEnabled(bool2.booleanValue());
        }
        if (obj != null) {
            if (obj instanceof Long) {
                firebaseDatabase.setPersistenceCacheSizeBytes(((Long) obj).longValue());
            } else if (obj instanceof Integer) {
                firebaseDatabase.setPersistenceCacheSizeBytes(Long.valueOf(((Integer) obj).intValue()).longValue());
            }
        }
        setCachedFirebaseDatabaseInstanceForKey(firebaseDatabase, concat);
        return firebaseDatabase;
    }

    private DatabaseReference getReference(Map<String, Object> map) {
        FirebaseDatabase database = getDatabase(map);
        Object obj = map.get(Constants.PATH);
        Objects.requireNonNull(obj);
        return database.getReference((String) obj);
    }

    private Query getQuery(Map<String, Object> map) {
        DatabaseReference reference = getReference(map);
        Object obj = map.get(Constants.MODIFIERS);
        Objects.requireNonNull(obj);
        return new QueryBuilder(reference, (List) obj).build();
    }

    private Task<Void> goOnline(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1049xc12096c6(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$goOnline$0$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1049xc12096c6(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            getDatabase(map).goOnline();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> goOffline(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1048xf4e7726d(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$goOffline$1$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1048xf4e7726d(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            getDatabase(map).goOffline();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> purgeOutstandingWrites(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1051x7b9dfef0(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$purgeOutstandingWrites$2$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1051x7b9dfef0(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            getDatabase(map).purgeOutstandingWrites();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setValue(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1057xfd72ee1d(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setValue$3$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1057xfd72ee1d(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(getReference(map).setValue(map.get("value")));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setValueWithPriority(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1058xe1d85bc8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setValueWithPriority$4$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1058xe1d85bc8(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(getReference(map).setValue(map.get("value"), map.get("priority")));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> update(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1060x2087df79(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$update$5$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1060x2087df79(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            DatabaseReference reference = getReference(map);
            Object obj = map.get("value");
            Objects.requireNonNull(obj);
            Tasks.await(reference.updateChildren((Map) obj));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setPriority(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1056xab252b3d(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setPriority$6$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1056xab252b3d(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(getReference(map).setPriority(map.get("priority")));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> runTransaction(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1054xa1b1f085(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$runTransaction$7$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1054xa1b1f085(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            DatabaseReference reference = getReference(map);
            Object obj = map.get(Constants.TRANSACTION_KEY);
            Objects.requireNonNull(obj);
            int intValue = ((Integer) obj).intValue();
            Object obj2 = map.get(Constants.TRANSACTION_APPLY_LOCALLY);
            Objects.requireNonNull(obj2);
            boolean booleanValue = ((Boolean) obj2).booleanValue();
            TransactionHandler transactionHandler = new TransactionHandler(this.methodChannel, intValue);
            reference.runTransaction(transactionHandler, booleanValue);
            taskCompletionSource.setResult((Map) Tasks.await(transactionHandler.getTask()));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> queryGet(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1052x3aeff3a1(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$queryGet$8$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1052x3aeff3a1(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(new FlutterDataSnapshotPayload((DataSnapshot) Tasks.await(getQuery(map).get())).toMap());
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> queryKeepSynced(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1053xe94a7ea1(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$queryKeepSynced$9$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1053xe94a7ea1(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Query query = getQuery(map);
            Object obj = map.get("value");
            Objects.requireNonNull(obj);
            query.keepSynced(((Boolean) obj).booleanValue());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<String> observe(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1050xf54752c7(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$observe$11$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1050xf54752c7(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Query query = getQuery(map);
            String str = (String) map.get(Constants.EVENT_CHANNEL_NAME_PREFIX);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("#");
            int i = this.listenerCount;
            this.listenerCount = i + 1;
            sb.append(i);
            String sb2 = sb.toString();
            final EventChannel eventChannel = new EventChannel(this.messenger, sb2);
            EventStreamHandler eventStreamHandler = new EventStreamHandler(query, new OnDispose() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda10
                @Override // io.flutter.plugins.firebase.database.OnDispose
                public final void run() {
                    EventChannel.this.setStreamHandler(null);
                }
            });
            eventChannel.setStreamHandler(eventStreamHandler);
            this.streamHandlers.put(eventChannel, eventStreamHandler);
            taskCompletionSource.setResult(sb2);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setOnDisconnect(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1055xa17c2de7(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setOnDisconnect$12$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1055xa17c2de7(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(getReference(map).onDisconnect().setValue(map.get("value")));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setWithPriorityOnDisconnect(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1059xfa1342fe(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$setWithPriorityOnDisconnect$13$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1059xfa1342fe(Map map, TaskCompletionSource taskCompletionSource) {
        Task<Void> value;
        try {
            Object obj = map.get("value");
            Object obj2 = map.get("priority");
            OnDisconnect onDisconnect = getReference(map).onDisconnect();
            if (obj2 instanceof Double) {
                value = onDisconnect.setValue(obj, ((Number) obj2).doubleValue());
            } else if (obj2 instanceof String) {
                value = onDisconnect.setValue(obj, (String) obj2);
            } else if (obj2 == null) {
                value = onDisconnect.setValue(obj, (String) null);
            } else {
                throw new Exception("Invalid priority value for OnDisconnect.setWithPriority");
            }
            Tasks.await(value);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> updateOnDisconnect(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1061xb9d82a08(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$updateOnDisconnect$14$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1061xb9d82a08(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            DatabaseReference reference = getReference(map);
            Object obj = map.get("value");
            Objects.requireNonNull(obj);
            Tasks.await(reference.onDisconnect().updateChildren((Map) obj));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> cancelOnDisconnect(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1046xe6a71438(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$cancelOnDisconnect$15$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1046xe6a71438(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(getReference(map).onDisconnect().cancel());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(final MethodCall methodCall, final MethodChannel.Result result) {
        Task runTransaction;
        Map<String, Object> map = (Map) methodCall.arguments();
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2082411450:
                if (str.equals("DatabaseReference#runTransaction")) {
                    c = 0;
                    break;
                }
                break;
            case -2059578349:
                if (str.equals("DatabaseReference#setPriority")) {
                    c = 1;
                    break;
                }
                break;
            case -1666493916:
                if (str.equals("FirebaseDatabase#purgeOutstandingWrites")) {
                    c = 2;
                    break;
                }
                break;
            case -1481870471:
                if (str.equals("DatabaseReference#setWithPriority")) {
                    c = 3;
                    break;
                }
                break;
            case -858161988:
                if (str.equals("DatabaseReference#update")) {
                    c = 4;
                    break;
                }
                break;
            case -526424742:
                if (str.equals("FirebaseDatabase#goOffline")) {
                    c = 5;
                    break;
                }
                break;
            case -429179942:
                if (str.equals("OnDisconnect#set")) {
                    c = 6;
                    break;
                }
                break;
            case -43852798:
                if (str.equals("OnDisconnect#cancel")) {
                    c = 7;
                    break;
                }
                break;
            case 195628283:
                if (str.equals("Query#get")) {
                    c = '\b';
                    break;
                }
                break;
            case 272980762:
                if (str.equals("Query#keepSynced")) {
                    c = '\t';
                    break;
                }
                break;
            case 485025361:
                if (str.equals("OnDisconnect#update")) {
                    c = '\n';
                    break;
                }
                break;
            case 734082383:
                if (str.equals("DatabaseReference#set")) {
                    c = 11;
                    break;
                }
                break;
            case 1185022340:
                if (str.equals("OnDisconnect#setWithPriority")) {
                    c = '\f';
                    break;
                }
                break;
            case 1653150716:
                if (str.equals("FirebaseDatabase#goOnline")) {
                    c = '\r';
                    break;
                }
                break;
            case 1749611585:
                if (str.equals("Query#observe")) {
                    c = 14;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                runTransaction = runTransaction(map);
                break;
            case 1:
                runTransaction = setPriority(map);
                break;
            case 2:
                runTransaction = purgeOutstandingWrites(map);
                break;
            case 3:
                runTransaction = setValueWithPriority(map);
                break;
            case 4:
                runTransaction = update(map);
                break;
            case 5:
                runTransaction = goOffline(map);
                break;
            case 6:
                runTransaction = setOnDisconnect(map);
                break;
            case 7:
                runTransaction = cancelOnDisconnect(map);
                break;
            case '\b':
                runTransaction = queryGet(map);
                break;
            case '\t':
                runTransaction = queryKeepSynced(map);
                break;
            case '\n':
                runTransaction = updateOnDisconnect(map);
                break;
            case 11:
                runTransaction = setValue(map);
                break;
            case '\f':
                runTransaction = setWithPriorityOnDisconnect(map);
                break;
            case '\r':
                runTransaction = goOnline(map);
                break;
            case 14:
                runTransaction = observe(map);
                break;
            default:
                result.notImplemented();
                return;
        }
        runTransaction.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FirebaseDatabasePlugin.lambda$onMethodCall$16(MethodChannel.Result.this, methodCall, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$16(MethodChannel.Result result, MethodCall methodCall, Task task) {
        FlutterFirebaseDatabaseException fromException;
        if (task.isSuccessful()) {
            result.success(task.getResult());
            return;
        }
        Exception exception = task.getException();
        if (exception instanceof FlutterFirebaseDatabaseException) {
            fromException = (FlutterFirebaseDatabaseException) exception;
        } else if (exception instanceof DatabaseException) {
            fromException = FlutterFirebaseDatabaseException.fromDatabaseException((DatabaseException) exception);
        } else {
            Log.e("firebase_database", "An unknown error occurred handling native method call " + methodCall.method, exception);
            fromException = FlutterFirebaseDatabaseException.fromException(exception);
        }
        result.error(fromException.getCode(), fromException.getMessage(), fromException.getAdditionalData());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initPluginInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.methodChannel.setMethodCallHandler(null);
        cleanup();
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.lambda$getPluginConstantsForFirebaseApp$17(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPluginConstantsForFirebaseApp$17(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(new HashMap());
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.database.FirebaseDatabasePlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseDatabasePlugin.this.m1047x2ae255e2(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$didReinitializeFirebaseCore$18$io-flutter-plugins-firebase-database-FirebaseDatabasePlugin, reason: not valid java name */
    public /* synthetic */ void m1047x2ae255e2(TaskCompletionSource taskCompletionSource) {
        try {
            cleanup();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private void cleanup() {
        removeEventStreamHandlers();
        databaseInstanceCache.clear();
    }

    private void removeEventStreamHandlers() {
        for (EventChannel eventChannel : this.streamHandlers.keySet()) {
            EventChannel.StreamHandler streamHandler = this.streamHandlers.get(eventChannel);
            if (streamHandler != null) {
                streamHandler.onCancel(null);
                eventChannel.setStreamHandler(null);
            }
        }
        this.streamHandlers.clear();
    }
}
