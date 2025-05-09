package org.apache.pdfbox.pdfparser;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.io.PushBackInputStream;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdmodel.PDDocument;

/* loaded from: classes5.dex */
public class PDFParser extends COSParser {
    private static final InputStream EMPTY_INPUT_STREAM = new ByteArrayInputStream(new byte[0]);
    private String keyAlias;
    private InputStream keyStoreInputStream;
    private String password;
    private final RandomAccessBufferedFileInputStream raStream;
    private File tempPDFFile;

    public PDFParser(String str) throws IOException {
        this(new File(str), (String) null, false);
    }

    public PDFParser(String str, boolean z) throws IOException {
        this(new File(str), (String) null, z);
    }

    public PDFParser(File file) throws IOException {
        this(file, "", false);
    }

    public PDFParser(File file, boolean z) throws IOException {
        this(file, "", z);
    }

    public PDFParser(File file, String str) throws IOException {
        this(file, str, false);
    }

    public PDFParser(File file, String str, boolean z) throws IOException {
        this(file, str, (InputStream) null, (String) null, z);
    }

    public PDFParser(File file, String str, InputStream inputStream, String str2) throws IOException {
        this(file, str, inputStream, str2, false);
    }

    public PDFParser(File file, String str, InputStream inputStream, String str2, boolean z) throws IOException {
        super(EMPTY_INPUT_STREAM);
        this.password = "";
        this.keyStoreInputStream = null;
        this.keyAlias = null;
        this.fileLen = file.length();
        this.raStream = new RandomAccessBufferedFileInputStream(file);
        this.password = str;
        this.keyStoreInputStream = inputStream;
        this.keyAlias = str2;
        init(z);
    }

    public PDFParser(InputStream inputStream) throws IOException {
        this(inputStream, "", false);
    }

    public PDFParser(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, "", z);
    }

    public PDFParser(InputStream inputStream, String str) throws IOException {
        this(inputStream, str, false);
    }

    public PDFParser(InputStream inputStream, String str, boolean z) throws IOException {
        this(inputStream, str, (InputStream) null, (String) null, z);
    }

    public PDFParser(InputStream inputStream, String str, InputStream inputStream2, String str2) throws IOException {
        this(inputStream, str, inputStream2, str2, false);
    }

    public PDFParser(InputStream inputStream, String str, InputStream inputStream2, String str2, boolean z) throws IOException {
        super(EMPTY_INPUT_STREAM);
        this.password = "";
        this.keyStoreInputStream = null;
        this.keyAlias = null;
        File createTmpFile = createTmpFile(inputStream);
        this.tempPDFFile = createTmpFile;
        this.fileLen = createTmpFile.length();
        this.raStream = new RandomAccessBufferedFileInputStream(this.tempPDFFile);
        this.password = str;
        this.keyStoreInputStream = inputStream2;
        this.keyAlias = str2;
        init(z);
    }

    private void init(boolean z) throws IOException {
        String property = System.getProperty(COSParser.SYSPROP_EOFLOOKUPRANGE);
        if (property != null) {
            try {
                setEOFLookupRange(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                Log.w("PdfBoxAndroid", "System property org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + property + "'");
            }
        }
        this.document = new COSDocument(z);
        this.pdfSource = new PushBackInputStream(this.raStream, 4096);
    }

    public PDDocument getPDDocument() throws IOException {
        return new PDDocument(getDocument(), this, null);
    }

    protected void initialParse() throws IOException {
        COSDictionary rebuildTrailer;
        long startxrefOffset = getStartxrefOffset();
        if (startxrefOffset > -1) {
            rebuildTrailer = parseXref(startxrefOffset);
        } else {
            rebuildTrailer = isLenient() ? rebuildTrailer() : null;
        }
        for (COSBase cOSBase : rebuildTrailer.getValues()) {
            if (cOSBase instanceof COSObject) {
                parseObjectDynamically((COSObject) cOSBase, false);
            }
        }
        COSObject cOSObject = (COSObject) rebuildTrailer.getItem(COSName.ROOT);
        if (cOSObject == null) {
            throw new IOException("Missing root object specification in trailer.");
        }
        parseObjectDynamically(cOSObject, false);
        COSObject catalog = this.document.getCatalog();
        if (catalog != null && (catalog.getObject() instanceof COSDictionary)) {
            parseDictObjects((COSDictionary) catalog.getObject(), null);
            this.document.setDecrypted();
        }
        this.initialParseDone = true;
    }

    public void parse() throws IOException {
        try {
            if (!parsePDFHeader() && !parseFDFHeader()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            if (!this.initialParseDone) {
                initialParse();
            }
            IOUtils.closeQuietly(this.pdfSource);
            IOUtils.closeQuietly(this.keyStoreInputStream);
            deleteTempFile();
        } catch (Throwable th) {
            IOUtils.closeQuietly(this.pdfSource);
            IOUtils.closeQuietly(this.keyStoreInputStream);
            deleteTempFile();
            if (this.document != null) {
                try {
                    this.document.close();
                    this.document = null;
                } catch (IOException unused) {
                }
            }
            throw th;
        }
    }

    private void deleteTempFile() {
        File file = this.tempPDFFile;
        if (file != null) {
            try {
                if (file.delete()) {
                    return;
                }
                Log.w("PdfBoxAndroid", "Temporary file '" + this.tempPDFFile.getName() + "' can't be deleted");
            } catch (SecurityException e) {
                Log.w("PdfBoxAndroid", "Temporary file '" + this.tempPDFFile.getName() + "' can't be deleted", e);
            }
        }
    }

    private void parseDictionaryRecursive(COSObject cOSObject) throws IOException {
        parseObjectDynamically(cOSObject, true);
        for (COSBase cOSBase : ((COSDictionary) cOSObject.getObject()).getValues()) {
            if (cOSBase instanceof COSObject) {
                COSObject cOSObject2 = (COSObject) cOSBase;
                if (cOSObject2.getObject() == null) {
                    parseDictionaryRecursive(cOSObject2);
                }
            }
        }
    }
}
