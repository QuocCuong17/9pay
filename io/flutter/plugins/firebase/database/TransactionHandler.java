package io.flutter.plugins.firebase.database;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class TransactionHandler implements Transaction.Handler {
    private final MethodChannel channel;
    private final TaskCompletionSource<Map<String, Object>> transactionCompletionSource = new TaskCompletionSource<>();
    private final int transactionKey;

    public TransactionHandler(MethodChannel methodChannel, int i) {
        this.channel = methodChannel;
        this.transactionKey = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Map<String, Object>> getTask() {
        return this.transactionCompletionSource.getTask();
    }

    @Override // com.google.firebase.database.Transaction.Handler
    public Transaction.Result doTransaction(MutableData mutableData) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put("key", mutableData.getKey());
        hashMap.put("value", mutableData.getValue());
        hashMap2.put("snapshot", hashMap);
        hashMap2.put(Constants.TRANSACTION_KEY, Integer.valueOf(this.transactionKey));
        try {
            Object execute = new TransactionExecutor(this.channel).execute(hashMap2);
            Objects.requireNonNull(execute);
            Map map = (Map) execute;
            Object obj = map.get("aborted");
            Objects.requireNonNull(obj);
            boolean booleanValue = ((Boolean) obj).booleanValue();
            Object obj2 = map.get("exception");
            Objects.requireNonNull(obj2);
            boolean booleanValue2 = ((Boolean) obj2).booleanValue();
            if (!booleanValue && !booleanValue2) {
                mutableData.setValue(map.get("value"));
                return Transaction.success(mutableData);
            }
            return Transaction.abort();
        } catch (Exception e) {
            Log.e("firebase_database", "An unexpected exception occurred for a transaction.", e);
            return Transaction.abort();
        }
    }

    @Override // com.google.firebase.database.Transaction.Handler
    public void onComplete(DatabaseError databaseError, boolean z, DataSnapshot dataSnapshot) {
        if (databaseError != null) {
            this.transactionCompletionSource.setException(FlutterFirebaseDatabaseException.fromDatabaseError(databaseError));
        } else if (dataSnapshot != null) {
            FlutterDataSnapshotPayload flutterDataSnapshotPayload = new FlutterDataSnapshotPayload(dataSnapshot);
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.COMMITTED, Boolean.valueOf(z));
            this.transactionCompletionSource.setResult(flutterDataSnapshotPayload.withAdditionalParams(hashMap).toMap());
        }
    }
}
