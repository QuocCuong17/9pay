package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.bundle.BundleSerializer;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.v1.Value;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public final class FirestoreClient {
    private static final String LOG_TAG = "FirestoreClient";
    private static final int MAX_CONCURRENT_LIMBO_RESOLUTIONS = 100;
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private final BundleSerializer bundleSerializer;
    private final DatabaseInfo databaseInfo;
    private EventManager eventManager;
    private Scheduler gcScheduler;
    private Scheduler indexBackfillScheduler;
    private LocalStore localStore;
    private final GrpcMetadataProvider metadataProvider;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$3(String str) {
    }

    public FirestoreClient(final Context context, DatabaseInfo databaseInfo, final FirebaseFirestoreSettings firebaseFirestoreSettings, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, final AsyncQueue asyncQueue, GrpcMetadataProvider grpcMetadataProvider) {
        this.databaseInfo = databaseInfo;
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
        this.asyncQueue = asyncQueue;
        this.metadataProvider = grpcMetadataProvider;
        this.bundleSerializer = new BundleSerializer(new RemoteSerializer(databaseInfo.getDatabaseId()));
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m776lambda$new$0$comgooglefirebasefirestorecoreFirestoreClient(taskCompletionSource, context, firebaseFirestoreSettings);
            }
        });
        credentialsProvider.setChangeListener(new Listener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda18
            @Override // com.google.firebase.firestore.util.Listener
            public final void onValue(Object obj) {
                FirestoreClient.this.m778lambda$new$2$comgooglefirebasefirestorecoreFirestoreClient(atomicBoolean, taskCompletionSource, asyncQueue, (User) obj);
            }
        });
        credentialsProvider2.setChangeListener(new Listener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda19
            @Override // com.google.firebase.firestore.util.Listener
            public final void onValue(Object obj) {
                FirestoreClient.lambda$new$3((String) obj);
            }
        });
    }

    /* renamed from: lambda$new$0$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m776lambda$new$0$comgooglefirebasefirestorecoreFirestoreClient(TaskCompletionSource taskCompletionSource, Context context, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        try {
            initialize(context, (User) Tasks.await(taskCompletionSource.getTask()), firebaseFirestoreSettings);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: lambda$new$2$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m778lambda$new$2$comgooglefirebasefirestorecoreFirestoreClient(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue, final User user) {
        if (atomicBoolean.compareAndSet(false, true)) {
            Assert.hardAssert(!taskCompletionSource.getTask().isComplete(), "Already fulfilled first user task", new Object[0]);
            taskCompletionSource.setResult(user);
        } else {
            asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    FirestoreClient.this.m777lambda$new$1$comgooglefirebasefirestorecoreFirestoreClient(user);
                }
            });
        }
    }

    /* renamed from: lambda$new$1$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m777lambda$new$1$comgooglefirebasefirestorecoreFirestoreClient(User user) {
        Assert.hardAssert(this.syncEngine != null, "SyncEngine not yet initialized", new Object[0]);
        Logger.debug(LOG_TAG, "Credential changed. Current user: %s", user.getUid());
        this.syncEngine.handleCredentialChange(user);
    }

    public Task<Void> disableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m769x7ebb7879();
            }
        });
    }

    /* renamed from: lambda$disableNetwork$4$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m769x7ebb7879() {
        this.remoteStore.disableNetwork();
    }

    public Task<Void> enableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m770x445cc74d();
            }
        });
    }

    /* renamed from: lambda$enableNetwork$5$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m770x445cc74d() {
        this.remoteStore.enableNetwork();
    }

    public Task<Void> terminate() {
        this.authProvider.removeChangeListener();
        this.appCheckProvider.removeChangeListener();
        return this.asyncQueue.enqueueAndInitiateShutdown(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m783xd01fe2b6();
            }
        });
    }

    /* renamed from: lambda$terminate$6$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m783xd01fe2b6() {
        this.remoteStore.shutdown();
        this.persistence.shutdown();
        Scheduler scheduler = this.gcScheduler;
        if (scheduler != null) {
            scheduler.stop();
        }
        Scheduler scheduler2 = this.indexBackfillScheduler;
        if (scheduler2 != null) {
            scheduler2.stop();
        }
    }

    public boolean isTerminated() {
        return this.asyncQueue.isShuttingDown();
    }

    public QueryListener listen(Query query, EventManager.ListenOptions listenOptions, EventListener<ViewSnapshot> eventListener) {
        verifyNotTerminated();
        final QueryListener queryListener = new QueryListener(query, listenOptions, eventListener);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m774xbcc44995(queryListener);
            }
        });
        return queryListener;
    }

    /* renamed from: lambda$listen$7$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m774xbcc44995(QueryListener queryListener) {
        this.eventManager.addQueryListener(queryListener);
    }

    public void stopListening(final QueryListener queryListener) {
        if (isTerminated()) {
            return;
        }
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m782x3c424f7c(queryListener);
            }
        });
    }

    /* renamed from: lambda$stopListening$8$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m782x3c424f7c(QueryListener queryListener) {
        this.eventManager.removeQueryListener(queryListener);
    }

    public Task<Document> getDocumentFromLocalCache(final DocumentKey documentKey) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Callable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda16
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FirestoreClient.this.m771xcef7a7c2(documentKey);
            }
        }).continueWith(new Continuation() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirestoreClient.lambda$getDocumentFromLocalCache$10(task);
            }
        });
    }

    /* renamed from: lambda$getDocumentFromLocalCache$9$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ Document m771xcef7a7c2(DocumentKey documentKey) throws Exception {
        return this.localStore.readDocument(documentKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Document lambda$getDocumentFromLocalCache$10(Task task) throws Exception {
        Document document = (Document) task.getResult();
        if (document.isFoundDocument()) {
            return document;
        }
        if (document.isNoDocument()) {
            return null;
        }
        throw new FirebaseFirestoreException("Failed to get document from cache. (However, this document may exist on the server. Run again without setting source to CACHE to attempt to retrieve the document from the server.)", FirebaseFirestoreException.Code.UNAVAILABLE);
    }

    public Task<ViewSnapshot> getDocumentsFromLocalCache(final Query query) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Callable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda15
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FirestoreClient.this.m772x6c1df5c8(query);
            }
        });
    }

    /* renamed from: lambda$getDocumentsFromLocalCache$11$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ ViewSnapshot m772x6c1df5c8(Query query) throws Exception {
        QueryResult executeQuery = this.localStore.executeQuery(query, true);
        View view = new View(query, executeQuery.getRemoteKeys());
        return view.applyChanges(view.computeDocChanges(executeQuery.getDocuments())).getSnapshot();
    }

    public Task<Void> write(final List<Mutation> list) {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m786xabeef1a9(list, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$write$12$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m786xabeef1a9(List list, TaskCompletionSource taskCompletionSource) {
        this.syncEngine.writeMutations(list, taskCompletionSource);
    }

    public <TResult> Task<TResult> transaction(final TransactionOptions transactionOptions, final Function<Transaction, Task<TResult>> function) {
        verifyNotTerminated();
        return AsyncQueue.callTask(this.asyncQueue.getExecutor(), new Callable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda14
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FirestoreClient.this.m784xd83b2b87(transactionOptions, function);
            }
        });
    }

    /* renamed from: lambda$transaction$13$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ Task m784xd83b2b87(TransactionOptions transactionOptions, Function function) throws Exception {
        return this.syncEngine.transaction(this.asyncQueue, transactionOptions, function);
    }

    public Task<Map<String, Value>> runAggregateQuery(final Query query, final List<AggregateField> list) {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m780x8ccaf6ba(query, list, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$runAggregateQuery$16$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m780x8ccaf6ba(Query query, List list, final TaskCompletionSource taskCompletionSource) {
        this.syncEngine.runAggregateQuery(query, list).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda17
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                TaskCompletionSource.this.setResult((Map) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda11
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                TaskCompletionSource.this.setException(exc);
            }
        });
    }

    public Task<Void> waitForPendingWrites() {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m785x5cd85a36(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$waitForPendingWrites$17$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m785x5cd85a36(TaskCompletionSource taskCompletionSource) {
        this.syncEngine.registerPendingWritesTask(taskCompletionSource);
    }

    private void initialize(Context context, User user, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        ComponentProvider memoryComponentProvider;
        Logger.debug(LOG_TAG, "Initializing. user=%s", user.getUid());
        ComponentProvider.Configuration configuration = new ComponentProvider.Configuration(context, this.asyncQueue, this.databaseInfo, new Datastore(this.databaseInfo, this.asyncQueue, this.authProvider, this.appCheckProvider, context, this.metadataProvider), user, 100, firebaseFirestoreSettings);
        if (firebaseFirestoreSettings.isPersistenceEnabled()) {
            memoryComponentProvider = new SQLiteComponentProvider();
        } else {
            memoryComponentProvider = new MemoryComponentProvider();
        }
        memoryComponentProvider.initialize(configuration);
        this.persistence = memoryComponentProvider.getPersistence();
        this.gcScheduler = memoryComponentProvider.getGarbageCollectionScheduler();
        this.localStore = memoryComponentProvider.getLocalStore();
        this.remoteStore = memoryComponentProvider.getRemoteStore();
        this.syncEngine = memoryComponentProvider.getSyncEngine();
        this.eventManager = memoryComponentProvider.getEventManager();
        IndexBackfiller indexBackfiller = memoryComponentProvider.getIndexBackfiller();
        Scheduler scheduler = this.gcScheduler;
        if (scheduler != null) {
            scheduler.start();
        }
        if (indexBackfiller != null) {
            IndexBackfiller.Scheduler scheduler2 = indexBackfiller.getScheduler();
            this.indexBackfillScheduler = scheduler2;
            scheduler2.start();
        }
    }

    public void addSnapshotsInSyncListener(final EventListener<Void> eventListener) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m766x66bcffc0(eventListener);
            }
        });
    }

    /* renamed from: lambda$addSnapshotsInSyncListener$18$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m766x66bcffc0(EventListener eventListener) {
        this.eventManager.addSnapshotsInSyncListener(eventListener);
    }

    public void loadBundle(InputStream inputStream, final LoadBundleTask loadBundleTask) {
        verifyNotTerminated();
        final BundleReader bundleReader = new BundleReader(this.bundleSerializer, inputStream);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m775x9f576345(bundleReader, loadBundleTask);
            }
        });
    }

    /* renamed from: lambda$loadBundle$19$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m775x9f576345(BundleReader bundleReader, LoadBundleTask loadBundleTask) {
        this.syncEngine.loadBundle(bundleReader, loadBundleTask);
    }

    public Task<Query> getNamedQuery(final String str) {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m773xfe455012(str, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getNamedQuery$20$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m773xfe455012(String str, TaskCompletionSource taskCompletionSource) {
        NamedQuery namedQuery = this.localStore.getNamedQuery(str);
        if (namedQuery != null) {
            Target target = namedQuery.getBundledQuery().getTarget();
            taskCompletionSource.setResult(new Query(target.getPath(), target.getCollectionGroup(), target.getFilters(), target.getOrderBy(), target.getLimit(), namedQuery.getBundledQuery().getLimitType(), target.getStartAt(), target.getEndAt()));
        } else {
            taskCompletionSource.setResult(null);
        }
    }

    public Task<Void> configureFieldIndexes(final List<FieldIndex> list) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m767x7e96efd8(list);
            }
        });
    }

    /* renamed from: lambda$configureFieldIndexes$21$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m767x7e96efd8(List list) {
        this.localStore.configureFieldIndexes(list);
    }

    public void setIndexAutoCreationEnabled(final boolean z) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m781x6ec3220e(z);
            }
        });
    }

    /* renamed from: lambda$setIndexAutoCreationEnabled$22$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m781x6ec3220e(boolean z) {
        this.localStore.setIndexAutoCreationEnabled(z);
    }

    public void deleteAllFieldIndexes() {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m768x996f55a6();
            }
        });
    }

    /* renamed from: lambda$deleteAllFieldIndexes$23$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m768x996f55a6() {
        this.localStore.deleteAllFieldIndexes();
    }

    public void removeSnapshotsInSyncListener(final EventListener<Void> eventListener) {
        if (isTerminated()) {
            return;
        }
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FirestoreClient.this.m779x16f11d28(eventListener);
            }
        });
    }

    /* renamed from: lambda$removeSnapshotsInSyncListener$24$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    public /* synthetic */ void m779x16f11d28(EventListener eventListener) {
        this.eventManager.removeSnapshotsInSyncListener(eventListener);
    }

    private void verifyNotTerminated() {
        if (isTerminated()) {
            throw new IllegalStateException("The client has already been terminated");
        }
    }
}
