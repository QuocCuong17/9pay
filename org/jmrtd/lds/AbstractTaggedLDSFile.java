package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;

/* loaded from: classes6.dex */
public abstract class AbstractTaggedLDSFile extends AbstractLDSFile {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -4761360877353069639L;
    private int length;
    private int tag;

    protected abstract void readContent(InputStream inputStream) throws IOException;

    protected abstract void writeContent(OutputStream outputStream) throws IOException;

    @Override // org.jmrtd.lds.AbstractLDSFile, org.jmrtd.lds.LDSElement
    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTaggedLDSFile(int i) {
        this.tag = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTaggedLDSFile(int i, InputStream inputStream) throws IOException {
        this.tag = i;
        readObject(inputStream);
    }

    @Override // org.jmrtd.lds.AbstractLDSFile
    protected void readObject(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != this.tag) {
            throw new IllegalArgumentException("Was expecting tag " + Integer.toHexString(this.tag) + ", found " + Integer.toHexString(readTag));
        }
        this.length = tLVInputStream.readLength();
        readContent(tLVInputStream);
    }

    @Override // org.jmrtd.lds.AbstractLDSFile
    protected void writeObject(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        int tag = getTag();
        if (this.tag != tag) {
            this.tag = tag;
        }
        tLVOutputStream.writeTag(tag);
        byte[] content = getContent();
        int length = content == null ? 0 : content.length;
        if (this.length != length) {
            this.length = length;
        }
        tLVOutputStream.writeValue(content);
    }

    public String toString() {
        return "TaggedLDSFile [" + Integer.toHexString(getTag()) + " (" + getLength() + ")]";
    }

    public int getTag() {
        return this.tag;
    }

    @Override // org.jmrtd.lds.LDSFile
    public int getLength() {
        if (this.length <= 0) {
            this.length = getContent().length;
        }
        return this.length;
    }

    private byte[] getContent() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                writeContent(byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e);
                }
                return byteArray;
            } catch (IOException e2) {
                throw new IllegalStateException("Could not get DG content", e2);
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e3);
            }
            throw th;
        }
    }
}
