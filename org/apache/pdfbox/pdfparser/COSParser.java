package org.apache.pdfbox.pdfparser;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.beust.jcommander.Parameters;
import com.facebook.internal.ServerProtocol;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSObjectKey;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdfparser.XrefTrailerResolver;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes5.dex */
public class COSParser extends BaseParser {
    private static final int DEFAULT_TRAIL_BYTECOUNT = 2048;
    private static final String FDF_DEFAULT_VERSION = "1.0";
    private static final String FDF_HEADER = "%FDF-";
    private static final long MINIMUM_SEARCH_OFFSET = 6;
    private static final String PDF_DEFAULT_VERSION = "1.4";
    private static final String PDF_HEADER = "%PDF-";
    private static final int STREAMCOPYBUFLEN = 8192;
    public static final String SYSPROP_EOFLOOKUPRANGE = "org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange";
    public static final String SYSPROP_PARSEMINIMAL = "org.apache.pdfbox.pdfparser.nonSequentialPDFParser.parseMinimal";
    public static final String TMP_FILE_PREFIX = "tmpPDF";
    private static final int X = 120;
    private Map<COSObjectKey, Long> bfSearchCOSObjectKeyOffsets;
    private List<Long> bfSearchXRefStreamsOffsets;
    private List<Long> bfSearchXRefTablesOffsets;
    protected long fileLen;
    private boolean inGetLength;
    protected boolean initialParseDone;
    private boolean isLenient;
    private final boolean parseMinimalCatalog;
    private int readTrailBytes;
    private final byte[] streamCopyBuf;
    private long trailerOffset;
    protected XrefTrailerResolver xrefTrailerResolver;
    private static final char[] XREF_TABLE = {'x', 'r', 'e', 'f'};
    private static final char[] XREF_STREAM = {'/', 'X', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'e', 'f'};
    private static final char[] STARTXREF = {'s', 't', 'a', 'r', 't', 'x', 'r', 'e', 'f'};
    protected static final char[] EOF_MARKER = {'%', '%', 'E', 'O', 'F'};
    protected static final char[] OBJ_MARKER = {'o', 'b', 'j'};

    public COSParser(InputStream inputStream) throws IOException {
        super(inputStream);
        this.isLenient = true;
        this.initialParseDone = false;
        this.bfSearchCOSObjectKeyOffsets = null;
        this.bfSearchXRefTablesOffsets = null;
        this.bfSearchXRefStreamsOffsets = null;
        this.readTrailBytes = 2048;
        this.parseMinimalCatalog = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(System.getProperty(SYSPROP_PARSEMINIMAL));
        this.xrefTrailerResolver = new XrefTrailerResolver();
        this.inGetLength = false;
        this.streamCopyBuf = new byte[8192];
    }

