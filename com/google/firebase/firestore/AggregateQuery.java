package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public class AggregateQuery {
    private final List<AggregateField> aggregateFieldList;
    private final Query query;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AggregateQuery(Query query, List<AggregateField> list) {
        this.query = query;
        this.aggregateFieldList = list;
    }

    public Query getQuery() {
        return this.query;
    }

    public List<AggregateField> getAggregateFields() {
        return this.aggregateFieldList;
    }

    public Task<AggregateQuerySnapshot> get(AggregateSource aggregateSource) {
        Preconditions.checkNotNull(aggregateSource, "AggregateSource must not be null");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.query.firestore.getClient().runAggregateQuery(this.query.query, this.aggregateFieldList).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.AggregateQuery$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return AggregateQuery.this.m747lambda$get$0$comgooglefirebasefirestoreAggregateQuery(taskCompletionSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$get$0$com-google-firebase-firestore-AggregateQuery, reason: not valid java name */
    public /* synthetic */ Object m747lambda$get$0$comgooglefirebasefirestoreAggregateQuery(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(new AggregateQuerySnapshot(this, (Map) task.getResult()));
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateQuery)) {
            return false;
        }
        AggregateQuery aggregateQuery = (AggregateQuery) obj;
        return this.query.equals(aggregateQuery.query) && this.aggregateFieldList.equals(aggregateQuery.aggregateFieldList);
    }

    public int hashCode() {
        return Objects.hash(this.query, this.aggregateFieldList);
    }
}
