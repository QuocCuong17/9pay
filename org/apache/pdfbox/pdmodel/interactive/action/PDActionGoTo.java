package org.apache.pdfbox.pdmodel.interactive.action;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;

/* loaded from: classes5.dex */
public class PDActionGoTo extends PDAction {
    public static final String SUB_TYPE = "GoTo";

    public PDActionGoTo() {
        setSubType(SUB_TYPE);
    }

    public PDActionGoTo(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDDestination getDestination() throws IOException {
        return PDDestination.create(getCOSDictionary().getDictionaryObject("D"));
    }

    public void setDestination(PDDestination pDDestination) {
        getCOSDictionary().setItem("D", pDDestination);
    }
}
