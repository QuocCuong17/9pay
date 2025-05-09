package com.google.firebase.database.core.utilities;

/* loaded from: classes4.dex */
public class OffsetClock implements Clock {
    private final Clock baseClock;
    private long offset;

    public OffsetClock(Clock clock, long j) {
        this.offset = 0L;
        this.baseClock = clock;
        this.offset = j;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    @Override // com.google.firebase.database.core.utilities.Clock
    public long millis() {
        return this.baseClock.millis() + this.offset;
    }
}
