package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class DocumentReference {
    private final FirebaseFirestore firestore;
    private final DocumentKey key;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentReference(DocumentKey documentKey, FirebaseFirestore firebaseFirestore) {
        this.key = (DocumentKey) Preconditions.checkNotNull(documentKey);
        this.firestore = firebaseFirestore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DocumentReference forPath(ResourcePath resourcePath, FirebaseFirestore firebaseFirestore) {
        if (resourcePath.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid document reference. Document references must have an even number of segments, but " + resourcePath.canonicalString() + " has " + resourcePath.length());
        }
        return new DocumentReference(DocumentKey.fromPath(resourcePath), firebaseFirestore);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentKey getKey() {
        return this.key;
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public String getId() {
        return this.key.getDocumentId();
    }

    public CollectionReference getParent() {
        return new CollectionReference(this.key.getCollectionPath(), this.firestore);
    }

    public String getPath() {
        return this.key.getPath().canonicalString();
    }

    public CollectionReference collection(String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        return new CollectionReference(this.key.getPath().append(ResourcePath.fromString(str)), this.firestore);
    }

    public Task<Void> set(Object obj) {
        return set(obj, SetOptions.OVERWRITE);
    }

    public Task<Void> set(Object obj, SetOptions setOptions) {
        UserData.ParsedSetData parseSetData;
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        if (setOptions.isMerge()) {
            parseSetData = this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask());
        } else {
            parseSetData = this.firestore.getUserDataReader().parseSetData(obj);
        }
        return this.firestore.getClient().write(Collections.singletonList(parseSetData.toMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> update(Map<String, Object> map) {
        return update(this.firestore.getUserDataReader().parseUpdateData(map));
    }

    public Task<Void> update(String str, Object obj, Object... objArr) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public Task<Void> update(FieldPath fieldPath, Object obj, Object... objArr) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    private Task<Void> update(UserData.ParsedUpdateData parsedUpdateData) {
        return this.firestore.getClient().write(Collections.singletonList(parsedUpdateData.toMutation(this.key, Precondition.exists(true)))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> delete() {
        return this.firestore.getClient().write(Collections.singletonList(new DeleteMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<DocumentSnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<DocumentSnapshot> get(Source source) {
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentFromLocalCache(this.key).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.DocumentReference$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return DocumentReference.this.m749lambda$get$0$comgooglefirebasefirestoreDocumentReference(task);
                }
            });
        }
        return getViaSnapshotListener(source);
    }

    /* renamed from: lambda$get$0$com-google-firebase-firestore-DocumentReference, reason: not valid java name */
    public /* synthetic */ DocumentSnapshot m749lambda$get$0$comgooglefirebasefirestoreDocumentReference(Task task) throws Exception {
        Document document = (Document) task.getResult();
        return new DocumentSnapshot(this.firestore, this.key, document, true, document != null && document.hasLocalMutations());
    }

    private Task<DocumentSnapshot> getViaSnapshotListener(final Source source) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = true;
        listenOptions.includeQueryMetadataChanges = true;
        listenOptions.waitForSyncWhenOnline = true;
        taskCompletionSource2.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, listenOptions, null, new EventListener() { // from class: com.google.firebase.firestore.DocumentReference$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                DocumentReference.lambda$getViaSnapshotListener$1(TaskCompletionSource.this, taskCompletionSource2, source, (DocumentSnapshot) obj, firebaseFirestoreException);
            }
        }));
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, Source source, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            taskCompletionSource.setException(firebaseFirestoreException);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(taskCompletionSource2.getTask())).remove();
            if (!documentSnapshot.exists() && documentSnapshot.getMetadata().isFromCache()) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get document because the client is offline.", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else if (documentSnapshot.exists() && documentSnapshot.getMetadata().isFromCache() && source == Source.SERVER) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get document from server. (However, this document does exist in the local cache. Run again without setting source to SERVER to retrieve the cached document.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else {
                taskCompletionSource.setResult(documentSnapshot);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e, "Failed to register a listener for a single document", new Object[0]);
        } catch (ExecutionException e2) {
            throw Assert.fail(e2, "Failed to register a listener for a single document", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> eventListener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), null, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> eventListener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, eventListener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions listenOptions, Activity activity, final EventListener<DocumentSnapshot> eventListener) {
        AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: com.google.firebase.firestore.DocumentReference$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                DocumentReference.this.m748xb5c5a0f3(eventListener, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        });
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(asQuery(), listenOptions, asyncEventListener), asyncEventListener));
    }

    /* renamed from: lambda$addSnapshotListenerInternal$2$com-google-firebase-firestore-DocumentReference, reason: not valid java name */
    public /* synthetic */ void m748xb5c5a0f3(EventListener eventListener, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        DocumentSnapshot fromNoDocument;
        if (firebaseFirestoreException != null) {
            eventListener.onEvent(null, firebaseFirestoreException);
            return;
        }
        Assert.hardAssert(viewSnapshot != null, "Got event without value or error set", new Object[0]);
        Assert.hardAssert(viewSnapshot.getDocuments().size() <= 1, "Too many documents returned on a document query", new Object[0]);
        Document document = viewSnapshot.getDocuments().getDocument(this.key);
        if (document != null) {
            fromNoDocument = DocumentSnapshot.fromDocument(this.firestore, document, viewSnapshot.isFromCache(), viewSnapshot.getMutatedKeys().contains(document.getKey()));
        } else {
            fromNoDocument = DocumentSnapshot.fromNoDocument(this.firestore, this.key, viewSnapshot.isFromCache());
        }
        eventListener.onEvent(fromNoDocument, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentReference)) {
            return false;
        }
        DocumentReference documentReference = (DocumentReference) obj;
        return this.key.equals(documentReference.key) && this.firestore.equals(documentReference.firestore);
    }

    public int hashCode() {
        return (this.key.hashCode() * 31) + this.firestore.hashCode();
    }

    private com.google.firebase.firestore.core.Query asQuery() {
        return com.google.firebase.firestore.core.Query.atPath(this.key.getPath());
    }

    private static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.includeQueryMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.waitForSyncWhenOnline = false;
        return listenOptions;
    }
}
