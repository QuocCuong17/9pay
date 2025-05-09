package co.hyperverge.hypersnapsdk.objects;

import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;

/* loaded from: classes2.dex */
public class HyperSnapSDKConfig {
    private String accessToken;
    private String appId;
    private String appKey;
    private int connectTimeOut;
    private ExternalConfigs externalConfigs;
    private HyperSnapParams.Product hyperSnapProduct;
    private HyperSnapParams.Region hyperSnapRegion;
    private String mixpanelToken;
    private int readTimeOut;

    @Deprecated
    private boolean shouldActivateDeviceBlocklist;
    private boolean shouldEnableSSLPinning;
    private boolean shouldLogOnlyErrors;
    private boolean shouldReturnRawResponse;
    private boolean shouldUseLocation;
    private boolean shouldUseRemoteConfig;
    private boolean shouldUseSHA256Signature;
    private boolean shouldUseSensorBiometrics;
    private boolean shouldUseSignature;
    private String signatureMethod;
    private UIConfig uiConfig;
    private int writeTimeOut;

    protected boolean canEqual(Object obj) {
        return obj instanceof HyperSnapSDKConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HyperSnapSDKConfig)) {
            return false;
        }
        HyperSnapSDKConfig hyperSnapSDKConfig = (HyperSnapSDKConfig) obj;
        if (!hyperSnapSDKConfig.canEqual(this) || isShouldEnableSSLPinning() != hyperSnapSDKConfig.isShouldEnableSSLPinning() || isShouldUseSignature() != hyperSnapSDKConfig.isShouldUseSignature() || isShouldLogOnlyErrors() != hyperSnapSDKConfig.isShouldLogOnlyErrors() || isShouldReturnRawResponse() != hyperSnapSDKConfig.isShouldReturnRawResponse() || isShouldActivateDeviceBlocklist() != hyperSnapSDKConfig.isShouldActivateDeviceBlocklist() || isShouldUseSHA256Signature() != hyperSnapSDKConfig.isShouldUseSHA256Signature() || getConnectTimeOut() != hyperSnapSDKConfig.getConnectTimeOut() || getReadTimeOut() != hyperSnapSDKConfig.getReadTimeOut() || getWriteTimeOut() != hyperSnapSDKConfig.getWriteTimeOut() || isShouldUseSensorBiometrics() != hyperSnapSDKConfig.isShouldUseSensorBiometrics() || isShouldUseRemoteConfig() != hyperSnapSDKConfig.isShouldUseRemoteConfig() || isShouldUseLocation() != hyperSnapSDKConfig.isShouldUseLocation()) {
            return false;
        }
        String appId = getAppId();
        String appId2 = hyperSnapSDKConfig.getAppId();
        if (appId != null ? !appId.equals(appId2) : appId2 != null) {
            return false;
        }
        String appKey = getAppKey();
        String appKey2 = hyperSnapSDKConfig.getAppKey();
        if (appKey != null ? !appKey.equals(appKey2) : appKey2 != null) {
            return false;
        }
        String accessToken = getAccessToken();
        String accessToken2 = hyperSnapSDKConfig.getAccessToken();
        if (accessToken != null ? !accessToken.equals(accessToken2) : accessToken2 != null) {
            return false;
        }
        HyperSnapParams.Region hyperSnapRegion = getHyperSnapRegion();
        HyperSnapParams.Region hyperSnapRegion2 = hyperSnapSDKConfig.getHyperSnapRegion();
        if (hyperSnapRegion != null ? !hyperSnapRegion.equals(hyperSnapRegion2) : hyperSnapRegion2 != null) {
            return false;
        }
        HyperSnapParams.Product hyperSnapProduct = getHyperSnapProduct();
        HyperSnapParams.Product hyperSnapProduct2 = hyperSnapSDKConfig.getHyperSnapProduct();
        if (hyperSnapProduct != null ? !hyperSnapProduct.equals(hyperSnapProduct2) : hyperSnapProduct2 != null) {
            return false;
        }
        String signatureMethod = getSignatureMethod();
        String signatureMethod2 = hyperSnapSDKConfig.getSignatureMethod();
        if (signatureMethod != null ? !signatureMethod.equals(signatureMethod2) : signatureMethod2 != null) {
            return false;
        }
        String mixpanelToken = getMixpanelToken();
        String mixpanelToken2 = hyperSnapSDKConfig.getMixpanelToken();
        if (mixpanelToken != null ? !mixpanelToken.equals(mixpanelToken2) : mixpanelToken2 != null) {
            return false;
        }
        ExternalConfigs externalConfigs = getExternalConfigs();
        ExternalConfigs externalConfigs2 = hyperSnapSDKConfig.getExternalConfigs();
        if (externalConfigs != null ? !externalConfigs.equals(externalConfigs2) : externalConfigs2 != null) {
            return false;
        }
        UIConfig uiConfig = getUiConfig();
        UIConfig uiConfig2 = hyperSnapSDKConfig.getUiConfig();
        return uiConfig != null ? uiConfig.equals(uiConfig2) : uiConfig2 == null;
    }

    public int hashCode() {
        int connectTimeOut = (((((((((((((((((((((((isShouldEnableSSLPinning() ? 79 : 97) + 59) * 59) + (isShouldUseSignature() ? 79 : 97)) * 59) + (isShouldLogOnlyErrors() ? 79 : 97)) * 59) + (isShouldReturnRawResponse() ? 79 : 97)) * 59) + (isShouldActivateDeviceBlocklist() ? 79 : 97)) * 59) + (isShouldUseSHA256Signature() ? 79 : 97)) * 59) + getConnectTimeOut()) * 59) + getReadTimeOut()) * 59) + getWriteTimeOut()) * 59) + (isShouldUseSensorBiometrics() ? 79 : 97)) * 59) + (isShouldUseRemoteConfig() ? 79 : 97)) * 59) + (isShouldUseLocation() ? 79 : 97);
        String appId = getAppId();
        int hashCode = (connectTimeOut * 59) + (appId == null ? 43 : appId.hashCode());
        String appKey = getAppKey();
        int hashCode2 = (hashCode * 59) + (appKey == null ? 43 : appKey.hashCode());
        String accessToken = getAccessToken();
        int hashCode3 = (hashCode2 * 59) + (accessToken == null ? 43 : accessToken.hashCode());
        HyperSnapParams.Region hyperSnapRegion = getHyperSnapRegion();
        int hashCode4 = (hashCode3 * 59) + (hyperSnapRegion == null ? 43 : hyperSnapRegion.hashCode());
        HyperSnapParams.Product hyperSnapProduct = getHyperSnapProduct();
        int hashCode5 = (hashCode4 * 59) + (hyperSnapProduct == null ? 43 : hyperSnapProduct.hashCode());
        String signatureMethod = getSignatureMethod();
        int hashCode6 = (hashCode5 * 59) + (signatureMethod == null ? 43 : signatureMethod.hashCode());
        String mixpanelToken = getMixpanelToken();
        int hashCode7 = (hashCode6 * 59) + (mixpanelToken == null ? 43 : mixpanelToken.hashCode());
        ExternalConfigs externalConfigs = getExternalConfigs();
        int hashCode8 = (hashCode7 * 59) + (externalConfigs == null ? 43 : externalConfigs.hashCode());
        UIConfig uiConfig = getUiConfig();
        return (hashCode8 * 59) + (uiConfig != null ? uiConfig.hashCode() : 43);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setConnectTimeOut(int i) {
        this.connectTimeOut = i;
    }

    public void setExternalConfigs(ExternalConfigs externalConfigs) {
        this.externalConfigs = externalConfigs;
    }

    public void setHyperSnapProduct(HyperSnapParams.Product product) {
        this.hyperSnapProduct = product;
    }

    public void setHyperSnapRegion(HyperSnapParams.Region region) {
        this.hyperSnapRegion = region;
    }

    public void setMixpanelToken(String str) {
        this.mixpanelToken = str;
    }

    public void setReadTimeOut(int i) {
        this.readTimeOut = i;
    }

    @Deprecated
    public void setShouldActivateDeviceBlocklist(boolean z) {
        this.shouldActivateDeviceBlocklist = z;
    }

    public void setShouldEnableSSLPinning(boolean z) {
        this.shouldEnableSSLPinning = z;
    }

    public void setShouldLogOnlyErrors(boolean z) {
        this.shouldLogOnlyErrors = z;
    }

    public void setShouldReturnRawResponse(boolean z) {
        this.shouldReturnRawResponse = z;
    }

    public void setShouldUseLocation(boolean z) {
        this.shouldUseLocation = z;
    }

    public void setShouldUseRemoteConfig(boolean z) {
        this.shouldUseRemoteConfig = z;
    }

    public void setShouldUseSHA256Signature(boolean z) {
        this.shouldUseSHA256Signature = z;
    }

    public void setShouldUseSensorBiometrics(boolean z) {
        this.shouldUseSensorBiometrics = z;
    }

    public void setShouldUseSignature(boolean z) {
        this.shouldUseSignature = z;
    }

    public void setSignatureMethod(String str) {
        this.signatureMethod = str;
    }

    public void setUiConfig(UIConfig uIConfig) {
        this.uiConfig = uIConfig;
    }

    public void setWriteTimeOut(int i) {
        this.writeTimeOut = i;
    }

    public String toString() {
        return "HyperSnapSDKConfig(appId=" + getAppId() + ", appKey=" + getAppKey() + ", accessToken=" + getAccessToken() + ", hyperSnapRegion=" + getHyperSnapRegion() + ", hyperSnapProduct=" + getHyperSnapProduct() + ", shouldEnableSSLPinning=" + isShouldEnableSSLPinning() + ", shouldUseSignature=" + isShouldUseSignature() + ", shouldLogOnlyErrors=" + isShouldLogOnlyErrors() + ", signatureMethod=" + getSignatureMethod() + ", mixpanelToken=" + getMixpanelToken() + ", shouldReturnRawResponse=" + isShouldReturnRawResponse() + ", shouldActivateDeviceBlocklist=" + isShouldActivateDeviceBlocklist() + ", shouldUseSHA256Signature=" + isShouldUseSHA256Signature() + ", connectTimeOut=" + getConnectTimeOut() + ", readTimeOut=" + getReadTimeOut() + ", writeTimeOut=" + getWriteTimeOut() + ", externalConfigs=" + getExternalConfigs() + ", shouldUseSensorBiometrics=" + isShouldUseSensorBiometrics() + ", shouldUseRemoteConfig=" + isShouldUseRemoteConfig() + ", shouldUseLocation=" + isShouldUseLocation() + ", uiConfig=" + getUiConfig() + ")";
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public HyperSnapParams.Region getHyperSnapRegion() {
        return this.hyperSnapRegion;
    }

    public HyperSnapParams.Product getHyperSnapProduct() {
        return this.hyperSnapProduct;
    }

    public boolean isShouldEnableSSLPinning() {
        return this.shouldEnableSSLPinning;
    }

    public boolean isShouldUseSignature() {
        return this.shouldUseSignature;
    }

    public boolean isShouldLogOnlyErrors() {
        return this.shouldLogOnlyErrors;
    }

    public String getSignatureMethod() {
        return this.signatureMethod;
    }

    public String getMixpanelToken() {
        return this.mixpanelToken;
    }

    public boolean isShouldReturnRawResponse() {
        return this.shouldReturnRawResponse;
    }

    @Deprecated
    public boolean isShouldActivateDeviceBlocklist() {
        return this.shouldActivateDeviceBlocklist;
    }

    public boolean isShouldUseSHA256Signature() {
        return this.shouldUseSHA256Signature;
    }

    public int getConnectTimeOut() {
        return this.connectTimeOut;
    }

    public int getReadTimeOut() {
        return this.readTimeOut;
    }

    public int getWriteTimeOut() {
        return this.writeTimeOut;
    }

    public ExternalConfigs getExternalConfigs() {
        return this.externalConfigs;
    }

    public boolean isShouldUseSensorBiometrics() {
        return this.shouldUseSensorBiometrics;
    }

    public boolean isShouldUseRemoteConfig() {
        return this.shouldUseRemoteConfig;
    }

    public boolean isShouldUseLocation() {
        return this.shouldUseLocation;
    }

    public UIConfig getUiConfig() {
        return this.uiConfig;
    }

    public HyperSnapSDKConfig() {
        this.signatureMethod = "MD5";
        this.shouldActivateDeviceBlocklist = true;
        this.shouldUseSHA256Signature = false;
        this.connectTimeOut = 120;
        this.readTimeOut = 120;
        this.writeTimeOut = 120;
        this.shouldUseSensorBiometrics = true;
        this.shouldUseRemoteConfig = true;
    }

    public HyperSnapSDKConfig(String str, String str2, HyperSnapParams.Region region) {
        this.signatureMethod = "MD5";
        this.shouldActivateDeviceBlocklist = true;
        this.shouldUseSHA256Signature = false;
        this.connectTimeOut = 120;
        this.readTimeOut = 120;
        this.writeTimeOut = 120;
        this.shouldUseSensorBiometrics = true;
        this.shouldUseRemoteConfig = true;
        this.appId = str;
        this.appKey = str2;
        this.hyperSnapRegion = region;
        this.hyperSnapProduct = HyperSnapParams.Product.FACEID;
    }

    public void setHttpTimeoutValues(int i, int i2, int i3) {
        this.connectTimeOut = i;
        this.readTimeOut = i2;
        this.writeTimeOut = i3;
    }
}
