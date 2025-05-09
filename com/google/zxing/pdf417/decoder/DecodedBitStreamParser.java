package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.ECIStringBuilder;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
            i++;
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x001f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x0022. Please report as an issue. */
    public static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int i;
        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder(iArr.length * 2);
        int textCompaction = textCompaction(iArr, 1, eCIStringBuilder);
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        while (textCompaction < iArr[0]) {
            int i2 = textCompaction + 1;
            int i3 = iArr[textCompaction];
            if (i3 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i3) {
                    case 900:
                        textCompaction = textCompaction(iArr, i2, eCIStringBuilder);
                        break;
                    case 901:
                        textCompaction = byteCompaction(i3, iArr, i2, eCIStringBuilder);
                        break;
                    case 902:
                        textCompaction = numericCompaction(iArr, i2, eCIStringBuilder);
                        break;
                    default:
                        switch (i3) {
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                textCompaction = byteCompaction(i3, iArr, i2, eCIStringBuilder);
                                break;
                            case ECI_USER_DEFINED /* 925 */:
                                i = i2 + 1;
                                textCompaction = i;
                                break;
                            case ECI_GENERAL_PURPOSE /* 926 */:
                                i = i2 + 2;
                                textCompaction = i;
                                break;
                            case ECI_CHARSET /* 927 */:
                                textCompaction = i2 + 1;
                                eCIStringBuilder.appendECI(iArr[i2]);
                                break;
                            case 928:
                                textCompaction = decodeMacroBlock(iArr, i2, pDF417ResultMetadata);
                                break;
                            default:
                                textCompaction = textCompaction(iArr, i2 - 1, eCIStringBuilder);
                                break;
                        }
                }
            } else {
                textCompaction = i2 + 1;
                eCIStringBuilder.append((char) iArr[i2]);
            }
        }
        if (eCIStringBuilder.isEmpty() && pDF417ResultMetadata.getFileId() == null) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, eCIStringBuilder.toString(), null, str);
        decoderResult.setOther(pDF417ResultMetadata);
        return decoderResult;
    }

    static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        String decodeBase900toBase10 = decodeBase900toBase10(iArr2, 2);
        if (decodeBase900toBase10.isEmpty()) {
            pDF417ResultMetadata.setSegmentIndex(0);
        } else {
            try {
                pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10));
            } catch (NumberFormatException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (i < iArr[0] && i < iArr.length && iArr[i] != MACRO_PDF417_TERMINATOR && iArr[i] != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            sb.append(String.format("%03d", Integer.valueOf(iArr[i])));
            i++;
        }
        if (sb.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        pDF417ResultMetadata.setFileId(sb.toString());
        int i3 = iArr[i] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? i + 1 : -1;
        while (i < iArr[0]) {
            int i4 = iArr[i];
            if (i4 == MACRO_PDF417_TERMINATOR) {
                i++;
                pDF417ResultMetadata.setLastSegment(true);
            } else if (i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int i5 = i + 1;
                switch (iArr[i5]) {
                    case 0:
                        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder();
                        i = textCompaction(iArr, i5 + 1, eCIStringBuilder);
                        pDF417ResultMetadata.setFileName(eCIStringBuilder.toString());
                        break;
                    case 1:
                        ECIStringBuilder eCIStringBuilder2 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i5 + 1, eCIStringBuilder2);
                        pDF417ResultMetadata.setSegmentCount(Integer.parseInt(eCIStringBuilder2.toString()));
                        break;
                    case 2:
                        ECIStringBuilder eCIStringBuilder3 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i5 + 1, eCIStringBuilder3);
                        pDF417ResultMetadata.setTimestamp(Long.parseLong(eCIStringBuilder3.toString()));
                        break;
                    case 3:
                        ECIStringBuilder eCIStringBuilder4 = new ECIStringBuilder();
                        i = textCompaction(iArr, i5 + 1, eCIStringBuilder4);
                        pDF417ResultMetadata.setSender(eCIStringBuilder4.toString());
                        break;
                    case 4:
                        ECIStringBuilder eCIStringBuilder5 = new ECIStringBuilder();
                        i = textCompaction(iArr, i5 + 1, eCIStringBuilder5);
                        pDF417ResultMetadata.setAddressee(eCIStringBuilder5.toString());
                        break;
                    case 5:
                        ECIStringBuilder eCIStringBuilder6 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i5 + 1, eCIStringBuilder6);
                        pDF417ResultMetadata.setFileSize(Long.parseLong(eCIStringBuilder6.toString()));
                        break;
                    case 6:
                        ECIStringBuilder eCIStringBuilder7 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i5 + 1, eCIStringBuilder7);
                        pDF417ResultMetadata.setChecksum(Integer.parseInt(eCIStringBuilder7.toString()));
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i3 != -1) {
            int i6 = i - i3;
            if (pDF417ResultMetadata.isLastSegment()) {
                i6--;
            }
            pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i3, i6 + i3));
        }
        return i;
    }

    private static int textCompaction(int[] iArr, int i, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int[] iArr2 = new int[(iArr[0] - i) * 2];
        int[] iArr3 = new int[(iArr[0] - i) * 2];
        Mode mode = Mode.ALPHA;
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            } else if (i4 != ECI_CHARSET) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            iArr2[i2] = 900;
                            i2++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i4) {
                            }
                    }
                }
                i3--;
                z = true;
            } else {
                Mode decodeTextCompaction = decodeTextCompaction(iArr2, iArr3, i2, eCIStringBuilder, mode);
                int i5 = i3 + 1;
                eCIStringBuilder.appendECI(iArr[i3]);
                int[] iArr4 = new int[(iArr[0] - i5) * 2];
                int[] iArr5 = new int[(iArr[0] - i5) * 2];
                i2 = 0;
                mode = decodeTextCompaction;
                i = i5;
                iArr2 = iArr4;
                iArr3 = iArr5;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, eCIStringBuilder, mode);
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:42:0x007c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0020. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:55:0x009f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:65:0x00c4. Please report as an issue. */
    private static Mode decodeTextCompaction(int[] iArr, int[] iArr2, int i, ECIStringBuilder eCIStringBuilder, Mode mode) {
        Mode mode2;
        int i2;
        char c;
        char c2;
        Mode mode3;
        Mode mode4 = mode;
        Mode mode5 = mode4;
        Mode mode6 = mode5;
        int i3 = 0;
        while (i3 < i) {
            int i4 = iArr[i3];
            char c3 = ' ';
            switch (AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[mode5.ordinal()]) {
                case 1:
                    if (i4 < 26) {
                        i2 = i4 + 65;
                        c = (char) i2;
                        Mode mode7 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode7;
                        break;
                    } else {
                        if (i4 != 900) {
                            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                switch (i4) {
                                    case 27:
                                        mode4 = Mode.LOWER;
                                        break;
                                    case 28:
                                        mode4 = Mode.MIXED;
                                        break;
                                    case 29:
                                        mode2 = Mode.PUNCT_SHIFT;
                                        c3 = 0;
                                        Mode mode8 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode8;
                                        break;
                                }
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                eCIStringBuilder.append((char) iArr2[i3]);
                            }
                            c3 = 0;
                            mode3 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        } else {
                            mode4 = Mode.ALPHA;
                        }
                        c3 = 0;
                        mode5 = mode4;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 2:
                    if (i4 < 26) {
                        i2 = i4 + 97;
                        c = (char) i2;
                        Mode mode72 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode72;
                        break;
                    } else {
                        if (i4 != 900) {
                            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                switch (i4) {
                                    case 27:
                                        mode2 = Mode.ALPHA_SHIFT;
                                        c3 = 0;
                                        Mode mode82 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode82;
                                        break;
                                    case 28:
                                        mode4 = Mode.MIXED;
                                        break;
                                    case 29:
                                        mode2 = Mode.PUNCT_SHIFT;
                                        c3 = 0;
                                        Mode mode822 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode822;
                                        break;
                                }
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                eCIStringBuilder.append((char) iArr2[i3]);
                            }
                            c3 = 0;
                            mode3 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        } else {
                            mode4 = Mode.ALPHA;
                        }
                        c3 = 0;
                        mode5 = mode4;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 3:
                    if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                        Mode mode722 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode722;
                        break;
                    } else {
                        if (i4 != 900) {
                            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                switch (i4) {
                                    case 25:
                                        mode4 = Mode.PUNCT;
                                        c3 = 0;
                                        mode5 = mode4;
                                        break;
                                    case 27:
                                        mode4 = Mode.LOWER;
                                        c3 = 0;
                                        mode5 = mode4;
                                        break;
                                    case 29:
                                        mode2 = Mode.PUNCT_SHIFT;
                                        c3 = 0;
                                        Mode mode8222 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode8222;
                                        break;
                                }
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                eCIStringBuilder.append((char) iArr2[i3]);
                            }
                            c3 = 0;
                            mode3 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        }
                        mode4 = Mode.ALPHA;
                        c3 = 0;
                        mode5 = mode4;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 4:
                    if (i4 >= 29) {
                        if (i4 == 29 || i4 == 900) {
                            mode4 = Mode.ALPHA;
                            mode5 = mode4;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = 0;
                        break;
                    } else {
                        c = PUNCT_CHARS[i4];
                        Mode mode7222 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode7222;
                        break;
                    }
                case 5:
                    if (i4 < 26) {
                        c2 = (char) (i4 + 65);
                        mode3 = mode6;
                        break;
                    } else {
                        if (i4 != 26) {
                            if (i4 == 900) {
                                mode5 = Mode.ALPHA;
                                c3 = 0;
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                c3 = 0;
                            }
                        }
                        mode5 = mode6;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 6:
                    if (i4 < 29) {
                        c2 = PUNCT_CHARS[i4];
                    } else if (i4 == 29 || i4 == 900) {
                        mode5 = Mode.ALPHA;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = 0;
                        break;
                    } else {
                        if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        c2 = 0;
                    }
                    mode3 = mode6;
                    break;
                default:
                    mode3 = mode6;
                    mode6 = mode5;
                    c2 = 0;
                    break;
            }
            if (c2 != 0) {
                eCIStringBuilder.append(c2);
            }
            i3++;
            mode5 = mode6;
            mode6 = mode3;
        }
        return mode4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0047, code lost:
    
        if (r11 == com.google.zxing.pdf417.decoder.DecodedBitStreamParser.BYTE_COMPACTION_MODE_LATCH_6) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004b, code lost:
    
        if (r8 >= r12[0]) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004f, code lost:
    
        if (r12[r8] >= 900) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0051, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0053, code lost:
    
        if (r13 >= 6) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0055, code lost:
    
        r14.append((byte) (r6 >> ((5 - r13) * 8)));
        r13 = r13 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int byteCompaction(int i, int[] iArr, int i2, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int i3;
        boolean z = false;
        while (i2 < iArr[0] && !z) {
            while (i2 < iArr[0] && iArr[i2] == ECI_CHARSET) {
                int i4 = i2 + 1;
                eCIStringBuilder.appendECI(iArr[i4]);
                i2 = i4 + 1;
            }
            if (i2 >= iArr[0] || iArr[i2] >= 900) {
                z = true;
            } else {
                long j = 0;
                int i5 = 0;
                while (true) {
                    i3 = i2 + 1;
                    j = (j * 900) + iArr[i2];
                    i5++;
                    if (i5 >= 5 || i3 >= iArr[0] || iArr[i3] >= 900) {
                        break;
                    }
                    i2 = i3;
                }
                i3 -= i5;
                while (i3 < iArr[0] && !z) {
                    int i6 = i3 + 1;
                    int i7 = iArr[i3];
                    if (i7 < 900) {
                        eCIStringBuilder.append((byte) i7);
                        i3 = i6;
                    } else if (i7 == ECI_CHARSET) {
                        eCIStringBuilder.appendECI(iArr[i6]);
                        i3 = i6 + 1;
                    } else {
                        i3 = i6 - 1;
                        z = true;
                    }
                }
                i2 = i3;
            }
        }
        return i2;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x002e. Please report as an issue. */
    private static int numericCompaction(int[] iArr, int i, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else {
                if (i4 != 900 && i4 != 901 && i4 != ECI_CHARSET && i4 != 928) {
                    switch (i4) {
                    }
                }
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == 902 || z) && i2 > 0) {
                eCIStringBuilder.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return bigInteger2.substring(1);
    }
}
