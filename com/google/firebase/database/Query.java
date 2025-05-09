package com.google.firebase.database;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.ChildEventRegistration;
import com.google.firebase.database.core.EventRegistration;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.Repo;
import com.google.firebase.database.core.ValueEventRegistration;
import com.google.firebase.database.core.ZombieEventManager;
import com.google.firebase.database.core.utilities.PushIdGenerator;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.KeyIndex;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.PathIndex;
import com.google.firebase.database.snapshot.PriorityIndex;
import com.google.firebase.database.snapshot.PriorityUtilities;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.database.snapshot.ValueIndex;

/* loaded from: classes4.dex */
public class Query {
    private final boolean orderByCalled;
    protected final QueryParams params;
    protected final Path path;
    protected final Repo repo;

    Query(Repo repo, Path path, QueryParams queryParams, boolean z) throws DatabaseException {
        this.repo = repo;
        this.path = path;
        this.params = queryParams;
        this.orderByCalled = z;
        Utilities.hardAssert(queryParams.isValid(), "Validation of queries failed.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Query(Repo repo, Path path) {
        this.repo = repo;
        this.path = path;
        this.params = QueryParams.DEFAULT_PARAMS;
        this.orderByCalled = false;
    }

    private void validateQueryEndpoints(QueryParams queryParams) {
        if (queryParams.getIndex().equals(KeyIndex.getInstance())) {
            if (queryParams.hasStart()) {
                Node indexStartValue = queryParams.getIndexStartValue();
                if (!Objects.equal(queryParams.getIndexStartName(), ChildKey.getMinName()) || !(indexStartValue instanceof StringNode)) {
                    throw new IllegalArgumentException("You must use startAt(String value), startAfter(String value), endAt(String value), endBefore(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
            if (queryParams.hasEnd()) {
                Node indexEndValue = queryParams.getIndexEndValue();
                if (!queryParams.getIndexEndName().equals(ChildKey.getMaxName()) || !(indexEndValue instanceof StringNode)) {
                    throw new IllegalArgumentException("You must use startAt(String value), startAfter(String value), endAt(String value), endBefore(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
                return;
            }
            return;
        }
        if (queryParams.getIndex().equals(PriorityIndex.getInstance())) {
            if ((queryParams.hasStart() && !PriorityUtilities.isValidPriority(queryParams.getIndexStartValue())) || (queryParams.hasEnd() && !PriorityUtilities.isValidPriority(queryParams.getIndexEndValue()))) {
                throw new IllegalArgumentException("When using orderByPriority(), values provided to startAt(), startAfter(), endAt(), endBefore(), or equalTo() must be valid priorities.");
            }
        }
    }

    private void validateLimit(QueryParams queryParams) {
        if (queryParams.hasStart() && queryParams.hasEnd() && queryParams.hasLimit() && !queryParams.hasAnchoredLimit()) {
            throw new IllegalArgumentException("Can't combine startAt(), startAfter(), endAt(), endBefore(), and limit(). Use limitToFirst() or limitToLast() instead");
        }
    }

    private void validateEqualToCall() {
        if (this.params.hasStart()) {
            throw new IllegalArgumentException("Cannot combine equalTo() with startAt() or startAfter()");
        }
        if (this.params.hasEnd()) {
            throw new IllegalArgumentException("Cannot combine equalTo() with endAt() or endBefore()");
        }
    }

    private void validateNoOrderByCall() {
        if (this.orderByCalled) {
            throw new IllegalArgumentException("You can't combine multiple orderBy calls!");
        }
    }

    public ValueEventListener addValueEventListener(ValueEventListener valueEventListener) {
        addEventRegistration(new ValueEventRegistration(this.repo, valueEventListener, getSpec()));
        return valueEventListener;
    }

    public ChildEventListener addChildEventListener(ChildEventListener childEventListener) {
        addEventRegistration(new ChildEventRegistration(this.repo, childEventListener, getSpec()));
        return childEventListener;
    }

    public Task<DataSnapshot> get() {
        return this.repo.getValue(this);
    }

    public void addListenerForSingleValueEvent(final ValueEventListener valueEventListener) {
        addEventRegistration(new ValueEventRegistration(this.repo, new ValueEventListener() { // from class: com.google.firebase.database.Query.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                Query.this.removeEventListener(this);
                valueEventListener.onDataChange(dataSnapshot);
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
                valueEventListener.onCancelled(databaseError);
            }
        }, getSpec()));
    }

    public void removeEventListener(ValueEventListener valueEventListener) {
        java.util.Objects.requireNonNull(valueEventListener, "listener must not be null");
        removeEventRegistration(new ValueEventRegistration(this.repo, valueEventListener, getSpec()));
    }

    public void removeEventListener(ChildEventListener childEventListener) {
        java.util.Objects.requireNonNull(childEventListener, "listener must not be null");
        removeEventRegistration(new ChildEventRegistration(this.repo, childEventListener, getSpec()));
    }

    private void removeEventRegistration(final EventRegistration eventRegistration) {
        ZombieEventManager.getInstance().zombifyForRemove(eventRegistration);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.Query.2
            @Override // java.lang.Runnable
            public void run() {
                Query.this.repo.removeEventCallback(eventRegistration);
            }
        });
    }

    private void addEventRegistration(final EventRegistration eventRegistration) {
        ZombieEventManager.getInstance().recordEventRegistration(eventRegistration);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.Query.3
            @Override // java.lang.Runnable
            public void run() {
                Query.this.repo.addEventCallback(eventRegistration);
            }
        });
    }

    public void keepSynced(final boolean z) {
        if (!this.path.isEmpty() && this.path.getFront().equals(ChildKey.getInfoKey())) {
            throw new DatabaseException("Can't call keepSynced() on .info paths.");
        }
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.Query.4
            @Override // java.lang.Runnable
            public void run() {
                Query.this.repo.keepSynced(Query.this.getSpec(), z);
            }
        });
    }

    public Query startAfter(String str) {
        if (str != null && this.params.getIndex().equals(KeyIndex.getInstance())) {
            return startAt(PushIdGenerator.successor(str));
        }
        return startAt(str, ChildKey.getMaxName().asString());
    }

    public Query startAfter(double d) {
        return startAt(d, ChildKey.getMaxName().asString());
    }

    public Query startAfter(boolean z) {
        return startAt(z, ChildKey.getMaxName().asString());
    }

    public Query startAfter(String str, String str2) {
        if (str != null && this.params.getIndex().equals(KeyIndex.getInstance())) {
            str = PushIdGenerator.successor(str);
        }
        return startAfter(str != null ? new StringNode(str, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), str2);
    }

    public Query startAfter(double d, String str) {
        return startAfter(new DoubleNode(Double.valueOf(d), PriorityUtilities.NullPriority()), str);
    }

    public Query startAfter(boolean z, String str) {
        return startAfter(new BooleanNode(Boolean.valueOf(z), PriorityUtilities.NullPriority()), str);
    }

    private Query startAfter(Node node, String str) {
        return startAt(node, PushIdGenerator.successor(str));
    }

    public Query startAt(String str) {
        return startAt(str, (String) null);
    }

    public Query startAt(double d) {
        return startAt(d, (String) null);
    }

    public Query startAt(boolean z) {
        return startAt(z, (String) null);
    }

    public Query startAt(String str, String str2) {
        return startAt(str != null ? new StringNode(str, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), str2);
    }

    public Query startAt(double d, String str) {
        return startAt(new DoubleNode(Double.valueOf(d), PriorityUtilities.NullPriority()), str);
    }

    public Query startAt(boolean z, String str) {
        return startAt(new BooleanNode(Boolean.valueOf(z), PriorityUtilities.NullPriority()), str);
    }

    private Query startAt(Node node, String str) {
        Validation.validateNullableKey(str);
        if (!node.isLeafNode() && !node.isEmpty()) {
            throw new IllegalArgumentException("Can only use simple values for startAt() and startAfter()");
        }
        if (this.params.hasStart()) {
            throw new IllegalArgumentException("Can't call startAt(), startAfte(), or equalTo() multiple times");
        }
        ChildKey childKey = null;
        if (str != null) {
            if (str.equals(ChildKey.MIN_KEY_NAME)) {
                childKey = ChildKey.getMinName();
            } else if (str.equals(ChildKey.MAX_KEY_NAME)) {
                childKey = ChildKey.getMaxName();
            } else {
                childKey = ChildKey.fromString(str);
            }
        }
        QueryParams startAt = this.params.startAt(node, childKey);
        validateLimit(startAt);
        validateQueryEndpoints(startAt);
        Utilities.hardAssert(startAt.isValid());
        return new Query(this.repo, this.path, startAt, this.orderByCalled);
    }

    public Query endBefore(String str) {
        if (str != null && this.params.getIndex().equals(KeyIndex.getInstance())) {
            return endAt(PushIdGenerator.predecessor(str));
        }
        return endAt(str, ChildKey.getMinName().asString());
    }

    public Query endBefore(double d) {
        return endAt(d, ChildKey.getMinName().asString());
    }

    public Query endBefore(boolean z) {
        return endAt(z, ChildKey.getMinName().asString());
    }

    public Query endBefore(String str, String str2) {
        if (str != null && this.params.getIndex().equals(KeyIndex.getInstance())) {
            str = PushIdGenerator.predecessor(str);
        }
        return endBefore(str != null ? new StringNode(str, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), str2);
    }

    public Query endBefore(double d, String str) {
        return endBefore(new DoubleNode(Double.valueOf(d), PriorityUtilities.NullPriority()), str);
    }

    public Query endBefore(boolean z, String str) {
        return endBefore(new BooleanNode(Boolean.valueOf(z), PriorityUtilities.NullPriority()), str);
    }

    private Query endBefore(Node node, String str) {
        return endAt(node, PushIdGenerator.predecessor(str));
    }

    public Query endAt(String str) {
        return endAt(str, (String) null);
    }

    public Query endAt(double d) {
        return endAt(d, (String) null);
    }

    public Query endAt(boolean z) {
        return endAt(z, (String) null);
    }

    public Query endAt(String str, String str2) {
        return endAt(str != null ? new StringNode(str, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), str2);
    }

    public Query endAt(double d, String str) {
        return endAt(new DoubleNode(Double.valueOf(d), PriorityUtilities.NullPriority()), str);
    }

    public Query endAt(boolean z, String str) {
        return endAt(new BooleanNode(Boolean.valueOf(z), PriorityUtilities.NullPriority()), str);
    }

    private Query endAt(Node node, String str) {
        Validation.validateNullableKey(str);
        if (!node.isLeafNode() && !node.isEmpty()) {
            throw new IllegalArgumentException("Can only use simple values for endAt()");
        }
        ChildKey fromString = str != null ? ChildKey.fromString(str) : null;
        if (this.params.hasEnd()) {
            throw new IllegalArgumentException("Can't call endAt() or equalTo() multiple times");
        }
        QueryParams endAt = this.params.endAt(node, fromString);
        validateLimit(endAt);
        validateQueryEndpoints(endAt);
        Utilities.hardAssert(endAt.isValid());
        return new Query(this.repo, this.path, endAt, this.orderByCalled);
    }

    public Query equalTo(String str) {
        validateEqualToCall();
        return startAt(str).endAt(str);
    }

    public Query equalTo(double d) {
        validateEqualToCall();
        return startAt(d).endAt(d);
    }

    public Query equalTo(boolean z) {
        validateEqualToCall();
        return startAt(z).endAt(z);
    }

    public Query equalTo(String str, String str2) {
        validateEqualToCall();
        return startAt(str, str2).endAt(str, str2);
    }

    public Query equalTo(double d, String str) {
        validateEqualToCall();
        return startAt(d, str).endAt(d, str);
    }

    public Query equalTo(boolean z, String str) {
        validateEqualToCall();
        return startAt(z, str).endAt(z, str);
    }

    public Query limitToFirst(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        }
        if (this.params.hasLimit()) {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
        return new Query(this.repo, this.path, this.params.limitToFirst(i), this.orderByCalled);
    }

    public Query limitToLast(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        }
        if (this.params.hasLimit()) {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
        return new Query(this.repo, this.path, this.params.limitToLast(i), this.orderByCalled);
    }

    public Query orderByChild(String str) {
        java.util.Objects.requireNonNull(str, "Key can't be null");
        if (str.equals("$key") || str.equals(".key")) {
            throw new IllegalArgumentException("Can't use '" + str + "' as path, please use orderByKey() instead!");
        }
        if (str.equals("$priority") || str.equals(".priority")) {
            throw new IllegalArgumentException("Can't use '" + str + "' as path, please use orderByPriority() instead!");
        }
        if (str.equals("$value") || str.equals(".value")) {
            throw new IllegalArgumentException("Can't use '" + str + "' as path, please use orderByValue() instead!");
        }
        Validation.validatePathString(str);
        validateNoOrderByCall();
        Path path = new Path(str);
        if (path.size() == 0) {
            throw new IllegalArgumentException("Can't use empty path, use orderByValue() instead!");
        }
        return new Query(this.repo, this.path, this.params.orderBy(new PathIndex(path)), true);
    }

    public Query orderByPriority() {
        validateNoOrderByCall();
        QueryParams orderBy = this.params.orderBy(PriorityIndex.getInstance());
        validateQueryEndpoints(orderBy);
        return new Query(this.repo, this.path, orderBy, true);
    }

    public Query orderByKey() {
        validateNoOrderByCall();
        QueryParams orderBy = this.params.orderBy(KeyIndex.getInstance());
        validateQueryEndpoints(orderBy);
        return new Query(this.repo, this.path, orderBy, true);
    }

    public Query orderByValue() {
        validateNoOrderByCall();
        return new Query(this.repo, this.path, this.params.orderBy(ValueIndex.getInstance()), true);
    }

    public DatabaseReference getRef() {
        return new DatabaseReference(this.repo, getPath());
    }

    public Path getPath() {
        return this.path;
    }

    public Repo getRepo() {
        return this.repo;
    }

    public QuerySpec getSpec() {
        return new QuerySpec(this.path, this.params);
    }
}
