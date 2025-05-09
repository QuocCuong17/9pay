package com.google.firebase.database.core;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.core.persistence.PersistenceManager;
import com.google.firebase.database.core.utilities.Pair;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.DataEvent;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.core.view.View;
import com.google.firebase.database.core.view.ViewCache;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SyncPoint {
    private final PersistenceManager persistenceManager;
    private final Map<QueryParams, View> views = new HashMap();

    public SyncPoint(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public boolean isEmpty() {
        return this.views.isEmpty();
    }

    private List<DataEvent> applyOperationToView(View view, Operation operation, WriteTreeRef writeTreeRef, Node node) {
        View.OperationResult applyOperation = view.applyOperation(operation, writeTreeRef, node);
        if (!view.getQuery().loadsAllData()) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (Change change : applyOperation.changes) {
                Event.EventType eventType = change.getEventType();
                if (eventType == Event.EventType.CHILD_ADDED) {
                    hashSet2.add(change.getChildKey());
                } else if (eventType == Event.EventType.CHILD_REMOVED) {
                    hashSet.add(change.getChildKey());
                }
            }
            if (!hashSet2.isEmpty() || !hashSet.isEmpty()) {
                this.persistenceManager.updateTrackedQueryKeys(view.getQuery(), hashSet2, hashSet);
            }
        }
        return applyOperation.events;
    }

    public List<DataEvent> applyOperation(Operation operation, WriteTreeRef writeTreeRef, Node node) {
        QueryParams queryParams = operation.getSource().getQueryParams();
        if (queryParams != null) {
            View view = this.views.get(queryParams);
            Utilities.hardAssert(view != null);
            return applyOperationToView(view, operation, writeTreeRef, node);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<QueryParams, View>> it = this.views.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(applyOperationToView(it.next().getValue(), operation, writeTreeRef, node));
        }
        return arrayList;
    }

    public View getView(QuerySpec querySpec, WriteTreeRef writeTreeRef, CacheNode cacheNode) {
        boolean z;
        View view = this.views.get(querySpec.getParams());
        if (view != null) {
            return view;
        }
        Node calcCompleteEventCache = writeTreeRef.calcCompleteEventCache(cacheNode.isFullyInitialized() ? cacheNode.getNode() : null);
        if (calcCompleteEventCache != null) {
            z = true;
        } else {
            calcCompleteEventCache = writeTreeRef.calcCompleteEventChildren(cacheNode.getNode() != null ? cacheNode.getNode() : EmptyNode.Empty());
            z = false;
        }
        return new View(querySpec, new ViewCache(new CacheNode(IndexedNode.from(calcCompleteEventCache, querySpec.getIndex()), z, false), cacheNode));
    }

    public List<DataEvent> addEventRegistration(EventRegistration eventRegistration, WriteTreeRef writeTreeRef, CacheNode cacheNode) {
        QuerySpec querySpec = eventRegistration.getQuerySpec();
        View view = getView(querySpec, writeTreeRef, cacheNode);
        if (!querySpec.loadsAllData()) {
            HashSet hashSet = new HashSet();
            Iterator<NamedNode> it = view.getEventCache().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getName());
            }
            this.persistenceManager.setTrackedQueryKeys(querySpec, hashSet);
        }
        if (!this.views.containsKey(querySpec.getParams())) {
            this.views.put(querySpec.getParams(), view);
        }
        this.views.put(querySpec.getParams(), view);
        view.addEventRegistration(eventRegistration);
        return view.getInitialEvents(eventRegistration);
    }

    public Pair<List<QuerySpec>, List<Event>> removeEventRegistration(QuerySpec querySpec, EventRegistration eventRegistration, DatabaseError databaseError) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean hasCompleteView = hasCompleteView();
        if (querySpec.isDefault()) {
            Iterator<Map.Entry<QueryParams, View>> it = this.views.entrySet().iterator();
            while (it.hasNext()) {
                View value = it.next().getValue();
                arrayList2.addAll(value.removeEventRegistration(eventRegistration, databaseError));
                if (value.isEmpty()) {
                    it.remove();
                    if (!value.getQuery().loadsAllData()) {
                        arrayList.add(value.getQuery());
                    }
                }
            }
        } else {
            View view = this.views.get(querySpec.getParams());
            if (view != null) {
                arrayList2.addAll(view.removeEventRegistration(eventRegistration, databaseError));
                if (view.isEmpty()) {
                    this.views.remove(querySpec.getParams());
                    if (!view.getQuery().loadsAllData()) {
                        arrayList.add(view.getQuery());
                    }
                }
            }
        }
        if (hasCompleteView && !hasCompleteView()) {
            arrayList.add(QuerySpec.defaultQueryAtPath(querySpec.getPath()));
        }
        return new Pair<>(arrayList, arrayList2);
    }

    public List<View> getQueryViews() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<QueryParams, View>> it = this.views.entrySet().iterator();
        while (it.hasNext()) {
            View value = it.next().getValue();
            if (!value.getQuery().loadsAllData()) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    public Node getCompleteServerCache(Path path) {
        Iterator<View> it = this.views.values().iterator();
        while (it.hasNext()) {
            Node completeServerCache = it.next().getCompleteServerCache(path);
            if (completeServerCache != null) {
                return completeServerCache;
            }
        }
        return null;
    }

    public View viewForQuery(QuerySpec querySpec) {
        if (querySpec.loadsAllData()) {
            return getCompleteView();
        }
        return this.views.get(querySpec.getParams());
    }

    public boolean viewExistsForQuery(QuerySpec querySpec) {
        return viewForQuery(querySpec) != null;
    }

    public boolean hasCompleteView() {
        return getCompleteView() != null;
    }

    public View getCompleteView() {
        Iterator<Map.Entry<QueryParams, View>> it = this.views.entrySet().iterator();
        while (it.hasNext()) {
            View value = it.next().getValue();
            if (value.getQuery().loadsAllData()) {
                return value;
            }
        }
        return null;
    }

    Map<QueryParams, View> getViews() {
        return this.views;
    }
}
