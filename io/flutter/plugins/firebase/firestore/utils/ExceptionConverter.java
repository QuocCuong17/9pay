package io.flutter.plugins.firebase.firestore.utils;

import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestoreException;
import io.flutter.plugins.firebase.database.FlutterFirebaseDatabaseException;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestoreException;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class ExceptionConverter {
    public static Map<String, String> createDetails(Exception exc) {
        Throwable cause;
        HashMap hashMap = new HashMap();
        if (exc == null) {
            return hashMap;
        }
        FlutterFirebaseFirestoreException flutterFirebaseFirestoreException = null;
        if (exc instanceof FirebaseFirestoreException) {
            flutterFirebaseFirestoreException = new FlutterFirebaseFirestoreException((FirebaseFirestoreException) exc, exc.getCause());
        } else if (exc.getCause() != null && (exc.getCause() instanceof FirebaseFirestoreException)) {
            FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc.getCause();
            if (exc.getCause().getCause() != null) {
                cause = exc.getCause().getCause();
            } else {
                cause = exc.getCause();
            }
            flutterFirebaseFirestoreException = new FlutterFirebaseFirestoreException(firebaseFirestoreException, cause);
        }
        if (flutterFirebaseFirestoreException != null) {
            hashMap.put("code", flutterFirebaseFirestoreException.getCode());
            hashMap.put("message", flutterFirebaseFirestoreException.getMessage());
        }
        if (hashMap.containsKey("code")) {
            String str = (String) hashMap.get("code");
            Objects.requireNonNull(str);
            if (str.equals("unknown")) {
                Log.e("FLTFirebaseFirestore", FlutterFirebaseDatabaseException.UNKNOWN_ERROR_MESSAGE, exc);
            }
        }
        return hashMap;
    }

    public static void sendErrorToFlutter(GeneratedAndroidFirebaseFirestore.Result result, Exception exc) {
        result.error(new GeneratedAndroidFirebaseFirestore.FlutterError(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, exc != null ? exc.getMessage() : null, createDetails(exc)));
    }
}
