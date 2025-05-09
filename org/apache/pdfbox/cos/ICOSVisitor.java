package org.apache.pdfbox.cos;

import java.io.IOException;

/* loaded from: classes5.dex */
public interface ICOSVisitor {
    Object visitFromArray(COSArray cOSArray) throws IOException;

    Object visitFromBoolean(COSBoolean cOSBoolean) throws IOException;

    Object visitFromDictionary(COSDictionary cOSDictionary) throws IOException;

    Object visitFromDocument(COSDocument cOSDocument) throws IOException;

    Object visitFromFloat(COSFloat cOSFloat) throws IOException;

    Object visitFromInt(COSInteger cOSInteger) throws IOException;

    Object visitFromName(COSName cOSName) throws IOException;

    Object visitFromNull(COSNull cOSNull) throws IOException;

    Object visitFromStream(COSStream cOSStream) throws IOException;

    Object visitFromString(COSString cOSString) throws IOException;
}
