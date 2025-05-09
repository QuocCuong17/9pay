package co.hyperverge.hypersnapsdk.objects;

import java.util.HashMap;

/* loaded from: classes2.dex */
public class ExternalConfigs {
    private HashMap<String, String> metadataMap;
    private SentryConfig sentryConfig;

    /* loaded from: classes2.dex */
    public static class ExternalConfigsBuilder {
        private HashMap<String, String> metadataMap;
        private SentryConfig sentryConfig;

        ExternalConfigsBuilder() {
        }

        public ExternalConfigs build() {
            return new ExternalConfigs(this.sentryConfig, this.metadataMap);
        }

        public ExternalConfigsBuilder metadataMap(HashMap<String, String> hashMap) {
            this.metadataMap = hashMap;
            return this;
        }

        public ExternalConfigsBuilder sentryConfig(SentryConfig sentryConfig) {
            this.sentryConfig = sentryConfig;
            return this;
        }

        public String toString() {
            return "ExternalConfigs.ExternalConfigsBuilder(sentryConfig=" + this.sentryConfig + ", metadataMap=" + this.metadataMap + ")";
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ExternalConfigs;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalConfigs)) {
            return false;
        }
        ExternalConfigs externalConfigs = (ExternalConfigs) obj;
        if (!externalConfigs.canEqual(this)) {
            return false;
        }
        SentryConfig sentryConfig = getSentryConfig();
        SentryConfig sentryConfig2 = externalConfigs.getSentryConfig();
        if (sentryConfig != null ? !sentryConfig.equals(sentryConfig2) : sentryConfig2 != null) {
            return false;
        }
        HashMap<String, String> metadataMap = getMetadataMap();
        HashMap<String, String> metadataMap2 = externalConfigs.getMetadataMap();
        return metadataMap != null ? metadataMap.equals(metadataMap2) : metadataMap2 == null;
    }

    public int hashCode() {
        SentryConfig sentryConfig = getSentryConfig();
        int hashCode = sentryConfig == null ? 43 : sentryConfig.hashCode();
        HashMap<String, String> metadataMap = getMetadataMap();
        return ((hashCode + 59) * 59) + (metadataMap != null ? metadataMap.hashCode() : 43);
    }

    public void setMetadataMap(HashMap<String, String> hashMap) {
        this.metadataMap = hashMap;
    }

    public void setSentryConfig(SentryConfig sentryConfig) {
        this.sentryConfig = sentryConfig;
    }

    public String toString() {
        return "ExternalConfigs(sentryConfig=" + getSentryConfig() + ", metadataMap=" + getMetadataMap() + ")";
    }

    ExternalConfigs(SentryConfig sentryConfig, HashMap<String, String> hashMap) {
        this.sentryConfig = sentryConfig;
        this.metadataMap = hashMap;
    }

    public static ExternalConfigsBuilder builder() {
        return new ExternalConfigsBuilder();
    }

    public SentryConfig getSentryConfig() {
        return this.sentryConfig;
    }

    public HashMap<String, String> getMetadataMap() {
        return this.metadataMap;
    }
}
