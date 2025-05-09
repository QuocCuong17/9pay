package org.apache.pdfbox.pdmodel.graphics;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/* loaded from: classes5.dex */
public class PDXObject implements COSObjectable {
    private PDStream stream;

    public static PDXObject createXObject(COSBase cOSBase, String str, PDResources pDResources) throws IOException {
        if (cOSBase == null) {
            return null;
        }
        if (!(cOSBase instanceof COSStream)) {
            throw new IOException("Unexpected object type: " + cOSBase.getClass().getName());
        }
        COSStream cOSStream = (COSStream) cOSBase;
        String nameAsString = cOSStream.getNameAsString(COSName.SUBTYPE);
        if (COSName.IMAGE.getName().equals(nameAsString)) {
            return new PDImageXObject(new PDStream(cOSStream), pDResources);
        }
        if (COSName.FORM.getName().equals(nameAsString)) {
            return new PDFormXObject(new PDStream(cOSStream), str);
        }
        if (COSName.PS.getName().equals(nameAsString)) {
            return new PDPostScriptXObject(new PDStream(cOSStream));
        }
        throw new IOException("Invalid XObject Subtype: " + nameAsString);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDXObject(PDStream pDStream, COSName cOSName) {
        this.stream = pDStream;
        pDStream.getStream().setName(COSName.TYPE, COSName.XOBJECT.getName());
        pDStream.getStream().setName(COSName.SUBTYPE, cOSName.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDXObject(PDDocument pDDocument, COSName cOSName) {
        PDStream pDStream = new PDStream(pDDocument);
        this.stream = pDStream;
        pDStream.getStream().setName(COSName.TYPE, COSName.XOBJECT.getName());
        this.stream.getStream().setName(COSName.SUBTYPE, cOSName.getName());
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public final COSBase getCOSObject() {
        return this.stream.getCOSObject();
    }

    public final COSStream getCOSStream() {
        return this.stream.getStream();
    }

    public final PDStream getPDStream() {
        return this.stream;
    }
}
