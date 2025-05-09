package com.google.zxing.aztec.decoder;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.webkit.ProxyConfig;
import com.beust.jcommander.Parameters;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes4.dex */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, "G", StandardStructureTypes.H, "I", "J", "K", "L", "M", "N", PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, "P", "Q", "R", "S", "T", PDBorderStyleDictionary.STYLE_UNDERLINE, "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", CmcdData.Factory.STREAMING_FORMAT_HLS, CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "j", "k", CmcdData.Factory.STREAM_TYPE_LIVE, "m", "n", "o", TtmlNode.TAG_P, "q", PDPageLabelRange.STYLE_ROMAN_LOWER, CmcdData.Factory.STREAMING_FORMAT_SS, "t", "u", "v", "w", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", IOUtils.LINE_SEPARATOR_UNIX, "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"FLG(n)", "\r", IOUtils.LINE_SEPARATOR_WINDOWS, ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", ProxyConfig.MATCH_ALL_SCHEMES, "+", ",", Parameters.DEFAULT_OPTION_PREFIXES, ".", RemoteSettings.FORWARD_SLASH_STRING, ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        CorrectedBitsResult correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits.correctBits), getEncodedData(correctBits.correctBits), null, String.format("%d%%", Integer.valueOf(correctBits.ecLevel)));
        decoderResult.setNumBits(correctBits.correctBits.length);
        return decoderResult;
    }

    public static String highLevelDecode(boolean[] zArr) throws FormatException {
        return getEncodedData(zArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b6, code lost:
    
        throw com.google.zxing.FormatException.getFormatInstance();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String getEncodedData(boolean[] zArr) throws FormatException {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder((zArr.length - 5) / 4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Charset charset = DEFAULT_ENCODING;
        int i = 0;
        loop0: while (i < length) {
            if (table2 != Table.BINARY) {
                int i2 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i >= i2) {
                    int readCode = readCode(zArr, i, i2);
                    i += i2;
                    String character = getCharacter(table2, readCode);
                    if ("FLG(n)".equals(character)) {
                        if (length - i >= 3) {
                            int readCode2 = readCode(zArr, i, 3);
                            i += 3;
                            try {
                                sb.append(byteArrayOutputStream.toString(charset.name()));
                                byteArrayOutputStream.reset();
                                if (readCode2 == 0) {
                                    sb.append((char) 29);
                                } else {
                                    if (readCode2 == 7) {
                                        throw FormatException.getFormatInstance();
                                    }
                                    if (length - i >= readCode2 * 4) {
                                        int i3 = 0;
                                        while (true) {
                                            int i4 = readCode2 - 1;
                                            if (readCode2 > 0) {
                                                int readCode3 = readCode(zArr, i, 4);
                                                i += 4;
                                                if (readCode3 < 2 || readCode3 > 11) {
                                                    break loop0;
                                                }
                                                i3 = (i3 * 10) + (readCode3 - 2);
                                                readCode2 = i4;
                                            } else {
                                                CharacterSetECI characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(i3);
                                                if (characterSetECIByValue == null) {
                                                    throw FormatException.getFormatInstance();
                                                }
                                                charset = characterSetECIByValue.getCharset();
                                            }
                                        }
                                    }
                                }
                            } catch (UnsupportedEncodingException e) {
                                throw new IllegalStateException(e);
                            }
                        }
                    } else if (character.startsWith("CTRL_")) {
                        table = getTable(character.charAt(5));
                        if (character.charAt(6) != 'L') {
                            Table table3 = table2;
                            table2 = table;
                            table = table3;
                        }
                    } else {
                        byte[] bytes = character.getBytes(StandardCharsets.US_ASCII);
                        byteArrayOutputStream.write(bytes, 0, bytes.length);
                    }
                    table2 = table;
                }
            } else if (length - i >= 5) {
                int readCode4 = readCode(zArr, i, 5);
                i += 5;
                if (readCode4 == 0) {
                    if (length - i >= 11) {
                        readCode4 = readCode(zArr, i, 11) + 31;
                        i += 11;
                    }
                }
                int i5 = 0;
                while (true) {
                    if (i5 >= readCode4) {
                        break;
                    }
                    if (length - i < 8) {
                        i = length;
                        break;
                    }
                    byteArrayOutputStream.write((byte) readCode(zArr, i, 8));
                    i += 8;
                    i5++;
                }
                table2 = table;
            }
        }
        try {
            sb.append(byteArrayOutputStream.toString(charset.name()));
            return sb.toString();
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private static Table getTable(char c) {
        if (c == 'B') {
            return Table.BINARY;
        }
        if (c == 'D') {
            return Table.DIGIT;
        }
        if (c == 'P') {
            return Table.PUNCT;
        }
        if (c == 'L') {
            return Table.LOWER;
        }
        if (c == 'M') {
            return Table.MIXED;
        }
        return Table.UPPER;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        static {
            int[] iArr = new int[Table.values().length];
            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = iArr;
            try {
                iArr[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static String getCharacter(Table table, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i2 == 1) {
            return UPPER_TABLE[i];
        }
        if (i2 == 2) {
            return LOWER_TABLE[i];
        }
        if (i2 == 3) {
            return MIXED_TABLE[i];
        }
        if (i2 == 4) {
            return PUNCT_TABLE[i];
        }
        if (i2 == 5) {
            return DIGIT_TABLE[i];
        }
        throw new IllegalStateException("Bad table");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class CorrectedBitsResult {
        private final boolean[] correctBits;
        private final int ecLevel;

        CorrectedBitsResult(boolean[] zArr, int i) {
            this.correctBits = zArr;
            this.ecLevel = i;
        }
    }

    private CorrectedBitsResult correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i = 8;
        if (this.ddata.getNbLayers() <= 2) {
            i = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i;
        if (length < nbDatablocks) {
            throw FormatException.getFormatInstance();
        }
        int length2 = zArr.length % i;
        int[] iArr = new int[length];
        int i2 = 0;
        while (i2 < length) {
            iArr[i2] = readCode(zArr, length2, i);
            i2++;
            length2 += i;
        }
        try {
            ReedSolomonDecoder reedSolomonDecoder = new ReedSolomonDecoder(genericGF);
            int i3 = length - nbDatablocks;
            reedSolomonDecoder.decode(iArr, i3);
            int i4 = (1 << i) - 1;
            int i5 = 0;
            for (int i6 = 0; i6 < nbDatablocks; i6++) {
                int i7 = iArr[i6];
                if (i7 == 0 || i7 == i4) {
                    throw FormatException.getFormatInstance();
                }
                if (i7 == 1 || i7 == i4 - 1) {
                    i5++;
                }
            }
            boolean[] zArr2 = new boolean[(nbDatablocks * i) - i5];
            int i8 = 0;
            for (int i9 = 0; i9 < nbDatablocks; i9++) {
                int i10 = iArr[i9];
                if (i10 == 1 || i10 == i4 - 1) {
                    Arrays.fill(zArr2, i8, (i8 + i) - 1, i10 > 1);
                    i8 += i - 1;
                } else {
                    int i11 = i - 1;
                    while (i11 >= 0) {
                        int i12 = i8 + 1;
                        zArr2[i8] = ((1 << i11) & i10) != 0;
                        i11--;
                        i8 = i12;
                    }
                }
            }
            return new CorrectedBitsResult(zArr2, (i3 * 100) / length);
        } catch (ReedSolomonException e) {
            throw FormatException.getFormatInstance(e);
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i = (isCompact ? 11 : 14) + (nbLayers * 4);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i2 = 2;
        if (isCompact) {
            for (int i3 = 0; i3 < i; i3++) {
                iArr[i3] = i3;
            }
        } else {
            int i4 = i / 2;
            int i5 = ((i + 1) + (((i4 - 1) / 15) * 2)) / 2;
            for (int i6 = 0; i6 < i4; i6++) {
                iArr[(i4 - i6) - 1] = (i5 - r12) - 1;
                iArr[i4 + i6] = (i6 / 15) + i6 + i5 + 1;
            }
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < nbLayers) {
            int i9 = ((nbLayers - i7) * 4) + (isCompact ? 9 : 12);
            int i10 = i7 * 2;
            int i11 = (i - 1) - i10;
            int i12 = 0;
            while (i12 < i9) {
                int i13 = i12 * 2;
                int i14 = 0;
                while (i14 < i2) {
                    int i15 = i10 + i14;
                    int i16 = i10 + i12;
                    zArr[i8 + i13 + i14] = bitMatrix.get(iArr[i15], iArr[i16]);
                    int i17 = iArr[i16];
                    int i18 = i11 - i14;
                    zArr[(i9 * 2) + i8 + i13 + i14] = bitMatrix.get(i17, iArr[i18]);
                    int i19 = i11 - i12;
                    zArr[(i9 * 4) + i8 + i13 + i14] = bitMatrix.get(iArr[i18], iArr[i19]);
                    zArr[(i9 * 6) + i8 + i13 + i14] = bitMatrix.get(iArr[i19], iArr[i15]);
                    i14++;
                    nbLayers = nbLayers;
                    isCompact = isCompact;
                    i2 = 2;
                }
                i12++;
                i2 = 2;
            }
            i8 += i9 * 8;
            i7++;
            i2 = 2;
        }
        return zArr;
    }

    private static int readCode(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    private static byte readByte(boolean[] zArr, int i) {
        int readCode;
        int length = zArr.length - i;
        if (length >= 8) {
            readCode = readCode(zArr, i, 8);
        } else {
            readCode = readCode(zArr, i, length) << (8 - length);
        }
        return (byte) readCode;
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = readByte(zArr, i * 8);
        }
        return bArr;
    }
}
