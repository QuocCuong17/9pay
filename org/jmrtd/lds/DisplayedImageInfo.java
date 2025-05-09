package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;
import net.sf.scuba.tlv.TLVUtil;

/* loaded from: classes6.dex */
public class DisplayedImageInfo extends AbstractImageInfo {
    public static final int DISPLAYED_PORTRAIT_TAG = 24384;
    public static final int DISPLAYED_SIGNATURE_OR_MARK_TAG = 24387;
    private static final long serialVersionUID = 3801320585294302721L;
    private int displayedImageTag;

    public DisplayedImageInfo(int i, byte[] bArr) {
        super(i, getMimeTypeFromType(i));
        this.displayedImageTag = getDisplayedImageTagFromType(i);
        setImageBytes(bArr);
    }

    public DisplayedImageInfo(InputStream inputStream) throws IOException {
        readObject(inputStream);
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    protected void readObject(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        this.displayedImageTag = readTag;
        if (readTag != 24384 && readTag != 24387) {
            throw new IllegalArgumentException("Expected tag 0x5F40 or 0x5F43, found " + Integer.toHexString(this.displayedImageTag));
        }
        int typeFromDisplayedImageTag = getTypeFromDisplayedImageTag(readTag);
        setType(typeFromDisplayedImageTag);
        setMimeType(getMimeTypeFromType(typeFromDisplayedImageTag));
        readImage(tLVInputStream, tLVInputStream.readLength());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jmrtd.lds.AbstractImageInfo
    public void writeObject(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(getDisplayedImageTagFromType(getType()));
        writeImage(tLVOutputStream);
        tLVOutputStream.writeValueEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDisplayedImageTag() {
        return this.displayedImageTag;
    }

    @Override // org.jmrtd.lds.ImageInfo
    public long getRecordLength() {
        return TLVUtil.getTagLength(getDisplayedImageTagFromType(getType())) + 0 + TLVUtil.getLengthLength(r0) + getImageLength();
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public int hashCode() {
        return (super.hashCode() * 31) + this.displayedImageTag;
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj) && getClass() == obj.getClass() && this.displayedImageTag == ((DisplayedImageInfo) obj).displayedImageTag;
    }

    private static String getMimeTypeFromType(int i) {
        if (i == 0 || i == 1) {
            return "image/jpeg";
        }
        if (i == 2) {
            return ImageInfo.WSQ_MIME_TYPE;
        }
        throw new NumberFormatException("Unknown type: " + Integer.toHexString(i));
    }

    private static int getDisplayedImageTagFromType(int i) {
        if (i == 0) {
            return DISPLAYED_PORTRAIT_TAG;
        }
        if (i == 1) {
            return DISPLAYED_SIGNATURE_OR_MARK_TAG;
        }
        throw new NumberFormatException("Unknown type: " + Integer.toHexString(i));
    }

    private static int getTypeFromDisplayedImageTag(int i) {
        if (i == 24384) {
            return 0;
        }
        if (i == 24387) {
            return 1;
        }
        throw new NumberFormatException("Unknown tag: " + Integer.toHexString(i));
    }
}
