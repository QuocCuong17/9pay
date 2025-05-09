package io.flutter.plugins.firebase.firestore;

/* loaded from: classes5.dex */
public class FlutterFirebaseFirestoreTransactionResult {
    public final Exception exception;

    private FlutterFirebaseFirestoreTransactionResult(Exception exc) {
        this.exception = exc;
    }

    private FlutterFirebaseFirestoreTransactionResult() {
        this.exception = null;
    }

    public static FlutterFirebaseFirestoreTransactionResult failed(Exception exc) {
        return new FlutterFirebaseFirestoreTransactionResult(exc);
    }

    public static FlutterFirebaseFirestoreTransactionResult complete() {
        return new FlutterFirebaseFirestoreTransactionResult();
    }
}
