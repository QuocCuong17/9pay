package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

/* loaded from: classes5.dex */
public interface ClientStreamListener extends StreamListener {

    /* loaded from: classes5.dex */
    public enum RpcProgress {
        PROCESSED,
        REFUSED,
        DROPPED,
        MISCARRIED
    }

    void closed(Status status, RpcProgress rpcProgress, Metadata metadata);

    void headersRead(Metadata metadata);
}
