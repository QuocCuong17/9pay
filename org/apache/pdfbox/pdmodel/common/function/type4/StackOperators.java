package org.apache.pdfbox.pdmodel.common.function.type4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/* loaded from: classes5.dex */
class StackOperators {
    StackOperators() {
    }

    /* loaded from: classes5.dex */
    static class Copy implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int intValue = ((Number) stack.pop()).intValue();
            if (intValue > 0) {
                int size = stack.size();
                stack.addAll(new ArrayList(stack.subList(size - intValue, size)));
            }
        }
    }

    /* loaded from: classes5.dex */
    static class Dup implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            stack.push(stack.peek());
        }
    }

    /* loaded from: classes5.dex */
    static class Exch implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Object pop = stack.pop();
            Object pop2 = stack.pop();
            stack.push(pop);
            stack.push(pop2);
        }
    }

    /* loaded from: classes5.dex */
    static class Index implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int intValue = ((Number) stack.pop()).intValue();
            if (intValue < 0) {
                throw new IllegalArgumentException("rangecheck: " + intValue);
            }
            stack.push(stack.get((stack.size() - intValue) - 1));
        }
    }

    /* loaded from: classes5.dex */
    static class Pop implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().pop();
        }
    }

    /* loaded from: classes5.dex */
    static class Roll implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            int intValue = ((Number) stack.pop()).intValue();
            int intValue2 = ((Number) stack.pop()).intValue();
            if (intValue == 0) {
                return;
            }
            if (intValue2 < 0) {
                throw new IllegalArgumentException("rangecheck: " + intValue2);
            }
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            int i = 0;
            if (intValue < 0) {
                int i2 = intValue2 + intValue;
                while (i < i2) {
                    linkedList2.addFirst(stack.pop());
                    i++;
                }
                while (intValue < 0) {
                    linkedList.addFirst(stack.pop());
                    intValue++;
                }
                stack.addAll(linkedList2);
                stack.addAll(linkedList);
                return;
            }
            int i3 = intValue2 - intValue;
            while (intValue > 0) {
                linkedList.addFirst(stack.pop());
                intValue--;
            }
            while (i < i3) {
                linkedList2.addFirst(stack.pop());
                i++;
            }
            stack.addAll(linkedList);
            stack.addAll(linkedList2);
        }
    }
}
