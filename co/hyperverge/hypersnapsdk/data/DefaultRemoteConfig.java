package co.hyperverge.hypersnapsdk.data;

import co.hyperverge.hypersnapsdk.utils.AppConstants;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class DefaultRemoteConfig {

    @SerializedName("mobilesdk")
    private MobileSdkConfig mobileSdkConfig;

    protected boolean canEqual(Object obj) {
        return obj instanceof DefaultRemoteConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultRemoteConfig)) {
            return false;
        }
        DefaultRemoteConfig defaultRemoteConfig = (DefaultRemoteConfig) obj;
        if (!defaultRemoteConfig.canEqual(this)) {
            return false;
        }
        MobileSdkConfig mobileSdkConfig = getMobileSdkConfig();
        MobileSdkConfig mobileSdkConfig2 = defaultRemoteConfig.getMobileSdkConfig();
        return mobileSdkConfig != null ? mobileSdkConfig.equals(mobileSdkConfig2) : mobileSdkConfig2 == null;
    }

    public int hashCode() {
        MobileSdkConfig mobileSdkConfig = getMobileSdkConfig();
        return 59 + (mobileSdkConfig == null ? 43 : mobileSdkConfig.hashCode());
    }

    public void setMobileSdkConfig(MobileSdkConfig mobileSdkConfig) {
        this.mobileSdkConfig = mobileSdkConfig;
    }

    public String toString() {
        return "DefaultRemoteConfig(mobileSdkConfig=" + getMobileSdkConfig() + ")";
    }

    public MobileSdkConfig getMobileSdkConfig() {
        return this.mobileSdkConfig;
    }

    /* loaded from: classes2.dex */
    public static class MobileSdkConfig {

        @SerializedName(AppConstants.ANALYTICS_FEATURE)
        private AnalyticsConfig analyticsConfig;

        @SerializedName("crashguard")
        private CrashguardConfig crashguardConfig;

        /* loaded from: classes2.dex */
        public static class MobileSdkConfigBuilder {
            private AnalyticsConfig analyticsConfig;
            private CrashguardConfig crashguardConfig;

            MobileSdkConfigBuilder() {
            }

            public MobileSdkConfigBuilder analyticsConfig(AnalyticsConfig analyticsConfig) {
                this.analyticsConfig = analyticsConfig;
                return this;
            }

            public MobileSdkConfig build() {
                return new MobileSdkConfig(this.analyticsConfig, this.crashguardConfig);
            }

            public MobileSdkConfigBuilder crashguardConfig(CrashguardConfig crashguardConfig) {
                this.crashguardConfig = crashguardConfig;
                return this;
            }

            public String toString() {
                return "DefaultRemoteConfig.MobileSdkConfig.MobileSdkConfigBuilder(analyticsConfig=" + this.analyticsConfig + ", crashguardConfig=" + this.crashguardConfig + ")";
            }
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof MobileSdkConfig;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MobileSdkConfig)) {
                return false;
            }
            MobileSdkConfig mobileSdkConfig = (MobileSdkConfig) obj;
            if (!mobileSdkConfig.canEqual(this)) {
                return false;
            }
            AnalyticsConfig analyticsConfig = getAnalyticsConfig();
            AnalyticsConfig analyticsConfig2 = mobileSdkConfig.getAnalyticsConfig();
            if (analyticsConfig != null ? !analyticsConfig.equals(analyticsConfig2) : analyticsConfig2 != null) {
                return false;
            }
            CrashguardConfig crashguardConfig = getCrashguardConfig();
            CrashguardConfig crashguardConfig2 = mobileSdkConfig.getCrashguardConfig();
            return crashguardConfig != null ? crashguardConfig.equals(crashguardConfig2) : crashguardConfig2 == null;
        }

        public int hashCode() {
            AnalyticsConfig analyticsConfig = getAnalyticsConfig();
            int hashCode = analyticsConfig == null ? 43 : analyticsConfig.hashCode();
            CrashguardConfig crashguardConfig = getCrashguardConfig();
            return ((hashCode + 59) * 59) + (crashguardConfig != null ? crashguardConfig.hashCode() : 43);
        }

        public void setAnalyticsConfig(AnalyticsConfig analyticsConfig) {
            this.analyticsConfig = analyticsConfig;
        }

        public void setCrashguardConfig(CrashguardConfig crashguardConfig) {
            this.crashguardConfig = crashguardConfig;
        }

        public String toString() {
            return "DefaultRemoteConfig.MobileSdkConfig(analyticsConfig=" + getAnalyticsConfig() + ", crashguardConfig=" + getCrashguardConfig() + ")";
        }

        MobileSdkConfig(AnalyticsConfig analyticsConfig, CrashguardConfig crashguardConfig) {
            this.analyticsConfig = analyticsConfig;
            this.crashguardConfig = crashguardConfig;
        }

        public static MobileSdkConfigBuilder builder() {
            return new MobileSdkConfigBuilder();
        }

        public AnalyticsConfig getAnalyticsConfig() {
            return this.analyticsConfig;
        }

        public CrashguardConfig getCrashguardConfig() {
            return this.crashguardConfig;
        }
    }

    /* loaded from: classes2.dex */
    public static class AnalyticsConfig {

        @SerializedName("endpoint")
        private String endpoint;

        /* loaded from: classes2.dex */
        public static class AnalyticsConfigBuilder {
            private String endpoint;

            AnalyticsConfigBuilder() {
            }

            public AnalyticsConfig build() {
                return new AnalyticsConfig(this.endpoint);
            }

            public AnalyticsConfigBuilder endpoint(String str) {
                this.endpoint = str;
                return this;
            }

            public String toString() {
                return "DefaultRemoteConfig.AnalyticsConfig.AnalyticsConfigBuilder(endpoint=" + this.endpoint + ")";
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
            String endpoint = getEndpoint();
            String endpoint2 = analyticsConfig.getEndpoint();
            return endpoint != null ? endpoint.equals(endpoint2) : endpoint2 == null;
        }

        public int hashCode() {
            String endpoint = getEndpoint();
            return 59 + (endpoint == null ? 43 : endpoint.hashCode());
        }

        public void setEndpoint(String str) {
            this.endpoint = str;
        }

        public String toString() {
            return "DefaultRemoteConfig.AnalyticsConfig(endpoint=" + getEndpoint() + ")";
        }

        AnalyticsConfig(String str) {
            this.endpoint = str;
        }

        public static AnalyticsConfigBuilder builder() {
            return new AnalyticsConfigBuilder();
        }

        public String getEndpoint() {
            return this.endpoint;
        }
    }

    /* loaded from: classes2.dex */
    public static class CrashguardConfig {

        @SerializedName("endpoint")
        private String endpoint;

        /* loaded from: classes2.dex */
        public static class CrashguardConfigBuilder {
            private String endpoint;

            CrashguardConfigBuilder() {
            }

            public CrashguardConfig build() {
                return new CrashguardConfig(this.endpoint);
            }

            public CrashguardConfigBuilder endpoint(String str) {
                this.endpoint = str;
                return this;
            }

            public String toString() {
                return "DefaultRemoteConfig.CrashguardConfig.CrashguardConfigBuilder(endpoint=" + this.endpoint + ")";
            }
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof CrashguardConfig;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CrashguardConfig)) {
                return false;
            }
            CrashguardConfig crashguardConfig = (CrashguardConfig) obj;
            if (!crashguardConfig.canEqual(this)) {
                return false;
            }
            String endpoint = getEndpoint();
            String endpoint2 = crashguardConfig.getEndpoint();
            return endpoint != null ? endpoint.equals(endpoint2) : endpoint2 == null;
        }

        public int hashCode() {
            String endpoint = getEndpoint();
            return 59 + (endpoint == null ? 43 : endpoint.hashCode());
        }

        public void setEndpoint(String str) {
            this.endpoint = str;
        }

        public String toString() {
            return "DefaultRemoteConfig.CrashguardConfig(endpoint=" + getEndpoint() + ")";
        }

        CrashguardConfig(String str) {
            this.endpoint = str;
        }

        public static CrashguardConfigBuilder builder() {
            return new CrashguardConfigBuilder();
        }

        public String getEndpoint() {
            return this.endpoint;
        }
    }
}
