package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class GlyfSimpleDescript extends GlyfDescript {
    private int[] endPtsOfContours;
    private byte[] flags;
    private final int pointCount;
    private short[] xCoordinates;
    private short[] yCoordinates;

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public boolean isComposite() {
        return false;
    }

    public GlyfSimpleDescript(short s, TTFDataStream tTFDataStream) throws IOException {
        super(s, tTFDataStream);
        if (s == 0) {
            this.pointCount = 0;
            return;
        }
        int[] readUnsignedShortArray = tTFDataStream.readUnsignedShortArray(s);
        this.endPtsOfContours = readUnsignedShortArray;
        int i = readUnsignedShortArray[s - 1] + 1;
        this.pointCount = i;
        this.flags = new byte[i];
        this.xCoordinates = new short[i];
        this.yCoordinates = new short[i];
        readInstructions(tTFDataStream, tTFDataStream.readUnsignedShort());
        readFlags(i, tTFDataStream);
        readCoords(i, tTFDataStream);
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getEndPtOfContours(int i) {
        return this.endPtsOfContours[i];
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public byte getFlags(int i) {
        return this.flags[i];
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public short getXCoordinate(int i) {
        return this.xCoordinates[i];
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public short getYCoordinate(int i) {
        return this.yCoordinates[i];
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getPointCount() {
        return this.pointCount;
    }

    private void readCoords(int i, TTFDataStream tTFDataStream) throws IOException {
        short readSignedShort;
        int i2;
        short readSignedShort2;
        int i3;
        short s = 0;
        for (int i4 = 0; i4 < i; i4++) {
            byte[] bArr = this.flags;
            if ((bArr[i4] & 16) != 0) {
                if ((bArr[i4] & 2) != 0) {
                    i3 = tTFDataStream.readUnsignedByte();
                } else {
                    this.xCoordinates[i4] = s;
                }
            } else if ((bArr[i4] & 2) != 0) {
                i3 = -((short) tTFDataStream.readUnsignedByte());
            } else {
                readSignedShort2 = tTFDataStream.readSignedShort();
                s = (short) (s + readSignedShort2);
                this.xCoordinates[i4] = s;
            }
            readSignedShort2 = (short) i3;
            s = (short) (s + readSignedShort2);
            this.xCoordinates[i4] = s;
        }
        short s2 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            byte[] bArr2 = this.flags;
            if ((bArr2[i5] & 32) != 0) {
                if ((bArr2[i5] & 4) != 0) {
                    i2 = tTFDataStream.readUnsignedByte();
                } else {
                    this.yCoordinates[i5] = s2;
                }
            } else if ((bArr2[i5] & 4) != 0) {
                i2 = -((short) tTFDataStream.readUnsignedByte());
            } else {
                readSignedShort = tTFDataStream.readSignedShort();
                s2 = (short) (s2 + readSignedShort);
                this.yCoordinates[i5] = s2;
            }
            readSignedShort = (short) i2;
            s2 = (short) (s2 + readSignedShort);
            this.yCoordinates[i5] = s2;
        }
    }

    private void readFlags(int i, TTFDataStream tTFDataStream) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            this.flags[i2] = (byte) tTFDataStream.readUnsignedByte();
            if ((this.flags[i2] & 8) != 0) {
                int readUnsignedByte = tTFDataStream.readUnsignedByte();
                for (int i3 = 1; i3 <= readUnsignedByte; i3++) {
                    byte[] bArr = this.flags;
                    bArr[i2 + i3] = bArr[i2];
                }
                i2 += readUnsignedByte;
            }
            i2++;
        }
    }
}
