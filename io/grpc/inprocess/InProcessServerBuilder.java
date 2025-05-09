package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import io.grpc.Deadline;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import java.io.File;
import java.net.SocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public final class InProcessServerBuilder extends AbstractServerImplBuilder<InProcessServerBuilder> {
    final SocketAddress listenAddress;
    int maxInboundMetadataSize = Integer.MAX_VALUE;
    ObjectPool<ScheduledExecutorService> schedulerPool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
    private final ServerImplBuilder serverImplBuilder;

    public static InProcessServerBuilder forName(String str) {
        return forAddress(new InProcessSocketAddress((String) Preconditions.checkNotNull(str, "name")));
    }

    public static InProcessServerBuilder forAddress(SocketAddress socketAddress) {
        return new InProcessServerBuilder(socketAddress);
    }

    public static InProcessServerBuilder forPort(int i) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static String generateName() {
        return UUID.randomUUID().toString();
    }

    private InProcessServerBuilder(SocketAddress socketAddress) {
        this.listenAddress = (SocketAddress) Preconditions.checkNotNull(socketAddress, "listenAddress");
        ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new ServerImplBuilder.ClientTransportServersBuilder() { // from class: io.grpc.inprocess.InProcessServerBuilder.1InProcessClientTransportServersBuilder
            @Override // io.grpc.internal.ServerImplBuilder.ClientTransportServersBuilder
            public InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list) {
                return InProcessServerBuilder.this.buildTransportServers(list);
            }
        });
        this.serverImplBuilder = serverImplBuilder;
        serverImplBuilder.setStatsRecordStartedRpcs(false);
        serverImplBuilder.setStatsRecordFinishedRpcs(false);
        handshakeTimeout(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder
    protected ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    public InProcessServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.schedulerPool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public InProcessServerBuilder deadlineTicker(Deadline.Ticker ticker) {
        this.serverImplBuilder.setDeadlineTicker(ticker);
        return this;
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public InProcessServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    InProcessServer buildTransportServers(List<? extends ServerStreamTracer.Factory> list) {
        return new InProcessServer(this, list);
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public InProcessServerBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("TLS not supported in InProcessServer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.serverImplBuilder.setStatsEnabled(z);
    }
}
