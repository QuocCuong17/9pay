package co.hyperverge.hypersnapsdk.objects;

import java.util.HashSet;

/* loaded from: classes2.dex */
public class SentryConfig {
    HashSet<String> sentryFilters;

    /* loaded from: classes2.dex */
    public static class SentryConfigBuilder {
        private HashSet<String> sentryFilters;

        SentryConfigBuilder() {
        }

        public SentryConfig build() {
            return new SentryConfig(this.sentryFilters);
        }

        public SentryConfigBuilder sentryFilters(HashSet<String> hashSet) {
            this.sentryFilters = hashSet;
            return this;
        }

        public String toString() {
            return "SentryConfig.SentryConfigBuilder(sentryFilters=" + this.sentryFilters + ")";
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SentryConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SentryConfig)) {
            return false;
        }
        SentryConfig sentryConfig = (SentryConfig) obj;
        if (!sentryConfig.canEqual(this)) {
            return false;
        }
        HashSet<String> sentryFilters = getSentryFilters();
        HashSet<String> sentryFilters2 = sentryConfig.getSentryFilters();
        return sentryFilters != null ? sentryFilters.equals(sentryFilters2) : sentryFilters2 == null;
    }

    public int hashCode() {
        HashSet<String> sentryFilters = getSentryFilters();
        return 59 + (sentryFilters == null ? 43 : sentryFilters.hashCode());
    }

    public void setSentryFilters(HashSet<String> hashSet) {
        this.sentryFilters = hashSet;
    }

    public String toString() {
        return "SentryConfig(sentryFilters=" + getSentryFilters() + ")";
    }

    SentryConfig(HashSet<String> hashSet) {
        this.sentryFilters = hashSet;
    }

    public static SentryConfigBuilder builder() {
        return new SentryConfigBuilder();
    }

    public HashSet<String> getSentryFilters() {
        return this.sentryFilters;
    }
}
