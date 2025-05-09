package com.github.jaiimageio.impl.plugins.tiff;

import androidx.media3.extractor.ts.PsExtractor;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.IIOException;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.jmrtd.PassportService;

/* loaded from: classes3.dex */
public class TIFFFaxDecompressor extends TIFFDecompressor {
    private int bitPointer;
    private int bitsPerScanline;
    private byte[] buffer;
    private int bytePointer;
    protected int compression;
    private int[] currChangingElems;
    private byte[] data;
    protected int fillOrder;
    private int h;
    private int lineBitNum;
    protected int oneD;
    private int[] prevChangingElems;
    private int t4Options;
    private int t6Options;
    private int w;
    static int[] table1 = {0, 1, 3, 7, 15, 31, 63, 127, 255};
    static int[] table2 = {0, 128, 192, 224, PsExtractor.VIDEO_STREAM_MASK, 248, 252, 254, 255};
    static byte[] flipTable = {0, Byte.MIN_VALUE, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_GET_RESPONSE, 32, ISOFileInfo.A0, 96, ISO7816.INS_CREATE_FILE, 16, -112, 80, ISO7816.INS_WRITE_BINARY, ISO7816.INS_DECREASE, ISO7816.INS_READ_BINARY, ISO7816.INS_MANAGE_CHANNEL, -16, 8, -120, 72, -56, 40, -88, 104, -24, Ascii.CAN, -104, AvcSpsUtils.PROFILE_IDC_EXTENDED, ISO7816.INS_LOAD_KEY_FILE, 56, -72, 120, -8, 4, -124, ISO7816.INS_REHABILITATE_CHV, -60, ISO7816.INS_CHANGE_CHV, -92, 100, ISO7816.INS_DELETE_FILE, Ascii.DC4, -108, 84, -44, ISO7816.INS_DECREASE_STAMPED, ISO7816.INS_READ_BINARY_STAMPED, 116, -12, 12, ISOFileInfo.SECURITY_ATTR_COMPACT, 76, -52, ISO7816.INS_UNBLOCK_CHV, -84, 108, -20, 28, -100, 92, ISO7816.INS_UPDATE_RECORD, 60, PSSSigner.TRAILER_IMPLICIT, 124, -4, 2, -126, 66, ISO7816.INS_ENVELOPE, ISO7816.INS_MSE, -94, ISOFileInfo.FCP_BYTE, ISO7816.INS_APPEND_RECORD, Ascii.DC2, -110, 82, ISO7816.INS_WRITE_RECORD, ISO7816.INS_INCREASE, -78, 114, -14, 10, ISOFileInfo.LCS_BYTE, 74, ISO7816.INS_GET_DATA, ISO7816.INS_PSO, -86, 106, -22, Ascii.SUB, -102, 90, ISO7816.INS_PUT_DATA, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, Ascii.SYN, -106, 86, ISO7816.INS_UPDATE_BINARY, 54, ISO7816.INS_READ_RECORD_STAMPED, 118, -10, 14, ISOFileInfo.CHANNEL_SECURITY, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, ISOFileInfo.DATA_BYTES2, 65, -63, 33, ISOFileInfo.A1, 97, -31, 17, -111, 81, -47, 49, ISO7816.INS_READ_BINARY2, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, Ascii.EM, -103, 89, -39, 57, -71, 121, -7, 5, ISOFileInfo.PROP_INFO, 69, -59, 37, ISOFileInfo.A5, 101, -27, Ascii.NAK, -107, 85, -43, 53, -75, 117, -11, 13, ISOFileInfo.ENV_TEMP_EF, AvcSpsUtils.PROFILE_IDC_MAIN, -51, 45, -83, 109, -19, 29, -99, 93, -35, Base64.padSymbol, -67, 125, -3, 3, ISOFileInfo.FILE_IDENTIFIER, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, ISO7816.INS_READ_RECORD2, 115, -13, 11, ISOFileInfo.SECURITY_ATTR_EXP, 75, -53, 43, ISOFileInfo.AB, 107, -21, Ascii.ESC, -101, 91, -37, 59, ByteSourceJsonBootstrapper.UTF8_BOM_2, 123, -5, 7, ISOFileInfo.FCI_EXT, 71, -57, 39, -89, 103, -25, Ascii.ETB, -105, 87, -41, 55, -73, 119, -9, 15, -113, 79, -49, 47, -81, ISOFileInfo.FCI_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_1, Ascii.US, -97, 95, -33, Utf8.REPLACEMENT_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_3, Byte.MAX_VALUE, -1};
    static short[] white = {6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, PassportService.EF_DG10, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
    232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232};
    static short[] additionalMakeup = {28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 30727, 30727, -27639, -26615, -25591, -24567};
    static short[] initBlack = {3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 100, 100, 68, 68, 68, 68};
    static short[] twoBitBlack = {292, PassportService.EF_DG4, 226, 226};
    static short[] black = {62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390};
    static byte[] twoDCodes = {80, AvcSpsUtils.PROFILE_IDC_EXTENDED, Ascii.ETB, 71, 30, 30, 62, 62, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41};
    protected int uncompressedMode = 0;
    protected int fillBits = 0;
    private int changingElemSize = 0;
    private int lastChangingElement = 0;

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void beginDecoding() {
        super.beginDecoding();
        if (this.metadata instanceof TIFFImageMetadata) {
            TIFFImageMetadata tIFFImageMetadata = (TIFFImageMetadata) this.metadata;
            TIFFField tIFFField = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_FILL_ORDER);
            this.fillOrder = tIFFField == null ? 1 : tIFFField.getAsInt(0);
            TIFFField tIFFField2 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION);
            this.compression = tIFFField2 == null ? 2 : tIFFField2.getAsInt(0);
            TIFFField tIFFField3 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_T4_OPTIONS);
            int asInt = tIFFField3 == null ? 0 : tIFFField3.getAsInt(0);
            this.t4Options = asInt;
            this.oneD = asInt & 1;
            this.uncompressedMode = (asInt & 2) >> 1;
            this.fillBits = (asInt & 4) >> 2;
            TIFFField tIFFField4 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_T6_OPTIONS);
            this.t6Options = tIFFField4 != null ? tIFFField4.getAsInt(0) : 0;
            return;
        }
        this.fillOrder = 1;
        this.compression = 2;
        this.t4Options = 0;
        this.oneD = 0;
        this.uncompressedMode = 0;
        this.fillBits = 0;
        this.t6Options = 0;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        this.buffer = bArr;
        this.w = this.srcWidth;
        this.h = this.srcHeight;
        this.bitsPerScanline = i3 * 8;
        this.lineBitNum = i * 8;
        this.data = new byte[this.byteCount];
        this.bitPointer = 0;
        this.bytePointer = 0;
        int i4 = this.w;
        this.prevChangingElems = new int[i4 + 1];
        this.currChangingElems = new int[i4 + 1];
        this.stream.seek(this.offset);
        this.stream.readFully(this.data);
        try {
            int i5 = this.compression;
            if (i5 == 2) {
                decodeRLE();
                return;
            }
            if (i5 == 3) {
                decodeT4();
                return;
            }
            if (i5 == 4) {
                this.uncompressedMode = (this.t6Options & 2) >> 1;
                decodeT6();
            } else {
                throw new IIOException("Unknown compression type " + this.compression);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(byteArrayOutputStream));
            warning("Ignoring exception:\n " + new String(byteArrayOutputStream.toByteArray()));
        }
    }

    public void decodeRLE() throws IIOException {
        for (int i = 0; i < this.h; i++) {
            decodeNextScanline(this.srcMinY + i);
            if (this.bitPointer != 0) {
                this.bytePointer++;
                this.bitPointer = 0;
            }
            this.lineBitNum += this.bitsPerScanline;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x01a8, code lost:
    
        r1 = r18.currChangingElems;
        r2 = r18.changingElemSize;
        r18.changingElemSize = r2 + 1;
        r1[r2] = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01b2, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decodeNextScanline(int i) throws IIOException {
        boolean z;
        this.changingElemSize = 0;
        int i2 = 0;
        boolean z2 = true;
        while (true) {
            if (i2 >= this.w) {
                break;
            }
            int i3 = i2;
            while (z2 && i3 < this.w) {
                int nextNBits = nextNBits(10);
                short s = white[nextNBits];
                int i4 = s & 1;
                int i5 = (s >>> 1) & 15;
                if (i5 == 12) {
                    short s2 = additionalMakeup[nextLesserThan8Bits(2) | ((nextNBits << 2) & 12)];
                    i3 += (s2 >>> 4) & 4095;
                    updatePointer(4 - ((s2 >>> 1) & 7));
                } else if (i5 == 0) {
                    warning("Error 0");
                } else {
                    if (i5 == 15) {
                        warning("Premature EOL in white run of line " + i + ": read " + i3 + " of " + this.w + " expected pixels.");
                        return;
                    }
                    i3 += (s >>> 5) & 2047;
                    updatePointer(10 - i5);
                    if (i4 == 0) {
                        int[] iArr = this.currChangingElems;
                        int i6 = this.changingElemSize;
                        this.changingElemSize = i6 + 1;
                        iArr[i6] = i3;
                        z2 = false;
                    }
                }
            }
            if (i3 == this.w) {
                int i7 = i3 - i2;
                if (z2 && i7 != 0 && i7 % 64 == 0 && nextNBits(8) != 53) {
                    warning("Missing zero white run length terminating code!");
                    updatePointer(8);
                }
                i2 = i3;
            } else {
                i2 = i3;
                while (!z2 && i2 < this.w) {
                    short s3 = initBlack[nextLesserThan8Bits(4)];
                    int i8 = (s3 >>> 1) & 15;
                    int i9 = (s3 >>> 5) & 2047;
                    if (i9 == 100) {
                        short s4 = black[nextNBits(9)];
                        int i10 = s4 & 1;
                        int i11 = (s4 >>> 1) & 15;
                        int i12 = (s4 >>> 5) & 2047;
                        if (i11 == 12) {
                            updatePointer(5);
                            short s5 = additionalMakeup[nextLesserThan8Bits(4)];
                            int i13 = (s5 >>> 1) & 7;
                            int i14 = (s5 >>> 4) & 4095;
                            setToBlack(i2, i14);
                            i2 += i14;
                            updatePointer(4 - i13);
                        } else {
                            if (i11 == 15) {
                                warning("Premature EOL in black run of line " + i + ": read " + i2 + " of " + this.w + " expected pixels.");
                                return;
                            }
                            setToBlack(i2, i12);
                            i2 += i12;
                            updatePointer(9 - i11);
                            if (i10 == 0) {
                                int[] iArr2 = this.currChangingElems;
                                int i15 = this.changingElemSize;
                                this.changingElemSize = i15 + 1;
                                iArr2[i15] = i2;
                                z2 = true;
                            }
                        }
                    } else {
                        if (i9 == 200) {
                            short s6 = twoBitBlack[nextLesserThan8Bits(2)];
                            int i16 = (s6 >>> 5) & 2047;
                            z = true;
                            setToBlack(i2, i16);
                            i2 += i16;
                            updatePointer(2 - ((s6 >>> 1) & 15));
                            int[] iArr3 = this.currChangingElems;
                            int i17 = this.changingElemSize;
                            this.changingElemSize = i17 + 1;
                            iArr3[i17] = i2;
                        } else {
                            z = true;
                            setToBlack(i2, i9);
                            i2 += i9;
                            updatePointer(4 - i8);
                            int[] iArr4 = this.currChangingElems;
                            int i18 = this.changingElemSize;
                            this.changingElemSize = i18 + 1;
                            iArr4[i18] = i2;
                        }
                        z2 = z;
                    }
                }
                if (i2 == this.w) {
                    int i19 = i2 - i3;
                    if (!z2 && i19 != 0 && i19 % 64 == 0 && nextNBits(10) != 55) {
                        warning("Missing zero black run length terminating code!");
                        updatePointer(10);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e0, code lost:
    
        warning("Unknown coding mode encountered at line " + (r17.srcMinY + r8) + ": read " + r9 + " of " + r17.w + " expected pixels.");
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x010d, code lost:
    
        if (r7 == 1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x010f, code lost:
    
        r7 = findNextLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0113, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0116, code lost:
    
        warning("Sync loss at line " + (r17.srcMinY + r8) + ": read " + r8 + " of " + r2 + " lines.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x013e, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x013f, code lost:
    
        r8 = r8 + (r3 - 1);
        updatePointer(13);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decodeT4() throws IIOException {
        int decodeWhiteCodeWord;
        int i = this.h;
        int[] iArr = new int[2];
        if (this.data.length < 2) {
            throw new IIOException("Insufficient data to read initial EOL.");
        }
        if (nextNBits(12) != 1) {
            warning("T.4 compressed data should begin with EOL.");
        }
        updatePointer(12);
        int i2 = 0;
        int i3 = -1;
        while (i2 != 1) {
            try {
                i2 = findNextLine();
                i3++;
            } catch (EOFException unused) {
                throw new IIOException("No reference line present.");
            }
        }
        decodeNextScanline(this.srcMinY);
        int i4 = i3 + 1;
        this.lineBitNum += this.bitsPerScanline;
        while (i4 < i) {
            try {
                int findNextLine = findNextLine();
                if (findNextLine == 0) {
                    int[] iArr2 = this.prevChangingElems;
                    this.prevChangingElems = this.currChangingElems;
                    this.currChangingElems = iArr2;
                    this.lastChangingElement = 0;
                    int i5 = 0;
                    int i6 = 0;
                    boolean z = true;
                    int i7 = -1;
                    while (true) {
                        if (i5 >= this.w) {
                            break;
                        }
                        getNextChangingElement(i7, z, iArr);
                        int i8 = iArr[0];
                        int i9 = iArr[1];
                        int i10 = twoDCodes[nextLesserThan8Bits(7)] & 255;
                        int i11 = (i10 & 120) >>> 3;
                        int i12 = 7 & i10;
                        if (i11 != 0) {
                            if (i11 != 1) {
                                if (i11 > 8) {
                                    break;
                                }
                                i7 = i8 + (i11 - 5);
                                int i13 = i6 + 1;
                                this.currChangingElems[i6] = i7;
                                if (!z) {
                                    setToBlack(i5, i7 - i5);
                                }
                                z = !z;
                                updatePointer(7 - i12);
                                i5 = i7;
                                i6 = i13;
                            } else {
                                updatePointer(7 - i12);
                                if (z) {
                                    int decodeWhiteCodeWord2 = i5 + decodeWhiteCodeWord();
                                    int i14 = i6 + 1;
                                    this.currChangingElems[i6] = decodeWhiteCodeWord2;
                                    int decodeBlackCodeWord = decodeBlackCodeWord();
                                    setToBlack(decodeWhiteCodeWord2, decodeBlackCodeWord);
                                    decodeWhiteCodeWord = decodeWhiteCodeWord2 + decodeBlackCodeWord;
                                    i6 = i14 + 1;
                                    this.currChangingElems[i14] = decodeWhiteCodeWord;
                                } else {
                                    int decodeBlackCodeWord2 = decodeBlackCodeWord();
                                    setToBlack(i5, decodeBlackCodeWord2);
                                    int i15 = i5 + decodeBlackCodeWord2;
                                    int i16 = i6 + 1;
                                    this.currChangingElems[i6] = i15;
                                    decodeWhiteCodeWord = i15 + decodeWhiteCodeWord();
                                    i6 = i16 + 1;
                                    this.currChangingElems[i16] = decodeWhiteCodeWord;
                                }
                                i7 = decodeWhiteCodeWord;
                                i5 = i7;
                            }
                        } else {
                            if (!z) {
                                setToBlack(i5, i9 - i5);
                            }
                            updatePointer(7 - i12);
                            i5 = i9;
                            i7 = i5;
                        }
                    }
                    this.currChangingElems[i6] = i5;
                    this.changingElemSize = i6 + 1;
                } else {
                    decodeNextScanline(this.srcMinY + i4);
                }
                this.lineBitNum += this.bitsPerScanline;
                i4++;
            } catch (EOFException unused2) {
                warning("Input exhausted before EOL found at line " + (this.srcMinY + i4) + ": read 0 of " + this.w + " expected pixels.");
                return;
            }
        }
    }

    public synchronized void decodeT6() throws IIOException {
        int i;
        int i2;
        int i3 = this.h;
        int[] iArr = new int[2];
        int[] iArr2 = this.currChangingElems;
        int i4 = 0;
        this.changingElemSize = 0;
        int i5 = 0 + 1;
        this.changingElemSize = i5;
        int i6 = this.w;
        iArr2[0] = i6;
        this.changingElemSize = i5 + 1;
        iArr2[i5] = i6;
        int i7 = 0;
        while (i7 < i3) {
            int i8 = -1;
            int[] iArr3 = this.prevChangingElems;
            this.prevChangingElems = this.currChangingElems;
            this.currChangingElems = iArr3;
            this.lastChangingElement = i4;
            int i9 = i4;
            int i10 = i9;
            boolean z = true;
            while (true) {
                i = this.w;
                if (i9 >= i) {
                    break;
                }
                getNextChangingElement(i8, z, iArr);
                int i11 = iArr[i4];
                int i12 = iArr[1];
                int i13 = twoDCodes[nextLesserThan8Bits(7)] & 255;
                int i14 = (i13 & 120) >>> 3;
                int i15 = i13 & 7;
                if (i14 == 0) {
                    if (!z) {
                        int i16 = this.w;
                        if (i12 > i16) {
                            warning("Decoded row " + (this.srcMinY + i7) + " too long; ignoring extra samples.");
                            i12 = i16;
                        }
                        setToBlack(i9, i12 - i9);
                    }
                    i9 = i12;
                    updatePointer(7 - i15);
                } else if (i14 == 1) {
                    updatePointer(7 - i15);
                    if (z) {
                        int decodeWhiteCodeWord = i9 + decodeWhiteCodeWord();
                        int i17 = i10 + 1;
                        iArr3[i10] = decodeWhiteCodeWord;
                        int decodeBlackCodeWord = decodeBlackCodeWord();
                        int i18 = this.w;
                        if (decodeBlackCodeWord > i18 - decodeWhiteCodeWord) {
                            decodeBlackCodeWord = i18 - decodeWhiteCodeWord;
                            warning("Decoded row " + (this.srcMinY + i7) + " too long; ignoring extra samples.");
                        }
                        setToBlack(decodeWhiteCodeWord, decodeBlackCodeWord);
                        i9 = decodeWhiteCodeWord + decodeBlackCodeWord;
                        i2 = i17 + 1;
                        iArr3[i17] = i9;
                    } else {
                        int decodeBlackCodeWord2 = decodeBlackCodeWord();
                        int i19 = this.w;
                        if (decodeBlackCodeWord2 > i19 - i9) {
                            decodeBlackCodeWord2 = i19 - i9;
                            warning("Decoded row " + (this.srcMinY + i7) + " too long; ignoring extra samples.");
                        }
                        setToBlack(i9, decodeBlackCodeWord2);
                        int i20 = i9 + decodeBlackCodeWord2;
                        int i21 = i10 + 1;
                        iArr3[i10] = i20;
                        i9 = i20 + decodeWhiteCodeWord();
                        i2 = i21 + 1;
                        iArr3[i21] = i9;
                    }
                    i10 = i2;
                } else if (i14 <= 8) {
                    int i22 = i11 + (i14 - 5);
                    int i23 = i10 + 1;
                    iArr3[i10] = i22;
                    if (!z) {
                        int i24 = this.w;
                        if (i22 > i24) {
                            warning("Decoded row " + (this.srcMinY + i7) + " too long; ignoring extra samples.");
                            i22 = i24;
                        }
                        setToBlack(i9, i22 - i9);
                    }
                    i9 = i22;
                    z = !z;
                    updatePointer(7 - i15);
                    i10 = i23;
                } else {
                    if (i14 == 11) {
                        int nextLesserThan8Bits = nextLesserThan8Bits(3);
                        if (nextLesserThan8Bits != 7) {
                            warning("Unsupported entrance code " + nextLesserThan8Bits + " for extension mode at line " + (this.srcMinY + i7) + ".");
                        }
                        boolean z2 = false;
                        int i25 = 0;
                        while (!z2) {
                            while (nextLesserThan8Bits(1) != 1) {
                                i25++;
                            }
                            if (i25 > 5) {
                                i25 -= 6;
                                if (!z && i25 > 0) {
                                    iArr3[i10] = i9;
                                    i10++;
                                }
                                i9 += i25;
                                if (i25 > 0) {
                                    z = true;
                                }
                                if (nextLesserThan8Bits(1) == 0) {
                                    if (!z) {
                                        iArr3[i10] = i9;
                                        i10++;
                                    }
                                    z = true;
                                } else {
                                    if (z) {
                                        iArr3[i10] = i9;
                                        i10++;
                                    }
                                    z = false;
                                }
                                z2 = true;
                            }
                            if (i25 == 5) {
                                if (!z) {
                                    iArr3[i10] = i9;
                                    i10++;
                                }
                                i9 += i25;
                                z = true;
                            } else {
                                int i26 = i9 + i25;
                                iArr3[i10] = i26;
                                setToBlack(i26, 1);
                                i9 = i26 + 1;
                                i10++;
                                z = false;
                            }
                        }
                    } else {
                        warning("Unknown coding mode encountered at line " + (this.srcMinY + i7) + ".");
                    }
                    i4 = 0;
                }
                i8 = i9;
                i4 = 0;
            }
            if (i10 <= i) {
                iArr3[i10] = i9;
                i10++;
            }
            this.changingElemSize = i10;
            this.lineBitNum += this.bitsPerScanline;
            i7++;
            i4 = 0;
        }
    }

    private void setToBlack(int i, int i2) {
        int i3 = i + this.lineBitNum;
        int i4 = i2 + i3;
        int i5 = i3 >> 3;
        int i6 = i3 & 7;
        if (i6 > 0) {
            int i7 = 1 << (7 - i6);
            byte b = this.buffer[i5];
            while (i7 > 0 && i3 < i4) {
                b = (byte) (b | i7);
                i7 >>= 1;
                i3++;
            }
            this.buffer[i5] = b;
        }
        int i8 = i3 >> 3;
        while (i3 < i4 - 7) {
            this.buffer[i8] = -1;
            i3 += 8;
            i8++;
        }
        while (i3 < i4) {
            int i9 = i3 >> 3;
            byte[] bArr = this.buffer;
            bArr[i9] = (byte) (bArr[i9] | (1 << (7 - (i3 & 7))));
            i3++;
        }
    }

    private int decodeWhiteCodeWord() throws IIOException {
        boolean z = true;
        int i = 0;
        while (z) {
            int nextNBits = nextNBits(10);
            short s = white[nextNBits];
            int i2 = s & 1;
            int i3 = (s >>> 1) & 15;
            if (i3 == 12) {
                short s2 = additionalMakeup[((nextNBits << 2) & 12) | nextLesserThan8Bits(2)];
                i += (s2 >>> 4) & 4095;
                updatePointer(4 - ((s2 >>> 1) & 7));
            } else {
                if (i3 == 0) {
                    throw new IIOException("Error 0");
                }
                if (i3 == 15) {
                    throw new IIOException("Error 1");
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

    private int decodeBlackCodeWord() throws IIOException {
        boolean z = false;
        int i = 0;
        while (!z) {
            short s = initBlack[nextLesserThan8Bits(4)];
            int i2 = (s >>> 1) & 15;
            int i3 = (s >>> 5) & 2047;
            if (i3 == 100) {
                short s2 = black[nextNBits(9)];
                int i4 = s2 & 1;
                int i5 = (s2 >>> 1) & 15;
                int i6 = (s2 >>> 5) & 2047;
                if (i5 == 12) {
                    updatePointer(5);
                    short s3 = additionalMakeup[nextLesserThan8Bits(4)];
                    i += (s3 >>> 4) & 4095;
                    updatePointer(4 - ((s3 >>> 1) & 7));
                } else {
                    if (i5 == 15) {
                        throw new IIOException("Error 2");
                    }
                    i += i6;
                    updatePointer(9 - i5);
                    if (i4 == 0) {
                    }
                }
            } else if (i3 == 200) {
                short s4 = twoBitBlack[nextLesserThan8Bits(2)];
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

    private int findNextLine() throws IIOException, EOFException {
        int length = (this.data.length * 8) - 1;
        int i = length - 12;
        int i2 = (this.bytePointer * 8) + this.bitPointer;
        while (i2 <= i) {
            int nextNBits = nextNBits(12);
            i2 += 12;
            while (nextNBits != 1 && i2 < length) {
                nextNBits = ((nextNBits & 2047) << 1) | (nextLesserThan8Bits(1) & 1);
                i2++;
            }
            if (nextNBits == 1) {
                if (this.oneD != 1) {
                    return 1;
                }
                if (i2 < length) {
                    return nextLesserThan8Bits(1);
                }
            }
        }
        throw new EOFException();
    }

    private void getNextChangingElement(int i, boolean z, int[] iArr) throws IIOException {
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

    private int nextNBits(int i) throws IIOException {
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
            byte[] bArr2 = flipTable;
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
            throw new IIOException("Invalid FillOrder");
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
        int i11 = (b3 & table1[i8]) << i9;
        int[] iArr = table2;
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

    private int nextLesserThan8Bits(int i) throws IIOException {
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
            byte[] bArr2 = flipTable;
            byte b3 = bArr2[bArr[i2] & 255];
            b = i2 == length ? (byte) 0 : bArr2[bArr[i2 + 1] & 255];
            b2 = b3;
        } else {
            throw new IIOException("Invalid FillOrder");
        }
        int i4 = this.bitPointer;
        int i5 = 8 - i4;
        int i6 = i - i5;
        int i7 = i5 - i;
        if (i7 >= 0) {
            int i8 = (table1[i5] & b2) >>> i7;
            int i9 = i4 + i;
            this.bitPointer = i9;
            if (i9 != 8) {
                return i8;
            }
            this.bitPointer = 0;
            this.bytePointer = i2 + 1;
            return i8;
        }
        int i10 = ((b & table2[i6]) >>> (8 - i6)) | ((table1[i5] & b2) << (-i7));
        this.bytePointer = i2 + 1;
        this.bitPointer = i6;
        return i10;
    }

    private void updatePointer(int i) {
        if (i > 8) {
            this.bytePointer -= i / 8;
            i %= 8;
        }
        int i2 = this.bitPointer - i;
        if (i2 < 0) {
            this.bytePointer--;
            this.bitPointer = i2 + 8;
        } else {
            this.bitPointer = i2;
        }
    }

    private void warning(String str) {
        if (this.reader instanceof TIFFImageReader) {
            ((TIFFImageReader) this.reader).forwardWarningMessage(str);
        }
    }
}
