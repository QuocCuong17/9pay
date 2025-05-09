package io.flutter.plugins.firebase.database;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import io.flutter.plugin.common.EventChannel;

/* loaded from: classes5.dex */
public class ChildEventsProxy extends EventsProxy implements ChildEventListener {
    /* JADX INFO: Access modifiers changed from: protected */
    public ChildEventsProxy(EventChannel.EventSink eventSink, String str) {
        super(eventSink, str);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildAdded(DataSnapshot dataSnapshot, String str) {
        sendEvent(Constants.EVENT_TYPE_CHILD_ADDED, dataSnapshot, str);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildChanged(DataSnapshot dataSnapshot, String str) {
        sendEvent(Constants.EVENT_TYPE_CHILD_CHANGED, dataSnapshot, str);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        sendEvent(Constants.EVENT_TYPE_CHILD_REMOVED, dataSnapshot, null);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildMoved(DataSnapshot dataSnapshot, String str) {
        sendEvent(Constants.EVENT_TYPE_CHILD_MOVED, dataSnapshot, str);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onCancelled(DatabaseError databaseError) {
        FlutterFirebaseDatabaseException fromDatabaseError = FlutterFirebaseDatabaseException.fromDatabaseError(databaseError);
        this.eventSink.error(fromDatabaseError.getCode(), fromDatabaseError.getMessage(), fromDatabaseError.getAdditionalData());
    }
}
