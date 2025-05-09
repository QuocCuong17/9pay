package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class Transaction {
    private static final Executor defaultExecutor = createDefaultExecutor();
    private boolean committed;
    private final Datastore datastore;
    private FirebaseFirestoreException lastWriteError;
    private final HashMap<DocumentKey, SnapshotVersion> readVersions = new HashMap<>();
    private final ArrayList<Mutation> mutations = new ArrayList<>();
    private Set<DocumentKey> writtenDocs = new HashSet();

    public Transaction(Datastore datastore) {
        this.datastore = datastore;
    }

    public Task<List<MutableDocument>> lookup(List<DocumentKey> list) {
        ensureCommitNotCalled();
        if (this.mutations.size() != 0) {
            return Tasks.forException(new FirebaseFirestoreException("Firestore transactions require all reads to be executed before all writes.", FirebaseFirestoreException.Code.INVALID_ARGUMENT));
        }
        return this.datastore.lookup(list).continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.core.Transaction$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return Transaction.this.m787lambda$lookup$0$comgooglefirebasefirestorecoreTransaction(task);
            }
        });
    }

    /* renamed from: lambda$lookup$0$com-google-firebase-firestore-core-Transaction, reason: not valid java name */
    public /* synthetic */ Task m787lambda$lookup$0$comgooglefirebasefirestorecoreTransaction(Task task) throws Exception {
        if (task.isSuccessful()) {
            Iterator it = ((List) task.getResult()).iterator();
            while (it.hasNext()) {
                recordVersion((MutableDocument) it.next());
            }
        }
        return task;
    }

    public void set(DocumentKey documentKey, UserData.ParsedSetData parsedSetData) {
        write(Collections.singletonList(parsedSetData.toMutation(documentKey, precondition(documentKey))));
        this.writtenDocs.add(documentKey);
    }

    public void update(DocumentKey documentKey, UserData.ParsedUpdateData parsedUpdateData) {
        try {
            write(Collections.singletonList(parsedUpdateData.toMutation(documentKey, preconditionForUpdate(documentKey))));
        } catch (FirebaseFirestoreException e) {
            this.lastWriteError = e;
        }
        this.writtenDocs.add(documentKey);
    }

    public void delete(DocumentKey documentKey) {
        write(Collections.singletonList(new DeleteMutation(documentKey, precondition(documentKey))));
        this.writtenDocs.add(documentKey);
    }

    public Task<Void> commit() {
        ensureCommitNotCalled();
        FirebaseFirestoreException firebaseFirestoreException = this.lastWriteError;
        if (firebaseFirestoreException != null) {
            return Tasks.forException(firebaseFirestoreException);
        }
        HashSet hashSet = new HashSet(this.readVersions.keySet());
        Iterator<Mutation> it = this.mutations.iterator();
        while (it.hasNext()) {
            hashSet.remove(it.next().getKey());
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            DocumentKey documentKey = (DocumentKey) it2.next();
            this.mutations.add(new VerifyMutation(documentKey, precondition(documentKey)));
        }
        this.committed = true;
        return this.datastore.commit(this.mutations).continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.core.Transaction$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return Transaction.lambda$commit$1(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Task lambda$commit$1(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(null);
        }
        return Tasks.forException(task.getException());
    }

    private static Executor createDefaultExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    private void recordVersion(MutableDocument mutableDocument) throws FirebaseFirestoreException {
        SnapshotVersion snapshotVersion;
        if (mutableDocument.isFoundDocument()) {
            snapshotVersion = mutableDocument.getVersion();
        } else if (mutableDocument.isNoDocument()) {
            snapshotVersion = SnapshotVersion.NONE;
        } else {
            throw Assert.fail("Unexpected document type in transaction: " + mutableDocument, new Object[0]);
        }
        if (this.readVersions.containsKey(mutableDocument.getKey())) {
            if (!this.readVersions.get(mutableDocument.getKey()).equals(mutableDocument.getVersion())) {
                throw new FirebaseFirestoreException("Document version changed between two reads.", FirebaseFirestoreException.Code.ABORTED);
            }
        } else {
            this.readVersions.put(mutableDocument.getKey(), snapshotVersion);
        }
    }

    private Precondition precondition(DocumentKey documentKey) {
        SnapshotVersion snapshotVersion = this.readVersions.get(documentKey);
        if (!this.writtenDocs.contains(documentKey) && snapshotVersion != null) {
            if (snapshotVersion.equals(SnapshotVersion.NONE)) {
                return Precondition.exists(false);
            }
            return Precondition.updateTime(snapshotVersion);
        }
        return Precondition.NONE;
    }

    private Precondition preconditionForUpdate(DocumentKey documentKey) throws FirebaseFirestoreException {
        SnapshotVersion snapshotVersion = this.readVersions.get(documentKey);
        if (!this.writtenDocs.contains(documentKey) && snapshotVersion != null) {
            if (snapshotVersion != null && snapshotVersion.equals(SnapshotVersion.NONE)) {
                throw new FirebaseFirestoreException("Can't update a document that doesn't exist.", FirebaseFirestoreException.Code.INVALID_ARGUMENT);
            }
            return Precondition.updateTime(snapshotVersion);
        }
        return Precondition.exists(true);
    }

    private void write(List<Mutation> list) {
        ensureCommitNotCalled();
        this.mutations.addAll(list);
    }

    private void ensureCommitNotCalled() {
        Assert.hardAssert(!this.committed, "A transaction object cannot be used after its update callback has been invoked.", new Object[0]);
    }

    public static Executor getDefaultExecutor() {
        return defaultExecutor;
    }
}
