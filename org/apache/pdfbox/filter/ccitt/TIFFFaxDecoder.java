package org.apache.pdfbox.filter.ccitt;

import androidx.media3.extractor.ts.PsExtractor;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.io.IOException;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.jmrtd.PassportService;

/* loaded from: classes5.dex */
public final class TIFFFaxDecoder {
    private int[] currChangingElems;
    private byte[] data;
    private int fillOrder;
    private int h;
    private int oneD;
    private int[] prevChangingElems;
    private int w;
    private static final int[] TABLE1 = {0, 1, 3, 7, 15, 31, 63, 127, 255};
    private static final int[] TABLE2 = {0, 128, 192, 224, PsExtractor.VIDEO_STREAM_MASK, 248, 252, 254, 255};
    private static final byte[] FLIP_TABLE = {0, Byte.MIN_VALUE, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_GET_RESPONSE, 32, ISOFileInfo.A0, 96, ISO7816.INS_CREATE_FILE, 16, -112, 80, ISO7816.INS_WRITE_BINARY, ISO7816.INS_DECREASE, ISO7816.INS_READ_BINARY, ISO7816.INS_MANAGE_CHANNEL, -16, 8, -120, 72, -56, 40, -88, 104, -24, Ascii.CAN, -104, AvcSpsUtils.PROFILE_IDC_EXTENDED, ISO7816.INS_LOAD_KEY_FILE, 56, -72, 120, -8, 4, -124, ISO7816.INS_REHABILITATE_CHV, -60, ISO7816.INS_CHANGE_CHV, -92, 100, ISO7816.INS_DELETE_FILE, Ascii.DC4, -108, 84, -44, ISO7816.INS_DECREASE_STAMPED, ISO7816.INS_READ_BINARY_STAMPED, 116, -12, 12, ISOFileInfo.SECURITY_ATTR_COMPACT, 76, -52, ISO7816.INS_UNBLOCK_CHV, -84, 108, -20, 28, -100, 92, ISO7816.INS_UPDATE_RECORD, 60, PSSSigner.TRAILER_IMPLICIT, 124, -4, 2, -126, 66, ISO7816.INS_ENVELOPE, ISO7816.INS_MSE, -94, ISOFileInfo.FCP_BYTE, ISO7816.INS_APPEND_RECORD, Ascii.DC2, -110, 82, ISO7816.INS_WRITE_RECORD, ISO7816.INS_INCREASE, -78, 114, -14, 10, ISOFileInfo.LCS_BYTE, 74, ISO7816.INS_GET_DATA, ISO7816.INS_PSO, -86, 106, -22, Ascii.SUB, -102, 90, ISO7816.INS_PUT_DATA, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, Ascii.SYN, -106, 86, ISO7816.INS_UPDATE_BINARY, 54, ISO7816.INS_READ_RECORD_STAMPED, 118, -10, 14, ISOFileInfo.CHANNEL_SECURITY, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, ISOFileInfo.DATA_BYTES2, 65, -63, 33, ISOFileInfo.A1, 97, -31, 17, -111, 81, -47, 49, ISO7816.INS_READ_BINARY2, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, Ascii.EM, -103, 89, -39, 57, -71, 121, -7, 5, ISOFileInfo.PROP_INFO, 69, -59, 37, ISOFileInfo.A5, 101, -27, Ascii.NAK, -107, 85, -43, 53, -75, 117, -11, 13, ISOFileInfo.ENV_TEMP_EF, AvcSpsUtils.PROFILE_IDC_MAIN, -51, 45, -83, 109, -19, 29, -99, 93, -35, Base64.padSymbol, -67, 125, -3, 3, ISOFileInfo.FILE_IDENTIFIER, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, ISO7816.INS_READ_RECORD2, 115, -13, 11, ISOFileInfo.SECURITY_ATTR_EXP, 75, -53, 43, ISOFileInfo.AB, 107, -21, Ascii.ESC, -101, 91, -37, 59, ByteSourceJsonBootstrapper.UTF8_BOM_2, 123, -5, 7, ISOFileInfo.FCI_EXT, 71, -57, 39, -89, 103, -25, Ascii.ETB, -105, 87, -41, 55, -73, 119, -9, 15, -113, 79, -49, 47, -81, ISOFileInfo.FCI_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_1, Ascii.US, -97, 95, -33, Utf8.REPLACEMENT_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_3, Byte.MAX_VALUE, -1};
    private static final short[] WHITE = {6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
    232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232};
    private static final short[] ADDITIONAL_MAKEUP = {28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 30727, 30727, -27639, -26615, -25591, -24567};
    private static final short[] INIT_BLACK = {3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 100, 100, 68, 68, 68, 68};
    private static final short[] TWO_BIT_BLACK = {292, PassportService.EF_DG4, 226, 226};
    private static final short[] BLACK = {62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390};
    private static final byte[] TWO_DCODES = {80, AvcSpsUtils.PROFILE_IDC_EXTENDED, Ascii.ETB, 71, 30, 30, 62, 62, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41};
    private int changingElemSize = 0;
    private int lastChangingElement = 0;
    private int compression = 2;
    private int uncompressedMode = 0;
    private int fillBits = 0;
    private int bitPointer = 0;
    private int bytePointer = 0;

