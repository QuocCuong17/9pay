package co.hyperverge.hyperkyc.data.models;

import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.data.models.Properties;
import co.hyperverge.hyperkyc.utils.extensions.AccessTokenPart;
import co.hyperverge.hyperkyc.utils.extensions.NetworkExtsKt;
import io.sentry.protocol.SentryStackFrame;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HyperKycConfig.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\f\u0018\u0000 I2\u00020\u0001:\u0001IB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\tB\u0007\b\u0002¢\u0006\u0002\u0010\nJ\u001a\u0010;\u001a\u00020<2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030>J\r\u0010?\u001a\u00020\u001fH\u0000¢\u0006\u0002\b@J\r\u0010A\u001a\u00020\u001fH\u0000¢\u0006\u0002\bBJ\u000e\u0010C\u001a\u00020<2\u0006\u0010\u0014\u001a\u00020\u0003J\u001a\u0010D\u001a\u00020<2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00180>J\u000e\u0010E\u001a\u00020<2\u0006\u0010F\u001a\u00020\u0003J\u000e\u0010G\u001a\u00020<2\u0006\u0010-\u001a\u00020\u001fJ\b\u0010H\u001a\u00020<H\u0002R*\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR*\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR*\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR^\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00180\u0017j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0018`\u00192\"\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00180\u0017j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0018`\u00198@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR&\u0010 \u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u001f8@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R8\u0010%\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u00198@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR&\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00038@@@X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\r\"\u0004\b)\u0010\u000fR*\u0010*\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\r\"\u0004\b,\u0010\u000fR&\u0010-\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u001f8@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\"\"\u0004\b/\u0010$R&\u00101\u001a\u0002002\u0006\u0010\u000b\u001a\u0002008@@@X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R*\u00106\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\r\"\u0004\b8\u0010\u000fR&\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00038@@@X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000f¨\u0006J"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "Ljava/io/Serializable;", "appId", "", HyperKycConfig.APP_KEY, "workflowId", "transactionId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "accessToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()V", "<set-?>", "getAccessToken$hyperkyc_release", "()Ljava/lang/String;", "setAccessToken$hyperkyc_release", "(Ljava/lang/String;)V", "getAppId$hyperkyc_release", "setAppId$hyperkyc_release", "getAppKey$hyperkyc_release", "setAppKey$hyperkyc_release", HyperKycConfig.DEFAULT_LANG_CODE, "getDefaultLangCode$hyperkyc_release", "setDefaultLangCode$hyperkyc_release", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", WorkflowModule.PREFIX_INPUTS, "getInputs$hyperkyc_release", "()Ljava/util/HashMap;", "setInputs$hyperkyc_release", "(Ljava/util/HashMap;)V", "", "isAppIdAppKeyInit", "isAppIdAppKeyInit$hyperkyc_release", "()Z", "setAppIdAppKeyInit$hyperkyc_release", "(Z)V", "metadataMap", "getMetadataMap$hyperkyc_release", "setMetadataMap$hyperkyc_release", "getTransactionId$hyperkyc_release", "setTransactionId$hyperkyc_release", "uniqueId", "getUniqueId$hyperkyc_release", "setUniqueId$hyperkyc_release", "useLocation", "getUseLocation$hyperkyc_release", "setUseLocation$hyperkyc_release", "Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;", "workflowConfig", "getWorkflowConfig$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;", "setWorkflowConfig$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;)V", "workflowConfigJson", "getWorkflowConfigJson$hyperkyc_release", "setWorkflowConfigJson$hyperkyc_release", "getWorkflowId$hyperkyc_release", "setWorkflowId$hyperkyc_release", "addMetadata", "", TtmlNode.TAG_METADATA, "", "exitIfRooted", "exitIfRooted$hyperkyc_release", "isServerSideResumeEnabled", "isServerSideResumeEnabled$hyperkyc_release", "setDefaultLangCode", "setInputs", "setUniqueId", "uuid", "setUseLocation", "updateDefaultMetadata", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperKycConfig implements Serializable {
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String APP_ID = "appId";
    public static final String APP_KEY = "appKey";
    public static final /* synthetic */ String ARG_CACHE_FILE_PATH_KEY = "hyperKycConfig_cacheFilePath";
    public static final String ARG_KEY = "hyperKycConfig";
    public static final String ARG_REMOTE_CONFIG = "remoteConfig";
    public static final String AUTHORIZATION = "Authorization";
    public static final String DEFAULT_LANG_CODE = "defaultLangCode";
    public static final String JOURNEY_ID = "journeyId";
    public static final String TRANSACTION_ID = "transactionId";
    public static final String WORKFLOW_ID = "workflowId";
    private String accessToken;
    private String appId;
    private String appKey;
    private String defaultLangCode;
    private HashMap<String, Object> inputs;
    private boolean isAppIdAppKeyInit;
    private HashMap<String, String> metadataMap;
    public String transactionId;
    private String uniqueId;
    private boolean useLocation;
    public WorkflowConfig workflowConfig;
    private String workflowConfigJson;
    public String workflowId;

    private HyperKycConfig() {
        this.inputs = new HashMap<>();
        this.isAppIdAppKeyInit = true;
        this.metadataMap = new HashMap<>();
    }

    /* renamed from: getAppId$hyperkyc_release, reason: from getter */
    public final /* synthetic */ String getAppId() {
        return this.appId;
    }

    public final /* synthetic */ void setAppId$hyperkyc_release(String str) {
        this.appId = str;
    }

    /* renamed from: getAppKey$hyperkyc_release, reason: from getter */
    public final /* synthetic */ String getAppKey() {
        return this.appKey;
    }

    public final /* synthetic */ void setAppKey$hyperkyc_release(String str) {
        this.appKey = str;
    }

    /* renamed from: getAccessToken$hyperkyc_release, reason: from getter */
    public final /* synthetic */ String getAccessToken() {
        return this.accessToken;
    }

    public final /* synthetic */ void setAccessToken$hyperkyc_release(String str) {
        this.accessToken = str;
    }

    public final /* synthetic */ String getWorkflowId$hyperkyc_release() {
        String str = this.workflowId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workflowId");
        return null;
    }

    public final /* synthetic */ void setWorkflowId$hyperkyc_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.workflowId = str;
    }

    public final /* synthetic */ String getTransactionId$hyperkyc_release() {
        String str = this.transactionId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("transactionId");
        return null;
    }

    public final /* synthetic */ void setTransactionId$hyperkyc_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.transactionId = str;
    }

    /* renamed from: getUniqueId$hyperkyc_release, reason: from getter */
    public final /* synthetic */ String getUniqueId() {
        return this.uniqueId;
    }

    public final /* synthetic */ void setUniqueId$hyperkyc_release(String str) {
        this.uniqueId = str;
    }

    /* renamed from: getDefaultLangCode$hyperkyc_release, reason: from getter */
    public final /* synthetic */ String getDefaultLangCode() {
        return this.defaultLangCode;
    }

    public final /* synthetic */ void setDefaultLangCode$hyperkyc_release(String str) {
        this.defaultLangCode = str;
    }

    /* renamed from: getInputs$hyperkyc_release, reason: from getter */
    public final /* synthetic */ HashMap getInputs() {
        return this.inputs;
    }

    public final /* synthetic */ void setInputs$hyperkyc_release(HashMap hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.inputs = hashMap;
    }

    /* renamed from: getUseLocation$hyperkyc_release, reason: from getter */
    public final /* synthetic */ boolean getUseLocation() {
        return this.useLocation;
    }

    public final /* synthetic */ void setUseLocation$hyperkyc_release(boolean z) {
        this.useLocation = z;
    }

    public final /* synthetic */ WorkflowConfig getWorkflowConfig$hyperkyc_release() {
        WorkflowConfig workflowConfig = this.workflowConfig;
        if (workflowConfig != null) {
            return workflowConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workflowConfig");
        return null;
    }

    public final /* synthetic */ void setWorkflowConfig$hyperkyc_release(WorkflowConfig workflowConfig) {
        Intrinsics.checkNotNullParameter(workflowConfig, "<set-?>");
        this.workflowConfig = workflowConfig;
    }

    /* renamed from: getWorkflowConfigJson$hyperkyc_release, reason: from getter */
    public final /* synthetic */ String getWorkflowConfigJson() {
        return this.workflowConfigJson;
    }

    public final /* synthetic */ void setWorkflowConfigJson$hyperkyc_release(String str) {
        this.workflowConfigJson = str;
    }

    /* renamed from: isAppIdAppKeyInit$hyperkyc_release, reason: from getter */
    public final /* synthetic */ boolean getIsAppIdAppKeyInit() {
        return this.isAppIdAppKeyInit;
    }

    public final /* synthetic */ void setAppIdAppKeyInit$hyperkyc_release(boolean z) {
        this.isAppIdAppKeyInit = z;
    }

    /* renamed from: getMetadataMap$hyperkyc_release, reason: from getter */
    public final /* synthetic */ HashMap getMetadataMap() {
        return this.metadataMap;
    }

    public final void setMetadataMap$hyperkyc_release(HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.metadataMap = hashMap;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HyperKycConfig(String appId, String appKey, String workflowId, String transactionId) {
        this();
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        this.appId = appId;
        this.appKey = appKey;
        setWorkflowId$hyperkyc_release(workflowId);
        setTransactionId$hyperkyc_release(transactionId);
        this.isAppIdAppKeyInit = true;
        updateDefaultMetadata();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HyperKycConfig(String accessToken, String workflowId, String transactionId) {
        this();
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        this.accessToken = accessToken;
        this.appId = (String) NetworkExtsKt.extractFromAccessToken(accessToken, AccessTokenPart.AppId.INSTANCE);
        setWorkflowId$hyperkyc_release(workflowId);
        setTransactionId$hyperkyc_release(transactionId);
        this.isAppIdAppKeyInit = false;
        updateDefaultMetadata();
    }

    public final void setUniqueId(String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.uniqueId = uuid;
    }

    public final void setDefaultLangCode(String defaultLangCode) {
        Intrinsics.checkNotNullParameter(defaultLangCode, "defaultLangCode");
        this.defaultLangCode = defaultLangCode;
    }

    public final void setInputs(Map<String, ? extends Object> inputs) {
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        this.inputs = new HashMap<>(MapsKt.toMutableMap(inputs));
    }

    public final void setUseLocation(boolean useLocation) {
        this.useLocation = useLocation;
    }

    public final void addMetadata(Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.metadataMap = new HashMap<>(metadata);
        updateDefaultMetadata();
    }

    private final void updateDefaultMetadata() {
        if (!this.metadataMap.containsKey(SentryStackFrame.JsonKeys.PACKAGE)) {
            this.metadataMap.put(SentryStackFrame.JsonKeys.PACKAGE, "co.hyperverge");
        }
        if (!this.metadataMap.containsKey("transactionId")) {
            this.metadataMap.put("transactionId", getTransactionId$hyperkyc_release());
        }
        if (!this.metadataMap.containsKey("workflowId")) {
            this.metadataMap.put("workflowId", getWorkflowId$hyperkyc_release());
        }
        HashMap<String, String> hashMap = this.metadataMap;
        HashMap<String, String> hashMap2 = hashMap;
        String str = hashMap.get(WorkflowAPIHeaders.SDK_TYPE);
        if (str == null) {
            str = "android";
        }
        hashMap2.put(WorkflowAPIHeaders.SDK_TYPE, str);
        HashMap<String, String> hashMap3 = this.metadataMap;
        HashMap<String, String> hashMap4 = hashMap3;
        String str2 = hashMap3.get(WorkflowAPIHeaders.SDK_VERSION);
        if (str2 == null) {
            str2 = BuildConfig.HYPERKYC_VERSION_NAME;
        }
        hashMap4.put(WorkflowAPIHeaders.SDK_VERSION, str2);
    }

    public final boolean exitIfRooted$hyperkyc_release() {
        Properties.Mobile mobile;
        Properties properties = getWorkflowConfig$hyperkyc_release().getProperties();
        return (properties == null || (mobile = properties.getMobile()) == null || !mobile.getExitIfRooted()) ? false : true;
    }

    public final boolean isServerSideResumeEnabled$hyperkyc_release() {
        if (this.workflowConfig != null) {
            Properties properties = getWorkflowConfig$hyperkyc_release().getProperties();
            if (properties != null && properties.getEnableServerSideResume()) {
                return true;
            }
        }
        return false;
    }
}
