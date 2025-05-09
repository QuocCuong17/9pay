package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.imageio.stream.MemoryCacheImageInputStream;
import net.sf.scuba.smartcards.ISO7816;
import okio.Utf8;

/* loaded from: classes3.dex */
public class TIFFOldJPEGDecompressor extends TIFFJPEGDecompressor {
    private static final boolean DEBUG = false;
    private static final int DHT = 196;
    private static final int DQT = 219;
    private static final int DRI = 221;
    private static final int SOF0 = 192;
    private static final int SOS = 218;
    private boolean isInitialized = false;
    private Long JPEGStreamOffset = null;
    private int SOFPosition = -1;
    private byte[] SOSMarker = null;
    private int subsamplingX = 2;
    private int subsamplingY = 2;

    private synchronized void initialize() throws IOException {
        char c;
        if (this.isInitialized) {
            return;
        }
        TIFFImageMetadata tIFFImageMetadata = (TIFFImageMetadata) this.metadata;
        TIFFField tIFFField = tIFFImageMetadata.getTIFFField(513);
        TIFFField tIFFField2 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_OFFSETS);
        if (tIFFField2 == null && (tIFFField2 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_STRIP_OFFSETS)) == null) {
            tIFFField2 = tIFFField;
        }
        long[] asLongs = tIFFField2.getAsLongs();
        int i = 0;
        if (!(asLongs.length > 1)) {
            this.stream.seek(this.offset);
            this.stream.mark();
            if (this.stream.read() == 255 && this.stream.read() == 216) {
                this.JPEGStreamOffset = new Long(this.offset);
                ((TIFFImageReader) this.reader).forwardWarningMessage("SOI marker detected at start of strip or tile.");
                this.isInitialized = true;
                this.stream.reset();
                return;
            }
            this.stream.reset();
            if (tIFFField != null) {
                long asLong = tIFFField.getAsLong(0);
                this.stream.mark();
                this.stream.seek(asLong);
                if (this.stream.read() == 255 && this.stream.read() == 216) {
                    this.JPEGStreamOffset = new Long(asLong);
                } else {
                    ((TIFFImageReader) this.reader).forwardWarningMessage("JPEGInterchangeFormat does not point to SOI");
                }
                this.stream.reset();
                TIFFField tIFFField3 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                if (tIFFField3 == null) {
                    ((TIFFImageReader) this.reader).forwardWarningMessage("JPEGInterchangeFormatLength field is missing");
                } else {
                    long asLong2 = tIFFField3.getAsLong(0);
                    if (asLong >= asLongs[0] || asLong + asLong2 <= asLongs[0]) {
                        ((TIFFImageReader) this.reader).forwardWarningMessage("JPEGInterchangeFormatLength field value is invalid");
                    }
                }
                if (this.JPEGStreamOffset != null) {
                    this.isInitialized = true;
                    return;
                }
            }
        }
        TIFFField tIFFField4 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING);
        if (tIFFField4 != null) {
            this.subsamplingX = tIFFField4.getAsChars()[0];
            this.subsamplingY = tIFFField4.getAsChars()[1];
        }
        if (tIFFField != null) {
            long asLong3 = tIFFField.getAsLong(0);
            TIFFField tIFFField5 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
            if (tIFFField5 != null) {
                long asLong4 = tIFFField5.getAsLong(0);
                if (asLong4 >= 2) {
                    long j = asLong3 + asLong4;
                    if (j <= asLongs[0]) {
                        this.stream.mark();
                        this.stream.seek(j - 2);
                        if (this.stream.read() == 255 && this.stream.read() == 217) {
                            this.tables = new byte[(int) (asLong4 - 2)];
                        } else {
                            this.tables = new byte[(int) asLong4];
                        }
                        this.stream.reset();
                        this.stream.mark();
                        this.stream.seek(asLong3);
                        this.stream.readFully(this.tables);
                        this.stream.reset();
                        ((TIFFImageReader) this.reader).forwardWarningMessage("Incorrect JPEG interchange format: using JPEGInterchangeFormat offset to derive tables.");
                    }
                }
                ((TIFFImageReader) this.reader).forwardWarningMessage("JPEGInterchangeFormat+JPEGInterchangeFormatLength > offset to first strip or tile.");
            }
        }
        if (this.tables == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long length = this.stream.length();
            byteArrayOutputStream.write(255);
            byteArrayOutputStream.write(216);
            TIFFField tIFFField6 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_JPEG_Q_TABLES);
            if (tIFFField6 == null) {
                throw new IIOException("JPEGQTables field missing!");
            }
            long[] asLongs2 = tIFFField6.getAsLongs();
            int i2 = 0;
            while (i2 < asLongs2.length) {
                byteArrayOutputStream.write(255);
                byteArrayOutputStream.write(DQT);
                byteArrayOutputStream.write(i);
                byteArrayOutputStream.write(67);
                byteArrayOutputStream.write(i2);
                byte[] bArr = new byte[64];
                if (length != -1 && asLongs2[i2] > length) {
                    throw new IIOException("JPEGQTables offset for index " + i2 + " is not in the stream!");
                }
                this.stream.seek(asLongs2[i2]);
                this.stream.readFully(bArr);
                byteArrayOutputStream.write(bArr);
                i2++;
                i = 0;
            }
            int i3 = 0;
            for (int i4 = 2; i3 < i4; i4 = 2) {
                int i5 = i3 == 0 ? 520 : BaselineTIFFTagSet.TAG_JPEG_AC_TABLES;
                TIFFField tIFFField7 = tIFFImageMetadata.getTIFFField(i5);
                String str = i5 == 520 ? "JPEGDCTables" : "JPEGACTables";
                if (tIFFField7 == null) {
                    throw new IIOException(str + " field missing!");
                }
                long[] asLongs3 = tIFFField7.getAsLongs();
                for (int i6 = 0; i6 < asLongs3.length; i6++) {
                    byteArrayOutputStream.write(255);
                    byteArrayOutputStream.write(196);
                    byte[] bArr2 = new byte[16];
                    if (length != -1 && asLongs3[i6] > length) {
                        throw new IIOException(str + " offset for index " + i6 + " is not in the stream!");
                    }
                    this.stream.seek(asLongs3[i6]);
                    this.stream.readFully(bArr2);
                    int i7 = 0;
                    for (int i8 = 0; i8 < 16; i8++) {
                        i7 += bArr2[i8] & 255;
                    }
                    char c2 = (char) (i7 + 19);
                    byteArrayOutputStream.write((c2 >>> '\b') & 255);
                    byteArrayOutputStream.write(c2 & 255);
                    byteArrayOutputStream.write((i3 << 4) | i6);
                    byteArrayOutputStream.write(bArr2);
                    byte[] bArr3 = new byte[i7];
                    this.stream.readFully(bArr3);
                    byteArrayOutputStream.write(bArr3);
                }
                i3++;
            }
            byteArrayOutputStream.write(-1);
            byteArrayOutputStream.write(-64);
            short s = (short) ((this.samplesPerPixel * 3) + 8);
            byteArrayOutputStream.write((byte) ((s >>> 8) & 255));
            byteArrayOutputStream.write((byte) (s & 255));
            byteArrayOutputStream.write(8);
            short s2 = (short) this.srcHeight;
            byteArrayOutputStream.write((byte) ((s2 >>> 8) & 255));
            byteArrayOutputStream.write((byte) (s2 & 255));
            short s3 = (short) this.srcWidth;
            byteArrayOutputStream.write((byte) ((s3 >>> 8) & 255));
            byteArrayOutputStream.write((byte) (s3 & 255));
            byteArrayOutputStream.write((byte) this.samplesPerPixel);
            if (this.samplesPerPixel == 1) {
                byteArrayOutputStream.write(1);
                byteArrayOutputStream.write(17);
                byteArrayOutputStream.write(0);
            } else {
                int i9 = 0;
                while (i9 < 3) {
                    int i10 = i9 + 1;
                    byteArrayOutputStream.write((byte) i10);
                    byteArrayOutputStream.write(i9 != 0 ? (byte) 17 : (byte) (((this.subsamplingX & 15) << 4) | (this.subsamplingY & 15)));
                    byteArrayOutputStream.write((byte) i9);
                    i9 = i10;
                }
            }
            TIFFField tIFFField8 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_JPEG_RESTART_INTERVAL);
            if (tIFFField8 != null && (c = tIFFField8.getAsChars()[0]) != 0) {
                byteArrayOutputStream.write(-1);
                byteArrayOutputStream.write(-35);
                byteArrayOutputStream.write((byte) 0);
                byteArrayOutputStream.write((byte) 4);
                byteArrayOutputStream.write((byte) ((c >>> '\b') & 255));
                byteArrayOutputStream.write((byte) (c & 255));
            }
            this.tables = byteArrayOutputStream.toByteArray();
        }
        int length2 = this.tables.length - 1;
        int i11 = 0;
        while (true) {
            if (i11 >= length2) {
                break;
            }
            if ((this.tables[i11] & 255) == 255 && (this.tables[i11 + 1] & 255) == 192) {
                this.SOFPosition = i11;
                break;
            }
            i11++;
        }
        if (this.SOFPosition == -1) {
            byte[] bArr4 = new byte[this.tables.length + 10 + (this.samplesPerPixel * 3)];
            System.arraycopy(this.tables, 0, bArr4, 0, this.tables.length);
            int length3 = this.tables.length;
            this.SOFPosition = this.tables.length;
            this.tables = bArr4;
            int i12 = length3 + 1;
            this.tables[length3] = -1;
            int i13 = i12 + 1;
            this.tables[i12] = ISO7816.INS_GET_RESPONSE;
            short s4 = (short) ((this.samplesPerPixel * 3) + 8);
            int i14 = i13 + 1;
            this.tables[i13] = (byte) ((s4 >>> 8) & 255);
            int i15 = i14 + 1;
            this.tables[i14] = (byte) (s4 & 255);
            int i16 = i15 + 1;
            this.tables[i15] = 8;
            short s5 = (short) this.srcHeight;
            int i17 = i16 + 1;
            this.tables[i16] = (byte) ((s5 >>> 8) & 255);
            int i18 = i17 + 1;
            this.tables[i17] = (byte) (s5 & 255);
            short s6 = (short) this.srcWidth;
            int i19 = i18 + 1;
            this.tables[i18] = (byte) ((s6 >>> 8) & 255);
            int i20 = i19 + 1;
            this.tables[i19] = (byte) (s6 & 255);
            int i21 = i20 + 1;
            this.tables[i20] = (byte) this.samplesPerPixel;
            if (this.samplesPerPixel == 1) {
                int i22 = i21 + 1;
                this.tables[i21] = 1;
                this.tables[i22] = 17;
                this.tables[i22 + 1] = 0;
            } else {
                int i23 = 0;
                while (i23 < 3) {
                    int i24 = i21 + 1;
                    int i25 = i23 + 1;
                    this.tables[i21] = (byte) i25;
                    int i26 = i24 + 1;
                    this.tables[i24] = i23 != 0 ? (byte) 17 : (byte) (((this.subsamplingX & 15) << 4) | (this.subsamplingY & 15));
                    this.tables[i26] = (byte) i23;
                    i21 = i26 + 1;
                    i23 = i25;
                }
            }
        }
        this.stream.mark();
        this.stream.seek(asLongs[0]);
        if (this.stream.read() == 255 && this.stream.read() == SOS) {
            int read = (this.stream.read() << 8) | this.stream.read();
            byte[] bArr5 = new byte[read + 2];
            this.SOSMarker = bArr5;
            bArr5[0] = -1;
            bArr5[1] = ISO7816.INS_PUT_DATA;
            bArr5[2] = (byte) ((65280 & read) >> 8);
            bArr5[3] = (byte) (read & 255);
            this.stream.readFully(this.SOSMarker, 4, read - 2);
        } else {
            byte[] bArr6 = new byte[(this.samplesPerPixel * 2) + 8];
            this.SOSMarker = bArr6;
            bArr6[0] = -1;
            bArr6[1] = ISO7816.INS_PUT_DATA;
            short s7 = (short) ((this.samplesPerPixel * 2) + 6);
            byte[] bArr7 = this.SOSMarker;
            bArr7[2] = (byte) ((s7 >>> 8) & 255);
            bArr7[3] = (byte) (s7 & 255);
            int i27 = 5;
            bArr7[4] = (byte) this.samplesPerPixel;
            if (this.samplesPerPixel == 1) {
                byte[] bArr8 = this.SOSMarker;
                bArr8[5] = 1;
                i27 = 7;
                bArr8[6] = 0;
            } else {
                int i28 = 0;
                while (i28 < 3) {
                    byte[] bArr9 = this.SOSMarker;
                    int i29 = i27 + 1;
                    int i30 = i28 + 1;
                    bArr9[i27] = (byte) i30;
                    i27 = i29 + 1;
                    bArr9[i29] = (byte) (i28 | (i28 << 4));
                    i28 = i30;
                }
            }
            byte[] bArr10 = this.SOSMarker;
            int i31 = i27 + 1;
            bArr10[i27] = 0;
            bArr10[i31] = Utf8.REPLACEMENT_BYTE;
            bArr10[i31 + 1] = 0;
        }
        this.stream.reset();
        this.isInitialized = true;
    }

    @Override // com.github.jaiimageio.impl.plugins.tiff.TIFFJPEGDecompressor, com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        initialize();
        if (this.JPEGStreamOffset != null) {
            this.stream.seek(this.JPEGStreamOffset.longValue());
            this.JPEGReader.setInput(this.stream, false, true);
        } else {
            int length = this.tables.length;
            byte[] bArr2 = new byte[this.SOSMarker.length + length + this.byteCount + 2];
            if (this.tables != null) {
                System.arraycopy(this.tables, 0, bArr2, 0, length);
            }
            short s = (short) this.srcHeight;
            int i4 = this.SOFPosition;
            bArr2[i4 + 5] = (byte) ((s >>> 8) & 255);
            bArr2[i4 + 6] = (byte) (s & 255);
            short s2 = (short) this.srcWidth;
            int i5 = this.SOFPosition;
            bArr2[i5 + 7] = (byte) ((s2 >>> 8) & 255);
            bArr2[i5 + 8] = (byte) (s2 & 255);
            this.stream.seek(this.offset);
            byte[] bArr3 = new byte[2];
            this.stream.readFully(bArr3);
            if ((bArr3[0] & 255) != 255 || (bArr3[1] & 255) != SOS) {
                byte[] bArr4 = this.SOSMarker;
                System.arraycopy(bArr4, 0, bArr2, length, bArr4.length);
                length += this.SOSMarker.length;
            }
            int i6 = length + 1;
            bArr2[length] = bArr3[0];
            int i7 = i6 + 1;
            bArr2[i6] = bArr3[1];
            this.stream.readFully(bArr2, i7, this.byteCount - 2);
            int i8 = i7 + (this.byteCount - 2);
            int i9 = i8 + 1;
            bArr2[i8] = -1;
            bArr2[i9] = -39;
            this.JPEGReader.setInput(new MemoryCacheImageInputStream(new ByteArrayInputStream(bArr2, 0, i9 + 1)), true, true);
        }
        this.JPEGParam.setDestination(this.rawImage);
        this.JPEGReader.read(0, this.JPEGParam);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.jaiimageio.impl.plugins.tiff.TIFFJPEGDecompressor
    public void finalize() throws Throwable {
        super.finalize();
        this.JPEGReader.dispose();
    }
}
