package com.google.firebase.firestore.index;

import androidx.media3.extractor.ts.PsExtractor;
import com.google.protobuf.ByteString;
import java.math.RoundingMode;
import java.util.Arrays;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes4.dex */
public class OrderedCodeWriter {
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final long DOUBLE_ALL_BITS = -1;
    public static final long DOUBLE_SIGN_MASK = Long.MIN_VALUE;
    public static final byte ESCAPE1 = 0;
    public static final byte ESCAPE2 = -1;
    public static final byte FF_BYTE = 0;
    public static final byte INFINITY = -1;
    private static final byte[][] LENGTH_TO_HEADER_BITS = {new byte[]{0, 0}, new byte[]{Byte.MIN_VALUE, 0}, new byte[]{ISO7816.INS_GET_RESPONSE, 0}, new byte[]{ISO7816.INS_CREATE_FILE, 0}, new byte[]{-16, 0}, new byte[]{-8, 0}, new byte[]{-4, 0}, new byte[]{-2, 0}, new byte[]{-1, 0}, new byte[]{-1, Byte.MIN_VALUE}, new byte[]{-1, ISO7816.INS_GET_RESPONSE}};
    public static final byte NULL_BYTE = -1;
    public static final byte SEPARATOR = 1;
    private int position = 0;
    private byte[] buffer = new byte[1024];

    public void writeBytesAscending(ByteString byteString) {
        for (int i = 0; i < byteString.size(); i++) {
            writeByteAscending(byteString.byteAt(i));
        }
        writeSeparatorAscending();
    }

    public void writeBytesDescending(ByteString byteString) {
        for (int i = 0; i < byteString.size(); i++) {
            writeByteDescending(byteString.byteAt(i));
        }
        writeSeparatorDescending();
    }

