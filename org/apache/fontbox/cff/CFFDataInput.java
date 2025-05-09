package org.apache.fontbox.cff;

import java.io.IOException;

/* loaded from: classes5.dex */
public class CFFDataInput extends DataInput {
    public CFFDataInput(byte[] bArr) {
        super(bArr);
    }

    public int readCard8() throws IOException {
        return readUnsignedByte();
    }

    public int readCard16() throws IOException {
        return readUnsignedShort();
    }

    public int readOffset(int i) throws IOException {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) | readUnsignedByte();
        }
        return i2;
    }

    public int readOffSize() throws IOException {
        return readUnsignedByte();
    }

    public int readSID() throws IOException {
        return readUnsignedShort();
    }
}
