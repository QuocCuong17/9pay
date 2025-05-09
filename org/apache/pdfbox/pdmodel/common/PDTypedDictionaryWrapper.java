package org.apache.pdfbox.pdmodel.common;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDTypedDictionaryWrapper extends PDDictionaryWrapper {
    public PDTypedDictionaryWrapper(String str) {
        getCOSDictionary().setName(COSName.TYPE, str);
    }

    public PDTypedDictionaryWrapper(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getType() {
        return getCOSDictionary().getNameAsString(COSName.TYPE);
    }
}
