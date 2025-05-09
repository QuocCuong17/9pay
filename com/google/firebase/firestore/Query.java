package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import io.flutter.plugins.firebase.database.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class Query {
    final FirebaseFirestore firestore;
    final com.google.firebase.firestore.core.Query query;

    /* loaded from: classes4.dex */
    public enum Direction {
        ASCENDING,
        DESCENDING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Query(com.google.firebase.firestore.core.Query query, FirebaseFirestore firebaseFirestore) {
        this.query = (com.google.firebase.firestore.core.Query) Preconditions.checkNotNull(query);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public Query whereEqualTo(String str, Object obj) {
        return where(Filter.equalTo(str, obj));
    }

    public Query whereEqualTo(FieldPath fieldPath, Object obj) {
        return where(Filter.equalTo(fieldPath, obj));
    }

    public Query whereNotEqualTo(String str, Object obj) {
        return where(Filter.notEqualTo(str, obj));
    }

    public Query whereNotEqualTo(FieldPath fieldPath, Object obj) {
        return where(Filter.notEqualTo(fieldPath, obj));
    }

    public Query whereLessThan(String str, Object obj) {
        return where(Filter.lessThan(str, obj));
    }

    public Query whereLessThan(FieldPath fieldPath, Object obj) {
        return where(Filter.lessThan(fieldPath, obj));
    }

    public Query whereLessThanOrEqualTo(String str, Object obj) {
        return where(Filter.lessThanOrEqualTo(str, obj));
    }

    public Query whereLessThanOrEqualTo(FieldPath fieldPath, Object obj) {
        return where(Filter.lessThanOrEqualTo(fieldPath, obj));
    }

    public Query whereGreaterThan(String str, Object obj) {
        return where(Filter.greaterThan(str, obj));
    }

    public Query whereGreaterThan(FieldPath fieldPath, Object obj) {
        return where(Filter.greaterThan(fieldPath, obj));
    }

    public Query whereGreaterThanOrEqualTo(String str, Object obj) {
        return where(Filter.greaterThanOrEqualTo(str, obj));
    }

    public Query whereGreaterThanOrEqualTo(FieldPath fieldPath, Object obj) {
        return where(Filter.greaterThanOrEqualTo(fieldPath, obj));
    }

    public Query whereArrayContains(String str, Object obj) {
        return where(Filter.arrayContains(str, obj));
    }

    public Query whereArrayContains(FieldPath fieldPath, Object obj) {
        return where(Filter.arrayContains(fieldPath, obj));
    }

    public Query whereArrayContainsAny(String str, List<? extends Object> list) {
        return where(Filter.arrayContainsAny(str, list));
    }

    public Query whereArrayContainsAny(FieldPath fieldPath, List<? extends Object> list) {
        return where(Filter.arrayContainsAny(fieldPath, list));
    }

    public Query whereIn(String str, List<? extends Object> list) {
        return where(Filter.inArray(str, list));
    }

    public Query whereIn(FieldPath fieldPath, List<? extends Object> list) {
        return where(Filter.inArray(fieldPath, list));
    }

    public Query whereNotIn(String str, List<? extends Object> list) {
        return where(Filter.notInArray(str, list));
    }

    public Query whereNotIn(FieldPath fieldPath, List<? extends Object> list) {
        return where(Filter.notInArray(fieldPath, list));
    }

    public Query where(Filter filter) {
        com.google.firebase.firestore.core.Filter parseFilter = parseFilter(filter);
        if (parseFilter.getFilters().isEmpty()) {
            return this;
        }
        validateNewFilter(parseFilter);
        return new Query(this.query.filter(parseFilter), this.firestore);
    }

    private FieldFilter parseFieldFilter(Filter.UnaryFilter unaryFilter) {
        Value parseQueryValue;
        FieldPath field = unaryFilter.getField();
        FieldFilter.Operator operator = unaryFilter.getOperator();
        Object value = unaryFilter.getValue();
        Preconditions.checkNotNull(field, "Provided field path must not be null.");
        Preconditions.checkNotNull(operator, "Provided op must not be null.");
        if (field.getInternalPath().isKeyField()) {
            if (operator == FieldFilter.Operator.ARRAY_CONTAINS || operator == FieldFilter.Operator.ARRAY_CONTAINS_ANY) {
                throw new IllegalArgumentException("Invalid query. You can't perform '" + operator.toString() + "' queries on FieldPath.documentId().");
            }
            if (operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN) {
                validateDisjunctiveFilterElements(value, operator);
                ArrayValue.Builder newBuilder = ArrayValue.newBuilder();
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    newBuilder.addValues(parseDocumentIdValue(it.next()));
                }
                parseQueryValue = Value.newBuilder().setArrayValue(newBuilder).build();
            } else {
                parseQueryValue = parseDocumentIdValue(value);
            }
        } else {
            if (operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN || operator == FieldFilter.Operator.ARRAY_CONTAINS_ANY) {
                validateDisjunctiveFilterElements(value, operator);
            }
            parseQueryValue = this.firestore.getUserDataReader().parseQueryValue(value, operator == FieldFilter.Operator.IN || operator == FieldFilter.Operator.NOT_IN);
        }
        return FieldFilter.create(field.getInternalPath(), operator, parseQueryValue);
    }

    private com.google.firebase.firestore.core.Filter parseCompositeFilter(Filter.CompositeFilter compositeFilter) {
        ArrayList arrayList = new ArrayList();
        Iterator<Filter> it = compositeFilter.getFilters().iterator();
        while (it.hasNext()) {
            com.google.firebase.firestore.core.Filter parseFilter = parseFilter(it.next());
            if (!parseFilter.getFilters().isEmpty()) {
                arrayList.add(parseFilter);
            }
        }
        if (arrayList.size() == 1) {
            return (com.google.firebase.firestore.core.Filter) arrayList.get(0);
        }
        return new CompositeFilter(arrayList, compositeFilter.getOperator());
    }

    private com.google.firebase.firestore.core.Filter parseFilter(Filter filter) {
        boolean z = filter instanceof Filter.UnaryFilter;
        Assert.hardAssert(z || (filter instanceof Filter.CompositeFilter), "Parsing is only supported for Filter.UnaryFilter and Filter.CompositeFilter.", new Object[0]);
        if (z) {
            return parseFieldFilter((Filter.UnaryFilter) filter);
        }
        return parseCompositeFilter((Filter.CompositeFilter) filter);
    }

    private void validateOrderByField(com.google.firebase.firestore.model.FieldPath fieldPath) {
        com.google.firebase.firestore.model.FieldPath inequalityField = this.query.inequalityField();
        if (this.query.getFirstOrderByField() != null || inequalityField == null) {
            return;
        }
        validateOrderByFieldMatchesInequality(fieldPath, inequalityField);
    }

    private Value parseDocumentIdValue(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid document ID, but it was an empty string.");
            }
            if (!this.query.isCollectionGroupQuery() && str.contains(RemoteSettings.FORWARD_SLASH_STRING)) {
                throw new IllegalArgumentException("Invalid query. When querying a collection by FieldPath.documentId() you must provide a plain document ID, but '" + str + "' contains a '/' character.");
            }
            ResourcePath append = this.query.getPath().append(ResourcePath.fromString(str));
            if (!DocumentKey.isDocumentKey(append)) {
                throw new IllegalArgumentException("Invalid query. When querying a collection group by FieldPath.documentId(), the value provided must result in a valid document path, but '" + append + "' is not because it has an odd number of segments (" + append.length() + ").");
            }
            return Values.refValue(getFirestore().getDatabaseId(), DocumentKey.fromPath(append));
        }
        if (obj instanceof DocumentReference) {
            return Values.refValue(getFirestore().getDatabaseId(), ((DocumentReference) obj).getKey());
        }
        throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid String or DocumentReference, but it was of type: " + Util.typeName(obj));
    }

    private void validateDisjunctiveFilterElements(Object obj, FieldFilter.Operator operator) {
        if (!(obj instanceof List) || ((List) obj).size() == 0) {
            throw new IllegalArgumentException("Invalid Query. A non-empty array is required for '" + operator.toString() + "' filters.");
        }
    }

    private void validateOrderByFieldMatchesInequality(com.google.firebase.firestore.model.FieldPath fieldPath, com.google.firebase.firestore.model.FieldPath fieldPath2) {
        if (fieldPath.equals(fieldPath2)) {
            return;
        }
        String canonicalString = fieldPath2.canonicalString();
        throw new IllegalArgumentException(String.format("Invalid query. You have an inequality where filter (whereLessThan(), whereGreaterThan(), etc.) on field '%s' and so you must also have '%s' as your first orderBy() field, but your first orderBy() is currently on field '%s' instead.", canonicalString, canonicalString, fieldPath.canonicalString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.Query$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator;

        static {
            int[] iArr = new int[FieldFilter.Operator.values().length];
            $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = iArr;
            try {
                iArr[FieldFilter.Operator.NOT_EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.IN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[FieldFilter.Operator.NOT_IN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private List<FieldFilter.Operator> conflictingOps(FieldFilter.Operator operator) {
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[operator.ordinal()];
        return i != 1 ? (i == 2 || i == 3) ? Arrays.asList(FieldFilter.Operator.NOT_IN) : i != 4 ? new ArrayList() : Arrays.asList(FieldFilter.Operator.ARRAY_CONTAINS_ANY, FieldFilter.Operator.IN, FieldFilter.Operator.NOT_IN, FieldFilter.Operator.NOT_EQUAL) : Arrays.asList(FieldFilter.Operator.NOT_EQUAL, FieldFilter.Operator.NOT_IN);
    }

    private void validateNewFieldFilter(com.google.firebase.firestore.core.Query query, FieldFilter fieldFilter) {
        FieldFilter.Operator operator = fieldFilter.getOperator();
        if (fieldFilter.isInequality()) {
            com.google.firebase.firestore.model.FieldPath inequalityField = query.inequalityField();
            com.google.firebase.firestore.model.FieldPath field = fieldFilter.getField();
            if (inequalityField != null && !inequalityField.equals(field)) {
                throw new IllegalArgumentException(String.format("All where filters with an inequality (notEqualTo, notIn, lessThan, lessThanOrEqualTo, greaterThan, or greaterThanOrEqualTo) must be on the same field. But you have filters on '%s' and '%s'", inequalityField.canonicalString(), field.canonicalString()));
            }
            com.google.firebase.firestore.model.FieldPath firstOrderByField = query.getFirstOrderByField();
            if (firstOrderByField != null) {
                validateOrderByFieldMatchesInequality(firstOrderByField, field);
            }
        }
        FieldFilter.Operator findOpInsideFilters = findOpInsideFilters(query.getFilters(), conflictingOps(operator));
        if (findOpInsideFilters != null) {
            if (findOpInsideFilters == operator) {
                throw new IllegalArgumentException("Invalid Query. You cannot use more than one '" + operator.toString() + "' filter.");
            }
            throw new IllegalArgumentException("Invalid Query. You cannot use '" + operator.toString() + "' filters with '" + findOpInsideFilters.toString() + "' filters.");
        }
    }

    private void validateNewFilter(com.google.firebase.firestore.core.Filter filter) {
        com.google.firebase.firestore.core.Query query = this.query;
        for (FieldFilter fieldFilter : filter.getFlattenedFilters()) {
            validateNewFieldFilter(query, fieldFilter);
            query = query.filter(fieldFilter);
        }
    }

    private FieldFilter.Operator findOpInsideFilters(List<com.google.firebase.firestore.core.Filter> list, List<FieldFilter.Operator> list2) {
        Iterator<com.google.firebase.firestore.core.Filter> it = list.iterator();
        while (it.hasNext()) {
            for (FieldFilter fieldFilter : it.next().getFlattenedFilters()) {
                if (list2.contains(fieldFilter.getOperator())) {
                    return fieldFilter.getOperator();
                }
            }
        }
        return null;
    }

    public Query orderBy(String str) {
        return orderBy(FieldPath.fromDotSeparatedPath(str), Direction.ASCENDING);
    }

    public Query orderBy(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), Direction.ASCENDING);
    }

    public Query orderBy(String str, Direction direction) {
        return orderBy(FieldPath.fromDotSeparatedPath(str), direction);
    }

    public Query orderBy(FieldPath fieldPath, Direction direction) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), direction);
    }

    private Query orderBy(com.google.firebase.firestore.model.FieldPath fieldPath, Direction direction) {
        OrderBy.Direction direction2;
        Preconditions.checkNotNull(direction, "Provided direction must not be null.");
        if (this.query.getStartAt() != null) {
            throw new IllegalArgumentException("Invalid query. You must not call Query.startAt() or Query.startAfter() before calling Query.orderBy().");
        }
        if (this.query.getEndAt() != null) {
            throw new IllegalArgumentException("Invalid query. You must not call Query.endAt() or Query.endBefore() before calling Query.orderBy().");
        }
        validateOrderByField(fieldPath);
        if (direction == Direction.ASCENDING) {
            direction2 = OrderBy.Direction.ASCENDING;
        } else {
            direction2 = OrderBy.Direction.DESCENDING;
        }
        return new Query(this.query.orderBy(OrderBy.getInstance(direction2, fieldPath)), this.firestore);
    }

    public Query limit(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid Query. Query limit (" + j + ") is invalid. Limit must be positive.");
        }
        return new Query(this.query.limitToFirst(j), this.firestore);
    }

    public Query limitToLast(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid Query. Query limitToLast (" + j + ") is invalid. Limit must be positive.");
        }
        return new Query(this.query.limitToLast(j), this.firestore);
    }

    public Query startAt(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.startAt(boundFromDocumentSnapshot(Constants.START_AT, documentSnapshot, true)), this.firestore);
    }

    public Query startAt(Object... objArr) {
        return new Query(this.query.startAt(boundFromFields(Constants.START_AT, objArr, true)), this.firestore);
    }

    public Query startAfter(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.startAt(boundFromDocumentSnapshot(Constants.START_AFTER, documentSnapshot, false)), this.firestore);
    }

    public Query startAfter(Object... objArr) {
        return new Query(this.query.startAt(boundFromFields(Constants.START_AFTER, objArr, false)), this.firestore);
    }

    public Query endBefore(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.endAt(boundFromDocumentSnapshot(Constants.END_BEFORE, documentSnapshot, false)), this.firestore);
    }

    public Query endBefore(Object... objArr) {
        return new Query(this.query.endAt(boundFromFields(Constants.END_BEFORE, objArr, false)), this.firestore);
    }

    public Query endAt(DocumentSnapshot documentSnapshot) {
        return new Query(this.query.endAt(boundFromDocumentSnapshot(Constants.END_AT, documentSnapshot, true)), this.firestore);
    }

    public Query endAt(Object... objArr) {
        return new Query(this.query.endAt(boundFromFields(Constants.END_AT, objArr, true)), this.firestore);
    }

    private Bound boundFromDocumentSnapshot(String str, DocumentSnapshot documentSnapshot, boolean z) {
        Preconditions.checkNotNull(documentSnapshot, "Provided snapshot must not be null.");
        if (!documentSnapshot.exists()) {
            throw new IllegalArgumentException("Can't use a DocumentSnapshot for a document that doesn't exist for " + str + "().");
        }
        Document document = documentSnapshot.getDocument();
        ArrayList arrayList = new ArrayList();
        for (OrderBy orderBy : this.query.getOrderBy()) {
            if (orderBy.getField().equals(com.google.firebase.firestore.model.FieldPath.KEY_PATH)) {
                arrayList.add(Values.refValue(this.firestore.getDatabaseId(), document.getKey()));
            } else {
                Value field = document.getField(orderBy.getField());
                if (ServerTimestamps.isServerTimestamp(field)) {
                    throw new IllegalArgumentException("Invalid query. You are trying to start or end a query using a document for which the field '" + orderBy.getField() + "' is an uncommitted server timestamp. (Since the value of this field is unknown, you cannot start/end a query with it.)");
                }
                if (field != null) {
                    arrayList.add(field);
                } else {
                    throw new IllegalArgumentException("Invalid query. You are trying to start or end a query using a document for which the field '" + orderBy.getField() + "' (used as the orderBy) does not exist.");
                }
            }
        }
        return new Bound(arrayList, z);
    }

    private Bound boundFromFields(String str, Object[] objArr, boolean z) {
        List<OrderBy> explicitOrderBy = this.query.getExplicitOrderBy();
        if (objArr.length > explicitOrderBy.size()) {
            throw new IllegalArgumentException("Too many arguments provided to " + str + "(). The number of arguments must be less than or equal to the number of orderBy() clauses.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (explicitOrderBy.get(i).getField().equals(com.google.firebase.firestore.model.FieldPath.KEY_PATH)) {
                if (!(obj instanceof String)) {
                    throw new IllegalArgumentException("Invalid query. Expected a string for document ID in " + str + "(), but got " + obj + ".");
                }
                String str2 = (String) obj;
                if (!this.query.isCollectionGroupQuery() && str2.contains(RemoteSettings.FORWARD_SLASH_STRING)) {
                    throw new IllegalArgumentException("Invalid query. When querying a collection and ordering by FieldPath.documentId(), the value passed to " + str + "() must be a plain document ID, but '" + str2 + "' contains a slash.");
                }
                ResourcePath append = this.query.getPath().append(ResourcePath.fromString(str2));
                if (!DocumentKey.isDocumentKey(append)) {
                    throw new IllegalArgumentException("Invalid query. When querying a collection group and ordering by FieldPath.documentId(), the value passed to " + str + "() must result in a valid document path, but '" + append + "' is not because it contains an odd number of segments.");
                }
                arrayList.add(Values.refValue(this.firestore.getDatabaseId(), DocumentKey.fromPath(append)));
            } else {
                arrayList.add(this.firestore.getUserDataReader().parseQueryValue(obj));
            }
        }
        return new Bound(arrayList, z);
    }

    public Task<QuerySnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<QuerySnapshot> get(Source source) {
        validateHasExplicitOrderByForLimitToLast();
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentsFromLocalCache(this.query).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.Query$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return Query.this.m758lambda$get$0$comgooglefirebasefirestoreQuery(task);
                }
            });
        }
        return getViaSnapshotListener(source);
    }

    /* renamed from: lambda$get$0$com-google-firebase-firestore-Query, reason: not valid java name */
    public /* synthetic */ QuerySnapshot m758lambda$get$0$comgooglefirebasefirestoreQuery(Task task) throws Exception {
        return new QuerySnapshot(new Query(this.query, this.firestore), (ViewSnapshot) task.getResult(), this.firestore);
    }

    private Task<QuerySnapshot> getViaSnapshotListener(final Source source) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = true;
        listenOptions.includeQueryMetadataChanges = true;
        listenOptions.waitForSyncWhenOnline = true;
        taskCompletionSource2.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, listenOptions, null, new EventListener() { // from class: com.google.firebase.firestore.Query$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                Query.lambda$getViaSnapshotListener$1(TaskCompletionSource.this, taskCompletionSource2, source, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        }));
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, Source source, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            taskCompletionSource.setException(firebaseFirestoreException);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(taskCompletionSource2.getTask())).remove();
            if (querySnapshot.getMetadata().isFromCache() && source == Source.SERVER) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get documents from server. (However, these documents may exist in the local cache. Run again without setting source to SERVER to retrieve the cached documents.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else {
                taskCompletionSource.setResult(querySnapshot);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e, "Failed to register a listener for a query result", new Object[0]);
        } catch (ExecutionException e2) {
            throw Assert.fail(e2, "Failed to register a listener for a query result", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, eventListener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<QuerySnapshot> eventListener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<QuerySnapshot> eventListener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), null, eventListener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<QuerySnapshot> eventListener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, eventListener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions listenOptions, Activity activity, final EventListener<QuerySnapshot> eventListener) {
        validateHasExplicitOrderByForLimitToLast();
        AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: com.google.firebase.firestore.Query$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                Query.this.m757xb1c289cb(eventListener, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        });
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(this.query, listenOptions, asyncEventListener), asyncEventListener));
    }

    /* renamed from: lambda$addSnapshotListenerInternal$2$com-google-firebase-firestore-Query, reason: not valid java name */
    public /* synthetic */ void m757xb1c289cb(EventListener eventListener, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventListener.onEvent(null, firebaseFirestoreException);
        } else {
            Assert.hardAssert(viewSnapshot != null, "Got event without value or error set", new Object[0]);
            eventListener.onEvent(new QuerySnapshot(this, viewSnapshot, this.firestore), null);
        }
    }

    private void validateHasExplicitOrderByForLimitToLast() {
        if (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_LAST) && this.query.getExplicitOrderBy().isEmpty()) {
            throw new IllegalStateException("limitToLast() queries require specifying at least one orderBy() clause");
        }
    }

    public AggregateQuery count() {
        return new AggregateQuery(this, Collections.singletonList(AggregateField.count()));
    }

    public AggregateQuery aggregate(AggregateField aggregateField, AggregateField... aggregateFieldArr) {
        ArrayList<AggregateField> arrayList = new ArrayList<AggregateField>(aggregateField) { // from class: com.google.firebase.firestore.Query.1
            final /* synthetic */ AggregateField val$aggregateField;

            {
                this.val$aggregateField = aggregateField;
                add(aggregateField);
            }
        };
        arrayList.addAll(Arrays.asList(aggregateFieldArr));
        return new AggregateQuery(this, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Query)) {
            return false;
        }
        Query query = (Query) obj;
        return this.query.equals(query.query) && this.firestore.equals(query.firestore);
    }

    public int hashCode() {
        return (this.query.hashCode() * 31) + this.firestore.hashCode();
    }

    private static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.includeQueryMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.waitForSyncWhenOnline = false;
        return listenOptions;
    }
}