    public void setEOFLookupRange(int i) {
        if (i > 15) {
            this.readTrailBytes = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public COSDictionary parseXref(long j) throws IOException {
        this.pdfSource.seek(j);
        long max = Math.max(0L, parseStartXref());
        long checkXRefOffset = checkXRefOffset(max);
        if (checkXRefOffset > -1) {
            max = checkXRefOffset;
        }
        this.document.setStartXref(max);
        long j2 = max;
        while (true) {
            if (j2 > -1) {
                this.pdfSource.seek(j2);
                skipSpaces();
                if (this.pdfSource.peek() == 120) {
                    parseXrefTable(j2);
                    this.trailerOffset = this.pdfSource.getOffset();
                    while (this.isLenient && this.pdfSource.peek() != 116) {
                        if (this.pdfSource.getOffset() == this.trailerOffset) {
                            Log.w("PdfBoxAndroid", "Expected trailer object at position " + this.trailerOffset + ", keep trying");
                        }
                        readLine();
                    }
                    if (!parseTrailer()) {
                        throw new IOException("Expected trailer object at position: " + this.pdfSource.getOffset());
                    }
                    COSDictionary currentTrailer = this.xrefTrailerResolver.getCurrentTrailer();
                    if (currentTrailer.containsKey(COSName.XREF_STM)) {
                        int i = currentTrailer.getInt(COSName.XREF_STM);
                        long j3 = i;
                        long checkXRefStreamOffset = checkXRefStreamOffset(j3, false);
                        if (checkXRefStreamOffset > -1 && checkXRefStreamOffset != j3) {
                            i = (int) checkXRefStreamOffset;
                            currentTrailer.setInt(COSName.XREF_STM, i);
                        }
                        if (i > 0) {
                            this.pdfSource.seek(i);
                            skipSpaces();
                            parseXrefObjStream(j2, false);
                        } else if (this.isLenient) {
                            Log.e("PdfBoxAndroid", "Skipped XRef stream due to a corrupt offset:" + i);
                        } else {
                            throw new IOException("Skipped XRef stream due to a corrupt offset:" + i);
                        }
                    }
                    j2 = currentTrailer.getInt(COSName.PREV);
                    if (j2 > -1) {
                        long checkXRefOffset2 = checkXRefOffset(j2);
                        if (checkXRefOffset2 > -1 && checkXRefOffset2 != j2) {
                            currentTrailer.setLong(COSName.PREV, checkXRefOffset2);
                            j2 = checkXRefOffset2;
                        }
                    }
                } else {
                    j2 = parseXrefObjStream(j2, true);
                    if (j2 > -1) {
                        long checkXRefOffset3 = checkXRefOffset(j2);
                        if (checkXRefOffset3 > -1 && checkXRefOffset3 != j2) {
                            this.xrefTrailerResolver.getCurrentTrailer().setLong(COSName.PREV, checkXRefOffset3);
                            j2 = checkXRefOffset3;
                        }
                    }
                }
            } else {
                this.xrefTrailerResolver.setStartxref(max);
                COSDictionary trailer = this.xrefTrailerResolver.getTrailer();
                this.document.setTrailer(trailer);
                this.document.setIsXRefStream(XrefTrailerResolver.XRefType.STREAM == this.xrefTrailerResolver.getXrefType());
                checkXrefOffsets();
                this.document.addXRefTable(this.xrefTrailerResolver.getXrefTable());
                return trailer;
            }
        }
    }

    private long parseXrefObjStream(long j, boolean z) throws IOException {
        readObjectNumber();
        readGenerationNumber();
        readExpectedString(OBJ_MARKER, true);
        COSDictionary parseCOSDictionary = parseCOSDictionary();
        COSStream parseCOSStream = parseCOSStream(parseCOSDictionary);
        parseXrefStream(parseCOSStream, (int) j, z);
        parseCOSStream.close();
        return parseCOSDictionary.getLong(COSName.PREV);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long getStartxrefOffset() throws IOException {
        try {
            long j = this.fileLen;
            int i = this.readTrailBytes;
            if (j < i) {
                i = (int) j;
            }
            byte[] bArr = new byte[i];
            long j2 = j - i;
            this.pdfSource.seek(j2);
            int i2 = 0;
            while (i2 < i) {
                int i3 = i - i2;
                int read = this.pdfSource.read(bArr, i2, i3);
                if (read < 1) {
                    throw new IOException("No more bytes to read for trailing buffer, but expected: " + i3);
                }
                i2 += read;
            }
            this.pdfSource.seek(0L);
            char[] cArr = EOF_MARKER;
            int lastIndexOf = lastIndexOf(cArr, bArr, i);
            if (lastIndexOf >= 0) {
                i = lastIndexOf;
            } else if (this.isLenient) {
                Log.d("PdfBoxAndroid", "Missing end of file marker '" + new String(cArr) + "'");
            } else {
                throw new IOException("Missing end of file marker '" + new String(cArr) + "'");
            }
            int lastIndexOf2 = lastIndexOf(STARTXREF, bArr, i);
            long j3 = j2 + lastIndexOf2;
            if (lastIndexOf2 >= 0) {
                return j3;
            }
            if (this.isLenient) {
                Log.d("PdfBoxAndroid", "Can't find offset for startxref");
                return -1L;
            }
            throw new IOException("Missing 'startxref' marker.");
        } catch (Throwable th) {
            this.pdfSource.seek(0L);
            throw th;
        }
    }

    protected int lastIndexOf(char[] cArr, byte[] bArr, int i) {
        int length = cArr.length - 1;
        char c = cArr[length];
        while (true) {
            int i2 = length;
            while (true) {
                i--;
                if (i < 0) {
                    return -1;
                }
                if (bArr[i] == c) {
                    i2--;
                    if (i2 < 0) {
                        return i;
                    }
                    c = cArr[i2];
                } else if (i2 < length) {
                    break;
                }
            }
            c = cArr[length];
        }
    }

    public boolean isLenient() {
        return this.isLenient;
    }

    public void setLenient(boolean z) {
        if (this.initialParseDone) {
            throw new IllegalArgumentException("Cannot change leniency after parsing");
        }
        this.isLenient = z;
    }

    private long getObjectId(COSObject cOSObject) {
        return (cOSObject.getObjectNumber() << 32) | cOSObject.getGenerationNumber();
    }

    private void addNewToList(Queue<COSBase> queue, Collection<COSBase> collection, Set<Long> set) {
        Iterator<COSBase> it = collection.iterator();
        while (it.hasNext()) {
            addNewToList(queue, it.next(), set);
        }
    }

    private void addNewToList(Queue<COSBase> queue, COSBase cOSBase, Set<Long> set) {
        if (!(cOSBase instanceof COSObject) || set.add(Long.valueOf(getObjectId((COSObject) cOSBase)))) {
            queue.add(cOSBase);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x010b, code lost:
    
        if (r1.isEmpty() == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x010e, code lost:
    
        r14 = ((java.util.List) r1.remove(r1.firstKey())).iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0120, code lost:
    
        if (r14.hasNext() == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0122, code lost:
    
        r4 = (org.apache.pdfbox.cos.COSObject) r14.next();
        r5 = parseObjectDynamically(r4, false);
        r4.setObject(r5);
        addNewToList(r0, r5, r3);
        r2.add(java.lang.Long.valueOf(getObjectId(r4)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x010d, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void parseDictObjects(COSDictionary cOSDictionary, COSName... cOSNameArr) throws IOException {
        COSObjectKey cOSObjectKey;
        Long l;
        LinkedList linkedList = new LinkedList();
        TreeMap treeMap = new TreeMap();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        addExcludedToList(cOSNameArr, cOSDictionary, hashSet);
        addNewToList(linkedList, cOSDictionary.getValues(), hashSet2);
        loop0: while (true) {
            if (!linkedList.isEmpty() || !treeMap.isEmpty()) {
                while (true) {
                    COSBase poll = linkedList.poll();
                    if (poll == null) {
                        break;
                    }
                    if (poll instanceof COSDictionary) {
                        addNewToList(linkedList, ((COSDictionary) poll).getValues(), hashSet2);
                    } else if (poll instanceof COSArray) {
                        Iterator<COSBase> it = ((COSArray) poll).iterator();
                        while (it.hasNext()) {
                            addNewToList(linkedList, it.next(), hashSet2);
                        }
                    } else if (poll instanceof COSObject) {
                        COSObject cOSObject = (COSObject) poll;
                        long objectId = getObjectId(cOSObject);
                        cOSObjectKey = new COSObjectKey(cOSObject.getObjectNumber(), cOSObject.getGenerationNumber());
                        if (hashSet.contains(Long.valueOf(objectId))) {
                            continue;
                        } else {
                            Long l2 = this.xrefTrailerResolver.getXrefTable().get(cOSObjectKey);
                            if (l2 == null || l2.longValue() == 0) {
                                this.document.getObjectFromPool(cOSObjectKey).setObject(COSNull.NULL);
                            } else if (l2.longValue() > 0) {
                                treeMap.put(l2, Collections.singletonList(cOSObject));
                            } else {
                                l = this.xrefTrailerResolver.getXrefTable().get(new COSObjectKey((int) (-l2.longValue()), 0));
                                if (l == null || l.longValue() <= 0) {
                                    break loop0;
                                }
                                List list = (List) treeMap.get(l);
                                if (list == null) {
                                    list = new ArrayList();
                                    treeMap.put(l, list);
                                }
                                list.add(cOSObject);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                return;
            }
        }
        throw new IOException("Invalid object stream xref object reference for key '" + cOSObjectKey + "': " + l);
    }

    private void addExcludedToList(COSName[] cOSNameArr, COSDictionary cOSDictionary, Set<Long> set) {
        if (cOSNameArr != null) {
            for (COSName cOSName : cOSNameArr) {
                COSBase item = cOSDictionary.getItem(cOSName);
                if (item instanceof COSObject) {
                    set.add(Long.valueOf(getObjectId((COSObject) item)));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final COSBase parseObjectDynamically(COSObject cOSObject, boolean z) throws IOException {
        return parseObjectDynamically(cOSObject.getObjectNumber(), cOSObject.getGenerationNumber(), z);
    }

    protected COSBase parseObjectDynamically(long j, int i, boolean z) throws IOException {
        COSObjectKey cOSObjectKey = new COSObjectKey(j, i);
        COSObject objectFromPool = this.document.getObjectFromPool(cOSObjectKey);
        if (objectFromPool.getObject() == null) {
            Long l = this.xrefTrailerResolver.getXrefTable().get(cOSObjectKey);
            if (z && (l == null || l.longValue() <= 0)) {
                throw new IOException("Object must be defined and must not be compressed object: " + cOSObjectKey.getNumber() + ":" + cOSObjectKey.getGeneration());
            }
            if (l == null) {
                objectFromPool.setObject(COSNull.NULL);
            } else if (l.longValue() > 0) {
                this.pdfSource.seek(l.longValue());
                long readObjectNumber = readObjectNumber();
                int readGenerationNumber = readGenerationNumber();
                readExpectedString(OBJ_MARKER, true);
                if (readObjectNumber != cOSObjectKey.getNumber() || readGenerationNumber != cOSObjectKey.getGeneration()) {
                    throw new IOException("XREF for " + cOSObjectKey.getNumber() + ":" + cOSObjectKey.getGeneration() + " points to wrong object: " + readObjectNumber + ":" + readGenerationNumber);
                }
                skipSpaces();
                COSBase parseDirObject = parseDirObject();
                String readString = readString();
                if (readString.equals("stream")) {
                    this.pdfSource.unread(readString.getBytes("ISO-8859-1"));
                    this.pdfSource.unread(32);
                    if (parseDirObject instanceof COSDictionary) {
                        parseDirObject = parseCOSStream((COSDictionary) parseDirObject);
                        skipSpaces();
                        readString = readLine();
                        if (!readString.startsWith("endobj") && readString.startsWith("endstream")) {
                            readString = readString.substring(9).trim();
                            if (readString.length() == 0) {
                                readString = readLine();
                            }
                        }
                    } else {
                        throw new IOException("Stream not preceded by dictionary (offset: " + l + ").");
                    }
                }
                objectFromPool.setObject(parseDirObject);
                if (!readString.startsWith("endobj")) {
                    if (!this.isLenient) {
                        throw new IOException("Object (" + readObjectNumber + ":" + readGenerationNumber + ") at offset " + l + " does not end with 'endobj' but with '" + readString + "'");
                    }
                    Log.w("PdfBoxAndroid", "Object (" + readObjectNumber + ":" + readGenerationNumber + ") at offset " + l + " does not end with 'endobj' but with '" + readString + "'");
                }
            } else {
                int i2 = (int) (-l.longValue());
                COSBase parseObjectDynamically = parseObjectDynamically(i2, 0, true);
                if (parseObjectDynamically instanceof COSStream) {
                    PDFObjectStreamParser pDFObjectStreamParser = new PDFObjectStreamParser((COSStream) parseObjectDynamically, this.document);
                    pDFObjectStreamParser.parse();
                    pDFObjectStreamParser.close();
                    Set<Long> containedObjectNumbers = this.xrefTrailerResolver.getContainedObjectNumbers(i2);
                    for (COSObject cOSObject : pDFObjectStreamParser.getObjects()) {
                        COSObjectKey cOSObjectKey2 = new COSObjectKey(cOSObject);
                        if (containedObjectNumbers.contains(Long.valueOf(cOSObjectKey2.getNumber()))) {
                            this.document.getObjectFromPool(cOSObjectKey2).setObject(cOSObject.getObject());
                        }
                    }
                }
            }
        }
        return objectFromPool.getObject();
    }

    private COSNumber getLength(COSBase cOSBase) throws IOException {
        COSNumber cOSNumber;
        if (cOSBase == null) {
            return null;
        }
        if (this.inGetLength) {
            throw new IOException("Loop while reading length from " + cOSBase);
        }
        try {
            this.inGetLength = true;
            if (cOSBase instanceof COSNumber) {
                cOSNumber = (COSNumber) cOSBase;
            } else if (cOSBase instanceof COSObject) {
                COSObject cOSObject = (COSObject) cOSBase;
                if (cOSObject.getObject() == null) {
                    long offset = this.pdfSource.getOffset();
                    parseObjectDynamically(cOSObject, true);
                    this.pdfSource.seek(offset);
                    if (cOSObject.getObject() == null) {
                        throw new IOException("Length object content was not read.");
                    }
                }
                if (!(cOSObject.getObject() instanceof COSNumber)) {
                    throw new IOException("Wrong type of referenced length object " + cOSObject + ": " + cOSObject.getObject().getClass().getSimpleName());
                }
                cOSNumber = (COSNumber) cOSObject.getObject();
            } else {
                throw new IOException("Wrong type of length object: " + cOSBase.getClass().getSimpleName());
            }
            return cOSNumber;
        } finally {
            this.inGetLength = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.pdfparser.BaseParser
    public COSStream parseCOSStream(COSDictionary cOSDictionary) throws IOException {
        COSStream createCOSStream = createCOSStream(cOSDictionary);
        OutputStream outputStream = null;
        try {
            readString();
            int read = this.pdfSource.read();
            while (read == 32) {
                read = this.pdfSource.read();
            }
            if (read == 13) {
                int read2 = this.pdfSource.read();
                if (read2 != 10) {
                    this.pdfSource.unread(read2);
                }
            } else if (read != 10) {
                this.pdfSource.unread(read);
            }
            COSNumber length = getLength(cOSDictionary.getItem(COSName.LENGTH));
            if (length == null) {
                if (this.isLenient) {
                    Log.w("PdfBoxAndroid", "The stream doesn't provide any stream length, using fallback readUntilEnd");
                } else {
                    throw new IOException("Missing length for stream.");
                }
            }
            boolean z = true;
            if (length != null && validateStreamLength(length.longValue())) {
                outputStream = createCOSStream.createFilteredStream(length);
                long longValue = length.longValue();
                int i = 0;
                while (true) {
                    if (longValue <= 0) {
                        z = false;
                        break;
                    }
                    int read3 = this.pdfSource.read(this.streamCopyBuf, 0, longValue > PlaybackStateCompat.ACTION_PLAY_FROM_URI ? 8192 : (int) longValue);
                    if (read3 <= 0) {
                        outputStream.close();
                        this.pdfSource.unread(i);
                        break;
                    }
                    outputStream.write(this.streamCopyBuf, 0, read3);
                    longValue -= read3;
                    i += read3;
                }
            }
            if (z) {
                outputStream = createCOSStream.createFilteredStream();
                readUntilEndStream(new EndstreamOutputStream(outputStream));
            }
            String readString = readString();
            if (readString.equals("endobj") && this.isLenient) {
                Log.w("PdfBoxAndroid", "stream ends with 'endobj' instead of 'endstream' at offset " + this.pdfSource.getOffset());
                this.pdfSource.unread(ENDOBJ);
            } else if (readString.length() > 9 && this.isLenient && readString.substring(0, 9).equals("endstream")) {
                Log.w("PdfBoxAndroid", "stream ends with '" + readString + "' instead of 'endstream' at offset " + this.pdfSource.getOffset());
                this.pdfSource.unread(readString.substring(9).getBytes("ISO-8859-1"));
            } else if (!readString.equals("endstream")) {
                throw new IOException("Error reading stream, expected='endstream' actual='" + readString + "' at offset " + this.pdfSource.getOffset());
            }
            return createCOSStream;
        } finally {
            if (0 != 0) {
                outputStream.close();
            }
        }
    }

    private boolean validateStreamLength(long j) throws IOException {
        long offset = this.pdfSource.getOffset();
        long j2 = j + offset;
        boolean z = false;
        if (j2 > this.fileLen) {
            Log.e("PdfBoxAndroid", "The end of the stream is out of range, using workaround to read the stream, found " + offset + " but expected " + j2);
        } else {
            this.pdfSource.seek(j2);
            skipSpaces();
            if (isString(ENDSTREAM)) {
                z = true;
            } else {
                Log.e("PdfBoxAndroid", "The end of the stream doesn't point to the correct offset, using workaround to read the stream, found " + offset + " but expected " + j2);
            }
            this.pdfSource.seek(offset);
        }
        return z;
    }

    private long checkXRefOffset(long j) throws IOException {
        if (!this.isLenient) {
            return j;
        }
        this.pdfSource.seek(j);
        if (this.pdfSource.peek() == 120 && isString(XREF_TABLE)) {
            return j;
        }
        if (j > 0) {
            long checkXRefStreamOffset = checkXRefStreamOffset(j, true);
            if (checkXRefStreamOffset > -1) {
                return checkXRefStreamOffset;
            }
        }
        return calculateXRefFixedOffset(j, false);
    }

    private long checkXRefStreamOffset(long j, boolean z) throws IOException {
        int peek;
        if (!this.isLenient || j == 0) {
            return j;
        }
        this.pdfSource.seek(j - 1);
        if (isWhitespace(this.pdfSource.read()) && (peek = this.pdfSource.peek()) > 47 && peek < 58) {
            try {
                readObjectNumber();
                readGenerationNumber();
                readExpectedString(OBJ_MARKER, true);
                this.pdfSource.seek(j);
                return j;
            } catch (IOException unused) {
                this.pdfSource.seek(j);
            }
        }
        if (z) {
            return -1L;
        }
        return calculateXRefFixedOffset(j, true);
    }

    private long calculateXRefFixedOffset(long j, boolean z) throws IOException {
        if (j < 0) {
            Log.e("PdfBoxAndroid", "Invalid object offset " + j + " when searching for a xref table/stream");
            return 0L;
        }
        long bfSearchForXRef = bfSearchForXRef(j, z);
        if (bfSearchForXRef > -1) {
            Log.d("PdfBoxAndroid", "Fixed reference for xref table/stream " + j + " -> " + bfSearchForXRef);
            return bfSearchForXRef;
        }
        Log.e("PdfBoxAndroid", "Can't find the object axref table/stream at offset " + j);
        return 0L;
    }

    private void checkXrefOffsets() throws IOException {
        Map<COSObjectKey, Long> xrefTable;
        if (this.isLenient && (xrefTable = this.xrefTrailerResolver.getXrefTable()) != null) {
            boolean z = false;
            Iterator<Map.Entry<COSObjectKey, Long>> it = xrefTable.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<COSObjectKey, Long> next = it.next();
                COSObjectKey key = next.getKey();
                Long value = next.getValue();
                if (value != null && value.longValue() >= 0 && !checkObjectKeys(key, value.longValue())) {
                    Log.d("PdfBoxAndroid", "Stop checking xref offsets as at least one couldn't be dereferenced");
                    z = true;
                    break;
                }
            }
            if (z) {
                bfSearchForObjects();
                Map<COSObjectKey, Long> map = this.bfSearchCOSObjectKeyOffsets;
                if (map == null || map.isEmpty()) {
                    return;
                }
                Log.d("PdfBoxAndroid", "Replaced read xref table with the results of a brute force search");
                xrefTable.putAll(this.bfSearchCOSObjectKeyOffsets);
            }
        }
    }

    private boolean checkObjectKeys(COSObjectKey cOSObjectKey, long j) throws IOException {
        if (j < 6) {
            return false;
        }
        long number = cOSObjectKey.getNumber();
        int generation = cOSObjectKey.getGeneration();
        long offset = this.pdfSource.getOffset();
        this.pdfSource.seek(j);
        try {
            if (isString(createObjectString(number, generation).getBytes("ISO-8859-1"))) {
                this.pdfSource.seek(offset);
                this.pdfSource.seek(offset);
                return true;
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.pdfSource.seek(offset);
            throw th;
        }
        this.pdfSource.seek(offset);
        return false;
    }

    private String createObjectString(long j, int i) {
        return Long.toString(j) + " " + Integer.toString(i) + " obj";
    }

    private void bfSearchForObjects() throws IOException {
        Long l;
        if (this.bfSearchCOSObjectKeyOffsets != null) {
            return;
        }
        this.bfSearchCOSObjectKeyOffsets = new HashMap();
        long offset = this.pdfSource.getOffset();
        char[] charArray = " obj".toCharArray();
        long j = 6;
        long j2 = 6;
        while (true) {
            this.pdfSource.seek(j2);
            if (isString(charArray)) {
                long j3 = j2 - 1;
                this.pdfSource.seek(j3);
                int peek = this.pdfSource.peek();
                if (peek > 47 && peek < 58) {
                    int i = peek - 48;
                    long j4 = j3 - 1;
                    this.pdfSource.seek(j4);
                    if (isSpace()) {
                        while (j4 > j && isSpace()) {
                            j4--;
                            this.pdfSource.seek(j4);
                        }
                        int i2 = 0;
                        while (j4 > j && isDigit()) {
                            j4--;
                            this.pdfSource.seek(j4);
                            i2++;
                        }
                        if (i2 > 0) {
                            this.pdfSource.read();
                            byte[] readFully = this.pdfSource.readFully(i2);
                            try {
                                l = Long.valueOf(new String(readFully, 0, readFully.length, "ISO-8859-1"));
                            } catch (NumberFormatException unused) {
                                l = null;
                            }
                            if (l != null) {
                                this.bfSearchCOSObjectKeyOffsets.put(new COSObjectKey(l.longValue(), i), Long.valueOf(j4 + 1));
                            }
                        }
                    }
                }
            }
            j2++;
            if (this.pdfSource.isEOF()) {
                this.pdfSource.seek(offset);
                return;
            }
            j = 6;
        }
    }

    private long bfSearchForXRef(long j, boolean z) throws IOException {
        List<Long> list;
        if (!z) {
            bfSearchForXRefTables();
        }
        bfSearchForXRefStreams();
        long searchNearestValue = (z || (list = this.bfSearchXRefTablesOffsets) == null) ? -1L : searchNearestValue(list, j);
        List<Long> list2 = this.bfSearchXRefStreamsOffsets;
        long searchNearestValue2 = list2 != null ? searchNearestValue(list2, j) : -1L;
        if (searchNearestValue <= -1 || searchNearestValue2 <= -1) {
            if (searchNearestValue > -1) {
                this.bfSearchXRefTablesOffsets.remove(Long.valueOf(searchNearestValue));
                return searchNearestValue;
            }
            if (searchNearestValue2 <= -1) {
                return -1L;
            }
            this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(searchNearestValue2));
            return searchNearestValue2;
        }
        long j2 = j - searchNearestValue;
        long j3 = j - searchNearestValue2;
        if (Math.abs(j2) > Math.abs(j3)) {
            this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(searchNearestValue2));
            return j3;
        }
        this.bfSearchXRefTablesOffsets.remove(Long.valueOf(searchNearestValue));
        return j2;
    }

    private long searchNearestValue(List<Long> list, long j) {
        int size = list.size();
        int i = -1;
        long j2 = -1;
        for (int i2 = 0; i2 < size; i2++) {
            long longValue = j - list.get(i2).longValue();
            if (j2 == -1 || Math.abs(j2) > Math.abs(longValue)) {
                i = i2;
                j2 = longValue;
            }
        }
        if (i > -1) {
            return list.get(i).longValue();
        }
        return -1L;
    }

    private void bfSearchForXRefTables() throws IOException {
        if (this.bfSearchXRefTablesOffsets == null) {
            this.bfSearchXRefTablesOffsets = new Vector();
            long offset = this.pdfSource.getOffset();
            this.pdfSource.seek(6L);
            while (!this.pdfSource.isEOF()) {
                if (isString(XREF_TABLE)) {
                    long offset2 = this.pdfSource.getOffset();
                    this.pdfSource.seek(offset2 - 1);
                    if (isWhitespace()) {
                        this.bfSearchXRefTablesOffsets.add(Long.valueOf(offset2));
                    }
                    this.pdfSource.seek(offset2 + 4);
                }
                this.pdfSource.read();
            }
            this.pdfSource.seek(offset);
        }
    }

    private void bfSearchForXRefStreams() throws IOException {
        if (this.bfSearchXRefStreamsOffsets == null) {
            this.bfSearchXRefStreamsOffsets = new Vector();
            long offset = this.pdfSource.getOffset();
            this.pdfSource.seek(6L);
            char[] charArray = " obj".toCharArray();
            while (!this.pdfSource.isEOF()) {
                if (isString(XREF_STREAM)) {
                    long offset2 = this.pdfSource.getOffset();
                    boolean z = false;
                    long j = -1;
                    for (int i = 1; i < 30 && !z; i++) {
                        long j2 = offset2 - (i * 10);
                        if (j2 > 0) {
                            this.pdfSource.seek(j2);
                            int i2 = 0;
                            while (true) {
                                if (i2 >= 10) {
                                    break;
                                }
                                if (isString(charArray)) {
                                    long j3 = j2 - 1;
                                    this.pdfSource.seek(j3);
                                    if (isDigit(this.pdfSource.peek())) {
                                        long j4 = j3 - 1;
                                        this.pdfSource.seek(j4);
                                        if (isSpace()) {
                                            long j5 = j4 - 1;
                                            this.pdfSource.seek(j5);
                                            int i3 = 0;
                                            while (j5 > 6 && isDigit()) {
                                                j5--;
                                                this.pdfSource.seek(j5);
                                                i3++;
                                            }
                                            if (i3 > 0) {
                                                this.pdfSource.read();
                                                j = this.pdfSource.getOffset();
                                            }
                                        }
                                    }
                                    Log.d("PdfBoxAndroid", "Fixed reference for xref stream " + offset2 + " -> " + j);
                                    z = true;
                                } else {
                                    j2++;
                                    this.pdfSource.read();
                                    i2++;
                                }
                            }
                        }
                    }
                    if (j > -1) {
                        this.bfSearchXRefStreamsOffsets.add(Long.valueOf(j));
                    }
                    this.pdfSource.seek(offset2 + 5);
                }
                this.pdfSource.read();
            }
            this.pdfSource.seek(offset);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final COSDictionary rebuildTrailer() throws IOException {
        bfSearchForObjects();
        if (this.bfSearchCOSObjectKeyOffsets == null) {
            return null;
        }
        this.xrefTrailerResolver.nextXrefObj(0L, XrefTrailerResolver.XRefType.TABLE);
        for (COSObjectKey cOSObjectKey : this.bfSearchCOSObjectKeyOffsets.keySet()) {
            this.xrefTrailerResolver.setXRef(cOSObjectKey, this.bfSearchCOSObjectKeyOffsets.get(cOSObjectKey).longValue());
        }
        this.xrefTrailerResolver.setStartxref(0L);
        COSDictionary trailer = this.xrefTrailerResolver.getTrailer();
        getDocument().setTrailer(trailer);
        for (COSObjectKey cOSObjectKey2 : this.bfSearchCOSObjectKeyOffsets.keySet()) {
            this.pdfSource.seek(this.bfSearchCOSObjectKeyOffsets.get(cOSObjectKey2).longValue());
            readObjectNumber();
            readGenerationNumber();
            readExpectedString(OBJ_MARKER, true);
            try {
                COSDictionary parseCOSDictionary = parseCOSDictionary();
                if (parseCOSDictionary != null) {
                    if (COSName.CATALOG.equals(parseCOSDictionary.getCOSName(COSName.TYPE))) {
                        trailer.setItem(COSName.ROOT, (COSBase) this.document.getObjectFromPool(cOSObjectKey2));
                    } else if (parseCOSDictionary.containsKey(COSName.TITLE) || parseCOSDictionary.containsKey(COSName.AUTHOR) || parseCOSDictionary.containsKey(COSName.SUBJECT) || parseCOSDictionary.containsKey(COSName.KEYWORDS) || parseCOSDictionary.containsKey(COSName.CREATOR) || parseCOSDictionary.containsKey(COSName.PRODUCER) || parseCOSDictionary.containsKey(COSName.CREATION_DATE)) {
                        trailer.setItem(COSName.INFO, (COSBase) this.document.getObjectFromPool(cOSObjectKey2));
                    }
                }
            } catch (IOException unused) {
                Log.d("PdfBoxAndroid", "Skipped object " + cOSObjectKey2 + ", either it's corrupt or not a dictionary");
            }
        }
        return trailer;
    }

    protected long parseStartXref() throws IOException {
        if (!isString(STARTXREF)) {
            return -1L;
        }
        readString();
        skipSpaces();
        return readLong();
    }

    protected boolean parseTrailer() throws IOException {
        if (this.pdfSource.peek() != 116) {
            return false;
        }
        long offset = this.pdfSource.getOffset();
        String readLine = readLine();
        if (!readLine.trim().equals("trailer")) {
            if (!readLine.startsWith("trailer")) {
                return false;
            }
            this.pdfSource.seek(offset + 7);
        }
        skipSpaces();
        this.xrefTrailerResolver.setTrailer(parseCOSDictionary());
        skipSpaces();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean parsePDFHeader() throws IOException {
        return parseHeader(PDF_HEADER, PDF_DEFAULT_VERSION);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean parseFDFHeader() throws IOException {
        return parseHeader(FDF_HEADER, "1.0");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:20|(1:22)|23|(2:25|(8:27|(1:29)|30|31|32|(1:34)|36|(2:38|39)(2:40|41)))|44|30|31|32|(0)|36|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e3, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e4, code lost:
    
        android.util.Log.d("PdfBoxAndroid", "Can't parse the header version.", r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00dc A[Catch: NumberFormatException -> 0x00e3, TRY_LEAVE, TryCatch #0 {NumberFormatException -> 0x00e3, blocks: (B:32:0x00d2, B:34:0x00dc), top: B:31:0x00d2 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean parseHeader(String str, String str2) throws IOException {
        float f;
        String[] split;
        String readLine = readLine();
        if (!readLine.contains(str)) {
            readLine = readLine();
            while (!readLine.contains(str) && (readLine.length() <= 0 || !Character.isDigit(readLine.charAt(0)))) {
                readLine = readLine();
            }
        }
        if (!readLine.contains(str)) {
            this.pdfSource.seek(0L);
            return false;
        }
        int indexOf = readLine.indexOf(str);
        if (indexOf > 0) {
            readLine = readLine.substring(indexOf, readLine.length());
        }
        if (readLine.startsWith(str)) {
            if (!readLine.matches(str + "\\d.\\d")) {
                if (readLine.length() < str.length() + 3) {
                    readLine = str + str2;
                    Log.d("PdfBoxAndroid", "No version found, set to " + str2 + " as default.");
                }
                f = -1.0f;
                split = readLine.split(Parameters.DEFAULT_OPTION_PREFIXES);
                if (split.length == 2) {
                    f = Float.parseFloat(split[1]);
                }
                if (f >= 0.0f) {
                    throw new IOException("Error getting header version: " + readLine);
                }
                this.document.setVersion(f);
                this.pdfSource.seek(0L);
                return true;
            }
        }
        String str3 = readLine.substring(str.length() + 3, readLine.length()) + IOUtils.LINE_SEPARATOR_UNIX;
        readLine = readLine.substring(0, str.length() + 3);
        this.pdfSource.unread(str3.getBytes("ISO-8859-1"));
        f = -1.0f;
        split = readLine.split(Parameters.DEFAULT_OPTION_PREFIXES);
        if (split.length == 2) {
        }
        if (f >= 0.0f) {
        }
    }

    protected boolean parseXrefTable(long j) throws IOException {
        if (this.pdfSource.peek() != 120 || !readString().trim().equals("xref")) {
            return false;
        }
        String readString = readString();
        byte[] bytes = readString.getBytes("ISO-8859-1");
        this.pdfSource.unread(bytes, 0, bytes.length);
        this.xrefTrailerResolver.nextXrefObj(j, XrefTrailerResolver.XRefType.TABLE);
        if (readString.startsWith("trailer")) {
            Log.w("PdfBoxAndroid", "skipping empty xref table");
            return false;
        }
        do {
            long readObjectNumber = readObjectNumber();
            long readLong = readLong();
            skipSpaces();
            int i = 0;
            while (true) {
                if (i >= readLong || this.pdfSource.isEOF() || isEndOfName((char) this.pdfSource.peek()) || this.pdfSource.peek() == 116) {
                    break;
                }
                String readLine = readLine();
                String[] split = readLine.split("\\s");
                if (split.length < 3) {
                    Log.w("PdfBoxAndroid", "invalid xref line: " + readLine);
                    break;
                }
                if (split[split.length - 1].equals("n")) {
                    try {
                        this.xrefTrailerResolver.setXRef(new COSObjectKey(readObjectNumber, Integer.parseInt(split[1])), Integer.parseInt(split[0]));
                    } catch (NumberFormatException e) {
                        throw new IOException(e);
                    }
                } else if (!split[2].equals("f")) {
                    throw new IOException("Corrupt XRefTable Entry - ObjID:" + readObjectNumber);
                }
                readObjectNumber++;
                skipSpaces();
                i++;
            }
            skipSpaces();
        } while (isDigit());
        return true;
    }

    public void parseXrefStream(COSStream cOSStream, long j, boolean z) throws IOException {
        if (z) {
            this.xrefTrailerResolver.nextXrefObj(j, XrefTrailerResolver.XRefType.STREAM);
            this.xrefTrailerResolver.setTrailer(cOSStream);
        }
        PDFXrefStreamParser pDFXrefStreamParser = new PDFXrefStreamParser(cOSStream, this.document, this.xrefTrailerResolver);
        pDFXrefStreamParser.parse();
        pDFXrefStreamParser.close();
    }

    public COSDocument getDocument() throws IOException {
        if (this.document == null) {
            throw new IOException("You must call parse() before calling getDocument()");
        }
        return this.document;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File createTmpFile(InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            File createTempFile = File.createTempFile(TMP_FILE_PREFIX, ".pdf");
            fileOutputStream = new FileOutputStream(createTempFile);
            try {
                org.apache.pdfbox.io.IOUtils.copy(inputStream, fileOutputStream);
                org.apache.pdfbox.io.IOUtils.closeQuietly(inputStream);
                org.apache.pdfbox.io.IOUtils.closeQuietly(fileOutputStream);
                return createTempFile;
            } catch (Throwable th2) {
                th = th2;
                org.apache.pdfbox.io.IOUtils.closeQuietly(inputStream);
                org.apache.pdfbox.io.IOUtils.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            fileOutputStream = null;
            th = th3;
        }
    }
}
