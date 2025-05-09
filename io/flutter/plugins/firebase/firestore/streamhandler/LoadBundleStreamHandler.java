package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.OnProgressListener;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import java.util.Objects;

/* loaded from: classes5.dex */
public class LoadBundleStreamHandler implements EventChannel.StreamHandler {
    private final byte[] bundle;
    private EventChannel.EventSink eventSink;
    private final FirebaseFirestore firestore;

    public LoadBundleStreamHandler(FirebaseFirestore firebaseFirestore, byte[] bArr) {
        this.firestore = firebaseFirestore;
        this.bundle = bArr;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
        LoadBundleTask loadBundle = this.firestore.loadBundle(this.bundle);
        Objects.requireNonNull(eventSink);
        loadBundle.addOnProgressListener(new OnProgressListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.LoadBundleStreamHandler$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.OnProgressListener
            public final void onProgress(Object obj2) {
                EventChannel.EventSink.this.success((LoadBundleTaskProgress) obj2);
            }
        });
        loadBundle.addOnFailureListener(new OnFailureListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.LoadBundleStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LoadBundleStreamHandler.this.m1071x802db407(eventSink, exc);
            }
        });
    }

    /* renamed from: lambda$onListen$0$io-flutter-plugins-firebase-firestore-streamhandler-LoadBundleStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1071x802db407(EventChannel.EventSink eventSink, Exception exc) {
        eventSink.error(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, exc.getMessage(), ExceptionConverter.createDetails(exc));
        onCancel(null);
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.eventSink.endOfStream();
    }
}
