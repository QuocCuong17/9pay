package io.flutter.plugins.firebase.database;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* loaded from: classes5.dex */
public class TransactionExecutor {
    private final MethodChannel channel;
    private final TaskCompletionSource<Object> completion = new TaskCompletionSource<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public TransactionExecutor(MethodChannel methodChannel) {
        this.channel = methodChannel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object execute(final Map<String, Object> map) throws ExecutionException, InterruptedException {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.flutter.plugins.firebase.database.TransactionExecutor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TransactionExecutor.this.m1062xe40cc520(map);
            }
        });
        return Tasks.await(this.completion.getTask());
    }

    /* renamed from: lambda$execute$0$io-flutter-plugins-firebase-database-TransactionExecutor, reason: not valid java name */
    public /* synthetic */ void m1062xe40cc520(Map map) {
        this.channel.invokeMethod(Constants.METHOD_CALL_TRANSACTION_HANDLER, map, new MethodChannel.Result() { // from class: io.flutter.plugins.firebase.database.TransactionExecutor.1
            @Override // io.flutter.plugin.common.MethodChannel.Result
            public void notImplemented() {
            }

            @Override // io.flutter.plugin.common.MethodChannel.Result
            public void success(Object obj) {
                TransactionExecutor.this.completion.setResult(obj);
            }

            @Override // io.flutter.plugin.common.MethodChannel.Result
            public void error(String str, String str2, Object obj) {
                Map hashMap = new HashMap();
                if (str2 == null) {
                    str2 = FlutterFirebaseDatabaseException.UNKNOWN_ERROR_MESSAGE;
                }
                if (obj instanceof Map) {
                    hashMap = (Map) obj;
                }
                TransactionExecutor.this.completion.setException(new FlutterFirebaseDatabaseException(str, str2, hashMap));
            }
        });
    }
}
