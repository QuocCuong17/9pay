package org.apache.pdfbox.pdfparser;

import android.util.Log;
import com.facebook.internal.ServerProtocol;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSUpdateInfo;
import org.apache.pdfbox.pdmodel.common.PDStream;

/* loaded from: classes5.dex */
public class PDFStreamParser extends BaseParser {
    private static final int MAX_BIN_CHAR_TEST_LENGTH = 10;
    private final byte[] binCharTestArr;
    private final List<Object> streamObjects;

    private boolean isSpaceOrReturn(int i) {
        return i == 10 || i == 13 || i == 32;
    }

    public PDFStreamParser(InputStream inputStream) throws IOException {
        super(inputStream);
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public PDFStreamParser(PDStream pDStream) throws IOException {
        this(pDStream.createInputStream());
    }

    public PDFStreamParser(COSStream cOSStream) throws IOException {
        this(cOSStream.getUnfilteredStream());
    }

    public void parse() throws IOException {
        while (true) {
            try {
                Object parseNextToken = parseNextToken();
                if (parseNextToken == null) {
                    return;
                } else {
                    this.streamObjects.add(parseNextToken);
                }
            } finally {
                this.pdfSource.close();
            }
        }
    }

    public List<Object> getTokens() {
        return this.streamObjects;
    }

    public Iterator<Object> getTokenIterator() {
        return new Iterator<Object>() { // from class: org.apache.pdfbox.pdfparser.PDFStreamParser.1
            private Object token;

            private void tryNext() {
                try {
                    if (this.token == null) {
                        this.token = PDFStreamParser.this.parseNextToken();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                tryNext();
                return this.token != null;
            }

            @Override // java.util.Iterator
            public Object next() {
                tryNext();
                Object obj = this.token;
                if (obj == null) {
                    throw new NoSuchElementException();
                }
                this.token = null;
                return obj;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01b1, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:82:0x019e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object parseNextToken() throws IOException {
        boolean z;
        COSUpdateInfo cOSUpdateInfo;
        skipSpaces();
        int peek = this.pdfSource.peek();
        if (((byte) peek) == -1) {
            return null;
        }
        char c = (char) peek;
        switch (c) {
            case '(':
                return parseCOSString();
            case '+':
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(c);
                this.pdfSource.read();
                if (c != '.') {
                    z = true;
                    while (true) {
                        char peek2 = (char) this.pdfSource.peek();
                        if (Character.isDigit(peek2) || (z && peek2 == '.')) {
                            stringBuffer.append(peek2);
                            this.pdfSource.read();
                            if (z && peek2 == '.') {
                            }
                        }
                    }
                    return COSNumber.get(stringBuffer.toString());
                }
                z = false;
                break;
            case '<':
                int read = this.pdfSource.read();
                char peek3 = (char) this.pdfSource.peek();
                this.pdfSource.unread(read);
                if (peek3 == '<') {
                    COSDictionary parseCOSDictionary = parseCOSDictionary();
                    skipSpaces();
                    cOSUpdateInfo = parseCOSDictionary;
                    if (((char) this.pdfSource.peek()) == 's') {
                        cOSUpdateInfo = parseCOSStream(parseCOSDictionary);
                    }
                    return cOSUpdateInfo;
                }
                return parseCOSString();
            case 'B':
                String readString = readString();
                Operator operator = Operator.getOperator(readString);
                if (!readString.equals("BI")) {
                    return operator;
                }
                COSDictionary cOSDictionary = new COSDictionary();
                operator.setImageParameters(cOSDictionary);
                while (true) {
                    Object parseNextToken = parseNextToken();
                    if (parseNextToken instanceof COSName) {
                        cOSDictionary.setItem((COSName) parseNextToken, (COSBase) parseNextToken());
                    } else {
                        operator.setImageData(((Operator) parseNextToken).getImageData());
                        return operator;
                    }
                }
            case 'I':
                String str = "" + ((char) this.pdfSource.read()) + ((char) this.pdfSource.read());
                if (!str.equals("ID")) {
                    throw new IOException("Error: Expected operator 'ID' actual='" + str + "'");
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (isWhitespace()) {
                    this.pdfSource.read();
                }
                int read2 = this.pdfSource.read();
                int read3 = this.pdfSource.read();
                while (true) {
                    if ((read2 != 69 || read3 != 73 || !hasNextSpaceOrReturn() || !hasNoFollowingBinData(this.pdfSource)) && !this.pdfSource.isEOF()) {
                        byteArrayOutputStream.write(read2);
                        int i = read3;
                        read3 = this.pdfSource.read();
                        read2 = i;
                    }
                }
                Operator operator2 = Operator.getOperator("ID");
                operator2.setImageData(byteArrayOutputStream.toByteArray());
                return operator2;
            case 'R':
                String readString2 = readString();
                if (readString2.equals("R")) {
                    cOSUpdateInfo = new COSObject(null);
                    return cOSUpdateInfo;
                }
                return Operator.getOperator(readString2);
            case '[':
                return parseCOSArray();
            case ']':
                this.pdfSource.read();
                return COSNull.NULL;
            case 'f':
            case 't':
                String readString3 = readString();
                if (readString3.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                    return COSBoolean.TRUE;
                }
                if (readString3.equals("false")) {
                    return COSBoolean.FALSE;
                }
                return Operator.getOperator(readString3);
            case 'n':
                String readString4 = readString();
                if (readString4.equals("null")) {
                    return COSNull.NULL;
                }
                return Operator.getOperator(readString4);
            default:
                switch (c) {
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                    case '/':
                        return parseCOSName();
                    default:
                        String readOperator = readOperator();
                        if (readOperator.trim().length() == 0) {
                            return null;
                        }
                        return Operator.getOperator(readOperator);
                }
        }
    }

    private boolean hasNoFollowingBinData(PushbackInputStream pushbackInputStream) throws IOException {
        int read = pushbackInputStream.read(this.binCharTestArr, 0, 10);
        boolean z = true;
        if (read > 0) {
            int i = -1;
            int i2 = -1;
            for (int i3 = 0; i3 < read; i3++) {
                byte b = this.binCharTestArr[i3];
                if (b < 9 || (b > 10 && b < 32 && b != 13)) {
                    z = false;
                    break;
                }
                if (i == -1 && b != 9 && b != 32 && b != 10 && b != 13) {
                    i = i3;
                } else if (i != -1 && i2 == -1 && (b == 9 || b == 32 || b == 10 || b == 13)) {
                    i2 = i3;
                }
            }
            if (read == 10) {
                int i4 = (i == -1 || i2 != -1) ? i2 : 10;
                if (i4 != -1 && i != -1 && i4 - i > 3) {
                    z = false;
                }
            }
            pushbackInputStream.unread(this.binCharTestArr, 0, read);
        }
        if (!z) {
            Log.w("PdfBoxAndroid", "ignoring 'EI' assumed to be in the middle of inline image");
        }
        return z;
    }

    private boolean hasPrecedingAscii85Data(ByteArrayOutputStream byteArrayOutputStream) {
        if (byteArrayOutputStream.size() < 70) {
            return false;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        for (int length = byteArray.length - 1; length >= byteArray.length - 70; length--) {
            if (byteArray[length] < 33 || byteArray[length] > 117) {
                return false;
            }
        }
        return true;
    }

    protected String readOperator() throws IOException {
        skipSpaces();
        StringBuffer stringBuffer = new StringBuffer(4);
        int peek = this.pdfSource.peek();
        while (peek != -1 && !isWhitespace(peek) && !isClosing(peek) && peek != 91 && peek != 60 && peek != 40 && peek != 47 && (peek < 48 || peek > 57)) {
            char read = (char) this.pdfSource.read();
            int peek2 = this.pdfSource.peek();
            stringBuffer.append(read);
            if (read == 'd' && (peek2 == 48 || peek2 == 49)) {
                stringBuffer.append((char) this.pdfSource.read());
                peek = this.pdfSource.peek();
            } else {
                peek = peek2;
            }
        }
        return stringBuffer.toString();
    }

    private boolean hasNextSpaceOrReturn() throws IOException {
        return isSpaceOrReturn(this.pdfSource.peek());
    }
}
