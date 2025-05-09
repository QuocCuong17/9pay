package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Objects;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class FirebaseFirestoreSettings {
    public static final long CACHE_SIZE_UNLIMITED = -1;
    static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
    public static final String DEFAULT_HOST = "firestore.googleapis.com";
    static final long MINIMUM_CACHE_BYTES = 1048576;
    private LocalCacheSettings cacheSettings;
    private final long cacheSizeBytes;
    private final String host;
    private final boolean persistenceEnabled;
    private final boolean sslEnabled;

    /* loaded from: classes4.dex */
    public static final class Builder {
        private LocalCacheSettings cacheSettings;
        private long cacheSizeBytes;
        private String host;
        private boolean persistenceEnabled;
        private boolean sslEnabled;
        private boolean usedLegacyCacheSettings;

        public Builder() {
            this.usedLegacyCacheSettings = false;
            this.host = FirebaseFirestoreSettings.DEFAULT_HOST;
            this.sslEnabled = true;
            this.persistenceEnabled = true;
            this.cacheSizeBytes = FirebaseFirestoreSettings.DEFAULT_CACHE_SIZE_BYTES;
        }

        public Builder(FirebaseFirestoreSettings firebaseFirestoreSettings) {
            this.usedLegacyCacheSettings = false;
            Preconditions.checkNotNull(firebaseFirestoreSettings, "Provided settings must not be null.");
            this.host = firebaseFirestoreSettings.host;
            this.sslEnabled = firebaseFirestoreSettings.sslEnabled;
            this.persistenceEnabled = firebaseFirestoreSettings.persistenceEnabled;
            long j = firebaseFirestoreSettings.cacheSizeBytes;
            this.cacheSizeBytes = j;
            if (!this.persistenceEnabled || j != FirebaseFirestoreSettings.DEFAULT_CACHE_SIZE_BYTES) {
                this.usedLegacyCacheSettings = true;
            }
            if (!this.usedLegacyCacheSettings) {
                this.cacheSettings = firebaseFirestoreSettings.cacheSettings;
            } else {
                Assert.hardAssert(firebaseFirestoreSettings.cacheSettings == null, "Given settings object mixes both cache config APIs, which is impossible.", new Object[0]);
            }
        }

        public Builder setHost(String str) {
            this.host = (String) Preconditions.checkNotNull(str, "Provided host must not be null.");
            return this;
        }

        public Builder setSslEnabled(boolean z) {
            this.sslEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setPersistenceEnabled(boolean z) {
            if (this.cacheSettings != null) {
                throw new IllegalStateException("New cache config API setLocalCacheSettings() is already used.");
            }
            this.persistenceEnabled = z;
            this.usedLegacyCacheSettings = true;
            return this;
        }

        @Deprecated
        public Builder setCacheSizeBytes(long j) {
            if (this.cacheSettings != null) {
                throw new IllegalStateException("New cache config API setLocalCacheSettings() is already used.");
            }
            if (j != -1 && j < 1048576) {
                throw new IllegalArgumentException("Cache size must be set to at least 1048576 bytes");
            }
            this.cacheSizeBytes = j;
            this.usedLegacyCacheSettings = true;
            return this;
        }

        public Builder setLocalCacheSettings(LocalCacheSettings localCacheSettings) {
            if (this.usedLegacyCacheSettings) {
                throw new IllegalStateException("Deprecated setPersistenceEnabled() or setCacheSizeBytes() is already used, remove those first.");
            }
            if (!(localCacheSettings instanceof MemoryCacheSettings) && !(localCacheSettings instanceof PersistentCacheSettings)) {
                throw new IllegalArgumentException("Only MemoryCacheSettings and PersistentCacheSettings are accepted");
            }
            this.cacheSettings = localCacheSettings;
            return this;
        }

        public String getHost() {
            return this.host;
        }

        public boolean isSslEnabled() {
            return this.sslEnabled;
        }

        @Deprecated
        public boolean isPersistenceEnabled() {
            return this.persistenceEnabled;
        }

        @Deprecated
        public long getCacheSizeBytes() {
            return this.cacheSizeBytes;
        }

        public FirebaseFirestoreSettings build() {
            if (!this.sslEnabled && this.host.equals(FirebaseFirestoreSettings.DEFAULT_HOST)) {
                throw new IllegalStateException("You can't set the 'sslEnabled' setting unless you also set a non-default 'host'.");
            }
            return new FirebaseFirestoreSettings(this);
        }
    }

    private FirebaseFirestoreSettings(Builder builder) {
        this.host = builder.host;
        this.sslEnabled = builder.sslEnabled;
        this.persistenceEnabled = builder.persistenceEnabled;
        this.cacheSizeBytes = builder.cacheSizeBytes;
        this.cacheSettings = builder.cacheSettings;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FirebaseFirestoreSettings firebaseFirestoreSettings = (FirebaseFirestoreSettings) obj;
        if (this.sslEnabled == firebaseFirestoreSettings.sslEnabled && this.persistenceEnabled == firebaseFirestoreSettings.persistenceEnabled && this.cacheSizeBytes == firebaseFirestoreSettings.cacheSizeBytes && this.host.equals(firebaseFirestoreSettings.host)) {
            return Objects.equals(this.cacheSettings, firebaseFirestoreSettings.cacheSettings);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((this.host.hashCode() * 31) + (this.sslEnabled ? 1 : 0)) * 31) + (this.persistenceEnabled ? 1 : 0)) * 31;
        long j = this.cacheSizeBytes;
        int i = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        LocalCacheSettings localCacheSettings = this.cacheSettings;
        return i + (localCacheSettings != null ? localCacheSettings.hashCode() : 0);
    }

    public String toString() {
        if (("FirebaseFirestoreSettings{host=" + this.host + ", sslEnabled=" + this.sslEnabled + ", persistenceEnabled=" + this.persistenceEnabled + ", cacheSizeBytes=" + this.cacheSizeBytes + ", cacheSettings=" + this.cacheSettings) == null) {
            return "null";
        }
        return this.cacheSettings.toString() + "}";
    }

    public String getHost() {
        return this.host;
    }

    public boolean isSslEnabled() {
        return this.sslEnabled;
    }

    @Deprecated
    public boolean isPersistenceEnabled() {
        LocalCacheSettings localCacheSettings = this.cacheSettings;
        if (localCacheSettings != null) {
            return localCacheSettings instanceof PersistentCacheSettings;
        }
        return this.persistenceEnabled;
    }

    @Deprecated
    public long getCacheSizeBytes() {
        LocalCacheSettings localCacheSettings = this.cacheSettings;
        if (localCacheSettings != null) {
            if (localCacheSettings instanceof PersistentCacheSettings) {
                return ((PersistentCacheSettings) localCacheSettings).getSizeBytes();
            }
            MemoryCacheSettings memoryCacheSettings = (MemoryCacheSettings) localCacheSettings;
            if (memoryCacheSettings.getGarbageCollectorSettings() instanceof MemoryLruGcSettings) {
                return ((MemoryLruGcSettings) memoryCacheSettings.getGarbageCollectorSettings()).getSizeBytes();
            }
            return -1L;
        }
        return this.cacheSizeBytes;
    }

    @Nullable
    public LocalCacheSettings getCacheSettings() {
        return this.cacheSettings;
    }
}
