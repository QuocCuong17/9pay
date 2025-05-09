package co.hyperverge.hyperkyc.data.models.state;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransactionStateRequest.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B!\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/TransactionStateRequest;", "", WorkflowModule.PREFIX_INPUTS, "", "", "workflowHash", "(Ljava/util/Map;Ljava/lang/String;)V", "getInputs", "()Ljava/util/Map;", "getWorkflowHash", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class TransactionStateRequest {
    private final Map<String, Object> inputs;
    private final String workflowHash;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TransactionStateRequest copy$default(TransactionStateRequest transactionStateRequest, Map map, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            map = transactionStateRequest.inputs;
        }
        if ((i & 2) != 0) {
            str = transactionStateRequest.workflowHash;
        }
        return transactionStateRequest.copy(map, str);
    }

    public final Map<String, Object> component1() {
        return this.inputs;
    }

    /* renamed from: component2, reason: from getter */
    public final String getWorkflowHash() {
        return this.workflowHash;
    }

    public final TransactionStateRequest copy(Map<String, ? extends Object> inputs, String workflowHash) {
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        Intrinsics.checkNotNullParameter(workflowHash, "workflowHash");
        return new TransactionStateRequest(inputs, workflowHash);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransactionStateRequest)) {
            return false;
        }
        TransactionStateRequest transactionStateRequest = (TransactionStateRequest) other;
        return Intrinsics.areEqual(this.inputs, transactionStateRequest.inputs) && Intrinsics.areEqual(this.workflowHash, transactionStateRequest.workflowHash);
    }

    public int hashCode() {
        return (this.inputs.hashCode() * 31) + this.workflowHash.hashCode();
    }

    public String toString() {
        return "TransactionStateRequest(inputs=" + this.inputs + ", workflowHash=" + this.workflowHash + ')';
    }

    public TransactionStateRequest(Map<String, ? extends Object> inputs, String workflowHash) {
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        Intrinsics.checkNotNullParameter(workflowHash, "workflowHash");
        this.inputs = inputs;
        this.workflowHash = workflowHash;
    }

    public final Map<String, Object> getInputs() {
        return this.inputs;
    }

    public final String getWorkflowHash() {
        return this.workflowHash;
    }
}
