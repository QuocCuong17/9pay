package io.flutter.plugins.firebase.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class FlutterFirebasePluginRegistry {
    private static final Map<String, FlutterFirebasePlugin> registeredPlugins = new WeakHashMap();

    public static void registerPlugin(String str, FlutterFirebasePlugin flutterFirebasePlugin) {
        registeredPlugins.put(str, flutterFirebasePlugin);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Task<Map<String, Object>> getPluginConstantsForFirebaseApp(final FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebasePluginRegistry.lambda$getPluginConstantsForFirebaseApp$0(FirebaseApp.this, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPluginConstantsForFirebaseApp$0(FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        try {
            Map<String, FlutterFirebasePlugin> map = registeredPlugins;
            HashMap hashMap = new HashMap(map.size());
            for (Map.Entry<String, FlutterFirebasePlugin> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), Tasks.await(entry.getValue().getPluginConstantsForFirebaseApp(firebaseApp)));
            }
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebasePluginRegistry.lambda$didReinitializeFirebaseCore$1(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$didReinitializeFirebaseCore$1(TaskCompletionSource taskCompletionSource) {
        try {
            Iterator<Map.Entry<String, FlutterFirebasePlugin>> it = registeredPlugins.entrySet().iterator();
            while (it.hasNext()) {
                Tasks.await(it.next().getValue().didReinitializeFirebaseCore());
            }
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }
}
