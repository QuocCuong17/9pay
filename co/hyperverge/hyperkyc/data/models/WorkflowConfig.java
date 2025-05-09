package co.hyperverge.hyperkyc.data.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import io.sentry.SentryEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowConfig.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B¡\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012(\b\u0002\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b\u0012(\b\u0002\u0010\f\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b\u0012(\b\u0002\u0010\r\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\u000b¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J)\u0010\u001e\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bHÆ\u0003J)\u0010\u001f\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bHÆ\u0003J)\u0010 \u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\u000bHÆ\u0003J¥\u0001\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052(\b\u0002\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b2(\b\u0002\u0010\f\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b2(\b\u0002\u0010\r\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\u000bHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\tHÖ\u0001R6\u0010\f\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R6\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\tX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR6\u0010\r\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010¨\u0006)"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;", "Ljava/io/Serializable;", "properties", "Lco/hyperverge/hyperkyc/data/models/Properties;", SentryEvent.JsonKeys.MODULES, "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule;", "conditions", "Ljava/util/LinkedHashMap;", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowCondition;", "Lkotlin/collections/LinkedHashMap;", WorkflowModule.PREFIX_CONDITIONAL_VARS, "sdkResponse", "(Lco/hyperverge/hyperkyc/data/models/Properties;Ljava/util/List;Ljava/util/LinkedHashMap;Ljava/util/LinkedHashMap;Ljava/util/LinkedHashMap;)V", "getConditionalVariables", "()Ljava/util/LinkedHashMap;", "getConditions", "hash", "getHash", "()Ljava/lang/String;", "setHash", "(Ljava/lang/String;)V", "getModules", "()Ljava/util/List;", "getProperties", "()Lco/hyperverge/hyperkyc/data/models/Properties;", "getSdkResponse", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class WorkflowConfig implements Serializable {

    @SerializedName(WorkflowModule.PREFIX_CONDITIONAL_VARS)
    private final LinkedHashMap<String, WorkflowCondition> conditionalVariables;

    @SerializedName("conditions")
    private final LinkedHashMap<String, WorkflowCondition> conditions;
    public String hash;

    @SerializedName(SentryEvent.JsonKeys.MODULES)
    private final List<WorkflowModule> modules;

    @SerializedName("properties")
    private final Properties properties;

    @SerializedName("sdkResponse")
    private final LinkedHashMap<String, String> sdkResponse;

    public WorkflowConfig() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ WorkflowConfig copy$default(WorkflowConfig workflowConfig, Properties properties, List list, LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, LinkedHashMap linkedHashMap3, int i, Object obj) {
        if ((i & 1) != 0) {
            properties = workflowConfig.properties;
        }
        if ((i & 2) != 0) {
            list = workflowConfig.modules;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            linkedHashMap = workflowConfig.conditions;
        }
        LinkedHashMap linkedHashMap4 = linkedHashMap;
        if ((i & 8) != 0) {
            linkedHashMap2 = workflowConfig.conditionalVariables;
        }
        LinkedHashMap linkedHashMap5 = linkedHashMap2;
        if ((i & 16) != 0) {
            linkedHashMap3 = workflowConfig.sdkResponse;
        }
        return workflowConfig.copy(properties, list2, linkedHashMap4, linkedHashMap5, linkedHashMap3);
    }

    /* renamed from: component1, reason: from getter */
    public final Properties getProperties() {
        return this.properties;
    }

    public final List<WorkflowModule> component2() {
        return this.modules;
    }

    public final LinkedHashMap<String, WorkflowCondition> component3() {
        return this.conditions;
    }

    public final LinkedHashMap<String, WorkflowCondition> component4() {
        return this.conditionalVariables;
    }

    public final LinkedHashMap<String, String> component5() {
        return this.sdkResponse;
    }

    public final WorkflowConfig copy(Properties properties, List<WorkflowModule> modules, LinkedHashMap<String, WorkflowCondition> conditions, LinkedHashMap<String, WorkflowCondition> conditionalVariables, LinkedHashMap<String, String> sdkResponse) {
        return new WorkflowConfig(properties, modules, conditions, conditionalVariables, sdkResponse);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowConfig)) {
            return false;
        }
        WorkflowConfig workflowConfig = (WorkflowConfig) other;
        return Intrinsics.areEqual(this.properties, workflowConfig.properties) && Intrinsics.areEqual(this.modules, workflowConfig.modules) && Intrinsics.areEqual(this.conditions, workflowConfig.conditions) && Intrinsics.areEqual(this.conditionalVariables, workflowConfig.conditionalVariables) && Intrinsics.areEqual(this.sdkResponse, workflowConfig.sdkResponse);
    }

    public int hashCode() {
        Properties properties = this.properties;
        int hashCode = (properties == null ? 0 : properties.hashCode()) * 31;
        List<WorkflowModule> list = this.modules;
        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
        LinkedHashMap<String, WorkflowCondition> linkedHashMap = this.conditions;
        int hashCode3 = (hashCode2 + (linkedHashMap == null ? 0 : linkedHashMap.hashCode())) * 31;
        LinkedHashMap<String, WorkflowCondition> linkedHashMap2 = this.conditionalVariables;
        int hashCode4 = (hashCode3 + (linkedHashMap2 == null ? 0 : linkedHashMap2.hashCode())) * 31;
        LinkedHashMap<String, String> linkedHashMap3 = this.sdkResponse;
        return hashCode4 + (linkedHashMap3 != null ? linkedHashMap3.hashCode() : 0);
    }

    public String toString() {
        return "WorkflowConfig(properties=" + this.properties + ", modules=" + this.modules + ", conditions=" + this.conditions + ", conditionalVariables=" + this.conditionalVariables + ", sdkResponse=" + this.sdkResponse + ')';
    }

    public WorkflowConfig(Properties properties, List<WorkflowModule> list, LinkedHashMap<String, WorkflowCondition> linkedHashMap, LinkedHashMap<String, WorkflowCondition> linkedHashMap2, LinkedHashMap<String, String> linkedHashMap3) {
        this.properties = properties;
        this.modules = list;
        this.conditions = linkedHashMap;
        this.conditionalVariables = linkedHashMap2;
        this.sdkResponse = linkedHashMap3;
    }

    public /* synthetic */ WorkflowConfig(Properties properties, List list, LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, LinkedHashMap linkedHashMap3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : properties, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : linkedHashMap, (i & 8) != 0 ? null : linkedHashMap2, (i & 16) != 0 ? null : linkedHashMap3);
    }

    public final Properties getProperties() {
        return this.properties;
    }

    public final List<WorkflowModule> getModules() {
        return this.modules;
    }

    public final LinkedHashMap<String, WorkflowCondition> getConditions() {
        return this.conditions;
    }

    public final LinkedHashMap<String, WorkflowCondition> getConditionalVariables() {
        return this.conditionalVariables;
    }

    public final LinkedHashMap<String, String> getSdkResponse() {
        return this.sdkResponse;
    }

    public final String getHash() {
        String str = this.hash;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hash");
        return null;
    }

    public final void setHash(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hash = str;
    }
}