    public TIFFFaxDecoder(int i, int i2, int i3) {
        this.fillOrder = i;
        this.w = i2;
        this.h = i3;
        int i4 = i2 + 1;
        this.prevChangingElems = new int[i4];
        this.currChangingElems = new int[i4];
    }

    public void decode1D(byte[] bArr, byte[] bArr2, int i, int i2) throws IOException {
        this.data = bArr2;
        int i3 = (this.w + 7) / 8;
        this.bitPointer = 0;
        this.bytePointer = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            decodeNextScanline(bArr, i4, i);
            i4 += i3;
        }
    }

    public void decodeNextScanline(byte[] bArr, int i, int i2) throws IOException {
        this.changingElemSize = 0;
        boolean z = true;
        while (true) {
            if (i2 >= this.w) {
                break;
            }
            while (z) {
                int nextNBits = nextNBits(10);
                short s = WHITE[nextNBits];
                int i3 = s & 1;
                int i4 = (s >>> 1) & 15;
                if (i4 == 12) {
                    short s2 = ADDITIONAL_MAKEUP[(12 & (nextNBits << 2)) | nextLesserThan8Bits(2)];
                    i2 += (s2 >>> 4) & 4095;
                    updatePointer(4 - ((s2 >>> 1) & 7));
                } else {
                    if (i4 == 0) {
                        throw new IOException("TIFFFaxDecoder: Invalid code encountered.");
                    }
                    if (i4 == 15) {
                        throw new IOException("TIFFFaxDecoder: EOL encountered in white run.");
                    }
                    i2 += (s >>> 5) & 2047;
                    updatePointer(10 - i4);
                    if (i3 == 0) {
                        int[] iArr = this.currChangingElems;
                        int i5 = this.changingElemSize;
                        this.changingElemSize = i5 + 1;
                        iArr[i5] = i2;
                        z = false;
                    }
                }
            }
            if (i2 == this.w) {
                if (this.compression == 2) {
                    advancePointer();
                }
            } else {
                while (!z) {
                    short s3 = INIT_BLACK[nextLesserThan8Bits(4)];
                    int i6 = (s3 >>> 1) & 15;
                    int i7 = (s3 >>> 5) & 2047;
                    if (i7 == 100) {
                        short s4 = BLACK[nextNBits(9)];
                        int i8 = s4 & 1;
                        int i9 = (s4 >>> 1) & 15;
                        int i10 = (s4 >>> 5) & 2047;
                        if (i9 == 12) {
                            updatePointer(5);
                            short s5 = ADDITIONAL_MAKEUP[nextLesserThan8Bits(4)];
                            int i11 = (s5 >>> 1) & 7;
                            int i12 = (s5 >>> 4) & 4095;
                            setToBlack(bArr, i, i2, i12);
                            i2 += i12;
                            updatePointer(4 - i11);
                        } else {
                            if (i9 == 15) {
                                throw new IOException("TIFFFaxDecoder: EOL encountered in black run.");
                            }
                            setToBlack(bArr, i, i2, i10);
                            i2 += i10;
                            updatePointer(9 - i9);
                            if (i8 == 0) {
                                int[] iArr2 = this.currChangingElems;
                                int i13 = this.changingElemSize;
                                this.changingElemSize = i13 + 1;
                                iArr2[i13] = i2;
                            }
                        }
                    } else if (i7 == 200) {
                        short s6 = TWO_BIT_BLACK[nextLesserThan8Bits(2)];
                        int i14 = (s6 >>> 5) & 2047;
                        setToBlack(bArr, i, i2, i14);
                        i2 += i14;
                        updatePointer(2 - ((s6 >>> 1) & 15));
                        int[] iArr3 = this.currChangingElems;
                        int i15 = this.changingElemSize;
                        this.changingElemSize = i15 + 1;
                        iArr3[i15] = i2;
                    } else {
                        setToBlack(bArr, i, i2, i7);
                        i2 += i7;
                        updatePointer(4 - i6);
                        int[] iArr4 = this.currChangingElems;
                        int i16 = this.changingElemSize;
                        this.changingElemSize = i16 + 1;
                        iArr4[i16] = i2;
                    }
                    z = true;
                }
                if (i2 == this.w) {
                    if (this.compression == 2) {
                        advancePointer();
                    }
                }
            }
        }
        int[] iArr5 = this.currChangingElems;
        int i17 = this.changingElemSize;
        this.changingElemSize = i17 + 1;
        iArr5[i17] = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v9 */
    public void decode2D(byte[] bArr, byte[] bArr2, int i, int i2, long j) throws IOException {
        char c;
        char c2;
        boolean z;
        int i3;
        this.data = bArr2;
        char c3 = 3;
        this.compression = 3;
        int i4 = 0;
        this.bitPointer = 0;
        this.bytePointer = 0;
        int i5 = 7;
        char c4 = '\b';
        int i6 = (this.w + 7) / 8;
        int[] iArr = new int[2];
        this.oneD = (int) (j & 1);
        ?? r12 = 1;
        this.uncompressedMode = (int) ((j & 2) >> 1);
        this.fillBits = (int) ((j & 4) >> 2);
        if (readEOL() != 1) {
            throw new IOException("TIFFFaxDecoder: First scanline must be 1D encoded.");
        }
        decodeNextScanline(bArr, 0, i);
        int i7 = i6 + 0;
        int i8 = 1;
        while (i8 < i2) {
            if (readEOL() == 0) {
                int[] iArr2 = this.prevChangingElems;
                this.prevChangingElems = this.currChangingElems;
                this.currChangingElems = iArr2;
                int i9 = -1;
                this.lastChangingElement = i4;
                int i10 = i;
                int i11 = i4;
                boolean z2 = r12;
                while (i10 < this.w) {
                    getNextChangingElement(i9, z2, iArr);
                    int i12 = iArr[i4];
                    i9 = iArr[r12];
                    int i13 = TWO_DCODES[nextLesserThan8Bits(i5)] & 255;
                    int i14 = (i13 & 120) >>> 3;
                    int i15 = i13 & i5;
                    if (i14 == 0) {
                        if (!z2) {
                            setToBlack(bArr, i7, i10, i9 - i10);
                        }
                        updatePointer(7 - i15);
                        i10 = i9;
                        i4 = 0;
                        r12 = 1;
                    } else if (i14 == 1) {
                        updatePointer(7 - i15);
                        if (z2) {
                            int decodeWhiteCodeWord = i10 + decodeWhiteCodeWord();
                            int i16 = i11 + 1;
                            this.currChangingElems[i11] = decodeWhiteCodeWord;
                            int decodeBlackCodeWord = decodeBlackCodeWord();
                            setToBlack(bArr, i7, decodeWhiteCodeWord, decodeBlackCodeWord);
                            i10 = decodeWhiteCodeWord + decodeBlackCodeWord;
                            i3 = i16 + 1;
                            this.currChangingElems[i16] = i10;
                        } else {
                            int decodeBlackCodeWord2 = decodeBlackCodeWord();
                            setToBlack(bArr, i7, i10, decodeBlackCodeWord2);
                            int i17 = i10 + decodeBlackCodeWord2;
                            int i18 = i11 + 1;
                            this.currChangingElems[i11] = i17;
                            i10 = i17 + decodeWhiteCodeWord();
                            i3 = i18 + 1;
                            this.currChangingElems[i18] = i10;
                        }
                        i11 = i3;
                        r12 = 1;
                        i9 = i10;
                        i4 = 0;
                    } else if (i14 <= 8) {
                        int i19 = i12 + (i14 - 5);
                        int i20 = i11 + 1;
                        this.currChangingElems[i11] = i19;
                        if (!z2) {
                            setToBlack(bArr, i7, i10, i19 - i10);
                        }
                        z2 = !z2;
                        updatePointer(7 - i15);
                        i10 = i19;
                        r12 = 1;
                        i11 = i20;
                        i4 = 0;
                        i5 = 7;
                        i9 = i10;
                    } else {
                        throw new IOException("TIFFFaxDecoder: Invalid code encountered while decoding 2D group 3 compressed data.");
                    }
                }
                z = r12;
                c2 = '\b';
                c = 3;
                this.currChangingElems[i11] = i10;
                this.changingElemSize = i11 + 1;
            } else {
                c = c3;
                c2 = c4;
                z = r12;
                decodeNextScanline(bArr, i7, i);
            }
            i7 += i6;
            i8++;
            c4 = c2;
            r12 = z;
            c3 = c;
            i4 = 0;
            i5 = 7;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    public synchronized void decodeT6(byte[] bArr, byte[] bArr2, int i, int i2, long j, boolean z) throws IOException {
        int i3;
        boolean z2;
        this.data = bArr2;
        this.compression = 4;
        int i4 = 0;
        this.bitPointer = 0;
        this.bytePointer = 0;
        int i5 = this.w;
        char c = '\b';
        int i6 = (i5 + 7) / 8;
        int[] iArr = new int[2];
        ?? r9 = 1;
        this.uncompressedMode = (int) ((j & 2) >> 1);
        int[] iArr2 = this.currChangingElems;
        this.changingElemSize = 0;
        int i7 = 0 + 1;
        this.changingElemSize = i7;
        iArr2[0] = i5;
        this.changingElemSize = i7 + 1;
        iArr2[i7] = i5;
        int i8 = i2;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            if (z && this.bitPointer != 0) {
                this.bitPointer = i4;
                this.bytePointer += r9;
            }
            int i11 = -1;
            int[] iArr3 = this.prevChangingElems;
            this.prevChangingElems = this.currChangingElems;
            this.currChangingElems = iArr3;
            this.lastChangingElement = i4;
            int i12 = i;
            int i13 = i4;
            boolean z3 = r9;
            while (i12 < this.w) {
                getNextChangingElement(i11, z3, iArr);
                int i14 = iArr[i4];
                int i15 = iArr[r9];
                int i16 = TWO_DCODES[nextLesserThan8Bits(7)] & 255;
                int i17 = (i16 & 120) >>> 3;
                int i18 = i16 & 7;
                if (i17 == 0) {
                    if (!z3) {
                        setToBlack(bArr, i10, i12, i15 - i12);
                    }
                    updatePointer(7 - i18);
                    i11 = i15;
                    i12 = i11;
                } else if (i17 == 1) {
                    updatePointer(7 - i18);
                    if (z3) {
                        int decodeWhiteCodeWord = i12 + decodeWhiteCodeWord();
                        int i19 = i13 + 1;
                        iArr3[i13] = decodeWhiteCodeWord;
                        int decodeBlackCodeWord = decodeBlackCodeWord();
                        setToBlack(bArr, i10, decodeWhiteCodeWord, decodeBlackCodeWord);
                        i12 = decodeWhiteCodeWord + decodeBlackCodeWord;
                        i3 = i19 + 1;
                        iArr3[i19] = i12;
                    } else {
                        int decodeBlackCodeWord2 = decodeBlackCodeWord();
                        setToBlack(bArr, i10, i12, decodeBlackCodeWord2);
                        int i20 = i12 + decodeBlackCodeWord2;
                        int i21 = i13 + 1;
                        iArr3[i13] = i20;
                        i12 = i20 + decodeWhiteCodeWord();
                        i3 = i21 + 1;
                        iArr3[i21] = i12;
                    }
                    i13 = i3;
                    i11 = i12;
                } else if (i17 <= 8) {
                    i11 = i14 + (i17 - 5);
                    int i22 = i13 + 1;
                    iArr3[i13] = i11;
                    if (!z3) {
                        setToBlack(bArr, i10, i12, i11 - i12);
                    }
                    z3 = !z3;
                    updatePointer(7 - i18);
                    c = '\b';
                    i13 = i22;
                    i12 = i11;
                    i4 = 0;
                    r9 = 1;
                } else if (i17 == 11) {
                    if (nextLesserThan8Bits(3) != 7) {
                        throw new IOException("TIFFFaxDecoder: Invalid code encountered while decoding 2D group 4 compressed data.");
                    }
                    boolean z4 = false;
                    int i23 = 0;
                    while (!z4) {
                        while (nextLesserThan8Bits(1) != 1) {
                            i23++;
                        }
                        if (i23 > 5) {
                            i23 -= 6;
                            if (!z3 && i23 > 0) {
                                iArr3[i13] = i12;
                                i13++;
                            }
                            i12 += i23;
                            if (i23 > 0) {
                                z3 = true;
                            }
                            if (nextLesserThan8Bits(1) == 0) {
                                if (!z3) {
                                    iArr3[i13] = i12;
                                    i13++;
                                }
                                z2 = true;
                            } else {
                                if (z3) {
                                    iArr3[i13] = i12;
                                    i13++;
                                }
                                z2 = false;
                            }
                            z3 = z2;
                            z4 = true;
                        }
                        if (i23 == 5) {
                            if (!z3) {
                                iArr3[i13] = i12;
                                i13++;
                            }
                            i12 += i23;
                            z3 = true;
                        } else {
                            int i24 = i12 + i23;
                            iArr3[i13] = i24;
                            setToBlack(bArr, i10, i24, 1);
                            i12 = i24 + 1;
                            i13++;
                            z3 = false;
                        }
                    }
                    c = '\b';
                    i4 = 0;
                    r9 = 1;
                } else {
                    throw new IOException("TIFFFaxDecoder: Invalid code encountered while decoding 2D group 4 compressed data.");
                }
                i4 = 0;
                c = '\b';
                r9 = 1;
            }
            char c2 = c;
            int i25 = r9;
            if (iArr3.length == i13) {
                break;
            }
            iArr3[i13] = i12;
            this.changingElemSize = i13 + 1;
            i10 += i6;
            i9++;
            i8 = i2;
            c = c2;
            r9 = i25;
            i4 = 0;
        }
    }

    private void setToBlack(byte[] bArr, int i, int i2, int i3) {
        int i4 = (i * 8) + i2;
        int i5 = i3 + i4;
        int i6 = i4 >> 3;
        int i7 = i4 & 7;
        if (i7 > 0) {
            int i8 = 1 << (7 - i7);
            byte b = bArr[i6];
            while (i8 > 0 && i4 < i5) {
                b = (byte) (b | i8);
                i8 >>= 1;
                i4++;
            }
            bArr[i6] = b;
        }
        int i9 = i4 >> 3;
        while (i4 < i5 - 7) {
            bArr[i9] = -1;
            i4 += 8;
            i9++;
        }
        while (i4 < i5) {
            int i10 = i4 >> 3;
            bArr[i10] = (byte) (bArr[i10] | (1 << (7 - (i4 & 7))));
            i4++;
        }
    }

    private int decodeWhiteCodeWord() throws IOException {
        boolean z = true;
        int i = 0;
        while (z) {
            int nextNBits = nextNBits(10);
            short s = WHITE[nextNBits];
            int i2 = s & 1;
            int i3 = (s >>> 1) & 15;
            if (i3 == 12) {
                short s2 = ADDITIONAL_MAKEUP[((nextNBits << 2) & 12) | nextLesserThan8Bits(2)];
                i += (s2 >>> 4) & 4095;
                updatePointer(4 - ((s2 >>> 1) & 7));
            } else {
                if (i3 == 0) {
                    throw new IOException("TIFFFaxDecoder: Invalid code encountered.");
                }
                if (i3 == 15) {
                    throw new IOException("TIFFFaxDecoder: EOL encountered in white run.");
                }
                i += (s >>> 5) & 2047;
                updatePointer(10 - i3);
                if (i2 == 0) {
                    z = false;
                }
            }
        }
        return i;
    }

    private int decodeBlackCodeWord() throws IOException {
        boolean z = false;
        int i = 0;
        while (!z) {
            short s = INIT_BLACK[nextLesserThan8Bits(4)];
            int i2 = (s >>> 1) & 15;
            int i3 = (s >>> 5) & 2047;
            if (i3 == 100) {
                short s2 = BLACK[nextNBits(9)];
                int i4 = s2 & 1;
                int i5 = (s2 >>> 1) & 15;
                int i6 = (s2 >>> 5) & 2047;
                if (i5 == 12) {
                    updatePointer(5);
                    short s3 = ADDITIONAL_MAKEUP[nextLesserThan8Bits(4)];
                    i += (s3 >>> 4) & 4095;
                    updatePointer(4 - ((s3 >>> 1) & 7));
                } else {
                    if (i5 == 15) {
                        throw new IOException("TIFFFaxDecoder: EOL encountered in black run.");
                    }
                    i += i6;
                    updatePointer(9 - i5);
                    if (i4 == 0) {
                    }
                }
            } else if (i3 == 200) {
                short s4 = TWO_BIT_BLACK[nextLesserThan8Bits(2)];
                i += (s4 >>> 5) & 2047;
                updatePointer(2 - ((s4 >>> 1) & 15));
            } else {
                i += i3;
                updatePointer(4 - i2);
            }
            z = true;
        }
        return i;
    }

    private int readEOL() throws IOException {
        int nextNBits;
        int i = this.fillBits;
        if (i == 0) {
            if (nextNBits(12) != 1) {
                throw new IOException("TIFFFaxDecoder: Scanline must begin with EOL.");
            }
        } else if (i == 1) {
            int i2 = 8 - this.bitPointer;
            if (nextNBits(i2) != 0) {
                throw new IOException("TIFFFaxDecoder: All fill bits preceding EOL code must be 0.");
            }
            if (i2 < 4 && nextNBits(8) != 0) {
                throw new IOException("TIFFFaxDecoder: All fill bits preceding EOL code must be 0.");
            }
            do {
                nextNBits = nextNBits(8);
                if (nextNBits != 1) {
                }
            } while (nextNBits == 0);
            throw new IOException("TIFFFaxDecoder: All fill bits preceding EOL code must be 0.");
        }
        if (this.oneD == 0) {
            return 1;
        }
        return nextLesserThan8Bits(1);
    }

    private void getNextChangingElement(int i, boolean z, int[] iArr) {
        int[] iArr2 = this.prevChangingElems;
        int i2 = this.changingElemSize;
        int i3 = this.lastChangingElement;
        int i4 = i3 > 0 ? i3 - 1 : 0;
        int i5 = z ? i4 & (-2) : i4 | 1;
        while (true) {
            if (i5 >= i2) {
                break;
            }
            int i6 = iArr2[i5];
            if (i6 > i) {
                this.lastChangingElement = i5;
                iArr[0] = i6;
                break;
            }
            i5 += 2;
        }
        int i7 = i5 + 1;
        if (i7 < i2) {
            iArr[1] = iArr2[i7];
        }
    }

    private int nextNBits(int i) throws IOException {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        int i2;
        int i3;
        byte[] bArr = this.data;
        int length = bArr.length - 1;
        int i4 = this.bytePointer;
        int i5 = this.fillOrder;
        if (i5 == 1) {
            b3 = bArr[i4];
            if (i4 == length) {
                b4 = 0;
                b5 = 0;
            } else {
                int i6 = i4 + 1;
                if (i6 == length) {
                    b4 = bArr[i6];
                    b5 = 0;
                } else {
                    b = bArr[i6];
                    b2 = bArr[i4 + 2];
                    byte b6 = b;
                    b5 = b2;
                    b4 = b6;
                }
            }
        } else if (i5 == 2) {
            byte[] bArr2 = FLIP_TABLE;
            byte b7 = bArr2[bArr[i4] & 255];
            if (i4 == length) {
                b4 = 0;
                b5 = 0;
            } else {
                int i7 = i4 + 1;
                if (i7 == length) {
                    b4 = bArr2[bArr[i7] & 255];
                    b5 = 0;
                } else {
                    b = bArr2[bArr[i7] & 255];
                    b2 = bArr2[bArr[i4 + 2] & 255];
                    b3 = b7;
                    byte b62 = b;
                    b5 = b2;
                    b4 = b62;
                }
            }
            b3 = b7;
        } else {
            throw new IOException("TIFFFaxDecoder: TIFF_FILL_ORDER tag must be either 1 or 2.");
        }
        int i8 = 8 - this.bitPointer;
        int i9 = i - i8;
        if (i9 > 8) {
            i3 = i9 - 8;
            i2 = 8;
        } else {
            i2 = i9;
            i3 = 0;
        }
        int i10 = i4 + 1;
        this.bytePointer = i10;
        int i11 = (b3 & TABLE1[i8]) << i9;
        int[] iArr = TABLE2;
        int i12 = (b4 & iArr[i2]) >>> (8 - i2);
        if (i3 != 0) {
            i12 = (i12 << i3) | ((b5 & iArr[i3]) >>> (8 - i3));
            this.bytePointer = i10 + 1;
            this.bitPointer = i3;
        } else if (i2 == 8) {
            this.bitPointer = 0;
            this.bytePointer = i10 + 1;
        } else {
            this.bitPointer = i2;
        }
        return i11 | i12;
    }

    private int nextLesserThan8Bits(int i) throws IOException {
        byte b;
        byte b2;
        byte[] bArr = this.data;
        int length = bArr.length - 1;
        int i2 = this.bytePointer;
        int i3 = this.fillOrder;
        if (i3 == 1) {
            b2 = bArr[i2];
            b = i2 == length ? (byte) 0 : bArr[i2 + 1];
        } else if (i3 == 2) {
            byte[] bArr2 = FLIP_TABLE;
            byte b3 = bArr2[bArr[i2] & 255];
            b = i2 == length ? (byte) 0 : bArr2[bArr[i2 + 1] & 255];
            b2 = b3;
        } else {
            throw new IOException("TIFFFaxDecoder: TIFF_FILL_ORDER tag must be either 1 or 2.");
        }
        int i4 = this.bitPointer;
        int i5 = 8 - i4;
        int i6 = i - i5;
        int i7 = i5 - i;
        if (i7 >= 0) {
            int i8 = (TABLE1[i5] & b2) >>> i7;
            int i9 = i4 + i;
            this.bitPointer = i9;
            if (i9 != 8) {
                return i8;
            }
            this.bitPointer = 0;
            this.bytePointer = i2 + 1;
            return i8;
        }
        int i10 = ((b & TABLE2[i6]) >>> (8 - i6)) | ((TABLE1[i5] & b2) << (-i7));
        this.bytePointer = i2 + 1;
        this.bitPointer = i6;
        return i10;
    }

    private void updatePointer(int i) {
        int i2 = this.bitPointer - i;
        if (i2 < 0) {
            this.bytePointer--;
            this.bitPointer = i2 + 8;
        } else {
            this.bitPointer = i2;
        }
    }

    private boolean advancePointer() {
        if (this.bitPointer != 0) {
            this.bytePointer++;
            this.bitPointer = 0;
        }
        return true;
    }
}
