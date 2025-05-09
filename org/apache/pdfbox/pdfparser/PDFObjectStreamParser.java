package org.apache.pdfbox.pdfparser;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;

/* loaded from: classes5.dex */
public class PDFObjectStreamParser extends BaseParser {
    private final COSStream stream;
    private List<COSObject> streamObjects;

    public PDFObjectStreamParser(COSStream cOSStream, COSDocument cOSDocument) throws IOException {
        super(cOSStream.getUnfilteredStream());
        this.streamObjects = null;
        this.document = cOSDocument;
        this.stream = cOSStream;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0041, code lost:
    
        android.util.Log.e("PdfBoxAndroid", "/ObjStm (object stream) has more objects than /N " + r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void parse() throws IOException {
        try {
            int i = this.stream.getInt("N");
            ArrayList arrayList = new ArrayList(i);
            this.streamObjects = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                long readObjectNumber = readObjectNumber();
                readLong();
                arrayList.add(Long.valueOf(readObjectNumber));
            }
            int i3 = 0;
            while (true) {
                COSBase parseDirObject = parseDirObject();
                if (parseDirObject == null) {
                    break;
                }
                COSObject cOSObject = new COSObject(parseDirObject);
                cOSObject.setGenerationNumber(0);
                if (i3 >= arrayList.size()) {
                    break;
                }
                cOSObject.setObjectNumber(((Long) arrayList.get(i3)).longValue());
                this.streamObjects.add(cOSObject);
                Log.d("PdfBoxAndroid", "parsed=" + cOSObject);
                if (!this.pdfSource.isEOF() && this.pdfSource.peek() == 101) {
                    readLine();
                }
                i3++;
            }
        } finally {
            this.pdfSource.close();
        }
    }

    public List<COSObject> getObjects() {
        return this.streamObjects;
    }
}
