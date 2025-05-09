package io.flutter.plugins.firebase.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import io.flutter.plugin.common.EventChannel;

/* loaded from: classes5.dex */
public class ValueEventsProxy extends EventsProxy implements ValueEventListener {
    /* JADX INFO: Access modifiers changed from: protected */
    public ValueEventsProxy(EventChannel.EventSink eventSink) {
        super(eventSink, "value");
    }

    @Override // com.google.firebase.database.ValueEventListener
    public void onDataChange(DataSnapshot dataSnapshot) {
        sendEvent("value", dataSnapshot, null);
    }

    @Override // com.google.firebase.database.ValueEventListener
    public void onCancelled(DatabaseError databaseError) {
        FlutterFirebaseDatabaseException fromDatabaseError = FlutterFirebaseDatabaseException.fromDatabaseError(databaseError);
        this.eventSink.error(fromDatabaseError.getCode(), fromDatabaseError.getMessage(), fromDatabaseError.getAdditionalData());
    }
}
