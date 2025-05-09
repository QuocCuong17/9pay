package co.hyperverge.hypersnapsdk.analytics;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class TrackerAnalyticsData implements Serializable {

    @SerializedName("ABI Architecture")
    private String abi;

    @SerializedName("App Id")
    private String appId;

    @SerializedName("App Name")
    private String appName;

    @SerializedName("Device Manufacturer")
    private String deviceManufacturer;

    @SerializedName("Device Model")
    private String deviceModel;

    @SerializedName("Device Name")
    private String deviceName;

    @SerializedName("distinct_id")
    private String distinctId;

    @SerializedName("token")
    private String mixPanelToken;

    @SerializedName("OS Version")
    private String osVersion;

    @SerializedName("Screen Height")
    private int screenHeight;

    @SerializedName("Screen Width")
    private int screenWidth;

    @SerializedName("SDK Version")
    private String sdkVersionNumber;

    @SerializedName("Current Time")
    private long timeInMilliSeconds;

    @SerializedName("Token")
    private String token;

    @SerializedName("TransactionId")
    private String transactionId;

    /* loaded from: classes2.dex */
    public static class TrackerAnalyticsDataBuilder {
        private String abi;
        private String appId;
        private String appName;
        private String deviceManufacturer;
        private String deviceModel;
        private String deviceName;
        private String distinctId;
        private String mixPanelToken;
        private String osVersion;
        private int screenHeight;
        private int screenWidth;
        private String sdkVersionNumber;
        private long timeInMilliSeconds;
        private String token;
        private String transactionId;

        TrackerAnalyticsDataBuilder() {
        }

        public TrackerAnalyticsDataBuilder abi(String str) {
            this.abi = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder appId(String str) {
            this.appId = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder appName(String str) {
            this.appName = str;
            return this;
        }

        public TrackerAnalyticsData build() {
            return new TrackerAnalyticsData(this.appId, this.appName, this.sdkVersionNumber, this.token, this.mixPanelToken, this.distinctId, this.timeInMilliSeconds, this.screenWidth, this.screenHeight, this.deviceName, this.osVersion, this.deviceManufacturer, this.deviceModel, this.transactionId, this.abi);
        }

        public TrackerAnalyticsDataBuilder deviceManufacturer(String str) {
            this.deviceManufacturer = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder deviceModel(String str) {
            this.deviceModel = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder deviceName(String str) {
            this.deviceName = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder distinctId(String str) {
            this.distinctId = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder mixPanelToken(String str) {
            this.mixPanelToken = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder osVersion(String str) {
            this.osVersion = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder screenHeight(int i) {
            this.screenHeight = i;
            return this;
        }

        public TrackerAnalyticsDataBuilder screenWidth(int i) {
            this.screenWidth = i;
            return this;
        }

        public TrackerAnalyticsDataBuilder sdkVersionNumber(String str) {
            this.sdkVersionNumber = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder timeInMilliSeconds(long j) {
            this.timeInMilliSeconds = j;
            return this;
        }

        public String toString() {
            return "TrackerAnalyticsData.TrackerAnalyticsDataBuilder(appId=" + this.appId + ", appName=" + this.appName + ", sdkVersionNumber=" + this.sdkVersionNumber + ", token=" + this.token + ", mixPanelToken=" + this.mixPanelToken + ", distinctId=" + this.distinctId + ", timeInMilliSeconds=" + this.timeInMilliSeconds + ", screenWidth=" + this.screenWidth + ", screenHeight=" + this.screenHeight + ", deviceName=" + this.deviceName + ", osVersion=" + this.osVersion + ", deviceManufacturer=" + this.deviceManufacturer + ", deviceModel=" + this.deviceModel + ", transactionId=" + this.transactionId + ", abi=" + this.abi + ")";
        }

        public TrackerAnalyticsDataBuilder token(String str) {
            this.token = str;
            return this;
        }

        public TrackerAnalyticsDataBuilder transactionId(String str) {
            this.transactionId = str;
            return this;
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TrackerAnalyticsData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TrackerAnalyticsData)) {
            return false;
        }
        TrackerAnalyticsData trackerAnalyticsData = (TrackerAnalyticsData) obj;
        if (!trackerAnalyticsData.canEqual(this) || getTimeInMilliSeconds() != trackerAnalyticsData.getTimeInMilliSeconds() || getScreenWidth() != trackerAnalyticsData.getScreenWidth() || getScreenHeight() != trackerAnalyticsData.getScreenHeight()) {
            return false;
        }
        String appId = getAppId();
        String appId2 = trackerAnalyticsData.getAppId();
        if (appId != null ? !appId.equals(appId2) : appId2 != null) {
            return false;
        }
        String appName = getAppName();
        String appName2 = trackerAnalyticsData.getAppName();
        if (appName != null ? !appName.equals(appName2) : appName2 != null) {
            return false;
        }
        String sdkVersionNumber = getSdkVersionNumber();
        String sdkVersionNumber2 = trackerAnalyticsData.getSdkVersionNumber();
        if (sdkVersionNumber != null ? !sdkVersionNumber.equals(sdkVersionNumber2) : sdkVersionNumber2 != null) {
            return false;
        }
        String token = getToken();
        String token2 = trackerAnalyticsData.getToken();
        if (token != null ? !token.equals(token2) : token2 != null) {
            return false;
        }
        String mixPanelToken = getMixPanelToken();
        String mixPanelToken2 = trackerAnalyticsData.getMixPanelToken();
        if (mixPanelToken != null ? !mixPanelToken.equals(mixPanelToken2) : mixPanelToken2 != null) {
            return false;
        }
        String distinctId = getDistinctId();
        String distinctId2 = trackerAnalyticsData.getDistinctId();
        if (distinctId != null ? !distinctId.equals(distinctId2) : distinctId2 != null) {
            return false;
        }
        String deviceName = getDeviceName();
        String deviceName2 = trackerAnalyticsData.getDeviceName();
        if (deviceName != null ? !deviceName.equals(deviceName2) : deviceName2 != null) {
            return false;
        }
        String osVersion = getOsVersion();
        String osVersion2 = trackerAnalyticsData.getOsVersion();
        if (osVersion != null ? !osVersion.equals(osVersion2) : osVersion2 != null) {
            return false;
        }
        String deviceManufacturer = getDeviceManufacturer();
        String deviceManufacturer2 = trackerAnalyticsData.getDeviceManufacturer();
        if (deviceManufacturer != null ? !deviceManufacturer.equals(deviceManufacturer2) : deviceManufacturer2 != null) {
            return false;
        }
        String deviceModel = getDeviceModel();
        String deviceModel2 = trackerAnalyticsData.getDeviceModel();
        if (deviceModel != null ? !deviceModel.equals(deviceModel2) : deviceModel2 != null) {
            return false;
        }
        String transactionId = getTransactionId();
        String transactionId2 = trackerAnalyticsData.getTransactionId();
        if (transactionId != null ? !transactionId.equals(transactionId2) : transactionId2 != null) {
            return false;
        }
        String abi = getAbi();
        String abi2 = trackerAnalyticsData.getAbi();
        return abi != null ? abi.equals(abi2) : abi2 == null;
    }

    public int hashCode() {
        long timeInMilliSeconds = getTimeInMilliSeconds();
        int screenWidth = ((((((int) (timeInMilliSeconds ^ (timeInMilliSeconds >>> 32))) + 59) * 59) + getScreenWidth()) * 59) + getScreenHeight();
        String appId = getAppId();
        int hashCode = (screenWidth * 59) + (appId == null ? 43 : appId.hashCode());
        String appName = getAppName();
        int hashCode2 = (hashCode * 59) + (appName == null ? 43 : appName.hashCode());
        String sdkVersionNumber = getSdkVersionNumber();
        int hashCode3 = (hashCode2 * 59) + (sdkVersionNumber == null ? 43 : sdkVersionNumber.hashCode());
        String token = getToken();
        int hashCode4 = (hashCode3 * 59) + (token == null ? 43 : token.hashCode());
        String mixPanelToken = getMixPanelToken();
        int hashCode5 = (hashCode4 * 59) + (mixPanelToken == null ? 43 : mixPanelToken.hashCode());
        String distinctId = getDistinctId();
        int hashCode6 = (hashCode5 * 59) + (distinctId == null ? 43 : distinctId.hashCode());
        String deviceName = getDeviceName();
        int hashCode7 = (hashCode6 * 59) + (deviceName == null ? 43 : deviceName.hashCode());
        String osVersion = getOsVersion();
        int hashCode8 = (hashCode7 * 59) + (osVersion == null ? 43 : osVersion.hashCode());
        String deviceManufacturer = getDeviceManufacturer();
        int hashCode9 = (hashCode8 * 59) + (deviceManufacturer == null ? 43 : deviceManufacturer.hashCode());
        String deviceModel = getDeviceModel();
        int hashCode10 = (hashCode9 * 59) + (deviceModel == null ? 43 : deviceModel.hashCode());
        String transactionId = getTransactionId();
        int hashCode11 = (hashCode10 * 59) + (transactionId == null ? 43 : transactionId.hashCode());
        String abi = getAbi();
        return (hashCode11 * 59) + (abi != null ? abi.hashCode() : 43);
    }

    public void setAbi(String str) {
        this.abi = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setDeviceManufacturer(String str) {
        this.deviceManufacturer = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDistinctId(String str) {
        this.distinctId = str;
    }

    public void setMixPanelToken(String str) {
        this.mixPanelToken = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public void setSdkVersionNumber(String str) {
        this.sdkVersionNumber = str;
    }

    public void setTimeInMilliSeconds(long j) {
        this.timeInMilliSeconds = j;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTransactionId(String str) {
        this.transactionId = str;
    }

    public String toString() {
        return "TrackerAnalyticsData(appId=" + getAppId() + ", appName=" + getAppName() + ", sdkVersionNumber=" + getSdkVersionNumber() + ", token=" + getToken() + ", mixPanelToken=" + getMixPanelToken() + ", distinctId=" + getDistinctId() + ", timeInMilliSeconds=" + getTimeInMilliSeconds() + ", screenWidth=" + getScreenWidth() + ", screenHeight=" + getScreenHeight() + ", deviceName=" + getDeviceName() + ", osVersion=" + getOsVersion() + ", deviceManufacturer=" + getDeviceManufacturer() + ", deviceModel=" + getDeviceModel() + ", transactionId=" + getTransactionId() + ", abi=" + getAbi() + ")";
    }

    TrackerAnalyticsData(String str, String str2, String str3, String str4, String str5, String str6, long j, int i, int i2, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.appId = str;
        this.appName = str2;
        this.sdkVersionNumber = str3;
        this.token = str4;
        this.mixPanelToken = str5;
        this.distinctId = str6;
        this.timeInMilliSeconds = j;
        this.screenWidth = i;
        this.screenHeight = i2;
        this.deviceName = str7;
        this.osVersion = str8;
        this.deviceManufacturer = str9;
        this.deviceModel = str10;
        this.transactionId = str11;
        this.abi = str12;
    }

    public static TrackerAnalyticsDataBuilder builder() {
        return new TrackerAnalyticsDataBuilder();
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getSdkVersionNumber() {
        return this.sdkVersionNumber;
    }

    public String getToken() {
        return this.token;
    }

    public String getMixPanelToken() {
        return this.mixPanelToken;
    }

    public String getDistinctId() {
        return this.distinctId;
    }

    public long getTimeInMilliSeconds() {
        return this.timeInMilliSeconds;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public String getAbi() {
        return this.abi;
    }
}
