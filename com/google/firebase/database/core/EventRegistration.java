package com.google.firebase.database.core;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.database.core.view.DataEvent;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.core.view.QuerySpec;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public abstract class EventRegistration {
    private EventRegistrationZombieListener listener;
    private AtomicBoolean zombied = new AtomicBoolean(false);
    private boolean isUserInitiated = false;

    public abstract EventRegistration clone(QuerySpec querySpec);

    public abstract DataEvent createEvent(Change change, QuerySpec querySpec);

    public abstract void fireCancelEvent(DatabaseError databaseError);

    public abstract void fireEvent(DataEvent dataEvent);

    public abstract QuerySpec getQuerySpec();

    Repo getRepo() {
        return null;
    }

    public abstract boolean isSameListener(EventRegistration eventRegistration);

    public abstract boolean respondsTo(Event.EventType eventType);

    public void zombify() {
        EventRegistrationZombieListener eventRegistrationZombieListener;
        if (!this.zombied.compareAndSet(false, true) || (eventRegistrationZombieListener = this.listener) == null) {
            return;
        }
        eventRegistrationZombieListener.onZombied(this);
        this.listener = null;
    }

    public boolean isZombied() {
        return this.zombied.get();
    }

    public void setOnZombied(EventRegistrationZombieListener eventRegistrationZombieListener) {
        Utilities.hardAssert(!isZombied());
        Utilities.hardAssert(this.listener == null);
        this.listener = eventRegistrationZombieListener;
    }

    public boolean isUserInitiated() {
        return this.isUserInitiated;
    }

    public void setIsUserInitiated(boolean z) {
        this.isUserInitiated = z;
    }
}
