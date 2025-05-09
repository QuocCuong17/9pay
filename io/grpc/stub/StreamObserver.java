package io.grpc.stub;

/* loaded from: classes5.dex */
public interface StreamObserver<V> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(V v);
}
