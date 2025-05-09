package org.jmrtd.lds.iso19794;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jmrtd.lds.AbstractImageInfo;
import org.jmrtd.lds.ImageInfo;

/* loaded from: classes6.dex */
public class IrisImageInfo extends AbstractImageInfo {
    public static final int IMAGE_QUAL_HIGH_HI = 100;
    public static final int IMAGE_QUAL_HIGH_LO = 76;
    public static final int IMAGE_QUAL_LOW_HI = 50;
    public static final int IMAGE_QUAL_LOW_LO = 26;
    public static final int IMAGE_QUAL_MED_HI = 75;
    public static final int IMAGE_QUAL_MED_LO = 51;
    public static final int IMAGE_QUAL_UNDEF = 254;
    private static final int ROT_ANGLE_UNDEF = 65535;
    private static final int ROT_UNCERTAIN_UNDEF = 65535;
    private static final long serialVersionUID = 833541246115625112L;
    private int imageFormat;
    private int imageNumber;
    private int quality;
    private int rotationAngle;
    private int rotationAngleUncertainty;

    private static String getMimeTypeFromImageFormat(int i) {
        if (i == 2 || i == 4) {
            return ImageInfo.WSQ_MIME_TYPE;
        }
        if (i == 6 || i == 8 || i == 10 || i == 12) {
            return "image/jpeg";
        }
        if (i == 14 || i == 16) {
            return ImageInfo.JPEG2000_MIME_TYPE;
        }
        return null;
    }

    public IrisImageInfo(int i, int i2, int i3, int i4, int i5, int i6, InputStream inputStream, int i7, int i8) throws IOException {
        super(3, i5, i6, inputStream, i7, getMimeTypeFromImageFormat(i8));
        if (inputStream == null) {
            throw new IllegalArgumentException("Null image bytes");
        }
        this.imageNumber = i;
        this.quality = i2;
        this.rotationAngle = i3;
        this.rotationAngleUncertainty = i4;
    }

    public IrisImageInfo(int i, int i2, int i3, InputStream inputStream, int i4, int i5) throws IOException {
        this(i, 254, 65535, 65535, i2, i3, inputStream, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IrisImageInfo(InputStream inputStream, int i) throws IOException {
        super(3);
        this.imageFormat = i;
        setMimeType(getMimeTypeFromImageFormat(i));
        readObject(inputStream);
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public int getImageNumber() {
        return this.imageNumber;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getRotationAngle() {
        return this.rotationAngle;
    }

    public int getRotationAngleUncertainty() {
        return this.rotationAngleUncertainty;
    }

    @Override // org.jmrtd.lds.ImageInfo
    public long getRecordLength() {
        return getImageLength() + 11;
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public int hashCode() {
        return (((((((((super.hashCode() * 31) + this.imageFormat) * 31) + this.imageNumber) * 31) + this.quality) * 31) + this.rotationAngle) * 31) + this.rotationAngleUncertainty;
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        IrisImageInfo irisImageInfo = (IrisImageInfo) obj;
        return this.imageFormat == irisImageInfo.imageFormat && this.imageNumber == irisImageInfo.imageNumber && this.quality == irisImageInfo.quality && this.rotationAngle == irisImageInfo.rotationAngle && this.rotationAngleUncertainty == irisImageInfo.rotationAngleUncertainty;
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public String toString() {
        return "IrisImageInfo [image number: " + this.imageNumber + ", quality: " + this.quality + ", image: " + getWidth() + " x " + getHeight() + "mime-type: " + getMimeTypeFromImageFormat(this.imageFormat) + "]";
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    protected void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        this.imageNumber = dataInputStream.readUnsignedShort();
        this.quality = dataInputStream.readUnsignedByte();
        this.rotationAngle = dataInputStream.readShort();
        this.rotationAngleUncertainty = dataInputStream.readUnsignedShort();
        readImage(inputStream, dataInputStream.readInt() & 4294967295L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jmrtd.lds.AbstractImageInfo
    public void writeObject(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = outputStream instanceof DataOutputStream ? (DataOutputStream) outputStream : new DataOutputStream(outputStream);
        dataOutputStream.writeShort(this.imageNumber);
        dataOutputStream.writeByte(this.quality);
        dataOutputStream.writeShort(this.rotationAngle);
        dataOutputStream.writeShort(this.rotationAngleUncertainty);
        dataOutputStream.writeInt(getImageLength());
        writeImage(dataOutputStream);
    }
}
