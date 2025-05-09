package org.apache.pdfbox.cos;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public abstract class COSBase implements COSObjectable {
    private boolean direct;

    public abstract Object accept(ICOSVisitor iCOSVisitor) throws IOException;

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public void setDirect(boolean z) {
        this.direct = z;
    }
}
