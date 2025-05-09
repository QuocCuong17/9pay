package co.hyperverge.hyperkyc.core;

import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RuleEvaluator.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lco/hyperverge/hyperkyc/core/InvalidSymbolException;", "Lco/hyperverge/hyperkyc/core/InvalidExpressionException;", SentryStackFrame.JsonKeys.SYMBOL, "", "expr", "pos", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getSymbol", "()Ljava/lang/String;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InvalidSymbolException extends InvalidExpressionException {
    private final String symbol;

    public final String getSymbol() {
        return this.symbol;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InvalidSymbolException(String symbol, String expr, int i) {
        super(expr, i);
        Intrinsics.checkNotNullParameter(symbol, "symbol");
        Intrinsics.checkNotNullParameter(expr, "expr");
        this.symbol = symbol;
    }
}
