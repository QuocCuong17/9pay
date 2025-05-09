package org.apache.pdfbox.pdfwriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public class ContentStreamWriter {
    private OutputStream output;
    public static final byte[] SPACE = {32};
    public static final byte[] EOL = {10};

    public ContentStreamWriter(OutputStream outputStream) {
        this.output = outputStream;
    }

    public void writeTokens(List list, int i, int i2) throws IOException {
        while (i < i2) {
            writeObject(list.get(i));
            this.output.write(32);
            i++;
        }
        this.output.flush();
    }

    private void writeObject(Object obj) throws IOException {
        if (obj instanceof COSString) {
            COSWriter.writeString((COSString) obj, this.output);
            return;
        }
        if (obj instanceof COSFloat) {
            ((COSFloat) obj).writePDF(this.output);
            return;
        }
        if (obj instanceof COSInteger) {
            ((COSInteger) obj).writePDF(this.output);
            return;
        }
        if (obj instanceof COSBoolean) {
            ((COSBoolean) obj).writePDF(this.output);
            return;
        }
        if (obj instanceof COSName) {
            ((COSName) obj).writePDF(this.output);
            return;
        }
        if (obj instanceof COSArray) {
            COSArray cOSArray = (COSArray) obj;
            this.output.write(COSWriter.ARRAY_OPEN);
            for (int i = 0; i < cOSArray.size(); i++) {
                writeObject(cOSArray.get(i));
                this.output.write(SPACE);
            }
            this.output.write(COSWriter.ARRAY_CLOSE);
            return;
        }
        if (obj instanceof COSDictionary) {
            this.output.write(COSWriter.DICT_OPEN);
            for (Map.Entry<COSName, COSBase> entry : ((COSDictionary) obj).entrySet()) {
                if (entry.getValue() != null) {
                    writeObject(entry.getKey());
                    OutputStream outputStream = this.output;
                    byte[] bArr = SPACE;
                    outputStream.write(bArr);
                    writeObject(entry.getValue());
                    this.output.write(bArr);
                }
            }
            this.output.write(COSWriter.DICT_CLOSE);
            this.output.write(SPACE);
            return;
        }
        if (obj instanceof Operator) {
            Operator operator = (Operator) obj;
            if (operator.getName().equals("BI")) {
                this.output.write("BI".getBytes("ISO-8859-1"));
                COSDictionary imageParameters = operator.getImageParameters();
                for (COSName cOSName : imageParameters.keySet()) {
                    COSBase dictionaryObject = imageParameters.getDictionaryObject(cOSName);
                    cOSName.writePDF(this.output);
                    this.output.write(SPACE);
                    writeObject(dictionaryObject);
                    this.output.write(EOL);
                }
                this.output.write("ID".getBytes("ISO-8859-1"));
                this.output.write(EOL);
                this.output.write(operator.getImageData());
                return;
            }
            this.output.write(operator.getName().getBytes("ISO-8859-1"));
            this.output.write(EOL);
            return;
        }
        throw new IOException("Error:Unknown type in content stream:" + obj);
    }

    public void writeTokens(List list) throws IOException {
        writeTokens(list, 0, list.size());
    }
}
