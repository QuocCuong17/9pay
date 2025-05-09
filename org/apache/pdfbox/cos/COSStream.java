package org.apache.pdfbox.cos;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.filter.DecodeResult;
import org.apache.pdfbox.filter.Filter;
import org.apache.pdfbox.filter.FilterFactory;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.io.RandomAccess;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessFileInputStream;
import org.apache.pdfbox.io.RandomAccessFileOutputStream;

/* loaded from: classes5.dex */
public class COSStream extends COSDictionary implements Closeable {
    private static final int BUFFER_SIZE = 16384;
    private final RandomAccess buffer;
    private DecodeResult decodeResult;
    private RandomAccessFileOutputStream filteredStream;
    private File scratchFile;
    private RandomAccessFileOutputStream unFilteredStream;

    public COSStream() {
        this(false, null);
    }

    public COSStream(COSDictionary cOSDictionary) {
        this(cOSDictionary, false, null);
    }

    public COSStream(boolean z, File file) {
        if (z) {
            this.buffer = createScratchFile(file);
        } else {
            this.buffer = new RandomAccessBuffer();
        }
    }

    public COSStream(COSDictionary cOSDictionary, boolean z, File file) {
        super(cOSDictionary);
        if (z) {
            this.buffer = createScratchFile(file);
        } else {
            this.buffer = new RandomAccessBuffer();
        }
    }

    private RandomAccess createScratchFile(File file) {
        try {
            File createTempFile = File.createTempFile("PDFBox", null, file);
            this.scratchFile = createTempFile;
            return new RandomAccessFile(createTempFile, "rw");
        } catch (IOException e) {
            Log.e("PdfBoxAndroid", "Can't create temp file, using memory buffer instead", e);
            return new RandomAccessBuffer();
        }
    }

    public InputStream getFilteredStream() throws IOException {
        if (this.buffer.isClosed()) {
            throw new IOException("COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?");
        }
        if (this.filteredStream == null) {
            doEncode();
        }
        return new BufferedInputStream(new RandomAccessFileInputStream(this.buffer, this.filteredStream.getPosition(), this.filteredStream.getLengthWritten()), 16384);
    }

    public long getFilteredLength() throws IOException {
        if (this.filteredStream == null) {
            doEncode();
        }
        return this.filteredStream.getLength();
    }

    public InputStream getUnfilteredStream() throws IOException {
        if (this.buffer.isClosed()) {
            throw new IOException("COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?");
        }
        if (this.unFilteredStream == null) {
            doDecode();
        }
        RandomAccessFileOutputStream randomAccessFileOutputStream = this.unFilteredStream;
        if (randomAccessFileOutputStream != null) {
            return new BufferedInputStream(new RandomAccessFileInputStream(this.buffer, randomAccessFileOutputStream.getPosition(), this.unFilteredStream.getLengthWritten()), 16384);
        }
        return new ByteArrayInputStream(new byte[0]);
    }

    public DecodeResult getDecodeResult() throws IOException {
        DecodeResult decodeResult;
        if (this.unFilteredStream == null) {
            doDecode();
        }
        if (this.unFilteredStream != null && (decodeResult = this.decodeResult) != null) {
            return decodeResult;
        }
        StringBuilder sb = new StringBuilder();
        COSBase filters = getFilters();
        if (filters != null) {
            sb.append(" - filter: ");
            if (filters instanceof COSName) {
                sb.append(((COSName) filters).getName());
            } else if (filters instanceof COSArray) {
                COSArray cOSArray = (COSArray) filters;
                for (int i = 0; i < cOSArray.size(); i++) {
                    if (cOSArray.size() > 1) {
                        sb.append(", ");
                    }
                    sb.append(((COSName) cOSArray.get(i)).getName());
                }
            }
        }
        throw new IOException(getNameAsString(COSName.SUBTYPE) + " stream was not read" + ((Object) sb));
    }