    public void writeUtf8Ascending(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                writeByteAscending((byte) charAt);
            } else if (charAt < 2048) {
                writeByteAscending((byte) ((charAt >>> 6) | 960));
                writeByteAscending((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                writeByteAscending((byte) ((charAt >>> '\f') | 480));
                writeByteAscending((byte) (((charAt >>> 6) & 63) | 128));
                writeByteAscending((byte) ((charAt & '?') | 128));
            } else {
                int codePointAt = Character.codePointAt(charSequence, i);
                i++;
                writeByteAscending((byte) ((codePointAt >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                writeByteAscending((byte) (((codePointAt >>> 12) & 63) | 128));
                writeByteAscending((byte) (((codePointAt >>> 6) & 63) | 128));
                writeByteAscending((byte) ((codePointAt & 63) | 128));
            }
            i++;
        }
        writeSeparatorAscending();
    }

    public void writeUtf8Descending(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                writeByteDescending((byte) charAt);
            } else if (charAt < 2048) {
                writeByteDescending((byte) ((charAt >>> 6) | 960));
                writeByteDescending((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                writeByteDescending((byte) ((charAt >>> '\f') | 480));
                writeByteDescending((byte) (((charAt >>> 6) & 63) | 128));
                writeByteDescending((byte) ((charAt & '?') | 128));
            } else {
                int codePointAt = Character.codePointAt(charSequence, i);
                i++;
                writeByteDescending((byte) ((codePointAt >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                writeByteDescending((byte) (((codePointAt >>> 12) & 63) | 128));
                writeByteDescending((byte) (((codePointAt >>> 6) & 63) | 128));
                writeByteDescending((byte) ((codePointAt & 63) | 128));
            }
            i++;
        }
        writeSeparatorDescending();
    }

    public void writeUnsignedLongAscending(long j) {
        int unsignedNumLength = unsignedNumLength(j);
        ensureAvailable(unsignedNumLength + 1);
        byte[] bArr = this.buffer;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        bArr[i] = (byte) unsignedNumLength;
        int i3 = i2 + unsignedNumLength;
        while (true) {
            i3--;
            int i4 = this.position;
            if (i3 >= i4) {
                this.buffer[i3] = (byte) (255 & j);
                j >>>= 8;
            } else {
                this.position = i4 + unsignedNumLength;
                return;
            }
        }
    }

    public void writeUnsignedLongDescending(long j) {
        int unsignedNumLength = unsignedNumLength(j);
        ensureAvailable(unsignedNumLength + 1);
        byte[] bArr = this.buffer;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        bArr[i] = (byte) (~unsignedNumLength);
        int i3 = i2 + unsignedNumLength;
        while (true) {
            i3--;
            int i4 = this.position;
            if (i3 >= i4) {
                this.buffer[i3] = (byte) (~(255 & j));
                j >>>= 8;
            } else {
                this.position = i4 + unsignedNumLength;
                return;
            }
        }
    }

    public void writeSignedLongAscending(long j) {
        int i;
        long j2 = j < 0 ? ~j : j;
        if (j2 < 64) {
            ensureAvailable(1);
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (j ^ LENGTH_TO_HEADER_BITS[1][0]);
            return;
        }
        int signedNumLength = signedNumLength(j2);
        ensureAvailable(signedNumLength);
        if (signedNumLength < 2) {
            throw new AssertionError(String.format("Invalid length (%d) returned by signedNumLength", Integer.valueOf(signedNumLength)));
        }
        byte b = j < 0 ? (byte) -1 : (byte) 0;
        int i3 = this.position;
        if (signedNumLength == 10) {
            i = i3 + 2;
            byte[] bArr2 = this.buffer;
            bArr2[i3] = b;
            bArr2[i3 + 1] = b;
        } else if (signedNumLength == 9) {
            i = i3 + 1;
            this.buffer[i3] = b;
        } else {
            i = i3;
        }
        for (int i4 = (signedNumLength - 1) + i3; i4 >= i; i4--) {
            this.buffer[i4] = (byte) (255 & j);
            j >>= 8;
        }
        byte[] bArr3 = this.buffer;
        int i5 = this.position;
        byte b2 = bArr3[i5];
        byte[][] bArr4 = LENGTH_TO_HEADER_BITS;
        bArr3[i5] = (byte) (b2 ^ bArr4[signedNumLength][0]);
        int i6 = i5 + 1;
        bArr3[i6] = (byte) (bArr4[signedNumLength][1] ^ bArr3[i6]);
        this.position = i5 + signedNumLength;
    }

    public void writeSignedLongDescending(long j) {
        writeSignedLongAscending(~j);
    }

    public void writeDoubleAscending(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        writeUnsignedLongAscending(doubleToLongBits ^ (doubleToLongBits < 0 ? -1L : Long.MIN_VALUE));
    }

    public void writeDoubleDescending(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        writeUnsignedLongDescending(doubleToLongBits ^ (doubleToLongBits < 0 ? -1L : Long.MIN_VALUE));
    }

    public void writeInfinityAscending() {
        writeEscapedByteAscending((byte) -1);
        writeEscapedByteAscending((byte) -1);
    }

    public void writeInfinityDescending() {
        writeEscapedByteDescending((byte) -1);
        writeEscapedByteDescending((byte) -1);
    }

    public void reset() {
        this.position = 0;
    }

    public byte[] encodedBytes() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    private void writeByteAscending(byte b) {
        if (b == 0) {
            writeEscapedByteAscending((byte) 0);
            writeEscapedByteAscending((byte) -1);
        } else if (b == -1) {
            writeEscapedByteAscending((byte) -1);
            writeEscapedByteAscending((byte) 0);
        } else {
            writeEscapedByteAscending(b);
        }
    }

    private void writeByteDescending(byte b) {
        if (b == 0) {
            writeEscapedByteDescending((byte) 0);
            writeEscapedByteDescending((byte) -1);
        } else if (b == -1) {
            writeEscapedByteDescending((byte) -1);
            writeEscapedByteDescending((byte) 0);
        } else {
            writeEscapedByteDescending(b);
        }
    }

    private void writeSeparatorAscending() {
        writeEscapedByteAscending((byte) 0);
        writeEscapedByteAscending((byte) 1);
    }

    private void writeSeparatorDescending() {
        writeEscapedByteDescending((byte) 0);
        writeEscapedByteDescending((byte) 1);
    }

    private void writeEscapedByteAscending(byte b) {
        ensureAvailable(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    private void writeEscapedByteDescending(byte b) {
        ensureAvailable(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (~b);
    }

    private void ensureAvailable(int i) {
        int i2 = i + this.position;
        byte[] bArr = this.buffer;
        if (i2 <= bArr.length) {
            return;
        }
        int length = bArr.length * 2;
        if (length >= i2) {
            i2 = length;
        }
        this.buffer = Arrays.copyOf(bArr, i2);
    }

    private int signedNumLength(long j) {
        if (j < 0) {
            j = ~j;
        }
        return IntMath.divide((64 - Long.numberOfLeadingZeros(j)) + 1, 7, RoundingMode.UP);
    }

    private int unsignedNumLength(long j) {
        return IntMath.divide(64 - Long.numberOfLeadingZeros(j), 8, RoundingMode.UP);
    }

    public void seed(byte[] bArr) {
        ensureAvailable(bArr.length);
        for (byte b : bArr) {
            byte[] bArr2 = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr2[i] = b;
        }
    }
}
