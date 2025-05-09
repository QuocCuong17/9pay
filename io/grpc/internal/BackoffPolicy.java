package io.grpc.internal;

/* loaded from: classes5.dex */
public interface BackoffPolicy {

    /* loaded from: classes5.dex */
    public interface Provider {
        BackoffPolicy get();
    }

    long nextBackoffNanos();
}
