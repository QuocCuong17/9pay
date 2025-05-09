package org.apache.pdfbox.pdmodel.common.function.type4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* loaded from: classes5.dex */
public class InstructionSequence {
    private List<Object> instructions = new ArrayList();

    public void addName(String str) {
        this.instructions.add(str);
    }

    public void addInteger(int i) {
        this.instructions.add(Integer.valueOf(i));
    }

    public void addReal(float f) {
        this.instructions.add(Float.valueOf(f));
    }

    public void addBoolean(boolean z) {
        this.instructions.add(Boolean.valueOf(z));
    }

    public void addProc(InstructionSequence instructionSequence) {
        this.instructions.add(instructionSequence);
    }

    public void execute(ExecutionContext executionContext) {
        Stack<Object> stack = executionContext.getStack();
        for (Object obj : this.instructions) {
            if (obj instanceof String) {
                String str = (String) obj;
                Operator operator = executionContext.getOperators().getOperator(str);
                if (operator != null) {
                    operator.execute(executionContext);
                } else {
                    throw new UnsupportedOperationException("Unknown operator or name: " + str);
                }
            } else {
                stack.push(obj);
            }
        }
        while (!stack.isEmpty() && (stack.peek() instanceof InstructionSequence)) {
            ((InstructionSequence) stack.pop()).execute(executionContext);
        }
    }
}
