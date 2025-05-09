package net.sf.scuba.smartcards;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class CommandAPDU implements Serializable {
    private static final int MAX_APDU_SIZE = 65544;
    private static final long serialVersionUID = 398698301286670877L;
    private byte[] apdu;
    private transient int dataOffset;
    private transient int nc;
    private transient int ne;

    public CommandAPDU(byte[] bArr) {
        this.apdu = (byte[]) bArr.clone();
        parse();
    }

    public CommandAPDU(byte[] bArr, int i, int i2) {
        checkArrayBounds(bArr, i, i2);
        byte[] bArr2 = new byte[i2];
        this.apdu = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
        parse();
    }

    private void checkArrayBounds(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Offset and length must not be negative");
        }
        if (bArr != null) {
            if (i > bArr.length - i2) {
                throw new IllegalArgumentException("Offset plus length exceed array size");
            }
        } else if (i != 0 && i2 != 0) {
            throw new IllegalArgumentException("offset and length must be 0 if array is null");
        }
    }

    public CommandAPDU(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        this.apdu = bArr;
        byteBuffer.get(bArr);
        parse();
    }

    public CommandAPDU(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0, 0, 0);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, i4, null, 0, 0, i5);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr) {
        this(i, i2, i3, i4, bArr, 0, arrayLength(bArr), 0);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6) {
        this(i, i2, i3, i4, bArr, i5, i6, 0);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr, int i5) {
        this(i, i2, i3, i4, bArr, 0, arrayLength(bArr), i5);
    }

    private static int arrayLength(byte[] bArr) {
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    private void parse() {
        byte[] bArr = this.apdu;
        if (bArr.length < 4) {
            throw new IllegalArgumentException("apdu must be at least 4 bytes long");
        }
        if (bArr.length == 4) {
            return;
        }
        int i = bArr[4] & 255;
        if (bArr.length == 5) {
            if (i == 0) {
                i = 256;
            }
            this.ne = i;
            return;
        }
        if (i != 0) {
            if (bArr.length == i + 5) {
                this.nc = i;
                this.dataOffset = 5;
                return;
            }
            if (bArr.length == i + 6) {
                this.nc = i;
                this.dataOffset = 5;
                int i2 = bArr[bArr.length - 1] & 255;
                this.ne = i2 != 0 ? i2 : 256;
                return;
            }
            throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + i);
        }
        if (bArr.length < 7) {
            throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + i);
        }
        int i3 = ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        if (bArr.length == 7) {
            if (i3 == 0) {
                i3 = 65536;
            }
            this.ne = i3;
            return;
        }
        if (i3 == 0) {
            throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + i + ", b2||b3=" + i3);
        }
        if (bArr.length == i3 + 7) {
            this.nc = i3;
            this.dataOffset = 7;
            return;
        }
        if (bArr.length == i3 + 9) {
            this.nc = i3;
            this.dataOffset = 7;
            int length = bArr.length - 2;
            int i4 = (bArr[length + 1] & 255) | ((bArr[length] & 255) << 8);
            this.ne = i4 != 0 ? i4 : 65536;
            return;
        }
        throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + i + ", b2||b3=" + i3);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6, int i7) {
        byte b;
        checkArrayBounds(bArr, i5, i6);
        if (i6 > 65535) {
            throw new IllegalArgumentException("dataLength is too large");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("ne must not be negative");
        }
        if (i7 > 65536) {
            throw new IllegalArgumentException("ne is too large");
        }
        this.ne = i7;
        this.nc = i6;
        if (i6 == 0) {
            if (i7 == 0) {
                this.apdu = new byte[4];
                setHeader(i, i2, i3, i4);
                return;
            }
            if (i7 <= 256) {
                r9 = i7 != 256 ? (byte) i7 : (byte) 0;
                this.apdu = new byte[5];
                setHeader(i, i2, i3, i4);
                this.apdu[4] = r9;
                return;
            }
            if (i7 == 65536) {
                b = 0;
            } else {
                r9 = (byte) (i7 >> 8);
                b = (byte) i7;
            }
            this.apdu = new byte[7];
            setHeader(i, i2, i3, i4);
            byte[] bArr2 = this.apdu;
            bArr2[5] = r9;
            bArr2[6] = b;
            return;
        }
        if (i7 == 0) {
            if (i6 <= 255) {
                this.apdu = new byte[i6 + 5];
                setHeader(i, i2, i3, i4);
                byte[] bArr3 = this.apdu;
                bArr3[4] = (byte) i6;
                this.dataOffset = 5;
                System.arraycopy(bArr, i5, bArr3, 5, i6);
                return;
            }
            this.apdu = new byte[i6 + 7];
            setHeader(i, i2, i3, i4);
            byte[] bArr4 = this.apdu;
            bArr4[4] = 0;
            bArr4[5] = (byte) (i6 >> 8);
            bArr4[6] = (byte) i6;
            this.dataOffset = 7;
            System.arraycopy(bArr, i5, bArr4, 7, i6);
            return;
        }
        if (i6 <= 255 && i7 <= 256) {
            this.apdu = new byte[i6 + 6];
            setHeader(i, i2, i3, i4);
            byte[] bArr5 = this.apdu;
            bArr5[4] = (byte) i6;
            this.dataOffset = 5;
            System.arraycopy(bArr, i5, bArr5, 5, i6);
            this.apdu[r1.length - 1] = i7 != 256 ? (byte) i7 : (byte) 0;
            return;
        }
        this.apdu = new byte[i6 + 9];
        setHeader(i, i2, i3, i4);
        byte[] bArr6 = this.apdu;
        bArr6[4] = 0;
        bArr6[5] = (byte) (i6 >> 8);
        bArr6[6] = (byte) i6;
        this.dataOffset = 7;
        System.arraycopy(bArr, i5, bArr6, 7, i6);
        if (i7 != 65536) {
            byte[] bArr7 = this.apdu;
            int length = bArr7.length - 2;
            bArr7[length] = (byte) (i7 >> 8);
            bArr7[length + 1] = (byte) i7;
        }
    }

    private void setHeader(int i, int i2, int i3, int i4) {
        byte[] bArr = this.apdu;
        bArr[0] = (byte) i;
        bArr[1] = (byte) i2;
        bArr[2] = (byte) i3;
        bArr[3] = (byte) i4;
    }

    public int getCLA() {
        return this.apdu[0] & 255;
    }

    public int getINS() {
        return this.apdu[1] & 255;
    }

    public int getP1() {
        return this.apdu[2] & 255;
    }

    public int getP2() {
        return this.apdu[3] & 255;
    }

    public int getNc() {
        return this.nc;
    }

    public byte[] getData() {
        int i = this.nc;
        byte[] bArr = new byte[i];
        System.arraycopy(this.apdu, this.dataOffset, bArr, 0, i);
        return bArr;
    }

    public int getNe() {
        return this.ne;
    }

    public byte[] getBytes() {
        return (byte[]) this.apdu.clone();
    }

    public String toString() {
        return "CommmandAPDU: " + this.apdu.length + " bytes, nc=" + this.nc + ", ne=" + this.ne;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CommandAPDU) {
            return Arrays.equals(this.apdu, ((CommandAPDU) obj).apdu);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.apdu);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.apdu = (byte[]) objectInputStream.readUnshared();
        parse();
    }
}
