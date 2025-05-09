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
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.AB, 118, ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117, 9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, Ascii.SUB, Ascii.ESC, 110, 90, ISOFileInfo.A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, AvcSpsUtils.PROFILE_IDC_EXTENDED, -49, ISO7816.INS_WRITE_BINARY, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, AvcSpsUtils.PROFILE_IDC_MAIN, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD, -51, 12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, Ascii.ETB, -60, -89, 126, Base64.padSymbol, 100, 93, Ascii.EM, 115, 96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, 11, -37, ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121, -25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, Ascii.US, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, 30, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.A1, -119, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, ISO7816.INS_DECREASE, 54, ISOFileInfo.A5, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, SignedBytes.MAX_POWER_OF_TWO, -93, -98, ISOFileInfo.DATA_BYTES2, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.CHANNEL_SECURITY, 67, ISO7816.INS_REHABILITATE_CHV, -60, -34, -23, -53, 84, 123, -108, ISO7816.INS_INCREASE, -90, ISO7816.INS_ENVELOPE, 35, Base64.padSymbol, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, ISOFileInfo.A1, 102, 40, -39, ISO7816.INS_CHANGE_CHV, -78, 118, 91, -94, 73, 109, ISOFileInfo.SECURITY_ATTR_EXP, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, ISO7816.INS_READ_RECORD_STAMPED, -110, 108, ISO7816.INS_MANAGE_CHANNEL, 72, 80, -3, -19, -71, ISO7816.INS_PUT_DATA, 94, Ascii.NAK, 70, 87, -89, ISOFileInfo.ENV_TEMP_EF, -99, -124, -112, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, 0, ISOFileInfo.SECURITY_ATTR_COMPACT, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, ISO7816.INS_DELETE_FILE, AvcSpsUtils.PROFILE_IDC_EXTENDED, 5, -72, ISO7816.INS_READ_RECORD2, 69, 6, ISO7816.INS_WRITE_BINARY, ISO7816.INS_UNBLOCK_CHV, 30, -113, ISO7816.INS_GET_DATA, Utf8.REPLACEMENT_BYTE, 15, 2, -63, -81, -67, 3, 1, 19, ISOFileInfo.LCS_BYTE, 107, 58, -111, 17, 65, 79, 103, ISO7816.INS_UPDATE_RECORD, -22, -105, -14, -49, -50, -16, ISO7816.INS_READ_BINARY_STAMPED, -26, 115, -106, -84, 116, ISO7816.INS_MSE, -25, -83, 53, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, -7, 55, -24, 28, 117, -33, 110, 71, -15, Ascii.SUB, 113, 29, 41, -59, -119, ISOFileInfo.FCI_BYTE, -73, ISOFileInfo.FCP_BYTE, 14, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, ISO7816.INS_WRITE_RECORD, 121, 32, -102, -37, ISO7816.INS_GET_RESPONSE, -2, 120, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, 49, ISO7816.INS_READ_BINARY2, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, ISOFileInfo.A0, ISO7816.INS_CREATE_FILE, 59, AvcSpsUtils.PROFILE_IDC_MAIN, -82, ISO7816.INS_PSO, -11, ISO7816.INS_READ_BINARY, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, ISOFileInfo.FILE_IDENTIFIER, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, ISO7816.INS_UPDATE_BINARY, 38, -31, 105, Ascii.DC4, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, 151, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA};

    public AESLightEngine() {
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (m5 & i) << 2;
        int i3 = i & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.C0;
        int i2 = this.ROUNDS;
        int i3 = i ^ iArr[i2][0];
        int i4 = this.C1 ^ iArr[i2][1];
        int i5 = this.C2 ^ iArr[i2][2];
        int i6 = i2 - 1;
        int i7 = iArr[i2][3] ^ this.C3;
        while (true) {
            byte[] bArr = Si;
            int i8 = i3 & 255;
            if (i6 <= 1) {
                int inv_mcol = inv_mcol((((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
                int inv_mcol2 = inv_mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
                int inv_mcol3 = inv_mcol((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
                int inv_mcol4 = inv_mcol((bArr[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr[i7 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
                this.C0 = ((((bArr[inv_mcol & 255] & 255) ^ ((bArr[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][0];
                this.C1 = ((((bArr[inv_mcol2 & 255] & 255) ^ ((bArr[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][1];
                this.C2 = ((((bArr[inv_mcol3 & 255] & 255) ^ ((bArr[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol4 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][2];
                this.C3 = iArr[0][3] ^ ((((bArr[inv_mcol4 & 255] & 255) ^ ((bArr[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol >> 24) & 255] << Ascii.CAN));
                return;
            }
            int inv_mcol5 = inv_mcol((((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
            int inv_mcol6 = inv_mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
            int inv_mcol7 = inv_mcol((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
            int inv_mcol8 = inv_mcol((bArr[(i3 >> 24) & 255] << Ascii.CAN) ^ (((bArr[i7 & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)));
            int i9 = i6 - 1;
            int i10 = inv_mcol8 ^ iArr[i6][3];
            int inv_mcol9 = inv_mcol((((bArr[inv_mcol5 & 255] & 255) ^ ((bArr[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][0];
            int inv_mcol10 = inv_mcol((((bArr[inv_mcol6 & 255] & 255) ^ ((bArr[(inv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i10 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][1];
            int inv_mcol11 = inv_mcol((((bArr[inv_mcol7 & 255] & 255) ^ ((bArr[(inv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i10 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][2];
            int inv_mcol12 = inv_mcol((((bArr[i10 & 255] & 255) ^ ((bArr[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(inv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr[(inv_mcol5 >> 24) & 255] << Ascii.CAN));
            int i11 = i9 - 1;
            i7 = iArr[i9][3] ^ inv_mcol12;
            i3 = inv_mcol9;
            i4 = inv_mcol10;
            i5 = inv_mcol11;
            i6 = i11;
        }
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.C0 ^ iArr[0][0];
        int i2 = this.C1 ^ iArr[0][1];
        int i3 = this.C2 ^ iArr[0][2];
        int i4 = this.C3 ^ iArr[0][3];
        int i5 = 1;
        while (i5 < this.ROUNDS - 1) {
            byte[] bArr = S;
            int mcol = mcol((((bArr[i & 255] & 255) ^ ((bArr[(i2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i5][0];
            int mcol2 = mcol((((bArr[i2 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr[(i >> 24) & 255] << Ascii.CAN)) ^ iArr[i5][1];
            int mcol3 = mcol((((bArr[i3 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i >> 16) & 255] & 255) << 16)) ^ (bArr[(i2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i5][2];
            int mcol4 = mcol(((((bArr[(i >> 8) & 255] & 255) << 8) ^ (bArr[i4 & 255] & 255)) ^ ((bArr[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr[(i3 >> 24) & 255] << Ascii.CAN));
            int i6 = i5 + 1;
            int i7 = mcol4 ^ iArr[i5][3];
            int mcol5 = mcol((((bArr[mcol & 255] & 255) ^ ((bArr[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][0];
            int mcol6 = mcol((((bArr[mcol2 & 255] & 255) ^ ((bArr[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr[(mcol >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][1];
            int mcol7 = mcol((((bArr[mcol3 & 255] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(mcol >> 16) & 255] & 255) << 16)) ^ (bArr[(mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i6][2];
            int mcol8 = mcol((((bArr[i7 & 255] & 255) ^ ((bArr[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr[(mcol3 >> 24) & 255] << Ascii.CAN));
            int i8 = i6 + 1;
            int i9 = mcol8 ^ iArr[i6][3];
            i2 = mcol6;
            i4 = i9;
            i = mcol5;
            i3 = mcol7;
            i5 = i8;
        }
        byte[] bArr2 = S;
        int mcol9 = mcol((((bArr2[i & 255] & 255) ^ ((bArr2[(i2 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i5][0];
        int mcol10 = mcol((((bArr2[i2 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i >> 24) & 255] << Ascii.CAN)) ^ iArr[i5][1];
        int mcol11 = mcol((((bArr2[i3 & 255] & 255) ^ ((bArr2[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i >> 16) & 255] & 255) << 16)) ^ (bArr2[(i2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i5][2];
        int mcol12 = mcol(((((bArr2[(i >> 8) & 255] & 255) << 8) ^ (bArr2[i4 & 255] & 255)) ^ ((bArr2[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i3 >> 24) & 255] << Ascii.CAN));
        int i10 = i5 + 1;
        int i11 = mcol12 ^ iArr[i5][3];
        this.C0 = iArr[i10][0] ^ ((((bArr2[mcol9 & 255] & 255) ^ ((bArr2[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i11 >> 24) & 255] << Ascii.CAN));
        this.C1 = ((((bArr2[mcol10 & 255] & 255) ^ ((bArr2[(mcol11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr[i10][1];
        this.C2 = ((((bArr2[mcol11 & 255] & 255) ^ ((bArr2[(i11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr[i10][2];
        this.C3 = iArr[i10][3] ^ ((((bArr2[i11 & 255] & 255) ^ ((bArr2[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(mcol11 >> 24) & 255] << Ascii.CAN));
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >> 2;
        int i2 = i + 6;
        this.ROUNDS = i2;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i2 + 1, 4);
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i3 - 1];
                iArr[i3][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr[i3][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr[i3][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr[i3][3] = littleEndianToInt4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt9;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt10;
            int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ 1);
            iArr[1][2] = subWord;
            int i4 = littleEndianToInt6 ^ subWord;
            iArr[1][3] = i4;
            int i5 = littleEndianToInt7 ^ i4;
            iArr[2][0] = i5;
            int i6 = littleEndianToInt8 ^ i5;
            iArr[2][1] = i6;
            int i7 = littleEndianToInt9 ^ i6;
            iArr[2][2] = i7;
            int i8 = littleEndianToInt10 ^ i7;
            iArr[2][3] = i8;
            int i9 = 2;
            for (int i10 = 3; i10 < 12; i10 += 3) {
                int subWord2 = subWord(shift(i8, 8)) ^ i9;
                int i11 = i9 << 1;
                int i12 = subWord ^ subWord2;
                iArr[i10][0] = i12;
                int i13 = i4 ^ i12;
                iArr[i10][1] = i13;
                int i14 = i5 ^ i13;
                iArr[i10][2] = i14;
                int i15 = i6 ^ i14;
                iArr[i10][3] = i15;
                int i16 = i7 ^ i15;
                int i17 = i10 + 1;
                iArr[i17][0] = i16;
                int i18 = i8 ^ i16;
                iArr[i17][1] = i18;
                int subWord3 = subWord(shift(i18, 8)) ^ i11;
                i9 = i11 << 1;
                subWord = i12 ^ subWord3;
                iArr[i17][2] = subWord;
                i4 = i13 ^ subWord;
                iArr[i17][3] = i4;
                i5 = i14 ^ i4;
                int i19 = i10 + 2;
                iArr[i19][0] = i5;
                i6 = i15 ^ i5;
                iArr[i19][1] = i6;
                i7 = i16 ^ i6;
                iArr[i19][2] = i7;
                i8 = i18 ^ i7;
                iArr[i19][3] = i8;
            }
            int subWord4 = (subWord(shift(i8, 8)) ^ i9) ^ subWord;
            iArr[12][0] = subWord4;
            int i20 = subWord4 ^ i4;
            iArr[12][1] = i20;
            int i21 = i20 ^ i5;
            iArr[12][2] = i21;
            iArr[12][3] = i21 ^ i6;
        } else {
            if (i != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i22 = 1;
            for (int i23 = 2; i23 < 14; i23 += 2) {
                int subWord5 = subWord(shift(littleEndianToInt18, 8)) ^ i22;
                i22 <<= 1;
                littleEndianToInt11 ^= subWord5;
                iArr[i23][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr[i23][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i23][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i23][3] = littleEndianToInt14;
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int i24 = i23 + 1;
                iArr[i24][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr[i24][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr[i24][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr[i24][3] = littleEndianToInt18;
            }
            int subWord6 = (subWord(shift(littleEndianToInt18, 8)) ^ i22) ^ littleEndianToInt11;
            iArr[14][0] = subWord6;
            int i25 = subWord6 ^ littleEndianToInt12;
            iArr[14][1] = i25;
            int i26 = i25 ^ littleEndianToInt13;
            iArr[14][2] = i26;
            iArr[14][3] = i26 ^ littleEndianToInt14;
        }
        if (!z) {
            for (int i27 = 1; i27 < this.ROUNDS; i27++) {
                for (int i28 = 0; i28 < 4; i28++) {
                    iArr[i27][i28] = inv_mcol(iArr[i27][i28]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << Ascii.CAN) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = bArr[i] & 255;
        this.C0 = i3;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i2] & 255) << 8);
        this.C0 = i5;
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 16);
        this.C0 = i7;
        int i8 = i6 + 1;
        this.C0 = i7 | (bArr[i6] << Ascii.CAN);
        int i9 = i8 + 1;
        int i10 = bArr[i8] & 255;
        this.C1 = i10;
        int i11 = i9 + 1;
        int i12 = ((bArr[i9] & 255) << 8) | i10;
        this.C1 = i12;
        int i13 = i11 + 1;
        int i14 = i12 | ((bArr[i11] & 255) << 16);
        this.C1 = i14;
        int i15 = i13 + 1;
        this.C1 = i14 | (bArr[i13] << Ascii.CAN);
        int i16 = i15 + 1;
        int i17 = bArr[i15] & 255;
        this.C2 = i17;
        int i18 = i16 + 1;
        int i19 = ((bArr[i16] & 255) << 8) | i17;
        this.C2 = i19;
        int i20 = i18 + 1;
        int i21 = i19 | ((bArr[i18] & 255) << 16);
        this.C2 = i21;
        int i22 = i20 + 1;
        this.C2 = i21 | (bArr[i20] << Ascii.CAN);
        int i23 = i22 + 1;
        int i24 = bArr[i22] & 255;
        this.C3 = i24;
        int i25 = i23 + 1;
        int i26 = ((bArr[i23] & 255) << 8) | i24;
        this.C3 = i26;
        int i27 = i26 | ((bArr[i25] & 255) << 16);
        this.C3 = i27;
        this.C3 = (bArr[i25 + 1] << Ascii.CAN) | i27;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        int[][] iArr = this.WorkingKey;
        if (z) {
            encryptBlock(iArr);
        } else {
            decryptBlock(iArr);
        }
        packBlock(bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
