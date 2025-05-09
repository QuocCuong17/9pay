package co.hyperverge.hyperkyc.data.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StartTransaction.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0003J=\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/StartTransaction;", "", "transactionId", "", "workflowId", "journeyId", WorkflowModule.PREFIX_INPUTS, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getInputs", "()Ljava/util/Map;", "getJourneyId", "()Ljava/lang/String;", "getTransactionId", "getWorkflowId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class StartTransaction {
    private final Map<String, Object> inputs;
    private final String journeyId;
    private final String transactionId;
    private final String workflowId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StartTransaction copy$default(StartTransaction startTransaction, String str, String str2, String str3, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = startTransaction.transactionId;
        }
        if ((i & 2) != 0) {
            str2 = startTransaction.workflowId;
        }
        if ((i & 4) != 0) {
            str3 = startTransaction.journeyId;
        }
        if ((i & 8) != 0) {
            map = startTransaction.inputs;
        }
        return startTransaction.copy(str, str2, str3, map);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTransactionId() {
        return this.transactionId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getWorkflowId() {
        return this.workflowId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getJourneyId() {
        return this.journeyId;
    }

    public final Map<String, Object> component4() {
        return this.inputs;
    }

    public final StartTransaction copy(String transactionId, String workflowId, String journeyId, Map<String, ? extends Object> inputs) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(journeyId, "journeyId");
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        return new StartTransaction(transactionId, workflowId, journeyId, inputs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StartTransaction)) {
            return false;
        }
        StartTransaction startTransaction = (StartTransaction) other;
        return Intrinsics.areEqual(this.transactionId, startTransaction.transactionId) && Intrinsics.areEqual(this.workflowId, startTransaction.workflowId) && Intrinsics.areEqual(this.journeyId, startTransaction.journeyId) && Intrinsics.areEqual(this.inputs, startTransaction.inputs);
    }

    public int hashCode() {
        return (((((this.transactionId.hashCode() * 31) + this.workflowId.hashCode()) * 31) + this.journeyId.hashCode()) * 31) + this.inputs.hashCode();
    }

    public String toString() {
        return "StartTransaction(transactionId=" + this.transactionId + ", workflowId=" + this.workflowId + ", journeyId=" + this.journeyId + ", inputs=" + this.inputs + ')';
    }

    public StartTransaction(String transactionId, String workflowId, String journeyId, Map<String, ? extends Object> inputs) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(journeyId, "journeyId");
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        this.transactionId = transactionId;
        this.workflowId = workflowId;
        this.journeyId = journeyId;
        this.inputs = inputs;
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

    public /* synthetic */ StartTransaction(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, Object> getInputs() {
        return this.inputs;
    }
}
