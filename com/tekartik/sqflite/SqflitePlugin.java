package com.tekartik.sqflite;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.tekartik.sqflite.DatabaseWorkerPool;
import com.tekartik.sqflite.dev.Debug;
import com.tekartik.sqflite.operation.MethodCallOperation;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMethodCodec;
import io.flutter.plugins.firebase.database.Constants;
import io.sentry.protocol.Device;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class SqflitePlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    private static DatabaseWorkerPool databaseWorkerPool;
    static String databasesPath;
    private Context context;
    private MethodChannel methodChannel;
    static final Map<String, Integer> _singleInstancesByPath = new HashMap();
    static final Map<Integer, Database> databaseMap = new HashMap();
    private static final Object databaseMapLocker = new Object();
    private static final Object openCloseLocker = new Object();
    static int logLevel = 0;
    private static int THREAD_PRIORITY = 0;
    private static int THREAD_COUNT = 1;
    private static int databaseId = 0;

    public SqflitePlugin() {
    }

    public SqflitePlugin(Context context) {
        this.context = context.getApplicationContext();
    }

    private static Map<String, Object> fixMap(Map<Object, Object> map) {
        Object sqflitePlugin;
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                sqflitePlugin = fixMap((Map) value);
            } else {
                sqflitePlugin = toString(value);
            }
            hashMap.put(toString(entry.getKey()), sqflitePlugin);
        }
        return hashMap;
    }

    private static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            ArrayList arrayList = new ArrayList();
            for (byte b : (byte[]) obj) {
                arrayList.add(Integer.valueOf(b));
            }
            return arrayList.toString();
        }
        if (obj instanceof Map) {
            return fixMap((Map) obj).toString();
        }
        return obj.toString();
    }

    static boolean isInMemoryPath(String str) {
        return str == null || str.equals(":memory:");
    }

    static Map makeOpenResult(int i, boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.valueOf(i));
        if (z) {
            hashMap.put(Constant.PARAM_RECOVERED, true);
        }
        if (z2) {
            hashMap.put(Constant.PARAM_RECOVERED_IN_TRANSACTION, true);
        }
        return hashMap;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger());
    }

    private void onAttachedToEngine(Context context, BinaryMessenger binaryMessenger) {
        this.context = context;
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "com.tekartik.sqflite", StandardMethodCodec.INSTANCE, binaryMessenger.makeBackgroundTaskQueue());
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.context = null;
        this.methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
    }

    private Context getContext() {
        return this.context;
    }

    private Database getDatabase(int i) {
        return databaseMap.get(Integer.valueOf(i));
    }

    private Database getDatabaseOrError(MethodCall methodCall, MethodChannel.Result result) {
        int intValue = ((Integer) methodCall.argument("id")).intValue();
        Database database = getDatabase(intValue);
        if (database != null) {
            return database;
        }
        result.error("sqlite_error", "database_closed " + intValue, null);
        return null;
    }

    private void onQueryCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                databaseOrError.query(new MethodCallOperation(MethodCall.this, result));
            }
        });
    }

    private void onQueryCursorNextCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                databaseOrError.queryCursorNext(new MethodCallOperation(MethodCall.this, result));
            }
        });
    }

    private void onBatchCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.batch(methodCall, result);
            }
        });
    }

    private void onInsertCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                databaseOrError.insert(new MethodCallOperation(MethodCall.this, result));
            }
        });
    }

    private void onExecuteCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                databaseOrError.execute(new MethodCallOperation(MethodCall.this, result));
            }
        });
    }

    private void onSetLocaleCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SqflitePlugin.lambda$onSetLocaleCall$5(MethodCall.this, databaseOrError, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onSetLocaleCall$5(MethodCall methodCall, Database database, MethodChannel.Result result) {
        try {
            database.sqliteDatabase.setLocale(Utils.localeForLanguateTag((String) methodCall.argument(Device.JsonKeys.LOCALE)));
            result.success(null);
        } catch (Exception e) {
            result.error("sqlite_error", "Error calling setLocale: " + e.getMessage(), null);
        }
    }

    private void onUpdateCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                databaseOrError.update(new MethodCallOperation(MethodCall.this, result));
            }
        });
    }

    private void onDebugCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = (String) methodCall.argument("cmd");
        HashMap hashMap = new HashMap();
        if (WorkflowRequestType.Method.GET.equals(str)) {
            int i = logLevel;
            if (i > 0) {
                hashMap.put("logLevel", Integer.valueOf(i));
            }
            Map<Integer, Database> map = databaseMap;
            if (!map.isEmpty()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry<Integer, Database> entry : map.entrySet()) {
                    Database value = entry.getValue();
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put(Constants.PATH, value.path);
                    hashMap3.put("singleInstance", Boolean.valueOf(value.singleInstance));
                    if (value.logLevel > 0) {
                        hashMap3.put("logLevel", Integer.valueOf(value.logLevel));
                    }
                    hashMap2.put(entry.getKey().toString(), hashMap3);
                }
                hashMap.put("databases", hashMap2);
            }
        }
        result.success(hashMap);
    }

    private void onDebugModeCall(MethodCall methodCall, MethodChannel.Result result) {
        Debug.LOGV = Boolean.TRUE.equals(methodCall.arguments());
        Debug.EXTRA_LOGV = Debug._EXTRA_LOGV && Debug.LOGV;
        if (Debug.LOGV) {
            if (Debug.EXTRA_LOGV) {
                logLevel = 2;
            } else if (Debug.LOGV) {
                logLevel = 1;
            }
        } else {
            logLevel = 0;
        }
        result.success(null);
    }

    private void onOpenDatabaseCall(final MethodCall methodCall, final MethodChannel.Result result) {
        final int i;
        Database database;
        final String str = (String) methodCall.argument(Constants.PATH);
        final Boolean bool = (Boolean) methodCall.argument("readOnly");
        final boolean isInMemoryPath = isInMemoryPath(str);
        boolean z = (Boolean.FALSE.equals(methodCall.argument("singleInstance")) || isInMemoryPath) ? false : true;
        if (z) {
            synchronized (databaseMapLocker) {
                if (LogLevel.hasVerboseLevel(logLevel)) {
                    Log.d(Constant.TAG, "Look for " + str + " in " + _singleInstancesByPath.keySet());
                }
                Integer num = _singleInstancesByPath.get(str);
                if (num != null && (database = databaseMap.get(num)) != null) {
                    if (!database.sqliteDatabase.isOpen()) {
                        if (LogLevel.hasVerboseLevel(logLevel)) {
                            Log.d(Constant.TAG, database.getThreadLogPrefix() + "single instance database of " + str + " not opened");
                        }
                    } else {
                        if (LogLevel.hasVerboseLevel(logLevel)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(database.getThreadLogPrefix());
                            sb.append("re-opened single instance ");
                            sb.append(database.isInTransaction() ? "(in transaction) " : "");
                            sb.append(num);
                            sb.append(" ");
                            sb.append(str);
                            Log.d(Constant.TAG, sb.toString());
                        }
                        result.success(makeOpenResult(num.intValue(), true, database.isInTransaction()));
                        return;
                    }
                }
            }
        }
        Object obj = databaseMapLocker;
        synchronized (obj) {
            i = databaseId + 1;
            databaseId = i;
        }
        final Database database2 = new Database(this.context, str, i, z, logLevel);
        synchronized (obj) {
            if (databaseWorkerPool == null) {
                DatabaseWorkerPool create = DatabaseWorkerPool.CC.create(Constant.TAG, THREAD_COUNT, THREAD_PRIORITY);
                databaseWorkerPool = create;
                create.start();
                if (LogLevel.hasSqlLevel(database2.logLevel)) {
                    Log.d(Constant.TAG, database2.getThreadLogPrefix() + "starting worker pool with priority " + THREAD_PRIORITY);
                }
            }
            database2.databaseWorkerPool = databaseWorkerPool;
            if (LogLevel.hasSqlLevel(database2.logLevel)) {
                Log.d(Constant.TAG, database2.getThreadLogPrefix() + "opened " + i + " " + str);
            }
            final boolean z2 = z;
            databaseWorkerPool.post(database2, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    SqflitePlugin.lambda$onOpenDatabaseCall$7(isInMemoryPath, str, result, bool, database2, methodCall, z2, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onOpenDatabaseCall$7(boolean z, String str, MethodChannel.Result result, Boolean bool, Database database, MethodCall methodCall, boolean z2, int i) {
        synchronized (openCloseLocker) {
            if (!z) {
                try {
                    File file = new File(new File(str).getParent());
                    if (!file.exists() && !file.mkdirs() && !file.exists()) {
                        result.error("sqlite_error", "open_failed " + str, null);
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            try {
                if (Boolean.TRUE.equals(bool)) {
                    database.openReadOnly();
                } else {
                    database.open();
                }
                synchronized (databaseMapLocker) {
                    if (z2) {
                        _singleInstancesByPath.put(str, Integer.valueOf(i));
                    }
                    databaseMap.put(Integer.valueOf(i), database);
                }
                if (LogLevel.hasSqlLevel(database.logLevel)) {
                    Log.d(Constant.TAG, database.getThreadLogPrefix() + "opened " + i + " " + str);
                }
                result.success(makeOpenResult(i, false, false));
            } catch (Exception e) {
                database.handleException(e, new MethodCallOperation(methodCall, result));
            }
        }
    }

    private void onCloseDatabaseCall(MethodCall methodCall, final MethodChannel.Result result) {
        int intValue = ((Integer) methodCall.argument("id")).intValue();
        final Database databaseOrError = getDatabaseOrError(methodCall, result);
        if (databaseOrError == null) {
            return;
        }
        if (LogLevel.hasSqlLevel(databaseOrError.logLevel)) {
            Log.d(Constant.TAG, databaseOrError.getThreadLogPrefix() + "closing " + intValue + " " + databaseOrError.path);
        }
        String str = databaseOrError.path;
        synchronized (databaseMapLocker) {
            databaseMap.remove(Integer.valueOf(intValue));
            if (databaseOrError.singleInstance) {
                _singleInstancesByPath.remove(str);
            }
        }
        databaseWorkerPool.post(databaseOrError, new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (SqflitePlugin.openCloseLocker) {
                    SqflitePlugin.this.closeDatabase(databaseOrError);
                }
                result.success(null);
            }
        });
    }

    private void onDeleteDatabaseCall(MethodCall methodCall, final MethodChannel.Result result) {
        final Database database;
        Map<Integer, Database> map;
        final String str = (String) methodCall.argument(Constants.PATH);
        synchronized (databaseMapLocker) {
            if (LogLevel.hasVerboseLevel(logLevel)) {
                Log.d(Constant.TAG, "Look for " + str + " in " + _singleInstancesByPath.keySet());
            }
            Map<String, Integer> map2 = _singleInstancesByPath;
            Integer num = map2.get(str);
            if (num == null || (database = (map = databaseMap).get(num)) == null || !database.sqliteDatabase.isOpen()) {
                database = null;
            } else {
                if (LogLevel.hasVerboseLevel(logLevel)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(database.getThreadLogPrefix());
                    sb.append("found single instance ");
                    sb.append(database.isInTransaction() ? "(in transaction) " : "");
                    sb.append(num);
                    sb.append(" ");
                    sb.append(str);
                    Log.d(Constant.TAG, sb.toString());
                }
                map.remove(num);
                map2.remove(str);
            }
        }
        Runnable runnable = new Runnable() { // from class: com.tekartik.sqflite.SqflitePlugin.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (SqflitePlugin.openCloseLocker) {
                    Database database2 = database;
                    if (database2 != null) {
                        SqflitePlugin.this.closeDatabase(database2);
                    }
                    try {
                        if (LogLevel.hasVerboseLevel(SqflitePlugin.logLevel)) {
                            Log.d(Constant.TAG, "delete database " + str);
                        }
                        Database.deleteDatabase(str);
                    } catch (Exception e) {
                        Log.e(Constant.TAG, "error " + e + " while closing database " + SqflitePlugin.databaseId);
                    }
                }
                result.success(null);
            }
        };
        DatabaseWorkerPool databaseWorkerPool2 = databaseWorkerPool;
        if (databaseWorkerPool2 != null) {
            databaseWorkerPool2.post(database, runnable);
        } else {
            runnable.run();
        }
    }

    private void onDatabaseExistsCall(MethodCall methodCall, MethodChannel.Result result) {
        result.success(Boolean.valueOf(Database.existsDatabase((String) methodCall.argument(Constants.PATH))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeDatabase(Database database) {
        try {
            if (LogLevel.hasSqlLevel(database.logLevel)) {
                Log.d(Constant.TAG, database.getThreadLogPrefix() + "closing database ");
            }
            database.close();
        } catch (Exception e) {
            Log.e(Constant.TAG, "error " + e + " while closing database " + databaseId);
        }
        synchronized (databaseMapLocker) {
            if (databaseMap.isEmpty() && databaseWorkerPool != null) {
                if (LogLevel.hasSqlLevel(database.logLevel)) {
                    Log.d(Constant.TAG, database.getThreadLogPrefix() + "stopping thread");
                }
                databaseWorkerPool.quit();
                databaseWorkerPool = null;
            }
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1319569547:
                if (str.equals(Constant.METHOD_EXECUTE)) {
                    c = 0;
                    break;
                }
                break;
            case -1253581933:
                if (str.equals(Constant.METHOD_CLOSE_DATABASE)) {
                    c = 1;
                    break;
                }
                break;
            case -1249474914:
                if (str.equals("options")) {
                    c = 2;
                    break;
                }
                break;
            case -1183792455:
                if (str.equals(Constant.METHOD_INSERT)) {
                    c = 3;
                    break;
                }
                break;
            case -838846263:
                if (str.equals(Constant.METHOD_UPDATE)) {
                    c = 4;
                    break;
                }
                break;
            case -396289107:
                if (str.equals(Constant.METHOD_ANDROID_SET_LOCALE)) {
                    c = 5;
                    break;
                }
                break;
            case -263511994:
                if (str.equals(Constant.METHOD_DELETE_DATABASE)) {
                    c = 6;
                    break;
                }
                break;
            case -198450538:
                if (str.equals(Constant.METHOD_DEBUG_MODE)) {
                    c = 7;
                    break;
                }
                break;
            case -17190427:
                if (str.equals(Constant.METHOD_OPEN_DATABASE)) {
                    c = '\b';
                    break;
                }
                break;
            case 93509434:
                if (str.equals(Constant.METHOD_BATCH)) {
                    c = '\t';
                    break;
                }
                break;
            case 95458899:
                if (str.equals(Constant.METHOD_DEBUG)) {
                    c = '\n';
                    break;
                }
                break;
            case 107944136:
                if (str.equals("query")) {
                    c = 11;
                    break;
                }
                break;
            case 956410295:
                if (str.equals(Constant.METHOD_DATABASE_EXISTS)) {
                    c = '\f';
                    break;
                }
                break;
            case 1193546321:
                if (str.equals(Constant.METHOD_QUERY_CURSOR_NEXT)) {
                    c = '\r';
                    break;
                }
                break;
            case 1385449135:
                if (str.equals(Constant.METHOD_GET_PLATFORM_VERSION)) {
                    c = 14;
                    break;
                }
                break;
            case 1863829223:
                if (str.equals(Constant.METHOD_GET_DATABASES_PATH)) {
                    c = 15;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                onExecuteCall(methodCall, result);
                return;
            case 1:
                onCloseDatabaseCall(methodCall, result);
                return;
            case 2:
                onOptionsCall(methodCall, result);
                return;
            case 3:
                onInsertCall(methodCall, result);
                return;
            case 4:
                onUpdateCall(methodCall, result);
                return;
            case 5:
                onSetLocaleCall(methodCall, result);
                return;
            case 6:
                onDeleteDatabaseCall(methodCall, result);
                return;
            case 7:
                onDebugModeCall(methodCall, result);
                return;
            case '\b':
                onOpenDatabaseCall(methodCall, result);
                return;
            case '\t':
                onBatchCall(methodCall, result);
                return;
            case '\n':
                onDebugCall(methodCall, result);
                return;
            case 11:
                onQueryCall(methodCall, result);
                return;
            case '\f':
                onDatabaseExistsCall(methodCall, result);
                return;
            case '\r':
                onQueryCursorNextCall(methodCall, result);
                return;
            case 14:
                result.success("Android " + Build.VERSION.RELEASE);
                return;
            case 15:
                onGetDatabasesPathCall(methodCall, result);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    void onOptionsCall(MethodCall methodCall, MethodChannel.Result result) {
        Object argument = methodCall.argument("androidThreadPriority");
        if (argument != null) {
            THREAD_PRIORITY = ((Integer) argument).intValue();
        }
        Object argument2 = methodCall.argument("androidThreadCount");
        if (argument2 != null && !argument2.equals(Integer.valueOf(THREAD_COUNT))) {
            THREAD_COUNT = ((Integer) argument2).intValue();
            DatabaseWorkerPool databaseWorkerPool2 = databaseWorkerPool;
            if (databaseWorkerPool2 != null) {
                databaseWorkerPool2.quit();
                databaseWorkerPool = null;
            }
        }
        Integer logLevel2 = LogLevel.getLogLevel(methodCall);
        if (logLevel2 != null) {
            logLevel = logLevel2.intValue();
        }
        result.success(null);
    }

    void onGetDatabasesPathCall(MethodCall methodCall, MethodChannel.Result result) {
        if (databasesPath == null) {
            databasesPath = this.context.getDatabasePath("tekartik_sqflite.db").getParent();
        }
        result.success(databasesPath);
    }
}
