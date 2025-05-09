package io.sentry;

import java.util.List;

/* loaded from: classes5.dex */
public interface ITransactionProfiler {
    void close();

    ProfilingTraceData onTransactionFinish(ITransaction iTransaction, List<PerformanceCollectionData> list);

    void onTransactionStart(ITransaction iTransaction);
}
