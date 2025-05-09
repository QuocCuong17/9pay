package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes4.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0040. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0127 A[LOOP:0: B:2:0x0022->B:23:0x0127, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ef A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode forBits;
        int i;
        int i2;
        int i3;
        boolean z;
        boolean z2;
        int i4;
        Mode mode;
        boolean z3;
        boolean z4;
        int i5;
        int i6;
        int i7;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i8 = 1;
        ArrayList arrayList = new ArrayList(1);
        int i9 = -1;
        int i10 = -1;
        boolean z5 = 0;
        boolean z6 = false;
        boolean z7 = false;
        CharacterSetECI characterSetECI = null;
        while (true) {
            try {
                if (bitSource.available() < 4) {
                    forBits = Mode.TERMINATOR;
                } else {
                    forBits = Mode.forBits(bitSource.readBits(4));
                }
                Mode mode2 = forBits;
                switch (mode2) {
                    case TERMINATOR:
                        i = 3;
                        i2 = 2;
                        mode = mode2;
                        i3 = 4;
                        i5 = z5;
                        z4 = z6;
                        z3 = z7;
                        if (mode == Mode.TERMINATOR) {
                            if (characterSetECI != null) {
                                if (z4) {
                                    i6 = i3;
                                } else if (z3) {
                                    i7 = 6;
                                    i6 = i7;
                                } else {
                                    i6 = i2;
                                }
                            } else if (z4) {
                                i6 = i;
                            } else if (z3) {
                                i7 = 5;
                                i6 = i7;
                            } else {
                                i6 = 1;
                            }
                            return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel != null ? errorCorrectionLevel.toString() : null, i9, i10, i6);
                        }
                        i8 = 1;
                        z5 = i5;
                        z6 = z4;
                        z7 = z3;
                    case FNC1_FIRST_POSITION:
                        i = 3;
                        i2 = 2;
                        i3 = 4;
                        int i11 = i8;
                        z = i11 == true ? 1 : 0;
                        i4 = i11;
                        z2 = z7;
                        mode = mode2;
                        i5 = i4;
                        z4 = z;
                        z3 = z2;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case FNC1_SECOND_POSITION:
                        i = 3;
                        i2 = 2;
                        i3 = 4;
                        int i12 = i8;
                        z2 = i12 == true ? 1 : 0;
                        i4 = i12;
                        z = z6;
                        mode = mode2;
                        i5 = i4;
                        z4 = z;
                        z3 = z2;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case STRUCTURED_APPEND:
                        if (bitSource.available() < 16) {
                            throw FormatException.getFormatInstance();
                        }
                        int readBits = bitSource.readBits(8);
                        i10 = bitSource.readBits(8);
                        i9 = readBits;
                        i = 3;
                        i2 = 2;
                        mode = mode2;
                        i3 = 4;
                        i5 = z5;
                        z4 = z6;
                        z3 = z7;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case ECI:
                        characterSetECI = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                        if (characterSetECI == null) {
                            throw FormatException.getFormatInstance();
                        }
                        i = 3;
                        i2 = 2;
                        mode = mode2;
                        i3 = 4;
                        i5 = z5;
                        z4 = z6;
                        z3 = z7;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case HANZI:
                        int readBits2 = bitSource.readBits(4);
                        int readBits3 = bitSource.readBits(mode2.getCharacterCountBits(version));
                        if (readBits2 == i8) {
                            decodeHanziSegment(bitSource, sb, readBits3);
                        }
                        i = 3;
                        i2 = 2;
                        mode = mode2;
                        i3 = 4;
                        i5 = z5;
                        z4 = z6;
                        z3 = z7;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    default:
                        int readBits4 = bitSource.readBits(mode2.getCharacterCountBits(version));
                        int i13 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode2.ordinal()];
                        if (i13 == i8) {
                            i = 3;
                            i2 = 2;
                            mode = mode2;
                            i3 = 4;
                            decodeNumericSegment(bitSource, sb, readBits4);
                            i5 = z5;
                            z4 = z6;
                            z3 = z7;
                        } else if (i13 == 2) {
                            i = 3;
                            i2 = 2;
                            mode = mode2;
                            i3 = 4;
                            decodeAlphanumericSegment(bitSource, sb, readBits4, z5);
                            i5 = z5;
                            z4 = z6;
                            z3 = z7;
                        } else if (i13 == 3) {
                            i = 3;
                            i2 = 2;
                            mode = mode2;
                            i3 = 4;
                            decodeByteSegment(bitSource, sb, readBits4, characterSetECI, arrayList, map);
                            i5 = z5;
                            z4 = z6;
                            z3 = z7;
                        } else if (i13 == 4) {
                            decodeKanjiSegment(bitSource, sb, readBits4);
                            i = 3;
                            i2 = 2;
                            mode = mode2;
                            i3 = 4;
                            i5 = z5;
                            z4 = z6;
                            z3 = z7;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                }
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % 96) | ((readBits / 96) << 8);
            int i4 = i3 + (i3 < 2560 ? 41377 : 42657);
            bArr[i2] = (byte) ((i4 >> 8) & 255);
            bArr[i2 + 1] = (byte) (i4 & 255);
            i2 += 2;
            i--;
        }
        sb.append(new String(bArr, StringUtils.GB2312_CHARSET));
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % 192) | ((readBits / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        sb.append(new String(bArr, StringUtils.SHIFT_JIS_CHARSET));
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        Charset charset;
        if (i * 8 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) bitSource.readBits(8);
        }
        if (characterSetECI == null) {
            charset = StringUtils.guessCharset(bArr, map);
        } else {
            charset = characterSetECI.getCharset();
        }
        sb.append(new String(bArr, charset));
        collection.add(bArr);
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i >= cArr.length) {
            throw FormatException.getFormatInstance();
        }
        return cArr[i];
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(readBits / 45));
            sb.append(toAlphaNumericChar(readBits % 45));
            i -= 2;
        }
        if (i == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(10);
            if (readBits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits / 100));
            sb.append(toAlphaNumericChar((readBits / 10) % 10));
            sb.append(toAlphaNumericChar(readBits % 10));
            i -= 3;
        }
        if (i == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int readBits2 = bitSource.readBits(7);
            if (readBits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits2 / 10));
            sb.append(toAlphaNumericChar(readBits2 % 10));
            return;
        }
        if (i == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int readBits3 = bitSource.readBits(4);
            if (readBits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
