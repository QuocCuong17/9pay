package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.core.SyncEngine;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class EventManager implements SyncEngine.SyncEngineCallback {
    private final SyncEngine syncEngine;
    private final Set<EventListener<Void>> snapshotsInSyncListeners = new HashSet();
    private OnlineState onlineState = OnlineState.UNKNOWN;
    private final Map<Query, QueryListenersInfo> queries = new HashMap();

    /* loaded from: classes4.dex */
    public static class ListenOptions {
        public boolean includeDocumentMetadataChanges;
        public boolean includeQueryMetadataChanges;
        public boolean waitForSyncWhenOnline;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class QueryListenersInfo {
        private final List<QueryListener> listeners = new ArrayList();
        private int targetId;
        private ViewSnapshot viewSnapshot;

        QueryListenersInfo() {
        }
    }

    public EventManager(SyncEngine syncEngine) {
        this.syncEngine = syncEngine;
        syncEngine.setCallback(this);
    }

    public int addQueryListener(QueryListener queryListener) {
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        boolean z = queryListenersInfo == null;
        if (z) {
            queryListenersInfo = new QueryListenersInfo();
            this.queries.put(query, queryListenersInfo);
        }
        queryListenersInfo.listeners.add(queryListener);
        Assert.hardAssert(true ^ queryListener.onOnlineStateChanged(this.onlineState), "onOnlineStateChanged() shouldn't raise an event for brand-new listeners.", new Object[0]);
        if (queryListenersInfo.viewSnapshot != null && queryListener.onViewSnapshot(queryListenersInfo.viewSnapshot)) {
            raiseSnapshotsInSyncEvent();
        }
        if (z) {
            queryListenersInfo.targetId = this.syncEngine.listen(query);
        }
        return queryListenersInfo.targetId;
    }

    public void removeQueryListener(QueryListener queryListener) {
        boolean z;
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            queryListenersInfo.listeners.remove(queryListener);
            z = queryListenersInfo.listeners.isEmpty();
        } else {
            z = false;
        }
        if (z) {
            this.queries.remove(query);
            this.syncEngine.stopListening(query);
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.add(eventListener);
        eventListener.onEvent(null, null);
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.remove(eventListener);
    }

    private void raiseSnapshotsInSyncEvent() {
        Iterator<EventListener<Void>> it = this.snapshotsInSyncListeners.iterator();
        while (it.hasNext()) {
            it.next().onEvent(null, null);
        }
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void onViewSnapshots(List<ViewSnapshot> list) {
        boolean z = false;
        for (ViewSnapshot viewSnapshot : list) {
            QueryListenersInfo queryListenersInfo = this.queries.get(viewSnapshot.getQuery());
            if (queryListenersInfo != null) {
                Iterator it = queryListenersInfo.listeners.iterator();
                while (it.hasNext()) {
                    if (((QueryListener) it.next()).onViewSnapshot(viewSnapshot)) {
                        z = true;
                    }
                }
                queryListenersInfo.viewSnapshot = viewSnapshot;
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void onError(Query query, Status status) {
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            Iterator it = queryListenersInfo.listeners.iterator();
            while (it.hasNext()) {
                ((QueryListener) it.next()).onError(Util.exceptionFromStatus(status));
            }
        }
        this.queries.remove(query);
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void handleOnlineStateChange(OnlineState onlineState) {
        this.onlineState = onlineState;
        Iterator<QueryListenersInfo> it = this.queries.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Iterator it2 = it.next().listeners.iterator();
            while (it2.hasNext()) {
                if (((QueryListener) it2.next()).onOnlineStateChanged(onlineState)) {
                    z = true;
                }
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }
}
