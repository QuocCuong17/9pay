package org.apache.pdfbox.pdmodel.font.encoding;

import java.util.Map;
import org.apache.fontbox.afm.CharMetric;
import org.apache.fontbox.afm.FontMetrics;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class Type1Encoding extends Encoding {
    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return null;
    }

    public static Type1Encoding fromFontBox(org.apache.fontbox.encoding.Encoding encoding) {
        Map<Integer, String> codeToNameMap = encoding.getCodeToNameMap();
        Type1Encoding type1Encoding = new Type1Encoding();
        for (Map.Entry<Integer, String> entry : codeToNameMap.entrySet()) {
            type1Encoding.add(entry.getKey().intValue(), entry.getValue());
        }
        return type1Encoding;
    }

    public Type1Encoding() {
    }

    public Type1Encoding(FontMetrics fontMetrics) {
        for (CharMetric charMetric : fontMetrics.getCharMetrics()) {
            add(charMetric.getCharacterCode(), charMetric.getName());
        }
    }
}
