package com.tekartik.sqflite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.sqlite.CursorWrapper;
import com.tekartik.sqflite.operation.BatchOperation;
import com.tekartik.sqflite.operation.MethodCallOperation;
import com.tekartik.sqflite.operation.Operation;
import com.tekartik.sqflite.operation.QueuedOperation;
import com.tekartik.sqflite.operation.SqlErrorInfo;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class Database {
    static final boolean WAL_ENABLED_BY_DEFAULT = false;
    private static final String WAL_ENABLED_META_NAME = "com.tekartik.sqflite.wal_enabled";
    private static Boolean walGloballyEnabled;
    final Context context;
    private Integer currentTransactionId;
    public DatabaseWorkerPool databaseWorkerPool;

    /* renamed from: id, reason: collision with root package name */
    final int f113id;
    final int logLevel;
    final String path;
    final boolean singleInstance;
    SQLiteDatabase sqliteDatabase;
    final List<QueuedOperation> noTransactionOperationQueue = new ArrayList();
    final Map<Integer, SqfliteCursor> cursors = new HashMap();
    private int transactionDepth = 0;
    private int lastTransactionId = 0;
    private int lastCursorId = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Database(Context context, String str, int i, boolean z, int i2) {
        this.context = context;
        this.path = str;
        this.singleInstance = z;
        this.f113id = i;
        this.logLevel = i2;
    }

    protected static boolean checkWalEnabled(Context context) {
        return checkMetaBoolean(context, WAL_ENABLED_META_NAME, false);
    }

    static ApplicationInfo getApplicationInfoWithMeta32(Context context, String str, int i) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(str, i);
    }

    protected static boolean checkMetaBoolean(Context context, String str, boolean z) {
        ApplicationInfo applicationInfoWithMeta32;
        try {
            String packageName = context.getPackageName();
            if (Build.VERSION.SDK_INT >= 33) {
                applicationInfoWithMeta32 = context.getPackageManager().getApplicationInfo(packageName, PackageManager.ApplicationInfoFlags.of(128L));
            } else {
                applicationInfoWithMeta32 = getApplicationInfoWithMeta32(context, packageName, 128);
            }
            return applicationInfoWithMeta32.metaData.getBoolean(str, z);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteDatabase(String str) {
        SQLiteDatabase.deleteDatabase(new File(str));
    }

    public static boolean existsDatabase(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public void open() {
        if (walGloballyEnabled == null) {
            Boolean valueOf = Boolean.valueOf(checkWalEnabled(this.context));
            walGloballyEnabled = valueOf;
            if (valueOf.booleanValue() && LogLevel.hasVerboseLevel(this.logLevel)) {
                Log.d(Constant.TAG, getThreadLogPrefix() + "[sqflite] WAL enabled");
            }
        }
        this.sqliteDatabase = SQLiteDatabase.openDatabase(this.path, null, walGloballyEnabled.booleanValue() ? 805306368 : 268435456);
    }

    public void openReadOnly() {
        this.sqliteDatabase = SQLiteDatabase.openDatabase(this.path, null, 1, new DatabaseErrorHandler() { // from class: com.tekartik.sqflite.Database.1
            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            }
        });
    }

    public void close() {
        if (!this.cursors.isEmpty() && LogLevel.hasSqlLevel(this.logLevel)) {
            Log.d(Constant.TAG, getThreadLogPrefix() + this.cursors.size() + " cursor(s) are left opened");
        }
        this.sqliteDatabase.close();
    }

    public SQLiteDatabase getWritableDatabase() {
        return this.sqliteDatabase;
    }

    public SQLiteDatabase getReadableDatabase() {
        return this.sqliteDatabase;
    }

    public boolean enableWriteAheadLogging() {
        try {
            return this.sqliteDatabase.enableWriteAheadLogging();
        } catch (Exception e) {
            Log.e(Constant.TAG, getThreadLogPrefix() + "enable WAL error: " + e);
            return false;
        }
    }

    String getThreadLogTag() {
        Thread currentThread = Thread.currentThread();
        return this.f113id + "," + currentThread.getName() + "(" + currentThread.getId() + ")";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getThreadLogPrefix() {
        return "[" + getThreadLogTag() + "] ";
    }

    private Map<String, Object> cursorToResults(Cursor cursor, Integer num) {
        HashMap hashMap = null;
        int i = 0;
        ArrayList arrayList = null;
        while (cursor.moveToNext()) {
            if (hashMap == null) {
                ArrayList arrayList2 = new ArrayList();
                HashMap hashMap2 = new HashMap();
                i = cursor.getColumnCount();
                hashMap2.put(Constant.PARAM_COLUMNS, Arrays.asList(cursor.getColumnNames()));
                hashMap2.put(Constant.PARAM_ROWS, arrayList2);
                arrayList = arrayList2;
                hashMap = hashMap2;
            }
            arrayList.add(Utils.cursorRowToList(cursor, i));
            if (num != null && arrayList.size() >= num.intValue()) {
                break;
            }
        }
        return hashMap == null ? new HashMap() : hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runQueuedOperations() {
        while (!this.noTransactionOperationQueue.isEmpty() && this.currentTransactionId == null) {
            this.noTransactionOperationQueue.get(0).run();
            this.noTransactionOperationQueue.remove(0);
        }
    }

    private void wrapSqlOperationHandler(Operation operation, Runnable runnable) {
        Integer transactionId = operation.getTransactionId();
        Integer num = this.currentTransactionId;
        if (num == null) {
            runnable.run();
            return;
        }
        if (transactionId != null && (transactionId.equals(num) || transactionId.intValue() == -1)) {
            runnable.run();
            if (this.currentTransactionId != null || this.noTransactionOperationQueue.isEmpty()) {
                return;
            }
            this.databaseWorkerPool.post(this, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Database.this.runQueuedOperations();
                }
            });
            return;
        }
        this.noTransactionOperationQueue.add(new QueuedOperation(operation, runnable));
    }

    public void query(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m987lambda$query$0$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.tekartik.sqflite.operation.Operation] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.tekartik.sqflite.SqlCommand, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.tekartik.sqflite.Database] */
    /* renamed from: doQuery, reason: merged with bridge method [inline-methods] */
    public boolean m987lambda$query$0$comtekartiksqfliteDatabase(Operation operation) {
        Integer num = (Integer) operation.getArgument(Constant.PARAM_CURSOR_PAGE_SIZE);
        final ?? sqlCommand = operation.getSqlCommand();
        if (LogLevel.hasSqlLevel(this.logLevel)) {
            Log.d(Constant.TAG, getThreadLogPrefix() + sqlCommand);
        }
        SqfliteCursor sqfliteCursor = null;
        try {
            try {
                sqlCommand = getReadableDatabase().rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda0
                    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                        return Database.lambda$doQuery$1(SqlCommand.this, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
                    }
                }, sqlCommand.getSql(), Constant.EMPTY_STRING_ARRAY, null);
                try {
                    Map<String, Object> cursorToResults = cursorToResults(sqlCommand, num);
                    if ((num == null || sqlCommand.isLast() || sqlCommand.isAfterLast()) ? false : true) {
                        int i = this.lastCursorId + 1;
                        this.lastCursorId = i;
                        cursorToResults.put(Constant.PARAM_CURSOR_ID, Integer.valueOf(i));
                        SqfliteCursor sqfliteCursor2 = new SqfliteCursor(i, num.intValue(), sqlCommand);
                        try {
                            this.cursors.put(Integer.valueOf(i), sqfliteCursor2);
                            sqfliteCursor = sqfliteCursor2;
                        } catch (Exception e) {
                            e = e;
                            sqfliteCursor = sqfliteCursor2;
                            handleException(e, operation);
                            if (sqfliteCursor != null) {
                                closeCursor(sqfliteCursor);
                            }
                            if (sqfliteCursor == null && sqlCommand != 0) {
                                sqlCommand.close();
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            sqfliteCursor = sqfliteCursor2;
                            if (sqfliteCursor == null && sqlCommand != 0) {
                                sqlCommand.close();
                            }
                            throw th;
                        }
                    }
                    operation.success(cursorToResults);
                    if (sqfliteCursor == null && sqlCommand != 0) {
                        sqlCommand.close();
                    }
                    return true;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            sqlCommand = 0;
        } catch (Throwable th3) {
            th = th3;
            sqlCommand = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Cursor lambda$doQuery$1(SqlCommand sqlCommand, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        sqlCommand.bindTo(sQLiteQuery);
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }

    public void queryCursorNext(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m988lambda$queryCursorNext$2$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doQueryCursorNext, reason: merged with bridge method [inline-methods] */
    public boolean m988lambda$queryCursorNext$2$comtekartiksqfliteDatabase(Operation operation) {
        boolean z;
        int intValue = ((Integer) operation.getArgument(Constant.PARAM_CURSOR_ID)).intValue();
        boolean equals = Boolean.TRUE.equals(operation.getArgument(Constant.PARAM_CANCEL));
        if (LogLevel.hasVerboseLevel(this.logLevel)) {
            StringBuilder sb = new StringBuilder();
            sb.append(getThreadLogPrefix());
            sb.append("cursor ");
            sb.append(intValue);
            sb.append(equals ? " cancel" : " next");
            Log.d(Constant.TAG, sb.toString());
        }
        SqfliteCursor sqfliteCursor = null;
        if (equals) {
            closeCursor(intValue);
            operation.success(null);
            return true;
        }
        SqfliteCursor sqfliteCursor2 = this.cursors.get(Integer.valueOf(intValue));
        boolean z2 = false;
        try {
            if (sqfliteCursor2 == null) {
                throw new IllegalStateException("Cursor " + intValue + " not found");
            }
            Cursor cursor = sqfliteCursor2.cursor;
            Map<String, Object> cursorToResults = cursorToResults(cursor, Integer.valueOf(sqfliteCursor2.pageSize));
            z = (cursor.isLast() || cursor.isAfterLast()) ? false : true;
            if (z) {
                try {
                    try {
                        cursorToResults.put(Constant.PARAM_CURSOR_ID, Integer.valueOf(intValue));
                    } catch (Exception e) {
                        e = e;
                        handleException(e, operation);
                        if (sqfliteCursor2 != null) {
                            closeCursor(sqfliteCursor2);
                        } else {
                            sqfliteCursor = sqfliteCursor2;
                        }
                        if (!z && sqfliteCursor != null) {
                            closeCursor(sqfliteCursor);
                        }
                        return false;
                    }
                } catch (Throwable th) {
                    th = th;
                    z2 = z;
                    if (!z2 && sqfliteCursor2 != null) {
                        closeCursor(sqfliteCursor2);
                    }
                    throw th;
                }
            }
            operation.success(cursorToResults);
            if (!z && sqfliteCursor2 != null) {
                closeCursor(sqfliteCursor2);
            }
            return true;
        } catch (Exception e2) {
            e = e2;
            z = false;
        } catch (Throwable th2) {
            th = th2;
            if (!z2) {
                closeCursor(sqfliteCursor2);
            }
            throw th;
        }
    }

    private void closeCursor(SqfliteCursor sqfliteCursor) {
        try {
            int i = sqfliteCursor.cursorId;
            if (LogLevel.hasVerboseLevel(this.logLevel)) {
                Log.d(Constant.TAG, getThreadLogPrefix() + "closing cursor " + i);
            }
            this.cursors.remove(Integer.valueOf(i));
            sqfliteCursor.cursor.close();
        } catch (Exception unused) {
        }
    }

    private void closeCursor(int i) {
        SqfliteCursor sqfliteCursor = this.cursors.get(Integer.valueOf(i));
        if (sqfliteCursor != null) {
            closeCursor(sqfliteCursor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleException(Exception exc, Operation operation) {
        if (exc instanceof SQLiteCantOpenDatabaseException) {
            operation.error("sqlite_error", "open_failed " + this.path, null);
            return;
        }
        if (exc instanceof SQLException) {
            operation.error("sqlite_error", exc.getMessage(), SqlErrorInfo.getMap(operation));
        } else {
            operation.error("sqlite_error", exc.getMessage(), SqlErrorInfo.getMap(operation));
        }
    }

    private boolean executeOrError(Operation operation) {
        SqlCommand sqlCommand = operation.getSqlCommand();
        if (LogLevel.hasSqlLevel(this.logLevel)) {
            Log.d(Constant.TAG, getThreadLogPrefix() + sqlCommand);
        }
        Boolean inTransactionChange = operation.getInTransactionChange();
        try {
            getWritableDatabase().execSQL(sqlCommand.getSql(), sqlCommand.getSqlArguments());
            enterOrLeaveInTransaction(inTransactionChange);
            return true;
        } catch (Exception e) {
            handleException(e, operation);
            return false;
        }
    }

    public void execute(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m985lambda$execute$3$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* renamed from: lambda$execute$3$com-tekartik-sqflite-Database, reason: not valid java name */
    public /* synthetic */ void m985lambda$execute$3$comtekartiksqfliteDatabase(Operation operation) {
        Boolean inTransactionChange = operation.getInTransactionChange();
        boolean z = Boolean.TRUE.equals(inTransactionChange) && operation.hasNullTransactionId();
        if (z) {
            int i = this.lastTransactionId + 1;
            this.lastTransactionId = i;
            this.currentTransactionId = Integer.valueOf(i);
        }
        if (!executeOrError(operation)) {
            if (z) {
                this.currentTransactionId = null;
            }
        } else if (z) {
            HashMap hashMap = new HashMap();
            hashMap.put("transactionId", this.currentTransactionId);
            operation.success(hashMap);
        } else {
            if (Boolean.FALSE.equals(inTransactionChange)) {
                this.currentTransactionId = null;
            }
            operation.success(null);
        }
    }

    private boolean doExecute(Operation operation) {
        if (!executeOrError(operation)) {
            return false;
        }
        operation.success(null);
        return true;
    }

    public void insert(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m986lambda$insert$4$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d3  */
    /* renamed from: doInsert, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m986lambda$insert$4$comtekartiksqfliteDatabase(Operation operation) {
        Exception e;
        Cursor cursor;
        if (!executeOrError(operation)) {
            return false;
        }
        CursorWrapper cursorWrapper = 0;
        if (operation.getNoResult()) {
            operation.success(null);
            return true;
        }
        try {
            try {
                cursor = getWritableDatabase().rawQuery("SELECT changes(), last_insert_rowid()", null);
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                            if (cursor.getInt(0) == 0) {
                                if (LogLevel.hasSqlLevel(this.logLevel)) {
                                    Log.d(Constant.TAG, getThreadLogPrefix() + "no changes (id was " + cursor.getLong(1) + ")");
                                }
                                operation.success(null);
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return true;
                            }
                            long j = cursor.getLong(1);
                            if (LogLevel.hasSqlLevel(this.logLevel)) {
                                Log.d(Constant.TAG, getThreadLogPrefix() + "inserted " + j);
                            }
                            operation.success(Long.valueOf(j));
                            if (cursor != null) {
                                cursor.close();
                            }
                            return true;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        handleException(e, operation);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    }
                }
                Log.e(Constant.TAG, getThreadLogPrefix() + "fail to read changes for Insert");
                operation.success(null);
                if (cursor != null) {
                    cursor.close();
                }
                return true;
            } catch (Throwable th) {
                th = th;
                cursorWrapper = "SELECT changes(), last_insert_rowid()";
                if (cursorWrapper != 0) {
                    cursorWrapper.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursorWrapper != 0) {
            }
            throw th;
        }
    }

    public void update(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m989lambda$update$5$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doUpdate, reason: merged with bridge method [inline-methods] */
    public boolean m989lambda$update$5$comtekartiksqfliteDatabase(Operation operation) {
        if (!executeOrError(operation)) {
            return false;
        }
        Cursor cursor = null;
        if (operation.getNoResult()) {
            operation.success(null);
            return true;
        }
        try {
            try {
                Cursor rawQuery = getWritableDatabase().rawQuery("SELECT changes()", null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
                            int i = rawQuery.getInt(0);
                            if (LogLevel.hasSqlLevel(this.logLevel)) {
                                Log.d(Constant.TAG, getThreadLogPrefix() + "changed " + i);
                            }
                            operation.success(Integer.valueOf(i));
                            if (rawQuery != null) {
                                rawQuery.close();
                            }
                            return true;
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = rawQuery;
                        handleException(e, operation);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                Log.e(Constant.TAG, getThreadLogPrefix() + "fail to read changes for Update/Delete");
                operation.success(null);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return true;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x008b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void batch(MethodCall methodCall, MethodChannel.Result result) {
        MethodCallOperation methodCallOperation = new MethodCallOperation(methodCall, result);
        boolean noResult = methodCallOperation.getNoResult();
        boolean continueOnError = methodCallOperation.getContinueOnError();
        List list = (List) methodCallOperation.getArgument("operations");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BatchOperation batchOperation = new BatchOperation((Map) it.next(), noResult);
            String method = batchOperation.getMethod();
            method.hashCode();
            char c = 65535;
            switch (method.hashCode()) {
                case -1319569547:
                    if (method.equals(Constant.METHOD_EXECUTE)) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            if (doExecute(batchOperation)) {
                                batchOperation.handleSuccess(arrayList);
                                break;
                            } else if (continueOnError) {
                                batchOperation.handleErrorContinue(arrayList);
                                break;
                            } else {
                                batchOperation.handleError(result);
                                return;
                            }
                        case 1:
                            if (m986lambda$insert$4$comtekartiksqfliteDatabase(batchOperation)) {
                                batchOperation.handleSuccess(arrayList);
                                break;
                            } else if (continueOnError) {
                                batchOperation.handleErrorContinue(arrayList);
                                break;
                            } else {
                                batchOperation.handleError(result);
                                return;
                            }
                        case 2:
                            if (m989lambda$update$5$comtekartiksqfliteDatabase(batchOperation)) {
                                batchOperation.handleSuccess(arrayList);
                                break;
                            } else if (continueOnError) {
                                batchOperation.handleErrorContinue(arrayList);
                                break;
                            } else {
                                batchOperation.handleError(result);
                                return;
                            }
                        case 3:
                            if (m987lambda$query$0$comtekartiksqfliteDatabase(batchOperation)) {
                                batchOperation.handleSuccess(arrayList);
                                break;
                            } else if (continueOnError) {
                                batchOperation.handleErrorContinue(arrayList);
                                break;
                            } else {
                                batchOperation.handleError(result);
                                return;
                            }
                        default:
                            result.error("bad_param", "Batch method '" + method + "' not supported", null);
                            return;
                    }
                case -1183792455:
                    if (method.equals(Constant.METHOD_INSERT)) {
                        c = 1;
                    }
                    switch (c) {
                    }
                    break;
                case -838846263:
                    if (method.equals(Constant.METHOD_UPDATE)) {
                        c = 2;
                    }
                    switch (c) {
                    }
                    break;
                case 107944136:
                    if (method.equals("query")) {
                        c = 3;
                    }
                    switch (c) {
                    }
                    break;
                default:
                    switch (c) {
                    }
                    break;
            }
        }
        if (noResult) {
            result.success(null);
        } else {
            result.success(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean isInTransaction() {
        return this.transactionDepth > 0;
    }

    synchronized void enterOrLeaveInTransaction(Boolean bool) {
        if (Boolean.TRUE.equals(bool)) {
            this.transactionDepth++;
        } else if (Boolean.FALSE.equals(bool)) {
            this.transactionDepth--;
        }
    }
}
