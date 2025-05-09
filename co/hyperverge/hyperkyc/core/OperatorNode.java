package co.hyperverge.hyperkyc.core;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RuleEvaluator.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\t\u0010\b\u001a\u00020\u0001HÂ\u0003J\u001b\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004HÂ\u0003J\t\u0010\n\u001a\u00020\u0001HÂ\u0003J9\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u001a\b\u0002\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\b\u0010\u0010\u001a\u00020\u0005H\u0016J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lco/hyperverge/hyperkyc/core/OperatorNode;", "Lco/hyperverge/hyperkyc/core/Node;", "leftNode", "operator", "Lkotlin/Function2;", "", "rightNode", "(Lco/hyperverge/hyperkyc/core/Node;Lkotlin/jvm/functions/Function2;Lco/hyperverge/hyperkyc/core/Node;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "", "eval", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OperatorNode implements Node {
    private final Node leftNode;
    private final Function2<String, String, String> operator;
    private final Node rightNode;

    /* renamed from: component1, reason: from getter */
    private final Node getLeftNode() {
        return this.leftNode;
    }

    private final Function2<String, String, String> component2() {
        return this.operator;
    }

    /* renamed from: component3, reason: from getter */
    private final Node getRightNode() {
        return this.rightNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ OperatorNode copy$default(OperatorNode operatorNode, Node node, Function2 function2, Node node2, int i, Object obj) {
        if ((i & 1) != 0) {
            node = operatorNode.leftNode;
        }
        if ((i & 2) != 0) {
            function2 = operatorNode.operator;
        }
        if ((i & 4) != 0) {
            node2 = operatorNode.rightNode;
        }
        return operatorNode.copy(node, function2, node2);
    }

    public final OperatorNode copy(Node leftNode, Function2<? super String, ? super String, String> operator, Node rightNode) {
        Intrinsics.checkNotNullParameter(leftNode, "leftNode");
        Intrinsics.checkNotNullParameter(operator, "operator");
        Intrinsics.checkNotNullParameter(rightNode, "rightNode");
        return new OperatorNode(leftNode, operator, rightNode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OperatorNode)) {
            return false;
        }
        OperatorNode operatorNode = (OperatorNode) other;
        return Intrinsics.areEqual(this.leftNode, operatorNode.leftNode) && Intrinsics.areEqual(this.operator, operatorNode.operator) && Intrinsics.areEqual(this.rightNode, operatorNode.rightNode);
    }

    public int hashCode() {
        return (((this.leftNode.hashCode() * 31) + this.operator.hashCode()) * 31) + this.rightNode.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OperatorNode(Node leftNode, Function2<? super String, ? super String, String> operator, Node rightNode) {
        Intrinsics.checkNotNullParameter(leftNode, "leftNode");
        Intrinsics.checkNotNullParameter(operator, "operator");
        Intrinsics.checkNotNullParameter(rightNode, "rightNode");
        this.leftNode = leftNode;
        this.operator = operator;
        this.rightNode = rightNode;
    }

    @Override // co.hyperverge.hyperkyc.core.Node
    public String eval() {
        return this.operator.invoke(this.leftNode.eval(), this.rightNode.eval());
    }

    public String toString() {
        return '[' + this.leftNode + "] ?? [" + this.rightNode + ']';
    }
}
