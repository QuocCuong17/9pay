package org.apache.pdfbox.pdfparser;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSObjectKey;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.io.PushBackInputStream;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes5.dex */
public abstract class BaseParser implements Closeable {
    private static final int A = 97;
    protected static final byte ASCII_CR = 13;
    protected static final byte ASCII_LF = 10;
    private static final byte ASCII_NINE = 57;
    private static final byte ASCII_SPACE = 32;
    private static final byte ASCII_ZERO = 48;
    private static final int B = 98;
    private static final int D = 100;
    public static final String DEF = "def";
    private static final int E = 101;
    protected static final String ENDOBJ_STRING = "endobj";
    protected static final String ENDSTREAM_STRING = "endstream";
    private static final String FALSE = "false";
    private static final long GENERATION_NUMBER_THRESHOLD = 65535;
    public static final String ISO_8859_1 = "ISO-8859-1";
    private static final int J = 106;
    private static final int M = 109;
    private static final int N = 110;
    private static final String NULL = "null";
    private static final int O = 111;
    private static final long OBJECT_NUMBER_THRESHOLD = 10000000000L;
    public static final String PROP_PUSHBACK_SIZE = "org.apache.pdfbox.baseParser.pushBackSize";
    private static final int R = 114;
    private static final int S = 115;
    protected static final String STREAM_STRING = "stream";
    private static final int STRMBUFLEN = 2048;
    private static final int T = 116;
    private static final String TRUE = "true";
    protected COSDocument document;
    protected PushBackInputStream pdfSource;
    private final byte[] strmBuf;
    public static final byte[] ENDSTREAM = {101, 110, 100, 115, 116, 114, 101, 97, 109};
    public static final byte[] ENDOBJ = {101, 110, 100, ISOFileInfo.FCI_BYTE, ISOFileInfo.FCP_BYTE, 106};

    private boolean isCR(int i) {
        return 13 == i;
    }