    @Override // org.apache.pdfbox.cos.COSDictionary, org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromStream(this);
    }

    private void doDecode() throws IOException {
        this.unFilteredStream = this.filteredStream;
        COSBase filters = getFilters();
        if (filters == null) {
            this.decodeResult = DecodeResult.DEFAULT;
            return;
        }
        if (filters instanceof COSName) {
            doDecode((COSName) filters, 0);
            return;
        }
        if (filters instanceof COSArray) {
            COSArray cOSArray = (COSArray) filters;
            for (int i = 0; i < cOSArray.size(); i++) {
                doDecode((COSName) cOSArray.get(i), i);
            }
            return;
        }
        throw new IOException("Error: Unknown filter type:" + filters);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:
    
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:
    
        if (r20 != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
    
        if (r14 >= r6) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006a, code lost:
    
        r15 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0072, code lost:
    
        attemptDecode(r10, r12, r9, r26);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0075, code lost:
    
        r20 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0078, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0079, code lost:
    
        r12 = r12 - 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void doDecode(COSName cOSName, int i) throws IOException {
        int i2;
        int i3;
        Filter filter = FilterFactory.INSTANCE.getFilter(cOSName);
        long position = this.unFilteredStream.getPosition();
        long length = this.unFilteredStream.getLength();
        long lengthWritten = this.unFilteredStream.getLengthWritten();
        boolean z = true;
        IOException iOException = null;
        if (length == 0 && lengthWritten == 0) {
            IOUtils.closeQuietly(this.unFilteredStream);
            this.unFilteredStream = new RandomAccessFileOutputStream(this.buffer);
        } else {
            long j = length;
            IOException e = null;
            int i4 = 0;
            boolean z2 = false;
            while (true) {
                i2 = 5;
                if (j <= 0 || z2 || i4 >= 5) {
                    break;
                }
                int i5 = i4;
                try {
                    attemptDecode(position, j, filter, i);
                    z2 = true;
                } catch (IOException e2) {
                    e = e2;
                    j--;
                }
                i4 = i5 + 1;
            }
            iOException = e;
            z = z2;
        }
        if (!z && iOException != null) {
            throw iOException;
        }
        return;
        int i6 = i6 + 1;
        i2 = i3;
    }

    private void attemptDecode(long j, long j2, Filter filter, int i) throws IOException {
        InputStream inputStream = null;
        try {
            InputStream bufferedInputStream = new BufferedInputStream(new RandomAccessFileInputStream(this.buffer, j, j2), 16384);
            try {
                IOUtils.closeQuietly(this.unFilteredStream);
                RandomAccessFileOutputStream randomAccessFileOutputStream = new RandomAccessFileOutputStream(this.buffer);
                this.unFilteredStream = randomAccessFileOutputStream;
                this.decodeResult = filter.decode(bufferedInputStream, randomAccessFileOutputStream, this, i);
                IOUtils.closeQuietly(bufferedInputStream);
            } catch (Throwable th) {
                th = th;
                inputStream = bufferedInputStream;
                IOUtils.closeQuietly(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void doEncode() throws IOException {
        this.filteredStream = this.unFilteredStream;
        COSBase filters = getFilters();
        if (filters == null) {
            return;
        }
        if (filters instanceof COSName) {
            doEncode((COSName) filters, 0);
            return;
        }
        if (filters instanceof COSArray) {
            COSArray cOSArray = (COSArray) filters;
            for (int size = cOSArray.size() - 1; size >= 0; size--) {
                doEncode((COSName) cOSArray.get(size), size);
            }
        }
    }

    private void doEncode(COSName cOSName, int i) throws IOException {
        Filter filter = FilterFactory.INSTANCE.getFilter(cOSName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new RandomAccessFileInputStream(this.buffer, this.filteredStream.getPosition(), this.filteredStream.getLength()), 16384);
        IOUtils.closeQuietly(this.filteredStream);
        RandomAccessFileOutputStream randomAccessFileOutputStream = new RandomAccessFileOutputStream(this.buffer);
        this.filteredStream = randomAccessFileOutputStream;
        filter.encode(bufferedInputStream, randomAccessFileOutputStream, this, i);
        IOUtils.closeQuietly(bufferedInputStream);
    }

    public COSBase getFilters() {
        return getDictionaryObject(COSName.FILTER);
    }

    public OutputStream createFilteredStream() throws IOException {
        IOUtils.closeQuietly(this.unFilteredStream);
        this.unFilteredStream = null;
        IOUtils.closeQuietly(this.filteredStream);
        this.filteredStream = new RandomAccessFileOutputStream(this.buffer);
        return new BufferedOutputStream(this.filteredStream, 16384);
    }

    public OutputStream createFilteredStream(COSBase cOSBase) throws IOException {
        OutputStream createFilteredStream = createFilteredStream();
        this.filteredStream.setExpectedLength(cOSBase);
        return createFilteredStream;
    }

    public void setFilters(COSBase cOSBase) throws IOException {
        if (this.unFilteredStream == null) {
            doDecode();
        }
        setItem(COSName.FILTER, cOSBase);
        IOUtils.closeQuietly(this.filteredStream);
        this.filteredStream = null;
    }

    public OutputStream createUnfilteredStream() throws IOException {
        IOUtils.closeQuietly(this.filteredStream);
        this.filteredStream = null;
        IOUtils.closeQuietly(this.unFilteredStream);
        this.unFilteredStream = new RandomAccessFileOutputStream(this.buffer);
        return new BufferedOutputStream(this.unFilteredStream, 16384);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccess randomAccess = this.buffer;
        if (randomAccess != null) {
            randomAccess.close();
        }
        RandomAccessFileOutputStream randomAccessFileOutputStream = this.filteredStream;
        if (randomAccessFileOutputStream != null) {
            randomAccessFileOutputStream.close();
        }
        RandomAccessFileOutputStream randomAccessFileOutputStream2 = this.unFilteredStream;
        if (randomAccessFileOutputStream2 != null) {
            randomAccessFileOutputStream2.close();
        }
        File file = this.scratchFile;
        if (file == null || !file.exists() || this.scratchFile.delete()) {
            return;
        }
        throw new IOException("Can't delete the temporary scratch file " + this.scratchFile.getAbsolutePath());
    }
}
