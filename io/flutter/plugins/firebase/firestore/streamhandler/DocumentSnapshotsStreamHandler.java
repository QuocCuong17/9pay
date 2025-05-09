package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import io.flutter.plugins.firebase.firestore.utils.PigeonParser;

/* loaded from: classes5.dex */
public class DocumentSnapshotsStreamHandler implements EventChannel.StreamHandler {
    DocumentReference documentReference;
    FirebaseFirestore firestore;
    ListenerRegistration listenerRegistration;
    MetadataChanges metadataChanges;
    DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;

    public DocumentSnapshotsStreamHandler(FirebaseFirestore firebaseFirestore, DocumentReference documentReference, Boolean bool, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        this.firestore = firebaseFirestore;
        this.documentReference = documentReference;
        this.metadataChanges = bool.booleanValue() ? MetadataChanges.INCLUDE : MetadataChanges.EXCLUDE;
        this.serverTimestampBehavior = serverTimestampBehavior;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.listenerRegistration = this.documentReference.addSnapshotListener(this.metadataChanges, new EventListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.DocumentSnapshotsStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj2, FirebaseFirestoreException firebaseFirestoreException) {
                DocumentSnapshotsStreamHandler.this.m1070xe9f41391(eventSink, (DocumentSnapshot) obj2, firebaseFirestoreException);
            }
        });
    }

    /* renamed from: lambda$onListen$0$io-flutter-plugins-firebase-firestore-streamhandler-DocumentSnapshotsStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1070xe9f41391(EventChannel.EventSink eventSink, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventSink.error(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, firebaseFirestoreException.getMessage(), ExceptionConverter.createDetails(firebaseFirestoreException));
            eventSink.endOfStream();
            onCancel(null);
            return;
        }
        eventSink.success(PigeonParser.toPigeonDocumentSnapshot(documentSnapshot, this.serverTimestampBehavior).toList());
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
