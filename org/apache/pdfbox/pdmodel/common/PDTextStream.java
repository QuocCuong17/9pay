package org.apache.pdfbox.pdmodel.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: classes5.dex */
public class PDTextStream implements COSObjectable {
    private COSStream stream;
    private COSString string;

    public PDTextStream(COSString cOSString) {
        this.string = cOSString;
    }

    public PDTextStream(String str) {
        this.string = new COSString(str);
    }

    public PDTextStream(COSStream cOSStream) {
        this.stream = cOSStream;
    }

    public static PDTextStream createTextStream(COSBase cOSBase) {
        if (cOSBase instanceof COSString) {
            return new PDTextStream((COSString) cOSBase);
        }
        if (cOSBase instanceof COSStream) {
            return new PDTextStream((COSStream) cOSBase);
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        COSString cOSString = this.string;
        return cOSString == null ? this.stream : cOSString;
    }

    public String getAsString() throws IOException {
        COSString cOSString = this.string;
        if (cOSString != null) {
            return cOSString.getString();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream unfilteredStream = this.stream.getUnfilteredStream();
        IOUtils.copy(unfilteredStream, byteArrayOutputStream);
        IOUtils.closeQuietly(unfilteredStream);
        return new String(byteArrayOutputStream.toByteArray(), "ISO-8859-1");
    }

    public InputStream getAsStream() throws IOException {
        if (this.string != null) {
            return new ByteArrayInputStream(this.string.getBytes());
        }
        return this.stream.getUnfilteredStream();
    }
}
