package co.hyperverge.hyperkyc.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RuleEvaluator.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/core/InvalidExpressionException;", "", "expr", "", "pos", "", "(Ljava/lang/String;I)V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class InvalidExpressionException extends Throwable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InvalidExpressionException(String expr, int i) {
        super("Invalid expression at " + i + " in " + expr);
        Intrinsics.checkNotNullParameter(expr, "expr");
    }
}
