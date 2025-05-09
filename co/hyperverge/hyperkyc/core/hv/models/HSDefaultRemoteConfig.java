package co.hyperverge.hyperkyc.core.hv.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HSDefaultRemoteConfig.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig;", "Ljava/io/Serializable;", "mobileSdkConfig", "Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig;", "(Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig;)V", "getMobileSdkConfig", "()Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "MobileSdkConfig", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class HSDefaultRemoteConfig implements Serializable {

    @SerializedName("mobilesdk")
    private final MobileSdkConfig mobileSdkConfig;

    /* JADX WARN: Multi-variable type inference failed */
    public HSDefaultRemoteConfig() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ HSDefaultRemoteConfig copy$default(HSDefaultRemoteConfig hSDefaultRemoteConfig, MobileSdkConfig mobileSdkConfig, int i, Object obj) {
        if ((i & 1) != 0) {
            mobileSdkConfig = hSDefaultRemoteConfig.mobileSdkConfig;
        }
        return hSDefaultRemoteConfig.copy(mobileSdkConfig);
    }

    /* renamed from: component1, reason: from getter */
    public final MobileSdkConfig getMobileSdkConfig() {
        return this.mobileSdkConfig;
    }

    public final HSDefaultRemoteConfig copy(MobileSdkConfig mobileSdkConfig) {
        return new HSDefaultRemoteConfig(mobileSdkConfig);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HSDefaultRemoteConfig) && Intrinsics.areEqual(this.mobileSdkConfig, ((HSDefaultRemoteConfig) other).mobileSdkConfig);
    }

    public int hashCode() {
        MobileSdkConfig mobileSdkConfig = this.mobileSdkConfig;
        if (mobileSdkConfig == null) {
            return 0;
        }
        return mobileSdkConfig.hashCode();
    }

    public String toString() {
        return "HSDefaultRemoteConfig(mobileSdkConfig=" + this.mobileSdkConfig + ')';
    }

    public HSDefaultRemoteConfig(MobileSdkConfig mobileSdkConfig) {
        this.mobileSdkConfig = mobileSdkConfig;
    }

    public /* synthetic */ HSDefaultRemoteConfig(MobileSdkConfig mobileSdkConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : mobileSdkConfig);
    }

    public final MobileSdkConfig getMobileSdkConfig() {
        return this.mobileSdkConfig;
    }

    /* compiled from: HSDefaultRemoteConfig.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig;", "Ljava/io/Serializable;", "webSDKVersion", "Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig$WebSDKVersion;", "(Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig$WebSDKVersion;)V", "getWebSDKVersion", "()Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig$WebSDKVersion;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "WebSDKVersion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class MobileSdkConfig implements Serializable {

        @SerializedName("websdkVersion")
        private final WebSDKVersion webSDKVersion;

        /* JADX WARN: Multi-variable type inference failed */
        public MobileSdkConfig() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ MobileSdkConfig copy$default(MobileSdkConfig mobileSdkConfig, WebSDKVersion webSDKVersion, int i, Object obj) {
            if ((i & 1) != 0) {
                webSDKVersion = mobileSdkConfig.webSDKVersion;
            }
            return mobileSdkConfig.copy(webSDKVersion);
        }

        /* renamed from: component1, reason: from getter */
        public final WebSDKVersion getWebSDKVersion() {
            return this.webSDKVersion;
        }

        public final MobileSdkConfig copy(WebSDKVersion webSDKVersion) {
            return new MobileSdkConfig(webSDKVersion);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MobileSdkConfig) && Intrinsics.areEqual(this.webSDKVersion, ((MobileSdkConfig) other).webSDKVersion);
        }

        public int hashCode() {
            WebSDKVersion webSDKVersion = this.webSDKVersion;
            if (webSDKVersion == null) {
                return 0;
            }
            return webSDKVersion.hashCode();
        }

        public String toString() {
            return "MobileSdkConfig(webSDKVersion=" + this.webSDKVersion + ')';
        }

        public MobileSdkConfig(WebSDKVersion webSDKVersion) {
            this.webSDKVersion = webSDKVersion;
        }

        public /* synthetic */ MobileSdkConfig(WebSDKVersion webSDKVersion, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : webSDKVersion);
        }

        public final WebSDKVersion getWebSDKVersion() {
            return this.webSDKVersion;
        }

        /* compiled from: HSDefaultRemoteConfig.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\t\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001R$\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig$MobileSdkConfig$WebSDKVersion;", "", "androidVersionMap", "", "", "(Ljava/util/Map;)V", "getAndroidVersionMap", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class WebSDKVersion {

            @SerializedName("android")
            private final Map<String, String> androidVersionMap;

            /* JADX WARN: Multi-variable type inference failed */
            public WebSDKVersion() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ WebSDKVersion copy$default(WebSDKVersion webSDKVersion, Map map, int i, Object obj) {
                if ((i & 1) != 0) {
                    map = webSDKVersion.androidVersionMap;
                }
                return webSDKVersion.copy(map);
            }

            public final Map<String, String> component1() {
                return this.androidVersionMap;
            }

            public final WebSDKVersion copy(Map<String, String> androidVersionMap) {
                return new WebSDKVersion(androidVersionMap);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WebSDKVersion) && Intrinsics.areEqual(this.androidVersionMap, ((WebSDKVersion) other).androidVersionMap);
            }

            public int hashCode() {
                Map<String, String> map = this.androidVersionMap;
                if (map == null) {
                    return 0;
                }
                return map.hashCode();
            }

            public String toString() {
                return "WebSDKVersion(androidVersionMap=" + this.androidVersionMap + ')';
            }

            public WebSDKVersion(Map<String, String> map) {
                this.androidVersionMap = map;
            }

            public /* synthetic */ WebSDKVersion(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : map);
            }

            public final Map<String, String> getAndroidVersionMap() {
                return this.androidVersionMap;
            }
        }
    }
}
