package org.apache.pdfbox.pdmodel.interactive.annotation;

import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.common.COSDictionaryMap;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDAppearanceEntry implements COSObjectable {
    private COSBase entry;

    private PDAppearanceEntry() {
    }

    public PDAppearanceEntry(COSBase cOSBase) {
        this.entry = cOSBase;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.entry;
    }

    public boolean isSubDictionary() {
        return !(this.entry instanceof COSStream);
    }

    public boolean isStream() {
        return this.entry instanceof COSStream;
    }

    public PDAppearanceStream getAppearanceStream() {
        if (!isStream()) {
            throw new IllegalStateException();
        }
        return new PDAppearanceStream((COSStream) this.entry);
    }

    public Map<COSName, PDAppearanceStream> getSubDictionary() {
        if (!isSubDictionary()) {
            throw new IllegalStateException();
        }
        COSDictionary cOSDictionary = (COSDictionary) this.entry;
        HashMap hashMap = new HashMap();
        for (COSName cOSName : cOSDictionary.keySet()) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
            if (dictionaryObject instanceof COSStream) {
                hashMap.put(cOSName, new PDAppearanceStream((COSStream) dictionaryObject));
            }
        }
        return new COSDictionaryMap(hashMap, cOSDictionary);
    }
}
