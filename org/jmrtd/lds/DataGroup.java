package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes6.dex */
public abstract class DataGroup extends AbstractTaggedLDSFile {
    private static final long serialVersionUID = -4761360877353069639L;

    /* JADX INFO: Access modifiers changed from: protected */
    public DataGroup(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DataGroup(int i, InputStream inputStream) throws IOException {
        super(i, inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        return "DataGroup [" + Integer.toHexString(getTag()) + " (" + getLength() + ")]";
    }
}
