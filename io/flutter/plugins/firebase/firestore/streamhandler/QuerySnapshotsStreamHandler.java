package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import io.flutter.plugins.firebase.firestore.utils.PigeonParser;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class QuerySnapshotsStreamHandler implements EventChannel.StreamHandler {
    ListenerRegistration listenerRegistration;
    MetadataChanges metadataChanges;
    Query query;
    DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;

    public QuerySnapshotsStreamHandler(Query query, Boolean bool, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        this.query = query;
        this.metadataChanges = bool.booleanValue() ? MetadataChanges.INCLUDE : MetadataChanges.EXCLUDE;
        this.serverTimestampBehavior = serverTimestampBehavior;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.listenerRegistration = this.query.addSnapshotListener(this.metadataChanges, new EventListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.QuerySnapshotsStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj2, FirebaseFirestoreException firebaseFirestoreException) {
                QuerySnapshotsStreamHandler.this.m1072x353c19a8(eventSink, (QuerySnapshot) obj2, firebaseFirestoreException);
            }
        });
    }

    /* renamed from: lambda$onListen$0$io-flutter-plugins-firebase-firestore-streamhandler-QuerySnapshotsStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1072x353c19a8(EventChannel.EventSink eventSink, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventSink.error(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, firebaseFirestoreException.getMessage(), ExceptionConverter.createDetails(firebaseFirestoreException));
            eventSink.endOfStream();
            onCancel(null);
            return;
        }
        ArrayList arrayList = new ArrayList(3);
        ArrayList arrayList2 = new ArrayList(querySnapshot.getDocuments().size());
        ArrayList arrayList3 = new ArrayList(querySnapshot.getDocumentChanges().size());
        Iterator<DocumentSnapshot> it = querySnapshot.getDocuments().iterator();
        while (it.hasNext()) {
            arrayList2.add(PigeonParser.toPigeonDocumentSnapshot(it.next(), this.serverTimestampBehavior).toList());
        }
        Iterator<DocumentChange> it2 = querySnapshot.getDocumentChanges().iterator();
        while (it2.hasNext()) {
            arrayList3.add(PigeonParser.toPigeonDocumentChange(it2.next(), this.serverTimestampBehavior).toList());
        }
        arrayList.add(arrayList2);
        arrayList.add(arrayList3);
        arrayList.add(PigeonParser.toPigeonSnapshotMetadata(querySnapshot.getMetadata()).toList());
        eventSink.success(arrayList);
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
