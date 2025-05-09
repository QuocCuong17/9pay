package io.flutter.plugins.firebase.database;

import com.google.firebase.database.DataSnapshot;
import io.flutter.plugin.common.EventChannel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class EventsProxy {
    protected final EventChannel.EventSink eventSink;
    private final String eventType;

    /* JADX INFO: Access modifiers changed from: protected */
    public EventsProxy(EventChannel.EventSink eventSink, String str) {
        this.eventSink = eventSink;
        this.eventType = str;
    }

    Map<String, Object> buildAdditionalParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.EVENT_TYPE, str);
        if (str2 != null) {
            hashMap.put(Constants.PREVIOUS_CHILD_NAME, str2);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendEvent(String str, DataSnapshot dataSnapshot, String str2) {
        if (this.eventType.equals(str)) {
            this.eventSink.success(new FlutterDataSnapshotPayload(dataSnapshot).withAdditionalParams(buildAdditionalParams(str, str2)).toMap());
        }
    }
}
