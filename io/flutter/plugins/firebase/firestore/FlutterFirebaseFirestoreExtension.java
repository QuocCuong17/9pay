package io.flutter.plugins.firebase.firestore;

import com.google.firebase.firestore.FirebaseFirestore;

/* loaded from: classes5.dex */
public class FlutterFirebaseFirestoreExtension {
    private final String databaseURL;
    private final FirebaseFirestore instance;

    public FlutterFirebaseFirestoreExtension(FirebaseFirestore firebaseFirestore, String str) {
        this.instance = firebaseFirestore;
        this.databaseURL = str;
    }

    public FirebaseFirestore getInstance() {
        return this.instance;
    }

    public String getDatabaseURL() {
        return this.databaseURL;
    }
}
