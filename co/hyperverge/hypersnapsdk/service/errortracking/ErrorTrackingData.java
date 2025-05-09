package co.hyperverge.hypersnapsdk.service.errortracking;

/* loaded from: classes2.dex */
public class ErrorTrackingData {
    private String abi;
    private String appId;
    private String hyperSnapSdkVersion;
    private String packageName;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getHyperSnapSdkVersion() {
        return this.hyperSnapSdkVersion;
    }

    public void setHyperSnapSdkVersion(String str) {
        this.hyperSnapSdkVersion = str;
    }

    public String getAbi() {
        return this.abi;
    }

    public void setAbi(String str) {
        this.abi = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }
}
