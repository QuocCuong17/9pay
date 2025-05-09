package co.hyperverge.hyperkyc.core.hv.models;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.webCore.ui.WebCoreVM;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HSRemoteConfig.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u00002\u00020\u0001:\u0001\u001cB%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\r\u0010\u0014\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0017HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "Ljava/io/Serializable;", "useBranding", "", "uploadLogs", "webCore", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs;", "(ZZLco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs;)V", "getUploadLogs", "()Z", "getUseBranding", "getWebCore", "()Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs;", "component1", "component2", "component3", "copy", "equals", "other", "", "getIsWebCoreEnabled", "getIsWebCoreEnabled$hyperkyc_release", "getWebCoreVersion", "", "getWebCoreVersion$hyperkyc_release", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "WebCoreConfigs", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class HSRemoteConfig implements Serializable {

    @SerializedName("uploadLogs")
    private final boolean uploadLogs;

    @SerializedName("use_branding")
    private final boolean useBranding;

    @SerializedName("webCore")
    private final WebCoreConfigs webCore;

    public HSRemoteConfig() {
        this(false, false, null, 7, null);
    }

    public static /* synthetic */ HSRemoteConfig copy$default(HSRemoteConfig hSRemoteConfig, boolean z, boolean z2, WebCoreConfigs webCoreConfigs, int i, Object obj) {
        if ((i & 1) != 0) {
            z = hSRemoteConfig.useBranding;
        }
        if ((i & 2) != 0) {
            z2 = hSRemoteConfig.uploadLogs;
        }
        if ((i & 4) != 0) {
            webCoreConfigs = hSRemoteConfig.webCore;
        }
        return hSRemoteConfig.copy(z, z2, webCoreConfigs);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getUseBranding() {
        return this.useBranding;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getUploadLogs() {
        return this.uploadLogs;
    }

    /* renamed from: component3, reason: from getter */
    public final WebCoreConfigs getWebCore() {
        return this.webCore;
    }

    public final HSRemoteConfig copy(boolean useBranding, boolean uploadLogs, WebCoreConfigs webCore) {
        return new HSRemoteConfig(useBranding, uploadLogs, webCore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HSRemoteConfig)) {
            return false;
        }
        HSRemoteConfig hSRemoteConfig = (HSRemoteConfig) other;
        return this.useBranding == hSRemoteConfig.useBranding && this.uploadLogs == hSRemoteConfig.uploadLogs && Intrinsics.areEqual(this.webCore, hSRemoteConfig.webCore);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.useBranding;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.uploadLogs;
        int i2 = (i + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        WebCoreConfigs webCoreConfigs = this.webCore;
        return i2 + (webCoreConfigs == null ? 0 : webCoreConfigs.hashCode());
    }

    public String toString() {
        return "HSRemoteConfig(useBranding=" + this.useBranding + ", uploadLogs=" + this.uploadLogs + ", webCore=" + this.webCore + ')';
    }

    public HSRemoteConfig(boolean z, boolean z2, WebCoreConfigs webCoreConfigs) {
        this.useBranding = z;
        this.uploadLogs = z2;
        this.webCore = webCoreConfigs;
    }

    public /* synthetic */ HSRemoteConfig(boolean z, boolean z2, WebCoreConfigs webCoreConfigs, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? null : webCoreConfigs);
    }

    public final boolean getUseBranding() {
        return this.useBranding;
    }

    public final boolean getUploadLogs() {
        return this.uploadLogs;
    }

    public final WebCoreConfigs getWebCore() {
        return this.webCore;
    }

    /* compiled from: HSRemoteConfig.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001:\u0001\u0019B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs;", "Ljava/io/Serializable;", "override", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs$Override;", "enable", "", "version", "", "(Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs$Override;ZLjava/lang/String;)V", "getEnable", "()Z", "getOverride", "()Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs$Override;", "getVersion", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Override", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class WebCoreConfigs implements Serializable {

        @SerializedName("enable")
        private final boolean enable;

        @SerializedName("override")
        private final Override override;

        @SerializedName("version")
        private final String version;

        public WebCoreConfigs() {
            this(null, false, null, 7, null);
        }

        public static /* synthetic */ WebCoreConfigs copy$default(WebCoreConfigs webCoreConfigs, Override override, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                override = webCoreConfigs.override;
            }
            if ((i & 2) != 0) {
                z = webCoreConfigs.enable;
            }
            if ((i & 4) != 0) {
                str = webCoreConfigs.version;
            }
            return webCoreConfigs.copy(override, z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final Override getOverride() {
            return this.override;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getEnable() {
            return this.enable;
        }

        /* renamed from: component3, reason: from getter */
        public final String getVersion() {
            return this.version;
        }

        public final WebCoreConfigs copy(Override override, boolean enable, String version) {
            return new WebCoreConfigs(override, enable, version);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WebCoreConfigs)) {
                return false;
            }
            WebCoreConfigs webCoreConfigs = (WebCoreConfigs) other;
            return Intrinsics.areEqual(this.override, webCoreConfigs.override) && this.enable == webCoreConfigs.enable && Intrinsics.areEqual(this.version, webCoreConfigs.version);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            Override override = this.override;
            int hashCode = (override == null ? 0 : override.hashCode()) * 31;
            boolean z = this.enable;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            String str = this.version;
            return i2 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "WebCoreConfigs(override=" + this.override + ", enable=" + this.enable + ", version=" + this.version + ')';
        }

        public WebCoreConfigs(Override override, boolean z, String str) {
            this.override = override;
            this.enable = z;
            this.version = str;
        }

        public /* synthetic */ WebCoreConfigs(Override override, boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : override, (i & 2) != 0 ? false : z, (i & 4) != 0 ? null : str);
        }

        public final Override getOverride() {
            return this.override;
        }

        public final boolean getEnable() {
            return this.enable;
        }

        public final String getVersion() {
            return this.version;
        }

        /* compiled from: HSRemoteConfig.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u001d\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0017\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J!\u0010\n\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R$\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs$Override;", "Ljava/io/Serializable;", "android", "", "", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs$Override$AndroidVersionSpecificConfig;", "(Ljava/util/Map;)V", "getAndroid", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "AndroidVersionSpecificConfig", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Override implements Serializable {

            @SerializedName("android")
            private final Map<String, AndroidVersionSpecificConfig> android;

            /* JADX WARN: Multi-variable type inference failed */
            public Override() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Override copy$default(Override override, Map map, int i, Object obj) {
                if ((i & 1) != 0) {
                    map = override.android;
                }
                return override.copy(map);
            }

            public final Map<String, AndroidVersionSpecificConfig> component1() {
                return this.android;
            }

            public final Override copy(Map<String, AndroidVersionSpecificConfig> android2) {
                return new Override(android2);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Override) && Intrinsics.areEqual(this.android, ((Override) other).android);
            }

            public int hashCode() {
                Map<String, AndroidVersionSpecificConfig> map = this.android;
                if (map == null) {
                    return 0;
                }
                return map.hashCode();
            }

            public String toString() {
                return "Override(android=" + this.android + ')';
            }

            public Override(Map<String, AndroidVersionSpecificConfig> map) {
                this.android = map;
            }

            public /* synthetic */ Override(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : map);
            }

            public final Map<String, AndroidVersionSpecificConfig> getAndroid() {
                return this.android;
            }

            /* compiled from: HSRemoteConfig.kt */
            @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig$WebCoreConfigs$Override$AndroidVersionSpecificConfig;", "", "enable", "", "version", "", "(ZLjava/lang/String;)V", "getEnable", "()Z", "getVersion", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final /* data */ class AndroidVersionSpecificConfig {

                @SerializedName("enable")
                private final boolean enable;

                @SerializedName("version")
                private final String version;

                /* JADX WARN: Multi-variable type inference failed */
                public AndroidVersionSpecificConfig() {
                    this(false, null, 3, 0 == true ? 1 : 0);
                }

                public static /* synthetic */ AndroidVersionSpecificConfig copy$default(AndroidVersionSpecificConfig androidVersionSpecificConfig, boolean z, String str, int i, Object obj) {
                    if ((i & 1) != 0) {
                        z = androidVersionSpecificConfig.enable;
                    }
                    if ((i & 2) != 0) {
                        str = androidVersionSpecificConfig.version;
                    }
                    return androidVersionSpecificConfig.copy(z, str);
                }

                /* renamed from: component1, reason: from getter */
                public final boolean getEnable() {
                    return this.enable;
                }

                /* renamed from: component2, reason: from getter */
                public final String getVersion() {
                    return this.version;
                }

                public final AndroidVersionSpecificConfig copy(boolean enable, String version) {
                    return new AndroidVersionSpecificConfig(enable, version);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof AndroidVersionSpecificConfig)) {
                        return false;
                    }
                    AndroidVersionSpecificConfig androidVersionSpecificConfig = (AndroidVersionSpecificConfig) other;
                    return this.enable == androidVersionSpecificConfig.enable && Intrinsics.areEqual(this.version, androidVersionSpecificConfig.version);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v1, types: [int] */
                /* JADX WARN: Type inference failed for: r0v4 */
                /* JADX WARN: Type inference failed for: r0v5 */
                public int hashCode() {
                    boolean z = this.enable;
                    ?? r0 = z;
                    if (z) {
                        r0 = 1;
                    }
                    int i = r0 * 31;
                    String str = this.version;
                    return i + (str == null ? 0 : str.hashCode());
                }

                public String toString() {
                    return "AndroidVersionSpecificConfig(enable=" + this.enable + ", version=" + this.version + ')';
                }

                public AndroidVersionSpecificConfig(boolean z, String str) {
                    this.enable = z;
                    this.version = str;
                }

                public /* synthetic */ AndroidVersionSpecificConfig(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str);
                }

                public final boolean getEnable() {
                    return this.enable;
                }

                public final String getVersion() {
                    return this.version;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean getIsWebCoreEnabled$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        WebCoreConfigs.Override override;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("getIsWebCoreEnabled() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "getIsWebCoreEnabled() called ");
                }
            }
        }
        WebCoreConfigs webCoreConfigs = this.webCore;
        boolean enable = webCoreConfigs != null ? webCoreConfigs.getEnable() : false;
        WebCoreConfigs webCoreConfigs2 = this.webCore;
        Map<String, WebCoreConfigs.Override.AndroidVersionSpecificConfig> android2 = (webCoreConfigs2 == null || (override = webCoreConfigs2.getOverride()) == null) ? null : override.getAndroid();
        WebCoreConfigs.Override.AndroidVersionSpecificConfig androidVersionSpecificConfig = android2 != null ? android2.get(BuildConfig.HYPERKYC_VERSION_NAME) : null;
        return enable && (androidVersionSpecificConfig != null ? androidVersionSpecificConfig.getEnable() : enable);
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0122, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getWebCoreVersion$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        Map<String, WebCoreConfigs.Override.AndroidVersionSpecificConfig> android2;
        WebCoreConfigs.Override.AndroidVersionSpecificConfig androidVersionSpecificConfig;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("getWebCoreVersion() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "getWebCoreVersion() called ");
                }
            }
        }
        WebCoreConfigs webCoreConfigs = this.webCore;
        String str3 = WebCoreVM.WEB_CORE_VERSION;
        if (webCoreConfigs == null) {
            return WebCoreVM.WEB_CORE_VERSION;
        }
        String version = webCoreConfigs.getVersion();
        if (version != null) {
            str3 = version;
        }
        WebCoreConfigs.Override override = webCoreConfigs.getOverride();
        if (override != null && (android2 = override.getAndroid()) != null && (androidVersionSpecificConfig = android2.get(BuildConfig.HYPERKYC_VERSION_NAME)) != null) {
            str2 = androidVersionSpecificConfig.getVersion();
        }
        return str2 == null ? str3 : str2;
    }
}
