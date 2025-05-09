package org.apache.pdfbox.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;

/* loaded from: classes5.dex */
public class RandomAccessFileOutputStream extends OutputStream {
    private final RandomAccess file;
    private final long position;
    private long lengthWritten = 0;
    private COSBase expectedLength = null;

    public RandomAccessFileOutputStream(RandomAccess randomAccess) throws IOException {
        this.file = randomAccess;
        this.position = randomAccess.length();
    }

    public long getPosition() {
        return this.position;
    }

    public long getLengthWritten() {
        return this.lengthWritten;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long getLength() {
        long j;
        int intValue;
        COSBase cOSBase = this.expectedLength;
        if (cOSBase instanceof COSNumber) {
            intValue = ((COSNumber) cOSBase).intValue();
        } else {
            if (!(cOSBase instanceof COSObject) || !(((COSObject) cOSBase).getObject() instanceof COSNumber)) {
                j = -1;
                return j != -1 ? this.lengthWritten : j;
            }
            intValue = ((COSNumber) ((COSObject) this.expectedLength).getObject()).intValue();
        }
        j = intValue;
        if (j != -1) {
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.file.seek(this.position + this.lengthWritten);
        this.lengthWritten += i2;
        this.file.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.file.seek(this.position + this.lengthWritten);
        this.lengthWritten++;
        this.file.write(i);
    }

    public COSBase getExpectedLength() {
        return this.expectedLength;
    }

    public void setExpectedLength(COSBase cOSBase) {
        this.expectedLength = cOSBase;
    }
}
