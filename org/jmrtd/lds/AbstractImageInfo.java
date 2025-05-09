package org.jmrtd.lds;

import com.facebook.internal.AnalyticsEvents;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jmrtd.io.SplittableInputStream;

/* loaded from: classes6.dex */
public abstract class AbstractImageInfo implements ImageInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 2870092217269116309L;
    private int height;
    private byte[] imageBytes;
    private int imageLength;
    private int imagePositionInInputStream;
    private String mimeType;
    private transient SplittableInputStream splittableInputStream;
    private int type;
    private int width;

    protected abstract void readObject(InputStream inputStream) throws IOException;

    protected abstract void writeObject(OutputStream outputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractImageInfo() {
        this(-1, 0, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractImageInfo(int i) {
        this(i, 0, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractImageInfo(int i, String str) {
        this(i, 0, 0, str);
    }

    private AbstractImageInfo(int i, int i2, int i3, String str) {
        this.type = i;
        this.mimeType = str;
        this.width = i2;
        this.height = i3;
    }

    public AbstractImageInfo(int i, int i2, int i3, InputStream inputStream, long j, String str) throws IOException {
        this(i, i2, i3, str);
        readImage(inputStream, j);
    }

    @Override // org.jmrtd.lds.ImageInfo
    public int getType() {
        return this.type;
    }

    @Override // org.jmrtd.lds.ImageInfo
    public String getMimeType() {
        return this.mimeType;
    }

    @Override // org.jmrtd.lds.ImageInfo
    public int getWidth() {
        return this.width;
    }

    @Override // org.jmrtd.lds.ImageInfo
    public int getHeight() {
        return this.height;
    }

    @Override // org.jmrtd.lds.ImageInfo
    public int getImageLength() {
        if (this.splittableInputStream != null) {
            return this.imageLength;
        }
        byte[] bArr = this.imageBytes;
        if (bArr == null) {
            throw new IllegalStateException("Cannot get length of null");
        }
        return bArr.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("type: ");
        sb.append(typeToString(this.type) + ", ");
        sb.append("size: ");
        sb.append(getImageLength());
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        int i = (-591263623) + (this.type * 5);
        String str = this.mimeType;
        return i + ((str == null ? 1337 : str.hashCode()) * 5) + 7 + (getImageLength() * 7) + 11;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        try {
            if (!obj.getClass().equals(getClass())) {
                return false;
            }
            AbstractImageInfo abstractImageInfo = (AbstractImageInfo) obj;
            if (!Arrays.equals(getImageBytes(), abstractImageInfo.getImageBytes())) {
                return false;
            }
            String str = this.mimeType;
            if (!(str == null && abstractImageInfo.mimeType == null) && (str == null || !str.equals(abstractImageInfo.mimeType))) {
                return false;
            }
            return this.type == abstractImageInfo.type;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception" + e);
            return false;
        }
    }

    @Override // org.jmrtd.lds.LDSElement
    public byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeObject(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            return null;
        }
    }

    @Override // org.jmrtd.lds.ImageInfo
    public InputStream getImageInputStream() {
        SplittableInputStream splittableInputStream = this.splittableInputStream;
        if (splittableInputStream != null) {
            return splittableInputStream.getInputStream(this.imagePositionInInputStream);
        }
        if (this.imageBytes != null) {
            return new ByteArrayInputStream(this.imageBytes);
        }
        throw new IllegalStateException("Both the byte buffer and the stream are null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void readImage(InputStream inputStream, long j) throws IOException {
        this.splittableInputStream = null;
        this.imageBytes = new byte[(int) j];
        new DataInputStream(inputStream).readFully(this.imageBytes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeImage(OutputStream outputStream) throws IOException {
        outputStream.write(getImageBytes());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMimeType(String str) {
        this.mimeType = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setType(int i) {
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setWidth(int i) {
        this.width = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setHeight(int i) {
        this.height = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setImageBytes(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Cannot set null image bytes");
        }
        try {
            readImage(new ByteArrayInputStream(bArr), bArr.length);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
    }

    private byte[] getImageBytes() throws IOException {
        byte[] bArr = new byte[getImageLength()];
        new DataInputStream(getImageInputStream()).readFully(bArr);
        return bArr;
    }

    private static String typeToString(int i) {
        if (i == -1) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        if (i == 0) {
            return "Portrait";
        }
        if (i == 1) {
            return "Signature or usual mark";
        }
        if (i == 2) {
            return "Finger";
        }
        if (i == 3) {
            return "Iris";
        }
        throw new NumberFormatException("Unknown type: " + Integer.toHexString(i));
    }
}
