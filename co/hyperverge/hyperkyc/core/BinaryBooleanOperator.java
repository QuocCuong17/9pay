package co.hyperverge.hyperkyc.core;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RuleEvaluator.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\u001b\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003JC\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R#\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/core/BinaryBooleanOperator;", "Lco/hyperverge/hyperkyc/core/Operator;", "label", "", "precedence", "", "isLeftAssociative", "", "action", "Lkotlin/Function2;", "(Ljava/lang/String;IZLkotlin/jvm/functions/Function2;)V", "getAction", "()Lkotlin/jvm/functions/Function2;", "()Z", "getLabel", "()Ljava/lang/String;", "getPrecedence", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BinaryBooleanOperator implements Operator {
    private final Function2<String, String, String> action;
    private final boolean isLeftAssociative;
    private final String label;
    private final int precedence;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BinaryBooleanOperator copy$default(BinaryBooleanOperator binaryBooleanOperator, String str, int i, boolean z, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = binaryBooleanOperator.label;
        }
        if ((i2 & 2) != 0) {
            i = binaryBooleanOperator.precedence;
        }
        if ((i2 & 4) != 0) {
            z = binaryBooleanOperator.isLeftAssociative;
        }
        if ((i2 & 8) != 0) {
            function2 = binaryBooleanOperator.action;
        }
        return binaryBooleanOperator.copy(str, i, z, function2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPrecedence() {
        return this.precedence;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsLeftAssociative() {
        return this.isLeftAssociative;
    }

    public final Function2<String, String, String> component4() {
        return this.action;
    }

    public final BinaryBooleanOperator copy(String label, int precedence, boolean isLeftAssociative, Function2<? super String, ? super String, String> action) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(action, "action");
        return new BinaryBooleanOperator(label, precedence, isLeftAssociative, action);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BinaryBooleanOperator)) {
            return false;
        }
        BinaryBooleanOperator binaryBooleanOperator = (BinaryBooleanOperator) other;
        return Intrinsics.areEqual(this.label, binaryBooleanOperator.label) && this.precedence == binaryBooleanOperator.precedence && this.isLeftAssociative == binaryBooleanOperator.isLeftAssociative && Intrinsics.areEqual(this.action, binaryBooleanOperator.action);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.label.hashCode() * 31) + this.precedence) * 31;
        boolean z = this.isLeftAssociative;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((hashCode + i) * 31) + this.action.hashCode();
    }

    public String toString() {
        return "BinaryBooleanOperator(label=" + this.label + ", precedence=" + this.precedence + ", isLeftAssociative=" + this.isLeftAssociative + ", action=" + this.action + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BinaryBooleanOperator(String label, int i, boolean z, Function2<? super String, ? super String, String> action) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(action, "action");
        this.label = label;
        this.precedence = i;
        this.isLeftAssociative = z;
        this.action = action;
    }

    public /* synthetic */ BinaryBooleanOperator(String str, int i, boolean z, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? true : z, function2);
    }

    public final String getLabel() {
        return this.label;
    }

    public final int getPrecedence() {
        return this.precedence;
    }

    public final boolean isLeftAssociative() {
        return this.isLeftAssociative;
    }

    public final Function2<String, String, String> getAction() {
        return this.action;
    }
}
