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
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes5.dex */
public class ARIAEngine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[][] C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = {99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.AB, 118, ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117, 9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, Ascii.SUB, Ascii.ESC, 110, 90, ISOFileInfo.A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, AvcSpsUtils.PROFILE_IDC_EXTENDED, -49, ISO7816.INS_WRITE_BINARY, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, AvcSpsUtils.PROFILE_IDC_MAIN, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD, -51, 12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, Ascii.ETB, -60, -89, 126, Base64.padSymbol, 100, 93, Ascii.EM, 115, 96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, 11, -37, ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121, -25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, Ascii.US, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, 30, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.A1, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN};
    private static final byte[] SB2_sbox = {ISO7816.INS_APPEND_RECORD, 78, 84, -4, -108, ISO7816.INS_ENVELOPE, 74, -52, ISOFileInfo.FCP_BYTE, 13, 106, 70, 60, AvcSpsUtils.PROFILE_IDC_MAIN, ISOFileInfo.SECURITY_ATTR_EXP, -47, 94, -6, 100, -53, ISO7816.INS_READ_BINARY_STAMPED, -105, -66, 43, PSSSigner.TRAILER_IMPLICIT, 119, 46, 3, -45, Ascii.EM, 89, -63, 29, 6, 65, 107, 85, -16, -103, 105, -22, -100, Ascii.CAN, -82, 99, -33, -25, ByteSourceJsonBootstrapper.UTF8_BOM_2, 0, 115, 102, -5, -106, 76, ISOFileInfo.PROP_INFO, ISO7816.INS_DELETE_FILE, 58, 9, 69, -86, 15, -18, 16, -21, 45, Byte.MAX_VALUE, -12, 41, -84, -49, -83, -111, ISOFileInfo.ENV_TEMP_EF, 120, -56, -107, -7, 47, -50, -51, 8, 122, -120, 56, 92, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_PSO, 40, 71, -37, -72, -57, -109, -92, Ascii.DC2, 83, -1, ISOFileInfo.FCI_EXT, 14, 49, 54, 33, AvcSpsUtils.PROFILE_IDC_EXTENDED, 72, 1, ISOFileInfo.CHANNEL_SECURITY, 55, 116, ISO7816.INS_INCREASE, ISO7816.INS_GET_DATA, -23, ISO7816.INS_READ_BINARY2, -73, ISOFileInfo.AB, 12, -41, -60, 86, 66, 38, 7, -104, 96, -39, ISO7816.INS_READ_RECORD_STAMPED, -71, 17, SignedBytes.MAX_POWER_OF_TWO, -20, 32, ISOFileInfo.SECURITY_ATTR_COMPACT, -67, ISOFileInfo.A0, -55, -124, 4, 73, 35, -15, 79, 80, Ascii.US, 19, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_LOAD_KEY_FILE, ISO7816.INS_GET_RESPONSE, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, Ascii.NAK, -35, -3, Ascii.ETB, -87, ByteSourceJsonBootstrapper.UTF8_BOM_3, -44, -102, 126, -59, 57, 103, -2, 118, -99, 67, -89, -31, ISO7816.INS_WRITE_BINARY, -11, 104, -14, Ascii.ESC, ISO7816.INS_DECREASE_STAMPED, ISO7816.INS_MANAGE_CHANNEL, 5, -93, ISOFileInfo.LCS_BYTE, -43, 121, -122, -88, ISO7816.INS_DECREASE, -58, 81, 75, 30, -90, 39, -10, 53, ISO7816.INS_WRITE_RECORD, 110, ISO7816.INS_CHANGE_CHV, Ascii.SYN, -126, 95, ISO7816.INS_PUT_DATA, -26, 117, -94, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISO7816.INS_UNBLOCK_CHV, -78, 28, -97, 93, ISOFileInfo.FCI_BYTE, Byte.MIN_VALUE, 10, 114, ISO7816.INS_REHABILITATE_CHV, -101, 108, -112, 11, 91, 51, 125, 90, 82, -13, 97, ISOFileInfo.A1, -9, ISO7816.INS_READ_BINARY, ISO7816.INS_UPDATE_BINARY, Utf8.REPLACEMENT_BYTE, 124, 109, -19, Ascii.DC4, ISO7816.INS_CREATE_FILE, ISOFileInfo.A5, Base64.padSymbol, ISO7816.INS_MSE, ISO7816.INS_READ_RECORD2, -8, -119, -34, 113, Ascii.SUB, -81, -70, -75, ISOFileInfo.DATA_BYTES2};
    private static final byte[] SB3_sbox = {82, 9, 106, -43, ISO7816.INS_DECREASE, 54, ISOFileInfo.A5, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, SignedBytes.MAX_POWER_OF_TWO, -93, -98, ISOFileInfo.DATA_BYTES2, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.CHANNEL_SECURITY, 67, ISO7816.INS_REHABILITATE_CHV, -60, -34, -23, -53, 84, 123, -108, ISO7816.INS_INCREASE, -90, ISO7816.INS_ENVELOPE, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, ISOFileInfo.A1, 102, 40, -39, ISO7816.INS_CHANGE_CHV, -78, 118, 91, -94, 73, 109, ISOFileInfo.SECURITY_ATTR_EXP, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, ISO7816.INS_READ_RECORD_STAMPED, -110, 108, ISO7816.INS_MANAGE_CHANNEL, 72, 80, -3, -19, -71, ISO7816.INS_PUT_DATA, 94, Ascii.NAK, 70, 87, -89, ISOFileInfo.ENV_TEMP_EF, -99, -124, -112, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, 0, ISOFileInfo.SECURITY_ATTR_COMPACT, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, ISO7816.INS_DELETE_FILE, AvcSpsUtils.PROFILE_IDC_EXTENDED, 5, -72, ISO7816.INS_READ_RECORD2, 69, 6, ISO7816.INS_WRITE_BINARY, ISO7816.INS_UNBLOCK_CHV, 30, -113, ISO7816.INS_GET_DATA, Utf8.REPLACEMENT_BYTE, 15, 2, -63, -81, -67, 3, 1, 19, ISOFileInfo.LCS_BYTE, 107, 58, -111, 17, 65, 79, 103, ISO7816.INS_UPDATE_RECORD, -22, -105, -14, -49, -50, -16, ISO7816.INS_READ_BINARY_STAMPED, -26, 115, -106, -84, 116, ISO7816.INS_MSE, -25, -83, 53, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, -7, 55, -24, 28, 117, -33, 110, 71, -15, Ascii.SUB, 113, 29, 41, -59, -119, ISOFileInfo.FCI_BYTE, -73, ISOFileInfo.FCP_BYTE, 14, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, ISO7816.INS_WRITE_RECORD, 121, 32, -102, -37, ISO7816.INS_GET_RESPONSE, -2, 120, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, 49, ISO7816.INS_READ_BINARY2, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISOFileInfo.A0, ISO7816.INS_CREATE_FILE, 59, AvcSpsUtils.PROFILE_IDC_MAIN, -82, ISO7816.INS_PSO, -11, ISO7816.INS_READ_BINARY, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, ISOFileInfo.FILE_IDENTIFIER, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, ISO7816.INS_UPDATE_BINARY, 38, -31, 105, Ascii.DC4, 99, 85, 33, 12, 125};
    private static final byte[] SB4_sbox = {ISO7816.INS_DECREASE, 104, -103, Ascii.ESC, ISOFileInfo.FCI_EXT, -71, 33, 120, 80, 57, -37, -31, 114, 9, ISOFileInfo.FCP_BYTE, 60, 62, 126, 94, ISOFileInfo.CHANNEL_SECURITY, -15, ISOFileInfo.A0, -52, -93, ISO7816.INS_PSO, 29, -5, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_UPDATE_BINARY, 32, -60, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.DATA_BYTES2, 101, -11, -119, -53, -99, 119, -58, 87, 67, 86, Ascii.ETB, -44, SignedBytes.MAX_POWER_OF_TWO, Ascii.SUB, AvcSpsUtils.PROFILE_IDC_MAIN, ISO7816.INS_GET_RESPONSE, 99, 108, -29, -73, -56, 100, 106, 83, -86, 56, -104, 12, -12, -101, -19, Byte.MAX_VALUE, ISO7816.INS_MSE, 118, -81, -35, 58, 11, AvcSpsUtils.PROFILE_IDC_EXTENDED, 103, -120, 6, -61, 53, 13, 1, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.SECURITY_ATTR_COMPACT, ISO7816.INS_ENVELOPE, -26, 95, 2, ISO7816.INS_CHANGE_CHV, 117, -109, 102, 30, -27, ISO7816.INS_APPEND_RECORD, 84, ISO7816.INS_LOAD_KEY_FILE, 16, -50, 122, -24, 8, ISO7816.INS_UNBLOCK_CHV, Ascii.DC2, -105, ISO7816.INS_INCREASE, ISOFileInfo.AB, ISO7816.INS_READ_BINARY_STAMPED, 39, 10, 35, -33, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISO7816.INS_GET_DATA, -39, -72, -6, ISO7816.INS_UPDATE_RECORD, 49, 107, -47, -83, Ascii.EM, 73, -67, 81, -106, -18, ISO7816.INS_DELETE_FILE, -88, 65, ISO7816.INS_PUT_DATA, -1, -51, 85, -122, 54, -66, 97, 82, -8, ByteSourceJsonBootstrapper.UTF8_BOM_2, 14, -126, 72, 105, -102, ISO7816.INS_CREATE_FILE, 71, -98, 92, 4, 75, ISO7816.INS_DECREASE_STAMPED, Ascii.NAK, 121, 38, -89, -34, 41, -82, -110, -41, -124, -23, ISO7816.INS_WRITE_RECORD, -70, 93, -13, -59, ISO7816.INS_READ_BINARY, ByteSourceJsonBootstrapper.UTF8_BOM_3, -92, 59, 113, ISO7816.INS_REHABILITATE_CHV, 70, 43, -4, -21, ISOFileInfo.FCI_BYTE, -43, -10, Ascii.DC4, -2, 124, ISO7816.INS_MANAGE_CHANNEL, 90, 125, -3, 47, Ascii.CAN, ISOFileInfo.FILE_IDENTIFIER, Ascii.SYN, ISOFileInfo.A5, -111, Ascii.US, 5, -107, 116, -87, -63, 91, 74, ISOFileInfo.PROP_INFO, 109, 19, 7, 79, 78, 69, -78, 15, -55, 28, -90, PSSSigner.TRAILER_IMPLICIT, -20, 115, -112, 123, -49, 89, -113, ISOFileInfo.A1, -7, 45, -14, ISO7816.INS_READ_BINARY2, 0, -108, 55, -97, ISO7816.INS_WRITE_BINARY, 46, -100, 110, 40, Utf8.REPLACEMENT_BYTE, Byte.MIN_VALUE, -16, Base64.padSymbol, -45, 37, ISOFileInfo.LCS_BYTE, -75, -25, 66, ISO7816.INS_READ_RECORD2, -57, -22, -9, 76, 17, 51, 3, -94, -84, 96};
    private byte[][] roundKeys;

    protected static void A(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        byte b7 = bArr[6];
        byte b8 = bArr[7];
        byte b9 = bArr[8];
        byte b10 = bArr[9];
        byte b11 = bArr[10];
        byte b12 = bArr[11];
        byte b13 = bArr[12];
        byte b14 = bArr[13];
        byte b15 = bArr[14];
        byte b16 = bArr[15];
        bArr[0] = (byte) ((((((b4 ^ b5) ^ b7) ^ b9) ^ b10) ^ b14) ^ b15);
        bArr[1] = (byte) ((((((b3 ^ b6) ^ b8) ^ b9) ^ b10) ^ b13) ^ b16);
        bArr[2] = (byte) ((((((b2 ^ b5) ^ b7) ^ b11) ^ b12) ^ b13) ^ b16);
        bArr[3] = (byte) ((((((b ^ b6) ^ b8) ^ b11) ^ b12) ^ b14) ^ b15);
        int i = b ^ b3;
        bArr[4] = (byte) (((((i ^ b6) ^ b9) ^ b12) ^ b15) ^ b16);
        int i2 = b2 ^ b4;
        bArr[5] = (byte) (((((i2 ^ b5) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[6] = (byte) (((((i ^ b8) ^ b10) ^ b11) ^ b13) ^ b14);
        bArr[7] = (byte) (((((i2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b14);
        int i3 = b ^ b2;
        bArr[8] = (byte) (((((i3 ^ b5) ^ b8) ^ b11) ^ b14) ^ b16);
        bArr[9] = (byte) (((((i3 ^ b6) ^ b7) ^ b12) ^ b13) ^ b15);
        int i4 = b3 ^ b4;
        bArr[10] = (byte) (((((i4 ^ b6) ^ b7) ^ b9) ^ b14) ^ b16);
        bArr[11] = (byte) (((((i4 ^ b5) ^ b8) ^ b10) ^ b13) ^ b15);
        int i5 = b2 ^ b3;
        bArr[12] = (byte) (((((i5 ^ b7) ^ b8) ^ b10) ^ b12) ^ b13);
        int i6 = b ^ b4;
        bArr[13] = (byte) (((((i6 ^ b7) ^ b8) ^ b9) ^ b11) ^ b14);
        bArr[14] = (byte) (((((i6 ^ b5) ^ b6) ^ b10) ^ b12) ^ b15);
        bArr[15] = (byte) (((((i5 ^ b5) ^ b6) ^ b9) ^ b11) ^ b16);
    }

    protected static void FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        A(bArr);
    }

    protected static void FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        A(bArr);
    }

    protected static byte SB1(byte b) {
        return SB1_sbox[b & 255];
    }

    protected static byte SB2(byte b) {
        return SB2_sbox[b & 255];
    }

    protected static byte SB3(byte b) {
        return SB3_sbox[b & 255];
    }

    protected static byte SB4(byte b) {
        return SB4_sbox[b & 255];
    }

    protected static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    protected static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    protected static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = (length >>> 3) - 2;
        byte[][] bArr2 = C;
        byte[] bArr3 = bArr2[i];
        byte[] bArr4 = bArr2[(i + 1) % 3];
        byte[] bArr5 = bArr2[(i + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int i2 = (i * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance((Class<?>) byte.class, i2 + 1, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (i2 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (i2 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int i3 = 1; i3 < i2; i3++) {
                A(bArr12[i3]);
            }
        }
        return bArr12;
    }

    protected static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        int i4 = 8 - i3;
        int i5 = bArr3[15 - i2] & 255;
        int i6 = 0;
        while (i6 < 16) {
            int i7 = bArr3[(i6 - i2) & 15] & 255;
            bArr[i6] = (byte) (((i5 << i4) | (i7 >>> i3)) ^ (bArr2[i6] & 255));
            i6++;
            i5 = i7;
        }
    }

    protected static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int i = length / 2;
        int i2 = length - 1;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2 - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = bArr2;
        }
    }

    protected static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.roundKeys = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.roundKeys == null) {
            throw new IllegalStateException("ARIA engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, i, bArr3, 0, 16);
        int length = this.roundKeys.length - 3;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            FO(bArr3, this.roundKeys[i3]);
            FE(bArr3, this.roundKeys[i4]);
            i3 = i4 + 1;
        }
        int i5 = i3 + 1;
        FO(bArr3, this.roundKeys[i3]);
        xor(bArr3, this.roundKeys[i5]);
        SL2(bArr3);
        xor(bArr3, this.roundKeys[i5 + 1]);
        System.arraycopy(bArr3, 0, bArr2, i2, 16);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
