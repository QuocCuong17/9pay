package org.apache.pdfbox.pdfparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObjectKey;
import org.apache.pdfbox.cos.COSStream;

/* loaded from: classes5.dex */
public class PDFXrefStreamParser extends BaseParser {
    private COSStream stream;
    private XrefTrailerResolver xrefTrailerResolver;

    public PDFXrefStreamParser(COSStream cOSStream, COSDocument cOSDocument, XrefTrailerResolver xrefTrailerResolver) throws IOException {
        super(cOSStream.getUnfilteredStream());
        this.document = cOSDocument;
        this.stream = cOSStream;
        this.xrefTrailerResolver = xrefTrailerResolver;
    }

    public void parse() throws IOException {
        try {
            COSArray cOSArray = (COSArray) this.stream.getDictionaryObject(COSName.W);
            COSArray cOSArray2 = (COSArray) this.stream.getDictionaryObject(COSName.INDEX);
            if (cOSArray2 == null) {
                cOSArray2 = new COSArray();
                cOSArray2.add((COSBase) COSInteger.ZERO);
                cOSArray2.add(this.stream.getDictionaryObject(COSName.SIZE));
            }
            ArrayList arrayList = new ArrayList();
            Iterator<COSBase> it = cOSArray2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                long longValue = ((COSInteger) it.next()).longValue();
                int intValue = ((COSInteger) it.next()).intValue();
                for (int i = 0; i < intValue; i++) {
                    arrayList.add(Long.valueOf(i + longValue));
                }
            }
            Iterator it2 = arrayList.iterator();
            int i2 = cOSArray.getInt(0);
            int i3 = cOSArray.getInt(1);
            int i4 = cOSArray.getInt(2);
            int i5 = i2 + i3 + i4;
            while (this.pdfSource.available() > 0 && it2.hasNext()) {
                byte[] bArr = new byte[i5];
                this.pdfSource.read(bArr);
                int i6 = 0;
                for (int i7 = 0; i7 < i2; i7++) {
                    i6 += (bArr[i7] & 255) << (((i2 - i7) - 1) * 8);
                }
                Long l = (Long) it2.next();
                if (i6 == 1) {
                    int i8 = 0;
                    for (int i9 = 0; i9 < i3; i9++) {
                        i8 += (bArr[i9 + i2] & 255) << (((i3 - i9) - 1) * 8);
                    }
                    int i10 = 0;
                    for (int i11 = 0; i11 < i4; i11++) {
                        i10 += (bArr[(i11 + i2) + i3] & 255) << (((i4 - i11) - 1) * 8);
                    }
                    this.xrefTrailerResolver.setXRef(new COSObjectKey(l.longValue(), i10), i8);
                } else if (i6 == 2) {
                    int i12 = 0;
                    for (int i13 = 0; i13 < i3; i13++) {
                        i12 += (bArr[i13 + i2] & 255) << (((i3 - i13) - 1) * 8);
                    }
                    this.xrefTrailerResolver.setXRef(new COSObjectKey(l.longValue(), 0), -i12);
                }
            }
        } finally {
            this.pdfSource.close();
        }
    }
}
