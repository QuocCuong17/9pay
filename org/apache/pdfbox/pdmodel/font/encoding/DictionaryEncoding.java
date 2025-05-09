package org.apache.pdfbox.pdmodel.font.encoding;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class DictionaryEncoding extends Encoding {
    private final Encoding baseEncoding;
    private final Map<Integer, String> differences = new HashMap();
    private final COSDictionary encoding;

    public DictionaryEncoding(COSName cOSName, COSArray cOSArray) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.encoding = cOSDictionary;
        cOSDictionary.setItem(COSName.NAME, (COSBase) COSName.ENCODING);
        cOSDictionary.setItem(COSName.DIFFERENCES, (COSBase) cOSArray);
        if (cOSName != COSName.STANDARD_ENCODING) {
            cOSDictionary.setItem(COSName.BASE_ENCODING, (COSBase) cOSName);
            this.baseEncoding = Encoding.getInstance(cOSName);
        } else {
            this.baseEncoding = Encoding.getInstance(cOSName);
        }
        if (this.baseEncoding != null) {
            return;
        }
        throw new IllegalArgumentException("Invalid encoding: " + cOSName);
    }

    public DictionaryEncoding(COSDictionary cOSDictionary, boolean z, Encoding encoding) {
        this.encoding = cOSDictionary;
        Encoding encoding2 = cOSDictionary.containsKey(COSName.BASE_ENCODING) ? Encoding.getInstance(cOSDictionary.getCOSName(COSName.BASE_ENCODING)) : null;
        if (encoding2 != null) {
            encoding = encoding2;
        } else if (z) {
            encoding = StandardEncoding.INSTANCE;
        } else if (encoding == null) {
            encoding = StandardEncoding.INSTANCE;
            Log.w("PdfBoxAndroid", "Built-in encoding required for symbolic font, using standard encoding");
        }
        this.baseEncoding = encoding;
        this.codeToName.putAll(encoding.codeToName);
        this.names.addAll(encoding.names);
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.DIFFERENCES);
        int i = -1;
        for (int i2 = 0; cOSArray != null && i2 < cOSArray.size(); i2++) {
            COSBase object = cOSArray.getObject(i2);
            if (object instanceof COSNumber) {
                i = ((COSNumber) object).intValue();
            } else if (object instanceof COSName) {
                COSName cOSName = (COSName) object;
                add(i, cOSName.getName());
                this.differences.put(Integer.valueOf(i), cOSName.getName());
                i++;
            }
        }
    }

    public Encoding getBaseEncoding() {
        return this.baseEncoding;
    }

    public Map<Integer, String> getDifferences() {
        return this.differences;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.encoding;
    }
}
