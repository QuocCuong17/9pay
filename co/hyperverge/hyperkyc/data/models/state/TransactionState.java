package co.hyperverge.hyperkyc.data.models.state;

import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransactionState.kt */
@kotlin.Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0081\b\u0018\u00002\u00020\u0001:\u0003%&'BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\u0010\rJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0017\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J]\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\bHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015¨\u0006("}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/TransactionState;", "", TtmlNode.TAG_METADATA, "Lco/hyperverge/hyperkyc/data/models/state/TransactionState$Metadata;", "transactionMetadata", "Lco/hyperverge/hyperkyc/data/models/state/TransactionState$TransactionMetadata;", "moduleExecutionOrder", "", "", "workflowExecutionOrder", "moduleData", "", "Lco/hyperverge/hyperkyc/data/models/state/TransactionState$ModuleData;", "(Lco/hyperverge/hyperkyc/data/models/state/TransactionState$Metadata;Lco/hyperverge/hyperkyc/data/models/state/TransactionState$TransactionMetadata;Ljava/util/List;Ljava/util/List;Ljava/util/Map;)V", "getMetadata", "()Lco/hyperverge/hyperkyc/data/models/state/TransactionState$Metadata;", "getModuleData", "()Ljava/util/Map;", "setModuleData", "(Ljava/util/Map;)V", "getModuleExecutionOrder", "()Ljava/util/List;", "getTransactionMetadata", "()Lco/hyperverge/hyperkyc/data/models/state/TransactionState$TransactionMetadata;", "getWorkflowExecutionOrder", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Metadata", "ModuleData", "TransactionMetadata", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class TransactionState {
    private final Metadata metadata;
    private Map<String, ModuleData> moduleData;
    private final List<String> moduleExecutionOrder;
    private final TransactionMetadata transactionMetadata;
    private final List<String> workflowExecutionOrder;

    public TransactionState() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ TransactionState copy$default(TransactionState transactionState, Metadata metadata, TransactionMetadata transactionMetadata, List list, List list2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            metadata = transactionState.metadata;
        }
        if ((i & 2) != 0) {
            transactionMetadata = transactionState.transactionMetadata;
        }
        TransactionMetadata transactionMetadata2 = transactionMetadata;
        if ((i & 4) != 0) {
            list = transactionState.moduleExecutionOrder;
        }
        List list3 = list;
        if ((i & 8) != 0) {
            list2 = transactionState.workflowExecutionOrder;
        }
        List list4 = list2;
        if ((i & 16) != 0) {
            map = transactionState.moduleData;
        }
        return transactionState.copy(metadata, transactionMetadata2, list3, list4, map);
    }

    /* renamed from: component1, reason: from getter */
    public final Metadata getMetadata() {
        return this.metadata;
    }

    /* renamed from: component2, reason: from getter */
    public final TransactionMetadata getTransactionMetadata() {
        return this.transactionMetadata;
    }

    public final List<String> component3() {
        return this.moduleExecutionOrder;
    }

    public final List<String> component4() {
        return this.workflowExecutionOrder;
    }

    public final Map<String, ModuleData> component5() {
        return this.moduleData;
    }

    public final TransactionState copy(Metadata metadata, TransactionMetadata transactionMetadata, List<String> moduleExecutionOrder, List<String> workflowExecutionOrder, Map<String, ModuleData> moduleData) {
        return new TransactionState(metadata, transactionMetadata, moduleExecutionOrder, workflowExecutionOrder, moduleData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransactionState)) {
            return false;
        }
        TransactionState transactionState = (TransactionState) other;
        return Intrinsics.areEqual(this.metadata, transactionState.metadata) && Intrinsics.areEqual(this.transactionMetadata, transactionState.transactionMetadata) && Intrinsics.areEqual(this.moduleExecutionOrder, transactionState.moduleExecutionOrder) && Intrinsics.areEqual(this.workflowExecutionOrder, transactionState.workflowExecutionOrder) && Intrinsics.areEqual(this.moduleData, transactionState.moduleData);
    }

    public int hashCode() {
        Metadata metadata = this.metadata;
        int hashCode = (metadata == null ? 0 : metadata.hashCode()) * 31;
        TransactionMetadata transactionMetadata = this.transactionMetadata;
        int hashCode2 = (hashCode + (transactionMetadata == null ? 0 : transactionMetadata.hashCode())) * 31;
        List<String> list = this.moduleExecutionOrder;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.workflowExecutionOrder;
        int hashCode4 = (hashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Map<String, ModuleData> map = this.moduleData;
        return hashCode4 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "TransactionState(metadata=" + this.metadata + ", transactionMetadata=" + this.transactionMetadata + ", moduleExecutionOrder=" + this.moduleExecutionOrder + ", workflowExecutionOrder=" + this.workflowExecutionOrder + ", moduleData=" + this.moduleData + ')';
    }

    public TransactionState(Metadata metadata, TransactionMetadata transactionMetadata, List<String> list, List<String> list2, Map<String, ModuleData> map) {
        this.metadata = metadata;
        this.transactionMetadata = transactionMetadata;
        this.moduleExecutionOrder = list;
        this.workflowExecutionOrder = list2;
        this.moduleData = map;
    }

    public /* synthetic */ TransactionState(Metadata metadata, TransactionMetadata transactionMetadata, List list, List list2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : metadata, (i & 2) != 0 ? null : transactionMetadata, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : list2, (i & 16) != 0 ? null : map);
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public final TransactionMetadata getTransactionMetadata() {
        return this.transactionMetadata;
    }

    public final List<String> getModuleExecutionOrder() {
        return this.moduleExecutionOrder;
    }

    public final List<String> getWorkflowExecutionOrder() {
        return this.workflowExecutionOrder;
    }

    public final Map<String, ModuleData> getModuleData() {
        return this.moduleData;
    }

    public final void setModuleData(Map<String, ModuleData> map) {
        this.moduleData = map;
    }

    /* compiled from: TransactionState.kt */
    @kotlin.Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JQ\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/TransactionState$Metadata;", "", "appId", "", "transactionId", "workflowId", "journeyId", WorkflowModule.PREFIX_INPUTS, "", "workflowHash", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "getInputs", "()Ljava/util/Map;", "getJourneyId", "getTransactionId", "getWorkflowHash", "getWorkflowId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Metadata {
        private final String appId;
        private final Map<String, Object> inputs;
        private final String journeyId;
        private final String transactionId;
        private final String workflowHash;
        private final String workflowId;

        public static /* synthetic */ Metadata copy$default(Metadata metadata, String str, String str2, String str3, String str4, Map map, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = metadata.appId;
            }
            if ((i & 2) != 0) {
                str2 = metadata.transactionId;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = metadata.workflowId;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = metadata.journeyId;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                map = metadata.inputs;
            }
            Map map2 = map;
            if ((i & 32) != 0) {
                str5 = metadata.workflowHash;
            }
            return metadata.copy(str, str6, str7, str8, map2, str5);
        }

        /* renamed from: component1, reason: from getter */
        public final String getAppId() {
            return this.appId;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTransactionId() {
            return this.transactionId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getWorkflowId() {
            return this.workflowId;
        }

        /* renamed from: component4, reason: from getter */
        public final String getJourneyId() {
            return this.journeyId;
        }

        public final Map<String, Object> component5() {
            return this.inputs;
        }

        /* renamed from: component6, reason: from getter */
        public final String getWorkflowHash() {
            return this.workflowHash;
        }

        public final Metadata copy(String appId, String transactionId, String workflowId, String journeyId, Map<String, ? extends Object> inputs, String workflowHash) {
            Intrinsics.checkNotNullParameter(appId, "appId");
            Intrinsics.checkNotNullParameter(transactionId, "transactionId");
            Intrinsics.checkNotNullParameter(workflowId, "workflowId");
            Intrinsics.checkNotNullParameter(journeyId, "journeyId");
            Intrinsics.checkNotNullParameter(inputs, "inputs");
            Intrinsics.checkNotNullParameter(workflowHash, "workflowHash");
            return new Metadata(appId, transactionId, workflowId, journeyId, inputs, workflowHash);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Metadata)) {
                return false;
            }
            Metadata metadata = (Metadata) other;
            return Intrinsics.areEqual(this.appId, metadata.appId) && Intrinsics.areEqual(this.transactionId, metadata.transactionId) && Intrinsics.areEqual(this.workflowId, metadata.workflowId) && Intrinsics.areEqual(this.journeyId, metadata.journeyId) && Intrinsics.areEqual(this.inputs, metadata.inputs) && Intrinsics.areEqual(this.workflowHash, metadata.workflowHash);
        }

        public int hashCode() {
            return (((((((((this.appId.hashCode() * 31) + this.transactionId.hashCode()) * 31) + this.workflowId.hashCode()) * 31) + this.journeyId.hashCode()) * 31) + this.inputs.hashCode()) * 31) + this.workflowHash.hashCode();
        }

        public String toString() {
            return "Metadata(appId=" + this.appId + ", transactionId=" + this.transactionId + ", workflowId=" + this.workflowId + ", journeyId=" + this.journeyId + ", inputs=" + this.inputs + ", workflowHash=" + this.workflowHash + ')';
        }

        public Metadata(String appId, String transactionId, String workflowId, String journeyId, Map<String, ? extends Object> inputs, String workflowHash) {
            Intrinsics.checkNotNullParameter(appId, "appId");
            Intrinsics.checkNotNullParameter(transactionId, "transactionId");
            Intrinsics.checkNotNullParameter(workflowId, "workflowId");
            Intrinsics.checkNotNullParameter(journeyId, "journeyId");
            Intrinsics.checkNotNullParameter(inputs, "inputs");
            Intrinsics.checkNotNullParameter(workflowHash, "workflowHash");
            this.appId = appId;
            this.transactionId = transactionId;
            this.workflowId = workflowId;
            this.journeyId = journeyId;
            this.inputs = inputs;
            this.workflowHash = workflowHash;
        }

        public final String getAppId() {
            return this.appId;
        }

        public final String getTransactionId() {
            return this.transactionId;
        }

        public final String getWorkflowId() {
            return this.workflowId;
        }

        public final String getJourneyId() {
            return this.journeyId;
        }

        public /* synthetic */ Metadata(String str, String str2, String str3, String str4, Map map, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i & 16) != 0 ? MapsKt.emptyMap() : map, str5);
        }

        public final Map<String, Object> getInputs() {
            return this.inputs;
        }

        public final String getWorkflowHash() {
            return this.workflowHash;
        }
    }

    /* compiled from: TransactionState.kt */
    @kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/TransactionState$TransactionMetadata;", "", "status", "", "moduleToResumeFrom", "(Ljava/lang/String;Ljava/lang/String;)V", "getModuleToResumeFrom", "()Ljava/lang/String;", "getStatus", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class TransactionMetadata {
        private final String moduleToResumeFrom;
        private final String status;

        public static /* synthetic */ TransactionMetadata copy$default(TransactionMetadata transactionMetadata, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = transactionMetadata.status;
            }
            if ((i & 2) != 0) {
                str2 = transactionMetadata.moduleToResumeFrom;
            }
            return transactionMetadata.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* renamed from: component2, reason: from getter */
        public final String getModuleToResumeFrom() {
            return this.moduleToResumeFrom;
        }

        public final TransactionMetadata copy(String status, String moduleToResumeFrom) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new TransactionMetadata(status, moduleToResumeFrom);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TransactionMetadata)) {
                return false;
            }
            TransactionMetadata transactionMetadata = (TransactionMetadata) other;
            return Intrinsics.areEqual(this.status, transactionMetadata.status) && Intrinsics.areEqual(this.moduleToResumeFrom, transactionMetadata.moduleToResumeFrom);
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            String str = this.moduleToResumeFrom;
            return hashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "TransactionMetadata(status=" + this.status + ", moduleToResumeFrom=" + this.moduleToResumeFrom + ')';
        }

        public TransactionMetadata(String status, String str) {
            Intrinsics.checkNotNullParameter(status, "status");
            this.status = status;
            this.moduleToResumeFrom = str;
        }

        public /* synthetic */ TransactionMetadata(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2);
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getModuleToResumeFrom() {
            return this.moduleToResumeFrom;
        }
    }

    /* compiled from: TransactionState.kt */
    @kotlin.Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u0000 ,2\u00020\u0001:\u0001,B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007HÆ\u0003J\u0017\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003Je\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00072\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006-"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/TransactionState$ModuleData;", "", "expireAt", "", "action", "", "requestIds", "", "flags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "variables", "", "parentModuleId", "(JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "setAction", "(Ljava/lang/String;)V", "getExpireAt", "()J", "getFlags", "()Ljava/util/List;", "setFlags", "(Ljava/util/List;)V", "getParentModuleId", "getRequestIds", "setRequestIds", "getVariables", "()Ljava/util/Map;", "setVariables", "(Ljava/util/Map;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class ModuleData {
        public static final String ACTION_DELETE = "delete";
        public static final String ACTION_PUT = "put";
        public static final long DEFAULT_EXPIRY = 2521823400000L;
        private String action;
        private final long expireAt;
        private List<HyperKycData.ApiFlags> flags;
        private final String parentModuleId;
        private List<String> requestIds;
        private Map<String, ? extends Object> variables;

        /* renamed from: component1, reason: from getter */
        public final long getExpireAt() {
            return this.expireAt;
        }

        /* renamed from: component2, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        public final List<String> component3() {
            return this.requestIds;
        }

        public final List<HyperKycData.ApiFlags> component4() {
            return this.flags;
        }

        public final Map<String, Object> component5() {
            return this.variables;
        }

        /* renamed from: component6, reason: from getter */
        public final String getParentModuleId() {
            return this.parentModuleId;
        }

        public final ModuleData copy(long expireAt, String action, List<String> requestIds, List<HyperKycData.ApiFlags> flags, Map<String, ? extends Object> variables, String parentModuleId) {
            Intrinsics.checkNotNullParameter(action, "action");
            return new ModuleData(expireAt, action, requestIds, flags, variables, parentModuleId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModuleData)) {
                return false;
            }
            ModuleData moduleData = (ModuleData) other;
            return this.expireAt == moduleData.expireAt && Intrinsics.areEqual(this.action, moduleData.action) && Intrinsics.areEqual(this.requestIds, moduleData.requestIds) && Intrinsics.areEqual(this.flags, moduleData.flags) && Intrinsics.areEqual(this.variables, moduleData.variables) && Intrinsics.areEqual(this.parentModuleId, moduleData.parentModuleId);
        }

        public int hashCode() {
            int m = ((TransactionState$ModuleData$$ExternalSyntheticBackport0.m(this.expireAt) * 31) + this.action.hashCode()) * 31;
            List<String> list = this.requestIds;
            int hashCode = (m + (list == null ? 0 : list.hashCode())) * 31;
            List<HyperKycData.ApiFlags> list2 = this.flags;
            int hashCode2 = (hashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
            Map<String, ? extends Object> map = this.variables;
            int hashCode3 = (hashCode2 + (map == null ? 0 : map.hashCode())) * 31;
            String str = this.parentModuleId;
            return hashCode3 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "ModuleData(expireAt=" + this.expireAt + ", action=" + this.action + ", requestIds=" + this.requestIds + ", flags=" + this.flags + ", variables=" + this.variables + ", parentModuleId=" + this.parentModuleId + ')';
        }

        public ModuleData(long j, String action, List<String> list, List<HyperKycData.ApiFlags> list2, Map<String, ? extends Object> map, String str) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.expireAt = j;
            this.action = action;
            this.requestIds = list;
            this.flags = list2;
            this.variables = map;
            this.parentModuleId = str;
        }

        public /* synthetic */ ModuleData(long j, String str, List list, List list2, Map map, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : list2, (i & 16) != 0 ? null : map, (i & 32) != 0 ? null : str2);
        }

        public final long getExpireAt() {
            return this.expireAt;
        }

        public final String getAction() {
            return this.action;
        }

        public final void setAction(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.action = str;
        }

        public final List<String> getRequestIds() {
            return this.requestIds;
        }

        public final void setRequestIds(List<String> list) {
            this.requestIds = list;
        }

        public final List<HyperKycData.ApiFlags> getFlags() {
            return this.flags;
        }

        public final void setFlags(List<HyperKycData.ApiFlags> list) {
            this.flags = list;
        }

        public final Map<String, Object> getVariables() {
            return this.variables;
        }

        public final void setVariables(Map<String, ? extends Object> map) {
            this.variables = map;
        }

        public final String getParentModuleId() {
            return this.parentModuleId;
        }
    }
}
