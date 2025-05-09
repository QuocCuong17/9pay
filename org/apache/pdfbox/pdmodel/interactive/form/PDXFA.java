package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/* loaded from: classes5.dex */
public class PDXFA implements COSObjectable {
    private COSBase xfa;

    public PDXFA(COSBase cOSBase) {
        this.xfa = cOSBase;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.xfa;
    }

    /* JADX WARN: Finally extract failed */
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            if (getCOSObject() instanceof COSArray) {
                byte[] bArr = new byte[1024];
                COSArray cOSArray = (COSArray) getCOSObject();
                for (int i = 1; i < cOSArray.size(); i += 2) {
                    COSBase object = cOSArray.getObject(i);
                    if (object instanceof COSStream) {
                        inputStream = ((COSStream) object).getUnfilteredStream();
                        while (true) {
                            int read = inputStream.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        byteArrayOutputStream.flush();
                    }
                }
            } else if (this.xfa.getCOSObject() instanceof COSStream) {
                byte[] bArr2 = new byte[1024];
                inputStream = ((COSStream) this.xfa.getCOSObject()).getUnfilteredStream();
                while (true) {
                    int read2 = inputStream.read(bArr2, 0, 1024);
                    if (read2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read2);
                }
                byteArrayOutputStream.flush();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            if (0 != 0) {
                inputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public Document getDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        return newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(getBytes()));
    }
}
