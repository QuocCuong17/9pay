package com.tekartik.sqflite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DatabaseWorkerPool.java */
/* loaded from: classes5.dex */
public class DatabaseWorkerPoolImpl implements DatabaseWorkerPool {
    final String name;
    final int numberOfWorkers;
    final int priority;
    private final LinkedList<DatabaseTask> waitingList = new LinkedList<>();
    private final Set<DatabaseWorker> idleWorkers = new HashSet();
    private final Set<DatabaseWorker> busyWorkers = new HashSet();
    private final Map<Integer, DatabaseWorker> onlyEligibleWorkers = new HashMap();

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public /* synthetic */ void post(Database database, Runnable runnable) {
        post(new DatabaseTask(r2 == null ? null : new DatabaseDelegate() { // from class: com.tekartik.sqflite.DatabaseWorkerPool.1
            final /* synthetic */ Database val$database;

            AnonymousClass1(Database database2) {
                r2 = database2;
            }

            @Override // com.tekartik.sqflite.DatabaseDelegate
            public int getDatabaseId() {
                return r2.f113id;
            }

            @Override // com.tekartik.sqflite.DatabaseDelegate
            public boolean isInTransaction() {
                return r2.isInTransaction();
            }
        }, runnable));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DatabaseWorkerPoolImpl(String str, int i, int i2) {
        this.name = str;
        this.numberOfWorkers = i;
        this.priority = i2;
    }

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public synchronized void start() {
        for (int i = 0; i < this.numberOfWorkers; i++) {
            final DatabaseWorker createWorker = createWorker(this.name + i, this.priority);
            createWorker.start(new Runnable() { // from class: com.tekartik.sqflite.DatabaseWorkerPoolImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DatabaseWorkerPoolImpl.this.m991lambda$start$0$comtekartiksqfliteDatabaseWorkerPoolImpl(createWorker);
                }
            });
            this.idleWorkers.add(createWorker);
        }
    }

    protected DatabaseWorker createWorker(String str, int i) {
        return new DatabaseWorker(str, i);
    }

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public synchronized void quit() {
        Iterator<DatabaseWorker> it = this.idleWorkers.iterator();
        while (it.hasNext()) {
            it.next().quit();
        }
        Iterator<DatabaseWorker> it2 = this.busyWorkers.iterator();
        while (it2.hasNext()) {
            it2.next().quit();
        }
    }

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public synchronized void post(DatabaseTask databaseTask) {
        this.waitingList.add(databaseTask);
        Iterator it = new HashSet(this.idleWorkers).iterator();
        while (it.hasNext()) {
            tryPostingTaskToWorker((DatabaseWorker) it.next());
        }
    }

    private synchronized void tryPostingTaskToWorker(DatabaseWorker databaseWorker) {
        DatabaseTask findTaskForWorker = findTaskForWorker(databaseWorker);
        if (findTaskForWorker != null) {
            this.busyWorkers.add(databaseWorker);
            this.idleWorkers.remove(databaseWorker);
            if (findTaskForWorker.getDatabaseId() != null) {
                this.onlyEligibleWorkers.put(findTaskForWorker.getDatabaseId(), databaseWorker);
            }
            databaseWorker.postTask(findTaskForWorker);
        }
    }

    private synchronized DatabaseTask findTaskForWorker(DatabaseWorker databaseWorker) {
        DatabaseTask next;
        DatabaseWorker databaseWorker2;
        ListIterator<DatabaseTask> listIterator = this.waitingList.listIterator();
        do {
            if (!listIterator.hasNext()) {
                return null;
            }
            next = listIterator.next();
            databaseWorker2 = next.getDatabaseId() != null ? this.onlyEligibleWorkers.get(next.getDatabaseId()) : null;
            if (databaseWorker2 == null) {
                break;
            }
        } while (databaseWorker2 != databaseWorker);
        listIterator.remove();
        return next;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onWorkerIdle, reason: merged with bridge method [inline-methods] */
    public synchronized void m991lambda$start$0$comtekartiksqfliteDatabaseWorkerPoolImpl(DatabaseWorker databaseWorker) {
        HashSet hashSet = new HashSet(this.idleWorkers);
        this.busyWorkers.remove(databaseWorker);
        this.idleWorkers.add(databaseWorker);
        if (!databaseWorker.isLastTaskInTransaction() && databaseWorker.lastTaskDatabaseId() != null) {
            this.onlyEligibleWorkers.remove(databaseWorker.lastTaskDatabaseId());
        }
        tryPostingTaskToWorker(databaseWorker);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            tryPostingTaskToWorker((DatabaseWorker) it.next());
        }
    }
}
