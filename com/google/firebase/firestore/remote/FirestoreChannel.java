package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.BuildConfig;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

/* loaded from: classes4.dex */
public class FirestoreChannel {
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private final GrpcCallProvider callProvider;
    private final GrpcMetadataProvider metadataProvider;
    private final String resourcePrefixValue;
    private static final Metadata.Key<String> X_GOOG_API_CLIENT_HEADER = Metadata.Key.of("x-goog-api-client", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> RESOURCE_PREFIX_HEADER = Metadata.Key.of("google-cloud-resource-prefix", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> X_GOOG_REQUEST_PARAMS_HEADER = Metadata.Key.of("x-goog-request-params", Metadata.ASCII_STRING_MARSHALLER);
    private static volatile String clientLanguage = "gl-java/";

    /* loaded from: classes4.dex */
    public static abstract class StreamingListener<T> {
        public void onClose(Status status) {
        }

        public void onMessage(T t) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirestoreChannel(AsyncQueue asyncQueue, Context context, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, DatabaseInfo databaseInfo, GrpcMetadataProvider grpcMetadataProvider) {
        this.asyncQueue = asyncQueue;
        this.metadataProvider = grpcMetadataProvider;
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
        this.callProvider = new GrpcCallProvider(asyncQueue, context, databaseInfo, new FirestoreCallCredentials(credentialsProvider, credentialsProvider2));
        DatabaseId databaseId = databaseInfo.getDatabaseId();
        this.resourcePrefixValue = String.format("projects/%s/databases/%s", databaseId.getProjectId(), databaseId.getDatabaseId());
    }

    public void shutdown() {
        this.callProvider.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <ReqT, RespT> ClientCall<ReqT, RespT> runBidiStreamingRpc(MethodDescriptor<ReqT, RespT> methodDescriptor, final IncomingStreamObserver<RespT> incomingStreamObserver) {
        final ClientCall[] clientCallArr = {null};
        final Task<ClientCall<ReqT, RespT>> createClientCall = this.callProvider.createClientCall(methodDescriptor);
        createClientCall.addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FirestoreChannel.this.m864xae2b8084(clientCallArr, incomingStreamObserver, task);
            }
        });
        return new ForwardingClientCall<ReqT, RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.2
            @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall
            protected ClientCall<ReqT, RespT> delegate() {
                Assert.hardAssert(clientCallArr[0] != null, "ClientCall used before onOpen() callback", new Object[0]);
                return clientCallArr[0];
            }

            @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
            public void halfClose() {
                if (clientCallArr[0] == null) {
                    createClientCall.addOnSuccessListener(FirestoreChannel.this.asyncQueue.getExecutor(), new OnSuccessListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$2$$ExternalSyntheticLambda0
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            ((ClientCall) obj).halfClose();
                        }
                    });
                } else {
                    super.halfClose();
                }
            }
        };
    }

    /* renamed from: lambda$runBidiStreamingRpc$0$com-google-firebase-firestore-remote-FirestoreChannel, reason: not valid java name */
    public /* synthetic */ void m864xae2b8084(final ClientCall[] clientCallArr, final IncomingStreamObserver incomingStreamObserver, Task task) {
        clientCallArr[0] = (ClientCall) task.getResult();
        clientCallArr[0].start(new ClientCall.Listener<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.1
            @Override // io.grpc.ClientCall.Listener
            public void onReady() {
            }

            @Override // io.grpc.ClientCall.Listener
            public void onHeaders(Metadata metadata) {
                try {
                    incomingStreamObserver.onHeaders(metadata);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            @Override // io.grpc.ClientCall.Listener
            public void onMessage(RespT respt) {
                try {
                    incomingStreamObserver.onNext(respt);
                    clientCallArr[0].request(1);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata metadata) {
                try {
                    incomingStreamObserver.onClose(status);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }
        }, requestHeaders());
        incomingStreamObserver.onOpen();
        clientCallArr[0].request(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <ReqT, RespT> void runStreamingResponseRpc(MethodDescriptor<ReqT, RespT> methodDescriptor, final ReqT reqt, final StreamingListener<RespT> streamingListener) {
        this.callProvider.createClientCall(methodDescriptor).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FirestoreChannel.this.m866x26428698(streamingListener, reqt, task);
            }
        });
    }

    /* renamed from: lambda$runStreamingResponseRpc$1$com-google-firebase-firestore-remote-FirestoreChannel, reason: not valid java name */
    public /* synthetic */ void m866x26428698(final StreamingListener streamingListener, Object obj, Task task) {
        final ClientCall clientCall = (ClientCall) task.getResult();
        clientCall.start(new ClientCall.Listener<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.3
            @Override // io.grpc.ClientCall.Listener
            public void onMessage(RespT respt) {
                streamingListener.onMessage(respt);
                clientCall.request(1);
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata metadata) {
                streamingListener.onClose(status);
            }
        }, requestHeaders());
        clientCall.request(1);
        clientCall.sendMessage(obj);
        clientCall.halfClose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <ReqT, RespT> Task<RespT> runRpc(MethodDescriptor<ReqT, RespT> methodDescriptor, final ReqT reqt) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.callProvider.createClientCall(methodDescriptor).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FirestoreChannel.this.m865x7c1edc40(taskCompletionSource, reqt, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$runRpc$2$com-google-firebase-firestore-remote-FirestoreChannel, reason: not valid java name */
    public /* synthetic */ void m865x7c1edc40(final TaskCompletionSource taskCompletionSource, Object obj, Task task) {
        ClientCall clientCall = (ClientCall) task.getResult();
        clientCall.start(new ClientCall.Listener<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.4
            @Override // io.grpc.ClientCall.Listener
            public void onMessage(RespT respt) {
                taskCompletionSource.setResult(respt);
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata metadata) {
                if (!status.isOk()) {
                    taskCompletionSource.setException(FirestoreChannel.this.exceptionFromStatus(status));
                } else {
                    if (taskCompletionSource.getTask().isComplete()) {
                        return;
                    }
                    taskCompletionSource.setException(new FirebaseFirestoreException("Received onClose with status OK, but no message.", FirebaseFirestoreException.Code.INTERNAL));
                }
            }
        }, requestHeaders());
        clientCall.request(2);
        clientCall.sendMessage(obj);
        clientCall.halfClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirebaseFirestoreException exceptionFromStatus(Status status) {
        if (Datastore.isMissingSslCiphers(status)) {
            return new FirebaseFirestoreException("The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.", FirebaseFirestoreException.Code.fromValue(status.getCode().value()), status.getCause());
        }
        return Util.exceptionFromStatus(status);
    }

    public void invalidateToken() {
        this.authProvider.invalidateToken();
        this.appCheckProvider.invalidateToken();
    }

    public static void setClientLanguage(String str) {
        clientLanguage = str;
    }

    private String getGoogApiClientValue() {
        return String.format("%s fire/%s grpc/", clientLanguage, BuildConfig.VERSION_NAME);
    }

    private Metadata requestHeaders() {
        Metadata metadata = new Metadata();
        metadata.put(X_GOOG_API_CLIENT_HEADER, getGoogApiClientValue());
        metadata.put(RESOURCE_PREFIX_HEADER, this.resourcePrefixValue);
        metadata.put(X_GOOG_REQUEST_PARAMS_HEADER, this.resourcePrefixValue);
        GrpcMetadataProvider grpcMetadataProvider = this.metadataProvider;
        if (grpcMetadataProvider != null) {
            grpcMetadataProvider.updateMetadata(metadata);
        }
        return metadata;
    }
}
