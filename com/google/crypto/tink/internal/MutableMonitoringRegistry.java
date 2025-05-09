package com.google.crypto.tink.internal;

import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public final class MutableMonitoringRegistry {
    private final AtomicReference<MonitoringClient> monitoringClient = new AtomicReference<>();
    private static final MutableMonitoringRegistry GLOBAL_INSTANCE = new MutableMonitoringRegistry();
    private static final DoNothingClient DO_NOTHING_CLIENT = new DoNothingClient();

    public static MutableMonitoringRegistry globalInstance() {
        return GLOBAL_INSTANCE;
    }

    /* loaded from: classes4.dex */
    private static class DoNothingClient implements MonitoringClient {
        private DoNothingClient() {
        }

        @Override // com.google.crypto.tink.monitoring.MonitoringClient
        public MonitoringClient.Logger createLogger(MonitoringKeysetInfo keysetInfo, String primitive, String api) {
            return MonitoringUtil.DO_NOTHING_LOGGER;
        }
    }

    public synchronized void clear() {
        this.monitoringClient.set(null);
    }

    public synchronized void registerMonitoringClient(MonitoringClient client) {
        if (this.monitoringClient.get() != null) {
            throw new IllegalStateException("a monitoring client has already been registered");
        }
        this.monitoringClient.set(client);
    }

    public MonitoringClient getMonitoringClient() {
        MonitoringClient monitoringClient = this.monitoringClient.get();
        return monitoringClient == null ? DO_NOTHING_CLIENT : monitoringClient;
    }
}
