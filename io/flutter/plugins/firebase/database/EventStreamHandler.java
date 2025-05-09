package io.flutter.plugins.firebase.database;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import io.flutter.plugin.common.EventChannel;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class EventStreamHandler implements EventChannel.StreamHandler {
    private ChildEventListener childEventListener;
    private final OnDispose onDispose;
    private final Query query;
    private ValueEventListener valueEventListener;

    public EventStreamHandler(Query query, OnDispose onDispose) {
        this.query = query;
        this.onDispose = onDispose;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        Object obj2 = ((Map) obj).get(Constants.EVENT_TYPE);
        Objects.requireNonNull(obj2);
        String str = (String) obj2;
        if ("value".equals(str)) {
            ValueEventsProxy valueEventsProxy = new ValueEventsProxy(eventSink);
            this.valueEventListener = valueEventsProxy;
            this.query.addValueEventListener(valueEventsProxy);
        } else {
            ChildEventsProxy childEventsProxy = new ChildEventsProxy(eventSink, str);
            this.childEventListener = childEventsProxy;
            this.query.addChildEventListener(childEventsProxy);
        }
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.onDispose.run();
        ValueEventListener valueEventListener = this.valueEventListener;
        if (valueEventListener != null) {
            this.query.removeEventListener(valueEventListener);
            this.valueEventListener = null;
        }
        ChildEventListener childEventListener = this.childEventListener;
        if (childEventListener != null) {
            this.query.removeEventListener(childEventListener);
            this.childEventListener = null;
        }
    }
}