    private static boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    private boolean isLF(int i) {
        return 10 == i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isClosing(int i) {
        return i == 93;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDigit(int i) {
        return i >= 48 && i <= 57;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEndOfName(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t' || c == '>' || c == '<' || c == '[' || c == '/' || c == ']' || c == ')' || c == '(';
    }

    protected boolean isSpace(int i) {
        return 32 == i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isWhitespace(int i) {
        return i == 0 || i == 9 || i == 12 || i == 10 || i == 13 || i == 32;
    }

    public BaseParser() {
        this.strmBuf = new byte[2048];
    }

    public BaseParser(InputStream inputStream) throws IOException {
        this.strmBuf = new byte[2048];
        int i = 65536;
        try {
            i = Integer.getInteger(PROP_PUSHBACK_SIZE, 65536).intValue();
        } catch (SecurityException unused) {
        }
        this.pdfSource = new PushBackInputStream(new BufferedInputStream(inputStream, 16384), i);
    }

    protected BaseParser(byte[] bArr) throws IOException {
        this(new ByteArrayInputStream(bArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final COSStream createCOSStream(COSDictionary cOSDictionary) {
        COSDocument cOSDocument = this.document;
        if (cOSDocument != null) {
            return cOSDocument.createCOSStream(cOSDictionary);
        }
        return null;
    }

    private COSBase parseCOSDictionaryValue() throws IOException {
        long offset = this.pdfSource.getOffset();
        COSBase parseDirObject = parseDirObject();
        skipSpaces();
        char peek = (char) this.pdfSource.peek();
        if (peek < '0' || peek > '9') {
            return parseDirObject;
        }
        long offset2 = this.pdfSource.getOffset();
        COSBase parseDirObject2 = parseDirObject();
        skipSpaces();
        readExpectedChar(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        if (!(parseDirObject instanceof COSInteger)) {
            throw new IOException("expected number, actual=" + parseDirObject + " at offset " + offset);
        }
        if (!(parseDirObject2 instanceof COSInteger)) {
            throw new IOException("expected number, actual=" + parseDirObject + " at offset " + offset2);
        }
        return this.document.getObjectFromPool(new COSObjectKey(((COSInteger) parseDirObject).longValue(), ((COSInteger) parseDirObject2).intValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00cb, code lost:
    
        r11.pdfSource.unread(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public COSDictionary parseCOSDictionary() throws IOException {
        int read;
        readExpectedChar(Typography.less);
        readExpectedChar(Typography.less);
        skipSpaces();
        COSDictionary cOSDictionary = new COSDictionary();
        boolean z = false;
        while (!z) {
            skipSpaces();
            char peek = (char) this.pdfSource.peek();
            if (peek == '>') {
                z = true;
            } else {
                if (peek != '/') {
                    Log.w("PdfBoxAndroid", "Invalid dictionary, found: '" + peek + "' but expected: '/'");
                    int read2 = this.pdfSource.read();
                    while (read2 != -1 && read2 != 47 && read2 != 62) {
                        if (read2 == 101 && this.pdfSource.read() == 110 && (read = this.pdfSource.read()) == 100) {
                            boolean z2 = read == 115 && this.pdfSource.read() == 116 && this.pdfSource.read() == 114 && this.pdfSource.read() == 101 && this.pdfSource.read() == 97 && this.pdfSource.read() == 109;
                            boolean z3 = !z2 && read == 111 && this.pdfSource.read() == 98 && this.pdfSource.read() == 106;
                            if (z2 || z3) {
                                return cOSDictionary;
                            }
                        }
                        read2 = this.pdfSource.read();
                    }
                    return cOSDictionary;
                }
                COSName parseCOSName = parseCOSName();
                COSBase parseCOSDictionaryValue = parseCOSDictionaryValue();
                skipSpaces();
                if (((char) this.pdfSource.peek()) == 'd') {
                    String readString = readString();
                    if (!readString.equals(DEF)) {
                        this.pdfSource.unread(readString.getBytes("ISO-8859-1"));
                    } else {
                        skipSpaces();
                    }
                }
                if (parseCOSDictionaryValue == null) {
                    Log.w("PdfBoxAndroid", "Bad Dictionary Declaration " + this.pdfSource);
                } else {
                    parseCOSDictionaryValue.setDirect(true);
                    cOSDictionary.setItem(parseCOSName, parseCOSDictionaryValue);
                }
            }
        }
        readExpectedChar(Typography.greater);
        readExpectedChar(Typography.greater);
        return cOSDictionary;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public COSStream parseCOSStream(COSDictionary cOSDictionary) throws IOException {
        boolean z;
        COSStream createCOSStream = createCOSStream(cOSDictionary);
        OutputStream outputStream = null;
        try {
            readExpectedString(STREAM_STRING);
            int read = this.pdfSource.read();
            while (32 == read) {
                read = this.pdfSource.read();
            }
            if (13 == read) {
                int read2 = this.pdfSource.read();
                if (10 != read2) {
                    this.pdfSource.unread(read2);
                }
            } else if (10 != read) {
                this.pdfSource.unread(read);
            }
            COSBase item = cOSDictionary.getItem(COSName.LENGTH);
            outputStream = createCOSStream.createFilteredStream(item);
            int intValue = item instanceof COSNumber ? ((COSNumber) item).intValue() : -1;
            if (intValue == -1) {
                readUntilEndStream(new EndstreamOutputStream(outputStream));
            } else {
                int i = intValue;
                while (i > 0) {
                    int read3 = this.pdfSource.read(this.strmBuf, 0, Math.min(i, 2048));
                    if (read3 == -1) {
                        break;
                    }
                    outputStream.write(this.strmBuf, 0, read3);
                    i -= read3;
                }
                int read4 = this.pdfSource.read(this.strmBuf, 0, 20);
                if (read4 > 0) {
                    int i2 = 0;
                    for (int i3 = 0; i3 < read4; i3++) {
                        int i4 = this.strmBuf[i3] & 255;
                        byte[] bArr = ENDSTREAM;
                        if (i4 == bArr[i2]) {
                            i2++;
                            if (i2 >= bArr.length) {
                                z = true;
                                break;
                            }
                        } else if (i2 > 0 || !isWhitespace(i4)) {
                            break;
                        }
                    }
                    z = false;
                    this.pdfSource.unread(this.strmBuf, 0, read4);
                    if (!z) {
                        Log.w("PdfBoxAndroid", "Specified stream length " + intValue + " is wrong. Fall back to reading stream until 'endstream'.");
                        outputStream.flush();
                        InputStream filteredStream = createCOSStream.getFilteredStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(intValue);
                        IOUtils.copy(filteredStream, byteArrayOutputStream);
                        IOUtils.closeQuietly(filteredStream);
                        try {
                            this.pdfSource.unread(byteArrayOutputStream.toByteArray());
                            IOUtils.closeQuietly(outputStream);
                            outputStream = createCOSStream.createFilteredStream();
                            readUntilEndStream(new EndstreamOutputStream(outputStream));
                        } catch (IOException e) {
                            throw new IOException("Could not push back " + byteArrayOutputStream.size() + " bytes in order to reparse stream. Try increasing push back buffer using system property " + PROP_PUSHBACK_SIZE, e);
                        }
                    }
                }
            }
            skipSpaces();
            String readString = readString();
            if (!readString.equals(ENDSTREAM_STRING)) {
                if (readString.startsWith(ENDOBJ_STRING)) {
                    this.pdfSource.unread(readString.getBytes("ISO-8859-1"));
                } else if (readString.startsWith(ENDSTREAM_STRING)) {
                    this.pdfSource.unread(readString.substring(9, readString.length()).getBytes("ISO-8859-1"));
                } else {
                    readUntilEndStream(new EndstreamOutputStream(outputStream));
                    readExpectedString(ENDSTREAM_STRING);
                }
            }
            return createCOSStream;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void readUntilEndStream(OutputStream outputStream) throws IOException {
        int i;
        byte b;
        byte[] bArr = ENDSTREAM;
        int i2 = 0;
        while (true) {
            int read = this.pdfSource.read(this.strmBuf, i2, 2048 - i2);
            if (read <= 0) {
                break;
            }
            int i3 = read + i2;
            int i4 = i3 - 5;
            int i5 = i2;
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                if (i5 != 0 || (i = i2 + 5) >= i4 || ((b = this.strmBuf[i]) <= 116 && b >= 97)) {
                    byte b2 = this.strmBuf[i2];
                    if (b2 == bArr[i5]) {
                        i5++;
                        if (i5 == bArr.length) {
                            i2++;
                            break;
                        }
                    } else {
                        if (i5 == 3) {
                            bArr = ENDOBJ;
                            if (b2 == bArr[i5]) {
                                i5++;
                            }
                        }
                        i5 = b2 == 101 ? 1 : (b2 == 110 && i5 == 7) ? 2 : 0;
                        bArr = ENDSTREAM;
                    }
                } else {
                    i2 = i;
                }
                i2++;
            }
            int max = Math.max(0, i2 - i5);
            if (max > 0) {
                outputStream.write(this.strmBuf, 0, max);
            }
            if (i5 == bArr.length) {
                this.pdfSource.unread(this.strmBuf, max, i3 - max);
                break;
            } else {
                System.arraycopy(bArr, 0, this.strmBuf, 0, i5);
                i2 = i5;
            }
        }
        outputStream.flush();
    }

    private int checkForMissingCloseParen(int i) throws IOException {
        byte[] bArr = new byte[3];
        int read = this.pdfSource.read(bArr);
        if ((read == 3 && bArr[0] == 13 && bArr[1] == 10 && bArr[2] == 47) || (bArr[0] == 13 && bArr[1] == 47)) {
            i = 0;
        }
        if (read > 0) {
            this.pdfSource.unread(bArr, 0, read);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:40:0x0067. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0110 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0017 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public COSString parseCOSString() throws IOException {
        char read = (char) this.pdfSource.read();
        if (read != '(') {
            if (read == '<') {
                return parseCOSHexString();
            }
            throw new IOException("parseCOSString string should start with '(' or '<' and not '" + read + "' " + this.pdfSource);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read2 = this.pdfSource.read();
        int i = 1;
        while (i > 0 && read2 != -1) {
            char c = (char) read2;
            if (c == ')') {
                i = checkForMissingCloseParen(i - 1);
                if (i != 0) {
                    byteArrayOutputStream.write(c);
                }
            } else if (c == '(') {
                i++;
                byteArrayOutputStream.write(c);
            } else if (c == '\\') {
                char read3 = (char) this.pdfSource.read();
                if (read3 != '\n' && read3 != '\r') {
                    if (read3 != '\\') {
                        if (read3 == 'b') {
                            byteArrayOutputStream.write(8);
                        } else if (read3 == 'f') {
                            byteArrayOutputStream.write(12);
                        } else if (read3 == 'n') {
                            byteArrayOutputStream.write(10);
                        } else if (read3 == 'r') {
                            byteArrayOutputStream.write(13);
                        } else if (read3 == 't') {
                            byteArrayOutputStream.write(9);
                        } else if (read3 != '(') {
                            if (read3 == ')') {
                                i = checkForMissingCloseParen(i);
                                if (i != 0) {
                                    byteArrayOutputStream.write(read3);
                                } else {
                                    byteArrayOutputStream.write(92);
                                }
                            } else {
                                switch (read3) {
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                        StringBuffer stringBuffer = new StringBuffer();
                                        stringBuffer.append(read3);
                                        read2 = this.pdfSource.read();
                                        char c2 = (char) read2;
                                        if (c2 >= '0' && c2 <= '7') {
                                            stringBuffer.append(c2);
                                            read2 = this.pdfSource.read();
                                            char c3 = (char) read2;
                                            if (c3 >= '0' && c3 <= '7') {
                                                stringBuffer.append(c3);
                                                read2 = -2;
                                            }
                                        }
                                        try {
                                            byteArrayOutputStream.write(Integer.parseInt(stringBuffer.toString(), 8));
                                            break;
                                        } catch (NumberFormatException e) {
                                            throw new IOException("Error: Expected octal character, actual='" + ((Object) stringBuffer) + "'", e);
                                        }
                                        break;
                                    default:
                                        byteArrayOutputStream.write(read3);
                                        break;
                                }
                            }
                        }
                    }
                    byteArrayOutputStream.write(read3);
                } else {
                    read2 = this.pdfSource.read();
                    while (isEOL(read2) && read2 != -1) {
                        read2 = this.pdfSource.read();
                    }
                }
                if (read2 != -2) {
                    read2 = this.pdfSource.read();
                }
            } else {
                byteArrayOutputStream.write(c);
            }
            read2 = -2;
            if (read2 != -2) {
            }
        }
        if (read2 != -1) {
            this.pdfSource.unread(read2);
        }
        return new COSString(byteArrayOutputStream.toByteArray());
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x005d, code lost:
    
        return org.apache.pdfbox.cos.COSString.parseHex(r0.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private COSString parseCOSHexString() throws IOException {
        int read;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read2 = this.pdfSource.read();
            char c = (char) read2;
            if (!isHexDigit(c)) {
                if (read2 == 62) {
                    break;
                }
                if (read2 < 0) {
                    throw new IOException("Missing closing bracket for hex string. Reached EOS.");
                }
                if (read2 != 32 && read2 != 10 && read2 != 9 && read2 != 13 && read2 != 8 && read2 != 12) {
                    if (sb.length() % 2 != 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    do {
                        read = this.pdfSource.read();
                        if (read == 62) {
                            break;
                        }
                    } while (read >= 0);
                    if (read < 0) {
                        throw new IOException("Missing closing bracket for hex string. Reached EOS.");
                    }
                }
            } else {
                sb.append(c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b3, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public COSArray parseCOSArray() throws IOException {
        readExpectedChar('[');
        COSArray cOSArray = new COSArray();
        skipSpaces();
        while (true) {
            int peek = this.pdfSource.peek();
            if (peek <= 0 || ((char) peek) == ']') {
                break;
            }
            COSBase parseDirObject = parseDirObject();
            if (parseDirObject instanceof COSObject) {
                if (cOSArray.get(cOSArray.size() - 1) instanceof COSInteger) {
                    COSInteger cOSInteger = (COSInteger) cOSArray.remove(cOSArray.size() - 1);
                    if (cOSArray.get(cOSArray.size() - 1) instanceof COSInteger) {
                        parseDirObject = this.document.getObjectFromPool(new COSObjectKey(((COSInteger) cOSArray.remove(cOSArray.size() - 1)).longValue(), cOSInteger.intValue()));
                    }
                }
                parseDirObject = null;
            }
            if (parseDirObject != null) {
                cOSArray.add(parseDirObject);
            } else {
                Log.w("PdfBoxAndroid", "Corrupt object reference at offset " + this.pdfSource.getOffset());
                String readString = readString();
                this.pdfSource.unread(readString.getBytes("ISO-8859-1"));
                if (ENDOBJ_STRING.equals(readString) || ENDSTREAM_STRING.equals(readString)) {
                    break;
                }
            }
            skipSpaces();
        }
        this.pdfSource.read();
        skipSpaces();
        return cOSArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5, types: [char] */
    public COSName parseCOSName() throws IOException {
        readExpectedChar('/');
        StringBuilder sb = new StringBuilder();
        int read = this.pdfSource.read();
        while (read != -1) {
            char c = (char) read;
            if (c == '#') {
                read = (char) this.pdfSource.read();
                char read2 = (char) this.pdfSource.read();
                if (isHexDigit(read) && isHexDigit(read2)) {
                    String str = "" + ((char) read) + read2;
                    try {
                        sb.append((char) Integer.parseInt(str, 16));
                        read = this.pdfSource.read();
                    } catch (NumberFormatException e) {
                        throw new IOException("Error: expected hex number, actual='" + str + "'", e);
                    }
                } else {
                    this.pdfSource.unread(read2);
                    sb.append(c);
                }
            } else {
                if (isEndOfName(c)) {
                    break;
                }
                sb.append(c);
                read = this.pdfSource.read();
            }
        }
        if (read != -1) {
            this.pdfSource.unread(read);
        }
        return COSName.getPDFName(sb.toString());
    }

    protected COSBoolean parseBoolean() throws IOException {
        char peek = (char) this.pdfSource.peek();
        if (peek == 't') {
            String str = new String(this.pdfSource.readFully(4), "ISO-8859-1");
            if (!str.equals("true")) {
                throw new IOException("Error parsing boolean: expected='true' actual='" + str + "' at offset " + this.pdfSource.getOffset());
            }
            return COSBoolean.TRUE;
        }
        if (peek == 'f') {
            String str2 = new String(this.pdfSource.readFully(5), "ISO-8859-1");
            if (!str2.equals(FALSE)) {
                throw new IOException("Error parsing boolean: expected='true' actual='" + str2 + "' at offset " + this.pdfSource.getOffset());
            }
            return COSBoolean.FALSE;
        }
        throw new IOException("Error parsing boolean expected='t or f' actual='" + peek + "' at offset " + this.pdfSource.getOffset());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public COSBase parseDirObject() throws IOException {
        skipSpaces();
        char peek = (char) this.pdfSource.peek();
        if (peek == '(') {
            return parseCOSString();
        }
        if (peek == '/') {
            return parseCOSName();
        }
        if (peek == '<') {
            int read = this.pdfSource.read();
            char peek2 = (char) this.pdfSource.peek();
            this.pdfSource.unread(read);
            if (peek2 == '<') {
                COSDictionary parseCOSDictionary = parseCOSDictionary();
                skipSpaces();
                return parseCOSDictionary;
            }
            return parseCOSString();
        }
        if (peek == 'R') {
            this.pdfSource.read();
            return new COSObject(null);
        }
        if (peek == '[') {
            return parseCOSArray();
        }
        if (peek == 'f') {
            String str = new String(this.pdfSource.readFully(5), "ISO-8859-1");
            if (str.equals(FALSE)) {
                return COSBoolean.FALSE;
            }
            throw new IOException("expected false actual='" + str + "' " + this.pdfSource);
        }
        if (peek == 'n') {
            readExpectedString(NULL);
            return COSNull.NULL;
        }
        if (peek == 't') {
            String str2 = new String(this.pdfSource.readFully(4), "ISO-8859-1");
            if (str2.equals("true")) {
                return COSBoolean.TRUE;
            }
            throw new IOException("expected true actual='" + str2 + "' " + this.pdfSource);
        }
        if (peek == 65535) {
            return null;
        }
        if (Character.isDigit(peek) || peek == '-' || peek == '+' || peek == '.') {
            StringBuilder sb = new StringBuilder();
            int read2 = this.pdfSource.read();
            while (true) {
                char c = (char) read2;
                if (!Character.isDigit(c) && c != '-' && c != '+' && c != '.' && c != 'E' && c != 'e') {
                    break;
                }
                sb.append(c);
                read2 = this.pdfSource.read();
            }
            if (read2 != -1) {
                this.pdfSource.unread(read2);
            }
            return COSNumber.get(sb.toString());
        }
        String readString = readString();
        if (readString == null || readString.length() == 0) {
            int peek3 = this.pdfSource.peek();
            throw new IOException("Unknown dir object c='" + peek + "' cInt=" + ((int) peek) + " peek='" + ((char) peek3) + "' peekInt=" + peek3 + " " + this.pdfSource.getOffset());
        }
        if (!ENDOBJ_STRING.equals(readString) && !ENDSTREAM_STRING.equals(readString)) {
            return null;
        }
        this.pdfSource.unread(readString.getBytes("ISO-8859-1"));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String readString() throws IOException {
        skipSpaces();
        StringBuilder sb = new StringBuilder();
        int read = this.pdfSource.read();
        while (true) {
            char c = (char) read;
            if (isEndOfName(c) || read == -1) {
                break;
            }
            sb.append(c);
            read = this.pdfSource.read();
        }
        if (read != -1) {
            this.pdfSource.unread(read);
        }
        return sb.toString();
    }

    protected void readExpectedString(String str) throws IOException {
        readExpectedString(str.toCharArray(), false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void readExpectedString(char[] cArr, boolean z) throws IOException {
        skipSpaces();
        for (char c : cArr) {
            if (this.pdfSource.read() != c) {
                throw new IOException("Expected string '" + new String(cArr) + "' but missed at character '" + c + "' at offset " + this.pdfSource.getOffset());
            }
        }
        skipSpaces();
    }

    protected void readExpectedChar(char c) throws IOException {
        char read = (char) this.pdfSource.read();
        if (read == c) {
            return;
        }
        throw new IOException("expected='" + c + "' actual='" + read + "' at offset " + this.pdfSource.getOffset());
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        r4.pdfSource.unread(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected String readString(int i) throws IOException {
        skipSpaces();
        int read = this.pdfSource.read();
        StringBuilder sb = new StringBuilder(i);
        while (!isWhitespace(read) && !isClosing(read) && read != -1 && sb.length() < i && read != 91 && read != 60 && read != 40 && read != 47) {
            sb.append((char) read);
            read = this.pdfSource.read();
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isClosing() throws IOException {
        return isClosing(this.pdfSource.peek());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String readLine() throws IOException {
        int read;
        if (this.pdfSource.isEOF()) {
            throw new IOException("Error: End-of-File, expected line");
        }
        StringBuilder sb = new StringBuilder(11);
        while (true) {
            read = this.pdfSource.read();
            if (read == -1 || isEOL(read)) {
                break;
            }
            sb.append((char) read);
        }
        if (isCR(read) && isLF(this.pdfSource.peek())) {
            this.pdfSource.read();
        }
        return sb.toString();
    }

    protected boolean isEOL() throws IOException {
        return isEOL(this.pdfSource.peek());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEOL(int i) {
        return isLF(i) || isCR(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isWhitespace() throws IOException {
        return isWhitespace(this.pdfSource.peek());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSpace() throws IOException {
        return isSpace(this.pdfSource.peek());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDigit() throws IOException {
        return isDigit(this.pdfSource.peek());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isString(byte[] bArr) throws IOException {
        if (this.pdfSource.peek() != bArr[0]) {
            return false;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int read = this.pdfSource.read(bArr2, 0, length);
        while (read < length) {
            int read2 = this.pdfSource.read(bArr2, read, length - read);
            if (read2 < 0) {
                break;
            }
            read += read2;
        }
        boolean equals = Arrays.equals(bArr, bArr2);
        this.pdfSource.unread(bArr2, 0, read);
        return equals;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isString(char[] cArr) throws IOException {
        long offset = this.pdfSource.getOffset();
        boolean z = true;
        for (char c : cArr) {
            if (this.pdfSource.read() != c) {
                z = false;
            }
        }
        this.pdfSource.seek(offset);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void skipSpaces() throws IOException {
        int read = this.pdfSource.read();
        while (true) {
            if (!isWhitespace(read) && read != 37) {
                break;
            }
            if (read == 37) {
                read = this.pdfSource.read();
                while (!isEOL(read) && read != -1) {
                    read = this.pdfSource.read();
                }
            } else {
                read = this.pdfSource.read();
            }
        }
        if (read != -1) {
            this.pdfSource.unread(read);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readObjectNumber() throws IOException {
        int readInt = readInt();
        if (readInt >= 0 && readInt < 10000000000L) {
            return readInt;
        }
        throw new IOException("Object Number '" + readInt + "' has more than 10 digits or is negative");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readGenerationNumber() throws IOException {
        int readInt = readInt();
        if (readInt >= 0 && readInt <= 65535) {
            return readInt;
        }
        throw new IOException("Generation Number '" + readInt + "' has more than 5 digits");
    }

    protected int readInt() throws IOException {
        skipSpaces();
        StringBuilder readStringNumber = readStringNumber();
        try {
            return Integer.parseInt(readStringNumber.toString());
        } catch (NumberFormatException e) {
            this.pdfSource.unread(readStringNumber.toString().getBytes("ISO-8859-1"));
            throw new IOException("Error: Expected an integer type at offset " + this.pdfSource.getOffset(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long readLong() throws IOException {
        skipSpaces();
        StringBuilder readStringNumber = readStringNumber();
        try {
            return Long.parseLong(readStringNumber.toString());
        } catch (NumberFormatException e) {
            this.pdfSource.unread(readStringNumber.toString().getBytes("ISO-8859-1"));
            throw new IOException("Error: Expected a long type at offset " + this.pdfSource.getOffset() + ", instead got '" + ((Object) readStringNumber) + "'", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002f, code lost:
    
        r4.pdfSource.unread(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final StringBuilder readStringNumber() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = this.pdfSource.read();
            if (read == 32 || read == 10 || read == 13 || read == 60 || read == 91 || read == 40 || read == 0 || read == -1) {
                break;
            }
            sb.append((char) read);
        }
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void skipToNextObj() throws IOException {
        int read;
        byte[] bArr = new byte[16];
        Pattern compile = Pattern.compile("\\d+\\s+\\d+\\s+obj.*", 32);
        while (!this.pdfSource.isEOF() && (read = this.pdfSource.read(bArr)) >= 1) {
            String str = new String(bArr, "US-ASCII");
            if (str.startsWith("trailer") || str.startsWith("xref") || str.startsWith("startxref") || str.startsWith(STREAM_STRING) || compile.matcher(str).matches()) {
                this.pdfSource.unread(bArr);
                return;
            }
            this.pdfSource.unread(bArr, 1, read - 1);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        PushBackInputStream pushBackInputStream = this.pdfSource;
        if (pushBackInputStream != null) {
            pushBackInputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public COSObjectKey parseObjectKey(boolean z) throws IOException {
        int readObjectNumber;
        long j;
        int i;
        long j2 = -1;
        boolean z2 = false;
        try {
        } catch (IOException unused) {
            readObjectNumber = readObjectNumber();
        }
        if (((char) this.pdfSource.peek()) != '<') {
            readObjectNumber = readObjectNumber();
            j = readObjectNumber;
            if (z2) {
            }
            return new COSObjectKey(j2, i);
        }
        z2 = true;
        j = -1;
        if (z2) {
            skipSpaces();
            int readGenerationNumber = readGenerationNumber();
            String readString = readString(3);
            if (!readString.equals("obj") && z && readString.equals("o")) {
                throw new IOException("expected='obj' actual='" + readString + "' " + this.pdfSource);
            }
            i = readGenerationNumber;
            j2 = j;
        } else {
            i = -1;
        }
        return new COSObjectKey(j2, i);
    }
}
