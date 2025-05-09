package org.apache.pdfbox.pdmodel.common.function;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.pdmodel.common.PDRange;
import org.apache.pdfbox.pdmodel.common.function.type4.ExecutionContext;
import org.apache.pdfbox.pdmodel.common.function.type4.InstructionSequence;
import org.apache.pdfbox.pdmodel.common.function.type4.InstructionSequenceBuilder;
import org.apache.pdfbox.pdmodel.common.function.type4.Operators;

/* loaded from: classes5.dex */
public class PDFunctionType4 extends PDFunction {
    private static final Operators OPERATORS = new Operators();
    private final InstructionSequence instructions;

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        return 4;
    }

    public PDFunctionType4(COSBase cOSBase) throws IOException {
        super(cOSBase);
        this.instructions = InstructionSequenceBuilder.parse(getPDStream().getInputStreamAsString());
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        ExecutionContext executionContext = new ExecutionContext(OPERATORS);
        for (int i = 0; i < fArr.length; i++) {
            PDRange domainForInput = getDomainForInput(i);
            executionContext.getStack().push(Float.valueOf(clipToRange(fArr[i], domainForInput.getMin(), domainForInput.getMax())));
        }
        this.instructions.execute(executionContext);
        int numberOfOutputParameters = getNumberOfOutputParameters();
        int size = executionContext.getStack().size();
        if (size < numberOfOutputParameters) {
            throw new IllegalStateException("The type 4 function returned " + size + " values but the Range entry indicates that " + numberOfOutputParameters + " values be returned.");
        }
        float[] fArr2 = new float[numberOfOutputParameters];
        for (int i2 = numberOfOutputParameters - 1; i2 >= 0; i2--) {
            PDRange rangeForOutput = getRangeForOutput(i2);
            fArr2[i2] = executionContext.popReal();
            fArr2[i2] = clipToRange(fArr2[i2], rangeForOutput.getMin(), rangeForOutput.getMax());
        }
        return fArr2;
    }
}
