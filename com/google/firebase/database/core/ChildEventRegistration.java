package com.google.firebase.database.core;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.InternalHelpers;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.DataEvent;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.core.view.QuerySpec;

/* loaded from: classes4.dex */
public class ChildEventRegistration extends EventRegistration {
    private final ChildEventListener eventListener;
    private final Repo repo;
    private final QuerySpec spec;

    public String toString() {
        return "ChildEventRegistration";
    }

    public ChildEventRegistration(Repo repo, ChildEventListener childEventListener, QuerySpec querySpec) {
        this.repo = repo;
        this.eventListener = childEventListener;
        this.spec = querySpec;
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public boolean respondsTo(Event.EventType eventType) {
        return eventType != Event.EventType.VALUE;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ChildEventRegistration) {
            ChildEventRegistration childEventRegistration = (ChildEventRegistration) obj;
            if (childEventRegistration.eventListener.equals(this.eventListener) && childEventRegistration.repo.equals(this.repo) && childEventRegistration.spec.equals(this.spec)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((this.eventListener.hashCode() * 31) + this.repo.hashCode()) * 31) + this.spec.hashCode();
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public DataEvent createEvent(Change change, QuerySpec querySpec) {
        return new DataEvent(change.getEventType(), this, InternalHelpers.createDataSnapshot(InternalHelpers.createReference(this.repo, querySpec.getPath().child(change.getChildKey())), change.getIndexedNode()), change.getPrevName() != null ? change.getPrevName().asString() : null);
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public void fireEvent(DataEvent dataEvent) {
        if (isZombied()) {
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$core$view$Event$EventType[dataEvent.getEventType().ordinal()];
        if (i == 1) {
            this.eventListener.onChildAdded(dataEvent.getSnapshot(), dataEvent.getPreviousName());
            return;
        }
        if (i == 2) {
            this.eventListener.onChildChanged(dataEvent.getSnapshot(), dataEvent.getPreviousName());
        } else if (i == 3) {
            this.eventListener.onChildMoved(dataEvent.getSnapshot(), dataEvent.getPreviousName());
        } else {
            if (i != 4) {
                return;
            }
            this.eventListener.onChildRemoved(dataEvent.getSnapshot());
        }
    }

    /* renamed from: com.google.firebase.database.core.ChildEventRegistration$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$core$view$Event$EventType;

        static {
            int[] iArr = new int[Event.EventType.values().length];
            $SwitchMap$com$google$firebase$database$core$view$Event$EventType = iArr;
            try {
                iArr[Event.EventType.CHILD_ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$view$Event$EventType[Event.EventType.CHILD_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$view$Event$EventType[Event.EventType.CHILD_MOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$view$Event$EventType[Event.EventType.CHILD_REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public void fireCancelEvent(DatabaseError databaseError) {
        this.eventListener.onCancelled(databaseError);
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public EventRegistration clone(QuerySpec querySpec) {
        return new ChildEventRegistration(this.repo, this.eventListener, querySpec);
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public boolean isSameListener(EventRegistration eventRegistration) {
        return (eventRegistration instanceof ChildEventRegistration) && ((ChildEventRegistration) eventRegistration).eventListener.equals(this.eventListener);
    }

    @Override // com.google.firebase.database.core.EventRegistration
    public QuerySpec getQuerySpec() {
        return this.spec;
    }

    @Override // com.google.firebase.database.core.EventRegistration
    Repo getRepo() {
        return this.repo;
    }
}
