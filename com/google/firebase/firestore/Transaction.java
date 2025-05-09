package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
public class Transaction {
    private final FirebaseFirestore firestore;
    private final com.google.firebase.firestore.core.Transaction transaction;

    /* loaded from: classes4.dex */
    public interface Function<TResult> {
        TResult apply(Transaction transaction) throws FirebaseFirestoreException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Transaction(com.google.firebase.firestore.core.Transaction transaction, FirebaseFirestore firebaseFirestore) {
        this.transaction = (com.google.firebase.firestore.core.Transaction) Preconditions.checkNotNull(transaction);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
    }

    public Transaction set(DocumentReference documentReference, Object obj) {
        return set(documentReference, obj, SetOptions.OVERWRITE);
    }

    public Transaction set(DocumentReference documentReference, Object obj, SetOptions setOptions) {
        UserData.ParsedSetData parseSetData;
        this.firestore.validateReference(documentReference);
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        if (setOptions.isMerge()) {
            parseSetData = this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask());
        } else {
            parseSetData = this.firestore.getUserDataReader().parseSetData(obj);
        }
        this.transaction.set(documentReference.getKey(), parseSetData);
        return this;
    }

    public Transaction update(DocumentReference documentReference, Map<String, Object> map) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(map));
    }

    public Transaction update(DocumentReference documentReference, String str, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public Transaction update(DocumentReference documentReference, FieldPath fieldPath, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    private Transaction update(DocumentReference documentReference, UserData.ParsedUpdateData parsedUpdateData) {
        this.firestore.validateReference(documentReference);
        this.transaction.update(documentReference.getKey(), parsedUpdateData);
        return this;
    }

    public Transaction delete(DocumentReference documentReference) {
        this.firestore.validateReference(documentReference);
        this.transaction.delete(documentReference.getKey());
        return this;
    }

    private Task<DocumentSnapshot> getAsync(DocumentReference documentReference) {
        return this.transaction.lookup(Collections.singletonList(documentReference.getKey())).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.Transaction$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return Transaction.this.m759lambda$getAsync$0$comgooglefirebasefirestoreTransaction(task);
            }
        });
    }

    /* renamed from: lambda$getAsync$0$com-google-firebase-firestore-Transaction, reason: not valid java name */
    public /* synthetic */ DocumentSnapshot m759lambda$getAsync$0$comgooglefirebasefirestoreTransaction(Task task) throws Exception {
        if (!task.isSuccessful()) {
            throw task.getException();
        }
        List list = (List) task.getResult();
        if (list.size() != 1) {
            throw Assert.fail("Mismatch in docs returned from document lookup.", new Object[0]);
        }
        MutableDocument mutableDocument = (MutableDocument) list.get(0);
        if (mutableDocument.isFoundDocument()) {
            return DocumentSnapshot.fromDocument(this.firestore, mutableDocument, false, false);
        }
        if (mutableDocument.isNoDocument()) {
            return DocumentSnapshot.fromNoDocument(this.firestore, mutableDocument.getKey(), false);
        }
        throw Assert.fail("BatchGetDocumentsRequest returned unexpected document type: " + mutableDocument.getClass().getCanonicalName(), new Object[0]);
    }

    public DocumentSnapshot get(DocumentReference documentReference) throws FirebaseFirestoreException {
        this.firestore.validateReference(documentReference);
        try {
            return (DocumentSnapshot) Tasks.await(getAsync(documentReference));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof FirebaseFirestoreException) {
                throw ((FirebaseFirestoreException) e2.getCause());
            }
            throw new RuntimeException(e2.getCause());
        }
    }
}
