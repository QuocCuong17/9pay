package org.apache.pdfbox.pdfparser;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSObjectKey;
import org.apache.pdfbox.cos.COSUpdateInfo;
import org.apache.pdfbox.pdfwriter.COSWriter;

/* loaded from: classes5.dex */
public class VisualSignatureParser extends BaseParser {
    public VisualSignatureParser(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public void parse() throws IOException {
        this.document = new COSDocument();
        skipToNextObj();
        boolean z = false;
        while (!z) {
            try {
                if (this.pdfSource.isEOF()) {
                    return;
                }
                try {
                    z = parseObject();
                } catch (IOException e) {
                    Log.w("PdfBoxAndroid", "Parsing Error, Skipping Object", e);
                    skipToNextObj();
                }
                skipSpaces();
            } catch (IOException e2) {
                if (!z) {
                    throw e2;
                }
                return;
            }
        }
    }

    protected String readExpectedStringUntilEOL(String str) throws IOException {
        int read = this.pdfSource.read();
        while (isWhitespace(read) && read != -1) {
            read = this.pdfSource.read();
        }
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        while (!isEOL(read) && read != -1 && i < str.length()) {
            char c = (char) read;
            sb.append(c);
            if (str.charAt(i) != c) {
                this.pdfSource.unread(sb.toString().getBytes("ISO-8859-1"));
                throw new IOException("Error: Expected to read '" + str + "' instead started reading '" + sb.toString() + "'");
            }
            i++;
            read = this.pdfSource.read();
        }
        while (isEOL(read) && read != -1) {
            read = this.pdfSource.read();
        }
        if (read != -1) {
            this.pdfSource.unread(read);
        }
        return sb.toString();
    }

    private boolean parseObject() throws IOException {
        char c;
        skipSpaces();
        int peek = this.pdfSource.peek();
        while (true) {
            c = (char) peek;
            if (c != 'e') {
                break;
            }
            readString();
            skipSpaces();
            peek = this.pdfSource.peek();
        }
        if (this.pdfSource.isEOF()) {
            return false;
        }
        if (c == 'x') {
            return true;
        }
        if (c == 't' || c == 's') {
            if (c == 't') {
                return true;
            }
            if (c != 's') {
                return false;
            }
            skipToNextObj();
            String readExpectedStringUntilEOL = readExpectedStringUntilEOL("%%EOF");
            if (readExpectedStringUntilEOL.contains("%%EOF") || this.pdfSource.isEOF()) {
                return true;
            }
            throw new IOException("expected='%%EOF' actual='" + readExpectedStringUntilEOL + "' next=" + readString() + " next=" + readString());
        }
        COSObjectKey parseObjectKey = parseObjectKey(false);
        skipSpaces();
        COSBase parseDirObject = parseDirObject();
        String readString = readString();
        if (readString.equals("stream")) {
            this.pdfSource.unread(readString.getBytes());
            this.pdfSource.unread(32);
            if (parseDirObject instanceof COSDictionary) {
                parseDirObject = parseCOSStream((COSDictionary) parseDirObject);
                readString = readString();
            } else {
                throw new IOException("stream not preceded by dictionary");
            }
        }
        COSObject objectFromPool = this.document.getObjectFromPool(parseObjectKey);
        if (parseDirObject instanceof COSUpdateInfo) {
            ((COSUpdateInfo) parseDirObject).setNeedToBeUpdated(true);
        }
        objectFromPool.setObject(parseDirObject);
        if (!readString.equals("endobj")) {
            if (readString.startsWith("endobj")) {
                this.pdfSource.unread(readString.substring(6).getBytes());
            } else if (!this.pdfSource.isEOF()) {
                try {
                    Float.parseFloat(readString);
                    this.pdfSource.unread(COSWriter.SPACE);
                    this.pdfSource.unread(readString.getBytes());
                } catch (NumberFormatException e) {
                    String readString2 = readString();
                    if (!readString2.equals("endobj")) {
                        if (isClosing()) {
                            this.pdfSource.read();
                        }
                        skipSpaces();
                        if (!readString().equals("endobj")) {
                            throw new IOException("expected='endobj' firstReadAttempt='" + readString + "' secondReadAttempt='" + readString2 + "' " + this.pdfSource, e);
                        }
                    }
                }
            }
        }
        skipSpaces();
        return false;
    }

    public COSDocument getDocument() throws IOException {
        if (this.document == null) {
            throw new IOException("You must call parse() before calling getDocument()");
        }
        return this.document;
    }
}
