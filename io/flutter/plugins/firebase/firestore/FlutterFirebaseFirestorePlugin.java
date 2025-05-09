package io.flutter.plugins.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.sessions.settings.RemoteSettings;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.StandardMethodCodec;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore;
import io.flutter.plugins.firebase.firestore.streamhandler.DocumentSnapshotsStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.LoadBundleStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.OnTransactionResultListener;
import io.flutter.plugins.firebase.firestore.streamhandler.QuerySnapshotsStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.SnapshotsInSyncStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import io.flutter.plugins.firebase.firestore.utils.PigeonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class FlutterFirebaseFirestorePlugin implements FlutterFirebasePlugin, FlutterPlugin, ActivityAware, GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_ERROR_CODE = "firebase_firestore";
    private static final String METHOD_CHANNEL_NAME = "plugins.flutter.io/firebase_firestore";
    protected static final HashMap<FirebaseFirestore, FlutterFirebaseFirestoreExtension> firestoreInstanceCache = new HashMap<>();
    public static final Map<Integer, DocumentSnapshot.ServerTimestampBehavior> serverTimestampBehaviorHashMap = new HashMap();
    private BinaryMessenger binaryMessenger;
    final StandardMethodCodec MESSAGE_CODEC = new StandardMethodCodec(FlutterFirebaseFirestoreMessageCodec.INSTANCE);
    private final AtomicReference<Activity> activity = new AtomicReference<>(null);
    private final Map<String, Transaction> transactions = new HashMap();
    private final Map<String, EventChannel> eventChannels = new HashMap();
    private final Map<String, EventChannel.StreamHandler> streamHandlers = new HashMap();
    private final Map<String, OnTransactionResultListener> transactionHandlers = new HashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public static FlutterFirebaseFirestoreExtension getCachedFirebaseFirestoreInstanceForKey(FirebaseFirestore firebaseFirestore) {
        FlutterFirebaseFirestoreExtension flutterFirebaseFirestoreExtension;
        HashMap<FirebaseFirestore, FlutterFirebaseFirestoreExtension> hashMap = firestoreInstanceCache;
        synchronized (hashMap) {
            flutterFirebaseFirestoreExtension = hashMap.get(firebaseFirestore);
        }
        return flutterFirebaseFirestoreExtension;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setCachedFirebaseFirestoreInstanceForKey(FirebaseFirestore firebaseFirestore, String str) {
        HashMap<FirebaseFirestore, FlutterFirebaseFirestoreExtension> hashMap = firestoreInstanceCache;
        synchronized (hashMap) {
            if (hashMap.get(firebaseFirestore) == null) {
                hashMap.put(firebaseFirestore, new FlutterFirebaseFirestoreExtension(firebaseFirestore, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static FirebaseFirestore getFirestoreInstanceByNameAndDatabaseUrl(String str, String str2) {
        for (Map.Entry<FirebaseFirestore, FlutterFirebaseFirestoreExtension> entry : firestoreInstanceCache.entrySet()) {
            if (entry.getValue().getInstance().getApp().getName().equals(str) && entry.getValue().getDatabaseURL().equals(str2)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void destroyCachedFirebaseFirestoreInstanceForKey(FirebaseFirestore firebaseFirestore) {
        HashMap<FirebaseFirestore, FlutterFirebaseFirestoreExtension> hashMap = firestoreInstanceCache;
        synchronized (hashMap) {
            if (hashMap.get(firebaseFirestore) != null) {
                hashMap.remove(firebaseFirestore);
            }
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        removeEventListeners();
        this.binaryMessenger = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        attachToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        detachToActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        attachToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        detachToActivity();
    }

    private void attachToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activity.set(activityPluginBinding.getActivity());
    }

    private void detachToActivity() {
        this.activity.set(null);
    }

    private void initInstance(BinaryMessenger binaryMessenger) {
        this.binaryMessenger = binaryMessenger;
        FlutterFirebasePluginRegistry.registerPlugin(METHOD_CHANNEL_NAME, this);
        GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi.CC.setup(this.binaryMessenger, this);
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$getPluginConstantsForFirebaseApp$0(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPluginConstantsForFirebaseApp$0(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m1067x4279566a(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$didReinitializeFirebaseCore$1$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m1067x4279566a(TaskCompletionSource taskCompletionSource) {
        try {
            Iterator<Map.Entry<FirebaseFirestore, FlutterFirebaseFirestoreExtension>> it = firestoreInstanceCache.entrySet().iterator();
            while (it.hasNext()) {
                FirebaseFirestore key = it.next().getKey();
                Tasks.await(key.terminate());
                destroyCachedFirebaseFirestoreInstanceForKey(key);
            }
            removeEventListeners();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private String registerEventChannel(String str, EventChannel.StreamHandler streamHandler) {
        return registerEventChannel(str, UUID.randomUUID().toString().toLowerCase(Locale.US), streamHandler);
    }

    private String registerEventChannel(String str, String str2, EventChannel.StreamHandler streamHandler) {
        EventChannel eventChannel = new EventChannel(this.binaryMessenger, str + RemoteSettings.FORWARD_SLASH_STRING + str2, this.MESSAGE_CODEC);
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
        this.transactionHandlers.clear();
    }

    static FirebaseFirestoreSettings getSettingsFromPigeon(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp) {
        FirebaseFirestoreSettings.Builder builder = new FirebaseFirestoreSettings.Builder();
        if (firestorePigeonFirebaseApp.getSettings().getHost() != null) {
            builder.setHost(firestorePigeonFirebaseApp.getSettings().getHost());
        }
        if (firestorePigeonFirebaseApp.getSettings().getSslEnabled() != null) {
            builder.setSslEnabled(firestorePigeonFirebaseApp.getSettings().getSslEnabled().booleanValue());
        }
        if (firestorePigeonFirebaseApp.getSettings().getPersistenceEnabled() != null) {
            if (firestorePigeonFirebaseApp.getSettings().getPersistenceEnabled().booleanValue()) {
                builder.setLocalCacheSettings(PersistentCacheSettings.newBuilder().setSizeBytes(firestorePigeonFirebaseApp.getSettings().getCacheSizeBytes().longValue()).build());
            } else {
                builder.setLocalCacheSettings(MemoryCacheSettings.newBuilder().build());
            }
        }
        return builder.build();
    }

    public static FirebaseFirestore getFirestoreFromPigeon(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp) {
        synchronized (firestoreInstanceCache) {
            if (getFirestoreInstanceByNameAndDatabaseUrl(firestorePigeonFirebaseApp.getAppName(), firestorePigeonFirebaseApp.getDatabaseURL()) != null) {
                return getFirestoreInstanceByNameAndDatabaseUrl(firestorePigeonFirebaseApp.getAppName(), firestorePigeonFirebaseApp.getDatabaseURL());
            }
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance(FirebaseApp.getInstance(firestorePigeonFirebaseApp.getAppName()), firestorePigeonFirebaseApp.getDatabaseURL());
            firebaseFirestore.setFirestoreSettings(getSettingsFromPigeon(firestorePigeonFirebaseApp));
            setCachedFirebaseFirestoreInstanceForKey(firebaseFirestore, firestorePigeonFirebaseApp.getDatabaseURL());
            return firebaseFirestore;
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void loadBundle(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, byte[] bArr, GeneratedAndroidFirebaseFirestore.Result<String> result) {
        result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/loadBundle", new LoadBundleStreamHandler(getFirestoreFromPigeon(firestorePigeonFirebaseApp), bArr)));
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void namedQueryGet(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final String str, final GeneratedAndroidFirebaseFirestore.PigeonGetOptions pigeonGetOptions, final GeneratedAndroidFirebaseFirestore.Result<GeneratedAndroidFirebaseFirestore.PigeonQuerySnapshot> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$namedQueryGet$2(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, str, result, pigeonGetOptions);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$namedQueryGet$2(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, String str, GeneratedAndroidFirebaseFirestore.Result result, GeneratedAndroidFirebaseFirestore.PigeonGetOptions pigeonGetOptions) {
        try {
            Query query = (Query) Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).getNamedQuery(str));
            if (query == null) {
                result.error(new NullPointerException("Named query has not been found. Please check it has been loaded properly via loadBundle()."));
            } else {
                result.success(PigeonParser.toPigeonQuerySnapshot((QuerySnapshot) Tasks.await(query.get(PigeonParser.parsePigeonSource(pigeonGetOptions.getSource()))), PigeonParser.parsePigeonServerTimestampBehavior(pigeonGetOptions.getServerTimestampBehavior())));
            }
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void clearPersistence(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$clearPersistence$3(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clearPersistence$3(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).clearPersistence());
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void disableNetwork(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$disableNetwork$4(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$disableNetwork$4(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).disableNetwork());
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void enableNetwork(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$enableNetwork$5(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$enableNetwork$5(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).enableNetwork());
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void terminate(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$terminate$6(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$terminate$6(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            FirebaseFirestore firestoreFromPigeon = getFirestoreFromPigeon(firestorePigeonFirebaseApp);
            Tasks.await(firestoreFromPigeon.terminate());
            destroyCachedFirebaseFirestoreInstanceForKey(firestoreFromPigeon);
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void waitForPendingWrites(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$waitForPendingWrites$7(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$waitForPendingWrites$7(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).waitForPendingWrites());
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void setIndexConfiguration(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final String str, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$setIndexConfiguration$8(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, str, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setIndexConfiguration$8(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, String str, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).setIndexConfiguration(str));
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void setLoggingEnabled(final Boolean bool, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$setLoggingEnabled$9(bool, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setLoggingEnabled$9(Boolean bool, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            FirebaseFirestore.setLoggingEnabled(bool.booleanValue());
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void snapshotsInSyncSetup(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result<String> result) {
        result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/snapshotsInSync", new SnapshotsInSyncStreamHandler(getFirestoreFromPigeon(firestorePigeonFirebaseApp))));
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void transactionCreate(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, Long l, Long l2, GeneratedAndroidFirebaseFirestore.Result<String> result) {
        FirebaseFirestore firestoreFromPigeon = getFirestoreFromPigeon(firestorePigeonFirebaseApp);
        final String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        TransactionStreamHandler transactionStreamHandler = new TransactionStreamHandler(new TransactionStreamHandler.OnTransactionStartedListener() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler.OnTransactionStartedListener
            public final void onStarted(Transaction transaction) {
                FlutterFirebaseFirestorePlugin.this.m1068x13be96dc(lowerCase, transaction);
            }
        }, firestoreFromPigeon, lowerCase, l, l2);
        registerEventChannel("plugins.flutter.io/firebase_firestore/transaction", lowerCase, transactionStreamHandler);
        this.transactionHandlers.put(lowerCase, transactionStreamHandler);
        result.success(lowerCase);
    }

    /* renamed from: lambda$transactionCreate$10$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m1068x13be96dc(String str, Transaction transaction) {
        this.transactions.put(str, transaction);
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void transactionStoreResult(String str, GeneratedAndroidFirebaseFirestore.PigeonTransactionResult pigeonTransactionResult, List<GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand> list, GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        OnTransactionResultListener onTransactionResultListener = this.transactionHandlers.get(str);
        Objects.requireNonNull(onTransactionResultListener);
        onTransactionResultListener.receiveTransactionResponse(pigeonTransactionResult, list);
        result.success(null);
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void transactionGet(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final String str, final String str2, final GeneratedAndroidFirebaseFirestore.Result<GeneratedAndroidFirebaseFirestore.PigeonDocumentSnapshot> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m1069xd27c87b3(firestorePigeonFirebaseApp, str2, str, result);
            }
        });
    }

    /* renamed from: lambda$transactionGet$11$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m1069xd27c87b3(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, String str, String str2, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            DocumentReference document = getFirestoreFromPigeon(firestorePigeonFirebaseApp).document(str);
            Transaction transaction = this.transactions.get(str2);
            if (transaction == null) {
                result.error(new Exception("Transaction.getDocument(): No transaction handler exists for ID: " + str2));
                return;
            }
            result.success(PigeonParser.toPigeonDocumentSnapshot(transaction.get(document), DocumentSnapshot.ServerTimestampBehavior.NONE));
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void documentReferenceSet(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentReferenceSet$12(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, documentReferenceRequest, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentReferenceSet$12(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, GeneratedAndroidFirebaseFirestore.Result result) {
        Task<Void> task;
        try {
            DocumentReference document = getFirestoreFromPigeon(firestorePigeonFirebaseApp).document(documentReferenceRequest.getPath());
            Map<Object, Object> data = documentReferenceRequest.getData();
            Objects.requireNonNull(data);
            Map<Object, Object> map = data;
            if (documentReferenceRequest.getOption().getMerge() != null && documentReferenceRequest.getOption().getMerge().booleanValue()) {
                task = document.set(data, SetOptions.merge());
            } else if (documentReferenceRequest.getOption().getMergeFields() != null) {
                List<List<String>> mergeFields = documentReferenceRequest.getOption().getMergeFields();
                Objects.requireNonNull(mergeFields);
                List<List<String>> list = mergeFields;
                task = document.set(data, SetOptions.mergeFieldPaths(PigeonParser.parseFieldPath(mergeFields)));
            } else {
                task = document.set(data);
            }
            result.success((Void) Tasks.await(task));
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void documentReferenceUpdate(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentReferenceUpdate$13(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, documentReferenceRequest, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentReferenceUpdate$13(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            DocumentReference document = getFirestoreFromPigeon(firestorePigeonFirebaseApp).document(documentReferenceRequest.getPath());
            Map<Object, Object> data = documentReferenceRequest.getData();
            Objects.requireNonNull(data);
            Map<Object, Object> map = data;
            HashMap hashMap = new HashMap();
            for (Object obj : data.keySet()) {
                if (obj instanceof String) {
                    hashMap.put(FieldPath.of((String) obj), data.get(obj));
                } else if (obj instanceof FieldPath) {
                    hashMap.put((FieldPath) obj, data.get(obj));
                } else {
                    throw new IllegalArgumentException("Invalid key type in update data. Supported types are String and FieldPath.");
                }
            }
            FieldPath fieldPath = (FieldPath) hashMap.keySet().iterator().next();
            Object obj2 = hashMap.get(fieldPath);
            ArrayList arrayList = new ArrayList();
            for (FieldPath fieldPath2 : hashMap.keySet()) {
                if (!fieldPath2.equals(fieldPath)) {
                    arrayList.add(fieldPath2);
                    arrayList.add(hashMap.get(fieldPath2));
                }
            }
            result.success((Void) Tasks.await(document.update(fieldPath, obj2, arrayList.toArray())));
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void documentReferenceGet(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, final GeneratedAndroidFirebaseFirestore.Result<GeneratedAndroidFirebaseFirestore.PigeonDocumentSnapshot> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentReferenceGet$14(GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest.this, firestorePigeonFirebaseApp, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentReferenceGet$14(GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            result.success(PigeonParser.toPigeonDocumentSnapshot((DocumentSnapshot) Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).document(documentReferenceRequest.getPath()).get(PigeonParser.parsePigeonSource(documentReferenceRequest.getSource()))), PigeonParser.parsePigeonServerTimestampBehavior(documentReferenceRequest.getServerTimestampBehavior())));
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void documentReferenceDelete(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentReferenceDelete$15(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, documentReferenceRequest, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentReferenceDelete$15(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            result.success((Void) Tasks.await(getFirestoreFromPigeon(firestorePigeonFirebaseApp).document(documentReferenceRequest.getPath()).delete()));
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void queryGet(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final String str, final Boolean bool, final GeneratedAndroidFirebaseFirestore.PigeonQueryParameters pigeonQueryParameters, final GeneratedAndroidFirebaseFirestore.PigeonGetOptions pigeonGetOptions, final GeneratedAndroidFirebaseFirestore.Result<GeneratedAndroidFirebaseFirestore.PigeonQuerySnapshot> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$queryGet$16(GeneratedAndroidFirebaseFirestore.PigeonGetOptions.this, firestorePigeonFirebaseApp, str, bool, pigeonQueryParameters, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$queryGet$16(GeneratedAndroidFirebaseFirestore.PigeonGetOptions pigeonGetOptions, GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, String str, Boolean bool, GeneratedAndroidFirebaseFirestore.PigeonQueryParameters pigeonQueryParameters, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            Source parsePigeonSource = PigeonParser.parsePigeonSource(pigeonGetOptions.getSource());
            Query parseQuery = PigeonParser.parseQuery(getFirestoreFromPigeon(firestorePigeonFirebaseApp), str, bool.booleanValue(), pigeonQueryParameters);
            if (parseQuery == null) {
                result.error(new GeneratedAndroidFirebaseFirestore.FlutterError("invalid_query", "An error occurred while parsing query arguments, see native logs for more information. Please report this issue.", null));
            } else {
                result.success(PigeonParser.toPigeonQuerySnapshot((QuerySnapshot) Tasks.await(parseQuery.get(parsePigeonSource)), PigeonParser.parsePigeonServerTimestampBehavior(pigeonGetOptions.getServerTimestampBehavior())));
            }
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void aggregateQueryCount(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final String str, final GeneratedAndroidFirebaseFirestore.PigeonQueryParameters pigeonQueryParameters, final GeneratedAndroidFirebaseFirestore.AggregateSource aggregateSource, final GeneratedAndroidFirebaseFirestore.Result<Double> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$aggregateQueryCount$17(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, str, pigeonQueryParameters, aggregateSource, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$aggregateQueryCount$17(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, String str, GeneratedAndroidFirebaseFirestore.PigeonQueryParameters pigeonQueryParameters, GeneratedAndroidFirebaseFirestore.AggregateSource aggregateSource, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            result.success(Double.valueOf(((AggregateQuerySnapshot) Tasks.await(PigeonParser.parseQuery(getFirestoreFromPigeon(firestorePigeonFirebaseApp), str, false, pigeonQueryParameters).count().get(PigeonParser.parseAggregateSource(aggregateSource)))).getCount()));
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void writeBatchCommit(final GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, final List<GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand> list, final GeneratedAndroidFirebaseFirestore.Result<Void> result) {
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$writeBatchCommit$18(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp.this, list, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$writeBatchCommit$18(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, List list, GeneratedAndroidFirebaseFirestore.Result result) {
        try {
            FirebaseFirestore firestoreFromPigeon = getFirestoreFromPigeon(firestorePigeonFirebaseApp);
            WriteBatch batch = firestoreFromPigeon.batch();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand pigeonTransactionCommand = (GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand) it.next();
                GeneratedAndroidFirebaseFirestore.PigeonTransactionType type = pigeonTransactionCommand.getType();
                Objects.requireNonNull(type);
                GeneratedAndroidFirebaseFirestore.PigeonTransactionType pigeonTransactionType = type;
                String path = pigeonTransactionCommand.getPath();
                Objects.requireNonNull(path);
                String str = path;
                Map<String, Object> data = pigeonTransactionCommand.getData();
                DocumentReference document = firestoreFromPigeon.document(path);
                int i = AnonymousClass1.$SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType[type.ordinal()];
                if (i == 1) {
                    batch = batch.delete(document);
                } else if (i == 2) {
                    Objects.requireNonNull(data);
                    Map<String, Object> map = data;
                    batch = batch.update(document, data);
                } else if (i == 3) {
                    GeneratedAndroidFirebaseFirestore.PigeonDocumentOption option = pigeonTransactionCommand.getOption();
                    Objects.requireNonNull(option);
                    GeneratedAndroidFirebaseFirestore.PigeonDocumentOption pigeonDocumentOption = option;
                    if (option.getMerge() != null && option.getMerge().booleanValue()) {
                        Objects.requireNonNull(data);
                        batch = batch.set(document, data, SetOptions.merge());
                    } else if (option.getMergeFields() != null) {
                        List<List<String>> mergeFields = option.getMergeFields();
                        Objects.requireNonNull(mergeFields);
                        List<List<String>> list2 = mergeFields;
                        List<FieldPath> parseFieldPath = PigeonParser.parseFieldPath(mergeFields);
                        Objects.requireNonNull(data);
                        batch = batch.set(document, data, SetOptions.mergeFieldPaths(parseFieldPath));
                    } else {
                        Objects.requireNonNull(data);
                        batch = batch.set(document, data);
                    }
                }
            }
            Tasks.await(batch.commit());
            result.success(null);
        } catch (Exception e) {
            ExceptionConverter.sendErrorToFlutter(result, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType;

        static {
            int[] iArr = new int[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.values().length];
            $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType = iArr;
            try {
                iArr[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.DELETE_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void querySnapshot(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, String str, Boolean bool, GeneratedAndroidFirebaseFirestore.PigeonQueryParameters pigeonQueryParameters, GeneratedAndroidFirebaseFirestore.PigeonGetOptions pigeonGetOptions, Boolean bool2, GeneratedAndroidFirebaseFirestore.Result<String> result) {
        Query parseQuery = PigeonParser.parseQuery(getFirestoreFromPigeon(firestorePigeonFirebaseApp), str, bool.booleanValue(), pigeonQueryParameters);
        if (parseQuery == null) {
            result.error(new GeneratedAndroidFirebaseFirestore.FlutterError("invalid_query", "An error occurred while parsing query arguments, see native logs for more information. Please report this issue.", null));
        } else {
            result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/query", new QuerySnapshotsStreamHandler(parseQuery, bool2, PigeonParser.parsePigeonServerTimestampBehavior(pigeonGetOptions.getServerTimestampBehavior()))));
        }
    }

    @Override // io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore.FirebaseFirestoreHostApi
    public void documentReferenceSnapshot(GeneratedAndroidFirebaseFirestore.FirestorePigeonFirebaseApp firestorePigeonFirebaseApp, GeneratedAndroidFirebaseFirestore.DocumentReferenceRequest documentReferenceRequest, Boolean bool, GeneratedAndroidFirebaseFirestore.Result<String> result) {
        result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/document", new DocumentSnapshotsStreamHandler(getFirestoreFromPigeon(firestorePigeonFirebaseApp), getFirestoreFromPigeon(firestorePigeonFirebaseApp).document(documentReferenceRequest.getPath()), bool, PigeonParser.parsePigeonServerTimestampBehavior(documentReferenceRequest.getServerTimestampBehavior()))));
    }
}
