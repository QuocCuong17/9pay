package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.AbstractStream;
import com.google.firebase.firestore.remote.Stream;
import com.google.firebase.firestore.remote.Stream.StreamCallback;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class AbstractStream<ReqT, RespT, CallbackT extends Stream.StreamCallback> implements Stream<CallbackT> {
    private static final double BACKOFF_FACTOR = 1.5d;
    final ExponentialBackoff backoff;
    private ClientCall<ReqT, RespT> call;
    private final FirestoreChannel firestoreChannel;
    private AsyncQueue.DelayedTask healthCheck;
    private final AsyncQueue.TimerId healthTimerId;
    private AsyncQueue.DelayedTask idleTimer;
    private final AsyncQueue.TimerId idleTimerId;
    final CallbackT listener;
    private final MethodDescriptor<ReqT, RespT> methodDescriptor;
    private final AsyncQueue workerQueue;
    private static final long BACKOFF_INITIAL_DELAY_MS = TimeUnit.SECONDS.toMillis(1);
    private static final long BACKOFF_MAX_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long IDLE_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long HEALTHY_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    private static final long BACKOFF_CLIENT_NETWORK_FAILURE_MAX_DELAY_MS = TimeUnit.SECONDS.toMillis(10);
    private Stream.State state = Stream.State.Initial;
    private long closeCount = 0;
    private final AbstractStream<ReqT, RespT, CallbackT>.IdleTimeoutRunnable idleTimeoutRunnable = new IdleTimeoutRunnable();

    public abstract void onNext(RespT respt);

    protected void tearDown() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class CloseGuardedRunner {
        private final long initialCloseCount;

        CloseGuardedRunner(long j) {
            this.initialCloseCount = j;
        }

        void run(Runnable runnable) {
            AbstractStream.this.workerQueue.verifyIsCurrentThread();
            if (AbstractStream.this.closeCount == this.initialCloseCount) {
                runnable.run();
            } else {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "stream callback skipped by CloseGuardedRunner.", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class StreamObserver implements IncomingStreamObserver<RespT> {
        private final AbstractStream<ReqT, RespT, CallbackT>.CloseGuardedRunner dispatcher;

        StreamObserver(AbstractStream<ReqT, RespT, CallbackT>.CloseGuardedRunner closeGuardedRunner) {
            this.dispatcher = closeGuardedRunner;
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onHeaders(final Metadata metadata) {
            this.dispatcher.run(new Runnable() { // from class: com.google.firebase.firestore.remote.AbstractStream$StreamObserver$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractStream.StreamObserver.this.m857x509175ff(metadata);
                }
            });
        }

        /* renamed from: lambda$onHeaders$0$com-google-firebase-firestore-remote-AbstractStream$StreamObserver, reason: not valid java name */
        public /* synthetic */ void m857x509175ff(Metadata metadata) {
            if (Logger.isDebugEnabled()) {
                HashMap hashMap = new HashMap();
                for (String str : metadata.keys()) {
                    if (Datastore.WHITE_LISTED_HEADERS.contains(str.toLowerCase(Locale.ENGLISH))) {
                        hashMap.put(str, (String) metadata.get(Metadata.Key.of(str, Metadata.ASCII_STRING_MARSHALLER)));
                    }
                }
                if (hashMap.isEmpty()) {
                    return;
                }
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream received headers: %s", Integer.valueOf(System.identityHashCode(AbstractStream.this)), hashMap);
            }
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onNext(final RespT respt) {
            this.dispatcher.run(new Runnable() { // from class: com.google.firebase.firestore.remote.AbstractStream$StreamObserver$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractStream.StreamObserver.this.m858x77323899(respt);
                }
            });
        }

        /* renamed from: lambda$onNext$1$com-google-firebase-firestore-remote-AbstractStream$StreamObserver, reason: not valid java name */
        public /* synthetic */ void m858x77323899(Object obj) {
            if (Logger.isDebugEnabled()) {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream received: %s", Integer.valueOf(System.identityHashCode(AbstractStream.this)), obj);
            }
            AbstractStream.this.onNext(obj);
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onOpen() {
            this.dispatcher.run(new Runnable() { // from class: com.google.firebase.firestore.remote.AbstractStream$StreamObserver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractStream.StreamObserver.this.m859x42615001();
                }
            });
        }

        /* renamed from: lambda$onOpen$2$com-google-firebase-firestore-remote-AbstractStream$StreamObserver, reason: not valid java name */
        public /* synthetic */ void m859x42615001() {
            Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream is open", Integer.valueOf(System.identityHashCode(AbstractStream.this)));
            AbstractStream.this.onOpen();
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onClose(final Status status) {
            this.dispatcher.run(new Runnable() { // from class: com.google.firebase.firestore.remote.AbstractStream$StreamObserver$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractStream.StreamObserver.this.m856x427512ea(status);
                }
            });
        }

        /* renamed from: lambda$onClose$3$com-google-firebase-firestore-remote-AbstractStream$StreamObserver, reason: not valid java name */
        public /* synthetic */ void m856x427512ea(Status status) {
            if (status.isOk()) {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream closed.", Integer.valueOf(System.identityHashCode(AbstractStream.this)));
            } else {
                Logger.warn(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream closed with status: %s.", Integer.valueOf(System.identityHashCode(AbstractStream.this)), status);
            }
            AbstractStream.this.handleServerClose(status);
        }
    }

    /* loaded from: classes4.dex */
    class IdleTimeoutRunnable implements Runnable {
        IdleTimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractStream.this.handleIdleCloseTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractStream(FirestoreChannel firestoreChannel, MethodDescriptor<ReqT, RespT> methodDescriptor, AsyncQueue asyncQueue, AsyncQueue.TimerId timerId, AsyncQueue.TimerId timerId2, AsyncQueue.TimerId timerId3, CallbackT callbackt) {
        this.firestoreChannel = firestoreChannel;
        this.methodDescriptor = methodDescriptor;
        this.workerQueue = asyncQueue;
        this.idleTimerId = timerId2;
        this.healthTimerId = timerId3;
        this.listener = callbackt;
        this.backoff = new ExponentialBackoff(asyncQueue, timerId, BACKOFF_INITIAL_DELAY_MS, 1.5d, BACKOFF_MAX_DELAY_MS);
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public boolean isStarted() {
        this.workerQueue.verifyIsCurrentThread();
        return this.state == Stream.State.Starting || this.state == Stream.State.Backoff || isOpen();
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public boolean isOpen() {
        this.workerQueue.verifyIsCurrentThread();
        return this.state == Stream.State.Open || this.state == Stream.State.Healthy;
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public void start() {
        this.workerQueue.verifyIsCurrentThread();
        Assert.hardAssert(this.call == null, "Last call still set", new Object[0]);
        Assert.hardAssert(this.idleTimer == null, "Idle timer still set", new Object[0]);
        if (this.state == Stream.State.Error) {
            performBackoff();
            return;
        }
        Assert.hardAssert(this.state == Stream.State.Initial, "Already started", new Object[0]);
        this.call = this.firestoreChannel.runBidiStreamingRpc(this.methodDescriptor, new StreamObserver(new CloseGuardedRunner(this.closeCount)));
        this.state = Stream.State.Starting;
    }

    private void close(Stream.State state, Status status) {
        Assert.hardAssert(isStarted(), "Only started streams should be closed.", new Object[0]);
        Assert.hardAssert(state == Stream.State.Error || status.isOk(), "Can't provide an error when not in an error state.", new Object[0]);
        this.workerQueue.verifyIsCurrentThread();
        if (Datastore.isMissingSslCiphers(status)) {
            Util.crashMainThread(new IllegalStateException("The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.", status.getCause()));
        }
        cancelIdleCheck();
        cancelHealthCheck();
        this.backoff.cancel();
        this.closeCount++;
        Status.Code code = status.getCode();
        if (code == Status.Code.OK) {
            this.backoff.reset();
        } else if (code == Status.Code.RESOURCE_EXHAUSTED) {
            Logger.debug(getClass().getSimpleName(), "(%x) Using maximum backoff delay to prevent overloading the backend.", Integer.valueOf(System.identityHashCode(this)));
            this.backoff.resetToMax();
        } else if (code == Status.Code.UNAUTHENTICATED && this.state != Stream.State.Healthy) {
            this.firestoreChannel.invalidateToken();
        } else if (code == Status.Code.UNAVAILABLE && ((status.getCause() instanceof UnknownHostException) || (status.getCause() instanceof ConnectException))) {
            this.backoff.setTemporaryMaxDelay(BACKOFF_CLIENT_NETWORK_FAILURE_MAX_DELAY_MS);
        }
        if (state != Stream.State.Error) {
            Logger.debug(getClass().getSimpleName(), "(%x) Performing stream teardown", Integer.valueOf(System.identityHashCode(this)));
            tearDown();
        }
        if (this.call != null) {
            if (status.isOk()) {
                Logger.debug(getClass().getSimpleName(), "(%x) Closing stream client-side", Integer.valueOf(System.identityHashCode(this)));
                this.call.halfClose();
            }
            this.call = null;
        }
        this.state = state;
        this.listener.onClose(status);
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public void stop() {
        if (isStarted()) {
            close(Stream.State.Initial, Status.OK);
        }
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public void inhibitBackoff() {
        Assert.hardAssert(!isStarted(), "Can only inhibit backoff after in a stopped state", new Object[0]);
        this.workerQueue.verifyIsCurrentThread();
        this.state = Stream.State.Initial;
        this.backoff.reset();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeRequest(ReqT reqt) {
        this.workerQueue.verifyIsCurrentThread();
        Logger.debug(getClass().getSimpleName(), "(%x) Stream sending: %s", Integer.valueOf(System.identityHashCode(this)), reqt);
        cancelIdleCheck();
        this.call.sendMessage(reqt);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIdleCloseTimer() {
        if (isOpen()) {
            close(Stream.State.Initial, Status.OK);
        }
    }

    void handleServerClose(Status status) {
        Assert.hardAssert(isStarted(), "Can't handle server close on non-started stream!", new Object[0]);
        close(Stream.State.Error, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOpen() {
        this.state = Stream.State.Open;
        this.listener.onOpen();
        if (this.healthCheck == null) {
            this.healthCheck = this.workerQueue.enqueueAfterDelay(this.healthTimerId, HEALTHY_TIMEOUT_MS, new Runnable() { // from class: com.google.firebase.firestore.remote.AbstractStream$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractStream.this.m854x1b126b97();
                }
            });
        }
    }

    /* renamed from: lambda$onOpen$0$com-google-firebase-firestore-remote-AbstractStream, reason: not valid java name */
    public /* synthetic */ void m854x1b126b97() {
        if (isOpen()) {
            this.state = Stream.State.Healthy;
        }
    }

    private void performBackoff() {
        Assert.hardAssert(this.state == Stream.State.Error, "Should only perform backoff in an error state", new Object[0]);
        this.state = Stream.State.Backoff;
        this.backoff.backoffAndRun(new Runnable() { // from class: com.google.firebase.firestore.remote.AbstractStream$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AbstractStream.this.m855x27f47156();
            }
        });
    }

    /* renamed from: lambda$performBackoff$1$com-google-firebase-firestore-remote-AbstractStream, reason: not valid java name */
    public /* synthetic */ void m855x27f47156() {
        Assert.hardAssert(this.state == Stream.State.Backoff, "State should still be backoff but was %s", this.state);
        this.state = Stream.State.Initial;
        start();
        Assert.hardAssert(isStarted(), "Stream should have started", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markIdle() {
        if (isOpen() && this.idleTimer == null) {
            this.idleTimer = this.workerQueue.enqueueAfterDelay(this.idleTimerId, IDLE_TIMEOUT_MS, this.idleTimeoutRunnable);
        }
    }

    private void cancelIdleCheck() {
        AsyncQueue.DelayedTask delayedTask = this.idleTimer;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.idleTimer = null;
        }
    }

    private void cancelHealthCheck() {
        AsyncQueue.DelayedTask delayedTask = this.healthCheck;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.healthCheck = null;
        }
    }
}
