package org.apache.pdfbox.contentstream.operator;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public final class MissingOperandException extends IOException {
    public MissingOperandException(Operator operator, List<COSBase> list) {
        super("Operator " + operator.getName() + " has too few operands: " + list);
    }
}
