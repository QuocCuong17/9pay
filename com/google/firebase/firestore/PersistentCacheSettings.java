package com.google.firebase.firestore;

/* loaded from: classes4.dex */
public final class PersistentCacheSettings implements LocalCacheSettings {
    private final long sizeBytes;

    public static Builder newBuilder() {
        return new Builder();
    }

    private PersistentCacheSettings(long j) {
        this.sizeBytes = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.sizeBytes == ((PersistentCacheSettings) obj).sizeBytes;
    }

    public int hashCode() {
        long j = this.sizeBytes;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return "PersistentCacheSettings{sizeBytes=" + this.sizeBytes + '}';
    }

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private long sizeBytes;

        private Builder() {
            this.sizeBytes = 104857600L;
        }

        public Builder setSizeBytes(long j) {
            this.sizeBytes = j;
            return this;
        }

        public PersistentCacheSettings build() {
            return new PersistentCacheSettings(this.sizeBytes);
        }
    }
}
