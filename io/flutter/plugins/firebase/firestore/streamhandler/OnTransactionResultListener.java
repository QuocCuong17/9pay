package io.flutter.plugins.firebase.firestore.streamhandler;

import io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore;
import java.util.List;

/* loaded from: classes5.dex */
public interface OnTransactionResultListener {
    void receiveTransactionResponse(GeneratedAndroidFirebaseFirestore.PigeonTransactionResult pigeonTransactionResult, List<GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand> list);
}
