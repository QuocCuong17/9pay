package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import io.flutter.plugin.common.EventChannel;

/* loaded from: classes5.dex */
public class SnapshotsInSyncStreamHandler implements EventChannel.StreamHandler {
    FirebaseFirestore firestore;
    ListenerRegistration listenerRegistration;

    public SnapshotsInSyncStreamHandler(FirebaseFirestore firebaseFirestore) {
        this.firestore = firebaseFirestore;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.listenerRegistration = this.firestore.addSnapshotsInSyncListener(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.SnapshotsInSyncStreamHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EventChannel.EventSink.this.success(null);
            }
        });
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        ListenerRegistration listenerRegistration = this.listenerRegistration;
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            this.listenerRegistration = null;
        }
    }
}
