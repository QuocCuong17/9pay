package org.apache.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: classes5.dex */
class RelationalOperators {
    RelationalOperators() {
    }

    /* loaded from: classes5.dex */
    static class Eq implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(Boolean.valueOf(isEqual(stack.pop(), stack.pop())));
        }

        protected boolean isEqual(Object obj, Object obj2) {
            if ((obj instanceof Number) && (obj2 instanceof Number)) {
                return ((Number) obj).floatValue() == ((Number) obj2).floatValue();
            }
            return obj.equals(obj2);
        }
    }

    /* loaded from: classes5.dex */
    private static abstract class AbstractNumberComparisonOperator implements Operator {
        protected abstract boolean compare(Number number, Number number2);

        private AbstractNumberComparisonOperator() {
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(Boolean.valueOf(compare((Number) stack.pop(), (Number) stack.pop())));
        }
    }

    /* loaded from: classes5.dex */
    static class Ge extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Ge() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() >= number2.floatValue();
        }
    }

    /* loaded from: classes5.dex */
    static class Gt extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Gt() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() > number2.floatValue();
        }
    }

    /* loaded from: classes5.dex */
    static class Le extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Le() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() <= number2.floatValue();
        }
    }

    /* loaded from: classes5.dex */
    static class Lt extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Lt() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number number, Number number2) {
            return number.floatValue() < number2.floatValue();
        }
    }

    /* loaded from: classes5.dex */
    static class Ne extends Eq {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.Eq
        protected boolean isEqual(Object obj, Object obj2) {
            return !super.isEqual(obj, obj2);
        }
    }
}
