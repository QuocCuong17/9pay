package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;

/* loaded from: classes5.dex */
public final class FailingClientStream extends NoopClientStream {
    private final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;
    private boolean started;
    private final ClientStreamTracer[] tracers;

    public FailingClientStream(Status status, ClientStreamTracer[] clientStreamTracerArr) {
        this(status, ClientStreamListener.RpcProgress.PROCESSED, clientStreamTracerArr);
    }

    public FailingClientStream(Status status, ClientStreamListener.RpcProgress rpcProgress, ClientStreamTracer[] clientStreamTracerArr) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress;
        this.tracers = clientStreamTracerArr;
    }

    @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
    public void start(ClientStreamListener clientStreamListener) {
        Preconditions.checkState(!this.started, "already started");
        this.started = true;
        for (ClientStreamTracer clientStreamTracer : this.tracers) {
            clientStreamTracer.streamClosed(this.error);
        }
        clientStreamListener.closed(this.error, this.rpcProgress, new Metadata());
    }

    Status getError() {
        return this.error;
    }

    @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue("error", this.error).appendKeyValue("progress", this.rpcProgress);
    }
}
