package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.lang.reflect.Array;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;
    private long A0;
    private long A1;
    private long A2;
    private long A3;
    private int BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, Ascii.EM, 1, ISO7816.INS_INCREASE, 2, Ascii.SUB, -58, 75, -57, Ascii.ESC, 104, 51, -18, -33, 3, 100, 4, ISO7816.INS_CREATE_FILE, 14, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.DATA_BYTES2, ByteSourceJsonBootstrapper.UTF8_BOM_1, 76, 113, 8, -56, -8, 105, 28, -63, 125, ISO7816.INS_ENVELOPE, 29, -75, -7, -71, 39, 106, AvcSpsUtils.PROFILE_IDC_MAIN, ISO7816.INS_DELETE_FILE, -90, 114, -102, -55, 9, 120, 101, 47, ISOFileInfo.LCS_BYTE, 5, 33, 15, -31, ISO7816.INS_CHANGE_CHV, Ascii.DC2, -16, -126, 69, 53, -109, ISO7816.INS_PUT_DATA, ISOFileInfo.CHANNEL_SECURITY, -106, -113, -37, -67, 54, ISO7816.INS_WRITE_BINARY, -50, -108, 19, 92, ISO7816.INS_WRITE_RECORD, -15, SignedBytes.MAX_POWER_OF_TWO, 70, ISOFileInfo.FILE_IDENTIFIER, 56, 102, -35, -3, ISO7816.INS_DECREASE, ByteSourceJsonBootstrapper.UTF8_BOM_3, 6, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.FCP_BYTE, ISO7816.INS_READ_RECORD2, 37, ISO7816.INS_APPEND_RECORD, -104, ISO7816.INS_MSE, -120, -111, 16, 126, 110, 72, -61, -93, ISO7816.INS_READ_RECORD_STAMPED, 30, 66, 58, 107, 40, 84, -6, ISOFileInfo.PROP_INFO, Base64.padSymbol, -70, 43, 121, 10, Ascii.NAK, -101, -97, 94, ISO7816.INS_GET_DATA, 78, -44, -84, -27, -13, 115, -89, 87, -81, AvcSpsUtils.PROFILE_IDC_EXTENDED, -88, 80, -12, -22, ISO7816.INS_UPDATE_BINARY, 116, 79, -82, -23, -43, -25, -26, -83, -24, ISO7816.INS_UNBLOCK_CHV, -41, 117, 122, -21, Ascii.SYN, 11, -11, 89, -53, 95, ISO7816.INS_READ_BINARY, -100, -87, 81, ISOFileInfo.A0, Byte.MAX_VALUE, 12, -10, ISOFileInfo.FCI_BYTE, Ascii.ETB, -60, 73, -20, ISO7816.INS_LOAD_KEY_FILE, 67, Ascii.US, 45, -92, 118, 123, -73, -52, ByteSourceJsonBootstrapper.UTF8_BOM_2, 62, 90, -5, 96, ISO7816.INS_READ_BINARY2, -122, 59, 82, ISOFileInfo.A1, 108, -86, 85, 41, -99, -105, -78, ISOFileInfo.FCI_EXT, -112, 97, -66, ISO7816.INS_UPDATE_RECORD, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, Utf8.REPLACEMENT_BYTE, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, Ascii.DC4, ISO7816.INS_PSO, -98, 93, 86, -14, -45, ISOFileInfo.AB, ISO7816.INS_REHABILITATE_CHV, 17, -110, -39, 35, 32, 46, -119, ISO7816.INS_READ_BINARY_STAMPED, 124, -72, 38, 119, -103, -29, ISOFileInfo.A5, 103, 74, -19, -34, -59, 49, -2, Ascii.CAN, 13, 99, ISOFileInfo.SECURITY_ATTR_COMPACT, Byte.MIN_VALUE, ISO7816.INS_GET_RESPONSE, -9, ISO7816.INS_MANAGE_CHANNEL, 7};
    private static final byte[] aLogtable = {0, 3, 5, 15, 17, 51, 85, -1, Ascii.SUB, 46, 114, -106, ISOFileInfo.A1, -8, 19, 53, 95, -31, 56, 72, ISO7816.INS_LOAD_KEY_FILE, 115, -107, -92, -9, 2, 6, 10, 30, ISO7816.INS_MSE, 102, -86, -27, ISO7816.INS_DECREASE_STAMPED, 92, ISO7816.INS_DELETE_FILE, 55, 89, -21, 38, 106, -66, -39, ISO7816.INS_MANAGE_CHANNEL, -112, ISOFileInfo.AB, -26, 49, 83, -11, 4, 12, Ascii.DC4, 60, ISO7816.INS_REHABILITATE_CHV, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, ISO7816.INS_CREATE_FILE, 59, AvcSpsUtils.PROFILE_IDC_MAIN, -41, ISOFileInfo.FCP_BYTE, -90, -15, 8, Ascii.CAN, 40, 120, -120, ISOFileInfo.FILE_IDENTIFIER, -98, -71, ISO7816.INS_WRITE_BINARY, 107, -67, ISO7816.INS_UPDATE_RECORD, Byte.MAX_VALUE, ISOFileInfo.DATA_BYTES2, -104, ISO7816.INS_READ_RECORD2, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, ISO7816.INS_DECREASE, 80, -16, 11, 29, 39, 105, ByteSourceJsonBootstrapper.UTF8_BOM_2, ISO7816.INS_UPDATE_BINARY, 97, -93, -2, Ascii.EM, 43, 125, ISOFileInfo.FCI_EXT, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, ISOFileInfo.A0, -5, Ascii.SYN, 58, 78, ISO7816.INS_WRITE_RECORD, 109, -73, ISO7816.INS_ENVELOPE, 93, -25, ISO7816.INS_INCREASE, 86, -6, Ascii.NAK, Utf8.REPLACEMENT_BYTE, 65, -61, 94, ISO7816.INS_APPEND_RECORD, Base64.padSymbol, 71, -55, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_GET_RESPONSE, 91, -19, ISO7816.INS_UNBLOCK_CHV, 116, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, ISO7816.INS_PUT_DATA, 117, -97, -70, -43, 100, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISO7816.INS_PSO, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, ISOFileInfo.CHANNEL_SECURITY, -119, Byte.MIN_VALUE, -101, ISO7816.INS_READ_RECORD_STAMPED, -63, AvcSpsUtils.PROFILE_IDC_EXTENDED, -24, 35, 101, -81, -22, 37, ISOFileInfo.FCI_BYTE, ISO7816.INS_READ_BINARY2, -56, 67, -59, 84, -4, Ascii.US, 33, 99, ISOFileInfo.A5, -12, 7, 9, Ascii.ESC, 45, 119, -103, ISO7816.INS_READ_BINARY, -53, 70, ISO7816.INS_GET_DATA, 69, -49, 74, -34, 121, ISOFileInfo.SECURITY_ATTR_EXP, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, Ascii.DC2, 54, 90, -18, 41, 123, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.SECURITY_ATTR_COMPACT, -113, ISOFileInfo.LCS_BYTE, ISOFileInfo.PROP_INFO, -108, -89, -14, 13, Ascii.ETB, 57, 75, -35, 124, -124, -105, -94, -3, 28, ISO7816.INS_CHANGE_CHV, 108, ISO7816.INS_READ_BINARY_STAMPED, -57, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, Ascii.SUB, 46, 114, -106, ISOFileInfo.A1, -8, 19, 53, 95, -31, 56, 72, ISO7816.INS_LOAD_KEY_FILE, 115, -107, -92, -9, 2, 6, 10, 30, ISO7816.INS_MSE, 102, -86, -27, ISO7816.INS_DECREASE_STAMPED, 92, ISO7816.INS_DELETE_FILE, 55, 89, -21, 38, 106, -66, -39, ISO7816.INS_MANAGE_CHANNEL, -112, ISOFileInfo.AB, -26, 49, 83, -11, 4, 12, Ascii.DC4, 60, ISO7816.INS_REHABILITATE_CHV, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, ISO7816.INS_CREATE_FILE, 59, AvcSpsUtils.PROFILE_IDC_MAIN, -41, ISOFileInfo.FCP_BYTE, -90, -15, 8, Ascii.CAN, 40, 120, -120, ISOFileInfo.FILE_IDENTIFIER, -98, -71, ISO7816.INS_WRITE_BINARY, 107, -67, ISO7816.INS_UPDATE_RECORD, Byte.MAX_VALUE, ISOFileInfo.DATA_BYTES2, -104, ISO7816.INS_READ_RECORD2, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, ISO7816.INS_DECREASE, 80, -16, 11, 29, 39, 105, ByteSourceJsonBootstrapper.UTF8_BOM_2, ISO7816.INS_UPDATE_BINARY, 97, -93, -2, Ascii.EM, 43, 125, ISOFileInfo.FCI_EXT, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, ISOFileInfo.A0, -5, Ascii.SYN, 58, 78, ISO7816.INS_WRITE_RECORD, 109, -73, ISO7816.INS_ENVELOPE, 93, -25, ISO7816.INS_INCREASE, 86, -6, Ascii.NAK, Utf8.REPLACEMENT_BYTE, 65, -61, 94, ISO7816.INS_APPEND_RECORD, Base64.padSymbol, 71, -55, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_GET_RESPONSE, 91, -19, ISO7816.INS_UNBLOCK_CHV, 116, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, ISO7816.INS_PUT_DATA, 117, -97, -70, -43, 100, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISO7816.INS_PSO, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, ISOFileInfo.CHANNEL_SECURITY, -119, Byte.MIN_VALUE, -101, ISO7816.INS_READ_RECORD_STAMPED, -63, AvcSpsUtils.PROFILE_IDC_EXTENDED, -24, 35, 101, -81, -22, 37, ISOFileInfo.FCI_BYTE, ISO7816.INS_READ_BINARY2, -56, 67, -59, 84, -4, Ascii.US, 33, 99, ISOFileInfo.A5, -12, 7, 9, Ascii.ESC, 45, 119, -103, ISO7816.INS_READ_BINARY, -53, 70, ISO7816.INS_GET_DATA, 69, -49, 74, -34, 121, ISOFileInfo.SECURITY_ATTR_EXP, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, Ascii.DC2, 54, 90, -18, 41, 123, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.SECURITY_ATTR_COMPACT, -113, ISOFileInfo.LCS_BYTE, ISOFileInfo.PROP_INFO, -108, -89, -14, 13, Ascii.ETB, 57, 75, -35, 124, -124, -105, -94, -3, 28, ISO7816.INS_CHANGE_CHV, 108, ISO7816.INS_READ_BINARY_STAMPED, -57, 82, -10, 1};
    private static final byte[] S = {99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.AB, 118, ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117, 9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, Ascii.SUB, Ascii.ESC, 110, 90, ISOFileInfo.A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, AvcSpsUtils.PROFILE_IDC_EXTENDED, -49, ISO7816.INS_WRITE_BINARY, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, AvcSpsUtils.PROFILE_IDC_MAIN, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD, -51, 12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, Ascii.ETB, -60, -89, 126, Base64.padSymbol, 100, 93, Ascii.EM, 115, 96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, 11, -37, ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121, -25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, Ascii.US, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, 30, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.A1, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, ISO7816.INS_DECREASE, 54, ISOFileInfo.A5, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, SignedBytes.MAX_POWER_OF_TWO, -93, -98, ISOFileInfo.DATA_BYTES2, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.CHANNEL_SECURITY, 67, ISO7816.INS_REHABILITATE_CHV, -60, -34, -23, -53, 84, 123, -108, ISO7816.INS_INCREASE, -90, ISO7816.INS_ENVELOPE, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, ISOFileInfo.A1, 102, 40, -39, ISO7816.INS_CHANGE_CHV, -78, 118, 91, -94, 73, 109, ISOFileInfo.SECURITY_ATTR_EXP, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, ISO7816.INS_READ_RECORD_STAMPED, -110, 108, ISO7816.INS_MANAGE_CHANNEL, 72, 80, -3, -19, -71, ISO7816.INS_PUT_DATA, 94, Ascii.NAK, 70, 87, -89, ISOFileInfo.ENV_TEMP_EF, -99, -124, -112, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, 0, ISOFileInfo.SECURITY_ATTR_COMPACT, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, ISO7816.INS_DELETE_FILE, AvcSpsUtils.PROFILE_IDC_EXTENDED, 5, -72, ISO7816.INS_READ_RECORD2, 69, 6, ISO7816.INS_WRITE_BINARY, ISO7816.INS_UNBLOCK_CHV, 30, -113, ISO7816.INS_GET_DATA, Utf8.REPLACEMENT_BYTE, 15, 2, -63, -81, -67, 3, 1, 19, ISOFileInfo.LCS_BYTE, 107, 58, -111, 17, 65, 79, 103, ISO7816.INS_UPDATE_RECORD, -22, -105, -14, -49, -50, -16, ISO7816.INS_READ_BINARY_STAMPED, -26, 115, -106, -84, 116, ISO7816.INS_MSE, -25, -83, 53, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, -7, 55, -24, 28, 117, -33, 110, 71, -15, Ascii.SUB, 113, 29, 41, -59, -119, ISOFileInfo.FCI_BYTE, -73, ISOFileInfo.FCP_BYTE, 14, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, ISO7816.INS_WRITE_RECORD, 121, 32, -102, -37, ISO7816.INS_GET_RESPONSE, -2, 120, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, 49, ISO7816.INS_READ_BINARY2, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISOFileInfo.A0, ISO7816.INS_CREATE_FILE, 59, AvcSpsUtils.PROFILE_IDC_MAIN, -82, ISO7816.INS_PSO, -11, ISO7816.INS_READ_BINARY, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, ISOFileInfo.FILE_IDENTIFIER, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, ISO7816.INS_UPDATE_BINARY, 38, -31, 105, Ascii.DC4, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, 151, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, Ascii.CAN, 32}};
    static byte[][] shifts1 = {new byte[]{0, Ascii.CAN, 16, 8}, new byte[]{0, 32, Ascii.CAN, 16}, new byte[]{0, 40, 32, Ascii.CAN}, new byte[]{0, ISO7816.INS_DECREASE, 40, Ascii.CAN}, new byte[]{0, 56, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.BC = 32;
            this.BC_MASK = 4294967295L;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else {
            if (i != 256) {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
            this.BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = i;
    }

    private void InvMixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            long j5 = j3;
            int i5 = (int) ((this.A3 >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? logtable[i2 & 255] & 255 : -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & 255 : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & 255 : -1;
            if (i5 != 0) {
                i6 = logtable[i5 & 255] & 255;
            }
            j |= ((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i6)) & 255) << i;
            j4 |= ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i6)) ^ mul0x9(i7)) & 255) << i;
            j2 |= ((((mul0xe(i9) ^ mul0xb(i6)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255) << i;
            j3 = (((((mul0xe(i6) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255) << i) | j5;
        }
        this.A0 = j;
        this.A1 = j4;
        this.A2 = j2;
        this.A3 = j3;
    }

    private void KeyAddition(long[] jArr) {
        this.A0 ^= jArr[0];
        this.A1 ^= jArr[1];
        this.A2 ^= jArr[2];
        this.A3 ^= jArr[3];
    }

    private void MixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            long j5 = j3;
            int i5 = (int) ((this.A3 >> i) & 255);
            j |= ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255) << i;
            j4 |= ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255) << i;
            j2 |= ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255) << i;
            j3 = (((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255) << i) | j5;
        }
        this.A0 = j;
        this.A1 = j4;
        this.A2 = j2;
        this.A3 = j3;
    }

    private void ShiftRow(byte[] bArr) {
        this.A1 = shift(this.A1, bArr[1]);
        this.A2 = shift(this.A2, bArr[2]);
        this.A3 = shift(this.A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.A0 = applyS(this.A0, bArr);
        this.A1 = applyS(this.A1, bArr);
        this.A2 = applyS(this.A2, bArr);
        this.A3 = applyS(this.A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            j2 |= (bArr[(int) ((j >> i) & 255)] & 255) << i;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2;
        int i3 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) long.class, 15, 4);
        int i4 = 4;
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
            i = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i + 6 : (this.BC / 8) + 6;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < bArr.length) {
            bArr2[i6 % 4][i6 / 4] = bArr[i7];
            i6++;
            i7++;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < i && i9 < (this.ROUNDS + 1) * (this.BC / 8)) {
            int i10 = 0;
            while (i10 < i4) {
                int i11 = this.BC;
                long[] jArr2 = jArr[i9 / (i11 / 8)];
                jArr2[i10] = ((bArr2[i10][i8] & 255) << ((i9 * 8) % i11)) | jArr2[i10];
                i10++;
                i4 = 4;
            }
            i8++;
            i9++;
            i4 = 4;
        }
        int i12 = 0;
        while (i9 < (this.ROUNDS + 1) * (this.BC / i3)) {
            int i13 = i5;
            while (i13 < 4) {
                byte[] bArr3 = bArr2[i13];
                i13++;
                bArr3[i5] = (byte) (bArr3[i5] ^ S[bArr2[i13 % 4][i - 1] & 255]);
            }
            byte[] bArr4 = bArr2[i5];
            int i14 = i12 + 1;
            bArr4[i5] = (byte) (rcon[i12] ^ bArr4[i5]);
            int i15 = 1;
            if (i <= 6) {
                while (i15 < i) {
                    for (int i16 = i5; i16 < 4; i16++) {
                        byte[] bArr5 = bArr2[i16];
                        bArr5[i15] = (byte) (bArr5[i15] ^ bArr2[i16][i15 - 1]);
                    }
                    i15++;
                }
            } else {
                while (true) {
                    i2 = 4;
                    if (i15 >= 4) {
                        break;
                    }
                    int i17 = i5;
                    while (i17 < i2) {
                        byte[] bArr6 = bArr2[i17];
                        bArr6[i15] = (byte) (bArr6[i15] ^ bArr2[i17][i15 - 1]);
                        i17++;
                        i2 = 4;
                    }
                    i15++;
                }
                for (int i18 = i5; i18 < 4; i18++) {
                    byte[] bArr7 = bArr2[i18];
                    bArr7[4] = (byte) (bArr7[4] ^ S[bArr2[i18][3] & 255]);
                }
                int i19 = 5;
                while (i19 < i) {
                    int i20 = i5;
                    while (i20 < i2) {
                        byte[] bArr8 = bArr2[i20];
                        bArr8[i19] = (byte) (bArr8[i19] ^ bArr2[i20][i19 - 1]);
                        i20++;
                        i2 = 4;
                    }
                    i19++;
                    i2 = 4;
                }
            }
            int i21 = i5;
            while (i21 < i && i9 < (this.ROUNDS + 1) * (this.BC / i3)) {
                for (int i22 = i5; i22 < 4; i22++) {
                    int i23 = this.BC;
                    long[] jArr3 = jArr[i9 / (i23 / 8)];
                    jArr3[i22] = ((bArr2[i22][i21] & 255) << ((i9 * 8) % i23)) | jArr3[i22];
                }
                i21++;
                i9++;
                i5 = 0;
                i3 = 8;
            }
            i12 = i14;
            i5 = 0;
            i3 = 8;
        }
        return jArr;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + 199];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.BC; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) (this.A0 >> i2);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (this.A1 >> i2);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.A2 >> i2);
            i = i5 + 1;
            bArr[i5] = (byte) (this.A3 >> i2);
        }
    }

    private long shift(long j, int i) {
        return ((j << (this.BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.A0 = bArr[i] & 255;
        this.A1 = bArr[r0] & 255;
        this.A2 = bArr[r8] & 255;
        int i2 = i + 1 + 1 + 1 + 1;
        this.A3 = bArr[r0] & 255;
        for (int i3 = 8; i3 != this.BC; i3 += 8) {
            this.A0 |= (bArr[i2] & 255) << i3;
            this.A1 |= (bArr[r3] & 255) << i3;
            this.A2 |= (bArr[r8] & 255) << i3;
            i2 = i2 + 1 + 1 + 1 + 1;
            this.A3 |= (bArr[r3] & 255) << i3;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        int i3 = this.BC;
        if ((i3 / 2) + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((i3 / 2) + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        long[][] jArr = this.workingKey;
        if (z) {
            encryptBlock(jArr);
        } else {
            decryptBlock(jArr);
        }
        packBlock(bArr2, i2);
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
