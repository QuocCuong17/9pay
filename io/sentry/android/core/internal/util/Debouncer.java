package io.sentry.android.core.internal.util;

import io.sentry.transport.ICurrentDateProvider;

/* loaded from: classes5.dex */
public class Debouncer {
    private Long lastExecutionTime = null;
    private final ICurrentDateProvider timeProvider;
    private final long waitTimeMs;

    public Debouncer(ICurrentDateProvider iCurrentDateProvider, long j) {
        this.timeProvider = iCurrentDateProvider;
        this.waitTimeMs = j;
    }

    public boolean checkForDebounce() {
        long currentTimeMillis = this.timeProvider.getCurrentTimeMillis();
        Long l = this.lastExecutionTime;
        if (l != null && l.longValue() + this.waitTimeMs > currentTimeMillis) {
            return true;
        }
        this.lastExecutionTime = Long.valueOf(currentTimeMillis);
        return false;
    }
}
