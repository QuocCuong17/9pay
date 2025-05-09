package io.sentry.clientreport;

import java.util.List;

/* loaded from: classes5.dex */
public interface IClientReportStorage {
    void addCount(ClientReportKey clientReportKey, Long l);

    List<DiscardedEvent> resetCountsAndGet();
}
