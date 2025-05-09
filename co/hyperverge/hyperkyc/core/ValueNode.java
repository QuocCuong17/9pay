package co.hyperverge.hyperkyc.core;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RuleEvaluator.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\t\u0010\f\u001a\u00020\rHÖ\u0001J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lco/hyperverge/hyperkyc/core/ValueNode;", "Lco/hyperverge/hyperkyc/core/Node;", "value", "", "(Ljava/lang/String;)V", "component1", "copy", "equals", "", "other", "", "eval", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ValueNode implements Node {
    private final String value;

    /* renamed from: component1, reason: from getter */
    private final String getValue() {
        return this.value;
    }

    public static /* synthetic */ ValueNode copy$default(ValueNode valueNode, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = valueNode.value;
        }
        return valueNode.copy(str);
    }

    public final ValueNode copy(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new ValueNode(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ValueNode) && Intrinsics.areEqual(this.value, ((ValueNode) other).value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public ValueNode(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    @Override // co.hyperverge.hyperkyc.core.Node
    public String eval() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }
}
