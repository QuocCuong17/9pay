package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes4.dex */
public class WriteBatch {
    private final FirebaseFirestore firestore;
    private final ArrayList<Mutation> mutations = new ArrayList<>();
    private boolean committed = false;

    /* loaded from: classes4.dex */
    public interface Function {
        void apply(WriteBatch writeBatch);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteBatch(FirebaseFirestore firebaseFirestore) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
    }

    public WriteBatch set(DocumentReference documentReference, Object obj) {
        return set(documentReference, obj, SetOptions.OVERWRITE);
    }

    public WriteBatch set(DocumentReference documentReference, Object obj, SetOptions setOptions) {
        UserData.ParsedSetData parseSetData;
        this.firestore.validateReference(documentReference);
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        verifyNotCommitted();
        if (setOptions.isMerge()) {
            parseSetData = this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask());
        } else {
            parseSetData = this.firestore.getUserDataReader().parseSetData(obj);
        }
        this.mutations.add(parseSetData.toMutation(documentReference.getKey(), Precondition.NONE));
        return this;
    }

    public WriteBatch update(DocumentReference documentReference, Map<String, Object> map) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(map));
    }

    public WriteBatch update(DocumentReference documentReference, String str, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public WriteBatch update(DocumentReference documentReference, FieldPath fieldPath, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    private WriteBatch update(DocumentReference documentReference, UserData.ParsedUpdateData parsedUpdateData) {
        this.firestore.validateReference(documentReference);
        verifyNotCommitted();
        this.mutations.add(parsedUpdateData.toMutation(documentReference.getKey(), Precondition.exists(true)));
        return this;
    }

    public WriteBatch delete(DocumentReference documentReference) {
        this.firestore.validateReference(documentReference);
        verifyNotCommitted();
        this.mutations.add(new DeleteMutation(documentReference.getKey(), Precondition.NONE));
        return this;
    }

    public Task<Void> commit() {
        verifyNotCommitted();
        this.committed = true;
        if (this.mutations.size() > 0) {
            return this.firestore.getClient().write(this.mutations);
        }
        return Tasks.forResult(null);
    }

    private void verifyNotCommitted() {
        if (this.committed) {
            throw new IllegalStateException("A write batch can no longer be used after commit() has been called.");
        }
    }
}
