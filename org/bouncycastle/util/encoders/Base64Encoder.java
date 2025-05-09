package org.bouncycastle.util.encoders;

import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.io.IOException;
import java.io.OutputStream;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;

/* loaded from: classes6.dex */
public class Base64Encoder implements Encoder {
    protected final byte[] encodingTable = {65, 66, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 75, 76, AvcSpsUtils.PROFILE_IDC_MAIN, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, AvcSpsUtils.PROFILE_IDC_EXTENDED, 89, 90, 97, ISOFileInfo.FCP_BYTE, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, ISOFileInfo.FCI_BYTE, ISO7816.INS_MANAGE_CHANNEL, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = kotlin.io.encoding.Base64.padSymbol;
    protected final byte[] decodingTable = new byte[128];

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        byte b = this.padding;
        if (c3 == b) {
            if (c4 != b) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            byte[] bArr = this.decodingTable;
            byte b2 = bArr[c];
            byte b3 = bArr[c2];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b2 << 2) | (b3 >> 4));
            return 1;
        }
        if (c4 == b) {
            byte[] bArr2 = this.decodingTable;
            byte b4 = bArr2[c];
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            if ((b4 | b5 | b6) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b4 << 2) | (b5 >> 4));
            outputStream.write((b5 << 4) | (b6 >> 2));
            return 2;
        }
        byte[] bArr3 = this.decodingTable;
        byte b7 = bArr3[c];
        byte b8 = bArr3[c2];
        byte b9 = bArr3[c3];
        byte b10 = bArr3[c4];
        if ((b7 | b8 | b9 | b10) < 0) {
            throw new IOException("invalid characters encountered at end of base64 data");
        }
        outputStream.write((b7 << 2) | (b8 >> 4));
        outputStream.write((b8 << 4) | (b9 >> 2));
        outputStream.write((b9 << 6) | b10);
        return 3;
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    private int nextI(String str, int i, int i2) {
        while (i < i2 && ignore(str.charAt(i))) {
            i++;
        }
        return i;
    }

    private int nextI(byte[] bArr, int i, int i2) {
        while (i < i2 && ignore((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        if (length == 0) {
            return 0;
        }
        int i2 = length;
        int i3 = 0;
        while (i2 > 0 && i3 != 4) {
            if (!ignore(str.charAt(i2 - 1))) {
                i3++;
            }
            i2--;
        }
        int nextI = nextI(str, 0, i2);
        while (nextI < i2) {
            int i4 = nextI + 1;
            byte b = this.decodingTable[str.charAt(nextI)];
            int nextI2 = nextI(str, i4, i2);
            int i5 = nextI2 + 1;
            byte b2 = this.decodingTable[str.charAt(nextI2)];
            int nextI3 = nextI(str, i5, i2);
            int i6 = nextI3 + 1;
            byte b3 = this.decodingTable[str.charAt(nextI3)];
            int nextI4 = nextI(str, i6, i2);
            int i7 = nextI4 + 1;
            byte b4 = this.decodingTable[str.charAt(nextI4)];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i += 3;
            nextI = nextI(str, i7, i2);
        }
        int nextI5 = nextI(str, nextI, length);
        int nextI6 = nextI(str, nextI5 + 1, length);
        int nextI7 = nextI(str, nextI6 + 1, length);
        return i + decodeLastBlock(outputStream, str.charAt(nextI5), str.charAt(nextI6), str.charAt(nextI7), str.charAt(nextI(str, nextI7 + 1, length)));
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3 = i2 + i;
        while (i3 > i && ignore((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        if (i3 == 0) {
            return 0;
        }
        int i5 = i3;
        int i6 = 0;
        while (i5 > i && i6 != 4) {
            if (!ignore((char) bArr[i5 - 1])) {
                i6++;
            }
            i5--;
        }
        int nextI = nextI(bArr, i, i5);
        while (nextI < i5) {
            int i7 = nextI + 1;
            byte b = this.decodingTable[bArr[nextI]];
            int nextI2 = nextI(bArr, i7, i5);
            int i8 = nextI2 + 1;
            byte b2 = this.decodingTable[bArr[nextI2]];
            int nextI3 = nextI(bArr, i8, i5);
            int i9 = nextI3 + 1;
            byte b3 = this.decodingTable[bArr[nextI3]];
            int nextI4 = nextI(bArr, i9, i5);
            int i10 = nextI4 + 1;
            byte b4 = this.decodingTable[bArr[nextI4]];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i4 += 3;
            nextI = nextI(bArr, i10, i5);
        }
        int nextI5 = nextI(bArr, nextI, i3);
        int nextI6 = nextI(bArr, nextI5 + 1, i3);
        int nextI7 = nextI(bArr, nextI6 + 1, i3);
        return i4 + decodeLastBlock(outputStream, (char) bArr[nextI5], (char) bArr[nextI6], (char) bArr[nextI7], (char) bArr[nextI(bArr, nextI7 + 1, i3)]);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a3  */
    @Override // org.bouncycastle.util.encoders.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3;
        byte b;
        int i4 = i2 % 3;
        int i5 = i2 - i4;
        int i6 = i;
        while (true) {
            i3 = i + i5;
            if (i6 >= i3) {
                break;
            }
            int i7 = bArr[i6] & 255;
            int i8 = bArr[i6 + 1] & 255;
            int i9 = bArr[i6 + 2] & 255;
            outputStream.write(this.encodingTable[(i7 >>> 2) & 63]);
            outputStream.write(this.encodingTable[((i7 << 4) | (i8 >>> 4)) & 63]);
            outputStream.write(this.encodingTable[((i8 << 2) | (i9 >>> 6)) & 63]);
            outputStream.write(this.encodingTable[i9 & 63]);
            i6 += 3;
        }
        if (i4 != 1) {
            if (i4 == 2) {
                int i10 = bArr[i3] & 255;
                int i11 = bArr[i3 + 1] & 255;
                outputStream.write(this.encodingTable[(i10 >>> 2) & 63]);
                outputStream.write(this.encodingTable[((i10 << 4) | (i11 >>> 4)) & 63]);
                b = this.encodingTable[(i11 << 2) & 63];
            }
            return ((i5 / 3) * 4) + (i4 == 0 ? 0 : 4);
        }
        int i12 = bArr[i3] & 255;
        outputStream.write(this.encodingTable[(i12 >>> 2) & 63]);
        outputStream.write(this.encodingTable[(i12 << 4) & 63]);
        b = this.padding;
        outputStream.write(b);
        outputStream.write(this.padding);
        return ((i5 / 3) * 4) + (i4 == 0 ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialiseDecodingTable() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }
}
