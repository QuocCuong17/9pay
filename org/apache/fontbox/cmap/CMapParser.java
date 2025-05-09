package org.apache.fontbox.cmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

/* loaded from: classes5.dex */
public class CMapParser {
    private static final String MARK_END_OF_ARRAY = "]";
    private static final String MARK_END_OF_DICTIONARY = ">>";
    private final byte[] tokenParserByteBuffer = new byte[512];

    private static boolean isDelimiter(int i) {
        return i == 37 || i == 47 || i == 60 || i == 62 || i == 91 || i == 93 || i == 123 || i == 125 || i == 40 || i == 41;
    }

    private static boolean isWhitespaceOrEOF(int i) {
        return i == -1 || i == 32 || i == 13 || i == 10;
    }

    public CMap parse(File file) throws IOException {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            CMap parse = parse(fileInputStream);
            fileInputStream.close();
            return parse;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public CMap parsePredefined(String str) throws IOException {
        InputStream inputStream;
        try {
            inputStream = getExternalCMap(str);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            CMap parse = parse(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return parse;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public CMap parse(InputStream inputStream) throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        CMap cMap = new CMap();
        Object obj = null;
        while (true) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken == null) {
                break;
            }
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("usecmap")) {
                    parseUsecmap(obj, cMap);
                } else {
                    if (operator.op.equals("endcmap")) {
                        break;
                    }
                    if (operator.op.equals("begincodespacerange")) {
                        parseBegincodespacerange(obj, pushbackInputStream, cMap);
                    } else if (operator.op.equals("beginbfchar")) {
                        parseBeginbfchar(obj, pushbackInputStream, cMap);
                    } else if (operator.op.equals("beginbfrange")) {
                        parseBeginbfrange(obj, pushbackInputStream, cMap);
                    } else if (operator.op.equals("begincidchar")) {
                        parseBegincidchar(obj, pushbackInputStream, cMap);
                    } else if (operator.op.equals("begincidrange")) {
                        parseBegincidrange(obj, pushbackInputStream, cMap);
                    }
                }
            } else if (parseNextToken instanceof LiteralName) {
                parseLiteralName(parseNextToken, pushbackInputStream, cMap);
            }
            obj = parseNextToken;
        }
        return cMap;
    }

    private void parseUsecmap(Object obj, CMap cMap) throws IOException {
        cMap.useCmap(parse(getExternalCMap(((LiteralName) obj).name)));
    }

    private void parseLiteralName(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        LiteralName literalName = (LiteralName) obj;
        if ("WMode".equals(literalName.name)) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Integer) {
                cMap.setWMode(((Integer) parseNextToken).intValue());
                return;
            }
            return;
        }
        if ("CMapName".equals(literalName.name)) {
            Object parseNextToken2 = parseNextToken(pushbackInputStream);
            if (parseNextToken2 instanceof LiteralName) {
                cMap.setName(((LiteralName) parseNextToken2).name);
                return;
            }
            return;
        }
        if ("CMapVersion".equals(literalName.name)) {
            Object parseNextToken3 = parseNextToken(pushbackInputStream);
            if (parseNextToken3 instanceof Number) {
                cMap.setVersion(parseNextToken3.toString());
                return;
            } else {
                if (parseNextToken3 instanceof String) {
                    cMap.setVersion((String) parseNextToken3);
                    return;
                }
                return;
            }
        }
        if ("CMapType".equals(literalName.name)) {
            Object parseNextToken4 = parseNextToken(pushbackInputStream);
            if (parseNextToken4 instanceof Integer) {
                cMap.setType(((Integer) parseNextToken4).intValue());
                return;
            }
            return;
        }
        if ("Registry".equals(literalName.name)) {
            Object parseNextToken5 = parseNextToken(pushbackInputStream);
            if (parseNextToken5 instanceof String) {
                cMap.setRegistry((String) parseNextToken5);
                return;
            }
            return;
        }
        if ("Ordering".equals(literalName.name)) {
            Object parseNextToken6 = parseNextToken(pushbackInputStream);
            if (parseNextToken6 instanceof String) {
                cMap.setOrdering((String) parseNextToken6);
                return;
            }
            return;
        }
        if ("Supplement".equals(literalName.name)) {
            Object parseNextToken7 = parseNextToken(pushbackInputStream);
            if (parseNextToken7 instanceof Integer) {
                cMap.setSupplement(((Integer) parseNextToken7).intValue());
            }
        }
    }

    private void parseBegincodespacerange(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("endcodespacerange")) {
                    return;
                }
                throw new IOException("Error : ~codespacerange contains an unexpected operator : " + operator.op);
            }
            byte[] bArr = (byte[]) parseNextToken(pushbackInputStream);
            CodespaceRange codespaceRange = new CodespaceRange();
            codespaceRange.setStart((byte[]) parseNextToken);
            codespaceRange.setEnd(bArr);
            cMap.addCodespaceRange(codespaceRange);
        }
    }

    private void parseBeginbfchar(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("endbfchar")) {
                    return;
                }
                throw new IOException("Error : ~bfchar contains an unexpected operator : " + operator.op);
            }
            byte[] bArr = (byte[]) parseNextToken;
            Object parseNextToken2 = parseNextToken(pushbackInputStream);
            if (parseNextToken2 instanceof byte[]) {
                cMap.addCharMapping(bArr, createStringFromBytes((byte[]) parseNextToken2));
            } else {
                if (!(parseNextToken2 instanceof LiteralName)) {
                    throw new IOException("Error parsing CMap beginbfchar, expected{COSString or COSName} and not " + parseNextToken2);
                }
                cMap.addCharMapping(bArr, ((LiteralName) parseNextToken2).name);
            }
        }
    }

    private void parseBegincidrange(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        int intValue = ((Integer) obj).intValue();
        for (int i = 0; i < intValue; i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("endcidrange")) {
                    return;
                }
                throw new IOException("Error : ~cidrange contains an unexpected operator : " + operator.op);
            }
            byte[] bArr = (byte[]) parseNextToken;
            int createIntFromBytes = createIntFromBytes(bArr);
            byte[] bArr2 = (byte[]) parseNextToken(pushbackInputStream);
            int createIntFromBytes2 = createIntFromBytes(bArr2);
            int intValue2 = ((Integer) parseNextToken(pushbackInputStream)).intValue();
            if (bArr.length > 2 || bArr2.length > 2) {
                int i2 = (createIntFromBytes2 + intValue2) - createIntFromBytes;
                while (intValue2 <= i2) {
                    cMap.addCIDMapping(intValue2, createIntFromBytes(bArr));
                    increment(bArr);
                    intValue2++;
                }
            } else {
                cMap.addCIDRange((char) createIntFromBytes, (char) createIntFromBytes2, intValue2);
            }
        }
    }

    private void parseBegincidchar(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("endcidchar")) {
                    return;
                }
                throw new IOException("Error : ~cidchar contains an unexpected operator : " + operator.op);
            }
            cMap.addCIDMapping(((Integer) parseNextToken(pushbackInputStream)).intValue(), createIntFromBytes((byte[]) parseNextToken));
        }
    }

    private void parseBeginbfrange(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        byte[] bArr;
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("endbfrange")) {
                    return;
                }
                throw new IOException("Error : ~bfrange contains an unexpected operator : " + operator.op);
            }
            byte[] bArr2 = (byte[]) parseNextToken;
            byte[] bArr3 = (byte[]) parseNextToken(pushbackInputStream);
            Object parseNextToken2 = parseNextToken(pushbackInputStream);
            List list = null;
            if (parseNextToken2 instanceof List) {
                list = (List) parseNextToken2;
                bArr = (byte[]) list.get(0);
            } else {
                bArr = (byte[]) parseNextToken2;
            }
            boolean z = false;
            int i2 = 0;
            while (!z) {
                if (compare(bArr2, bArr3) >= 0) {
                    z = true;
                }
                cMap.addCharMapping(bArr2, createStringFromBytes(bArr));
                increment(bArr2);
                if (list == null) {
                    increment(bArr);
                } else {
                    i2++;
                    if (i2 < list.size()) {
                        bArr = (byte[]) list.get(i2);
                    }
                }
            }
        }
    }

    protected InputStream getExternalCMap(String str) throws IOException {
        if (PDFBoxResourceLoader.isReady()) {
            return PDFBoxResourceLoader.getStream("/org/apache/fontbox/resources/cmap/" + str);
        }
        URL resource = getClass().getResource("/org/apache/fontbox/resources/cmap/" + str);
        if (resource == null) {
            throw new IOException("Error: Could not find referenced cmap stream " + str);
        }
        return resource.openStream();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Object parseNextToken(PushbackInputStream pushbackInputStream) throws IOException {
        int i;
        String stringBuffer;
        int read = pushbackInputStream.read();
        while (true) {
            if (read != 9 && read != 32 && read != 13 && read != 10) {
                break;
            }
            read = pushbackInputStream.read();
        }
        if (read == -1) {
            return null;
        }
        if (read == 37) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append((char) read);
            readUntilEndOfLine(pushbackInputStream, stringBuffer2);
            return stringBuffer2.toString();
        }
        if (read == 40) {
            StringBuffer stringBuffer3 = new StringBuffer();
            int read2 = pushbackInputStream.read();
            while (read2 != -1 && read2 != 41) {
                stringBuffer3.append((char) read2);
                read2 = pushbackInputStream.read();
            }
            return stringBuffer3.toString();
        }
        if (read == 60) {
            int read3 = pushbackInputStream.read();
            if (read3 == 60) {
                HashMap hashMap = new HashMap();
                Object parseNextToken = parseNextToken(pushbackInputStream);
                while ((parseNextToken instanceof LiteralName) && parseNextToken != MARK_END_OF_DICTIONARY) {
                    hashMap.put(((LiteralName) parseNextToken).name, parseNextToken(pushbackInputStream));
                    parseNextToken = parseNextToken(pushbackInputStream);
                }
                return hashMap;
            }
            int i2 = 16;
            int i3 = -1;
            while (read3 != -1 && read3 != 62) {
                if (read3 < 48 || read3 > 57) {
                    int i4 = 65;
                    if (read3 < 65 || read3 > 70) {
                        i4 = 97;
                        if (read3 < 97 || read3 > 102) {
                            if (isWhitespaceOrEOF(read3)) {
                                read3 = pushbackInputStream.read();
                            } else {
                                throw new IOException("Error: expected hex character and not " + ((char) read3) + ":" + read3);
                            }
                        }
                    }
                    i = (read3 + 10) - i4;
                } else {
                    i = read3 - 48;
                }
                int i5 = i * i2;
                if (i2 == 16) {
                    i3++;
                    this.tokenParserByteBuffer[i3] = 0;
                    i2 = 1;
                } else {
                    i2 = 16;
                }
                byte[] bArr = this.tokenParserByteBuffer;
                bArr[i3] = (byte) (bArr[i3] + i5);
                read3 = pushbackInputStream.read();
            }
            int i6 = i3 + 1;
            byte[] bArr2 = new byte[i6];
            System.arraycopy(this.tokenParserByteBuffer, 0, bArr2, 0, i6);
            return bArr2;
        }
        if (read == 62) {
            if (pushbackInputStream.read() == 62) {
                return MARK_END_OF_DICTIONARY;
            }
            throw new IOException("Error: expected the end of a dictionary.");
        }
        if (read == 91) {
            ArrayList arrayList = new ArrayList();
            Object parseNextToken2 = parseNextToken(pushbackInputStream);
            while (parseNextToken2 != null && parseNextToken2 != MARK_END_OF_ARRAY) {
                arrayList.add(parseNextToken2);
                parseNextToken2 = parseNextToken(pushbackInputStream);
            }
            return arrayList;
        }
        if (read == 93) {
            return MARK_END_OF_ARRAY;
        }
        switch (read) {
            case 47:
                StringBuffer stringBuffer4 = new StringBuffer();
                int read4 = pushbackInputStream.read();
                while (!isWhitespaceOrEOF(read4) && !isDelimiter(read4)) {
                    stringBuffer4.append((char) read4);
                    read4 = pushbackInputStream.read();
                }
                if (isDelimiter(read4)) {
                    pushbackInputStream.unread(read4);
                }
                return new LiteralName(stringBuffer4.toString());
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append((char) read);
                int read5 = pushbackInputStream.read();
                while (!isWhitespaceOrEOF(read5)) {
                    char c = (char) read5;
                    if (Character.isDigit(c) || read5 == 46) {
                        stringBuffer5.append(c);
                        read5 = pushbackInputStream.read();
                    } else {
                        pushbackInputStream.unread(read5);
                        stringBuffer = stringBuffer5.toString();
                        if (stringBuffer.indexOf(46) < 0) {
                            return new Double(stringBuffer);
                        }
                        return new Integer(stringBuffer);
                    }
                }
                pushbackInputStream.unread(read5);
                stringBuffer = stringBuffer5.toString();
                if (stringBuffer.indexOf(46) < 0) {
                }
                break;
            default:
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append((char) read);
                int read6 = pushbackInputStream.read();
                while (!isWhitespaceOrEOF(read6) && !isDelimiter(read6) && !Character.isDigit(read6)) {
                    stringBuffer6.append((char) read6);
                    read6 = pushbackInputStream.read();
                }
                if (isDelimiter(read6) || Character.isDigit(read6)) {
                    pushbackInputStream.unread(read6);
                }
                return new Operator(stringBuffer6.toString());
        }
    }

    private static void readUntilEndOfLine(InputStream inputStream, StringBuffer stringBuffer) throws IOException {
        int read = inputStream.read();
        while (read != -1 && read != 13 && read != 10) {
            stringBuffer.append((char) read);
            read = inputStream.read();
        }
    }

    private void increment(byte[] bArr) {
        increment(bArr, bArr.length - 1);
    }

    private void increment(byte[] bArr, int i) {
        if (i > 0 && (bArr[i] + 256) % 256 == 255) {
            bArr[i] = 0;
            increment(bArr, i - 1);
        } else {
            bArr[i] = (byte) (bArr[i] + 1);
        }
    }

    private static int createIntFromBytes(byte[] bArr) {
        int i = (bArr[0] + 256) % 256;
        return bArr.length == 2 ? (i << 8) + ((bArr[1] + 256) % 256) : i;
    }

    private static String createStringFromBytes(byte[] bArr) throws IOException {
        if (bArr.length == 1) {
            return new String(bArr, "ISO-8859-1");
        }
        return new String(bArr, "UTF-16BE");
    }

    private static int compare(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return (bArr[i] + 256) % 256 < (bArr2[i] + 256) % 256 ? -1 : 1;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public final class LiteralName {
        private String name;

        private LiteralName(String str) {
            this.name = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public final class Operator {
        private String op;

        private Operator(String str) {
            this.op = str;
        }
    }
}
