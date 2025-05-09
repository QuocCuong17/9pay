package co.hyperverge.hyperkyc.data.models;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowConfig.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0081\b\u0018\u0000 32\u00020\u0001:\u0003345BÍ\u0001\u0012(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006\u0012(\b\u0002\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0006\u0012(\b\u0002\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000b¢\u0006\u0002\u0010\u0013J)\u0010#\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J)\u0010%\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0006HÆ\u0003J)\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0006HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\u000bHÆ\u0003J\t\u0010*\u001a\u00020\u000eHÆ\u0003J\t\u0010+\u001a\u00020\u000bHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0011HÆ\u0003JÑ\u0001\u0010-\u001a\u00020\u00002(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u00062(\b\u0002\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00062(\b\u0002\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000bHÆ\u0001J\u0013\u0010.\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u000100HÖ\u0003J\t\u00101\u001a\u00020\u000eHÖ\u0001J\t\u00102\u001a\u00020\u0004HÖ\u0001R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R6\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR6\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0016\u0010\u000f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R6\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0012\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015¨\u00066"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/Properties;", "Ljava/io/Serializable;", "sdkVersions", "Ljava/util/HashMap;", "", "Lco/hyperverge/hyperkyc/data/models/Properties$SDKVersion;", "Lkotlin/collections/HashMap;", "inputsRequired", "textConfigSource", "uiConfigSource", "enableResumeWorkflow", "", "enableServerSideResume", "resumeWorkflowDurationInHours", "", "secure", Properties.SDK_VERSION_MOBILE_KEY, "Lco/hyperverge/hyperkyc/data/models/Properties$Mobile;", "useWebForm", "(Ljava/util/HashMap;Ljava/util/HashMap;Ljava/util/HashMap;Ljava/lang/String;ZZIZLco/hyperverge/hyperkyc/data/models/Properties$Mobile;Z)V", "getEnableResumeWorkflow", "()Z", "getEnableServerSideResume", "getInputsRequired", "()Ljava/util/HashMap;", "getMobile", "()Lco/hyperverge/hyperkyc/data/models/Properties$Mobile;", "getResumeWorkflowDurationInHours", "()I", "getSdkVersions", "getSecure", "getTextConfigSource", "getUiConfigSource", "()Ljava/lang/String;", "getUseWebForm", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "Companion", "Mobile", "SDKVersion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Properties implements Serializable {
    public static final /* synthetic */ int DEFAULT_RESUME_WORKFLOW_DURATION_IN_HOURS = 48;
    public static final /* synthetic */ String INPUT_TYPE_BOOLEAN = "boolean";
    public static final /* synthetic */ String INPUT_TYPE_IMAGE = "image";
    public static final /* synthetic */ String INPUT_TYPE_NUMBER = "number";
    public static final /* synthetic */ String INPUT_TYPE_STRING = "string";
    public static final /* synthetic */ String SDK_VERSION_MOBILE_KEY = "mobile";

    @SerializedName("enableResumeWorkflow")
    private final boolean enableResumeWorkflow;

    @SerializedName("enableServerSideResume")
    private final boolean enableServerSideResume;

    @SerializedName("inputsRequired")
    private final HashMap<String, String> inputsRequired;

    @SerializedName(SDK_VERSION_MOBILE_KEY)
    private final Mobile mobile;

    @SerializedName("resumeWorkflowDuration")
    private final int resumeWorkflowDurationInHours;

    @SerializedName("sdkVersions")
    private final HashMap<String, SDKVersion> sdkVersions;

    @SerializedName("secure")
    private final boolean secure;

    @SerializedName("textConfigSource")
    private final HashMap<String, String> textConfigSource;

    @SerializedName("uiConfigSource")
    private final String uiConfigSource;

    @SerializedName("useWebForm")
    private final boolean useWebForm;

    public Properties() {
        this(null, null, null, null, false, false, 0, false, null, false, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    public final HashMap<String, SDKVersion> component1() {
        return this.sdkVersions;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getUseWebForm() {
        return this.useWebForm;
    }

    public final HashMap<String, String> component2() {
        return this.inputsRequired;
    }

    public final HashMap<String, String> component3() {
        return this.textConfigSource;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUiConfigSource() {
        return this.uiConfigSource;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getEnableResumeWorkflow() {
        return this.enableResumeWorkflow;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getEnableServerSideResume() {
        return this.enableServerSideResume;
    }

    /* renamed from: component7, reason: from getter */
    public final int getResumeWorkflowDurationInHours() {
        return this.resumeWorkflowDurationInHours;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getSecure() {
        return this.secure;
    }

    /* renamed from: component9, reason: from getter */
    public final Mobile getMobile() {
        return this.mobile;
    }

    public final Properties copy(HashMap<String, SDKVersion> sdkVersions, HashMap<String, String> inputsRequired, HashMap<String, String> textConfigSource, String uiConfigSource, boolean enableResumeWorkflow, boolean enableServerSideResume, int resumeWorkflowDurationInHours, boolean secure, Mobile mobile, boolean useWebForm) {
        return new Properties(sdkVersions, inputsRequired, textConfigSource, uiConfigSource, enableResumeWorkflow, enableServerSideResume, resumeWorkflowDurationInHours, secure, mobile, useWebForm);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Properties)) {
            return false;
        }
        Properties properties = (Properties) other;
        return Intrinsics.areEqual(this.sdkVersions, properties.sdkVersions) && Intrinsics.areEqual(this.inputsRequired, properties.inputsRequired) && Intrinsics.areEqual(this.textConfigSource, properties.textConfigSource) && Intrinsics.areEqual(this.uiConfigSource, properties.uiConfigSource) && this.enableResumeWorkflow == properties.enableResumeWorkflow && this.enableServerSideResume == properties.enableServerSideResume && this.resumeWorkflowDurationInHours == properties.resumeWorkflowDurationInHours && this.secure == properties.secure && Intrinsics.areEqual(this.mobile, properties.mobile) && this.useWebForm == properties.useWebForm;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        HashMap<String, SDKVersion> hashMap = this.sdkVersions;
        int hashCode = (hashMap == null ? 0 : hashMap.hashCode()) * 31;
        HashMap<String, String> hashMap2 = this.inputsRequired;
        int hashCode2 = (hashCode + (hashMap2 == null ? 0 : hashMap2.hashCode())) * 31;
        HashMap<String, String> hashMap3 = this.textConfigSource;
        int hashCode3 = (hashCode2 + (hashMap3 == null ? 0 : hashMap3.hashCode())) * 31;
        String str = this.uiConfigSource;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        boolean z = this.enableResumeWorkflow;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode4 + i) * 31;
        boolean z2 = this.enableServerSideResume;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (((i2 + i3) * 31) + this.resumeWorkflowDurationInHours) * 31;
        boolean z3 = this.secure;
        int i5 = z3;
        if (z3 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        Mobile mobile = this.mobile;
        int hashCode5 = (i6 + (mobile != null ? mobile.hashCode() : 0)) * 31;
        boolean z4 = this.useWebForm;
        return hashCode5 + (z4 ? 1 : z4 ? 1 : 0);
    }

    public String toString() {
        return "Properties(sdkVersions=" + this.sdkVersions + ", inputsRequired=" + this.inputsRequired + ", textConfigSource=" + this.textConfigSource + ", uiConfigSource=" + this.uiConfigSource + ", enableResumeWorkflow=" + this.enableResumeWorkflow + ", enableServerSideResume=" + this.enableServerSideResume + ", resumeWorkflowDurationInHours=" + this.resumeWorkflowDurationInHours + ", secure=" + this.secure + ", mobile=" + this.mobile + ", useWebForm=" + this.useWebForm + ')';
    }

    public Properties(HashMap<String, SDKVersion> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, String str, boolean z, boolean z2, int i, boolean z3, Mobile mobile, boolean z4) {
        this.sdkVersions = hashMap;
        this.inputsRequired = hashMap2;
        this.textConfigSource = hashMap3;
        this.uiConfigSource = str;
        this.enableResumeWorkflow = z;
        this.enableServerSideResume = z2;
        this.resumeWorkflowDurationInHours = i;
        this.secure = z3;
        this.mobile = mobile;
        this.useWebForm = z4;
    }

    public /* synthetic */ Properties(HashMap hashMap, HashMap hashMap2, HashMap hashMap3, String str, boolean z, boolean z2, int i, boolean z3, Mobile mobile, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : hashMap, (i2 & 2) != 0 ? null : hashMap2, (i2 & 4) != 0 ? null : hashMap3, (i2 & 8) != 0 ? null : str, (i2 & 16) != 0 ? true : z, (i2 & 32) != 0 ? false : z2, (i2 & 64) != 0 ? 48 : i, (i2 & 128) != 0 ? false : z3, (i2 & 256) == 0 ? mobile : null, (i2 & 512) == 0 ? z4 : false);
    }

    public final HashMap<String, SDKVersion> getSdkVersions() {
        return this.sdkVersions;
    }

    public final HashMap<String, String> getInputsRequired() {
        return this.inputsRequired;
    }

    public final HashMap<String, String> getTextConfigSource() {
        return this.textConfigSource;
    }

    public final String getUiConfigSource() {
        return this.uiConfigSource;
    }

    public final boolean getEnableResumeWorkflow() {
        return this.enableResumeWorkflow;
    }

    public final boolean getEnableServerSideResume() {
        return this.enableServerSideResume;
    }

    public final int getResumeWorkflowDurationInHours() {
        return this.resumeWorkflowDurationInHours;
    }

    public final boolean getSecure() {
        return this.secure;
    }

    public final Mobile getMobile() {
        return this.mobile;
    }

    public final boolean getUseWebForm() {
        return this.useWebForm;
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/Properties$SDKVersion;", "Ljava/io/Serializable;", "minimum", "", "maximum", "(Ljava/lang/String;Ljava/lang/String;)V", "getMaximum", "()Ljava/lang/String;", "getMinimum", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class SDKVersion implements Serializable {

        @SerializedName("maximum")
        private final String maximum;

        @SerializedName("minimum")
        private final String minimum;

        public static /* synthetic */ SDKVersion copy$default(SDKVersion sDKVersion, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sDKVersion.minimum;
            }
            if ((i & 2) != 0) {
                str2 = sDKVersion.maximum;
            }
            return sDKVersion.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMinimum() {
            return this.minimum;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMaximum() {
            return this.maximum;
        }

        public final SDKVersion copy(String minimum, String maximum) {
            Intrinsics.checkNotNullParameter(minimum, "minimum");
            Intrinsics.checkNotNullParameter(maximum, "maximum");
            return new SDKVersion(minimum, maximum);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SDKVersion)) {
                return false;
            }
            SDKVersion sDKVersion = (SDKVersion) other;
            return Intrinsics.areEqual(this.minimum, sDKVersion.minimum) && Intrinsics.areEqual(this.maximum, sDKVersion.maximum);
        }

        public int hashCode() {
            return (this.minimum.hashCode() * 31) + this.maximum.hashCode();
        }

        public String toString() {
            return "SDKVersion(minimum=" + this.minimum + ", maximum=" + this.maximum + ')';
        }

        public SDKVersion(String minimum, String maximum) {
            Intrinsics.checkNotNullParameter(minimum, "minimum");
            Intrinsics.checkNotNullParameter(maximum, "maximum");
            this.minimum = minimum;
            this.maximum = maximum;
        }

        public final String getMinimum() {
            return this.minimum;
        }

        public final String getMaximum() {
            return this.maximum;
        }
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/Properties$Mobile;", "Ljava/io/Serializable;", "exitIfRooted", "", "(Z)V", "getExitIfRooted", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Mobile implements Serializable {

        @SerializedName("exitIfRooted")
        private final boolean exitIfRooted;

        public Mobile() {
            this(false, 1, null);
        }

        public static /* synthetic */ Mobile copy$default(Mobile mobile, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = mobile.exitIfRooted;
            }
            return mobile.copy(z);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getExitIfRooted() {
            return this.exitIfRooted;
        }

        public final Mobile copy(boolean exitIfRooted) {
            return new Mobile(exitIfRooted);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Mobile) && this.exitIfRooted == ((Mobile) other).exitIfRooted;
        }

        public int hashCode() {
            boolean z = this.exitIfRooted;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "Mobile(exitIfRooted=" + this.exitIfRooted + ')';
        }

        public Mobile(boolean z) {
            this.exitIfRooted = z;
        }

        public /* synthetic */ Mobile(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z);
        }

        public final boolean getExitIfRooted() {
            return this.exitIfRooted;
        }
    }
}
