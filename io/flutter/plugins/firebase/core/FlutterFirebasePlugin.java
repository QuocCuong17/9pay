package io.flutter.plugins.firebase.core;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public interface FlutterFirebasePlugin {
    public static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    Task<Void> didReinitializeFirebaseCore();

    Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp);
}
