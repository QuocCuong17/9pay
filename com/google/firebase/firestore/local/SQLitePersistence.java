package com.google.firebase.firestore.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTransactionListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.FileUtil;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class SQLitePersistence extends Persistence {
    public static final int MAX_ARGS = 900;
    private final SQLiteBundleCache bundleCache;
    private SQLiteDatabase db;
    private final OpenHelper opener;
    private final SQLiteLruReferenceDelegate referenceDelegate;
    private final SQLiteRemoteDocumentCache remoteDocumentCache;
    private final LocalSerializer serializer;
    private boolean started;
    private final SQLiteTargetCache targetCache;
    private final SQLiteTransactionListener transactionListener;

    public static String databaseName(String str, DatabaseId databaseId) {
        try {
            return "firestore." + URLEncoder.encode(str, "utf-8") + "." + URLEncoder.encode(databaseId.getProjectId(), "utf-8") + "." + URLEncoder.encode(databaseId.getDatabaseId(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public SQLitePersistence(Context context, String str, DatabaseId databaseId, LocalSerializer localSerializer, LruGarbageCollector.Params params) {
        this(localSerializer, params, new OpenHelper(context, localSerializer, databaseName(str, databaseId)));
    }

    public SQLitePersistence(LocalSerializer localSerializer, LruGarbageCollector.Params params, OpenHelper openHelper) {
        this.transactionListener = new SQLiteTransactionListener() { // from class: com.google.firebase.firestore.local.SQLitePersistence.1
            @Override // android.database.sqlite.SQLiteTransactionListener
            public void onRollback() {
            }

            @Override // android.database.sqlite.SQLiteTransactionListener
            public void onBegin() {
                SQLitePersistence.this.referenceDelegate.onTransactionStarted();
            }

            @Override // android.database.sqlite.SQLiteTransactionListener
            public void onCommit() {
                SQLitePersistence.this.referenceDelegate.onTransactionCommitted();
            }
        };
        this.opener = openHelper;
        this.serializer = localSerializer;
        this.targetCache = new SQLiteTargetCache(this, localSerializer);
        this.bundleCache = new SQLiteBundleCache(this, localSerializer);
        this.remoteDocumentCache = new SQLiteRemoteDocumentCache(this, localSerializer);
        this.referenceDelegate = new SQLiteLruReferenceDelegate(this, params);
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void start() {
        Assert.hardAssert(!this.started, "SQLitePersistence double-started!", new Object[0]);
        this.started = true;
        try {
            this.db = this.opener.getWritableDatabase();
            this.targetCache.start();
            this.referenceDelegate.start(this.targetCache.getHighestListenSequenceNumber());
        } catch (SQLiteDatabaseLockedException e) {
            throw new RuntimeException("Failed to gain exclusive lock to the Cloud Firestore client's offline persistence. This generally means you are using Cloud Firestore from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing Cloud Firestore in your Application class. If you are intentionally using Cloud Firestore from multiple processes, you can only enable offline persistence (that is, call setPersistenceEnabled(true)) in one of them.", e);
        }
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void shutdown() {
        Assert.hardAssert(this.started, "SQLitePersistence shutdown without start!", new Object[0]);
        this.started = false;
        this.db.close();
        this.db = null;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public boolean isStarted() {
        return this.started;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public SQLiteLruReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        return new SQLiteMutationQueue(this, this.serializer, user, indexManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public SQLiteTargetCache getTargetCache() {
        return this.targetCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public IndexManager getIndexManager(User user) {
        return new SQLiteIndexManager(this, this.serializer, user);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public BundleCache getBundleCache() {
        return this.bundleCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public DocumentOverlayCache getDocumentOverlayCache(User user) {
        return new SQLiteDocumentOverlayCache(this, this.serializer, user);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public OverlayMigrationManager getOverlayMigrationManager() {
        return new SQLiteOverlayMigrationManager(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public void runTransaction(String str, Runnable runnable) {
        Logger.debug(TAG, "Starting transaction: %s", str);
        this.db.beginTransactionWithListener(this.transactionListener);
        try {
            runnable.run();
            this.db.setTransactionSuccessful();
        } finally {
            this.db.endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public <T> T runTransaction(String str, Supplier<T> supplier) {
        Logger.debug(TAG, "Starting transaction: %s", str);
        this.db.beginTransactionWithListener(this.transactionListener);
        try {
            T t = supplier.get();
            this.db.setTransactionSuccessful();
            return t;
        } finally {
            this.db.endTransaction();
        }
    }

    public static void clearPersistence(Context context, DatabaseId databaseId, String str) throws FirebaseFirestoreException {
        String path = context.getDatabasePath(databaseName(str, databaseId)).getPath();
        String str2 = path + "-wal";
        File file = new File(path);
        File file2 = new File(path + "-journal");
        File file3 = new File(str2);
        try {
            FileUtil.delete(file);
            FileUtil.delete(file2);
            FileUtil.delete(file3);
        } catch (IOException e) {
            throw new FirebaseFirestoreException("Failed to clear persistence." + e, FirebaseFirestoreException.Code.UNKNOWN);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getByteSize() {
        return getPageCount() * getPageSize();
    }

    private long getPageSize() {
        return ((Long) query("PRAGMA page_size").firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLitePersistence$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf(((Cursor) obj).getLong(0));
                return valueOf;
            }
        })).longValue();
    }

    private long getPageCount() {
        return ((Long) query("PRAGMA page_count").firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLitePersistence$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf(((Cursor) obj).getLong(0));
                return valueOf;
            }
        })).longValue();
    }

    /* loaded from: classes4.dex */
    static class OpenHelper extends SQLiteOpenHelper {
        private boolean configured;
        private final LocalSerializer serializer;

        private OpenHelper(Context context, LocalSerializer localSerializer, String str) {
            this(context, localSerializer, str, 16);
        }

        OpenHelper(Context context, LocalSerializer localSerializer, String str, int i) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, i);
            this.serializer = localSerializer;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.configured = true;
            sQLiteDatabase.rawQuery("PRAGMA locking_mode = EXCLUSIVE", new String[0]).close();
        }

        private void ensureConfigured(SQLiteDatabase sQLiteDatabase) {
            if (this.configured) {
                return;
            }
            onConfigure(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            ensureConfigured(sQLiteDatabase);
            new SQLiteSchema(sQLiteDatabase, this.serializer).runSchemaUpgrades(0);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            ensureConfigured(sQLiteDatabase);
            new SQLiteSchema(sQLiteDatabase, this.serializer).runSchemaUpgrades(i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            ensureConfigured(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            ensureConfigured(sQLiteDatabase);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void execute(String str, Object... objArr) {
        this.db.execSQL(str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteStatement prepare(String str) {
        return this.db.compileStatement(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int execute(SQLiteStatement sQLiteStatement, Object... objArr) {
        sQLiteStatement.clearBindings();
        bind(sQLiteStatement, objArr);
        return sQLiteStatement.executeUpdateDelete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Query query(String str) {
        return new Query(this.db, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Query {
        private SQLiteDatabase.CursorFactory cursorFactory;
        private final SQLiteDatabase db;
        private final String sql;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Query(SQLiteDatabase sQLiteDatabase, String str) {
            this.db = sQLiteDatabase;
            this.sql = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Query binding(final Object... objArr) {
            this.cursorFactory = new SQLiteDatabase.CursorFactory() { // from class: com.google.firebase.firestore.local.SQLitePersistence$Query$$ExternalSyntheticLambda0
                @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                    return SQLitePersistence.Query.lambda$binding$0(objArr, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
                }
            };
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Cursor lambda$binding$0(Object[] objArr, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            SQLitePersistence.bind(sQLiteQuery, objArr);
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int forEach(Consumer<Cursor> consumer) {
            Cursor startQuery = startQuery();
            int i = 0;
            while (startQuery.moveToNext()) {
                try {
                    i++;
                    consumer.accept(startQuery);
                } catch (Throwable th) {
                    if (startQuery != null) {
                        try {
                            startQuery.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (startQuery != null) {
                startQuery.close();
            }
            return i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int first(Consumer<Cursor> consumer) {
            Cursor startQuery = startQuery();
            try {
                if (!startQuery.moveToFirst()) {
                    if (startQuery != null) {
                        startQuery.close();
                    }
                    return 0;
                }
                consumer.accept(startQuery);
                if (startQuery != null) {
                    startQuery.close();
                }
                return 1;
            } catch (Throwable th) {
                if (startQuery != null) {
                    try {
                        startQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public <T> T firstValue(Function<Cursor, T> function) {
            Cursor startQuery = startQuery();
            try {
                if (!startQuery.moveToFirst()) {
                    if (startQuery != null) {
                        startQuery.close();
                    }
                    return null;
                }
                T apply = function.apply(startQuery);
                if (startQuery != null) {
                    startQuery.close();
                }
                return apply;
            } catch (Throwable th) {
                if (startQuery != null) {
                    try {
                        startQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isEmpty() {
            Cursor startQuery = startQuery();
            try {
                boolean z = !startQuery.moveToFirst();
                if (startQuery != null) {
                    startQuery.close();
                }
                return z;
            } catch (Throwable th) {
                if (startQuery != null) {
                    try {
                        startQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private Cursor startQuery() {
            SQLiteDatabase.CursorFactory cursorFactory = this.cursorFactory;
            if (cursorFactory != null) {
                return this.db.rawQueryWithFactory(cursorFactory, this.sql, null, null);
            }
            return this.db.rawQuery(this.sql, null);
        }
    }

    /* loaded from: classes4.dex */
    static class LongQuery {
        private static final int LIMIT = 900;
        private final List<Object> argsHead;
        private final Iterator<Object> argsIter;
        private final SQLitePersistence db;
        private final String head;
        private int subqueriesPerformed;
        private final String tail;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LongQuery(SQLitePersistence sQLitePersistence, String str, List<Object> list, String str2) {
            this.subqueriesPerformed = 0;
            this.db = sQLitePersistence;
            this.head = str;
            this.argsHead = Collections.emptyList();
            this.tail = str2;
            this.argsIter = list.iterator();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LongQuery(SQLitePersistence sQLitePersistence, String str, List<Object> list, List<Object> list2, String str2) {
            this.subqueriesPerformed = 0;
            this.db = sQLitePersistence;
            this.head = str;
            this.argsHead = list;
            this.tail = str2;
            this.argsIter = list2.iterator();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean hasMoreSubqueries() {
            return this.argsIter.hasNext();
        }

        private Object[] getNextSubqueryArgs() {
            ArrayList arrayList = new ArrayList(this.argsHead);
            for (int i = 0; this.argsIter.hasNext() && i < 900 - this.argsHead.size(); i++) {
                arrayList.add(this.argsIter.next());
            }
            return arrayList.toArray();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Query performNextSubquery() {
            this.subqueriesPerformed++;
            Object[] nextSubqueryArgs = getNextSubqueryArgs();
            return this.db.query(this.head + ((Object) Util.repeatSequence("?", nextSubqueryArgs.length, ", ")) + this.tail).binding(nextSubqueryArgs);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void executeNextSubquery() {
            this.subqueriesPerformed++;
            Object[] nextSubqueryArgs = getNextSubqueryArgs();
            this.db.execute(this.head + ((Object) Util.repeatSequence("?", nextSubqueryArgs.length, ", ")) + this.tail, nextSubqueryArgs);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getSubqueriesPerformed() {
            return this.subqueriesPerformed;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bind(SQLiteProgram sQLiteProgram, Object[] objArr) {
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null) {
                sQLiteProgram.bindNull(i + 1);
            } else if (obj instanceof String) {
                sQLiteProgram.bindString(i + 1, (String) obj);
            } else if (obj instanceof Integer) {
                sQLiteProgram.bindLong(i + 1, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                sQLiteProgram.bindLong(i + 1, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                sQLiteProgram.bindDouble(i + 1, ((Double) obj).doubleValue());
            } else {
                if (!(obj instanceof byte[])) {
                    throw Assert.fail("Unknown argument %s of type %s", obj, obj.getClass());
                }
                sQLiteProgram.bindBlob(i + 1, (byte[]) obj);
            }
        }
    }
}
