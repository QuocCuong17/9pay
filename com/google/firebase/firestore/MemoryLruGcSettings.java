package com.google.firebase.firestore;

/* loaded from: classes4.dex */
public final class MemoryLruGcSettings implements MemoryGarbageCollectorSettings {
    private long sizeBytes;

    /* loaded from: classes4.dex */
    public static class Builder {
        private long sizeBytes;

        private Builder() {
            this.sizeBytes = 104857600L;
        }

        public MemoryLruGcSettings build() {
            return new MemoryLruGcSettings(this.sizeBytes);
        }

        public Builder setSizeBytes(long j) {
            this.sizeBytes = j;
            return this;
        }
    }

    private MemoryLruGcSettings(long j) {
        this.sizeBytes = j;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.sizeBytes == ((MemoryLruGcSettings) obj).sizeBytes;
    }

    public int hashCode() {
        long j = this.sizeBytes;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return "MemoryLruGcSettings{cacheSize=" + getSizeBytes() + "}";
    }
}
