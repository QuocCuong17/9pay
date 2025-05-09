package org.apache.pdfbox.pdmodel.graphics.state;

import android.util.Log;
import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.function.PDFunction;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

/* loaded from: classes5.dex */
public final class PDSoftMask implements COSObjectable {
    private COSDictionary dictionary;
    private COSName subType = null;
    private PDFormXObject group = null;
    private COSArray backdropColor = null;
    private PDFunction transferFunction = null;

    public static PDSoftMask create(COSBase cOSBase) {
        if (cOSBase instanceof COSName) {
            if (COSName.NONE.equals(cOSBase)) {
                return null;
            }
            Log.w("PdfBoxAndroid", "Invalid SMask " + cOSBase);
            return null;
        }
        if (cOSBase instanceof COSDictionary) {
            return new PDSoftMask((COSDictionary) cOSBase);
        }
        Log.w("PdfBoxAndroid", "Invalid SMask " + cOSBase);
        return null;
    }

    public PDSoftMask(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public COSDictionary getCOSDictionary() {
        return this.dictionary;
    }

    public COSName getSubType() {
        if (this.subType == null) {
            this.subType = (COSName) getCOSDictionary().getDictionaryObject(COSName.S);
        }
        return this.subType;
    }

    public PDFormXObject getGroup() throws IOException {
        COSBase dictionaryObject;
        if (this.group == null && (dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.G)) != null) {
            this.group = (PDFormXObject) PDXObject.createXObject(dictionaryObject, COSName.G.getName(), null);
        }
        return this.group;
    }

    public COSArray getBackdropColor() {
        if (this.backdropColor == null) {
            this.backdropColor = (COSArray) getCOSDictionary().getDictionaryObject(COSName.BC);
        }
        return this.backdropColor;
    }

    public PDFunction getTransferFunction() throws IOException {
        COSBase dictionaryObject;
        if (this.transferFunction == null && (dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.TR)) != null) {
            this.transferFunction = PDFunction.create(dictionaryObject);
        }
        return this.transferFunction;
    }
}
