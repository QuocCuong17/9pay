package io.flutter.plugins.firebase.firestore.streamhandler;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.TransactionOptions;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestoreTransactionResult;
import io.flutter.plugins.firebase.firestore.GeneratedAndroidFirebaseFirestore;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import io.flutter.plugins.firebase.firestore.utils.PigeonParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class TransactionStreamHandler implements OnTransactionResultListener, EventChannel.StreamHandler {
    private List<GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand> commands;
    final FirebaseFirestore firestore;
    final Long maxAttempts;
    final OnTransactionStartedListener onTransactionStartedListener;
    private GeneratedAndroidFirebaseFirestore.PigeonTransactionResult resultType;
    final Long timeout;
    final String transactionId;
    final Semaphore semaphore = new Semaphore(0);
    final Handler mainLooper = new Handler(Looper.getMainLooper());

    /* loaded from: classes5.dex */
    public interface OnTransactionStartedListener {
        void onStarted(Transaction transaction);
    }

    public TransactionStreamHandler(OnTransactionStartedListener onTransactionStartedListener, FirebaseFirestore firebaseFirestore, String str, Long l, Long l2) {
        this.onTransactionStartedListener = onTransactionStartedListener;
        this.firestore = firebaseFirestore;
        this.transactionId = str;
        this.timeout = l;
        this.maxAttempts = l2;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.firestore.runTransaction(new TransactionOptions.Builder().setMaxAttempts(this.maxAttempts.intValue()).build(), new Transaction.Function() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.Transaction.Function
            public final Object apply(Transaction transaction) {
                return TransactionStreamHandler.this.m1073xe9e08326(eventSink, transaction);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TransactionStreamHandler.this.m1074x1be32664(eventSink, task);
            }
        });
    }

    /* renamed from: lambda$onListen$1$io-flutter-plugins-firebase-firestore-streamhandler-TransactionStreamHandler, reason: not valid java name */
    public /* synthetic */ FlutterFirebaseFirestoreTransactionResult m1073xe9e08326(final EventChannel.EventSink eventSink, Transaction transaction) throws FirebaseFirestoreException {
        this.onTransactionStartedListener.onStarted(transaction);
        final HashMap hashMap = new HashMap();
        hashMap.put("appName", this.firestore.getApp().getName());
        this.mainLooper.post(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                EventChannel.EventSink.this.success(hashMap);
            }
        });
        try {
            if (!this.semaphore.tryAcquire(this.timeout.longValue(), TimeUnit.MILLISECONDS)) {
                return FlutterFirebaseFirestoreTransactionResult.failed(new FirebaseFirestoreException("timed out", FirebaseFirestoreException.Code.DEADLINE_EXCEEDED));
            }
            if (this.commands.isEmpty()) {
                return FlutterFirebaseFirestoreTransactionResult.complete();
            }
            if (this.resultType == GeneratedAndroidFirebaseFirestore.PigeonTransactionResult.FAILURE) {
                return FlutterFirebaseFirestoreTransactionResult.complete();
            }
            for (GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand pigeonTransactionCommand : this.commands) {
                DocumentReference document = this.firestore.document(pigeonTransactionCommand.getPath());
                int i = AnonymousClass1.$SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType[pigeonTransactionCommand.getType().ordinal()];
                if (i == 1) {
                    transaction.delete(document);
                } else if (i == 2) {
                    Map<String, Object> data = pigeonTransactionCommand.getData();
                    Objects.requireNonNull(data);
                    transaction.update(document, data);
                } else if (i == 3) {
                    GeneratedAndroidFirebaseFirestore.PigeonDocumentOption option = pigeonTransactionCommand.getOption();
                    Objects.requireNonNull(option);
                    SetOptions setOptions = null;
                    if (option.getMerge() != null && option.getMerge().booleanValue()) {
                        setOptions = SetOptions.merge();
                    } else if (option.getMergeFields() != null) {
                        List<List<String>> mergeFields = option.getMergeFields();
                        Objects.requireNonNull(mergeFields);
                        setOptions = SetOptions.mergeFieldPaths(PigeonParser.parseFieldPath(mergeFields));
                    }
                    Map<String, Object> data2 = pigeonTransactionCommand.getData();
                    Objects.requireNonNull(data2);
                    if (setOptions == null) {
                        transaction.set(document, data2);
                    } else {
                        transaction.set(document, data2, setOptions);
                    }
                }
            }
            return FlutterFirebaseFirestoreTransactionResult.complete();
        } catch (InterruptedException unused) {
            return FlutterFirebaseFirestoreTransactionResult.failed(new FirebaseFirestoreException("interrupted", FirebaseFirestoreException.Code.DEADLINE_EXCEEDED));
        }
    }

    /* renamed from: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType;

        static {
            int[] iArr = new int[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.values().length];
            $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType = iArr;
            try {
                iArr[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.DELETE_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$flutter$plugins$firebase$firestore$GeneratedAndroidFirebaseFirestore$PigeonTransactionType[GeneratedAndroidFirebaseFirestore.PigeonTransactionType.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: lambda$onListen$3$io-flutter-plugins-firebase-firestore-streamhandler-TransactionStreamHandler, reason: not valid java name */
    public /* synthetic */ void m1074x1be32664(final EventChannel.EventSink eventSink, Task task) {
        final HashMap hashMap = new HashMap();
        if (task.getException() != null || ((FlutterFirebaseFirestoreTransactionResult) task.getResult()).exception != null) {
            Exception exception = task.getException() != null ? task.getException() : ((FlutterFirebaseFirestoreTransactionResult) task.getResult()).exception;
            hashMap.put("appName", this.firestore.getApp().getName());
            hashMap.put("error", ExceptionConverter.createDetails(exception));
        } else if (task.getResult() != null) {
            hashMap.put("complete", true);
        }
        this.mainLooper.post(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TransactionStreamHandler.lambda$onListen$2(EventChannel.EventSink.this, hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onListen$2(EventChannel.EventSink eventSink, HashMap hashMap) {
        eventSink.success(hashMap);
        eventSink.endOfStream();
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.semaphore.release();
    }

    @Override // io.flutter.plugins.firebase.firestore.streamhandler.OnTransactionResultListener
    public void receiveTransactionResponse(GeneratedAndroidFirebaseFirestore.PigeonTransactionResult pigeonTransactionResult, List<GeneratedAndroidFirebaseFirestore.PigeonTransactionCommand> list) {
        this.resultType = pigeonTransactionResult;
        this.commands = list;
        this.semaphore.release();
    }
}
