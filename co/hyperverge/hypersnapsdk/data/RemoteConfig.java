package co.hyperverge.hypersnapsdk.data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class RemoteConfig implements Serializable {

    @SerializedName(alternate = {"rudderStackConfig"}, value = "analyticsConfig")
    private AnalyticsConfig analyticsConfig;

    @SerializedName("use_branding")
    private boolean useBranding = true;

    @SerializedName("mixpanelConfig")
    private MixpanelConfig mixpanelConfig = new MixpanelConfig();

    @SerializedName("useIpToGeo")
    private boolean useIpToGeo = false;

    @SerializedName("use_compression")
    private boolean useCompression = false;

    @SerializedName("use_analytics")
    private boolean useAnalytics = true;

    @SerializedName("useSelfieVideoRecording")
    private boolean useSelfieVideoRecording = false;

    protected boolean canEqual(Object obj) {
        return obj instanceof RemoteConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RemoteConfig)) {
            return false;
        }
        RemoteConfig remoteConfig = (RemoteConfig) obj;
        if (!remoteConfig.canEqual(this) || isUseBranding() != remoteConfig.isUseBranding() || isUseIpToGeo() != remoteConfig.isUseIpToGeo() || isUseCompression() != remoteConfig.isUseCompression() || isUseAnalytics() != remoteConfig.isUseAnalytics() || isUseSelfieVideoRecording() != remoteConfig.isUseSelfieVideoRecording()) {
            return false;
        }
        MixpanelConfig mixpanelConfig = getMixpanelConfig();
        MixpanelConfig mixpanelConfig2 = remoteConfig.getMixpanelConfig();
        if (mixpanelConfig != null ? !mixpanelConfig.equals(mixpanelConfig2) : mixpanelConfig2 != null) {
            return false;
        }
        AnalyticsConfig analyticsConfig = getAnalyticsConfig();
        AnalyticsConfig analyticsConfig2 = remoteConfig.getAnalyticsConfig();
        return analyticsConfig != null ? analyticsConfig.equals(analyticsConfig2) : analyticsConfig2 == null;
    }

    public int hashCode() {
        int i = (((((((((isUseBranding() ? 79 : 97) + 59) * 59) + (isUseIpToGeo() ? 79 : 97)) * 59) + (isUseCompression() ? 79 : 97)) * 59) + (isUseAnalytics() ? 79 : 97)) * 59) + (isUseSelfieVideoRecording() ? 79 : 97);
        MixpanelConfig mixpanelConfig = getMixpanelConfig();
        int hashCode = (i * 59) + (mixpanelConfig == null ? 43 : mixpanelConfig.hashCode());
        AnalyticsConfig analyticsConfig = getAnalyticsConfig();
        return (hashCode * 59) + (analyticsConfig != null ? analyticsConfig.hashCode() : 43);
    }

    public void setAnalyticsConfig(AnalyticsConfig analyticsConfig) {
        this.analyticsConfig = analyticsConfig;
    }

    public void setMixpanelConfig(MixpanelConfig mixpanelConfig) {
        this.mixpanelConfig = mixpanelConfig;
    }

    public void setUseAnalytics(boolean z) {
        this.useAnalytics = z;
    }

    public void setUseBranding(boolean z) {
        this.useBranding = z;
    }

    public void setUseCompression(boolean z) {
        this.useCompression = z;
    }

    public void setUseIpToGeo(boolean z) {
        this.useIpToGeo = z;
    }

    public void setUseSelfieVideoRecording(boolean z) {
        this.useSelfieVideoRecording = z;
    }

    public String toString() {
        return "RemoteConfig(useBranding=" + isUseBranding() + ", mixpanelConfig=" + getMixpanelConfig() + ", useIpToGeo=" + isUseIpToGeo() + ", useCompression=" + isUseCompression() + ", useAnalytics=" + isUseAnalytics() + ", analyticsConfig=" + getAnalyticsConfig() + ", useSelfieVideoRecording=" + isUseSelfieVideoRecording() + ")";
    }

    public boolean isUseBranding() {
        return this.useBranding;
    }

    public MixpanelConfig getMixpanelConfig() {
        return this.mixpanelConfig;
    }

    public boolean isUseIpToGeo() {
        return this.useIpToGeo;
    }

    public boolean isUseCompression() {
        return this.useCompression;
    }

    public boolean isUseAnalytics() {
        return this.useAnalytics;
    }

    public AnalyticsConfig getAnalyticsConfig() {
        return this.analyticsConfig;
    }

    public boolean isUseSelfieVideoRecording() {
        return this.useSelfieVideoRecording;
    }

    /* loaded from: classes2.dex */
    public static class AnalyticsConfig {

        @SerializedName("effectiveFrom")
        private String effectiveFrom;

        @SerializedName("loggingPercentage")
        private Integer loggingPercentage;

        /* loaded from: classes2.dex */
        public static class AnalyticsConfigBuilder {
            private String effectiveFrom;
            private Integer loggingPercentage;

            AnalyticsConfigBuilder() {
            }

            public AnalyticsConfig build() {
                return new AnalyticsConfig(this.loggingPercentage, this.effectiveFrom);
            }

            public AnalyticsConfigBuilder effectiveFrom(String str) {
                this.effectiveFrom = str;
                return this;
            }

            public AnalyticsConfigBuilder loggingPercentage(Integer num) {
                this.loggingPercentage = num;
                return this;
            }

            public String toString() {
                return "RemoteConfig.AnalyticsConfig.AnalyticsConfigBuilder(loggingPercentage=" + this.loggingPercentage + ", effectiveFrom=" + this.effectiveFrom + ")";
            }
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof AnalyticsConfig;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AnalyticsConfig)) {
                return false;
            }
            AnalyticsConfig analyticsConfig = (AnalyticsConfig) obj;
            if (!analyticsConfig.canEqual(this)) {
                return false;
            }
            Integer loggingPercentage = getLoggingPercentage();
            Integer loggingPercentage2 = analyticsConfig.getLoggingPercentage();
            if (loggingPercentage != null ? !loggingPercentage.equals(loggingPercentage2) : loggingPercentage2 != null) {
                return false;
            }
            String effectiveFrom = getEffectiveFrom();
            String effectiveFrom2 = analyticsConfig.getEffectiveFrom();
            return effectiveFrom != null ? effectiveFrom.equals(effectiveFrom2) : effectiveFrom2 == null;
        }

        public int hashCode() {
            Integer loggingPercentage = getLoggingPercentage();
            int hashCode = loggingPercentage == null ? 43 : loggingPercentage.hashCode();
            String effectiveFrom = getEffectiveFrom();
            return ((hashCode + 59) * 59) + (effectiveFrom != null ? effectiveFrom.hashCode() : 43);
        }

        public void setEffectiveFrom(String str) {
            this.effectiveFrom = str;
        }

        public void setLoggingPercentage(Integer num) {
            this.loggingPercentage = num;
        }

        public String toString() {
            return "RemoteConfig.AnalyticsConfig(loggingPercentage=" + getLoggingPercentage() + ", effectiveFrom=" + getEffectiveFrom() + ")";
        }

        AnalyticsConfig(Integer num, String str) {
            this.loggingPercentage = 30;
            this.effectiveFrom = "";
            this.loggingPercentage = num;
            this.effectiveFrom = str;
        }

        public static AnalyticsConfigBuilder builder() {
            return new AnalyticsConfigBuilder();
        }

        public Integer getLoggingPercentage() {
            return this.loggingPercentage;
        }

        public String getEffectiveFrom() {
            return this.effectiveFrom;
        }
    }
}
