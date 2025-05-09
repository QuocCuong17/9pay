package io.grpc.okhttp;

import io.grpc.ServerStreamTracer;
import io.grpc.internal.InternalServer;
import io.grpc.internal.TransportTracer;
import java.util.List;

/* loaded from: classes5.dex */
public final class InternalOkHttpServerBuilder {
    public static InternalServer buildTransportServers(OkHttpServerBuilder okHttpServerBuilder, List<? extends ServerStreamTracer.Factory> list) {
        return okHttpServerBuilder.buildTransportServers(list);
    }

    public static void setTransportTracerFactory(OkHttpServerBuilder okHttpServerBuilder, TransportTracer.Factory factory) {
        okHttpServerBuilder.setTransportTracerFactory(factory);
    }

    public static void setStatsEnabled(OkHttpServerBuilder okHttpServerBuilder, boolean z) {
        okHttpServerBuilder.setStatsEnabled(z);
    }

    private InternalOkHttpServerBuilder() {
    }
}
