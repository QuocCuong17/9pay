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
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;

/* loaded from: classes5.dex */
public class FDFParser extends COSParser {
    private static final InputStream EMPTY_INPUT_STREAM = new ByteArrayInputStream(new byte[0]);
    private final RandomAccessBufferedFileInputStream raStream;
    private File tempPDFFile;

    public FDFParser(String str) throws IOException {
        this(new File(str));
    }

    public FDFParser(File file) throws IOException {
        super(EMPTY_INPUT_STREAM);
        this.fileLen = file.length();
        this.raStream = new RandomAccessBufferedFileInputStream(file);
        init();
    }

    public FDFParser(InputStream inputStream) throws IOException {
        super(EMPTY_INPUT_STREAM);
        File createTmpFile = createTmpFile(inputStream);
        this.tempPDFFile = createTmpFile;
        this.fileLen = createTmpFile.length();
        this.raStream = new RandomAccessBufferedFileInputStream(this.tempPDFFile);
        init();
    }

    private void init() throws IOException {
        String property = System.getProperty(COSParser.SYSPROP_EOFLOOKUPRANGE);
        if (property != null) {
            try {
                setEOFLookupRange(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                Log.w("PdfBoxAndroid", "System property org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + property + "'");
            }
        }
        this.document = new COSDocument(false);
        this.pdfSource = new PushBackInputStream(this.raStream, 4096);
    }

    private void initialParse() throws IOException {
        COSDictionary rebuildTrailer;
        long startxrefOffset = getStartxrefOffset();
        if (startxrefOffset > 0) {
            rebuildTrailer = parseXref(startxrefOffset);
        } else {
            rebuildTrailer = rebuildTrailer();
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
        COSBase parseObjectDynamically = parseObjectDynamically(cOSObject, false);
        if (parseObjectDynamically instanceof COSDictionary) {
            parseDictObjects((COSDictionary) parseObjectDynamically, null);
        }
        this.initialParseDone = true;
    }

    public void parse() throws IOException {
        try {
            if (!parseFDFHeader()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            initialParse();
            IOUtils.closeQuietly(this.pdfSource);
            deleteTempFile();
        } catch (Throwable th) {
            IOUtils.closeQuietly(this.pdfSource);
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

    public FDFDocument getFDFDocument() throws IOException {
        return new FDFDocument(getDocument());
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
}
